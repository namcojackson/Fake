/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFDL0160;

import static business.blap.NFDL0160.constant.NFDL0160Constant.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFDL0160.common.NFDL0160CommonLogic;
import business.db.CLT_PTFOTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Collector Portfolio Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/17   Fujitsu         C.Naito         Create          N/A
 * 2016/06/03   Fujitsu         S.Yoshida       Update          CSA QC#8887
 * 2016/08/09   Hitachi         K.Kojima        Update          QC#13129
 * 2018/02/28   Hitachi         J.Kim           Update          QC#20944
 * 2018/08/03   Fujitsu         T.Ogura         Update          QC#25899
 *</pre>
 */
public class NFDL0160BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NFDL0160CMsg bizMsg = (NFDL0160CMsg) cMsg;
            NFDL0160SMsg glblMsg = (NFDL0160SMsg) sMsg;

            if ("NFDL0160Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFDL0160Scrn00_CMN_Submit(bizMsg, glblMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NFDL0160Scrn00_CMN_Submit(NFDL0160CMsg bizMsg, NFDL0160SMsg glblMsg) {
        boolean errorExists = false;

        // START 2018/08/03 T.Ogura [QC#25899,ADD]
        /** CHECK Item chenge **/
        List<BigDecimal> cltPtfoCorNmChangePkList = new ArrayList<BigDecimal>();
        List<BigDecimal> rowChangePkList = new ArrayList<BigDecimal>();
        boolean rowChengeFlg = false;
        int index = 0;
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).cltPtfoPk_A)) {
                if (!bizMsg.A.no(i).cltPtfoNm_A.getValue().equals(glblMsg.A.no(index).cltPtfoNm_A.getValue())
                        || !bizMsg.A.no(i).cltPtfoDescTxt_A.getValue().equals(glblMsg.A.no(index).cltPtfoDescTxt_A.getValue())
                        || !bizMsg.A.no(i).cltPtfoCorNm_A.getValue().equals(glblMsg.A.no(index).cltPtfoCorNm_A.getValue())
                        || !bizMsg.A.no(i).cltPsnCd_A.getValue().equals(glblMsg.A.no(index).cltPsnCd_A.getValue())
                        || !bizMsg.A.no(i).cltStmtPhoNum_A.getValue().equals(glblMsg.A.no(index).cltStmtPhoNum_A.getValue())
                        || !bizMsg.A.no(i).cltStmtFaxNum_A.getValue().equals(glblMsg.A.no(index).cltStmtFaxNum_A.getValue())
                        || !bizMsg.A.no(i).arAdjTpCd_A.getValue().equals(glblMsg.A.no(index).arAdjTpCd_A.getValue())
                        || !bizMsg.A.no(i).cltCrAnlstEquipPsnCd_A.getValue().equals(glblMsg.A.no(index).cltCrAnlstEquipPsnCd_A.getValue())
                        || !bizMsg.A.no(i).cltCrAnlstSvcPsnCd_A.getValue().equals(glblMsg.A.no(index).cltCrAnlstSvcPsnCd_A.getValue())
                        || !bizMsg.A.no(i).cltCrAnlstSplyPsnCd_A.getValue().equals(glblMsg.A.no(index).cltCrAnlstSplyPsnCd_A.getValue())
                        || !bizMsg.A.no(i).cltPtfoNm_AR.getValue().equals(glblMsg.A.no(index).cltPtfoNm_AR.getValue())
                        || !bizMsg.A.no(i).cltPtfoStsFlg_A.getValue().equals(glblMsg.A.no(index).cltPtfoStsFlg_A.getValue())
                        ) {
                    rowChengeFlg = true;
                    rowChangePkList.add(bizMsg.A.no(i).cltPtfoPk_A.getValue());
                    if (!bizMsg.A.no(i).cltPtfoNm_A.getValue().equals(glblMsg.A.no(index).cltPtfoNm_A.getValue())) {
                        cltPtfoCorNmChangePkList.add(bizMsg.A.no(i).cltPtfoPk_A.getValue());
                    }
                }
                index++;
            } else {
                rowChengeFlg = true;
            }
        }

        if (!rowChengeFlg) {
            bizMsg.setMessageInfo(NFDM0051E);
            return;
        }

        /** CHECK Duplicate Portfolio Name(on Screen) **/
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            String cltPtfoNm = bizMsg.A.no(i).cltPtfoNm_A.getValue();
            for (int j = 0; j < bizMsg.A.getValidCount(); j++) {
                if (i != j && cltPtfoNm.equals(bizMsg.A.no(j).cltPtfoNm_A.getValue())) {
                    bizMsg.A.no(i).cltPtfoNm_A.setErrorInfo(1, NFDM0019E, new String[] {"Portfolio Name"});
                    errorExists = true;
                    break;
                }
            }
        }

        /** CHECK Duplicate Portfolio Name(on Database) **/
        List<Map<String, Object>> cltPtfoNmInfoList = null;
        S21SsmEZDResult result = NFDL0160Query.getInstance().getCltPtfoNmInfo(getGlobalCompanyCode());
        if (result.isCodeNormal()) {
            cltPtfoNmInfoList = (List<Map<String, Object>>) result.getResultObject();
        }
        if (cltPtfoNmInfoList != null && cltPtfoNmInfoList.size() > 0) {
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                BigDecimal cltPtfoPk = bizMsg.A.no(i).cltPtfoPk_A.getValue();
                String cltPtfoNm = bizMsg.A.no(i).cltPtfoNm_A.getValue();
                for (Map<String, Object> cltPtfoNmInfoMap: cltPtfoNmInfoList) {
                    BigDecimal dbCltPtfoPk = (BigDecimal) cltPtfoNmInfoMap.get("CLT_PTFO_PK");
                    String dbCltPtfoNm = (String) cltPtfoNmInfoMap.get("CLT_PTFO_NM");
                    if (!ZYPCommonFunc.hasValue(cltPtfoPk) || (ZYPCommonFunc.hasValue(cltPtfoPk) && cltPtfoPk.compareTo(dbCltPtfoPk) != 0)) {
                        if (!cltPtfoCorNmChangePkList.contains(dbCltPtfoPk) && cltPtfoNm.equals(dbCltPtfoNm)) {
                            bizMsg.A.no(i).cltPtfoNm_A.setErrorInfo(1, NFDM0019E, new String[] {"Portfolio Name"});
                            errorExists = true;
                            break;
                        }
                    }
                }
            }
        }

        if (errorExists) {
            // error exists
            return;
        }
        // END   2018/08/03 T.Ogura [QC#25899,ADD]

        // START 2016/08/09 K.Kojima [QC#13129,ADD]
        ArrayList<BigDecimal> pkArrayList = new ArrayList<BigDecimal>(bizMsg.A.getValidCount());
        // END 2016/08/09 K.Kojima [QC#13129,ADD]

        /** CHECK RelationCltPtfo_Pk is choose from popup **/
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            // START 2016/08/09 K.Kojima [QC#13129,ADD]
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).cltPtfoPk_A)) {
                pkArrayList.add(bizMsg.A.no(i).cltPtfoPk_A.getValue());
            }
            // END 2016/08/09 K.Kojima [QC#13129,ADD]
            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).cltPtfoNm_AR)) {
                String scrnRelCltPtfoNm = bizMsg.A.no(i).cltPtfoNm_AR.getValue();
                String dbRelCltPtfoNm = glblMsg.A.no(i).cltPtfoNm_AR.getValue();

                if (scrnRelCltPtfoNm.equals(dbRelCltPtfoNm)) {
                    continue;
                }
                // RelCtlPtfoNm is edited by this screen

                BigDecimal scrnRelCltPtfoPk = null;
                if (scrnRelCltPtfoNm.equals(bizMsg.A.no(i).cltPtfoNm_BK.getValue())) {
                    scrnRelCltPtfoPk = bizMsg.A.no(i).relCltPtfoPk_A.getValue();
                }

                BigDecimal relCltPtfoPkEditByScrn = NFDL0160CommonLogic.getRelCltPtfoNmIsExistDBData(scrnRelCltPtfoPk, scrnRelCltPtfoNm);
                if (BigDecimal.ONE.negate().equals(relCltPtfoPkEditByScrn)) {
                    // user edit scrnRelCltPtfoNm -> NotExist Database, or SomeExist Database.
                    bizMsg.A.no(i).cltPtfoNm_AR.setErrorInfo(1, NFDM0023E);
                    errorExists = true;
                    continue;
                }

                // user edit scrnRelCltPtfoNm -> Exist Database.
                // Set return data -> RelationPK
                bizMsg.A.no(i).relCltPtfoPk_A.setValue(relCltPtfoPkEditByScrn);
                bizMsg.A.no(i).cltPtfoNm_BK.setValue(scrnRelCltPtfoNm);
            }
        }

        if (errorExists) {
            // error exists
            return;
        }

        /** CHECK RelationCltPtfo_Pk can not set this CltPtfo_Pk (circulate check) **/
        /** CHECK RelationCltPtfo_Pk can not set Status Inactive CltPtfo_Pk **/
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {

            if (!ZYPCommonFunc.hasValue(bizMsg.A.no(i).cltPtfoPk_A)) {
                // this data is new data
                continue;
            }

            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).relCltPtfoPk_A)) {
                BigDecimal scrnRelCltPtfoPk = bizMsg.A.no(i).relCltPtfoPk_A.getValue();
                BigDecimal scrnCltPtfoPk = bizMsg.A.no(i).cltPtfoPk_A.getValue();

                if (0 == scrnRelCltPtfoPk.compareTo(scrnCltPtfoPk)) {
                    // errorMessage: can not set RelCltPtfoPk = CltPtfoPk
                    bizMsg.A.no(i).cltPtfoNm_AR.setErrorInfo(1, NFDM0026E);
                    errorExists = true;
                    continue;
                }

                boolean checkContinue = true;
                BigDecimal relCtlPtfoPk = scrnRelCltPtfoPk;
                while (checkContinue) {
                    // search relCltPtfoPk's CltPtfoData
                    CLT_PTFOTMsg searchCond = new CLT_PTFOTMsg();
                    searchCond.glblCmpyCd.setValue(getGlobalCompanyCode());
                    searchCond.cltPtfoPk.setValue(relCtlPtfoPk);
                    CLT_PTFOTMsg reqTMsg = (CLT_PTFOTMsg) S21FastTBLAccessor.findByKey(searchCond);

                    if (reqTMsg == null) {
                        // errorMessage: this record does not exist
                        bizMsg.A.no(i).cltPtfoNm_AR.setErrorInfo(1, NFDM0027E);
                        errorExists = true;
                        checkContinue = false;
                        break;
                    }

                    if (!ZYPCommonFunc.hasValue(reqTMsg.relCltPtfoPk)) {
                        // this data is no problem !
                        checkContinue = false;
                        break;
                    }

                    if (ZYPConstant.FLG_OFF_N.equals(reqTMsg.cltPtfoStsFlg.getValue())) {
                        // errorMessage: this record status inactive
                        bizMsg.A.no(i).cltPtfoNm_AR.setErrorInfo(1, NFDM0030E);
                        errorExists = true;
                        checkContinue = false;
                        break;

                    }

                    BigDecimal searchRelCltPtfoPk = reqTMsg.relCltPtfoPk.getValue();

                    if (0 == relCtlPtfoPk.compareTo(searchRelCltPtfoPk)) {
                        // errorMessage: Data set up Error
                        bizMsg.A.no(i).cltPtfoNm_AR.setErrorInfo(1, NFDM0028E);
                        errorExists = true;
                        checkContinue = false;
                        break;
                    }

                    if (0 == scrnCltPtfoPk.compareTo(searchRelCltPtfoPk)) {
                        // errorMessage: RelationCltPtfo_Pk can not set this CltPtfo (circulate check)
                        bizMsg.A.no(i).cltPtfoNm_AR.setErrorInfo(1, NFDM0029E);
                        errorExists = true;
                        checkContinue = false;
                        break;
                    }

                    // next SearchCondition...
                    relCtlPtfoPk = searchRelCltPtfoPk;
                }
            }
        }

        if (errorExists) {
            // error exists
            return;
        }

        // START 2016/08/09 K.Kojima [QC#13129,ADD]
        /** Check status : active -> inactive */
        BigDecimal[] pkList = (BigDecimal[]) pkArrayList.toArray(new BigDecimal[0]);
        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NFDL0160_ACMsg checkMsg = bizMsg.A.no(i);
            if (!ZYPCommonFunc.hasValue(checkMsg.cltPtfoPk_A)) {
                continue;
            }
            if (ZYPConstant.FLG_OFF_N.equals(checkMsg.cltPtfoStsFlg_BK.getValue())) {
                continue;
            }
            if (ZYPConstant.FLG_ON_Y.equals(checkMsg.cltPtfoStsFlg_A.getValue()) && ZYPConstant.FLG_ON_Y.equals(checkMsg.cltPtfoStsFlg_BK.getValue())) {
                continue;
            }
            String glblCmpyCd = getGlobalCompanyCode();
            BigDecimal cltPtfoPk = checkMsg.cltPtfoPk_A.getValue();
            // CLT_PTFO.REL_CLT_PTFO_PK(Screen)
            boolean subErrorExists = false;
            for (int subi = 0; subi < bizMsg.A.getValidCount(); subi++) {
                NFDL0160_ACMsg subMsg = bizMsg.A.no(subi);
                if (!ZYPConstant.FLG_ON_Y.equals(subMsg.cltPtfoStsFlg_A.getValue())) {
                    continue;
                }
                if (ZYPCommonFunc.hasValue(subMsg.relCltPtfoPk_A) && subMsg.relCltPtfoPk_A.getValue().compareTo(cltPtfoPk) == 0) {
                    checkMsg.cltPtfoStsFlg_A.setErrorInfo(1, NFDM0037E);
                    errorExists = true;
                    subErrorExists = true;
                    break;
                }
            }
            if (subErrorExists) {
                continue;
            }
            // CLT_PTFO.REL_CLT_PTFO_PK(DB)
            BigDecimal countCltPtfo = NFDL0160Query.getInstance().getCountCltPtfo(glblCmpyCd, cltPtfoPk, pkList);
            if (countCltPtfo.compareTo(BigDecimal.ZERO) != 0) {
                checkMsg.cltPtfoStsFlg_A.setErrorInfo(1, NFDM0037E);
                errorExists = true;
                continue;
            }
            // SELL_TO_CUST
            BigDecimal countSellToCust = NFDL0160Query.getInstance().getCountSellToCust(glblCmpyCd, cltPtfoPk);
            if (countSellToCust.compareTo(BigDecimal.ZERO) != 0) {
                checkMsg.cltPtfoStsFlg_A.setErrorInfo(1, NFDM0036E, new String[] {"SELL_TO_CUST" });
                errorExists = true;
                continue;
            }
            // DS_ACCT_PROS
            BigDecimal countDsAcctPros = NFDL0160Query.getInstance().getCountDsAcctPros(glblCmpyCd, cltPtfoPk);
            if (countDsAcctPros.compareTo(BigDecimal.ZERO) != 0) {
                checkMsg.cltPtfoStsFlg_A.setErrorInfo(1, NFDM0036E, new String[] {"DS_ACCT_PROS" });
                errorExists = true;
                continue;
            }
            // BILL_TO_CUST
            BigDecimal countBillToCust = NFDL0160Query.getInstance().getCountBillToCust(glblCmpyCd, cltPtfoPk);
            if (countBillToCust.compareTo(BigDecimal.ZERO) != 0) {
                checkMsg.cltPtfoStsFlg_A.setErrorInfo(1, NFDM0036E, new String[] {"BILL_TO_CUST" });
                errorExists = true;
                continue;
            }
        }

        if (errorExists) {
            // error exists
            return;
        }
        // END 2016/08/09 K.Kojima [QC#13129,ADD]

        // Data update
        // START 2018/08/03 T.Ogura [QC#25899,MOD]
