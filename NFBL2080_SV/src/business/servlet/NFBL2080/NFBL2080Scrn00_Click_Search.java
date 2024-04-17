/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2080;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFBL2080.NFBL2080CMsg;
import business.servlet.NFBL2080.common.NFBL2080CommonLogic;
import business.servlet.NFBL2080.constant.NFBL2080Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * AP Invoice I/F Error Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/29   CSAI            Y.Miyauchi      Create          N/A
 *</pre>
 */
public class NFBL2080Scrn00_Click_Search extends S21CommonHandler implements NFBL2080Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2080BMsg scrnMsg = (NFBL2080BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.vndCd);
        scrnMsg.addCheckItem(scrnMsg.vndInvNum);
        scrnMsg.addCheckItem(scrnMsg.soNum);
        scrnMsg.addCheckItem(scrnMsg.xxCratDt_S1);
        scrnMsg.addCheckItem(scrnMsg.xxCratDt_S2);
        scrnMsg.addCheckItem(scrnMsg.poOrdNum);
        scrnMsg.addCheckItem(scrnMsg.ediPoOrdNum);
        scrnMsg.addCheckItem(scrnMsg.batErrMsgTxt);

        if ( ZYPCommonFunc.hasValue(scrnMsg.xxCratDt_S1.getValue()) && ZYPCommonFunc.hasValue(scrnMsg.xxCratDt_S2.getValue()) ){
            if ( ZYPDateUtil.compare(scrnMsg.xxCratDt_S1.getValue(), scrnMsg.xxCratDt_S2.getValue()) > 0 ){
                scrnMsg.xxCratDt_S1.setErrorInfo(1, MSG_ID.NPAM0225E.name(), new String[]{"EDI Rcv Date"});
                scrnMsg.xxCratDt_S2.setErrorInfo(1, MSG_ID.NPAM0225E.name(), new String[]{"EDI Rcv Date"});
                scrnMsg.addCheckItem(scrnMsg.xxCratDt_S1);
                scrnMsg.addCheckItem(scrnMsg.xxCratDt_S2);
            }
        }

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2080BMsg scrnMsg = (NFBL2080BMsg) bMsg;

        NFBL2080CMsg bizMsg = new NFBL2080CMsg();
        bizMsg.setBusinessID("NFBL2080");
        bizMsg.setFunctionCode("20");
        scrnMsg.xxDplyTab.clear();
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL2080BMsg scrnMsg = (NFBL2080BMsg) bMsg;
        NFBL2080CMsg bizMsg  = (NFBL2080CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFBL2080CommonLogic.setHyoSettings(scrnMsg, this);

        scrnMsg.putErrorScreen();

    }
}
