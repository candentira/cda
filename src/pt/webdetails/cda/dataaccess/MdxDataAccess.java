package pt.webdetails.cda.dataaccess;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Element;
import org.pentaho.reporting.engine.classic.core.DataFactory;
import org.pentaho.reporting.engine.classic.extensions.datasources.mondrian.BandedMDXDataFactory;
import org.pentaho.reporting.engine.classic.extensions.datasources.mondrian.DefaultCubeFileProvider;
import pt.webdetails.cda.connections.InvalidConnectionException;
import pt.webdetails.cda.connections.mondrian.JdbcConnection;
import pt.webdetails.cda.connections.mondrian.MondrianConnection;
import pt.webdetails.cda.settings.UnknownConnectionException;

/**
 * Implementation of a DataAccess that will get data from a SQL database
 * <p/>
 * User: pedro
 * Date: Feb 3, 2010
 * Time: 12:18:05 PM
 */
public class MdxDataAccess extends PREDataAccess
{

  private static final Log logger = LogFactory.getLog(MdxDataAccess.class);
  private static final String TYPE = "mdx";

  public MdxDataAccess(final Element element)
  {
    super(element);
  }

  
  public String getType()
  {
    return TYPE;
  }


  @Override
  public DataFactory getDataFactory() throws UnknownConnectionException, InvalidConnectionException
  {

    logger.debug("Creating BandedMDXDataFactory");

    final MondrianConnection connection = (MondrianConnection) getCdaSettings().getConnection(getConnectionId());

    final BandedMDXDataFactory mdxDataFactory = new BandedMDXDataFactory();
    mdxDataFactory.setDataSourceProvider(connection.getInitializedDataSourceProvider());
    mdxDataFactory.setCubeFileProvider(new DefaultCubeFileProvider(connection.getConnectionInfo().getCatalog()));


    if (connection instanceof JdbcConnection)
    {

      mdxDataFactory.setJdbcUser(((JdbcConnection) connection).getConnectionInfo().getUser());
      mdxDataFactory.setJdbcPassword(((JdbcConnection) connection).getConnectionInfo().getPass());

    }

    mdxDataFactory.setQuery("query", getQuery());

    return mdxDataFactory;


  }


}