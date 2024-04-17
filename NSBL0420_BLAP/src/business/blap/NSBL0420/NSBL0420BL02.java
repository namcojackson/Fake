/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0420;

import static business.blap.NSBL0420.constant.NSBL0420Constant.NSBM0057E;
import static business.blap.NSBL0420.constant.NSBL0420Constant.NZZM0001W;
import static business.blap.NSBL0420.constant.NSBL0420Constant.NSBM0005I;
import static business.blap.NSBL0420.constant.NSBL0420Constant.ZZZM9007E;
import static business.blap.NSBL0420.constant.NSBL0420Constant.NSBM0016E;
import static business.blap.NSBL0420.constant.NSBL0420Constant.MODE_CODE;
import static business.blap.NSBL0420.constant.NSBL0420Constant.MDSE_CODE;
import static business.blap.NSBL0420.constant.NSBL0420Constant.NZZM0012E;
import static business.blap.NSBL0420.constant.NSBL0420Constant.NZZM0000E;
import static business.blap.NSBL0420.constant.NSBL0420Constant.SVC_MOD_DTL_PK;
import static business.blap.NSBL0420.constant.NSBL0420Constant.NSBM0042E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;

import business.blap.NSBL0420.common.NSBL0420CommonLogic;
import business.db.ALL_MDSE_VTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
/**
 *<pre>
 * Mods Parts Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/18   Hitachi         T.Tsuchida      Create          N/A
 * 2016/07/13   Hitachi         O.Okuma         Update          QC#11672
 *</pre>
 */
