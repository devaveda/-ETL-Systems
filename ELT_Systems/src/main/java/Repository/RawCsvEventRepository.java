package Repository;



import Domain.RawCsvEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RawCsvEventRepository extends JpaRepository<RawCsvEvent, Long> {
}

