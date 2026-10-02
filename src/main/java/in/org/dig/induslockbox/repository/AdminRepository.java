package in.org.dig.induslockbox.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.org.dig.induslockbox.entity.Admin;
import in.org.dig.induslockbox.entity.Role;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

	Optional<Admin> findByUserName(String userName);

	Optional<Admin> findByEmail(String email);

	Boolean existsByUserName(String userName);

	Boolean existsByMobile(String mobile);

	Boolean existsByEmail(String email);

	Admin findByRole(Role role);

}
