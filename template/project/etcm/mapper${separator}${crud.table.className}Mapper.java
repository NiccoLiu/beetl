package @{crud.properties.base_package}.@{crud.properties.model}.mapper;

import java.util.List;


import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import @{crud.properties.base_package}.@{crud.properties.model}.entity.@{crud.table.className};

/**
 * @{crud.table.remarks}:数据层
 * 
 * @author generator
 */
public interface @{crud.table.className}Mapper extends BaseMapper<@{crud.table.className}> {
    
    /**
     * 分页查询@{crud.table.remarks}
     */
    List<@{crud.table.className}> get@{crud.table.className}Page(@Param("page") Page<@{crud.table.className}> page,
        @Param("@{strutils.toLowerCaseFirst(crud.table.className)}") @{crud.table.className} @{strutils.toLowerCaseFirst(crud.table.className)},
        @Param("orderByField") String orderByField, @Param("isAsc") boolean isAsc);
}
