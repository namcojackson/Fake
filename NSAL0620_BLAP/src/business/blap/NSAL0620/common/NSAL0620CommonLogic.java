/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0620.common;

import static business.blap.NSAL0300.constant.NSAL0300Constant.NSAM0045E;
import static business.blap.NSAL0620.constant.NSAL0620Constant.ACTV_PENDING_PO_CONTR_STS_CD;
import static business.blap.NSAL0620.constant.NSAL0620Constant.ACTV_PENDING_PO_CONTR_STS_NM;
import static business.blap.NSAL0620.constant.NSAL0620Constant.ACTV_RENEWAL_HOLD_CONTR_STS_CD;
import static business.blap.NSAL0620.constant.NSAL0620Constant.ACTV_RENEWAL_HOLD_CONTR_STS_NM;
import static business.blap.NSAL0620.constant.NSAL0620Constant.APL_ID;
import static business.blap.NSAL0620.constant.NSAL0620Constant.DATE_FORMAT_YYYYMMDD;
import static business.blap.NSAL0620.constant.NSAL0620Constant.LIMIT_DL_ROWNUM;
import static business.blap.NSAL0620.constant.NSAL0620Constant.LINE_LVL_ONE;
import static business.blap.NSAL0620.constant.NSAL0620Constant.LINE_LVL_FOUR;
import static business.blap.NSAL0620.constant.NSAL0620Constant.PRINT_BASE;
import static business.blap.NSAL0620.constant.NSAL0620Constant.LIST_MAX_CNT;
import static business.blap.NSAL0620.constant.NSAL0620Constant.LIST_MAX_CNT_BLLG_MTR;
import static business.blap.NSAL0620.constant.NSAL0620Constant.LIST_MAX_CNT_BR_LIST;
import static business.blap.NSAL0620.constant.NSAL0620Constant.OTPT_OP_CD;
import static business.blap.NSAL0620.constant.NSAL0620Constant.PND_ISTL_CONTR_STS_CD;
import static business.blap.NSAL0620.constant.NSAL0620Constant.PND_ISTL_CONTR_STS_NM;
import static business.blap.NSAL0620.constant.NSAL0620Constant.RPT_ID;
import static business.blap.NSAL0620.constant.NSAL0620Constant.SCRN_ID;
import static business.blap.NSAL0620.constant.NSAL0620Constant.STR_COMMA;
import static business.blap.NSAL0620.constant.NSAL0620Constant.US_DOLLAR;
import static business.blap.NSAL0620.constant.NSAL0620Constant.XX_CHK_BOX_A;
import static business.blap.NSAL0620.constant.NSAL0620Constant.COL_NM.SRCH_OPT_NM;
import static business.blap.NSAL0620.constant.NSAL0620Constant.COL_NM.SRCH_OPT_PK;
import static business.blap.NSAL0620.constant.NSAL0620Constant.LAST_DAY_OF_A_MONTH;
import static business.blap.NSAL0620.constant.NSAL0620Constant.LAST_DAY;
import static business.blap.NSAL0620.constant.NSAL0620Constant.SEMIANNUAL;
import static business.blap.NSAL0620.constant.NSAL0620Constant.SEMIANNUALLY;
import static business.blap.NSAL0620.constant.NSAL0620Constant.LINE_LVL_FIVE;
import static business.blap.NSAL0620.constant.NSAL0620Constant.LINE_LVL_TWO;
import static business.blap.NSAL0620.constant.NSAL0620Constant.NONEXISTENT_CODE;
import static business.blap.NSAL0620.constant.NSAL0620Constant.DECIMAL_FORMAT_PATTERN;
import static com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil.isValidDate;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static parts.common.EZDCommonFunc.isNumberCheck;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCDateItem;
import parts.common.EZDCStringItemArray;
import parts.common.EZDMsg;
import parts.common.EZDPStringItem;
import parts.common.EZDTBigDecimalItem;
import parts.dbcommon.EZDTBLAccessor;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NSAL0620.NSAL0620CMsg;
import business.blap.NSAL0620.NSAL0620Query;
import business.blap.NSAL0620.NSAL0620SMsg;
import business.blap.NSAL0620.NSAL0620_ACMsg;
import business.blap.NSAL0620.NSAL0620_ACMsgArray;
import business.blap.NSAL0620.NSAL0620_ASMsg;
import business.blap.NSAL0620.NSAL0620_ASMsgArray;
import business.blap.NSAL0620.constant.NSAL0620Constant.COL_NM;
import business.blap.NSAL0620.constant.NSAL0620Constant.MASS_UPDATE_LIST;
import business.blap.NSAL0620.constant.NSAL0620Constant.MSG_ID;
import business.blap.NSAL0620.constant.NSAL0620Constant.MSG_KIND;
import business.blap.NSAL0620.constant.NSAL0620Constant.TBL_NM;
import business.db.DS_CONTRTMsg;
import business.db.MTR_LBTMsg;
import business.db.MTR_LBTMsgArray;
import business.db.MTR_READ_METHTMsg;
import business.db.MTR_READ_METHTMsgArray;
import business.db.SVC_CONTR_BRTMsg;
import business.db.SVC_CONTR_BRTMsgArray;
import business.db.SVC_PRC_RNW_LTR_WRKTMsg;
import business.db.SVC_PRC_RNW_LTR_WRKTMsgArray;
import business.db.SVC_RGTMsg;
import business.db.SVC_RGTMsgArray;
import business.db.SVC_TERM_COND_LTR_WRKTMsg;
import business.db.SVC_TERM_COND_LTR_WRKTMsgArray;
import business.file.NSAL0620F00FMsg;
import business.file.NSAL0620F01FMsg;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.common.ZYPFormatUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Contract Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/23   Hitachi         T.Tsuchida      Create          N/A
 * 2016/03/25   Hitachi         M.Gotou         Update          QC4595
 * 2016/04/25   Hitachi         M.Gotou         Update          QC4326
 * 2016/07/01   Hitachi         T.Aoyagi        Update          QC#11261
 * 2016/07/28   Hitachi         A.Kohinata      Update          QC6923
 * 2016/08/02   Hitachi         A.Kohinata      Update          QC#4433
 * 2016/08/25   Hitachi         A.Kohinata      Update          QC#11027
 * 2016/10/28   Hitachi         T.Tomita        Update          QC#15468
 * 2017/02/07   Hitachi         K.Kojima        Update          QC#17303
 * 2017/05/25   Hitachi         Y.Osawa         Update          QC#18560
 * 2017/08/21   Hitachi         E.Kameishi      Update          QC#8661
 * 2017/09/20   Hitachi         A.Kohinata      Update          QC#18534
 * 2017/10/02   Hitachi         K.Kojima        Update          QC#18417
 * 2017/11/14   Hitachi         K.Kojima        Update          QC#21886
 * 2018/01/16   Hitachi         K.Kojima        Update          QC#21811
 * 2018/02/06   Hitachi         M.Kidokoro      Update          QC#23375
 * 2018/03/23   Hitachi         K.Kojima        Update          QC#22722
 * 2018/05/14   CITS            T.Wada          Update          QC#25904
 * 2018/05/25   Hitachi         K.Kitachi       Update          QC#26253
 * 2018/06/05   Fujitsu         T.Ogura         Update          QC#21159
 * 2018/06/26   Fujitsu         T.Ogura         Update          QC#26336
 * 2018/07/23   Hitachi         K.Kim           Update          QC#26831
 * 2018/11/05   Hitachi         K.Fujimoto      Update          QC#28627
 * 2019/08/30   Hitachi         T.Aoyagi        Update          QC#53005
 * 2021/02/03   CITS            Y.Penequito     Update          QC#58312
 * 2021/03/17   CITS            L.Mandanas      Update          QC#58314-1
 * 2021/09/10   CITS            L.Mandanas      Update          QC#58314-1
 * 2022/01/14   CITS            L.Mandanas      Update          QC#58314-4
 * 2022/03/22   Hitachi         D.Yoshida       Update          QC#59683
 * 2022/06/20   Hitachi         T.NEMA          Update          QC#60200
 * 2022/10/13   Hitachi         M.Komatsu       Update          QC#60078,60537
 * 2023/09/12   Hitachi         S.Nakatani      Update          QC#60074
 * 2024/02/07   Hitachi         K.Watanabe      Update          QC#63464
 * 2024/02/26   Hitachi         K.Ogasawara     Update          QC#63550
 *</pre>
 */
public class NSAL0620CommonLogic {

    /**
     * copy To ASMsg for Current Page Info
     * @param cMsg NSAL0620CMsg
     * @param sMsg NSAL0620SMsg
     */
    public static void copyCurrentPageToSMsg(NSAL0620CMsg cMsg, NSAL0620SMsg sMsg) {

        NSAL0620_ACMsgArray acMsgArray = cMsg.A;
        for (int i = 0; i < acMsgArray.getValidCount(); i++) {
            NSAL0620_ACMsg acMsg = (NSAL0620_ACMsg) acMsgArray.no(i);

            // mod start 2016/03/25 CSA Defect#4595
            int targetIdxNum = acMsg.xxRowNum_A.getValueInt();
            NSAL0620_ASMsgArray asMsgArray = sMsg.A;
            for (int j = 0; j < asMsgArray.getValidCount(); j++) {
                NSAL0620_ASMsg asMsg = (NSAL0620_ASMsg) asMsgArray.no(j);
                int cpyIdxNum = asMsg.xxRowNum_A.getValueInt();
                if (targetIdxNum == cpyIdxNum) {
                    ZYPEZDItemValueSetter.setValue(asMsg.xxChkBox_A , acMsg.xxChkBox_A);
                    break;
                }
            }
            // mod end 2016/03/25 CSA Defect#4595
        }
    }

    /**
     * Sets the pagenation.
     * @param xxPageShowOfNum the xx page show of num
     * @param xxPageShowToNum the xx page show to num
     * @param xxPageShowFromNum the xx page show from num
     * @param pageRecs the page records
     * @param totalRecs the total records
     */
    public static void setPagenation(EZDCBigDecimalItem xxPageShowOfNum, EZDCBigDecimalItem xxPageShowToNum, int xxPageShowFromNum, int pageRecs, int totalRecs) {

        if (xxPageShowOfNum == null //
                || xxPageShowToNum == null) {
            return;
        }
        if (pageRecs == 0 || totalRecs == 0) {
            return;
        }

        xxPageShowToNum.setValue(xxPageShowFromNum + pageRecs - 1);
        xxPageShowOfNum.setValue(totalRecs);

    }

    /**
     * createList
     * @param cMsg NSAL0620CMsg
     */
    public static void createList(NSAL0620CMsg cMsg) {
        createSvcRgList(cMsg);
        createSvcContrBrList(cMsg);
        createMtrReadMethList(cMsg);
        createMtrLbList(cMsg);
   }

    /**
     * createPullDown
     * @param cMsg NSAL0620CMsg
     * @param srchOptUsrId String
     */
    public static void createPullDown(NSAL0620CMsg cMsg, String srchOptUsrId) {
        createSavedSearchOptionsPullDown(cMsg, srchOptUsrId);
        createContrCatgPullDown(cMsg);
        createContrCtrlStsPullDown(cMsg);
        createContrClsPullDown(cMsg);
        createSvcMachMstrStsPullDown(cMsg);
        // del start 2016/07/01 CSA Defect#11261
//        createSvcCovTmplTpPullDown(cMsg);
        // del end 2016/07/01 CSA Defect#11261
        createBllgCyclePullDown(cMsg);
        createMassUpdatePullDown(cMsg);
    }

    private static void createContrCatgPullDown(NSAL0620CMsg cMsg) {
        // START 2017/05/25 Y.Osawa [QC#18560, MOD]
//        ZYPCodeDataUtil.createPulldownList(DS_CONTR_CATG.toString(), cMsg.dsContrCatgCd_L, cMsg.dsContrCatgNm_L);
        // START 2018/06/05 T.Ogura [QC#21159,MOD]
//        ZYPCodeDataUtil.createPulldownList(TBL_NM.DS_CONTR_CATG.toString(), cMsg.dsContrCatgCd_L, cMsg.dsContrCatgNm_L);
        ZYPCodeDataUtil.createPulldownList(TBL_NM.DS_CONTR_CATG.toString(), cMsg.dsContrCatgCd_L, cMsg.dsContrCatgDescTxt_L);
        // END   2018/06/05 T.Ogura [QC#21159,MOD]
        // END   2017/05/25 Y.Osawa [QC#18560, MOD]
    }

