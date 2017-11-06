package com.wanda.test.executor.jobHandler;

import com.alibaba.fastjson.JSON;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;
import com.xxl.job.core.log.XxlJobLogger;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@JobHander(value = "SecondJobHandler")
@Service
public class SecondJobHandler extends IJobHandler {

    @Override
    public ReturnT<String> execute(String... strings) throws Exception {
        int result = doWork(strings);
        if (result == 0) {
            return new ReturnT<>("SecondJobHandler Job SUCCESS");
        }else {
            XxlJobLogger.log("SecondJobHandler Job result is Fail.");
            return new ReturnT<>(ReturnT.FAIL_CODE, "Simple Job FAIL");
        }
    }

    private int doWork(String[] params) throws Exception {
        XxlJobLogger.log("start Second Job Handler by params:{0}", JSON.toJSONString(params));

        int i = 5;
        int result = 0;
        while (i > 0) {
            result = RandomUtils.nextInt(0, 10);
            XxlJobLogger.log("Do Second Job Work. i:{0}, result:{1}", i, result);
            TimeUnit.SECONDS.sleep(1);
            i--;
        }
        XxlJobLogger.log("do Second Job Work result:{0} if <= 6 then return success else return fail.", result);
        return result <= 6 ? 0 : 1;
    }

}
