package business.blap.NMAL0160;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import business.blap.NMAL0160.NMAL0160CMsg;
import business.blap.NMAL0160.NMAL0160Query;
import business.blap.NMAL0160.constant.NMAL0160Constant;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_COST_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_CST_UPD_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 * 11/22/2016   Fujitsu         N.Sugiura       Update          S21_NA#16026
 * 2018/02/13   Hitachi         J.Kim           Update          QC#23765
 * 2018/02/19   Hitachi         J.Kim           Update          QC#24259
 * 2020/10/13   CITS            K.Ogino         Update          QC#57843
 *</pre>
 */
public class NMAL0160Query extends S21SsmEZDQuerySupport implements NMAL0160Constant {

    /**
     * Singleton instance.
     */
    private static final NMAL0160Query myInstance = new NMAL0160Query();
    
    /**
     * Constructor.
     */
    private NMAL0160Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NMAL0160Query
     */
    public static NMAL0160Query getInstance() {
        return myInstance;
    }

    public S21SsmEZDResult getCostTypeList(NMAL0160CMsg cMsg, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        return getSsmEZDClient().queryObjectList("getCostTypeList", ssmParam);
    }
    
    public S21SsmEZDResult getCostStatusList(NMAL0160CMsg cMsg, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        List<String> mdseCstUpdStsCdList = new ArrayList<String>();
        mdseCstUpdStsCdList.add(MDSE_CST_UPD_STS.APPROVED);
        mdseCstUpdStsCdList.add(MDSE_CST_UPD_STS.CANCELLED);
        mdseCstUpdStsCdList.add(MDSE_CST_UPD_STS.PENDING);
        ssmParam.put("mdseCstUpdStsCdList", mdseCstUpdStsCdList);
        return getSsmEZDClient().queryObjectList("getCostStatusList", ssmParam);
    }

    // QC#57843 Mod. Add invtySnapShotDt Parameter.
    public S21SsmEZDResult search(NMAL0160CMsg cMsg, NMAL0160SMsg sMsg, String glblCmpyCd, String invtySnapShotDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("rowNum", sMsg.A.length() + 1);
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCstUpdTpCd", cMsg.mdseCstUpdTpCd_H1.getValue());  //Cost Update Type
        ssmParam.put("mdseCstUpdGrpTxt", cMsg.mdseCstUpdGrpTxt_H1.getValue());  //Cost Update Group
        ssmParam.put("mdseCstUpdStsCd", MDSE_CST_UPD_STS.PENDING);
        // START 2018/02/13 J.Kim [QC#23765,DEL]
        // ssmParam.put("stkSts", STK_STS.GOOD);
        // ssmParam.put("locSts", LOC_STS.DC_STOCK);
        // END 2018/02/13 J.Kim [QC#23765,DEL]
        // START 2018/02/13 J.Kim [QC#23765,ADD]
        ssmParam.put("stdCostTp", MDSE_COST_TP.STANDARD_COST);
        ssmParam.put("assetRecovCostTp", MDSE_COST_TP.ASSET_RECOVERY);
        // // QC#57843 Mod. Add invtySnapShotDt Parameter.
        ssmParam.put("invtySnapShotDt", invtySnapShotDt);
        // END 2018/02/13 J.Kim [QC#23765,ADD]
        return getSsmEZDClient().queryEZDMsgArray("search", ssmParam, sMsg.A);
    }

    // START 2018/02/19 J.Kim [QC#24259,DEL]
    // public S21SsmEZDResult getResultSummary(NMAL0160CMsg cMsg, String glblCmpyCd) {
    //    Map<String, Object> ssmParam = new HashMap<String, Object>();
    //    ssmParam.put("glblCmpyCd", glblCmpyCd);
    //    ssmParam.put("mdseCstUpdTpCd", cMsg.mdseCstUpdTpCd_H1.getValue());  //Cost Update Type
    //    ssmParam.put("mdseCstUpdGrpTxt", cMsg.mdseCstUpdGrpTxt_H1.getValue());  //Cost Update Group
    //    ssmParam.put("mdseCstUpdStsCd", MDSE_CST_UPD_STS.PENDING);
    //    // START 2018/02/13 J.Kim [QC#23765,DEL]
    //    // ssmParam.put("stkSts", STK_STS.GOOD);
    //    // ssmParam.put("locSts", LOC_STS.DC_STOCK);
    //    // END 2018/02/13 J.Kim [QC#23765,DEL]
    //    // START 2018/02/13 J.Kim [QC#23765,ADD]
    //    ssmParam.put("stdCostTp", MDSE_COST_TP.STANDARD_COST);
    //    ssmParam.put("assetRecovCostTp", MDSE_COST_TP.ASSET_RECOVERY);
    //    // END 2018/02/13 J.Kim [QC#23765,ADD]
    //    return getSsmEZDClient().queryObject("getResultSummary", ssmParam);
    // }
    // END 2018/02/19 J.Kim [QC#24259,DEL]

    // 11/22/2016 S21_NA#16026 Add Start
    public boolean isDuplicateEffectiveDate(String mdseCd, String EffFrom, String glblCmpyCd)  {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", mdseCd);
        ssmParam.put("EffFrom", EffFrom);
        ssmParam.put("mdseCstUpdStsCd", MDSE_CST_UPD_STS.APPROVED);

        return (Integer) getSsmEZDClient().queryObject("isDuplicateEffectiveDate", ssmParam).getResultObject() > 0;

    }
    // 11/22/2016 S21_NA#16026 Add End

    // QC#57843 Add Get target date
    public S21SsmEZDResult getMaxSnapShotDt(String glblCmpyCd)  {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);

        return getSsmEZDClient().queryObject("getMaxSnapShotDt", ssmParam);

    }
}
