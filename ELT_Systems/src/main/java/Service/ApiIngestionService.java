package Service;


import Domain.RawApiEvent;
import Repository.RawApiEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApiIngestionService {

    private final RawApiEventRepository rawApiEventRepository;
    // private final RestTemplate or WebClient here

    public List<RawApiEvent> fetchNewEvents() {
        // TODO: use Checkpoint to fetch only incremental data
        // Call external API, parse response, map to RawApiEvent list
        RawApiEvent dummy = RawApiEvent.builder()
                .rawPayload("{\"example\":\"data\"}")
                .fetchedAt(Instant.now())
                .build();

        return rawApiEventRepository.saveAll(List.of(dummy));
    }
}

