package com.decrypto.challenge.controllers.dtos.request;

import com.decrypto.challenge.utils.Messages;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMarketRequest {

  @Pattern(regexp = "^[A-Z0-9]{2,5}$", message = Messages.CODE_BAD_FORMAT)
  private String code;

  @Size(min = 5 , message = Messages.DESCRIPTION_MIN_CHAR)
  @Pattern(regexp = ".*[a-zA-Z].*", message = Messages.DESCRIPTION_NOT_ALPHABETIC)
  private String description;

  @Pattern(regexp = "^[a-zA-Z\\s]+$", message = Messages.DESCRIPTION_NOT_ALPHABETIC)
  private String country;
}
