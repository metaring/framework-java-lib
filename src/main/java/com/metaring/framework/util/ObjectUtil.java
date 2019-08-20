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

import java.util.Map;
import java.util.Objects;
import java.util.Properties;

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


    public static final Properties toProperties(final DataRepresentation rep) {
        return toProperties(rep, null, null);
    }

    private static final Properties toProperties(final DataRepresentation initialData, Properties properties, String current) {
        properties = properties == null ? new Properties() : properties;
        if(ObjectUtil.isNullOrEmpty(initialData)) {
            return properties;
        }
        for (String currentProperty : initialData.getProperties()) {
            String propertyName = String.join(StringUtil.isNullOrEmpty(current) ? "" : ".", StringUtil.isNullOrEmpty(current) ? "" : current, currentProperty);
            DataRepresentation dataRepresentation = initialData.get(currentProperty);
            if (dataRepresentation.hasProperties()) {
                toProperties(dataRepresentation, properties, propertyName);
            } else {
                properties.put(propertyName, initialData.getText(currentProperty));
            }
        }
        return properties;
    }
}
