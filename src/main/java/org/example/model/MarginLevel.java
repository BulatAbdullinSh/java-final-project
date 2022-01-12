package org.example.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class MarginLevel {
    private double marginLevel = 0.015;
}
