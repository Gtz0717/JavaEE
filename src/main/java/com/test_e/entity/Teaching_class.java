package com.test_e.entity;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Teaching_class {
    private int class_id;
    private String class_name;
    private int course_id;
    private int teacher_id;
    private String class_time;
    private String classroom;
    private int capacity;
    private int selected;

}
