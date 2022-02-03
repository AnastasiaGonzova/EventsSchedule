package com.gonzova.EventsSchedule.controller;

import com.gonzova.EventsSchedule.domain.dto.office.OfficeCreateDto;
import com.gonzova.EventsSchedule.domain.dto.office.OfficeDto;
import com.gonzova.EventsSchedule.domain.dto.office.OfficeUpdateDto;
import com.gonzova.EventsSchedule.domain.mapper.OfficeMapper;
import com.gonzova.EventsSchedule.service.OfficeService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path="offices")
@RequiredArgsConstructor
public class OfficeController {

    @NonNull
    private OfficeService officeService;

    @NonNull
    private OfficeMapper officeMapper;

    @GetMapping("/{officeId}")
    public OfficeDto get(@PathVariable UUID id){
        return Optional.of(id)
                .map(officeService::get)
                .map(officeMapper::toDto)
                .orElseThrow();
    }

    @PostMapping
    public OfficeDto post(OfficeCreateDto officeJson){
        return Optional.ofNullable(officeJson)
                .map(officeMapper::fromCreateDto)
                .map(officeService::create)
                .map(officeMapper::toDto)
                .orElseThrow();
    }

    @PatchMapping("/{officeId}")
    public OfficeDto patch(@PathVariable UUID id, OfficeUpdateDto officeJson){
        return Optional.ofNullable(officeJson)
                .map(officeMapper::fromUpdateDto)
                .map(toUpdate->officeService.update(id, toUpdate))
                .map(officeMapper::toDto)
                .orElseThrow();
    }

    @DeleteMapping("/{officeId}")
    public void delete(@PathVariable UUID id){
        officeService.delete(id);
    }
}