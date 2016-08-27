package cn.edu.cwnu.zhangls.dropdownmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<DropdownItemObject> data = new ArrayList<>(2);//全部讨论
    private static final int ID_TYPE_ALL = 0;
    private static final int ID_TYPE_MY = 1;
    private static final String TYPE_ALL = "全部讨论";
    private static final String TYPE_MY = "我的讨论";

    View mask;
    DropdownButton mDropdownButton;
    ListView mListView;
    MyListAdapter adapter;

    private DropdownController mDropdownController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mask = findViewById(R.id.mask);
        mListView = (ListView) findViewById(R.id.list_view);
        mDropdownButton = (DropdownButton) findViewById(R.id.dropdown_btn);
        findViewById(R.id.btn_unselected).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDropdownButton.getState() == DropdownButton.State.Checked) {
                    mDropdownController.hide();
                }
                mDropdownButton.setState(DropdownButton.State.Unselected);
            }
        });
        mDropdownController = new DropdownController(this, mDropdownButton, mListView, mask);

        data.add(new DropdownItemObject(ID_TYPE_ALL, TYPE_ALL));
        data.add(new DropdownItemObject(ID_TYPE_MY, TYPE_MY));
        mDropdownButton.setText(data.get(0).getText());
        mDropdownButton.setState(DropdownButton.State.Unselected);
        adapter = new MyListAdapter(MainActivity.this, data);
        mListView.setAdapter(adapter);

        mDropdownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDropdownButton.getState() ==  DropdownButton.State.Unselected) {
                    mDropdownButton.setState(DropdownButton.State.Closed);
                } else if (mDropdownButton.getState() ==  DropdownButton.State.Closed) {
                    mDropdownController.show();
                } else {
                    mDropdownButton.setState(DropdownButton.State.Closed);
                    mDropdownController.hide();
                }
            }
        });

        mask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDropdownController.hide();
                mDropdownButton.setState(DropdownButton.State.Closed);
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapter.getCheckedNum() != i) {
                    adapter.setCheckedNum(i);
                    adapter.notifyDataSetChanged();
                }
                mDropdownController.hide();
                mDropdownButton.setState(DropdownButton.State.Closed);
                mDropdownButton.setText(data.get(i).getText());
            }
        });

        mDropdownController.init();
    }
}
