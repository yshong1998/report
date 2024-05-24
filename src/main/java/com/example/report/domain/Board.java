package com.example.report.domain;

import com.example.report.domain.dto.CreateBoardForm;
import com.example.report.domain.dto.UpdateBoardForm;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String title;
    private String content;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Board(CreateBoardForm createBoardForm){
        this.name = createBoardForm.getName();
        this.title = createBoardForm.getTitle();
        this.content = createBoardForm.getContent();
        this.password = createBoardForm.getPassword();
        this.createdAt = LocalDateTime.now();
    }

    public void update(UpdateBoardForm updateBoardForm){
        this.name = updateBoardForm.getName();
        this.title = updateBoardForm.getTitle();
        this.content = updateBoardForm.getContent();
        this.password = updateBoardForm.getPassword();
        this.updatedAt = LocalDateTime.now();
    }
}
