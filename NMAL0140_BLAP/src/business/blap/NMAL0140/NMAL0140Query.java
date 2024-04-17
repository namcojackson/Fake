package business.blap.NMAL0140;

import java.util.HashMap;
import java.util.Map;
import business.blap.NMAL0140.NMAL0140CMsg;
import business.blap.NMAL0140.NMAL0140Query;
import business.blap.NMAL0140.NMAL0140SMsg;
import business.blap.NMAL0140.constant.NMAL0140Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_COST_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 * 08/22/2018   Fujitsu         T.Aoi           Update          QC#25754
 *</pre>
 */
public class NMAL0140Query extends S21SsmEZDQuerySupport implements NMAL0140Constant {

    /**
     * Singleton instance.
     */
    private static final NMAL0140Query myInstance = new NMAL0140Query();
    
    /**
     * Constructor.
     */
    private NMAL0140Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NMAL0140Query
     */
    public static NMAL0140Query getInstance() {
        return myInstance;
    }

    public S21SsmEZDResult getCostTypeList(NMAL0140CMsg cMsg, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        return getSsmEZDClient().queryObjectList("getCostTypeList", ssmParam);
    }
    
    public S21SsmEZDResult search(NMAL0140CMsg cMsg, NMAL0140SMsg sMsg, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("rowNum", sMsg.A.length() + 1);
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCstUpdTpCd", cMsg.mdseCstUpdTpCd_H1.getValue());  //Cost Type
        ssmParam.put("mdseCstUpdGrpTxt", cMsg.mdseCstUpdGrpTxt_H1.getValue());  //Cost Update Group
        ssmParam.put("mdseCstUpdRefTxt", cMsg.mdseCstUpdRefTxt_H1.getValue());  //Reference
        ssmParam.put("mdseCstUpdRefId", cMsg.mdseCstUpdRefId_H1.getValue());  //Reference Id
        ssmParam.put("mdseCd", cMsg.mdseCd_H1.getValue());  //Item Number
        ssmParam.put("mdseItemTpCd", cMsg.mdseItemTpCd_H1.getValue());  //Item Type
        ssmParam.put("coaMdseTpCd", cMsg.coaMdseTpCd_H1.getValue());  //Merch Type
        ssmParam.put("coaProdCd", cMsg.coaProdCd_H1.getValue());  //Prod Code
        if (ZYPCommonFunc.hasValue(cMsg.rtlWhCd_H1) && ZYPCommonFunc.hasValue(cMsg.rtlSwhCd_H1)) {
	        ssmParam.put("rtlWhSwhCd", "Y");  //Warehouse - Sub Warehouse
            ssmParam.put("mdseCostTpAR", MDSE_COST_TP.ASSET_RECOVERY); // 2018/08/22 QC#25754 Add
        }
        if (ZYPCommonFunc.hasValue(cMsg.rtlWhCatgCd_H1) || ZYPCommonFunc.hasValue(cMsg.rtlWhCd_H1) || ZYPCommonFunc.hasValue(cMsg.rtlSwhCd_H1)) {
	        ssmParam.put("rtlWhSwhCatgCd", "Y");  //Category - Warehouse - Sub Warehouse
        }
        
        
        ssmParam.put("locStsCd", LOC_STS.DC_STOCK);  //DC Stock
        ssmParam.put("rtlWhCatgCd", cMsg.rtlWhCatgCd_H1.getValue());  //Warehouse Category
        ssmParam.put("rtlWhCd", cMsg.rtlWhCd_H1.getValue());  //Warehouse
        ssmParam.put("rtlSwhCd", cMsg.rtlSwhCd_H1.getValue());  //Sub Warehouse
        ssmParam.put("mdseCstUpdEffFromDt", cMsg.mdseCstUpdEffFromDt_H1.getValue());  //Eff From
        ssmParam.put("currentCostOnly", cMsg.xxChkBox_CC.getValue());  //Current Cost Only
        return getSsmEZDClient().queryEZDMsgArray( "search", ssmParam, sMsg.A);
    }
    
    public S21SsmEZDResult getSubWhList(String glblCmpyCd, String rtlSwhCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rtlSwhCd", rtlSwhCd);
        return getSsmEZDClient().queryObject("getSubWhList", ssmParam);
    }
    
    /**
     * getSavedSearchOptionList
     * @param usrId user id
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSavedSearchOptionList(String usrId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("srchOptAplId", BUSINESS_ID);
        params.put("srchOptUsrId", usrId);

        return getSsmEZDClient().queryObjectList("getSavedSearchOptionList", params);
    }
}
