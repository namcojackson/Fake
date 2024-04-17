/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1380.common;

import static business.servlet.NSAL1380.constant.NSAL1380Constant.BIZ_ID;
import static business.servlet.NSAL1380.constant.NSAL1380Constant.FUNC_ID_UPDATE;
import static business.servlet.NSAL1380.constant.NSAL1380Constant.ZZSM4300E;
import static business.servlet.NSAL1380.constant.NSAL1380Constant.BTN_CMN_APL.BTN_CMN_APL0;
import static business.servlet.NSAL1380.constant.NSAL1380Constant.BTN_CMN_APL.BTN_CMN_APL1;
import static business.servlet.NSAL1380.constant.NSAL1380Constant.BTN_CMN_APL.BTN_CMN_APL2;
import static business.servlet.NSAL1380.constant.NSAL1380Constant.BTN_CMN_APR.BTN_CMN_APR0;
import static business.servlet.NSAL1380.constant.NSAL1380Constant.BTN_CMN_APR.BTN_CMN_APR1;
import static business.servlet.NSAL1380.constant.NSAL1380Constant.BTN_CMN_APR.BTN_CMN_APR2;
import static business.servlet.NSAL1380.constant.NSAL1380Constant.BTN_CMN_CLR.BTN_CMN_CLR0;
import static business.servlet.NSAL1380.constant.NSAL1380Constant.BTN_CMN_CLR.BTN_CMN_CLR1;
import static business.servlet.NSAL1380.constant.NSAL1380Constant.BTN_CMN_CLR.BTN_CMN_CLR2;
import static business.servlet.NSAL1380.constant.NSAL1380Constant.BTN_CMN_DEL.BTN_CMN_DEL0;
import static business.servlet.NSAL1380.constant.NSAL1380Constant.BTN_CMN_DEL.BTN_CMN_DEL1;
import static business.servlet.NSAL1380.constant.NSAL1380Constant.BTN_CMN_DEL.BTN_CMN_DEL2;
import static business.servlet.NSAL1380.constant.NSAL1380Constant.BTN_CMN_DWL.BTN_CMN_DWL0;
import static business.servlet.NSAL1380.constant.NSAL1380Constant.BTN_CMN_DWL.BTN_CMN_DWL1;
import static business.servlet.NSAL1380.constant.NSAL1380Constant.BTN_CMN_DWL.BTN_CMN_DWL2;
import static business.servlet.NSAL1380.constant.NSAL1380Constant.BTN_CMN_RJT.BTN_CMN_RJT0;
import static business.servlet.NSAL1380.constant.NSAL1380Constant.BTN_CMN_RJT.BTN_CMN_RJT1;
import static business.servlet.NSAL1380.constant.NSAL1380Constant.BTN_CMN_RJT.BTN_CMN_RJT2;
import static business.servlet.NSAL1380.constant.NSAL1380Constant.BTN_CMN_RST.BTN_CMN_RST0;
import static business.servlet.NSAL1380.constant.NSAL1380Constant.BTN_CMN_RST.BTN_CMN_RST1;
import static business.servlet.NSAL1380.constant.NSAL1380Constant.BTN_CMN_RST.BTN_CMN_RST2;
import static business.servlet.NSAL1380.constant.NSAL1380Constant.BTN_CMN_RTN.BTN_CMN_RTN0;
import static business.servlet.NSAL1380.constant.NSAL1380Constant.BTN_CMN_RTN.BTN_CMN_RTN1;
import static business.servlet.NSAL1380.constant.NSAL1380Constant.BTN_CMN_RTN.BTN_CMN_RTN2;
import static business.servlet.NSAL1380.constant.NSAL1380Constant.BTN_CMN_SAV.BTN_CMN_SAV0;
import static business.servlet.NSAL1380.constant.NSAL1380Constant.BTN_CMN_SAV.BTN_CMN_SAV1;
import static business.servlet.NSAL1380.constant.NSAL1380Constant.BTN_CMN_SAV.BTN_CMN_SAV2;
import static business.servlet.NSAL1380.constant.NSAL1380Constant.BTN_CMN_SUB.BTN_CMN_SUB0;
import static business.servlet.NSAL1380.constant.NSAL1380Constant.BTN_CMN_SUB.BTN_CMN_SUB1;
import static business.servlet.NSAL1380.constant.NSAL1380Constant.BTN_CMN_SUB.BTN_CMN_SUB2;

import java.util.List;

import parts.common.EZDMessageInfo;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL1380.NSAL1380BMsg;
import business.servlet.NSAL1380.NSAL1380_ABMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SPLY_AGMT_DOC_TP;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Supply Agreement Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/05/08   Hitachi         N.Arai          Create          N/A
 *</pre>
 */
public class NSAL1380CommonLogic {

