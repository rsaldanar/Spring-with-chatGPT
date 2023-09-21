package com.example.springwithchatgpt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @project: Spring-with-chatGPT
 * @author: Rafael Saldana
 * @date: 9/20/2023
 * @email: rsaldanar@gmail.com
 * @description:
 * @history:
 */
@Data
@AllArgsConstructor
public class ResponseDTO<T> {

    private Integer code;
    private String message;
    private T data;

    public static <T> ResponseDTO<T> success(T data) {
        return new ResponseDTO<>(200, "success", data);
    }

    public static <T> ResponseDTO<T> fail(T data) {
        return new ResponseDTO<>(500, "fail", data);
    }

}
