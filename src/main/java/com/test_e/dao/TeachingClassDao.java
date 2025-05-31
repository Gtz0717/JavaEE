package com.test_e.dao;

import com.test_e.entity.Teaching_class;
import java.util.List;

public interface TeachingClassDao {
    int insertTeachingClass(Teaching_class teachingClass);
    List<Teaching_class> getClassesByTeacher(int teacherId);
    int addStudentToClass(int classId, int studentId);
}