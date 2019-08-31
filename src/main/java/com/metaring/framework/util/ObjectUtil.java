/**
 *    Copyright 2019 MetaRing s.r.l.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.metaring.framework.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.function.Function;

import org.apache.calcite.linq4j.Linq4j;

import com.metaring.framework.type.DataRepresentation;

public final class ObjectUtil {

    public static final <T> boolean isNullOrEmpty(T[] input) {
        return Objects.isNull(input) || input.length == 0;
    }

    public static final boolean isNullOrEmpty(Iterable<?> input) {
        return Objects.isNull(input) || Linq4j.asEnumerable(input).count() == 0;
    }

    public static final boolean isNullOrEmpty(Map<?, ?> input) {
        return Objects.isNull(input) || input.isEmpty();
    }

    public static final boolean isNullOrEmpty(DataRepresentation input) {
        return Objects.isNull(input) || input.isNullOrEmpty();
    }

    public static final Map<String, Object> toMap(final DataRepresentation rep) {
        return toMap(rep, false);
    }

    public static final Map<String, Object> toMap(final DataRepresentation rep, boolean preserveNativeType) {
        return toMap(rep, null, null, preserveNativeType ? DataRepresentationUtil::toNative : DataRepresentationUtil::toText);
    }

    private static final Map<String, Object> toMap(final DataRepresentation initialData, Map<String, Object> map, String current, Function<DataRepresentation, Object> transformer) {
        map = isNullOrEmpty(map) ? new HashMap<>() : map;
        if(isNullOrEmpty(initialData)) {
            return map;
        }
        if(!StringUtil.isNullOrEmpty(current)) {
            map.put(current, transformer.apply(initialData));
        }
        if(initialData.hasProperties()) {
            for (String currentProperty : initialData.getProperties()) {
                String propertyName = String.join(StringUtil.isNullOrEmpty(current) ? "" : ".", StringUtil.isNullOrEmpty(current) ? "" : current, currentProperty);
                map = toMap(initialData.get(currentProperty), map, propertyName, transformer);
            }
        }
        if(initialData.hasLength()) {
            int i = 0;
            for (DataRepresentation item : initialData) {
                String propertyName = String.join(StringUtil.isNullOrEmpty(current) ? "" : ".", StringUtil.isNullOrEmpty(current) ? "" : current, ("." + i++));
                map = toMap(item, map, propertyName, transformer);
            }
        }
        return map;
    }

    public static final Properties toProperties(Map<?, ?> map) {
        if(isNullOrEmpty(map)) {
            return new Properties();
        }
        Properties properties = new Properties();
        for(Object key : map.keySet()) {
            properties.put(key, map.get(key));
        }
        return properties;
    }

    public static final Properties toProperties(DataRepresentation dataRep) {
        if(isNullOrEmpty(dataRep)) {
            return new Properties();
        }
        return toProperties(toMap(dataRep));
    }

    public static final Properties toProperties(DataRepresentation dataRep, boolean preserveNativeType) {
        if(isNullOrEmpty(dataRep)) {
            return new Properties();
        }
        return toProperties(toMap(dataRep, preserveNativeType));
    }
}