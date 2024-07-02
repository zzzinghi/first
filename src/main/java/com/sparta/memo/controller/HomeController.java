package com.sparta.memo.controller;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.ui.Model;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final MemoService memoService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("memos", memoService.getMemos());
        return "Main";
    }
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable(value = "id", required = true) Long id, Model model) {  //@PathVariable Long id 추가하니깐 id오류 사라짐
        MemoResponseDto memo = memoService.getMemoById(id);  // 특정 메모를 가져옴
        model.addAttribute("memo",memo); // 모델에 메모를 추가하여 뷰로 전달
        return "Detail";

    //    @GetMapping("/detail")
        //    public String detail() {
        //        return "Detail";
    }
    @GetMapping("/edit")
    public String edit() {
        return "Edit";
    }

    @GetMapping("/write")
    public String write() {
        return "Write";
    }

    @PostMapping("/write")
    public MemoResponseDto write(@RequestBody MemoRequestDto requestDto) {  // 입력받은 데이터를 처리하는 과정!
        System.out.println(requestDto);
        return memoService.createMemo(requestDto);
    }
}

// naver.com?id=dwadwa&&password=fnnww /->GET
// naver.com /->POST