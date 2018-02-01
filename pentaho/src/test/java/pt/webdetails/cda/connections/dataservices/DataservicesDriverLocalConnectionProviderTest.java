package pt.webdetails.cda.connections.dataservices;

import org.junit.Test;
import org.mockito.Mockito;
import org.pentaho.di.trans.dataservice.client.DataServiceClientPlugin;
import org.pentaho.di.trans.dataservice.client.DataServiceClientService;
import org.pentaho.di.trans.dataservice.jdbc.ThinConnection;
import org.pentaho.platform.engine.core.system.PentahoSystem;
import org.pentaho.reporting.engine.classic.core.modules.misc.datafactory.sql.DriverConnectionProvider;
import pt.webdetails.cpf.PluginEnvironment;
import pt.webdetails.cpf.context.api.IUrlProvider;

import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DataservicesDriverLocalConnectionProviderTest {

  @Test(expected = NullPointerException.class)
  public void getLocalConnectionProviderTestUrlRequired() throws SQLException, MalformedURLException {
    DataServiceClientService dataServiceMock = mock(DataServiceClientService.class);
    DataservicesDriverLocalConnectionProvider provider = spy(new DataservicesDriverLocalConnectionProvider(
      () -> Arrays.asList(dataServiceMock)
    ));
    provider.createConnection( "user", "password" );
  }

  @Test(expected = SQLException.class)
  public void getLocalConnectionProviderTestDriverRequired() throws SQLException, MalformedURLException {
    DataServiceClientService dataServiceMock = mock(DataServiceClientService.class);
    DataservicesDriverLocalConnectionProvider provider = spy(new DataservicesDriverLocalConnectionProvider(
      () -> Arrays.asList(dataServiceMock)
    ));
    provider.setUrl( "jdbc:pdi://localhost:8080/pentaho/kettle?local=true" );
    provider.createConnection( "user", "password" );
  }

}
