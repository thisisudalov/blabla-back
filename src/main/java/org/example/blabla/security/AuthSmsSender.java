package org.example.blabla.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthSmsSender {

    public String generateAndSendAuthSequence() {
        //gen seq
        //send seq
        return "1234";
    }

}
