/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0490;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.blap.NSAL0490.constant.NSAL0490Constant;
import business.db.MDL_NMTMsg;
import business.db.MDL_NMTMsgArray;
import business.db.ORD_TAKE_MDSETMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_RGTN_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/09   Fujitsu         T.Yoshida       Create          N/A
 * 2015/10/07   Hitachi         Y.Tsuchimoto    Update          N/A
 * 2016/02/10   Hitachi         A.Kohinata      Update          CSA QC#1157
 * 2016/04/22   Hitachi         T.Tomita        Update          QC#5407
 * 2016/05/19   Hitachi         K.Kasai         Update          CSA QC#6675
 * 2017/01/16   Hitachi         T.Mizuki        Update          CSA QC#16618
 * 2017/07/12   Hitachi         K.Kim           Update          QC#18952
 * 2017/08/01   CITS            S.Endo          Update          Sol#072(QC#18386)
 * 2017/08/30   CITS            S.Endo          Update          QC#20732
 * 2024/03/14   Hitachi         K.Watanabe      Update          QC#63542
 *</pre>
 */
public final class NSAL0490Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NSAL0490Query INSTANCE = new NSAL0490Query();

    /**
     * Private constructor
     */
    private NSAL0490Query() {
        super();
    }

    /**
     * Get instance
     * @return NSAL0490Query singleton instance
     */
    public static NSAL0490Query getInstance() {
        return INSTANCE;
    }

    /**
     * get Meter Group List
     * @param cMsg NSAL0490CMsg
     * @return Meter Group List
     */
    public S21SsmEZDResult getMtrGrpList(NSAL0490CMsg cMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());

        return getSsmEZDClient().queryObjectList("getMtrGrpList", ssmParam);
    }

    // START 2016/02/10 A.Kohinata [QC#1157, DEL]
//    /**
//     * get Service Skills List
//     * @param cMsg NSAL0490CMsg
//     * @return Service Skills List
//     */
//    public S21SsmEZDResult getSvcSklList(NSAL0490CMsg cMsg) {
//
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
//
//        return getSsmEZDClient().queryObjectList("getSvcSklList", ssmParam);
//    }
    // END 2016/02/10 A.Kohinata [QC#1157, DEL]

    /**
     * get Service Installation Rules List
     * @param cMsg NSAL0490CMsg
     * @param actvFlg Active Flag
     * @return Service Installation Rules List
     */
    public S21SsmEZDResult getSvcIstlRuleList(NSAL0490CMsg cMsg, String actvFlg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        ssmParam.put("actvFlg", actvFlg);

        return getSsmEZDClient().queryObjectList("getSvcIstlRuleList", ssmParam);
    }

    /**
     * get Header Data
     * @param cMsg NSAL0490CMsg
     * @return Header Data
     */
    public S21SsmEZDResult getHdrData(NSAL0490CMsg cMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("mdlNm", cMsg.mdlNm.getValue());

        return getSsmEZDClient().queryEZDMsg("getHdrData", params, cMsg);
    }

    //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) START
    /**
     * get Item and Model Criticality Data
     * @param cMsg NSAL0490CMsg
     * @return Criticality Data
     */
    public S21SsmEZDResult getMdlCriticality(NSAL0490CMsg cMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("mdlId", cMsg.mdlId.getValue());
        params.put("emptySpace", " ");

        return getSsmEZDClient().queryObjectList("getMdlCriticality", params);
    }

    /**
     * get CriticalityMasterList Data
     * @param cMsg NSAL0490CMsg
     * @return CriticalityMaster Data
     */
    public S21SsmEZDResult getCriticalityMstList(NSAL0490CMsg cMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());

        return getSsmEZDClient().queryObjectList("getCriticalityMstList", params);
    }

    /**
     * get ItemInfo for Criticality
     * @param glblCmpyCd glblCmpyCd
     * @param mdseCd mdseCd
     * @return ItemInfo for Criticality
     */
    public S21SsmEZDResult getItemInfoForCriticality(String glblCmpyCd, String mdseCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdseCd", mdseCd);

        return getSsmEZDClient().queryObject("getItemInfoForCriticality", params);
    }

    /**
     * checkItemIsSetParent
     * @param glblCmpyCd glblCmpyCd
     * @param mdseCd mdseCd
     * @return checkResult
     */
    public S21SsmEZDResult checkItemIsSetParent(String glblCmpyCd, String mdseCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdseCd", mdseCd);
        //08/30/2017 CITS S.Endo Add QC#20732 START
        params.put("setParentCd", MDSE_ITEM_TP.SET);
        //08/30/2017 CITS S.Endo Add QC#20732 END
        return getSsmEZDClient().queryObject("checkItemIsSetParent", params);
    }

    /**
     * getModelBoImpctPk
     * @param glblCmpyCd glblCmpyCd
     * @param mdlId mdlId
     * @param mdseCd mdseCd
     * @return result
     */
    public S21SsmEZDResult getModelBoImpctPk(String glblCmpyCd, BigDecimal mdlId, String mdseCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdlId", mdlId);
        params.put("mdseCd", mdseCd);

        return getSsmEZDClient().queryObject("getModelBoImpctPk", params);
    }

    /**
     * getModelBoImpctDataList
     * @param glblCmpyCd glblCmpyCd
     * @param mdlId mdlId
     * @return result
     */
    public S21SsmEZDResult getModelBoImpctDataList(String glblCmpyCd, BigDecimal mdlId) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdlId", mdlId);

        return getSsmEZDClient().queryObjectList("getModelBoImpctData", params);
    }
    //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) END
