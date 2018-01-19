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
package pt.webdetails.cda.dataaccess;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Element;
import org.pentaho.reporting.engine.classic.core.DataFactory;
import org.pentaho.reporting.engine.classic.core.modules.misc.datafactory.sql.SQLReportDataFactory;
import pt.webdetails.cda.connections.ConnectionCatalog.ConnectionType;
import pt.webdetails.cda.connections.InvalidConnectionException;
import pt.webdetails.cda.connections.dataservices.DataservicesConnection;
import pt.webdetails.cda.settings.UnknownConnectionException;

/**
 * Implementation of a DataAccess that will get data from a Pentaho Data Service
 */
public class DataservicesDataAccess extends PREDataAccess {

  private static final Log logger = LogFactory.getLog( DataservicesDataAccess.class );
  private static final String TYPE = "dataservices";

  public DataservicesDataAccess( final Element element ) {
    super( element );
  }

  public DataservicesDataAccess() {
  }

  public DataFactory getDataFactory() throws UnknownConnectionException, InvalidConnectionException {
    logger.debug( "Creating DataServicesDataFactory" );

    final DataservicesConnection connection = (DataservicesConnection) getCdaSettings().getConnection( getConnectionId() );
    final SQLReportDataFactory reportDataFactory = new SQLReportDataFactory( connection.getInitializedConnectionProvider() );

    // using deprecated version for 3.9/3.10 support until it breaks with latest
    reportDataFactory.setQuery( "query", getQuery() );
    // reportDataFactory.setQuery("query", getQuery(), null, null);

    return reportDataFactory;
  }

  public String getType() {
    return TYPE;
  }

  @Override
  public ConnectionType getConnectionType() {
    return ConnectionType.DATASERVICES;
  }
}
