package com.chengdu.longblog.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chengdu.longblog.entity.Blog;
import com.chengdu.longblog.entity.Type;
import com.chengdu.longblog.service.BlogService;
import com.chengdu.longblog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.ArrayList;


@Controller
@RequestMapping("admin")
public class TypeController {

    /* 每页显示多少数据 */
    private static final int SIZE = 6;

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;


    /* 分页显示标签类型 */
    @GetMapping("/types")
    public String types(@RequestParam(value = "current",defaultValue = "0") Integer current, Model model){   //
        Page page = new Page(current,SIZE);
        IPage types = typeService.page(page);
        model.addAttribute("types",types);
        return "/admin/types";
    }

    /* 跳转到type-input页面 */
    @RequestMapping("/typeInput")
    public String typeInput(Model model){
        model.addAttribute("type",new Type());
        return "/admin/type-input";
    }

    /* 新增类型 */
    @PostMapping("/addType")
    public String addType(Type type, Model model){

        int count = typeService.count(new QueryWrapper<Type>(type));
        if (count>=1){
            model.addAttribute("result",true);
            return "forward:/admin/typeInput";
        }

        boolean result = typeService.save(type);
        model.addAttribute("result",result);
        return "redirect:/admin/types";
    }

    /*
    * 根据前端传送过来的type进行查询，并且回显到前端
    *
    * */
    @GetMapping("/getType")
    public String getType(Type type,Model model){
        Type one = typeService.getOne(new QueryWrapper<Type>(type));
        model.addAttribute("type",one);
        return "/admin/type-input";
    }
    /* type-input页面进行判断如果id不为空说明是修改Type，为空就是添加Type */
    @PostMapping("/updateTypeName")
    public String updateTypeName(Type type,Model model){
        boolean b = typeService.updateById(type);
        /*boolean update = typeService.update(new UpdateWrapper<Type>(type));*/
        return "redirect:/admin/types";
    }

    /* 通过主键id删除Type */
    @GetMapping("deleteType")
    public String deleteType(Type type,Model model){
        boolean b = typeService.removeById(type.id);
        return "redirect:/admin/types";
    }



}
