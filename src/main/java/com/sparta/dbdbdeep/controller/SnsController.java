package com.sparta.dbdbdeep.controller;


import com.sparta.dbdbdeep.dto.SnsRequestDto;
import com.sparta.dbdbdeep.dto.SnsResponseDto;
import com.sparta.dbdbdeep.service.SnsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SnsController {

  private final SnsService snsService;

  public SnsController(SnsService snsService) {
    this.snsService = snsService;
  }

  @PostMapping("/sns")
  public SnsResponseDto upLoadFeed(@RequestBody SnsRequestDto snsRequestDto) {
    return snsService.upLoadFeed(snsRequestDto);
  }

  @GetMapping("/sns")
  public List<SnsResponseDto> getSns() {
    return snsService.getSns();
  }
  @GetMapping("/sns/{id}")
  public SnsResponseDto getOneSns(@PathVariable Long id) {
    return snsService.getOneSns(id);
  }
  @GetMapping("/sns/{Date}")
  public List<SnsResponseDto> getDateSns(@PathVariable String Date) {
    return snsService.getDateSns(Date);
  }

// ######## 3단계, 4단계 도저히 모르겠습니다..ㅜㅠㅜㅠㅜ########
  @PutMapping("/sns/{id}")
  public String updateSns(@PathVariable Long id, @RequestBody SnsRequestDto snsRequestDto) {
    return snsService.updateSns(id, snsRequestDto);
  }

  @DeleteMapping("/sns/{id}")
  public String deleteSns(@PathVariable Long id) {
    return snsService.deleteSns(id);
  }
}


