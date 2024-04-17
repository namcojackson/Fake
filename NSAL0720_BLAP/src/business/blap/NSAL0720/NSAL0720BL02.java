/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0720;

import static business.blap.NSAL0720.constant.NSAL0720Constant.MTR_LB_NM;
import static business.blap.NSAL0720.constant.NSAL0720Constant.NSAM0013E;
import static business.blap.NSAL0720.constant.NSAL0720Constant.NSAM0072E;
import static business.blap.NSAL0720.constant.NSAL0720Constant.NZZM0001W;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL0720.common.NSAL0720CommonLogic;
import business.blap.NSAL0720.constant.NSAL0720Constant;
import business.db.BILL_TO_CUSTTMsg;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_DTLTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Update Bill To
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/20   Hitachi         K.Kasai         Create          N/A
 * 2015/12/08   Hitachi         T.Tsuchida      Update          QC#1606
 * 2016/01/05   Hitachi         T.Tomita        Update          QC#1029
 * 2016/02/19   Hitachi         T.Aoyagi        Update          QC3694
 * 2016/02/29   Hitachi         K.Kasai         Update          QC#2684
 * 2016/05/23   Hitachi         M.Gotou         Update          QC#5922
 * 2016/07/25   Hitachi         K.Yamada        Update          CSA QC#12001
 * 2016/08/26   Hitachi         A.Kohinata      Update          QC#5922
 * 2016/11/08   Hitachi         T.Mizuki        Update          QC#4210
 * 2017/02/14   Hitachi         K.Ochiai        Update          QC#16331
 * 2019/11/25   Hitachi         E.Kameishi      Update          QC#53071
 *</pre>
 */
