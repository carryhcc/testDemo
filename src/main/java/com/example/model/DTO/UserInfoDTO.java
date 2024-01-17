package com.example.model.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2023/1/13 16:03
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserInfoDTO extends LimitDTO{
    String name;
    Long age;
}
