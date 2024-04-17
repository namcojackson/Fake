/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1390;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/21   Fujitsu         T.Murai         Create          S21_NA#8661(Sol#004)
 * 2017/10/27   Hitachi         K.Kojima        Update          QC#21586
 *</pre>
 */
public final class NSAL1390Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NSAL1390Query INSTANCE = new NSAL1390Query();

    /**
     * Private constructor
     */
    private NSAL1390Query() {
        super();
    }

    /**
     * Get instance
     * @return NSAL1390Query singleton instance
     */
    public static NSAL1390Query getInstance() {
        return INSTANCE;
    }

    /**
     * Search
     * @param cMsg NSAL1390CMsg
     * @param sMsg NSAL1390SMsg
     * @return Meter Group List
     */
    public S21SsmEZDResult search(NSAL1390CMsg cMsg, NSAL1390SMsg sMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        ssmParam.put("lineBizTpCd", cMsg.lineBizTpCd_H.getValue());
        ssmParam.put("svcRgPk", cMsg.svcRgPk_H.getValue());
        ssmParam.put("svcContrBr", cMsg.svcContrBrCd_H.getValue());
        ssmParam.put("count", sMsg.A.length() + 1);

        return getSsmEZDClient().queryEZDMsgArray("search", ssmParam, sMsg.A);
    }

    /**
     * count LOB Region Branch Relation
     * @param cMsg NSAL1390CMsg
     * @param insMsg NSAL1390_ASMsg
     * @return Meter Group List
     */
    public Integer cntLobRgBrRelation(NSAL1390CMsg cMsg, NSAL1390_ASMsg insMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        ssmParam.put("lineBizTpCd", insMsg.lineBizTpCd_A.getValue());
        ssmParam.put("svcRgPk", insMsg.svcRgPk_A);
        ssmParam.put("svcContrBrCd", insMsg.svcContrBrCd_A.getValue());

        return (Integer) getSsmEZDClient().queryObject("cntLobRgBrRelation", ssmParam).getResultObject();
    }

    /**
     * get Branch Name List
     * @param cMsg NSAL1390CMsg
     * @param svcContrBrCd String
     * @return Meter Group List
     */
    public List<String> getSvcContrBrNmLike(NSAL1390CMsg cMsg, String svcContrBrCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        ssmParam.put("svcContrBrCd", svcContrBrCd);
        ssmParam.put("slsDt", cMsg.slsDt.getValue());

        return (List<String>) getSsmEZDClient().queryObjectList("getSvcContrBrNmLike", ssmParam).getResultObject();
    }

    // START 2017/10/27 K.Kojima [QC#21586,ADD]
    /**
     * getSvcLineBiz
     * @param glblCmpyCd String
     */
    public List<Map<String, Object>> getSvcLineBiz(String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("sysSrcCd", SYS_SRC.S21_SERVICE);
        S21SsmEZDResult res = getSsmEZDClient().queryObjectList("getSvcLineBiz", ssmParam);
        if (res == null || !res.isCodeNormal()) {
            return new ArrayList<Map<String, Object>>();
        }
        return (List<Map<String, Object>>) res.getResultObject();
    }
    // END 2017/10/27 K.Kojima [QC#21586,ADD]
}
