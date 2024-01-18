package com.kani.e_mail.service.impl;

import com.kani.e_mail.domain.Confirmation;
import com.kani.e_mail.domain.User;
import com.kani.e_mail.repository.IConfirmationRepository;
import com.kani.e_mail.repository.IUserRepository;
import com.kani.e_mail.service.IEmailService;
import com.kani.e_mail.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final IUserRepository userRepository;
    private final IConfirmationRepository confirmationRepository;
    private final IEmailService emailService;

    @Override
    public User saveUserToDB(User user) {
        if(userRepository.existsByEmail(user.getEmail())){
            throw new RuntimeException("Email already exists");
        }
        user.setIsEnabled(false);
        Confirmation confirmation = new Confirmation(user);
        confirmationRepository.save(confirmation);

        // todo here Email sender codes
        //emailService.sendSimpleMaileMessage(user.getFirstName(), user.getEmail(), confirmation.getToken());
        //emailService.sendMimeMessageWithAttachment(user.getFirstName(), user.getEmail(), confirmation.getToken());
        emailService.sendMimeMessageWithEmbeddedImages(user.getFirstName(), user.getEmail(), confirmation.getToken());
        return user;
    }

    @Override
    public Boolean verifyToken(String token) {
        Confirmation confirmation = confirmationRepository.findByToken(token);
        User user = userRepository.findByUserEmailIgnoreCase(confirmation.getUser().getEmail());
        user.setIsEnabled(true);
        userRepository.save(user);
        return Boolean.TRUE;
    }
}