    /**
     * initControlScreen
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL1380BMsg
     */
    public static void initControlScreen(EZDCommonHandler handler, NSAL1380BMsg scrnMsg) {
        initCommonButton(handler);
        initButton(handler);
        initScreen(handler, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.splyBaseAmt);
    }

    /**
     * Set Button Enable Init
     * @param handler EZDCommonHandler
     * @param isImport if import then true.
     */
    public static void initCommonButton(EZDCommonHandler handler) {

        handler.setButtonProperties(BTN_CMN_SAV0, BTN_CMN_SAV1, BTN_CMN_SAV2, 1, null);
        handler.setButtonProperties(BTN_CMN_SUB0, BTN_CMN_SUB1, BTN_CMN_SUB2, 0, null);
        handler.setButtonProperties(BTN_CMN_APL0, BTN_CMN_APL1, BTN_CMN_APL2, 0, null);
        handler.setButtonProperties(BTN_CMN_APR0, BTN_CMN_APR1, BTN_CMN_APR2, 0, null);
        handler.setButtonProperties(BTN_CMN_RJT0, BTN_CMN_RJT1, BTN_CMN_RJT2, 0, null);
        handler.setButtonProperties(BTN_CMN_DWL0, BTN_CMN_DWL1, BTN_CMN_DWL2, 0, null);
        handler.setButtonProperties(BTN_CMN_DEL0, BTN_CMN_DEL1, BTN_CMN_DEL2, 0, null);
        handler.setButtonProperties(BTN_CMN_CLR0, BTN_CMN_CLR1, BTN_CMN_CLR2, 0, null);
        handler.setButtonProperties(BTN_CMN_RST0, BTN_CMN_RST1, BTN_CMN_RST2, 1, null);
        handler.setButtonProperties(BTN_CMN_RTN0, BTN_CMN_RTN1, BTN_CMN_RTN2, 1, null);
    }

    /**
     * initButton
     * @param handler EZDCommonHandler
     */
    public static void initButton(EZDCommonHandler handler) {
    }

