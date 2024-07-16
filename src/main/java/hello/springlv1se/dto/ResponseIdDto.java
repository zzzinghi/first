package hello.springlv1se.dto;

import lombok.*;

@Getter
@Setter
@Builder
public class ResponseIdDto {

    private Long id;

    private String title;
    private String writer;
    private String password;
    private String comments;
    private String date;

}
