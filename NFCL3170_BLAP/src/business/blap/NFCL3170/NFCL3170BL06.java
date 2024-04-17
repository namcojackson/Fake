package business.blap.NFCL3170;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NFCL3170.common.NFCL3170CommonLogic;
import business.blap.NFCL3170.constant.NFCL3170Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BANK_ACCT_TP;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * bank Account Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/08/2015   Fujitsu         T.Tanaka        Create          Initial
 * 03/21/2016   Fujitsu         T.Tanaka        Update          Def#5743
 * 05/27/2016   Fujitsu         S.Fujita        Update          QC#8534
 * 01/12/2018   Fujitsu         T.Murai         Update          QC#21290
 *</pre>
 */

public class NFCL3170BL06 extends S21BusinessHandler implements NFCL3170Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NFCL3170Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NFCL3170Scrn00_CMN_Submit(cMsg, sMsg);

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
    private void doProcess_NFCL3170Scrn00_CMN_Submit(EZDCMsg cMsg, EZDSMsg sMsg) {

        NFCL3170CMsg bizMsg = (NFCL3170CMsg) cMsg;
        NFCL3170SMsg globalMsg = (NFCL3170SMsg) sMsg;

        if(BigDecimal.ONE.compareTo(bizMsg.xxRadioBtn.getValue()) == 0) {
            bizMsg.dsBankAcctTpCd_H1.setValue(DS_BANK_ACCT_TP.EXTERNAL);
        } else {
            bizMsg.dsBankAcctTpCd_H1.setValue(DS_BANK_ACCT_TP.INTERNAL);
        }

        // START 2016/05/27 S.Fujita [QC#8534,DEL]
        //Def#5743
//        if(!NFCL3170CommonLogic.checkDsBank(bizMsg)) {
//            return;
//        }
        // END   2016/05/27 S.Fujita [QC#8534,DEL]

        // START 2018/01/12 [QC#21290,ADD]
        // Check Customer Relation
        if (!NFCL3170CommonLogic.checkCustReln(bizMsg)) {
            return;
        }
        // END   2018/01/12 [QC#21290,ADD]

        if(ZYPCommonFunc.hasValue(bizMsg.dsBankAcctPk_H1.getValue())) {
            if(!NFCL3170CommonLogic.updateDSBankAcct(bizMsg)) {
                return; 
            }
            if(ZYPCommonFunc.hasValue(bizMsg.dsBankBrPk_H1.getValue())) {
                if(!NFCL3170CommonLogic.updateDSBankBr(bizMsg)) {
                    return;
                }
            }
            if(bizMsg.dsBankAcctTpCd_H1.getValue().equals(DS_BANK_ACCT_TP.EXTERNAL)) {
                for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
                    if(ZYPCommonFunc.hasValue(bizMsg.A.no(i).dsCustBankAcctRelnPk_A1.getValue())) {
                        if(!NFCL3170CommonLogic.updateDsCustBankAcctReln(bizMsg, i)) {
                            return;
                        }
                    } else {
                        if(!NFCL3170CommonLogic.createDsCustBankAcctReln(bizMsg, i)) {
                            return;
                        }
                    }
                }
                
                if(!NFCL3170CommonLogic.deleteDsCustBankAcctReln2(bizMsg, globalMsg)) {
                    return;
                }
            } else {
                for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
                    if(ZYPCommonFunc.hasValue(bizMsg.A.no(i).dsCustBankAcctRelnPk_A1.getValue())) {
                        if(!NFCL3170CommonLogic.deleteDsCustBankAcctReln(bizMsg, i)) {
                            return;
                        }
                    }
                }
            }
        } else {
            // START 2016/05/27 S.Fujita [QC#8534,ADD]
            if(!NFCL3170CommonLogic.checkDsBank(bizMsg)) {
                return;
            }
            // END   2016/05/27 S.Fujita [QC#8534,ADD]
            if(!NFCL3170CommonLogic.createDSBankBr(bizMsg)) {
                return;
            }
            if(!NFCL3170CommonLogic.createDSBankAcct(bizMsg)) {
                return; 
            }
            if(bizMsg.dsBankAcctTpCd_H1.getValue().equals(DS_BANK_ACCT_TP.EXTERNAL)) {
                for(int i = 0; i < bizMsg.A.getValidCount(); i++) {
                    if(!NFCL3170CommonLogic.createDsCustBankAcctReln(bizMsg, i)) {
                        return;
                    }
                }
            }
        }

        bizMsg.setMessageInfo("NZZM0002I");
    }

}
