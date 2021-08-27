package com.rentori.spring_rest.repository;

import com.rentori.spring_rest.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
