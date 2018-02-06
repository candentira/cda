/*!
 * Copyright 2018 Webdetails, a Hitachi Vantara company. All rights reserved.
 *
 * This software was developed by Webdetails and is provided under the terms
 * of the Mozilla Public License, Version 2.0, or any later version. You may not use
 * this file except in compliance with the license. If you need a copy of the license,
 * please go to  http://mozilla.org/MPL/2.0/. The Initial Developer is Webdetails.
 *
 * Software distributed under the Mozilla Public License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. Please refer to
 * the license for the specific language governing your rights and limitations.
 */

package pt.webdetails.cda.filetests;

import pt.webdetails.cda.CdaEngine;
import pt.webdetails.cda.ICdaEnvironment;
import pt.webdetails.cda.connections.dataservices.IDataservicesLocalConnection;
import pt.webdetails.cda.query.QueryOptions;
import pt.webdetails.cda.settings.CdaSettings;

import javax.swing.table.TableModel;

import static org.mockito.Mockito.*;

public class DataserviceTest extends CdaTestCase {

  public void testSampleKettle() throws Exception {

    ICdaEnvironment env = spy(CdaEngine.getEnvironment());

    IDataservicesLocalConnection dataserviceLocalConnection = mock( IDataservicesLocalConnection.class );
    //DriverConnectionProvider connectionProvider = mock(DriverConnectionProvider.class);
    //when(connectionProvider.getUrl()).thenReturn( "jdbc:hsqldb:res:sampledata" );
    //when(connectionProvider.getDriver()).thenReturn( "org.hsqldb.jdbcDriver" );
    //doReturn( connectionProvider ).when( dataserviceLocalConnection.getDriverConnectionProvider() );
    //when( dataserviceLocalConnection.getDriverConnectionProvider() ).thenReturn( connectionProvider );
    doReturn( dataserviceLocalConnection ).when(env).getDataServicesLocalConnection();
    //when( env.getDataServicesLocalConnection() ).thenReturn( dataserviceLocalConnection );


    final CdaSettings cdaSettings = parseSettingsFile( "sample-dataservice.cda" );

    final QueryOptions queryOptions = new QueryOptions();
    queryOptions.setDataAccessId( "1" );

    CdaEngine engine = getEngine();

    TableModel table = engine.doQuery( cdaSettings, queryOptions );

  }

}
