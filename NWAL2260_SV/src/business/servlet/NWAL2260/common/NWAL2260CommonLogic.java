/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2260.common;

import static business.servlet.NWAL2260.constant.NWAL2260Constant.BIZ_ID;
import static business.servlet.NWAL2260.constant.NWAL2260Constant.BTN_CMN_APL;
import static business.servlet.NWAL2260.constant.NWAL2260Constant.BTN_CMN_APR;
import static business.servlet.NWAL2260.constant.NWAL2260Constant.BTN_CMN_CLR;
import static business.servlet.NWAL2260.constant.NWAL2260Constant.BTN_CMN_DEL;
import static business.servlet.NWAL2260.constant.NWAL2260Constant.BTN_CMN_DWL;
import static business.servlet.NWAL2260.constant.NWAL2260Constant.BTN_CMN_RJT;
import static business.servlet.NWAL2260.constant.NWAL2260Constant.BTN_CMN_RST;
import static business.servlet.NWAL2260.constant.NWAL2260Constant.BTN_CMN_RTN;
import static business.servlet.NWAL2260.constant.NWAL2260Constant.BTN_CMN_SAV;
import static business.servlet.NWAL2260.constant.NWAL2260Constant.BTN_CMN_SUB;
import static business.servlet.NWAL2260.constant.NWAL2260Constant.FUNC_ID_EDT;
import static business.servlet.NWAL2260.constant.NWAL2260Constant.FUNC_ID_INQ;
import static business.servlet.NWAL2260.constant.NWAL2260Constant.SCREEN_ID;
import static business.servlet.NWAL2260.constant.NWAL2260Constant.STYLE_CLASS;
import static business.servlet.NWAL2260.constant.NWAL2260Constant.TBL_A_ROW_NUM;
import static business.servlet.NWAL2260.constant.NWAL2260Constant.TBL_B_ROW_NUM;
import static business.servlet.NWAL2260.constant.NWAL2260Constant.TBL_C_ROW_NUM;
import static business.servlet.NWAL2260.constant.NWAL2260Constant.TBL_D_ROW_NUM;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NWAL2260.NWAL2260BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/24   Hitachi         O.Okuma         Create          N/A
 * 2018/07/03   Fujitsu         A.Kosai         Update          S21_NA#26908
 * 2018/11/27   Fujitsu         K.Ishizuka      Update          S21_NA#28899
 *</pre>
 */
