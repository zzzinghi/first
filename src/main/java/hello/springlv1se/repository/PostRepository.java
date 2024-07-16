package hello.springlv1se.repository;


import hello.springlv1se.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

//jpaRepository를 상속받는 postRepository 인터페이스를 만들어보자 ->
// japRepository를 상속받으면 기본적인 crud메서드를 바로 사용할 수 있다
public interface PostRepository extends JpaRepository<Post, Long> {
}
