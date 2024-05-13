package com.acortador.url.Service;

import org.springframework.stereotype.Service;

import com.acortador.url.DTO.LinkDto;
import com.acortador.url.Entity.LinkEntity;

@Service
public interface LinkService {
    public String shortLink(LinkDto linkDto);
    
    public LinkEntity getLink(String urlShort);
}
