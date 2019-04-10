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

import java.util.ArrayList;
import java.util.List;

import org.apache.olingo.odata2.api.edm.EdmConcurrencyMode;
import org.apache.olingo.odata2.api.edm.EdmMultiplicity;
import org.apache.olingo.odata2.api.edm.EdmSimpleTypeKind;
import org.apache.olingo.odata2.api.edm.EdmTargetPath;
import org.apache.olingo.odata2.api.edm.FullQualifiedName;
import org.apache.olingo.odata2.api.edm.provider.Association;
import org.apache.olingo.odata2.api.edm.provider.AssociationEnd;
import org.apache.olingo.odata2.api.edm.provider.AssociationSet;
import org.apache.olingo.odata2.api.edm.provider.AssociationSetEnd;
import org.apache.olingo.odata2.api.edm.provider.ComplexProperty;
import org.apache.olingo.odata2.api.edm.provider.ComplexType;
import org.apache.olingo.odata2.api.edm.provider.CustomizableFeedMappings;
import org.apache.olingo.odata2.api.edm.provider.EdmProvider;
import org.apache.olingo.odata2.api.edm.provider.EntityContainer;
import org.apache.olingo.odata2.api.edm.provider.EntityContainerInfo;
import org.apache.olingo.odata2.api.edm.provider.EntitySet;
import org.apache.olingo.odata2.api.edm.provider.EntityType;
import org.apache.olingo.odata2.api.edm.provider.Facets;
import org.apache.olingo.odata2.api.edm.provider.FunctionImport;
import org.apache.olingo.odata2.api.edm.provider.Key;
import org.apache.olingo.odata2.api.edm.provider.NavigationProperty;
import org.apache.olingo.odata2.api.edm.provider.Property;
import org.apache.olingo.odata2.api.edm.provider.PropertyRef;
import org.apache.olingo.odata2.api.edm.provider.ReturnType;
import org.apache.olingo.odata2.api.edm.provider.Schema;
import org.apache.olingo.odata2.api.edm.provider.SimpleProperty;
import org.apache.olingo.odata2.api.exception.ODataException;

public class OrdersEdmProvider extends EdmProvider {

  static final String ENTITY_SET_NAME_ORDERS = "Orders";
  static final String ENTITY_NAME_ORDER = "Order";

  private static final String NAMESPACE = "com.cfindustries.s4sdk.ODataOrders";

  private static final FullQualifiedName ENTITY_TYPE_1_1 = new FullQualifiedName(NAMESPACE, ENTITY_NAME_ORDER);

  private static final String ENTITY_CONTAINER = "ODataOrdersEntityContainer";

  @Override
  public List<Schema> getSchemas() throws ODataException {
    List<Schema> schemas = new ArrayList<Schema>();

    Schema schema = new Schema();
    schema.setNamespace(NAMESPACE);

    List<EntityType> entityTypes = new ArrayList<EntityType>();
    entityTypes.add(getEntityType(ENTITY_TYPE_1_1));
    schema.setEntityTypes(entityTypes);

    List<EntityContainer> entityContainers = new ArrayList<EntityContainer>();
    EntityContainer entityContainer = new EntityContainer();
    entityContainer.setName(ENTITY_CONTAINER).setDefaultEntityContainer(true);

    List<EntitySet> entitySets = new ArrayList<EntitySet>();
    entitySets.add(getEntitySet(ENTITY_CONTAINER, ENTITY_SET_NAME_ORDERS));
    entityContainer.setEntitySets(entitySets);

    entityContainers.add(entityContainer);
    schema.setEntityContainers(entityContainers);

    schemas.add(schema);

    return schemas;
  }

