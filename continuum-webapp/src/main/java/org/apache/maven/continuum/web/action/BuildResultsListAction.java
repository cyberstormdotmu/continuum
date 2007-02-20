package org.apache.maven.continuum.web.action;

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

import org.apache.maven.continuum.ContinuumException;
import org.apache.maven.continuum.security.ContinuumRoleConstants;
import org.apache.maven.continuum.model.project.Project;
import org.codehaus.plexus.security.ui.web.interceptor.SecureActionBundle;
import org.codehaus.plexus.security.ui.web.interceptor.SecureActionException;
import org.codehaus.plexus.security.ui.web.interceptor.SecureAction;

import java.util.Collection;

/**
 * @author <a href="mailto:evenisse@apache.org">Emmanuel Venisse</a>
 * @version $Id$
 *
 * @plexus.component
 *   role="com.opensymphony.xwork.Action"
 *   role-hint="buildResults"
 */
public class BuildResultsListAction
    extends ContinuumActionSupport
    implements SecureAction
{
    private Project project;

    private Collection buildResults;

    private int projectId;

    private String projectName;

    private String projectGroupName = "";

    public String execute()
        throws ContinuumException
    {
        project = getContinuum().getProject( projectId );

        buildResults = getContinuum().getBuildResultsForProject( projectId );

        return SUCCESS;
    }

    public int getProjectId()
    {
        return projectId;
    }

    public void setProjectId( int projectId )
    {
        this.projectId = projectId;
    }

    public Collection getBuildResults()
    {
        return buildResults;
    }

    public String getProjectName()
    {
        return projectName;
    }

    public void setProjectName( String projectName )
    {
        this.projectName = projectName;
    }

    public Project getProject()
    {
        return project;
    }

    public String getProjectGroupName()
        throws ContinuumException
    {
        if( projectGroupName == null || "".equals( projectGroupName ) )
        {
            projectGroupName = getContinuum().getProject( projectId ).getProjectGroup().getName();
        }

        return projectGroupName;
    }

    public SecureActionBundle getSecureActionBundle()
        throws SecureActionException
    {
        SecureActionBundle bundle = new SecureActionBundle();
        bundle.setRequiresAuthentication( true );

        try
        {
            bundle.addRequiredAuthorization( ContinuumRoleConstants.CONTINUUM_VIEW_GROUP_OPERATION, getProjectGroupName() );
        }
        catch ( ContinuumException e )
        {
            throw new SecureActionException( e.getMessage() );
        }

        return bundle;
    }
}
