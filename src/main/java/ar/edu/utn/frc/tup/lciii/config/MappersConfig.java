package ar.edu.utn.frc.tup.lciii.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;

public class MappersConfig {
    public ModelMapper modelMapper () {
        ModelMapper modelMapper = new ModelMapper();
        return  modelMapper;
    }

    public ModelMapper mergerMapper(){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().
                setPropertyCondition(Conditions.isNotNull());
        return mapper;
    }

    public ObjectMapper objectMapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper;
    }
}
