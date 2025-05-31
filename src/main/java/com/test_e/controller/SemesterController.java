package com.test_e.controller;

import com.test_e.entity.Semester;
import com.test_e.entity.User;
import com.test_e.service.SemesterService;

import com.test_e.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/semester")
public class SemesterController extends BaseController {

    @Autowired
    private SemesterService semesterService;

    /**
     * 获取所有学期
     */
    @GetMapping("/list")
    @ResponseBody
    public Result getSemesterList() {
        List<Semester> semesters = semesterService.getAllSemesters();
        return Result.success(semesters);
    }

    /**
     * 获取当前学期
     */
    @GetMapping("/current")
    @ResponseBody
    public Result getCurrentSemester() {
        Semester semester = semesterService.getCurrentSemester();
        return Result.success(semester);
    }

    /**
     * 添加学期
     */
    @PostMapping("/add")
    @ResponseBody
    public Result addSemester(@ModelAttribute("currentUser") User currentUser,
                              @RequestBody Semester semester) {
        checkRole(currentUser, "admin");
        semesterService.addSemester(semester);
        return Result.success("学期添加成功");
    }

    /**
     * 更新学期
     */
    @PostMapping("/update")
    @ResponseBody
    public Result updateSemester(@ModelAttribute("currentUser") User currentUser,
                                 @RequestBody Semester semester) {
        checkRole(currentUser, "admin");
        semesterService.updateSemester(semester);
        return Result.success("学期更新成功");
    }

    /**
     * 设置当前学期
     */
    @PostMapping("/setCurrent/{semesterId}")
    @ResponseBody
    public Result setCurrentSemester(@ModelAttribute("currentUser") User currentUser,
                                     @PathVariable Integer semesterId) {
        checkRole(currentUser, "admin");
        semesterService.setCurrentSemester(semesterId);
        return Result.success("当前学期设置成功");
    }

    /**
     * 设置选课时间
     */
    @PostMapping("/setSelectionTime")
    @ResponseBody
    public Result setSelectionTime(@ModelAttribute("currentUser") User currentUser,
                                   Integer semesterId, Date startTime, Date endTime) {
        checkRole(currentUser, "admin");
        semesterService.setSelectionTime(semesterId, startTime, endTime);
        return Result.success("选课时间设置成功");
    }
}