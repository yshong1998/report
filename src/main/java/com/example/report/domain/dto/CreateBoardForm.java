package com.example.report.domain.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBoardForm {

    private String name;
    private String title;
    private String content;
    private String password;

}
