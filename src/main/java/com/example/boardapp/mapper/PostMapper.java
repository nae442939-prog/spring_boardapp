package com.example.boardapp.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.boardapp.domain.Post;

@Mapper //@Mapper: 이 인터페이스를 매퍼로 만들어라 
public interface PostMapper { //스프링이 내부적으로 이 인터페이스를 구현해준다 

    //게시물 전체 가져오기
    List<Post> findAll ();

    //id로 게시물 가져오기 
    Optional<Post> findById(Long id);

    //게시물 생성처리
    void save (Post post);

    //게시물 삭제처리
    void delete (Long id);
    
}