package com.chen.part_time.dao;

import com.chen.part_time.entity.Ad;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Create by ChenYicheng
 * 2020/12/30 14:54
 */
@Mapper
public interface IAdDao {

    /**
     * 通过广告位 code 查询广告
     * @param code
     * @return
     */
    List<Ad> getAdsByCode(String code);

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
