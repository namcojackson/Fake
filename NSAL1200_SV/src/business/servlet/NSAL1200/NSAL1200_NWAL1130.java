/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1200;

import static business.servlet.NSAL1200.constant.NSAL1200Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL1200.NSAL1200CMsg;
import business.servlet.NSAL1200.common.NSAL1200CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Counter Group Setup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/28   Hitachi         Y.Takeno        Create          N/A
 * 2017/08/03   Hitachi         T.Kanasaka      Update          QC#18193
 *</pre>
 */
public class NSAL1200_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL1200BMsg scrnMsg = (NSAL1200BMsg) bMsg;

        if (!NWAL1130_EVENT_NM_CLOSE.equals(getLastGuard())) {
            if (LINK_NM_OPEN_WIN_NWAL1130.equals(scrnMsg.xxScrEventNm.getValue())) {
                return setRequestData_OpenWinNWAL1130(ctx, scrnMsg);

            } else if (BTN_NM_OPEN_WIN_COUNTER.equals(scrnMsg.xxScrEventNm.getValue())) {
                return setRequestData_OpenWinCounter(ctx, scrnMsg);

            // START 2017/08/03 T.Kanasaka [QC#18193,DEL]
//            } else if (BTN_NM_OPEN_WIN_DEFAULT_BILLING.equals(scrnMsg.xxScrEventNm.getValue())) {
//                return setRequestData_OpenWinDefaultBilling(ctx, scrnMsg);
//
//            } else if (BTN_NM_OPEN_WIN_FLT_COUNTER.equals(scrnMsg.xxScrEventNm.getValue())) {
//                return setRequestData_OpenWinFltCounter(ctx, scrnMsg);
//
//            } else if (BTN_NM_OPEN_WIN_AGG_COUNTER.equals(scrnMsg.xxScrEventNm.getValue())) {
//                return setRequestData_OpenWinAggCounter(ctx, scrnMsg);
            // END 2017/08/03 T.Kanasaka [QC#18193,DEL]

            // START 2017/08/03 T.Kanasaka [QC#18193,ADD]
            } else if (BTN_NM_OPEN_WIN_LVL1_COUNTER.equals(scrnMsg.xxScrEventNm.getValue())) {
                return setRequestData_OpenWin_LVL1Counter(ctx, scrnMsg);

            } else if (BTN_NM_OPEN_WIN_LVL2_COUNTER.equals(scrnMsg.xxScrEventNm.getValue())) {
                return setRequestData_OpenWin_LVL2Counter(ctx, scrnMsg);

            } else if (BTN_NM_OPEN_WIN_LVL3_COUNTER.equals(scrnMsg.xxScrEventNm.getValue())) {
                return setRequestData_OpenWin_LVL3Counter(ctx, scrnMsg);
            // END 2017/08/03 T.Kanasaka [QC#18193,ADD]
            }
        }

        return null;
    }

    protected EZDCMsg setRequestData_OpenWinNWAL1130(EZDApplicationContext ctx, NSAL1200BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.P.length(); i++) {
            if (NWAL1130_SQL_NM_MTR_GRP_NM.equals(scrnMsg.P.no(i).xxComnScrQueryColNm.getValue())) {
                if (ZYPCommonFunc.hasValue(scrnMsg.P.no(i).xxComnScrColValTxt)) {
                    setValue(scrnMsg.mtrGrpNm_H, scrnMsg.P.no(i).xxComnScrColValTxt.getValue());
                } else {
                    scrnMsg.mtrGrpNm_H.clear();
                }
            } else if (NWAL1130_SQL_NM_MTR_GRP_PK.equals(scrnMsg.P.no(i).xxComnScrQueryColNm.getValue())) {
                if (ZYPCommonFunc.hasValue(scrnMsg.P.no(i).xxComnScrColValTxt)) {
                    setValue(scrnMsg.mtrGrpPk_H, new BigDecimal(scrnMsg.P.no(i).xxComnScrColValTxt.getValue()));
                } else {
                    scrnMsg.mtrGrpPk_H.clear();
                }
            } else if (NWAL1130_SQL_NM_MTR_GRP_DESC_TXT.equals(scrnMsg.P.no(i).xxComnScrQueryColNm.getValue())) {
                if (ZYPCommonFunc.hasValue(scrnMsg.P.no(i).xxComnScrColValTxt)) {
                    setValue(scrnMsg.mtrGrpDescTxt_H, scrnMsg.P.no(i).xxComnScrColValTxt.getValue());
                } else {
                    scrnMsg.mtrGrpDescTxt_H.clear();
                }
            } else if (NWAL1130_SQL_NM_MTR_GRP_TP_CD.equals(scrnMsg.P.no(i).xxComnScrQueryColNm.getValue())) {
                if (ZYPCommonFunc.hasValue(scrnMsg.P.no(i).xxComnScrColValTxt)) {
                    setValue(scrnMsg.mtrGrpTpCd_H, scrnMsg.P.no(i).xxComnScrColValTxt.getValue());
                } else {
                    scrnMsg.mtrGrpTpCd_H.clear();
                }
            }
        }

        NSAL1200CMsg bizMsg = new NSAL1200CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    protected EZDCMsg setRequestData_OpenWinCounter(EZDApplicationContext ctx, NSAL1200BMsg scrnMsg) {

        int selIndex = getButtonSelectNumber();

        for (int i = 0; i < scrnMsg.P.length(); i++) {
            if ("MTR_LB_CD".equals(scrnMsg.P.no(i).xxComnScrQueryColNm.getValue())) {
                if (ZYPCommonFunc.hasValue(scrnMsg.P.no(i).xxComnScrColValTxt)) {
                    setValue(scrnMsg.A.no(selIndex).mdlMtrLbCd_A, scrnMsg.P.no(i).xxComnScrColValTxt.getValue());
                } else {
                    scrnMsg.A.no(selIndex).mdlMtrLbCd_A.clear();
                }
            } else if ("MTR_LB_DESC_TXT".equals(scrnMsg.P.no(i).xxComnScrQueryColNm.getValue())) {
                if (ZYPCommonFunc.hasValue(scrnMsg.P.no(i).xxComnScrColValTxt)) {
                    setValue(scrnMsg.A.no(selIndex).mtrLbDescTxt_CN, scrnMsg.P.no(i).xxComnScrColValTxt.getValue());
                } else {
                    scrnMsg.A.no(selIndex).mtrLbDescTxt_CN.clear();
                }
            }
        }
        scrnMsg.setFocusItem(scrnMsg.A.no(selIndex).mtrLbDescTxt_CN);

        return null;
    }

    // START 2017/08/03 T.Kanasaka [QC#18193,DEL]
