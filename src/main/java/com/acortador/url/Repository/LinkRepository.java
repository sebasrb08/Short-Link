package com.acortador.url.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acortador.url.Entity.LinkEntity;

@Repository
public interface LinkRepository extends JpaRepository<LinkEntity,Long> {
    public Optional<LinkEntity> findByUrlShort(String urlShort);
    public Optional<LinkEntity> findByUrlOrigin(String urlOrigin);

}