    private static void createContrCtrlStsPullDown(NSAL0620CMsg cMsg) {
        // START 2017/11/14 K.Kojima [QC#21886,MOD]
        // DS_CONTR_CTRL_STSTMsgArray tMsgAry = NSAL0620Query.getInstance().findContrCtrlStsCdPulldownList(cMsg.glblCmpyCd.getValue());
        // Map<String, String> tMsgKeys = new HashMap<String, String>();
        // tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "dsContrCtrlStsCd");
        // tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "dsContrCtrlStsNm");
        // ZYPPulldownValueSetter.set(tMsgAry, tMsgKeys, cMsg.dsContrCtrlStsCd_L, cMsg.dsContrCtrlStsNm_L);
        S21SsmEZDResult ssmResult = NSAL0620Query.getInstance().getContrCtrlStsCdPulldownList(cMsg.glblCmpyCd.getValue());
        if (!ssmResult.isCodeNormal()) {
            return;
        }
        List<Map<String, Object>> list = (List<Map<String, Object>>) ssmResult.getResultObject();
        for (int i = 0; i < list.size(); i++) {
            ZYPEZDItemValueSetter.setValue(cMsg.dsContrCtrlStsCd_L.no(i), (String) list.get(i).get("DS_CONTR_CTRL_STS_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.dsContrCtrlStsNm_L.no(i), (String) list.get(i).get("DS_CONTR_CTRL_STS_NM"));
        }
        // END 2017/11/14 K.Kojima [QC#21886,MOD]
        // START 2017/10/02 K.Kojima [QC#18417,ADD]
        int addCount = 0;
        // END 2017/10/02 K.Kojima [QC#18417,ADD]
        // add start 2017/09/20 CSA Defect#18534
        String pndIstlContrStsCd = ZYPCodeDataUtil.getVarCharConstValue(PND_ISTL_CONTR_STS_CD, cMsg.glblCmpyCd.getValue());
        String pndIstlContrStsNm = ZYPCodeDataUtil.getVarCharConstValue(PND_ISTL_CONTR_STS_NM, cMsg.glblCmpyCd.getValue());
        if (hasValue(pndIstlContrStsCd) && hasValue(pndIstlContrStsNm)) {
            // START 2017/11/14 K.Kojima [QC#21886,MOD]
            // ZYPEZDItemValueSetter.setValue(cMsg.dsContrCtrlStsCd_L.no(tMsgAry.getValidCount()), pndIstlContrStsCd);
            // ZYPEZDItemValueSetter.setValue(cMsg.dsContrCtrlStsNm_L.no(tMsgAry.getValidCount()), pndIstlContrStsNm);
            ZYPEZDItemValueSetter.setValue(cMsg.dsContrCtrlStsCd_L.no(list.size()), pndIstlContrStsCd);
            ZYPEZDItemValueSetter.setValue(cMsg.dsContrCtrlStsNm_L.no(list.size()), pndIstlContrStsNm);
            // END 2017/11/14 K.Kojima [QC#21886,MOD]
            // START 2017/10/02 K.Kojima [QC#18417,ADD]
            addCount++;
            // END 2017/10/02 K.Kojima [QC#18417,ADD]
        }
        // add end 2017/09/20 CSA Defect#18534
        // START 2017/10/02 K.Kojima [QC#18417,ADD]
        String activePendingPOContrStsCd = ZYPCodeDataUtil.getVarCharConstValue(ACTV_PENDING_PO_CONTR_STS_CD, cMsg.glblCmpyCd.getValue());
        String activePendingPOContrStsNm = ZYPCodeDataUtil.getVarCharConstValue(ACTV_PENDING_PO_CONTR_STS_NM, cMsg.glblCmpyCd.getValue());
        if (hasValue(activePendingPOContrStsCd) && hasValue(activePendingPOContrStsNm)) {
            // START 2017/11/14 K.Kojima [QC#21886,MOD]
            // ZYPEZDItemValueSetter.setValue(cMsg.dsContrCtrlStsCd_L.no(tMsgAry.getValidCount() + addCount), activePendingPOContrStsCd);
            // ZYPEZDItemValueSetter.setValue(cMsg.dsContrCtrlStsNm_L.no(tMsgAry.getValidCount() + addCount), activePendingPOContrStsNm);
            ZYPEZDItemValueSetter.setValue(cMsg.dsContrCtrlStsCd_L.no(list.size() + addCount), activePendingPOContrStsCd);
            ZYPEZDItemValueSetter.setValue(cMsg.dsContrCtrlStsNm_L.no(list.size() + addCount), activePendingPOContrStsNm);
            // END 2017/11/14 K.Kojima [QC#21886,MOD]
            addCount++;
        }
        String activeRenewalHoldContrStsCd = ZYPCodeDataUtil.getVarCharConstValue(ACTV_RENEWAL_HOLD_CONTR_STS_CD, cMsg.glblCmpyCd.getValue());
        String activeRenewalHoldContrStsNm = ZYPCodeDataUtil.getVarCharConstValue(ACTV_RENEWAL_HOLD_CONTR_STS_NM, cMsg.glblCmpyCd.getValue());
        if (hasValue(activeRenewalHoldContrStsCd) && hasValue(activeRenewalHoldContrStsNm)) {
            // START 2017/11/14 K.Kojima [QC#21886,MOD]
            // ZYPEZDItemValueSetter.setValue(cMsg.dsContrCtrlStsCd_L.no(tMsgAry.getValidCount() + addCount), activeRenewalHoldContrStsCd);
            // ZYPEZDItemValueSetter.setValue(cMsg.dsContrCtrlStsNm_L.no(tMsgAry.getValidCount() + addCount), activeRenewalHoldContrStsNm);
            ZYPEZDItemValueSetter.setValue(cMsg.dsContrCtrlStsCd_L.no(list.size() + addCount), activeRenewalHoldContrStsCd);
            ZYPEZDItemValueSetter.setValue(cMsg.dsContrCtrlStsNm_L.no(list.size() + addCount), activeRenewalHoldContrStsNm);
            // END 2017/11/14 K.Kojima [QC#21886,MOD]
            addCount++;
        }
        // END 2017/10/02 K.Kojima [QC#18417,ADD]
    }

    private static void createContrClsPullDown(NSAL0620CMsg cMsg) {
        // START 2017/05/25 Y.Osawa [QC#18560, MOD]
//        ZYPCodeDataUtil.createPulldownList(DS_CONTR_CLS.toString(), cMsg.dsContrClsCd_L, cMsg.dsContrClsNm_L);
        ZYPCodeDataUtil.createPulldownList(TBL_NM.DS_CONTR_CLS.toString(), cMsg.dsContrClsCd_L, cMsg.dsContrClsNm_L);
        // END   2017/05/25 Y.Osawa [QC#18560, MOD]
    }

    private static void createSvcMachMstrStsPullDown(NSAL0620CMsg cMsg) {
        // START 2017/05/25 Y.Osawa [QC#18560, MOD]
//        ZYPCodeDataUtil.createPulldownList(SVC_MACH_MSTR_STS.toString(), cMsg.svcMachMstrStsCd_L, cMsg.svcMachMstrStsNm_L);
        ZYPCodeDataUtil.createPulldownList(TBL_NM.SVC_MACH_MSTR_STS.toString(), cMsg.svcMachMstrStsCd_L, cMsg.svcMachMstrStsNm_L);
        // END   2017/05/25 Y.Osawa [QC#18560, MOD]
    }

    private static void createBllgCyclePullDown(NSAL0620CMsg cMsg) {
        // START 2017/05/25 Y.Osawa [QC#18560, MOD]
//        ZYPCodeDataUtil.createPulldownList(BLLG_CYCLE.toString(), cMsg.bllgCycleCd_LB, cMsg.bllgCycleNm_LB);
//        ZYPCodeDataUtil.createPulldownList(BLLG_CYCLE.toString(), cMsg.bllgCycleCd_LM, cMsg.bllgCycleNm_LM);
        ZYPCodeDataUtil.createPulldownList(TBL_NM.BLLG_CYCLE.toString(), cMsg.bllgCycleCd_LB, cMsg.bllgCycleNm_LB);
        deletePulldownList(cMsg.bllgCycleCd_LB, cMsg.bllgCycleNm_LB, BLLG_CYCLE.DAILY);
        ZYPCodeDataUtil.createPulldownList(TBL_NM.BLLG_CYCLE.toString(), cMsg.bllgCycleCd_LM, cMsg.bllgCycleNm_LM);
        deletePulldownList(cMsg.bllgCycleCd_LM, cMsg.bllgCycleNm_LM, BLLG_CYCLE.DAILY);
        // END   2017/05/25 Y.Osawa [QC#18560, MOD]
    }

    private static void createMassUpdatePullDown(NSAL0620CMsg cMsg) {
        // mod start 2016/08/02 CSA Defect#4433
        // START 2017/02/07 K.Kojima [QC#17303,ADD]
        setMassUpdatePullDown(cMsg, MASS_UPDATE_LIST.ZYPL0210.getScrnId(), MASS_UPDATE_LIST.ZYPL0210.getScrnNm());
        // END 2017/02/07 K.Kojima [QC#17303,ADD]
        setMassUpdatePullDown(cMsg, MASS_UPDATE_LIST.NSAL0380.getScrnId(), MASS_UPDATE_LIST.NSAL0380.getScrnNm());
        setMassUpdatePullDown(cMsg, MASS_UPDATE_LIST.NSAL0740.getScrnId(), MASS_UPDATE_LIST.NSAL0740.getScrnNm());
        setMassUpdatePullDown(cMsg, MASS_UPDATE_LIST.NSAL0720.getScrnId(), MASS_UPDATE_LIST.NSAL0720.getScrnNm());
        setMassUpdatePullDown(cMsg, MASS_UPDATE_LIST.NSAL0750.getScrnId(), MASS_UPDATE_LIST.NSAL0750.getScrnNm());
        setMassUpdatePullDown(cMsg, MASS_UPDATE_LIST.NSAL0660.getScrnId(), MASS_UPDATE_LIST.NSAL0660.getScrnNm());
        setMassUpdatePullDown(cMsg, MASS_UPDATE_LIST.NSAL0650.getScrnId(), MASS_UPDATE_LIST.NSAL0650.getScrnNm());
        setMassUpdatePullDown(cMsg, MASS_UPDATE_LIST.NSAL0390.getScrnId(), MASS_UPDATE_LIST.NSAL0390.getScrnNm());
        setMassUpdatePullDown(cMsg, MASS_UPDATE_LIST.NSAL0730.getScrnId(), MASS_UPDATE_LIST.NSAL0730.getScrnNm());
        setMassUpdatePullDown(cMsg, MASS_UPDATE_LIST.NSAL0640.getScrnId(), MASS_UPDATE_LIST.NSAL0640.getScrnNm());
        setMassUpdatePullDown(cMsg, MASS_UPDATE_LIST.NSAL0700.getScrnId(), MASS_UPDATE_LIST.NSAL0700.getScrnNm());
        setMassUpdatePullDown(cMsg, MASS_UPDATE_LIST.NSAL0630.getScrnId(), MASS_UPDATE_LIST.NSAL0630.getScrnNm());
        setMassUpdatePullDown(cMsg, MASS_UPDATE_LIST.NSAL0670.getScrnId(), MASS_UPDATE_LIST.NSAL0670.getScrnNm());
        setMassUpdatePullDown(cMsg, MASS_UPDATE_LIST.NSAL0690.getScrnId(), MASS_UPDATE_LIST.NSAL0690.getScrnNm());
        setMassUpdatePullDown(cMsg, MASS_UPDATE_LIST.NSAL0400.getScrnId(), MASS_UPDATE_LIST.NSAL0400.getScrnNm());
        setMassUpdatePullDown(cMsg, MASS_UPDATE_LIST.NSAL0460.getScrnId(), MASS_UPDATE_LIST.NSAL0460.getScrnNm());
        setMassUpdatePullDown(cMsg, MASS_UPDATE_LIST.NSAL0710.getScrnId(), MASS_UPDATE_LIST.NSAL0710.getScrnNm());
        // mod end 2016/08/02 CSA Defect#4433
    }

    // add start 2016/08/02 CSA Defect#4433
    private static void setMassUpdatePullDown(NSAL0620CMsg cMsg, String scrnId, String scrnNm) {
        List<String> functionList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(scrnId);
        if (functionList != null && !functionList.isEmpty()) {
            ZYPPulldownValueSetter.insertFirstData(scrnId, cMsg.xxGenlFldAreaTxt_LC, scrnNm, cMsg.xxGenlFldAreaTxt_LN);
        }
    }
    // add end 2016/08/02 CSA Defect#4433

    private static void createSavedSearchOptionsPullDown(NSAL0620CMsg cMsg, String srchOptUsrId) {
        S21SsmEZDResult ssmResult = NSAL0620Query.getInstance().getSavedSearchOptionList(srchOptUsrId);
        if (!ssmResult.isCodeNormal()) {
            cMsg.srchOptPk_L.clear();
            cMsg.srchOptNm_L.clear();
            return;
        }

        cMsg.srchOptPk_L.clear();
        cMsg.srchOptNm_L.clear();
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        for (int i = 0; i < resultList.size() && i < cMsg.srchOptPk_L.length(); i++) {
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
            cMsg.srchOptPk_L.no(i).setValue((BigDecimal) resultMap.get(SRCH_OPT_PK.toString()));
            cMsg.srchOptNm_L.no(i).setValue((String) resultMap.get(SRCH_OPT_NM.toString()));
        }

    }

    private static void createSvcRgList(NSAL0620CMsg cMsg) {
        NSAL0620Query query = NSAL0620Query.getInstance();
        SVC_RGTMsgArray tMsgList = query.getSvcRgList(cMsg.glblCmpyCd.getValue());
        if (tMsgList.getValidCount() > LIST_MAX_CNT) {
            tMsgList.setValidCount(LIST_MAX_CNT);
        }
        for (int i = 0; i < tMsgList.getValidCount(); i++) {
            SVC_RGTMsg tMsg = (SVC_RGTMsg) tMsgList.get(i);
            ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxChkBox_B, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).svcRgNm_B, tMsg.svcRgNm);
            ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).svcRgDescTxt_B, tMsg.svcRgDescTxt);
        }
        cMsg.B.setValidCount(tMsgList.getValidCount());
    }

    private static void createSvcContrBrList(NSAL0620CMsg cMsg) {
        NSAL0620Query query = NSAL0620Query.getInstance();
        SVC_CONTR_BRTMsgArray tMsgList = query.getSvcContrBrList(cMsg.glblCmpyCd.getValue());
        // START 2018/01/16 K.Kojima [QC#23352,MOD]
        // if (tMsgList.getValidCount() > LIST_MAX_CNT) {
        //     tMsgList.setValidCount(LIST_MAX_CNT);
        // }
        if (tMsgList.getValidCount() > LIST_MAX_CNT_BR_LIST) {
            tMsgList.setValidCount(LIST_MAX_CNT_BR_LIST);
        }
        // END 2018/01/16 K.Kojima [QC#23352,MOD]
        for (int i = 0; i < tMsgList.getValidCount(); i++) {
            SVC_CONTR_BRTMsg tMsg = (SVC_CONTR_BRTMsg) tMsgList.get(i);
            ZYPEZDItemValueSetter.setValue(cMsg.C.no(i).xxChkBox_C, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(cMsg.C.no(i).svcContrBrCd_C, tMsg.svcContrBrCd);
            ZYPEZDItemValueSetter.setValue(cMsg.C.no(i).svcContrBrDescTxt_C, tMsg.svcContrBrDescTxt);
        }
        cMsg.C.setValidCount(tMsgList.getValidCount());
    }

    private static void createMtrReadMethList(NSAL0620CMsg cMsg) {
        NSAL0620Query query = NSAL0620Query.getInstance();
        MTR_READ_METHTMsgArray tMsgList = query.getMtrReadMethList(cMsg.glblCmpyCd.getValue());
        if (tMsgList.getValidCount() > LIST_MAX_CNT) {
            tMsgList.setValidCount(LIST_MAX_CNT);
        }
        for (int i = 0; i < tMsgList.getValidCount(); i++) {
            MTR_READ_METHTMsg tMsg = (MTR_READ_METHTMsg) tMsgList.get(i);
            ZYPEZDItemValueSetter.setValue(cMsg.D.no(i).xxChkBox_D, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(cMsg.D.no(i).mtrReadMethCd_D, tMsg.mtrReadMethCd);
            ZYPEZDItemValueSetter.setValue(cMsg.D.no(i).mtrReadMethNm_D, tMsg.mtrReadMethNm);
        }
        cMsg.D.setValidCount(tMsgList.getValidCount());
    }

    /**
     * check Select
     * @param cMsg NSAL0620CMsg
     * @param sMsg NSAL0620SMsg
     * @return boolean
     */
    public static boolean checkSelect(NSAL0620CMsg cMsg, NSAL0620SMsg sMsg) {
        boolean rtnVal = true;
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.A, XX_CHK_BOX_A, ZYPConstant.CHKBOX_ON_Y);
        if (selectedRows.isEmpty()) {
            cMsg.setMessageInfo(MSG_ID.NSAM0015E.toString());
            rtnVal = false;
        }
        return rtnVal;
    }

    /**
     * check Only One Select
     * @param cMsg NSAL0620CMsg
     * @param sMsg NSAL0620SMsg
     * @return boolean
     */
    public static boolean checkOnlyOneSelect(NSAL0620CMsg cMsg, NSAL0620SMsg sMsg) {
        boolean rtnVal = true;
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.A, XX_CHK_BOX_A, ZYPConstant.CHKBOX_ON_Y);
        if (selectedRows.size() != 1) {
            cMsg.setMessageInfo(MSG_ID.NSAM0132E.toString());
            rtnVal = false;
        }
        return rtnVal;
    }

    private static void createMtrLbList(NSAL0620CMsg cMsg) {
        NSAL0620Query query = NSAL0620Query.getInstance();
        MTR_LBTMsgArray tMsgList = query.getMtrLbList(cMsg.glblCmpyCd.getValue());
        // START 2017/08/02 K.Kojima [QC#19906,MOD]
        // if (tMsgList.getValidCount() > LIST_MAX_CNT) {
        //     tMsgList.setValidCount(LIST_MAX_CNT);
        // }
        if (tMsgList.getValidCount() > LIST_MAX_CNT_BLLG_MTR) {
            tMsgList.setValidCount(LIST_MAX_CNT_BLLG_MTR);
        }
        // END 2017/08/02 K.Kojima [QC#19906,MOD]
        for (int i = 0; i < tMsgList.getValidCount(); i++) {
            MTR_LBTMsg tMsg = (MTR_LBTMsg) tMsgList.get(i);
            ZYPEZDItemValueSetter.setValue(cMsg.E.no(i).xxChkBox_E, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(cMsg.E.no(i).mtrLbCd_E, tMsg.mtrLbCd);
            ZYPEZDItemValueSetter.setValue(cMsg.E.no(i).mtrLbNm_E, tMsg.mtrLbNm);
        }
        cMsg.E.setValidCount(tMsgList.getValidCount());
    }

    /**
     * getSearchData
     * @param cMsg NSAL0620CMsg
     * @param sMsg NSAL0620SMsg
     */
    public static void getSearchData(NSAL0620CMsg cMsg, NSAL0620SMsg sMsg) {
        // START 2018/03/23 K.Kojima [QC#22722,ADD]
        outputSearchLog(cMsg);
        // END 2018/03/23 K.Kojima [QC#22722,ADD]
        S21SsmEZDResult ssmResult = NSAL0620Query.getInstance().getSearchData(cMsg, sMsg);
        if (ssmResult.isCodeNormal()) {
            // Result > 200
            int queryResCnt = ssmResult.getQueryResultCount();
            // START 2024/02/07 K.Watanabe [QC#63464,ADD]
            NSAL0620_ASMsgArray resultList =  (NSAL0620_ASMsgArray) ssmResult.getResultObject();
            BigDecimal srchCnt = resultList.no(0).xxSrchCnt_A.getValue();
            // END 2024/02/07 K.Watanabe [QC#63464,ADD]
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(MSG_ID.NZZM0001W.toString(), new String[]{Integer.toString(sMsg.A.length())});
                queryResCnt = sMsg.A.length();
            } else {
                cMsg.setMessageInfo(MSG_ID.NSAM0200I.toString());
            }

            // Copy one page from SMsg to CMsg
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            // Set page#
            // START 2024/02/07 K.Watanabe [QC#63464,ADD]
            cMsg.xxSrchCnt.setValue(srchCnt);
            // END 2024/02/07 K.Watanabe [QC#63464,ADD]
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(queryResCnt);

        } else {
            // No result
            cMsg.setMessageInfo(MSG_ID.NZZM0000E.toString());
            // START 2024/02/07 K.Watanabe [QC#63464,ADD]
            NSAL0620CommonLogic.initSetToSrchCntZero(cMsg.xxSrchCnt);
            // END 2024/02/07 K.Watanabe [QC#63464,ADD]
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
        }
    }

    /**
     * copySMsgTocMsg
     * @param cMsg NSAL0620CMsg
     * @param sMsg NSAL0620SMsg
     */
    public static void copyGlblMsgToBizMsg(NSAL0620CMsg cMsg, NSAL0620SMsg sMsg) {
        int ixS = (cMsg.xxPageShowFromNum.getValueInt() - 1);
        int i = 0;
        for (; i < cMsg.A.length() && ixS < sMsg.A.getValidCount(); i++) {
            EZDMsg.copy(sMsg.A.no(ixS++), null, cMsg.A.no(i), null);
        }
        cMsg.A.setValidCount(i);
    }

    /**
     * preSetToPageOne
     * @param xxPageShowFromNum EZDCBigDecimalItem
     */
    public static void preSetToPageOne(EZDCBigDecimalItem xxPageShowFromNum) {
        if (xxPageShowFromNum == null) {
            return;
        }
        xxPageShowFromNum.setValue(BigDecimal.ONE);
    }

    // START 2024/02/07 K.Watanabe [QC#63464,ADD]
    /**
     * initSetToSrchCntZero
     * @param xxSrchCnt EZDCBigDecimalItem
     */
    public static void initSetToSrchCntZero(EZDCBigDecimalItem xxSrchCnt) {
        if (xxSrchCnt == null) {
            return;
        }
        xxSrchCnt.setValue(BigDecimal.ZERO);
    }
    // END 2024/02/07 K.Watanabe [QC#63464,ADD]

    /**
     * writeCsvFile
     * @param cMsg NSAL0620CMsg
     * @param rs ResultSet
     * @throws SQLException SQLException
     */
    public static void writeCsvFile(NSAL0620CMsg cMsg, ResultSet rs) throws SQLException {

        NSAL0620F00FMsg fMsg = new NSAL0620F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(cMsg));
        // Set don't display column
        fMsg.addExclusionItem("xxChkBox_A");
        fMsg.addExclusionItem("dsContrPk_A");

        //write header
        writeCsvFileHeader(csvOutFile, fMsg, cMsg);

        if (!rs.next()) {
            cMsg.setMessageInfo(MSG_ID.NZZM0000E.toString(), null);
            csvOutFile.close();
            return;
        }

        //write contents
        do {
            if (rs.getRow() > LIMIT_DL_ROWNUM) {
                cMsg.setMessageInfo(MSG_ID.NZZM0001W.toString(), null);
                break;
            }
            //resultSet -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.dsContrNum_A, rs.getString(COL_NM.DS_CONTR_NUM.toString()));
            // START 2018/06/05 T.Ogura [QC#21159,MOD]
//            ZYPEZDItemValueSetter.setValue(fMsg.dsContrCatgNm_A, rs.getString(COL_NM.DS_CONTR_CATG_NM.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.dsContrCatgDescTxt_A, rs.getString(COL_NM.DS_CONTR_CATG_DESC_TXT.toString()));
            // END   2018/06/05 T.Ogura [QC#21159,MOD]
            ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNm_A, rs.getString(COL_NM.DS_ACCT_NM.toString()));
            // START 2016/10/28 T.Tomita [QC#15468, MOD]
