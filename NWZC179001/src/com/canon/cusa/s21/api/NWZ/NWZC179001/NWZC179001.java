package com.canon.cusa.s21.api.NWZ.NWZC179001;

import static com.canon.cusa.s21.api.NWZ.NWZC179001.constant.NWZC179001Constant.NWZM0188E;
import static com.canon.cusa.s21.api.NWZ.NWZC179001.constant.NWZC179001Constant.NWZM1584E;
import static com.canon.cusa.s21.api.NWZ.NWZC179001.constant.NWZC179001Constant.NWZM1585E;
import static com.canon.cusa.s21.api.NWZ.NWZC179001.constant.NWZC179001Constant.NWZM1586E;
import static com.canon.cusa.s21.api.NWZ.NWZC179001.constant.NWZC179001Constant.NWZM1588E;
import static com.canon.cusa.s21.api.NWZ.NWZC179001.constant.NWZC179001Constant.NWZM1589E;
import static com.canon.cusa.s21.api.NWZ.NWZC179001.constant.NWZC179001Constant.NWZM1590E;
import static com.canon.cusa.s21.api.NWZ.NWZC179001.constant.NWZC179001Constant.NWZM1591E;

import java.util.List;

import business.db.HLDTMsg;
import business.db.HLDTMsgArray;
import business.parts.NWXC005001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC179001.cache.DataCacheForTBLAccessor;
import com.canon.cusa.s21.api.NWZ.NWZC179001.cache.FindCondition;
import com.canon.cusa.s21.common.NWX.NWXC005001.NWXC005001ValidationBean;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_RSN;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;

/**
 * <pre>
 * ITT Outbound Hold API Constant
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/20   Fujitsu         C.Yokoi         Create          N/A
 * 2016/05/26   Fujitsu         S.Yamamoto      Create          S21_NA#9077
 * 2016/09/23   Fujitsu         Y.Taoka         Update          S21_NA#11280
 * 2016/10/13   Fujitsu         T.Yoshida       Create          S21_NA#14973 (For Performance)
 *</pre>
 */
public class NWZC179001 extends S21ApiCommonBase {

    /**
     * Constructor.
     */
    public NWZC179001() {
        super();
    }

    /**
     * ITT Outbound Hold API
     * @param params List<NWXC005001ValidationBean>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(List<NWXC005001ValidationBean> params, ONBATCH_TYPE onBatchType) {

        for (NWXC005001ValidationBean bean : params) {
            execute(bean, onBatchType);
        }
    }

    /**
     * Execute
     * @param bean NWXC005001ValidationBean
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NWXC005001ValidationBean bean, final ONBATCH_TYPE onBatchType) {

        NWXC005001PMsg pMsg = bean.getInputPMsg();
        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);

        try {
            // Checking Input value
            if (!checkInputParam(msgMap, bean)) {
                msgMap.flush();
                return;
            }

            // main
            doProcess(msgMap, bean);

        } finally {
            msgMap.flush();
        }
    }

    /**
     * Check the input parameters. If an error occurs, add a message to the Message Map.
     * @param msgMap Message Map
     * @param bean NWXC005001ValidationBean
     * @return Results of the check.(false:error)
     */
    private boolean checkInputParam(S21ApiMessageMap msgMap, NWXC005001ValidationBean bean) {
        NWXC005001PMsg param = (NWXC005001PMsg) msgMap.getPmsg();

        // Global Company Code
        if (!ZYPCommonFunc.hasValue(param.glblCmpyCd)) {
            msgMap.addXxMsgId(NWZM0188E);
            return false;
        }

        // Order Number
        if (!ZYPCommonFunc.hasValue(param.cpoOrdNum_I)) {
            msgMap.addXxMsgId(NWZM1584E);
            return false;
        }

        // Order Line Number
        if (!ZYPCommonFunc.hasValue(param.cpoDtlLineNum_I)) {
            msgMap.addXxMsgId(NWZM1585E);
            return false;
        }

        // Order Line Sub Number
        if (!ZYPCommonFunc.hasValue(param.cpoDtlLineSubNum_I)) {
            msgMap.addXxMsgId(NWZM1586E);
            return false;
        }

        // CPOTMsg
        if (bean.getCTMsg() == null) {
            msgMap.addXxMsgId(NWZM1588E);
            return false;
        }

        // CPO_DTLTMsg
        if (bean.getCdTMsg() == null) {
            msgMap.addXxMsgId(NWZM1589E);
            return false;
        }

        // DS_CPOTMsg
        if (bean.getDscTMsg() == null) {
            msgMap.addXxMsgId(NWZM1590E);
            return false;
        }

        // DS_CPO_DTLTMsg
        if (bean.getDscdTMsg() == null) {
            msgMap.addXxMsgId(NWZM1591E);
            return false;
        }

        return true;
    }

