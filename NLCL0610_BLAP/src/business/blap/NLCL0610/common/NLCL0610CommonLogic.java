/**
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLCL0610.common;

import static business.blap.NLCL0610.constant.NLCL0610Constant.BIZ_ID;
import static business.blap.NLCL0610.constant.NLCL0610Constant.NZZM0000E;
import static business.blap.NLCL0610.constant.NLCL0610Constant.NZZM0001W;
import static business.blap.NLCL0610.constant.NLCL0610Constant.NZZM0010E;
import static business.blap.NLCL0610.constant.NLCL0610Constant.DB_COLUMN_RTL_WH_NM;
import static business.blap.NLCL0610.constant.NLCL0610Constant.TIMESTAMP_LENGTH;

import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import business.blap.NLCL0610.NLCL0610CMsg;
import business.blap.NLCL0610.NLCL0610Query;
import business.blap.NLCL0610.NLCL0610SMsg;
import business.parts.NLZC063001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC063001.NLZC063001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PHYS_INVTY_CNT_STS;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * PI Inquiry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/01   CITS            T.Gotoda        Create          N/A
 *</pre>
 */
public class NLCL0610CommonLogic {

    /**
     * Set Warehouse Name
     * @param cMsg NPAL1520CMsg
     * @param sMsg NPAL1520SMsg
     */
    public static void setWarehouseName(NLCL0610CMsg cMsg, NLCL0610SMsg sMsg) {

        S21SsmEZDResult ssmResult = NLCL0610Query.getInstance().getWarehouseName(cMsg);

        if (!ssmResult.isCodeNormal()) {
            cMsg.rtlWhNm.clear();
            cMsg.rtlWhCd.setErrorInfo(1, NZZM0010E, new String[] {cMsg.rtlWhCd.getValue()});
            return;
        }

        List<Map> rtlWhList = (List<Map>) ssmResult.getResultObject();
        if (rtlWhList.size() == 0) {
            cMsg.rtlWhNm.clear();
            cMsg.rtlWhCd.setErrorInfo(1, NZZM0010E, new String[] {cMsg.rtlWhCd.getValue()});
            return;
        }

        Map record = rtlWhList.get(0);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm, (String) record.get(DB_COLUMN_RTL_WH_NM));
    }

    /**
     * Search
     * @param cMsg NPAL1520CMsg
     * @param sMsg NPAL1520SMsg
     */
    public static void search(NLCL0610CMsg cMsg, NLCL0610SMsg sMsg) {

        S21SsmEZDResult ssmResult = NLCL0610Query.getInstance().searchPiList(cMsg, sMsg);

        // Got Result
        if (ssmResult.isCodeNormal()) {

            // Over Max count
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
                queryResCnt = sMsg.A.length();
            }

            String physInvtyNum = "";
            for (int j = 0; j < sMsg.A.getValidCount(); j++) {
                if (physInvtyNum.equals(sMsg.A.no(j).physInvtyNum_A.getValue())) {
                    sMsg.A.no(j).rtlWhCd_A.clear();
                    sMsg.A.no(j).rtlWhNm_A.clear();
                } else {
                    physInvtyNum = sMsg.A.no(j).physInvtyNum_A.getValue();
                }
                if (ZYPCommonFunc.hasValue(sMsg.A.no(j).physInvtyStartTs_A)) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).xxScrItem20Txt_A, ZYPDateUtil.formatEzd17ToDisp(sMsg.A.no(j).physInvtyStartTs_A.getValue()).substring(0, TIMESTAMP_LENGTH));
                }
            }

            // Copy（SMsg -> CMsg）
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            // Set Page count
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(queryResCnt);

        // No Result
        } else {
            cMsg.setMessageInfo(NZZM0000E);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
        }
    }

    /**
     * Call PI Close API
     * @param cMsg NLCL0610CMsg
     * @return boolean
     */
    public static boolean callPiCloseApi(NLCL0610CMsg cMsg) {

        int index = cMsg.xxRadioBtn_A.getValueInt();

        final NLZC063001 piCloseApi = new NLZC063001();

        NLZC063001PMsg pMsg = new NLZC063001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.salesDate,  ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue(), BIZ_ID));
        ZYPEZDItemValueSetter.setValue(pMsg.physInvtyCntStsCd, PHYS_INVTY_CNT_STS.PI_CANCELED);
        ZYPEZDItemValueSetter.setValue(pMsg.physInvtyNum, cMsg.A.no(index).physInvtyNum_A);

        piCloseApi.execute(pMsg, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            for (String xxMsgId : S21ApiUtil.getXxMsgIdList(pMsg)) {

                cMsg.setMessageInfo(xxMsgId);
                return false;
            }
        }

        return true;
    }
}
