package Repository;



import Domain.Checkpoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CheckpointRepository extends JpaRepository<Checkpoint, Long> {

    Optional<Checkpoint> findBySourceName(String sourceName);
}
