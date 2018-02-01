package pt.webdetails.cda.connections.dataservices;

import org.junit.Test;
import org.pentaho.reporting.engine.classic.core.modules.misc.datafactory.sql.DriverConnectionProvider;

import java.net.MalformedURLException;
import pt.webdetails.cpf.PluginEnvironment;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import pt.webdetails.cpf.PluginEnvironment;
import pt.webdetails.cpf.context.api.IUrlProvider;

public class DataservicesLocalConnectionTest {

  @Test
  public void getDriverConnectionProviderTest() throws MalformedURLException {
    PluginEnvironment mockEnv = mock(PluginEnvironment.class);
    IUrlProvider mockUrlProvider = mock(IUrlProvider.class);
    doReturn( "http://localhost:8080" ).when( mockUrlProvider ).getWebappContextRoot();
    doReturn( "/pentaho" ).when( mockUrlProvider ).getWebappContextPath();
    doReturn( mockUrlProvider ).when( mockEnv ).getUrlProvider();
    PluginEnvironment.init(mockEnv);
    DriverConnectionProvider connectionProvider = new DataservicesLocalConnection().getDriverConnectionProvider();
    assertNotNull("Driver shouldn't be empty", connectionProvider.getDriver());
    assertNotNull("Url shouldn't be empty", connectionProvider.getUrl());
    assertEquals("jdbc:pdi://localhost:8080/pentaho/kettle?local=true", connectionProvider.getUrl());
  }
}
