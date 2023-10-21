package com.ceam.shop.controller;

import com.ceam.shop.service.IAppHomeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CeaM
 * 2023/02/08 17:53
 **/
@RestController
@AllArgsConstructor
@RequestMapping("/api/home")
public class AppHomeController {

    private final IAppHomeService appHomeService;

    @GetMapping
    public ResponseEntity<Object> home() {
        Object home = appHomeService.home();
        return ResponseEntity.ok(home);
    }
}
