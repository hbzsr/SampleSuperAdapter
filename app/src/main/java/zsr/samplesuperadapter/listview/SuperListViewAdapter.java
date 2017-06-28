package zsr.samplesuperadapter.listview;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by zsr on 2017/5/27.
 */

public abstract class SuperListViewAdapter<T> extends BaseAdapter {
    private CalculateViewType calculateViewType;
    private List<T> data;
    private SparseArray<Integer> layoutIds;

    /**
     * 当有多种类型的item时使用此构造方法。
     * @param calculateViewType 回调获取item Type的类型接口。
     * @param data 数据
     * @param layoutIds item的layout集合。
     */
    public SuperListViewAdapter(CalculateViewType calculateViewType, List<T> data, SparseArray<Integer> layoutIds) {
        this.calculateViewType = calculateViewType;
        this.layoutIds = layoutIds;
        this.data = data;
    }

    /**
     * 默认一种item类型时使用此构造方法
     * @param data
     * @param layoutId
     */
    public SuperListViewAdapter(List<T> data, int layoutId) {
        this.data = data;
        this.layoutIds = new SparseArray<>();
        layoutIds.put(0,layoutId);
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public int getViewTypeCount() {
        return calculateViewType == null ? 1: layoutIds.size();
    }

    @Override
    public int getItemViewType(int position) {
        return calculateViewType == null ? 0 : calculateViewType.getViewType(position);
    }

    @Override
    public T getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int viewType = getItemViewType(position);
        int layoutId = layoutIds.get(viewType);
        SuperListViewHolder svh = SuperListViewHolder.getInstance(convertView, parent, layoutId, viewType);
        convert(svh, position, viewType, getItem(position));
        return svh.getConvertView();
    }

    /**
     * 根据不同viewType去设置不同view的id
     *
     * @param svh
     * @param position
     * @param viewType
     */
    public abstract void convert(SuperListViewHolder svh, int position, int viewType, T itemData);

    public interface CalculateViewType {
        int getViewType(int postion);
    }
}
