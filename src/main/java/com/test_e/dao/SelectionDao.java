package com.test_e.dao;

import com.test_e.entity.Selection;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface SelectionDao {
    /**
     * 插入选课记录
     */
    int insertSelection(Selection selection);

    /**
     * 根据学生和课程获取选课记录
     */
    Selection getSelectionByStudentAndCourse(@Param("studentId") Integer studentId,
                                             @Param("courseId") Integer courseId);

    /**
     * 获取学生的选课列表
     */
    List<Selection> getSelectionsByStudent(Integer studentId);

    /**
     * 更新选课状态
     */
    int updateSelectionStatus(@Param("selectionId") Integer selectionId,
                              @Param("status") String status);

    /**
     * 更新成绩和状态
     */
    int updateScore(@Param("selectionId") Integer selectionId,
                    @Param("score") Double score,
                    @Param("status") String status);

    /**
     * 更新课程评价
     */
    int updateEvaluation(@Param("selectionId") Integer selectionId,
                         @Param("evaluation") String evaluation);

    /**
     * 根据ID获取选课记录
     */
    Selection getSelectionById(Integer selectionId);

    /**
     * 更新课程已选人数
     * @param courseId 课程ID
     * @param delta 变化量(+1增加，-1减少)
     */
    int updateSelectedCount(@Param("courseId") Integer courseId,
                            @Param("delta") int delta);



    List<Selection> selectByStudentIdAndSemester(@Param("studentId") Integer studentId,
                                                 @Param("semesterId") Integer semesterId);

    List<Selection> selectByCourseId(@Param("courseId") Integer courseId);

    List<Selection> selectByConditions(@Param("semesterId") Integer semesterId,
                                       @Param("studentId") Integer studentId,
                                       @Param("courseId") Integer courseId);

    int updateById(Selection selection);
}