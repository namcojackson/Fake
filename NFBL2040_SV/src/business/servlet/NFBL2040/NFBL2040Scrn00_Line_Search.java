/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFBL2040.NFBL2040CMsg;
import business.servlet.NFBL2040.common.NFBL2040CommonLogic;
import business.servlet.NFBL2040.constant.NFBL2040Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2020/02/17   Fujitsu         H.Ikeda         Create          QC#53413
 * 2022/02/15   Hitachi         A.Kohinata      Update          QC#57090
 *</pre>
 */
public class NFBL2040Scrn00_Line_Search extends S21CommonHandler implements NFBL2040Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;

        // Mandatory Check
        // S21UserProfileService Instance
        S21UserProfileService PROFILE_SERVICE = S21UserProfileServiceFactory.getInstance().getService();
        // Global Company Code
        String GLBL_CMPY_CD = PROFILE_SERVICE.getGlobalCompanyCode();
        // Get Varchar Const value(NFBL2040_PO_MATCH)
        String varCharConstValPoMatch = ZYPCodeDataUtil.getVarCharConstValue(VARCHAR_CONST_NFBL2040_PO_MATCH, GLBL_CMPY_CD);
        if (ZYPCommonFunc.hasValue(varCharConstValPoMatch)) {
            // mod start 2022/02/15 QC#57090
            //// PO Number or Receipt Number or Vendor Return Number is Mandatory.
            //if (!ZYPCommonFunc.hasValue(scrnMsg.poNum_L) 
            //&&  !ZYPCommonFunc.hasValue(scrnMsg.delyOrdNum_L)) {
            //    scrnMsg.poNum_L.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.poNum_L.getNameForMessage() });
            //    scrnMsg.delyOrdNum_LA.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.delyOrdNum_L.getNameForMessage() });
            //}
            if (varCharConstValPoMatch.equals(scrnMsg.cmApInvTpCd_S.getValue())) {
                // PO Number or Receipt Number is Mandatory.
                if (!ZYPCommonFunc.hasValue(scrnMsg.poNum_L) && !ZYPCommonFunc.hasValue(scrnMsg.delyOrdNum_L)) {
                    scrnMsg.poNum_L.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.poNum_L.getNameForMessage() });
                    scrnMsg.delyOrdNum_L.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.delyOrdNum_L.getNameForMessage() });
                }
            // if invoice type is credit memo
            } else {
                // PO Number or Vendor Return Number is Mandatory.
                if (!ZYPCommonFunc.hasValue(scrnMsg.poNum_L) && !ZYPCommonFunc.hasValue(scrnMsg.vndRtrnNum_L)) {
                    scrnMsg.poNum_L.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.poNum_L.getNameForMessage() });
                    scrnMsg.vndRtrnNum_L.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.vndRtrnNum_L.getNameForMessage() });
                }
            }
            // mod end 2022/02/15 QC#57090
        } else {
            scrnMsg.setMessageInfo(NFBM0028E);
            throw new EZDFieldErrorException();
        }

        // mod start 2022/02/15 QC#57090
        //if (ZYPCommonFunc.hasValue(scrnMsg.poNum_L) && scrnMsg.poNum_L.getValue().equals(scrnMsg.poNum_HD.getValue())) {
        //    scrnMsg.poNum_L.setErrorInfo(1, NFBM0293E);
        //    scrnMsg.setFocusItem(scrnMsg.poNum_L);
        //    scrnMsg.addCheckItem(scrnMsg.poNum_L);
        //    scrnMsg.putErrorScreen();
        //    throw new EZDFieldErrorException();
        //}
        if (ZYPCommonFunc.hasValue(scrnMsg.poNum_L)) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).poNum_A1) && scrnMsg.poNum_L.getValue().equals(scrnMsg.A.no(i).poNum_A1.getValue())) {
                    scrnMsg.poNum_L.setErrorInfo(1, NFBM0293E);
                    scrnMsg.setFocusItem(scrnMsg.poNum_L);
                    break;
                }
            }
        }
       // mod end 2022/02/15 QC#57090

        // add start 2022/02/15 QC#57090
        if (ZYPCommonFunc.hasValue(scrnMsg.vndRtrnNum_L)) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).vndRtrnNum_A1) && scrnMsg.vndRtrnNum_L.getValue().equals(scrnMsg.A.no(i).vndRtrnNum_A1.getValue())) {
                    scrnMsg.vndRtrnNum_L.setErrorInfo(1, NFBM0295E);
                    scrnMsg.setFocusItem(scrnMsg.vndRtrnNum_L);
                    break;
                }
            }
        }
        // add end 2022/02/15 QC#57090

        scrnMsg.addCheckItem(scrnMsg.poNum_L);
        scrnMsg.addCheckItem(scrnMsg.delyOrdNum_L);
        // add start 2022/02/15 QC#57090
        scrnMsg.addCheckItem(scrnMsg.vndRtrnNum_L);
        // add end 2022/02/15 QC#57090
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;

        NFBL2040CMsg bizMsg = new NFBL2040CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_20);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPageTblNm, TABLE_A);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
        NFBL2040CMsg bizMsg  = (NFBL2040CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.addCheckItem(scrnMsg.poNum_L);
        // add start 2022/02/15 QC#57090
        scrnMsg.addCheckItem(scrnMsg.vndRtrnNum_L);
        // add end 2022/02/15 QC#57090
        scrnMsg.putErrorScreen();
        if (MESSAGE_KIND_E.equals(bizMsg.getMessageKind())) {
            // START 2020/03/16 [QC#55993, ADD]
            NFBL2040CommonLogic.clearHoldReleasePending(scrnMsg, true);
            // END   2020/03/16 [QC#55993, ADD]
            throw new EZDFieldErrorException();
        }

        String dispMode = NFBL2040CommonLogic.getDisplayMode(this, scrnMsg);
        if (DISPLAY_MODE_NOT_OTH_SCR_EDIT.equals(dispMode)) {
            if (!MESSAGE_KIND_E.equals(bizMsg.getMessageKind())) {
                // Common
                NFBL2040CommonLogic.setInputProtectedNotFromOthScrForEditCommon(scrnMsg);
                NFBL2040CommonLogic.setButtonNotFromOthScrForEditCommon(this, scrnMsg, true);

                NFBL2040CommonLogic.setInputProtectedNotFromOthScrForEditForSearch(scrnMsg);
                NFBL2040CommonLogic.setButtonNotFromOthScrForEditForSearch(this, scrnMsg);

                NFBL2040CommonLogic.setInputProtectedNotFromOthScrForEditForSearchLinesTab(scrnMsg, false);
                NFBL2040CommonLogic.setButtonNotFromOthScrForEditForSearchLinesTab(this, scrnMsg);
            }
        } else {
            /** Initialize button control */ 
            NFBL2040CommonLogic.initControl(this, scrnMsg);
        }

        /** Set alternate rows background color */
        NFBL2040CommonLogic.setAlternateRowsBGCommon(scrnMsg);
        NFBL2040CommonLogic.clearRowsBG_H(scrnMsg);
        /** Initialize tab position */
        NFBL2040CommonLogic.initTabPosition(scrnMsg);
        /** Set focus when opening screen */
//        if (CM_AP_INV_TP.PO_MATCH.equals(bizMsg.cmApInvTpCd_S.getValue())) {
//            scrnMsg.setFocusItem(scrnMsg.apVndInvNum);
//        } else {
//            scrnMsg.setFocusItem(scrnMsg.dplyVndNm);
//        }
        NFBL2040CommonLogic.setFocusRule(scrnMsg);
        NFBL2040CommonLogic.clearRowsBG_NoSelectTab(bMsg);
        // START 2020/03/16 [QC#55993, ADD]
        NFBL2040CommonLogic.clearHoldReleasePending(scrnMsg, true);
        // END   2020/03/16 [QC#55993, ADD]
    }
}
