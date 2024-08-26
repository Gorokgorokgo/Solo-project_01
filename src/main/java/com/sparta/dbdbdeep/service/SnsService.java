package com.sparta.dbdbdeep.service;

import com.sparta.dbdbdeep.dto.SnsRequestDto;
import com.sparta.dbdbdeep.dto.SnsResponseDto;
import com.sparta.dbdbdeep.entity.Sns;
import com.sparta.dbdbdeep.repository.SnsRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class SnsService {
  private final JdbcTemplate jdbcTemplate;

  public SnsService(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public SnsResponseDto upLoadFeed(@RequestBody SnsRequestDto snsRequestDto) {
    Sns sns = new Sns(snsRequestDto);
    // 고객정보 수신
    // Long maxId = feedList.size() > 0 ? Collections.max(feedList.keySet()) + 1 : 1;
    SnsRepository snsRepository = new SnsRepository(jdbcTemplate);
    snsRepository.save(sns);

    SnsResponseDto snsResponseDto = new SnsResponseDto(sns);

    return snsResponseDto;
  } #################################

  public List<SnsResponseDto> getSns() {
    // snsList 가 map 타입이므로, List 타입으로 변환
    String sql = "SELECT * FROM sns";

    return jdbcTemplate.query(sql, new RowMapper<SnsResponseDto>() {
      @Override
      public SnsResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String userId = rs.getString("userId");
        String userPassword = rs.getString("userPassword");
        String contents = rs.getString("contents");
        String uploadDate = rs.getString("uploadDate");
        return new SnsResponseDto(id, userId, userPassword, contents, uploadDate);
      }
    });
  }

  public String updateSns(@PathVariable Long id, @RequestBody SnsRequestDto snsRequestDto) {
    // feedList에 수정하고자 하는 feed가 존재하는지 확인
    Sns sns = findById(id);
    if (sns != null) {
      // feed 수정
      String sql = "UPDATE sns Set userId = ?, userPassword = ?, contents = ?, uploadDate = ? WHERE id = ?";
      jdbcTemplate.update(sql, sns.getUserId(), sns.getUserPassword(), sns.getContents(), sns.getUploadDate(), id);

      return id + "번 피드 수정 완료";
    } else {
      throw new IllegalArgumentException("해당 피드는 존재하지 않습니다.");
    }
  }

  public String deleteSns(@PathVariable Long id) {
    // feedList에 수정하고자 하는 feed가 존재하는지 확인
    Sns sns = findById(id);
    if (sns != null) {
      // feed 삭제
      String sql = "DELETE FROM sns WHERE id = ?";
      jdbcTemplate.update(sql, id);

      return id + "번 피드 삭제 완료";
    } else {
      throw new IllegalArgumentException("해당 피드는 존재하지 않습니다.");
    }
  }

  private Sns findById(Long id) {
    String sql = "SELECT * FROM sns WHERE id = ?";

    return jdbcTemplate.query(sql, resultSet -> {
      if (resultSet.next()) {
        Sns sns = new Sns();
        sns.setUserId(resultSet.getString("userId"));
        sns.setContents(resultSet.getString("contents"));
        sns.setUploadDate(resultSet.getString("uploadDate"));
        return sns;
      } else {
        return null;
      }
    }, id);
  }
}
