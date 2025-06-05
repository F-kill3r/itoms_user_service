package com.capston_design.fkiller.itoms.user_service.service;

import com.capston_design.fkiller.itoms.user_service.dto.UserRequest;
import com.capston_design.fkiller.itoms.user_service.model.User;
import com.capston_design.fkiller.itoms.user_service.model.enums.UserCategory;
import com.capston_design.fkiller.itoms.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ThreadLocalRandom;

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

    @Transactional(readOnly = true)
    public User getRandomRequesterUser() {
        List<User> requester = userRepository.findByCategory(UserCategory.REQUESTER);
        if (requester.isEmpty()) {
            throw new NoSuchElementException("Requester 유저가 없습니다.");
        }
        int idx = ThreadLocalRandom.current().nextInt(requester.size());
        return requester.get(idx);
    }

    @Transactional(readOnly = true)
    public User getRandomCreatorUser() {
        List<User> creator = userRepository.findByCategory(UserCategory.CREATOR);
        if (creator.isEmpty()) {
            throw new NoSuchElementException("Creator 유저가 없습니다.");
        }
        int idx = ThreadLocalRandom.current().nextInt(creator.size());
        return creator.get(idx);
    }

    @Transactional(readOnly = true)
    public User getRandomChargerUser() {
        List<User> charger = userRepository.findByCategory(UserCategory.CHARGER);
        if (charger.isEmpty()) {
            throw new NoSuchElementException("Charger 유저가 없습니다.");
        }
        int idx = ThreadLocalRandom.current().nextInt(charger.size());
        return charger.get(idx);
    }
}
