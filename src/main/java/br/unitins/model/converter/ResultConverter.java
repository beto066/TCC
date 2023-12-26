package br.unitins.model.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.unitins.model.enums.TrainingResult;


@Converter(autoApply = true)
public class ResultConverter implements AttributeConverter<TrainingResult, String>  {
    @Override
    public String convertToDatabaseColumn(TrainingResult training) {
        return training.getLabel();
    }

    @Override
    public TrainingResult convertToEntityAttribute(String label) {
        return TrainingResult.valueOf(label.toUpperCase());
    }
}
