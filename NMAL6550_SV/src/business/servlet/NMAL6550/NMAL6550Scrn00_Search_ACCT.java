/*
 *  <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
*/
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/22/2010   Fujitsu         H.Nagashima     Create          N/A
 *</pre>
 */
package business.servlet.NMAL6550;


import parts.common.*;
import parts.servletcommon.*;
//import business.blap.NMAL6550.NMAL6550CMsg;

import business.blap.NMAL6550.NMAL6550CMsg;
import business.servlet.NMAL6550.common.NMAL6550CommonLogic;
import business.servlet.NMAL6550.constant.NMAL6550Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NMAL6550Scrn00_Search_ACCT extends S21CommonHandler implements NMAL6550Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6550BMsg scrnMsg = (NMAL6550BMsg) bMsg;

        int eventOccuredLine = getButtonSelectNumber();
        scrnMsg.xxNum.setValue(eventOccuredLine);

        scrnMsg.addCheckItem(scrnMsg.A.no(eventOccuredLine).acctCd_A1);
        scrnMsg.putErrorScreen();

	}

 	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
 
        NMAL6550BMsg scrnMsg = (NMAL6550BMsg) bMsg;

        NMAL6550CMsg bizMsg = NMAL6550CommonLogic.setBizFunction("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6550BMsg scrnMsg = (NMAL6550BMsg) bMsg;
        NMAL6550CMsg bizMsg  = (NMAL6550CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NMAL6550CommonLogic.setControlScreen(this, scrnMsg, bizMsg.getUserID());

        scrnMsg.addCheckItem(scrnMsg.A);
        scrnMsg.A.setCheckParam(new String[]{NMAL6550Bean.acctCd_A1}, 1);
        scrnMsg.putErrorScreen();

        scrnMsg.setFocusItem(scrnMsg.A.no(0).acctCd_A1);
        if ("E".equals(bizMsg.getMessageKind()) || ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            return;
        }
        
        scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.xxNum.getValueInt()).acctCd_A1);

	}

}