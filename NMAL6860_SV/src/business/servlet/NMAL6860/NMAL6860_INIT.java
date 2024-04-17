/**
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6860;

import static business.servlet.NMAL6860.common.NMAL6860CommonLogic.copyScrnMsgToBizMsgForSearch;
import static business.servlet.NMAL6860.common.NMAL6860CommonLogic.disabledButtonProperties;
import static business.servlet.NMAL6860.common.NMAL6860CommonLogic.initializeButtonProperties;
import static business.servlet.NMAL6860.common.NMAL6860CommonLogic.isEntryGranted;
import static business.servlet.NMAL6860.common.NMAL6860CommonLogic.setCursorRuleForSwhDetail;
import static business.servlet.NMAL6860.common.NMAL6860CommonLogic.setDisplayNameForMessage;
import static business.servlet.NMAL6860.common.NMAL6860CommonLogic.setDisplayNameForMessageOfSupplierSite;
import static business.servlet.NMAL6860.common.NMAL6860CommonLogic.setInputProtectedForCommonHeader;
import static business.servlet.NMAL6860.common.NMAL6860CommonLogic.setInputProtectedForDetailTab;
import static business.servlet.NMAL6860.common.NMAL6860CommonLogic.setInputProtectedForGeneralTab;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.BIZ_ID;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.NEW_SUPPLIER;
import static business.servlet.NMAL6860.constant.NMAL6860Constant.TAB_NM_GENERAL;

import java.io.Serializable;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6860.NMAL6860CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRNT_VND_TP;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Business ID   : NMAL6860 Supplier Entry
 * Function Name : The business process for Initialization.
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/08/03   CITS            M.Ouchi         Create          N/A
 * 2020/10/28   CITS            R.Kurahashi     Update          QC#57732
 * 2020/12/12   CITS            R.Kurahashi     Update          QC#57732-1
 * 2021/03/01   CITS            G.Delgado       Update          QC#56057
 *</pre>
 */
public class NMAL6860_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL6860BMsg scrnMsg = (NMAL6860BMsg) bMsg;
        // sets the parameters.
        setParametersFromPreviousScreen(scrnMsg);
        // copies scrnMsg to bizMsg.
        return copyScrnMsgToBizMsgForSearch(scrnMsg);
    }

    /**
     * <p>
     * Sets the parameters from previous screen.
     * </p>
     * @param scrnMsg scrnMsg
     */
    private void setParametersFromPreviousScreen(NMAL6860BMsg scrnMsg) {
        Serializable args = getArgForSubScreen();
        if (args == null || !(args instanceof Object[])) {
            return;
        }
        Object[] params = (Object[]) args;
        if (params.length == 0) {
            return;
        }
        // sets the Parent Vendor PK.
        ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndPk_P, (EZDBBigDecimalItem) params[0]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNum_P, (EZDBStringItem) params[1]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.billToCustCd_P, (EZDBStringItem) params[2]);
        
        // START 2020/10/28 R.Kurahashi [QC#57732,ADD]
        if (params.length == 4) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndNm_DF, (String) params[3]);
        }
        // END 2020/10/28 R.Kurahashi [QC#57732,ADD]
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // copies bizMsg to scrnMsg.
        NMAL6860BMsg scrnMsg = (NMAL6860BMsg) bMsg;
        EZDMsg.copy((NMAL6860CMsg) cMsg, null, scrnMsg, null);
        
        // START 2020/10/28 R.Kurahashi [QC#57732,ADD]
        if (ZYPCommonFunc.hasValue(scrnMsg.prntVndNm_DF) && scrnMsg.prntVndNm_DF.getValue().equals(NEW_SUPPLIER)) {
            scrnMsg.prntVndCd.clear();
        }
        // END 2020/10/28 R.Kurahashi [QC#57732,ADD]

        // START 2021/03/01 G.Delgado [QC#56057,MOD]
        // sets the button properties.
        // initializeButtonProperties(this);
        initializeButtonProperties(this, scrnMsg);
        // END 2021/03/01 G.Delgado [QC#56057,MOD]

        // START 2020/12/12 R.Kurahashi [QC#57732-1,ADD]
        if (scrnMsg.prntVndTpCd.getValue().equals(PRNT_VND_TP.ARREFUND) && ZYPCommonFunc.hasValue(scrnMsg.prntVndCd)) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                disabledButtonProperties(this, i);
            }
        }
        // END 2020/12/12 R.Kurahashi [QC#57732-1,ADD]

        // focuses on Supplier Number/Supplier Name on General Tab.
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, TAB_NM_GENERAL);
        if (isEntryGranted() && !ZYPCommonFunc.hasValue(scrnMsg.prntVndCd)) {
            scrnMsg.setFocusItem(scrnMsg.prntVndCd);
        } else {
            scrnMsg.setFocusItem(scrnMsg.prntVndNm);
        }

        // sets the input fields of Common Header.
        setInputProtectedForCommonHeader(scrnMsg);
        // sets the input fields of General Tab.
        setInputProtectedForGeneralTab(scrnMsg);
        // sets the input fields of Detail Tab.
        setInputProtectedForDetailTab(scrnMsg);

        // START 2021/03/01 G.Delgado [QC#56057,ADD]
        if (scrnMsg.prntVndTpDescTxt.isInputProtected()) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyCtrlFlg_D1, ZYPConstant.FLG_ON_Y);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyCtrlFlg_D1, ZYPConstant.FLG_OFF_N);
        }
        // END 2021/03/01 G.Delgado [QC#56057,ADD]

        setCursorRuleForSwhDetail(scrnMsg);

        // sets the display name.
        for (int index = 0; index < scrnMsg.A.getValidCount(); index++) {
            setDisplayNameForMessageOfSupplierSite(scrnMsg.A.no(index));
        }

        // Set Application Fraction Digit
        scrnMsg.invTolRate_H2.setAppFracDigit(2);
        scrnMsg.rcvTolRate_H2.setAppFracDigit(2);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        // sets the display name.
        setDisplayNameForMessage((NMAL6860BMsg) bMsg);
    }
}
