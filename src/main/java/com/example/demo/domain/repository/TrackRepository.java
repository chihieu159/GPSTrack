package com.example.demo.domain.repository;

import com.example.demo.domain.entities.Track;
import com.example.demo.domain.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepository extends PagingAndSortingRepository<Track, Long> {
    List<Track> findByUser(User user);
}
