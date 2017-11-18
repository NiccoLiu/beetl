package @{crud.properties.base_package}.@{crud.properties.model}.dao;

import java.util.List;


import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import @{crud.properties.base_package}.@{crud.properties.model}.entity.@{crud.table.className};

/**
 * @{crud.table.remarks}DAO类
 * 
 * @author generator
 */
public interface @{crud.table.className}DAO extends BaseMapper<@{crud.table.className}> {
    
    /**
     * 分页查询@{crud.table.remarks}
     * @param page
     * @param alipayOrder
     * @param orderByField
     * @param isAsc
     * @return @{crud.table.remarks}列表
     */
    List<@{crud.table.className}> get@{crud.table.className}Page(@Param("page") Page<@{crud.table.className}> page,
        @Param("@{strutils.toLowerCaseFirst(crud.table.className)}") @{crud.table.className} @{strutils.toLowerCaseFirst(crud.table.className)},
        @Param("orderByField") String orderByField, @Param("isAsc") boolean isAsc);
}
