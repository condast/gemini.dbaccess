/*******************************************************************************
 * Copyright (c) 2010 Oracle.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Apache License v2.0 which accompanies this distribution. 
 * The Eclipse Public License is available at
 *     http://www.eclipse.org/legal/epl-v10.html
 * and the Apache License v2.0 is available at 
 *     http://www.opensource.org/licenses/apache2.0.php.
 * You may elect to redistribute this code under either of these licenses.
 *
 * Contributors:
 *     mkeith - CLient/Server Derby JDBC support 
 ******************************************************************************/

package org.eclipse.gemini.dbaccess.derby;

import org.apache.derby.jdbc.ClientDriver;
import org.apache.derby.jdbc.EmbeddedDriver;
import org.eclipse.gemini.dbaccess.*;

import java.util.Properties;

/** 
 * An abbreviated/simplified DataSource impl that takes a URL from the client
 * and just returns a thin data source wrapper around the basic JDBC driver.
 */
class EmbeddedUrlBasedDriverDataSource extends UrlBasedDriverDataSource {

    /**
     * @param properties The properties to use for operations on the driver
     * @param embedded Whether to wrap an embedded or a client driver
     */
    public EmbeddedUrlBasedDriverDataSource(Properties properties, boolean embedded) {
        super( properties, embedded ? new EmbeddedDriver() : new ClientDriver() );
    }

    public EmbeddedUrlBasedDriverDataSource(Properties properties) {
        this(properties, true);
    }

    public boolean isWrapperFor(Class<?> cls) { 
        return super.isWrapperFor(cls) 
            ? (cls == EmbeddedDriver.class)
            : (cls == ClientDriver.class);
    }
}