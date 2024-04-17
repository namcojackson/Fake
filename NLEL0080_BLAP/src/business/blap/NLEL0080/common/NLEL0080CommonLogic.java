/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLEL0080.common;

import static business.blap.NLEL0080.constant.NLEL0080Constant.COA_SPLIT_STR;
import static business.blap.NLEL0080.constant.NLEL0080Constant.NLEM0006E;
import static business.blap.NLEL0080.constant.NLEL0080Constant.NLEM0007E;
import static business.blap.NLEL0080.constant.NLEL0080Constant.NLEM0033E;
import static business.blap.NLEL0080.constant.NLEL0080Constant.NLEM0045E;
import static business.blap.NLEL0080.constant.NLEL0080Constant.NLEM0050E;
import static business.blap.NLEL0080.constant.NLEL0080Constant.NLEM0051E;
import static business.blap.NLEL0080.constant.NLEL0080Constant.NLZM2436E;
import static business.blap.NLEL0080.constant.NLEL0080Constant.NZZM0002I;
import static business.blap.NLEL0080.constant.NLEL0080Constant.NZZM0011E;
import static business.blap.NLEL0080.constant.NLEL0080Constant.PRM_DEF_DPLY_COA_INFO;
import static business.blap.NLEL0080.constant.NLEL0080Constant.ZZM9000E;
import static business.blap.NLEL0080.constant.NLEL0080Constant.ZZM9037E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDPMsg;
import business.blap.NLEL0080.NLEL0080CMsg;
import business.blap.NLEL0080.NLEL0080Query;
import business.blap.NLEL0080.NLEL0080_ACMsg;
import business.db.DS_ASSET_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsgArray;
import business.parts.NLZC305001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC305001.NLZC305001;
import com.canon.cusa.s21.api.NLZ.NLZC305001.constant.NLZC305001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASSET_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASSET_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_MODE;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * DS Asset Manual Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/25   Hitachi         J.Kim           Create          N/A
 * 2017/05/30   CITS            T.Tokutomi      Update          DS Merge L2
 * 2017/06/13   CITS            K.Ogino         Update          QC#19086
 * 2018/01/22   Hitachi         J.Kim           Update          QC#22397
 * 2018/03/05   Hitachi         J.Kim           Update          QC#24570
 * 2018/07/24   Hitachi         J.Kim           Update          QC#24950
 * 2018/08/23   Hitachi         J.Kim           Update          QC#22267
 *</pre>
 */
public class NLEL0080CommonLogic {

    /**
     * checkSelect
     * @param cMsg NLEL0080CMsg
     * @return boolean
     */
    public static boolean checkSelect(NLEL0080CMsg cMsg) {

        boolean rtnVal = true;
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(cMsg.A, "xxChkBox", ZYPConstant.CHKBOX_ON_Y);
        if (selectedRows.isEmpty()) {
            cMsg.setMessageInfo(NZZM0011E);
            rtnVal = false;
        } else {
            ZYPTableUtil.deleteRows(cMsg.A, selectedRows);
        }
        return rtnVal;
    }

