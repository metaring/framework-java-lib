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

import com.metaring.framework.ExecutionEnvironment;
import com.metaring.framework.SysKB;
import com.metaring.framework.Tools;
import com.metaring.framework.type.DataRepresentation;
import com.metaring.framework.type.Email;
import com.metaring.framework.type.series.DigitSeries;
import com.metaring.framework.type.series.EmailSeries;
import com.metaring.framework.type.series.RealDigitSeries;
import com.metaring.framework.type.series.TextSeries;
import com.metaring.framework.type.series.TruthSeries;
import com.metaring.framework.util.log.LogMessageType;
import com.metaring.framework.util.log.Logger;

public abstract class ToolsAccessor {

    protected final Email newEmail(String username, String domainName, String domainLocation) {
        return Tools.FACTORY_EMAIL.create(username, domainName, domainLocation);
    }

    protected final Email newEmail(String email) {
        return Tools.FACTORY_EMAIL.create(email);
    }

    protected final Email emailFromJson(String json) {
        return Tools.FACTORY_EMAIL.fromJson(json);
    }

    protected final EmailSeries newEmailSeries(Email... items) {
        return Tools.FACTORY_EMAIL_SERIES.create(items);
    }

    protected final EmailSeries newEmailSeries(Iterable<Email> items) {
        return Tools.FACTORY_EMAIL_SERIES.create(items);
    }

    protected final EmailSeries emailSeriesFromJson(String json) {
        return Tools.FACTORY_EMAIL_SERIES.fromJson(json);
    }

    protected final DigitSeries newDigitSeries(Long... items) {
        return Tools.FACTORY_DIGIT_SERIES.create(items);
    }

    protected final DigitSeries newDigitSeries(Iterable<Long> items) {
        return Tools.FACTORY_DIGIT_SERIES.create(items);
    }

    protected final DigitSeries digitSeriesFromJson(String json) {
        return Tools.FACTORY_DIGIT_SERIES.fromJson(json);
    }

    protected final RealDigitSeries newRealDigitSeries(Double... items) {
        return Tools.FACTORY_REAL_DIGIT_SERIES.create(items);
    }

    protected final RealDigitSeries newRealDigitSeries(Iterable<Double> items) {
        return Tools.FACTORY_REAL_DIGIT_SERIES.create(items);
    }

    protected final RealDigitSeries realDigitSeriesFromJson(String json) {
        return Tools.FACTORY_REAL_DIGIT_SERIES.fromJson(json);
    }

    protected final TruthSeries newTruthSeries(Boolean... items) {
        return Tools.FACTORY_TRUTH_SERIES.create(items);
    }

    protected final TruthSeries newTruthSeries(Iterable<Boolean> items) {
        return Tools.FACTORY_TRUTH_SERIES.create(items);
    }

    protected final TruthSeries truthSeriesFromJson(String json) {
        return Tools.FACTORY_TRUTH_SERIES.fromJson(json);
    }
  
    protected final TextSeries newTextSeries(String... items) {
        return Tools.FACTORY_TEXT_SERIES.create(items);
    }

    protected final TextSeries newTextSeries(Iterable<String> items) {
        return Tools.FACTORY_TEXT_SERIES.create(items);
    }

    protected final TextSeries textSeriesFromJson(String json) {
        return Tools.FACTORY_TEXT_SERIES.fromJson(json);
    }

    protected final LogMessageType logMessageTypeENTERING() {
        return Tools.PROVIDER_LOG_MESSAGE_TYPE.ENTERING();
    }

    protected final LogMessageType logMessageTypeEXITING() {
        return Tools.PROVIDER_LOG_MESSAGE_TYPE.EXITING();
    }

    protected final LogMessageType logMessageTypeINFO() {
        return Tools.PROVIDER_LOG_MESSAGE_TYPE.INFO();
    }

    protected final LogMessageType logMessageTypeDEBUG() {
        return Tools.PROVIDER_LOG_MESSAGE_TYPE.DEBUG();
    }

    protected final LogMessageType logMessageTypeCONFIG() {
        return Tools.PROVIDER_LOG_MESSAGE_TYPE.CONFIG();
    }

    protected final LogMessageType logMessageTypeWARNING() {
        return Tools.PROVIDER_LOG_MESSAGE_TYPE.WARNING();
    }

    protected final LogMessageType logMessageTypeSEVERE() {
        return Tools.PROVIDER_LOG_MESSAGE_TYPE.SEVERE();
    }

    protected final LogMessageType logMessageTypeByName(String levelName) {
        return Tools.PROVIDER_LOG_MESSAGE_TYPE.getByName(levelName);
    }

    protected final LogMessageType logMessageTypeByPriorityLevel(Long priority) {
        return Tools.PROVIDER_LOG_MESSAGE_TYPE.getByPriorityLevel(priority);
    }

    protected final Logger newLogger(String logName) {
        return Tools.FACTORY_LOGGER.create(logName);
    }

    protected final Logger newLogger(String logName, Long depth) {
        return Tools.FACTORY_LOGGER.create(logName, depth);
    }

    protected final Logger newLogger(String logName, LogMessageType messageType) {
        return Tools.FACTORY_LOGGER.create(logName, messageType);
    }

    protected final Logger newLogger(String logName, LogMessageType messageType, Long depth) {
        return Tools.FACTORY_LOGGER.create(logName, messageType, depth);
    }

    protected final DataRepresentation dataRepresentationfromObject(Object object) {
        return Tools.FACTORY_DATA_REPRESENTATION.fromObject(object);
    }

    protected final DataRepresentation newDataRepresentation() {
        return Tools.FACTORY_DATA_REPRESENTATION.create();
    }

    protected final DataRepresentation dataRepresentationFromJson(String json) {
        return Tools.FACTORY_DATA_REPRESENTATION.fromJson(json);
    }

    protected final ExecutionEnvironment executionEnvironmentTEST() {
        return Tools.PROVIDER_EXECUTION_ENVIRONMENT.TEST();
    }

    protected final ExecutionEnvironment executionEnvironmentDEVELOPMENT() {
        return Tools.PROVIDER_EXECUTION_ENVIRONMENT.DEVELOPMENT();
    }

    protected final ExecutionEnvironment executionEnvironmentPRODUCTION() {
        return Tools.PROVIDER_EXECUTION_ENVIRONMENT.PRODUCTION();
    }

    protected final ExecutionEnvironment executionEnvironmentByName(String environmentString) {
        return Tools.PROVIDER_EXECUTION_ENVIRONMENT.getByName(environmentString);
    }

    protected final SysKB loadSysKB(String fileName) {
        return Tools.FACTORY_SYSKB.load(fileName);
    }

    protected final SysKB newSysKB(String systemName) {
        return Tools.FACTORY_SYSKB.create(systemName);
    }

    protected final SysKB newSysKB(String systemName, LogMessageType logMessageType) {
        return Tools.FACTORY_SYSKB.create(systemName,  logMessageType);
    }

    protected final SysKB newSysKB(String systemName, Boolean test) {
        return Tools.FACTORY_SYSKB.create(systemName, test);
    }

    protected final SysKB newSysKB(String systemName, LogMessageType logMessageType, Boolean test) {
        return Tools.FACTORY_SYSKB.create(systemName, logMessageType, test);
    }

    protected final SysKB newSysKB(DataRepresentation dataRepresentation) {
        return Tools.FACTORY_SYSKB.create(dataRepresentation);
    }

}