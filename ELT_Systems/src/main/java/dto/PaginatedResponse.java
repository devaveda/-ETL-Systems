package dto;



import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaginatedResponse<T> {

    private String requestId;
    private long apiLatencyMs;

    private int page;
    private int size;
    private long totalElements;

    private List<T> content;
}

