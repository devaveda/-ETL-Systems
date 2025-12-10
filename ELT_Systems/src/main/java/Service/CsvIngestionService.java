package Service;

import Domain.RawCsvEvent;
import Repository.RawCsvEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CsvIngestionService {

    private final RawCsvEventRepository rawCsvEventRepository;

    public List<RawCsvEvent> fetchNewRows() {
        // TODO: read CSV file, maybe using OpenCSV
        // TODO: use Checkpoint (line number / last id) for incremental ingestion
        return List.of(); // placeholder
    }
}

