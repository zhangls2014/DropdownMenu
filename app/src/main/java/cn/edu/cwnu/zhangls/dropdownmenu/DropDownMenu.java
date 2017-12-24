package cn.edu.cwnu.zhangls.dropdownmenu;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;


/**
 * 下拉菜单按钮
 *
 * @author zhangls
 */
public class DropDownMenu extends LinearLayout {

    /**
     * 下拉菜单按钮
     */
    private LinearLayout parentGroup;
    /**
     * 菜单所依附的视图
     */
    private View parentView;
    /**
     * 底部容器
     */
    private PopupWindow containerView;
    /**
     * 底部容器，包含 PopupWindow，maskView
     */
    private FrameLayout contentView;
    /**
     * 遮罩半透明 View，点击可关闭 DropDownMenu
     */
    private View maskView;
    /**
     * tab 选中颜色
     */
    private int textSelectedColor;
    /**
     * tab 未选中颜色
     */
    private int textUnselectedColor;
    /**
     * 遮罩颜色
     */
    private int maskColor;
    /**
     * tab 字体大小
     */
    private int menuTextSize;
    /**
     * tab 选中图标
     */
    private int menuSelectedIcon;
    /**
     * tab 未选中图标
     */
    private int menuUnselectedIcon;
    /**
     * 菜单背景颜色
     */
    private int menuBackgroundColor;
    /**
     * 菜单名称
     */
    private TextView name;
    /**
     * 菜单箭头
     */
    private ImageView arrow;


    public DropDownMenu(Context context) {
        this(context, null);
    }

    public DropDownMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DropDownMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DropDownMenu);
        textSelectedColor = a.getColor(R.styleable.DropDownMenu_textSelectedColor,
                ContextCompat.getColor(context, android.R.color.black));
        textUnselectedColor = a.getColor(R.styleable.DropDownMenu_textUnselectedColor,
                ContextCompat.getColor(context, android.R.color.black));
        menuSelectedIcon = a.getResourceId(R.styleable.DropDownMenu_menuSelectedIcon,
                R.drawable.ic_arrow_drop_up_black_24dp);
        menuUnselectedIcon = a.getResourceId(R.styleable.DropDownMenu_menuUnselectedIcon,
                R.drawable.ic_arrow_drop_down_black_24dp);

        menuBackgroundColor = a.getColor(R.styleable.DropDownMenu_menuBackgroundColor,
                ContextCompat.getColor(context, android.R.color.white));
        maskColor = a.getColor(R.styleable.DropDownMenu_maskColor,
                ContextCompat.getColor(context, R.color.maskColor));
        menuTextSize = a.getDimensionPixelSize(R.styleable.DropDownMenu_menuTextSize, menuTextSize);
        a.recycle();

        LayoutInflater.from(context).inflate(R.layout.item_dropdown_menu, this);
        parentGroup = findViewById(R.id.parent_group);
        name = parentGroup.findViewById(R.id.name);
        arrow = parentGroup.findViewById(R.id.arrow);
        name.setTextColor(textUnselectedColor);
        arrow.setImageResource(menuUnselectedIcon);

        contentView = new FrameLayout(context);
        contentView.setLayoutParams(new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
    }

    /**
     * 初始化下拉菜单
     *
     * @param tabText      默认文字
     * @param recyclerView 下拉列表
     * @param parentView   父视图
     */
    public void setDropDownMenu(@NonNull String tabText, @NonNull View recyclerView, @NonNull View parentView) {
        recyclerView.setBackgroundColor(menuBackgroundColor);
        this.parentView = parentView;
        name.setText(tabText);
        maskView = new View(getContext());
        maskView.setLayoutParams(new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT));
        maskView.setBackgroundColor(maskColor);
        maskView.setOnClickListener(v -> closeMenu());
        contentView.addView(maskView, 0);
        contentView.addView(recyclerView, 1);

        containerView = new PopupWindow(
                contentView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            containerView.setOverlapAnchor(false);
        }
        containerView.setAnimationStyle(R.style.PopupWinDowAnimStyle);
        containerView.setOnDismissListener(() -> arrow.setImageResource(menuUnselectedIcon));

        parentGroup.setOnClickListener(v -> {
            if (containerView.isShowing()) {
                closeMenu();
            } else {
                openMenu();
            }
        });
    }

    /**
     * 打开菜单
     */
    private void openMenu() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            int[] location = new int[2];
            parentView.getLocationOnScreen(location);
            int offsetY = location[1] + parentView.getHeight();
            if (Build.VERSION.SDK_INT == Build.VERSION_CODES.N_MR1) {
                int screenHeight = ScreenUtils.getScreenHeight(parentView.getContext());
                containerView.setHeight(screenHeight - offsetY);
            }
            containerView.showAtLocation(parentView, Gravity.NO_GRAVITY, 0, offsetY);
        } else {
            containerView.showAsDropDown(parentView);
        }
        arrow.setImageResource(menuSelectedIcon);
        name.setTextColor(textSelectedColor);
    }

    /**
     * 关闭菜单
     */
    public void closeMenu() {
        containerView.dismiss();
        arrow.setImageResource(menuUnselectedIcon);
        name.setTextColor(textUnselectedColor);
    }

    /**
     * 返回下拉菜单是否被显示
     *
     * @return PopupWindow 显示状态
     */
    public boolean isShowing() {
        return containerView.isShowing();
    }
}
