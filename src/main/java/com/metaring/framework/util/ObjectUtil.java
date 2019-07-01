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

import java.util.Collection;
import java.util.Map;

import com.metaring.framework.type.DataRepresentation;

public class ObjectUtil {

    public static final boolean isNullOrCollection(Object o) {
        if (o == null) {
            return true;
        }
        if (o instanceof Collection<?>) {
            Collection<?> collection = (Collection<?>) o;
            if (collection.size() == 0) {
                return true;
            }
        }

        return false;
    }

    public static final boolean isNullOrMap(Object o) {
        if (o == null) {
            return true;
            }
        if (o instanceof Map<?, ?>) {
            Map<?, ?> map = (Map<?, ?>) o;
            if (map.size() == 0) {
                return true;
            }
        }

        return false;
    }

public static final boolean dataRepresentationIsNullOrEmpty(DataRepresentation input) {
    if (input == null || input.isNull()) {
            return true;
        }
        if (input.getProperties().size() == 0 && input.length() == 0) {
            return true;
        }
        return false;
    }
}
