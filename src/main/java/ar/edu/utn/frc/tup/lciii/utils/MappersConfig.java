package ar.edu.utn.frc.tup.lciii.utils;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;

public class MappersConfig {
    public static ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public ModelMapper mergerMapper() {
        ModelMapper mapper =  new ModelMapper();
        mapper.getConfiguration()
                .setPropertyCondition(Conditions.isNotNull());
        return mapper;
    }
}
