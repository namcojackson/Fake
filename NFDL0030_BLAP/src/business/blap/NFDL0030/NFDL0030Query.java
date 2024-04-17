package business.blap.NFDL0030;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.blap.NFDL0030.constant.NFDL0030Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PNT_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Collection Summary
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 05/23/2018   Hitachi         Y.Takeno        Update          QC#24882
 * 2020/06/01   Fujitsu         H.Ikeda         Update          QC#56866
 * 2022/09/01   Hitachi         S.Naya          Update          QC#58101
 * 2023/03/10   Hitachi         S.Nakatani      Update          QC#55645
 *</pre>
 */
public class NFDL0030Query extends S21SsmEZDQuerySupport implements NFDL0030Constant {

    /**
     * Singleton instance.
     */
    private static final NFDL0030Query myInstance = new NFDL0030Query();

    /**
     * Constructor
     */
    public NFDL0030Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NFDL0030Query
     */
    public static NFDL0030Query getInstance() {
        return myInstance;
    }

    /**
     * @param map Map
     * @param sMsg NFDL0030SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getArTrxBalList(Map map, NFDL0030CMsg cMsg) {
        return getSsmEZDClient().queryObjectList("getArTrxBal", map);
    }

    /**
     * @param map Map
     * @param sMsg NFDL0030SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCreditCardInfo(Map map, NFDL0030CMsg cMsg) {
        return getSsmEZDClient().queryObject("getCreditCardInfo", map);
    }

    // START 2018/05/23 [QC#24882, ADD]
    /**
     * getCltPsnCd
     * @param glblCmpyCd String
     * @param arTrxNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCltPsnCd(String glblCmpyCd, String arTrxNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("arTrxNum", arTrxNum);
        return getSsmEZDClient().queryObject("getCltPsnCd", param);
    }

    /**
     * getCltPsnCdOnAcct
     * @param glblCmpyCd String
     * @param sellToCustCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCltPsnCdOnAcct(String glblCmpyCd, String sellToCustCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("sellToCustCd", sellToCustCd);
        return getSsmEZDClient().queryObject("getCltPsnCdOnAcct", param);
    }

    /**
     * getCtacPsnMailAddr
     * @param glblCmpyCd String
     * @param arTrxNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCtacPsnMailAddr(String glblCmpyCd, String arTrxNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("arTrxNum", arTrxNum);
        return getSsmEZDClient().queryObject("getCtacPsnMailAddr", param);
    }

    /**
     * getCtacPsnMailAddrOnAcct
     * @param glblCmpyCd String
     * @param sellToCustCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCtacPsnMailAddrOnAcct(String glblCmpyCd, String sellToCustCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("sellToCustCd", sellToCustCd);
        return getSsmEZDClient().queryObject("getCtacPsnMailAddrOnAcct", param);
    }

    /**
     * getCltInfoMap
     * @param glblCmpyCd String
     * @param arTrxNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCltInfoMap(String glblCmpyCd, String arTrxNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("arTrxNum", arTrxNum);
        // START 2022/09/01 S.Naya [QC#58101,ADD]
        param.put("userId", getContextUserInfo().getUserId());
        // END   2022/09/01 S.Naya [QC#58101,ADD]
        return getSsmEZDClient().queryObject("getCltInfoMap", param);
    }

    /**
     * getConfLtrData
     * @param glblCmpyCd String
     * @param arTrxNumList List
     * @param rcptNum String
     * @param crCardCustRefNum String
     * @return S21SsmEZDResult
     */
    // START 2023/03/10 S.Nakatani [QC#55645,MOD]
//    public S21SsmEZDResult getConfLtrData(String glblCmpyCd, List<String> arTrxNumList, String rcptNum, String crCardCustRefNum) {
    public S21SsmEZDResult getConfLtrData(String glblCmpyCd, String rcptNum, String crCardCustRefNum) {
    // END 2023/03/10 S.Nakatani [QC#55645,MOD]
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        // START 2023/03/10 S.Nakatani [QC#55645,DEL]
//        param.put("arTrxNumList", arTrxNumList);
        // END 2023/03/10 S.Nakatani [QC#55645,DEL]
        param.put("rcptNum", rcptNum);
        param.put("crCardCustRefNum", crCardCustRefNum);
        param.put("ctacTpCd", CTAC_TP.CREDIT_OR_COLLECTONS);
        param.put("dsCtacPntTpCd", DS_CTAC_PNT_TP.EMAIL_WORK);
        param.put("flgY", ZYPConstant.FLG_ON_Y);
        return getSsmEZDClient().queryObjectList("getConfLtrData", param);
    }

