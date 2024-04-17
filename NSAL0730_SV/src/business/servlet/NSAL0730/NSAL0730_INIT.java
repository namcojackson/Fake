/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0730;

import static business.servlet.NSAL0730.constant.NSAL0730Constant.BUSINESS_ID;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBMsgArray;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0730.NSAL0730CMsg;
import business.servlet.NSAL0730.common.NSAL0730CommonLogic;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/19   Hitachi         J.Kim           Create          N/A
 * 2019/01/10   Hitachi         K.Kitachi       Update          QC#26928
 *</pre>
 */
public class NSAL0730_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0730BMsg scrnMsg = (NSAL0730BMsg) bMsg;
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0730BMsg scrnMsg = (NSAL0730BMsg) bMsg;

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

        NSAL0730CMsg bizMsg = new NSAL0730CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0730BMsg scrnMsg = (NSAL0730BMsg) bMsg;
        NSAL0730CMsg bizMsg = (NSAL0730CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0730CommonLogic.initialControlScreen(this, scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NSAL0730BMsg scrnMsg = (NSAL0730BMsg) bMsg;

        scrnMsg.custPoNum.setNameForMessage("New PO#");
        // START 2019/01/10 K.Kitachi [QC#26928, MOD]
//        scrnMsg.poDt.setNameForMessage("Expiry Date");
        scrnMsg.poFromDt.setNameForMessage("From Date");
        scrnMsg.poDt.setNameForMessage("Thru Date");
        // END 2019/01/10 K.Kitachi [QC#26928, MOD]
        scrnMsg.svcMemoRsnCd_H3.setNameForMessage("Reason Code");
        scrnMsg.svcCmntTxt.setNameForMessage("Notes");

        // START 2019/01/10 K.Kitachi [QC#26928, MOD]
//        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
        for (int i = 0; i < scrnMsg.A.length(); i++) {
//            scrnMsg.A.no(i).custPoNum_D2.setNameForMessage("New PO#");
//            scrnMsg.A.no(i).poDt_D2.setNameForMessage("New Exp Date");
            scrnMsg.A.no(i).custPoNum_A.setNameForMessage("PO#");
            scrnMsg.A.no(i).poFromDt_A.setNameForMessage("From Date");
            scrnMsg.A.no(i).poDt_A.setNameForMessage("Thru Date");
        }
        // END 2019/01/10 K.Kitachi [QC#26928, MOD]
    }
}
