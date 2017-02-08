package uk.co.ribot.androidboilerplate.data.local;

import android.content.Context;

import com.securepreferences.SecurePreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import uk.co.ribot.androidboilerplate.injection.ApplicationContext;
import uk.co.ribot.androidboilerplate.util.Consts;

@Singleton
public class PreferencesHelper {

    public static final String PREF_FILE_NAME = "project_pref.xml";

    private final SecurePreferences mPref;

    @Inject
    public PreferencesHelper(@ApplicationContext Context context) {
        mPref = new SecurePreferences(context, Consts.PREFERENCE_PASSWORD, PREF_FILE_NAME);    }

    public void clear() {
        mPref.edit().clear().apply();
    }

}
