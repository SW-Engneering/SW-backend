package com.swengineer.sportsmatch.repository;

import com.swengineer.sportsmatch.entity.TeamMember;
import com.swengineer.sportsmatch.entity.TeamMember.TeamMemberId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamMemberRepository extends JpaRepository<TeamMember, TeamMemberId> {
}
