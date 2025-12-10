package Domain;


import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
    @Table(name = "checkpoints")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class Checkpoint {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String sourceName;     // "API1", "CSV1"
        private String lastOffset;     // e.g. last id / timestamp
        private Instant lastRunAt;
    }

