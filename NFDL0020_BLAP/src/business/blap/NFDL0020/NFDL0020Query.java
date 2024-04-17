package business.blap.NFDL0020;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import business.blap.NFDL0020.constant.NFDL0020Constant;
import business.db.CLT_WRK_ITEMTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_WRK_ITEM_STS;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Collection Detail Screen
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 2016/06/16   Hitachi         K.Kojima        Update          QC#10200
 * 2016/09/26   Hitachi         K.Kojima        Update          QC#13907
 * 2018/03/15   Hitachi         J.Kim           Update          QC#20945
 * 2018/05/16   Fujitsu         Y.Matsui        Update          QC#24329
 * 2021/05/25   CITS            G.Delgado       Update          QC#58704
 * 2021/11/11   CITS            G.Delgado       Update          QC#55788
 * 2022/03/29   CITS            T.Omura         Update          QC#59011
 * 2022/06/02   CITS            D.Mamaril       Update          QC#60131
 *</pre>
 */
public class NFDL0020Query extends S21SsmEZDQuerySupport implements NFDL0020Constant {

    /**
     * Singleton instance.
     */
    private static final NFDL0020Query myInstance = new NFDL0020Query();

    /**
     * Constructor
     */
    public NFDL0020Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NFDL0020Query
     */
    public static NFDL0020Query getInstance() {
        return myInstance;
    }

    /**
     * @param map Map
     * @param sMsg NFDL0020SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCollectionHeader(Map map, NFDL0020CMsg cMsg) {
        return getSsmEZDClient().queryEZDMsg("getCollectionHeader", map, cMsg);
    }

    // START 2022/03/29 [QC#59011,Add]
    /**
     * @param map Map
     * @param sMsg NFDL0020SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getLastPaymentData(Map map, NFDL0020CMsg cMsg) {
        return getSsmEZDClient().queryEZDMsg("getLastPaymentData", map, cMsg);
    }
    // END 2022/03/29 [QC#59011,Add]

    /**
     * @param map Map
     * @param sMsg NFDL0020SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCollectionNoteSpecial(Map map) {
        return getSsmEZDClient().queryObject("getCollectionNoteSpecial", map);
    }

    /**
     * @param map Map
     * @param sMsg NFDL0020SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCollectionNote(Map map, NFDL0020SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("getCollectionNote", map, sMsg.F);    
    }

    /**
     * @param map Map
     * @param sMsg NFDL0020SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTransaction(Map map, NFDL0020SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("getTransaction", map, sMsg.A);    
    }

    /**
     * @param map Map
     * @param sMsg NFDL0020SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCollectionTask(Map map, NFDL0020SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("getCollectionTask", map, sMsg.G);    
    }

    /**
     * @param map Map
     * @param sMsg NFDL0020SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCollectionContract(Map map, NFDL0020SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("getCollectionContract", map, sMsg.B);    
    }

    /**
     * @param map Map
     * @param sMsg NFDL0020SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCollectionStrategy(Map map, NFDL0020CMsg cMsg) {
        return getSsmEZDClient().queryEZDMsgArray("getCollectionStrategy", map, cMsg.C);    
    }

    /**
     * @param map Map
     * @param sMsg NFDL0020SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCollectionStrategyWrkItem(Map map, NFDL0020CMsg cMsg) {
        return getSsmEZDClient().queryEZDMsgArray("getCollectionStrategyWrkItem", map, cMsg.D);    
    }

    /**
     * @param map Map
     * @param sMsg NFDL0020SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getTrxTypeList(Map map) {
        return getSsmEZDClient().queryObjectList("getTrxTypeList", map,-1, -1);
    }

    /**
     * @param map Map
     * @param sMsg NFDL0020SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContactTypeCodeList(Map map) {
        return getSsmEZDClient().queryObjectList("getContactTypeCodeList", map,-1, -1);
    }

    /**
     * @param map Map
     * @param sMsg NFDL0020SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContactMtrBill(Map map) {
        return getSsmEZDClient().queryObjectList("getContactMtrBill", map,-1, -1);
    }

    /**
     * @param map Map
     * @param sMsg NFDL0020SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContactPrcEff(Map map) {
        return getSsmEZDClient().queryObjectList("getContactPrcEff", map,-1, -1);
    }

    // START 2016/06/16 K.Kojima [QC#10200,ADD]
    /**
     * @param map Map
     * @param sMsg NFDL0020SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCollectionPorsonName(Map map) {
        return getSsmEZDClient().queryObject("getCollectionPorsonName", map);
    }
    // END 2016/06/16 K.Kojima [QC#10200,ADD]

    // START 2016/09/26 K.Kojima [QC#13907,ADD]
    /**
     * getCollectionStrategyWrkItemForStatusCheck
     * @param map Map
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCollectionStrategyWrkItemForStatusCheck(Map map) {
        return getSsmEZDClient().queryObjectList("getCollectionStrategyWrkItemForStatusCheck", map);
    }
    // END 2016/09/26 K.Kojima [QC#13907,ADD]

    // START 2018/03/15 J.Kim [QC#20945,ADD]
    /**
     * getAdjHistory
     * @param map Map
     * @param sMsg NFDL0020SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAdjHistory(Map map, NFDL0020SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("getAdjHistory", map, sMsg.H);
    }
    // END 2018/03/15 J.Kim [QC#20945,ADD]

    // START 2018/05/11 J.Kim [QC#21426,ADD]
    /**
     * findByKeyForCltWrkItem
     * @param glblCmpyCd String
     * @return glblCmpyCd
     */
    public static CLT_WRK_ITEMTMsg findByKeyForCltWrkItem(String glblCmpyCd, String cltWrkItemCd) {

        CLT_WRK_ITEMTMsg inTMsg = new CLT_WRK_ITEMTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.cltWrkItemCd, cltWrkItemCd);
        CLT_WRK_ITEMTMsg outTMsg = (CLT_WRK_ITEMTMsg) S21FastTBLAccessor.findByKey(inTMsg);

