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
 * 11/25/2009   Fujitsu         FAP)D.Kato      Update          DefID 2024
 * 02/04/2010   Fujitsu         FAP)D.Kato      Update      
 * 03/22/2010   Fujitsu         FAP)K.Sakano    Update          DefID 5048
 * 05/14/2010   Fujitsu         FAP)N.Aoyama    Update          DefID:6321
 * 05/25/2010   Fujitsu         K.Kimura        Update          DefID:6738 No:045
 * 07/13/2010   Fujitsu         I.Kondo         Update          DefID 7731 No.198
 * 08/06/2010   Fujitsu         I.Kondo         Update          Merge.
 * 03/10/2016   Fujitsu         T.Tanaka        Update          Def#5318
 * 03/31/2016   Fujitsu         T.Tanaka        Update          Def#6241
 * 2018/07/20   Fujitsu         Y.Matsui        Update          QC#26985
 *</pre>
 */
package business.servlet.NFCL5050;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL5050.NFCL5050CMsg;
import business.servlet.NFCL5050.common.NFCL5050CommonLogic;
import business.servlet.NFCL5050.constant.NFCL5050Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * NFCL5050_INIT.
 */
public class NFCL5050_INIT extends S21INITCommonHandler implements NFCL5050Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL5050CMsg bizMsg = null;

        NFCL5050BMsg scrnMsg = (NFCL5050BMsg) bMsg;
        Object[] params = (Object[]) getArgForSubScreen();

        if (params == null || params.length != NUM_OF_PARAMS) {
            return null;
        }

        NFCL5050CommonLogic.otherBusConnectFrom_NFCL5050_INIT(params, scrnMsg);

        bizMsg = NFCL5050CommonLogic.setRequestData_NFCL5050_INIT(scrnMsg);

        NFCL5050CommonLogic.scrnItemControl_NFCL5050_INIT(scrnMsg);

        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        
        BigDecimal[] ezArTrxBalPkList = (BigDecimal[]) params[PARAMS.NUM_7.getValue()];
        String strRefNum = (String) params[PARAMS.NUM_13.getValue()];
        if ( ZYPCommonFunc.hasValue(strRefNum) ) {
            if (strRefNum.contains("%")) {
                bizMsg.arCustRefNum.setValue(strRefNum);
            } else {
                bizMsg.arCustRefNum.setValue(strRefNum + "%");
                //bizMsg.arCustRefNum_H1.setValue(strRefNum);
            }
        }
        for (int i = 0; i < ezArTrxBalPkList.length; i++) {
            bizMsg.B.no(i).arTrxBalPk_B1.setValue(ezArTrxBalPkList[i]);
        }
        bizMsg.B.setValidCount(ezArTrxBalPkList.length);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL5050BMsg scrnMsg = (NFCL5050BMsg) bMsg;
        NFCL5050CMsg bizMsg = (NFCL5050CMsg) cMsg;

        Object[] params = (Object[]) getArgForSubScreen();

        if (params == null || params.length != NUM_OF_PARAMS) {
            scrnMsg.setMessageInfo("NFCM0031E", null);
            this.setButtonProperties("SearchInvoice", "SearchInvoice", "Search", 0, null);
            this.setButtonProperties("SelectInvoice", "SelectInvoice", "Select", 0, null);
            this.setButtonProperties("Check_All", "Check_All", "CheckAll", 0, null);
            this.setButtonProperties("Un_Check_All", "Un_Check_All", "UncheckAll", 0, null);
            this.setButtonProperties("btn8", "CMN_Clear", "Clear", 0, null);
            this.setButtonProperties("btn10", "CMN_Close", "Close", 1, null);

            scrnMsg.setInputProtected(true);
            scrnMsg.putErrorScreen();
            return;
        }

        if (bizMsg != null) {
            if ("E".equals(bizMsg.getMessageKind())) {
                if ("NFCM0106E".equals(bizMsg.getMessageCode())) {
                    this.setButtonProperties("SearchInvoice", "SearchInvoice", "Search", 0, null);
                    this.setButtonProperties("SelectInvoice", "SelectInvoice", "Select", 0, null);
                    this.setButtonProperties("Check_All", "Check_All", "CheckAll", 0, null);
                    this.setButtonProperties("Un_Check_All", "Un_Check_All", "UncheckAll", 0, null);
                    this.setButtonProperties("btn8", "CMN_Clear", "Clear", 0, null);
                    this.setButtonProperties("btn10", "CMN_Close", "Close", 1, null);
                    scrnMsg.setInputProtected(true);
                    scrnMsg.putErrorScreen();
                    return;
                } else {
//                    throw new EZDFieldErrorException();
                }
            }
        }

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFCL5050CommonLogic.transMsgCheck(scrnMsg);
        scrnMsg.putErrorScreen();

        NFCL5050CommonLogic.initialize(this, scrnMsg);

//        NFCL5050CommonLogic.setScrnItemValue_NFCL5050_INIT(scrnMsg);

        NFCL5050CommonLogic.busBtnControl_NFCL5050_INIT(this, scrnMsg);

        NFCL5050CommonLogic.scrnItemControl_NFCL5050_INIT(scrnMsg);

        
        // Def#6241 Delete Initial Search
        //        NFCL5050CommonLogic.scrnItemControl_NFCL5050Scrn00_SearchInvoice(scrnMsg);
        NFCL5050CommonLogic.busBtnControl_NFCL5050_INIT_SelectInvoice(this,scrnMsg);

        NFCL5050CommonLogic.setRowBg(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.A.no(0).xxChkBox_A1);
        S21TableColorController tblColor = new S21TableColorController("NFCL5050Scrn00", scrnMsg);
        tblColor.clearRowsBG("A", scrnMsg.A);
        tblColor.setAlternateRowsBG("A", scrnMsg.A);

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NFCL5050BMsg scrnMsg = (NFCL5050BMsg) bMsg;
        scrnMsg.xxPageShowCurNum.setNameForMessage(S21BatchSearchPagenationScrnSupport.getCurrentPageFieldName());
        scrnMsg.arTrxNum.setNameForMessage("Trx Number From");
        scrnMsg.arTrxNum_H1.setNameForMessage("Trx Number To");
        // START 2018/07/20 Y.Matsui [QC#26985,ADD]
        scrnMsg.xxTrxNumSrchTxt.setNameForMessage("Trx Number");
        // END   2018/07/20 Y.Matsui [QC#26985,ADD]
        scrnMsg.arTrxDt.setNameForMessage("Trx Date From");
        scrnMsg.arTrxDt_H1.setNameForMessage("Trx Date To");
        scrnMsg.grpInvNum.setNameForMessage("Summary Billing Number From");
        scrnMsg.soNum.setNameForMessage("SO Number");
        scrnMsg.custIssPoNum.setNameForMessage("Cust PO Number");
        scrnMsg.cpoOrdNum.setNameForMessage("CPO Number");
        scrnMsg.crCardOrdNum.setNameForMessage("CC Order ID");
        scrnMsg.xxInpAmtNum.setNameForMessage("Gross Amt From");
        scrnMsg.xxInpAmtNum_H1.setNameForMessage("Gross Amt To");

    }

}
