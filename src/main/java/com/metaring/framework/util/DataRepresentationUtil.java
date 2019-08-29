package com.metaring.framework.util;

import com.metaring.framework.type.DataRepresentation;

public final class DataRepresentationUtil {

    public static final Object toText(DataRepresentation dataRepresentation) {
        if(dataRepresentation == null || dataRepresentation.isNull()) {
            return "";
        }
        return dataRepresentation.asText();
    };

    public static final Object toNative(DataRepresentation dataRepresentation) {
        if(dataRepresentation == null || dataRepresentation.isNull()) {
            return null;
        }
        if(dataRepresentation.isDigit()) {
            return dataRepresentation.asDigit();
        }
        if(dataRepresentation.isRealDigit()) {
            return dataRepresentation.asRealDigit();
        }
        if(dataRepresentation.isTruth()) {
            return dataRepresentation.asTruth();
        }
        if(dataRepresentation.isEmail()) {
            return dataRepresentation.asEmail().toString();
        }
        if(dataRepresentation.isText()) {
            return dataRepresentation.asText();
        }
        return dataRepresentation.toJson();
    }
}