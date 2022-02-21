package com.gonzova.EventsSchedule.service.impl;

import com.gonzova.EventsSchedule.domain.entity.Office;
import com.gonzova.EventsSchedule.domain.mapper.OfficeMapper;
import com.gonzova.EventsSchedule.repository.OfficeRepository;
import com.gonzova.EventsSchedule.service.OfficeService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
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
    public Office getAndInitialize(UUID id) {
        Office office = officeRepository.getById(id);
        Hibernate.initialize(office);
        Hibernate.initialize(office.getRooms());
        return office;
    }

    @Override
    @Transactional
    public Office create(Office officeJson) {
        return officeRepository.saveAndFlush(officeJson);
    }

    @Override
    @Transactional
    public Office update(UUID id, Office officeJson) {
        return Optional.of(id)
                .map(officeRepository::getById)
                .map(current -> officeMapper.merge(current, officeJson))
                .map(officeRepository::saveAndFlush)
                .orElseThrow();
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        officeRepository.deleteById(id);
    }
}
