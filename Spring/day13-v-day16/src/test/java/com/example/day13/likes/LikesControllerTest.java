package com.example.day13.likes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.day13.utils.JwtUtil;

@SpringBootTest
@AutoConfigureMockMvc
class LikesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    LikesService likesService;

    @MockBean
    private JwtUtil jwtUtil;

    private String token;

    @BeforeEach
    public void setUp() {
        token = "mocked-jwt-token";
        given(jwtUtil.isExpired(token)).willReturn(false);
        given(jwtUtil.getIdx(token)).willReturn(1L);
        given(jwtUtil.getUsername(token)).willReturn("test01@test.com");
        given(jwtUtil.getRole(token)).willReturn("ROLE_USER");
    }

    @Test
    void likeController_create_failed_empty_token() throws Exception {
        mockMvc.perform(get("/likes/create?idx=1"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void 좋아요_성공() throws Exception {
        mockMvc.perform(get("/likes/create?idx=1").header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk());
    }

}