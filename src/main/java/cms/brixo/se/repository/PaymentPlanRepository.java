package cms.brixo.se.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cms.brixo.se.entity.PaymentPlan;
import org.springframework.stereotype.Repository;

/**
 * Jpa repository for PaymentPlan.
 * @author Parasuram
 * @since 15-01-2021
 */
@Repository
public interface PaymentPlanRepository extends JpaRepository<PaymentPlan, Long>{

	
}
