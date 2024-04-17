/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0430.common;

import static business.blap.NSBL0430.constant.NSBL0430Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import static com.canon.cusa.s21.common.NSX.NSXC002001.NSXC002001SerialRangeCheck.isCheckSerialNum;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AUTO_CRAT_RFRS_TMG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSBL0430.NSBL0430CMsg;
import business.blap.NSBL0430.NSBL0430Query;
import business.blap.NSBL0430.NSBL0430SMsg;
import business.blap.NSBL0430.NSBL0430_ACMsgArray;
import business.blap.NSBL0430.NSBL0430_ASMsg;
import business.blap.NSBL0430.NSBL0430_ASMsgArray;
import business.db.ALL_MDSE_VTMsg;
import business.db.ALL_MDSE_VTMsgArray;
import business.db.MDSE_SER_NUM_RNGTMsg;
import business.db.MDSE_SER_NUM_RNGTMsgArray;
import business.db.SVC_MODTMsg;
import business.db.SVC_MOD_SER_RNGTMsg;
import business.file.NSBL0430F00FMsg;

/**
 *<pre>
 * Mods Serial# Assignment
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/28   Hitachi         O.Okuma         Create          N/A
 * 2016/02/29   Hitachi         O.Okuma         Update          QC4723
 * 2016/03/01   Hitachi         O.Okuma         Update          QC4726
 * 2016/03/08   Hitachi         O.Okuma         Update          QC4725
 * 2016/04/12   Hitachi         M.Gotou         Update          QC5224
 * 2016/04/18   Hitachi         M.Gotou         Update          QC3425
 * 2016/07/11   Hitachi         O.Okuma         Update          QC11646
 * 2017/02/15   Hitachi         N.Arai          Update          QC#17562
 * 2017/10/26   Hitachi         U.Kim           Update          QC#21797
 * 2017/12/14   Hitachi         M.Kidokoro      Update          QC#22254
 * 2018/05/09   CITS            T.Wada          Update          QC#25666
 *</pre>
 */
public class NSBL0430CommonLogic {

    /**
     * Clear Message
     * @param cMsg NSBL0430CMsg
     * @param sMsg NSBL0430SMsg
     */
    public static void clearMsg(NSBL0430CMsg cMsg, NSBL0430SMsg sMsg) {

        BigDecimal svcModPk = cMsg.svcModPk.getValue();

        sMsg.clear();
        cMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(sMsg.B);

        setValue(cMsg.svcModPk, svcModPk);
    }

    /**
     * Create Pull Down
     * @param cMsg NSBL0430CMsg
     */
    public static void createPullDown(NSBL0430CMsg cMsg) {

        ZYPCodeDataUtil.createPulldownList(AUTO_CRAT_RFRS_TMG.class, cMsg.autoCratRfrsTmgCd_01, cMsg.autoCratRfrsTmgDescTxt_01);
        ZYPPulldownValueSetter.insertFirstData(ZYPConstant.FLG_OFF_N, cMsg.xxDplyByCtrlItemCd_01, ZYPConstant.FLG_OFF_N, cMsg.xxDplyByCtrlItemCdNm_01);
        ZYPPulldownValueSetter.insertFirstData(ZYPConstant.FLG_ON_Y, cMsg.xxDplyByCtrlItemCd_01, ZYPConstant.FLG_ON_Y, cMsg.xxDplyByCtrlItemCdNm_01);
    }

    /**
     * Set Initialize Parameters
     * @param cMsg NSBL0430CMsg
     * @param sMsg NSBL0430SMsg
     */
    public static void setInitParams(NSBL0430CMsg cMsg, NSBL0430SMsg sMsg) {
    }

    /**
     * Check whether the cMsg has an error.
     * @param cMsg NSBL0430CMsg
     * @return boolean true: If cMsg has error. false: otherwise.
     */
    public static boolean isErrorSearchCondition(NSBL0430CMsg cMsg) {
        return false;
    }

