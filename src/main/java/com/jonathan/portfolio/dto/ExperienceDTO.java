package com.jonathan.portfolio.dto;

import java.time.LocalDate;
import java.util.List;

public record ExperienceDTO(
        String id,
        String company,
        String jobTitle,
        String jobDescription,
        String contact,
        LocalDate startDate,
        LocalDate endDate,
        List<String> toolsId
) {
}
