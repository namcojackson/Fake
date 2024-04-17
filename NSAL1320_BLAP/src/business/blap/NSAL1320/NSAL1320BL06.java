/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1320;

import static business.blap.NSAL1320.constant.NSAL1320Constant.NSAM0789W;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL1320.common.NSAL1320CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NSAL1320BL06
 *F
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/19   Hitachi         Y.Takeno        Create          N/A
 * 2017/06/08   Hitachi         A.Kohinata      Update          QC#18853
 * 2017/08/18   Hitachi         Y.Takeno        Update          QC#20651
 * 2017/10/24   Hitachi         Y.Takeno        Update          QC#21556
 * 2019/11/22   Hitachi         K.Kitachi       Update          QC#52959
 * 2019/11/29   Hitachi         K.Kitachi       Update          QC#53682
 * 2023/07/05   CITS            T.Kojima        Update          QC#61472
 * 2023/11/06   Hitachi         H.Watanabe      Update          QC#61924
 * 2023/11/08   CITS            R.Jin           Update          QC#62108
 * 2024/03/12   CITS            M.Kuroi         Update          QC#63638
 *</pre>
 */
public class NSAL1320BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NSAL1320CMsg bizMsg = (NSAL1320CMsg) cMsg;
            NSAL1320SMsg glblMsg = (NSAL1320SMsg) sMsg;

            if ("NSAL1320Scrn00_CMN_Save".equals(screenAplID)) {
                doProcess_NSAL1320Scrn00_CMN_Save(bizMsg, glblMsg);

            } else if ("NSAL1320Scrn00_OpenWin_SvcPricing".equals(screenAplID)) {
                doProcess_NSAL1320Scrn00_OpenWin_SvcPricing(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL1320Scrn00_OpenWin_SvcPricing(NSAL1320CMsg bizMsg, NSAL1320SMsg glblMsg) {
        // force save
        if (NSAL1320CommonLogic.isModified(bizMsg, getGlobalCompanyCode())) {
            doProcess_NSAL1320Scrn00_CMN_Save(bizMsg, glblMsg);
        }

        // del start 2017/06/08 QC#18853
        //NSAL1320CommonLogic.deleteShellPrice(getGlobalCompanyCode(), bizMsg);
        // del end 2017/06/08 QC#18853
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NSAL1320Scrn00_CMN_Save(NSAL1320CMsg bizMsg, NSAL1320SMsg glblMsg) {
        if (NSAL1320CommonLogic.deriveFromInputValue(getGlobalCompanyCode(), bizMsg, ZYPConstant.FLG_OFF_N)) {
            return; // error
        }
        // START 2017/08/18 [QC#20651, ADD]
        if (NSAL1320CommonLogic.checkMtrReadMeth(getGlobalCompanyCode(), bizMsg)) {
            return; // error
        }
        // END   2017/08/18 [QC#20651, ADD]
        if (NSAL1320CommonLogic.checkPoNumAndDt(bizMsg)) {
            return; // error
        }
        // START  2017/10/24 [QC#21556, ADD]
        if (NSAL1320CommonLogic.checkTermFromTo(bizMsg)) {
            return; // error
        }
        // END    2017/10/24 [QC#21556, ADD]
        if (NSAL1320CommonLogic.checkTermSeq(bizMsg)) {
            return; // error
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.xxCellIdx, BigDecimal.ZERO);
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (NSAL1320CommonLogic.checkContr(bizMsg)) {
                return; // error
            }
            ZYPEZDItemValueSetter.setValue(bizMsg.xxCellIdx, bizMsg.xxCellIdx.getValue().add(BigDecimal.ONE));
        }
        if (NSAL1320CommonLogic.checkExclusionCtrl(getGlobalCompanyCode(), bizMsg)) {
            return; // error
        }

        // START 2019/11/29 K.Kitachi [QC#53682, ADD]
        if (NSAL1320CommonLogic.checkAddToContrAccReln(bizMsg)) {
            return; // error
        }
        // END 2019/11/29 K.Kitachi [QC#53682, ADD]

        // 2023/11/06 QC#61924 Add Start
        if (!NSAL1320CommonLogic.hasDeactivateAccountOrLocation(getGlobalCompanyCode(), bizMsg, glblMsg)) {
            return;
        }
        // 2023/11/06 QC#61924 Add End

        // START 2024/03/12 M.Kuroi [QC#63638, ADD]
        if (NSAL1320CommonLogic.checkLatestShpgSts(bizMsg)) {
            return; // error
        }
        // END 2024/03/12 M.Kuroi [QC#63638, ADD]

        // mod start 2017/06/08 QC#18853
        // START 2019/11/22 K.Kitachi [QC#52959, DEL]
//        NSAL1320CommonLogic.deleteShellPrice(getGlobalCompanyCode(), bizMsg);
//        NSAL1320CommonLogic.callContractImportApi(getGlobalCompanyCode(), bizMsg);
        // END 2019/11/22 K.Kitachi [QC#52959, DEL]
        // mod end 2017/06/08 QC#18853

        // START 2019/11/22 K.Kitachi [QC#52959, ADD]
        NSAL1320CMsg copyBizMsg = (NSAL1320CMsg) bizMsg.clone();

        NSAL1320CommonLogic.deleteShellPrice(getGlobalCompanyCode(), copyBizMsg);
        if ("E".equals(copyBizMsg.getMessageKind())) {
            EZDMessageInfo msgInfo = copyBizMsg.getMessageInfo();
            bizMsg.setMessageInfo(msgInfo.getCode(), msgInfo.getParameter());
            // START 2023/11/08 R.Jin [QC#62108, DEL]
            // START 2023/07/05 T.Kojima [QC#61472, ADD]
//            if (NSZM1406E.equals(msgInfo.getCode())) {
//                NSAL1320_ACMsg aBizMsg = bizMsg.A.no(0);
//                aBizMsg.dsContrNum_AD.setErrorInfo(1, NSAM0778E);
//            }
            // END 2023/07/05 T.Kojima [QC#61472, ADD]
            // END 2023/11/08 R.Jin [QC#62108, DEL]
            return;
        }

        NSAL1320CommonLogic.callContractImportApi(getGlobalCompanyCode(), copyBizMsg);
        if ("E".equals(copyBizMsg.getMessageKind())) {
            EZDMessageInfo msgInfo = copyBizMsg.getMessageInfo();
            bizMsg.setMessageInfo(msgInfo.getCode(), msgInfo.getParameter());
            // START 2023/11/08 R.Jin [QC#62108, DEL]
            // START 2023/07/05 T.Kojima [QC#61472, ADD]
//            if (NSZM1406E.equals(msgInfo.getCode())) {
//                NSAL1320_ACMsg aBizMsg = bizMsg.A.no(0);
//                aBizMsg.dsContrNum_AD.setErrorInfo(1, NSAM0778E);
//            }
            // END 2023/07/05 T.Kojima [QC#61472, ADD]
            // END 2023/11/08 R.Jin [QC#62108, DEL]
            return;
        }

        // START 2024/03/12 M.Kuroi [QC#63638, ADD]
        if ("NSAL1320Scrn00_CMN_Save".equals(bizMsg.getScreenAplID())) {
            NSAL1320CommonLogic.callDiCheckApi(getGlobalCompanyCode(), ZYPDateUtil.getSalesDate(), getContextUserInfo().getUserId(), copyBizMsg, bizMsg);
            if ("E".equals(copyBizMsg.getMessageKind())) {
                EZDMessageInfo msgInfo = copyBizMsg.getMessageInfo();
                bizMsg.setMessageInfo(msgInfo.getCode(), msgInfo.getParameter());
                return;
            }
            NSAL1320CommonLogic.callOrderStatusUpdateApi(getGlobalCompanyCode(), copyBizMsg);
            if ("E".equals(copyBizMsg.getMessageKind())) {
                EZDMessageInfo msgInfo = copyBizMsg.getMessageInfo();
                bizMsg.setMessageInfo(msgInfo.getCode(), msgInfo.getParameter());
                return;
            }
        }
        // END 2024/03/12 M.Kuroi [QC#63638, ADD]

        EZDMsg.copy(copyBizMsg, null, bizMsg, null);
        // END 2019/11/22 K.Kitachi [QC#52959, ADD]
    }
}
