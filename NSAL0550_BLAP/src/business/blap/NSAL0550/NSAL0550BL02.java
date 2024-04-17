/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0550;

import static business.blap.NSAL0550.constant.NSAL0550Constant.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL0550.common.NSAL0550CommonLogic;
import business.file.NSAL0550F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CR_REBIL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
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
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   Hitachi         Y.Takeno        Create          N/A
 * 2016/03/31   Hitachi         T.Kanasaka      Update          QC#6338
 * 2016/04/11   Hitachi         Y.Takeno        Update          QC#6032
 * 2016/10/26   Hitachi         T.Tomita        Update          QC#15468
 * 2017/01/18   Hitachi         K.Ochiai        Update          QC#16331
 * 2017/09/21   CITS            M.Naito         Update          QC#18758
 * 2017/10/12   CITS            M.Naito         Update          QC#21764
 * 2017/12/04   Hitachi         M.Kidokoro      Update          QC#22598
 * 2018/06/11   Fujitsu         M.Ohno          Update          QC#22381
 * 2018/06/13   Fujitsu         T.Murai         Update          QC#25687
 * 2019/12/06   Hitachi         E.Kameishi      Update          CSA QC#54749
 * 2023/09/22   CITS            R.Jin           Update          QC#61399
 *</pre>
 */
public class NSAL0550BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL0550CMsg cMsg = (NSAL0550CMsg) arg0;
        NSAL0550SMsg sMsg = (NSAL0550SMsg) arg1;

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            // START 2017/09/21 M.Naito [QC#18758, MOD]
            cMsg.xxSrvUrlTxt_H1.clear();
            // END 2017/09/21 M.Naito [QC#18758, MOD]

            if ("NSAL0550_INIT".equals(screenAplID)) {
                doProcess_NSAL0550_INIT(cMsg, sMsg);
                ZYPGUITableColumn.getColData(cMsg, sMsg);

            // START 2017/01/18 K.Ochiai [QC#16331, MOD]
            } else if ("NSAL0550Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSAL0550Scrn00_CMN_Reset(cMsg, sMsg);
            // END 2017/01/18 K.Ochiai [QC#16331, MOD]

            } else if ("NSAL0550Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NSAL0550Scrn00_CMN_Download(cMsg, sMsg);

            } else if ("NSAL0550Scrn00_PageJump".equals(screenAplID)) {
                doProcess_NSAL0550Scrn00_PageJump(cMsg, sMsg);

            } else if ("NSAL0550Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL0550Scrn00_PageNext(cMsg, sMsg);

            } else if ("NSAL0550Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL0550Scrn00_PagePrev(cMsg, sMsg);

            } else if ("NSAL0550Scrn00_Search".equals(screenAplID)) {
                doProcess_NSAL0550Scrn00_Search(cMsg, sMsg);

            } else if ("NSAL0550Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NSAL0550Scrn00_TBLColumnSort(cMsg, sMsg);

            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL0550_INIT(NSAL0550CMsg cMsg, NSAL0550SMsg sMsg) {
        initPulldownList(cMsg);
        setInputCondition(cMsg, sMsg);
        // START 2018/06/11 M.Ohno [QC#22381, MOD]
        if (ZYPCommonFunc.hasValue(cMsg.dsContrNum_H1)) {
            initSvcInvList(cMsg, sMsg);
        }
        // END 2018/06/11 M.Ohno [QC#22381, MOD]
    }

    // START 2017/01/18 K.Ochiai [QC#16331, MOD]
    private void doProcess_NSAL0550Scrn00_CMN_Reset(NSAL0550CMsg cMsg, NSAL0550SMsg sMsg) {
        initPulldownList(cMsg);
        setInputCondition(cMsg, sMsg);
        // START 2018/06/11 M.Ohno [QC#22381, MOD]
        if (ZYPCommonFunc.hasValue(cMsg.dsContrNum_H1)) {
            initSvcInvList(cMsg, sMsg);
        }
        // END 2018/06/11 M.Ohno [QC#22381, MOD]
    }
    // END 2017/01/18 K.Ochiai [QC#16331, MOD]

    private void doProcess_NSAL0550Scrn00_CMN_Download(NSAL0550CMsg cMsg, NSAL0550SMsg sMsg) {
        cMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), CSV_FILE_EXT);

        NSAL0550F00FMsg fMsg = new NSAL0550F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);

        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(cMsg));
        for (String item : CSV_EXCLUSION_ITEMS) {
            fMsg.addExclusionItem(item);
        }

        csvOutFile.writeHeader(CSV_HEADER_COLUMNS, fMsg, ZYPGUITableColumn.getColOrder(cMsg));

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            EZDMsg.copy(sMsg.A.no(i), null, fMsg, null);
            // START 2016/10/26 T.Tomita [QC#15468, ADD]
            ZYPEZDItemValueSetter.setValue(fMsg.invDtTxt_A1, convertDateFormat(sMsg.A.no(i).invDt_A1.getValue()));
            // START 2018/06/13 T.Murai [QC#25687, MOD]
