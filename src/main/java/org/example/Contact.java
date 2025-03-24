package org.example;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Contact {

    private final String name;

    private final String phone;

    private final String email;


    @Override
    public String toString() {
        return name + ";" + phone + ";" + email;
    }
}
