/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFBL2060.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSDateItem;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFBL2060.NFBL2060CMsg;
import business.blap.NFBL2060.NFBL2060Query;
import business.blap.NFBL2060.NFBL2060SMsg;
import business.blap.NFBL2060.NFBL2060_DSMsg;
import business.blap.NFBL2060.NFBL2060_SSMsg;
import business.blap.NFBL2060.constant.NFBL2060Constant;
import business.db.SAVE_SRCH_OPTTMsg;
import business.db.SAVE_SRCH_OPTTMsgArray;
import business.file.NFBL2060F00FMsg;
import business.file.NFBL2060F01FMsg;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * Invoice Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/14   CUSA            Y.Aikawa        Create          N/A
 * 2016/07/26   Hitachi         T.Tsuchida      Update          QC#12239
 * 2016/07/29   Hitachi         J.Kim           Update          QC#12554
 * 2016/08/05   Fujitsu         C.Tanaka        Update          QC#12973
 * 2016/08/30   Fujitsu         C.Tanaka        Update          QC#5521
 * 2016/09/27   Hitachi         Y.Tsuchimoto    Update          QC#14797
 * 2016/09/30   Hitachi         T.Tsuchida      Update          QC#14498
 * 2016/10/03   Hitachi         T.Tsuchida      Update          QC#13414
 * 2016/10/24   Hitachi         Y.Tsuchimoto    Update          QC#15493
 * 2018/03/26   Hitachi         Y.Takeno        Update          QC#22350
 * 2018/05/28   CITS            K.Ogino         Update          QC#25902
 * 2018/10/18   Hitachi         J.Kim           Update          QC#28816
 * 2018/10/24   Hitachi         J.Kim           Update          QC#28869
 * 2020/03/03   Fujitsu         H.Ikeda         Update          QC#53413
 * 2022/02/15   Hitachi         A.Kohinata      Update          QC#57090
 * 2024/02/05   Hitachi         S.Ikariya       Update          QC#63451
 * </pre>
 */
public class NFBL2060CommonLogic implements NFBL2060Constant, ZYPConstant {

    /** S21UserProfileService Instance */
    public static final S21UserProfileService PROFILE_SERVICE = S21UserProfileServiceFactory.getInstance().getService();

    /** Global Company Code */
    public static final String GLBL_CMPY_CD = PROFILE_SERVICE.getGlobalCompanyCode();

