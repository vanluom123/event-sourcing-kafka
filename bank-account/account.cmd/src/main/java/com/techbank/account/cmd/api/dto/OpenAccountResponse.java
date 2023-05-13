package com.techbank.account.cmd.api.dto;

import com.techbank.account.common.dto.BaseResponse;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpenAccountResponse extends BaseResponse {
    private String id;

    public OpenAccountResponse(String message, String id) {
        super(message);
        this.id = id;
    }
}
