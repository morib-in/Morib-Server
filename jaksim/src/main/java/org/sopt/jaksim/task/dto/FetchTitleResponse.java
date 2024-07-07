package org.sopt.jaksim.task.dto;

public record FetchTitleResponse(
        String tabName
) {
    public static FetchTitleResponse of(String tabName) {
        return new FetchTitleResponse(tabName);
    }
}
