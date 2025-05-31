package com.test_e.dao;

import com.test_e.entity.Teaching_class;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TeachingClassMapper {
    /**
     * 插入教学班
     */
    int insertTeachingClass(Teaching_class teachingClass);

    /**
     * 更新教学班信息
     */
    int updateTeachingClass(Teaching_class teachingClass);

    /**
     * 根据ID查询教学班
     */
    Teaching_class selectTeachingClassById(Integer classId);

    /**
     * 根据课程和名称查询教学班
     */
    Teaching_class selectByCourseAndName(@Param("courseId") Integer courseId,
                                         @Param("className") String className);

    /**
     * 查询课程的教学班列表
     */
    List<Teaching_class> selectTeachingClassesByCourse(Integer courseId);

    /**
     * 查询教师的教学班列表
     */
    List<Teaching_class> selectTeachingClassesByTeacher(Integer teacherId);
}