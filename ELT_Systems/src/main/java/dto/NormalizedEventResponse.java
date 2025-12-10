package dto;



import lombok.*;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NormalizedEventResponse {

    private Long id;
    private String source;
    private String externalId;
    private String type;
    private String payload;
    private Instant eventTime;
}

