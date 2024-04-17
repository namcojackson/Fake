/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0720;

import static business.servlet.NSAL0720.common.NSAL0720CommonLogic.initialControlScreen;
import static business.servlet.NSAL0720.constant.NSAL0720Constant.BUSINESS_ID;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBMsgArray;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0720.NSAL0720CMsg;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Update Bill To
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/20   Hitachi         K.Kasai         Create          N/A
 * 2016/02/29   Hitachi         K.Kasai         Update          QC#2684
 *</pre>
 */
public class NSAL0720_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0720BMsg scrnMsg = (NSAL0720BMsg) bMsg;

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

        NSAL0720CMsg bizMsg = new NSAL0720CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0720BMsg scrnMsg = (NSAL0720BMsg) bMsg;
        NSAL0720CMsg bizMsg = (NSAL0720CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        initialControlScreen(this, scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL0720BMsg scrnMsg = (NSAL0720BMsg) bMsg;
        scrnMsg.xxChkBox_LS.setNameForMessage("Lease");
        scrnMsg.billToCustCd_H1.setNameForMessage("New Bill To");
        scrnMsg.svcMemoRsnCd_H3.setNameForMessage("Reason Code");
        scrnMsg.xxChkBox_BS.setNameForMessage("Base");
        scrnMsg.xxChkBox_OR.setNameForMessage("Overage");
        scrnMsg.svcCmntTxt_H1.setNameForMessage("Notes");
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxScrItem34Txt_A1.setNameForMessage("Contract#");
            scrnMsg.A.no(i).serNum_A1.setNameForMessage("Serial#");
            // mod start 2016/02/29 CSA Defect#2684
            scrnMsg.A.no(i).mtrLbDescTxt_A1.setNameForMessage("Base/Overage");
            // mod end 2016/02/29 CSA Defect#2684
            scrnMsg.A.no(i).billToCustLocAddr_A1.setNameForMessage("Bill To");
            scrnMsg.A.no(i).billToCustLocAddr_A2.setNameForMessage("New Bill To");
            scrnMsg.A.no(i).xxGenlFldAreaTxt_A1.setNameForMessage("Return Message");
        }
    }
}
