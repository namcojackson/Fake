/**
 * <pre>Copyright(c)2009 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLGL0030.common;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDMsg;
import business.blap.NLGL0030.NLGL0030CMsg;
import business.blap.NLGL0030.NLGL0030Query;
import business.blap.NLGL0030.NLGL0030SMsg;
import business.blap.NLGL0030.constant.NLGL0030Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WMS_ITEM_DNLD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;

/**
 * <pre>
 * ForcedItem Master download
 * 
 * Date           Company         Name              Create/Update     Defect No
 * ------------------------------------------------------------------------------------
 * 08/16/2013     CSAI            M.Shimamura       Create            MW Replace Initial
 *</pre>
 */
public class NLGL0030CommonLogic implements NLGL0030Constant {

    /**
     * This method gets mdse list from WMS_MDSE Table.
     * @param cMsg NLGL0030CMsg
     * @param sMsg NLGL0030SMsg
     */
    public static void getMdseHistList(NLGL0030CMsg cMsg, NLGL0030SMsg sMsg) {

        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
        ssmParam.put(DB_PARAM_WH_CD, cMsg.whCd_P0.getValue());
        ssmParam.put(DB_PARAM_WMS_MDSE_CD, cMsg.wmsMdseCd_H0.getValue());
        ssmParam.put(DB_PARAM_UOM_CA, UOM_CA);
        ssmParam.put(DB_PARAM_UOM_PL, UOM_PL);
        ssmParam.put(DB_PARAM_ROWNUM, sMsg.A.length());
        S21SsmEZDResult mdseList = NLGL0030Query.getInstance().getMdseHistList(cMsg, ssmParam, sMsg);

        if (!mdseList.isCodeNotFound()) {
            // 999over
            int queryResCnt = mdseList.getQueryResultCount();

            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
                queryResCnt = sMsg.A.length();
            }
            // 1page copy（globalMsg -> bizMsg）
            int rowCnt = sMsg.A.getValidCount();

            if (sMsg.A.getValidCount() > cMsg.A.length()) {
                rowCnt = cMsg.A.length();
            }

            for (int i = 0; i < rowCnt; i++) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.xxPageShowFromNum_H0.setValue(BigDecimal.ONE);
            cMsg.xxPageShowOfNum_H0.setValue(queryResCnt);
            copyFromSMsgOntoCmsg(cMsg, sMsg);
        } else {
            cMsg.setMessageInfo(NLGM0027W);
            cMsg.xxPageShowFromNum_H0.clear();
            cMsg.xxPageShowToNum_H0.clear();
            cMsg.xxPageShowOfNum_H0.clear();
        }
    }

    /**
     * Copy data From Smsg Onto Cmsg
     * @param cMsg NLGL0030CMsg
     * @param sMsg NLGL0030SMsg
     */
    public static void copyFromSMsgOntoCmsg(NLGL0030CMsg cMsg, NLGL0030SMsg sMsg) {

        int pagenationFromIndex = cMsg.xxPageShowFromNum_H0.getValueInt() - 1;
        int i = pagenationFromIndex;

        for (; i < pagenationFromIndex + cMsg.A.length(); i++) {

            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFromIndex), null);
                // Set Date Format
                if (ZYPCommonFunc.hasValue(cMsg.A.no(i - pagenationFromIndex).ezUpTime_D0)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(i - pagenationFromIndex).xxDtTm_D0 //
                            , (ZYPDateUtil.DateFormatter(cMsg.A.no(i - pagenationFromIndex).ezUpTime_D0.getValue() //
                            , YYYYMMDDHHMMSSSSS_BEFORE, YYYYMMDDHHMMSSSSS_AFTER)));
                }

                // Set Status Name
                if (ZYPCommonFunc.hasValue(cMsg.A.no(i - pagenationFromIndex).wmsItemDnldStsCd_D0)) {
                    cMsg.A.no(i - pagenationFromIndex).xxPrmoStsCd_D0.setValue(ZYPCodeDataUtil.getName(WMS_ITEM_DNLD_STS.class, cMsg.glblCmpyCd.getValue(), cMsg.A.no(i - pagenationFromIndex).wmsItemDnldStsCd_D0.getValue()));
                }
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFromIndex);
        cMsg.xxPageShowToNum_H0.setValue(pagenationFromIndex + cMsg.A.getValidCount());
    }

    /**
     * This method gets mdse list from MDSE Table
     * @param cMsg NLGL0030CMsg
     * @param sMsg NLGL0030SMsg
     */
    public static void getMdseDnldList(NLGL0030CMsg cMsg, NLGL0030SMsg sMsg) {

        // Check exist MDSE
        if (!existMdse(cMsg, sMsg)) {
            cMsg.mdseCd_H1.setErrorInfo(1, ZZZM9006E, new String[] {MSG_ITEM_NUMBER});
            return;
        }

        ZYPTableUtil.clear(cMsg.B);
        ZYPTableUtil.clear(sMsg.B);
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
        ssmParam.put(DB_PARAM_WH_CD, cMsg.whCd_P1.getValue());
        ssmParam.put(DB_PARAM_MDSE_CD, cMsg.mdseCd_H1.getValue());
        ssmParam.put(DB_PARAM_UOM_CA, UOM_CA);
        ssmParam.put(DB_PARAM_UOM_PL, UOM_PL);
        ssmParam.put(DB_PARAM_ROWNUM, sMsg.B.length());
        S21SsmEZDResult mdseList = NLGL0030Query.getInstance().getMdseDnldList(cMsg, ssmParam, sMsg);

        if (!mdseList.isCodeNotFound()) {
            int queryResCnt = mdseList.getQueryResultCount();

            if (queryResCnt > sMsg.B.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            }

            int rowCnt = sMsg.B.getValidCount();

            for (int i = 0; i < rowCnt; i++) {
                EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i), null);
                if (ZYPCommonFunc.hasValue(cMsg.B.no(i).ezUpTime_D2)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxDtTm_D1 //
                            , (ZYPDateUtil.DateFormatter(cMsg.B.no(i).ezUpTime_D2.getValue() //
                                    , YYYYMMDDHHMMSSSSS_BEFORE, YYYYMMDDHHMMSSSSS_AFTER)));
                }

                // Registered Check
                if (ZYPCommonFunc.hasValue(cMsg.B.no(i).ezUpTime_D2)) {
                    cMsg.setMessageInfo(NLGM0030W);
                } else {
                    cMsg.setMessageInfo(NLGM0031W);
                }

                // Batch Error Check
                if (ZYPCommonFunc.hasValue(cMsg.B.no(i).wmsItemDnldStsCd_D1)) {
                    if (PROC_STS.ERROR.equals(cMsg.B.no(i).wmsItemDnldStsCd_D1.getValue())) {
                        cMsg.setMessageInfo(NLGM0032W);
                    }
                }
            }
            cMsg.B.setValidCount(rowCnt);
        } else {
            cMsg.mdseCd_H1.setErrorInfo(1, ZZZM9006E, new String[] {MSG_ITEM_NUMBER});
        }
    }

    /**
     * This method gets mdse list from MDSE Table
     * @param cMsg NLGL0030CMsg
     * @param sMsg NLGL0030SMsg
     */
    public static void getMdseDnldListForSubmit(NLGL0030CMsg cMsg, NLGL0030SMsg sMsg) {

        ZYPTableUtil.clear(cMsg.B);
        ZYPTableUtil.clear(sMsg.B);
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
        ssmParam.put(DB_PARAM_WH_CD, cMsg.whCd_P1.getValue());
        ssmParam.put(DB_PARAM_MDSE_CD, cMsg.mdseCd_H1.getValue());
        ssmParam.put(DB_PARAM_UOM_CA, UOM_CA);
        ssmParam.put(DB_PARAM_UOM_PL, UOM_PL);
        ssmParam.put(DB_PARAM_ROWNUM, sMsg.B.length());
        S21SsmEZDResult mdseList = NLGL0030Query.getInstance().getMdseDnldList(cMsg, ssmParam, sMsg);

        if (!mdseList.isCodeNotFound()) {
            int queryResCnt = mdseList.getQueryResultCount();

            if (queryResCnt > sMsg.B.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            }

            int rowCnt = sMsg.B.getValidCount();

            for (int i = 0; i < rowCnt; i++) {
                EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i), null);
                if (ZYPCommonFunc.hasValue(cMsg.B.no(i).ezUpTime_D2)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).xxDtTm_D1 //
                            , (ZYPDateUtil.DateFormatter(cMsg.B.no(i).ezUpTime_D2.getValue() //
                                    , YYYYMMDDHHMMSSSSS_BEFORE, YYYYMMDDHHMMSSSSS_AFTER)));
                }
            }
            cMsg.B.setValidCount(rowCnt);
        } else {
            cMsg.setMessageInfo(NLGM0007E, new String[] {TBL_WMS_MDSE_LIST, cMsg.mdseCd_H1.getValue() });
        }
    }

    /**
     * check exist MDSE
     * @param cMsg NLGL0030CMsg
     * @param sMsg NLGL0030SMsg
     * @return boolean ture : Exist MDSE false : Not exsit MDSE
     */
    public static boolean existMdse(NLGL0030CMsg cMsg, NLGL0030SMsg sMsg) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
        ssmParam.put(DB_PARAM_MDSE_CD, cMsg.mdseCd_H1.getValue());
        S21SsmEZDResult ssmResult = NLGL0030Query.getInstance().cntMdse(ssmParam);

        if ((Integer) ssmResult.getResultObject() >= 1) {
            return true;
        }

        return false;
    }
}
