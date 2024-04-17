package business.blap.NSAL1170;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_MDLTMsg;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Model Escalation Rules Maintenance Popup.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/27   Hitachi         T.Nishimura     Create          N/A
 * 2016/12/13   Hitachi         T.Mizuki        Update          QC#16369
 *</pre>
 */
public final class NSAL1170Query extends S21SsmEZDQuerySupport {

    /** instance */
    private static final NSAL1170Query INSTANCE = new NSAL1170Query();

    /**
     * Private constructor
     */
    private NSAL1170Query() {
        super();
    }

    /**
     * Get instance
     * @return NSAL0510Query singleton instance
     */
    public static NSAL1170Query getInstance() {
        return INSTANCE;
    }

    /**
     * getSearchData.
     * @param sc Map<String, Object>
     * @param sMsg NSAL1170SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchData(Map<String, Object> sc, NSAL1170SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("getSearchData", sc, sMsg.A);
    }

    /**
     * getAddRowData.
     * @param sc Map<String, Object>
     * @param sMsg NSAL1170SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAddRowData(Map<String, Object> sc, NSAL1170SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("getAddRowData", sc, sMsg.Y);
    }

    // add start 2016/12/13 CSA QC#16369
    /**
     * Get DS_MDL
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return DS_MDLTMsg
     */
    public DS_MDLTMsg getDsMdl(String glblCmpyCd, BigDecimal mdlId) {
        DS_MDLTMsg inMsg = new DS_MDLTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.mdlId, mdlId);
        return (DS_MDLTMsg) EZDTBLAccessor.findByKey(inMsg);
    }
    // add end 2016/12/13 CSA QC#16369
}