//            ZYPEZDItemValueSetter.setValue(fMsg.bllgPerFromDtTxt_A1, convertDateFormat(sMsg.A.no(i).bllgPerFromDt_A1.getValue()));
//            ZYPEZDItemValueSetter.setValue(fMsg.bllgPerThruDtTxt_A1, convertDateFormat(sMsg.A.no(i).bllgPerToDt_A1.getValue()));
            ZYPEZDItemValueSetter.setValue(fMsg.xxMsgPrmTxt_FR, convertDateFormat(sMsg.A.no(i).xxMsgPrmTxt_FR.getValue()));
            ZYPEZDItemValueSetter.setValue(fMsg.xxMsgPrmTxt_TO, convertDateFormat(sMsg.A.no(i).xxMsgPrmTxt_TO.getValue()));
            // END 2018/06/13 T.Murai [QC#25687, MOD]
            ZYPEZDItemValueSetter.setValue(fMsg.invDueDtTxt_A1, convertDateFormat(sMsg.A.no(i).invDueDt_A1.getValue()));
            // END 2016/10/26 T.Tomita [QC#15468, ADD]
            csvOutFile.write();
        }

        csvOutFile.close();
    }

    private void doProcess_NSAL0550Scrn00_PageJump(NSAL0550CMsg cMsg, NSAL0550SMsg sMsg) {
        NSAL0550CommonLogic.doPagenationJump(this, cMsg, sMsg);
    }

    private void doProcess_NSAL0550Scrn00_PageNext(NSAL0550CMsg cMsg, NSAL0550SMsg sMsg) {
        NSAL0550CommonLogic.doPagenationNext(this, cMsg, sMsg);
    }

    private void doProcess_NSAL0550Scrn00_PagePrev(NSAL0550CMsg cMsg, NSAL0550SMsg sMsg) {
        NSAL0550CommonLogic.doPagenationPrev(this, cMsg, sMsg);
    }

    private void doProcess_NSAL0550Scrn00_Search(NSAL0550CMsg cMsg, NSAL0550SMsg sMsg) {
        setInputCondition(cMsg, sMsg);
        initSvcInvList(cMsg, sMsg);
    }

    private void doProcess_NSAL0550Scrn00_TBLColumnSort(NSAL0550CMsg cMsg, NSAL0550SMsg sMsg) {
        String sortTblNm = cMsg.xxSortTblNm.getValue();
        String sortItemNm = cMsg.xxSortItemNm.getValue();
        String sortOrdBy = cMsg.xxSortOrdByTxt.getValue();

        // Table:A
        if ("A".equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMsg.A, sMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMsg.A.getValidCount());

            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
        }

    }

    private void initPulldownList(NSAL0550CMsg cMsg) {
        ZYPCodeDataUtil.createPulldownList(TABLE_NAME_INV_TP, cMsg.invTpCd_H1, cMsg.invTpDescTxt_H2);
    }

    private void initSvcInvList(NSAL0550CMsg cMsg, NSAL0550SMsg sMsg) {
        ZYPTableUtil.clear(cMsg.A);

        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("glblCmpyCd", getGlobalCompanyCode());
        queryMap.put("dsContrNum", sMsg.dsContrNum_H1.getValue());
        queryMap.put("invTpCd", sMsg.invTpCd_H3.getValue());
        queryMap.put("bllgPerFromDt", sMsg.bllgPerFromDt_H1.getValue());
        queryMap.put("bllgPerToDt", sMsg.bllgPerToDt_H1.getValue());
        queryMap.put("svcInvSrcTpCd", SVC_INV_SRC_TP.CONTRACT);
        queryMap.put("rowNum", sMsg.A.length() + 1);
        // START 2016/04/11 T.Takeno [QC#6032, ADD]
        queryMap.put("delimiter", DELIMITER_CHRG_TYPE);
        // END   2016/04/11 T.Takeno [QC#6032, ADD]
        // START 2017/10/12 M.Naito [QC#21764, ADD]
        queryMap.put("svcCrRebilStsCancelled", SVC_CR_REBIL_STS.CANCELLED);
        // END 2017/10/12 M.Naito [QC#21764, ADD]
        // START 2018/06/11 M.Ohno [QC#22381, ADD]
        queryMap.put("svcCrRebilStsProcessed", SVC_CR_REBIL_STS.PROCESSED);
        queryMap.put("invTpCreditMemo", INV_TP.CREDIT_MEMO);
        queryMap.put("svcInvNum", sMsg.svcInvNum_H1.getValue());
        queryMap.put("invDt", sMsg.invDt_H1.getValue());
        queryMap.put("custIncdtId", sMsg.custCareTktNum_H1.getValue());
        if (ZYPCommonFunc.hasValue(sMsg.xxChkBox_H1) && ZYPConstant.FLG_ON_Y.equals(sMsg.xxChkBox_H1.getValue())) {
            queryMap.put("latestOnly", ZYPConstant.FLG_ON_Y);
        }
        if (ZYPCommonFunc.hasValue(sMsg.xxChkBox_H2) && ZYPConstant.FLG_ON_Y.equals(sMsg.xxChkBox_H2.getValue())) {
            queryMap.put("relatedInvoice", ZYPConstant.FLG_ON_Y);
        }
        // END 2018/06/11 M.Ohno [QC#22381, ADD]
        // START 2019/12/06 E.Kameishi [QC#54749, ADD]
        queryMap.put("invTpInvoice", INV_TP.INVOICE);
        queryMap.put("nfc", SYS_SRC.S21_ACCOUNTING_AR);
        queryMap.put("contract", CONTRACT);
        // END 2019/12/06 E.Kameishi [QC#54749, ADD]
        // START 2023/09/22 R.Jin [QC#61399, ADD]
        List<String> svcInvChrgTpCdList = new ArrayList<String>();
        svcInvChrgTpCdList.add(SVC_INV_CHRG_TP.ADDITIONAL_CHARGE);
        svcInvChrgTpCdList.add(SVC_INV_CHRG_TP.BASE_CHARGE);
        svcInvChrgTpCdList.add(SVC_INV_CHRG_TP.METER_CHARGE);
        queryMap.put("svcInvChrgTpCdList", svcInvChrgTpCdList);
        // END 2023/09/22 R.Jin [QC#61399, ADD]

        S21SsmEZDResult ssmResult = NSAL0550Query.getInstance().getSvcInvList(queryMap, sMsg.A);

        if (ssmResult.isCodeNormal()) {
            // START 2016/04/11 T.Takeno [QC#6032, DEL]
            // setInvoiceChargeType(this, queryMap, sMsg);
            // END   2016/04/11 T.Takeno [QC#6032, DEL]
            // START 2017/12/04 M.Kidokoro [QC#22598, ADD]
            BigDecimal invTotDealSlsAmt;
            BigDecimal invTotDealTaxAmt;
            BigDecimal invTotDealNetAmt;
            for (int index = 0; index < sMsg.A.getValidCount(); index++) {
                if (INV_TP.CREDIT_MEMO.equals(sMsg.A.no(index).invTpCd_A1.getValue())) {
                    invTotDealSlsAmt = sMsg.A.no(index).invTotDealSlsAmt_A1.getValue().negate();
                    invTotDealTaxAmt = sMsg.A.no(index).invTotDealTaxAmt_A1.getValue().negate();
                    invTotDealNetAmt = sMsg.A.no(index).invTotDealNetAmt_A1.getValue().negate();

                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).invTotDealSlsAmt_A1, invTotDealSlsAmt);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).invTotDealTaxAmt_A1, invTotDealTaxAmt);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).invTotDealNetAmt_A1, invTotDealNetAmt);
                }
            }
            // END 2017/12/04 M.Kidokoro [QC#22598, ADD]
            NSAL0550CommonLogic.copySMsgToCMsg(this, cMsg, sMsg, ssmResult);

        } else if (ssmResult.isCodeNotFound()) {
            NSAL0550CommonLogic.setCMsgNoResult(this, cMsg, sMsg);
        }
    }

    // START 2016/04/11 T.Takeno [QC#6032, DEL]
    // START 2016/03/31 T.Kanasaka [QC#6338, MOD]
