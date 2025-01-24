package com.ecom.product_api.util;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StandardResponse {
    private int code;
    private Object data;
    private String message;


}