    /**
     * getConfLtrDataOnAcct
     * @param glblCmpyCd String
     * @param rcptNum String
     * @param crCardCustRefNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getConfLtrDataOnAcct(String glblCmpyCd, String rcptNum, String crCardCustRefNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("rcptNum", rcptNum);
        param.put("crCardCustRefNum", crCardCustRefNum);
        param.put("ctacTpCd", CTAC_TP.CREDIT_OR_COLLECTONS);
        param.put("dsCtacPntTpCd", DS_CTAC_PNT_TP.EMAIL_WORK);
        param.put("flgY", ZYPConstant.FLG_ON_Y);
        return getSsmEZDClient().queryObjectList("getConfLtrDataOnAcct", param);
    }

    /**
     * getConfLtrWrk
     * @param glblCmpyCd String
     * @param arTrxNumList List
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getConfLtrWrk(String glblCmpyCd, BigDecimal rqstNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("rqstNum", rqstNum);
        param.put("ctacTpCd", CTAC_TP.CREDIT_OR_COLLECTONS);
        param.put("dsCtacPntTpCd", DS_CTAC_PNT_TP.EMAIL_WORK);
        param.put("flgY", ZYPConstant.FLG_ON_Y);
        return getSsmEZDClient().queryObject("getConfLtrWrk", param);
    }
    // END   2018/05/23 [QC#24882, ADD]

    // START 2020/06/1 [QC#56866, ADD]
    /**
     * getInvInfo
     * Mod QC#56866
     * @param glblCmpyCd String
     * @param arTrxNumList <String>
     * @param constNum   BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getInvInfo(String glblCmpyCd, List<String> arTrxNumList, BigDecimal constNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("arTrxNumList", arTrxNumList);
        // QC#56866 Add
        if(ZYPCommonFunc.hasValue(constNum)) {
            param.put("constNum", constNum);
        }
        // QC#56866 End
        return getSsmEZDClient().queryObjectList("getInvInfo", param);
    }

    /**
     * @param glblCmpyCd       String
     * @param crCardCustRefNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsCardInfo(String glblCmpyCd, String crCardCustRefNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("crCardCustRefNum", crCardCustRefNum);
        return getSsmEZDClient().queryObject("getDsCardInfo", param);
    }
    // END   2020/06/1 [QC#56866, ADD]
    // START 2023/03/10 S.Nakatani [QC#55645,ADD]
    /**
     * @param glblCmpyCd String
     * @param dsCustBankAcctRelnPk String
     * @return S21SsmEZDResult
     */
    public Map<String, Object> getBankInfo(String glblCmpyCd, String dsCustBankAcctRelnPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsCustBankAcctRelnPk", dsCustBankAcctRelnPk);
        return (Map<String, Object>) getSsmEZDClient().queryObject("getBankInfo", param).getResultObject();
    }
    /**
     * @param glblCmpyCd String
     * @param dsCustBankAcctRelnPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBankAcctPk(String glblCmpyCd, BigDecimal dsCustBankAcctRelnPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsCustBankAcctRelnPk", dsCustBankAcctRelnPk);
        return getSsmEZDClient().queryObject("getBankAcctPk", param);
    }
    /**
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPmtMethList(String glblCmpyCd) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dispFlg", ZYPConstant.FLG_ON_Y);
        return getSsmEZDClient().queryObjectList("getPmtMethList", param);
    }
    /**
     * @param glblCmpyCd String
     * @param dsAcctNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPayerName(String glblCmpyCd, String dsAcctNum) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsAcctNum", dsAcctNum);
        return getSsmEZDClient().queryObject("getPayerName", param);
    }

    /**
     * getConfLtrData
     * @param glblCmpyCd String
     * @param rcptNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getConfLtrDataForECheck(String glblCmpyCd, String rcptNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("rcptNum", rcptNum);
        param.put("ctacTpCd", CTAC_TP.CREDIT_OR_COLLECTONS);
        param.put("dsCtacPntTpCd", DS_CTAC_PNT_TP.EMAIL_WORK);
        param.put("flgY", ZYPConstant.FLG_ON_Y);
        return getSsmEZDClient().queryObjectList("getConfLtrDataForECheck", param);
    }

    /**
     * getConfLtrDataOnAcct
     * @param glblCmpyCd String
     * @param rcptNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getConfLtrDataOnAcctForECheck(String glblCmpyCd, String rcptNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("rcptNum", rcptNum);
        param.put("ctacTpCd", CTAC_TP.CREDIT_OR_COLLECTONS);
        param.put("dsCtacPntTpCd", DS_CTAC_PNT_TP.EMAIL_WORK);
        param.put("flgY", ZYPConstant.FLG_ON_Y);
        return getSsmEZDClient().queryObjectList("getConfLtrDataOnAcctForECheck", param);
    }
    // END 2023/03/10 S.Nakatani [QC#55645,ADD]
}
