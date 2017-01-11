package uk.co.ribot.androidboilerplate.ui.base;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import uk.co.ribot.androidboilerplate.BoilerplateApplication;
import uk.co.ribot.androidboilerplate.injection.component.DaggerFragmentComponent;
import uk.co.ribot.androidboilerplate.injection.component.FragmentComponent;
import uk.co.ribot.androidboilerplate.injection.module.FragmentModule;
import uk.co.ribot.androidboilerplate.util.Consts;

/**
 * Created by Max Chervatiuk on 06.04.16.
 * Email: duo.blood@gmail.com
 */
public class BaseDialogFragment extends DialogFragment {

    private FragmentComponent mFragmentComponent;
    private DialogInterface.OnDismissListener onDismissListener;
    private BlurDialogFragmentHelperWithRadius mHelper;
    private long mLastClickTime = SystemClock.elapsedRealtime();

    public FragmentComponent getFragmentComponent() {
        if (mFragmentComponent == null) {
            mFragmentComponent = DaggerFragmentComponent.builder()
                    .fragmentModule(new FragmentModule(this))
                    .applicationComponent(BoilerplateApplication.get(getContext()).getComponent())
                    .build();
        }
        return mFragmentComponent;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mHelper = new BlurDialogFragmentHelperWithRadius(this, 1);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mHelper.onActivityCreated();
    }

    @Override
    public void onStart() {
        super.onStart();
        mHelper.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mHelper.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mHelper.onPause();
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        mHelper.onDismiss();
        super.onDismiss(dialog);
        if (onDismissListener != null) {
            onDismissListener.onDismiss(dialog);
        }
    }

    protected boolean isClickAllowed() {
        long now = SystemClock.elapsedRealtime();
        if (now - mLastClickTime > Consts.CLICK_TIME_INTERVAL) {
            mLastClickTime = now;
            return true;
        } else return false;
    }
}
