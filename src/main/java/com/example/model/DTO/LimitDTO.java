package com.example.model.DTO;

import lombok.Data;


/**
 * The LimitDTO class represents the pagination limit for a data request.
 * It contains the page number and the size of each page.
 */
@Data
public class LimitDTO {
    Integer page ;
    Integer size ;
}
