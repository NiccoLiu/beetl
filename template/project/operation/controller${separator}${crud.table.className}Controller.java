package @{packagePath}.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
import com.kmob.powernetwork.operation.constant.Dict;
import com.kmob.powernetwork.operation.constant.Tip;
import com.kmob.powernetwork.operation.exception.BizExceptionEnum;
import com.kmob.powernetwork.operation.exception.BussinessException;
import com.kmob.powernetwork.operation.factory.ConstantFactory;
import com.kmob.powernetwork.operation.factory.PageFactory;
import com.kmob.powernetwork.operation.log.LogObjectHolder;
import com.kmob.powernetwork.operation.model.Role;
import com.kmob.powernetwork.operation.util.ToolUtil;

/**
 * @{crud.table.remarks} 控制层
 * @author zhouzhixiang
 * @date @{now}
 */
@Controller
@RequestMapping(value = "/@{module}/@{strutils.toLowerCaseFirst(crud.table.className)}")
public class @{crud.table.className}Controller extends BaseController{
    
    private static String PREFIX = "/system/@{strutils.toLowerCaseFirst(crud.table.className)}/";
    
    @Resource
    private @{crud.table.className}Mapper @{strutils.toLowerCaseFirst(crud.table.className)}Mapper;
    
    @RequestMapping("")
    public String index() {
        return PREFIX + "@{strutils.toLowerCaseFirst(crud.table.className)}.html";
    }
    
    @RequestMapping("/list")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Object list() {
        Page<@{crud.table.className}> page = new PageFactory<@{crud.table.className}>().defaultPage();
        List<@{crud.table.className}> result = @{strutils.toLowerCaseFirst(crud.table.className)}Mapper.get@{crud.table.className}Page(page, page.getOrderByField(), page.isAsc());
        page.setRecords(result);
        return packForBT(page);
    }
    
    @RequestMapping(value = "/@{strutils.toLowerCaseFirst(crud.table.className)}_add")
    public String @{strutils.toLowerCaseFirst(crud.table.className)}Add() {
        return PREFIX + "@{strutils.toLowerCaseFirst(crud.table.className)}_add.html";
    }
    
    @Permission(Const.ADMIN_NAME)
    @RequestMapping(value = "/@{strutils.toLowerCaseFirst(crud.table.className)}_edit/{id}")
    public String @{strutils.toLowerCaseFirst(crud.table.className)}Edit(@PathVariable Long id, Model model) {
        if (ToolUtil.isEmpty(id)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        @{crud.table.className} @{strutils.toLowerCaseFirst(crud.table.className)} = @{strutils.toLowerCaseFirst(crud.table.className)}Mapper.selectById(id);
        
        LogObjectHolder.me().set(@{strutils.toLowerCaseFirst(crud.table.className)});
        return PREFIX + "@{strutils.toLowerCaseFirst(crud.table.className)}_edit.html";
    }
    
    /**
     * 新增 @{crud.table.remarks}
     */
    @RequestMapping(value = "/add")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Tip add(@Valid @{crud.table.className} @{strutils.toLowerCaseFirst(crud.table.className)}, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        @{strutils.toLowerCaseFirst(crud.table.className)}.setId(null);
        @{strutils.toLowerCaseFirst(crud.table.className)}Mapper.insert(@{strutils.toLowerCaseFirst(crud.table.className)});
        return SUCCESS_TIP;
    }
    
    /**
     * 修改 @{crud.table.remarks}
     */
    @RequestMapping(value = "/update")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Tip edit(@Valid @{crud.table.className} @{strutils.toLowerCaseFirst(crud.table.className)}, BindingResult result) {
        if (result.hasErrors()) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        @{strutils.toLowerCaseFirst(crud.table.className)}Mapper.updateById(@{strutils.toLowerCaseFirst(crud.table.className)});

        return SUCCESS_TIP;
    }

    /**
     * 删除 @{crud.table.remarks}
     */
    @RequestMapping(value = "/remove")
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Tip remove(@RequestParam Long id) {
        if (ToolUtil.isEmpty(id)) {
            throw new BussinessException(BizExceptionEnum.REQUEST_NULL);
        }
        
        @{strutils.toLowerCaseFirst(crud.table.className)}Mapper.deleteById(id);
        return SUCCESS_TIP;
    }
    
}