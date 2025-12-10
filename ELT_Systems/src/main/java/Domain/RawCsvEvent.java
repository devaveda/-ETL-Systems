package Domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "raw_csv_events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RawCsvEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String rawPayload;

    private Instant ingestedAt;
}

