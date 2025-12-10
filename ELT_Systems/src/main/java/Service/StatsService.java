package Service;



import Domain.EtlRun;
import Repository.EtlRunRepository;
import Repository.NormalizedEventRepository;



import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StatsService {

    private final EtlRunRepository etlRunRepository;
    private final NormalizedEventRepository normalizedEventRepository;

    public dto.StatsResponse buildStats() {

        // 1. Total normalized events in the DB
        long totalRecords = normalizedEventRepository.count();

        // 2. Load all ETL runs
        List<EtlRun> runs = etlRunRepository.findAll();

        // 3. Get last run (sorted by time)
        EtlRun lastRun = runs.stream()
                .sorted((a, b) -> b.getStartedAt().compareTo(a.getStartedAt()))
                .findFirst()
                .orElse(null);

        Instant lastRunAt = (lastRun != null) ? lastRun.getFinishedAt() : null;
        String lastRunStatus = (lastRun != null) ? lastRun.getStatus() : "UNKNOWN";

        // 4. Compute average records per run
        long avgPerRun = 0;
        if (!runs.isEmpty()) {
            long totalProcessed = runs.stream()
                    .mapToLong(r -> r.getRecordsProcessed() == null ? 0 : r.getRecordsProcessed())
                    .sum();

            avgPerRun = totalProcessed / runs.size();
        }

        // 5. Count records per source
        //    Execute SQL: SELECT source, COUNT(*) FROM normalized_events GROUP BY source
        Map<String, Long> recordsPerSource = new HashMap<>();
        normalizedEventRepository.findAll().forEach(ev ->
                recordsPerSource.merge(ev.getSource(), 1L, Long::sum)
        );

        // 6. Return DTO
        return dto.StatsResponse.builder()
                .totalProcessedAcrossRuns(totalRecords)
                .recordsPerSource(recordsPerSource)
                .lastRunAt(lastRunAt)
                .lastRunStatus(lastRunStatus)
                .avgRecordsPerRun(avgPerRun)
                .build();
    }
}

