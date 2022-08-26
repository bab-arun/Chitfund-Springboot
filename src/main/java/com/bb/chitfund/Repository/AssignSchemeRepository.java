package com.bb.chitfund.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bb.chitfund.Entity.AssignScheme;

@Repository
public interface AssignSchemeRepository extends JpaRepository<AssignScheme, Integer> {

	@Query("Select assignScheme.user.id from  AssignScheme assignScheme where assignScheme.scheme.id =:schemeId")
	List<Integer> findBy(int schemeId);

}
