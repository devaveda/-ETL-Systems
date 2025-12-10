package Service;

import Domain.EtlRun;
import Repository.EtlRunRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class EtlService {

    private final ApiIngestionService apiIngestionService;
    private final CsvIngestionService csvIngestionService;
    private final TransformationService transformationService;
    private final EtlRunRepository etlRunRepository;

    // you can also schedule this
    // @Scheduled(fixedDelayString = "60000")
    public void runFullIngestion() {
        EtlRun run = EtlRun.builder()
                .startedAt(Instant.now())
                .status("RUNNING")
                .build();
        run = etlRunRepository.save(run);

        long totalProcessed = 0L;

        try {
            var apiEvents = apiIngestionService.fetchNewEvents();
            transformationService.transformAndLoadFromApi(apiEvents);
            totalProcessed += apiEvents.size();

            var csvEvents = csvIngestionService.fetchNewRows();
            transformationService.transformAndLoadFromCsv(csvEvents);
            totalProcessed += csvEvents.size();

            run.setStatus("SUCCESS");
            run.setRecordsProcessed(totalProcessed);
        } catch (Exception ex) {
            run.setStatus("FAILURE");
            run.setErrorMessage(ex.getMessage());
        } finally {
            run.setFinishedAt(Instant.now());
            etlRunRepository.save(run);
        }
    }
}

