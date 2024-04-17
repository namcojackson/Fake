/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2190;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NWAL2190.common.NWAL2190CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_INTVL;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 *
 * Supply Agreement Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/12/02   Fujitsu         S.Iidaka        Create          N/A
 *</pre>
 */
public class NWAL2190BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL2190CMsg bizMsg = (NWAL2190CMsg) cMsg;

            if ("NWAL2190_INIT".equals(screenAplID)) {
                doProcess_NWAL2190_INIT(bizMsg);
            } else if ("NWAL2190Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NWAL2190Scrn00_CMN_Reset(bizMsg);
            } else if ("NWAL2190Scrn00_CMN_Return".equals(screenAplID)) {
                doProcess_NWAL2190Scrn00_CMN_Return(bizMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    private void doProcess_NWAL2190Scrn00_CMN_Return(NWAL2190CMsg bizMsg) {
        bizMsg.clear();
        ZYPTableUtil.clear(bizMsg.A);
    }

    private void doProcess_NWAL2190Scrn00_CMN_Reset(NWAL2190CMsg bizMsg) {
        doProcess_NWAL2190_INIT(bizMsg);

    }

    private void doProcess_NWAL2190_INIT(NWAL2190CMsg bizMsg) {

        ZYPCodeDataUtil.createPulldownList(SHPG_INTVL.class, bizMsg.shpgIntvlCd_P, bizMsg.shpgIntvlDescTxt_P);

        if (ZYPCommonFunc.hasValue(bizMsg.cpoSvcConfigRefPk)) {
            NWAL2190CommonLogic.search(bizMsg);
        }
    }

}
