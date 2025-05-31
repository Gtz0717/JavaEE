package com.test_e.controller;

import com.test_e.entity.Course;
import com.test_e.entity.Selection;
import com.test_e.entity.Semester;
import com.test_e.entity.User;
import com.test_e.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;




@Controller
@RequestMapping("/admin")
public class AdminCotroller {

    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private SelectionService selectionService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private UserService userService;
    @Autowired
    private SemesterService semesterService;


    // 管理员查看所有学生成绩
    @GetMapping("/grade")
    public String adminScore(Model model,
                             @RequestParam(value = "semesterId", required = false) Integer semesterId,
                             @RequestParam(value = "studentId", required = false) Integer studentId,
                             @RequestParam(value = "courseId", required = false) Integer courseId) {
        // 获取所有学期
        List<Semester> semesters = semesterService.getAllSemesters();
        model.addAttribute("semesters", semesters);

        // 获取当前学期
        Semester currentSemester = semesterService.getCurrentSemester();
        if (semesterId == null && currentSemester != null) {
            semesterId = currentSemester.getSemestor_id();
        }

        // 获取成绩列表
        List<Selection> scores = selectionService.getScores(semesterId, studentId, courseId);
        model.addAttribute("scores", scores);
        model.addAttribute("selectedSemesterId", semesterId);
        model.addAttribute("selectedStudentId", studentId);
        model.addAttribute("selectedCourseId", courseId);

        // 如果指定了学生ID，获取学生信息
        if (studentId != null) {
            User student = userService.getUserById(studentId);
            model.addAttribute("student", student);
        }

        // 如果指定了课程ID，获取课程信息
        if (courseId != null) {
            Course course = courseService.getCourseById(courseId);
            model.addAttribute("course", course);
        }

        return "score/admin_score";
    }

}
