package uk.co.ribot.androidboilerplate.ui.base;


import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import uk.co.ribot.androidboilerplate.BoilerplateApplication;
import uk.co.ribot.androidboilerplate.injection.component.DaggerFragmentComponent;
import uk.co.ribot.androidboilerplate.injection.component.FragmentComponent;
import uk.co.ribot.androidboilerplate.injection.module.FragmentModule;
import uk.co.ribot.androidboilerplate.util.Consts;

/**
 * Created by Max Chervatiuk on 24.03.16.
 * Email: duo.blood@gmail.com
 */
public abstract class BaseFragment extends Fragment {

    private FragmentComponent mFragmentComponent;
    private boolean mAddToBAckStack;
    private long mLastClickTime = SystemClock.elapsedRealtime();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void show(FragmentManager fragmentManager) {
        show(fragmentManager, true, getContainer());
    }

    public void show(FragmentManager fragmentManager, @IdRes int container) {
        show(fragmentManager, true, container);
    }

    public void show(FragmentManager fragmentManager, boolean addToBackStack) {
        show(fragmentManager, addToBackStack, getContainer());
    }

    public void show(FragmentManager fragmentManager, boolean addToBackStack, int container) {
        this.mAddToBAckStack = addToBackStack;
        replaceFragment(fragmentManager, addToBackStack, container);
    }

    protected void replaceFragment(FragmentManager fragmentManager, boolean addToBackStack, int container) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(container, this, getName());
        if (addToBackStack)
            transaction.addToBackStack(getName());
        transaction.commitAllowingStateLoss();
    }

    public FragmentComponent getFragmentComponent() {
        if (mFragmentComponent == null) {
            mFragmentComponent = DaggerFragmentComponent.builder()
                    .fragmentModule(new FragmentModule(this))
                    .applicationComponent(BoilerplateApplication.get(getContext()).getComponent())
                    .build();
        }
        return mFragmentComponent;
    }

    public boolean isActivityPaused() {
        Activity activity = getActivity();
        if (activity == null) return true;
        if (activity instanceof BaseActivity) {
            return ((BaseActivity) activity).isPaused();
        } else {
            return false;
        }
    }

    protected boolean isClickAllowed() {
        long now = SystemClock.elapsedRealtime();
        if (now - mLastClickTime > Consts.CLICK_TIME_INTERVAL) {
            mLastClickTime = now;
            return true;
        } else return false;
    }

    public abstract String getName();

    public abstract int getContainer();

}
