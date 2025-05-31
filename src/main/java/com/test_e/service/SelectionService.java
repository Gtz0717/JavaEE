package com.test_e.service;

import com.test_e.entity.Selection;
import java.util.List;

public interface SelectionService {
    /**
     * 学生选课
     * @param studentId 学生ID
     * @param courseId 课程ID
     */
    void selectCourse(Integer studentId, Integer courseId);

    /**
     * 学生退课
     * @param studentId 学生ID
     * @param courseId 课程ID
     */
    void dropCourse(Integer studentId, Integer courseId);

    /**
     * 获取学生的选课列表
     * @param studentId 学生ID
     * @return 选课列表
     */
    List<Selection> getSelectionsByStudent(Integer studentId);

    /**
     * 录入成绩
     * @param selection 选课记录(包含成绩)
     */
    void recordScore(Selection selection);

    /**
     * 评价课程
     * @param selection 选课记录(包含评价)
     */
    void evaluateCourse(Selection selection);

    /**
     * 检查教师是否有权限操作该选课记录
     * @param selectionId 选课记录ID
     * @param teacherId 教师ID
     * @return 是否有权限
     */
    boolean checkTeacherPermission(Integer selectionId, Integer teacherId);

    /**
     * 检查学生是否有权限操作该选课记录
     * @param selectionId 选课记录ID
     * @param studentId 学生ID
     * @return 是否有权限
     */
    boolean checkStudentPermission(Integer selectionId, Integer studentId);



    // 根据学生ID和学期ID获取成绩
    List<Selection> getScoresByStudentId(Integer studentId, Integer semesterId);

    // 根据课程ID获取成绩
    List<Selection> getScoresByCourseId(Integer courseId);

    // 管理员多条件查询成绩
    List<Selection> getScores(Integer semesterId, Integer studentId, Integer courseId);

    // 更新成绩
    boolean updateScore(Integer selectionId, Double score);



}