package com.test_e.entity;
import lombok.*;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Selection {
    private Integer selectionId;
    private Integer studentId;
    private Integer courseId;
    private Double score;
    private Date selectionTime;
    private String status;
    private String evaluation;
    private Date evaluationTime;

    // Getter/Setter 方法省略
}