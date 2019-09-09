package com.boot.webservice.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanUp() {
        /**
         * 이후 테스트 코드에 영향을 끼치지 않기 위해
         * 테스트 메서드가 끝날때 마다 repository 전체를 비우는 코드
         */
        postsRepository.deleteAll();
    }

    // 게시글저장_불러오기
    @Test
    public void load_savePost() {

        // given
        postsRepository.save(Posts.builder()
                .title("테스트 게시글 제목")
                .content("테스트 게시글 본문")
                .author("Ahnder")
                .build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle(), is("테스트 게시글 제목"));
        assertThat(posts.getContent(), is("테스트 게시글 본문"));

    }

    // BaseTimeEntity_등록
    @Test
    public void register_BaseTimeEntity() {

        // given
        LocalDateTime now = LocalDateTime.now();
        postsRepository.save(Posts.builder()
                .title("테스트 게시글 제목")
                .content("테스트 게시글 본문")
                .author("Ahnder")
                .build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);
        assertTrue(posts.getCreatedDate().isAfter(now));
        assertTrue(posts.getModifiedDate().isAfter(now));

    }

}
