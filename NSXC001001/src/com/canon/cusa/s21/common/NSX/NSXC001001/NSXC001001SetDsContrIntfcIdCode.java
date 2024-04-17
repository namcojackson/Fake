/**
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

import business.db.DS_CONTR_INTFCTMsg;

/**
 * <pre>
 * Set DsContrIntfc Id Code
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/23   Hitachi         Y.Tsuchimoto    Create          N/A(Template only)
 *</pre>
 */
public class NSXC001001SetDsContrIntfcIdCode {

    /**
     * <pre>
     * getDsContrIntfcIdCode
     * </pre>
     * @param bean DsContrIntfInfoBean
     * @return DS_CONTR_INTFCTMsg
     */
    public static DS_CONTR_INTFCTMsg getDsContrIntfcIdCode(DsContrIntfInfoBean bean) {
        return new DS_CONTR_INTFCTMsg();
    }
}
