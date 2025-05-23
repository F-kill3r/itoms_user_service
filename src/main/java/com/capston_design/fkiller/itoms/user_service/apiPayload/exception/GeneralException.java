package com.capston_design.fkiller.itoms.user_service.apiPayload.exception;

import com.capston_design.fkiller.itoms.user_service.apiPayload.code.BaseErrorCode;
import com.capston_design.fkiller.itoms.user_service.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {

    private BaseErrorCode code;

    public ErrorReasonDTO getErrorReason() {
        return this.code.getReason();
    }

    public ErrorReasonDTO getErrorReasonHttpStatus(){
        return this.code.getReasonHttpStatus();
    }
}
