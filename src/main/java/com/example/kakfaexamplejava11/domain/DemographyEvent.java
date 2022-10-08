package com.example.kakfaexamplejava11.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DemographyEvent {
    private Long id;
    private EventType eventType;
    private Object object;
}
