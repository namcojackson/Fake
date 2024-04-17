package business.blap.NWAL2320.common;

import static business.blap.NWAL2320.constant.NWAL2320Constant.TMPL_DL_HEADER_FOR_EXS_ORDER;
import static business.blap.NWAL2320.constant.NWAL2320Constant.TMPL_DL_HEADER_FOR_EXS_RMA;
import static business.blap.NWAL2320.constant.NWAL2320Constant.TMPL_DL_HEADER_FOR_NEW_ORDER;
import static business.blap.NWAL2320.constant.NWAL2320Constant.TMPL_DL_HEADER_FOR_NEW_RMA;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCSVInFile;
import parts.common.EZDFMsg;
import parts.common.EZDMsg;
import business.blap.NWAL2320.NWAL2320CMsg;
import business.blap.NWAL2320.NWAL2320Query;
import business.blap.NWAL2320.NWAL2320SMsg;
import business.blap.NWAL2320.NWAL2320_ASMsg;
import business.blap.NWAL2320.NWAL2320_BSMsg;
import business.blap.NWAL2320.NWAL2320_CSMsg;
import business.blap.NWAL2320.NWAL2320_DSMsg;
import business.blap.NWAL2320.constant.NWAL2320Constant;
import business.file.NWAL2320F00FMsg;
import business.file.NWAL2320F01FMsg;
import business.file.NWAL2320F02FMsg;
import business.file.NWAL2320F03FMsg;

import com.canon.cusa.s21.common.NWX.NWXC220001.NWXC220001ErrorInfo;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_UPLD_TMPL_TP;
import com.canon.cusa.s21.framework.ZYP.file.ZYPExcelUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/26/2016   Fujitsu         M.Hara          Create          N/A
 * 06/17/2016   Fujitsu         M.Hara          Update          QC#10320
 * 2016/11/21   Fujitsu         M.Ohno          Update          S21_NA#16082
 * 2017/01/05   Fujitsu         S.Ohki          Update          S21_NA#16757
 * 2016/12/22   Fujitsu         W.Honda         Update          S21_NA#16750
 * 2016/12/28   Fujitsu         W.Honda         Update          S21_NA#16836
 * 2017/01/19   Fujitsu         M.Ohno          Update          S21_NA#16836-2
 * 2019/03/25   Fujitsu         K.Ishizuka      Update          S21_NA#30924
 *</pre>
 */
public class NWAL2320CommonLogicForUpload {

    /**
     * createFMsg
     * @param bizMsg NWAL2320CMsg
     * @return EZDFMsg EZDFMsg
     */
    public static EZDFMsg createFMsg(NWAL2320CMsg bizMsg) {
        String ordUpldTmplTpCd = bizMsg.ordUpldTmplTpCd.getValue();

        if (ORD_UPLD_TMPL_TP.NEW_SALES_ORDER.equals(ordUpldTmplTpCd)) {
            return new NWAL2320F00FMsg();
        } else if (ORD_UPLD_TMPL_TP.EXISTING_SALES_ORDER.equals(ordUpldTmplTpCd)) {
            return new NWAL2320F01FMsg();
        } else if (ORD_UPLD_TMPL_TP.NEW_RMA_ORDER.equals(ordUpldTmplTpCd)) {
            return new NWAL2320F02FMsg();
        } else if (ORD_UPLD_TMPL_TP.EXISTING_RMA_ORDER.equals(ordUpldTmplTpCd)) {
            return new NWAL2320F03FMsg();
        }

        return null;
    }

    /**
     * readCsvHeader
     * @param mappedFile EZDCSVInFile
     * @param bizMsg NWAL2320CMsg
     * @return boolean
     */
    public static boolean readCsvHeader(EZDCSVInFile mappedFile, NWAL2320CMsg bizMsg) {
        int result = mappedFile.read();
        if (result == 1) {
            // When End Of Line
            bizMsg.setMessageInfo("NMAM8191E", new String[] {"Header Record"});
            return false;
        }
        return true;
    }

    /**
     * readCsvFile
     * @param bizMsg NWAL2320CMsg
     * @param glblMsg NWAL2320SMsg
     * @return boolean
     */
    public static boolean readCsvFile(NWAL2320CMsg bizMsg, NWAL2320SMsg glblMsg) {

        String xlsPath = bizMsg.xxFileData_UL.getTempFilePath();

        // Mod Start 2016/11/18 M.Ohno S21_NA#16082
        String csvPath = ZYPExcelUtil.excelToCsvFile(xlsPath);
        //String csvPath = xlsPath;
        // Mod End   2016/11/18 M.Ohno S21_NA#16082

        EZDFMsg fMsg = createFMsg(bizMsg);

        int option = EZDCSVInFile.NO_ABORT | EZDCSVInFile.QUOTE_FLG | EZDCSVInFile.STR_LEN_CHK | EZDCSVInFile.STR_TYPE_CHK;
        EZDCSVInFile mappedFile = new EZDCSVInFile(csvPath, fMsg, option);

        try {

            if (!readCsvHeader(mappedFile, bizMsg)) {
                return false;
            }

            String ordUpldTmplTpCd = bizMsg.ordUpldTmplTpCd.getValue();

            if (ORD_UPLD_TMPL_TP.NEW_SALES_ORDER.equals(ordUpldTmplTpCd)) {
                if (!readCsvFileForNewOrd(bizMsg, glblMsg, mappedFile, (NWAL2320F00FMsg) fMsg)) {
                    return false;
                }
            } else if (ORD_UPLD_TMPL_TP.EXISTING_SALES_ORDER.equals(ordUpldTmplTpCd)) {
                if (!readCsvFileForExsOrd(bizMsg, glblMsg, mappedFile, (NWAL2320F01FMsg) fMsg)) {
                    return false;
                }
            } else if (ORD_UPLD_TMPL_TP.NEW_RMA_ORDER.equals(ordUpldTmplTpCd)) {
                if (!readCsvFileForNewRma(bizMsg, glblMsg, mappedFile, (NWAL2320F02FMsg) fMsg)) {
                    return false;
                }
            } else if (ORD_UPLD_TMPL_TP.EXISTING_RMA_ORDER.equals(ordUpldTmplTpCd)) {
                if (!readCsvFileForExsRma(bizMsg, glblMsg, mappedFile, (NWAL2320F03FMsg) fMsg)) {
                    return false;
                }
            }

        } finally {
            mappedFile.close();
            bizMsg.xxFileData_UL.deleteTempFile();
            // Add Start 2016/11/18 M.Ohno S21_NA#16082
            ZYPExcelUtil.deleteFile(csvPath);
            // Add End   2016/11/18 M.Ohno S21_NA#16082
        }

        return true;
    }

