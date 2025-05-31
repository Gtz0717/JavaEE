package com.test_e.service.impl;

import com.test_e.entity.Course;
import com.test_e.entity.Selection;
import com.test_e.dao.CourseDao;
import com.test_e.dao.SelectionDao;
import com.test_e.service.SelectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class SelectionServiceImpl implements SelectionService {

    @Autowired
    private SelectionDao selectionMapper;

    @Autowired
    private CourseDao courseMapper;

    @Override
    @Transactional
    public void selectCourse(Integer studentId, Integer courseId) {
        // 检查是否已选过该课程
        Selection existing = selectionMapper.getSelectionByStudentAndCourse(studentId, courseId);
        if (existing != null) {
            throw new RuntimeException("您已选过该课程");
        }

        // 检查课程容量
        Course course = courseMapper.selectCourseById(courseId);
        if (course.getSelected() >= course.getCapacity()) {
            throw new RuntimeException("课程已满");
        }

        // 创建选课记录
        Selection selection = new Selection();
        selection.setStudentId(studentId);
        selection.setCourseId(courseId);
        selection.setStatus("selected");
        selectionMapper.insertSelection(selection);

        // 更新课程已选人数
        courseMapper.updateSelectedCount(courseId, 1);
    }

    @Override
    @Transactional
    public void dropCourse(Integer studentId, Integer courseId) {
        // 获取选课记录
        Selection selection = selectionMapper.getSelectionByStudentAndCourse(studentId, courseId);
        if (selection == null) {
            throw new RuntimeException("未找到选课记录");
        }

        // 检查是否已结束的课程
        if ("finished".equals(selection.getStatus())) {
            throw new RuntimeException("已结束的课程不能退选");
        }

        // 更新选课状态
        selectionMapper.updateSelectionStatus(selection.getSelectionId(), "dropped");

        // 更新课程已选人数
        courseMapper.updateSelectedCount(courseId, -1);
    }

    @Override
    public List<Selection> getSelectionsByStudent(Integer studentId) {
        return selectionMapper.getSelectionsByStudent(studentId);
    }

    @Override
    @Transactional
    public void recordScore(Selection selection) {
        // 验证成绩范围
        if (selection.getScore() != null &&
                (selection.getScore() < 0 || selection.getScore() > 100)) {
            throw new RuntimeException("成绩必须在0-100之间");
        }

        selectionMapper.updateScore(selection.getSelectionId(),
                selection.getScore(),
                "finished");
    }

    @Override
    @Transactional
    public void evaluateCourse(Selection selection) {
        selectionMapper.updateEvaluation(selection.getSelectionId(),
                selection.getEvaluation());
    }

    @Override
    public boolean checkTeacherPermission(Integer selectionId, Integer teacherId) {
        Selection selection = selectionMapper.getSelectionById(selectionId);
        if (selection == null) {
            return false;
        }
        // 获取课程信息
        Course course = courseMapper.selectCourseById(selection.getCourseId());
        return course != null && course.getTeacherId().equals(teacherId);
    }

    @Override
    public boolean checkStudentPermission(Integer selectionId, Integer studentId) {
        Selection selection = selectionMapper.getSelectionById(selectionId);
        return selection != null && selection.getStudentId().equals(studentId);
    }






    @Override
    public List<Selection> getScoresByStudentId(Integer studentId, Integer semesterId) {
        return selectionMapper.selectByStudentIdAndSemester(studentId, semesterId);
    }

    @Override
    public List<Selection> getScoresByCourseId(Integer courseId) {
        return selectionMapper.selectByCourseId(courseId);
    }

    @Override
    public List<Selection> getScores(Integer semesterId, Integer studentId, Integer courseId) {
        return selectionMapper.selectByConditions(semesterId, studentId, courseId);
    }

    @Override
    public boolean updateScore(Integer selectionId, Double score) {
        Selection selection = new Selection();
        selection.setSelectionId(selectionId);
        selection.setScore(score);
        return selectionMapper.updateById(selection) > 0;
    }
}