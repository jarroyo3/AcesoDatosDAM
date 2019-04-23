package db;

import java.io.File;
import java.sql.ResultSet;

import javax.xml.transform.OutputKeys;

import org.exist.util.UTF8;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.CompiledExpression;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XQueryService;

public class DatabaseCore {

	private static DatabaseCore self;

	private static final String DRIVER = "org.exist.xmldb.DatabaseImpl";
	private static final String URI_CONEXION = "xmldb:exist://localhost:8080/exist/xmlrpc/db/apps/act04";
	private static final String USERNAME = "admin";
	private static final String PASSWORD = "123";

	private String collectionName;
	private Collection collection;
	private String resourceName;

	public DatabaseCore() {
		this.collectionName = "";
		this.collection = null;
		this.resourceName = "";
	}

	public static DatabaseCore getInstance() {
		if (null == self) {
			self = new DatabaseCore();
		}

		return self;
	}

	private String getResourceName() {
		return this.resourceName;
	}

	public DatabaseCore setResourceName(String resourceName) {
		this.resourceName = resourceName;
		return this;
	}

	public DatabaseCore setCollectionName(String collectionName) {
		this.collectionName = collectionName;
		return this;
	}

	public Collection connect() {
		try {
			Database database = (Database) Class.forName(DRIVER).newInstance();
			database.setProperty("create-database", "true");
			DatabaseManager.registerDatabase(database);

			String resourceName = getResourceName();
			if ("".equals(resourceName)) {
				throw new Exception("El recurso es nulo");
			}

			this.collection = DatabaseManager.getCollection(URI_CONEXION + resourceName, USERNAME, PASSWORD);

			if (null == collection || collection.getResourceCount() == 0) {
				throw new Exception(String.format("La colección [%s] no tiene recursos [%s].",
						getOrCreateCollection(this.collectionName), URI_CONEXION + resourceName));
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		setResourceName("");
		return this.collection;
	}

	public ResultSet executeS(String queryS) {
		ResultSet r = null;
		return r;
	}

	public ResourceSet executeQ(String queryS) throws XMLDBException {
		XQueryService servicio = preparedStatement(getResourceName());
		ResourceSet resultado = servicio.query(queryS);
		return resultado;
	}

	public void update(String query) {
		try {

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Collection addCollection(String newCollection) {
		Collection newCol = null;

		try {
			CollectionManagementService mgtService = (CollectionManagementService) collection
					.getService("CollectionManagementService", "1.0");
			newCol = mgtService.createCollection(new String(UTF8.encode(newCollection)));
		} catch (XMLDBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return newCol;
	}

	public void insert(String query) throws XMLDBException {
		XQueryService servicio = preparedStatement(getResourceName());
		CompiledExpression consultaCompilada = servicio.compile(query);
		servicio.execute(consultaCompilada);
	}
	
	public void deleteById(String collection, int id) throws XMLDBException {
		String query = String.format("update delete %s[id=%s]", collection, id);
		XQueryService servicio = preparedStatement(getResourceName());
        CompiledExpression consultaCompilada = servicio.compile(query);
        servicio.execute(consultaCompilada);
	}

	private XQueryService preparedStatement(String resourceName2) throws XMLDBException {
		Collection col = connect();
		XQueryService xQueryService = (XQueryService) col.getService("XQueryService", "1.0");
		xQueryService.setProperty(OutputKeys.INDENT, "yes");
		xQueryService.setProperty(OutputKeys.ENCODING, "UTF-8");
		return xQueryService;
	}

	private static Collection getOrCreateCollection(String collectionUri) throws XMLDBException {
		return getOrCreateCollection(collectionUri, 0);
	}

	private static Collection getOrCreateCollection(String collectionUri, int pathSegmentOffset) throws XMLDBException {

		Collection col = DatabaseManager.getCollection(URI_CONEXION + collectionUri);
		if (col == null) {
			if (collectionUri.startsWith("/")) {
				collectionUri = collectionUri.substring(1);
			}
			String pathSegments[] = collectionUri.split("/");
			if (pathSegments.length > 0) {
				StringBuilder path = new StringBuilder();
				for (int i = 0; i <= pathSegmentOffset; i++) {
					path.append("/" + pathSegments[i]);
				}
				Collection start = DatabaseManager.getCollection(URI_CONEXION + path);
				if (start == null) {
					// collection does not exist, so create
					String parentPath = path.substring(0, path.lastIndexOf("/"));
					Collection parent = DatabaseManager.getCollection(URI_CONEXION + parentPath);
					CollectionManagementService mgt = (CollectionManagementService) parent
							.getService("CollectionManagementService", "1.0");
					col = mgt.createCollection(pathSegments[pathSegmentOffset]);
					col.close();
					parent.close();
				} else {
					start.close();
				}
			}
			return getOrCreateCollection(collectionUri, ++pathSegmentOffset);
		} else {
			return col;
		}
	}

	public Long Last_Insert_Id(String collection) {
		Long id = 0L;
		String query = String.format("for $q in %s/id return $q/text()", collection);
		
		try {
			ResourceSet resource = executeQ(query);
			ResourceIterator iterator = resource.getIterator();
			while (iterator.hasMoreResources()) {
				XMLResource res = (XMLResource) iterator.nextResource();
				String idchar = (String) res.getContent();
				id = Long.parseLong(idchar) + 1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
}
