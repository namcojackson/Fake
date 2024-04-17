/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2800.common;

import static business.servlet.NMAL2800.constant.NMAL2800Constant.AFT_CTRY_CD;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.AFT_LOC_CTY_ADDR;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.AFT_LOC_FIRST_LINE_ADDR;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.AFT_LOC_POST_CD;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.AFT_LOC_ST_CD;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.BIZ_ID;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.BTN_CMN_APL;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.BTN_CMN_APR;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.BTN_CMN_CLR;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.BTN_CMN_DEL;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.BTN_CMN_DWL;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.BTN_CMN_RJT;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.BTN_CMN_RST;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.BTN_CMN_RTN;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.BTN_CMN_SAV;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.BTN_CMN_SUB;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.CNTY_NM;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.NMAM8656E;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.PRIV_UPD;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.SCRN_ID_00;

import java.util.List;

import business.servlet.NMAL2800.NMAL2800BMsg;
import business.servlet.NMAL2800.NMAL2800_ABMsg;
import business.servlet.NMAL2800.NMAL2800_ABMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTRY;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * NMAL2800CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/16   Fujitsu         C.Tanaka        Create          N/A
 * 2016/07/26   Fujitsu         N.Sugiura       Update          QC#12417
 * 2016/11/02   Fujitsu         C.Yokoi         Update          QC#15296
 * 2017/10/17   Fujitsu         M.Ohno          Update          QC#21782
 * 2017/10/20   Fujitsu         M.Ohno          Update          QC#21866
 * 2018/03/28   Fujitsu         R.Nakamura      Update          QC#23145
 *</pre>
 */
