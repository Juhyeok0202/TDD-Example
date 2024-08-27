package com.example.ttd.controller;

import com.google.gson.Gson;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class) //Mockito라는 도구를 사용하여 객체를 자동으로 만들어주는 설정이다.
public class MembershipControllerTest {

    @InjectMocks // MembershipController라는 클래스를 테스트할 때 필요한 객체를 자동으로 준비해준다.
    private MembershipController target;

    private MockMvc mockMvc; // 가짜 웹 브라우저처럼웹 요청을 보내고 응답을 받을 수 있음(API 테스트할 때 사용하는 도구)
    private Gson gson; // JSON 형식의 데이터를 다루는 도구

    /*
    컨트롤러는 함수 호출이 아닌 API호출을 통해 요청을 받고 응답을
    처리해야 하며, 메세지 컨버팅 등과 같은 작업이 필요하다. 그러므로
    MockMvc라는 클래스를 이용해야 하는데, 이에 대한 초기화를 하는 테스트부터 작성한다.
     */
    @Test
    public void mockMvc가Null이아님() throws Exception {

        /*
        MockMvc를 설정하고 준비하는 과정이다.
        (target은 우리가 테스트할 API를 가진 클래스)
        */
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(target)
                .build();

        // 객체가 잘 만들어 졌는지 확인 테스트!
        assertThat(target).isNotNull();
        assertThat(mockMvc).isNotNull();
    }
}
