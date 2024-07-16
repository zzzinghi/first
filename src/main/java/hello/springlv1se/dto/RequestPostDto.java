package hello.springlv1se.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RequestPostDto {

    private String title;
    private String writer;
    private String password;
    private String comments;
    private String date;

}
