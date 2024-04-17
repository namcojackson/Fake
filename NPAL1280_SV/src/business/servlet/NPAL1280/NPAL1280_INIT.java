/**
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 */
package business.servlet.NPAL1280;

import static business.servlet.NPAL1280.constant.NPAL1280Constant.ASL_MDSE_CD_D;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.BUSINESS_APPL_ID;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.BUSINESS_UNIT;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.CARR_ACCT_NUM;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.CARR_CD;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.CHARGE_ACCOUNT_D;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.CHECK_BOX_D;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.CPO_ORD_NUM;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.DATE_NEEDED;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.DEST_SWH_CD;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.DEST_WH_CD;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.FRT_COND_CD;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.FULL_PSN_NM;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.LINE_TYPE_D;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.MDSE_CD_D;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.ORDER_QTY_D;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.PO_SCHD_REL_DT_D;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.PRCH_REQ_CMNT_TXT;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.PRCH_REQ_LINE_CMNT_TXT_D;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.REL_RQST_TO_PO_ORD_NUM_D;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.REQUISITION_NUM;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.SHIP_TO_ADDR_LINE;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.SHIP_TO_CITY_CODE;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.SHIP_TO_CTRY_CODE;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.SHIP_TO_CUST_CODE;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.SHIP_TO_NAME;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.SHIP_TO_POSTAL_CODE;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.SHPG_SVC_LVL_CD;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.SITE_D;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.SPCL_INSTN_CMNT_TXT;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.SUPPLIER_D;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.TAB_DETAIL;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.UNIT_PRICE_D;
import static business.servlet.NPAL1280.constant.NPAL1280Constant.VENDOR_SHIP_DATE;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1280.NPAL1280CMsg;
import business.servlet.NPAL1280.common.NPAL1280CommonLogic;
import business.servlet.NPAL1280.constant.NPAL1280Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Business ID : NPAL1280 PO Requisition Entry
 * Function Name : INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/28/2016   CITS            K.Ogino          Create          N/A
 * 02/29/2016   CITS            K.Ogino          Update          QC#4633
 * 10/06/2017   CITS            T.Tokutomi       Update          QC#21209
 * 08/20/2018   CITS            K.Ogino          Update          QC#27846
 * 01/17/2019   CITS            T.Tokutomi       Update          QC#28709
 * 02/03/2023   Hitachi         T.Kuroda         Update          QC#60966
 *</pre>
 */
