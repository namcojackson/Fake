/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC048001;

import static com.canon.cusa.s21.api.NSZ.NSZC048001.constant.NSZC048001Constant.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;

import business.db.MDSETMsg;
import business.db.MDSETMsgArray;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.ORD_TAKE_MDSETMsgArray;
import business.parts.NSZC048001PMsg;

import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Service Model API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/18/2015   Hitachi         T.Tsuchida      Create
 * 12/24/2015   Hitachi         T.Tomita        Update          CSA QC#2113
 * 03/09/2016   Hitachi         T.Tomita        Update          CSA QC#2113
 * 03/24/2016   Hitachi         M.Gotou         Update          CSA QC#4887
 * 01/12/2017   Hitachi         N.Arai          Update          QC#14614
 * 05/25/2017   Hitachi         K.Kitachi       Update          QC#18642
 * 06/14/2017   Hitachi         T.Mizuki        Update          QC#19027
 * 09/27/2017   Fujitsu         S.Ohki          Update          QC#21363
 * 2018/01/09   Hitachi         U.Kim           Update          QC#23352
 * 2018/02/16   Hitachi         K.Kojima        Update          QC#24210
 * 2018/06/14   Fujitsu         K.Ishizuka      Update          QC#24294
 * 2018/06/22   Fujitsu         S.Takami        Update          QC#25151
 * </pre>
 */
public class NSZC048001 extends S21ApiCommonBase {

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap = null;

    /** onBatTp */
    private ONBATCH_TYPE onBatType = null;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    // START 2018/01/09 U.Kim [QC#23352,ADD]
    private boolean isChildMode = false;

    // END 2018/01/09 U.Kim [QC#23352,ADD]

    // START 2018/02/16 K.Kojima [QC#24210,ADD]
    private List<String> checkedMdseCdList = null;
    // END 2018/02/16 K.Kojima [QC#24210,ADD]

    /**
     * Constructor
     */
    public NSZC048001() {
        super();
    }

    /**
     * Service Model API
     * @param pMsg {@link NSZC048001PMsg}
     * @param onBatTp Online Batch Type
     */
    public void execute(NSZC048001PMsg pMsg, final ONBATCH_TYPE onBatTp) {

        init(pMsg, onBatTp);

        if (!inputCheck(pMsg)) {
            return;
        }

        // START 2018/01/09 U.Kim [QC#23352,MOD]
        // execute(pMsg);
        if (!this.isChildMode) {
            execute(pMsg);
            checkChlidMode(pMsg);
        }

        if (this.isChildMode) {
            executeForChildMode(pMsg);
        }
        // END 2018/01/09 U.Kim [QC#23352,MOD]
    }

    private void init(NSZC048001PMsg pMsg, final ONBATCH_TYPE onBatTp) {

        msgMap = new S21ApiMessageMap(pMsg);

        this.onBatType = onBatTp;

        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        // START 2018/01/09 U.Kim [QC#23352,ADD]
        if (ZYPCommonFunc.hasValue(pMsg.prntMdseCd)) {
            this.isChildMode = false;
        } else {
            this.isChildMode = true;
        }
        // END 2018/01/09 U.Kim [QC#23352,ADD]

        // START 2018/02/16 K.Kojima [QC#24210,ADD]
        this.checkedMdseCdList = new ArrayList<String>();
        // END 2018/02/16 K.Kojima [QC#24210,ADD]
    }

    private boolean inputCheck(NSZC048001PMsg pMsg) {
        if (!mandatoryCheck(pMsg)) {
            return false;
        }

        if (!masterCheck(pMsg)) {
            return false;
        }

        return true;
    }

