package com.chen.part_time.service;

import com.chen.part_time.entity.BrowsingHistory;
import com.github.pagehelper.PageInfo;


/**
 * Create by ChenYicheng
 * 2021/2/23 11:52
 */
public interface IBrowsingHistoryService {

    /**
     * 添加浏览记录
     * @param browsingHistory
     * @return
     */
    boolean addBrowsingHistory(BrowsingHistory browsingHistory);

    /**
     * 查询浏览记录
     * @return
     */
    PageInfo<BrowsingHistory> getAll(int pageNumber,int pageSize);
}
