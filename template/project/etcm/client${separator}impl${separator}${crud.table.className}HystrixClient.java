package @{crud.properties.base_package}.apigateway.client.impl;


import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.kmob.etcm.apigateway.service.@{crud.table.className}Client;
import com.kmob.etcm.common.entity.ResultInfo;

/**
 * 
 * @{crud.table.remarks} HystrixClient 
 * @author generator
 */
@Component
public class @{crud.table.className}HystrixClient implements @{crud.table.className}Client {
    @Override
    public ResultInfo add(JSONObject params){
        return ResultInfo.RPC_ERROR;
    }

    @Override
    public ResultInfo query(JSONObject params){
        return ResultInfo.RPC_ERROR;
    }

    @Override
    public ResultInfo queryPage(JSONObject params){
        return ResultInfo.RPC_ERROR;
    }

    @Override
    public ResultInfo update(JSONObject params){
        return ResultInfo.RPC_ERROR;
    }
    
    
    @Override
    public ResultInfo delete(JSONObject params){
        return ResultInfo.RPC_ERROR;
    }
}