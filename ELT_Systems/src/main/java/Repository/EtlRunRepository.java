package Repository;



import Domain.EtlRun;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EtlRunRepository extends JpaRepository<EtlRun, Long> {

    Optional<EtlRun> findTopByOrderByStartedAtDesc();
}
