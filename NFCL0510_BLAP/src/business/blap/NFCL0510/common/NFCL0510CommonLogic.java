package business.blap.NFCL0510.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsgArray;
import parts.common.EZDCStringItemArray;
import parts.common.EZDMsg;
import parts.common.EZDMsgCommons;
import parts.common.EZDSMsgArray;
import parts.common.EZDSStringItemArray;
import business.blap.NFCL0510.NFCL0510CMsg;
import business.blap.NFCL0510.NFCL0510Query;
import business.blap.NFCL0510.NFCL0510SMsg;
import business.blap.NFCL0510.constant.NFCL0510Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Lock box Error Correction Screen
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 06/11/2018   Hitachi         Y.Takeno        Create          QC#19723
 *</pre>
 */
public class NFCL0510CommonLogic implements NFCL0510Constant {

    /**
     * Copy Page
     * @param bizMsg NFCL0510CMsg
     * @param globalMsg NFCL0510SMsg
     * @return boolean
     */
    public static boolean copyPage(NFCL0510CMsg bizMsg, NFCL0510SMsg globalMsg) {
        EZDCMsgArray bizMsgAry = null;
        EZDSMsgArray shareMsgAry = null;

        if (globalMsg.A.getValidCount() > 0) {
            bizMsgAry = bizMsg.A;
            shareMsgAry = globalMsg.A;
            int startIndex = bizMsg.xxPageShowFromNum_A.getValueInt() - 1;
            int dispRowNum = 0;
            if (bizMsg.xxPageShowFromNum_A.getValueInt() < 0) {
                return true;
            }
            for (; dispRowNum < bizMsgAry.length() && dispRowNum < bizMsgAry.getValidCount(); dispRowNum++) {
            // START 2018/06/11 [QC#19723, DEL]
            //     if (!bizMsg.A.no(dispRowNum).arLockBoxStsCd_A.getValue().equals(globalMsg.A.no((startIndex + dispRowNum)).arLockBoxStsCd_A.getValue())) {
            //         for (int j = 0; j < bizMsg.A.getValidCount(); j++) {
            //             if (bizMsg.A.no(dispRowNum).arLockBoxFileNm_A.getValue().equals(bizMsg.A.no(j).arLockBoxFileNm_A.getValue())) {
            //                 ZYPEZDItemValueSetter.setValue(bizMsg.A.no(j).arLockBoxStsCd_A, bizMsg.A.no(dispRowNum).arLockBoxStsCd_A);
            //             }
            //         }
            //         for (int k = 0; k < globalMsg.A.getValidCount(); k++) {
            //             if (bizMsg.A.no(dispRowNum).arLockBoxFileNm_A.getValue().equals(globalMsg.A.no(k).arLockBoxFileNm_A.getValue())) {
            //                 ZYPEZDItemValueSetter.setValue(globalMsg.A.no(k).arLockBoxStsCd_A, bizMsg.A.no(dispRowNum).arLockBoxStsCd_A);
            //             }
            //         }
            //     }
            // END   2018/06/11 [QC#19723, DEL]
                EZDMsg.copy(bizMsgAry.get(dispRowNum), null, shareMsgAry.get(startIndex + dispRowNum), null);
            }
        }

        boolean hasError = false;

        if (globalMsg.B.getValidCount() > 0) {
            bizMsgAry = bizMsg.B;
            shareMsgAry = globalMsg.B;
            int startIndex = bizMsg.xxPageShowFromNum_B.getValueInt() - 1;
            int dispRowNum = 0;
            if (bizMsg.xxPageShowFromNum_B.getValueInt() < 0) {
                return true;
            }
            for (; dispRowNum < bizMsgAry.length() && dispRowNum < bizMsgAry.getValidCount(); dispRowNum++) {

                if (!bizMsg.B.no(dispRowNum).custRcptNum_B.getValue().equals(globalMsg.B.no((startIndex + dispRowNum)).custRcptNum_B.getValue())) {
                    for (int j = 0; j < bizMsg.B.getValidCount(); j++) {
                        if (bizMsg.B.no(dispRowNum).arLockBoxBatNum_B.getValue().equals(bizMsg.B.no(j).arLockBoxBatNum_B.getValue())
                                && (bizMsg.B.no(dispRowNum).arLockBoxBatLineNum_B.getValue().equals(bizMsg.B.no(j).arLockBoxBatLineNum_B.getValue()))) {
                            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(j).custRcptNum_B, bizMsg.B.no(dispRowNum).custRcptNum_B);
                        }
                    }
                    for (int k = 0; k < globalMsg.B.getValidCount(); k++) {
                        if (bizMsg.B.no(dispRowNum).arLockBoxBatNum_B.getValue().equals(globalMsg.B.no(k).arLockBoxBatNum_B.getValue())
                                && (bizMsg.B.no(dispRowNum).arLockBoxBatLineNum_B.getValue().equals(globalMsg.B.no(k).arLockBoxBatLineNum_B.getValue()))) {
                            ZYPEZDItemValueSetter.setValue(globalMsg.B.no(k).custRcptNum_B, bizMsg.B.no(dispRowNum).custRcptNum_B);
                        }
                    }
                }
                EZDMsg.copy(bizMsgAry.get(dispRowNum), null, shareMsgAry.get(startIndex + dispRowNum), null);
            }
        }
        bizMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_COMMIT);
        return !hasError;
    }

    /**
     * Display Page
     * @param glblCmpyCd String
     * @param bizMsg NFCL0510CMsg
     * @param globalMsg NFCL0510SMsg
     */
    public static void dispPage(String glblCmpyCd, NFCL0510CMsg bizMsg, NFCL0510SMsg globalMsg) {
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(bizMsg.B);

        if (globalMsg.A.getValidCount() > 0) {
            int startIndex = bizMsg.xxPageShowFromNum_A.getValueInt() - 1;
            int dispRowNum = 0;
            if (bizMsg.xxPageShowFromNum_A.getValueInt() < 0) {
                return;
            }
            for (; dispRowNum < bizMsg.A.length() && startIndex + dispRowNum < globalMsg.A.getValidCount(); dispRowNum++) {
                EZDMsg.copy(globalMsg.A.get(startIndex + dispRowNum), null, bizMsg.A.get(dispRowNum), null);
            }
            bizMsg.A.setValidCount(dispRowNum);
            bizMsg.xxPageShowToNum_A.setValue(startIndex + dispRowNum);
            bizMsg.xxPageShowOfNum_A.setValue(globalMsg.A.getValidCount());
        }
        if (globalMsg.B.getValidCount() > 0) {
            int startIndex = bizMsg.xxPageShowFromNum_B.getValueInt() - 1;
            int dispRowNum = 0;
            if (bizMsg.xxPageShowFromNum_B.getValueInt() < 0) {
                return;
            }
            for (; dispRowNum < bizMsg.B.length() && startIndex + dispRowNum < globalMsg.B.getValidCount(); dispRowNum++) {
                EZDMsg.copy(globalMsg.B.get(startIndex + dispRowNum), null, bizMsg.B.get(dispRowNum), null);

            }
            bizMsg.B.setValidCount(dispRowNum);
            bizMsg.xxPageShowToNum_B.setValue(startIndex + dispRowNum);
            bizMsg.xxPageShowOfNum_B.setValue(globalMsg.B.getValidCount());
            createPulldownListStatusMsgDetail(glblCmpyCd, bizMsg);
        }
    }

    /**
     * Previous Page
     * @param bizMsg NFCL0510CMsg
     * @param globalMsg NFCL0510SMsg
     */
    public static void prevPage(NFCL0510CMsg bizMsg, NFCL0510SMsg globalMsg) {
        if (bizMsg.xxPageTblNm.getValue().equals(TABLE_A)) {
            bizMsg.xxPageShowFromNum_A.setValue(bizMsg.xxPageShowFromNum_A.getValueInt() - bizMsg.A.length());
        } else if (bizMsg.xxPageTblNm.getValue().equals(TABLE_B)) {
            bizMsg.xxPageShowFromNum_B.setValue(bizMsg.xxPageShowFromNum_B.getValueInt() - bizMsg.B.length());
        }
    }

    /**
     * Next Page
     * @param bizMsg NFCL0510CMsg
     * @param globalMsg NFCL0510SMsg
     */
    public static void nextPage(NFCL0510CMsg bizMsg, NFCL0510SMsg globalMsg) {
        if (bizMsg.xxPageTblNm.getValue().equals(TABLE_A)) {
            bizMsg.xxPageShowFromNum_A.setValue(bizMsg.xxPageShowFromNum_A.getValueInt() + bizMsg.A.length());
        } else if (bizMsg.xxPageTblNm.getValue().equals(TABLE_B)) {
            bizMsg.xxPageShowFromNum_B.setValue(bizMsg.xxPageShowFromNum_B.getValueInt() + bizMsg.B.length());
        }
    }

    /**
     * Get Page Show From Number
     * @param index integer
     * @param bizMsg NFCL0510CMsg
     * @param globalMsg NFCL0510SMsg
     * @return BigDecimal 
     */
    public static BigDecimal getPageShowFrom(int index, NFCL0510CMsg bizMsg, NFCL0510SMsg globalMsg) {
        int pageCnt = index / bizMsg.A.length();
        int pageShowFrom = bizMsg.A.length() * pageCnt + 1;
        if (index % bizMsg.A.length() == 0) {
            pageShowFrom = pageShowFrom - bizMsg.A.length();
        }
        return new BigDecimal(pageShowFrom);
    }

    /**
     * Create Pull down list : Lock box Status
     * @param glblCmpyCd String
     * @param globalMsg NFCL0510SMsg
     */
    public static void createPulldownListLockBoxStatus(String glblCmpyCd, NFCL0510SMsg globalMsg) {

        for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
            List<String> arLockBoxStsCdList = new ArrayList<String>();
            arLockBoxStsCdList.add(globalMsg.A.no(i).arLockBoxStsCd_A.getValue());
            if (globalMsg.A.no(i).manUsbleLockBoxStsFlg_A.getValue().equals(ZYPConstant.FLG_ON_Y)) {
                arLockBoxStsCdList.add("R");
                arLockBoxStsCdList.add("C");
            }

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("arLockBoxStsCdList", arLockBoxStsCdList);
            S21SsmEZDResult arLockBoxSts = NFCL0510Query.getInstance().getLockBoxStatus(ssmParam);

            if (arLockBoxSts.isCodeNormal()) {
                List<Map> arLockBoxStsList = (List<Map>) arLockBoxSts.getResultObject();
                initPullDownCreate(globalMsg.A.no(i).arLockBoxStsCd_AC, globalMsg.A.no(i).arLockBoxStsDescTxt_AD, arLockBoxStsList, new String[] {"AR_LOCK_BOX_STS_CD", "AR_LOCK_BOX_STS_DESC_TXT" });
            }
        }
    }

    /**
     * Create Pull down list : Status Message Detail
     * @param glblCmpyCd String
     * @param bizMsg NFCL0510CMsg
     */
    public static void createPulldownListStatusMsgDetail(String glblCmpyCd, NFCL0510CMsg bizMsg) {

        for (int i = 0; i < bizMsg.B.getValidCount(); i++) {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("arRcptRcvIntfcPk", String.valueOf(bizMsg.B.no(i).arRcptRcvIntfcPk_B.getValue()));
            S21SsmEZDResult stsMsgDtl = NFCL0510Query.getInstance().getStatusMsgDetail(bizMsg, ssmParam);

            if (stsMsgDtl.isCodeNormal()) {
                List<Map> stsMsgDtlList = (List<Map>) stsMsgDtl.getResultObject();
                initPullDownCreate(bizMsg.B.no(i).arBatInfoLvlCd_BC, bizMsg.B.no(i).arBatInfoMsgTxt_BD, stsMsgDtlList, new String[] {"AR_BAT_INFO_LVL_CD", "AR_BAT_INFO_MSG_TXT" });
            }
        }
    }

    /**
     * Create Pull down List for initialize process. It makes [Code:Value].
     * @param pulldownCd EZDCStringItemArray
     * @param pulldownName EZDCStringItemArray
     * @param pullDownList List<Map>
     * @param dbColumn String[]
     */
    private static void initPullDownCreate(EZDCStringItemArray pulldownCd, EZDCStringItemArray pulldownName, List<Map> pullDownList, String[] dbColumn) {

        pulldownCd.clear();
        pulldownName.clear();

        for (int i = 0; i < pullDownList.size(); i++) {

            Map pullDownData = pullDownList.get(i);

            pulldownCd.no(i).setValue((String) pullDownData.get(dbColumn[0]));
            pulldownName.no(i).setValue(ZYPCommonFunc.concatString((String) pullDownData.get(dbColumn[0]), ":", (String) pullDownData.get(dbColumn[1])));
        }
    }

    /**
     * Create Pull down List for initialize process. It makes [Code:Value].
     * @param pulldownCd EZDSStringItemArray
     * @param pulldownName EZDSStringItemArray
     * @param pullDownList List<Map>
     * @param dbColumn String[]
     */
    private static void initPullDownCreate(EZDSStringItemArray pulldownCd, EZDSStringItemArray pulldownName, List<Map> pullDownList, String[] dbColumn) {

        pulldownCd.clear();
        pulldownName.clear();

        for (int i = 0; i < pullDownList.size(); i++) {

            Map pullDownData = pullDownList.get(i);

            pulldownCd.no(i).setValue((String) pullDownData.get(dbColumn[0]));
            pulldownName.no(i).setValue(ZYPCommonFunc.concatString((String) pullDownData.get(dbColumn[0]), ":", (String) pullDownData.get(dbColumn[1])));
        }
    }
}
