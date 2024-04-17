/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NPZ.NPZC104001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDDBCICarrier;
import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_PO_TPTMsg;
import business.db.MDSETMsg;
import business.db.POTMsg;
import business.db.PO_ACCTTMsg;
import business.db.PO_DTLTMsg;
import business.db.PO_MSGTMsg;
import business.db.PRCH_REQTMsg;
import business.db.RCV_ASN_VNDTMsg;
import business.db.RTL_WHTMsg;
import business.db.SHPG_PLNTMsg;
import business.db.SHPG_SVC_LVLTMsg;
import business.parts.NLZC205001PMsg;
import business.parts.NPZC004001PMsg;
import business.parts.NPZC103001PMsg;
import business.parts.NPZC104001PMsg;
import business.parts.NPZC104001_poAcctInfoPMsg;
import business.parts.NPZC104001_poLineInfoPMsg;
import business.parts.NPZC104001_serNumListPMsg;
import business.parts.NPZC109001PMsg;
import business.parts.NSZC001001PMsg;
import business.parts.NWZC003001PMsg;
import business.parts.NWZC107001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC205001.NLZC205001;
import com.canon.cusa.s21.api.NPZ.NPZC004001.NPZC004001;
import com.canon.cusa.s21.api.NPZ.NPZC103001.NPZC103001;
import com.canon.cusa.s21.api.NPZ.NPZC103001.constant.NPZC103001Constant;
import com.canon.cusa.s21.api.NPZ.NPZC104001.constant.NPZC104001Constant;
import com.canon.cusa.s21.api.NPZ.NPZC109001.NPZC109001;
import com.canon.cusa.s21.api.NPZ.NPZC109001.constant.NPZC109001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC001001.NSZC001001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.ProcessMode;
import com.canon.cusa.s21.api.NWZ.NWZC003001.NWZC003001;
import com.canon.cusa.s21.api.NWZ.NWZC107001.NWZC107001;
import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001CreatePOHistory;
import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001PoMsg;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_PO_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_CHRG_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_CHRG_TO;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_APVL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MATCH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MDSE_CMPSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MSG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_APVL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_SVC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_OWNR;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * PO Create API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/07/2015   CITS            T.kikuhara      Create          CSA Ver3.0
 * 03/24/2016   CSAI            K.Lee           Update          QC#6023
 * 03/24/2016   CSAI            K.Lee           Update          QC#6180
 * 05/03/2016   CSAI            K.Lee           Update          QC#6460
 * 05/03/2016   CSAI            K.Lee           Update          QC#7196
 * 05/03/2016   CSAI            K.Lee           Update          QC#7300
 * 05/03/2016   CSAI            K.Lee           Update          QC#5762
 * 05/06/2016   CSAI            K.Lee           Update          QC#8009
 * 09/14/2016   CITS            R.Shimamoto     Update          QC#13985
 * 09/29/2016   CITS            T.Gotoda        Update          QC#13163
 * 10/07/2016   CITS            R.Shimamoto     Update          QC#13212
 * 11/01/2016   CITS            T.Hakodate      Update          QC#8424
 * 11/21/2016   CITS            R.Shimamoto     Update          QC#16122
 * 12/14/2016   CITS            R.Shimamoto     Update          QC#15817
 * 12/20/2016   CITS            S.Endo          Update          QC#16715
 * 02/27/2017   CITS            S.Endo          Update          QC#17806
 * 05/12/2017   CITS            R.Shimamoto     Update          QC#18473
 * 08/04/2017   CITS            R.Shimamoto     Update          QC#18671
 * 10/12/2017   CITS            T.Wada          Update          QC#18644
 * 10/20/2017   CITS            S.Katsuma       Update          QC#21206
 * 11/20/2017   CITS            T.Tokutomi      Update          QC#22610
 * 11/21/2017   CITS            K.Ogino         Update          QC#22190
 * 01/10/2018   CITS            K.Ogino         Update          QC#23126
 * 02/16/2018   CITS            K.Ogino         Update          QC#23936
 * 03/15/2018   CITS            K.Ogino         Update          QC#24780
 * 05/31/2018   CITS            K.Kameoka       Update          QC#26228
 * 06/21/2018   CITS            K.Kameoka       Update          QC#18420
 * 07/30/2018   CITS            K.Kameoka       Update          QC#27024
 * 09/18/2018   CITS            K.Ogino         Update          QC#28261
 * 01/31/2019   CITS            K.Ogino         Update          QC#30039
 * 02/15/2019   CITS            M.Naito         Update          QC#30354
 * 05/21/2019   CITS            K.Ogino         Update          QC#50139
 * 07/30/2019   Fujitsu         T.Ogura         Update          QC#51446
 * 08/09/2019   Fujitsu         T.Ogura         Update          QC#52359
 * 09/25/2019   CITS            R.Shimamoto     Update          QC#52460
 * 10/08/2019   CITS            R.Shimamoto     Update          QC#53392
 * 11/18/2019   CITS            R.Shimamoto     Update          QC#54667
 * 11/21/2019   CITS            R.Shimamoto     Update          QC#54765
 * 12/10/2019   CITS            R.Shimamoto     Update          QC#54947
 * 12/10/2019   CITS            R.Shimamoto     Update          QC#55023
 * 12/20/2019   CITS            R.Shimamoto     Update          QC#55085
 * 03/02/2020   Fujitsu         T.Ogura         Update          QC#55920
 * 03/13/2020   Fujitsu         T.Ogura         Update          QC#55881
 * 04/06/2020   Fujitsu         T.Ogura         Update          QC#56390
 * 06/04/2020   Fujitsu         T.Ogura         Update          QC#56912
 * 05/26/2021   CITS            M.Furugoori     Update          QC#58872
 * 09/14/2021   CITS            K.Watanabe      Update          QC#59193
 * 03/24/2022   CITS            A.Cullano       Update          QC#59359
 * 02/15/2023   Hitachi         S.Dong          Update          QC#60966
 * </pre>
 */
public class NPZC104001 extends S21ApiCommonBase {

    /** S21ApiMessageMap */
    private S21ApiMessageMap glMsgMap = null;

    /** S21SsmBatchClient */
    private S21SsmBatchClient glSsmBatchClient = null;

    /** OnBatchType */
    private ONBATCH_TYPE glOnBatchType = null;

    /** NPZC104001PMsg */
    private NPZC104001PMsg glWkPmsg = null;

    /** NPZC104001_poLineInfoPMsg List */
    private List<NPZC104001_poLineInfoPMsg> glPoLineInfoList = null;

    /** NPZC104001_serNumListPMsg List */
    private List<NPZC104001_serNumListPMsg> glSerNumList = null;

    /** NPZC104001_poAcctInfoPMsg List */
    private List<NPZC104001_poAcctInfoPMsg> glPoAcctInfoList = null;

    /** NPZC104001InternalInfoBean List */
    private List<NPZC104001InternalInfoBean> glInternalInfoList = null;

    /** SetItem NPZC104001_poLineInfoPMsg List */
    private List<NPZC104001_poLineInfoPMsg> glSetPoLineInfoList = null;

    /** SetItem NPZC104001_poAcctInfoPMsg List */
    private List<NPZC104001_poAcctInfoPMsg> glSetPoAcctInfoList = null;

    /** SetItem NPZC104001InternalInfoBean List */
    private List<NPZC104001InternalInfoBean> glSetInternalInfoBeanList = null;

    // QC#18644
    /** TPL_WH List */
    private List<String> glTplWhList = null;

    /**
     * Constructor
     */
    public NPZC104001() {
        super();
    }

