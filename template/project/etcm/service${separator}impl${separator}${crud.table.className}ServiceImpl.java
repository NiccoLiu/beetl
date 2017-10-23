package @{crud.properties.base_package}.@{crud.properties.model}.service.impl;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.kmob.etcm.common.entity.ResultInfo;
import com.kmob.etcm.util.Grid;


import @{crud.properties.base_package}.@{crud.properties.model}.entity.@{crud.table.className};
import @{crud.properties.base_package}.@{crud.properties.model}.mapper.@{crud.table.className}Mapper;
import @{crud.properties.base_package}.@{crud.properties.model}.service.@{crud.table.className}Service;



/**
 * @{crud.table.remarks}:业务接口实现类
 * @author generator
 * @date @{crud.timestamp}
 * @since 1.0
 */
@Service
@Transactional
public class @{crud.table.className}ServiceImpl implements @{crud.table.className}Service {
    private static final Logger LOGGER = LoggerFactory.getLogger(@{crud.table.className}ServiceImpl.class);
    
    @Autowired
    private @{crud.table.className}Mapper @{strutils.toLowerCaseFirst(crud.table.className)}Mapper;
    
    @Override
    public ResultInfo add(JSONObject params){
        LOGGER.debug("add @{crud.table.className} ,the params is {}",params);
        ResultInfo resultInfo = new ResultInfo(0, "success");
        @{crud.table.className} @{strutils.toLowerCaseFirst(crud.table.className)} = params.toJavaObject(@{crud.table.className}.class);
        @{strutils.toLowerCaseFirst(crud.table.className)}Mapper.insert(@{strutils.toLowerCaseFirst(crud.table.className)});
        return resultInfo;
    }
    
    @Override
    public @{crud.table.className} query(@{crud.table.className} param){
        LOGGER.debug("query @{crud.table.className} by {}",param);
        @{crud.table.className} @{strutils.toLowerCaseFirst(crud.table.className)} = @{strutils.toLowerCaseFirst(crud.table.className)}Mapper.selectOne(@{strutils.toLowerCaseFirst(crud.table.className)});
        return @{strutils.toLowerCaseFirst(crud.table.className)};
    }
    
    @Override
    public ResultInfo queryPage(Page<@{crud.table.className}> page, @{crud.table.className} @{strutils.toLowerCaseFirst(crud.table.className)}){
        ResultInfo resultInfo = new ResultInfo(0, "success");
        List<@{crud.table.className}> result = @{strutils.toLowerCaseFirst(crud.table.className)}Mapper.get@{crud.table.className}Page(page,
            # for(column in crud.table.searchColumns){ #
            @{strutils.toLowerCaseFirst(column.columnJavaName)},
            # } #
            page.getOrderByField(), page.isAsc());
        Grid grid = new Grid();
        grid.setTotal_record(page.getTotal());
        grid.setTotal_page(page.getPages());
        grid.setList(result);
        resultInfo.setData(grid);
        return resultInfo;
    }
    
    
    @Override
    public ResultInfo update(@{crud.table.className} @{strutils.toLowerCaseFirst(crud.table.className)}){
        LOGGER.debug("update @{crud.table.className} ,the entity is {}",@{strutils.toLowerCaseFirst(crud.table.className)});
        ResultInfo resultInfo = new ResultInfo(0, "success");
        @{strutils.toLowerCaseFirst(crud.table.className)}Mapper.updateById(@{strutils.toLowerCaseFirst(crud.table.className)});
        return resultInfo;
    }
    
    @Override
    public ResultInfo delete(Long id){
        LOGGER.debug("delete @{crud.table.className} by id {}",id);
        ResultInfo resultInfo = new ResultInfo(0, "success");
        @{strutils.toLowerCaseFirst(crud.table.className)}Mapper.deleteById(id);
        return resultInfo;
    }
    
}