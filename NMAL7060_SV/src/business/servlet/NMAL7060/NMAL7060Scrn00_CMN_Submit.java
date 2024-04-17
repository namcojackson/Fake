/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7060;

import static business.servlet.NMAL7060.constant.NMAL7060Constant.BIZ_ID;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.BTN_DELETE_ROW;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.BTN_DELETE_ROW_BLLG;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.NMAM0042E;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.NMAM0043E;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.NMAM8214E;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.NMAM8234I;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.ZZM9000E;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.ZZM9037E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7060.NMAL7060CMsg;
import business.servlet.NMAL7060.common.NMAL7060CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7060Scrn00_CMN_Submit
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/01   Fujitsu         W.Honda         Create          N/A
 * 2016/03/10   Fujitsu         Y.Kanefusa      Update          QC#4963
 * 2016/04/26   Fujitsu         W.Honda         Update          QC#6738
 * 2017/02/16   Fujitsu         R.Nakamura      Update          QC#17529
 *</pre>
 */
public class NMAL7060Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7060BMsg scrnMsg = (NMAL7060BMsg) bMsg;

        scrnMsg.clearErrorInfo();
        scrnMsg.A.clearErrorInfo();

        // Mandatory Check
        scrnMsg.addCheckItem(scrnMsg.prcMtrPkgNm);
        scrnMsg.addCheckItem(scrnMsg.effFromDt);
        scrnMsg.addCheckItem(scrnMsg.effThruDt);
        scrnMsg.addCheckItem(scrnMsg.prcMtrPkgDescTxt);
        scrnMsg.putErrorScreen();
        if (!ZYPCommonFunc.hasValue(scrnMsg.prcMtrPkgNm)) {
            // Mod Start 2017/02/16 QC#17529
//            scrnMsg.prcMtrPkgNm.setErrorInfo(1, NMAM0192E);
            scrnMsg.prcMtrPkgNm.setErrorInfo(1, ZZM9000E, new String[] {"Package Name" });
            // Mod End 2017/02/16 QC#17529
            scrnMsg.setMessageInfo(ZZM9037E);
            scrnMsg.addCheckItem(scrnMsg.prcMtrPkgNm);
            scrnMsg.putErrorScreen();
            throw new EZDFieldErrorException();
        } else if (!ZYPCommonFunc.hasValue(scrnMsg.effFromDt)) {
            // Mod Start 2017/02/16 QC#17529
//            scrnMsg.effFromDt.setErrorInfo(1, NMAM0192E);
            scrnMsg.effFromDt.setErrorInfo(1, ZZM9000E, new String[] {"Effective Date From" });
            // Mod End 2017/02/16 QC#17529
            scrnMsg.setMessageInfo(ZZM9037E);
            scrnMsg.addCheckItem(scrnMsg.effFromDt);
            scrnMsg.putErrorScreen();
            throw new EZDFieldErrorException();
        }

        // Date Relation Check
        if (ZYPCommonFunc.hasValue(scrnMsg.effFromDt)
                && ZYPCommonFunc.hasValue(scrnMsg.effThruDt)
                && ZYPDateUtil.compare(scrnMsg.effFromDt.getValue(), scrnMsg.effThruDt.getValue()) > 0) {
            scrnMsg.effFromDt.setErrorInfo(1, NMAM0043E, new String[]{scrnMsg.effFromDt.getNameForMessage(), scrnMsg.effThruDt.getNameForMessage()});
            scrnMsg.effThruDt.setErrorInfo(1, NMAM0042E, new String[]{scrnMsg.effThruDt.getNameForMessage(), scrnMsg.effFromDt.getNameForMessage()});
            scrnMsg.setMessageInfo(ZZM9037E);
            scrnMsg.addCheckItem(scrnMsg.effFromDt);
            scrnMsg.addCheckItem(scrnMsg.effThruDt);
            scrnMsg.putErrorScreen();
        }

        if (scrnMsg.A.getValidCount() < 1) {
            scrnMsg.setMessageInfo(NMAM8214E, new String[]{"Model"});
            throw new EZDFieldErrorException();
        }

        // Date Relation Check For Header & Associated Modeles
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).mdlNm_A1)) {
                // Mod Start 2017/02/16 QC#17529
//                scrnMsg.A.no(i).mdlNm_A1.setErrorInfo(1, NMAM0192E);
//                scrnMsg.A.no(i).effFromDt_A1.setErrorInfo(1, NMAM0192E);
                scrnMsg.A.no(i).effFromDt_A1.setErrorInfo(1, ZZM9000E, new String[] {"Service Model Name" });
                // Mod End 2017/02/16 QC#17529
                scrnMsg.addCheckItem(scrnMsg.A.no(i).mdlNm_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A1);
                scrnMsg.putErrorScreen();
                throw new EZDFieldErrorException();
            // Add Start 2017/02/16 QC#17529
            } else if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).mdlNm_A1)) {
              scrnMsg.A.no(i).effFromDt_A1.setErrorInfo(1, ZZM9000E, new String[] {"Start Date" });
              scrnMsg.addCheckItem(scrnMsg.A.no(i).mdlNm_A1);
              scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A1);
              scrnMsg.putErrorScreen();
              throw new EZDFieldErrorException();
            }
            // Add End 2017/02/16 QC#17529
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mdlNm_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_A1);
            scrnMsg.putErrorScreen();

            // DEL START 2016/03/10 #4963
