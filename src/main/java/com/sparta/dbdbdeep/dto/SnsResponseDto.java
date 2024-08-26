package com.sparta.dbdbdeep.dto;

import com.sparta.dbdbdeep.entity.Sns;
import lombok.Getter;

@Getter
public class SnsResponseDto {
  private Long id;
  private String userId;
  private String userPassword;
  private String contents;
  private String uploadDate;

  public SnsResponseDto(Sns sns) {
    this.id = sns.getId();
    this.userId = sns.getUserId();
    this.contents = sns.getContents();
  }

  public SnsResponseDto(Long id, String userId, String userPassword, String contents, String uploadDate) {
    this.id = id;
    this.userId = userId;
    this.userPassword = userPassword;
    this.contents = contents;
    this.uploadDate = uploadDate;
  }
}
