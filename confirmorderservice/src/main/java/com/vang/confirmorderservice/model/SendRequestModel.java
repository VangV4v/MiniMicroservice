package com.vang.confirmorderservice.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class SendRequestModel implements Serializable {
    private String orderId;
}