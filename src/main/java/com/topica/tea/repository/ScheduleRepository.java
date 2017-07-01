package com.topica.tea.repository;

import com.topica.tea.domain.Schedule;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Schedule entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Long> {

}