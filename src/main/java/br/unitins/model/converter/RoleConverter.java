package br.unitins.model.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.unitins.model.Role;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role, String> {

    @Override
    public String convertToDatabaseColumn(Role role) {
        return role.getLabel();
    }

    @Override
    public Role convertToEntityAttribute(String label) {
        return Role.valueOf(label.toUpperCase());
    }
    
}
