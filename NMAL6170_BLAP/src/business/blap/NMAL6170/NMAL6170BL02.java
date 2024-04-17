/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6170;

import static business.blap.NMAL6170.constant.NMAL6170Constant.NMAM8121E;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;

import business.db.DS_ACCT_PROSTMsg;
import business.db.DS_ACCT_PROSTMsgArray;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_RELN_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Relationships Filter Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/07/05   Hitachi         J.Kim           Create          N/A
 *</pre>
 */
public class NMAL6170BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        NMAL6170CMsg bizMsg = (NMAL6170CMsg) cMsg;
        String screenAplID = cMsg.getScreenAplID();
        try {
            if ("NMAL6170_INIT".equals(screenAplID)) {
                doProcess_NMAL6170_INIT(bizMsg);
            } else if ("NMAL6170Scrn00_ShowAcct".equals(screenAplID)) {
                doProcess_NMAL6170Scrn00_ShowAcct(bizMsg);
            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL6170_INIT(NMAL6170CMsg bizMsg) {

        createPullDown(bizMsg);

    }

    private void createPullDown(NMAL6170CMsg bizMsg) {

        ZYPCodeDataUtil.createPulldownList(DS_ACCT_RELN_TP.class, bizMsg.dsAcctRelnTpCd_C, bizMsg.dsAcctRelnTpNm_F);
    }

    private void doProcess_NMAL6170Scrn00_ShowAcct(NMAL6170CMsg bizMsg) {

        String dsAcctNum = getDsAcctNm(bizMsg.dsAcctNum_F.getValue(), getGlobalCompanyCode());
        if (!ZYPCommonFunc.hasValue(dsAcctNum)) {
            bizMsg.dsAcctNum_F.setErrorInfo(1, NMAM8121E, new String[] {"Account#" });
        }
    }

    /**
     * getDsAcctNm
     * @param dsAcctNum String
     * @param glblCmpyCd String
     * @return String
     */
    private static String getDsAcctNm(String dsAcctNum, String glblCmpyCd) {

        SELL_TO_CUSTTMsg exSellToCustTMsg = new SELL_TO_CUSTTMsg();
        exSellToCustTMsg.setSQLID("001");
        exSellToCustTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        exSellToCustTMsg.setConditionValue("sellToCustCd01", dsAcctNum);
        SELL_TO_CUSTTMsgArray resultArray = (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(exSellToCustTMsg);

        if (resultArray == null || resultArray.getValidCount() == 0) {
            DS_ACCT_PROSTMsg exDsAcctProsTMsg = new DS_ACCT_PROSTMsg();
            exDsAcctProsTMsg.setSQLID("001");
            exDsAcctProsTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            exDsAcctProsTMsg.setConditionValue("dsAcctNum01", dsAcctNum);
            DS_ACCT_PROSTMsgArray resultArrayPros = (DS_ACCT_PROSTMsgArray) EZDTBLAccessor.findByCondition(exDsAcctProsTMsg);
            if (resultArrayPros != null && resultArrayPros.getValidCount() > 0) {
                return resultArrayPros.no(0).dsAcctNm.getValue();
            }
        } else {
            return resultArray.no(0).dsAcctNm.getValue();
        }

        return "";
    }

}
