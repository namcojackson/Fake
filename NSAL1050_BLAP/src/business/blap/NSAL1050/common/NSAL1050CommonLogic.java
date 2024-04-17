/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1050.common;

import static business.blap.NSAL1050.constant.NSAL1050Constant.COL_NUM;
import static business.blap.NSAL1050.constant.NSAL1050Constant.LIMIT_DOWNLOAD;
import static business.blap.NSAL1050.constant.NSAL1050Constant.NZZM0001W;
import static business.blap.NSAL1050.constant.NSAL1050Constant.NZZM0002I;
import static business.blap.NSAL1050.constant.NSAL1050Constant.ZZZM9001E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.sql.ResultSet;
import java.sql.SQLException;

import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL1050.NSAL1050CMsg;
import business.blap.NSAL1050.NSAL1050Query;
import business.db.SVC_TERM_ATTRB_DATA_TPTMsg;
import business.db.SVC_TERM_ATTRB_DATA_TPTMsgArray;
import business.db.SVC_TERM_COND_CATGTMsg;
import business.db.SVC_TERM_COND_CATGTMsgArray;
import business.file.NSAL1050F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * T&C Attributes Setup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/17   Hitachi         T.Mizuki        Create          N/A
 * 2016/10/28   Hitachi         T.Tomita        Update          QC#15468
 *</pre>
 */
public class NSAL1050CommonLogic {

    /**
     * Create Pull Down
     * @param cMsg NSAL1050CMsg
     */
    public static void createPullDown(NSAL1050CMsg cMsg) {

        SVC_TERM_ATTRB_DATA_TPTMsg stadtTMsg = new SVC_TERM_ATTRB_DATA_TPTMsg();
        stadtTMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        stadtTMsg.setConditionValue("ezCancelFlag01", ZYPConstant.FLG_OFF_0);
        stadtTMsg.setSQLID("001");
        SVC_TERM_ATTRB_DATA_TPTMsgArray stadtMsgList = (SVC_TERM_ATTRB_DATA_TPTMsgArray) EZDTBLAccessor.findByCondition(stadtTMsg);

        if (stadtMsgList.length() == 0) {
            return;
        }
        for (int j = 0; j < stadtMsgList.length(); j++) {
            cMsg.svcTermDataTpCd_1C.no(j).setValue(stadtMsgList.no(j).svcTermAttrbDataTpCd.getValue());
            cMsg.svcTermDataTpDescTxt_1D.no(j).setValue(stadtMsgList.no(j).svcTermDataTpDescTxt.getValue());
        }
        SVC_TERM_COND_CATGTMsg stccTMsg = new SVC_TERM_COND_CATGTMsg();
        stccTMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        stccTMsg.setConditionValue("svcTermCondCatgActvFlg01", ZYPConstant.FLG_ON_Y);
        stccTMsg.setConditionValue("ezCancelFlag01", ZYPConstant.FLG_OFF_0);
        stccTMsg.setConditionValue("effFromDt01", cMsg.slsDt.getValue());
        stccTMsg.setConditionValue("effThruDt02", cMsg.slsDt.getValue());
        stccTMsg.setSQLID("001");
        SVC_TERM_COND_CATGTMsgArray inMsgList = (SVC_TERM_COND_CATGTMsgArray) EZDTBLAccessor.findByCondition(stccTMsg);

        if (inMsgList.length() == 0) {
            return;
        }
        for (int j = 0; j < inMsgList.length(); j++) {
            cMsg.svcTermCondCatgPk_1C.no(j).setValue(inMsgList.no(j).svcTermCondCatgPk.getValue());
            cMsg.svcTermCondCatgDescTxt_1D.no(j).setValue(inMsgList.no(j).svcTermCondCatgDescTxt.getValue());
        }
    }

    /**
     * writeCsvFile
     * @param cMsg NSAL1050CMsg
     * @param rs ResultSet
     * @throws SQLException SQLException
     */
    public static void writeCsvFile(NSAL1050CMsg cMsg, ResultSet rs) throws SQLException {

        if (!rs.next()) {
            cMsg.setMessageInfo("ZZZM9001E");
            return;
        }

        NSAL1050F00FMsg fMsg = new NSAL1050F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        fMsg.setItemOrder(COL_NUM);
        fMsg.addExclusionItem("xxChkBox_D1");

        // write header
        final String[] csvHeader = new String[] {"T&C Attribute", "Short Name", "LOV DDF Maaping", "Data Type", "Seq", "Category", "Contract", "Machine", "Active", "Start", "End" };
        csvOutFile.writeHeader(csvHeader, fMsg, COL_NUM);

        // write contents
        do {
            if (rs.getRow() >= LIMIT_DOWNLOAD) {
                cMsg.setMessageInfo("NZZM0001W");
                break;
            }

            // resultSet -> fMsg
            ZYPEZDItemValueSetter.setValue(fMsg.svcTermAttrbDescTxt_A, rs.getString("SVC_TERM_ATTRB_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.svcTermCondAttrbNm_A, rs.getString("SVC_TERM_COND_ATTRB_NM"));
            ZYPEZDItemValueSetter.setValue(fMsg.svcTermCondSrcDescTxt_A, rs.getString("SVC_TERM_COND_SRC_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.svcTermDataTpDescTxt_A, rs.getString("SVC_TERM_DATA_TP_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.svcTermAttrbSortNum_A, rs.getBigDecimal("SVC_TERM_ATTRB_SORT_NUM"));
            ZYPEZDItemValueSetter.setValue(fMsg.svcTermCondCatgDescTxt_A, rs.getString("SVC_TERM_COND_CATG_DESC_TXT"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxChkBox_AC, rs.getString("SVC_TERM_ATTRB_CONTR_FLG"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxChkBox_AM, rs.getString("SVC_TERM_ATTRB_MACH_FLG"));
            ZYPEZDItemValueSetter.setValue(fMsg.xxChkBox_AA, rs.getString("SVC_TERM_ATTRB_ACTV_FLG"));
            // START 2016/10/28 T.Tomita [QC#15468, MOD]
//            ZYPEZDItemValueSetter.setValue(fMsg.effFromDt_A, rs.getString("EFF_FROM_DT"));
//            ZYPEZDItemValueSetter.setValue(fMsg.effToDt_A, rs.getString("EFF_THRU_DT"));
            ZYPEZDItemValueSetter.setValue(fMsg.contrEffFromDtTxt_A, convertDateFormat(rs.getString("EFF_FROM_DT")));
            ZYPEZDItemValueSetter.setValue(fMsg.contrEffThruDtTxt_A, convertDateFormat(rs.getString("EFF_THRU_DT")));
            // END 2016/10/28 T.Tomita [QC#15468, MOD]

            csvOutFile.write();
        } while (rs.next());

        csvOutFile.close();
    }

    /**
     * searchInfo
     * @param cMsg NSAL1050CMsg
     */
    public static void searchInfo(NSAL1050CMsg cMsg) {
        S21SsmEZDResult ssmResult = NSAL1050Query.getInstance().getSearchData(cMsg, cMsg.A.length() + 1);
        if (ssmResult.isCodeNormal()) {
            // Result > 200
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > cMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            } else {
                cMsg.setMessageInfo(NZZM0002I);
            }
        } else {
            // No result
            cMsg.setMessageInfo(ZZZM9001E);
        }
    }

    // START 2016/10/28 T.Tomita [QC#15468, ADD]
    private static String convertDateFormat(String date) {
        if (hasValue(date)) {
            date = ZYPDateUtil.formatEzd8ToDisp(date);
        }
        return date;
    }
    // END 2016/10/28 T.Tomita [QC#15468, ADD]
}
