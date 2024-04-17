package business.blap.NFCL3170;

import java.math.BigDecimal;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;

import business.blap.NFCL3170.common.NFCL3170CommonLogic;
import business.blap.NFCL3170.constant.NFCL3170Constant;
import business.db.CNTYTMsg;
import business.db.DS_BANK_BRTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BANK_ACCT_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * bank Account Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/08/2015   Fujitsu         T.Tanaka        Create          Initial
 * 02/01/2016   Fujitsu         T.Tanaka        Update          Def#2568
 * 02/08/2016   Fujitsu         T.Tanaka        Update          Def#2600 Change Layout
 * 02/09/2023   Hitachi         R.Takau         Update          QC#55645
 *</pre>
 */

public class NFCL3170BL02 extends S21BusinessHandler implements NFCL3170Constant {
   
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NFCL3170_INIT".equals(screenAplID)) {
                doProcess_NFCL3170_INIT(cMsg, sMsg);
//            } else if ("NFCL3170Scrn00_SelectBank".equals(screenAplID)) {
//                doProcess_NFCL3170Scrn00_SelectBank(cMsg, sMsg);
//            } else if ("NFCL3170Scrn00_SelectBranch".equals(screenAplID)) {
//                doProcess_NFCL3170Scrn00_SelectBranch(cMsg, sMsg);
            } else if ("NFCL3170Scrn00_Click_SetCustomerName".equals(screenAplID)) {
//            } else if ("NFCL3170Scrn00_New".equals(screenAplID)) {
//                doProcess_NFCL3170Scrn00_New(cMsg, sMsg);
            } else if ("NFCL3170Scrn00_Add".equals(screenAplID)) {
                doProcess_NFCL3170Scrn00_Add(cMsg, sMsg);
            } else if ("NFCL3170Scrn00_Delete".equals(screenAplID)) {
                doProcess_NFCL3170Scrn00_Delete(cMsg, sMsg);
            } else if ("NFCL3170Scrn00_OnChangeRadio".equals(screenAplID)) {
                doProcess_NFCL3170Scrn00_OnChangeRadio(cMsg, sMsg);
            } else if ("NFCL3170Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFCL3170Scrn00_CMN_Submit(cMsg, sMsg);
            } else if ("NFCL3170Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NFCL3170Scrn00_CMN_Clear(cMsg, sMsg);
            } else if ("NFCL3170Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NFCL3170Scrn00_CMN_Reset(cMsg, sMsg);
            } else if ("NFCL3170_NMAL6760".equals(screenAplID)) {
                doProcess_NFCL3170_NMAL6760(cMsg, sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * 
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3170_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {
        
        NFCL3170CMsg bizMsg = (NFCL3170CMsg) cMsg;
        NFCL3170SMsg globalMsg = (NFCL3170SMsg) sMsg;
        
        globalMsg.B.clear();
        globalMsg.B.setValidCount(0);

        bizMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        bizMsg.procDt.setValue(ZYPDateUtil.getSalesDate(bizMsg.glblCmpyCd.getValue()));
        
//        NFCL3170CommonLogic.createPulldownListDsBank(bizMsg);
//        NFCL3170CommonLogic.createPulldownListArRcptSrc(bizMsg);
//        NFCL3170CommonLogic.createPulldownListArLockBox(bizMsg);
//        NFCL3170CommonLogic.createPulldownListCcy(bizMsg);
//        NFCL3170CommonLogic.createPulldownListCtry(bizMsg);
        NFCL3170CommonLogic.createPulldownListSt(bizMsg);
        
        GLBL_CMPYTMsg glblCmpyTMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(glblCmpyTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        glblCmpyTMsg = (GLBL_CMPYTMsg) EZDTBLAccessor.findByKey(glblCmpyTMsg);
        if (glblCmpyTMsg!=null) {
            ZYPEZDItemValueSetter.setValue(bizMsg.ctryCd_H1, glblCmpyTMsg.ctryCd.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.ccyCd_H1, glblCmpyTMsg.stdCcyCd.getValue());
        }
       
        if(ZYPCommonFunc.hasValue(bizMsg.dsBankAcctPk_H1.getValue())) {
            NFCL3170CommonLogic.doSerch(bizMsg);
            // Set Radio Button
            if(bizMsg.dsBankAcctTpCd_H1.getValue().equals(DS_BANK_ACCT_TP.EXTERNAL)) {
                bizMsg.xxRadioBtn.setValue(BigDecimal.ONE);
            } else {
                bizMsg.xxRadioBtn.setValue(new BigDecimal(2));
            }
        }
        //START 2023/02/09 R.Takau [QC#55645,ADD]
        if(ZYPCommonFunc.hasValue(bizMsg.billToCustCd.getValue())) {
            Map<String, Object> resultMap = NFCL3170CommonLogic.getAccountInfo(bizMsg);
            if(resultMap != null) {
                bizMsg.xxRadioBtn.setValue(BigDecimal.ONE);
                
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(0).xxChkBox_A1, ZYPConstant.FLG_OFF_N);
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(0).dsAcctNum_A1, resultMap.get("SELL_TO_CUST_CD").toString());
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(0).dsAcctNm_A1, resultMap.get("DS_ACCT_NM").toString());
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(0).locNum_A1, resultMap.get("LOC_NUM").toString());
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(0).dsAcctCustPk_A1, (BigDecimal)resultMap.get("SELL_TO_CUST_PK"));
                bizMsg.A.setValidCount(1);

            }
        //END 2023/02/09 R.Takau [QC#55645,ADD]
        }

        if(!ZYPCommonFunc.hasValue(bizMsg.effFromDt_H1.getValue())) {
            ZYPEZDItemValueSetter.setValue(bizMsg.effFromDt_H1, bizMsg.procDt.getValue());
        }
    }
    /**
     *
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3170Scrn00_SelectBranch(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL3170CMsg bizMsg = (NFCL3170CMsg) cMsg;
        
        NFCL3170CommonLogic.clearBankBr(bizMsg);
        NFCL3170CommonLogic.clearBankAcct(bizMsg);
        NFCL3170CommonLogic.clearCustomer(bizMsg);

        DS_BANK_BRTMsg inMsg = new DS_BANK_BRTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(inMsg.bankRteNum, bizMsg.bankRteNum_H1.getValue());
        
        DS_BANK_BRTMsg outMsg = (DS_BANK_BRTMsg) EZDTBLAccessor.findByKey(inMsg);
        if(outMsg != null) {
            ZYPEZDItemValueSetter.setValue(bizMsg.dsBankBrNm_H1, outMsg.dsBankBrNm.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.firstLineAddr_H1, outMsg.firstLineAddr.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.scdLineAddr_H1, outMsg.scdLineAddr.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.ctyAddr_H1, outMsg.ctyAddr.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.cntyPk_H1, outMsg.cntyPk.getValue());
            ZYPEZDItemValueSetter.setValue(bizMsg.stCd_H1, outMsg.stCd.getValue());
            
            if(ZYPCommonFunc.hasValue(outMsg.cntyPk.getValue())) {
                CNTYTMsg inMsg2 = new CNTYTMsg();
                ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(inMsg.cntyPk, bizMsg.cntyPk_H1.getValue());
                CNTYTMsg outMsg2 = (CNTYTMsg) EZDTBLAccessor.findByKey(inMsg2);
                if(outMsg2 != null) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.cntyNm_H1, outMsg2.cntyNm.getValue());
                }
            }
        }
        // Set Effective From Date
        ZYPEZDItemValueSetter.setValue(bizMsg.effFromDt_H1, bizMsg.procDt.getValue());
        
    }

    /**
     * 
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3170Scrn00_SelectBank(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL3170CMsg bizMsg = (NFCL3170CMsg) cMsg;
        
        NFCL3170CommonLogic.createPulldownListDsBankBr(bizMsg);
        NFCL3170CommonLogic.clearBankBr(bizMsg);
        NFCL3170CommonLogic.clearBankAcct(bizMsg);
        NFCL3170CommonLogic.clearCustomer(bizMsg);
        
    }

    /**
     * 
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3170Scrn00_New(EZDCMsg cMsg, EZDSMsg sMsg) {
        NFCL3170CMsg bizMsg = (NFCL3170CMsg) cMsg;
        
        NFCL3170CommonLogic.clearBankBr(bizMsg);
        NFCL3170CommonLogic.clearBankAcct(bizMsg);
    }

    /**
     * 
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3170Scrn00_Add(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL3170CMsg bizMsg = (NFCL3170CMsg) cMsg;
        
        if(bizMsg.A.length() < (bizMsg.A.getValidCount() + 1)) {
            bizMsg.setMessageInfo("NFBM0150E", new String[] {String.valueOf(bizMsg.A.length()) });
            return;
        }
        if(!NFCL3170CommonLogic.checkAccount(bizMsg)) {
            return;
        }
        
        int no = bizMsg.A.getValidCount();

        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(no).xxChkBox_A1, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(no).dsAcctNum_A1, bizMsg.dsAcctNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(no).dsAcctNm_A1, bizMsg.dsAcctNm_H1.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(no).locNum_A1, bizMsg.locNum_H1);
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(no).dsAcctCustPk_A1, bizMsg.dsAcctCustPk_H1.getValue());

        no++;
        bizMsg.A.setValidCount(no);
        
        bizMsg.dsAcctNum_H1.clear();
        bizMsg.dsAcctNm_H1.clear();
        bizMsg.locNum_H1.clear();
    }   

    /**
     * 
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3170Scrn00_Delete(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL3170CMsg bizMsg = (NFCL3170CMsg) cMsg;
        NFCL3170SMsg globalMsg = (NFCL3170SMsg) sMsg;

        NFCL3170CommonLogic.deleteCustomer(bizMsg, globalMsg);
        
    }

    /**
     * 
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3170Scrn00_OnChangeRadio(EZDCMsg cMsg, EZDSMsg sMsg) {
        
        NFCL3170CMsg bizMsg = (NFCL3170CMsg) cMsg;
        
        bizMsg.dsAcctCustPk_H1.clear();
        bizMsg.dsAcctNm_H1.clear();
        bizMsg.dsAcctNum_H1.clear();
        bizMsg.locNum_H1.clear();
        if(BigDecimal.ONE.compareTo(bizMsg.xxRadioBtn.getValue()) != 0) {
            bizMsg.A.clear();
            bizMsg.A.setValidCount(0);
        }
    }

    /**
     * 
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3170Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {
        
        NFCL3170CMsg bizMsg = (NFCL3170CMsg) cMsg;
//        if(bizMsg.xxModeCd.getValue().equals(NEW_BUTTON_ON)) {
//            // After Branch Create
//            NFCL3170CommonLogic.createPulldownListDsBankBr(bizMsg);
//            bizMsg.bankRteNum_H1.setValue(bizMsg.bankRteNum_H2.getValue());
//        }
        
        doProcess_NFCL3170_INIT(cMsg, sMsg);
    }

    /**
     * 
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3170Scrn00_CMN_Clear(EZDCMsg cMsg, EZDSMsg sMsg) {
        
        NFCL3170CMsg bizMsg = (NFCL3170CMsg) cMsg;

        NFCL3170CommonLogic.clearNew(bizMsg);
    }

    /**
     * 
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3170Scrn00_CMN_Reset(EZDCMsg cMsg, EZDSMsg sMsg) {
        
        NFCL3170CMsg bizMsg = (NFCL3170CMsg) cMsg;

        NFCL3170CommonLogic.doSerch(bizMsg);
    }

    /**
     * 
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_NFCL3170_NMAL6760(EZDCMsg cMsg, EZDSMsg sMsg) {
        
        NFCL3170CMsg bizMsg = (NFCL3170CMsg) cMsg;

        SELL_TO_CUSTTMsg inMsg = new SELL_TO_CUSTTMsg();
        inMsg.setSQLID("003");
        inMsg.setConditionValue("glblCmpyCd01", bizMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("sellToCustCd01", bizMsg.dsAcctNum_H1.getValue());
        
        SELL_TO_CUSTTMsgArray outMsg = (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if(outMsg.getValidCount() > 0) {
            ZYPEZDItemValueSetter.setValue(bizMsg.dsAcctCustPk_H1, outMsg.no(0).sellToCustPk.getValue());
        }
    }
}
