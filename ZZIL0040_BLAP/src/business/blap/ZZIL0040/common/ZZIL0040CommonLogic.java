package business.blap.ZZIL0040.common;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import business.blap.ZZIL0040.ZZIL0040CMsg;
import business.blap.ZZIL0040.ZZIL0040Query;
import business.blap.ZZIL0040.ZZIL0040SMsg;
import business.blap.ZZIL0040.constant.ZZIL0040Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/18   Fujitsu         C.Ogaki         Create          N/A
 *</pre>
 */
public class ZZIL0040CommonLogic {

    /**
     * search request data from Staging Table
     * @param cMsg ZZIL0040CMsg
     * @param sMsg ZZIL0040SMsg
     */
    public static void searchRequestList(ZZIL0040CMsg cMsg, ZZIL0040SMsg sMsg) {

        // initialize the table data
        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        sMsg.clear();
        sMsg.A.clear();
        sMsg.A.setValidCount(0);

        S21SsmEZDResult ssmResult = ZZIL0040Query.getInstance().getRequestList(cMsg, sMsg);

        if (ssmResult.isCodeNormal()) {
            setChangeStsList(sMsg);

            // Max Over
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo("ZZZM9002W");
                queryResCnt = sMsg.A.length();
            }

            // Copy(SMsg -> CMsg)
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            // set pagenation parameters
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(queryResCnt);

        } else {
            cMsg.setMessageInfo("ZZZM9005W");
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
        }
    }

    /**
     * Set Change Status List.
     * @param sMsg ZZIL0040SMsg
     */
    private static void setChangeStsList(ZZIL0040SMsg sMsg) {
        // if "Status" is "COMPLETED", "FAILED1", or "FAILED2",
        // cahge status pulldown list setup
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            String rptSts = sMsg.A.no(i).intfcRqstStsTxt_A.getValue();
            if (Arrays.asList(ZZIL0040Constant.RQST_STS_CHANGEABLE).contains(rptSts)) {
                for (int j = 0; j < ZZIL0040Constant.RQST_STS_CHANGE_TO.length; j++) {
                    sMsg.A.no(i).intfcRqstStsTxt_AC.no(j).setValue(ZZIL0040Constant.RQST_STS_CHANGE_TO[j]);
                    sMsg.A.no(i).intfcRqstStsTxt_AD.no(j).setValue(ZZIL0040Constant.RQST_STS_CHANGE_TO[j]);
                }
            } else {
                sMsg.A.no(i).intfcRqstStsTxt_AC.no(0).setValue(rptSts);
                sMsg.A.no(i).intfcRqstStsTxt_AD.no(0).setValue(rptSts);
            }
        }
    }

    /**
     * check change status flag
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     * @param pagenationFrom Page Number
     * @param revFlg Revision value Flag
     * @return boolean
     */
    public static boolean chkChangeFlg(ZZIL0040CMsg cMsg, ZZIL0040SMsg sMsg, int pagenationFrom, boolean revFlg) {

        boolean retFlg = false;
        Map<Integer, String> flgMap = new HashMap<Integer, String>();

        // out of check and warning flag of check
        if (sMsg.A.getValidCount() <= 1 || "W".equals(cMsg.xxRsltFlg.getValue())) {
            cMsg.xxRsltFlg.clear();
            return retFlg;
        }

        // check change status
        String changeSts = null;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(cMsg.A.no(i).intfcRqstStsTxt_AS)) {
                changeSts = cMsg.A.no(i).intfcRqstStsTxt_AS.getValue();
                if (!changeSts.equals(cMsg.A.no(i).intfcRqstStsTxt_A.getValue())) {
                    flgMap.put(i, changeSts);
                }
            }
        }

        if (flgMap.size() > 0) {
            cMsg.A.clear();

            if (!revFlg) {
                pagenationFrom--;
            }

            int num = 0;
            int i = pagenationFrom;
            for (; i < pagenationFrom + cMsg.A.getValidCount(); i++) {
                if (i < sMsg.A.getValidCount()) {
                    num = i - pagenationFrom;

                    EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(num), null);
                    changeSts = flgMap.get(num);
                    if (changeSts != null) {
                        cMsg.A.no(num).intfcRqstStsTxt_AS.setValue(changeSts);
                    }
                } else {
                    break;
                }
            }
            cMsg.A.setValidCount(i - pagenationFrom);

            // set value to pagenation items
            cMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
            cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount());

            cMsg.xxRsltFlg.setValue("W");

            retFlg = true;
        }

        return retFlg;
    }

    /**
     * next and prev page
     * @param cMsgArray EZDCMsgArray
     * @param sMsgArray EZDSMsgArray
     * @param fromNum EZDCBigDecimalItem
     * @param toNum EZDCBigDecimalItem
     */
    public static void pageMove(EZDCMsgArray cMsgArray, EZDSMsgArray sMsgArray, EZDCBigDecimalItem fromNum, EZDCBigDecimalItem toNum) {
        // copy data from SMsg onto CMsg
        int pagenationFrom = fromNum.getValueInt();
        int i;
        for (i = pagenationFrom; i < pagenationFrom + cMsgArray.length(); i++) {
            if (i < sMsgArray.getValidCount()) {
                EZDMsg.copy(sMsgArray.get(i), null, cMsgArray.get(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsgArray.setValidCount(i - pagenationFrom);

        // set value to pagenation items
        fromNum.setValue(pagenationFrom + 1);
        toNum.setValue(pagenationFrom + cMsgArray.getValidCount());
    }

    /**
     * copy data value
     * @param cMsg ZZIL0040CMsg
     * @param sMsg ZZIL0040SMsg
     * @param pagenationFrom int
     */
    public static void setCopyVal(ZZIL0040CMsg cMsg, ZZIL0040SMsg sMsg, int pagenationFrom) {

        // set status, ezuptime
        for (int i = pagenationFrom; i < pagenationFrom + cMsg.A.getValidCount(); i++) {
            if (i < sMsg.A.getValidCount()) {
                cMsg.A.no(i - pagenationFrom).intfcRqstStsTxt_AS.clear();
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i - pagenationFrom).intfcRqstStsTxt_A, sMsg.A.no(i).intfcRqstStsTxt_A.getValue());
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i - pagenationFrom).xxDtTm_AS, sMsg.A.no(i).xxDtTm_AS.getValue());
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i - pagenationFrom).xxDtTm_AE, sMsg.A.no(i).xxDtTm_AE.getValue());
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i - pagenationFrom).ezUpTime_A, sMsg.A.no(i).ezUpTime_A.getValue());
            } else {
                break;
            }
        }
    }

}
