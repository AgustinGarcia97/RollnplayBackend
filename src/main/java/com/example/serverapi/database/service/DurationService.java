package com.example.serverapi.database.service;

import com.example.serverapi.database.repository.DurationRepository;
import com.example.serverapi.dto.DurationDTO;
import com.example.serverapi.model.Duration;
import com.example.serverapi.utils.Converter.DtoAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DurationService {

    private final DurationRepository durationRepository;
    private final DtoAssembler dtoAssembler;

    @Autowired
    public DurationService(DurationRepository durationRepository, DtoAssembler dtoAssembler){
        this.durationRepository = durationRepository;
        this.dtoAssembler = dtoAssembler;
    }

    public Duration createOrUpdateDuration(DurationDTO durationDTO) {
        Duration duration = null;
        try{
           duration = dtoAssembler.getDurationEntity(durationDTO);
           duration = durationRepository.save(duration);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return duration;
    }

    public Optional<Duration> getDurationById(Long id) {
        try{
            return durationRepository.findById(id);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<DurationDTO> getALLDurationDTO() {
        try{
           return durationRepository.findAll().stream().map(dtoAssembler::getDurationDTO).toList();
        } catch (Exception e){
            return null;
        }

    }
}
