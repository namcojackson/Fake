/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2500;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2500.NMAL2500CMsg;
import business.servlet.NMAL2500.common.NMAL2500CommonLogic;
import business.servlet.NMAL2500.constant.NMAL2500Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/19   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/03/01   Fujitsu         H.Ikeda         Update          QC#4532
 * 2017/02/23   Fujitsu         K.Ishizuka      Update          QC#16481
 *</pre>
 */
public class NMAL2500_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2500BMsg scrnMsg = (NMAL2500BMsg) bMsg;

        NMAL2500CMsg bizMsg = new NMAL2500CMsg();
        bizMsg.setBusinessID(NMAL2500Constant.BIZ_ID);
        bizMsg.setFunctionCode(NMAL2500Constant.FUNCTION_CD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2500BMsg scrnMsg = (NMAL2500BMsg) bMsg;
        NMAL2500CMsg bizMsg = (NMAL2500CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL2500CommonLogic.initialControlScreen(this, scrnMsg);

        S21UserInfo userInfo = getContextUserInfo();
        String userId = userInfo.getUserId();
        S21UserProfileService profile = getUserProfileService();
        boolean authReadonly = profile.isFunctionGranted(userId, NMAL2500Constant.AUTH_READONLY);
        boolean authEdit = profile.isFunctionGranted(userId, NMAL2500Constant.AUTH_EDIT);
        // 2016/02/26 QC#4532 Mod Start
//        if (authReadonly) {
            this.setButtonProperties(NMAL2500Constant.BTN_CMN_BTN_SUBMIT[0], NMAL2500Constant.BTN_CMN_BTN_SUBMIT[1], NMAL2500Constant.BTN_CMN_BTN_SUBMIT[2], 0, null);
//        }
//        if (authEdit) {
//            this.setButtonProperties(NMAL2500Constant.BTN_CMN_BTN_SUBMIT[0], NMAL2500Constant.BTN_CMN_BTN_SUBMIT[1], NMAL2500Constant.BTN_CMN_BTN_SUBMIT[2], 1, null);
//        }
        // 2016/02/26 QC#4532 Mod End
        scrnMsg.xxDplyTab.setValue(NMAL2500Constant.SHOW_HIERARCHY);
        scrnMsg.xxModeCd_P1.setValue(NMAL2500Constant.SEARCH_MODE_SHOW_HIERARCHY);
        scrnMsg.xxRadioBtn_H1.setValue(NMAL2500Constant.DISPLAY_ORG_LEVEL_CHILDREN_RESOURCE);

        scrnMsg.setFocusItem(scrnMsg.bizAreaOrgCd_P1);
    }

    @Override
    protected void setNameForMessage(EZDBMsg arg0) {
        NMAL2500BMsg scrnMsg = (NMAL2500BMsg) arg0;
        scrnMsg.orgNm_H2.setNameForMessage(NMAL2500Constant.NAME_FOR_MESSAGE_ORG_NM);
        scrnMsg.xxPsnNm_H3.setNameForMessage(NMAL2500Constant.NAME_FOR_MESSAGE_RESRC_NM);
        scrnMsg.psnCd_H3.setNameForMessage(NMAL2500Constant.NAME_FOR_MESSAGE_PSN_CD);
        scrnMsg.jobTtlCd_H3.setNameForMessage(NMAL2500Constant.NAME_FOR_MESSAGE_JOB_TTL_CD);
        scrnMsg.hrTtlNm_H3.setNameForMessage(NMAL2500Constant.NAME_FOR_MESSAGE_JOB_TTL_NM);
        scrnMsg.psnNum_H3.setNameForMessage(NMAL2500Constant.NAME_FOR_MESSAGE_RESRC_NUM);
        scrnMsg.orgNm_H3.setNameForMessage(NMAL2500Constant.NAME_FOR_MESSAGE_NTRTY_NM);
        // MOD START S21_NA QC16481
        scrnMsg.effFromDt_FR.setNameForMessage(NMAL2500Constant.NAME_FOR_MESSAGE_EFF_FROM_DT);
        scrnMsg.effThruDt_FR.setNameForMessage(NMAL2500Constant.NAME_FOR_MESSAGE_EFF_THRU_DT);
        scrnMsg.effFromDt_TO.setNameForMessage(NMAL2500Constant.NAME_FOR_MESSAGE_EFF_FROM_DT);
        scrnMsg.effThruDt_TO.setNameForMessage(NMAL2500Constant.NAME_FOR_MESSAGE_EFF_THRU_DT);
        // MOD END S21_NA QC#16481
        scrnMsg.effThruDt_H4.setNameForMessage(NMAL2500Constant.NAME_FOR_MESSAGE_EFF_THRU_DT);
    }

}
