/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL0730;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFCL0730.NFCL0730CMsg;
//import business.servlet.NFCL0730.constant.NFCL0730Constant;

import business.blap.NFCL0730.NFCL0730CMsg;
import business.servlet.NFCL0730.common.NFCL0730CommonLogic;
import business.servlet.NFCL0730.constant.NFCL0730Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2022/11/10   Hitachi         S.Naya          Update          QC#57252
 *</pre>
 */
public class NFCL0730Scrn00_AddLine extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL0730BMsg scrnMsg = (NFCL0730BMsg) bMsg;
        
        NFCL0730CommonLogic.addLineCheck(scrnMsg);
        scrnMsg.putErrorScreen();
        
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL0730BMsg scrnMsg = (NFCL0730BMsg) bMsg;

        NFCL0730CMsg bizMsg = new NFCL0730CMsg();
        bizMsg.setBusinessID("NFCL0730");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL0730BMsg scrnMsg = (NFCL0730BMsg) bMsg;
        NFCL0730CMsg bizMsg  = (NFCL0730CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        
        // START 2022/11/10 S.Naya [QC#57252,ADD]
        scrnMsg.addCheckItem(scrnMsg.xxCmntTxt_H1);
        scrnMsg.putErrorScreen();
        // END   2022/11/10 S.Naya [QC#57252,ADD]
        
        this.setButtonProperties("btn2", "CMN_Submit", "Submit", 1, null);
        this.setButtonProperties("DeleteLines", "DeleteLines", "Delete", 1, null);

        scrnMsg.arAdjTpCd_H1.clear();
        scrnMsg.adjDt_H1.clear();
        scrnMsg.dealArAdjAmt_H1.clear();
        scrnMsg.adjCmntTxt_H1.clear();
        // START 2022/11/10 S.Naya [QC#57252,ADD]
        scrnMsg.xxCmntTxt_H1.clear();
        scrnMsg.xxCmntTxt_H1.setInputProtected(true);
        setButtonEnabled(NFCL0730Constant.BTN_A, false);
        // END   2022/11/10 S.Naya [QC#57252,ADD]
    }
}
