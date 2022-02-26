package com.jiankowalski.basicspring.datasources.repository;

import com.jiankowalski.basicspring.datasources.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
}
