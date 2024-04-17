/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFBL1130;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.blap.NFBL1130.NFBL1130Query;
import business.blap.NFBL1130.constant.NFBL1130Constant;
import business.blap.NFBL1130.NFBL1130CMsg;
import business.blap.NFBL1130.NFBL1130SMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_PROJ_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * </pre>
 * 
 * <pre>
 * AP Accrual Write-off Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/14   CUSA            Y.Aikawa        Create          N/A
 * 2016/09/30   Hitachi         K.Kojima        Update          QC#14178
 * 2017/01/12   Fujitsu         T.Murai         Update          QC#16928
 * 2019/01/30   Fujitsu         H.Ikeda         Update          QC#30057
 * 2019/03/12   Hitachi         Y.Takeno        Update          QC#30729
 * 2021/03/12   CITS            H.Dimay         Update          QC#57040
 * 2022/03/28   Hitachi         A.Kohinata      Update          QC#57935(56588)
 * 2022/04/05   Hitachi         R.Onozuka       Update          QC#57935
 * 2022/08/05   Hitachi         A.Kohinata      Update          QC#59245
 * </pre>
 */
public final class NFBL1130Query extends S21SsmEZDQuerySupport implements NFBL1130Constant {
    /**
     * Singleton instance.
     */
    private static final NFBL1130Query INSTANCE = new NFBL1130Query();
    /**
     * User Profile
     */
    public static final S21UserProfileService PROFILE_SERVICE = S21UserProfileServiceFactory.getInstance().getService();
    /**
     * Global Company Code.
     */
    public static final String GLBL_CMPY_CD = PROFILE_SERVICE.getGlobalCompanyCode();
    /**
     * Constructor.
     */
    private NFBL1130Query() {
        super();
    }
    /**
     * Singleton instance getter.
     * @return NFBL1130Query
     */
    public static NFBL1130Query getInstance() {
        return INSTANCE;
    }

