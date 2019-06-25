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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class StringUtil {
    public static String firstLetterToUpperCase(String aString) {
        if (aString == null) {
            return null;
        }
        if (aString.isEmpty()) {
            return "";
        }
        if (aString.length() == 1) {
            return aString.toUpperCase();
        }
        return new StringBuilder().append(aString.charAt(0)).append("").toString().toUpperCase() + aString.substring(1);
    }

    public static String firstLetterToLowerCase(String aString) {
        if (aString == null) {
            return null;
        }
        if (aString.isEmpty()) {
            return "";
        }
        if (aString.length() == 1) {
            return aString.toLowerCase();
        }
        return new StringBuilder().append(aString.charAt(0)).append("").toString().toLowerCase() + aString.substring(1);
    }

    public static String toStaticCase(String aString) {
        if (aString == null) {
            return null;
        }
        if (aString.isEmpty()) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        boolean digit = false;
        for (int i = 0; i < aString.length(); ++i) {
            char c = aString.charAt(i);
            if (i > 0) {
                if (Character.isDigit(c)) {
                    if (!digit) {
                        stringBuilder.append("_");
                        digit = true;
                    }
                } else {
                    digit = false;
                    if (Character.isUpperCase(c)) {
                        stringBuilder.append("_");
                    }
                }
            }
            stringBuilder.append(c);
        }
        String result = stringBuilder.toString();
        result = result.toLowerCase();
        return result;
    }

    public static String toStaticFieldName(String aString) {
        String result = StringUtil.toStaticCase(aString);
        if (result != null) {
            result = result.toUpperCase();
        }
        return result;
    }

    public static final String toCamelCase(String aString) {
        if (aString == null || aString.trim().isEmpty()) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        boolean first = true;
        StringTokenizer st = new StringTokenizer(aString.toLowerCase(), "_");
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (first) {
                first = false;
                token = StringUtil.firstLetterToLowerCase(token);
            } else {
                token = StringUtil.firstLetterToUpperCase(token);
            }
            stringBuilder.append(token);
        }
        return stringBuilder.toString();
    }

    public static final String fromThrowableToString(Throwable t) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintWriter printWriter = new PrintWriter(baos);
        t.printStackTrace(printWriter);
        try {
            printWriter.flush();
            baos.flush();
            printWriter.close();
            baos.close();
        } catch (IOException e) {
        }
        String errorMessage = "\n" + new String(baos.toByteArray()).trim();
        return errorMessage;
    }

    public static final boolean isNullOrEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    public static final boolean isNullOrEmpty(Object o) {
        if(o == null) {
            return true;
        }
        return isNullOrEmpty(o.toString());
    }
}
