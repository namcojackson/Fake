/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0410.common;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCStringItemArray;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0410.NSAL0410CMsg;
import business.blap.NSAL0410.NSAL0410Query;
import business.blap.NSAL0410.NSAL0410_ACMsg;
import business.blap.NSAL0410.constant.NSAL0410Constant;
import business.db.ADDL_CHRG_INV_TPTMsg;
import business.db.BLLG_CYCLETMsg;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.SVC_BILL_BY_TPTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ADDL_CHRG_INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Additional Charge
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/24   Fujitsu         C.Naito         Create          N/A
 * 2015/11/26   Hitachi         T.Tomita        Update          N/A
 * 2015/12/02   Hitachi         T.Tomita        Update          QC#1319
 * 2016/02/15   Hitachi         K.Kasai         Update          QC#2874
 * 2016/02/24   Hitachi         K.Kasai         Update          QC#2570
 * 2016/04/19   Hitachi         K.Kishimoto     Update          QC#5740
 * 2016/05/24   Hitachi         Y.Takeno        Update          QC#447
 * 2017/05/26   Hitachi         Y.Osawa         Update          QC#18560
 * 2017/12/26   Hitachi         K.Kojima        Update          QC#18562
 * 2019/01/29   Hitachi         Y.Takeno        Update          QC#29949
 *</pre>
 */
public class NSAL0410CommonLogic {

