package @{crud.properties.base_package}.apigateway.service.impl;


import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.kmob.etcm.apigateway.service.@{crud.table.className}Client;
import com.kmob.etcm.common.entity.ResultInfo;
/**
 * @{crud.table.remarks} HystrixClient 
 * @author generator
 * @date @{crud.timestamp}
 * @since 1.0
 */
@Component
public class @{crud.table.className}HystrixClient implements @{crud.table.className}Client {
    @Override
    public ResultInfo add(JSONObject jSONObject){
        ResultInfo rs = new ResultInfo(-1, "调用微服务出错！");
        return rs;
    }

    @Override
    public ResultInfo query(JSONObject jSONObject){
        ResultInfo rs = new ResultInfo(-1, "调用微服务出错！");
        return rs;
    }

    @Override
    public ResultInfo queryPage(JSONObject jSONObject){
        ResultInfo rs = new ResultInfo(-1, "调用微服务出错！");
        return rs;
    }

    @Override
    public ResultInfo update(JSONObject jSONObject){
        ResultInfo rs = new ResultInfo(-1, "调用微服务出错！");
        return rs;
    }
    
    
    @Override
    public ResultInfo delete(Long id){
        ResultInfo rs = new ResultInfo(-1, "调用微服务出错！");
        return rs;
    }
}