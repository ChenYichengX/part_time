package com.chen.part_time.service;

import com.chen.part_time.entity.Ad;

import java.util.List;

/**
 * Create by ChenYicheng
 * 2020/12/30 15:14
 */
public interface IAdService {

    /**
     * 通过广告位 code 查询广告
     * @param code
     * @return
     */
    List<Ad> getAdsByCode(String code);

    /**
     * 通过广告 id 查询广告
     * @param id
     * @return
     */
    Ad getAdById(Long id);

    /**
     * 添加广告Ad
     * @param ad
     * @return
     */
    boolean insertAd(Ad ad);

    /**
     * 删除广告通过 id
     * @param id
     * @return
     */
    boolean deleteAdById(Long id);
}
