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

import java.net.URI;
import java.util.Map;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.time.LocalDateTime;

import com.sap.cloud.sdk.cloudplatform.logging.CloudLoggerFactory;
import com.sap.cloud.sdk.odatav2.connectivity.ODataException;

import com.sap.cloud.sdk.s4hana.datamodel.odata.helper.Order;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.salesorder.SalesOrder;
import com.sap.cloud.sdk.s4hana.datamodel.odata.services.DefaultSalesOrderService;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletResponse;

public class OrdersDataStore {

  private static final Logger logger = CloudLoggerFactory.getLogger(OrdersDataStore.class);

  // Data accessors
  public Map<String, Object> getOrder(final String salesOrderStart) {
    Map<String, Object> data = null;

    try {

      logger.error("Right after try");
      final List<SalesOrder> salesOrders =
              new DefaultSalesOrderService()
                      .getAllSalesOrder()
                      .select(SalesOrder.SALES_ORDER,
                              SalesOrder.SALES_ORDER_TYPE,
                              SalesOrder.SALES_ORGANIZATION,
                              SalesOrder.ORGANIZATION_DIVISION,
                              SalesOrder.DISTRIBUTION_CHANNEL,
                              SalesOrder.PURCHASE_ORDER_BY_CUSTOMER,
                              SalesOrder.REQUESTED_DELIVERY_DATE,
                              SalesOrder.INCOTERMS_CLASSIFICATION,
                              SalesOrder.SOLD_TO_PARTY,
                              SalesOrder.SHIPPING_TYPE,
                              SalesOrder.INCOTERMS_LOCATION1,
                              SalesOrder.INCOTERMS_VERSION,
                              SalesOrder.CUSTOMER_PAYMENT_TERMS,
                              SalesOrder.REFERENCE_SD_DOCUMENT)
                      .orderBy(SalesOrder.SOLD_TO_PARTY, Order.ASC)
                      .filter(SalesOrder.SALES_ORDER.eq(salesOrderStart))
                      .execute();
      //                           .execute(new ErpConfigContext("ErpQueryEndpointSteve"));

      logger.error("Right after execute");

      SalesOrder salesOrder = salesOrders.get(0);

      data = createOrder(salesOrder.getSalesOrder(), salesOrder.getSalesOrderType(), salesOrder.getSalesOrganization(),
              salesOrder.getOrganizationDivision(), salesOrder.getDistributionChannel(),
              salesOrder.getPurchaseOrderByCustomer(), salesOrder.getRequestedDeliveryDate(),
              salesOrder.getIncotermsClassification(), salesOrder.getSoldToParty(), salesOrder.getCustomerPaymentTerms());

    } catch (final ODataException e) {
      logger.error(e.getMessage(), e);
    }

    return data;
  }

  private Map<String, Object> createOrder(final String orderNumber, final String orderType, final String organization,
      final String division, final String channel, final String poNumber, final LocalDateTime reqDelDate, final String inco,
      final String soldTo, final String custPayTerms)

  {
    Map<String, Object> data = new HashMap<String, Object>();

    data.put("SalesOrderStart", orderNumber);
    data.put("SalesOrderEnd", " ");
    data.put("SalesOrderType", orderType);
    data.put("SalesOrganization", organization);
    data.put("DistributionChannel", channel);
    data.put("OrganizationDivision", division);
    data.put("SoldToParty", soldTo);
    data.put("PurchaseOrderByCustomer", poNumber);
    //data.put("RequestedDeliveryDate", "2019-04-12T15:00");
    data.put("ShippingType", " ");
    data.put("IncotermsClassification", inco);
    data.put("IncotermsLocation1", " ");
    data.put("IncotermsVersion", " ");
    data.put("CustomerPaymentTerms", custPayTerms);
    data.put("ReferenceSDDocument", " ");
    data.put("Material", " ");
    data.put("RequestedQuantity", 25);
    data.put("RequestedQuantityUnit", "TON");
    data.put("ProductionPlant", "1700");
    data.put("NbrOrdersToBeCreated", 1);

    return data;
  }


 public List<Map<String, Object>> getOrders() {
    List<Map<String, Object>> orders = new ArrayList<Map<String, Object>>();
    orders.add(getOrder("0030000006"));
    orders.add(getOrder("0030000001"));

    return orders;
  }

}
