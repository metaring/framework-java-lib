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

public class AsyncValuedResponse<T> extends AsyncResponse {

    private T result;

    public AsyncValuedResponse(T result) {
        super();
        this.result = result;
    }

    public AsyncValuedResponse(Throwable errorCause) {
        super(errorCause);
    }

    public AsyncValuedResponse() {
        super();
    }

    public AsyncValuedResponse<T> setResult(T result, boolean exclusion) {
        this.result = result;
        if(exclusion) {
            super.setErrorCause(null);
        }
        return this;
    }

    public T getResult() {
        return result;
    }

    public boolean hasResult() {
        return result != null;
    }

    public AsyncValuedResponse<T> setErrorCause(Throwable errorCause, boolean exclusion) {
        super.setErrorCause(errorCause);
        if(exclusion) {
            this.result = null;
        }
        return this;
    }

    @Override
    public AsyncValuedResponse<T> setErrorCause(Throwable errorCause) {
        return this.setErrorCause(errorCause, true);
    }

    public AsyncValuedResponse<T> setResult(T result) {
        return this.setResult(result, true);
    }
}
