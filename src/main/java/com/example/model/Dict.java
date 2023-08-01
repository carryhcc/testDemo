package com.example.model;

import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2021/12/16 09:34
 */
@Data
public class Dict {
    public Long id;
    public String dictKey;
    public String dictValue;
    public Integer status;
}
