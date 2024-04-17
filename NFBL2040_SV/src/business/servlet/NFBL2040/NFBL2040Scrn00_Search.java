/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFBL2040.NFBL2040CMsg;
//import business.servlet.NFBL2040.constant.NFBL2040Constant;

import business.blap.NFBL2040.NFBL2040CMsg;
import business.servlet.NFBL2040.common.NFBL2040CommonLogic;
import business.servlet.NFBL2040.constant.NFBL2040Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CM_AP_INV_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   CUSA            Y.Aikawa        Create          N/A
 * 2016/07/29   Hitachi         K.Kojima        Update          QC#12665
 * 2016/08/05   Hitachi         Y.Tsuchimoto    Update          QC#12799
 * 2016/09/14   Hitachi         Y.Tsuchimoto    Update          QC#12799
 * 2016/09/26   Hitachi         T.Tsuchida      Update          QC#12038
 * 2017/01/13   Hitachi         E.Kameishi      Update          QC#16949
 * 2017/12/21   Hitachi         J.KIm           Update          QC#21638
 * 2018/05/25   CITS            K.Ogino         Update          QC#25902,QC#25190,QC#25141
 * 2020/02/17   Fujitsu         H.Ikeda         Update          QC#53413
 * 2022/02/15   Hitachi         A.Kohinata      Update          QC#57090
 *</pre>
 */