public class NSBL0420BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSBL0420CMsg cMsg = (NSBL0420CMsg) arg0;
        NSBL0420SMsg sMsg = (NSBL0420SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSBL0420_INIT".equals(screenAplID)) {
                doProcess_NSBL0420_INIT(cMsg, sMsg);
            } else if ("NSBL0420Scrn00_Add".equals(screenAplID)) {
                doProcess_NSBL0420Scrn00_Add(cMsg, sMsg);
            } else if ("NSBL0420Scrn00_Delete".equals(screenAplID)) {
                doProcess_NSBL0420Scrn00_Delete(cMsg, sMsg);
            } else if ("NSBL0420Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSBL0420Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSBL0420Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSBL0420Scrn00_PagePrev(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSBL0420_INIT(NSBL0420CMsg cMsg, NSBL0420SMsg sMsg) {
        init(cMsg, sMsg);
    }

    private void doProcess_NSBL0420Scrn00_Add(NSBL0420CMsg cMsg, NSBL0420SMsg sMsg) {
        addNewMdseCdRow(cMsg, sMsg);
    }

    private void doProcess_NSBL0420Scrn00_Delete(NSBL0420CMsg cMsg, NSBL0420SMsg sMsg) {
        // set Row Number
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            setValue(cMsg.A.no(i).xxRowNum_A, BigDecimal.valueOf(i + 1));
        }

        NSBL0420CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        NSBL0420_ACMsgArray acMsgArray = cMsg.A;
        ZYPTableUtil.clear(acMsgArray);

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(sMsg.A, "xxChkBox_A", ZYPConstant.CHKBOX_ON_Y);
        if (selectedRows.isEmpty()) {
            cMsg.setMessageInfo(NSBM0042E);
        } else {
            ZYPTableUtil.deleteRows(sMsg.A, selectedRows);
        }

        int pageFrom;
        int pageFromTo;
        if (sMsg.A.getValidCount() != 0) {
            if (((sMsg.A.getValidCount()) % (cMsg.A.length())) == 0 && cMsg.xxPageShowFromNum.getValueInt() > sMsg.A.getValidCount()) {
                pageFrom = cMsg.xxPageShowFromNum.getValueInt() - cMsg.A.length() - 1;
            } else {
                pageFrom = cMsg.xxPageShowFromNum.getValueInt() - 1;
            }
            int i = pageFrom;
            for (; i < pageFrom + acMsgArray.length(); i++) {
                if (i < sMsg.A.getValidCount()) {
                    EZDMsg.copy(sMsg.A.no(i), null, acMsgArray.no(i - pageFrom), null);
                } else {
                    break;
                }
            }
            acMsgArray.setValidCount(i - pageFrom);
            pageFrom = pageFrom + 1;
            pageFromTo = acMsgArray.getValidCount() - 1;
        } else {
            pageFrom = 0;
            pageFromTo = 0;
        }
        cMsg.xxPageShowFromNum.setValue(pageFrom);
        cMsg.xxPageShowToNum.setValue(pageFrom + pageFromTo);
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
    }

    private void doProcess_NSBL0420Scrn00_PageNext(NSBL0420CMsg cMsg, NSBL0420SMsg sMsg) {
        // set Row Number
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            setValue(cMsg.A.no(i).xxRowNum_A, BigDecimal.valueOf(i + 1));
        }

        NSBL0420CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        NSBL0420_ACMsgArray acMsgArray = cMsg.A;
        ZYPTableUtil.clear(acMsgArray);

        int pageTo = cMsg.xxPageShowToNum.getValueInt();
        int i = pageTo;
        for (; i < pageTo + acMsgArray.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, acMsgArray.no(i - pageTo), null);
            } else {
                break;
            }
        }
        acMsgArray.setValidCount(i - pageTo);

        cMsg.xxPageShowFromNum.setValue(pageTo + 1);
        cMsg.xxPageShowToNum.setValue(pageTo + acMsgArray.getValidCount());
    }

    private void doProcess_NSBL0420Scrn00_PagePrev(NSBL0420CMsg cMsg, NSBL0420SMsg sMsg) {
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            setValue(cMsg.A.no(i).xxRowNum_A, BigDecimal.valueOf(i + 1));
        }

        NSBL0420CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        NSBL0420_ACMsgArray acMsgArray = cMsg.A;
        ZYPTableUtil.clear(acMsgArray);

        int pageFrom = cMsg.xxPageShowFromNum.getValueInt() - acMsgArray.length() - 1;
        int i = pageFrom;
        for (; i < pageFrom + acMsgArray.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, acMsgArray.no(i - pageFrom), null);
            } else {
                break;
            }
        }
        acMsgArray.setValidCount(i - pageFrom);

        pageFrom = pageFrom + 1;
        cMsg.xxPageShowFromNum.setValue(pageFrom);
        cMsg.xxPageShowToNum.setValue(pageFrom + acMsgArray.getValidCount() - 1);
    }

    private void init(NSBL0420CMsg cMsg, NSBL0420SMsg sMsg) {
        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);
        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        // add start 2016/07/13 CSA Defect#11672
        setValue(cMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
        // add end 2016/07/13 CSA Defect#11672
        if (!hasValue(cMsg.xxModeCd)) {
            cMsg.setMessageInfo(NZZM0012E, new String[]{MODE_CODE});
            return;
        }
        if (!hasValue(cMsg.svcModDtlPk)) {
            cMsg.setMessageInfo(NZZM0012E, new String[]{SVC_MOD_DTL_PK});
            return;
        }
        findDtlItemInfo(cMsg, sMsg);
        // set Paging Data
        cMsg.xxPageShowFromNum.setValue(1);
        cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
        // set Row Number
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            setValue(cMsg.A.no(i).xxRowNum_A, BigDecimal.valueOf(i + 1));
        }
    }

    private void findDtlItemInfo(NSBL0420CMsg cMsg, NSBL0420SMsg sMsg) {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("glblCmpyCd", cMsg.glblCmpyCd);
        queryMap.put("svcModDtlPk", cMsg.svcModDtlPk.getValue());
        queryMap.put("limitCnt", sMsg.A.length() + 1);
        S21SsmEZDResult ssmResult = NSBL0420Query.getInstance().getDtlItemInfo(queryMap, sMsg.A);

        if (ssmResult.isCodeNormal() || ssmResult.isCodeNotFound()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
                queryResCnt = sMsg.A.length();
            }

            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            cMsg.setMessageInfo(NSBM0005I);
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(queryResCnt);
        } else {
            cMsg.setMessageInfo(NZZM0000E);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
        }
    }

    private void addNewMdseCdRow(NSBL0420CMsg cMsg, NSBL0420SMsg sMsg) {
        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        if (!hasValue(cMsg.mdseCd_TF)) {
            cMsg.mdseCd_TF.setErrorInfo(1, ZZZM9007E, new String[] {MDSE_CODE });
            return;
        }
        int countSMsg = sMsg.A.getValidCount();
        if (sMsg.A.length() < countSMsg + 1) {
            cMsg.setMessageInfo(NSBM0057E);
            return;
        }
        ALL_MDSE_VTMsgArray result = NSBL0420Query.getALL_MDSE_V(cMsg.glblCmpyCd.getValue(), cMsg.mdseCd_TF.getValue());

        if (result.getValidCount() == 0) {
            cMsg.mdseCd_TF.setErrorInfo(1, NZZM0000E);
            return;
        }
        int i = 0;
        for (; i < countSMsg; i++) {
            if (cMsg.mdseCd_TF.getValue().equals(sMsg.A.no(i).mdseCd_A.getValue())) {
                cMsg.mdseCd_TF.setErrorInfo(1, NSBM0016E, new String[] {MDSE_CODE });
                return;
            }
        }

        sMsg.A.setValidCount(countSMsg + 1);
        setValue(sMsg.A.no(countSMsg).mdseCd_A, result.no(0).mdseCd);
        setValue(sMsg.A.no(countSMsg).mdseDescShortTxt_A, result.no(0).mdseDescShortTxt);

        if (countSMsg == 0) {
            setValue(cMsg.xxPageShowFromNum, BigDecimal.ONE);
        }

        int countCMsg = cMsg.A.getValidCount();
        if (countCMsg < cMsg.A.length()) {
            cMsg.A.setValidCount(countCMsg + 1);
            setValue(cMsg.A.no(countCMsg).mdseCd_A, result.no(0).mdseCd);
            setValue(cMsg.A.no(countCMsg).mdseDescShortTxt_A, result.no(0).mdseDescShortTxt);
        }

        for (int h = 0; h < cMsg.A.getValidCount(); h++) {
            setValue(cMsg.A.no(h).xxRowNum_A, BigDecimal.valueOf(h + 1));
        }
        NSBL0420CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        cMsg.mdseCd_TF.clear();

        int pagenationFrom;
        if (sMsg.A.getValidCount() < cMsg.A.length() + 1) {
            pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
            int j = 0;
            for (; j < sMsg.A.getValidCount(); j++) {
                if (j < sMsg.A.getValidCount()) {
                    EZDMsg.copy(sMsg.A.no(j), null, cMsg.A.no(j), null);
                } else {
                    break;
                }
            }
            cMsg.A.setValidCount(j);
            cMsg.xxPageShowFromNum.setValue(pagenationFrom);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());

        } else {
            int pageNum = (sMsg.A.getValidCount() - 1) / cMsg.A.length();
            pagenationFrom = pageNum * cMsg.A.length();
            ZYPTableUtil.clear(cMsg.A);
            int j = pagenationFrom; //100
            for (; j < sMsg.A.getValidCount(); j++) {
                if (j < sMsg.A.getValidCount()) {
                    EZDMsg.copy(sMsg.A.no(j), null, cMsg.A.no(j - pagenationFrom), null);
                } else {
                    break;
                }
            }
            cMsg.A.setValidCount(j - pagenationFrom);
            cMsg.xxPageShowFromNum.setValue(pagenationFrom + 1);
            cMsg.xxPageShowToNum.setValue(pagenationFrom + cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
        }
    }
}
