/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7100.common;

import static business.servlet.NMAL7100.constant.NMAL7100Constant.BIZ_ID;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.BTN_01_SAV_GUARD;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.BTN_01_SAV_LABEL;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.BTN_01_SAV_NAME;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.BTN_02_SUB_GUARD;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.BTN_02_SUB_LABEL;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.BTN_02_SUB_NAME;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.BTN_03_APL_GUARD;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.BTN_03_APL_LABEL;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.BTN_03_APL_NAME;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.BTN_04_APR_GUARD;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.BTN_04_APR_LABEL;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.BTN_04_APR_NAME;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.BTN_05_REJ_GUARD;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.BTN_05_REJ_LABEL;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.BTN_05_REJ_NAME;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.BTN_06_DWL_GUARD;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.BTN_06_DWL_LABEL;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.BTN_06_DWL_NAME;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.BTN_07_DEL_GUARD;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.BTN_07_DEL_LABEL;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.BTN_07_DEL_NAME;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.BTN_08_CLE_GUARD;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.BTN_08_CLE_LABEL;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.BTN_08_CLE_NAME;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.BTN_09_RST_GUARD;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.BTN_09_RST_LABEL;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.BTN_09_RST_NAME;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.BTN_10_RTR_GUARD;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.BTN_10_RTR_LABEL;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.BTN_10_RTR_NAME;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.BTN_ACC;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.BTN_CMN;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.BTN_FLG_ITEM;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.BTN_FLG_MDL;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.BTN_FLG_NULL;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.BTN_MKT_AUD_01;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.BTN_MKT_AUD_02;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.BTN_MKT_AUD_03;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.CMN_PRM_COLUMN_NUM;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.CMN_PRM_WHERE_NUM;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.EVT_ADD_CAN_NOT_BE_USED;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.EVT_ADD_DETAIL;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.EVT_ADD_MKT_AUD;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.EVT_DEL_CAN_NOT_BE_USED;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.EVT_DEL_DETAIL;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.EVT_DEL_MKT_AUD;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.EVT_DOWNLOAD_TEMPLATE;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.EVT_MASS_UPD;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.EVT_OPENWIN_PRCCATG;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.EVT_OPENWIN_PRMOQLFY_ITEM;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.EVT_OPENWIN_PRMOQLFY_MDL;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.EVT_OPENWIN_PRMO_ITEM;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.EVT_SELECT_ALL;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.EVT_UN_SELECT_ALL;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.EVT_UPLOAD_MKT_AUD;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.EVT_UPLOAD_MKT_PROGRAM;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.HIGH_VAL_DT;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.MDSE_CD_MAX_LENGTH;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.NMAM0163E;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.NMAM8054E;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.NMAM8190E;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.SCRN_ID_00;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.TAB_CAN_NOT_BE_USED;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.TAB_MKT_AUDC;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.TXT_MKT_AUD_01;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.UPDATE_AUTHORITY;
import static business.servlet.NMAL7100.constant.NMAL7100Constant.ZZM9000E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBStringItem;
import parts.common.EZDCommonConst;
import parts.common.EZDFieldErrorException;
import parts.common.EZDGUIAttribute;
import parts.common.EZDMessageInfo;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL6800.constant.NMAL6800Constant;
import business.servlet.NMAL7100.NMAL7100BMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MKT_PRMO_AUDC_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_PRMO_QLFY_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPEZDItemErrorUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * NMAL7100CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/15   Fujitsu         M.Hara          Create          CSA
 * 2016/02/24   Fujitsu         W.Honda         Update          CSA-QC#4043
 * 2016/04/15   Fujitsu         W.Honda         Update          QC#6998
 * 09/12/2016   Fujitsu         R.Nakamura      Update          QC#11615
 * 2016/11/10   Fujitsu         W.Honda         Update          QC#15842
 * 2016/12/09   Fujitsu         M.Ohno          Update          S21_NA#16485
 * 2017/02/24   Fujitsu         R.Nakamura      Update          QC#16011
 * 2019/12/06   Fujitsu         S.Kosaka        Update          QC#54215
 *</pre>
 */
public class NMAL7100CommonLogic {

