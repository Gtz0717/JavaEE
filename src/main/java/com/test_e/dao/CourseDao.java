package com.test_e.dao;

import com.test_e.entity.Course;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CourseDao {
    /**
     * 新增课程
     */
    int insertCourse(Course course);

    /**
     * 更新课程信息
     */
    int updateCourse(Course course);

    /**
     * 删除课程
     */
    int deleteCourse(Integer courseId);

    /**
     * 根据ID查询课程
     */
    Course selectCourseById(Integer courseId);

    /**
     * 查询课程列表
     * @param semester 学期
     * @param keyword 搜索关键字(课程名称或代码)
     */
    List<Course> selectCourseList(@Param("semester") String semester,
                                  @Param("keyword") String keyword);

    /**
     * 查询教师教授的课程
     */
    List<Course> selectCoursesByTeacher(Integer teacherId);

    /**
     * 查询教师教授的课程
     */
    List<Course> selectCoursesByStudent(Integer teacherId);

    /**
     * 更新课程已选人数
     * @param courseId 课程ID
     * @param delta 变化量(+1增加，-1减少)
     */
    int updateSelectedCount(@Param("courseId") Integer courseId,
                            @Param("delta") int delta);

}