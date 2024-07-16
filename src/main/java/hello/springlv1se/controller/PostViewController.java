package hello.springlv1se.controller;

import hello.springlv1se.dto.RequestPostDto;
import hello.springlv1se.entity.Post;
import hello.springlv1se.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class PostViewController {


    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/post/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "viewPost";
    }

    @GetMapping("/newPost")
    public String newPost() {
        return "newPost";
    }

    @GetMapping("/editPost/{id}")
    public String editPost(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "editPost";
    }

}
