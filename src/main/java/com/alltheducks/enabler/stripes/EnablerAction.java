package com.alltheducks.enabler.stripes;

import blackboard.admin.data.IAdminObject;
import blackboard.admin.data.course.CourseSite;
import blackboard.admin.persist.course.CourseSiteLoader;
import blackboard.admin.persist.course.impl.CourseSiteDbLoader;
import blackboard.persist.PersistenceException;
import com.alltheducks.bb.stripes.EntitlementRestrictions;
import net.sourceforge.stripes.action.*;
import com.alltheducks.bb.stripes.BlackboardActionBeanContext;


@EntitlementRestrictions(entitlements = "atd.enabler.admin.MODIFY", errorPage = "error.jsp")
public class EnablerAction implements ActionBean {

    private BlackboardActionBeanContext context;

    private boolean courseEnabled;
    private String batchUid;
    private String courseName;

    @DefaultHandler
    public Resolution displayEnabler() {
        return new ForwardResolution("/WEB-INF/jsp/enabler.jsp");
    }

    public Resolution displayCourseStatus() throws PersistenceException {
        CourseSiteLoader courseLoader = CourseSiteDbLoader.Default.getInstance();

        CourseSite cs = courseLoader.load(batchUid);
        IAdminObject.RowStatus status = cs.getRowStatus();
        courseEnabled = status.equals(IAdminObject.RowStatus.ENABLED);
        courseName = cs.getTitle();

        return new ForwardResolution("/WEB-INF/jsp/status.jsp");
    }

    public boolean isCourseEnabled() {
        return courseEnabled;
    }

    public void setCourseEnabled(boolean courseEnabled) {
        this.courseEnabled = courseEnabled;
    }

    public String getBatchUid() {
        return batchUid;
    }

    public void setBatchUid(String batchUid) {
        this.batchUid = batchUid;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public ActionBeanContext getContext() {
        return context;
    }

    public void setContext(ActionBeanContext context) {
        this.context = (BlackboardActionBeanContext)context;
    }

}