package business.servlet.NPAL1080.common;

import static business.servlet.NPAL1080.constant.NPAL1080Constant.BTN_ADD_LINE;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.BTN_CMN_BTN_APPLY;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.BTN_CMN_BTN_APPROVE;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.BTN_CMN_BTN_CLEAR;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.BTN_CMN_BTN_DELETE;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.BTN_CMN_BTN_DOWNLOAD;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.BTN_CMN_BTN_REJECT;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.BTN_CMN_BTN_RESET;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.BTN_CMN_BTN_RETURN;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.BTN_CMN_BTN_SAVE;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.BTN_CMN_BTN_SUBMIT;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.BTN_DETAIL_GET_ITEM_NAME;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.BTN_DETAIL_GET_TRACKING_NUMBER;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.BTN_DETAIL_GET_WH_SUPPLER;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.BTN_DETAIL_ITEM;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.BTN_DETAIL_WH;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.CHK_TIME_PATTERN;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.COLON;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.DISP_HRCH_ACCT_CD_SHIP;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.DTL_CD_LB_NM_FOR_CARR;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.DTL_NM_LB_NM_FOR_CARR;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.FUNC_ID_SPEC_UPDATE;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.FUNC_ID_SUBMIT;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.HDR_CD_LB_NM_FOR_CARR;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.HDR_NM_LB_NM_FOR_CARR;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.IDX_0;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.IDX_1;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.IDX_2;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.IDX_20;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.IDX_3;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.IDX_4;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.IDX_5;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.IDX_6;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.IDX_62;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.IDX_7;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.LABEL_TXT_RQST_RCV_DT;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.LABEL_TXT_RQST_TECH_TOC_CD;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.LABEL_TXT_TECH_NM;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.LIKE_SEARCH_CHAR;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.LOC_ROLE_TP_CDLIST;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.MODE_BTN_ADDLINE;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.MODE_BTN_COPY;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.MODE_BTN_INIT;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.NLAM0173E;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.NLZM2274E;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.NMAM0836E;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.NPAM1230E;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.NPAM1517E;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.NPAM1531E;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.OPEN_WIN_SUPPLIER_SQL;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.RUN_MODE_CLOSED;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.RUN_MODE_NEW;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.RUN_MODE_SAVED;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.RUN_MODE_SUBMITTED;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.SCREEN_ID;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.SCR_NM_FOR_CARR;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.TAB_DETAIL;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.TAB_HEADER;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.TBL_CD_COL_NM_FOR_CARR;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.TBL_ID_L;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.TBL_ID_R;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.TBL_NM_COL_NM_FOR_CARR;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.TBL_NM_FOR_CARR;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.TBL_NM_FOR_TECH;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.TBL_SORT_COL_NM_FOR_CARR;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.TECH_SWH_CD_R;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.TIME_CHECK_VAL;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBItem;
import parts.common.EZDBStringItem;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NPAL1080.NPAL1080BMsg;
import business.servlet.NPAL1080.NPAL1080_ABMsg;
import business.servlet.NPAL1080.NPAL1080_ZBMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_APVL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROCR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTL_WH_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_SVC_LVL;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 * <pre>
 * Business ID : NPAL1080 Tech Parts Request Entry
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/16/2015   CITS            Yasushi Nomura  Create          N/A
 * 05/06/2016   CSAI            D.Fukaya        Update          QC#7628
 * 12/19/2016   CITS            K.Ogino         Update          QC#15820
 * 10/25/2017   CITS            S.Katsuma       Update          QC#21896
 * 03/13/2018   CITS            S.Katsuma       Update          SOL#118(QC#12110)
 * 05/25/2018   CITS            Y.Iwasaki       Update          SOL#135(QC#2366)
 * 07/09/2018   CITS            K.Ogino         Update          QC#24918
 * 08/17/2018   CITS            T.Tokutomi      Update          QC#26581
 * 08/20/2018   CITS            T.Tokutomi      Update          QC#26662
 * 12/05/2018   CITS            M.Naito         Update          QC#25900
 * 02/14/2019   Fujitsu         S.Takami        Update          QC#30358
 * 02/18/2018   CITS            K.Ogino         Update          QC#30355
 * 02/13/2020   CITS            Y.Iwasaki       Update          QC#55702,55709
 * 02/14/2020   CITS            K.Ogino         Update          QC#55710
 * 2020/11/12   CITS            K.Ogino         Update          QC#57659
 * 2023/03/22   Hitachi         T.Kuroda        Update          QC#61204
 *</pre>
 */
public class NPAL1080CommonLogic {

