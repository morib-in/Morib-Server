package org.sopt.jaksim.mset.domain;

import lombok.Getter;

@Getter
public class MsetDTO {
    private final Long id;
    private final String name;
    private final String url;

    public MsetDTO(Long id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }
}

