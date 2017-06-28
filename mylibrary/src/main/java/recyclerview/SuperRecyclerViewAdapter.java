package recyclerview;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * Created by zsr on 2017/6/1.
 */

public abstract class SuperRecyclerViewAdapter<T> extends RecyclerView.Adapter<SuperRecyclerViewHolder> {
    private SparseArray<Integer> layoutIds;
    private List<T> data;
    private CalculateViewType calculateViewType;
    private int flag = 0;

    public SuperRecyclerViewAdapter(int layoutId, List<T> data) {
        this.data = data;
        this.layoutIds = new SparseArray<>();
        layoutIds.put(0, layoutId);
    }

    public SuperRecyclerViewAdapter(SparseArray<Integer> layoutIds, List<T> data, CalculateViewType calculateViewType) {
        this.layoutIds = layoutIds;
        this.data = data;
        this.calculateViewType = calculateViewType;
    }

    @Override
    public SuperRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e("message", "onCreateViewHolder : " + (++flag));
        int layoutId = layoutIds.get(viewType);
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        SuperRecyclerViewHolder vh = new SuperRecyclerViewHolder(view);
        return vh;
    }

    @Override
    public int getItemViewType(int position) {
        return calculateViewType == null ? 0 : calculateViewType.getViewType(position);
    }

    @Override
    public void onBindViewHolder(SuperRecyclerViewHolder holder, int position) {
//        Log.i("message", "onBindViewHolder");
        T itemData = data.get(position);
        convert(holder, itemData, position, getItemViewType(position));
    }

    public abstract void convert(SuperRecyclerViewHolder holder, T itemData, int position, int viewType);

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public interface CalculateViewType {
        int getViewType(int postion);
    }


}
