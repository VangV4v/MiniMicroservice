package com.vang.kafkamessage.model;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class NotifyRegistrationAccountMessage implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String email;
}