/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC182001;

import static com.canon.cusa.s21.api.NWZ.NWZC182001.constant.NWZC182001Constant.COLUMN_MDSE_CD;
import static com.canon.cusa.s21.api.NWZ.NWZC182001.constant.NWZC182001Constant.COLUMN_SER_NUM;
import static com.canon.cusa.s21.api.NWZ.NWZC182001.constant.NWZC182001Constant.COLUMN_SVC_CONFIG_MSTR_PK;
import static com.canon.cusa.s21.api.NWZ.NWZC182001.constant.NWZC182001Constant.COLUMN_SVC_MACH_MSTR_PK;
import static com.canon.cusa.s21.api.NWZ.NWZC182001.constant.NWZC182001Constant.COLUMN_SVC_MACH_MSTR_STS_CD;
import static com.canon.cusa.s21.api.NWZ.NWZC182001.constant.NWZC182001Constant.EXIST_MAIN_MACHINE_IN_LINE_N;
import static com.canon.cusa.s21.api.NWZ.NWZC182001.constant.NWZC182001Constant.NWZM0473E;
import static com.canon.cusa.s21.api.NWZ.NWZC182001.constant.NWZC182001Constant.NWZM1253E;
import static com.canon.cusa.s21.api.NWZ.NWZC182001.constant.NWZC182001Constant.NWZM1286E;
import static com.canon.cusa.s21.api.NWZ.NWZC182001.constant.NWZC182001Constant.NWZM1415E;
import static com.canon.cusa.s21.api.NWZ.NWZC182001.constant.NWZC182001Constant.NWZM1442E;
import static com.canon.cusa.s21.api.NWZ.NWZC182001.constant.NWZC182001Constant.NWZM1568E;
import static com.canon.cusa.s21.api.NWZ.NWZC182001.constant.NWZC182001Constant.NWZM1577E;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import business.db.ORD_TAKE_MDSETMsg;
import business.parts.NWZC182001PMsg;
import business.parts.NWZC182001_APMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;

/**
 * <pre>
 * Auto Add RMA API
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/19   Fujitsu         E.Yoshitake     Create          N/A
 * 2016/01/06   Fujitsu         S.Takami        Update          S21_NA#2585
 * 2016/05/19   Fujitsu         T.Murai         Update          S21_NA#5330
 * 2016/06/07   Fujitsu         T.Ishii         Update          S21_NA#5340
 * 2016/06/17   Fujitsu         H.Nagashima     Update          S21_NA#9787
 * 2018/11/14   Fujitsu         Y.Kanefusa      Update          S21_NA#29138
 * 2019/07/31   Fujitsu         S.Kosaka        Update          QC#51941
 *</pre>
 */
public class NWZC182001 extends S21ApiCommonBase {

    /**
     * Constructor.
     */
    public NWZC182001() {
        super();
    }

