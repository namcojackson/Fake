/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC185001;

import static com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant.NWZM1450E;
import static com.canon.cusa.s21.api.NWZ.NWZC185001.constant.NWZC185001Constant.BLANK;
import static com.canon.cusa.s21.api.NWZ.NWZC185001.constant.NWZC185001Constant.DELY_ADDL_CMNT_TXT_MAX_SIZE;
import static com.canon.cusa.s21.api.NWZ.NWZC185001.constant.NWZC185001Constant.EQUIPMENT_ORDER_VALUE_SET;
import static com.canon.cusa.s21.api.NWZ.NWZC185001.constant.NWZC185001Constant.LINE;
import static com.canon.cusa.s21.api.NWZ.NWZC185001.constant.NWZC185001Constant.NWZM0021E;
import static com.canon.cusa.s21.api.NWZ.NWZC185001.constant.NWZC185001Constant.NWZM0036E;
import static com.canon.cusa.s21.api.NWZ.NWZC185001.constant.NWZC185001Constant.NWZM0046E;
import static com.canon.cusa.s21.api.NWZ.NWZC185001.constant.NWZC185001Constant.NWZM0049E;
import static com.canon.cusa.s21.api.NWZ.NWZC185001.constant.NWZC185001Constant.NWZM0188E;
import static com.canon.cusa.s21.api.NWZ.NWZC185001.constant.NWZC185001Constant.NWZM0347E;
import static com.canon.cusa.s21.api.NWZ.NWZC185001.constant.NWZC185001Constant.NWZM1138E;
import static com.canon.cusa.s21.api.NWZ.NWZC185001.constant.NWZC185001Constant.NWZM1266E;
import static com.canon.cusa.s21.api.NWZ.NWZC185001.constant.NWZC185001Constant.NWZM1286E;
import static com.canon.cusa.s21.api.NWZ.NWZC185001.constant.NWZC185001Constant.NWZM1346E;
import static com.canon.cusa.s21.api.NWZ.NWZC185001.constant.NWZC185001Constant.NWZM1426E;
import static com.canon.cusa.s21.api.NWZ.NWZC185001.constant.NWZC185001Constant.NWZM1428E;
import static com.canon.cusa.s21.api.NWZ.NWZC185001.constant.NWZC185001Constant.NWZM1446E;
import static com.canon.cusa.s21.api.NWZ.NWZC185001.constant.NWZC185001Constant.NWZM1448E;
import static com.canon.cusa.s21.api.NWZ.NWZC185001.constant.NWZC185001Constant.NWZM1514E;
import static com.canon.cusa.s21.api.NWZ.NWZC185001.constant.NWZC185001Constant.NWZM1900E;
import static com.canon.cusa.s21.api.NWZ.NWZC185001.constant.NWZC185001Constant.NWZM1901E;
import static com.canon.cusa.s21.api.NWZ.NWZC185001.constant.NWZC185001Constant.NWZM1902E;
import static com.canon.cusa.s21.api.NWZ.NWZC185001.constant.NWZC185001Constant.NWZM1903E;
import static com.canon.cusa.s21.api.NWZ.NWZC185001.constant.NWZC185001Constant.NWZM1904E;
import static com.canon.cusa.s21.api.NWZ.NWZC185001.constant.NWZC185001Constant.NWZM1905E;
import static com.canon.cusa.s21.api.NWZ.NWZC185001.constant.NWZC185001Constant.NWZM1906E;
import static com.canon.cusa.s21.api.NWZ.NWZC185001.constant.NWZC185001Constant.NWZM1907E;
import static com.canon.cusa.s21.api.NWZ.NWZC185001.constant.NWZC185001Constant.NWZM1908E;
import static com.canon.cusa.s21.api.NWZ.NWZC185001.constant.NWZC185001Constant.NWZM1909E;
import static com.canon.cusa.s21.api.NWZ.NWZC185001.constant.NWZC185001Constant.NWZM1910E;
import static com.canon.cusa.s21.api.NWZ.NWZC185001.constant.NWZC185001Constant.NWZM1911E;
import static com.canon.cusa.s21.api.NWZ.NWZC185001.constant.NWZC185001Constant.NWZM1912E;
import static com.canon.cusa.s21.api.NWZ.NWZC185001.constant.NWZC185001Constant.NWZM1927E;
import static com.canon.cusa.s21.api.NWZ.NWZC185001.constant.NWZC185001Constant.NWZM2254E;
import static com.canon.cusa.s21.api.NWZ.NWZC185001.constant.NWZC185001Constant.NWZM2255E;
import static com.canon.cusa.s21.api.NWZ.NWZC185001.constant.NWZC185001Constant.NWZM2323E;
import static com.canon.cusa.s21.api.NWZ.NWZC185001.constant.NWZC185001Constant.ML_TMPL_ID_01;
import static com.canon.cusa.s21.api.NWZ.NWZC185001.constant.NWZC185001Constant.ML_GRP_ID_01;
import static com.canon.cusa.s21.api.NWZ.NWZC185001.constant.NWZC185001Constant.SORT_NUM_MYCSAUSER;
import static com.canon.cusa.s21.api.NWZ.NWZC185001.constant.NWZC185001Constant.MYCSAUSER_FIRST_NM;
import static com.canon.cusa.s21.api.NWZ.NWZC185001.constant.NWZC185001Constant.MYCSAUSER_LAST_NM;
import static com.canon.cusa.s21.api.NWZ.NWZC185001.constant.NWZC185001Constant.MYCSAUSER_TEL_NUM;
import static com.canon.cusa.s21.api.NWZ.NWZC185001.constant.NWZC185001Constant.SUPPLIES_ORDER_VALUE_SET;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.common.EZDMsg;
import parts.common.EZDPStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CPO_DTLTMsg;
import business.db.CPO_DTLTMsgArray;
import business.db.CTAC_PSNTMsg;
import business.db.CTAC_TPTMsg;
import business.db.DS_ORD_TPTMsg;
import business.db.DS_ORD_TP_PROC_DFNTMsg;
import business.db.MDSETMsg;
import business.db.MDSETMsgArray;
import business.db.MDSE_STORE_PKGTMsg;
import business.db.MDSE_STORE_PKGTMsgArray;
import business.db.ORD_CATG_BIZ_CTXTMsg;
import business.db.ORD_CATG_BIZ_CTXTMsgArray;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.PRC_CATGTMsg;
import business.db.SHPG_SVC_LVL_CARR_RELNTMsg;
import business.db.TOCTMsg;
import business.parts.NMZC260001PMsg;
import business.parts.NMZC260001_defSlsRepListPMsg;
import business.parts.NMZC260001_defSlsRepListPMsgArray;
import business.parts.NMZC610001PMsg;
import business.parts.NMZC610001_EligibleCheckListPMsg;
import business.parts.NMZC611001PMsg;
import business.parts.NWZC150001PMsg;
import business.parts.NWZC150001_APMsg;
import business.parts.NWZC150001_cpoConfigPMsg;
import business.parts.NWZC150001_cpoCtacPsnInfoListPMsg;
import business.parts.NWZC150001_cpoDlvyInfoListPMsg;
import business.parts.NWZC150001_cpoSlsCrPMsg;
import business.parts.NWZC150001_priceListPMsg;
import business.parts.NWZC150002PMsg;
import business.parts.NWZC150003PMsg;
import business.parts.NWZC157001PMsg;
import business.parts.NWZC157002PMsg;
import business.parts.NWZC157003PMsg;
import business.parts.NWZC157004PMsg;
import business.parts.NWZC180001PMsg;
import business.parts.NWZC185001PMsg;
import business.parts.NWZC185001_OrdLineListPMsg;
import business.parts.NWZC185001_OrdLineReturnListPMsg;

import com.canon.cusa.s21.api.NMZ.NMZC260001.NMZC260001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant;
import com.canon.cusa.s21.api.NMZ.NMZC611001.NMZC611001;
import com.canon.cusa.s21.api.NWZ.NWZC150001.NWZC150001;
import com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC157001.NWZC157001;
import com.canon.cusa.s21.api.NWZ.NWZC180001.NWZC180001;
import com.canon.cusa.s21.api.NWZ.NWZC180001.constant.NWZC180001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC185001.constant.NWZC185001Constant;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001Exchange;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ExchangeAmount;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ExchangeAmoutData;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ExchangeData;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ExchangePriceData;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CUST_MSG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_DRCTN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_COND;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INTFC_XREF_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_DTL_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.mail.ZYPMail;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 * <pre>
 * IS Web Store Order Creation API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/08/2016   Fujitsu         Kamide          Create
 * 08/04/2016   Fujitsu         N.Sugiura       Update          QC#11812
 * 09/15/2016   Hitachi         Y.Takeno        Update          QC#13158
 * 10/04/2016   Fujitsu         N.Sugiura       Update          QC#13170
 * 12/13/2016   SRAA            K.Aratani       Update          QC#14722
 * 12/22/2016   Fujitsu         S.Ohki          Update          QC#16617
 * 04/12/2017   Fujitsu         Y.Kanefusa      Update          S21_NA#18235
 * 2017/06/09   Fujitsu         S.Takami        Update          S21_NA#18296
 * 2017/10/18   Fujitsu         K.Ishizuka      Update          QC#20246(Sol#454)
 * 2017/10/26   Fujitsu         H.Sugawara      Update          QC#21590
 * 2017/10/26   Fujitsu         H.Sugawara      Update          QC#21593
 * 2017/10/26   Fujitsu         H.Sugawara      Update          QC#22020
 * 2017/12/14   Fujitsu         Hd.Sugawara     Update          QC#19804(Sol#349)
 * 2018/02/08   Fujitsu         K.Ishizuka      Update          QC#20297(Sol#379)
 * 2018/02/26   Fujitsu         Hd.Sugawara     Update          QC#22967
 * 2018/04/18   Fujitsu         K.Ishizuka      Update          QC#25418
 * 2018/06/07   Fujitsu         A.Kosai         Update          QC#26123
 * 2018/06/27   Fujitsu         Mz.Takahashi    Update          QC#23726
 * 2018/09/19   Fujitsu         N.Sugiura       Update          QC#28284
 * 2018/12/18   Fujitsu         M.Ohno          Update          QC#29315
 * 2019/07/17   Fujitsu         Mz.Takahashi    Update          QC#51666
 * 2019/10/04   Fujitsu         K.Kato          Update          S21_NA#51372
 * 2020/02/18   Fujitsu         Y.Kanefusa      Update          S21_NA#55981
 * 2020/04/24   CITS            K.Ogino         Update          QC#56638
 * 2023/09/20   Hitachi         H.Watanabe      Update          QC#61782
 * 2023/10/14   CITS            F.Komaki        Update          QC#61942
 * </pre>
 */
public class NWZC185001 extends S21ApiCommonBase {

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
    public NWZC185001() {
        ssmBatClnt = S21SsmBatchClient.getClient(getClass());
    }

    public void execute(final List<NWZC185001PMsg> pMsgList, final ONBATCH_TYPE onBatTp) {
        for (NWZC185001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatTp);
        }
    }

    public void execute(final NWZC185001PMsg pMsg, final ONBATCH_TYPE onBatTp) {
        printParam(pMsg, "NWZC185001 IN-PARAM");
        init(pMsg, onBatTp);
        execute();
        setMsgIdFromMsgIdDetailList(pMsg);
        msgMap.flush();
        printParam(pMsg, "NWZC185001 OUT-PARAM");
    }

    private void setMsgIdFromMsgIdDetailList(NWZC185001PMsg pMsg) {
        if (pMsg.xxMsgIdList.getValidCount() < 1 && pMsg.xxMsgIdDetailList.getValidCount() > 0) {
            setErrMsg(pMsg, NWZM1927E);
        }
    }

    private void printParam(NWZC185001PMsg pMsg, String title) {
        EZDDebugOutput.println(1, "----------" + title + "----------Start", this);
        EZDDebugOutput.println(1, pMsg.toString(), this);
        EZDDebugOutput.println(1, "----------" + title + "----------End", this);
    }

    private void execute() {

        vldPMsg();
        if (hasErr()) {
            return;
        }

        ParamBean paramBean = getParamBean();
        if (hasErr()) {
            return;
        }

        // 2018/06/27 QC#23726 Add Start
        setDefaultCarrierServiceLevel(paramBean);
        if (hasErr()) {
            return;
        }
        // 2018/06/27 QC#23726 Add End

        vldOtherCheck(paramBean);
        if (hasErr()) {
            return;
        }

        setPriceInfo(paramBean);
        if (hasErr()) {
            return;
        }

        callDsCpoUpdateApi(paramBean);

    }

    // 2018/06/27 QC#23726 Add Start
    /**
     * Derive Default Carrier Service Level
     * @param bizMsg NWZC185001PMsg
     * @return No API Error : true
     */
    private void setDefaultCarrierServiceLevel(ParamBean paramBean) {
        NWZC185001PMsg pMsg = (NWZC185001PMsg) msgMap.getPmsg();

        if (ZYPCommonFunc.hasValue(pMsg.carrSvcLvlCd)){
            return;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.shipToLocNum) || !ZYPCommonFunc.hasValue(pMsg.shpgSvcLvlCd)) {
            return;
        }

        String shipToAcctNum = getShipToAcctNumByLocNum(pMsg);

        if (!ZYPCommonFunc.hasValue(shipToAcctNum)){
            setErrMsg(pMsg, NWZM1909E);
            return;
        }

        // 2018/12/19 S21_NA#29315 Mod Start
