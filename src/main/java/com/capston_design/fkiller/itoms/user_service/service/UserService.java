package com.capston_design.fkiller.itoms.user_service.service;

import com.capston_design.fkiller.itoms.user_service.dto.UserRequest;
import com.capston_design.fkiller.itoms.user_service.model.User;
import com.capston_design.fkiller.itoms.user_service.model.enums.UserCategory;
import com.capston_design.fkiller.itoms.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User createUser(UserRequest userRequest) {

        User user = new User();

        user.setName(userRequest.name());
        user.setCategory(UserCategory.from(userRequest.category()));

        return userRepository.save(user);
    }
}
