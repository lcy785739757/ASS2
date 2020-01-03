package ujs.mlearn.db;

import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

public class DataSourceManager {
	private static DataSource ds;
	static {
		try {
			InputStream in = DataSourceManager.class.getClassLoader().
					getResourceAsStream("dbcpconfig.properties");
			Properties props = new Properties();
			props.load(in);
			ds = BasicDataSourceFactory.createDataSource(props);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static DataSource getDataSource(){
		return ds;
	}
}
