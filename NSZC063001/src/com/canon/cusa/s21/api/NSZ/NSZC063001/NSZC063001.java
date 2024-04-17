/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC063001;

import static com.canon.cusa.s21.api.NSZ.NSZC063001.constant.NSZC063001Constant.NSZM0001E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.HLDTMsg;
import business.db.MDSETMsg;
import business.parts.NWXC005001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC063001.constant.NSZC063001Constant;
import com.canon.cusa.s21.common.NWX.NWXC005001.NWXC005001ValidationBean;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_RSN;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Supply Yield Validation API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/30/2015   Hitachi         J.Kim           Create
 * 03/15/2016   Hitachi         K.Kasai         Update          QC#5282
 * 03/16/2016   Hitachi         A.Kohinata      Update          QC#5540
 * 01/30/2017   Hitachi         N.Arai          Update          QC#17313
 * 05/21/2018   Fujitsu         H.Nagashima     Update          QC#25943
 * </pre>
 */
public class NSZC063001 extends S21ApiCommonBase {

    /**
     * S21SsmBatchClient
     */
    private S21SsmBatchClient ssmBatchClient = null;

    /**
     * Constructor
     */
    public NSZC063001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute
     * @param param NWXC005001ValidationBean
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NWXC005001ValidationBean param, final ONBATCH_TYPE onBatchType) {

        NWXC005001PMsg pMsg = param.getInputPMsg();
        if (pMsg != null) {
            ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum_O, (String) null);
            ZYPEZDItemValueSetter.setValue(pMsg.cpoDtlLineNum_O, (String) null);
            ZYPEZDItemValueSetter.setValue(pMsg.cpoDtlLineSubNum_O, (String) null);
            ZYPEZDItemValueSetter.setValue(pMsg.shpgPlnNum_O, (String) null);
            ZYPEZDItemValueSetter.setValue(pMsg.hldRsnCd, (String) null);
        }

        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);
        if (checkParameter(msgMap, param)) {
            checkSupplyYield(msgMap, param);
        }
        msgMap.flush();
    }

    private boolean checkParameter(S21ApiMessageMap msgMap, NWXC005001ValidationBean param) {

        NWXC005001PMsg pMsg = (NWXC005001PMsg) msgMap.getPmsg();

        if (!hasValue(pMsg.glblCmpyCd)) {
            msgMap.addXxMsgId(NSZM0001E);
            return false;
        }

        HLDTMsg condition = new HLDTMsg();
        condition.setSQLID("015");
        condition.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        condition.setConditionValue("hldRsnCd01", HLD_RSN.SUPPLY_YIELD_VALIDATION_HOLD);
        condition.setConditionValue("cpoOrdNum01", param.getInputPMsg().cpoOrdNum_I.getValue());
        condition.setConditionValue("cpoDtlLineNum01", param.getInputPMsg().cpoDtlLineNum_I.getValue());
        condition.setConditionValue("cpoDtlLineSubNum01", param.getInputPMsg().cpoDtlLineSubNum_I.getValue());
        condition.setConditionValue("relFlg01", ZYPConstant.FLG_OFF_N);
        int count = EZDTBLAccessor.count(condition);
        if (count > 0) {
            return false;
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    private void checkSupplyYield(S21ApiMessageMap msgMap, NWXC005001ValidationBean param) {

        NWXC005001PMsg pMsg = (NWXC005001PMsg) msgMap.getPmsg();

        BigDecimal imgSplyYieldCnt = null;
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String mdseCd = param.getCdTMsg().mdseCd.getValue();

        //QC#25943 add Start
        List<String> trgtImgSplyTpCdList = new ArrayList<String>();
        String trgtImgSplyTpCdCsv = ZYPCodeDataUtil.getVarCharConstValue(NSZC063001Constant.CONST_TRGT_IMG_SPLY_TP, pMsg.glblCmpyCd.getValue());
        if (trgtImgSplyTpCdCsv != null) {
            String[] trgtImgSplyTpCdArray = trgtImgSplyTpCdCsv.split(",");
            trgtImgSplyTpCdList = Arrays.asList(trgtImgSplyTpCdArray);
        }
        //QC#25943 add End

     // START 2017/01/30 N.Arai [QC#17313, MOD]
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("glblCmpyCd", glblCmpyCd);
        map.put("ordTakeMdseCd", mdseCd);

        //QC#25943 mod Start
//        BigDecimal resultTMsg = (BigDecimal) ssmBatchClient.queryObject("getSupplyYieldInfo", map);
//        if (ZYPCommonFunc.hasValue(resultTMsg)) {
//            imgSplyYieldCnt = resultTMsg;
        Map<String, Object> resultMap = (Map<String, Object>) ssmBatchClient.queryObject("getSupplyYieldInfo", map);
        if (resultMap != null) {  
            if (!trgtImgSplyTpCdList.contains((String) resultMap.get("IMG_SPLY_TP_CD"))) {
                return;
            }
            imgSplyYieldCnt = (BigDecimal) resultMap.get("IMG_SPLY_YIELD_CNT");
            //QC#25943 mod End

        } else {
            MDSETMsg inTMsg = new MDSETMsg();
            ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inTMsg.mdseCd, mdseCd);

            MDSETMsg mdseTMsg = (MDSETMsg) S21FastTBLAccessor.findByKey(inTMsg);
            if (mdseTMsg != null) {
                //QC#25943 add Start
                if (!trgtImgSplyTpCdList.contains(mdseTMsg.imgSplyTpCd.getValue())) {
                    return;
                }
                //QC#25943 add End
                imgSplyYieldCnt = mdseTMsg.imgSplyYieldCnt.getValue();
            }
        }

//        if (imgSplyYieldCnt == null) {
        if (!ZYPCommonFunc.hasValue(imgSplyYieldCnt)) {
     // END 2017/01/30 N.Arai [QC#17313, MOD]
            setOutputHoldInfo(param);
        }
    }

    private void setOutputHoldInfo(NWXC005001ValidationBean param) {

        ZYPEZDItemValueSetter.setValue(param.getInputPMsg().cpoOrdNum_O, param.getInputPMsg().cpoOrdNum_I.getValue());
        ZYPEZDItemValueSetter.setValue(param.getInputPMsg().cpoDtlLineNum_O, param.getInputPMsg().cpoDtlLineNum_I.getValue());
        ZYPEZDItemValueSetter.setValue(param.getInputPMsg().cpoDtlLineSubNum_O, param.getInputPMsg().cpoDtlLineSubNum_I.getValue());
        ZYPEZDItemValueSetter.setValue(param.getInputPMsg().hldRsnCd, HLD_RSN.SUPPLY_YIELD_VALIDATION_HOLD);
    }
}
