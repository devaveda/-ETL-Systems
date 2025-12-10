package Domain;


import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
    @Table(name = "etl_runs")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class EtlRun {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private Instant startedAt;
        private Instant finishedAt;
        private Long recordsProcessed;
        private String status;        // SUCCESS / FAILURE
        private String errorMessage;
    }


