package com.example.serverapi.utils.Converter;

import com.example.serverapi.dto.DurationDTO;
import com.example.serverapi.model.Duration;
import org.springframework.stereotype.Component;

@Component
public class DurationConverter {

    public Duration convertToEntity(DurationDTO durationDTO){
        Duration duration = new Duration();
        try{
            if(durationDTO.getId() != null){
                duration.setId(durationDTO.getId());
            }
            duration.setDurationName(durationDTO.getDurationName());
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return duration;
    }

    public DurationDTO convertToDTO(Duration duration){
        DurationDTO durationDTO = new DurationDTO();
        try{
            if(duration.getId() != null){
                durationDTO.setId(duration.getId());
            }
            durationDTO.setDurationName(duration.getDurationName());

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return durationDTO;

    }

}
