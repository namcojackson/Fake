/**
 * <Pre>
 * 
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
package business.servlet.NMAL6710;

import static business.servlet.NMAL6710.constant.NMAL6710Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6710.NMAL6710CMsg;
import business.servlet.NMAL6710.common.NMAL6710CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 *  Account Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/20/2015   SRAA            K.Aratani       Create          N/A
 * 10/01/2015   Fujitsu         N.Sugiura       Update          CSA
 * 02/17/2016   Fujitsu         T.Murai         Update          CSA#2943
 * 03/01/2016   SRAA            Y.Chen          Update          QC#3290
 * 03/03/2016   SRAA            Y.Chen          Update          QC#4522
 * 03/14/2016   SRAA            Y.Chen          Update          QC#6190, QC#6191
 * 06/22/2016   SRAA            Y.Chen          Update          QC#6189
 * 07/06/2016   SRAA            Y.Chen          Update          QC#11456
 * 07/15/2020   Fujitsu         M.Ohno          Update          QC#57315
 *</pre>
 */
public class NMAL6710_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6710BMsg scrnMsg = (NMAL6710BMsg) bMsg;
        NMAL6710CMsg bizMsg = new NMAL6710CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_CODE_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6710BMsg scrnMsg = (NMAL6710BMsg) bMsg;
        NMAL6710CMsg bizMsg = (NMAL6710CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL6710CommonLogic.setButtonProperties_INIT(this, scrnMsg);
        NMAL6710CommonLogic.setCommonProperties(this, scrnMsg);

        S21UserInfo userInfo = getContextUserInfo();
        String userId = userInfo.getUserId();
        S21UserProfileService profile = getUserProfileService();
        boolean authReadonly = profile.isFunctionGranted(userId, AUTH_READONLY);
        boolean authEdit = profile.isFunctionGranted(userId, AUTH_EDIT);
        boolean authDownload = profile.isFunctionGranted(userId, AUTH_DOWNLOAD);
        // 2020/07/14 QC#57315 Add Start
        boolean authDunsUpld = profile.isFunctionGranted(userId, AUTH_DUNS_UPLD);
        // 2020/07/14 QC#57315 Add End

        if (authReadonly) {
            this.setButtonEnabled(BTN_SEARCH[1], true);
            this.setButtonEnabled(BTN_ACCT_INFO[1], true);
            this.setButtonEnabled(BTN_LOC_INFO[1], true);
            this.setButtonEnabled(BTN_NOTE[1], true);
            this.setButtonEnabled(BTN_CTAC[1], true);
            this.setButtonEnabled(BTN_NEW_LOC_INFO[1], false);
        }

        if (authEdit || authDownload) {
            this.setButtonEnabled(BTN_SEARCH[1], true);
            this.setButtonEnabled(BTN_ACCT_INFO[1], true);
            this.setButtonEnabled(BTN_LOC_INFO[1], true);
            this.setButtonEnabled(BTN_NOTE[1], true);
            this.setButtonEnabled(BTN_CTAC[1], true);
            this.setButtonEnabled(BTN_NEW_LOC_INFO[1], true);
        }

        // 2020/07/14 QC#57315 Add Start
        if (authDunsUpld) {
            this.setButtonEnabled(BTN_DUNS_UPLD[1], true);
        } else {
            this.setButtonEnabled(BTN_DUNS_UPLD[1], false);
        }
        // 2020/07/14 QC#57315 Add End

        // QC#6189
        // scrnMsg.xxDplyTab.setValue("Hierarchy");
        scrnMsg.xxDplyTab.setValue(TAB_NM_QUICK);
        scrnMsg.xxAcctSrchModeCd_D1.setValue(SEARCH_MODE_CD_QUICK);
        // QC#11456
        // scrnMsg.xxAcctSrchDplyRelnCd_D4.setValue(DISP_RELN_ACCT_CD_ACCT);
        
        scrnMsg.setFocusItem(scrnMsg.dsAcctNm_01);
        scrnMsg.xxChkBox_01.setValue(ZYPConstant.CHKBOX_ON_Y);
        // 2016/02/17 CSA #2943 Add Start
        scrnMsg.dsAcctGrpDescTxt_DP.setInputProtected(true);
        // 2016/02/17 CSA #2943 Add End

        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected void setNameForMessage(EZDBMsg arg0) {
        NMAL6710BMsg scrnMsg = (NMAL6710BMsg) arg0;
        scrnMsg.xxAcctSrchModeCd_D1.setNameForMessage("Search Mode");
        scrnMsg.xxAcctSrchStsCd_D2.setNameForMessage("Status");
        scrnMsg.dsAcctNm_01.setNameForMessage("Account Name");
        scrnMsg.xxAllLineAddr_01.setNameForMessage("Address");
        // 02/17/2016 CSA#2943 Add Start
        scrnMsg.dsAcctTpCd_DP.setNameForMessage("Category");
        // 02/17/2016 CSA#2943 Add End
        scrnMsg.ctyAddr_01.setNameForMessage("City");
        scrnMsg.stCd_DP.setNameForMessage("State");
        scrnMsg.postCd_01.setNameForMessage("Postal Code");
        scrnMsg.dsAcctNum_01.setNameForMessage("Account#");
        scrnMsg.locNum_01.setNameForMessage("Location#");
        scrnMsg.dbaNm_01.setNameForMessage("DBA Name");
        scrnMsg.dsAcctLegalNm_01.setNameForMessage("Account Legal Name");
        // 02/17/2016 CSA#2943 Mod Start
        //scrnMsg.dsAcctGrpCd_DP.setNameForMessage("Account Group Name");
        scrnMsg.dsAcctGrpCd_DP.setNameForMessage("Account Group Code");
        scrnMsg.dsAcctGrpDescTxt_DP.setNameForMessage("Account Group Name");
        // 02/17/2016 CSA#2943 Mod End
        scrnMsg.dsAcctClsCd_DP.setNameForMessage("Account Classification");
        scrnMsg.dsLocNm_01.setNameForMessage("Location Name");
        scrnMsg.dsAcctDunsNum_01.setNameForMessage("DUNS#");
        scrnMsg.dsUltDunsNum_01.setNameForMessage("UDUNS#");
        scrnMsg.dsCustSicDescTxt_01.setNameForMessage("Industry");
        scrnMsg.ctacPsnLastNm_01.setNameForMessage("Contact Last Name");
        scrnMsg.ctacPsnFirstNm_01.setNameForMessage("Contact First Name");
        scrnMsg.ctacPsnTelNum_01.setNameForMessage("Contact Phone#");
        scrnMsg.dsXrefAcctTpCd_DP.setNameForMessage("Cross Reference Category");
        scrnMsg.dsXrefAcctCd_01.setNameForMessage("Cross Reference#");
        scrnMsg.dsXtrnlRefTxt_01.setNameForMessage("External Reference#");
        scrnMsg.dsAcctItrlFlg_C1.setNameForMessage("Internal / External");
        scrnMsg.xxAcctSrchDplyHrchCd_D3.setNameForMessage("Display Hierarchy Accounts");
        scrnMsg.xxAcctSrchDplyRelnCd_D4.setNameForMessage("Display Related Accounts");
        scrnMsg.dsAcctNm_RT.setNameForMessage("Related Account Name");
        scrnMsg.dsAcctNum_RT.setNameForMessage("Related Account#");
        scrnMsg.xxAllLineAddr_RT.setNameForMessage("Related Address");
        // QC#3290
        // QC#6191
        scrnMsg.xxChkBox_02.setNameForMessage("Display Inactive Location");
        scrnMsg.xxChkBox_03.setNameForMessage("Display Inactive Bill to / Ship to");
        // QC#4522
        // QC#6190
        scrnMsg.xxChkBox_01.setNameForMessage("Display Addresses");
        scrnMsg.billToCustCd_01.setNameForMessage("Bill To Code");
        scrnMsg.shipToCustCd_01.setNameForMessage("Ship To Code");
    }
}
