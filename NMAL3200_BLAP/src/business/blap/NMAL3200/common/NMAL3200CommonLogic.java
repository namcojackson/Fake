/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL3200.common;

import static business.blap.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_01;
import static business.blap.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_02;
import static business.blap.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_03;
import static business.blap.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_04;
import static business.blap.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_05;
import static business.blap.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_06;
import static business.blap.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_07;
import static business.blap.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_08;
import static business.blap.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_09;
import static business.blap.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_10;
import static business.blap.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_11;
import static business.blap.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_12;
import static business.blap.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_13;
import static business.blap.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_14;
import static business.blap.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_15;
import static business.blap.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_16;
import static business.blap.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_17;
import static business.blap.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_18;
import static business.blap.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_19;
import static business.blap.NMAL3200.constant.NMAL3200Constant.ATTRB_TXT_COL_DFN_NM_20;
import static business.blap.NMAL3200.constant.NMAL3200Constant.CNTY_PK_COL_DFN_NM;
import static business.blap.NMAL3200.constant.NMAL3200Constant.CTRY_CD_COL_DFN_NM;
import static business.blap.NMAL3200.constant.NMAL3200Constant.CTY_ADDR_COL_DFN_NM;
import static business.blap.NMAL3200.constant.NMAL3200Constant.DS_ACCT_NM_COL_DFN_NM;
import static business.blap.NMAL3200.constant.NMAL3200Constant.DUNS_NUM_COL_DFN_NM;
import static business.blap.NMAL3200.constant.NMAL3200Constant.EZUPTIME;
import static business.blap.NMAL3200.constant.NMAL3200Constant.EZUPTIMEZONE;
import static business.blap.NMAL3200.constant.NMAL3200Constant.FIRST_LINE_ADDR_COL_DFN_NM;
import static business.blap.NMAL3200.constant.NMAL3200Constant.FRTH_LINE_ADDR_COL_DFN_NM;
import static business.blap.NMAL3200.constant.NMAL3200Constant.GLN_COL_DFN_NM;
import static business.blap.NMAL3200.constant.NMAL3200Constant.HIN_COL_DFN_NM;
import static business.blap.NMAL3200.constant.NMAL3200Constant.MKTG_FLD_MAP_PK;
import static business.blap.NMAL3200.constant.NMAL3200Constant.MKTG_MAP_RQST_PK;
import static business.blap.NMAL3200.constant.NMAL3200Constant.POST_CD_COL_DFN_NM;
import static business.blap.NMAL3200.constant.NMAL3200Constant.SCD_LINE_ADDR_COL_DFN_NM;
import static business.blap.NMAL3200.constant.NMAL3200Constant.ST_CD_COL_DFN_NM;
import static business.blap.NMAL3200.constant.NMAL3200Constant.THIRD_LINE_ADDR_COL_DFN_NM;

import java.math.BigDecimal;
import java.util.Map;

import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import business.blap.NMAL3200.NMAL3200CMsg;
import business.blap.NMAL3200.NMAL3200SMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;

/**
 * <pre>
 * Business ID : NMAL3200 Marketing Data Analysis
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/24/2016   CITS            K.Ogino         Create          N/A
 *</pre>
 */
public class NMAL3200CommonLogic {

