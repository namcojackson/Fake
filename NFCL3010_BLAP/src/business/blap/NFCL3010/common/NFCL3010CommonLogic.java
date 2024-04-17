package business.blap.NFCL3010.common;

import static business.blap.NFCL3010.constant.NFCL3010Constant.PERCENT;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCBigDecimalItemArray;
import parts.common.EZDCStringItemArray;
import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NFCL3010.NFCL3010CMsg;
import business.blap.NFCL3010.NFCL3010Query;
import business.blap.NFCL3010.NFCL3010SMsg;
import business.db.DS_BANK_ACCTTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BANK_ACCT_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Batch Receipt Inquiry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 04/26/2016   Fujitsu         S.Fujita        Update          QC#3293
 * 2018/03/22   Fujitsu         H.Ikeda         Update          QC#21737
 *</pre>
 */
public class NFCL3010CommonLogic {
    /**
     * Create pull down List for initialize process. It makes
     * [Code:Value].
     * @param pulldownCd EZDCStringItemArray
     * @param pulldownName EZDCStringItemArray
     * @param pullDownList List<Map>
     * @param dbColumn String[] Modify Def 3872
     */
    private static void initPullDownCreate(EZDCStringItemArray pulldownCd, EZDCStringItemArray pulldownName, List<Map> pullDownList, String[] dbColumn) {

        pulldownCd.clear();
        pulldownName.clear();

        for (int i = 0; i < pullDownList.size(); i++) {

            Map pullDownData = pullDownList.get(i);

            pulldownCd.no(i).setValue((String) pullDownData.get(dbColumn[0]));
            pulldownName.no(i).setValue(ZYPCommonFunc.concatString((String) pullDownData.get(dbColumn[0]), ":", (String) pullDownData.get(dbColumn[1])));
        }
    }

    /**
     * Create pull down List for initialize process. It makes
     * [Code:Value].
     * @param pulldownCd EZDCStringItemArray
     * @param pulldownName EZDCStringItemArray
     * @param pullDownList List<Map>
     * @param dbColumn String[] Modify Def 3872
     */
    private static void initPullDownCreate(EZDCBigDecimalItemArray pulldownCd, EZDCStringItemArray pulldownName, List<Map> pullDownList, String[] dbColumn) {

        pulldownCd.clear();
        pulldownName.clear();

        for (int i = 0; i < pullDownList.size(); i++) {

            Map pullDownData = pullDownList.get(i);

            pulldownCd.no(i).setValue((BigDecimal) pullDownData.get(dbColumn[0]));
            pulldownName.no(i).setValue(ZYPCommonFunc.concatString((String) pullDownData.get(dbColumn[1]), ":", (String) pullDownData.get(dbColumn[2])));
        }
    }

    public static void createPulldownListBank(String glblCmpyCd, NFCL3010CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        S21SsmEZDResult dsBank = NFCL3010Query.getInstance().getBankPullDownList(bizMsg, ssmParam);

        if (dsBank.isCodeNormal()) {
            List<Map> dsBankList = (List<Map>) dsBank.getResultObject();
            initPullDownCreate(bizMsg.dsBankCd_LC, bizMsg.dsBankNm_LD, dsBankList, new String[] {"DS_BANK_CD", "DS_BANK_NM" });
        }
    }

    public static void createPulldownListBankBr(String glblCmpyCd, NFCL3010CMsg bizMsg) {

        List<Map> dsBankBrList = new ArrayList<Map>();
        if (ZYPCommonFunc.hasValue(bizMsg.dsBankCd_H)) {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("dsBankCd", bizMsg.dsBankCd_H.getValue());
            S21SsmEZDResult dsBankBr = NFCL3010Query.getInstance().getBankBrPullDownList(bizMsg, ssmParam);

            if (dsBankBr.isCodeNormal()) {
                dsBankBrList = (List<Map>) dsBankBr.getResultObject();
            }
        }
        initPullDownCreate(bizMsg.bankRteNum_LC, bizMsg.dsBankBrNm_LD, dsBankBrList, new String[] {"BANK_RTE_NUM", "DS_BANK_BR_NM" });
        createPulldownListBankAccount(glblCmpyCd, bizMsg);
    }

