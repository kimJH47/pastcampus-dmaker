package com.fastcampus.spring.dmaker.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum DMakerErrorCode {
    LEVEL_EXPERIENCE_YEARS_NOT_MATCHED("개발자 레벨과 연차가 맞지 않습니다"),
    NO_DEVELOPER("해당되는 개발자가 없습니다"),
    DUPLICATED_MEMBER_ID("MemberId 가 중복되는 개발자가 있습니다"),

    INTERNAL_SERVER_ERROR("서버에 오류가 발생했습니다"),
    INVALID_REQUEST("잘못된 요청입니다");
    final private String detailedMassage;
}
