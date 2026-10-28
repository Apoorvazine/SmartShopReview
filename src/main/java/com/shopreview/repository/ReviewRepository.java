package com.shopreview.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopreview.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
