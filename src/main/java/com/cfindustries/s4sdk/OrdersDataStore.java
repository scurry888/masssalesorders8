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
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class OrdersDataStore {

  // Data accessors
  public Map<String, Object> getOrder(final String salesOrderStart) {
    Map<String, Object> data = null;

    data = createOrder(1, "F1 W03", 1, 189189.43, "EUR", "2012", updated, "file://imagePath/w03");

    return data;
  }

  private Map<String, Object> createOrder(final int carId, final String model, final int manufacturerId,
      final double price,
      final String currency, final String modelYear, final Calendar updated, final String imagePath) {
    Map<String, Object> data = new HashMap<String, Object>();

    data.put("Id", carId);
    data.put("Model", model);
    data.put("ManufacturerId", manufacturerId);
    data.put("Price", price);
    data.put("Currency", currency);
    data.put("ModelYear", modelYear);
    data.put("Updated", updated);
    data.put("ImagePath", imagePath);

    return data;
  }


  public List<Map<String, Object>> getOrders() {
    List<Map<String, Object>> cars = new ArrayList<Map<String, Object>>();
    cars.add(getCar(1));
    cars.add(getCar(2));
    cars.add(getCar(3));
    cars.add(getCar(4));
    cars.add(getCar(5));
    return cars;
  }

}
