package Repository;




import Domain.NormalizedEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;

public interface NormalizedEventRepository extends JpaRepository<NormalizedEvent, Long> {

    Page<NormalizedEvent> findBySource(String source, Pageable pageable);

    Page<NormalizedEvent> findBySourceAndEventTimeBetween(
            String source,
            Instant from,
            Instant to,
            Pageable pageable
    );
}