    /**
     * initScreen
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL7070BMsg
     */
    public static void initScreen(EZDCommonHandler handler, NSAL1380BMsg scrnMsg) {
        String splyAgmtDocTpCd = scrnMsg.splyAgmtDocTpCd.getValue();

        scrnMsg.shellLineNum.setInputProtected(true);
        scrnMsg.svcPgmMdseCd.setInputProtected(true);
        scrnMsg.termMthAot.setInputProtected(true);
        scrnMsg.splyBaseAmt.setInputProtected(false);
        scrnMsg.splyAgmtPlnPk.setInputProtected(true);
        scrnMsg.splyAgmtPlnNm.setInputProtected(true);
        scrnMsg.splyAgmtPlnShortNm.setInputProtected(true);
        scrnMsg.splyAgmtPlnDescTxt.setInputProtected(true);
        scrnMsg.splyAgmtPlnTpDescTxt.setInputProtected(true);
        scrnMsg.splyAgmtDocTpDescTxt.setInputProtected(true);
        scrnMsg.annTermCapNum.setInputProtected(true);

        if (SPLY_AGMT_DOC_TP.QUANTITY_CONTRACT.equals(splyAgmtDocTpCd)) {
            scrnMsg.qtyContrCapQty.setInputProtected(false);
        } else {
            scrnMsg.qtyContrCapQty.setInputProtected(true);
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSAL1380_ABMsg lineMsg = scrnMsg.A.no(i);
            lineMsg.imgSplyTpNm_A.setInputProtected(true);
            lineMsg.mdseCd_A.setInputProtected(true);
            lineMsg.mnfItemCd_A.setInputProtected(true);
            lineMsg.mdseDescShortTxt_A.setInputProtected(true);

            if (SPLY_AGMT_DOC_TP.SCHEDULING_AGREEMENT.equals(splyAgmtDocTpCd)) {
                lineMsg.shpgIntvlCd_A.setInputProtected(false);
                lineMsg.otmShipQty_A.setInputProtected(false);
                lineMsg.schdAllwQty_A.setInputProtected(false);
                lineMsg.firstShipQty_A.setInputProtected(false);
            } else {
                lineMsg.shpgIntvlCd_A.setInputProtected(true);
                lineMsg.otmShipQty_A.setInputProtected(true);
                lineMsg.schdAllwQty_A.setInputProtected(true);
                lineMsg.firstShipQty_A.setInputProtected(true);
            }

            lineMsg.xxTotQtyCnt_A.setInputProtected(true);
            lineMsg.xxTotQtyCnt_AT.setInputProtected(true);
        }

        checkFuncId(handler, scrnMsg);

        // Mod Start 2018/09/05 QC#28055
        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType() || ZYPConstant.FLG_OFF_N.equals(scrnMsg.scrEntAvalFlg.getValue())) {
            setReadOnlyMode(handler, scrnMsg);
        }
        // Mod End 2018/09/05 QC#28055
    }

    /**
     * hasUpdateFuncId
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL1380BMsg
     */
    public static void checkFuncId(EZDCommonHandler handler, NSAL1380BMsg scrnMsg) {
        if (!hasUpdateFuncId()) {
            setReadOnlyMode(handler, scrnMsg);
        }
    }

    /**
     * hasUpdateFuncId
     * @return boolean
     */
    public static boolean hasUpdateFuncId() {

        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();

        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(BIZ_ID);
        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException(ZZSM4300E, new String[] {userProfSvc.getContextUserInfo().getUserId() });
        }

        if (funcList.contains(FUNC_ID_UPDATE)) {
            return true;
        }

        return false;
    }

    private static void setReadOnlyMode(EZDCommonHandler handler, NSAL1380BMsg scrnMsg) {
        //CommonButton
        handler.setButtonProperties(BTN_CMN_SAV0, BTN_CMN_SAV1, BTN_CMN_SAV2, 0, null);
        handler.setButtonProperties(BTN_CMN_SUB0, BTN_CMN_SUB1, BTN_CMN_SUB2, 0, null);
        handler.setButtonProperties(BTN_CMN_APL0, BTN_CMN_APL1, BTN_CMN_APL2, 0, null);
        handler.setButtonProperties(BTN_CMN_APR0, BTN_CMN_APR1, BTN_CMN_APR2, 0, null);
        handler.setButtonProperties(BTN_CMN_RJT0, BTN_CMN_RJT1, BTN_CMN_RJT2, 0, null);
        handler.setButtonProperties(BTN_CMN_DWL0, BTN_CMN_DWL1, BTN_CMN_DWL2, 0, null);
        handler.setButtonProperties(BTN_CMN_DEL0, BTN_CMN_DEL1, BTN_CMN_DEL2, 0, null);
        handler.setButtonProperties(BTN_CMN_CLR0, BTN_CMN_CLR1, BTN_CMN_CLR2, 0, null);
        handler.setButtonProperties(BTN_CMN_RST0, BTN_CMN_RST1, BTN_CMN_RST2, 0, null);
        handler.setButtonProperties(BTN_CMN_RTN0, BTN_CMN_RTN1, BTN_CMN_RTN2, 1, null);

        //Button

        //Screen
        scrnMsg.svcPgmMdseCd.setInputProtected(true);
        scrnMsg.termMthAot.setInputProtected(true);
        scrnMsg.splyBaseAmt.setInputProtected(true);
        scrnMsg.splyAgmtPlnPk.setInputProtected(true);
        scrnMsg.splyAgmtPlnNm.setInputProtected(true);
        scrnMsg.splyAgmtPlnShortNm.setInputProtected(true);
        scrnMsg.splyAgmtPlnDescTxt.setInputProtected(true);
        scrnMsg.splyAgmtPlnTpDescTxt.setInputProtected(true);
        scrnMsg.splyAgmtDocTpDescTxt.setInputProtected(true);
        scrnMsg.annTermCapNum.setInputProtected(true);
        scrnMsg.qtyContrCapQty.setInputProtected(true);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSAL1380_ABMsg lineMsg = scrnMsg.A.no(i);
            lineMsg.imgSplyTpNm_A.setInputProtected(true);
            lineMsg.mdseCd_A.setInputProtected(true);
            lineMsg.mnfItemCd_A.setInputProtected(true);
            lineMsg.mdseDescShortTxt_A.setInputProtected(true);
            lineMsg.shpgIntvlCd_A.setInputProtected(true);
            lineMsg.firstShipQty_A.setInputProtected(true);
            lineMsg.otmShipQty_A.setInputProtected(true);
            lineMsg.schdAllwQty_A.setInputProtected(true);
            lineMsg.xxTotQtyCnt_A.setInputProtected(true);
            lineMsg.xxTotQtyCnt_AT.setInputProtected(true);
        }
    }

    /**
     * commonAddCheckItem
     * @param scrnMsg NSAL1380BMsg
     */
    public static void addCheckItem(NSAL1380BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.splyBaseAmt);
        scrnMsg.addCheckItem(scrnMsg.qtyContrCapQty);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NSAL1380_ABMsg lineMsg = scrnMsg.A.no(i);
            scrnMsg.addCheckItem(lineMsg.shpgIntvlCd_A);
            scrnMsg.addCheckItem(lineMsg.otmShipQty_A);
            scrnMsg.addCheckItem(lineMsg.schdAllwQty_A);
            scrnMsg.addCheckItem(lineMsg.firstShipQty_A);
        }
    }
}
