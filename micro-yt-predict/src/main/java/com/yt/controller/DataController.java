package com.yt.controller;

import com.yt.core.YtStatistic;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/yt")
public class DataController {

    @Resource(name = "ytStatistic")
    private YtStatistic ytStatistic;

    /**
     * 输入代号查询网点 3个预测件数
     * 测试使用100
     * @param code
     * @return
     */
    @GetMapping("/getCount/{code}")
    public List<Long> getCount(@PathVariable String code){
        return ytStatistic.getCount(code);
    }

    /**
     * 打开或关闭模拟快递信息导入
     * @return
     */
    @GetMapping("/close")
    public String getCount(){
        ytStatistic.trans();
        return "OK";
    }
}
