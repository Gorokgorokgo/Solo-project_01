package com.sparta.dbdbdeep.entity;


import com.sparta.dbdbdeep.dto.SnsRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
// (access = AccessLevel.PROTECTED) 무분별한 객체 생성을 막는다
public class Sns {
  private Long id;
  private String userId;
  private String userPassword;
  private String contents;
  private String uploadDate;
  private int year = LocalDate.now().getYear();
  private int month = LocalDate.now().getMonthValue();
  private int day = LocalDate.now().getDayOfMonth();
  private int hour = LocalTime.now().getHour();
  private int minute = LocalTime.now().getMinute();
  private int second = LocalTime.now().getSecond();

  public Sns(SnsRequestDto snsRequestDto) {
    this.userId = snsRequestDto.getUserId();
    this.userPassword = snsRequestDto.getUserPassword();
    this.contents = snsRequestDto.getContents();
    this.uploadDate = year + "-" + (month > 9 ? "" : "0") + month + "-" + day + " / "
        +(hour > 9 ? "" : "0")+ hour + ":" +(minute > 9 ? "" : "0")+ minute + ":" +(second > 9 ? "" : "0")+ second;
  }

  public void update(SnsRequestDto snsRequestDto) {
    this.contents = snsRequestDto.getContents();
  }

  public void LocalDate() {

  }
}
