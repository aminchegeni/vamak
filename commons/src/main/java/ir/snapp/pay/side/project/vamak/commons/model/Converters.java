package ir.snapp.pay.side.project.vamak.commons.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import static ir.snapp.pay.side.project.vamak.commons.model.Model.UNKNOWN;
import static java.util.Objects.nonNull;

class Converters {

    private Converters() {
        throw new AssertionError("Utility class");
    }

    @Converter
    static class _Model implements AttributeConverter<Model, Character> {

        @Override
        public Character convertToDatabaseColumn(Model attribute) {
            if (nonNull(attribute)) {
                return attribute.getSymbol();
            }
            return Model.UNKNOWN.getSymbol();
        }

        @Override
        public Model convertToEntityAttribute(Character dbData) {
            if (nonNull(dbData)) {
                return Model.valueOf(dbData);
            }
            return Model.UNKNOWN;
        }
    }

    @Converter
    static class _PayType implements AttributeConverter<PayType, Character> {

        @Override
        public Character convertToDatabaseColumn(PayType attribute) {
            if (nonNull(attribute)) {
                return attribute.getCode();
            }
            return PayType.UNKNOWN.getCode();
        }

        @Override
        public PayType convertToEntityAttribute(Character dbData) {
            if (nonNull(dbData)) {
                return PayType.valueOf(dbData);
            }
            return PayType.UNKNOWN;
        }
    }
}