//            ZYPEZDItemValueSetter.setValue(fMsg.contrVrsnEffFromDt_A, rs.getString(COL_NM.CONTR_VRSN_EFF_FROM_DT.toString()));
//            ZYPEZDItemValueSetter.setValue(fMsg.contrVrsnEffThruDt_A, rs.getString(COL_NM.CONTR_VRSN_EFF_THRU_DT.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.contrEffFromDtTxt_VR, convertDateFormat(rs.getString(COL_NM.CONTR_VRSN_EFF_FROM_DT.toString())));
            ZYPEZDItemValueSetter.setValue(fMsg.contrEffThruDtTxt_VR, convertDateFormat(rs.getString(COL_NM.CONTR_VRSN_EFF_THRU_DT.toString())));
            // END 2016/10/28 T.Tomita [QC#15468, MOD]
            ZYPEZDItemValueSetter.setValue(fMsg.dsContrCtrlStsNm_A, rs.getString(COL_NM.DS_CONTR_CTRL_STS_NM.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.svcRgNm_A, rs.getString(COL_NM.SVC_RG_NM.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.svcContrBrCd_A, rs.getString(COL_NM.SVC_CONTR_BR_CD.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.serNum_A, rs.getString(COL_NM.SER_NUM.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.t_MdlNm_A, rs.getString(COL_NM.T_MDL_NM.toString()));
            // START 2016/10/28 T.Tomita [QC#15468, MOD]
//            ZYPEZDItemValueSetter.setValue(fMsg.contrEffFromDt_A, rs.getString(COL_NM.CONTR_EFF_FROM_DT.toString()));
//            ZYPEZDItemValueSetter.setValue(fMsg.contrEffThruDt_A, rs.getString(COL_NM.CONTR_EFF_THRU_DT.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.contrEffFromDtTxt_A, convertDateFormat(rs.getString(COL_NM.CONTR_EFF_FROM_DT.toString())));
            ZYPEZDItemValueSetter.setValue(fMsg.contrEffThruDtTxt_A, convertDateFormat(rs.getString(COL_NM.CONTR_EFF_THRU_DT.toString())));
            // END 2016/10/28 T.Tomita [QC#15468, MOD]
            ZYPEZDItemValueSetter.setValue(fMsg.svcMachMstrStsNm_A, rs.getString(COL_NM.SVC_MACH_MSTR_STS_NM.toString()));
            // START 2016/10/28 T.Tomita [QC#15468, MOD]
//            ZYPEZDItemValueSetter.setValue(fMsg.nextBllgDt_A, rs.getString(COL_NM.NEXT_BLLG_DT.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_A, convertDateFormat(convertDateFormat(rs.getString(COL_NM.NEXT_BLLG_DT.toString()))));
            // END 2016/10/28 T.Tomita [QC#15468, MOD]
            
            // START 2018/07/18 T.Wada [QC#26424, ADD]
            ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNum_A, rs.getString(COL_NM.DS_ACCT_NUM.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.altPayerCustCd_A, rs.getString(COL_NM.BILL_TO_CUST_CD.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.billToLocNm_A, rs.getString(COL_NM.BILL_TO_LOC_NM.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.dsContrStsDescTxt_A, rs.getString(COL_NM.DS_CONTR_CTRL_STS_NM_H.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.dsContrClsDescTxt_A, rs.getString(COL_NM.DS_CONTR_CLS_DESC_TXT.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.xxPsnNm_BR, rs.getString(COL_NM.BR_PSN_NM.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.tocNm_A, rs.getString(COL_NM.TOC_NM.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.svcContrRefCmntTxt_A, rs.getString(COL_NM.SVC_CONTR_REF_CMNT_TXT.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.dsContrRptGrpDescTxt_A, rs.getString(COL_NM.DS_CONTR_RPT_GRP_DESC_TXT.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.dsContrNm_A, rs.getString(COL_NM.DS_CONTR_NM.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.contrInvCmntTxt_A, rs.getString(COL_NM.CONTR_INV_CMNT_TXT.toString()));
            // START 2022/06/20 [QC#60200, MOD]
            // START 2022/03/22 [QC#59683, MOD]
//            ZYPEZDItemValueSetter.setValue(fMsg.dsBillTgtrFlg_A, rs.getString(COL_NM.DS_BILL_TGTR_FLG.toString()));
//            ZYPEZDItemValueSetter.setValue(fMsg.dsInvTgtrTpDescTxt_A, rs.getString(COL_NM.DS_BILL_TGTR_FLG.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.dsInvTgtrTpDescTxt_A, rs.getString(COL_NM.DS_INV_TGTR_TP_DESC_TXT.toString()));
            // END   2022/03/22 [QC#59683, MOD]
            // END   2022/06/20 [QC#60200, MOD]
            ZYPEZDItemValueSetter.setValue(fMsg.prcAllocByMachQtyFlg_A, rs.getString(COL_NM.PRC_ALLOC_BY_MACH_QTY_FLG.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.mtrEstTpDescTxt_A, rs.getString(COL_NM.MTR_EST_TP_DESC_TXT.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNm_LS, rs.getString(COL_NM.DS_ACCT_NM_LS.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.baseChrgToLeaseCmpyFlg_A, rs.getString(COL_NM.BASE_CHRG_TO_LEASE_CMPY_FLG.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.usgChrgToLeaseCmpyFlg_A, rs.getString(COL_NM.USG_CHRG_TO_LEASE_CMPY_FLG.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.cfsLeaseNumCmntTxt_A, rs.getString(COL_NM.CFS_LEASE_NUM_CMNT_TXT.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.custPoNum_A, rs.getString(COL_NM.CUST_PO_NUM.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_PO, convertDateFormat(rs.getString(COL_NM.PO_DT.toString())));
            ZYPEZDItemValueSetter.setValue(fMsg.crCardCustRefNum_A, rs.getString(COL_NM.CR_CARD_CUST_REF_NUM.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_CR, convertDateFormat2(rs.getString(COL_NM.CR_CARD_EXPR_YR_MTH.toString())));
            ZYPEZDItemValueSetter.setValue(fMsg.pmtTermCashDiscDescTxt_A, rs.getString(COL_NM.PMT_TERM_CASH_DISC_DESC_TXT.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.contrAutoRnwTpDescTxt_A, rs.getString(COL_NM.CONTR_AUTO_RNW_TP_DESC_TXT.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.rnwPrcMethDescTxt_A, rs.getString(COL_NM.RNW_PRC_METH_DESC_TXT.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.basePrcUpRatio_A, rs.getBigDecimal(COL_NM.BASE_PRC_UP_RATIO.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.mtrPrcUpRatio_A, rs.getBigDecimal(COL_NM.MTR_PRC_UP_RATIO.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.befEndRnwDaysAot_A, rs.getBigDecimal(COL_NM.BEF_END_RNW_DAYS_AOT.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.contrUplftTpDescTxt_A, rs.getString(COL_NM.CONTR_UPLFT_TP_DESC_TXT.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.uplftPrcMethDescTxt_A, rs.getString(COL_NM.UPLFT_PRC_METH_DESC_TXT.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.uplftBasePrcUpRatio_A, rs.getBigDecimal(COL_NM.UPLFT_BASE_PRC_UP_RATIO.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.uplftMtrPrcUpRatio_A, rs.getBigDecimal(COL_NM.UPLFT_MTR_PRC_UP_RATIO.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.uplftBefEndRnwDaysAot_A, rs.getBigDecimal(COL_NM.UPLFT_BEF_END_RNW_DAYS_AOT.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.svcMachTpDescTxt_A, rs.getString(COL_NM.SVC_MACH_TP_DESC_TXT.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.svcMachMstrPk_IB, rs.getBigDecimal(COL_NM.SVC_MACH_MSTR_PK.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.svcConfigMstrPk_A, rs.getBigDecimal(COL_NM.SVC_CONFIG_MSTR_PK.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.mdseCd_A, rs.getString(COL_NM.MDSE_CD.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.mdseDescShortTxt_A, rs.getString(COL_NM.MDSE_DESC_SHORT_TXT.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.shipToCustCd_A, rs.getString(COL_NM.SHIP_TO_CUST_CD.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.shipToLocNm_A, rs.getString(COL_NM.SHIP_TO_LOC_NM.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.shipToCustLocAddr_A, rs.getString(COL_NM.SHIP_TO_CUST_LOC_ADDR.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.svcBrCdDescTxt_A, rs.getString(COL_NM.SVC_BR_CD_DESC_TXT.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.mdseDescShortTxt_SA, rs.getString(COL_NM.MDSE_DESC_SHORT_TXT_SA.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.mtrReadMethDescTxt_A, rs.getString(COL_NM.MTR_READ_METH_DESC_TXT.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_CL, convertDateFormat(rs.getString(COL_NM.CONTR_CLO_DT.toString())));
            ZYPEZDItemValueSetter.setValue(fMsg.svcMemoRsnDescTxt_A, rs.getString(COL_NM.SVC_MEMO_RSN_DESC_TXT.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_RN, convertDateFormat(rs.getString(COL_NM.RNW_EFF_FROM_DT.toString())));
            ZYPEZDItemValueSetter.setValue(fMsg.contrRnwTotCnt_A, rs.getBigDecimal(COL_NM.CONTR_RNW_TOT_CNT.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.bllgMtrBillToCustCd_A, rs.getString(COL_NM.BLLG_MTR_BILL_TO_CUST_CD.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.billToLocNm_BS, rs.getString(COL_NM.BILL_TO_LOC_NM_BS.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.billToCustLocAddr_A, rs.getString(COL_NM.BILL_TO_CUST_LOC_ADDR.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.xxPsnNm_BS, rs.getString(COL_NM.BS_PSN_NM.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.bllgCycleDescTxt_A, rs.getString(COL_NM.BLLG_CYCLE_DESC_TXT.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.basePrcDealAmt_A, rs.getBigDecimal(COL_NM.BASE_PRC_DEAL_AMT.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.basePrcTermDealAmtRate_A, rs.getBigDecimal(COL_NM.BASE_PRC_TERM_DEAL_AMT_RATE.toString()));
            ZYPEZDItemValueSetter.setValue(fMsg.bllgTmgTpDescTxt_A, rs.getString(COL_NM.BLLG_TMG_TP_DESC_TXT.toString()));
            // START 2018/11/05 K.Fujimoto [QC#28627, ADD]
            ZYPEZDItemValueSetter.setValue(fMsg.contrLinkNum, rs.getString(COL_NM.CONTR_LINK_NUM.toString()));
            // END   2018/11/05 K.Fujimoto [QC#28627, ADD]

            String contrCloDay = rs.getString(COL_NM.CONTR_CLO_DAY.toString());
            if (ZYPCommonFunc.hasValue(contrCloDay) && business.blap.NSAL0620.constant.NSAL0620Constant.LAST_DAY_OF_A_MONTH.equals(contrCloDay)) {
                ZYPEZDItemValueSetter.setValue(fMsg.xxCntnrTxt_CD, LAST_DAY);
            } else {
                ZYPEZDItemValueSetter.setValue(fMsg.xxCntnrTxt_CD, contrCloDay);
            }

            String contrBllgDay = rs.getString(COL_NM.CONTR_BLLG_DAY.toString());
            if (ZYPCommonFunc.hasValue(contrBllgDay) && business.blap.NSAL0620.constant.NSAL0620Constant.LAST_DAY_OF_A_MONTH.equals(contrBllgDay)) {
                ZYPEZDItemValueSetter.setValue(fMsg.xxCntnrTxt_BD, LAST_DAY);
            } else {
                ZYPEZDItemValueSetter.setValue(fMsg.xxCntnrTxt_BD, contrBllgDay);
            }
            // END 2018/07/18 T.Wada [QC#26424, ADD]

            csvOutFile.write();
        } while (rs.next());

        csvOutFile.close();
    }

    private static void writeCsvFileHeader(ZYPCSVOutFile csvOutFile, NSAL0620F00FMsg fMsg, NSAL0620CMsg cMsg) {
        EZDI18NLabelConv labelConv = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();
        final String[] csvHeader = new String[] {
                labelConv.convLabel2i18nLabel(SCRN_ID, "dummy1")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "dummy2")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Contract #")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Contract Type")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Customer#")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Customer Name")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Bill To Code")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Bill To Customer Name")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Start Date")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "End Date")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Region")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Branch")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Header Status")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Category")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Branch Rep")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Sales Rep")
                // START 2022/06/20 T.NEMA [QC#60200, MOD]
//                , labelConv.convLabel2i18nLabel(SCRN_ID, "Sorce Ref#")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Source Ref#")
                // END   2022/06/20 T.NEMA [QC#60200, MOD]
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Report Group")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Description")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Inv Comments")
                // START 2022/06/20 T.NEMA [QC#60200, MOD]
//                , labelConv.convLabel2i18nLabel(SCRN_ID, "Bill All Base & Usage Together")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Invoicing Option")
                // END   2022/06/20 T.NEMA [QC#60200, MOD]
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Allocate Across All Machine")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Auto Estimation")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Lease Company")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Base Lease Flag")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Usage Lease Flag")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Lease#")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "PO#")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "PO Expiration Date")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Credit Card#")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "CC Expiration Date")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Payment Term")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Renewal Type")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Renewal Method")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "% Base")
                // START 2022/06/20 T.NEMA [QC#60200, MOD]
//                , labelConv.convLabel2i18nLabel(SCRN_ID, "% Overrage")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "% Overage")
                // END   2022/06/20 T.NEMA [QC#60200, MOD]
                , labelConv.convLabel2i18nLabel(SCRN_ID, "# of Days Before")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Uplift Type")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Uplift Method")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "% Base")
                // START 2022/06/20 T.NEMA [QC#60200, MOD]
//                , labelConv.convLabel2i18nLabel(SCRN_ID, "% Overrage")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "% Overage")
                // END 2022/06/20 T.NEMA [QC#60200, MOD]
                , labelConv.convLabel2i18nLabel(SCRN_ID, "# of Days Before")
                // START 2018/11/05 K.Fujimoto [QC#28627, ADD]
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Contract Link #")
                // END   2018/11/05 K.Fujimoto [QC#28627, ADD]
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Status")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Serial #")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Model #")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Machine Start Date")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Machine End Date")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Machine Status")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Schedule Date")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "BaseUnit_Accessory")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "IB ID")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Config#")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Item Code")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Item Name")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Ship To Code")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Ship To Customer Name")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Ship To Customer Address")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Svc Branch")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Service Program")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Read Method")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Date Terminated")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Termination Reason")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Renewal Date")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Times Renewed")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Base Bill To Code")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Base Bill To Customer Name")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Base Bill To Address")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Base Bill To Contact")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Base Frequency")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Base Charge")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Term Amount")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Billing Timing")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Period End Date")
                , labelConv.convLabel2i18nLabel(SCRN_ID, "Invoice Date")

        };
        csvOutFile.writeHeader(csvHeader, fMsg, ZYPGUITableColumn.getColOrder(cMsg));
    }

    /**
     * callNszc0330forSearchOption
     * @param cMsg NSAL0620CMsg
     * @param sMsg NSAL0620SMsg
     * @param srchOptUsrId String
     */
    public static void callNszc0330forSearchOption(NSAL0620CMsg cMsg, NSAL0620SMsg sMsg, String srchOptUsrId) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, srchOptUsrId);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, APL_ID);

        if (!callNszc0330(cMsg, pMsg)) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(cMsg.dsContrNum_H, pMsg.srchOptTxt_01.getValue());
        setSrchOptForDate(cMsg.dsContrCratDt_H1, pMsg.srchOptTxt_02.getValue());
        setSrchOptForDate(cMsg.dsContrCratDt_H2, pMsg.srchOptTxt_03.getValue());
        // START 2022/10/13 M.Komatsu [QC#60078,MOD]
        // ZYPEZDItemValueSetter.setValue(cMsg.dsContrCatgCd_H, pMsg.srchOptTxt_04.getValue());
        // ZYPEZDItemValueSetter.setValue(cMsg.dsContrCtrlStsCd_H, pMsg.srchOptTxt_05.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.xxComnScrColValTxt_H1, pMsg.srchOptTxt_04.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.xxComnScrColValTxt_H2, pMsg.srchOptTxt_05.getValue());
        // END 2022/10/13 M.Komatsu [QC#60078,MOD]
        ZYPEZDItemValueSetter.setValue(cMsg.invSeptBaseUsgFlg_H, pMsg.srchOptTxt_06.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_H1, pMsg.srchOptTxt_07.getValue());
        // mod start 2016/04/25 CSA Defect#4326
        ZYPEZDItemValueSetter.setValue(cMsg.dsContrRptGrpNum_H, pMsg.srchOptTxt_08.getValue());
        // mod end 2016/04/25 CSA Defect#4326
        ZYPEZDItemValueSetter.setValue(cMsg.svcContrRefCmntTxt_H, pMsg.srchOptTxt_09.getValue());
        // START 2022/10/13 M.Komatsu [QC#60078,MOD]
        // ZYPEZDItemValueSetter.setValue(cMsg.dsContrClsCd_H, pMsg.srchOptTxt_10.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.xxComnScrColValTxt_H3, pMsg.srchOptTxt_10.getValue());
        // END 2022/10/13 M.Komatsu [QC#60078,MOD]
        String[] svcRgNm = null;
        if (hasValue(pMsg.srchOptTxt_11)) {
            svcRgNm = pMsg.srchOptTxt_11.getValue().split(STR_COMMA);
        }
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxChkBox_B, ZYPConstant.FLG_OFF_N);
            if (svcRgNm != null) {
                for (int j = 0; j < svcRgNm.length; j++) {
                    if (svcRgNm[j].equals(cMsg.B.no(i).svcRgNm_B.getValue())) {
                        ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxChkBox_B, ZYPConstant.CHKBOX_ON_Y);
                        break;
                    }
                }
            }
        }

        String[] svcContrBrCd = null;
        if (hasValue(pMsg.srchOptTxt_12)) {
            svcContrBrCd = pMsg.srchOptTxt_12.getValue().split(STR_COMMA);
        }
        for (int i = 0; i < cMsg.C.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(cMsg.C.no(i).xxChkBox_C, ZYPConstant.FLG_OFF_N);
            if (svcContrBrCd != null) {
                for (int j = 0; j < svcContrBrCd.length; j++) {
                    if (svcContrBrCd[j].equals(cMsg.C.no(i).svcContrBrCd_C.getValue())) {
                        ZYPEZDItemValueSetter.setValue(cMsg.C.no(i).xxChkBox_C, ZYPConstant.CHKBOX_ON_Y);
                        break;
                    }
                }
            }
        }

        ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm_H, pMsg.srchOptTxt_13.getValue()); // Mdse Cd
        ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNum_H, pMsg.srchOptTxt_14.getValue()); // Supply Mdse Cd
        ZYPEZDItemValueSetter.setValue(cMsg.xxGenlFldAreaTxt_H1, pMsg.srchOptTxt_15.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.billToCustCd_H1, pMsg.srchOptTxt_16.getValue());
        // mod start 2016/07/01 CSA Defect#11261
//        ZYPEZDItemValueSetter.setValue(cMsg.svcPgmMdseCd_H, pMsg.srchOptTxt_17.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.mdseDescShortTxt_H, pMsg.srchOptTxt_17.getValue());
        // mod end 2016/07/01 CSA Defect#11261
        ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm_H3, pMsg.srchOptTxt_18.getValue());

        String[] mtrReadMethCd = null;
        if (hasValue(pMsg.srchOptTxt_19)) {
            mtrReadMethCd = pMsg.srchOptTxt_19.getValue().split(STR_COMMA);
        }
        for (int i = 0; i < cMsg.D.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(cMsg.D.no(i).xxChkBox_D, ZYPConstant.FLG_OFF_N);
            if (mtrReadMethCd != null) {
                for (int j = 0; j < mtrReadMethCd.length; j++) {
                    if (mtrReadMethCd[j].equals(cMsg.D.no(i).mtrReadMethCd_D.getValue())) {
                        ZYPEZDItemValueSetter.setValue(cMsg.D.no(i).xxChkBox_D, ZYPConstant.CHKBOX_ON_Y);
                        break;
                    }
                }
            }
        }

        String[] mtrLbCd = null;
        if (hasValue(pMsg.srchOptTxt_20)) {
            mtrLbCd = pMsg.srchOptTxt_20.getValue().split(STR_COMMA);
        }
        for (int i = 0; i < cMsg.E.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(cMsg.E.no(i).xxChkBox_E, ZYPConstant.FLG_OFF_N);
            if (mtrLbCd != null) {
                for (int j = 0; j < mtrLbCd.length; j++) {
                    if (mtrLbCd[j].equals(cMsg.E.no(i).mtrLbCd_E.getValue())) {
                        ZYPEZDItemValueSetter.setValue(cMsg.E.no(i).xxChkBox_E, ZYPConstant.CHKBOX_ON_Y);
                        break;
                    }
                }
            }
        }

        ZYPEZDItemValueSetter.setValue(cMsg.serNum_H, pMsg.srchOptTxt_21.getValue());
        // START 2022/10/13 M.Komatsu [QC#60078,MOD]
        // ZYPEZDItemValueSetter.setValue(cMsg.svcMachMstrStsCd_H, pMsg.srchOptTxt_22.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.xxComnScrColValTxt_H4, pMsg.srchOptTxt_22.getValue());
        // END 2022/10/13 M.Komatsu [QC#60078,MOD]
        // START 2022/10/13 M.Komatsu [QC#60537,ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.mdseCd_H, pMsg.srchOptTxt_39.getValue());
        // END 2022/10/13 M.Komatsu [QC#60537,ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.t_MdlNm_H, pMsg.srchOptTxt_23.getValue());
        // START 2019/08/30 [QC#53005,ADD]
        setSrchOptForBigDeciml(cMsg.svcConfigMstrPk_H, pMsg.srchOptTxt_38.getValue());
        // END 2019/08/30 [QC#53005,ADD]
        // mod start 2016/04/25 CSA Defect#4326
        ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm_H2, pMsg.srchOptTxt_24.getValue());
        // mod end 2016/04/25 CSA Defect#4326
        ZYPEZDItemValueSetter.setValue(cMsg.xxGenlFldAreaTxt_H2, pMsg.srchOptTxt_25.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.locNum_H, pMsg.srchOptTxt_26.getValue());
        setSrchOptForBigDeciml(cMsg.xxRadioBtn_H1, pMsg.srchOptTxt_27.getValue());
        setSrchOptForDate(cMsg.xxFromDt_H, pMsg.srchOptTxt_28.getValue());
        setSrchOptForDate(cMsg.xxThruDt_H, pMsg.srchOptTxt_29.getValue());
        // START 2022/10/13 M.Komatsu [QC#60078,MOD]
        // ZYPEZDItemValueSetter.setValue(cMsg.bllgCycleCd_HB, pMsg.srchOptTxt_30.getValue());
        // ZYPEZDItemValueSetter.setValue(cMsg.bllgCycleCd_HM, pMsg.srchOptTxt_31.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.xxComnScrColValTxt_H5, pMsg.srchOptTxt_30.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.xxComnScrColValTxt_H6, pMsg.srchOptTxt_31.getValue());
        // END 2022/10/13 M.Komatsu [QC#60078,MOD]
        ZYPEZDItemValueSetter.setValue(cMsg.baseChrgToLeaseCmpyFlg_H, pMsg.srchOptTxt_32.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.usgChrgToLeaseCmpyFlg_H, pMsg.srchOptTxt_33.getValue());
        setSrchOptForBigDeciml(cMsg.xxRadioBtn_H2, pMsg.srchOptTxt_34.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_H2, pMsg.srchOptTxt_35.getValue());
        // START 2018/02/06 M.Kidokoro [QC#23375,ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_H3, pMsg.srchOptTxt_36.getValue());
        // END 2018/02/06 M.Kidokoro [QC#23375,ADD]
        // START 2018/11/05 K.Fujimoto [QC#28627, ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.contrLinkNum_H,pMsg.srchOptTxt_37.getValue());
        // END   2018/11/05 K.Fujimoto [QC#28627, ADD]
    }

    private static void setSrchOptForDate(EZDCDateItem cItem, String pItem) {
        if (isValidDate(pItem, DATE_FORMAT_YYYYMMDD)) {
            ZYPEZDItemValueSetter.setValue(cItem, pItem);
        } else {
            cItem.clear();
        }
    }

    private static void setSrchOptForBigDeciml(EZDCBigDecimalItem cItem, String pItem) {
        if (isNumberCheck(pItem)) {
            ZYPEZDItemValueSetter.setValue(cItem, new BigDecimal(pItem));
        } else {
            cItem.clear();
        }
    }

    /**
     * callNszc0330forSaveSearch
     * @param cMsg NSAL0620CMsg
     * @param sMsg NSAL0620SMsg
     * @param srchOptUsrId String
     */
    public static void callNszc0330forSaveSearch(NSAL0620CMsg cMsg, NSAL0620SMsg sMsg, String srchOptUsrId) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_INSERT_UPDATE);
        if (!hasValue(cMsg.srchOptNm_S) //
                || isSameSaveSearchName(cMsg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_S);
        }
        if (hasValue(cMsg.srchOptNm_S)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, cMsg.srchOptNm_S);
        } else {
            setSelectSaveSearchName(pMsg, cMsg);
        }
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, APL_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, srchOptUsrId);

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, cMsg.dsContrNum_H.getValue());
        ZYPEZDItemValueSetter.setValue((EZDPStringItem) pMsg.srchOptTxt_02, cMsg.dsContrCratDt_H1.getValue());
        ZYPEZDItemValueSetter.setValue((EZDPStringItem) pMsg.srchOptTxt_03, cMsg.dsContrCratDt_H2.getValue());
        // START 2022/10/13 M.Komatsu [QC#60078,MOD]
        // ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, cMsg.dsContrCatgCd_H.getValue());
        // ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, cMsg.dsContrCtrlStsCd_H.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, cMsg.xxComnScrColValTxt_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, cMsg.xxComnScrColValTxt_H2.getValue());
        // END 2022/10/13 M.Komatsu [QC#60078,MOD]
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, cMsg.invSeptBaseUsgFlg_H.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, cMsg.xxChkBox_H1.getValue());
        // mod start 2016/04/25 CSA Defect#4326
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_08, cMsg.dsContrRptGrpNum_H.getValue());
        // mod end 2016/04/25 CSA Defect#4326
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_09, cMsg.svcContrRefCmntTxt_H.getValue());
        // START 2022/10/13 M.Komatsu [QC#60078,MOD]
        // ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_10, cMsg.dsContrClsCd_H.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_10, cMsg.xxComnScrColValTxt_H3.getValue());
        // END 2022/10/13 M.Komatsu [QC#60078,MOD]
        String svcRgNm = "";
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.B.no(i).xxChkBox_B.getValue())) {
                svcRgNm = cMsg.B.no(i).svcRgNm_B.getValue();
                if (hasValue(pMsg.srchOptTxt_11)) {
                    svcRgNm = pMsg.srchOptTxt_11.getValue().concat(STR_COMMA).concat(svcRgNm);
                }
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_11, svcRgNm);
            }
        }

        String svcContrBrCd = "";
        for (int i = 0; i < cMsg.C.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.C.no(i).xxChkBox_C.getValue())) {
                svcContrBrCd = cMsg.C.no(i).svcContrBrCd_C.getValue();
                if (hasValue(pMsg.srchOptTxt_12)) {
                    svcContrBrCd = pMsg.srchOptTxt_12.getValue().concat(STR_COMMA).concat(svcContrBrCd);
                }
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_12, svcContrBrCd);
            }
        }
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_13, cMsg.dsAcctNm_H.getValue()); // Mdse Cd
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_14, cMsg.dsAcctNum_H.getValue()); // Supply Mdse Cd
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_15, cMsg.xxGenlFldAreaTxt_H1.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_16, cMsg.billToCustCd_H1.getValue());
        // add start 2016/07/01 CSA Defect#11261
//        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_17, cMsg.svcPgmMdseCd_H.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_17, cMsg.mdseDescShortTxt_H.getValue());
        // add end 2016/07/01 CSA Defect#11261
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_18, cMsg.dsAcctNm_H3.getValue());

        String mtrReadMethCd = "";
        for (int i = 0; i < cMsg.D.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.D.no(i).xxChkBox_D.getValue())) {
                mtrReadMethCd = cMsg.D.no(i).mtrReadMethCd_D.getValue();
                if (hasValue(pMsg.srchOptTxt_19)) {
                    mtrReadMethCd = pMsg.srchOptTxt_19.getValue().concat(STR_COMMA).concat(mtrReadMethCd);
                }
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_19, mtrReadMethCd);
            }
        }

        String mtrLbCd = "";
        for (int i = 0; i < cMsg.E.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.E.no(i).xxChkBox_E.getValue())) {
                mtrLbCd = cMsg.E.no(i).mtrLbCd_E.getValue();
                if (hasValue(pMsg.srchOptTxt_20)) {
                    mtrLbCd = pMsg.srchOptTxt_20.getValue().concat(STR_COMMA).concat(mtrLbCd);
                }
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_20, mtrLbCd);
            }
        }

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_21, cMsg.serNum_H.getValue());
        // START 2022/10/13 M.Komatsu [QC#60078,MOD]
        // ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_22, cMsg.svcMachMstrStsCd_H.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_22, cMsg.xxComnScrColValTxt_H4.getValue());
        // END 2022/10/13 M.Komatsu [QC#60078,MOD]
        // START 2022/10/13 M.Komatsu [QC#60537,ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_39, cMsg.mdseCd_H.getValue());
        // END 2022/10/13 M.Komatsu [QC#60537,ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_23, cMsg.t_MdlNm_H.getValue());
        // START 2019/08/30 [QC#53005,ADD]
        if (hasValue(cMsg.svcConfigMstrPk_H)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_38, cMsg.svcConfigMstrPk_H.getValue().toString());
        }
        // END 2019/08/30 [QC#53005,ADD]
        // mod start 2016/04/25 CSA Defect#4326
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_24, cMsg.dsAcctNm_H2.getValue());
        // mod end 2016/04/25 CSA Defect#4326
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_25, cMsg.xxGenlFldAreaTxt_H2.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_26, cMsg.locNum_H.getValue());
        if (hasValue(cMsg.xxRadioBtn_H1)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_27, cMsg.xxRadioBtn_H1.getValue().toString());
        }
        ZYPEZDItemValueSetter.setValue((EZDPStringItem) pMsg.srchOptTxt_28, cMsg.xxFromDt_H.getValue());
        ZYPEZDItemValueSetter.setValue((EZDPStringItem) pMsg.srchOptTxt_29, cMsg.xxThruDt_H.getValue());
        // START 2022/10/13 M.Komatsu [QC#60078,MOD]
        // ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_30, cMsg.bllgCycleCd_HB.getValue());
        // ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_31, cMsg.bllgCycleCd_HM.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_30, cMsg.xxComnScrColValTxt_H5.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_31, cMsg.xxComnScrColValTxt_H6.getValue());
        // END 2022/10/13 M.Komatsu [QC#60078,MOD]
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_32, cMsg.baseChrgToLeaseCmpyFlg_H.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_33, cMsg.usgChrgToLeaseCmpyFlg_H.getValue());
        if (hasValue(cMsg.xxRadioBtn_H2)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_34, cMsg.xxRadioBtn_H2.getValue().toString());
        }
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_35, cMsg.xxChkBox_H2.getValue());
        // START 2018/02/06 M.Kidokoro [QC#23375,ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_36, cMsg.xxChkBox_H3.getValue());
        // END 2018/02/06 M.Kidokoro [QC#23375,ADD]
        // START 2018/11/05 K.Fujimoto [QC#28627, ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_37, cMsg.contrLinkNum_H.getValue());
        // END   2018/11/05 K.Fujimoto [QC#28627, ADD]
        if (callNszc0330(cMsg, pMsg)) {
            createSavedSearchOptionsPullDown(cMsg, srchOptUsrId);
            ZYPEZDItemValueSetter.setValue(cMsg.srchOptPk_S, pMsg.srchOptPk);
            cMsg.srchOptNm_S.clear();
            cMsg.setMessageInfo(MSG_ID.ZZZM9003I.toString(), new String[] {"Save Search" });
        }
    }

    private static boolean isSameSaveSearchName(NSAL0620CMsg cMsg) {
        if (!hasValue(cMsg.srchOptPk_S)) {
            return false;
        }
        if (!hasValue(cMsg.srchOptNm_S)) {
            return false;
        }

        for (int i = 0; i < cMsg.srchOptNm_L.length(); i++) {
            if (!hasValue(cMsg.srchOptNm_L.no(i))) {
                return false;
            }
            if (cMsg.srchOptPk_S.getValue().compareTo(cMsg.srchOptPk_L.no(i).getValue()) == 0) {
                if (cMsg.srchOptNm_S.getValue().equals(cMsg.srchOptNm_L.no(i).getValue())) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    /**
     * isExistSaveSearchName
     * @param cMsg NSAL0620CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NSAL0620CMsg cMsg) {
        if (!hasValue(cMsg.srchOptNm_S)) {
            return false;
        }

        for (int i = 0; i < cMsg.srchOptNm_L.length(); i++) {
            if (!hasValue(cMsg.srchOptNm_L.no(i))) {
                return false;
            }
            if (cMsg.srchOptNm_S.getValue().equals(cMsg.srchOptNm_L.no(i).getValue())) {
                if (hasValue(cMsg.srchOptPk_S) //
                        && cMsg.srchOptPk_S.getValue().compareTo(cMsg.srchOptPk_L.no(i).getValue()) == 0) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * setSelectSaveSearchName
     * @param pMsg NSZC033001PMsg
     * @param cMsg NSAL0620CMsg
     */
    public static void setSelectSaveSearchName(NSZC033001PMsg pMsg, NSAL0620CMsg cMsg) {
        if (!hasValue(cMsg.srchOptPk_S)) {
            return;
        }

        for (int i = 0; i < cMsg.srchOptNm_L.length(); i++) {
            if (!hasValue(cMsg.srchOptNm_L.no(i))) {
                return;
            }
            if (cMsg.srchOptPk_S.getValue().compareTo(cMsg.srchOptPk_L.no(i).getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, cMsg.srchOptNm_L.no(i));
            }
        }
        return;
    }

    /**
     * callNszc0330forDeleteSearch
     * @param cMsg NSAL0620CMsg
     * @param sMsg NSAL0620SMsg
     * @param srchOptUsrId String
     */
    public static void callNszc0330forDeleteSearch(NSAL0620CMsg cMsg, NSAL0620SMsg sMsg, String srchOptUsrId) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_S);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, APL_ID);

        if (callNszc0330(cMsg, pMsg)) {
            createSavedSearchOptionsPullDown(cMsg, srchOptUsrId);
            cMsg.srchOptNm_S.clear();
            cMsg.setMessageInfo(MSG_ID.ZZZM9003I.toString(), new String[] {"Delete Search" });
        }
    }

    private static boolean callNszc0330(NSAL0620CMsg cMsg, NSZC033001PMsg pMsg) {
        // Search Option API(NSZC0330) is executed
        NSZC033001 api = new NSZC033001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);

        String msgId;
        if (!S21ApiUtil.getXxMsgIdList(pMsg).isEmpty()) {
            for (int j = 0; j < pMsg.xxMsgIdList.length(); j++) {
                if (hasValue(pMsg.xxMsgIdList.no(j).xxMsgId)) {
                    msgId = pMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                    cMsg.setMessageInfo(msgId);
                    if (msgId.endsWith(MSG_KIND.ERROR.getMsgKind())) {
                        cMsg.srchOptPk_S.setErrorInfo(1, msgId);
                        cMsg.srchOptNm_S.setErrorInfo(1, msgId);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // add start 2016/07/28 CSA Defect#6923
    /**
     * isExistNotManualRenewContr
     * @param cMsg NSAL0620CMsg
     * @param sMsg NSAL0620SMsg
     * @return boolean
     */
    public static boolean isExistNotManualRenewContr(NSAL0620CMsg cMsg, NSAL0620SMsg sMsg) {
        List<BigDecimal> checkedList = new ArrayList<BigDecimal>();
        List<BigDecimal> notManualList = new ArrayList<BigDecimal>();
        // START 2018/07/23 K.Kim [QC#26831,ADD]
        List<BigDecimal> manualList = new ArrayList<BigDecimal>();
        List<BigDecimal> notRnwAvalList = new ArrayList<BigDecimal>();
        // END 2018/07/23 K.Kim [QC#26831,ADD]

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxChkBox_A.getValue())) {
                if (!checkedList.contains(sMsg.A.no(i).dsContrPk_A.getValue())) {
                    checkedList.add(sMsg.A.no(i).dsContrPk_A.getValue());
                }
            }
        }

        S21SsmEZDResult rslt = NSAL0620Query.getInstance().getContrForManualRenew(cMsg.glblCmpyCd.getValue(), checkedList);
        if (rslt.isCodeNormal()) {
            // START 2018/07/23 K.Kim [QC#26831,MOD]
            // List<BigDecimal> manualList = (List<BigDecimal>) rslt.getResultObject();
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) rslt.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
                manualList.add((BigDecimal)resultMap.get("DS_CONTR_PK"));
                if (!ZYPConstant.FLG_ON_Y.equals((String)resultMap.get("CONTR_RNW_AVAL_FLG"))) {
                	notRnwAvalList.add((BigDecimal)resultMap.get("DS_CONTR_PK"));
                }
            }
            // END 2018/07/23 K.Kim [QC#26831,MOD]
            for (BigDecimal dsContrPk : checkedList) {
                if (!manualList.contains(dsContrPk)) {
                    notManualList.add(dsContrPk);
                }
            }
        } else if (rslt.isCodeNotFound()) {
            notManualList = new ArrayList<BigDecimal>(checkedList);
        }
        // START 2018/07/23 K.Kim [QC#26831,MOD]
        // if (notManualList.isEmpty()) {
        if (notManualList.isEmpty() && notRnwAvalList.isEmpty()){
        // END 2018/07/23 K.Kim [QC#26831,MOD]
            return false;
        }

        BigDecimal firstErrIndex = null;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxChkBox_A.getValue())) {
                if (notManualList.contains(sMsg.A.no(i).dsContrPk_A.getValue())) {
                    // START 2024/02/26 K.Ogasawara [QC#63550,MOD]
                    // sMsg.A.no(i).xxChkBox_A.setErrorInfo(1, MSG_ID.NSAM0441E.toString(), new String[] {"The contract other than Manual Renew" });
                    sMsg.A.no(i).xxChkBox_A.setErrorInfo(1, MSG_ID.NSAM0441E.toString(), new String[] {"The contract other than Manual Renew or Auto Renew" });
                    // END 2024/02/26 K.Ogasawara [QC#64500,MOD]
                    if (firstErrIndex == null) {
                        firstErrIndex = BigDecimal.valueOf(i);
                    }
                }
                // START 2018/07/23 K.Kim [QC#26831,ADD]
                if (notRnwAvalList.contains(sMsg.A.no(i).dsContrPk_A.getValue())) {
                    sMsg.A.no(i).xxChkBox_A.setErrorInfo(1, MSG_ID.NSAM0441E.toString(), new String[] {"This contract status" });
                    if (firstErrIndex == null) {
                        firstErrIndex = BigDecimal.valueOf(i);
                    }
                }
                // END 2018/07/23 K.Kim [QC#26831,MOD]
            }
        }
        if (firstErrIndex == null) {
            return false;
        }

        BigDecimal len = BigDecimal.valueOf(cMsg.A.length());
        BigDecimal fromNum = firstErrIndex.divide(len, 0, BigDecimal.ROUND_DOWN).multiply(len).add(BigDecimal.ONE);
        cMsg.xxPageShowFromNum.setValue(fromNum);
        NSAL0620CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
        NSAL0620CommonLogic.setPagenation(cMsg.xxPageShowOfNum, cMsg.xxPageShowToNum, cMsg.xxPageShowFromNum.getValueInt(), cMsg.A.getValidCount(), sMsg.A.getValidCount());

        return true;
    }
    // add end 2016/07/28 CSA Defect#6923

    // add start 2016/08/25 CSA Defect#11027
    /**
     * isExistExcludedTerminateContr
     * @param cMsg NSAL0620CMsg
     * @param sMsg NSAL0620SMsg
     * @return boolean
     */
    public static boolean isExistExcludedTerminateContr(NSAL0620CMsg cMsg, NSAL0620SMsg sMsg) {
        BigDecimal firstErrIndex = null;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (!ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxChkBox_A.getValue())) {
                continue;
            }

            // START 2018/07/23 K.Kim [QC#26831,MOD]
            // String dsContrCtrlStsCd = NSAL0620Query.getInstance().getDsContrCtrlStsCd(cMsg.glblCmpyCd.getValue(), sMsg.A.no(i).dsContrPk_A.getValue());
            // // START 2017/11/16 K.Kojima [QC#21886,MOD]
            // // if (DS_CONTR_CTRL_STS.SINGED.endsWith(dsContrCtrlStsCd) || DS_CONTR_CTRL_STS.ACTIVE.endsWith(dsContrCtrlStsCd) || DS_CONTR_CTRL_STS.RENEWAL_HOLD.endsWith(dsContrCtrlStsCd)
            // //         || DS_CONTR_CTRL_STS.RENEWAL_HOLD_FOR_PO.endsWith(dsContrCtrlStsCd)) {
            // if (DS_CONTR_CTRL_STS.SINGED.endsWith(dsContrCtrlStsCd) || DS_CONTR_CTRL_STS.ACTIVE.endsWith(dsContrCtrlStsCd) || DS_CONTR_CTRL_STS.RENEWAL_HOLD.endsWith(dsContrCtrlStsCd)
            //         || DS_CONTR_CTRL_STS.RENEWAL_HOLD_FOR_PO.endsWith(dsContrCtrlStsCd) || DS_CONTR_CTRL_STS.RENEWAL_HOLD_SYSTEM_HOLD.endsWith(dsContrCtrlStsCd)
            //         || DS_CONTR_CTRL_STS.RENEWAL_HOLD_FOR_PO_SYSTEM_HOLD.endsWith(dsContrCtrlStsCd)) {
            // // END 2017/11/16 K.Kojima [QC#21886,MOD]
            //     continue;
            // }
            String contrTrmnAvalFlg = NSAL0620Query.getInstance().getContrTrmnAvalFlg(cMsg.glblCmpyCd.getValue(), sMsg.A.no(i).dsContrPk_A.getValue());
            if (ZYPConstant.FLG_ON_Y.equals(contrTrmnAvalFlg)) {
                continue;
            }
            // END 2018/07/23 K.Kim [QC#26831,MOD]

            sMsg.A.no(i).xxChkBox_A.setErrorInfo(1, MSG_ID.NSAM0441E.toString(), new String[] {"This contract status" });
            if (firstErrIndex == null) {
                firstErrIndex = BigDecimal.valueOf(i);
            }
        }

        if (firstErrIndex == null) {
            return false;
        }

        BigDecimal len = BigDecimal.valueOf(cMsg.A.length());
        BigDecimal fromNum = firstErrIndex.divide(len, 0, BigDecimal.ROUND_DOWN).multiply(len).add(BigDecimal.ONE);
        cMsg.xxPageShowFromNum.setValue(fromNum);
        NSAL0620CommonLogic.copyGlblMsgToBizMsg(cMsg, sMsg);
        NSAL0620CommonLogic.setPagenation(cMsg.xxPageShowOfNum, cMsg.xxPageShowToNum, cMsg.xxPageShowFromNum.getValueInt(), cMsg.A.getValidCount(), sMsg.A.getValidCount());

        return true;
    }
    // add end 2016/08/25 CSA Defect#11027

    // START 2016/10/28 T.Tomita [QC#15468, ADD]
    private static String convertDateFormat(String date) {
        if (hasValue(date)) {
            date = ZYPDateUtil.formatEzd8ToDisp(date);
        }
        return date;
    }
    // END 2016/10/28 T.Tomita [QC#15468, ADD]
    // START 2018/07/20 T.Wada [QC#26424, ADD]
    private static String convertDateFormat2(String date) {
        if (hasValue(date)) {
            date = ZYPDateUtil.formatEzd6ToDisp(date);
        }
        return date;
    }
    // END 2018/07/20 T.Wada [QC#26424, ADD]

    // START 2017/05/25 Y.Osawa [QC#18560, ADD]
    /**
     * deletePulldownList
     * @param cdArray EZDCStringItemArray Code Array
     * @param txtArray EZDCStringItemArray Text Array
     * @param delCd delete Code
     */
    public static void deletePulldownList(EZDCStringItemArray cdArray, EZDCStringItemArray txtArray, String delCd) {
        int index = -1;
        for (int i = 0; i < cdArray.length(); i++) {
            if (delCd.equals(cdArray.no(i).getValue())) {
                index = i;
                break;
            }
        }

        if (index >= 0) {
            int i = index;
            for (; i < cdArray.length() - 1; i++) {
                ZYPEZDItemValueSetter.setValue(cdArray.no(i), cdArray.no(i + 1));
                ZYPEZDItemValueSetter.setValue(txtArray.no(i), txtArray.no(i + 1));
            }
            cdArray.no(i).clear();
            txtArray.no(i).clear();
        }
    }
    // END   2017/05/25 Y.Osawa [QC#18560, ADD]

    //START 2017/08/21 E.Kameishi [QC#8661,ADD]
    /**
     * createLetter
     * @param cMsg NSAL0620CMsg
     * @param sMsg NSAL0620SMsg
     */
    public static void createLetter(NSAL0620CMsg cMsg, NSAL0620SMsg sMsg) {
        // get Contract for creating Letter Work
        List<BigDecimal> dsContrDtlPkList = new ArrayList<BigDecimal>();
        List<BigDecimal> dsContrPkList = new ArrayList<BigDecimal>();
        List<Map<String, Object>> contrInfoList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxChkBox_A.getValue())) {
                dsContrDtlPkList.add(sMsg.A.no(i).dsContrDtlPk_A.getValue());
            }
        }
        S21SsmEZDResult ssmResult = NSAL0620Query.getInstance().getContrDtlList(dsContrDtlPkList);

        if (!ssmResult.isCodeNormal()) {
            return;
        }

        // delete SVC_PRC_RNW_LTR_WRK,delete SVC_TERM_COND_LTR_WRK
        List<Map<String, Object>> dsContrDtlInfoList = (List<Map<String, Object>>) ssmResult.getResultObject();
        List<SVC_PRC_RNW_LTR_WRKTMsg> delSvcPrcRnwLtrWrkList = new ArrayList<SVC_PRC_RNW_LTR_WRKTMsg>();
        List<SVC_TERM_COND_LTR_WRKTMsg> delSvcTermCondLtrWrkList = new ArrayList<SVC_TERM_COND_LTR_WRKTMsg>();

        for (Map<String, Object> dsContrDtlInfo : dsContrDtlInfoList) {
            BigDecimal dsContrPk = (BigDecimal) dsContrDtlInfo.get("DS_CONTR_PK");

            // get SVC_PRC_RNW_LTR_WRK information for deleting
            SVC_PRC_RNW_LTR_WRKTMsgArray outSvcPrcRnwLtrWrkTMsgArray = NSAL0620Query.getSvcPrcRmwLtrWrkInfo(cMsg, dsContrPk);
            for (int i = 0; i < outSvcPrcRnwLtrWrkTMsgArray.getValidCount(); i++) {
                delSvcPrcRnwLtrWrkList.add(outSvcPrcRnwLtrWrkTMsgArray.no(i));
            }

            // get SVC_TERM_COND_LTR_WRK information for deleting
            SVC_TERM_COND_LTR_WRKTMsgArray outSvcTermCondLtrWrkTMsgArray = NSAL0620Query.getSvcTermCondLtrWrkInfo(cMsg, dsContrPk);
            for (int i = 0; i < outSvcTermCondLtrWrkTMsgArray.getValidCount(); i++) {
                delSvcTermCondLtrWrkList.add(outSvcTermCondLtrWrkTMsgArray.no(i));
            }
        }
        if (delSvcPrcRnwLtrWrkList.size() > 0) {
            S21FastTBLAccessor.removePhysical(delSvcPrcRnwLtrWrkList.toArray(new SVC_PRC_RNW_LTR_WRKTMsg[delSvcPrcRnwLtrWrkList.size()]));
        }
        if (delSvcTermCondLtrWrkList.size() > 0) {
            S21FastTBLAccessor.removePhysical(delSvcTermCondLtrWrkList.toArray(new SVC_TERM_COND_LTR_WRKTMsg[delSvcTermCondLtrWrkList.size()]));
        }

        // get WRK data 
        List<BigDecimal> dsContrPkListRegAndAgg = new ArrayList<BigDecimal>();
        List<BigDecimal> dsContrPkListFlt = new ArrayList<BigDecimal>();

        for (Map<String, Object> dsContrDtlInfo : dsContrDtlInfoList) {
            String dsContrCatgCd = (String) dsContrDtlInfo.get("DS_CONTR_CATG_CD");
            if (DS_CONTR_CATG.REGULAR.equals(dsContrCatgCd) || DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
                dsContrPkListRegAndAgg.add((BigDecimal) dsContrDtlInfo.get("DS_CONTR_PK"));
            } else if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
                dsContrPkListFlt.add((BigDecimal) dsContrDtlInfo.get("DS_CONTR_PK"));
            }
        }
        //START 2021/09/10 L.Mandanas [QC#58314-1, ADD]
        S21SsmEZDResult checkDsContrBllgMtr =  new S21SsmEZDResult();
        String hasMtrFlg = ZYPConstant.FLG_OFF_N;
        List < Map < String, Object > > checkDsContrBllgMtrResult = null;
        if (dsContrDtlPkList.size() > 0) {
            checkDsContrBllgMtr = NSAL0620Query.getInstance()
                                  .checkDsContrBllgMtr(cMsg, dsContrDtlPkList);
            if (checkDsContrBllgMtr.isCodeNormal()) {
                checkDsContrBllgMtrResult =
                    (List < Map < String, Object > >) checkDsContrBllgMtr
                                                       .getResultObject();
                if (!checkDsContrBllgMtrResult.isEmpty()) {
                    hasMtrFlg = ZYPConstant.FLG_ON_Y;
                }
            }
        }
        //END 2021/09/10 L.Mandanas [QC#58314-1, ADD]

        S21SsmEZDResult resWrkDataForRegAndAgg = new S21SsmEZDResult();
        S21SsmEZDResult resWrkDataForFlt =  new S21SsmEZDResult();

        if (dsContrPkListRegAndAgg.size() > 0) {
            //START 2021/09/10 L.Mandanas [QC#58314-1, MOD]
              //resWrkDataForRegAndAgg = NSAL0620Query.getInstance().getWrkDataForRegAndAgg(cMsg, dsContrPkListRegAndAgg);
            resWrkDataForRegAndAgg = NSAL0620Query.getInstance()
                                     .getWrkDataForRegAndAgg(cMsg,
                                      dsContrPkListRegAndAgg, hasMtrFlg);
            //END 2021/09/10 L.Mandanas [QC#58314-1, MOD]
        }
        if (dsContrPkListFlt.size() > 0) { 
            resWrkDataForFlt = NSAL0620Query.getInstance().getWrkDataForFlt(cMsg, dsContrPkListFlt);
        }
 
        // create SVC_PRC_RNW_LTR_WRK
        boolean insErrFlg = false;
        // Regular/Aggregate
        List<Map<String, Object>> wrkDataForRegAndAggList = (List<Map<String, Object>>) resWrkDataForRegAndAgg.getResultObject();
        if (wrkDataForRegAndAggList != null) {
            Map<String, Map<String, Object>> totDealNetAmtRegAndAggMap = calcTotDealNetAmt(wrkDataForRegAndAggList);
            if (wrkDataForRegAndAggList.size() > 0) {
                insErrFlg = insertSvcPrcRnwLtrWrk(wrkDataForRegAndAggList, dsContrPkList, contrInfoList, totDealNetAmtRegAndAggMap, cMsg);
            }
        }

        // Fleet
        List<Map<String, Object>> wrkDataForFltList = (List<Map<String, Object>>) resWrkDataForFlt.getResultObject();
        if (wrkDataForFltList != null) {
            Map<String, Map<String, Object>> totDealNetAmtFltMap = calcTotDealNetAmt(wrkDataForFltList);
            if (wrkDataForFltList == null || wrkDataForFltList.size() > 0) {
                insErrFlg = insertSvcPrcRnwLtrWrk(wrkDataForFltList, dsContrPkList, contrInfoList, totDealNetAmtFltMap, cMsg);
            }
        }

        if (insErrFlg) {
            return;
        }

        if (dsContrPkList.size() == 0) {
            cMsg.setMessageInfo(MSG_ID.NSAM0701E.toString());
            return;
        }
        //get Data for creating Term & Condition Work
        S21SsmEZDResult wrkDataForTermAndCond = NSAL0620Query.getInstance().getWrkDataForTermAndCond(cMsg, dsContrPkList);
        List<Map<String, Object>> wrkDataForTermAndCondList = (List<Map<String, Object>>) wrkDataForTermAndCond.getResultObject();

        for (Map<String, Object> wrkDataForTermAndCondMap : wrkDataForTermAndCondList) {
            SVC_TERM_COND_LTR_WRKTMsg svcTermCondLtrWrkTMsg = new SVC_TERM_COND_LTR_WRKTMsg();
            setValue(svcTermCondLtrWrkTMsg.glblCmpyCd, cMsg.glblCmpyCd);
            setValue(svcTermCondLtrWrkTMsg.svcTermCondLtrWrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_TERM_COND_LTR_WRK_SQ));
            setValue(svcTermCondLtrWrkTMsg.dsContrPk, (BigDecimal) wrkDataForTermAndCondMap.get("DS_CONTR_PK"));
            setValue(svcTermCondLtrWrkTMsg.slsDt, cMsg.slsDt);
            setValue(svcTermCondLtrWrkTMsg.bizAppId, APL_ID);
            setValue(svcTermCondLtrWrkTMsg.otptOpCd, OTPT_OP_CD);
            setValue(svcTermCondLtrWrkTMsg.specSideTpCd, (String) wrkDataForTermAndCondMap.get("SPEC_SIDE_TP_CD"));
            setValue(svcTermCondLtrWrkTMsg.svcTermCondHdrNum, (BigDecimal) wrkDataForTermAndCondMap.get("SVC_TERM_COND_HDR_NUM"));
            setValue(svcTermCondLtrWrkTMsg.svcTermCondDtlNum, (BigDecimal) wrkDataForTermAndCondMap.get("SVC_TERM_COND_DTL_NUM"));
            setValue(svcTermCondLtrWrkTMsg.ttlValTxt, (String) wrkDataForTermAndCondMap.get("ENG_TTL_VAL_TXT"));
            setValue(svcTermCondLtrWrkTMsg.descValTxt, (String) wrkDataForTermAndCondMap.get("ENG_DESC_VAL_TXT"));
            setValue(svcTermCondLtrWrkTMsg.termCondVrsnTxt, (String) wrkDataForTermAndCondMap.get("TERM_COND_VRSN_TXT"));

            EZDTBLAccessor.insert(svcTermCondLtrWrkTMsg);
            String rtnCd = svcTermCondLtrWrkTMsg.getReturnCode();
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                cMsg.setMessageInfo(MSG_ID.NSAM0032E.toString(), new String[] {"SVC_TERM_COND_LTR_WRK" });
                return;
            }
        }
    }

    /**
     * check Over 10 Select
     * @param cMsg NSAL0620CMsg
     * @param sMsg NSAL0620SMsg
     * @return boolean
     */
    public static boolean checkOverSelect(NSAL0620CMsg cMsg, NSAL0620SMsg sMsg) {
        boolean rtnVal = true;
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.A, XX_CHK_BOX_A, ZYPConstant.CHKBOX_ON_Y);
        if (selectedRows.size() > 10) {
            cMsg.setMessageInfo(MSG_ID.NSAM0441E.toString(), new String[] {"More than 10 checkboxes" });
            rtnVal = false;
        }
        return rtnVal;
    }

    private static Map<String, Map<String, Object>> calcTotDealNetAmt(List<Map<String, Object>> wrkDataList) {
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        Map<String, Map<String, Object>> resultsMap = new HashMap<String, Map<String, Object>>();
        //START 2021/09/10 L.Mandanas [QC#58314-1, ADD]
        List < String > dsContrDtlPkList = new ArrayList < String >();
        String dsContrDtlPk = null;
        //END 2021/09/10 L.Mandanas [QC#58314-1, ADD]

        // Initialize temporary Map
        for (Map<String, Object> wrkData : wrkDataList) {
            // START 2018/05/25 K.Kitachi [QC#26253, MOD]
            BigDecimal dsContrPk = (BigDecimal) wrkData.get("DS_CONTR_PK");
            String shipToCustCd = (String) wrkData.get("SHIP_TO_CUST_CD");
            String mapKey = dsContrPk.toString() + " " + shipToCustCd;

//            if (!resultsMap.containsKey(dsContrPk)) {
            if (!resultsMap.containsKey(mapKey)) {
                Map<String, Object> result = new HashMap<String, Object>();
                result.put("DS_CONTR_PK", dsContrPk.toString());
                result.put("SHIP_TO_CUST_CD", shipToCustCd);
                result.put("TOT_DEAL_NET_AMT_TXT", BigDecimal.ZERO);
//                resultsMap.put(dsContrPk.toString(), result);
                resultsMap.put(mapKey, result);
            }
            // END 2018/05/25 K.Kitachi [QC#26253, MOD]
        }
        // calculate TOT_DEAL_NET_AMT
        for (Map<String, Object> wrkData : wrkDataList) {
            // START 2018/05/25 K.Kitachi [QC#26253, MOD]
            BigDecimal dsContrPk = (BigDecimal) wrkData.get("DS_CONTR_PK");
            String shipToCustCd = (String) wrkData.get("SHIP_TO_CUST_CD");
            String mapKey = dsContrPk.toString() + " " + shipToCustCd;
//            Map<String, Object> result = resultsMap.get(dsContrPk.toString());
            Map<String, Object> result = resultsMap.get(mapKey);
            // END 2018/05/25 K.Kitachi [QC#26253, MOD]
            //START 2021/09/10 L.Mandanas [QC#58314-1, ADD]
            dsContrDtlPk = wrkData.get("DS_CONTR_DTL_PK").toString();
            if (dsContrDtlPkList.contains(dsContrDtlPk)) {
                continue;
            }
            //END 2021/09/10 L.Mandanas [QC#58314-1, ADD]

            BigDecimal resAmt = (BigDecimal) result.get("TOT_DEAL_NET_AMT_TXT");
            if (ZYPCommonFunc.hasValue((String) wrkData.get("BASE_PRC_DEAL_AMT_TXT"))) {
                BigDecimal wrkAmt = new BigDecimal((String) wrkData.get("BASE_PRC_DEAL_AMT_TXT"));
                result.put("TOT_DEAL_NET_AMT_TXT", resAmt.add(wrkAmt));
                //START 2021/09/10 L.Mandanas [QC#58314-1, ADD]
                dsContrDtlPkList.add(dsContrDtlPk);
                //END 2021/09/10 L.Mandanas [QC#58314-1, ADD]
            }
        }
        return resultsMap;

    }

    private static String getNumberFormat(BigDecimal data) {
        if (hasValue(data)) {
            return ZYPFormatUtil.formatNumToSysDisp(data);
        }
        return "";
    }

    private static String getNumberFormat(String data) {
        if (hasValue(data)) {
            return ZYPFormatUtil.formatNumToSysDisp(new BigDecimal(data));
        }
        return data;
    }

    private static boolean insertSvcPrcRnwLtrWrk(List<Map<String, Object>> wrkDataList, List<BigDecimal> dsContrPkList, List<Map<String, Object>> contrInfoList, Map<String, Map<String, Object>> totDealNetAmtMap, NSAL0620CMsg cMsg) {
        BigDecimal wrkDsContrPk = BigDecimal.ZERO;
        int seqNum = 0;
        // START 2021/01/29 Y.Penequito [QC#56717, ADD]
        BigDecimal dsCntrDtlPkMainUnit = null;
        BigDecimal dsCntrPkValueMainUnit = null;
        BigDecimal totalBasePrcDealAmt = null;
        int seqNumberMainUnit = 0;
        // END 2021/01/29 Y.Penequito [QC#56717, ADD]

        //START 2021/04/19 L.Mandanas [QC#58314-1, ADD]
        String mdlNmHldr = null;
        Map < String, Object > aggLine = new HashMap < String, Object >();
        Boolean isAggregate = false;
        //END 2021/04/19 L.Mandanas [QC#58314-1, ADD]
        // START 2021/07/15 L.Mandanas [QC#58314-1, ADD]
        BigDecimal maxCumFreqMthAot = BigDecimal.ZERO;
        String maxCumBllgCycleNm = null;
        // END 2021/07/15 L.Mandanas [QC#58314-1, ADD]

        for (Map<String, Object> wrkData : wrkDataList) {
            BigDecimal dsContrPk = (BigDecimal) wrkData.get("DS_CONTR_PK");
            String shipToCustCd = (String) wrkData.get("SHIP_TO_CUST_CD");
            String dsContrNum = (String) wrkData.get("DS_CONTR_NUM");
            String bllgCycle = (String) wrkData.get("BASE_BLLG_CYCLE_DESC_TXT");
            // START 2021/03/17 L.Mandanas [QC#58314-1, MOD]
               //String meterCycle = (String) wrkData.get("USG_BLLG_CYCLE_DESC_TXT");
            String meterCycle = null;
            // END 2021/03/17 L.Mandanas [QC#58314-1, MOD]

            // START 2021/04/19 L.Mandanas [QC#58314-1, ADD]
            String lineLv = (String) wrkData.get("LINE_LV");
            if (LINE_LVL_FIVE.equals(lineLv)) {
                 aggLine.put("CUM_COPY_CNT",
                        (String) wrkData.get("CUM_COPY_CNT"));
                 aggLine.put("CUM_BLLG_CYCLE_NM",
                        (String) wrkData.get("CUM_BLLG_CYCLE_NM"));
                 aggLine.put("CUM_COPY_END_DT",
                        (String) wrkData.get("CUM_COPY_END_DT"));
                 // START 2021/07/15 L.Mandanas [QC#58314-1, ADD]
                 if (maxCumFreqMthAot.compareTo(
                      (BigDecimal) wrkData.get("CUM_COPY_FREQ_MTH_AOT")) < 0) {
                     maxCumFreqMthAot = (BigDecimal) wrkData.get(
                       "CUM_COPY_FREQ_MTH_AOT");
                     maxCumBllgCycleNm = (String) wrkData.get(
                       "CUM_BLLG_CYCLE_NM");
                 }
                 // END 2021/07/15 L.Mandanas [QC#58314-1, ADD]
                 isAggregate = true;
                 continue;
             }
            // END 2021/04/19 L.Mandanas [QC#58314-1, ADD]
            // START 2021/03/17 L.Mandanas [QC#58314-1, ADD]
            String copyQtyTxt = null;
            String usgBllgCycleDescTxt = (String) wrkData.get(
                    "USG_BLLG_CYCLE_DESC_TXT");
            String cumBllgCycleNm = (String) wrkData.get("CUM_BLLG_CYCLE_NM");
            String xsMtrCovCopyQtyTxt = (String) wrkData.get(
                    "XS_MTR_COV_COPY_QTY_TXT");
            BigDecimal xsMtrCovCopyQtyNum = BigDecimal.ZERO;
            String cumCopyCntTxt = (String) wrkData.get("CUM_COPY_CNT");
            String cumCopyEndDtTxt = (String) wrkData.get("CUM_COPY_END_DT");
            String slsDtTxt = cMsg.slsDt.getValue();

            String mdlNmTmp = (String) wrkData.get("MDL_NM");
            String serNum = (String) wrkData.get("SER_NUM");
            if (!hasValue(serNum) && hasValue(mdlNmTmp) && hasValue(mdlNmHldr)
                   && mdlNmTmp.equals(mdlNmHldr)) {
                continue;
            }
            if (hasValue(serNum)) {
                mdlNmHldr = null;
            } else {
                mdlNmHldr = mdlNmTmp;
            }
            // START 2021/04/19 L.Mandanas [QC#58314-1, ADD]
            if (LINE_LVL_TWO.equals(lineLv)
                    && hasValue((String) aggLine.get("CUM_COPY_END_DT"))
                    && hasValue((String) aggLine.get("CUM_BLLG_CYCLE_NM"))
                    && hasValue((String) aggLine.get("CUM_COPY_CNT"))) {
                cumCopyEndDtTxt = (String) aggLine.get("CUM_COPY_END_DT");
                cumBllgCycleNm = (String) aggLine.get("CUM_BLLG_CYCLE_NM");
                cumCopyCntTxt = (String) aggLine.get("CUM_COPY_CNT");
            }
            // END 2021/04/19 L.Mandanas [QC#58314-1, ADD]

            if (hasValue(xsMtrCovCopyQtyTxt)) {
                xsMtrCovCopyQtyNum = new BigDecimal(xsMtrCovCopyQtyTxt);
            }
            if (xsMtrCovCopyQtyNum.compareTo(BigDecimal.ZERO) > 0
                    && hasValue(cumCopyEndDtTxt) && hasValue(slsDtTxt)
                    && ZYPDateUtil.compare(cumCopyEndDtTxt, slsDtTxt) >= 0) {
                    // Cum End Date >= Sales Date
                cMsg.setMessageInfo(MSG_ID.NSZM1378E.toString());
                return true;
            }

            // START 2022/01/14 L.Mandanas [QC#58314-4, MOD]
            //if (hasValue(cumCopyCntTxt) && hasValue(cumCopyEndDtTxt)) {
            if (hasValue(cumCopyCntTxt) && hasValue(cumCopyEndDtTxt)
                    && !isAggregate) {
            // END 2022/01/14 L.Mandanas [QC#58314-4, MOD]
                // Cum End Date < Contract/ Uplift End Date
                if (ZYPDateUtil.compare(cumCopyEndDtTxt, slsDtTxt) < 0) {
                    copyQtyTxt = xsMtrCovCopyQtyTxt;
                    meterCycle = usgBllgCycleDescTxt;
                } else if (ZYPDateUtil.compare(cumCopyEndDtTxt,
                        slsDtTxt) == 0) {
                    // Cum End Date == Contract End Date
                    // will only run if xsMtrCovCopyQtyTxt is 0
                    // START 2021/07/15 L.Mandanas [QC#58314-1, MOD]
                      //copyQtyTxt = xsMtrCovCopyQtyTxt;
                      //meterCycle = usgBllgCycleDescTxt;
                    copyQtyTxt = cumCopyCntTxt;
                    meterCycle = cumBllgCycleNm;
                    // END 2021/07/15 L.Mandanas [QC#58314-1, MOD]
                } else {
                    // Cum End Date > Contract End Date
                    // will only run if xsMtrCovCopyQtyNum is 0
                    copyQtyTxt = cumCopyCntTxt;
                    meterCycle = cumBllgCycleNm;
                }
            } else {
                copyQtyTxt = xsMtrCovCopyQtyTxt;
                meterCycle = usgBllgCycleDescTxt;
            }
            if (hasValue(meterCycle) && meterCycle.equals(SEMIANNUAL)) {
                meterCycle = SEMIANNUALLY;
            }
            // END 2021/03/17 L.Mandanas [QC#58314-1, ADD]

            boolean existFlg = false;
            for (Map<String, Object> tmpContrInfo : contrInfoList) {
                BigDecimal tmpDsContrPk = (BigDecimal) tmpContrInfo.get("DS_CONTR_PK");
                String tmpShipToCustCd = (String) tmpContrInfo.get("SHIP_TO_CUST_CD");

                if (hasValue(dsContrPk) && dsContrPk.compareTo(tmpDsContrPk) == 0
                        && hasValue(shipToCustCd) && shipToCustCd.equals(tmpShipToCustCd)) {
                    existFlg = true;
                }
            }
            if (!existFlg) {
                Map<String, Object>contrInfo = new HashMap<String, Object>();
                contrInfo.put("DS_CONTR_PK", dsContrPk);
                contrInfo.put("SHIP_TO_CUST_CD",  shipToCustCd);
                contrInfo.put("DS_CONTR_NUM",  dsContrNum);
                contrInfoList.add(contrInfo);
            }

            if (!dsContrPkList.contains(dsContrPk)) {
                dsContrPkList.add(dsContrPk);
            }

            if (wrkDsContrPk.compareTo(dsContrPk) != 0) {
                seqNum = 1;

            }
            // START 2021/04/19 L.Mandanas [QC#58314-1, DEL]
                //String lineLv = (String) wrkData.get("LINE_LV");
            // END 2021/04/19 L.Mandanas [QC#58314-1, DEL]
            if (LINE_LVL_ONE.equals(lineLv)) {
                BigDecimal bllgCycleMthAot = (BigDecimal) wrkData.get("BASE_BLLG_CYCLE_MTH_AOT");
                BigDecimal meterCycleMthAot = (BigDecimal) wrkData.get("USG_BLLG_CYCLE_MTH_AOT");
                // START 2021/07/15 L.Mandanas [QC#58314-1, ADD]
                BigDecimal cumCopyFreqMthAot = BigDecimal.ZERO;
                if (isAggregate) {
                    cumCopyFreqMthAot = maxCumFreqMthAot;
                    meterCycle = maxCumBllgCycleNm;
                } else {
                   cumCopyFreqMthAot =
                        (BigDecimal) wrkData.get("CUM_COPY_FREQ_MTH_AOT");
                }
                // END 2021/07/15 L.Mandanas [QC#58314-1, ADD]
                for (Map<String, Object> tmpData : wrkDataList) {
                    String tmpShipToCustCd = (String) tmpData.get("SHIP_TO_CUST_CD");
                    BigDecimal tmpDsContrPk = (BigDecimal) tmpData.get("DS_CONTR_PK");
                    BigDecimal tmpBllgCycleMthAot = (BigDecimal) tmpData.get("BASE_BLLG_CYCLE_MTH_AOT");
                    BigDecimal tmpMeterCycleMthAot = (BigDecimal) tmpData.get("USG_BLLG_CYCLE_MTH_AOT");
                    // START 2021/07/15 L.Mandanas [QC#58314-1, ADD]
                    BigDecimal tmpCopyFreqMthAot =
                        (BigDecimal) tmpData.get("CUM_COPY_FREQ_MTH_AOT");
                    // END 2021/07/15 L.Mandanas [QC#58314-1, ADD]

                    // bllgCycle
                    if (dsContrPk.compareTo(tmpDsContrPk) == 0 && shipToCustCd.equals(tmpShipToCustCd)
                            && (hasValue(bllgCycleMthAot) && hasValue(tmpBllgCycleMthAot) && bllgCycleMthAot.compareTo(tmpBllgCycleMthAot) < 0)
                            || (!hasValue(bllgCycleMthAot) && hasValue(tmpBllgCycleMthAot))) {
                        bllgCycleMthAot = tmpBllgCycleMthAot;
                        bllgCycle = (String) tmpData.get("BASE_BLLG_CYCLE_DESC_TXT");
                    }
                    // meterCycle
                    // START 2021/07/15 L.Mandanas [QC#58314-1, MOD]
/*                      if (dsContrPk.compareTo(tmpDsContrPk) == 0 && shipToCustCd.equals(tmpShipToCustCd)
                               && (hasValue(meterCycleMthAot) && hasValue(tmpMeterCycleMthAot) && meterCycleMthAot.compareTo(tmpMeterCycleMthAot) < 0)
                               || (!hasValue(meterCycleMthAot) && hasValue(tmpMeterCycleMthAot))) { */
                    if (dsContrPk.compareTo(tmpDsContrPk) == 0 && shipToCustCd.equals(tmpShipToCustCd)
                            && (hasValue(meterCycleMthAot) && hasValue(tmpMeterCycleMthAot) && meterCycleMthAot.compareTo(tmpMeterCycleMthAot) < 0)
                            || (!hasValue(meterCycleMthAot) && hasValue(tmpMeterCycleMthAot))
                            || (cumCopyFreqMthAot.compareTo(
                                   tmpCopyFreqMthAot) < 0)) {
                    // END 2021/07/15 L.Mandanas [QC#58314-1, MOD]
                        meterCycleMthAot = tmpMeterCycleMthAot;
                        // START 2021/03/17 L.Mandanas [QC#58314-1, MOD]
                        // START 2021/07/15 L.Mandanas [QC#58314-1, MOD]
                          //meterCycle = (String) tmpData.get("USG_BLLG_CYCLE_DESC_TXT");
                          /*String cumCopyCntTxtTmp = (String) wrkData.get(
                                   "CUM_COPY_CNT");
                           String cumCopyEndDtTxtTmp = (String) wrkData.get(
                                   "CUM_COPY_END_DT");
                           String usgBllgCycleDescTxtTmp = (String) wrkData.get(
                                   "USG_BLLG_CYCLE_DESC_TXT");
                           String cumBllgCycleNmTmp = (String) wrkData.get(
                                   "CUM_BLLG_CYCLE_NM");*/
//                        String cumCopyCntTxtTmp = (String) tmpData.get(
//                                "CUM_COPY_CNT");
                        String cumCopyEndDtTxtTmp = (String) tmpData.get(
                                "CUM_COPY_END_DT");
                        String usgBllgCycleDescTxtTmp = (String) tmpData.get(
                                "USG_BLLG_CYCLE_DESC_TXT");
                        String cumBllgCycleNmTmp = (String) tmpData.get(
                                "CUM_BLLG_CYCLE_NM");

                           //if (LINE_LVL_TWO.equals(lineLv)
                           //   && hasValue((String) aggLine.get("CUM_COPY_END_DT"))
                        if (hasValue((String) aggLine.get("CUM_COPY_END_DT"))
                           && hasValue((String) aggLine.get("CUM_BLLG_CYCLE_NM"))
                           && hasValue((String) aggLine.get("CUM_COPY_CNT"))) {
                            cumCopyEndDtTxtTmp = (String) aggLine.get(
                                    "CUM_COPY_END_DT");
                            cumBllgCycleNmTmp = (String) aggLine.get(
                                    "CUM_BLLG_CYCLE_NM");
//                            cumCopyCntTxtTmp = (String) aggLine.get(
//                                    "CUM_COPY_CNT");
                        }

                           //if (hasValue(cumCopyCntTxtTmp)
                                //&& hasValue(cumCopyEndDtTxtTmp)) {
                        // START 2022/01/14 L.Mandanas [QC#58314-4, MOD]
                        //if (hasValue(cumCopyEndDtTxtTmp)) {
                        if (hasValue(cumCopyEndDtTxtTmp) & !isAggregate) {
                        // END 2022/01/14 L.Mandanas [QC#58314-4, MOD]
                        // END 2021/07/15 L.Mandanas [QC#58314-1, MOD]
                            // Cum End Date < Contract/ Uplift End Date
                            if (ZYPDateUtil.compare(cumCopyEndDtTxtTmp,
                                    slsDtTxt) < 0) {
                                meterCycle = usgBllgCycleDescTxtTmp;
                        // START 2021/07/15 L.Mandanas [QC#58314-1, MOD]
                               //} else if (ZYPDateUtil.compare(cumCopyEndDtTxtTmp,
                               //        slsDtTxt) == 0) {
                            } else if (cumCopyFreqMthAot.compareTo(
                                            tmpCopyFreqMthAot) < 0) {
                                // Cum End Date >= Contract End Date
                                // will only run if xsMtrCovCopyQtyTxt is 0
                                  //meterCycle = usgBllgCycleDescTxtTmp;
                                meterCycle = cumBllgCycleNmTmp;
                                cumCopyFreqMthAot = tmpCopyFreqMthAot;
                            // } else {
                                // Cum End Date > Contract End Date
                                // will only run if xsMtrCovCopyQtyNum is 0
                                //meterCycle = cumBllgCycleNmTmp;
                            }
                        // END 2021/07/15 L.Mandanas [QC#58314-1, MOD]
                        } else {
                            meterCycle = usgBllgCycleDescTxtTmp;
                        }
                        if (hasValue(meterCycle)
                                && meterCycle.equals(SEMIANNUAL)) {
                            meterCycle = SEMIANNUALLY;
                        }
                        // END 2021/03/17 L.Mandanas [QC#58314-1, MOD]
                    }
                }
            }

            SVC_PRC_RNW_LTR_WRKTMsg svcPrcRnwLtrWrkTMsg = new SVC_PRC_RNW_LTR_WRKTMsg();
            setValue(svcPrcRnwLtrWrkTMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
            setValue(svcPrcRnwLtrWrkTMsg.svcPrcRnwLtrWrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_PRC_RNW_LTR_WRK_SQ));
            setValue(svcPrcRnwLtrWrkTMsg.dsContrPk, dsContrPk);
            setValue(svcPrcRnwLtrWrkTMsg.dsContrDtlPk, (BigDecimal) wrkData.get("DS_CONTR_DTL_PK"));
            setValue(svcPrcRnwLtrWrkTMsg.dsContrDtlSortNum, BigDecimal.valueOf(seqNum));
            setValue(svcPrcRnwLtrWrkTMsg.slsDt, cMsg.slsDt.getValue());
            setValue(svcPrcRnwLtrWrkTMsg.bizAppId, APL_ID);
            setValue(svcPrcRnwLtrWrkTMsg.otptOpCd, OTPT_OP_CD);
            setValue(svcPrcRnwLtrWrkTMsg.tocNm, (String) wrkData.get("TOC_NM"));
            setValue(svcPrcRnwLtrWrkTMsg.billToCustCd, (String) wrkData.get("BILL_TO_CUST_CD"));
            setValue(svcPrcRnwLtrWrkTMsg.billToLocNm, (String) wrkData.get("BILL_TO_LOC_NM"));
            setValue(svcPrcRnwLtrWrkTMsg.billToFirstLineAddr, (String) wrkData.get("BILL_TO_FIRST_LINE_ADDR"));
            setValue(svcPrcRnwLtrWrkTMsg.billToScdLineAddr, (String) wrkData.get("BILL_TO_SCD_LINE_ADDR"));
            setValue(svcPrcRnwLtrWrkTMsg.billToThirdLineAddr, (String) wrkData.get("BILL_TO_THIRD_LINE_ADDR"));
            setValue(svcPrcRnwLtrWrkTMsg.billToFrthLineAddr, (String) wrkData.get("BILL_TO_FRTH_LINE_ADDR"));
            setValue(svcPrcRnwLtrWrkTMsg.billToCtyAddr, (String) wrkData.get("BILL_TO_CTY_ADDR"));
            setValue(svcPrcRnwLtrWrkTMsg.billToStCd, (String) wrkData.get("BILL_TO_ST_CD"));
            setValue(svcPrcRnwLtrWrkTMsg.billToPostCd, (String) wrkData.get("BILL_TO_POST_CD"));
            setValue(svcPrcRnwLtrWrkTMsg.billToCtryNm, (String) wrkData.get("BILL_TO_CTRY_NM"));
            setValue(svcPrcRnwLtrWrkTMsg.shipToCustCd, (String) wrkData.get("SHIP_TO_CUST_CD"));
            setValue(svcPrcRnwLtrWrkTMsg.shipToLocNm, (String) wrkData.get("SHIP_TO_LOC_NM"));
            setValue(svcPrcRnwLtrWrkTMsg.shipToFirstLineAddr, (String) wrkData.get("SHIP_TO_FIRST_LINE_ADDR"));
            setValue(svcPrcRnwLtrWrkTMsg.shipToScdLineAddr, (String) wrkData.get("SHIP_TO_SCD_LINE_ADDR"));
            setValue(svcPrcRnwLtrWrkTMsg.shipToThirdLineAddr, (String) wrkData.get("SHIP_TO_THIRD_LINE_ADDR"));
            setValue(svcPrcRnwLtrWrkTMsg.shipToFrthLineAddr, (String) wrkData.get("SHIP_TO_FRTH_LINE_ADDR"));
            setValue(svcPrcRnwLtrWrkTMsg.shipToCtyAddr, (String) wrkData.get("SHIP_TO_CTY_ADDR"));
            setValue(svcPrcRnwLtrWrkTMsg.shipToStCd, (String) wrkData.get("SHIP_TO_ST_CD"));
            setValue(svcPrcRnwLtrWrkTMsg.shipToPostCd, (String) wrkData.get("SHIP_TO_POST_CD"));
            setValue(svcPrcRnwLtrWrkTMsg.shipToCtryNm, (String) wrkData.get("SHIP_TO_CTRY_NM"));
            setValue(svcPrcRnwLtrWrkTMsg.dsContrNum, dsContrNum);
            setValue(svcPrcRnwLtrWrkTMsg.baseBllgCycleDescTxt, bllgCycle);
            setValue(svcPrcRnwLtrWrkTMsg.usgBllgCycleDescTxt, meterCycle);
            setValue(svcPrcRnwLtrWrkTMsg.contrEffFromDtTxt, (String) wrkData.get("CONTR_EFF_FROM_DT_TXT"));
            setValue(svcPrcRnwLtrWrkTMsg.contrEffThruDtTxt, (String) wrkData.get("CONTR_EFF_THRU_DT_TXT"));

            String mdlNm = (String) wrkData.get("MDL_NM");
            if (hasValue(mdlNm) && mdlNm.length() > 50) {
                mdlNm = mdlNm.substring(0, 50);
            }
            setValue(svcPrcRnwLtrWrkTMsg.mdlNm, mdlNm);
            setValue(svcPrcRnwLtrWrkTMsg.serNum, (String) wrkData.get("SER_NUM"));
            // START 2023/09/12 S.Nakatani [QC#60074,MOD]
//            setValue(svcPrcRnwLtrWrkTMsg.startReadMtrCntQtyTxt, (String) wrkData.get("START_READ_MTR_CNT_QTY_TXT"));
            setValue(svcPrcRnwLtrWrkTMsg.startReadMtrCntQtyTxt, getNumberFormat((String) wrkData.get("START_READ_MTR_CNT_QTY_TXT")));
            // END 2023/09/12 S.Nakatani [QC#60074,MOD]
            // START 2021/03/17 L.Mandanas [QC#58314-1, MOD]
                //setValue(svcPrcRnwLtrWrkTMsg.xsMtrCovCopyQtyTxt, getNumberFormat((String) wrkData.get("XS_MTR_COV_COPY_QTY_TXT")));
            setValue(svcPrcRnwLtrWrkTMsg.xsMtrCovCopyQtyTxt,
                    getNumberFormat(copyQtyTxt));
            // END 2021/03/17 L.Mandanas [QC#58314-1, MOD]
            setValue(svcPrcRnwLtrWrkTMsg.xsMtrCopyFromQtyTxt, getNumberFormat((String) wrkData.get("XS_MTR_COPY_FROM_QTY_TXT")));
            setValue(svcPrcRnwLtrWrkTMsg.xsMtrCopyThruQtyTxt, getNumberFormat((String) wrkData.get("XS_MTR_COPY_THRU_QTY_TXT")));
            setValue(svcPrcRnwLtrWrkTMsg.xsMtrAmtTxt, getNumberFormat((String) wrkData.get("XS_MTR_AMT_TXT")));
            // START 2023/09/12 S.Nakatani [QC#60074,MOD]
//            setValue(svcPrcRnwLtrWrkTMsg.basePrcDealAmtTxt, getNumberFormat((String) wrkData.get("BASE_PRC_DEAL_AMT_TXT")));
            setValue(svcPrcRnwLtrWrkTMsg.basePrcDealAmtTxt, formatAmountWithCommas((String) wrkData.get("BASE_PRC_DEAL_AMT_TXT")));
            // END 2023/09/12 S.Nakatani [QC#60074,MOD]
            // START 2021/02/03 Y.Penequito [QC#58312, ADD]
            String svcInvMergeTpCd = (String) wrkData.get("SVC_INV_MERGE_TP_CD");
            BigDecimal prntDsContrDtlPk = (BigDecimal) wrkData.get("PRNT_DS_CONTR_DTL_PK");
            String basePrcDealAmtTxtMainUnit = (String) wrkData.get("BASE_PRC_DEAL_AMT_TXT");
            // Get data for main unit
            if (!LINE_LVL_FOUR.equals(lineLv) && basePrcDealAmtTxtMainUnit != null) {
                if (prntDsContrDtlPk == null || isAggregate == true) {
                    // Reset values
                    totalBasePrcDealAmt = new BigDecimal(basePrcDealAmtTxtMainUnit);
                    dsCntrDtlPkMainUnit = (BigDecimal) wrkData.get("DS_CONTR_DTL_PK");
                    seqNumberMainUnit = seqNum;
                    dsCntrPkValueMainUnit = dsContrPk;
                }
            }
            // Condition: Accessary and Print with Base
            if (LINE_LVL_FOUR.equals(lineLv) && svcInvMergeTpCd.equals(PRINT_BASE)) {
                if (prntDsContrDtlPk.equals(dsCntrDtlPkMainUnit) && hasValue(basePrcDealAmtTxtMainUnit)) {
                    // Add BASE_PRC_DEAL_AMT_TXT values
                    totalBasePrcDealAmt = totalBasePrcDealAmt.add(new BigDecimal(basePrcDealAmtTxtMainUnit));
                }
            }
            // END 2021/02/03 Y.Penequito [QC#58312, ADD]

            // START 2018/05/25 K.Kitachi [QC#26253, MOD]
//            Map<String, Object> result = totDealNetAmtMap.get(dsContrPk.toString());
            String mapKey = dsContrPk.toString() + " " + shipToCustCd;
            Map<String, Object> result = totDealNetAmtMap.get(mapKey);
            // END 2018/05/25 K.Kitachi [QC#26253, MOD]
            BigDecimal totDealNetAmt = (BigDecimal) result.get("TOT_DEAL_NET_AMT_TXT");
            // START 2023/09/12 S.Nakatani [QC#60074,MOD]
//            String totDealNetAmtTxt = getNumberFormat(totDealNetAmt);
            String totDealNetAmtTxt = formatAmountWithCommas(totDealNetAmt);
            // END 2023/09/12 S.Nakatani [QC#60074,MOD]
            totDealNetAmtTxt = US_DOLLAR.concat(totDealNetAmtTxt);
            setValue(svcPrcRnwLtrWrkTMsg.totDealNetAmtTxt, totDealNetAmtTxt);

            setValue(svcPrcRnwLtrWrkTMsg.rptId, RPT_ID);
            setValue(svcPrcRnwLtrWrkTMsg.slsDtDescTxt, convertDateTxt(cMsg.slsDt.getValue()));
            setValue(svcPrcRnwLtrWrkTMsg.oldXsMtrAmtTxt, getNumberFormat((String) wrkData.get("OLD_XS_MTR_AMT_TXT")));
            // START 2023/09/12 S.Nakatani [QC#60074,MOD]
//            setValue(svcPrcRnwLtrWrkTMsg.oldBasePrcDealAmtTxt, getNumberFormat((String) wrkData.get("OLD_BASE_PRC_DEAL_AMT_TXT")));
            setValue(svcPrcRnwLtrWrkTMsg.oldBasePrcDealAmtTxt, formatAmountWithCommas((String) wrkData.get("OLD_BASE_PRC_DEAL_AMT_TXT")));
            // END 2023/09/12 S.Nakatani [QC#60074,MOD]

            // QC#25904  Add Start
            setValue(svcPrcRnwLtrWrkTMsg.billToCustAcctCd, (String) wrkData.get("BILL_TO_CUST_ACCT_CD"));
            setValue(svcPrcRnwLtrWrkTMsg.billToCustAcctNm, (String) wrkData.get("BILL_TO_CUST_ACCT_NM"));
            setValue(svcPrcRnwLtrWrkTMsg.shipToCustAcctCd, (String) wrkData.get("SHIP_TO_CUST_ACCT_CD"));
            setValue(svcPrcRnwLtrWrkTMsg.shipToCustAcctNm, (String) wrkData.get("SHIP_TO_CUST_ACCT_NM"));
            // QC#25904  Add End

            // START 2021/02/03 Y.Penequito [QC#58312, MOD]
            if (LINE_LVL_FOUR.equals(lineLv) && svcInvMergeTpCd.equals(PRINT_BASE)) {
                SVC_PRC_RNW_LTR_WRKTMsgArray svcPrcRnwLtrWrkTMsgArray = NSAL0620Query.getInstance().getBasePrcDealAmtTxtMainUnit(cMsg.glblCmpyCd.getValue(), dsCntrPkValueMainUnit);

                if (svcPrcRnwLtrWrkTMsgArray.getValidCount() != 0 || svcPrcRnwLtrWrkTMsgArray != null) {
                    for (int i = 0; i < svcPrcRnwLtrWrkTMsgArray.getValidCount(); i++) {
                        BigDecimal sortNumber = svcPrcRnwLtrWrkTMsgArray.no(i).dsContrDtlSortNum.getValue();
                        if (sortNumber.compareTo(new BigDecimal(seqNumberMainUnit)) == 0) {
                            // Update total BASE_PRC_DEAL_AMT_TXT of main unit
                            // START 2023/09/12 S.Nakatani [QC#60074,MOD]
//                            ZYPEZDItemValueSetter.setValue(svcPrcRnwLtrWrkTMsgArray.no(i).basePrcDealAmtTxt, getNumberFormat(totalBasePrcDealAmt.toString()));
                            ZYPEZDItemValueSetter.setValue(svcPrcRnwLtrWrkTMsgArray.no(i).basePrcDealAmtTxt, formatAmountWithCommas(totalBasePrcDealAmt));
                            // END 2023/09/12 S.Nakatani [QC#60074,MOD]
                            S21FastTBLAccessor.update(svcPrcRnwLtrWrkTMsgArray.no(i));
                            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(svcPrcRnwLtrWrkTMsgArray.no(i).getReturnCode())) {
                                cMsg.setMessageInfo(MSG_ID.NSAM0031E.toString(), new String[] {"SVC_PRC_RNW_LTR_WRK" });
                                return true;
                            }
                        }
                    }
                }
            } else {
                // Insert if Line_LV is not accessary and not print with base
                EZDTBLAccessor.insert(svcPrcRnwLtrWrkTMsg);
                String rtnCd = svcPrcRnwLtrWrkTMsg.getReturnCode();
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
                    cMsg.setMessageInfo(MSG_ID.NSAM0032E.toString(), new String[] {"SVC_PRC_RNW_LTR_WRK" });
                    return true;
                }
            }
            // END 2021/02/03 Y.Penequito [QC#58312, MOD]
            wrkDsContrPk = dsContrPk;
            seqNum++;
        }
        return false;
    }

    private static String convertDateTxt(String strDate) {
        SimpleDateFormat dateFormatIn = new SimpleDateFormat(DATE_FORMAT_YYYYMMDD);
        SimpleDateFormat dateFormatOut = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
        String result = null;

        try {
            Date resultDate = dateFormatIn.parse(strDate);
            result = dateFormatOut.format(resultDate);

        } catch (ParseException e) {
            return result;
        }
        return result;
    }

    // END 2017/08/21 E.Kameishi [QC#8661,ADD]

    // START 2018/03/23 K.Kojima [QC#22722,ADD]
    private static void outputSearchLog(NSAL0620CMsg cMsg) {
        boolean title = false;
        StringBuffer sb = new StringBuffer();
        sb.append("NSAL0620 Search Condition - Contract Details : ");
        if (hasValue(cMsg.dsContrNum_H)) {
            sb.append(" Contract[").append(cMsg.dsContrNum_H.getValue()).append("]");
        }
        // START 2018/11/05 K.Fujimoto [QC#28627, ADD]
        if (hasValue(cMsg.contrLinkNum_H)) {
            sb.append(" Contract Link#[").append(cMsg.contrLinkNum_H.getValue()).append("]");
        }
        // END   2018/11/05 K.Fujimoto [QC#28627, ADD]
        if (hasValue(cMsg.dsContrCratDt_H1) || hasValue(cMsg.dsContrCratDt_H2)) {
            sb.append(" Created Between[").append(cMsg.dsContrCratDt_H1.getValue()).append("-").append(cMsg.dsContrCratDt_H2.getValue()).append("]");
        }
        // START 2022/10/13 M.Komatsu [QC#60078,MOD]
        // if (hasValue(cMsg.dsContrCatgCd_H)) {
        // sb.append(" Contract Type[").append(cMsg.dsContrCatgCd_H.getValue()).append("]");
        if (hasValue(cMsg.xxComnScrColValTxt_H1)) {
            sb.append(" Contract Type[").append(cMsg.xxComnScrColValTxt_H1.getValue()).append("]");
        }
        // END 2022/10/13 M.Komatsu [QC#60078,MOD]
        if (hasValue(cMsg.invSeptBaseUsgFlg_H)) {
            sb.append(" Consolidated Contract[").append(cMsg.invSeptBaseUsgFlg_H.getValue()).append("]");
        }
        // START 2022/10/13 M.Komatsu [QC#60078,MOD]
        // if (hasValue(cMsg.dsContrCtrlStsCd_H)) {
        // sb.append(" Status[").append(cMsg.dsContrCtrlStsCd_H.getValue()).append("]");
        if (hasValue(cMsg.xxComnScrColValTxt_H2)) {
            sb.append(" Status[").append(cMsg.xxComnScrColValTxt_H2.getValue()).append("]");
        }
        // END 2022/10/13 M.Komatsu [QC#60078,MOD]
        if (hasValue(cMsg.xxChkBox_H1)) {
            sb.append(" Has Sub-Contract[").append(cMsg.xxChkBox_H1.getValue()).append("]");
        }
        if (hasValue(cMsg.dsContrRptGrpNum_H)) {
            sb.append(" Report Group #[").append(cMsg.dsContrRptGrpNum_H.getValue()).append("]");
        }
        if (hasValue(cMsg.svcContrRefCmntTxt_H)) {
            sb.append(" Source Reference #[").append(cMsg.svcContrRefCmntTxt_H.getValue()).append("]");
        }
        // START 2022/10/13 M.Komatsu [QC#60078,MOD]
        // if (hasValue(cMsg.dsContrClsCd_H)) {
        // sb.append(" Contract Category[").append(cMsg.dsContrClsCd_H.getValue()).append("]");
        if (hasValue(cMsg.xxComnScrColValTxt_H3)) {
            sb.append(" Contract Category[").append(cMsg.xxComnScrColValTxt_H3.getValue()).append("]");
        }
        // END 2022/10/13 M.Komatsu [QC#60078,MOD]
        title = false;
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            if (hasValue(cMsg.B.no(i).xxChkBox_B)) {
                if (title == false) {
                    sb.append(" Region[").append(cMsg.B.no(i).svcRgDescTxt_B.getValue());
                    title = true;
                } else {
                    sb.append(",").append(cMsg.B.no(i).svcRgDescTxt_B.getValue());
                }
            }
        }
        if (title == true) {
            sb.append("]");
        }
        title = false;
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            if (hasValue(cMsg.B.no(i).xxChkBox_B)) {
                if (title == false) {
                    sb.append(" Region[").append(cMsg.B.no(i).svcRgDescTxt_B.getValue());
                    title = true;
                } else {
                    sb.append(",").append(cMsg.B.no(i).svcRgDescTxt_B.getValue());
                }
            }
        }
        if (title == true) {
            sb.append("]");
        }
        title = false;
        for (int i = 0; i < cMsg.C.getValidCount(); i++) {
            if (hasValue(cMsg.C.no(i).xxChkBox_C)) {
                if (title == false) {
                    sb.append(" Branch[").append(cMsg.C.no(i).svcContrBrDescTxt_C.getValue());
                    title = true;
                } else {
                    sb.append(",").append(cMsg.C.no(i).svcContrBrDescTxt_C.getValue());
                }
            }
        }
        if (title == true) {
            sb.append("]");
        }
        S21InfoLogOutput.println(sb.toString());

        sb = new StringBuffer();
        sb.append("NSAL0620 Search Condition - Customer Details : ");
        if (hasValue(cMsg.dsAcctNm_H)) {
            sb.append(" Customer Name[").append(cMsg.dsAcctNm_H.getValue()).append("]");
        }
        if (hasValue(cMsg.dsAcctNum_H)) {
            sb.append(" Customer #[").append(cMsg.dsAcctNum_H.getValue()).append("]");
        }
        if (hasValue(cMsg.xxGenlFldAreaTxt_H1)) {
            sb.append(" Bill To Location[").append(cMsg.xxGenlFldAreaTxt_H1.getValue()).append("]");
        }
        if (hasValue(cMsg.billToCustCd_H1)) {
            sb.append(" Bill To Code[").append(cMsg.billToCustCd_H1.getValue()).append("]");
        }
        if (hasValue(cMsg.mdseDescShortTxt_H)) {
            sb.append(" Service Program[").append(cMsg.mdseDescShortTxt_H.getValue()).append("]");
        }
        if (hasValue(cMsg.dsAcctNm_H3)) {
            sb.append(" Master Account Name[").append(cMsg.dsAcctNm_H3.getValue()).append("]");
        }
        title = false;
        for (int i = 0; i < cMsg.D.getValidCount(); i++) {
            if (hasValue(cMsg.D.no(i).xxChkBox_D)) {
                if (title == false) {
                    sb.append(" Reading Method[").append(cMsg.D.no(i).mtrReadMethNm_D.getValue());
                    title = true;
                } else {
                    sb.append(",").append(cMsg.D.no(i).mtrReadMethNm_D.getValue());
                }
            }
        }
        if (title == true) {
            sb.append("]");
        }
        title = false;
        for (int i = 0; i < cMsg.E.getValidCount(); i++) {
            if (hasValue(cMsg.E.no(i).xxChkBox_E)) {
                if (title == false) {
                    sb.append(" Reading Method[").append(cMsg.E.no(i).mtrLbNm_E.getValue());
                    title = true;
                } else {
                    sb.append(",").append(cMsg.E.no(i).mtrLbNm_E.getValue());
                }
            }
        }
        if (title == true) {
            sb.append("]");
        }
        S21InfoLogOutput.println(sb.toString());

        sb = new StringBuffer();
        sb.append("NSAL0620 Search Condition - Machine Details : ");
        if (hasValue(cMsg.serNum_H)) {
            sb.append(" Serial #[").append(cMsg.serNum_H.getValue()).append("]");
        }
        // START 2022/10/13 M.Komatsu [QC#60078,MOD]
        // if (hasValue(cMsg.svcMachMstrStsCd_H)) {
        // sb.append(" Status[").append(cMsg.svcMachMstrStsCd_H.getValue()).append("]");
        if (hasValue(cMsg.xxComnScrColValTxt_H4)) {
            sb.append(" Status[").append(cMsg.xxComnScrColValTxt_H4.getValue()).append("]");
        }
        // END 2022/10/13 M.Komatsu [QC#60078,MOD]
        // START 2022/10/13 M.Komatsu [QC#60537,ADD]
        if (hasValue(cMsg.mdseCd_H)) {
            sb.append(" Item #[").append(cMsg.mdseCd_H.getValue()).append("]");
        }
        // END 2022/10/13 M.Komatsu [QC#60537,ADD]
        if (hasValue(cMsg.t_MdlNm_H)) {
            sb.append(" Model #[").append(cMsg.t_MdlNm_H.getValue()).append("]");
        }
        // START 2019/08/30 [QC#53005,ADD]
        if (hasValue(cMsg.svcConfigMstrPk_H)) {
            sb.append(" Config #[").append(cMsg.svcConfigMstrPk_H.getValue()).append("]");
        }
        // END 2019/08/30 [QC#53005,ADD]
        if (hasValue(cMsg.dsAcctNm_H2)) {
            sb.append(" Located At Customer[").append(cMsg.dsAcctNm_H2.getValue()).append("]");
        }
        if (hasValue(cMsg.xxGenlFldAreaTxt_H2)) {
            sb.append(" Location[").append(cMsg.xxGenlFldAreaTxt_H2.getValue()).append("]");
        }
        if (hasValue(cMsg.locNum_H)) {
            sb.append(" Ship To Code[").append(cMsg.locNum_H.getValue()).append("]");
        }
        if (hasValue(cMsg.xxRadioBtn_H1)) {
            if (cMsg.xxRadioBtn_H1.getValue().equals("1")) {
                sb.append(" Schedule Date[").append(cMsg.xxFromDt_H.getValue()).append("-").append(cMsg.xxThruDt_H.getValue()).append("]");
            } else if (cMsg.xxRadioBtn_H1.getValue().equals("2")) {
                sb.append(" Interface Date[").append(cMsg.xxFromDt_H.getValue()).append("-").append(cMsg.xxThruDt_H.getValue()).append("]");
            } else if (cMsg.xxRadioBtn_H1.getValue().equals("3")) {
                sb.append(" Machine Effectivity[").append(cMsg.xxFromDt_H.getValue()).append("-").append(cMsg.xxThruDt_H.getValue()).append("]");
            } else if (cMsg.xxRadioBtn_H1.getValue().equals("4")) {
                sb.append(" RMA Date[").append(cMsg.xxFromDt_H.getValue()).append("-").append(cMsg.xxThruDt_H.getValue()).append("]");
            } else if (cMsg.xxRadioBtn_H1.getValue().equals("5")) {
                sb.append(" Contract Start Date[").append(cMsg.xxFromDt_H.getValue()).append("-").append(cMsg.xxThruDt_H.getValue()).append("]");
            }
        }
        // START 2022/10/13 M.Komatsu [QC#60078,MOD]
        // if (hasValue(cMsg.bllgCycleCd_HB)) {
        // sb.append(" Base Freq[").append(cMsg.bllgCycleCd_HB.getValue()).append("]");
        // }
        // if (hasValue(cMsg.bllgCycleCd_HM)) {
        // sb.append(" Overage Freq[").append(cMsg.bllgCycleCd_HM.getValue()).append("]");
        if (hasValue(cMsg.xxComnScrColValTxt_H5)) {
            sb.append(" Base Freq[").append(cMsg.xxComnScrColValTxt_H5.getValue()).append("]");
        }
        if (hasValue(cMsg.xxComnScrColValTxt_H6)) {
            sb.append(" Overage Freq[").append(cMsg.xxComnScrColValTxt_H6.getValue()).append("]");
        }
        // END 2022/10/13 M.Komatsu [QC#60078,MOD]
        if (hasValue(cMsg.baseChrgToLeaseCmpyFlg_H)) {
            sb.append(" Unbilled Base[").append(cMsg.baseChrgToLeaseCmpyFlg_H.getValue()).append("]");
        }
        if (hasValue(cMsg.usgChrgToLeaseCmpyFlg_H)) {
            sb.append(" Unbilled Overage[").append(cMsg.usgChrgToLeaseCmpyFlg_H.getValue()).append("]");
        }
        if (hasValue(cMsg.xxRadioBtn_H2)) {
            if (cMsg.xxRadioBtn_H2.getValue().equals("1")) {
                sb.append(" Null Start Read");
            } else if (cMsg.xxRadioBtn_H2.getValue().equals("2")) {
                sb.append(" RMA'ed");
            } else if (cMsg.xxRadioBtn_H2.getValue().equals("3")) {
                sb.append(" Renewed");
            } else if (cMsg.xxRadioBtn_H2.getValue().equals("4")) {
                sb.append(" None");
            } else if (cMsg.xxRadioBtn_H2.getValue().equals("5")) {
                sb.append(" Renewal Error");
            }
        }
        if (hasValue(cMsg.xxChkBox_H3)) {
            sb.append(" My Team[").append(cMsg.xxChkBox_H3.getValue()).append("]");
        }
        if (hasValue(cMsg.xxChkBox_H2)) {
            sb.append(" Only Mytask[").append(cMsg.xxChkBox_H2.getValue()).append("]");
        }
        S21InfoLogOutput.println(sb.toString());
    }
    // END 2018/03/23 K.Kojima [QC#22722,ADD]

    // START 2018/06/26 T.Ogura [QC#26336,ADD]
    /**
     * writeMeter_HistoryCsvFile
     * @param bizMsg NSAL0620CMsg
     * @param rs ResultSet
     * @throws SQLException SQLException
     */
    public static void writeMeter_HistoryCsvFile(NSAL0620CMsg cMsg, ResultSet rs) throws SQLException {

        NSAL0620F01FMsg fMsg = new NSAL0620F01FMsg();
        ZYPCSVOutFile fileWriter = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        /** CSV Header */
        String[] csvHeader = {"Serial#", "Item Code", "Model", "Counters", "Reading", "Type", "Valid", "Group#", "Date",
                "Meter Count", "Test Copies", "Comment", "Created By", "Creation Date(EST)", "Data Source", "Invoice#",
                "Invoice Date", "Task Number", "In/Out", "Bill To", "Bill To Location", "Install At", "Install Location"};
        fileWriter.writeHeader(csvHeader);

        int cnt = 0;
        while (rs.next()) {
            writeCsvLine(fMsg, rs);
            fileWriter.write();

            cnt++;
            fMsg.clear();
        }
        fileWriter.close();
    }
    // END   2018/06/26 T.Ogura [QC#26336,ADD]

    // START 2018/06/26 T.Ogura [QC#26336,ADD]
    /**
     * writeCsvLine
     * @param fMsg NSAL0620F01FMsg
     * @param rs ResultSet
     * @throws SQLException SQLException
     */
    private static void writeCsvLine(NSAL0620F01FMsg fMsg, ResultSet rs) throws SQLException {
        ZYPEZDItemValueSetter.setValue(fMsg.serNum, rs.getString("SER_NUM"));
        ZYPEZDItemValueSetter.setValue(fMsg.mdseCd, rs.getString("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(fMsg.t_MdlNm, rs.getString("T_MDL_NM"));
        ZYPEZDItemValueSetter.setValue(fMsg.mtrLbDescTxt, rs.getString("MTR_LB_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(fMsg.fill65Txt, rs.getString("XX_MTR_READ_TP_GRP_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(fMsg.dsMtrReadTpDescTxt, rs.getString("DS_MTR_READ_TP_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(fMsg.vldMtrFlg, rs.getString("VLD_MTR_FLG"));
        ZYPEZDItemValueSetter.setValue(fMsg.fill5Txt, rs.getString("GRP_NUM"));
        ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_RE, rs.getString("XX_READ_DT"));
        if (hasValue(rs.getBigDecimal("READ_MTR_CNT"))) {
            ZYPEZDItemValueSetter.setValue(fMsg.fill15Txt_ME, ZYPFormatUtil.formatNumToSysDisp(rs.getBigDecimal("READ_MTR_CNT")));
        }
        if (hasValue(rs.getBigDecimal("TEST_MTR_CNT"))) {
            ZYPEZDItemValueSetter.setValue(fMsg.fill15Txt_TE, ZYPFormatUtil.formatNumToSysDisp(rs.getBigDecimal("TEST_MTR_CNT")));
        }
        ZYPEZDItemValueSetter.setValue(fMsg.mtrEntryCmntTxt, rs.getString("MTR_ENTRY_CMNT_TXT"));
        ZYPEZDItemValueSetter.setValue(fMsg.xxEmpNmTxt, rs.getString("XX_CRAT_PSN_NM"));
        ZYPEZDItemValueSetter.setValue(fMsg.xxDtTm, rs.getString("XX_CRT_DT"));
        ZYPEZDItemValueSetter.setValue(fMsg.mtrReadSrcTpDescTxt, rs.getString("MTR_READ_SRC_TP_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(fMsg.svcInvNum, rs.getString("SVC_INV_NUM"));
        ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_IN, rs.getString("XX_INV_DT"));
        ZYPEZDItemValueSetter.setValue(fMsg.svcTaskNum, rs.getString("SVC_TASK_NUM"));
        ZYPEZDItemValueSetter.setValue(fMsg.dsTestCopyClsDescTxt, rs.getString("DS_TEST_COPY_CLS_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(fMsg.billToCustNm, rs.getString("BILL_TO_CUST_NM"));
        ZYPEZDItemValueSetter.setValue(fMsg.xxAllLineAddr_BI, rs.getString("BILL_TO_LOC_ADDR"));
        ZYPEZDItemValueSetter.setValue(fMsg.curLocAcctNm, rs.getString("CUR_LOC_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(fMsg.xxAllLineAddr_IN, rs.getString("CUR_LOC_ADDR"));
    }
    // END   2018/06/26 T.Ogura [QC#26336,ADD]

    // START 2022/10/13 M.Komatsu [QC#60078,ADD]
    /**
     * Convert contract status description array into status code list.
     * 
     * @param descs contract status descriptions
     * @param descArray contract status description dictionary array
     * @param codeArray contract status code dictionary array
     * @return list of contract status code
     */
    public static List<String> convertDescs2Codes(String[] descs, EZDCStringItemArray descArray, EZDCStringItemArray codeArray) {
        List<String> codes = new ArrayList<String>();

        for (String desc : descs) {
            if (!desc.isEmpty()) {
                codes.add(convertDesc2Code(desc, descArray, codeArray));
            }
        }

        return codes;
    }

    /**
     * Convert contract status description into status code.
     * 
     * @param desc contract status description
     * @param descArray contract status description dictionary array
     * @param codeArray contract status code dictionary array
     * @return status code ( nonexitent code (XXXX) if contract status description can't be converted to status code )
     */
    public static String convertDesc2Code(String desc, EZDCStringItemArray descArray, EZDCStringItemArray codeArray) {
        String code = NONEXISTENT_CODE;

        for (int i = 0; i < descArray.length(); i++) {
            if (!desc.isEmpty() && desc.equals(descArray.no(i).getValue())) {
                code = codeArray.no(i).getValue();
                break;
            }
        }

        return code;
    }
    // END 2022/10/13 M.Komatsu [QC#60078,ADD]

    // START 2023/09/12 S.Nakatani [QC#60074,ADD]
    private static String formatAmountWithCommas(String Amt) {
        if (!hasValue(Amt)) {
            return null;
        }
        BigDecimal decimalAmt = new BigDecimal(Amt);
        DecimalFormat df = new DecimalFormat(DECIMAL_FORMAT_PATTERN);
        return df.format(decimalAmt);
    }

    private static String formatAmountWithCommas(BigDecimal decimalAmt) {
        if (!hasValue(decimalAmt)) {
            return null;
        }
        DecimalFormat df = new DecimalFormat(DECIMAL_FORMAT_PATTERN);
        return df.format(decimalAmt);
    }
    // END 2023/09/12 S.Nakatani [QC#60074,ADD]
}
