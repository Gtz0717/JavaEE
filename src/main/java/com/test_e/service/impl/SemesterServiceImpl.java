package com.test_e.service.impl;

import com.test_e.entity.Semester;
import com.test_e.dao.SemesterDao;
import com.test_e.service.SemesterService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class SemesterServiceImpl implements SemesterService {

    @Autowired
    private SemesterDao semesterMapper;

    @Override
    public List<Semester> getAllSemesters() {
        return semesterMapper.selectAllSemesters();
    }

    @Override
    public Semester getCurrentSemester() {
        return semesterMapper.selectCurrentSemester();
    }

    @Override
    @Transactional
    public void addSemester(Semester semester) {
        // 检查学期名称是否已存在
        Semester existing = semesterMapper.selectSemesterByName(semester.getSemester_name());
        if (existing != null) {
            throw new RuntimeException("学期名称已存在");
        }

        semesterMapper.insertSemester(semester);
    }

    @Override
    @Transactional
    public void updateSemester(Semester semester) {
        semesterMapper.updateSemester(semester);
    }

    @Override
    @Transactional
    public void setCurrentSemester(Integer semesterId) {
        // 先取消所有学期的当前标记
        semesterMapper.clearCurrentSemester();

        // 设置新的当前学期
        semesterMapper.setCurrentSemester(semesterId);
    }

    @Override
    @Transactional
    public void setSelectionTime(Integer semesterId, Date startTime, Date endTime) {
        // 验证时间格式和逻辑
        if (startTime.compareTo(endTime) >= 0) {
            throw new RuntimeException("选课开始时间必须早于结束时间");
        }

        Semester semester = new Semester();
        semester.setSemestor_id(semesterId);
        semester.setCourse_select_start(startTime);
        semester.setCourse_select_end(endTime);

        semesterMapper.updateSelectionTime(semester);
    }



}