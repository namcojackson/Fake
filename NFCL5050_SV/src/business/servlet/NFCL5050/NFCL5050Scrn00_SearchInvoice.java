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
 * 11/16/2009   Fujitsu         FAP)K.Sakano    Update          DefID 1740
 * 11/25/2009   Fujitsu         FAP)D.Kato      Update          DefID 2024
 * 01/25/2010   Fujitsu         FAP)D.Kato      Update          
 * 02/04/2010   Fujitsu         FAP)D.Kato      Update            
 * 03/22/2010   Fujitsu         FAP)K.Sakano    Update          DefID 5048
 * 05/25/2010   Fujitsu         K.Kimura        Update          DefID:6738 No:045
 * 07/13/2010   Fujitsu         I.Kondo         Update          DefID 7731 No.198
 * 08/06/2010   Fujitsu         I.Kondo         Update          Merge.
 * 2018/07/20   Fujitsu         Y.Matsui        Update          QC#26985
 *</pre>
 */
package business.servlet.NFCL5050;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL5050.NFCL5050CMsg;
import business.servlet.NFCL5050.common.NFCL5050CommonLogic;
import business.servlet.NFCL5050.constant.NFCL5050Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * NFCL5050Scrn00_SearchInvoice.
 */
public class NFCL5050Scrn00_SearchInvoice extends S21CommonHandler implements NFCL5050Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL5050BMsg scrnMsg = (NFCL5050BMsg) bMsg;

        scrnMsg.A.clear();
        scrnMsg.A.setValidCount(0);
        this.setButtonProperties("SelectInvoice", "SelectInvoice", "Select", 0, null);
        S21TableColorController tblColor = new S21TableColorController("NFCL5050Scrn00", scrnMsg);
        tblColor.clearRowsBG("A", scrnMsg.A);
//        tblColor.clearRowsBG("A1", scrnMsg.A);
//        tblColor.clearRowsBG("A2", scrnMsg.A);
        scrnMsg.clearAllGUIAttribute("NFCL5050Scrn00");

        NFCL5050CommonLogic.checkInput_NFCL5050Scrn00_SearchInvoice(scrnMsg);

        scrnMsg.xxRsltStsCd.clear();
        scrnMsg.grpInvNum_BK.clear();

        if (ZYPCommonFunc.hasValue(scrnMsg.grpInvNum.getValue())) {
            // mode serch Summary Billing Num
            // serch condition clear
            scrnMsg.arTrxNum.clear();
            scrnMsg.arTrxNum_H1.clear();
            // START 2018/07/20 Y.Matsui [QC#26985,ADD]
            scrnMsg.xxTrxNumSrchTxt.clear();
            // END   2018/07/20 Y.Matsui [QC#26985,ADD]
            scrnMsg.arTrxDt.clear();
            scrnMsg.arTrxDt_H1.clear();
            scrnMsg.soNum.clear();
            scrnMsg.custIssPoNum.clear();
            scrnMsg.cpoOrdNum.clear();
            scrnMsg.crCardOrdNum.clear();
            scrnMsg.xxInpAmtNum.clear();
            scrnMsg.xxInpAmtNum_H1.clear();
            scrnMsg.xxInpAmtNum_H1.clear();

            // set check box
            scrnMsg.xxChkBox_H1.setValue(ZYPConstant.CHKBOX_ON_Y);
            scrnMsg.xxChkBox_H2.setValue(ZYPConstant.CHKBOX_ON_Y);
            scrnMsg.xxChkBox_H3.setValue(ZYPConstant.CHKBOX_ON_Y);
            scrnMsg.xxChkBox_H4.clear();
            scrnMsg.xxChkBox_H5.clear();

            // else
            scrnMsg.grpInvNum_BK.setValue(scrnMsg.grpInvNum.getValue());
            scrnMsg.xxRsltStsCd.setValue(SUMMARY_STATUS_Y);
        } else {
            scrnMsg.xxRsltStsCd.setValue(SUMMARY_STATUS_N);
        }

        boolean errorFlg = true;
        if (ZYPCommonFunc.hasValue(scrnMsg.arTrxNum_H1) && !ZYPCommonFunc.hasValue(scrnMsg.arTrxNum)) {
            scrnMsg.arTrxNum_H1.setErrorInfo(1, "NFCM0024E");
            errorFlg = false;
        }

        if (errorFlg == true && ZYPCommonFunc.hasValue(scrnMsg.arTrxNum) && ZYPCommonFunc.hasValue(scrnMsg.arTrxNum_H1) && scrnMsg.arTrxNum.getValue().compareTo(scrnMsg.arTrxNum_H1.getValue()) > 0) {
            scrnMsg.arTrxNum_H1.setErrorInfo(1, "NFCM0023E", null);
            errorFlg = false;
        }

        if (errorFlg == true && NFCL5050CommonLogic.isAllCheckBoxNull(scrnMsg)) {
            scrnMsg.xxChkBox_H1.setErrorInfo(1, "NFCM0153E", null);
            scrnMsg.xxChkBox_H2.setErrorInfo(1, "NFCM0153E", null);
            scrnMsg.xxChkBox_H3.setErrorInfo(1, "NFCM0153E", null);
            scrnMsg.xxChkBox_H4.setErrorInfo(1, "NFCM0153E", null);
            errorFlg = false;
        }

        // START 2018/07/20 Y.Matsui [QC#26985,DEL]
