package business.blap.NFDL0020.common;

import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCDateItem;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import parts.dbcommon.EZDTBLAccessor;

import business.blap.NFDL0020.NFDL0020CMsg;
import business.blap.NFDL0020.NFDL0020Query;
import business.blap.NFDL0020.NFDL0020SMsg;
import business.blap.NFDL0020.NFDL0020_DCMsg;
import business.blap.NFDL0020.constant.NFDL0020Constant;
import business.db.BILL_TO_CUSTTMsg;
import business.db.BILL_TO_CUSTTMsgArray;
import business.db.CLT_STRGY_WRK_ITEM_TRXTMsg;
import business.db.CLT_WRK_ITEMTMsg;
import business.file.NFDL0020F00FMsg;
import business.file.NFDL0020F01FMsg;
import business.file.NFDL0020F02FMsg;
import business.file.NFDL0020F03FMsg;
import business.file.NFDL0020F04FMsg;
import business.file.NFDL0020F05FMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_ADJ_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_APPLY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_CASH_APPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_DSPT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_WRK_ITEM_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_WRK_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_TRK_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Collection Detail Screen
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 2016/04/27   Fujitsu         S.Fujita        Update          QC#7218
 * 2016/06/22   Hitachi         K.Kojima        Update          QC#10529
 * 2016/07/07   Hitachi         K.Kojima        Update          QC#10337
 * 2016/09/26   Hitachi         K.Kojima        Update          QC#13907
 * 2017/03/07   Fujitsu         T.murai         Update          QC#17193
 * 2017/08/07   Hitachi         T.Tsuchida      Update          QC#19576
 * 2018/03/15   Hitachi         J.Kim           Update          QC#20945
 * 2018/05/11   Hitachi         J.Kim           Update          QC#21426
 * 2018/05/16   Fujitsu         Y.Matsui        Update          QC#24329
 * 2018/05/21   CITS            S.Katsuma       Update          QC#24793
 * 2018/06/04   Fujitsu         Y.Matsui        Update          QC#24809
 * 2018/06/05   Hitachi         Y.Takeno        Update          QC#26107
 * 2018/06/21   Hitachi         Y.Takeno        Update          QC#25080
 * 2018/07/25   Hitachi         Y.Takeno        Update          QC#25767
 * 2018/07/30   Fujitsu         Y.Matsui        Update          QC#27081
 * 2018/08/30   Hitachi         Y.Takeno        Update          QC#27603
 * 2018/08/31   Hitachi         Y.Takeno        Update          QC#27603
 * 2018/10/30   Hitachi         J.Kim           Update          QC#28937
 * 2019/08/15   Fujitsu         T.Murai         Update          QC#52654
 * 2021/05/25   CITS            G.Delgado       Update          QC#58704
 * 2021/12/23   CITS            K.Suzuki        Update          QC#55788-1
 * 2022/01/20   Hitachi         A.Kohinata      Update          QC#56864
 * 2022/11/01   Hitachi         T.Doi           Update          QC#60415
 * 2022/11/01   Hitachi         T.Doi           Update          QC#57088
 * 2022/12/12   Hitachi         S.Fujita        Update          QC#60406
 * 2023/02/28   Hitachi         S.Fujita        Update          QC#61220
 *</pre>
 */
public class NFDL0020CommonLogic {

    /**
     * copy from SMsg to CMsg
     * @param bizMsg DPAL0110CMsg
     * @param bizMsgAry EZDCMsgArray
     * @param shareMsgAry EZDSMsgArray
     */
    public static void dispPage(NFDL0020CMsg bizMsg, NFDL0020SMsg globalMsg) {

        if (bizMsg.xxPageTblNm_H.getValue().equals("A") && globalMsg.A.getValidCount() > 0) {
            ZYPTableUtil.clear(bizMsg.A);
            int startIndex = bizMsg.xxPageShowFromNum_AH.getValueInt() - 1;
            int dispRowNum = 0;
            if (bizMsg.xxPageShowFromNum_AH.getValueInt() < 0) {
                return;
            }
            for (; dispRowNum < bizMsg.A.length() && startIndex + dispRowNum < globalMsg.A.getValidCount(); dispRowNum++) {
                EZDMsg.copy(globalMsg.A.get(startIndex + dispRowNum), null, bizMsg.A.get(dispRowNum), null);
            }
            bizMsg.A.setValidCount(dispRowNum);
            bizMsg.xxPageShowToNum_AH.setValue(startIndex + dispRowNum);
            bizMsg.xxPageShowOfNum_AH.setValue(globalMsg.A.getValidCount());
        }
        if (bizMsg.xxPageTblNm_H.getValue().equals("B") && globalMsg.B.getValidCount() > 0) {
            ZYPTableUtil.clear(bizMsg.B);
            int startIndex = bizMsg.xxPageShowFromNum_BH.getValueInt() - 1;
            int dispRowNum = 0;
            if (bizMsg.xxPageShowFromNum_BH.getValueInt() < 0) {
                return;
            }
            for (; dispRowNum < bizMsg.B.length() && startIndex + dispRowNum < globalMsg.B.getValidCount(); dispRowNum++) {
                EZDMsg.copy(globalMsg.B.get(startIndex + dispRowNum), null, bizMsg.B.get(dispRowNum), null);
            }
            bizMsg.B.setValidCount(dispRowNum);
            bizMsg.xxPageShowToNum_BH.setValue(startIndex + dispRowNum);
            bizMsg.xxPageShowOfNum_BH.setValue(globalMsg.B.getValidCount());
        }
        if (bizMsg.xxPageTblNm_H.getValue().equals("F") && globalMsg.F.getValidCount() > 0) {
            ZYPTableUtil.clear(bizMsg.F);
            int startIndex = bizMsg.xxPageShowFromNum_FH.getValueInt() - 1;
            int dispRowNum = 0;
            if (bizMsg.xxPageShowFromNum_FH.getValueInt() < 0) {
                return;
            }
            for (; dispRowNum < bizMsg.F.length() && startIndex + dispRowNum < globalMsg.F.getValidCount(); dispRowNum++) {
                EZDMsg.copy(globalMsg.F.get(startIndex + dispRowNum), null, bizMsg.F.get(dispRowNum), null);
            }
            bizMsg.F.setValidCount(dispRowNum);
            bizMsg.xxPageShowToNum_FH.setValue(startIndex + dispRowNum);
            bizMsg.xxPageShowOfNum_FH.setValue(globalMsg.F.getValidCount());
        }
        if (bizMsg.xxPageTblNm_H.getValue().equals("G") && globalMsg.G.getValidCount() > 0) {
            ZYPTableUtil.clear(bizMsg.G);
            int startIndex = bizMsg.xxPageShowFromNum_GH.getValueInt() - 1;
            int dispRowNum = 0;
            if (bizMsg.xxPageShowFromNum_GH.getValueInt() < 0) {
                return;
            }
            for (; dispRowNum < bizMsg.G.length() && startIndex + dispRowNum < globalMsg.G.getValidCount(); dispRowNum++) {
                EZDMsg.copy(globalMsg.G.get(startIndex + dispRowNum), null, bizMsg.G.get(dispRowNum), null);
            }
            bizMsg.G.setValidCount(dispRowNum);
            bizMsg.xxPageShowToNum_GH.setValue(startIndex + dispRowNum);
            bizMsg.xxPageShowOfNum_GH.setValue(globalMsg.G.getValidCount());
        }
        // START 2018/03/15 J.Kim [QC#20945,ADD]
        if (bizMsg.xxPageTblNm_H.getValue().equals("H") && globalMsg.H.getValidCount() > 0) {
            ZYPTableUtil.clear(bizMsg.H);
            int startIndex = bizMsg.xxPageShowFromNum_H.getValueInt() - 1;
            int dispRowNum = 0;
            if (bizMsg.xxPageShowFromNum_H.getValueInt() < 0) {
                return;
            }
            for (; dispRowNum < bizMsg.H.length() && startIndex + dispRowNum < globalMsg.H.getValidCount(); dispRowNum++) {
                EZDMsg.copy(globalMsg.H.get(startIndex + dispRowNum), null, bizMsg.H.get(dispRowNum), null);
            }
            bizMsg.H.setValidCount(dispRowNum);
            bizMsg.xxPageShowToNum_H.setValue(startIndex + dispRowNum);
            bizMsg.xxPageShowOfNum_H.setValue(globalMsg.H.getValidCount());
        }
        // END 2018/03/15 J.Kim [QC#20945,ADD]
        // START 2018/05/16 [QC#24329,ADD]
        if (bizMsg.xxPageTblNm_H.getValue().equals("J") && globalMsg.J.getValidCount() > 0) {
            ZYPTableUtil.clear(bizMsg.J);
            int startIndex = bizMsg.xxPageShowFromNum_J.getValueInt() - 1;
            int dispRowNum = 0;
            if (bizMsg.xxPageShowFromNum_J.getValueInt() < 0) {
                return;
            }
            for (; dispRowNum < bizMsg.J.length() && startIndex + dispRowNum < globalMsg.J.getValidCount(); dispRowNum++) {
                EZDMsg.copy(globalMsg.J.get(startIndex + dispRowNum), null, bizMsg.J.get(dispRowNum), null);
            }
            bizMsg.J.setValidCount(dispRowNum);
            bizMsg.xxPageShowToNum_J.setValue(startIndex + dispRowNum);
            bizMsg.xxPageShowOfNum_J.setValue(globalMsg.J.getValidCount());
        }
        // END 2018/05/16 [QC#24329,ADD]
    }

    /**
     * prev Page.(copy from SMsg to CMsg)
     * @param bizMsg .
     * @param bizMsgAry CMsg.
     * @param shareMsgAry SMsg.
     */
    public static void prevPage(NFDL0020CMsg bizMsg, NFDL0020SMsg globalMsg) {
        if (bizMsg.xxPageTblNm_H.getValue().equals("A")) {
            copyToSMsg(bizMsg, globalMsg); // ADD 2017/03/07 [QC#17193]
            bizMsg.xxPageShowFromNum_AH.setValue(bizMsg.xxPageShowFromNum_AH.getValueInt() - bizMsg.A.length());
        } else if (bizMsg.xxPageTblNm_H.getValue().equals("B")) {
            copyToSMsg(bizMsg, globalMsg); // ADD 2017/03/07 [QC#17193]
            bizMsg.xxPageShowFromNum_BH.setValue(bizMsg.xxPageShowFromNum_BH.getValueInt() - bizMsg.B.length());
        } else if (bizMsg.xxPageTblNm_H.getValue().equals("F")) {
            bizMsg.xxPageShowFromNum_FH.setValue(bizMsg.xxPageShowFromNum_FH.getValueInt() - bizMsg.F.length());
        } else if (bizMsg.xxPageTblNm_H.getValue().equals("G")) {
            bizMsg.xxPageShowFromNum_GH.setValue(bizMsg.xxPageShowFromNum_GH.getValueInt() - bizMsg.G.length());
            // START 2018/03/15 J.Kim [QC#20945,ADD]
        } else if (bizMsg.xxPageTblNm_H.getValue().equals("H")) {
            bizMsg.xxPageShowFromNum_H.setValue(bizMsg.xxPageShowFromNum_H.getValueInt() - bizMsg.H.length());
            // END 2018/03/15 J.Kim [QC#20945,ADD]
            // START 2018/05/16 [QC#24329,ADD]
        } else if (bizMsg.xxPageTblNm_H.getValue().equals("J")) {
            bizMsg.xxPageShowFromNum_J.setValue(bizMsg.xxPageShowFromNum_J.getValueInt() - bizMsg.J.length());
            // END 2018/05/16 [QC#24329,ADD]
        }
        dispPage(bizMsg, globalMsg);
    }

