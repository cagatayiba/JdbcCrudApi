package com.cagatayiba.coursePlatform.model;


import lombok.*;

@AllArgsConstructor
@ToString
@NoArgsConstructor
@Getter
@Setter
public class Course {
    private int id;
    private String title;
    private String description;
    private String url;

}
