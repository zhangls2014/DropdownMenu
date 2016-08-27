package cn.edu.cwnu.zhangls.dropdownmenu;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by zhangls on 2016/8/27.
 *
 */
public class DropdownButton extends RelativeLayout {
    private TextView textView;
    private View bottomLine;
    //选中标记
    private State state = State.Unselected;

    public enum State {
        Unselected,
        Checked,
        Closed
    };
    public DropdownButton(Context context) {
        this(context, null);
        init();
    }

    public DropdownButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init();
    }

    public DropdownButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DropdownButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        View view =  LayoutInflater.from(getContext()).inflate(R.layout.dropdown_tab_button,this, true);
        textView = (TextView) view.findViewById(R.id.textView);
        bottomLine = view.findViewById(R.id.bottomLine);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
        Drawable icon;
        if (state == State.Unselected) {
            icon = ContextCompat.getDrawable(getContext(), R.drawable.ic_dropdown_normal);
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.font_black_6));
            bottomLine.setVisibility(INVISIBLE);
        } else if (state == State.Checked) {
            icon = ContextCompat.getDrawable(getContext(), R.drawable.ic_dropdown_actived);
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.green));
            bottomLine.setVisibility(VISIBLE);
        } else {
            icon = ContextCompat.getDrawable(getContext(), R.drawable.ic_dropdown_actived_true);
            textView.setTextColor(Color.RED);
            bottomLine.setVisibility(INVISIBLE);
        }
        //将图片绘制在文本的右侧
        textView.setCompoundDrawablesWithIntrinsicBounds(null, null, icon, null);
    }

    public void setText(CharSequence text) {
        textView.setText(text);
    }
}
