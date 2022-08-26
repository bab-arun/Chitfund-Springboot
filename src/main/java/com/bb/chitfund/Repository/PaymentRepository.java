package com.bb.chitfund.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bb.chitfund.Entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

	@Query(value = "select * from chitfund.payment where user_id=:userId and scheme_id=:schemeId and installment_date like :cutDate% and status=:status", nativeQuery = true)
	Payment findPaymentDetails(int userId, int schemeId, String cutDate, String status);

	@Query(value = "select * from payment where user_id=:userId and scheme_id=:schemeId and  status=:status", nativeQuery = true)
	Payment findPaymentRecord(int userId, int schemeId, String status);

	@Query("From Payment where status=:status")
	List<Payment> findAcceptPaymentList(String status);

	@Query(value = "SELECT s.scheme_name,s.start_date,s.end_date,s.scheme_duration,s.number_of_user,Count(p.status) as pending_payment,s.id FROM chitfund.payment p,chitfund.scheme s  where s.id=p.scheme_id and p.installment_Date<=:currentDate and status=:status GROUP BY s.scheme_name ", nativeQuery = true)
	List<Object[]> schemePendingPaymentList(String status, Date currentDate);

	@Query(value = "select u.id,u.user_code,u.user_name,u.mobile,count(p.status) as pending_payment_count from chitfund.payment p,chitfund.user u where p.scheme_id=:schemeId and u.id=p.user_id  and p.installment_Date<=:currentDate and p.status=:status group by p.scheme_id ,p.user_id", nativeQuery = true)
	List<Object[]> getschemeUserPendingPaymentList(long schemeId, Date currentDate, String status);

	@Query(value = "select installment_date ,status,paid_date from chitfund.payment where user_id=:userId and scheme_id=:schemeId and installment_date<=:currentDate", nativeQuery = true)
	List<Object[]> getUserMonthlyPendingpay(long userId, long schemeId, Date currentDate);

	@Query(value = "SELECT * FROM chitfund.payment where  user_id=:userId and installment_date<= :currentDate  and  status=:status", nativeQuery = true)
	List<Payment> getlist(int userId, Date currentDate, String status);

	@Query(value = "SELECT * FROM chitfund.payment where  user_id=:userId and scheme_id=:schemeId and installment_date<=:currentDate  and  status=:status", nativeQuery = true)
	List<Payment> getlistschemeId(int userId, int schemeId, Date currentDate, String status);

	@Query(value = "SELECT * FROM chitfund.payment where  user_id=:userId and  status=:status", nativeQuery = true)
	List<Payment> getPaymentHistoryList(int userId, String status);

	@Query(value = "SELECT * FROM chitfund.payment where  user_id=:userId and scheme_id=:schemeId  and  status=:status", nativeQuery = true)
	List<Payment> getPaymentHistroyschemeList(int userId, int schemeId, String status);

}