    // Add Start 2017/02/24 QC#16011
    /**
     * Update Authority Check
     * @param userProfileService S21UserProfileService
     * @return boolean
     */
    public static boolean updateAuthority(S21UserProfileService userProfileService) {
        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BIZ_ID);
        return functionIds.contains(UPDATE_AUTHORITY);
    }

    /**
     * Set Screan Control
     * @param userProfileService S21UserProfileService
     * @return boolean
     */
    public static void setScrnCtrl(EZDCommonHandler handler, NMAL7100BMsg scrnMsg, S21UserProfileService userProfileService) {

        boolean protectBtnFlg = updateAuthority(userProfileService);
        boolean protectFldFlg = !protectBtnFlg;

        // Header
        scrnMsg.prcMktPrmoNm_H1.setInputProtected(protectFldFlg);
        scrnMsg.prcMktPrmoDescTxt_H1.setInputProtected(protectFldFlg);
        scrnMsg.prcMktPrmoCmntTxt_H1.setInputProtected(protectFldFlg);
        scrnMsg.prcMktPrmoTpCd_H1.setInputProtected(protectFldFlg);
        scrnMsg.effFromDt_H1.setInputProtected(protectFldFlg);
        scrnMsg.effThruDt_H1.setInputProtected(protectFldFlg);
        scrnMsg.actvFlg_H1.setInputProtected(protectFldFlg);

        // Marketing Program Audience
        scrnMsg.xxFileData_HC.setInputProtected(protectFldFlg);
        handler.setButtonEnabled(EVT_UPLOAD_MKT_AUD, protectBtnFlg);
        handler.setButtonEnabled(EVT_ADD_MKT_AUD, protectBtnFlg);
        handler.setButtonEnabled(EVT_DEL_MKT_AUD, protectBtnFlg);
        for (int i = 0; i < scrnMsg.X.length(); i++) {
            scrnMsg.X.no(i).xxChkBox_MX.setInputProtected(protectFldFlg);
            scrnMsg.X.no(i).mktPrmoAudcCatgCd_X1.setInputProtected(protectFldFlg);
            scrnMsg.X.no(i).xxScrItem30Txt_X1.setInputProtected(protectFldFlg);
            scrnMsg.X.no(i).xxRecNmTxt_X1.setInputProtected(true);
            scrnMsg.X.no(i).mktPrmoAudcCatgCd_X2.setInputProtected(protectFldFlg);
            scrnMsg.X.no(i).xxScrItem30Txt_X2.setInputProtected(protectFldFlg);
            scrnMsg.X.no(i).xxRecNmTxt_X2.setInputProtected(true);
            scrnMsg.X.no(i).mktPrmoAudcCatgCd_X3.setInputProtected(protectFldFlg);
            scrnMsg.X.no(i).xxScrItem30Txt_X3.setInputProtected(protectFldFlg);
            scrnMsg.X.no(i).xxRecNmTxt_X3.setInputProtected(true);
            scrnMsg.X.no(i).effFromDt_MX.setInputProtected(protectFldFlg);
            scrnMsg.X.no(i).effThruDt_MX.setInputProtected(protectFldFlg);
        }

        // Canon Not Be Used
        handler.setButtonEnabled(EVT_ADD_CAN_NOT_BE_USED, protectBtnFlg);
        handler.setButtonEnabled(EVT_DEL_CAN_NOT_BE_USED, protectBtnFlg);
        for (int i = 0; i < scrnMsg.X.length(); i++) {
            scrnMsg.Y.no(i).xxChkBox_CX.setInputProtected(protectFldFlg);
            scrnMsg.Y.no(i).prcListTpCd_CX.setInputProtected(protectFldFlg);
            scrnMsg.Y.no(i).prcCatgNm_CX.setInputProtected(true);
            handler.setButtonEnabled(EVT_OPENWIN_PRCCATG, protectBtnFlg);
            scrnMsg.Y.no(i).effFromDt_CX.setInputProtected(protectFldFlg);
            scrnMsg.Y.no(i).effThruDt_CX.setInputProtected(protectFldFlg);
        }

        // Detail
        handler.setButtonEnabled(EVT_SELECT_ALL, protectBtnFlg);
        handler.setButtonEnabled(EVT_UN_SELECT_ALL, protectBtnFlg);
        handler.setButtonEnabled(EVT_ADD_DETAIL, protectBtnFlg);
        handler.setButtonEnabled(EVT_DEL_DETAIL, protectBtnFlg);
        scrnMsg.effThruDt_DH.setInputProtected(protectFldFlg);
        handler.setButtonEnabled(EVT_MASS_UPD, protectBtnFlg);
        scrnMsg.xxFileData_DH.setInputProtected(protectFldFlg);
        handler.setButtonEnabled(EVT_UPLOAD_MKT_PROGRAM, protectBtnFlg);
        handler.setButtonEnabled(EVT_DOWNLOAD_TEMPLATE, protectBtnFlg);
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxChkBox_DA.setInputProtected(protectFldFlg);
            scrnMsg.A.no(i).prcMktPrmoCd_DA.setInputProtected(protectFldFlg);
            scrnMsg.A.no(i).prcPrmoQlfyTpCd_DA.setInputProtected(protectFldFlg);
            scrnMsg.A.no(i).prcQlfyValTxt_DA.setInputProtected(protectFldFlg);
            handler.setButtonEnabled(EVT_OPENWIN_PRMOQLFY_ITEM, protectBtnFlg);
            handler.setButtonEnabled(EVT_OPENWIN_PRMOQLFY_MDL, protectBtnFlg);
            scrnMsg.A.no(i).mdseCd_DA.setInputProtected(protectFldFlg);
            handler.setButtonEnabled(EVT_OPENWIN_PRMO_ITEM, protectBtnFlg);
            scrnMsg.A.no(i).mdseDescShortTxt_DA.setInputProtected(true);
            scrnMsg.A.no(i).prmoAmt_DA.setInputProtected(protectFldFlg);
            scrnMsg.A.no(i).effFromDt_DA.setInputProtected(protectFldFlg);
            scrnMsg.A.no(i).effThruDt_DA.setInputProtected(protectFldFlg);
            scrnMsg.A.no(i).xxFullNm_D1.setInputProtected(true);
            scrnMsg.A.no(i).ezInTime_DA.setInputProtected(true);
            scrnMsg.A.no(i).xxCratDt_DA.setInputProtected(true);
            scrnMsg.A.no(i).xxFullNm_D2.setInputProtected(true);
            scrnMsg.A.no(i).ezUpTime_DA.setInputProtected(true);
            scrnMsg.A.no(i).xxLastEntryDt_DA.setInputProtected(true);
        }
    }
    // Add End 2017/02/24 QC#16011

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     * @param userProfileService S21UserProfileService
     */
    public static void initCmnBtnProp(S21CommonHandler handler, S21UserProfileService userProfileService) {

        handler.setButtonProperties(BTN_01_SAV_NAME, BTN_01_SAV_GUARD, BTN_01_SAV_LABEL, 0, null);
        // Mod Start 2017/02/24 QC#16011
        if (updateAuthority(userProfileService)) {
            handler.setButtonProperties(BTN_02_SUB_NAME, BTN_02_SUB_GUARD, BTN_02_SUB_LABEL, 1, null);
        } else {
            handler.setButtonProperties(BTN_02_SUB_NAME, BTN_02_SUB_GUARD, BTN_02_SUB_LABEL, 0, null);
        }
        // Mod End 2017/02/24 QC#16011
        handler.setButtonProperties(BTN_03_APL_NAME, BTN_03_APL_GUARD, BTN_03_APL_LABEL, 0, null);
        handler.setButtonProperties(BTN_04_APR_NAME, BTN_04_APR_GUARD, BTN_04_APR_LABEL, 0, null);
        handler.setButtonProperties(BTN_05_REJ_NAME, BTN_05_REJ_GUARD, BTN_05_REJ_LABEL, 0, null);
        handler.setButtonProperties(BTN_06_DWL_NAME, BTN_06_DWL_GUARD, BTN_06_DWL_LABEL, 1, null);
        handler.setButtonProperties(BTN_07_DEL_NAME, BTN_07_DEL_GUARD, BTN_07_DEL_LABEL, 0, null);
        handler.setButtonProperties(BTN_08_CLE_NAME, BTN_08_CLE_GUARD, BTN_08_CLE_LABEL, 1, null);
        handler.setButtonProperties(BTN_09_RST_NAME, BTN_09_RST_GUARD, BTN_09_RST_LABEL, 1, null);
        handler.setButtonProperties(BTN_10_RTR_NAME, BTN_10_RTR_GUARD, BTN_10_RTR_LABEL, 1, null);
    }

    /**
     * scrnAllGUIControl.
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL7100BMsg
     * @param userProfileService S21UserProfileService
     */
    public static void scrnAllGUIControl(EZDCommonHandler handler, NMAL7100BMsg scrnMsg, S21UserProfileService userProfileService) {
        scrnMsg.clearAllGUIAttribute(SCRN_ID_00);
        // Add Start 2017/02/27 QC#16011
        boolean uploadAuthFlg = updateAuthority(userProfileService);
        // Add End 2017/02/27 QC#16011

        // Marketing Program Audience
        if (TAB_MKT_AUDC.equals(scrnMsg.xxDplyTab_H1.getValue())) {
            // Mkt Aud Button
            for (int i = 0; i < scrnMsg.X.getValidCount(); i++) {
                NMAL7100CommonLogic.mktAudcValTxtCtrl(handler, TXT_MKT_AUD_01, i, scrnMsg.X.no(i).mktPrmoAudcCatgCd_X1.getValue(), scrnMsg);
                NMAL7100CommonLogic.mktAudcCatgBtnCtrl(handler, BTN_MKT_AUD_01, i, scrnMsg.X.no(i).mktPrmoAudcCatgCd_X1.getValue(), scrnMsg, uploadAuthFlg);
                NMAL7100CommonLogic.mktAudcCatgBtnCtrl(handler, BTN_MKT_AUD_02, i, scrnMsg.X.no(i).mktPrmoAudcCatgCd_X2.getValue(), scrnMsg, uploadAuthFlg);
                NMAL7100CommonLogic.mktAudcCatgBtnCtrl(handler, BTN_MKT_AUD_03, i, scrnMsg.X.no(i).mktPrmoAudcCatgCd_X3.getValue(), scrnMsg, uploadAuthFlg);
           }
        }
        
        // Add Start #4032 02/12/2016 
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NMAL7100CommonLogic.promQifyCatgBtnCtrl(i, scrnMsg.A.no(i).prcPrmoQlfyTpCd_DA.getValue(), scrnMsg);
        }

    }

    /**
     * prcCustAudcCategCtrl.
     * @param handler EZDCommonHandler
     * @param btnName String
     * @param idx int
     * @param mktPrmoAudcCatgCd String
     * @param scrnMsg NMAL7100BMsg
     * @param updateAuthFlg boolean
     */
    public static void mktAudcCatgBtnCtrl(EZDCommonHandler handler, String btnName, int idx, String mktPrmoAudcCatgCd, NMAL7100BMsg scrnMsg, boolean updateAuthFlg) {

        // Button Display Change
        String cmnDispAttr, accDispAttr;

        if (MKT_PRMO_AUDC_CATG.BUSINESS_LINE.equals(mktPrmoAudcCatgCd)
                || MKT_PRMO_AUDC_CATG.COA_BRANCH.equals(mktPrmoAudcCatgCd)
                || MKT_PRMO_AUDC_CATG.COA_CHANNEL.equals(mktPrmoAudcCatgCd)
                || MKT_PRMO_AUDC_CATG.ACCOUNT_GROUP.equals(mktPrmoAudcCatgCd)
                || MKT_PRMO_AUDC_CATG.CSMP_NUM.equals(mktPrmoAudcCatgCd)
                || MKT_PRMO_AUDC_CATG.CUSTOMER_PRICE_GROUP.equals(mktPrmoAudcCatgCd)) {
            cmnDispAttr = "";
            accDispAttr = "none";
        } else if (MKT_PRMO_AUDC_CATG.ACCOUNT_NUM.equals(mktPrmoAudcCatgCd)) {
            cmnDispAttr = "none";
            accDispAttr = "";
        } else {
            cmnDispAttr = "none";
            accDispAttr = "none";
        }

        EZDGUIAttribute btnAttr = new EZDGUIAttribute(SCRN_ID_00, btnName + BTN_CMN + "#" + idx);
        btnAttr.setStyleAttribute("display", cmnDispAttr);
        scrnMsg.addGUIAttribute(btnAttr);

        btnAttr = new EZDGUIAttribute(SCRN_ID_00, btnName + BTN_ACC + "#" + idx);
        btnAttr.setStyleAttribute("display", accDispAttr);
        scrnMsg.addGUIAttribute(btnAttr);

        // Button Enable
        handler.setButtonEnabled(btnName + BTN_CMN, idx, !cmnDispAttr.equals("none"));
        handler.setButtonEnabled(btnName + BTN_ACC, idx, !accDispAttr.equals("none"));

        // Add Start 2017/02/27 QC#16011
        if (!updateAuthFlg) {
            handler.setButtonEnabled(btnName + BTN_CMN, idx, false);
            handler.setButtonEnabled(btnName + BTN_ACC, idx, false);
        }
        // Add End 2017/02/27 QC#16011
    }

    /**
     * mktAudcValTxtCtrl
     * @param handler EZDCommonHandler
     * @param txtName String
     * @param idx int
     * @param mktPrmoAudcCatgCd String
     * @param scrnMsg NMAL7100BMsg
     */
    public static void mktAudcValTxtCtrl(EZDCommonHandler handler, String txtName, int idx, String mktPrmoAudcCatgCd, NMAL7100BMsg scrnMsg) {
        String dispAttr = "";

        if (MKT_PRMO_AUDC_CATG.PUBLIC.equals(mktPrmoAudcCatgCd)) {
            dispAttr = "none";
        }

        EZDGUIAttribute custAudcVal = new EZDGUIAttribute(SCRN_ID_00, txtName + "#" + idx);
        custAudcVal.setStyleAttribute("display", dispAttr);
        scrnMsg.addGUIAttribute(custAudcVal);

    }

    /**
     * createArgumentNWAL1130ForHdr
     * @param scrnMsg NMAL7100BMsg
     * @param glblCmpyCd String
     * @return Object[]
     */
    public static Object[] createArgumentNWAL1130ForHdr(NMAL7100BMsg scrnMsg, String glblCmpyCd) {

        String fmt = ZYPDateUtil.getDateFormatString(true);
        String valString;
        Object[] param = new Object[7];
        // 0:Suffix
        String suffixP0 = "";
        // 1:Screen Name
        String scrnNmP1 = "";
        // 2:Table Name
        StringBuilder tblNmP2 = new StringBuilder();
        // 3:Criteria
        List<Object[]> whereListP3 = new ArrayList<Object[]>();
        // 4:Column
        List<Object[]> columnListP4 = new ArrayList<Object[]>();
        // 5:Sort
        List<Object[]> sortConditionListP5 = new ArrayList<Object[]>();
        // 6:Result(BMsg.R)

        suffixP0 = "";
        scrnNmP1 = "Marketing Program ID Search";

        tblNmP2.append(" SELECT");
        tblNmP2.append(" MP.GLBL_CMPY_CD AS GLBL_CMPY_CD ");
        tblNmP2.append(",MP.EZCANCELFLAG AS EZCANCELFLAG ");
        tblNmP2.append(",MP.PRC_MKT_PRMO_PK AS PRC_MKT_PRMO_PK ");
        tblNmP2.append(",MP.PRC_MKT_PRMO_NM AS PRC_MKT_PRMO_NM ");
        tblNmP2.append(",TO_CHAR(TO_DATE(MP.EFF_FROM_DT,'YYYYMMDD'),'").append(fmt).append("') AS EFF_FROM_DT ");
        tblNmP2.append(",TO_CHAR(TO_DATE(MP.EFF_THRU_DT,'YYYYMMDD'),'").append(fmt).append("') AS EFF_THRU_DT ");
        tblNmP2.append(" FROM PRC_MKT_PRMO MP");
        tblNmP2.append(" WHERE");
        tblNmP2.append(" MP.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
        tblNmP2.append(" AND MP.EZCANCELFLAG = '").append("0").append("'");

        Object[] whereArray;
        valString = "";
        if (ZYPCommonFunc.hasValue(scrnMsg.prcMktPrmoPk_H1)) {
            valString = scrnMsg.prcMktPrmoPk_H1.getValue().toString();
        }
        whereArray = createWhereArray("Marketing Program ID", "PRC_MKT_PRMO_PK", valString, true);
        whereListP3.add(whereArray);
        whereArray = createWhereArray("Marketing Program Nm", "PRC_MKT_PRMO_NM", null, true);
        whereListP3.add(whereArray);
        whereArray = createWhereArray("Date From", "EFF_FROM_DT", null, true);
        whereListP3.add(whereArray);
        whereArray = createWhereArray("Date To", "EFF_THRU_DT", null, true);
        whereListP3.add(whereArray);

        Object[] columnArray;
        columnArray = createColumnArray("Marketing Program ID", "PRC_MKT_PRMO_PK", 14, true);
        columnListP4.add(columnArray);
        columnArray = createColumnArray("Marketing Program Nm", "PRC_MKT_PRMO_NM", 50, false);
        columnListP4.add(columnArray);
        columnArray = createColumnArray("Date From", "EFF_FROM_DT", 13, false);
        columnListP4.add(columnArray);
        columnArray = createColumnArray("Date To", "EFF_THRU_DT", 13, false);
        columnListP4.add(columnArray);

        Object[] sortArray;
        sortArray = createSortArray("PRC_MKT_PRMO_PK", true);
        sortConditionListP5.add(sortArray);

        param[0] = suffixP0;
        param[1] = scrnNmP1;
        param[2] = tblNmP2.toString();
        param[3] = whereListP3;
        param[4] = columnListP4;
        param[5] = sortConditionListP5;
        param[6] = scrnMsg.R;

        return param;
    }

    /**
     * createArgumentNWAL1130
     * @param scrnMsg NMAL7100BMsg
     * @param rowIdx int
     * @param mktPrmoAudcCatgCd String
     * @param glblCmpyCd String
     * @return Object[]
     */
    public static Object[] createArgumentNWAL1130(NMAL7100BMsg scrnMsg, int rowIdx, String mktPrmoAudcCatgCd, String glblCmpyCd) {

        Object[] args = null;

        if (MKT_PRMO_AUDC_CATG.BUSINESS_LINE.equals(mktPrmoAudcCatgCd)) {
            args = createArgumentNWAL1130ForBL(scrnMsg, glblCmpyCd);
        } else if (MKT_PRMO_AUDC_CATG.COA_CHANNEL.equals(mktPrmoAudcCatgCd)) {
            args = createArgumentNWAL1130ForCC(scrnMsg, glblCmpyCd);
        } else if (MKT_PRMO_AUDC_CATG.ACCOUNT_GROUP.equals(mktPrmoAudcCatgCd)) {
            args = createArgumentNWAL1130ForAG(scrnMsg, glblCmpyCd);
        } else if (MKT_PRMO_AUDC_CATG.COA_BRANCH.equals(mktPrmoAudcCatgCd)) {
            args = createArgumentNWAL1130ForCB(scrnMsg, glblCmpyCd);
        } else if (MKT_PRMO_AUDC_CATG.CSMP_NUM.equals(mktPrmoAudcCatgCd)) {
            args = createArgumentNWAL1130ForCN(scrnMsg, glblCmpyCd);
        } else if (MKT_PRMO_AUDC_CATG.CUSTOMER_PRICE_GROUP.equals(mktPrmoAudcCatgCd)) {
            args = createArgumentNWAL1130ForCPG(scrnMsg, glblCmpyCd);
        }

        return args;
    }

    /**
     * createArgumentNWAL1130ForBL
     * @param scrnMsg NMAL7100BMsg
     * @param glblCmpyCd String
     * @return Object[]
     */
    public static Object[] createArgumentNWAL1130ForBL(NMAL7100BMsg scrnMsg, String glblCmpyCd) {

        Object[] param = new Object[7];
        // 0:Suffix
        String suffixP0 = "";
        // 1:Screen Name
        String scrnNmP1 = "";
        // 2:Table Name
        StringBuilder tblNmP2 = new StringBuilder();
        // 3:Criteria
        List<Object[]> whereListP3 = new ArrayList<Object[]>();
        // 4:Column
        List<Object[]> columnListP4 = new ArrayList<Object[]>();
        // 5:Sort
        List<Object[]> sortConditionListP5 = new ArrayList<Object[]>();
        // 6:Result(BMsg.R)

        suffixP0 = "";
        scrnNmP1 = "Line Of Business Search";
        tblNmP2.append("LINE_BIZ_TP");
        Object[] whereArray;
        whereArray = createWhereArray("Line Of Business Cd", "LINE_BIZ_TP_CD", "", true);
        whereListP3.add(whereArray);
        whereArray = createWhereArray("Line Of Business Nm", "LINE_BIZ_TP_NM", "", true);
        whereListP3.add(whereArray);

        Object[] columnArray;
        columnArray = createColumnArray("Line Of Business Cd", "LINE_BIZ_TP_CD", 20, true);
        columnListP4.add(columnArray);
        columnArray = createColumnArray("Line Of Business Nm", "LINE_BIZ_TP_NM", 70, false);
        columnListP4.add(columnArray);

        Object[] sortArray;
        sortArray = createSortArray("LINE_BIZ_TP_SORT_NUM", true);
        sortConditionListP5.add(sortArray);

        param[0] = suffixP0;
        param[1] = scrnNmP1;
        param[2] = tblNmP2.toString();
        param[3] = whereListP3;
        param[4] = columnListP4;
        param[5] = sortConditionListP5;
        param[6] = scrnMsg.R;

        return param;
    }

    /**
     * createArgumentNWAL1130ForCC
     * @param scrnMsg NMAL7100BMsg
     * @param glblCmpyCd String
     * @return Object[]
     */
    public static Object[] createArgumentNWAL1130ForCC(NMAL7100BMsg scrnMsg, String glblCmpyCd) {

        Object[] param = new Object[7];
        // 0:Suffix
        String suffixP0 = "";
        // 1:Screen Name
        String scrnNmP1 = "";
        // 2:Table Name
        StringBuilder tblNmP2 = new StringBuilder();
        // 3:Criteria
        List<Object[]> whereListP3 = new ArrayList<Object[]>();
        // 4:Column
        List<Object[]> columnListP4 = new ArrayList<Object[]>();
        // 5:Sort
        List<Object[]> sortConditionListP5 = new ArrayList<Object[]>();
        // 6:Result(BMsg.R)

        suffixP0 = "";
        scrnNmP1 = "COA Channel Search";
        tblNmP2.append("COA_CH");
        Object[] whereArray;
        whereArray = createWhereArray("COA Channel Code", "COA_CH_CD", "", true);
        whereListP3.add(whereArray);
        whereArray = createWhereArray("COA Chanel Name", "COA_CH_NM", "", true);
        whereListP3.add(whereArray);

        Object[] columnArray;
        columnArray = createColumnArray("COA Channel Code", "COA_CH_CD", 20, true);
        columnListP4.add(columnArray);
        columnArray = createColumnArray("COA Chanel Name", "COA_CH_NM", 70, false);
        columnListP4.add(columnArray);

        Object[] sortArray;
        sortArray = createSortArray("COA_CH_SORT_NUM", true);
        sortConditionListP5.add(sortArray);

        param[0] = suffixP0;
        param[1] = scrnNmP1;
        param[2] = tblNmP2.toString();
        param[3] = whereListP3;
        param[4] = columnListP4;
        param[5] = sortConditionListP5;
        param[6] = scrnMsg.R;

        return param;
    }

    /**
     * createArgumentNWAL1130ForAG
     * @param scrnMsg NMAL7100BMsg
     * @param glblCmpyCd String
     * @return Object[]
     */
    public static Object[] createArgumentNWAL1130ForAG(NMAL7100BMsg scrnMsg, String glblCmpyCd) {

        Object[] param = new Object[7];
        // 0:Suffix
        String suffixP0 = "";
        // 1:Screen Name
        String scrnNmP1 = "";
        // 2:Table Name
        StringBuilder tblNmP2 = new StringBuilder();
        // 3:Criteria
        List<Object[]> whereListP3 = new ArrayList<Object[]>();
        // 4:Column
        List<Object[]> columnListP4 = new ArrayList<Object[]>();
        // 5:Sort
        List<Object[]> sortConditionListP5 = new ArrayList<Object[]>();
        // 6:Result(BMsg.R)

        suffixP0 = "";
        scrnNmP1 = "Account Group Search";
        tblNmP2.append("DS_ACCT_GRP");
        Object[] whereArray;
        whereArray = createWhereArray("Account Group Code", "DS_ACCT_GRP_CD", "", true);
        whereListP3.add(whereArray);
        whereArray = createWhereArray("Account Group Name", "DS_ACCT_GRP_NM", "", true);
        whereListP3.add(whereArray);

        Object[] columnArray;
        columnArray = createColumnArray("Account Group Code", "DS_ACCT_GRP_CD", 20, true);
        columnListP4.add(columnArray);
        columnArray = createColumnArray("Account Group Name", "DS_ACCT_GRP_NM", 70, false);
        columnListP4.add(columnArray);

        Object[] sortArray;
        sortArray = createSortArray("DS_ACCT_GRP_SORT_NUM", true);
        sortConditionListP5.add(sortArray);

        param[0] = suffixP0;
        param[1] = scrnNmP1;
        param[2] = tblNmP2.toString();
        param[3] = whereListP3;
        param[4] = columnListP4;
        param[5] = sortConditionListP5;
        param[6] = scrnMsg.R;

        return param;
    }

    /**
     * createArgumentNWAL1130ForCB
     * @param scrnMsg NMAL7100BMsg
     * @param glblCmpyCd String
     * @return Object[]
     */
    public static Object[] createArgumentNWAL1130ForCB(NMAL7100BMsg scrnMsg, String glblCmpyCd) {

        Object[] param = new Object[7];
        // 0:Suffix
        String suffixP0 = "";
        // 1:Screen Name
        String scrnNmP1 = "";
        // 2:Table Name
        StringBuilder tblNmP2 = new StringBuilder();
        // 3:Criteria
        List<Object[]> whereListP3 = new ArrayList<Object[]>();
        // 4:Column
        List<Object[]> columnListP4 = new ArrayList<Object[]>();
        // 5:Sort
        List<Object[]> sortConditionListP5 = new ArrayList<Object[]>();
        // 6:Result(BMsg.R)

        suffixP0 = "";
        scrnNmP1 = "COA Branch Search";
        tblNmP2.append("COA_BR");
        Object[] whereArray;
        whereArray = createWhereArray("COA Branch Code", "COA_BR_CD", "", true);
        whereListP3.add(whereArray);
        whereArray = createWhereArray("COA Branch Name", "COA_BR_NM", "", true);
        whereListP3.add(whereArray);

        Object[] columnArray;
        columnArray = createColumnArray("COA Branch Code", "COA_BR_CD", 20, true);
        columnListP4.add(columnArray);
        columnArray = createColumnArray("COA Branch Name", "COA_BR_NM", 70, false);
        columnListP4.add(columnArray);

        Object[] sortArray;
        sortArray = createSortArray("COA_BR_SORT_NUM", true);
        sortConditionListP5.add(sortArray);

        param[0] = suffixP0;
        param[1] = scrnNmP1;
        param[2] = tblNmP2.toString();
        param[3] = whereListP3;
        param[4] = columnListP4;
        param[5] = sortConditionListP5;
        param[6] = scrnMsg.R;

        return param;
    }

    /**
     * createArgumentNWAL1130ForCN
     * @param scrnMsg NMAL7100BMsg
     * @param glblCmpyCd String
     * @return Object[]
     */
    public static Object[] createArgumentNWAL1130ForCN(NMAL7100BMsg scrnMsg, String glblCmpyCd) {

        Object[] param = new Object[7];
        // 0:Suffix
        String suffixP0 = "";
        // 1:Screen Name
        String scrnNmP1 = "";
        // 2:Table Name
        StringBuilder tblNmP2 = new StringBuilder();
        // 3:Criteria
        List<Object[]> whereListP3 = new ArrayList<Object[]>();
        // 4:Column
        List<Object[]> columnListP4 = new ArrayList<Object[]>();
        // 5:Sort
        List<Object[]> sortConditionListP5 = new ArrayList<Object[]>();
        // 6:Result(BMsg.R)

        suffixP0 = "";
        scrnNmP1 = "CSMP Search";
        tblNmP2.append("CSMP_CONTR");
        Object[] whereArray;
        whereArray = createWhereArray("CSMP Number", "CSMP_NUM", "", true);
        whereListP3.add(whereArray);
        whereArray = createWhereArray("Account Number", "DS_ACCT_NUM", "", true);
        whereListP3.add(whereArray);
        whereArray = createWhereArray("Account Name", "DS_ACCT_NM", "", true);
        whereListP3.add(whereArray);

        Object[] columnArray;
        columnArray = createColumnArray("CSMP Number", "CSMP_NUM", 15, true);
        columnListP4.add(columnArray);
        columnArray = createColumnArray("Account Number", "DS_ACCT_NUM", 20, false);
        columnListP4.add(columnArray);
        columnArray = createColumnArray("Account Name", "DS_ACCT_NM", 50, false);
        columnListP4.add(columnArray);

        Object[] sortArray;
        sortArray = createSortArray("CSMP_NUM", true);
        sortConditionListP5.add(sortArray);

        param[0] = suffixP0;
        param[1] = scrnNmP1;
        param[2] = tblNmP2.toString();
        param[3] = whereListP3;
        param[4] = columnListP4;
        param[5] = sortConditionListP5;
        param[6] = scrnMsg.R;

        return param;
    }

    /**
     * createArgumentNWAL1130ForCPG
     * @param scrnMsg NMAL7100BMsg
     * @param glblCmpyCd String
     * @return Object[]
     */
    public static Object[] createArgumentNWAL1130ForCPG(NMAL7100BMsg scrnMsg, String glblCmpyCd) {

        Object[] param = new Object[7];
        // 0:Suffix
        String suffixP0 = "";
        // 1:Screen Name
        String scrnNmP1 = "";
        // 2:Table Name
        StringBuilder tblNmP2 = new StringBuilder();
        // 3:Criteria
        List<Object[]> whereListP3 = new ArrayList<Object[]>();
        // 4:Column
        List<Object[]> columnListP4 = new ArrayList<Object[]>();
        // 5:Sort
        List<Object[]> sortConditionListP5 = new ArrayList<Object[]>();
        // 6:Result(BMsg.R)

        suffixP0 = "";
        scrnNmP1 = "Price Group Search";
        // START QC#6998 04/15/2016 MOD
//        tblNmP2.append("PRC_GRP");
        tblNmP2.append(" SELECT ");
        tblNmP2.append(" PG.PRC_GRP_NM ");
        tblNmP2.append(" , PG.PRC_GRP_PK ");
        tblNmP2.append(" , PG.GLBL_CMPY_CD AS GLBL_CMPY_CD ");
        tblNmP2.append(" , PG.EZCANCELFLAG AS EZCANCELFLAG ");
        tblNmP2.append(" FROM PRC_GRP PG  ");
        tblNmP2.append(" WHERE PG.GLBL_CMPY_CD = '").append(glblCmpyCd).append("' ");
        tblNmP2.append(" AND PG.EZCANCELFLAG = '0' ");
        tblNmP2.append(" AND PG.PRC_GRP_TP_CD = '").append(PRC_GRP_TP.CUSTOMER_PRICING_GROUP).append("' ");
        // END QC#6998 04/15/2016 MOD

        Object[] whereArray;
        whereArray = createWhereArray("Price Group Code", "PRC_GRP_PK", "", true);
        whereListP3.add(whereArray);
        // START QC#6998 04/15/2016 MOD
//        whereArray = createWhereArray("Account Name", "PRC_GRP_NM", "", true);
        whereArray = createWhereArray("Price Group Name", "PRC_GRP_NM", "", true);
        // END QC#6998 04/15/2016 MOD
        whereListP3.add(whereArray);

        Object[] columnArray;
        columnArray = createColumnArray("Price Group Code", "PRC_GRP_PK", 20, true);
        columnListP4.add(columnArray);
        // START QC#6998 04/15/2016 MOD
//        columnArray = createColumnArray("Account Name", "PRC_GRP_NM", 70, false);
        columnArray = createColumnArray("Price Group Name", "PRC_GRP_NM", 70, false);
        // END QC#6998 04/15/2016 MOD
        columnListP4.add(columnArray);

        Object[] sortArray;
        sortArray = createSortArray("PRC_GRP_PK", true);
        sortConditionListP5.add(sortArray);

        param[0] = suffixP0;
        param[1] = scrnNmP1;
        param[2] = tblNmP2.toString();
        param[3] = whereListP3;
        param[4] = columnListP4;
        param[5] = sortConditionListP5;
        param[6] = scrnMsg.R;

        return param;
    }

    /**
     * createWhereArray
     * @param dispName String
     * @param colName String
     * @param value Object
     * @param isLike Boolean
     * @return Object[]
     */
    public static Object[] createWhereArray(String dispName, String colName, Object value, Boolean isLike) {
        Object[] whereArray = new Object[4];
        whereArray[0] = dispName;
        whereArray[1] = colName;
        whereArray[2] = value;
        if (isLike) {
            whereArray[3] = "Y";
        } else {
            whereArray[3] = "N";
        }

        return whereArray;
    }

    /**
     * checkAllItemInput
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL7100BMsg
     * @param userProfileService S21UserProfileService
     */
    public static void checkAllItemInput(EZDCommonHandler handler, NMAL7100BMsg scrnMsg, S21UserProfileService userProfileService) {
        boolean isError = false;

        scrnMsg.addCheckItem(scrnMsg.prcMktPrmoPk_H1);
        scrnMsg.addCheckItem(scrnMsg.prcMktPrmoNm_H1);
        scrnMsg.addCheckItem(scrnMsg.prcMktPrmoDescTxt_H1);
        scrnMsg.addCheckItem(scrnMsg.prcMktPrmoCmntTxt_H1);
        scrnMsg.addCheckItem(scrnMsg.prcMktPrmoTpCd_H1);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_H1);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_H1);

        // Mandatory
        if (!ZYPCommonFunc.hasValue(scrnMsg.prcMktPrmoNm_H1)) {
            scrnMsg.prcMktPrmoNm_H1.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.prcMktPrmoNm_H1.getNameForMessage()});
            scrnMsg.addCheckItem(scrnMsg.prcMktPrmoNm_H1);
            isError = true;
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.prcMktPrmoTpCd_H1)) {
            scrnMsg.prcMktPrmoTpCd_H1.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.prcMktPrmoTpCd_H1.getNameForMessage()});
            scrnMsg.addCheckItem(scrnMsg.prcMktPrmoTpCd_H1);
            isError = true;
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.effFromDt_H1)) {
            scrnMsg.effFromDt_H1.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.effFromDt_H1.getNameForMessage()});
            scrnMsg.addCheckItem(scrnMsg.effFromDt_H1);
            isError = true;
        }

        if (isError || ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab_H1, TAB_MKT_AUDC);
            // Mod Start 2017/02/27 QC#16011
