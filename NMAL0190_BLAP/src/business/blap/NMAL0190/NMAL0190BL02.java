/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL0190;

import static business.blap.NMAL0190.constant.NMAL0190Constant.COL_SUPD_FROM_MDSE_CD;
import static business.blap.NMAL0190.constant.NMAL0190Constant.CSV_FILE_EXTENSION;
import static business.blap.NMAL0190.constant.NMAL0190Constant.CSV_FILE_NAME;
import static business.blap.NMAL0190.constant.NMAL0190Constant.NMAM0007I;
import static business.blap.NMAL0190.constant.NMAL0190Constant.NZZM0001W;

import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL0190.common.NMAL0190CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPRecHistUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NMAL0190BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/07   Fujitsu         T.Arima         Create          N/A
 * 2016/03/02   CITS            S.Tanikawa      UPDATE          QC#2669
 *</pre>
 */
public class NMAL0190BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL0190CMsg bizMsg = (NMAL0190CMsg) cMsg;
            NMAL0190SMsg glblMsg = (NMAL0190SMsg) sMsg;

            if ("NMAL0190_INIT".equals(screenAplID)) {
                doProcess_NMAL0190_INIT(bizMsg, glblMsg);

            } else if ("NMAL0190Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NMAL0190Scrn00_CMN_Download(bizMsg, glblMsg);

            } else if ("NMAL0190Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL0190Scrn00_Clear(bizMsg, glblMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     */
    private void doProcess_NMAL0190_INIT(NMAL0190CMsg bizMsg, NMAL0190SMsg glblMsg) {
        search(bizMsg, glblMsg);
    }

    /**
     * CLEAR Event
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     */
    private void doProcess_NMAL0190Scrn00_Clear(NMAL0190CMsg bizMsg, NMAL0190SMsg glblMsg) {
        search(bizMsg, glblMsg);
    }

    /**
     * Init Search
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     */
    private void search(NMAL0190CMsg bizMsg, NMAL0190SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(bizMsg.B);

        // UPDATE START 2016/03/02 QC#2669
        ZYPTableUtil.clear(glblMsg.A);
        // get key record from SUPD_RELN_STAGE
        // S21SsmEZDResult ssmResult = NMAL0190Query.getInstance().search(bizMsg, glblMsg);
        S21SsmEZDResult ssmResultKeyRec = NMAL0190Query.getInstance().searchKeyRec(bizMsg, glblMsg);

        // if (ssmResult.isCodeNotFound()) {
        if (ssmResultKeyRec.isCodeNotFound()) {

            bizMsg.setMessageInfo(NMAM0007I);
            return;

        } else if (ssmResultKeyRec.isCodeNormal()) {
            Map<String, String> keyMap = (Map<String, String>) ssmResultKeyRec.getResultObject();

            int indx = 0;
            NMAL0190CommonLogic.makeHdrInfo(glblMsg, keyMap, indx);
            // only first row, relnMdseCd = SUPD_RELN_STAGE.supdFromMdseCd
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(indx).relnMdseCd, keyMap.get(COL_SUPD_FROM_MDSE_CD));
            // Add Start 2016/11/10 QC#2872
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(indx).xxRecHistCratTs_A1, keyMap.get("XX_REC_HIST_CRAT_TS"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(indx).xxRecHistCratByNm_A1, ZYPRecHistUtil.getFullNameForRecHist(keyMap.get("XX_REC_HIST_CRAT_BY_NM")));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(indx).xxRecHistUpdTs_A1, keyMap.get("XX_REC_HIST_UPD_TS"));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(indx).xxRecHistUpdByNm_A1, ZYPRecHistUtil.getFullNameForRecHist(keyMap.get("XX_REC_HIST_UPD_BY_NM")));
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(indx).xxRecHistTblNm_A1, keyMap.get("XX_REC_HIST_TBL_NM"));
            // Add End 2016/11/10 QC#2872
            NMAL0190CommonLogic.getMdseCompatibleInfo(glblMsg, indx);

            String sFromMdseCd = keyMap.get(COL_SUPD_FROM_MDSE_CD);
            // get supersedes relationship from SUPD_RELN
            S21SsmEZDResult ssmResultSupdReln = NMAL0190Query.getInstance().getSupdReln(sFromMdseCd);

            // make second data row
            if (ssmResultSupdReln.isCodeNormal()) {
                List<Map<String, String>> supdRelnList = (List<Map<String, String>>) ssmResultSupdReln.getResultObject();
                if (supdRelnList != null && supdRelnList.size() > 0) {
                    for (Map<String, String> supdRelnMap : supdRelnList) {

                        indx++;

                        NMAL0190CommonLogic.makeHdrInfo(glblMsg, keyMap, indx);
                        // from second row, relnMdseCd = SUPD_RELN.supdFromMdseCd
                        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(indx).relnMdseCd, supdRelnMap.get(COL_SUPD_FROM_MDSE_CD));
                        // Add Start 2016/11/10 QC#2872
                        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(indx).xxRecHistCratTs_A1, supdRelnMap.get("XX_REC_HIST_CRAT_TS"));
                        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(indx).xxRecHistCratByNm_A1, ZYPRecHistUtil.getFullNameForRecHist(supdRelnMap.get("XX_REC_HIST_CRAT_BY_NM")));
                        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(indx).xxRecHistUpdTs_A1, supdRelnMap.get("XX_REC_HIST_UPD_TS"));
                        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(indx).xxRecHistUpdByNm_A1, ZYPRecHistUtil.getFullNameForRecHist(supdRelnMap.get("XX_REC_HIST_UPD_BY_NM")));
                        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(indx).xxRecHistTblNm_A1, supdRelnMap.get("XX_REC_HIST_TBL_NM"));
                        // Add End 2016/11/10 QC#2872

                        NMAL0190CommonLogic.getMdseCompatibleInfo(glblMsg, indx);
                    }
                }
            }
            // glblMsg.A.setValidCount(ssmResult.getQueryResultCount());
            glblMsg.A.setValidCount(indx + 1);

        }
        // UPDATE END  2016/03/02 QC#2669

        if (glblMsg.A.getValidCount() > glblMsg.A.length() - 1) {
            bizMsg.setMessageInfo(NZZM0001W);
            glblMsg.A.setValidCount(glblMsg.A.length() - 1);
        }
        NMAL0190CommonLogic.copyBizMsgFromGlblMsg(bizMsg, glblMsg);
    }

    /**
     * CMN_Download Event
     * @param bizMsg Business Message
     * @param glblMsg Global Message
     */
    private void doProcess_NMAL0190Scrn00_CMN_Download(NMAL0190CMsg bizMsg, NMAL0190SMsg glblMsg) {

        glblMsg.O.clear();

        // build Supersession
        NMAL0190CommonLogic.buildSupdHdr(glblMsg);
        NMAL0190Query.getInstance().buidlSupd(bizMsg, glblMsg);

        // build Forward
        NMAL0190Query.getInstance().buildForward(bizMsg, glblMsg);

        // build Backward
        NMAL0190Query.getInstance().buildBackward(bizMsg, glblMsg);
        NMAL0190CommonLogic.buildSupdBtm(glblMsg);

        // build Old Revision
        NMAL0190CommonLogic.buildOldRevHdr(glblMsg);
        NMAL0190Query.getInstance().buildOldRevision(bizMsg, glblMsg);

        // build New Revision
        NMAL0190CommonLogic.buildNewRevHdr(glblMsg);
        NMAL0190Query.getInstance().buildNewRevision(bizMsg, glblMsg);

        // create csv file
        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), CSV_FILE_EXTENSION);
        // Write Csv Data from glblMsg.O
        NMAL0190CommonLogic.writeCsvMsg(bizMsg.xxFileData.getTempFilePath(), glblMsg);

    }

}