// mod start 2017/01/16 CSA QC#16618
//    /**
//     * get Parent Data List
//     * @param cMsg NSAL0490CMsg
//     * @return Parent Data List
//     */
//    public S21SsmEZDResult getPrntDataList(NSAL0490CMsg cMsg) {
//
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
//        params.put("mdlId", cMsg.mdlId.getValue());
//
//        return getSsmEZDClient().queryObjectList("getPrntDataList", params);
//    }
//
//    /**
//     * get Child Data List
//     * @param cMsg NSAL0490CMsg
//     * @param prntConfigPk Parent Config PK
//     * @param prntMdseCd Parent Mdse Code
//     * @return Child Data List
//     */
//    public S21SsmEZDResult getChildDataList(NSAL0490CMsg cMsg, BigDecimal prntConfigPk, String prntMdseCd) {
//
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
//        params.put("mdlId", cMsg.mdlId.getValue());
//        params.put("prntConfigPk", prntConfigPk);
//        params.put("prntMdseCd", prntMdseCd);
//
//        return getSsmEZDClient().queryObjectList("getChildDataList", params);
//    }

    /**
     * get Item Config Data List
     * @param cMsg NSAL0490CMsg
     * @return Parent Data List
     */
    public S21SsmEZDResult getItemConfigDataList(NSAL0490CMsg cMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("mdlId", cMsg.mdlId.getValue());

        return getSsmEZDClient().queryObjectList("getItemConfigDataList", params);
    }
