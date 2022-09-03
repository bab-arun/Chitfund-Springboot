package com.bb.chitfund.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bb.chitfund.entity.Scheme;

@Repository
public interface SchemeRepository extends JpaRepository<Scheme, Integer> {

	@Query(" FROM Scheme  WHERE schemeName = :schemeName")
	Scheme findBySchemeName(String schemeName);

	@Query(value = "select pay_amount from scheme where scheme_name=:schemeName", nativeQuery = true)
	long findInstallmentAmount(String schemeName);

	@Query(value = "select id from scheme where scheme_name=:schemeName", nativeQuery = true)
	long findSchemeIdBySchemeName(String schemeName);

	@Query(value = "select scheme_name from scheme;", nativeQuery = true)
	List<String> findSchemeName();

}