public class NFBL2040Scrn00_Search extends S21CommonHandler implements NFBL2040Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;

        // Mandatory Check
        // Invoice Type is Mandatory.
        if (!ZYPCommonFunc.hasValue(scrnMsg.cmApInvTpCd_S)) {
            scrnMsg.cmApInvTpCd_S.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.cmApInvTpCd_S.getNameForMessage() });
        }

        // S21UserProfileService Instance
        S21UserProfileService PROFILE_SERVICE = S21UserProfileServiceFactory.getInstance().getService();
        // Global Company Code
        String GLBL_CMPY_CD = PROFILE_SERVICE.getGlobalCompanyCode();
        // Get Varchar Const value(NFBL2040_PO_MATCH)
        String varCharConstValPoMatch = ZYPCodeDataUtil.getVarCharConstValue(VARCHAR_CONST_NFBL2040_PO_MATCH, GLBL_CMPY_CD);
        if (ZYPCommonFunc.hasValue(varCharConstValPoMatch)) {
            // MOD QC#25902,QC#25190,QC#25141
            if (varCharConstValPoMatch.equals(scrnMsg.cmApInvTpCd_S.getValue())) {
                // PO Number or Receipt Number or Vendor Return Number is Mandatory.
                if (!ZYPCommonFunc.hasValue(scrnMsg.poNum) 
                &&  !ZYPCommonFunc.hasValue(scrnMsg.delyOrdNum_H)
                &&  !ZYPCommonFunc.hasValue(scrnMsg.vndRtrnNum)) {
                    scrnMsg.poNum.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.poNum.getNameForMessage() });
                    scrnMsg.delyOrdNum_H.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.delyOrdNum_H.getNameForMessage() });
                }
            // if invoice type is credit memo
            } else {
                if (!ZYPCommonFunc.hasValue(scrnMsg.poNum) && !ZYPCommonFunc.hasValue(scrnMsg.vndRtrnNum)) {
                    // PO Number is Mandatory.
                    scrnMsg.poNum.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.poNum.getNameForMessage() });
                    scrnMsg.vndRtrnNum.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.vndRtrnNum.getNameForMessage() });
                }
            }
            // END QC#25902,QC#25190,QC#25141
        } else {
            scrnMsg.setMessageInfo(NFBM0028E);
            throw new EZDFieldErrorException();
        }
        scrnMsg.addCheckItem(scrnMsg.cmApInvTpCd_S);
        scrnMsg.addCheckItem(scrnMsg.poNum);
        scrnMsg.addCheckItem(scrnMsg.delyOrdNum_H);
        // START QC#25902,QC#25190,QC#25141
        scrnMsg.addCheckItem(scrnMsg.vndRtrnNum);
        // END QC#25902,QC#25190,QC#25141
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
        // START 2016/09/26 T.Tsuchida [QC#12038,ADD]
        scrnMsg.addCheckItem(scrnMsg.poNum);
        // START QC#25902,QC#25190,QC#25141
        scrnMsg.addCheckItem(scrnMsg.vndRtrnNum);
        // END QC#25902,QC#25190,QC#25141
        scrnMsg.putErrorScreen();
        // END 2016/09/26 T.Tsuchida [QC#12038,ADD]
        if (MESSAGE_KIND_E.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        // START 2016/09/15 Y.Tsuchimoto [QC#13156,MOD]
        String dispMode = NFBL2040CommonLogic.getDisplayMode(this, scrnMsg);
        if (DISPLAY_MODE_NOT_OTH_SCR_EDIT.equals(dispMode)) {
            if (!MESSAGE_KIND_E.equals(bizMsg.getMessageKind())) {
                // Common
                NFBL2040CommonLogic.setInputProtectedNotFromOthScrForEditCommon(scrnMsg);
                // START 2020/02/17 [QC#53413, MOD]
                //NFBL2040CommonLogic.setButtonNotFromOthScrForEditCommon(this, scrnMsg);
                // mod start 2022/02/15 QC#57090
                //boolean mtBtnFlg = false;
                //boolean mtFdFlg = false;
                //if (ZYPCommonFunc.hasValue(scrnMsg.vndRtrnNum)) {
                //    mtBtnFlg = false;
                //    mtFdFlg = true;
                //} else {
                //    mtBtnFlg = true;
                //    mtFdFlg = false;
                //}
                //NFBL2040CommonLogic.setButtonNotFromOthScrForEditCommon(this, scrnMsg, mtBtnFlg);
                NFBL2040CommonLogic.setButtonNotFromOthScrForEditCommon(this, scrnMsg, true);
                // mod end 2022/02/15 QC#57090
                // END   2020/02/17 [QC#53413, MOD]
                NFBL2040CommonLogic.setInputProtectedNotFromOthScrForEditForSearch(scrnMsg);
                NFBL2040CommonLogic.setButtonNotFromOthScrForEditForSearch(this, scrnMsg);
                // START 2020/02/17 [QC#53413, MOD]
                //NFBL2040CommonLogic.setInputProtectedNotFromOthScrForEditForSearchLinesTab(scrnMsg);
                // mod start 2022/02/15 QC#57090
                //NFBL2040CommonLogic.setInputProtectedNotFromOthScrForEditForSearchLinesTab(scrnMsg, mtFdFlg);
                NFBL2040CommonLogic.setInputProtectedNotFromOthScrForEditForSearchLinesTab(scrnMsg, false);
                // mod end 2022/02/15 QC#57090
                // END   2020/02/17 [QC#53413, MOD]
                NFBL2040CommonLogic.setButtonNotFromOthScrForEditForSearchLinesTab(this, scrnMsg);
            }
        } else {
            /** Initialize button control */ 
            NFBL2040CommonLogic.initControl(this, scrnMsg);
        }
        // END   2016/09/15 Y.Tsuchimoto [QC#13156,MOD]

        /** Set alternate rows background color */
        NFBL2040CommonLogic.setAlternateRowsBGCommon(scrnMsg);
        NFBL2040CommonLogic.clearRowsBG_H(scrnMsg);
        /** Initialize tab position */
        NFBL2040CommonLogic.initTabPosition(scrnMsg);
        // START 2017/01/13 E.Kameishi [QC#16949,MOD]
        /** Set focus when opening screen */
        // START 2017/12/20 J.Kim [QC#21638,MOD]
        // //scrnMsg.setFocusItem(scrnMsg.cmApInvTpCd_S);
        // scrnMsg.setFocusItem(scrnMsg.dplyVndNm);
        if (CM_AP_INV_TP.PO_MATCH.equals(bizMsg.cmApInvTpCd_S.getValue())) {
            scrnMsg.setFocusItem(scrnMsg.apVndInvNum);
        } else {
            scrnMsg.setFocusItem(scrnMsg.dplyVndNm);
        }
        // END 2017/12/20 J.Kim [QC#21638,MOD]
        NFBL2040CommonLogic.setFocusRule(scrnMsg);
        // END 2017/01/13 E.Kameishi [QC#16949,MOD]

        // START 2016/09/14 Y.Tsuchimoto [QC#13156,DEL]
        //// START 2016/09/14 Y.Tsuchimoto [QC#12799,ADD]
        //// START 2016/08/05 Y.Tsuchimoto [QC#12799,MOD]
        //if ((ZYPCommonFunc.hasValue(scrnMsg.poNum) && scrnMsg.poNum.getValue().equals(scrnMsg.poOrdNum.getValue()))
        //        || (ZYPCommonFunc.hasValue(scrnMsg.delyOrdNum_H) && scrnMsg.delyOrdNum_H.getValue().equals(scrnMsg.delyOrdNum_DH.getValue()))) {
        //    scrnMsg.poOrdNum.setInputProtected(true);
        //    scrnMsg.delyOrdNum_DH.setInputProtected(true);
        //} else {
        //    scrnMsg.poOrdNum.setInputProtected(false);
        //    scrnMsg.delyOrdNum_DH.setInputProtected(false);
        //}
        ////if (ZYPCommonFunc.hasValue(scrnMsg.delyOrdNum_H) && scrnMsg.delyOrdNum_H.getValue().equals(scrnMsg.delyOrdNum_DH.getValue())) {
        ////    scrnMsg.delyOrdNum_DH.setInputProtected(true);
        ////} else {
        ////    scrnMsg.delyOrdNum_DH.setInputProtected(false);
        ////}
        //// END   2016/09/14 Y.Tsuchimoto [QC#12799,MOD]
        //// END   2016/08/05 Y.Tsuchimoto [QC#12799,ADD]
        // END   2016/09/14 Y.Tsuchimoto [QC#13156,DEL]

        // START 2016/07/29 K.Kojima [QC#12665,ADD]
        NFBL2040CommonLogic.clearRowsBG_NoSelectTab(bMsg);
        // END 2016/07/29 K.Kojima [QC#12665,ADD]
    }
}
