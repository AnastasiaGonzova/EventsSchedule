package com.gonzova.EventsSchedule.service.impl;

import com.gonzova.EventsSchedule.domain.entity.Office;
import com.gonzova.EventsSchedule.domain.mapper.OfficeMapper;
import com.gonzova.EventsSchedule.repository.OfficeRepository;
import com.gonzova.EventsSchedule.service.OfficeService;
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
public class OfficeServiceImpl implements OfficeService {

    @NonNull
    private OfficeRepository officeRepository;

    @NonNull
    private OfficeMapper officeMapper;

    @Override
    public Office get(UUID id) {
        return officeRepository.getById(id);
    }

    @Override
    public Office create(Office officeJson) {
        return officeRepository.saveAndFlush(officeJson);
    }

    @Override
    public Office update(UUID id, Office officeJson) {
        return Optional.of(id)
                .map(this::get)
                .map(current -> officeMapper.merge(current, officeJson))
                .map(this::create)
                .orElseThrow();
    }

    @Override
    public void delete(UUID id) {
        officeRepository.deleteById(id);
    }
}