package com.acortador.url.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.acortador.url.DTO.LinkDto;
import com.acortador.url.Entity.LinkEntity;
import com.acortador.url.Service.LinkService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class LinkController {

    @Autowired
    private LinkService linkService;


    @PostMapping("/short")
    public ResponseEntity<String> UrlShort(@RequestBody LinkDto linkDto) {
        return ResponseEntity.ok(linkService.shortLink(linkDto));
    }

    @GetMapping("/{id}")
    public RedirectView getUrl(@PathVariable String id) {
        LinkEntity link = linkService.getLink(id);
        if (link == null) {
            return new RedirectView("/error");
        }
        
        return new RedirectView(link.getUrlOrigin());
    }
    
    @GetMapping("/error")
    public ResponseEntity<String> error() {
        return ResponseEntity.notFound().build();
    }
    
}
