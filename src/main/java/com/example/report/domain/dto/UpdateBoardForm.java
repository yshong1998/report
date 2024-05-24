package com.example.report.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateBoardForm {
    private Long id;
    private String name;
    private String title;
    private String content;
    private String password;

    public UpdateBoardForm(Long id, String name, String title, String content){
        this.id = id;
        this.name = name;
        this.title = title;
        this.content = content;
    }
}
