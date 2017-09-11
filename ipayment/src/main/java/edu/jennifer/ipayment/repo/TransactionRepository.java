package edu.jennifer.ipayment.repo;

import edu.jennifer.ipayment.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

/**
 * @author Khalid Elshafie
 * @Created 9/11/17 2:14 PM.
 */
@Transactional
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Transaction findByReservationid(String reservationid);
}