public class NPAL1280_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), NPAL1280Constant.BUSINESS_APPL_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1280BMsg scrnMsg = (NPAL1280BMsg) bMsg;

        setParameters(scrnMsg);

        // set values to items of pagenation for next page pagenation
        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();

        NPAL1280CMsg bizMsg = new NPAL1280CMsg();
        bizMsg.setBusinessID(BUSINESS_APPL_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1280BMsg scrnMsg = (NPAL1280BMsg) bMsg;
        NPAL1280CMsg bizMsg = (NPAL1280CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        String glblCmpyCd = getGlobalCompanyCode();
        String loginFullName = getContextUserInfo().getFullName();
        String loginUserId = getContextUserInfo().getUserId();
        String salesDate = ZYPDateUtil.getSalesDate(glblCmpyCd);

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_APPL_ID);
        if (ZYPCommonFunc.hasValue(scrnMsg.prchReqNum)) {

            NPAL1280CommonLogic.initializeCommandButtons(this);

            if (!(ZYPConstant.FLG_ON_Y.equals(scrnMsg.openStsFlg_H.getValue()))) {

                NPAL1280CommonLogic.controlItemsOnSearchNoOpen(this, scrnMsg, glblCmpyCd, funcList);

            } else if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.prchReqSavedFlg.getValue())) {

                NPAL1280CommonLogic.controlItemsOnSearchOpenY(this, scrnMsg, glblCmpyCd, funcList);

            } else {

                NPAL1280CommonLogic.controlItemsOnSearchOpenN(this, scrnMsg, glblCmpyCd, loginFullName, loginUserId, funcList, false);
            }

        } else {
            NPAL1280CommonLogic.controlItemsOnInit(this, scrnMsg, glblCmpyCd, loginFullName, loginUserId, funcList, salesDate);
        }
        scrnMsg.setFocusItem(scrnMsg.prchReqNum);
        scrnMsg.xxDplyTab.setValue(TAB_DETAIL);
    }

    @Override
    protected void setNameForMessage(EZDBMsg arg0) {

        NPAL1280BMsg scrnMsg = (NPAL1280BMsg) arg0;

        // Header
        scrnMsg.prchReqNum.setNameForMessage(REQUISITION_NUM);
        scrnMsg.rqstRcvDt.setNameForMessage(DATE_NEEDED);
        // START 2023/02/03 T.Kuroda [QC#60966, ADD]
        scrnMsg.rqstShipDt.setNameForMessage(VENDOR_SHIP_DATE);
        // END   2023/02/03 T.Kuroda [QC#60966, ADD]
        scrnMsg.fullPsnNm.setNameForMessage(FULL_PSN_NM);
        scrnMsg.prchReqCmntTxt.setNameForMessage(PRCH_REQ_CMNT_TXT);
        
        scrnMsg.prntVndNm.setNameForMessage(SUPPLIER_D);
        scrnMsg.vndCd.setNameForMessage(SITE_D);
        scrnMsg.locNm.setNameForMessage(SITE_D);
        scrnMsg.destRtlWhCd.setNameForMessage(DEST_WH_CD);
        scrnMsg.destRtlSwhCd.setNameForMessage(DEST_SWH_CD);
        scrnMsg.cpoOrdNum.setNameForMessage(CPO_ORD_NUM);
        // QC#27846
        scrnMsg.dsOrdPosnNum.setNameForMessage(CPO_ORD_NUM);
        scrnMsg.prchGrpCd_SL.setNameForMessage(BUSINESS_UNIT);

        // Additional Header
        scrnMsg.frtCondCd_SL.setNameForMessage(FRT_COND_CD);
        scrnMsg.shpgSvcLvlCd_SL.setNameForMessage(SHPG_SVC_LVL_CD);
        scrnMsg.carrCd_HF.setNameForMessage(CARR_CD);
        scrnMsg.carrAcctNum_HF.setNameForMessage(CARR_ACCT_NUM);
        // QC#28709 Add.
        scrnMsg.shipToCustCd.setNameForMessage(SHIP_TO_CUST_CODE);
        scrnMsg.xxLocNm.setNameForMessage(SHIP_TO_NAME);
        scrnMsg.shipToPostCd_HS.setNameForMessage(SHIP_TO_POSTAL_CODE);
        scrnMsg.xxAllLineAddr_HS.setNameForMessage(SHIP_TO_ADDR_LINE);
        scrnMsg.shipToCtyAddr_HS.setNameForMessage(SHIP_TO_CITY_CODE);
        scrnMsg.shipToCtryCd_HS.setNameForMessage(SHIP_TO_CTRY_CODE);

        // Detail
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxChkBox_A1.setNameForMessage(CHECK_BOX_D);
            scrnMsg.A.no(i).prchReqLineTpCd_A1.setNameForMessage(LINE_TYPE_D);
            scrnMsg.A.no(i).mdseCd_A1.setNameForMessage(MDSE_CD_D);
            scrnMsg.A.no(i).aslMdseCd_A1.setNameForMessage(ASL_MDSE_CD_D);
            scrnMsg.A.no(i).prchReqDispQty_A1.setNameForMessage(ORDER_QTY_D);
            scrnMsg.A.no(i).prntVndNm_A1.setNameForMessage(SUPPLIER_D);
            scrnMsg.A.no(i).locNm_A1.setNameForMessage(SITE_D);
            scrnMsg.A.no(i).entDealNetUnitPrcAmt_A1.setNameForMessage(UNIT_PRICE_D);
            scrnMsg.A.no(i).rqstRcvDt_A1.setNameForMessage(DATE_NEEDED);
            // START 2023/02/03 T.Kuroda [QC#60966, ADD]
            scrnMsg.A.no(i).rqstShipDt_A1.setNameForMessage(VENDOR_SHIP_DATE);
            // END   2023/02/03 T.Kuroda [QC#60966, ADD]
            scrnMsg.A.no(i).xxScrItem130Txt_A1.setNameForMessage(CHARGE_ACCOUNT_D);
            scrnMsg.A.no(i).prchReqLineCmntTxt_A1.setNameForMessage(PRCH_REQ_LINE_CMNT_TXT_D);
            scrnMsg.A.no(i).relRqstToPoOrdNum_A1.setNameForMessage(REL_RQST_TO_PO_ORD_NUM_D);
            scrnMsg.A.no(i).poSchdRelDt_A1.setNameForMessage(PO_SCHD_REL_DT_D);
            // QC#21209
            scrnMsg.A.no(i).spclInstnCmntTxt_A1.setNameForMessage(SPCL_INSTN_CMNT_TXT);
        }
    }

    private void setParameters(NPAL1280BMsg scrnMsg) {

        Object[] args = (Object[]) getArgForSubScreen();

        if (args != null && args.length == 1) {

            Object params = (Object) args[0];

            if (params instanceof EZDBStringItem) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.prchReqNum, (EZDBStringItem) params);
                ZYPEZDItemValueSetter.setValue(scrnMsg.prchReqNum_HD, (EZDBStringItem) params);

            } else {
                // String
                ZYPEZDItemValueSetter.setValue(scrnMsg.prchReqNum, (String) params);
                ZYPEZDItemValueSetter.setValue(scrnMsg.prchReqNum_HD, (String) params);
            }
        }
    }
}
