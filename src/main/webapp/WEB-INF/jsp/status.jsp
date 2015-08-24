<!DOCTYPE html>

<%@ taglib uri="/bbNG" prefix="bbNG" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:message var="statusTitle" key="enabler-app.enabler.statusStep.title" />
<fmt:message var="statusLabel" key="enabler-app.enabler.statusStep.status.label" />
<fmt:message var="batchUidLabel" key="enabler-app.enabler.statusStep.batchUid.label" />
<fmt:message var="courseNameLabel" key="enabler-app.enabler.statusStep.courseName.label" />

<bbNG:genericPage bodyClass="normalBackground"
                  navItem="atd-enabler-app-nav-enabler">

  <stripes:form beanclass="com.alltheducks.enabler.stripes.EnablerAction">
    <!-- Don't use stripes:hidden here, otherwise stripes looks for the bean field with that name. -->
    <input type="hidden" name="setCourseStatus"/>
    <bbNG:dataCollection>
      <bbNG:step title="${statusTitle}">
        <bbNG:dataElement label="${courseNameLabel}">
          ${actionBean.courseName}
        </bbNG:dataElement>
        <bbNG:dataElement label="${batchUidLabel}">
          ${actionBean.batchUid}
        </bbNG:dataElement>
        <bbNG:dataElement isRequired="true" label="${statusLabel}">
          <stripes:checkbox name="courseEnabled"></stripes:checkbox>
        </bbNG:dataElement>
      </bbNG:step>
      <bbNG:stepSubmit />
    </bbNG:dataCollection>
  </stripes:form>
</bbNG:genericPage>