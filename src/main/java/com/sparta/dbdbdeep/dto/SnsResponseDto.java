package com.sparta.dbdbdeep.dto;

import com.sparta.dbdbdeep.entity.Sns;
import lombok.Getter;

@Getter
public class SnsResponseDto {
  private Long id;
  private String userId;
  private String contents;

  public SnsResponseDto(Sns sns) {
    this.id = sns.getId();
    this.userId = sns.getUserId();
    this.contents = sns.getContents();
  }
}
