/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2510;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2510.NMAL2510CMsg;
import business.servlet.NMAL2510.common.NMAL2510CommonLogic;
import business.servlet.NMAL2510.constant.NMAL2510Constant;

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
 * 2015/10/19   Fujitsu         K.Koitabashi    Update          N/A
 * 2016/02/08   Fujitsu         C.Yokoi         Update          CSA-QC#2869
 * 2016/03/03   Fujitsu         W.Honda         Update          CSA-QC#4553
 * 2016/05/24   SRAA            Y.Chen          Update          QC#7962
 * 2018/10/02   CITS            Y.Fujii         Update          QC#19408
 * 2019/06/27   Fujitsu         Mz.Takahashi    Update          QC#50847
 *</pre>
 */
public class NMAL2510_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2510BMsg scrnMsg = (NMAL2510BMsg) bMsg;

        Object[] params = (Object[]) getArgForSubScreen();
        if (params != null && params.length > 0) {
            EZDBStringItem param0 = (EZDBStringItem) params[0];
            ZYPEZDItemValueSetter.setValue(scrnMsg.psnNum_H1, param0);
            if (params.length > 1) {
                String param1 = (String) params[1];
                if (ZYPCommonFunc.hasValue(param1) && "NFAL0120".equals(param1)) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, NMAL2510Constant.TAB_REVENUE);
                } else {
                    scrnMsg.xxDplyTab.setValue(null);
                }
            }
        }

        NMAL2510CMsg bizMsg = new NMAL2510CMsg();
        bizMsg.setBusinessID(NMAL2510Constant.BIZ_ID);
        bizMsg.setFunctionCode(NMAL2510Constant.FUNCTION_CD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2510BMsg scrnMsg = (NMAL2510BMsg) bMsg;
        NMAL2510CMsg bizMsg = (NMAL2510CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // 2019/06/27 QC#50847 Mod Start 
        /*
        S21UserInfo userInfo = getContextUserInfo();
        String userId = userInfo.getUserId();
        S21UserProfileService profile = getUserProfileService();
        boolean authReadonly = profile.isFunctionGranted(userId, NMAL2510Constant.AUTH_READONLY);
        boolean authEdit = profile.isFunctionGranted(userId, NMAL2510Constant.AUTH_EDIT);

        if (authReadonly) {
            this.setButtonProperties(NMAL2510Constant.BTN_CMN_BTN_SUBMIT[0], NMAL2510Constant.BTN_CMN_BTN_SUBMIT[1], NMAL2510Constant.BTN_CMN_BTN_SUBMIT[2], 0, null);
        }

        if (authEdit) {
            this.setButtonProperties(NMAL2510Constant.BTN_CMN_BTN_SUBMIT[0], NMAL2510Constant.BTN_CMN_BTN_SUBMIT[1], NMAL2510Constant.BTN_CMN_BTN_SUBMIT[2], 1, null);
        }
        */

        if (!ZYPCommonFunc.hasValue(scrnMsg.xxDplyTab)) {
            scrnMsg.xxDplyTab.setValue(NMAL2510Constant.TAB_HIERARCHY);
        }
        NMAL2510CommonLogic.commonControl(this, scrnMsg);

        S21UserInfo userInfo = getContextUserInfo();
        String userId = userInfo.getUserId();
        S21UserProfileService profile = getUserProfileService();
        boolean authReadonly = profile.isFunctionGranted(userId, NMAL2510Constant.AUTH_READONLY);
        boolean authEdit = profile.isFunctionGranted(userId, NMAL2510Constant.AUTH_EDIT);

        if (authReadonly) {
            this.setButtonProperties(NMAL2510Constant.BTN_CMN_BTN_SUBMIT[0], NMAL2510Constant.BTN_CMN_BTN_SUBMIT[1], NMAL2510Constant.BTN_CMN_BTN_SUBMIT[2], 0, null);
        }

        if (authEdit) {
            this.setButtonProperties(NMAL2510Constant.BTN_CMN_BTN_SUBMIT[0], NMAL2510Constant.BTN_CMN_BTN_SUBMIT[1], NMAL2510Constant.BTN_CMN_BTN_SUBMIT[2], 1, null);
        }
        // 2019/06/27 QC#50847 Mod End

        setAppFracDigit(scrnMsg);

        // 2016/02/08 CSA-QC#2869 Mod Start
        // if (ZYPCommonFunc.hasValue(scrnMsg.psnCd_H1.getValue())
        // && (ZYPCommonFunc.hasValue(scrnMsg.psnNum_H1.getValue()))) {
        NMAL2510CommonLogic.setProtectKeyValue(scrnMsg);
        // }
        // 2016/02/08 CSA-QC#2869 Mod End
        
        // QC#7962
        scrnMsg.setFocusItem(scrnMsg.psnNum_H1);
    }

    @Override
    protected void setNameForMessage(EZDBMsg arg0) {
        NMAL2510BMsg scrnMsg = (NMAL2510BMsg) arg0;

        scrnMsg.psnCd_H1.setNameForMessage(NMAL2510Constant.NAME_FOR_MESSAGE_PSN_CD);
        scrnMsg.psnNum_H1.setNameForMessage(NMAL2510Constant.NAME_FOR_MESSAGE_PSN_NUM);
        scrnMsg.psnFirstNm_H1.setNameForMessage(NMAL2510Constant.NAME_FOR_MESSAGE_PSN_FIRST_NM);
        scrnMsg.psnLastNm_H1.setNameForMessage(NMAL2510Constant.NAME_FOR_MESSAGE_PSN_LAST_NM);
        scrnMsg.psnTpCd_P1.setNameForMessage(NMAL2510Constant.NAME_FOR_MESSAGE_PSN_TP_CD);
        scrnMsg.effFromDt_H1.setNameForMessage(NMAL2510Constant.NAME_FOR_MESSAGE_EFF_FROM_DT);

        // 2016/03/03 CSA-QC#4553 Add Start
        scrnMsg.hrPsnCmpyNm_H1.setNameForMessage("Company Name");
        scrnMsg.jobTtlCd_H1.setNameForMessage("Job Code");
        scrnMsg.effThruDt_H1.setNameForMessage("Employment Date To");
        scrnMsg.hrTtlNm_H1.setNameForMessage("Job Name");
        scrnMsg.workTelNum_H1.setNameForMessage("Phone #");
        scrnMsg.hrSupvId_H1.setNameForMessage("Supervisor ID");
        scrnMsg.cellTelNum_H1.setNameForMessage("Mobile #");
        scrnMsg.hrSupvNm_H1.setNameForMessage("Supervisor Name");
        scrnMsg.emlAddr_H1.setNameForMessage("EMail");
        scrnMsg.lineBizTpCd_P1.setNameForMessage("Line of Business");
        scrnMsg.xxAllLineAddr_H1.setNameForMessage("Physical Location");
        // 2016/03/03 CSA-QC#4553 Add End

        // 2016/03/03 CSA-QC#4553 Add Start
        // ### Hierarchy Tab ###
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).bizAreaOrgCd_P2.setNameForMessage("Business Area");
            scrnMsg.A.no(i).orgFuncRoleTpCd_P2.setNameForMessage("Role");
            scrnMsg.A.no(i).orgNm_A2.setNameForMessage("Organization Name");
            scrnMsg.A.no(i).effFromDt_A2.setNameForMessage("Start Date");
            scrnMsg.A.no(i).effThruDt_A2.setNameForMessage("End Date");
        }

        // ### Territory Tab ###
        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).bizAreaOrgCd_P3.setNameForMessage("Business Area");
            scrnMsg.B.no(i).orgNm_B2.setNameForMessage("Territory Name");
            scrnMsg.B.no(i).acctTeamRoleTpCd_P3.setNameForMessage("Role");
            scrnMsg.B.no(i).orgDescTxt_B2.setNameForMessage("Territory Description");
            scrnMsg.B.no(i).effFromDt_B2.setNameForMessage("Start Date");
            scrnMsg.B.no(i).effThruDt_B2.setNameForMessage("End Date");
        }

        // ### Revenue Data ###
        scrnMsg.geoCd_C1.setNameForMessage("Sales Tax Geo Code");
        scrnMsg.dsInsdCtyLimitFlg_C1.setNameForMessage("Inside City LImits");
        for (int i = 0; i < scrnMsg.C.length(); i++) {
            scrnMsg.C.no(i).revAcctTpCd_P1.setNameForMessage("Revenue Account Type");
            scrnMsg.C.no(i).coaCmpyCd_C2.setNameForMessage("(GL) String Cmpy");
            scrnMsg.C.no(i).coaExtnCd_C2.setNameForMessage("(GL) String BU");
            scrnMsg.C.no(i).coaBrCd_C2.setNameForMessage("(GL) String BR");
            scrnMsg.C.no(i).coaCcCd_C2.setNameForMessage("(GL) String CC");
            scrnMsg.C.no(i).effFromDt_C2.setNameForMessage("Start Date");
            scrnMsg.C.no(i).effThruDt_C2.setNameForMessage("End Date");
            scrnMsg.C.no(i).xxChkBox_C3.setNameForMessage("Manual Update Flag");

        }

        // ### Miscellaneous Tab ###
        scrnMsg.moveOrdLimitAmt_D1.setNameForMessage("Parts Request Limit");
        scrnMsg.tmZoneCd_P1.setNameForMessage("Time Zone");
        scrnMsg.costPerHourAmt_D1.setNameForMessage("Cost per Hour");
        scrnMsg.ctacPsnPk_D1.setNameForMessage("Supplier Contact");
        scrnMsg.xxPsnNm_D1.setNameForMessage("Supplier Contact");
        // 2016/03/03 CSA-QC#4553 Add End
    }

    private void setAppFracDigit(NMAL2510BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.moveOrdLimitAmt_D1.setAppFracDigit(2);
            scrnMsg.costPerHourAmt_D1.setAppFracDigit(2);
        }
    }
}
