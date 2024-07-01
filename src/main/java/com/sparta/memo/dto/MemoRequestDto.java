package com.sparta.memo.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemoRequestDto {
    private String username;
    private String contents;
    private String password;
    private String title;
    private LocalDateTime date;
}