    /**
     * Method name: createApInvCatgPulldownList
     * <dd>The method explanation: Create pulldown list.
     * Code Pull Down value from MDSE_OR_PRTS table.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    @SuppressWarnings("unchecked")
    public static void createApInvCatgPulldownList(EZDCMsg cMsg) {

        NFBL2060CMsg bizMsg = (NFBL2060CMsg) cMsg;

        S21SsmEZDResult ssmResult = NFBL2060Query.getInstance().getApInvCatgPulldownValue();

        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();
            for (int i = 0; i < resultList.size(); i++) {
                Map map = (Map) resultList.get(i);
                bizMsg.apInvCatgCd_C.no(i).setValue((String) map.get(AP_INV_CATG_CD));
                bizMsg.apInvCatgDescTxt_D.no(i).setValue((String) map.get(AP_INV_CATG_DESC_TXT));
            }
        }

    }

    /**
     * Method name: clearHeader
     * <dd>The method explanation: Clear Header values.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    public static void clearHeader(EZDCMsg cMsg) {

        NFBL2060CMsg bizMsg = (NFBL2060CMsg) cMsg;

        // Clear Values(Header Vendor Section)
        bizMsg.dplyVndNm.clear();
        bizMsg.apVndCd.clear();
        bizMsg.prntVndCd.clear();
        // START 2016/10/24 Y.Tsuchimoto [QC#15493,MOD]
        bizMsg.poNum.clear();
        // QC#25902
        bizMsg.vndRtrnNum.clear();
        bizMsg.vndRtrnSubmtDt_FR.clear();
        bizMsg.vndRtrnSubmtDt_TO.clear();
        // QC#25902 End
        // END   2016/10/24 Y.Tsuchimoto [QC#15493,MOD]
        bizMsg.delyOrdNum.clear();
        bizMsg.poDt_FR.clear();
        bizMsg.poDt_TO.clear();
        // payment term code
        bizMsg.vndPmtTermDescTxt.clear();
        // QC#12973 ADD Start
        bizMsg.vndPmtMethDescTxt.clear();
        // QC#12973 ADD End

        // Clear Values(Header Invoice Section)
        bizMsg.apVndInvNum.clear();
        bizMsg.acctInvStsCd_S.clear();
        bizMsg.invDt_FR.clear();
        bizMsg.invDt_TO.clear();
        // START 2024/02/05 S.Ikariya [QC#63451, ADD]
        bizMsg.acInvTotCostAmt_FR.clear();
        bizMsg.acInvTotCostAmt_TO.clear();
        // END 2024/02/05 S.Ikariya [QC#63451, ADD]
        bizMsg.xxCmntTxt_FR.clear();
        bizMsg.xxCmntTxt_TO.clear();
        // bizMsg.dsPmtMethNm.clear();
        bizMsg.apInvCatgCd_S.clear();
        bizMsg.apPmtChkNum.clear();
        bizMsg.pmtDt_FR.clear();
        bizMsg.pmtDt_TO.clear();

        // Clear Values(Header Invoice With Section)
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_01, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_02, ZYPConstant.FLG_OFF_N);

        // QC#5521 ADD Start
        bizMsg.srchOptPk_S.clear();
        // QC#5521 ADD End
        
        // START 2018/03/26 [QC#22350, ADD]
        bizMsg.dispPoDtlLineNum.clear();
        // END   2018/03/26 [QC#22350, ADD]
        // START 2018/10/18 J.Kim [QC#28816,ADD]
        bizMsg.invAuthDt_FR.clear();
        bizMsg.invAuthDt_TO.clear();
        // END 2018/10/18 J.Kim [QC#28816,ADD]
    }

    /**
     * Method name: clearDetail
     * <dd>The method explanation: Clear Detail values.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     */
    public static void clearDetail(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFBL2060CMsg bizMsg = (NFBL2060CMsg) cMsg;
        NFBL2060SMsg globalMsg = (NFBL2060SMsg) sMsg;

        // Detailed Tab
        bizMsg.D.clear();
        bizMsg.D.setValidCount(0);
        globalMsg.D.clear();
        globalMsg.D.setValidCount(0);

        // Summary Tab
        bizMsg.S.clear();
        bizMsg.S.setValidCount(0);
        globalMsg.S.clear();
        globalMsg.S.setValidCount(0);
    }

