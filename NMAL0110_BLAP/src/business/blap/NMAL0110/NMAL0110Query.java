package business.blap.NMAL0110;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_OFF_N;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.blap.NMAL0110.constant.NMAL0110Constant;
import business.db.AMER_MSTRTMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.PKG_UOM_BOX_EACHTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_PROJ_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_STRU_ELMNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/12/2015   SRAA            K.Aratani       Create          
 * 07/03/2015   Fujitsu         C.Tanaka        Update          
 * 10/02/2015   SRAA            K.Aratani       Update
 * 02/11/2016   SRAA            Y.Chen          Update          QC#3579
 * 03/16/2016   SRAA            K.Aratani       Update          QC#5580
 * 06/23/2016   SRAA            K.Aratani       Update          QC#9823, QC#10129
 * 2019/10/23   Fujitsu         S.Kosaka        Update          QC#51964
 * 2019/10/28   Fujitsu         K.Kato          Update          QC#53741
 * 2019/10/31   Fujitsu         K.Kato          Update          QC#51967
 * 2019/12/13   Fujitsu         S.Kosaka        Update          QC#54621
 * 2020/01/07   Fujitsu         K.Kato          Update          QC#55220
 * 2021/01/07   CITS            J.Evangelista   Update          QC#58090
 * 2022/02/17   Fujitsu         C.Hara          Update          QC#59693
 * 2023/09/05   Hitachi         K.Watanabe      Update          QC#53408
 * 2023/12/12   Hitachi         K.Watanabe      Update          QC#61300
 *</pre>
 */
public final class NMAL0110Query extends S21SsmEZDQuerySupport implements NMAL0110Constant {

    /**
     * Singleton instance.
     */
    private static final NMAL0110Query myInstance = new NMAL0110Query();

    /**
     * Constructor.
     */
    private NMAL0110Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NMAL0110Query
     */
    public static NMAL0110Query getInstance() {
        return myInstance;
    }

    public S21SsmEZDResult getItemStatusList(NMAL0110CMsg cMsg, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        return getSsmEZDClient().queryObjectList("getItemStatusList", ssmParam);
    }
    
    public S21SsmEZDResult getItemStatusDefaultList(NMAL0110CMsg cMsg, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("defFlg", FLG_ON_Y);
        return getSsmEZDClient().queryObject("getItemStatusList", ssmParam);
    }
    
    public S21SsmEZDResult getCoaProjList(NMAL0110CMsg cMsg, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        return getSsmEZDClient().queryObjectList("getCoaProjList", ssmParam);
    }
    
    public S21SsmEZDResult getPlanningGroupList(NMAL0110CMsg cMsg, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        return getSsmEZDClient().queryObjectList("getPlanningGroupList", ssmParam);
    }
    
    public S21SsmEZDResult getPkgUomPullDownList(NMAL0110CMsg cMsg, String glblCmpyCd, String pkgUomClsCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("pkgUomClsCd", pkgUomClsCd);
        return getSsmEZDClient().queryObjectList("getPkgUomPullDownList", ssmParam);
    }
    
    public S21SsmEZDResult getProductLevelName(NMAL0110CMsg cMsg, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        return getSsmEZDClient().queryObjectList("getProductLevelName", ssmParam);
    }
    
    public S21SsmEZDResult search(NMAL0110CMsg cMsg, String glblCmpyCd, String mdseCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", mdseCd);
        return getSsmEZDClient().queryObject("search", ssmParam);
    }

    public S21SsmEZDResult getSerialRange(NMAL0110CMsg cMsg, String glblCmpyCd, String mdseCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", mdseCd);
        return getSsmEZDClient().queryObjectList("getSerialRange", ssmParam);
    }

    public S21SsmEZDResult getSupdReln(NMAL0110CMsg cMsg, String glblCmpyCd, String mdseCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", mdseCd);
        return getSsmEZDClient().queryObjectList("getSupdReln", ssmParam);
    }
    
    public S21SsmEZDResult getSupdRelnTo(NMAL0110CMsg cMsg, String glblCmpyCd, String mdseCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", mdseCd);
        return getSsmEZDClient().queryObjectList("getSupdRelnTo", ssmParam);
    }
    
    public S21SsmEZDResult getSupdRelnFrom(NMAL0110CMsg cMsg, String glblCmpyCd, String mdseCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", mdseCd);
        return getSsmEZDClient().queryObjectList("getSupdRelnFrom", ssmParam);
    }
    
