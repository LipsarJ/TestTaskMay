package org.example.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUserDTO {

    private Long id;
    private String username;
    private String email;
    private String firstname;
    private String middlename;
    private String lastname;
    private List<ResponseSubscriptionDTO> subscriptions;
}
