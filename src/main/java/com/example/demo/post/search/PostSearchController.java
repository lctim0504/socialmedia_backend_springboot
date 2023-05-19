package com.example.demo.post.search;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Post;

@RestController
@RequestMapping("/api/posts")
public class PostSearchController {

    private final PostSearchService postSearchService;

    public PostSearchController(PostSearchService postSearchService) {
        this.postSearchService = postSearchService;
    }

    @GetMapping("/search")
    public List<Post> searchPosts(@RequestParam("query") String query) {
        return postSearchService.searchPosts(query);
    }
}
