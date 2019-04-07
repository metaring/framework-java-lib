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

public class AsyncResponse {

    protected Throwable errorCause;

    public AsyncResponse(Throwable errorCause) {
        this.errorCause = errorCause;
    }

    public AsyncResponse() {

    }

    public AsyncResponse setErrorCause(Throwable errorCause) {
        this.errorCause = errorCause;
        return this;
    }

    public Throwable getErrorCause() {
        return this.errorCause;
    }

    public Exception getException() {
        return hasException() ? (Exception) this.errorCause : null;
    }

    public boolean hasError() {
        return this.errorCause != null;
    }

    public boolean hasException() {
        return this.errorCause != null && this.errorCause instanceof Exception;
    }

    public boolean success() {
        return this.errorCause == null;
    }
}
