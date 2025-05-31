package com.test_e.service;

import com.test_e.entity.Semester;

import java.util.Date;
import java.util.List;

public interface SemesterService {
    /**
     * 获取所有学期
     * @return 学期列表
     */
    List<Semester> getAllSemesters();

    /**
     * 获取当前学期
     * @return 当前学期
     */
    Semester getCurrentSemester();

    /**
     * 添加学期
     * @param semester 学期信息
     */
    void addSemester(Semester semester);

    /**
     * 更新学期信息
     * @param semester 学期信息
     */
    void updateSemester(Semester semester);

    /**
     * 设置当前学期
     * @param semesterId 学期ID
     */
    void setCurrentSemester(Integer semesterId);

    /**
     * 设置选课时间
     * @param semesterId 学期ID
     * @param startTime 选课开始时间
     * @param endTime 选课结束时间
     */
    void setSelectionTime(Integer semesterId, Date startTime, Date endTime);
}