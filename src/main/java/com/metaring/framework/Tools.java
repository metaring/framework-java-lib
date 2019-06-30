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

import java.util.function.Function;
import java.util.stream.Stream;

import com.metaring.framework.type.factory.DataRepresentationFactory;
import com.metaring.framework.type.factory.DigitSeriesFactory;
import com.metaring.framework.type.factory.EmailFactory;
import com.metaring.framework.type.factory.EmailSeriesFactory;
import com.metaring.framework.type.factory.RealDigitSeriesFactory;
import com.metaring.framework.type.factory.TextSeriesFactory;
import com.metaring.framework.type.factory.TruthSeriesFactory;
import com.metaring.framework.util.log.LogMessageTypeProvider;
import com.metaring.framework.util.log.LoggerFactory;

public class Tools {

    private static final Function<String, Stream<String>> FROM_JSON_TO_STRINGS_FUNCTION = DataRepresentationImpl::getStrings;

    public static final EmailFactory FACTORY_EMAIL = new EmailFactoryImpl();
    public static final EmailSeriesFactory FACTORY_EMAIL_SERIES = new EmailSeriesFactoryImpl(FROM_JSON_TO_STRINGS_FUNCTION, FACTORY_EMAIL);
    public static final DigitSeriesFactory FACTORY_DIGIT_SERIES = new DigitSeriesFactoryImpl(FROM_JSON_TO_STRINGS_FUNCTION);
    public static final RealDigitSeriesFactory FACTORY_REAL_DIGIT_SERIES = new RealDigitSeriesFactoryImpl(FROM_JSON_TO_STRINGS_FUNCTION);
    public static final TruthSeriesFactory FACTORY_TRUTH_SERIES = new TruthSeriesFactoryImpl(FROM_JSON_TO_STRINGS_FUNCTION);
    public static final TextSeriesFactory FACTORY_TEXT_SERIES = new TextSeriesFactoryImpl(FROM_JSON_TO_STRINGS_FUNCTION);
    public static final LogMessageTypeProvider PROVIDER_LOG_MESSAGE_TYPE = new LogMessageTypeImpl();
    public static final LoggerFactory FACTORY_LOGGER = new LoggerFactoryImpl(PROVIDER_LOG_MESSAGE_TYPE);
    public static final DataRepresentationFactory FACTORY_DATA_REPRESENTATION = new DataRepresentationImpl(null, FACTORY_TEXT_SERIES, FACTORY_DIGIT_SERIES, FACTORY_REAL_DIGIT_SERIES, FACTORY_TRUTH_SERIES, FACTORY_EMAIL, FACTORY_EMAIL_SERIES, PROVIDER_LOG_MESSAGE_TYPE);
    public static final ExecutionEnvironmentProvider PROVIDER_EXECUTION_ENVIRONMENT = new ExecutionEnvironmentImpl();
    public static final SysKBFactory FACTORY_SYSKB = new SysKBImpl(FACTORY_TEXT_SERIES, FACTORY_DIGIT_SERIES, FACTORY_REAL_DIGIT_SERIES, FACTORY_TRUTH_SERIES, FACTORY_EMAIL, FACTORY_EMAIL_SERIES, PROVIDER_LOG_MESSAGE_TYPE, PROVIDER_EXECUTION_ENVIRONMENT, FACTORY_LOGGER, Resources.CFG_SYSTEM_DATA_REPRESENTATION, Resources.CFG_SYSTEM_NAME, Resources.CFG_SYSTEM_EXECUTION_ENVIRONMENT, Resources.CFG_SYSTEM_LOGGER_MESSAGE_TYPE, Resources.CFG_SYSTEM_TEST_MODE);
}
