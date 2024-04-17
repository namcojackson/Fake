/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2610;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2610.NMAL2610CMsg;
import business.servlet.NMAL2610.common.NMAL2610CommonLogic;
import business.servlet.NMAL2610.constant.NMAL2610Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/03/02   Fujitsu         M.suzuki        Update          CSA-QC#4553
 * 2016/08/29   SRAA            Y.Chen          Update          QC#7966
 * 2018/09/21   Fujitsu         S.Kosaka        Update          QC#27726
 *</pre>
 */
public class NMAL2610_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2610BMsg scrnMsg = (NMAL2610BMsg) bMsg;

        Object[] params = (Object[]) getArgForSubScreen();
        if (params != null && params.length > 0) {
            EZDBStringItem param0 = (EZDBStringItem) params[NMAL2610Constant.POP_PAR_0];
            ZYPEZDItemValueSetter.setValue(scrnMsg.orgCd_H1, param0);
        }

        if (NMAL2610Constant.EVENT_NAME_LINK_COPY_TERRITORY.equals(ctx.getEventName())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxEventFlgTxt, NMAL2610Constant.LINK_COPY_TERRITORY);
        }

        NMAL2610CMsg bizMsg = new NMAL2610CMsg();
        bizMsg.setBusinessID(NMAL2610Constant.BIZ_ID);
        bizMsg.setFunctionCode(NMAL2610Constant.FUNCTION_CD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2610BMsg scrnMsg = (NMAL2610BMsg) bMsg;
        NMAL2610CMsg bizMsg  = (NMAL2610CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL2610CommonLogic.initialControlScreen(this, scrnMsg);

        S21UserInfo userInfo = getContextUserInfo();
        String userId = userInfo.getUserId();
        S21UserProfileService profile = getUserProfileService();
        boolean authEdit = profile.isFunctionGranted(userId, NMAL2610Constant.FUNCID_UPD);

        if (authEdit) {
            this.setButtonProperties(NMAL2610Constant.BTN_CMN_BTN_SUBMIT[0], NMAL2610Constant.BTN_CMN_BTN_SUBMIT[1], NMAL2610Constant.BTN_CMN_BTN_SUBMIT[2], 1, null);
        } else {
            this.setButtonProperties(NMAL2610Constant.BTN_CMN_BTN_SUBMIT[0], NMAL2610Constant.BTN_CMN_BTN_SUBMIT[1], NMAL2610Constant.BTN_CMN_BTN_SUBMIT[2], 0, null);
        }

        if (NMAL2610Constant.LINK_COPY_TERRITORY.equals(scrnMsg.xxEventFlgTxt.getValue())) {
            scrnMsg.xxDplyTab.setValue(NMAL2610Constant.TAB_TERRITORY_RULES);
            NMAL2610CommonLogic.initialCopyTerritory(scrnMsg);
        } else {
            scrnMsg.xxDplyTab.setValue(NMAL2610Constant.TAB_TERRITORY);
        }

        // 2018/09/21 QC#27726,ADD Add Start
        NMAL2610CommonLogic.setAllBGWithClear(scrnMsg);
        NMAL2610CommonLogic.setAddDelButton(this, scrnMsg);
        // 2018/09/21 QC#27726,ADD Add End

        scrnMsg.setFocusItem(scrnMsg.orgNm_H1);
    }

    @Override
    protected void setNameForMessage(EZDBMsg arg0) {

        NMAL2610BMsg scrnMsg = (NMAL2610BMsg) arg0;

        scrnMsg.orgCd_H1.setNameForMessage(NMAL2610Constant.NAME_FOR_MESSAGE_ORG_CD);
        scrnMsg.bizAreaOrgCd_P1.setNameForMessage(NMAL2610Constant.NAME_FOR_MESSAGE_BIZ_AREA_ORG_CD);
        scrnMsg.trtyTpCd_P1.setNameForMessage(NMAL2610Constant.NAME_FOR_MESSAGE_TRTY_TP_CD);
        scrnMsg.orgNm_H1.setNameForMessage(NMAL2610Constant.NAME_FOR_MESSAGE_ORG_NM);
        scrnMsg.orgTierCd_P1.setNameForMessage(NMAL2610Constant.NAME_FOR_MESSAGE_ORG_TIER_CD);
        scrnMsg.orgDescTxt_H1.setNameForMessage(NMAL2610Constant.NAME_FOR_MESSAGE_ORG_DESC_TXT);
        scrnMsg.trtyGrpTpCd_P1.setNameForMessage(NMAL2610Constant.NAME_FOR_MESSAGE_TRTY_GRP_TP_CD);
        scrnMsg.effFromDt_H1.setNameForMessage(NMAL2610Constant.NAME_FOR_MESSAGE_EFF_FROM_DT);
        scrnMsg.effThruDt_H1.setNameForMessage(NMAL2610Constant.NAME_FOR_MESSAGE_EFF_THRU_DT);
        // QC#7966
        // scrnMsg.orgNm_F1.setNameForMessage(NMAL2610Constant.NAME_FOR_MESSAGE_ORG_NM_FILTER);
        scrnMsg.trtyRuleFromValTxt_F1.setNameForMessage(NMAL2610Constant.NAME_FOR_MESSAGE_TRTY_RULE_FROM_VAL_TXT);
        scrnMsg.trtyRuleThruValTxt_F1.setNameForMessage(NMAL2610Constant.NAME_FOR_MESSAGE_TRTY_RULE_THRU_VAL_TXT);

        // 2016/03/02 S21_NA#4553 Add Start --------------
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).orgNm_A1.setNameForMessage(NMAL2610Constant.NAME_FOR_MESSAGE_ORG_NM_A1);
            scrnMsg.A.no(i).effFromDt_A1.setNameForMessage(NMAL2610Constant.NAME_FOR_MESSAGE_EFF_FROM_DT);
            scrnMsg.A.no(i).effThruDt_A1.setNameForMessage(NMAL2610Constant.NAME_FOR_MESSAGE_EFF_THRU_DT);
        }

        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).orgNm_B1.setNameForMessage(NMAL2610Constant.NAME_FOR_MESSAGE_ORG_NM_B1);
            scrnMsg.B.no(i).effFromDt_B1.setNameForMessage(NMAL2610Constant.NAME_FOR_MESSAGE_EFF_FROM_DT);
            scrnMsg.B.no(i).effThruDt_B1.setNameForMessage(NMAL2610Constant.NAME_FOR_MESSAGE_EFF_THRU_DT);
        }

        for (int i = 0; i < scrnMsg.C.length(); i++) {
            scrnMsg.C.no(i).trtyRuleTpCd_P1.setNameForMessage(NMAL2610Constant.NAME_FOR_MESSAGE_TRTY_RULE_TP_CD);
            scrnMsg.C.no(i).trtyRuleOprdTpCd_P1.setNameForMessage(NMAL2610Constant.NAME_FOR_MESSAGE_TRTY_RULE_OPRD_TP_CD);
            scrnMsg.C.no(i).trtyRuleFromValTxt_C1.setNameForMessage(NMAL2610Constant.NAME_FOR_MESSAGE_TRTY_RULE_FROM_VAL_TXT);
            scrnMsg.C.no(i).trtyRuleThruValTxt_C1.setNameForMessage(NMAL2610Constant.NAME_FOR_MESSAGE_TRTY_RULE_THRU_VAL_TXT);
            scrnMsg.C.no(i).trtyRuleLogicTpCd_P1.setNameForMessage(NMAL2610Constant.NAME_FOR_MESSAGE_TRTY_RULE_LOGIC_TP_CD);

            scrnMsg.C.no(i).effFromDt_C1.setNameForMessage(NMAL2610Constant.NAME_FOR_MESSAGE_EFF_FROM_DT);
            scrnMsg.C.no(i).effThruDt_C1.setNameForMessage(NMAL2610Constant.NAME_FOR_MESSAGE_EFF_THRU_DT);
        }

        for (int i = 0; i < scrnMsg.D.length(); i++) {
            scrnMsg.D.no(i).xxPsnNm_D1.setNameForMessage(NMAL2610Constant.NAME_FOR_MESSAGE_XX_PSN_NM);
            scrnMsg.D.no(i).psnCd_D1.setNameForMessage(NMAL2610Constant.NAME_FOR_MESSAGE_PSN_CD);
            scrnMsg.D.no(i).acctTeamRoleTpCd_P1.setNameForMessage(NMAL2610Constant.NAME_FOR_MESSAGE_ACCT_TEAM_ROLE_TP_CD);

            scrnMsg.D.no(i).effFromDt_D1.setNameForMessage(NMAL2610Constant.NAME_FOR_MESSAGE_EFF_FROM_DT);
            scrnMsg.D.no(i).effThruDt_D1.setNameForMessage(NMAL2610Constant.NAME_FOR_MESSAGE_EFF_THRU_DT);
        }
        // 2016/03/02 S21_NA#4553 Add Start --------------

    }

}