//    private static void setInvoiceChargeType(S21BusinessHandler handler, Map<String, Object> queryMap, NSAL0550SMsg sMsg) {
//
//        S21SsmEZDResult ssmResult = NSAL0550Query.getInstance().getSvcInvChrgTypeList(queryMap);
//        List<Map<String, Object>> invoiceChargeTypeList = (List<Map<String, Object>>) ssmResult.getResultObject();
//
//        for (int sIndex = 0; sIndex < sMsg.A.getValidCount(); sIndex++) {
//            StringBuilder builder = new StringBuilder();
//            for (int listIndex = 0; listIndex < invoiceChargeTypeList.size(); listIndex++) {
//                Map<String, Object> invoiceChargeType = invoiceChargeTypeList.get(listIndex);
//                String svcInvNum = (String) invoiceChargeType.get("SVC_INV_NUM");
//                if (svcInvNum.equals(sMsg.A.no(sIndex).svcInvNum_A1.getValue())) {
//                    if (builder.length() > 0) {
//                        builder.append(DELIMITER_CHRG_TYPE);
//                    }
//                    builder.append((String) invoiceChargeType.get("SVC_INV_CHRG_TP_DESC_TXT"));
//                }
//            }
//            ZYPEZDItemValueSetter.setValue(sMsg.A.no(sIndex).svcInvChrgTpDescTxt_A1, builder.toString());
//        }
//    }
    // END 2016/03/31 T.Kanasaka [QC#6338, MOD]
    // END 2016/04/11 T.Takeno [QC#6032, DEL]

    private void setInputCondition(NSAL0550CMsg cMsg, NSAL0550SMsg sMsg) {
        ZYPEZDItemValueSetter.setValue(sMsg.invTpCd_H3, cMsg.invTpCd_H3);
        ZYPEZDItemValueSetter.setValue(sMsg.dsContrNum_H1, cMsg.dsContrNum_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.bllgPerFromDt_H1, cMsg.bllgPerFromDt_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.bllgPerToDt_H1, cMsg.bllgPerToDt_H1);
        // START 2018/06/11 M.Ohno [QC#22381, ADD]
        ZYPEZDItemValueSetter.setValue(sMsg.svcInvNum_H1, cMsg.svcInvNum_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.invDt_H1, cMsg.invDt_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.custCareTktNum_H1, cMsg.custCareTktNum_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.xxChkBox_H1, cMsg.xxChkBox_H1);
        ZYPEZDItemValueSetter.setValue(sMsg.xxChkBox_H2, cMsg.xxChkBox_H2);
        // END 2018/06/11 M.Ohno [QC#22381, ADD]
    }

    // START 2016/10/26 T.Tomita [QC#15468, ADD]
    private String convertDateFormat(String date) {
        if (ZYPCommonFunc.hasValue(date)) {
            date = ZYPDateUtil.formatEzd8ToDisp(date);
        }
        return date;
    }
    // END 2016/10/26 T.Tomita [QC#15468, ADD]
}
