/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3170;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFCL3170.NFCL3170CMsg;
//import business.servlet.NFCL3170.constant.NFCL3170Constant;

import business.blap.NFCL3170.NFCL3170CMsg;
import business.servlet.NFCL3170.common.NFCL3170CommonLogic;
import business.servlet.NFCL3170.constant.NFCL3170Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/07   Fujitsu         T.Tanaka        Create          N/A
 * 2023/02/06   Hitachi         R.Takau         Update          QC#55645
 * 2023/03/10   Hitachi         S.Nakatani      Update          QC#55645
 *</pre>
 */
public class NFCL3170_INIT extends S21INITCommonHandler implements NFCL3170Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFCL3170BMsg scrnMsg = (NFCL3170BMsg) bMsg;
        checkBusinessAppGranted(getContextUserInfo().getUserId(), "NFCL3170");

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3170BMsg scrnMsg = (NFCL3170BMsg) bMsg;

        // SubmitedBusinessAplId -Get
        String busAppGrId = getSubmitedBusinessAplId(ctx);
        
        if (SEARCH_BUSINESS_ID.equals(busAppGrId)) {
            scrnMsg.xxBizAppId.setValue(SEARCH_BUSINESS_ID);
        } else {
            scrnMsg.xxBizAppId.setValue(BUSINESS_ID);
        }
        
        // Security
        S21UserProfileService profile = getUserProfileService();
        List<String> functionIds = profile.getFunctionCodeListForBizAppId(BUSINESS_ID);
        if (functionIds.contains(AUTH_READWRITE)) {
            scrnMsg.xxFuncId.setValue(AUTH_READWRITE);
        } else {
            scrnMsg.xxFuncId.setValue(AUTH_READONLY);
        }
        scrnMsg.xxFuncId.setValue(AUTH_READWRITE);

        
        
        NFCL3170CMsg bizMsg = new NFCL3170CMsg();
        bizMsg.setBusinessID("NFCL3170");
        bizMsg.setFunctionCode("20");

        bizMsg.xxBizAppId.setValue("NFCL3170");
        Object[] params = (Object[]) getArgForSubScreen();
        if (params != null && params.length > 0) {
            if (params.length == 1) {
                EZDBBigDecimalItem param01 = (EZDBBigDecimalItem) params[0];
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsBankAcctPk_H1, param01);
                bizMsg.xxBizAppId.setValue("NFCL31XX");
            // START 2023/02/06 R.Takau [QC#55645, ADD]
            } else if(params.length == 3) {
                // START 2023/03/10 S.Nakatani [QC#55645,ADD]
                if (ZYPCommonFunc.hasValue(params[1].toString())) {
                // END 2023/03/10 S.Nakatani [QC#55645,ADD]
                    EZDBStringItem param02 = (EZDBStringItem) params[1];
                    ZYPEZDItemValueSetter.setValue(scrnMsg.billToCustCd, param02);
                // START 2023/03/10 S.Nakatani [QC#55645,ADD]
                }
                // END 2023/03/10 S.Nakatani [QC#55645,ADD]
            // END 2023/02/06 R.Takau [QC#55645, ADD]
            }
        }
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3170BMsg scrnMsg = (NFCL3170BMsg) bMsg;
        NFCL3170CMsg bizMsg  = (NFCL3170CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        
        if(scrnMsg.xxFuncId.getValue().equals(AUTH_READONLY)) {
            NFCL3170CommonLogic.initialize_ReadOnly(this, scrnMsg);
        } else {
            if(!ZYPCommonFunc.hasValue(scrnMsg.xxBizAppId.getValue())) {
                NFCL3170CommonLogic.initialize_New(this, scrnMsg);
            } else {
                if(ZYPCommonFunc.hasValue(scrnMsg.dsBankAcctPk_H1.getValue())) {
                    NFCL3170CommonLogic.initialize_Update(this, scrnMsg);
                } else {
                    NFCL3170CommonLogic.initialize_New(this, scrnMsg);
                }
            }
            scrnMsg.setFocusItem(scrnMsg.dsBankAcctNm_H1);
        }
    }
    
    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NFCL3170BMsg scrnMsg = (NFCL3170BMsg) bMsg;

        scrnMsg.dsBankAcctNm_H1.setNameForMessage("Bank Name");
        scrnMsg.dsBankBrNm_H1.setNameForMessage("Branch Name");
        scrnMsg.dsBankAcctNum_H1.setNameForMessage("Accounting Number");
        scrnMsg.bankRteNum_H1.setNameForMessage("Routing Number");
        scrnMsg.effFromDt_H1.setNameForMessage("Effective Start Date");
        scrnMsg.effThruDt_H1.setNameForMessage("Effective End Date");

        scrnMsg.firstLineAddr_H1.setNameForMessage("First Line Address");
        scrnMsg.scdLineAddr_H1.setNameForMessage("Second Line Address");
        scrnMsg.ctyAddr_H1.setNameForMessage("City Address");
        scrnMsg.cntyNm_H1.setNameForMessage("County Name");
        scrnMsg.stCd_H1.setNameForMessage("State");
        scrnMsg.postCd_H1.setNameForMessage("Postal Code");

        
        scrnMsg.dsAcctNum_H1.setNameForMessage("Customer Number");
        scrnMsg.dsAcctNm_H1.setNameForMessage("Customer Name");
        scrnMsg.locNum_H1.setNameForMessage("Site Number");

    }
}
