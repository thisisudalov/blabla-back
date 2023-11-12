package org.example.blabla.controller;

import org.example.blabla.api.OrderApi;
import org.example.blabla.model.InfoResponse;
import org.example.blabla.security.Authenticated;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController("/order")
public class OrderController implements OrderApi {

    @Authenticated
    @Override
    public ResponseEntity<InfoResponse> getOrder() {
        return null;
    }

}