//        NMZC611001PMsg defCarrApiPMsg = callDefaultCarrierApi(pMsg, shipToAcctNum);
        NMZC611001PMsg defCarrApiPMsg = callDefaultCarrierApi(pMsg, shipToAcctNum, paramBean);
        // 2018/12/19 S21_NA#29315 Mod End

        if (S21ApiUtil.isXxMsgId(defCarrApiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(defCarrApiPMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                setErrMsg(pMsg, msgId, msgPrms);

                if (msgId.endsWith("E")) {
                    return;
                }
            }
        }

        String vndCd = defCarrApiPMsg.vndCd_O.getValue();
        if (ZYPCommonFunc.hasValue(vndCd)) {
            if (FRT_COND.COLLECT.equals(pMsg.frtCondCd)){
                ZYPEZDItemValueSetter.setValue(pMsg.carrAcctNum, defCarrApiPMsg.dsCarrAcctNum.getValue());
            }

            String  carrSvcLvlCd = getCarrSvcLvlCd(pMsg, vndCd);
            if (S21StringUtil.isNotEmpty(carrSvcLvlCd)){
                ZYPEZDItemValueSetter.setValue(pMsg.carrSvcLvlCd, carrSvcLvlCd);
            }
        }
    }

    /**
     * Call NMZC6110 Default Carrier API
     * @param bizMsg NWAL1500CMsg
     * @return NMZC611001PMsg
     */
    public static NMZC611001PMsg callDefaultCarrierApi(NWZC185001PMsg pMsg, String shipToAcctNum, ParamBean bean) {

        NMZC611001PMsg defCarrApiPMsg = new NMZC611001PMsg();
        ZYPEZDItemValueSetter.setValue(defCarrApiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(defCarrApiPMsg.slsDt, pMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(defCarrApiPMsg.dsAcctNum, shipToAcctNum);
        // 2018/12/19 S21_NA#29315 Add Start
        ZYPEZDItemValueSetter.setValue(defCarrApiPMsg.locNum, pMsg.shipToLocNum);
        ZYPEZDItemValueSetter.setValue(defCarrApiPMsg.dsBizAreaCd, getDsBizAreaCd(pMsg, bean));
        ZYPEZDItemValueSetter.setValue(defCarrApiPMsg.lineBizTpCd, bean.getLineBizTpCd());
        // 2018/12/19 S21_NA#29315 Add End
        new NMZC611001().execute(defCarrApiPMsg, ONBATCH_TYPE.ONLINE);

        return defCarrApiPMsg;
    }

    private String getShipToAcctNumByLocNum(NWZC185001PMsg pMsg) {
        String shipToAcctNum = "";

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd);
        ssmParam.put("slsDt", this.slsDt);
        ssmParam.put("locNum", pMsg.shipToLocNum);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        List<Map<String, Object>> result = (List<Map<String, Object>>) ssmBatClnt.queryObjectList("getShipToAcctNumByLocNum", ssmParam);

        if (!result.isEmpty()){
            shipToAcctNum = (String)result.get(0).get("SELL_TO_CUST_CD");
        }
        
        return shipToAcctNum;
    }

    /**
     * get Carrier SerVice Level Code
     * @param bizMsg NWZC185001PMsg
     * @param carrCd Carrier Code
     * @return Carrier SerVice Level Code
     */
    public String getCarrSvcLvlCd(NWZC185001PMsg pMsg, String carrCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        params.put("shpgSvcLvlCd", pMsg.shpgSvcLvlCd.getValue());
        params.put("carrCd", carrCd);

        return (String)ssmBatClnt.queryObject("getCarrSvcLvlCd", params);
    }
    // 2018/06/27 QC#23726 Add End

    private void init(NWZC185001PMsg pMsg, final ONBATCH_TYPE onBatTp) {

        msgMap = new S21ApiMessageMap(pMsg);

        onBatType = onBatTp;

        slsDt = ZYPDateUtil.getSalesDate();
    }

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

    private boolean hasErr() {

        NWZC185001PMsg pMsg = (NWZC185001PMsg) msgMap.getPmsg();
        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            return true;
        }
        if (pMsg.xxMsgIdDetailList.getValidCount() > 0) {
            return true;
        }
        return false;
    }

    private void vldMandatoryCheck() {

        NWZC185001PMsg pMsg = (NWZC185001PMsg) msgMap.getPmsg();
        if (!hasValue(pMsg.glblCmpyCd)) {
            setErrMsg(pMsg, NWZM0188E);
        }

        if (!hasValue(pMsg.lgcyOrdTpCd)) {
            setErrMsg(pMsg, NWZM1900E);
        }

        if (!hasValue(pMsg.lgcyOrdRsnCd)) {
            setErrMsg(pMsg, NWZM1901E);
        }

        if (!hasValue(pMsg.billToLocNum)) {
            setErrMsg(pMsg, NWZM1902E);
        }

        if (!hasValue(pMsg.soldToLocNum)) {
            setErrMsg(pMsg, NWZM1903E);
        }

        if (!hasValue(pMsg.shipToLocNum)) {
            setErrMsg(pMsg, NWZM1904E);
        }

        if (!hasValue(pMsg.frtCondCd)) {
            setErrMsg(pMsg, NWZM1266E);
        }

        if (!hasValue(pMsg.shpgSvcLvlCd)) {
            setErrMsg(pMsg, NWZM0049E);
        }

        if (!hasValue(pMsg.dropShipFlg)) {
            setErrMsg(pMsg, NWZM0347E);
        }

        if (!hasValue(pMsg.ctacPsnPk)) {
            setErrMsg(pMsg, NWZM1346E);
        }

        for (int i = 0; i < pMsg.OrdLineList.getValidCount(); i++) {
            NWZC185001_OrdLineListPMsg linePMsg = pMsg.OrdLineList.no(i);
            if (!hasValue(linePMsg.ordSrcRefLineNum)) {
                setDetailErrMsg(pMsg, linePMsg, NWZM1905E);
            }

            if (!hasValue(linePMsg.mdseCd)) {
                setDetailErrMsg(pMsg, linePMsg, NWZM0021E);
            }

            if (!hasValue(linePMsg.ordQty)) {
                setDetailErrMsg(pMsg, linePMsg, NWZM0046E);
            }
        }
        return;
    }

    private void vldMasterCheck() {

        NWZC185001PMsg pMsg = (NWZC185001PMsg) msgMap.getPmsg();

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

        if (hasValue(pMsg.ctacPsnPk)) {
            CTAC_PSNTMsg ctacPsnTMsg = getCtacPsn(pMsg.glblCmpyCd.getValue(), pMsg.ctacPsnPk.getValue());
            if (ctacPsnTMsg == null) {
                setErrMsg(pMsg, NWZM1138E);
            }
        }
        return;
    }

    private void vldOtherCheck(ParamBean bean) {

        NWZC185001PMsg pMsg = (NWZC185001PMsg) msgMap.getPmsg();

        if (NWXC150001DsCheck.checkDsOrdTpAndDsOrdCatgRelation(pMsg.glblCmpyCd.getValue(), bean.getDsOrdTpCd(), bean.getDsOrdCatgCd())) {
            setErrMsg(pMsg, NWZM1450E);
        }

        checkBillShipSoldRelation(pMsg, bean);

        if (hasValue(pMsg.shpgSvcLvlCd) && hasValue(pMsg.carrCd)) {
            if (checkCarrCdAndShpgSvcLvlRelation(pMsg.glblCmpyCd.getValue(), pMsg.shpgSvcLvlCd.getValue(), pMsg.carrCd.getValue())) {
                setErrMsg(pMsg, NWZM1911E);
            }
        }

        if (hasValue(pMsg.frtCondCd) && hasValue(pMsg.shpgSvcLvlCd)) {
            // 2018/12/13 S21_NA#29315 Add Start
            String carrSvcLvl = null ;
            if (hasValue(pMsg.carrCd)) {
                SHPG_SVC_LVL_CARR_RELNTMsg inTMsg = new SHPG_SVC_LVL_CARR_RELNTMsg();
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(inTMsg.shpgSvcLvlCd, pMsg.shpgSvcLvlCd);
                ZYPEZDItemValueSetter.setValue(inTMsg.carrCd, pMsg.carrCd);
                inTMsg = (SHPG_SVC_LVL_CARR_RELNTMsg) S21FastTBLAccessor.findByKey(inTMsg);
                if (inTMsg != null) {
                    carrSvcLvl = inTMsg.carrSvcLvlCd.getValue();
                }
            }
            // 2018/12/13 S21_NA#29315 Add End
            // 2018/12/13 S21_NA#29315 Mod Start
//            if (checkFrtCondSvcLvlRelation(pMsg.glblCmpyCd.getValue(), bean.getLineBizTpCd(), pMsg.frtCondCd.getValue(), pMsg.shpgSvcLvlCd.getValue())) {
            if (NWXC150001DsCheck.checkFrtCondSvcLvlRelation(pMsg.glblCmpyCd.getValue() //
                    , this.slsDt //
                    , bean.getDsOrdTpCd() //
                    , pMsg.frtCondCd.getValue() //
                    , pMsg.shpgSvcLvlCd.getValue() //
                    , carrSvcLvl //
                    , bean.getShipToCustAcctCd() //
                    , bean.getShipToLocNum())) {
                setErrMsg(pMsg, NWZM1912E);
            }
            // 2018/12/13 S21_NA#29315 Add End
        }

        return;
    }

    private void setErrMsg(NWZC185001PMsg pMsg, String msgId) {
        setErrMsg(pMsg, msgId, null);
    }

    private void setErrMsg(NWZC185001PMsg pMsg, String msgId, String[] msgPrms) {
        int cnt = pMsg.xxMsgIdList.getValidCount();
        ZYPEZDItemValueSetter.setValue(pMsg.xxMsgIdList.no(cnt).xxMsgId, msgId);
        ZYPEZDItemValueSetter.setValue(pMsg.xxMsgIdList.no(cnt).xxMsgTxt, cutMsg(S21MessageFunc.clspGetMessage(msgId, msgPrms)));
        pMsg.xxMsgIdList.setValidCount(cnt + 1);
    }

    private void setDetailErrMsg(NWZC185001PMsg pMsg, NWZC185001_OrdLineListPMsg linePMsg, String msgId) {
        setDetailErrMsg(pMsg, linePMsg, msgId, null);
    }

    private void setDetailErrMsg(NWZC185001PMsg pMsg, NWZC185001_OrdLineListPMsg linePMsg, String msgId, String[] msgPrms) {
        int cnt = pMsg.xxMsgIdDetailList.getValidCount();
        // 2023/10/14 QC#61942 START
        if (linePMsg != null) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxMsgIdDetailList.no(cnt).ordSrcRefLineNum, linePMsg.ordSrcRefLineNum);
            ZYPEZDItemValueSetter.setValue(pMsg.xxMsgIdDetailList.no(cnt).mdseCd, linePMsg.mdseCd);
        }
        // 2023/10/14 QC#61942 END
        ZYPEZDItemValueSetter.setValue(pMsg.xxMsgIdDetailList.no(cnt).xxMsgId, msgId);
        ZYPEZDItemValueSetter.setValue(pMsg.xxMsgIdDetailList.no(cnt).xxMsgTxt, cutMsg(S21MessageFunc.clspGetMessage(msgId, msgPrms)));
        pMsg.xxMsgIdDetailList.setValidCount(cnt + 1);
    }

    private String cutMsg(String msg) {
        if (msg == null) {
            return null;
        }
        if (msg.length() > NWZC185001Constant.MAX_MSG_LEN) {
            return msg.substring(0, NWZC185001Constant.MAX_MSG_LEN);
        }
        return msg;
    }

    /**
     * <pre>
     * Call Pricing API
     * </pre>
     * @param paramBean
     * @return NWZC157001PMsg
     */
    private NWZC157001PMsg callPricingApiForBasePrice(ParamBean paramBean) {

        NWZC185001PMsg pMsg = (NWZC185001PMsg) msgMap.getPmsg();
        String modeCd = NWZC157001.GET_BASE_PRICE;
        NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();
        setHdrParam(prcApiPMsg, modeCd, paramBean);
        setLineParam(prcApiPMsg, modeCd, paramBean);
        if (hasErr()) {
            return prcApiPMsg;
        }

        new NWZC157001().execute(prcApiPMsg, this.onBatType);
        if (S21ApiUtil.isXxMsgId(prcApiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(prcApiPMsg);
            for (S21ApiMessage msg : msgList) {
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                setErrMsg(pMsg, msgId, msgPrms);
            }
        }

        for (int i = 0; i < prcApiPMsg.NWZC157002PMsg.getValidCount(); i++) {
            if (S21ApiUtil.isXxMsgId(prcApiPMsg.NWZC157002PMsg.no(i))) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(prcApiPMsg.NWZC157002PMsg.no(i));
                for (S21ApiMessage msg : msgList) {
                    ParamDetailBean detailBean = getParamDetailBean(paramBean, prcApiPMsg.NWZC157002PMsg.no(i).trxLineNum.getValue(), prcApiPMsg.NWZC157002PMsg.no(i).trxLineSubNum.getValue());
                    NWZC185001_OrdLineListPMsg linePMsg = getTarget(pMsg, detailBean.getOrdSrcRefLineNum());
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    setDetailErrMsg(pMsg, linePMsg, msgId, msgPrms);
                }
            }
        }

        return prcApiPMsg;
    }

    /**
     * <pre>
     * Call CPO Update API
     * </pre>
     * @param paramBean
     * @return No Error : true
     */
    private boolean callDsCpoUpdateApi(ParamBean paramBean) {

        NWZC185001PMsg pMsg = (NWZC185001PMsg) msgMap.getPmsg();

        NWZC150001PMsg apiPMsg = getApiParam(paramBean);
        final List<NWZC150002PMsg> cpoUpdApiOutMsgList = new ArrayList<NWZC150002PMsg>();
        final List<NWZC150003PMsg> cpoUpdApiOutMsgList03 = new ArrayList<NWZC150003PMsg>();

        new NWZC150001().execute(apiPMsg, cpoUpdApiOutMsgList, cpoUpdApiOutMsgList03, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(apiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(apiPMsg);
            for (S21ApiMessage msg : msgList) {
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                setErrMsg(pMsg, msgId, msgPrms);
            }
        }

        for (NWZC150002PMsg pMsg02 : cpoUpdApiOutMsgList) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg02);
            for (S21ApiMessage msg : msgList) {
                NWZC185001_OrdLineListPMsg linePMsg = getTarget(pMsg, paramBean, pMsg02);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                setDetailErrMsg(pMsg, linePMsg, msgId, msgPrms);
            }

        }
        if (hasErr()) {
            return false;
        }
        ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum, apiPMsg.cpoOrdNum);
        ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdTs, apiPMsg.cpoOrdTs);

        CPO_DTLTMsgArray cpoDtlArray = getDsCpoDtl(pMsg.glblCmpyCd.getValue(), apiPMsg.cpoOrdNum.getValue());

        int cnt = 0;
        for (NWZC150002PMsg pMsg02 : cpoUpdApiOutMsgList) {
            CPO_DTLTMsg dtlTMsg = null;
            for (int i = 0; i < cpoDtlArray.getValidCount(); i++) {
                CPO_DTLTMsg targetDtlTMsg = cpoDtlArray.no(i);
                if (targetDtlTMsg.cpoDtlLineNum.getValue().equals(pMsg02.cpoDtlLineNum.getValue()) && targetDtlTMsg.cpoDtlLineSubNum.getValue().equals(pMsg02.cpoDtlLineSubNum.getValue())) {
                    dtlTMsg = targetDtlTMsg;
                    break;
                }
            }
            if (dtlTMsg == null) {
                continue;
            }
            NWZC185001_OrdLineListPMsg linePMsg = getTarget(pMsg, paramBean, pMsg02);
            NWZC185001_OrdLineReturnListPMsg lineRtnPMsg = pMsg.OrdLineReturnList.no(cnt);

            ZYPEZDItemValueSetter.setValue(lineRtnPMsg.ordSrcRefLineNum, linePMsg.ordSrcRefLineNum);
            ZYPEZDItemValueSetter.setValue(lineRtnPMsg.dsCpoLineNum, dtlTMsg.dsCpoLineNum);
            ZYPEZDItemValueSetter.setValue(lineRtnPMsg.dsCpoLineSubNum, dtlTMsg.dsCpoLineSubNum);
            cnt++;
        }
        pMsg.OrdLineReturnList.setValidCount(cnt);

        // 2023/09/20 QC#61782 Add Start
        if (!validateEmailAddr(pMsg.endUsrNm.getValue())) {
            sendEmailAddrFormatError(pMsg.glblCmpyCd.getValue(), ZYPDateUtil.getCurrentSystemTime("MM/dd/yyyy HH:mm:ss.SSS"), pMsg.cpoOrdNum.getValue(), pMsg.ordSrcRefNum.getValue());
        }
        // 2023/09/20 QC#61782 Add End
        return true;
    }

    private void setPrcElement(NWZC157001PMsg basePricePMsg, NWZC157001PMsg prcApiPMsg) {
        int cnt = prcApiPMsg.NWZC157002PMsg.getValidCount();
        for (int i = 0; i < cnt; i++) {
            NWZC157002PMsg nwzc157002PMsg = prcApiPMsg.NWZC157002PMsg.no(i);

            int xCnt = basePricePMsg.NWZC157002PMsg.getValidCount();
            NWZC157002PMsg baseNWZC157002PMsg = null;
            for (int j = 0; j < xCnt; j++) {
                if (nwzc157002PMsg.trxLineNum.getValue().equals(basePricePMsg.NWZC157002PMsg.no(j).trxLineNum.getValue()) && nwzc157002PMsg.trxLineSubNum.getValue().equals(basePricePMsg.NWZC157002PMsg.no(j).trxLineSubNum.getValue())) {
                    baseNWZC157002PMsg = basePricePMsg.NWZC157002PMsg.no(j);
                    break;
                }
            }
            if (baseNWZC157002PMsg == null) {
                continue;
            }

            int validCnt = baseNWZC157002PMsg.NWZC157003PMsg.getValidCount();
            for (int k = 0; k < validCnt; k++) {
                NWZC157003PMsg basePrcElementPMsg = baseNWZC157002PMsg.NWZC157003PMsg.no(k);
                NWZC157003PMsg prcElementPMsg = nwzc157002PMsg.NWZC157003PMsg.no(k);
                EZDMsg.copy(basePrcElementPMsg, null, prcElementPMsg, null);
            }
            nwzc157002PMsg.NWZC157003PMsg.setValidCount(validCnt);
        }
    }

    private NWZC150001PMsg getApiParam(ParamBean paramBean) {
        NWZC185001PMsg pMsg = (NWZC185001PMsg) msgMap.getPmsg();

        NWZC150001PMsg apiPMsg = new NWZC150001PMsg();

        ZYPEZDItemValueSetter.setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(apiPMsg.xxModeCd, NWZC150001Constant.MODE_SUBMIT);
        ZYPEZDItemValueSetter.setValue(apiPMsg.slsDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(apiPMsg.usrId, NWZC185001Constant.BIZ_ID);
        ZYPEZDItemValueSetter.setValue(apiPMsg.bizAppId, NWZC185001Constant.BIZ_ID);

        ZYPEZDItemValueSetter.setValue(apiPMsg.cpoOrdNum, "");
        ZYPEZDItemValueSetter.setValue(apiPMsg.cpoOrdTpCd, paramBean.getCpoOrdTpCd());
        ZYPEZDItemValueSetter.setValue(apiPMsg.dsOrdCatgCd, paramBean.getDsOrdCatgCd());
        ZYPEZDItemValueSetter.setValue(apiPMsg.dsOrdTpCd, paramBean.getDsOrdTpCd());
        ZYPEZDItemValueSetter.setValue(apiPMsg.dsOrdRsnCd, "");
        ZYPEZDItemValueSetter.setValue(apiPMsg.custIssPoNum, pMsg.custIssPoNum);
        ZYPEZDItemValueSetter.setValue(apiPMsg.custIssPoDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(apiPMsg.sysSrcCd, SYS_SRC.S21_ORDER);
        ZYPEZDItemValueSetter.setValue(apiPMsg.cpoSrcTpCd, CPO_SRC_TP.IS_WEB);
        ZYPEZDItemValueSetter.setValue(apiPMsg.ordFuflLvlCd, "");
        ZYPEZDItemValueSetter.setValue(apiPMsg.billToCustAcctCd, paramBean.getBillToCustAcctCd());
        ZYPEZDItemValueSetter.setValue(apiPMsg.billToCustCd, paramBean.getBillToCustCd());
        ZYPEZDItemValueSetter.setValue(apiPMsg.shipToCustAcctCd, paramBean.getShipToCustAcctCd());
        ZYPEZDItemValueSetter.setValue(apiPMsg.sellToCustCd, paramBean.getSellToCustCd());
        ZYPEZDItemValueSetter.setValue(apiPMsg.soldToCustLocCd, paramBean.getSoldToCustLocCd());
        ZYPEZDItemValueSetter.setValue(apiPMsg.adminPsnCd, NWZC185001Constant.BIZ_ID);
        ZYPEZDItemValueSetter.setValue(apiPMsg.addRddDt, "");
        ZYPEZDItemValueSetter.setValue(apiPMsg.addRsdDt, "");
        ZYPEZDItemValueSetter.setValue(apiPMsg.addShpgSvcLvlCd, pMsg.shpgSvcLvlCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.addDropShipFlg, pMsg.dropShipFlg);
        ZYPEZDItemValueSetter.setValue(apiPMsg.addShipToCustCd, paramBean.getShipToCustCd());
        // Add Start 2017/10/26 QC#22020
        ZYPEZDItemValueSetter.setValue(apiPMsg.sellToFirstRefCmntTxt, pMsg.firstRefCmntTxt.getValue());
        // Add End 2017/10/26 QC#22020
        if (ZYPConstant.FLG_ON_Y.equals(pMsg.dropShipFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipToLocNm, pMsg.shipToLocNm.getValue());
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipToAddlLocNm, pMsg.shipToAddlLocNm.getValue());
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipToFirstLineAddr, pMsg.shipToFirstLineAddr.getValue());
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipToScdLineAddr, pMsg.shipToScdLineAddr.getValue());
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipToThirdLineAddr, pMsg.shipToThirdLineAddr.getValue());
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipToFrthLineAddr, pMsg.shipToFrthLineAddr.getValue());
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipToCtyAddr, pMsg.shipToCtyAddr.getValue());
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipToStCd, pMsg.shipToStCd.getValue());
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipToProvNm, pMsg.shipToProvNm.getValue());
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipToPostCd, pMsg.shipToPostCd.getValue());
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipToCtryCd, pMsg.shipToCtryCd.getValue());
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipToCntyNm, pMsg.shipToCntyNm.getValue());
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipTo01RefCmntTxt, pMsg.firstRefCmntTxt.getValue());
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipTo02RefCmntTxt, pMsg.scdRefCmntTxt.getValue());
        } else {
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipToLocNm, paramBean.getShipToLocNm());
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipToAddlLocNm, paramBean.getShipToAddlLocNm());
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipToFirstLineAddr, paramBean.getShipToFirstLineAddr());
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipToScdLineAddr, paramBean.getShipToScdLineAddr());
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipToThirdLineAddr, paramBean.getShipToThirdLineAddr());
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipToFrthLineAddr, paramBean.getShipToFrthLineAddr());
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipToCtyAddr, paramBean.getShipToCtyAddr());
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipToStCd, paramBean.getShipToStCd());
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipToProvNm, paramBean.getShipToProvNm());
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipToPostCd, paramBean.getShipToPostCd());
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipToCtryCd, paramBean.getShipToCtryCd());
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipToCntyNm, paramBean.getShipToCntyNm());
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipTo01RefCmntTxt, paramBean.getShipToFirstRefCmntTxt());
            ZYPEZDItemValueSetter.setValue(apiPMsg.addShipTo02RefCmntTxt, paramBean.getShipToScdRefCmntTxt());
        }

        ZYPEZDItemValueSetter.setValue(apiPMsg.addPmtTermCashDiscCd, "");
        ZYPEZDItemValueSetter.setValue(apiPMsg.carrAcctNum, pMsg.carrAcctNum);
        ZYPEZDItemValueSetter.setValue(apiPMsg.ordSgnDt, "");

        ZYPEZDItemValueSetter.setValue(apiPMsg.slsRepCd, paramBean.getSlsRepOrSlsTeamTocCd());
        ZYPEZDItemValueSetter.setValue(apiPMsg.prcBaseDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(apiPMsg.prcCalcDt, this.slsDt);
        apiPMsg.negoDealAmt.clear();
        ZYPEZDItemValueSetter.setValue(apiPMsg.prcCatgCd, paramBean.getPrcCatgCd());
        ZYPEZDItemValueSetter.setValue(apiPMsg.flPrcListCd, paramBean.getDefPrcCatgCd());
        ZYPEZDItemValueSetter.setValue(apiPMsg.csmpContrNum, "");
        ZYPEZDItemValueSetter.setValue(apiPMsg.prcContrNum, "");
        ZYPEZDItemValueSetter.setValue(apiPMsg.aquNum, "");
        ZYPEZDItemValueSetter.setValue(apiPMsg.ordSrcImptDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(apiPMsg.ordSrcRefNum, pMsg.ordSrcRefNum);
        apiPMsg.loanPerDaysAot.clear();
        ZYPEZDItemValueSetter.setValue(apiPMsg.leaseCmpyPoNum, "");
        ZYPEZDItemValueSetter.setValue(apiPMsg.leasePrchOptCd, "");
        ZYPEZDItemValueSetter.setValue(apiPMsg.leaseTermCd, "");
        ZYPEZDItemValueSetter.setValue(apiPMsg.leasePmtFreqCd, "");
        ZYPEZDItemValueSetter.setValue(apiPMsg.ordLogTpCd, "");
        ZYPEZDItemValueSetter.setValue(apiPMsg.dlrRefNum, "");
        ZYPEZDItemValueSetter.setValue(apiPMsg.frtCondCd, pMsg.frtCondCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.carrSvcLvlCd, pMsg.carrSvcLvlCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.spclHdlgTpCd, pMsg.spclHdlgTpCd);
        ZYPEZDItemValueSetter.setValue(apiPMsg.prePmtChkNum, "");
        apiPMsg.prePmtAmt.clear();
        ZYPEZDItemValueSetter.setValue(apiPMsg.prePmtTpCd, "");
        ZYPEZDItemValueSetter.setValue(apiPMsg.crRebilRsnCatgCd, "");
        apiPMsg.dsCrCardPk.clear();
        ZYPEZDItemValueSetter.setValue(apiPMsg.xxValUpdFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(apiPMsg.orgRqstDelyDt, "");
        ZYPEZDItemValueSetter.setValue(apiPMsg.sendInvFlg, "");
        ZYPEZDItemValueSetter.setValue(apiPMsg.invCmntTxt, "");

        setConfigUpdApiReqDtlPMsg(pMsg, apiPMsg, paramBean);

        setCpoUpdApiReqSalesCredit(pMsg, apiPMsg, paramBean);

        setCpoUpdApiReqDeliveryInfo(pMsg, apiPMsg, paramBean);

        setCpoCtacPsnInfo(pMsg, apiPMsg, paramBean);

        setCpoUpdApiReqPriceCalcBase(pMsg, apiPMsg, paramBean);
        return apiPMsg;
    }

    private void setCpoUpdApiReqSalesCredit(NWZC185001PMsg pMsg, NWZC150001PMsg apiPMsg, ParamBean paramBean) {
        NWZC150001_cpoSlsCrPMsg cpoSlsCr = apiPMsg.cpoSlsCr.no(0);
        ZYPEZDItemValueSetter.setValue(cpoSlsCr.xxRqstTpCd, NWZC150001Constant.RQST_TP_SLS_CR_NEW);
        ZYPEZDItemValueSetter.setValue(cpoSlsCr.dsOrdPosnNum, NWZC185001Constant.POSN_NUM);
        ZYPEZDItemValueSetter.setValue(cpoSlsCr.configCatgCd, CONFIG_CATG.OUTBOUND);
        ZYPEZDItemValueSetter.setValue(cpoSlsCr.slsRepCd, paramBean.getSlsRepOrSlsTeamTocCd());
        ZYPEZDItemValueSetter.setValue(cpoSlsCr.slsRepRoleTpCd, paramBean.getLineBizRoleTpCd());
        ZYPEZDItemValueSetter.setValue(cpoSlsCr.slsRepCrPct, new BigDecimal(100));
        ZYPEZDItemValueSetter.setValue(cpoSlsCr.slsCrQuotFlg, ZYPConstant.FLG_ON_Y);
        apiPMsg.cpoSlsCr.setValidCount(1);
    }

    private void setCpoUpdApiReqDeliveryInfo(NWZC185001PMsg pMsg, NWZC150001PMsg apiPMsg, ParamBean paramBean) {
        NWZC150001_cpoDlvyInfoListPMsg dlvyInfo = apiPMsg.cpoDlvyInfoList.no(0);
        ZYPEZDItemValueSetter.setValue(dlvyInfo.xxRqstTpCd, NWZC150001Constant.RQST_TP_DELY_INFO_NEW);
        ZYPEZDItemValueSetter.setValue(dlvyInfo.configCatgCd, CONFIG_CATG.OUTBOUND);
        String shpgCmntTxt = pMsg.shpgCmntTxt.getValue();
        if (shpgCmntTxt.length() > DELY_ADDL_CMNT_TXT_MAX_SIZE) {
            shpgCmntTxt = shpgCmntTxt.substring(0, DELY_ADDL_CMNT_TXT_MAX_SIZE);
        }
        // 2018/02/08 S21_NA#20297(Sol#379) Add Start
        if(!hasValue(shpgCmntTxt)){
            shpgCmntTxt = getDefShpgCmt(pMsg, paramBean);
        }
        // 2018/02/08 S21_NA#20297(Sol#379) Add End
        ZYPEZDItemValueSetter.setValue(dlvyInfo.delyAddlCmntTxt, shpgCmntTxt);
        apiPMsg.cpoDlvyInfoList.setValidCount(1);
    }

    private void setCpoCtacPsnInfo(NWZC185001PMsg pMsg, NWZC150001PMsg apiPMsg, ParamBean paramBean) {

        CTAC_PSNTMsg ctacPsnTMsg = getCtacPsn(pMsg.glblCmpyCd.getValue(), pMsg.ctacPsnPk.getValue());
        //String ctacTpCd = getDsCtacPsnReln(pMsg.glblCmpyCd.getValue(), pMsg.ctacPsnPk.getValue()); // S21_NA DEL QC#20246(Sol#454)

        NWZC150001_cpoCtacPsnInfoListPMsg ctacPsnInfo = apiPMsg.cpoCtacPsnInfoList.no(0);
        ZYPEZDItemValueSetter.setValue(ctacPsnInfo.xxRqstTpCd, NWZC150001Constant.RQST_TP_CTAC_PSN_NEW);
        ZYPEZDItemValueSetter.setValue(ctacPsnInfo.dsOrdPosnNum, NWZC185001Constant.POSN_NUM);
        ZYPEZDItemValueSetter.setValue(ctacPsnInfo.configCatgCd, CONFIG_CATG.OUTBOUND);
        ZYPEZDItemValueSetter.setValue(ctacPsnInfo.ctacTpCd, CTAC_TP.ORDER_CONTACT); // S21_NA MOD QC#20246(Sol#454)
        ZYPEZDItemValueSetter.setValue(ctacPsnInfo.ctacPsnPk, ctacPsnTMsg.ctacPsnPk); // S21_NA MOD QC#20246(Sol#454)
        ZYPEZDItemValueSetter.setValue(ctacPsnInfo.firstNm, ctacPsnTMsg.ctacPsnFirstNm);
        ZYPEZDItemValueSetter.setValue(ctacPsnInfo.lastNm, ctacPsnTMsg.ctacPsnLastNm);
        ZYPEZDItemValueSetter.setValue(ctacPsnInfo.sortNum, BigDecimal.ONE);
        // 2018/09/19 QC#28284 Add Start
        String ctacCustRefTpCd = getCtacCustRefTpCd(pMsg.glblCmpyCd.getValue(), ctacPsnInfo.ctacTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(ctacPsnInfo.ctacCustRefTpCd, ctacCustRefTpCd);
        // 2018/09/19 QC#28284 Add End

        String telNum = "";
        String ctacPsnExtnNum = "";
        String faxNum = "";
        String emlAddr = "";
        List<Map<String, Object>> ctacPntList = getCtacPnt(pMsg.glblCmpyCd.getValue(), pMsg.ctacPsnPk.getValue());
        if (ctacPntList != null && ctacPntList.size() > 0) {
            for (Map<String, Object> map : ctacPntList) {
                if (DS_CTAC_PNT_TP.EMAIL_WORK.equals((String) map.get("DS_CTAC_PNT_TP_CD"))
                        && map.get("DS_CTAC_PNT_VAL_TXT") != null) {
                    emlAddr = (String) map.get("DS_CTAC_PNT_VAL_TXT");
                } else if (DS_CTAC_PNT_TP.FAX_WORK.equals((String) map.get("DS_CTAC_PNT_TP_CD"))
                        && map.get("DS_CTAC_PNT_VAL_TXT") != null) {
                    faxNum = (String) map.get("DS_CTAC_PNT_VAL_TXT");
                } else if (DS_CTAC_PNT_TP.PHONE_WORK.equals((String) map.get("DS_CTAC_PNT_TP_CD"))
                        && map.get("DS_CTAC_PNT_VAL_TXT") != null) {
                    telNum = (String) map.get("DS_CTAC_PNT_VAL_TXT");
                    ctacPsnExtnNum = (String) map.get("DS_CTAC_PSN_EXTN_NUM");
                }
            }
        }

        ZYPEZDItemValueSetter.setValue(ctacPsnInfo.telNum, telNum);
        ZYPEZDItemValueSetter.setValue(ctacPsnInfo.ctacPsnExtnNum, ctacPsnExtnNum);
        ZYPEZDItemValueSetter.setValue(ctacPsnInfo.faxNum, faxNum);
        ZYPEZDItemValueSetter.setValue(ctacPsnInfo.emlAddr, emlAddr);
        
        // Del Start 2017/10/26 QC#22020
        //ZYPEZDItemValueSetter.setValue(apiPMsg.sellToFirstRefCmntTxt, ctacPsnTMsg.ctacPsnFirstNm.getValue() + " " + ctacPsnTMsg.ctacPsnLastNm.getValue()); // S21_NA MOD QC#20246(Sol#454)
        // Del End 2017/10/26 QC#22020
        
        // Add Start 2017/10/26 QC#21590
        NWZC150001_cpoCtacPsnInfoListPMsg ctacPsnInfoForHeader = apiPMsg.cpoCtacPsnInfoList.no(1);
        EZDMsg.copy(ctacPsnInfo, null, ctacPsnInfoForHeader, null);
        ctacPsnInfoForHeader.configCatgCd.clear();
        // Add End 2017/10/26 QC#21590

        // Mod Start 2017/10/26 QC#21590
        //apiPMsg.cpoCtacPsnInfoList.setValidCount(1);
        apiPMsg.cpoCtacPsnInfoList.setValidCount(2);
        // Mod End 2017/10/26 QC#21590
        
        // 2023/09/20 QC#61782 Add Start
        NWZC150001_cpoCtacPsnInfoListPMsg ctacPsnInfoForMyCsaUser = apiPMsg.cpoCtacPsnInfoList.no(2);
        if (ZYPCommonFunc.hasValue(pMsg.endUsrNm)) {
            String emlAddrForMyCSAUser = pMsg.endUsrNm.getValue();
            if (validateEmailAddr(emlAddrForMyCSAUser)
                && hasVarCharConstSetUp(pMsg.glblCmpyCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(ctacPsnInfoForMyCsaUser.xxRqstTpCd, NWZC150001Constant.RQST_TP_CTAC_PSN_NEW);
                ZYPEZDItemValueSetter.setValue(ctacPsnInfoForMyCsaUser.ctacTpCd, CTAC_TP.MYCSAUSER);
                ZYPEZDItemValueSetter.setValue(ctacPsnInfoForMyCsaUser.emlAddr, emlAddrForMyCSAUser);
                ZYPEZDItemValueSetter.setValue(ctacPsnInfoForMyCsaUser.configCatgCd, CONFIG_CATG.OUTBOUND);
                ZYPEZDItemValueSetter.setValue(ctacPsnInfoForMyCsaUser.dsOrdPosnNum, NWZC185001Constant.POSN_NUM);
                ZYPEZDItemValueSetter.setValue(ctacPsnInfoForMyCsaUser.firstNm, ZYPCodeDataUtil.getVarCharConstValue(MYCSAUSER_FIRST_NM, pMsg.glblCmpyCd.getValue()));
                ZYPEZDItemValueSetter.setValue(ctacPsnInfoForMyCsaUser.lastNm, ZYPCodeDataUtil.getVarCharConstValue(MYCSAUSER_LAST_NM, pMsg.glblCmpyCd.getValue()));
                ZYPEZDItemValueSetter.setValue(ctacPsnInfoForMyCsaUser.telNum, ZYPCodeDataUtil.getVarCharConstValue(MYCSAUSER_TEL_NUM, pMsg.glblCmpyCd.getValue()));
                ZYPEZDItemValueSetter.setValue(ctacPsnInfoForMyCsaUser.sortNum, SORT_NUM_MYCSAUSER);
                int validCount = apiPMsg.cpoCtacPsnInfoList.getValidCount();
                validCount++;
                apiPMsg.cpoCtacPsnInfoList.setValidCount(validCount);
            }
        }
        // 2023/09/20 QC#61782 Add End
    }

    private void setCpoUpdApiReqPriceCalcBase(NWZC185001PMsg pMsg, NWZC150001PMsg apiPMsg, ParamBean paramBean) {
        int priceListPMsgCount = 0;

        for (NWZC157003PMsg pMsg03 : paramBean.getPrcElemList()) {
            NWZC150001_priceListPMsg priceListPMsg = apiPMsg.priceList.no(priceListPMsgCount);

            if (!CONFIG_CATG.OUTBOUND.equals(pMsg03.configCatgCd.getValue())) {
                continue;
            }

            EZDMsg.copy(pMsg03, null, priceListPMsg, null);
            ZYPEZDItemValueSetter.setValue(priceListPMsg.cpoDtlLineNum, pMsg03.trxLineNum);
            ZYPEZDItemValueSetter.setValue(priceListPMsg.cpoDtlLineSubNum, pMsg03.trxLineSubNum);

            priceListPMsgCount++;
        }
        apiPMsg.priceList.setValidCount(priceListPMsgCount);
    }

    private void setConfigUpdApiReqDtlPMsg(NWZC185001PMsg pMsg, NWZC150001PMsg apiPMsg, ParamBean paramBean) {

        final NWZC150001_cpoConfigPMsg cpoConfigPMsg = apiPMsg.cpoConfig.no(0);
        String posnNum = NWZC185001Constant.POSN_NUM;

        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.xxRqstTpCd, NWZC150001Constant.RQST_TP_CONFIG_NEW);
        cpoConfigPMsg.dsCpoConfigPk.clear();
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.dsOrdPosnNum, posnNum);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.configTpCd, CONFIG_TP.NEW);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.configCatgCd, CONFIG_CATG.OUTBOUND);
        cpoConfigPMsg.svcConfigMstrPk.clear();
        cpoConfigPMsg.mdlId.clear();
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.mdlDescTxt, "");
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.billToCustAcctCd, paramBean.getBillToCustAcctCd());
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.billToCustCd, paramBean.getBillToCustCd());
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCustAcctCd, paramBean.getShipToCustAcctCd());
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCustCd, paramBean.getShipToCustCd());
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.dropShipFlg, pMsg.dropShipFlg);
        if (ZYPConstant.FLG_ON_Y.equals(pMsg.dropShipFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToLocNm, pMsg.shipToLocNm);
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToAddlLocNm, pMsg.shipToAddlLocNm);
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToFirstLineAddr, pMsg.shipToFirstLineAddr);
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToScdLineAddr, pMsg.shipToScdLineAddr);
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToThirdLineAddr, pMsg.shipToThirdLineAddr);
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToFrthLineAddr, pMsg.shipToFrthLineAddr);
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipTo01RefCmntTxt, pMsg.firstRefCmntTxt);
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipTo02RefCmntTxt, pMsg.scdRefCmntTxt);
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCtyAddr, pMsg.shipToCtyAddr);
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToStCd, pMsg.shipToStCd);
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToProvNm, pMsg.shipToProvNm);
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCntyNm, pMsg.shipToCntyNm);
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToPostCd, pMsg.shipToPostCd);
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCtryCd, pMsg.shipToCtryCd);

        } else {
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToLocNm, paramBean.getShipToLocNm());
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToAddlLocNm, paramBean.getShipToAddlLocNm());
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToFirstLineAddr, paramBean.getShipToFirstLineAddr());
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToScdLineAddr, paramBean.getShipToScdLineAddr());
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToThirdLineAddr, paramBean.getShipToThirdLineAddr());
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToFrthLineAddr, paramBean.getShipToFrthLineAddr());
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipTo01RefCmntTxt, paramBean.getShipToFirstRefCmntTxt());
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipTo02RefCmntTxt, paramBean.getShipToScdRefCmntTxt());
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCtyAddr, paramBean.getShipToCtyAddr());
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToStCd, paramBean.getShipToStCd());
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToProvNm, paramBean.getShipToProvNm());
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCntyNm, paramBean.getShipToCntyNm());
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToPostCd, paramBean.getShipToPostCd());
            ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCtryCd, paramBean.getShipToCtryCd());

        }
        cpoConfigPMsg.pickSvcConfigMstrPk.clear();

        apiPMsg.cpoConfig.setValidCount(1);

        setCpoUpdApiReqDtlPMsg(pMsg, apiPMsg, paramBean, posnNum);
    }

    private void setCpoUpdApiReqDtlPMsg(NWZC185001PMsg pMsg, NWZC150001PMsg apiPMsg, ParamBean paramBean, String posnNum) {

        int cnt = pMsg.OrdLineList.getValidCount();
        // 2018/06/08 QC#26123 Add Start
        int vldCnt = 0;
        // 2018/06/08 QC#26123 Add End
        for (int i = 0; i < cnt; i++) {
            NWZC185001_OrdLineListPMsg linePMsg = pMsg.OrdLineList.no(i);
            // 2018/06/08 QC#26123 Mod Start
//            NWZC150001_APMsg dtlPMsg = apiPMsg.A.no(i);
            NWZC150001_APMsg dtlPMsg = apiPMsg.A.no(vldCnt++);
            // 2018/06/08 QC#26123 Mod End
            ParamDetailBean detailBean = getParamDetailBean(paramBean, linePMsg);

            ZYPEZDItemValueSetter.setValue(dtlPMsg.xxRqstTpCd_A1, NWZC150001Constant.RQST_TP_DTL_NEW);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.cpoDtlLineNum_A1, detailBean.getCpoDtlLineNum());
            ZYPEZDItemValueSetter.setValue(dtlPMsg.cpoDtlLineSubNum_A1, detailBean.getCpoDtlLineSubNum());

            ZYPEZDItemValueSetter.setValue(dtlPMsg.mdseCd_A1, detailBean.getMdseCd());

            // 2019/07/17 QC#51666 Mod Start
            String origMdseCd = getMnfItemCd(pMsg.glblCmpyCd.getValue(), detailBean.getMdseCd());
            ZYPEZDItemValueSetter.setValue(dtlPMsg.origMdseCd_A1, origMdseCd);
            //ZYPEZDItemValueSetter.setValue(dtlPMsg.origMdseCd_A1, linePMsg.mdseCd);
            // 2019/07/17 QC#51666 Mod End

            dtlPMsg.dropShipFlg_A1.clear();
            ZYPEZDItemValueSetter.setValue(dtlPMsg.shipToCustCd_A1, paramBean.getShipToCustCd());
            dtlPMsg.shipToLocNm_A1.clear();
            dtlPMsg.shipToAddlLocNm_A1.clear();
            dtlPMsg.shipToFirstLineAddr_A1.clear();
            dtlPMsg.shipToScdLineAddr_A1.clear();
            dtlPMsg.shipToThirdLineAddr_A1.clear();
            dtlPMsg.shipToFrthLineAddr_A1.clear();
            dtlPMsg.shipToCtyAddr_A1.clear();
            dtlPMsg.shipToStCd_A1.clear();
            dtlPMsg.shipToProvNm_A1.clear();
            dtlPMsg.shipToPostCd_A1.clear();
            dtlPMsg.shipToCtryCd_A1.clear();
            dtlPMsg.shipToCntyNm_A1.clear();
            dtlPMsg.shipToFirstRefCmntTxt_A1.clear();
            dtlPMsg.shipToScdRefCmntTxt_A1.clear();
            if (hasValue(detailBean.getInEachQty()) && BigDecimal.ZERO.compareTo(detailBean.getInEachQty()) != 0) {
                ZYPEZDItemValueSetter.setValue(dtlPMsg.ordQty_A1, linePMsg.ordQty.getValue().divide(detailBean.getInEachQty(), 0, RoundingMode.DOWN));
            } else {
                ZYPEZDItemValueSetter.setValue(dtlPMsg.ordQty_A1, linePMsg.ordQty.getValue());
            }
            ZYPEZDItemValueSetter.setValue(dtlPMsg.ordCustUomQty_A1, linePMsg.ordQty);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.invtyLocCd_A1, detailBean.getInvtyLocCd());
            dtlPMsg.entDealNetUnitPrcAmt_A1.clear();
            ZYPEZDItemValueSetter.setValue(dtlPMsg.ccyCd_A1, paramBean.getCcyCd());
            dtlPMsg.rddDt_A1.clear();
            dtlPMsg.rsdDt_A1.clear();
            dtlPMsg.shipCpltCd_A1.clear();
            dtlPMsg.stkStsCd_A1.clear();
            ZYPEZDItemValueSetter.setValue(dtlPMsg.slsRepOrSlsTeamTocCd_A1, paramBean.getSlsRepOrSlsTeamTocCd());
            dtlPMsg.custMdseCd_A1.clear();
            ZYPEZDItemValueSetter.setValue(dtlPMsg.custUomCd_A1, detailBean.getPkgUomCd());
            dtlPMsg.svcConfigMstrPk_A1.clear();
            ZYPEZDItemValueSetter.setValue(dtlPMsg.dsOrdPosnNum_A1, posnNum);
            if (hasValue(detailBean.getInPoundWt())) {
                ZYPEZDItemValueSetter.setValue(dtlPMsg.unitNetWt_A1, linePMsg.ordQty.getValue().multiply(detailBean.getInPoundWt()));
            } else {
                ZYPEZDItemValueSetter.setValue(dtlPMsg.unitNetWt_A1, linePMsg.ordQty);
            }

            NWZC157004PMsg prcInfo = detailBean.getPrcInfo();
            ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotBaseAmt_A1, prcInfo.xxTotBaseAmt);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotDiscAmt_A1, prcInfo.xxTotDiscAmt);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotSpclPrcAmt_A1, prcInfo.xxTotSpclPrcAmt);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotNetDiscAmt_A1, prcInfo.xxTotNetDiscAmt);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotNetPrcAmt_A1, prcInfo.xxTotNetPrcAmt);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotFrtAmt_A1, prcInfo.xxTotFrtAmt);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotSpclChrgAmt_A1, prcInfo.xxTotSpclChrgAmt);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotTaxAmt_A1, prcInfo.xxTotTaxAmt);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.xxSlsAmt_A1, prcInfo.xxSlsAmt);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.xxTotAmt_A1, prcInfo.xxTotAmt);

            dtlPMsg.vndInvtyLocCd_A1.clear();
            ZYPEZDItemValueSetter.setValue(dtlPMsg.frtCondCd_A1, pMsg.frtCondCd);
            dtlPMsg.dsContrNum_A1.clear();
            dtlPMsg.dsContrSqNum_A1.clear();
            dtlPMsg.dsCpoConfigPk_A1.clear();
            ZYPEZDItemValueSetter.setValue(dtlPMsg.dsOrdLineCatgCd_A1, paramBean.getDsOrdLineCatdCd());
            ZYPEZDItemValueSetter.setValue(dtlPMsg.ordLineSrcCd_A1, detailBean.getOrdLineSrcCd());
            ZYPEZDItemValueSetter.setValue(dtlPMsg.rtlWhCd_A1, detailBean.getRtlWhCd());
            ZYPEZDItemValueSetter.setValue(dtlPMsg.rtlSwhCd_A1, detailBean.getRtlSwhCd());
            dtlPMsg.serNum_A1.clear();

            ZYPEZDItemValueSetter.setValue(dtlPMsg.flPrcListCd_A1, paramBean.getDefPrcCatgCd());

            NWZC157003PMsg prcCalc = null;
            for (NWZC157003PMsg pMsg03 : paramBean.getPrcElemList()) {
                if (CONFIG_CATG.OUTBOUND.equals(pMsg03.configCatgCd.getValue()) && detailBean.getCpoDtlLineNum().equals(pMsg03.trxLineNum.getValue()) && detailBean.getCpoDtlLineSubNum().equals(pMsg03.trxLineSubNum.getValue())
                        && PRC_DTL_GRP.BASE_PRICE.equals(pMsg03.prcDtlGrpCd.getValue())) {
                    prcCalc = pMsg03;
                    break;
                }
            }
            if (prcCalc != null) {
                ZYPEZDItemValueSetter.setValue(dtlPMsg.dealPrcListPrcAmt_A1, prcCalc.autoPrcAmtRate);
                String baseDt = null;
                if (hasValue(pMsg.slsDt)) {
                    baseDt = pMsg.slsDt.getValue();
                } else {
                    baseDt = this.slsDt;
                }
                BigDecimal funcAmt = calcFuncAmt(pMsg.glblCmpyCd.getValue(), paramBean.getCcyCd(), baseDt, prcCalc.autoPrcAmtRate.getValue());
                if (funcAmt == null) {
                    ZYPEZDItemValueSetter.setValue(dtlPMsg.funcPrcListPrcAmt_A1, prcCalc.autoPrcAmtRate);
                } else {
                    ZYPEZDItemValueSetter.setValue(dtlPMsg.funcPrcListPrcAmt_A1, funcAmt);
                }
            }

            dtlPMsg.refCpoDtlLineNum_A1.clear();
            dtlPMsg.refCpoDtlLineSubNum_A1.clear();
            dtlPMsg.dplyLineRefNum_A1.clear();
            dtlPMsg.crRebilCd_A1.clear();
            ZYPEZDItemValueSetter.setValue(dtlPMsg.prcBaseDt_A1, apiPMsg.prcBaseDt);
            dtlPMsg.splyTpCd_A1.clear();
            dtlPMsg.splyNm_A1.clear();
            // 2019/10/04 S21_NA#51372 Add Start
            dtlPMsg.prntVndNm_A1.clear();
            // 2019/10/04 S21_NA#51372 Add End
            dtlPMsg.splyFirstAddr_A1.clear();
            dtlPMsg.splyCtyAddr_A1.clear();
            dtlPMsg.splyStCd_A1.clear();
            dtlPMsg.splyPostCd_A1.clear();
            dtlPMsg.csmpContrNum_A1.clear();
            dtlPMsg.dlrRefNum_A1.clear();
            dtlPMsg.csmpPrcListCd_A1.clear();
            dtlPMsg.rntlTrmnDt_A1.clear();
            dtlPMsg.bllgAttrbCustAcctCd_A1.clear();
            dtlPMsg.firstBllgAttrbNm_A1.clear();
            dtlPMsg.firstBllgAttrbValTxt_A1.clear();
            dtlPMsg.scdBllgAttrbNm_A1.clear();
            dtlPMsg.scdBllgAttrbValTxt_A1.clear();
            dtlPMsg.thirdBllgAttrbNm_A1.clear();
            dtlPMsg.thirdBllgAttrbValTxt_A1.clear();
            dtlPMsg.frthBllgAttrbNm_A1.clear();
            dtlPMsg.frthBllgAttrbValTxt_A1.clear();
            dtlPMsg.fifthBllgAttrbNm_A1.clear();
            dtlPMsg.fifthBllgAttrbValTxt_A1.clear();
            dtlPMsg.sixthBllgAttrbNm_A1.clear();
            dtlPMsg.sixthBllgAttrbValTxt_A1.clear();
            dtlPMsg.sbstMdseCd_A1.clear();
            ZYPEZDItemValueSetter.setValue(dtlPMsg.ordSrcRefLineNum_A1, linePMsg.ordSrcRefLineNum);
            dtlPMsg.ordSrcRefLineSubNum_A1.clear();
            ZYPEZDItemValueSetter.setValue(dtlPMsg.carrSvcLvlCd_A1, pMsg.carrSvcLvlCd);
            // Mod Start 2017/10/26 QC#21590
            //ZYPEZDItemValueSetter.setValue(dtlPMsg.supdLockFlg_A1, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(dtlPMsg.supdLockFlg_A1, ZYPConstant.FLG_OFF_N);
            // Mod End 2017/10/26 QC#21590
            dtlPMsg.prcListEquipConfigNum_A1.clear();
            ZYPEZDItemValueSetter.setValue(dtlPMsg.prcCatgCd_A1, paramBean.getPrcCatgCd());
            dtlPMsg.loanBalQty_A1.clear();
            dtlPMsg.funcSvcRevTrnsfAmt_A1.clear();
            dtlPMsg.dealSvcRevTrnsfAmt_A1.clear();
            dtlPMsg.funcSvcCostTrnsfAmt_A1.clear();
            dtlPMsg.dealSvcCostTrnsfAmt_A1.clear();
            dtlPMsg.funcManFlAdjAmt_A1.clear();
            dtlPMsg.dealManFlAdjAmt_A1.clear();
            dtlPMsg.funcManRepRevAdjAmt_A1.clear();
            dtlPMsg.dealManRepRevAdjAmt_A1.clear();
            dtlPMsg.shopItemFlg_A1.clear();
            dtlPMsg.ordUpldRefNum_A1.clear();
            ZYPEZDItemValueSetter.setValue(dtlPMsg.baseCmptFlg_A1, ZYPConstant.FLG_OFF_N);
            dtlPMsg.svcMachMstrPk_A1.clear();

            // 2018/06/08 QC#26123 Add Start
            List<ParamDetailBean> childBeanList = getParamChildDetailBean(paramBean, linePMsg.ordSrcRefLineNum.getValue());
            if (!childBeanList.isEmpty()) {
                for (ParamDetailBean bean : childBeanList) {
                    ParamChildDetailBean childBean = (ParamChildDetailBean) bean;

                    NWZC150001_APMsg dtlPMsgChild = apiPMsg.A.no(vldCnt++);
                    EZDMsg.copy(dtlPMsg, null, dtlPMsgChild, null);

                    ZYPEZDItemValueSetter.setValue(dtlPMsgChild.cpoDtlLineNum_A1, childBean.getCpoDtlLineNum());
                    ZYPEZDItemValueSetter.setValue(dtlPMsgChild.cpoDtlLineSubNum_A1, childBean.getCpoDtlLineSubNum());
                    ZYPEZDItemValueSetter.setValue(dtlPMsgChild.mdseCd_A1, childBean.getMdseCd());
                    ZYPEZDItemValueSetter.setValue(dtlPMsgChild.origMdseCd_A1, childBean.getOrigMdseCd());
                    ZYPEZDItemValueSetter.setValue(dtlPMsgChild.custUomCd_A1, childBean.getPkgUomCd());
                    ZYPEZDItemValueSetter.setValue(dtlPMsgChild.ordQty_A1, childBean.getOrdQty().multiply(linePMsg.ordQty.getValue()));
                    if (hasValue(childBean.getInEachQty()) && BigDecimal.ZERO.compareTo(childBean.getInEachQty()) != 0) {
                        ZYPEZDItemValueSetter.setValue(dtlPMsgChild.ordQty_A1, dtlPMsgChild.ordQty_A1.getValue().divide(childBean.getInEachQty(), 0, RoundingMode.DOWN));
                    }
                    ZYPEZDItemValueSetter.setValue(dtlPMsgChild.ordCustUomQty_A1, childBean.getOrdQty().multiply(linePMsg.ordQty.getValue()));
                    ZYPEZDItemValueSetter.setValue(dtlPMsgChild.custUomCd_A1, childBean.getPkgUomCd());
                    if (hasValue(childBean.getInPoundWt())) {
                        ZYPEZDItemValueSetter.setValue(dtlPMsgChild.unitNetWt_A1, dtlPMsgChild.ordCustUomQty_A1.getValue().multiply(childBean.getInPoundWt()));
                    } else {
                        ZYPEZDItemValueSetter.setValue(dtlPMsgChild.unitNetWt_A1, dtlPMsgChild.ordCustUomQty_A1);
                    }

                    ZYPEZDItemValueSetter.setValue(dtlPMsgChild.xxTotBaseAmt_A1, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(dtlPMsgChild.xxTotDiscAmt_A1, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(dtlPMsgChild.xxTotSpclPrcAmt_A1, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(dtlPMsgChild.xxTotNetDiscAmt_A1, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(dtlPMsgChild.xxTotNetPrcAmt_A1, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(dtlPMsgChild.xxTotFrtAmt_A1, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(dtlPMsgChild.xxTotSpclChrgAmt_A1, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(dtlPMsgChild.xxTotTaxAmt_A1, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(dtlPMsgChild.xxSlsAmt_A1, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(dtlPMsgChild.xxTotAmt_A1, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(dtlPMsgChild.dealPrcListPrcAmt_A1, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(dtlPMsgChild.funcPrcListPrcAmt_A1, BigDecimal.ZERO);
                    ZYPEZDItemValueSetter.setValue(dtlPMsgChild.funcUnitFlPrcAmt_A1, BigDecimal.ZERO);
                }
            }
            // 2018/06/08 QC#26123 Add End
        }
        // 2018/06/08 QC#26123 Mod Start
//        apiPMsg.A.setValidCount(cnt);
        apiPMsg.A.setValidCount(vldCnt);
        // 2018/06/08 QC#26123 Mod End
    }

    private BigDecimal calcFuncAmt(String glblCmpyCd, String dealCcyCd, String slsDt, BigDecimal dealAmt) {

        NWXC001001ExchangeData exchData = new NWXC001001ExchangeData();
        exchData.setGlblCmpyCd(glblCmpyCd);
        exchData.setCcyCd(dealCcyCd);
        exchData.setSlsDt(slsDt);
        List<NWXC001001ExchangePriceData> priceDataList = new ArrayList<NWXC001001ExchangePriceData>();
        NWXC001001ExchangeAmoutData exchAmtData = new NWXC001001ExchangeAmoutData();

        NWXC001001ExchangeAmount grsAmt = new NWXC001001ExchangeAmount();
        grsAmt.setDealAmt(dealAmt);

        exchAmtData.setGrsAmt(grsAmt);
        priceDataList.add(exchAmtData);

        exchData.setPriceData(priceDataList);

        NWXC001001Exchange.exchFuncUnitPrice(exchData);
        if (!exchData.getXxMsgIdList().isEmpty()) {
            return null;
        }
        BigDecimal funcAmt = null;
        for (NWXC001001ExchangePriceData prcData : exchData.getPriceData()) {
            for (NWXC001001ExchangeAmount amount : prcData.getAmountList()) {
                funcAmt = amount.getFuncAmt();
            }
        }
        return funcAmt;
    }

    private ParamDetailBean getParamDetailBean(ParamBean paramBean, NWZC185001_OrdLineListPMsg linePMsg) {
        return getParamDetailBean(paramBean, linePMsg.ordSrcRefLineNum.getValue());
    }

    private ParamDetailBean getParamDetailBean(ParamBean paramBean, String ordSrcRefLineNum) {
        ParamDetailBean result = null;
        for (ParamDetailBean detailBean : paramBean.getDetailBeanList()) {
            // 2018/06/08 QC#26123 Add Start
            if (detailBean instanceof ParamChildDetailBean) {
                continue;
            }
            // 2018/06/08 QC#26123 Add End
            if (detailBean.getOrdSrcRefLineNum().equals(ordSrcRefLineNum)) {
                result = detailBean;
                break;
            }
        }
        return result;
    }

    private ParamDetailBean getParamDetailBean(ParamBean paramBean, String cpoDtlLineNum, String cpoDtlLineSubNum) {
        ParamDetailBean result = null;
        for (ParamDetailBean detailBean : paramBean.getDetailBeanList()) {
            if (detailBean.getCpoDtlLineNum().equals(cpoDtlLineNum) && detailBean.getCpoDtlLineSubNum().equals(cpoDtlLineSubNum)) {
                result = detailBean;
                break;
            }
        }
        return result;
    }

    private String getNextCpoDtlLineNum(String lineNum) {

        char[] digit1 = S21StringUtil.subStringByLength(lineNum, 0, 1).toCharArray();
        int digit23 = Integer.parseInt(S21StringUtil.subStringByLength(lineNum, 1, 2));

        digit23++;
        digit23 = digit23 % 100;
        if (digit23 == 0) {
            if (digit1[0] == 0x0039) {
                digit1[0] = 0x0041;
            } else {
                digit1[0]++;
            }
        }

        return String.valueOf(digit1) + ZYPCommonFunc.leftPad(String.valueOf(digit23), 2, "0");
    }

    private ParamBean getParamBean() {
        NWZC185001PMsg pMsg = (NWZC185001PMsg) msgMap.getPmsg();
        ParamBean bean = new ParamBean();

        getSoldTo(pMsg, bean);

        getShipTo(pMsg, bean);

        getBillTo(pMsg, bean);

        getOrdCatgAndOrdTp(pMsg, bean);
        if (hasErr()) {
            return null;
        }

        getLineBizTpCd(pMsg, bean);
        if (hasErr()) {
            return null;
        }

        getDsOrdLineCatg(pMsg, bean);
        if (hasErr()) {
            return null;
        }

        List<String> writers = getWriterLineBizRoleTp(pMsg.glblCmpyCd.getValue());
        getSlsRep(pMsg, bean, writers);
        if (hasErr()) {
            return null;
        }

        List<ParamDetailBean> list = new ArrayList<ParamDetailBean>();
        bean.setDetailBeanList(list);
        int cnt = pMsg.OrdLineList.getValidCount();
        String cpoDtlLineNum = "000";
        for (int i = 0; i < cnt; i++) {
            NWZC185001_OrdLineListPMsg ordLine = pMsg.OrdLineList.no(i);
            ParamDetailBean detailBean = new ParamDetailBean();
            detailBean.setOrdSrcRefLineNum(ordLine.ordSrcRefLineNum.getValue());

            if (!getMdseCd(detailBean, ordLine, pMsg, bean.getSellToCustCd())) {
                continue;
            }

            getDefWhCd(bean, detailBean, ordLine, pMsg, "");

            cpoDtlLineNum = getNextCpoDtlLineNum(cpoDtlLineNum);
            detailBean.setCpoDtlLineNum(cpoDtlLineNum);
            // 2018/06/08 QC#26123 Mod Start
//            detailBean.setCpoDtlLineSubNum(NWZC185001Constant.SUB_NUM);
            if (isSet(pMsg.glblCmpyCd.getValue(), detailBean.getMdseCd())) {
                detailBean.setCpoDtlLineSubNum(NWZC185001Constant.PRNT_SUB_NUM);
            } else {
                detailBean.setCpoDtlLineSubNum(NWZC185001Constant.SUB_NUM);
            }
            // 2018/06/08 QC#26123 Mod End

            list.add(detailBean);

            // 2018/06/08 QC#26123 Add Start
            if (isSet(pMsg.glblCmpyCd.getValue(), detailBean.getMdseCd())) {

                List<ParamChildDetailBean> childDtlBeanList = getChildDtlBeanList(pMsg, detailBean);
                list.addAll(childDtlBeanList);
            }
            // 2018/06/08 QC#26123 Add End
        }

        return bean;
    }

    private boolean setPriceInfo(ParamBean bean) {

        NWZC185001PMsg pMsg = (NWZC185001PMsg) msgMap.getPmsg();

        getPrcList(pMsg, bean);
        if (hasErr()) {
            return false;
        }

        NWZC157001PMsg basePricePMsg = callPricingApiForBasePrice(bean);
        if (hasErr()) {
            return false;
        }

        String modeCd = NWZC157001.GET_ORDER_ALL;

        NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();
        setHdrParam(prcApiPMsg, modeCd, bean);
        setLineParam(prcApiPMsg, modeCd, bean);

        setPrcElement(basePricePMsg, prcApiPMsg);

        new NWZC157001().execute(prcApiPMsg, this.onBatType);
        if (S21ApiUtil.isXxMsgId(prcApiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(prcApiPMsg);
            for (S21ApiMessage msg : msgList) {
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                setErrMsg(pMsg, msgId, msgPrms);
            }
        }

        for (int i = 0; i < prcApiPMsg.NWZC157002PMsg.getValidCount(); i++) {
            if (S21ApiUtil.isXxMsgId(prcApiPMsg.NWZC157002PMsg.no(i))) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(prcApiPMsg.NWZC157002PMsg.no(i));
                for (S21ApiMessage msg : msgList) {
                    ParamDetailBean detailBean = getParamDetailBean(bean, prcApiPMsg.NWZC157002PMsg.no(i).trxLineNum.getValue(), prcApiPMsg.NWZC157002PMsg.no(i).trxLineSubNum.getValue());
                    NWZC185001_OrdLineListPMsg linePMsg = getTarget(pMsg, detailBean.getOrdSrcRefLineNum());
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    setDetailErrMsg(pMsg, linePMsg, msgId, msgPrms);
                }
            }
        }
        if (hasErr()) {
            return false;
        }

        List<NWZC157003PMsg> prcElemList = new ArrayList<NWZC157003PMsg>();
        for (int i = 0; i < prcApiPMsg.NWZC157002PMsg.getValidCount(); i++) {
            NWZC157002PMsg pMsg02 = prcApiPMsg.NWZC157002PMsg.no(i);
            for (int j = 0; j < pMsg02.NWZC157003PMsg.getValidCount(); j++) {
                NWZC157003PMsg pMsg03 = pMsg02.NWZC157003PMsg.no(j);
                NWZC157003PMsg prcElem = new NWZC157003PMsg();
                EZDMsg.copy(pMsg03, null, prcElem, null);
                prcElemList.add(prcElem);
            }
        }
        bean.setPrcElemList(prcElemList);

        for (int i = 0; i < prcApiPMsg.NWZC157004PMsg.getValidCount(); i++) {
            NWZC157004PMsg prcInfo = new NWZC157004PMsg();
            EZDMsg.copy(prcApiPMsg.NWZC157004PMsg.no(i), null, prcInfo, null);

            ParamDetailBean detailBean = getParamDetailBean(bean, prcApiPMsg.NWZC157004PMsg.no(i).trxLineNum.getValue(), prcApiPMsg.NWZC157004PMsg.no(i).trxLineSubNum.getValue());
            detailBean.setPrcInfo(prcInfo);
        }
        return true;
    }

    private void getPrcList(NWZC185001PMsg pMsg, ParamBean bean) {
        NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxModeCd, NWZC157001.GET_PRICE_LIST);
        if (hasValue(pMsg.slsDt)) {
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, pMsg.slsDt);
        } else {
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, this.slsDt);
        }
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcCtxTpCd, PRC_CTX_TP.SALES_PRICING);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdCatgCd, bean.getDsOrdCatgCd());
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdTpCd, bean.getDsOrdTpCd());
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.lineBizTpCd, bean.getLineBizTpCd());
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsAcctNum, bean.getSellToCustCd());
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxPrcCatgOpFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.coaBrCd, bean.getCoaBrCd());

        new NWZC157001().execute(prcApiPMsg, this.onBatType);
        if (S21ApiUtil.isXxMsgId(prcApiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(prcApiPMsg);
            for (S21ApiMessage msg : msgList) {
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                setErrMsg(pMsg, msgId, msgPrms);
            }
            return;
        }

        bean.setPrcCatgCd(prcApiPMsg.xxPrcList.no(0).prcCatgCd.getValue());

        PRC_CATGTMsg prcCatgTMsg = getPrcCatg(pMsg.glblCmpyCd.getValue(), bean.getPrcCatgCd());
        if (prcCatgTMsg != null) {
            bean.setCcyCd(prcCatgTMsg.ccyCd.getValue());
        }
    }

    private boolean getMdseCd(ParamDetailBean detailBean, NWZC185001_OrdLineListPMsg ordLine, NWZC185001PMsg pMsg, String sellToCustCd) {

        List<Map<String, Object>> mdseInfoList = getMdseInfo(pMsg.glblCmpyCd.getValue(), ordLine.mdseCd.getValue(), sellToCustCd);
        if (mdseInfoList.size() == 0) {
            setDetailErrMsg(pMsg, ordLine, NWZM0036E);
            return false;
        }
        Map<String, Object> mdseInfo = mdseInfoList.get(0);

        detailBean.setMdseCd((String) mdseInfo.get("MDSE_CD"));
        detailBean.setPkgUomCd((String) mdseInfo.get("BASE_PKG_UOM_CD"));

        BigDecimal inEachQty = (BigDecimal) mdseInfo.get("IN_EACH_QTY");
        if (inEachQty == null || BigDecimal.ZERO.compareTo(inEachQty) == 0) {
            inEachQty = BigDecimal.ONE;
        }
        detailBean.setInEachQty(inEachQty);
        detailBean.setInPoundWt((BigDecimal) mdseInfo.get("IN_POUND_WT"));
        return true;
    }

    private boolean getDefWhCd(ParamBean bean, ParamDetailBean detailBean, NWZC185001_OrdLineListPMsg ordLine, NWZC185001PMsg pMsg, String dsOrdPosnNum) {

        MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(pMsg.glblCmpyCd.getValue(), detailBean.getMdseCd());
        if (mdseTMsg == null) {
            setDetailErrMsg(pMsg, ordLine, NWZM0036E);
            return false;
        }

        // 2018/06/07 QC#26123 Mod Start
//        if (ZYPConstant.FLG_OFF_N.equals(mdseTMsg.invtyCtrlFlg.getValue())) {
        if (ZYPConstant.FLG_OFF_N.equals(mdseTMsg.invtyCtrlFlg.getValue())
                && !S21StringUtil.isEquals(mdseTMsg.mdseTpCd.getValue(), MDSE_TP.SET)) {
        // 2018/06/07 QC#26123 Mod End
            return true;
        }

        boolean result = true;
        NWZC180001PMsg apiMsg = new NWZC180001PMsg();
        ZYPEZDItemValueSetter.setValue(apiMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(apiMsg.xxModeCd, NWZC180001Constant.PROC_MODE_OTBD);
        ZYPEZDItemValueSetter.setValue(apiMsg.slsDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(apiMsg.dsOrdCatgCd, bean.getDsOrdCatgCd());
        ZYPEZDItemValueSetter.setValue(apiMsg.dsOrdTpCd, bean.getDsOrdTpCd());
        ZYPEZDItemValueSetter.setValue(apiMsg.mdseCd, mdseTMsg.mdseCd);
        if (hasValue(pMsg.shipToPostCd)) {
            ZYPEZDItemValueSetter.setValue(apiMsg.postCd, pMsg.shipToPostCd.getValue());
        } else {
            ZYPEZDItemValueSetter.setValue(apiMsg.postCd, bean.getShipToPostCd());
        }

        ZYPEZDItemValueSetter.setValue(apiMsg.ordQty, ordLine.ordQty);

        // call NWZC1800 Default WH API
        new NWZC180001().execute(apiMsg, this.onBatType);

        if (S21ApiUtil.isXxMsgId(apiMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(apiMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                if (msgId.endsWith("E")) {
                    setDetailErrMsg(pMsg, ordLine, msgId, msgPrms);
                    result = false;
                }
            }
        } else {
            String dsWhCd = ZYPCodeDataUtil.getVarCharConstValue("DROP_SHIP_RTL_WH_CD", pMsg.glblCmpyCd.getValue());
            if (dsWhCd.equals(apiMsg.rtlWhCd.getValue())) {
                detailBean.setRtlWhCd(apiMsg.rtlWhCd.getValue());
                detailBean.setRtlSwhCd(apiMsg.rtlSwhCd.getValue());
                detailBean.setInvtyLocCd(apiMsg.vndCd.getValue());
                detailBean.setOrdLineSrcCd(apiMsg.ordLineSrcCd.getValue());
            } else {
                detailBean.setRtlWhCd(apiMsg.rtlWhCd.getValue());
                detailBean.setRtlSwhCd(apiMsg.rtlSwhCd.getValue());
                detailBean.setInvtyLocCd(S21StringUtil.concatStrings(apiMsg.rtlWhCd.getValue(), apiMsg.rtlSwhCd.getValue()));
                detailBean.setOrdLineSrcCd(apiMsg.ordLineSrcCd.getValue());
            }
        }

        return result;
    }

    // Mod Start 2018/02/26 QC#22967
    //private boolean checkBillShipSoldRelation(NWZC185001PMsg pMsg, ParamBean bean) {
    private void checkBillShipSoldRelation(NWZC185001PMsg pMsg, ParamBean bean) {
        // Mod End 2018/02/26 QC#22967

        // Add Start 2018/02/26 QC#22967
        // Check Sold To(Customer Code) <- Ship To(Account Number)
        // relation.
        callCustInfoGetApiForCheckRelation(pMsg, //
                bean.getSoldToCustLocCd(), bean.getShipToCustAcctCd(), //
                NWZM2254E);

        // Check Bill To(Customer Code) <- Sold To(Account Number)
        // relation.
        callCustInfoGetApiForCheckRelation(pMsg, //
                bean.getBillToCustCd(), bean.getSellToCustCd(), //
                NWZM2255E);
        // Add End 2018/02/26 QC#22967

        // Del Start 2018/02/26 QC#22967
//        // 2017/06/09 S21_NA#18296 Mod Start
//        // QC#18235 2017/04/12 Mod Start
//        // NMZC610001PMsg custInfoGetApiMsg = NWXC150001DsCheck.callCustInfoGetApi(pMsg.glblCmpyCd.getValue(), bean.getBillToCustCd(), bean.getShipToCustCd(), bean.getSellToCustCd(), this.onBatType);
////        NMZC610001PMsg custInfoGetApiMsg = NWXC150001DsCheck.callCustInfoGetApi(pMsg.glblCmpyCd.getValue(), bean.getBillToCustCd(), bean.getShipToCustCd(), bean.getShipToCustAcctCd(), this.onBatType);
//        // QC#18235 2017/04/12 Mod End
//        NMZC610001PMsg custInfoGetApiMsg = NWXC150001DsCheck.callCustInfoGetApi(pMsg.glblCmpyCd.getValue(), //
//                bean.getBillToCustCd(), //
//                bean.getSellToCustCd(), //
//                bean.getShipToCustAcctCd(), //
//                this.onBatType);
//        // 2017/06/09 S21_NA#18296 Mod End
//        if (S21ApiUtil.isXxMsgId(custInfoGetApiMsg)) {
//            for (int ix = 0; ix < custInfoGetApiMsg.xxMsgIdList.getValidCount(); ix++) {
//                String[] p = new String[4];
//                p[0] = custInfoGetApiMsg.xxMsgIdList.no(ix).xxMsgPrmTxt_1.getValue();
//                p[1] = custInfoGetApiMsg.xxMsgIdList.no(ix).xxMsgPrmTxt_2.getValue();
//                p[2] = custInfoGetApiMsg.xxMsgIdList.no(ix).xxMsgPrmTxt_3.getValue();
//                p[3] = custInfoGetApiMsg.xxMsgIdList.no(ix).xxMsgPrmTxt_4.getValue();
//                setErrMsg(pMsg, custInfoGetApiMsg.xxMsgIdList.no(ix).xxMsgId.getValue(), p);
//            }
//            return true;
//        }
//        for (int i = 0; i < custInfoGetApiMsg.EligibleCheckList.getValidCount(); i++) {
//            NMZC610001_EligibleCheckListPMsg eligiblePMsg = custInfoGetApiMsg.EligibleCheckList.no(i);
//            // 2017/06/09 S21_NA#18296 Mod Start
////            if (!ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnBillToFlg_B.getValue()) //
////                    || !ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnShipToFlg_S.getValue())) {
////                setErrMsg(pMsg, NWZM1455E);
////                return true;
////            }
//            if (!ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnBillToFlg_B.getValue()) //
//                    || !ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnRecipFlg.getValue())) {
//                setErrMsg(pMsg, NWZM1455E);
//                return true;
//            }
//            // 2017/06/09 S21_NA#18296 Mod End
//        }
//        return false;
        // Del End 2018/02/26 QC#22967
    }

    // Add Start 2018/02/26 QC#22967
    /**
     * @param pMsg NWZC185001PMsg
     * @param billToCustCd String
     * @param acctNum String
     * @param errMsgId String
     */
    private void callCustInfoGetApiForCheckRelation(NWZC185001PMsg pMsg, String billToCustCd, String acctNum, String errMsgId) {
        NMZC610001PMsg custInfoGetApiMsg = NWXC150001DsCheck.callCustInfoGetApiBillTo( //
                pMsg.glblCmpyCd.getValue(), billToCustCd, acctNum, this.onBatType);

        if (S21ApiUtil.isXxMsgId(custInfoGetApiMsg)) {
            for (int ix = 0; ix < custInfoGetApiMsg.xxMsgIdList.getValidCount(); ix++) {
                String[] p = new String[4];
                p[0] = custInfoGetApiMsg.xxMsgIdList.no(ix).xxMsgPrmTxt_1.getValue();
                p[1] = custInfoGetApiMsg.xxMsgIdList.no(ix).xxMsgPrmTxt_2.getValue();
                p[2] = custInfoGetApiMsg.xxMsgIdList.no(ix).xxMsgPrmTxt_3.getValue();
                p[3] = custInfoGetApiMsg.xxMsgIdList.no(ix).xxMsgPrmTxt_4.getValue();
                setErrMsg(pMsg, custInfoGetApiMsg.xxMsgIdList.no(ix).xxMsgId.getValue(), p);
            }
        }

        for (int i = 0; i < custInfoGetApiMsg.EligibleCheckList.getValidCount(); i++) {
            NMZC610001_EligibleCheckListPMsg eligiblePMsg = custInfoGetApiMsg.EligibleCheckList.no(i);
            if (!ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnBillToFlg_B.getValue())) {
                setErrMsg(pMsg, errMsgId);
            }
        }
    }
    // Add End 2018/02/26 QC#22967

    private boolean checkFrtCondSvcLvlRelation(String glblCmpyCd, String lineBizTpCd, String frtCondCd, String shpgSvcLvlCd) {
        Integer cnt = getFrtCondSvcLvlRelationCnt(glblCmpyCd, lineBizTpCd, frtCondCd, shpgSvcLvlCd);
        return (cnt == 0);
    }

    private boolean checkCarrCdAndShpgSvcLvlRelation(String glblCmpyCd, String shpgSvcLvlCd, String carrCd) {

        if (!hasValue(carrCd)) {
            return false;
        }
        Integer cnt = getCarrCdAndShpgSvcLvlRelationCnt(glblCmpyCd, shpgSvcLvlCd, carrCd);
        return (cnt == 0);
    }

    private void getOrdCatgAndOrdTp(NWZC185001PMsg pMsg, ParamBean bean) {
        List<Map<String, Object>> list = getXtrnlIntfcXref(pMsg.glblCmpyCd.getValue(), pMsg.lgcyOrdTpCd.getValue(), pMsg.lgcyOrdRsnCd.getValue());
        if (list.size() == 0) {
            setErrMsg(pMsg, NWZM1906E);
            return;
        }
        Map<String, Object> xtrnlIntfcXref = list.get(0);

        bean.setDsOrdCatgCd((String) xtrnlIntfcXref.get("TRGT_ATTRB_TXT_02"));
        bean.setDsOrdTpCd((String) xtrnlIntfcXref.get("TRGT_ATTRB_TXT_03"));

        DS_ORD_TPTMsg dsOrdTpTMsg = getDsOrdTp(pMsg.glblCmpyCd.getValue(), bean.getDsOrdTpCd());
        if (dsOrdTpTMsg == null) {
            setErrMsg(pMsg, NWZM1286E);
            return;
        }

        bean.setCpoOrdTpCd(dsOrdTpTMsg.cpoOrdTpCd.getValue());
    }

    private void getLineBizTpCd(NWZC185001PMsg pMsg, ParamBean bean) {
        List<Map<String, Object>> list = getDefPrcCatgCd(pMsg.glblCmpyCd.getValue(), bean.getDsOrdTpCd(), this.slsDt);
        if (list.size() == 0) {
            setErrMsg(pMsg, NWZM1514E);
            return;
        }
        Map<String, Object> defPrcCatgCd = list.get(0);

        bean.setLineBizTpCd((String) defPrcCatgCd.get("LINE_BIZ_TP_CD"));
        bean.setDefPrcCatgCd((String) defPrcCatgCd.get("DEF_PRC_CATG_CD"));
    }

    private void getSoldTo(NWZC185001PMsg pMsg, ParamBean bean) {

        List<Map<String, Object>> list = getBillToCustByLocNum(pMsg.glblCmpyCd.getValue(), pMsg.soldToLocNum.getValue());
        if (list == null || list.size() == 0) {
            setErrMsg(pMsg, NWZM1907E);
            return;
        }
        Map<String, Object> soldTo = list.get(0);

        bean.setSellToCustCd((String) soldTo.get("SELL_TO_CUST_CD"));
        bean.setSoldToCustLocCd((String) soldTo.get("BILL_TO_CUST_CD"));

    }

    private void getShipTo(NWZC185001PMsg pMsg, ParamBean bean) {

        List<Map<String, Object>> list = getShipToCustByLocNum(pMsg.glblCmpyCd.getValue(), pMsg.shipToLocNum.getValue());
        if (list == null || list.size() == 0) {
            setErrMsg(pMsg, NWZM1909E);
            return;
        }
        Map<String, Object> shipTo = list.get(0);

        bean.setShipToCustCd((String) shipTo.get("SHIP_TO_CUST_CD"));
        bean.setShipToCustAcctCd((String) shipTo.get("SELL_TO_CUST_CD"));
        bean.setShipToAddlLocNm((String) shipTo.get("ADDL_LOC_NM"));
        bean.setShipToLocNm((String) shipTo.get("LOC_NM"));
        bean.setShipToFirstLineAddr((String) shipTo.get("FIRST_LINE_ADDR"));
        bean.setShipToScdLineAddr((String) shipTo.get("SCD_LINE_ADDR"));
        bean.setShipToThirdLineAddr((String) shipTo.get("THIRD_LINE_ADDR"));
        bean.setShipToFrthLineAddr((String) shipTo.get("FRTH_LINE_ADDR"));
        bean.setShipToPostCd((String) shipTo.get("POST_CD"));
        bean.setShipToCtyAddr((String) shipTo.get("CTY_ADDR"));
        bean.setShipToStCd((String) shipTo.get("ST_CD"));
        bean.setShipToProvNm((String) shipTo.get("PROV_NM"));
        bean.setShipToCtryCd((String) shipTo.get("CTRY_CD"));
        bean.setShipToCntyNm((String) shipTo.get("CNTY_NM"));
        bean.setShipToFirstRefCmntTxt((String) shipTo.get("FIRST_REF_CMNT_TXT"));
        bean.setShipToScdRefCmntTxt((String) shipTo.get("SCD_REF_CMNT_TXT"));
    }

    private void getBillTo(NWZC185001PMsg pMsg, ParamBean bean) {

        List<Map<String, Object>> list = getBillToCustByLocNum(pMsg.glblCmpyCd.getValue(), pMsg.billToLocNum.getValue());
        if (list == null || list.size() == 0) {
            setErrMsg(pMsg, NWZM1908E);
            return;
        }
        Map<String, Object> billTo = list.get(0);

        bean.setBillToCustCd((String) billTo.get("BILL_TO_CUST_CD"));
        bean.setBillToCustAcctCd((String) billTo.get("SELL_TO_CUST_CD"));

    }

    private void getDsOrdLineCatg(NWZC185001PMsg pMsg, ParamBean bean) {

        List<Map<String, Object>> list = getDsOrdLineProcDfn(pMsg.glblCmpyCd.getValue(), bean.getDsOrdTpCd());
        if (list == null || list.size() == 0) {
            setErrMsg(pMsg, NWZM1910E);
            return;
        }
        Map<String, Object> dsOrdLineProcDfn = list.get(0);

        bean.setDsOrdLineCatdCd((String) dsOrdLineProcDfn.get("DS_ORD_LINE_CATG_CD"));
    }

    private NWZC185001_OrdLineListPMsg getTarget(NWZC185001PMsg pMsg, ParamBean bean, NWZC150002PMsg pMsg02) {
        ParamDetailBean target = getParamDetailBean(bean, pMsg02.cpoDtlLineNum.getValue(), pMsg02.cpoDtlLineSubNum.getValue());
        if (target == null) {
            return null;
        }
        return getTarget(pMsg, target.getOrdSrcRefLineNum());
    }

    private NWZC185001_OrdLineListPMsg getTarget(NWZC185001PMsg pMsg, String ordSrcRefLineNum) {
        NWZC185001_OrdLineListPMsg linePMsg = null;
        for (int j = 0; j < pMsg.OrdLineList.getValidCount(); j++) {
            if (ordSrcRefLineNum.equals(pMsg.OrdLineList.no(j).ordSrcRefLineNum.getValue())) {
                linePMsg = pMsg.OrdLineList.no(j);
                break;
            }
        }
        return linePMsg;
    }

    private void setHdrParam(NWZC157001PMsg prcApiPMsg, String modeCd, ParamBean paramBean) {

        NWZC185001PMsg pMsg = (NWZC185001PMsg) msgMap.getPmsg();

        // Header
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        prcApiPMsg.xxModeCd.setValue(modeCd);
        if (hasValue(pMsg.slsDt)) {
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, pMsg.slsDt);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.orgRqstDelyDt, pMsg.slsDt);
        } else {
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, this.slsDt);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.orgRqstDelyDt, this.slsDt);
        }

        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcCtxTpCd, PRC_CTX_TP.SALES_PRICING);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdCatgCd, paramBean.getDsOrdCatgCd());
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdTpCd, paramBean.getDsOrdTpCd());
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.lineBizTpCd, paramBean.getLineBizTpCd());
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsAcctNum, paramBean.getSellToCustCd());

        ZYPEZDItemValueSetter.setValue(prcApiPMsg.cpoSrcTpCd, CPO_SRC_TP.IS_WEB);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.taxCalcFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxPrcCatgOpFlg, ZYPConstant.FLG_OFF_N);

        if (NWZC157001.GET_ORDER_ALL.equals(modeCd)) {
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.custIssPoNum, pMsg.custIssPoNum);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.taxCalcFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.spclHdlgTpCd, pMsg.spclHdlgTpCd);
            // QC#55981 2020/02/20 Add Start
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxSmryLineFlg, ZYPConstant.FLG_ON_Y);
            // QC#55981 2020/02/20 Add End
        }
    }

    private void setLineParam(NWZC157001PMsg prcApiPMsg, String modeCd, ParamBean paramBean) {
        NWZC185001PMsg pMsg = (NWZC185001PMsg) msgMap.getPmsg();

        int cnt = pMsg.OrdLineList.getValidCount();
        // 2018/06/08 QC#26123 Add Start
        int vldCnt = 0;
        // 2018/06/08 QC#26123 Add End
        for (int i = 0; i < cnt; i++) {
            NWZC185001_OrdLineListPMsg ordLine = pMsg.OrdLineList.no(i);
            // 2018/06/08 QC#26123 Mod Start
//            NWZC157002PMsg nwzc157002PMsg = prcApiPMsg.NWZC157002PMsg.no(i);
            NWZC157002PMsg nwzc157002PMsg = prcApiPMsg.NWZC157002PMsg.no(vldCnt++);
            // 2018/06/08 QC#26123 Mod End

            ParamDetailBean detailBean = getParamDetailBean(paramBean, ordLine);

            ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.trxLineNum, detailBean.getCpoDtlLineNum());
            ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.trxLineSubNum, detailBean.getCpoDtlLineSubNum());
            ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.configCatgCd, CONFIG_CATG.OUTBOUND);
            ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.shipToCustCd, paramBean.getShipToCustCd());
            ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.dsAcctNum_SH, paramBean.getShipToCustAcctCd());
            ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.billToCustCd, paramBean.getBillToCustCd());
            ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.dsAcctNum_BL, paramBean.getBillToCustAcctCd());
            ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.prcCatgCd, paramBean.getPrcCatgCd());
            ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.ccyCd, paramBean.getCcyCd());
            ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.mdseCd, detailBean.getMdseCd());
            ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.pkgUomCd, detailBean.getPkgUomCd());
            ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.dsOrdLineCatgCd, paramBean.getDsOrdLineCatdCd());
            if (hasValue(detailBean.getInEachQty()) && BigDecimal.ZERO.compareTo(detailBean.getInEachQty()) != 0) {
                ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.ordQty, ordLine.ordQty.getValue().divide(detailBean.getInEachQty(), 0, RoundingMode.DOWN));
            } else {
                ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.ordQty, ordLine.ordQty);
            }

            ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.ordCustUomQty, ordLine.ordQty);
            ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.invQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.xxPrcCloFlg, ZYPConstant.FLG_OFF_N);

            if (NWZC157001.GET_LINE_PRICE.equals(modeCd)) {
                ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.ctyAddr_SH, paramBean.getShipToCtyAddr());
                ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.stCd_SH, paramBean.getShipToStCd());
                ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.ctryCd_SH, paramBean.getShipToCtryCd());
            } else if (NWZC157001.GET_ORDER_ALL.equals(modeCd)) {
                setValue(nwzc157002PMsg.firstLineAddr_SH, pMsg.shipToFirstLineAddr, paramBean.getShipToFirstLineAddr());
                setValue(nwzc157002PMsg.scdLineAddr_SH, pMsg.shipToScdLineAddr, paramBean.getShipToScdLineAddr());
                setValue(nwzc157002PMsg.ctyAddr_SH, pMsg.shipToCtyAddr, paramBean.getShipToCtyAddr());
                setValue(nwzc157002PMsg.stCd_SH, pMsg.shipToStCd, paramBean.getShipToStCd());
                setValue(nwzc157002PMsg.cntyNm_SH, pMsg.shipToCntyNm, paramBean.getShipToCntyNm());
                setValue(nwzc157002PMsg.postCd_SH, pMsg.shipToPostCd, paramBean.getShipToPostCd());
                setValue(nwzc157002PMsg.ctryCd_SH, pMsg.shipToCtryCd, paramBean.getShipToCtryCd());

                ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.slsRepOrSlsTeamTocCd, paramBean.getSlsRepOrSlsTeamTocCd());
                ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.rtlWhCd, detailBean.getRtlWhCd());  // QC#55981 2020/02/20
                ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.frtCondCd, pMsg.frtCondCd);
                ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.shpgSvcLvlCd, pMsg.shpgSvcLvlCd);
                ZYPEZDItemValueSetter.setValue(nwzc157002PMsg.coaExtnCd, paramBean.getCoaExtnCd());
            }

            // 2018/06/08 QC#26123 Add Start
