package com.example.demo.components.post.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.PostShare;


@Repository
public interface PostShareDao extends JpaRepository<PostShare, Integer>{

}
