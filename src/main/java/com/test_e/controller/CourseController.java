package com.test_e.controller;

import com.test_e.entity.Course;
import com.test_e.entity.User;
import com.test_e.service.CourseService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController extends BaseController {

    @Autowired
    private CourseService courseService;

    private User getCurrentUser(HttpSession session) {
        return (User) session.getAttribute("currentUser");
    }

    /**
     * 添加课程 - 显示表单
     */
    @GetMapping("/add")
    public String showAddForm(HttpSession session, Model model) {
        User currentUser = getCurrentUser(session);
        checkRole(currentUser, "teacher");
        model.addAttribute("course", new Course());
        return "teacher/TeacherAddCourse";
    }

    /**
     * 添加课程 - 处理提交
     */
    @PostMapping("/addCourse")
    public String addCourse(@ModelAttribute("course") Course course,
            HttpSession session) {

        // 获取当前登录用户(老师)的ID
        User currentUser = getCurrentUser(session);

        Integer teacherId=currentUser.getUserId();
        course.setTeacherId(teacherId);
        // 设置默认状态为待审核
        course.setStatus("pending");
        // 设置初始已选人数为0
        course.setSelected(0);
        // 创建课程对象并保存
        courseService.addCourse(course);

        return "redirect:/course/list";
    }

    /**
     * 更新课程信息 - 显示表单
     */
    @GetMapping("/update/{courseId}")
    public String showUpdateForm(HttpSession session,
                                 @PathVariable Integer courseId,
                                 Model model) {
        User currentUser = getCurrentUser(session);
        checkRole(currentUser, "teacher");
        Course course = courseService.getCourseById(courseId);
        if (course == null || !course.getTeacherId().equals(currentUser.getUserId())) {
            model.addAttribute("error", "无权修改该课程");
            return "error";
        }
        model.addAttribute("course", course);
        return "course/update";
    }

    /**
     * 更新课程信息 - 处理提交
     */
    @PostMapping("/update")
    public String updateCourse(HttpSession session,
                               @ModelAttribute Course course,
                               Model model) {
        User currentUser = getCurrentUser(session);
        checkRole(currentUser, "teacher");
        Course oldCourse = courseService.getCourseById(course.getCourseId());
        if (oldCourse == null || !oldCourse.getTeacherId().equals(currentUser.getUserId())) {
            model.addAttribute("error", "无权修改该课程");
            return "error";
        }
        courseService.updateCourse(course);
        return "redirect:/course/list";
    }

    /**
     * 删除课程
     */
    @GetMapping("/delete/{courseId}")
    public String deleteCourse(HttpSession session,
                               @PathVariable Integer courseId,
                               Model model) {
        User currentUser = getCurrentUser(session);
        checkRole(currentUser, "teacher");
        Course course = courseService.getCourseById(courseId);
        if (course == null || !course.getTeacherId().equals(currentUser.getUserId())) {
            model.addAttribute("error", "无权删除该课程");
            return "error";
        }
        courseService.deleteCourse(courseId);
        return "redirect:/course/list";
    }

    /**
     * 获取课程列表
     */
    @GetMapping("/list")
    public String getCourseList(@RequestParam(required = false) String semester,
                                @RequestParam(required = false) String keyword,
                                Model model) {
        List<Course> courses = courseService.getCourseList(semester, keyword);
        model.addAttribute("courses", courses);
        model.addAttribute("semester", semester);
        model.addAttribute("keyword", keyword);
        return "course/list";
    }

    /**
     * 获取课程详情
     */
    @GetMapping("/detail/{courseId}")
    public String getCourseDetail(@PathVariable Integer courseId,
                                  Model model) {
        Course course = courseService.getCourseById(courseId);
        model.addAttribute("course", course);
        return "course/detail";
    }

    /**
     * 获取教师教授的课程
     */
    @GetMapping("/TeacherCourses")
    public String getTeacherCourses(
            HttpSession session,
            Model model,
            RedirectAttributes redirectAttributes  // 用于重定向时传递消息
    ) {
        try {
            // 1. 获取当前用户（如果未登录，会抛出异常）
            User currentUser = getCurrentUser(session);

            // 2. 检查角色（如果不是教师，会抛出 AccessDeniedException）
            checkRole(currentUser, "teacher");

            // 3. 查询该教师的课程
            List<Course> courses = courseService.getCoursesByTeacher(currentUser.getUserId());
            model.addAttribute("courses", courses);

            // 4. 返回教师课程页面
            return "teacher/my-courses";

        } catch (RuntimeException e) {
            // 5. 如果未登录或权限不足，重定向到登录页并提示
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/login";
        }
    }
}