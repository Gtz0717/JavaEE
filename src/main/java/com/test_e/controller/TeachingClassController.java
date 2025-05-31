package com.test_e.controller;


import com.test_e.entity.Teaching_class;
import com.test_e.entity.User;
import com.test_e.service.TeachingClassService;
import com.test_e.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/teachingClass")
public class TeachingClassController extends BaseController {

    @Autowired
    private TeachingClassService teachingClassService;

    /**
     * 创建教学班
     */
    @PostMapping("/create")
    @ResponseBody
    public Result createTeachingClass(@ModelAttribute("currentUser") User currentUser,
                                      @RequestBody Teaching_class teachingClass) {
        checkRole(currentUser, "teacher");
        teachingClass.setTeacher_id(currentUser.getUserId());
        teachingClassService.createTeachingClass(teachingClass);
        return Result.success("教学班创建成功");
    }

    /**
     * 更新教学班信息
     */
    @PostMapping("/update")
    @ResponseBody
    public Result updateTeachingClass(@ModelAttribute("currentUser") User currentUser,
                                      @RequestBody Teaching_class teachingClass) {
        checkRole(currentUser, "teacher");
        // 验证教师是否有权限修改该教学班
        Teaching_class oldClass = teachingClassService.getTeachingClassById(teachingClass.getClass_id());
        if (oldClass == null || !Integer.valueOf(oldClass.getTeacher_id()).equals(currentUser.getUserId())) {
            return Result.error("无权修改该教学班");
        }
        teachingClassService.updateTeachingClass(teachingClass);
        return Result.success("教学班更新成功");
    }

    /**
     * 获取课程的教学班列表
     */
    @GetMapping("/listByCourse/{courseId}")
    @ResponseBody
    public Result getTeachingClassesByCourse(@PathVariable Integer courseId) {
        List<Teaching_class> classes = teachingClassService.getTeachingClassesByCourse(courseId);
        return Result.success(classes);
    }

    /**
     * 获取教师的教学班列表
     */
    @GetMapping("/myClasses")
    @ResponseBody
    public Result getMyTeachingClasses(@ModelAttribute("currentUser") User currentUser) {
        checkRole(currentUser, "teacher");
        List<Teaching_class> classes = teachingClassService.getTeachingClassesByTeacher(currentUser.getUserId());
        return Result.success(classes);
    }
}