package com.sparta.dbdbdeep.repository;

import com.sparta.dbdbdeep.entity.Sns;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;

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







}
