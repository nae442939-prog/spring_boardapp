-- schema.sql: 서버가 시작 될 때 실행할 sql문을 작성하는 곳 

-- 멤버 테이블 생성
CREATE TABLE IF NOT EXISTS member (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, --if not exists(member 테이블이 없으면): 테스트용
    username VARCHAR (50) NOT NULL UNIQUE,--bigint: 일반 정수형 (int)보다 더 큰 자료형
    password VARCHAR (100) NOT NULL --UNIQUE: 중복되면 안됨 
);

--게시물 테이블 생성
CREATE TABLE IF NOT EXISTS post (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) NOT NULL, --게시물 제목
    content TEXT NOT NULL, --게시물 내용
    member_id BIGINT NOT NULL, --외래키 (게시물과 사용자를 연결)
    FOREIGN KEY (member_id) REFERENCES member(id) -- member 테이블의 기본키를 참조
)