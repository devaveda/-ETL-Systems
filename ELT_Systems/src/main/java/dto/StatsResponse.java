package dto;


import lombok.*;
import java.time.Instant;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatsResponse {

    private long totalProcessedAcrossRuns;

    private Map<String, Long> recordsPerSource;
    // Example: { "API": 1200, "CSV": 450 }

    private Instant lastRunAt;
    private String lastRunStatus;

    private long avgRecordsPerRun;
}

