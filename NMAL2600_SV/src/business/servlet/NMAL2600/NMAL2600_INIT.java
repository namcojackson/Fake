/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2600;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2600.NMAL2600CMsg;
import business.servlet.NMAL2600.common.NMAL2600CommonLogic;
import business.servlet.NMAL2600.constant.NMAL2600Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/01   Fujitsu         K.Koitabashi    Create          N/A
 *</pre>
 */
public class NMAL2600_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2600BMsg scrnMsg = (NMAL2600BMsg) bMsg;

        NMAL2600CMsg bizMsg = new NMAL2600CMsg();
        bizMsg.setBusinessID(NMAL2600Constant.BIZ_ID);
        bizMsg.setFunctionCode(NMAL2600Constant.FUNCTION_CD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2600BMsg scrnMsg = (NMAL2600BMsg) bMsg;
        NMAL2600CMsg bizMsg = (NMAL2600CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        S21UserInfo userInfo = getContextUserInfo();
        String userId = userInfo.getUserId();
        S21UserProfileService profile = getUserProfileService();
        boolean authReadonly = profile.isFunctionGranted(userId, NMAL2600Constant.AUTH_READONLY);
        boolean authEdit = profile.isFunctionGranted(userId, NMAL2600Constant.AUTH_EDIT);

        if (authReadonly) {
            this.setButtonProperties(NMAL2600Constant.BTN_CMN_BTN_SUBMIT[0], NMAL2600Constant.BTN_CMN_BTN_SUBMIT[1], NMAL2600Constant.BTN_CMN_BTN_SUBMIT[2], 0, null);
        }

        if (authEdit) {
            this.setButtonProperties(NMAL2600Constant.BTN_CMN_BTN_SUBMIT[0], NMAL2600Constant.BTN_CMN_BTN_SUBMIT[1], NMAL2600Constant.BTN_CMN_BTN_SUBMIT[2], 1, null);
        }

        NMAL2600CommonLogic.initialControlScreen(this, scrnMsg);
        NMAL2600CommonLogic.convertTreeInfo(scrnMsg, bizMsg.T);
    }

    @Override
    protected void setNameForMessage(EZDBMsg arg0) {

        NMAL2600BMsg scrnMsg = (NMAL2600BMsg) arg0;

        scrnMsg.bizAreaOrgCd_P1.setNameForMessage(NMAL2600Constant.NAME_FOR_MESSAGE_BIZ_AREA_ORG_CD);
        scrnMsg.trtyTpCd_P1.setNameForMessage(NMAL2600Constant.NAME_FOR_MESSAGE_TRTY_TP_CD);
        scrnMsg.orgNm_H1.setNameForMessage(NMAL2600Constant.NAME_FOR_MESSAGE_ORG_NM);
        scrnMsg.orgTierCd_P1.setNameForMessage(NMAL2600Constant.NAME_FOR_MESSAGE_ORG_TIER_CD);
        scrnMsg.xxPsnNm_H1.setNameForMessage(NMAL2600Constant.NAME_FOR_MESSAGE_PSN_NM);
        scrnMsg.trtyGrpTpCd_P1.setNameForMessage(NMAL2600Constant.NAME_FOR_MESSAGE_TRTY_GRP_CD);
        scrnMsg.psnCd_H1.setNameForMessage(NMAL2600Constant.NAME_FOR_MESSAGE_PSN_CD);
        scrnMsg.psnNum_H1.setNameForMessage(NMAL2600Constant.NAME_FOR_MESSAGE_PSN_NUM);
        scrnMsg.effFromDt_H1.setNameForMessage(NMAL2600Constant.NAME_FOR_MESSAGE_EFF_FROM_DT);

    }
}
