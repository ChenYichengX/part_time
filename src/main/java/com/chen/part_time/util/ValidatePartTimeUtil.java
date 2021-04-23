package com.chen.part_time.util;

import com.chen.part_time.dao.ISensitiveWorkDao;
import com.chen.part_time.entity.PartTime;
import com.chen.part_time.entity.SensitiveWork;
import com.chen.part_time.service.ISensitiveWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @ClassName ValidatePartTimeUtil
 * @Author ChenYicheng
 * @Description 验证兼职信息-敏感词过滤
 * @Date 2021/4/16 16:20
 */
@Component
public class ValidatePartTimeUtil {

    @Autowired
    private ISensitiveWorkService sensitiveWork;
    private static ISensitiveWorkService sensitiveWorkService;

    @PostConstruct
    public void init() {
        sensitiveWorkService = sensitiveWork;
    }

    /**
     * @Author ChenYicheng
     * @Description 审核兼职信息
     * @Date 2021/4/16 16:48
     */
    public static String validatePartTime(PartTime partTime) {
        // 0.获取所有敏感词
        List<SensitiveWork> list = sensitiveWorkService.getList(null);
        String title = partTime.getTitle() != null ? partTime.getTitle() : "";
        String content = partTime.getContent() != null ? partTime.getContent() : "";
        String require_text = partTime.getRequire_text() != null ? partTime.getRequire_text() : "";
        String treatment = partTime.getTreatment() != null ? partTime.getTreatment() : "";
        for (SensitiveWork sensitiveWork : list) {
            String work = sensitiveWork.getWork();
            // 1.比对标题
            if (title.indexOf(work) != -1) {
                return "标题包含敏感词";
            }
            // 2.比对内容
            if (content.indexOf(work)!= -1) {
                return "内容包含敏感词";
            }
            // 3.比对要求
            if (require_text.indexOf(work) != -1) {
                return "要求包含敏感词";
            }
            // 4.比对待遇
            if (treatment.indexOf(work) != -1) {
                return "待遇包含敏感词";
            }
        }
        // 5.比对图片 ---目前功力,做不到
        return "审核通过";
    }
}
