package com.test_e.controller;

import com.test_e.entity.Selection;
import com.test_e.entity.Semester;
import com.test_e.entity.Student;
import com.test_e.entity.User;

import com.test_e.service.SelectionService;
import com.test_e.service.SemesterService;
import com.test_e.service.StudentService;
import com.test_e.service.TeacherService;
import com.test_e.utils.Result;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.List;


@Controller
@RequestMapping("/student")
public class StudentController extends BaseController {


    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private SemesterService semesterService;
    @Autowired
    private SelectionService selectionService;


    @GetMapping("/home")
    public String studentHome(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/login";
        }
        try {
            checkRole(currentUser, "student");
        } catch (RuntimeException e) {
            if (e.getMessage() != null && e.getMessage().contains("请先登录")) {
                return "redirect:/login";
            } else if (e.getMessage() != null && e.getMessage().contains("权限不足")) {
                return "error/403";
            }
            throw e;
        }
        String welcomeMsg = "欢迎, " + currentUser.getUsername();
        model.addAttribute("welcomeMsg", welcomeMsg);
        model.addAttribute("currentUser", currentUser);
        return "student/studentHome";
    }

    @GetMapping("/edit")
    public String showEditForm(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("currentUser");
        checkRole(currentUser, "student");

        // 获取学生信息并添加到模型
        Student student = studentService.getStudentById(currentUser.getUserId());
        model.addAttribute("student", student);

        // 添加当前年份，供入学年份验证使用
        model.addAttribute("currentYear", Year.now().getValue());

        return "student/studentEdit";
    }

    @GetMapping("/detail")
    public String getStudentDetail(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("currentUser");
        checkRole(currentUser, "student");

        // 获取学生信息并添加到模型
        Student student = studentService.getStudentById(currentUser.getUserId());
        System.out.println("学生信息：" + student);
        if (student == null) {
            // 处理学生信息不存在的情况
            return "redirect:/error"; // 或其他错误处理页面
        }
        model.addAttribute("student", student);
        return "student/studentDetail";
    }

    @PostMapping("/update")
    @ResponseBody
    public Result updateStudent(
            HttpSession session,
            @ModelAttribute("student") Student student,
            BindingResult result) {

        if (result.hasErrors()) {
            return Result.error(result.getFieldError().getDefaultMessage());
        }

        User currentUser = (User) session.getAttribute("currentUser");
        checkRole(currentUser, "student");

        // 确保ID设置正确
        student.setStudent_Id(currentUser.getUserId());

        studentService.updateStudent(student);
        return Result.success("学生信息更新成功");
    }

    @GetMapping("/grade")
    public String studentScore(HttpSession session, Model model,
                               @RequestParam(value = "semesterId", required = false) Integer semesterId) {
        User student = (User) session.getAttribute("currentUser");
        if (student == null || !"student".equals(student.getRole())) {
            return "redirect:/login";
        }

         //获取所有学期
        List<Semester> semesters = semesterService.getAllSemesters();
        model.addAttribute("semesters", semesters);

        // 获取当前学期
        Semester currentSemester = semesterService.getCurrentSemester();
        if (semesterId == null && currentSemester != null) {
            semesterId = currentSemester.getSemestor_id();
        }

        // 获取学生成绩
        List<Selection> scores = selectionService.getScoresByStudentId(student.getUserId(), semesterId);
        model.addAttribute("scores", scores);
        model.addAttribute("selectedSemesterId", semesterId);

        return "student/studentGrade";
    }
}