//            scrnAllGUIControl(handler, scrnMsg);
            scrnAllGUIControl(handler, scrnMsg, userProfileService);
            // Mod End 2017/02/27 QC#16011
            scrnMsg.putErrorScreen();
            throw new EZDFieldErrorException();
        }

        for (int i = 0; i < scrnMsg.X.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.X.no(i).xxScrItem30Txt_X1);
            scrnMsg.addCheckItem(scrnMsg.X.no(i).xxScrItem30Txt_X2);
            scrnMsg.addCheckItem(scrnMsg.X.no(i).xxScrItem30Txt_X3);
            scrnMsg.addCheckItem(scrnMsg.X.no(i).effFromDt_MX);
            scrnMsg.addCheckItem(scrnMsg.X.no(i).effThruDt_MX);

            // Mandatory

            if (!ZYPCommonFunc.hasValue(scrnMsg.X.no(i).mktPrmoAudcCatgCd_X1)) {
                scrnMsg.X.no(i).mktPrmoAudcCatgCd_X1.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.X.no(i).mktPrmoAudcCatgCd_X1.getNameForMessage()});
                scrnMsg.addCheckItem(scrnMsg.X.no(i).mktPrmoAudcCatgCd_X1);
                isError = true;
            }
            if (!MKT_PRMO_AUDC_CATG.PUBLIC.equals(scrnMsg.X.no(i).mktPrmoAudcCatgCd_X1.getValue())) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.X.no(i).xxScrItem30Txt_X1)) {
                    scrnMsg.X.no(i).xxScrItem30Txt_X1.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.X.no(i).xxScrItem30Txt_X1.getNameForMessage()});
                    scrnMsg.addCheckItem(scrnMsg.X.no(i).xxScrItem30Txt_X1);
                    isError = true;
                }
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.X.no(i).effFromDt_MX)) {
                scrnMsg.X.no(i).effFromDt_MX.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.X.no(i).effFromDt_MX.getNameForMessage()});
                scrnMsg.addCheckItem(scrnMsg.X.no(i).effFromDt_MX);
                isError = true;
            }
        }
        if (isError || ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab_H1, TAB_MKT_AUDC);
            // Mod Start 2017/02/27 QC#16011
