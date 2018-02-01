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

import org.pentaho.di.trans.dataservice.client.DataServiceClientPlugin;
import org.pentaho.reporting.engine.classic.core.modules.misc.datafactory.sql.DriverConnectionProvider;
import pt.webdetails.cpf.PluginEnvironment;

import java.net.MalformedURLException;
import java.net.URL;

public class DataservicesLocalConnection implements IDataservicesLocalConnection {

  public DriverConnectionProvider getDriverConnectionProvider() throws MalformedURLException {
    final DriverConnectionProvider connectionProvider = new DataservicesDriverLocalConnectionProvider();

    String driver = new DataServiceClientPlugin().getDriverClass();
    connectionProvider.setDriver( driver );

    URL contextRootUrl = new URL( PluginEnvironment.env().getUrlProvider().getWebappContextRoot());
    String hostname = contextRootUrl.getHost();
    String port = String.valueOf( contextRootUrl.getPort() );
    String path = PluginEnvironment.env().getUrlProvider().getWebappContextPath().replace( "/", "" );
    String url = new DataServiceClientPlugin().getURL(hostname, port, path) + "?local=true";
    connectionProvider.setUrl( url );
    return connectionProvider;
  }
}
