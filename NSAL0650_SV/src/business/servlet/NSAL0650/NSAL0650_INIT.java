/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0650;

import static business.servlet.NSAL0650.common.NSAL0650CommonLogic.initialControlScreen;
import static business.servlet.NSAL0650.constant.NSAL0650Constant.BUSINESS_ID;

import java.io.Serializable;

import parts.common.EZDBBigDecimalItemArray;
import parts.common.EZDBMsg;
import parts.common.EZDBMsgArray;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0650.NSAL0650CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 * Update Auto Estimation Round
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/11   Hitachi         T.Tsuchida      Create          N/A
 * 2015/11/19   Hitachi         T.Tsuchida      Update          QC#883
 *</pre>
 */
public class NSAL0650_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0650BMsg scrnMsg = (NSAL0650BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.P);

        Serializable ser = getArgForSubScreen();
        if (ser instanceof Object[]) {
            Object[] prm = (Object[]) ser;
            if (prm != null) {
                if (prm.length > 0 && prm[0] != null && prm[0] instanceof EZDBMsgArray) {
                    EZDMsg.copy(((EZDBMsgArray) prm[0]), null, scrnMsg.P, null);
                }
            }
        }

        NSAL0650CMsg bizMsg = new NSAL0650CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0650BMsg scrnMsg = (NSAL0650BMsg) bMsg;
        NSAL0650CMsg bizMsg = (NSAL0650CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        initialControlScreen(this, scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL0650BMsg scrnMsg = (NSAL0650BMsg) bMsg;
        scrnMsg.mtrEstTpCd_H.setNameForMessage("Meter Estimate Type");
        scrnMsg.svcMemoRsnCd_H.setNameForMessage("Reason Code");
        scrnMsg.svcCmntTxt_H.setNameForMessage("Notes");
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxScrItem34Txt_A1.setNameForMessage("Contract#");
            scrnMsg.A.no(i).xxGenlFldAreaTxt_A1.setNameForMessage("Branch");
            scrnMsg.A.no(i).dsAcctNm_A1.setNameForMessage("Customer Name");
            scrnMsg.A.no(i).mtrEstTpNm_A1.setNameForMessage("Round");
            scrnMsg.A.no(i).mtrEstTpCd_AH.setNameForMessage("New Round");
            scrnMsg.A.no(i).dsMsgTxt_A1.setNameForMessage("Return Message");
        }
    }
}