    /**
     * MKTG_FLD_MAP Data Clear
     * @param bizMsg NMAL3200CMsg
     */
    public static void mktgFldMapClear(NMAL3200CMsg bizMsg) {
        bizMsg.dsAcctNmColDfnNm.clear();
        bizMsg.firstLineAddrColDfnNm.clear();
        bizMsg.scdLineAddrColDfnNm.clear();
        bizMsg.thirdLineAddrColDfnNm.clear();
        bizMsg.frthLineAddrColDfnNm.clear();
        bizMsg.ctyAddrColDfnNm.clear();
        bizMsg.cntyPkColDfnNm.clear();
        bizMsg.stCdColDfnNm.clear();
        bizMsg.postCdColDfnNm.clear();
        bizMsg.ctryCdColDfnNm.clear();
        bizMsg.dunsNumColDfnNm.clear();
        bizMsg.glnColDfnNm.clear();
        bizMsg.hinColDfnNm.clear();
        bizMsg.attrbTxtColDfnNm_01.clear();
        bizMsg.attrbTxtColDfnNm_02.clear();
        bizMsg.attrbTxtColDfnNm_03.clear();
        bizMsg.attrbTxtColDfnNm_04.clear();
        bizMsg.attrbTxtColDfnNm_05.clear();
        bizMsg.attrbTxtColDfnNm_06.clear();
        bizMsg.attrbTxtColDfnNm_07.clear();
        bizMsg.attrbTxtColDfnNm_08.clear();
        bizMsg.attrbTxtColDfnNm_09.clear();
        bizMsg.attrbTxtColDfnNm_10.clear();
        bizMsg.attrbTxtColDfnNm_11.clear();
        bizMsg.attrbTxtColDfnNm_12.clear();
        bizMsg.attrbTxtColDfnNm_13.clear();
        bizMsg.attrbTxtColDfnNm_14.clear();
        bizMsg.attrbTxtColDfnNm_15.clear();
        bizMsg.attrbTxtColDfnNm_16.clear();
        bizMsg.attrbTxtColDfnNm_17.clear();
        bizMsg.attrbTxtColDfnNm_18.clear();
        bizMsg.attrbTxtColDfnNm_19.clear();
        bizMsg.attrbTxtColDfnNm_20.clear();
    }