//            scrnAllGUIControl(handler, scrnMsg);
            scrnAllGUIControl(handler, scrnMsg, userProfileService);
            // Mod End 2017/02/27 QC#16011
            scrnMsg.putErrorScreen();
            throw new EZDFieldErrorException();
        }

        isError = false;
        for (int i = 0; i < scrnMsg.Y.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.Y.no(i).prcCatgCd_CX);
            scrnMsg.addCheckItem(scrnMsg.Y.no(i).prcCatgNm_CX);
            scrnMsg.addCheckItem(scrnMsg.Y.no(i).effFromDt_CX);
            scrnMsg.addCheckItem(scrnMsg.Y.no(i).effThruDt_CX);

            // Mandatory
            if (!ZYPCommonFunc.hasValue(scrnMsg.Y.no(i).prcCatgCd_CX)) {
                scrnMsg.Y.no(i).prcCatgCd_CX.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.Y.no(i).prcCatgCd_CX.getNameForMessage()});
                scrnMsg.addCheckItem(scrnMsg.Y.no(i).prcCatgCd_CX);
                isError = true;
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.Y.no(i).effFromDt_CX)) {
                scrnMsg.Y.no(i).effFromDt_CX.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.Y.no(i).effFromDt_CX.getNameForMessage()});
                scrnMsg.addCheckItem(scrnMsg.Y.no(i).effFromDt_CX);
                isError = true;
            }
        }


        if (isError || ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab_H1, TAB_CAN_NOT_BE_USED);
            // Mod Start 2017/02/27 QC#16011