    /**
     * Method name: getDetailedTabRecord
     * <dd>The method explanation: Get record for detailed tab 
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    public static void getDetailedTabRecord(EZDCMsg cMsg, EZDSMsg sMsg, boolean blSearch) {
        EZDDebugOutput.println(5, "getDetailedTabRecord================================START", null);

        NFBL2060CMsg bizMsg = (NFBL2060CMsg) cMsg;
        NFBL2060SMsg globalMsg = (NFBL2060SMsg) sMsg;

        S21SsmEZDResult ssmResult = NFBL2060Query.getInstance().getDetailedTabRecord(bizMsg, globalMsg, blSearch);

        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();

            bizMsg.xxPageShowFromNum_D.setValue(1);
            if (queryResCnt > globalMsg.D.length()) {
                globalMsg.D.setValidCount(globalMsg.D.length());
                bizMsg.xxPageShowOfNum_D.setValue(globalMsg.D.length());
                bizMsg.setMessageInfo(NZZM0001W);
            } else {
                globalMsg.D.setValidCount(queryResCnt);
                bizMsg.xxPageShowOfNum_D.setValue(queryResCnt);
                bizMsg.setMessageInfo(ZZM8002I);
            }

            if (bizMsg.D.length() < globalMsg.D.getValidCount()) {
                bizMsg.D.setValidCount(bizMsg.D.length());
                bizMsg.xxPageShowToNum_D.setValue(bizMsg.D.length());
            } else {
                bizMsg.D.setValidCount(queryResCnt);
                bizMsg.xxPageShowToNum_D.setValue(queryResCnt);
            }

            // Copy SMsg info to CMsg.
            copySMsgToCMsgD(bizMsg, globalMsg);
        } else {
            // Not found
            bizMsg.D.setValidCount(0);
            bizMsg.setMessageInfo(NFBM0069E);
            return;
        }
        EZDDebugOutput.println(5, "getDetailedTabRecord================================END", null);
    }
    
    /**
     * Method name: copySMsgToCMsgD
     * <dd>The method explanation: Copy SMsg info to CMsg.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    public static void copySMsgToCMsgD(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "copySMsgToCMsg00================================START", null);
        NFBL2060CMsg bizMsg = (NFBL2060CMsg) cMsg;
        NFBL2060SMsg globalMsg = (NFBL2060SMsg) sMsg;
        for (int i = 0; i < bizMsg.D.getValidCount(); i++) {
            EZDMsg.copy(globalMsg.D.no(i), null, bizMsg.D.no(i), null);
        }
        EZDDebugOutput.println(5, "copySMsgToCMsg00================================END", null);
    }

    /**
     * Method name: getSummaryTabRecord
     * <dd>The method explanation: Get record for Summary tab 
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    public static void getSummaryTabRecord(EZDCMsg cMsg, EZDSMsg sMsg, boolean blSearch) {
        EZDDebugOutput.println(5, "getSummaryTabRecord================================START", null);

        NFBL2060CMsg bizMsg = (NFBL2060CMsg) cMsg;
        NFBL2060SMsg globalMsg = (NFBL2060SMsg) sMsg;

        S21SsmEZDResult ssmResult = NFBL2060Query.getInstance().getSummaryTabRecord(bizMsg, globalMsg, blSearch);

        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();

            bizMsg.xxPageShowFromNum_S.setValue(1);
            if (queryResCnt > globalMsg.S.length()) {
                globalMsg.S.setValidCount(globalMsg.S.length());
                bizMsg.xxPageShowOfNum_S.setValue(globalMsg.S.length());
                bizMsg.setMessageInfo(NZZM0001W);
            } else {
                globalMsg.S.setValidCount(queryResCnt);
                bizMsg.xxPageShowOfNum_S.setValue(queryResCnt);
                bizMsg.setMessageInfo(ZZM8002I);
            }

            if (bizMsg.S.length() < globalMsg.S.getValidCount()) {
                bizMsg.S.setValidCount(bizMsg.S.length());
                bizMsg.xxPageShowToNum_S.setValue(bizMsg.S.length());
            } else {
                bizMsg.S.setValidCount(queryResCnt);
                bizMsg.xxPageShowToNum_S.setValue(queryResCnt);
            }

            // Copy SMsg info to CMsg.
            copySMsgToCMsgS(bizMsg, globalMsg);
        } else {
            // Not found
            bizMsg.S.setValidCount(0);
            bizMsg.setMessageInfo(NFBM0069E);
            return;
        }
        EZDDebugOutput.println(5, "getSummaryTabRecord================================END", null);
    }

    /**
     * Method name: copySMsgToCMsgS
     * <dd>The method explanation: Copy SMsg info to CMsg.
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    public static void copySMsgToCMsgS(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(5, "copySMsgToCMsgS================================START", null);
        NFBL2060CMsg bizMsg = (NFBL2060CMsg) cMsg;
        NFBL2060SMsg globalMsg = (NFBL2060SMsg) sMsg;
        for (int i = 0; i < bizMsg.S.getValidCount(); i++) {
            EZDMsg.copy(globalMsg.S.no(i), null, bizMsg.S.no(i), null);
        }
        EZDDebugOutput.println(5, "copySMsgToCMsgS================================END", null);
    }

    /**
     * Method name: csvDownLoadDetailedTab
     * <dd>The method explanation: CSV Download Process 
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    public static void csvDownLoadDetailedTab(NFBL2060CMsg bizMsg, NFBL2060SMsg globalMsg) {

        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(FILE_NAME_DETAILED), CSV_EXT);

        NFBL2060F00FMsg f00Msg = new NFBL2060F00FMsg();

        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), f00Msg);

        // QC#5521 MOD Start
        f00Msg.setItemOrder(ZYPGUITableColumn.getColOrder(bizMsg));

        csvOutFile.writeHeader(CSV_HEADER_DETAILED, f00Msg, ZYPGUITableColumn.getColOrder(bizMsg));
        // QC#5521 MOD End

        for (int i = 0; i < globalMsg.D.getValidCount(); i++) {

            // START 2016/07/29 J.Kim [QC#12554,MOD]
            // /* Copy a record into FMsg, and write it by copy. */
            NFBL2060_DSMsg sMsg = globalMsg.D.no(i);
            EZDMsg.copy(sMsg, null, f00Msg, null);
            // START 2018/10/18 J.Kim [QC#28816,ADD]
            ZYPEZDItemValueSetter.setValue(f00Msg.xxDtTxt_D5, convertDateFormat(sMsg.invAuthDt_D1));
            // END 2018/10/18 J.Kim [QC#28816,ADD]
            ZYPEZDItemValueSetter.setValue(f00Msg.xxDtTxt_D1, convertDateFormat(sMsg.invDt_D1));
            ZYPEZDItemValueSetter.setValue(f00Msg.xxDtTxt_D2, convertDateFormat(sMsg.poDt_D1));
            ZYPEZDItemValueSetter.setValue(f00Msg.xxDtTxt_D3, convertDateFormat(sMsg.pmtDt_D1));
            // QC#25902
            ZYPEZDItemValueSetter.setValue(f00Msg.xxDtTxt_D4, convertDateFormat(sMsg.vndRtrnSubmtDt_D1));
            // QC#25902 End
            // END 2016/07/29 J.Kim [QC#12554,MOD]
            csvOutFile.write();
        }
        csvOutFile.close();

    }

    /**
     * Method name: csvDownLoadSummaryTab
     * <dd>The method explanation: CSV Download Process 
     * <dd>Remarks:
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    public static void csvDownLoadSummaryTab(NFBL2060CMsg bizMsg, NFBL2060SMsg globalMsg) {

        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(FILE_NAME_SUMMARY), CSV_EXT);

        NFBL2060F01FMsg f01Msg = new NFBL2060F01FMsg();

        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), f01Msg);

        // QC#5521 MOD Start
        f01Msg.setItemOrder(ZYPGUITableColumn.getColOrder(bizMsg));

        csvOutFile.writeHeader(CSV_HEADER_SUMMARY, f01Msg, ZYPGUITableColumn.getColOrder(bizMsg));
        // QC#5521 MOD End

        for (int i = 0; i < globalMsg.S.getValidCount(); i++) {

            // START 2016/07/29 J.Kim [QC#12554,MOD]
            // /* Copy a record into FMsg, and write it by copy. */
            NFBL2060_SSMsg sMsg = globalMsg.S.no(i);
            EZDMsg.copy(sMsg, null, f01Msg, null);
            // START 2018/10/18 J.Kim [QC#28816,ADD]
            ZYPEZDItemValueSetter.setValue(f01Msg.xxDtTxt_S5, convertDateFormat(sMsg.invAuthDt_S1));
            // END 2018/10/18 J.Kim [QC#28816,ADD]
            ZYPEZDItemValueSetter.setValue(f01Msg.xxDtTxt_S1, convertDateFormat(sMsg.invDt_S1));
            ZYPEZDItemValueSetter.setValue(f01Msg.xxDtTxt_S2, convertDateFormat(sMsg.poApvlDt_S1));
            ZYPEZDItemValueSetter.setValue(f01Msg.xxDtTxt_S3, convertDateFormat(sMsg.pmtDt_S1));
            // QC#25902
            ZYPEZDItemValueSetter.setValue(f01Msg.xxDtTxt_S4, convertDateFormat(sMsg.vndRtrnSubmtDt_S1));
            // QC#25902 End
            // END 2016/07/29 J.Kim [QC#12554,MOD]
            csvOutFile.write();
        }
        csvOutFile.close();

    }

    private static String convertDateFormat(EZDSDateItem date) {
        if (ZYPCommonFunc.hasValue(date)) {
            return ZYPDateUtil.formatEzd8ToDisp(date.getValue());
        }
        return date.getValue();
    }

    // QC#5521 ADD Start
    /**
     * Create Save Option PullDown list
     * @param bizMsg NFBL2060CMsg
     * @param userId String
     * @param glblCmpyCd String
     */
    public static void createPulldownListSaveOpt(NFBL2060CMsg bizMsg, String userId, String glblCmpyCd) {

        bizMsg.srchOptPk_H.clear();
        bizMsg.srchOptNm_H.clear();

        SAVE_SRCH_OPTTMsg saveSrchOptTMsg = new SAVE_SRCH_OPTTMsg();
        saveSrchOptTMsg.setSQLID("001");
        saveSrchOptTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        saveSrchOptTMsg.setConditionValue("srchOptAplId01", BIZ_ID);
        saveSrchOptTMsg.setConditionValue("srchOptUsrId01", userId);

        SAVE_SRCH_OPTTMsgArray saveSrchOptTMsgArray = (SAVE_SRCH_OPTTMsgArray) EZDTBLAccessor.findByCondition(saveSrchOptTMsg);
        for (int i = 0; i < saveSrchOptTMsgArray.length(); i++) {
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptPk_H.no(i), saveSrchOptTMsgArray.no(i).srchOptPk);
            ZYPEZDItemValueSetter.setValue(bizMsg.srchOptNm_H.no(i), saveSrchOptTMsgArray.no(i).srchOptNm);
        }
    }

    /**
     * Call NSZC0330 for Search Option
     * @param bizMsg NFBL2060CMsg
     * @param pMsg NSZC033001PMsg
     * @return boolean
     */
    public static boolean callSrchOptApi(NFBL2060CMsg bizMsg, NSZC033001PMsg pMsg) {

        NSZC033001 api = new NSZC033001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (!S21ApiUtil.getXxMsgIdList(pMsg).isEmpty()) {
            for (int i = 0; i < pMsg.xxMsgIdList.length(); i++) {
                if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(i).xxMsgId)) {
                    String msgId = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
                    bizMsg.setMessageInfo(msgId);
                    if (msgId.endsWith("E")) {
                        bizMsg.srchOptPk_S.setErrorInfo(1, msgId);
                        bizMsg.srchOptNm_S.setErrorInfo(1, msgId);
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * isExistSaveSearchName
     * @param bizMsg NFBL2060CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NFBL2060CMsg bizMsg) {

        for (int i = 0; i < bizMsg.srchOptNm_H.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_H.no(i))) {
                return false;
            }
            if (bizMsg.srchOptNm_S.getValue().equals(bizMsg.srchOptNm_H.no(i).getValue())) {
                if (ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S) && bizMsg.srchOptPk_S.getValue().compareTo(bizMsg.srchOptPk_H.no(i).getValue()) == 0) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * isSameSaveSearchName
     * @param bizMsg NFBL2060CMsg
     * @return boolean
     */
    public static boolean isSameSaveSearchName(NFBL2060CMsg bizMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_S)) {
            return false;
        }

        for (int i = 0; i < bizMsg.srchOptNm_H.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_H.no(i))) {
                return false;
            }
            if (bizMsg.srchOptPk_S.getValue().compareTo(bizMsg.srchOptPk_H.no(i).getValue()) == 0) {
                if (bizMsg.srchOptNm_S.getValue().equals(bizMsg.srchOptNm_H.no(i).getValue())) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    /**
     * setSelectSaveSearchName
     * @param bizMsg NLAL2020CMsg
     * @param pMsg NSZC033001PMsg
     */
    public static void setSelectSaveSearchName(NFBL2060CMsg bizMsg, NSZC033001PMsg pMsg) {

        if (!ZYPCommonFunc.hasValue(bizMsg.srchOptPk_S)) {
            return;
        }

        for (int i = 0; i < bizMsg.srchOptNm_H.length(); i++) {
            if (!ZYPCommonFunc.hasValue(bizMsg.srchOptNm_H.no(i))) {
                return;
            }
            if (bizMsg.srchOptPk_S.getValue().compareTo(bizMsg.srchOptPk_H.no(i).getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, bizMsg.srchOptPk_H.no(i));
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, bizMsg.srchOptNm_H.no(i));
            }
        }
        return;
    }

    /**
     * Pagination <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param pageFrom int
     */
    public static void pagination(NFBL2060CMsg cMsg, NFBL2060SMsg sMsg, int pageFrom) {

        String dplyTab = cMsg.xxDplyTab.getValue();

        if (TAB_DETAILED.equals(dplyTab)) {
            int i = pageFrom;
            for (; i < pageFrom + cMsg.D.length(); i++) {
                if (i < sMsg.D.getValidCount()) {
                    EZDMsg.copy(sMsg.D.get(i), null, cMsg.D.get(i - pageFrom), null);
                } else {
                    break;
                }
            }
            cMsg.D.setValidCount(i - pageFrom);
            cMsg.xxPageShowFromNum_D.setValue(pageFrom + 1);
            cMsg.xxPageShowToNum_D.setValue(pageFrom + cMsg.D.getValidCount());

        } else {
            int i = pageFrom;
            for (; i < pageFrom + cMsg.S.length(); i++) {
                if (i < sMsg.S.getValidCount()) {
                    EZDMsg.copy(sMsg.S.get(i), null, cMsg.S.get(i - pageFrom), null);
                } else {
                    break;
                }
            }
            cMsg.S.setValidCount(i - pageFrom);
            cMsg.xxPageShowFromNum_S.setValue(pageFrom + 1);
            cMsg.xxPageShowToNum_S.setValue(pageFrom + cMsg.S.getValidCount());
        }
    }
    // QC#5521 ADD End

    // START 2018/10/24 J.Kim [QC#28869,ADD]
    public static void outputSearchLog(NFBL2060CMsg cMsg) {

        StringBuffer sb = new StringBuffer();
        sb.append("NFBL2060 Search Condition - Supplier : ");
        if (ZYPCommonFunc.hasValue(cMsg.dplyVndNm)) {
            sb.append(" Supplier Name[").append(cMsg.dplyVndNm.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.apVndCd)) {
            sb.append(" Supplier Site Code[").append(cMsg.apVndCd.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.prntVndCd)) {
            sb.append(" Supplier Number[").append(cMsg.prntVndCd.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.poNum)) {
            sb.append(" PO Number[").append(cMsg.poNum.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.delyOrdNum)) {
            sb.append(" Receipt[").append(cMsg.delyOrdNum.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.poDt_FR) || ZYPCommonFunc.hasValue(cMsg.poDt_TO)) {
            sb.append(" PO Date[").append(cMsg.poDt_FR.getValue()).append("-").append(cMsg.poDt_TO.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.vndPmtTermDescTxt)) {
            sb.append(" Terms[").append(cMsg.vndPmtTermDescTxt.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.vndRtrnSubmtDt_FR) || ZYPCommonFunc.hasValue(cMsg.vndRtrnSubmtDt_TO)) {
            sb.append(" VR Date[").append(cMsg.vndRtrnSubmtDt_FR.getValue()).append("-").append(cMsg.vndRtrnSubmtDt_TO.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.vndRtrnNum)) {
            sb.append(" VR #[").append(cMsg.vndRtrnNum.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.dispPoDtlLineNum)) {
            sb.append(" Line#[").append(cMsg.dispPoDtlLineNum.getValue()).append("]");
        }
        S21InfoLogOutput.println(sb.toString());

        sb = new StringBuffer();
        sb.append("NFBL2060 Search Condition - Invoice : ");
        if (ZYPCommonFunc.hasValue(cMsg.apVndInvNum)) {
            sb.append(" Invoice Number[").append(cMsg.apVndInvNum.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.acctInvStsCd_S)) {
            sb.append(" Status[").append(cMsg.acctInvStsCd_S.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.invDt_FR) || ZYPCommonFunc.hasValue(cMsg.invDt_TO)) {
            sb.append(" Invoice Date[").append(cMsg.invDt_FR.getValue()).append("-").append(cMsg.invDt_TO.getValue()).append("]");
        }
        // START 2024/02/05 S.Ikariya [QC#63451, ADD]
        if (ZYPCommonFunc.hasValue(cMsg.acInvTotCostAmt_FR) || ZYPCommonFunc.hasValue(cMsg.acInvTotCostAmt_TO)) {
            sb.append(" Invoice Amount[").append(cMsg.acInvTotCostAmt_FR.getValue()).append("-").append(cMsg.acInvTotCostAmt_TO.getValue()).append("]");
        }
        // END 2024/02/05 S.Ikariya [QC#63451, ADD]
        if (ZYPCommonFunc.hasValue(cMsg.invAuthDt_FR) || ZYPCommonFunc.hasValue(cMsg.invAuthDt_TO)) {
            sb.append(" Linked To ARCS Date[").append(cMsg.invAuthDt_FR.getValue()).append("-").append(cMsg.invAuthDt_TO.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.xxCmntTxt_FR)) {
            sb.append(" Charge Account From[").append(cMsg.xxCmntTxt_FR.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.xxCmntTxt_TO)) {
            sb.append(" Charge Account to[").append(cMsg.xxCmntTxt_TO.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.vndPmtMethDescTxt)) {
            sb.append(" Payment Method[").append(cMsg.vndPmtMethDescTxt.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.apInvCatgCd_S)) {
            sb.append(" Source[").append(cMsg.apInvCatgCd_S.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.apPmtChkNum)) {
            sb.append(" Payment Number[").append(cMsg.apPmtChkNum.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.pmtDt_FR) || ZYPCommonFunc.hasValue(cMsg.pmtDt_TO)) {
            sb.append(" Payment Date[").append(cMsg.pmtDt_FR.getValue()).append("-").append(cMsg.pmtDt_TO.getValue()).append("]");
        }
        S21InfoLogOutput.println(sb.toString());

        sb = new StringBuffer();
        sb.append("NFBL2060 Search Condition - Invoice With : ");
        if (ZYPCommonFunc.hasValue(cMsg.xxChkBox_01)) {
            sb.append(" Holds[").append(cMsg.xxChkBox_01.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.xxChkBox_02)) {
            sb.append(" PO Variance[").append(cMsg.xxChkBox_02.getValue()).append("]");
        }
        S21InfoLogOutput.println(sb.toString());
    }
    // END 2018/10/24 J.Kim [QC#28869,ADD]
    // START 2020/03/03 [QC#53413, ADD]
    /**
     * Check PO_NUM Create
     * 
     * @param cMsg EZDCMsg
     */
    // mod start 2022/02/15 QC#57090
    //public static void chkPoNumCreate(EZDCMsg cMsg) {
    public static void chkMultiPoOrMultiVndRtn(EZDCMsg cMsg) {
    // mod end 2022/02/15 QC#57090
        NFBL2060CMsg bizMsg = (NFBL2060CMsg) cMsg;

        for (int i = 0; i < bizMsg.S.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(bizMsg.S.no(i).apVndInvNum_S1)) {
                String invkey = bizMsg.S.no(i).apVndInvNum_S1.getValue();
                List<String> poNumList = new ArrayList<String>();
                // add start 2022/02/15 QC#57090
                List<String> vndRtrnNumList = new ArrayList<String>();
                // add end 2022/02/15 QC#57090
                for (int j = 0; j < bizMsg.D.getValidCount(); j++) {
                    if (ZYPCommonFunc.hasValue(bizMsg.D.no(j).apVndInvNum_D1)) {
                        if (invkey.equals(bizMsg.D.no(j).apVndInvNum_D1.getValue())) {
                            if (ZYPCommonFunc.hasValue(bizMsg.D.no(j).poNum_D1)) {
                                String poNum = bizMsg.D.no(j).poNum_D1.getValue();
                                if (!poNumList.contains(poNum)) {
                                    poNumList.add(poNum);
                                }
                            }
                            // add start 2022/02/15 QC#57090 QC#57090
                            if (ZYPCommonFunc.hasValue(bizMsg.D.no(j).vndRtrnNum_D1)) {
                                String vndRtrnNum = bizMsg.D.no(j).vndRtrnNum_D1.getValue();
                                if (!vndRtrnNumList.contains(vndRtrnNum)) {
                                    vndRtrnNumList.add(vndRtrnNum);
                                }
                            }
                            // add end 2022/02/15 QC#57090
                        }
                    }
                }
                if (poNumList.size() > 1) {
                    if (ZYPCommonFunc.hasValue(bizMsg.S.no(i).poNum_S1)) {
                        // mod start 2022/02/15 QC#57090
                        //ZYPEZDItemValueSetter.setValue(bizMsg.S.no(i).poNum_S1, (bizMsg.S.no(i).poNum_S1.getValue() + MULTI_PO_NUM_ITEM));
                        ZYPEZDItemValueSetter.setValue(bizMsg.S.no(i).poNum_S1, (bizMsg.S.no(i).poNum_S1.getValue() + MULTI_ITEM));
                        // mod end 2022/02/15 QC#57090
                    }
                }
                // add start 2022/02/15 QC#57090
                if (vndRtrnNumList.size() > 1) {
                    if (ZYPCommonFunc.hasValue(bizMsg.S.no(i).xxDplyTrxNumTxt_S1)) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.S.no(i).xxDplyTrxNumTxt_S1, (bizMsg.S.no(i).xxDplyTrxNumTxt_S1.getValue() + MULTI_ITEM));
                    }
                }
                // add end 2022/02/15 QC#57090
            }
        }
    }
    // END   2020/03/03 [QC#53413, ADD]
}
