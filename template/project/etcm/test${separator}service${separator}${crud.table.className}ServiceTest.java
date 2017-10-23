package @{crud.properties.base_package}.@{crud.properties.model}.test.service;



import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import @{crud.properties.base_package}.@{crud.properties.model}.test.BaseJunit;
import com.kmob.etcm.common.entity.ResultInfo;
import @{crud.properties.base_package}.@{crud.properties.model}.service.@{crud.table.className}Service;


/**
 * @{crud.table.remarks}:业务逻辑处理测试类
 * @author generator
 * @date @{crud.timestamp}
 * @since 1.0
 */
public class @{crud.table.className}ServiceTest extends BaseJunit  {
    @Autowired
    private @{crud.table.className}Service @{strutils.toLowerCaseFirst(crud.table.className)}Service;
    
    @Test
    public void testAdd(){
        JSONObject params = new JSONObject();
        ResultInfo resultInfo = @{strutils.toLowerCaseFirst(crud.table.className)}Service.add(params);
        LOGGER.debug("add PayAccount {} ,result is {}",params,resultInfo);
    }
    
    @Test
    public void testQuery(){
    }
    
    @Test
    public void testQueryPage(){
        
    }
    
    @Test
    public void testUpdate(){
        
    }
    
    @Test
    public void testDelete(){
        
    }
}