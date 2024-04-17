/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7090.common;

import static business.servlet.NMAL7090.constant.NMAL7090Constant.BIZ_ID;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.BTN_APPLY_A;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.BTN_APPLY_B;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.BTN_APPLY_C;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.BTN_CMN_APL;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.BTN_CMN_APR;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.BTN_CMN_CLR;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.BTN_CMN_DEL;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.BTN_CMN_DWL;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.BTN_CMN_RJT;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.BTN_CMN_RST;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.BTN_CMN_RTN;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.BTN_CMN_SAV;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.BTN_CMN_SUB;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.BTN_INSERT;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.BTN_LBL_PROCESSED;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.BTN_LBL_UPDATE_PRC_LIST;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.BTN_REFRESH;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.BTN_UPDATE_PRC_LIST;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.BTN_UPLOAD;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.EVENT_CLEAR;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.EVENT_INIT;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.EVENT_OPEN_WIN_ITEM_NEW;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.EVENT_OPEN_WIN_ITEM_OLD;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.EVENT_PAGE_NEXT;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.EVENT_PAGE_PREV;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.EVENT_REFRESH;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.EVENT_SEARCH;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.FUNC_ID_EDIT;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.LBL_SUPD_CRAT_DT_FR;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.LBL_SUPD_CRAT_DT_TO;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.MODE_CODE_8;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.NMAM0043E;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.NMAM0192E;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.SCRN_ID_00;
import static business.servlet.NMAL7090.constant.NMAL7090Constant.ZZSM4300E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDFieldErrorException;
import parts.common.EZDGUIAttribute;
import business.servlet.NMAL7090.NMAL7090BMsg;
import business.servlet.NMAL7090.NMAL7090Bean;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Business ID : NMAL7090 Item Supersessions Mass Add
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/28   CITS            S.Tanikawa      Create          N/A
 * 2016/05/11   CITS            S.Tanikawa      Update          QC#8177
 *</pre>
 */
public class NMAL7090CommonLogic {

    /**
     * setNameForMessage
     * @param scrnMsg NMAL7090BMsg
     */
    public static void setNameForMessage(NMAL7090BMsg scrnMsg) {

        scrnMsg.supdCratDt_FR.setNameForMessage(LBL_SUPD_CRAT_DT_FR);
        scrnMsg.supdCratDt_TO.setNameForMessage(LBL_SUPD_CRAT_DT_TO);
    }

    /**
     * set Screen Property
     * @param handler S21CommonHandler
     * @param scrnMsg NMAL7090BMsg
     * @param eventName String
     */
    public static void setScrnProp(S21CommonHandler handler, NMAL7090BMsg scrnMsg, String eventName) {

        scrnMsg.clearAllGUIAttribute(SCRN_ID_00);

        boolean isUpdate = hasUpdateFuncId();

        if (eventName.equals(EVENT_INIT) || eventName.equals(EVENT_CLEAR)) {

            handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
            handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
            handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
            handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
            handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
            handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
            handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
            handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 0, null);
            handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);

