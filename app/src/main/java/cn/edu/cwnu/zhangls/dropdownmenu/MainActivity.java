package cn.edu.cwnu.zhangls.dropdownmenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private DropDownMenu dropDownMenu;
    private Integer index = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<MenuTab> menuTabs = new ArrayList<>();
        menuTabs.add(new MenuTab("不限", MenuTab.STATUS_UNCHECKED));
        menuTabs.add(new MenuTab("男", MenuTab.STATUS_UNCHECKED));
        menuTabs.add(new MenuTab("女", MenuTab.STATUS_UNCHECKED));

        RecyclerView recyclerView = new RecyclerView(this);
        recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        recyclerView.setLayoutParams(new RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        final OnItemClickListener onItemClickListener = (view, position) -> {
            if (menuTabs.get(position).getStatus() == MenuTab.STATUS_CHECKED) {
                menuTabs.get(position).setStatus(MenuTab.STATUS_UNCHECKED);
            } else {
                menuTabs.get(position).setStatus(MenuTab.STATUS_CHECKED);
                if (index != null) {
                    menuTabs.get(index).setStatus(MenuTab.STATUS_UNCHECKED);
                    recyclerView.getAdapter().notifyItemChanged(index, menuTabs.get(position));
                }
                index = position;
            }
            recyclerView.getAdapter().notifyItemChanged(position, menuTabs.get(position));
        };
        final MenuAdapter adapter = new MenuAdapter(menuTabs, onItemClickListener);
        recyclerView.setAdapter(adapter);

        dropDownMenu = findViewById(R.id.menu);
        dropDownMenu.setDropDownMenu("性别", recyclerView, dropDownMenu);
    }

    @Override
    public void onBackPressed() {
        if (dropDownMenu.isShowing()) {
            dropDownMenu.closeMenu();
        } else {
            super.onBackPressed();
        }
    }
}
