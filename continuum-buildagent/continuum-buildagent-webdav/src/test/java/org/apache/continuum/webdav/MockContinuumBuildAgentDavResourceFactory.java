package org.apache.continuum.webdav;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.io.File;
import java.io.IOException;

import org.apache.jackrabbit.webdav.DavResource;
import org.apache.jackrabbit.webdav.DavSession;

public class MockContinuumBuildAgentDavResourceFactory
    extends ContinuumBuildAgentDavResourceFactory
{
    @Override
    protected File getResourceFile( int projectId, String logicalResource )
    {
        return new File( getWorkingDirectory( projectId ), logicalResource );
    }

    @Override
    protected DavResource createResource( File resourceFile, String logicalResource, DavSession session,
                                          ContinuumBuildAgentDavResourceLocator locator )
    {
        return new MockContinuumBuildAgentDavResource( resourceFile.getAbsolutePath(), logicalResource, session, 
                                                       locator, this, getMimeTypes() );
    }

    private File getWorkingDirectory( int projectId )
    {
        String basedir = System.getProperty( "basedir" );

        if ( basedir == null )
        {
            basedir = new File( "" ).getAbsolutePath();
        }

        File dir = new File( basedir, "target/appserver-base/data/working-directory/" + projectId  );

        try
        {
            dir = dir.getCanonicalFile();
        }
        catch ( IOException e )
        {
        }

        return dir;
    }
}