    /**
     * Auto Add RMA
     * @param params List<NWZC302001PMsg>
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(List<NWZC182001PMsg> params, ONBATCH_TYPE onBatchType) {

        for (NWZC182001PMsg msg : params) {
            execute(msg, onBatchType);
        }
    }

    /**
     * Execute
     * @param param NWZC182001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NWZC182001PMsg param, final ONBATCH_TYPE onBatchType) {

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        try {
            // Checking Input value
            if (!checkInput(msgMap)) {
                msgMap.flush();
                return;
            }

            // main
            doProcess(msgMap);

        } finally {
            msgMap.flush();
        }
    }

    /**
     * Check the input parameters. If an error occurs, add a message
     * to the Message Map.
     * @param msgMap Message Map
     * @return Results of the check.(false:error)
     */
    private boolean checkInput(S21ApiMessageMap msgMap) {
        NWZC182001PMsg param = (NWZC182001PMsg) msgMap.getPmsg();

        if (!ZYPCommonFunc.hasValue(param.glblCmpyCd)) {
            msgMap.addXxMsgId(NWZM0473E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(param.svcConfigMstrPk) && !ZYPCommonFunc.hasValue(param.serNum)) {
            msgMap.addXxMsgId("NWZM1578E");
            return false;
        }

        if (!ZYPCommonFunc.hasValue(param.dsOrdCatgCd)) {
            msgMap.addXxMsgId(NWZM1568E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(param.dsOrdTpCd)) {
            msgMap.addXxMsgId(NWZM1253E);
            return false;
        }

        if (ZYPCommonFunc.hasValue(param.svcConfigMstrPk)) {
            if (NWZC182001Query.getInstance().getSvcConfigMstr(param) == null) {
                msgMap.addXxMsgId(NWZM1442E);
                return false;
            }
        }

        if (NWZC182001Query.getInstance().getDsOrdCatg(param) == null) {
            msgMap.addXxMsgId(NWZM1415E);
            return false;
        }

        if (NWZC182001Query.getInstance().getDsOrdTp(param) == null) {
            msgMap.addXxMsgId(NWZM1286E);
            return false;
        }

        return true;
    }

    /**
     * Main process
     * @param msgMap S21ApiMessageMap
     */
    protected void doProcess(S21ApiMessageMap msgMap) {
        NWZC182001PMsg param = (NWZC182001PMsg) msgMap.getPmsg();

        List<Map<String, Object>> svcMachMstrList = null;

        // Exists Config Id
        if (ZYPCommonFunc.hasValue(param.svcConfigMstrPk)) {
            svcMachMstrList = NWZC182001Query.getInstance().getSvcMachMstrbyPk(param);
        } else {
            svcMachMstrList = NWZC182001Query.getInstance().getSvcMachMstrbySerNum(param);
        }

        if (svcMachMstrList.isEmpty()) {
            msgMap.addXxMsgId(NWZM1442E); // 2016/01/05 S21_NA#2585
            // Add
            return;
        }

        if (!chkSearchResult(msgMap, param, svcMachMstrList)) {
            return;
        }

        int idx = 0;
        for (Map<String, Object> map : svcMachMstrList) {
            // QC#29138 2018/11/14 Add Start
            if (!SVC_MACH_MSTR_STS.INSTALLED.equals(map.get(COLUMN_SVC_MACH_MSTR_STS_CD))
                    && !SVC_MACH_MSTR_STS.DEALER_SERVICE.equals(map.get(COLUMN_SVC_MACH_MSTR_STS_CD))) {
                continue;
            }
            // QC#29138 2018/11/14 Add End
            
            param.NWZC182002PMsg.no(idx).clear();
            ZYPEZDItemValueSetter.setValue(param.NWZC182002PMsg.no(idx).mdseCd, (String) map.get(COLUMN_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(param.NWZC182002PMsg.no(idx).serNum, (String) map.get(COLUMN_SER_NUM));
            ZYPEZDItemValueSetter.setValue(param.NWZC182002PMsg.no(idx).svcMachMstrPk, (BigDecimal) map.get(COLUMN_SVC_MACH_MSTR_PK)); // S21_NA#5340
            param.NWZC182002PMsg.setValidCount(++idx);

            if (!ZYPCommonFunc.hasValue(param.svcConfigMstrPk)) {
                ZYPEZDItemValueSetter.setValue(param.svcConfigMstrPk, (BigDecimal) map.get(COLUMN_SVC_CONFIG_MSTR_PK));
            }
        }
    }

    /**
     * checking Service Machine Master Info
     * @param msgMap S21ApiMessageMap
     * @param pMsg NWZC182001PMsg
     * @param svcMachMstrList SVC_MACH_MSTR INFO
     * @return result (false = check NG)
     */
    private boolean chkSearchResult(S21ApiMessageMap msgMap, NWZC182001PMsg pMsg, List<Map<String, Object>> svcMachMstrList) {

        for (Map<String, Object> map : svcMachMstrList) {

            // QC#29138 2018/11/14 Mod Start
////QC#9787 mod Start
////            if (!SVC_MACH_MSTR_STS.INSTALLED.equals(map.get(COLUMN_SVC_MACH_MSTR_STS_CD))) {
//            if (!SVC_MACH_MSTR_STS.INSTALLED.equals(map.get(COLUMN_SVC_MACH_MSTR_STS_CD))
//              && !SVC_MACH_MSTR_STS.DEALER_SERVICE.equals(map.get(COLUMN_SVC_MACH_MSTR_STS_CD))
//            ) {
//QC#9787 mod End
            // 2019/07/31 QC#51941 Add Start
            // If not exist main machine in line, check same mdse and serial only.
            //String ordTakeMdseCd = getOrdTakeMdse(pMsg.glblCmpyCd.getValue(), (String)map.get(COLUMN_MDSE_CD));
            if (ZYPCommonFunc.hasValue(pMsg.mainMachFlg) && EXIST_MAIN_MACHINE_IN_LINE_N.equals(pMsg.mainMachFlg.getValue())) {
                boolean unSameIbItemFlag = true;
                for (int i = 0; i < pMsg.A.getValidCount(); i++) {
                    NWZC182001_APMsg ibItemMsg = pMsg.A.no(i);
                    if ((ZYPCommonFunc.hasValue(ibItemMsg.mdseCd_A)
                            && ibItemMsg.mdseCd_A.getValue().equals((String)map.get(COLUMN_MDSE_CD)))
                        && (!ZYPCommonFunc.hasValue(ibItemMsg.serNum_A)
                            && !ZYPCommonFunc.hasValue((String)map.get(COLUMN_SER_NUM)))
                        || (ZYPCommonFunc.hasValue(ibItemMsg.serNum_A)
                            && ibItemMsg.serNum_A.getValue().equals(map.get(COLUMN_SER_NUM)))) {
                        unSameIbItemFlag = false;
                    }
                }
                if (unSameIbItemFlag) {
                    continue;
                }
            }
            // 2019/07/31 QC#51941 Add End
            if (SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION.equals(map.get(COLUMN_SVC_MACH_MSTR_STS_CD))
                    || SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL.equals(map.get(COLUMN_SVC_MACH_MSTR_STS_CD))) {
                // QC#29138 2018/11/14 Mod End
                msgMap.addXxMsgId(NWZM1577E);
                return false;

                // S21_NA#5330 Del Start
                // } else if
                // (!pMsg.dsOrdCatgCd.getValue().equals(map.get(COLUMN_DS_ORD_CATG_CD)))
                // {
                // msgMap.addXxMsgId(NWZM1575E);
                // return false;
                //
                // } else if
                // (!pMsg.dsOrdTpCd.getValue().equals(map.get(COLUMN_DS_ORD_TP_CD)))
                // {
                // msgMap.addXxMsgId(NWZM1576E);
                // return false;
                // S21_NA#5330 Del End
            }
        }
        return true;
    }

    // 2019/07/31 QC#51941 Add Start
    private static String getOrdTakeMdse(String glblCmpyCd, String mdseCd) {

        ORD_TAKE_MDSETMsg ordTakeMdseTMsg = new ORD_TAKE_MDSETMsg();
        int ordTakeCodeLen = ordTakeMdseTMsg.getAttr("ordTakeMdseCd").getDigit();
        String mdseCd8 = null;
        if (ordTakeCodeLen < mdseCd.length()) {
            // 10MDSE -> 8MDSE
            mdseCd8 = S21StringUtil.subStringByLength(mdseCd, 0, ordTakeCodeLen);
        } else {
            mdseCd8 = mdseCd;
        }

        ORD_TAKE_MDSETMsg tMsg = new ORD_TAKE_MDSETMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.ordTakeMdseCd, mdseCd8);
        tMsg = (ORD_TAKE_MDSETMsg) S21CacheTBLAccessor.findByKey(tMsg);
        if (tMsg != null) {
            return tMsg.ordTakeMdseCd.getValue();
        }

        return mdseCd;
    }
    // 2019/07/31 QC#51941 Add End
}
