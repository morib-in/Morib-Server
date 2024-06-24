package org.sopt.jaksim.user.dto.request;

public record UserSignInRequest(
    String userId,
    String password
){
}
