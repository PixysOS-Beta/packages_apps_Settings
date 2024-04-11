package com.google.android.settings.aware;

import android.content.Context;
import android.provider.Settings;
import com.android.settings.R;

public class TapDialogGesturePreferenceController extends AwareGesturePreferenceController {

    public TapDialogGesturePreferenceController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        if (AwareHelper.isTapAvailableOnTheDevice()) {
            return super.getAvailabilityStatus();
        }
        return 3;
    }

    /* access modifiers changed from: protected */
    public CharSequence getGestureSummary() {
        return mContext.getText(isTapGestureEnabled() ? R.string.gesture_tap_on_summary : R.string.gesture_setting_off);
    }

    private boolean isTapGestureEnabled() {
        return mHelper.isEnabled() && Settings.Secure.getInt(mContext.getContentResolver(), "tap_gesture", 0) == 1;
    }
}
