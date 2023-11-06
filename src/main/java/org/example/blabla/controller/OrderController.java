package org.example.blabla.controller;

import org.example.blabla.api.OrderApi;
import org.example.blabla.model.InfoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController("/order")
public class OrderController implements OrderApi {

    @Override
    public ResponseEntity<InfoResponse> _getOrder() {
        return ResponseEntity.ok(new InfoResponse()
                .message("hi")
                .time(LocalDateTime.now().toString())
        );
    }
}
