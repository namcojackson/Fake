/*
 *  <Pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NMAL7050.common;

import static business.blap.NMAL7050.constant.NMAL7050Constant.LIMIT_DL_ROWNUM;
import static business.blap.NMAL7050.constant.NMAL7050Constant.NZZM0000E;
import static business.blap.NMAL7050.constant.NMAL7050Constant.NZZM0001W;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.blap.NMAL7050.NMAL7050CMsg;
import business.blap.NMAL7050.NMAL7050SMsg;
import business.blap.NMAL7050.NMAL7050_ASMsg;
import business.file.NMAL7050F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/19   Fujitsu         W.Honda         Create          N/A
 * 2017/02/15   Fujitsu         R.Nakamura      Update          QC#17529
 *</pre>
 */
public class NMAL7050CommonLogic {

    /** for I18N */
    static EZDI18NLabelConv converter = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

    /**
     * setSsearchSchdResultToGlblMsg
     * @param ssmResult S21SsmEZDResult
     * @param sMsg NMAL7050SMsg
     * @param cMsg NMAL7050CMsg
     * @param glblCmpyCd String
     */
    public static void setSearchResultToGlblMsg(S21SsmEZDResult ssmResult, NMAL7050SMsg sMsg, NMAL7050CMsg cMsg, String glblCmpyCd) {
        // SSM Result
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        // Tab_AvalOrd
        int reccount = 0;
        ZYPTableUtil.clear(sMsg.A);
        sMsg.A.setValidCount(0);
        for (int i = 0; i < resultList.size() && i < sMsg.A.length(); i++) {
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(i);
            NMAL7050_ASMsg sMsgALine = sMsg.A.no(i);

            ZYPEZDItemValueSetter.setValue(sMsgALine.prcMtrPkgPk_A1, (BigDecimal) resultMap.get("PRC_MTR_PKG_PK"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.mdlNm_A1, (String) resultMap.get("MDL_NM"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.mtrLbDescTxt_A1, (String) resultMap.get("MTR_LB_NM"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.prcMtrPkgNm_A1, (String) resultMap.get("PRC_MTR_PKG_NM"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.prcMtrPkgDescTxt_A1, (String) resultMap.get("PRC_MTR_PKG_DESC_TXT"));


            ZYPEZDItemValueSetter.setValue(sMsgALine.effFromDt_A1, (String) resultMap.get("EFF_FROM_DT"));
            ZYPEZDItemValueSetter.setValue(sMsgALine.effThruDt_A1, (String) resultMap.get("EFF_THRU_DT"));

            reccount++;
        }
        sMsg.A.setValidCount(reccount);
    }

    /**
     * It copy 'NMAL7050CMsg.A' to 'NMAL7050SMsg.A' .
     * @param cMsg NMAL7050CMsg
     * @param sMsg NMAL7050SMsg
     */
    public static void saveCurrentPageToSMsg(NMAL7050CMsg cMsg, NMAL7050SMsg sMsg) {

        cMsg.clearErrorInfo();
        cMsg.A.clearErrorInfo();
        sMsg.clearErrorInfo();
        sMsg.A.clearErrorInfo();

        int fromIdx = cMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            EZDMsg.copy(cMsg.A.no(i), null, sMsg.A.no(fromIdx + i), null);
        }
    }

    /**
     * It copy 'NMAL7050SMsg.A' to 'NMAL7050CMsg.A' .
     * @param bizMsg NMAL7050CMsg
     * @param globalMsg NMAL7050SMsg
     * @param fromIdx int
     */
    public static void pagenation(NMAL7050CMsg bizMsg, NMAL7050SMsg globalMsg, int fromIdx) {
        int i = fromIdx;
        for ( ; i < fromIdx + bizMsg.A.length(); i++) {
            if (i < globalMsg.A.getValidCount()) {
                EZDMsg.copy(globalMsg.A.no(i), null, bizMsg.A.no(i - fromIdx), null);
            } else {
                break;
            }
        }
        // set value to pagenation items
        bizMsg.A.setValidCount(i - fromIdx);
        bizMsg.xxPageShowFromNum.setValue(fromIdx + 1);
        bizMsg.xxPageShowToNum.setValue(fromIdx + bizMsg.A.getValidCount());
    }

    // Add Start 2017/02/15 QC#17529
    /**
     * Singleton instance getter.
     * @param cMsg NMAL7050CMsg
     * @param glblCmpyCd String
     * @return  Map<String, Object>
     */
    public static Map<String, Object> getServiceMeterPackageParam(NMAL7050CMsg cMsg, String glblCmpyCd) {
        // Create Parameter
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rowNum", LIMIT_DL_ROWNUM);
        if (ZYPCommonFunc.hasValue(cMsg.prcMtrPkgNm.getValue())) {
            ssmParam.put("prcMtrPkgNm", cMsg.prcMtrPkgNm.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.xxDsMultMsgDplyTxt.getValue())) {
            String[] mdlNm = cMsg.xxDsMultMsgDplyTxt.getValue().split(",", 0);
            ssmParam.put("mdlNmList", mdlNm);
        }
        if (ZYPCommonFunc.hasValue(cMsg.mtrLbDescTxt_BG.getValue())) {
            ssmParam.put("mtrLbDescTxtBllg", cMsg.mtrLbDescTxt_BG.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.mtrLbDescTxt_PH.getValue())) {
            ssmParam.put("mtrLbDescTxtPhys", cMsg.mtrLbDescTxt_PH.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.effFromDt.getValue())) {
            ssmParam.put("effFromDt", cMsg.effFromDt.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.effThruDt.getValue())) {
            ssmParam.put("effThruDt", cMsg.effThruDt.getValue());
        }

        return ssmParam;
    }

    /**
     * Write Csv File.
     * @param cMsg NMAL7050CMsg
     * @param rs ResultSet
     */
    public static void writeCsvFile(NMAL7050CMsg cMsg, ResultSet rs) throws SQLException {

        NMAL7050F00FMsg fMsg = new NMAL7050F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        //write header
        csvOutFile.writeHeader(new String[] { //
                "Service Model Name" //
                        , "Meter Package Display Name" //
                        , "Meter Package Description" //
                        , "Start Date" //
                        , "End Date" //
                        , "Create Date" //
                        , "Create By" //
                        , "Last Update Date" //
                        , "Last Update By" //
                });

        if (!rs.next()) {
            cMsg.setMessageInfo(NZZM0000E, null);
            csvOutFile.close();
            return;
        }

        do {

            if (rs.getRow() >= LIMIT_DL_ROWNUM) {
                cMsg.setMessageInfo(NZZM0001W, null);
                csvOutFile.close();
                break;
            }

            fMsg.clear();
            //resultSet -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.mdlNm, (String) rs.getString("MDL_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.prcMtrPkgNm, (String) rs.getString("PRC_MTR_PKG_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.prcMtrPkgDescTxt, (String) rs.getString("PRC_MTR_PKG_DESC_TXT"));
            String startDt = getDateValue((String) rs.getString("EFF_FROM_DT"));
            if (ZYPCommonFunc.hasValue(startDt)) {
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtNm_S1, startDt);
            }
            String endDt = getDateValue((String) rs.getString("EFF_THRU_DT"));
            if (ZYPCommonFunc.hasValue(endDt)) {
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtNm_E1, endDt);
            }
            String ezInTime = getDateValue((String) rs.getString("EZINTIME"));
            if (ZYPCommonFunc.hasValue(ezInTime)) {
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtNm_C1, ezInTime);
            }
            ZYPEZDItemValueSetter.setValue(fMsg.xxFullNm_C1, (String) rs.getString("EZINUSERNM"));
            String ezUpTime = getDateValue((String) rs.getString("EZUPTIME"));
            if (ZYPCommonFunc.hasValue(ezUpTime)) {
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtNm_U1, ezUpTime);
            }
            ZYPEZDItemValueSetter.setValue(fMsg.xxFullNm_U1, (String) rs.getString("EZUPUSERNM"));
            csvOutFile.write();
        } while (rs.next());
        csvOutFile.close();
    }

    /**
     * get Date Value
     * @param getTime String
     * @return String
     */
    public static String getDateValue(String getTime) {

        if (!ZYPCommonFunc.hasValue(getTime)) {
            return null;
        } else if (getTime.length() < 8) {
            return null;
        }

        return ZYPDateUtil.formatEzd8ToDisp(getTime.substring(0, 8));
    }
    // Add End 2017/02/15 QC#17529
}