    public static void createPulldownListBankAccount(String glblCmpyCd, NFCL3010CMsg bizMsg) {
        List<Map> dsBankAcctList = new ArrayList<Map>();
        if (ZYPCommonFunc.hasValue(bizMsg.bankRteNum_H)) {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("dsBankCd", bizMsg.dsBankCd_H.getValue());
            ssmParam.put("bankRteNum", bizMsg.bankRteNum_H.getValue());
            ssmParam.put("dsBankAcctTpCd", DS_BANK_ACCT_TP.INTERNAL);
            S21SsmEZDResult dsBankAcct = NFCL3010Query.getInstance().getBankAccountPullDownList(bizMsg, ssmParam);

            if (dsBankAcct.isCodeNormal()) {
                dsBankAcctList = (List<Map>) dsBankAcct.getResultObject();
            }
        }
        initPullDownCreate(bizMsg.remDsBankAcctPk_LC, bizMsg.dsBankAcctNm_LD, dsBankAcctList, new String[] {"DS_BANK_ACCT_PK", "DS_BANK_ACCT_NUM", "DS_BANK_ACCT_NM" });
    }

    public static void createPulldownListBankAccountByremDsBankAcctPk(String glblCmpyCd, BigDecimal remDsBankAcctPk, NFCL3010CMsg bizMsg) {
        bizMsg.dsBankCd_H.clear();
        bizMsg.bankRteNum_H.clear();
        bizMsg.remDsBankAcctPk_H.clear();

        DS_BANK_ACCTTMsg acctInMsg = new DS_BANK_ACCTTMsg();
        ZYPEZDItemValueSetter.setValue(acctInMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(acctInMsg.dsBankAcctPk, remDsBankAcctPk);

        DS_BANK_ACCTTMsg acctOutMsg = (DS_BANK_ACCTTMsg) EZDTBLAccessor.findByKey(acctInMsg);
        if (acctOutMsg == null) {
            NFCL3010CommonLogic.createPulldownListBankBr(glblCmpyCd, bizMsg);
            NFCL3010CommonLogic.createPulldownListBankAccount(glblCmpyCd, bizMsg);
            return;
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.dsBankCd_H, acctOutMsg.dsBankCd);
            NFCL3010CommonLogic.createPulldownListBankBr(glblCmpyCd, bizMsg);
            ZYPEZDItemValueSetter.setValue(bizMsg.bankRteNum_H, acctOutMsg.bankRteNum);
            NFCL3010CommonLogic.createPulldownListBankAccount(glblCmpyCd, bizMsg);
            ZYPEZDItemValueSetter.setValue(bizMsg.remDsBankAcctPk_H, acctOutMsg.dsBankAcctPk);
        }
    }

    // START 2016/04/26 S.Fujita [QC#3293,MOD]
    /**
     * setSearchFlg
     * @param character String
     * @return searchFlg String
     */
    public static String setSearchFlg(String character) {

        String searchFlg;
        if (character.contains(PERCENT)) {
            searchFlg = ZYPConstant.FLG_ON_Y;
        } else {
            searchFlg = ZYPConstant.FLG_OFF_N;
        }

        return searchFlg;
    }
    // END 2016/04/26 S.Fujita [QC#3293,MOD]

    
    /**
     * pagenation <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param pageFrom int
     */
    public static void pagenation(NFCL3010CMsg cMsg, NFCL3010SMsg sMsg, int pageFrom) {

        int i = pageFrom;
        for (; i < pageFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(i - pageFrom), null);
            } else {
                break;
            }
        }

        cMsg.A.setValidCount(i - pageFrom);
        cMsg.xxPageShowFromNum_A.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum_A.setValue(pageFrom + cMsg.A.getValidCount());
    }
}