    /**
     * <pre>
     * Initial common button
     * </pre>
     * @param handler EZCommandHandler
     */
    public static void initCommonButton(EZDCommonHandler handler) {
        handler.setButtonProperties(BTN_CMN_BTN_SAVE[0], BTN_CMN_BTN_SAVE[1], BTN_CMN_BTN_SAVE[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_SUBMIT[0], BTN_CMN_BTN_SUBMIT[1], BTN_CMN_BTN_SUBMIT[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_APPLY[0], BTN_CMN_BTN_APPLY[1], BTN_CMN_BTN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_APPROVE[0], BTN_CMN_BTN_APPROVE[1], BTN_CMN_BTN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_REJECT[0], BTN_CMN_BTN_REJECT[1], BTN_CMN_BTN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_DOWNLOAD[0], BTN_CMN_BTN_DOWNLOAD[1], BTN_CMN_BTN_DOWNLOAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_DELETE[0], BTN_CMN_BTN_DELETE[1], BTN_CMN_BTN_DELETE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_CLEAR[0], BTN_CMN_BTN_CLEAR[1], BTN_CMN_BTN_CLEAR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BTN_RESET[0], BTN_CMN_BTN_RESET[1], BTN_CMN_BTN_RESET[2], 1, null);
        handler.setButtonProperties(BTN_CMN_BTN_RETURN[0], BTN_CMN_BTN_RETURN[1], BTN_CMN_BTN_RETURN[2], 1, null);
        handler.setButtonProperties(BTN_ADD_LINE[0], BTN_ADD_LINE[1], BTN_ADD_LINE[2], 0, null);
    }

    /**
     * Init
     * @param scrnMsg NPAL1080BMsg
     */
    public static void commonInit(NPAL1080BMsg scrnMsg) {
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxSupdFlg_CA, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxSupdFlg_CL, ZYPConstant.FLG_OFF_N);
        if (ZYPCommonFunc.hasValue(scrnMsg.prchReqNum_H1)) {
            if (0 < scrnMsg.A.getValidCount()) {
                if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.prchReqSavedFlg_H1.getValue())) {
                    scrnMsg.xxEdtModeFlg.setValue(RUN_MODE_SAVED);
                } else {
                    scrnMsg.xxEdtModeFlg.setValue(RUN_MODE_SUBMITTED);
                }
                if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.openStsFlg_H1.getValue())) {
                    scrnMsg.xxEdtModeFlg.setValue(RUN_MODE_CLOSED);
                }
            } else {
                scrnMsg.xxEdtModeFlg.setValue(RUN_MODE_NEW);
            }
        } else {
            scrnMsg.xxEdtModeFlg.setValue(RUN_MODE_NEW);
        }
    }

    /**
     * @param scrnMsg NPAL1080BMsg
     */
    public static void setAppFracDigit(NPAL1080BMsg scrnMsg) {
        scrnMsg.thisMthTotStdCostAmt_D2.setAppFracDigit(2);
    }

    /**
     * Do addCheckItem for items
     * @param scrnMsg NPAL1080BMsg
     */
    public static void addCheckItem(NPAL1080BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.prchReqNum_H1);
        scrnMsg.addCheckItem(scrnMsg.prchReqTpCd_SE);
        scrnMsg.addCheckItem(scrnMsg.rqstRcvDt_H1);
        // QC#57659
        scrnMsg.addCheckItem(scrnMsg.xxDtTm_H1);
        scrnMsg.addCheckItem(scrnMsg.rqstRcvDtTxt_SL);
        scrnMsg.addCheckItem(scrnMsg.dsCondConstCd_SE);
        scrnMsg.addCheckItem(scrnMsg.xxDtNm_H2);
        scrnMsg.addCheckItem(scrnMsg.fsrNum_H1);
        scrnMsg.addCheckItem(scrnMsg.svcTaskNum_H1);
        scrnMsg.addCheckItem(scrnMsg.svcMachSerNum_H1);
        scrnMsg.addCheckItem(scrnMsg.ctacPsnNm_H1);
        scrnMsg.addCheckItem(scrnMsg.rqstTechTocCd_H1);
        scrnMsg.addCheckItem(scrnMsg.techNm_H1);
        scrnMsg.addCheckItem(scrnMsg.destRtlWhCd_H1);
        scrnMsg.addCheckItem(scrnMsg.destRtlSwhCd_H1);
        scrnMsg.addCheckItem(scrnMsg.shipToCustCd_H1);
        scrnMsg.addCheckItem(scrnMsg.carrNm_H1);
        scrnMsg.addCheckItem(scrnMsg.shpgSvcLvlCd_SE);
        scrnMsg.addCheckItem(scrnMsg.delyAddlCmntTxt_H2);

        if (TAB_HEADER.equals(scrnMsg.xxDplyTab.getValue())) {
            scrnMsg.addCheckItem(scrnMsg.prchReqLineCmntTxt_D2);
        } else {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_D1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).prchReqLineTpCd_SE);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseCd_D1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).prchReqQty_D1);
                // START 2017/10/25 S.Katsuma QC#21896 ADD
                scrnMsg.addCheckItem(scrnMsg.A.no(i).procrTpCd_SE);
                // END 2017/10/25 S.Katsuma QC#21896 ADD
                scrnMsg.addCheckItem(scrnMsg.A.no(i).prntVndCd_D1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).prntVndNm_D1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).locNm_D1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).prchReqLineCmntTxt_D1);
            }
        }
    }

    /**
     * <pre>
     * Details are initialized (sorting sign deletion and BG color control).
     * </pre>
     * @param scrnMsg Screen Msg
     * @param baseContents String[][]
     */
    public static void clearGUIAttribute(NPAL1080BMsg scrnMsg, String[][] baseContents) {
        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, baseContents);
        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
    }

    /**
     * Protect items
     * @param items EZDBItem
     */
    public static void disableItems(EZDBItem... items) {
        for (EZDBItem item : items) {
            item.setInputProtected(true);
        }
    }

    /**
     * Protect items
     * @param items EZDBItem
     */
    public static void enableItems(EZDBItem... items) {
        for (EZDBItem item : items) {
            item.setInputProtected(false);
        }
    }

    /**
     * Protect buttons
     * @param handler EZDCommonHandler
     * @param btnHtmlNm String
     */
    public static void disableButtons(EZDCommonHandler handler, String... btnHtmlNm) {
        for (String btnNm : btnHtmlNm) {
            handler.setButtonEnabled(btnNm, false);
        }
    }

    /**
     * UnProtect buttons
     * @param handler EZDCommonHandler
     * @param btnHtmlNm String
     */
    public static void enableButtons(EZDCommonHandler handler, String... btnHtmlNm) {
        for (String btnNm : btnHtmlNm) {
            handler.setButtonEnabled(btnNm, true);
        }
    }

    /**
     * @param scrnMsg NPAL1080BMsg
     * @return false for error
     */
    public static boolean checkNoLineCheckedError(NPAL1080BMsg scrnMsg) {
        boolean isAnyChecked = false;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_D1.getValue())) {
                isAnyChecked = true;
                break;
            }
        }

        if (!isAnyChecked) {
            // scrnMsg.setMessageInfo(NPAM1233E);
            return false;
        }
        return true;
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NPAL1080BMsg
     * @return Object[]
     */
    public static Object[] setParamForCarrierPopup(NPAL1080BMsg scrnMsg) {

        scrnMsg.xxTblNm_P1.clear();
        scrnMsg.xxTblCdColNm_P1.clear();
        scrnMsg.xxTblNmColNm_P1.clear();
        scrnMsg.xxTblSortColNm_P1.clear();
        scrnMsg.xxScrNm_P1.clear();
        scrnMsg.xxHdrCdLbNm_P1.clear();
        scrnMsg.xxHdrNmLbNm_P1.clear();
        scrnMsg.xxDtlCdLbNm_P1.clear();
        scrnMsg.xxDtlNmLbNm_P1.clear();
        scrnMsg.xxCondCd_P1.clear();
        scrnMsg.xxCondNm_P1.clear();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P1, TBL_NM_FOR_CARR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P1, TBL_CD_COL_NM_FOR_CARR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P1, TBL_NM_COL_NM_FOR_CARR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P1, TBL_SORT_COL_NM_FOR_CARR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P1, SCR_NM_FOR_CARR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P1, HDR_CD_LB_NM_FOR_CARR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P1, HDR_NM_LB_NM_FOR_CARR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P1, DTL_CD_LB_NM_FOR_CARR);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P1, DTL_NM_LB_NM_FOR_CARR);
        // ZYPEZDItemValueSetter.setValue(scrnMsg.xxCondCd_P1,
        // scrnMsg.carrCd_AC);
        if (ZYPCommonFunc.hasValue(scrnMsg.carrNm_H1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxCondNm_P1, LIKE_SEARCH_CHAR + scrnMsg.carrNm_H1.getValue() + LIKE_SEARCH_CHAR);
        }

        Object[] params = new Object[11];
        params[0] = scrnMsg.xxTblNm_P1;
        params[1] = scrnMsg.xxTblCdColNm_P1;
        params[2] = scrnMsg.xxTblNmColNm_P1;
        params[3] = scrnMsg.xxTblSortColNm_P1;
        params[4] = scrnMsg.xxScrNm_P1;
        params[5] = scrnMsg.xxHdrCdLbNm_P1;
        params[6] = scrnMsg.xxHdrNmLbNm_P1;
        params[7] = scrnMsg.xxDtlCdLbNm_P1;
        params[8] = scrnMsg.xxDtlNmLbNm_P1;
        params[9] = scrnMsg.xxCondCd_P1;
        params[10] = scrnMsg.xxCondNm_P1;

        return params;
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NPAL1080BMsg
     * @return Object[]
     */
    public static Object[] setParamForTechnicianPopup(NPAL1080BMsg scrnMsg, String glblCmpyCd) {

        scrnMsg.xxTblNm_P1.setValue(TBL_NM_FOR_TECH);

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "";
        params[IDX_1] = "Technician Search Popup";

        String salseDate = ZYPDateUtil.getSalesDate(glblCmpyCd);
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("   TM.TECH_TOC_CD ");
        sb.append("  ,TM.TECH_NM ");
        sb.append("  ,TM.EZCANCELFLAG ");
        sb.append("  ,TM.GLBL_CMPY_CD ");
        sb.append("FROM ");
        sb.append("   TECH_MSTR TM ");
        sb.append("  ,S21_PSN  PSN ");
        sb.append("WHERE");
        sb.append("    TM.GLBL_CMPY_CD   = " + "'" + glblCmpyCd + "'");
        sb.append(" AND TM.GLBL_CMPY_CD   =  PSN.GLBL_CMPY_CD");
        sb.append(" AND TM.TECH_TOC_CD    =  PSN.PSN_CD");
        sb.append(" AND PSN.EFF_FROM_DT <= " + "'" + salseDate + "'");
        sb.append(" AND NVL(PSN.EFF_THRU_DT, '99991231') >= " + "'" + salseDate + "'");
        sb.append(" AND TM.EZCANCELFLAG   = '0'");
        sb.append(" AND PSN.EZCANCELFLAG  = '0'");

        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Technician Code";
        whereArray1[IDX_1] = "TECH_TOC_CD";
        if (ZYPCommonFunc.hasValue(scrnMsg.rqstTechTocCd_H1)) {
            whereArray1[IDX_2] = scrnMsg.rqstTechTocCd_H1.getValue();
        }
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = "Technician Name";
        whereArray2[IDX_1] = "TECH_NM";
        if (ZYPCommonFunc.hasValue(scrnMsg.techNm_H1)) {
            whereArray2[IDX_2] = scrnMsg.techNm_H1.getValue();
        }
        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);
        whereList.add(whereArray2);
        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "Technician Code";
        columnArray1[IDX_1] = "TECH_TOC_CD";
        columnArray1[IDX_2] = new BigDecimal(IDX_20);
        columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "Technician Name";
        columnArray2[IDX_1] = "TECH_NM";
        columnArray2[IDX_2] = new BigDecimal(IDX_62);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);
        columnList.add(columnArray2);
        params[IDX_4] = columnList;

        List<Object[]> sortList = new ArrayList<Object[]>();
        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray1[IDX_0] = "TECH_TOC_CD";
        sortConditionArray1[IDX_1] = "ASC";
        sortList.add(sortConditionArray1);
        params[IDX_5] = sortList;
        ZYPTableUtil.clear(scrnMsg.R);
        params[IDX_6] = scrnMsg.R;
        return params;
    }

    /**
     * Set Supplier Popup param
     * @param scrnMsg NPAL1080BMsg
     * @param detailIndex int
     * @return SupplierPopup Param (NWAL1130) Object[]
     */
    public static Object[] setParamForSupplierPopup(NPAL1080BMsg scrnMsg, int detailIndex) {
        ZYPTableUtil.clear(scrnMsg.R);
        Object[] params = new Object[7];
        scrnMsg.xxTblNm_P1.clear();

        params[0] = "";
        params[1] = "Supplier/Supplier Site Search";
        params[2] = OPEN_WIN_SUPPLIER_SQL;

        // Where list
        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Supplier Code";
        whereArray1[1] = "PRNT_VND_CD";
        whereArray1[2] = "";
        whereArray1[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[4];
        whereArray2[0] = "Supplier Name";
        whereArray2[1] = "PRNT_VND_NM";
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(detailIndex).prntVndNm_D1)) {
            whereArray2[2] = scrnMsg.A.no(detailIndex).prntVndNm_D1.getValue();
        } else {
            whereArray2[2] = "";
        }
        whereArray2[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray2);

        Object[] whereArray3 = new Object[4];
        whereArray3[0] = "Supplier Site Code";
        whereArray3[1] = "VND_CD";
        whereArray3[2] = "";
        whereArray3[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray3);

        Object[] whereArray4 = new Object[4];
        whereArray4[0] = "Supplier Site Name";
        whereArray4[1] = "LOC_NM";
        if (ZYPCommonFunc.hasValue(scrnMsg.A.no(detailIndex).locNm_D1)) {
            whereArray4[2] = scrnMsg.A.no(detailIndex).locNm_D1.getValue();
        } else {
            whereArray4[2] = "";
        }
        whereArray4[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray4);

        params[3] = whereList;

        // Column list
        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Supplier Code";
        columnArray1[1] = "PRNT_VND_CD";
        columnArray1[2] = BigDecimal.valueOf(20);
        columnArray1[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[4];
        columnArray2[0] = "Supplier Name";
        columnArray2[1] = "PRNT_VND_NM";
        columnArray2[2] = BigDecimal.valueOf(40);
        columnArray2[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[4];
        columnArray3[0] = "Supplier Site Code";
        columnArray3[1] = "VND_CD";
        columnArray3[2] = BigDecimal.valueOf(20);
        columnArray3[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray3);

        Object[] columnArray4 = new Object[4];
        columnArray4[0] = "Supplier Site Name";
        columnArray4[1] = "LOC_NM";
        columnArray4[2] = BigDecimal.valueOf(60);
        columnArray4[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray4);

        params[4] = columnList;

        // Sort list
        List<Object[]> sortConditionList = new ArrayList<Object[]>();

        Object[] sortConditionArray1 = new Object[2];
        sortConditionArray1[0] = "PRNT_VND_CD";
        sortConditionArray1[1] = "ASC";
        sortConditionList.add(sortConditionArray1);

        Object[] sortConditionArray2 = new Object[2];
        sortConditionArray2[0] = "VND_CD";
        sortConditionArray2[1] = "ASC";
        sortConditionList.add(sortConditionArray2);

        params[5] = sortConditionList;
        params[6] = scrnMsg.R;

        return params;
    }

    /**
     * Set Location Popup param
     * @param scrnMsg NPAL1080BMsg
     * @param detailIndex int
     * @return LocationPopup Param (NPAL1010) Object[]
     */
    public static Object[] setParamForLocationPopup(NPAL1080BMsg scrnMsg, int detailIndex) {

        scrnMsg.xxPopPrm_P0.clear();
        scrnMsg.xxPopPrm_P1.clear();
        scrnMsg.xxPopPrm_P2.clear();
        scrnMsg.xxPopPrm_P3.clear();
        scrnMsg.xxPopPrm_P4.clear();
        scrnMsg.xxPopPrm_P5.clear();
        scrnMsg.xxPopPrm_P6.clear();
        scrnMsg.xxPopPrm_P7.clear();
        scrnMsg.xxPopPrm_P8.clear();
        scrnMsg.xxPopPrm_P9.clear();
        scrnMsg.xxPopPrm_PA.clear();
        scrnMsg.xxPopPrm_PB.clear();
        scrnMsg.xxPopPrm_PC.clear();
        scrnMsg.xxPopPrm_PD.clear();
        scrnMsg.xxPopPrm_PE.clear();
        scrnMsg.xxPopPrm_PF.clear();
        scrnMsg.xxPopPrm_PG.clear();
        scrnMsg.xxPopPrm_PH.clear();
        scrnMsg.xxPopPrm_PI.clear();
        scrnMsg.xxPopPrm_PJ.clear();
        scrnMsg.xxPopPrm_PK.clear();
        scrnMsg.xxPopPrm_PL.clear();
        scrnMsg.xxPopPrm_PM.clear();
        scrnMsg.xxTblNm_P1.clear();

        if (detailIndex < 0) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P2, LOC_ROLE_TP_CDLIST);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P3, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P6, scrnMsg.destRtlWhCd_H1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P8, scrnMsg.destRtlSwhCd_H1);
            if (!ZYPCommonFunc.hasValue(scrnMsg.destRtlSwhCd_H1)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PA, ZYPConstant.FLG_ON_Y);
            }
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PE, RTL_WH_CATG.TECH_CAR_STOCK);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PK, scrnMsg.rqstTechTocCd_H1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PL, scrnMsg.techNm_H1);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P3, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P4, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P7, scrnMsg.A.no(detailIndex).prntVndNm_D1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P8, scrnMsg.A.no(detailIndex).locNm_D1);
        }

        // QC#10213
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_PM, ZYPConstant.FLG_ON_Y);

        Object[] params = new Object[23];
        params[0] = scrnMsg.xxPopPrm_P0;
        params[1] = scrnMsg.xxPopPrm_P1;
        params[2] = scrnMsg.xxPopPrm_P2;
        params[3] = scrnMsg.xxPopPrm_P3;
        params[4] = scrnMsg.xxPopPrm_P4;
        params[5] = scrnMsg.xxPopPrm_P5;
        params[6] = scrnMsg.xxPopPrm_P6;
        params[7] = scrnMsg.xxPopPrm_P7;
        params[8] = scrnMsg.xxPopPrm_P8;
        params[9] = scrnMsg.xxPopPrm_P9;
        params[10] = scrnMsg.xxPopPrm_PA;
        params[11] = scrnMsg.xxPopPrm_PB;
        params[12] = scrnMsg.xxPopPrm_PC;
        params[13] = scrnMsg.xxPopPrm_PD;
        params[14] = scrnMsg.xxPopPrm_PE;
        params[15] = scrnMsg.xxPopPrm_PF;
        params[16] = scrnMsg.xxPopPrm_PG;
        params[17] = scrnMsg.xxPopPrm_PH;
        params[18] = scrnMsg.xxPopPrm_PI;
        params[19] = scrnMsg.xxPopPrm_PJ;
        params[20] = scrnMsg.xxPopPrm_PK;
        params[21] = scrnMsg.xxPopPrm_PL;
        params[22] = scrnMsg.xxPopPrm_PM;

        return params;
    }

    /**
     * Set Location Popup param
     * @param scrnMsg NPAL1080BMsg
     * @return ShipToCustPopup Param (NMAL6010) Object[]
     */
    public static Object[] setParamForShipToCustPopup(NPAL1080BMsg scrnMsg) {

        scrnMsg.O.no(0).xxPopPrm_O1.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.O.no(1).xxPopPrm_O1, scrnMsg.dsAcctNm_H1);
        scrnMsg.O.no(2).xxPopPrm_O1.clear();
        scrnMsg.O.no(3).xxPopPrm_O1.clear();
        scrnMsg.O.no(4).xxPopPrm_O1.clear();
        scrnMsg.O.no(5).xxPopPrm_O1.clear();
        scrnMsg.O.no(6).xxPopPrm_O1.clear();
        scrnMsg.O.no(7).xxPopPrm_O1.clear();
        scrnMsg.O.no(8).xxPopPrm_O1.clear();
        scrnMsg.O.no(9).xxPopPrm_O1.clear();
        scrnMsg.O.no(10).xxPopPrm_O1.clear();
        scrnMsg.O.no(11).xxPopPrm_O1.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.O.no(12).xxPopPrm_O1, DISP_HRCH_ACCT_CD_SHIP);
        scrnMsg.O.no(13).xxPopPrm_O1.clear();
        scrnMsg.O.no(14).xxPopPrm_O1.clear();
        scrnMsg.O.no(15).xxPopPrm_O1.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.O.no(16).xxPopPrm_O1, scrnMsg.shipToCustCd_H1);
        scrnMsg.O.no(17).xxPopPrm_O1.clear();
        scrnMsg.O.no(18).xxPopPrm_O1.clear();
        scrnMsg.O.no(19).xxPopPrm_O1.clear();
        scrnMsg.O.no(20).xxPopPrm_O1.clear();
        scrnMsg.O.no(21).xxPopPrm_O1.clear();
        scrnMsg.O.no(22).xxPopPrm_O1.clear();
        scrnMsg.O.no(23).xxPopPrm_O1.clear();

        scrnMsg.O.setValidCount(24);

        Object[] params = new Object[24];

        for (int i = 0; i < scrnMsg.O.getValidCount() && i < params.length; i++) {
            params[i] = scrnMsg.O.no(i).xxPopPrm_O1;
        }

        return params;
    }

    private static boolean isParentDetail(NPAL1080_ABMsg abMsg) {
        return BigDecimal.ZERO.equals(abMsg.prchReqLineSubNum_D1.getValue());
    }

    private static void enable(EZDBItem item) {
        item.setInputProtected(false);
    }

    private static void disable(EZDBItem item) {
        item.setInputProtected(true);
    }

    private static void enable(S21CommonHandler handler, String itemName) {
        handler.setButtonEnabled(itemName, true);
    }

    private static void disable(S21CommonHandler handler, String itemName) {
        handler.setButtonEnabled(itemName, false);
    }

    /**
     * @param prchReqStsCd EZDBStringItem
     * @return true if PRCH_REQ_STS_CD means cancelled or closed.
     */
    public static boolean isCancelledOrClosed(EZDBStringItem prchReqStsCd) {
        return ZYPCommonFunc.hasValue(prchReqStsCd) //
                && (PRCH_REQ_STS.CANCELLED.equals(prchReqStsCd.getValue()) || PRCH_REQ_STS.CLOSED.equals(prchReqStsCd.getValue()));
    }

    /**
     * @param scrnMsg NPAL1080BMsg
     * @return boolean
     */
    public static boolean inputCheck(NPAL1080BMsg scrnMsg) {
        boolean ret = true;

        if (!ZYPCommonFunc.hasValue(scrnMsg.rqstTechTocCd_H1) && !ZYPCommonFunc.hasValue(scrnMsg.techNm_H1)) {
            scrnMsg.rqstTechTocCd_H1.setErrorInfo(1, NPAM1517E, new String[] {LABEL_TXT_RQST_TECH_TOC_CD, LABEL_TXT_TECH_NM });
            scrnMsg.techNm_H1.setErrorInfo(1, NPAM1517E, new String[] {LABEL_TXT_RQST_TECH_TOC_CD, LABEL_TXT_TECH_NM });
            ret = false;
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.rqstRcvDt_H1) && ZYPDateUtil.isPastDate(scrnMsg.rqstRcvDt_H1.getValue())) {
            scrnMsg.rqstRcvDt_H1.setErrorInfo(1, NPAM1230E, new String[] {LABEL_TXT_RQST_RCV_DT });
            ret = false;
        }
        // QC#57659
        if (ZYPCommonFunc.hasValue(scrnMsg.prchReqTpCd_SE) && ZYPCommonFunc.hasValue(scrnMsg.shpgSvcLvlCd_SE) //
                && PRCH_REQ_TP.PREMIUM_RUSH.equals(scrnMsg.prchReqTpCd_SE.getValue()) //
                && SHPG_SVC_LVL.SCHD_DELIVERY.equals(scrnMsg.shpgSvcLvlCd_SE.getValue()) //
                && !ZYPCommonFunc.hasValue(scrnMsg.xxDtTm_H1)) {

            scrnMsg.xxDtTm_H1.setErrorInfo(1,"ZZZM9000E", new String[] {LABEL_TXT_RQST_RCV_DT });
            ret = false;
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.xxDtTm_H1)) {
            if (!(ZYPCommonFunc.isNumeric(scrnMsg.xxDtTm_H1.getValue().replace(":", "")))) {
                scrnMsg.xxDtTm_H1.setErrorInfo(1, NPAM1531E);
                scrnMsg.addCheckItem(scrnMsg.xxDtTm_H1);
            } else {
                if (scrnMsg.xxDtTm_H1.getValue().length() == IDX_4) {
                    String xxDtTm = scrnMsg.xxDtTm_H1.getValue();
                    xxDtTm = xxDtTm.substring(0, 2) + ":" + xxDtTm.substring(2, 4);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtTm_H1, xxDtTm);
                }
                if (isTime(scrnMsg.xxDtTm_H1)) {
                    String sTime = getTm(scrnMsg.xxDtTm_H1.getValue());
                    int time = Integer.parseInt(sTime);
                    // TIME_CHECK_VAL:1200 More than 1200 error
                    if (TIME_CHECK_VAL < time) {
                        scrnMsg.xxDtTm_H1.setErrorInfo(1, NPAM1531E);
                        scrnMsg.addCheckItem(scrnMsg.xxDtTm_H1);
                    }
                } else {
                    scrnMsg.addCheckItem(scrnMsg.xxDtTm_H1);
                }
            }
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.xxDtNm_H2)) {
            if (!(ZYPCommonFunc.isNumeric(scrnMsg.xxDtNm_H2.getValue().replace(":", "")))) {
                scrnMsg.xxDtNm_H2.setErrorInfo(1, NPAM1531E);
                scrnMsg.addCheckItem(scrnMsg.xxDtNm_H2);
            } else {
                if (isTime(scrnMsg.xxDtNm_H2)) {
                    String sTime = getTm(scrnMsg.xxDtNm_H2.getValue());
                    int time = Integer.parseInt(sTime);
                    // TIME_CHECK_VAL:1200 More than 1200 error
                    if (TIME_CHECK_VAL < time) {
                        scrnMsg.xxDtNm_H2.setErrorInfo(1, NPAM1531E);
                        scrnMsg.addCheckItem(scrnMsg.xxDtNm_H2);
                    }
                    if (!(ZYPCommonFunc.hasValue(scrnMsg.dsCondConstCd_SE.getValue()))) {
                        scrnMsg.dsCondConstCd_SE.setErrorInfo(1, NLZM2274E, new String[] {LABEL_TXT_RQST_RCV_DT });
                        scrnMsg.addCheckItem(scrnMsg.dsCondConstCd_SE);
                    }
                } else {
                    scrnMsg.addCheckItem(scrnMsg.xxDtNm_H2);
                }
            }
        }
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // 2019/02/14 QC#30358 Add Start
            if (isSubmittedLine(scrnMsg, scrnMsg.A.no(i))) {
                scrnMsg.A.no(i).submtFlg_D1.setValue(ZYPConstant.FLG_ON_Y);
            }
            // 2019/02/14 QC#30358 Add End
            if (PRCH_REQ_REC_TP.TECH_RETURN.equals(getPrchReqRecTpCdFromReqTpCd(scrnMsg, scrnMsg.prchReqTpCd_SE.getValue()))) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prchReqLineTpCd_SE)) {
                    scrnMsg.A.no(i).prchReqLineTpCd_SE.setErrorInfo(1, NMAM0836E, new String[] {"Line Type" });
                    ret = false;
                }
                if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prntVndNm_D1)) {
                    scrnMsg.A.no(i).prntVndNm_D1.setErrorInfo(1, NMAM0836E, new String[] {"WH/Supplier Name" });
                    ret = false;
                }
                if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).locNm_D1)) {
                    scrnMsg.A.no(i).locNm_D1.setErrorInfo(1, NMAM0836E, new String[] {"SWH/Site Name" });
                    ret = false;
                }
            } else {
                if (PROCR_TP.WAREHOUSE.equals(scrnMsg.A.no(i).procrTpCd_SE.getValue())) {
                    if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prntVndNm_D1)) {
                        scrnMsg.A.no(i).prntVndNm_D1.setErrorInfo(1, NMAM0836E, new String[] {"WH Name" });
                        ret = false;
                    }
                    if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).locNm_D1) //
                            && !scrnMsg.A.no(i).locNm_D1.isInputProtected()) { // 2019/02/14 QC#30358 Add Condition !isInputProtected
                        scrnMsg.A.no(i).locNm_D1.setErrorInfo(1, NMAM0836E, new String[] {"SWH Name" });
                        ret = false;
                    }
                }
                if (PROCR_TP.SUPPLIER.equals(scrnMsg.A.no(i).procrTpCd_SE.getValue())) {
                    if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prntVndNm_D1) && (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).locNm_D1))) {
                        scrnMsg.A.no(i).locNm_D1.setErrorInfo(1, NLAM0173E, new String[] {"Site Name" });
                        ret = false;
                    }
                    if ((!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prntVndNm_D1)) && ZYPCommonFunc.hasValue(scrnMsg.A.no(i).locNm_D1)) {
                        scrnMsg.A.no(i).prntVndNm_D1.setErrorInfo(1, NLAM0173E, new String[] {"Supplier Name" });
                        ret = false;
                    }
                    if ((!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prntVndNm_D1)) && (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).locNm_D1))) {
                        scrnMsg.A.no(i).prntVndCd_D1.clear();
                    }
                }
            }
        }
        if (!ret) {
            scrnMsg.xxDplyTab.setValue(TAB_DETAIL);
        }
        return ret;
    }

    private static boolean isTime(EZDBStringItem... timeItems) {
        for (EZDBStringItem timeItem : timeItems) {
            if (ZYPCommonFunc.hasValue(timeItem)) {
                // IDX_5:5 hh24mi input length check
                if (timeItem.getValue().length() < IDX_5) {
                    timeItem.setErrorInfo(1, NPAM1531E);
                    return false;
                }

                if (!timeItem.getValue().matches(CHK_TIME_PATTERN)) {
                    timeItem.setErrorInfo(1, NPAM1531E);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Get Time
     * @param tm time
     * @return time
     */
    public static String getTm(String tm) {
        if (ZYPCommonFunc.hasValue(tm)) {
            return tm.replace(COLON, "");
        }
        return tm;
    }

    /**
     * controlLineType
     * @param scrnMsg NPAL1080BMsg
     */
    public static void controlLineType(S21CommonHandler handler, NPAL1080BMsg scrnMsg) {
        boolean flg = true;
        if (PRCH_REQ_REC_TP.TECH_RETURN.equals(getPrchReqRecTpCdFromReqTpCd(scrnMsg, scrnMsg.prchReqTpCd_SE.getValue()))) {
            flg = false;
        }
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).prchReqLineTpCd_SE.setInputProtected(flg);
            // START 2017/10/25 S.Katsuma QC#21896 ADD
            if (flg) {
                scrnMsg.A.no(i).procrTpCd_SE.setInputProtected(!flg);
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).prchReqLineTpCd_SE, "");
                // START 2017/10/25 S.Katsuma QC#21896 ADD
                handler.setButtonEnabled(BTN_DETAIL_WH, i, flg);
                enable(scrnMsg.A.no(i).locNm_D1);
                // END 2017/10/25 S.Katsuma QC#21896 ADD
            } else {
                disable(scrnMsg.A.no(i).procrTpCd_SE);
                if (scrnMsg.destRtlSwhCd_H1.getValue().equals(TECH_SWH_CD_R)) {
                    disable(scrnMsg.A.no(i).locNm_D1);
                    handler.setButtonEnabled(BTN_DETAIL_WH, i, flg);
                } else {
                    enable(scrnMsg.A.no(i).locNm_D1);
                    handler.setButtonEnabled(BTN_DETAIL_WH, i, !flg);
                }
//                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(i).procrTpCd_SE, PROCR_TP.WAREHOUSE);
            }
            // END 2017/10/25 S.Katsuma QC#21896 ADD
        }
    }

    /**
     * @param scrnMsg NPAL1080BMsg
     * @param prchReqTpCd String
     * @return String
     */
    public static String getPrchReqRecTpCdFromReqTpCd(NPAL1080BMsg scrnMsg, String prchReqTpCd) {
        for (int i = 0; i < scrnMsg.X.getValidCount(); i++) {
            if (prchReqTpCd.equals(scrnMsg.X.no(i).prchReqTpCd_X1.getValue())) {
                return scrnMsg.X.no(i).prchReqRecTpCd_X1.getValue();
            }
        }
        return null;
    }

    /**
     * @param handler S21CommonHandler
     * @param scrnMsg NPAL1080BMsg
     * @param mode int
     * @param funcList List<String>
     */
    public static void control(S21CommonHandler handler, NPAL1080BMsg scrnMsg, int mode, List<String> funcList) {

        // QC#15820
        // clear html attribute
        scrnMsg.clearAllGUIAttribute(SCREEN_ID);

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        String tabName = scrnMsg.xxDplyTab.getValue();

        if (TAB_DETAIL.equals(tabName)) {

            tblColor.setAlternateRowsBG(TBL_ID_L, scrnMsg.A);
            tblColor.setAlternateRowsBG(TBL_ID_R, scrnMsg.A);

        } else if (TAB_HEADER.equals(tabName)) {

            tblColor.clearRowsBG(TBL_ID_L, scrnMsg.A);
            tblColor.clearRowsBG(TBL_ID_R, scrnMsg.A);

        }

        // QC#26662 Update 2018/08/20. Add Function of special Update mode.
        boolean canSubmit = false;
        boolean isSpecUpdate = false;
        for (String function : funcList) {
            if (FUNC_ID_SUBMIT.equals(function)) {
                canSubmit = true;
            } else if (FUNC_ID_SPEC_UPDATE.equals(function)) {
                canSubmit = true;
                isSpecUpdate = true;
            }
        }
        // Common Header
        disable(scrnMsg.prchReqStsDescTxt_H1);
        disable(scrnMsg.prchReqApvlStsDescTxt_H1);
        disable(scrnMsg.prchReqCratDt_H1);
        disable(scrnMsg.xxDtNm_H1);
        // QC#57659
        disable(scrnMsg.xxDtTm_H1);
        disable(scrnMsg.rqstRcvDtTxt_SL);
        disable(scrnMsg.prchReqSrcTpDescTxt_H1);
        disable(scrnMsg.fullPsnNm_H1);
        disable(scrnMsg.dsAcctNm_H1);
        disable(scrnMsg.prchReqCmntTxt_H1);
        // Common(Additional Header)
        disable(scrnMsg.shipToCustCd_H2);
        disable(scrnMsg.locNm_H2);
        disable(scrnMsg.shipToAddlLocNm_H2);
        disable(scrnMsg.xxPopPrm_H2);
        disable(scrnMsg.shipToPostCd_H2);
        disable(scrnMsg.shipToCtyAddr_H2);
        disable(scrnMsg.shipToCntyNm_H2);
        disable(scrnMsg.shipToStCd_H2);
        disable(scrnMsg.shipToProvNm_H2);
        disable(scrnMsg.ctryNm_H2);
        // START 2018/03/13 S.Katsuma [SOL#118(QC#12110),ADD]
        disable(scrnMsg.techExpRqstFlg);
        // END 2018/03/13 S.Katsuma [SOL#118(QC#12110),ADD]
        disable(scrnMsg.hazMatFlg);
        // Common(Details)
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NPAL1080_ABMsg abMsg = scrnMsg.A.no(i);
            disable(abMsg.prchReqLineNum_D1);
            disable(abMsg.prchReqLineSubNum_D1);
            disable(abMsg.prchReqLineStsDescTxt_D1);
            disable(abMsg.mdseDescShortTxt_D1);
            disable(abMsg.prntVndCd_D1);
            disable(abMsg.dsSoLineStsDescTxt_D1);
            disable(abMsg.rwsStsDescTxt_D1);
            disable(abMsg.shpgSvcLvlCd_D1);
            // QC#24918
            disable(abMsg.shpgSvcLvlDescTxt_D1);
            disable(abMsg.proNum_D1);
        }
        disable(scrnMsg.thisMthTotStdCostAmt_D2);

        disable(handler, BTN_CMN_BTN_APPROVE[0]);
        disable(handler, BTN_CMN_BTN_REJECT[0]);
        disable(handler, BTN_CMN_BTN_DOWNLOAD[0]);
        enable(handler, BTN_CMN_BTN_CLEAR[0]);
        enable(handler, BTN_CMN_BTN_RESET[0]);
        enable(handler, BTN_CMN_BTN_RETURN[0]);

        if (!canSubmit) {
            controlForReadonly(handler, scrnMsg);
        } else {
            // Init New
            if (mode == MODE_BTN_INIT) {
                controlForInit(handler, scrnMsg);
            } else if (mode == MODE_BTN_COPY) {
                controlForCopy(handler, scrnMsg);
            }
            // START 2023/04/04 T.Kuroda [QC#61204, MOD]
            else if (mode == MODE_BTN_ADDLINE) {
//            if (mode == MODE_BTN_ADDLINE) {
            // END   2023/04/04 T.Kuroda [QC#61204, MOD]
                controlForAddLine(handler, scrnMsg);
            } else {
                // QC#26662 Update 2018/08/20. Add Function of special Update mode.
                // Other
                controlForOther(handler, scrnMsg, isSpecUpdate);
            }
        }
        // START 2018/12/05 M.Naito [QC#25900,ADD]
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NPAL1080_ABMsg abMsg = scrnMsg.A.no(i);
            if (ZYPCommonFunc.hasValue(abMsg.prchReqLineStsCd_D1) && PRCH_REQ_LINE_STS.CANCELLED.equals(abMsg.prchReqLineStsCd_D1.getValue())) {
                enable(abMsg.xxChkBox_D1);
                disable(abMsg.prchReqLineTpCd_SE);
                handler.setButtonEnabled(BTN_DETAIL_ITEM, i, false);
                handler.setButtonEnabled(BTN_DETAIL_GET_ITEM_NAME, i, false);
                disable(abMsg.mdseCd_D1);
                disable(abMsg.prchReqQty_D1);
                disable(abMsg.procrTpCd_SE);
                disable(abMsg.prntVndCd_D1);
                disable(abMsg.prntVndNm_D1);
                handler.setButtonEnabled(BTN_DETAIL_WH, i, false);
                handler.setButtonEnabled(BTN_DETAIL_GET_WH_SUPPLER, i, false);
                disable(abMsg.locNm_D1);
                disable(abMsg.prchReqLineCmntTxt_D1);
            }
        }
        // END 2018/12/05 M.Naito [QC#25900,ADD]
    }

    private static void controlForInit(S21CommonHandler handler, NPAL1080BMsg scrnMsg) {
        // Header(Button)
        enable(handler, "Search");
        enable(handler, "Copy");
        disable(handler, "HeaderCancel");
        disable(handler, "HeaderClose");
        disable(handler, "OpenWin_ApprvHist");
        enable(handler, "GetTechLocInfo");
        disable(handler, "Attachments");
        // Header
        enable(scrnMsg.prchReqNum_H1);
        enable(scrnMsg.prchReqTpCd_SE);
        enable(scrnMsg.rqstRcvDt_H1);
        enable(scrnMsg.xxDtNm_H2);
        // QC#57659
        enable(scrnMsg.xxDtTm_H1);
        enable(scrnMsg.rqstRcvDtTxt_SL);
        // QC:7389
        //enable(scrnMsg.rqstRcvDtTxt_SE);
        disable(scrnMsg.dsCondConstCd_SE);

        enable(scrnMsg.fsrNum_H1);
        enable(scrnMsg.svcTaskNum_H1);
        enable(scrnMsg.svcMachSerNum_H1);
        enable(scrnMsg.ctacPsnNm_H1);
        enable(scrnMsg.rqstTechTocCd_H1);
        enable(scrnMsg.techNm_H1);
        enable(scrnMsg.destRtlWhCd_H1);
        enable(scrnMsg.destRtlSwhCd_H1);
        enable(scrnMsg.shipToCustCd_H1);
        enable(scrnMsg.shpgSvcLvlCd_SE);
        enable(scrnMsg.carrNm_H1);
        enable(scrnMsg.rqstTechTocCd_AC);
        enable(scrnMsg.rtlWhCd_AC);
        enable(scrnMsg.shipToCustCd_AC);
        enable(scrnMsg.carrCd_AC);
        // Header Notes
        enable(scrnMsg.delyAddlCmntTxt_H2);
        // Detail
        enable(handler, "AddLine");
        // Detail Footer
        disable(handler, "SelectAll");
        disable(handler, "UnSelectAll");
        disable(handler, "History");
        disable(handler, "OpenWin_TechInv");
        disable(handler, "PRFreeze");
        disable(handler, "LineCancel");
        // START 2023/03/22 T.Kuroda [QC#61204, ADD]
        enable(scrnMsg.xxLinkAncr_IL);
        enable(scrnMsg.xxFileData);
        enable(handler, "Upload");
        // END   2023/03/22 T.Kuroda [QC#61204, ADD]
        // Footer
        enable(handler, BTN_CMN_BTN_SAVE[0]);
        enable(handler, BTN_CMN_BTN_SUBMIT[0]);
        enable(handler, BTN_ADD_LINE[0]);
        disable(handler, BTN_CMN_BTN_DELETE[0]);
    }

    private static void controlForCopy(S21CommonHandler handler, NPAL1080BMsg scrnMsg) {
        // Copy from PR#
        // Header(Button)
        enable(handler, "Search");
        disable(handler, "Copy");
        enable(handler, "HeaderCancel");
        enable(handler, "HeaderClose");
        enable(handler, "OpenWin_ApprvHist");
        enable(handler, "GetTechLocInfo");
        disable(handler, "Attachments");
        // Header
        enable(scrnMsg.prchReqNum_H1);
        enable(scrnMsg.prchReqTpCd_SE);
        enable(scrnMsg.rqstRcvDt_H1);
        enable(scrnMsg.xxDtNm_H2);
        // QC#57659
        enable(scrnMsg.xxDtTm_H1);
        enable(scrnMsg.rqstRcvDtTxt_SL);
        enable(scrnMsg.dsCondConstCd_SE);
        enable(scrnMsg.fsrNum_H1);
        enable(scrnMsg.svcTaskNum_H1);
        enable(scrnMsg.svcMachSerNum_H1);
        enable(scrnMsg.ctacPsnNm_H1);
        enable(scrnMsg.rqstTechTocCd_H1);
        enable(scrnMsg.techNm_H1);
        enable(scrnMsg.destRtlWhCd_H1);
        enable(scrnMsg.destRtlSwhCd_H1);
        enable(scrnMsg.shipToCustCd_H1);
        enable(scrnMsg.shpgSvcLvlCd_SE);
        enable(scrnMsg.carrNm_H1);
        enable(scrnMsg.rqstTechTocCd_AC);
        enable(scrnMsg.rtlWhCd_AC);
        enable(scrnMsg.shipToCustCd_AC);
        enable(scrnMsg.carrCd_AC);
        // Header Notes
        enable(scrnMsg.delyAddlCmntTxt_H2);
        // Detail
        enable(handler, "AddLine");
        // Details
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NPAL1080_ABMsg abMsg = scrnMsg.A.no(i);
            enable(abMsg.xxChkBox_D1);
            disable(abMsg.prchReqLineNum_D1);
            disable(abMsg.prchReqLineSubNum_D1);
            if (PRCH_REQ_REC_TP.TECH_RETURN.equals(getPrchReqRecTpCdFromReqTpCd(scrnMsg, scrnMsg.prchReqTpCd_SE.getValue()))) {
                enable(abMsg.prchReqLineTpCd_SE);
                // START 2017/10/25 S.Katsuma QC#21896 ADD
                disable(abMsg.procrTpCd_SE);
                if (scrnMsg.destRtlSwhCd_H1.getValue().equals(TECH_SWH_CD_R)) {
                    disable(abMsg.locNm_D1);
                    handler.setButtonEnabled(BTN_DETAIL_WH, i, false);
                } else {
                    enable(abMsg.locNm_D1);
                    handler.setButtonEnabled(BTN_DETAIL_WH, i, true);
                }
//                ZYPEZDItemValueSetter.setValue(abMsg.procrTpCd_SE, PROCR_TP.WAREHOUSE);
                // END 2017/10/25 S.Katsuma QC#21896 ADD
            } else {
                disable(abMsg.prchReqLineTpCd_SE);
                abMsg.prchReqLineTpCd_SE.setValue("");
                enable(abMsg.procrTpCd_SE);
                // START 2017/10/25 S.Katsuma QC#21896 ADD
                handler.setButtonEnabled(BTN_DETAIL_WH, i, true);
                enable(abMsg.locNm_D1);
                // END 2017/10/25 S.Katsuma QC#21896 ADD
            }
            enable(abMsg.mdseCd_D1);
            handler.setButtonEnabled(BTN_DETAIL_ITEM, i, true);
            handler.setButtonEnabled(BTN_DETAIL_GET_ITEM_NAME, i, true);
            enable(abMsg.prchReqQty_D1);
            enable(abMsg.prntVndCd_D1);
            enable(abMsg.prntVndNm_D1);
            // START 2017/10/25 S.Katsuma QC#21896 DEL
//            handler.setButtonEnabled(BTN_DETAIL_WH, i, true);
            // END 2017/10/25 S.Katsuma QC#21896 DEL
            handler.setButtonEnabled(BTN_DETAIL_GET_WH_SUPPLER, i, true);
            // START 2017/10/25 S.Katsuma QC#21896 DEL
//            enable(abMsg.locNm_D1);
            // END 2017/10/25 S.Katsuma QC#21896 DEL
            if (!isParentDetail(abMsg) || isCancelledOrClosed(abMsg.prchReqLineStsCd_D1)) {
                disable(abMsg.prchReqLineCmntTxt_D1);
            } else {
                enable(abMsg.prchReqLineCmntTxt_D1);
            }
        }
        // Detail Footer
        enable(handler, "SelectAll");
        enable(handler, "UnSelectAll");
        enable(handler, "History");
        enable(handler, "OpenWin_TechInv");
        enable(handler, "PRFreeze");
        enable(handler, "LineCancel");
        // START 2023/03/22 T.Kuroda [QC#61204, ADD]
        enable(scrnMsg.xxLinkAncr_IL);
        enable(scrnMsg.xxFileData);
        enable(handler, "Upload");
        // END   2023/03/22 T.Kuroda [QC#61204, ADD]
        // Footer
        enable(handler, BTN_CMN_BTN_SAVE[0]);
        enable(handler, BTN_CMN_BTN_SUBMIT[0]);
        enable(handler, BTN_ADD_LINE[0]);
    }

    private static void controlForAddLine(S21CommonHandler handler, NPAL1080BMsg scrnMsg) {
        if (RUN_MODE_SAVED.equals(scrnMsg.xxEdtModeFlg.getValue())) {
            // New Add Line (saved)
            // Header(Button)
            enable(handler, "Search");
            disable(handler, "GetTechLocInfo");
            // Header
            enable(scrnMsg.prchReqNum_H1);
            disable(scrnMsg.prchReqTpCd_SE);
            enable(scrnMsg.rqstRcvDt_H1);
            enable(scrnMsg.xxDtNm_H2);
            // QC#57659
            enable(scrnMsg.xxDtTm_H1);
            enable(scrnMsg.rqstRcvDtTxt_SL);
            enable(scrnMsg.dsCondConstCd_SE);
            enable(scrnMsg.fsrNum_H1);
            enable(scrnMsg.svcTaskNum_H1);
            enable(scrnMsg.svcMachSerNum_H1);
            enable(scrnMsg.ctacPsnNm_H1);
            disable(scrnMsg.rqstTechTocCd_H1);
            disable(scrnMsg.techNm_H1);
            disable(scrnMsg.destRtlWhCd_H1);
            disable(scrnMsg.destRtlSwhCd_H1);
            enable(scrnMsg.shipToCustCd_H1);
            enable(scrnMsg.shpgSvcLvlCd_SE);
            enable(scrnMsg.carrNm_H1);
            disable(scrnMsg.rqstTechTocCd_AC);
            disable(scrnMsg.rtlWhCd_AC);
            enable(scrnMsg.shipToCustCd_AC);
            enable(scrnMsg.carrCd_AC);
            // Header Notes
            enable(scrnMsg.delyAddlCmntTxt_H2);
            // Detail
            enable(handler, "AddLine");
            // Details
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                NPAL1080_ABMsg abMsg = scrnMsg.A.no(i);
                if (isParentDetail(abMsg)) {
                    enable(abMsg.xxChkBox_D1);
                    if (PRCH_REQ_REC_TP.TECH_RETURN.equals(getPrchReqRecTpCdFromReqTpCd(scrnMsg, scrnMsg.prchReqTpCd_SE.getValue()))) {
                        enable(abMsg.prchReqLineTpCd_SE);
                        // START 2017/10/25 S.Katsuma QC#21896 ADD
                        disable(abMsg.procrTpCd_SE);
                        if (scrnMsg.destRtlSwhCd_H1.getValue().equals(TECH_SWH_CD_R)) {
                            disable(abMsg.locNm_D1);
                            handler.setButtonEnabled(BTN_DETAIL_WH, i, false);
                        } else {
                            enable(abMsg.locNm_D1);
                            handler.setButtonEnabled(BTN_DETAIL_WH, i, true);
                        }
                        // ZYPEZDItemValueSetter.setValue(abMsg.procrTpCd_SE,
                        // PROCR_TP.WAREHOUSE);
                        // END 2017/10/25 S.Katsuma QC#21896 ADD
                    } else {
                        disable(abMsg.prchReqLineTpCd_SE);
                        abMsg.prchReqLineTpCd_SE.setValue("");
                        enable(abMsg.procrTpCd_SE);
                        // START 2017/10/25 S.Katsuma QC#21896 ADD
                        handler.setButtonEnabled(BTN_DETAIL_WH, i, true);
                        enable(abMsg.locNm_D1);
                        // END 2017/10/25 S.Katsuma QC#21896 ADD
                    }
                    enable(abMsg.mdseCd_D1);
                    handler.setButtonEnabled(BTN_DETAIL_ITEM, i, true);
                    handler.setButtonEnabled(BTN_DETAIL_GET_ITEM_NAME, i, true);
                    enable(abMsg.prchReqQty_D1);
                    enable(abMsg.prntVndCd_D1);
                    enable(abMsg.prntVndNm_D1);
                    // START 2017/10/25 S.Katsuma QC#21896 DEL
                    // handler.setButtonEnabled(BTN_DETAIL_WH, i,
                    // true);
                    // END 2017/10/25 S.Katsuma QC#21896 DEL
                    handler.setButtonEnabled(BTN_DETAIL_GET_WH_SUPPLER, i, true);
                    // START 2017/10/25 S.Katsuma QC#21896 DEL
                    // enable(abMsg.locNm_D1);
                    // END 2017/10/25 S.Katsuma QC#21896 DEL
                    enable(abMsg.prchReqLineCmntTxt_D1);
                } else {
                    disable(abMsg.xxChkBox_D1);
                    disable(abMsg.prchReqLineTpCd_SE);
                    abMsg.prchReqLineTpCd_SE.setValue("");
                    disable(abMsg.mdseCd_D1);
                    handler.setButtonEnabled(BTN_DETAIL_ITEM, i, false);
                    handler.setButtonEnabled(BTN_DETAIL_GET_ITEM_NAME, i, false);
                    disable(abMsg.prchReqQty_D1);
                    disable(abMsg.procrTpCd_SE);
                    disable(abMsg.prntVndCd_D1);
                    disable(abMsg.prntVndNm_D1);
                    handler.setButtonEnabled(BTN_DETAIL_WH, i, false);
                    handler.setButtonEnabled(BTN_DETAIL_GET_WH_SUPPLER, i, false);
                    disable(abMsg.locNm_D1);
                    disable(abMsg.prchReqLineCmntTxt_D1);
                }

                // QC#21913
                handler.setButtonEnabled(BTN_DETAIL_GET_TRACKING_NUMBER, i, false);
            }
            // Detail Footer
            enable(handler, "SelectAll");
            enable(handler, "UnSelectAll");
            enable(handler, "History");
            enable(handler, "OpenWin_TechInv");
            enable(handler, "PRFreeze");
            enable(handler, "LineCancel");
            // START 2023/03/22 T.Kuroda [QC#61204, ADD]
            enable(scrnMsg.xxLinkAncr_IL);
            enable(scrnMsg.xxFileData);
            enable(handler, "Upload");
            // END   2023/03/22 T.Kuroda [QC#61204, ADD]
            // Footer
            enable(handler, BTN_CMN_BTN_SAVE[0]);
            enable(handler, BTN_CMN_BTN_SUBMIT[0]);
            enable(handler, BTN_ADD_LINE[0]);
        } else {
            // Header(Button)
            enable(handler, "Search");
            enable(handler, "GetTechLocInfo");
            // Header
            // Update QC#26662.
            if (PRCH_REQ_APVL_STS.APPROVED.equals(scrnMsg.prchReqApvlStsCd_H1.getValue()) || PRCH_REQ_APVL_STS.PRE_APPROVED.equals(scrnMsg.prchReqApvlStsCd_H1.getValue())) {
                enable(scrnMsg.prchReqNum_H1);
                disable(scrnMsg.prchReqTpCd_SE);
                enable(scrnMsg.rqstRcvDt_H1);
                enable(scrnMsg.xxDtNm_H2);
                // QC#57659
                enable(scrnMsg.xxDtTm_H1);
                enable(scrnMsg.rqstRcvDtTxt_SL);
                disable(scrnMsg.dsCondConstCd_SE);
                disable(scrnMsg.fsrNum_H1);
                disable(scrnMsg.svcTaskNum_H1);
                disable(scrnMsg.svcMachSerNum_H1);
                disable(scrnMsg.ctacPsnNm_H1);
                disable(scrnMsg.rqstTechTocCd_H1);
                disable(scrnMsg.techNm_H1);
                disable(scrnMsg.destRtlWhCd_H1);
                disable(scrnMsg.destRtlSwhCd_H1);
                disable(scrnMsg.shipToCustCd_H1);
                disable(scrnMsg.shpgSvcLvlCd_SE);
                disable(scrnMsg.carrNm_H1);
                disable(scrnMsg.rqstTechTocCd_AC);
                disable(scrnMsg.rtlWhCd_AC);
                disable(scrnMsg.shipToCustCd_AC);
                disable(scrnMsg.carrCd_AC);
            } else {
                enable(scrnMsg.prchReqNum_H1);
                enable(scrnMsg.prchReqTpCd_SE);
                enable(scrnMsg.rqstRcvDt_H1);
                enable(scrnMsg.xxDtNm_H2);
                // QC#57659
                enable(scrnMsg.xxDtTm_H1);
                enable(scrnMsg.rqstRcvDtTxt_SL);
                enable(scrnMsg.dsCondConstCd_SE);
                enable(scrnMsg.fsrNum_H1);
                enable(scrnMsg.svcTaskNum_H1);
                enable(scrnMsg.svcMachSerNum_H1);
                enable(scrnMsg.ctacPsnNm_H1);
                enable(scrnMsg.rqstTechTocCd_H1);
                enable(scrnMsg.techNm_H1);
                enable(scrnMsg.destRtlWhCd_H1);
                enable(scrnMsg.destRtlSwhCd_H1);
                enable(scrnMsg.shipToCustCd_H1);
                enable(scrnMsg.shpgSvcLvlCd_SE);
                enable(scrnMsg.carrNm_H1);
                enable(scrnMsg.rqstTechTocCd_AC);
                enable(scrnMsg.rtlWhCd_AC);
                enable(scrnMsg.shipToCustCd_AC);
                enable(scrnMsg.carrCd_AC);
            }
            // Header Notes
            enable(scrnMsg.delyAddlCmntTxt_H2);
            // Detail
            enable(handler, "AddLine");
            // Details
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                NPAL1080_ABMsg abMsg = scrnMsg.A.no(i);
                enable(abMsg.xxChkBox_D1);

                // QC#26662 Update.
                if (ZYPCommonFunc.hasValue(abMsg.prchReqLineStsCd_D1)) {
                    disable(abMsg.prchReqLineTpCd_SE);
                    disable(abMsg.mdseCd_D1);
                    handler.setButtonEnabled(BTN_DETAIL_ITEM, i, false);
                    handler.setButtonEnabled(BTN_DETAIL_GET_ITEM_NAME, i, false);
                    disable(abMsg.prchReqQty_D1);
                    disable(abMsg.procrTpCd_SE);
                    disable(abMsg.prntVndCd_D1);
                    disable(abMsg.prntVndNm_D1);
                    handler.setButtonEnabled(BTN_DETAIL_WH, i, false);
                    handler.setButtonEnabled(BTN_DETAIL_GET_WH_SUPPLER, i, false);
                    disable(abMsg.locNm_D1);
                    if (isParentDetail(abMsg)) {
                        enable(abMsg.prchReqLineCmntTxt_D1);
                    } else {
                        disable(abMsg.prchReqLineCmntTxt_D1);
                    }
                    // QC#21913
                    handler.setButtonEnabled(BTN_DETAIL_GET_TRACKING_NUMBER, i, true);

                } else {

                    if (PRCH_REQ_REC_TP.TECH_RETURN.equals(getPrchReqRecTpCdFromReqTpCd(scrnMsg, scrnMsg.prchReqTpCd_SE.getValue()))) {
                        enable(abMsg.prchReqLineTpCd_SE);
                        // START 2017/10/25 S.Katsuma QC#21896 ADD
                        disable(abMsg.procrTpCd_SE);
                        if (scrnMsg.destRtlSwhCd_H1.getValue().equals(TECH_SWH_CD_R)) {
                            disable(abMsg.locNm_D1);
                            handler.setButtonEnabled(BTN_DETAIL_WH, i, false);
                        } else {
                            enable(abMsg.locNm_D1);
                            handler.setButtonEnabled(BTN_DETAIL_WH, i, true);
                        }
                        // ZYPEZDItemValueSetter.setValue(abMsg.procrTpCd_SE,
                        // PROCR_TP.WAREHOUSE);
                        // END 2017/10/25 S.Katsuma QC#21896 ADD
                    } else {
                        disable(abMsg.prchReqLineTpCd_SE);
                        abMsg.prchReqLineTpCd_SE.setValue("");
                        enable(abMsg.procrTpCd_SE);
                        // START 2017/10/25 S.Katsuma QC#21896 ADD
                        handler.setButtonEnabled(BTN_DETAIL_WH, i, true);
                        enable(abMsg.locNm_D1);
                        // END 2017/10/25 S.Katsuma QC#21896 ADD
                    }
                    enable(abMsg.mdseCd_D1);
                    handler.setButtonEnabled(BTN_DETAIL_ITEM, i, true);
                    handler.setButtonEnabled(BTN_DETAIL_GET_ITEM_NAME, i, true);
                    enable(abMsg.prchReqQty_D1);
                    enable(abMsg.prntVndCd_D1);
                    enable(abMsg.prntVndNm_D1);
                    // START 2017/10/25 S.Katsuma QC#21896 DEL
                    // handler.setButtonEnabled(BTN_DETAIL_WH, i,
                    // true);
                    // END 2017/10/25 S.Katsuma QC#21896 DEL
                    handler.setButtonEnabled(BTN_DETAIL_GET_WH_SUPPLER, i, true);
                    // START 2017/10/25 S.Katsuma QC#21896 DEL
                    // enable(abMsg.locNm_D1);
                    // END 2017/10/25 S.Katsuma QC#21896 DEL
                    if (!isParentDetail(abMsg) || isCancelledOrClosed(abMsg.prchReqLineStsCd_D1)) {
                        disable(abMsg.prchReqLineCmntTxt_D1);
                    } else {
                        enable(abMsg.prchReqLineCmntTxt_D1);
                    }

                    // QC#21913
                    handler.setButtonEnabled(BTN_DETAIL_GET_TRACKING_NUMBER, i, false);
                }
            }
            // Detail Footer
            enable(handler, "SelectAll");
            enable(handler, "UnSelectAll");
            enable(handler, "History");
            enable(handler, "OpenWin_TechInv");
            enable(handler, "PRFreeze");
            enable(handler, "LineCancel");
            // START 2023/03/22 T.Kuroda [QC#61204, ADD]
            enable(scrnMsg.xxLinkAncr_IL);
            enable(scrnMsg.xxFileData);
            enable(handler, "Upload");
            // END   2023/03/22 T.Kuroda [QC#61204, ADD]
            // Footer
            // QC#26662 Update.
            if (PRCH_REQ_APVL_STS.APPROVED.equals(scrnMsg.prchReqApvlStsCd_H1.getValue()) //
                    || PRCH_REQ_APVL_STS.PRE_APPROVED.equals(scrnMsg.prchReqApvlStsCd_H1.getValue())) {
                // QC#30355
                enable(handler, BTN_CMN_BTN_SAVE[0]);
            } else {
                enable(handler, BTN_CMN_BTN_SAVE[0]);
            }
            enable(handler, BTN_CMN_BTN_SUBMIT[0]);
            enable(handler, BTN_ADD_LINE[0]);
        }
    }

    private static void controlForOther(S21CommonHandler handler, NPAL1080BMsg scrnMsg, boolean isSpecUpdate) {
        if (RUN_MODE_SAVED.equals(scrnMsg.xxEdtModeFlg.getValue())) {
            // Header(Button)
            enable(handler, "Search");
            enable(handler, "Copy");
            enable(handler, "HeaderCancel");
            disable(handler, "HeaderClose");
            enable(handler, "OpenWin_ApprvHist");
            disable(handler, "GetTechLocInfo");
            enable(handler, "Attachments");
            
            // Header
            enable(scrnMsg.prchReqNum_H1);
            disable(scrnMsg.prchReqTpCd_SE);
            enable(scrnMsg.rqstRcvDt_H1);
            enable(scrnMsg.xxDtNm_H2);
            // QC#57659
            enable(scrnMsg.xxDtTm_H1);
            enable(scrnMsg.rqstRcvDtTxt_SL);
            // QC:7389
            //enable(scrnMsg.rqstRcvDtTxt_SE);
            disable(scrnMsg.dsCondConstCd_SE);
            
            enable(scrnMsg.fsrNum_H1);
            enable(scrnMsg.svcTaskNum_H1);
            enable(scrnMsg.svcMachSerNum_H1);
            enable(scrnMsg.ctacPsnNm_H1);
            disable(scrnMsg.rqstTechTocCd_H1);
            disable(scrnMsg.techNm_H1);
            disable(scrnMsg.destRtlWhCd_H1);
            disable(scrnMsg.destRtlSwhCd_H1);
            enable(scrnMsg.shipToCustCd_H1);
            enable(scrnMsg.shpgSvcLvlCd_SE);
            enable(scrnMsg.carrNm_H1);
            disable(scrnMsg.rqstTechTocCd_AC);
            disable(scrnMsg.rtlWhCd_AC);
            enable(scrnMsg.shipToCustCd_AC);
            enable(scrnMsg.carrCd_AC);
            // Header Notes
            enable(scrnMsg.delyAddlCmntTxt_H2);
            // Detail
            enable(handler, "AddLine");
            // Details
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                NPAL1080_ABMsg abMsg = scrnMsg.A.no(i);
                if (ZYPCommonFunc.hasValue(abMsg.insrcFlg_D1.getValue()) && ZYPConstant.FLG_ON_Y.equals(abMsg.insrcFlg_D1.getValue())) {
                    continue;
                }
                // QC#55615
                if (isParentDetail(abMsg) && !ZYPCommonFunc.hasValue(abMsg.poSchdRelDt_D1)) {
                    enable(abMsg.xxChkBox_D1);
                    if (PRCH_REQ_REC_TP.TECH_RETURN.equals(getPrchReqRecTpCdFromReqTpCd(scrnMsg, scrnMsg.prchReqTpCd_SE.getValue()))) {
                        enable(abMsg.prchReqLineTpCd_SE);
                        // START 2017/10/25 S.Katsuma QC#21896 ADD
                        disable(abMsg.procrTpCd_SE);
                        if (scrnMsg.destRtlSwhCd_H1.getValue().equals(TECH_SWH_CD_R)) {
                            disable(abMsg.locNm_D1);
                            handler.setButtonEnabled(BTN_DETAIL_WH, i, false);
                        } else {
                            enable(abMsg.locNm_D1);
                            handler.setButtonEnabled(BTN_DETAIL_WH, i, true);
                        }
//                        ZYPEZDItemValueSetter.setValue(abMsg.procrTpCd_SE, PROCR_TP.WAREHOUSE);
                        // END 2017/10/25 S.Katsuma QC#21896 ADD
                    } else {
                        disable(abMsg.prchReqLineTpCd_SE);
                        abMsg.prchReqLineTpCd_SE.setValue("");
                        enable(abMsg.procrTpCd_SE);
                        // START 2017/10/25 S.Katsuma QC#21896 ADD
                        handler.setButtonEnabled(BTN_DETAIL_WH, i, true);
                        enable(abMsg.locNm_D1);
                        // END 2017/10/25 S.Katsuma QC#21896 ADD
                    }
                    enable(abMsg.mdseCd_D1);
                    handler.setButtonEnabled(BTN_DETAIL_ITEM, i, true);
                    handler.setButtonEnabled(BTN_DETAIL_GET_ITEM_NAME, i, true);
                    enable(abMsg.prchReqQty_D1);
                    enable(abMsg.prntVndCd_D1);
                    enable(abMsg.prntVndNm_D1);
                    // START 2017/10/25 S.Katsuma QC#21896 DEL
//                  handler.setButtonEnabled(BTN_DETAIL_WH, i, true);
                    // END 2017/10/25 S.Katsuma QC#21896 DEL
                    handler.setButtonEnabled(BTN_DETAIL_GET_WH_SUPPLER, i, true);
                    // START 2017/10/25 S.Katsuma QC#21896 DEL
//                  enable(abMsg.locNm_D1);
                    // END 2017/10/25 S.Katsuma QC#21896 DEL
                    enable(abMsg.prchReqLineCmntTxt_D1);
                } else {
                    disable(abMsg.xxChkBox_D1);
                    disable(abMsg.prchReqLineTpCd_SE);
                    if (isParentDetail(abMsg)) {
                        enable(abMsg.xxChkBox_D1);
                        abMsg.prchReqLineTpCd_SE.setValue("");
                    }
                    disable(abMsg.mdseCd_D1);
                    handler.setButtonEnabled(BTN_DETAIL_ITEM, i, false);
                    handler.setButtonEnabled(BTN_DETAIL_GET_ITEM_NAME, i, false);
                    disable(abMsg.prchReqQty_D1);
                    disable(abMsg.procrTpCd_SE);
                    disable(abMsg.prntVndCd_D1);
                    disable(abMsg.prntVndNm_D1);
                    handler.setButtonEnabled(BTN_DETAIL_WH, i, false);
                    handler.setButtonEnabled(BTN_DETAIL_GET_WH_SUPPLER, i, false);
                    disable(abMsg.locNm_D1);
                    disable(abMsg.prchReqLineCmntTxt_D1);
                }

                // QC#21913
                handler.setButtonEnabled(BTN_DETAIL_GET_TRACKING_NUMBER, i, false);
            }
            // Detail Footer
            enable(handler, "SelectAll");
            enable(handler, "UnSelectAll");
            enable(handler, "History");
            enable(handler, "OpenWin_TechInv");
            enable(handler, "PRFreeze");
            enable(handler, "LineCancel");
            // START 2023/03/22 T.Kuroda [QC#61204, ADD]
            enable(scrnMsg.xxLinkAncr_IL);
            enable(scrnMsg.xxFileData);
            enable(handler, "Upload");
            // END   2023/03/22 T.Kuroda [QC#61204, ADD]
            // Footer
            enable(handler, BTN_CMN_BTN_SAVE[0]);
            enable(handler, BTN_CMN_BTN_SUBMIT[0]);
            enable(handler, BTN_ADD_LINE[0]);
        } else if (RUN_MODE_SUBMITTED.equals(scrnMsg.xxEdtModeFlg.getValue())) {
            // Saved 'N'
            // Header(Button)
            enable(handler, "Search");
            enable(handler, "Copy");
            disable(handler, "HeaderCancel");
            disable(handler, "HeaderClose");
            if (PRCH_REQ_APVL_STS.APPROVED.equals(scrnMsg.prchReqApvlStsCd_H1.getValue()) || PRCH_REQ_APVL_STS.PRE_APPROVED.equals(scrnMsg.prchReqApvlStsCd_H1.getValue())) {
                // QC#55710
                if (PRCH_REQ_TP.TECH_RETURN.equals(scrnMsg.prchReqTpCd_SE.getValue()) || PRCH_REQ_TP.CONFIRM_ONLY_RETURN.equals(scrnMsg.prchReqTpCd_SE.getValue())) {
                    enable(handler, "HeaderClose");
                } else {
                    String prchReqLineNum_Pre = "";
                    String prchReqLineNum = "";
                    for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                        prchReqLineNum = scrnMsg.A.no(i).prchReqLineNum_D1.getValue();
                        if (prchReqLineNum_Pre.equals(prchReqLineNum)) {
                            enable(handler, "HeaderClose");
                            break;
                        }
                        prchReqLineNum_Pre = prchReqLineNum;
                    }
                }
            }
            enable(handler, "OpenWin_ApprvHist");
            disable(handler, "GetTechLocInfo");
            enable(handler, "Attachments");
            // Header
            enable(scrnMsg.prchReqNum_H1);
            disable(scrnMsg.prchReqTpCd_SE);
            disable(scrnMsg.rqstRcvDt_H1);
            disable(scrnMsg.xxDtNm_H2);
            // QC#57659
            disable(scrnMsg.xxDtTm_H1);
            disable(scrnMsg.rqstRcvDtTxt_SL);
            disable(scrnMsg.dsCondConstCd_SE);
            disable(scrnMsg.fsrNum_H1);
            disable(scrnMsg.svcTaskNum_H1);
            disable(scrnMsg.svcMachSerNum_H1);
            disable(scrnMsg.ctacPsnNm_H1);
            disable(scrnMsg.rqstTechTocCd_H1);
            disable(scrnMsg.techNm_H1);
            disable(scrnMsg.destRtlWhCd_H1);
            disable(scrnMsg.destRtlSwhCd_H1);
            disable(scrnMsg.shipToCustCd_H1);
            disable(scrnMsg.shpgSvcLvlCd_SE);
            disable(scrnMsg.carrNm_H1);
            disable(scrnMsg.rqstTechTocCd_AC);
            disable(scrnMsg.rtlWhCd_AC);
            disable(scrnMsg.shipToCustCd_AC);
            disable(scrnMsg.carrCd_AC);
            // Header Notes
            enable(scrnMsg.delyAddlCmntTxt_H2);
            // Detail
            // QC#26662 Update 2018/08/20. Add Function of special Update mode.
            if (isSpecUpdate) {
                enable(handler, "AddLine");
            } else {
                disable(handler, "AddLine");
            }

            // Details
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                NPAL1080_ABMsg abMsg = scrnMsg.A.no(i);
                enable(abMsg.xxChkBox_D1);
                disable(abMsg.prchReqLineTpCd_SE);
                disable(abMsg.mdseCd_D1);
                handler.setButtonEnabled(BTN_DETAIL_ITEM, i, false);
                handler.setButtonEnabled(BTN_DETAIL_GET_ITEM_NAME, i, false);
                disable(abMsg.prchReqQty_D1);
                disable(abMsg.procrTpCd_SE);
                disable(abMsg.prntVndCd_D1);
                disable(abMsg.prntVndNm_D1);
                handler.setButtonEnabled(BTN_DETAIL_WH, i, false);
                handler.setButtonEnabled(BTN_DETAIL_GET_WH_SUPPLER, i, false);
                disable(abMsg.locNm_D1);
                if (isParentDetail(abMsg)) {
                    enable(abMsg.prchReqLineCmntTxt_D1);
                } else {
                    disable(abMsg.prchReqLineCmntTxt_D1);
                }
                // QC#21913
                handler.setButtonEnabled(BTN_DETAIL_GET_TRACKING_NUMBER, i, true);
            }
            // Detail Footer
            enable(handler, "SelectAll");
            enable(handler, "UnSelectAll");
            enable(handler, "History");
            enable(handler, "OpenWin_TechInv");
            enable(handler, "PRFreeze");
            // QC#26581 Update. 08/17/2018
            // QC#30335
            if (PRCH_REQ_APVL_STS.AWAITING_APPROVAL.equals(scrnMsg.prchReqApvlStsCd_H1.getValue())) {
                disable(handler, "LineCancel");
            } else {
                enable(handler, "LineCancel");   
            }
            // START 2023/03/22 T.Kuroda [QC#61204, ADD]
            if (isSpecUpdate) {
                enable(scrnMsg.xxLinkAncr_IL);
                enable(scrnMsg.xxFileData);
                enable(handler, "Upload");
            } else {
                disable(scrnMsg.xxLinkAncr_IL);
                disable(scrnMsg.xxFileData);
                disable(handler, "Upload");
            }
            // END   2023/03/22 T.Kuroda [QC#61204, ADD]
            // Footer
            disable(handler, BTN_CMN_BTN_SAVE[0]);
            disable(handler, BTN_CMN_BTN_SUBMIT[0]);
            // QC#26662 Update 2018/08/20. Add Function of special Update mode.
            if(isSpecUpdate){
                enable(handler, BTN_ADD_LINE[0]);
            } else {
                disable(handler, BTN_ADD_LINE[0]);
            }
        } else if (RUN_MODE_CLOSED.equals(scrnMsg.xxEdtModeFlg.getValue())) {
            // Header(Button)
            enable(handler, "Search");
            enable(handler, "Copy");
            disable(handler, "HeaderCancel");
            disable(handler, "HeaderClose");
            enable(handler, "OpenWin_ApprvHist");
            disable(handler, "GetTechLocInfo");
            enable(handler, "Attachments");
            // Header
            enable(scrnMsg.prchReqNum_H1);
            disable(scrnMsg.prchReqTpCd_SE);
            disable(scrnMsg.rqstRcvDt_H1);
            disable(scrnMsg.xxDtNm_H2);
            // QC#57659
            disable(scrnMsg.xxDtTm_H1);
            disable(scrnMsg.rqstRcvDtTxt_SL);
            disable(scrnMsg.dsCondConstCd_SE);
            disable(scrnMsg.fsrNum_H1);
            disable(scrnMsg.svcTaskNum_H1);
            disable(scrnMsg.svcMachSerNum_H1);
            disable(scrnMsg.ctacPsnNm_H1);
            disable(scrnMsg.rqstTechTocCd_H1);
            disable(scrnMsg.techNm_H1);
            disable(scrnMsg.destRtlWhCd_H1);
            disable(scrnMsg.destRtlSwhCd_H1);
            disable(scrnMsg.shipToCustCd_H1);
            disable(scrnMsg.shpgSvcLvlCd_SE);
            disable(scrnMsg.carrNm_H1);
            disable(scrnMsg.rqstTechTocCd_AC);
            disable(scrnMsg.rtlWhCd_AC);
            disable(scrnMsg.shipToCustCd_AC);
            disable(scrnMsg.carrCd_AC);
            // Header Notes
            disable(scrnMsg.delyAddlCmntTxt_H2);
            // Detail
            disable(handler, "AddLine");
            // Details
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                NPAL1080_ABMsg abMsg = scrnMsg.A.no(i);
                enable(abMsg.xxChkBox_D1);
                disable(abMsg.prchReqLineTpCd_SE);
                handler.setButtonEnabled(BTN_DETAIL_ITEM, i, false);
                handler.setButtonEnabled(BTN_DETAIL_GET_ITEM_NAME, i, false);
                disable(abMsg.mdseCd_D1);
                disable(abMsg.prchReqQty_D1);
                disable(abMsg.procrTpCd_SE);
                disable(abMsg.prntVndCd_D1);
                disable(abMsg.prntVndNm_D1);
                handler.setButtonEnabled(BTN_DETAIL_WH, i, false);
                handler.setButtonEnabled(BTN_DETAIL_GET_WH_SUPPLER, i, false);
                disable(abMsg.locNm_D1);
                disable(abMsg.prchReqLineCmntTxt_D1);

                // QC#21913
                handler.setButtonEnabled(BTN_DETAIL_GET_TRACKING_NUMBER, i, true);
            }
            // Detail Footer
            disable(handler, "SelectAll");
            disable(handler, "UnSelectAll");
            enable(handler, "History");
            enable(handler, "OpenWin_TechInv");
            disable(handler, "PRFreeze");
            disable(handler, "LineCancel");
            // START 2023/03/22 T.Kuroda [QC#61204, ADD]
            disable(scrnMsg.xxLinkAncr_IL);
            disable(scrnMsg.xxFileData);
            disable(handler, "Upload");
            // END   2023/03/22 T.Kuroda [QC#61204, ADD]
            // Footer
            disable(handler, BTN_CMN_BTN_SAVE[0]);
            disable(handler, BTN_CMN_BTN_SUBMIT[0]);
            disable(handler, BTN_ADD_LINE[0]);
        }
        // START 2023/04/04 T.Kuroda [QC#61204, ADD]
        else if (RUN_MODE_NEW.equals(scrnMsg.xxEdtModeFlg.getValue())) {
            if (0 < scrnMsg.A.getValidCount()) {
                controlForAddLine(handler, scrnMsg);
            }
        }
        // END   2023/04/04 T.Kuroda [QC#61204, ADD]
    }

    private static void controlForReadonly(S21CommonHandler handler, NPAL1080BMsg scrnMsg) {
        // Header(Button)
        enable(handler, "Search");
        disable(handler, "Copy");
        disable(handler, "HeaderCancel");
        disable(handler, "HeaderClose");
        enable(handler, "OpenWin_ApprvHist");
        disable(handler, "GetTechLocInfo");
        disable(handler, "Attachments");
        // Header
        enable(scrnMsg.prchReqNum_H1);
        disable(scrnMsg.prchReqTpCd_SE);
        disable(scrnMsg.rqstRcvDt_H1);
        disable(scrnMsg.xxDtNm_H2);
        // QC#57659
        disable(scrnMsg.xxDtTm_H1);
        disable(scrnMsg.rqstRcvDtTxt_SL);
        disable(scrnMsg.dsCondConstCd_SE);
        disable(scrnMsg.fsrNum_H1);
        disable(scrnMsg.svcTaskNum_H1);
        disable(scrnMsg.svcMachSerNum_H1);
        disable(scrnMsg.ctacPsnNm_H1);
        disable(scrnMsg.rqstTechTocCd_H1);
        disable(scrnMsg.techNm_H1);
        disable(scrnMsg.destRtlWhCd_H1);
        disable(scrnMsg.destRtlSwhCd_H1);
        disable(scrnMsg.shipToCustCd_H1);
        disable(scrnMsg.shpgSvcLvlCd_SE);
        disable(scrnMsg.carrNm_H1);
        disable(scrnMsg.rqstTechTocCd_AC);
        disable(scrnMsg.rtlWhCd_AC);
        disable(scrnMsg.shipToCustCd_AC);
        disable(scrnMsg.carrCd_AC);
        // Header Notes
        disable(scrnMsg.delyAddlCmntTxt_H2);
        // Detail
        disable(handler, "AddLine");
        // Details
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NPAL1080_ABMsg abMsg = scrnMsg.A.no(i);
            enable(abMsg.xxChkBox_D1);
            disable(abMsg.prchReqLineTpCd_SE);
            handler.setButtonEnabled(BTN_DETAIL_ITEM, i, false);
            handler.setButtonEnabled(BTN_DETAIL_GET_ITEM_NAME, i, false);
            disable(abMsg.mdseCd_D1);
            disable(abMsg.prchReqQty_D1);
            disable(abMsg.procrTpCd_SE);
            disable(abMsg.prntVndCd_D1);
            disable(abMsg.prntVndNm_D1);
            handler.setButtonEnabled(BTN_DETAIL_WH, i, false);
            handler.setButtonEnabled(BTN_DETAIL_GET_WH_SUPPLER, i, false);
            disable(abMsg.locNm_D1);
            disable(abMsg.prchReqLineCmntTxt_D1);
        }
        // Detail Footer
        disable(handler, "SelectAll");
        disable(handler, "UnSelectAll");
        enable(handler, "History");
        enable(handler, "OpenWin_TechInv");
        disable(handler, "PRFreeze");
        disable(handler, "LineCancel");
        // START 2023/03/22 T.Kuroda [QC#61204, ADD]
        disable(scrnMsg.xxLinkAncr_IL);
        disable(scrnMsg.xxFileData);
        disable(handler, "Upload");
        // END   2023/03/22 T.Kuroda [QC#61204, ADD]
        // Footer
        disable(handler, BTN_CMN_BTN_SAVE[0]);
        disable(handler, BTN_CMN_BTN_SUBMIT[0]);
        disable(handler, BTN_ADD_LINE[0]);
    }

    // START 2017/10/25 S.Katsuma QC#21896 ADD
    /**
     * 
     * @param handler
     * @param scrnMsg
     */
    public static void controlForTechSwhR(S21CommonHandler handler, NPAL1080BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NPAL1080_ABMsg abMsg = scrnMsg.A.no(i);
            // 2019/02/14 QC#30358 Add Start
            boolean isSubmittedLine = isSubmittedLine(scrnMsg, abMsg);
            // 2019/02/14 QC#30358 Add End
            if (isParentDetail(abMsg)) {
                enable(abMsg.xxChkBox_D1);
                if (PRCH_REQ_REC_TP.TECH_RETURN.equals(getPrchReqRecTpCdFromReqTpCd(scrnMsg, scrnMsg.prchReqTpCd_SE.getValue()))) {
                    enable(abMsg.prchReqLineTpCd_SE);
                    disable(abMsg.procrTpCd_SE);
                    if (scrnMsg.destRtlSwhCd_H1.getValue().equals(TECH_SWH_CD_R)) {
                        disable(abMsg.locNm_D1);
                        handler.setButtonEnabled(BTN_DETAIL_WH, i, false);
                    } else {
                        if (!isSubmittedLine) { // 2019/02/14 QC#30358 Add Condition
                            enable(abMsg.locNm_D1);
                            handler.setButtonEnabled(BTN_DETAIL_WH, i, true);
                        } // 2019/02/14 QC#30358 Add Condition
                    }
                } else {
                    disable(abMsg.prchReqLineTpCd_SE);
                    abMsg.prchReqLineTpCd_SE.setValue("");
                    if (!isSubmittedLine) { // 2019/02/14 QC#30358 Add Condition
                        enable(abMsg.procrTpCd_SE);
                        handler.setButtonEnabled(BTN_DETAIL_WH, i, true);
                        enable(abMsg.locNm_D1);
                    }  // 2019/02/14 QC#30358 Add Condition
                }
            }
        }
    }
    // END 2017/10/25 S.Katsuma QC#21896 ADD

    /**
     * The method explanation: set parameter to call popup.(ZYPL0310)
     * @param p NPAL1080_XBMsgArray
     * @param size int
     * @return Object[]
     */
    public static Object[] toArray_popupForZYPL0310(NPAL1080_ZBMsgArray p, int size) {
        Object[] param = new Object[size];
        for (int i = 0; i < size; i++) {
            param[i] = p.no(i).xxPopPrm_AT.getValue();
        }
        return param;
    }

    // 2019/02/14 QC#30358 Add Start

    private static boolean isSubmittedLine(NPAL1080BMsg scrnMsg, NPAL1080_ABMsg abMsg) {

        if (RUN_MODE_SUBMITTED.equals(scrnMsg.xxEdtModeFlg.getValue()) //
                && ZYPCommonFunc.hasValue(abMsg.prchReqLineStsCd_D1)) {
            return true;
        }
        return false;
    }
    // 2019/02/14 QC#30358 Add End
}