    private boolean mandatoryCheck(NSZC048001PMsg pMsg) {
        boolean retFlg = true;

        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            setErrMsg(pMsg, NSZM0401E, MSG_GLBL_CMPY_CD, MSG_INFO_IN_PRM);
            retFlg = false;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.slsDt)) {
            setErrMsg(pMsg, NSZM0401E, MSG_SLS_DT, MSG_INFO_IN_PRM);
            retFlg = false;
        }

        // START 2018/01/09 U.Kim [QC#23352,DEL]
        // if (!ZYPCommonFunc.hasValue(pMsg.prntMdseCd)) {
        // setErrMsg(pMsg, NSZM0401E, MSG_PRNT_MDSE_CD, MSG_INFO_IN_PRM);
        // retFlg = false;
        // }
        // END 2018/01/09 U.Kim [QC#23352,DEL]

        return retFlg;
    }

    private boolean masterCheck(NSZC048001PMsg pMsg) {
        boolean retFlg = true;

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String prntMdseCd = pMsg.prntMdseCd.getValue();
        // START 2018/01/09 U.Kim [QC#23352,MOD]
        // if (!mdseCheck(glblCmpyCd, prntMdseCd)) {
        if (ZYPCommonFunc.hasValue(prntMdseCd) && !mdseCheck(glblCmpyCd, prntMdseCd)) {
            // END 2018/01/09 U.Kim [QC#23352,MOD]
            setErrMsg(pMsg, NSZM0396E, MSG_INFO_MDSE_CD + prntMdseCd, MSG_INFO_MDSE_MSTR);
            retFlg = false;
        }

        int cnt = pMsg.xxChildMdseCdList.getValidCount();
        for (int i = 0; i < cnt; i++) {
            String childMdseCd = pMsg.xxChildMdseCdList.no(i).childMdseCd.getValue();
            // START 2018/02/16 K.Kojima [QC#24210,ADD]
            if (this.checkedMdseCdList.contains(childMdseCd)) {
                continue;
            }
            // END 2018/02/16 K.Kojima [QC#24210,ADD]
            if (!mdseCheck(glblCmpyCd, childMdseCd)) {
                setErrMsg(pMsg, NSZM0396E, MSG_INFO_MDSE_CD + childMdseCd, MSG_INFO_MDSE_MSTR);
                retFlg = false;
            }
            // START 2018/02/16 K.Kojima [QC#24210,ADD]
            this.checkedMdseCdList.add(childMdseCd);
            // END 2018/02/16 K.Kojima [QC#24210,ADD]
        }

        return retFlg;
    }

    private boolean mdseCheck(String glblCmpyCd, String mdseCd) {
        boolean retFlg = true;
        // 2018/06/14 S21_NA#24294 Mod Start
        MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);
        // ORD_TAKE_MDSETMsgArray ordTakeMdseTMsg = getOrdTakeMdse(glblCmpyCd, mdseCd);
        // MDSETMsgArray mdseTMsg;

        // if (ordTakeMdseTMsg.length() == 0) {
        //     mdseTMsg = getMdse(glblCmpyCd, mdseCd);
        // } else {
        //     mdseTMsg = getMdse(glblCmpyCd, ordTakeMdseTMsg.no(0).mdseCd.getValue());
        // }
        // if (mdseTMsg.length() == 0) {
        if (mdseTMsg == null) {
        // 2018/06/14 S21_NA#24294 Mod End
            retFlg = false;
        } else { // 2018/06/22 QC#25151 Add Start
            if (!S21StringUtil.isEquals(RGTN_STS.READY_FOR_ORDER_TAKING, mdseTMsg.rgtnStsCd.getValue())) {
                retFlg = false;
            }
        } // 2018/06/22 QC#25151 Add End
        return retFlg;
    }

    private List<Map<String, Object>> getQueryObject(String sqlId, Map<String, Object> paramMap) {
        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList(sqlId, paramMap);
    }

    // START 2018/01/09 U.Kim [QC#23352,MOD]
    // private Map<String, Object> setParamForGetDsMdlConfigPk(NSZC048001PMsg pMsg) {
    private Map<String, Object> setParamForGetDsMdlConfigPk(NSZC048001PMsg pMsg, String mdseCd) {
    // END 2018/01/09 U.Kim [QC#23352,MOD]
        Map<String, Object> inParam = new HashMap<String, Object>();
        inParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        // START 2017/05/25 K.Kitachi [QC#18642, MOD]
//        String prntMdseCd;
//        if (pMsg.prntMdseCd.getValue().length() > END_POS_8) {
//            prntMdseCd = pMsg.prntMdseCd.getValue().substring(START_POS_0, END_POS_8);
//        } else {
//            prntMdseCd = pMsg.prntMdseCd.getValue();
//        }
//        inParam.put("prntMdseCd", prntMdseCd + STR_PERCENT);
        // START 2018/01/09 U.Kim [QC#23352,MOD]
        // inParam.put("prntMdseCd", getMdseCd(pMsg.glblCmpyCd.getValue(), pMsg.prntMdseCd.getValue()));
        inParam.put("prntMdseCd", getMdseCd(pMsg.glblCmpyCd.getValue(), mdseCd));
        // END 2018/01/09 U.Kim [QC#23352,MOD]
        // END 2017/05/25 K.Kitachi [QC#18642, MOD]
        inParam.put("mdlActvFlg", ZYPConstant.FLG_ON_Y);
        inParam.put("slsDt", pMsg.slsDt.getValue());
        inParam.put("maxEffThruDt", MAX_EFF_THRU_DT);
        // START 2018/01/09 U.Kim [QC#23352,ADD]
        if (this.isChildMode) {
            inParam.put("childMode", ZYPConstant.FLG_ON_Y);
        }
        // END 2018/01/09 U.Kim [QC#23352,ADD]
        return inParam;
    }

    private Map<String, Object> setParamForGetChildMdse(NSZC048001PMsg pMsg, BigDecimal dsMdlConfigPk) {
        Map<String, Object> inParam = new HashMap<String, Object>();
        inParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        inParam.put("dsMdlConfigPk", dsMdlConfigPk);
        // START 2015/12/24 T.Tomita [QC#2113, MOD]
        inParam.put("childMdseCnt", pMsg.xxChildMdseCdList.getValidCount());
        // END 2015/12/24 T.Tomita [QC#2113, MOD]
        inParam.put("slsDt", pMsg.slsDt.getValue());
        inParam.put("maxEffThruDt", MAX_EFF_THRU_DT);
        return inParam;
    }

    // START 2018/01/09 U.Kim [QC#23352,ADD]
    private Map<String, Object> setParamForGetChildMdseForChildMode(NSZC048001PMsg pMsg, BigDecimal dsMdlConfigPk) {
        Map<String, Object> inParam = new HashMap<String, Object>();
        inParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        inParam.put("dsMdlConfigPk", dsMdlConfigPk);
        inParam.put("slsDt", pMsg.slsDt.getValue());
        inParam.put("maxEffThruDt", MAX_EFF_THRU_DT);
        return inParam;
    }
    // END 2018/01/09 U.Kim [QC#23352,ADD]

    private Map<String, Object> setParamForGetMdlId(NSZC048001PMsg pMsg) {
        Map<String, Object> inParam = new HashMap<String, Object>();
        inParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        // START 2017/05/25 K.Kitachi [QC#18642, MOD]
//        String prntMdseCd;
//        if (pMsg.prntMdseCd.getValue().length() > END_POS_8) {
//            prntMdseCd = pMsg.prntMdseCd.getValue().substring(START_POS_0, END_POS_8);
//        } else {
//            prntMdseCd = pMsg.prntMdseCd.getValue();
//        }
//        inParam.put("prntMdseCd", prntMdseCd + STR_PERCENT);
        inParam.put("prntMdseCd", getMdseCd(pMsg.glblCmpyCd.getValue(), pMsg.prntMdseCd.getValue()));
        // END 2017/05/25 K.Kitachi [QC#18642, MOD]
        inParam.put("mdlActvFlg", ZYPConstant.FLG_ON_Y);
        inParam.put("slsDt", pMsg.slsDt.getValue());
        inParam.put("maxEffThruDt", MAX_EFF_THRU_DT);
        return inParam;
    }

    /**
     * execute
     * @param pMsg NSZC048001PMsg
     */
    private void execute(NSZC048001PMsg pMsg) {

        // START 2017/05/25 K.Kitachi [QC#18642, MOD]
//        List<Map<String, Object>> dsMdlConfigPkList = getQueryObject("getDsMdlConfigPk", setParamForGetDsMdlConfigPk(pMsg));
//        if (dsMdlConfigPkList.size() == 0) {
//            setErrMsg(pMsg, NSZM0396E, MSG_INFO_PRT_MDSE_CD + pMsg.prntMdseCd.getValue(), MSG_INFO_DS_MDL_CONFIG_MSTR);
//            return;
//        }
        // START 2018/01/09 U.Kim [QC#23352,MOD]
        // List<Map<String, Object>> dsMdlConfigPkList = getDsMdlConfigPkList(pMsg);
        List<Map<String, Object>> dsMdlConfigPkList = getDsMdlConfigPkList(pMsg, pMsg.prntMdseCd.getValue());
        // END 2018/01/09 U.Kim [QC#23352,MOD]
        if (dsMdlConfigPkList == null) {
            setErrMsg(pMsg, NSZM0396E, MSG_INFO_PRT_MDSE_CD + pMsg.prntMdseCd.getValue(), MSG_INFO_DS_MDL_CONFIG_MSTR);
            return;
        }
        // END 2017/05/25 K.Kitachi [QC#18642, MOD]

        List<Map<String, Object>> childMdseInfoList;
        BigDecimal tmpMdlId = null;
        int tmpCnt = -1;
        // START 2015/03/09 T.Tomita [QC#2113, MOD]
        String pramChildMdseCd;
        String dbChildMdseCd;
        // END 2015/03/09 T.Tomita [QC#2113, MOD]
        int cnt;
        BigDecimal dsMdlConfigPk;
        for (int pkIdx = 0; pkIdx < dsMdlConfigPkList.size(); pkIdx++) {
            dsMdlConfigPk = (BigDecimal) dsMdlConfigPkList.get(pkIdx).get("DS_MDL_CONFIG_PK");
            // START 2017/05/25 K.Kitachi [QC#18642, MOD]
//            childMdseInfoList = getQueryObject("getChildMdse", setParamForGetChildMdse(pMsg, dsMdlConfigPk));
        	childMdseInfoList = getChildMdseInfoList(pMsg, dsMdlConfigPk);
            // END 2017/05/25 K.Kitachi [QC#18642, MOD]
            if (childMdseInfoList.size() > 0) {
                cnt = 0;
                // START 2015/12/24 T.Tomita [QC#2113, MOD]
                // START 2017/09/27 S.Ohki [QC#21363, MOD]
//                for (int paramIdx = 0; paramIdx < pMsg.xxChildMdseCdList.getValidCount(); paramIdx++) {
//                    for (int childIdx = 0; childIdx < childMdseInfoList.size(); childIdx++) {
                for (int childIdx = 0; childIdx < childMdseInfoList.size(); childIdx++) {
                	for (int paramIdx = 0; paramIdx < pMsg.xxChildMdseCdList.getValidCount(); paramIdx++) {
                // END 2017/09/27 S.Ohki [QC#21363, MOD]
                        // START 2015/03/09 T.Tomita [QC#2113, MOD]
                        pramChildMdseCd = "";
                        dbChildMdseCd = "";
                        if (childMdseInfoList.get(childIdx).get("ORD_TAKE_MDSE_CD") == null) {
                            pramChildMdseCd = pMsg.xxChildMdseCdList.no(paramIdx).childMdseCd.getValue();
                            dbChildMdseCd = childMdseInfoList.get(childIdx).get("CHILD_MDSE_CD").toString();
                        } else {
                            dbChildMdseCd = childMdseInfoList.get(childIdx).get("ORD_TAKE_MDSE_CD").toString();
                            // START 2017/05/25 K.Kitachi [QC#18642, MOD]
//                            if (pMsg.xxChildMdseCdList.no(paramIdx).childMdseCd.getValue().length() > END_POS_8) {
//                                pramChildMdseCd = pMsg.xxChildMdseCdList.no(paramIdx).childMdseCd.getValue().substring(START_POS_0, END_POS_8);
//                            } else {
//                                pramChildMdseCd = pMsg.xxChildMdseCdList.no(paramIdx).childMdseCd.getValue();
//                            }
                            pramChildMdseCd = getMdseCd(pMsg.glblCmpyCd.getValue(), pMsg.xxChildMdseCdList.no(paramIdx).childMdseCd.getValue());
                            // END 2017/05/25 K.Kitachi [QC#18642, MOD]
                        }
                        if (pramChildMdseCd.equals(dbChildMdseCd)) {
                            cnt++;
                            break;  // 2017/09/27 S.Ohki [QC#21363, ADD]
                        }
                        // END 2015/03/09 T.Tomita [QC#2113, MOD]
                    }
                }

//                if (tmpCnt == cnt) {
//                    tmpMdlId = null;
//                } else
                if (cnt == childMdseInfoList.size() && tmpCnt < cnt) {
                    tmpMdlId = (BigDecimal) childMdseInfoList.get(0).get("MDL_ID");
                    tmpCnt = cnt;
                }
                // END 2015/12/24 T.Tomita [QC#2113, MOD]
            }
        }

        if (!ZYPCommonFunc.hasValue(tmpMdlId)) {
            // START 2017/05/25 K.Kitachi [QC#18642, MOD]
//            List<Map<String, Object>> mdlIdList = getQueryObject("getMdlId", setParamForGetMdlId(pMsg));
//            if (mdlIdList.size() != 1) {
//                // START 2016/03/24 M.Gotou [QC#4887, MOD]
////                setErrMsg(pMsg, ZZZM9006E, MSG_INFO_MDL_ID);
//                setErrMsg(pMsg, NSZM0925E);
//                // END 2016/03/24 M.Gotou [QC#4887, MOD]
//                return;
//            }
            List<Map<String, Object>> mdlIdList = getMdlIdList(pMsg);
            if (mdlIdList == null) {
                // START 2018/01/09 U.Kim [QC#23352,DEL]
                // setErrMsg(pMsg, NSZM0925E);
                // END 2018/01/09 U.Kim [QC#23352,DEL]
                return;
            }
            // END 2017/05/25 K.Kitachi [QC#18642, MOD]
            tmpMdlId = (BigDecimal) mdlIdList.get(0).get("MDL_ID");
        }

        ZYPEZDItemValueSetter.setValue(pMsg.mdlId, tmpMdlId);
    }

    private ORD_TAKE_MDSETMsgArray getOrdTakeMdse(String glblCmpyCd, String mdseCd) {
        ORD_TAKE_MDSETMsg paramTMsg = new ORD_TAKE_MDSETMsg();
        paramTMsg.setSQLID("002");
        paramTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        paramTMsg.setConditionValue("ordTakeMdseCd01", mdseCd);
        return (ORD_TAKE_MDSETMsgArray) S21ApiTBLAccessor.findByCondition(paramTMsg);
    }

    private MDSETMsgArray getMdse(String glblCmpyCd, String mdseCd) {
        MDSETMsg paramTMsg = new MDSETMsg();
        paramTMsg.setSQLID("001");
        paramTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        paramTMsg.setConditionValue("mdseCd01", mdseCd);
        paramTMsg.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
        return (MDSETMsgArray) S21ApiTBLAccessor.findByCondition(paramTMsg);
    }

    private void setErrMsg(NSZC048001PMsg pMsg, String msgId, String msgPrm1, String msgPrm2) {
     // START 2017/01/12 N.Arai [QC#14614, MOD]
        Integer msgIdCnt = pMsg.xxMsgIdList.getValidCount();
        if (msgIdCnt >= pMsg.xxMsgIdList.length()) {
            return;
        }
     // END 2017/01/12 N.Arai [QC#14614, MOD]
        msgMap.addXxMsgIdWithPrm(msgId, new String[] {msgPrm1, msgPrm2 });
        msgMap.flush();
    }

    // START 2016/03/24 M.Gotou [QC#4887, DEL]
