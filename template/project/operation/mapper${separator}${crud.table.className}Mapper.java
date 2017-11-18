package @{packagePath}.mapper;

import java.util.List;


import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import @{packagePath}.model.@{crud.table.className};

/**
 * @{crud.table.remarks} 数据层
 * @author generator
 */
public interface @{crud.table.className}Mapper extends BaseMapper<@{crud.table.className}> {
    
    List<@{crud.table.className}> get@{crud.table.className}Page(@Param("page") Page<@{crud.table.className}> page,
        # for(column in crud.table.searchColumns){ #
        @Param("@{strutils.toLowerCaseFirst(column.columnJavaName)}") @{column.javaTypeObject} @{strutils.toLowerCaseFirst(column.columnJavaName)},
        # } #
        @Param("orderByField") String orderByField, @Param("isAsc") boolean isAsc);
}