    /**
     * next Page.(copy from SMsg to CMsg)
     * @param bizMsg .
     * @param bizMsgAry CMsg.
     * @param shareMsgAry SMsg.
     */
    public static void nextPage(NFDL0020CMsg bizMsg, NFDL0020SMsg globalMsg) {
        if (bizMsg.xxPageTblNm_H.getValue().equals("A")) {
            copyToSMsg(bizMsg, globalMsg); // ADD 2017/03/07 [QC#17193]
            bizMsg.xxPageShowFromNum_AH.setValue(bizMsg.xxPageShowFromNum_AH.getValueInt() + bizMsg.A.length());
        } else if (bizMsg.xxPageTblNm_H.getValue().equals("B")) {
            copyToSMsg(bizMsg, globalMsg); // ADD 2017/03/07 [QC#17193]
            bizMsg.xxPageShowFromNum_BH.setValue(bizMsg.xxPageShowFromNum_BH.getValueInt() + bizMsg.B.length());
        } else if (bizMsg.xxPageTblNm_H.getValue().equals("F")) {
            bizMsg.xxPageShowFromNum_FH.setValue(bizMsg.xxPageShowFromNum_FH.getValueInt() + bizMsg.F.length());
        } else if (bizMsg.xxPageTblNm_H.getValue().equals("G")) {
            bizMsg.xxPageShowFromNum_GH.setValue(bizMsg.xxPageShowFromNum_GH.getValueInt() + bizMsg.G.length());
            // START 2018/03/15 J.Kim [QC#20945,ADD]
        } else if (bizMsg.xxPageTblNm_H.getValue().equals("H")) {
            bizMsg.xxPageShowFromNum_H.setValue(bizMsg.xxPageShowFromNum_H.getValueInt() + bizMsg.H.length());
            // END 2018/03/15 J.Kim [QC#20945,ADD]
            // START 2018/05/16 [QC#24329,ADD]
        } else if (bizMsg.xxPageTblNm_H.getValue().equals("J")) {
            bizMsg.xxPageShowFromNum_J.setValue(bizMsg.xxPageShowFromNum_J.getValueInt() + bizMsg.J.length());
            // END 2018/05/16 [QC#24329,ADD]
        }
        dispPage(bizMsg, globalMsg);
    }