//    protected EZDCMsg setRequestData_OpenWinDefaultBilling(EZDApplicationContext ctx, NSAL1200BMsg scrnMsg) {
//        int selIndex = getButtonSelectNumber();
//
//        for (int i = 0; i < scrnMsg.P.length(); i++) {
//            if ("MTR_LB_CD".equals(scrnMsg.P.no(i).xxComnScrQueryColNm.getValue())) {
//                if (ZYPCommonFunc.hasValue(scrnMsg.P.no(i).xxComnScrColValTxt)) {
//                    setValue(scrnMsg.A.no(selIndex).bllgMtrLbCd_A, scrnMsg.P.no(i).xxComnScrColValTxt.getValue());
//                } else {
//                    scrnMsg.A.no(selIndex).bllgMtrLbCd_A.clear();
//                }
//            } else if ("MTR_LB_DESC_TXT".equals(scrnMsg.P.no(i).xxComnScrQueryColNm.getValue())) {
//                if (ZYPCommonFunc.hasValue(scrnMsg.P.no(i).xxComnScrColValTxt)) {
//                    setValue(scrnMsg.A.no(selIndex).mtrLbDescTxt_DB, scrnMsg.P.no(i).xxComnScrColValTxt.getValue());
//                } else {
//                    scrnMsg.A.no(selIndex).mtrLbDescTxt_DB.clear();
//                }
//            }
//        }
//        scrnMsg.setFocusItem(scrnMsg.A.no(selIndex).mtrLbDescTxt_DB);
//
//        return null;
//    }
//
//    protected EZDCMsg setRequestData_OpenWinFltCounter(EZDApplicationContext ctx, NSAL1200BMsg scrnMsg) {
//        int selIndex = getButtonSelectNumber();
//
//        for (int i = 0; i < scrnMsg.P.length(); i++) {
//            if ("MTR_LB_CD".equals(scrnMsg.P.no(i).xxComnScrQueryColNm.getValue())) {
//                if (ZYPCommonFunc.hasValue(scrnMsg.P.no(i).xxComnScrColValTxt)) {
//                    setValue(scrnMsg.A.no(selIndex).fleetMtrLbCd_A, scrnMsg.P.no(i).xxComnScrColValTxt.getValue());
//                } else {
//                    scrnMsg.A.no(selIndex).mdlMtrLbCd_A.clear();
//                }
//            } else if ("MTR_LB_DESC_TXT".equals(scrnMsg.P.no(i).xxComnScrQueryColNm.getValue())) {
//                if (ZYPCommonFunc.hasValue(scrnMsg.P.no(i).xxComnScrColValTxt)) {
//                    setValue(scrnMsg.A.no(selIndex).mtrLbDescTxt_FL, scrnMsg.P.no(i).xxComnScrColValTxt.getValue());
//                } else {
//                    scrnMsg.A.no(selIndex).mtrLbDescTxt_FL.clear();
//                }
//            }
//        }
//        scrnMsg.setFocusItem(scrnMsg.A.no(selIndex).mtrLbDescTxt_FL);
//
//        return null;
//    }
//
//    protected EZDCMsg setRequestData_OpenWinAggCounter(EZDApplicationContext ctx, NSAL1200BMsg scrnMsg) {
//        int selIndex = getButtonSelectNumber();
//
//        for (int i = 0; i < scrnMsg.P.length(); i++) {
//            if ("MTR_LB_CD".equals(scrnMsg.P.no(i).xxComnScrQueryColNm.getValue())) {
//                if (ZYPCommonFunc.hasValue(scrnMsg.P.no(i).xxComnScrColValTxt)) {
//                    setValue(scrnMsg.A.no(selIndex).aggrMtrLbCd_A, scrnMsg.P.no(i).xxComnScrColValTxt.getValue());
//                } else {
//                    scrnMsg.A.no(selIndex).aggrMtrLbCd_A.clear();
//                }
//            } else if ("MTR_LB_DESC_TXT".equals(scrnMsg.P.no(i).xxComnScrQueryColNm.getValue())) {
//                if (ZYPCommonFunc.hasValue(scrnMsg.P.no(i).xxComnScrColValTxt)) {
//                    setValue(scrnMsg.A.no(selIndex).mtrLbDescTxt_AG, scrnMsg.P.no(i).xxComnScrColValTxt.getValue());
//                } else {
//                    scrnMsg.A.no(selIndex).mtrLbDescTxt_AG.clear();
//                }
//            }
//        }
//        scrnMsg.setFocusItem(scrnMsg.A.no(selIndex).mtrLbDescTxt_AG);
//
//        return null;
//    }
    // END 2017/08/03 T.Kanasaka [QC#18193,DEL]

    // START 2017/08/03 T.Kanasaka [QC#18193,ADD]
    protected EZDCMsg setRequestData_OpenWin_LVL1Counter(EZDApplicationContext ctx, NSAL1200BMsg scrnMsg) {
        int selIndex = getButtonSelectNumber();

        for (int i = 0; i < scrnMsg.P.length(); i++) {
            if ("MTR_LB_CD".equals(scrnMsg.P.no(i).xxComnScrQueryColNm.getValue())) {
                if (ZYPCommonFunc.hasValue(scrnMsg.P.no(i).xxComnScrColValTxt)) {
                    setValue(scrnMsg.A.no(selIndex).bllgMtrLbCd_L1, scrnMsg.P.no(i).xxComnScrColValTxt.getValue());
                } else {
                    scrnMsg.A.no(selIndex).bllgMtrLbCd_L1.clear();
                }
            } else if ("MTR_LB_DESC_TXT".equals(scrnMsg.P.no(i).xxComnScrQueryColNm.getValue())) {
                if (ZYPCommonFunc.hasValue(scrnMsg.P.no(i).xxComnScrColValTxt)) {
                    setValue(scrnMsg.A.no(selIndex).mtrLbDescTxt_L1, scrnMsg.P.no(i).xxComnScrColValTxt.getValue());
                } else {
                    scrnMsg.A.no(selIndex).mtrLbDescTxt_L1.clear();
                }
            }
        }
        scrnMsg.setFocusItem(scrnMsg.A.no(selIndex).mtrLbDescTxt_L1);

        return null;
    }

    protected EZDCMsg setRequestData_OpenWin_LVL2Counter(EZDApplicationContext ctx, NSAL1200BMsg scrnMsg) {
        int selIndex = getButtonSelectNumber();

        for (int i = 0; i < scrnMsg.P.length(); i++) {
            if ("MTR_LB_CD".equals(scrnMsg.P.no(i).xxComnScrQueryColNm.getValue())) {
                if (ZYPCommonFunc.hasValue(scrnMsg.P.no(i).xxComnScrColValTxt)) {
                    setValue(scrnMsg.A.no(selIndex).bllgMtrLbCd_L2, scrnMsg.P.no(i).xxComnScrColValTxt.getValue());
                } else {
                    scrnMsg.A.no(selIndex).bllgMtrLbCd_L2.clear();
                }
            } else if ("MTR_LB_DESC_TXT".equals(scrnMsg.P.no(i).xxComnScrQueryColNm.getValue())) {
                if (ZYPCommonFunc.hasValue(scrnMsg.P.no(i).xxComnScrColValTxt)) {
                    setValue(scrnMsg.A.no(selIndex).mtrLbDescTxt_L2, scrnMsg.P.no(i).xxComnScrColValTxt.getValue());
                } else {
                    scrnMsg.A.no(selIndex).mtrLbDescTxt_L2.clear();
                }
            }
        }
        scrnMsg.setFocusItem(scrnMsg.A.no(selIndex).mtrLbDescTxt_L2);

        return null;
    }

    protected EZDCMsg setRequestData_OpenWin_LVL3Counter(EZDApplicationContext ctx, NSAL1200BMsg scrnMsg) {
        int selIndex = getButtonSelectNumber();

        for (int i = 0; i < scrnMsg.P.length(); i++) {
            if ("MTR_LB_CD".equals(scrnMsg.P.no(i).xxComnScrQueryColNm.getValue())) {
                if (ZYPCommonFunc.hasValue(scrnMsg.P.no(i).xxComnScrColValTxt)) {
                    setValue(scrnMsg.A.no(selIndex).bllgMtrLbCd_L3, scrnMsg.P.no(i).xxComnScrColValTxt.getValue());
                } else {
                    scrnMsg.A.no(selIndex).bllgMtrLbCd_L3.clear();
                }
            } else if ("MTR_LB_DESC_TXT".equals(scrnMsg.P.no(i).xxComnScrQueryColNm.getValue())) {
                if (ZYPCommonFunc.hasValue(scrnMsg.P.no(i).xxComnScrColValTxt)) {
                    setValue(scrnMsg.A.no(selIndex).mtrLbDescTxt_L3, scrnMsg.P.no(i).xxComnScrColValTxt.getValue());
                } else {
                    scrnMsg.A.no(selIndex).mtrLbDescTxt_L3.clear();
                }
            }
        }
        scrnMsg.setFocusItem(scrnMsg.A.no(selIndex).mtrLbDescTxt_L3);

        return null;
    }
    // END 2017/08/03 T.Kanasaka [QC#18193,ADD]

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        if (!NWAL1130_EVENT_NM_CLOSE.equals(getLastGuard())) {

            NSAL1200BMsg scrnMsg = (NSAL1200BMsg) bMsg;
            NSAL1200CMsg bizMsg = (NSAL1200CMsg) cMsg;

            if (LINK_NM_OPEN_WIN_NWAL1130.equals(scrnMsg.xxScrEventNm.getValue())) {
                doProcess_OpenWinNWAL1130(ctx, scrnMsg, bizMsg);

            } else if (BTN_NM_OPEN_WIN_COUNTER.equals(scrnMsg.xxScrEventNm.getValue())) {
                doProcess_OpenWinCounter(ctx, scrnMsg, bizMsg);
            }
        }
    }

    protected void doProcess_OpenWinNWAL1130(EZDApplicationContext ctx, NSAL1200BMsg scrnMsg, NSAL1200CMsg bizMsg) {
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        S21SortColumnIMGController.clearIMG(SCRN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        NSAL1200CommonLogic.initialControlScreen(this, scrnMsg);
    }

    protected void doProcess_OpenWinCounter(EZDApplicationContext ctx, NSAL1200BMsg scrnMsg, NSAL1200CMsg bizMsg) {
        NSAL1200CommonLogic.initialControlScreen(this, scrnMsg);
    }
}
