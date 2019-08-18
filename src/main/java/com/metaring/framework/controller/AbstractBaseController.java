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

package com.metaring.framework.controller;

import com.metaring.framework.Core;
import com.metaring.framework.SysKB;
import com.metaring.framework.util.StringUtil;
import com.metaring.framework.util.log.Logger;

public abstract class AbstractBaseController extends ToolsAccessor {

    private Logger logger;
    private boolean javaTest;

    public AbstractBaseController(Logger logger, Boolean test) {
        this.logger = logger;
        this.setLoggerStackPosition();
        this.setTest(test);
    }

    public AbstractBaseController(SysKB sysKB, Logger logger) {
        this(logger, Core.SYSKB.isSystemInTestMode());
    }

    public AbstractBaseController(Boolean test) {
        this(Core.SYSKB.getSystemLogger(), test);
    }

    public AbstractBaseController() {
        this(Core.SYSKB.getSystemLogger(), Core.SYSKB.isSystemInTestMode());
    }

    protected void setLoggerStackPosition() {
        long actualStackPosition = this.logger.getActualStackPosition();
        long newStackPositionLong = actualStackPosition + 1;
        Long newStackPosition = newStackPositionLong;
        this.logger.setActualStackPosition(newStackPosition);
    }

    public Boolean getTest() {
        return this.javaTest ? true : false;
    }

    public void setTest(Boolean test) {
        this.javaTest = test == true;
    }

    public Logger getLogger() {
        return this.logger;
    }

    protected boolean isTest() {
        return this.javaTest;
    }

    protected String concatenate(String message, String ... messages) {
        if (messages != null && messages.length > 0) {
            StringBuilder sb = new StringBuilder(message);
            for (int i = 0; i < messages.length; ++i) {
                String messageString = messages[i];
                sb.append(messageString);
                if (i >= messages.length - 1) continue;
                sb.append(", ");
            }
            String finalText = sb.toString();
            String concatenatedText = finalText;
            return concatenatedText;
        }
        return message;
    }

    protected void info(String messageString, String ... messages) {
        String message = this.concatenate(messageString, messages);
        this.logger.info(message);
    }

    protected void entering(String prefix) {
        this.logger.entering(prefix);
    }

    protected void entering() {
        this.logger.entering();
    }

    protected void exiting(String prefix) {
        this.logger.exiting(prefix);
    }

    protected void exiting() {
        this.logger.exiting();
    }

    protected void debug(String messageString, String ... messages) {
        String message = this.concatenate(messageString, messages);
        this.logger.debug(message);
    }

    protected void config(String parameterNameString, Object parameterValue) {
        String parameterName = parameterNameString;
        this.logger.config(parameterName, parameterValue);
    }

    protected void warning(String messageString, String ... messages) {
        String message = this.concatenate(messageString, messages);
        this.logger.warning(message);
    }

    protected void severe(String messageString, String ... messages) {
        String message = this.concatenate(messageString, messages);
        this.logger.severe(message);
    }

    protected void severe(Throwable t, String ... messages) {
        String errorMessage = StringUtil.fromThrowableToString(t);
        String message = this.concatenate(errorMessage, messages);
        this.logger.severe(message);
    }
}