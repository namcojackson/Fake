/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2520;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2520.NMAL2520CMsg;
import business.servlet.NMAL2520.common.NMAL2520CommonLogic;
import business.servlet.NMAL2520.constant.NMAL2520Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2015/11/10   Fujitsu         K.Koitabashi    Update          N/A
 * 2016/03/03   Fujitsu         W.Honda         Update          CSA-QC#4553
 * 2017/06/14   Hitachi         J.Kim           Update          QC#18924
 *</pre>
 */
public class NMAL2520_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2520BMsg scrnMsg = (NMAL2520BMsg) bMsg;

        Object[] params = (Object[]) getArgForSubScreen();
        if (params != null && params.length > 0) {
            setParams(params, scrnMsg);
        }

        if (NMAL2520Constant.NMAL2500_EVENT_NAME_ADD_PARENT.equals(ctx.getEventName())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxEventFlgTxt, NMAL2520Constant.ADD_CHILD_FROM_NMAL2500);
        }

        NMAL2520CMsg bizMsg = new NMAL2520CMsg();
        bizMsg.setBusinessID(NMAL2520Constant.BIZ_ID);
        bizMsg.setFunctionCode(NMAL2520Constant.FUNCTION_CD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2520BMsg scrnMsg = (NMAL2520BMsg) bMsg;
        NMAL2520CMsg bizMsg = (NMAL2520CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL2520CommonLogic.initialControlScreen(this, scrnMsg);

        S21UserInfo userInfo = getContextUserInfo();
        String userId = userInfo.getUserId();
        S21UserProfileService profile = getUserProfileService();
        boolean authReadonly = profile.isFunctionGranted(userId, NMAL2520Constant.AUTH_READONLY);
        boolean authEdit = profile.isFunctionGranted(userId, NMAL2520Constant.AUTH_EDIT);

        if (authReadonly) {
            this.setButtonProperties(NMAL2520Constant.BTN_CMN_BTN_SUBMIT[0], NMAL2520Constant.BTN_CMN_BTN_SUBMIT[1], NMAL2520Constant.BTN_CMN_BTN_SUBMIT[2], 0, null);
        }

        if (authEdit) {
            this.setButtonProperties(NMAL2520Constant.BTN_CMN_BTN_SUBMIT[0], NMAL2520Constant.BTN_CMN_BTN_SUBMIT[1], NMAL2520Constant.BTN_CMN_BTN_SUBMIT[2], 1, null);
        }

        scrnMsg.xxDplyTab.setValue(NMAL2520Constant.TAB_BUILD_HIERARCHY);

        // START 2017/06/14 J.Kim [QC#18924,ADD]
        NMAL2520CommonLogic.controlInsertDelete(this, scrnMsg);
        // END 2017/06/14 J.Kim [QC#18924,ADD]
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NMAL2520BMsg scrnMsg = (NMAL2520BMsg) bMsg;

        // Header
        scrnMsg.orgCd_H1.setNameForMessage(NMAL2520Constant.NAME_FOR_MESSAGE_ORGANIZATION_ID);
        scrnMsg.bizAreaOrgCd_P1.setNameForMessage(NMAL2520Constant.NAME_FOR_MESSAGE_BUSINESS_AREA);
        scrnMsg.lineBizTpCd_P1.setNameForMessage(NMAL2520Constant.NAME_FOR_MESSAGE_LINE_OF_BUSINESS);
        scrnMsg.orgNm_H1.setNameForMessage(NMAL2520Constant.NAME_FOR_MESSAGE_ORG_NM);
        scrnMsg.orgShortNm_H1.setNameForMessage(NMAL2520Constant.NAME_FOR_MESSAGE_ORGANIZATION_SHORT_NAME);
        scrnMsg.orgDescTxt_H1.setNameForMessage(NMAL2520Constant.NAME_FOR_MESSAGE_DESCRIPTION);
        scrnMsg.orgTierCd_P1.setNameForMessage(NMAL2520Constant.NAME_FOR_MESSAGE_TIER_LEVEL);
        scrnMsg.effFromDt_H1.setNameForMessage(NMAL2520Constant.NAME_FOR_MESSAGE_EFF_FROM_DT);
        scrnMsg.orgTierDescTxt_H1.setNameForMessage(NMAL2520Constant.NAME_FOR_MESSAGE_TIER_DESCRIPTION);
        scrnMsg.effThruDt_H1.setNameForMessage(NMAL2520Constant.NAME_FOR_MESSAGE_EFF_THRU_DT);
        scrnMsg.csrRgTpCd_P1.setNameForMessage(NMAL2520Constant.NAME_FOR_MESSAGE_CSR_REGION);
        scrnMsg.xxChkBox_H1.setNameForMessage(NMAL2520Constant.NAME_FOR_MESSAGE_AUTO_ESTIMATE);
        scrnMsg.effFromDt_H1.setNameForMessage(NMAL2520Constant.NAME_FOR_MESSAGE_EFF_FROM_DT);
        scrnMsg.effThruDt_H1.setNameForMessage(NMAL2520Constant.NAME_FOR_MESSAGE_EFF_THRU_DT);

        // 2016/03/03 CSA-QC#4553 Add Start
        // ### Build Hierarchy ###
        // Parent Organization / Team
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxChkBox_A1.setNameForMessage("Check Box");
            scrnMsg.A.no(i).orgCd_A1.setNameForMessage("Parent Organization ID");
            scrnMsg.A.no(i).orgNm_A1.setNameForMessage("Parent Organization Name");
            scrnMsg.A.no(i).effFromDt_A1.setNameForMessage("Parent Organization Start Date");
            scrnMsg.A.no(i).effThruDt_A1.setNameForMessage("Parent Organization End Date");
        }

        // Child Organization Team
        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).orgCd_B1.setNameForMessage("Child Organization ID");
            scrnMsg.B.no(i).orgNm_B1.setNameForMessage("Child Organization Name");
            scrnMsg.B.no(i).effFromDt_B1.setNameForMessage("Child Organization Start Date");
            scrnMsg.B.no(i).effThruDt_B1.setNameForMessage("Child Organization End Date");
        }

        // ### Research Assign ###
        // Resource Assignment(s)
        for (int i = 0; i < scrnMsg.C.length(); i++) {
            scrnMsg.C.no(i).xxChkBox_C1.setNameForMessage("Check Box");
            scrnMsg.C.no(i).psnNum_C1.setNameForMessage("Resource#");
            scrnMsg.C.no(i).orgFuncRoleTpCd_P1.setNameForMessage("Role");
            scrnMsg.C.no(i).psnLastNm_C1.setNameForMessage("Last Name");
            scrnMsg.C.no(i).psnFirstNm_C1.setNameForMessage("First Name");
            scrnMsg.C.no(i).asgCustFromNm_C1.setNameForMessage("Contract Assign Customer Name Starting From");
            scrnMsg.C.no(i).asgCustToNm_C1.setNameForMessage("Contract Assign Customer Name Starting To");
            scrnMsg.C.no(i).effFromDt_C1.setNameForMessage("Start Date");
            scrnMsg.C.no(i).effThruDt_C1.setNameForMessage("End Date");
        }
        // 2016/03/03 CSA-QC#4553 Add End
    }

    private void setParams(Object[] params, NMAL2520BMsg scrnMsg) {

        // Input Parameter.
        // Organization Name
        EZDBStringItem param0 = (EZDBStringItem) params[0];
        if (ZYPCommonFunc.hasValue(param0)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.orgNm_H1, param0);
        }

        // Business Area
        EZDBStringItem param1 = (EZDBStringItem) params[1];
        if (ZYPCommonFunc.hasValue(param1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.bizAreaOrgCd_P1, param1);
        }

        // Line Of Business
        EZDBStringItem param2 = (EZDBStringItem) params[2];
        if (ZYPCommonFunc.hasValue(param2)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.lineBizTpCd_P1, param2);
        }
    }
}
