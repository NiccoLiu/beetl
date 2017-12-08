package @{crud.properties.base_package}.@{crud.properties.model}.service.impl;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.kmob.mot.pay.common.vo.ResultInfo;
import com.kmob.mot.pay.common.dto.GridDTO;

import @{crud.properties.base_package}.@{crud.properties.model}.entity.@{crud.table.className};
import @{crud.properties.base_package}.@{crud.properties.model}.dao.@{crud.table.className}DAO;
import @{crud.properties.base_package}.@{crud.properties.model}.service.@{crud.table.className}Service;

/**
 * @{crud.table.remarks}:业务接口实现类
 * 
 * 
 * @author generator
 */
@Service
@Transactional
public class @{crud.table.className}ServiceImpl implements @{crud.table.className}Service {
    private static final Logger logger = LoggerFactory.getLogger(@{crud.table.className}ServiceImpl.class);
    
    @Autowired
    private @{crud.table.className}DAO @{strutils.toLowerCaseFirst(crud.table.className)}DAO;
    
    @Override
    public ResultInfo add(JSONObject params){
        logger.debug("add @{crud.table.className} ,the params is {}",params);
        ResultInfo resultInfo = new ResultInfo(0, "success");
        @{crud.table.className} @{strutils.toLowerCaseFirst(crud.table.className)} = params.toJavaObject(@{crud.table.className}.class);
        @{strutils.toLowerCaseFirst(crud.table.className)}DAO.insert(@{strutils.toLowerCaseFirst(crud.table.className)});
        return resultInfo;
    }
    
    @Override
    public @{crud.table.className} query(@{crud.table.className} param){
        logger.debug("query @{crud.table.className} by {}",param);
        @{crud.table.className} @{strutils.toLowerCaseFirst(crud.table.className)} = @{strutils.toLowerCaseFirst(crud.table.className)}DAO.selectOne(param);
        return @{strutils.toLowerCaseFirst(crud.table.className)};
    }
    
    @Override
    public ResultInfo queryPage(Page<@{crud.table.className}> page, @{crud.table.className} @{strutils.toLowerCaseFirst(crud.table.className)}){
        logger.debug("queryPage @{crud.table.className} ,the entity is {},the page is {}",@{strutils.toLowerCaseFirst(crud.table.className)},page);
        ResultInfo resultInfo = new ResultInfo(0, "success");
        List<@{crud.table.className}> result = @{strutils.toLowerCaseFirst(crud.table.className)}DAO.get@{crud.table.className}Page(page,
            @{strutils.toLowerCaseFirst(crud.table.className)},
            page.getOrderByField(), page.isAsc());
        GridDTO<@{crud.table.className}> grid = new GridDTO<@{crud.table.className}>();
        grid.setTotalRecord(page.getTotal());
        grid.setTotalPage(page.getPages());
        grid.setList(result);
        resultInfo.setData(grid);
        return resultInfo;
    }
    
    
    @Override
    public ResultInfo update(@{crud.table.className} @{strutils.toLowerCaseFirst(crud.table.className)}){
        logger.debug("update @{crud.table.className} ,the entity is {}",@{strutils.toLowerCaseFirst(crud.table.className)});
        ResultInfo resultInfo = new ResultInfo(0, "success");
        @{strutils.toLowerCaseFirst(crud.table.className)}DAO.updateById(@{strutils.toLowerCaseFirst(crud.table.className)});
        return resultInfo;
    }
    
    @Override
    public ResultInfo delete(JSONObject params){
        logger.debug("delete @{crud.table.className} by params {}",params);
        Long id = params.getLong("id");
        ResultInfo resultInfo = new ResultInfo(0, "success");
        @{strutils.toLowerCaseFirst(crud.table.className)}DAO.deleteById(id);
        return resultInfo;
    }
    
}