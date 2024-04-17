/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0100;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0100.NFDL0100CMsg;
import business.servlet.NFDL0100.common.NFDL0100CommonLogic;
import business.servlet.NFDL0100.constant.NFDL0100Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/** 
 *<pre>
 * Print Invoice
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/14   CSAI            K.Lee           Create          N/A
 * 2016/08/01   Hitachi         K.Kojima        Update          QC#11570
 * 2017/10/05   Fujitsu         H.Sugawara      Update          QC#19922
 * 2018/07/10   Hitachi         J.Kim           Update          QC#26980
 * 2022/08/03   CITS            D.Mamaril       Update          QC#60294
 * 2023/03/07   Hitachi         S.Fujita        Update          QC#61169
 *</pre>
 */
public class NFDL0100_INIT extends S21INITCommonHandler implements NFDL0100Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_APPLICATION_ID);
    }


    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0100BMsg scrnMsg = (NFDL0100BMsg) bMsg;
        NFDL0100CMsg bizMsg = new NFDL0100CMsg();

        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null && params.length > 0) {

            if (params.length == 1) {
                EZDBStringItem param01 = (EZDBStringItem) params[0];
                if (!ZYPCommonFunc.hasValue(param01)) {
                    scrnMsg.setMessageInfo("NFDM0001E", new String[]{"Customer Account Number"});
                    scrnMsg.putErrorScreen();
                } else {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.billToCustAcctCd_H, param01);
                }
            }else if (params.length == 2) {
                EZDBStringItem param01 = (EZDBStringItem) params[0];
                EZDBStringItem param02 = (EZDBStringItem) params[1];
                if (!ZYPCommonFunc.hasValue(param01)) {
                    scrnMsg.setMessageInfo("NFDM0001E", new String[]{"Customer Account Number"});
                    scrnMsg.putErrorScreen();
                } else {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.billToCustAcctCd_H, param01);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.billToCustCd_H, param02);
                }
            } else {
                scrnMsg.setMessageInfo("NFCM0041E");
                scrnMsg.putErrorScreen();
            }
            // START 2018/07/09 J.Kim [QC#26980,ADD]
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_H2, ZYPConstant.FLG_ON_Y);
            // END 2018/07/09 J.Kim [QC#26980,ADD]
        } else {
            scrnMsg.setMessageInfo("NFCM0041E");
            scrnMsg.putErrorScreen();
        }

        bizMsg.setBusinessID("NFDL0100");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0100BMsg scrnMsg = (NFDL0100BMsg) bMsg;
        NFDL0100CMsg bizMsg  = (NFDL0100CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        S21TableColorController tblColor = new S21TableColorController("NFDL0100Scrn00", scrnMsg);
        tblColor.clearRowsBG(TABLE_A, scrnMsg.A);
        NFDL0100CommonLogic.initialize(this, scrnMsg);
        // START 2016/08/01 K.Kojima [QC#11570,DEL]
        // NFDL0100CommonLogic.setTableColor(scrnMsg);
        // END 2016/08/01 K.Kojima [QC#11570,DEL]
        tblColor.setAlternateRowsBG(TABLE_A, scrnMsg.A);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NFDL0100BMsg scrnMsg = (NFDL0100BMsg) bMsg;

        scrnMsg.custIssPoNum_H1.setNameForMessage("PO Number");
        // START 2023/03/07 S.Fujita [QC#61169, MOD]
        // scrnMsg.arTrxNum_H1.setNameForMessage("Inv/Bill Number");
        scrnMsg.arCustRefNum_H1.setNameForMessage("Inv/Bill Number");
        // END 2023/03/07 S.Fujita [QC#61169, MOD]
        // Mod Start 2017/10/05 QC#19922
        //scrnMsg.billToCustCd_H1.setNameForMessage("Bill To Loc");
        scrnMsg.billToCustCd_H1.setNameForMessage("Bill To Code");
        // Mod End 2017/10/05 QC#19922
        scrnMsg.arTrxDt_H1.setNameForMessage("Bill Date From");
        scrnMsg.arTrxDt_H2.setNameForMessage("Bill Date To");
        scrnMsg.xxDplyTrxNumTxt_H1.setNameForMessage("Contract Number");
        scrnMsg.xxChkBox_H1.setNameForMessage("Include Closed");
        scrnMsg.xxChkBox_H2.setNameForMessage("Include Credits");
        scrnMsg.serNum_H1.setNameForMessage("Serial Number ");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).dealOrigGrsAmt_A.setAppFracDigit(2);
            scrnMsg.A.no(i).dealRmngBalGrsAmt_A.setAppFracDigit(2);
            // START 2022/08/03 D.Mamaril [QC#60294, MOD]
            //scrnMsg.A.no(i).pmtLateDaysAot_A.setAppFracDigit(0);
            scrnMsg.A.no(i).daysPastDueAot_A.setAppFracDigit(0);
            // END 2022/08/03 D.Mamaril [QC#60294, MOD]
        }
    }
}
