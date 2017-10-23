package @{crud.properties.base_package}.@{crud.properties.model}.service;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.kmob.etcm.common.entity.ResultInfo;
import @{crud.properties.base_package}.@{crud.properties.model}.entity.@{crud.table.className};

/**
 * @{crud.table.remarks}:业务接口类
 * @author generator
 * @date @{crud.timestamp}
 * @since 1.0
 */
public interface @{crud.table.className}Service {
    ResultInfo add(JSONObject params);
    
    @{crud.table.className} query(@{crud.table.className} @{strutils.toLowerCaseFirst(crud.table.className)});

    ResultInfo queryPage(Page<@{crud.table.className}> page, @{crud.table.className} @{strutils.toLowerCaseFirst(crud.table.className)});

    ResultInfo update(@{crud.table.className} @{strutils.toLowerCaseFirst(crud.table.className)});

    ResultInfo delete(Long id);
}