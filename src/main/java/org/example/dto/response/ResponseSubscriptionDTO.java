package org.example.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dto.entity.ServiceName;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseSubscriptionDTO {
    private Long id;
    private ServiceName serviceName;
}
