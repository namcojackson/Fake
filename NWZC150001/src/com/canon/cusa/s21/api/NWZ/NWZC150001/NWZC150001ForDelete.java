/**<pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>*/
package com.canon.cusa.s21.api.NWZ.NWZC150001;

import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.MODE_DELETE;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM0011E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM0073E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM0346E;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.RQST_TP_CONFIG_DELETE;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.RQST_TP_DELETE;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.RQST_TP_DTL_DELETE;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.RQST_TP_RTRN_DTL_DELETE;
import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.SET_LINE_SUB_NUM;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDMsg;
import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.CPO_DTL_RECTMsg;
import business.db.CPO_RTRN_CALC_BASE_RECTMsg;
import business.db.CPO_RTRN_PRC_CALC_BASETMsg;
import business.db.DS_CPO_CONFIGTMsg;
import business.db.DS_CPO_CONFIG_RECTMsg;
import business.db.DS_CPO_CTAC_PSNTMsg;
import business.db.DS_CPO_DELY_INFOTMsg;
import business.db.DS_CPO_ISTL_INFOTMsg;
import business.db.DS_CPO_RTRN_DTLTMsg;
import business.db.DS_CPO_RTRN_DTL_RECTMsg;
import business.db.DS_CPO_SLS_CRTMsg;
import business.db.DS_CPO_SLS_CR_RECTMsg;
import business.db.DS_SITE_SRVYTMsg;
import business.db.HLDTMsg;
import business.db.ORD_PRC_CALC_BASETMsg;
import business.db.ORD_PRC_CALC_BASE_RECTMsg;
import business.parts.NSZC115001PMsg;
import business.parts.NWZC150001PMsg;
import business.parts.NWZC150001_APMsg;
import business.parts.NWZC150001_cpoConfigPMsg;
import business.parts.NWZC150001_rtnDtlPMsg;
import business.parts.NWZC150002PMsg;
import business.parts.NWZC150003PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC115001.NSZC115001;
import com.canon.cusa.s21.api.NSZ.NSZC115001.constant.NSZC115001Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageIdMgr;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
//import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * DS CPO Update API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/06   Fujitsu         S.Takami        Create          S21_NA#7821
 * 2016/08/01   Fujitsu         S.Takami        Update          S21_NA#12637
 * 2016/08/04   Fujitsu         S.Takami        Update          S21_NA#7821-2
 * 2016/08/22   Fujitsu         S.Takami        Update          S21_NA#7821-3
 * 2016/09/05   Fujitsu         S.Takami        Update          S21_NA#6523
 * 2016/09/26   Fujitsu         S.Takami        Update          S21_NA#10274
 * 2016/11/29   Fujitsu         S.Ohki          Update          S21_NA#16214
 * 2017/05/10   Fujitsu         S.Takami        Update          S21_NA#Review structure Lv.2 RS#8390, 8391
 * 2017/05/25   Fujitsu         S.Takami        Update          S21_NA#Review structure Lv.2 RS#8391-2
 * 2017/06/19   Fujitsu         R.Nakamura      Update          S21_NA#19150
 * 2018/04/17   Fujitsu         M.Ohno          Update          S21_NA#24458
 * 2018/05/20   Fujitsu         S.Takami        Update          S21_NA#25604
 * 2018/06/01   Fujitsu         T.Noguchi       Update          S21_NA#26334
 * 2018/06/05   Fujitsu         S.Takami        Update          S21_NA#25151
 * </pre>
 */

public class NWZC150001ForDelete extends S21ApiCommonBase {

    /** Online Batch Type */
    private ONBATCH_TYPE onBatchType = null;

    /** Global Company Code : set from pMsg */
    private String glblCmpyCd = null;

    /** Sales Date : set from pMsg */
    private String slsDt = null;

    /** Message ID Manager */
    private S21ApiMessageIdMgr msgIdMgr = null;

    /** SSM Client */
//    private final S21SsmBatchClient ssmClient;

    private boolean rmaLineRefRest = true;

    /** Partial Update Keys for Config */
    private static final String[] PARTIAL_UPD_CONFIG_KEY = {
        "glblCmpyCd", //
        "dsCpoConfigPk"
    };

    /** Partial Update Values for Config */
    private static final String[] PARTIAL_UPD_CONFIG_VAL = {
        "dsOrdPosnNum", //
        "mdlId", // 2018/05/20 S21_NA#25604 Add
        "mdlDescTxt" // 2018/05/20 S21_NA#25604 Add
    };

    // 2018/05/20 S21_NA#25604 Del Start
//    // 2016/11/29 S21_NA#16214 Add Start
//    /** Partial Update Values for Config Model ID */
//    private static final String[] PARTIAL_UPD_CONFIG_MDL_ID = {
//        "mdlId" //
//    };
//    // 2016/11/29 S21_NA#16214 Add End
    // // 2018/05/20 S21_NA#25604 Del End

    /** Partial Update Keys for Detail */
    private static final String[] PARTIAL_UPD_DTL_KEY = {
        "glblCmpyCd", //
        "cpoOrdNum", //
        "cpoDtlLineNum", //
        "cpoDtlLineSubNum"
    };

//    /** Partial Update Values for Detail */
//    private static final String[] PARTIAL_UPD_DTL_VAL = {
//        "cpoDtlLineNum", //
//        "cpoDtlLineSubNum"
//    };
    // 2017/05/10 S21_NA#Review structure Lv.2 RS#8390 Del End

    /** Partial Update Keys for Retern Details */
    private static final String[] PARTIAL_UPD_RTN_DTL_KEY = {
        "glblCmpyCd", //
        "cpoOrdNum", //
        "dsCpoRtrnLineNum", //
        "dsCpoRtrnLineSubNum"
    };

    // 2017/05/10 S21_NA#Review structure Lv.2 RS#8390 Del Start
//    /** Partial Update Keys for Retern Details */
//    private static final String[] PARTIAL_UPD_RTN_DTL_VAL = {
//        "dsCpoRtrnLineNum", //
//        "dsCpoRtrnLineSubNum"
//    };
    // 2017/05/10 S21_NA#Review structure Lv.2 RS#8390 Del End

    /**
     * Constructor for NWZC150001ForDelete
     */
    public NWZC150001ForDelete() {
        super();
//        ssmClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * <pre>
     * DS CPO Update API.
     * @param inPMsg        NWZC150001PMsg
     * @param resPMsg2List  List<NWZC150002PMsg>
     * @param resPMsg3List  List<NWZC150003PMsg>
     * @param prmOnBatchType ONBATCH_TYPE
     * </pre>
     */
    public void execute(final NWZC150001PMsg inPMsg //
            , final List<NWZC150002PMsg> resPMsg2List //
            , final List<NWZC150003PMsg> resPMsg3List //
            , final ONBATCH_TYPE prmOnBatchType) {

        NWZC150001PMsg pMsg = new NWZC150001PMsg();

        this.onBatchType = prmOnBatchType;
        try {
            EZDMsg.copy(inPMsg, null, pMsg, null);

            this.msgIdMgr = new S21ApiMessageIdMgr();
            this.onBatchType = prmOnBatchType;
            this.glblCmpyCd = pMsg.glblCmpyCd.getValue();
            this.slsDt = pMsg.slsDt.getValue();

            doProcess(pMsg, resPMsg2List, resPMsg3List);

        } finally {
            inPMsg.xxMsgIdList.setValidCount(pMsg.xxMsgIdList.getValidCount());
            EZDMsg.copy(pMsg, null, inPMsg, null);
            super.updateMessage(inPMsg, this.msgIdMgr);
        }
    }

    private boolean chkParam(NWZC150001PMsg pMsg) {

        boolean errFlg = false;
        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            setMsgId(pMsg, NWZM0011E);
            errFlg = true;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.slsDt)) {
            setMsgId(pMsg, NWZM0346E);
            errFlg = true;
        }
        CPOTMsg cpoTMsg = new CPOTMsg();
        if (ZYPCommonFunc.hasValue(pMsg.cpoOrdNum)) {
            ZYPEZDItemValueSetter.setValue(cpoTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoOrdNum, pMsg.cpoOrdNum);
            cpoTMsg = (CPOTMsg) S21ApiTBLAccessor.findByKey(cpoTMsg);
            if (!existsCpo(cpoTMsg)) {
                setMsgId(pMsg, NWZM0073E);
            }
        } else {
            setMsgId(pMsg, NWZM0073E);
        }

        if (!S21StringUtil.isEquals(MODE_DELETE, pMsg.xxModeCd.getValue())) {
            errFlg = true;
        }
        int delConfigCnt = 0;
        int delLineConfigCnt = 0;
        int delRmaCnt = 0;
        if (pMsg.cpoConfig.getValidCount() == 0) {
            errFlg = true;
        } else {
            for (int i = 0; i < pMsg.cpoConfig.getValidCount(); i++) {
                String xxRqstTpCd = pMsg.cpoConfig.no(i).xxRqstTpCd.getValue();
                if (S21StringUtil.isEquals(RQST_TP_CONFIG_DELETE, xxRqstTpCd)) {
                    delConfigCnt++;
                }
            }
        }

        if (pMsg.A.getValidCount() == 0 && pMsg.rtnDtl.getValidCount() == 0) {
            errFlg = true;
        } else {
            for (int i = 0; i < pMsg.A.getValidCount(); i++) {
                String xxRqstTpCd = pMsg.A.no(i).xxRqstTpCd_A1.getValue();
                if (S21StringUtil.isEquals(RQST_TP_DTL_DELETE, xxRqstTpCd)) {
                    delLineConfigCnt++;
                }
            }
            for (int i = 0; i < pMsg.rtnDtl.getValidCount(); i++) {
                String xxRqstTpCd = pMsg.rtnDtl.no(i).xxRqstTpCd_B1.getValue();
                if (S21StringUtil.isEquals(RQST_TP_RTRN_DTL_DELETE, xxRqstTpCd)) {
                    delRmaCnt++;
                }
            }
        }
        if (delConfigCnt == 0 //
                && delLineConfigCnt == 0 //
                && delRmaCnt == 0) {
            errFlg = true;
        }

