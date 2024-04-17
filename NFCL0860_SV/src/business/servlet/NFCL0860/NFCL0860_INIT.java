/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL0860;

import static business.servlet.NFCL0860.constant.NFCL0860Constant.BIZ_ID;
import static business.servlet.NFCL0860.constant.NFCL0860Constant.FUNC_CD_SRCH;
import static business.servlet.NFCL0860.constant.NFCL0860Constant.SCRN_ID_00;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFCL0860.common.NFCL0860CommonLogic;
import business.blap.NFCL0860.NFCL0860CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NFCL0860_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/21   Fujitsu         S.Fujita        Create          N/A
 * 2016/03/14   Hitachi         T.Tsuchida      Update          QC#5431
 * 2016/05/11   Fujitsu         S.Fujita        Update          QC#7983
 * 2022/01/08   CITS            K.Suzuki        Update          QC#55788-1
 * 2022/01/22   CITS            K.Suzuki        Update          QC#55788-2
 *</pre>
 */
public class NFCL0860_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
      checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL0860BMsg scrnMsg = (NFCL0860BMsg) bMsg;
        NFCL0860CMsg bizMsg = new NFCL0860CMsg();

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);

        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            if (params != null && params.length == 1) {
                EZDBStringItem invNum = (EZDBStringItem) params[0];
                ZYPEZDItemValueSetter.setValue(scrnMsg.invNum_H, invNum);
            }
        } else {
            scrnMsg.invNum_H.clear();
        }

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL0860BMsg scrnMsg = (NFCL0860BMsg) bMsg;
        NFCL0860CMsg bizMsg = (NFCL0860CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.clearAllGUIAttribute(SCRN_ID_00);
        NFCL0860CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
        NFCL0860CommonLogic.initCmnBtnProp(this);
        NFCL0860CommonLogic.setGuiAttr(this, scrnMsg);

        // START 2022/01/08 K.Suzuki [QC#55788-1, MOD]
        //scrnMsg.invNum_H.setInputProtected(true);
        // START 2022/01/22 K.Suzuki [QC#55788-2, MOD]
        //scrnMsg.invNum_H1.setInputProtected(true);
        scrnMsg.arCustRefNum_H.setInputProtected(true);
        // END   2022/01/22 K.Suzuki [QC#55788-2, MOD]
        // END   2022/01/08 K.Suzuki [QC#55788-1, MOD]

        // START 2022/01/08 K.Suzuki [QC#55788-1, MOD]
        //scrnMsg.setFocusItem(scrnMsg.invNum_H);
        // START 2022/01/22 K.Suzuki [QC#55788-2, MOD]
        //scrnMsg.setFocusItem(scrnMsg.invNum_H1);
        scrnMsg.setFocusItem(scrnMsg.arCustRefNum_H);
        // END   2022/01/22 K.Suzuki [QC#55788-2, MOD]
        // END   2022/01/08 K.Suzuki [QC#55788-1, MOD]
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NFCL0860BMsg scrnMsg = (NFCL0860BMsg) bMsg;

        // START 2016/05/11 S.Fujita [QC#7983,MOD]
//        scrnMsg.invNum_H.setNameForMessage("Invoice#");
        // START 2022/01/08 K.Suzuki [QC#55788-1, MOD]
        //scrnMsg.invNum_H.setNameForMessage("Invoice Number");
        // START 2022/01/22 K.Suzuki [QC#55788-2, MOD]
        //scrnMsg.invNum_H1.setNameForMessage("Invoice Number");
        scrnMsg.arCustRefNum_H.setNameForMessage("Transaction#");
        // END   2022/01/22 K.Suzuki [QC#55788-2, MOD]
        // END   2022/01/08 K.Suzuki [QC#55788-1, MOD]
        // END 2016/05/11 S.Fujita [QC#7983,MOD]

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NFCL0860_ABMsg aBMsg = scrnMsg.A.no(i);
            aBMsg.arTrxTpDescTxt_A.setNameForMessage("Type");
            aBMsg.arScrCancFlg_A.setNameForMessage("C");
            // START 2016/03/14 T.Tsuchida [QC#5431,MOD]
            //aBMsg.arAdjTpNm_A.setNameForMessage("Activity Name");
            aBMsg.arAdjTpDescTxt_A.setNameForMessage("Activity Name");
            // END 2016/03/14 T.Tsuchida [QC#5431,MOD]
            aBMsg.arTrxNum_A.setNameForMessage("Number");
            aBMsg.arTrxDt_A.setNameForMessage("Activity Date");
            aBMsg.dealOrigGrsAmt_A.setNameForMessage("Amount");
            aBMsg.dealApplyAmt_A.setNameForMessage("Amt Applied");
            aBMsg.cashAppDt_A.setNameForMessage("Appl Date");
            aBMsg.glDt_A.setNameForMessage("GL Date");
            aBMsg.adjCmntTxt_A.setNameForMessage("Comments");
        }

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).dealOrigGrsAmt_A.setAppFracDigit(2);
            scrnMsg.A.no(i).dealApplyAmt_A.setAppFracDigit(2);
        }
    }
}
