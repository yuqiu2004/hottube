package org.ht.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ht.model.dto.UserAuthDTO;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private boolean pass;
    private UserAuthDTO userAuthDTO;

    public static AuthResponse pass(UserAuthDTO userAuthDTO) {
        return AuthResponse.builder().pass(true).userAuthDTO(userAuthDTO).build();
    }

    public static AuthResponse reject() {
        return AuthResponse.builder().pass(false).userAuthDTO(null).build();
    }

}
