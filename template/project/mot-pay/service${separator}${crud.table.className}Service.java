package @{crud.properties.base_package}.@{crud.properties.model}.service;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.kmob.mot.pay.common.vo.ResultInfo;
import @{crud.properties.base_package}.@{crud.properties.model}.entity.@{crud.table.className};

/**
 * @{crud.table.remarks}:业务接口类
 * 
 * @author generator
 */
public interface @{crud.table.className}Service {
    
    /**
     * 新增@{crud.table.remarks}
     * 
     * @param params
     * @return code & msg
     */
    ResultInfo add(JSONObject params);
    
    
    /**
     * 查询@{crud.table.remarks}
     * 
     * @param params
     * @return @{crud.table.remarks}
     */
    @{crud.table.className} query(@{crud.table.className} @{strutils.toLowerCaseFirst(crud.table.className)});

    
    /**
     * 分页查询@{crud.table.remarks}
     * 
     * @param params
     * @return code & @{crud.table.remarks}List
     */
    ResultInfo queryPage(Page<@{crud.table.className}> page, @{crud.table.className} @{strutils.toLowerCaseFirst(crud.table.className)});

    
    /**
     * 更新@{crud.table.remarks}
     * 
     * @param alipayOrder 更新对象
     * @return code & msg
     */
    ResultInfo update(@{crud.table.className} @{strutils.toLowerCaseFirst(crud.table.className)});

    
    /**
     * 删除@{crud.table.remarks}
     * 
     * @param params 
     * @return 是否成功
     */
    ResultInfo delete(JSONObject params);
}