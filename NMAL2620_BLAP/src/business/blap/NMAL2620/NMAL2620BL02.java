/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NMAL2620;

import static business.blap.NMAL2620.constant.NMAL2620Constant.CSV_DOWNLOAD_FILE_NAME;
import static business.blap.NMAL2620.constant.NMAL2620Constant.CSV_DOWNLOAD_HEADER;
import static business.blap.NMAL2620.constant.NMAL2620Constant.NMAM0192E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL2620.common.NMAL2620CommonLogic;
import business.blap.NMAL2620.constant.NMAL2620Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;

/**
 *<pre>
 * Territory Mass Update
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/26   Hitachi         T.Mizuki        Create          N/A
 * 2016/04/27   Fujitsu         C.Yokoi         Update          N/A
 *</pre>
 */
public class NMAL2620BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {

        NMAL2620CMsg cMsg = (NMAL2620CMsg) arg0;
        NMAL2620SMsg sMsg = (NMAL2620SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NMAL2620_INIT".equals(screenAplID) || "NMAL2620Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL2620_Init(cMsg, sMsg);
            } else if ("NMAL2620Scrn00_Search".equals(screenAplID)) {
                doProcess_NMAL2620_Search(cMsg, sMsg);
            } else if ("NMAL2620Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NMAL2620_TBLColumnSort(cMsg, sMsg);
                // } else if ("NMAL2620Scrn00_CMN_Download".equals(screenAplID)) {
                // doProcess_NMAL2620Scrn00_Download((NMAL2620CMsg) cMsg, (NMAL2620SMsg) sMsg);
            } else if ("NMAL2620Scrn00_SelectUnselect_All".equals(screenAplID)) {
                doProcess_NMAL2620Scrn00_SelectUnselect_All(cMsg, (NMAL2620SMsg) sMsg);
            } else if ("NMAL2620Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL2620Scrn00_CMN_Submit(cMsg, (NMAL2620SMsg) sMsg);
            } else if ("NMAL2620Scrn00_GetPersonNum".equals(screenAplID)) {
                doProcess_NMAL2620Scrn00_GetPersonNum(cMsg, (NMAL2620SMsg) sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NMAL2620_Init(NMAL2620CMsg cMsg, NMAL2620SMsg sMsg) {

        cMsg.clear();
        sMsg.clear();
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        ZYPGUITableColumn.clearColData(cMsg, sMsg);
        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());

        NMAL2620CommonLogic.createPullDown(cMsg);
    }

    private void doProcess_NMAL2620_TBLColumnSort(NMAL2620CMsg cMsg, NMAL2620SMsg sMsg) {

        String sortTblNm = cMsg.xxSortTblNm.getValue();
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            EZDMsg.copy(cMsg.A.no(i), null, sMsg.A.no(i), null);
        }
        if ("A".equals(sortTblNm)) {
            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

            // SMsg -> CMsg
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

        }
    }

    private void doProcess_NMAL2620_Search(NMAL2620CMsg cMsg, NMAL2620SMsg sMsg) {

        if (!NMAL2620CommonLogic.hasValueItems(cMsg.psnNum_H, cMsg.orgNm, cMsg.psnCd, cMsg.xxPsnNm_H, cMsg.bizAreaOrgCd_V)) {
            cMsg.setMessageInfo(NMAM0192E);
            return;
        }
        NMAL2620CommonLogic.getSearchData(cMsg, sMsg);
        NMAL2620CommonLogic.formatPsnName(sMsg);
        EZDMsg.copy(sMsg, null, cMsg, null);
    }

    private void doProcess_NMAL2620Scrn00_SelectUnselect_All(NMAL2620CMsg cMsg, NMAL2620SMsg sMsg) {

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_AL.getValue())) {
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).xxChkBox_A, ZYPConstant.FLG_ON_Y);
            } else {
                cMsg.A.no(i).xxChkBox_A.clear();
            }
            EZDMsg.copy(cMsg.A.no(i), null, sMsg.A.no(i), null);
        }
    }

    /**
     * do process (download)
     * @param cMsg NMAL2620CMsg
     * @param sMsg NMAL2620SMsg
     */
    private void doProcess_NMAL2620Scrn00_Download(NMAL2620CMsg cMsg, NMAL2620SMsg sMsg) {
        cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_DOWNLOAD_FILE_NAME), ".csv");

        NMAL2620F00FMsg fMsg = new NMAL2620F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        csvOutFile.writeHeader(CSV_DOWNLOAD_HEADER);

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            fMsg.clear();
            ZYPEZDItemValueSetter.setValue(fMsg.orgNm, sMsg.A.no(i).orgNm_A.getValue());
            ZYPEZDItemValueSetter.setValue(fMsg.psnNum, sMsg.A.no(i).psnNum_A.getValue());
            csvOutFile.write();
        }
        csvOutFile.close();
    }

    /**
     * do process (Submit)
     * @param cMsg NMAL2620CMsg
     * @param sMsg NMAL2620SMsg
     */
    private void doProcess_NMAL2620Scrn00_CMN_Submit(NMAL2620CMsg cMsg, NMAL2620SMsg sMsg) {
        // 2016/04/27 CSA-QC#7614 Add Start
        if (NMAL2620Constant.MESSAGE_KIND_ERROR.equals(cMsg.getMessageKind())) {
            return;
        }

        NMAL2620CommonLogic.clearDetailControl(cMsg);
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            cMsg.A.no(i).xxChkBox_A.clear();
        }

        cMsg.setMessageInfo(NMAL2620Constant.NMAM8182I, new String[] {"Submit"});
        // 2016/04/27 CSA-QC#7614 Add End
    }

    /**
     * do process (Get Person number)
     * @param cMsg NMAL2620CMsg
     * @param sMsg NMAL2620SMsg
     */
    private void doProcess_NMAL2620Scrn00_GetPersonNum(NMAL2620CMsg cMsg, NMAL2620SMsg sMsg) {
        // 2016/04/27 Add Start
        S21SsmEZDResult ssmResult = NMAL2620Query.getInstance().getPsnNum(cMsg);
        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt >= 2) {
                cMsg.setMessageInfo(NMAL2620Constant.NMAM8489E, new String[] {"Resource#"});
            } else {
                List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
                ZYPEZDItemValueSetter.setValue(cMsg.psnNum_D, (String)resultList.get(0).get("PSN_NUM"));
            }
        } else {
            // No result
            cMsg.setMessageInfo(NMAL2620Constant.NMAM0009E, new String[] {"Resource"});
        }
    }
    // 2016/04/27 Add End

}
