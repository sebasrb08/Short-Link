package com.acortador.url.Service.Impl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acortador.url.DTO.LinkDto;
import com.acortador.url.Entity.LinkEntity;
import com.acortador.url.Repository.LinkRepository;
import com.acortador.url.Service.LinkService;


@Service
public class LinkServiceImpl implements LinkService{

    @Autowired
    LinkRepository linkRepository;


    @Override
    public String shortLink(LinkDto linkDto) {
        LinkEntity result =  checkLink(linkDto);
        
        if (result != null) {
            return result.getUrlShort();
        }

        String urlShort = generateRandomId();

        LinkEntity linkEntity = LinkEntity.builder()
        .urlOrigin(linkDto.getUrlOrigin())
        .urlShort(urlShort)
        .build();
        
        LinkEntity linkEntity2 = linkRepository.save(linkEntity);
        
        return  linkEntity2.getUrlShort();
    }

    @Override
    public LinkEntity getLink(String id) {
        Optional<LinkEntity> link = linkRepository.findByUrlShort(id);
        if (!link.isPresent()) {
            return null;
        }
        return link.get() ;
    }

    public LinkEntity checkLink(LinkDto linkDto){
        Optional<LinkEntity> linkEntity = linkRepository.findByUrlOrigin(linkDto.getUrlOrigin());
        if (!linkEntity.isPresent()) {
            return null;
        }
        return linkEntity.get();
    
    }

    public String generateRandomId(){
        Optional<LinkEntity> linkEntity;
        String urlShort;
        do{
            UUID id = UUID.randomUUID();
            urlShort = convertCharacter(id.toString());
            linkEntity = linkRepository.findByUrlShort(urlShort);
        }
        while (linkEntity.isPresent());

        return urlShort;
    }

    public String convertCharacter(String id){
        String character = "" ;
        for (int i = 0; i < 7; i++) {
            character+=id.charAt(i);
        }
        return character;
    }

}
