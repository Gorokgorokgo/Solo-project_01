package com.sparta.dbdbdeep.controller;


import com.sparta.dbdbdeep.dto.SnsRequestDto;
import com.sparta.dbdbdeep.dto.SnsResponseDto;
import com.sparta.dbdbdeep.service.SnsService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SnsController {

  private final JdbcTemplate jdbcTemplate;

  public SnsController(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  // private final Map<Long, Sns> feedList = new HashMap<>();

  @PostMapping("/sns")
  public SnsResponseDto upLoadFeed(@RequestBody SnsRequestDto snsRequestDto) {
    SnsService snsService = new SnsService(jdbcTemplate);
    return snsService.upLoadFeed(snsRequestDto);
  }

  @GetMapping("/sns")
  public List<SnsResponseDto> getSns() {
    SnsService snsService = new SnsService(jdbcTemplate);
    return snsService.getSns();
  }

  @PutMapping("/sns/{id}")
  public String updateSns(@PathVariable Long id, @RequestBody SnsRequestDto snsRequestDto) {
    SnsService snsService = new SnsService(jdbcTemplate);
    return snsService.updateSns(id, snsRequestDto);
  }

  @DeleteMapping("/sns/{id}")
  public String deleteSns(@PathVariable Long id) {
    SnsService snsService = new SnsService(jdbcTemplate);
    return snsService.deleteSns(id);
  }
}


