/*******************************************************************************
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
 ******************************************************************************/
package com.cfindustries.s4sdk;

import static com.cfindustries.s4sdk.OrdersEdmProvider.ENTITY_SET_NAME_ORDERS;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.olingo.odata2.api.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.edm.EdmLiteralKind;
import org.apache.olingo.odata2.api.edm.EdmProperty;
import org.apache.olingo.odata2.api.edm.EdmSimpleType;
import org.apache.olingo.odata2.api.ep.EntityProvider;
import org.apache.olingo.odata2.api.ep.EntityProviderWriteProperties;
import org.apache.olingo.odata2.api.ep.EntityProviderWriteProperties.ODataEntityProviderPropertiesBuilder;
import org.apache.olingo.odata2.api.exception.ODataException;
import org.apache.olingo.odata2.api.exception.ODataNotFoundException;
import org.apache.olingo.odata2.api.exception.ODataNotImplementedException;
import org.apache.olingo.odata2.api.processor.ODataResponse;
import org.apache.olingo.odata2.api.processor.ODataSingleProcessor;
import org.apache.olingo.odata2.api.uri.KeyPredicate;
import org.apache.olingo.odata2.api.uri.info.GetEntitySetUriInfo;
import org.apache.olingo.odata2.api.uri.info.GetEntityUriInfo;

public class OrdersODataSingleProcessor extends ODataSingleProcessor {

  private final OrdersDataStore dataStore;

  public OrdersODataSingleProcessor() {
    dataStore = new OrdersDataStore();
  }

  @Override
  public ODataResponse readEntitySet(final GetEntitySetUriInfo uriInfo, final String contentType) 
      throws ODataException {

    EdmEntitySet entitySet;

    if (uriInfo.getNavigationSegments().size() == 0) {
      entitySet = uriInfo.getStartEntitySet();

    if (ENTITY_SET_NAME_ORDERS.equals(entitySet.getName())) {
        return EntityProvider.writeFeed(contentType, entitySet, dataStore.getOrders(),
            EntityProviderWriteProperties.serviceRoot(getContext().getPathInfo().getServiceRoot()).build());
      }

      throw new ODataNotFoundException(ODataNotFoundException.ENTITY);

    }

    throw new ODataNotImplementedException();
  }

  @Override
  public ODataResponse readEntity(final GetEntityUriInfo uriInfo, final String contentType) throws ODataException {

    if (uriInfo.getNavigationSegments().size() == 0) {
      EdmEntitySet entitySet = uriInfo.getStartEntitySet();

      if (ENTITY_SET_NAME_ORDERS.equals(entitySet.getName())) {
        String salesOrderStart = getKeyValue(uriInfo.getKeyPredicates().get(0));
        Map<String, Object> data = dataStore.getOrder(salesOrderStart);

        if (data != null) {
          URI serviceRoot = getContext().getPathInfo().getServiceRoot();
          ODataEntityProviderPropertiesBuilder propertiesBuilder =
                  EntityProviderWriteProperties.serviceRoot(serviceRoot);

          return EntityProvider.writeEntry(contentType, entitySet, data, propertiesBuilder.build());
        }
      }
      throw new ODataNotFoundException(ODataNotFoundException.ENTITY);

    }
    throw new ODataNotImplementedException();
  }

  private String getKeyValue(final KeyPredicate key) throws ODataException {
    EdmProperty property = key.getProperty();
    EdmSimpleType type = (EdmSimpleType) property.getType();
    return type.valueOfString(key.getLiteral(), EdmLiteralKind.DEFAULT, property.getFacets(), String.class);
  }
}
