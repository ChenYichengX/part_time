package com.chen.part_time.dao;

import com.chen.part_time.entity.Type;
import com.chen.part_time.vo.TypeVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 陈奕成
 * @create 2020 11 19 16:21
 */
@Mapper
public interface ITypeDao {

    /**
     * 新增类型
     * @return
     */
    boolean saveType(Type type);

    /**
     * 查询
     * @param id
     * @return
     */
    Type getType(Long id);

    /**
     * 查询
     * @param name
     * @return
     */
    Type getTypeByName(String name);

    /**
     * 查询所有
     * @return
     */
    List<TypeVo> getAllType();

    /**
     * 修改
     * @param type
     * @return
     */
    boolean updateType(Type type);

    /**
     * 删除
     * @param id
     * @return
     */
    boolean deleteType(Long id);

}
