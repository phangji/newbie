package com.ssg.newbie.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserClubType {
    SMILE_CLUB("스마일 클럽 회원"),
    NORMAL("일반 회원");

    private final String description;
}