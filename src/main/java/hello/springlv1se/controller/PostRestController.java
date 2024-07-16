package hello.springlv1se.controller;

import hello.springlv1se.dto.RequestPostDto;
import hello.springlv1se.dto.RequestUpdatePostDto;
import hello.springlv1se.dto.ResponseIdDto;
import hello.springlv1se.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostRestController {

    private final PostService postService;   // 의존성을 주입함으로써 사용함?!


    @PostMapping
    public ResponseEntity<ResponseIdDto> registerPost(@RequestBody RequestPostDto requestDto) {
        ResponseIdDto responseIdDto = postService.write(requestDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseIdDto.getId())
                .toUri();
        return ResponseEntity.created(location).body(responseIdDto);
    }

    //게시글 '조회' 요청에 응답하는 메소드
    //데이터를 조회하는 요청이기 때문에 @GetMapping을 사용해서 GET 요청을 받아준다.
    //@PathVariable을 사용해서 id를 받아준다
    //그 후에 postService의 get 메서드를 호출해주면 해당 게시글의 제목,내용,작성일 등을 담은 dto를 받을 수 있다
    //ResponseEntity에 200 ok 상태와 dto를 반환하면 끝이다.
    @GetMapping("/{id}")
    public ResponseEntity<ResponseIdDto> getPost(@PathVariable Long id) {
        ResponseIdDto responseIdDto = postService.get(id);

        return ResponseEntity.ok(responseIdDto);
        //http 응답을 생성하는 방법 중 하나, 클라이언트에게 http 200 ok 상태 코드를 반환하면서 응답 본문에 responseIdDto 객체를 포함하는 것을 의미함.
    }

    //postService의 edit 메서드를 호출하고, 응답을 내려줌
    @PutMapping("/{id}")        //Body를 RequestUpdatePostDto객체로 매핑한다. 이를 통해 클라이언트가 보낸 json 데이터를 자바 객체로 변환한다
    public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody RequestUpdatePostDto requestUpdatePostDto) {
        postService.edit(id, requestUpdatePostDto); //postService의 edit 메서드를 호출함. -> edit 메서드는 주어진 id와 requestUpdatePostDto객체를 받아 게시글을 수정하는 로직을 포함

        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id,@RequestParam String password) {  //@RequestParam 추가함
        postService.delete(id, password);

        return ResponseEntity.noContent().build();  //본문 생략하고, 클라이언트에게 요청의 성공 여부만을 전달함.
    }
//    // 게시물 전체 목록 조회 - JSON 응답
//    @GetMapping("/posts")
//    public ResponseEntity<List<Post>> getAllPosts() {
//        List<Post> posts = postService.getPosts();
//        return ResponseEntity.ok(posts);
//    }
}