public class NWAL2260CommonLogic {
    private static S21UserProfileService getUserProfileService() {
        return S21UserProfileServiceFactory.getInstance().getService();
    }

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NWAL2260BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NWAL2260BMsg scrnMsg) {
        ArrayList<String> functionList = (ArrayList<String>) getUserProfileService().getFunctionCodeListForBizAppId(BIZ_ID);
        activateButtons(handler, scrnMsg, functionList);
        controlScreenFields(scrnMsg);
    }

    /**
     * activateButtons
     * @param handler S21CommonHandler
     * @param scrnMsg NWAL2260BMsg
     * @param functionList List<String>
     */
    private static void activateButtons(EZDCommonHandler handler, NWAL2260BMsg scrnMsg, List<String> functionList) {

        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 0, null);

        if (functionList == null || functionList.isEmpty()) {
            handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);
            setProtectedScreenFields(scrnMsg);
        // 2018/07/03 S21_NA#26908 Add Start
        } else if (ZYPCommonFunc.hasValue(scrnMsg.xxReadOnlyFlg) && ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxReadOnlyFlg.getValue())) {
            handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);
            setProtectedScreenFields(scrnMsg);
        // 2018/07/03 S21_NA#26908 Add End
        } else if (functionList.contains(FUNC_ID_EDT)) {
            handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 1, null);
            handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 1, null);
            handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);
        } else if (functionList.contains(FUNC_ID_INQ)) {
            handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);
            setProtectedScreenFields(scrnMsg);
        }
    }

    /**
     * controlScreenFields
     * @param scrnMsg NWAL2260BMsg
     */
    public static void controlScreenFields(NWAL2260BMsg scrnMsg) {

        scrnMsg.ordSrcRefNum_H.setInputProtected(true);
        scrnMsg.ordSrcRefLineNum_H.setInputProtected(true);
        scrnMsg.ordSrcRefLineSubNum_H.setInputProtected(true);
        scrnMsg.cpoOrdNum.setInputProtected(true);
        scrnMsg.xxRefNumTxt.setInputProtected(true);
        scrnMsg.ediTrdPtnrNm.setInputProtected(true);

        scrnMsg.scrLbNm_01.setInputProtected(true);
        scrnMsg.scrLbNm_02.setInputProtected(true);
        scrnMsg.scrLbNm_03.setInputProtected(true);
        scrnMsg.scrLbNm_04.setInputProtected(true);
        scrnMsg.scrLbNm_05.setInputProtected(true);
        scrnMsg.scrLbNm_06.setInputProtected(true);
        scrnMsg.scrLbNm_07.setInputProtected(true);
        scrnMsg.scrLbNm_08.setInputProtected(true);
        scrnMsg.scrLbNm_09.setInputProtected(true);
        scrnMsg.scrLbNm_10.setInputProtected(true);
        scrnMsg.scrLbNm_11.setInputProtected(true);
        scrnMsg.scrLbNm_12.setInputProtected(true);
        scrnMsg.scrLbNm_13.setInputProtected(true);
        scrnMsg.scrLbNm_14.setInputProtected(true);
        scrnMsg.scrLbNm_15.setInputProtected(true);

        setRowColors(scrnMsg);
    }

    /**
     * setRowColors
     * @param scrnMsg NWAL2260BMsg
     */
    private static void setRowColors(NWAL2260BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);

        if (ZYPConstant.FLG_ON_1.equals(scrnMsg.xxEdtModeFlg.getValue())) {
            for (int i = 0; i < TBL_A_ROW_NUM; i++) {
                if (i % 2 == 0) {
                    tblColor.setRowStyle("A", i, STYLE_CLASS);
                }
            }
            for (int i = 0; i < TBL_B_ROW_NUM; i++) {
                if (i % 2 == 0) {
                    tblColor.setRowStyle("B", i, STYLE_CLASS);
                }
            }
        } else {
            for (int i = 0; i < TBL_C_ROW_NUM; i++) {
                if (i % 2 == 0) {
                    tblColor.setRowStyle("C", i, STYLE_CLASS);
                }
            }
            for (int i = 0; i < TBL_D_ROW_NUM; i++) {
                if (i % 2 == 0) {
                    tblColor.setRowStyle("D", i, STYLE_CLASS);
                }
            }
        }
    }

    /**
     * setProtectedScreenFields
     * @param scrnMsg NWAL2260BMsg
     */
    private static void setProtectedScreenFields(NWAL2260BMsg scrnMsg) {

        scrnMsg.dsImptAttrbTxt_01.setInputProtected(true);
        scrnMsg.dsImptAttrbTxt_02.setInputProtected(true);
        scrnMsg.dsImptAttrbTxt_03.setInputProtected(true);
        scrnMsg.dsImptAttrbTxt_04.setInputProtected(true);
        scrnMsg.dsImptAttrbTxt_05.setInputProtected(true);
        scrnMsg.dsImptAttrbTxt_06.setInputProtected(true);
        scrnMsg.dsImptAttrbTxt_07.setInputProtected(true);
        scrnMsg.dsImptAttrbTxt_08.setInputProtected(true);
        scrnMsg.dsImptAttrbTxt_09.setInputProtected(true);
        scrnMsg.dsImptAttrbTxt_10.setInputProtected(true);
        scrnMsg.dsImptAttrbTxt_11.setInputProtected(true);
        scrnMsg.dsImptAttrbTxt_12.setInputProtected(true);
        scrnMsg.dsImptAttrbTxt_13.setInputProtected(true);
        scrnMsg.dsImptAttrbTxt_14.setInputProtected(true);
        scrnMsg.dsImptAttrbTxt_15.setInputProtected(true);

        scrnMsg.idocPoDocNum.setInputProtected(true);
        scrnMsg.idocPoOrgValTxt_01.setInputProtected(true);
        scrnMsg.idocPoOrgValTxt_02.setInputProtected(true);
        scrnMsg.idocPoOrdRsnCd.setInputProtected(true);
        scrnMsg.idocPoCustRefNum.setInputProtected(true);
        scrnMsg.idocPoCustRefDt.setInputProtected(true);
        scrnMsg.idocPoPtnrTpCd_01.setInputProtected(true);
        scrnMsg.idocPoPtnrTpCd_02.setInputProtected(true);
        scrnMsg.idocPoPtnrTpCd_03.setInputProtected(true);
        scrnMsg.idocPoPtnrTpCd_04.setInputProtected(true);
        scrnMsg.idocPoPtnrTpCd_05.setInputProtected(true);
        scrnMsg.idocPoPtnrTpCd_06.setInputProtected(true);
        scrnMsg.idocPoPtnrNum_01.setInputProtected(true);
        scrnMsg.idocPoPtnrNum_02.setInputProtected(true);
        scrnMsg.idocPoPtnrNum_03.setInputProtected(true);
        scrnMsg.idocPoPtnrNum_04.setInputProtected(true);
        scrnMsg.idocPoPtnrNum_05.setInputProtected(true);
        scrnMsg.idocPoPtnrNum_06.setInputProtected(true);
        scrnMsg.idocPtnrCustRefTxt_01.setInputProtected(true);
        scrnMsg.idocPtnrCustRefTxt_02.setInputProtected(true);
        scrnMsg.idocPtnrCustRefTxt_03.setInputProtected(true);
        scrnMsg.idocPtnrCustRefTxt_04.setInputProtected(true);
        scrnMsg.idocPtnrCustRefTxt_05.setInputProtected(true);
        scrnMsg.idocPtnrCustRefTxt_06.setInputProtected(true);
        scrnMsg.idocPoPtnrId_01.setInputProtected(true);
        scrnMsg.idocPoPtnrId_02.setInputProtected(true);
        scrnMsg.idocPoPtnrId_03.setInputProtected(true);
        scrnMsg.idocPoPtnrId_04.setInputProtected(true);
        scrnMsg.idocPoPtnrId_05.setInputProtected(true);
        scrnMsg.idocPoPtnrId_06.setInputProtected(true);
        scrnMsg.idocPtnrCtacNm_01.setInputProtected(true);
        scrnMsg.idocPtnrCtacNm_02.setInputProtected(true);
        scrnMsg.idocPtnrCtacNm_03.setInputProtected(true);
        scrnMsg.idocPtnrCtacNm_04.setInputProtected(true);
        scrnMsg.idocPtnrCtacNm_05.setInputProtected(true);
        scrnMsg.idocPtnrCtacNm_06.setInputProtected(true);
        scrnMsg.idocPtnrTelNum_01.setInputProtected(true);
        scrnMsg.idocPtnrTelNum_02.setInputProtected(true);
        scrnMsg.idocPtnrTelNum_03.setInputProtected(true);
        scrnMsg.idocPtnrTelNum_04.setInputProtected(true);
        scrnMsg.idocPtnrTelNum_05.setInputProtected(true);
        scrnMsg.idocPtnrTelNum_06.setInputProtected(true);
        scrnMsg.idocFirstLineAddr.setInputProtected(true);
        scrnMsg.idocScdLineAddr.setInputProtected(true);
        scrnMsg.idocPtnrCtyNm.setInputProtected(true);
        scrnMsg.idocPtnrStCd.setInputProtected(true);
        scrnMsg.idocPtnrPostCd.setInputProtected(true);
        scrnMsg.idocPtnrCtryCd.setInputProtected(true);
        scrnMsg.idocPoDtValTxt.setInputProtected(true);
        scrnMsg.idocPoDelyCondCd.setInputProtected(true);
        scrnMsg.idocPoDelyCondNm.setInputProtected(true);
        scrnMsg.idocPoNoteTxt.setInputProtected(true);
        scrnMsg.idocPoRcpntPtnrNum.setInputProtected(true);

        scrnMsg.dsImptDtlAttrbTxt_01.setInputProtected(true);
        scrnMsg.dsImptDtlAttrbTxt_02.setInputProtected(true);
        scrnMsg.dsImptDtlAttrbTxt_03.setInputProtected(true);
        scrnMsg.dsImptDtlAttrbTxt_04.setInputProtected(true);
        scrnMsg.dsImptDtlAttrbTxt_05.setInputProtected(true);
        scrnMsg.dsImptDtlAttrbTxt_06.setInputProtected(true);
        scrnMsg.dsImptDtlAttrbTxt_07.setInputProtected(true);
        scrnMsg.dsImptDtlAttrbTxt_08.setInputProtected(true);
        scrnMsg.dsImptDtlAttrbTxt_09.setInputProtected(true);
        scrnMsg.dsImptDtlAttrbTxt_10.setInputProtected(true);
        scrnMsg.dsImptDtlAttrbTxt_11.setInputProtected(true);
        scrnMsg.dsImptDtlAttrbTxt_12.setInputProtected(true);
        scrnMsg.dsImptDtlAttrbTxt_13.setInputProtected(true);
        scrnMsg.dsImptDtlAttrbTxt_14.setInputProtected(true);
        scrnMsg.dsImptDtlAttrbTxt_15.setInputProtected(true);

        scrnMsg.idocPoDtlLineRefNum.setInputProtected(true);
        scrnMsg.idocPoDtlMatNum_01.setInputProtected(true);
        scrnMsg.idocPoDtlMatNum_02.setInputProtected(true);
        scrnMsg.idocPoDtlOrdQty.setInputProtected(true);
        scrnMsg.idocPoDtlUomCd.setInputProtected(true);
        scrnMsg.idocPoDtlDelyPrtyNm.setInputProtected(true);
    }

    /**
     * addCheckItem
     * @param scrnMsg NWAL2260BMsg
     */
    public static void addCheckItem(NWAL2260BMsg scrnMsg) {

        if (ZYPConstant.FLG_ON_1.equals(scrnMsg.xxEdtModeFlg.getValue())) {

            scrnMsg.addCheckItem(scrnMsg.dsImptAttrbTxt_01);
            scrnMsg.addCheckItem(scrnMsg.dsImptAttrbTxt_02);
            scrnMsg.addCheckItem(scrnMsg.dsImptAttrbTxt_03);
            scrnMsg.addCheckItem(scrnMsg.dsImptAttrbTxt_04);
            scrnMsg.addCheckItem(scrnMsg.dsImptAttrbTxt_05);
            scrnMsg.addCheckItem(scrnMsg.dsImptAttrbTxt_06);
            scrnMsg.addCheckItem(scrnMsg.dsImptAttrbTxt_07);
            scrnMsg.addCheckItem(scrnMsg.dsImptAttrbTxt_08);
            scrnMsg.addCheckItem(scrnMsg.dsImptAttrbTxt_09);
            scrnMsg.addCheckItem(scrnMsg.dsImptAttrbTxt_10);
            scrnMsg.addCheckItem(scrnMsg.dsImptAttrbTxt_11);
            scrnMsg.addCheckItem(scrnMsg.dsImptAttrbTxt_12);
            scrnMsg.addCheckItem(scrnMsg.dsImptAttrbTxt_13);
            scrnMsg.addCheckItem(scrnMsg.dsImptAttrbTxt_14);
            scrnMsg.addCheckItem(scrnMsg.dsImptAttrbTxt_15);

            scrnMsg.addCheckItem(scrnMsg.idocPoDocNum);
            scrnMsg.addCheckItem(scrnMsg.idocPoOrgValTxt_01);
            scrnMsg.addCheckItem(scrnMsg.idocPoOrgValTxt_02);
            scrnMsg.addCheckItem(scrnMsg.idocPoOrdRsnCd);
            scrnMsg.addCheckItem(scrnMsg.idocPoCustRefNum);
            scrnMsg.addCheckItem(scrnMsg.idocPoCustRefDt);
            scrnMsg.addCheckItem(scrnMsg.idocPoPtnrTpCd_01);
            scrnMsg.addCheckItem(scrnMsg.idocPoPtnrTpCd_02);
            scrnMsg.addCheckItem(scrnMsg.idocPoPtnrTpCd_03);
            scrnMsg.addCheckItem(scrnMsg.idocPoPtnrTpCd_04);
            scrnMsg.addCheckItem(scrnMsg.idocPoPtnrTpCd_05);
            scrnMsg.addCheckItem(scrnMsg.idocPoPtnrTpCd_06);
            scrnMsg.addCheckItem(scrnMsg.idocPoPtnrNum_01);
            scrnMsg.addCheckItem(scrnMsg.idocPoPtnrNum_02);
            scrnMsg.addCheckItem(scrnMsg.idocPoPtnrNum_03);
            scrnMsg.addCheckItem(scrnMsg.idocPoPtnrNum_04);
            scrnMsg.addCheckItem(scrnMsg.idocPoPtnrNum_05);
            scrnMsg.addCheckItem(scrnMsg.idocPoPtnrNum_06);
            scrnMsg.addCheckItem(scrnMsg.idocPtnrCustRefTxt_01);
            scrnMsg.addCheckItem(scrnMsg.idocPtnrCustRefTxt_02);
            scrnMsg.addCheckItem(scrnMsg.idocPtnrCustRefTxt_03);
            scrnMsg.addCheckItem(scrnMsg.idocPtnrCustRefTxt_04);
            scrnMsg.addCheckItem(scrnMsg.idocPtnrCustRefTxt_05);
            scrnMsg.addCheckItem(scrnMsg.idocPtnrCustRefTxt_06);
            scrnMsg.addCheckItem(scrnMsg.idocPoPtnrId_01);
            scrnMsg.addCheckItem(scrnMsg.idocPoPtnrId_02);
            scrnMsg.addCheckItem(scrnMsg.idocPoPtnrId_03);
            scrnMsg.addCheckItem(scrnMsg.idocPoPtnrId_04);
            scrnMsg.addCheckItem(scrnMsg.idocPoPtnrId_05);
            scrnMsg.addCheckItem(scrnMsg.idocPoPtnrId_06);
            scrnMsg.addCheckItem(scrnMsg.idocPtnrCtacNm_01);
            scrnMsg.addCheckItem(scrnMsg.idocPtnrCtacNm_02);
            scrnMsg.addCheckItem(scrnMsg.idocPtnrCtacNm_03);
            scrnMsg.addCheckItem(scrnMsg.idocPtnrCtacNm_04);
            scrnMsg.addCheckItem(scrnMsg.idocPtnrCtacNm_05);
            scrnMsg.addCheckItem(scrnMsg.idocPtnrCtacNm_06);
            scrnMsg.addCheckItem(scrnMsg.idocPtnrTelNum_01);
            scrnMsg.addCheckItem(scrnMsg.idocPtnrTelNum_02);
            scrnMsg.addCheckItem(scrnMsg.idocPtnrTelNum_03);
            scrnMsg.addCheckItem(scrnMsg.idocPtnrTelNum_04);
            scrnMsg.addCheckItem(scrnMsg.idocPtnrTelNum_05);
            scrnMsg.addCheckItem(scrnMsg.idocPtnrTelNum_06);
            scrnMsg.addCheckItem(scrnMsg.idocFirstLineAddr);
            scrnMsg.addCheckItem(scrnMsg.idocScdLineAddr);
            scrnMsg.addCheckItem(scrnMsg.idocPtnrCtyNm);
            scrnMsg.addCheckItem(scrnMsg.idocPtnrStCd);
            scrnMsg.addCheckItem(scrnMsg.idocPtnrPostCd);
            scrnMsg.addCheckItem(scrnMsg.idocPtnrCtryCd);
            scrnMsg.addCheckItem(scrnMsg.idocPoDtValTxt);
            scrnMsg.addCheckItem(scrnMsg.idocPoDelyCondCd);
            scrnMsg.addCheckItem(scrnMsg.idocPoDelyCondNm);
            scrnMsg.addCheckItem(scrnMsg.idocPoNoteTxt);
            scrnMsg.addCheckItem(scrnMsg.idocPoRcpntPtnrNum);

        } else {

            scrnMsg.addCheckItem(scrnMsg.dsImptDtlAttrbTxt_01);
            scrnMsg.addCheckItem(scrnMsg.dsImptDtlAttrbTxt_02);
            scrnMsg.addCheckItem(scrnMsg.dsImptDtlAttrbTxt_03);
            scrnMsg.addCheckItem(scrnMsg.dsImptDtlAttrbTxt_04);
            scrnMsg.addCheckItem(scrnMsg.dsImptDtlAttrbTxt_05);
            scrnMsg.addCheckItem(scrnMsg.dsImptDtlAttrbTxt_06);
            scrnMsg.addCheckItem(scrnMsg.dsImptDtlAttrbTxt_07);
            scrnMsg.addCheckItem(scrnMsg.dsImptDtlAttrbTxt_08);
            scrnMsg.addCheckItem(scrnMsg.dsImptDtlAttrbTxt_09);
            scrnMsg.addCheckItem(scrnMsg.dsImptDtlAttrbTxt_10);
            scrnMsg.addCheckItem(scrnMsg.dsImptDtlAttrbTxt_11);
            scrnMsg.addCheckItem(scrnMsg.dsImptDtlAttrbTxt_12);
            scrnMsg.addCheckItem(scrnMsg.dsImptDtlAttrbTxt_13);
            scrnMsg.addCheckItem(scrnMsg.dsImptDtlAttrbTxt_14);
            scrnMsg.addCheckItem(scrnMsg.dsImptDtlAttrbTxt_15);

            scrnMsg.addCheckItem(scrnMsg.idocPoDtlLineRefNum);
            scrnMsg.addCheckItem(scrnMsg.idocPoDtlMatNum_01);
            scrnMsg.addCheckItem(scrnMsg.idocPoDtlMatNum_02);
            scrnMsg.addCheckItem(scrnMsg.idocPoDtlOrdQty);
            scrnMsg.addCheckItem(scrnMsg.idocPoDtlUomCd);
            scrnMsg.addCheckItem(scrnMsg.idocPoDtlDelyPrtyNm);
        }
    }
    
    // 2018/11/27 S21_NA#28899 Add Start
    /**
     * Set Common Popup Parameter
     * @param scrnMsg
     * @param glblCmpyCd
     * @return
     */
    public static Object[] prepareAddressLookupPopupParameter(NWAL2260BMsg scrnMsg, String glblCmpyCd) {

        Object[] params = new Object[7];

        StringBuilder baseSql = new StringBuilder();
        baseSql.append("SELECT ");
        baseSql.append("    P.GLBL_CMPY_CD ");
        baseSql.append("  , P.EZCANCELFLAG ");
        baseSql.append("  , P.CTY_ADDR ");
        baseSql.append("  , P.ST_CD ");
        baseSql.append("  , P.POST_CD ");
        baseSql.append("  , C.CNTY_NM ");
        baseSql.append("FROM ");
        baseSql.append("    POST P ");
        baseSql.append("  , CNTY_POST_RELN R ");
        baseSql.append("  , CNTY C ");
        baseSql.append("WHERE ");
        baseSql.append("    P.GLBL_CMPY_CD = '" + glblCmpyCd + "' ");
        baseSql.append("AND P.EZCANCELFLAG = '0' ");
        baseSql.append("AND R.POST_PK(+) = P.POST_PK ");
        baseSql.append("AND R.GLBL_CMPY_CD(+) = P.GLBL_CMPY_CD ");
        baseSql.append("AND R.EZCANCELFLAG(+) = '0' ");
        baseSql.append("AND C.GLBL_CMPY_CD(+) = R.GLBL_CMPY_CD ");
        baseSql.append("AND C.EZCANCELFLAG(+) = '0' ");
        baseSql.append("AND C.CNTY_PK(+) = R.CNTY_PK ");

        List<Object[]> whereList = new ArrayList<Object[]>();
        addWhereCondition(whereList, "City", "CTY_ADDR", scrnMsg.idocPtnrCtyNm.getValue(), "Y");
        addWhereCondition(whereList, "State", "ST_CD", scrnMsg.idocPtnrStCd.getValue(), "Y");
        addWhereCondition(whereList, "Postal Code", "POST_CD", scrnMsg.idocPtnrPostCd.getValue(), "Y");
        addWhereCondition(whereList, "County", "CNTY_NM", scrnMsg.idocPtnrCntyNm.getValue(), "Y");

        List<Object[]> columnList = new ArrayList<Object[]>();
        addDisplayColumn(columnList, "City", "CTY_ADDR", BigDecimal.valueOf(25), "Y");
        addDisplayColumn(columnList, "State", "ST_CD", BigDecimal.valueOf(5), "Y");
        addDisplayColumn(columnList, "Postal Code", "POST_CD", BigDecimal.valueOf(10), "Y");
        addDisplayColumn(columnList, "County", "CNTY_NM", BigDecimal.valueOf(30), "Y");

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        addSortCondition(sortConditionList, "CTY_ADDR", "ASC");
        addSortCondition(sortConditionList, "ST_CD", "ASC");
        addSortCondition(sortConditionList, "POST_CD", "ASC");
        addSortCondition(sortConditionList, "CNTY_NM", "ASC");

        scrnMsg.L.clear();

        params[0] = "";
        params[1] = "Address Lookup Popup";
        params[2] = baseSql.toString();
        params[3] = whereList;
        params[4] = columnList;
        params[5] = sortConditionList;
        params[6] = scrnMsg.L;

        return params;
    }
    
    /**
     * arrange where condition for popup
     * @param whereList
     * @param labelName
     * @param dbColumnName
     * @param initValue
     * @param likeConditionFlag
     */
    private static void addWhereCondition(List<Object[]> whereList, String labelName, String dbColumnName, String initValue, String likeConditionFlag) {
        Object[] whereArray = new Object[4];
        whereArray[0] = labelName;
        whereArray[1] = dbColumnName;
        whereArray[2] = initValue;
        whereArray[3] = likeConditionFlag;
        whereList.add(whereArray);
    }

    /**
     * arrange display column for popup
     * @param columnList
     * @param labelName
     * @param dbColumnName
     * @param displaySize
     * @param linkFlag
     */
    private static void addDisplayColumn(List<Object[]> columnList, String labelName, String dbColumnName, BigDecimal displaySize, String linkFlag) {
        Object[] columnArray = new Object[4];
        columnArray[0] = labelName;
        columnArray[1] = dbColumnName;
        columnArray[2] = displaySize;
        columnArray[3] = linkFlag;
        columnList.add(columnArray);
    }

    /**
     * arrange sort condition for popup
     * @param sortConditionList
     * @param dbColumnName
     * @param orderBy
     */
    private static void addSortCondition(List<Object[]> sortConditionList, String dbColumnName, String orderBy) {
        Object[] sortConditionArray = new Object[2];
        sortConditionArray[0] = dbColumnName;
        sortConditionArray[1] = orderBy;
        sortConditionList.add(sortConditionArray);
    }
    // 2018/11/27 S21_NA#28899 Add End
    
}
