/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0410;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.MDSETMsg;
import business.db.MKT_MDLTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Mods Detail
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Hitachi         M.Gotou         Create          N/A
 * 2018/03/08   Hitachi         K.Kojima        Update          QC#22479
 * 2018/05/30   Hitachi         U.Kim           Update          QC#22393
 *</pre>
 */
public final class NSBL0410Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSBL0410Query INSTANCE = new NSBL0410Query();

    /**
     * Constructor.
     */
    private NSBL0410Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSBL0410Query
     */
    public static NSBL0410Query getInstance() {
        return INSTANCE;
    }

    /**
     * get DtlItem Info
     * @param ssmParam Map<String, Object>
     * @param aSMsgArray NSBL0410_ASMsgArray
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDtlInfo(Map<String, Object> ssmParam, NSBL0410_ASMsgArray aSMsgArray) {
        return getSsmEZDClient().queryEZDMsgArray("getDtlInfo", ssmParam, aSMsgArray);
    }

    // START 2018/03/07 K.Kojima [QC#22479,DEL]
    // /**
    //  * get Merchandise Name
    //  * @param glblCmpyCd String
    //  * @param mdseCd BigDecimal
    //  * @return ALL_MDSE_VTMsg
    //  */
    // public static ALL_MDSE_VTMsgArray getALL_MDSE_V(String glblCmpyCd, String mdseCd) {
    //     ALL_MDSE_VTMsg param = new ALL_MDSE_VTMsg();
    //     param.setSQLID("003");
    //     param.setConditionValue("glblCmpyCd01", glblCmpyCd);
    //     param.setConditionValue("mdseCd01", mdseCd);
    //     ALL_MDSE_VTMsgArray result = (ALL_MDSE_VTMsgArray) EZDTBLAccessor.findByCondition(param);
    //     return result;
    // }
    // END 2018/03/07 K.Kojima [QC#22479,DEL]

    // START 2018/03/07 K.Kojima [QC#22479,ADD]
    /**
     * get Merchandise Name
     * @param glblCmpyCd String
     * @param mdseCd BigDecimal
     * @return MDSETMsg
     */
    public static MDSETMsg getMDSE(String glblCmpyCd, String mdseCd) {
        MDSETMsg param = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(param.mdseCd, mdseCd);
        MDSETMsg result = (MDSETMsg) EZDTBLAccessor.findByKey(param);
        return result;
    }
    // END 2018/03/07 K.Kojima [QC#22479,ADD]

    // START 2018/05/30 U.Kim [QC#22393, ADD]
    public MKT_MDLTMsg getMarketingModel(String glblCmpyCd, String mktMdlCd) {
        MKT_MDLTMsg inMsg = new MKT_MDLTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.mktMdlCd, mktMdlCd);
        return (MKT_MDLTMsg) EZDTBLAccessor.findByKey(inMsg);
    }

    public List<String> getMdseCdForItemTpMktMdl(NSBL0410CMsg cMsg, NSBL0410SMsg sMsg){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("mdseItemTpCd", cMsg.mdseItemTpCd.getValue());
        params.put("mktMdlCd", cMsg.mktMdlCd.getValue());
        params.put("limitCnt", sMsg.A.length() + 1);
        S21SsmEZDResult result = getSsmEZDClient().queryObjectList("getMdseCdForItemTpMktMdl", params);
        if (result.isCodeNormal()) {
            return (List<String>) result.getResultObject();
        } else {
            return null;
        }
    }
    // END 2018/05/30 U.Kim [QC#22393, ADD]
    

}