    /**
     * Get Search Data
     * @param cMsg NSBL0430CMsg
     * @param sMsg NSBL0430SMsg
     */
    public static void getSearchData(NSBL0430CMsg cMsg, NSBL0430SMsg sMsg) {

        getSvcMod(cMsg);
        S21SsmEZDResult ssmResult = NSBL0430Query.getInstance().getSearchData(cMsg, sMsg, sMsg.A.length() + 1);
        if (ssmResult.isCodeNormal()) {
            // Result > 1000
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            } else {
                cMsg.setMessageInfo(NSBM0005I);
            }
        } else if (ssmResult.isCodeNotFound()) {
            if ("NSBL0430Scrn00_CMN_Submit".equals(cMsg.getScreenAplID())) {
                return;
            }
            // add start 2016/04/18 CSA Defect#3425
            if ("NSBL0430Scrn00_Filter".equals(cMsg.getScreenAplID())) {
                cMsg.setMessageInfo(ZZZM9001E);
                return;
            }
            // add end 2016/04/18 CSA Defect#3425
            S21SsmEZDResult ssmResultDefault = NSBL0430Query.getInstance().getDefaultData(cMsg, sMsg, sMsg.A.length() + 1);
            if (ssmResultDefault.isCodeNormal()) {

                for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                    if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).svcModAutoCratMndFlg_A.getValue())) {
                        setValue(sMsg.A.no(i).autoCratCallFlg_A, ZYPConstant.FLG_ON_Y);
                    }
                }

                // Result > 1000
                int queryResCnt = ssmResult.getQueryResultCount();
                if (queryResCnt > sMsg.A.length()) {
                    cMsg.setMessageInfo(NZZM0001W);
                } else {
                    cMsg.setMessageInfo(NSBM0005I);
                }
            }
        }
    }

    /**
     * setPagenation <dd>The method explanation: The business
     * peculiarity processing is executed.
     * @param cMsg NSBL0430CMsg
     * @param sMsg NSBL0430SMsg
     * @param pagenationFrom int
     */
    public static void setPagenation(NSBL0430CMsg cMsg, NSBL0430SMsg sMsg, int pagenationFrom) {

        int cnt = pagenationFrom;
        for (; cnt < pagenationFrom + cMsg.A.length(); cnt++) {
            if (cnt < pagenationFrom + cMsg.A.getValidCount()) {
                EZDMsg.copy(cMsg.A.get(cnt - pagenationFrom), null, sMsg.A.get(cnt), null);
            } else {
                break;
            }
        }
    }

    /**
     * pagenation <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param pageFrom int
     */
    public static void pagenation(NSBL0430CMsg cMsg, NSBL0430SMsg sMsg, int pageFrom) {

        int i = pageFrom;
        for (; i < pageFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(i - pageFrom), null);
            } else {
                break;
            }
        }

        cMsg.A.setValidCount(i - pageFrom);
        cMsg.xxPageShowFromNum.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum.setValue(pageFrom + cMsg.A.getValidCount());
    }

    /**
     * copy To ASMsg for Current Page Info
     * @param cMsg NSBL0430CMsg
     * @param sMsg NSBL0430SMsg
     */
    public static void copyCurrentPageToSMsg(NSBL0430CMsg cMsg, NSBL0430SMsg sMsg) {

        int pageFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        NSBL0430CommonLogic.setPagenation(cMsg, sMsg, pageFromNum);
    }

    private static void getSvcMod(NSBL0430CMsg cMsg) {

        SVC_MODTMsg tMsg = new SVC_MODTMsg();
        setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(tMsg.svcModPk, cMsg.svcModPk);

        SVC_MODTMsg rtMsg =  (SVC_MODTMsg) EZDTBLAccessor.findByKey(tMsg);

        if (rtMsg != null) {
            setValue(cMsg.svcModPlnId, rtMsg.svcModPlnId);
            setValue(cMsg.svcModNm, rtMsg.svcModNm);
            setValue(cMsg.svcModCmntTxt, rtMsg.svcModCmntTxt);
            setValue(cMsg.xxEndDplyTmTxt, rtMsg.estSvcLborHourMn);
        }
    }

    /**
     * Check MdseCd.
     * @param cMsg NSBL0430CMsg
     * @return boolean
     */
    public static boolean checkMdseCd(NSBL0430CMsg cMsg) {

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            // del start 2016/04/12 CSA Defect#5224
            //if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.A.no(i).xxChkBox_A.getValue())) {
            // del end 2016/04/12 CSA Defect#5224
            if (!checkAllMdseV(cMsg.glblCmpyCd.getValue(), cMsg.A.no(i).mdseCd_A.getValue())) {
                cMsg.A.no(i).xxChkBox_A.setErrorInfo(1, NSBM0011E, new String[] {cMsg.A.no(i).mdseCd_A.getValue()});
                return false;
            }

            Map<String, Object> map = getDetailData(cMsg, cMsg.A.no(i).mdseCd_A.getValue());

            if (map == null || map.isEmpty()) {
                cMsg.A.no(i).xxChkBox_A.setErrorInfo(1, NSAM0063E, new String[] {cMsg.A.no(i).mdseCd_A.getValue(), MOD_SETAIL });
                return false;
            }
        }
        return true;
    }

    /**
     * Add Detail for upload.
     * @param status int
     * @param cMsg NSBL0430CMsg
     * @param sMsg NSBL0430SMsg
     * @param fMsg NSBL0430F00FMsg
     * @return String Error Code
     */
    public static String addNewMdseCdRowUpload(int status, NSBL0430CMsg cMsg, NSBL0430SMsg sMsg, NSBL0430F00FMsg fMsg) {
        int count = sMsg.A.getValidCount();
        if (sMsg.A.length() < count + 1) {
            cMsg.setMessageInfo(NSBM0058E, new String[] {MDSE_CODE, MAX_COUNT });
            return NSBM0058E;
        }

        if (NSAM0208E.equals(validateUpload(status, count, cMsg, sMsg.A, fMsg))) {
            return NSAM0208E;
        }

        if (!hasValue(fMsg.mdseCd)) {
            sMsg.A.no(count).xxChkBox_A.setErrorInfo(1, NSAM0007E, new String[] {MDSE_UPD });
            return NSAM0007E;
        }

        if (!checkAllMdseV(cMsg.glblCmpyCd.getValue(), fMsg.mdseCd.getValue())) {
            sMsg.A.no(count).xxChkBox_A.setErrorInfo(1, NSBM0011E, new String[] {fMsg.mdseCd.getValue() });
            return NSBM0011E;
        }


        Map<String, Object> map = getDetailData(cMsg, fMsg.mdseCd.getValue());

        if (map == null || map.isEmpty()) {
            sMsg.A.no(count).xxChkBox_A.setErrorInfo(1, NSAM0063E, new String[] {fMsg.mdseCd.getValue(), MOD_SETAIL });
            return NSAM0063E;
        }

        setValue(sMsg.A.no(count).svcModDtlPk_A, (BigDecimal) map.get("SVC_MOD_DTL_PK"));
        setValue(sMsg.A.no(count).svcModAutoCratMndFlg_A, (String) map.get("SVC_MOD_AUTO_CRAT_MND_FLG"));

        if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(count).svcModAutoCratMndFlg_A.getValue())) {
            setValue(sMsg.A.no(count).autoCratCallFlg_A, ZYPConstant.FLG_ON_Y);
        }
        return "";
    }

    /**
     * Add Detail.
     * @param cMsg NSBL0430CMsg
     * @param sMsg NSBL0430SMsg
     * @return boolean
     */
    public static boolean addNewMdseCdRow(NSBL0430CMsg cMsg, NSBL0430SMsg sMsg) {
        int count = sMsg.A.getValidCount();
        if (sMsg.A.length() < count + 1) {
            cMsg.setMessageInfo(NSBM0058E, new String[] {MDSE_CODE, MAX_COUNT });
            return false;
        }

        if (!checkAllMdseV(cMsg.glblCmpyCd.getValue(), cMsg.mdseCd.getValue())) {
            cMsg.mdseCd.setErrorInfo(1,  NSBM0011E, new String[] {cMsg.mdseCd.getValue() });
            return false;
        }

        Map<String, Object> map = getDetailData(cMsg, cMsg.mdseCd.getValue());

        if (map == null || map.isEmpty()) {
            cMsg.mdseCd.setErrorInfo(1, NSAM0063E, new String[] {cMsg.mdseCd.getValue(), MOD_SETAIL });
            return false;
        }

        setValue(sMsg.A.no(count).mdseCd_A, cMsg.mdseCd.getValue());
        setValue(sMsg.A.no(count).svcModDtlPk_A, (BigDecimal) map.get("SVC_MOD_DTL_PK"));
        setValue(sMsg.A.no(count).svcModAutoCratMndFlg_A, (String) map.get("SVC_MOD_AUTO_CRAT_MND_FLG"));

        if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(count).svcModAutoCratMndFlg_A.getValue())) {
            setValue(sMsg.A.no(count).autoCratCallFlg_A, ZYPConstant.FLG_ON_Y);
        }
        sMsg.A.setValidCount(count + 1);
        return true;
    }

    /**
     * findPage
     * @param cMsg NSBL0430CMsg
     * @param sMsg NSBL0430SMsg
     */
    public static void findPage(NSBL0430CMsg cMsg, NSBL0430SMsg sMsg) {

        if (sMsg.A.getValidCount() == 1) {
            cMsg.xxPageShowFromNum.setValue(1);
        }

        int pagenationFrom;
        if (sMsg.A.getValidCount() < cMsg.A.length() + 1) {
            pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
            int j = 0;
            for (; j < sMsg.A.getValidCount(); j++) {
                if (j < sMsg.A.getValidCount()) {
                    EZDMsg.copy(sMsg.A.no(j), null, cMsg.A.no(j), null);
                } else {
                    break;
                }
            }
            cMsg.A.setValidCount(j);
            cMsg.xxPageShowFromNum.setValue(pagenationFrom);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());

        } else {
            int pageNum = (sMsg.A.getValidCount() - 1) / cMsg.A.length();
            pagenationFrom = pageNum * cMsg.A.length();
            ZYPTableUtil.clear(cMsg.A);
            int j = pagenationFrom;
            for (; j < sMsg.A.getValidCount(); j++) {
                if (j < sMsg.A.getValidCount()) {
                    EZDMsg.copy(sMsg.A.no(j), null, cMsg.A.no(j - pagenationFrom), null);
                } else {
                    break;
                }
            }
            cMsg.A.setValidCount(j - pagenationFrom);
            cMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
            cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
        }
    }

    private static boolean checkAllMdseV(String glblCmpyCd, String mdseCd) {
        ALL_MDSE_VTMsg param = new ALL_MDSE_VTMsg();
        param.setSQLID("003");
        param.setConditionValue("glblCmpyCd01", glblCmpyCd);
        param.setConditionValue("mdseCd01", mdseCd);
        ALL_MDSE_VTMsgArray result = (ALL_MDSE_VTMsgArray) EZDTBLAccessor.findByCondition(param);

        if (result.getValidCount() == 0) {
            return false;
        }
        return true;
    }

    private static Map<String, Object> getDetailData(NSBL0430CMsg cMsg, String mdseCd) {

        S21SsmEZDResult ssmResult = NSBL0430Query.getInstance().getDetailData(cMsg, mdseCd);
        if (ssmResult.isCodeNormal()) {

            List<Map<String, Object>> list = (List<Map<String, Object>>) ssmResult.getResultObject();
            if (list != null && !list.isEmpty()) {
                return list.get(0);
            }
        }
        return null;
    }

    /**
     * delete Detail.
     * @param cMsg NSBL0430CMsg
     * @param sMsg NSBL0430SMsg
     */
    public static void deleteRows(NSBL0430CMsg cMsg, NSBL0430SMsg sMsg) {

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.A, "xxChkBox_A", ZYPConstant.CHKBOX_ON_Y);
        if (selectedRows.isEmpty()) {
            cMsg.setMessageInfo(NSBM0042E);
            return;
        }
        if (isCheckSerialRangeForDel(cMsg, sMsg, selectedRows)) {
            pagenation(cMsg, sMsg, cMsg.xxPageShowFromNum.getValueInt() - 1);
            return;
        }

        int bCnt = sMsg.B.getValidCount();
        for (int i = 0; i < selectedRows.size(); i++) {
            if (hasValue(sMsg.A.no(selectedRows.get(i)).svcModSerRngPk_A)) {
                setValue(sMsg.B.no(bCnt).svcModSerRngPk_B, sMsg.A.no(selectedRows.get(i)).svcModSerRngPk_A);
               bCnt++;
                sMsg.B.setValidCount(bCnt);
            }
        }

        ZYPTableUtil.deleteRows(sMsg.A, selectedRows);

        NSBL0430_ACMsgArray acMsgArray = cMsg.A;
        ZYPTableUtil.clear(acMsgArray);

        int pageFrom = cMsg.xxPageShowFromNum.getValueInt() - 1;

        if (pageFrom >= sMsg.A.getValidCount()) {
            pageFrom = 0;
        }

        int i = pageFrom;
        for (; i < pageFrom + acMsgArray.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, acMsgArray.no(i - pageFrom), null);
            } else {
                break;
            }
        }
        acMsgArray.setValidCount(i - pageFrom);
        pageFrom = pageFrom + 1;
        cMsg.xxPageShowFromNum.setValue(pageFrom);
        cMsg.xxPageShowToNum.setValue(pageFrom + acMsgArray.getValidCount() - 1);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    private static boolean isCheckSerialRangeForDel(NSBL0430CMsg cMsg, NSBL0430SMsg sMsg, List<Integer> selectedRows) {

        boolean checkFlg = false;
        for (int i = 0; i < selectedRows.size(); i++) {
            if (!checkCallData(cMsg, sMsg.A.no(selectedRows.get(i)))) {
                checkFlg = true;
            }
        }
        return checkFlg;
    }

    private static boolean checkCallData(NSBL0430CMsg cMsg, NSBL0430_ASMsg aSMsg) {

        if (!hasValue(aSMsg.svcModSerRngPk_A)) {
            return true;
        }

        S21SsmEZDResult ssmResult = NSBL0430Query.getInstance().getCallData(cMsg, aSMsg.svcModSerRngPk_A.getValue());
        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) ssmResult.getResultObject();

            for (int j = 0; j < list.size(); j++) {

                Map<String, Object> map = list.get(j);

                if (isCheckSerialNum(
                        (String) map.get("SVC_MOD_FROM_SER_NUM")
                        , (String) map.get("SVC_MOD_TO_SER_NUM")
                        , (String) map.get("SER_NUM"))) {

                    aSMsg.xxChkBox_A.setErrorInfo(1, NSBM0139E);
                    return false;
                }
            }
        }
        return true;
    }

    private static String validateUpload(int status, int count, NSBL0430CMsg cMsg, NSBL0430_ASMsgArray asMsgArray, NSBL0430F00FMsg fMsg) {

        NSBL0430_ASMsg sMsgLine = asMsgArray.no(count);

        if (status == CSV_READ_STATUS_CODE_1000) {
            sMsgLine.xxChkBox_A.setErrorInfo(1, NSAM0208E);
            return NSAM0208E;
        } else if (status > CSV_READ_STATUS_CODE_1000 && status < CSV_READ_STATUS_CODE_2000) {
            int column = status - CSV_READ_STATUS_CODE_1000;

            switch (column) {
                case COL_NUM_1:
                    sMsgLine.xxChkBox_A.setErrorInfo(1, NSAM0209E, new String[] {MDSE_UPD });
                    break;
                case COL_NUM_2:
                    sMsgLine.xxChkBox_A.setErrorInfo(1, NSAM0209E, new String[] {SER_FROM_UPD });
                    break;
                case COL_NUM_3:
                    sMsgLine.xxChkBox_A.setErrorInfo(1, NSAM0209E, new String[] {SER_TO_UPD });
                    break;
                case COL_NUM_4:
                    sMsgLine.xxChkBox_A.setErrorInfo(1, NSAM0209E, new String[] {AUTO_CRE_CALLS_UPD });
                    break;
                case COL_NUM_5:
                    sMsgLine.xxChkBox_A.setErrorInfo(1, NSAM0209E, new String[] {AUTO_CRE_REF_RATE_UPD });
                    break;
                default:
                    sMsgLine.xxChkBox_A.setErrorInfo(1, NSAM0209E);
           }
           return NSAM0209E;
        } else if (status >= CSV_READ_STATUS_CODE_2000) {
            int column = status - CSV_READ_STATUS_CODE_2000;

            switch (column) {
                case COL_NUM_1:
                    sMsgLine.xxChkBox_A.setErrorInfo(1, NSAM0210E, new String[] {MDSE_UPD });
                    break;
                case COL_NUM_2:
                    sMsgLine.xxChkBox_A.setErrorInfo(1, NSAM0210E, new String[] {SER_FROM_UPD });
                    break;
                case COL_NUM_3:
                    sMsgLine.xxChkBox_A.setErrorInfo(1, NSAM0210E, new String[] {SER_TO_UPD });
                    break;
                case COL_NUM_4:
                    sMsgLine.xxChkBox_A.setErrorInfo(1, NSAM0210E, new String[] {AUTO_CRE_CALLS_UPD });
                    break;
                case COL_NUM_5:
                    sMsgLine.xxChkBox_A.setErrorInfo(1, NSAM0210E, new String[] {AUTO_CRE_REF_RATE_UPD });
                    break;
                default:
                    sMsgLine.xxChkBox_A.setErrorInfo(1, NSAM0210E);
            }
            return NSAM0210E;
        }
        return "";
    }

    /**
     * Do Submit.
     * @param cMsg NSBL0430CMsg
     * @param sMsg NSBL0430SMsg
     * @return boolean
     */
    public static boolean submitSvcModSerRng(NSBL0430CMsg cMsg, NSBL0430SMsg sMsg) {

        boolean errFlg = true;
        // START 2017/12/14 M.Kidokoro [QC#22254, ADD]
        SVC_MOD_SER_RNGTMsg result = null;
        NSBL0430_ASMsg aSMsg = null;
        String[] diffFlgArray = new String[sMsg.A.getValidCount()];
        // END 2017/12/14 M.Kidokoro [QC#22254, ADD]
        NSBL0430SMsg aftSMsg = (NSBL0430SMsg) sMsg.clone();
        aftSMsg.clear();
        ZYPTableUtil.clear(aftSMsg.A);

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            // START 2017/12/14 M.Kidokoro [QC#22254, ADD]
            aSMsg = sMsg.A.no(i);
            result = getSvcModSerRng(cMsg.glblCmpyCd.getValue(), aSMsg.svcModSerRngPk_A.getValue());
            if (result != null) {
                if (compare(result, aSMsg)) {
                    diffFlgArray[i] = ZYPConstant.FLG_ON_Y;
                } else {
                    diffFlgArray[i] = ZYPConstant.FLG_OFF_N;
                    continue;
                }
            } else {
                diffFlgArray[i] = ZYPConstant.FLG_ON_Y;
            }
            // END 2017/12/14 M.Kidokoro [QC#22254, ADD]
            if (!isSubmit(sMsg.A.no(i))) {
                continue;
            }
            boolean chkErrFlg = checkSubmit(cMsg, sMsg, i);
            if (errFlg && !chkErrFlg) {
                errFlg = false;
            }
        }

        if (!errFlg) {
            return false;
        }

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            // START 2017/12/14 M.Kidokoro [QC#22254, ADD]
            if (!ZYPConstant.FLG_ON_Y.equals(diffFlgArray[i])) {
                continue;
            }
            // END 2017/12/14 M.Kidokoro [QC#22254, ADD]
            if (!isSubmit(sMsg.A.no(i))) {
                setValue(aftSMsg.A.no(i).svcModSerRngPk_A, sMsg.A.no(i).svcModSerRngPk_A);
                setValue(aftSMsg.A.no(i).mdseCd_A, sMsg.A.no(i).mdseCd_A);
                setValue(aftSMsg.A.no(i).svcModFromSerNum_A, sMsg.A.no(i).svcModFromSerNum_A);
                setValue(aftSMsg.A.no(i).svcModToSerNum_A, sMsg.A.no(i).svcModToSerNum_A);
                continue;
            }
            if (hasValue(sMsg.A.no(i).svcModSerRngPk_A)) {
                errFlg = updateSvcModSerRng(cMsg, sMsg, aftSMsg.A, i);
            } else {
                errFlg = createSvcModSerRng(cMsg, sMsg, aftSMsg.A, i);
            }

            if (!errFlg) {
                return false;
            }
        }
        if (!deleteSvcModSerRng(cMsg, sMsg)) {
            return false;
        }

        NSBL0430SMsg rsltSMsg = (NSBL0430SMsg) sMsg.clone();
        rsltSMsg.clear();
        ZYPTableUtil.clear(rsltSMsg.A);
        getSearchData(cMsg, rsltSMsg);

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            // START 2017/12/14 M.Kidokoro [QC#22254, ADD]
            if (!ZYPConstant.FLG_ON_Y.equals(diffFlgArray[i])) {
                continue;
            }
            // END 2017/12/14 M.Kidokoro [QC#22254, ADD]
            if (!checkAfterSubmit(aftSMsg.A, rsltSMsg, i)) {
                sMsg.A.no(i).svcModFromSerNum_A.setErrorInfo(1, NSBM0118E, new String[] {SERIAL_NUM });
                sMsg.A.no(i).svcModToSerNum_A.setErrorInfo(1, NSBM0118E, new String[] {SERIAL_NUM });
                errFlg = false;
            }
        }
        return errFlg;
    }

    private static boolean checkSerRng(NSBL0430SMsg sMsg, int count) {

        String mdseCd = sMsg.A.no(count).mdseCd_A.getValue();
        String serFrom = sMsg.A.no(count).svcModFromSerNum_A.getValue();
        String serTo = sMsg.A.no(count).svcModToSerNum_A.getValue();

        if (serFrom.length() != serTo.length()) {
            sMsg.A.no(count).svcModFromSerNum_A.setErrorInfo(1, NSBM0137E);
            sMsg.A.no(count).svcModToSerNum_A.setErrorInfo(1, NSBM0137E);
            return false;
        }
        if (!isCheckSerialNum(serFrom, serTo, serFrom)
                || !isCheckSerialNum(serFrom, serTo, serTo)) {
            // QC#25666 Mod Start
//            sMsg.A.no(count).svcModFromSerNum_A.setErrorInfo(1, NSZM0860E, new String[] {SER_FROM_UPD, SER_TO_UPD });
//            sMsg.A.no(count).svcModToSerNum_A.setErrorInfo(1, NSZM0860E, new String[] {SER_FROM_UPD, SER_TO_UPD });
            sMsg.A.no(count).svcModFromSerNum_A.setErrorInfo(1, NSZM1330E, new String[] {SER_FROM_UPD, SER_TO_UPD });
            sMsg.A.no(count).svcModToSerNum_A.setErrorInfo(1, NSZM1330E, new String[] {SER_FROM_UPD, SER_TO_UPD });
            // QC#25666 Mod End

            return false;
        }

        int serFromLen = serFrom.length();
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            // del start 2016/04/12 CSA Defect#5224
            //if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A.getValue())) {
            // del start 2016/04/12 CSA Defect#5224
            if (i == count || !mdseCd.equals(sMsg.A.no(i).mdseCd_A.getValue()) || serFromLen != sMsg.A.no(i).svcModFromSerNum_A.getValue().length()) {
                continue;
            }

            if (isCheckSerialNum(sMsg.A.no(i).svcModFromSerNum_A.getValue(), sMsg.A.no(i).svcModToSerNum_A.getValue(), serFrom)
                    || isCheckSerialNum(sMsg.A.no(i).svcModFromSerNum_A.getValue(), sMsg.A.no(i).svcModToSerNum_A.getValue(), serTo)) {
                sMsg.A.no(count).svcModFromSerNum_A.setErrorInfo(1, NSBM0118E, new String[] {SERIAL_NUM });
                sMsg.A.no(count).svcModToSerNum_A.setErrorInfo(1, NSBM0118E, new String[] {SERIAL_NUM });
                return false;
            }
        }
        return true;
    }

    private static MDSE_SER_NUM_RNGTMsgArray getMdseSerReg(String glblCmpyCd, String mdseCd) {
        MDSE_SER_NUM_RNGTMsg param = new MDSE_SER_NUM_RNGTMsg();
        param.setSQLID("007");
        param.setConditionValue("glblCmpyCd01", glblCmpyCd);
        param.setConditionValue("mdseCd01", mdseCd + "%");
        MDSE_SER_NUM_RNGTMsgArray result = (MDSE_SER_NUM_RNGTMsgArray) EZDTBLAccessor.findByCondition(param);
        return result;
    }

    private static boolean checkSubmit(NSBL0430CMsg cMsg, NSBL0430SMsg sMsg, int count) {

        if (!checkSerRng(sMsg, count)) {
            return false;
        }

     // START 2017/02/15 N.Arai [QC#17562, MOD]
        if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(count).autoCratCallFlg_A.getValue()) && !hasValue(sMsg.A.no(count).autoCratRfrsTmgCd_A)) {
            sMsg.A.no(count).autoCratRfrsTmgCd_A.setErrorInfo(1, ZZM9000E, new String[]{"Auto Create Refresh Rate"});
            return false;
        }
     // END 2017/02/15 N.Arai [QC#17562, MOD]

        if (!checkAllMdseV(cMsg.glblCmpyCd.getValue(), sMsg.A.no(count).mdseCd_A.getValue())) {
            sMsg.A.no(count).xxChkBox_A.setErrorInfo(1,  NSBM0011E, new String[] {sMsg.A.no(count).mdseCd_A.getValue() });
            return false;
        }

        Map<String, Object> map = getDetailData(cMsg, sMsg.A.no(count).mdseCd_A.getValue());

        if (map == null || map.isEmpty()) {
            sMsg.A.no(count).xxChkBox_A.setErrorInfo(1, NSAM0063E, new String[] {sMsg.A.no(count).mdseCd_A.getValue(), MOD_SETAIL });
            return false;
        }

        MDSE_SER_NUM_RNGTMsgArray result = getMdseSerReg(cMsg.glblCmpyCd.getValue(), sMsg.A.no(count).mdseCd_A.getValue());

        if (result.getValidCount() != 0) {

            boolean msnrFlg = true;
            for (int i = 0; i < result.getValidCount(); i++) {
                if (result.no(i).lgSerNum.getValueInt() != sMsg.A.no(count).svcModFromSerNum_A.getValue().length()) {
                    continue;
                }

                if (isCheckSerialNum(result.no(i).fromSerNum.getValue(), result.no(i).thruSerNum.getValue(), sMsg.A.no(count).svcModFromSerNum_A.getValue())
                        && isCheckSerialNum(result.no(i).fromSerNum.getValue(), result.no(i).thruSerNum.getValue(), sMsg.A.no(count).svcModToSerNum_A.getValue())) {
                    break;
                } else {
                    msnrFlg = false;
                }
            }

            if (!msnrFlg) {
                sMsg.A.no(count).svcModFromSerNum_A.setErrorInfo(1, NSBM0138E);
                sMsg.A.no(count).svcModToSerNum_A.setErrorInfo(1, NSBM0138E);
                return false;
            }
        }

        if (!checkCallData(cMsg, sMsg.A.no(count))) {
            return false;
        }

        return true;
    }

    /**
     * getErrPageFromNum
     * @param cMsg NSBL0430CMsg
     * @param sMsg NSBL0430SMsg
     * @return int
     */
    public static int getErrPageFromNum(NSBL0430CMsg cMsg, NSBL0430SMsg sMsg) {

        int errIndex = 0;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            // Error
            if (sMsg.A.no(i).xxChkBox_A.isError()
                    || sMsg.A.no(i).svcModFromSerNum_A.isError()
                    || sMsg.A.no(i).svcModToSerNum_A.isError()) {
                errIndex = i;
                break;
            }
        }

        return errIndex / cMsg.A.length() * cMsg.A.length();
    }

    private static boolean createSvcModSerRng(NSBL0430CMsg cMsg, NSBL0430SMsg sMsg, NSBL0430_ASMsgArray asMsg, int count) {

        SVC_MOD_SER_RNGTMsg tMsg = new SVC_MOD_SER_RNGTMsg();

        setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(tMsg.svcModSerRngPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MOD_SER_RNG_SQ));
        setValue(tMsg.svcModPk, cMsg.svcModPk);
        setValue(tMsg.svcModDtlPk, sMsg.A.no(count).svcModDtlPk_A);
        setValue(tMsg.svcModFromSerNum, sMsg.A.no(count).svcModFromSerNum_A);
        setValue(tMsg.svcModToSerNum, sMsg.A.no(count).svcModToSerNum_A);
        setValue(tMsg.autoCratCallFlg, sMsg.A.no(count).autoCratCallFlg_A);
        setValue(tMsg.autoCratRfrsTmgCd, sMsg.A.no(count).autoCratRfrsTmgCd_A);
        setValue(tMsg.lgSerNum, new BigDecimal(sMsg.A.no(count).svcModFromSerNum_A.getValue().length()));
        setValue(tMsg.mdseCd, sMsg.A.no(count).mdseCd_A);

        EZDTBLAccessor.insert(tMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            cMsg.setMessageInfo(NSAM0031E, new String[] {"SVC_MOD_SER_RNG" });
            return false;
        }

        setValue(asMsg.no(count).svcModSerRngPk_A, tMsg.svcModSerRngPk);
        setValue(asMsg.no(count).mdseCd_A, sMsg.A.no(count).mdseCd_A);
        setValue(asMsg.no(count).svcModFromSerNum_A, sMsg.A.no(count).svcModFromSerNum_A);
        setValue(asMsg.no(count).svcModToSerNum_A, sMsg.A.no(count).svcModToSerNum_A);
        return true;
    }

    private static boolean updateSvcModSerRng(NSBL0430CMsg cMsg, NSBL0430SMsg sMsg, NSBL0430_ASMsgArray asMsg, int count) {

        SVC_MOD_SER_RNGTMsg tMsg = getsvcModSerRng(cMsg.glblCmpyCd.getValue(), sMsg.A.no(count).svcModSerRngPk_A.getValue());

        if (tMsg == null) {
            cMsg.setMessageInfo(NSBM0075E, new String[] {SVC_MOD_SER_RNG, SVC_MOD_SER_RNG_PK, sMsg.A.no(count).svcModSerRngPk_A.getValue().toString() });
            return false;
        }
        if (!ZYPDateUtil.isSameTimeStamp(sMsg.A.no(count).ezUpTime_A.getValue(), sMsg.A.no(count).ezUpTimeZone_A.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
            cMsg.setMessageInfo(NSBM0075E, new String[] {SVC_MOD_SER_RNG, SVC_MOD_SER_RNG_PK, sMsg.A.no(count).svcModSerRngPk_A.getValue().toString() });
            return false;
        }

        setValue(tMsg.svcModFromSerNum, sMsg.A.no(count).svcModFromSerNum_A);
        setValue(tMsg.svcModToSerNum, sMsg.A.no(count).svcModToSerNum_A);
        setValue(tMsg.autoCratCallFlg, sMsg.A.no(count).autoCratCallFlg_A);
        setValue(tMsg.autoCratRfrsTmgCd, sMsg.A.no(count).autoCratRfrsTmgCd_A);
        setValue(tMsg.lgSerNum, new BigDecimal(sMsg.A.no(count).svcModFromSerNum_A.getValue().length()));

        EZDTBLAccessor.update(tMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            cMsg.setMessageInfo(NSAM0031E, new String[] {"SVC_MOD_SER_RNG" });
            return false;
        }

        setValue(asMsg.no(count).svcModSerRngPk_A, sMsg.A.no(count).svcModSerRngPk_A);
        setValue(asMsg.no(count).mdseCd_A, sMsg.A.no(count).mdseCd_A);
        setValue(asMsg.no(count).svcModFromSerNum_A, sMsg.A.no(count).svcModFromSerNum_A);
        setValue(asMsg.no(count).svcModToSerNum_A, sMsg.A.no(count).svcModToSerNum_A);
        return true;
    }

    private static boolean deleteSvcModSerRng(NSBL0430CMsg cMsg, NSBL0430SMsg sMsg) {

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {

            SVC_MOD_SER_RNGTMsg tMsg = getsvcModSerRng(cMsg.glblCmpyCd.getValue(), sMsg.B.no(i).svcModSerRngPk_B.getValue());

            if (tMsg == null) {
                cMsg.setMessageInfo(NSBM0075E, new String[] {SVC_MOD_SER_RNG, SVC_MOD_SER_RNG_PK, sMsg.B.no(i).svcModSerRngPk_B.getValue().toString() });
                return false;
            }
            EZDTBLAccessor.logicalRemove(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                cMsg.setMessageInfo(NSAM0031E, new String[] {"SVC_MOD_SER_RNG" });
                return false;
            }
        }
        return true;
    }

    private static SVC_MOD_SER_RNGTMsg getsvcModSerRng(String glblCmpyCd, BigDecimal svcModSerRngPk) {
        SVC_MOD_SER_RNGTMsg prmTMsg = new SVC_MOD_SER_RNGTMsg();
        setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        setValue(prmTMsg.svcModSerRngPk, svcModSerRngPk);
        return (SVC_MOD_SER_RNGTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }

    private static boolean checkAfterSubmit(NSBL0430_ASMsgArray asMsgArray, NSBL0430SMsg aftSMsg, int count) {
        for (int i = 0; i < aftSMsg.A.getValidCount(); i++) {
            if ((asMsgArray.no(count).svcModSerRngPk_A.getValue().equals(aftSMsg.A.no(i).svcModSerRngPk_A.getValue()))
                    || !asMsgArray.no(count).mdseCd_A.getValue().equals(aftSMsg.A.no(i).mdseCd_A.getValue())) {
                continue;
            }
            if (asMsgArray.no(count).svcModFromSerNum_A.getValue().length() != aftSMsg.A.no(i).svcModFromSerNum_A.getValue().length()) {
                continue;
            }
            if (isCheckSerialNum(aftSMsg.A.no(i).svcModFromSerNum_A.getValue(), aftSMsg.A.no(i).svcModToSerNum_A.getValue(), asMsgArray.no(count).svcModFromSerNum_A.getValue())
                    || isCheckSerialNum(aftSMsg.A.no(i).svcModFromSerNum_A.getValue(), aftSMsg.A.no(i).svcModToSerNum_A.getValue(), asMsgArray.no(count).svcModToSerNum_A.getValue())) {
                return false;
            }
        }
        return true;
    }

    private static boolean isSubmit(NSBL0430_ASMsg asMsg) {
        if ((hasValue(asMsg.svcModCancDt_A) && ZYPDateUtil.isPastDate(asMsg.svcModCancDt_A.getValue()))
                || (hasValue(asMsg.svcModObslDt_A) && ZYPDateUtil.isPastDate(asMsg.svcModObslDt_A.getValue()))) {
            return false;

        } else {
            return true;
        }
    }
    // START 2017/10/26 U.Kim [QC#21797, ADD]
    public static String getAutoCratRfrsTmgCd(NSBL0430CMsg cMsg, NSBL0430F00FMsg fMsg) {
        String autoCratRfrsTmsCd = null;
        if (hasValue(fMsg.autoCratRfrsTmgDescTxt.getValue())) {
            autoCratRfrsTmsCd = NSBL0430Query.getInstance().getAutoCratRfrsTmgCd(cMsg, fMsg.autoCratRfrsTmgDescTxt.getValue());
        }
        return autoCratRfrsTmsCd;
    }
    // END 2017/10/26 U.Kim [QC#21797, ADD]

    // START 2017/12/14 M.Kidokoro [QC#22254, ADD]
    private static SVC_MOD_SER_RNGTMsg getSvcModSerRng(String glblCmpyCd, BigDecimal svcModSerRngPk) {
        SVC_MOD_SER_RNGTMsg inMsg = new SVC_MOD_SER_RNGTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.svcModSerRngPk, svcModSerRngPk);
        return (SVC_MOD_SER_RNGTMsg) EZDTBLAccessor.findByKey(inMsg);
    }

    private static boolean compare(SVC_MOD_SER_RNGTMsg tMsg, NSBL0430_ASMsg aSMsg) {
        int diffCnt = 0;
        boolean result = false;

        // svcModSerRngPk
        if (tMsg.svcModSerRngPk.getValue().compareTo(aSMsg.svcModSerRngPk_A.getValue()) != 0) {
            diffCnt++;
        }

        // svcModDtlPk
        if (tMsg.svcModDtlPk.getValue().compareTo(aSMsg.svcModDtlPk_A.getValue()) != 0) {
            diffCnt++;
        }

        // mdseCd
        if (tMsg.mdseCd.getValue().compareTo(aSMsg.mdseCd_A.getValue()) != 0) {
            diffCnt++;
        }

        // svcModFromSerNum
        if (tMsg.svcModFromSerNum.getValue().compareTo(aSMsg.svcModFromSerNum_A.getValue()) != 0) {
            diffCnt++;
        }

        // svcModToSerNum
        if (tMsg.svcModToSerNum.getValue().compareTo(aSMsg.svcModToSerNum_A.getValue()) != 0) {
            diffCnt++;
        }

        // autoCratCallFlg
        if (tMsg.autoCratCallFlg.getValue().compareTo(aSMsg.autoCratCallFlg_A.getValue()) != 0) {
            diffCnt++;
        }

        // autoCratRfrsTmgCd
        if (tMsg.autoCratRfrsTmgCd.getValue().compareTo(aSMsg.autoCratRfrsTmgCd_A.getValue()) != 0) {
            diffCnt++;
        }

        // ezUpTime
        if (tMsg.ezUpTime.getValue().compareTo(aSMsg.ezUpTime_A.getValue()) != 0) {
            diffCnt++;
        }

        // ezUpTimeZone
        if (tMsg.ezUpTimeZone.getValue().compareTo(aSMsg.ezUpTimeZone_A.getValue()) != 0) {
            diffCnt++;
        }

        if (diffCnt > 0) {
            result = true;
        }

        return result;
    }
    // END 2017/12/14 M.Kidokoro [QC#22254, ADD]
}
