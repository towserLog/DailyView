package io.paizi.supportview.widget.recycle;

/**
 * Created by pai on 2017/1/23.
 * item交换
 * item删除
 */

public interface ItemDragListener {

    /**
     * 拖拽交换
     * @param formIndex 拖动的索引
     * @param toIndex  交换条目的索引
     */
    void onSwap(int formIndex, int toIndex);

    /**
     *侧滑删除
     * @param index 删除位置的索引
     */
    void onRemove(int index);
}
