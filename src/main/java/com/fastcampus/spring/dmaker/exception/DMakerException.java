package com.fastcampus.spring.dmaker.exception;


import lombok.Getter;

@Getter
public class DMakerException extends RuntimeException {

    private DMakerErrorCode dMakerErrorCode;
    private String detailedMessage;

    public DMakerException(DMakerErrorCode dMakerErrorCode) {
        super(dMakerErrorCode.getDetailedMassage());
        this.dMakerErrorCode = dMakerErrorCode;
        this.detailedMessage = dMakerErrorCode.getDetailedMassage();
    }
    public DMakerException(DMakerErrorCode dMakerErrorCode, String detailedMessage) {
        super(detailedMessage);
        this.dMakerErrorCode = dMakerErrorCode;
        this.detailedMessage = detailedMessage;
    }
}
