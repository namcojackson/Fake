/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC189001;

import static com.canon.cusa.s21.api.NWZ.NWZC189001.constant.NWZC189001Constant.NWZM0012E;
import static com.canon.cusa.s21.api.NWZ.NWZC189001.constant.NWZC189001Constant.NWZM0013E;
import static com.canon.cusa.s21.api.NWZ.NWZC189001.constant.NWZC189001Constant.NWZM0021E;
import static com.canon.cusa.s21.api.NWZ.NWZC189001.constant.NWZC189001Constant.NWZM0188E;
import static com.canon.cusa.s21.api.NWZ.NWZC189001.constant.NWZC189001Constant.NWZM0625E;
import static com.canon.cusa.s21.api.NWZ.NWZC189001.constant.NWZC189001Constant.NWZM1426E;
import static com.canon.cusa.s21.api.NWZ.NWZC189001.constant.NWZC189001Constant.NWZM1428E;
import static com.canon.cusa.s21.api.NWZ.NWZC189001.constant.NWZC189001Constant.NWZM1446E;
import static com.canon.cusa.s21.api.NWZ.NWZC189001.constant.NWZC189001Constant.NWZM1448E;
import static com.canon.cusa.s21.api.NWZ.NWZC189001.constant.NWZC189001Constant.NWZM1472E;
import static com.canon.cusa.s21.api.NWZ.NWZC189001.constant.NWZC189001Constant.NWZM1679E;
import static com.canon.cusa.s21.api.NWZ.NWZC189001.constant.NWZC189001Constant.NWZM1927E;
import static com.canon.cusa.s21.api.NWZ.NWZC189001.constant.NWZC189001Constant.NWZM2239E;
import static com.canon.cusa.s21.api.NWZ.NWZC189001.constant.NWZC189001Constant.NWZM2240E;
import static com.canon.cusa.s21.api.NWZ.NWZC189001.constant.NWZC189001Constant.NWZM2241E;
import static com.canon.cusa.s21.api.NWZ.NWZC189001.constant.NWZC189001Constant.NWZM2242E;
import static com.canon.cusa.s21.api.NWZ.NWZC189001.constant.NWZC189001Constant.NWZM2243E;
import static com.canon.cusa.s21.api.NWZ.NWZC189001.constant.NWZC189001Constant.VAR_CHAR_STAPLE_INCL;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_CONTRTMsg;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.SVC_SPLY_ABUSE_STAGETMsg;
import business.db.SVC_SPLY_ABUSE_STAGETMsgArray;
import business.db.SVC_SPLY_ORD_TP_RELNTMsg;
import business.db.SVC_SPLY_ORD_TP_RELNTMsgArray;
import business.parts.NWZC157001PMsg;
import business.parts.NWZC157002PMsg;
import business.parts.NWZC157004PMsg;
import business.parts.NWZC189001PMsg;
import business.parts.NWZC189001_ordLineListPMsg;
import business.parts.NWZC189001_ordLineReturnListPMsg;

import com.canon.cusa.s21.api.NWZ.NWZC157001.NWZC157001;
import com.canon.cusa.s21.api.NWZ.NWZC189001.constant.NWZC189001Constant;
import com.canon.cusa.s21.common.NSX.NSXC001001.CovTmplInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetCovTmpl;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IMG_SPLY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * My CSA Supply Order Pricing API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/10/31   Fujitsu         K.Ishizuka      Create          QC#18426(Sol#123)
 * 2017/12/25   Fujitsu         N.Sugiura       Update          QC#23207, 23208
 * 2018/01/09   Hitachi         M.Kidokoro      Update          QC#20635
 * 2018/02/20   Fujitsu         K.Ishizuka      Update          QC#23811
 * 
 * </pre>
 */
public class NWZC189001 extends S21ApiCommonBase {

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap = null;

    /** ONBATCH_TYPE */
    private ONBATCH_TYPE onBatType = null;

    /** SSM Batch Client */
    private final S21SsmBatchClient ssmBatClnt;

    /** Sales Date */
    private String slsDt = null;

    /**
     * Constructor
     */
    public NWZC189001() {
        ssmBatClnt = S21SsmBatchClient.getClient(getClass());
    }

