package com.chen.part_time.service.impl;

import com.chen.part_time.dao.ISensitiveWorkDao;
import com.chen.part_time.entity.SensitiveWork;
import com.chen.part_time.service.ISensitiveWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SensitiveWorkServiceImpl
 * @Author ChenYicheng
 * @Description 敏感词-Service-impl
 * @Date 2021/4/15 15:30
 */
@Service
public class SensitiveWorkServiceImpl implements ISensitiveWorkService {

    @Autowired
    private ISensitiveWorkDao sensitiveWorkDao;

    @Override
    public boolean addSensitiveWork(SensitiveWork sensitiveWork) {
        return sensitiveWorkDao.addSensitiveWork(sensitiveWork);
    }

    @Override
    public int addSensitiveWorkByLists(List<SensitiveWork> sensitiveWorkList) {
        return sensitiveWorkDao.addSensitiveWorkByLists(sensitiveWorkList);
    }

    @Override
    public List<SensitiveWork> getList(SensitiveWork sensitiveWork) {
        return sensitiveWorkDao.getList(sensitiveWork);
    }

    @Override
    public Integer getCountByWork(String work) {
        return sensitiveWorkDao.getCountByWork(work);
    }


    @Override
    public boolean deleteWork(Long id) {
        return sensitiveWorkDao.deleteWork(id);
    }
}
