package com.sparta.dbdbdeep.dto;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class SnsRequestDto {
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

  public void uploadDate() {
    this.uploadDate = year + "-" + (month > 9 ? "" : "0") + month + "-" + day + " / "
        +(hour > 9 ? "" : "0")+ hour + ":" +(minute > 9 ? "" : "0")+ minute + ":" +(second > 9 ? "" : "0")+ second;
  }
}



