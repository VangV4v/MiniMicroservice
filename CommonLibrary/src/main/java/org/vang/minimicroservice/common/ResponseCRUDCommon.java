package org.vang.minimicroservice.common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
@Builder
public class ResponseCRUDCommon implements Serializable {
    private String message;
    private boolean errorStatus;
    private Set<String> errorMessage;
}