    public S21SsmEZDResult getRelnMdse(NMAL0110CMsg cMsg, String glblCmpyCd, String mdseCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", mdseCd);
        return getSsmEZDClient().queryObjectList("getRelnMdse", ssmParam);
    }
    
    public S21SsmEZDResult getCustRef(NMAL0110CMsg cMsg, String glblCmpyCd, String mdseCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", mdseCd);
        return getSsmEZDClient().queryObjectList("getCustRef", ssmParam);
    }

    public S21SsmEZDResult getProdCtrl(String prodCtrlCd, String mdseStruElmntTpCd, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("prodCtrlCd", prodCtrlCd);
        ssmParam.put("mdseStruElmntTpCd", mdseStruElmntTpCd);
        return getSsmEZDClient().queryObjectList("getProdCtrl", ssmParam);
    }
    public S21SsmEZDResult getProdCtrlForThird(String prodCtrlCd, String mdseStruElmntTpCd, String scdProdCtrlCd, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("prodCtrlCd", prodCtrlCd);
        ssmParam.put("mdseStruElmntTpCd", mdseStruElmntTpCd);
        ssmParam.put("mdseStruElmntTpCdPL2", MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_2);
        if (ZYPCommonFunc.hasValue(scdProdCtrlCd)) {
            ssmParam.put("scdProdCtrlCd", scdProdCtrlCd);
        }
        return getSsmEZDClient().queryObjectList("getProdCtrlForThird", ssmParam);
    }

    public S21SsmEZDResult getSlsMatGrp(NMAL0110CMsg cMsg, String glblCmpyCd, String slsMatGrpCd, String slsMatGrpSqNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("slsMatGrpCd", slsMatGrpCd);
        ssmParam.put("slsMatGrpSqNum", slsMatGrpSqNum);
        return getSsmEZDClient().queryObject("getSlsMatGrp", ssmParam);
    }
    public S21SsmEZDResult getDsCmsnGrp(NMAL0110CMsg cMsg, String glblCmpyCd, String dsCmsnGrpCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsCmsnGrpCd", dsCmsnGrpCd);
        return getSsmEZDClient().queryObject("getDsCmsnGrp", ssmParam);
    }
    public S21SsmEZDResult getDsAcctCust(String glblCmpyCd, String dsAcctNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsAcctNum", dsAcctNum);
        return getSsmEZDClient().queryObjectList("getDsAcctCust", ssmParam);
    }
    public S21SsmEZDResult getMnfItemCd(String glblCmpyCd, String mdseCd, String mnfItemCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", mdseCd);
        ssmParam.put("mnfItemCd", mnfItemCd);
        return getSsmEZDClient().queryObjectList("getMnfItemCd", ssmParam);
    }
    public S21SsmEZDResult getPkgUomList(String glblCmpyCd, String mdseCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", mdseCd);
        return getSsmEZDClient().queryObjectList("getPkgUomList", ssmParam);
    }
    public S21SsmEZDResult getPkgUomTmplList(String glblCmpyCd, BigDecimal mdseCratTmplPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCratTmplPk", mdseCratTmplPk);
        return getSsmEZDClient().queryObjectList("getPkgUomTmplList", ssmParam);
    }
    public S21SsmEZDResult getPkgUomTmplAllList(String glblCmpyCd, BigDecimal mdseCratTmplPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCratTmplPk", mdseCratTmplPk);
        return getSsmEZDClient().queryObjectList("getPkgUomTmplAllList", ssmParam);
    }
    
