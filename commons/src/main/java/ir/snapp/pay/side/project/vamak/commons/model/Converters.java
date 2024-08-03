package ir.snapp.pay.side.project.vamak.commons.model;

import ir.snapp.pay.side.project.vamak.commons.Identifiable;
import ir.snapp.pay.side.project.vamak.commons.LoanModel;
import ir.snapp.pay.side.project.vamak.commons.PayType;
import ir.snapp.pay.side.project.vamak.commons.ReceiveType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import static java.util.Objects.nonNull;

class Converters {

    private Converters() {
        throw new AssertionError("Utility class");
    }

    static abstract class BasicIdentifiableAttrConverter<T extends Identifiable<Character>> implements
            AttributeConverter<T, Character> {

        final T defaultValue;

        BasicIdentifiableAttrConverter(T defaultValue) {
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
        public T convertToEntityAttribute(Character dbData) {
            if (nonNull(dbData)) {
                return valueOf(dbData);
            }
            return defaultValue;
        }

        abstract T valueOf(Character dbData);
    }

    @Converter
    static class _LoanModel extends BasicIdentifiableAttrConverter<LoanModel> {

        public _LoanModel() {
            super(LoanModel.UNKNOWN);
        }

        @Override
        LoanModel valueOf(Character dbData) {
            return LoanModel.valueOf(dbData);
        }
    }

    @Converter
    static class _ReceiveType extends BasicIdentifiableAttrConverter<ReceiveType> {

        public _ReceiveType() {
            super(ReceiveType.UNKNOWN);
        }

        @Override
        ReceiveType valueOf(Character dbData) {
            return ReceiveType.valueOf(dbData);
        }
    }

    @Converter
    static class _PayType extends BasicIdentifiableAttrConverter<PayType> {

        public _PayType() {
            super(PayType.UNK);
        }

        @Override
        PayType valueOf(Character dbData) {
            return PayType.valueOf(dbData);
        }
    }
}
