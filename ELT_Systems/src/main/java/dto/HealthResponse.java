package dto;


import lombok.*;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HealthResponse {

    private String dbStatus;              // "UP" / "DOWN"
    private long recordsInNormalized;     // count(*)
    private String lastEtlRunStatus;      // SUCCESS / FAILURE / UNKNOWN
    private Instant lastEtlRunAt;         // timestamp of last ETL run
}

