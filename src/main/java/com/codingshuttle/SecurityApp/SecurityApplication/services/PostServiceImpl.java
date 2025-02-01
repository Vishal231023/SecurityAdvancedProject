package com.codingshuttle.SecurityApp.SecurityApplication.services;


import com.codingshuttle.SecurityApp.SecurityApplication.dto.PostDto;
import com.codingshuttle.SecurityApp.SecurityApplication.entities.PostEntity;
import com.codingshuttle.SecurityApp.SecurityApplication.exceptions.ResourceNotFoundException;
import com.codingshuttle.SecurityApp.SecurityApplication.repositories.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private  final ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PostDto createNewPost(PostDto postDto) {
        PostEntity post = modelMapper.map(postDto, PostEntity.class);

        return modelMapper.map(postRepository.save(post), PostDto.class) ;


    }

    @Override
    public List<PostDto> getAllPosts() {
        return postRepository
                .findAll()
                .stream()
                .map(postEntity -> modelMapper.map(postEntity, PostDto.class))
                .toList();
    }

    @Override
    public PostDto getPostById(Long id) {
       PostEntity post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post not found with this Id " +id));

       return modelMapper.map(post, PostDto.class);
    }

    @Override
    public PostDto updatePost(Long id, PostDto inputPost) {

        PostEntity oldPost = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with this Id " +id));

        inputPost.setId(id);
        modelMapper.map(inputPost,oldPost);

        PostEntity savedPost = postRepository.save(oldPost);

        return modelMapper.map(savedPost,PostDto.class);
    }
}
