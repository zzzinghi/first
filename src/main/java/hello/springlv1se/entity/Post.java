package hello.springlv1se.entity;

import hello.springlv1se.dto.RequestPostDto;
import hello.springlv1se.dto.RequestUpdatePostDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post")   //테이블과 매핑 // 엔티티는 클래스 이름 PostEntity 을 기반으로 테이블 찾음// 벗 하이버네이트는 데이터 테이블 없으면 자동 생성
@Builder
@Getter //getId() 메서드를 찾을 수 없다는 오류
public class Post {
    @Id //id만 사용시 직접 할당     //@Id와 @GeneratedValue를 같이 사용 시 자동 생성
    @GeneratedValue(strategy = GenerationType.IDENTITY) //데이터베이스가 키 값을 정하게 하도록 시킴
    private Long id;
    @Column(name = "title", nullable = false, length = 50)  //공백은 문자열에 속해서 null이 아님 //nullable =null이 아님
    private String title;
    @Column(name = "writer", nullable = false, length = 20)
    private String writer;
    @Column(name = "password", nullable = false, length = 20)
    private String password;
    @Column(name = "comments", nullable = false, length = 500)
    private String comments;

//    @CreatedDate //데이터베이스 넣는 시간에 맞춰서 반환해줌
    @Column(name = "date")
    private LocalDateTime date;


    //요청만 하면 되니깐?! // 생성자를 통해 dto로부터 데이터를 받아올 수 있음

    public Post(RequestPostDto requestPostDto) {        //클래스를 생성할 때 기본 생성자도 생성되는데, 하나의 생성자라도 내가 따로 생성한다면 그 기본 생성자가 없어지기 때문에 기본 생성자를 생성해주는 어노테이션을 써야돼
        this.title = requestPostDto.getTitle();
        this.writer = requestPostDto.getWriter();
        this.password = requestPostDto.getPassword();
        this.comments = requestPostDto.getComments();
        this.date = LocalDateTime.now();                //@CreatedDate 있으면 없어도 돼

    }
    public void update(RequestUpdatePostDto requestDto) {
        this.title = requestDto.getTitle();
        this.writer = requestDto.getWriter();
        this.password = requestDto.getPassword();
        this.comments = requestDto.getComments();
        this.date = LocalDateTime.now();
    }
}
