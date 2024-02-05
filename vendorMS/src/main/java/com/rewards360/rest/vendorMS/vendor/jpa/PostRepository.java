package com.rewards360.rest.vendorMS.vendor.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rewards360.rest.vendorMS.post.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