            handler.setButtonEnabled(BTN_APPLY_A, false);
            handler.setButtonEnabled(BTN_APPLY_B, false);
            handler.setButtonEnabled(BTN_APPLY_C, false);
            handler.setButtonEnabled(BTN_REFRESH, false);
            handler.setButtonEnabled(BTN_INSERT, false);
            handler.setButtonEnabled(BTN_UPLOAD, false);

        } else if (eventName.equals(EVENT_SEARCH) || eventName.equals(EVENT_REFRESH) || eventName.equals(EVENT_PAGE_PREV) || eventName.equals(EVENT_PAGE_NEXT)) {

            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 1, null);
            handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 1, null);
            handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 1, null);
            handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
            handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);

            handler.setButtonEnabled(BTN_APPLY_A, true);
            handler.setButtonEnabled(BTN_APPLY_B, true);
            handler.setButtonEnabled(BTN_APPLY_C, true);
            handler.setButtonEnabled(BTN_REFRESH, true);
            handler.setButtonEnabled(BTN_INSERT, true);
            handler.setButtonEnabled(BTN_UPLOAD, true);
        }

        if (!isUpdate) {
            handler.setButtonEnabled(BTN_INSERT, false);
            handler.setButtonEnabled(BTN_UPLOAD, false);
            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
            handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        }
        setTableDataProtection(handler, scrnMsg, isUpdate);
    }

    /**
     * set Table Data Protection
     * @param handler S21CommonHandler
     * @param scrnMsg NMAL7090BMsg
     * @param isUpdate boolean
     */
    public static void setTableDataProtection(S21CommonHandler handler, NMAL7090BMsg scrnMsg, boolean isUpdate) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).supdFromMdseCd_A.setInputProtected(true);
            scrnMsg.A.no(i).mdseDescShortTxt_AO.setInputProtected(true);
            scrnMsg.A.no(i).thisMthTotStdCostAmt_AO.setInputProtected(true);
            scrnMsg.A.no(i).supdToMdseCd_A.setInputProtected(true);
            scrnMsg.A.no(i).mdseDescShortTxt_AN.setInputProtected(true);
            scrnMsg.A.no(i).thisMthTotStdCostAmt_AN.setInputProtected(true);
            scrnMsg.A.no(i).supdCratDt_A.setInputProtected(true);

            scrnMsg.A.no(i).thisMthTotStdCostAmt_AO.setAppFracDigit(2);
            scrnMsg.A.no(i).thisMthTotStdCostAmt_AN.setAppFracDigit(2);

            if (isUpdate && ZYPConstant.FLG_OFF_N.equals(scrnMsg.A.no(i).xxPrinFlg_A.getValue())) {
                handler.setButtonProperties(BTN_UPDATE_PRC_LIST, i, null, BTN_LBL_UPDATE_PRC_LIST, 1, null);

            } else if (!isUpdate && ZYPConstant.FLG_OFF_N.equals(scrnMsg.A.no(i).xxPrinFlg_A.getValue())) {
                handler.setButtonProperties(BTN_UPDATE_PRC_LIST, i, null, BTN_LBL_UPDATE_PRC_LIST, 0, null);

            } else {
                handler.setButtonProperties(BTN_UPDATE_PRC_LIST, i, null, BTN_LBL_PROCESSED, 0, null);
            }

        }

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.B.no(i).prcListEquipRqstPk_B.setInputProtected(true);
            scrnMsg.B.no(i).oldMdseCd_B.setInputProtected(false);
            scrnMsg.B.no(i).oldMdseDescShortTxt_B.setInputProtected(true);
            scrnMsg.B.no(i).oldTmthTotStdCostAmt_B.setInputProtected(true);
            scrnMsg.B.no(i).newMdseCd_B.setInputProtected(false);
            scrnMsg.B.no(i).newMdseDescShortTxt_B.setInputProtected(true);
            scrnMsg.B.no(i).newTmthTotStdCostAmt_B.setInputProtected(true);
            scrnMsg.B.no(i).prcListTpCd_BP.setInputProtected(false);
            scrnMsg.B.no(i).prcListTpDescTxt_BP.setInputProtected(false);
            scrnMsg.B.no(i).prcListTpCd_BS.setInputProtected(false);
            scrnMsg.B.no(i).prcListActTpCd_BP.setInputProtected(false);
            scrnMsg.B.no(i).prcListActTpDescTxt_BP.setInputProtected(false);
            scrnMsg.B.no(i).prcListActTpCd_BS.setInputProtected(false);
            scrnMsg.B.no(i).prcListsDescTxt_B.setInputProtected(false);
            scrnMsg.B.no(i).retanOrigPrcFlg_B.setInputProtected(false);
            scrnMsg.B.no(i).newPrcAmt_B.setInputProtected(false);
            scrnMsg.B.no(i).newMinPrcAmt_B.setInputProtected(false);
            scrnMsg.B.no(i).newMaxPrcAmt_B.setInputProtected(false);
            scrnMsg.B.no(i).newMlyPmtAmt_B.setInputProtected(false);
            scrnMsg.B.no(i).newMinLeasePmtAmt_B.setInputProtected(false);
            scrnMsg.B.no(i).newMaxLeasePmtAmt_B.setInputProtected(false);
            scrnMsg.B.no(i).submtFlg_B.setInputProtected(false);
            scrnMsg.B.no(i).rqstDscdFlg_B.setInputProtected(false);

            scrnMsg.B.no(i).oldTmthTotStdCostAmt_B.setAppFracDigit(2);
            scrnMsg.B.no(i).newTmthTotStdCostAmt_B.setAppFracDigit(2);
            scrnMsg.B.no(i).newPrcAmt_B.setAppFracDigit(2);
            scrnMsg.B.no(i).newMinPrcAmt_B.setAppFracDigit(2);
            scrnMsg.B.no(i).newMaxPrcAmt_B.setAppFracDigit(2);
            scrnMsg.B.no(i).newMlyPmtAmt_B.setAppFracDigit(2);
            scrnMsg.B.no(i).newMinLeasePmtAmt_B.setAppFracDigit(2);
            scrnMsg.B.no(i).newMaxLeasePmtAmt_B.setAppFracDigit(2);

        }

        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            scrnMsg.C.no(i).oldMdseCd_C.setInputProtected(true);
            scrnMsg.C.no(i).oldMdseDescShortTxt_C.setInputProtected(true);
            scrnMsg.C.no(i).oldTmthTotStdCostAmt_C.setInputProtected(true);
            scrnMsg.C.no(i).newMdseCd_C.setInputProtected(true);
            scrnMsg.C.no(i).newMdseDescShortTxt_C.setInputProtected(true);
            scrnMsg.C.no(i).newTmthTotStdCostAmt_C.setInputProtected(true);
            scrnMsg.C.no(i).prcListTpDescTxt_C.setInputProtected(true);
            scrnMsg.C.no(i).prcListActTpDescTxt_C.setInputProtected(true);
            scrnMsg.C.no(i).prcListsDescTxt_C.setInputProtected(true);
            scrnMsg.C.no(i).retanOrigPrcFlg_C.setInputProtected(true);
            scrnMsg.C.no(i).newPrcAmt_C.setInputProtected(true);
            scrnMsg.C.no(i).newMinPrcAmt_C.setInputProtected(true);
            scrnMsg.C.no(i).newMaxPrcAmt_C.setInputProtected(true);
            scrnMsg.C.no(i).newMlyPmtAmt_C.setInputProtected(true);
            scrnMsg.C.no(i).newMinLeasePmtAmt_C.setInputProtected(true);
            scrnMsg.C.no(i).newMaxLeasePmtAmt_C.setInputProtected(true);
            scrnMsg.C.no(i).rqstStsTpDescTxt_C.setInputProtected(true);
            scrnMsg.C.no(i).prcListEquipRqstPk_C.setInputProtected(true);
            if (!ZYPCommonFunc.hasValue(scrnMsg.C.no(i).prcListEquipRqstPk_C)) {

                EZDGUIAttribute linkStyle = new EZDGUIAttribute(SCRN_ID_00, NMAL7090Bean.prcListEquipRqstPk_C + "#" + i);

                linkStyle.setVisibility(false);
                scrnMsg.addGUIAttribute(linkStyle);
            }
            scrnMsg.C.no(i).xxAllPsnNm_CC.setInputProtected(true);
            scrnMsg.C.no(i).cratDt_C.setInputProtected(true);
            scrnMsg.C.no(i).xxAllPsnNm_CU.setInputProtected(true);
            scrnMsg.C.no(i).lastUpdDt_C.setInputProtected(true);

            scrnMsg.C.no(i).oldTmthTotStdCostAmt_C.setAppFracDigit(2);
            scrnMsg.C.no(i).newTmthTotStdCostAmt_C.setAppFracDigit(2);
            scrnMsg.C.no(i).newPrcAmt_C.setAppFracDigit(2);
            scrnMsg.C.no(i).newMinPrcAmt_C.setAppFracDigit(2);
            scrnMsg.C.no(i).newMaxPrcAmt_C.setAppFracDigit(2);
            scrnMsg.C.no(i).newMlyPmtAmt_C.setAppFracDigit(2);
            scrnMsg.C.no(i).newMinLeasePmtAmt_C.setAppFracDigit(2);
            scrnMsg.C.no(i).newMaxLeasePmtAmt_C.setAppFracDigit(2);
        }
    }

    /**
     * hasUpdateFuncId
     * @return boolean
     */
    private static boolean hasUpdateFuncId() {

        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();

        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(BIZ_ID);
        if (funcList == null || funcList.isEmpty()) {
            // ZZSM4300E=0,User @ has no permissions to operate this application.
            throw new S21AbendException(ZZSM4300E, new String[] {userProfSvc.getContextUserInfo().getUserId() });
        }

        return funcList.contains(FUNC_ID_EDIT);
    }

    /**
     * checkSearchItem
     * @param scrnMsg NMAL7090BMsg
     */
    public static void checkSearchItem(NMAL7090BMsg scrnMsg) {

        // addCheck
        scrnMsg.addCheckItem(scrnMsg.supdCratDt_FR);
        scrnMsg.addCheckItem(scrnMsg.supdCratDt_TO);

        // mandatory check
        if (!ZYPCommonFunc.hasValue(scrnMsg.supdCratDt_FR) && !ZYPCommonFunc.hasValue(scrnMsg.supdCratDt_TO)) {
            scrnMsg.supdCratDt_FR.setErrorInfo(1, NMAM0192E, new String[] {});
            scrnMsg.supdCratDt_TO.setErrorInfo(1, NMAM0192E, new String[] {});
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.supdCratDt_FR) && ZYPCommonFunc.hasValue(scrnMsg.supdCratDt_TO) && ZYPDateUtil.compare(scrnMsg.supdCratDt_FR.getValue(), scrnMsg.supdCratDt_TO.getValue()) > 0) {
            scrnMsg.supdCratDt_FR.setErrorInfo(1, NMAM0043E, new String[] {LBL_SUPD_CRAT_DT_FR, LBL_SUPD_CRAT_DT_TO });
            scrnMsg.supdCratDt_TO.setErrorInfo(1, NMAM0043E, new String[] {LBL_SUPD_CRAT_DT_FR, LBL_SUPD_CRAT_DT_TO });
        }
    }

    /**
     * add check on Table New Request
     * @param scrnMsg NMAL7090BMsg
     * @param afterSubmit boolean true only when after Submit
     */
    public static void addCheckTableB(NMAL7090BMsg scrnMsg, boolean afterSubmit) {
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            if (afterSubmit) {
                scrnMsg.addCheckItem(scrnMsg.B.no(i).oldMdseCd_B);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).newMdseCd_B);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).prcListTpCd_BS);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).prcListActTpCd_BS);
            }

            scrnMsg.addCheckItem(scrnMsg.B.no(i).oldMdseDescShortTxt_B);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).oldTmthTotStdCostAmt_B);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).newMdseDescShortTxt_B);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).newTmthTotStdCostAmt_B);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).prcListsDescTxt_B);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).retanOrigPrcFlg_B);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).newPrcAmt_B);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).newMinPrcAmt_B);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).newMaxPrcAmt_B);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).newMlyPmtAmt_B);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).newMinLeasePmtAmt_B);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).newMaxLeasePmtAmt_B);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).submtFlg_B);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).rqstDscdFlg_B);
        }
        scrnMsg.putErrorScreen();

        String msgCode = scrnMsg.getMessageCode();
        if (msgCode.endsWith("E")) {
            throw new EZDFieldErrorException();
        }
    }

    /**
     * setMdseInfoParam
     * @param scrnMsg NMAL7090BMsg
     * @param selectedNum int
     * @return Object[] popup parameters
     */
    public static Object[] setMdseInfoParam(NMAL7090BMsg scrnMsg, int selectedNum) {

        // clear Parameter
        scrnMsg.P.clear();

        if (EVENT_OPEN_WIN_ITEM_OLD.equals(scrnMsg.xxPopPrm_EV.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, scrnMsg.B.no(selectedNum).oldMdseCd_B);
        } else if (EVENT_OPEN_WIN_ITEM_NEW.equals(scrnMsg.xxPopPrm_EV.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, scrnMsg.B.no(selectedNum).newMdseCd_B);
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(9).xxPopPrm, MODE_CODE_8);

        // set Parameter
        Object[] param = new Object[10];
        param[0] = scrnMsg.P.no(0).xxPopPrm;
        param[1] = scrnMsg.P.no(1).xxPopPrm;
        param[2] = scrnMsg.P.no(2).xxPopPrm;
        param[3] = scrnMsg.P.no(3).xxPopPrm;
        param[4] = scrnMsg.P.no(4).xxPopPrm;
        param[5] = scrnMsg.P.no(5).xxPopPrm;
        param[6] = scrnMsg.P.no(6).xxPopPrm;
        param[7] = scrnMsg.P.no(7).xxPopPrm;
        param[8] = scrnMsg.P.no(8).xxPopPrm;
        param[9] = scrnMsg.P.no(9).xxPopPrm;

        return param;
    }

    /**
     * setMdseInfoReturnParam
     * @param scrnMsg NMAL7090BMsg
     * @param selectedNum int
     */
    public static void setMdseInfoReturnParam(NMAL7090BMsg scrnMsg, int selectedNum) {

        if (EVENT_OPEN_WIN_ITEM_NEW.equals(scrnMsg.xxPopPrm_EV.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(selectedNum).newMdseCd_B, scrnMsg.P.no(0).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(selectedNum).newMdseDescShortTxt_B, scrnMsg.P.no(1).xxPopPrm);
        } else if (EVENT_OPEN_WIN_ITEM_OLD.equals(scrnMsg.xxPopPrm_EV.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(selectedNum).oldMdseCd_B, scrnMsg.P.no(0).xxPopPrm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(selectedNum).oldMdseDescShortTxt_B, scrnMsg.P.no(1).xxPopPrm);
        }
    }

    /**
     * setPrcListParam
     * @param scrnMsg NMAL7090BMsg
     * @param glblCmpyCd String
     * @param i int
     * @return Object[] popup parameters
     */
    public static Object[] setPrcListParam(NMAL7090BMsg scrnMsg, String glblCmpyCd, int i) {

        Object[] param = new Object[7];

        param[0] = "";
        param[1] = "Price List Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT PCI.PRC_CATG_CD");
        sb.append("     , PCI.PRC_CATG_NM");
        sb.append("     , PCI.PRC_LIST_TP_DESC_TXT");
        sb.append("     , PCI.DISP_STS");
        sb.append("     , PCI.EFF_FROM_DT");
        sb.append("     , PCI.EFF_THRU_DT");
        sb.append("     , PCI.GLBL_CMPY_CD");
        sb.append("     , PCI.EZCANCELFLAG");
        sb.append("  FROM (");
        sb.append("    SELECT PC.GLBL_CMPY_CD");
        sb.append("         , PC.PRC_CATG_CD");
        sb.append("         , PC.PRC_CATG_NM");
        sb.append("         , PLT.PRC_LIST_TP_DESC_TXT");
        sb.append("         , PLST.PRC_LIST_STRU_TP_DESC_TXT");
        sb.append("         , CASE");
        sb.append("             WHEN PC.ACTV_FLG = 'Y'");
        sb.append("              AND PC.DEL_FLG = 'N'");
        sb.append("              AND PC.EFF_FROM_DT <= '#Sales_Date#'");
        sb.append("              AND NVL(PC.EFF_THRU_DT, '99991231') >= '#Sales_Date#'");
        sb.append("             THEN 'Active'");
        sb.append("             WHEN (PC.ACTV_FLG = 'N' ");
        sb.append("                   AND PC.DEL_FLG = 'N')");
        sb.append("               OR (PC.ACTV_FLG = 'Y' ");
        sb.append("                   AND PC.DEL_FLG = 'N'");
        sb.append("                   AND (PC.EFF_FROM_DT  > '#Sales_Date#'");
        sb.append("                     OR NVL(PC.EFF_THRU_DT, '99991231')  < '#Sales_Date#'))");
        sb.append("             THEN 'Inactive'");
        sb.append("             WHEN PC.DEL_FLG = 'Y'");
        sb.append("             THEN 'Delete'");
        sb.append("             ELSE 'None'");
        sb.append("             END DISP_STS");
        sb.append("         , PC.PRC_CONTR_NUM");
        sb.append("         , PCON.PRC_CONTR_NM");
        sb.append("         , PLG.PRC_LIST_GRP_DESC_TXT");
        sb.append("         , PC.EFF_FROM_DT");
        sb.append("         , PC.EFF_THRU_DT");
        sb.append("         , PC.EZCANCELFLAG");
        sb.append("      FROM PRC_CATG PC");
        sb.append("         , PRC_LIST_TP PLT");
        sb.append("         , PRC_LIST_STRU_TP PLST");
        sb.append("         , PRC_CONTR PCON");
        sb.append("         , PRC_LIST_GRP PLG");
        sb.append("      WHERE PC.GLBL_CMPY_CD = '#glblCmpyCd#'");
        sb.append("        AND PC.EZCANCELFLAG = '0'");
        sb.append("        AND PC.GLBL_CMPY_CD   = PLT.GLBL_CMPY_CD");
        sb.append("        AND PC.EZCANCELFLAG   = PLT.EZCANCELFLAG");
        sb.append("        AND PC.PRC_LIST_TP_CD = PLT.PRC_LIST_TP_CD");
        sb.append("        AND PLT.GLBL_CMPY_CD   = PLST.GLBL_CMPY_CD");
        sb.append("        AND PLT.EZCANCELFLAG   = PLST.EZCANCELFLAG");
        sb.append("        AND PLT.PRC_LIST_STRU_TP_CD = PLST.PRC_LIST_STRU_TP_CD");
        sb.append("        AND PC.GLBL_CMPY_CD  = PCON.GLBL_CMPY_CD(+)");
        sb.append("        AND PC.EZCANCELFLAG  = PCON.EZCANCELFLAG(+)");
        sb.append("        AND PC.PRC_CONTR_NUM = PCON.PRC_CONTR_NUM(+)");
        sb.append("        AND PC.GLBL_CMPY_CD  = PLG.GLBL_CMPY_CD(+)");
        sb.append("        AND PC.EZCANCELFLAG  = PLG.EZCANCELFLAG(+)");
        sb.append("        AND PC.PRC_LIST_GRP_CD = PLG.PRC_LIST_GRP_CD(+)");
        // UPDATE START 2016/05/11 QC#8177
        // sb.append("        AND PLT.PRC_LIST_STRU_TP_CD = '#PRC_LIST_TP#'");
        sb.append("        AND PLT.PRC_LIST_TP_CD = '#PRC_LIST_TP#'");
        // UPDATE END 2016/05/11 QC#8177
        sb.append("    ) PCI");
        sb.append("  WHERE PCI.GLBL_CMPY_CD = '#glblCmpyCd#'");
        sb.append(" ORDER BY PCI.PRC_CATG_CD");

        String sql = sb.toString();
        sql = sql.replaceAll("#Sales_Date#", ZYPDateUtil.getSalesDate());
        sql = sql.replaceAll("#PRC_LIST_TP#", scrnMsg.B.no(i).prcListTpCd_BS.getValue());
        sql = sql.replaceAll("#glblCmpyCd#", glblCmpyCd);

        param[2] = sql;

        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] whereArray0 = new Object[4];
        whereArray0[0] = "Price List ID";
        whereArray0[1] = "PRC_CATG_CD";
        whereArray0[2] = null;
        whereArray0[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Price List Name";
        whereArray1[1] = "PRC_CATG_NM";
        whereArray1[2] = null;
        whereArray1[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[4];
        whereArray2[0] = "Price List Type";
        whereArray2[1] = "PRC_LIST_TP_DESC_TXT";
        whereArray2[2] = null;
        whereArray2[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray2);

        Object[] whereArray3 = new Object[4];
        whereArray3[0] = "Status";
        whereArray3[1] = "DISP_STS";
        whereArray3[2] = null;
        whereArray3[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray3);
        param[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Price List ID";
        columnArray0[1] = "PRC_CATG_CD";
        columnArray0[2] = new BigDecimal("10");
        columnArray0[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Price List Name";
        columnArray1[1] = "PRC_CATG_NM";
        columnArray1[2] = new BigDecimal("75");
        columnArray1[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[4];
        columnArray2[0] = "Price List Type";
        columnArray2[1] = "PRC_LIST_TP_DESC_TXT";
        columnArray2[2] = new BigDecimal("50");
        columnArray2[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[4];
        columnArray3[0] = "Status";
        columnArray3[1] = "DISP_STS";
        columnArray3[2] = new BigDecimal("8");
        columnArray3[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray3);

        Object[] columnArray4 = new Object[4];
        columnArray4[0] = "Effective Date From";
        columnArray4[1] = "EFF_FROM_DT";
        columnArray4[2] = new BigDecimal("10");
        columnArray4[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray4);

        Object[] columnArray5 = new Object[4];
        columnArray5[0] = "Effective Date To";
        columnArray5[1] = "EFF_THRU_DT";
        columnArray5[2] = new BigDecimal("10");
        columnArray5[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray5);

        param[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();

        Object[] sortArray0 = new Object[4];
        sortArray0[0] = "PRC_CATG_CD";
        sortArray0[1] = "ASC";
        sortConditionList.add(sortArray0);

        param[5] = sortConditionList;

        // clear Parameter
        scrnMsg.P.clear();

        String[] prcList = scrnMsg.B.no(i).prcListsDescTxt_B.getValue().split(",", 0);
        for (int j = 0; j < prcList.length; j++) {
            scrnMsg.P.no(j).xxComnScrColValTxt_0.setValue(prcList[j]);
        }
        scrnMsg.P.setValidCount(prcList.length);
        param[6] = scrnMsg.P;

        return param;
    }
}
