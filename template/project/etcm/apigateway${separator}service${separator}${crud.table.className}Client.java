package @{crud.properties.base_package}.apigateway.service;


import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.alibaba.fastjson.JSONObject;
import com.kmob.etcm.apigateway.service.impl.@{crud.table.className}HystrixClient;
import com.kmob.etcm.common.entity.ResultInfo;

/**
 * @{crud.table.remarks} springcloud client 
 * @author generator
 * @date @{crud.timestamp}
 * @since 1.0
 */
@FeignClient(value = "@{crud.properties.microservice_name}", fallback = @{crud.table.className}HystrixClient.class)
public interface @{crud.table.className}Client {
    
    @LoadBalanced
    @PostMapping(value = "/@{crud.properties.model}/@{strutils.toLowerCaseFirst(crud.table.className)}/add")
    public ResultInfo add(JSONObject jSONObject);

    @LoadBalanced
    @PostMapping(value = "/@{crud.properties.model}/@{strutils.toLowerCaseFirst(crud.table.className)}/queryById")
    public ResultInfo query(JSONObject jSONObject);

    @LoadBalanced
    @PostMapping(value = "/@{crud.properties.model}/@{strutils.toLowerCaseFirst(crud.table.className)}/queryPage")
    public ResultInfo queryPage(JSONObject jSONObject);

    @LoadBalanced
    @PostMapping(value = "/@{crud.properties.model}/@{strutils.toLowerCaseFirst(crud.table.className)}/update")
    public ResultInfo update(JSONObject jSONObject);
    
    
    @LoadBalanced
    @PostMapping(value = "/@{crud.properties.model}/@{strutils.toLowerCaseFirst(crud.table.className)}/delete")
    public ResultInfo delete(Long id);
    
}