    private static boolean readCsvFileForNewOrd(NWAL2320CMsg bizMsg, NWAL2320SMsg glblMsg, EZDCSVInFile mappedFile, NWAL2320F00FMsg fMsg) {

        int status = -1;
        Integer cnt = 0;
        int errColIdx;
        Map<String, Integer> lineMap = new HashMap<String, Integer>();
        int configCnt = 0;
        List<String> configList = new ArrayList<String>();

        fMsg.clear();
        glblMsg.A.clear();

        while ((status = mappedFile.read()) != 1) {

            if (status != 0) {
                if (status == 1000) {
                    bizMsg.setMessageInfo(NWAL2320Constant.NWAM0857E);
                    return false;
                }
                if (status > 1000) {
                    errColIdx = status % 1000;
                    bizMsg.setMessageInfo(NWAL2320Constant.NWAM0859E, new String[] {String.valueOf(cnt + 1), TMPL_DL_HEADER_FOR_NEW_ORDER[errColIdx - 1]});
                    return false;
                }
            }

            if (cnt >= bizMsg.maxLgNum.getValueInt()) {
                bizMsg.setMessageInfo(NWAL2320Constant.ZYEM0013E, new String[] {String.valueOf(bizMsg.maxLgNum.getValueInt())});
                return false;
            }

            // Add Start 2016/12/22 W.Honda S21_NA#16750
            String configKey = fMsg.procGrpNum.getValue() + fMsg.dsOrdPosnNum.getValue();
            if (!configList.contains(configKey)) {
                configList.add(configKey);
                configCnt++;
                if (configCnt > NWAL2320Constant.NWAL2320_MAX_CONFIG_CNT) {
                    bizMsg.setMessageInfo(NWAL2320Constant.NWAM0917E, new String[] {String.valueOf(NWAL2320Constant.NWAL2320_MAX_CONFIG_CNT)});
                    return false;
                }
            }

            String lineKey = fMsg.procGrpNum.getValue();
            if (lineMap.containsKey(lineKey)) {
                int lineCnt = lineMap.get(lineKey);
                lineCnt++;
                if (lineCnt > NWAL2320Constant.NWAL2320_MAX_LINE_CNT) {
                    bizMsg.setMessageInfo(NWAL2320Constant.NWAM0917E, new String[] {String.valueOf(NWAL2320Constant.NWAL2320_MAX_LINE_CNT)});
                    return false;
                }
                lineMap.put(lineKey, lineCnt);
            } else {
                int lineCnt = 0;
                lineMap.put(lineKey, lineCnt);
            }
            // Add End 2016/12/22 W.Honda S21_NA#16750
            EZDMsg.copy(fMsg, "", glblMsg.A.no(cnt), "CO");
            cnt++;
        }

        glblMsg.A.setValidCount(cnt);

        return true;
    }

    private static boolean readCsvFileForExsOrd(NWAL2320CMsg bizMsg, NWAL2320SMsg glblMsg, EZDCSVInFile mappedFile, NWAL2320F01FMsg fMsg) {

        int status = -1;
        int cnt = 0;
        int errColIdx;
        Map<String, Integer> lineMap = new HashMap<String, Integer>();
        int configCnt = 0;
        List<String> configList = new ArrayList<String>();

        fMsg.clear();
        bizMsg.B.clear();

        while ((status = mappedFile.read()) != 1) {

            if (status != 0) {
                if (status == 1000) {
                    bizMsg.setMessageInfo(NWAL2320Constant.NWAM0857E);
                    return false;
                }
                if (status > 1000) {
                    errColIdx = status % 1000;
                    bizMsg.setMessageInfo(NWAL2320Constant.NWAM0859E, new String[] {String.valueOf(cnt + 1), TMPL_DL_HEADER_FOR_EXS_ORDER[errColIdx - 1]});
                    return false;
                }
            }

            if (cnt >= bizMsg.maxLgNum.getValueInt()) {
                bizMsg.setMessageInfo(NWAL2320Constant.ZYEM0013E, new String[] {String.valueOf(bizMsg.maxLgNum.getValueInt())});
                return false;
            }

            // Add Start 2016/12/22 W.Honda S21_NA#16750
            String configKey = fMsg.cpoOrdNum.getValue() + fMsg.dsOrdPosnNum.getValue();
            if (!configList.contains(configKey)) {
                configList.add(configKey);
                configCnt++;
                if (configCnt > NWAL2320Constant.NWAL2320_MAX_CONFIG_CNT) {
                    bizMsg.setMessageInfo(NWAL2320Constant.NWAM0917E, new String[] {String.valueOf(NWAL2320Constant.NWAL2320_MAX_CONFIG_CNT)});
                    return false;
                }
            }

            String lineKey = fMsg.cpoOrdNum.getValue();
            if (lineMap.containsKey(lineKey)) {
                int lineCnt = lineMap.get(lineKey);
                lineCnt++;
                if (lineCnt > NWAL2320Constant.NWAL2320_MAX_LINE_CNT) {
                    bizMsg.setMessageInfo(NWAL2320Constant.NWAM0917E, new String[] {String.valueOf(NWAL2320Constant.NWAL2320_MAX_LINE_CNT)});
                    return false;
                }
                lineMap.put(lineKey, lineCnt);
            } else {
                int lineCnt = 0;
                lineMap.put(lineKey, lineCnt);
            }
            // Add End 2016/12/22 W.Honda S21_NA#16750
            EZDMsg.copy(fMsg, "", glblMsg.B.no(cnt), "EO");
            cnt++;
        }

        glblMsg.B.setValidCount(cnt);

        return true;
    }

    private static boolean readCsvFileForNewRma(NWAL2320CMsg bizMsg, NWAL2320SMsg glblMsg, EZDCSVInFile mappedFile, NWAL2320F02FMsg fMsg) {

        int status = -1;
        int cnt = 0;
        int errColIdx;
        Map<String, Integer> lineMap = new HashMap<String, Integer>();
        int configCnt = 0;
        List<String> configList = new ArrayList<String>();

        fMsg.clear();
        bizMsg.C.clear();

        while ((status = mappedFile.read()) != 1) {

            if (status != 0) {
                if (status == 1000) {
                    bizMsg.setMessageInfo(NWAL2320Constant.NWAM0857E);
                    return false;
                }
                if (status > 1000) {
                    errColIdx = status % 1000;
                    bizMsg.setMessageInfo(NWAL2320Constant.NWAM0859E, new String[] {String.valueOf(cnt + 1), TMPL_DL_HEADER_FOR_NEW_RMA[errColIdx - 1]});
                    return false;
                }
            }

            if (cnt >= bizMsg.maxLgNum.getValueInt()) {
                bizMsg.setMessageInfo(NWAL2320Constant.ZYEM0013E, new String[] {String.valueOf(bizMsg.maxLgNum.getValueInt())});
                return false;
            }

            // Add Start 2016/12/22 W.Honda S21_NA#16750
            String configKey = fMsg.procGrpNum.getValue() + fMsg.dsOrdPosnNum.getValue();
            if (!configList.contains(configKey)) {
                configList.add(configKey);
                configCnt++;
                if (configCnt > NWAL2320Constant.NWAL2320_MAX_RTRN_CONFIG_CNT) {
                    bizMsg.setMessageInfo(NWAL2320Constant.NWAM0917E, new String[] {String.valueOf(NWAL2320Constant.NWAL2320_MAX_RTRN_CONFIG_CNT)});
                    return false;
                }
            }

            String lineKey = fMsg.procGrpNum.getValue();
            if (lineMap.containsKey(lineKey)) {
                int lineCnt = lineMap.get(lineKey);
                lineCnt++;
                if (lineCnt > NWAL2320Constant.NWAL2320_MAX_RTRN_LINE_CNT) {
                    bizMsg.setMessageInfo(NWAL2320Constant.NWAM0917E, new String[] {String.valueOf(NWAL2320Constant.NWAL2320_MAX_RTRN_LINE_CNT)});
                    return false;
                }
                lineMap.put(lineKey, lineCnt);
            } else {
                int lineCnt = 0;
                lineMap.put(lineKey, lineCnt);
            }
            // Add End 2016/12/22 W.Honda S21_NA#16750
            EZDMsg.copy(fMsg, "", glblMsg.C.no(cnt), "NR");
            cnt++;
        }

        glblMsg.C.setValidCount(cnt);

        return true;
    }

