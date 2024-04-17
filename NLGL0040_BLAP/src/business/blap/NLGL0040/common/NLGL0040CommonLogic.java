/**
 * <pre>Copyright(c)2009 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLGL0040.common;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDMsg;
import parts.common.EZDMsgCommons;
import business.blap.NLGL0040.NLGL0040CMsg;
import business.blap.NLGL0040.NLGL0040Query;
import business.blap.NLGL0040.NLGL0040SMsg;
import business.blap.NLGL0040.constant.NLGL0040Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Ship Via Mapping from HOST to WMS
 * Date           Company         Name              Create/Update     Defect No
 * ------------------------------------------------------------------------------------
 * 08/12/2013     CSAI            N.Sekine          Create             MW Replace Initial
 *</pre>
 */
public class NLGL0040CommonLogic implements NLGL0040Constant {

    /**
     * The method explanation: keep selected rows in sMsg.
     * @param cMsg NLGL0040CMsg
     * @param sMsg NLGL0040SMsg
     */
    public static void keepSelectedRowInSMsg(NLGL0040CMsg cMsg, NLGL0040SMsg sMsg) {

        int pagenationFrom = cMsg.xxPageShowFromNum_D1.getValueInt();
        int pagenationTo = cMsg.xxPageShowToNum_D1.getValueInt();

        for (int i = pagenationFrom; i <= pagenationTo; i++) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i - 1).xxChkBox_D1, cMsg.A.no(i - pagenationFrom).xxChkBox_D1);
        }
    }

    /**
     * copyFromSmsgOntoCmsg
     * Copy data From Smsg Onto Cmsg
     * @param cMsg NLGL0040CMsg
     * @param sMsg NLGL0040SMsg
     */
    public static void copyFromSMsgOntoCmsg(NLGL0040CMsg cMsg, NLGL0040SMsg sMsg) {

        int pagenationFromIndex = cMsg.xxPageShowFromNum_D1.getValueInt() - 1;
        int i = pagenationFromIndex;

        for (; i < pagenationFromIndex + cMsg.A.length(); i++) {

            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFromIndex), null);

                if (ZYPCommonFunc.hasValue(cMsg.A.no(i - pagenationFromIndex).ezUpTime_D1)) {
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(i - pagenationFromIndex).xxDtTm_D1//
                            , (ZYPDateUtil.DateFormatter(cMsg.A.no(i - pagenationFromIndex).ezUpTime_D1.getValue()//
                            , YYYYMMDDHHMMSSSSS_BEFORE, YYYYMMDDHHMMSSSSS_AFTER)));
                }
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFromIndex);
        cMsg.xxPageShowToNum_D1.setValue(pagenationFromIndex + cMsg.A.getValidCount());
    }

    /**
     * getShipViaList
     * to get WMS_SHIP_VIA_RTE_MAP DataList 
     * @param bizMsg NLGL0040CMsg
     * @param globalMsg NLGL0040SMsg
     */
    public static void getShipViaList(NLGL0040CMsg bizMsg, NLGL0040SMsg globalMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(globalMsg.A);
        bizMsg.xxPageShowFromNum_D1.setValue(BigDecimal.ZERO);
        bizMsg.xxPageShowToNum_D1.setValue(BigDecimal.ZERO);
        bizMsg.xxPageShowOfNum_D1.setValue(BigDecimal.ZERO);
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, bizMsg.glblCmpyCd.getValue());
        ssmParam.put(DB_PARAM_WH_CD, bizMsg.whCd_H1.getValue());
        ssmParam.put(DB_PARAM_ROWNUM, globalMsg.A.length());
        S21SsmEZDResult whList = NLGL0040Query.getInstance().getSHIPVIAList(bizMsg, ssmParam, globalMsg);

        if (!whList.isCodeNotFound()) {
            // 999over
            int queryResCnt = whList.getQueryResultCount();

            if (queryResCnt > globalMsg.A.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
                queryResCnt = globalMsg.A.length();
            }
            // 1page copy（globalMsg -> bizMsg）
            int rowCnt = globalMsg.A.getValidCount();

            if (globalMsg.A.getValidCount() > bizMsg.A.length()) {
                rowCnt = bizMsg.A.length();
            }

            for (int i = 0; i < rowCnt; i++) {
                EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i), null);
            }
            bizMsg.xxPageShowFromNum_D1.setValue(BigDecimal.ONE);
            bizMsg.xxPageShowOfNum_D1.setValue(queryResCnt);
            copyFromSMsgOntoCmsg(bizMsg, globalMsg);
        } else {
            bizMsg.setMessageInfo(NLGM0027W);
            bizMsg.xxPageShowFromNum_D1.clear();
            bizMsg.xxPageShowToNum_D1.clear();
            bizMsg.xxPageShowOfNum_D1.clear();
            bizMsg.setTransactionMode(EZDMsgCommons.TRANSKBN_COMMIT);
        }
    }

    /**
     * getShipViaList
     * to get and to set WMS_SHIP_VIA_RTE_MAP Detail infomation 
     * @param bizMsg NLGL0040CMsg
     * @param globalMsg NLGL0040SMsg
     * @param ssmParam Map<String, Object>
     */
    public static void getShipViaEdit(NLGL0040CMsg bizMsg, NLGL0040SMsg globalMsg, Map<String, Object> ssmParam) {

        S21SsmEZDResult wh = NLGL0040Query.getInstance().getSHIPVIAEdit(bizMsg, ssmParam, globalMsg);

        if (wh.isCodeNormal()) {
            ZYPEZDItemValueSetter.setValue(bizMsg.wmsShipViaTpCd_D2, globalMsg.wmsShipViaTpCd_D2);
            ZYPEZDItemValueSetter.setValue(bizMsg.wmsDescTxt_D2, globalMsg.wmsDescTxt_D2);
            ZYPEZDItemValueSetter.setValue(bizMsg.mdBreakTpCd_P2, globalMsg.mdBreakTpCd_P2);
            ZYPEZDItemValueSetter.setValue(bizMsg.rteGuideNum_D2, globalMsg.rteGuideNum_D2);
            ZYPEZDItemValueSetter.setValue(bizMsg.pclCarrCd_D2, globalMsg.pclCarrCd_D2);
            ZYPEZDItemValueSetter.setValue(bizMsg.pclMaxCapNum_D2, globalMsg.pclMaxCapNum_D2);
            ZYPEZDItemValueSetter.setValue(bizMsg.pclPrtyCd_D2, globalMsg.pclPrtyCd_D2);
            ZYPEZDItemValueSetter.setValue(bizMsg.ltlCarrCd_D2, globalMsg.ltlCarrCd_D2);
            ZYPEZDItemValueSetter.setValue(bizMsg.ltlMaxCapNum_D2, globalMsg.ltlMaxCapNum_D2);
            ZYPEZDItemValueSetter.setValue(bizMsg.ltlPrtyCd_D2, globalMsg.ltlPrtyCd_D2);
            ZYPEZDItemValueSetter.setValue(bizMsg.tlCarrCd_D2, globalMsg.tlCarrCd_D2);
            ZYPEZDItemValueSetter.setValue(bizMsg.tlMaxCapNum_D2, globalMsg.tlMaxCapNum_D2);
            ZYPEZDItemValueSetter.setValue(bizMsg.tlPrtyCd_D2, globalMsg.tlPrtyCd_D2);
            ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTime_D2, globalMsg.ezUpTime_D2);
            ZYPEZDItemValueSetter.setValue(bizMsg.ezUpTimeZone_D2, globalMsg.ezUpTimeZone_D2);
        }
    }
}
