package com.chen.part_time.service.impl;

import com.chen.part_time.dao.IPaymentTypeDao;
import com.chen.part_time.entity.PaymentType;
import com.chen.part_time.service.IPaymentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 陈奕成
 * @create 2020 11 20 21:44
 */
@Service
public class PaymentTypeServiceImpl implements IPaymentTypeService {
    @Autowired
    private IPaymentTypeDao paymentTypeDao;

    @Override
    public boolean savePaymentType(PaymentType paymentType) {
        return paymentTypeDao.savePaymentType(paymentType);
    }

    @Override
    public PaymentType getPaymentType(Long id) {
        return paymentTypeDao.getPaymentType(id);
    }

    @Override
    public PaymentType getPaymentTypeByName(String name) {
        return paymentTypeDao.getPaymentTypeByName(name);
    }

    @Override
    public List<PaymentType> getAllPaymentType() {
        return paymentTypeDao.getAllPaymentType();
    }

    @Override
    public boolean updatePaymentType(PaymentType paymentType) {
        return paymentTypeDao.updatePaymentType(paymentType);
    }

    @Override
    public boolean deletePaymentType(Long id) {
        return paymentTypeDao.deletePaymentType(id);
    }
}
