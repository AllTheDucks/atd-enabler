<!DOCTYPE html>

<%@ taglib uri="/bbNG" prefix="bbNG" %>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:message var="courseStepTitle" key="enabler-app.enabler.courseStep.title" />
<fmt:message var="batchUidLabel" key="enabler-app.enabler.courseStep.batchUid.label" />

<bbNG:genericPage bodyClass="normalBackground"
                  navItem="atd-enabler-app-nav-enabler">

  <stripes:form beanclass="com.alltheducks.enabler.stripes.EnablerAction">
    <input type="hidden" name="displayCourseStatus"/>

    <bbNG:dataCollection>
      <bbNG:step title="${courseStepTitle}">
        <bbNG:dataElement isRequired="true" label="${batchUidLabel}">
          <stripes:text name="batchUid"></stripes:text>
          <stripes:errors field="courseId"></stripes:errors>
        </bbNG:dataElement>
      </bbNG:step>
      <bbNG:stepSubmit />
    </bbNG:dataCollection>
  </stripes:form>

</bbNG:genericPage>