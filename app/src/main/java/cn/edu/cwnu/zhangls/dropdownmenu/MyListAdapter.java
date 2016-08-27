package cn.edu.cwnu.zhangls.dropdownmenu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zhangls on 2016/8/27.
 *
 */
public class MyListAdapter extends BaseAdapter {
    private Context context;
    private List<DropdownItemObject> list;
    private int checkedNum = 0;

    public MyListAdapter(Context context, List<DropdownItemObject> list) {
        this.context = context;
        this.list = list;
    }

    public int getCheckedNum() {
        return checkedNum;
    }

    public void setCheckedNum(int checkedNum) {
        this.checkedNum = checkedNum;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.dropdown_tab_list_item, viewGroup, false);
            holder.textView = (TextView) view;
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.textView.setText(list.get(i).getText());
        if (i == checkedNum) {
            Drawable icon = ContextCompat.getDrawable(context, R.drawable.ic_task_status_list_check);
            holder.textView.setCompoundDrawablesWithIntrinsicBounds(null,null,icon,null);
        } else {
            holder.textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
        return view;
    }

    static class ViewHolder {
        public TextView textView;
    }
}