    private static boolean readCsvFileForExsRma(NWAL2320CMsg bizMsg, NWAL2320SMsg glblMsg, EZDCSVInFile mappedFile, NWAL2320F03FMsg fMsg) {

        int status = -1;
        int cnt = 0;
        int errColIdx;
        Map<String, Integer> lineMap = new HashMap<String, Integer>();
        int configCnt = 0;
        List<String> configList = new ArrayList<String>();

        fMsg.clear();
        bizMsg.D.clear();

        while ((status = mappedFile.read()) != 1) {

            if (status != 0) {
                if (status == 1000) {
                    bizMsg.setMessageInfo(NWAL2320Constant.NWAM0857E);
                    return false;
                }
                if (status > 1000) {
                    errColIdx = status % 1000;
                    bizMsg.setMessageInfo(NWAL2320Constant.NWAM0859E, new String[] {String.valueOf(cnt + 1), TMPL_DL_HEADER_FOR_EXS_RMA[errColIdx - 1]});
                    return false;
                }
            }

            if (cnt >= bizMsg.maxLgNum.getValueInt()) {
                bizMsg.setMessageInfo(NWAL2320Constant.ZYEM0013E, new String[] {String.valueOf(bizMsg.maxLgNum.getValueInt())});
                return false;
            }

            // Add Start 2016/12/22 W.Honda S21_NA#16750
            String configKey = fMsg.cpoOrdNum.getValue() + fMsg.dsOrdPosnNum.getValue();
            if (!configList.contains(configKey)) {
                configList.add(configKey);
                configCnt++;
                if (configCnt > NWAL2320Constant.NWAL2320_MAX_RTRN_CONFIG_CNT) {
                    bizMsg.setMessageInfo(NWAL2320Constant.NWAM0917E, new String[] {String.valueOf(NWAL2320Constant.NWAL2320_MAX_RTRN_CONFIG_CNT)});
                    return false;
                }
            }

            String lineKey = fMsg.cpoOrdNum.getValue();
            if (lineMap.containsKey(lineKey)) {
                int lineCnt = lineMap.get(lineKey);
                lineCnt++;
                if (lineCnt > NWAL2320Constant.NWAL2320_MAX_RTRN_LINE_CNT) {
                    bizMsg.setMessageInfo(NWAL2320Constant.NWAM0917E, new String[] {String.valueOf(NWAL2320Constant.NWAL2320_MAX_RTRN_LINE_CNT)});
                    return false;
                }
                lineMap.put(lineKey, lineCnt);
            } else {
                int lineCnt = 0;
                lineMap.put(lineKey, lineCnt);
            }
            // Add End 2016/12/22 W.Honda S21_NA#16750
            EZDMsg.copy(fMsg, "", glblMsg.D.no(cnt), "ER");
            cnt++;
        }

        glblMsg.D.setValidCount(cnt);

        return true;
    }

    /**
     * checkUpload
     * @param bizMsg NWAL2320CMsg
     * @param glblMsg NWAL2320SMsg
     * @return boolean
     */
    public static boolean checkUpload(NWAL2320CMsg bizMsg, NWAL2320SMsg glblMsg) {
        String ordUpldTmplTpCd = bizMsg.ordUpldTmplTpCd.getValue();

        if (ORD_UPLD_TMPL_TP.NEW_SALES_ORDER.equals(ordUpldTmplTpCd)) {
            checkUploadForNewOrd(bizMsg, glblMsg);
        } else if (ORD_UPLD_TMPL_TP.EXISTING_SALES_ORDER.equals(ordUpldTmplTpCd)) {
            checkUploadForExsOrd(bizMsg, glblMsg);
        } else if (ORD_UPLD_TMPL_TP.NEW_RMA_ORDER.equals(ordUpldTmplTpCd)) {
            checkUploadForNewRma(bizMsg, glblMsg);
        } else if (ORD_UPLD_TMPL_TP.EXISTING_RMA_ORDER.equals(ordUpldTmplTpCd)) {
            checkUploadForExsRma(bizMsg, glblMsg);
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.xxErrNum_UP, getUploadCheckErrorNum(glblMsg, ordUpldTmplTpCd));

        return (bizMsg.xxErrNum_UP.getValueInt() == 0);
    }

