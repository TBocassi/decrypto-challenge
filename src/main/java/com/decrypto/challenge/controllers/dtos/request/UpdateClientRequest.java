package com.decrypto.challenge.controllers.dtos.request;

import com.decrypto.challenge.utils.Messages;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateClientRequest {
  
  @NotNull(message = Messages.DESCRIPTION_NOT_NULL)
  @Size(min = 5 ,max = 50, message = Messages.DESCRIPTION_SIZE)
  @Pattern(regexp = ".*[a-zA-Z].*", message = Messages.DESCRIPTION_NOT_ALPHABETIC)
  @Schema(description = "client description", example = "Samsung")
  private String description;
}
