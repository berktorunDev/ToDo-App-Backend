package com.project.to_do_backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.to_do_backend.model.Status;

public interface StatusRepository extends JpaRepository<Status, UUID> {

}
