package com.kypi.demoproject.mvp.features;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.hanks.htextview.HTextViewType;
import com.kypi.demoproject.R;
import com.kypi.demoproject.base.BaseActivity;
import com.kypi.demoproject.di.component.ActivityComponent;
import com.kypi.demoproject.domain.scheduler.SimpleEmptyObserver;
import com.kypi.demoproject.mvp.activities.MainActivity;
import com.kypi.demoproject.widget.htextview.CustomHTextView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

public class SplashScreenActivity extends BaseActivity {

    public final boolean IS_SHOW_SPLASH = false;



    // Activity constants
    private static final long ANIMATION_DURATION = 1500;
    private static final long TIME_TO_RUN_ANIM = 400;
    private static final long TIME_TO_NEXT_SCREEN = 3000;

    // Text view for set text later
    @BindView(R.id.splash_app_name)
    CustomHTextView hTextView;

    // Bookmark the subscription  for cancel event move next screen if activity destroyed
    private CompositeDisposable subscribe;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash_screen;
    }

    @Override
    protected void setupActivityComponent(ActivityComponent mActivityComponent) {
    }

    @Override
    protected void onActivityCreated(Bundle savedInstanceState) {
        if(!IS_SHOW_SPLASH){
            gotoNextScreen();
            return;
        }


        subscribe = new CompositeDisposable();
        // Run animation text after TIME_TO_RUN_ANIM

        subscribe.add(Observable.defer(() -> Observable.just("").
                delay(TIME_TO_RUN_ANIM, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).
                doOnNext(ignore -> showText())).
                subscribe());

        // Go next screen after TIME_TO_NEXT_SCREEN
        subscribe.add(Observable.defer(() -> Observable.just("").
                delay(TIME_TO_NEXT_SCREEN, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).
                doOnNext(ignore -> gotoNextScreen())).subscribeWith(new SimpleEmptyObserver<>()));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        subscribe.dispose();
    }

    /**
     * Show animation text
     */
    private void showText() {
        // init Line animation
        hTextView.setTextColor(Color.BLACK);
        hTextView.setBackgroundColor(Color.TRANSPARENT);
        hTextView.setTypeface(null);
        hTextView.setAnimateType(HTextViewType.LINE);
        hTextView.setDuration(ANIMATION_DURATION);
        hTextView.animateText(getString(R.string.app_name));

        // init alpha animation
        Animation animation = new AlphaAnimation(0, 1);
        animation.setDuration(ANIMATION_DURATION);
        hTextView.startAnimation(animation);
    }

    /**
     * go to next screen
     */
    private void gotoNextScreen() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
