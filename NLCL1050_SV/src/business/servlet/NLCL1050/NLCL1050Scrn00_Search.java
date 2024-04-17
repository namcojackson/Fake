/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL1050;

import static business.servlet.NLCL1050.constant.NLCL1050Constant.BIZ_ID;
import static business.servlet.NLCL1050.constant.NLCL1050Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NLCL1050.constant.NLCL1050Constant.NAMM0027E;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL1050.NLCL1050CMsg;
import business.servlet.NLCL1050.common.NLCL1050CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/08   CITS            N.Akaishi       Create          N/A
 *</pre>
 */
public class NLCL1050Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL1050BMsg scrnMsg = (NLCL1050BMsg) bMsg;
        if (!ZYPCommonFunc.hasValue(scrnMsg.abcAnlsClsNm)) {
            scrnMsg.abcAnlsClsNm.setErrorInfo(1, NAMM0027E, new String[] {"ABC Class Name"});
            scrnMsg.addCheckItem(scrnMsg.abcAnlsClsNm);
            scrnMsg.putErrorScreen();
        }

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL1050BMsg scrnMsg = (NLCL1050BMsg) bMsg;

        NLCL1050CMsg bizMsg = new NLCL1050CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL1050BMsg scrnMsg = (NLCL1050BMsg) bMsg;
        NLCL1050CMsg bizMsg  = (NLCL1050CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLCL1050CommonLogic.commonInit(scrnMsg);

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_ID);
        NLCL1050CommonLogic.ctrlScreenItem(this, scrnMsg, funcList);

        if (!ZYPCommonFunc.hasValue(scrnMsg.abcAnlsClsNm)) {
            scrnMsg.setFocusItem(scrnMsg.abcAnlsClsNm);
        } else {
            if (scrnMsg.A.getValidCount() > 0) {
                scrnMsg.setFocusItem(scrnMsg.A.no(0).abcAnlsClsPrtyNum);
            } else {
                scrnMsg.setFocusItem(scrnMsg.abcAnlsClsNm);
            }
        }
    }
}
