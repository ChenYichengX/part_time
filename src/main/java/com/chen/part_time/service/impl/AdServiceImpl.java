package com.chen.part_time.service.impl;

import com.chen.part_time.dao.IAdDao;
import com.chen.part_time.entity.Ad;
import com.chen.part_time.service.IAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by ChenYicheng
 * 2020/12/30 15:15
 */
@Service
public class AdServiceImpl implements IAdService {

    @Autowired
    private IAdDao adDao;

    @Override
    public List<Ad> getAdsByCode(String code) {
        return adDao.getAdsByCode(code);
    }

    @Override
    public Ad getAdById(Long id) {
        return adDao.getAdById(id);
    }

    @Override
    public boolean insertAd(Ad ad) {
        return adDao.insertAd(ad);
    }

    @Override
    public boolean updateAd(Ad ad) {
        return adDao.updateAd(ad);
    }

    @Override
    public boolean deleteAdById(Long id) {
        return adDao.deleteAdById(id);
    }
}
