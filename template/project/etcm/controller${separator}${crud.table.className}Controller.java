package @{crud.properties.base_package}.@{crud.properties.model}.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.kmob.etcm.common.entity.ResultInfo;
import @{crud.properties.base_package}.@{crud.properties.model}.entity.@{crud.table.className};
import com.kmob.etcm.common.util.PageFactory;
import @{crud.properties.base_package}.@{crud.properties.model}.service.@{crud.table.className}Service;

/**
 * @{crud.table.remarks}: 后端controller类
 * 
 * 
 * @author generator
 */
@RestController
@RequestMapping(value = "/@{strutils.toLowerCaseFirst(crud.table.className)}")
public class @{crud.table.className}Controller {
    
    
    @Autowired
    private @{crud.table.className}Service @{strutils.toLowerCaseFirst(crud.table.className)}ServiceImpl;
    
    
    /**
     * 新增@{crud.table.remarks}
     * 
     * @param params
     */
    @PostMapping("add")
    @ResponseBody
    public ResultInfo add(@RequestBody JSONObject params){
        return @{strutils.toLowerCaseFirst(crud.table.className)}ServiceImpl.add(params);
    }
    
    
    /**
     * 查询@{crud.table.remarks}
     * 
     * @param params
     */
    @PostMapping("query")
    @ResponseBody
    public ResultInfo query(@RequestBody JSONObject params){
        ResultInfo resultInfo = new ResultInfo(0, "success");
        @{crud.table.className} paramEntity = params.toJavaObject(@{crud.table.className}.class);
        
        @{crud.table.className} @{strutils.toLowerCaseFirst(crud.table.className)} = @{strutils.toLowerCaseFirst(crud.table.className)}ServiceImpl.query(paramEntity);
        
        resultInfo.setData(@{strutils.toLowerCaseFirst(crud.table.className)});
        return resultInfo;
    }
    
    
    /**
     * 分页查询@{crud.table.remarks}
     * 
     * @param params
     */
    @PostMapping("queryPage")
    @ResponseBody
    public ResultInfo queryPage(@RequestBody JSONObject params){
        ResultInfo resultInfo = new ResultInfo(0, "success");
        Page<@{crud.table.className}> page = new PageFactory<@{crud.table.className}>().defaultPage(params);
        
        @{crud.table.className} @{strutils.toLowerCaseFirst(crud.table.className)} = JSONObject.parseObject(params.toJSONString(),@{crud.table.className}.class);
        
        
        resultInfo =  @{strutils.toLowerCaseFirst(crud.table.className)}ServiceImpl.queryPage(page,@{strutils.toLowerCaseFirst(crud.table.className)});
        return resultInfo;
    }

    
    /**
     * 更新@{crud.table.remarks}
     * 
     * @param params
     */
    @PostMapping("update")
    @ResponseBody
    public ResultInfo update(@RequestBody JSONObject params){
        @{crud.table.className} @{strutils.toLowerCaseFirst(crud.table.className)} = params.toJavaObject(@{crud.table.className}.class);
        ResultInfo resultInfo = @{strutils.toLowerCaseFirst(crud.table.className)}ServiceImpl.update(@{strutils.toLowerCaseFirst(crud.table.className)});
        return resultInfo;
    }
    
    
    /**
     * 删除@{crud.table.remarks}
     * 
     * @param params
     */
    @PostMapping("delete")
    @ResponseBody
    public ResultInfo delete(@RequestBody JSONObject params){
        ResultInfo resultInfo = @{strutils.toLowerCaseFirst(crud.table.className)}ServiceImpl.delete(params);
        return resultInfo;
    }
    
    
}