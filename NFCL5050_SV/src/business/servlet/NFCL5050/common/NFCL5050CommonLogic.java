/**
 * <Pre>
 * 
 * Copyright(c)2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/24/2009   Fujitsu         FXS)Z.Wang      Create          N/A
 * 10/29/2009   Fujitsu         FAP)D.Kato      Update          DefID 0346
 * 11/09/2009   Fujitsu         FAP)N.Aoyama    Update          DefID:1647
 * 11/17/2009   Fujitsu         FAP)K.Sakano    Update          DefID:1740
 * 11/25/2009   Fujitsu         FAP)D.Kato      Update          DefID 2024
 * 01/25/2010   Fujitsu         FAP)D.Kato      Update
 * 02/04/2010   Fujitsu         FAP)D.Kato      Update            
 * 03/22/2010   Fujitsu         FAP)K.Sakano    Update          DefID 5048
 * 05/14/2010   Fujitsu         FAP)N.Aoyama    Update          DefID:6321
 * 05/25/2010   Fujitsu         K.Kimura        Update          DefID:6738 No:045
 * 07/13/2010   Fujitsu         I.Kondo         Update          DefID 7731 No.198
 * 08/06/2010   Fujitsu         I.Kondo         Update          Merge.
 * 08/10/2010   Fujitsu         I.Kondo         Update          For EXPT_FLG.
 * 10/05/2015   Fujitsu         T.Tanaka        Update          CAS NA
 * 04/18/2016   Fujitsu         S.Fujita        Update          QC#6462
 * 2018/07/20   Fujitsu         Y.Matsui        Update          QC#26985
 * 2024/04/15   Hitachi         TZ.Win          Update          QC#63750
 *</pre>
 */
package business.servlet.NFCL5050.common;

import java.math.BigDecimal;

import parts.common.EZDBBigDecimalItemArray;
import parts.common.EZDBDateItem;
import parts.common.EZDBStringItem;
import parts.common.EZDBStringItemArray;
import parts.common.EZDDebugOutput;
import parts.common.EZDGUIAttribute;
import parts.servletcommon.EZDCommonHandler;
import business.blap.NFCL5050.NFCL5050CMsg;
import business.servlet.NFCL5050.NFCL5050BMsg;
import business.servlet.NFCL5050.NFCL5050Bean;
import business.servlet.NFCL5050.constant.NFCL5050Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;

/**
 * NFCL5050CommonLogic.
 */
public class NFCL5050CommonLogic implements NFCL5050Constant {

