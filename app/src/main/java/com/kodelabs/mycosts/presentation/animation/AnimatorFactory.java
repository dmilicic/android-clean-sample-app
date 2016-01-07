package com.kodelabs.mycosts.presentation.animation;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;

import com.kodelabs.mycosts.R;

import io.codetail.animation.SupportAnimator;

/**
 * Created by dmilicic on 1/7/16.
 */
public class AnimatorFactory {

    public static final int REVEAL_ANIMATION_LENGTH = 350; // in milliseconds

    /**
     * Creates a circural reveal animation from a given source view. While revealing it uses the reveal layout and after
     * the animation completes, it starts the activity given in the intent.
     *
     * @param src          The source view from which the circular animation starts.
     * @param revealLayout The layout to reveal in the animation.
     * @param intent       The intent used to start another activity.
     * @param activity     The activity is needed as a context object.
     */
    public static void enterReveal(ViewGroup revealLayout, final Intent intent, final Activity activity) {

        int cx = (revealLayout.getLeft() + revealLayout.getRight());
        int cy = revealLayout.getTop();
        int finalRadius = Math.max(revealLayout.getWidth(), revealLayout.getHeight());

        AnimatorListener animatorListener = new AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                activity.startActivity(intent);
                activity.overridePendingTransition(0, R.anim.hold);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        };


//        src.setVisibility(View.INVISIBLE);

        // make the view visible and start the animation
        revealLayout.setVisibility(View.VISIBLE);

        if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {

            Animator anim =
                    ViewAnimationUtils.createCircularReveal(revealLayout, cx, cy, 0, finalRadius);

            anim.setDuration(REVEAL_ANIMATION_LENGTH);
            anim.addListener(animatorListener);
            anim.start();
        } else {
            // create the animator for this view (the start radius is zero)
            SupportAnimator anim =
                    io.codetail.animation.ViewAnimationUtils.createCircularReveal(revealLayout, cx, cy, 0, finalRadius);

            anim.setDuration(REVEAL_ANIMATION_LENGTH);
            anim.addListener(new SupportAnimator.AnimatorListener() {
                @Override
                public void onAnimationStart() {

                }

                @Override
                public void onAnimationEnd() {
                    activity.startActivity(intent);
                    activity.overridePendingTransition(0, R.anim.hold);
                }

                @Override
                public void onAnimationCancel() {

                }

                @Override
                public void onAnimationRepeat() {

                }
            });
            anim.start();
        }
    }
}