    public S21SsmEZDResult getTermCondOptList(String glblCmpyCd, String mdseCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", mdseCd);
        return getSsmEZDClient().queryObjectList("getTermCondOptList", ssmParam);
    }
//    public S21SsmEZDResult getIntgProdCatgConv(NMAL0110CMsg cMsg, String glblCmpyCd) {
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", glblCmpyCd);
//        if (ZYPCommonFunc.hasValue(cMsg.mdseItemTpCd_H1)) {
//	        ssmParam.put("mdseItemTpCd", cMsg.mdseItemTpCd_H1.getValue());
//        }
//        if (ZYPCommonFunc.hasValue(cMsg.coaMdseTpCd_H1)) {
//	        ssmParam.put("coaMdseTpCd", cMsg.coaMdseTpCd_H1.getValue());
//        }
//        if (ZYPCommonFunc.hasValue(cMsg.swLicCtrlTpCd_H1)) {
//	        ssmParam.put("swLicCtrlTpCd", cMsg.swLicCtrlTpCd_H1.getValue());
//        }
//        if (ZYPCommonFunc.hasValue(cMsg.swCatgCd_H1)) {
//	        ssmParam.put("swCatgCd", cMsg.swCatgCd_H1.getValue());
//        }
//        if (ZYPCommonFunc.hasValue(cMsg.xxChkBox_EC) && FLG_ON_Y.equals(cMsg.xxChkBox_EC.getValue())) {
//	        ssmParam.put("intntConnSwCtrlFlg", FLG_ON_Y);
//        } else {
//	        ssmParam.put("intntConnSwCtrlFlg", FLG_OFF_N);
//        }
//        return getSsmEZDClient().queryObject("getIntgProdCatgConv", ssmParam);
//    }
    
    //QC#12578
    public S21SsmEZDResult getInventoryCountForSerial(String glblCmpyCd, String mdseCd, boolean invtyFlg) {
        Map<String, String> cond = new HashMap<String, String>();
        cond.put("glblCmpyCd", glblCmpyCd);
        cond.put("mdseCd", mdseCd);
        if (invtyFlg) {
            cond.put("invtyFlg", "Y");
        }
        return getSsmEZDClient().queryObject("getInventoryCountForSerial", cond);
    }
    //QC#10449
    public S21SsmEZDResult getRetailSubWhName(String glblCmpyCd, String rtlSwhCd, String slsDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rtlSwhCd", rtlSwhCd);
        ssmParam.put("slsDt", slsDt);
        return getSsmEZDClient().queryObject("getRetailSubWhName", ssmParam);
    }
    //QC#10449
    public S21SsmEZDResult getRetailSubWh(String glblCmpyCd, String rtlWhCd, String rtlSwhCd, String slsDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rtlWhCd", rtlWhCd);
        ssmParam.put("rtlSwhCd", rtlSwhCd);
        ssmParam.put("slsDt", slsDt);
        return getSsmEZDClient().queryObject("getRetailSubWh", ssmParam);
    }
    public S21SsmEZDResult getIntgProdCatgConvList(String glblCmpyCd, String mdseItemTpCd, String coaMdseTpCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseItemTpCd", mdseItemTpCd);
        ssmParam.put("coaMdseTpCd", coaMdseTpCd);
        return getSsmEZDClient().queryObjectList("getIntgProdCatgConvList", ssmParam);
    }
    public S21SsmEZDResult getTransactionDataCPOAndPO(String glblCmpyCd, String mdseCd) {
        // START 2021/01/07 J.Evangelista [QC#58090,MOD]
//        Map<String, String> cond = new HashMap<String, String>();
        Map<String, Object> cond = new HashMap<String, Object>();
        // END   2021/01/07 J.Evangelista [QC#58090,MOD]        
        cond.put("glblCmpyCd", glblCmpyCd);
        cond.put("mdseCd", mdseCd);
        if (mdseCd.length() > 8) {
	        cond.put("mdseCd8Digit", mdseCd.substring(0, 8));
        }
        // START 2021/01/07 J.Evangelista [QC#58090,ADD]
        cond.put("ordLineStsCd", new String[] {ORD_LINE_STS.CLOSED, ORD_LINE_STS.CANCELLED});
        cond.put("rtrnLineStsCd", new String[] {RTRN_LINE_STS.CLOSED, RTRN_LINE_STS.CANCELLED});
        cond.put("poStsCd", new String[] {PO_STS.CLOSED, PO_STS.CANCELLED});
        // END   2021/01/07 J.Evangelista [QC#58090,ADD]        
        return getSsmEZDClient().queryObject("getTransactionDataCPOAndPO", cond);
    }
    public S21SsmEZDResult getMachineMaster(String glblCmpyCd, String mdseCd) {
        Map<String, String> cond = new HashMap<String, String>();
        cond.put("glblCmpyCd", glblCmpyCd);
        cond.put("mdseCd", mdseCd);
        return getSsmEZDClient().queryObject("getMachineMaster", cond);
    }
    
