package zsr.samplesuperadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import zsr.samplesuperadapter.recyclerview.SuperRecyclerViewAdapter;
import zsr.samplesuperadapter.recyclerview.SuperRecyclerViewHolder;

/**
 * recyclerView实现列表，itemtype一致
 */
public class SimpleTypeRecyclerViewActivity extends AppCompatActivity {
    private RecyclerView rv;
    private List<String> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_type_recycler_view);
        setTitle("recyclerView实现列表，itemtype一致");
        getData();
        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rv.setAdapter(new SuperRecyclerViewAdapter<String>(android.R.layout.simple_list_item_1,data) {
            @Override
            public void convert(SuperRecyclerViewHolder holder, String itemData, int position, int viewType) {
                holder.setText(android.R.id.text1,itemData);
            }
        });
    }

    private void getData() {
        data = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            data.add(i + "");
        }
    }
}
