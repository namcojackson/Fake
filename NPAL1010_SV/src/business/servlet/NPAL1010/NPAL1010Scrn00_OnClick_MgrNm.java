/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1010;

import static business.servlet.NPAL1010.constant.NPAL1010Constant.BUSINESS_ID;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.FUNC_SRCH_ID;
//import static business.servlet.NPAL1010.constant.NPAL1010Constant.ZZM9000E;
//import static business.servlet.NPAL1010.constant.NPAL1010Constant.LABEL_WH_MGR_PSN_CD;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1010.NPAL1010CMsg;
import business.servlet.NPAL1010.common.NPAL1010CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Business ID   : NPAL1010 Location Info Pop Up
 * Function Name : Button Action to get Manager Name
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/04/2016   CSAI            D.Fukaya        Create          QC#7628
 *</pre>
 */
public class NPAL1010Scrn00_OnClick_MgrNm extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1010BMsg scrnMsg = (NPAL1010BMsg) bMsg;
        NPAL1010CommonLogic.addCheckItemSearchCondition(scrnMsg);
        if (!ZYPCommonFunc.hasValue(scrnMsg.whMgrPsnCd_H1)) {
//            scrnMsg.whMgrPsnCd_H1.setErrorInfo(1, ZZM9000E, new String[] {LABEL_WH_MGR_PSN_CD });
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1010BMsg scrnMsg = (NPAL1010BMsg) bMsg;

        NPAL1010CMsg bizMsg = new NPAL1010CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_SRCH_ID);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1010BMsg scrnMsg = (NPAL1010BMsg) bMsg;
        NPAL1010CMsg bizMsg = (NPAL1010CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.whMgrPsnCd_H1);
        scrnMsg.putErrorScreen();

        // cursor focus
        scrnMsg.setFocusItem(scrnMsg.whMgrPsnCd_H1);
    }
}
