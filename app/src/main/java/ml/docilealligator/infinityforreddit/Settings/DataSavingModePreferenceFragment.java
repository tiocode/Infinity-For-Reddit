package ml.docilealligator.infinityforreddit.Settings;

import android.os.Bundle;

import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;

import org.greenrobot.eventbus.EventBus;

import ml.docilealligator.infinityforreddit.Event.ChangeDataSavingModeEvent;
import ml.docilealligator.infinityforreddit.Event.ChangeDisableImagePreviewEvent;
import ml.docilealligator.infinityforreddit.R;
import ml.docilealligator.infinityforreddit.Utils.SharedPreferencesUtils;

public class DataSavingModePreferenceFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.data_saving_mode_preferences, rootKey);

        ListPreference dataSavingModeListPreference = findPreference(SharedPreferencesUtils.DATA_SAVING_MODE);
        SwitchPreference disableImagePreviewPreference = findPreference(SharedPreferencesUtils.DISABLE_IMAGE_PREVIEW);

        if (dataSavingModeListPreference != null) {
            dataSavingModeListPreference.setOnPreferenceChangeListener((preference, newValue) -> {
                EventBus.getDefault().post(new ChangeDataSavingModeEvent((String) newValue));
                return true;
            });
        }

        if (disableImagePreviewPreference != null) {
            disableImagePreviewPreference.setOnPreferenceChangeListener((preference, newValue) -> {
                EventBus.getDefault().post(new ChangeDisableImagePreviewEvent((Boolean) newValue));
                return true;
            });
        }
    }
}