package cn.edu.cwnu.zhangls.dropdownmenu;

/**
 * Created by zhangls on 2016/8/27.
 * 保存ListView Item的信息
 */
public class DropdownItemObject {
    private int id;
    private String text;

    public DropdownItemObject(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
