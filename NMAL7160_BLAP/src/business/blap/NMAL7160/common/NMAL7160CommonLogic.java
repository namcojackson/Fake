/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7160.common;

import static business.blap.NMAL7160.constant.NMAL7160Constant.CSV_FILE_EXT;
import static business.blap.NMAL7160.constant.NMAL7160Constant.CSV_FILE_NAME_DOWNLOAD;
import static business.blap.NMAL7160.constant.NMAL7160Constant.CSV_MAX_ROW;
import static business.blap.NMAL7160.constant.NMAL7160Constant.NMAM0177E;
import static business.blap.NMAL7160.constant.NMAL7160Constant.NMAM8175E;
import static business.blap.NMAL7160.constant.NMAL7160Constant.NZZM0003E;
import static business.blap.NMAL7160.constant.NMAL7160Constant.ZZZM9001E;
import static business.blap.NMAL7160.constant.NMAL7160Constant.ZZZM9002W;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDMsg;
import business.blap.NMAL7160.NMAL7160CMsg;
import business.blap.NMAL7160.NMAL7160Query;
import business.blap.NMAL7160.NMAL7160SMsg;
import business.blap.NMAL7160.NMAL7160_ASMsg;
import business.db.INV_LINETMsg;
import business.file.NMAL7160F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CSMP_INV_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/17   Fujitsu         T.Murai         Create          N/A
 *</pre>
 */
public class NMAL7160CommonLogic {

