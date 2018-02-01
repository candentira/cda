/*
 * Copyright 2018 Hitachi Vantara. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 */
package pt.webdetails.cda.connections.dataservices;

import org.pentaho.di.trans.dataservice.client.DataServiceClientService;
import org.pentaho.di.trans.dataservice.jdbc.ThinConnection;
import org.pentaho.platform.engine.core.system.PentahoSystem;
import org.pentaho.reporting.engine.classic.core.modules.misc.datafactory.sql.DriverConnectionProvider;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Supplier;

public class DataservicesDriverLocalConnectionProvider extends DriverConnectionProvider {

  private Supplier<List<DataServiceClientService>> serviceSupplier;

  public DataservicesDriverLocalConnectionProvider() {
    this(DataservicesDriverLocalConnectionProvider.defaultSupplier());
  }

  public DataservicesDriverLocalConnectionProvider(Supplier<List<DataServiceClientService>> serviceSupplier) {
    this.serviceSupplier = serviceSupplier;
  }

  private static Supplier<List<DataServiceClientService>> defaultSupplier() {
    return () -> PentahoSystem.getAll(DataServiceClientService.class);
  }

  @Override public Connection createConnection( String user, String password ) throws SQLException {
    if ( ThinConnection.localClient == null ) {
      List<DataServiceClientService> listDataServiceClientServices = this.serviceSupplier.get();
      if ( listDataServiceClientServices != null && listDataServiceClientServices.size() > 0 ) {
        // gets the first one... if later more than one implementation exists, we need to think on how to filter
        // the correct one
        ThinConnection.localClient = (DataServiceClientService) listDataServiceClientServices.get( 0 );
      }
    }

    return super.createConnection( user, password );
  }
}