    public void execute(final List<NWZC189001PMsg> pMsgList, final ONBATCH_TYPE onBatTp) {
        for (NWZC189001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatTp);
        }
    }

    /**
     * <pre>
     * Execute Process
     * </pre>
     * @param pMsg
     * @param onBatTp
     */
    public void execute(final NWZC189001PMsg pMsg, final ONBATCH_TYPE onBatTp) {
        printParam(pMsg, "NWZC189001 IN-PARAM");
        init(pMsg, onBatTp);
        execute();
        setMsgIdFromMsgIdDetailList(pMsg);
        msgMap.flush();
        printParam(pMsg, "NWZC189001 OUT-PARAM");
    }

    private void setMsgIdFromMsgIdDetailList(NWZC189001PMsg pMsg) {
        if (pMsg.xxMsgIdList.getValidCount() < 1 && pMsg.xxMsgIdDetailList.getValidCount() > 0) {
            setErrMsg(pMsg, NWZM1927E);
        }
    }

    /**
     * <pre>
     * Show Debug Message
     * </pre>
     * @param pMsg
     * @param title
     */
    private void printParam(NWZC189001PMsg pMsg, String title) {
        EZDDebugOutput.println(1, "----------" + title + "----------Start", this);
        EZDDebugOutput.println(1, pMsg.toString(), this);
        EZDDebugOutput.println(1, "----------" + title + "----------End", this);
    }

    /**
     * <pre>
     * Execute Main Process
     * </pre>
     */
    private void execute() {

        vldPMsg();
        if (hasErr()) {
            return;
        }

        NWZC189001PMsg pMsg = (NWZC189001PMsg) msgMap.getPmsg();
        Map<ParamHdrBean, List<Map<String, BigDecimal>>> mdseListMap = getMachineSetListMap(pMsg);

        for (ParamHdrBean hdrBean : getMachineSetList(pMsg)) {
            ParamBean paramBean = getParamBean(hdrBean);
            if (hasErr()) {
                return;
            }

            String mode = pMsg.xxPrcCalcModeCd.getValue();
            if (NWZC189001Constant.MODE_ONLY_NET_PRC.equals(mode)) {
                calcBasePrice(pMsg, paramBean, hdrBean, mdseListMap);
            } else if (NWZC189001Constant.MODE_NET_PRC_FRT_TAX.equals(mode)) {
                calcAllPrice(pMsg, paramBean);
            } else {
                setErrMsg(pMsg, NWZM0013E);
            }
        }

    }

    private List<ParamHdrBean> getMachineSetList(NWZC189001PMsg pMsg) {
        int cnt = pMsg.ordLineList.getValidCount();
        List<ParamHdrBean> list = new ArrayList<ParamHdrBean>();
        boolean mode1Flg = false;
        if (NWZC189001Constant.MODE_ONLY_NET_PRC.equals(pMsg.xxPrcCalcModeCd.getValue())) {
            mode1Flg = true;
        }
        for (int i = 0; i < cnt; i++) {
            NWZC189001_ordLineListPMsg ordLinePMsg = pMsg.ordLineList.no(i);
            ParamHdrBean bean = new ParamHdrBean();
            if (checkSet(list, ordLinePMsg)) {
                continue;
            }
            bean.setDsContrPk(ordLinePMsg.dsContrPk.getValue());
            bean.setSerNum(ordLinePMsg.serNum.getValue());
            bean.setMdseCd_M(ordLinePMsg.mdseCd_M.getValue());
            if (mode1Flg && !hasValue(ordLinePMsg.mdseCd.getValue())) {
                bean.setListFlg(true);
            }
            bean.setListFlg(false);

            list.add(bean);
        }

        return list;

    }

    private Map<ParamHdrBean, List<Map<String, BigDecimal>>> getMachineSetListMap(NWZC189001PMsg pMsg) {
        Map<ParamHdrBean, List<Map<String, BigDecimal>>> listMap = new HashMap<ParamHdrBean, List<Map<String, BigDecimal>>>();
        List<ParamHdrBean> setList = getMachineSetList(pMsg);
        int cnt = pMsg.ordLineList.getValidCount();
        for (ParamHdrBean bean : setList) {
            List<Map<String, BigDecimal>> mdseList = new ArrayList<Map<String, BigDecimal>>();
            Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
            for (int i = 0; i < cnt; i++) {

                NWZC189001_ordLineListPMsg ordLinePMsg = pMsg.ordLineList.no(i);
                if (bean.getDsContrPk().equals(ordLinePMsg.dsContrPk.getValue()) && //
                        bean.getSerNum().equals(ordLinePMsg.serNum.getValue()) && //
                        bean.getMdseCd_M().equals(ordLinePMsg.mdseCd_M.getValue())) {
                    if (hasValue(ordLinePMsg.mdseCd)) {
                        if (hasValue(ordLinePMsg.ordCustUomQty)) {
                            map.put(ordLinePMsg.mdseCd.getValue(), ordLinePMsg.ordCustUomQty.getValue());
                        } else {
                            map.put(ordLinePMsg.mdseCd.getValue(), BigDecimal.ZERO);
                        }
                        mdseList.add(map);
                    }
                }
            }
            listMap.put(bean, mdseList);
        }
        return listMap;
    }

    private boolean checkSet(List<ParamHdrBean> list, NWZC189001_ordLineListPMsg ordLinePMsg) {

        for (ParamHdrBean bean : list) {
            if (bean.getDsContrPk().equals(ordLinePMsg.dsContrPk.getValue()) && //
                    bean.getSerNum().equals(ordLinePMsg.serNum.getValue()) && //
                    bean.getMdseCd_M().equals(ordLinePMsg.mdseCd_M.getValue())) {
                return true;
            }
        }
        return false;
    }

    /**
     * <pre>
     * Initial Process
     * </pre>
     * @param pMsg
     * @param onBatTp
     */
    private void init(NWZC189001PMsg pMsg, final ONBATCH_TYPE onBatTp) {

        msgMap = new S21ApiMessageMap(pMsg);
        onBatType = onBatTp;
        slsDt = ZYPDateUtil.getSalesDate();
    }

    /**
     * <pre>
     * Validate PMsg Param
     * </pre>
     */
    private void vldPMsg() {

        vldMandatoryCheck();
        if (hasErr()) {
            return;
        }

        vldMasterCheck();
        if (hasErr()) {
            return;
        }
    }

    /**
     * <pre>
     * Check Error
     * </pre>
     * @return if has error, return true. if not, return false.
     */
    private boolean hasErr() {

        NWZC189001PMsg pMsg = (NWZC189001PMsg) msgMap.getPmsg();
        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            return true;
        }
        if (pMsg.xxMsgIdDetailList.getValidCount() > 0) {
            return true;
        }
        return false;
    }

    /**
     * <pre>
     * Check Mandatory Parameter
     * </pre>
     */
    private void vldMandatoryCheck() {

        NWZC189001PMsg pMsg = (NWZC189001PMsg) msgMap.getPmsg();
        if (!hasValue(pMsg.glblCmpyCd)) {
            setErrMsg(pMsg, NWZM0188E);
        }

        if (!hasValue(pMsg.xxPrcCalcModeCd)) {
            setErrMsg(pMsg, NWZM0012E);
        }

        for (int i = 0; i < pMsg.ordLineList.getValidCount(); i++) {
            NWZC189001_ordLineListPMsg linePMsg = pMsg.ordLineList.no(i);

            if (!hasValue(linePMsg.dsContrPk)) {
                setDetailErrMsg(pMsg, linePMsg, NWZM2239E);
            }

            if (!hasValue(linePMsg.serNum)) {
                setDetailErrMsg(pMsg, linePMsg, NWZM2240E);
            }

            if (!hasValue(linePMsg.mdseCd_M)) {
                setDetailErrMsg(pMsg, linePMsg, NWZM2241E);
            }

            if (hasValue(pMsg.xxPrcCalcModeCd) && NWZC189001Constant.MODE_NET_PRC_FRT_TAX.equals(pMsg.xxPrcCalcModeCd.getValue())) {

                if (!hasValue(linePMsg.mdseCd)) {
                    setDetailErrMsg(pMsg, linePMsg, NWZM0021E);
                }

                if (!hasValue(linePMsg.ordCustUomQty)) {
                    setDetailErrMsg(pMsg, linePMsg, NWZM2242E);
                }

                if (!hasValue(linePMsg.custUomCd)) {
                    setDetailErrMsg(pMsg, linePMsg, NWZM2243E);
                }
            }
        }

        return;
    }

    /**
     * <pre>
     * Check Master Data
     * </pre>
     */
    private void vldMasterCheck() {

        NWZC189001PMsg pMsg = (NWZC189001PMsg) msgMap.getPmsg();

        if (hasValue(pMsg.frtCondCd)) {
            if (!NWXC150001DsCheck.existsFrtCond(pMsg.glblCmpyCd.getValue(), pMsg.frtCondCd.getValue())) {
                setErrMsg(pMsg, NWZM1426E);
            }
        }

        if (hasValue(pMsg.spclHdlgTpCd)) {
            if (!NWXC150001DsCheck.existsSpclHdlgTp(pMsg.glblCmpyCd.getValue(), pMsg.spclHdlgTpCd.getValue())) {
                setErrMsg(pMsg, NWZM1428E);
            }
        }

        if (hasValue(pMsg.shipToStCd)) {
            if (!NWXC150001DsCheck.existsState(pMsg.glblCmpyCd.getValue(), pMsg.shipToStCd.getValue())) {
                setErrMsg(pMsg, NWZM1446E);
            }
        }

        if (hasValue(pMsg.shipToCtryCd)) {
            if (!NWXC150001DsCheck.existsCtry(pMsg.glblCmpyCd.getValue(), pMsg.shipToCtryCd.getValue())) {
                setErrMsg(pMsg, NWZM1448E);
            }
        }
        return;
    }

    /**
     * <pre>
     * Get Info By Parameter
     * </pre>
     * @return
     */
    private ParamBean getParamBean(ParamHdrBean hdrBean) {
        ParamBean bean = new ParamBean();
        NWZC189001PMsg pMsg = (NWZC189001PMsg) msgMap.getPmsg();
        getMachineInfo(pMsg, bean, hdrBean);

        getContractInfo(pMsg, bean, hdrBean);

        getShipToInfo(pMsg, bean);

        getOrdTp(pMsg, bean);

        getFreightInfo(pMsg, bean);

        getTonerAllotInfo(pMsg, bean, hdrBean);
        
        getStplInclInfo(pMsg, bean, hdrBean); // 2018/02/20 S21_NA#23811 Add

        return bean;
    }

    /**
     * <pre>
     * Set Error Message
     * </pre>
     * @param pMsg
     * @param msgId
     */
    private void setErrMsg(NWZC189001PMsg pMsg, String msgId) {
        setErrMsg(pMsg, msgId, null);
    }

    /**
     * <pre>
     * Set Error Message
     * </pre>
     * @param pMsg
     * @param msgId
     * @param msgPrms
     */
    private void setErrMsg(NWZC189001PMsg pMsg, String msgId, String[] msgPrms) {
        int cnt = pMsg.xxMsgIdList.getValidCount();
        ZYPEZDItemValueSetter.setValue(pMsg.xxMsgIdList.no(cnt).xxMsgId, msgId);
        ZYPEZDItemValueSetter.setValue(pMsg.xxMsgIdList.no(cnt).xxMsgTxt, cutMsg(S21MessageFunc.clspGetMessage(msgId, msgPrms)));
        pMsg.xxMsgIdList.setValidCount(cnt + 1);
    }

    /**
     * <pre>
     * Set Error Message For Detail Line
     * </pre>
     * @param pMsg
     * @param linePMsg
     * @param msgId
     */
    private void setDetailErrMsg(NWZC189001PMsg pMsg, NWZC189001_ordLineListPMsg linePMsg, String msgId) {
        setDetailErrMsg(pMsg, linePMsg, msgId, null);
    }

    /**
     * <pre>
     * Set Error Message For Detail Line
     * </pre>
     * @param pMsg
     * @param linePMsg
     * @param msgId
     * @param msgPrms
     */
    private void setDetailErrMsg(NWZC189001PMsg pMsg, NWZC189001_ordLineListPMsg linePMsg, String msgId, String[] msgPrms) {
        int cnt = pMsg.xxMsgIdDetailList.getValidCount();
        ZYPEZDItemValueSetter.setValue(pMsg.xxMsgIdDetailList.no(cnt).ordSrcRefLineNum, linePMsg.ordSrcRefLineNum.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.xxMsgIdDetailList.no(cnt).mdseCd, linePMsg.mdseCd.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.xxMsgIdDetailList.no(cnt).xxMsgId, msgId);
        ZYPEZDItemValueSetter.setValue(pMsg.xxMsgIdDetailList.no(cnt).xxMsgTxt, cutMsg(S21MessageFunc.clspGetMessage(msgId, msgPrms)));
        pMsg.xxMsgIdDetailList.setValidCount(cnt + 1);
    }

    private String cutMsg(String msg) {
        if (msg == null) {
            return null;
        }
        if (msg.length() > NWZC189001Constant.MAX_MSG_LEN) {
            return msg.substring(0, NWZC189001Constant.MAX_MSG_LEN);
        }
        return msg;
    }

    /**
     * <pre>
     * Get Order Type
     * </pre>
     * @param pMsg
     * @param bean
     */
    private void getOrdTp(NWZC189001PMsg pMsg, ParamBean bean) {
        // get suplIncl
        NSXC001001GetCovTmpl covTmpl = new NSXC001001GetCovTmpl();
        CovTmplInfo tmplInfo = new CovTmplInfo();
        tmplInfo.setGlblCmpyCd(pMsg.glblCmpyCd.getValue());
        tmplInfo.setSlsDt(pMsg.slsDt.getValue());
        tmplInfo.setSvcPgmMdseCd(bean.getSvcPgmMdseCd());
        // get Supply Inclusive
        boolean isSuplIncl = covTmpl.isSuplIncl(tmplInfo);
        // get laser Plus Contract
        boolean isLaserPlusContr = covTmpl.isLaserPlusContr(tmplInfo);
        // get Order Type
        SVC_SPLY_ORD_TP_RELNTMsg condition = new SVC_SPLY_ORD_TP_RELNTMsg();
        condition.setSQLID("001");
        condition.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        condition.setConditionValue("lineBizTpCd01", bean.getSvcByLineBizTpCd());
        if (isSuplIncl) {
            condition.setConditionValue("splyInclFlg01", ZYPConstant.FLG_ON_Y);
        } else {
            condition.setConditionValue("splyInclFlg01", ZYPConstant.FLG_OFF_N);
        }
        if (isLaserPlusContr) {
            condition.setConditionValue("laserPlusFlg01", ZYPConstant.FLG_ON_Y);
        } else {
            condition.setConditionValue("laserPlusFlg01", ZYPConstant.FLG_OFF_N);
        }

        SVC_SPLY_ORD_TP_RELNTMsgArray outTMsgArray = (SVC_SPLY_ORD_TP_RELNTMsgArray) EZDTBLAccessor.findByCondition(condition);
        if (outTMsgArray.getValidCount() > 0) {
            bean.setDsOrdCatgCd(outTMsgArray.no(0).dsOrdCatgCd.getValue());
            bean.setDsOrdTpCd(outTMsgArray.no(0).dsOrdTpCd.getValue());
            bean.setDsOrdRsnCd(outTMsgArray.no(0).dsOrdRsnCd.getValue());
            bean.setDsOrdLineCatgCd(outTMsgArray.no(0).dsOrdLineCatgCd.getValue());
        } else {
            condition.setConditionValue("lineBizTpCd01", "ALL");
            outTMsgArray = (SVC_SPLY_ORD_TP_RELNTMsgArray) EZDTBLAccessor.findByCondition(condition);
            if (outTMsgArray.getValidCount() > 0) {
                bean.setDsOrdCatgCd(outTMsgArray.no(0).dsOrdCatgCd.getValue());
                bean.setDsOrdTpCd(outTMsgArray.no(0).dsOrdTpCd.getValue());
                bean.setDsOrdRsnCd(outTMsgArray.no(0).dsOrdRsnCd.getValue());
                bean.setDsOrdLineCatgCd(outTMsgArray.no(0).dsOrdLineCatgCd.getValue());
            }
        }
    }

    /**
     * @param pMsg
     * @param bean
     */
    private void getTonerAllotInfo(NWZC189001PMsg pMsg, ParamBean bean, ParamHdrBean hdrBean) {

        // get MDSE Info
        SVC_SPLY_ABUSE_STAGETMsgArray outTMsgArray = getSvcSplyAbuseStage(pMsg, bean, hdrBean);
        if (outTMsgArray.getValidCount() > 0) {
            SVC_SPLY_ABUSE_STAGETMsg outTMsg = (SVC_SPLY_ABUSE_STAGETMsg) outTMsgArray.get(0);
            if (hasValue(outTMsg.bwPrrtQty)) {
                bean.setBwPrrtQty(outTMsg.bwPrrtQty.getValue());
            } else {
                bean.setBwPrrtQty(BigDecimal.ZERO);
            }
            if (hasValue(outTMsg.colorPrrtQty)) {
                bean.setColorPrrtQty(outTMsg.colorPrrtQty.getValue());
            } else {
                bean.setColorPrrtQty(BigDecimal.ZERO);
            }
            bean.setTotQty(bean.getBwPrrtQty().add(bean.getColorPrrtQty()));
        } else {
            bean.setBwPrrtQty(BigDecimal.ZERO);
            bean.setColorPrrtQty(BigDecimal.ZERO);
            bean.setTotQty(bean.getBwPrrtQty().add(bean.getColorPrrtQty()));
        }
    }

    /**
     * Get SVC_SPLY_ABUSE_STAGE
     * @param cMsg NSAL0990CMsg
     * @param dsContrPk
     * @param svcPgmMdseCd
     * @return
     */
    private SVC_SPLY_ABUSE_STAGETMsgArray getSvcSplyAbuseStage(NWZC189001PMsg pMsg, ParamBean bean, ParamHdrBean hdrBean) {
        final SVC_SPLY_ABUSE_STAGETMsg condition = new SVC_SPLY_ABUSE_STAGETMsg();
        condition.setSQLID("001");
        condition.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        condition.setConditionValue("dsContrPk01", hdrBean.getDsContrPk());
        condition.setConditionValue("svcPgmMdseCd01", bean.getSvcPgmMdseCd());
        condition.setConditionValue("frzFlg01", ZYPConstant.FLG_OFF_N);

        return (SVC_SPLY_ABUSE_STAGETMsgArray) EZDTBLAccessor.findByCondition(condition);
    }

    /**
     * <pre>
     * Get Info For Machine
     * </pre>
     * @param pMsg
     * @param bean
     */
    @SuppressWarnings("unchecked")
    private void getMachineInfo(NWZC189001PMsg pMsg, ParamBean bean, ParamHdrBean hdrBean) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("serNum", hdrBean.getSerNum());
        ssmParam.put("mdseCd", hdrBean.getMdseCd_M());

        List<Map<String, Object>> list = (List<Map<String, Object>>) ssmBatClnt.queryObjectList("getMachineInfo", ssmParam);
        if (list != null && list.size() > 0) {
            Map<String, Object> resultMap = list.get(0);

            bean.setMdlId((BigDecimal) resultMap.get("MDL_ID"));
            bean.setOwnrAcctNum((String) resultMap.get("OWNR_ACCT_NUM"));
            bean.setOwnrLocNum((String) resultMap.get("OWNR_LOC_NUM"));
            bean.setBillToAcctNum((String) resultMap.get("BILL_TO_ACCT_NUM"));
            bean.setBillToLocNum((String) resultMap.get("BILL_TO_LOC_NUM"));
            bean.setCurLocAcctNum((String) resultMap.get("CUR_LOC_ACCT_NUM"));
            bean.setCurLocNum((String) resultMap.get("CUR_LOC_NUM"));
            bean.setSvcByLineBizTpCd((String) resultMap.get("SVC_BY_LINE_BIZ_TP_CD"));
            bean.setShipToCustCd((String) resultMap.get("SHIP_TO_CUST_CD"));
            bean.setFinBrCd((String) resultMap.get("FIN_BR_CD"));
            // START 2018/01/09 M.Kidokoro [QC#20635,DEL]
//            bean.setTocCd((String) resultMap.get("TOC_CD"));
            // END 2018/01/09 M.Kidokoro [QC#20635,DEL]
            bean.setSvcMachMstrPk((BigDecimal) resultMap.get("SVC_MACH_MSTR_PK"));
            // 2017/12/25 QC#23207 Add Start
            if (!hasValue(pMsg.billToCustAcctCd)) {
                ZYPEZDItemValueSetter.setValue(pMsg.billToCustAcctCd, bean.getBillToAcctNum());
            }

            if (!hasValue(pMsg.billToCustCd)) {
                ZYPEZDItemValueSetter.setValue(pMsg.billToCustCd, bean.getBillToLocNum());
            }

            if (!hasValue(pMsg.shipToCustAcctCd)) {
                ZYPEZDItemValueSetter.setValue(pMsg.shipToCustAcctCd, bean.getCurLocAcctNum());
            }

            if (!hasValue(pMsg.shipToCustCd)) {
                ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, bean.getCurLocNum());
            }
            // 2017/12/25 QC#23207 Add End
        } else {
            setErrMsg(pMsg, NWZM1472E);
        }
    }

    private void getContractInfo(NWZC189001PMsg pMsg, ParamBean bean, ParamHdrBean hdrBean) {
        DS_CONTRTMsg tMsg = new DS_CONTRTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrPk, hdrBean.getDsContrPk());
        tMsg = (DS_CONTRTMsg) S21FastTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            setErrMsg(pMsg, NWZM1679E);
        } else {
            bean.setCcyCd(tMsg.ccyCd.getValue());
            bean.setSvcPgmMdseCd(tMsg.svcPgmMdseCd.getValue());
            bean.setDsCntrCatgCd(tMsg.dsContrCatgCd.getValue()); // 2018/02/20 S21_NA#23811 Add 
        }
    }

    private void getShipToInfo(NWZC189001PMsg pMsg, ParamBean bean) {

        if (ZYPConstant.FLG_OFF_N.equals(pMsg.dropShipFlg.getValue())) {
            SHIP_TO_CUSTTMsg inTMsg = new SHIP_TO_CUSTTMsg();
            inTMsg.setSQLID("002");
            inTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
            inTMsg.setConditionValue("shipToCustCd01", bean.getShipToCustCd());
            inTMsg.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
            SHIP_TO_CUSTTMsgArray outTMsgArray = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
            if (outTMsgArray == null || outTMsgArray.getValidCount() == 0) {
                setErrMsg(pMsg, NWZM0625E);
            } else {
                bean.setCtryCd(outTMsgArray.no(0).ctryCd.getValue());
                bean.setCtyAddr(outTMsgArray.no(0).ctyAddr.getValue());
                bean.setStCd(outTMsgArray.no(0).stCd.getValue());
            }
        }
    }

    private void getFreightInfo(NWZC189001PMsg pMsg, ParamBean bean) {

// 2017/12/25 QC#23207 Del Start
//        DS_ORD_TP_PROC_DFNTMsg tMsg = new DS_ORD_TP_PROC_DFNTMsg();
//        setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
//        setValue(tMsg.dsOrdTpCd, bean.getDsOrdTpCd());
//        tMsg = (DS_ORD_TP_PROC_DFNTMsg) EZDTBLAccessor.findByKey(tMsg);
//        if (tMsg == null) {
//            setErrMsg(pMsg, NWZM1426E);
//        } else {
//            bean.setFrtCondCd(tMsg.frtCondCd.getValue());
//        }
// 2017/12/25 QC#23207 Del End
        // 2017/12/25 QC#23207 Add Start
        if (!ZYPCommonFunc.hasValue(pMsg.frtCondCd) || !ZYPCommonFunc.hasValue(pMsg.shpgSvcLvlCd)) {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
            ssmParam.put("dsOrdTpCd", bean.getDsOrdTpCd());
            ssmParam.put("lineBizTpCd", bean.getSvcByLineBizTpCd());
            Map<String, Object> resultMap = (Map<String, Object>) ssmBatClnt.queryObject("getDefaultFreightTermInfo", ssmParam);
            if (resultMap == null) {
                setErrMsg(pMsg, NWZM1426E);
            } else {
                if (!ZYPCommonFunc.hasValue(pMsg.frtCondCd)) {
                    ZYPEZDItemValueSetter.setValue(pMsg.frtCondCd, (String) resultMap.get("FRT_COND_CD"));
                }
                if (!ZYPCommonFunc.hasValue(pMsg.shpgSvcLvlCd)) {
                    ZYPEZDItemValueSetter.setValue(pMsg.shpgSvcLvlCd, (String) resultMap.get("DEF_SHPG_SVC_LVL_CD"));
                }

            }
        }
        // 2017/12/25 QC#23207 Add End
    }
    
    // 2018/02/20 S21_NA#23811 Add Start
    private void getStplInclInfo(NWZC189001PMsg pMsg, ParamBean bean, ParamHdrBean hdrBean){
        String svcTermCondAttrbNm = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_STAPLE_INCL, pMsg.glblCmpyCd.getValue());
        
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmPrm.put("slsDt", slsDt);
        ssmPrm.put("svcTermCondAttrbNm", svcTermCondAttrbNm);
        ssmPrm.put("svcMachMstrPk", bean.getSvcMachMstrPk());
        ssmPrm.put("dsContrPk", hdrBean.getDsContrPk());
        String fltFlg = ZYPConstant.FLG_OFF_N;
        if (DS_CONTR_CATG.FLEET.equals(bean.getDsCntrCatgCd())) {
            fltFlg = ZYPConstant.FLG_ON_Y;
        }
        ssmPrm.put("fltFlg", fltFlg);
        bean.setSvcCondTerm((String) ssmBatClnt.queryObject("getSvcTermCondDataDispTxt", ssmPrm));
    }
    // 2018/02/20 S21_NA#23811 Add End

    @SuppressWarnings("unchecked")
    private void calcBasePrice(NWZC189001PMsg pMsg, ParamBean bean, ParamHdrBean hdrBean, Map<ParamHdrBean, List<Map<String, BigDecimal>>> mdseListMap) {
        NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();
        List<Map<String, BigDecimal>> mdseInfoList = getMdseList(hdrBean, mdseListMap);
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("mdlId", bean.getMdlId());
        ssmParam.put("slsDt", slsDt);
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmBatClnt.queryObjectList("getSupplyList", ssmParam);
        if (resultList != null && resultList.size() > 0) {
            int cnt = pMsg.ordLineReturnList.getValidCount();
            for (Map<String, Object> resultMap : resultList) {
                String mdse = (String) resultMap.get("MDSE_CD");
                BigDecimal qty = BigDecimal.ZERO;
                if (mdseInfoList != null && mdseInfoList.size() > 0) {
                    qty = hasMdse(mdseInfoList, mdse);
                    if (qty == null) {
                        continue;
                    }
                }
                // get Price List
                prcApiPMsg = callPricingApiOfGetBasePriceMode(pMsg, mdse, bean, qty);
                // set Field Amount
                checkPrcApiParam(pMsg, prcApiPMsg);

                setPriceInfo(pMsg, prcApiPMsg, cnt, bean, hdrBean);
                cnt++;
            }
            pMsg.ordLineList.setValidCount(cnt);
            pMsg.ordLineReturnList.setValidCount(cnt);
        }
    }

    private List<Map<String, BigDecimal>> getMdseList(ParamHdrBean hdrBean, Map<ParamHdrBean, List<Map<String, BigDecimal>>> mdseListMap) {
        for (Map.Entry<ParamHdrBean, List<Map<String, BigDecimal>>> e : mdseListMap.entrySet()) {
            ParamHdrBean bean = e.getKey();
            if (bean.getDsContrPk().equals(hdrBean.getDsContrPk()) && //
                    bean.getSerNum().equals(hdrBean.getSerNum()) && //
                    bean.getMdseCd_M().equals(hdrBean.getMdseCd_M())) {
                return e.getValue();
            }

        }
        return null;
    }

    private BigDecimal hasMdse(List<Map<String, BigDecimal>> mdseInfoList, String mdse) {

        if (mdseInfoList != null) {
            for (Map<String, BigDecimal> map : mdseInfoList) {
                if (map.containsKey(mdse)) {
                    return map.get(mdse);
                }
            }
        }
        return null;
    }

    private void calcAllPrice(NWZC189001PMsg pMsg, ParamBean bean) {
        NWZC157001PMsg prcApiPMsg = callPricingApiOfGetOrderAllMode(pMsg, bean);

        setPriceInfo(pMsg, prcApiPMsg, bean);
    }

    /**
     * @param cMsg NSAL0990CMsg
     * @param prcApiPMsg NWZC157001PMsg
     * @return boolean
     */
    private void checkPrcApiParam(NWZC189001PMsg pMsg, NWZC157001PMsg prcApiPMsg) {
        if (prcApiPMsg == null) {
            return;
        }
        if (S21ApiUtil.isXxMsgId(prcApiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(prcApiPMsg);
            for (S21ApiMessage msg : msgList) {
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                setErrMsg(pMsg, msgId, msgPrms);
            }
        }
        // set Calc Base
        NWZC157002PMsg prcLinePMsg = prcApiPMsg.NWZC157002PMsg.no(0);
        if (S21ApiUtil.isXxMsgId(prcLinePMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(prcLinePMsg);
            for (S21ApiMessage msg : msgList) {
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                setErrMsg(pMsg, msgId, msgPrms);
            }
        }
        // set Calc Base
        NWZC157004PMsg prcLineSumPMsg = prcApiPMsg.NWZC157004PMsg.no(0);
        if (S21ApiUtil.isXxMsgId(prcLineSumPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(prcLineSumPMsg);
            for (S21ApiMessage msg : msgList) {
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                setErrMsg(pMsg, msgId, msgPrms);
            }
        }
    }

    /**
     * Call NWZC1570 Pricing API (02:Get Base Price Mode)
     * @param pMsg
     * @param mdseCd
     * @param bean
     * @param lineNum
     * @return
     */
    private static NWZC157001PMsg callPricingApiOfGetBasePriceMode(NWZC189001PMsg pMsg, String mdseCd, ParamBean bean, BigDecimal qty) {

        NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();

        // Header
        setValue(prcApiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(prcApiPMsg.xxModeCd, NWZC157001.GET_BASE_PRICE);
        setValue(prcApiPMsg.prcBaseDt, pMsg.slsDt.getValue());
        setValue(prcApiPMsg.prcCtxTpCd, PRC_CTX_TP.SALES_PRICING);
        setValue(prcApiPMsg.orgRqstDelyDt, pMsg.slsDt.getValue());
        setValue(prcApiPMsg.dsOrdCatgCd, bean.getDsOrdCatgCd());
        setValue(prcApiPMsg.dsOrdTpCd, bean.getDsOrdTpCd());
        setValue(prcApiPMsg.lineBizTpCd, bean.getSvcByLineBizTpCd());
        setValue(prcApiPMsg.dsAcctNum, bean.getOwnrAcctNum());
        setValue(prcApiPMsg.cpoSrcTpCd, CPO_SRC_TP.SUPPLY_RELEASE_SCREEN);
        setValue(prcApiPMsg.taxCalcFlg, ZYPConstant.FLG_ON_Y);
        setValue(prcApiPMsg.xxSmryLineFlg, ZYPConstant.FLG_ON_Y);

        // Detail : Supply Order Line
        NWZC157002PMsg linePrcApiPMsg = prcApiPMsg.NWZC157002PMsg.no(0);

        setValue(linePrcApiPMsg.trxLineNum, ZYPCommonFunc.leftPad(String.valueOf(1), 3, "0"));
        setValue(linePrcApiPMsg.trxLineSubNum, "001");
        setValue(linePrcApiPMsg.billToCustCd, bean.getBillToLocNum());
        setValue(linePrcApiPMsg.shipToCustCd, bean.getCurLocNum());
        setValue(linePrcApiPMsg.dsAcctNum_SH, bean.getCurLocAcctNum());
        setValue(linePrcApiPMsg.dsAcctNum_BL, bean.getBillToAcctNum());
        setValue(linePrcApiPMsg.coaBrCd, bean.getFinBrCd());
        setValue(linePrcApiPMsg.ccyCd, bean.getCcyCd());
        setValue(linePrcApiPMsg.mdseCd, mdseCd);
        setValue(linePrcApiPMsg.pkgUomCd, PKG_UOM.EACH);
        setValue(linePrcApiPMsg.dsOrdLineCatgCd, bean.getDsOrdLineCatgCd());
        setValue(linePrcApiPMsg.ordQty, qty);
        setValue(linePrcApiPMsg.ordCustUomQty, qty);
        setValue(linePrcApiPMsg.invQty, BigDecimal.ZERO);
        setValue(linePrcApiPMsg.mdlId, bean.getMdlId());
        setValue(linePrcApiPMsg.ctyAddr_SH, bean.getCtyAddr());
        setValue(linePrcApiPMsg.stCd_SH, bean.getStCd());
        setValue(linePrcApiPMsg.ctryCd_SH, bean.getCtryCd());
        setValue(linePrcApiPMsg.frtCondCd, pMsg.frtCondCd);
        setValue(linePrcApiPMsg.shpgSvcLvlCd, pMsg.shpgSvcLvlCd);
        setValue(linePrcApiPMsg.xxPrcCloFlg, ZYPConstant.FLG_OFF_N);
        prcApiPMsg.NWZC157002PMsg.setValidCount(1);

        // call NWZC1570 Pricing API
        new NWZC157001().execute(prcApiPMsg, ONBATCH_TYPE.ONLINE);

        return prcApiPMsg;
    }

    private void setPriceInfo(NWZC189001PMsg pMsg, NWZC157001PMsg prcApiPMsg, int cnt, ParamBean bean, ParamHdrBean hdrBean) {

        NWZC189001_ordLineListPMsg ordLinePMsg = pMsg.ordLineList.no(cnt);
        NWZC189001_ordLineReturnListPMsg ordLineRtnPMsg = pMsg.ordLineReturnList.no(cnt);
        NWZC157002PMsg linePrc02 = prcApiPMsg.NWZC157002PMsg.no(0);
        NWZC157004PMsg linePrc04 = prcApiPMsg.NWZC157004PMsg.no(0);

        ZYPEZDItemValueSetter.setValue(ordLinePMsg.ordSrcRefLineNum, linePrc02.trxLineNum);
        ZYPEZDItemValueSetter.setValue(ordLinePMsg.mdseCd, linePrc02.mdseCd);
        ZYPEZDItemValueSetter.setValue(ordLinePMsg.ordCustUomQty, linePrc02.ordCustUomQty);
        ZYPEZDItemValueSetter.setValue(ordLinePMsg.custUomCd, linePrc02.pkgUomCd);
        ZYPEZDItemValueSetter.setValue(ordLinePMsg.serNum, hdrBean.getSerNum());
        ZYPEZDItemValueSetter.setValue(ordLinePMsg.dsContrPk, hdrBean.getDsContrPk());
        ZYPEZDItemValueSetter.setValue(ordLinePMsg.svcMachMstrPk, bean.getSvcMachMstrPk());
        ZYPEZDItemValueSetter.setValue(ordLinePMsg.mdseCd_M, hdrBean.getMdseCd_M());

        ZYPEZDItemValueSetter.setValue(ordLineRtnPMsg.ordSrcRefLineNum, ordLinePMsg.ordSrcRefLineNum);
        ZYPEZDItemValueSetter.setValue(ordLineRtnPMsg.mdseCd, ordLinePMsg.mdseCd);
        ZYPEZDItemValueSetter.setValue(ordLineRtnPMsg.dealUnitPrcAmt, linePrc04.xxUnitGrsPrcAmt);
        ZYPEZDItemValueSetter.setValue(ordLineRtnPMsg.dealDiscAmt, linePrc04.xxTotDiscAmt);
        ZYPEZDItemValueSetter.setValue(ordLineRtnPMsg.dealNetUnitPrcAmt, linePrc04.xxUnitNetPrcAmt);
        // 2018/02/19 S21_NA#23881 Mod Start
        //ZYPEZDItemValueSetter.setValue(ordLineRtnPMsg.dealNetSlsAmt, linePrc04.xxSlsAmt);
        if (isTonerExistsSplyReln(pMsg.glblCmpyCd.getValue(), ordLinePMsg.mdseCd.getValue(), bean.getSvcCondTerm())) {
            ZYPEZDItemValueSetter.setValue(ordLineRtnPMsg.dealNetSlsAmt, BigDecimal.ZERO);
        } else {
            ZYPEZDItemValueSetter.setValue(ordLineRtnPMsg.dealNetSlsAmt, linePrc04.xxSlsAmt);
        }
        // 2018/02/19 S21_NA#23881 Mod End
        ZYPEZDItemValueSetter.setValue(ordLineRtnPMsg.serNum, ordLinePMsg.serNum);
        ZYPEZDItemValueSetter.setValue(ordLineRtnPMsg.dsContrPk, ordLinePMsg.dsContrPk);
        ZYPEZDItemValueSetter.setValue(ordLineRtnPMsg.mdseCd_M, ordLinePMsg.mdseCd_M);
        ZYPEZDItemValueSetter.setValue(ordLineRtnPMsg.svcMachMstrPk, bean.getSvcMachMstrPk());
        ZYPEZDItemValueSetter.setValue(ordLineRtnPMsg.ordCustUomQty, linePrc02.ordCustUomQty);
        ZYPEZDItemValueSetter.setValue(ordLineRtnPMsg.custUomCd, linePrc02.pkgUomCd);
        ZYPEZDItemValueSetter.setValue(ordLineRtnPMsg.totQty, bean.getTotQty()); // 2017/12/25 QC#23208 Add

    }

    private void setPriceInfo(NWZC189001PMsg pMsg, NWZC157001PMsg prcApiPMsg, ParamBean bean) {

        int cnt = prcApiPMsg.NWZC157002PMsg.getValidCount();

        for (int i = 0; i < cnt; i++) {
            NWZC189001_ordLineListPMsg ordLinePMsg = pMsg.ordLineList.no(i);
            NWZC189001_ordLineReturnListPMsg ordLineRtnPMsg = pMsg.ordLineReturnList.no(i);
            NWZC157002PMsg linePrc02 = prcApiPMsg.NWZC157002PMsg.no(i);
            NWZC157004PMsg linePrc04 = prcApiPMsg.NWZC157004PMsg.no(i);

            ZYPEZDItemValueSetter.setValue(ordLinePMsg.ordSrcRefLineNum, linePrc02.trxLineNum);
            ZYPEZDItemValueSetter.setValue(ordLinePMsg.mdseCd, linePrc02.mdseCd);
            ZYPEZDItemValueSetter.setValue(ordLinePMsg.ordCustUomQty, linePrc02.ordCustUomQty);
            ZYPEZDItemValueSetter.setValue(ordLinePMsg.custUomCd, linePrc02.pkgUomCd);
            ZYPEZDItemValueSetter.setValue(ordLinePMsg.serNum, pMsg.ordLineList.no(0).serNum);
            ZYPEZDItemValueSetter.setValue(ordLinePMsg.dsContrPk, pMsg.ordLineList.no(0).dsContrPk);
            ZYPEZDItemValueSetter.setValue(ordLinePMsg.svcMachMstrPk, bean.getSvcMachMstrPk());
            ZYPEZDItemValueSetter.setValue(ordLinePMsg.mdseCd_M, pMsg.ordLineList.no(0).mdseCd_M);

            ZYPEZDItemValueSetter.setValue(ordLineRtnPMsg.ordSrcRefLineNum, ordLinePMsg.ordSrcRefLineNum);
            ZYPEZDItemValueSetter.setValue(ordLineRtnPMsg.mdseCd, ordLinePMsg.mdseCd);
            ZYPEZDItemValueSetter.setValue(ordLineRtnPMsg.dealUnitPrcAmt, linePrc04.xxUnitGrsPrcAmt);
            // 2018/02/19 S21_NA#23881 Mod Start
            //ZYPEZDItemValueSetter.setValue(ordLineRtnPMsg.dealDiscAmt, linePrc04.xxTotDiscAmt);
            if (isTonerExistsSplyReln(pMsg.glblCmpyCd.getValue(), ordLinePMsg.mdseCd.getValue(), bean.getSvcCondTerm())) {
                ZYPEZDItemValueSetter.setValue(ordLineRtnPMsg.dealDiscAmt, BigDecimal.ZERO);
            } else {
                ZYPEZDItemValueSetter.setValue(ordLineRtnPMsg.dealDiscAmt, linePrc04.xxTotDiscAmt);
            }
            // 2018/02/19 S21_NA#23881 Mod End
            ZYPEZDItemValueSetter.setValue(ordLineRtnPMsg.dealNetUnitPrcAmt, linePrc04.xxUnitNetPrcAmt);
            ZYPEZDItemValueSetter.setValue(ordLineRtnPMsg.dealNetSlsAmt, linePrc04.xxSlsAmt);
            ZYPEZDItemValueSetter.setValue(ordLineRtnPMsg.serNum, ordLinePMsg.serNum);
            ZYPEZDItemValueSetter.setValue(ordLineRtnPMsg.dsContrPk, ordLinePMsg.dsContrPk);
            ZYPEZDItemValueSetter.setValue(ordLineRtnPMsg.mdseCd_M, ordLinePMsg.mdseCd_M);
            ZYPEZDItemValueSetter.setValue(ordLineRtnPMsg.svcMachMstrPk, bean.getSvcMachMstrPk());
            ZYPEZDItemValueSetter.setValue(ordLineRtnPMsg.ordCustUomQty, linePrc02.ordCustUomQty);
            ZYPEZDItemValueSetter.setValue(ordLineRtnPMsg.custUomCd, linePrc02.pkgUomCd);
            ZYPEZDItemValueSetter.setValue(ordLineRtnPMsg.lineDealChrgAmt, linePrc04.xxTotAmt);
            ZYPEZDItemValueSetter.setValue(ordLineRtnPMsg.lineDealTaxAmt, linePrc04.xxTotTaxAmt);
            ZYPEZDItemValueSetter.setValue(ordLineRtnPMsg.totQty, bean.getTotQty());
        }

        pMsg.ordLineList.setValidCount(cnt);
        pMsg.ordLineReturnList.setValidCount(cnt);
    }

    private static NWZC157001PMsg callPricingApiOfGetOrderAllMode(NWZC189001PMsg pMsg, ParamBean bean) {
        NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();

        // Header
        setValue(prcApiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(prcApiPMsg.xxModeCd, NWZC157001.GET_ORDER_ALL);
        setValue(prcApiPMsg.prcBaseDt, pMsg.slsDt.getValue());
        setValue(prcApiPMsg.prcCtxTpCd, PRC_CTX_TP.SALES_PRICING);
        setValue(prcApiPMsg.orgRqstDelyDt, pMsg.slsDt.getValue());
        setValue(prcApiPMsg.dsOrdCatgCd, bean.getDsOrdCatgCd());
        setValue(prcApiPMsg.dsOrdTpCd, bean.getDsOrdTpCd());
        setValue(prcApiPMsg.lineBizTpCd, bean.getSvcByLineBizTpCd());
        setValue(prcApiPMsg.dsAcctNum, bean.getOwnrAcctNum());
        setValue(prcApiPMsg.cpoSrcTpCd, CPO_SRC_TP.SUPPLY_RELEASE_SCREEN);
        //setValue(prcApiPMsg.custIssPoNum, cMsg.custIssPoNum);
        //setValue(prcApiPMsg.dsPmtMethCd, cMsg.dsPmtMethCd);
        setValue(prcApiPMsg.taxCalcFlg, ZYPConstant.FLG_ON_Y);
        setValue(prcApiPMsg.xxSmryLineFlg, ZYPConstant.FLG_ON_Y);

        int cnt = pMsg.ordLineList.getValidCount();
        for (int i = 0; i < cnt; i++) {
            NWZC189001_ordLineListPMsg ordLine = pMsg.ordLineList.no(i);
            NWZC157002PMsg linePrc02 = prcApiPMsg.NWZC157002PMsg.no(i);

            setValue(linePrc02.trxLineNum, ZYPCommonFunc.leftPad(String.valueOf(i + 1), 3, "0"));
            setValue(linePrc02.trxLineSubNum, "001");
            setValue(linePrc02.billToCustCd, bean.getBillToLocNum());
            setValue(linePrc02.shipToCustCd, bean.getCurLocNum());
            setValue(linePrc02.dsAcctNum_SH, bean.getCurLocAcctNum());
            setValue(linePrc02.dsAcctNum_BL, bean.getBillToAcctNum());
            setValue(linePrc02.coaBrCd, bean.getFinBrCd());
            setValue(linePrc02.ccyCd, bean.getCcyCd());
            setValue(linePrc02.mdseCd, ordLine.mdseCd);
            setValue(linePrc02.pkgUomCd, PKG_UOM.EACH);
            setValue(linePrc02.dsOrdLineCatgCd, bean.getDsOrdLineCatgCd());
            setValue(linePrc02.ordQty, ordLine.ordCustUomQty);
            setValue(linePrc02.ordCustUomQty, ordLine.ordCustUomQty);
            setValue(linePrc02.invQty, BigDecimal.ZERO);
            setValue(linePrc02.mdlId, bean.getMdlId());
            setValue(linePrc02.ctyAddr_SH, bean.getCtyAddr());
            setValue(linePrc02.stCd_SH, bean.getStCd());
            setValue(linePrc02.ctryCd_SH, bean.getCtryCd());
            setValue(linePrc02.frtCondCd, pMsg.frtCondCd);
            setValue(linePrc02.shpgSvcLvlCd, pMsg.shpgSvcLvlCd);
            setValue(linePrc02.xxPrcCloFlg, ZYPConstant.FLG_OFF_N);
        }
        prcApiPMsg.NWZC157002PMsg.setValidCount(cnt);

        // call NWZC1570 Pricing API
        new NWZC157001().execute(prcApiPMsg, ONBATCH_TYPE.ONLINE);

        return prcApiPMsg;
    }
    
    // 2018/02/20 S21_NA#23811 Add Start
    private boolean isTonerExistsSplyReln(String glblCmpyCd, String mdseCd, String svcTermCondDataDispTxt_02) {
            Map<String, Object> ssmPrm = new HashMap<String, Object>();
            ssmPrm.put("glblCmpyCd", glblCmpyCd);
            ssmPrm.put("mdseCd", mdseCd);
            
            List<String> imgSplyTpCd = new ArrayList<String>(2);
            imgSplyTpCd.add(IMG_SPLY_TP.TONER);
            if (hasValue(svcTermCondDataDispTxt_02) && "Yes".equals(svcTermCondDataDispTxt_02)) {
                imgSplyTpCd.add(IMG_SPLY_TP.STAPLE);
            }
            ssmPrm.put("imgSplyTpCd", imgSplyTpCd.toArray(new String[imgSplyTpCd.size()]));
            BigDecimal count = (BigDecimal) ssmBatClnt.queryObject("isTonerExistsSplyReln", ssmPrm);
            if (count.compareTo(BigDecimal.ZERO) > 0) {
                return true;
            }
            count = (BigDecimal) ssmBatClnt.queryObject("isTonerExistsSplyRelnForOrdTakeMdse", ssmPrm);
            if (count.compareTo(BigDecimal.ZERO) > 0) {
                return true;
            }
            return false;
        }
    // 2018/02/20 S21_NA#23811 Add End

}
