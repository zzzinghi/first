package hello.springlv1se.service;

import hello.springlv1se.dto.RequestPostDto;
import hello.springlv1se.dto.RequestUpdatePostDto;
import hello.springlv1se.dto.ResponseIdDto;
import hello.springlv1se.entity.Post;
import hello.springlv1se.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PostService {
    //PostService클래스가 postRepository를 통해 데이터베이스 작업을 수행하기 위해서    -> PostRepository가 JpaRepository<Post, Long>를 상속받기 때문 ->PostRepository는 JpaRepository가 제공하는 모든 CRUD 기능을 상속
    private final PostRepository postRepository;

    //객체를 생성할 수 있는 빌더를 builder() 함수를 통해 얻고 거기에 셋팅하고자 하는 값을 셋팅하고 마지막에 build()를 통해 빌더를 작동시켜 객체를 생성한다.
    //write메서드 :  이 메서드는 requestdto 객체를 입력으로 받고, 새로운 게시글을 db에 저장하고, 저장된 게시글의 id를 포함한 responsesaveidDto 객체를 반환한다.
    //RequestDto 게시글 작성 요청 데이터를 담고 있는 dto, requestDto는 Dto의 인스턴싀로, 클라이언트가 보낸 데이터를 포함함..
    public ResponseIdDto write(RequestPostDto requestDto) {
        Post post = Post.builder()                      //post 객체를 빌더패턴을 사용하여 생성함
                .title(requestDto.getTitle())
                .writer(requestDto.getWriter())
                .password(requestDto.getPassword())
                .comments(requestDto.getComments())
                .date(LocalDateTime.now())
                .build();                               //post 객체를 생성하는 최종 메서드 마지막은 .build()

        Post savePost = postRepository.save(post);      //생성된 post 객체를 db에 저장하고, 저장되 결과를 savePost에 할당함.

        return ResponseIdDto.builder()
                .id(savePost.getId())
                .writer(savePost.getWriter())
                .title(savePost.getTitle())
//                .password(savePost.getPassword())
                .comments(savePost.getComments())
                .date(String.valueOf(savePost.getDate()))
                .build();
    }

    //게시글 꺼내오는 get 메서드 만들기
    public ResponseIdDto get(Long id) {         //어떤 게시글을 가져올지 식별위해 -> 가져오고 싶은 id를 받는다
        Post post = postRepository.findById(id) //postRepository의 findById 메서드를 사용해서 게시글을 가져온다
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다"));
                                                //JPA Repository에서 반환받은 Post는 optional 클래스로 감싸져있음.
                                                //optional로 감싸진 post entity를  orElseThrow 메서드를 통해서 해당 id에 대한 post가 없으면 RuntimeException을 뱉도록 작성함
        return ResponseIdDto.builder()          //받아온 post 데이터는 responsePostDto에 담아서 컨트롤러에 반환
                .id(post.getId())
                .writer(post.getWriter())
                .title(post.getTitle())
//                .password(post.getPassword())
                .comments(post.getComments())
                .date(String.valueOf(post.getDate()))
                .build();
    }

    //게시글 수정 //등록dto, 수정dto는 분리해서 사용하는게 좋음
    @Transactional
    public void edit(Long id, RequestUpdatePostDto requestDto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글 입니다."));
        post.update(requestDto); //update -> post entity 클래스에 setter를 사용하지 않고 객체 내부에서 데이터를 수정하도록 만든 메서드
    }

    //게시글 삭제
//    public void delete(Long id, String password) {
//        Post post = postRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글 입니다."));
//        postRepository.delete(post);
//    }

    public void delete(Long id, String password) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글 입니다."));

        if (password.equals(post.getPassword())) {
            postRepository.delete(post);
        } else {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }

    //게시물 목록 조회
    public List<Post> getPosts() {
        return postRepository.findAll();
    }
}
