package org.vang.minimicroservice.model;

import lombok.Data;

@Data
public class MessageResponseModel {

    private boolean status;
    private String message;
}