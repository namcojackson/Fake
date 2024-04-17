/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFAL0230.common;

import static business.blap.NFAL0230.constant.NFAL0230Constant.CSV_FILE_EXT;
import static business.blap.NFAL0230.constant.NFAL0230Constant.CSV_FILE_NAME_DOWNLOAD;
import static business.blap.NFAL0230.constant.NFAL0230Constant.CSV_HEADER;
import static business.blap.NFAL0230.constant.NFAL0230Constant.CSV_MAX_ROW;
import static business.blap.NFAL0230.constant.NFAL0230Constant.DEFALT_MAX_LEVEL;
import static business.blap.NFAL0230.constant.NFAL0230Constant.NFAM0157I;
import static business.blap.NFAL0230.constant.NFAL0230Constant.NFAM0174I;
import static business.blap.NFAL0230.constant.NFAL0230Constant.NFAM0175W;
import static business.blap.NFAL0230.constant.NFAL0230Constant.ZZZM9002W;

import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import business.blap.NFAL0230.NFAL0230CMsg;
import business.blap.NFAL0230.NFAL0230Query;
import business.blap.NFAL0230.NFAL0230SMsg;
import business.file.NFAL0230F00FMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/23   Fujitsu         T.Murai         Create          N/A
 *</pre>
 */
public class NFAL0230CommonLogic {

    /**
     * Search
     * @param bizMsg NFAL0230CMsg
     * @param glblMsg NFAL0230SMsg
     */
    public static void search(NFAL0230CMsg bizMsg, NFAL0230SMsg glblMsg) {

        // Get Top Level Value
        S21SsmEZDResult ssmResult = selectSqlForTopLevelSearch(bizMsg);

        List<Map<String, Object>> treeTopLevelVlaueMapList = null;
        if (ssmResult != null && ssmResult.isCodeNormal()) {
            treeTopLevelVlaueMapList = (List<Map<String, Object>>) ssmResult.getResultObject();
        }
        if (treeTopLevelVlaueMapList == null || treeTopLevelVlaueMapList.size() == 0) {
            bizMsg.setMessageInfo(NFAM0174I);
            return;
        }

        for (Map<String, Object> topLevelValueMap : treeTopLevelVlaueMapList) {

            ssmResult = selectSqlForTreeSearch(bizMsg, glblMsg, topLevelValueMap);
            if (ssmResult == null || ssmResult.isCodeNormal()) {
                int bizMsgCount = bizMsg.A.getValidCount();
                int i = 0;
                for (; i < glblMsg.A.getValidCount(); i++) {
                    if (i + bizMsgCount >= bizMsg.A.length()) {
                        bizMsg.setMessageInfo(NFAM0157I, new String[] {String.valueOf(bizMsg.A.length()) ,String.valueOf(bizMsg.A.length())});
                        break;
                    }
                    EZDMsg.copy(glblMsg.A.no(i), null, bizMsg.A.no(i + bizMsgCount), null);
                    ZYPEZDItemValueSetter.setValue(bizMsg.A.no(i + bizMsgCount).xxCoaExtnSrchTxt_0, bizMsg.arcsSplyCoaSegTxt);
                }
                bizMsg.A.setValidCount(i + bizMsgCount);
                if (bizMsg.A.getValidCount() == bizMsg.A.length()) {
                    break;
                }
            }
        }

        ssmResult = NFAL0230Query.getInstance().getMaxTreeLevel(bizMsg);
        int maxLevel = ((Integer) ssmResult.getResultObject()).intValue();
        if (maxLevel > DEFALT_MAX_LEVEL) {
            bizMsg.setMessageInfo(NFAM0175W);
        }
    }

    /**
     * Select SQL For top Level Search
     * @param bizMsg NFAL0230CMsg
     * @return S21SsmEZDResult top Level Value
     */
    public static S21SsmEZDResult selectSqlForTopLevelSearch(NFAL0230CMsg bizMsg) {

        String segmentNm = bizMsg.coaSegNm.getValue();
        S21SsmEZDResult ssmResult = new S21SsmEZDResult();
        ssmResult = NFAL0230Query.getInstance().getTopLevelValueSeg(bizMsg, segmentNm);

        return ssmResult;
    }

    /**
     * select SQL For Tree Search
     * @param bizMsg NFAL0230CMsg
     * @param glblMsg NFAL0230SMsg
     * @param topLevelValueMap Map<String, Object>
     * @return String segment Value Name
     */
    public static S21SsmEZDResult selectSqlForTreeSearch(NFAL0230CMsg bizMsg, NFAL0230SMsg glblMsg, Map<String, Object> topLevelValueMap) {

        S21SsmEZDResult ssmResult = new S21SsmEZDResult();
        ssmResult = NFAL0230Query.getInstance().getTreeInfoSeg(bizMsg, glblMsg, topLevelValueMap);

        return ssmResult;
    }

    /**
     * csv Download Tree result
     * @param bizMsg NFAL0230CMsg
     */
    public static void csvDownload(NFAL0230CMsg bizMsg) {

        NFAL0230F00FMsg fMsg = new NFAL0230F00FMsg();
        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME_DOWNLOAD), CSV_FILE_EXT);
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);
        csvOutFile.writeHeader(CSV_HEADER);

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

            int rowCount = 1;
            if (rowCount > CSV_MAX_ROW) {
                bizMsg.setMessageInfo(ZZZM9002W);
            }

            fMsg.clear();
            EZDMsg.copy(bizMsg.A.no(i), null, fMsg, null);

            csvOutFile.write();
            rowCount++;
        }
        csvOutFile.close();
    }
}
