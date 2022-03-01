package com.gonzova.EventsSchedule.controller;

import com.gonzova.EventsSchedule.domain.dto.office.OfficeCreateDto;
import com.gonzova.EventsSchedule.domain.dto.office.OfficeDto;
import com.gonzova.EventsSchedule.domain.dto.office.OfficeUpdateDto;
import com.gonzova.EventsSchedule.domain.mapper.OfficeMapper;
import com.gonzova.EventsSchedule.service.OfficeService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @PreAuthorize("hasAuthority('USER')")
    public OfficeDto get(@PathVariable(name="officeId") UUID id){
        return Optional.of(id)
                .map(officeService::getAndInitialize)
                .map(officeMapper::toDto)
                .orElseThrow();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public OfficeDto create(@Valid @RequestBody OfficeCreateDto officeJson){
        return Optional.ofNullable(officeJson)
                .map(officeMapper::fromCreateDto)
                .map(officeService::create)
                .map(officeMapper::toDto)
                .orElseThrow();
    }

    @PatchMapping("/{officeId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public OfficeDto update(@PathVariable(name="officeId") UUID id, @Valid @RequestBody OfficeUpdateDto officeJson){
        return Optional.ofNullable(officeJson)
                .map(officeMapper::fromUpdateDto)
                .map(toUpdate->officeService.update(id, toUpdate))
                .map(officeMapper::toDto)
                .orElseThrow();
    }

    @DeleteMapping("/{officeId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void delete(@PathVariable(name="officeId") UUID id){
        officeService.delete(id);
    }
}
