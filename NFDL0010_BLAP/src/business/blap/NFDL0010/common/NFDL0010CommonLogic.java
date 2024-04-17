package business.blap.NFDL0010.common;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_DISP_TP;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

import business.blap.NFDL0010.NFDL0010CMsg;
import business.blap.NFDL0010.constant.NFDL0010Constant;

/**
 *<pre>
 * Collection Summary
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 2018/10/24   Hitachi         J.Kim           Update          QC#28869
 *</pre>
 */
public class NFDL0010CommonLogic implements NFDL0010Constant {

    // START 2018/10/24 J.Kim [QC#28869,ADD]
    public static void outputSearchLog(NFDL0010CMsg cMsg) {

        StringBuffer sb = new StringBuffer();
        sb.append("NFDL0010 Search Condition :");
        if (ZYPCommonFunc.hasValue(cMsg.cltDispTpCd_H)) {
            String cltDispTpCd = cMsg.cltDispTpCd_H.getValue();
            if (CLT_DISP_TP.SELF.equals(cltDispTpCd)) {
                sb.append(" Display[").append(cltDispTpCd).append(" : Self").append("]");
            } else if (CLT_DISP_TP.MYGROUP.equals(cltDispTpCd)) {
                sb.append(" Display[").append(cltDispTpCd).append(" : MyGroup").append("]");
            }
        }

        if (ZYPCommonFunc.hasValue(cMsg.xxModeCd_H)) {
            String modeCd = cMsg.xxModeCd_H.getValue();
            if (SEARCH_MODE_CD_LOOKUP.equals(modeCd)) {
                sb.append(" Search Mode[").append(SEARCH_MODE_NM_LOOKUP).append("]");
            } else {
                sb.append(" Search Mode[").append(SEARCH_MODE_NM_SUMMARY).append("]");
            }
        }
        if (ZYPCommonFunc.hasValue(cMsg.xxQueryFltrTxt_H1)) {
            sb.append(" Collector[").append(cMsg.xxQueryFltrTxt_H1.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.cltPsnNm_H1)) {
            sb.append(" Collector Name(*)[").append(cMsg.cltPsnNm_H1.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.xxQueryFltrTxt_H2)) {
            sb.append(" Account#[").append(cMsg.xxQueryFltrTxt_H2.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.dsAcctNm_H)) {
            sb.append(" Account Name(*)[").append(cMsg.dsAcctNm_H.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.cltPtfoNm_H)) {
            sb.append(" Portfolio Name(*)[").append(cMsg.cltPtfoNm_H.getValue()).append("]");
        }
        if (ZYPCommonFunc.hasValue(cMsg.xxChkBox_H)) {
            sb.append(" Include Current[").append(cMsg.xxChkBox_H.getValue()).append("]");
        }
        S21InfoLogOutput.println(sb.toString());
    }
    // END 2018/10/24 J.Kim [QC#28869,ADD]
}
