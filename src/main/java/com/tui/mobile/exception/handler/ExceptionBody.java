package com.tui.mobile.exception.handler;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ExceptionBody {
    private Integer status;
    private String message;
}
