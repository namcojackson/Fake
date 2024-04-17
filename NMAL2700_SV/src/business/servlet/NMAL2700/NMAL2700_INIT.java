/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2700;

import static business.servlet.NMAL2700.constant.NMAL2700Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2700.NMAL2700CMsg;
import business.servlet.NMAL2700.common.NMAL2700CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NMAL2700_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/05   Fujitsu         M.Suzuki        Create          N/A
 * 2016/03/08   Fujitsu         M.Suzuki        Create          N/A
 * 2016/05/03   SRAA            Y.Chen          Update          QC#5575
 * 2019/01/18   Fujitsu         W.Honda         Update          QC#29889
 *</pre>
 */
public class NMAL2700_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL2700BMsg scrnMsg = (NMAL2700BMsg) bMsg;
        NMAL2700CMsg bizMsg = new NMAL2700CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL2700BMsg scrnMsg = (NMAL2700BMsg) bMsg;
        NMAL2700CMsg bizMsg = (NMAL2700CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // QC#5575
        setAppFracDigit(scrnMsg);
        NMAL2700CommonLogic.initCmnBtnProp(this);
        NMAL2700CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.firstOrgCd);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NMAL2700BMsg scrnMsg = (NMAL2700BMsg) bMsg;

        //TODO [Template]
        scrnMsg.firstOrgCd.setNameForMessage("Business Area");
        scrnMsg.orgFuncRoleTpCd.setNameForMessage("Role Code");
        scrnMsg.orgFuncRoleTpNm.setNameForMessage("Role Name");
        scrnMsg.orgFuncRoleTpDescTxt.setNameForMessage("Role Description");
        scrnMsg.mgrFlg.setNameForMessage("Manager");
        scrnMsg.spclFlg.setNameForMessage("Specialist");
        scrnMsg.equipFlg.setNameForMessage("Equipment");
        scrnMsg.cmsnFlg.setNameForMessage("Commisionable");
        scrnMsg.actvFlg.setNameForMessage("Display Inactive");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxChkBox_A.setNameForMessage("Check Box");
            scrnMsg.A.no(i).firstOrgCd_A.setNameForMessage("Business Area");
            scrnMsg.A.no(i).orgFuncRoleTpCd_A.setNameForMessage("Role Code");
            scrnMsg.A.no(i).orgFuncRoleTpNm_A.setNameForMessage("Role name");
            scrnMsg.A.no(i).orgFuncRoleTpDescTxt_A.setNameForMessage("Role Description");
            scrnMsg.A.no(i).actvFlg_A.setNameForMessage("Active");  //QC#4304 Mod
            scrnMsg.A.no(i).equipFlg_A.setNameForMessage("Equipment");
            scrnMsg.A.no(i).mgrFlg_A.setNameForMessage("Manager");
            scrnMsg.A.no(i).spclFlg_A.setNameForMessage("Specialist");
            scrnMsg.A.no(i).cmsnFlg_A.setNameForMessage("Commisionable");
            scrnMsg.A.no(i).adminFlg_A.setNameForMessage("Admin");
            scrnMsg.A.no(i).gesTpCd_A.setNameForMessage("Golden Eagle Specialist");
            // 2019/01/18 QC#29889 mod start
//            scrnMsg.A.no(i).apvlLimitAmt_A.setNameForMessage("Credit/Rebill Approval Limit");
            scrnMsg.A.no(i).apvlLimitAmt_A.setNameForMessage("Approval Limit");
            // 2019/01/18 QC#29889 mod end
            scrnMsg.A.no(i).slsRepFlg_A.setNameForMessage("Sales Rep");
            scrnMsg.A.no(i).asgContrFlg_A.setNameForMessage("Assignment Contract");
            scrnMsg.A.no(i).thirdPtyTechFlg_A.setNameForMessage("Third Party Tech");
            scrnMsg.A.no(i).techMstrReqFlg_A.setNameForMessage("Tech Master Request");
            scrnMsg.A.no(i).crmSlsExclFlg_A.setNameForMessage("SFDC Exclude");
            scrnMsg.A.no(i).crmSlsPrflNm_A.setNameForMessage("SFDC Profile Name");//QC#4304 Mod
        }
    }
    
    // QC#5575
    private void setAppFracDigit(NMAL2700BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).apvlLimitAmt_A.setAppFracDigit(2);
        }
    }
}