    /**
     * Main process
     * @param msgMap S21ApiMessageMap
     * @param bean NWXC005001ValidationBean
     */
    protected void doProcess(S21ApiMessageMap msgMap, NWXC005001ValidationBean bean) {
        final String glblCmpyCd = bean.getInputPMsg().glblCmpyCd.getValue();
        // final String ordNum = bean.getInputPMsg().cpoDtlLineNum_I.getValue();
        final String ordNum = bean.getInputPMsg().cpoOrdNum_I.getValue(); // S21_NA#11280 MOD
        final String lineNum = bean.getInputPMsg().cpoDtlLineNum_I.getValue();
        final String lineSubNum = bean.getInputPMsg().cpoDtlLineSubNum_I.getValue();
        final String ordLineSrcCd = bean.getDscdTMsg().ordLineSrcCd.getValue();

        // Exists Hold
        if (existsHld(glblCmpyCd, ordNum, lineNum, lineSubNum, HLD_RSN.ITT_OUTBOUND_HOLD)) {
            return;
        }

        // Exsists Order Line Source of ITT-Hold
        if (!existsOrdLineSrc(glblCmpyCd, ordLineSrcCd, ZYPConstant.FLG_ON_Y)) {
            return;
        }

        // Result Set
        ZYPEZDItemValueSetter.setValue(bean.getInputPMsg().cpoOrdNum_O, bean.getDscdTMsg().cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(bean.getInputPMsg().cpoDtlLineNum_O, bean.getDscdTMsg().cpoDtlLineNum);
        ZYPEZDItemValueSetter.setValue(bean.getInputPMsg().cpoDtlLineSubNum_O, bean.getDscdTMsg().cpoDtlLineSubNum);
        ZYPEZDItemValueSetter.setValue(bean.getInputPMsg().hldRsnCd, HLD_RSN.ITT_OUTBOUND_HOLD);
    }

    /**
     * existsHld
     * @param glblCmpyCd String
     * @param ordNum String
     * @param lineNum String
     * @param lineSubNum String
     * @param hldRsn String
     * @return boolean
     */
    private static boolean existsHld(String glblCmpyCd, String ordNum, String lineNum, String lineSubNum, String hldRsn) {
        HLDTMsg hldKey = new HLDTMsg();

        hldKey.setSQLID("002");
        hldKey.setConditionValue("glblCmpyCd01", glblCmpyCd);
        hldKey.setConditionValue("cpoOrdNum01", ordNum);
        hldKey.setConditionValue("cpoDtlLineNum01", lineNum);
        hldKey.setConditionValue("cpoDtlLineSubNum01", lineSubNum);
        hldKey.setConditionValue("hldRsnCd01", hldRsn);

        // 2016/05/26 S21_NA#9077
        return ((HLDTMsgArray) S21ApiTBLAccessor.findByCondition(hldKey)).getValidCount() > 0;
    }

    /**
     * existsOrdLineSrc
     * @param glblCmpyCd String
     * @param ordLineSrcCd String
     * @param ittHldFlg String
     * @return boolean
     */
    private static boolean existsOrdLineSrc(String glblCmpyCd, String ordLineSrcCd, String ittHldFlg) {
        // S21_NA#14973 Mod Start
//        ORD_LINE_SRCTMsg ordLineSrcKey = new ORD_LINE_SRCTMsg();
//
//        ordLineSrcKey.setSQLID("002");
//        ordLineSrcKey.setConditionValue("glblCmpyCd01", glblCmpyCd);
//        ordLineSrcKey.setConditionValue("ordLineSrcCd01", ordLineSrcCd);
//        ordLineSrcKey.setConditionValue("ittHldFlg01", ittHldFlg);
//
//        // 2016/05/26 S21_NA#9077
//        return ((ORD_LINE_SRCTMsgArray) S21ApiTBLAccessor.findByCondition(ordLineSrcKey)).getValidCount() > 0;

        final FindCondition condition = new FindCondition("002");
        condition.addCondition("glblCmpyCd01", glblCmpyCd);
        condition.addCondition("ordLineSrcCd01", ordLineSrcCd);
        condition.addCondition("ittHldFlg01", ittHldFlg);
        return DataCacheForTBLAccessor.getInstance().getOrdLineSrcTMsgArray(condition).getValidCount() > 0;
        // S21_NA#14973 Mod End
    }
}
