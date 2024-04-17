/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2370;

import static business.servlet.NWAL2370.constant.NWAL2370Constant.BTN_CMN_APL;
import static business.servlet.NWAL2370.constant.NWAL2370Constant.BTN_CMN_APR;
import static business.servlet.NWAL2370.constant.NWAL2370Constant.BTN_CMN_CLR;
import static business.servlet.NWAL2370.constant.NWAL2370Constant.BTN_CMN_DEL;
import static business.servlet.NWAL2370.constant.NWAL2370Constant.BTN_CMN_DWL;
import static business.servlet.NWAL2370.constant.NWAL2370Constant.BTN_CMN_RJT;
import static business.servlet.NWAL2370.constant.NWAL2370Constant.BTN_CMN_RST;
import static business.servlet.NWAL2370.constant.NWAL2370Constant.BTN_CMN_RTN;
import static business.servlet.NWAL2370.constant.NWAL2370Constant.BTN_CMN_SAV;
import static business.servlet.NWAL2370.constant.NWAL2370Constant.BTN_CMN_SUB;
import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2370.NWAL2370CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/04/10   Fujitsu         N.Sugiura       Create          QC#10374
 * 2018/04/10   Fujitsu         W.Honda         Update          QC#10374
 *</pre>
 */
public class NWAL2370_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2370BMsg scrnMsg = (NWAL2370BMsg) bMsg;

        NWAL2370CMsg bizMsg = new NWAL2370CMsg();

        Object[] params = (Object[]) getArgForSubScreen();

        if (params instanceof Object[]) {

            if (ZYPCommonFunc.hasValue((EZDBStringItem) params[0])) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd, (EZDBStringItem) params[0]);
            }

            if (ZYPCommonFunc.hasValue((EZDBBigDecimalItem) params[1])) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrDtlPk, (EZDBBigDecimalItem) params[1]);
            }

            if (ZYPCommonFunc.hasValue((EZDBBigDecimalItem) params[2])) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsImptSvcConfigRefPk, (EZDBBigDecimalItem) params[2]);
            }
        }

        bizMsg.setBusinessID("NWAL2370");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2370BMsg scrnMsg = (NWAL2370BMsg) bMsg;
        NWAL2370CMsg bizMsg  = (NWAL2370CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        setControlFieldsForDigit(scrnMsg);

        initCmnBtnProp(this);
        setProtect(this, scrnMsg);
        scrnMsg.clearAllGUIAttribute("NWAL2370Scrn00");
        resetRowsBackgroundQuickMode(scrnMsg);

        if ("E".equals(scrnMsg.getMessageCode())) {
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NWAL2370BMsg scrnMsg = (NWAL2370BMsg) bMsg;

        scrnMsg.shellLineNum.setNameForMessage("Maintenance Shell#");
        scrnMsg.fromPerMthNum.setNameForMessage("Term From");
        scrnMsg.toPerMthNum.setNameForMessage("Term To");
        scrnMsg.termMthAot.setNameForMessage("Term Mon");
        scrnMsg.dsOrdPosnNum.setNameForMessage("Config#");
        scrnMsg.t_MdlNm.setNameForMessage("Model");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxLineNum_A1.setNameForMessage("Line#");
            scrnMsg.A.no(i).mdseCd_A1.setNameForMessage("Item#");
            scrnMsg.A.no(i).mdseDescShortTxt_A1.setNameForMessage("Description");
            scrnMsg.A.no(i).xxFreqCycleCnt_A1.setNameForMessage("# of Payments");
            scrnMsg.A.no(i).bllgCycleDescTxt_A1.setNameForMessage("Frequency");
            scrnMsg.A.no(i).xxTotAmt_PY.setNameForMessage("Rental Payment");
            scrnMsg.A.no(i).xxTotAmt_EQ.setNameForMessage("Rental Equip");
            scrnMsg.A.no(i).xxTotAmt_SV.setNameForMessage("Rental Service");
            scrnMsg.A.no(i).xxTotAmt_AC.setNameForMessage("Addl Charge");
            scrnMsg.A.no(i).prcListEquipConfigNm_A1.setNameForMessage("Bundle Name");
        }
    }

    /**
     * Set Control Fields For Digit
     * @param scrnMsg NWAL2300BMsg
     */
    private static void setControlFieldsForDigit(NWAL2370BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).xxTotAmt_PY.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            scrnMsg.A.no(i).xxTotAmt_EQ.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            scrnMsg.A.no(i).xxTotAmt_SV.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
            scrnMsg.A.no(i).xxTotAmt_AC.setAppFracDigit(scrnMsg.aftDeclPntDigitNum.getValueInt());
        }
    }

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    private static void initCmnBtnProp(S21CommonHandler handler) {
        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);
    }

    /**
     * Set Screen Protect
     * @param handler S21CommonHandler
     * @param scrnMsg NWAL2370BMsg
     * @param clearFlg boolean
     */
    private static void setProtect(S21CommonHandler handler, NWAL2370BMsg scrnMsg) {

        scrnMsg.shellLineNum.setInputProtected(true);
        scrnMsg.fromPerMthNum.setInputProtected(true);
        scrnMsg.toPerMthNum.setInputProtected(true);
        scrnMsg.termMthAot.setInputProtected(true);
        scrnMsg.dsOrdPosnNum.setInputProtected(true);
        scrnMsg.t_MdlNm.setInputProtected(true);

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxLineNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).mdseCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).mdseDescShortTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxFreqCycleCnt_A1.setInputProtected(true);
            scrnMsg.A.no(i).bllgCycleDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxTotAmt_PY.setInputProtected(true);
            scrnMsg.A.no(i).xxTotAmt_EQ.setInputProtected(true);
            scrnMsg.A.no(i).xxTotAmt_SV.setInputProtected(true);
            scrnMsg.A.no(i).xxTotAmt_AC.setInputProtected(true);
            scrnMsg.A.no(i).prcListEquipConfigNm_A1.setInputProtected(true);
        }
    }

    private static void resetRowsBackgroundQuickMode(NWAL2370BMsg scrnMsg) {
        String tblId = "A";
        NWAL2370_ABMsgArray scrnCMsgAry = scrnMsg.A;
        S21TableColorController tblColor = new S21TableColorController("NWAL2370Scrn00", scrnMsg);
        tblColor.clearRowsBG(tblId, scrnCMsgAry);
        tblColor.setAlternateRowsBG(tblId, scrnCMsgAry);
    }
}