public class NSAL0720BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL0720CMsg cMsg = (NSAL0720CMsg) arg0;
        NSAL0720SMsg sMsg = (NSAL0720SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL0720_INIT".equals(screenAplID)) {
                doProcess_NSAL0720_INIT(cMsg, sMsg);
            } else if ("NSAL0720Scrn00_ApplyToAll".equals(screenAplID)) {
                doProcess_NSAL0720Scrn00_ApplyToAll(cMsg, sMsg);
            } else if ("NSAL0720Scrn00_Search_BillTo".equals(screenAplID)) {
                doProcess_NSAL0720Scrn00_Search_BillTo(cMsg);
            // START 2017/02/14 K.Ochiai [QC#16331, MOD]
            } else if ("NSAL0720Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSAL0720_CMN_Reset(cMsg, sMsg);
            // END   2017/02/14 K.Ochiai [QC#16331, MOD]
            } else if ("NSAL0720Scrn00_Contraction".equals(screenAplID)) {
                doProcess_NSAL0720Scrn00_Contraction(cMsg, sMsg);
            } else if ("NSAL0720Scrn00_Expansion".equals(screenAplID)) {
                doProcess_NSAL0720Scrn00_Expansion(cMsg, sMsg);
            // START 2016/01/05 T.Tomita [QC#1029, ADD]
            } else if ("NSAL0720_NMAL6760".equals(screenAplID)) {
                doProcess_NSAL0720_NMAL6760(cMsg, sMsg);
            // END 2016/01/05 T.Tomita [QC#1029, ADD]
            // add start 2016/07/26 CSA Defect#12001
            } else if ("NSAL0720Scrn00_CheckAllBase".equals(screenAplID)) {
                doProcess_NSAL0720Scrn00_CheckAllBase(cMsg, sMsg);
            } else if ("NSAL0720Scrn00_CheckAllOverage".equals(screenAplID)) {
                doProcess_NSAL0720Scrn00_CheckAllOverage(cMsg, sMsg);
            // mod start 2016/11/08 CSA QC#4210
            } else if ("NSAL0720Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL0720Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSAL0720Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL0720Scrn00_PagePrev(cMsg, sMsg);
            }
            // mod end 2016/11/08 CSA QC#4210
            // add end 2016/07/26 CSA Defect#12001
        } finally {
            super.postDoProcess(cMsg, null);
        }
    }

    private void doProcess_NSAL0720_INIT(NSAL0720CMsg cMsg, NSAL0720SMsg sMsg) {
        init(cMsg, sMsg);
    }
    // START 2017/02/14 K.Ochiai [QC#16331, MOD]
    private void doProcess_NSAL0720_CMN_Reset(NSAL0720CMsg cMsg, NSAL0720SMsg sMsg) {
    // END   2017/02/14 K.Ochiai [QC#16331, MOD]
        init(cMsg, sMsg);
    }

    // mod start 2016/07/25 CSA Defect#12001
    private void doProcess_NSAL0720Scrn00_ApplyToAll(NSAL0720CMsg cMsg, NSAL0720SMsg sMsg) {
        NSAL0720CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);

        if (validationCheck(sMsg)) {
            cMsg.setMessageInfo(NSAL0720Constant.NSAM0015E, new String[]{});
            return;
        }

        //get Bill to Address
        if (!getBillTo(cMsg)) {
            cMsg.billToCustCd_H1.setErrorInfo(1, NSAM0072E, new String[] {"New Bill To" });
            return;
        }

        //Apply selected row
        NSAL0720_ASMsg aSMsgIn = new NSAL0720_ASMsg();
        BigDecimal preContrHdrPk = BigDecimal.ZERO;
        BigDecimal curContrHdrPk = BigDecimal.ZERO;
        BigDecimal preContrDtlPk = BigDecimal.ZERO;
        BigDecimal curContrDtlPk = BigDecimal.ZERO;
        boolean procHdrFlg = false;
        boolean procDtlFlg = false;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            aSMsgIn = sMsg.A.no(i);
            curContrHdrPk = aSMsgIn.dsContrPk_A1.getValue();
            curContrDtlPk = aSMsgIn.dsContrDtlPk_A1.getValue();
            if (!preContrHdrPk.equals(curContrHdrPk)) {
                procHdrFlg = false;
                if (FLG_ON_Y.equals(aSMsgIn.xxChkBox_A1.getValue())) {
                    procHdrFlg = true;
                }
            }
            if (!preContrDtlPk.equals(curContrDtlPk)) {
                procDtlFlg = false;
            }
            if (hasValue(aSMsgIn.serNum_A1) && FLG_ON_Y.equals(aSMsgIn.xxChkBox_A1.getValue())) {
                procDtlFlg = true;
            // START 2019/11/25 E.Kameishi [QC#53071, ADD]
            } else if (!hasValue(aSMsgIn.xxScrItem34Txt_A1) && !hasValue(aSMsgIn.serNum_A1) && !hasValue(aSMsgIn.mtrLbDescTxt_A1) && FLG_ON_Y.equals(aSMsgIn.xxChkBox_A1.getValue())) {
                procDtlFlg = true;
            }
            // END 2019/11/25 E.Kameishi [QC#53071, ADD]
            // mod start 2016/05/23 CSA Defect#5922
            if (procHdrFlg || procDtlFlg || FLG_ON_Y.equals(aSMsgIn.xxChkBox_A2.getValue())) {
            // mod end 2016/05/23 CSA Defect#5922
                // START 2019/11/25 E.Kameishi [QC#53071, MOD]
                //if (setNewBillTo(cMsg, aSMsgIn)) {
                if (setNewBillTo(cMsg, aSMsgIn, procHdrFlg)) {
                // END 2019/11/25 E.Kameishi [QC#53071, MOD]
                    setValue(aSMsgIn.billToCustCd_A2, cMsg.billToCustCd_H1);
                    setValue(aSMsgIn.billToCustLocAddr_A2, cMsg.billToCustLocAddr_H1);
                }
            }
            preContrHdrPk = curContrHdrPk;
            preContrDtlPk = curContrDtlPk;
        }

        NSAL0720CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }
    // mod end 2016/07/25 CSA Defect#12001

    /**
     * Contraction event handler
     * @param cMsg NSAL0720CMsg
     * @param SMsg NSAL0720SMsg
     */
    private void doProcess_NSAL0720Scrn00_Contraction(NSAL0720CMsg cMsg, NSAL0720SMsg sMsg) {

        // set Expansion Button
        NSAL0720CommonLogic.executeContraction(cMsg, sMsg);
    }

    /**
     * Expansion event handler
     * @param cMsg NSAL0720CMsg
     * @param SMsg NSAL0720SMsg
     */
    private void doProcess_NSAL0720Scrn00_Expansion(NSAL0720CMsg cMsg, NSAL0720SMsg sMsg) {

        // set Expansion Button
        NSAL0720CommonLogic.executeExpansion(cMsg, sMsg);
    }

    // START 2016/01/05 T.Tomita [QC#1029, ADD]
    private void doProcess_NSAL0720_NMAL6760(NSAL0720CMsg cMsg, NSAL0720SMsg sMsg) {
        getBillTo(cMsg);
    }
    // END 2016/01/05 T.Tomita [QC#1029, ADD]

    /**
     * @param cMsg
     * @param aSMsgIn
     * @return
     */
    private boolean setNewBillTo(NSAL0720CMsg cMsg, NSAL0720_ASMsg aSMsgIn, boolean procHdrFlg) {
        if (hasValue(aSMsgIn.xxScrItem34Txt_A1)) {
            return true;
        } else if (DS_CONTR_CATG.FLEET.equals(aSMsgIn.dsContrCatgCd_A1.getValue()) && !DS_CONTR_DTL_TP.FLEET.equals(aSMsgIn.dsContrDtlTpCd_A1.getValue())) {
            return false;
        // mod start 2016/02/29 CSA Defect#2684
        } else if (hasValue(aSMsgIn.mtrLbDescTxt_A1) && MTR_LB_NM.equals(aSMsgIn.mtrLbDescTxt_A1.getValue()) && FLG_ON_Y.equals(cMsg.xxChkBox_BS.getValue())) {
        // mod end 2016/02/29 CSA Defect#2684
            return true;
        // mod start 2016/02/29 CSA Defect#2684
        } else if (hasValue(aSMsgIn.mtrLbDescTxt_A1) && !MTR_LB_NM.equals(aSMsgIn.mtrLbDescTxt_A1.getValue()) && FLG_ON_Y.equals(cMsg.xxChkBox_OR.getValue())) {
        // mod end 2016/02/29 CSA Defect#2684
            return true;
        // mod start 2016/02/29 CSA Defect#2684
        } else if (hasValue(aSMsgIn.mtrLbDescTxt_A1) && !FLG_ON_Y.equals(cMsg.xxChkBox_BS.getValue()) && !FLG_ON_Y.equals(cMsg.xxChkBox_OR.getValue())) {
        // mod end 2016/02/29 CSA Defect#2684
            return true;
        // add start 2016/05/23 CSA Defect#5922
        } else if (hasValue(aSMsgIn.serNum_A1)) {
            return true;
        // add start 2016/05/23 CSA Defect#5922
        // START 2019/11/25 E.Kameishi [QC#53071, ADD]
        } else if (procHdrFlg && !hasValue(aSMsgIn.xxChkBox_A1.getValue()) && !hasValue(aSMsgIn.xxScrItem34Txt_A1) && !hasValue(aSMsgIn.serNum_A1) && !hasValue(aSMsgIn.mtrLbDescTxt_A1)) {
            return true;
        } else if (FLG_ON_Y.equals(aSMsgIn.xxChkBox_A1.getValue()) && !hasValue(aSMsgIn.xxScrItem34Txt_A1) && !hasValue(aSMsgIn.serNum_A1) && !hasValue(aSMsgIn.mtrLbDescTxt_A1)) {
            return true;
        }
        // END 2019/11/25 E.Kameishi [QC#53071, ADD]
        return false;
    }

    private void doProcess_NSAL0720Scrn00_Search_BillTo(NSAL0720CMsg cMsg) {
        if (!getBillTo(cMsg)) {
            cMsg.billToCustCd_H1.setErrorInfo(1, NSAM0072E, new String[] {"New Bill To" });
        }
    }

    // add start 2016/07/26 CSA Defect#12001
    private void doProcess_NSAL0720Scrn00_CheckAllBase(NSAL0720CMsg cMsg, NSAL0720SMsg sMsg) {
        NSAL0720CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        boolean checkOn = false;
        if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_BS.getValue())) {
            checkOn = true;
        }

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NSAL0720_ASMsg asMsg = sMsg.A.no(i);
            if (hasValue(asMsg.mtrLbDescTxt_A1) && MTR_LB_NM.equals(asMsg.mtrLbDescTxt_A1.getValue())) {
                //Base Lv
                if (checkOn) {
                    // START 2016/08/26 A.Kohinata [QC#5922, ADD]
                    if (isFleetMachine(cMsg, sMsg, asMsg)) {
                        continue;
                    }
                    // END 2016/08/26 A.Kohinata [QC#5922, ADD]
                    setValue(asMsg.xxChkBox_A2, ZYPConstant.FLG_ON_Y);
                } else {
                    setValue(asMsg.xxChkBox_A2, ZYPConstant.FLG_OFF_N);
                }
            }
        }

        NSAL0720CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }

    private void doProcess_NSAL0720Scrn00_CheckAllOverage(NSAL0720CMsg cMsg, NSAL0720SMsg sMsg) {
        NSAL0720CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        boolean checkOn = false;
        if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_OR.getValue())) {
            checkOn = true;
        }

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NSAL0720_ASMsg asMsg = sMsg.A.no(i);
            if (hasValue(asMsg.mtrLbDescTxt_A1) && !MTR_LB_NM.equals(asMsg.mtrLbDescTxt_A1.getValue())) {
                //Overage Lv
                if (checkOn) {
                    // START 2016/08/26 A.Kohinata [QC#5922, ADD]
                    if (isFleetMachine(cMsg, sMsg, asMsg)) {
                        continue;
                    }
                    // END 2016/08/26 A.Kohinata [QC#5922, ADD]
                    setValue(asMsg.xxChkBox_A2, ZYPConstant.FLG_ON_Y);
                } else {
                    setValue(asMsg.xxChkBox_A2, ZYPConstant.FLG_OFF_N);
                }
            }
        }

        NSAL0720CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
    }
    // add end 2016/07/26 CSA Defect#12001

    /**
     * @param cMsg
     */
    private boolean getBillTo(NSAL0720CMsg cMsg) {
        if (hasValue((cMsg.billToCustCd_H1))) {
            String glblCmpyCd = cMsg.glblCmpyCd.getValue();
            NSAL0720Query query = NSAL0720Query.getInstance();
            BILL_TO_CUSTTMsg tMsg = query.getBillToCust(glblCmpyCd, cMsg.billToCustCd_H1.getValue());
            if (tMsg == null) {
                return false;
            } else {
                StringBuilder billToCustLocAddr = new StringBuilder();
                if (hasValue(tMsg.firstLineAddr)) {
                    billToCustLocAddr.append(tMsg.firstLineAddr.getValue());
                }
                if (hasValue(tMsg.scdLineAddr)) {
                    billToCustLocAddr.append(tMsg.scdLineAddr.getValue());
                    billToCustLocAddr.append(" ");
                }
                if (hasValue(tMsg.ctyAddr)) {
                    billToCustLocAddr.append(tMsg.ctyAddr.getValue());
                    billToCustLocAddr.append(" ");
                }
                if (hasValue(tMsg.postCd)) {
                    billToCustLocAddr.append(tMsg.postCd.getValue());
                }
                setValue(cMsg.billToCustLocAddr_H1, billToCustLocAddr.toString().trim());
                setValue(cMsg.locNm_H1, tMsg.locNm);
            }

            // add start 2016/07/27 CSA Defect#12001
            String leaseAcctFlg = query.getLeaseAcctFlg(glblCmpyCd, cMsg.billToCustCd_H1.getValue());
            if (FLG_ON_Y.equals(leaseAcctFlg)) {
                setValue(cMsg.xxChkBox_LS, leaseAcctFlg);
            } else {
                cMsg.xxChkBox_LS.clear();
            }
            // add end 2016/07/27 CSA Defect#12001

            return true;
        } else {
            return false;
        }
    }

    private void init(NSAL0720CMsg cMsg, NSAL0720SMsg sMsg) {
        cMsg.billToCustCd_H1.clear();
        cMsg.locNm_H1.clear();
        cMsg.billToCustLocAddr_H1.clear();
        cMsg.svcCmntTxt_H1.clear();
        cMsg.svcMemoRsnCd_H3.clear();
        cMsg.xxChkBox_LS.clear();
        cMsg.xxChkBox_BS.clear();
        cMsg.xxChkBox_OR.clear();
        cMsg.xxChkBox_AL.clear();
        cMsg.xxChkBox_BL.clear();
        ZYPTableUtil.clear(cMsg.A);

        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        NSAL0720CommonLogic.createPullDown(cMsg);
        findContrHdrInfo(cMsg, sMsg);
        // mod start 2016/11/08 CSA QC#4210
        // set Paging Data
        cMsg.xxPageShowFromNum.setValue(1);
        cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount());
        // mod end 2016/11/08 CSA QC#4210
    }

    /**
     * @param cMsg
     * @param sMsg
     * @return
     */
    private void findContrHdrInfo(NSAL0720CMsg cMsg, NSAL0720SMsg sMsg) {

        Map<String, Object> queryMap = setParamForSearch(cMsg);
        S21SsmEZDResult ssmResult = NSAL0720Query.getInstance().getContrHdrInfo(queryMap, sMsg.A);
        sMsg.A.setValidCount(ssmResult.getQueryResultCount());

        if (ssmResult.isCodeNotFound()) {
            cMsg.setMessageInfo(NSAM0013E);
            return;
        }

        if (ssmResult.getQueryResultCount() > sMsg.A.length()) {
            cMsg.setMessageInfo(NZZM0001W);
            sMsg.A.setValidCount(sMsg.A.length());
        }

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxRowNum_A, BigDecimal.valueOf(i));
        }
        EZDMsg.copy(sMsg.A, null, cMsg.A, null);
    }

    /**
     * @param cMsg
     * @return
     */
    private Map<String, Object> setParamForSearch(NSAL0720CMsg cMsg) {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        List<BigDecimal> dsContrPkList = new ArrayList<BigDecimal>(cMsg.P.getValidCount());
        // START 2016/02/19 T.Aoyagi [QC3694, MOD]
        List<BigDecimal> dsContrDtlPkList = new ArrayList<BigDecimal>(cMsg.P.getValidCount());

        for (int i = 0; i < cMsg.P.getValidCount(); i++) {
            dsContrPkList.add(cMsg.P.no(i).dsContrPk_P1.getValue());
            if (ZYPCommonFunc.hasValue(cMsg.P.no(i).dsContrDtlPk_P1)) {
                dsContrDtlPkList.add(cMsg.P.no(i).dsContrDtlPk_P1.getValue());
            }
        }

        queryMap.put("dsContrPkArray", dsContrPkList);
        if (!dsContrDtlPkList.isEmpty()) {
            queryMap.put("existContrDtlPk", ZYPConstant.FLG_ON_Y);
            queryMap.put("dsContrDtlPkArray", dsContrDtlPkList);
            queryMap.put("dsContrDtlTpCdFleet", DS_CONTR_DTL_TP.FLEET);
            queryMap.put("dsContrDtlTpCdAgg", DS_CONTR_DTL_TP.AGGREGATE);
        }
        // END 2016/02/19 T.Aoyagi [QC3694, MOD]

        queryMap.put("mtrLbNm", MTR_LB_NM);
        queryMap.put("baseChrgFlg", FLG_ON_Y);
        queryMap.put("usgChrgFlg", FLG_ON_Y);
        queryMap.put("rowNum", cMsg.A.length() + 1);
        return queryMap;
    }

    /**
     * @param cMsg
     */
    private boolean validationCheck(NSAL0720SMsg sMsg) {
        NSAL0720_ASMsg aSMsg = new NSAL0720_ASMsg();
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            aSMsg = sMsg.A.no(i);
            if (hasValue(aSMsg.xxChkBox_A1) || hasValue(aSMsg.xxChkBox_A2)) {
                return false;
            }
        }
        return true;
    }

    // START 2016/08/26 A.Kohinata [QC#5922, ADD]
    private boolean isFleetMachine(NSAL0720CMsg cMsg, NSAL0720SMsg sMsg, NSAL0720_ASMsg asMsg) {
        DS_CONTRTMsg inHdrTMsg = new DS_CONTRTMsg();
        setValue(inHdrTMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
        setValue(inHdrTMsg.dsContrPk, asMsg.dsContrPk_A1.getValue());
        DS_CONTRTMsg outHdrTMsg = (DS_CONTRTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(inHdrTMsg);
        if (outHdrTMsg == null) {
            return true;
        }
        DS_CONTR_DTLTMsg inDtlTMsg = new DS_CONTR_DTLTMsg();
        setValue(inDtlTMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
        setValue(inDtlTMsg.dsContrDtlPk, asMsg.dsContrDtlPk_A1.getValue());
        DS_CONTR_DTLTMsg outDtlTMsg = (DS_CONTR_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(inDtlTMsg);
        if (outDtlTMsg == null) {
            return true;
        }
        if (DS_CONTR_CATG.FLEET.equals(outHdrTMsg.dsContrCatgCd.getValue()) && !DS_CONTR_DTL_TP.FLEET.equals(outDtlTMsg.dsContrDtlTpCd.getValue())) {
            return true;
        }
        return false;
    }
    // END 2016/08/26 A.Kohinata [QC#5922, ADD]
    // mod start 2016/11/08 CSA QC#4210
    private void doProcess_NSAL0720Scrn00_PageNext(NSAL0720CMsg cMsg, NSAL0720SMsg sMsg) {

        NSAL0720CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        NSAL0720_ACMsgArray acMsgArray = cMsg.A;
        int pageTo = cMsg.xxPageShowToNum.getValueInt();
        int rowNum = cMsg.A.no(cMsg.A.getValidCount() - 1).xxRowNum_A.getValueInt() + 1;
        int cMsgCnt = 0;
        ZYPTableUtil.clear(acMsgArray);

        for (; cMsgCnt < acMsgArray.length();) {
            if (rowNum < sMsg.A.getValidCount()) {
                if (!ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(rowNum).xxBtnFlg.getValue())) {
                    EZDMsg.copy(sMsg.A.no(rowNum), null, acMsgArray.no(cMsgCnt), null);
                    ++cMsgCnt;
                }
                ++rowNum;
            } else {
                break;
            }
        }
        acMsgArray.setValidCount(cMsgCnt);
        cMsg.xxPageShowFromNum.setValue(pageTo + 1);
        cMsg.xxPageShowToNum.setValue(pageTo + acMsgArray.getValidCount());
    }

    private void doProcess_NSAL0720Scrn00_PagePrev(NSAL0720CMsg cMsg, NSAL0720SMsg sMsg) {

        NSAL0720CommonLogic.copyCurrentPageToSMsg(cMsg, sMsg);
        NSAL0720_ACMsgArray acMsgArray = cMsg.A;

        int pageFrom = cMsg.xxPageShowFromNum.getValueInt() - acMsgArray.length() - 1;
        int rowNum = pageFrom;
        int cMsgCnt = 0;
        int flgCnt = 0;
        ZYPTableUtil.clear(acMsgArray);

        for (int bfrFrom = 0; bfrFrom < pageFrom; bfrFrom++) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(bfrFrom).xxBtnFlg.getValue())) {
                ++flgCnt;
            }
        }
        rowNum = rowNum + flgCnt;
        for (; cMsgCnt < acMsgArray.length();) {
            if (rowNum < sMsg.A.getValidCount()) {
                if (!ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(rowNum).xxBtnFlg.getValue())) {
                    EZDMsg.copy(sMsg.A.no(rowNum), null, acMsgArray.no(cMsgCnt), null);
                    ++cMsgCnt;
                }
                ++rowNum;
            } else {
                break;
            }
        }
        acMsgArray.setValidCount(cMsgCnt);

        pageFrom = pageFrom + 1;
        cMsg.xxPageShowFromNum.setValue(pageFrom);
        cMsg.xxPageShowToNum.setValue(pageFrom + acMsgArray.getValidCount() - 1);
    }
    // mod end 2016/11/08 CSA QC#4210
}