    @SuppressWarnings("unchecked")
    public List<ORD_TAKE_MDSETMsg> getDuplicateOrdTakeMdse(String glblCmpyCd, String mdseCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", mdseCd);
        S21SsmEZDResult rslt = getSsmEZDClient().queryObjectList("getDuplicateOrdTakeMdse", ssmParam);
        if (rslt.isCodeNormal()) {
            return (List<ORD_TAKE_MDSETMsg>) rslt.getResultObject();
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<AMER_MSTRTMsg> getDuplicateAmerMstr(String glblCmpyCd, String mdseCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", mdseCd);
        S21SsmEZDResult rslt = getSsmEZDClient().queryObjectList("getDuplicateAmerMstr", ssmParam);
        if (rslt.isCodeNormal()) {
            return (List<AMER_MSTRTMsg>) rslt.getResultObject();
        } else {
            return null;
        }
    }

    public S21SsmEZDResult getDuplicateS21Parts(String glblCmpyCd, String mdseCd, String cusaPartsViewName) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", mdseCd);
        ssmParam.put("cusaPartsViewName", cusaPartsViewName);
        return getSsmEZDClient().queryObjectList("getDuplicateS21Parts", ssmParam);
    }
    /**
     * get query result - id:getS21Parts
     * @param mdseCd - merchandise code
     * @return query result
     */
    public S21SsmEZDResult getS21Parts(String glblCmpyCd, String mdseCd, String cusaPartsViewName) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", mdseCd);
        ssmParam.put("cusaPartsViewName", cusaPartsViewName);
        
        return getSsmEZDClient().queryObject("getS21Parts", ssmParam);
    }
    
    public S21SsmEZDResult getBasePkgUomNm(String glblCmpyCd, String pkgUomClsCd) {
        Map<String, String> cond = new HashMap<String, String>();
        cond.put("glblCmpyCd", glblCmpyCd);
        cond.put("pkgUomClsCd", pkgUomClsCd);
        return getSsmEZDClient().queryObject("getBasePkgUomNm", cond);
    }
    public S21SsmEZDResult getBasePkgUom(String glblCmpyCd, String pkgUomClsCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("pkgUomClsCd", pkgUomClsCd);
        return getSsmEZDClient().queryObject("getBasePkgUom", ssmParam);
    }
    //QC#9346
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getMdseTpValSet(String glblCmpyCd, String mdseTpCtxTpCd, String mdseItemTpCd, String coaMdseTpCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseTpCtxTpCd", mdseTpCtxTpCd);
        ssmParam.put("mdseItemTpCd", mdseItemTpCd);
        ssmParam.put("coaMdseTpCd", coaMdseTpCd);
        S21SsmEZDResult rslt = getSsmEZDClient().queryObjectList("getMdseTpValSet", ssmParam);
        if (rslt.isCodeNormal()) {
            return (List<Map<String, Object>>) rslt.getResultObject();
        } else {
            return null;
        }
    }
    @SuppressWarnings("unchecked")
    public Map<String, Object> getCusaMdseView(String glblCmpyCd, String mdseCd, String cusaMdseTblaName, String cusaMdseStorePkgTableName, String cusaMdseSftyInfoTableName) {
        Map<String, String> cond = new HashMap<String, String>();
        cond.put("glblCmpyCd", glblCmpyCd);
        cond.put("mdseCd", mdseCd);
        cond.put("cusaMdseTblaName", cusaMdseTblaName);
        cond.put("cusaMdseStorePkgTableName", cusaMdseStorePkgTableName);
        cond.put("cusaMdseSftyInfoTableName", cusaMdseSftyInfoTableName);
        S21SsmEZDResult rslt = getSsmEZDClient().queryObject("getCusaMdseView", cond);
        if (rslt.isCodeNormal()) {
            return (Map<String, Object>) rslt.getResultObject();
        } else {
            return null;
        }
    }
    @SuppressWarnings("unchecked")
    public Map<String, Object> getCusaPartsView(String mdseCd, String cusaPartsViewName) {
        Map<String, String> cond = new HashMap<String, String>();
        cond.put("mdseCd", mdseCd);
        cond.put("cusaPartsViewName", cusaPartsViewName);
        S21SsmEZDResult rslt = getSsmEZDClient().queryObject("getCusaPartsView", cond);
        if (rslt.isCodeNormal()) {
            return (Map<String, Object>) rslt.getResultObject();
        } else {
            return null;
        }
    }
    public S21SsmEZDResult setRtrnVndList(NMAL0110CMsg cMsg, String glblCmpyCd, String rtrnCtrlTpCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rtrnCtrlTpCd", rtrnCtrlTpCd);
        return getSsmEZDClient().queryObjectList("setRtrnVndList", ssmParam);
    }
    public S21SsmEZDResult existsAllMdseV(String glblCmpyCd, String mdseCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", mdseCd);
        return getSsmEZDClient().queryObject("existsAllMdseV", ssmParam);
    }
    
