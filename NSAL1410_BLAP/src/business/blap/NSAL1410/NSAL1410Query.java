/*
 * <Pre>Copyright (c) 2022 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1410;

import static business.blap.NSAL1410.constant.NSAL1410Constant.MAX_DATA;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.BILL_TO_CUSTTMsg;
import business.db.BILL_TO_CUSTTMsgArray;
import business.db.DS_CONTRTMsg;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_STS;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Contract Branch Rep Update
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2022/06/07   Hitachi         A.Kohinata      Create          QC#60080
 * 2022/07/25   Hitachi         H.Watanabe      Update          QC#60080
 *</pre>
 */
public final class NSAL1410Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSAL1410Query INSTANCE = new NSAL1410Query();

    /**
     * Constructor.
     */
    private NSAL1410Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSAL1410Query
     */
    public static NSAL1410Query getInstance() {
        return INSTANCE;
    }

    /**
     * getSearchData
     * @param cMsg NSAL1410CMsg
     * @param sMsg NSAL1410SMsg
     * @param cnt int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchData(NSAL1410CMsg cMsg, NSAL1410SMsg sMsg, int cnt) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", cMsg.glblCmpyCd);
        ssmPrm.put("dsContrNum", cMsg.dsContrNum_H);
        ssmPrm.put("svcContrBrCd", cMsg.svcContrBrCd_H);
        ssmPrm.put("svcContrBrDescTxt", cMsg.svcContrBrDescTxt_H);
        ssmPrm.put("psnCd", cMsg.psnCd_H);
        ssmPrm.put("xxPsnNm", cMsg.xxPsnNm_H);
        ssmPrm.put("billToCustCd", cMsg.billToCustCd_H);
        ssmPrm.put("locNm", cMsg.locNm_H);
        ssmPrm.put("limitNum", sMsg.A.length() + 1);
        String[] dsContrStsCdList = new String[] {DS_CONTR_STS.CANCELLED, DS_CONTR_STS.ORDER };
        ssmPrm.put("dsContrStsCdList", dsContrStsCdList);
        return getSsmEZDClient().queryEZDMsgArray("getSearchData", ssmPrm, sMsg.A);
    }

    /**
     * getContrInfo
     * @param glblCmpyCd String
     * @param dsContrNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContrInfo(String glblCmpyCd, String dsContrNum) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrNum", dsContrNum);
        return getSsmEZDClient().queryObject("getContrInfo", ssmPrm);
    }

    /**
     * getPsnInfo
     * @param glblCmpyCd String
     * @param slsDt String
     * @param psnCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPsnInfo(String glblCmpyCd, String slsDt, String psnCd) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("psnCd", psnCd);
        ssmPrm.put("slsDt", slsDt);
        return getSsmEZDClient().queryObject("getPsnInfo", ssmPrm);
    }

    /**
     * getAdminPsnAuthority
     * @param glblCmpyCd String
     * @param svcContrBrCd String
     * @param psnCd String
     * @param slsDt String
     * @return BigDecimal
     */
    public boolean getAdminPsnAuthority(String glblCmpyCd, String svcContrBrCd, String psnCd, String slsDt){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("svcContrBrCd", svcContrBrCd);
        paramMap.put("psnCd", psnCd);
        paramMap.put("effFromDt", slsDt);
        paramMap.put("maxData", MAX_DATA);
        BigDecimal cnt = (BigDecimal) getSsmEZDClient().queryObject("getAdminPsnAuthority", paramMap).getResultObject();
        if(cnt.toString().equals("0")){
            return false;
        }
        return true;
    }
    

    /**
     * getBillToCust
     * @param glblCmpyCd String
     * @param billToCustCd String
     * @return BILL_TO_CUSTTMsg
     */
    public BILL_TO_CUSTTMsg getBillToCust(String glblCmpyCd, String billToCustCd) {
        BILL_TO_CUSTTMsg tMsg = new BILL_TO_CUSTTMsg();
        tMsg.setSQLID("047");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("billToCustCd01", billToCustCd);
        BILL_TO_CUSTTMsgArray tMsgArray = (BILL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (tMsgArray.length() > 0) {
            return tMsgArray.no(0);
        } else {
            return null;
        }
    }

    /**
     * get Contract By Contract Primary Key
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return DS_CONTRTMsg
     */
    public DS_CONTRTMsg getDsContr(String glblCmpyCd, BigDecimal dsContrPk) {
        DS_CONTRTMsg prmTMsg = new DS_CONTRTMsg();
        setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        setValue(prmTMsg.dsContrPk, dsContrPk);
        return (DS_CONTRTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(prmTMsg);
    }
}
