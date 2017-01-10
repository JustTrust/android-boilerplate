package uk.co.ribot.androidboilerplate.injection.module;


import android.support.v4.app.Fragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Max Chervatiuk on 31.03.16.
 * Email: duo.blood@gmail.com
 */
@Module
public class FragmentModule {

    private Fragment mFragment;

    public FragmentModule(Fragment fragment) {
        mFragment = fragment;
    }

    @Provides
    Fragment provideFragment() {
        return mFragment;
    }
}