    /**
     * NFBL1130Query.xml id="searchRecord"
     * @param bizMsg NFBL1130CMsg
     * @param globalMsg NFBL1130SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchRecord(NFBL1130CMsg bizMsg, NFBL1130SMsg globalMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", GLBL_CMPY_CD);
        ssmParam.put("poNum", bizMsg.poNum.getValue());
        ssmParam.put("delyOrdNum", bizMsg.delyOrdNum.getValue());
        ssmParam.put("mdseCd", bizMsg.mdseCd.getValue());
        ssmParam.put("invRcvQty", bizMsg.invRcvQty.getValue());
        ssmParam.put("apVndInvNum", bizMsg.apVndInvNum.getValue());
        // START 2016/09/30 K.Kojima [QC#14179,MOD]
        // ssmParam.put("apVndCd", bizMsg.apVndCd.getValue());
        ssmParam.put("prntVndCd", bizMsg.prntVndCd.getValue());
        // END 2016/09/30 K.Kojima [QC#14179,MOD]
        ssmParam.put("invQty", bizMsg.invQty.getValue());
        ssmParam.put("coaAcctCd", bizMsg.acrlCoaAcctCd_S.getValue());
        // START 2016/09/29 K.Kojima [QC#14178,ADD]
        ssmParam.put("rwsNum", bizMsg.rwsNum.getValue());
        // END 2016/09/29 K.Kojima [QC#14178,ADD]
        ssmParam.put("invDt_FR", bizMsg.invDt_FR.getValue());
        ssmParam.put("invDt_TO", bizMsg.invDt_TO.getValue());
        ssmParam.put("stkInDt_FR", bizMsg.stkInDt_FR.getValue());
        ssmParam.put("stkInDt_TO", bizMsg.stkInDt_TO.getValue());
        if (ZYPCommonFunc.hasValue(bizMsg.xxChkBox_WO)
        &&  bizMsg.xxChkBox_WO.getValue().equals(ZYPConstant.FLG_ON_Y)) {
            ssmParam.put("xxChkBox_WO", ZYPConstant.FLG_ON_Y);
        } else {
            ssmParam.put("xxChkBox_WO", ZYPConstant.FLG_OFF_N);
        }
        if (ZYPCommonFunc.hasValue(bizMsg.xxChkBox_PM)
        &&  bizMsg.xxChkBox_PM.getValue().equals(ZYPConstant.FLG_ON_Y)) {
            ssmParam.put("xxChkBox_PM", ZYPConstant.FLG_ON_Y);
        } else {
            ssmParam.put("xxChkBox_PM", ZYPConstant.FLG_OFF_N);
        }
        ssmParam.put("wrtOffFlg", ZYPConstant.FLG_OFF_N);
        // START 2018/11/08 J.Kim [QC#23037,ADD]
        ssmParam.put("crTpCd", DR_CR_TP_CREDIT);
        ssmParam.put("drTpCd", DR_CR_TP_DEBIT);
        // END 2018/11/08 J.Kim [QC#23037,ADD]
        // START 2021/03/12 H.Dimay [QC#57040,ADD]
        String coaAcctCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NFBL1130_COA_ACCT_CD_LIST, GLBL_CMPY_CD);
        List<String> coaAcctCdList = new ArrayList<String>();
        if (ZYPCommonFunc.hasValue(coaAcctCd)) {
            coaAcctCdList = Arrays.asList(coaAcctCd.split(ARRAY_DELIMITER));
        } else {
            coaAcctCdList.add(DEFAULT_COA_ACCT_CD);
        }
        ssmParam.put("coaAcctCdList", coaAcctCdList);
        // END 2021/03/12 H.Dimay [QC#57040,ADD]
        // START 2022/04/05 R.Onozuka [QC357935, ADD]
        ssmParam.put("wrtOffFlgY", ZYPConstant.FLG_ON_Y);
        ssmParam.put("notJournalized", ZYPConstant.FLG_OFF_0);
        // END 2022/04/05 R.Onozuka [QC357935, ADD]
        // add start 2022/08/05 QC#59245
        if (ZYPCommonFunc.hasValue(bizMsg.xxChkBox_CV) && bizMsg.xxChkBox_CV.getValue().equals(ZYPConstant.FLG_ON_Y)) {
            ssmParam.put("xxChkBox_CV", ZYPConstant.FLG_ON_Y);
        } else {
            ssmParam.put("xxChkBox_CV", ZYPConstant.FLG_OFF_N);
        }
        // add end 2022/08/05 QC#59245

        return getSsmEZDClient().queryObjectList("searchRecord", ssmParam, -1, -1);
    }

    /**
     * NFBL1130Query.xml id="getAcrlCoaAcctCdPulldownValue"
     * 
     * <pre>
     * Get records for [Accrual Account Code] pulldown.
     * </pre>
     * 
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAcrlCoaAcctCdPulldownValue() {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", GLBL_CMPY_CD);
        queryParam.put("acctTpCdAccrual", COA_PROJ_ACCT_TP.ACCRUAL); // ADD 2017/01/12 [QC#16928]
        return getSsmEZDClient().queryObjectList("getAcrlCoaAcctCdPulldownValue", queryParam, -1, -1);

    }

    /**
     * NFBL1130Query.xml id="getAcrlWrtOffRsnPulldownValue"
     * 
     * <pre>
     * Get records for [Accrual Account Code] pulldown.
     * </pre>
     * 
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAcrlWrtOffRsnPulldownValue() {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", GLBL_CMPY_CD);
        return getSsmEZDClient().queryObjectList("getAcrlWrtOffRsnPulldownValue", queryParam, -1, -1);

    }

    /**
     * NFBL1130Query.xml id="getPrntVndNm"
     * 
     * <pre>
     * Get Supplier Name.
     * </pre>
     * 
     * @param prntVndCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrntVndNm(String prntVndCd) {

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", GLBL_CMPY_CD);
        queryParam.put("prntVndCd", prntVndCd);
        return getSsmEZDClient().queryObjectList("getPrntVndNm", queryParam, -1, -1);

    }

    // START 2019/03/12 [QC#30729, MOD]
    /**
     * NFBL1130Query.xml id="getCmAcrlWriteOffPkList"
     * 
     * <pre>
     * Get CM_ACRL_WRITE_OFF_PK list.
     * </pre>
     * 
     * @param poNum String
     * @param poOrdDtlLineNum String
     * @param wrtOffFlg String
     * @param wrtOffDt String
     * @return S21SsmEZDResult
     */
    // public S21SsmEZDResult getCmAcrlWriteOffPkList(String poNum, String apVndCd, String mdseCd) {
    // mod start 2022/03/28 QC#57935(56588)
    //public S21SsmEZDResult getCmAcrlWriteOffPkList(String poNum, String apVndCd, String mdseCd, String poOrdDtlLineNum) {
    public S21SsmEZDResult getCmAcrlWriteOffPkList(String poNum, String poOrdDtlLineNum, String wrtOffFlg, String wrtOffDt) {
    // mod end 2022/03/28 QC#57935(56588)

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", GLBL_CMPY_CD);
        queryParam.put("poNum", poNum);
        // del start 2022/03/28 QC#57935(56588)
        //queryParam.put("apVndCd", apVndCd);
        //queryParam.put("mdseCd", mdseCd);
        // del end 2022/03/28 QC#57935(56588)
        queryParam.put("poOrdDtlLineNum", poOrdDtlLineNum);
        // add start 2022/03/28 QC#57935(56588)
        queryParam.put("wrtOffFlg", wrtOffFlg);
        queryParam.put("wrtOffDt", wrtOffDt);
        // add end 2022/03/28 QC#57935(56588)
        return getSsmEZDClient().queryObjectList("getCmAcrlWriteOffPkList", queryParam, -1, -1);

    }

