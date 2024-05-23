package com.acortador.url.controller;

import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.servlet.view.RedirectView;

import com.acortador.url.Controller.LinkController;
import com.acortador.url.DTO.LinkDto;
import com.acortador.url.Entity.LinkEntity;
import com.acortador.url.Service.Impl.LinkServiceImpl;

import jakarta.servlet.http.HttpServletRequest;

@ExtendWith(MockitoExtension.class)
public class LinkControllerTest {
    
    @InjectMocks
    LinkController linkController;

    @Mock
    LinkServiceImpl linkServiceImpl;

    
    MockHttpServletRequest request;
    
    LinkDto linkDto;

    LinkEntity linkEntity;

    @BeforeEach
    public void setUp(){
        linkDto = LinkDto.builder()
        .id(1)
        .urlOrigin("http:/google.com/aaaaa")
        .build();

        linkEntity = LinkEntity.builder()
        .id(1L)
        .urlOrigin("http:/google.com/aaaaa")
        .urlShort("acortador")
        .build();

        request = new MockHttpServletRequest();
        request.setScheme("http");
        request.setServerName("example.com");
        request.setServerPort(8080);
    }


    @Test
    public void shortUrl(){


        when(linkServiceImpl.shortLink(linkDto)).thenReturn(linkEntity.getUrlShort());
        ResponseEntity<String> response =linkController.UrlShort(request,linkDto);
        String url = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";
        Assertions.assertEquals(ResponseEntity.ok(url+linkEntity.getUrlShort()), response);
        Assertions.assertEquals(url+linkEntity.getUrlShort(), response.getBody());

    }

    @Test
    public void getUrl(){
        when(linkServiceImpl.getLink(linkEntity.getUrlShort())).thenReturn(linkEntity);

        RedirectView redirect =linkController.getUrl(linkEntity.getUrlShort());

        Assertions.assertEquals(linkEntity.getUrlOrigin(), redirect.getUrl());
        // Assertions.assertEquals(linkEntity.getUrlShort(), redirect.getBody());

    }

    @Test
    public void errorUrl(){
        when(linkServiceImpl.getLink("aaaaa")).thenReturn(null);

        RedirectView redirect =linkController.getUrl("aaaaa");
        Assertions.assertEquals("/error", redirect.getUrl());
        // Assertions.assertEquals(linkEntity.getUrlShort(), redirect.getBody());

    }

    @Test
    public void error(){

        ResponseEntity<String> response =linkController.error();

        Assertions.assertEquals(ResponseEntity.notFound().build(), response);
        // Assertions.assertEquals(linkEntity.getUrlShort(), redirect.getBody());

    }

}