    private static boolean checkUploadForNewOrd(NWAL2320CMsg bizMsg, NWAL2320SMsg glblMsg) {
        boolean isSuccess = true;

        List<NWXC220001ErrorInfo> rowErrList = new ArrayList<NWXC220001ErrorInfo>();
        Map<Integer, List<NWXC220001ErrorInfo>> allRowErrMap = new HashMap<Integer, List<NWXC220001ErrorInfo>>();
        NWAL2320_ASMsg glblASMsg;
        LinkedHashMap<String, List<NWAL2320_ASMsg>> headLevelMsgMap = new LinkedHashMap<String, List<NWAL2320_ASMsg>>();
        LinkedHashMap<String, List<NWAL2320_ASMsg>> configLevelMsgMap = new LinkedHashMap<String, List<NWAL2320_ASMsg>>();
        List<NWAL2320_ASMsg> levelMsgList;
        String procGrpNum, configLevelKey;

        //**********************************************************************
        // Single Item Check
        //**********************************************************************
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            glblASMsg = glblMsg.A.no(i);
            rowErrList = new ArrayList<NWXC220001ErrorInfo>();
            allRowErrMap.put(glblASMsg.hashCode(), rowErrList);

            procGrpNum = glblASMsg.procGrpNum_CO.getValue();
            //******************************************************************
            // Mandatory Check
            //******************************************************************
            NWAL2320CommonLogic.checkMandatory(rowErrList, glblASMsg.procGrpNum_CO, TMPL_DL_HEADER_FOR_NEW_ORDER[0]);
            NWAL2320CommonLogic.checkMandatory(rowErrList, glblASMsg.custIssPoNum_CO, TMPL_DL_HEADER_FOR_NEW_ORDER[1]);
            NWAL2320CommonLogic.checkMandatory(rowErrList, glblASMsg.dsOrdCatgDescTxt_CO, TMPL_DL_HEADER_FOR_NEW_ORDER[4]);
            NWAL2320CommonLogic.checkMandatory(rowErrList, glblASMsg.dsOrdTpDescTxt_CO, TMPL_DL_HEADER_FOR_NEW_ORDER[5]);
            NWAL2320CommonLogic.checkMandatory(rowErrList, glblASMsg.mdseCd_CO, TMPL_DL_HEADER_FOR_NEW_ORDER[6]);
            NWAL2320CommonLogic.checkMandatory(rowErrList, glblASMsg.ordQty_CO, TMPL_DL_HEADER_FOR_NEW_ORDER[7]);
            NWAL2320CommonLogic.checkMandatory(rowErrList, glblASMsg.dsOrdPosnNum_CO, TMPL_DL_HEADER_FOR_NEW_ORDER[9]);
            NWAL2320CommonLogic.checkMandatory(rowErrList, glblASMsg.dsOrdLineCatgDescTxt_CO, TMPL_DL_HEADER_FOR_NEW_ORDER[10]);
            NWAL2320CommonLogic.checkMandatory(rowErrList, glblASMsg.billToCustLocCd_CO, TMPL_DL_HEADER_FOR_NEW_ORDER[11]);
            NWAL2320CommonLogic.checkMandatory(rowErrList, glblASMsg.shipToCustLocCd_CO, TMPL_DL_HEADER_FOR_NEW_ORDER[12]);
            // 2017/01/05 S21_NA#16757 Mod Start
//            NWAL2320CommonLogic.checkMandatory(rowErrList, glblASMsg.tocNm_CO, TMPL_DL_HEADER_FOR_NEW_ORDER[13]);
//            NWAL2320CommonLogic.checkMandatory(rowErrList, glblASMsg.rtlWhNm_CO, TMPL_DL_HEADER_FOR_NEW_ORDER[14]);
            NWAL2320CommonLogic.checkMandatory(rowErrList, glblASMsg.soldToCustLocCd_CO, TMPL_DL_HEADER_FOR_NEW_ORDER[13]);
            NWAL2320CommonLogic.checkMandatory(rowErrList, glblASMsg.tocNm_CO, TMPL_DL_HEADER_FOR_NEW_ORDER[14]);
            // 2019/03/25 S21_NA#30294 Add Start
            // NWAL2320CommonLogic.checkMandatory(rowErrList, glblASMsg.rtlWhNm_CO, TMPL_DL_HEADER_FOR_NEW_ORDER[15]);
            if (!NWAL2320CommonLogic.isNoInvtyItem(bizMsg.glblCmpyCd.getValue(), glblASMsg.mdseCd_CO.getValue())) {
                NWAL2320CommonLogic.checkMandatory(rowErrList, glblASMsg.rtlWhNm_CO, TMPL_DL_HEADER_FOR_NEW_ORDER[15]);
            }
            // 2019/03/25 S21_NA#30294 Add End
            // 2017/01/05 S21_NA#16757 Mod End

            if (ZYPCommonFunc.hasValue(glblASMsg.ordQty_CO)) {
                if (glblASMsg.ordQty_CO.getValueInt() <= 0) {
                    NWXC220001ErrorInfo errorInfo = new NWXC220001ErrorInfo(NWAL2320Constant.NWZM1488E);
                    rowErrList.add(errorInfo);
                }
            }

            //******************************************************************
            // Create Header Level MsgList
            //******************************************************************
            if (!headLevelMsgMap.containsKey(procGrpNum)) {
                levelMsgList = new ArrayList<NWAL2320_ASMsg>();
                headLevelMsgMap.put(procGrpNum, levelMsgList);
            }
            levelMsgList = headLevelMsgMap.get(procGrpNum);
            levelMsgList.add(glblASMsg);

            //******************************************************************
            // Create Config Level MsgList
            //******************************************************************
            configLevelKey = String.format("%s_%s", procGrpNum, glblASMsg.dsOrdPosnNum_CO.getValue());
            if (!configLevelMsgMap.containsKey(configLevelKey)) {
                levelMsgList = new ArrayList<NWAL2320_ASMsg>();
                configLevelMsgMap.put(configLevelKey, levelMsgList);
            }
            levelMsgList = configLevelMsgMap.get(configLevelKey);
            levelMsgList.add(glblASMsg);
        }


        //**********************************************************************
        // Header Group Item Check
        //**********************************************************************
        NWAL2320_ASMsg srcMsg;
        for (List<NWAL2320_ASMsg> targetMsgList : headLevelMsgMap.values()) {
            srcMsg = null;
            for (NWAL2320_ASMsg targetMsg : targetMsgList) {
                if (srcMsg == null) {
                    srcMsg = targetMsg;
                    continue;
                }

                rowErrList = allRowErrMap.get(targetMsg.hashCode());
                NWAL2320CommonLogic.checkSameSMsgValue(rowErrList, srcMsg.custIssPoNum_CO, targetMsg.custIssPoNum_CO, TMPL_DL_HEADER_FOR_NEW_ORDER[1]);
                NWAL2320CommonLogic.checkSameSMsgValue(rowErrList, srcMsg.aquNum_CO, targetMsg.aquNum_CO, TMPL_DL_HEADER_FOR_NEW_ORDER[2]);
                NWAL2320CommonLogic.checkSameSMsgValue(rowErrList, srcMsg.negoDealAmt_CO, targetMsg.negoDealAmt_CO, TMPL_DL_HEADER_FOR_NEW_ORDER[3]);
                NWAL2320CommonLogic.checkSameSMsgValue(rowErrList, srcMsg.dsOrdCatgDescTxt_CO, targetMsg.dsOrdCatgDescTxt_CO, TMPL_DL_HEADER_FOR_NEW_ORDER[4]);
                NWAL2320CommonLogic.checkSameSMsgValue(rowErrList, srcMsg.dsOrdTpDescTxt_CO, targetMsg.dsOrdTpDescTxt_CO, TMPL_DL_HEADER_FOR_NEW_ORDER[5]);
                NWAL2320CommonLogic.checkSameSMsgValue(rowErrList, srcMsg.billToCustLocCd_CO, targetMsg.billToCustLocCd_CO, TMPL_DL_HEADER_FOR_NEW_ORDER[11]);
                NWAL2320CommonLogic.checkSameSMsgValue(rowErrList, srcMsg.shipToCustLocCd_CO, targetMsg.shipToCustLocCd_CO, TMPL_DL_HEADER_FOR_NEW_ORDER[12]);
                // 2017/01/05 S21_NA#16757 Mod Start
//                NWAL2320CommonLogic.checkSameSMsgValue(rowErrList, srcMsg.tocNm_CO, targetMsg.tocNm_CO, TMPL_DL_HEADER_FOR_NEW_ORDER[13]);
                NWAL2320CommonLogic.checkSameSMsgValue(rowErrList, srcMsg.tocNm_CO, targetMsg.tocNm_CO, TMPL_DL_HEADER_FOR_NEW_ORDER[14]);
                // 2017/01/05 S21_NA#16757 Mod End
            }
        }

        //**********************************************************************
        // Config Group Item Check
        //**********************************************************************
        for (List<NWAL2320_ASMsg> targetMsgList : configLevelMsgMap.values()) {
            srcMsg = null;
            for (NWAL2320_ASMsg targetMsg : targetMsgList) {
                if (srcMsg == null) {
                    srcMsg = targetMsg;
                    continue;
                }

                rowErrList = allRowErrMap.get(targetMsg.hashCode());
                // 2017/01/05 S21_NA#16757 Mod Start
//                NWAL2320CommonLogic.checkSameSMsgValue(rowErrList, srcMsg.xxShpgOrdCmntTxt_CO, targetMsg.xxShpgOrdCmntTxt_CO, TMPL_DL_HEADER_FOR_NEW_ORDER[16]);
                NWAL2320CommonLogic.checkSameSMsgValue(rowErrList, srcMsg.xxShpgOrdCmntTxt_CO, targetMsg.xxShpgOrdCmntTxt_CO, TMPL_DL_HEADER_FOR_NEW_ORDER[17]);
                // 2017/01/05 S21_NA#16757 Mod End
            }
        }