    /**
     * @param scrnMsg NFCL5050BMsg
     * @return bizMsg NFCL5050CMsg
     */
    public static NFCL5050CMsg setRequestData_NFCL5050_INIT(NFCL5050BMsg scrnMsg) {

        NFCL5050CMsg bizMsg;

        bizMsg = new NFCL5050CMsg();
        bizMsg.setBusinessID("NFCL5050");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @param params Object[]
     * @param scrnMsg NFCL5050BMsg
     */
    public static void otherBusConnectFrom_NFCL5050_INIT(Object[] params, NFCL5050BMsg scrnMsg) {

        scrnMsg.payerCustCd.setValue(((EZDBStringItem) params[PARAMS.NUM_0.getValue()]).getValue());
        scrnMsg.dealCcyCd.setValue(((EZDBStringItem) params[PARAMS.NUM_1.getValue()]).getValue());
        scrnMsg.glDt.setValue(((EZDBDateItem) params[PARAMS.NUM_10.getValue()]).getValue());
        scrnMsg.arRcptTrxTpCd_H1.setValue(((EZDBStringItem) params[PARAMS.NUM_11.getValue()]).getValue());
        scrnMsg.exptFlg_H1.setValue(((String) params[PARAMS.NUM_12.getValue()]));
    }

    /**
     * @param scrnMsg NFCL5050BMsg
     */
    public static void transMsgCheck(NFCL5050BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.arTrxNum);
        scrnMsg.addCheckItem(scrnMsg.arTrxNum_H1);
        scrnMsg.addCheckItem(scrnMsg.soNum);
        scrnMsg.addCheckItem(scrnMsg.grpInvNum);
        scrnMsg.addCheckItem(scrnMsg.shipDt);
        scrnMsg.addCheckItem(scrnMsg.shipDt_H1);
        scrnMsg.addCheckItem(scrnMsg.arTrxTpCd_P1);
        scrnMsg.addCheckItem(scrnMsg.cpoOrdNum);
        scrnMsg.addCheckItem(scrnMsg.crCardOrdNum);
        scrnMsg.addCheckItem(scrnMsg.arTrxDt);
        scrnMsg.addCheckItem(scrnMsg.arTrxDt_H1);
        scrnMsg.addCheckItem(scrnMsg.custIssPoNum);
        scrnMsg.addCheckItem(scrnMsg.invDueDt);
        scrnMsg.addCheckItem(scrnMsg.invDueDt_H1);
        scrnMsg.addCheckItem(scrnMsg.xxInpAmtNum);
        scrnMsg.addCheckItem(scrnMsg.xxInpAmtNum_H1);
        scrnMsg.addCheckItem(scrnMsg.dealOrigGrsAmt_H2);
        scrnMsg.addCheckItem(scrnMsg.payerCustCd);
        scrnMsg.addCheckItem(scrnMsg.locNm);
        scrnMsg.addCheckItem(scrnMsg.dealCcyCd);
        scrnMsg.addCheckItem(scrnMsg.arCashApplyStsCd);
        scrnMsg.addCheckItem(scrnMsg.glblCmpyCd);
        // scrnMsg.addCheckItem(scrnMsg.xx);
        scrnMsg.addCheckItem(scrnMsg.xxPageShowFromNum);
        scrnMsg.addCheckItem(scrnMsg.xxPageShowToNum);
        scrnMsg.addCheckItem(scrnMsg.xxPageShowOfNum);
        scrnMsg.A.setCheckParam(new String[] {"xxChkBox_A1", "arTrxNum_A1", "grpInvNum_A1", "arTrxTpCd_A1", "arTrxTpNm", "arTrxDt_A1", "invDueDt_A1", "shipDt_A1", "arCustRefNum_A1", "crCardOrdNum_A1", "cpoOrdNum_A1", "custIssPoNum_A1",
                "dealOrigGrsAmt_A1", "dealApplyGrsAmt", "dealRmngBalGrsAmt", "dealNetSlsAmt", "dealFrtAmt", "dealTaxAmt", "dealApplyCashDiscAmt", "dealApplyCrAmt", "dealApplyAdjAmt", "arTrxBalPk", "arTrxNum_BK", "arTrxTpCd_BK" }, 1);
        scrnMsg.addCheckItem(scrnMsg.A);

    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL5050BMsg
     */
    public static void initialize(EZDCommonHandler scrnAppli, NFCL5050BMsg scrnMsg) {

        scrnMsg.setInputProtected(false);
        scrnMsg.locNm.setInputProtected(true);
        scrnMsg.dsAcctNm.setInputProtected(true);
        scrnAppli.setButtonProperties("SearchInvoice", "SearchInvoice", "Search", 1, null);
        scrnAppli.setButtonProperties("SelectInvoice", "SelectInvoice", "Select", 1, null);
        scrnAppli.setButtonProperties("Check_All", "Check_All", "CheckAll", 1, null);
        scrnAppli.setButtonProperties("Un_Check_All", "Un_Check_All", "UncheckAll", 1, null);
        scrnAppli.setButtonProperties("btn8", "CMN_Clear", "Clear", 1, null);
        scrnAppli.setButtonProperties("btn10", "CMN_Close", "Close", 1, null);

        // START 2016/04/18 S.Fujita [QC#6462,MOD]
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).dsAcctNm_A1.setInputProtected(true);
        }
        // END 2016/04/18 S.Fujita [QC#6462,MOD]
    }

    /**
     * @param scrnMsg NFCL5050BMsg
     */
    public static void setScrnItemValue_NFCL5050_INIT(NFCL5050BMsg scrnMsg) {

        scrnMsg.arTrxNum.clear();
        scrnMsg.arTrxNum_H1.clear();
        scrnMsg.arCustRefNum.clear();
        scrnMsg.arCustRefNum_H1.clear();
        scrnMsg.arTrxDt.clear();
        scrnMsg.arTrxDt_H1.clear();
        scrnMsg.xxChkBox_H1.clear();
        scrnMsg.xxChkBox_H2.clear();
        scrnMsg.xxChkBox_H3.clear();
        scrnMsg.xxChkBox_H4.clear();
        scrnMsg.xxChkBox_H5.clear();
        scrnMsg.grpInvNum.clear();
        scrnMsg.soNum.clear();
        scrnMsg.custIssPoNum.clear();
        scrnMsg.cpoOrdNum.clear();
        scrnMsg.crCardOrdNum.clear();
        scrnMsg.xxInpAmtNum.clear();
        scrnMsg.xxInpAmtNum_H1.clear();
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();
        scrnMsg.xxPageShowCurNum.clear();
        scrnMsg.xxPageShowFromNum_H1.clear();
        scrnMsg.xxPageShowTotNum.clear();
        scrnMsg.xxBalApplyGrsAmt.clear();
        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);
    }

    public static void setScrnItemValue_NFCL5050_INIT_W_SEARCH(NFCL5050BMsg scrnMsg) {

        scrnMsg.arTrxNum.clear();
        scrnMsg.arTrxNum_H1.clear();
        scrnMsg.arCustRefNum.clear();
        scrnMsg.arCustRefNum_H1.clear();
        scrnMsg.arTrxDt.clear();
        scrnMsg.arTrxDt_H1.clear();
        scrnMsg.xxChkBox_H1.clear();
        scrnMsg.xxChkBox_H2.clear();
        scrnMsg.xxChkBox_H3.clear();
        scrnMsg.xxChkBox_H4.clear();
        scrnMsg.xxChkBox_H5.clear();
        scrnMsg.grpInvNum.clear();
        scrnMsg.soNum.clear();
        scrnMsg.custIssPoNum.clear();
        scrnMsg.cpoOrdNum.clear();
        scrnMsg.crCardOrdNum.clear();
        scrnMsg.xxInpAmtNum.clear();
        scrnMsg.xxInpAmtNum_H1.clear();
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();
        scrnMsg.xxPageShowCurNum.clear();
        scrnMsg.xxPageShowFromNum_H1.clear();
        scrnMsg.xxPageShowTotNum.clear();
        scrnMsg.xxBalApplyGrsAmt.clear();
//        scrnMsg.A.clear();
//        scrnMsg.A.setValidCount(0);
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL5050BMsg
     */
    public static void busBtnControl_NFCL5050_INIT(EZDCommonHandler scrnAppli, NFCL5050BMsg scrnMsg) {

        scrnAppli.setButtonEnabled("SelectInvoice", false);
        scrnAppli.setButtonEnabled("Check_All", false);
        scrnAppli.setButtonEnabled("Un_Check_All", false);

    }

    public static void busBtnControl_NFCL5050_INIT_SelectInvoice(EZDCommonHandler scrnAppli, NFCL5050BMsg scrnMsg) {

        if(scrnMsg.A.getValidCount()>0) {
            scrnAppli.setButtonEnabled("SelectInvoice", true);
        }
    }

    /**
     * @param scrnMsg NFCL5050BMsg
     */
    public static void scrnItemControl_NFCL5050_INIT(NFCL5050BMsg scrnMsg) {

        scrnMsg.setFocusItem(scrnMsg.arTrxNum);
        // set AR_TRX_TP_CD
        scrnMsg.xxChkBox_H1.setValue(ZYPConstant.CHKBOX_ON_Y); // INV
        scrnMsg.xxChkBox_H2.setValue(ZYPConstant.CHKBOX_ON_Y); // CRM
        scrnMsg.xxChkBox_H3.setValue(ZYPConstant.CHKBOX_ON_Y); // ACC
        scrnMsg.xxChkBox_H4.setValue(ZYPConstant.CHKBOX_ON_Y); // DED Late Fee
        // START 2024/04/15 TZ.Win [QC#63750, MOD]
        //scrnMsg.xxChkBox_H5.setValue(ZYPConstant.FLG_OFF_N); // Related Customer
        scrnMsg.xxChkBox_H5.setValue(ZYPConstant.CHKBOX_ON_Y); // Related Customer
        // END 2024/04/15 TZ.Win [QC#63750, MOD]
    }

    /**
     * @param scrnMsg NFCL5050BMsg
     */
    public static void checkInput_NFCL5050Scrn00_SearchInvoice(NFCL5050BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.arTrxNum);
        scrnMsg.addCheckItem(scrnMsg.arTrxNum_H1);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_H1);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_H2);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_H3);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_H4);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_H5);
        // START 2018/07/20 Y.Matsui [QC#26985,ADD]
        scrnMsg.addCheckItem(scrnMsg.xxTrxNumSrchTxt);
        // END   2018/07/20 Y.Matsui [QC#26985,ADD]
        scrnMsg.addCheckItem(scrnMsg.soNum);
        scrnMsg.addCheckItem(scrnMsg.arTrxDt);
        scrnMsg.addCheckItem(scrnMsg.arTrxDt_H1);
        scrnMsg.addCheckItem(scrnMsg.custIssPoNum);
        scrnMsg.addCheckItem(scrnMsg.cpoOrdNum);
        scrnMsg.addCheckItem(scrnMsg.crCardOrdNum);
        scrnMsg.addCheckItem(scrnMsg.xxInpAmtNum);
        scrnMsg.addCheckItem(scrnMsg.xxInpAmtNum_H1);
        scrnMsg.addCheckItem(scrnMsg.grpInvNum);
    }

    /**
     * @param scrnMsg NFCL5050BMsg
     * @return bizMsg NFCL5050CMsg
     */
    public static NFCL5050CMsg setRequestData_NFCL5050Scrn00_SearchInvoice(NFCL5050BMsg scrnMsg) {

        NFCL5050CMsg bizMsg;

        bizMsg = new NFCL5050CMsg();
        bizMsg.setBusinessID("NFCL5050");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @param scrnMsg NFCL5050BMsg
     */
    public static void scrnItemControl_NFCL5050Scrn00_SearchInvoice(NFCL5050BMsg scrnMsg) {

        if (SUMMARY_STATUS_Y.equals(scrnMsg.xxRsltStsCd.getValue())) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).xxChkBox_A1.setValue(ZYPConstant.CHKBOX_ON_Y);
                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(true);
            }
        } else {
            // do nothing
            EZDDebugOutput.println(1, "do nothing.", NFCL5050CommonLogic.class);
        }
        scrnMsg.setMessageInfo("NZZM0002I", new String[] {"Search" });
    }

    /**
     * @param argsParams Object[]
     * @param arTrxNumList String[]
     * @param arTrxTpCdList String[]
     * @param arTrxBalPkList BigDecimal[]
     * @param scrnMsg NFCL5050BMsg
     * @return params Object[]
     */
    public static Object[] otherBusConnectToReturn_NFCL5050Scrn00_SelectInvoice(Object[] argsParams, String[] arTrxNumList, String[] arTrxTpCdList, BigDecimal[] arTrxBalPkList, NFCL5050BMsg scrnMsg) {

        Object[] params = argsParams;

        params[PARAMS.NUM_0.getValue()] = argsParams[PARAMS.NUM_0.getValue()];
        params[PARAMS.NUM_1.getValue()] = argsParams[PARAMS.NUM_1.getValue()];

        if (params[PARAMS.NUM_2.getValue()] instanceof EZDBStringItemArray) {

            for (int i = 0; i < arTrxNumList.length; i++) {
                if (ZYPCommonFunc.hasValue(arTrxNumList[i])) {
                    ((EZDBStringItemArray) params[PARAMS.NUM_2.getValue()]).no(i).setValue(arTrxNumList[i]);
                } else {
                    break;
                }
            }

        }

        if (params[PARAMS.NUM_3.getValue()] instanceof EZDBStringItemArray) {

            for (int i = 0; i < arTrxTpCdList.length; i++) {
                if (ZYPCommonFunc.hasValue(arTrxTpCdList[i])) {
                    ((EZDBStringItemArray) params[PARAMS.NUM_3.getValue()]).no(i).setValue(arTrxTpCdList[i]);
                } else {
                    break;
                }
            }

        }

        if (params[PARAMS.NUM_4.getValue()] instanceof EZDBBigDecimalItemArray) {

            for (int i = 0; i < arTrxBalPkList.length; i++) {
                if (ZYPCommonFunc.hasValue(arTrxBalPkList[i])) {
                    ((EZDBBigDecimalItemArray) params[PARAMS.NUM_4.getValue()]).no(i).setValue((arTrxBalPkList[i]));
                } else {
                    break;
                }

            }

        }

        params[PARAMS.NUM_2.getValue()] = arTrxNumList;
        params[PARAMS.NUM_3.getValue()] = arTrxTpCdList;
        params[PARAMS.NUM_4.getValue()] = arTrxBalPkList;
        // params[9] = scrnMsg.grpInvNum_BK;
        ((EZDBStringItem) params[PARAMS.NUM_9.getValue()]).setValue(scrnMsg.grpInvNum_BK.getValue());

        return params;
    }

    /**
     * @param scrnMsg NFCL5050BMsg
     * @return bizMsg NFCL5050CMsg
     */
    public static NFCL5050CMsg setRequestData_NFCL5050Scrn00_CMN_Clear(NFCL5050BMsg scrnMsg) {

        NFCL5050CMsg bizMsg;

        bizMsg = new NFCL5050CMsg();
        bizMsg.setBusinessID("NFCL5050");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @param scrnMsg NFCL5050BMsg
     */
    public static void setScrnItemValue_NFCL5050Scrn00_CMN_Clear(NFCL5050BMsg scrnMsg) {
        scrnMsg.arTrxNum.clear();
        scrnMsg.arTrxNum_H1.clear();
        // START 2018/07/20 Y.Matsui [QC#26985,ADD]
        scrnMsg.xxTrxNumSrchTxt.clear();
        scrnMsg.payerCustCd_H1.clear();
        // END   2018/07/20 Y.Matsui [QC#26985,ADD]
        scrnMsg.arTrxDt.clear();
        scrnMsg.arTrxDt_H1.clear();
        scrnMsg.xxChkBox_H1.clear();
        scrnMsg.xxChkBox_H2.clear();
        scrnMsg.xxChkBox_H3.clear();
        scrnMsg.xxChkBox_H4.clear();
        scrnMsg.xxChkBox_H5.clear();
        scrnMsg.grpInvNum.clear();
        scrnMsg.soNum.clear();
        scrnMsg.custIssPoNum.clear();
        scrnMsg.cpoOrdNum.clear();
        scrnMsg.crCardOrdNum.clear();
        scrnMsg.xxInpAmtNum.clear();
        scrnMsg.xxInpAmtNum_H1.clear();
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();
        scrnMsg.xxPageShowCurNum.clear();
        scrnMsg.xxPageShowFromNum_H1.clear();
        scrnMsg.xxPageShowTotNum.clear();
        scrnMsg.xxBalApplyGrsAmt.clear();
        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL5050BMsg
     */
    public static void busBtnControl_NFCL5050Scrn00_CMN_Clear(EZDCommonHandler scrnAppli, NFCL5050BMsg scrnMsg) {

        scrnAppli.setButtonEnabled("SelectInvoice", false);
        scrnAppli.setButtonEnabled("Check_All", false);
        scrnAppli.setButtonEnabled("Un_Check_All", false);
    }

    /**
     * @param scrnMsg NFCL5050BMsg
     */
    public static void scrnItemControl_NFCL5050Scrn00_CMN_Clear(NFCL5050BMsg scrnMsg) {

        scrnMsg.setFocusItem(scrnMsg.arTrxNum);
        scrnMsg.xxChkBox_H1.setValue(ZYPConstant.CHKBOX_ON_Y);
    }

    /**
     * @param scrnMsg NFCL5050BMsg
     * @return bizMsg NFCL5050CMsg
     */
    public static NFCL5050CMsg setRequestData_NFCL5050Scrn00_PageNext(NFCL5050BMsg scrnMsg) {

        NFCL5050CMsg bizMsg;

        bizMsg = new NFCL5050CMsg();
        bizMsg.setBusinessID("NFCL5050");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @param scrnMsg NFCL5050BMsg
     * @return bizMsg NFCL5050CMsg
     */
    public static NFCL5050CMsg setRequestData_NFCL5050Scrn00_PagePrev(NFCL5050BMsg scrnMsg) {

        NFCL5050CMsg bizMsg;

        bizMsg = new NFCL5050CMsg();
        bizMsg.setBusinessID("NFCL5050");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @param scrnMsg NFCL5050BMsg
     * @return bizMsg NFCL5050CMsg
     */
    public static NFCL5050CMsg setRequestData_NFCL5050Scrn00_PageJump(NFCL5050BMsg scrnMsg) {

        NFCL5050CMsg bizMsg;

        bizMsg = new NFCL5050CMsg();
        bizMsg.setBusinessID("NFCL5050");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL5050BMsg
     */
    public static void controlTableBegin_NFCL5050Scrn00_A(EZDCommonHandler scrnAppli, NFCL5050BMsg scrnMsg) {

        // set values to items of pagenation for next page pagenation
        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL5050BMsg
     */
    public static void controlTablePrev_NFCL5050Scrn00_A(EZDCommonHandler scrnAppli, NFCL5050BMsg scrnMsg) {

        scrnMsg.xxPageShowFromNum.setValue(scrnMsg.xxPageShowFromNum.getValueInt() - scrnMsg.A.length() - 1);
    }

    /**
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NFCL5050BMsg
     */
    public static void controlTableNext_NFCL5050Scrn00_A(EZDCommonHandler scrnAppli, NFCL5050BMsg scrnMsg) {

        scrnMsg.xxPageShowFromNum.setValue(scrnMsg.xxPageShowToNum.getValueInt());
    }

    /**
     * @param scrnMsg
     * @param scrnMsg NFCL5050BMsg
     * @return bizMsg NFCL5050CMsg
     */
    public static NFCL5050CMsg setRequestData_NFCL5050Scrn00_SelectInvoice(NFCL5050BMsg scrnMsg) {
        NFCL5050CMsg bizMsg;

        bizMsg = new NFCL5050CMsg();
        bizMsg.setBusinessID("NFCL5050");
        bizMsg.setFunctionCode("20");

        return bizMsg;
    }

    /**
     * @param scrnMsg NFCL5050BMsg
     */
    public static void setRowBg(NFCL5050BMsg scrnMsg) {

        scrnMsg.clearAllGUIAttribute("NFCL5050Scrn00");

        EZDGUIAttribute custRefNum = null;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).arCustRefNum_A1.setInputProtected(true);
            custRefNum = new EZDGUIAttribute("NFCL5050Scrn00", NFCL5050Bean.arCustRefNum_A1 + "#" + i);

            if ((i + 1) % 2 == 0) {
                custRefNum.setStyleAttribute("background-color", EZDGUIAttribute.pEvenNumberBGcolor);
            } else {
                custRefNum.setStyleAttribute("background-color", EZDGUIAttribute.pOddNumberBGcolor);
            }

            scrnMsg.addGUIAttribute(custRefNum);
        }
    }

    /**
     * @param scrnMsg NFCL5050BMsg
     * @return trx tp check boxs are All Null:true/not all null:false.
     */
    public static boolean isAllCheckBoxNull(NFCL5050BMsg scrnMsg) {
        if (ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_H1)) {
            return false;
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_H2)) {
            return false;
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_H3)) {
            return false;
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_H4)) {
            return false;
        }
        return true;
    }
}
