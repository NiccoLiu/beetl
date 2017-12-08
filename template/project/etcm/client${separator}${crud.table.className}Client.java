package @{crud.properties.base_package}.apigateway.client;


import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.alibaba.fastjson.JSONObject;
import com.kmob.etcm.apigateway.service.impl.@{crud.table.className}HystrixClient;
import com.kmob.etcm.common.entity.ResultInfo;

/**
 * @{crud.table.remarks} springcloud client 
 * 
 * @author generator
 */
@FeignClient(value = "@{crud.properties.microservice_name}", fallback = @{crud.table.className}HystrixClient.class)
public interface @{crud.table.className}Client {
    
    @LoadBalanced
    @PostMapping(value = "/@{crud.properties.model}/@{strutils.toLowerCaseFirst(crud.table.className)}/add")
    public ResultInfo add(JSONObject params);

    @LoadBalanced
    @PostMapping(value = "/@{crud.properties.model}/@{strutils.toLowerCaseFirst(crud.table.className)}/query")
    public ResultInfo query(JSONObject params);

    @LoadBalanced
    @PostMapping(value = "/@{crud.properties.model}/@{strutils.toLowerCaseFirst(crud.table.className)}/queryPage")
    public ResultInfo queryPage(JSONObject params);

    @LoadBalanced
    @PostMapping(value = "/@{crud.properties.model}/@{strutils.toLowerCaseFirst(crud.table.className)}/update")
    public ResultInfo update(JSONObject params);
    
    
    @LoadBalanced
    @PostMapping(value = "/@{crud.properties.model}/@{strutils.toLowerCaseFirst(crud.table.className)}/delete")
    public ResultInfo delete(JSONObject params);
    
}