        return errFlg;
    }

    private void doProcess(NWZC150001PMsg pMsg //
            , List<NWZC150002PMsg> resPMsg2List //
            , List<NWZC150003PMsg> resPMsg3List) {
        if (chkParam(pMsg)) {
            return;
        }

        List<NWZC150001DsCpoConfigForDeleteBean> configList = setParameterConfig(pMsg);
        List<NWZC150001DsCpoDtlForDeleteBean> dtlList = setParameterDtl(pMsg);
        List<NWZC150001DsCpoRtrnDtlForDeleteBean> rtnDtlList = setParameterRtnDtl(pMsg);

        chkAllDtlDeleteInConfig(configList, dtlList, rtnDtlList);

        boolean isDeleteConfig = reNewPositionNumber(pMsg, configList);

        rePositionForDetail(configList, dtlList);
        boolean isDeleteLine = reNewOrderLineNumberForDetail(pMsg, dtlList);

        rePositionForRtnDetail(configList, rtnDtlList);
        boolean isRtnDeleteLine = reNewOrderLineNumberForRtnDetail(pMsg, rtnDtlList);

        reNewDplyRefNum(configList, dtlList, rtnDtlList);

        // Delete Config
        if (isDeleteConfig) {
            deleteAndUpdateConfig(pMsg, configList);
        }

        // Delete Details
        if (isDeleteLine) {
            deleteAndUpdateCpoDtl(pMsg, dtlList);
            // 2018/06/05 S21_NA#25151 Del Start
//            // 2016/11/29 S21_NA#16214 Add Start
//            updMdlIdForConfig(pMsg, configList);
//            // 2016/11/29 S21_NA#16214 Add End
            // 2018/06/05 S21_NA#25151 Del End
        }

        // Delete Return Details
        if (isRtnDeleteLine) {
            deleteAndUpdateRtnDtl(pMsg, rtnDtlList);
        }

        // 2016/09/05 S21_NA#6523 Add Start
        if (isDeleteConfig || isDeleteLine || isRtnDeleteLine) {
            CPOTMsg cpoTMsg = new CPOTMsg();
            ZYPEZDItemValueSetter.setValue(cpoTMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(cpoTMsg.cpoOrdNum, pMsg.cpoOrdNum);

            cpoTMsg = (CPOTMsg) S21FastTBLAccessor.findByKeyForUpdate(cpoTMsg);
            if (cpoTMsg != null) {
                cpoTMsg.dsCpoPrcInd.setValue(ZYPConstant.FLG_OFF_0);
                S21FastTBLAccessor.update(cpoTMsg);
            }
        }
        // 2016/09/05 S21_NA#6523 Add End

        // Update CPO Svc
        updateCpoSvc(pMsg);
    }

    private List<NWZC150001DsCpoConfigForDeleteBean> setParameterConfig(NWZC150001PMsg pMsg) {

        List<NWZC150001DsCpoConfigForDeleteBean> configList = new ArrayList<NWZC150001DsCpoConfigForDeleteBean>(0);

        for (int i = 0; i < pMsg.cpoConfig.getValidCount(); i++) {
            NWZC150001DsCpoConfigForDeleteBean configBean = new NWZC150001DsCpoConfigForDeleteBean(pMsg.cpoConfig.no(i));
            configBean.setCurDsOrdPosnNum(pMsg.cpoConfig.no(i).dsOrdPosnNum.getValue());
            configList.add(configBean);
        }
        return configList;
    }

    private List<NWZC150001DsCpoDtlForDeleteBean> setParameterDtl(NWZC150001PMsg pMsg) {

        List<NWZC150001DsCpoDtlForDeleteBean> dtlList = new ArrayList<NWZC150001DsCpoDtlForDeleteBean>(0);

        for (int i = 0; i < pMsg.A.getValidCount(); i++) {
            NWZC150001DsCpoDtlForDeleteBean dtlBean = new NWZC150001DsCpoDtlForDeleteBean(pMsg.A.no(i));
            dtlBean.setCurDsOrdPosnNum(pMsg.A.no(i).dsOrdPosnNum_A1.getValue());
            dtlBean.setCurCpoDtlLineNum(pMsg.A.no(i).cpoDtlLineNum_A1.getValue());
            dtlBean.setCurCpoDtlLineSubNum(pMsg.A.no(i).cpoDtlLineSubNum_A1.getValue());
            dtlBean.setCurDsCpoLineNum(pMsg.A.no(i).dsCpoLineNum_A1.getValue());
            dtlBean.setCurDsCpoLineSubNum(pMsg.A.no(i).dsCpoLineSubNum_A1.getValue());
            dtlList.add(dtlBean);
        }
        return dtlList;
    }

    private List<NWZC150001DsCpoRtrnDtlForDeleteBean> setParameterRtnDtl(NWZC150001PMsg pMsg) {

        List<NWZC150001DsCpoRtrnDtlForDeleteBean> rtnDtlList = new ArrayList<NWZC150001DsCpoRtrnDtlForDeleteBean>(0);

        for (int i = 0; i < pMsg.rtnDtl.getValidCount(); i++) {
            NWZC150001DsCpoRtrnDtlForDeleteBean rtnDtlBean = new NWZC150001DsCpoRtrnDtlForDeleteBean(pMsg.rtnDtl.no(i));
            rtnDtlBean.setCurDsOrdPosnNum(pMsg.rtnDtl.no(i).dsOrdPosnNum_B1.getValue());
            rtnDtlBean.setCurCpoDtlLineNum(pMsg.rtnDtl.no(i).cpoDtlLineNum_B1.getValue());
            rtnDtlBean.setCurCpoDtlLineSubNum(pMsg.rtnDtl.no(i).cpoDtlLineSubNum_B1.getValue());
            rtnDtlBean.setCurDsCpoLineNum(pMsg.rtnDtl.no(i).dsCpoLineNum_B1.getValue());
            rtnDtlBean.setCurDsCpoLineSubNum(pMsg.rtnDtl.no(i).dsCpoLineSubNum_B1.getValue());
            rtnDtlList.add(rtnDtlBean);
        }
        return rtnDtlList;
    }


    private void chkAllDtlDeleteInConfig(List<NWZC150001DsCpoConfigForDeleteBean> configList, //
            List<NWZC150001DsCpoDtlForDeleteBean> dtlList, //
            List<NWZC150001DsCpoRtrnDtlForDeleteBean> rtnDtlList) {

        for (NWZC150001DsCpoConfigForDeleteBean configBean : configList) {
            NWZC150001_cpoConfigPMsg configMsg = configBean.getCpoConfigPMsg();
            final String configCatgCd = configMsg.configCatgCd.getValue();
            final String dsOrdPosnNum = configMsg.dsOrdPosnNum.getValue();

            if (S21StringUtil.isEquals(RQST_TP_CONFIG_DELETE, configMsg.xxRqstTpCd.getValue())) {
                allDtlDelete(configMsg, dtlList, rtnDtlList);
                continue;
            }

            if (S21StringUtil.isEquals(CONFIG_CATG.OUTBOUND, configCatgCd)) {
                if (isAllDtlDelete(dsOrdPosnNum, dtlList)) {
                    configMsg.xxRqstTpCd.setValue(RQST_TP_CONFIG_DELETE);
                }
            } else if (S21StringUtil.isEquals(CONFIG_CATG.INBOUND, configCatgCd)) {
                if (isAllRtrnDtlDelete(dsOrdPosnNum, rtnDtlList)) {
                    configMsg.xxRqstTpCd.setValue(RQST_TP_CONFIG_DELETE);
                }
            }
        }
    }

    private void allDtlDelete(NWZC150001_cpoConfigPMsg configMsg, //
            List<NWZC150001DsCpoDtlForDeleteBean> dtlList, //
            List<NWZC150001DsCpoRtrnDtlForDeleteBean> rtnDtlList) {

        String dsOrdPosnNum = configMsg.dsOrdPosnNum.getValue();
        if (S21StringUtil.isEquals(CONFIG_CATG.OUTBOUND, configMsg.configCatgCd.getValue())) {
            for (NWZC150001DsCpoDtlForDeleteBean dtlBean : dtlList) {
                NWZC150001_APMsg dtlMsg = dtlBean.getDtlPMsg();
                if (S21StringUtil.isEquals(dsOrdPosnNum, dtlMsg.dsOrdPosnNum_A1.getValue())) {
                    dtlMsg.xxRqstTpCd_A1.setValue(RQST_TP_DTL_DELETE);
                }
            }
        } else if (S21StringUtil.isEquals(CONFIG_CATG.INBOUND, configMsg.configCatgCd.getValue())) {
            for (NWZC150001DsCpoRtrnDtlForDeleteBean rtnDtlBean : rtnDtlList) {
                NWZC150001_rtnDtlPMsg rtnDtlMsg = rtnDtlBean.getRtnDtlPMsg();
                if (S21StringUtil.isEquals(dsOrdPosnNum, rtnDtlMsg.dsOrdPosnNum_B1.getValue())) {
                    rtnDtlMsg.xxRqstTpCd_B1.setValue(RQST_TP_DTL_DELETE);
                }
            }
        }
    }

    private boolean isAllDtlDelete(String dsOrdPosnNum, List<NWZC150001DsCpoDtlForDeleteBean> dtlList) {

        int dtlCnt = 0;
        int delDtlCnt = 0;
        for (NWZC150001DsCpoDtlForDeleteBean dtlBean : dtlList) {
            NWZC150001_APMsg dtlMsg = dtlBean.getDtlPMsg();
            // 2016/08/01 S21_NA#12637 Add Start
            if (!S21StringUtil.isEquals(dsOrdPosnNum, dtlMsg.dsOrdPosnNum_A1.getValue())) {
                continue;
            }
            // 2016/08/01 S21_NA#12637 Add End
            dtlCnt++;
            if (S21StringUtil.isEquals(RQST_TP_DTL_DELETE, dtlMsg.xxRqstTpCd_A1.getValue())) {
                delDtlCnt++;
            }
        }
        return dtlCnt == delDtlCnt;
    }

    private boolean isAllRtrnDtlDelete(String dsOrdPosnNum, List<NWZC150001DsCpoRtrnDtlForDeleteBean> rtnDtlList) {

        int dtlCnt = 0;
        int delDtlCnt = 0;
        for (NWZC150001DsCpoRtrnDtlForDeleteBean rtnDtlBean : rtnDtlList) {
            NWZC150001_rtnDtlPMsg dtlMsg = rtnDtlBean.getRtnDtlPMsg();
            // 2016/08/01 S21_NA#12637 Add Start
            if (!S21StringUtil.isEquals(dsOrdPosnNum, dtlMsg.dsOrdPosnNum_B1.getValue())) {
                continue;
            }
            // 2016/08/01 S21_NA#12637 Add End
            dtlCnt++;
            if (S21StringUtil.isEquals(RQST_TP_RTRN_DTL_DELETE, dtlMsg.xxRqstTpCd_B1.getValue())) {
                delDtlCnt++;
            }
        }
        return dtlCnt == delDtlCnt;
    }

    // 2017/05/10 S21_NA#Review structure Lv.2 RS#8390 Del Start
    private boolean reNewPositionNumber(NWZC150001PMsg pMsg, List<NWZC150001DsCpoConfigForDeleteBean> configList) {

        int outBoundPosnNum = 1;
        int inBoundPosnNum = 1;
        boolean isDeleteConfig = false;
        for (NWZC150001DsCpoConfigForDeleteBean configBean : configList) {
            NWZC150001_cpoConfigPMsg configMsg = configBean.getCpoConfigPMsg();
            if (MODE_DELETE.equals(pMsg.xxModeCd.getValue()) && RQST_TP_CONFIG_DELETE.equals(configMsg.xxRqstTpCd.getValue())) {
                isDeleteConfig = true;
                continue;
            }
            String configCatgCd = configMsg.configCatgCd.getValue();
            if (S21StringUtil.isEquals(CONFIG_CATG.OUTBOUND, configCatgCd)) {
                configMsg.dsOrdPosnNum.setValue(String.valueOf(outBoundPosnNum));
                // 2018/06/05 S21_NA#25151 Add Start
                configBean.setModified(true);
                // 2018/06/05 S21_NA#25151 Add End
                outBoundPosnNum++;
            }
            if (S21StringUtil.isEquals(CONFIG_CATG.INBOUND, configCatgCd)) {
                configMsg.dsOrdPosnNum.setValue(String.valueOf(inBoundPosnNum));
                // 2018/06/05 S21_NA#25151 Add Start
                configBean.setModified(true);
                // 2018/06/05 S21_NA#25151 Add End
                inBoundPosnNum++;
            }
            // 2018/06/05 S21_NA#25151 Add Start
            if (hasDeletingLine(pMsg, configMsg)) {
                // 2018/06/05 S21_NA#25151 Add Start
                configBean.setModified(true);
                // 2018/06/05 S21_NA#25151 Add End
                isDeleteConfig = true;
            }
            // 2018/06/05 S21_NA#25151 Add End
        }

        return isDeleteConfig;
    }

    private void rePositionForDetail(List<NWZC150001DsCpoConfigForDeleteBean> configList, //
            List<NWZC150001DsCpoDtlForDeleteBean> dtlList) {

        for (NWZC150001DsCpoConfigForDeleteBean configBean : configList) {

            NWZC150001_cpoConfigPMsg configMsg = configBean.getCpoConfigPMsg();
            if (!S21StringUtil.isEquals(CONFIG_CATG.OUTBOUND, configMsg.configCatgCd.getValue())) {
                continue;
            }
            String dbPosnNum = configBean.getCurDsOrdPosnNum();
            String reNumPosnNum = configMsg.dsOrdPosnNum.getValue();
            if (S21StringUtil.isEquals(dbPosnNum, reNumPosnNum)) {
                continue;
            }
            for (NWZC150001DsCpoDtlForDeleteBean dtlBean : dtlList) {
                NWZC150001_APMsg dtlMsg = dtlBean.getDtlPMsg();
                if (S21StringUtil.isEquals(dbPosnNum, dtlBean.getCurDsOrdPosnNum())) {
                    dtlMsg.dsOrdPosnNum_A1.setValue(reNumPosnNum);
                    // 2018/06/05 S21_NA#25151 Add Start
                    dtlBean.setModified(true);
                    // 2018/06/05 S21_NA#25151 Add End
                }
            }
        }
    }

    private void rePositionForRtnDetail(List<NWZC150001DsCpoConfigForDeleteBean> configList, //
            List<NWZC150001DsCpoRtrnDtlForDeleteBean> rtnDtlList) {

        for (NWZC150001DsCpoConfigForDeleteBean configBean : configList) {

            NWZC150001_cpoConfigPMsg configMsg = configBean.getCpoConfigPMsg();
            if (!S21StringUtil.isEquals(CONFIG_CATG.INBOUND, configMsg.configCatgCd.getValue())) {
                continue;
            }
            String dbPosnNum = configBean.getCurDsOrdPosnNum();
            String reNumPosnNum = configMsg.dsOrdPosnNum.getValue();
            if (S21StringUtil.isEquals(dbPosnNum, reNumPosnNum)) {
                continue;
            }
            for (NWZC150001DsCpoRtrnDtlForDeleteBean rtnDtlBean : rtnDtlList) {
                NWZC150001_rtnDtlPMsg rtnDtlMsg = rtnDtlBean.getRtnDtlPMsg();
                if (S21StringUtil.isEquals(dbPosnNum, rtnDtlBean.getCurDsOrdPosnNum())) {
                    rtnDtlMsg.dsOrdPosnNum_B1.setValue(reNumPosnNum);
                    // 2018/06/05 S21_NA#25151 Add Start
                    rtnDtlBean.setModified(true);
                    // 2018/06/05 S21_NA#25151 Add End
                }
            }
        }
    }

    /**
     * CPO Order Line Number , CPO Order Line Sub Number Numbering
     * 
     * <pre>
     * Numbering of Order Line Number and CPO Order Line Sub Number is done.
     * </pre>
     * @param cpoBean NWZC150001Bean
     */
    private boolean reNewOrderLineNumberForDetail(NWZC150001PMsg pMsg, List<NWZC150001DsCpoDtlForDeleteBean> dtlList) {

        // 2017/05/10 S21_NA#Review structure Lv.2 RS#8390 Del Start
//        String cpoDtlLineNum = "000";
        // 2017/05/10 S21_NA#Review structure Lv.2 RS#8390 Del End
        int subNum = 0;
        String processLineNumber = null;

        // 2018/06/05 S21_NA#25151 Mod Start
//        BigDecimal dsCpoLineNum = BigDecimal.ZERO; // 
        int dsCpoLineNum = 0;
        // 2018/06/05 S21_NA#25151 change dsCpoLineNum logic without any comments, preventing instance new BigDecimal objects.;
        // 2018/06/05 S21_NA#25151 Mod End
//        String prevDsOrdPosNum = ""; 2016/08/01 S21_NA#12637 Add Start

        boolean isDeleteLine = false;
        for (NWZC150001DsCpoDtlForDeleteBean detailBean : dtlList) {

            NWZC150001_APMsg detailMsg = detailBean.getDtlPMsg();
            // 2016/08/01 S21_NA#12637 Del Start
//            String curDsOrdPosNum = detailMsg.dsOrdPosnNum_A1.getValue();
//            if (!prevDsOrdPosNum.equals(curDsOrdPosNum)) {
//                dsCpoLineNum = BigDecimal.ZERO;
//                prevDsOrdPosNum = curDsOrdPosNum;
//            }
            // 2016/08/01 S21_NA#12637 Del End

            if (MODE_DELETE.equals(pMsg.xxModeCd.getValue()) && RQST_TP_DTL_DELETE.equals(detailMsg.xxRqstTpCd_A1.getValue())) {
                isDeleteLine = true;
                // continue; 2017/05/10 S21_NA#Review structure Lv.2 RS#8390 Del
            }

            // 2017/05/10 S21_NA#Review structure Lv.2 RS#8390 Del Start
//            String orderLineNumber = detailMsg.cpoDtlLineNum_A1.getValue();
//            String orderLineSubNumber = detailMsg.cpoDtlLineSubNum_A1.getValue();
//
//            if (SET_LINE_SUB_NUM.equals(orderLineSubNumber)) {
//                cpoDtlLineNum = NWXC150001NumberingUtil.getNextCpoDtlLineNum(cpoDtlLineNum);
//                subNum = 0;
//                processLineNumber = orderLineNumber;
////                dsCpoLineNum = dsCpoLineNum.add(BigDecimal.ONE); 2016/08/01 S21_NA#12637 Del
//            } else if (orderLineNumber.equals(processLineNumber)) {
//                subNum++;
//            } else {
//                cpoDtlLineNum = NWXC150001NumberingUtil.getNextCpoDtlLineNum(cpoDtlLineNum);
//                subNum = 1;
////                dsCpoLineNum = dsCpoLineNum.add(BigDecimal.ONE); 2016/08/01 S21_NA#12637 Del
//            }
//
//            String strNum = cpoDtlLineNum;
//            String strSubNum = ZYPCommonFunc.leftPad(String.valueOf(subNum), 3, "0");
//
//            detailMsg.cpoDtlLineNum_A1.setValue(strNum);
//            detailMsg.cpoDtlLineSubNum_A1.setValue(strSubNum);
////            detailMsg.dsCpoLineNum_A1.setValue(dsCpoLineNum); 2016/08/01 S21_NA#12637 Del
            // 2017/05/10 S21_NA#Review structure Lv.2 RS#8390 Del End
        }

        // 2016/08/01 S21_NA#12637 Add Start
        for (int sltConfigIdx = 0; sltConfigIdx < pMsg.cpoConfig.getValidCount(); sltConfigIdx++) {
            NWZC150001_cpoConfigPMsg cpoConfigPMsg = pMsg.cpoConfig.no(sltConfigIdx);
            if (!S21StringUtil.isEquals(CONFIG_CATG.OUTBOUND, cpoConfigPMsg.configCatgCd.getValue())) {
                continue;
            }
            String dsOrdPosnNum = cpoConfigPMsg.dsOrdPosnNum.getValue();
            subNum = -1;
            dsCpoLineNum = 0;
            for (NWZC150001DsCpoDtlForDeleteBean detailBean : dtlList) {
                NWZC150001_APMsg detailMsg = detailBean.getDtlPMsg();
                if (!S21StringUtil.isEquals(dsOrdPosnNum, detailMsg.dsOrdPosnNum_A1.getValue())) {
                    continue;
                }
                if (MODE_DELETE.equals(pMsg.xxModeCd.getValue()) && RQST_TP_DTL_DELETE.equals(detailMsg.xxRqstTpCd_A1.getValue())) {
                    continue;
                }
                String orderLineNumber = detailMsg.cpoDtlLineNum_A1.getValue();
                String orderLineSubNumber = detailMsg.cpoDtlLineSubNum_A1.getValue();

                if (SET_LINE_SUB_NUM.equals(orderLineSubNumber)) {
                    subNum = 0;
                    processLineNumber = orderLineNumber;
                    dsCpoLineNum = dsCpoLineNum + 1;
                } else if (orderLineNumber.equals(processLineNumber)) {
                    subNum++;
                } else {
                    subNum = -1;
                    dsCpoLineNum = dsCpoLineNum + 1;
                }

                // 2018/06/05 S21_NA#25151 Add Start
                if (detailMsg.dsCpoLineNum_A1.getValueInt() != dsCpoLineNum) {
                    detailBean.setModified(true);
                }
                // 2018/06/05 S21_NA#25151 Add End
                detailMsg.dsCpoLineNum_A1.setValue(dsCpoLineNum);
                if (subNum > 0) {
                    // 2018/06/05 S21_NA#25151 Add Start
                    if (detailMsg.dsCpoLineSubNum_A1.getValueInt() != subNum) {
                        detailBean.setModified(true);
                    }
                    // 2018/06/05 S21_NA#25151 Add End
                    // 2018/06/05 S21_NA#25151 Mod Start
//                    detailMsg.dsCpoLineSubNum_A1.setValue(BigDecimal.valueOf(subNum));
                    detailMsg.dsCpoLineSubNum_A1.setValue(subNum);
                    // 2018/06/05 S21_NA#25151 Mod End
                }
            }
        }
        // 2016/08/01 S21_NA#12637 Add End
        for (NWZC150001DsCpoDtlForDeleteBean refDetailBean : dtlList) {

            NWZC150001_APMsg refMsg = refDetailBean.getDtlPMsg();
            String origRefCpoDtlLineNum = refMsg.refCpoDtlLineNum_A1.getValue() + refMsg.refCpoDtlLineSubNum_A1.getValue();
            if (S21StringUtil.isEmpty(origRefCpoDtlLineNum)) {
                // no reference line
                continue;
            }

            // search new reference line
            for (NWZC150001DsCpoDtlForDeleteBean sourceDetailBean : dtlList) {
                 String origCpoDtlLineNum = sourceDetailBean.getCurCpoDtlLineNum() + sourceDetailBean.getCurCpoDtlLineSubNum();
                if (!S21StringUtil.isEquals(origRefCpoDtlLineNum, origCpoDtlLineNum)) {
                    continue;
                }

                // original reference line is found. (include cancel
                // request line -> new line number is empty.)
                NWZC150001_APMsg sourceMsg = sourceDetailBean.getDtlPMsg();
                String newCpoDtlLineNum = sourceMsg.cpoDtlLineNum_A1.getValue() + sourceMsg.cpoDtlLineSubNum_A1.getValue();

                // 2018/06/01 S21_NA#26334 Mod Start
                // if (S21StringUtil.isNotEmpty(newCpoDtlLineNum)) {
                if (S21StringUtil.isNotEmpty(newCpoDtlLineNum) && !RQST_TP_DTL_DELETE.equals(sourceMsg.xxRqstTpCd_A1.getValue())) {
                // 2018/06/01 S21_NA#26334 Mod End

                    // re:numbering reference line number.
                    // 2018/06/05 S21_NA#25151 Add Start
                    if (!S21StringUtil.isEquals(refMsg.refCpoDtlLineNum_A1.getValue(), sourceMsg.cpoDtlLineNum_A1.getValue()) //
                            || !S21StringUtil.isEquals(refMsg.refCpoDtlLineSubNum_A1.getValue(), sourceMsg.cpoDtlLineSubNum_A1.getValue())) {
                        refDetailBean.setModified(true);
                    }
                    // 2018/06/05 S21_NA#25151 Add End
                    refMsg.refCpoDtlLineNum_A1.setValue(sourceMsg.cpoDtlLineNum_A1.getValue());
                    refMsg.refCpoDtlLineSubNum_A1.setValue(sourceMsg.cpoDtlLineSubNum_A1.getValue());

                    // re:numbering display reference line number(DS).
                    String dplyLineRefNum = editDplyLineRefNum(sourceMsg.dsOrdPosnNum_A1.getValue(), //
                            sourceMsg.dsCpoLineNum_A1.getValue(), //
                            sourceMsg.dsCpoLineSubNum_A1.getValue());
                    // 2018/06/05 S21_NA#25151 Add Start
                    if (!S21StringUtil.isEquals(refMsg.dplyLineRefNum_A1.getValue(), dplyLineRefNum)) {
                        refDetailBean.setModified(true);
                    }
                    // 2018/06/05 S21_NA#25151 Add End
                    refMsg.dplyLineRefNum_A1.setValue(dplyLineRefNum);
                } else {

                    // clear reference line number.
                    refMsg.refCpoDtlLineNum_A1.clear();
                    refMsg.refCpoDtlLineSubNum_A1.clear();

                    // clear display reference line number(DS).
                    refMsg.dplyLineRefNum_A1.clear();
                    // 2018/06/05 S21_NA#25151 Add Start
                    refDetailBean.setModified(true);
                    // 2018/06/05 S21_NA#25151 Add End
                }
            }
        }
        return isDeleteLine;
    }

    private boolean reNewOrderLineNumberForRtnDetail(NWZC150001PMsg pMsg, List<NWZC150001DsCpoRtrnDtlForDeleteBean> rtnDtlList) {

        // 2017/05/10 S21_NA#Review structure Lv.2 RS#8390 Del Start
//        String cpoDtlLineNum = "000";
        // 2017/05/10 S21_NA#Review structure Lv.2 RS#8390 Del End
        int subNum = 0;
        String processLineNumber = null;

        // 2018/06/05 S21_NA#25151 Mod Start
//        BigDecimal dsCpoLineNum = BigDecimal.ZERO;
        int dsCpoLineNum = 0;
        // 2018/06/05 S21_NA#25151 change dsCpoLineNum logic without any comments, preventing instance new BigDecimal objects.;
        // 2018/06/05 S21_NA#25151 Mod End
        // String prevDsOrdPosNum = ""; 2016/08/01 S21_NA#12637 Del

        boolean isDeleteLine = false;
        for (NWZC150001DsCpoRtrnDtlForDeleteBean rtnDetailBean : rtnDtlList) {

            NWZC150001_rtnDtlPMsg rtnDetailMsg = rtnDetailBean.getRtnDtlPMsg();
            // 2016/08/01 S21_NA#12637 Del Start
//            String curDsOrdPosNum = rtnDetailMsg.dsOrdPosnNum_B1.getValue();
//            if (!prevDsOrdPosNum.equals(curDsOrdPosNum)) {
//                dsCpoLineNum = BigDecimal.ZERO;
//                prevDsOrdPosNum = curDsOrdPosNum;
//            }
            // 2016/08/01 S21_NA#12637 Del End

            if (MODE_DELETE.equals(pMsg.xxModeCd.getValue()) && RQST_TP_RTRN_DTL_DELETE.equals(rtnDetailMsg.xxRqstTpCd_B1.getValue())) {
                isDeleteLine = true;
                // continue; 2017/05/10 S21_NA#Review structure Lv.2 RS#8390 Del
            }

            // 2017/05/10 S21_NA#Review structure Lv.2 RS#8390 Del Start
//            String orderLineNumber = rtnDetailMsg.cpoDtlLineNum_B1.getValue();
//            String orderLineSubNumber = rtnDetailMsg.cpoDtlLineSubNum_B1.getValue();
//
//            if (SET_LINE_SUB_NUM.equals(orderLineSubNumber)) {
//                cpoDtlLineNum = NWXC150001NumberingUtil.getNextCpoDtlLineNum(cpoDtlLineNum);
//                subNum = 0;
//                processLineNumber = orderLineNumber;
////                dsCpoLineNum = dsCpoLineNum.add(BigDecimal.ONE); 2016/08/01 S21_NA#12637 Del
//            } else if (orderLineNumber.equals(processLineNumber)) {
//                subNum++;
//            } else {
//                cpoDtlLineNum = NWXC150001NumberingUtil.getNextCpoDtlLineNum(cpoDtlLineNum);
//                subNum = 1;
////                dsCpoLineNum = dsCpoLineNum.add(BigDecimal.ONE); 2016/08/01 S21_NA#12637 Del
//            }
//
//            String strNum = cpoDtlLineNum;
//            String strSubNum = ZYPCommonFunc.leftPad(String.valueOf(subNum), 3, "0");
//
//            rtnDetailMsg.cpoDtlLineNum_B1.setValue(strNum);
//            rtnDetailMsg.cpoDtlLineSubNum_B1.setValue(strSubNum);
////            rtnDetailMsg.dsCpoLineNum_B1.setValue(dsCpoLineNum); 2016/08/01 S21_NA#12637 Del
            // 2017/05/10 S21_NA#Review structure Lv.2 RS#8390 Del End
        }

        // 2016/08/01 S21_NA#12637 Add Start
        for (int sltConfigIdx = 0; sltConfigIdx < pMsg.cpoConfig.getValidCount(); sltConfigIdx++) {
            NWZC150001_cpoConfigPMsg cpoConfigPMsg = pMsg.cpoConfig.no(sltConfigIdx);
            if (!S21StringUtil.isEquals(CONFIG_CATG.INBOUND, cpoConfigPMsg.configCatgCd.getValue())) {
                continue;
            }
            String dsOrdPosnNum = cpoConfigPMsg.dsOrdPosnNum.getValue();
            subNum = -1;
            dsCpoLineNum = 0;
            for (NWZC150001DsCpoRtrnDtlForDeleteBean rtnDetailBean : rtnDtlList) {
                NWZC150001_rtnDtlPMsg rtnDetailMsg = rtnDetailBean.getRtnDtlPMsg();
                if (!S21StringUtil.isEquals(dsOrdPosnNum, rtnDetailMsg.dsOrdPosnNum_B1.getValue())) {
                    continue;
                }
                if (MODE_DELETE.equals(pMsg.xxModeCd.getValue()) && RQST_TP_DTL_DELETE.equals(rtnDetailMsg.xxRqstTpCd_B1.getValue())) {
                    continue;
                }
                String orderLineNumber = rtnDetailMsg.cpoDtlLineNum_B1.getValue();
                String orderLineSubNumber = rtnDetailMsg.cpoDtlLineSubNum_B1.getValue();

                if (SET_LINE_SUB_NUM.equals(orderLineSubNumber)) {
                    subNum = 0;
                    processLineNumber = orderLineNumber;
                    dsCpoLineNum = dsCpoLineNum + 1;
                } else if (orderLineNumber.equals(processLineNumber)) {
                    subNum++;
                } else {
                    subNum = -1;
                    dsCpoLineNum = dsCpoLineNum + 1;
                }

                // 2018/06/05 S21_NA#25151 Add Start
                if (rtnDetailMsg.dsCpoLineNum_B1.getValueInt() != dsCpoLineNum) {
                    rtnDetailBean.setModified(true);
                }
                // 2018/06/05 S21_NA#25151 Add End
                rtnDetailMsg.dsCpoLineNum_B1.setValue(dsCpoLineNum);
                if (subNum > 0) {
                    // 2018/06/05 S21_NA#25151 Add Start
                    if (rtnDetailMsg.dsCpoLineSubNum_B1.getValueInt() != subNum) {
                        rtnDetailBean.setModified(true);
                    }
                    // 2018/06/05 S21_NA#25151 Add End
                    // 2018/06/05 S21_NA#25151 Mod Start
//                    rtnDetailMsg.dsCpoLineSubNum_B1.setValue(BigDecimal.valueOf(subNum));
                    rtnDetailMsg.dsCpoLineSubNum_B1.setValue(subNum);
                    // 2018/06/05 S21_NA#25151 Mod End
                }
            }
        }
        // 2016/08/01 S21_NA#12637 Add End

        if (!this.rmaLineRefRest) {
            for (NWZC150001DsCpoRtrnDtlForDeleteBean refRtnDetailBean : rtnDtlList) {

                NWZC150001_rtnDtlPMsg refMsg = refRtnDetailBean.getRtnDtlPMsg();
                // clear reference line number.
                refMsg.refCpoDtlLineNum_B1.clear();
                refMsg.refCpoDtlLineSubNum_B1.clear();

                // clear display reference line number(DS).
                refMsg.dplyLineRefNum_B1.clear();
            }
            return isDeleteLine;
        }
        for (NWZC150001DsCpoRtrnDtlForDeleteBean refRtnDetailBean : rtnDtlList) {

            NWZC150001_rtnDtlPMsg refMsg = refRtnDetailBean.getRtnDtlPMsg();
            String origRefCpoDtlLineNum = refMsg.refCpoDtlLineNum_B1.getValue() + refMsg.refCpoDtlLineSubNum_B1.getValue();
            if (S21StringUtil.isEmpty(origRefCpoDtlLineNum)) {
                // no reference line
                continue;
            }

            // search new reference line
            for (NWZC150001DsCpoRtrnDtlForDeleteBean sourceDetailBean : rtnDtlList) {
                 String origCpoDtlLineNum = sourceDetailBean.getCurCpoDtlLineNum() + sourceDetailBean.getCurCpoDtlLineSubNum();
                if (!S21StringUtil.isEquals(origRefCpoDtlLineNum, origCpoDtlLineNum)) {
                    continue;
                }

                // original reference line is found. (include cancel
                // request line -> new line number is empty.)
                NWZC150001_rtnDtlPMsg sourceMsg = sourceDetailBean.getRtnDtlPMsg();
                String newCpoDtlLineNum = sourceMsg.cpoDtlLineNum_B1.getValue() + sourceMsg.cpoDtlLineSubNum_B1.getValue();

                if (S21StringUtil.isNotEmpty(newCpoDtlLineNum)) {

                    // re:numbering reference line number.
                    // 2018/06/05 S21_NA#25151 Add Start
                    if (!S21StringUtil.isEquals(refMsg.refCpoDtlLineNum_B1.getValue(), sourceMsg.cpoDtlLineNum_B1.getValue()) //
                            || !S21StringUtil.isEquals(refMsg.refCpoDtlLineSubNum_B1.getValue(), sourceMsg.cpoDtlLineSubNum_B1.getValue())) {
                        refRtnDetailBean.setModified(true);
                    }
                    // 2018/06/05 S21_NA#25151 Add End
                    refMsg.refCpoDtlLineNum_B1.setValue(sourceMsg.cpoDtlLineNum_B1.getValue());
                    refMsg.refCpoDtlLineSubNum_B1.setValue(sourceMsg.cpoDtlLineSubNum_B1.getValue());

                    // re:numbering display reference line number(DS).
                    String dplyLineRefNum = editDplyLineRefNum(sourceMsg.dsOrdPosnNum_B1.getValue(), //
                            sourceMsg.dsCpoLineNum_B1.getValue(), //
                            sourceMsg.dsCpoLineSubNum_B1.getValue());
                    // 2018/06/05 S21_NA#25151 Add Start
                    if (!S21StringUtil.isEquals(refMsg.dplyLineRefNum_B1.getValue(), dplyLineRefNum)) {
                        refRtnDetailBean.setModified(true);
                    }
                    // 2018/06/05 S21_NA#25151 Add End
                    refMsg.dplyLineRefNum_B1.setValue(dplyLineRefNum);
                } else {

                    // clear reference line number.
                    refMsg.refCpoDtlLineNum_B1.clear();
                    refMsg.refCpoDtlLineSubNum_B1.clear();

                    // clear display reference line number(DS).
                    refMsg.dplyLineRefNum_B1.clear();
                    // 2018/06/05 S21_NA#25151 Add Start
                    refRtnDetailBean.setModified(true);
                    // 2018/06/05 S21_NA#25151 Add End
                }
            }
        }
        return isDeleteLine;
    }

    private void reNewDplyRefNum(List<NWZC150001DsCpoConfigForDeleteBean> configList, List<NWZC150001DsCpoDtlForDeleteBean> dtlList, List<NWZC150001DsCpoRtrnDtlForDeleteBean> rtnDtlList) {

        for (NWZC150001DsCpoConfigForDeleteBean configBean : configList) {
            NWZC150001_cpoConfigPMsg configMsg = configBean.getCpoConfigPMsg();
            String configCatgCd = configMsg.configCatgCd.getValue();
            String xxRqstTpCd = configMsg.xxRqstTpCd.getValue();

            if (S21StringUtil.isEquals(RQST_TP_CONFIG_DELETE, xxRqstTpCd)) {
                continue;
            }

            if (S21StringUtil.isEquals(CONFIG_CATG.OUTBOUND, configCatgCd)) {
                // 2018/06/01 S21_NA#26334 Del Start
                // reNewDplyRefNum4Outbound(configBean, dtlList);
                // 2018/06/01 S21_NA#26334 Del End
            } else if (S21StringUtil.isEquals(CONFIG_CATG.INBOUND, configCatgCd)) {
                reNewDplyRefNum4Inbound(configBean, rtnDtlList);
            }
        }
    }

    // 2018/06/01 S21_NA#26334 Del Start
//    private void reNewDplyRefNum4Outbound(NWZC150001DsCpoConfigForDeleteBean configBean, List<NWZC150001DsCpoDtlForDeleteBean> dtlList) {
//
//        String curDsOrdPosnNum = configBean.getCurDsOrdPosnNum();
//        String dplyRefNum = null;
//        for (NWZC150001DsCpoDtlForDeleteBean dtlBean : dtlList) {
//            NWZC150001_APMsg dtlMsg = dtlBean.getDtlPMsg();
//            if (S21StringUtil.isEquals(curDsOrdPosnNum, dtlBean.getCurDsOrdPosnNum()) //
//                    && S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, dtlMsg.baseCmptFlg_A1.getValue())) {
//                String dsOrdPosnNum = configBean.getCpoConfigPMsg().dsOrdPosnNum.getValue();
//                dplyRefNum = dsOrdPosnNum + "." + String.valueOf(dtlMsg.dsCpoLineNum_A1.getValue());
//                if (ZYPCommonFunc.hasValue(dtlMsg.dsCpoLineSubNum_A1)) {
//                    dplyRefNum = dplyRefNum + "." + String.valueOf(dtlMsg.dsCpoLineSubNum_A1.getValue());
//                }
//                break;
//            }
//        }
//
//        for (NWZC150001DsCpoDtlForDeleteBean dtlBean : dtlList) {
//            NWZC150001_APMsg dtlMsg = dtlBean.getDtlPMsg();
//            // 2016/08/01 S21_NA#12637 Add Start
//            if (!S21StringUtil.isEquals(curDsOrdPosnNum, dtlBean.getCurDsOrdPosnNum())) {
//                continue;
//            }
//            // 2016/08/01 S21_NA#12637 Add End
//            if (S21StringUtil.isEquals(RQST_TP_DELETE, dtlMsg.xxRqstTpCd_A1.getValue()) //
//                    || S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, dtlMsg.baseCmptFlg_A1.getValue())) {
//                dtlMsg.dplyLineRefNum_A1.clear();
//                continue;
//            }
//
//            if (ZYPCommonFunc.hasValue(dplyRefNum)) {
//                dtlMsg.dplyLineRefNum_A1.setValue(dplyRefNum);
//            } else {
//                dtlMsg.dplyLineRefNum_A1.clear();
//            }
//        }
//    }
    // 2018/06/01 S21_NA#26334 Del End

    private void reNewDplyRefNum4Inbound(NWZC150001DsCpoConfigForDeleteBean configBean, List<NWZC150001DsCpoRtrnDtlForDeleteBean> rtnDtlList) {

        if (!this.rmaLineRefRest) {
            return;
        }

        String curDsOrdPosnNum = configBean.getCurDsOrdPosnNum();
        String dplyRefNum = null;
        for (NWZC150001DsCpoRtrnDtlForDeleteBean rtnDtlBean : rtnDtlList) {
            NWZC150001_rtnDtlPMsg rtnDtlMsg = rtnDtlBean.getRtnDtlPMsg();
            if (S21StringUtil.isEquals(curDsOrdPosnNum, rtnDtlBean.getCurDsOrdPosnNum()) //
                    && S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, rtnDtlMsg.baseCmptFlg_B1.getValue())) {
                String dsOrdPosnNum = configBean.getCpoConfigPMsg().dsOrdPosnNum.getValue();
                dplyRefNum = dsOrdPosnNum + "." + String.valueOf(rtnDtlMsg.dsCpoLineNum_B1.getValue());
                if (ZYPCommonFunc.hasValue(rtnDtlMsg.dsCpoLineSubNum_B1)) {
                    dplyRefNum = dplyRefNum + "." + String.valueOf(rtnDtlMsg.dsCpoLineSubNum_B1.getValue());
                }
                break;
            }
        }

        for (NWZC150001DsCpoRtrnDtlForDeleteBean rtnDtlBean : rtnDtlList) {
            NWZC150001_rtnDtlPMsg rtnDtlMsg = rtnDtlBean.getRtnDtlPMsg();
            // 2016/08/01 S21_NA#12637 Add Start
            if (!S21StringUtil.isEquals(curDsOrdPosnNum, rtnDtlBean.getCurDsOrdPosnNum())) {
                continue;
            }
            // 2016/08/01 S21_NA#12637 Add End
            if (S21StringUtil.isEquals(RQST_TP_DELETE, rtnDtlMsg.xxRqstTpCd_B1.getValue()) //
                    || S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, rtnDtlMsg.baseCmptFlg_B1.getValue())) {
                rtnDtlMsg.dplyLineRefNum_B1.clear();
                continue;
            }

            if (ZYPCommonFunc.hasValue(dplyRefNum)) {
                rtnDtlMsg.dplyLineRefNum_B1.setValue(dplyRefNum);
            } else {
                rtnDtlMsg.dplyLineRefNum_B1.clear();
            }
        }
    }

    private String editDplyLineRefNum(String dsOrdPosnNum, BigDecimal dsCpoLineNum, BigDecimal dsCpoLineSubNum) {

        if (!ZYPCommonFunc.hasValue(dsOrdPosnNum) || !ZYPCommonFunc.hasValue(dsCpoLineNum)) {
            return "";
        }

        StringBuilder dplyLineRefNum = new StringBuilder();
        concatWithSeparator(dplyLineRefNum, dsOrdPosnNum, ".");
        concatWithSeparator(dplyLineRefNum, dsCpoLineNum.toPlainString(), ".");
        if (dsCpoLineSubNum != null) {
            concatWithSeparator(dplyLineRefNum, dsCpoLineSubNum.toPlainString(), ".");
        }
        return dplyLineRefNum.toString();
    }

    private void concatWithSeparator(StringBuilder target, String element, String separator) {

        if (!S21StringUtil.isEmpty(element)) {
            if (target.length() > 0) {
                target.append(separator);
            }
            target.append(element);
        }
    }

    private void deleteAndUpdateConfig(NWZC150001PMsg pMsg, List<NWZC150001DsCpoConfigForDeleteBean>  configList) {

//        List<NWZC150001_cpoConfigPMsg> removeConfigList = new ArrayList<NWZC150001_cpoConfigPMsg>(0);
        for (NWZC150001DsCpoConfigForDeleteBean configBean : configList) {
            NWZC150001_cpoConfigPMsg configMsg = configBean.getCpoConfigPMsg();
            if (S21StringUtil.isEquals(RQST_TP_CONFIG_DELETE, configMsg.xxRqstTpCd.getValue())) {
                removeConfig(pMsg, configMsg);
            } else {
                updConfig(pMsg, configBean);
            }
        }
    }

    private void updConfig(NWZC150001PMsg pMsg, NWZC150001DsCpoConfigForDeleteBean configBean) {

        NWZC150001_cpoConfigPMsg configMsg = configBean.getCpoConfigPMsg();
        // 2018/06/05 S21_NA#25151 Del Start
//        String curDsOrdPosnNum = configBean.getCurDsOrdPosnNum();
        // 2018/06/05 S21_NA#25151 Del End
        String newDsOrdPosnNum = configMsg.dsOrdPosnNum.getValue();
        // 2018/06/05 S21_NA#25151 Del Start
//        if (S21StringUtil.isEquals(curDsOrdPosnNum, newDsOrdPosnNum)) {
//            return;
//        }
        // 2018/06/05 S21_NA#25151 Del End
        // 2018/06/05 S21_NA#25151 Add Start
        if (!configBean.isModified()) {
            return;
        }
        // 2018/06/05 S21_NA#25151 Add End
        DS_CPO_CONFIGTMsg dsCpoConfigMsg = new DS_CPO_CONFIGTMsg();
        ZYPEZDItemValueSetter.setValue(dsCpoConfigMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsCpoConfigMsg.dsCpoConfigPk, configMsg.dsCpoConfigPk);
        dsCpoConfigMsg = (DS_CPO_CONFIGTMsg) S21FastTBLAccessor.findByKeyForUpdate(dsCpoConfigMsg);
        boolean isModified = false;
        boolean isModifiedPosnNum = false;
        if (dsCpoConfigMsg != null) {
            // 2018/06/05 S21_NA#25151 Del Start
//            dsCpoConfigMsg.dsOrdPosnNum.setValue(newDsOrdPosnNum);
//            S21ApiTBLAccessor.updateSelectionField(dsCpoConfigMsg, PARTIAL_UPD_CONFIG_VAL);
            // 2018/06/05 S21_NA#25151 Del End
            // 2018/06/05 S21_NA#25151 Add Start
            if (!S21StringUtil.isEquals(dsCpoConfigMsg.dsOrdPosnNum.getValue(), newDsOrdPosnNum)) {
                dsCpoConfigMsg.dsOrdPosnNum.setValue(newDsOrdPosnNum);
                isModified = true;
                isModifiedPosnNum = true;
            }
            if (!NWZC150001Common.isEqualsBigDecimal(dsCpoConfigMsg.mdlId.getValue(), configMsg.mdlId.getValue())) {
                if (ZYPCommonFunc.hasValue(configMsg.mdlId)) {
                    dsCpoConfigMsg.mdlId.setValue(configMsg.mdlId.getValue());
                } else {
                    dsCpoConfigMsg.mdlId.clear();
                }
                isModified = true;
            }
            if (!S21StringUtil.isEquals(dsCpoConfigMsg.mdlDescTxt.getValue(), configMsg.mdlDescTxt.getValue())) {
                if (ZYPCommonFunc.hasValue(configMsg.mdlDescTxt)) {
                    dsCpoConfigMsg.mdlDescTxt.setValue(configMsg.mdlDescTxt.getValue());
                } else {
                    dsCpoConfigMsg.mdlDescTxt.clear();
                }
                isModified = true;
            }
            if (isModified) {
                S21ApiTBLAccessor.updateSelectionField(dsCpoConfigMsg, PARTIAL_UPD_CONFIG_VAL);
            }
            // 2018/06/05 S21_NA#25151 Add End
        }

        if (isModifiedPosnNum) { // 2018/06/05 S21_NA#25151 Add Condition
            DS_CPO_SLS_CRTMsg dsCpoSlsCrMsgCond = getUpdCond4DsCpoSlsCr(pMsg, configMsg);
            DS_CPO_SLS_CRTMsg dsCpoSlsCrMsgVal = new  DS_CPO_SLS_CRTMsg();
            dsCpoSlsCrMsgVal.dsOrdPosnNum.setValue(newDsOrdPosnNum);
            S21FastTBLAccessor.updateByPartialValue(dsCpoSlsCrMsgCond, PARTIAL_UPD_CONFIG_KEY, dsCpoSlsCrMsgVal, PARTIAL_UPD_CONFIG_VAL);

            DS_CPO_SLS_CR_RECTMsg dsCpoSlsCrRecMsgCond = getUpdCond4DsCpoSlsCrRec(pMsg, configMsg);
            DS_CPO_SLS_CR_RECTMsg dsCpoSlsCrRecMsgVal = new  DS_CPO_SLS_CR_RECTMsg();
            dsCpoSlsCrRecMsgVal.dsOrdPosnNum.setValue(newDsOrdPosnNum);
            S21FastTBLAccessor.updateByPartialValue(dsCpoSlsCrRecMsgCond, PARTIAL_UPD_CONFIG_KEY, dsCpoSlsCrRecMsgVal, PARTIAL_UPD_CONFIG_VAL);
        }  // 2018/06/05 S21_NA#25151 Add Condition

        // Actualy, NWAL1510 D&I/Contact/Site Survey Screen doesn't set DS_ORD_POSN_NUM, then these process doesn't act. =>
//        // DS_CPO_DELY_INFO
//        DS_CPO_DELY_INFOTMsg dsCpoDelyInfoMsgCond = getUpdCond4DsCpoDelyInfo(pMsg, configMsg);
//        DS_CPO_DELY_INFOTMsg dsCpoDelyInfoMsgVal = new DS_CPO_DELY_INFOTMsg();
//        dsCpoDelyInfoMsgVal.dsOrdPosnNum.setValue(newDsOrdPosnNum);
//        S21FastTBLAccessor.updateByPartialValue(dsCpoDelyInfoMsgCond, PARTIAL_UPD_CONFIG_KEY, dsCpoDelyInfoMsgVal, PARTIAL_UPD_CONFIG_VAL);
//
//        // DS_CPO_ISTL_INFO
//        DS_CPO_ISTL_INFOTMsg dsCpoIstlInfoMsgCond = getUpdCond4DsCpoIstlInfo(pMsg, configMsg);
//        DS_CPO_ISTL_INFOTMsg dsCpoIstlInfoMsgVal = new DS_CPO_ISTL_INFOTMsg();
//        dsCpoIstlInfoMsgVal.dsOrdPosnNum.setValue(newDsOrdPosnNum);
//        S21FastTBLAccessor.updateByPartialValue(dsCpoIstlInfoMsgCond, PARTIAL_UPD_CONFIG_KEY, dsCpoIstlInfoMsgVal, PARTIAL_UPD_CONFIG_VAL);
        // Actualy, NWAL1510 D&I/Contact/Site Survey Screen doesn't set DS_ORD_POSN_NUM, then these process doesn't act. <=
    }

    private boolean removeConfig(NWZC150001PMsg pMsg, NWZC150001_cpoConfigPMsg removeConfigMsg) {

        boolean rslt = false;

//        List<DS_CPO_CONFIGTMsg> removeDsCpoConfigMsgList = new ArrayList<DS_CPO_CONFIGTMsg>(0);
//        List<DS_CPO_CONFIG_RECTMsg> removeDsCpoConfigRecMsgList = new ArrayList<DS_CPO_CONFIG_RECTMsg>(0);
//        List<DS_CPO_SLS_CRTMsg> removeDsCpoSlsCrMsgList = new ArrayList<DS_CPO_SLS_CRTMsg>(0);
//        List<DS_CPO_SLS_CR_RECTMsg> removeDsCpoSlsCrRecMsgList = new ArrayList<DS_CPO_SLS_CR_RECTMsg>(0);
//        List<DS_CPO_DELY_INFOTMsg> removeDsCpoDelyInfoMsgList = new ArrayList<DS_CPO_DELY_INFOTMsg>(0);
//        List<DS_SITE_SRVYTMsg> removeDsSiteSrvyMsgList = new ArrayList<DS_SITE_SRVYTMsg>(0);
//        List<DS_CPO_ISTL_INFOTMsg> removeDsCpoIstlInfoMsgList = new ArrayList<DS_CPO_ISTL_INFOTMsg>(0);
//        List<DS_CPO_CTAC_PSNTMsg> removeDsCpoCtacPsnMsgList = new ArrayList<DS_CPO_CTAC_PSNTMsg>(0);
//
//
//        for (NWZC150001_cpoConfigPMsg removeConfigMsg : removeConfigList) {
//            // DS_CPO_CONFIG
//            removeDsCpoConfigMsgList.add(getUpdCond4DsCpoConfig(pMsg, removeConfigMsg));
//            // DS_CPO_CONFIG_REC
//            removeDsCpoConfigRecMsgList.add(getUpdCond4DsCpoConfigRec(pMsg, removeConfigMsg));
//            // DS_CPO_SLS_CR
//            removeDsCpoSlsCrMsgList.add(getUpdCond4DsCpoSlsCr(pMsg, removeConfigMsg));
//            // DS_CPO_SLS_CR_REC
//            removeDsCpoSlsCrRecMsgList.add(getUpdCond4DsCpoSlsCrRec(pMsg, removeConfigMsg));
//            // DS_CPO_DELY_INFO
//            removeDsCpoDelyInfoMsgList.add(getUpdCond4DsCpoDelyInfo(pMsg, removeConfigMsg));
//            // DS_SITE_SRVY
//            removeDsSiteSrvyMsgList.add(getUpdCond4DsSiteSrvy(pMsg, removeConfigMsg));
//            // DS_CPO_ISTL_INFO
//            removeDsCpoIstlInfoMsgList.add(getUpdCond4DsCpoIstlInfo(pMsg, removeConfigMsg));
//            // DS_CPO_CTAC_PSN
//            removeDsCpoCtacPsnMsgList.add(getUpdCond4DsCpoCtacPsn(pMsg, removeConfigMsg));
//        }
//        int delCnt = S21FastTBLAccessor.removePhysical(removeDsCpoConfigMsgList.toArray(new DS_CPO_CONFIGTMsg[0]));
//        // DS_CPO_CONFIG_REC
//        for (DS_CPO_CONFIG_RECTMsg removeDsCpoConfigRecMsg : removeDsCpoConfigRecMsgList) {
//            S21FastTBLAccessor.removeByPartialValue(removeDsCpoConfigRecMsg, PARTIAL_UPD_CONFIG_KEY);
//        }
//        // DS_CPO_SLS_CR
//        for (DS_CPO_SLS_CRTMsg removeDsCpoSlsCrMsg : removeDsCpoSlsCrMsgList) {
//            S21FastTBLAccessor.removeByPartialValue(removeDsCpoSlsCrMsg, PARTIAL_UPD_CONFIG_KEY);
//        }
//        // DS_CPO_SLS_CR_REC
//        for (DS_CPO_SLS_CR_RECTMsg removeDsCpoSlsCrRecMsg : removeDsCpoSlsCrRecMsgList) {
//            S21FastTBLAccessor.removeByPartialValue(removeDsCpoSlsCrRecMsg, PARTIAL_UPD_CONFIG_KEY);
//        }
//        // DS_CPO_DELY_INFO
//        for (DS_CPO_DELY_INFOTMsg removeDsCpoDelyInfoMsg : removeDsCpoDelyInfoMsgList) {
//            S21FastTBLAccessor.removeByPartialValue(removeDsCpoDelyInfoMsg, PARTIAL_UPD_CONFIG_KEY);
//        }
//        // DS_SITE_SRVYT
//        for (DS_SITE_SRVYTMsg removeDsSiteSrvyMsg : removeDsSiteSrvyMsgList) {
//            S21FastTBLAccessor.removeByPartialValue(removeDsSiteSrvyMsg, PARTIAL_UPD_CONFIG_KEY);
//        }
//        // DS_CPO_ISTL_INFO
//        for (DS_CPO_ISTL_INFOTMsg removeDsCpoIstlInfoMsg : removeDsCpoIstlInfoMsgList) {
//            S21FastTBLAccessor.removeByPartialValue(removeDsCpoIstlInfoMsg, PARTIAL_UPD_CONFIG_KEY);
//        }
//        // DS_CPO_CTAC_PSN
//        for (DS_CPO_CTAC_PSNTMsg removeDsCpoCtacPsnMsg : removeDsCpoCtacPsnMsgList) {
//            S21FastTBLAccessor.removeByPartialValue(removeDsCpoCtacPsnMsg, PARTIAL_UPD_CONFIG_KEY);
//        }

        // DS_CPO_CONFIG
        DS_CPO_CONFIGTMsg dsCpoConfigMsg = getUpdCond4DsCpoConfig(pMsg, removeConfigMsg);
        S21ApiTBLAccessor.remove(dsCpoConfigMsg);
        // DS_CPO_CONFIG_REC
        DS_CPO_CONFIG_RECTMsg removeDsCpoConfigRecMsg = getUpdCond4DsCpoConfigRec(pMsg, removeConfigMsg);
        S21FastTBLAccessor.removeByPartialValue(removeDsCpoConfigRecMsg, PARTIAL_UPD_CONFIG_KEY);
        // DS_CPO_SLS_CR
        DS_CPO_SLS_CRTMsg removeDsCpoSlsCrMsg = getUpdCond4DsCpoSlsCr(pMsg, removeConfigMsg);
        S21FastTBLAccessor.removeByPartialValue(removeDsCpoSlsCrMsg, PARTIAL_UPD_CONFIG_KEY);
        // DS_CPO_SLS_CR_REC
        DS_CPO_SLS_CR_RECTMsg removeDsCpoSlsCrRecMsg = getUpdCond4DsCpoSlsCrRec(pMsg, removeConfigMsg);
        S21FastTBLAccessor.removeByPartialValue(removeDsCpoSlsCrRecMsg, PARTIAL_UPD_CONFIG_KEY);
        // DS_CPO_DELY_INFO
        DS_CPO_DELY_INFOTMsg removeDsCpoDelyInfoMsg = getUpdCond4DsCpoDelyInfo(pMsg, removeConfigMsg);
        S21FastTBLAccessor.removeByPartialValue(removeDsCpoDelyInfoMsg, PARTIAL_UPD_CONFIG_KEY);
        // DS_SITE_SRVY
        DS_SITE_SRVYTMsg removeDsSiteSrvyMsg =getUpdCond4DsSiteSrvy(pMsg, removeConfigMsg);
        S21FastTBLAccessor.removeByPartialValue(removeDsSiteSrvyMsg, PARTIAL_UPD_CONFIG_KEY);
        // DS_CPO_ISTL_INFO
        DS_CPO_ISTL_INFOTMsg removeDsCpoIstlInfoMsg = getUpdCond4DsCpoIstlInfo(pMsg, removeConfigMsg);
        S21FastTBLAccessor.removeByPartialValue(removeDsCpoIstlInfoMsg, PARTIAL_UPD_CONFIG_KEY);
        // DS_CPO_CTAC_PSN
        DS_CPO_CTAC_PSNTMsg removeDsCpoCtacPsnMsg = getUpdCond4DsCpoCtacPsn(pMsg, removeConfigMsg);
        S21FastTBLAccessor.removeByPartialValue(removeDsCpoCtacPsnMsg, PARTIAL_UPD_CONFIG_KEY);

        return rslt;
    }

    private void deleteAndUpdateCpoDtl(NWZC150001PMsg pMsg, List<NWZC150001DsCpoDtlForDeleteBean> dtlList) {

        // 2017/05/25 S21_NA#Review structure Lv.2 RS#8391-2 Add Start
        NSZC115001PMsg shellApiPMsg = new NSZC115001PMsg();
        ZYPEZDItemValueSetter.setValue(shellApiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        shellApiPMsg.xxProcMd.setValue(NSZC115001Constant.PROC_MODE_PHYS_DEL);
        // 2018/04/17 S21_NA#24458-2 add start
        ZYPEZDItemValueSetter.setValue(shellApiPMsg.slsDt, pMsg.slsDt);
        // 2018/04/17 S21_NA#24458-2 add end
        int shellApiValCnt = 0;
        // 2017/05/25 S21_NA#Review structure Lv.2 RS#8391-2 Add End

        for (NWZC150001DsCpoDtlForDeleteBean dtlBean : dtlList) {
            String xxRqstTpCd = dtlBean.getDtlPMsg().xxRqstTpCd_A1.getValue();
            if (S21StringUtil.isEquals(RQST_TP_DTL_DELETE, xxRqstTpCd)) {
                removeDetail(pMsg, dtlBean.getDtlPMsg());

                // 2017/05/10 S21_NA#Review structure Lv.2 RS#8391 Del Old Logic Start
//                // CPO_SVC
//                removeCpoSvc(pMsg, dtlBean);
                // 2017/05/10 S21_NA#Review structure Lv.2 RS#8391 Del Old Logic End
                // 2017/05/25 S21_NA#Review structure Lv.2 RS#8391-2 Add Start
                shellApiPMsg.svcConfigRefList.no(shellApiValCnt).cpoOrdNum.setValue(pMsg.cpoOrdNum.getValue());
                shellApiPMsg.svcConfigRefList.no(shellApiValCnt).cpoDtlLineNum.setValue(dtlBean.getDtlPMsg().cpoDtlLineNum_A1.getValue());
                // Mod Start 2017/06/19 QC#19150
//                shellApiPMsg.svcConfigRefList.no(shellApiValCnt).cpoDtlLineSubNum.setValue(dtlBean.getDtlPMsg().cpoDtlLineNum_A1.getValue());
                shellApiPMsg.svcConfigRefList.no(shellApiValCnt).cpoDtlLineSubNum.setValue(dtlBean.getDtlPMsg().cpoDtlLineSubNum_A1.getValue());
                // Mod End 2017/06/19 QC#19150
                shellApiValCnt++;
                // 2017/05/25 S21_NA#Review structure Lv.2 RS#8391-2 Add End
            } else {
                updateCpoDtl(pMsg, dtlBean);

//                // CPO SVC
//                updateCpoSvc(pMsg, dtlBean);
            }
        }
//        resetBaseComponentFlag(pMsg); 2016/08/04 S21_NA#7821-2 Del
        // 2017/05/25 S21_NA#Review structure Lv.2 RS#8391-2 Add Start
        if (shellApiValCnt > 0) {
            shellApiPMsg.svcConfigRefList.setValidCount(shellApiValCnt);

            new NSZC115001().execute(shellApiPMsg, onBatchType);
        }
        // 2017/05/25 S21_NA#Review structure Lv.2 RS#8391-2 Add End
    }

    // 2018/06/05 S21_NA#25151 Del Start
//    // 2016/11/29 S21_NA#16214 Add Start
//    private void updMdlIdForConfig(NWZC150001PMsg pMsg, List<NWZC150001DsCpoConfigForDeleteBean>  configList) {
//
//        for (NWZC150001DsCpoConfigForDeleteBean configBean : configList) {
//            NWZC150001_cpoConfigPMsg configMsg = configBean.getCpoConfigPMsg();
//
//            DS_CPO_CONFIGTMsg dsCpoConfigMsg = new DS_CPO_CONFIGTMsg();
//            ZYPEZDItemValueSetter.setValue(dsCpoConfigMsg.glblCmpyCd, pMsg.glblCmpyCd);
//            ZYPEZDItemValueSetter.setValue(dsCpoConfigMsg.dsCpoConfigPk, configMsg.dsCpoConfigPk);
//            dsCpoConfigMsg = (DS_CPO_CONFIGTMsg) S21FastTBLAccessor.findByKeyForUpdate(dsCpoConfigMsg);
//            if (dsCpoConfigMsg != null) {
//                // 2018/05/20 S21_NA#25604 Mod Start
////                dsCpoConfigMsg.mdlId.setValue(configMsg.mdlId.getValue());
////                S21ApiTBLAccessor.updateSelectionField(dsCpoConfigMsg, PARTIAL_UPD_CONFIG_MDL_ID);
//                if (ZYPCommonFunc.hasValue(configMsg.mdlId)) {
//                    dsCpoConfigMsg.mdlId.setValue(configMsg.mdlId.getValue());
//                } else {
//                    dsCpoConfigMsg.mdlId.clear();
//                }
//                if (ZYPCommonFunc.hasValue(configMsg.mdlDescTxt)) {
//                    dsCpoConfigMsg.mdlDescTxt.setValue(configMsg.mdlDescTxt.getValue());
//                } else {
//                    dsCpoConfigMsg.mdlDescTxt.clear();
//                }
//                S21ApiTBLAccessor.updateSelectionField(dsCpoConfigMsg, PARTIAL_UPD_CONFIG_VAL);
//                // 2018/05/20 S21_NA#25604 Mod End
//            }
//        }
//    }
//    // 2016/11/29 S21_NA#16214 Add End
    // 2018/06/05 S21_NA#25151 Del End

    // 2016/08/04 S21_NA#7821-2 Del Start
//    private void resetBaseComponentFlag(NWZC150001PMsg pMsg) {
//
//        List<Map<String, String>> baseCmptMapList = getBaseComponentReln(this.glblCmpyCd, pMsg.cpoOrdNum.getValue());
//
//        if (baseCmptMapList == null || baseCmptMapList.isEmpty()) {
//            return;
//        }
//        String prevDsOrdPosnNum = baseCmptMapList.get(0).get("DS_ORD_POSN_NUM");
//        List<Map<String, String>> baseCmptOneConfigMapList = new ArrayList<Map<String, String>>(0);
//        for (Map<String, String> baseCmptMap :  baseCmptMapList) {
//            String dsOrdPosnNum = baseCmptMap.get("DS_ORD_POSN_NUM");
//            if (!S21StringUtil.isEquals(prevDsOrdPosnNum, dsOrdPosnNum)) {
//                updateBaseCmptFlg(baseCmptOneConfigMapList);
//                baseCmptOneConfigMapList.clear();
//                prevDsOrdPosnNum = dsOrdPosnNum;
//            }
//            baseCmptOneConfigMapList.add(baseCmptMap);
//        }
//        updateBaseCmptFlg(baseCmptOneConfigMapList);
//    }
//    private void updateBaseCmptFlg(List<Map<String, String>> baseCmptOneConfigMapList) {
//
//        CPO_DTLTMsg dsCpoDtlMsgCond4Valset = null;
//        CPO_DTLTMsg dsCpoDtlMsgCond4InstlFlg = null;
//        String cpoOrdNum = baseCmptOneConfigMapList.get(0).get("CPO_ORD_NUM");
//        String dsOrdPosnNum = baseCmptOneConfigMapList.get(0).get("DS_ORD_POSN_NUM");
//
//        for (Map<String, String> baseCmptOneConfigMap : baseCmptOneConfigMapList) {
//            if (dsCpoDtlMsgCond4Valset == null && ZYPCommonFunc.hasValue(baseCmptOneConfigMap.get("MDSE_TP_CTX_TP_CD"))) {
//                dsCpoDtlMsgCond4Valset = new CPO_DTLTMsg();
//
//                ZYPEZDItemValueSetter.setValue(dsCpoDtlMsgCond4Valset.glblCmpyCd, this.glblCmpyCd);
//                ZYPEZDItemValueSetter.setValue(dsCpoDtlMsgCond4Valset.cpoOrdNum, baseCmptOneConfigMap.get("CPO_ORD_NUM"));
//                ZYPEZDItemValueSetter.setValue(dsCpoDtlMsgCond4Valset.cpoDtlLineNum, baseCmptOneConfigMap.get("CPO_DTL_LINE_NUM"));
//                ZYPEZDItemValueSetter.setValue(dsCpoDtlMsgCond4Valset.cpoDtlLineSubNum, baseCmptOneConfigMap.get("CPO_DTL_LINE_SUB_NUM"));
//            } else if (dsCpoDtlMsgCond4InstlFlg == null) {
//                dsCpoDtlMsgCond4InstlFlg = new CPO_DTLTMsg();
//
//                ZYPEZDItemValueSetter.setValue(dsCpoDtlMsgCond4InstlFlg.glblCmpyCd, this.glblCmpyCd);
//                ZYPEZDItemValueSetter.setValue(dsCpoDtlMsgCond4InstlFlg.cpoOrdNum, baseCmptOneConfigMap.get("CPO_ORD_NUM"));
//                ZYPEZDItemValueSetter.setValue(dsCpoDtlMsgCond4InstlFlg.cpoDtlLineNum, baseCmptOneConfigMap.get("CPO_DTL_LINE_NUM"));
//                ZYPEZDItemValueSetter.setValue(dsCpoDtlMsgCond4InstlFlg.cpoDtlLineSubNum, baseCmptOneConfigMap.get("CPO_DTL_LINE_SUB_NUM"));
//            }
//        }
//
//        CPO_DTLTMsg dsCpoDtlMsgVal = null;
//        String dplyRefNum = null;
//        String refCpoDtlLineNum = null;
//        String refCpoDtlLineSubNum = null;
//
//        if (dsCpoDtlMsgCond4Valset != null) {
//            dsCpoDtlMsgVal = (CPO_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdateAPI(dsCpoDtlMsgCond4Valset);
//        } else if (dsCpoDtlMsgCond4InstlFlg != null) {
//            dsCpoDtlMsgVal = (CPO_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdateAPI(dsCpoDtlMsgCond4InstlFlg);
//        }
//
//        if (dsCpoDtlMsgVal != null) {
//            dsCpoDtlMsgVal.baseCmptFlg.setValue(ZYPConstant.FLG_ON_Y);
//            dsCpoDtlMsgVal.dplyLineRefNum.clear();
//            dsCpoDtlMsgVal.refCpoDtlLineNum.clear();
//            dsCpoDtlMsgVal.refCpoDtlLineSubNum.clear();
//            dplyRefNum = dsCpoDtlMsgVal.dsOrdPosnNum.getValue() + "." + String.valueOf(dsCpoDtlMsgVal.dsCpoLineNum.getValue());
//            if (ZYPCommonFunc.hasValue(dsCpoDtlMsgVal.dsCpoLineSubNum)) {
//                dplyRefNum = dplyRefNum + "." + String.valueOf(dsCpoDtlMsgVal.dsCpoLineSubNum.getValue());
//            }
//            refCpoDtlLineNum = dsCpoDtlMsgVal.cpoDtlLineNum.getValue();
//            refCpoDtlLineSubNum = dsCpoDtlMsgVal.cpoDtlLineSubNum.getValue();
//        }
//
//        CPO_DTLTMsg baseCmptFlgOffCondMsg = new CPO_DTLTMsg();
//        ZYPEZDItemValueSetter.setValue(baseCmptFlgOffCondMsg.glblCmpyCd, this.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(baseCmptFlgOffCondMsg.cpoOrdNum,  cpoOrdNum);
//        ZYPEZDItemValueSetter.setValue(baseCmptFlgOffCondMsg.dsOrdPosnNum,  dsOrdPosnNum);
//
//        CPO_DTLTMsg baseCmptFlgOffValMsg = new CPO_DTLTMsg();
//        baseCmptFlgOffValMsg.baseCmptFlg.setValue(ZYPConstant.FLG_OFF_N);
//        if (ZYPCommonFunc.hasValue(dplyRefNum)) {
//            baseCmptFlgOffValMsg.dplyLineRefNum.setValue(dplyRefNum);
//            baseCmptFlgOffValMsg.refCpoDtlLineNum.setValue(refCpoDtlLineNum);
//            baseCmptFlgOffValMsg.refCpoDtlLineSubNum.setValue(refCpoDtlLineSubNum);
//        } else {
//            baseCmptFlgOffValMsg.dplyLineRefNum.clear();
//            baseCmptFlgOffValMsg.refCpoDtlLineNum.clear();
//            baseCmptFlgOffValMsg.refCpoDtlLineSubNum.clear();
//        }
//        
//        S21FastTBLAccessor.updateByPartialValue( //
//                baseCmptFlgOffCondMsg, //
//                new String[] {"glblCmpyCd","cpoOrdNum","dsOrdPosnNum"}, //
//                baseCmptFlgOffValMsg, //
//                new String[] {"baseCmptFlg","dplyLineRefNum", "refCpoDtlLineNum", "refCpoDtlLineSubNum"});
//
//        if (dsCpoDtlMsgVal != null) {
//            S21ApiTBLAccessor.updateSelectionField(dsCpoDtlMsgVal, new String[] {"baseCmptFlg", "dplyLineRefNum", "refCpoDtlLineNum", "refCpoDtlLineSubNum"});
//        }
//    }
    // 2016/08/04 S21_NA#7821-2 Del End

    // 2017/05/10 S21_NA#Review structure Lv.2 RS#8390 Del Start
    private void updateCpoDtl(NWZC150001PMsg pMsg, NWZC150001DsCpoDtlForDeleteBean dtlBean) {

        // 2018/06/05 S21_NA#25151 Add Start
        if (!dtlBean.isModified()) {
            return;
        }
        // 2018/06/05 S21_NA#25151 Add End
        NWZC150001_APMsg dtlMsg = dtlBean.getDtlPMsg();
        // 2016/08/04 S21_NA#7821-2 Del Start
//        if (isEqualLine(dtlBean)) {
//            return;
//        }
		// 2016/08/04 S21_NA#7821-2 Del End

        // Update CPO_DTL
        CPO_DTLTMsg dbCpoDtl = new CPO_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(dbCpoDtl.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dbCpoDtl.cpoOrdNum, pMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(dbCpoDtl.cpoDtlLineNum, dtlBean.getCurCpoDtlLineNum());
        ZYPEZDItemValueSetter.setValue(dbCpoDtl.cpoDtlLineSubNum, dtlBean.getCurCpoDtlLineSubNum());

        dbCpoDtl = (CPO_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdate(dbCpoDtl);
        if (dbCpoDtl != null) {
            // 2017/05/10 S21_NA#Review structure Lv.2 RS#8390 Del Start
//            CPO_DTLTMsg newCpoDtl = new CPO_DTLTMsg();
//            EZDMsg.copy(dbCpoDtl, null, newCpoDtl, null);
//            S21ApiTBLAccessor.remove(dbCpoDtl);
            // 2017/05/10 S21_NA#Review structure Lv.2 RS#8390 Del End

            // 2017/05/10 S21_NA#Review structure Lv.2 RS#8390 Mod Start
//            ZYPEZDItemValueSetter.setValue(newCpoDtl.cpoDtlLineNum, dtlMsg.cpoDtlLineNum_A1);
//            ZYPEZDItemValueSetter.setValue(newCpoDtl.cpoDtlLineSubNum, dtlMsg.cpoDtlLineSubNum_A1);
//            ZYPEZDItemValueSetter.setValue(newCpoDtl.dsOrdPosnNum, dtlMsg.dsOrdPosnNum_A1);
//            ZYPEZDItemValueSetter.setValue(newCpoDtl.dsCpoLineNum, dtlMsg.dsCpoLineNum_A1);
//            ZYPEZDItemValueSetter.setValue(newCpoDtl.dsCpoLineSubNum, dtlMsg.dsCpoLineSubNum_A1);
//            ZYPEZDItemValueSetter.setValue(newCpoDtl.dplyLineRefNum, dtlMsg.dplyLineRefNum_A1);
//            ZYPEZDItemValueSetter.setValue(newCpoDtl.baseCmptFlg, dtlMsg.baseCmptFlg_A1); // 2016/08/04 S21_NA#7821-2 Add
//            ZYPEZDItemValueSetter.setValue(newCpoDtl.refCpoDtlLineNum, dtlMsg.refCpoDtlLineNum_A1); // 2016/08/22 S21_NA#7821-3 Add
//            ZYPEZDItemValueSetter.setValue(newCpoDtl.refCpoDtlLineSubNum, dtlMsg.refCpoDtlLineSubNum_A1); // 2016/08/22 S21_NA#7821-3 Add
//            S21ApiTBLAccessor.insert(newCpoDtl);
            ZYPEZDItemValueSetter.setValue(dbCpoDtl.dsOrdPosnNum, dtlMsg.dsOrdPosnNum_A1);
            ZYPEZDItemValueSetter.setValue(dbCpoDtl.dsCpoLineNum, dtlMsg.dsCpoLineNum_A1);
            ZYPEZDItemValueSetter.setValue(dbCpoDtl.dsCpoLineSubNum, dtlMsg.dsCpoLineSubNum_A1);
            ZYPEZDItemValueSetter.setValue(dbCpoDtl.dplyLineRefNum, dtlMsg.dplyLineRefNum_A1);
            ZYPEZDItemValueSetter.setValue(dbCpoDtl.baseCmptFlg, dtlMsg.baseCmptFlg_A1);
            ZYPEZDItemValueSetter.setValue(dbCpoDtl.refCpoDtlLineNum, dtlMsg.refCpoDtlLineNum_A1);
            ZYPEZDItemValueSetter.setValue(dbCpoDtl.refCpoDtlLineSubNum, dtlMsg.refCpoDtlLineSubNum_A1);

            String[] valKey = new String[7];
            valKey[0] = "dsOrdPosnNum";
            valKey[1] = "dsCpoLineNum";
            valKey[2] = "dsCpoLineSubNum";
            valKey[3] = "dplyLineRefNum";
            valKey[4] = "baseCmptFlg";
            valKey[5] = "refCpoDtlLineNum";
            valKey[6] = "refCpoDtlLineSubNum";
            S21ApiTBLAccessor.updateSelectionField(dbCpoDtl, valKey);
            // 2017/05/10 S21_NA#Review structure Lv.2 RS#8390 Mod End
        } else { // 2016/09/26 S21_NA#10274 Add Start
            return;
        } // 2016/09/26 S21_NA#10274 Add End

        // Update CPO_DTL_REC
        CPO_DTL_RECTMsg cpoDtlRecMsgCond = new CPO_DTL_RECTMsg();
        EZDMsg.copy(dbCpoDtl, null, cpoDtlRecMsgCond, null);
//        ZYPEZDItemValueSetter.setValue(cpoDtlRecMsgCond.glblCmpyCd, pMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(cpoDtlRecMsgCond.cpoOrdNum, pMsg.cpoOrdNum);
//        ZYPEZDItemValueSetter.setValue(cpoDtlRecMsgCond.cpoDtlLineNum, dtlBean.getCurCpoDtlLineNum());
//        ZYPEZDItemValueSetter.setValue(cpoDtlRecMsgCond.cpoDtlLineSubNum, dtlBean.getCurCpoDtlLineSubNum());

        CPO_DTL_RECTMsg cpoDtlRecMsgVal = getUpdCond4CpoDtlRec(pMsg, dtlMsg);
        ZYPEZDItemValueSetter.setValue(cpoDtlRecMsgVal.dsOrdPosnNum, dtlMsg.dsOrdPosnNum_A1);
        ZYPEZDItemValueSetter.setValue(cpoDtlRecMsgVal.dsCpoLineNum, dtlMsg.dsCpoLineNum_A1);
        ZYPEZDItemValueSetter.setValue(cpoDtlRecMsgVal.dsCpoLineSubNum, dtlMsg.dsCpoLineSubNum_A1);
        ZYPEZDItemValueSetter.setValue(cpoDtlRecMsgVal.dplyLineRefNum, dtlMsg.dplyLineRefNum_A1);

        // 2017/05/10 S21_NA#Review structure Lv.2 RS#8390 Mod Start
//        String[] valKey = new String[6];
//        valKey[0] = PARTIAL_UPD_DTL_VAL[0];
//        valKey[1] = PARTIAL_UPD_DTL_VAL[1];
//        valKey[2] = "dsOrdPosnNum";
//        valKey[3] = "dsCpoLineNum";
//        valKey[4] = "dsCpoLineSubNum";
//        valKey[5] = "dplyLineRefNum";
        String[] valKey = new String[4];
        valKey[0] = "dsOrdPosnNum";
        valKey[1] = "dsCpoLineNum";
        valKey[2] = "dsCpoLineSubNum";
        valKey[3] = "dplyLineRefNum";
        // 2017/05/10 S21_NA#Review structure Lv.2 RS#8390 Mod End

        S21FastTBLAccessor.updateByPartialValue(cpoDtlRecMsgCond, PARTIAL_UPD_DTL_KEY, cpoDtlRecMsgVal, valKey);

        // 2017/05/10 S21_NA#Review structure Lv.2 RS#8390 Del Start
//        // ORD_PRC_CALC_BASE
//        ORD_PRC_CALC_BASETMsg ordPrcCalcBaseMsgCond = new ORD_PRC_CALC_BASETMsg();
//        EZDMsg.copy(dbCpoDtl, null, ordPrcCalcBaseMsgCond, null);
//
//        ORD_PRC_CALC_BASETMsg ordPrcCalcBaseMsgVal = getUpdCond4OrdPrcCalcBase(pMsg, dtlMsg);
//        S21FastTBLAccessor.updateByPartialValue(ordPrcCalcBaseMsgCond, PARTIAL_UPD_DTL_KEY, ordPrcCalcBaseMsgVal, PARTIAL_UPD_DTL_VAL);
//
//        // ORD_PRC_CALC_BASE
//        ORD_PRC_CALC_BASE_RECTMsg ordPrcCalcBaseRecMsgCond = new ORD_PRC_CALC_BASE_RECTMsg();
//        EZDMsg.copy(dbCpoDtl, null, ordPrcCalcBaseRecMsgCond, null);
//
//        ORD_PRC_CALC_BASE_RECTMsg ordPrcCalcBaseRecMsgVal = getUpdCond4OrdPrcCalcBaseRec(pMsg, dtlMsg);
//        S21FastTBLAccessor.updateByPartialValue(ordPrcCalcBaseRecMsgCond, PARTIAL_UPD_DTL_KEY, ordPrcCalcBaseRecMsgVal, PARTIAL_UPD_DTL_VAL);
//
//        // HLD
//        HLDTMsg hldMsgCond = new HLDTMsg();
//        EZDMsg.copy(dbCpoDtl, null, hldMsgCond, null);
//
//        HLDTMsg hldMsgVal = getUpdCond4Hld(pMsg, dtlMsg);
//        S21FastTBLAccessor.updateByPartialValue(hldMsgCond, PARTIAL_UPD_DTL_KEY, hldMsgVal, PARTIAL_UPD_DTL_VAL);
        // 2017/05/10 S21_NA#Review structure Lv.2 RS#8390 Del End
    }
    // 2017/05/10 S21_NA#Review structure Lv.2 RS#8390 Del End

    // 2017/05/10 S21_NA#Review structure Lv.2 RS#8391 Del Old Logic Start
//    private void updateCpoSvc(NWZC150001PMsg pMsg, NWZC150001DsCpoDtlForDeleteBean dtlBean) {
//
//        if (isEqualLine(dtlBean)) {
//            return;
//        }
//        NWZC150001_APMsg dtlMsg = dtlBean.getDtlPMsg();
//        // CPO_SVC_CONFIG_REF
//        CPO_SVC_CONFIG_REFTMsg cpoSvcConfigRefMsgCond = new CPO_SVC_CONFIG_REFTMsg();
//        ZYPEZDItemValueSetter.setValue(cpoSvcConfigRefMsgCond.glblCmpyCd, pMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(cpoSvcConfigRefMsgCond.cpoOrdNum, pMsg.cpoOrdNum);
//        ZYPEZDItemValueSetter.setValue(cpoSvcConfigRefMsgCond.cpoDtlLineNum, dtlBean.getCurCpoDtlLineNum());
//        ZYPEZDItemValueSetter.setValue(cpoSvcConfigRefMsgCond.cpoDtlLineSubNum, dtlBean.getCurCpoDtlLineSubNum());
//
//        CPO_SVC_CONFIG_REFTMsg cpoSvcConfigRefMsgVal = getUpdCond4CpoSvcConfigRef(pMsg, dtlMsg);
//        S21FastTBLAccessor.updateByPartialValue(cpoSvcConfigRefMsgCond, PARTIAL_UPD_DTL_KEY, cpoSvcConfigRefMsgVal, PARTIAL_UPD_DTL_VAL);
//
//        // CPO_SVC_CONFIG_REF_REC
//        CPO_SVC_CONFIG_REF_RECTMsg cpoSvcConfigRefRecMsgCond = new CPO_SVC_CONFIG_REF_RECTMsg();
//        EZDMsg.copy(cpoSvcConfigRefMsgCond, null, cpoSvcConfigRefRecMsgCond, null);
//
//        CPO_SVC_CONFIG_REF_RECTMsg cpoSvcConfigRefRecMsgVal = getUpdCond4CpoSvcConfigRefRec(pMsg, dtlMsg);
//        S21FastTBLAccessor.updateByPartialValue(cpoSvcConfigRefRecMsgCond, PARTIAL_UPD_DTL_KEY, cpoSvcConfigRefRecMsgVal, PARTIAL_UPD_DTL_VAL);
//
//        // CPO_SVC_ADDL_BASE_PRC
//        CPO_SVC_ADDL_BASE_PRCTMsg cpoSvcAddlBasePrcMsgCond = new CPO_SVC_ADDL_BASE_PRCTMsg();
//        EZDMsg.copy(cpoSvcConfigRefMsgCond, null, cpoSvcAddlBasePrcMsgCond, null);
//
//        CPO_SVC_ADDL_BASE_PRCTMsg cpoSvcAddlBasePrcMsgVal = getUpdCond4CpoSvcAddlBasePrc(pMsg, dtlMsg);
//        S21FastTBLAccessor.updateByPartialValue(cpoSvcAddlBasePrcMsgCond, PARTIAL_UPD_DTL_KEY, cpoSvcAddlBasePrcMsgVal, PARTIAL_UPD_DTL_VAL);
//
//        // CPO_SVC_ADDL_BASE_REC
//        CPO_SVC_ADDL_BASE_RECTMsg cpoSvcAddlBaseRecMsgCond = new CPO_SVC_ADDL_BASE_RECTMsg();
//        EZDMsg.copy(cpoSvcConfigRefMsgCond, null, cpoSvcAddlBaseRecMsgCond, null);
//
//        CPO_SVC_ADDL_BASE_RECTMsg cpoSvcAddlBasePrcRecVal = getUpdCond4CpoSvcAddlBaseRec(pMsg, dtlMsg);
//        S21FastTBLAccessor.updateByPartialValue(cpoSvcAddlBaseRecMsgCond, PARTIAL_UPD_DTL_KEY, cpoSvcAddlBasePrcRecVal, PARTIAL_UPD_DTL_VAL);
//
//        // CPO_SVC_ADDL_CHRG_PRC
//        CPO_SVC_ADDL_CHRG_PRCTMsg cpoSvcAddlChrgPrcMsgCond = new CPO_SVC_ADDL_CHRG_PRCTMsg();
//        EZDMsg.copy(cpoSvcConfigRefMsgCond, null, cpoSvcAddlChrgPrcMsgCond, null);
//
//        CPO_SVC_ADDL_CHRG_PRCTMsg cpoSvcAddlChrgPrcMsgVal = getUpdCond4CpoSvcAddlChrgPrc(pMsg, dtlMsg);
//        S21FastTBLAccessor.updateByPartialValue(cpoSvcAddlChrgPrcMsgCond, PARTIAL_UPD_DTL_KEY, cpoSvcAddlChrgPrcMsgVal, PARTIAL_UPD_DTL_VAL);
//
//        // CPO_SVC_ADDL_CHRG_REC
//        CPO_SVC_ADDL_CHRG_RECTMsg cpoSvcAddlChrgRecMsgCond = new CPO_SVC_ADDL_CHRG_RECTMsg();
//        EZDMsg.copy(cpoSvcConfigRefMsgCond, null, cpoSvcAddlChrgRecMsgCond, null);
//
//        CPO_SVC_ADDL_CHRG_RECTMsg cpoSvcAddlChrgPrcRecVal = getUpdCond4CpoSvcAddlChrgRec(pMsg, dtlMsg);
//        S21FastTBLAccessor.updateByPartialValue(cpoSvcAddlChrgRecMsgCond, PARTIAL_UPD_DTL_KEY, cpoSvcAddlChrgPrcRecVal, PARTIAL_UPD_DTL_VAL);
//    }
    // 2017/05/10 S21_NA#Review structure Lv.2 RS#8391 Del Old Logic End

    private boolean removeDetail(NWZC150001PMsg pMsg, NWZC150001_APMsg removeDtlMsg) {

        boolean rslt = false;

        // CPO_DTL
        S21ApiTBLAccessor.remove(getUpdCond4CpoDtl(pMsg, removeDtlMsg));
        // CPO_DTL_REC
        S21FastTBLAccessor.removeByPartialValue(getUpdCond4CpoDtlRec(pMsg, removeDtlMsg), PARTIAL_UPD_DTL_KEY);
        // ORD_PRC_CALC_BASE
        S21FastTBLAccessor.removeByPartialValue(getUpdCond4OrdPrcCalcBase(pMsg, removeDtlMsg), PARTIAL_UPD_DTL_KEY);
        // ORD_PRC_CALC_BASE_REC
        S21FastTBLAccessor.removeByPartialValue(getUpdCond4OrdPrcCalcBaseRec(pMsg, removeDtlMsg), PARTIAL_UPD_DTL_KEY);
        // HLD
        S21FastTBLAccessor.removeByPartialValue(getUpdCond4Hld(pMsg, removeDtlMsg), PARTIAL_UPD_DTL_KEY);

        return rslt;
    }

    private DS_CPO_CONFIGTMsg getUpdCond4DsCpoConfig(NWZC150001PMsg pMsg, NWZC150001_cpoConfigPMsg configMsg) {

        DS_CPO_CONFIGTMsg dsCpoConfigMsgCond = new DS_CPO_CONFIGTMsg();
        ZYPEZDItemValueSetter.setValue(dsCpoConfigMsgCond.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsCpoConfigMsgCond.dsCpoConfigPk, configMsg.dsCpoConfigPk);

        return dsCpoConfigMsgCond;
    }

    private DS_CPO_CONFIG_RECTMsg getUpdCond4DsCpoConfigRec(NWZC150001PMsg pMsg, NWZC150001_cpoConfigPMsg configMsg) {

        DS_CPO_CONFIG_RECTMsg dsCpoConfigRecMsgCond = new DS_CPO_CONFIG_RECTMsg();
        ZYPEZDItemValueSetter.setValue(dsCpoConfigRecMsgCond.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsCpoConfigRecMsgCond.dsCpoConfigPk, configMsg.dsCpoConfigPk);

        return dsCpoConfigRecMsgCond;
    }

    private DS_CPO_SLS_CRTMsg getUpdCond4DsCpoSlsCr(NWZC150001PMsg pMsg, NWZC150001_cpoConfigPMsg configMsg) {

        DS_CPO_SLS_CRTMsg dsCpoSlsCrMsgCond = new DS_CPO_SLS_CRTMsg();
        ZYPEZDItemValueSetter.setValue(dsCpoSlsCrMsgCond.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsCpoSlsCrMsgCond.dsCpoConfigPk, configMsg.dsCpoConfigPk);

        return dsCpoSlsCrMsgCond;
    }

    private DS_CPO_SLS_CR_RECTMsg getUpdCond4DsCpoSlsCrRec(NWZC150001PMsg pMsg, NWZC150001_cpoConfigPMsg configMsg) {

        DS_CPO_SLS_CR_RECTMsg dsCpoSlsCrRecMsgCond = new DS_CPO_SLS_CR_RECTMsg();
        ZYPEZDItemValueSetter.setValue(dsCpoSlsCrRecMsgCond.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsCpoSlsCrRecMsgCond.dsCpoConfigPk, configMsg.dsCpoConfigPk);

        return dsCpoSlsCrRecMsgCond;
    }

    private DS_CPO_DELY_INFOTMsg getUpdCond4DsCpoDelyInfo(NWZC150001PMsg pMsg, NWZC150001_cpoConfigPMsg configMsg) {

        DS_CPO_DELY_INFOTMsg dsCpoDelyInfoMsgCond = new DS_CPO_DELY_INFOTMsg();
        ZYPEZDItemValueSetter.setValue(dsCpoDelyInfoMsgCond.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsCpoDelyInfoMsgCond.dsCpoConfigPk, configMsg.dsCpoConfigPk);

        return dsCpoDelyInfoMsgCond;
    }

    private DS_SITE_SRVYTMsg getUpdCond4DsSiteSrvy(NWZC150001PMsg pMsg, NWZC150001_cpoConfigPMsg configMsg) {

        DS_SITE_SRVYTMsg dsSiteSrvyMsgCond = new DS_SITE_SRVYTMsg();
        ZYPEZDItemValueSetter.setValue(dsSiteSrvyMsgCond.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsSiteSrvyMsgCond.dsCpoConfigPk, configMsg.dsCpoConfigPk);

        return dsSiteSrvyMsgCond;
    }

    private DS_CPO_ISTL_INFOTMsg getUpdCond4DsCpoIstlInfo(NWZC150001PMsg pMsg, NWZC150001_cpoConfigPMsg configMsg) {

        DS_CPO_ISTL_INFOTMsg dsCpoIstlInfoMsgCond = new DS_CPO_ISTL_INFOTMsg();
        ZYPEZDItemValueSetter.setValue(dsCpoIstlInfoMsgCond.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsCpoIstlInfoMsgCond.dsCpoConfigPk, configMsg.dsCpoConfigPk);

        return dsCpoIstlInfoMsgCond;
    }

    private DS_CPO_CTAC_PSNTMsg getUpdCond4DsCpoCtacPsn(NWZC150001PMsg pMsg, NWZC150001_cpoConfigPMsg configMsg) {

        DS_CPO_CTAC_PSNTMsg dsCpoCtacPsnMsgCond = new DS_CPO_CTAC_PSNTMsg();
        ZYPEZDItemValueSetter.setValue(dsCpoCtacPsnMsgCond.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsCpoCtacPsnMsgCond.dsCpoConfigPk, configMsg.dsCpoConfigPk);

        return dsCpoCtacPsnMsgCond;
    }

    private CPO_DTLTMsg getUpdCond4CpoDtl(NWZC150001PMsg pMsg, NWZC150001_APMsg dtlMsg) {

        CPO_DTLTMsg cpoDtlMsgCond = new CPO_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(cpoDtlMsgCond.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cpoDtlMsgCond.cpoOrdNum, pMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(cpoDtlMsgCond.cpoDtlLineNum, dtlMsg.cpoDtlLineNum_A1);
        ZYPEZDItemValueSetter.setValue(cpoDtlMsgCond.cpoDtlLineSubNum, dtlMsg.cpoDtlLineSubNum_A1);

        return cpoDtlMsgCond;
    }

    private CPO_DTL_RECTMsg getUpdCond4CpoDtlRec(NWZC150001PMsg pMsg, NWZC150001_APMsg dtlMsg) {

        CPO_DTL_RECTMsg cpoDtlRecCond = new CPO_DTL_RECTMsg();
        ZYPEZDItemValueSetter.setValue(cpoDtlRecCond.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cpoDtlRecCond.cpoOrdNum, pMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(cpoDtlRecCond.cpoDtlLineNum, dtlMsg.cpoDtlLineNum_A1);
        ZYPEZDItemValueSetter.setValue(cpoDtlRecCond.cpoDtlLineSubNum, dtlMsg.cpoDtlLineSubNum_A1);

        return cpoDtlRecCond;
    }

    private ORD_PRC_CALC_BASETMsg getUpdCond4OrdPrcCalcBase(NWZC150001PMsg pMsg, NWZC150001_APMsg dtlMsg) {

        ORD_PRC_CALC_BASETMsg calcBaseCond = new ORD_PRC_CALC_BASETMsg();
        ZYPEZDItemValueSetter.setValue(calcBaseCond.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(calcBaseCond.cpoOrdNum, pMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(calcBaseCond.cpoDtlLineNum, dtlMsg.cpoDtlLineNum_A1);
        ZYPEZDItemValueSetter.setValue(calcBaseCond.cpoDtlLineSubNum, dtlMsg.cpoDtlLineSubNum_A1);

        return calcBaseCond;
    }

    private ORD_PRC_CALC_BASE_RECTMsg getUpdCond4OrdPrcCalcBaseRec(NWZC150001PMsg pMsg, NWZC150001_APMsg dtlMsg) {

        ORD_PRC_CALC_BASE_RECTMsg calcBaseRecCond = new ORD_PRC_CALC_BASE_RECTMsg();
        ZYPEZDItemValueSetter.setValue(calcBaseRecCond.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(calcBaseRecCond.cpoOrdNum, pMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(calcBaseRecCond.cpoDtlLineNum, dtlMsg.cpoDtlLineNum_A1);
        ZYPEZDItemValueSetter.setValue(calcBaseRecCond.cpoDtlLineSubNum, dtlMsg.cpoDtlLineSubNum_A1);

        return calcBaseRecCond;
    }

    private HLDTMsg getUpdCond4Hld(NWZC150001PMsg pMsg, NWZC150001_APMsg dtlMsg) {

        HLDTMsg hldMsgCond = new HLDTMsg();
        ZYPEZDItemValueSetter.setValue(hldMsgCond.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(hldMsgCond.cpoOrdNum, pMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(hldMsgCond.cpoDtlLineNum, dtlMsg.cpoDtlLineNum_A1);
        ZYPEZDItemValueSetter.setValue(hldMsgCond.cpoDtlLineSubNum, dtlMsg.cpoDtlLineSubNum_A1);

        return hldMsgCond;
    }

    // 2017/05/10 S21_NA#Review structure Lv.2 RS#8391 Del Old Logic Start
//    private CPO_SVC_CONFIG_REFTMsg getUpdCond4CpoSvcConfigRef(NWZC150001PMsg pMsg, NWZC150001_APMsg dtlMsg) {
//
//        CPO_SVC_CONFIG_REFTMsg cpoSvcConfigRefCond = new CPO_SVC_CONFIG_REFTMsg();
//        ZYPEZDItemValueSetter.setValue(cpoSvcConfigRefCond.glblCmpyCd, pMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(cpoSvcConfigRefCond.cpoOrdNum, pMsg.cpoOrdNum);
//        ZYPEZDItemValueSetter.setValue(cpoSvcConfigRefCond.cpoDtlLineNum, dtlMsg.cpoDtlLineNum_A1);
//        ZYPEZDItemValueSetter.setValue(cpoSvcConfigRefCond.cpoDtlLineSubNum, dtlMsg.cpoDtlLineSubNum_A1);
//
//        return cpoSvcConfigRefCond;
//    }
//
//    private CPO_SVC_CONFIG_REF_RECTMsg getUpdCond4CpoSvcConfigRefRec(NWZC150001PMsg pMsg, NWZC150001_APMsg dtlMsg) {
//
//        CPO_SVC_CONFIG_REF_RECTMsg cpoSvcConfigRefRecCond = new CPO_SVC_CONFIG_REF_RECTMsg();
//        ZYPEZDItemValueSetter.setValue(cpoSvcConfigRefRecCond.glblCmpyCd, pMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(cpoSvcConfigRefRecCond.cpoOrdNum, pMsg.cpoOrdNum);
//        ZYPEZDItemValueSetter.setValue(cpoSvcConfigRefRecCond.cpoDtlLineNum, dtlMsg.cpoDtlLineNum_A1);
//        ZYPEZDItemValueSetter.setValue(cpoSvcConfigRefRecCond.cpoDtlLineSubNum, dtlMsg.cpoDtlLineSubNum_A1);
//
//        return cpoSvcConfigRefRecCond;
//    }
//
//    private CPO_SVC_ADDL_BASE_PRCTMsg getUpdCond4CpoSvcAddlBasePrc(NWZC150001PMsg pMsg, NWZC150001_APMsg dtlMsg) {
//
//        CPO_SVC_ADDL_BASE_PRCTMsg cpoSvcAddlBasePrcCond = new CPO_SVC_ADDL_BASE_PRCTMsg();
//        ZYPEZDItemValueSetter.setValue(cpoSvcAddlBasePrcCond.glblCmpyCd, pMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(cpoSvcAddlBasePrcCond.cpoOrdNum, pMsg.cpoOrdNum);
//        ZYPEZDItemValueSetter.setValue(cpoSvcAddlBasePrcCond.cpoDtlLineNum, dtlMsg.cpoDtlLineNum_A1);
//        ZYPEZDItemValueSetter.setValue(cpoSvcAddlBasePrcCond.cpoDtlLineSubNum, dtlMsg.cpoDtlLineSubNum_A1);
//
//        return cpoSvcAddlBasePrcCond;
//    }
//
//    private CPO_SVC_ADDL_BASE_RECTMsg getUpdCond4CpoSvcAddlBaseRec(NWZC150001PMsg pMsg, NWZC150001_APMsg dtlMsg) {
//
//        CPO_SVC_ADDL_BASE_RECTMsg cpoSvcAddlBaseRecCond = new CPO_SVC_ADDL_BASE_RECTMsg();
//        ZYPEZDItemValueSetter.setValue(cpoSvcAddlBaseRecCond.glblCmpyCd, pMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(cpoSvcAddlBaseRecCond.cpoOrdNum, pMsg.cpoOrdNum);
//        ZYPEZDItemValueSetter.setValue(cpoSvcAddlBaseRecCond.cpoDtlLineNum, dtlMsg.cpoDtlLineNum_A1);
//        ZYPEZDItemValueSetter.setValue(cpoSvcAddlBaseRecCond.cpoDtlLineSubNum, dtlMsg.cpoDtlLineSubNum_A1);
//
//        return cpoSvcAddlBaseRecCond;
//    }
//
//    private CPO_SVC_ADDL_CHRG_PRCTMsg getUpdCond4CpoSvcAddlChrgPrc(NWZC150001PMsg pMsg, NWZC150001_APMsg dtlMsg) {
//
//        CPO_SVC_ADDL_CHRG_PRCTMsg cpoSvcAddlChrgPrcCond = new CPO_SVC_ADDL_CHRG_PRCTMsg();
//        ZYPEZDItemValueSetter.setValue(cpoSvcAddlChrgPrcCond.glblCmpyCd, pMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(cpoSvcAddlChrgPrcCond.cpoOrdNum, pMsg.cpoOrdNum);
//        ZYPEZDItemValueSetter.setValue(cpoSvcAddlChrgPrcCond.cpoDtlLineNum, dtlMsg.cpoDtlLineNum_A1);
//        ZYPEZDItemValueSetter.setValue(cpoSvcAddlChrgPrcCond.cpoDtlLineSubNum, dtlMsg.cpoDtlLineSubNum_A1);
//
//        return cpoSvcAddlChrgPrcCond;
//    }
//
//    private CPO_SVC_ADDL_CHRG_RECTMsg getUpdCond4CpoSvcAddlChrgRec(NWZC150001PMsg pMsg, NWZC150001_APMsg dtlMsg) {
//
//        CPO_SVC_ADDL_CHRG_RECTMsg cpoSvcAddlChrgRecCond = new CPO_SVC_ADDL_CHRG_RECTMsg();
//        ZYPEZDItemValueSetter.setValue(cpoSvcAddlChrgRecCond.glblCmpyCd, pMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(cpoSvcAddlChrgRecCond.cpoOrdNum, pMsg.cpoOrdNum);
//        ZYPEZDItemValueSetter.setValue(cpoSvcAddlChrgRecCond.cpoDtlLineNum, dtlMsg.cpoDtlLineNum_A1);
//        ZYPEZDItemValueSetter.setValue(cpoSvcAddlChrgRecCond.cpoDtlLineSubNum, dtlMsg.cpoDtlLineSubNum_A1);
//
//        return cpoSvcAddlChrgRecCond;
//    }
    // 2017/05/10 S21_NA#Review structure Lv.2 RS#8391 Del Old Logic End

    private DS_CPO_RTRN_DTLTMsg getUpdCond4DsCpoRtrnDtl(NWZC150001PMsg pMsg, NWZC150001_rtnDtlPMsg rtnDtlMsg) {

        DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlCond = new DS_CPO_RTRN_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlCond.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlCond.cpoOrdNum, pMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlCond.dsCpoRtrnLineNum, rtnDtlMsg.cpoDtlLineNum_B1);
        ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlCond.dsCpoRtrnLineSubNum, rtnDtlMsg.cpoDtlLineSubNum_B1);

        return dsCpoRtrnDtlCond;
    }

    private DS_CPO_RTRN_DTL_RECTMsg getUpdCond4DsCpoRtrnDtlRec(NWZC150001PMsg pMsg, NWZC150001_rtnDtlPMsg rtnDtlMsg) {

        DS_CPO_RTRN_DTL_RECTMsg dsCpoRtrnDtlRecCond = new DS_CPO_RTRN_DTL_RECTMsg();
        ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlRecCond.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlRecCond.cpoOrdNum, pMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlRecCond.dsCpoRtrnLineNum, rtnDtlMsg.cpoDtlLineNum_B1);
        ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlRecCond.dsCpoRtrnLineSubNum, rtnDtlMsg.cpoDtlLineSubNum_B1);

        return dsCpoRtrnDtlRecCond;
    }

    private CPO_RTRN_PRC_CALC_BASETMsg getUpdCond4CpoRtrnPrcCalcBase(NWZC150001PMsg pMsg, NWZC150001_rtnDtlPMsg rtnDtlMsg) {

        CPO_RTRN_PRC_CALC_BASETMsg cpoRtrnPrcCalcBaseCond = new CPO_RTRN_PRC_CALC_BASETMsg();
        ZYPEZDItemValueSetter.setValue(cpoRtrnPrcCalcBaseCond.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cpoRtrnPrcCalcBaseCond.cpoOrdNum, pMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(cpoRtrnPrcCalcBaseCond.dsCpoRtrnLineNum, rtnDtlMsg.cpoDtlLineNum_B1);
        ZYPEZDItemValueSetter.setValue(cpoRtrnPrcCalcBaseCond.dsCpoRtrnLineSubNum, rtnDtlMsg.cpoDtlLineSubNum_B1);

        return cpoRtrnPrcCalcBaseCond;
    }

    private CPO_RTRN_CALC_BASE_RECTMsg getUpdCond4CpoRtrnCalcBaseRec(NWZC150001PMsg pMsg, NWZC150001_rtnDtlPMsg rtnDtlMsg) {

        CPO_RTRN_CALC_BASE_RECTMsg cpoRtrnCalcBaseRecCond = new CPO_RTRN_CALC_BASE_RECTMsg();
        ZYPEZDItemValueSetter.setValue(cpoRtrnCalcBaseRecCond.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(cpoRtrnCalcBaseRecCond.cpoOrdNum, pMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(cpoRtrnCalcBaseRecCond.dsCpoRtrnLineNum, rtnDtlMsg.cpoDtlLineNum_B1);
        ZYPEZDItemValueSetter.setValue(cpoRtrnCalcBaseRecCond.dsCpoRtrnLineSubNum, rtnDtlMsg.cpoDtlLineSubNum_B1);

        return cpoRtrnCalcBaseRecCond;
    }

    // 2017/05/10 S21_NA#Review structure Lv.2 RS#8391 Del Old Logic Start
//    private void removeCpoSvc(NWZC150001PMsg pMsg, NWZC150001DsCpoDtlForDeleteBean dtlBean) {
//
//        String cpoOrdNum = pMsg.cpoOrdNum.getValue();
//
//        Map<String, String> ssmParam = new HashMap<String, String>();
//        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
//        ssmParam.put("cpoOrdNum", cpoOrdNum);
//
//        List<CPO_SVC_DTLTMsg> cpoSvcDtlMsgList = getCpoSvcDtlList(pMsg);
//        if (cpoSvcDtlMsgList == null || cpoSvcDtlMsgList.isEmpty()) {
//            return;
//        }
//
//        for (CPO_SVC_DTLTMsg cpoSvcDtlMsg : cpoSvcDtlMsgList) {
//            // CPO_SVC_CONFIG_REF
//            // CPO_SVC_CONFIG_REF_REC
//            // CPO_SVC_PRC
//            // CPO_SVC_PRC_REC
//            // CPO_SVC_USG_PRC
//            removeCpoSvcConfigRef(pMsg, dtlBean, cpoSvcDtlMsg);
//            // CPO_SVC_ADDL_BASE_PRC
//            // CPO_SVC_ADDL_BASE_REC
//            removeCpoSvcAddlBase(pMsg, dtlBean, cpoSvcDtlMsg);
//            // CPO_SVC_ADDL_CHRG_PRC
//            // CPO_SVC_ADDL_CHRG_REC
//            removeCpoSvcAddlChrg(pMsg, dtlBean, cpoSvcDtlMsg);
//            // CPO_SVC_DTL
//            removeCpoSvcDtl(pMsg, cpoSvcDtlMsg);
//        }
//    }
//
//    private void removeCpoSvcDtl(NWZC150001PMsg pMsg, CPO_SVC_DTLTMsg cpoSvcDtlMsg) {
//
//        // Data Check
//        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
//        BigDecimal cpoSvcDtlPk = cpoSvcDtlMsg.cpoSvcDtlPk.getValue();
//
//        BigDecimal cpoSvcConfigRefCount = getCpoSvcConfigRefCount(glblCmpyCd, cpoSvcDtlPk);
//        BigDecimal cpoSvcPrcCount = getCpoSvcPrcCount(glblCmpyCd, cpoSvcDtlPk);
//        BigDecimal cpoSvcUsgPrcCount = getCpoSvcUsgPrcCount(glblCmpyCd, cpoSvcDtlPk);
//        BigDecimal cpoSvcAddlBasePrcCount = getCpoSvcAddlBasePrcCount(glblCmpyCd, cpoSvcDtlPk);
//        BigDecimal cpoSvcAddlChrgPrcCount = getCpoSvcAddlChrgPrcCount(glblCmpyCd, cpoSvcDtlPk);
//
//        BigDecimal countRec = cpoSvcConfigRefCount.add(cpoSvcPrcCount);
//        countRec = countRec.add(cpoSvcUsgPrcCount);
//        countRec = countRec.add(cpoSvcAddlBasePrcCount);
//        countRec = countRec.add(cpoSvcAddlChrgPrcCount);
//
//        if (BigDecimal.ZERO.compareTo(countRec) == 0) {
//            S21ApiTBLAccessor.remove(cpoSvcDtlMsg);
//
//            CPO_SVC_DTL_RECTMsg cpoSvcDtlRecCond = new CPO_SVC_DTL_RECTMsg();
//            cpoSvcDtlRecCond.glblCmpyCd.setValue(cpoSvcDtlMsg.glblCmpyCd.getValue());
//            cpoSvcDtlRecCond.cpoSvcDtlPk.setValue(cpoSvcDtlMsg.cpoSvcDtlPk.getValue());
//            S21FastTBLAccessor.removeByPartialValue(cpoSvcDtlRecCond, new String[]{"glblCmpyCd", "cpoSvcDtlPk"});
//        }
////        getCpoSvcConfigRefList(pMsg, cpoSvcDtlMsg);
//    }
//
//    private void removeCpoSvcConfigRef(NWZC150001PMsg pMsg, NWZC150001DsCpoDtlForDeleteBean dtlBean, CPO_SVC_DTLTMsg cpoSvcDtlMsg) {
//
//        String cpoOrdNum = pMsg.cpoOrdNum.getValue();
//        String cpoDtlLineNum = dtlBean.getCurCpoDtlLineNum();
//        String cpoDtlLineSubNum = dtlBean.getCurCpoDtlLineSubNum();
//
//        CPO_SVC_CONFIG_REFTMsg cpoSvcConfigMsgCond = new CPO_SVC_CONFIG_REFTMsg();
//        cpoSvcConfigMsgCond.setSQLID("001");
//        cpoSvcConfigMsgCond.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
//        cpoSvcConfigMsgCond.setConditionValue("cpoOrdNum01", cpoOrdNum);
//        cpoSvcConfigMsgCond.setConditionValue("cpoDtlLineNum01", cpoDtlLineNum);
//        cpoSvcConfigMsgCond.setConditionValue("cpoDtlLineSubNum01", cpoDtlLineSubNum);
//        cpoSvcConfigMsgCond.setConditionValue("cpoSvcDtlPk01", cpoSvcDtlMsg.cpoSvcDtlPk.getValue());
//
//        CPO_SVC_CONFIG_REFTMsgArray cpoSvcConfigMsgArray = (CPO_SVC_CONFIG_REFTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(cpoSvcConfigMsgCond);
//
//        if (cpoSvcConfigMsgArray == null || cpoSvcConfigMsgArray.getValidCount() == 0) {
//            return;
//        }
//
//        for (int i = 0; i < cpoSvcConfigMsgArray.getValidCount(); i++) {
//            CPO_SVC_CONFIG_REFTMsg cpoSvcConfigMsg = cpoSvcConfigMsgArray.no(i);
//
//            // CPO_SVC_PRC
//            CPO_SVC_PRCTMsg cpoSvcPrcTMsg = new CPO_SVC_PRCTMsg();
//            ZYPEZDItemValueSetter.setValue(cpoSvcPrcTMsg.glblCmpyCd, cpoSvcConfigMsg.glblCmpyCd);
//            ZYPEZDItemValueSetter.setValue(cpoSvcPrcTMsg.cpoSvcPrcPk, cpoSvcConfigMsg.cpoSvcPrcPk);
//            ZYPEZDItemValueSetter.setValue(cpoSvcPrcTMsg.cpoSvcDtlPk, cpoSvcConfigMsg.cpoSvcDtlPk);
//            S21FastTBLAccessor.removeByPartialValue(cpoSvcPrcTMsg, new String[] {"glblCmpyCd", "cpoSvcPrcPk", "cpoSvcDtlPk"});
//
//            // CPO_SVC_PRC_REC
//            CPO_SVC_PRC_RECTMsg cpoSvcPrcRecTMsg = new CPO_SVC_PRC_RECTMsg();
//            ZYPEZDItemValueSetter.setValue(cpoSvcPrcRecTMsg.glblCmpyCd, cpoSvcConfigMsg.glblCmpyCd);
//            ZYPEZDItemValueSetter.setValue(cpoSvcPrcRecTMsg.cpoSvcPrcPk, cpoSvcConfigMsg.cpoSvcPrcPk);
//            ZYPEZDItemValueSetter.setValue(cpoSvcPrcRecTMsg.cpoSvcDtlPk, cpoSvcConfigMsg.cpoSvcDtlPk);
//            S21FastTBLAccessor.removeByPartialValue(cpoSvcPrcRecTMsg, new String[] {"glblCmpyCd", "cpoSvcPrcPk", "cpoSvcDtlPk"});
//
//            // CPO_SVC_USG_PRC
//            CPO_SVC_USG_PRCTMsg cpoSvcUsgPrcTMsg = new CPO_SVC_USG_PRCTMsg();
//            ZYPEZDItemValueSetter.setValue(cpoSvcUsgPrcTMsg.glblCmpyCd, cpoSvcConfigMsg.glblCmpyCd);
//            ZYPEZDItemValueSetter.setValue(cpoSvcUsgPrcTMsg.cpoSvcPrcPk, cpoSvcConfigMsg.cpoSvcPrcPk);
//            ZYPEZDItemValueSetter.setValue(cpoSvcUsgPrcTMsg.cpoSvcDtlPk, cpoSvcConfigMsg.cpoSvcDtlPk);
//            S21FastTBLAccessor.removeByPartialValue(cpoSvcUsgPrcTMsg, new String[] {"glblCmpyCd", "cpoSvcPrcPk", "cpoSvcDtlPk"});
//        }
//        // CPO_SVC_CONFIG_REF
//        CPO_SVC_CONFIG_REFTMsg removeCpoSvcConfigRefCond = getUpdCond4CpoSvcConfigRef(pMsg, dtlBean.getDtlPMsg());
//        S21FastTBLAccessor.removeByPartialValue(removeCpoSvcConfigRefCond, PARTIAL_UPD_DTL_KEY);
//
//        // CPO_SVC_CONFIG_REF_REC
//        CPO_SVC_CONFIG_REF_RECTMsg removeCpoSvcConfigRefRecCond = getUpdCond4CpoSvcConfigRefRec(pMsg, dtlBean.getDtlPMsg());
//        S21FastTBLAccessor.removeByPartialValue(removeCpoSvcConfigRefRecCond, PARTIAL_UPD_DTL_KEY);
//    }
//
//    private void removeCpoSvcAddlBase(NWZC150001PMsg pMsg, NWZC150001DsCpoDtlForDeleteBean dtlBean, CPO_SVC_DTLTMsg cpoSvcDtlMsg) {
//
//        // CPO_SVC_ADDL_BASE_PRC
//        CPO_SVC_ADDL_BASE_PRCTMsg cpoSvcAddlBasePrcMsg = getUpdCond4CpoSvcAddlBasePrc(pMsg, dtlBean.getDtlPMsg());
//        S21FastTBLAccessor.removeByPartialValue(cpoSvcAddlBasePrcMsg, PARTIAL_UPD_DTL_KEY);
//
//        // CPO_SVC_ADDL_BASE_REC
//        CPO_SVC_ADDL_BASE_RECTMsg cpoSvcAddlBaseRecMsg = getUpdCond4CpoSvcAddlBaseRec(pMsg, dtlBean.getDtlPMsg());
//        S21FastTBLAccessor.removeByPartialValue(cpoSvcAddlBaseRecMsg, PARTIAL_UPD_DTL_KEY);
//    }
//
//    private void removeCpoSvcAddlChrg(NWZC150001PMsg pMsg, NWZC150001DsCpoDtlForDeleteBean dtlBean, CPO_SVC_DTLTMsg cpoSvcDtlMsg) {
//
//        // CPO_SVC_ADDL_CHRG_PRC
//        CPO_SVC_ADDL_CHRG_PRCTMsg cpoSvcAddlChrgPrcMsg = getUpdCond4CpoSvcAddlChrgPrc(pMsg, dtlBean.getDtlPMsg());
//        S21FastTBLAccessor.removeByPartialValue(cpoSvcAddlChrgPrcMsg, PARTIAL_UPD_DTL_KEY);
//
//        // CPO_SVC_ADDL_CHRG_REC
//        CPO_SVC_ADDL_CHRG_RECTMsg cpoSvcAddlChrgRecMsg = getUpdCond4CpoSvcAddlChrgRec(pMsg, dtlBean.getDtlPMsg());
//        S21FastTBLAccessor.removeByPartialValue(cpoSvcAddlChrgRecMsg, PARTIAL_UPD_DTL_KEY);
//    }
//
//    private boolean isEqualLine(NWZC150001DsCpoDtlForDeleteBean dtlBean) {
//
//        NWZC150001_APMsg dtlMsg = dtlBean.getDtlPMsg();
//        boolean isEqualLine = S21StringUtil.isEquals(dtlBean.getCurCpoDtlLineNum(), dtlMsg.cpoDtlLineNum_A1.getValue());
//        isEqualLine = isEqualLine & S21StringUtil.isEquals(dtlBean.getCurCpoDtlLineSubNum(), dtlMsg.cpoDtlLineSubNum_A1.getValue());
//
//        return isEqualLine;
//    }
    // 2017/05/10 S21_NA#Review structure Lv.2 RS#8391 Del Old Logic End

    private void deleteAndUpdateRtnDtl(NWZC150001PMsg pMsg, List<NWZC150001DsCpoRtrnDtlForDeleteBean> rtnDtlList) {

        for (NWZC150001DsCpoRtrnDtlForDeleteBean rtnDtlBean : rtnDtlList) {
            NWZC150001_rtnDtlPMsg rtnDtlMsg = rtnDtlBean.getRtnDtlPMsg();
            if (S21StringUtil.isEquals(RQST_TP_RTRN_DTL_DELETE, rtnDtlMsg.xxRqstTpCd_B1.getValue())) {
                removeRtrnDtl(pMsg, rtnDtlBean);
            } else {
                updateRtrnDtl(pMsg, rtnDtlBean);
            }
        }
    }

    private void removeRtrnDtl(NWZC150001PMsg pMsg, NWZC150001DsCpoRtrnDtlForDeleteBean rtnDtlBean) {

        NWZC150001_rtnDtlPMsg rtnDtlMsg = rtnDtlBean.getRtnDtlPMsg();
        // DS_CPO_RTRN_DTL
        DS_CPO_RTRN_DTLTMsg dsCpoRtrnDtlMsg = getUpdCond4DsCpoRtrnDtl(pMsg, rtnDtlMsg);
        S21ApiTBLAccessor.remove(dsCpoRtrnDtlMsg);

        // DS_CPO_RTRN_DTL_REC
        DS_CPO_RTRN_DTL_RECTMsg dsCpoRtrnDtlRecMsg = getUpdCond4DsCpoRtrnDtlRec(pMsg, rtnDtlMsg);
        S21FastTBLAccessor.removeByPartialValue(dsCpoRtrnDtlRecMsg, PARTIAL_UPD_RTN_DTL_KEY);

        // CPO_RTRN_PRC_CALC_BASE
        CPO_RTRN_PRC_CALC_BASETMsg cpoRtrnPrcCalcBaseMsg = getUpdCond4CpoRtrnPrcCalcBase(pMsg, rtnDtlMsg);
        S21FastTBLAccessor.removeByPartialValue(cpoRtrnPrcCalcBaseMsg, PARTIAL_UPD_RTN_DTL_KEY);

        // CPO_RTRN_PRC_CALC_BASE
        CPO_RTRN_CALC_BASE_RECTMsg cpoRtrnCalcBaseRecMsg = getUpdCond4CpoRtrnCalcBaseRec(pMsg, rtnDtlMsg);
        S21FastTBLAccessor.removeByPartialValue(cpoRtrnCalcBaseRecMsg, PARTIAL_UPD_RTN_DTL_KEY);
    }

    private void updateRtrnDtl(NWZC150001PMsg pMsg, NWZC150001DsCpoRtrnDtlForDeleteBean rtnDtlBean) {

        // 2018/06/05 S21_NA#25151 Add Start
        if (!rtnDtlBean.isModified()) {
            return;
        }
        // 2018/06/05 S21_NA#25151 Add End
        NWZC150001_rtnDtlPMsg rtnDtlMsg = rtnDtlBean.getRtnDtlPMsg();
        // DS_CPO_RTRN_DTL
        DS_CPO_RTRN_DTLTMsg dbDsCpoRtrnDtlMsg = new DS_CPO_RTRN_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(dbDsCpoRtrnDtlMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dbDsCpoRtrnDtlMsg.cpoOrdNum, pMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(dbDsCpoRtrnDtlMsg.dsCpoRtrnLineNum, rtnDtlBean.getCurCpoDtlLineNum());
        ZYPEZDItemValueSetter.setValue(dbDsCpoRtrnDtlMsg.dsCpoRtrnLineSubNum, rtnDtlBean.getCurCpoDtlLineSubNum());
        dbDsCpoRtrnDtlMsg = (DS_CPO_RTRN_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdate(dbDsCpoRtrnDtlMsg);

        if (dbDsCpoRtrnDtlMsg != null) {
            // 2017/05/10 S21_NA#Review structure Lv.2 RS#8390 Del Start
//            DS_CPO_RTRN_DTLTMsg newDsCpoRtrnDtlMsg = new DS_CPO_RTRN_DTLTMsg();
//            EZDMsg.copy(dbDsCpoRtrnDtlMsg, null, newDsCpoRtrnDtlMsg, null);
//            S21ApiTBLAccessor.remove(dbDsCpoRtrnDtlMsg);
            // 2017/05/10 S21_NA#Review structure Lv.2 RS#8390 Del End

            // 2017/05/10 S21_NA#Review structure Lv.2 RS#8390 Mod Start
//            ZYPEZDItemValueSetter.setValue(newDsCpoRtrnDtlMsg.dsCpoRtrnLineNum, rtnDtlMsg.cpoDtlLineNum_B1);
//            ZYPEZDItemValueSetter.setValue(newDsCpoRtrnDtlMsg.dsCpoRtrnLineSubNum, rtnDtlMsg.cpoDtlLineSubNum_B1);
//            ZYPEZDItemValueSetter.setValue(newDsCpoRtrnDtlMsg.dsOrdPosnNum, rtnDtlMsg.dsOrdPosnNum_B1);
//            ZYPEZDItemValueSetter.setValue(newDsCpoRtrnDtlMsg.dsCpoLineNum, rtnDtlMsg.dsCpoLineNum_B1);
//            ZYPEZDItemValueSetter.setValue(newDsCpoRtrnDtlMsg.dsCpoLineSubNum, rtnDtlMsg.dsCpoLineSubNum_B1);
//            ZYPEZDItemValueSetter.setValue(newDsCpoRtrnDtlMsg.baseCmptFlg, rtnDtlMsg.baseCmptFlg_B1); // 2016/08/04 S21_NA#7821-2 Add
//            ZYPEZDItemValueSetter.setValue(newDsCpoRtrnDtlMsg.dplyLineRefNum, rtnDtlMsg.dplyLineRefNum_B1);
//            ZYPEZDItemValueSetter.setValue(newDsCpoRtrnDtlMsg.refCpoDtlLineNum, rtnDtlMsg.refCpoDtlLineNum_B1); // 2016/08/22 S21_NA#7821-3 Add
//            ZYPEZDItemValueSetter.setValue(newDsCpoRtrnDtlMsg.refCpoDtlLineSubNum, rtnDtlMsg.refCpoDtlLineSubNum_B1); // 2016/08/22 S21_NA#7821-3 Add
//
//            S21FastTBLAccessor.insert(newDsCpoRtrnDtlMsg);
            ZYPEZDItemValueSetter.setValue(dbDsCpoRtrnDtlMsg.dsOrdPosnNum, rtnDtlMsg.dsOrdPosnNum_B1);
            ZYPEZDItemValueSetter.setValue(dbDsCpoRtrnDtlMsg.dsCpoLineNum, rtnDtlMsg.dsCpoLineNum_B1);
            ZYPEZDItemValueSetter.setValue(dbDsCpoRtrnDtlMsg.dsCpoLineSubNum, rtnDtlMsg.dsCpoLineSubNum_B1);
            ZYPEZDItemValueSetter.setValue(dbDsCpoRtrnDtlMsg.baseCmptFlg, rtnDtlMsg.baseCmptFlg_B1);
            ZYPEZDItemValueSetter.setValue(dbDsCpoRtrnDtlMsg.dplyLineRefNum, rtnDtlMsg.dplyLineRefNum_B1);
            ZYPEZDItemValueSetter.setValue(dbDsCpoRtrnDtlMsg.refCpoDtlLineNum, rtnDtlMsg.refCpoDtlLineNum_B1);
            ZYPEZDItemValueSetter.setValue(dbDsCpoRtrnDtlMsg.refCpoDtlLineSubNum, rtnDtlMsg.refCpoDtlLineSubNum_B1);

            String[] valKey = new String[7];
            valKey[0] = "dsOrdPosnNum";
            valKey[1] = "dsCpoLineNum";
            valKey[2] = "dsCpoLineSubNum";
            valKey[3] = "baseCmptFlg";
            valKey[4] = "dplyLineRefNum";
            valKey[5] = "refCpoDtlLineNum";
            valKey[6] = "refCpoDtlLineSubNum";

            S21ApiTBLAccessor.updateSelectionField(dbDsCpoRtrnDtlMsg, valKey);
            // 2017/05/10 S21_NA#Review structure Lv.2 RS#8390 Mod End
        } else { // 2016/09/26 S21_NA#10274 Add Start
            return;
        } // 2016/09/26 S21_NA#10274 Add End
        // DS_CPO_RTRN_DTL_REC
        DS_CPO_RTRN_DTL_RECTMsg dsCpoRtrnDtlRecCond = new DS_CPO_RTRN_DTL_RECTMsg();
        EZDMsg.copy(dbDsCpoRtrnDtlMsg, null, dsCpoRtrnDtlRecCond, null);

        DS_CPO_RTRN_DTL_RECTMsg dsCpoRtrnDtlRecMsgVal = getUpdCond4DsCpoRtrnDtlRec(pMsg, rtnDtlMsg);
        ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlRecMsgVal.dsOrdPosnNum, rtnDtlMsg.dsOrdPosnNum_B1);
        ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlRecMsgVal.dsCpoLineNum, rtnDtlMsg.dsCpoLineNum_B1);
        ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlRecMsgVal.dsCpoLineSubNum, rtnDtlMsg.dsCpoLineSubNum_B1);
        ZYPEZDItemValueSetter.setValue(dsCpoRtrnDtlRecMsgVal.dplyLineRefNum, rtnDtlMsg.dplyLineRefNum_B1);

        // 2017/05/10 S21_NA#Review structure Lv.2 RS#8390 Mod Start
//        String[] valKey = new String[6];
//        valKey[0] = PARTIAL_UPD_RTN_DTL_VAL[0];
//        valKey[1] = PARTIAL_UPD_RTN_DTL_VAL[1];
//        valKey[2] = "dsOrdPosnNum";
//        valKey[3] = "dsCpoLineNum";
//        valKey[4] = "dsCpoLineSubNum";
//        valKey[5] = "dplyLineRefNum";
        String[] valKey = new String[4];
        valKey[0] = "dsOrdPosnNum";
        valKey[1] = "dsCpoLineNum";
        valKey[2] = "dsCpoLineSubNum";
        valKey[3] = "dplyLineRefNum";
        // 2017/05/10 S21_NA#Review structure Lv.2 RS#8390 Mod End
        S21FastTBLAccessor.updateByPartialValue(dsCpoRtrnDtlRecCond, PARTIAL_UPD_RTN_DTL_KEY, dsCpoRtrnDtlRecMsgVal, valKey);

        // 2017/05/10 S21_NA#Review structure Lv.2 RS#8390 Del Start
//        // CPO_RTRN_PRC_CALC_BASE
//        CPO_RTRN_PRC_CALC_BASETMsg cpoRtrnPrcCalcBaseMsgCond = new CPO_RTRN_PRC_CALC_BASETMsg();
//        EZDMsg.copy(dbDsCpoRtrnDtlMsg, null, cpoRtrnPrcCalcBaseMsgCond, null);
//
//        CPO_RTRN_PRC_CALC_BASETMsg cpoRtrnPrcCalcBaseMsgVal = getUpdCond4CpoRtrnPrcCalcBase(pMsg, rtnDtlMsg);
//        S21FastTBLAccessor.updateByPartialValue(cpoRtrnPrcCalcBaseMsgCond, PARTIAL_UPD_RTN_DTL_KEY, cpoRtrnPrcCalcBaseMsgVal, PARTIAL_UPD_RTN_DTL_VAL);
//
//        // CPO_RTRN_PRC_CALC_BASE
//        CPO_RTRN_CALC_BASE_RECTMsg cpoRtrnCalcBaseRecCond = new CPO_RTRN_CALC_BASE_RECTMsg();
//        EZDMsg.copy(dbDsCpoRtrnDtlMsg, null, cpoRtrnCalcBaseRecCond, null);
//
//        CPO_RTRN_CALC_BASE_RECTMsg cpoRtrnCalcBaseRecVal = getUpdCond4CpoRtrnCalcBaseRec(pMsg, rtnDtlMsg);
//        S21FastTBLAccessor.updateByPartialValue(cpoRtrnCalcBaseRecCond, PARTIAL_UPD_RTN_DTL_KEY, cpoRtrnCalcBaseRecVal, PARTIAL_UPD_RTN_DTL_VAL);
        // 2017/05/10 S21_NA#Review structure Lv.2 RS#8390 Del End
    }

    private void updateCpoSvc(NWZC150001PMsg pMsg) {

        // 2017/05/10 S21_NA#Review structure Lv.2 RS#8391 Add Old Logic End
        // TODO Call New API For Delete!!!!!!
        // 2017/05/10 S21_NA#Review structure Lv.2 RS#8391 Add Old Logic End

        // 2017/05/10 S21_NA#Review structure Lv.2 RS#8391 Del Old Logic Start
//        // Get Header
//        List<CPO_SVCTMsg> cpoSvcMsgList = getCpoSvcList(pMsg);
//        if (cpoSvcMsgList == null || cpoSvcMsgList.isEmpty()) {
//            return;
//        }
//        for (CPO_SVCTMsg cpoSvcMsg : cpoSvcMsgList) {
//            NWZC154001PMsg shellApiMsg = setShellApiParam(pMsg, cpoSvcMsg);
//            new NWZC154001().execute(shellApiMsg, onBatchType);
//            shellApiMsg = null;
//        }
        // 2017/05/10 S21_NA#Review structure Lv.2 RS#8391 Del Old Logic End
    }

    // 2017/05/10 S21_NA#Review structure Lv.2 RS#8391 Del Old Logic Start
//    private NWZC154001PMsg setShellApiParam(NWZC150001PMsg pMsg, CPO_SVCTMsg cpoSvcMsg) {
//
//        NWZC154001PMsg shellApiMsg = new NWZC154001PMsg();
//
//        shellApiMsg.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
//        shellApiMsg.xxProcMd.setValue(NWZC154001Constant.PROC_MODE_MOD);
//        shellApiMsg.slsDt.setValue(pMsg.slsDt.getValue());
//        shellApiMsg.cpoSvcPk.setValue(cpoSvcMsg.cpoSvcPk.getValue());
//        shellApiMsg.refCpoOrdNum.setValue(pMsg.cpoOrdNum.getValue());
//
//        setDtlParam(shellApiMsg, pMsg, cpoSvcMsg);
//        setCpoSvcConfigRefParam(shellApiMsg, pMsg);
//        setCpoSvcPrcParam(shellApiMsg, pMsg);
//        setCpoSvcUsgPrcParam(shellApiMsg, pMsg);
//        setCpoSvcAddlBasePrcParam(shellApiMsg, pMsg);
//        setCpoSvcAddlChrgPrcParam(shellApiMsg, pMsg);
//
//        return shellApiMsg;
//    }
//
//    private void setDtlParam(NWZC154001PMsg shellApiMsg, NWZC150001PMsg pMsg, CPO_SVCTMsg cpoSvcMsg) {
//
//        List<CPO_SVC_DTLTMsg> cpoSvcDtlMsgList = getCpoSvcDtlList(pMsg, cpoSvcMsg);
//
//        int dtlCnt = 0;
//        for (CPO_SVC_DTLTMsg cpoSvcDtlMsg : cpoSvcDtlMsgList) {
//            shellApiMsg.cpoSvcDtlList.no(dtlCnt).xxRqstTpCd.setValue(NWZC154001Constant.RQST_TP_MOD);
//            ZYPEZDItemValueSetter.setValue(shellApiMsg.cpoSvcDtlList.no(dtlCnt).cpoSvcDtlPk, cpoSvcDtlMsg.cpoSvcDtlPk);
//            ZYPEZDItemValueSetter.setValue(shellApiMsg.cpoSvcDtlList.no(dtlCnt).cpoSvcPk, cpoSvcDtlMsg.cpoSvcPk);
//            ZYPEZDItemValueSetter.setValue(shellApiMsg.cpoSvcDtlList.no(dtlCnt).cpoSvcLineNum, cpoSvcDtlMsg.cpoSvcLineNum);
//            ZYPEZDItemValueSetter.setValue(shellApiMsg.cpoSvcDtlList.no(dtlCnt).cpoSvcMdseCd, cpoSvcDtlMsg.cpoSvcMdseCd);
//            ZYPEZDItemValueSetter.setValue(shellApiMsg.cpoSvcDtlList.no(dtlCnt).prcSvcContrTpCd, cpoSvcDtlMsg.prcSvcContrTpCd);
//            ZYPEZDItemValueSetter.setValue(shellApiMsg.cpoSvcDtlList.no(dtlCnt).prcSvcPlnTpCd, cpoSvcDtlMsg.prcSvcPlnTpCd);
//            ZYPEZDItemValueSetter.setValue(shellApiMsg.cpoSvcDtlList.no(dtlCnt).dsContrCatgCd, cpoSvcDtlMsg.dsContrCatgCd);
//            ZYPEZDItemValueSetter.setValue(shellApiMsg.cpoSvcDtlList.no(dtlCnt).baseBllgCycleCd, cpoSvcDtlMsg.baseBllgCycleCd);
//            ZYPEZDItemValueSetter.setValue(shellApiMsg.cpoSvcDtlList.no(dtlCnt).usgBllgCycleCd, cpoSvcDtlMsg.usgBllgCycleCd);
//            ZYPEZDItemValueSetter.setValue(shellApiMsg.cpoSvcDtlList.no(dtlCnt).fromPerMthNum, cpoSvcDtlMsg.fromPerMthNum);
//            ZYPEZDItemValueSetter.setValue(shellApiMsg.cpoSvcDtlList.no(dtlCnt).toPerMthNum, cpoSvcDtlMsg.toPerMthNum);
//            ZYPEZDItemValueSetter.setValue(shellApiMsg.cpoSvcDtlList.no(dtlCnt).billWithEquipFlg, cpoSvcDtlMsg.billWithEquipFlg);
//            ZYPEZDItemValueSetter.setValue(shellApiMsg.cpoSvcDtlList.no(dtlCnt).billByTpCd, cpoSvcDtlMsg.billByTpCd);
//            ZYPEZDItemValueSetter.setValue(shellApiMsg.cpoSvcDtlList.no(dtlCnt).soldToCustLocCd, cpoSvcDtlMsg.soldToCustLocCd);
//            ZYPEZDItemValueSetter.setValue(shellApiMsg.cpoSvcDtlList.no(dtlCnt).sellToCustCd, cpoSvcDtlMsg.sellToCustCd);
//
//            ZYPEZDItemValueSetter.setValue(shellApiMsg.cpoSvcDtlList.no(dtlCnt).cpoSvcAgmtVerNum, cpoSvcDtlMsg.cpoSvcAgmtVerNum);
//            ZYPEZDItemValueSetter.setValue(shellApiMsg.cpoSvcDtlList.no(dtlCnt).manContrOvrdFlg, cpoSvcDtlMsg.manContrOvrdFlg);
//            ZYPEZDItemValueSetter.setValue(shellApiMsg.cpoSvcDtlList.no(dtlCnt).manContrOvrdRsnCd, cpoSvcDtlMsg.manContrOvrdRsnCd);
//            ZYPEZDItemValueSetter.setValue(shellApiMsg.cpoSvcDtlList.no(dtlCnt).manContrOvrdCmntTxt, cpoSvcDtlMsg.manContrOvrdCmntTxt);
//            ZYPEZDItemValueSetter.setValue(shellApiMsg.cpoSvcDtlList.no(dtlCnt).dsContrPk, cpoSvcDtlMsg.dsContrPk);
//            ZYPEZDItemValueSetter.setValue(shellApiMsg.cpoSvcDtlList.no(dtlCnt).svcPrcCatgCd, cpoSvcDtlMsg.svcPrcCatgCd);
//            ZYPEZDItemValueSetter.setValue(shellApiMsg.cpoSvcDtlList.no(dtlCnt).billWithEquipInvdFlg, cpoSvcDtlMsg.billWithEquipInvdFlg);
//            ZYPEZDItemValueSetter.setValue(shellApiMsg.cpoSvcDtlList.no(dtlCnt).crRebilCd, cpoSvcDtlMsg.crRebilCd);
//            ZYPEZDItemValueSetter.setValue(shellApiMsg.cpoSvcDtlList.no(dtlCnt).contrCratFlg, cpoSvcDtlMsg.contrCratFlg);
//            ZYPEZDItemValueSetter.setValue(shellApiMsg.cpoSvcDtlList.no(dtlCnt).applyEquipBillToFlg, cpoSvcDtlMsg.applyEquipBillToFlg);
//
//            dtlCnt++;
//        }
//        shellApiMsg.cpoSvcDtlList.setValidCount(dtlCnt);
//    }
//
//    private void setCpoSvcConfigRefParam(NWZC154001PMsg shellApiMsg, NWZC150001PMsg pMsg) {
//
//        int configRefCnt = 0;
//        for (int i = 0; i < shellApiMsg.cpoSvcDtlList.getValidCount(); i++) {
//            List<NWZC154001_cpoSvcConfigRefListPMsg> cpoSvcConfigRefListMsgList = getCpoSvcConfigRefList(pMsg, shellApiMsg.cpoSvcDtlList.no(i));
//            if (cpoSvcConfigRefListMsgList == null || cpoSvcConfigRefListMsgList.isEmpty()) {
//                continue;
//            }
//            for (NWZC154001_cpoSvcConfigRefListPMsg cpoSvcConfigRefMsg : cpoSvcConfigRefListMsgList) {
//                EZDMsg.copy(cpoSvcConfigRefMsg, null, shellApiMsg.cpoSvcConfigRefList.no(configRefCnt), null);
//                shellApiMsg.cpoSvcConfigRefList.no(configRefCnt).xxRqstTpCd.setValue(NWZC154001Constant.RQST_TP_MOD);
//                configRefCnt++;
//            }
//        }
//        shellApiMsg.cpoSvcConfigRefList.setValidCount(configRefCnt);
//    }
//
//    private void setCpoSvcPrcParam(NWZC154001PMsg shellApiMsg, NWZC150001PMsg pMsg) {
//
//        int cpoSvcPrcCnt = 0;
//        for (int i = 0; i < shellApiMsg.cpoSvcDtlList.getValidCount(); i++) {
//            List<NWZC154001_cpoSvcPrcListPMsg> cpoSvcPrcListMsgList = getCpoSvcPrcList(pMsg, shellApiMsg.cpoSvcDtlList.no(i));
//            if (cpoSvcPrcListMsgList == null || cpoSvcPrcListMsgList.isEmpty()) {
//                continue;
//            }
//            for (NWZC154001_cpoSvcPrcListPMsg cpoSvcPrcListMsg : cpoSvcPrcListMsgList) {
//                EZDMsg.copy(cpoSvcPrcListMsg, null, shellApiMsg.cpoSvcPrcList.no(cpoSvcPrcCnt), null);
//                shellApiMsg.cpoSvcPrcList.no(cpoSvcPrcCnt).xxRqstTpCd.setValue(NWZC154001Constant.RQST_TP_MOD);
//                cpoSvcPrcCnt++;
//            }
//        }
//        shellApiMsg.cpoSvcPrcList.setValidCount(cpoSvcPrcCnt);
//    }
//
//    private void setCpoSvcUsgPrcParam(NWZC154001PMsg shellApiMsg, NWZC150001PMsg pMsg) {
//
//        int svcUsgPrcCnt = 0;
//        for (int i = 0; i < shellApiMsg.cpoSvcDtlList.getValidCount(); i++) {
//            List<NWZC154001_cpoSvcUsgPrcListPMsg> cpoSvcUsgPrcListMsgList = getCpoSvcUsgPrcList(pMsg, shellApiMsg.cpoSvcDtlList.no(i));
//            if (cpoSvcUsgPrcListMsgList == null || cpoSvcUsgPrcListMsgList.isEmpty()) {
//                continue;
//            }
//            for (NWZC154001_cpoSvcUsgPrcListPMsg cpoSvcUsgPrcListMsg : cpoSvcUsgPrcListMsgList) {
//                EZDMsg.copy(cpoSvcUsgPrcListMsg, null, shellApiMsg.cpoSvcUsgPrcList.no(svcUsgPrcCnt), null);
//                shellApiMsg.cpoSvcUsgPrcList.no(svcUsgPrcCnt).xxRqstTpCd.setValue(NWZC154001Constant.RQST_TP_MOD);
//                svcUsgPrcCnt++;
//            }
//        }
//        shellApiMsg.cpoSvcUsgPrcList.setValidCount(svcUsgPrcCnt);
//    }
//
//    private void setCpoSvcAddlBasePrcParam(NWZC154001PMsg shellApiMsg, NWZC150001PMsg pMsg) {
//
//        int svcAddlBaseCnt = 0;
//        for (int i = 0; i < shellApiMsg.cpoSvcDtlList.getValidCount(); i++) {
//            List<NWZC154001_cpoSvcAddlBasePrcListPMsg> cpoSvcAddlBasePrcMsgList = getCpoSvcAddlBasePrcList(pMsg, shellApiMsg.cpoSvcDtlList.no(i));
//            if (cpoSvcAddlBasePrcMsgList == null || cpoSvcAddlBasePrcMsgList.isEmpty()) {
//                continue;
//            }
//            for (NWZC154001_cpoSvcAddlBasePrcListPMsg cpoSvcAddlBasePrcMsg : cpoSvcAddlBasePrcMsgList) {
//                EZDMsg.copy(cpoSvcAddlBasePrcMsg, null, shellApiMsg.cpoSvcAddlBasePrcList.no(svcAddlBaseCnt), null);
//                shellApiMsg.cpoSvcAddlBasePrcList.no(svcAddlBaseCnt).xxRqstTpCd.setValue(NWZC154001Constant.RQST_TP_MOD);
//                svcAddlBaseCnt++;
//            }
//        }
//        shellApiMsg.cpoSvcAddlBasePrcList.setValidCount(svcAddlBaseCnt);
//    }
//
//
//    private void setCpoSvcAddlChrgPrcParam(NWZC154001PMsg shellApiMsg, NWZC150001PMsg pMsg) {
//
//        int svcAddlChrgCnt = 0;
//        for (int i = 0; i < shellApiMsg.cpoSvcDtlList.getValidCount(); i++) {
//            List<NWZC154001_cpoSvcAddlChrgPrcListPMsg> cpoSvcAddlChrgPrcMsgList = getCpoSvcAddlChrgPrcList(pMsg, shellApiMsg.cpoSvcDtlList.no(i));
//            if (cpoSvcAddlChrgPrcMsgList == null || cpoSvcAddlChrgPrcMsgList.isEmpty()) {
//                continue;
//            }
//            for (NWZC154001_cpoSvcAddlChrgPrcListPMsg cpoSvcAddlChrgPrcMsg : cpoSvcAddlChrgPrcMsgList) {
//                EZDMsg.copy(cpoSvcAddlChrgPrcMsg, null, shellApiMsg.cpoSvcAddlChrgPrcList.no(svcAddlChrgCnt), null);
//                shellApiMsg.cpoSvcAddlChrgPrcList.no(svcAddlChrgCnt).xxRqstTpCd.setValue(NWZC154001Constant.RQST_TP_MOD);
//                svcAddlChrgCnt++;
//            }
//        }
//        shellApiMsg.cpoSvcAddlChrgPrcList.setValidCount(svcAddlChrgCnt);
//    }
//
//    private List<CPO_SVCTMsg> getCpoSvcList(NWZC150001PMsg pMsg) {
//
//        Map<String, String> ssmParam = new HashMap<String, String>();
//        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
//        ssmParam.put("cpoOrdNum", pMsg.cpoOrdNum.getValue());
//        return (List<CPO_SVCTMsg>) ssmClient.queryObjectList("getCpoSvcList", ssmParam);
//    }
//
//    private List<CPO_SVC_DTLTMsg> getCpoSvcDtlList(NWZC150001PMsg pMsg) {
//
//        Map<String, String> ssmParam = new HashMap<String, String>();
//        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
//        ssmParam.put("cpoOrdNum", pMsg.cpoOrdNum.getValue());
//        return (List<CPO_SVC_DTLTMsg>) ssmClient.queryObjectList("getCpoSvcDtlList", ssmParam);
//    }
//
//    private List<CPO_SVC_DTLTMsg> getCpoSvcDtlList(NWZC150001PMsg pMsg, CPO_SVCTMsg cpoSvcMsg) {
//
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
//        ssmParam.put("cpoSvcPk", cpoSvcMsg.cpoSvcPk.getValue());
//        return (List<CPO_SVC_DTLTMsg>) ssmClient.queryObjectList("getCpoSvcDtlList", ssmParam);
//    }
    // 2017/05/10 S21_NA#Review structure Lv.2 RS#8391 Del Old Logic End

    // 2017/05/10 S21_NA#Review structure Lv.2 RS#8391 Del Old Logic Start
//    private List<NWZC154001_cpoSvcConfigRefListPMsg> getCpoSvcConfigRefList(NWZC150001PMsg pMsg, NWZC154001_cpoSvcDtlListPMsg cpoSvcDtlMsg) {
//
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
//        ssmParam.put("cpoSvcDtlPk", cpoSvcDtlMsg.cpoSvcDtlPk.getValue());
//        return (List<NWZC154001_cpoSvcConfigRefListPMsg>) ssmClient.queryObjectList("getCpoSvcConfigRefList", ssmParam);
//    }
//
//    private List<NWZC154001_cpoSvcPrcListPMsg> getCpoSvcPrcList(NWZC150001PMsg pMsg, NWZC154001_cpoSvcDtlListPMsg cpoSvcDtlMsg) {
//
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
//        ssmParam.put("cpoSvcDtlPk", cpoSvcDtlMsg.cpoSvcDtlPk.getValue());
//        return (List<NWZC154001_cpoSvcPrcListPMsg>) ssmClient.queryObjectList("getCpoSvcPrcList", ssmParam);
//    }
//
//    private List<NWZC154001_cpoSvcUsgPrcListPMsg> getCpoSvcUsgPrcList(NWZC150001PMsg pMsg, NWZC154001_cpoSvcDtlListPMsg cpoSvcDtlMsg) {
//
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
//        ssmParam.put("cpoSvcDtlPk", cpoSvcDtlMsg.cpoSvcDtlPk.getValue());
//        return (List<NWZC154001_cpoSvcUsgPrcListPMsg>) ssmClient.queryObjectList("getCpoSvcUsgPrcList", ssmParam);
//    }
//
//
//    private List<NWZC154001_cpoSvcAddlBasePrcListPMsg> getCpoSvcAddlBasePrcList(NWZC150001PMsg pMsg, NWZC154001_cpoSvcDtlListPMsg cpoSvcDtlMsg) {
//
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
//        ssmParam.put("cpoSvcDtlPk", cpoSvcDtlMsg.cpoSvcDtlPk.getValue());
//        return (List<NWZC154001_cpoSvcAddlBasePrcListPMsg>) ssmClient.queryObjectList("getCpoSvcAddlBasePrcList", ssmParam);
//    }
//
//    private List<NWZC154001_cpoSvcAddlChrgPrcListPMsg> getCpoSvcAddlChrgPrcList(NWZC150001PMsg pMsg, NWZC154001_cpoSvcDtlListPMsg cpoSvcDtlMsg) {
//
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
//        ssmParam.put("cpoSvcDtlPk", cpoSvcDtlMsg.cpoSvcDtlPk.getValue());
//        return (List<NWZC154001_cpoSvcAddlChrgPrcListPMsg>) ssmClient.queryObjectList("getCpoSvcAddlChrgPrcList", ssmParam);
//    }
//
//    private BigDecimal getCpoSvcConfigRefCount(String glblCmpyCd, BigDecimal cpoSvcDtlPk) {
//
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", glblCmpyCd);
//        ssmParam.put("cpoSvcDtlPk", cpoSvcDtlPk);
//        return (BigDecimal) ssmClient.queryObject("getCpoSvcConfigRefCount", ssmParam);
//    }
//
//    private BigDecimal getCpoSvcPrcCount(String glblCmpyCd, BigDecimal cpoSvcDtlPk) {
//
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", glblCmpyCd);
//        ssmParam.put("cpoSvcDtlPk", cpoSvcDtlPk);
//        return (BigDecimal) ssmClient.queryObject("getCpoSvcPrcCount", ssmParam);
//    }
//
//    private BigDecimal getCpoSvcUsgPrcCount(String glblCmpyCd, BigDecimal cpoSvcDtlPk) {
//
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", glblCmpyCd);
//        ssmParam.put("cpoSvcDtlPk", cpoSvcDtlPk);
//        return (BigDecimal) ssmClient.queryObject("getCpoSvcUsgPrcCount", ssmParam);
//    }
//
//    private BigDecimal getCpoSvcAddlBasePrcCount(String glblCmpyCd, BigDecimal cpoSvcDtlPk) {
//
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", glblCmpyCd);
//        ssmParam.put("cpoSvcDtlPk", cpoSvcDtlPk);
//        return (BigDecimal) ssmClient.queryObject("getCpoSvcAddlBasePrcCount", ssmParam);
//    }
//
//    private BigDecimal getCpoSvcAddlChrgPrcCount(String glblCmpyCd, BigDecimal cpoSvcDtlPk) {
//
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", glblCmpyCd);
//        ssmParam.put("cpoSvcDtlPk", cpoSvcDtlPk);
//        return (BigDecimal) ssmClient.queryObject("getCpoSvcAddlChrgPrcCount", ssmParam);
//    }
    // 2017/05/10 S21_NA#Review structure Lv.2 RS#8391 Del Old Logic End

    // 2016/08/04 S21_NA#7821-2 Del Start
//    private List<Map<String,String>> getBaseComponentReln(String glblCmpyCd, String cpoOrdNum) {
//
//        Map<String, String> ssmParam = new HashMap<String, String>();
//        ssmParam.put("glblCmpyCd", glblCmpyCd);
//        ssmParam.put("cpoOrdNum", cpoOrdNum);
//        ssmParam.put("mdseTpCtxTpMainMach", MDSE_TP_CTX_TP.MAIN_MACHINE);
//        ssmParam.put("mdseTpSet", MDSE_TP.SET); // 2016/08/04 S21_NA#13012 Add Start
//        ssmParam.put("flgOnY", ZYPConstant.FLG_ON_Y); // 2016/08/04 S21_NA#13012 Add Start
//
//        return (List<Map<String,String>>) ssmClient.queryObjectList("getBaseComponentReln", ssmParam);
//    }
    // 2016/08/04 S21_NA#7821-2 Del End

    private boolean existsCpo(CPOTMsg tMsg) {
        if (tMsg == null) {
            return false;
        }
        if (ORD_HDR_STS.CANCELLED.equals(tMsg.ordHdrStsCd.getValue())) {
            return false;
        }
        if (ORD_HDR_STS.CLOSED.equals(tMsg.ordHdrStsCd.getValue())) {
            return false;
        }
        return true;
    }

    /**
     * <pre>
     * Set the message id.
     * @param pMsg Input parameters.
     * @param val setting value for Message ID
     * </pre>
     */
    private void setMsgId(NWZC150001PMsg pMsg, String val) {
        this.msgIdMgr.addXxMsgId(val, pMsg);
    }

    // 2018/06/05 S21_NA#25151 Add Start
    private boolean hasDeletingLine(NWZC150001PMsg pMsg, NWZC150001_cpoConfigPMsg configPMsg) {

        if (CONFIG_CATG.OUTBOUND.equals(configPMsg.configCatgCd.getValue())) {
            for (int i = 0; i < pMsg.A.getValidCount(); i++) {
                if (RQST_TP_DTL_DELETE.equals(pMsg.A.no(i).xxRqstTpCd_A1.getValue())) {
                    return true;
                }
            }
        } else if (CONFIG_CATG.INBOUND.equals(configPMsg.configCatgCd.getValue())) {
            for (int i = 0; i < pMsg.A.getValidCount(); i++) {
                if (RQST_TP_DTL_DELETE.equals(pMsg.rtnDtl.no(i).xxRqstTpCd_B1.getValue())) {
                    return true;
                }
            }
        }
        return false;
    }
    // 2018/06/05 S21_NA#25151 Add End
}
