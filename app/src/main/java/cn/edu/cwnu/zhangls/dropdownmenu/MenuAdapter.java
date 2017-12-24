package cn.edu.cwnu.zhangls.dropdownmenu;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * 下拉菜单 item
 *
 * @author zhangls
 */
public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    private ArrayList<MenuTab> menuTab;
    private OnItemClickListener onItemClickListener;


    public MenuAdapter(ArrayList<MenuTab> menuTab, OnItemClickListener onItemClickListener) {
        this.menuTab = menuTab;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View inflate = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_dropdown_tab, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(menuTab.get(position).getTitle());
        if (menuTab.get(position).getStatus() == MenuTab.STATUS_CHECKED) {
            holder.image.setVisibility(View.VISIBLE);
            holder.image.setImageResource(R.drawable.ic_check_black_24dp);
        } else {
            holder.image.setVisibility(View.INVISIBLE);
        }
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(holder.itemView, position));
        }
    }

    @Override
    public int getItemCount() {
        return menuTab == null ? 0 : menuTab.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView image;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tab_title);
            image = itemView.findViewById(R.id.tab_checked);
        }
    }
}
