package br.unitins.model.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import br.unitins.model.enums.Role;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Role role) {
        return role.getId();
    }

    @Override
    public Role convertToEntityAttribute(Integer id) {
        return Role.valueOf(id);
    }
}
