package com.sparta.dbdbdeep.service;

import com.sparta.dbdbdeep.dto.SnsRequestDto;
import com.sparta.dbdbdeep.dto.SnsResponseDto;
import com.sparta.dbdbdeep.entity.Sns;
import com.sparta.dbdbdeep.repository.SnsRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class SnsService {
  private final SnsRepository snsRepository;

  public SnsService(JdbcTemplate jdbcTemplate) {
    this.snsRepository = new SnsRepository(jdbcTemplate);
  }

  public SnsResponseDto upLoadFeed(SnsRequestDto snsRequestDto) {
    Sns sns = new Sns(snsRequestDto);
    // 고객정보 수신
    snsRepository.save(sns);

    SnsResponseDto snsResponseDto = new SnsResponseDto(sns);

    return snsResponseDto;
  }

  public List<SnsResponseDto> getSns() {
    // snsList 가 map 타입이므로, List 타입으로 변환
    return snsRepository.findAll();
  }

  public String updateSns(Long id, SnsRequestDto snsRequestDto) {
    // feedList에 수정하고자 하는 feed가 존재하는지 확인

    Sns sns = snsRepository.findById(id);

    if (sns != null) {
      // feed 수정

      snsRepository.update(id, snsRequestDto);

      return id + "번 피드 수정 완료";
    } else {
      throw new IllegalArgumentException("해당 피드는 존재하지 않습니다.");
    }
  }

  public String deleteSns(Long id) {
    // feedList에 수정하고자 하는 feed가 존재하는지 확인

    Sns sns = snsRepository.findById(id);

    if (sns != null) {
      // feed 삭제
      snsRepository.delete(id);

      return id + "번 피드 삭제 완료";
    } else {
      throw new IllegalArgumentException("해당 피드는 존재하지 않습니다.");
    }
  }
}
