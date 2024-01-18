package com.kani.e_mail.service;

import com.kani.e_mail.domain.User;

public interface IUserService {
    User saveUserToDB(User user);
    Boolean verifyToken(String token);
}
