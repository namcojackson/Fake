/**
 * <pre> Copyright (c) 2013 Canon USA Inc. All rights reserved. </pre>
 */
package business.blap.NLGL0050.common;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import parts.common.EZDMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import business.blap.NLGL0050.NLGL0050CMsg;
import business.blap.NLGL0050.NLGL0050Query;
import business.blap.NLGL0050.NLGL0050SMsg;
import business.blap.NLGL0050.constant.NLGL0050Constant;

/**
 * <pre>
 * Ship Via Mapping from WMS to HOST
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/08/16   CSAI            Y.Miyauchi      Create          MW Replace Initial
 * 2018/03/09   CITS            M.Naito         Update          QC#24696
 *</pre>
 */
public class NLGL0050CommonLogic implements NLGL0050Constant {

    /**
     * The method explanation: This method updates sMsg checked data
     * of cMsg.
     * @param cMsg NLGL0050CMsg
     * @param sMsg NLGL0050SMsg
     */
    public static void keepSelectedRowInSMsg(NLGL0050CMsg cMsg, NLGL0050SMsg sMsg) {

        for (int i = cMsg.xxPageShowFromNum_A1.getValueInt(); i <= cMsg.xxPageShowToNum_A1.getValueInt(); i++) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i - 1).xxChkBox_A1, cMsg.A.no(i - cMsg.xxPageShowFromNum_A1.getValueInt()).xxChkBox_A1);
        }
    }

    /**
     * The method explanation: This method sets disp data(Carr Cd
     * List) of sMsg.
     * @param cMsg NLGL0050CMsg
     * @param sMsg NLGL0050SMsg
     */
    public static void copyFromSMsgOntoCmsg(NLGL0050CMsg cMsg, NLGL0050SMsg sMsg) {

        int pagenationFromIndex = cMsg.xxPageShowFromNum_A1.getValueInt() - 1;
        int iRoopCount = 0;
        for (int i = pagenationFromIndex; i < pagenationFromIndex + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFromIndex), null);
                if (ZYPCommonFunc.hasValue(cMsg.A.no(i - pagenationFromIndex).ezUpTime_A1)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(i - pagenationFromIndex).xxDtTm_A1//
                            , (ZYPDateUtil.DateFormatter(cMsg.A.no(i - pagenationFromIndex).ezUpTime_A1.getValue()//
                                    , YYYYMMDDHHMMSSSSS_BEFORE, YYYYMMDDHHMMSSSSS_AFTER)));
                }
                iRoopCount++;
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(iRoopCount);
        cMsg.xxPageShowToNum_A1.setValue(pagenationFromIndex + cMsg.A.getValidCount());
    }

    /**
     * The method explanation: This method gets carr code list from
     * WMS_SHIP_VIA.
     * @param bizMsg NLGL0050CMsg
     * @param globalMsg NLGL0050SMsg
     */
    public static void getCarrCdList(NLGL0050CMsg bizMsg, NLGL0050SMsg globalMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(globalMsg.A);
        bizMsg.xxPageShowFromNum_A1.setValue(BigDecimal.ZERO);
        bizMsg.xxPageShowToNum_A1.setValue(BigDecimal.ZERO);
        bizMsg.xxPageShowOfNum_A1.setValue(BigDecimal.ZERO);
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, bizMsg.glblCmpyCd.getValue());
        ssmParam.put(DB_PARAM_WMS_ORG_HOST_ID, bizMsg.wmsOrgHostId_P1.getValue());
        ssmParam.put(DB_PARAM_CARR_CD_ROW_NUM, globalMsg.A.length() + 1);
        if (!PULL_DOWN_ALL.equals(bizMsg.wmsTrnspTpCd_P1.getValue())) {
            ssmParam.put(DB_PARAM_WMS_TRNSP_TP_CD, bizMsg.wmsTrnspTpCd_P1.getValue());
        } else {
            ssmParam.put(DB_PARAM_WMS_TRNSP_TP_CD, DB_FIELD_BLANK);
        }
        // START 2018/03/09 M.Naito [QC#24696,ADD]
        ssmParam.put(DB_PARAM_WMS_CARR_CD, bizMsg.wmsCarrCd_T1.getValue());
        // END 2018/03/09 M.Naito [QC#24696,ADD]
        ssmParam.put(DB_PARAM_CARR_SCAC_CD, bizMsg.carrScacCd_T1.getValue());
        S21SsmEZDResult resultQuery = NLGL0050Query.getInstance().getCarrCodeList(bizMsg, ssmParam, globalMsg);

        if (!resultQuery.isCodeNotFound()) {

            int queryResCnt = resultQuery.getQueryResultCount();
            if (queryResCnt > globalMsg.A.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
                queryResCnt = globalMsg.A.length();
            }

            int iRoopCount = 0;

            for (int i = 0; i < globalMsg.A.getValidCount(); i++) {
                if (i == bizMsg.A.length()) {
                    break;
                }

                EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i), null);
                iRoopCount++;
            }
            bizMsg.A.setValidCount(iRoopCount);
            bizMsg.xxPageShowFromNum_A1.setValue(BigDecimal.ONE);
            bizMsg.xxPageShowToNum_A1.setValue(bizMsg.A.getValidCount());
            bizMsg.xxPageShowOfNum_A1.setValue(queryResCnt);
            copyFromSMsgOntoCmsg(bizMsg, globalMsg);
        } else {
            bizMsg.setMessageInfo(NLGM0027W);
            bizMsg.xxPageShowFromNum_A1.clear();
            bizMsg.xxPageShowToNum_A1.clear();
            bizMsg.xxPageShowOfNum_A1.clear();
        }
    }

    /**
     * The method explanation: This method gets carr code by
     * PrimaryKey from WMS_SHIP_VIA.
     * @param bizMsg NLGL0050CMsg
     * @param ssmParam Map<String, Object>
     * @param globalMsg NLGL0050SMsg
     */
    public static void getCarrCdEdit(NLGL0050CMsg bizMsg, NLGL0050SMsg globalMsg, Map<String, Object> ssmParam) {

        S21SsmEZDResult resultQuery = NLGL0050Query.getInstance().getCarrCdEdit(bizMsg, ssmParam, globalMsg);

        if (!resultQuery.isCodeNotFound()) {
            ZYPEZDItemValueSetter.setValue(bizMsg.wmsCarrCd_T2, globalMsg.wmsCarrCd_T2);
            ZYPEZDItemValueSetter.setValue(bizMsg.carrScacCd_T2, globalMsg.carrScacCd_T2);
            ZYPEZDItemValueSetter.setValue(bizMsg.carrSvcTxt_T2, globalMsg.carrSvcTxt_T2);
            ZYPEZDItemValueSetter.setValue(bizMsg.wmsTrnspTpCd_P2, globalMsg.wmsTrnspTpCd_P2);
            ZYPEZDItemValueSetter.setValue(bizMsg.wmsDescTxt_T2, globalMsg.wmsDescTxt_T2);
            ZYPEZDItemValueSetter.setValue(bizMsg.wmsCarrNm_T2, globalMsg.wmsCarrNm_T2);
            ZYPEZDItemValueSetter.setValue(bizMsg.shpgSvcLvlCd_P2, globalMsg.shpgSvcLvlCd_P2);
            ZYPEZDItemValueSetter.setValue(bizMsg.fuelUpchgTpCd_P2, globalMsg.fuelUpchgTpCd_P2);
            ZYPEZDItemValueSetter.setValue(bizMsg.fuelUpchgAmt_T2, globalMsg.fuelUpchgAmt_T2);
            ZYPEZDItemValueSetter.setValue(bizMsg.addlUpchgTpCd_P2, globalMsg.addlUpchgTpCd_P2);
            ZYPEZDItemValueSetter.setValue(bizMsg.addlUpchgAmt_T2, globalMsg.addlUpchgAmt_T2);
            ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime_T2, globalMsg.ezUpTime_T2);
            ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone_T2, globalMsg.ezUpTimeZone_T2);
        }
    }

    /**
     * The method explanation: This method sets the Host System Data
     * to pulldownlist of header.
     * @param cMsg NLBL0050CMsg
     */
    public static void setPullDownList_HostSystem(NLGL0050CMsg cMsg) {

        S21SsmEZDResult ssmResult = NLGL0050Query.getInstance().getHostSystemList(cMsg);

        // clears data
        cMsg.wmsOrgHostId_D1.clear();
        cMsg.xxEdtCdNm_D1.clear();

        if (!ssmResult.isCodeNotFound()) {
            List resultList = (List) ssmResult.getResultObject();

            int resultCnt = ssmResult.getQueryResultCount();
            if (resultCnt > cMsg.wmsOrgHostId_D1.length()) {
                resultCnt = cMsg.wmsOrgHostId_D1.length();
            }

            for (int i = 0; i < resultCnt; i++) {
                Map map = (Map) resultList.get(i);
                cMsg.wmsOrgHostId_D1.no(i).setValue((String) map.get(CN_WMS_ORG_HOST_ID));
                cMsg.xxEdtCdNm_D1.no(i).setValue((String) map.get(CN_WMS_ORG_HOST_ID));
            }
        }
    }

    /**
     * The method explanation: This method sets the Transport Mode
     * Data to pulldownlist of header.
     * @param cMsg NLBL0050CMsg
     */
    public static void setPullDownList_TransportMode(NLGL0050CMsg cMsg) {

        S21SsmEZDResult ssmResult = NLGL0050Query.getInstance().getTransportModeList(cMsg);

        // clears data
        cMsg.wmsTrnspTpCd_D2.clear();
        cMsg.xxEdtCdNm_D2.clear();

        if (!ssmResult.isCodeNotFound()) {
            List resultList = (List) ssmResult.getResultObject();

            int resultCnt = ssmResult.getQueryResultCount();
            if (resultCnt + 1 > cMsg.wmsTrnspTpCd_D2.length()) {
                resultCnt = cMsg.wmsTrnspTpCd_D2.length();
            }

            cMsg.wmsTrnspTpCd_D2.no(0).setValue(PULL_DOWN_ALL);
            cMsg.xxEdtCdNm_D2.no(0).setValue(PULL_DOWN_ALL);

            for (int i = 0; i < resultCnt; i++) {
                Map map = (Map) resultList.get(i);
                cMsg.wmsTrnspTpCd_D2.no(i + 1).setValue((String) map.get(CN_WMS_TRNSP_TP_CD));
                cMsg.xxEdtCdNm_D2.no(i + 1).setValue((String) map.get(CN_WMS_TRNSP_TP_CD) + PULL_DOWN_DELIMITER + (String) map.get(CN_WMS_TRNSP_TP_NM));
            }
        }
    }

    /**
     * The method explanation: This method sets the Transport Mode
     * Data to pulldownlist of edit.
     * @param cMsg NLBL0050CMsg
     */
    public static void setPullDownList_TransportModeEdit(NLGL0050CMsg cMsg) {

        S21SsmEZDResult ssmResult = NLGL0050Query.getInstance().getTransportModeList(cMsg);

        cMsg.wmsTrnspTpCd_T2.clear();
        cMsg.xxEdtCdNm_T2.clear();

        if (!ssmResult.isCodeNotFound()) {
            List resultList = (List) ssmResult.getResultObject();

            int resultCnt = ssmResult.getQueryResultCount();
            if (resultCnt + 1 > cMsg.wmsTrnspTpCd_T2.length()) {
                resultCnt = cMsg.wmsTrnspTpCd_T2.length();
            }

            for (int i = 0; i < resultCnt; i++) {
                Map map = (Map) resultList.get(i);
                cMsg.wmsTrnspTpCd_T2.no(i).setValue((String) map.get(CN_WMS_TRNSP_TP_CD));
                cMsg.xxEdtCdNm_T2.no(i).setValue((String) map.get(CN_WMS_TRNSP_TP_CD) + PULL_DOWN_DELIMITER + (String) map.get(CN_WMS_TRNSP_TP_NM));
            }
        }
    }

    /**
     * The method explanation: This method sets search Items on Event.
     * @param bizMsg NLBL0050CMsg
     */
    public static void setSearchItemsOnEvent(NLGL0050CMsg bizMsg) {
        if (ZYPCommonFunc.hasValue(bizMsg.wmsOrgHostId_B1)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.wmsOrgHostId_P1, bizMsg.wmsOrgHostId_B1);
            ZYPEZDItemValueSetter.setValue(bizMsg.wmsTrnspTpCd_P1, bizMsg.wmsTrnspTpCd_B1);
            // START 2018/03/09 M.Naito [QC#24696,ADD]
            ZYPEZDItemValueSetter.setValue(bizMsg.wmsCarrCd_T1, bizMsg.wmsCarrCd_B1);
            // END 2018/03/09 M.Naito [QC#24696,ADD]
            ZYPEZDItemValueSetter.setValue(bizMsg.carrScacCd_T1, bizMsg.carrScacCd_B1);
        }
    }
}
