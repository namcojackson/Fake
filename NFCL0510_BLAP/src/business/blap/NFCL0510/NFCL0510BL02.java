package business.blap.NFCL0510;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NFCL0510.common.NFCL0510CommonLogic;
import business.blap.NFCL0510.constant.NFCL0510Constant;
import business.file.NFCL0510F00FMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_LOCK_BOX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_LOCK_BOX_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Lock box Error Correction Screen
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 12/21/2016   Fujitsu         T.Murai         Update          QC#16710
 * 01/30/2018   Hitachi         Y.Takeno        Update          QC#19728
 * 06/11/2018   Hitachi         Y.Takeno        Create          QC#19723
 * 2023/07/03   Hitachi         S.Nakatani      Update          QC#55645
 *</pre>
 */
public class NFCL0510BL02 extends S21BusinessHandler implements NFCL0510Constant {

    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NFCL0510_INIT".equals(screenAplID)) {
                doProcess_NFCL0510_INIT(cMsg, sMsg);

            } else if ("NFCL0510Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NFCL0510Scrn00_CMN_Clear(cMsg, sMsg);

            } else if ("NFCL0510Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NFCL0510Scrn00_PageNext((NFCL0510CMsg) cMsg, (NFCL0510SMsg) sMsg);

            } else if ("NFCL0510Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NFCL0510Scrn00_PagePrev((NFCL0510CMsg) cMsg, (NFCL0510SMsg) sMsg);

            } else if ("NFCL0510Scrn00_Click_Search".equals(screenAplID)) {
                doProcess_NFCL0510Scrn00_Click_Search(cMsg, sMsg);

            } else if ("NFCL0510Scrn00_Click_Link_LockboxFileName".equals(screenAplID)) {
                doProcess_NFCL0510Scrn00_Click_Link_LockboxFileName(cMsg, sMsg);

            } else if ("NFCL0510Scrn00_Click_Link_BatchNumber".equals(screenAplID)) {
                doProcess_NFCL0510Scrn00_Click_Link_BatchNumber(cMsg, sMsg);

            } else if ("NFCL0510Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFCL0510Scrn00_CMN_Submit(cMsg, sMsg);

            // START 2018/06/11 [QC#19723, ADD]
            } else if ("NFCL0510Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NFCL0510Scrn00_CMN_Download(cMsg, sMsg);
            // END   2018/06/11 [QC#19723, ADD]

            } else {

                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL0510_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL0510_INIT================================START", this);

        NFCL0510CMsg bizMsg = (NFCL0510CMsg) cMsg;
        NFCL0510SMsg globalMsg = (NFCL0510SMsg) sMsg;

        bizMsg.arLockBoxFileNm_H.clear();
        bizMsg.arLockBoxCd_H.clear();
        bizMsg.arLockBoxCd_LC.clear();
        bizMsg.arLockBoxNm_LD.clear();
        bizMsg.arLockBoxBatNum_H.clear();
        bizMsg.arLockBoxStsCd_LC.clear();
        bizMsg.arLockBoxStsDescTxt_LD.clear();
        bizMsg.arLockBoxBatNum_H.clear();
        bizMsg.arBatInfoMsgTxt_H.clear();
        bizMsg.rcvDt_H1.clear();
        bizMsg.rcvDt_H2.clear();
        bizMsg.arBatInfoMsgTxt_H.clear();
        ZYPEZDItemValueSetter.setValue(bizMsg.arLockBoxStsCd_H, AR_LOCK_BOX_STS.ERROR);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_H, ZYPConstant.FLG_OFF_N);

        bizMsg.xxPageShowFromNum_A.clear();
        bizMsg.xxPageShowToNum_A.clear();
        bizMsg.xxPageShowOfNum_A.clear();
        bizMsg.xxPageShowFromNum_B.clear();
        bizMsg.xxPageShowToNum_B.clear();
        bizMsg.xxPageShowOfNum_B.clear();

        ZYPCodeDataUtil.createPulldownList(AR_LOCK_BOX.class, bizMsg.arLockBoxCd_LC, bizMsg.arLockBoxNm_LD, ":");
        ZYPCodeDataUtil.createPulldownList(AR_LOCK_BOX_STS.class, bizMsg.arLockBoxStsCd_LC, bizMsg.arLockBoxStsDescTxt_LD, ":");

        ZYPTableUtil.clear(bizMsg.A);
        bizMsg.A.setValidCount(0);

        bizMsg.arLockBoxFileNm_BH.clear();
        bizMsg.arLockBoxBatNum_BH.clear();
        bizMsg.arBatInfoMsgTxt_H.clear();

        ZYPTableUtil.clear(bizMsg.B);
        bizMsg.A.setValidCount(0);

        globalMsg.clear();
        ZYPTableUtil.clear(globalMsg.A);
        ZYPTableUtil.clear(globalMsg.B);
        globalMsg.A.setValidCount(0);
        globalMsg.B.setValidCount(0);
        

        ZYPEZDItemValueSetter.setValue(bizMsg.xxDplyTab, "Detail");

        // START 2018/01/30 [QC#19728, ADD]
        if (ZYPCommonFunc.hasValue(bizMsg.arBatRcptNm_P)) {
            getLockboxListFromArBatRcptNm(bizMsg, globalMsg);
        }
        // END   2018/01/30 [QC#19728, ADD]

        EZDDebugOutput.println(1, "doProcess_NFCL0510_INIT================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL0510Scrn00_CMN_Clear(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL0510Scrn00_CMN_Clear================================START", this);
        NFCL0510SMsg globalMsg = (NFCL0510SMsg) sMsg;

        globalMsg.arLockBoxFileNm_H.clear();
        globalMsg.arLockBoxCd_H.clear();
        globalMsg.arLockBoxStsCd_H.clear();
        globalMsg.arLockBoxBatNum_H.clear();
        globalMsg.arBatInfoMsgTxt_H.clear();
        globalMsg.rcvDt_H1.clear();
        globalMsg.rcvDt_H2.clear();
        globalMsg.xxChkBox_H.clear();

        ZYPEZDItemValueSetter.setValue(globalMsg.xxChkBox_H, ZYPConstant.FLG_OFF_N);

        doProcess_NFCL0510_INIT(cMsg, sMsg);

        EZDDebugOutput.println(1, "doProcess_NFCL0510Scrn00_CMN_Clear================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL0510Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL0510Scrn00_CMN_Submit================================START", this);
        NFCL0510CMsg bizMsg = (NFCL0510CMsg) cMsg;
        NFCL0510SMsg globalMsg = (NFCL0510SMsg) sMsg;

        if (bizMsg.getMessageKind().equals("E")) {
            return;
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.arLockBoxFileNm_H, globalMsg.arLockBoxFileNm_H);
        ZYPEZDItemValueSetter.setValue(bizMsg.arLockBoxCd_H, globalMsg.arLockBoxCd_H);
        ZYPEZDItemValueSetter.setValue(bizMsg.arLockBoxBatNum_H, globalMsg.arLockBoxBatNum_H);
        ZYPEZDItemValueSetter.setValue(bizMsg.arLockBoxStsCd_H, globalMsg.arLockBoxStsCd_H);
        ZYPEZDItemValueSetter.setValue(bizMsg.arBatInfoMsgTxt_H, globalMsg.arBatInfoMsgTxt_H);
        ZYPEZDItemValueSetter.setValue(bizMsg.rcvDt_H1, globalMsg.rcvDt_H1);
        ZYPEZDItemValueSetter.setValue(bizMsg.rcvDt_H2, globalMsg.rcvDt_H2);
        ZYPEZDItemValueSetter.setValue(bizMsg.arBatInfoMsgTxt_H, globalMsg.arBatInfoMsgTxt_H);
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_H, globalMsg.xxChkBox_H);

        doProcess_NFCL0510Scrn00_Click_Search(bizMsg, globalMsg);

        // START 2016/12/22 T.Murai [QC#16710,ADD]
        if (!hasHeaderForDetailSearch(globalMsg)) {
            globalMsg.arLockBoxFileNm_BH.clear();
            globalMsg.arLockBoxBatNum_BH.clear();
        }
        // END   2016/12/22 T.Murai [QC#16710,ADD]

        ZYPEZDItemValueSetter.setValue(bizMsg.arLockBoxFileNm_BH, globalMsg.arLockBoxFileNm_BH);
        ZYPEZDItemValueSetter.setValue(bizMsg.arLockBoxBatNum_BH, globalMsg.arLockBoxBatNum_BH);

        doProcess_NFCL0510Scrn00_Click_Link_BatchNumber(bizMsg, globalMsg);

        EZDDebugOutput.println(1, "doProcess_NFCL0510Scrn00_CMN_Submit================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL0510Scrn00_Click_Search(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL0510Scrn00_Search================================START", this);

        NFCL0510CMsg bizMsg = (NFCL0510CMsg) cMsg;
        NFCL0510SMsg globalMsg = (NFCL0510SMsg) sMsg;

        getLockboxList(bizMsg, globalMsg);

        EZDDebugOutput.println(1, "doProcess_NFCL0510Scrn00_Search================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL0510Scrn00_Click_Link_LockboxFileName(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL0510Scrn00_Click_Link_LockboxFileName================================START", this);

        NFCL0510CMsg bizMsg = (NFCL0510CMsg) cMsg;
        NFCL0510SMsg globalMsg = (NFCL0510SMsg) sMsg;

        getReceiptInvoiceList(bizMsg, globalMsg);

        EZDDebugOutput.println(1, "doProcess_NFCL0510Scrn00_Click_Link_LockboxFileName================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL0510Scrn00_Click_Link_BatchNumber(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL0510Scrn00_Click_Link_BatchNumber================================START", this);

        NFCL0510CMsg bizMsg = (NFCL0510CMsg) cMsg;
        NFCL0510SMsg globalMsg = (NFCL0510SMsg) sMsg;

        getReceiptInvoiceList(bizMsg, globalMsg);

        EZDDebugOutput.println(1, "doProcess_NFCL0510Scrn00_Click_Link_BatchNumber================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL0510Scrn00_PagePrev(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL0510Scrn00_PagePrev================================START", this);
        NFCL0510CMsg bizMsg = (NFCL0510CMsg) cMsg;
        NFCL0510SMsg globalMsg = (NFCL0510SMsg) sMsg;

        if (NFCL0510CommonLogic.copyPage(bizMsg, globalMsg)) {
            NFCL0510CommonLogic.prevPage(bizMsg, globalMsg);
        }
        NFCL0510CommonLogic.dispPage(getGlobalCompanyCode(), bizMsg, globalMsg);
        EZDDebugOutput.println(1, "doProcess_NFCL0510Scrn00_PagePrev================================END", this);
    }

    /**
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NFCL0510Scrn00_PageNext(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL0510Scrn00_PageNext================================START", this);
        NFCL0510CMsg bizMsg = (NFCL0510CMsg) cMsg;
        NFCL0510SMsg globalMsg = (NFCL0510SMsg) sMsg;

        if (NFCL0510CommonLogic.copyPage(bizMsg, globalMsg)) {
            NFCL0510CommonLogic.nextPage(bizMsg, globalMsg);
        }
        NFCL0510CommonLogic.dispPage(getGlobalCompanyCode(), bizMsg, globalMsg);
        EZDDebugOutput.println(1, "doProcess_NFCL0510Scrn00_PageNext================================END", this);
    }

    /**
     * @param bizMsg Business Component Interface Message
     * @param globalMsg Global area information
     */
    private void getLockboxList(NFCL0510CMsg bizMsg, NFCL0510SMsg globalMsg) {

        globalMsg.A.clear();
        globalMsg.B.clear();
        globalMsg.A.setValidCount(0);
        globalMsg.B.setValidCount(0);
        bizMsg.A.clear();
        bizMsg.B.clear();
        bizMsg.A.setValidCount(0);
        bizMsg.B.setValidCount(0);

        bizMsg.arLockBoxFileNm_BH.clear();
        bizMsg.arLockBoxBatNum_BH.clear();

        if (bizMsg.arLockBoxStsCd_H.getValue().equals(AR_LOCK_BOX_STS.RECEIPT_WORK_GENERATED)) {
            ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_H, ZYPConstant.FLG_ON_Y);
        }

        Map<String, Object> ssmMap = new HashMap<String, Object>();
        ssmMap.put("glblCmpyCd", getGlobalCompanyCode());
        ssmMap.put("cMsg", bizMsg);
        ssmMap.put("rowNum", String.valueOf(globalMsg.A.length()));
        ssmMap.put("LockboxStsGenerated", AR_LOCK_BOX_STS.RECEIPT_WORK_GENERATED);
        // START 2018/06/11 [QC#19723, ADD]
        if (AR_LOCK_BOX_STS.ERROR.equals(bizMsg.arLockBoxStsCd_H.getValue())) {
            ssmMap.put("ArRcptErrFlgY", ZYPConstant.FLG_ON_Y);
        }
        // END   2018/06/11 [QC#19723, ADD]

        S21SsmEZDResult ssmResult = NFCL0510Query.getInstance().getLockboxList(ssmMap, globalMsg);

        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();

            if (queryResCnt > globalMsg.A.length()) {

                bizMsg.setMessageInfo("NZZM0001W");

                queryResCnt = globalMsg.A.length();
            } else {

                bizMsg.setMessageInfo("NZZM0002I");
            }

            NFCL0510CommonLogic.createPulldownListLockBoxStatus(getGlobalCompanyCode(), globalMsg);

            bizMsg.xxPageShowFromNum_A.setValue(1);
            NFCL0510CommonLogic.dispPage(getGlobalCompanyCode(), bizMsg, globalMsg);

            ZYPEZDItemValueSetter.setValue(globalMsg.arLockBoxFileNm_H, bizMsg.arLockBoxFileNm_H);
            ZYPEZDItemValueSetter.setValue(globalMsg.arLockBoxCd_H, bizMsg.arLockBoxCd_H);
            ZYPEZDItemValueSetter.setValue(globalMsg.arLockBoxBatNum_H, bizMsg.arLockBoxBatNum_H);
            ZYPEZDItemValueSetter.setValue(globalMsg.arLockBoxStsCd_H, bizMsg.arLockBoxStsCd_H);
            ZYPEZDItemValueSetter.setValue(globalMsg.arBatInfoMsgTxt_H, bizMsg.arBatInfoMsgTxt_H);
            ZYPEZDItemValueSetter.setValue(globalMsg.rcvDt_H1, bizMsg.rcvDt_H1);
            ZYPEZDItemValueSetter.setValue(globalMsg.rcvDt_H2, bizMsg.rcvDt_H2);
            ZYPEZDItemValueSetter.setValue(globalMsg.arBatInfoMsgTxt_H, bizMsg.arBatInfoMsgTxt_H);
            ZYPEZDItemValueSetter.setValue(globalMsg.xxChkBox_H, bizMsg.xxChkBox_H);

        } else {
            bizMsg.setMessageInfo("NZZM0002I");
            bizMsg.A.setValidCount(0);
            bizMsg.xxPageShowFromNum_A.setValue(1);
            bizMsg.xxPageShowToNum_A.setValue(bizMsg.A.getValidCount());
            bizMsg.xxPageShowOfNum_A.setValue(0);
        }
    }

    /**
     * @param bizMsg Business Component Interface Message
     * @param globalMsg Global area information
     */
    private void getReceiptInvoiceList(NFCL0510CMsg bizMsg, NFCL0510SMsg globalMsg) {

        Map<String, Object> ssmMap = new HashMap<String, Object>();
        ssmMap.put("glblCmpyCd", getGlobalCompanyCode());
        ssmMap.put("cMsg", bizMsg);
        ssmMap.put("rowNum", String.valueOf(globalMsg.B.length()));
        // START 2018/06/11 [QC#19723, ADD]
        if (AR_LOCK_BOX_STS.ERROR.equals(bizMsg.arLockBoxStsCd_H.getValue())) {
            ssmMap.put("ArRcptErrFlgY", ZYPConstant.FLG_ON_Y);
        }
        // END   2018/06/11 [QC#19723, ADD]

        S21SsmEZDResult ssmResult = NFCL0510Query.getInstance().getReceiptInvoiceList(ssmMap, globalMsg);

        if (ssmResult.isCodeNormal()) {

            int queryResCnt = ssmResult.getQueryResultCount();

            if (queryResCnt > globalMsg.B.length()) {

                bizMsg.setMessageInfo("NZZM0001W");

            } else {

                bizMsg.setMessageInfo("NZZM0002I");
            }

            bizMsg.xxPageShowFromNum_B.setValue(1);
            NFCL0510CommonLogic.dispPage(getGlobalCompanyCode(), bizMsg, globalMsg);
            ZYPEZDItemValueSetter.setValue(globalMsg.arLockBoxFileNm_BH, bizMsg.arLockBoxFileNm_BH);
            ZYPEZDItemValueSetter.setValue(globalMsg.arLockBoxBatNum_BH, bizMsg.arLockBoxBatNum_BH);
        } else {
            bizMsg.setMessageInfo("NZZM0002I");
            bizMsg.B.setValidCount(0);
            bizMsg.xxPageShowFromNum_B.setValue(1);
            bizMsg.xxPageShowToNum_B.setValue(bizMsg.B.getValidCount());
            bizMsg.xxPageShowOfNum_B.setValue(0);
        }
    }
    // START 2016/12/22 [QC#16710,ADD]
    /**
     * @param globalMsg Global area information
     */
    private boolean hasHeaderForDetailSearch(NFCL0510SMsg globalMsg) {
        
        String fileNm_B = globalMsg.arLockBoxFileNm_BH.getValue();
        String batNum_B = globalMsg.arLockBoxBatNum_BH.getValue();
        
        if (!ZYPCommonFunc.hasValue(fileNm_B)) {
            return true;
        }
        for(int i = 0 ; i < globalMsg.A.getValidCount(); i++) {
            NFCL0510_ASMsg headerMsg = globalMsg.A.no(i);
            if (fileNm_B.equals(headerMsg.arLockBoxFileNm_A.getValue())) {
                if (!ZYPCommonFunc.hasValue(batNum_B)) {
                    return true;
                }
                if (batNum_B.equals(headerMsg.arLockBoxBatNum_A.getValue())) {
                    return true;
                }
            }
        }
        return false;
    }
     // START 2016/12/22 [QC#16710,ADD]

    // START 2018/01/30 [QC#19728, ADD]
    private void getLockboxListFromArBatRcptNm(NFCL0510CMsg bizMsg, NFCL0510SMsg globalMsg) {

        Map<String, Object> ssmMap = new HashMap<String, Object>();
        ssmMap.put("glblCmpyCd", getGlobalCompanyCode());
        ssmMap.put("arBatRcptNm", bizMsg.arBatRcptNm_P.getValue());

        S21SsmEZDResult ssmResult = NFCL0510Query.getInstance().getArRcprRcvIntfc(ssmMap);
        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> resultList = (List<Map<String, Object>>)ssmResult.getResultObject();
            if (resultList == null || resultList.size() == 0) {
                return;
            }
            Map<String, Object> result = resultList.get(0);
            ZYPEZDItemValueSetter.setValue(bizMsg.arLockBoxFileNm_H, (String) result.get("AR_LOCK_BOX_FILE_NM"));
            ZYPEZDItemValueSetter.setValue(bizMsg.arLockBoxBatNum_H, (String) result.get("AR_LOCK_BOX_BAT_NUM"));
            bizMsg.arLockBoxStsCd_H.clear();
            ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_H, ZYPConstant.FLG_ON_Y);

            getLockboxList(bizMsg, globalMsg);
        }
    }
    // END   2018/01/30 [QC#19728, ADD]

    // START 2018/06/11 [QC#19723, ADD]
    private void doProcess_NFCL0510Scrn00_CMN_Download(EZDCMsg cMsg, EZDSMsg sMsg) {
        EZDDebugOutput.println(1, "doProcess_NFCL0510_Download================================START", this);

        NFCL0510CMsg bizMsg = (NFCL0510CMsg) cMsg;
        NFCL0510SMsg globalMsg = (NFCL0510SMsg) sMsg;

        NFCL0510F00FMsg fMsg = new NFCL0510F00FMsg();
        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(NFCL0510Constant.CSV_NAME), NFCL0510Constant.CSV);
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);

        // write header
        csvOutFile.writeHeader(NFCL0510Constant.CSV_HEADER);

        Map<String, Object> ssmMap = new HashMap<String, Object>();
        ssmMap.put("glblCmpyCd", getGlobalCompanyCode());
        ssmMap.put("cMsg", bizMsg);
        ssmMap.put("rowNum", String.valueOf(globalMsg.B.length()));
        if (AR_LOCK_BOX_STS.ERROR.equals(bizMsg.arLockBoxStsCd_H.getValue())) {
            ssmMap.put("ArRcptErrFlgY", ZYPConstant.FLG_ON_Y);
        }

        S21SsmEZDResult ssmResult = NFCL0510Query.getInstance().getReceiptInvoiceDownloadList(ssmMap, globalMsg);
        if (ssmResult.isCodeNormal()) {
            // write contents
            int rowCnt = 0;
            List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
            for (Map<String, Object> result : resultList) {

                ZYPEZDItemValueSetter.setValue(fMsg.arLockBoxBatNum_B, (String) result.get("AR_LOCK_BOX_BAT_NUM_B"));
                ZYPEZDItemValueSetter.setValue(fMsg.arLockBoxBatLineNum_B, (String) result.get("AR_LOCK_BOX_BAT_LINE_NUM_B"));
                StringBuilder builder = new StringBuilder();
                if (ZYPCommonFunc.hasValue((String) result.get("AR_BAT_INFO_LVL_CD"))) {
                    builder.append((String) result.get("AR_BAT_INFO_LVL_CD"));
                }
                if (builder.length() > 0) {
                    builder.append(":");
                }
                if (ZYPCommonFunc.hasValue((String) result.get("AR_BAT_INFO_MSG_TXT"))) {
                    builder.append((String) result.get("AR_BAT_INFO_MSG_TXT"));
                }
                ZYPEZDItemValueSetter.setValue(fMsg.arBatInfoMsgTxt_B, builder.toString());
                ZYPEZDItemValueSetter.setValue(fMsg.custRcptNum_B, (String) result.get("CUST_RCPT_NUM_B"));
                ZYPEZDItemValueSetter.setValue(fMsg.custRcptAmt_B, (BigDecimal) result.get("CUST_RCPT_AMT_B"));
                ZYPEZDItemValueSetter.setValue(fMsg.custBankRteNum_B, (String) result.get("CUST_BANK_RTE_NUM_B"));
                // START 2023/07/03 S.Nakatani [QC#55645, MOD]
//                ZYPEZDItemValueSetter.setValue(fMsg.custDsBankAcctNum_B, (String) result.get("CUST_DS_BANK_ACCT_NUM_B"));
                ZYPEZDItemValueSetter.setValue(fMsg.custDsBankAcctNum_B, (String) result.get("MASK_BANK_ACCT_NUM_BM"));
                // END 2023/07/03 S.Nakatani [QC#55645, MOD]
                ZYPEZDItemValueSetter.setValue(fMsg.payerCustCd_B, (String) result.get("PAYER_CUST_CD_B"));
                ZYPEZDItemValueSetter.setValue(fMsg.custInvNum_B, (String) result.get("CUST_INV_NUM_B"));
                ZYPEZDItemValueSetter.setValue(fMsg.custInvAmt_B, (BigDecimal) result.get("CUST_INV_AMT_B"));

                csvOutFile.write();
                rowCnt++;
            }

        } else {
            bizMsg.setMessageInfo(NFCL0510Constant.ZZZM9005W);
        }
        csvOutFile.close();

        EZDDebugOutput.println(1, "doProcess_NFCL0510_Download================================END", this);
    }
    // END   2018/06/11 [QC#19723, ADD]
}
