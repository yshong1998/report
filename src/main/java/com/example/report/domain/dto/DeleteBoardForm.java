package com.example.report.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DeleteBoardForm {
    private Long id;
    private String password;

    public DeleteBoardForm(Long id){
        this.id = id;
    }
}
