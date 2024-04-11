package com.google.android.settings.aware;

import android.content.Context;
import android.content.IntentFilter;
import android.os.SystemProperties;
import com.android.settings.aware.AwareFeatureProvider;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.overlay.FeatureFactory;

public class AwareGesturesCategoryPreferenceController extends BasePreferenceController {
    final Context mContext;
    final AwareFeatureProvider mFeatureProvider;

    public /* bridge */ /* synthetic */ Class getBackgroundWorkerClass() {
        return super.getBackgroundWorkerClass();
    }

    public /* bridge */ /* synthetic */ IntentFilter getIntentFilter() {
        return super.getIntentFilter();
    }

    public /* bridge */ /* synthetic */ int getSliceHighlightMenuRes() {
        return super.getSliceHighlightMenuRes();
    }

    public /* bridge */ /* synthetic */ boolean hasAsyncUpdate() {
        return super.hasAsyncUpdate();
    }

    public /* bridge */ /* synthetic */ boolean isPublicSlice() {
        return super.isPublicSlice();
    }

    public /* bridge */ /* synthetic */ boolean isSliceable() {
        return super.isSliceable();
    }

    public /* bridge */ /* synthetic */ boolean useDynamicSliceSummary() {
        return super.useDynamicSliceSummary();
    }

    public AwareGesturesCategoryPreferenceController(Context context, String str) {
        super(context, str);
        this.mContext = context;
        this.mFeatureProvider = FeatureFactory.getFeatureFactory().getAwareFeatureProvider();
    }

    public int getAvailabilityStatus() {
        return SystemProperties.getBoolean("ro.vendor.aware_available", false) ? 0 : 3;
    }
}
