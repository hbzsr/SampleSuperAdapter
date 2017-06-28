package zsr.samplesuperadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.List;

import zsr.samplesuperadapter.recyclerview.SuperRecyclerViewAdapter;
import zsr.samplesuperadapter.recyclerview.SuperRecyclerViewHolder;

/**
 * itemType 不确定，recyclerView实现列表
 */
public class MutilTypeRecyclerViewActivity extends AppCompatActivity {
    private RecyclerView rv2;
    private SparseArray<Integer> laoutids;
    private List<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mutil_type_recycler_view);
        setTitle("itemType 不确定，recyclerView实现列表");
        getData();
        rv2 = (RecyclerView) findViewById(R.id.rv2);
        rv2.setLayoutManager(new LinearLayoutManager(this));
        rv2.setItemAnimator(new DefaultItemAnimator());
        rv2.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rv2.setAdapter(new SuperRecyclerViewAdapter<String>(laoutids, data, new MyCalculateViewType()) {
            @Override
            public void convert(SuperRecyclerViewHolder holder, String itemData, int position, int viewType) {
                switch (viewType) {
                    case 0:
                        holder.setText(R.id.tv1, itemData);
                        break;
                    case 1:
                        holder.setText(R.id.tv2, itemData);
                        break;
                }
            }

            @Override
            public void onBindViewHolder(SuperRecyclerViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
            }
        });
    }

    class MyCalculateViewType implements SuperRecyclerViewAdapter.CalculateViewType {

        @Override
        public int getViewType(int postion) {
            if (postion % 7 == 0)
                return 0;
            else
                return 1;
        }
    }

    private void getData() {
        laoutids = new SparseArray<>();
        laoutids.put(0, R.layout.mutil_type_list_item1);
        laoutids.put(1, R.layout.mutil_type_list_item2);
        data = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            data.add(i + "");
        }
    }
}
