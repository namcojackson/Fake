/*
 * <Pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NFAL0280;

import java.util.Map;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * NFAL0280 Service Accrual Reversal Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2024/01/30   CITS            M.Okamura       Create          QC#62449
 *</pre>
 */
public final class NFAL0280Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NFAL0280Query INSTANCE = new NFAL0280Query();

    /**
     * Constructor.
     */
    private NFAL0280Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSBL0410Query
     */
    public static NFAL0280Query getInstance() {
        return INSTANCE;
    }

    /**
     * getSvcInvChrgTpCdList
     * @param prmMap prmMap
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcInvChrgTpCdList(Map<String, Object> prmMap) {
        return getSsmEZDClient().queryObjectList("getSvcInvChrgTpCdList", prmMap);
    }

    /**
     * getPulldownTargetDataToBeReversedList
     * @param prmMap prmMap
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult setPulldownTrgtDataToBeRvrsList(Map<String, Object> prmMap) {

        return getSsmEZDClient().queryObjectList("setPulldownTrgtDataToBeRvrsList", prmMap);
    }

    /**
     * getAjeAcrlIntfcList
     * @param prmMap prmMap
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAjeAcrlIntfcList(Map<String, Object> prmMap) {

        return getSsmEZDClient().queryObjectList("getAjeAcrlIntfcList", prmMap);
    }

    /**
     * checkAjeAcrlRvslIntfcDuplicate
     * @param prmMap prmMap
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult chkAjeAcrlRvslIntfcDup(Map<String, Object> prmMap) {

        return getSsmEZDClient().queryObject("chkAjeAcrlRvslIntfcDup", prmMap);
    }

    /**
     * checkAjeAcrlRvslIntfcDuplicate
     * @param prmMap prmMap
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAjeAcrlIntfcEzInTime(Map<String, Object> prmMap) {

        return getSsmEZDClient().queryObject("getAjeAcrlIntfcEzInTime", prmMap);
    }

}
