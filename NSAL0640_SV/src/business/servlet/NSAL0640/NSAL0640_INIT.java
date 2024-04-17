/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0640;

import static business.servlet.NSAL0640.common.NSAL0640CommonLogic.initialControlScreen;
import static business.servlet.NSAL0640.constant.NSAL0640Constant.BUSINESS_ID;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBMsgArray;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0640.NSAL0640CMsg;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Update Contract Branch/Representative
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/06   Hitachi         T.Tsuchida      Create          N/A
 * 2015/11/19   Hitachi         T.Tsuchida      Update          QC#883
 * 2016/03/28   Hitachi         M.Gotou         Update          QC#4702,4703,4915
 * 2016/04/01   Hitachi         M.Gotou         Update          QC#4916
 *</pre>
 */
public class NSAL0640_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0640BMsg scrnMsg = (NSAL0640BMsg) bMsg;

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

        NSAL0640CMsg bizMsg = new NSAL0640CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0640BMsg scrnMsg = (NSAL0640BMsg) bMsg;
        NSAL0640CMsg bizMsg = (NSAL0640CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        initialControlScreen(this, scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL0640BMsg scrnMsg = (NSAL0640BMsg) bMsg;
        scrnMsg.svcContrBrCd_H.setNameForMessage("New Branch Code");
        // mod start 2016/04/01 CSA Defect#4916
        //scrnMsg.svcContrBrDescTxt_H.setNameForMessage("New Branch Name");
        scrnMsg.xxDplyByCdNmCnctTxt_H.setNameForMessage("New Branch Name");
        // mod start 2016/04/01 CSA Defect#4916
        // mod start 2016/03/28 CSA Defect#4702,4703,4915
        //scrnMsg.tocCd_H.setNameForMessage("New Rep Code");
        //scrnMsg.tocNm_H.setNameForMessage("New Rep Name");
        scrnMsg.psnCd_H.setNameForMessage("New Rep Code");
        scrnMsg.xxPsnNm_H.setNameForMessage("New Rep Name");
        // mod end 2016/03/28 CSA Defect#4702,4703,4915
        scrnMsg.svcMemoRsnCd_H.setNameForMessage("Reason Code");
        scrnMsg.svcCmntTxt_H.setNameForMessage("Notes");
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxScrItem34Txt_A1.setNameForMessage("Contract#");
            scrnMsg.A.no(i).dsAcctNm_A1.setNameForMessage("Customer Name");
            scrnMsg.A.no(i).contrVrsnEffFromDt_A1.setNameForMessage("Contract Start Date");
            scrnMsg.A.no(i).contrVrsnEffThruDt_A1.setNameForMessage("Contract End Date");
            scrnMsg.A.no(i).xxGenlFldAreaTxt_A1.setNameForMessage("Current Branch");
            // mod start 2016/03/28 CSA Defect#4702,4703,4915
            //scrnMsg.A.no(i).tocNm_A1.setNameForMessage("Current Assigned Rep");
            scrnMsg.A.no(i).xxPsnNm_A1.setNameForMessage("Current Assigned Rep");
            scrnMsg.A.no(i).xxGenlFldAreaTxt_A2.setNameForMessage("New Branch");
            //scrnMsg.A.no(i).tocNm_A2.setNameForMessage("New Assigned Rep");
            scrnMsg.A.no(i).xxPsnNm_A2.setNameForMessage("New Assigned Rep");
            // mod end 2016/03/28 CSA Defect#4702,4703,4915
            scrnMsg.A.no(i).dsMsgTxt_A1.setNameForMessage("Return Message");
        }
    }
}
