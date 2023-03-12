package org.palad.fakeshop.infra.repository;

import org.palad.fakeshop.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


}