//        if (ZYPCommonFunc.hasValue(scrnMsg.arCustRefNum_H1) && !ZYPCommonFunc.hasValue(scrnMsg.arCustRefNum)) {
//            scrnMsg.arCustRefNum_H1.setErrorInfo(1, "NFCM0024E");
//            errorFlg = false;
//        }
//
//        if (errorFlg == true && ZYPCommonFunc.hasValue(scrnMsg.arCustRefNum) && ZYPCommonFunc.hasValue(scrnMsg.arCustRefNum_H1) && scrnMsg.arCustRefNum.getValue().compareTo(scrnMsg.arCustRefNum_H1.getValue()) > 0) {
//            scrnMsg.arCustRefNum_H1.setErrorInfo(1, "NFCM0023E", null);
//            errorFlg = false;
//        }
        // START 2018/07/20 Y.Matsui [QC#26985,DEL]

        if (errorFlg == true && ZYPCommonFunc.hasValue(scrnMsg.arTrxDt_H1) && !ZYPCommonFunc.hasValue(scrnMsg.arTrxDt)) {
            scrnMsg.arTrxDt_H1.setErrorInfo(1, "NFCM0024E", null);
            errorFlg = false;
        }

        if (errorFlg == true && ZYPCommonFunc.hasValue(scrnMsg.arTrxDt) && ZYPCommonFunc.hasValue(scrnMsg.arTrxDt_H1) && ZYPDateUtil.compare(scrnMsg.arTrxDt.getValue(), scrnMsg.arTrxDt_H1.getValue()) == 1) {
            scrnMsg.arTrxDt_H1.setErrorInfo(1, "ZZM9010E", new String[] {"Transaction Date" });
            errorFlg = false;
        }

        if (errorFlg == true && ZYPCommonFunc.hasValue(scrnMsg.xxInpAmtNum_H1) && !ZYPCommonFunc.hasValue(scrnMsg.xxInpAmtNum)) {
            scrnMsg.xxInpAmtNum_H1.setErrorInfo(1, "NFCM0024E", null);
            errorFlg = false;
        }

        if (errorFlg == true && ZYPCommonFunc.hasValue(scrnMsg.xxInpAmtNum) && ZYPCommonFunc.hasValue(scrnMsg.xxInpAmtNum_H1) && scrnMsg.xxInpAmtNum.getValue().compareTo(scrnMsg.xxInpAmtNum_H1.getValue()) == 1) {
            scrnMsg.xxInpAmtNum_H1.setErrorInfo(1, "NFCM0023E", null);
            errorFlg = false;
        }

        if (errorFlg == false) {
            this.setButtonProperties("SelectInvoice", "SelectInvoice", "Select", 0, null);
            this.setButtonProperties("Check_All", "Check_All", "CheckAll", 0, null);
            this.setButtonProperties("Un_Check_All", "Un_Check_All", "UnCheckAll", 0, null);
        }

        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL5050CMsg bizMsg = null;

        NFCL5050BMsg scrnMsg = (NFCL5050BMsg) bMsg;

        NFCL5050CommonLogic.controlTableBegin_NFCL5050Scrn00_A(this, scrnMsg);

        bizMsg = NFCL5050CommonLogic.setRequestData_NFCL5050Scrn00_SearchInvoice(scrnMsg);

        Object[] params = (Object[]) getArgForSubScreen();

        String[] ezArTrxNumList = (String[]) params[PARAMS.NUM_5.getValue()];
        String[] ezArTrxTpCdList = (String[]) params[PARAMS.NUM_6.getValue()];
        BigDecimal[] ezArTrxBalPkList = (BigDecimal[]) params[PARAMS.NUM_7.getValue()];

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        for (int i = 0; i < ezArTrxBalPkList.length; i++) {
            bizMsg.B.no(i).arTrxNum_B1.setValue(ezArTrxNumList[i]);
            bizMsg.B.no(i).arTrxTpCd_B1.setValue(ezArTrxTpCdList[i]);
            bizMsg.B.no(i).arTrxBalPk_B1.setValue(ezArTrxBalPkList[i]);

        }

        bizMsg.B.setValidCount(ezArTrxBalPkList.length);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL5050CMsg bizMsg = (NFCL5050CMsg) cMsg;
        NFCL5050BMsg scrnMsg = (NFCL5050BMsg) bMsg;

        if (bizMsg != null) {
            if ("E".equals(bizMsg.getMessageKind())) {
                scrnMsg.setFocusItem(scrnMsg.arTrxNum);
                this.setButtonProperties("SelectInvoice", "SelectInvoice", "Select", 0, null);
                this.setButtonProperties("Check_All", "Check_All", "CheckAll", 0, null);
                this.setButtonProperties("Un_Check_All", "Un_Check_All", "UnCheckAll", 0, null);
                S21TableColorController tblColor = new S21TableColorController("NFCL5050Scrn00", scrnMsg);
                tblColor.clearRowsBG("A", scrnMsg.A);
//                tblColor.clearRowsBG("A1", scrnMsg.A);
//                tblColor.clearRowsBG("A2", scrnMsg.A);
                throw new EZDFieldErrorException();
            }
        }

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFCL5050CommonLogic.transMsgCheck(scrnMsg);

        scrnMsg.putErrorScreen();

        NFCL5050CommonLogic.initialize(this, scrnMsg);

        NFCL5050CommonLogic.scrnItemControl_NFCL5050Scrn00_SearchInvoice(scrnMsg);

        NFCL5050CommonLogic.setRowBg(scrnMsg);

        // scrnMsg.xxChkBox.setValue("0");

        scrnMsg.setFocusItem(scrnMsg.A.no(0).xxChkBox_A1);
        S21TableColorController tblColor = new S21TableColorController("NFCL5050Scrn00", scrnMsg);
        tblColor.clearRowsBG("A", scrnMsg.A);
//        tblColor.clearRowsBG("A1", scrnMsg.A);
//        tblColor.clearRowsBG("A2", scrnMsg.A);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);
//        tblColor.setAlternateRowsBG("A1", scrnMsg.A);
//        tblColor.setAlternateRowsBG("A2", scrnMsg.A);

    }
}
