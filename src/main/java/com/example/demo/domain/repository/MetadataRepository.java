package com.example.demo.domain.repository;

import com.example.demo.domain.entities.Metadata;
import com.example.demo.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MetadataRepository extends JpaRepository<Metadata, Long> {
    List<Metadata> findByUser(User user);
}
