/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSBL0200.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static business.blap.NSBL0200.constant.NSBL0200Constant.*;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;

import parts.common.EZDMsg;
import business.blap.NSBL0200.NSBL0200CMsg;
import business.blap.NSBL0200.NSBL0200Query;
import business.blap.NSBL0200.NSBL0200SMsg;

/** 
 *<pre>
 *
 * Technician Recommend Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/07/03   Hitachi         Y.Igarashi      Create          N/A
 * 2013/08/30   WDS Team        K.Aratani       Update          QC1457
 * 2015/11/25   Hitachi         T.Harada        Update          CSA,searchTechInfo
 * 2018/06/19   Fujitsu         T.Ogura         Update          QC#24735
 *</pre>
 */
public class NSBL0200CommonLogic {

    /**
     * 
     * searchTechInfo
     * 
     * @param cMsg NSBL0200CMsg
     * @param sMsg NSBL0200SMsg
     * @param gcc String
     * @param slsDt String
     */
    public static void searchTechInfo(NSBL0200CMsg cMsg, NSBL0200SMsg sMsg, String gcc, String slsDt) {

        cMsg.xxPageShowFromNum.setValue(0);
        sMsg.A.clear();
        sMsg.A.setValidCount(0);

        Map<String, Object> sc = new HashMap<String, Object>();

        sc.put("glblCmpyCd", gcc);
        String techCd = cMsg.techCd_SC.getValue();
        if (hasValue(techCd)) {
            techCd = techCd + "%";
        }
        sc.put("techCd", techCd);
        String techNm = cMsg.techNm_SC.getValue();
        if (hasValue(techNm)) {
            techNm = techNm.toUpperCase() + "%";
        }
        sc.put("techNm", techNm);
        sc.put("svcBrCd", cMsg.fldSvcBrCd_SC.getValue());
        sc.put("svcLineBizCd", cMsg.lineBizTpNm_SV.getValue());

        String training = cMsg.xxScrItem10Txt_SV.getValue();
        if (hasValue(training)) {
            sc.put("svcSkillNum", cMsg.svcSkillNum_SC.getValue());
            // START 2018/06/19 T.Ogura [QC#24735,DEL]
//            sc.put("flgY", ZYPConstant.FLG_ON_Y);
//            sc.put("slsDt", slsDt);
            // END   2018/06/19 T.Ogura [QC#24735,DEL]
            sc.put("training", cMsg.xxScrItem10Txt_SV.getValue());
            // START 2018/06/19 T.Ogura [QC#24735,DEL]
//            sc.put("thruDtLimit", THRU_DT_LIMIT);
            // END   2018/06/19 T.Ogura [QC#24735,DEL]
        }

        // START 2018/06/19 T.Ogura [QC#24735,ADD]
        sc.put("flgY", ZYPConstant.FLG_ON_Y);
        sc.put("slsDt", slsDt);
        sc.put("thruDtLimit", THRU_DT_LIMIT);
        // END   2018/06/19 T.Ogura [QC#24735,ADD]

        NSBL0200Query.getInstance().getTechList(sc, sMsg);

        if (sMsg.A.getValidCount() < 1) {
            cMsg.setMessageInfo(NZZM0000E);
            return;
        }
        copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    /**
     * 
     * initDateTime
     * 
     * @param cMsg NSBL0200CMsg
     * @param glblCmpyCd Global Company Code
     */
    public static void initDateTime(NSBL0200CMsg cMsg, String glblCmpyCd) {
        setValue(cMsg.techBakDt_SC, ZYPDateUtil.getSalesDate(glblCmpyCd));
        setValue(cMsg.xxHrsMn_SF, ZYPDateUtil.getCurrentSystemTime("HH:mm"));
        setValue(cMsg.xxHrsMn_ST, ZYPDateUtil.getCurrentSystemTime("HH:mm"));

        getTimeZone(cMsg, glblCmpyCd);
    }

    /**
     * 
     * getTimeZone
     * 
     * @param cMsg NSBL0200CMsg
     * @param glblCmpyCd Global Company Code
     */
    public static void getTimeZone(NSBL0200CMsg cMsg, String glblCmpyCd) {
        String hrMn = null;
        String dtTm = null;
        if (ZYPCommonFunc.hasValue(cMsg.xxHrsMn_SF)) {
            hrMn = cMsg.xxHrsMn_SF.getValue();
        } else if (ZYPCommonFunc.hasValue(cMsg.xxHrsMn_ST)) {
            hrMn = cMsg.xxHrsMn_ST.getValue();
        } else {
            hrMn = ZYPDateUtil.getCurrentSystemTime("HHmm");
        }
        if (hasValue(cMsg.techBakDt_SC)) {
            dtTm = cMsg.techBakDt_SC.getValue() + hrMn.replace(":", "");
            setValue(cMsg.techTz_SC, NSXC001001SvcTimeZone.getSysTimeZone(dtTm));
        }
    }

    /**
     * 
     * setPageNum
     * 
     * @param cMsg NSBL0200CMsg
     * @param fromCnt PageShowFromNum
     * @param toCnt PageShowToNum
     * @param allCnt PageShowOfNum
     */
    public static void setPageNum(NSBL0200CMsg cMsg, int fromCnt, int toCnt, int allCnt) {
        cMsg.xxPageShowFromNum.setValue(fromCnt);
        cMsg.xxPageShowToNum.setValue(toCnt);
        cMsg.xxPageShowOfNum.setValue(allCnt);
    }

    /**
     * 
     * clearPageNum
     * 
     * @param cMsg NSBL0200CMsg
     */
    public static void clearPageNum(NSBL0200CMsg cMsg) {
        cMsg.xxPageShowFromNum.clear();
        cMsg.xxPageShowToNum.clear();
        cMsg.xxPageShowOfNum.clear();
    }

    /**
     * 
     * copyFromSMsgOntoCmsg
     * 
     * @param cMsg NSBL0200CMsg
     * @param sMsg NSBL0200SMsg
     */
    public static void copyFromSMsgOntoCmsg(NSBL0200CMsg cMsg, NSBL0200SMsg sMsg) {
        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFrom);
        setPageNum(cMsg, (pagenationFrom + 1), (pagenationFrom + cMsg.A.getValidCount()), sMsg.A.getValidCount());
    }
}
