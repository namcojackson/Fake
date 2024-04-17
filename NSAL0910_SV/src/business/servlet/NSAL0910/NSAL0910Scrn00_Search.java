/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0910;

import static business.servlet.NSAL0910.constant.NSAL0910Constant.*;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0910.NSAL0910CMsg;
import business.servlet.NSAL0910.common.NSAL0910CommonLogic;
import business.servlet.NSAL0910.constant.NSAL0910Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/10   Hitachi         K.Kasai         Create          N/A
 *</pre>
 */
public class NSAL0910Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0910BMsg scrnMsg = (NSAL0910BMsg) bMsg;
        if (!ZYPCommonFunc.hasValue(scrnMsg.dsAcctDlrCd)) {
            scrnMsg.dsAcctDlrCd.setErrorInfo(1, NSAL0910Constant.ZZM9000E, new String[] {"Dealer Code" });
        }
        NSAL0910CommonLogic.addCheckItemForSearch(scrnMsg);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0910BMsg scrnMsg = (NSAL0910BMsg) bMsg;
        // set values to items of pagenation for next page pagenation
        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();


        NSAL0910CMsg bizMsg = new NSAL0910CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0910BMsg scrnMsg = (NSAL0910BMsg) bMsg;
        NSAL0910CMsg bizMsg  = (NSAL0910CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSAL0910CommonLogic.clearGUIAttribute(scrnMsg, scrnMsg.A.no(0).getBaseContents());
        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_ID);
        NSAL0910CommonLogic.activateButtonsByFuncList(this, scrnMsg, functionList);
        NSAL0910CommonLogic.activateScreenItemsByFuncList(this, functionList, scrnMsg);
        NSAL0910CommonLogic.controlScreenFields(scrnMsg);
        NSAL0910CommonLogic.alternateTableRowColor(scrnMsg);
        NSAL0910CommonLogic.focusItem(scrnMsg);

    }
}
