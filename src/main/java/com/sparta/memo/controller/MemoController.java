package com.sparta.memo.controller;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.entity.Memo;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MemoController {

//데이터베이스를 연결도 하지 않았기 때문에 자바의 컬렉션을 그중에서 맵 자료구조를 사용해서 만들어보자!
    private final Map<Long, Memo> memoList = new HashMap<>();


    @PostMapping("/memos")
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto) {
        //RequestDto - > Entitu 로 수정해야함 (데이트베이스로 저장해야해서) entity는 데이터베이스와 소통하는 클래스
        Memo memo = new Memo(requestDto);

        //Memo Max ID 를 찾아야함
        //현재 데이터베이스에 가장 마지막 값을 구해서 거기에 +1을 하면 max id를 만들어낼 수 있음
        Long maxId = memoList.size() > 0 ? Collections.max(memoList.keySet()) + 1 : 1;
        //(삼항연산자) 메모리스트.키샛 메서드를 호출하면, Long 값을 가져온다. . 그 중 가장 큰 값 max
        //만약 1,2,3이면 가장 max 값 3이 반환됨.
        //중복이 되면 안되니깐 + 1을 해준다.
        //메모의 최대값을 구할 수 있음.
        //이제 데이터베이스에 저장해야됨.
        memo.setId(maxId);      //메모에 샛 메서드 사용해서 아이디에 멕스아이디 넣어줌.
        //DB 저장
        memoList.put(memo.getId(), memo);   //데이터베이스를 맵 자료구조로 사용할 거니깐 put 메서드 사용해서 넣어주면 됨.
        //키 부분에는 메모의 아이디를 넣기로 함. 그리고 벨류에는 메모 객체를 넣어줌.

        //Entity -> ResponseDto
        MemoResponseDto memoResponseDto = new MemoResponseDto(memo);

        return memoResponseDto;
        //creatMemo 메서드가 만들어졌다. -> 이제 api 하나가 만들어진것! (메모 생성하기 api)

    }

    //조회하는 api
    @GetMapping("/memos")
    public List<MemoResponseDto> getMemos() { //왜 List냐면, MemoResponseDto는 메모가 하나임, 근데 메모는 여러개니깐, 리스트 형식으로 반환해줘야함.
        // Map To List
        List<MemoResponseDto> responseList = memoList.values().stream()
                .map(MemoResponseDto::new).toList();        // .toList하면 List로 변환됨.
        //벨류스하면 Memo memo = new Memo(requestDto); 여기 안에 있는 메모를 다 가지고 온다.

        return responseList;
    }

    @PutMapping("/memos/{id}") //반환은 업데이트 한 아이디만 넘겨줄거라 long 타입
    public Long updateMemo(@PathVariable Long id,@RequestBody MemoRequestDto requestDto) {
        //우리가 메모를 수정하려면, 그 메모가 데이터베이스에 존재하는 지 확인하는게 안전함.
        //해당 메모가 DB에 존재하는지 확인
        if(memoList.containsKey(id)) {
            //해당 메모를 가져오기
            Memo memo = memoList.get(id);

            //memo 수정 //update라는 메서드를 만들거고, ()안에다가 수정할 내용을 파라미터로 전달해 줘야함
            memo.update(requestDto);
            return memo.getId();
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    //데이터를 수정한다는 건 데이터의 내용, 컨탠츠도 같이 넘어옴 그 데이터 내용은 클라이언트 바디부분에서 넘어온다
    //Body에 JSON 형식으로 넘어온다 @RequestBody
    //업데이트 할 메모의 아이디 받아오고(@PathVariable)이걸로, 수정할 내용은 (@RequestBody)로 받아온다
    }

    @DeleteMapping("/memos/{id}")
    public Long deleteMemo(@PathVariable Long id) {     //지우기만 하면 되니깐 id만 받는다
        //해당 메모가  DB에 존재하는지 확인
        if(memoList.containsKey(id)) {
            // 해당 메모를 삭제하기
            memoList.remove(id);
            return id;
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }
}
