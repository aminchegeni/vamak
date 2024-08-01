package ir.snapp.pay.side.project.vamak.commons.model;

import ir.snapp.pay.side.project.vamak.commons.Identifiable;
import ir.snapp.pay.side.project.vamak.commons.Model;
import ir.snapp.pay.side.project.vamak.commons.PayType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import static java.util.Objects.nonNull;

class Converters {

    private Converters() {
        throw new AssertionError("Utility class");
    }

    private static abstract class BasicIdentifiableConverter<T extends Identifiable<Character>> implements
            AttributeConverter<T, Character> {

        final T defaultValue;

        BasicIdentifiableConverter(T defaultValue) {
            this.defaultValue = defaultValue;
        }

        @Override
        public Character convertToDatabaseColumn(T attribute) {
            if (nonNull(attribute)) {
                return attribute.getCode();
            }
            return defaultValue.getCode();
        }

        @Override
        public abstract T convertToEntityAttribute(Character dbData);
    }

    @Converter
    static class _Model extends BasicIdentifiableConverter<Model> {

        public _Model() {
            super(Model.UNKNOWN);
        }

        @Override
        public Model convertToEntityAttribute(Character dbData) {
            if (nonNull(dbData)) {
                return Model.valueOf(dbData);
            }
            return defaultValue;
        }
    }

    @Converter
    static class _PayType extends BasicIdentifiableConverter<PayType> {

        public _PayType() {
            super(PayType.UNKNOWN);
        }

        @Override
        public PayType convertToEntityAttribute(Character dbData) {
            if (nonNull(dbData)) {
                return PayType.valueOf(dbData);
            }
            return defaultValue;
        }
    }
}