    /**
     * <pre>
     * copyFromSMsgOntoCmsg
     * </pre>
     * @param bizMsg NMAL7160CMsg
     * @param glblMsg NMAL7160SMsg
     */
    public static void copyFromSMsgOntoCmsg(NMAL7160CMsg bizMsg, NMAL7160SMsg glblMsg) {
        // copy data from SMsg onto CMsg
        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + bizMsg.A.length(); i++) {
            if (i < glblMsg.A.getValidCount()) {

                EZDMsg.copy(glblMsg.A.no(i), null, bizMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        bizMsg.A.setValidCount(i - pagenationFrom);
        setPageNum(bizMsg, (pagenationFrom + 1), (pagenationFrom + bizMsg.A.getValidCount()), glblMsg.A.getValidCount());
    }

    /**
     * <pre>
    * setPageNum
    * </pre>
     * @param bizMsg NMAL7160CMsg
     * @param fromCnt PageShowFromNum
     * @param toCnt PageShowToNum
     * @param allCnt PageShowOfNum
     */
    public static void setPageNum(NMAL7160CMsg bizMsg, int fromCnt, int toCnt, int allCnt) {
        bizMsg.xxPageShowFromNum.setValue(fromCnt);
        bizMsg.xxPageShowToNum.setValue(toCnt);
        bizMsg.xxPageShowOfNum.setValue(allCnt);
    }

    /**
     * <pre>
    * copyFromCMsgOntoSmsg
    * </pre>
     * @param bizMsg NMAL7160CMsg
     * @param glblMsg NMAL7160SMsg
     */
    public static void copyFromCMsgOntoSmsg(NMAL7160CMsg bizMsg, NMAL7160SMsg glblMsg) {
        // copy data from CMsg onto SMsg
        int pagenationFrom = bizMsg.xxPageShowFromNum.getValueInt();
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (i + pagenationFrom - 1 < glblMsg.A.getValidCount()) {

                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i + pagenationFrom - 1).xxChkBox_A, bizMsg.A.no(i).xxChkBox_A);
            } else {
                break;
            }
        }
    }

    /**
     * Upload INV_LINE.CSMP_INV_PROC_STS_CD
     * @param bizMsg NMAL7160CMsg
     * @param glblMsg NMAL7160SMsg
     * @return boolean false:Error
     */
    public static boolean uploadDsInvLine(NMAL7160CMsg bizMsg, NMAL7160SMsg glblMsg) {

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(glblMsg.A, "xxChkBox_A", ZYPConstant.FLG_ON_Y);
        List<INV_LINETMsg> tMsgList = new ArrayList<INV_LINETMsg>();

        for (int checkNum : checkList) {

            NMAL7160_ASMsg lineMsg = glblMsg.A.no(checkNum);

            INV_LINETMsg dsInvLineTmsg = new INV_LINETMsg();
            ZYPEZDItemValueSetter.setValue(dsInvLineTmsg.glblCmpyCd, bizMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsInvLineTmsg.invNum, lineMsg.invNum_A);
            ZYPEZDItemValueSetter.setValue(dsInvLineTmsg.invBolLineNum, lineMsg.invBolLineNum_A);
            ZYPEZDItemValueSetter.setValue(dsInvLineTmsg.invLineNum, lineMsg.invLineNum_A);
            ZYPEZDItemValueSetter.setValue(dsInvLineTmsg.invLineSubNum, lineMsg.invLineSubNum_A);
            ZYPEZDItemValueSetter.setValue(dsInvLineTmsg.invLineSubTrxNum, lineMsg.invLineSubTrxNum_A);

            INV_LINETMsg updateTMsg = (INV_LINETMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(dsInvLineTmsg);

            if (updateTMsg == null) {
                bizMsg.setMessageInfo(NZZM0003E);
                return false;
            }

            // Exclusive check
            String tmpTimeZone = updateTMsg.ezUpTimeZone.getValue();
            String tmpTime = updateTMsg.ezUpTime.getValue();

            if (!ZYPDateUtil.isSameTimeStamp(lineMsg.ezUpTime_A.getValue(), lineMsg.ezUpTimeZone_A.getValue(), tmpTime, tmpTimeZone)) {
                bizMsg.setMessageInfo(NMAM8175E, new String[] {"INV_LINE", "Invoice Number", lineMsg.invNum_A.getValue() });
                return false;
            }
            updateTMsg.csmpInvProcStsCd.setValue(CSMP_INV_PROC_STS.MANUAL_COMPLETED);
            tMsgList.add(updateTMsg);
        }

        int updCnt = tMsgList.size();
        int rsltCnt = S21FastTBLAccessor.update(tMsgList.toArray(new INV_LINETMsg[tMsgList.size()]));
        if (rsltCnt != updCnt) {
            bizMsg.setMessageInfo(NMAM0177E, new String[] {"INV_LINE" });
            return false;
        }
        return true;
    }

    /**
     * csv Download Search result
     * @param bizMsg NMAL7160CMsg
     * @param glblMsg NMAL7160SMsg
     */
    public static void csvDownload(NMAL7160CMsg bizMsg, NMAL7160SMsg glblMsg) {

        NMAL7160Query.getInstance().searchForCSV(bizMsg, glblMsg, new CreateDownloadData(bizMsg));
    }

    /**
     * Create Download Date for CSV
     */
    private static class CreateDownloadData extends S21SsmBooleanResultSetHandlerSupport {
        /** cMsg */
        private NMAL7160CMsg bizMsg;

        public CreateDownloadData(NMAL7160CMsg cMsg) {
            this.bizMsg = cMsg;
        }

        /** csv Header Column */
        private static final String[] CSV_HEADER = {"Order Number", "Line Name", "Invoice Number", "Item Code", "Description", "CSMP Number", "CSA Number", "Error Date" };

        @Override
        protected Boolean doProcessQueryResult(ResultSet result) throws SQLException {
            if (!result.next()) {
                bizMsg.setMessageInfo(ZZZM9001E);
                return false;
            }
            NMAL7160F00FMsg fMsg = new NMAL7160F00FMsg();
            bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME_DOWNLOAD), CSV_FILE_EXT);
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);
            csvOutFile.writeHeader(CSV_HEADER);

            int rowCount = 1;
            do {
                if (rowCount > CSV_MAX_ROW) {
                    bizMsg.setMessageInfo(ZZZM9002W);
                    return true;
                }

                fMsg.clear();
                ZYPEZDItemValueSetter.setValue(fMsg.cpoOrdNum, result.getString("CPO_ORD_NUM"));
                ZYPEZDItemValueSetter.setValue(fMsg.dplyLineNum, result.getString("DPLY_LINE_NUM"));
                ZYPEZDItemValueSetter.setValue(fMsg.invNum, result.getString("INV_NUM"));
                ZYPEZDItemValueSetter.setValue(fMsg.mdseCd, result.getString("MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(fMsg.mdseDescShortTxt, result.getString("MDSE_DESC_SHORT_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.csmpContrNum, result.getString("CSMP_CONTR_NUM"));
                ZYPEZDItemValueSetter.setValue(fMsg.dlrRefNum, result.getString("DLR_REF_NUM"));
                ZYPEZDItemValueSetter.setValue(fMsg.invDtTxt, convertDateFormat(result.getString("CSMP_INV_ERR_DT")));
                csvOutFile.write();
                rowCount++;
            } while (result.next());
            csvOutFile.close();

            return true;
        }
    }

    /**
     * convertDateFormat
     * @param date String
     * @return String
     */
    private static String convertDateFormat(String date) {
        if (ZYPCommonFunc.hasValue(date)) {
            date = ZYPDateUtil.formatEzd8ToDisp(date);
        }
        return date;
    }
}