//            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcMtrPkgMdlPk_A1)) {
//                if (ZYPCommonFunc.hasValue(scrnMsg.effFromDt)
//                        && ZYPCommonFunc.hasValue(scrnMsg.A.no(i).effFromDt_A1)
//                        && ZYPDateUtil.compare(scrnMsg.effFromDt.getValue(), scrnMsg.A.no(i).effFromDt_A1.getValue()) > 0) {
//                    scrnMsg.A.no(i).effFromDt_A1.setErrorInfo(1, NMAM0042E, new String[] {scrnMsg.A.no(i).effFromDt_A1.getNameForMessage(), scrnMsg.effFromDt.getNameForMessage()});
//                    scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A1);
//                }
//            }
//            if (ZYPCommonFunc.hasValue(scrnMsg.effThruDt)) {
//                if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).effThruDt_A1)
//                        || (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).effThruDt_A1)
//                                && ZYPDateUtil.compare(scrnMsg.effThruDt.getValue(), scrnMsg.A.no(i).effThruDt_A1.getValue()) < 0)) {
//                    scrnMsg.A.no(i).effThruDt_A1.setErrorInfo(1, NMAM0043E, new String[] {scrnMsg.A.no(i).effThruDt_A1.getNameForMessage(), scrnMsg.effThruDt.getNameForMessage()});
//                    scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_A1);
//                }
//            }
            // DEL END 2016/03/10 #4963
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).effFromDt_A1)
                    && ZYPCommonFunc.hasValue(scrnMsg.A.no(i).effThruDt_A1)
                    && ZYPDateUtil.compare(scrnMsg.A.no(i).effThruDt_A1.getValue(), scrnMsg.A.no(i).effFromDt_A1.getValue()) < 0) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).mdlId_A1)) {
                    scrnMsg.A.no(i).effFromDt_A1.setErrorInfo(1, NMAM0043E, new String[] {scrnMsg.A.no(i).effFromDt_A1.getNameForMessage(), scrnMsg.A.no(i).effThruDt_A1.getNameForMessage()});
                }
                scrnMsg.A.no(i).effThruDt_A1.setErrorInfo(1, NMAM0042E, new String[] {scrnMsg.A.no(i).effThruDt_A1.getNameForMessage(), scrnMsg.A.no(i).effFromDt_A1.getNameForMessage()});
                scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_A1);
            }
        }

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.B.no(i).mtrLbDescTxt_B1)) {
                // Mod Start 2017/02/16 QC#17529
//                scrnMsg.B.no(i).mtrLbDescTxt_B1.setErrorInfo(1, NMAM0192E);
//                scrnMsg.setMessageInfo(NMAM8214E);
                scrnMsg.B.no(i).mtrLbDescTxt_B1.setErrorInfo(1, ZZM9000E, new String[] {"Billing Counter Name" });
                // Mod End 2017/02/16 QC#17529
            }

            scrnMsg.addCheckItem(scrnMsg.B.no(i).mtrLbDescTxt_B1);
        }

        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.C.no(i).mtrMultRate_C1);
        }

        scrnMsg.addCheckItem(scrnMsg.prcMtrPkgNm);
        scrnMsg.addCheckItem(scrnMsg.prcMtrPkgDescTxt);
        scrnMsg.addCheckItem(scrnMsg.effFromDt);
        scrnMsg.addCheckItem(scrnMsg.effThruDt);
        scrnMsg.addCheckItem(scrnMsg.corpAdvPrcFlg);
        scrnMsg.putErrorScreen();
        if (ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7060BMsg scrnMsg = (NMAL7060BMsg) bMsg;

        NMAL7060CMsg bizMsg = new NMAL7060CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7060BMsg scrnMsg = (NMAL7060BMsg) bMsg;
        NMAL7060CMsg bizMsg = (NMAL7060CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            scrnMsg.addCheckItem(scrnMsg.prcMtrPkgNm);
            scrnMsg.addCheckItem(scrnMsg.xxRadioBtn);
            scrnMsg.addCheckItem(scrnMsg.xxRadioBtn_B1);
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.addCheckItem(scrnMsg.A.no(i).mdlNm_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_A1);
            }
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                scrnMsg.addCheckItem(scrnMsg.B.no(i).bllgMtrLvlNum_B1);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).mtrLbDescTxt_B1);
            }
                // QC#6738 2016/04/26 Del start
//            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
//                scrnMsg.addCheckItem(scrnMsg.C.no(i).bllblFlg_C1);
//                scrnMsg.addCheckItem(scrnMsg.C.no(i).actvFlg_C1);
//            }
                // QC#6738 2016/04/26 Del end
            scrnMsg.putErrorScreen();
            throw new EZDFieldErrorException();
        }

        NMAL7060CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg);
        setButtonConfirmMsgEx(BTN_DELETE_ROW[1], NMAM8234I, new String[] {}, 1);
        setButtonConfirmMsgEx(BTN_DELETE_ROW_BLLG[1], NMAM8234I, new String[] {}, 1);

        scrnMsg.setFocusItem(scrnMsg.prcMtrPkgNm);
    }
}
