package com.chen.part_time;

import com.chen.part_time.entity.PartTime;
import com.chen.part_time.util.ValidatePartTimeUtil;
import com.chen.part_time.web.merchant.PartTimeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PartTimeApplicationTests {

    @Autowired
    private PartTimeController partTimeController;

    @Test
    void contextLoads() {

    }

    /**
     * @Author ChenYicheng
     * @Description 测试用例-标题包含敏感词
     * @Date 2021/4/23 16:58
     */
    @Test
    void testSensitiveWorkTitle(){
        PartTime partTime = new PartTime();
        partTime.setTitle("fuckggok");
        partTime.setContent("xxxxxxxxxxxxxxxx");
        String s = ValidatePartTimeUtil.validatePartTime(partTime);
        System.out.println(s);
    }


    /**
     * @Author ChenYicheng
     * @Description 测试用例-兼职内容包含敏感词
     * @Date 2021/4/23 16:58
     */
    @Test
    void testSensitiveWorkContent(){
        PartTime partTime = new PartTime();
        partTime.setTitle("fucskggok");
        partTime.setContent("xxxxxfuckxxxxxxxx");
        String s = ValidatePartTimeUtil.validatePartTime(partTime);
        System.out.println(s);
    }


    /**
     * @Author ChenYicheng
     * @Description 测试用例-所有内容都不包含函敏感词
     * @Date 2021/4/23 17:00
     */
    @Test
    void testSensitiveWork(){
        PartTime partTime = new PartTime();
        partTime.setTitle("fucskggok");
        partTime.setContent("xxxxxfussckxxxxxxxx");
        partTime.setRequire_text("努力认真");
        partTime.setTreatment("待遇");
        String s = ValidatePartTimeUtil.validatePartTime(partTime);
        System.out.println(s);
    }


    /**
     * @Author ChenYicheng
     * @Description 测试-删除不是自己发布的兼职信息
     * @Date 2021/4/23 17:05
     */
    @Test
    void testDeletePartTime(){
        partTimeController.deleteFile();
    }


}
