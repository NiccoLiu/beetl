package @{crud.properties.base_package}.apigateway.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONObject;
import com.kmob.etcm.apigateway.service.@{crud.table.className}Client;
import com.kmob.etcm.common.entity.ResultInfo;



/**
 * @{crud.table.remarks} api-gateway controller
 * @author generator
 * @date @{crud.timestamp}
 * @since 1.0
 */
@Controller
@RequestMapping(value = "/api/@{crud.properties.model}/@{strutils.toLowerCaseFirst(crud.table.className)}")
public class @{crud.table.className}ApiController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(@{crud.table.className}ApiController.class);
    
    
    @Autowired
    @{crud.table.className}Client @{strutils.toLowerCaseFirst(crud.table.className)}Client;
    
    /**
     * 新增
     */
    @PostMapping(value = "/add")
    public @ResponseBody ResultInfo add(@RequestBody JSONObject params) {
        LOGGER.debug("add @{crud.table.className} is {}", params.toString());
        ResultInfo resultInfo = @{strutils.toLowerCaseFirst(crud.table.className)}Client.add(params);
        return resultInfo;
    }
    
    
    /**
     * 查询
     */
    @PostMapping(value = "/query")
    public @ResponseBody ResultInfo query(@RequestBody JSONObject params) {
        LOGGER.debug("query @{crud.table.className} by id is {}", params.toString());
        ResultInfo resultInfo = @{strutils.toLowerCaseFirst(crud.table.className)}Client.query(params);
        return resultInfo;
    }
    
    
    /**
     * 分页查询
     */
    @PostMapping(value = "/queryPage")
    public @ResponseBody ResultInfo queryPage(@RequestBody JSONObject params) {
        LOGGER.debug("queryPage @{crud.table.className} is {}", params.toString());
        ResultInfo resultInfo = @{strutils.toLowerCaseFirst(crud.table.className)}Client.queryPage(params);
        return resultInfo;
    }
    
    
    /**
     * 更新
     */
    @PostMapping(value = "/update")
    public @ResponseBody ResultInfo update(@RequestBody JSONObject params) {
        LOGGER.debug("update @{crud.table.className},params is {}", params.toString());
        ResultInfo resultInfo = @{strutils.toLowerCaseFirst(crud.table.className)}Client.update(params);
        return resultInfo;
    }
    
    
    /**
     * 删除
     */
    @PostMapping(value = "/delete")
    public @ResponseBody ResultInfo delete(@RequestBody Long id) {
        LOGGER.debug("delete @{crud.table.className},id is {}", id);
        ResultInfo resultInfo = @{strutils.toLowerCaseFirst(crud.table.className)}Client.delete(id);
        return resultInfo;
    }
    
}