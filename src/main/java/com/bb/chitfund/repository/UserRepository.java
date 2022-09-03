package com.bb.chitfund.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bb.chitfund.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query(" FROM User  WHERE userCode = :userCode")
	public User findByUserCode(String userCode);

	@Query(value = "select  u.user_Code  from user u where u.id not in (select user_id from assign_scheme where scheme_id=:schemeId)", nativeQuery = true)
	public List<String> getUserCode(int schemeId);

	@Query(value = "select u.user_code from user u where u.id in (select user_id from assign_scheme );", nativeQuery = true)
	public List<String> findAssignedusers();

	@Query(value = "select id from user where user_Code=:userCode", nativeQuery = true)
	long findByCode(String userCode);

	@Query(value = "select user_code from user u where u.id in (select user_id from payment where scheme_id=:schemeId and status=:status);", nativeQuery = true)
	public List<String> getAssignedSchemeUserById(long schemeId, String status);

	@Query(value = "select u.user_code,s.scheme_name,u.user_name from chitfund.payment p,user u,scheme s where p.status=:status and p.installment_date like :cutDate% and p.user_id=u.id and p.scheme_id=s.id", nativeQuery = true)
	public List<String[]> getEmails(String status, String cutDate);

}
