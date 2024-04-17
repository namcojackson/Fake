/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1260;

import static business.blap.NSAL1260.constant.NSAL1260Constant.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.BLLG_CYCLETMsg;
import business.db.BLLG_CYCLETMsgArray;
import business.db.MTR_READ_METHTMsg;
import business.db.MTR_READ_METHTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Contract Machine Upload
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/22   Hitachi         A.Kohinata      Create          N/A
 *</pre>
 */
public final class NSAL1260Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSAL1260Query INSTANCE = new NSAL1260Query();

    /**
     * Constructor.
     */
    private NSAL1260Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSAL1260Query
     */
    public static NSAL1260Query getInstance() {
        return INSTANCE;
    }

    /**
     * getHeaderInfo
     * @param cMsg NSAL1260CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getHeaderInfo(NSAL1260CMsg cMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("dsContrPk", cMsg.dsContrPk.getValue());

        return getSsmEZDClient().queryEZDMsg("getHeaderInfo", params, cMsg);
    }

    /**
     * getSvcMachMstr
     * @param cMsg NSAL1260CMsg
     * @param sMsgLine NSAL1260_ASMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcMachMstr(NSAL1260CMsg cMsg, NSAL1260_ASMsg sMsgLine) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("svcMachMstrPk", sMsgLine.svcMachMstrPk_A.getValue());
        params.put("serNum", sMsgLine.serNum_A.getValue());

        return getSsmEZDClient().queryObject("getSvcMachMstr", params);
    }

    /**
     * getContrCount
     * @param cMsg NSAL1260CMsg
     * @param sMsgLine NSAL1260_ASMsg
     * @return S21SsmEZDResult
     */
    public BigDecimal getContrCount(NSAL1260CMsg cMsg, NSAL1260_ASMsg sMsgLine) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        params.put("dsContrPk", cMsg.dsContrPk.getValue());
        params.put("svcMachMstrPk", sMsgLine.svcMachMstrPk_A.getValue());
        params.put("slsDt", cMsg.slsDt.getValue());
        params.put("maxDate", MAX_DATE);

        return (BigDecimal) getSsmEZDClient().queryObject("getContrCount", params).getResultObject();
    }

    /**
     * getBllgCycleCdByCd
     * @param glblCmpyCd String
     * @param bllgCycleCd String
     * @return BLLG_CYCLETMsg
     */
    public BLLG_CYCLETMsg getBllgCycleCdByCd(String glblCmpyCd, String bllgCycleCd) {
        BLLG_CYCLETMsg inMsg = new BLLG_CYCLETMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.bllgCycleCd, bllgCycleCd);

        return (BLLG_CYCLETMsg) EZDTBLAccessor.findByKey(inMsg);
    }

    /**
     * getBllgCycleCdByDescTxt
     * @param glblCmpyCd String
     * @param bllgCycleDescTxt String
     * @return BLLG_CYCLETMsg
     */
    public BLLG_CYCLETMsg getBllgCycleCdByDescTxt(String glblCmpyCd, String bllgCycleDescTxt) {
        BLLG_CYCLETMsg inMsg = new BLLG_CYCLETMsg();
        inMsg.setSQLID("004");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("bllgCycleDescTxt01", bllgCycleDescTxt);
        BLLG_CYCLETMsgArray tMsgArray = (BLLG_CYCLETMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (tMsgArray.length() > 0) {
            return tMsgArray.no(0);
        } else {
            return null;
        }
    }

    /**
     * getMtrReadMethCdByCd
     * @param glblCmpyCd String
     * @param mtrReadMethCd String
     * @return MTR_READ_METHTMsg
     */
    public MTR_READ_METHTMsg getMtrReadMethCdByCd(String glblCmpyCd, String mtrReadMethCd) {
        MTR_READ_METHTMsg inMsg = new MTR_READ_METHTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.mtrReadMethCd, mtrReadMethCd);

        return (MTR_READ_METHTMsg) EZDTBLAccessor.findByKey(inMsg);
    }

    /**
     * getMtrReadMethCdByDescTxt
     * @param glblCmpyCd String
     * @param mtrReadMethDescTxt String
     * @return MTR_READ_METHTMsg
     */
    public MTR_READ_METHTMsg getMtrReadMethCdByDescTxt(String glblCmpyCd, String mtrReadMethDescTxt) {
        MTR_READ_METHTMsg inMsg = new MTR_READ_METHTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("mtrReadMethDescTxt01", mtrReadMethDescTxt);
        MTR_READ_METHTMsgArray tMsgArray = (MTR_READ_METHTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (tMsgArray.length() > 0) {
            return tMsgArray.no(0);
        } else {
            return null;
        }
    }
}
