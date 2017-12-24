package cn.edu.cwnu.zhangls.dropdownmenu;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 下拉菜单数据结构体
 *
 * @author zhangls
 */
public class MenuTab {

    /**
     * 未选中状态
     */
    public static final int STATUS_UNCHECKED = 1;
    /**
     * 选中状态
     */
    public static final int STATUS_CHECKED = 2;
    /**
     * tab 标题
     */
    private String title;
    /**
     * tab 状态，
     */
    @Status
    private int status;

    public MenuTab(String title, int status) {
        this.title = title;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Status
    public int getStatus() {
        return status;
    }

    public void setStatus(@Status int status) {
        this.status = status;
    }

    @IntDef({STATUS_UNCHECKED, STATUS_CHECKED})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Status {
    }
}
