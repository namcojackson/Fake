/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0960;

import static business.blap.NSAL0960.constant.NSAL0960Constant.DEFALT_FROM_DATE;
import static business.blap.NSAL0960.constant.NSAL0960Constant.DEFALT_TO_DATE;
import static business.blap.NSAL0960.constant.NSAL0960Constant.NSAM0035E;
import static business.blap.NSAL0960.constant.NSAL0960Constant.NSAM0456E;
import static business.blap.NSAL0960.constant.NSAL0960Constant.NSAM0457E;
import static business.blap.NSAL0960.constant.NSAL0960Constant.ZZM9000E;
import static business.blap.NSAL0960.constant.NSAL0960Constant.ZZZM9003I;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.common.EZDCDateItem;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0960.common.NSAL0960CommonLogic;
import business.db.DS_CONTR_VLD_LISTTMsg;
import business.db.DS_CONTR_VLD_LISTTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Contract Validation List Setup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/21   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2016/06/16   Hitachi         M.Gotou         Update          QC#9698
 *</pre>
 */
public class NSAL0960BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL0960Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0960Scrn00_CMN_Submit((NSAL0960CMsg) cMsg, (NSAL0960SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

  /**
     * do process (Submit)
     * @param cMsg NSAL0960CMsg
     * @param sMsg NSAL0960SMsg
     */
    private void doProcess_NSAL0960Scrn00_CMN_Submit(NSAL0960CMsg cMsg, NSAL0960SMsg sMsg) {
        if (cMsg.A.getValidCount() == 0) {
            cMsg.setMessageInfo(NSAM0456E);
            return;
        }
        if (isDuplicatedDsContrVldListNm(cMsg)) {
            cMsg.dsContrVldListNm_H.setErrorInfo(1, NSAM0035E, new String[] {"List Name" });
            return;
        }
        if (isDuplicatedSeq(cMsg)) {
            return;
        }
        if (isMandatoryDsContrVldPk(cMsg)) {
            return;
        }
        if (isDuplicatedDsContrVldPk(cMsg)) {
            return;
        }
        // Check date
        if (isCheckDate(cMsg)) {
            return;
        }
        if (isRelationCheckDate(cMsg)) {
            return;
        }
        // add start 2016/06/16 CSA Defect#9698
        if (!chkDuplicatedDsContrVldListPeriod(cMsg)) {
            return;
        }
        // add end 2016/06/16 CSA Defect#9698

        NSAL0960CommonLogic.submitDsContrVldList(cMsg);
        NSAL0960CommonLogic.submitDsContrVldReln(cMsg);
        if (!hasValue(cMsg.getMessageCode())) {
            cMsg.setMessageInfo(ZZZM9003I, new String[]{"Submit"});
            return;
        }
    }

    private boolean isDuplicatedDsContrVldListNm(NSAL0960CMsg cMsg) {
        if (cMsg == null) {
            return true;
        }
        if (hasValue(cMsg.dsContrVldListPk_H)) {
            return false;
        }
        DS_CONTR_VLD_LISTTMsg inMsg = new DS_CONTR_VLD_LISTTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
        inMsg.setConditionValue("dsContrVldListNm01", cMsg.dsContrVldListNm_H.getValue());
        DS_CONTR_VLD_LISTTMsgArray list = (DS_CONTR_VLD_LISTTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (list.getValidCount() > 0) {
            DS_CONTR_VLD_LISTTMsg tMsg = (DS_CONTR_VLD_LISTTMsg) list.get(0);
            if (tMsg != null) {
                return true;
            }
        }
        return false;
    }

    private boolean isMandatoryDsContrVldPk(NSAL0960CMsg cMsg) {
        boolean errorFlg = false;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            BigDecimal dsContrVldPk = cMsg.A.no(i).dsContrVldPk_A.getValue();
            if (!hasValue(dsContrVldPk)) {
                cMsg.A.no(i).dsContrVldCatgDescTxt_A.setErrorInfo(1, ZZM9000E, new String[] {"Category" });
                errorFlg = true;
            }
        }
        return errorFlg;
    }

    private boolean isDuplicatedSeq(NSAL0960CMsg cMsg) {
        if (cMsg == null) {
            return true;
        }

        Set<BigDecimal> dupliSortNum = new HashSet<BigDecimal>();
        Set<BigDecimal> dupliCheckSortNum = new HashSet<BigDecimal>();
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            BigDecimal nowSortNum = cMsg.A.no(i).vldSortNum_A.getValue();
            if (dupliCheckSortNum.contains(nowSortNum)) {
                dupliSortNum.add(nowSortNum);
            } else {
                dupliCheckSortNum.add(nowSortNum);
            }
        }
        boolean errorFlg = false;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            BigDecimal nowSortNum = cMsg.A.no(i).vldSortNum_A.getValue();
            if (dupliSortNum.contains(nowSortNum)) {
                cMsg.A.no(i).vldSortNum_A.setErrorInfo(1, NSAM0035E, new String[] {"Seq" });
                errorFlg = true;
            }
        }
        return errorFlg;
    }

    private boolean isDuplicatedDsContrVldPk(NSAL0960CMsg cMsg) {
        if (cMsg == null) {
            return true;
        }
        Set<BigDecimal> dupliDsContrVldPk = new HashSet<BigDecimal>();
        Set<BigDecimal> dupliCheckDsContrVldPk = new HashSet<BigDecimal>();
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            BigDecimal nowDsContrVldPk = cMsg.A.no(i).dsContrVldPk_A.getValue();
            if (dupliCheckDsContrVldPk.contains(nowDsContrVldPk)) {
                dupliDsContrVldPk.add(nowDsContrVldPk);
            } else {
                dupliCheckDsContrVldPk.add(nowDsContrVldPk);
            }
        }
        boolean errorFlg = false;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            BigDecimal nowDsContrVldPk = cMsg.A.no(i).dsContrVldPk_A.getValue();
            if (dupliDsContrVldPk.contains(nowDsContrVldPk)) {
                cMsg.A.no(i).dsContrVldCatgDescTxt_A.setErrorInfo(1, NSAM0035E, new String[] {"Category" });
                errorFlg = true;
            }
        }
        return errorFlg;
    }

    private boolean isCheckDate(NSAL0960CMsg cMsg) {
        boolean errorFlg = false;

        EZDCDateItem effFromDt = cMsg.effFromDt_H;
        EZDCDateItem effToDt = cMsg.effToDt_H;
        if (hasValue(effFromDt) && hasValue(effToDt)) {
            int compareRes = ZYPDateUtil.compare(effFromDt.getValue(), effToDt.getValue());
            if (compareRes == 1) {
                String[] msgParam = {"End Date", "Start Date" };
                effFromDt.setErrorInfo(1, NSAM0457E, msgParam);
                effToDt.setErrorInfo(1, NSAM0457E, msgParam);
                errorFlg = true;
            }
        }
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            EZDCDateItem effFromDtA = cMsg.A.no(i).effFromDt_A;
            EZDCDateItem effToDtA = cMsg.A.no(i).effToDt_A;
            if (hasValue(effFromDtA) && hasValue(effToDtA)) {
                int compareRes = ZYPDateUtil.compare(effFromDtA.getValue(), effToDtA.getValue());
                if (compareRes == 1) {
                    String[] msgParam = {"End Date", "Start Date" };
                    effFromDtA.setErrorInfo(1, NSAM0457E, msgParam);
                    effToDtA.setErrorInfo(1, NSAM0457E, msgParam);
                    errorFlg = true;
                }
            }
        }
        return errorFlg;
    }

    private boolean isRelationCheckDate(NSAL0960CMsg cMsg) {
        boolean errorFlg = false;

        EZDCDateItem effFromDt = cMsg.effFromDt_H;
        EZDCDateItem effToDt = cMsg.effToDt_H;
        String effFromDtHeader = cMsg.effFromDt_H.getValue();
        String effToDtHeader = cMsg.effToDt_H.getValue();

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            EZDCDateItem effFromDtA = cMsg.A.no(i).effFromDt_A;
            EZDCDateItem effToDtA = cMsg.A.no(i).effToDt_A;
            String effFromDtList = effFromDtA.getValue();
            String effToDtList = effToDtA.getValue();

            if (hasValue(effToDtHeader)) {
                if (!hasValue(effToDtList)) {
                    effToDtList = DEFALT_TO_DATE;
                }
                int compareResTo = ZYPDateUtil.compare(effToDtList, effToDtHeader);
                if (compareResTo == 1) {
                    String[] msgParam = {"Validation End Date", "List End Date" };
                    effToDt.setErrorInfo(1, NSAM0457E, msgParam);
                    effToDtA.setErrorInfo(1, NSAM0457E, msgParam);
                    errorFlg = true;
                }
            }

            if (hasValue(effFromDtHeader)) {
                if (!hasValue(effFromDtList)) {
                    effFromDtList = DEFALT_FROM_DATE;
                }
                int compareResFrom = ZYPDateUtil.compare(effFromDtHeader, effFromDtList);
                if (compareResFrom == 1) {
                    String[] msgParam = {"Validation Start Date", "List Start Date" };
                    effFromDt.setErrorInfo(1, NSAM0457E, msgParam);
                    effFromDtA.setErrorInfo(1, NSAM0457E, msgParam);
                    errorFlg = true;
                }
            }
        }
        return errorFlg;
    }

    // add start 2016/06/16 CSA Defect#9698
    private boolean chkDuplicatedDsContrVldListPeriod(NSAL0960CMsg cMsg) {

        String effFromDtHeader = cMsg.effFromDt_H.getValue();
        if (!hasValue(effFromDtHeader)) {
            effFromDtHeader = DEFALT_FROM_DATE;
        }
        String effToDtHeader = cMsg.effToDt_H.getValue();
        if (!hasValue(effToDtHeader)) {
            effToDtHeader = DEFALT_TO_DATE;
        }

        S21SsmEZDResult ssmResult = NSAL0960Query.getInstance().getDsContrVldListPeriod(cMsg);
        if (ssmResult.getQueryResultCount() != 0) {
            List<Map<String, Object>> list = (List<Map<String, Object>>) ssmResult.getResultObject();
            for (int i = 0; i < ssmResult.getQueryResultCount(); i++) {
                BigDecimal dsContrVldListPk = (BigDecimal) list.get(i).get("DS_CONTR_VLD_LIST_PK");
                if (dsContrVldListPk.equals(cMsg.dsContrVldListPk_H.getValue())) {
                    continue;
                }
                String effFromDt = (String) list.get(i).get("EFF_FROM_DT");
                if (!hasValue(effFromDt)) {
                    effFromDt = DEFALT_FROM_DATE;
                }
                String effToDt = (String) list.get(i).get("EFF_TO_DT");
                if (!hasValue(effToDt)) {
                    effToDt = DEFALT_TO_DATE;
                }
                if ((ZYPDateUtil.compare(effToDt, effFromDtHeader) >= 0)
                        && (ZYPDateUtil.compare(effToDtHeader, effFromDt) >= 0)) {
                    cMsg.effFromDt_H.setErrorInfo(1, NSAM0035E, new String[] {"Period that you entered"});
                    cMsg.effToDt_H.setErrorInfo(1, NSAM0035E, new String[] {"Period that you entered"});
                    return false;
                }
            }
        }

        return true;
    }
    // add end 2016/06/16 CSA Defect#9698

}