public class NMAL2800CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     * @param scrnMsg NMAL2800BMsg
     */
    public static void initCmnBtnProp(S21CommonHandler handler, NMAL2800BMsg scrnMsg) {

        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 1, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);

        if (hasUpdatePriv(scrnMsg)) {
            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 1, null);
        }
    }

    /**
     * Set Common Button properties.
     * @param handler Event Handler
     * @param btnProp Button Properties in Constant class
     * @param sts Status (0:Inactive, 1:Active)
     */
    public static void setCmnBtnProp(S21CommonHandler handler, String[] btnProp, int sts) {
        handler.setButtonProperties(btnProp[0], btnProp[1], btnProp[2], sts, null);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NMAL2800BMsg
     * @param scrnAMsgAry NMAL2800_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NMAL2800BMsg scrnMsg, NMAL2800_ABMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NMAL2800BMsg
     * @param scrnAMsgAry NMAL2800_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     * @param grpRows color reversal switch lines
     */
    public static void setRowsBGWithClear(NMAL2800BMsg scrnMsg, NMAL2800_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color
     * back to the initial color.
     * @param scrnMsg NMAL2800BMsg
     * @param scrnAMsgAry NMAL2800_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void clearRowsBG(NMAL2800BMsg scrnMsg, NMAL2800_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * Check if the user has update privilege.
     * @param scrnMsg NMAL2800BMsg
     * @return boolean
     */
    public static boolean hasUpdatePriv(NMAL2800BMsg scrnMsg) {

        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();

        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(BIZ_ID);
        if (funcList.contains(PRIV_UPD)) {
            return true;
        }

        return false;
    }

    /**
     * Control detail fields properties.
     * @param scrnMsg NMAL2800BMsg
     */
   // 2017/10/20 QC#21866 mod start
//    public static void ctrlDtlFieldProp(NMAL2800BMsg scrnMsg) {
   public static void ctrlDtlFieldProp(NMAL2800BMsg scrnMsg, boolean isUpload) {
   // 2017/10/20 QC#21866 mod end

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NMAL2800_ABMsg aBMsg = scrnMsg.A.no(i);
            aBMsg.rvwProsNum_A1.setInputProtected(true);
            aBMsg.befDsAcctNm_A1.setInputProtected(true);
            // 2017/10/17 QC#21782 mod start 
//            aBMsg.befBillToFirstLineAddr_A1.setInputProtected(true);
//            aBMsg.befBillToScdLineAddr_A1.setInputProtected(true);
//            aBMsg.befBillToThirdLineAddr_A1.setInputProtected(true);
//            aBMsg.befBillToFrthLineAddr_A1.setInputProtected(true);
//            aBMsg.befBillToCtyAddr_A1.setInputProtected(true);
//            aBMsg.befBillToStCd_A1.setInputProtected(true);
//            aBMsg.befBillToPostCd_A1.setInputProtected(true);
//            aBMsg.befBillToCntyNm_A1.setInputProtected(true);
            aBMsg.befLocFirstLineAddr_A1.setInputProtected(true);
            aBMsg.befLocScdLineAddr_A1.setInputProtected(true);
            aBMsg.befLocThirdLineAddr_A1.setInputProtected(true);
            aBMsg.befLocFrthLineAddr_A1.setInputProtected(true);
            aBMsg.befShipToCtyAddr_A1.setInputProtected(true);
            aBMsg.befShipToStNm_A1.setInputProtected(true);
            aBMsg.befShipToPostCd_A1.setInputProtected(true);
            aBMsg.befShipToCntyNm_A1.setInputProtected(true);
            // 2017/10/17 QC#21782 mod end
            aBMsg.xxScrItem61Txt_A1.setInputProtected(true);
            aBMsg.lineBizTpDescTxt_A1.setInputProtected(true);
            aBMsg.resrcTrtyOrgNm_A1.setInputProtected(true);
            aBMsg.candiTrtyNm_A1.setInputProtected(true);
            aBMsg.xxDtTxt_A1.setInputProtected(true);
            aBMsg.matchAcctLocNum_A1.setInputProtected(true);
            aBMsg.xxScrItem61Txt_A2.setInputProtected(true);
            aBMsg.befTelNum_A1.setInputProtected(true);
            aBMsg.befFaxNum_A1.setInputProtected(true);
            aBMsg.xxDtTxt_A2.setInputProtected(true);
            aBMsg.befDunsNum_A1.setInputProtected(true);
            aBMsg.befDsCustSicCd_A1.setInputProtected(true);
            aBMsg.befDsUltDunsNum_A1.setInputProtected(true);
            aBMsg.dsAcctNum_A1.setInputProtected(true);
            aBMsg.locNum_A1.setInputProtected(true);

            // 2017/10/20 QC#21866 mod start
//            if (ZYPCommonFunc.hasValue(aBMsg.prosRvwStsCd_A1)) {
            if (ZYPCommonFunc.hasValue(aBMsg.prosRvwStsCd_A1) && !isUpload) {
            // 2017/10/20 QC#21866 mod END
                aBMsg.prosRvwStsCd_A1.setInputProtected(true);
                aBMsg.trtyOrgNm_A1.setInputProtected(true);
                aBMsg.aftLocNum_A1.setInputProtected(true);
                aBMsg.aftDsXrefAcctCd_A1.setInputProtected(true);
                aBMsg.aftLocFirstLineAddr_A1.setInputProtected(true);
                aBMsg.aftLocScdLineAddr_A1.setInputProtected(true);
                aBMsg.aftLocThirdLineAddr_A1.setInputProtected(true);
                aBMsg.aftLocFrthLineAddr_A1.setInputProtected(true);
                aBMsg.aftLocCtyAddr_A1.setInputProtected(true);
                aBMsg.aftLocStCd_A1.setInputProtected(true);
                aBMsg.aftLocPostCd_A1.setInputProtected(true);
                aBMsg.cntyNm_A1.setInputProtected(true);
                aBMsg.aftCtryCd_A1.setInputProtected(true);
                aBMsg.aftTelNum_A1.setInputProtected(true);
                aBMsg.aftFaxNum_A1.setInputProtected(true);
                aBMsg.dsAcctUrl_A1.setInputProtected(true);
                aBMsg.aftDsAcctDunsNm_A1.setInputProtected(true);
                aBMsg.aftDsLocRevAmt_A1.setInputProtected(true);
                aBMsg.aftDunsNum_A1.setInputProtected(true);
                aBMsg.aftDsCustSicDescTxt_A1.setInputProtected(true);
                aBMsg.aftDsLocEmpNum_A1.setInputProtected(true);
                aBMsg.aftDsCustSicCd_A1.setInputProtected(true);
                aBMsg.aftDsUltDunsNum_A1.setInputProtected(true);
                // Del Start 2018/03/28 QC#23145
//                aBMsg.aftGlnNum_A1.setInputProtected(true);
                // Del End 2018/03/28 QC#23145
                aBMsg.aftDsPrntDunsNum_A1.setInputProtected(true);
                aBMsg.aftHqDunsNum_A1.setInputProtected(true);
                aBMsg.aftDsCmpySicCd_A1.setInputProtected(true);
                aBMsg.aftDsCmpySicDescTxt_A1.setInputProtected(true);
            } else {
                aBMsg.prosRvwStsCd_A1.setInputProtected(false);
                aBMsg.trtyOrgNm_A1.setInputProtected(false);
                aBMsg.aftLocNum_A1.setInputProtected(false);
                aBMsg.aftDsXrefAcctCd_A1.setInputProtected(false);
                aBMsg.aftLocFirstLineAddr_A1.setInputProtected(false);
                aBMsg.aftLocScdLineAddr_A1.setInputProtected(false);
                aBMsg.aftLocThirdLineAddr_A1.setInputProtected(false);
                aBMsg.aftLocFrthLineAddr_A1.setInputProtected(false);
                aBMsg.aftLocCtyAddr_A1.setInputProtected(false);
                aBMsg.aftLocStCd_A1.setInputProtected(false);
                aBMsg.aftLocPostCd_A1.setInputProtected(false);
                aBMsg.cntyNm_A1.setInputProtected(false);
                aBMsg.aftCtryCd_A1.setInputProtected(false);
                aBMsg.aftTelNum_A1.setInputProtected(false);
                aBMsg.aftFaxNum_A1.setInputProtected(false);
                aBMsg.dsAcctUrl_A1.setInputProtected(false);
                aBMsg.aftDsAcctDunsNm_A1.setInputProtected(false);
                aBMsg.aftDsLocRevAmt_A1.setInputProtected(false);
                aBMsg.aftDunsNum_A1.setInputProtected(false);
                aBMsg.aftDsCustSicDescTxt_A1.setInputProtected(false);
                aBMsg.aftDsLocEmpNum_A1.setInputProtected(false);
                aBMsg.aftDsCustSicCd_A1.setInputProtected(false);
                aBMsg.aftDsUltDunsNum_A1.setInputProtected(false);
                // Del Start 2018/03/28 QC#23145
//                aBMsg.aftGlnNum_A1.setInputProtected(false);
                // Del End 2018/03/28 QC#23145
                aBMsg.aftDsPrntDunsNum_A1.setInputProtected(false);
                aBMsg.aftHqDunsNum_A1.setInputProtected(false);
                aBMsg.aftDsCmpySicCd_A1.setInputProtected(false);
                aBMsg.aftDsCmpySicDescTxt_A1.setInputProtected(false);
            }
        }
    }

    /**
     * addCheckItem
     * @param scrnMsg NMAL2800BMsg
     */
    public static void addCheckItem(NMAL2800BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NMAL2800_ABMsg aBMsg = scrnMsg.A.no(i);
            scrnMsg.addCheckItem(aBMsg.prosRvwStsCd_A1);
            scrnMsg.addCheckItem(aBMsg.trtyOrgNm_A1);
        }
        scrnMsg.putErrorScreen();
    }

    /**
     * addCheckItemAfterLocationAddress
     * @param scrnMsg NMAL2800BMsg
     * @param aBMsg NMAL2800_ABMsg
     */
    public static void addCheckItemAfterLocationAddress(NMAL2800BMsg scrnMsg, NMAL2800_ABMsg aBMsg) {
        // 2016/11/02 CSA-QC#15296 Add Start
        if (!ZYPCommonFunc.hasValue(aBMsg.aftLocFirstLineAddr_A1)){
            aBMsg.aftLocFirstLineAddr_A1.setErrorInfo(1, NMAM8656E, new String[] {AFT_LOC_FIRST_LINE_ADDR });
        }
        if (!ZYPCommonFunc.hasValue(aBMsg.aftLocCtyAddr_A1)) {
            aBMsg.aftLocCtyAddr_A1.setErrorInfo(1, NMAM8656E, new String[] {AFT_LOC_CTY_ADDR });
        }
        if (!ZYPCommonFunc.hasValue(aBMsg.aftLocPostCd_A1)){
            aBMsg.aftLocPostCd_A1.setErrorInfo(1, NMAM8656E, new String[] {AFT_LOC_POST_CD });
        }
        if (!ZYPCommonFunc.hasValue(aBMsg.aftCtryCd_A1)){
            aBMsg.aftCtryCd_A1.setErrorInfo(1, NMAM8656E, new String[] {AFT_CTRY_CD });
        } else {
            if (CTRY.UNITED_STATES_OF_AMERICA.equals(aBMsg.aftCtryCd_A1.getValue())) {
                if (!ZYPCommonFunc.hasValue(aBMsg.aftLocStCd_A1)) {
                    aBMsg.aftLocStCd_A1.setErrorInfo(1, NMAM8656E, new String[] {AFT_LOC_ST_CD });
                }
                if (!ZYPCommonFunc.hasValue(aBMsg.cntyNm_A1)){
                    aBMsg.cntyNm_A1.setErrorInfo(1, NMAM8656E, new String[] {CNTY_NM });
                }
            }
        }

        scrnMsg.addCheckItem(aBMsg.aftLocFirstLineAddr_A1);
        scrnMsg.addCheckItem(aBMsg.aftLocCtyAddr_A1);
        scrnMsg.addCheckItem(aBMsg.aftLocPostCd_A1);
        scrnMsg.addCheckItem(aBMsg.aftCtryCd_A1);
        scrnMsg.addCheckItem(aBMsg.aftLocStCd_A1);
        scrnMsg.addCheckItem(aBMsg.cntyNm_A1);
        // 2016/11/02 CSA-QC#15296 Add End
    }
}
