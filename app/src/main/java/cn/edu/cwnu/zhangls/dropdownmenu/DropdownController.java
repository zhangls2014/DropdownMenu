package cn.edu.cwnu.zhangls.dropdownmenu;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;

/**
 * Created by zhangls on 2016/8/27.
 *
 */
public class DropdownController {
    View mask;
    DropdownButton mDropdownButton;
    ListView mListView;

    Animation dropdown_in, dropdown_out, dropdown_mask_out;

    public DropdownController(Context context, DropdownButton mDropdownButton, ListView mListView, View mask) {
        this.mDropdownButton = mDropdownButton;
        this.mListView = mListView;
        this.mask = mask;

        dropdown_in = AnimationUtils.loadAnimation(context, R.anim.dropdown_in);
        dropdown_out = AnimationUtils.loadAnimation(context,R.anim.dropdown_out);
        dropdown_mask_out = AnimationUtils.loadAnimation(context,R.anim.dropdown_mask_out);
    }

    public void show() {
        mask.clearAnimation();
        mask.setVisibility(View.VISIBLE);
        mListView.clearAnimation();
        mListView.setVisibility(View.VISIBLE);
        mListView.startAnimation(dropdown_in);
        mDropdownButton.setState(DropdownButton.State.Checked);
    }

    public void hide() {
        if (mListView != null) {
            mListView.clearAnimation();
            mListView.startAnimation(dropdown_out);
            mask.clearAnimation();
            mask.startAnimation(dropdown_mask_out);
        }
    }

    private void reset() {
        mListView.setVisibility(View.GONE);
        mask.setVisibility(View.GONE);

        mListView.clearAnimation();
        mask.clearAnimation();
    }

    public void init() {
        reset();
        dropdown_mask_out.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                reset();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }
}
