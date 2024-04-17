/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2460;

import static business.servlet.NMAL2460.constant.NMAL2460Constant.BIZ_ID;
import static business.servlet.NMAL2460.constant.NMAL2460Constant.EZD_FUNC_CD_INQ;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2460.NMAL2460CMsg;
import business.servlet.NMAL2460.common.NMAL2460CommonLogic;

import com.canon.cusa.s21.framework.online.pagenation.S21SequentialSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/26   Hitachi         O.Okuma         Create          N/A
 *</pre>
 */
public class NMAL2460_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2460BMsg scrnMsg = (NMAL2460BMsg) bMsg;

        NMAL2460CMsg bizMsg = new NMAL2460CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2460BMsg scrnMsg = (NMAL2460BMsg) bMsg;
        NMAL2460CMsg bizMsg = (NMAL2460CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL2460CommonLogic.controlScreen(this, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.bizAreaOrgCd_H);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NMAL2460BMsg scrnMsg = (NMAL2460BMsg) bMsg;

        scrnMsg.xxPageShowCurNum_A.setNameForMessage(S21SequentialSearchPagenationScrnSupport.getCurrentPageFieldName());
        scrnMsg.xxPageShowCurNum_B.setNameForMessage(S21SequentialSearchPagenationScrnSupport.getCurrentPageFieldName());

        scrnMsg.bizAreaOrgCd_H.setNameForMessage("Business Area");
        scrnMsg.dsAcctTpCd_H.setNameForMessage("Account Category");
        scrnMsg.srchLyotTpCd_H.setNameForMessage("Aearch Results");
        scrnMsg.dsAcctNm_H.setNameForMessage("Account Name");
        scrnMsg.dsAcctNum_H.setNameForMessage("Account Number");
        scrnMsg.orgNm_H1.setNameForMessage("Territory Name");
        scrnMsg.trtyGrpTpCd_H.setNameForMessage("Territory Group");
        scrnMsg.fullPsnNm_H.setNameForMessage("Resource Name");
        scrnMsg.psnCd_H.setNameForMessage("Empleyee ID");
        scrnMsg.orgNm_H2.setNameForMessage("Organization Name");
        scrnMsg.lineBizTpCd_H.setNameForMessage("Source Business Unit");

        scrnMsg.xxCratDt_HF.setNameForMessage("Account Create From");
        scrnMsg.xxCratDt_HT.setNameForMessage("Account Create To");
        scrnMsg.xxLineCatgSrchTxt_H.setNameForMessage("Address");
        scrnMsg.locNum_H.setNameForMessage("Location ID");
        scrnMsg.ctyAddr_H.setNameForMessage("City");
        scrnMsg.stCd_H.setNameForMessage("State");
        scrnMsg.postCd_HF.setNameForMessage("Postal Code From");
        scrnMsg.postCd_HT.setNameForMessage("Postal Code To");

        scrnMsg.rqstRsltCmntTxt.setNameForMessage("Update Reason");

        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).orgNm_B1.setNameForMessage(scrnMsg.lineBizRoleTpNm_01.getValue() + " " + "Territory Name");
            scrnMsg.B.no(i).orgNm_B2.setNameForMessage(scrnMsg.lineBizRoleTpNm_02.getValue() + " " + "Territory Name");
            scrnMsg.B.no(i).orgNm_B3.setNameForMessage(scrnMsg.lineBizRoleTpNm_03.getValue() + " " + "Territory Name");
            scrnMsg.B.no(i).orgNm_B4.setNameForMessage(scrnMsg.lineBizRoleTpNm_04.getValue() + " " + "Territory Name");
            scrnMsg.B.no(i).orgNm_B5.setNameForMessage(scrnMsg.lineBizRoleTpNm_05.getValue() + " " + "Territory Name");
            scrnMsg.B.no(i).orgNm_B6.setNameForMessage(scrnMsg.lineBizRoleTpNm_06.getValue() + " " + "Territory Name");
            scrnMsg.B.no(i).orgNm_B7.setNameForMessage(scrnMsg.lineBizRoleTpNm_07.getValue() + " " + "Territory Name");
            scrnMsg.B.no(i).orgNm_B8.setNameForMessage(scrnMsg.lineBizRoleTpNm_08.getValue() + " " + "Territory Name");
            scrnMsg.B.no(i).orgNm_B9.setNameForMessage(scrnMsg.lineBizRoleTpNm_09.getValue() + " " + "Territory Name");
            scrnMsg.B.no(i).orgNm_BA.setNameForMessage(scrnMsg.lineBizRoleTpNm_10.getValue() + " " + "Territory Name");
            scrnMsg.B.no(i).orgNm_BB.setNameForMessage(scrnMsg.lineBizRoleTpNm_11.getValue() + " " + "Territory Name");
            scrnMsg.B.no(i).orgNm_BC.setNameForMessage(scrnMsg.lineBizRoleTpNm_12.getValue() + " " + "Territory Name");
            scrnMsg.B.no(i).orgNm_BD.setNameForMessage(scrnMsg.lineBizRoleTpNm_13.getValue() + " " + "Territory Name");
            scrnMsg.B.no(i).orgNm_BE.setNameForMessage(scrnMsg.lineBizRoleTpNm_14.getValue() + " " + "Territory Name");
            scrnMsg.B.no(i).orgNm_BF.setNameForMessage(scrnMsg.lineBizRoleTpNm_15.getValue() + " " + "Territory Name");
            scrnMsg.B.no(i).orgNm_BG.setNameForMessage(scrnMsg.lineBizRoleTpNm_16.getValue() + " " + "Territory Name");
            scrnMsg.B.no(i).orgNm_BH.setNameForMessage(scrnMsg.lineBizRoleTpNm_17.getValue() + " " + "Territory Name");
            scrnMsg.B.no(i).orgNm_BI.setNameForMessage(scrnMsg.lineBizRoleTpNm_18.getValue() + " " + "Territory Name");
            scrnMsg.B.no(i).orgNm_BJ.setNameForMessage(scrnMsg.lineBizRoleTpNm_19.getValue() + " " + "Territory Name");
            scrnMsg.B.no(i).orgNm_BK.setNameForMessage(scrnMsg.lineBizRoleTpNm_20.getValue() + " " + "Territory Name");
        }
    }
}
