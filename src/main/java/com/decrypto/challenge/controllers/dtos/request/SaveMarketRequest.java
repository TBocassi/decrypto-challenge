package com.decrypto.challenge.controllers.dtos.request;

import com.decrypto.challenge.utils.Messages;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveMarketRequest {
  
  @NotNull(message = Messages.CODE_NOT_NULL)
  @Pattern(regexp = "^[A-Z0-9]{2,5}$", message = Messages.CODE_BAD_FORMAT)
  private String code;
  
  @NotNull(message = Messages.DESCRIPTION_NOT_NULL)
  @Size(min = 5 , message = Messages.DESCRIPTION_MIN_CHAR)
  @Pattern(regexp = ".*[a-zA-Z].*", message = Messages.DESCRIPTION_NOT_ALPHABETIC)
  private String description;
  
  @NotNull(message = Messages.COUNTRY_NOT_NULL)
  @Pattern(regexp = "^[a-zA-Z]+$", message = Messages.COUNTRY_NOT_ALPHABETIC)
  private String country;
}
