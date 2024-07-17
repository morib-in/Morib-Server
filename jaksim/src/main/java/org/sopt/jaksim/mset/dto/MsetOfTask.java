package org.sopt.jaksim.mset.dto;

public record MsetOfTask(
        Long id,
        String name,
        String url
) {
    public static MsetOfTask of(Long id, String name, String url) {
        return new MsetOfTask(id, name, url);
    }
}
