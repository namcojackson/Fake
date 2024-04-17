/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2830;

import static business.servlet.NMAL2830.constant.NMAL2830Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2830.NMAL2830CMsg;
import business.servlet.NMAL2830.common.NMAL2830CommonLogic;

import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NMAL2830_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/09   Fujitsu         T.Ogura         Create          N/A
 *</pre>
 */
public class NMAL2830_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2830BMsg scrnMsg = (NMAL2830BMsg) bMsg;
        NMAL2830CMsg bizMsg = new NMAL2830CMsg();

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2830BMsg scrnMsg = (NMAL2830BMsg) bMsg;
        NMAL2830CMsg bizMsg = (NMAL2830CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL2830CommonLogic.initCmnBtnProp(this);
        NMAL2830CommonLogic.controlScreenFields(getUserProfileService(), this, scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.xxFromDt);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NMAL2830BMsg scrnMsg = (NMAL2830BMsg) bMsg;

        // Header
        scrnMsg.srchOptPk_S.setNameForMessage("Saved Search Options");
        scrnMsg.srchOptNm_S.setNameForMessage("Search Option Name");
        scrnMsg.xxFromDt.setNameForMessage("Prospects created from");
        scrnMsg.dsAcctNm.setNameForMessage("Prospect Name");
        scrnMsg.fill65Txt_RN.setNameForMessage("Resource Name ");
        scrnMsg.orgNm_TN.setNameForMessage("Territory Name");
        scrnMsg.xxTpCd_D.setNameForMessage("Display Territory Visibility ");
        scrnMsg.xxToDt.setNameForMessage("Prospects created to");
        scrnMsg.dsXrefAcctCd.setNameForMessage("Prospect #");
        scrnMsg.psnCd.setNameForMessage("Employee ID");
        scrnMsg.orgNm_ON.setNameForMessage("Organization Name");
        scrnMsg.xxChkBox_RT.setNameForMessage("Real Time Inquiry");
        scrnMsg.xxAllLineAddr.setNameForMessage("Location Address");
        scrnMsg.ctyAddr.setNameForMessage("Location City");
        scrnMsg.stCd.setNameForMessage("Location State");
        scrnMsg.postCd.setNameForMessage("Location Postal Code");
        scrnMsg.dsAcctNum.setNameForMessage("Account #");
        scrnMsg.xxPageShowCurNum.setNameForMessage(S21BatchSearchPagenationScrnSupport.getCurrentPageFieldName());

        // Detail
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            // Detail（Prospect）
            scrnMsg.A.no(i).locNum_M.setNameForMessage("Merge To");
            scrnMsg.A.no(i).dsAcctNum_A1.setNameForMessage("Account #");
            scrnMsg.A.no(i).locNum_A1.setNameForMessage("Location #");
            scrnMsg.A.no(i).dsXrefAcctCd_A1.setNameForMessage("Prospect #");
            scrnMsg.A.no(i).dsAcctTpNm_A1.setNameForMessage("Account Category");
            scrnMsg.A.no(i).dsAcctNm_A1.setNameForMessage("Account Name");
            scrnMsg.A.no(i).xxAllLineAddr_A1.setNameForMessage("Address");
            scrnMsg.A.no(i).ctyAddr_A1.setNameForMessage("City");
            scrnMsg.A.no(i).stCd_A1.setNameForMessage("State");
            scrnMsg.A.no(i).postCd_A1.setNameForMessage("Postal Code");
            scrnMsg.A.no(i).xxAsgTrtyNm_A1.setNameForMessage("Assigned Territory");
            scrnMsg.A.no(i).xxChkBox_E1.setNameForMessage("Exact Match");
            scrnMsg.A.no(i).xxChkBox_P1.setNameForMessage("Partial Match");
            scrnMsg.A.no(i).xxChkBox_D1.setNameForMessage("Duns Match");
            // Detail（Requested Merge To）
            scrnMsg.A.no(i).dsAcctNum_A2.setNameForMessage("Account #");
            scrnMsg.A.no(i).locNum_A2.setNameForMessage("Location #");
            scrnMsg.A.no(i).dsXrefAcctCd_A2.setNameForMessage("Prospect #");
            scrnMsg.A.no(i).dsAcctTpNm_A2.setNameForMessage("Account Category");
            scrnMsg.A.no(i).dsAcctNm_A2.setNameForMessage("Account Name");
            scrnMsg.A.no(i).xxAllLineAddr_A2.setNameForMessage("Address");
            scrnMsg.A.no(i).ctyAddr_A2.setNameForMessage("City");
            scrnMsg.A.no(i).stCd_A2.setNameForMessage("State");
            scrnMsg.A.no(i).postCd_A2.setNameForMessage("Postal Code");
            scrnMsg.A.no(i).xxAsgTrtyNm_A2.setNameForMessage("Assigned Territory");
            scrnMsg.A.no(i).xxChkBox_E2.setNameForMessage("Exact Match");
            scrnMsg.A.no(i).xxChkBox_P2.setNameForMessage("Partial Match");
            scrnMsg.A.no(i).xxChkBox_D2.setNameForMessage("Duns Match");
            // Detail（Duplicate）
            scrnMsg.A.no(i).xxChkBox_M.setNameForMessage("Merge To");
            scrnMsg.A.no(i).xxChkBox_AM.setNameForMessage("All Merge To");
            scrnMsg.A.no(i).dsAcctNum_A3.setNameForMessage("Account #");
            scrnMsg.A.no(i).locNum_A3.setNameForMessage("Location #");
            scrnMsg.A.no(i).dsXrefAcctCd_A3.setNameForMessage("Prospect #");
            scrnMsg.A.no(i).dsAcctTpNm_A3.setNameForMessage("Account Category");
            scrnMsg.A.no(i).dsAcctNm_A3.setNameForMessage("Account Name");
            scrnMsg.A.no(i).xxAllLineAddr_A3.setNameForMessage("Address");
            scrnMsg.A.no(i).ctyAddr_A3.setNameForMessage("City");
            scrnMsg.A.no(i).stCd_A3.setNameForMessage("State");
            scrnMsg.A.no(i).postCd_A3.setNameForMessage("Postal Code");
            scrnMsg.A.no(i).xxAsgTrtyNm_A3.setNameForMessage("Assigned Territory");
            scrnMsg.A.no(i).xxChkBox_E3.setNameForMessage("Exact Match");
            scrnMsg.A.no(i).xxChkBox_P3.setNameForMessage("Partial Match");
            scrnMsg.A.no(i).xxChkBox_D3.setNameForMessage("Duns Match");
        }
    }
}