    /**
     * addNewRow
     * @param cMsg NSAL0410CMsg
     * @param newAddRowIndx int
     * @param allRowNum int
     * @param glblcmpyCd String
     */
    public static void addNewRow(NSAL0410CMsg cMsg, int newAddRowIndx, int allRowNum, String glblcmpyCd) {

        for (int i = newAddRowIndx; i < allRowNum; i++) {
            // clear newRow
            cMsg.A.no(i).clear();

            // set NewRow
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).dsContrPk_A, cMsg.dsContrPk_P.getValue());
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).dsContrNum_A, cMsg.dsContrNum.getValue());
            // Mod Start 04/19/2016 <QC#5740>
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).effFromDt_A, cMsg.contrVrsnEffFromDt);
            // Mod End   04/19/2016 <QC#5740>
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).bllgCycleCd_SE, cMsg.bllgCycleCd_DF.getValue());
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).svcBillByTpCd_SE, cMsg.svcBillByTpCd_CD.no(0).getValue());
            // add start 2016/02/15 CSA Defect#2874
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).effThruDt_A, cMsg.contrVrsnEffThruDt);
            // add end 2016/02/15 CSA Defect#2874
            // START 2016/05/24  [QC#447, ADD]
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).addlChrgTpCd_SE, cMsg.addlChrgTpCd_CD.no(0).getValue());
            NSAL0410CommonLogic.setSlaTime(cMsg, i);
            // END   2016/05/24  [QC#447, ADD]
            setSerNumPulldown(cMsg, i);
            // START 2015/12/02 T.Tomita [QC#1319, ADD]
            setSvcBillByTpPulldownData(cMsg, i);
            // END 2015/12/02 T.Tomita [QC#1319, ADD]
        }
        cMsg.A.setValidCount(allRowNum);
    }

    /**
     * setSerNumPulldown
     */
    private static void setSerNumPulldown(NSAL0410CMsg cMsg, int line) {
        // Set Ser# row
        // START 2019/01/29 [QC#29949, DEL]
//      for (int i = 0; i < cMsg.dsContrDtlPk_CD.length(); i++) {
//          ZYPEZDItemValueSetter.setValue(cMsg.A.no(line).dsContrDtlPk_AC.no(i), cMsg.dsContrDtlPk_CD.no(i));
//          ZYPEZDItemValueSetter.setValue(cMsg.A.no(line).xxScrItem34Txt_AD.no(i), cMsg.xxScrItem34Txt_DI.no(i));
//      }
        // END   2019/01/29 [QC#29949, DEL]
    }

    /**
     * inputCheck Submit input ErrorCheck
     * @param cMsg NSAL0410CMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    public static boolean inputCheckNoError(NSAL0410CMsg cMsg, String glblCmpyCd) {
        boolean isNotError = true;

        /** search for Duplicate Check (Contract ADDL_CHRG_TP_CD) **/
        // Search this CONTR ADDL_CHRG_TP
        S21SsmEZDResult res = NSAL0410Query.getInstance().getThisContrEffAddlChrgTp(cMsg);
        if (!res.isCodeNotFound() && !res.isCodeNormal()) {
            cMsg.setMessageInfo(NSAL0410Constant.NSAM0348E, new String[] {"DS_CONTR" });
            return false;
        }
        List<Map<String, Object>> thisContrEffAddlChrgTpList = (List<Map<String, Object>>) res.getResultObject();

        /** search for StartDt to EndDt over Check **/
        // Search this CONTR EffDt
        DS_CONTRTMsg condTMsg0 = new DS_CONTRTMsg();
        ZYPEZDItemValueSetter.setValue(condTMsg0.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(condTMsg0.dsContrPk, cMsg.dsContrPk_P.getValue());
        DS_CONTRTMsg valueTMsg0 = (DS_CONTRTMsg) EZDTBLAccessor.findByKey(condTMsg0);
        String thisContrEffFromDt = valueTMsg0.contrVrsnEffFromDt.getValue();
        String thisContrEffThruDt = valueTMsg0.contrVrsnEffThruDt.getValue();

        // ##Check New data## //
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(cMsg.A.no(i).dsContrAddlChrgPk_A)) {
                continue;
            }

            /** this CONTR ADDL_CHRG_TP duplicate check **/
            if (DS_CONTR_CATG.FLEET.equals(cMsg.dsContrCatgCd_H.getValue()) && ZYPCommonFunc.hasValue(cMsg.dsContrDtlPk_P)) {
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).dsContrDtlPk_A, cMsg.dsContrDtlPk_P);
            }
            BigDecimal selectDsContrDtlPk = cMsg.A.no(i).dsContrDtlPk_A.getValue();
            String selectAddlChrgTp = cMsg.A.no(i).addlChrgTpCd_SE.getValue();
            String setStartDt = cMsg.A.no(i).effFromDt_A.getValue();
            String setEndTermDt = cMsg.A.no(i).effThruDt_A.getValue();
            if (ZYPCommonFunc.hasValue(cMsg.A.no(i).trmnDt_A)) {
                setEndTermDt = cMsg.A.no(i).trmnDt_A.getValue();
            }

            BigDecimal chkDsContrDtlPk;
            String chkAddlChrgTp;
            // DBADDL_CHRG_TP - NewADDL_CHRG_TP
            if (!thisContrEffAddlChrgTpList.isEmpty()) {
                String dbStartDt;
                String dbEndTermDt;
                for (Map thisContrEffAddlChrgTpMap : thisContrEffAddlChrgTpList) {
                    if (thisContrEffAddlChrgTpMap.get("DS_CONTR_DTL_PK") != null) {
                        chkDsContrDtlPk = new BigDecimal(thisContrEffAddlChrgTpMap.get("DS_CONTR_DTL_PK").toString());
                    } else {
                        chkDsContrDtlPk = null;
                    }

                    if (!isEquals(selectDsContrDtlPk, chkDsContrDtlPk)) {
                        continue;
                    }

                    chkAddlChrgTp = thisContrEffAddlChrgTpMap.get("ADDL_CHRG_TP_CD").toString();
                    if (!selectAddlChrgTp.equals(chkAddlChrgTp)) {
                        continue;
                    }

                    // Period DuplicateCheck
                    dbStartDt = thisContrEffAddlChrgTpMap.get("EFF_FROM_DT").toString();
                    dbEndTermDt = thisContrEffAddlChrgTpMap.get("EFF_THRU_TRMN_DT").toString();
                    if ((setStartDt.compareTo(dbStartDt) <= 0 && setEndTermDt.compareTo(dbStartDt) >= 0) || (setStartDt.compareTo(dbEndTermDt) <= 0 && setEndTermDt.compareTo(dbEndTermDt) >= 0)
                            || (dbStartDt.compareTo(setStartDt) <= 0 && dbEndTermDt.compareTo(setStartDt) >= 0) || (dbStartDt.compareTo(setEndTermDt) <= 0 && dbEndTermDt.compareTo(setEndTermDt) >= 0)) {
                        // DuplicateError
                        cMsg.A.no(i).xxChkBox_A.setErrorInfo(1, NSAL0410Constant.NSAM0345E);
                        isNotError = false;
                    }
                }
            }

            // NewADDL_CHRG_TP - NewADDL_CHRG_TP
            for (int k = 0; k < cMsg.A.getValidCount(); k++) {
                if (k == i) {
                    continue;
                }

                chkDsContrDtlPk = cMsg.A.no(k).dsContrDtlPk_A.getValue();
                if (!isEquals(selectDsContrDtlPk, chkDsContrDtlPk)) {
                    continue;
                }

                chkAddlChrgTp = cMsg.A.no(k).addlChrgTpCd_SE.getValue();
                if (!selectAddlChrgTp.equals(chkAddlChrgTp)) {
                    continue;
                }

                // Period DuplicateCheck
                String kStartDt = cMsg.A.no(k).effFromDt_A.getValue();
                String kEndTermDt = cMsg.A.no(k).effThruDt_A.getValue();
                if (ZYPCommonFunc.hasValue(cMsg.A.no(k).trmnDt_A)) {
                    kEndTermDt = cMsg.A.no(k).trmnDt_A.getValue();
                }
                if ((setStartDt.compareTo(kStartDt) <= 0 && setEndTermDt.compareTo(kStartDt) >= 0) || (setStartDt.compareTo(kEndTermDt) <= 0 && setEndTermDt.compareTo(kEndTermDt) >= 0)
                        || (kStartDt.compareTo(setStartDt) <= 0 && kEndTermDt.compareTo(setStartDt) >= 0) || (kStartDt.compareTo(setEndTermDt) <= 0 && kEndTermDt.compareTo(setEndTermDt) >= 0)) {
                    // DuplicateError
                    cMsg.A.no(i).xxChkBox_A.setErrorInfo(1, NSAL0410Constant.NSAM0345E);
                    cMsg.A.no(k).xxChkBox_A.setErrorInfo(1, NSAL0410Constant.NSAM0345E);
                    isNotError = false;
                }
            }

            /** BillingCycle Default compare check **/
            // NewBLLG_CYCLE - DefaultBLLG_CYCLE
            String selectBllgCycle = cMsg.A.no(i).bllgCycleCd_SE.getValue();

            BLLG_CYCLETMsg condTMsg = new BLLG_CYCLETMsg();
            ZYPEZDItemValueSetter.setValue(condTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(condTMsg.bllgCycleCd, selectBllgCycle);
            BLLG_CYCLETMsg valueTMsg = (BLLG_CYCLETMsg) S21CodeTableAccessor.findByKey(condTMsg);
            BigDecimal selectBllgCycleSortNum = valueTMsg.bllgCycleSortNum.getValue();

            if (0 < cMsg.bllgCycleSortNum_DF.getValue().compareTo(selectBllgCycleSortNum)) {
                // NewSortNum < DfSortNum Error
                cMsg.A.no(i).bllgCycleCd_SE.setErrorInfo(1, NSAL0410Constant.NSAM0344E);
                isNotError = false;
            }

            /** EffDt Compare **/
            String setEndDt = cMsg.A.no(i).effThruDt_A.getValue();
            // CONTR_DTL
            if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).dsContrDtlPk_A)) {
                if (0 < ZYPDateUtil.compare(thisContrEffFromDt, setStartDt)) {
                    // Error
                    cMsg.A.no(i).effFromDt_A.setErrorInfo(1, NSAL0410Constant.NSAM0346E, new String[] {"Start Date", "Contract Effective From Date" });
                    isNotError = false;
                }
                if (0 > ZYPDateUtil.compare(thisContrEffThruDt, setEndDt)) {
                    // Error
                    cMsg.A.no(i).effThruDt_A.setErrorInfo(1, NSAL0410Constant.NSAM0347E, new String[] {"End Date", "Contract Effective Thru Date" });
                    isNotError = false;
                }
                if (ZYPCommonFunc.hasValue(cMsg.A.no(i).trmnDt_A)) {
                    if (0 > ZYPDateUtil.compare(thisContrEffThruDt, cMsg.A.no(i).trmnDt_A.getValue())) {
                        // Error
                        cMsg.A.no(i).trmnDt_A.setErrorInfo(1, NSAL0410Constant.NSAM0347E, new String[] {"Terminate Date", "Contract Effective Thru Date" });
                        isNotError = false;
                    }
                }
            } else {
                // CONTR_DTL
                DS_CONTR_DTLTMsg condTMsg1 = new DS_CONTR_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(condTMsg1.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(condTMsg1.dsContrDtlPk, cMsg.A.no(i).dsContrDtlPk_A.getValue());
                DS_CONTR_DTLTMsg valueTMsg1 = (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKey(condTMsg1);
                String thisContrDtlEffFromDt = valueTMsg1.contrEffFromDt.getValue();
                String thisContrDtlEffThruDt = valueTMsg1.contrEffThruDt.getValue();

                if (0 < ZYPDateUtil.compare(thisContrDtlEffFromDt, setStartDt)) {
                    // Error
                    cMsg.A.no(i).effFromDt_A.setErrorInfo(1, NSAL0410Constant.NSAM0346E, new String[] {"Start Date", "Contract Effective From Date" });
                    isNotError = false;
                }
                if (0 > ZYPDateUtil.compare(thisContrDtlEffThruDt, setEndDt)) {
                    // Error
                    cMsg.A.no(i).effThruDt_A.setErrorInfo(1, NSAL0410Constant.NSAM0347E, new String[] {"End Date", "Contract Effective Thru Date" });
                    isNotError = false;
                }
                if (ZYPCommonFunc.hasValue(cMsg.A.no(i).trmnDt_A)) {
                    if (0 > ZYPDateUtil.compare(thisContrDtlEffThruDt, cMsg.A.no(i).trmnDt_A.getValue())) {
                        // Error
                        cMsg.A.no(i).trmnDt_A.setErrorInfo(1, NSAL0410Constant.NSAM0347E, new String[] {"Terminate Date", "Contract Effective Thru Date" });
                        isNotError = false;
                    }
                }
            }

            // Check Additional Charge Invoice Type
            if (isErrAddlChrgInvTp(cMsg, i, glblCmpyCd)) {
                isNotError = false;
            }
        }
        return isNotError;
    }

    /**
     * getSvcBillByTpTable for Event OnChangeBillBy
     * @param cMsg NSAL0410CMsg
     * @return boolean
     */
    public static boolean getSvcBillByTpTable(NSAL0410CMsg cMsg) {
        S21SsmEZDResult res = NSAL0410Query.getInstance().getSvcBillByTpTable(cMsg);
        if (!res.isCodeNormal()) {
            cMsg.setMessageInfo(NSAL0410Constant.NSAM0348E, new String[] {"SVC_BILL_BY_TP" });
            return false;
        }
        return true;
    }

    /**
     * checkReferAuth
     * @return referAuth boolean
     */
    public static boolean checkReferAuth() {
        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(NSAL0410Constant.BIZ_ID);
        boolean referAuth = true;
        for (String func : funcList) {
            if (NSAL0410Constant.FUNC_ID_UPDATE.equals(func)) {
                referAuth = false;
                break;
            }
        }
        return referAuth;
    }

    private static boolean isEquals(BigDecimal val1, BigDecimal val2) {
        // null = null
        if (!ZYPCommonFunc.hasValue(val1) && !ZYPCommonFunc.hasValue(val2)) {
            return true;
        }

        if ((ZYPCommonFunc.hasValue(val1) && !ZYPCommonFunc.hasValue(val2)) || (!ZYPCommonFunc.hasValue(val1) && ZYPCommonFunc.hasValue(val2))) {
            return false;
        }

        if (val1.compareTo(val2) == 0) {
            return true;
        }
        return false;
    }

    private static boolean isErrAddlChrgInvTp(NSAL0410CMsg cMsg, int row, String glblCmpyCd) {
        String addlChrgInvTpCd = cMsg.A.no(row).addlChrgInvTpCd_SE.getValue();

        ADDL_CHRG_INV_TPTMsg addlChrgInvTpTMsg = new ADDL_CHRG_INV_TPTMsg();
        ZYPEZDItemValueSetter.setValue(addlChrgInvTpTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(addlChrgInvTpTMsg.addlChrgInvTpCd, addlChrgInvTpCd);
        addlChrgInvTpTMsg = (ADDL_CHRG_INV_TPTMsg) S21CodeTableAccessor.findByKey(addlChrgInvTpTMsg);
        if (addlChrgInvTpTMsg == null) {
            cMsg.setMessageInfo(NSAL0410Constant.NSAM0348E, new String[] {"ADDL_CHRG_INV_TP" });
            return true;
        }
        String addlChrgInvTpNm = addlChrgInvTpTMsg.addlChrgInvTpDescTxt.getValue();

        BigDecimal leaseCmpyCnt;
        if (ADDL_CHRG_INV_TP.BASE.equals(addlChrgInvTpCd)) {
            // Base
            leaseCmpyCnt = NSAL0410Query.getInstance().getBaseLeaseCmpyCnt(cMsg, row);
        } else {
            // Usage
            leaseCmpyCnt = NSAL0410Query.getInstance().getUsageLeaseCmpyCnt(cMsg, row);
        }

        if (isEquals(BigDecimal.ZERO, leaseCmpyCnt)) {
            // no error
            return false;
        }

        // Search Service Bill By Type
        SVC_BILL_BY_TPTMsg msg = new SVC_BILL_BY_TPTMsg();
        ZYPEZDItemValueSetter.setValue(msg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(msg.svcBillByTpCd, cMsg.A.no(row).svcBillByTpCd_SE);
        SVC_BILL_BY_TPTMsg rtnMsg = (SVC_BILL_BY_TPTMsg) S21CodeTableAccessor.findByKey(msg);
        if (rtnMsg == null) {
            cMsg.setMessageInfo(NSAL0410Constant.NSAM0348E, new String[] {"SVC_BILL_BY_TP" });
            return true;
        }

        String chkFlg = ZYPConstant.FLG_ON_Y;
        if (ADDL_CHRG_INV_TP.BASE.equals(addlChrgInvTpCd)) {
            chkFlg = rtnMsg.baseChrgFlg.getValue();
        } else {
            chkFlg = rtnMsg.usgChrgFlg.getValue();
        }

        if (ZYPConstant.FLG_OFF_N.equals(chkFlg)) {
            cMsg.A.no(row).svcBillByTpCd_SE.setErrorInfo(1, NSAL0410Constant.NSAM0395E, new String[] {rtnMsg.svcBillByTpDescTxt.getValue(), addlChrgInvTpNm });
            return true;
        }
        // no error
        return false;
    }

    // START 2015/12/02 T.Tomita [QC#1319, ADD]
    /**
     * setMachSvcBillByTpPulldown
     * @param cMsg NSAL0410CMsg
     */
    public static void setSvcBillByTpPulldown(NSAL0410CMsg cMsg) {
        // Default Pulldown Data
        ZYPCodeDataUtil.createPulldownList("SVC_BILL_BY_TP", cMsg.svcBillByTpCd_CD, cMsg.svcBillByTpNm_DI);
        // Machine Pulldown Data
        setMachSvcBillByTpPulldown(cMsg);

        // set SVC_BILL_BY_TP Pulldown
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            setSvcBillByTpPulldownData(cMsg, i);
        }
    }

    private static void setMachSvcBillByTpPulldown(NSAL0410CMsg cMsg) {
        List<Map<String, Object>> machSvcBillByTpList = NSAL0410Query.getInstance().getMachSvcBillByTp();
        int i = 0;
        for (Map<String, Object> machSvcBillByTp : machSvcBillByTpList) {
            ZYPEZDItemValueSetter.setValue(cMsg.svcBillByTpCd_MC.no(i), (String) machSvcBillByTp.get("SVC_BILL_BY_TP_CD"));
            ZYPEZDItemValueSetter.setValue(cMsg.svcBillByTpNm_MD.no(i), (String) machSvcBillByTp.get("SVC_BILL_BY_TP_DESC_TXT"));
            i++;
        }
    }

    /**
     * setSvcBillByTpPulldownData
     * @param cMsg NSAL0410CMsg
     * @param row int
     */
    public static void setSvcBillByTpPulldownData(NSAL0410CMsg cMsg, int row) {
        String selectCd = cMsg.A.no(row).svcBillByTpCd_SE.getValue();
        boolean existFlg = false;
        if (ZYPCommonFunc.hasValue(cMsg.dsContrDtlPk_P) || ZYPCommonFunc.hasValue(cMsg.A.no(row).dsContrDtlPk_A)) {
            for (int i = 0; i < cMsg.svcBillByTpCd_MC.length(); i++) {
                if (ZYPCommonFunc.hasValue(selectCd) && selectCd.equals(cMsg.svcBillByTpCd_MC.no(i).getValue())) {
                    existFlg = true;
                }
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(row).svcBillByTpCd_AC.no(i), cMsg.svcBillByTpCd_MC.no(i));
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(row).svcBillByTpNm_AD.no(i), cMsg.svcBillByTpNm_MD.no(i));
            }
        } else {
            for (int i = 0; i < cMsg.svcBillByTpCd_CD.length(); i++) {
                if (ZYPCommonFunc.hasValue(selectCd) && selectCd.equals(cMsg.svcBillByTpCd_CD.no(i).getValue())) {
                    existFlg = true;
                }
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(row).svcBillByTpCd_AC.no(i), cMsg.svcBillByTpCd_CD.no(i));
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(row).svcBillByTpNm_AD.no(i), cMsg.svcBillByTpNm_DI.no(i));
            }
        }

        if (!existFlg) {
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(row).svcBillByTpCd_SE, cMsg.A.no(row).svcBillByTpCd_AC.no(0));
        }
    }
    // END 2015/12/02 T.Tomita [QC#1319, ADD]

    // START 2016/05/24 [QC#447, ADD]
    /**
     * setSlaTime
     * @param cMsg NSAL0410CMsg
     * @param index index
     */
    public static void setSlaTime(NSAL0410CMsg cMsg, int index) {
        if (ZYPCommonFunc.hasValue(cMsg.A.no(index).addlChrgTpCd_SE)) {
            List<Map<String, Object>> resultList = NSAL0410Query.getInstance().getSlaTime(cMsg.A.no(index).addlChrgTpCd_SE.getValue());
            if (resultList.size() > 0) {
                String intgMdseCd = (String) resultList.get(0).get("INTG_MDSE_CD");
                String termCondOptValTxt = (String) resultList.get(0).get("TERM_COND_OPT_VAL_TXT");
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(index).intgMdseCd_A, intgMdseCd);
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(index).termCondOptValTxt_A, termCondOptValTxt);
                return;
            }
        }

        cMsg.A.no(index).intgMdseCd_A.clear();
        cMsg.A.no(index).termCondOptValTxt_A.clear();
    }
    // END 2016/05/24 [QC#447, ADD]

    // START 2017/05/26 Y.Osawa [QC#18560, ADD]
    /**
     * deletePulldownList
     * @param cdArray EZDCStringItemArray Code Array
     * @param txtArray EZDCStringItemArray Text Array
     * @param delCd delete Code
     */
    public static void deletePulldownList(EZDCStringItemArray cdArray, EZDCStringItemArray txtArray, String delCd) {
        int index = -1;
        for (int i = 0; i < cdArray.length(); i++) {
            if (delCd.equals(cdArray.no(i).getValue())) {
                index = i;
                break;
            }
        }

        if (index >= 0) {
            int i = index;
            for (; i < cdArray.length() - 1; i++) {
                ZYPEZDItemValueSetter.setValue(cdArray.no(i), cdArray.no(i + 1));
                ZYPEZDItemValueSetter.setValue(txtArray.no(i), txtArray.no(i + 1));
            }
            cdArray.no(i).clear();
            txtArray.no(i).clear();
        }
    }
    // END   2017/05/26 Y.Osawa [QC#18560, ADD]

    // START 2017/12/26 K.Kojima [QC#18562,ADD]
    /**
     * setBllgCycle
     * @param cMsg NSAL0410CMsg
     * @param index int
     */
    public static void setBllgCycle(NSAL0410CMsg cMsg, int index) {
        NSAL0410_ACMsg acMsg = cMsg.A.no(index);
        BigDecimal dsContrPk = acMsg.dsContrPk_A.getValue();
        BigDecimal dsContrDtlPk = acMsg.dsContrDtlPk_A.getValue();
        String addlChrgInvTpCd = acMsg.addlChrgInvTpCd_SE.getValue();
        String bllgCycle = null;
        if (ADDL_CHRG_INV_TP.BASE.equals(addlChrgInvTpCd)) {
            bllgCycle = NSAL0410Query.getInstance().getBllgCycleForBase(dsContrPk, dsContrDtlPk);
        } else {
            bllgCycle = NSAL0410Query.getInstance().getBllgCycleForUsage(dsContrPk, dsContrDtlPk);
        }
        if (ZYPCommonFunc.hasValue(bllgCycle)) {
            ZYPEZDItemValueSetter.setValue(acMsg.bllgCycleCd_SE, bllgCycle);
        }
    }
    // END 2017/12/26 K.Kojima [QC#18562,ADD]
}
