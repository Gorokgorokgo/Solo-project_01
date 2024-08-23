package com.sparta.dbdbdeep.controller;


import com.sparta.dbdbdeep.dto.SnsRequestDto;
import com.sparta.dbdbdeep.dto.SnsResponseDto;
import com.sparta.dbdbdeep.entity.Sns;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SnsController {

  private final Map<Long, Sns> feedList = new HashMap<>();

  @PostMapping("/sns")
  public SnsResponseDto upLoadFeed(@RequestBody SnsRequestDto snsRequestDto) {
    Sns sns = new Sns(snsRequestDto);
    // 고객정보 수신
    Long maxId = feedList.size() > 0 ? Collections.max(feedList.keySet()) + 1 : 1;
    sns.setId(maxId); // 내부 id 설정, 리스트  추가
    feedList.put(sns.getId(), sns); // map 형식이라 put으로 넣어줌

    SnsResponseDto snsResponseDto = new SnsResponseDto(sns);

    return snsResponseDto;
  }

  @GetMapping("/sns")
  public List<SnsResponseDto> getSns(@RequestBody SnsRequestDto snsRequestDto) {
    // snsList 가 map 타입이므로, List 타입으로 변환
    List<SnsResponseDto> reponseList = feedList.values().stream().map(SnsResponseDto::new).toList();

    return reponseList;
  }

  @PutMapping("/sns/{id}")
  public String updatesns(@PathVariable Long id, @RequestBody SnsRequestDto snsRequestDto) {
    // feedList에 수정하고자 하는 feed가 존재하는지 확인
    if (feedList.containsKey(id)) {
      Sns sns = feedList.get(id);
      // feed 수정
      sns.update(snsRequestDto);
      return id + "번 피드 수정 완료";
    } else {
      throw new IllegalArgumentException("해당 피드는 존재하지 않습니다.");
    }
  }

  @DeleteMapping("/sns/{id}")
  public String deleteSns(@PathVariable Long id) {
    // feedList에 수정하고자 하는 feed가 존재하는지 확인
    if (feedList.containsKey(id)) {
      feedList.remove(id);
      return id + "번 피드 삭제 완료";
    } else {
      throw new IllegalArgumentException("해당 피드는 존재하지 않습니다.");
    }
  }
}


