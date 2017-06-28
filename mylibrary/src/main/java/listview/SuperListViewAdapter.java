package listview;

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
     * use this method for mutil item type
     * @param calculateViewType is a interface ,get item view type
     * @param data
     * @param layoutIds item's layouts
     */
    public SuperListViewAdapter(CalculateViewType calculateViewType, List<T> data, SparseArray<Integer> layoutIds) {
        this.calculateViewType = calculateViewType;
        this.layoutIds = layoutIds;
        this.data = data;
    }

    /**
     * single item type
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
     * According to the different viewType to set up the view id
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
