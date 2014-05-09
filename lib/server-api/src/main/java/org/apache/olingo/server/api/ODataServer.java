/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.olingo.server.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.olingo.commons.api.ODataRuntimeException;
import org.apache.olingo.server.api.serializer.ODataFormat;
import org.apache.olingo.server.api.serializer.ODataSerializer;

public abstract class ODataServer {

  private static final String IMPLEMENTATION = "org.apache.olingo.server.core.ODataServerImpl";

  public static ODataServer newInstance() {
    try {
      final Class<?> clazz = Class.forName(ODataServer.IMPLEMENTATION);

      /*
       * We explicitly do not use the singleton pattern to keep the server state free
       * and avoid class loading issues also during hot deployment.
       */
      final Object object = clazz.newInstance();
      return (ODataServer) object;

    } catch (final Exception e) {
      // TODO: Change to ODataRuntimeExcfeption
      throw new ODataRuntimeException(e);
    }
  }

  public abstract ODataSerializer getSerializer(ODataFormat format);

  public abstract void handle(HttpServletRequest req, HttpServletResponse resp);

}
