package com.acortador.url.Entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UrlEntityTest {
    
    @Test
    public void createUrlEntity(){
        LinkEntity link = new LinkEntity();
        
        link.setId(1L);
        link.setUrlOrigin("http:localHost:8080");
        link.setUrlShort("http:acortador");

        Assertions.assertNotNull(link);
        Assertions.assertEquals(1L, link.getId());
        Assertions.assertEquals("http:localHost:8080", link.getUrlOrigin());
        Assertions.assertEquals("http:acortador", link.getUrlShort());
    }
}
