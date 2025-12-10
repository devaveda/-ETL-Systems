package Domain;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "raw_api_events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RawApiEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rawPayload;
    private Instant fetchedAt;
}