    public S21SsmEZDResult getRtrnParntVnd(String glblCmpyCd, String vndCd) {
        Map<String, String> cond = new HashMap<String, String>();
        cond.put("glblCmpyCd", glblCmpyCd);
        cond.put("vndCd", vndCd);
        return getSsmEZDClient().queryObject("getRtrnParntVnd", cond);
    }
    
    public S21SsmEZDResult getIntgProdCatgConvPriorityList(NMAL0110CMsg cMsg, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        if (ZYPCommonFunc.hasValue(cMsg.mdseItemTpCd_H1)) {
	        ssmParam.put("mdseItemTpCd", cMsg.mdseItemTpCd_H1.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.coaMdseTpCd_H1)) {
	        ssmParam.put("coaMdseTpCd", cMsg.coaMdseTpCd_H1.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.swLicCtrlTpCd_H1)) {
	        ssmParam.put("swLicCtrlTpCd", cMsg.swLicCtrlTpCd_H1.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.swCatgCd_H1)) {
	        ssmParam.put("swCatgCd", cMsg.swCatgCd_H1.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMsg.xxChkBox_EC) && FLG_ON_Y.equals(cMsg.xxChkBox_EC.getValue())) {
	        ssmParam.put("intntConnSwCtrlFlg", FLG_ON_Y);
        } else {
	        ssmParam.put("intntConnSwCtrlFlg", FLG_OFF_N);
        }
        return getSsmEZDClient().queryObjectList("getIntgProdCatgConvPriorityList", ssmParam);
    }

