/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6710;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL6710.NMAL6710CMsg;
import static business.servlet.NMAL6710.constant.NMAL6710Constant.ZZM9000E;

import business.blap.NMAL6710.NMAL6710CMsg;
import business.servlet.NMAL6710.common.NMAL6710CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NMAL6710Scrn00_SaveSearch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6710BMsg scrnMsg = (NMAL6710BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.srchOptNm_S)){
            scrnMsg.srchOptNm_S.setErrorInfo(1, ZZM9000E, new String[]{"Search Option Name"});
            scrnMsg.addCheckItem(scrnMsg.srchOptNm_S);
            scrnMsg.putErrorScreen();
        }

        // QC#4522
        NMAL6710CommonLogic.checkItemAttribute(scrnMsg);
       
        // QC#6189
        NMAL6710CommonLogic.checkMandatorySearchCondition(scrnMsg);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6710BMsg scrnMsg = (NMAL6710BMsg) bMsg;

        NMAL6710CMsg bizMsg = new NMAL6710CMsg();
        bizMsg.setBusinessID("NMAL6710");
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6710BMsg scrnMsg = (NMAL6710BMsg) bMsg;
        NMAL6710CMsg bizMsg  = (NMAL6710CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.addCheckItem(scrnMsg.srchOptNm_S);
        scrnMsg.putErrorScreen();
    }
}
