package com.chen.part_time.service;

import com.chen.part_time.entity.SensitiveWork;

import java.util.List;

/**
 * @ClassName ISensitiveWorkService
 * @Author ChenYicheng
 * @Description 敏感词管理-Service
 * @Date 2021/4/15 15:24
 */
public interface ISensitiveWorkService {
    
    /**
     * @Author ChenYicheng
     * @Description 添加敏感词
     * @Date 2021/4/15 15:26
     */
    boolean addSensitiveWork(SensitiveWork sensitiveWork);


    /**
     * @Author ChenYicheng
     * @Description 批量插入
     * @Date 2021/4/24 16:54
     */
    int addSensitiveWorkByLists(List<SensitiveWork> sensitiveWorkList);
    
    /**
     * @Author ChenYicheng
     * @Description 查找敏感词
     * @Date 2021/4/15 15:32
     */
    List<SensitiveWork> getList(SensitiveWork sensitiveWork);


    /**
     * @Author ChenYicheng
     * @Description 判断是否有重复
     * @Date 2021/4/15 15:32
     */
    Integer getCountByWork(String work);


    /**
     * @Author ChenYicheng
     * @Description 删除敏感词
     * @Date 2021/4/16 11:54
     */
    boolean deleteWork(Long id);
    
}