    /**
     * @param bizMsg Business Component Interface Message
     * @return boolean
     */
    public static boolean setBilltoLocNm(String glblCmpyCd, NFDL0020CMsg bizMsg) {

        if (ZYPCommonFunc.hasValue(bizMsg.billToCustCd_H)) {
            BILL_TO_CUSTTMsg inMsg = new BILL_TO_CUSTTMsg();
            inMsg.setConditionValue("billToCustCd01", bizMsg.billToCustCd_H.getValue());
            inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            inMsg.setMaxCount(1);
            inMsg.setSQLID("057");

            BILL_TO_CUSTTMsgArray outMsg = (BILL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

            if (0 == outMsg.length()) {
                // START 2017/08/07 T.Tsuchida [QC#19576,MOD]
                //bizMsg.locNm_FH.clear();
                bizMsg.locNm_H.clear();
                // END 2017/08/07 T.Tsuchida [QC#19576,MOD]
                // START 2018/06/04 Y.Matsui [QC#24809,ADD]
                bizMsg.billToLocNum_H.clear();
                // END   2018/06/04 Y.Matsui [QC#24809,ADD]
                bizMsg.billToCustCd_H.setErrorInfo(1, "NFCM0029E", null);
                return false;
            } else {
                bizMsg.locNm_H.setValue(outMsg.no(0).locNm.getValue());
                // START 2018/06/04 Y.Matsui [QC#24809,ADD]
                bizMsg.billToLocNum_H.setValue(outMsg.no(0).locNum.getValue());
                // END   2018/06/04 Y.Matsui [QC#24809,ADD]
            }
            return true;
        } else {
            // START 2017/08/07 T.Tsuchida [QC#19576,MOD]
            //bizMsg.locNm_FH.clear();
            bizMsg.locNm_H.clear();
            // END 2017/08/07 T.Tsuchida [QC#19576,MOD]
            // START 2018/06/04 Y.Matsui [QC#24809,ADD]
            bizMsg.billToLocNum_H.clear();
            // END   2018/06/04 Y.Matsui [QC#24809,ADD]
            return true;
        }
    }

    // START 2016/04/27 S.Fujita [QC#7218,MOD]
    /**
     * Show Paging
     * @param bizMsg NFDL0020CMsg
     * @param globalMsg NFDL0020SMsg
     */
    public static void showPage(NFDL0020CMsg bizMsg, NFDL0020SMsg globalMsg) {

        EZDSMsgArray sMsgArray = setSMsgArray(bizMsg, globalMsg);

        if (sMsgArray == null) {
            showScrn(bizMsg);
            return;
        }

        int cnt = sMsgArray.getValidCount();
        if (cnt == 0) {
            bizMsg.setMessageInfo("NFDM0034I");
        } else {
            if (cnt > sMsgArray.length()) {
                bizMsg.setMessageInfo("NZZM0001W");
            }
        }
    }

    /**
     * Set EZDSMsgArray for tab
     * @param bizMsg NFDL0020CMsg
     * @param glblMsg NFDL0020SMsg
     * @return EZDSMsgArray
     */
    public static EZDSMsgArray setSMsgArray(NFDL0020CMsg bizMsg, NFDL0020SMsg globalMsg) {

        EZDSMsgArray sMsgArray = null;

        String tab = bizMsg.xxDplyTab_H.getValue();
        if ("Transaction".equals(tab)) {
            sMsgArray = globalMsg.A;
        } else if ("Note".equals(tab)) {
            sMsgArray = globalMsg.F;
        } else if ("Task".equals(tab)) {
            sMsgArray = globalMsg.G;
        } else if ("Contract".equals(tab)) {
            sMsgArray = globalMsg.B;
            // START 2018/03/15 J.Kim [QC#20945,ADD]
        } else if ("AdjHistory".equals(tab)) {
            sMsgArray = globalMsg.H;
            // END 2018/03/15 J.Kim [QC#20945,ADD]
            // START 2018/05/16 [QC#24329,ADD]
        } else if ("Statement".equals(tab)) {
            sMsgArray = globalMsg.J;
            // END 2018/05/16 [QC#24329,ADD]
        }

        return sMsgArray;
    }

    /**
     * Show Screen
     * @param bizMsg NFDL0020CMsg
     */
    public static void showScrn(NFDL0020CMsg bizMsg) {

        int cnt = bizMsg.D.getValidCount();
        if (cnt == 0) {
            bizMsg.setMessageInfo("NFDM0034I");
        } else {
            if (cnt > bizMsg.D.length()) {
                bizMsg.setMessageInfo("NZZM0001W");
            }
        }
    }
    // END 2016/04/27 S.Fujita [QC#7218,MOD]

    // START 2016/06/22 K.Kojima [QC#10529,ADD]
    /**
     * @param bizMsg Business Component Interface Message
     * @param glblCmpyCd String
     */
    public static void getCollectionPorsonName(NFDL0020CMsg bizMsg, String glblCmpyCd) {

        Map<String, Object> ssmMap = new HashMap<String, Object>();
        ssmMap.put("glblCmpyCd", glblCmpyCd);
        ssmMap.put("cltPsnCd", bizMsg.cltTaskOwnrId_GH.getValue());

        S21SsmEZDResult ssmResult = NFDL0020Query.getInstance().getCollectionPorsonName(ssmMap);

        if (ssmResult.isCodeNormal()) {
            // START 2016/07/07 K.Kojima [QC#10337,MOD]
            // ZYPEZDItemValueSetter.setValue(bizMsg.xxPsnNm_G1,
            // (String) ssmResult.getResultObject());
            ZYPEZDItemValueSetter.setValue(bizMsg.cltPsnNm_G1, (String) ssmResult.getResultObject());
            // END 2016/07/07 K.Kojima [QC#10337,MOD]
        } else {
            // START 2016/07/07 K.Kojima [QC#10337,MOD]
            // bizMsg.xxPsnNm_G1.clear();
            bizMsg.cltPsnNm_G1.clear();
            // END 2016/07/07 K.Kojima [QC#10337,MOD]
        }
    }
    // END 2016/06/22 K.Kojima [QC#10529,ADD]

    // START 2016/09/26 K.Kojima [QC#13004,ADD]
    /**
     * getCollectionStrategyWrkItemForStatusCheck
     * @param glblCmpyCd String
     * @param cltStrgyTrxPk BigDecimal
     * @return List<Map<String, Object>>
     */
    public static List<Map<String, Object>> getCollectionStrategyWrkItemForStatusCheck(String glblCmpyCd, BigDecimal cltStrgyTrxPk) {
        Map<String, Object> ssmMap = new HashMap<String, Object>();
        ssmMap.put("glblCmpyCd", glblCmpyCd);
        ssmMap.put("cltStrgyTrxPk", cltStrgyTrxPk.toString());
        S21SsmEZDResult ssmResult = NFDL0020Query.getInstance().getCollectionStrategyWrkItemForStatusCheck(ssmMap);
        if (ssmResult.isCodeNormal()) {
            return (List<Map<String, Object>>)ssmResult.getResultObject();
        } else {
            return null;
        }
    }
    // END 2016/09/26 K.Kojima [QC#13004,ADD]

    // START 2017/03/06 T.Murai [QC#17193,ADD]
    /**
     * copyMsg cMsg -> sMsg
     * @param NFDL0020CMsg bizMsg
     * @param NFDL0020SMsg globalMsg
     */
    public static void copyToSMsg(NFDL0020CMsg bizMsg, NFDL0020SMsg globalMsg) {

        if (bizMsg.xxPageTblNm_H.getValue().equals("A")) {
            int pagenationFrom = bizMsg.xxPageShowFromNum_AH.getValueInt() - 1;
            int endCnt = pagenationFrom + bizMsg.A.length();

            for (int i = pagenationFrom; i < endCnt; i++) {

                if (i < globalMsg.A.getValidCount()) {
                    EZDMsg.copy(bizMsg.A.no(i - pagenationFrom), null, globalMsg.A.no(i), null);
                } else {
                    break;
                }
            }

        } else if (bizMsg.xxPageTblNm_H.getValue().equals("B")) {

            int pagenationFrom = bizMsg.xxPageShowFromNum_BH.getValueInt();
            int endCnt = pagenationFrom + bizMsg.B.length();

            for (int i = pagenationFrom; i < endCnt; i++) {

                if (i < globalMsg.B.getValidCount()) {
                    EZDMsg.copy(bizMsg.B.no(i - pagenationFrom), null, globalMsg.B.no(i), null);
                } else {
                    break;
                }
            }
            // START 2018/03/15 J.Kim [QC#20945,ADD]
        } else if (bizMsg.xxPageTblNm_H.getValue().equals("H")) {

            int pagenationFrom = bizMsg.xxPageShowFromNum_H.getValueInt();
            int endCnt = pagenationFrom + bizMsg.H.length();

            for (int i = pagenationFrom; i < endCnt; i++) {

                if (i < globalMsg.H.getValidCount()) {
                    EZDMsg.copy(bizMsg.H.no(i - pagenationFrom), null, globalMsg.H.no(i), null);
                } else {
                    break;
                }
            }
            // END 2018/03/15 J.Kim [QC#20945,ADD]
            // START 2018/05/16 [QC#24329,ADD]
        } else if (bizMsg.xxPageTblNm_H.getValue().equals("J")) {

            int pagenationFrom = bizMsg.xxPageShowFromNum_J.getValueInt();
            int endCnt = pagenationFrom + bizMsg.J.length();

            for (int i = pagenationFrom; i < endCnt; i++) {

                if (i < globalMsg.J.getValidCount()) {
                    EZDMsg.copy(bizMsg.J.no(i - pagenationFrom), null, globalMsg.J.no(i), null);
                } else {
                    break;
                }
            }
            // END 2018/05/16 [QC#24329,ADD]
        }

    }
    // END 2017/03/06 T.Murai [QC#17193,ADD]

    // START 2018/03/15 J.Kim [QC#20945,ADD]
    /**
     * get AdjHistory
     * @param bizMsg Business Component Interface Message
     * @param globalMsg Global area information
     */
    public static void getAdjHistory(NFDL0020CMsg bizMsg, NFDL0020SMsg globalMsg, String glblCmpyCd) {

        ZYPTableUtil.clear(globalMsg.H);

        Map<String, Object> ssmMap = new HashMap<String, Object>();
        ssmMap.put("glblCmpyCd", glblCmpyCd);
        ssmMap.put("arApplyTpCd", AR_APPLY_TP.ADJUSTMENT);
        ssmMap.put("arScrCancFlg", ZYPConstant.FLG_OFF_N);
        ssmMap.put("arCashApplyStsU", AR_CASH_APPLY_STS.UNAPPLIED);
        ssmMap.put("arCashApplyStsP", AR_CASH_APPLY_STS.PARTIAL);
        ssmMap.put("cMsg", bizMsg);
        ssmMap.put("rowNum", String.valueOf(globalMsg.H.length() + 1));

        S21SsmEZDResult ssmResult = NFDL0020Query.getInstance().getAdjHistory(ssmMap, globalMsg);

        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > globalMsg.H.length()) {
                queryResCnt = globalMsg.H.length();
                // START 2022/12/14 Y.Mochida [QC#60683,MOD]
                bizMsg.setMessageInfo("NZZM0009W", new String[]{"AdjHistory"});
                // END 2022/12/14 Y.Mochida [QC#60683,MOD]
            }

            int i;
            for (i = 0; i < globalMsg.H.getValidCount(); i++) {
                if (i < bizMsg.H.length()) {
                    EZDMsg.copy(globalMsg.H.no(i), null, bizMsg.H.no(i), null);
                } else {
                    break;
                }
            }

            bizMsg.H.setValidCount(i);
            bizMsg.xxPageShowFromNum_H.setValue(1);
            bizMsg.xxPageShowToNum_H.setValue(bizMsg.H.getValidCount());
            bizMsg.xxPageShowOfNum_H.setValue(queryResCnt);

        } else {
            bizMsg.H.setValidCount(0);
            bizMsg.xxPageShowFromNum_H.setValue(1);
            bizMsg.xxPageShowToNum_H.setValue(bizMsg.H.getValidCount());
            bizMsg.xxPageShowOfNum_H.setValue(0);
        }
    }

    /**
     * writeCsvFile
     * @param bizMsg NFDL0020CMsg
     * @param globalMsg NFDL0020SMsg
     * @param glblCmpyCd String
     */
    public static void writeCsvFile(NFDL0020CMsg bizMsg, NFDL0020SMsg globalMsg, String glblCmpyCd) {

        NFDL0020F00FMsg fMsg = new NFDL0020F00FMsg();
        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(NFDL0020Constant.CSV_NAME), NFDL0020Constant.CSV);
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);
        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(bizMsg));

        // write header
        final String[] csvHeader = new String[] {"Adjustoment#", "Adjustoment Date", "Adjustment Amount", "Transaction#", "Activity Name", "Comments", "Note", "Status", "Due Date" };
        csvOutFile.writeHeader(csvHeader, fMsg, ZYPGUITableColumn.getColOrder(bizMsg));

        Map<String, Object> ssmMap = new HashMap<String, Object>();
        ssmMap.put("glblCmpyCd", glblCmpyCd);
        ssmMap.put("arApplyTpCd", AR_APPLY_TP.ADJUSTMENT);
        ssmMap.put("arScrCancFlg", ZYPConstant.FLG_OFF_N);
        ssmMap.put("arCashApplyStsU", AR_CASH_APPLY_STS.UNAPPLIED);
        ssmMap.put("arCashApplyStsP", AR_CASH_APPLY_STS.PARTIAL);
        ssmMap.put("cMsg", bizMsg);
        // START 2018/10/30 J.Kim [QC#28937,MOD]
        //ssmMap.put("rowNum", String.valueOf(globalMsg.H.length() + 1));
        //
        //S21SsmEZDResult ssmResult = NFDL0020Query.getInstance().getAdjHistory(ssmMap, globalMsg);
        //if (ssmResult.isCodeNormal()) {
        //    if (globalMsg.H.getValidCount() >= NFDL0020Constant.CSV_LIMIT_COUNT) {
        //        bizMsg.setMessageInfo(NFDL0020Constant.NZZM0001W);
        //    }
        //   // write contents
        //    for (int i = 0; i < globalMsg.H.getValidCount(); i++) {
        //        EZDMsg.copy(globalMsg.H.no(i), null, fMsg, null);
        //        String glDt = globalMsg.H.no(i).glDt_H.getValue();
        //        String invDueDt = globalMsg.H.no(i).invDueDt_H.getValue();
        //        if (ZYPCommonFunc.hasValue(glDt)) {
        //            glDt = ZYPDateUtil.convertFormat(glDt, "yyyyMMdd", "MMddyyyy", ZYPDateUtil.SEPARATOR_SLASH);
        //        }
        //        if (ZYPCommonFunc.hasValue(invDueDt)) {
        //            invDueDt = ZYPDateUtil.convertFormat(invDueDt, "yyyyMMdd", "MMddyyyy", ZYPDateUtil.SEPARATOR_SLASH);
        //        }
        //        ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_H1, glDt);
        //        ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_H2, invDueDt);
        //        csvOutFile.write();
        //    }
        //    csvOutFile.close();
        //} else {
        //    bizMsg.setMessageInfo("NFDM0034I");
        //}

        ResultSet rs = null;
        PreparedStatement st = null;
        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(NFDL0020Constant.FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NFDL0020Query.getInstance().getClass());

            st = ssmLLClient.createPreparedStatement("getAdjHistory", ssmMap, execParam);
            rs = st.executeQuery();

            boolean noRecord = true;
            while (rs.next()) {

                noRecord = false;
                String glDt = rs.getString("GL_DT");
                String invDueDt = rs.getString("INV_DUE_DT");
                if (ZYPCommonFunc.hasValue(glDt)) {
                    glDt = ZYPDateUtil.convertFormat(glDt, "yyyyMMdd", "MMddyyyy", ZYPDateUtil.SEPARATOR_SLASH);
                }
                if (ZYPCommonFunc.hasValue(invDueDt)) {
                    invDueDt = ZYPDateUtil.convertFormat(invDueDt, "yyyyMMdd", "MMddyyyy", ZYPDateUtil.SEPARATOR_SLASH);
                }
                ZYPEZDItemValueSetter.setValue(fMsg.arAdjNum_H, rs.getString("AR_ADJ_NUM"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_H1, glDt);
                ZYPEZDItemValueSetter.setValue(fMsg.dealApplyAmt_H, rs.getBigDecimal("DEAL_APPLY_AMT"));
                ZYPEZDItemValueSetter.setValue(fMsg.arTrxNum_H, rs.getString("AR_TRX_NUM"));
                ZYPEZDItemValueSetter.setValue(fMsg.arAdjTpDescTxt_H, rs.getString("AR_ADJ_TP_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.adjCmntTxt_H, rs.getString("ADJ_CMNT_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.arWrtOffNoteTxt_H, rs.getString("AR_WRT_OFF_NOTE_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.arCashApplyStsDescTxt_H, rs.getString("AR_CASH_APPLY_STS_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_H2, invDueDt);
                csvOutFile.write();
            }
            csvOutFile.close();

            if (noRecord) {
                bizMsg.setMessageInfo("NFDM0034I");
            }
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(st, rs);
        }
        // END 2018/10/30 J.Kim [QC#28937,MOD]
    }
    // END 2018/03/15 J.Kim [QC#20945,ADD]

    // START 2018/05/11 J.Kim [QC#21426,ADD]
    /**
     * equals
     * @param nItem EZDCDateItem
     * @param oItem EZDCDateItem
     * @param boolean
     */
    public static boolean equals(EZDCDateItem nItem, EZDCDateItem oItem) {

        boolean equalsFlg = false;
        if (ZYPDateUtil.compare(nItem.getValue(), oItem.getValue()) == 0) {
            equalsFlg = true;
        }

        return equalsFlg;
    }

    /**
     * updateWrkItmMsg
     * @param dMsg NFDL0020_DCMsg
     * @param boolean
     */
    public static boolean updateWrkItmMsg(NFDL0020_DCMsg dMsg) {

        boolean equalsFlg = false;
        if (!equals(dMsg.cltWrkItemRwsdDt_DD, dMsg.cltWrkItemRwsdDt_DB)) {
            equalsFlg = true;
        }
        if (!equals(dMsg.cltWrkItemRwedDt_DD, dMsg.cltWrkItemRwedDt_DB)) {
            equalsFlg = true;
        }
        if (!equals(dMsg.cltWrkItemWsrdDt_DD, dMsg.cltWrkItemWsrdDt_DB)) {
            equalsFlg = true;
        }
        if (!equals(dMsg.cltWrkItemWerdDt_DD, dMsg.cltWrkItemWerdDt_DB)) {
            equalsFlg = true;
        }
        // START 2021/05/25 G.Delgado [QC#58704,ADD]
        if (ZYPCommonFunc.hasValue(dMsg.cltWrkItemStsCd_DD) && !dMsg.cltWrkItemStsCd_DD.getValue().equals(dMsg.cltWrkItemStsCd_DB.getValue())) {
            equalsFlg = true;
        }
        // END 2021/05/25 G.Delgado [QC#58704,ADD]

        return equalsFlg;
    }

    /**
     * <pre> 
     *  Create Collection Strategy Work Item Transaction
     * </pre>
     * @param glblCmpyCd String
     * @param wrkTMsgList List<CLT_STRGY_WRK_ITEM_TRXTMsg>
     * @param dcMsg Collection Strategy Transaction Table
     * @param billToCustCd String
     * @param cltStrgyTrxPk BigDecimal
     * @param cltWrkItemOwnrId String
     * @return List<CLT_STRGY_WRK_ITEM_TRXTMsg>
     */
    // START 2021/05/25 G.Delgado [QC#58704,MOD]
// public static List<CLT_STRGY_WRK_ITEM_TRXTMsg> createCollectionStrategyWorkItemTransaction(String glblCmpyCd, List<CLT_STRGY_WRK_ITEM_TRXTMsg> wrkTMsgList, NFDL0020_DCMsg dcMsg, String billToCustCd, BigDecimal cltStrgyTrxPk) {
    public static List<CLT_STRGY_WRK_ITEM_TRXTMsg> createCollectionStrategyWorkItemTransaction(String glblCmpyCd, List<CLT_STRGY_WRK_ITEM_TRXTMsg> wrkTMsgList,
            NFDL0020_DCMsg dcMsg, String billToCustCd, BigDecimal cltStrgyTrxPk, String cltWrkItemOwnrId) {
    // END 2021/05/25 G.Delgado [QC#58704,MOD]

        CLT_STRGY_WRK_ITEM_TRXTMsg cltStrgyWrkItemTrxtmsg = new CLT_STRGY_WRK_ITEM_TRXTMsg();
        BigDecimal cltStrgyWorkItemTrxSeq = ZYPOracleSeqAccessor.getNumberBigDecimal(NFDL0020Constant.CLT_STRGY_WRK_ITEM_TRX_SQ);
        cltStrgyWrkItemTrxtmsg.glblCmpyCd.setValue(glblCmpyCd);
        cltStrgyWrkItemTrxtmsg.cltStrgyTrxPk.setValue(cltStrgyTrxPk);
        cltStrgyWrkItemTrxtmsg.cltStrgyWrkItemTrxPk.setValue(cltStrgyWorkItemTrxSeq);
        cltStrgyWrkItemTrxtmsg.cltWrkItemCd.setValue(dcMsg.cltWrkItemCd_DD.getValue());
        cltStrgyWrkItemTrxtmsg.cltWrkItemNm.setValue(dcMsg.cltWrkItemNm_DD.getValue());
        cltStrgyWrkItemTrxtmsg.cltWrkTpCd.setValue(dcMsg.cltWrkTpCd_DD.getValue());
        cltStrgyWrkItemTrxtmsg.cltWrkTpNm.setValue(dcMsg.cltWrkTpNm_DD.getValue());
        cltStrgyWrkItemTrxtmsg.billToCustCd.setValue(billToCustCd);
        cltStrgyWrkItemTrxtmsg.cltWrkItemStsCd.setValue(dcMsg.cltWrkItemStsCd_DD.getValue());
        // START 2021/05/25 G.Delgado [QC#58704,ADD]
        ZYPEZDItemValueSetter.setValue(cltStrgyWrkItemTrxtmsg.cltWrkItemOwnrId, cltWrkItemOwnrId);
        ZYPEZDItemValueSetter.setValue(cltStrgyWrkItemTrxtmsg.cltWrkItemRwsdDt, dcMsg.cltWrkItemRwsdDt_DD);
        ZYPEZDItemValueSetter.setValue(cltStrgyWrkItemTrxtmsg.cltWrkItemRwedDt, dcMsg.cltWrkItemRwedDt_DD);
        ZYPEZDItemValueSetter.setValue(cltStrgyWrkItemTrxtmsg.cltWrkItemWsrdDt, dcMsg.cltWrkItemWsrdDt_DD);
        ZYPEZDItemValueSetter.setValue(cltStrgyWrkItemTrxtmsg.cltWrkItemWerdDt, dcMsg.cltWrkItemWerdDt_DD);
        // END 2021/05/25 G.Delgado [QC#58704,ADD]

        // START 2018/07/18 J.Kim [QC#27267,ADD]
        ZYPEZDItemValueSetter.setValue(dcMsg.cltStrgyWrkItemTrxPk_DD, cltStrgyWorkItemTrxSeq);
        // END 2018/07/18 J.Kim [QC#27267,ADD]

        CLT_WRK_ITEMTMsg cltWrkItemTMsg = NFDL0020Query.findByKeyForCltWrkItem(glblCmpyCd, dcMsg.cltWrkItemCd_DD.getValue());
        if (cltWrkItemTMsg != null) {
            cltStrgyWrkItemTrxtmsg.cltWrkCatgCd.setValue(cltWrkItemTMsg.cltWrkCatgCd.getValue());
            cltStrgyWrkItemTrxtmsg.cltWrkCatgNm.setValue(cltWrkItemTMsg.cltWrkCatgNm.getValue());
        }
        wrkTMsgList.add(cltStrgyWrkItemTrxtmsg);

        return wrkTMsgList;
    }
    // END 2018/05/11 J.Kim [QC#21426,ADD]

    // START 2018/05/16 [QC#24329,ADD]
    /**
     * @param bizMsg NFDL0020CMsg
     * @param globalMsg NFDL0020SMsg
     * @param glblCmpyCd String
     */
    public static void getStatement(NFDL0020CMsg bizMsg, NFDL0020SMsg globalMsg, String glblCmpyCd) {

        ZYPTableUtil.clear(globalMsg.J);

        Map<String, Object> ssmMap = new HashMap<String, Object>();
        ssmMap.put("glblCmpyCd", glblCmpyCd);
        ssmMap.put("dsAcctNum", bizMsg.dsAcctNum_H.getValue());
        ssmMap.put("billToCustCd", bizMsg.billToCustCd_H.getValue());
        ssmMap.put("stmtBatId", NFDL0020Constant.STMT_BAT_ID);
        ssmMap.put("rowNum", String.valueOf(globalMsg.J.length() + 1));

        S21SsmEZDResult ssmResult = NFDL0020Query.getInstance().getStatement(ssmMap, globalMsg);

        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > globalMsg.J.length()) {
                queryResCnt = globalMsg.J.length();
                // START 2022/12/14 Y.Mochida [QC#60683,MOD]
                bizMsg.setMessageInfo("NZZM0009W", new String[]{"Statement"});
                // END 2022/12/14 Y.Mochida [QC#60683,MOD]
            }

            int i;
            for (i = 0; i < globalMsg.J.getValidCount(); i++) {
                if (i < bizMsg.J.length()) {
                    EZDMsg.copy(globalMsg.J.no(i), null, bizMsg.J.no(i), null);
                } else {
                    break;
                }
            }

            bizMsg.J.setValidCount(i);
            bizMsg.xxPageShowFromNum_J.setValue(1);
            bizMsg.xxPageShowToNum_J.setValue(bizMsg.J.getValidCount());
            bizMsg.xxPageShowOfNum_J.setValue(queryResCnt);

        } else {
            bizMsg.H.setValidCount(0);
            bizMsg.xxPageShowFromNum_J.setValue(1);
            bizMsg.xxPageShowToNum_J.setValue(bizMsg.J.getValidCount());
            bizMsg.xxPageShowOfNum_J.setValue(0);
        }
    }

    /**
     * @param bizMsg NFDL0020CMsg
     * @param globalMsg NFDL0020SMsg
     * @param glblCmpyCd String
     */
    public static void writeStatementCsvFile(NFDL0020CMsg bizMsg, NFDL0020SMsg globalMsg, String glblCmpyCd) {

        NFDL0020F01FMsg fMsg = new NFDL0020F01FMsg();
        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(NFDL0020Constant.STMT_CSV_NAME), NFDL0020Constant.CSV);
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);
        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(bizMsg));

        // write header
        final String[] csvHeader = new String[] {"Statement#", "Statement Date", "Bill To", "Bill To Name", "Address", "Current", "Over due 1-30 Days", "Overdue 31-60 Days", "Overdue 61-90 Days", "Overdue 90 Days", "Total Balance" };
        csvOutFile.writeHeader(csvHeader, fMsg, ZYPGUITableColumn.getColOrder(bizMsg));

        Map<String, Object> ssmMap = new HashMap<String, Object>();
        ssmMap.put("glblCmpyCd", glblCmpyCd);
        ssmMap.put("dsAcctNum", bizMsg.dsAcctNum_H.getValue());
        ssmMap.put("billToCustCd", bizMsg.billToCustCd_H.getValue());
        ssmMap.put("stmtBatId", NFDL0020Constant.STMT_BAT_ID);
        // START 2018/10/30 J.Kim [QC#28937,MOD]
        //ssmMap.put("rowNum", String.valueOf(NFDL0020Constant.CSV_LIMIT_COUNT + 1));
        //
        //S21SsmEZDResult ssmResult = NFDL0020Query.getInstance().getStatement(ssmMap, globalMsg);
        //if (ssmResult.isCodeNormal()) {
        //
        //    if (globalMsg.J.getValidCount() >= NFDL0020Constant.CSV_LIMIT_COUNT) {
        //        bizMsg.setMessageInfo(NFDL0020Constant.NZZM0001W);
        //    }
        //
        //    // write contents
        //    for (int i = 0; i < globalMsg.J.getValidCount(); i++) {
        //        EZDMsg.copy(globalMsg.J.no(i), null, fMsg, null);
        //        String printDt = globalMsg.J.no(i).stmtPrintDt_J.getValue();
        //        if (ZYPCommonFunc.hasValue(printDt)) {
        //            printDt = ZYPDateUtil.convertFormat(printDt, "yyyyMMdd", "MMddyyyy", ZYPDateUtil.SEPARATOR_SLASH);
        //        }
        //        ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_J, printDt);
        //        csvOutFile.write();
        //    }
        //    csvOutFile.close();
        //} else {
        //   bizMsg.setMessageInfo("NFDM0034I");
        //}

        ResultSet rs = null;
        PreparedStatement st = null;
        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(NFDL0020Constant.FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NFDL0020Query.getInstance().getClass());

            st = ssmLLClient.createPreparedStatement("getStatement", ssmMap, execParam);
            rs = st.executeQuery();

            boolean noRecord = true;
            while (rs.next()) {
                noRecord = false;
                String printDt = rs.getString("STMT_PRINT_DT");
                if (ZYPCommonFunc.hasValue(printDt)) {
                    printDt = ZYPDateUtil.convertFormat(printDt, "yyyyMMdd", "MMddyyyy", ZYPDateUtil.SEPARATOR_SLASH);
                }
                ZYPEZDItemValueSetter.setValue(fMsg.stmtSqPk_J, rs.getBigDecimal("STMT_SQ_PK"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_J, printDt);
                ZYPEZDItemValueSetter.setValue(fMsg.billToCustCd_J, rs.getString("BILL_TO_CUST_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.scdCustNm_J, rs.getString("SCD_CUST_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.billToCustLocAddr_J, rs.getString("BILL_TO_CUST_LOC_ADDR"));
                ZYPEZDItemValueSetter.setValue(fMsg.ageCurAmt_J, rs.getBigDecimal("AGE_CUR_AMT"));
                ZYPEZDItemValueSetter.setValue(fMsg.age0130Amt_J, rs.getBigDecimal("AGE_0130_AMT"));
                ZYPEZDItemValueSetter.setValue(fMsg.age3160Amt_J, rs.getBigDecimal("AGE_3160_AMT"));
                ZYPEZDItemValueSetter.setValue(fMsg.age6190Amt_J, rs.getBigDecimal("AGE_6190_AMT"));
                ZYPEZDItemValueSetter.setValue(fMsg.ageOverAmt_J, rs.getBigDecimal("AGE_OVER_AMT"));
                ZYPEZDItemValueSetter.setValue(fMsg.balTotAmt_J, rs.getBigDecimal("BAL_TOT_AMT"));
                csvOutFile.write();
            }
            csvOutFile.close();

            if (noRecord) {
                bizMsg.setMessageInfo("NFDM0034I");
            }

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(st, rs);
        }
        // END 2018/10/30 J.Kim [QC#28937,MOD]
    }
    // END 2018/05/16 [QC#24329,ADD]

    // START 2018/05/21 S.Katsuma [QC#24793,ADD]
    /**
     * @param bizMsg NFDL0020CMsg
     * @param globalMsg NFDL0020SMsg
     * @param glblCmpyCd String
     */
    // START 2018/08/30 [QC#27603, MOD]
    /*
    public static void writeTransactiontCsvFile(NFDL0020CMsg bizMsg, NFDL0020SMsg globalMsg, String glblCmpyCd) {
        NFDL0020F02FMsg fMsg = new NFDL0020F02FMsg();
        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(NFDL0020Constant.TRAN_CSV_NAME), NFDL0020Constant.CSV);
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);
        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(bizMsg));
        fMsg.addExclusionItem("xxChkBox_A");
        // write header
        csvOutFile.writeHeader(NFDL0020Constant.TRAN_CSV_HEADER, fMsg, ZYPGUITableColumn.getColOrder(bizMsg));

        NFDL0020SMsg globalMsgBk = new NFDL0020SMsg();;
        EZDMsg.copy(globalMsg.A, null, globalMsgBk.A, null);

        S21SsmEZDResult ssmResult = queryTransaction(bizMsg, globalMsg, glblCmpyCd, String.valueOf(NFDL0020Constant.CSV_LIMIT_COUNT + 1));
        if (ssmResult.isCodeNormal()) {
            if (globalMsg.A.getValidCount() >= NFDL0020Constant.CSV_LIMIT_COUNT) {
                bizMsg.setMessageInfo("NZZM0001W");
            }

            for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
                EZDMsg.copy(globalMsg.A.no(i), null, fMsg, null);
                csvOutFile.write();
            }
            csvOutFile.close();
        } else {
            bizMsg.setMessageInfo("NFDM0034I");
        }

        EZDMsg.copy(globalMsgBk.A, null, globalMsg.A, null);
    }
    */
    public static void writeTransactiontCsvFile(NFDL0020CMsg bizMsg, NFDL0020SMsg globalMsg, String glblCmpyCd) {
        NFDL0020F02FMsg fMsg = new NFDL0020F02FMsg();
        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(NFDL0020Constant.TRAN_CSV_NAME), NFDL0020Constant.CSV);
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);
        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(bizMsg));
        fMsg.addExclusionItem("xxChkBox_A");
        // write header
        csvOutFile.writeHeader(NFDL0020Constant.TRAN_CSV_HEADER, fMsg, ZYPGUITableColumn.getColOrder(bizMsg));

        ResultSet rs = null;
        PreparedStatement stmtSelect = null;
        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(NFDL0020Constant.FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NFDL0020Query.getInstance().getClass());

            stmtSelect = ssmLLClient.createPreparedStatement("getTransaction", 
                    createParamMapForQueryTransaction(bizMsg, globalMsg, glblCmpyCd, null), execParam);
            rs = stmtSelect.executeQuery();
            boolean noRecord = true;
            while (rs.next()) {
                noRecord = false;
                writeCsvDataLine(fMsg, rs);
                csvOutFile.write();
            }
            csvOutFile.close();
            if (noRecord) {
                bizMsg.setMessageInfo("NFDM0034I");
            }
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }

    }

    private static void writeCsvDataLine(NFDL0020F02FMsg fMsg, ResultSet rs) throws SQLException {
        ZYPEZDItemValueSetter.setValue(fMsg.arCustRefNum_A, rs.getString("AR_CUST_REF_NUM_A"));
        ZYPEZDItemValueSetter.setValue(fMsg.arTrxTpDescTxt_A, rs.getString("AR_TRX_TP_DESC_TXT_A"));
        ZYPEZDItemValueSetter.setValue(fMsg.arTrxDt_A, rs.getString("AR_TRX_DT_A"));
        ZYPEZDItemValueSetter.setValue(fMsg.sellToCustCd_A, rs.getString("BILL_TO_CUST_ACCT_CD_A"));
        ZYPEZDItemValueSetter.setValue(fMsg.billToCustCd_A, rs.getString("BILL_TO_CUST_CD_A"));
        ZYPEZDItemValueSetter.setValue(fMsg.shipToCustCd_A, rs.getString("SHIP_TO_CUST_CD_A"));
        ZYPEZDItemValueSetter.setValue(fMsg.shipToLocNm_A, rs.getString("SHIP_TO_LOC_NM_A"));
        ZYPEZDItemValueSetter.setValue(fMsg.dealNetSlsAmt_A, rs.getBigDecimal("DEAL_NET_SLS_AMT_A"));
        ZYPEZDItemValueSetter.setValue(fMsg.dealFrtAmt_A, rs.getBigDecimal("DEAL_FRT_AMT_A"));
        ZYPEZDItemValueSetter.setValue(fMsg.dealTaxAmt_A, rs.getBigDecimal("DEAL_TAX_AMT_A"));
        // START 2022/11/01 T.Doi [QC#60415, ADD]
        ZYPEZDItemValueSetter.setValue(fMsg.addlChrgPrcDealAmt_A, rs.getBigDecimal("ADDL_CHRG_PRC_DEAL_AMT_A"));
        // END 2022/11/01 T.Doi [QC#60415, ADD]
        ZYPEZDItemValueSetter.setValue(fMsg.dealOrigGrsAmt_A, rs.getBigDecimal("DEAL_ORIG_GRS_AMT_A"));
        ZYPEZDItemValueSetter.setValue(fMsg.dealRmngBalGrsAmt_A, rs.getBigDecimal("DEAL_RMNG_BAL_GRS_AMT_A"));
        ZYPEZDItemValueSetter.setValue(fMsg.invDueDt_A, rs.getString("INV_DUE_DT_A"));
        ZYPEZDItemValueSetter.setValue(fMsg.daysPastDueAot_A, rs.getBigDecimal("DAYS_PAST_DUE_AOT_A"));
        ZYPEZDItemValueSetter.setValue(fMsg.dealCcyCd_A, rs.getString("DEAL_CCY_CD_A"));
        ZYPEZDItemValueSetter.setValue(fMsg.dealCltPrmsAmt_A, rs.getBigDecimal("CLT_PRMS_AMT_A"));
        ZYPEZDItemValueSetter.setValue(fMsg.dealCltDsptAmt_A, rs.getBigDecimal("CLT_DSPT_AMT_A"));
        ZYPEZDItemValueSetter.setValue(fMsg.arTrxBillFromDt_A, rs.getString("AR_TRX_BILL_FROM_DT"));
        ZYPEZDItemValueSetter.setValue(fMsg.arTrxBillThruDt_A, rs.getString("AR_TRX_BILL_THRU_DT"));
        ZYPEZDItemValueSetter.setValue(fMsg.grpInvNum_A, rs.getString("GRP_INV_NUM"));
        ZYPEZDItemValueSetter.setValue(fMsg.custCareTktNum_A, rs.getString("CUST_CARE_TKT_NUM"));
        ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem130Txt_A, rs.getString("TICKET_STATUS"));
        ZYPEZDItemValueSetter.setValue(fMsg.dsContrNum_A, rs.getString("DS_CONTR_NUM"));
        ZYPEZDItemValueSetter.setValue(fMsg.arLateFeeAmt_A, rs.getBigDecimal("AR_LATE_FEE_AMT"));
        ZYPEZDItemValueSetter.setValue(fMsg.arTrxNum_A, rs.getString("AR_TRX_NUM_A"));
        ZYPEZDItemValueSetter.setValue(fMsg.custIssPoNum_A, rs.getString("CUST_ISS_PO_NUM"));
        ZYPEZDItemValueSetter.setValue(fMsg.leaseCmpyPoNum_A, rs.getString("LEASE_CMPY_PO_NUM"));
        ZYPEZDItemValueSetter.setValue(fMsg.invFirstCmntTxt_A, rs.getString("INV_FIRST_CMNT_TXT"));
        ZYPEZDItemValueSetter.setValue(fMsg.invTpNm_A, rs.getString("INV_TP_NM"));
        ZYPEZDItemValueSetter.setValue(fMsg.dsInvTpNm_A, rs.getString("DS_INV_TP_NM"));
        // START 2022/11/01 T.Doi [QC#57088, ADD]
        ZYPEZDItemValueSetter.setValue(fMsg.svcInvChrgTpRptTxt_A, rs.getString("SVC_INV_CHRG_TP_RPT_TXT_A"));
        // END 2022/11/01 T.Doi [QC#57088, ADD]
        ZYPEZDItemValueSetter.setValue(fMsg.cltDsptDt_A, rs.getString("CLT_DSPT_DT"));
        ZYPEZDItemValueSetter.setValue(fMsg.cpoOrdNum_A, rs.getString("CPO_ORD_NUM"));
        ZYPEZDItemValueSetter.setValue(fMsg.glDt_A, rs.getString("GL_DT"));
        ZYPEZDItemValueSetter.setValue(fMsg.tocNm_A, rs.getString("TOC_NM"));
        ZYPEZDItemValueSetter.setValue(fMsg.coaBrCd_A, rs.getString("COA_BR_CD"));
        ZYPEZDItemValueSetter.setValue(fMsg.origInvNum_A, rs.getString("ORIG_INV_NUM"));
        ZYPEZDItemValueSetter.setValue(fMsg.xxInvdCnt_A, rs.getBigDecimal("CNT_AR_TRX_NUM"));
        ZYPEZDItemValueSetter.setValue(fMsg.dealCltPrmsAmt_A1, rs.getBigDecimal("DEAL_CLT_PRMS_AMT"));
        ZYPEZDItemValueSetter.setValue(fMsg.pmtTermCashDiscDescTxt_A, rs.getString("PMT_TERM_CASH_DISC_DESC_TXT"));
        ZYPEZDItemValueSetter.setValue(fMsg.xxFullNm_A, rs.getString("XX_FULL_NM_A"));
        ZYPEZDItemValueSetter.setValue(fMsg.dsCtacPntValTxt_A, rs.getString("DS_CTAC_PNT_VAL_TXT_A"));
        ZYPEZDItemValueSetter.setValue(fMsg.dsCtacPntValTxt_A1, rs.getString("DS_CTAC_PNT_VAL_TXT_A1"));
    }
    // END   2018/08/30 [QC#27603, MOD]

    /**
     * @param bizMsg NFDL0020CMsg
     * @param globalMsg NFDL0020SMsg
     * @param glblCmpyCd String
     */
    public static void writeNoteCsvFile(NFDL0020CMsg bizMsg, NFDL0020SMsg globalMsg, String glblCmpyCd) {
        NFDL0020F03FMsg fMsg = new NFDL0020F03FMsg();
        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(NFDL0020Constant.NOTE_CSV_NAME), NFDL0020Constant.CSV);
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);
        // write header
        csvOutFile.writeHeader(NFDL0020Constant.NOTE_CSV_HEADER);

        // START 2018/10/30 J.Kim [QC#28937, MOD]
        // NFDL0020SMsg globalMsgBk = new NFDL0020SMsg();;
        // EZDMsg.copy(globalMsg.F, null, globalMsgBk.F, null);
        // S21SsmEZDResult ssmResult = queryCollectionNote(bizMsg, globalMsg, glblCmpyCd, String.valueOf(NFDL0020Constant.CSV_LIMIT_COUNT + 1));
        //
        // if (ssmResult.isCodeNormal()) {
        //    if (globalMsg.F.getValidCount() >= NFDL0020Constant.CSV_LIMIT_COUNT) {
        //        bizMsg.setMessageInfo(NFDL0020Constant.NZZM0001W);
        //    }
        //    // write contents
        //    for (int i = 0; i < globalMsg.F.getValidCount(); i++) {
        //        EZDMsg.copy(globalMsg.F.no(i), null, fMsg, null);
        //        csvOutFile.write();
        //    }
        //    csvOutFile.close();
        // } else {
        //    bizMsg.setMessageInfo("NFDM0034I");
        // }
        //
        // EZDMsg.copy(globalMsgBk.F, null, globalMsg.F, null);

        ResultSet rs = null;
        PreparedStatement st = null;
        try {

            // Start 2022/12/12 S.Fujita [QC#60406, MOD]
//            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
//            execParam.setFetchSize(NFDL0020Constant.FETCH_SIZE);
//            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
//            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
//            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
//            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NFDL0020Query.getInstance().getClass());
//
//            st = ssmLLClient.createPreparedStatement("getCollectionNote", queryCollectionNote(bizMsg, globalMsg, glblCmpyCd, null), execParam);
//            rs = st.executeQuery();

            List<Map<String, Object>> cltNoteList = (List<Map<String, Object>>) queryCollectionNote(bizMsg, globalMsg, glblCmpyCd).getResultObject();
            // End 2022/12/12 S.Fujita [QC#60406, MOD]
            
            boolean noRecord = true;
            // Start 2022/12/12 S.Fujita [QC#60406, MOD]
//            while (rs.next()) {
//                noRecord = false;
//                ZYPEZDItemValueSetter.setValue(fMsg.cltNoteDtlPk_FD, rs.getBigDecimal("CLT_NOTE_DTL_PK_FD"));
//                ZYPEZDItemValueSetter.setValue(fMsg.cratTsDplyTxt_FD, rs.getString("CRAT_TS_DPLY_TXT_FD"));
//                ZYPEZDItemValueSetter.setValue(fMsg.cltNoteTpDescTxt_FD, rs.getString("CLT_NOTE_TP_DESC_TXT"));
//                ZYPEZDItemValueSetter.setValue(fMsg.xxMlBodyTxt_FD, rs.getString("DTL_NOTE_TXT_FD"));
//                ZYPEZDItemValueSetter.setValue(fMsg.xxPsnNm_FD, rs.getString("XX_PSN_NM_FD"));
                
            for (Map<String, Object> cltNote : cltNoteList) {
                ZYPEZDItemValueSetter.setValue(fMsg.cltNoteDtlPk_FD, (BigDecimal) cltNote.get("CLT_NOTE_DTL_PK_FD"));
                ZYPEZDItemValueSetter.setValue(fMsg.cratTsDplyTxt_FD, (String) cltNote.get("CRAT_TS_DPLY_TXT_FD"));
                ZYPEZDItemValueSetter.setValue(fMsg.cltNoteTpDescTxt_FD, (String) cltNote.get("CLT_NOTE_TP_DESC_TXT"));
                //TODO
                if(cltNote.get("DTL_NOTE_CLOD") != null){
                    ZYPEZDItemValueSetter.setValue(fMsg.xxMlBodyTxt_FD, clobToString((Clob) cltNote.get("DTL_NOTE_CLOD")));
                } else{
                    ZYPEZDItemValueSetter.setValue(fMsg.xxMlBodyTxt_FD, (String) cltNote.get("DTL_NOTE_TXT_FD"));
                }
                ZYPEZDItemValueSetter.setValue(fMsg.xxPsnNm_FD, (String) cltNote.get("XX_PSN_NM_FD"));
            // End 2022/12/12 S.Fujita [QC#60406, MOD]
                csvOutFile.write();
            }
            csvOutFile.close();
            if (noRecord) {
                bizMsg.setMessageInfo("NFDM0034I");
            }
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(st, rs);
        }
        // END 2018/10/30 J.Kim [QC#28937, MOD]
    }

    /**
     * @param bizMsg NFDL0020CMsg
     * @param globalMsg NFDL0020SMsg
     * @param glblCmpyCd String
     */
    public static void writeTaskCsvFile(NFDL0020CMsg bizMsg, NFDL0020SMsg globalMsg, String glblCmpyCd) {
        NFDL0020F04FMsg fMsg = new NFDL0020F04FMsg();
        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(NFDL0020Constant.TASK_CSV_NAME), NFDL0020Constant.CSV);
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);
        // write header
        csvOutFile.writeHeader(NFDL0020Constant.TASK_CSV_HEADER);

        // START 2018/10/30 J.Kim [QC#28937, MOD]
        // NFDL0020SMsg globalMsgBk = new NFDL0020SMsg();;
        // EZDMsg.copy(globalMsg.G, null, globalMsgBk.G, null);
        // S21SsmEZDResult ssmResult = queryCollectionTask(bizMsg, globalMsg, glblCmpyCd, String.valueOf(NFDL0020Constant.CSV_LIMIT_COUNT + 1));
        // if (ssmResult.isCodeNormal()) {
        //    if (globalMsg.G.getValidCount() >= NFDL0020Constant.CSV_LIMIT_COUNT) {
        //        bizMsg.setMessageInfo(NFDL0020Constant.NZZM0001W);
        //    }
        //
        //    // write contents
        //    for (int i = 0; i < globalMsg.G.getValidCount(); i++) {
        //        EZDMsg.copy(globalMsg.G.no(i), null, fMsg, null);
        //        csvOutFile.write();
        //    }
        //   csvOutFile.close();
        // } else {
        //    bizMsg.setMessageInfo("NFDM0034I");
        // }
        // EZDMsg.copy(globalMsgBk.G, null, globalMsg.G, null);

        ResultSet rs = null;
        PreparedStatement st = null;
        try {

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(NFDL0020Constant.FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NFDL0020Query.getInstance().getClass());

            st = ssmLLClient.createPreparedStatement("getCollectionTask", queryCollectionTask(bizMsg, globalMsg, glblCmpyCd, null), execParam);
            rs = st.executeQuery();

            boolean noRecord = true;
            while (rs.next()) {
                noRecord = false;
                ZYPEZDItemValueSetter.setValue(fMsg.cltTaskPk_GD, rs.getBigDecimal("CLT_TASK_PK_GD"));
                ZYPEZDItemValueSetter.setValue(fMsg.cltTaskRwsdDt_GD, rs.getString("CLT_TASK_RWSD_DT_GD"));
                ZYPEZDItemValueSetter.setValue(fMsg.cltTaskSubjTxt_GD, rs.getString("CLT_TASK_SUBJ_TXT_GD"));
                ZYPEZDItemValueSetter.setValue(fMsg.cltTaskTpDescTxt_GD, rs.getString("CLT_TASK_TP_DESC_TXT_GD"));
                ZYPEZDItemValueSetter.setValue(fMsg.cltTaskStsDescTxt_GD, rs.getString("CLT_TASK_STS_DESC_TXT_GD"));
                ZYPEZDItemValueSetter.setValue(fMsg.cltTaskPrtyDescTxt_GD, rs.getString("CLT_TASK_PRTY_DESC_TXT_GD"));
                ZYPEZDItemValueSetter.setValue(fMsg.cltPsnNm_G3, rs.getString("XX_PSN_NM_G3"));
                csvOutFile.write();
            }
            csvOutFile.close();
            if (noRecord) {
                bizMsg.setMessageInfo("NFDM0034I");
            }
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(st, rs);
        }
        // END 2018/10/30 J.Kim [QC#28937, MOD]
    }

    /**
     * @param bizMsg NFDL0020CMsg
     * @param globalMsg NFDL0020SMsg
     * @param glblCmpyCd String
     */
    // START 2018/10/03 J.Kim [QC#28575, MOD]
    //public static void writeContractCsvFile(NFDL0020CMsg bizMsg, NFDL0020SMsg globalMsg, String glblCmpyCd) {
    //    NFDL0020F05FMsg fMsg = new NFDL0020F05FMsg();
    //    bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(NFDL0020Constant.CONTR_CSV_NAME), NFDL0020Constant.CSV);
    //    ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);
    //    fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(bizMsg));
    //    // write header
    //    csvOutFile.writeHeader(NFDL0020Constant.CONTR_CSV_HEADER, fMsg, ZYPGUITableColumn.getColOrder(bizMsg));
    //
    //    NFDL0020SMsg globalMsgBk = new NFDL0020SMsg();
    //    EZDMsg.copy(globalMsg.B, null, globalMsgBk.B, null);
    //
    //    S21SsmEZDResult ssmResult = queryCollectionContract(bizMsg, globalMsg, glblCmpyCd, String.valueOf(NFDL0020Constant.CSV_LIMIT_COUNT + 1));
    //
    //    if (ssmResult.isCodeNormal()) {
    //        if (globalMsg.B.getValidCount() >= NFDL0020Constant.CSV_LIMIT_COUNT) {
    //            bizMsg.setMessageInfo(NFDL0020Constant.NZZM0001W);
    //        }
    //
    //        // write contents
    //        for (int i = 0; i < globalMsg.B.getValidCount(); i++) {
    //            EZDMsg.copy(globalMsg.B.no(i), null, fMsg, null);
    //            csvOutFile.write();
    //        }
    //        csvOutFile.close();
    //    } else {
    //        bizMsg.setMessageInfo("NFDM0034I");
    //    }
    //
    //    EZDMsg.copy(globalMsgBk.B, null, globalMsg.B, null);
    //}

    public static void writeContractCsvFile(NFDL0020CMsg bizMsg, NFDL0020SMsg globalMsg, String glblCmpyCd) {

        NFDL0020F05FMsg fMsg = new NFDL0020F05FMsg();
        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(NFDL0020Constant.CONTR_CSV_NAME), NFDL0020Constant.CSV);
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);
        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(bizMsg));
        // write header
        csvOutFile.writeHeader(NFDL0020Constant.CONTR_CSV_HEADER, fMsg, ZYPGUITableColumn.getColOrder(bizMsg));

        ResultSet rs = null;
        PreparedStatement st = null;
        try {

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(NFDL0020Constant.FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NFDL0020Query.getInstance().getClass());

            st = ssmLLClient.createPreparedStatement("getCollectionContract", createParamMapForQueryCollectionContract(bizMsg, globalMsg, glblCmpyCd, null), execParam);
            rs = st.executeQuery();

            boolean noRecord = true;
            while (rs.next()) {
                noRecord = false;
                writeCsvDataLineForCollectionContract(fMsg, rs);
                csvOutFile.write();
            }
            csvOutFile.close();
            if (noRecord) {
                bizMsg.setMessageInfo("NFDM0034I");
            }
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(st, rs);
        }
    }

    private static void writeCsvDataLineForCollectionContract(NFDL0020F05FMsg fMsg, ResultSet rs) throws SQLException {

        ZYPEZDItemValueSetter.setValue(fMsg.dsContrNum_BD, rs.getString("DS_CONTR_NUM_BD"));
        ZYPEZDItemValueSetter.setValue(fMsg.svcContrRefCmntTxt_BD, rs.getString("SVC_CONTR_REF_CMNT_TXT_BD"));
        ZYPEZDItemValueSetter.setValue(fMsg.ccyCd_BD, rs.getString("CCY_CD_BD"));
        ZYPEZDItemValueSetter.setValue(fMsg.contrVrsnEffFromDt_BD, rs.getString("CONTR_VRSN_EFF_FROM_DT_BD"));
        ZYPEZDItemValueSetter.setValue(fMsg.contrVrsnEffThruDt_BD, rs.getString("CONTR_VRSN_EFF_THRU_DT_BD"));
        ZYPEZDItemValueSetter.setValue(fMsg.contrCloDt_BD, rs.getString("CONTR_CLO_DT_BD"));
        ZYPEZDItemValueSetter.setValue(fMsg.rnwEffFromDt_BD, rs.getString("RNW_EFF_FROM_DT"));
        ZYPEZDItemValueSetter.setValue(fMsg.xxDt10Dt_B1, rs.getString("HLD_CANC_DT"));
        ZYPEZDItemValueSetter.setValue(fMsg.xxDt10Dt_B2, rs.getString("HLD_CRT_DT"));
        ZYPEZDItemValueSetter.setValue(fMsg.stsMemoTxt_B1, rs.getString("STS_MEMO_TXT_B1"));
        ZYPEZDItemValueSetter.setValue(fMsg.contrHldFlg_BD, rs.getString("CONTR_HLD_FLG_BD"));
        ZYPEZDItemValueSetter.setValue(fMsg.xxDt10Dt_B3, rs.getString("HLD_REL_DT_BD"));
        ZYPEZDItemValueSetter.setValue(fMsg.stsMemoUpdFullPsnNm_BD, rs.getString("HLD_REL_PSN"));
        ZYPEZDItemValueSetter.setValue(fMsg.stsMemoTxt_B2, rs.getString("STS_MEMO_TXT_B2"));
    }
    // END 2018/10/03 J.Kim [QC#28575, MOD]

    /**
     * @param bizMsg Business Component Interface Message
     * @param globalMsg Global area information
     * @param glblCmpyCd Global Company Code
     */
    public static S21SsmEZDResult queryTransaction(NFDL0020CMsg bizMsg, NFDL0020SMsg globalMsg, String glblCmpyCd) {
        return queryTransaction(bizMsg, globalMsg, glblCmpyCd, String.valueOf(globalMsg.A.length() + 1));
    }

    /**
     * @param bizMsg Business Component Interface Message
     * @param globalMsg Global area information
     * @param glblCmpyCd Global Company Code
     * @param rownum Row Number
     */
    public static S21SsmEZDResult queryTransaction(NFDL0020CMsg bizMsg, NFDL0020SMsg globalMsg, String glblCmpyCd, String rownum) {
        // START 2018/08/30 [QC#27603, MOD]
        // Map<String, Object> ssmMap = new HashMap<String, Object>();
        // ssmMap.put("glblCmpyCd", glblCmpyCd);
        // ssmMap.put("dsAcctNum", bizMsg.dsAcctNum_H.getValue());
        // ssmMap.put("billToCustCd", bizMsg.billToCustCd_H.getValue());
        // ssmMap.put("cMsg", bizMsg);
        // ssmMap.put("rowNum", rownum);
        // ssmMap.put("salesDt", ZYPDateUtil.getSalesDate());
        // ssmMap.put("rcpTpCd", AR_TRX_TP.RECEIPT);
        // ssmMap.put("applied", AR_CASH_APPLY_STS.APPLIED);
        // ssmMap.put("void", AR_CASH_APPLY_STS.VOID);
        // // START 2018/06/05 [QC#26107, ADD]
        // ssmMap.put("arTrxTpCd", AR_TRX_TP.DEDUCTION);
        // // END   2018/06/05 [QC#26107, ADD]
        // if (!ZYPCommonFunc.hasValue(bizMsg.arTrxTpCd_AH)) {
        //     String arTrxTpCd = ZYPCodeDataUtil.getVarCharConstValue("NFDL0020_CLASS_TRX_TP", glblCmpyCd);
        //     if (ZYPCommonFunc.hasValue(arTrxTpCd)) {
        //         String[] arTrxTpCdList = arTrxTpCd.split(",");
        //         ssmMap.put("arTrxTpCdList", arTrxTpCdList);
        //     } else {
        //         ssmMap.put("arTrxTpCdList", null);
        //     }
        // }
        // ssmMap.put("cltDsptStsApproved", CLT_DSPT_STS.APPROVED);
        // ssmMap.put("invBolLineNum", NFDL0020Constant.INIT_INV_BOL_LINE_NUM);
        // 
        // // START 2018/07/30 Y.Matsui [QC#27081,ADD]
        // ssmMap.put("maxDt", NFDL0020Constant.MAX_DT);
        // ssmMap.put("ctacTpAccountPayable", CTAC_TP.ACCOUNT_PAYABLE);
        // ssmMap.put("ctacTpCreditCollections", CTAC_TP.CREDIT_OR_COLLECTONS);
        // ssmMap.put("dsCtacPntTpPhoneWork", DS_CTAC_PNT_TP.PHONE_WORK);
        // ssmMap.put("dsCtacPntTpEmailWork", DS_CTAC_PNT_TP.EMAIL_WORK);
        // // END   2018/07/30 Y.Matsui [QC#27081,ADD]
        Map<String, Object> ssmMap = createParamMapForQueryTransaction(bizMsg, globalMsg, glblCmpyCd, rownum);
        // END   2018/08/30 [QC#27603, MOD]

        return NFDL0020Query.getInstance().getTransaction(ssmMap, globalMsg);
    }

    // START 2018/08/30 [QC#27603, ADD]
    /**
     * createParamMapForQueryTransaction
     * 
     * @param bizMsg Business Component Interface Message
     * @param globalMsg Global area information
     * @param glblCmpyCd Global Company Code
     * @param rownum Row Number
     */
    private static Map<String, Object> createParamMapForQueryTransaction(NFDL0020CMsg bizMsg, NFDL0020SMsg globalMsg, String glblCmpyCd, String rownum) {
        Map<String, Object> ssmMap = new HashMap<String, Object>();
        ssmMap.put("glblCmpyCd", glblCmpyCd);
        ssmMap.put("dsAcctNum", bizMsg.dsAcctNum_H.getValue());
        ssmMap.put("billToCustCd", bizMsg.billToCustCd_H.getValue());
        ssmMap.put("cMsg", bizMsg);
        ssmMap.put("rowNum", rownum);
        ssmMap.put("salesDt", ZYPDateUtil.getSalesDate());
        ssmMap.put("rcpTpCd", AR_TRX_TP.RECEIPT);
        ssmMap.put("applied", AR_CASH_APPLY_STS.APPLIED);
        ssmMap.put("void", AR_CASH_APPLY_STS.VOID);
        // START 2021/12/23 K.Suzuki [QC#55788-1,ADD]
        ssmMap.put("unapplied", AR_CASH_APPLY_STS.UNAPPLIED);
        ssmMap.put("partial", AR_CASH_APPLY_STS.PARTIAL);
        ssmMap.put("arApplyTpCdADJ", AR_APPLY_TP.ADJUSTMENT);
        ssmMap.put("arAdjTrxTpCdACC", AR_ADJ_TRX_TP.ON_ACCOUNT);
        // END   2021/12/23 K.Suzuki [QC#55788-1,ADD]
        // START 2018/06/05 [QC#26107, ADD]
        ssmMap.put("arTrxTpCd", AR_TRX_TP.DEDUCTION);
        // END   2018/06/05 [QC#26107, ADD]
        // START 2019/08/15 [QC#52654, ADD]
        ssmMap.put("arTrxTpCdACC", AR_TRX_TP.ON_ACCOUNT);
        // END   2018/06/05 [QC#52654, ADD]
        // START 2022/11/01 T.Doi [QC#57088, ADD]
        ssmMap.put("nfc004PrimSvcChrg", NFDL0020Constant.NFC004_PRIM_SVC_CHRG);
        // END 2022/11/01 T.Doi [QC#57088, ADD]
        // START 2022/11/01 T.Doi [QC#60415, ADD]
        ssmMap.put("svcInvChrgTpAC", SVC_INV_CHRG_TP.ADDITIONAL_CHARGE);
        // END 2022/11/01 T.Doi [QC#60415, ADD]
        // START 2021/12/23 K.Suzuki [QC#55788-1,ADD]
        ssmMap.put("isReceiptFlg", ZYPConstant.FLG_OFF_N);
        // END   2021/12/23 K.Suzuki [QC#55788-1,ADD]
        if (!ZYPCommonFunc.hasValue(bizMsg.arTrxTpCd_AH)) {
            String arTrxTpCd = ZYPCodeDataUtil.getVarCharConstValue("NFDL0020_CLASS_TRX_TP", glblCmpyCd);
            if (ZYPCommonFunc.hasValue(arTrxTpCd)) {
                String[] arTrxTpCdList = arTrxTpCd.split(",");
                ssmMap.put("arTrxTpCdList", arTrxTpCdList);
                // START 2021/12/23 K.Suzuki [QC#55788-1,ADD]
                if (Arrays.asList(arTrxTpCdList).contains(AR_TRX_TP.RECEIPT)) {
                    ssmMap.put("isReceiptFlg", ZYPConstant.FLG_ON_Y);
                }
                // END   2021/12/23 K.Suzuki [QC#55788-1,ADD]
            } else {
                ssmMap.put("arTrxTpCdList", null);
            }
        }
        // START 2021/12/23 K.Suzuki [QC#55788-1,ADD]
        if (ZYPCommonFunc.hasValue(bizMsg.arTrxTpCd_AH) && AR_TRX_TP.RECEIPT.equals(bizMsg.arTrxTpCd_AH.getValue())) {
            ssmMap.put("isReceiptFlg", ZYPConstant.FLG_ON_Y);
        }
        // END   2021/12/23 K.Suzuki [QC#55788-1,ADD]
        ssmMap.put("cltDsptStsApproved", CLT_DSPT_STS.APPROVED);
        ssmMap.put("invBolLineNum", NFDL0020Constant.INIT_INV_BOL_LINE_NUM);

        // START 2018/07/30 Y.Matsui [QC#27081,ADD]
        ssmMap.put("maxDt", NFDL0020Constant.MAX_DT);
        ssmMap.put("ctacTpAccountPayable", CTAC_TP.ACCOUNT_PAYABLE);
        ssmMap.put("ctacTpCreditCollections", CTAC_TP.CREDIT_OR_COLLECTONS);
        ssmMap.put("dsCtacPntTpPhoneWork", DS_CTAC_PNT_TP.PHONE_WORK);
        ssmMap.put("dsCtacPntTpEmailWork", DS_CTAC_PNT_TP.EMAIL_WORK);
        // END   2018/07/30 Y.Matsui [QC#27081,ADD]

        // START 2018/08/31 [QC#27603, ADD]
        ssmMap.put("istlPmtTermFlg", ZYPConstant.FLG_ON_Y);
        // END   2018/08/31 [QC#27603, ADD]

        return ssmMap;
    }
    // END   2018/08/30 [QC#27603, ADD]

    /**
     * @param bizMsg Business Component Interface Message
     * @param globalMsg Global area information
     * @param glblCmpyCd Global Company Code
     */
    public static S21SsmEZDResult queryCollectionNote(NFDL0020CMsg bizMsg, NFDL0020SMsg globalMsg, String glblCmpyCd) {
        // START 2018/10/30 J.Kim [QC#28937,MOD]
        // return queryCollectionNote(bizMsg, globalMsg, glblCmpyCd, String.valueOf(globalMsg.F.length() + 1));
        Map<String, Object>  ssmMap = queryCollectionNote(bizMsg, globalMsg, glblCmpyCd, String.valueOf(globalMsg.F.length() + 1));
        return NFDL0020Query.getInstance().getCollectionNote(ssmMap, globalMsg);
        // END 2018/10/30 J.Kim [QC#28937,MOD]
    }

    /**
     * @param bizMsg Business Component Interface Message
     * @param globalMsg Global area information
     * @param glblCmpyCd Global Company Code
     * @param rownum Row Number
     */
    public static Map<String, Object> queryCollectionNote(NFDL0020CMsg bizMsg, NFDL0020SMsg globalMsg, String glblCmpyCd, String rownum) {
        Map<String, Object> ssmMap = new HashMap<String, Object>();
        ssmMap.put("glblCmpyCd", glblCmpyCd);
        ssmMap.put("cltAcctCd", bizMsg.dsAcctNum_H.getValue());
        ssmMap.put("cltAcctTpCd", "20");
        ssmMap.put("cratDtFrom", bizMsg.cratDt_FF.getValue());
        ssmMap.put("cratDtTo", bizMsg.cratDt_FT.getValue());
        // START 2018/06/21 [QC#25080, MOD]
        // ssmMap.put("arCltNoteTpCd", bizMsg.arCltNoteTpCd_FS.getValue());
        ssmMap.put("cltNoteTpCd", bizMsg.cltNoteTpCd_FS.getValue());
        // ssmMap.put("dtlNoteTxt", bizMsg.xxMlBodyTxt_FS.getValue());
        ssmMap.put("dtlNoteTxt", bizMsg.xxMlBodyTxt_FS.getValue().toUpperCase());
        // END   2018/06/21 [QC#25080, MOD]
        // START 2018/07/09 J.Kim [QC#26801,ADD]
        ssmMap.put("attFlg", bizMsg.xxChkBox_FH.getValue());
        ssmMap.put("businessID", NFDL0020Constant.BIZ_ID);
        // END 2018/07/09 J.Kim [QC#26801,ADD]
        ssmMap.put("rowNum", rownum);
        // START 2018/10/30 J.Kim [QC#28937,MOD]
        // return NFDL0020Query.getInstance().getCollectionNote(ssmMap, globalMsg);
        return ssmMap;
        // END 2018/10/30 J.Kim [QC#28937,MOD]
    }

    /**
     * @param bizMsg Business Component Interface Message
     * @param globalMsg Global area information
     * @param glblCmpyCd Global Company Code
     */
    public static S21SsmEZDResult queryCollectionTask(NFDL0020CMsg bizMsg, NFDL0020SMsg globalMsg, String glblCmpyCd) {
        // START 2018/10/30 J.Kim [QC#28937,MOD]
        //return queryCollectionTask(bizMsg, globalMsg, glblCmpyCd, String.valueOf(globalMsg.G.length() + 1));
        Map<String, Object> ssmMap =  queryCollectionTask(bizMsg, globalMsg, glblCmpyCd, String.valueOf(globalMsg.G.length() + 1));
        return NFDL0020Query.getInstance().getCollectionTask(ssmMap, globalMsg);
        // END 2018/10/30 J.Kim [QC#28937,MOD]
    }

    /**
     * @param bizMsg Business Component Interface Message
     * @param globalMsg Global area information
     * @param glblCmpyCd Global Company Code
     * @param rownum Row Number
     */
    public static Map<String, Object> queryCollectionTask(NFDL0020CMsg bizMsg, NFDL0020SMsg globalMsg, String glblCmpyCd, String rownum) {
        Map<String, Object> ssmMap = new HashMap<String, Object>();
        ssmMap.put("glblCmpyCd", glblCmpyCd);
        ssmMap.put("cltAcctCd", bizMsg.dsAcctNum_H.getValue());
        ssmMap.put("cltAcctTpCd", "20");
        // START 2018/07/25 [QC#25767, MOD]
        ssmMap.put("cltTaskRwsdDtFrom", bizMsg.cltTaskRwsdDt_GF.getValue());
        ssmMap.put("cltTaskRwsdDtTo", bizMsg.cltTaskRwsdDt_GT.getValue());
        // END   2018/07/25 [QC#25767, MOD]
        ssmMap.put("cltTaskSubjTxt", bizMsg.cltTaskSubjTxt_GS.getValue());
        ssmMap.put("cltTaskTpCd", bizMsg.cltTaskTpCd_GS.getValue());
        ssmMap.put("rowNum", rownum);
        // START 2018/10/30 J.Kim [QC#28937,MOD]
        // return NFDL0020Query.getInstance().getCollectionTask(ssmMap, globalMsg);
        return ssmMap;
        // END 2018/10/30 J.Kim [QC#28937,MOD]
    }

    /**
     * @param bizMsg Business Component Interface Message
     * @param globalMsg Global area information
     * @param glblCmpyCd Global Company Code
     */
    public static S21SsmEZDResult queryCollectionContract(NFDL0020CMsg bizMsg, NFDL0020SMsg globalMsg, String glblCmpyCd) {
        return queryCollectionContract(bizMsg, globalMsg, glblCmpyCd, String.valueOf(globalMsg.B.length() + 1));
    }

    /**
     * @param bizMsg Business Component Interface Message
     * @param globalMsg Global area information
     * @param glblCmpyCd Global Company Code
     * @param rownum Row Number
     */
    public static S21SsmEZDResult queryCollectionContract(NFDL0020CMsg bizMsg, NFDL0020SMsg globalMsg, String glblCmpyCd, String rownum) {
        // START 2018/10/03 J.Kim [QC#28575, MOD]
        // Map<String, Object> ssmMap = new HashMap<String, Object>();
        // ssmMap.put("glblCmpyCd", glblCmpyCd);
        // ssmMap.put("dsAcctNum", bizMsg.dsAcctNum_H.getValue());
        // ssmMap.put("billToCustCd", bizMsg.billToCustCd_H.getValue());
        // ssmMap.put("rowNum", rownum);
        // ssmMap.put("dsContrToStsCd_Canc", DS_CONTR_CTRL_STS.CANCELLED);
        // ssmMap.put("dsContrToStsCd_Hold", DS_CONTR_CTRL_STS.HOLD);
        // ssmMap.put("dsContrTrkTpCdIsHeader", DS_CONTR_TRK_TP.CONTRACT_HEADER);
        // ssmMap.put("ezinAplId_NFDB005001", "NFDB005001");
        // ssmMap.put("dsContrStsCd", DS_CONTR_STS.ORDER);
        // String[] dsContrDtlTpCdList = new String[] {DS_CONTR_DTL_TP.FLEET, DS_CONTR_DTL_TP.AGGREGATE, DS_CONTR_DTL_TP.PRICE_OF_MODEL_LEVEL};
        // smMap.put("dsContrDtlTpCdList", dsContrDtlTpCdList);
        // ssmMap.put("dsContrTpCd", bizMsg.dsContrTpCd_BH.getValue());
        Map<String, Object> ssmMap = createParamMapForQueryCollectionContract(bizMsg, globalMsg, glblCmpyCd, rownum);
        // END 2018/10/03 J.Kim [QC#28575, MOD]
        return NFDL0020Query.getInstance().getCollectionContract(ssmMap, globalMsg);
    }

    private static  Map<String, Object> createParamMapForQueryCollectionContract(NFDL0020CMsg bizMsg, NFDL0020SMsg globalMsg, String glblCmpyCd, String rownum) {
        Map<String, Object> ssmMap = new HashMap<String, Object>();
        ssmMap.put("glblCmpyCd", glblCmpyCd);
        ssmMap.put("dsAcctNum", bizMsg.dsAcctNum_H.getValue());
        ssmMap.put("billToCustCd", bizMsg.billToCustCd_H.getValue());
        ssmMap.put("rowNum", rownum);
        ssmMap.put("dsContrToStsCd_Canc", DS_CONTR_CTRL_STS.CANCELLED);
        ssmMap.put("dsContrToStsCd_Hold", DS_CONTR_CTRL_STS.HOLD);
        ssmMap.put("dsContrTrkTpCdIsHeader", DS_CONTR_TRK_TP.CONTRACT_HEADER);
        ssmMap.put("ezinAplId_NFDB005001", "NFDB005001");
        ssmMap.put("dsContrStsCd", DS_CONTR_STS.ORDER);
        String[] dsContrDtlTpCdList = new String[] {DS_CONTR_DTL_TP.FLEET, DS_CONTR_DTL_TP.AGGREGATE, DS_CONTR_DTL_TP.PRICE_OF_MODEL_LEVEL};
        ssmMap.put("dsContrDtlTpCdList", dsContrDtlTpCdList);
        ssmMap.put("dsContrTpCd", bizMsg.dsContrTpCd_BH.getValue());
        return ssmMap;
    }
    // END 2018/05/21 S.Katsuma [QC#24793,ADD]

    // START 2018/06/21 [QC#25080, ADD]
    public static String addMonths(String yyyyMMdd, int months) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(yyyyMMdd.substring(0, 4)), Integer.parseInt(yyyyMMdd.substring(4, 6)) - 1, Integer.parseInt(yyyyMMdd.substring(6, 8)));
        calendar.add(Calendar.MONTH, months);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        return dateFormat.format(calendar.getTime());
    }
    // END   2018/06/21 [QC#25080, ADD]

    // START 2021/05/25 G.Delgado [QC#58704,ADD]
    /**
     * Check work item request dates and do necessary processing
     * @param glblCmpyCd String
     * @param salesDt String
     * @param dcMsg NFDL0020_DCMsg
     */
    public static void checkWrkItemRequestDates(String glblCmpyCd, String salesDt, NFDL0020_DCMsg dcMsg) {
        if (ZYPCommonFunc.hasValue(dcMsg.cltWrkItemRwsdDt_DD)) {
            // Request Start Date earlier or equal to Sales Date
            if (ZYPDateUtil.compare(dcMsg.cltWrkItemRwsdDt_DD.getValue(), salesDt) <= 0) {
                // Set status to Open
                ZYPEZDItemValueSetter.setValue(dcMsg.cltWrkItemStsCd_DD, CLT_WRK_ITEM_STS.OPEN);
            }

            // Set Request Comp Date if blank
            if (!ZYPCommonFunc.hasValue(dcMsg.cltWrkItemRwedDt_DD) && (ZYPCommonFunc.hasValue(dcMsg.cltWrkItemCd_DD))) {
                if (CLT_WRK_TP.AUTOMATIC.equals(dcMsg.cltWrkTpCd_DD.getValue())) {
                    // Request start date
                    ZYPEZDItemValueSetter.setValue(dcMsg.cltWrkItemRwedDt_DD, dcMsg.cltWrkItemRwsdDt_DD);

                } else if (CLT_WRK_TP.MANUAL.equals(dcMsg.cltWrkTpCd_DD.getValue())) {
                    CLT_WRK_ITEMTMsg cltWrkItemTMsg = NFDL0020Query.findByKeyForCltWrkItem(glblCmpyCd, dcMsg.cltWrkItemCd_DD.getValue());
                    if (cltWrkItemTMsg != null && ZYPCommonFunc.hasValue(cltWrkItemTMsg.cltWrkNextWaitDaysAot)) {
                        // Request start date + CLT_WRK_NEXT_WAIT_DAYS_AOT - 1
                        ZYPEZDItemValueSetter.setValue(dcMsg.cltWrkItemRwedDt_DD, ZYPDateUtil.addDays(dcMsg.cltWrkItemRwsdDt_DD.getValue(), cltWrkItemTMsg.cltWrkNextWaitDaysAot.getValueInt() - 1));
                    }
                }
            }
        }
    }

    /**
     * Get latest Actual Comp Date from CLT_STRGY_WRK_ITEM_TRX
     * @param glblCmpyCd String
     * @param cltStrgyTrxPk BigDecimal
     * @return String
     */
    public static String getStrgyWrkItemMaxActualCompDate(String glblCmpyCd, BigDecimal cltStrgyTrxPk) {
        S21SsmEZDResult ssmResult = NFDL0020Query.getInstance().getStrgyWrkItemMaxActualCompDate(glblCmpyCd, cltStrgyTrxPk);
        if (ssmResult.isCodeNormal()) {
            return (String) ssmResult.getResultObject();
        }
        return null;
    }
    // END 2021/05/25 G.Delgado [QC#58704,ADD]

    // add start 2022/01/20 QC#56864
    /**
     * hasUpdateFuncId
     * @return boolean
     */
    public static boolean hasUpdateFuncId() {
        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();

        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId("NFDL0020");
        if (funcList == null || funcList.isEmpty()) {
            return false;
        }
        if (funcList.contains("NFDL0020T020")) {
            return true;
        }
        return false;
    }
    // add end 2022/01/20 QC#56864
    
    // Start 2022/12/12 S.Fujita [QC#60406, ADD]
    public static String clobToString(Clob clob) throws SQLException {
        // Start 2023/2/28 S.Fujita [QC#61220, MOD]
        //return clob.getSubString(1, (int) clob.length());
        return clob.getSubString(1, 65535);
        // End 2023/2/28 S.Fujita [QC#61220, MOD]
    }
    // Start 2022/12/12 S.Fujita [QC#60406, ADD]
}