//        update(bizMsg, glblMsg);
        update(bizMsg, rowChangePkList);
        // END   2018/08/03 T.Ogura [QC#25899,MOD]
    }

    /**
     * update
     * @param bizMsg Business Component Interface Message
     * @param rowChangePkList Row Change Collector Portfolio PK List
     */
    // START 2018/08/03 T.Ogura [QC#25899,MOD]
//    private void update(NFDL0160CMsg bizMsg, NFDL0160SMsg glblMsg) {
    private void update(NFDL0160CMsg bizMsg, List<BigDecimal> rowChangePkList) {
    // END   2018/08/03 T.Ogura [QC#25899,MOD]

        // create insert / update object
        List<CLT_PTFOTMsg> insList = new ArrayList<CLT_PTFOTMsg>(bizMsg.A.getValidCount());
        List<CLT_PTFOTMsg> updList = new ArrayList<CLT_PTFOTMsg>(bizMsg.A.getValidCount());

        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
            NFDL0160_ACMsg acMsg = bizMsg.A.no(i);

            // Exclusion check
            if (ZYPCommonFunc.hasValue(acMsg.cltPtfoPk_A)) {
                // START 2018/08/03 T.Ogura [QC#25899,ADD]
                if (!rowChangePkList.contains((BigDecimal) acMsg.cltPtfoPk_A.getValue())) {
                   continue;
                }
                // END   2018/08/03 T.Ogura [QC#25899,ADD]

                CLT_PTFOTMsg updTMsg = new CLT_PTFOTMsg();
                updTMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
                updTMsg.cltPtfoPk.setValue(acMsg.cltPtfoPk_A.getValue());

                updTMsg = (CLT_PTFOTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(updTMsg);
                if (updTMsg == null) {
                    bizMsg.setMessageInfo(NZZM0003E);
                    return;
                }

                if (!ZYPDateUtil.isSameTimeStamp(acMsg.ezUpTime_A.getValue(), acMsg.ezUpTimeZone_A.getValue(), updTMsg.ezUpTime.getValue(), updTMsg.ezUpTimeZone.getValue())) {
                    // Exclusion Error
                    bizMsg.setMessageInfo(NZZM0003E);
                    return;
                }

                // if changed -> for Update
                if (acMsg.cltPtfoNm_A.getValue().equals(updTMsg.cltPtfoNm.getValue()) || acMsg.cltPtfoDescTxt_A.getValue().equals(updTMsg.cltPtfoDescTxt.getValue()) || acMsg.cltPtfoCorNm_A.getValue().equals(updTMsg.cltPtfoCorNm.getValue())
                        || acMsg.cltPsnCd_A.getValue().equals(updTMsg.cltPsnCd.getValue()) || acMsg.cltPsnNm_A.getValue().equals(updTMsg.cltPsnNm.getValue()) || acMsg.cltStmtPhoNum_A.getValue().equals(updTMsg.cltStmtPhoNum.getValue())
                        || acMsg.cltStmtFaxNum_A.getValue().equals(updTMsg.cltStmtFaxNum.getValue()) || acMsg.arAdjTpCd_A.getValue().equals(updTMsg.arAdjTpCd.getValue())
                        // START 2018/02/27 J.Kim [QC#20944,DEL]
                        //|| acMsg.writeOffApvlLimitAmt_A.getValue().equals(updTMsg.writeOffApvlLimitAmt.getValue()) || acMsg.cltCrAnlstEquipPsnCd_A.getValue().equals(updTMsg.cltCrAnlstEquipPsnCd.getValue())
                        // END 2018/02/27 J.Kim [QC#20944,DEL]
                        || acMsg.cltCrAnlstEquipPsnNm_A.getValue().equals(updTMsg.cltCrAnlstEquipPsnNm.getValue()) || acMsg.cltCrAnlstSvcPsnCd_A.getValue().equals(updTMsg.cltCrAnlstSvcPsnCd.getValue())
                        || acMsg.cltCrAnlstSvcPsnNm_A.getValue().equals(updTMsg.cltCrAnlstSvcPsnNm.getValue()) || acMsg.cltCrAnlstSplyPsnCd_A.getValue().equals(updTMsg.cltCrAnlstSplyPsnCd.getValue())
                        || acMsg.cltCrAnlstSplyPsnNm_A.getValue().equals(updTMsg.cltCrAnlstSplyPsnNm.getValue()) || acMsg.relCltPtfoPk_A.getValue().equals(updTMsg.relCltPtfoPk.getValue())
                        || acMsg.cltPtfoStsFlg_A.getValue().equals(updTMsg.cltPtfoStsFlg.getValue())) {
                    NFDL0160CommonLogic.setUpdRegistDataToCLT_PTFOTMsg(bizMsg, acMsg, updTMsg);
                    updList.add(updTMsg);
                }

            } else {
                // new data -> for Insert
                CLT_PTFOTMsg insTMsg = new CLT_PTFOTMsg();
                NFDL0160CommonLogic.setInsRegistDataToCLT_PTFOTMsg(bizMsg, acMsg, insTMsg, getGlobalCompanyCode());
                insList.add(insTMsg);
            }
        }
        // insertList
        if (!insList.isEmpty()) {
            int insCnt = S21FastTBLAccessor.insert(insList.toArray(new CLT_PTFOTMsg[insList.size()]));
            if (insCnt != insList.size()) {
                bizMsg.setMessageInfo(NFDM0013E, new String[] {"CLT_PTFO" });
                return;
            }
        }
        // updateList
        if (!updList.isEmpty()) {
            int updCnt = S21FastTBLAccessor.update(updList.toArray(new CLT_PTFOTMsg[0]));
            if (updCnt != updList.size()) {
                bizMsg.setMessageInfo(NFDM0004E, new String[] {"CLT_PTFO" });
                return;
            }
        }

    }

}
