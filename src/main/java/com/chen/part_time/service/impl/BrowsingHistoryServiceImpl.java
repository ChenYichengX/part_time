package com.chen.part_time.service.impl;

import com.chen.part_time.dao.IBrowsingHistoryDao;
import com.chen.part_time.entity.BrowsingHistory;
import com.chen.part_time.service.IBrowsingHistoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by ChenYicheng
 * 2021/2/23 11:53
 */
@Service
public class BrowsingHistoryServiceImpl implements IBrowsingHistoryService {

    @Autowired
    private IBrowsingHistoryDao browsingHistoryDao;

    @Override
    public boolean addBrowsingHistory(BrowsingHistory browsingHistory) {
        return browsingHistoryDao.addBrowsingHistory(browsingHistory);
    }

    @Override
    public PageInfo<BrowsingHistory> getAll(int pageNumber,int pageSize) {
        PageHelper.startPage(pageNumber,pageSize);
        List<BrowsingHistory> all = browsingHistoryDao.getAll();
        PageInfo<BrowsingHistory> info = new PageInfo<>(all,5);
        return info;
    }
}