  @Override
  public EntityType getEntityType(final FullQualifiedName edmFQName) throws ODataException {
    if (NAMESPACE.equals(edmFQName.getNamespace())) {

      if (ENTITY_TYPE_1_1.getName().equals(edmFQName.getName())) {

        // Properties
        List<Property> properties = new ArrayList<Property>();

        properties.add(new SimpleProperty().setName("SalesOrderStart").setType(EdmSimpleTypeKind.String).setFacets(
                new Facets().setMaxLength(10)));
        properties.add(new SimpleProperty().setName("SalesOrderEnd").setType(EdmSimpleTypeKind.String).setFacets(
                new Facets().setMaxLength(10)));

        properties.add(new SimpleProperty().setName("SalesOrderType").setType(EdmSimpleTypeKind.String).setFacets(
                new Facets().setMaxLength(4)));

        properties.add(new SimpleProperty().setName("SalesOrganization").setType(EdmSimpleTypeKind.String).setFacets(
                new Facets().setMaxLength(4)));

        properties.add(new SimpleProperty().setName("DistributionChannel").setType(EdmSimpleTypeKind.String).setFacets(
                new Facets().setMaxLength(2)));

        properties.add(new SimpleProperty().setName("OrganizationDivision").setType(EdmSimpleTypeKind.String).setFacets(
                new Facets().setMaxLength(2)));

        properties.add(new SimpleProperty().setName("SoldToParty").setType(EdmSimpleTypeKind.String).setFacets(
                new Facets().setMaxLength(10)));

        properties.add(new SimpleProperty().setName("PurchaseOrderByCustomer").setType(EdmSimpleTypeKind.String).setFacets(
                new Facets().setMaxLength(35)));

  /*      properties.add(new SimpleProperty().setName("RequestedDeliveryDate").setType(EdmSimpleTypeKind.DateTime).setFacets(
                new Facets().setPrecision(0))); */

        properties.add(new SimpleProperty().setName("ShippingType").setType(EdmSimpleTypeKind.String).setFacets(
                new Facets().setMaxLength(2)));

        properties.add(new SimpleProperty().setName("IncotermsClassification").setType(EdmSimpleTypeKind.String).setFacets(
                new Facets().setMaxLength(3)));

        properties.add(new SimpleProperty().setName("IncotermsLocation1").setType(EdmSimpleTypeKind.String).setFacets(
                new Facets().setMaxLength(70)));

        properties.add(new SimpleProperty().setName("IncotermsVersion").setType(EdmSimpleTypeKind.String).setFacets(
                new Facets().setMaxLength(4)));

        properties.add(new SimpleProperty().setName("CustomerPaymentTerms").setType(EdmSimpleTypeKind.String).setFacets(
                new Facets().setMaxLength(4)));

        properties.add(new SimpleProperty().setName("ReferenceSDDocument").setType(EdmSimpleTypeKind.String).setFacets(
                new Facets().setMaxLength(10)));

        properties.add(new SimpleProperty().setName("Material").setType(EdmSimpleTypeKind.String).setFacets(
                new Facets().setMaxLength(40)));

        properties.add(new SimpleProperty().setName("RequestedQuantity").setType(EdmSimpleTypeKind.Decimal).setFacets(
                new Facets().setPrecision(15).setScale(3)));

        properties.add(new SimpleProperty().setName("RequestedQuantityUnit").setType(EdmSimpleTypeKind.String).setFacets(
                new Facets().setMaxLength(3)));

        properties.add(new SimpleProperty().setName("ProductionPlant").setType(EdmSimpleTypeKind.String).setFacets(
                new Facets().setMaxLength(4)));

        properties.add(new SimpleProperty().setName("NbrOrdersToBeCreated").setType(EdmSimpleTypeKind.Int16));

        // Key
        List<PropertyRef> keyProperties = new ArrayList<PropertyRef>();
        keyProperties.add(new PropertyRef().setName("SalesOrderStart"));
        Key key = new Key().setKeys(keyProperties);

        return new EntityType().setName(ENTITY_TYPE_1_1.getName())
                .setProperties(properties)
                .setKey(key);

      }

    }

    return null;
  }



  @Override
  public EntitySet getEntitySet(final String entityContainer, final String name) throws ODataException {
    if (ENTITY_CONTAINER.equals(entityContainer)) {
      if (ENTITY_SET_NAME_ORDERS.equals(name)) {
        return new EntitySet().setName(name).setEntityType(ENTITY_TYPE_1_1);
      }
    }
    return null;
  }



  @Override
  public EntityContainerInfo getEntityContainerInfo(final String name) throws ODataException {
    if (name == null || "ODataOrdersEntityContainer".equals(name)) {
      return new EntityContainerInfo().setName("ODataOrdersEntityContainer").setDefaultEntityContainer(true);
    }

    return null;
  }
}
