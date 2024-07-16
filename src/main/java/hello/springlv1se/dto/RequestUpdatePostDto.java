package hello.springlv1se.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter //해당 클래스의 필드를 외부에서 읽을 수 있게 해줌
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestUpdatePostDto {

    private String title;
    private String writer;
    private String password;
    private String comments;
    private String date;
}
