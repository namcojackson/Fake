/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3020;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL3020.NFCL3020CMsg;
import business.servlet.NFCL3020.common.NFCL3020CommonLogic;
import business.servlet.NFCL3020.constant.NFCL3020Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Batch Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/12/2015   CSAI            K.Lee           Create          Initial
 * 02/10/2016   Fujitsu         T.Tanaka        Update          Def#3294
 * 02/25/2016   Fujitsu         T.Tanaka        Update          Def#2742 negative amount
 * 2018/01/18   Fujitsu         H.Ikeda         Update          QC#22759
 * 2018/01/18   Hitachi         Y.Takeno        Update          QC#21406
 *</pre>
 */
public class NFCL3020Scrn00_Click_Add extends S21CommonHandler implements NFCL3020Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3020BMsg scrnMsg = (NFCL3020BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.arBatRcptCnt_H);
        scrnMsg.addCheckItem(scrnMsg.arBatRcptAmt_H);
        // START 2018/01/18 [QC#21406, ADD]
        scrnMsg.addCheckItem(scrnMsg.rcptChkNum_BH);
        // END   2018/01/18 [QC#21406, ADD]
        scrnMsg.addCheckItem(scrnMsg.arRcptTrxTpCd_BH);
        scrnMsg.addCheckItem(scrnMsg.rcptDt_BH);
        scrnMsg.addCheckItem(scrnMsg.funcRcptAmt_BH);
        // START 2018/01/18 H.Ikeda [QC#22759, ADD]
        scrnMsg.addCheckItem(scrnMsg.payerCustCd_BH);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNm_BH);
        scrnMsg.addCheckItem(scrnMsg.locNum_BH);
        // START 2018/01/18 H.Ikeda [QC#22759, ADD]
        
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.B.no(i).funcRcptAmt_B);
        }
        if(ZYPCommonFunc.hasValue(scrnMsg.arBatRcptCnt_H.getValue())) {
            if(scrnMsg.arBatRcptCnt_H.getValue().compareTo(BigDecimal.ZERO) < 0) {
                scrnMsg.arBatRcptCnt_H.setErrorInfo(1, "NFCM0006E");
            }
        }
        if(ZYPCommonFunc.hasValue(scrnMsg.arBatRcptAmt_H.getValue())) {
            if(scrnMsg.arBatRcptAmt_H.getValue().compareTo(BigDecimal.ZERO) < 0) {
                scrnMsg.arBatRcptAmt_H.setErrorInfo(1, "NFCM0006E");
            }
        }
        if(ZYPCommonFunc.hasValue(scrnMsg.funcRcptAmt_BH.getValue())) {
            if(scrnMsg.funcRcptAmt_BH.getValue().compareTo(BigDecimal.ZERO) < 0) {
                scrnMsg.funcRcptAmt_BH.setErrorInfo(1, "NFCM0006E");
            }
        }

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3020BMsg scrnMsg = (NFCL3020BMsg) bMsg;

        NFCL3020CMsg bizMsg = new NFCL3020CMsg();
        bizMsg.setBusinessID("NFCL3020");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3020BMsg scrnMsg = (NFCL3020BMsg) bMsg;
        NFCL3020CMsg bizMsg  = (NFCL3020CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NFCL3020CommonLogic.initialize(this, scrnMsg, getUserProfileService());
        scrnMsg.addCheckItem(scrnMsg.arRcptTrxTpCd_BH);
        scrnMsg.addCheckItem(scrnMsg.rcptDt_BH);
        scrnMsg.addCheckItem(scrnMsg.funcRcptAmt_BH);
        scrnMsg.addCheckItem(scrnMsg.arBatRcptDt_H);
        // START 2018/01/18 H.Ikeda [QC#22759, ADD]
        scrnMsg.addCheckItem(scrnMsg.payerCustCd_BH);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNm_BH);
        scrnMsg.addCheckItem(scrnMsg.locNum_BH);
        // END  2018/01/18 H.Ikeda [QC#22759, ADD]
        scrnMsg.putErrorScreen();
        
        scrnMsg.setFocusItem(scrnMsg.rcptChkNum_BH);

        // Table Line Color
        S21TableColorController tblColor = new S21TableColorController("NFCL3020Scrn00", scrnMsg);
//        tblColor.clearRowsBG(TABLE_B, scrnMsg.B);
        tblColor.setAlternateRowsBG(TABLE_B, scrnMsg.B);

        // Receipt Number Link Setting
        NFCL3020CommonLogic.setRecptNum(scrnMsg);

        NFCL3020CommonLogic.setTableCursor(scrnMsg);
    }
}
