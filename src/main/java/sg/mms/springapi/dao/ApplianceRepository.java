package sg.mms.springapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sg.mms.springapi.model.Appliance;

@Repository
public interface ApplianceRepository extends JpaRepository<Appliance, Integer> {
}