//            scrnAllGUIControl(handler, scrnMsg);
            scrnAllGUIControl(handler, scrnMsg, userProfileService);
            // Mod End 2017/02/27 QC#16011
            scrnMsg.putErrorScreen();
            throw new EZDFieldErrorException();
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prcMktPrmoCd_DA);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prcPrmoQlfyTpCd_DA);

            // Add Start 2016/12/09 M.Ohno S21_NA#16485
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcPrmoQlfyTpCd_DA) //
                    && !PRC_PRMO_QLFY_TP.SERVICE_MODEL.equals(scrnMsg.A.no(i).prcPrmoQlfyTpCd_DA.getValue())) {

                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcQlfyValTxt_DA) //
                        && scrnMsg.A.no(i).prcQlfyValTxt_DA.getValue().length() > MDSE_CD_MAX_LENGTH) {
                    scrnMsg.A.no(i).prcQlfyValTxt_DA.setErrorInfo(1, NMAM0163E, new String[] {scrnMsg.A.no(i).prcQlfyValTxt_DA.getValue(), "Merchandise"});
                    isError = true;
                }
            }
            // Add End   2016/12/09 M.Ohno S21_NA#16485
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prcQlfyValTxt_DA);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseCd_DA);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prmoAmt_DA);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_DA);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_DA);

            // Mandatory
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcMktPrmoCd_DA)) {
                scrnMsg.A.no(i).prcMktPrmoCd_DA.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.A.no(i).prcMktPrmoCd_DA.getNameForMessage()});
                scrnMsg.addCheckItem(scrnMsg.A.no(i).prcMktPrmoCd_DA);
                isError = true;
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcPrmoQlfyTpCd_DA)) {
                scrnMsg.A.no(i).prcPrmoQlfyTpCd_DA.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.A.no(i).prcPrmoQlfyTpCd_DA.getNameForMessage()});
                scrnMsg.addCheckItem(scrnMsg.A.no(i).prcPrmoQlfyTpCd_DA);
                isError = true;
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcQlfyValTxt_DA)) {
                scrnMsg.A.no(i).prcQlfyValTxt_DA.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.A.no(i).prcQlfyValTxt_DA.getNameForMessage()});
                scrnMsg.addCheckItem(scrnMsg.A.no(i).prcQlfyValTxt_DA);
                isError = true;
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).mdseCd_DA)) {
                scrnMsg.A.no(i).mdseCd_DA.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.A.no(i).mdseCd_DA.getNameForMessage()});
                scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseCd_DA);
                isError = true;
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prmoAmt_DA)) {
                scrnMsg.A.no(i).prmoAmt_DA.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.A.no(i).prmoAmt_DA.getNameForMessage()});
                scrnMsg.addCheckItem(scrnMsg.A.no(i).prmoAmt_DA);
                isError = true;
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).effFromDt_DA)) {
                scrnMsg.A.no(i).effFromDt_DA.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.A.no(i).effFromDt_DA.getNameForMessage()});
                scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_DA);
                isError = true;
            }

        }

        if (isError || ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            // Mod Start 2017/02/27 QC#16011
//            scrnAllGUIControl(handler, scrnMsg);
            scrnAllGUIControl(handler, scrnMsg, userProfileService);
            // Mod End 2017/02/27 QC#16011
            scrnMsg.putErrorScreen();
            throw new EZDFieldErrorException();
        }

        scrnMsg.putErrorScreen();
    }

    // START QC#15842 11/10/2016 ADD
    /**
     * checkAllItemInput
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL7100BMsg
     * @param userProfileService S21UserProfileService
     */
    public static void checkDetail(EZDCommonHandler handler, NMAL7100BMsg scrnMsg, S21UserProfileService userProfileService) {
        boolean isError = false;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prcMktPrmoCd_DA);
            if (scrnMsg.A.no(i).prcMktPrmoCd_DA.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.A.no(i).getErrorInfo("prcMktPrmoCd_DA");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.A.no(i).prcMktPrmoCd_DA.clearErrorInfo();
                }
            }

            scrnMsg.addCheckItem(scrnMsg.A.no(i).prcPrmoQlfyTpCd_DA);
            if (scrnMsg.A.no(i).prcPrmoQlfyTpCd_DA.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.A.no(i).getErrorInfo("prcPrmoQlfyTpCd_DA");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.A.no(i).prcPrmoQlfyTpCd_DA.clearErrorInfo();
                }
            }

            // Add Start 2016/12/09 M.Ohno S21_NA#16485
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcPrmoQlfyTpCd_DA) //
                    && !PRC_PRMO_QLFY_TP.SERVICE_MODEL.equals(scrnMsg.A.no(i).prcPrmoQlfyTpCd_DA.getValue())) {

                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcQlfyValTxt_DA) //
                        && scrnMsg.A.no(i).prcQlfyValTxt_DA.getValue().length() > MDSE_CD_MAX_LENGTH) {
                    scrnMsg.A.no(i).prcQlfyValTxt_DA.setErrorInfo(1, NMAM0163E, new String[] {scrnMsg.A.no(i).prcQlfyValTxt_DA.getValue(), "Merchandise"});
                    isError = true;
                }
            }
            // Add End   2016/12/09 M.Ohno S21_NA#16485
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prcQlfyValTxt_DA);
            if (scrnMsg.A.no(i).prcQlfyValTxt_DA.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.A.no(i).getErrorInfo("prcQlfyValTxt_DA");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.A.no(i).prcQlfyValTxt_DA.clearErrorInfo();
                }
            }

            scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseCd_DA);
            if (scrnMsg.A.no(i).mdseCd_DA.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.A.no(i).getErrorInfo("mdseCd_DA");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.A.no(i).mdseCd_DA.clearErrorInfo();
                }
            }

            scrnMsg.addCheckItem(scrnMsg.A.no(i).prmoAmt_DA);
            if (scrnMsg.A.no(i).mdseCd_DA.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.A.no(i).getErrorInfo("mdseCd_DA");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.A.no(i).mdseCd_DA.clearErrorInfo();
                }
            }
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_DA);
            if (scrnMsg.A.no(i).effFromDt_DA.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.A.no(i).getErrorInfo("effFromDt_DA");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.A.no(i).effFromDt_DA.clearErrorInfo();
                }
            }

            scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_DA);

        }

        if (isError || ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            // Mod Start 2017/02/27 QC#16011
//            scrnAllGUIControl(handler, scrnMsg);
            scrnAllGUIControl(handler, scrnMsg, userProfileService);
            // Mod End 2017/02/27 QC#16011
            scrnMsg.putErrorScreen();
            throw new EZDFieldErrorException();
        }

        scrnMsg.putErrorScreen();
    }
    // END QC#15842 11/10/2016 ADD

    /**
     * chkSelectPricListDetail
     * @param scrnMsg NMAL7100BMsg
     */
    public static void chkSelectPricListDetail(NMAL7100BMsg scrnMsg) {
        List<Integer> selectRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_DA", ZYPConstant.FLG_ON_Y);

        if (scrnMsg.A.getValidCount() == 0) {
            scrnMsg.setMessageInfo(NMAM8190E);
            throw new EZDFieldErrorException();
        }

        if (selectRows.isEmpty()) {
            scrnMsg.setMessageInfo(NMAM8054E);
            throw new EZDFieldErrorException();
        }

    }

    /**
     * createColumnArray
     * @param dispName String
     * @param colName  String
     * @param width long
     * @param isLinkFlag Boolean
     * @return Object[]
     */
    public static Object[] createColumnArray(String dispName, String colName, long width, Boolean isLinkFlag) {
        Object[] columnArray = new Object[4];
        columnArray[0] = dispName;
        columnArray[1] = colName;
        columnArray[2] = BigDecimal.valueOf(width);
        if (isLinkFlag) {
            columnArray[3] = "Y";
        } else {
            columnArray[3] = "N";
        }

        return columnArray;
    }

    /**
     * createSortArray
     * @param colName String
     * @param isAsc Boolean
     * @return Object[]
     */
    public static Object[] createSortArray(String colName, Boolean isAsc) {
        Object[] sortArray = new Object[2];
        sortArray[0] = colName;
        if (isAsc) {
            sortArray[1] = "ASC";
        } else {
            sortArray[1] = "DESC";
        }

        return sortArray;
    }

    /**
     * createArgumentNMWAL6760
     * @param acctNumItem EZDBStringItem
     * @param accNmItem EZDBStringItem
     * @return Object[]
     */
    public static Object[] createArgumentNMWAL6760(EZDBStringItem acctNumItem, EZDBStringItem accNmItem) {

        Object[] param = new Object[2];
        param[0] = acctNumItem;
        param[1] = accNmItem;

        return param;
    }

    /**
     * Convert To BigDecimal
     * @param val Object
     * @return BigDecimal
     */
    public static BigDecimal convToBigDecimal(Object val) {
        if (null == val) {
            return BigDecimal.ZERO;
        } else if (val instanceof BigDecimal) {
            return (BigDecimal) val;
        } else if (val instanceof EZDBStringItem) {
            return convToBigDecimal(((EZDBStringItem) val).getValue());
        } else if (val instanceof EZDBBigDecimalItem) {
            return ((EZDBBigDecimalItem) val).getValue();
        } else {
            return new BigDecimal(val.toString());
        }
    }


    // Add Start #4032 02/12/2016
    /**
     * prcCustAudcCategCtrl.
     * @param idx int
     * @param mktPrmoAudcCatgCd String
     * @param scrnMsg NMAL7100BMsg
     */
    public static void promQifyCatgBtnCtrl(int idx, String mktPrmoAudcCatgCd, NMAL7100BMsg scrnMsg) {

        if (PRC_PRMO_QLFY_TP.ITEM_CODE.equals(mktPrmoAudcCatgCd)
                || PRC_PRMO_QLFY_TP.BUNDLE.equals(mktPrmoAudcCatgCd)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).xxBtnFlg_DA, BTN_FLG_ITEM);
        } else if (PRC_PRMO_QLFY_TP.SERVICE_MODEL.equals(mktPrmoAudcCatgCd)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).xxBtnFlg_DA, BTN_FLG_MDL);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).xxBtnFlg_DA, BTN_FLG_NULL);
        }

    }

    /**
     * createArgumentNWAL1130
     * @param scrnMsg NMAL7100BMsg
     * @param rowIdx int
     * @param mktPrmoAudcCatgCd String
     * @param glblCmpyCd String
     * @return Object[]
     */
    public static Object[] createArgumentNWAL1130_Qlfy(NMAL7100BMsg scrnMsg, int rowIdx, String mktPrmoAudcCatgCd, String glblCmpyCd) {

        Object[] args = null;

        if (PRC_PRMO_QLFY_TP.SERVICE_MODEL.equals(mktPrmoAudcCatgCd)) {
            args = createArgumentNWAL1130ForMDL(scrnMsg, rowIdx, glblCmpyCd);
        }

        return args;
    }

    /**
     * createArgumentNWAL1130ForBL
     * @param scrnMsg NMAL7100BMsg
     * @param glblCmpyCd String
     * @return Object[]
     */
    public static Object[] createArgumentNWAL1130ForMDL(NMAL7100BMsg scrnMsg, int index, String glblCmpyCd) {

        Object[] param = new Object[7];
        // 0:Suffix
        String suffixP0 = "";
        // 1:Screen Name
        String scrnNmP1 = "";
        // 2:Table Name
        StringBuilder tblNmP2 = new StringBuilder();
        // 3:Criteria
        List<Object[]> whereListP3 = new ArrayList<Object[]>();
        // 4:Column
        List<Object[]> columnListP4 = new ArrayList<Object[]>();
        // 5:Sort
        List<Object[]> sortConditionListP5 = new ArrayList<Object[]>();
        // 6:Result(BMsg.R)


        suffixP0 = "";
        scrnNmP1 = "Service Model Popup";

        tblNmP2.append(" SELECT");
        tblNmP2.append(" MN.T_GLBL_CMPY_CD GLBL_CMPY_CD");
        tblNmP2.append(",MN.EZCANCELFLAG");
        tblNmP2.append(",TO_CHAR(MN.T_MDL_ID) T_MDL_ID");
        tblNmP2.append(",MN.T_MDL_NM");
        tblNmP2.append(" FROM MDL_NM MN");
        tblNmP2.append(" WHERE");
        tblNmP2.append(" MN.T_GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
        tblNmP2.append(" AND MN.EZCANCELFLAG = '").append("0").append("'");

        Object[] whereArray0 = new Object[CMN_PRM_WHERE_NUM];
        whereArray0[0] = "Service Model Name";
        whereArray0[1] = "T_MDL_NM";
        whereArray0[2] = scrnMsg.A.no(index).prcQlfyValTxt_DA.getValue();
        whereArray0[3] = "Y";
        whereListP3.add(whereArray0);

        Object[] columnArray0 = new Object[CMN_PRM_COLUMN_NUM];
        columnArray0[0] = "Service Model ID";
        columnArray0[1] = "T_MDL_ID";
        columnArray0[2] = BigDecimal.valueOf(22);
        columnArray0[3] = "N";
        columnListP4.add(columnArray0);

        Object[] columnArray1 = new Object[CMN_PRM_COLUMN_NUM];
        columnArray1[0] = "Service Model Name";
        columnArray1[1] = "T_MDL_NM";
        columnArray1[2] = BigDecimal.valueOf(50);
        columnArray1[3] = "Y";
        columnListP4.add(columnArray1);

        Object[] sortConditionArray1 = new Object[2];
        sortConditionArray1[0] = "T_MDL_ID";
        sortConditionArray1[1] = "ASC";
        sortConditionListP5.add(sortConditionArray1);
        
        param[0] = suffixP0;
        param[1] = scrnNmP1;
        param[2] = tblNmP2.toString();
        param[3] = whereListP3;
        param[4] = columnListP4;
        param[5] = sortConditionListP5;
        param[6] = scrnMsg.R;

        return param;
    }

    // Add Start #4043 02/24/2016
    /**
     * createArgumentNWAL1130ForBL
     * @param scrnMsg NMAL7100BMsg
     * @param index int
     * @param prcListTpCd String
     * @param glblCmpyCd String
     * @return Object[]
     */
    public static Object[] createArgumentNWAL1130ForPrcCatg(NMAL7100BMsg scrnMsg, int index, String prcListTpCd, String glblCmpyCd) {

        Object[] param = new Object[7];
        // 0:Suffix
        String suffixP0 = "";
        // 1:Screen Name
        String scrnNmP1 = "";
        // 2:Table Name
        StringBuilder tblNmP2 = new StringBuilder();
        // 3:Criteria
        List<Object[]> whereListP3 = new ArrayList<Object[]>();
        // 4:Column
        List<Object[]> columnListP4 = new ArrayList<Object[]>();
        // 5:Sort
        List<Object[]> sortConditionListP5 = new ArrayList<Object[]>();
        // 6:Result(BMsg.R)


        suffixP0 = "";
        scrnNmP1 = "Price List Popup";

        tblNmP2.append(" SELECT");
        tblNmP2.append("     PC.GLBL_CMPY_CD  AS GLBL_CMPY_CD");
        tblNmP2.append("     ,PC.EZCANCELFLAG AS EZCANCELFLAG");
        tblNmP2.append("     ,PC.PRC_CATG_CD  AS PRC_CATG_CD");
        tblNmP2.append("     ,PC.PRC_CATG_NM  AS PRC_CATG_NM");
        tblNmP2.append(" FROM");
        tblNmP2.append("     PRC_CATG     PC");
        tblNmP2.append(" WHERE");
        tblNmP2.append("     PC.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
        tblNmP2.append(" AND PC.EZCANCELFLAG = '0'");
        tblNmP2.append(" AND PC.PRC_LIST_TP_CD = '").append(prcListTpCd).append("'");
        tblNmP2.append(" AND PC.ACTV_FLG = 'Y'");
        tblNmP2.append(" AND PC.EFF_FROM_DT <= '").append(ZYPDateUtil.getSalesDate(glblCmpyCd)).append("'");
        tblNmP2.append(" AND NVL(PC.EFF_THRU_DT, '").append(HIGH_VAL_DT).append("') >= '").append(ZYPDateUtil.getSalesDate(glblCmpyCd)).append("'");

        Object[] whereArray0 = new Object[CMN_PRM_WHERE_NUM];
        whereArray0[0] = "Price List Code";
        whereArray0[1] = "PRC_CATG_CD";
        whereArray0[2] = null;
        whereArray0[3] = "Y";
        whereListP3.add(whereArray0);

        Object[] whereArray1 = new Object[CMN_PRM_WHERE_NUM];
        whereArray1[0] = "Price List Name";
        whereArray1[1] = "PRC_CATG_NM";
        whereArray1[2] = scrnMsg.Y.no(index).prcCatgNm_CX.getValue();
        whereArray1[3] = "Y";
        whereListP3.add(whereArray1);

        Object[] columnArray0 = new Object[CMN_PRM_COLUMN_NUM];
        columnArray0[0] = "Price List Code";
        columnArray0[1] = "PRC_CATG_CD";
        columnArray0[2] = BigDecimal.valueOf(10);
        columnArray0[3] = "N";
        columnListP4.add(columnArray0);

        Object[] columnArray1 = new Object[CMN_PRM_COLUMN_NUM];
        columnArray1[0] = "Price List Name";
        columnArray1[1] = "PRC_CATG_NM";
        columnArray1[2] = BigDecimal.valueOf(50);
        columnArray1[3] = "Y";
        columnListP4.add(columnArray1);

        Object[] sortConditionArray1 = new Object[2];
        sortConditionArray1[0] = "PRC_CATG_CD";
        sortConditionArray1[1] = "ASC";
        sortConditionListP5.add(sortConditionArray1);
        
        param[0] = suffixP0;
        param[1] = scrnNmP1;
        param[2] = tblNmP2.toString();
        param[3] = whereListP3;
        param[4] = columnListP4;
        param[5] = sortConditionListP5;
        param[6] = scrnMsg.R;

        return param;
    }
    // Add End #4043 02/24/2016 

    /**
     * createArgumentNMWAL6800
     * @param acctNumItem EZDBStringItem
     * @param accNmItem EZDBStringItem
     * @return Object[]
     */
    public static Object[] createArgumentNMWAL6800(NMAL7100BMsg scrnMsg, EZDBStringItem mdseNmItem, EZDBStringItem mdseCdItem) {


        Object[] param = new Object[10];
        param[0] = mdseCdItem;
        param[1] = mdseNmItem;
        param[2] = scrnMsg.xxPopPrm_0;
        param[3] = scrnMsg.xxPopPrm_0;
        param[4] = scrnMsg.xxPopPrm_0;
        param[5] = scrnMsg.xxPopPrm_0;
        param[6] = scrnMsg.xxPopPrm_0;
        // Mod Start 2016/09/12 QC#11615
//        param[7] = scrnMsg.xxPopPrm_0;
        param[7] = mdseNmItem;
        // Mod End 2016/09/12 QC#11615
        param[8] = scrnMsg.xxPopPrm_0;
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_1, NMAL6800Constant.XX_MODE_CD_ALL);
        param[9] = scrnMsg.xxPopPrm_1;

        return param;
    }

    // 2019/12/06 QC#54215 Add Start
    /**
     * setArgumentNMAL7140
     * @param scrnMsg NMAL7100BMsg
     * @return Object[]
     */
    public static Object[] setArgumentNMAL7140(NMAL7100BMsg scrnMsg) {
        Object[] param = new Object[8];
        param[0] = scrnMsg.prcMktPrmoCd_E1;
        param[1] = scrnMsg.effFromDt_E1;
        param[2] = scrnMsg.effFromDt_E2;
        param[3] = scrnMsg.mdseDescShortTxt_E1;
        param[4] = scrnMsg.prcQlfyValTxt_E1;
        param[5] = scrnMsg.prcPrmoQlfyTpCd_E1;
        param[6] = scrnMsg.mdseCd_E1;
        param[7] = scrnMsg.prmoAmt_E1;

        return param;
    }
    // 2019/12/06 QC#54215 Add End
}
