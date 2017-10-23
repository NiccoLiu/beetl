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
import com.kmob.etcm.user.entity.PayAccount;
import com.kmob.etcm.user.util.PageFactory;
import @{crud.properties.base_package}.@{crud.properties.model}.service.@{crud.table.className}Service;

/**
 * @{crud.table.remarks}: 后端controller类
 * @author generator
 * @date @{crud.timestamp}
 * @since 1.0
 */
@RestController
@RequestMapping(value = "/@{crud.properties.model}/@{strutils.toLowerCaseFirst(crud.table.className)}")
public class @{crud.table.className}Controller {
    
    
    @Autowired
    private @{crud.table.className}Service @{strutils.toLowerCaseFirst(crud.table.className)}ServiceImpl;
    
    
    @PostMapping("add")
    @ResponseBody
    public ResultInfo add(@RequestBody JSONObject paramJson){
        return @{strutils.toLowerCaseFirst(crud.table.className)}ServiceImpl.add(paramJson);
    }
    
    @PostMapping("query")
    @ResponseBody
    public ResultInfo query(@RequestBody JSONObject paramJson){
        ResultInfo resultInfo = new ResultInfo(0, "success");
        @{crud.table.className} paramEntity = paramJson.toJavaObject(@{crud.table.className}.class);
        
        @{crud.table.className} @{strutils.toLowerCaseFirst(crud.table.className)} = @{strutils.toLowerCaseFirst(crud.table.className)}ServiceImpl.query(paramEntity);
        
        resultInfo.setData(@{strutils.toLowerCaseFirst(crud.table.className)});
        return resultInfo;
    }
    
    @PostMapping("queryPage")
    @ResponseBody
    public ResultInfo queryPage(@RequestBody JSONObject paramJson){
        ResultInfo resultInfo = new ResultInfo(0, "success");
        Page<@{crud.table.className}> page = new PageFactory<@{crud.table.className}>().defaultPage(paramJson);
        
        @{crud.table.className} @{strutils.toLowerCaseFirst(crud.table.className)} = JSONObject.parseObject(paramJson.toJSONString(),@{crud.table.className}.class);
        
        
        resultInfo =  @{strutils.toLowerCaseFirst(crud.table.className)}ServiceImpl.queryPage(page,@{strutils.toLowerCaseFirst(crud.table.className)});
        return resultInfo;
    }

    @PostMapping("update")
    @ResponseBody
    public ResultInfo update(@RequestBody JSONObject params){
        @{crud.table.className} @{strutils.toLowerCaseFirst(crud.table.className)} = params.toJavaObject(@{crud.table.className}.class);
        ResultInfo resultInfo = @{strutils.toLowerCaseFirst(crud.table.className)}ServiceImpl.update(@{strutils.toLowerCaseFirst(crud.table.className)});
        return resultInfo;
    }
    
    @PostMapping("delete")
    @ResponseBody
    public ResultInfo delete(@RequestBody Long id){
        ResultInfo resultInfo = @{strutils.toLowerCaseFirst(crud.table.className)}ServiceImpl.delete(id);
        return resultInfo;
    }
    
    
}