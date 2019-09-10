package com.boot.webservice.service;

import com.boot.webservice.domain.posts.Posts;
import com.boot.webservice.domain.posts.PostsRepository;
import com.boot.webservice.dto.posts.PostsSaveRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsServiceTest {

    @Autowired
    private PostsService postsService;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void cleanUp() {
        postsRepository.deleteAll();
    }

    // Dto 데이터가_posts테이블에_저장
    @Test
    public void save_DTOData_to_postsTable() {

        // given
        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
                .author("Ahnder")
                .content("테스트")
                .title("테스트 제목")
                .build();

        // when
        postsService.save(dto);

        // then
        Posts posts = postsRepository.findAll().get(0);
        assertThat(posts.getAuthor()).isEqualTo(dto.getAuthor());
        assertThat(posts.getContent()).isEqualTo(dto.getContent());
        assertThat(posts.getTitle()).isEqualTo(dto.getTitle());

    }

}
