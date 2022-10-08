package com.example.kakfaexamplejava11.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Province {
    private Long provinceId;
    private String englishName;
    private String nepaliName;
    private String geoJson;
}
