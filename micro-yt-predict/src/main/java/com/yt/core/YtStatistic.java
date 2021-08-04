package com.yt.core;

import com.yt.entity.Pack;

import java.util.List;

public interface YtStatistic {

    /**
     * 初始化距离矩阵等
     */
    void initialize();

    /**
     * 将包裹数据进行统计
     * @param packs
     */
    void compute(List<Pack> packs);

    /**
     * 获取对应网点的预测量
     * @param code
     * @return
     */
    List<Integer> getCount(String code);

    /**
     * 刷新掉过期数据
     */
    void refresh();

    boolean getSk();

    void trans();
}