    // START 2019/01/30 H.Ikeda [QC#30057, ADD]
    /**
     * NFBL1130Query.xml id="getExclusiveData"
     * 
     * <pre>
     * Get getExclusive Data.
     * </pre>
     * 
     * @param poNum String
     * @param poOrdDtlLineNum String
     * @param wrtOffFlg String
     * @param wrtOffDt String
     * @return S21SsmEZDResult
     */
    // public S21SsmEZDResult getExclusiveData(String poNum, String apVndCd, String mdseCd) {
    // mod start 2022/03/28 QC#57935(56588)
    //public S21SsmEZDResult getExclusiveData(String poNum, String apVndCd, String mdseCd, String poOrdDtlLineNum) {
    public S21SsmEZDResult getExclusiveData(String poNum, String poOrdDtlLineNum, String wrtOffFlg, String wrtOffDt) {
    // mod start 2022/03/28 QC#57935(56588)

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", GLBL_CMPY_CD);
        queryParam.put("poNum", poNum);
        // del start 2022/03/28 QC#57935(56588)
        //queryParam.put("apVndCd", apVndCd);
        //queryParam.put("mdseCd", mdseCd);
        // del end 2022/03/28 QC#57935(56588)
        queryParam.put("poOrdDtlLineNum", poOrdDtlLineNum);
        // add start 2022/03/28 QC#57935(56588)
        queryParam.put("wrtOffFlg", wrtOffFlg);
        queryParam.put("wrtOffDt", wrtOffDt);
        // add end 2022/03/28 QC#57935(56588)
        return getSsmEZDClient().queryObjectList("getExclusiveData", queryParam, -1, -1);
    }
    // END   2019/03/12 [QC#30729, MOD]
    // END   2019/01/30 H.Ikeda [QC#30057, ADD]
    // START 2022/04/05 R.Onozuka [QC#57935, ADD]
    /**
     * NFBL1130Query.xml id="getDefaultCoaList"
     * @param mdseCd String
     * @param flgY String
     * @return Map<String, String>
     */
    public Map<String, String> getDefaultCoaList(String glblCmpyCd, String mdseCd){
        
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", mdseCd);
        ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);

        Map<String, String> defaultCoa = (Map<String, String>) getSsmEZDClient().queryObject("getDefaultCoaList", ssmParam).getResultObject();
        return defaultCoa;
    }
    // END 2022/04/05 R.Onozuka [QC#57935, ADD]
}
