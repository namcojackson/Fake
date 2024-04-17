/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLAL2030;

import static business.blap.NLAL2030.constant.NLAL2030Constant.NLZM1041E;
import static business.blap.NLAL2030.constant.NLAL2030Constant.NLZM1042E;

import java.io.File;
import java.util.HashSet;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLAL2030.common.NLAL2030CommonLogic;
import business.db.RWS_HDRTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileNameUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileWriter;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.printing.common.S21EIPPrintingService;
import com.canon.cusa.s21.framework.printing.common.S21ReportRequestBean;
import com.canon.cusa.s21.framework.printing.eip.S21InputParameter;

/**
 *<pre>
 * NLAL2030BL09
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/04   Fujitsu         Y.Taoka         Create          N/A
 * 05/09/2017   CITS           T.Tokutomi       Update          Merge DS
 *</pre>
 */
/**
 * @author q09447
 */
/**
 * @author q09447
 */
public class NLAL2030BL09 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            NLAL2030CMsg bizMsg = (NLAL2030CMsg) cMsg;
            NLAL2030SMsg glblMsg = (NLAL2030SMsg) sMsg;

            if ("NLAL2030Scrn00_Print".equals(screenAplID)) {
                doProcess_NLAL2030Scrn00_Print(bizMsg, glblMsg);

            } else {
                return;
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Print Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NLAL2030Scrn00_Print(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {

        NLAL2030CommonLogic.updateGlblMsg(bizMsg, glblMsg);

        // Input Check
        if (!NLAL2030CommonLogic.checkInputRWSPrint(bizMsg, glblMsg)) {

            return;
        }

        print(bizMsg, glblMsg);

        updateRwsHdrPringFlg(bizMsg, glblMsg);
    }

    /**
     * print
     * @param bizMsg NLAL2030CMsg
     * @param glblMsg NLAL2030SMsg
     */
    private void print(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {
        S21EIPPrintingService service = new S21EIPPrintingService();

        S21ReportRequestBean request = null;
        S21InputParameter inputParam = null;
        String userId = getContextUserInfo().getUserId();

        request = new S21ReportRequestBean("NLAF0010");
        request.setRptFileTpCd(S21ReportRequestBean.OUTPUT_FORMAT_TYPE_PDF);
        request.setRptArcFlg(false);
        request.setRptTtlNm("RWS Report");

        inputParam = request.getInputParamBeanInstance();

        inputParam.addReportParameter("GLBL_CMPY_CD", bizMsg.glblCmpyCd.getValue());
        inputParam.addReportParameter("USR_ID", userId);
        inputParam.addReportParameter("PROC_START_TS", bizMsg.xxRqstTs.getValue());
        inputParam.addReportParameter("INTL_LANG_VAL_COL_NM", inputParam.getSystemDefaultLanguage());

        request.setInputParamBean(inputParam);

        byte[] pdf = service.onlineReport(request);

        if (pdf == null) {
            bizMsg.setMessageInfo(NLZM1041E);
            return;
        }

        String fileNm = ZYPFileNameUtil.createUniqueFileNm(File.separator + "RWS", userId);
        bizMsg.xxFileData.setTempFilePath(null, fileNm, ".pdf");
        ZYPFileWriter.writeFile(bizMsg.xxFileData.getTempFilePath(), pdf);
    }

    /**
     * updateRwsHdrPringFlg
     * @param bizMsg NLAL2030CMsg
     * @param glblMsg NLAL2030SMsg
     */
    private void updateRwsHdrPringFlg(NLAL2030CMsg bizMsg, NLAL2030SMsg glblMsg) {

        HashSet<String> rwsNumSet = new HashSet<String>();
        String glblCmpyCd = getGlobalCompanyCode();

        for (int i = 0; i < glblMsg.B.getValidCount(); i++) {

            NLAL2030_BSMsg lineMsg = glblMsg.B.no(i);

            if (ZYPConstant.CHKBOX_ON_Y.equals(lineMsg.xxChkBox_B1.getValue()) //
                    && !rwsNumSet.contains(lineMsg.rwsNum_B1.getValue())) {

                RWS_HDRTMsg rwsHdrTMsg = new RWS_HDRTMsg();
                ZYPEZDItemValueSetter.setValue(rwsHdrTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(rwsHdrTMsg.rwsNum, lineMsg.rwsNum_B1);

                rwsHdrTMsg = (RWS_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(rwsHdrTMsg);

                if (rwsHdrTMsg != null //
                        && EZDTBLAccessor.RTNCD_NORMAL.equals(rwsHdrTMsg.getReturnCode())) {
                    rwsHdrTMsg.rwsPrtFlg.setValue(ZYPConstant.FLG_ON_Y);
                    EZDTBLAccessor.update(rwsHdrTMsg);

                    rwsNumSet.add(lineMsg.rwsNum_B1.getValue());
                } else {
                    bizMsg.setMessageInfo(NLZM1042E);
                    return;
                }
            }
        }
    }
}
