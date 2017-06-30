package @{packagePath}.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;

import @{packagePath}.model.@{crud.table.className};
import @{packagePath}.mapper.@{crud.table.className}Mapper;
import com.kmob.powernetwork.operation.annotation.Permission;
import com.kmob.powernetwork.operation.controller.base.BaseController;
import com.kmob.powernetwork.operation.constant.Const;
import com.kmob.powernetwork.operation.exception.BizExceptionEnum;
import com.kmob.powernetwork.operation.exception.BussinessException;
import com.kmob.powernetwork.operation.factory.PageFactory;
import com.kmob.powernetwork.operation.util.ToolUtil;

/**
 * @{crud.table.remarks} 控制层
 * @author zhouzhixiang
 * @date @{now}
 */
@Controller
@RequestMapping(value = "/@{module}/@{strutils.toLowerCaseFirst(crud.table.className)}")
public class @{crud.table.className}Controller extends BaseController{
    
    private static String PREFIX = "/system/@{strutils.toUpperCaseFirst(crud.table.className)}/";
    
    @Resource
    private @{crud.table.className}Mapper @{strutils.toUpperCaseFirst(crud.table.className)}Mapper;
    
    @RequestMapping("")
    public String index() {
        return PREFIX + "@{strutils.toLowerCaseFirst(crud.table.className)}.html";
    }
    
    @RequestMapping("/list")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Object list() {
        Page<@{crud.table.className}> page = new PageFactory<@{crud.table.className}>().defaultPage();
        List<@{crud.table.className}> result = @{strutils.toUpperCaseFirst(crud.table.className)}Mapper.get@{crud.table.className}Page(page, page.getOrderByField(), page.isAsc());
        page.setRecords(result);
        return packForBT(page);
    }
    
    @RequestMapping(value = "/@{strutils.toLowerCaseFirst(crud.table.className)}_add")
    public String @{strutils.toLowerCaseFirst(crud.table.className)}Add() {
        return PREFIX + "@{strutils.toLowerCaseFirst(crud.table.className)}_add.html";
    }
    
    @Permission(Const.ADMIN_NAME)
    @RequestMapping(value = "/@{strutils.toLowerCaseFirst(crud.table.className)}_edit/{id}")
    public String @{strutils.toLowerCaseFirst(crud.table.className)}Edit(@PathVariable Integer id, Model model) {
        if (ToolUtil.isEmpty(id)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        @{crud.table.className} @{strutils.toLowerCaseFirst(crud.table.className)} = @{strutils.toUpperCaseFirst(crud.table.className)}Mapper.selectById(id);
        
        LogObjectHolder.me().set(@{strutils.toLowerCaseFirst(crud.table.className)});
        return PREFIX + "@{strutils.toLowerCaseFirst(crud.table.className)}_edit.html";
    }
    
}