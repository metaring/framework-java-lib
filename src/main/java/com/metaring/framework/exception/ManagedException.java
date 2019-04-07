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

package com.metaring.framework.exception;

import java.util.List;

import com.metaring.framework.Tools;
import com.metaring.framework.type.series.TextSeries;

public abstract class ManagedException extends CoreException {
    private static final long serialVersionUID = 5541088841619378587L;
    private String name;
    private TextSeries payload = null;

    public ManagedException() {
        this.name = this.getClass().getSimpleName().replace((CharSequence) "Exception", (CharSequence) "");
    }

    public ManagedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.name = this.getClass().getSimpleName().replace((CharSequence) "Exception", (CharSequence) "");
    }

    public ManagedException(String message, Throwable cause) {
        super(message, cause);
        this.name = this.getClass().getSimpleName().replace((CharSequence) "Exception", (CharSequence) "");
    }

    public ManagedException(String message) {
        super(message);
        this.name = this.getClass().getSimpleName().replace((CharSequence) "Exception", (CharSequence) "");
    }

    public ManagedException(Throwable cause) {
        super(cause);
        this.name = this.getClass().getSimpleName().replace((CharSequence) "Exception", (CharSequence) "");
    }

    public final String getName() {
        return this.name;
    }

    public final TextSeries getPayload() {
        return this.payload;
    }

    protected final void fillPayload(TextSeries textSeries) {
        this.payload = textSeries;
    }

    protected final void fillPayload(String... texts) {
        if (texts == null || texts.length == 0) {
            return;
        }
        this.payload = Tools.FACTORY_TEXT_SERIES.create(texts);
    }

    @SuppressWarnings("all")
    protected final void fillPayload(List texts) {
        if (texts == null || texts.isEmpty()) {
            return;
        }
        if (texts.get(0) instanceof String) {
            this.fillPayload(((List<String>)texts).toArray(new String[texts.size()]));
        }
        else {
            String[] ts = new String[texts.size()];
            for (int i = 0; i < texts.size(); ++i) {
                ts[i] = texts.get(i).toString();
            }
            this.payload = Tools.FACTORY_TEXT_SERIES.create(ts);
        }
    }
}
