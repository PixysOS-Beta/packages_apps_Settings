package com.google.android.settings.aware;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import androidx.preference.PreferenceViewHolder;
import com.android.settings.R;
import com.android.settings.dashboard.DashboardFragment;
import com.android.settings.overlay.FeatureFactory;
import com.android.settingslib.CustomDialogPreferenceCompat;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import com.google.android.settings.aware.AwareHelper;

public class AwareDialogPreferenceBase extends CustomDialogPreferenceCompat {
    protected AwareHelper mHelper;
    private View mInfoIcon;
    private MetricsFeatureProvider mMetricsFeatureProvider;
    private View mSummary;
    private View mTitle;

    /* access modifiers changed from: protected */
    public boolean isAvailable() {
        return false;
    }

    public AwareDialogPreferenceBase(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init();
    }

    public AwareDialogPreferenceBase(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public AwareDialogPreferenceBase(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public AwareDialogPreferenceBase(Context context) {
        super(context);
        init();
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        mTitle = preferenceViewHolder.findViewById(16908310);
        mSummary = preferenceViewHolder.findViewById(16908304);
        mInfoIcon = preferenceViewHolder.findViewById(R.id.info_button);
        updatePreference();
    }

    public void onAttached() {
        super.onAttached();
        AwareHelper awareHelper = mHelper;
        if (awareHelper != null) {
            awareHelper.register(new AwareHelper.Callback() {
                public void onChange(Uri uri) {
                    AwareDialogPreferenceBase.updatePreference();
                    CharSequence summary = AwareDialogPreferenceBase.getSummary();
                    if (!TextUtils.isEmpty(summary)) {
                        AwareDialogPreferenceBase.setSummary(summary);
                    }
                }
            });
        }
    }

    public void onDetached() {
        super.onDetached();
        AwareHelper awareHelper = mHelper;
        if (awareHelper != null) {
            awareHelper.unregister();
        }
    }

    public void performClick() {
        if (isAvailable()) {
            performEnabledClick();
        } else {
            super.performClick();
        }
    }

    /* access modifiers changed from: protected */
    public void updatePreference() {
        View view = mTitle;
        if (view != null) {
            view.setEnabled(isAvailable());
        }
        View view2 = mSummary;
        if (view2 != null) {
            view2.setEnabled(isAvailable());
        }
        if (mInfoIcon != null) {
            int i = 0;
            boolean z = isAvailable() || mHelper.isAirplaneModeOn() || mHelper.isBatterySaverModeOn();
            View view3 = mInfoIcon;
            if (z) {
                i = 8;
            }
            view3.setVisibility(i);
        }
    }

    /* access modifiers changed from: protected */
    public void performEnabledClick() {
        mMetricsFeatureProvider.logClickedPreference( getSourceMetricsCategory());
    }

    /* access modifiers changed from: protected */
    public int getSourceMetricsCategory() {
        return getExtras().getInt(DashboardFragment.CATEGORY);
    }

    private void init() {
        Context context = getContext();
        mMetricsFeatureProvider = FeatureFactory.getFeatureFactory().getMetricsFeatureProvider();
        setWidgetLayoutResource(R.layout.preference_widget_info);
        mHelper = new AwareHelper(context);
    }
}
