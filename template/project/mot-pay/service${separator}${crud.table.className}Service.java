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
     * 
     * @param params jsonstring from entity
     * @return code and msg
     */
    ResultInfo add(JSONObject params);
    
    
    /**
     * 查询@{crud.table.remarks}
     * 
     * 
     * @param @{strutils.toLowerCaseFirst(crud.table.className)} jsonstring to entity
     * @return code and entity json string
     */
    @{crud.table.className} query(@{crud.table.className} @{strutils.toLowerCaseFirst(crud.table.className)});

    
    /**
     * 分页查询@{crud.table.remarks}
     * 
     * 
     * @param page pageInfo
     * @param @{strutils.toLowerCaseFirst(crud.table.className)} search criteria jsonstring to entity
     * @return code and dataList 
     */
    ResultInfo queryPage(Page<@{crud.table.className}> page, @{crud.table.className} @{strutils.toLowerCaseFirst(crud.table.className)});

    
    /**
     * 更新@{crud.table.remarks}
     * 
     * @param @{strutils.toLowerCaseFirst(crud.table.className)} jsonstring to entity
     * @return code and msg
     */
    ResultInfo update(@{crud.table.className} @{strutils.toLowerCaseFirst(crud.table.className)});

    
    /**
     * 删除@{crud.table.remarks}
     * 
     * @param params jsonstring from delete criteria
     * @return code and msg
     */
    ResultInfo delete(JSONObject params);
}