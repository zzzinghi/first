package com.sparta.memo.dto;

import com.sparta.memo.entity.Memo;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemoResponseDto {
    private Long id;
    private String username;
    private String contents;
    private String password;
    private String title;
    private LocalDateTime date;


    public MemoResponseDto(Memo memo) {
        this.id = memo.getId();
        this.username = memo.getUsername();
        this.contents = memo.getContents();
        this.password = memo.getPassword();
        this.title = memo.getTitle();
        this.date = memo.getDate();
    }

    public MemoResponseDto(Long id, String username, String contents, String password, String title, LocalDateTime date) {
        this.id = id;
        this.username = username;
        this.contents = contents;
        this.password = password;
        this.title = title;
        this.date = date;

    }

}