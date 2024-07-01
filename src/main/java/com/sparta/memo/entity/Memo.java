package com.sparta.memo.entity;

import com.sparta.memo.dto.MemoRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Memo {                 //지금은 기본 생성자만 있음.
    private Long id;                //이 ID 값으로 Memo를 구분할 건데, 중복이 되면 안됨
    private String username;
    private String contents;
    private String password;
    private String title;
    private LocalDateTime date;    //

    public Memo(MemoRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.password = requestDto.getPassword();
        this.title = requestDto.getTitle();
        this.date = requestDto.getDate();

//requestDto에서 get 메서드를 사용해서, 유저이름과 컨탠츠를 가지고 와서
// 메모 클래스의 유저네임,컨탠츠 필드에 데이터를 넣어주면서 메모 객체를 만들어내는 그러한 생성자를 만들어냄
//이렇게 하면, RequestDto - > Entitu 로 바꾸는 코드를 완성한것!
    }

    public void update(MemoRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.password = requestDto.getPassword();
        this.title = requestDto.getTitle();
        this.date = requestDto.getDate();
    }
}