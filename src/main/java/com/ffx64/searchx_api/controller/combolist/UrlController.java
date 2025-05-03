package com.ffx64.searchx_api.controller.combolist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ffx64.searchx_api.dto.combolist.UrlResponseDTO;
import com.ffx64.searchx_api.service.combolist.UrlService;

@RestController
@RequestMapping("/v1/api/combolist/urls")
@Controller("UrlControllerCombolist")
public class UrlController {

    @Autowired
    @Qualifier("UrlServiceCombolist")
    private UrlService urlService;

    @GetMapping
    public List<UrlResponseDTO> getAllUrls() {
        return urlService.getAllUrls();
    }

    @GetMapping("/{id}")
    public UrlResponseDTO getUrlById(@PathVariable Long id) {
        return urlService.getUrlById(id);
    }
}