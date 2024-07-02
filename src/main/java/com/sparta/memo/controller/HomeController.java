package com.sparta.memo.controller;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final MemoService memoService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("memos", memoService.getMemos()); //메모 다 불러옴
        return "Main";
    }
    @GetMapping("/detail/{id}")     //메모 목록 중 하나만 불러와야해서 getMemos 쓰면 안됨. -> getMemoById로 detail 하나만 불러와서 쓸 수 있음
    public String detail(@PathVariable(value = "id", required = true) Long id, Model model) {  //@PathVariable Long id 추가하니깐 id오류 사라짐
        MemoResponseDto memo = memoService.getMemoById(id);  // 특정 메모를 가져옴
        model.addAttribute("memo",memo); // 모델에 메모를 추가하여 뷰로 전달
        return "Detail";

    //    @GetMapping("/detail")
        //    public String detail() {
        //        return "Detail";
//    }
//    @GetMapping("/edit/{edit}")
//    public String edit(@PathVariable(value = "edit", required = true)Model model) {
//        MemoResponseDto memo = memoService.getMemoById(edit);
//        model.addAttribute("memo", memo);
//        return "Edit";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable(value = "id", required = true) Long id, Model model) {
        MemoResponseDto memo = memoService.getMemoById(id);
        model.addAttribute("memo",memo);
        return "Edit";
    }

    @ResponseBody
    @PostMapping("/edit/{id}")
    public MemoResponseDto edit(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
        return memoService.updateMemo(id,requestDto);
    }

//    @GetMapping("/")
//    public String edit(Model model) {
//    model.addAttribute("memos", memoService.getMemos());
//    return "Edit";
//}
    @GetMapping("/write")
    public String write() {
        return "Write";
    }


    @PostMapping("/write")
    public MemoResponseDto write(@RequestBody MemoRequestDto requestDto) {  // 입력받은 데이터를 처리하는 과정!
        System.out.println(requestDto);
        return memoService.createMemo(requestDto);
    }

    @GetMapping("/secret")
    public String secretPage() {
        return "secret"; // secret.html 파일과 매핑됨
    }



//    @DeleteMapping("/memos/{id}")
//    public Long deleteMemo(@PathVariable Long id) {
//        // 해당 메모가 DB에 존재하는지 확인
//        if(memoList.containsKey(id)) {
//            // 해당 메모 삭제하기
//            memoList.remove(id);
//            return id;
//        } else {
//            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
//        }
//    }
}

// naver.com?id=dwadwa&&password=fnnww /->GET
// naver.com /->POST