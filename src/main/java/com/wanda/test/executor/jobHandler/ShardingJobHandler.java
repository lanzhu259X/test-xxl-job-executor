package com.wanda.test.executor.jobHandler;

import com.alibaba.fastjson.JSON;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxl.job.core.util.ShardingUtil;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@JobHander(value = "ShardingJobHandler")
@Service
public class ShardingJobHandler extends IJobHandler {

    @Override
    public ReturnT<String> execute(String... strings) throws Exception {

        ShardingUtil.ShardingVO shardingVO = ShardingUtil.getShardingVo();

        XxlJobLogger.log("Start Sharding Job Handler by params:{0}, shardingVO:{1}",
                JSON.toJSONString(strings), JSON.toJSONString(shardingVO));

        int i = 5;
        int result = 0;
        while (i > 0) {
            result = RandomUtils.nextInt(0, 10);
            XxlJobLogger.log("Shardding index:{0} Do Sharding Job Work. i:{1}, result:{2}",shardingVO.getIndex(), i, result);
            TimeUnit.SECONDS.sleep(1);
            i--;
        }

        return result <= 6 ? ReturnT.SUCCESS :
                new ReturnT<>(ReturnT.FAIL_CODE, "分片:" + shardingVO.getIndex() + "执行失败");
    }
}
