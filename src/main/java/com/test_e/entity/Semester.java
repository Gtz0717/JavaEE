package com.test_e.entity;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Semester {
    private int semestor_id;
    private String semester_name;
    private Date start_data;
    private Date end_data;
    private String is_current;
    private Date course_select_start;
    private Date course_select_end;
}
