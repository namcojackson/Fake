/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7150.common;

import static business.blap.NMAL7150.constant.NMAL7150Constant.*;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import parts.common.EZDSStringItem;
import business.blap.NMAL7150.NMAL7150CMsg;
import business.blap.NMAL7150.NMAL7150SMsg;
import business.blap.NMAL7150.NMAL7150_ASMsg;
import business.blap.NMAL7150.NMAL7150_BSMsg;
import business.file.NMAL7150F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;

/**
 *<pre>
 * CSMP Contract Synchronization  Errors Correction
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/16   fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NMAL7150CommonLogic {
    /**
     * setSeachResult
     * @param resultList List<Map< ? , ? >>
     * @param glblMsg NMAL7150SMsg
     */
    public static void setSeachResult(List<Map<String, Object>> resultList, NMAL7150SMsg glblMsg) {
        int i = 0;
        for (Map<String, Object> result : resultList) {
            NMAL7150_ASMsg glblLineMsg = glblMsg.A.no(i);
            ZYPEZDItemValueSetter.setValue(glblLineMsg.csmpContrIntfcPk_A, (BigDecimal) result.get("CSMP_CONTR_INTFC_PK"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.xxDt10Dt_A, (String) result.get("EZINTIME"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.csmpProcStsCd_A, (String) result.get("CSMP_PROC_STS_CD"));
            String processFlag = (String) result.get("CSMP_PROC_STS_CD") + ":" + result.get("CSMP_PROC_STS_DESC_TXT");
            ZYPEZDItemValueSetter.setValue(glblLineMsg.xxScrItem54Txt_A, processFlag);
            ZYPEZDItemValueSetter.setValue(glblLineMsg.csmpErrMsgTxt_A, (String) result.get("CSMP_ERR_MSG_TXT"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.dlrRefNum_A, (String) result.get("DLR_REF_NUM"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.csmpTrxStsCd_A, (String) result.get("CSMP_TRX_STS_CD"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.rtlDlrCd_A, (String) result.get("RTL_DLR_CD"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.dsAcctNm_A, (String) result.get("DS_ACCT_NM"));
            String csmp = (String) result.get("CSMP_NUM") + "-" + (String) result.get("CUSA_END_USR_NUM");
            ZYPEZDItemValueSetter.setValue(glblLineMsg.xxScrItem40Txt_A, csmp);
            ZYPEZDItemValueSetter.setValue(glblLineMsg.effFromDt_A, (String) result.get("EFF_FROM_DT"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.effThruDt_A, (String) result.get("EFF_THRU_DT"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.crListTxt_A, (String) result.get("CR_LIST_TXT"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.crListGnrnNum_A, (String) result.get("CR_LIST_GNRN_NUM"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.csmpContrStsCd_A, (String) result.get("CSMP_PROC_STS_CD"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.csmpRgCd_A, (String) result.get("CSMP_RG_CD"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.csmpCatgCd_A, (String) result.get("CSMP_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.vldFromDt_A, (String) result.get("VLD_FROM_DT"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.prevCsmpNum_A, (String) result.get("PREV_CSMP_NUM"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.prevUsrTxt_A, (String) result.get("PREV_USR_TXT"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.ezUpTime_A, (String) result.get("EZUPTIME"));
            ZYPEZDItemValueSetter.setValue(glblLineMsg.ezUpTimeZone_A, (String) result.get("EZUPTIMEZONE"));
            i++;
            if (i >= glblMsg.A.getValidCount()) {
                break;
            }
        }
        EZDMsg.copy(glblMsg.A, null, glblMsg.B, null);
    }

    /**
     * setSeachResult
     * @param rs ResultSet
     * @param fMsg NMAL7150SMsg
     * @throws SQLException SQLException
     */
    public static void setSeachResultForDownload(ResultSet rs, NMAL7150F00FMsg fMsg) throws SQLException {
        ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_CA, formatDt((String) rs.getString("EZINTIME")));
        String processFlag = (String) rs.getString("CSMP_PROC_STS_CD") + ":" + rs.getString("CSMP_PROC_STS_DESC_TXT");
        ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem54Txt_A, processFlag);
        ZYPEZDItemValueSetter.setValue(fMsg.csmpErrMsgTxt_A, (String) rs.getString("CSMP_ERR_MSG_TXT"));
        ZYPEZDItemValueSetter.setValue(fMsg.dlrRefNum_A, (String) rs.getString("DLR_REF_NUM"));
        ZYPEZDItemValueSetter.setValue(fMsg.csmpTrxStsCd_A, (String) rs.getString("CSMP_TRX_STS_CD"));
        ZYPEZDItemValueSetter.setValue(fMsg.rtlDlrCd_A, (String) rs.getString("RTL_DLR_CD"));
        ZYPEZDItemValueSetter.setValue(fMsg.dsAcctNm_A, (String) rs.getString("DS_ACCT_NM"));
        String csmp = (String) rs.getString("CSMP_NUM") + "-"  + (String) rs.getString("CUSA_END_USR_NUM");
        ZYPEZDItemValueSetter.setValue(fMsg.xxScrItem40Txt_A, csmp);
        ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_FR, formatDt((String) rs.getString("EFF_FROM_DT")));
        ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_TH, formatDt((String) rs.getString("EFF_THRU_DT")));
        ZYPEZDItemValueSetter.setValue(fMsg.crListTxt_A, (String) rs.getString("CR_LIST_TXT"));
        ZYPEZDItemValueSetter.setValue(fMsg.crListGnrnNum_A, (String) rs.getString("CR_LIST_GNRN_NUM"));
        ZYPEZDItemValueSetter.setValue(fMsg.csmpContrStsCd_A, (String) rs.getString("CSMP_PROC_STS_CD"));
        ZYPEZDItemValueSetter.setValue(fMsg.csmpRgCd_A, (String) rs.getString("CSMP_RG_CD"));
        ZYPEZDItemValueSetter.setValue(fMsg.csmpCatgCd_A, (String) rs.getString("CSMP_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_VA, formatDt((String) rs.getString("VLD_FROM_DT")));
        ZYPEZDItemValueSetter.setValue(fMsg.prevCsmpNum_A, (String) rs.getString("PREV_CSMP_NUM"));
        ZYPEZDItemValueSetter.setValue(fMsg.prevUsrTxt_A, (String) rs.getString("PREV_USR_TXT"));

    }

    /**
     * loadOnePageToCMsg
     * @param cMsg EZDCMsg
     * @param cMsgArray EZDCMsgArray
     * @param sMsgArray EZDSMsgArray
     */
    public static void loadOnePageToCMsg(EZDCMsg cMsg, EZDCMsgArray cMsgArray, EZDSMsgArray sMsgArray) {

        NMAL7150CMsg bizMsg = (NMAL7150CMsg) cMsg;

        ZYPTableUtil.clear(cMsgArray);

        bizMsg.xxPageShowToNum.clear();
        bizMsg.xxPageShowOfNum.clear();

        int maxDisplayRows = cMsgArray.length();
        int startIndex = (bizMsg.xxPageShowFromNum.getValueInt() / maxDisplayRows) * maxDisplayRows;

        int i = startIndex;
        for (; i < startIndex + cMsgArray.length(); i++) {

            if (i < sMsgArray.getValidCount()) {

                EZDMsg sLineMsg = sMsgArray.get(i);
                int indexOfCMsg = i - startIndex;

                EZDMsg.copy(sLineMsg, null, cMsgArray.get(indexOfCMsg), null);

            } else {

                break;
            }
        }

        cMsgArray.setValidCount(i - startIndex);
        bizMsg.xxPageShowFromNum.setValue(startIndex + 1);
        bizMsg.xxPageShowToNum.setValue(startIndex + cMsgArray.getValidCount());
        bizMsg.xxPageShowOfNum.setValue(sMsgArray.getValidCount());
    }

    /**
     * Update the global Message.
     * @param bizMsg NMAL7150CMsg
     * @param glblMsg NMAL7150SMsg
     */
    public static void updateGlblMsg(NMAL7150CMsg bizMsg, NMAL7150SMsg glblMsg) {
        int ixG = bizMsg.xxPageShowFromNum.getValueInt() - 1;

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            EZDMsg.copy(bizMsg.A.no(i), null, glblMsg.A.no(ixG + i), null);
        }
        bizMsg.setCommitSMsg(true);
    }

    /**
     * isChangeDetail
     * @param asMsg NMAL7150_ASMsg
     * @param bsMsg NMAL7150_BSMsg
     * @return boolean
     */
    public static boolean isChangeDetail(NMAL7150_ASMsg asMsg, NMAL7150_BSMsg bsMsg) {
        if (!isEqual(bsMsg.dlrRefNum_A, asMsg.dlrRefNum_A) //
                || !isEqual(bsMsg.prevCsmpNum_A, asMsg.prevCsmpNum_A) //
                || !isEqual(bsMsg.prevUsrTxt_A, asMsg.prevUsrTxt_A) //
                || !isEqual(bsMsg.csmpProcStsCd_A, asMsg.csmpProcStsCd_A) //
        ) {
            return true;

        }
        return false;
    }

    private static boolean isEqual(EZDSStringItem item1, EZDSStringItem item2) {
        boolean item1HasVal = ZYPCommonFunc.hasValue(item1);
        boolean item2HasVal = ZYPCommonFunc.hasValue(item2);
        if (!item1HasVal && !item2HasVal) {
            return true;
        }
        if (item1HasVal && item2HasVal && item1.getValue().equals(item2.getValue())) {
            return true;
        }
        return false;
    }

    /**
     * formatDt
     * @param dt String
     * @return String formated
     */
    public static String formatDt(String dt) {

        if (!ZYPCommonFunc.hasValue(dt)) {
            return "";
        } else if (dt.length() > YYYYMMDD_LENGTH) {
            dt = dt.substring(0, YYYYMMDD_LENGTH);
        }

        return ZYPDateUtil.formatEzd8ToDisp(dt, true);
    }

}
