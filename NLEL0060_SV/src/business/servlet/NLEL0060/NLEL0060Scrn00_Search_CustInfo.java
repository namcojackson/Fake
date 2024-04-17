/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLEL0060;

import static business.servlet.NLEL0060.constant.NLEL0060Constant.BIZ_ID;
import static business.servlet.NLEL0060.constant.NLEL0060Constant.FUNC_CD_SRCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLEL0060.NLEL0060CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NLEL0060Scrn00_Search_CustInfo
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/10   Fujitsu         C.Tanaka        Create          N/A
 * 2016/04/11   Hitachi         J.Kim           Update          QC#6579
 * 2016/04/18   Hitachi         J.Kim           Update          QC#6581
 *</pre>
 */
public class NLEL0060Scrn00_Search_CustInfo extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLEL0060BMsg scrnMsg = (NLEL0060BMsg) bMsg;

        // START 2016/04/11 J.Kim [QC#6579,DEL]
        // if (!ZYPCommonFunc.hasValue(scrnMsg.sellToCustCd_H1)) {
        //    scrnMsg.sellToCustCd_H1.setErrorInfo(1, ZZM9000E, new String[] {SELL_TO_CUST_CD });
        // }
        // END 2016/04/11 J.Kim [QC#6579,DEL]

        // START 2016/04/18 J.Kim [QC#6581,MOD]
        // scrnMsg.addCheckItem(scrnMsg.sellToCustCd_H1);
        scrnMsg.addCheckItem(scrnMsg.shipToCustAcctCd_H1);
        // END 2016/04/18 J.Kim [QC#6581,MOD]
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLEL0060BMsg scrnMsg = (NLEL0060BMsg) bMsg;

        NLEL0060CMsg bizMsg = new NLEL0060CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLEL0060BMsg scrnMsg = (NLEL0060BMsg) bMsg;
        NLEL0060CMsg bizMsg = (NLEL0060CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2016/04/18 J.Kim [QC#6581,MOD]
        // scrnMsg.addCheckItem(scrnMsg.sellToCustCd_H1);
        scrnMsg.addCheckItem(scrnMsg.shipToCustAcctCd_H1);
        // END 2016/04/18 J.Kim [QC#6581,MOD]
        scrnMsg.putErrorScreen();
    }
}
