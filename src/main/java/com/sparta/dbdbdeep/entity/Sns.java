package com.sparta.dbdbdeep.entity;


import com.sparta.dbdbdeep.dto.SnsRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
// (access = AccessLevel.PROTECTED) 무분별한 객체 생성을 막는다
public class Sns {
  private Long id;
  private String userId;
  private String password;
  private String contents;

  public Sns(SnsRequestDto snsRequestDto) {
    this.userId = snsRequestDto.getUserId();
    this.password = snsRequestDto.getPassword();
    this.contents = snsRequestDto.getContents();
  }

  public void update(SnsRequestDto snsRequestDto) {
    this.contents = snsRequestDto.getContents();
  }
}
