package in.org.dig.induslockbox.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.org.dig.induslockbox.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
	
	 List<Company> findByActiveTrue();
	    List<Company> findByActiveFalse();
}