        if (outTMsg == null) {
            return null;
        }

        return outTMsg;
    }
    // END 2018/05/11 J.Kim [QC#21426,ADD]

    // START 2018/05/16 [QC#24329,ADD]
    /**
     * @param map Map
     * @param sMsg NFDL0020SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getStatement(Map map, NFDL0020SMsg sMsg) {
        return getSsmEZDClient().queryEZDMsgArray("getStatement", map, sMsg.J);
    }
    // END 2018/05/16 [QC#24329,ADD]

    // START 2021/05/25 G.Delgado [QC#58704,ADD]
    /**
     * Get collector from CLT_STRGY_TRX
     * @param glblCmpyCd String
     * @param cltStrgyTrxPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCollectorFromCltStrgyTrx(String glblCmpyCd, BigDecimal cltStrgyTrxPk) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("glblCmpyCd", glblCmpyCd);
        map.put("cltStrgyTrxPk", cltStrgyTrxPk);

        return getSsmEZDClient().queryObject("getCollectorFromCltStrgyTrx", map);
    }

    /**
     * Get latest Actual Comp Date from CLT_STRGY_WRK_ITEM_TRX
     * @param glblCmpyCd String
     * @param cltStrgyTrxPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getStrgyWrkItemMaxActualCompDate(String glblCmpyCd, BigDecimal cltStrgyTrxPk) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("glblCmpyCd", glblCmpyCd);
        map.put("cltStrgyTrxPk", cltStrgyTrxPk);
        map.put("cltWrkItemStsCd", CLT_WRK_ITEM_STS.COMPLETED);

        return getSsmEZDClient().queryObject("getStrgyWrkItemMaxActualCompDate", map);
    }
    // END 2021/05/25 G.Delgado [QC#58704,ADD]
}
