package com.alltheducks.enabler.stripes;

import blackboard.admin.data.IAdminObject;
import blackboard.admin.data.course.CourseSite;
import blackboard.admin.persist.course.CourseSiteLoader;
import blackboard.admin.persist.course.CourseSitePersister;
import blackboard.admin.persist.course.impl.CourseSiteDbLoader;
import blackboard.admin.persist.course.impl.CourseSiteDbPersister;
import blackboard.data.ValidationException;
import blackboard.db.ConstraintViolationException;
import blackboard.persist.PersistenceException;
import blackboard.platform.intl.BbResourceBundle;
import blackboard.platform.intl.BundleManager;
import blackboard.platform.intl.BundleManagerFactory;
import blackboard.platform.plugin.PlugIn;
import blackboard.platform.plugin.PlugInManager;
import blackboard.platform.plugin.PlugInManagerFactory;
import blackboard.platform.plugin.PlugInUtil;
import blackboard.platform.servlet.InlineReceiptUtil;
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

    public Resolution saveCourseStatus() throws PersistenceException, ConstraintViolationException, ValidationException {
        CourseSiteLoader courseLoader = CourseSiteDbLoader.Default.getInstance();
        CourseSitePersister coursePersister = CourseSiteDbPersister.Default.getInstance();

        CourseSite cs = courseLoader.load(batchUid);
        if (courseEnabled) {
            cs.setRowStatus(IAdminObject.RowStatus.ENABLED);
        } else {
            cs.setRowStatus(IAdminObject.RowStatus.DISABLED);
        }
        coursePersister.save(cs);

        String destUrl = InlineReceiptUtil.addSuccessReceiptToUrl("Enabler.action", getMessage("enabler-app.enabler.saveStatus.success.message", cs.getTitle()));

        return new RedirectResolution(destUrl, false);
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

    private String getMessage(String key, String... args) {
        PlugInManager pluginMgr = PlugInManagerFactory.getInstance();
        PlugIn plugin = pluginMgr.getPlugIn("atd", "enabler");
        BundleManager bm = BundleManagerFactory.getInstance();
        BbResourceBundle bundle = bm.getPluginBundle(plugin.getId());
        return bundle.getString(key, args);
    }
    public ActionBeanContext getContext() {
        return context;
    }

    public void setContext(ActionBeanContext context) {
        this.context = (BlackboardActionBeanContext)context;
    }

}