// mod end 2017/01/16 CSA QC#16618
    /**
     * get Mdse Info (Item Configurations)
     * @param cMsg NSAL0490CMsg
     * @param targetMdseCd Target Mdse Code
     * @return Mdse Info
     */
    public S21SsmEZDResult getMdseInfoForItemConfig(NSAL0490CMsg cMsg, String targetMdseCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("mdseCd", targetMdseCd);

        return getSsmEZDClient().queryObject("getMdseInfoForItemConfig", params);
    }

    /**
     * get Mdse Info (Supply Mapping)
     * @param cMsg NSAL0490CMsg
     * @param targetMdseCd Target Mdse Code
     * @return Mdse Info
     */
    public S21SsmEZDResult getMdseInfoForSupplyMap(NSAL0490CMsg cMsg, String targetMdseCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("mdseCd", targetMdseCd);

        return getSsmEZDClient().queryObject("getMdseInfoForSupplyMap", params);
    }

    /**
     * check Exist Model Name
     * @param cMsg NSAL0490CMsg
     * @return true: exist
     */
    public boolean isExistMdlNm(NSAL0490CMsg cMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("mdlNm", cMsg.mdlNm.getValue());

        S21SsmEZDResult result = getSsmEZDClient().queryObject("isExistMdlNm", params);

        return 0 < (Integer) result.getResultObject();
    }

    /**
     * check Exist Model Group
     * @param cMsg NSAL0490CMsg
     * @return true: exist
     */
    public boolean isExistMdlGrp(NSAL0490CMsg cMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("mdlGrpNm", cMsg.mdlGrpNm.getValue());

        S21SsmEZDResult result = getSsmEZDClient().queryObject("isExistMdlGrp", params);

        return 0 < (Integer) result.getResultObject();
    }

    /**
     * get Model Group ID
     * @param cMsg NSAL0490CMsg
     * @return Model Group ID
     */
    public BigDecimal getMdlGrpId(NSAL0490CMsg cMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("mdlGrpNm", cMsg.mdlGrpNm.getValue());

        S21SsmEZDResult result = getSsmEZDClient().queryObject("getMdlGrpId", params);

        return (BigDecimal) result.getResultObject();
    }

    /**
     * get Next Model ID
     * @param cMsg NSAL0490CMsg
     * @return Next Model ID
     */
    public BigDecimal getNextMdlId(NSAL0490CMsg cMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());

        S21SsmEZDResult result = getSsmEZDClient().queryObject("getNextMdlId", params);

        return (BigDecimal) result.getResultObject();
    }

    /**
     * get Service Rules Data
     * @param cMsg NSAL0490CMsg
     * @return Service Rules Data
     */
    public S21SsmEZDResult getSvcRulesData(NSAL0490CMsg cMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("mdlId", cMsg.mdlId.getValue());
        //add start 2016/05/19 CSA Defect#6675
        params.put("minute", NSAL0490Constant.INT_MINUTE);
        //add start 2016/05/19 CSA Defect#6675

        return getSsmEZDClient().queryEZDMsg("getSvcRulesData", params, cMsg);
    }

    /**
     * get Supply Mapping Data
     * @param cMsg NSAL0490CMsg
     * @param sMsg NSAL0490SMsg
     * @return Supply Mapping Data
     */
    public S21SsmEZDResult getSupplyMapData(NSAL0490CMsg cMsg, NSAL0490SMsg sMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("mdlId", cMsg.mdlId.getValue());

        return getSsmEZDClient().queryEZDMsgArray("getSupplyMapData", params, sMsg.B);
    }

    /**
     * check Exist DS Model Supply Relation
     * @param cMsg NSAL0490CMsg
     * @param bsMsg NSAL0490_BSMsg
     * @return true: exist
     */
    public boolean isExsitMdlSplyReln(NSAL0490CMsg cMsg, NSAL0490_BSMsg bsMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("mdlId", cMsg.mdlId.getValue());
        params.put("mdseCd", bsMsg.mdseCd_B.getValue());

        S21SsmEZDResult result = getSsmEZDClient().queryObject("isExsitMdlSplyReln", params);

        return 0 < (Integer) result.getResultObject();
    }

    /**
     * get Parent Info Of Other Model
     * @param cMsg NSAL0490CMsg
     * @param prntMdseCd Parent Mdse Code
     * @return Parent Info Of Other Model
     */
    public S21SsmEZDResult getPrntInfoOfOtherMdl(NSAL0490CMsg cMsg, String prntMdseCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("mdlId", cMsg.mdlId.getValue());
        params.put("prntMdseCd", prntMdseCd);

        return getSsmEZDClient().queryObjectList("getPrntInfoOfOtherMdl", params);
    }

    /**
     * get Mdse Combination
     * @param cMsg NSAL0490CMsg
     * @param mdlId Model ID
     * @param prntMdseCd Parent Mdse Code
     * @param dsMdlConfigPk DS Model Config PK
     * @return Mdse Combination
     */
    //START 2019/06/06 QC#50638 K.Fujimoto [MOD]
    //public S21SsmEZDResult getMdseCombination(NSAL0490CMsg cMsg, BigDecimal mdlId, String prntMdseCd, BigDecimal dsMdlConfigPk) {
    //END   2019/06/06 QC#50638 K.Fujimoto [MOD]
    public List<Map<String, Object>> getMdseCombination(NSAL0490CMsg cMsg, BigDecimal mdlId, String prntMdseCd, BigDecimal dsMdlConfigPk, HashMap<BigDecimal, List<Map<String, Object>>> mdseCombMap) {

        Map<String, Object> params = new HashMap<String, Object>();
        //START 2019/06/06 QC#50638 K.Fujimoto [ADD]
        if (mdseCombMap.containsKey(dsMdlConfigPk)) {
            return mdseCombMap.get(dsMdlConfigPk);
        }
        //END   2019/06/06 QC#50638 K.Fujimoto [ADD]
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("mdlId", mdlId);
        params.put("prntMdseCd", prntMdseCd);
        params.put("dsMdlConfigPk", dsMdlConfigPk);

        //START 2019/06/06 QC#50638 K.Fujimoto [MOD]
        //return getSsmEZDClient().queryObjectList("getMdseCombination", params);
        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObjectList("getMdseCombination", params);
        if (ssmResult.isCodeNotFound()) {
            return null;
        } else {
            List<Map<String, Object>> mdseList = (List<Map<String, Object>>) ssmResult.getResultObject();
            mdseCombMap.put(dsMdlConfigPk, mdseList);
            return mdseList;
        }
        //END   2019/06/06 QC#50638 K.Fujimoto [MOD]
    }

    // 2015/10/07 CSA Y.Tsuchimoto Add Start
    /**
     * get Service Istl Call Group List
     * @param cMsg NSAL0490CMsg
     * @param svcIstlCallGrpFlg SVC_ISTL_CALL_GRP_FLG
     * @return Service Istl Call Group List
     */
    public S21SsmEZDResult getSvcIstlCallGrpList(NSAL0490CMsg cMsg, String svcIstlCallGrpFlg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        ssmParam.put("svcIstlCallGrpFlg", svcIstlCallGrpFlg);

        return getSsmEZDClient().queryObjectList("getSvcIstlCallGrpList", ssmParam);
    }

    /**
     * get ALL_MDSE_VIEW List
     * @param cMsg NSAL0490CMsg
     * @param mdseCd MDSE_CD
     * @return ALL_MDSE_VIEW List
     */
    public S21SsmEZDResult getAllMdseV(NSAL0490CMsg cMsg, String mdseCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        ssmParam.put("mdseCd", mdseCd);

        return getSsmEZDClient().queryObjectList("getAllMdseV", ssmParam);
    }

    /**
     * get Machine only info
     * @param cMsg NSAL0490CMsg
     * @param mdseCd MDSE_CD
     * @return Machine only info
     */
    public S21SsmEZDResult getMachineOnlyInfo(NSAL0490CMsg cMsg, String mdseCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        ssmParam.put("mdseCd", mdseCd);

        return getSsmEZDClient().queryObjectList("getMachineOnlyInfo", ssmParam);
    }
    // 2015/10/07 CSA Y.Tsuchimoto Add End

    // add start 2016/04/22 CSA Defect#5407
    /**
     * get Mercury Mdse
     * @param glblCmpyCd String
     * @param mdseCd MDSE_CD
     * @return ALL_MDSE_VIEW List
     */
    public S21SsmEZDResult getMercuryMdse(String glblCmpyCd, String mdseCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", mdseCd);
        ssmParam.put("mdseRgtnTpCd", MDSE_RGTN_TP.MERCURY);

        return getSsmEZDClient().queryObject("getMercuryMdse", ssmParam);
    }
    // add end 2016/04/22 CSA Defect#5407

    // START 2017/07/12 K.Kim [QC#18952, ADD]
    /**
     * get Mercury OrdTakeMdse
     * @param glblCmpyCd String
     * @param ordTakeMdseCd ORD_TAKE_MDSE_CD
     * @return ORD_TAKE_MDSETMsg
     */
    public ORD_TAKE_MDSETMsg getMercuryOrdTakeMdseCd(String glblCmpyCd, String ordTakeMdseCd) {
        ORD_TAKE_MDSETMsg tMsg = new ORD_TAKE_MDSETMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.ordTakeMdseCd, ordTakeMdseCd);
        return (ORD_TAKE_MDSETMsg) EZDTBLAccessor.findByKey(tMsg);
    }
    // END 2017/07/12 K.Kim [QC#18952, ADD]

    // START 2024/03/14 K.Watanabe [QC#63542, ADD]
    /**
     * get Mercury OrdTakeMdse
     * @param glblCmpyCd String
     * @param mdlId BigDecimal
     * @param rowNum int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getItemInfoForNotEnteredEndDate(String glblCmpyCd, BigDecimal mdlId, int rowNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdlId", mdlId);
        ssmParam.put("rowNum", rowNum);

        return getSsmEZDClient().queryObjectList("getItemInfoForNotEnteredEndDate", ssmParam);
    }
    // END   2024/03/14 K.Watanabe [QC#63542, ADD]
}
