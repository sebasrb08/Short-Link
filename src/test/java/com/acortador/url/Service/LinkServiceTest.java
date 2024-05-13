package com.acortador.url.Service;


import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.acortador.url.DTO.LinkDto;
import com.acortador.url.Entity.LinkEntity;
import com.acortador.url.Repository.LinkRepository;
import com.acortador.url.Service.Impl.LinkServiceImpl;

@ExtendWith(MockitoExtension.class)
public class LinkServiceTest {

    @Mock
    LinkRepository linkRepository;

    @InjectMocks
    LinkServiceImpl linkServiceImpl;

    LinkEntity linkEntity;

    LinkDto linkDto;

    @BeforeEach
    public void setUp(){
        linkEntity = LinkEntity.builder()
        .id(1L)
        .urlOrigin("http://google.com/asdasdads")
        .urlShort("acortador")
        .build();

        linkDto = LinkDto.builder()
        .id(1L)
        .urlOrigin("http://google.com/asdasdads")
        .build();
    }

    @Test
    public void shortLink(){

        Mockito.when(linkRepository.save(Mockito.any())).thenReturn(linkEntity);
        
        String link =linkServiceImpl.shortLink(linkDto);

        Assertions.assertEquals("acortador", link);

    }

    @Test
    public void getLink(){
        Mockito.when(linkRepository.findByUrlShort(Mockito.any())).thenReturn(Optional.of(linkEntity));

        LinkEntity link = linkServiceImpl.getLink(linkEntity.getUrlShort());


        Assertions.assertNotNull(link);
        Assertions.assertEquals("acortador", link.getUrlShort());
        
        Assertions.assertEquals("http://google.com/asdasdads", link.getUrlOrigin());


    }

    
    

}
