package Controller;

import Domain.NormalizedEvent;
import Repository.NormalizedEventRepository;
import dto.NormalizedEventResponse;
import dto.PaginatedResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/data")
@RequiredArgsConstructor
public class DataController {

    private NormalizedEventRepository normalizedEventRepository;

    @GetMapping
    public PaginatedResponse<NormalizedEventResponse> getData(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(required = false) String source) {

        long start = System.currentTimeMillis();
        String requestId = UUID.randomUUID().toString();

        Pageable pageable = PageRequest.of(page, size);
        Page<NormalizedEvent> eventsPage;

        if (source != null) {
            eventsPage = normalizedEventRepository.findBySource(source,pageable);
        } else {
            eventsPage = normalizedEventRepository.findAll(pageable);
        }

        List<NormalizedEventResponse> content = eventsPage.getContent().stream()
                .map(e -> NormalizedEventResponse.builder()
                        .id(e.getId())
                        .source(e.getSource())
                        .externalId(e.getExternalId())
                        .type(e.getType())
                        .payload(e.getPayload())
                        .eventTime(e.getEventTime())
                        .build())
                .toList();

        long latencyMs = System.currentTimeMillis() - start;

        return PaginatedResponse.<NormalizedEventResponse>builder()
                .requestId(requestId)
                .apiLatencyMs(latencyMs)
                .page(page)
                .size(size)
                .totalElements(eventsPage.getTotalElements())
                .content(content)
                .build();
    }
}

