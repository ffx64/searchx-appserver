package com.ffx64.searchx_api.controllers.combolist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ffx64.searchx_api.entities.combolist.UrlEntity;
import com.ffx64.searchx_api.repositories.combolist.UrlRepository;

@RestController
@RequestMapping("/v1/api/combolist/urls")
@Controller("urlControllerCombolist")
public class UrlController {

    @Autowired
    private UrlRepository urlRepository;

    @GetMapping
    public List<UrlEntity> getAllUrls() {
        return urlRepository.findAll();
    }

    @GetMapping("/{id}")
    public UrlEntity getUrlById(@PathVariable Long id) {
        return urlRepository.findById(id).orElse(null);
    }
}