//            List<ParamDetailBean> childBeanList = getParamChildDetailBean(paramBean, ordLine.ordSrcRefLineNum.getValue());
//            if (!childBeanList.isEmpty()) {
//                for (ParamDetailBean bean : childBeanList) {
//                    ParamChildDetailBean childBean = (ParamChildDetailBean) bean;
//
//                    NWZC157002PMsg nwzc157002PMsgChild = prcApiPMsg.NWZC157002PMsg.no(vldCnt++);
//                    EZDMsg.copy(nwzc157002PMsg, null, nwzc157002PMsgChild, null);
//
//                    ZYPEZDItemValueSetter.setValue(nwzc157002PMsgChild.trxLineNum, childBean.getCpoDtlLineNum());
//                    ZYPEZDItemValueSetter.setValue(nwzc157002PMsgChild.trxLineSubNum, childBean.getCpoDtlLineSubNum());
//                    ZYPEZDItemValueSetter.setValue(nwzc157002PMsgChild.mdseCd, childBean.getMdseCd());
//                    ZYPEZDItemValueSetter.setValue(nwzc157002PMsgChild.pkgUomCd, childBean.getPkgUomCd());
//                    ZYPEZDItemValueSetter.setValue(nwzc157002PMsgChild.ordQty, childBean.getOrdQty().multiply(ordLine.ordQty.getValue()));
//                    if (hasValue(childBean.getInEachQty()) && BigDecimal.ZERO.compareTo(childBean.getInEachQty()) != 0) {
//                        ZYPEZDItemValueSetter.setValue(nwzc157002PMsgChild.ordQty, nwzc157002PMsgChild.ordQty.getValue().divide(childBean.getInEachQty(), 0, RoundingMode.DOWN));
//                    }
//                    ZYPEZDItemValueSetter.setValue(nwzc157002PMsgChild.ordCustUomQty, childBean.getOrdQty().multiply(ordLine.ordQty.getValue()));
//                }
//            }
            // 2018/06/08 QC#26123 Add End
        }
        // 2018/06/08 QC#26123 Mod Start
