package com.gonzova.EventsSchedule.service.impl;

import com.gonzova.EventsSchedule.domain.entity.User;
import com.gonzova.EventsSchedule.domain.mapper.UserMapper;
import com.gonzova.EventsSchedule.repository.UserRepository;
import com.gonzova.EventsSchedule.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Primary
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @NonNull
    private UserRepository userRepository;

    @NonNull
    private UserMapper userMapper;

    @Override
    public User get(UUID id) {
        return userRepository.getById(id);
    }

    @Override
    public User create(User userJson) {
        return userRepository.saveAndFlush(userJson);
    }

    @Override
    public User update(UUID id, User userJson) {
        return Optional.of(id)
                .map(this::get)
                .map(current -> userMapper.merge(current, userJson))
                .map(this::create)
                .orElseThrow();
    }

    @Override
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }
}