    // QC#3579
    S21SsmEZDResult getItemHistoryReportConfig(NMAL0110CMsg cMsg) {
        List<String> listFuncId = getUserProfileService().getAuthorizedFunctionCodeListForBizAppId(BUSINESS_ID);
        List<String> listBomFuncId = getUserProfileService().getAuthorizedFunctionCodeListForBizAppId(BOM_BUSINESS_ID);
        listFuncId.addAll(listBomFuncId);
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.getGlobalCompanyCode());
        ssmParam.put("dsHistRptId", ITEM_HIST_RPT_ID);
        ssmParam.put("listFuncId", listFuncId);
        return getSsmEZDClient().queryObjectList("getItemHistoryReportConfig", ssmParam);
    }

    S21SsmEZDResult getItemHistory(NMAL0110CMsg cMsg, String sql) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("sql", sql);
        return getSsmEZDClient().queryObjectList("getItemHistory", ssmParam);
    }
    
    //QC#5580
    public S21SsmEZDResult getSupdReln(String sFromMdseCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("supdFromMdseCd", sFromMdseCd);
        return getSsmEZDClient().queryObjectList("getSupdRelnList", params);
    }
    
    //QC#7875
    @SuppressWarnings("unchecked")
    public Map<String, Object> getCusaMdseTp(String mdseCd, String cusaMdseTblaName) {
        Map<String, String> cond = new HashMap<String, String>();
        cond.put("glblCmpyCd", "ABR");
        cond.put("mdseCd", mdseCd);
        cond.put("cusaMdseTblaName", cusaMdseTblaName);
        S21SsmEZDResult rslt = getSsmEZDClient().queryObject("getCusaMdseTp", cond);
        if (rslt.isCodeNormal()) {
            return (Map<String, Object>) rslt.getResultObject();
        } else {
            return null;
        }
    }
    //QC#4203, QC#10449
    @SuppressWarnings("unchecked")
    public Map<String, Object> getRtrnCtrlTpVndReln(String glblCmpyCd, String rtrnCtrlTpCd, String rtrnToVndCd, String rtrnWhCd, String slsDt) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("rtrnCtrlTpCd", rtrnCtrlTpCd);
        params.put("rtrnToVndCd", rtrnToVndCd);
        params.put("rtrnWhCd", rtrnWhCd);
        params.put("slsDt", slsDt);

        S21SsmEZDResult rslt = getSsmEZDClient().queryObject("getRtrnCtrlTpVndReln", params);
        if (rslt.isCodeNormal()) {
            return (Map<String, Object>) rslt.getResultObject();
        } else {
            return null;
        }
    }
    //QC#4203
    @SuppressWarnings("unchecked")
    public Map<String, Object> getPoVndV(String glblCmpyCd, String rtrnToVndCd, String rtrnToPrntVndCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("rtrnToVndCd", rtrnToVndCd);
        params.put("rtrnToPrntVndCd", rtrnToPrntVndCd);

        S21SsmEZDResult rslt = getSsmEZDClient().queryObject("getPoVndV", params);
        if (rslt.isCodeNormal()) {
            return (Map<String, Object>) rslt.getResultObject();
        } else {
            return null;
        }
    }
    //QC#10449
    public S21SsmEZDResult getRtlWh(String glblCmpyCd, String rtlWhCd, String slsDt) {
        Map<String, String> cond = new HashMap<String, String>();
        cond.put("glblCmpyCd", glblCmpyCd);
        cond.put("rtlWhCd", rtlWhCd);
        cond.put("slsDt", slsDt);
        return getSsmEZDClient().queryObject("getRtlWh", cond);
    }
    //QC#9823, QC#10129
    public S21SsmEZDResult getCmpsn(String glblCmpyCd, String mdseCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("mdseCd", mdseCd);
        return getSsmEZDClient().queryObject("getCmpsn", params);
    }

    // QC#25789
    public String getSvcItemFlg(String mdseItemTpCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("mdseItemTpCd", mdseItemTpCd);
        params.put("CPO_SVC_MDSE_QLFY_ITEM_TP", "CPO_SVC_MDSE_QLFY_ITEM_TP");
        params.put("CPO_SVC_ADDL_CHRG_QLFY_ITEM_TP", "CPO_SVC_ADDL_CHRG_QLFY_ITEM_TP");
        return (String) getSsmEZDClient().queryObject("getSvcItemFlg", params).getResultObject();
    }

    // 2019/10/23 QC#51964 Add Start
    public List<Map<String, String>> getCoaAcct(String mdseTpCd) {
        Map<String, Object> ssmParamCheckOrderReturn = new HashMap<String, Object>();
        ssmParamCheckOrderReturn.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParamCheckOrderReturn.put("invtyAcctFlgY", ZYPConstant.FLG_ON_Y);
        ssmParamCheckOrderReturn.put("projAcctTpCrRibl", COA_PROJ_ACCT_TP.CR_RBL);
        ssmParamCheckOrderReturn.put("mdseTpCd", mdseTpCd);
        S21SsmEZDResult rslt = getSsmEZDClient().queryObjectList("getCoaAcct", ssmParamCheckOrderReturn);
        if (rslt.isCodeNormal()) {
            return (List<Map<String, String>>) rslt.getResultObject();
        } else {
            return null;
        }
    }
    // 2019/10/23 QC#51964 Add End

    // 2019/10/28 QC#53741 Add Start
    public S21SsmEZDResult getUbInInchLgUomCd(NMAL0110CMsg cMsg, String glblCmpyCd) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dplyFlg", ZYPConstant.FLG_ON_Y);
        return getSsmEZDClient().queryObjectList("getUbInInchLgUomCd", params);
    }

    public S21SsmEZDResult getUbInInchWdtUomCd(NMAL0110CMsg cMsg, String glblCmpyCd) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dplyFlg", ZYPConstant.FLG_ON_Y);
        return getSsmEZDClient().queryObjectList("getUbInInchWdtUomCd", params);
    }

    public S21SsmEZDResult getUbInInchHgtUomCd(NMAL0110CMsg cMsg, String glblCmpyCd) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dplyFlg", ZYPConstant.FLG_ON_Y);
        return getSsmEZDClient().queryObjectList("getUbInInchHgtUomCd", params);
    }

    public S21SsmEZDResult getUbInPoundWtUomCd(NMAL0110CMsg cMsg, String glblCmpyCd) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dplyFlg", ZYPConstant.FLG_ON_Y);
        return getSsmEZDClient().queryObjectList("getUbInPoundWtUomCd", params);
    }

    public PKG_UOM_BOX_EACHTMsg getEaDefUomCd(NMAL0110CMsg cMsg, String glblCmpyCd, String pkgUomCd) {
        PKG_UOM_BOX_EACHTMsg pkgUomBoxEachTMsg = new PKG_UOM_BOX_EACHTMsg();
        pkgUomBoxEachTMsg.glblCmpyCd.setValue(glblCmpyCd);
        pkgUomBoxEachTMsg.pkgUomCd.setValue(pkgUomCd);
        return (PKG_UOM_BOX_EACHTMsg) EZDTBLAccessor.findByKey(pkgUomBoxEachTMsg);
    }

    // 2019/10/28 QC#53741 Add End

    // 2019/10/31 QC#51967 Add Start
    public S21SsmEZDResult findCoaMdseTpAcctReln(String glblCmpyCd, String coaMdseTpCd, String coaAcctCatgCd, String coaAcctCd) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("coaMdseTpCd", coaMdseTpCd);
        params.put("coaAcctCatgCd", coaAcctCatgCd);
        params.put("coaAcctCd", coaAcctCd);
        return getSsmEZDClient().queryObjectList("findCoaMdseTpAcctReln", params);
    }
    // 2019/10/31 QC#51967 Add End

    // 2019/12/13 QC#54621 Add Start
    public BigDecimal getInventoryCount(String mdseCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("mdseCd", mdseCd);
        S21SsmEZDResult rs = getSsmEZDClient().queryObject("getInventoryCount", params);
        if (rs.isCodeNotFound()) {
            return BigDecimal.ZERO;
        }
        return (BigDecimal) rs.getResultObject();
    }
    // 2019/12/13 QC#54621 Add End

    // 2020/01/07 QC#55220 Add Start
    public String getActiveMdse(String glblCmpyCd, String mdsecd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", mdsecd);
        ssmParam.put("mdseItemStsCd", new String[] {MDSE_ITEM_STS.PRELAUNCH, MDSE_ITEM_STS.PRELAUNCH_APPROVED, MDSE_ITEM_STS.INACTIVE});
        S21SsmEZDResult rslt = getSsmEZDClient().queryObjectList("getActiveMdse", ssmParam);
        if (!rslt.isCodeNormal()) {
            return null;
        }

        return ((List<Map<String, String>>) rslt.getResultObject()).get(0).get("MDSE_CD");
    }
    // 2020/01/07 QC#55220 Add Start

    // 2022/02/17 QC#59693 Add Start
    /**
     * Get Pulldown list of SourceType
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSourceTypePulldownList(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getSourceTypePulldownList", ssmParam);
    }
    // 2022/02/17 QC#59693 Add End

    // START 2023/09/05 K.Watanabe [QC#53408,ADD]
    /**
     * Get Service Install Rule
     * @param cMsg NMAL0110CMsg
     * @param glblCmpyCd Global Company Code
     * @return Service Install Rule
     */
    // START 2023/12/12 K.Watanabe [QC#61300,MOD]
    //public S21SsmEZDResult getSvcIstlRuleList(NMAL0110CMsg cMsg, String glblCmpyCd) {
    public S21SsmEZDResult getSvcIstlRuleList(NMAL0110CMsg cMsg, String glblCmpyCd, String svcIstlRuleFlg) {
    // END 2023/12/12 K.Watanabe [QC#61300,MOD]
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        // START 2023/12/12 K.Watanabe [QC#61300,ADD]
        ssmParam.put("svcIstlRuleFlg", svcIstlRuleFlg);
        // END 2023/12/12 K.Watanabe [QC#61300,ADD]
        return getSsmEZDClient().queryObjectList("getSvcIstlRuleList", ssmParam);
    }

    /**
     * Get Service Install Call Group
     * @param cMsg NMAL0110CMsg
     * @param glblCmpyCd Global Company Code
     * @return Service Install Rule
     */
    // START 2023/12/12 K.Watanabe [QC#61300,MOD]
    //public S21SsmEZDResult getSvcIstlCallGrpList(NMAL0110CMsg cMsg, String glblCmpyCd) {
    public S21SsmEZDResult getSvcIstlCallGrpList(NMAL0110CMsg cMsg, String glblCmpyCd, String svcIstlCallGrpFlg) {
    // END 2023/12/12 K.Watanabe [QC#61300,MOD]
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        // START 2023/12/12 K.Watanabe [QC#61300,ADD]
        ssmParam.put("svcIstlCallGrpFlg", svcIstlCallGrpFlg);
        // END 2023/12/12 K.Watanabe [QC#61300,ADD]
        return getSsmEZDClient().queryObjectList("getSvcIstlCallGrpList", ssmParam);
    }
    // END 2023/09/05 K.Watanabe [QC#53408,ADD]
}
