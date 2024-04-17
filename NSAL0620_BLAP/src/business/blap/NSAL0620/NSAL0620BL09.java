/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0620;

import static business.blap.NSAL0620.constant.NSAL0620Constant.RPT_ID;
import static business.blap.NSAL0620.constant.NSAL0620Constant.RPT_NM_SUFIX;
import static business.blap.NSAL0620.constant.NSAL0620Constant.STR_SPACE;
import static business.blap.NSAL0620.constant.NSAL0620Constant.APL_ID;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL0620.constant.NSAL0620Constant.MSG_ID;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileWriter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.printing.common.S21EIPPrintingService;
import com.canon.cusa.s21.framework.printing.common.S21ReportRequestBean;
import com.canon.cusa.s21.framework.printing.eip.S21InputParameter;

/**
 *<pre>
 * Contract Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/21   Hitachi         E.Kameishi      Create          QC#8661
 *</pre>
 */
public class NSAL0620BL09 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        String screenAplId = cMsg.getScreenAplID();

        try {

            if (screenAplId.startsWith("NSAL0620Scrn00")) {
                if (screenAplId.endsWith("Print")) {
                    doProcess_NSAL0620_Print((NSAL0620CMsg) cMsg, (NSAL0620SMsg) sMsg);
                }
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplId);
            }
            return;

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }

    }

    private void doProcess_NSAL0620_Print(NSAL0620CMsg cMsg, NSAL0620SMsg sMsg) {

        List<BigDecimal> dsContrPkList = new ArrayList<BigDecimal>();
        new ArrayList<Map<String, Object>>();

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).xxChkBox_A.getValue())) {
                dsContrPkList.add(sMsg.A.no(i).dsContrPk_A.getValue());
            }
        }

        S21SsmEZDResult ssmResult = NSAL0620Query.getInstance().getPrintData(dsContrPkList);

        if (!ssmResult.isCodeNormal()) {
            return;
        }

        List<Map<String, Object>> contrInfoList = (List<Map<String, Object>>) ssmResult.getResultObject();

        List<S21ReportRequestBean> requestList = new ArrayList<S21ReportRequestBean>();

        BigDecimal preDsContrPk = BigDecimal.ZERO;
        S21ReportRequestBean requestBean = null;
        S21InputParameter param = null;
        S21EIPPrintingService service =  new S21EIPPrintingService();
        String systemDt = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmss");

        for (Map<String, Object> contrInfo : contrInfoList) {
            BigDecimal dsContrPk = (BigDecimal) contrInfo.get("DS_CONTR_PK");
            String shipToCustCd = (String) contrInfo.get("SHIP_TO_CUST_CD");
            String dsContrNum = (String) contrInfo.get("DS_CONTR_NUM");

            requestBean = new S21ReportRequestBean(RPT_ID);
            requestBean.setRptFileTpCd(S21ReportRequestBean.OUTPUT_FORMAT_TYPE_PDF);
            requestBean.setRptArcFlg(true);

            if (preDsContrPk.compareTo(dsContrPk) != 0) {
                requestBean.setRptTtlNm(RPT_NM_SUFIX + dsContrNum + STR_SPACE + systemDt);
            }

            param = requestBean.getInputParamBeanInstance();
            param.addReportParameter("INTL_LANG_VAL_COL_NM", param.getSystemDefaultLanguage());
            param.addReportParameter("GLBL_CMPY_CD", cMsg.glblCmpyCd.getValue());
            param.addReportParameter("DS_CONTR_PK", dsContrPk);
            param.addReportParameter("BIZ_APP_ID", APL_ID);
            param.addReportParameter("SHIP_TO_CUST_CD", shipToCustCd);

            requestBean.setInputParamBean(param);
            requestList.add(requestBean);

            preDsContrPk = dsContrPk;
        }

        try{
            byte[] pdf = service.onlineMergeReports(requestList);
            if (pdf != null) {
                StringBuilder fileName = new StringBuilder();
                fileName.append(String.valueOf(System.currentTimeMillis()));
                cMsg.xxFileData.setTempFilePath(null, fileName.toString(), ".pdf");
                ZYPFileWriter.writeFile(cMsg.xxFileData.getTempFilePath(), pdf);
                cMsg.setMessageInfo(MSG_ID.ZZZM9003I.toString(), new String[] {"Print"});
            } else {
              throw new S21AbendException("get report bytes failure");
            }
        } catch (S21AbendException e) {
              cMsg.setMessageInfo(MSG_ID.ZZXM0001E.toString(), new String[] {e.getMessage() });
        }
    }
}
