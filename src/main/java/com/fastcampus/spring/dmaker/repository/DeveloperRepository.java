package com.fastcampus.spring.dmaker.repository;

import com.fastcampus.spring.dmaker.entity.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer,Long> {

    Optional<Developer> findByMemberId(String memberId);

}
