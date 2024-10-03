package com.decrypto.challenge.controllers.dtos.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatsResponse {

    @Schema(description = "country stats list")
    List<CountryStats> countryStats;
}
