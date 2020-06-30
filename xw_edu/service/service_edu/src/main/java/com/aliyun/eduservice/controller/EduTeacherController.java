package com.aliyun.eduservice.controller;

import com.aliyun.commonbase.exceptionhandler.exceptionhandler.SelfException;
import com.aliyun.commonutils.R;
import com.aliyun.eduservice.entity.EduTeacher;
import com.aliyun.eduservice.entity.vo.TeacherQuery;
import com.aliyun.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testXw
 * @since 2020-05-17
 */
@Api(description = "讲师管理模块")
@RestController
@RequestMapping("/eduservice/teacher")
@Slf4j
@CrossOrigin
public class EduTeacherController {

    // 访问地址： localhost:8004/eduservice/teacher/findAllTeachers

    @Autowired
    private EduTeacherService teacherService;

    // 1.查询所有讲师的列表 restful风格
    @GetMapping("findAllTeachers")
    @ApiOperation(value = "所有讲师列表")
    public R findAll() {
        List<EduTeacher> teachers = teacherService.list(null);
        try {
            throw new ArithmeticException("heihei");
        } catch (ArithmeticException e) {
            log.error(e.getMessage());
            e.printStackTrace();
            return R.error().data("items",teachers);
        }
//        return R.ok().data("items", teachers);
    }


    // 2.逻辑删除讲师
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "逻辑删除讲师")
    public R deleteByID(@ApiParam(name = "id", value = "讲师id", required = true) @PathVariable String id) {
        EduTeacher byId = teacherService.getById(id);
        if (byId != null) {
            teacherService.removeById(id);
            return R.ok();
        } else {
            return R.error();
        }
    }

    // 3.分页查询讲师
    @GetMapping("pageTeacher/{current}/{limit}")
    @ApiOperation(value = "分页查询讲师")
    public R pageTeacher(@ApiParam(name = "current", value = "当前页", required = true)
                         @PathVariable Long current,
                         @ApiParam(name = "limit", value = "每页记录数", required = true)
                         @PathVariable Long limit) {
        Page<EduTeacher> teacherPage = new Page<>(current, limit);
        // 进行查询，并将结果封装到teacherPage中
        teacherService.page(teacherPage, null);
        long total = teacherPage.getTotal();
        List<EduTeacher> row = teacherPage.getRecords();
        return R.ok().data("total", total).data("row", row);
    }

    // 4.条件查询带分页
    @ApiOperation(value = "条件查询带分页")
    @PostMapping("conditionPage/{current}/{limit}")
    public R conditionPage(@ApiParam(name = "current", value = "当前页", required = true)
                           @PathVariable Long current,
                           @ApiParam(name = "limit", value = "每页记录数", required = true)
                           @PathVariable Long limit,
                           @RequestBody(required = false) TeacherQuery teacherQuery) {
        // 获取对象
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        Page<EduTeacher> teacherPage = new Page<>(current, limit);

        // 获取动态传入的参数值
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        // todo 此处需要填入字段名称，而非属性名称
        if (!StringUtils.isEmpty(name)) wrapper.like("name", name);
        if (!StringUtils.isEmpty(level)) wrapper.eq("level", level);
        if (!StringUtils.isEmpty(begin)) wrapper.le("gmt_create", begin);
        if (!StringUtils.isEmpty(end)) wrapper.gt("gmt_modified", end);


        // 进行查询，并将结果封装到teacherPage中
        teacherService.page(teacherPage, wrapper);
        long total = teacherPage.getTotal();
        List<EduTeacher> row = teacherPage.getRecords();
        return R.ok().data("total", total).data("row", row);
    }


    // 5.添加讲师
    @PostMapping("addTeacher")
    @ApiOperation(value = "添加讲师")
    public R pageTeacher(@RequestBody EduTeacher eduTeacher) {
        // 进行查询，并将结果封装到teacherPage中
        boolean flag = teacherService.save(eduTeacher);

        try {
            if (flag) return R.ok();
                //else return R.error();
            else {
                throw new SelfException(666, "自定义异常");
            }
        } catch (SelfException e) {
            e.printStackTrace();
            return R.error();
        }
    }

    // 6.根据讲师id进行查询
    @GetMapping("getTeacherById/{id}")
    @ApiOperation(value = "根据讲师id进行查询")
    public R getTeacher(@ApiParam(name = "id", value = "讲师Id", required = true) @PathVariable String id) {
        EduTeacher eduTeacher = teacherService.getById(id);
        return R.ok().data("teacher", eduTeacher);
    }

    // 7.修改讲师
    @PostMapping("updateTeacher")
    @ApiOperation(value = "修改讲师")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean flag = teacherService.updateById(eduTeacher);
        if (flag) return R.ok();
        else return R.error();
    }

}

