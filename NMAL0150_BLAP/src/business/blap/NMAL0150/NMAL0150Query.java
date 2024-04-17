package business.blap.NMAL0150;

import java.util.HashMap;
import java.math.BigDecimal;
import java.util.Map;
import business.blap.NMAL0150.NMAL0150CMsg;
import business.blap.NMAL0150.NMAL0150Query;
import business.blap.NMAL0150.constant.NMAL0150Constant;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 * 2022/05/30   Hitachi         A.Kohinata      Update          QC#55970
 *</pre>
 */
public class NMAL0150Query extends S21SsmEZDQuerySupport implements NMAL0150Constant {

    /**
     * Singleton instance.
     */
    private static final NMAL0150Query myInstance = new NMAL0150Query();
    
    /**
     * Constructor.
     */
    private NMAL0150Query() {
		super();
		//test
    }

    /**
     * Singleton instance getter.
     * @return NMAL0150Query
     */
    public static NMAL0150Query getInstance() {
        return myInstance;
    }

    public S21SsmEZDResult getCostTypeList(NMAL0150CMsg cMsg, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(cMsg.mdseCstUpdHistHdrPk_H1)) {
            ssmParam.put("manCratFlg", FLG_ON_Y);
        }
        return getSsmEZDClient().queryObjectList("getCostTypeList", ssmParam);
    }
    
    public S21SsmEZDResult getPkgUomPullDownList(NMAL0150CMsg cMsg, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        return getSsmEZDClient().queryObjectList("getPkgUomPullDownList", ssmParam);
    }
    
    // mod start 2022/05/30 QC#55970
    //public S21SsmEZDResult getMdseCstUpdDetail(NMAL0150CMsg cMsg, String glblCmpyCd) {
    //    Map<String, Object> ssmParam = new HashMap<String, Object>();
    //    ssmParam.put("rowNum", cMsg.A.length() + 1);
    //    ssmParam.put("glblCmpyCd", glblCmpyCd);
    //    ssmParam.put("mdseCstUpdHistHdrPk", cMsg.mdseCstUpdHistHdrPk_H1.getValue());
    //    return getSsmEZDClient().queryObjectList("getMdseCstUpdDetail", ssmParam);
    //}
    public S21SsmEZDResult getMdseCstUpdDetail(NMAL0150CMsg cMsg, NMAL0150SMsg sMsg, String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("rowNum", sMsg.A.length() + 1);
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCstUpdHistHdrPk", cMsg.mdseCstUpdHistHdrPk_H1.getValue());
        return getSsmEZDClient().queryEZDMsgArray("getMdseCstUpdDetail", ssmParam, sMsg.A);
    }
    // mod end 2022/05/30 QC#55970
    
    public S21SsmEZDResult getMdseInfoForBomTable(String glblCmpyCd, String mdseCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", mdseCd);
        return getSsmEZDClient().queryObject("getMdseInfoForBomTable", ssmParam);
    }

    public S21SsmEZDResult getCostUpdateGroupCount(String glblCmpyCd, String mdseCstUpdGrpTxt, BigDecimal mdseCstUpdHistHdrPk) {
        Map<String, Object> cond = new HashMap<String, Object>();
        cond.put("glblCmpyCd", glblCmpyCd);
        cond.put("mdseCstUpdGrpTxt", mdseCstUpdGrpTxt);
        if (ZYPCommonFunc.hasValue(mdseCstUpdHistHdrPk)) {
	        cond.put("mdseCstUpdHistHdrPk", mdseCstUpdHistHdrPk);
        }
        return getSsmEZDClient().queryObject("getCostUpdateGroupCount", cond);
    }

    public S21SsmEZDResult getStdCostExistsCount(String glblCmpyCd, String mdseCd, BigDecimal mdseCstUpdHistHdrPk) {
        Map<String, Object> cond = new HashMap<String, Object>();
        cond.put("glblCmpyCd", glblCmpyCd);
        cond.put("mdseCd", mdseCd);
        if (ZYPCommonFunc.hasValue(mdseCstUpdHistHdrPk)) {
	        cond.put("mdseCstUpdHistHdrPk", mdseCstUpdHistHdrPk);
        }
        return getSsmEZDClient().queryObject("getStdCostExistsCount", cond);
    }
    
    public S21SsmEZDResult getItemCostStatusExistsCount(String glblCmpyCd, String mdseCd, BigDecimal mdseCstUpdHistHdrPk, String stdCostRelnFlg) {
        Map<String, Object> cond = new HashMap<String, Object>();
        cond.put("glblCmpyCd", glblCmpyCd);
        cond.put("mdseCd", mdseCd);
        cond.put("stdCostRelnFlg", stdCostRelnFlg);
        if (ZYPCommonFunc.hasValue(mdseCstUpdHistHdrPk)) {
	        cond.put("mdseCstUpdHistHdrPk", mdseCstUpdHistHdrPk);
        }
        return getSsmEZDClient().queryObject("getItemCostStatusExistsCount", cond);
    }
}
