package Service;

import Domain.RawApiEvent;
import Domain.RawCsvEvent;
import Repository.NormalizedEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransformationService {

    private final NormalizedEventRepository normalizedEventRepository;

    public void transformAndLoadFromApi(List<RawApiEvent> rawApiEvents) {
        // TODO: map each RawApiEvent.rawPayload -> NormalizedEvent
    }

    public void transformAndLoadFromCsv(List<RawCsvEvent> rawCsvEvents) {
        // TODO: map CSV rows -> NormalizedEvent
    }
}

