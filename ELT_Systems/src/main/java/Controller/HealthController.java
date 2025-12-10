package Controller;

import Domain.EtlRun;
import Repository.EtlRunRepository;
import Repository.NormalizedEventRepository;
import dto.HealthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
@RequiredArgsConstructor
public class HealthController {

    private final NormalizedEventRepository normalizedEventRepository;
    private final EtlRunRepository etlRunRepository;

    @GetMapping
    public HealthResponse health() {
        // DB check
        long count = normalizedEventRepository.count();

        EtlRun lastRun = etlRunRepository.findTopByOrderByStartedAtDesc()
                .orElse(null);

        return HealthResponse.builder()
                .dbStatus("UP")
                .recordsInNormalized(count)
                .lastEtlRunStatus(lastRun != null ? lastRun.getStatus() : "UNKNOWN")
                .lastEtlRunAt(lastRun != null ? lastRun.getFinishedAt() : null)
                .build();
    }
}