    /**
     * executeInsertDsAssetMstrDtl
     * @param cMsg NLEL0080CMsg
     * @return boolean
     */
    public static boolean executeInsertDsAssetMstrDtl(NLEL0080CMsg cMsg) {
        boolean errFlg = false;
        
        List<BigDecimal> prntAssetPkList = new ArrayList<BigDecimal>();
        List<Map<String, Object>> assetMstrAmtList = new ArrayList<Map<String, Object>>();

        String defaultLife = getDefaultLife(cMsg);
        for (int index = 0; index < cMsg.A.getValidCount(); index++) {

            BigDecimal oldCurValAmt = BigDecimal.ZERO;
            BigDecimal oldInitAmtValue = BigDecimal.ZERO;
            BigDecimal prntAssetMstrPk = cMsg.A.no(index).prntDsAssetMstrPk.getValue();
            if (ZYPCommonFunc.hasValue(prntAssetMstrPk) && !prntAssetPkList.contains(prntAssetMstrPk)) {
                Map<String, Object> assetMstrAmtMap = new HashMap<String, Object>();
                Map<String, Object> amountSummary = NLEL0080Query.getInstance().doProcessAmountSummary(cMsg.glblCmpyCd.getValue(), prntAssetMstrPk);
                oldCurValAmt = (BigDecimal) amountSummary.get("CUR_VAL_AMT");
                oldInitAmtValue = (BigDecimal) amountSummary.get("INIT_BOOK_AMT");
                assetMstrAmtMap.put("CUR_VAL_AMT", oldCurValAmt);
                assetMstrAmtMap.put("INIT_BOOK_AMT", oldInitAmtValue);
                assetMstrAmtMap.put("PRNT_DS_ASSET_MSTR_PK", prntAssetMstrPk);
                assetMstrAmtList.add(assetMstrAmtMap);
            }

            if (!insertDsAssetMstrDtl(cMsg.A.no(index), cMsg, errFlg, defaultLife)) {
                errFlg = true;
            }

            prntAssetMstrPk = cMsg.A.no(index).prntDsAssetMstrPk.getValue();
            if (!prntAssetPkList.contains(prntAssetMstrPk)) {
                prntAssetPkList.add(prntAssetMstrPk);
            }
        }
        if (errFlg == true) {
            return false;
        }

        // START 2018/08/23 J.Kim [QC#22267,ADD]
        for (BigDecimal prntAssetPk : prntAssetPkList) {

            NLZC305001PMsg pMsg = new NLZC305001PMsg();
            pMsg.updDtlList.setValidCount(1);
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate());
            ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).prntDsAssetMstrPk, prntAssetPk);
            ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).xxProcMd, NLZC305001Constant.PROC_MODE_SUMMARY);
            if (ASSET_SRC_TP.PROCURED.equals(cMsg.assetSrcTpCd_S.getValue())) {
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).procModeCd, PROC_MODE.PROCURED);
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).procModeCd, PROC_MODE.LEASED);
            }

            for (Map<String, Object> assetMstrAmt : assetMstrAmtList) {
                BigDecimal oldCurValAmt = BigDecimal.ZERO;
                BigDecimal oldInitBookAmt = BigDecimal.ZERO;
                BigDecimal prntAssetMstrPk = (BigDecimal) assetMstrAmt.get("PRNT_DS_ASSET_MSTR_PK");
                if (prntAssetMstrPk.compareTo(prntAssetPk) == 0) {
                    oldCurValAmt = (BigDecimal) assetMstrAmt.get("CUR_VAL_AMT");
                    oldInitBookAmt = (BigDecimal) assetMstrAmt.get("INIT_BOOK_AMT");
                    ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).curValAmt, oldCurValAmt);
                    ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).initBookAmt, oldInitBookAmt);
                    break;
                }
            }

            new NLZC305001().execute(pMsg, ONBATCH_TYPE.ONLINE);
            List<String> apiErrMsgList = setApiErrMsgList(pMsg);
            if (!apiErrMsgList.isEmpty()) {
                cMsg.setMessageInfo(apiErrMsgList.get(0));
                return false;
            }
        }
        // END 2018/08/23 J.Kim [QC#22267,ADD]

        cMsg.setMessageInfo(NZZM0002I);

        return true;
    }

    /**
     * insertDsAssetMstrDtl
     * @param acMsg
     * @param cMsg
     * @param otherErrFlg
     * @param defaultLife
     * @return
     */
    private static boolean insertDsAssetMstrDtl(NLEL0080_ACMsg acMsg, NLEL0080CMsg cMsg, boolean otherErrFlg, String defaultLife) {

        String glblCmpyCd = cMsg.glblCmpyCd.getValue();
        String bookTp = cMsg.assetTpNm_S.getValue();
        String scrDply = cMsg.xxScrDply.getValue();

        boolean errFlg = otherErrFlg;
        NLEL0080Query query = NLEL0080Query.getInstance();

        if (ASSET_SRC_TP.PROCURED.equals(scrDply)) {
            if (!ZYPCommonFunc.hasValue(acMsg.xxScrItem81Txt)) {
                acMsg.xxScrItem81Txt.setErrorInfo(1, ZZM9000E, new String[] {"Expense Acct" });
                errFlg = true;
            }
        } else {
            // Sale Rep, Expense Acct
            if (!ZYPCommonFunc.hasValue(acMsg.xxScrItem81Txt) && !ZYPCommonFunc.hasValue(acMsg.slsRepTocCd)) {
                acMsg.xxScrItem81Txt.setErrorInfo(1, NLEM0045E, new String[] {"Expense Acct", "Sales Rep" });
                acMsg.slsRepTocCd.setErrorInfo(1, NLEM0045E, new String[] {"Expense Acct", "Sales Rep" });
                errFlg = true;
            }
        }

        SVC_MACH_MSTRTMsg svcMachMstrTMsg = null;
        if (ZYPCommonFunc.hasValue(acMsg.serNum)) {
            SVC_MACH_MSTRTMsgArray svcMachMstrTMsgArray = query.getSvcMachMstr(glblCmpyCd, acMsg.serNum.getValue());
            if (svcMachMstrTMsgArray == null || svcMachMstrTMsgArray.getValidCount() == 0) {
                // START 2018/03/05 J.Kim [QC#24570,MOD]
                // acMsg.serNum.setErrorInfo(1, NLEM0033E, new String[] {"Service Machine Master" });
                if (ASSET_TP.EMSD_ASSET.equals(bookTp) && ASSET_SRC_TP.LEASED.equals(scrDply)) {
                    acMsg.serNum.setErrorInfo(1, NLEM0051E);
                } else {
                    acMsg.serNum.setErrorInfo(1, NLEM0033E, new String[] {"Service Machine Master" });
                }
                // END 2018/03/05 J.Kim [QC#24570,MOD]
                errFlg = true;
            } else if (svcMachMstrTMsgArray.getValidCount() > 1) {
                acMsg.serNum.setErrorInfo(1, NLZM2436E);
                errFlg = true;
            } else {
                svcMachMstrTMsg = (SVC_MACH_MSTRTMsg) svcMachMstrTMsgArray.get(0);
            }

            // START 2018/03/05 J.Kim [QC#24570,ADD]
            if (!errFlg && ASSET_TP.EMSD_ASSET.equals(bookTp) && ASSET_SRC_TP.LEASED.equals(scrDply)) {
                if (!NLEL0080Query.getInstance().findByCondition(glblCmpyCd, svcMachMstrTMsg.curLocAcctNum.getValue())) {
                    acMsg.serNum.setErrorInfo(1, NLEM0051E);
                    errFlg = true;
                }
            }
            // END 2018/03/05 J.Kim [QC#24570,ADD]
        }

        if (ZYPCommonFunc.hasValue(acMsg.sellToCustCd)) {
            List<Map<String, Object>> dsAcctCustInfo = query.isExistDsAcctCust(glblCmpyCd, acMsg.sellToCustCd.getValue());
            if (dsAcctCustInfo == null) {
                acMsg.sellToCustCd.setErrorInfo(1, NLEM0033E, new String[] {"Sell To Cust" });
                errFlg = true;
            }

            String addrLine = acMsg.firstLineAddr.getValue().trim().concat(acMsg.ctyAddr.getValue().trim());
            String addr = acMsg.stCd.getValue().trim().concat(acMsg.postCd.getValue().trim());
            String strAddress = addrLine.concat(addr);
            if (ZYPCommonFunc.hasValue(strAddress)) {
                boolean isExist = false;
                for (Map<String, Object> dsAcctCust : dsAcctCustInfo) {
                    String address = (String) dsAcctCust.get("ADDR");
                    if (address.equals(strAddress)) {
                        isExist = true;
                    }
                }
                if (!isExist) {
                    acMsg.firstLineAddr.setErrorInfo(1, NLEM0033E, new String[] {"Address Line" });
                    acMsg.ctyAddr.setErrorInfo(1, NLEM0033E, new String[] {"City" });
                    acMsg.stCd.setErrorInfo(1, NLEM0033E, new String[] {"State" });
                    acMsg.postCd.setErrorInfo(1, NLEM0033E, new String[] {"Zip Code" });
                    errFlg = true;
                }
            }
        }

        if (ZYPCommonFunc.hasValue(acMsg.slsRepTocCd)) {
            if (!query.isExistSalesRep(glblCmpyCd, acMsg.slsRepTocCd.getValue())) {
                acMsg.slsRepTocCd.setErrorInfo(1, NLEM0033E, new String[] {"Transaction Organization Code" });
                errFlg = true;
            }
        }

        if (ZYPCommonFunc.hasValue(acMsg.prntDsAssetMstrPk)) {
            DS_ASSET_MSTRTMsg dsAssetMstrTMsg = query.getDsAssetMstr(glblCmpyCd, acMsg.prntDsAssetMstrPk.getValue());
            if (dsAssetMstrTMsg == null) {
                acMsg.prntDsAssetMstrPk.setErrorInfo(1, NLEM0033E, new String[] {"Direct Sales Asset Master" });
                errFlg = true;
            } else {
                if (ZYPCommonFunc.hasValue(dsAssetMstrTMsg.svcMachMstrPk)) {
                    SVC_MACH_MSTRTMsg prntSvcMachMstrTMsg = query.getSvcMachMstr(glblCmpyCd, dsAssetMstrTMsg.svcMachMstrPk.getValue());
                    if (prntSvcMachMstrTMsg == null) {
                        acMsg.prntDsAssetMstrPk.setErrorInfo(1, NLEM0033E, new String[] {"Service Machine Master" });
                        errFlg = true;
                    }
                }
            }

            if (!query.isExistPrntDsAssetMstrPk(glblCmpyCd, bookTp, acMsg.prntDsAssetMstrPk.getValue())) {
                // START 2018/01/22 J.Kim [QC#22397,MOD]
                // acMsg.prntDsAssetMstrPk.setErrorInfo(1, NLEM0046E);
                acMsg.prntDsAssetMstrPk.setErrorInfo(1, NLEM0050E);
                // END 2018/01/22 J.Kim [QC#22397,MOD]
                errFlg = true;
            }
        }

        if (svcMachMstrTMsg != null && query.isExistDsAssetMstr(glblCmpyCd, svcMachMstrTMsg.mdseCd.getValue(), svcMachMstrTMsg.serNum.getValue())) {
            acMsg.serNum.setErrorInfo(1, NLEM0006E, new String[] {"Direct Sales Asset Master" });
            errFlg = true;
        }

        if (ZYPCommonFunc.hasValue(acMsg.depcMthNum)) {
            if (!ZYPCommonFunc.isNumberCheck(acMsg.depcMthNum.getValue()) || Integer.parseInt(acMsg.depcMthNum.getValue()) <= 0) {
                acMsg.depcMthNum.setErrorInfo(1, NLEM0007E, new String[] {"Life in Months" });
                errFlg = true;
            }
        }

        // DS Merge Lv.2
        // Check CPO Order
        if (ZYPCommonFunc.hasValue(acMsg.cpoDplyLineNum)) {
            if (!checkCpoDtl(acMsg, glblCmpyCd)) {
                errFlg = true;
            }
        }

        if (errFlg) {
            return false;
        }

        List<String> apiErrMsgList = callApiNLZC3050(cMsg, acMsg, glblCmpyCd, svcMachMstrTMsg);
        if (!apiErrMsgList.isEmpty()) {
            acMsg.xxChkBox.setErrorInfo(1, apiErrMsgList.get(0));
            return false;
        }

        return true;
    }

    /**
     * checkCpoDtl
     * @param acMsg NLEL0080_ACMsg
     * @param glblCmpyCd String
     * @return true:Success/false:Error
     */
    private static boolean checkCpoDtl(NLEL0080_ACMsg acMsg, String glblCmpyCd) {
        if (!ZYPCommonFunc.hasValue(acMsg.cpoOrdNum)) {
            acMsg.cpoOrdNum.setErrorInfo(1, ZZM9037E);
            return false;
        }

        String[] splitDsplyLineNum = acMsg.cpoDplyLineNum.getValue().split(COA_SPLIT_STR);

        String dsOrdPosnNum = splitDsplyLineNum[0];
        String dsCpoLineNum = null;
        String dsCpoLineSubNum = null;
        if (splitDsplyLineNum.length > 1) {
            dsCpoLineNum = splitDsplyLineNum[1];
        }
        if (splitDsplyLineNum.length > 2) {
            dsCpoLineSubNum = splitDsplyLineNum[2];
        }
        S21SsmEZDResult rslt = NLEL0080Query.getInstance().getCpoDtlLineNum(glblCmpyCd, acMsg.cpoOrdNum.getValue(), dsOrdPosnNum, dsCpoLineNum, dsCpoLineSubNum);

        if (!rslt.isCodeNormal()) {
            acMsg.cpoOrdNum.setErrorInfo(1, ZZM9037E);
            acMsg.cpoDplyLineNum.setErrorInfo(1, ZZM9037E);
            return false;
        }

        if (rslt.getQueryResultCount() != 1) {
            // Not uniquely.
            acMsg.cpoOrdNum.setErrorInfo(1, ZZM9037E);
            acMsg.cpoDplyLineNum.setErrorInfo(1, ZZM9037E);
            return false;
        }

        List<Map<String, Object>> records = (List<Map<String, Object>>) rslt.getResultObject();
        Map<String, Object> record = records.get(0);
        ZYPEZDItemValueSetter.setValue(acMsg.cpoDtlLineNum, (String) record.get("CPO_DTL_LINE_NUM"));
        ZYPEZDItemValueSetter.setValue(acMsg.cpoDtlLineSubNum, (String) record.get("CPO_DTL_LINE_SUB_NUM"));

        return true;
    }

    /**
     * call Asset Update API (Mode E : Asset Manual Entry)
     * @param acMsg NLEL0080_ACMsg
     * @param glblCmpyCd NLEL0080CMsg
     * @param assetTpNm String
     * @param svcMachMstrTMsg SVC_MACH_MSTRTMsg
     * @param cMsg NLEL0080CMsg
     * @return List<String>
     */
    private static List<String> callApiNLZC3050(NLEL0080CMsg cMsg, NLEL0080_ACMsg acMsg, String glblCmpyCd, SVC_MACH_MSTRTMsg svcMachMstrTMsg) {

        String slsRepTocCd = NLEL0080Query.getInstance().getTocCd(glblCmpyCd, acMsg.slsRepTocCd.getValue());
        String vndCd = NLEL0080Query.getInstance().getVndCd(glblCmpyCd, acMsg.vndTpDescTxt.getValue());

        NLZC305001PMsg pMsg = new NLZC305001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, ZYPDateUtil.getSalesDate());
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).xxProcMd, NLZC305001Constant.PROC_MODE_ASSET_MAN_ENTRY);
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).dsAssetMstrPk, acMsg.dsAssetMstrPk);
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).assetTpCd, cMsg.assetTpNm_S); // BookType
        // QC#19086 Modify Start
        if (svcMachMstrTMsg != null) {
            ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).svcMachMstrPk, svcMachMstrTMsg.svcMachMstrPk);
            ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).svcConfigMstrPk, svcMachMstrTMsg.svcConfigMstrPk);
        }
        // QC#19086 Modify End
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).slsRepTocCd, slsRepTocCd);
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).invNum, acMsg.invNum);

        String itemTxt = acMsg.xxScrItem81Txt.getValue();
        if (ZYPCommonFunc.hasValue(itemTxt)) {
            String[] cdList = itemTxt.split(COA_SPLIT_STR);
            if (cdList.length == PRM_DEF_DPLY_COA_INFO) {
                List<String> itemCdList = Arrays.asList(cdList);
                int itemIndex = 0;
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).coaCmpyCd, itemCdList.get(itemIndex));
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).assetCoaCmpyCd, itemCdList.get(itemIndex));
                itemIndex++;
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).coaBrCd, itemCdList.get(itemIndex));
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).assetCoaBrCd, itemCdList.get(itemIndex));
                itemIndex++;
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).coaCcCd, itemCdList.get(itemIndex));
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).assetCoaCcCd, itemCdList.get(itemIndex));
                itemIndex++;
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).coaAcctCd, itemCdList.get(itemIndex));
                // Not set Asset COA Account Code
                itemIndex++;
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).coaProdCd, itemCdList.get(itemIndex));
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).assetCoaProdCd, itemCdList.get(itemIndex));
                itemIndex++;
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).coaChCd, itemCdList.get(itemIndex));
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).assetCoaChCd, itemCdList.get(itemIndex));
                itemIndex++;
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).coaAfflCd, itemCdList.get(itemIndex));
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).assetCoaAfflCd, itemCdList.get(itemIndex));
                itemIndex++;
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).coaProjCd, itemCdList.get(itemIndex));
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).assetCoaProjCd, itemCdList.get(itemIndex));
                itemIndex++;
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).coaExtnCd, itemCdList.get(itemIndex));
                ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).assetCoaExtnCd, itemCdList.get(itemIndex));
            }
        }
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).depcMthNum, acMsg.depcMthNum);
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).curValAmt, acMsg.curValAmt);
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).prntDsAssetMstrPk, acMsg.prntDsAssetMstrPk);
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).assetSrcTpCd, cMsg.assetSrcTpCd_S); // AssetType
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).cpoOrdNum, acMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).cpoDtlLineNum, acMsg.cpoDtlLineNum);
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).cpoDtlLineSubNum, acMsg.cpoDtlLineSubNum);
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).sellToCustCd, acMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).assetDescTxt, acMsg.dsAssetDescTxt);
        // START 2018/06/25 J.Kim [QC#24844,MOD]
        // ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).assetLeaseNum, acMsg.assetLeaseNum);
        // ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).leaseStartDt, acMsg.leaseStartDt);
        // ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).leaseEndDt, acMsg.leaseEndDt);
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).dsContrNum, acMsg.dsContrNum);
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).contrEffFromDt, acMsg.contrEffFromDt);
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).contrEffThruDt, acMsg.contrEffThruDt);
        // END 2018/06/25 J.Kim [QC#24844,MOD]
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).invDt, acMsg.invDt);
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).vndCd, vndCd);
        // START 2018/06/25 J.Kim [QC#24844,MOD]
        // ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).poOrdNum, acMsg.poOrdNum);
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).custIssPoNum, acMsg.custIssPoNum);
        // END 2018/06/25 J.Kim [QC#24844,MOD]
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).totAssetQty, acMsg.totAssetQty);
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).bllgInvNum, acMsg.bllgInvNum);
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).lastBillDt, acMsg.lastBillDt);
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).firstLineAddr, acMsg.firstLineAddr);
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).ctyAddr, acMsg.ctyAddr);
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).stCd, acMsg.stCd);
        ZYPEZDItemValueSetter.setValue(pMsg.updDtlList.no(0).postCd, acMsg.postCd);

        pMsg.updDtlList.setValidCount(1);

        new NLZC305001().execute(pMsg, ONBATCH_TYPE.ONLINE);
        // START 2018/08/23 J.Kim [QC#22267,ADD]
        ZYPEZDItemValueSetter.setValue(acMsg.dsAssetMstrPk, pMsg.updDtlList.no(0).dsAssetMstrPk);
        ZYPEZDItemValueSetter.setValue(acMsg.prntDsAssetMstrPk, pMsg.updDtlList.no(0).prntDsAssetMstrPk);
        // END 2018/08/23 J.Kim [QC#22267,ADD]
        return setApiErrMsgList(pMsg);
    }

    /**
     * Clear Message
     * @param cMsg NLEL0080CMsg
     */
    public static void clearMsg(NLEL0080CMsg cMsg) {

        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        cMsg.assetTpNm_S.clear();
        cMsg.assetSrcTpCd_S.clear();
    }

    /**
     * Create Pull Down
     * @param cMsg NLEL0080CMsg
     */
    public static void createPullDown(NLEL0080CMsg cMsg) {

        ZYPCodeDataUtil.createPulldownList(ASSET_TP.class, cMsg.assetTpNm_C, cMsg.assetTpDescTxt_D);
        ZYPCodeDataUtil.createPulldownList(ASSET_SRC_TP.class, cMsg.assetSrcTpCd_C, cMsg.assetSrcTpDescTxt_D);
    }

    private static List<String> setApiErrMsgList(EZDPMsg apiPMsg) {

        List<String> apiErrMsgList = new ArrayList<String>();
        if (S21ApiUtil.isXxMsgId(apiPMsg)) {
            List<String> xxMsgIdList = S21ApiUtil.getXxMsgIdList(apiPMsg);
            for (String xxMsgId : xxMsgIdList) {
                apiErrMsgList.add(xxMsgId);
            }
        }
        return apiErrMsgList;
    }

    /**
     * @param cMsg NLEL0080CMsg
     */
    private static String getDefaultLife(NLEL0080CMsg cMsg) {
        return NLEL0080Query.getInstance().getDefaultLife(cMsg.glblCmpyCd.getValue(), cMsg.assetTpNm_S.getValue());
    }
}