//    private void setErrMsg(NSZC048001PMsg pMsg, String msgId, String msgPrm) {
//        msgMap.addXxMsgIdWithPrm(msgId, new String[] {msgPrm });
//        msgMap.flush();
//    }
    // END 2016/03/24 M.Gotou [QC#4887, DEL]

    // START 2016/03/24 M.Gotou [QC#4887, ADD]
    private void setErrMsg(NSZC048001PMsg pMsg, String msgId) {
     // START 2017/01/12 N.Arai [QC#14614, MOD]
        Integer msgIdCnt = pMsg.xxMsgIdList.getValidCount();
        if (msgIdCnt >= pMsg.xxMsgIdList.length()) {
            return;
        }
     // END 2017/01/12 N.Arai [QC#14614, MOD]
        msgMap.addXxMsgId(msgId);
        msgMap.flush();
    }
    // END 2016/03/24 M.Gotou [QC#4887, ADD]

    // START 2017/05/25 K.Kitachi [QC#18642, ADD]
    private String getMdseCd(String glblCmpyCd, String mdseCd) {
        ORD_TAKE_MDSETMsg inMsg = new ORD_TAKE_MDSETMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("mdseCd01", mdseCd);
        ORD_TAKE_MDSETMsgArray ordTakeMdseTMsgArray = (ORD_TAKE_MDSETMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        if (ordTakeMdseTMsgArray.getValidCount() > 0) {
            return ordTakeMdseTMsgArray.no(0).ordTakeMdseCd.getValue();
        // START 2017/06/14 T.Mizuki [QC#19027, ADD]
        } else {
            String prntMdseCd;
            if (mdseCd.length() > END_POS_8) {
                prntMdseCd = mdseCd.substring(START_POS_0, END_POS_8);
            } else {
                prntMdseCd = mdseCd;
            }
            ORD_TAKE_MDSETMsg tMsg = new ORD_TAKE_MDSETMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.ordTakeMdseCd, prntMdseCd);
            tMsg = (ORD_TAKE_MDSETMsg) S21ApiTBLAccessor.findByKey(tMsg);
            if (tMsg != null) {
                return tMsg.ordTakeMdseCd.getValue();
            }
        // END 2017/06/14 T.Mizuki [QC#19027, ADD]
        }
        return mdseCd;
    }

    // START 2018/01/09 U.Kim [QC#23352,MOD]
    // private List<Map<String, Object>> getDsMdlConfigPkList(NSZC048001PMsg pMsg) {
    private List<Map<String, Object>> getDsMdlConfigPkList(NSZC048001PMsg pMsg, String mdseCd) {
    // END 2018/01/09 U.Kim [QC#23352,MOD]
        // START 2018/01/09 U.Kim [QC#23352,MOD]
        // Map<String, Object> ssmParam = setParamForGetDsMdlConfigPk(pMsg);
        Map<String, Object> ssmParam = setParamForGetDsMdlConfigPk(pMsg, mdseCd);
        // END 2018/01/09 U.Kim [QC#23352,MOD]
        ssmParam.put("likeSrchFlg", ZYPConstant.FLG_OFF_N);
        List<Map<String, Object>> dsMdlConfigPkList = getQueryObject("getDsMdlConfigPk", ssmParam);
        if (dsMdlConfigPkList.size() > 0) {
            return dsMdlConfigPkList;
        }
        // START 2018/01/09 U.Kim [QC#23352,MOD]
        // ssmParam.put("prntMdseCd", pMsg.prntMdseCd.getValue() + STR_PERCENT);
        ssmParam.put("prntMdseCd", mdseCd + STR_PERCENT);
        // END 2018/01/09 U.Kim [QC#23352,MOD]
        ssmParam.put("likeSrchFlg", ZYPConstant.FLG_ON_Y);
        dsMdlConfigPkList = getQueryObject("getDsMdlConfigPk", ssmParam);
        if (dsMdlConfigPkList.size() > 0) {
            return dsMdlConfigPkList;
        }
        return null;
    }

    private List<Map<String, Object>> getChildMdseInfoList(NSZC048001PMsg pMsg, BigDecimal dsMdlConfigPk) {
        Map<String, Object> ssmParam = setParamForGetChildMdse(pMsg, dsMdlConfigPk);
        ssmParam.put("substrSrchFlg", ZYPConstant.FLG_OFF_N);
        List<Map<String, Object>> childMdseInfoList = getQueryObject("getChildMdse", ssmParam);
        if (childMdseInfoList.size() > 0) {
            return childMdseInfoList;
        }
        ssmParam.put("substrSrchFlg", ZYPConstant.FLG_ON_Y);
        childMdseInfoList = getQueryObject("getChildMdse", ssmParam);
        return childMdseInfoList;
    }

    // START 2018/01/09 U.Kim [QC#23352,MOD]
    private List<Map<String, Object>> getChildMdseInfoListForChildMode(NSZC048001PMsg pMsg, BigDecimal dsMdlConfigPk) {
        Map<String, Object> ssmParam = setParamForGetChildMdseForChildMode(pMsg, dsMdlConfigPk);
        ssmParam.put("substrSrchFlg", ZYPConstant.FLG_OFF_N);
        List<Map<String, Object>> childMdseInfoList = getQueryObject("getChildMdseForChildMode", ssmParam);
        if (childMdseInfoList.size() > 0) {
            return childMdseInfoList;
        }
        ssmParam.put("substrSrchFlg", ZYPConstant.FLG_ON_Y);
        childMdseInfoList = getQueryObject("getChildMdseForChildMode", ssmParam);
        return childMdseInfoList;
    }
    // END 2018/01/09 U.Kim [QC#23352,MOD]

    private List<Map<String, Object>> getMdlIdList(NSZC048001PMsg pMsg) {
        Map<String, Object> ssmParam = setParamForGetMdlId(pMsg);
        ssmParam.put("likeSrchFlg", ZYPConstant.FLG_OFF_N);
        List<Map<String, Object>> mdlIdList = getQueryObject("getMdlId", ssmParam);
        if (mdlIdList.size() == 1) {
            return mdlIdList;
        }
        ssmParam.put("prntMdseCd", pMsg.prntMdseCd.getValue() + STR_PERCENT);
        ssmParam.put("likeSrchFlg", ZYPConstant.FLG_ON_Y);
        mdlIdList = getQueryObject("getMdlId", ssmParam);
        if (mdlIdList.size() == 1) {
            return mdlIdList;
        }
        return null;
    }
    // END 2017/05/25 K.Kitachi [QC#18642, ADD]

    // START 2018/01/09 U.Kim [QC#23352,ADD]
    private void checkChlidMode(NSZC048001PMsg pMsg) {
        if (!ZYPCommonFunc.hasValue(pMsg.mdlId)) {
            if (!S21ApiUtil.isXxMsgId(pMsg)) {
                this.isChildMode = true;
            }
            return;
        }
        Map<String, Object> inParam = new HashMap<String, Object>();
        inParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        inParam.put("mdlId", pMsg.mdlId.getValue());
        BigDecimal count = (BigDecimal) ssmBatchClient.queryObject("countSwMdl", inParam);
        if (count == null || count.compareTo(BigDecimal.ZERO) == 0) {
            return;
        }
        this.isChildMode = true;
    }

    private void executeForChildMode(NSZC048001PMsg pMsg) {
        List<Map<String, Object>> dsMdlConfigPkList = null;
        String tmpPrntMdseCd = pMsg.prntMdseCd.getValue();
        int prntMdseCdIndex = -1;

        if (!ZYPCommonFunc.hasValue(tmpPrntMdseCd)) {
            for (int childCount = 0; childCount < pMsg.xxChildMdseCdList.getValidCount(); childCount++) {
                dsMdlConfigPkList = getDsMdlConfigPkList(pMsg, pMsg.xxChildMdseCdList.no(childCount).childMdseCd.getValue());
                if (dsMdlConfigPkList != null) {
                    tmpPrntMdseCd = pMsg.xxChildMdseCdList.no(childCount).childMdseCd.getValue();
                    prntMdseCdIndex = childCount;
                    break;
                }
            }
        } else {
            dsMdlConfigPkList = getDsMdlConfigPkList(pMsg, tmpPrntMdseCd);
            if (dsMdlConfigPkList == null) {
                setErrMsg(pMsg, NSZM0396E, MSG_INFO_PRT_MDSE_CD + tmpPrntMdseCd, MSG_INFO_DS_MDL_CONFIG_MSTR);
                return;
            }
        }

        if (!ZYPCommonFunc.hasValue(tmpPrntMdseCd)) {
            dsMdlConfigPkList = new ArrayList<Map<String,Object>>();
            ArrayList<Map<String, Object>> tmpDsMdlConfigPkList = new ArrayList<Map<String, Object>>();
            for (int i = 0; i < pMsg.xxChildMdseCdList.getValidCount(); i++) {
                tmpDsMdlConfigPkList.addAll(getDsMdlConfigPkListForChild(pMsg, pMsg.xxChildMdseCdList.no(i).childMdseCd.getValue()));
            }
            if (tmpDsMdlConfigPkList == null) {
                setErrMsg(pMsg, NSZM0925E);
                return;
            }
            for (int i = 0; i < tmpDsMdlConfigPkList.size(); i++) {
                if (!dsMdlConfigPkList.contains(tmpDsMdlConfigPkList.get(i))) {
                    dsMdlConfigPkList.add(tmpDsMdlConfigPkList.get(i));
                }
            }
        }

        BigDecimal tmpMdlId = null;
        for (int pkIdx = 0; pkIdx < dsMdlConfigPkList.size(); pkIdx++) {
            BigDecimal dsMdlConfigPk = (BigDecimal) dsMdlConfigPkList.get(pkIdx).get("DS_MDL_CONFIG_PK");
            BigDecimal mdlId = (BigDecimal) dsMdlConfigPkList.get(pkIdx).get("MDL_ID");
            String prntMdseCd = (String) dsMdlConfigPkList.get(pkIdx).get("PRNT_MDSE_CD");
            List<Map<String, Object>> childMdseInfoList = getChildMdseInfoListForChildMode(pMsg, dsMdlConfigPk);
            if (childMdseInfoList.size() > 0) {
                int findChildCount = 0;
                for (int childIdx = 0; childIdx < childMdseInfoList.size(); childIdx++) {
                    for (int paramIdx = 0; paramIdx < pMsg.xxChildMdseCdList.getValidCount(); paramIdx++) {
                        if (paramIdx == prntMdseCdIndex) {
                            continue;
                        }
                        String pramChildMdseCd = "";
                        String dbChildMdseCd = "";
                        if (childMdseInfoList.get(childIdx).get("ORD_TAKE_MDSE_CD") == null) {
                            pramChildMdseCd = pMsg.xxChildMdseCdList.no(paramIdx).childMdseCd.getValue();
                            dbChildMdseCd = childMdseInfoList.get(childIdx).get("CHILD_MDSE_CD").toString();
                        } else {
                            pramChildMdseCd = getMdseCd(pMsg.glblCmpyCd.getValue(), pMsg.xxChildMdseCdList.no(paramIdx).childMdseCd.getValue());
                            dbChildMdseCd = childMdseInfoList.get(childIdx).get("ORD_TAKE_MDSE_CD").toString();
                        }
                        if (pramChildMdseCd.equals(dbChildMdseCd)) {
                            findChildCount++;
                            break;
                        }
                    }
                }
                if (findChildCount > 0) {
                    if (tmpMdlId != null) {
                        setErrMsg(pMsg, NSZM0925E);
                        return;
                    }
                    tmpMdlId = mdlId;
                    if (!ZYPCommonFunc.hasValue(tmpPrntMdseCd)) {
                        tmpPrntMdseCd = prntMdseCd;
                    }
                }
            }
        }
        if (tmpMdlId == null) {
            setErrMsg(pMsg, NSZM0925E);
            return;
        }
        ZYPEZDItemValueSetter.setValue(pMsg.mdlId, tmpMdlId);
        if (!ZYPCommonFunc.hasValue(pMsg.prntMdseCd)) {
            ZYPEZDItemValueSetter.setValue(pMsg.prntMdseCd, tmpPrntMdseCd);
            if (prntMdseCdIndex >= 0) {
                for (int copyCount = prntMdseCdIndex + 1; copyCount < pMsg.xxChildMdseCdList.getValidCount(); copyCount++) {
                    EZDMsg.copy(pMsg.xxChildMdseCdList.no(copyCount), null, pMsg.xxChildMdseCdList.no(copyCount - 1), null);
                }
                pMsg.xxChildMdseCdList.setValidCount(pMsg.xxChildMdseCdList.getValidCount() - 1);
            }
        }
    }

    private List<Map<String, Object>> getDsMdlConfigPkListForChild(NSZC048001PMsg pMsg, String mdseCd) {
        Map<String, Object> ssmParam = setParamForGetDsMdlConfigPkForChild(pMsg, mdseCd);
        ssmParam.put("likeSrchFlg", ZYPConstant.FLG_OFF_N);
        List<Map<String, Object>> dsMdlConfigPkList = getQueryObject("getDsMdlConfigPkForChild", ssmParam);
        if (dsMdlConfigPkList.size() > 0) {
            return dsMdlConfigPkList;
        }
        ssmParam.put("childMdseCd", mdseCd + STR_PERCENT);
        ssmParam.put("likeSrchFlg", ZYPConstant.FLG_ON_Y);
        dsMdlConfigPkList = getQueryObject("getDsMdlConfigPkForChild", ssmParam);
        if (dsMdlConfigPkList.size() > 0) {
            return dsMdlConfigPkList;
        }
        return new ArrayList<Map<String,Object>>();
    }

    private Map<String, Object> setParamForGetDsMdlConfigPkForChild(NSZC048001PMsg pMsg, String mdseCd) {
        Map<String, Object> inParam = new HashMap<String, Object>();
        inParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        inParam.put("childMdseCd", getMdseCd(pMsg.glblCmpyCd.getValue(), mdseCd));
        inParam.put("mdlActvFlg", ZYPConstant.FLG_ON_Y);
        inParam.put("slsDt", pMsg.slsDt.getValue());
        inParam.put("maxEffThruDt", MAX_EFF_THRU_DT);
        return inParam;
    }

    // END 2018/01/09 U.Kim [QC#23352,ADD]
}
