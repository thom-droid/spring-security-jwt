package org.test.jwttest.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.test.jwttest.domain.Member;

@Repository
public interface MemberRepo extends JpaRepository<Member, Long> {

    Member findByEmail(String email);
}
