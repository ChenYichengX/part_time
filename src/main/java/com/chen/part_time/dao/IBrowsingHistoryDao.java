package com.chen.part_time.dao;

import com.chen.part_time.entity.BrowsingHistory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Create by ChenYicheng
 * 2021/2/23 11:20
 */
@Mapper
public interface IBrowsingHistoryDao {

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
    List<BrowsingHistory> getAll();
}
