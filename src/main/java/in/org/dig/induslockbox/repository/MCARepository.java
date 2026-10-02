package in.org.dig.induslockbox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import in.org.dig.induslockbox.entity.MCA;

@Repository
public interface MCARepository extends JpaRepository<MCA, Long> {

	@Query("FROM MCA m JOIN FETCH m.company c WHERE c.id = :companyid")
	List<MCA> findByCompany_id(@Param("companyid") Long companyid);

	List<MCA> findByActiveTrue();

	List<MCA> findByActiveFalse();
}
