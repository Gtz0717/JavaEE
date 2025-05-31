package com.test_e.controller;


import com.test_e.entity.Selection;
import com.test_e.entity.User;
import com.test_e.service.SelectionService;
import com.test_e.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/selection")
public class SelectionController extends BaseController {

    @Autowired
    private SelectionService selectionService;

    /**
     * 学生选课
     */
    @PostMapping("/select/{courseId}")
    @ResponseBody
    public Result selectCourse(@ModelAttribute("currentUser") User currentUser,
                               @PathVariable Integer courseId) {
        checkRole(currentUser, "student");
        selectionService.selectCourse(currentUser.getUserId(), courseId);
        return Result.success("选课成功");
    }

    /**
     * 学生退课
     */
    @PostMapping("/drop/{courseId}")
    @ResponseBody
    public Result dropCourse(@ModelAttribute("currentUser") User currentUser,
                             @PathVariable Integer courseId) {
        checkRole(currentUser, "student");
        selectionService.dropCourse(currentUser.getUserId(), courseId);
        return Result.success("退课成功");
    }

    /**
     * 获取学生已选课程
     */
    @GetMapping("/myCourses")
    @ResponseBody
    public Result getMyCourses(@ModelAttribute("currentUser") User currentUser) {
        checkRole(currentUser, "student");
        List<Selection> selections = selectionService.getSelectionsByStudent(currentUser.getUserId());
        return Result.success(selections);
    }

    /**
     * 教师录入成绩
     */
    @PostMapping("/recordScore")
    @ResponseBody
    public Result recordScore(@ModelAttribute("currentUser") User currentUser,
                              @RequestBody Selection selection) {
        checkRole(currentUser, "teacher");
        // 验证教师是否有权限录入该课程成绩
        if (!selectionService.checkTeacherPermission(selection.getSelectionId(), currentUser.getUserId())) {
            return Result.error("无权录入该课程成绩");
        }
        selectionService.recordScore(selection);
        return Result.success("成绩录入成功");
    }

    /**
     * 学生评价课程
     */
    @PostMapping("/evaluate")
    @ResponseBody
    public Result evaluateCourse(@ModelAttribute("currentUser") User currentUser,
                                 @RequestBody Selection selection) {
        checkRole(currentUser, "student");
        // 验证学生是否有权限评价该课程
        if (!selectionService.checkStudentPermission(selection.getSelectionId(), currentUser.getUserId())) {
            return Result.error("无权评价该课程");
        }
        selectionService.evaluateCourse(selection);
        return Result.success("课程评价成功");
    }
}