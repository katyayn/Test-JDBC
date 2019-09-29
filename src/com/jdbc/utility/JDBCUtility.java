/**
 * 
 */
package com.jdbc.utility;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;

import com.test.jdbc.Test_JDBC;

/**
 * @author katyayn
 *
 */
public class JDBCUtility {

    public static String getPath(String path) throws URISyntaxException {
        URL url = Test_JDBC.class.getResource(path);

        String sqlPath = Path.of(url.toURI()).toString();

        return sqlPath;
    }

}