    /**
     * <pre>
     * PO Create API
     * </pre>
     * @param param NPZC104001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NPZC104001PMsg param, final ONBATCH_TYPE onBatchType) {

        glMsgMap = new S21ApiMessageMap(param);
        glSsmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        glOnBatchType = onBatchType;
        glWkPmsg = (NPZC104001PMsg) param.clone();
        glInternalInfoList = new ArrayList<NPZC104001InternalInfoBean>();

        // QC#18644
        glTplWhList = new ArrayList<String> ();
        glTplWhList.add(WH_OWNR.DBS);
        glTplWhList.add(WH_OWNR.APX);
        glTplWhList.add(WH_OWNR.STI);
        glTplWhList.add(WH_OWNR.CHOICE);
        glTplWhList.add(WH_OWNR.TP);

        NPZC104001Common.checkGlblCmpyCd(glWkPmsg, glMsgMap);
        NPZC104001Common.checkMode(glWkPmsg, glMsgMap);
        if (glMsgMap.getMsgMgr().isXxMsgId()) {
            glMsgMap.flush();
            return;
        }

        // Sort poLineInfo By PO_ORD_DTL_LINE_NUM
        glPoLineInfoList = new ArrayList<NPZC104001_poLineInfoPMsg>();
        for (int i = 0; i < glWkPmsg.poLineInfo.getValidCount(); i++) {
            glPoLineInfoList.add(glWkPmsg.poLineInfo.no(i));
        }
        Collections.sort(glPoLineInfoList, new NPZC104001Common.Comp(NPZC104001Constant.SORT_KEY_PO_ORD_DTL_LINE_NUM));

        // Sort serNumList By PO_ORD_DTL_LINE_NUM
        glSerNumList = new ArrayList<NPZC104001_serNumListPMsg>();
        for (int i = 0; i < glWkPmsg.serNumList.getValidCount(); i++) {
            glSerNumList.add(glWkPmsg.serNumList.no(i));
        }
        Collections.sort(glSerNumList, new NPZC104001Common.Comp(NPZC104001Constant.SORT_KEY_PO_ORD_DTL_LINE_NUM));

        // Sort poAcctInfo By PO_ORD_DTL_LINE_NUM
        glPoAcctInfoList = new ArrayList<NPZC104001_poAcctInfoPMsg>();
        for (int i = 0; i < glWkPmsg.poAcctInfo.getValidCount(); i++) {
            glPoAcctInfoList.add(glWkPmsg.poAcctInfo.no(i));
        }
        Collections.sort(glPoAcctInfoList, new NPZC104001Common.Comp(NPZC104001Constant.SORT_KEY_PO_ORD_DTL_LINE_NUM));

        // SetItem List
        glSetPoLineInfoList = new ArrayList<NPZC104001_poLineInfoPMsg>();
        glSetPoAcctInfoList = new ArrayList<NPZC104001_poAcctInfoPMsg>();
        glSetInternalInfoBeanList = new ArrayList<NPZC104001InternalInfoBean>();

        // Get Common Intenal Info
        NPZC104001InternalInfoBean internalInfo = NPZC104001Common.commonInitProcess(glWkPmsg);
        glInternalInfoList.add(internalInfo);

        // divide mode process
        String mode = glWkPmsg.xxModeCd.getValue();
        if (NPZC104001Constant.MODE_CREATE.equals(mode)) {
            createProcess();
        // QC#24780
        } else if (NPZC104001Constant.MODE_UPDATE.equals(mode) || NPZC104001Constant.MODE_ASN.equals(mode)) {
            updateProcess();
        } else if (NPZC104001Constant.MODE_CANCEL.equals(mode)) {
            cancelProcess();
        } else if (NPZC104001Constant.MODE_SEND_PO.equals(mode)) {
            sendPoProcess();
        }

        endProcess(param);

        glMsgMap.flush();

        return;
    }

    private void createProcess() {

        NPZC104001Common.checkParamForCreateAndUpdate(glWkPmsg, glMsgMap, glSsmBatchClient);
        if (glMsgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        if (!consistencyCheck()) {
            return;
        }

        if (!createPo()) {
            return;
        }

    }

    private void updateProcess() {
        NPZC104001Common.checkParamForCreateAndUpdate(glWkPmsg, glMsgMap, glSsmBatchClient);
        if (glMsgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        if (!consistencyCheck()) {
            return;
        }

        if (!updatePo()) {
            return;
        }

    }

    private void cancelProcess() {
        NPZC104001Common.checkParamForCancelAndSendPo(glWkPmsg, glMsgMap);
        if (glMsgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        if (!cancelPo()) {
            return;
        }
    }

    private void sendPoProcess() {
        NPZC104001Common.checkParamForCancelAndSendPo(glWkPmsg, glMsgMap);
        if (glMsgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        if (!sendPo()) {
            return;
        }
    }

    private void endProcess(NPZC104001PMsg param) {

        // Out Param set

        // Set PO Detail Error
        for (NPZC104001_poLineInfoPMsg poLineInfo : glPoLineInfoList) {
            if (hasValue(poLineInfo.xxMsgId)) {
                String wkPoOrdDtlLineNum = poLineInfo.poOrdDtlLineNum.getValue();
                for (int i = 0; i < param.poLineInfo.getValidCount(); i++) {
                    if (wkPoOrdDtlLineNum.equals(param.poLineInfo.no(i).poOrdDtlLineNum.getValue())) {
                        ZYPEZDItemValueSetter.setValue(param.poLineInfo.no(i).xxMsgId, poLineInfo.xxMsgId);
                    }
                }
            }
        }

        // Set New PO_ORD_NUM
        if (!hasValue(param.poOrdNum) && hasValue(glWkPmsg.poOrdNum)) {
            ZYPEZDItemValueSetter.setValue(param.poOrdNum, glWkPmsg.poOrdNum);
        }

        // Set SetItem PO_ORD_DTL_LINE_NUM
        int i = param.poLineInfo.getValidCount();
        for (NPZC104001_poLineInfoPMsg poLineInfo : glSetPoLineInfoList) {
            ZYPEZDItemValueSetter.setValue(param.poLineInfo.no(i).poOrdDtlLineNum, poLineInfo.poOrdDtlLineNum);
            i++;
        }
        i = param.poAcctInfo.getValidCount();
        for (NPZC104001_poAcctInfoPMsg poAcctInfo : glSetPoAcctInfoList) {
            ZYPEZDItemValueSetter.setValue(param.poAcctInfo.no(i).poOrdDtlLineNum, poAcctInfo.poOrdDtlLineNum);
            i++;
        }

        // Set New SerNum
        for (NPZC104001_serNumListPMsg serNumInfo : glSerNumList) {
            String wkPoOrdDtlLineNum = serNumInfo.poOrdDtlLineNum.getValue();
            for (int j = 0; j < param.serNumList.getValidCount(); j++) {
                if (wkPoOrdDtlLineNum.equals(param.serNumList.no(j).poOrdDtlLineNum.getValue()) && !hasValue(param.serNumList.no(j).serNum)) {
                    ZYPEZDItemValueSetter.setValue(param.serNumList.no(j).serNum, serNumInfo.serNum);
                }
            }
        }

        // Set New PO_MSG_PK
        for (int k = 0; k < glWkPmsg.poInfo.getValidCount(); k++) {
            if (hasValue(glWkPmsg.poInfo.no(k).poMsgPk) && !hasValue(param.poInfo.no(k).poMsgPk)) {
                ZYPEZDItemValueSetter.setValue(param.poInfo.no(k).poMsgPk, glWkPmsg.poInfo.no(k).poMsgPk);
            }
        }

    }

    private boolean consistencyCheck() {

        NPZC104001Common.setDefaultParamCreate(glWkPmsg);

        NPZC104001Common.setDisplayLine(glWkPmsg, glSsmBatchClient);

        // Check Header Level

        NPZC104001InternalInfoBean wkInternalInfo = glInternalInfoList.get(0);
        if (!NPZC104001Common.checkDsPoTpData(glWkPmsg, glMsgMap, wkInternalInfo)) {
            return false;
        }
        if (!NPZC104001Common.checkShipToCustData(glWkPmsg, glMsgMap, glSsmBatchClient, glOnBatchType)) {
            return false;
        }
        if (!NPZC104001Common.checkVendorData(glWkPmsg, glMsgMap, glSsmBatchClient)) {
            return false;
        }
        // QC#13163 Start
        if (!NPZC104001Common.checkVndPmtTermCd(glWkPmsg, glMsgMap, glSsmBatchClient)) {
            return false;
        }
        // QE#13163 End
        if (!NPZC104001Common.checkRetailWarehouseData(glWkPmsg, glMsgMap, glSsmBatchClient, wkInternalInfo)) {
            return false;
        }
        if (!NPZC104001Common.setBillToCustCd(glWkPmsg, wkInternalInfo)) {
            return false;
        }
        if (!NPZC104001Common.checkCarrierShipServLv(glWkPmsg, glMsgMap, glSsmBatchClient, wkInternalInfo)) {
            return false;
        }
        if (!NPZC104001Common.checkBuyBackPoLocation(glWkPmsg, glMsgMap, glSsmBatchClient)) {
            return false;
        }

        // Check Detail Level

        // for rtlSwhCd different check val
        String oldDestRtlSwhCd = "";
        // for mdsecd different check val
        String oldMdseCd = glPoLineInfoList.get(0).mdseCd.getValue();
        // Max PO_ORD_DTL_LINE_NUM
        String maxPoOrdDtlLineNum = "";
        String invtyLocCdWk = "";

        HashSet<BigDecimal> svcConfigMstrPkSet = new HashSet<BigDecimal>();

        for (int i = 0; i < glPoLineInfoList.size(); i++) {

            NPZC104001_poLineInfoPMsg poLineInfo = glPoLineInfoList.get(i);

            // inner info bean initialize
            if (i > 0) {
                wkInternalInfo = new NPZC104001InternalInfoBean();
            }

            // check Line Type
            if (!NPZC104001Common.checkLineType(glWkPmsg, poLineInfo, i, glMsgMap, wkInternalInfo)) {
                return false;
            }

            // check rtlSwhCd is different
            if (i == 0 || !oldDestRtlSwhCd.equals(poLineInfo.destRtlSwhCd.getValue())) {

                oldDestRtlSwhCd = poLineInfo.destRtlSwhCd.getValue();

                // check Detail Location
                if (!NPZC104001Common.checkDetailLocation(glWkPmsg, poLineInfo, i, glMsgMap, wkInternalInfo, glSsmBatchClient)) {

                    return false;

                } else {

                    invtyLocCdWk = poLineInfo.invtyLocCd.getValue();

                }

            } else { // if same rtlSwhCd. set same info

                if (i > 0) {

                    if (!hasValue(poLineInfo.invtyLocCd)) {

                        // ZYPEZDItemValueSetter.setValue(poLineInfo.invtyLocCd,
                        // glWkPmsg.poLineInfo.no(i -
                        // 1).invtyLocCd.getValue());
                        ZYPEZDItemValueSetter.setValue(poLineInfo.invtyLocCd, invtyLocCdWk);
                    }

                    // inner info
                    wkInternalInfo.setInvtyLocNm(glInternalInfoList.get(i - 1).getInvtyLocNm());
                }
            }

            // check Item
            if (!hasValue(poLineInfo.mdseDescShortTxt) || !hasValue(poLineInfo.poMatchTpCd) || !hasValue(poLineInfo.poMdseCmpsnTpCd)) {
                if (!NPZC104001Common.checkItem(glWkPmsg, poLineInfo, i, glMsgMap, glSsmBatchClient, wkInternalInfo)) {
                    return false;
                }
            }
            if (!NPZC104001Common.checkVendorItemUom(glWkPmsg, poLineInfo, i, glMsgMap, glSsmBatchClient, glOnBatchType)) {
                return false;
            }
            // START 04/06/2020 T.Ogura [QC#56390,MOD]
//            if (!NPZC104001Common.changeQty(glWkPmsg, poLineInfo, i, glMsgMap, glOnBatchType)) {
            if (!NPZC104001Common.changeQty(glWkPmsg, poLineInfo, i, glMsgMap, glSsmBatchClient, glOnBatchType)) {
            // END   04/06/2020 T.Ogura [QC#56390,MOD]
                return false;
            }
            if (!NPZC104001Common.changePrice(glWkPmsg, poLineInfo, i, glMsgMap)) {
                return false;
            }
            if (!getAccount(poLineInfo, i, wkInternalInfo)) {
                return false;
            }
            // START 03/02/2020 T.Ogura [QC#55920,MOD]
//            if (!NPZC104001Common.checkSerial(glWkPmsg, poLineInfo, glSerNumList, i, glMsgMap, wkInternalInfo)) {
            if (!NPZC104001Common.checkSerial(glWkPmsg, poLineInfo, glSerNumList, i, glMsgMap, glSsmBatchClient, wkInternalInfo)) {
            // END   03/02/2020 T.Ogura [QC#55920,MOD]
                return false;
            }
            if (!NPZC104001Common.checkBuyBackPoConf(glWkPmsg, poLineInfo, i, glMsgMap, glSsmBatchClient, wkInternalInfo, svcConfigMstrPkSet)) {
                return false;
            }

            if (svcConfigMstrPkSet.isEmpty() || !svcConfigMstrPkSet.contains(poLineInfo.svcConfigMstrPk.getValue())) {

                svcConfigMstrPkSet.add(poLineInfo.svcConfigMstrPk.getValue());
            }

            maxPoOrdDtlLineNum = checkSet(maxPoOrdDtlLineNum, poLineInfo, i);

            if (NPZC104001Common.checkCusaParts(glWkPmsg)) {
            	// 2019/09/25 QC#52460 Mod Start
//                if (i > 0 && oldMdseCd.equals(glPoLineInfoList.get(i).mdseCd.getValue())) {
//                    glMsgMap.addXxMsgId(NPZC104001Constant.NPAM1441E);
//                    ZYPEZDItemValueSetter.setValue(glWkPmsg.poLineInfo.no(i).xxMsgId, NPZC104001Constant.NPAM1441E);
//                    return false;
//                }
            	String basePoLineStsCd = null;
            	if (ZYPCommonFunc.hasValue(glPoLineInfoList.get(i).poLineStsCd)) {
            		basePoLineStsCd = glPoLineInfoList.get(i).poLineStsCd.getValue();
            	}
            	if (basePoLineStsCd == null || (!PO_LINE_STS.CANCELLED.equals(basePoLineStsCd) && !PO_LINE_STS.CLOSED.equals(basePoLineStsCd))) {

            		for (int n = 0; n < glPoLineInfoList.size(); n++) {
            			String targetPoLineStsCd = null;
            			if (ZYPCommonFunc.hasValue(glPoLineInfoList.get(n).poLineStsCd)) {
            				targetPoLineStsCd = glPoLineInfoList.get(n).poLineStsCd.getValue();
                    	}
                		if (i == n) {
                			continue;
                		} else if (PO_LINE_STS.CANCELLED.equals(targetPoLineStsCd) || PO_LINE_STS.CLOSED.equals(targetPoLineStsCd)) {
                			// check skip Line Status Cancel and Closed
                			continue;
                		} else {
                			if (glPoLineInfoList.get(i).mdseCd.getValue().equals(glPoLineInfoList.get(n).mdseCd.getValue())) {
                    			glMsgMap.addXxMsgId(NPZC104001Constant.NPAM1441E);
                    			ZYPEZDItemValueSetter.setValue(glWkPmsg.poLineInfo.no(i).xxMsgId, NPZC104001Constant.NPAM1441E);
                    			return false;
                    		}
                		}

                	}
            		
                }
                // 2019/09/25 QC#52460 Mod End
            }

            glInternalInfoList.add(wkInternalInfo);

        } // detail loop end

        // Add SetItem to Detail
        for (NPZC104001_poLineInfoPMsg setPoLineInfo : glSetPoLineInfoList) {
            glPoLineInfoList.add(setPoLineInfo);
        }
        for (NPZC104001_poAcctInfoPMsg setPoAcctInfo : glSetPoAcctInfoList) {
            glPoAcctInfoList.add(setPoAcctInfo);
        }
        for (NPZC104001InternalInfoBean setInternalInfoBean : glSetInternalInfoBeanList) {
            glInternalInfoList.add(setInternalInfoBean);
        }

        return true;
    }

    // get Account
    // QC#13985 2016/09/14 Mod Start
    private boolean getAccount(NPZC104001_poLineInfoPMsg poLineInfo, int i, NPZC104001InternalInfoBean wkInternalInfo) {

        NPZC104001_poAcctInfoPMsg poAcctInfo;
        if (glPoAcctInfoList.size() == 0) {
            poAcctInfo = null;
        } else if (glPoAcctInfoList.size() > i) {
            poAcctInfo = glPoAcctInfoList.get(i);
        } else {
            poAcctInfo = null;
        }

        // QC#15817 2016/12/14 Mod.
//        boolean getActFlg = false;
//
//        if (poAcctInfo == null) {
//            if (NPZC104001Constant.MODE_CREATE.equals(glWkPmsg.xxModeCd.getValue())) {
//                getActFlg = true;
//            } else if (NPZC104001Constant.MODE_UPDATE.equals(glWkPmsg.xxModeCd.getValue())) {
//                getActFlg = true;
//            }
//        } else {
//            if (!hasValue(poAcctInfo.poAcctTpCd)) {
//                if (NPZC104001Constant.MODE_CREATE.equals(glWkPmsg.xxModeCd.getValue())) {
//                    getActFlg = true;
//                } else if (NPZC104001Constant.MODE_UPDATE.equals(glWkPmsg.xxModeCd.getValue())) {
//                    getActFlg = true;
//                }
//
//            } else {
//                if (!NPZC104001Common.checkPoAcctInfo(glWkPmsg, glMsgMap)) {
//                    return false;
//                }
//            }
//        }
        if (poAcctInfo != null && hasValue(poAcctInfo.poAcctTpCd)) {
            if (!NPZC104001Common.checkPoAcctInfo(glWkPmsg, glMsgMap)) {
                return false;
            }
        }

//        if (getActFlg) {
//            // get Default Account Info
//            poAcctInfo = new NPZC104001_poAcctInfoPMsg();
//            String glblCmpyCd = glWkPmsg.glblCmpyCd.getValue();
//            CM_DEF_ACCTTMsg cmDefActTMsg = NPZC104001DBAccess.getCmDefAcct(glblCmpyCd, wkInternalInfo.getAcrlCmDefAcctCd());
//            // set param
//            ZYPEZDItemValueSetter.setValue(poAcctInfo.poOrdDtlLineNum, poLineInfo.poOrdDtlLineNum);
//            ZYPEZDItemValueSetter.setValue(poAcctInfo.poAcctTpCd, PO_ACCT_TP.ACCRUAL);
//            ZYPEZDItemValueSetter.setValue(poAcctInfo.coaCmpyCd, cmDefActTMsg.cmCoaCmpyCd);
//            ZYPEZDItemValueSetter.setValue(poAcctInfo.coaAfflCd, cmDefActTMsg.cmCoaAfflCd);
//            ZYPEZDItemValueSetter.setValue(poAcctInfo.coaChCd, cmDefActTMsg.cmCoaChCd);
//            ZYPEZDItemValueSetter.setValue(poAcctInfo.coaCcCd, cmDefActTMsg.cmCoaCcCd);
//            ZYPEZDItemValueSetter.setValue(poAcctInfo.coaProdCd, cmDefActTMsg.cmCoaProdCd);
//            ZYPEZDItemValueSetter.setValue(poAcctInfo.coaProjCd, cmDefActTMsg.cmCoaProjCd);
//            ZYPEZDItemValueSetter.setValue(poAcctInfo.coaExtnCd, cmDefActTMsg.cmCoaExtnCd);
//            ZYPEZDItemValueSetter.setValue(poAcctInfo.coaBrCd, cmDefActTMsg.cmCoaBrCd);
//            ZYPEZDItemValueSetter.setValue(poAcctInfo.coaAcctCd, cmDefActTMsg.cmCoaAcctCd);
//
//            if (PO_LINE_TP.GOODS.equals(poLineInfo.poLineTpCd.getValue())) {
//                // get Branch by Retail Warehouse Account
//                RTL_WHTMsg rtlWhTMsg = NPZC104001DBAccess.getRtlWh(glblCmpyCd, glWkPmsg.destRtlWhCd.getValue());
//                if (rtlWhTMsg == null) {
//                    glMsgMap.addXxMsgId(NPZC104001Constant.NPZM0232E);
//                    ZYPEZDItemValueSetter.setValue(poLineInfo.xxMsgId, NPZC104001Constant.NPZM0232E);
//                    return false;
//                }
//
//                if (hasValue(rtlWhTMsg.coaBrCd)) {
//                    ZYPEZDItemValueSetter.setValue(poAcctInfo.coaBrCd, rtlWhTMsg.coaBrCd);
//                }
//
//                // get Account by Item
//                Map<String, Object> coaProjAcct = NPZC104001DBAccess.getCoaProjAcct(glblCmpyCd, poLineInfo.mdseCd.getValue(), glSsmBatchClient);
//                if (coaProjAcct == null || coaProjAcct.isEmpty()) {
//                    glMsgMap.addXxMsgId(NPZC104001Constant.NPZM0234E);
//                    ZYPEZDItemValueSetter.setValue(poLineInfo.xxMsgId, NPZC104001Constant.NPZM0234E);
//                    return false;
//                }
//
//                if (coaProjAcct.get(NPZC104001Constant.COA_ACCT_CD) != null) {
//                    ZYPEZDItemValueSetter.setValue(poAcctInfo.coaAcctCd, (String) coaProjAcct.get(NPZC104001Constant.COA_ACCT_CD));
//                }
//            }
//            glPoAcctInfoList.add(poAcctInfo);
//        }

        return true;
    }

    // QC#13985 2016/09/14 Mod End

    private String checkSet(String maxPoOrdDtlLineNum, NPZC104001_poLineInfoPMsg poLineInfo, int i) {
        String newMaxPoOrdDtlLineNum = null;

        String poMdseCmpsnTpCd = poLineInfo.poMdseCmpsnTpCd.getValue();

        if (poMdseCmpsnTpCd != null && PO_MDSE_CMPSN_TP.PARENT.equals(poMdseCmpsnTpCd)) {
            String poOrdDtlLineNum = poLineInfo.poOrdDtlLineNum.getValue();
            for (int j = 0; j < glWkPmsg.poLineInfo.getValidCount(); j++) {
                if (poOrdDtlLineNum.equals(glWkPmsg.poLineInfo.no(j).setPoOrdDtlLineNum.getValue())) {
                    return null;
                }
            }
        }

        String strPparentDispPoDtlLineNum = "";
        if (poMdseCmpsnTpCd != null && (PO_MDSE_CMPSN_TP.CHILD.equals(poMdseCmpsnTpCd) || PO_MDSE_CMPSN_TP.REGULAR.equals(poMdseCmpsnTpCd))) {
            return null;
        } else {
            maxPoOrdDtlLineNum = glPoLineInfoList.get(glPoLineInfoList.size() - 1).poOrdDtlLineNum.getValue();
            String tmpDispPoDtlLineNum = poLineInfo.dispPoDtlLineNum.getValue();
            if (ZYPCommonFunc.hasValue(tmpDispPoDtlLineNum)) {
                int dispLineNum = Integer.parseInt(tmpDispPoDtlLineNum.substring(0, tmpDispPoDtlLineNum.indexOf(".")));
                strPparentDispPoDtlLineNum = String.valueOf(dispLineNum);
            }
        }

        // get Set Component Info
        String glblCmpyCd = glWkPmsg.glblCmpyCd.getValue();
        String mdseCd = poLineInfo.mdseCd.getValue();
        String poSubmtTs = glWkPmsg.poSubmtTs.getValue();
        List<Map<String, Object>> setCompList = NPZC104001DBAccess.getSetComponent(glblCmpyCd, mdseCd, poSubmtTs, glSsmBatchClient);
        if (setCompList == null || setCompList.size() == 0) {
            glMsgMap.addXxMsgId(NPZC104001Constant.NPAM1439E);
            return null;
        }

        int wkPoOrdDtlLineNum = 0;
        int wkPoDispLineNum = 0;
        try {
            wkPoOrdDtlLineNum = Integer.parseInt(maxPoOrdDtlLineNum) + glSetPoLineInfoList.size();
            String dispPoDtlLineNum = poLineInfo.dispPoDtlLineNum.getValue();
            wkPoDispLineNum = Integer.parseInt(dispPoDtlLineNum.substring(dispPoDtlLineNum.indexOf(".") + 1));
        } catch (NumberFormatException nfex) {
            return null;
        }

        // Get SetItem
        int childIndex = 1;
        for (int j = 0; j < setCompList.size(); j++) {
            wkPoOrdDtlLineNum++;
            wkPoDispLineNum++;
            DecimalFormat df = new DecimalFormat("000");
            newMaxPoOrdDtlLineNum = df.format(wkPoOrdDtlLineNum);

            // PO_LINE_MAX_CNT
            String poLineMaxCnt = glInternalInfoList.get(0).getPoLineMaxCnt();
            if (newMaxPoOrdDtlLineNum.compareTo(poLineMaxCnt) > 0) {
                glMsgMap.addXxMsgId(NPZC104001Constant.NPAM1440E);
                ZYPEZDItemValueSetter.setValue(glWkPmsg.poLineInfo.no(i).xxMsgId, NPZC104001Constant.NPAM1440E);
                return null;
            }

            NPZC104001_poLineInfoPMsg wkPoLineInfoPMsg = new NPZC104001_poLineInfoPMsg();

            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.poOrdDtlLineNum, newMaxPoOrdDtlLineNum);
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.dispPoDtlLineNum, strPparentDispPoDtlLineNum + "." + String.valueOf(childIndex));
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.poLineTpCd, poLineInfo.poLineTpCd);
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.poMdseCmpsnTpCd, PO_MDSE_CMPSN_TP.CHILD);
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.setPoOrdDtlLineNum, poLineInfo.poOrdDtlLineNum);
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.mdseCd, (String) setCompList.get(j).get(NPZC104001Constant.CHILD_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.mdseNm, (String) setCompList.get(j).get(NPZC104001Constant.MDSE_NM));
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.mdseDescShortTxt, (String) setCompList.get(j).get(NPZC104001Constant.MDSE_DESC_SHORT_TXT));
//            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.poQty, (BigDecimal) setCompList.get(j).get(NPZC104001Constant.CHILD_MDSE_QTY));
            //QC#16122
//            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.poDispQty, (BigDecimal) setCompList.get(j).get(NPZC104001Constant.CHILD_MDSE_QTY));
            BigDecimal chldQty = (BigDecimal) setCompList.get(j).get(NPZC104001Constant.CHILD_MDSE_QTY);
            BigDecimal prntQty = poLineInfo.poDispQty.getValue();
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.poDispQty, chldQty.multiply(prntQty));
            // QC#18473 Mod.
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.poQty, chldQty.multiply(prntQty));

            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.poInvQty, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.poDispUomCd, VND_UOM.EACH);
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.thisMthFobCostAmt, BigDecimal.ZERO);
            //QC#18420 Add Start
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.poDtlDiscPct, BigDecimal.ZERO);
            //QC#18420 Add End
            // QC#53392 2019/10/05 Add Start
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.poDtlDiscPrcAmt, BigDecimal.ZERO);
            // QC#53392 2019/10/05 Add End
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.entDealNetUnitPrcAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.entPoDtlDealNetAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.entFuncNetUnitPrcAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.entPoDtlFuncNetAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.exchRate, poLineInfo.exchRate);
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.custUomCd, poLineInfo.custUomCd);
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.destRtlSwhCd, poLineInfo.destRtlSwhCd);
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.srcRtlSwhCd, poLineInfo.srcRtlSwhCd);
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.invtyLocCd, poLineInfo.invtyLocCd);
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.rqstRcvDt, poLineInfo.rqstRcvDt);
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.rqstRcvTm, poLineInfo.rqstRcvTm);
            // START 2023/02/15 S.Dong [QC#60966, ADD]
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.rqstShipDt, poLineInfo.rqstShipDt);
            // END 2023/02/15 S.Dong [QC#60966, ADD]
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.frtCondCd, poLineInfo.frtCondCd);
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.origMdseCd, (String) setCompList.get(j).get(NPZC104001Constant.CHILD_MDSE_CD));
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.fromStkStsCd, poLineInfo.fromStkStsCd);
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.toStkStsCd, poLineInfo.toStkStsCd);
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.adminPsnCd, poLineInfo.adminPsnCd);

            if (!hasValue(poLineInfo.poMatchTpCd)) {
                ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.poMatchTpCd, poLineInfo.poMatchTpCd);
            } else {
                if (ZYPConstant.FLG_ON_Y.equals(poLineInfo.poMatchTpCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.poMatchTpCd, PO_MATCH_TP.RECEIPT);
                } else {
                    ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.poMatchTpCd, PO_MATCH_TP.NO_GOODS_RECEIPT);
                }
            }

            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.ordQty, (BigDecimal) setCompList.get(j).get(NPZC104001Constant.CHILD_MDSE_QTY));
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.cpoOrdNum, poLineInfo.cpoOrdNum);
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.cpoDtlLineNum, poLineInfo.cpoDtlLineNum);
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.cpoDtlLineSubNum, poLineInfo.cpoDtlLineSubNum);
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.custIssPoNum, poLineInfo.custIssPoNum);
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.custIssPoDt, poLineInfo.custIssPoDt);
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.cpoOrdTpCd, poLineInfo.cpoOrdTpCd);
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.billToCustCd, poLineInfo.billToCustCd);
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.sellToCustCd, poLineInfo.sellToCustCd);
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.shpgPlnNum, poLineInfo.shpgPlnNum);
            // QC#18473 Mod.
//            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.prchReqNum, poLineInfo.poLineTpCd);
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.prchReqNum, poLineInfo.prchReqNum);

            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.prchReqLineNum, poLineInfo.prchReqLineNum);
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.prchReqLineSubNum, poLineInfo.prchReqLineSubNum);
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.trxRefNum, poLineInfo.trxRefNum);
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.trxRefLineNum, poLineInfo.trxRefLineNum);
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.trxRefLineSubNum, poLineInfo.trxRefLineSubNum);
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.shipFromSoNumListTxt, poLineInfo.shipFromSoNumListTxt);
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.vndInvtyLocCd, poLineInfo.vndInvtyLocCd);
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.vndIssPoOrdNum, poLineInfo.vndIssPoOrdNum);
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.vndIssPoOrdNum, poLineInfo.vndIssPoOrdNum);
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.proNum, poLineInfo.proNum);
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.vndPoAckStsCd, poLineInfo.vndPoAckStsCd);
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.origPoOrdNum, glWkPmsg.poOrdNum);
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.origPoOrdDtlLineNum, newMaxPoOrdDtlLineNum);
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.origDispPoDtlLineNum, strPparentDispPoDtlLineNum + "." + String.valueOf(childIndex));
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.svcConfigMstrPk, poLineInfo.svcConfigMstrPk);
            ZYPEZDItemValueSetter.setValue(wkPoLineInfoPMsg.poSendTs, poLineInfo.poSendTs);
            glSetPoLineInfoList.add(wkPoLineInfoPMsg);

            NPZC104001InternalInfoBean wkInternalInfoBean = new NPZC104001InternalInfoBean();
            wkInternalInfoBean.setInvtyCtrlFlg((String) setCompList.get(j).get(NPZC104001Constant.INVTY_CTRL_FLG));
            wkInternalInfoBean.setRcvSerTakeFlg((String) setCompList.get(j).get(NPZC104001Constant.RCV_SER_TAKE_FLG));
            glSetInternalInfoBeanList.add(wkInternalInfoBean);

            if (glPoAcctInfoList.size() > i) {
                NPZC104001_poAcctInfoPMsg wkPoAcctInfoPMsg = new NPZC104001_poAcctInfoPMsg();
                ZYPEZDItemValueSetter.setValue(wkPoAcctInfoPMsg.poOrdDtlLineNum, newMaxPoOrdDtlLineNum);
                ZYPEZDItemValueSetter.setValue(wkPoAcctInfoPMsg.poAcctTpCd, glPoAcctInfoList.get(i).poAcctTpCd);
                ZYPEZDItemValueSetter.setValue(wkPoAcctInfoPMsg.coaCmpyCd, glPoAcctInfoList.get(i).coaCmpyCd);
                ZYPEZDItemValueSetter.setValue(wkPoAcctInfoPMsg.coaAfflCd, glPoAcctInfoList.get(i).coaAfflCd);
                ZYPEZDItemValueSetter.setValue(wkPoAcctInfoPMsg.coaBrCd, glPoAcctInfoList.get(i).coaBrCd);
                ZYPEZDItemValueSetter.setValue(wkPoAcctInfoPMsg.coaChCd, glPoAcctInfoList.get(i).coaChCd);
                ZYPEZDItemValueSetter.setValue(wkPoAcctInfoPMsg.coaCcCd, glPoAcctInfoList.get(i).coaCcCd);
                ZYPEZDItemValueSetter.setValue(wkPoAcctInfoPMsg.coaAcctCd, (String) setCompList.get(j).get(NPZC104001Constant.COA_ACCT_CD));
                ZYPEZDItemValueSetter.setValue(wkPoAcctInfoPMsg.coaProdCd, glPoAcctInfoList.get(i).coaProdCd);
                ZYPEZDItemValueSetter.setValue(wkPoAcctInfoPMsg.coaProjCd, glPoAcctInfoList.get(i).coaProjCd);
                ZYPEZDItemValueSetter.setValue(wkPoAcctInfoPMsg.coaExtnCd, glPoAcctInfoList.get(i).coaExtnCd);
                glSetPoAcctInfoList.add(wkPoAcctInfoPMsg);
            }

            childIndex++;
        }

        return newMaxPoOrdDtlLineNum;
    }

    private boolean createPo() {
        // START 2017/10/20 S.Katsuma QC#21206 ADD
        // Keep Po Detail
        String glblCmpyCd = glWkPmsg.glblCmpyCd.getValue();
        String poOrdNum = glWkPmsg.poOrdNum.getValue();
        List<Map<String, Object>> poDtlList = null;
        if (hasValue(poOrdNum)) {
            poDtlList = NPZC104001DBAccess.getPoDtlMap(glblCmpyCd, poOrdNum, glSsmBatchClient);

            // delete PO data        
            NPZC104001DBAccess.deletePo(glblCmpyCd, poOrdNum);
            NPZC104001DBAccess.deletePoDtl(glblCmpyCd, poOrdNum);
            NPZC104001DBAccess.deletePoAcct(glblCmpyCd, poOrdNum);
            NPZC104001DBAccess.deletePoSerNum(glblCmpyCd, poOrdNum, glSsmBatchClient);
        }
        // END 2017/10/20 S.Katsuma QC#21206 ADD

        // set PO_STS_CD
        String poStsCd = "";
        if (NPZC104001Constant.EVENT_SAVE.equals(glWkPmsg.eventId.getValue())) {
            poStsCd = PO_STS.SAVED;
        } else {
            String poApvlStsCd = glWkPmsg.poApvlStsCd.getValue();
            if (PO_APVL_STS.APPROVED.equals(poApvlStsCd) || PO_APVL_STS.PRE_APPROVED.equals(poApvlStsCd)) {
                poStsCd = PO_STS.VALIDATED;
            } else {
                poStsCd = PO_STS.WAITING_FOR_APPROVAL;
            }
        }

        // create PO Header data
        if (!NPZC104001Common.createPoHead(glWkPmsg, glInternalInfoList.get(0), glOnBatchType, poStsCd)) {
            return false;
        }

        // create PO Detail data
        // START 2017/10/20 S.Katsuma QC#21206 ADD
        if (poDtlList != null && poDtlList.size() > 0) {
            if (!NPZC104001Common.createPoDetail(glWkPmsg, glMsgMap, glPoLineInfoList, poStsCd, poDtlList)) {
                return false;
            }
        } else {
            if (!NPZC104001Common.createPoDetail(glWkPmsg, glMsgMap, glPoLineInfoList, poStsCd)) {
                return false;
            }
        }
        // END 2017/10/20 S.Katsuma QC#21206 ADD

        // create PO Serial data
        if (!NPZC104001Common.createPoSerial(glWkPmsg, glPoLineInfoList, glSerNumList, glInternalInfoList)) {
            return false;
        }

        // create Po Acct data
        if (!NPZC104001Common.createPoAcct(glWkPmsg, glPoAcctInfoList)) {
            return false;
        }

        // create PO History data
        if (!NPZC104001Common.createPoHistory(glWkPmsg, glMsgMap, glPoLineInfoList)) {
            return false;
        }

        // update CPO Order
        if (!NPZC104001Common.updateCpoOrder(glWkPmsg, glMsgMap, glPoLineInfoList, glOnBatchType, glInternalInfoList.get(0))) {
            return false;
        }

        // create Transaction data
        if (!NPZC104001Common.createTranData(glWkPmsg, glMsgMap, glPoLineInfoList, glOnBatchType, poStsCd, glInternalInfoList, glSsmBatchClient)) {
            return false;
        }

        // QC#59193 2021/09/14 below insert
        String poApvlStsCd = glWkPmsg.poApvlStsCd.getValue();
        // QC#18644 
        // create RWS
        if (isCrtRws(glWkPmsg.destRtlWhCd.getValue(), glWkPmsg.vndCd.getValue())) {
            // QC#59193 2021/09/14 Start
            if (!PO_APVL_STS.PRE_APPROVED.equals(poApvlStsCd) && !DS_PO_TP.BUYBACK_PO.equals(glWkPmsg.dsPoTpCd.getValue())) {
                // QC#55023 2019/12/10 MOD START
                //if (!NPZC104001Common.createRws(glWkPmsg, glMsgMap, glPoLineInfoList, poStsCd, glWkPmsg.vndCd.getValue(), glWkPmsg.destRtlWhCd.getValue(), glInternalInfoList, glSsmBatchClient, glOnBatchType)) {
                if (!NPZC104001Common.createRws(glWkPmsg, glMsgMap, glPoLineInfoList, poStsCd, glWkPmsg.vndCd.getValue(), glWkPmsg.destRtlWhCd.getValue(), glInternalInfoList, glSsmBatchClient, glOnBatchType, false)) {
                    // QC#55023 2019/12/10 MOD END
                    return false;
                }
            }
            // QC#59193 2021/09/14 End
        }

        // send WF
        String poWfRqstFlg = ZYPConstant.FLG_ON_Y; // Create Mode is
        // mast WF
        boolean sendPoFlg = false; // for UPDATE MODE
        if (!NPZC104001Common.sendWf(glWkPmsg, glMsgMap, glPoLineInfoList, poStsCd, glOnBatchType, poWfRqstFlg)) {
            return false;
        } else {
            sendPoFlg = false;
        }

        // START 2021/05/26 [QC#58872,ADD]
        // Pre-Approved needs to pass sendPo as well as Approved
        // QC#59193 2021/09/14 below comment out
        //String poApvlStsCd = glWkPmsg.poApvlStsCd.getValue();
        if (PO_APVL_STS.PRE_APPROVED.equals(poApvlStsCd)) {
            if (!sendPo()) {
                return false;
            }
        }
        // END 2021/05/26 [QC#58872,ADD]

        // send PO
        if (!NPZC104001Common.sendPo(glWkPmsg, glMsgMap, poStsCd, glOnBatchType, sendPoFlg, glInternalInfoList)) {
            return false;
        }

        return true;
    }

    private boolean updatePo() {

        POTMsg poTmsg = NPZC104001DBAccess.getPo(glWkPmsg.glblCmpyCd.getValue(), glWkPmsg.poOrdNum.getValue());
        if (poTmsg == null) {
            glMsgMap.addXxMsgId(NPZC104001Constant.NPZM0082E);
            return false;
        }
        POTMsg additionalPoTmsg = NPZC104001DBAccess.getPo(glWkPmsg.glblCmpyCd.getValue(), glWkPmsg.poOrdNum.getValue());
        if (additionalPoTmsg == null) {
            glMsgMap.addXxMsgId(NPZC104001Constant.NPZM0123E);
            return false;
        }

        //QC#20724 Mod Start
        String poOrdNum = glWkPmsg.poOrdNum.getValue();
        List<Map<String, Object>> poDtlList = null;
        if (hasValue(poOrdNum)) {
            poDtlList = NPZC104001DBAccess.getPoDtlMap(glWkPmsg.glblCmpyCd.getValue(), poOrdNum, glSsmBatchClient);

            // delete PO data
//            List<Map<String, Object>> serialList = NPZC104001DBAccess.getSerialList(pMsg.glblCmpyCd.getValue(), rwsNum, ssmBatchClient);
//            if (serialList == null || serialList.size() == 0) {
//                return true; // skip under process
//            }
            
//            if(){
            NPZC104001DBAccess.deletePoSerNum(glWkPmsg.glblCmpyCd.getValue(), poOrdNum, glSsmBatchClient);
//            }
        }
        //QC#20724 Mod End
        
        String poStsCd = poTmsg.poStsCd.getValue();
        String poApvlStsCd = poTmsg.poApvlStsCd.getValue();
        boolean sendPoFlg = false;
        String poWfRqstFlg = ZYPConstant.FLG_OFF_N;
        int poyoDtlCnt = 0;
        boolean poCloseNewLineFlg = false;    // 2019/07/30 T.Ogura [QC#51446,ADD]
        // QC#54947 2019/12/10 ADD START
        boolean addLineFlg = false;
        // QC#54947 2019/12/10 ADD END
        // QC#55085 2019/12/20 ADD START
        String aplId = EZDDBCICarrier.getUppgID();
        // QC#55085 2019/12/20 ADD END

        if (!PO_STS.WAITING_FOR_APPROVAL.equals(poTmsg.poStsCd.getValue())) {
            // Update PO
            NPZC104001DBAccess.updatePo(poTmsg, glWkPmsg);

            // set POYO Update API param head
            NPZC109001PMsg poyoPmsg = new NPZC109001PMsg();
            ZYPEZDItemValueSetter.setValue(poyoPmsg.glblCmpyCd, glWkPmsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(poyoPmsg.xxModeCd, NPZC109001Constant.POYO_INSERT_MODE);

            // Po Close Detail List
            List<String> closePoDtlList = new ArrayList<String>();

            // Update PO_DTL
            for (NPZC104001_poLineInfoPMsg poLineInfo : glPoLineInfoList) {

                PO_DTLTMsg poDtlTmsg = NPZC104001DBAccess.getPoDtl(glWkPmsg.glblCmpyCd.getValue(), glWkPmsg.poOrdNum.getValue(), poLineInfo.poOrdDtlLineNum.getValue());

                // QC#55085 2019/12/20 ADD START
                BigDecimal oldPoQty = null;
                // QC#55085 2019/12/20 ADD END

                if (poDtlTmsg == null) {

                    String poLineStsCd = poStsCd;
                    poWfRqstFlg = ZYPConstant.FLG_ON_Y;

                    if (!hasValue(glWkPmsg.poApvlDt) || PO_STS.RECEIVING.equals(poStsCd)) {
                        //QC#26228 Mod Start
                        if (PO_STS.CLOSED.equals(poStsCd)) {
                            poWfRqstFlg = ZYPConstant.FLG_OFF_N;
                            poLineStsCd = PO_STS.VALIDATED;
                        } else if (PO_APVL_STS.APPROVED.equals(poApvlStsCd)) {
                            poWfRqstFlg = ZYPConstant.FLG_OFF_N;
                            // START 06/04/2020 T.Ogura [QC#56912,ADD]
                            if (PO_STS.RECEIVING.equals(poStsCd)) {
                                poLineStsCd = PO_STS.VALIDATED;
                            }
                            // END   06/04/2020 T.Ogura [QC#56912,ADD]
                        } else {
                            poStsCd = PO_STS.VALIDATED;
                            poLineStsCd = poStsCd;
                        }
                        // QC#26228 Mod End
                    // QC#28261
                    } else if(PO_STS.CLOSED.equals(poStsCd) //
                            && NPZC104001Constant.MODE_UPDATE.equals(glWkPmsg.xxModeCd.getValue()) //
                            && NPZC104001Constant.EVENT_SUBMIT.equals(glWkPmsg.eventId.getValue())
                            && PO_HDR_STS.OPEN.equals(poTmsg.poHdrStsCd.getValue())) {

                        poWfRqstFlg = ZYPConstant.FLG_OFF_N;
                        poLineStsCd = PO_STS.VALIDATED;
                    // START 2019/08/09 T.Ogura [QC#52359,ADD]
                    // START 03/13/2020 T.Ogura [QC#55881,MOD]
//                    } else if(PO_APVL_STS.PRE_APPROVED.equals(poApvlStsCd) && PO_STS.CLOSED.equals(poStsCd)) {
                    } else if((PO_APVL_STS.PRE_APPROVED.equals(poApvlStsCd) || PO_APVL_STS.APPROVED.equals(poApvlStsCd)) && PO_STS.CLOSED.equals(poStsCd)) {
                    // END   03/13/2020 T.Ogura [QC#55881,MOD]
                        poWfRqstFlg = ZYPConstant.FLG_OFF_N;
                        poLineStsCd = PO_STS.VALIDATED;
                    // END   2019/08/09 T.Ogura [QC#52359,ADD]
                    }

                    // QC#23930
                    if (PO_STS.CLOSED.equals(poStsCd)) {

                        //QC#26228 Mod Start
                        //poStsCd = PO_STS.VALIDATED;
                        //QC#26228 Mod End

                        // Update PO
                        // START 2019/07/30 T.Ogura [QC#51446,MOD]
//                        ZYPEZDItemValueSetter.setValue(additionalPoTmsg.poStsCd, poStsCd);
                        poCloseNewLineFlg = true;
                        ZYPEZDItemValueSetter.setValue(additionalPoTmsg.poStsCd, PO_STS.VALIDATED);
                        // END   2019/07/30 T.Ogura [QC#51446,MOD]
                        ZYPEZDItemValueSetter.setValue(additionalPoTmsg.poHdrStsCd, PO_HDR_STS.OPEN);
                    }

                    // QC#54947 2019/12/10 ADD START
                    String addLineStsCd = null;
                    if( (!PO_STS.SAVED.equals(poStsCd) || !PO_STS.CANCELLED.equals(poStsCd))
                            && PO_APVL_STS.APPROVED.equals(poApvlStsCd)
                            && NPZC104001Constant.MODE_UPDATE.equals(glWkPmsg.xxModeCd.getValue()) //
                            && NPZC104001Constant.EVENT_SUBMIT.equals(glWkPmsg.eventId.getValue())
                            && PO_HDR_STS.OPEN.equals(poTmsg.poHdrStsCd.getValue())) {

                        poLineStsCd = PO_STS.VALIDATED;
                        if (hasValue(poLineInfo.poMatchTpCd)) {
                            if (PO_MATCH_TP.NO_GOODS_RECEIPT.equals(poLineInfo.poMatchTpCd.getValue())) {
                                addLineStsCd = PO_LINE_STS.OPEN_FOR_INVOICE;
                            } else {
                                addLineStsCd = PO_LINE_STS.OPEN;
                            }
                        }

                    }
                    // QC#54947 2019/12/10 ADD END

                    // QC#54947 2019/12/10 MOD START
                    //NPZC104001DBAccess.insertPoDtl(glWkPmsg, poLineInfo, poLineStsCd, poLineInfo.poQty.getValue(), null);
                    NPZC104001DBAccess.insertPoDtl(glWkPmsg, poLineInfo, poLineStsCd, poLineInfo.poQty.getValue(), addLineStsCd);
                    // QC#54947 2019/12/10 MOD END

                    poDtlTmsg = NPZC104001DBAccess.getPoDtl(glWkPmsg.glblCmpyCd.getValue(), glWkPmsg.poOrdNum.getValue(), poLineInfo.poOrdDtlLineNum.getValue());

                    // QC#22190 Start
                    // update CPO Order
                    List<NPZC104001_poLineInfoPMsg> addLineLst = new ArrayList<NPZC104001_poLineInfoPMsg>();
                    addLineLst.add(poLineInfo);
                    if (!NPZC104001Common.updateCpoOrder(glWkPmsg, glMsgMap, addLineLst, glOnBatchType, glInternalInfoList.get(0))) {
                        return false;
                    }
                    // QC#22190 End

                    // QC#54947 2019/12/10 ADD START
                    addLineFlg = true;
                    // QC#54947 2019/12/10 ADD END

                } else {
                    // QC#55085 2019/12/20 ADD START
                    oldPoQty = poDtlTmsg.poQty.getValue();
                    // QC#55085 2019/12/20 ADD END
                    if (PO_STS.SAVED.equals(poStsCd)) {
                        poWfRqstFlg = ZYPConstant.FLG_ON_Y;
                    }

                    if (!poLineInfo.mdseCd.getValue().equals(poDtlTmsg.mdseCd.getValue())) {
                        poWfRqstFlg = ZYPConstant.FLG_ON_Y;
                    }

                    if (poLineInfo.poQty.getValue().compareTo(poDtlTmsg.poQty.getValue()) != 0) {
                        poWfRqstFlg = ZYPConstant.FLG_ON_Y;
                    }

                    NPZC104001DBAccess.updatePoDtl(poDtlTmsg, glWkPmsg, poLineInfo, poStsCd);

                }

                BigDecimal poQty = poLineInfo.poQty.getValue();
                BigDecimal poCancQty = poDtlTmsg.poCancQty.getValue();
                BigDecimal poInvQty = poLineInfo.poInvQty.getValue();
                BigDecimal poInvBalQty = poQty.subtract(poCancQty).subtract(poInvQty);

                PO_DTLTMsg additionalPoDtlTmsg = NPZC104001DBAccess.getPoDtl(glWkPmsg.glblCmpyCd.getValue(), glWkPmsg.poOrdNum.getValue(), poLineInfo.poOrdDtlLineNum.getValue());
                    if (!hasValue(poLineInfo.poDispQty) && hasValue(additionalPoDtlTmsg.poDispQty)) {
                        poWfRqstFlg = ZYPConstant.FLG_ON_Y;
                    }
                    if (hasValue(poLineInfo.poDispQty) && !hasValue(additionalPoDtlTmsg.poDispQty)) {
                        poWfRqstFlg = ZYPConstant.FLG_ON_Y;
                    }
                    if (hasValue(poLineInfo.poDispQty) && hasValue(additionalPoDtlTmsg.poDispQty) && poLineInfo.poDispQty.getValue().compareTo(additionalPoDtlTmsg.poDispQty.getValue()) != 0) {
                        poWfRqstFlg = ZYPConstant.FLG_ON_Y;
                    }
                    if (poLineInfo.entDealNetUnitPrcAmt.getValue().compareTo(additionalPoDtlTmsg.entDealNetUnitPrcAmt.getValue()) != 0) {
                        poWfRqstFlg = ZYPConstant.FLG_ON_Y;
                    }
                    poInvQty = additionalPoDtlTmsg.poInvQty.getValue();
                    poInvBalQty = poQty.subtract(poCancQty).subtract(poInvQty);
                    NPZC104001DBAccess.updateAdditionalPoDtl(additionalPoDtlTmsg, glWkPmsg, poLineInfo, poInvBalQty);

                // set POYO Update Api param detail
                // QC#55085 2019/12/20 MOD START
                //if (checkPoyoDetail(poDtlTmsg, poLineInfo)) {
                if (checkPoyoDetail(poDtlTmsg, poLineInfo, oldPoQty)) {
                    if (isCrtRws(additionalPoTmsg.destRtlWhCd.getValue(), poTmsg.vndCd.getValue())) {
                        ZYPEZDItemValueSetter.setValue(poyoPmsg.detailList.no(poyoDtlCnt).poOrdNum, glWkPmsg.poOrdNum);
                        ZYPEZDItemValueSetter.setValue(poyoPmsg.detailList.no(poyoDtlCnt).poOrdDtlLineNum, poLineInfo.poOrdDtlLineNum);
                        ZYPEZDItemValueSetter.setValue(poyoPmsg.detailList.no(poyoDtlCnt).xxQty10Num, poLineInfo.poQty);
                        poyoDtlCnt++;
                        poyoPmsg.detailList.setValidCount(poyoDtlCnt);
                    } else if (!isCrtRws(additionalPoTmsg.destRtlWhCd.getValue(), poTmsg.vndCd.getValue()) && !NPZC104001Constant.PO_ENTRY_SUBMIT_APL_ID.equals(aplId)) {
                        ZYPEZDItemValueSetter.setValue(poyoPmsg.detailList.no(poyoDtlCnt).poOrdNum, glWkPmsg.poOrdNum);
                        ZYPEZDItemValueSetter.setValue(poyoPmsg.detailList.no(poyoDtlCnt).poOrdDtlLineNum, poLineInfo.poOrdDtlLineNum);
                        ZYPEZDItemValueSetter.setValue(poyoPmsg.detailList.no(poyoDtlCnt).xxQty10Num, poLineInfo.poQty);
                        poyoDtlCnt++;
                        poyoPmsg.detailList.setValidCount(poyoDtlCnt);
                    }
                // QC#55085 2019/12/20 MOD END
                }

                // set send PO FLG
                if (!hasValue(additionalPoDtlTmsg.poSendTs)) {
                    sendPoFlg = true;
                }

                // set Po Close Detail Line Num
                if ((PO_STS.VALIDATED.equals(poDtlTmsg.poStsCd.getValue())
                        || PO_STS.RECEIVING.equals(poDtlTmsg.poStsCd.getValue())
                        || PO_STS.PO_CONFIRMED.equals(poDtlTmsg.poStsCd.getValue())
                        || PO_STS.PO_ERROR.equals(poDtlTmsg.poStsCd.getValue()))
                        && poDtlTmsg.poBalQty.getValue().compareTo(BigDecimal.ZERO) == 0) {
                    closePoDtlList.add(poLineInfo.poOrdDtlLineNum.getValue());
                }

            } // end dtl loop


            NPZC104001DBAccess.updateAdditionalPo(additionalPoTmsg, glWkPmsg, poWfRqstFlg, glWkPmsg.wfFlg.getValue());

            NPZC104001Common.createPoMsg(glWkPmsg);

            // update PO_SER_NUM
            // create PO Serial data
            //QC#27024 Mod Start
            if (!NPZC104001Common.createPoSerial(glWkPmsg, glPoLineInfoList, glSerNumList, glInternalInfoList)) {
                return false;
            }
//            for (NPZC104001_serNumListPMsg serNumDat : glSerNumList) {
//                PO_SER_NUMTMsg poSerNumTmsg = NPZC104001DBAccess.getPoSerNum(glWkPmsg.glblCmpyCd.getValue(), serNumDat.poSerNumPk.getValue());
//                if (poSerNumTmsg == null) {
//                    glMsgMap.addXxMsgId(NPZC104001Constant.NPAM1449E);
//                    return false;
//                }
//                // get same PO_DTL data and Inner data
//                String serPoOrdDtlLineNum = serNumDat.poOrdDtlLineNum.getValue();
//                for (int i = 0; i < glPoLineInfoList.size(); i++) {
//                    NPZC104001_poLineInfoPMsg wkPoLineInfo = glPoLineInfoList.get(i);
//                    String wkPoOrdDtrlLineNum = wkPoLineInfo.poOrdDtlLineNum.getValue();
//                    if (serPoOrdDtlLineNum.equals(wkPoOrdDtrlLineNum)) {
//                        String invtyLocNm = glInternalInfoList.get(i).getInvtyLocNm();
//                        NPZC104001DBAccess.updatePoSerNum(poSerNumTmsg, glWkPmsg, wkPoLineInfo, serNumDat, invtyLocNm);
//                        break;
//                    }
//                }
//            }
            //QC#27024 Mod End

            // update PO_ACCT
            for (NPZC104001_poAcctInfoPMsg poAcctInfo : glPoAcctInfoList) {
                PO_ACCTTMsg poAcctTmsg = NPZC104001DBAccess.getPoAcct(glWkPmsg.glblCmpyCd.getValue(), glWkPmsg.poOrdNum.getValue(), poAcctInfo.poOrdDtlLineNum.getValue(), poAcctInfo.poAcctTpCd.getValue());
                if (poAcctTmsg == null) {
                    NPZC104001DBAccess.insertPoAcct(glWkPmsg, poAcctInfo);
                } else {
                    // QC#13985
                    NPZC104001DBAccess.updatePoAcct(poAcctTmsg, poAcctInfo);
                }
            }

            for (NPZC104001_poLineInfoPMsg poLineInfo : glPoLineInfoList) {
                // PO History
                // QC#8424
                // String wkEvent = PO_APVL_STS.AWAITING_APPROVAL;
                // if
                // (ZYPConstant.FLG_OFF_N.equals(glWkPmsg.wfFlg.getValue()))
                // {
                // wkEvent = PO_APVL_STS.PRE_APPROVED;
                // }
                // if (0 !=
                // NPXC001001CreatePOHistory.createPOHistory(glWkPmsg.glblCmpyCd.getValue(),
                // NPZC104001Common.getPoApvlStsNm(glWkPmsg.glblCmpyCd.getValue(),
                // wkEvent), glWkPmsg.poOrdNum.getValue(),
                // poLineInfo.poOrdDtlLineNum

                String eventNm = NPZC104001Constant.PO_SUBMITTED;

                if (PO_STS.PO_CONFIRMED.equals(poStsCd)) {
                    eventNm = NPZC104001Constant.PO_CONFIRMED;
                }

                if (0 != NPXC001001CreatePOHistory.createPOHistory(glWkPmsg.glblCmpyCd.getValue(), eventNm, glWkPmsg.poOrdNum.getValue(), poLineInfo.poOrdDtlLineNum.getValue())) {
                    glMsgMap.addXxMsgId(NPZC104001Constant.NPAM1442E);
                }

            }

            // Call POYO Update Api
            if (poyoDtlCnt > 0
                    && (PO_STS.VALIDATED.equals(poTmsg.poStsCd.getValue()) || PO_STS.RECEIVING.equals(poTmsg.poStsCd.getValue()) || PO_STS.PO_CONFIRMED.equals(poTmsg.poStsCd.getValue()) || PO_STS.PO_ERROR.equals(poTmsg.poStsCd.getValue()))) {
                NPZC109001 dPZC109001 = new NPZC109001();
                dPZC109001.execute(poyoPmsg, glOnBatchType);
                if (poyoPmsg.xxMsgIdList.getValidCount() > 0) {
                    glMsgMap.addXxMsgId(poyoPmsg.xxMsgIdList.no(0).xxMsgId.getValue());
                    return false;
                }
            }

            for (String poOrdDtlLineNum : closePoDtlList) {
                // PO Status Update API
                NPZC004001PMsg poStatusPmsg = new NPZC004001PMsg();
                ZYPEZDItemValueSetter.setValue(poStatusPmsg.glblCmpyCd, glWkPmsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(poStatusPmsg.poOrdNum, glWkPmsg.poOrdNum);
                // QC#13212 Modfy Start.
                ZYPEZDItemValueSetter.setValue(poStatusPmsg.poStsCd, PO_STS.CLOSED);
                // QC#13212 Modfy End.
                ZYPEZDItemValueSetter.setValue(poStatusPmsg.poOrdDtlLineNum, poOrdDtlLineNum);
                NPZC004001 poStatusApi = new NPZC004001();
                poStatusApi.execute(poStatusPmsg, glOnBatchType);
                if (poStatusPmsg.xxMsgIdList.getValidCount() > 0) {
                    glMsgMap.addXxMsgId(poStatusPmsg.xxMsgIdList.no(0).xxMsgId.getValue());
                    return false;
                }
                NPZC104001DBAccess.clearSendTs(glWkPmsg.glblCmpyCd.getValue(), glWkPmsg.poOrdNum.getValue(), poOrdDtlLineNum);
                sendPoFlg = true;
            }

        } else {

            // PO Status Waiting for Approval
            if (!PO_APVL_STS.AWAITING_APPROVAL.equals(additionalPoTmsg.poApvlStsCd.getValue())) {
                NPZC104001DBAccess.updatePoApprovalStatus(additionalPoTmsg, PO_APVL_STS.AWAITING_APPROVAL);
            }
        }

        // GET updated PO info
        poTmsg = NPZC104001DBAccess.getPo(glWkPmsg.glblCmpyCd.getValue(), glWkPmsg.poOrdNum.getValue());
        if (poTmsg == null) {
            glMsgMap.addXxMsgId(NPZC104001Constant.NPZM0082E);
            return false;
        }
        additionalPoTmsg = NPZC104001DBAccess.getPo(glWkPmsg.glblCmpyCd.getValue(), glWkPmsg.poOrdNum.getValue());
        if (additionalPoTmsg == null) {
            glMsgMap.addXxMsgId(NPZC104001Constant.NPZM0123E);
            return false;
        }

        // Re-set by updated po status code
        // START 2019/07/30 T.Ogura [QC#51446,MOD]
//        poStsCd = poTmsg.poStsCd.getValue();
        if (poCloseNewLineFlg) {
            poStsCd = PO_STS.CLOSED;
        } else {
            poStsCd = poTmsg.poStsCd.getValue();
        }
        // END   2019/07/30 T.Ogura [QC#51446,MOD]

        // create RWS
        // QC#18644
        if (isCrtRws(additionalPoTmsg.destRtlWhCd.getValue(), poTmsg.vndCd.getValue())) {
            // QC#28261
            if(PO_STS.CLOSED.equals(poStsCd) //
                    && NPZC104001Constant.MODE_UPDATE.equals(glWkPmsg.xxModeCd.getValue()) //
                    && NPZC104001Constant.EVENT_SUBMIT.equals(glWkPmsg.eventId.getValue())
                    && PO_HDR_STS.OPEN.equals(poTmsg.poHdrStsCd.getValue())) {

                poStsCd = PO_STS.VALIDATED;
            }
            
            // QC#55023 2019/12/10 MOD START
	        //if (!NPZC104001Common.createRws(glWkPmsg, glMsgMap, glPoLineInfoList, poStsCd, poTmsg.vndCd.getValue(), additionalPoTmsg.destRtlWhCd.getValue(), glInternalInfoList, glSsmBatchClient, glOnBatchType)) {
            if (!NPZC104001Common.createRws(glWkPmsg, glMsgMap, glPoLineInfoList, poStsCd, poTmsg.vndCd.getValue(), additionalPoTmsg.destRtlWhCd.getValue(), glInternalInfoList, glSsmBatchClient, glOnBatchType, addLineFlg)) {
            // QC#55023 2019/12/10 MOD END
	            return false;
	        }
        }
        // send WF
        if (!NPZC104001Common.sendWf(glWkPmsg, glMsgMap, glPoLineInfoList, poStsCd, glOnBatchType, poWfRqstFlg)) {
            return false;
        } else {
            sendPoFlg = false;
        }

        // send PO
        if (!NPZC104001Common.sendPo(glWkPmsg, glMsgMap, poStsCd, glOnBatchType, sendPoFlg, glInternalInfoList)) {
            return false;
        }

        return true;
    }

    // check POYO Detail
    // QC#55085 2019/12/20 MOD START
    //private boolean checkPoyoDetail(PO_DTLTMsg poDtlTmsg, NPZC104001_poLineInfoPMsg poLineInfo) {
    private boolean checkPoyoDetail(PO_DTLTMsg poDtlTmsg, NPZC104001_poLineInfoPMsg poLineInfo, BigDecimal oldPoQty) {
    // QC#55085 2019/12/20 MOD END
        String mdseCd = poLineInfo.mdseCd.getValue();
        String oldMdseCd = poDtlTmsg.mdseCd.getValue();

        BigDecimal poQty = poLineInfo.poQty.getValue();
        // QC#55085 2019/12/20 DELETE START
        //BigDecimal oldPoQty = poDtlTmsg.poQty.getValue();
        // QC#55085 2019/12/20 DELETE END

        if (!mdseCd.equals(oldMdseCd)) {
            return true;
        }

        if (poQty == null && oldPoQty == null) {
            return false;
        }

        if (poQty != null && oldPoQty == null) {
            return true;
        }

        if (poQty == null && oldPoQty != null) {
            return true;
        }

        if (poQty.compareTo(oldPoQty) == 0) {
            return false;
        }

        return true;
    }

    private boolean cancelPo() {

        POTMsg poTmsg = NPZC104001DBAccess.getPo(glWkPmsg.glblCmpyCd.getValue(), glWkPmsg.poOrdNum.getValue());
        if (poTmsg == null) {
            glMsgMap.addXxMsgId(NPZC104001Constant.NPZM0082E);
            return false;
        }
        POTMsg dsPoTmsg = NPZC104001DBAccess.getPo(glWkPmsg.glblCmpyCd.getValue(), glWkPmsg.poOrdNum.getValue());
        if (dsPoTmsg == null) {
            glMsgMap.addXxMsgId(NPZC104001Constant.NPZM0123E);
            return false;
        }

        // PO Status Update
        for (NPZC104001_poLineInfoPMsg poLineInfo : glPoLineInfoList) {

            PO_DTLTMsg poDtlTmsg = NPZC104001DBAccess.getPoDtl(glWkPmsg.glblCmpyCd.getValue(), glWkPmsg.poOrdNum.getValue(), poLineInfo.poOrdDtlLineNum.getValue());
            if (poDtlTmsg == null) {
                glMsgMap.addXxMsgId(NPZC104001Constant.NPZM0084E);
                return false;
            }
            PO_DTLTMsg dsPoDtlTmsg = NPZC104001DBAccess.getPoDtl(glWkPmsg.glblCmpyCd.getValue(), glWkPmsg.poOrdNum.getValue(), poLineInfo.poOrdDtlLineNum.getValue());
            if (dsPoDtlTmsg == null) {
                glMsgMap.addXxMsgId(NPZC104001Constant.NPAM1447E);
                return false;
            }

            String poStsCd = poDtlTmsg.poStsCd.getValue();
            if (hasValue(poStsCd) && !PO_STS.CLOSED.equals(poStsCd) && !PO_STS.CANCELLED.equals(poStsCd) && BigDecimal.ZERO.compareTo(poDtlTmsg.poBalQty.getValue()) == 0) {
                // PO Status Update
                NPZC004001PMsg poStatusPmsg = new NPZC004001PMsg();
                ZYPEZDItemValueSetter.setValue(poStatusPmsg.glblCmpyCd, glWkPmsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(poStatusPmsg.poOrdNum, glWkPmsg.poOrdNum);
                ZYPEZDItemValueSetter.setValue(poStatusPmsg.poStsCd, PO_STS.CANCELLED);
                ZYPEZDItemValueSetter.setValue(poStatusPmsg.poOrdDtlLineNum, poLineInfo.poOrdDtlLineNum);
                NPZC004001 poStatusApi = new NPZC004001();
                poStatusApi.execute(poStatusPmsg, glOnBatchType);
                if (poStatusPmsg.xxMsgIdList.getValidCount() > 0) {
                    glMsgMap.addXxMsgId(poStatusPmsg.xxMsgIdList.no(0).xxMsgId.getValue());
                    return false;
                }
                NPZC104001DBAccess.clearSendTs(glWkPmsg.glblCmpyCd.getValue(), glWkPmsg.poOrdNum.getValue(), poLineInfo.poOrdDtlLineNum.getValue());
            }
        }

        // send WF
        if (!NPZC104001Common.sendWf(glWkPmsg, glMsgMap, glPoLineInfoList, null, glOnBatchType, null)) {
            return false;
        }

        // send PO
        if (!NPZC104001Common.sendPo(glWkPmsg, glMsgMap, null, glOnBatchType, false, glInternalInfoList)) {
            return false;
        }

        return true;
    }

    private boolean sendPo() {

        POTMsg poTmsg = NPZC104001DBAccess.getPo(glWkPmsg.glblCmpyCd.getValue(), glWkPmsg.poOrdNum.getValue());
        if (poTmsg == null) {
            glMsgMap.addXxMsgId(NPZC104001Constant.NPZM0082E);
            return false;
        }
        // QC#54765 2019/11/21 ADD START
        if (!ZYPCommonFunc.hasValue(glWkPmsg.poQlfyCd) && ZYPCommonFunc.hasValue(poTmsg.poQlfyCd)) {
            ZYPEZDItemValueSetter.setValue(glWkPmsg.poQlfyCd, poTmsg.poQlfyCd);
        }
        // QC#54765 2019/11/21 ADD END
        NPZC104001DBAccess.updatePoCancel(poTmsg, glWkPmsg);

        POTMsg additionalPoTmsg = NPZC104001DBAccess.getPo(glWkPmsg.glblCmpyCd.getValue(), glWkPmsg.poOrdNum.getValue());
        if (additionalPoTmsg == null) {
            glMsgMap.addXxMsgId(NPZC104001Constant.NPZM0123E);
            return false;
        }
        NPZC104001DBAccess.updateAdditionalPoForSend(additionalPoTmsg, glWkPmsg);
        ZYPEZDItemValueSetter.setValue(glWkPmsg.dsPoTpCd, additionalPoTmsg.dsPoTpCd.getValue());
        ZYPEZDItemValueSetter.setValue(glWkPmsg.rqstRcvDt, additionalPoTmsg.rqstRcvDt.getValue());
        // START 2023/02/15 S.Dong [QC#60966, ADD]
        ZYPEZDItemValueSetter.setValue(glWkPmsg.rqstShipDt, additionalPoTmsg.rqstShipDt.getValue());
        // END 2023/02/15 S.Dong [QC#60966, ADD]
        ZYPEZDItemValueSetter.setValue(glWkPmsg.shipFromSoNumListTxt, additionalPoTmsg.shipFromSoNumListTxt.getValue());

        // set DS_PO_TP_CD to SCE_ORD_TP_CD
        DS_PO_TPTMsg dsPoTpTMsg = NPZC104001DBAccess.getDsPoTp(glWkPmsg.glblCmpyCd.getValue(), additionalPoTmsg.dsPoTpCd.getValue());
        if (dsPoTpTMsg == null) {
            glMsgMap.addXxMsgId(NPZC104001Constant.NPZM0116E);
            return false;
        } else {
            // set inner info
            glInternalInfoList.get(0).setSceOrdTpCd(dsPoTpTMsg.sceOrdTpCd.getValue());
        }

        List<NPZC104001_poLineInfoPMsg> tmpPoLineInfoList = new ArrayList<NPZC104001_poLineInfoPMsg>();

        List<Map<String, Object>> poDtlList = NPZC104001DBAccess.getPoDtlMap(glWkPmsg.glblCmpyCd.getValue(), glWkPmsg.poOrdNum.getValue(), glSsmBatchClient);
        for (int i = 0; i < poDtlList.size(); i++) {
            NPZC104001_poLineInfoPMsg poLineInfo = new NPZC104001_poLineInfoPMsg();
            ZYPEZDItemValueSetter.setValue(poLineInfo.poOrdDtlLineNum, (String) poDtlList.get(i).get("PO_ORD_DTL_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.mdseCd, (String) poDtlList.get(i).get("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.mdseNm, (String) poDtlList.get(i).get("MDSE_NM"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.poQty, (BigDecimal) poDtlList.get(i).get("PO_QTY"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.thisMthFobCostAmt, (BigDecimal) poDtlList.get(i).get("THIS_MTH_FOB_COST_AMT"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.custUomCd, (String) poDtlList.get(i).get("CUST_UOM_CD"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.adminPsnCd, (String) poDtlList.get(i).get("ADMIN_PSN_CD"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.ordQty, (BigDecimal) poDtlList.get(i).get("ORD_QTY"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.cpoOrdNum, (String) poDtlList.get(i).get("CPO_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.cpoDtlLineNum, (String) poDtlList.get(i).get("CPO_DTL_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.cpoDtlLineSubNum, (String) poDtlList.get(i).get("CPO_DTL_LINE_SUB_NUM"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.custIssPoNum, (String) poDtlList.get(i).get("CUST_ISS_PO_NUM"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.custIssPoDt, (String) poDtlList.get(i).get("CUST_ISS_PO_DT"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.cpoOrdTpCd, (String) poDtlList.get(i).get("CPO_ORD_TP_CD"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.billToCustCd, (String) poDtlList.get(i).get("BILL_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.sellToCustCd, (String) poDtlList.get(i).get("SELL_TO_CUST_CD"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.shpgPlnNum, (String) poDtlList.get(i).get("SHPG_PLN_NUM"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.poOrdDtlCmntTxt, (String) poDtlList.get(i).get("PO_ORD_DTL_CMNT_TXT"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.dispPoDtlLineNum, (String) poDtlList.get(i).get("DISP_PO_DTL_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.poLineTpCd, (String) poDtlList.get(i).get("PO_LINE_TP_CD"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.poMdseCmpsnTpCd, (String) poDtlList.get(i).get("PO_MDSE_CMPSN_TP_CD"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.setPoOrdDtlLineNum, (String) poDtlList.get(i).get("SET_PO_ORD_DTL_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.mdseDescShortTxt, (String) poDtlList.get(i).get("MDSE_DESC_SHORT_TXT"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.poDispQty, (BigDecimal) poDtlList.get(i).get("PO_DISP_QTY"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.poInvQty, (BigDecimal) poDtlList.get(i).get("PO_INV_QTY"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.poDispUomCd, (String) poDtlList.get(i).get("PO_DISP_UOM_CD"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.entDealNetUnitPrcAmt, (BigDecimal) poDtlList.get(i).get("ENT_DEAL_NET_UNIT_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.entPoDtlDealNetAmt, (BigDecimal) poDtlList.get(i).get("ENT_PO_DTL_DEAL_NET_AMT"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.entFuncNetUnitPrcAmt, (BigDecimal) poDtlList.get(i).get("ENT_FUNC_NET_UNIT_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.entPoDtlFuncNetAmt, (BigDecimal) poDtlList.get(i).get("ENT_PO_DTL_FUNC_NET_AMT"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.exchRate, (BigDecimal) poDtlList.get(i).get("EXCH_RATE"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.destRtlSwhCd, (String) poDtlList.get(i).get("DEST_RTL_SWH_CD"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.srcRtlSwhCd, (String) poDtlList.get(i).get("SRC_RTL_SWH_CD"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.invtyLocCd, (String) poDtlList.get(i).get("INVTY_LOC_CD"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.rqstRcvDt, (String) poDtlList.get(i).get("RQST_RCV_DT"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.rqstRcvTm, (String) poDtlList.get(i).get("RQST_RCV_TM"));
            // START 2023/02/15 S.Dong [QC#60966, ADD]
            ZYPEZDItemValueSetter.setValue(poLineInfo.rqstShipDt, (String) poDtlList.get(i).get("RQST_SHIP_DT"));
            // END 2023/02/15 S.Dong [QC#60966, ADD]
            ZYPEZDItemValueSetter.setValue(poLineInfo.frtCondCd, (String) poDtlList.get(i).get("FRT_COND_CD"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.origMdseCd, (String) poDtlList.get(i).get("ORIG_MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.fromStkStsCd, (String) poDtlList.get(i).get("FROM_STK_STS_CD"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.toStkStsCd, (String) poDtlList.get(i).get("TO_STK_STS_CD"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.poMatchTpCd, (String) poDtlList.get(i).get("PO_MATCH_TP_CD"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.prchReqNum, (String) poDtlList.get(i).get("PRCH_REQ_NUM"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.prchReqLineNum, (String) poDtlList.get(i).get("PRCH_REQ_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.prchReqLineSubNum, (BigDecimal) poDtlList.get(i).get("PRCH_REQ_LINE_SUB_NUM"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.trxRefNum, (String) poDtlList.get(i).get("TRX_REF_NUM"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.trxRefLineNum, (String) poDtlList.get(i).get("TRX_REF_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.trxRefLineSubNum, (String) poDtlList.get(i).get("TRX_REF_LINE_SUB_NUM"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.aslDtlPk, (BigDecimal) poDtlList.get(i).get("ASL_DTL_PK"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.aslMdseCd, (String) poDtlList.get(i).get("ASL_MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.aslUnitPrcAmt, (BigDecimal) poDtlList.get(i).get("ASL_UNIT_PRC_AMT"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.shipFromSoNumListTxt, (String) poDtlList.get(i).get("SHIP_FROM_SO_NUM_LIST_TXT"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.vndInvtyLocCd, (String) poDtlList.get(i).get("VND_INVTY_LOC_CD"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.vndIssPoOrdNum, (String) poDtlList.get(i).get("VND_ISS_PO_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.proNum, (String) poDtlList.get(i).get("PRO_NUM"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.vndPoAckStsCd, (String) poDtlList.get(i).get("VND_PO_ACK_STS_CD"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.origPoOrdNum, (String) poDtlList.get(i).get("ORIG_PO_ORD_NUM"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.origPoOrdDtlLineNum, (String) poDtlList.get(i).get("ORIG_PO_ORD_DTL_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.origDispPoDtlLineNum, (String) poDtlList.get(i).get("ORIG_DISP_PO_DTL_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.svcConfigMstrPk, (BigDecimal) poDtlList.get(i).get("SVC_CONFIG_MSTR_PK"));
            ZYPEZDItemValueSetter.setValue(poLineInfo.poSendTs, (String) poDtlList.get(i).get("PO_SEND_TS"));
            //QC#18420 Add Start
            ZYPEZDItemValueSetter.setValue(poLineInfo.poDtlDiscPct, (BigDecimal) poDtlList.get(i).get("PO_DTL_DISC_PCT"));
            //QC#18420 Add End
            // QC#53392 2019/10/05 Add Start
            ZYPEZDItemValueSetter.setValue(poLineInfo.poDtlDiscPrcAmt, (BigDecimal) poDtlList.get(i).get("PO_DTL_DISC_PRC_AMT"));
            // QC#53392 2019/10/05 Add End
            // QC#54667 2019/11/18 Mod Start
            ZYPEZDItemValueSetter.setValue(poLineInfo.poLineStsCd, (String) poDtlList.get(i).get("PO_LINE_STS_CD"));
            // QC#54667 2019/11/18 Mod End

            // merge Update Data
            for (NPZC104001_poLineInfoPMsg pMsgLineInfo : glPoLineInfoList) {
                if (poLineInfo.poOrdDtlLineNum.getValue().equals(pMsgLineInfo.poOrdDtlLineNum.getValue())) {
                    if (ZYPCommonFunc.hasValue(pMsgLineInfo.vndInvtyLocCd)) {
                        ZYPEZDItemValueSetter.setValue(poLineInfo.vndInvtyLocCd, pMsgLineInfo.vndInvtyLocCd);
                    }

                    if (ZYPCommonFunc.hasValue(pMsgLineInfo.vndIssPoOrdNum)) {
                        ZYPEZDItemValueSetter.setValue(poLineInfo.vndIssPoOrdNum, pMsgLineInfo.vndIssPoOrdNum);
                    }

                    if (ZYPCommonFunc.hasValue(pMsgLineInfo.proNum)) {
                        ZYPEZDItemValueSetter.setValue(poLineInfo.proNum, pMsgLineInfo.proNum);
                    }

                    if (ZYPCommonFunc.hasValue(pMsgLineInfo.vndPoAckStsCd)) {
                        ZYPEZDItemValueSetter.setValue(poLineInfo.vndPoAckStsCd, pMsgLineInfo.vndPoAckStsCd);
                    }

                    if (ZYPCommonFunc.hasValue(pMsgLineInfo.poSendTs)) {
                        ZYPEZDItemValueSetter.setValue(poLineInfo.poSendTs, pMsgLineInfo.poSendTs);
                    }
                }
            }

            // QC#54667 2019/11/18 Mod Start
            if (!PO_LINE_STS.CANCELLED.equals(poLineInfo.poLineStsCd.getValue())) {
                tmpPoLineInfoList.add(poLineInfo);
            }
//            tmpPoLineInfoList.add(poLineInfo);
            // QC#54667 2019/11/18 Mod End
        }

        // Substitution
        glPoLineInfoList = tmpPoLineInfoList;

        // 10/11/2016 QC#6159 Add Start.
        if (DS_PO_TP.BUYBACK_PO.equals(glWkPmsg.dsPoTpCd.getValue())) {
            Collections.sort(glPoLineInfoList, new NPZC104001Common.CompByBigDecimal(NPZC104001Constant.SORT_KEY_SVC_CONFIG_MSTR_PK));
        } else {
            Collections.sort(glPoLineInfoList, new NPZC104001Common.Comp(NPZC104001Constant.SORT_KEY_PO_ORD_DTL_LINE_NUM));
        }
        // 10/11/2016 QC#6159 Add End.

        if (poDtlList != null && !poDtlList.isEmpty()) {

            if (ZYPCommonFunc.hasValue((String) poDtlList.get(0).get("CARR_CD"))) {

                ZYPEZDItemValueSetter.setValue(glWkPmsg.carrCd, (String) poDtlList.get(0).get("CARR_CD"));
            }
        }

        List<PO_DTLTMsg> poDtlTmsgList = new ArrayList<PO_DTLTMsg>();
        List<PO_DTLTMsg> dsPoDtlTmsgList = new ArrayList<PO_DTLTMsg>();

        // PO Detail Loop
        for (NPZC104001_poLineInfoPMsg poLineInfo : glPoLineInfoList) {
            PO_DTLTMsg poDtlTmsg = NPZC104001DBAccess.getPoDtl(glWkPmsg.glblCmpyCd.getValue(), glWkPmsg.poOrdNum.getValue(), poLineInfo.poOrdDtlLineNum.getValue());
            if (poDtlTmsg == null) {
                glMsgMap.addXxMsgId(NPZC104001Constant.NPZM0084E);
                return false;
            }
            poDtlTmsgList.add(poDtlTmsg);

            PO_DTLTMsg dsPoDtlTmsg = NPZC104001DBAccess.getPoDtl(glWkPmsg.glblCmpyCd.getValue(), glWkPmsg.poOrdNum.getValue(), poLineInfo.poOrdDtlLineNum.getValue());
            if (dsPoDtlTmsg == null) {
                glMsgMap.addXxMsgId(NPZC104001Constant.NPAM1447E);
                return false;
            }

            NPZC104001DBAccess.updateDsPoDtlForSend(dsPoDtlTmsg, glWkPmsg, poLineInfo);
            dsPoDtlTmsgList.add(dsPoDtlTmsg);

            // create PO History in This case Detail data exist

            // QC#8424

            // search po sts.

            String searchedPoStatus = NPZC104001DBAccess.searchPoStatus(glWkPmsg.glblCmpyCd.getValue(), glWkPmsg.poOrdNum.getValue(), glSsmBatchClient);

            if (!PO_APVL_STS.APPROVED.equals(searchedPoStatus) && !PO_APVL_STS.REJECTED.equals(searchedPoStatus)) {

                String eventId = NPZC104001Constant.PO_SUBMITTED;

                if (ZYPCommonFunc.hasValue(glWkPmsg.poPrintFlg) && ZYPConstant.FLG_ON_Y.equals(glWkPmsg.poPrintFlg.getValue())) {
                    eventId = NPZC104001Constant.PO_PRINTED;
                }

                if (0 != NPXC001001CreatePOHistory.createPOHistory(glWkPmsg.glblCmpyCd.getValue(), eventId, glWkPmsg.poOrdNum.getValue(), poLineInfo.poOrdDtlLineNum.getValue())) {
                    glMsgMap.addXxMsgId(NPZC104001Constant.NPAM1442E);
                }
            }

        }

        // Set PO Info Note (Except Internal Message);
        glWkPmsg.poInfo.clear();
        glWkPmsg.poInfo.setValidCount(0);
        int poInfoCount = 0;
        String[] poMsgTpArray = new String[] {PO_MSG_TP.RECEIVER_NOTE, PO_MSG_TP.SHIPPER_NOTE, PO_MSG_TP.SPECIAL_INSTRUCTIONS };
        for (int i = 0; i < poMsgTpArray.length; i++) {
            List<PO_MSGTMsg> poMsgList = NPXC001001PoMsg.getPoMsg(glWkPmsg.glblCmpyCd.getValue(), poMsgTpArray[i], glWkPmsg.poOrdNum.getValue(), null);
            if (poMsgList != null && poMsgList.size() > 0) {
                String msg = NPXC001001PoMsg.concatenatePoMsg(poMsgList);
                ZYPEZDItemValueSetter.setValue(glWkPmsg.poInfo.no(poInfoCount).poMsgTpCd, poMsgTpArray[i]);
                ZYPEZDItemValueSetter.setValue(glWkPmsg.poInfo.no(poInfoCount).xxDsMultMsgDplyTxt, msg);
                poInfoCount++;
            }
        }
        glWkPmsg.poInfo.setValidCount(poInfoCount);

        // create PO History in This case Detail data not exist
        if (glWkPmsg.poLineInfo.getValidCount() == 0) {
            // if (0 !=
            // NPXC001001CreatePOHistory.createPOHistory(glWkPmsg.glblCmpyCd.getValue(),
            // NPZC104001Constant.SEND_PO,
            // glWkPmsg.poOrdNum.getValue(), null)) {
            // glMsgMap.addXxMsgId(NPZC104001Constant.NPAM1442E);
            // }
            // QC#8424
            String eventId = NPZC104001Constant.PO_SUBMITTED;

            if (ZYPCommonFunc.hasValue(glWkPmsg.poPrintFlg) && ZYPConstant.FLG_ON_Y.equals(glWkPmsg.poPrintFlg.getValue())) {
                eventId = NPZC104001Constant.PO_PRINTED;
            }

            if (0 != NPXC001001CreatePOHistory.createPOHistory(glWkPmsg.glblCmpyCd.getValue(), eventId, glWkPmsg.poOrdNum.getValue(), null)) {
                glMsgMap.addXxMsgId(NPZC104001Constant.NPAM1442E);
            }
        }

        if (DS_PO_TP.BUYBACK_PO.equals(glWkPmsg.dsPoTpCd.getValue()) // 
                && ZYPCommonFunc.hasValue(glWkPmsg.poPrintFlg) //
                && ZYPConstant.FLG_ON_Y.equals(glWkPmsg.poPrintFlg.getValue())) {
            return true;
        }
        
        // create Trunsaction proc
        if (PO_APVL_STS.REJECTED.equals(additionalPoTmsg.poApvlStsCd.getValue())) {
            if (PO_STS.SAVED.equals(poTmsg.poStsCd.getValue())) {
                if (DS_PO_TP.BUYBACK_PO.equals(glWkPmsg.dsPoTpCd.getValue())) {
                    // get Machine Master
                    List<Map<String, BigDecimal>> svcMachMstrPkList = NPZC104001DBAccess.getSvcMachMstrPk(glWkPmsg.glblCmpyCd.getValue(), glWkPmsg.poOrdNum.getValue(), glSsmBatchClient);
                    // Allocation Cancel
                    if (svcMachMstrPkList != null) {
                        for (Map<String, BigDecimal> svcMachMstrPkMap : svcMachMstrPkList) {
                            BigDecimal svcMachMstrPk = (BigDecimal) svcMachMstrPkMap.get(NPZC104001Constant.SVC_MACH_MSTR_PK);
                            NSZC001001PMsg mmPmsg = new NSZC001001PMsg();
                            ZYPEZDItemValueSetter.setValue(mmPmsg.glblCmpyCd, glWkPmsg.glblCmpyCd);
                            ZYPEZDItemValueSetter.setValue(mmPmsg.slsDt, glWkPmsg.procDt);
                            ZYPEZDItemValueSetter.setValue(mmPmsg.xxModeCd, ProcessMode.ALLOCATION_OFF.code);
                            ZYPEZDItemValueSetter.setValue(mmPmsg.svcMachMstrPk, svcMachMstrPk);
                            NSZC001001 mmApi = new NSZC001001();
                            mmApi.execute(mmPmsg, glOnBatchType);
                            if (mmPmsg.xxMsgIdList.getValidCount() > 0) {
                                glMsgMap.addXxMsgId(mmPmsg.xxMsgIdList.no(0).xxMsgId.getValue());
                                return false;
                            }
                        }
                    }
                }
            }
        }

        // START 2021/05/26 [QC#58872,MOD]
//        if (PO_APVL_STS.APPROVED.equals(additionalPoTmsg.poApvlStsCd.getValue())) {
        if (PO_APVL_STS.APPROVED.equals(additionalPoTmsg.poApvlStsCd.getValue()) || PO_APVL_STS.PRE_APPROVED.equals(additionalPoTmsg.poApvlStsCd.getValue())) {
        // END 2021/05/26 [QC#58872,MOD]
            if (DS_PO_TP.SUBCONTRACT_PO.equals(glWkPmsg.dsPoTpCd.getValue())) {
                for (PO_DTLTMsg dsPoDtlTmsg : dsPoDtlTmsgList) {
                    // get Purchase Requisition
                    PRCH_REQTMsg prchReqTMsg = NPZC104001DBAccess.getPrchReq(glWkPmsg.glblCmpyCd.getValue(), dsPoDtlTmsg.prchReqNum.getValue());
                    if (prchReqTMsg != null && hasValue(prchReqTMsg.prchReqApvlStsCd) && !PRCH_REQ_APVL_STS.APPROVED.equals(prchReqTMsg.prchReqApvlStsCd.getValue())
                            && !PRCH_REQ_APVL_STS.PRE_APPROVED.equals(prchReqTMsg.prchReqApvlStsCd.getValue())) {
                        // PR Update API
                        NPZC103001PMsg prUpdPmsg = new NPZC103001PMsg();
                        ZYPEZDItemValueSetter.setValue(prUpdPmsg.xxModeCd, NPZC103001Constant.MODE_UPDATE);
                        ZYPEZDItemValueSetter.setValue(prUpdPmsg.eventId, NPZC103001Constant.EVENT_APPROVAL);
                        ZYPEZDItemValueSetter.setValue(prUpdPmsg.glblCmpyCd, glWkPmsg.glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(prUpdPmsg.prchReqNum, dsPoDtlTmsg.prchReqNum);
                        ZYPEZDItemValueSetter.setValue(prUpdPmsg.prchReqApvlStsCd, PRCH_REQ_APVL_STS.APPROVED);
                        ZYPEZDItemValueSetter.setValue(prUpdPmsg.prchReqApvlDt, poTmsg.poApvlDt);
                        ZYPEZDItemValueSetter.setValue(prUpdPmsg.procDt, glWkPmsg.procDt);
                        ZYPEZDItemValueSetter.setValue(prUpdPmsg.prchReqApvlByPsnCd, poTmsg.poApvlPsnCd);
                        NPZC103001 prUpdApi = new NPZC103001();
                        prUpdApi.execute(prUpdPmsg, glOnBatchType);
                        if (prUpdPmsg.xxMsgIdList.getValidCount() > 0) {
                            glMsgMap.addXxMsgId(prUpdPmsg.xxMsgIdList.no(0).xxMsgId.getValue());
                            return false;
                        }
                        // create RWS
                        // QC#18644
                        // START 2019/02/15 M.Naito [QC#30354,MOD]
//                        if (isCrtRws(additionalPoTmsg.destRtlWhCd.getValue(), poTmsg.vndCd.getValue())) {
                        // QC#55023 2019/12/10 MOD START
                        //if (!NPZC104001Common.createRws(glWkPmsg, glMsgMap, glPoLineInfoList, poTmsg.poStsCd.getValue(), poTmsg.vndCd.getValue(), additionalPoTmsg.destRtlWhCd.getValue(), glInternalInfoList, glSsmBatchClient, glOnBatchType)) {
                        if (!NPZC104001Common.createRws(glWkPmsg, glMsgMap, glPoLineInfoList, poTmsg.poStsCd.getValue(), poTmsg.vndCd.getValue(), additionalPoTmsg.destRtlWhCd.getValue(), glInternalInfoList, glSsmBatchClient, glOnBatchType, false)) {
                        // QC#55023 2019/12/10 MOD END
                            return false;
                        }
//                        }
                        // END 2019/02/15 M.Naito [QC#30354,MOD]
                    } else {
                        continue;
                    }
                } // end PO Detail Loop
                // PO Type BuyBack
            } else if (DS_PO_TP.BUYBACK_PO.equals(glWkPmsg.dsPoTpCd.getValue())) {

                if (!createTrxBuyBack(poTmsg, additionalPoTmsg, poDtlTmsgList, dsPoDtlTmsgList)) {
                    return false;
                }

                // PO Type Else
            } else {
                // create RWS
                // QC#18644
                if (isCrtRws(additionalPoTmsg.destRtlWhCd.getValue(), poTmsg.vndCd.getValue())) {
                    // QC#55023 2019/12/10 MOD START
	                //if (!NPZC104001Common.createRws(glWkPmsg, glMsgMap, glPoLineInfoList, poTmsg.poStsCd.getValue(), poTmsg.vndCd.getValue(), additionalPoTmsg.destRtlWhCd.getValue(), glInternalInfoList, glSsmBatchClient, glOnBatchType)) {
                    if (!NPZC104001Common.createRws(glWkPmsg, glMsgMap, glPoLineInfoList, poTmsg.poStsCd.getValue(), poTmsg.vndCd.getValue(), additionalPoTmsg.destRtlWhCd.getValue(), glInternalInfoList, glSsmBatchClient, glOnBatchType, false)) {  
                    // QC#55023 2019/12/10 MOD END
                        return false;
	                }
                }
            }
        }

        return true;
    }

    private boolean createTrxBuyBack(POTMsg poTmsg, POTMsg dsPoTmsg, List<PO_DTLTMsg> poDtlTmsgList, List<PO_DTLTMsg> dsPoDtlTmsgList) {
        // QC#30039
//        if (!PO_STS.VALIDATED.equals(poTmsg.poStsCd.getValue())) {
//            return false;
//        }

        String dsPoTpCd = dsPoTmsg.dsPoTpCd.getValue();
        DS_PO_TPTMsg dsPoTpTmsg = NPZC104001DBAccess.getDsPoTp(glWkPmsg.glblCmpyCd.getValue(), dsPoTpCd);
        if (dsPoTmsg == null) {
            glMsgMap.addXxMsgId(NPZC104001Constant.NPAM1451E);
            return false;
        }

        String sceOrdTpCd = dsPoTpTmsg.sceOrdTpCd.getValue();
        Map<String, Object> sceOrdTp = NPZC104001DBAccess.getSceOrdTp(glWkPmsg.glblCmpyCd.getValue(), sceOrdTpCd, glSsmBatchClient);

        if (sceOrdTp == null) {
            glMsgMap.addXxMsgId(NPZC104001Constant.NPAM1452E);
            return false;
        }

        // SO API Param List
        List<NLZC205001PMsg> soPmsgList = new ArrayList<NLZC205001PMsg>();

        List<NPZC104001_poLineInfoPMsg> buyBackPoLineInfoList = new ArrayList<NPZC104001_poLineInfoPMsg>();

        // PO_DTL Loop
        BigDecimal configId = BigDecimal.ZERO;
        BigDecimal indexConfigId = BigDecimal.ZERO;
        // QC#30039
        boolean reSubmitBB = false;
        for (int i = 0; i < poDtlTmsgList.size(); i++) {
            PO_DTLTMsg wkPoDtl = poDtlTmsgList.get(i);
            PO_DTLTMsg wkDsPoDtl = dsPoDtlTmsgList.get(i);
            // QC#50139
            MDSETMsg mdseTMsg = NPZC104001DBAccess.findMdse(glWkPmsg.glblCmpyCd.getValue(), wkPoDtl.mdseCd.getValue());
            if (mdseTMsg == null) {
                continue;
            } else if (ZYPConstant.FLG_OFF_N.equals(mdseTMsg.invtyCtrlFlg.getValue())) {
                continue;
            }
            // QC#30039
            SHPG_PLNTMsg spTMsg = NPZC104001DBAccess.getShpgPlnTMsg(glWkPmsg.glblCmpyCd.getValue(), poTmsg.poOrdNum.getValue(), wkPoDtl.poOrdDtlLineNum.getValue(), NPZC104001Constant.TRX_LINE_SUB_NUM, dsPoTpTmsg.trxSrcTpCd.getValue());
            if (spTMsg != null) {
                // Re-Submit Order
                reSubmitBB = true;
                continue;
            } else {
                reSubmitBB = false;
            }

            if (ZYPCommonFunc.hasValue(wkDsPoDtl.svcConfigMstrPk)) {
                indexConfigId = wkDsPoDtl.svcConfigMstrPk.getValue();
            } else {
                indexConfigId = BigDecimal.ZERO;
            }

            if (configId.compareTo(indexConfigId) != 0) {

                if (BigDecimal.ZERO.compareTo(configId) != 0) {
                    // SO API Param Exec
                    NLZC205001 soApi = new NLZC205001();
                    soApi.execute(soPmsgList, glOnBatchType);
                    if (soPmsgList.get(0).xxMsgIdList.getValidCount() > 0) {
                        glMsgMap.addXxMsgId(soPmsgList.get(0).xxMsgIdList.no(0).xxMsgId.getValue());
                        if (soPmsgList.get(0).xxMsgIdList.no(0).xxMsgId.getValue().endsWith("E")) {
                            return false;
                        }
                    }

                    // PMsgList Clear.
                    soPmsgList.clear();

                    // create RWS
                    // QC#18644
                    if (isCrtRws(dsPoTmsg.destRtlWhCd.getValue(), poTmsg.vndCd.getValue())) {
                        // QC#55023 2019/12/10 MOD START
	                    //if (!NPZC104001Common.createRws(glWkPmsg, glMsgMap, buyBackPoLineInfoList, poTmsg.poStsCd.getValue(), poTmsg.vndCd.getValue(), dsPoTmsg.destRtlWhCd.getValue(), glInternalInfoList, glSsmBatchClient, glOnBatchType)) {
                        if (!NPZC104001Common.createRws(glWkPmsg, glMsgMap, buyBackPoLineInfoList, poTmsg.poStsCd.getValue(), poTmsg.vndCd.getValue(), dsPoTmsg.destRtlWhCd.getValue(), glInternalInfoList, glSsmBatchClient, glOnBatchType, false)) {
                        // QC#55023 2019/12/10 MOD END
                            return false;
	                    }
                    }
                    // buyBackPoLineInfoList Clear.
                    buyBackPoLineInfoList.clear();

                }

                // New ConfigID Replace.
                configId = indexConfigId;

            }

            // QC#22610 Update. Unnecessary shpgSvcLvl Error handling.
            String shpgSvcLvlCd = wkPoDtl.shpgSvcLvlCd.getValue();
            SHPG_SVC_LVLTMsg shpgSvcLvl = NPZC104001DBAccess.getShpgSvcLvl(glWkPmsg.glblCmpyCd.getValue(), shpgSvcLvlCd);

            String frtChrgMethCd = FRT_CHRG_METH.N_OR_A;
            String frtChrgToCd = FRT_CHRG_TO.CANON;

            if (shpgSvcLvl != null && SHPG_SVC_TP.PICK_UP.equals(shpgSvcLvl.shpgSvcTpCd.getValue())) {
                frtChrgMethCd = FRT_CHRG_METH.PICK_UP_NO_CHARGE;
                frtChrgToCd = FRT_CHRG_TO.CUSTOMER;
            }

            Map<String, Object> srcInvtyLocMap = NPZC104001DBAccess.getDsInvtyLocV(glWkPmsg.glblCmpyCd.getValue(), wkDsPoDtl.srcRtlWhCd.getValue(), wkDsPoDtl.srcRtlSwhCd.getValue(), glSsmBatchClient);
            if (srcInvtyLocMap == null) {
                glMsgMap.addXxMsgId(NPZC104001Constant.NPAM1432E);
                return false;
            }

            // Allocation for non CPO API
            NWZC107001PMsg alcPmsg = new NWZC107001PMsg();
            ZYPEZDItemValueSetter.setValue(alcPmsg.glblCmpyCd, glWkPmsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(alcPmsg.xxSysSrcCd, SYS_SRC.S21_LOGISTICS);
            ZYPEZDItemValueSetter.setValue(alcPmsg.xxRqstTpCd, NWZC107001.REQ_TP_NEW);
            ZYPEZDItemValueSetter.setValue(alcPmsg.xxPrtlAcptFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(alcPmsg.trxSrcTpCd, dsPoTpTmsg.trxSrcTpCd);
            ZYPEZDItemValueSetter.setValue(alcPmsg.trxCd, (String) sceOrdTp.get(NPZC104001Constant.TRX_CD));
            ZYPEZDItemValueSetter.setValue(alcPmsg.trxRsnCd, (String) sceOrdTp.get(NPZC104001Constant.TRX_RSN_CD));
            ZYPEZDItemValueSetter.setValue(alcPmsg.trxHdrNum, poTmsg.poOrdNum);
            ZYPEZDItemValueSetter.setValue(alcPmsg.trxLineNum, wkPoDtl.poOrdDtlLineNum);
            ZYPEZDItemValueSetter.setValue(alcPmsg.trxLineSubNum, NPZC104001Constant.TRX_LINE_SUB_NUM);
            ZYPEZDItemValueSetter.setValue(alcPmsg.mdseCd, wkPoDtl.mdseCd);
            ZYPEZDItemValueSetter.setValue(alcPmsg.invtyLocCd, (String) srcInvtyLocMap.get("INVTY_LOC_CD"));
            ZYPEZDItemValueSetter.setValue(alcPmsg.locStsCd, LOC_STS.DC_STOCK);
            ZYPEZDItemValueSetter.setValue(alcPmsg.stkStsCd, wkDsPoDtl.fromStkStsCd);
            ZYPEZDItemValueSetter.setValue(alcPmsg.xxRqstQty, wkPoDtl.poQty);
            ZYPEZDItemValueSetter.setValue(alcPmsg.frtChrgMethCd, frtChrgMethCd);
            ZYPEZDItemValueSetter.setValue(alcPmsg.frtChrgToCd, frtChrgToCd);
            ZYPEZDItemValueSetter.setValue(alcPmsg.carrCd, wkPoDtl.carrCd);
            ZYPEZDItemValueSetter.setValue(alcPmsg.carrAcctNum, dsPoTmsg.carrAcctNum);
            ZYPEZDItemValueSetter.setValue(alcPmsg.shpgSvcLvlCd, wkPoDtl.shpgSvcLvlCd);
            ZYPEZDItemValueSetter.setValue(alcPmsg.xxOrdTs, poTmsg.poSubmtTs);
            ZYPEZDItemValueSetter.setValue(alcPmsg.rddDt, wkPoDtl.etaDt);
            ZYPEZDItemValueSetter.setValue(alcPmsg.xxUnitPrc, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(alcPmsg.xxSlsAmt, BigDecimal.ZERO);
            ZYPEZDItemValueSetter.setValue(alcPmsg.billToCustCd, wkDsPoDtl.invtyLocCd);
            ZYPEZDItemValueSetter.setValue(alcPmsg.sellToCustCd, wkDsPoDtl.invtyLocCd);
            ZYPEZDItemValueSetter.setValue(alcPmsg.shipToCustCd, wkDsPoDtl.invtyLocCd);
            ZYPEZDItemValueSetter.setValue(alcPmsg.dropShipFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(alcPmsg.xxShipToName, wkPoDtl.shipToLocNm);
            ZYPEZDItemValueSetter.setValue(alcPmsg.xxShipToNameAddl, wkPoDtl.shipToAddlLocNm);
            ZYPEZDItemValueSetter.setValue(alcPmsg.shipToFirstLineAddr, wkPoDtl.shipToFirstLineAddr);
            ZYPEZDItemValueSetter.setValue(alcPmsg.shipToScdLineAddr, wkPoDtl.shipToScdLineAddr);
            ZYPEZDItemValueSetter.setValue(alcPmsg.shipToThirdLineAddr, wkPoDtl.shipToThirdLineAddr);
            ZYPEZDItemValueSetter.setValue(alcPmsg.shipToFrthLineAddr, wkPoDtl.shipToFrthLineAddr);
            ZYPEZDItemValueSetter.setValue(alcPmsg.shipToCtyAddr, wkPoDtl.shipToCtyAddr);
            ZYPEZDItemValueSetter.setValue(alcPmsg.shipToCntyNm, wkPoDtl.shipToCntyNm);
            ZYPEZDItemValueSetter.setValue(alcPmsg.xxShipToProvName, wkPoDtl.shipToProvNm);
            ZYPEZDItemValueSetter.setValue(alcPmsg.shipToStCd, wkPoDtl.shipToStCd);
            ZYPEZDItemValueSetter.setValue(alcPmsg.shipToPostCd, wkPoDtl.shipToPostCd);
            ZYPEZDItemValueSetter.setValue(alcPmsg.shipToCtryCd, wkPoDtl.shipToCtryCd);
            ZYPEZDItemValueSetter.setValue(alcPmsg.shipToFirstRefCmntTxt, wkPoDtl.shipToFirstRefCmntTxt);
            ZYPEZDItemValueSetter.setValue(alcPmsg.shipToScdRefCmntTxt, wkPoDtl.shipToScdRefCmntTxt);
            ZYPEZDItemValueSetter.setValue(alcPmsg.slsDt, glWkPmsg.procDt);
            NWZC107001 alcApi = new NWZC107001();
            alcApi.execute(alcPmsg, glOnBatchType);
            if (alcPmsg.xxMsgIdList.getValidCount() > 0) {
                glMsgMap.addXxMsgId(alcPmsg.xxMsgIdList.no(0).xxMsgId.getValue());
                ZYPEZDItemValueSetter.setValue(glWkPmsg.poLineInfo.no(i).xxMsgId, alcPmsg.xxMsgIdList.no(0).xxMsgId.getValue());
                return false;
            }

            // Shipping Plan Update API
            List<NWZC003001PMsg> shpPmsgList = new ArrayList<NWZC003001PMsg>();
            NWZC003001PMsg shpPmsg = new NWZC003001PMsg();
            ZYPEZDItemValueSetter.setValue(shpPmsg.glblCmpyCd, glWkPmsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(shpPmsg.shpgModeCd, NWZC003001.MODE_SHIPPINGREQUEST);
            ZYPEZDItemValueSetter.setValue(shpPmsg.trxSrcTpCd, dsPoTpTmsg.trxSrcTpCd);
            ZYPEZDItemValueSetter.setValue(shpPmsg.trxHdrNum, poTmsg.poOrdNum);
            ZYPEZDItemValueSetter.setValue(shpPmsg.trxLineNum, wkPoDtl.poOrdDtlLineNum);
            ZYPEZDItemValueSetter.setValue(shpPmsg.trxLineSubNum, NPZC104001Constant.TRX_LINE_SUB_NUM);
            ZYPEZDItemValueSetter.setValue(shpPmsg.avalSoQty, wkPoDtl.poQty);

            List<String> shpgCmntMsgList = NPZC104001Common.getMsgList(glWkPmsg, PO_MSG_TP.SHIPPER_NOTE);

            if (shpgCmntMsgList != null) {
                if (shpgCmntMsgList.size() > 0) {
                    ZYPEZDItemValueSetter.setValue(shpPmsg.shipCmntFirstLineTxt, shpgCmntMsgList.get(0));
                }
                if (shpgCmntMsgList.size() > 1) {
                    ZYPEZDItemValueSetter.setValue(shpPmsg.shipCmntScdLineTxt, shpgCmntMsgList.get(1));
                }
                if (shpgCmntMsgList.size() > 2) {
                    ZYPEZDItemValueSetter.setValue(shpPmsg.shipCmntThirdLineTxt, shpgCmntMsgList.get(2));
                }
                if (shpgCmntMsgList.size() > 3) {
                    ZYPEZDItemValueSetter.setValue(shpPmsg.shipCmntFrthLineTxt, shpgCmntMsgList.get(3));
                }
            }

            shpPmsgList.add(shpPmsg);
            NWZC003001 shpApi = new NWZC003001();
            shpApi.execute(shpPmsgList, glOnBatchType);
            if (shpPmsgList.get(0).xxMsgIdList.getValidCount() > 0) {
                glMsgMap.addXxMsgId(shpPmsgList.get(0).xxMsgIdList.no(0).xxMsgId.getValue());
                ZYPEZDItemValueSetter.setValue(glWkPmsg.poLineInfo.no(i).xxMsgId, shpPmsgList.get(0).xxMsgIdList.no(0).xxMsgId.getValue());
                return false;
            }

            // Get Shipping Plan
            SHPG_PLNTMsg shpgPlnTMsg = NPZC104001DBAccess.getShpgPlnTMsg(glWkPmsg.glblCmpyCd.getValue(), poTmsg.poOrdNum.getValue(), wkPoDtl.poOrdDtlLineNum.getValue(), NPZC104001Constant.TRX_LINE_SUB_NUM, dsPoTpTmsg.trxSrcTpCd.getValue());
            if (shpgPlnTMsg == null) {
                glMsgMap.addXxMsgId(NPZC104001Constant.NPAM1600E);
                ZYPEZDItemValueSetter.setValue(glWkPmsg.poLineInfo.no(i).xxMsgId, NPZC104001Constant.NPAM1600E);
                return false;
            }

            // SO API Param Set
            NLZC205001PMsg soPmsg = new NLZC205001PMsg();
            ZYPEZDItemValueSetter.setValue(soPmsg.glblCmpyCd, glWkPmsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(soPmsg.sceOrdTpCd, sceOrdTpCd);
            ZYPEZDItemValueSetter.setValue(soPmsg.shpgPlnNum, shpgPlnTMsg.shpgPlnNum);
            ZYPEZDItemValueSetter.setValue(soPmsg.shpgFrceFlg, NLZC205001.SHPG_FRCE_FLG_ON);
            ZYPEZDItemValueSetter.setValue(soPmsg.xxModeCd, NLZC205001.MODE_NEW);
            soPmsgList.add(soPmsg);

            // Create buyBackPoLineInfoList For RWS
            buyBackPoLineInfoList.add(glPoLineInfoList.get(i));

        }

        // The rest of the record processing.
        // SO API Param Exec
        // QC#30039
        if (!reSubmitBB) {
            NLZC205001 soApi = new NLZC205001();
            soApi.execute(soPmsgList, glOnBatchType);
            if (soPmsgList.get(0).xxMsgIdList.getValidCount() > 0) {
                glMsgMap.addXxMsgId(soPmsgList.get(0).xxMsgIdList.no(0).xxMsgId.getValue());
                if (soPmsgList.get(0).xxMsgIdList.no(0).xxMsgId.getValue().endsWith("E")) {
                    return false;
                }
            }
            // START 2022/03/24 A.Cullano [QC#59359, ADD]
            String soNum = soPmsgList.get(0).soNum.getValue();
            for (int i = 0; i < poDtlTmsgList.size(); i++) {
                PO_DTLTMsg wkPoDtl = poDtlTmsgList.get(i);

                // If the item has a config assigned, no need for additional processing
                if (!hasValue(wkPoDtl.svcConfigMstrPk.getValue())) {
                    Map<String, Object> poSerNum = null;
                    poSerNum = NPZC104001DBAccess.getPoSerNumBuyBack(glWkPmsg.glblCmpyCd.getValue(), wkPoDtl, glSsmBatchClient);
                    
                    // Skip process if a serial number is assigned to the mdse_cd
                    if (poSerNum == null) {
                        List<BigDecimal> svcMachMstrPkList = NPZC104001DBAccess.getSvcMachMstrBuyBack(glWkPmsg.glblCmpyCd.getValue(), wkPoDtl, glSsmBatchClient);

                        // If there at least 1 PK retrieved, update SVC_MACH_MSTR
                        if (svcMachMstrPkList.size() > 0) {
                            for (int j = 0; j < wkPoDtl.poQty.getValue().intValue(); j++) {
                                Map< ? , ? > map = (Map< ? , ? >) svcMachMstrPkList.get(j);
                                BigDecimal svcMachMstrPk = (BigDecimal) map.get("SVC_MACH_MSTR_PK");

                                // Retrieve SO Info detail for a specific PK
                                SoInfo soInfo = NPZC104001DBAccess.getSoInfoBuyBack(
                                        glWkPmsg.glblCmpyCd.getValue(),
                                        soNum,
                                        wkPoDtl.poOrdDtlLineNum.getValue(),
                                        svcMachMstrPk,
                                        glSsmBatchClient);

                                // Prepare data to update SVC_MACH_MSTR table based on SO Info
                                NSZC001001 nszc001001 = new NSZC001001();
                                NSZC001001PMsg nszc001001PMsg = new NSZC001001PMsg();
                                ZYPEZDItemValueSetter.setValue(nszc001001PMsg.glblCmpyCd, glWkPmsg.glblCmpyCd.getValue());
                                ZYPEZDItemValueSetter.setValue(nszc001001PMsg.slsDt, ZYPDateUtil.getSalesDate(glWkPmsg.glblCmpyCd.getValue()));
                                ZYPEZDItemValueSetter.setValue(nszc001001PMsg.xxModeCd, ProcessMode.ALLOCATION_ON.code);
                                ZYPEZDItemValueSetter.setValue(nszc001001PMsg.svcMachMstrPk, svcMachMstrPk);
                                ZYPEZDItemValueSetter.setValue(nszc001001PMsg.trxHdrNum, soInfo.getTrxHdrNum());
                                ZYPEZDItemValueSetter.setValue(nszc001001PMsg.trxLineNum, soInfo.getTrxLineNum());
                                ZYPEZDItemValueSetter.setValue(nszc001001PMsg.trxLineSubNum, soInfo.getTrxLineSubNum());

                                nszc001001.execute(nszc001001PMsg, glOnBatchType);
                                int errCnt = nszc001001PMsg.xxMsgIdList.getValidCount();
                                if (errCnt > 0) {
                                    for (int index = 0; i < errCnt; i++) {
                                        glMsgMap.addXxMsgId(nszc001001PMsg.xxMsgIdList.no(index).xxMsgId.getValue());
                                    }
                                    return false;
                                }
                            }
                        } else {
                            glMsgMap.addXxMsgId(NPZC104001Constant.NWZM0452E);
                            return false;
                        }                        
                    }
                }
            }
            // END 2022/03/24 A.Cullano [QC#59359, ADD]
        }

        // PMsgList Clear.
        soPmsgList.clear();

        // create RWS
        // QC#18644
        // QC#30039
        if (!buyBackPoLineInfoList.isEmpty() && isCrtRws(dsPoTmsg.destRtlWhCd.getValue(), poTmsg.vndCd.getValue())) {
	        if (!NPZC104001Common.createRws(glWkPmsg, glMsgMap, buyBackPoLineInfoList, poTmsg.poStsCd.getValue(), poTmsg.vndCd.getValue(), dsPoTmsg.destRtlWhCd.getValue(), glInternalInfoList, glSsmBatchClient, glOnBatchType, false)) {
	            return false;
	        }
        }
        return true;
    }
    /**
     * QC#18644
     * getRtlWh
     * @param gCompCd
     * @param soNum
     * @return
     */
    private RTL_WHTMsg getRtlWh(String rtlWhCd) {

    	RTL_WHTMsg inMsg = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, glWkPmsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.rtlWhCd, rtlWhCd);
        RTL_WHTMsg outMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(inMsg);

        return outMsg;
    }

    /**
     * QC#18644
     * isCrtRws
     * @param rtlWhTMsg
     * @return
     */
    private boolean isCrtRws(String rtlWhCd, String vndCd) {
        // ASN Vendor?
        RCV_ASN_VNDTMsg asnVnd = NPZC104001DBAccess.getRcvAsnVnd(glWkPmsg.glblCmpyCd.getValue(), vndCd);
        if(asnVnd == null) {
            return true;
        } else {    // ASN Vendor
            // QC#23126
            return false;
//            // is 3PL?
//            RTL_WHTMsg rtlWhTMsg = getRtlWh(rtlWhCd);
//            if (rtlWhTMsg.whOwnrCd != null) {
//                if(glTplWhList.contains(rtlWhTMsg.whOwnrCd.getValue())) {
//                    // 3PL
//                    return false;
//                }
//            }
        }
    }
}