//        prcApiPMsg.NWZC157002PMsg.setValidCount(cnt);
        prcApiPMsg.NWZC157002PMsg.setValidCount(vldCnt);
        // 2018/06/08 QC#26123 Mod End
    }

    private void setValue(EZDPStringItem target, EZDPStringItem first, String second) {
        if (hasValue(first)) {
            ZYPEZDItemValueSetter.setValue(target, first);
        } else {
            ZYPEZDItemValueSetter.setValue(target, second);
        }
    }

    private boolean getSlsRep(NWZC185001PMsg pMsg, ParamBean bean, List<String> writers) {

        // call NMZC2600 Default Sales Rep API
        NMZC260001PMsg nMZC260001PMsg = new NMZC260001PMsg();
        ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.shipToCustCd, bean.getShipToCustCd());
        ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.dsOrdTpCd, bean.getDsOrdTpCd());
        ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.dsOrdCatgCd, bean.getDsOrdCatgCd()); // 2018/04/18 S21_NA#25418 Add
        // 2020/04/27 QC#56638 Add Start
        ZYPEZDItemValueSetter.setValue(nMZC260001PMsg.billToCustCd, bean.getSoldToCustLocCd());
        if (!callDefSlsRepApi(pMsg, nMZC260001PMsg)) {
            return false;
        }

        // 2017/03/14 S21_NA#16855 Add Start
        // Mod Start 2017/12/14 QC#19804(Sol#349)
        //String trtyGrpTpCd = getTrtyGrpTpCdFromDsOrdTpCd(bean.getDsOrdTpCd(), pMsg.glblCmpyCd.getValue());
        List<String> trtyGrpTpCdList = getTrtyGrpTpCdFromDsOrdTpCd(bean.getDsOrdTpCd(), pMsg.glblCmpyCd.getValue());
        // Mod End 2017/12/14 QC#19804(Sol#349)
        // 2017/03/14 S21_NA#16855 Add End

        NMZC260001_defSlsRepListPMsgArray defSlsRepMsgArray = nMZC260001PMsg.defSlsRepList;
        for (int i = 0; i < defSlsRepMsgArray.getValidCount(); i++) {
            NMZC260001_defSlsRepListPMsg defSlsRepPMsg = defSlsRepMsgArray.no(i);

            // 2017/03/14 S21_NA#16855 Mod Start
//          if (defSlsRepPMsg.lineBizTpCd.getValue().equals(bean.getLineBizTpCd())) {
            // Mod Start 2017/12/14 QC#19804(Sol#349)
            //if (defSlsRepPMsg.lineBizTpCd.getValue().equals(bean.getLineBizTpCd()) //
            //      && (!ZYPCommonFunc.hasValue(trtyGrpTpCd) //
            //      || trtyGrpTpCd.equals(defSlsRepPMsg.trtyGrpTpCd.getValue()))) {
            boolean matchFlag = false;
            if (trtyGrpTpCdList != null) {
                if (trtyGrpTpCdList.contains(defSlsRepPMsg.trtyGrpTpCd.getValue())) {
                    matchFlag = true;
                }
            } else {
                if (defSlsRepPMsg.lineBizTpCd.getValue().equals(bean.getLineBizTpCd())) {
                    matchFlag = true;
                }
            }

            if (matchFlag) {
                // Mod End 2017/12/14 QC#19804(Sol#349)
            // 2017/03/14 S21_NA#16855 Mod End
                String lineBizRoleTpCd = defSlsRepPMsg.lineBizRoleTpCd.getValue();
                if (isWriter(writers, lineBizRoleTpCd)) {
                    bean.setSlsRepOrSlsTeamTocCd(defSlsRepPMsg.tocCd.getValue());
                    bean.setLineBizRoleTpCd(defSlsRepPMsg.lineBizRoleTpCd.getValue());
                    TOCTMsg toc = getToc(pMsg.glblCmpyCd.getValue(), defSlsRepPMsg.tocCd.getValue());
                    if (toc != null) {
                        bean.setCoaExtnCd(toc.coaExtnCd.getValue());
                        bean.setCoaBrCd(toc.coaBrCd.getValue());
                    }
                    break;
                }
            }
        }

        return true;
    }

    private boolean callDefSlsRepApi(NWZC185001PMsg pMsg, NMZC260001PMsg nmzc260001PMsg) {
        boolean result = true;
        new NMZC260001().execute(nmzc260001PMsg, this.onBatType);

        if (S21ApiUtil.isXxMsgId(nmzc260001PMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(nmzc260001PMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                setErrMsg(pMsg, msgId, msgPrms);
                result = false;
            }
        }

        return result;
    }

    private boolean isWriter(List<String> writers, String lineBizRoleTpCd) {

        for (String writer : writers) {
            if (writer.equals(lineBizRoleTpCd)) {
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getDefPrcCatgCd(String glblCmpyCd, String dsOrdTpCd, String slsDt) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsOrdTpCd", dsOrdTpCd);
        ssmParam.put("slsDt", slsDt);

        return (List<Map<String, Object>>) ssmBatClnt.queryObjectList("getDefPrcCatgCd", ssmParam);
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getXtrnlIntfcXref(String glblCmpyCd, String lgcyOrdTpCd, String lgcyOrdRsnCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("cpoSrcTpCd", CPO_SRC_TP.IS_WEB);
        ssmParam.put("intfcXrefCtxTpCd", INTFC_XREF_CTX_TP.ORDER_TYPE_MAPPING);
        ssmParam.put("srcAttrbTxt02", lgcyOrdTpCd);
        ssmParam.put("srcAttrbTxt03", lgcyOrdRsnCd);

        return (List<Map<String, Object>>) ssmBatClnt.queryObjectList("getXtrnlIntfcXref", ssmParam);
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getBillToCustByLocNum(String glblCmpyCd, String locNum) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("locNum", locNum);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return (List<Map<String, Object>>) ssmBatClnt.queryObjectList("getBillToCustByLocNum", ssmParam);
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getShipToCustByLocNum(String glblCmpyCd, String locNum) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("locNum", locNum);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return (List<Map<String, Object>>) ssmBatClnt.queryObjectList("getShipToCustByLocNum", ssmParam);
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getMdseInfo(String glblCmpyCd, String mdseCd, String sellToCustCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("mdseCd", mdseCd);
        ssmParam.put("dsAcctNum", sellToCustCd);
        ssmParam.put("flagY", ZYPConstant.FLG_ON_Y);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        return (List<Map<String, Object>>) ssmBatClnt.queryObjectList("getMdseInfo", ssmParam);
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getDsOrdLineProcDfn(String glblCmpyCd, String dsOrdTpCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsOrdTpCd", dsOrdTpCd);
        ssmParam.put("flagY", ZYPConstant.FLG_ON_Y);
        ssmParam.put("dsOrdLineDrctnCd", DS_ORD_LINE_DRCTN.OUTBOUND);

        return (List<Map<String, Object>>) ssmBatClnt.queryObjectList("getDsOrdLineProcDfn", ssmParam);
    }

    @SuppressWarnings("unchecked")
    private List<String> getWriterLineBizRoleTp(String glblCmpyCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("primRepRoleFlg", ZYPConstant.FLG_ON_Y);

        return (List<String>) ssmBatClnt.queryObjectList("getWriterLineBizRoleTp", ssmParam);
    }

    private TOCTMsg getToc(String glblCmpyCd, String tocCd) {
        TOCTMsg prmTMsg = new TOCTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.tocCd, tocCd);
        return (TOCTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }

    private PRC_CATGTMsg getPrcCatg(String glblCmpyCd, String prcCatgCd) {
        PRC_CATGTMsg prmTMsg = new PRC_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.prcCatgCd, prcCatgCd);
        return (PRC_CATGTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }

    private DS_ORD_TPTMsg getDsOrdTp(String glblCmpyCd, String dsOrdTpCd) {
        DS_ORD_TPTMsg prmTMsg = new DS_ORD_TPTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.dsOrdTpCd, dsOrdTpCd);
        return (DS_ORD_TPTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }

    private CTAC_PSNTMsg getCtacPsn(String glblCmpyCd, BigDecimal ctacPsnPk) {
        CTAC_PSNTMsg prmTMsg = new CTAC_PSNTMsg();
        ZYPEZDItemValueSetter.setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prmTMsg.ctacPsnPk, ctacPsnPk);
        return (CTAC_PSNTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }

    // Del Start 2018/02/26 QC#22967
    //private String getDsCtacPsnReln(String glblCmpyCd, BigDecimal ctacPsnPk) {
    //    Map<String, Object> ssmParam = new HashMap<String, Object>();
    //    ssmParam.put("glblCmpyCd", glblCmpyCd);
    //    ssmParam.put("ctacPsnPk", ctacPsnPk);
    //    return (String) ssmBatClnt.queryObject("getCtacTpCd", ssmParam);
    //}
    // Del End 2018/02/26 QC#22967

    private Integer getCarrCdAndShpgSvcLvlRelationCnt(String glblCmpyCd, String shpgSvcLvlCd, String carrCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("shpgSvcLvlCd", shpgSvcLvlCd);
        ssmParam.put("carrCd", carrCd);

        return (Integer) ssmBatClnt.queryObject("getCarrCdAndShpgSvcLvlRelationCnt", ssmParam);
    }

    private Integer getFrtCondSvcLvlRelationCnt(String glblCmpyCd, String lineBizTpCd, String frtCondCd, String shpgSvcLvlCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("lineBizTpCd", lineBizTpCd);
        ssmParam.put("frtCondCd", frtCondCd);
        ssmParam.put("shpgSvcLvlCd", shpgSvcLvlCd);

        return (Integer) ssmBatClnt.queryObject("getFrtCondSvcLvlRelationCnt", ssmParam);
    }

    private CPO_DTLTMsgArray getDsCpoDtl(String glblCmpyCd, String cpoOrdNum) {
        CPO_DTLTMsg prmTMsg = new CPO_DTLTMsg();
        prmTMsg.setSQLID("001");
        prmTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        prmTMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);

        return (CPO_DTLTMsgArray) S21ApiTBLAccessor.findByCondition(prmTMsg);
    }
    
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getCtacPnt(String glblCmpyCd, BigDecimal ctacPsnPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("ctacPsnPk", ctacPsnPk);
        ssmParam.put("dsOpsOutFlg", ZYPConstant.FLG_OFF_N);
        ssmParam.put("dsCtacPntActvFlg", ZYPConstant.FLG_ON_Y);

        return (List<Map<String, Object>>) ssmBatClnt.queryObjectList("getCtacPnt", ssmParam);
    }

    // 2017/03/14 S21_NA#16855 Add Start
    // Mod Start 2017/12/14 QC#19804(Sol#349)
    ///**
    // * <pre>
    // * Get Territory Group Type Code From DS Order Type Code
    // * @param String dsOrdTpCd
    // * @param String glblCmpyCd
    // * </pre>
    // */
    //private static String getTrtyGrpTpCdFromDsOrdTpCd(String dsOrdTpCd, String glblCmpyCd) {
    /**
     * <pre>
     * Get Territory Group Type Code From DS Order Type Code
     * @param String dsOrdTpCd
     * @param String glblCmpyCd
     * </pre>
     */
    private static List<String> getTrtyGrpTpCdFromDsOrdTpCd(String dsOrdTpCd, String glblCmpyCd) {
        // Mod End 2017/12/14 QC#19804(Sol#349)

        if (!ZYPCommonFunc.hasValue(dsOrdTpCd)) {
            return null;
        }

        DS_ORD_TP_PROC_DFNTMsg dsOrdTpProcDfnTMsg = new DS_ORD_TP_PROC_DFNTMsg();
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfnTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfnTMsg.dsOrdTpCd, dsOrdTpCd);

        dsOrdTpProcDfnTMsg = (DS_ORD_TP_PROC_DFNTMsg) S21FastTBLAccessor.findByKey(dsOrdTpProcDfnTMsg);
        if (dsOrdTpProcDfnTMsg != null) {
            // Mod Start 2017/12/14 QC#19804(Sol#349)
            //return dsOrdTpProcDfnTMsg.trtyGrpTpCd.getValue();
            String trtyGrpTpTxt = dsOrdTpProcDfnTMsg.trtyGrpTpTxt.getValue();

            List<String> trtyGrpTpCdList =  null;
            if (ZYPCommonFunc.hasValue(trtyGrpTpTxt)){
                trtyGrpTpCdList = Arrays.asList(trtyGrpTpTxt.split(","));
            }

            return trtyGrpTpCdList;
            // Mod End 2017/12/14 QC#19804(Sol#349)
        }

        return null;
    }
    // 2017/03/14 S21_NA#16855 Add End
    
    // 2018/02/08 S21_NA#20297(Sol#379) Add Start
    private String getDefShpgCmt(NWZC185001PMsg pMsg, ParamBean paramBean){
        
        NMZC610001PMsg nmzc6100PMsg = new NMZC610001PMsg();
        ZYPEZDItemValueSetter.setValue(nmzc6100PMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(nmzc6100PMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_INSTRUCTION);
        ZYPEZDItemValueSetter.setValue(nmzc6100PMsg.lineBizTpCd, paramBean.getLineBizTpCd());
        ZYPEZDItemValueSetter.setValue(nmzc6100PMsg.dsBizAreaCd, getDsBizAreaCd(pMsg, paramBean));
        ZYPEZDItemValueSetter.setValue(nmzc6100PMsg.dsCustMsgTpCd, DS_CUST_MSG_TP.SHIP );
        ZYPEZDItemValueSetter.setValue(nmzc6100PMsg.shipToCustCd, paramBean.getShipToCustCd());
        ZYPEZDItemValueSetter.setValue(nmzc6100PMsg.slsDt, pMsg.slsDt);
        
        new NMZC610001().execute(nmzc6100PMsg, ONBATCH_TYPE.BATCH);
        
        if (S21ApiUtil.isXxMsgId(nmzc6100PMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(nmzc6100PMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                setErrMsg(pMsg, msgId, msgPrms);
            }
        }
        
        int count = nmzc6100PMsg.InstructionList.getValidCount();
        StringBuilder sb = new StringBuilder("");
        for(int i = 0 ; i < count; i ++){
            if(hasValue(nmzc6100PMsg.InstructionList.no(i).dsCustMsgTxt)){
                if(i != 0 ){
                    sb.append(LINE);
                }
                sb.append(nmzc6100PMsg.InstructionList.no(i).dsCustMsgTxt.getValue());
                if (sb.length() > DELY_ADDL_CMNT_TXT_MAX_SIZE) {
                   return sb.substring(0, DELY_ADDL_CMNT_TXT_MAX_SIZE);
                }
            }
        }
        
        return sb.toString();
    }
    
    private static String getDsBizAreaCd(NWZC185001PMsg pMsg, ParamBean paramBean) {

        ORD_CATG_BIZ_CTXTMsg condition = new ORD_CATG_BIZ_CTXTMsg();

        condition = new ORD_CATG_BIZ_CTXTMsg();
        condition.setSQLID("002");
        condition.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        condition.setConditionValue("ordCatgCtxTpCd01A", EQUIPMENT_ORDER_VALUE_SET);
        condition.setConditionValue("ordCatgCtxTpCd01B", SUPPLIES_ORDER_VALUE_SET);
        condition.setConditionValue("dsOrdCatgCd01",paramBean.getDsOrdCatgCd());

        ORD_CATG_BIZ_CTXTMsgArray tmsgArray = (ORD_CATG_BIZ_CTXTMsgArray) EZDTBLAccessor.findByCondition(condition);
        if (tmsgArray.getValidCount() > 0) {
            return tmsgArray.no(0).scdBizCtxAttrbTxt.getValue();
        }

        return BLANK;
    }
    // 2018/02/08 S21_NA#20297(Sol#379) Add End
    // 2018/06/08 QC#26123 Add Start
    private boolean isSet(String glblCmpyCd, String mdseCd) {

        MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);

        if (mdseTMsg == null) {
            return false;
        }

        return S21StringUtil.isEquals(mdseTMsg.mdseTpCd.getValue(), MDSE_TP.SET);
    }

    private List<ParamChildDetailBean> getChildDtlBeanList(NWZC185001PMsg pMsg, ParamDetailBean parentDtl) {

        List<ParamChildDetailBean> childDtlBeanList = new ArrayList<ParamChildDetailBean>();

        List<Map<String, Object>> childList = getChildMdseList(pMsg.glblCmpyCd.getValue(), parentDtl.getMdseCd());

        if (!childList.isEmpty()) {

            String cpoDtlLineSubNum = "000";

            for (Map<String, Object> childInfo : childList) {

                cpoDtlLineSubNum = getNextCpoDtlLineNum(cpoDtlLineSubNum);

                ParamChildDetailBean childDtlBean = createChildDtlBean(pMsg, parentDtl, childInfo, cpoDtlLineSubNum);
                childDtlBeanList.add(childDtlBean);
            }
        }

        return childDtlBeanList;
    }

    private ParamChildDetailBean createChildDtlBean(NWZC185001PMsg pMsg, ParamDetailBean parentDtl, Map<String, Object> childInfo, String cpoDtlLineSubNum) {

        String childMdseCd = (String) childInfo.get("CHILD_MDSE_CD");
        BigDecimal childMdseQty = (BigDecimal) childInfo.get("CHILD_MDSE_QTY");

        String mdseCd = getMdseCdFromOrdTakeMdse(pMsg.glblCmpyCd.getValue(), childMdseCd);
        MDSE_STORE_PKGTMsg mdseStorePkgTMsg = getMdseStorePkg(pMsg.glblCmpyCd.getValue(), mdseCd);

        // 2019/07/17 QC#51666 Mod Start
        String origMdseCd = getMnfItemCd(pMsg.glblCmpyCd.getValue(), mdseCd);
        //String origMdseCd = getOrigMdseCd(pMsg.glblCmpyCd.getValue(), mdseCd);
        // 2019/07/17 QC#51666 Mod End

        ParamChildDetailBean childDtlBean = new ParamChildDetailBean();

        childDtlBean.setOrdSrcRefLineNum(parentDtl.getOrdSrcRefLineNum());
        childDtlBean.setMdseCd(mdseCd);
        childDtlBean.setRtlWhCd(parentDtl.getRtlWhCd());
        childDtlBean.setRtlSwhCd(parentDtl.getRtlSwhCd());
        childDtlBean.setInvtyLocCd(parentDtl.getInvtyLocCd());
        childDtlBean.setOrdLineSrcCd(parentDtl.getOrdLineSrcCd());
        childDtlBean.setPkgUomCd(mdseStorePkgTMsg.pkgUomCd.getValue());
        childDtlBean.setInEachQty(mdseStorePkgTMsg.inEachQty.getValue());
        childDtlBean.setInPoundWt(mdseStorePkgTMsg.inPoundWt.getValue());
        childDtlBean.setCpoDtlLineNum(parentDtl.getCpoDtlLineNum());
        childDtlBean.setCpoDtlLineSubNum(cpoDtlLineSubNum);

        childDtlBean.setOrdQty(childMdseQty);
        childDtlBean.setOrigMdseCd(origMdseCd);

        return childDtlBean;
    }

    private List<Map<String, Object>> getChildMdseList(String glblCmpyCd, String prntMdseCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("slsDt", this.slsDt);
        ssmParam.put("prntMdseCd", prntMdseCd);

        return (List<Map<String, Object>>) ssmBatClnt.queryObjectList("getChildMdseList", ssmParam);
    }

    private MDSE_STORE_PKGTMsg getMdseStorePkg(String glblCmpyCd, String mdseCd) {

        MDSE_STORE_PKGTMsg tMsg = new MDSE_STORE_PKGTMsg();
        tMsg.setSQLID("501");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("mdseCd01", mdseCd);
        tMsg.setConditionValue("primPkgUomFlg01", ZYPConstant.FLG_ON_Y);

        MDSE_STORE_PKGTMsgArray tMsgAry = (MDSE_STORE_PKGTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);

        if (tMsgAry.getValidCount() > 0) {
            return tMsgAry.no(0);
        } else {
            return null;
        }
    }

    // 2019/07/17 QC#51666 Add Start
    private String getMnfItemCd(String glblCmpyCd, String mdseCd) {
        MDSETMsg tMsg = new MDSETMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, mdseCd);

        MDSETMsg tMsgRet = (MDSETMsg) S21CacheTBLAccessor.findByKey(tMsg);

        if (tMsgRet != null) {
            String mnfItemCd = tMsgRet.mnfItemCd.getValue();

            if (S21StringUtil.isEmpty(mnfItemCd)){
                mnfItemCd = mdseCd;
            }

            return mnfItemCd;
        } else {
            return mdseCd;
        }
    }
    // 2019/07/17 QC#51666 Add End

    // 2019/07/17 QC#51666 Del Start
    //private String getOrigMdseCd(String glblCmpyCd, String mdseCd) {
    //
    //    ORD_TAKE_MDSETMsg tMsg = new ORD_TAKE_MDSETMsg();
    //    tMsg.setSQLID("001");
    //    tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
    //    tMsg.setConditionValue("mdseCd01", mdseCd);
    //
    //    ORD_TAKE_MDSETMsgArray tMsgAry = (ORD_TAKE_MDSETMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
    //
    //    if (tMsgAry.getValidCount() > 0) {
    //        return tMsgAry.no(0).ordTakeMdseCd.getValue();
    //    } else {
    //        return mdseCd;
    //    }
    //}
    // 2019/07/17 QC#51666 Del End

    private String getMdseCdFromOrdTakeMdse(String glblCmpyCd, String mdseCd) {

        ORD_TAKE_MDSETMsg tMsg = new ORD_TAKE_MDSETMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.ordTakeMdseCd, mdseCd);

        tMsg = (ORD_TAKE_MDSETMsg) S21CacheTBLAccessor.findByKey(tMsg);

        if (tMsg != null) {
            return tMsg.mdseCd.getValue();
        } else {
            return mdseCd;
        }
    }

    private List<ParamDetailBean> getParamChildDetailBean(ParamBean paramBean, String ordSrcRefLineNum) {

        List<ParamDetailBean> result = new ArrayList<ParamDetailBean>();

        for (ParamDetailBean detailBean : paramBean.getDetailBeanList()) {
            // 2018/06/08 QC#26123 Add Start
            if (!(detailBean instanceof ParamChildDetailBean)) {
                continue;
            }
            // 2018/06/08 QC#26123 Add End
            if (detailBean.getOrdSrcRefLineNum().equals(ordSrcRefLineNum)) {
                result.add(detailBean);
            }
        }

        return result;
    }
    // 2018/06/08 QC#26123 Add End
    // 2018/09/19 QC#28284 Add Start
    /**
     * getCtacCustRefTpCd
     * @param glblCmpyCd String
     * @param ctacPsnTpCd String
     * @return String
     */
    private String getCtacCustRefTpCd(String glblCmpyCd, String ctacPsnTpCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("ctacPsnTpCd", ctacPsnTpCd);

        CTAC_TPTMsg ctacTpTMsg = new CTAC_TPTMsg();
        ZYPEZDItemValueSetter.setValue(ctacTpTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(ctacTpTMsg.ctacTpCd, ctacPsnTpCd);

        ctacTpTMsg = (CTAC_TPTMsg) S21FastTBLAccessor.findByKey(ctacTpTMsg);
        if (ctacTpTMsg != null) {
            return ctacTpTMsg.ctacCustRefTpCd.getValue();
        }

        return "";
    }
    // 2018/09/19 QC#28284 Add Start

    // 2023/09/20 QC#61782 Add Start
    /**
     * validateEmailAddr
     * @param errorTime emailAddr
     * @return boolean
     */
    private boolean validateEmailAddr(String emailAddr) {
        if (!ZYPMail.checkEmailFormat(emailAddr)) {
            return false;
        }
        return true;
    }

    /**
     * hasVarCharConstSetUp
     * @param glblCmpyCd String
     * @return boolean
     */
    private boolean hasVarCharConstSetUp(String glblCmpyCd) {
        if (!ZYPCommonFunc.hasValue(ZYPCodeDataUtil.getVarCharConstValue(MYCSAUSER_FIRST_NM, glblCmpyCd))) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(ZYPCodeDataUtil.getVarCharConstValue(MYCSAUSER_LAST_NM, glblCmpyCd))) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(ZYPCodeDataUtil.getVarCharConstValue(MYCSAUSER_TEL_NUM, glblCmpyCd))) {
            return false;
        }
        return true;
    }

    /**
     * sendEmailAddrFormatError
     * @param glblCmpyCd String
     * @param errorTime String
     * @param cpoOrdNum String
     * @param ordSrcRefNum String
     */
    private void sendEmailAddrFormatError(String glblCmpyCd, String errorTime, String cpoOrdNum, String ordSrcRefNum) {
        S21Mail mail = new S21Mail(glblCmpyCd);

        // Mail Send
        // Mail Template
        S21MailTemplate s21MailTemplate = null;
        s21MailTemplate = new S21MailTemplate(glblCmpyCd, ML_TMPL_ID_01);
        if (s21MailTemplate == null) {
            return;
        }
        s21MailTemplate.setTemplateParameter("errDate", errorTime);
        s21MailTemplate.setTemplateParameter("message", S21MessageFunc.clspGetMessage(NWZM2323E, new String[] {ordSrcRefNum, cpoOrdNum }));
        mail.setMailTemplate(s21MailTemplate);

        // From Address
        S21MailGroup groupFrom = new S21MailGroup(glblCmpyCd, ML_GRP_ID_01);
        if (groupFrom == null) {
            return;
        }
        S21MailAddress fromAddress = groupFrom.getMailAddress().get(0);
        mail.setFromAddress(fromAddress);

        // To Address
        S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, ML_GRP_ID_01);
        if (groupTo == null) {
            return;
        }
        S21MailAddress toAddress = groupTo.getMailAddress().get(0);
        mail.setToAddress(toAddress);

        mail.postMail();
    }
    // 2023/09/20 QC#61782 Add End
}
