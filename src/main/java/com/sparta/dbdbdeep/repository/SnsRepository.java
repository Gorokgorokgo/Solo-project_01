package com.sparta.dbdbdeep.repository;

import com.sparta.dbdbdeep.dto.SnsRequestDto;
import com.sparta.dbdbdeep.dto.SnsResponseDto;
import com.sparta.dbdbdeep.entity.Sns;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class SnsRepository {

  private final JdbcTemplate jdbcTemplate;

  public SnsRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public Sns save(Sns sns) {
    KeyHolder keyHolder = new GeneratedKeyHolder();

    String sql = "INSERT INTO sns (userId, userPassword, contents, uploadDate) VALUES (?,?,?,?)";
    jdbcTemplate.update(con -> {
          PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
          preparedStatement.setString(1, sns.getUserId());
          preparedStatement.setString(2, sns.getUserPassword());
          preparedStatement.setString(3, sns.getContents());
          preparedStatement.setString(4, sns.getUploadDate());
          return preparedStatement;
        },
        keyHolder);

    Long id = keyHolder.getKey().longValue();
    sns.setId(id);

    return sns;
  }

  public List<SnsResponseDto> findAll() {
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

  public void update(Long id, SnsRequestDto snsRequestDto) {
    String sql = "UPDATE sns Set userId = ?, userPassword = ?, contents = ?, uploadDate = ? WHERE id = ?";
    jdbcTemplate.update(sql, snsRequestDto.getUserId(),
        snsRequestDto.getUserPassword(), snsRequestDto.getContents(), snsRequestDto.getUploadDate(), id);
  }

  public void delete(Long id) {
    // feedList에 수정하고자 하는 feed가 존재하는지 확인

    String sql = "DELETE FROM sns WHERE id = ?";
    jdbcTemplate.update(sql, id);
  }


  public Sns findById(Long id) {
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
