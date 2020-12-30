package com.chen.part_time.service;

import com.chen.part_time.entity.PaymentType;

import java.util.List;

/**
 * @author 陈奕成
 * @create 2020 11 20 21:44
 */
public interface IPaymentTypeService {

    /**
     * 新增类型
     * @return
     */
    boolean savePaymentType(PaymentType paymentType);

    /**
     * 查询
     * @param id
     * @return
     */
    PaymentType getPaymentType(Long id);

    /**
     * 查询
     * @param name
     * @return
     */
    PaymentType getPaymentTypeByName(String name);

    /**
     * 查询所有
     * @return
     */
    List<PaymentType> getAllPaymentType();

    /**
     * 修改
     * @param paymentType
     * @return
     */
    boolean updatePaymentType(PaymentType paymentType);

    /**
     * 删除
     * @param id
     * @return
     */
    boolean deletePaymentType(Long id);
}
