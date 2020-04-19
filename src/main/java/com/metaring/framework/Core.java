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

package com.metaring.framework;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.io.Resource;

import com.metaring.framework.type.DataRepresentation;
import com.metaring.framework.type.series.TextSeries;
import com.metaring.framework.util.ObjectUtil;

public class Core {

    public static final SysKB SYSKB;
    public static final TextSeries ARGUMENTS;

    private static final ApplicationContext APPLICATION_CONTEXT = new GenericApplicationContext();

    static {
        SYSKB = getSystemKB();
        String[] texts = new String[0];
        try {
            String commandLine = System.getProperty("sun.java.command");
            int mainClassNameEnd = commandLine.length();
            if (commandLine.contains(" ")) {
                mainClassNameEnd = commandLine.indexOf(" ");
            }
            if (commandLine.contains(" ")) {
                String arguments = commandLine.substring(mainClassNameEnd + 1);
                String args[] = arguments.split(" ");
                if (args != null && args.length > 0) {
                    List<String> textList = new ArrayList<>();
                    for (String arg : args) {
                        textList.add(arg);
                    }
                    texts = textList.toArray((texts = new String[textList.size()]));
                }
            }
        } catch (Exception e) {
        }

        ARGUMENTS = Tools.FACTORY_TEXT_SERIES.create(texts);
    }

    private static final SysKB getSystemKB() {
        DataRepresentation configuration = load(Resources.GLOBAL_SYSKB_FILE_NAME);
        if(ObjectUtil.isNullOrEmpty(configuration)) {
            try {
                configuration = Tools.FACTORY_SYSKB.load(Core.class.getClassLoader().getResource(Resources.GLOBAL_SYSKB_FILE_NAME).toString());
            } catch(Exception e) {
            }
        }
        if(ObjectUtil.isNullOrEmpty(configuration)) {
            String systemName = null;
            try {
                systemName = System.getProperty("sun.java.command");
                systemName = systemName.substring(systemName.contains(" ") ? systemName.indexOf(" ") : systemName.length());
                systemName = systemName.substring(systemName.contains(".") ? systemName.lastIndexOf(".") : systemName.length());
            } catch(Exception e) {
                systemName = "MetaRing";
            }
            configuration = Tools.FACTORY_DATA_REPRESENTATION.create().add("system", Tools.FACTORY_DATA_REPRESENTATION.create().add("name", systemName).add("executionEnvironment", Tools.PROVIDER_EXECUTION_ENVIRONMENT.DEVELOPMENT().getName()));
        }
        return Tools.FACTORY_SYSKB.create(load(Resources.DEFAULT_SYSKB_FILE_NAME).merge(configuration, load(Resources.LOCAL_SYSKB_FILE_NAME)));
    }

    private static final DataRepresentation load(String filename) {
        DataRepresentation dataRepresentation = Tools.FACTORY_DATA_REPRESENTATION.create();
        try {
            Resource[] resources = APPLICATION_CONTEXT.getResources("classpath*:" + filename);
            if(resources == null || resources.length == 0) {
                resources = APPLICATION_CONTEXT.getResources("classpath*:*/*/" + filename);
            }
            for(Resource resource : resources) {
                dataRepresentation.merge(Tools.FACTORY_SYSKB.load(resource.getURL().toString()));
            }
        } catch(Exception e) {
        }
        return dataRepresentation;
    }
}