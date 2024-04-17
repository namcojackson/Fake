/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2010;

import static business.servlet.NWAL2010.constant.NWAL2010Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2010.NWAL2010CMsg;
import business.servlet.NWAL2010.common.NWAL2010CommonLogic;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2010Scrn00_Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/06   Fujitsu         C.Yokoi         Create          N/A
 * 2022/12/14   Hitachi         R.Takau         Update          QC#60823
 * 2024/04/10   CITS            M.Okamura       Update          QC#63757
 *</pre>
 */
public class NWAL2010Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2010BMsg scrnMsg = (NWAL2010BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.sellToCustCd);
        scrnMsg.addCheckItem(scrnMsg.crCardCustRefNum);
        scrnMsg.addCheckItem(scrnMsg.crCardExprYrMth);
        scrnMsg.addCheckItem(scrnMsg.crCardLastDigitNum);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2010BMsg scrnMsg = (NWAL2010BMsg) bMsg;

        NWAL2010CMsg bizMsg = new NWAL2010CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2010BMsg scrnMsg = (NWAL2010BMsg) bMsg;
        NWAL2010CMsg bizMsg = (NWAL2010CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL2010CommonLogic.initCmnBtnProp(this);
        // Start 2022/12/14 R.Takau [QC#60823,MOD]
        //NWAL2010CommonLogic.setCmnBtnProp(scrnMsg);
        NWAL2010CommonLogic.setCmnBtnProp(scrnMsg,this);
        // End 2022/12/14 R.Takau [QC#60823,MOD]
        // Start 2024/04/10 M.Okamura [QC#63757,DEL]
        //NWAL2010CommonLogic.setCrCardProp(this, scrnMsg);
        // End 2024/04/10 M.Okamura [QC#63757,DEL]
        NWAL2010CommonLogic.setBgColor(scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.crCardCustRefNum);

        // Start 2024/04/09 M.Okamura [QC#63757,DEL]
        // if (ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_C)) {
            // NWAL2010TceppsRequestResponce tr = new NWAL2010TceppsRequestResponce();
            // tr.loadRequestParams(scrnMsg, ctx.getHttpServletRequest().getRequestURL().toString());
        // }
        // End 2024/04/09 M.Okamura [QC#63757,DEL]
    }
}
