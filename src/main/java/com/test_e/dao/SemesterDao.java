package com.test_e.dao;

import com.test_e.entity.Semester;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SemesterDao {
    /**
     * 查询所有学期
     */
    List<Semester> selectAllSemesters();

    /**
     * 查询当前学期
     */
    Semester selectCurrentSemester();

    /**
     * 根据名称查询学期
     */
    Semester selectSemesterByName(String semesterName);

    /**
     * 新增学期
     */
    int insertSemester(Semester semester);

    /**
     * 更新学期信息
     */
    int updateSemester(Semester semester);

    /**
     * 清除所有学期的当前标记
     */
    int clearCurrentSemester();

    /**
     * 设置当前学期
     */
    int setCurrentSemester(Integer semesterId);

    /**
     * 更新选课时间
     */
    int updateSelectionTime(Semester semester);
}