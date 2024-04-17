/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL2020;

import static business.servlet.NLBL2020.constant.NLBL2020Constant.BUSINESS_ID;
import static business.servlet.NLBL2020.constant.NLBL2020Constant.FUNC_SRCH;
import static business.servlet.NLBL2020.constant.NLBL2020Constant.TAB_SO_LIST;

import java.io.Serializable;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL2020.NLBL2020CMsg;
import business.servlet.NLBL2020.common.NLBL2020CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Manage Shipping Orders
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/16   CITS            T.Tokutomi      Create          N/A
 * 2017/02/01   CITS            T.Kikuhara      Update          QC#10621
 *</pre>
 */
public class NLBL2020_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL2020BMsg scrnMsg = (NLBL2020BMsg) bMsg;

        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            // Source Order Number
            EZDBStringItem param00 = (EZDBStringItem) params[0];
            if (ZYPCommonFunc.hasValue(param00)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.trxHdrNum_H1, param00);
            }
            // Shipping Order Number
            EZDBStringItem param01 = (EZDBStringItem) params[1];
            if (ZYPCommonFunc.hasValue(param01)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.soNum_H1, param01);
                // Include Closed Line
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxExstFlg_NO, ZYPConstant.CHKBOX_ON_Y);
            }
            // Retail Warehouse Code
            EZDBStringItem param02 = (EZDBStringItem) params[2];
            if (ZYPCommonFunc.hasValue(param02)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxRtrnInvtyLocSrchTxt_RW, param02);
            }
            // Sub Warehouse Code
            EZDBStringItem param03 = (EZDBStringItem) params[3];
            if (ZYPCommonFunc.hasValue(param03)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxRtrnInvtyLocSrchTxt_SW, param03);
            }
            // Ship to Customer Code
            EZDBStringItem param04 = (EZDBStringItem) params[4];
            if (ZYPCommonFunc.hasValue(param04)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustCd_H1, param04);
            }
            // Carrier Code
            EZDBStringItem param05 = (EZDBStringItem) params[5];
            if (ZYPCommonFunc.hasValue(param05)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.carrCd_H1, param05);
            }
        }

        NLBL2020CMsg bizMsg = new NLBL2020CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL2020BMsg scrnMsg = (NLBL2020BMsg) bMsg;
        NLBL2020CMsg bizMsg = (NLBL2020CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // Set DefaultTab
        scrnMsg.xxDplyTab.setValue(TAB_SO_LIST);

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_ID);
        NLBL2020CommonLogic.ctrlScrnItemProtection(scrnMsg, this, funcList);

        scrnMsg.setFocusItem(scrnMsg.trxHdrNum_H1);
    }

    protected void setNameForMessage(EZDBMsg bMsg) {

        NLBL2020BMsg scrnMsg = (NLBL2020BMsg) bMsg;
        scrnMsg.xxPageShowCurNum_A.setNameForMessage(S21BatchSearchPagenationScrnSupport.getCurrentPageFieldName());
        scrnMsg.xxPageShowCurNum_B.setNameForMessage(S21BatchSearchPagenationScrnSupport.getCurrentPageFieldName());

        // QC#10621 add start
        scrnMsg.trxHdrNum_H1.setNameForMessage("Source Order Number");
        scrnMsg.soNum_H1.setNameForMessage("Shipping Order Number");
        scrnMsg.svcConfigMstrPk_H1.setNameForMessage("Config ID");
        scrnMsg.xxRtrnInvtyLocSrchTxt_RW.setNameForMessage("Source WH");
        scrnMsg.xxRtrnInvtyLocSrchTxt_SW.setNameForMessage("Source SWH");
        scrnMsg.shipToCustCd_H1.setNameForMessage("Ship To Customer");
        scrnMsg.carrCd_H1.setNameForMessage("Carrier");
        scrnMsg.proNum_H1.setNameForMessage("Tracking#");
        scrnMsg.mdseCd_H1.setNameForMessage("Item Number");
        scrnMsg.xxTrxDt_FR.setNameForMessage("Shipping Creation Date From");
        scrnMsg.xxTrxDt_TO.setNameForMessage("Shipping Creation Date To");
        scrnMsg.rddDt_FR.setNameForMessage("Request Delivery Date From");
        scrnMsg.rddDt_TO.setNameForMessage("Request Delivery Date To");
        scrnMsg.xxTrxDt_F3.setNameForMessage("Need By Date From");
        scrnMsg.xxTrxDt_T3.setNameForMessage("Need By Date To");
        scrnMsg.xxDtNm_F1.setNameForMessage("Shipping Creation Time From");
        scrnMsg.xxDtNm_T1.setNameForMessage("Shipping Creation Time To");
        scrnMsg.xxDtNm_F2.setNameForMessage("Request Delivery Time From");
        scrnMsg.xxDtNm_T2.setNameForMessage("Request Delivery Time To");
        scrnMsg.xxDtNm_F3.setNameForMessage("Need By Time From");
        scrnMsg.xxDtNm_T3.setNameForMessage("Need By Time To");
        scrnMsg.srchOptNm_H1.setNameForMessage("Search Option Name");
        // QC#10621 add end

        // SO List
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).carrNm_A1.setNameForMessage("Actual Carrier");
            scrnMsg.A.no(i).totFrtAmt_A1.setNameForMessage("Ship Cost");
            scrnMsg.A.no(i).proNum_A1.setNameForMessage("Tracking#");
        }

        // Apply to Detail Lines
        scrnMsg.carrNm_D1.setNameForMessage("Carrier");
        scrnMsg.totFrtAmt_D1.setNameForMessage("Ship Cost");
        scrnMsg.proNum_D1.setNameForMessage("Tracking#");
    }
}
