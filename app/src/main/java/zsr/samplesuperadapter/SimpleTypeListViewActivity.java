package zsr.samplesuperadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import zsr.samplesuperadapter.listview.SuperListViewAdapter;
import zsr.samplesuperadapter.listview.SuperListViewHolder;

/**
 * author zsr 2017 6 27
 * 使用listView实现的列表，itemType一致。
 */
public class SimpleTypeListViewActivity extends AppCompatActivity {
    private ListView listView;
    List<String> data ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_list);
        setTitle("使用listView实现的列表，itemType一致。");
        listView = (ListView) findViewById(R.id.listview);
        getData();
        listView.setAdapter(new SuperListViewAdapter<String>(data,android.R.layout.simple_list_item_1) {
            @Override
            public void convert(SuperListViewHolder svh, int position, int viewType, String itemData) {
                svh.setText(android.R.id.text1,itemData);
            }
        });
    }

    private void getData() {
        data = new ArrayList<>();
        for (int i = 0; i < 50 ;i++){
            data.add(i+"");
        }
    }
}
