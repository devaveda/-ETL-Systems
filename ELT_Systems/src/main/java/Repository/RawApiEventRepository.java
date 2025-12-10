package Repository;


import Domain.RawApiEvent;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RawApiEventRepository extends JpaRepository<RawApiEvent, Long> {
}

