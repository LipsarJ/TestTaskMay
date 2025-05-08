package org.example.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dto.entity.ServiceName;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestSubscriptionDTO {
    private ServiceName serviceName;
}
