/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLAL2040;

import static business.servlet.NLAL2040.constant.NLAL2040Constant.ACT_REC_ONLY;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.AGE_FROM;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.AGE_TO;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.BIZ_APP_ID;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.BTN_ADD_LINE;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.BTN_CMN_BTN_1;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.BTN_CMN_BTN_10;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.BTN_CMN_BTN_2;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.BTN_CMN_BTN_3;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.BTN_CMN_BTN_4;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.BTN_CMN_BTN_5;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.BTN_CMN_BTN_6;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.BTN_CMN_BTN_7;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.BTN_CMN_BTN_8;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.BTN_CMN_BTN_9;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.CFS;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.DT_FROM;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.DT_TO;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.FUNC_EDIT;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.LINE_CHK;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.MCD_TYPE;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.METER_FROM;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.METER_TO;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.MODEL;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.MODEL_NM;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.PCD;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.SPEED_SEG;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.SWH;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLAL2040.NLAL2040CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Business ID : NLAL2040 MODELS-CLICKS Inventory Valuation Entry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/18/2016   CITS            T.Kikuhara      Create          N/A
 *</pre>
 */
public class NLAL2040_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_APP_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NLAL2040BMsg scrnMsg = (NLAL2040BMsg) bMsg;
        NLAL2040CMsg bizMsg = new NLAL2040CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NLAL2040BMsg scrnMsg = (NLAL2040BMsg) bMsg;
        NLAL2040CMsg bizMsg  = (NLAL2040CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // Controll Screen By Authority
        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_APP_ID);
        boolean registAutorityFlg = false;
        for (String function : funcList) {
            if (FUNC_EDIT.equals(function)) {
                registAutorityFlg = true;
                break;
            }
        }

        setButtonProperties(BTN_CMN_BTN_1[0], BTN_CMN_BTN_1[1], BTN_CMN_BTN_1[2], 0, null);
        if (registAutorityFlg) {
            // Activate Submit Button
            setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 1, null);
        } else {
            setButtonProperties(BTN_CMN_BTN_2[0], BTN_CMN_BTN_2[1], BTN_CMN_BTN_2[2], 0, null);
        }
        setButtonProperties(BTN_CMN_BTN_3[0], BTN_CMN_BTN_3[1], BTN_CMN_BTN_3[2], 0, null);
        setButtonProperties(BTN_CMN_BTN_4[0], BTN_CMN_BTN_4[1], BTN_CMN_BTN_4[2], 0, null);
        setButtonProperties(BTN_CMN_BTN_5[0], BTN_CMN_BTN_5[1], BTN_CMN_BTN_5[2], 0, null);
        setButtonProperties(BTN_CMN_BTN_6[0], BTN_CMN_BTN_6[1], BTN_CMN_BTN_6[2], 0, null);
        setButtonProperties(BTN_CMN_BTN_7[0], BTN_CMN_BTN_7[1], BTN_CMN_BTN_7[2], 0, null);
        setButtonProperties(BTN_CMN_BTN_8[0], BTN_CMN_BTN_8[1], BTN_CMN_BTN_8[2], 1, null);
        setButtonProperties(BTN_CMN_BTN_9[0], BTN_CMN_BTN_9[1], BTN_CMN_BTN_9[2], 0, null);
        setButtonProperties(BTN_CMN_BTN_10[0], BTN_CMN_BTN_10[1], BTN_CMN_BTN_10[2], 1, null);
        setButtonProperties(BTN_ADD_LINE[0],BTN_ADD_LINE[1], BTN_ADD_LINE[2], 1, null);

        scrnMsg.A.no(scrnMsg.A.getValidCount() - 1).svcSegDescTxt_A1.setInputProtected(true);
        scrnMsg.setFocusItem(scrnMsg.t_MdlNm);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NLAL2040BMsg scrnMsg = (NLAL2040BMsg) bMsg;
        scrnMsg.t_MdlNm.setNameForMessage(MODEL_NM);
        scrnMsg.xxChkBox.setNameForMessage(ACT_REC_ONLY);
        scrnMsg.svcSegCd_PS.setNameForMessage(SPEED_SEG);
        scrnMsg.coaMdseTpCd_PS.setNameForMessage(MCD_TYPE);
        scrnMsg.coaProdCd_PS.setNameForMessage(PCD);
        scrnMsg.effFromDt.setNameForMessage(DT_FROM);
        scrnMsg.effThruDt.setNameForMessage(DT_TO);
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxChkBox_A1.setNameForMessage(LINE_CHK);
            scrnMsg.A.no(i).effFromDt_A1.setNameForMessage(DT_FROM);
            scrnMsg.A.no(i).effThruDt_A1.setNameForMessage(DT_TO);
            scrnMsg.A.no(i).t_MdlNm_A1.setNameForMessage(MODEL);
            scrnMsg.A.no(i).svcSegDescTxt_A1.setNameForMessage(SPEED_SEG);
            scrnMsg.A.no(i).fromMtrCnt_A1.setNameForMessage(METER_FROM);
            scrnMsg.A.no(i).toMtrCnt_A1.setNameForMessage(METER_TO);
            scrnMsg.A.no(i).fromElpsMthAot_A1.setNameForMessage(AGE_FROM);
            scrnMsg.A.no(i).toElpsMthAot_A1.setNameForMessage(AGE_TO);
            scrnMsg.A.no(i).rtlSwhCd_AS.setNameForMessage(SWH);
            scrnMsg.A.no(i).thirdPtyDspTpCd_AS.setNameForMessage(CFS);
        }
    }
}
