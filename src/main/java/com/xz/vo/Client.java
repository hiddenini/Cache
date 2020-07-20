package com.xz.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Client implements Serializable {
    private String appKey;

    private String appSecret;
}
