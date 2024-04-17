/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6720;

import static business.servlet.NMAL6720.constant.NMAL6720Constant.BUSINESS_ID;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.FUNC_CD_SRCH;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.MSG_BILL_TO_CD;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.MSG_MST;
import static business.servlet.NMAL6720.constant.NMAL6720Constant.NMAM0163E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NMAL6720.NMAL6720CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/15   Fujitsu         C.Tanaka        Create          CSA
 * 2016/10/21   Fujitsu         C.Yokoi         Update          QC#14982
 * 2018/07/11   Fujitsu         H.Kumagai       Update          QC#26422
 *</pre>
 */
public class NMAL6720Scrn00_ShowRelatedBillToDetails extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // 2018/07/11 Update Start QC#26422
        NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;

        NMAL6720CMsg bizMsg = new NMAL6720CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
        // 2018/07/11 Update End QC#26422
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;
        // 2018/07/11 Update Start QC#26422
        NMAL6720CMsg bizMsg = (NMAL6720CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (!ZYPCommonFunc.hasValue(scrnMsg.billToCustCd_SH.getValue())) {
            scrnMsg.billToCustCd_SH.setErrorInfo(1, NMAM0163E, new String[] {MSG_BILL_TO_CD, MSG_MST });
        }
        scrnMsg.addCheckItem(scrnMsg.billToCustCd_SH);
        scrnMsg.putErrorScreen();

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
        // 2018/07/11 Update End QC#26422

        Object[] params = new Object[1];
        // 2016/10/21 CSA-QC#14982 Mod Start
        params[0] = scrnMsg.billToCustCd_SH;
        // params[0] = scrnMsg.billToCustCd_BI;
        // 2016/10/21 CSA-QC#14982 Mod End

        setArgForSubScreen(params);

    }
}