    /**
     * loadOnePageToCMsg
     * @param bizMsg NMAL3200CMsg
     * @param cMsgArray EZDCMsgArray
     * @param glblMsg NMAL3200SMsg
     * @param sMsgArray EZDSMsgArray
     */
    public static void loadOnePageToCMsg(NMAL3200CMsg bizMsg, EZDCMsgArray cMsgArray, NMAL3200SMsg glblMsg, EZDSMsgArray sMsgArray) {

        ZYPTableUtil.clear(cMsgArray);

        bizMsg.xxPageShowToNum.clear();
        bizMsg.xxPageShowOfNum.clear();

        int maxDisplayRows = cMsgArray.length();
        int startIndex = (bizMsg.xxPageShowFromNum.getValueInt() / maxDisplayRows) * maxDisplayRows;

        int i = startIndex;
        if (startIndex == sMsgArray.getValidCount()) {
            startIndex = startIndex - cMsgArray.length();
        }
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
     * set MKTG_FLD_MAP Data
     * @param cMsg NMAL3200CMsg
     * @param map Map
     */
    public static void setMktgFldMap(NMAL3200CMsg cMsg, Map<String, Object> map) {
        ZYPEZDItemValueSetter.setValue(cMsg.mktgMapRqstPk_UP, (BigDecimal) map.get(MKTG_MAP_RQST_PK));
        ZYPEZDItemValueSetter.setValue(cMsg.mktgFldMapPk, (BigDecimal) map.get(MKTG_FLD_MAP_PK));
        ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNmColDfnNm, (String) map.get(DS_ACCT_NM_COL_DFN_NM));
        ZYPEZDItemValueSetter.setValue(cMsg.firstLineAddrColDfnNm, (String) map.get(FIRST_LINE_ADDR_COL_DFN_NM));
        ZYPEZDItemValueSetter.setValue(cMsg.scdLineAddrColDfnNm, (String) map.get(SCD_LINE_ADDR_COL_DFN_NM));
        ZYPEZDItemValueSetter.setValue(cMsg.thirdLineAddrColDfnNm, (String) map.get(THIRD_LINE_ADDR_COL_DFN_NM));
        ZYPEZDItemValueSetter.setValue(cMsg.frthLineAddrColDfnNm, (String) map.get(FRTH_LINE_ADDR_COL_DFN_NM));
        ZYPEZDItemValueSetter.setValue(cMsg.cntyPkColDfnNm, (String) map.get(CNTY_PK_COL_DFN_NM));
        ZYPEZDItemValueSetter.setValue(cMsg.ctyAddrColDfnNm, (String) map.get(CTY_ADDR_COL_DFN_NM));
        ZYPEZDItemValueSetter.setValue(cMsg.stCdColDfnNm, (String) map.get(ST_CD_COL_DFN_NM));
        ZYPEZDItemValueSetter.setValue(cMsg.postCdColDfnNm, (String) map.get(POST_CD_COL_DFN_NM));
        ZYPEZDItemValueSetter.setValue(cMsg.ctryCdColDfnNm, (String) map.get(CTRY_CD_COL_DFN_NM));
        ZYPEZDItemValueSetter.setValue(cMsg.dunsNumColDfnNm, (String) map.get(DUNS_NUM_COL_DFN_NM));
        ZYPEZDItemValueSetter.setValue(cMsg.glnColDfnNm, (String) map.get(GLN_COL_DFN_NM));
        ZYPEZDItemValueSetter.setValue(cMsg.hinColDfnNm, (String) map.get(HIN_COL_DFN_NM));
        ZYPEZDItemValueSetter.setValue(cMsg.attrbTxtColDfnNm_01, (String) map.get(ATTRB_TXT_COL_DFN_NM_01));
        ZYPEZDItemValueSetter.setValue(cMsg.attrbTxtColDfnNm_02, (String) map.get(ATTRB_TXT_COL_DFN_NM_02));
        ZYPEZDItemValueSetter.setValue(cMsg.attrbTxtColDfnNm_03, (String) map.get(ATTRB_TXT_COL_DFN_NM_03));
        ZYPEZDItemValueSetter.setValue(cMsg.attrbTxtColDfnNm_04, (String) map.get(ATTRB_TXT_COL_DFN_NM_04));
        ZYPEZDItemValueSetter.setValue(cMsg.attrbTxtColDfnNm_05, (String) map.get(ATTRB_TXT_COL_DFN_NM_05));
        ZYPEZDItemValueSetter.setValue(cMsg.attrbTxtColDfnNm_06, (String) map.get(ATTRB_TXT_COL_DFN_NM_06));
        ZYPEZDItemValueSetter.setValue(cMsg.attrbTxtColDfnNm_07, (String) map.get(ATTRB_TXT_COL_DFN_NM_07));
        ZYPEZDItemValueSetter.setValue(cMsg.attrbTxtColDfnNm_08, (String) map.get(ATTRB_TXT_COL_DFN_NM_08));
        ZYPEZDItemValueSetter.setValue(cMsg.attrbTxtColDfnNm_09, (String) map.get(ATTRB_TXT_COL_DFN_NM_09));
        ZYPEZDItemValueSetter.setValue(cMsg.attrbTxtColDfnNm_10, (String) map.get(ATTRB_TXT_COL_DFN_NM_10));
        ZYPEZDItemValueSetter.setValue(cMsg.attrbTxtColDfnNm_11, (String) map.get(ATTRB_TXT_COL_DFN_NM_11));
        ZYPEZDItemValueSetter.setValue(cMsg.attrbTxtColDfnNm_12, (String) map.get(ATTRB_TXT_COL_DFN_NM_12));
        ZYPEZDItemValueSetter.setValue(cMsg.attrbTxtColDfnNm_13, (String) map.get(ATTRB_TXT_COL_DFN_NM_13));
        ZYPEZDItemValueSetter.setValue(cMsg.attrbTxtColDfnNm_14, (String) map.get(ATTRB_TXT_COL_DFN_NM_14));
        ZYPEZDItemValueSetter.setValue(cMsg.attrbTxtColDfnNm_15, (String) map.get(ATTRB_TXT_COL_DFN_NM_15));
        ZYPEZDItemValueSetter.setValue(cMsg.attrbTxtColDfnNm_16, (String) map.get(ATTRB_TXT_COL_DFN_NM_16));
        ZYPEZDItemValueSetter.setValue(cMsg.attrbTxtColDfnNm_17, (String) map.get(ATTRB_TXT_COL_DFN_NM_17));
        ZYPEZDItemValueSetter.setValue(cMsg.attrbTxtColDfnNm_18, (String) map.get(ATTRB_TXT_COL_DFN_NM_18));
        ZYPEZDItemValueSetter.setValue(cMsg.attrbTxtColDfnNm_19, (String) map.get(ATTRB_TXT_COL_DFN_NM_19));
        ZYPEZDItemValueSetter.setValue(cMsg.attrbTxtColDfnNm_20, (String) map.get(ATTRB_TXT_COL_DFN_NM_20));
        ZYPEZDItemValueSetter.setValue(cMsg.ezUpTime, (String) map.get(EZUPTIME));
        ZYPEZDItemValueSetter.setValue(cMsg.ezUpTimeZone, (String) map.get(EZUPTIMEZONE));
    }
}
