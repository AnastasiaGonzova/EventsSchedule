package com.gonzova.EventsSchedule.controller;

import com.gonzova.EventsSchedule.domain.dto.user.UserCreateDto;
import com.gonzova.EventsSchedule.domain.dto.user.UserDto;
import com.gonzova.EventsSchedule.domain.dto.user.UserUpdateDto;
import com.gonzova.EventsSchedule.domain.mapper.UserMapper;
import com.gonzova.EventsSchedule.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path="users")
@RequiredArgsConstructor
public class UserController {

    @NonNull
    private UserService userService;

    @NonNull
    private UserMapper userMapper;

    @GetMapping("/{userId}")
    public UserDto get(@PathVariable(name="userId") UUID id){
        return Optional.of(id)
                .map(userService::get)
                .map(userMapper::toDto)
                .orElseThrow();
    }

    @PostMapping
    public UserDto post(@Valid @RequestBody UserCreateDto userJson){
        return Optional.ofNullable(userJson)
                .map(userMapper::fromCreateDto)
                .map(userService::create)
                .map(userMapper::toDto)
                .orElseThrow();
    }

    @PatchMapping("/{userId}")
    public UserDto patch(@PathVariable(name="userId") UUID id, @Valid @RequestBody UserUpdateDto userJson){
        return Optional.ofNullable(userJson)
                .map(userMapper::fromUpdateDto)
                .map(toUpdate->userService.update(id, toUpdate))
                .map(userMapper::toDto)
                .orElseThrow();
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable(name="userId") UUID id){
        userService.delete(id);
    }
}
