/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0010.NFDL0010CMsg;
import business.servlet.NFDL0010.common.NFDL0010CommonLogic;
import business.servlet.NFDL0010.constant.NFDL0010Constant;

import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Collection Summary
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 2016/08/25   Hitachi         K.Kojima        Update          QC#10997
 * 2017/01/19   Hitachi         E.Kameishi      Update          QC#16808
 * 2018/07/11   Hitachi         Y.Takeno        Update          QC#26679
 *</pre>
 */
public class NFDL0010_INIT extends S21INITCommonHandler implements NFDL0010Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFDL0010BMsg scrnMsg = (NFDL0010BMsg) bMsg;

        NFDL0010CMsg bizMsg = new NFDL0010CMsg();
        bizMsg.setBusinessID("NFDL0010");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0010BMsg scrnMsg = (NFDL0010BMsg) bMsg;
        NFDL0010CMsg bizMsg  = (NFDL0010CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        S21TableColorController tblColor = new S21TableColorController("NFDL0010Scrn00", scrnMsg);
        tblColor.clearRowsBG(TABLE_A, scrnMsg.A);
        NFDL0010CommonLogic.initialize(this, scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NFDL0010BMsg scrnMsg = (NFDL0010BMsg) bMsg;

        // START 2018/07/11 [QC#26679, ADD]
        scrnMsg.cltDispTpCd_H.setNameForMessage("Display");
        scrnMsg.xxModeCd_H.setNameForMessage("Search Mode");
        scrnMsg.xxQueryFltrTxt_H1.setNameForMessage("Collector");
        scrnMsg.cltPsnNm_H1.setNameForMessage("Collector Name");
        scrnMsg.xxQueryFltrTxt_H2.setNameForMessage("Account#");
        scrnMsg.dsAcctNm_H.setNameForMessage("Account Name");
        scrnMsg.cltPtfoNm_H.setNameForMessage("Portfolio Name");
        // END   2018/07/11 [QC#26679, ADD]

        for (int i=0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).dealCltDsptAmt_A1.setAppFracDigit(2);
            scrnMsg.A.no(i).dealCltPrmsAmt_A1.setAppFracDigit(2);
            scrnMsg.A.no(i).dealRcptAmt_A1.setAppFracDigit(2);
            scrnMsg.A.no(i).totBalAmt_A1.setAppFracDigit(2);
            scrnMsg.A.no(i).netAmt_A1.setAppFracDigit(2);
            // START 2017/08/07 T.Tsuchida [QC#19576,MOD]
            // scrnMsg.A.no(i).dsoAmt_A1.setAppFracDigit(2);
            // scrnMsg.A.no(i).dsoAmt_A2.setAppFracDigit(2);
            scrnMsg.A.no(i).dsoAmt_A1.setAppFracDigit(0);
            scrnMsg.A.no(i).dsoAmt_A2.setAppFracDigit(0);
            // END 2017/08/07 T.Tsuchida [QC#19576,MOD]
            //START 2017/01/19 E.Kameishi [QC#16808,ADD]
            scrnMsg.xxPageShowCurNum.setNameForMessage(S21BatchSearchPagenationScrnSupport.getCurrentPageFieldName());
            //END 2017/01/19 E.Kameishi [QC#16808,ADD]
        }
    }
}
