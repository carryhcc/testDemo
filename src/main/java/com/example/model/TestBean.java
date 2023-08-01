package com.example.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 *
 * @author : cchu
 * Date: 2021/11/10 10:44
 */
@Data
public class TestBean {
    private String name;
    private int age;
    private double score;
    private boolean isPass;
    private Date examDate;

}
