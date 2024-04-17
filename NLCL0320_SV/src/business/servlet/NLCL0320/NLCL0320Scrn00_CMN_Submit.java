/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0320;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL0320.NLCL0320CMsg;
import business.servlet.NLCL0320.common.NLCL0320CommonLogic;
import business.servlet.NLCL0320.constant.NLCL0320Constant.MESSAGE_ID;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/11   CSAI            K.Lee           Create          N/A
 *</pre>
 */
public class NLCL0320Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0320BMsg scrnMsg = (NLCL0320BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.A);
        scrnMsg.A.setCheckParam(new String[] {NLCL0320Bean.rtlWhCd_A, NLCL0320Bean.adjAcctAliasNm_A, NLCL0320Bean.adjAcctAliasDescTxt_A, NLCL0320Bean.effFromDt_A , NLCL0320Bean.effThruDt_A }, 1);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0320BMsg scrnMsg = (NLCL0320BMsg) bMsg;

        NLCL0320CMsg bizMsg = new NLCL0320CMsg();
        bizMsg.setBusinessID("NLCL0320");
        bizMsg.setFunctionCode("40");

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0320BMsg scrnMsg = (NLCL0320BMsg) bMsg;
        NLCL0320CMsg bizMsg = (NLCL0320CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NLCL0320CommonLogic.setControlScreen(this, scrnMsg, bizMsg.getUserID());

        scrnMsg.addCheckItem(scrnMsg.A);
        scrnMsg.A.setCheckParam(new String[] {NLCL0320Bean.rtlWhCd_A, NLCL0320Bean.adjAcctAliasNm_A, NLCL0320Bean.adjAcctAliasDescTxt_A, NLCL0320Bean.xxScrItem130Txt_A, NLCL0320Bean.effFromDt_A , NLCL0320Bean.effThruDt_A }, 1);
        scrnMsg.putErrorScreen();

        if ("E".equals(bizMsg.getMessageKind()) || ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            return;
        }
        scrnMsg.setMessageInfo(MESSAGE_ID.NZZM0002I.toString());
    }
}
