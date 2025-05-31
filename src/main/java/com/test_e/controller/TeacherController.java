package com.test_e.controller;

import com.test_e.entity.Course;
import com.test_e.entity.Selection;
import com.test_e.entity.Teacher;
import com.test_e.entity.User;
import com.test_e.service.CourseService;
import com.test_e.service.SelectionService;
import com.test_e.service.TeacherService;
import com.test_e.utils.Result;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController extends BaseController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private SelectionService selectionService;

    /**
     * 获取教师详细信息
     */
    @GetMapping("/detail")
    @ResponseBody
    public Result getTeacherDetail(@ModelAttribute("currentUser") User currentUser) {
        checkRole(currentUser, "teacher");
        Teacher teacher = teacherService.getTeacherById(currentUser.getUserId());
        return Result.success(teacher);
    }

    /**
     * 更新教师信息
     */
    @PostMapping("/update")
    @ResponseBody
    public Result updateTeacher(@ModelAttribute("currentUser") User currentUser,
                                @RequestBody Teacher teacher) {
        checkRole(currentUser, "teacher");
        teacher.setTeacher_id(currentUser.getUserId());
        teacherService.updateTeacher(teacher);
        return Result.success("教师信息更新成功");
    }


    @GetMapping("/grade")
    public String teacherScore(HttpSession session, Model model,
                               @RequestParam(value = "courseId", required = false) Integer courseId) {
        User teacher = (User) session.getAttribute("currentUser");
        if (teacher == null || !"teacher".equals(teacher.getRole())) {
            return "redirect:/login";
        }

        // 获取教师所教的所有课程
        List<Course> courses = courseService.getCoursesByTeacher(teacher.getUserId());
        model.addAttribute("courses", courses);

        if (courseId != null) {
            // 获取特定课程的学生成绩
            List<Selection> scores = selectionService.getScoresByCourseId(courseId);
            model.addAttribute("scores", scores);
            model.addAttribute("selectedCourseId", courseId);

            // 获取课程信息
            Course course = courseService.getCourseById(courseId);
            model.addAttribute("course", course);
        }

        return "score/teacher_score";
    }
}

