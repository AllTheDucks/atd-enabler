package com.alltheducks.enabler.stripes;

import com.alltheducks.bb.stripes.EntitlementRestrictions;
import net.sourceforge.stripes.action.*;
import com.alltheducks.bb.stripes.BlackboardActionBeanContext;


@EntitlementRestrictions(entitlements = "atd.enabler.admin.MODIFY", errorPage = "error.jsp")
public class EnablerAction implements ActionBean {

    private BlackboardActionBeanContext context;

    @DefaultHandler
    public Resolution displayEnabler() {
        return new ForwardResolution("/WEB-INF/jsp/enabler.jsp");
    }

    public ActionBeanContext getContext() {
        return context;
    }

    public void setContext(ActionBeanContext context) {
        this.context = (BlackboardActionBeanContext)context;
    }

}