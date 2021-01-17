package cms.brixo.se.repository;

import cms.brixo.se.entity.Debtor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Jpa repository for Debtor.
 * @author Parasuram
 * @since 15-01-2021
 */
@Repository
public interface DebtorRepository extends JpaRepository<Debtor, Integer> {


}
