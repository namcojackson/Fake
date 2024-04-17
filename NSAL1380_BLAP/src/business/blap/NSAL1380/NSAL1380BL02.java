/*
 * <Pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1380;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL1380.common.NSAL1380CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_INTVL;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
/**
 *<pre>
 * Supply Agreement Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/05/08   Hitachi         N.Arai          Create          N/A
 * 2017/10/16   Hitachi         Y.Takeno        Update          QC#20001
 *</pre>
 */
public class NSAL1380BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            NSAL1380CMsg bizMsg = (NSAL1380CMsg) cMsg;

            if ("NSAL1380_INIT".equals(screenAplID)) {
                doProcess_NSAL1380_INIT(bizMsg);
            } else if ("NSAL1380Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSAL1380Scrn00_CMN_Reset(bizMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    private void doProcess_NSAL1380Scrn00_CMN_Reset(NSAL1380CMsg bizMsg) {
        doProcess_NSAL1380_INIT(bizMsg);

    }

    private void doProcess_NSAL1380_INIT(NSAL1380CMsg bizMsg) {

        ZYPCodeDataUtil.createPulldownList(SHPG_INTVL.class, bizMsg.shpgIntvlCd_P, bizMsg.shpgIntvlDescTxt_P);

        if (ZYPCommonFunc.hasValue(bizMsg.dsContrDtlPk)) {
            // START 2017/10/16 [QC#20001, MOD]
            // NSAL1380CommonLogic.search(bizMsg);
            NSAL1380CommonLogic.search(getGlobalCompanyCode(), bizMsg);
            // END   2017/10/16 [QC#20001, MOD]
        }
    }

}
