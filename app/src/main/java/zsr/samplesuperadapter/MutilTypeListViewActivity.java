package zsr.samplesuperadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import zsr.samplesuperadapter.listview.SuperListViewAdapter;
import zsr.samplesuperadapter.listview.SuperListViewHolder;

/**
 * itemType 不确定，ListView实现列表
 */
public class MutilTypeListViewActivity extends AppCompatActivity {
    private ListView lv;
    private List<String> data;
    private SparseArray<Integer> layoutIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mutil_type_list_view);
        setTitle("itemType 不确定，ListView实现列表");
        lv = (ListView) findViewById(R.id.listView1);
        getData();
        lv.setAdapter(new SuperListViewAdapter<String>(new MyCalcuteViewType(), data, layoutIds) {
            @Override
            public void convert(SuperListViewHolder svh, int position, int viewType, String itemData) {
                switch (viewType) {
                    case 0:
                        svh.setText(R.id.tv1, itemData);
                        break;
                    case 1:
                        svh.setText(R.id.tv2, itemData);
                        break;
                }
            }
        });
    }

    /**
     * put的key就是ViewType的值，必须从0开始的整数。
     */
    private void getData() {
        data = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            data.add(i + "");
        }
        layoutIds = new SparseArray<>();
        layoutIds.put(0, R.layout.mutil_type_list_item1);
        layoutIds.put(1, R.layout.mutil_type_list_item2);
    }

    class MyCalcuteViewType implements SuperListViewAdapter.CalculateViewType {

        @Override
        public int getViewType(int postion) {
            if (postion % 2 == 0) {
                return 0;
            } else {
                return 1;
            }
        }
    }
}
