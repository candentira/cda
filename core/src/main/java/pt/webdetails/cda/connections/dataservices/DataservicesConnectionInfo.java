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

import org.dom4j.Element;

import java.util.List;
import java.util.Properties;

public class DataservicesConnectionInfo {

  private Properties properties;

  public DataservicesConnectionInfo( final Element connection ) {
    properties = new Properties();

    final List<?> list = connection.elements( "Property" );
    for ( int i = 0; i < list.size(); i++ ) {
      final Element childElement = (Element) list.get( i );
      final String name = childElement.attributeValue( "name" );
      final String text = childElement.getText();
      properties.put( name, text );
    }
  }

  public Properties getProperties() {
    return properties;
  }
}
