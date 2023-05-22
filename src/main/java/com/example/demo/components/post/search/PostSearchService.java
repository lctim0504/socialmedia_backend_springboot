package com.example.demo.components.post.search;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Post;

@Service
public class PostSearchService {

    private final PostSearchDao postSearchDao;

    public PostSearchService(PostSearchDao postSearchDao) {
        this.postSearchDao = postSearchDao;
    }

    public List<Post> searchPosts(String query) {
        return postSearchDao.findByContentContaining(query);
    }
}
