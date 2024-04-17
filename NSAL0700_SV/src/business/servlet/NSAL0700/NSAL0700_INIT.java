/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0700;

import static business.servlet.NSAL0700.common.NSAL0700CommonLogic.initialControlScreen;
import static business.servlet.NSAL0700.constant.NSAL0700Constant.BUSINESS_ID;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBMsgArray;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0700.NSAL0700CMsg;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/04   Hitachi         K.Kasai         Create          N/A
 * 2016/07/08   Hitachi         M.Gotou         Update          QC#11598
 *</pre>
 */
public class NSAL0700_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0700BMsg scrnMsg = (NSAL0700BMsg) bMsg;

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

        NSAL0700CMsg bizMsg = new NSAL0700CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0700BMsg scrnMsg = (NSAL0700BMsg) bMsg;
        NSAL0700CMsg bizMsg = (NSAL0700CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        initialControlScreen(this, scrnMsg);
        // START 2016/07/08 M.Gotou [QC#11598, DEL]
//        ZYPTableUtil.selectAll(scrnMsg.A, "xxChkBox_A1", ZYPConstant.CHKBOX_ON_Y);
        // END 2016/07/08 M.Gotou [QC#11598, DEL]
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL0700BMsg scrnMsg = (NSAL0700BMsg) bMsg;
        scrnMsg.svcMemoRsnCd_H3.setNameForMessage("Reason Code");
        scrnMsg.svcCmntTxt_H1.setNameForMessage("Notes");
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxScrItem34Txt_A1.setNameForMessage("Contract#");
            scrnMsg.A.no(i).serNum_A1.setNameForMessage("Serial#");
            // mod start 2016/02/26 CSA Defect#2684
            scrnMsg.A.no(i).mtrLbDescTxt_A1.setNameForMessage("Base/Overage");
            // mod end 2016/02/26 CSA Defect#2684
            scrnMsg.A.no(i).dsContrCtrlStsNm_A1.setNameForMessage("Status");
            scrnMsg.A.no(i).contrVrsnEffFromDt_A1.setNameForMessage("Start Date");
            scrnMsg.A.no(i).contrVrsnEffThruDt_A1.setNameForMessage("End Date");
            scrnMsg.A.no(i).basePrcDealAmt_A1.setNameForMessage("Base Amt");
            scrnMsg.A.no(i).bllgCycleUomNm_A1.setNameForMessage("UOM");
            scrnMsg.A.no(i).nextBllgDt_A1.setNameForMessage("Next Sche");
            scrnMsg.A.no(i).xxGenlFldAreaTxt_A1.setNameForMessage("Return Message");
        }
    }
}
