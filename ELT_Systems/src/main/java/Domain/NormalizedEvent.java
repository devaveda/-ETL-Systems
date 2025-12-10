package Domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "normalized_events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NormalizedEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String source;           // "API", "CSV", "API2", etc.
    private String externalId;       // id from source
    private Instant eventTime;       // unified timestamp
    private String type;             // e.g. "click", "view"
    private String payload;          // JSON/string payload

    private Instant ingestedAt;      // when ETL stored it
}
