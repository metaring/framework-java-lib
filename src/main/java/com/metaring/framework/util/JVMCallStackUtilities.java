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

import java.lang.reflect.Field;

import org.thobe.frame.Frame;

public class JVMCallStackUtilities {

    private static final Field FRAME_LOCAL_THIS_FIELD;

    static {
        Field localThis = null;
        try {
            localThis = Frame.class.getDeclaredField("localThis");
            localThis.setAccessible(true);
        }
        catch (Exception e) {
            if(e instanceof RuntimeException) {
                throw (RuntimeException) e;
            }
            else throw new RuntimeException(e);
        }
        FRAME_LOCAL_THIS_FIELD = localThis;
    }

    public static final Frame[] getFrames() {
        return Frame.getFrames();
    }

    public static final Object getCaller() {
        return getCaller(1);
    }

    public static final Object getCaller(int position) {
        Object o = null;
        try {
            o = FRAME_LOCAL_THIS_FIELD.get((Object) Frame.getFrame((int) (position + 1)));
        }
        catch (Exception e) {
        }
        return o;
    }

    public static final Class<?> getLastCallerClass() {
        Class<?> clazz = null;
        try {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            clazz = Class.forName(stackTrace[stackTrace.length - 1].getClassName());
        }
        catch (Exception e) {
        }
        return clazz;
    }

    public static final Class<?> getCallerClass() {
        return JVMCallStackUtilities.getCallerClass(1);
    }

    public static final Class<?> getCallerClass(int position) {
        Class<?> clazz = null;
        try {
            clazz = Class.forName(Thread.currentThread().getStackTrace()[position + 2].getClassName());
        }
        catch (Exception e) {
        }
        return clazz;
    }
}