        //**********************************************************************
        // Set Upload Error
        //**********************************************************************
        String errString;
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            glblASMsg = glblMsg.A.no(i);
            rowErrList = allRowErrMap.get(glblASMsg.hashCode());
            errString = "";
            if (rowErrList.size() > 0) {
                errString = NWAL2320CommonLogic.createErrString(rowErrList, bizMsg.maxLgNum.getValueInt());
                isSuccess = false;
            }
            ZYPEZDItemValueSetter.setValue(glblASMsg.xxComnScrColValTxt_CO, errString);
        }

        return isSuccess;
    }

    private static boolean checkUploadForExsOrd(NWAL2320CMsg bizMsg, NWAL2320SMsg glblMsg) {
        boolean isSuccess = true;

        List<NWXC220001ErrorInfo> rowErrList = new ArrayList<NWXC220001ErrorInfo>();
        Map<Integer, List<NWXC220001ErrorInfo>> allRowErrMap = new HashMap<Integer, List<NWXC220001ErrorInfo>>();
        NWAL2320_BSMsg glblBSMsg;
        LinkedHashMap<String, List<NWAL2320_BSMsg>> configLevelMsgMap = new LinkedHashMap<String, List<NWAL2320_BSMsg>>();
        List<NWAL2320_BSMsg> levelMsgList;
        String configLevelKey;

        //**********************************************************************
        // Single Item Check
        //**********************************************************************
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            glblBSMsg = glblMsg.B.no(i);
            rowErrList = new ArrayList<NWXC220001ErrorInfo>();
            allRowErrMap.put(glblBSMsg.hashCode(), rowErrList);

            //******************************************************************
            // Mandatory Check
            //******************************************************************
            NWAL2320CommonLogic.checkMandatory(rowErrList, glblBSMsg.cpoOrdNum_EO, TMPL_DL_HEADER_FOR_EXS_ORDER[0]);
            NWAL2320CommonLogic.checkMandatory(rowErrList, glblBSMsg.mdseCd_EO, TMPL_DL_HEADER_FOR_EXS_ORDER[1]);
            NWAL2320CommonLogic.checkMandatory(rowErrList, glblBSMsg.ordQty_EO, TMPL_DL_HEADER_FOR_EXS_ORDER[2]);
            NWAL2320CommonLogic.checkMandatory(rowErrList, glblBSMsg.dsOrdPosnNum_EO, TMPL_DL_HEADER_FOR_EXS_ORDER[4]);
            NWAL2320CommonLogic.checkMandatory(rowErrList, glblBSMsg.dsOrdLineCatgDescTxt_EO, TMPL_DL_HEADER_FOR_EXS_ORDER[5]);
            NWAL2320CommonLogic.checkMandatory(rowErrList, glblBSMsg.billToCustLocCd_EO, TMPL_DL_HEADER_FOR_EXS_ORDER[6]);
            NWAL2320CommonLogic.checkMandatory(rowErrList, glblBSMsg.shipToCustLocCd_EO, TMPL_DL_HEADER_FOR_EXS_ORDER[7]);
            NWAL2320CommonLogic.checkMandatory(rowErrList, glblBSMsg.tocNm_EO, TMPL_DL_HEADER_FOR_EXS_ORDER[8]);
            // 2019/03/25 S21_NA#30294 Add Start
            // NWAL2320CommonLogic.checkMandatory(rowErrList, glblBSMsg.rtlWhNm_EO, TMPL_DL_HEADER_FOR_EXS_ORDER[9]);
            if (!NWAL2320CommonLogic.isNoInvtyItem(bizMsg.glblCmpyCd.getValue(), glblBSMsg.mdseCd_EO.getValue())) {
                NWAL2320CommonLogic.checkMandatory(rowErrList, glblBSMsg.rtlWhNm_EO, TMPL_DL_HEADER_FOR_EXS_ORDER[9]);
            }
            // 2019/03/25 S21_NA#30294 Add End

            if (ZYPCommonFunc.hasValue(glblBSMsg.ordQty_EO)) {
                if (glblBSMsg.ordQty_EO.getValueInt() <= 0) {
                    NWXC220001ErrorInfo errorInfo = new NWXC220001ErrorInfo(NWAL2320Constant.NWZM1488E);
                    rowErrList.add(errorInfo);
                }
            }

            //******************************************************************
            // Create Config Level MsgList
            //******************************************************************
            configLevelKey = String.format("%s_%s", glblBSMsg.cpoOrdNum_EO.getValue(), glblBSMsg.dsOrdPosnNum_EO.getValue());
            if (!configLevelMsgMap.containsKey(configLevelKey)) {
                levelMsgList = new ArrayList<NWAL2320_BSMsg>();
                configLevelMsgMap.put(configLevelKey, levelMsgList);
            }
            levelMsgList = configLevelMsgMap.get(configLevelKey);
            levelMsgList.add(glblBSMsg);
        }

        //**********************************************************************
        // Config Group Item Check
        //**********************************************************************
        NWAL2320_BSMsg srcMsg;
        for (List<NWAL2320_BSMsg> targetMsgList : configLevelMsgMap.values()) {
            srcMsg = null;
            for (NWAL2320_BSMsg targetMsg : targetMsgList) {
                if (srcMsg == null) {
                    srcMsg = targetMsg;
                    continue;
                }

                rowErrList = allRowErrMap.get(targetMsg.hashCode());
                NWAL2320CommonLogic.checkSameSMsgValue(rowErrList, srcMsg.billToCustLocCd_EO, targetMsg.billToCustLocCd_EO, TMPL_DL_HEADER_FOR_EXS_ORDER[6]);
                NWAL2320CommonLogic.checkSameSMsgValue(rowErrList, srcMsg.shipToCustLocCd_EO, targetMsg.shipToCustLocCd_EO, TMPL_DL_HEADER_FOR_EXS_ORDER[7]);
                NWAL2320CommonLogic.checkSameSMsgValue(rowErrList, srcMsg.tocNm_EO, targetMsg.tocNm_EO, TMPL_DL_HEADER_FOR_EXS_ORDER[8]);
                NWAL2320CommonLogic.checkSameSMsgValue(rowErrList, srcMsg.xxShpgOrdCmntTxt_EO, targetMsg.xxShpgOrdCmntTxt_EO, TMPL_DL_HEADER_FOR_EXS_ORDER[11]);
            }
        }

        //**********************************************************************
        // Set Upload Error
        //**********************************************************************
        String errString;
        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
            glblBSMsg = glblMsg.B.no(i);
            rowErrList = allRowErrMap.get(glblBSMsg.hashCode());
            errString = "";
            if (rowErrList.size() > 0) {
                errString = NWAL2320CommonLogic.createErrString(rowErrList, bizMsg.maxLgNum.getValueInt());
                isSuccess = false;
            }
            ZYPEZDItemValueSetter.setValue(glblBSMsg.xxComnScrColValTxt_EO, errString);
        }

        return isSuccess;
    }

    private static boolean checkUploadForNewRma(NWAL2320CMsg bizMsg, NWAL2320SMsg glblMsg) {
        boolean isSuccess = true;

        List<NWXC220001ErrorInfo> rowErrList = new ArrayList<NWXC220001ErrorInfo>();
        Map<Integer, List<NWXC220001ErrorInfo>> allRowErrMap = new HashMap<Integer, List<NWXC220001ErrorInfo>>();
        NWAL2320_CSMsg glblCSMsg;
        LinkedHashMap<String, List<NWAL2320_CSMsg>> headLevelMsgMap = new LinkedHashMap<String, List<NWAL2320_CSMsg>>();
        LinkedHashMap<String, List<NWAL2320_CSMsg>> configLevelMsgMap = new LinkedHashMap<String, List<NWAL2320_CSMsg>>();
        List<NWAL2320_CSMsg> levelMsgList;
        String procGrpNum, configLevelKey;

        //**********************************************************************
        // Single Item Check
        //**********************************************************************
        for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
            glblCSMsg = glblMsg.C.no(i);
            rowErrList = new ArrayList<NWXC220001ErrorInfo>();
            allRowErrMap.put(glblCSMsg.hashCode(), rowErrList);

            procGrpNum = glblCSMsg.procGrpNum_NR.getValue();
            //******************************************************************
            // Mandatory Check
            //******************************************************************
            NWAL2320CommonLogic.checkMandatory(rowErrList, glblCSMsg.procGrpNum_NR, TMPL_DL_HEADER_FOR_NEW_RMA[0]);
            NWAL2320CommonLogic.checkMandatory(rowErrList, glblCSMsg.custIssPoNum_NR, TMPL_DL_HEADER_FOR_NEW_RMA[1]);
            NWAL2320CommonLogic.checkMandatory(rowErrList, glblCSMsg.dsOrdCatgDescTxt_NR, TMPL_DL_HEADER_FOR_NEW_RMA[4]);
            NWAL2320CommonLogic.checkMandatory(rowErrList, glblCSMsg.dsOrdTpDescTxt_NR, TMPL_DL_HEADER_FOR_NEW_RMA[5]);
            NWAL2320CommonLogic.checkMandatory(rowErrList, glblCSMsg.mdseCd_NR, TMPL_DL_HEADER_FOR_NEW_RMA[6]);
            NWAL2320CommonLogic.checkMandatory(rowErrList, glblCSMsg.ordQty_NR, TMPL_DL_HEADER_FOR_NEW_RMA[7]);
            NWAL2320CommonLogic.checkMandatory(rowErrList, glblCSMsg.dsOrdPosnNum_NR, TMPL_DL_HEADER_FOR_NEW_RMA[9]);
            NWAL2320CommonLogic.checkMandatory(rowErrList, glblCSMsg.dsOrdLineCatgDescTxt_NR, TMPL_DL_HEADER_FOR_NEW_RMA[10]);
            NWAL2320CommonLogic.checkMandatory(rowErrList, glblCSMsg.billToCustLocCd_NR, TMPL_DL_HEADER_FOR_NEW_RMA[11]);
            NWAL2320CommonLogic.checkMandatory(rowErrList, glblCSMsg.shipToCustLocCd_NR, TMPL_DL_HEADER_FOR_NEW_RMA[12]);
            // 2017/01/05 S21_NA#16757 Mod Start
//            NWAL2320CommonLogic.checkMandatory(rowErrList, glblCSMsg.serNum_NR, TMPL_DL_HEADER_FOR_NEW_RMA[13]);
//            NWAL2320CommonLogic.checkMandatory(rowErrList, glblCSMsg.tocNm_NR, TMPL_DL_HEADER_FOR_NEW_RMA[14]);
//            NWAL2320CommonLogic.checkMandatory(rowErrList, glblCSMsg.rtlWhNm_NR, TMPL_DL_HEADER_FOR_NEW_RMA[15]);
//            NWAL2320CommonLogic.checkMandatory(rowErrList, glblCSMsg.rtrnRsnDescTxt_NR, TMPL_DL_HEADER_FOR_NEW_RMA[16]);
            NWAL2320CommonLogic.checkMandatory(rowErrList, glblCSMsg.soldToCustLocCd_NR, TMPL_DL_HEADER_FOR_NEW_RMA[13]);

            // 2017/01/13 S21_NA#16836-2 Mod Start
            if (ZYPCommonFunc.hasValue(glblCSMsg.mdseCd_NR)) {
                S21SsmEZDResult ssmResult = NWAL2320Query.getInstance().getShpgSerTakeFlgFromAllMdse(bizMsg.glblCmpyCd.getValue(), glblCSMsg.mdseCd_NR.getValue());
                if (ssmResult.isCodeNormal() && ZYPConstant.FLG_ON_Y.equals((String) ssmResult.getResultObject())) {
                    NWAL2320CommonLogic.checkMandatory(rowErrList, glblCSMsg.serNum_NR, TMPL_DL_HEADER_FOR_NEW_RMA[14]);
                }
            }
            // 2017/01/13 S21_NA#16836-2 Mod End
            NWAL2320CommonLogic.checkMandatory(rowErrList, glblCSMsg.tocNm_NR, TMPL_DL_HEADER_FOR_NEW_RMA[15]);
            // 2019/03/25 S21_NA#30294 Add Start
            // NWAL2320CommonLogic.checkMandatory(rowErrList, glblCSMsg.rtlWhNm_NR, TMPL_DL_HEADER_FOR_NEW_RMA[16]);
            if (!NWAL2320CommonLogic.isNoInvtyItem(bizMsg.glblCmpyCd.getValue(), glblCSMsg.mdseCd_NR.getValue())) {
                NWAL2320CommonLogic.checkMandatory(rowErrList, glblCSMsg.rtlWhNm_NR, TMPL_DL_HEADER_FOR_NEW_RMA[16]);
            }
            // 2019/03/25 S21_NA#30294 Add End
            NWAL2320CommonLogic.checkMandatory(rowErrList, glblCSMsg.rtrnRsnDescTxt_NR, TMPL_DL_HEADER_FOR_NEW_RMA[17]);
            // 2017/01/05 S21_NA#16757 Mod End

            if (ZYPCommonFunc.hasValue(glblCSMsg.ordQty_NR)) {
                // QC#10320
                if (glblCSMsg.ordQty_NR.getValueInt() >= 0) {
                    NWXC220001ErrorInfo errorInfo = new NWXC220001ErrorInfo(NWAL2320Constant.NWZM1596E);
                    rowErrList.add(errorInfo);
                }
            }
            
            // 2019/03/26 S21_NA#30924 Add Start
            if (ZYPCommonFunc.hasValue(glblCSMsg.serNum_NR)) {
                ZYPEZDItemValueSetter.setValue(glblCSMsg.serNum_NR, glblCSMsg.serNum_NR.getValue().toUpperCase());
            }
            // 2019/03/26 S21_NA#30924 Add End

            //******************************************************************
            // Create Header Level MsgList
            //******************************************************************
            if (!headLevelMsgMap.containsKey(procGrpNum)) {
                levelMsgList = new ArrayList<NWAL2320_CSMsg>();
                headLevelMsgMap.put(procGrpNum, levelMsgList);
            }
            levelMsgList = headLevelMsgMap.get(procGrpNum);
            levelMsgList.add(glblCSMsg);

            //******************************************************************
            // Create Config Level MsgList
            //******************************************************************
            configLevelKey = String.format("%s_%s", procGrpNum, glblCSMsg.dsOrdPosnNum_NR.getValue());
            if (!configLevelMsgMap.containsKey(configLevelKey)) {
                levelMsgList = new ArrayList<NWAL2320_CSMsg>();
                configLevelMsgMap.put(configLevelKey, levelMsgList);
            }
            levelMsgList = configLevelMsgMap.get(configLevelKey);
            levelMsgList.add(glblCSMsg);
        }

        //**********************************************************************
        // Header Group Item Check
        //**********************************************************************
        NWAL2320_CSMsg srcMsg;
        for (List<NWAL2320_CSMsg> targetMsgList : headLevelMsgMap.values()) {
            srcMsg = null;
            for (NWAL2320_CSMsg targetMsg : targetMsgList) {
                if (srcMsg == null) {
                    srcMsg = targetMsg;
                    continue;
                }

                rowErrList = allRowErrMap.get(targetMsg.hashCode());
                NWAL2320CommonLogic.checkSameSMsgValue(rowErrList, srcMsg.custIssPoNum_NR, targetMsg.custIssPoNum_NR, TMPL_DL_HEADER_FOR_NEW_RMA[1]);
                NWAL2320CommonLogic.checkSameSMsgValue(rowErrList, srcMsg.aquNum_NR, targetMsg.aquNum_NR, TMPL_DL_HEADER_FOR_NEW_RMA[2]);
                NWAL2320CommonLogic.checkSameSMsgValue(rowErrList, srcMsg.negoDealAmt_NR, targetMsg.negoDealAmt_NR, TMPL_DL_HEADER_FOR_NEW_RMA[3]);
                NWAL2320CommonLogic.checkSameSMsgValue(rowErrList, srcMsg.dsOrdCatgDescTxt_NR, targetMsg.dsOrdCatgDescTxt_NR, TMPL_DL_HEADER_FOR_NEW_RMA[4]);
                NWAL2320CommonLogic.checkSameSMsgValue(rowErrList, srcMsg.dsOrdTpDescTxt_NR, targetMsg.dsOrdTpDescTxt_NR, TMPL_DL_HEADER_FOR_NEW_RMA[5]);
                NWAL2320CommonLogic.checkSameSMsgValue(rowErrList, srcMsg.billToCustLocCd_NR, targetMsg.billToCustLocCd_NR, TMPL_DL_HEADER_FOR_NEW_RMA[11]);
                NWAL2320CommonLogic.checkSameSMsgValue(rowErrList, srcMsg.shipToCustLocCd_NR, targetMsg.shipToCustLocCd_NR, TMPL_DL_HEADER_FOR_NEW_RMA[12]);
                // 2017/01/05 S21_NA#16757 Mod Start
//                NWAL2320CommonLogic.checkSameSMsgValue(rowErrList, srcMsg.tocNm_NR, targetMsg.tocNm_NR, TMPL_DL_HEADER_FOR_NEW_RMA[14]);
                NWAL2320CommonLogic.checkSameSMsgValue(rowErrList, srcMsg.tocNm_NR, targetMsg.tocNm_NR, TMPL_DL_HEADER_FOR_NEW_RMA[15]);
                // 2017/01/05 S21_NA#16757 Mod End
            }
        }

        //**********************************************************************
        // Config Group Item Check
        //**********************************************************************
        for (List<NWAL2320_CSMsg> targetMsgList : configLevelMsgMap.values()) {
            srcMsg = null;
            for (NWAL2320_CSMsg targetMsg : targetMsgList) {
                if (srcMsg == null) {
                    srcMsg = targetMsg;
                    continue;
                }

                rowErrList = allRowErrMap.get(targetMsg.hashCode());
                // 2017/01/05 S21_NA#16757 Mod Start
//                NWAL2320CommonLogic.checkSameSMsgValue(rowErrList, srcMsg.xxShpgOrdCmntTxt_NR, targetMsg.xxShpgOrdCmntTxt_NR, TMPL_DL_HEADER_FOR_NEW_RMA[17]);
                NWAL2320CommonLogic.checkSameSMsgValue(rowErrList, srcMsg.xxShpgOrdCmntTxt_NR, targetMsg.xxShpgOrdCmntTxt_NR, TMPL_DL_HEADER_FOR_NEW_RMA[18]);
                // 2017/01/05 S21_NA#16757 Mod End
            }
        }

        //**********************************************************************
        // Set Upload Error
        //**********************************************************************
        String errString;
        for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
            glblCSMsg = glblMsg.C.no(i);
            rowErrList = allRowErrMap.get(glblCSMsg.hashCode());
            errString = "";
            if (rowErrList.size() > 0) {
                errString = NWAL2320CommonLogic.createErrString(rowErrList, bizMsg.maxLgNum.getValueInt());
                isSuccess = false;
            }
            ZYPEZDItemValueSetter.setValue(glblCSMsg.xxComnScrColValTxt_NR, errString);
        }

        return isSuccess;
    }

    private static boolean checkUploadForExsRma(NWAL2320CMsg bizMsg, NWAL2320SMsg glblMsg) {
        boolean isSuccess = true;

        List<NWXC220001ErrorInfo> rowErrList = new ArrayList<NWXC220001ErrorInfo>();
        Map<Integer, List<NWXC220001ErrorInfo>> allRowErrMap = new HashMap<Integer, List<NWXC220001ErrorInfo>>();
        NWAL2320_DSMsg glblDSMsg;
        LinkedHashMap<String, List<NWAL2320_DSMsg>> configLevelMsgMap = new LinkedHashMap<String, List<NWAL2320_DSMsg>>();
        List<NWAL2320_DSMsg> levelMsgList;
        String configLevelKey;

        //**********************************************************************
        // Single Item Check
        //**********************************************************************
        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            glblDSMsg = glblMsg.D.no(i);
            rowErrList = new ArrayList<NWXC220001ErrorInfo>();
            allRowErrMap.put(glblDSMsg.hashCode(), rowErrList);

            //******************************************************************
            // Mandatory Check
            //******************************************************************
            NWAL2320CommonLogic.checkMandatory(rowErrList, glblDSMsg.cpoOrdNum_ER, TMPL_DL_HEADER_FOR_EXS_RMA[0]);
            NWAL2320CommonLogic.checkMandatory(rowErrList, glblDSMsg.mdseCd_ER, TMPL_DL_HEADER_FOR_EXS_RMA[1]);
            NWAL2320CommonLogic.checkMandatory(rowErrList, glblDSMsg.ordQty_ER, TMPL_DL_HEADER_FOR_EXS_RMA[2]);
            NWAL2320CommonLogic.checkMandatory(rowErrList, glblDSMsg.dsOrdPosnNum_ER, TMPL_DL_HEADER_FOR_EXS_RMA[4]);
            NWAL2320CommonLogic.checkMandatory(rowErrList, glblDSMsg.dsOrdLineCatgDescTxt_ER, TMPL_DL_HEADER_FOR_EXS_RMA[5]);
            NWAL2320CommonLogic.checkMandatory(rowErrList, glblDSMsg.billToCustLocCd_ER, TMPL_DL_HEADER_FOR_EXS_RMA[6]);
            NWAL2320CommonLogic.checkMandatory(rowErrList, glblDSMsg.shipToCustLocCd_ER, TMPL_DL_HEADER_FOR_EXS_RMA[7]);
            // 2017/01/13 S21_NA#16836-2 Mod Start
            if (ZYPCommonFunc.hasValue(glblDSMsg.mdseCd_ER)) {
                S21SsmEZDResult ssmResult = NWAL2320Query.getInstance().getShpgSerTakeFlgFromAllMdse(bizMsg.glblCmpyCd.getValue(), glblDSMsg.mdseCd_ER.getValue());
                if (ssmResult.isCodeNormal() && ZYPConstant.FLG_ON_Y.equals((String) ssmResult.getResultObject())) {
                    NWAL2320CommonLogic.checkMandatory(rowErrList, glblDSMsg.serNum_ER, TMPL_DL_HEADER_FOR_NEW_RMA[14]);
                }
            }
            // 2017/01/13 S21_NA#16836-2 Mod End
            NWAL2320CommonLogic.checkMandatory(rowErrList, glblDSMsg.tocNm_ER, TMPL_DL_HEADER_FOR_EXS_RMA[9]);
            // 2019/03/25 S21_NA#30294 Add Start
            // NWAL2320CommonLogic.checkMandatory(rowErrList, glblDSMsg.rtlWhNm_ER, TMPL_DL_HEADER_FOR_EXS_RMA[10]);
            if (!NWAL2320CommonLogic.isNoInvtyItem(bizMsg.glblCmpyCd.getValue(), glblDSMsg.mdseCd_ER.getValue())) {
                NWAL2320CommonLogic.checkMandatory(rowErrList, glblDSMsg.rtlWhNm_ER, TMPL_DL_HEADER_FOR_EXS_RMA[10]);
            }
            // 2019/03/25 S21_NA#30294 Add End
            NWAL2320CommonLogic.checkMandatory(rowErrList, glblDSMsg.rtrnRsnDescTxt_ER, TMPL_DL_HEADER_FOR_EXS_RMA[11]);

            if (ZYPCommonFunc.hasValue(glblDSMsg.ordQty_ER)) {
                // QC#10320
                if (glblDSMsg.ordQty_ER.getValueInt() >= 0) {
                    NWXC220001ErrorInfo errorInfo = new NWXC220001ErrorInfo(NWAL2320Constant.NWZM1596E);
                    rowErrList.add(errorInfo);
                }
            }
            
            // 2019/03/26 S21_NA#30924 Add Start
            if (ZYPCommonFunc.hasValue(glblDSMsg.serNum_ER)) {
                ZYPEZDItemValueSetter.setValue(glblDSMsg.serNum_ER, glblDSMsg.serNum_ER.getValue().toUpperCase());
            }
            // 2019/03/26 S21_NA#30924 Add End

            //******************************************************************
            // Create Config Level MsgList
            //******************************************************************
            configLevelKey = String.format("%s_%s", glblDSMsg.cpoOrdNum_ER.getValue(), glblDSMsg.dsOrdPosnNum_ER.getValue());
            if (!configLevelMsgMap.containsKey(configLevelKey)) {
                levelMsgList = new ArrayList<NWAL2320_DSMsg>();
                configLevelMsgMap.put(configLevelKey, levelMsgList);
            }
            levelMsgList = configLevelMsgMap.get(configLevelKey);
            levelMsgList.add(glblDSMsg);
        }

        //**********************************************************************
        // Config Group Item Check
        //**********************************************************************
        NWAL2320_DSMsg srcMsg;
        for (List<NWAL2320_DSMsg> targetMsgList : configLevelMsgMap.values()) {
            srcMsg = null;
            for (NWAL2320_DSMsg targetMsg : targetMsgList) {
                if (srcMsg == null) {
                    srcMsg = targetMsg;
                    continue;
                }

                rowErrList = allRowErrMap.get(targetMsg.hashCode());
                NWAL2320CommonLogic.checkSameSMsgValue(rowErrList, srcMsg.billToCustLocCd_ER, targetMsg.billToCustLocCd_ER, TMPL_DL_HEADER_FOR_EXS_RMA[6]);
                NWAL2320CommonLogic.checkSameSMsgValue(rowErrList, srcMsg.shipToCustLocCd_ER, targetMsg.shipToCustLocCd_ER, TMPL_DL_HEADER_FOR_EXS_RMA[7]);
                NWAL2320CommonLogic.checkSameSMsgValue(rowErrList, srcMsg.tocNm_ER, targetMsg.tocNm_ER, TMPL_DL_HEADER_FOR_EXS_RMA[9]);
                NWAL2320CommonLogic.checkSameSMsgValue(rowErrList, srcMsg.xxShpgOrdCmntTxt_ER, targetMsg.xxShpgOrdCmntTxt_ER, TMPL_DL_HEADER_FOR_EXS_RMA[12]);
            }
        }

        //**********************************************************************
        // Set Upload Error
        //**********************************************************************
        String errString;
        for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
            glblDSMsg = glblMsg.D.no(i);
            rowErrList = allRowErrMap.get(glblDSMsg.hashCode());
            errString = "";
            if (rowErrList.size() > 0) {
                errString = NWAL2320CommonLogic.createErrString(rowErrList, bizMsg.maxLgNum.getValueInt());
                isSuccess = false;
            }
            ZYPEZDItemValueSetter.setValue(glblDSMsg.xxComnScrColValTxt_ER, errString);
        }

        return isSuccess;
    }

    private static BigDecimal getUploadCheckErrorNum(NWAL2320SMsg glblMsg, String ordUpldTmplTpCd) {
        BigDecimal errNum = BigDecimal.ZERO;

        if (ORD_UPLD_TMPL_TP.NEW_SALES_ORDER.equals(ordUpldTmplTpCd)) {
            for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
                if (ZYPCommonFunc.hasValue(glblMsg.A.no(i).xxComnScrColValTxt_CO)) {
                    errNum = errNum.add(BigDecimal.ONE);
                }
            }
        } else if (ORD_UPLD_TMPL_TP.EXISTING_SALES_ORDER.equals(ordUpldTmplTpCd)) {
            for (int i = 0; i < glblMsg.B.getValidCount(); i++) {
                if (ZYPCommonFunc.hasValue(glblMsg.B.no(i).xxComnScrColValTxt_EO)) {
                    errNum = errNum.add(BigDecimal.ONE);
                }
            }
        } else if (ORD_UPLD_TMPL_TP.NEW_RMA_ORDER.equals(ordUpldTmplTpCd)) {
            for (int i = 0; i < glblMsg.C.getValidCount(); i++) {
                if (ZYPCommonFunc.hasValue(glblMsg.C.no(i).xxComnScrColValTxt_NR)) {
                    errNum = errNum.add(BigDecimal.ONE);
                }
            }
        } else if (ORD_UPLD_TMPL_TP.EXISTING_RMA_ORDER.equals(ordUpldTmplTpCd)) {
            for (int i = 0; i < glblMsg.D.getValidCount(); i++) {
                if (ZYPCommonFunc.hasValue(glblMsg.D.no(i).xxComnScrColValTxt_ER)) {
                    errNum = errNum.add(BigDecimal.ONE);
                }
            }
        }

        return errNum;
    }

}
