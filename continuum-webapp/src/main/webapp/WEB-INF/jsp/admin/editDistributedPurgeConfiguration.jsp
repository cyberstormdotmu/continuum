<%--
  ~ Licensed to the Apache Software Foundation (ASF) under one
  ~ or more contributor license agreements.  See the NOTICE file
  ~ distributed with this work for additional information
  ~ regarding copyright ownership.  The ASF licenses this file
  ~ to you under the Apache License, Version 2.0 (the
  ~ "License"); you may not use this file except in compliance
  ~ with the License.  You may obtain a copy of the License at
  ~
  ~   http://www.apache.org/licenses/LICENSE-2.0
  ~
  ~ Unless required by applicable law or agreed to in writing,
  ~ software distributed under the License is distributed on an
  ~ "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  ~ KIND, either express or implied.  See the License for the
  ~ specific language governing permissions and limitations
  ~ under the License.
  --%>

<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="continuum" prefix="c1" %>
<html>
  <s:i18n name="localization.Continuum">
  <head>
    <title><s:text name="purgeConfig.page.title"/></title>
  </head>
  <body>
  <div class="app">
    <div id="axial" class="h3">
      <h3><s:text name="purgeConfig.section.title"/></h3>

    <div class="axial">
      <s:form action="saveDistributedPurgeConfig" method="post" validate="true">
        <c:if test="${!empty actionErrors}">
          <div class="errormessage">
            <s:iterator value="actionErrors">
              <p><s:property/></p>
            </s:iterator>
          </div>
        </c:if>
          <table>
            <s:select label="%{getText('purgeConfig.directoryType.label')}" name="directoryType" list="directoryTypes"/>
            <s:textfield label="%{getText('purgeConfig.daysOlder.label')}" name="daysOlder"/>
            <s:textfield label="%{getText('purgeConfig.retentionCount.label')}" name="retentionCount"/>
            <s:checkbox label="%{getText('purgeConfig.deleteAll.label')}" name="deleteAll"/>
            <s:if test="purgeType == 'repository'">
              <s:checkbox label="%{getText('purgeConfig.deleteReleasedSnapshots.label')}" name="deleteReleasedSnapshots"/>
            </s:if>
            <s:select label="%{getText('purgeConfig.buildAgent.label')}" name="buildAgentUrl" list="buildAgentUrls"/>
            <s:select label="%{getText('purgeConfig.schedule.label')}" name="scheduleId" list="schedules"
                       headerKey="-1" headerValue=""/>
            <s:textfield label="%{getText('purgeConfig.description.label')}" name="description"/>
            <s:checkbox label="%{getText('purgeConfig.enabled.label')}" name="enabled"/>
          </table>
          <s:hidden name="purgeConfigId"/>
          <s:hidden name="purgeType"/>
          <div class="functnbar3">
            <c1:submitcancel value="%{getText('save')}" cancel="%{getText('cancel')}"/>
          </div>
      </s:form>
    </div>
  </div>
</div>

</body>
</s:i18n>
</html>