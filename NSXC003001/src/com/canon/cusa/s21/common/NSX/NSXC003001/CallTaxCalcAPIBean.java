/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC003001;

import business.db.FSRTMsg;
import business.db.SVC_INVTMsg;
import business.db.SVC_INV_LINETMsg;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/30/2015   Hitachi         A.Kohinata      Create          N/A
 *</pre>
 */
public class CallTaxCalcAPIBean {

    /** FSRTMsg */
    private FSRTMsg fsrTmsg;

    /** SVC_INVTMsg */
    private SVC_INVTMsg svcInvTmsg;

    /** SVC_INV_LINETMsg */
    private SVC_INV_LINETMsg svcInvLineTmsg;

    /**
     * constructor
     * @param fsrTmsg FSRTMsg
     * @param svcInvTmsg SVC_INV_LINETMsg
     * @param svcInvLineTmsg SVC_INV_LINETMsg
     */
    public CallTaxCalcAPIBean(FSRTMsg fsrTmsg, SVC_INVTMsg svcInvTmsg, SVC_INV_LINETMsg svcInvLineTmsg) {
        this.fsrTmsg = fsrTmsg;
        this.svcInvTmsg = svcInvTmsg;
        this.svcInvLineTmsg = svcInvLineTmsg;
    }

    /**
     * Get fsrTmsg
     * @return fsrTmsg
     */
    public FSRTMsg getFsrTmsg() {
        return fsrTmsg;
    }

    /**
     * Set fsrTmsg
     * @param fsrTmsg FSRTMsg
     */
    public void setFsrTmsg(FSRTMsg fsrTmsg) {
        this.fsrTmsg = fsrTmsg;
    }

    /**
     * Get svcInvTmsg
     * @return svcInvTmsg SVC_INVTMsg
     */
    public SVC_INVTMsg getSvcInvTmsg() {
        return svcInvTmsg;
    }

    /**
     * Set svcInvTmsg
     * @param svcInvTmsg SVC_INVTMsg
     */
    public void setSvcInvTmsg(SVC_INVTMsg svcInvTmsg) {
        this.svcInvTmsg = svcInvTmsg;
    }

    /**
     * Get svcInvLineTmsg
     * @return svcInvLineTmsg SVC_INV_LINETMsg
     */
    public SVC_INV_LINETMsg getSvcInvLineTmsg() {
        return svcInvLineTmsg;
    }

    /**
     * Set svcInvLineTmsg
     * @param svcInvLineTmsg SVC_INV_LINETMsg
     */
    public void setSvcInvLineTmsg(SVC_INV_LINETMsg svcInvLineTmsg) {
        this.svcInvLineTmsg = svcInvLineTmsg;
    }
}
