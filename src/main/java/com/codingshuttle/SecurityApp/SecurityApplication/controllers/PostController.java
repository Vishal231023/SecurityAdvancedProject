package com.codingshuttle.SecurityApp.SecurityApplication.controllers;


import com.codingshuttle.SecurityApp.SecurityApplication.dto.PostDto;
import com.codingshuttle.SecurityApp.SecurityApplication.services.PostService;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping(path = "/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostDto> getAllPosts(){
        return postService.getAllPosts();
    }

    @GetMapping(path ="/{id}")

    public PostDto getPostById(@PathVariable Long id){
        return postService.getPostById(id);
    }

    @PostMapping
    public PostDto createNewPost(@RequestBody @Valid PostDto postDto){
        return postService.createNewPost(postDto);
    }

    @PutMapping("/{id}")
    public PostDto updatePost(@PathVariable Long id,@RequestBody PostDto inputPost){
        return postService.updatePost(id,inputPost);
    }
}
