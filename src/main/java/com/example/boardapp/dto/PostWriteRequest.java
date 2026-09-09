package com.example.boardapp.dto;

import jakarta.validation.constraints.NotBlank;

//게시물 생성요청 DTO
public class PostWriteRequest {

    @NotBlank (message = "제목은 필수 입력 항목입니다 ")
    private String title;

    @NotBlank(message = "본문 내용을 입력해주세요 ")
    private String content;

    public PostWriteRequest () {}

    // Getter/Setter
    public String getTitle () {
        return title;
    }
    public void setTitle (String title) {
        this.title = title;
    }
    
    public String getContent () {
        return content;
    }
    public void setContent (String content) {
        this.content = content;
    }
}
