package com.labs.katandev.domain.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class ResponseDTO<T> {

    private boolean status;

    private List<String> messages = new ArrayList<String>();

    private T payload;
}
