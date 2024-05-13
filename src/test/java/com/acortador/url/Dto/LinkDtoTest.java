package com.acortador.url.Dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.acortador.url.DTO.LinkDto;

public class LinkDtoTest {
        @Test
    public void createUrlDto(){
        LinkDto link = new LinkDto();
        
        link.setId(1L);
        link.setUrlOrigin("http:localHost:8080");

        Assertions.assertNotNull(link);
        Assertions.assertEquals(1L, link.getId());
        Assertions.assertEquals("http:localHost:8080", link.getUrlOrigin());
    }
}
