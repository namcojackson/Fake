/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NPZ.NPZC104001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.common.EZDPBigDecimalItem;
import parts.common.EZDPStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_PO_TPTMsg;
import business.db.FRT_COND_SVC_LVL_RELNTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.MDSETMsg;
import business.db.PO_APVL_STSTMsg;
import business.db.PO_DTLTMsg;
import business.db.PO_LINE_TPTMsg;
import business.db.PO_MSGTMsg;
import business.db.RCV_ASN_VNDTMsg;
import business.db.RTL_SWHTMsg;
import business.db.SHPG_PLNTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.parts.NLZC200001PMsg;
import business.parts.NLZC201001PMsg;
import business.parts.NLZC304001PMsg;
import business.parts.NMZC003001PMsg;
import business.parts.NPZC104001PMsg;
import business.parts.NPZC104001_poAcctInfoPMsg;
import business.parts.NPZC104001_poInfoPMsg;
import business.parts.NPZC104001_poLineInfoPMsg;
import business.parts.NPZC104001_serNumListPMsg;
import business.parts.NPZC104001_svcMachMstrListPMsg;
import business.parts.NPZC109001PMsg;
import business.parts.NPZC113001PMsg;
import business.parts.NPZC129001PMsg;
import business.parts.NPZC130001PMsg;
import business.parts.NPZC134001PMsg;
import business.parts.NSZC001001PMsg;
import business.parts.NWZC003001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC200001.NLZC200001;
import com.canon.cusa.s21.api.NLZ.NLZC201001.NLZC201001;
import com.canon.cusa.s21.api.NLZ.NLZC304001.NLZC304001;
import com.canon.cusa.s21.api.NMZ.NMZC003001.NMZC003001;
import com.canon.cusa.s21.api.NMZ.NMZC003001.constant.NMZC003001Constant;
import com.canon.cusa.s21.api.NPZ.NPZC104001.constant.NPZC104001Constant;
import com.canon.cusa.s21.api.NPZ.NPZC109001.NPZC109001;
import com.canon.cusa.s21.api.NPZ.NPZC109001.constant.NPZC109001Constant;
import com.canon.cusa.s21.api.NPZ.NPZC113001.NPZC113001;
import com.canon.cusa.s21.api.NPZ.NPZC129001.NPZC129001;
import com.canon.cusa.s21.api.NPZ.NPZC130001.NPZC130001;
import com.canon.cusa.s21.api.NPZ.NPZC130001.constant.NPZC130001Constant;
import com.canon.cusa.s21.api.NPZ.NPZC134001.NPZC134001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.NSZC001001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.ProcessMode;
import com.canon.cusa.s21.api.NWZ.NWZC003001.NWZC003001;
import com.canon.cusa.s21.common.NFX.NFXC308001.NFCConst;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCost;
import com.canon.cusa.s21.common.NLX.NLXC001001.NLXC001001GetInventoryItemCostBean;
import com.canon.cusa.s21.common.NLX.NLXC016001.NLXSceConst;
import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001CreateApprovalHistory;
import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001CreateApprovalHistoryBean;
import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001CreatePOHistory;
import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001CurrencyConversion;
import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001CurrencyConversionBean;
import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001PoMsg;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.APVL_HIST_ACT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.APVL_HIST_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DRCT_SHIP_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_PO_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_APVL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_LINE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MATCH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MDSE_CMPSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MSG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_QLFY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PUT_AWAY_CHK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_PLN_UPD_MODE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_SYS_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_UOM;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPMaxTenDigitsNumbering;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * PO Create API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/07/2015   CITS            T.kikuhara      Create          CSA Ver3.0
 * 03/22/2016   CSAI            K.Lee           Update          QC#5856
 * 03/24/2016   CSAI            K.Lee           Update          QC#6024
 * 03/30/2016   CSAI            K.Lee           Update          QC#6180
 * 03/30/2016   CSAI            K.Lee           Update          QC#6102
 * 04/14/2016   CSAI            K.Lee           Update          QC#6664
 * 05/03/2016   CSAI            K.Lee           Update          QC#7300
 * 05/06/2016   CSAI            K.Lee           Update          QC#5762
 * 05/19/2016   CSAI            K.Lee           Update          QC#5332
 * 08/12/2016   CSAI            M.Okigami       Update          PO to WMS without ASN
 * 09/29/2016   CITS            T.Gotoda        Update          QC#13163
 * 10/03/2016   CITS            K.Ogino         Update          QC#12300
 * 10/04/2016   CITS            K.Ogino         Update          QC#14996
 * 10/07/2016   CITS            R.Shimamoto     Update          QC#10455
 * 11/09/2016   CITS            R.Shimamoto     Update          QC#15121
 * 11/09/2016   CITS            T.Hakodate      Update          QC#15933
 * 11/21/2016   CITS            R.Shimamoto     Update          QC#16001
 * 02/27/2017   CITS            S.Endo          Update          QC#17806
 * 05/11/2017   CITS            R.Shimamoto     Update          QC#18100
 * 08/17/2017   CITS            K.Ogino         Update          QC#20506
 * 10/17/2017   CITS            S.Katsuma       Update          QC#21206
 * 10/25/2017   CITS            T.Tokutomi      Update          QC#22074
 * 2017/12/01   CITS            K.Ogino         Update          QC#22481
 * 12/04/2017   CITS            K.Kameoka       Update          QC#14858(Sol#060)
 * 01/30/2018   CITS            K.Ogino         Update          QC#23616
 * 03/15/2018   CITS            K.Ogino         Update          QC#24780
 * 05/31/2018   CITS            Y.Iwasaki       Update          QC#26231
 * 06/20/2018   CITS            K.Ogino         Update          QC#26730
 * 08/07/2018   CITS            K.Kameoka       Update          QC#27474
 * 11/09/2018   CITS            T.Hakodate      Update          QC#29173
 * 11/16/2018   CITS            K.Ogino         Update          QC#29155
 * 12/14/2018   CITS            M.Naito         Update          QC#29027
 * 02/20/2019   CITS            K.Ogino         Update          QC#30438
 * 09/25/2019   CITS            R.Shimamoto     Update          QC#52460
 * 03/02/2020   Fujitsu         T.Ogura         Update          QC#55920
 * 04/06/2020   Fujitsu         T.Ogura         Update          QC#56390
 * 06/02/2021   CITS            M.Furugoori     Update          QC#58872
 * 02/15/2023   Hitachi         S.Dong          Update          QC#60966
 * </pre>
 */
public class NPZC104001Common {

    protected static NPZC104001InternalInfoBean commonInitProcess(NPZC104001PMsg pMsg) {
        NPZC104001InternalInfoBean internalInfo = new NPZC104001InternalInfoBean();
        internalInfo.setPoCustDropQlfyCd(ZYPCodeDataUtil.getVarCharConstValue(NPZC104001Constant.PO_CUST_DROP_QLFY_CD, pMsg.glblCmpyCd.getValue()));
        internalInfo.setPoLineMaxCnt(ZYPCodeDataUtil.getVarCharConstValue(NPZC104001Constant.PO_LINE_MAX_CNT, pMsg.glblCmpyCd.getValue()));
        internalInfo.setDrctCpoCratFlg(ZYPCodeDataUtil.getVarCharConstValue(NPZC104001Constant.DRCT_CPO_CRAT_FLG, pMsg.glblCmpyCd.getValue()));
        internalInfo.setSendPoIfCratFlg(ZYPCodeDataUtil.getVarCharConstValue(NPZC104001Constant.SEND_PO_IF_CRAT_FLG, pMsg.glblCmpyCd.getValue()));
        internalInfo.setBillToCustCd(ZYPCodeDataUtil.getVarCharConstValue(NPZC104001Constant.NPAL0110_BILL_TO_CD, pMsg.glblCmpyCd.getValue()));
        return internalInfo;
    }

    protected static void checkParamForCreateAndUpdate(NPZC104001PMsg pMsg, S21ApiMessageMap msgMap, S21SsmBatchClient glSsmBatchClient) {

        // header mandatory check
        checkProcDt(pMsg, msgMap);
        checkXxRqstTs(pMsg, msgMap);
        checkDsPoTpCd(pMsg, msgMap);
        checkDestRtlWhCd(pMsg, msgMap);
        checkPoOrdSrcCd(pMsg, msgMap);
        checkPrchGrpCd(pMsg, msgMap);

        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        if (DS_PO_TP.BUYBACK_PO.equals(pMsg.dsPoTpCd.getValue())) {
            checkSrcRtlWhCd(pMsg, msgMap);
            checkVndCd(pMsg, msgMap);
        }

        // CREATE MODE
        if (NPZC104001Constant.MODE_CREATE.equals(pMsg.xxModeCd.getValue())) {
            checkEvent(pMsg, msgMap);
            checkPoApvlStsCd(pMsg, msgMap);
            checkCreateEvent(pMsg, msgMap);
        // UPDATE MODE Modify QC#24780
        } else if (NPZC104001Constant.MODE_UPDATE.equals(pMsg.xxModeCd.getValue()) || NPZC104001Constant.MODE_ASN.equals(pMsg.xxModeCd.getValue())) {
            checkPoOrdNum(pMsg, msgMap);
        }

        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // detail param check
        if (!checkPoHdrMsgInfo(pMsg, msgMap)) {
            return;
        }

        if (!checkPoLineInfo(pMsg, msgMap, glSsmBatchClient)) {
            return;
        }

        if (!checkPoSerInfo(pMsg, msgMap)) {
            return;
        }

        // Check PoAcctInfo is in after process getAccount function

    }

    protected static void checkParamForCancelAndSendPo(NPZC104001PMsg pMsg, S21ApiMessageMap msgMap) {

        // header mandatory check
        checkProcDt(pMsg, msgMap);
        checkXxRqstTs(pMsg, msgMap);
        checkPoOrdNum(pMsg, msgMap);

        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

        // detail param check
        // CANCEL MODE
        if (NPZC104001Constant.MODE_CANCEL.equals(pMsg.xxModeCd.getValue())) {
            for (int i = 0; i < pMsg.poLineInfo.getValidCount(); i++) {
                if (!checkPoOrdDtlLineNum(pMsg.poLineInfo.no(i).poOrdDtlLineNum.getValue(), msgMap)) {
                    break;
                }
            }
        }

        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }

    }

    protected static void setDefaultParamCreate(NPZC104001PMsg pMsg) {
        if (!hasValue(pMsg.poSubmtTs)) {
            ZYPEZDItemValueSetter.setValue(pMsg.poSubmtTs, pMsg.xxRqstTs);
        }
        if (!hasValue(pMsg.poApvlStsCd)) {
            ZYPEZDItemValueSetter.setValue(pMsg.poApvlStsCd, PO_APVL_STS.ENTERED);
        }
        if (!hasValue(pMsg.rqstRcvDt)) {
            ZYPEZDItemValueSetter.setValue(pMsg.rqstRcvDt, pMsg.procDt);
        }
        // START 2023/02/15 S.Dong [QC#60966, ADD]
        if (!hasValue(pMsg.rqstShipDt)) {
            ZYPEZDItemValueSetter.setValue(pMsg.rqstShipDt, pMsg.procDt);
        }
        // END 2023/02/15 S.Dong [QC#60966, ADD]
        if (!hasValue(pMsg.shipToCustCd)) {
            ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, pMsg.destRtlWhCd);
        }
        if (!hasValue(pMsg.lineBizTpCd)) {
            ZYPEZDItemValueSetter.setValue(pMsg.lineBizTpCd, LINE_BIZ_TP.ALL);
        }
        if (!hasValue(pMsg.poSendFlg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.poSendFlg, ZYPConstant.FLG_OFF_N);
        }
        if (!hasValue(pMsg.poPrintFlg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.poPrintFlg, ZYPConstant.FLG_OFF_N);
        }
        if (!hasValue(pMsg.dsctnInd)) {
            ZYPEZDItemValueSetter.setValue(pMsg.dsctnInd, ZYPConstant.FLG_OFF_N);
        }
        if (!hasValue(pMsg.wfFlg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.wfFlg, ZYPConstant.FLG_ON_Y);
        }

        for (int i = 0; i < pMsg.poLineInfo.getValidCount(); i++) {
            pMsg.poLineInfo.no(i).poMatchTpCd.clear();
            pMsg.poLineInfo.no(i).invtyLocCd.clear();
            if (!hasValue(pMsg.poLineInfo.no(i).rqstRcvDt)) {
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).rqstRcvDt, pMsg.procDt);
            }
            if (!hasValue(pMsg.poLineInfo.no(i).toStkStsCd)) {
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).toStkStsCd, pMsg.poLineInfo.no(i).fromStkStsCd);
            }
            if (!hasValue(pMsg.poLineInfo.no(i).poInvQty)) {
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).poInvQty, BigDecimal.ZERO);
            }
        }
    }

    protected static void setDisplayLine(NPZC104001PMsg pMsg, S21SsmBatchClient ssmBatchClient) {

        //Skip if Display Line Numbers of all lines already set on PMsg.
        boolean hasAllDispLineNum = true;

        for (int i = 0; i < pMsg.poLineInfo.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(pMsg.poLineInfo.no(i).dispPoDtlLineNum)) {
                hasAllDispLineNum = false;
            }
        }

        if (hasAllDispLineNum) {
            return;
        }

        // Merge New Line and Exist PO Line
        List<Map<String, Object>> poDtlList = null;
        Map<String, String> displayLineMap = new HashMap<String, String>();

        if (NPZC104001Constant.MODE_CREATE.equals(pMsg.xxModeCd.getValue())) {
            poDtlList = new ArrayList<Map<String, Object>>();
        } else {
            poDtlList = NPZC104001DBAccess.getPoDtlMap(pMsg.glblCmpyCd.getValue(), pMsg.poOrdNum.getValue(), ssmBatchClient);
        }

        int size = poDtlList.size();
        for (int i = 0; i < pMsg.poLineInfo.getValidCount(); i++) {
            boolean isExistLine = false;
            for (int j = 0; j < size; j++) {
                String poDtlLineNum = (String) poDtlList.get(j).get("PO_ORD_DTL_LINE_NUM");
                if (poDtlLineNum.equals(pMsg.poLineInfo.no(i).poOrdDtlLineNum.getValue())) {
                    isExistLine = true;
                    break;
                }
            }
            if (!isExistLine) {
                Map<String, Object> poDtl = new HashMap<String, Object>();
                poDtl.put("PO_ORD_DTL_LINE_NUM", pMsg.poLineInfo.no(i).poOrdDtlLineNum.getValue());
                poDtl.put("SET_PO_ORD_DTL_LINE_NUM", pMsg.poLineInfo.no(i).setPoOrdDtlLineNum.getValue());
                poDtl.put("PO_MDSE_CMPSN_TP_CD", pMsg.poLineInfo.no(i).poMdseCmpsnTpCd.getValue());
                poDtlList.add(poDtl);
            }
        }

        // Calculate Display Line
        int dispDtlLineNum = 0;
        for (int i = 0; i < poDtlList.size(); i++) {
            String poOrdDtlLineNum = (String) poDtlList.get(i).get("PO_ORD_DTL_LINE_NUM");
            String poMdseCmpsnTpCd = (String) poDtlList.get(i).get("PO_MDSE_CMPSN_TP_CD");
            String setPoOrdDtlLineNum = (String) poDtlList.get(i).get("SET_PO_ORD_DTL_LINE_NUM");

            int dispDtlLineSubNum = 0;
            if (!poMdseCmpsnTpCd.equals(PO_MDSE_CMPSN_TP.CHILD)) {
                dispDtlLineNum++;
            } else {
                for (int j = 0; j <= i; j++) {
                    String temp = (String) poDtlList.get(j).get("SET_PO_ORD_DTL_LINE_NUM");
                    if (ZYPCommonFunc.hasValue(temp)) {
                        if (setPoOrdDtlLineNum.equals(temp)) {
                            dispDtlLineSubNum++;
                        }
                    }
                }
            }
            displayLineMap.put(poOrdDtlLineNum, dispDtlLineNum + "." + dispDtlLineSubNum);
        }

        // Set Display Line to PMsg
        for (int i = 0; i < pMsg.poLineInfo.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).dispPoDtlLineNum, displayLineMap.get(pMsg.poLineInfo.no(i).poOrdDtlLineNum.getValue()));
        }
    }

    protected static boolean checkDsPoTpData(NPZC104001PMsg pMsg, S21ApiMessageMap msgMap, NPZC104001InternalInfoBean wkInternalInfo) {
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        DS_PO_TPTMsg dsPoTpTMsg = NPZC104001DBAccess.getDsPoTp(glblCmpyCd, pMsg.dsPoTpCd.getValue());
        if (dsPoTpTMsg == null) {
            msgMap.addXxMsgId(NPZC104001Constant.NPZM0116E);
            return false;
        }

        // Set Parameter
        if (!hasValue(pMsg.dsPoTpNm)) {
            ZYPEZDItemValueSetter.setValue(pMsg.dsPoTpNm, dsPoTpTMsg.dsPoTpNm);
        }
        if (!hasValue(pMsg.shpgSvcLvlCd)) {
            ZYPEZDItemValueSetter.setValue(pMsg.shpgSvcLvlCd, dsPoTpTMsg.shpgSvcLvlCd);
        }

        // set inner info
        wkInternalInfo.setSceOrdTpCd(dsPoTpTMsg.sceOrdTpCd.getValue());
        wkInternalInfo.setTrxSrcTpCd(dsPoTpTMsg.trxSrcTpCd.getValue());
        wkInternalInfo.setFromBizAppLocChkKeyId(dsPoTpTMsg.fromBizAppLocChkKeyId.getValue());
        wkInternalInfo.setToBizAppLocChkKeyId(dsPoTpTMsg.toBizAppLocChkKeyId.getValue());
        wkInternalInfo.setEnblAseetFlg(dsPoTpTMsg.enblAssetFlg.getValue());

        return true;
    }

    protected static boolean checkShipToCustData(NPZC104001PMsg pMsg, S21ApiMessageMap msgMap, S21SsmBatchClient ssmBatchClient, ONBATCH_TYPE onBatchType) {
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String shipToCustCd = pMsg.shipToCustCd.getValue();
        Map<String, Object> shipToCustData = NPZC104001DBAccess.getShipToCustMap(glblCmpyCd, shipToCustCd, ssmBatchClient);
        if (shipToCustData == null || shipToCustData.isEmpty()) {
            msgMap.addXxMsgId(NPZC104001Constant.NPZM0146E);
            return false;
        }

        // for check style code
        boolean hasValue = false;
        if (hasValue(pMsg.shipToLocNm)) {
            hasValue = true;
        }
        if (hasValue(pMsg.shipToAcctNm)) {
            hasValue = true;
        }
        if (hasValue(pMsg.shipToAddlLocNm)) {
            hasValue = true;
        }
        if (hasValue(pMsg.shipToFirstLineAddr)) {
            hasValue = true;
        }
        if (hasValue(pMsg.shipToScdLineAddr)) {
            hasValue = true;
        }
        if (hasValue(pMsg.shipToThirdLineAddr)) {
            hasValue = true;
        }
        if (hasValue(pMsg.shipToFrthLineAddr)) {
            hasValue = true;
        }
        if (hasValue(pMsg.shipToFirstRefCmntTxt)) {
            hasValue = true;
        }
        if (hasValue(pMsg.shipToScdRefCmntTxt)) {
            hasValue = true;
        }
        if (hasValue(pMsg.shipToCtyAddr)) {
            hasValue = true;
        }
        if (hasValue(pMsg.shipToStCd)) {
            hasValue = true;
        }
        if (hasValue(pMsg.shipToProvNm)) {
            hasValue = true;
        }
        if (hasValue(pMsg.shipToPostCd)) {
            hasValue = true;
        }
        if (hasValue(pMsg.shipToCtryCd)) {
            hasValue = true;
        }

        // Set Parameter
        if (!hasValue) {
            ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, (String) shipToCustData.get(NPZC104001Constant.SHIP_TO_CUST_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToLocNm, (String) shipToCustData.get(NPZC104001Constant.LOC_NM));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToAcctNm, (String) shipToCustData.get(NPZC104001Constant.ACCT_NM));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToAddlLocNm, (String) shipToCustData.get(NPZC104001Constant.ADDL_LOC_NM));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToFirstLineAddr, (String) shipToCustData.get(NPZC104001Constant.FIRST_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToScdLineAddr, (String) shipToCustData.get(NPZC104001Constant.SCD_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToThirdLineAddr, (String) shipToCustData.get(NPZC104001Constant.THIRD_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToFrthLineAddr, (String) shipToCustData.get(NPZC104001Constant.FRTH_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToFirstRefCmntTxt, (String) shipToCustData.get(NPZC104001Constant.FIRST_REF_CMNT_TXT));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToScdRefCmntTxt, (String) shipToCustData.get(NPZC104001Constant.SCD_REF_CMNT_TXT));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToCtyAddr, (String) shipToCustData.get(NPZC104001Constant.CTY_ADDR));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToStCd, (String) shipToCustData.get(NPZC104001Constant.ST_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToProvNm, (String) shipToCustData.get(NPZC104001Constant.PROV_NM));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToPostCd, (String) shipToCustData.get(NPZC104001Constant.POST_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToCtryCd, (String) shipToCustData.get(NPZC104001Constant.CTRY_CD));
            ZYPEZDItemValueSetter.setValue(pMsg.shipToCntyNm, (String) shipToCustData.get(NPZC104001Constant.CNTY_NM));
        }

        String checkShipToCustFlg = ZYPCodeDataUtil.getVarCharConstValue(NPZC104001Constant.CHECK_SHIP_TO_CUST_FLG, glblCmpyCd);

        if (hasValue(checkShipToCustFlg) && ZYPConstant.FLG_OFF_N.equals(checkShipToCustFlg)) {
            return true;
        }

        NMZC003001PMsg addrValidApiPmsg = new NMZC003001PMsg();
        ZYPEZDItemValueSetter.setValue(addrValidApiPmsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(addrValidApiPmsg.firstLineAddr, pMsg.shipToFirstLineAddr.getValue());
        ZYPEZDItemValueSetter.setValue(addrValidApiPmsg.scdLineAddr, pMsg.shipToScdLineAddr.getValue());
        ZYPEZDItemValueSetter.setValue(addrValidApiPmsg.ctyAddr, pMsg.shipToCtyAddr.getValue());
        ZYPEZDItemValueSetter.setValue(addrValidApiPmsg.stCd, pMsg.shipToStCd.getValue());
        ZYPEZDItemValueSetter.setValue(addrValidApiPmsg.postCd, pMsg.shipToPostCd.getValue());
        ZYPEZDItemValueSetter.setValue(addrValidApiPmsg.ctryCd, pMsg.shipToCtryCd.getValue());
        ZYPEZDItemValueSetter.setValue(addrValidApiPmsg.cntyNm, pMsg.shipToCntyNm.getValue());

        NMZC003001 addrValidApi = new NMZC003001();
        addrValidApi.execute(addrValidApiPmsg, onBatchType);

        if (addrValidApiPmsg.xxMsgIdList.getValidCount() > 0) {
            msgMap.addXxMsgId(addrValidApiPmsg.xxMsgIdList.no(0).xxMsgId.getValue());
            return false;
        }

        if (NMZC003001Constant.RTRN_CD_ERROR.equals(addrValidApiPmsg.xxVldStsCd_01.getValue())) {
            msgMap.addXxMsgId(NPZC104001Constant.NPAM1424E);
            return false;
        }

        if (NMZC003001Constant.RTRN_CD_ERROR.equals(addrValidApiPmsg.xxVldStsCd_02.getValue())) {
            msgMap.addXxMsgId(NPZC104001Constant.NPAM1424E);
            return false;
        }

        if (NMZC003001Constant.RTRN_CD_ERROR.equals(addrValidApiPmsg.xxVldStsCd_03.getValue())) {
            msgMap.addXxMsgId(NPZC104001Constant.NPAM1424E);
            return false;
        }

        if (NMZC003001Constant.RTRN_CD_ERROR.equals(addrValidApiPmsg.xxVldStsCd_04.getValue())) {
            msgMap.addXxMsgId(NPZC104001Constant.NPAM1424E);
            return false;
        }

        if (NMZC003001Constant.RTRN_CD_ERROR.equals(addrValidApiPmsg.xxVldStsCd_05.getValue())) {
            msgMap.addXxMsgId(NPZC104001Constant.NPAM1424E);
            return false;
        }

        if (NMZC003001Constant.RTRN_CD_ERROR.equals(addrValidApiPmsg.xxVldStsCd_06.getValue())) {
            msgMap.addXxMsgId(NPZC104001Constant.NPAM1424E);
            return false;
        }

        if (NMZC003001Constant.RTRN_CD_ERROR.equals(addrValidApiPmsg.xxVldStsCd_07.getValue())) {
            msgMap.addXxMsgId(NPZC104001Constant.NPAM1424E);
            return false;
        }

        // Address Validation check OK
        ZYPEZDItemValueSetter.setValue(pMsg.shipToFirstLineAddr, addrValidApiPmsg.firstLineAddr);
        ZYPEZDItemValueSetter.setValue(pMsg.shipToScdLineAddr, addrValidApiPmsg.scdLineAddr);
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCtyAddr, addrValidApiPmsg.ctyAddr);
        ZYPEZDItemValueSetter.setValue(pMsg.shipToStCd, addrValidApiPmsg.stCd);
        ZYPEZDItemValueSetter.setValue(pMsg.shipToPostCd, addrValidApiPmsg.postCd);
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCtryCd, addrValidApiPmsg.ctryCd);
        ZYPEZDItemValueSetter.setValue(pMsg.shipToCntyNm, addrValidApiPmsg.cntyNm);

        return true;
    }

    protected static boolean checkVendorData(NPZC104001PMsg pMsg, S21ApiMessageMap msgMap, S21SsmBatchClient ssmBatchClient) {
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String vndCd = pMsg.vndCd.getValue();
        if (vndCd == null || vndCd.isEmpty()) {
            return true;
        }
        Map<String, Object> vndData = null;
        if (DS_PO_TP.BUYBACK_PO.equals(pMsg.dsPoTpCd.getValue())) {
            vndData = NPZC104001DBAccess.getVendor(glblCmpyCd, vndCd, ssmBatchClient);
        } else {
            vndData = NPZC104001DBAccess.getVendorBuyBack(glblCmpyCd, vndCd, ssmBatchClient);
        }
        if (vndData == null || vndData.isEmpty()) {
            msgMap.addXxMsgId(NPZC104001Constant.NPZM0043E);
            return false;
        }
        if (!DS_PO_TP.BUYBACK_PO.equals(pMsg.dsPoTpCd.getValue())) {
            if (hasValue(pMsg.dealCcyCd)) {
                String dealCcyCd = (String) vndData.get(NPZC104001Constant.DEAL_CCY_CD);
                if (dealCcyCd == null || !pMsg.dealCcyCd.getValue().equals(dealCcyCd)) {
                    msgMap.addXxMsgId(NPZC104001Constant.NPZM0162E);
                    return false;
                }
            }
        }

        String prntVndCdByDb = (String) vndData.get(NPZC104001Constant.PRNT_VND_CD);
        String prntVndCdByParam = pMsg.prntVndCd.getValue();
        if (!checkPrntVndCd(prntVndCdByDb, prntVndCdByParam)) {
            msgMap.addXxMsgId(NPZC104001Constant.NPAM1425E);
            return false;
        }

        // Set Parameter
        if (!hasValue(pMsg.vndNm)) {
            ZYPEZDItemValueSetter.setValue(pMsg.vndNm, (String) vndData.get(NPZC104001Constant.LOC_NM));
        }
        if (!hasValue(pMsg.prntVndCd)) {
            ZYPEZDItemValueSetter.setValue(pMsg.prntVndCd, prntVndCdByDb);
        }
        if (!hasValue(pMsg.prntVndNm)) {
            ZYPEZDItemValueSetter.setValue(pMsg.prntVndNm, (String) vndData.get(NPZC104001Constant.PRNT_VND_NM));
        }
        if (!hasValue(pMsg.dealCcyCd)) {
            if (!DS_PO_TP.BUYBACK_PO.equals(pMsg.dsPoTpCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(pMsg.dealCcyCd, (String) vndData.get(NPZC104001Constant.DEAL_CCY_CD));
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.dealCcyCd, (String) vndData.get(NPZC104001Constant.STD_CCY_CD));
            }
        }

        if (!hasValue(pMsg.trsmtMethTpCd)) {
            ZYPEZDItemValueSetter.setValue(pMsg.trsmtMethTpCd, (String) vndData.get(NPZC104001Constant.TRSMT_METH_TP_CD));
        }
        if (!hasValue(pMsg.sendPoFaxNum)) {
            ZYPEZDItemValueSetter.setValue(pMsg.sendPoFaxNum, (String) vndData.get(NPZC104001Constant.FAX_NUM));
        }
        if (!hasValue(pMsg.sendPoEmlAddr)) {
            ZYPEZDItemValueSetter.setValue(pMsg.sendPoEmlAddr, (String) vndData.get(NPZC104001Constant.SEND_PO_EML_ADDR));
        }

        return true;
    }

    protected static boolean checkPrntVndCd(String prntVndCdByDb, String prntVndCdByParam) {
        if (!hasValue(prntVndCdByDb) && !hasValue(prntVndCdByParam)) {
            return true; // both empty check OK
        }
        if (!hasValue(prntVndCdByDb) || !hasValue(prntVndCdByParam)) {
            return false; // part empty check NG
        }
        if (!prntVndCdByDb.equals(prntVndCdByParam)) {
            return false; // not equal check NG
        }
        return true; // check OK
    }

    // QC#13163 Start
    protected static boolean checkVndPmtTermCd(NPZC104001PMsg pMsg, S21ApiMessageMap msgMap, S21SsmBatchClient ssmBatchClient) {

        String vndPmtTermCd = null;
        if (hasValue(pMsg.vndPmtTermDescTxt)) {
            vndPmtTermCd = NPZC104001DBAccess.getVndPmtTermCd(pMsg.glblCmpyCd.getValue(), pMsg.vndPmtTermDescTxt.getValue(), ssmBatchClient);
        }

        if (hasValue(pMsg.vndPmtTermCd) && !pMsg.vndPmtTermCd.getValue().equals(vndPmtTermCd)) {
            // Invalid combination. VND_PMT_TERM_CD & VND_PMT_TERM_DESC_TXT
            msgMap.addXxMsgId(NPZC104001Constant.NPZM0293E);
            return false;
        }

        if (hasValue(pMsg.vndPmtTermDescTxt) && !hasValue(vndPmtTermCd)) {
            // Specified VND_PMT_TERM_DESC_TXT does not exist in VND_PMT_TERM table.
            msgMap.addXxMsgId(NPZC104001Constant.NPZM0294E);
            return false;
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.vndPmtTermCd, vndPmtTermCd);
        }

        return true;
    }
    // QC#13163 End

    protected static boolean checkRetailWarehouseData(NPZC104001PMsg pMsg, S21ApiMessageMap msgMap, S21SsmBatchClient ssmBatchClient, NPZC104001InternalInfoBean wkInternalInfo) {
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String rtrnShipToRtlWhCd = pMsg.rtrnShipToRtlWhCd.getValue();
        String shipToStCd = pMsg.shipToStCd.getValue();
        String shipToPostCd = pMsg.shipToPostCd.getValue();
        String poQlfyCd = pMsg.poQlfyCd.getValue();
        String poCustDropQlfyCd = wkInternalInfo.getPoCustDropQlfyCd();

        if (rtrnShipToRtlWhCd == null || rtrnShipToRtlWhCd.isEmpty()) {
            return true;
        }
        // Customer Dropship check
        if (poQlfyCd == null || !poQlfyCd.equals(poCustDropQlfyCd)) {
            return true;
        }
        if (shipToPostCd.length() >= NPZC104001Constant.LENGTH_FIVE) {
            shipToPostCd = shipToPostCd.substring(0, NPZC104001Constant.LENGTH_FIVE);
        }
        Map<String, Object> retailWarehouseData = NPZC104001DBAccess.getRetailWarehouse(glblCmpyCd, shipToStCd, shipToPostCd, ssmBatchClient);
        if (retailWarehouseData == null || retailWarehouseData.isEmpty()) {
            retailWarehouseData = NPZC104001DBAccess.getRetailWarehouseDefault(glblCmpyCd, ssmBatchClient);
            if (retailWarehouseData == null || retailWarehouseData.isEmpty()) {
                msgMap.addXxMsgId(NPZC104001Constant.NPAM1426E);
                return false;
            }
        }
        // Set Parameter
        ZYPEZDItemValueSetter.setValue(pMsg.rtrnShipToRtlWhCd, (String) retailWarehouseData.get(NPZC104001Constant.RTRN_SHIP_TO_RTL_WH_CD));
        return true;
    }

    protected static boolean setBillToCustCd(NPZC104001PMsg pMsg, NPZC104001InternalInfoBean wkInternalInfo) {
        if (!ZYPCommonFunc.hasValue(pMsg.poLineInfo.no(0).billToCustCd)) {
            for (int i = 0; i < pMsg.poLineInfo.getValidCount(); i++) {
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).billToCustCd, wkInternalInfo.getBillToCustCd());
            }
        }
        return true;
    }

    protected static boolean checkCarrierShipServLv(
            NPZC104001PMsg pMsg //
            , S21ApiMessageMap msgMap //
            , S21SsmBatchClient ssmBatchClient //
            , NPZC104001InternalInfoBean wkInternalInfo) {
        // Carrier Code Check
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String carrCd = pMsg.carrCd.getValue();
        if (carrCd != null && !carrCd.isEmpty()) {
            if (0 == NPZC104001DBAccess.getOtbdCarrV(glblCmpyCd, carrCd)) {
                msgMap.addXxMsgId(NPZC104001Constant.NPAM1427E);
                return false;
            }
        }

        // Shipping Service Level Check
        String shpgSvcLvlCd = pMsg.shpgSvcLvlCd.getValue();
        String carrSvcLvlCd = "";
        if (carrCd != null && !carrCd.isEmpty() && shpgSvcLvlCd != null && !shpgSvcLvlCd.isEmpty()) {
            carrSvcLvlCd = NPZC104001DBAccess.getCarrSvcLvlCd(glblCmpyCd, carrCd, shpgSvcLvlCd, ssmBatchClient);
            if (carrSvcLvlCd.isEmpty()) {
                // QC#29155
                carrSvcLvlCd = "";
            }
        }
        // QC#29155
        if (!ZYPCommonFunc.hasValue(carrSvcLvlCd)) {
            carrSvcLvlCd = "";
        }

        // Freight Condition Check
        String lineBizTpCd = pMsg.lineBizTpCd.getValue();
        String poQlfyCd = pMsg.poQlfyCd.getValue();
        boolean custDropShip = false;

        // check custDropShip
        if (ZYPCommonFunc.hasValue(poQlfyCd) //
                && poQlfyCd.equals(wkInternalInfo.getPoCustDropQlfyCd())) {
            custDropShip = true;
        }

        for (int i = 0; i < pMsg.poLineInfo.getValidCount(); i++) {
            String frtCndCd = pMsg.poLineInfo.no(i).frtCondCd.getValue();
            if (frtCndCd == null || frtCndCd.isEmpty()) {
                continue;
            }
            String reFrtCondCd = "";
            if (!custDropShip) {
                // QC#29155
                if (carrSvcLvlCd.isEmpty()) {
                    reFrtCondCd = NPZC104001DBAccess.getfFrtCondCd(glblCmpyCd, lineBizTpCd, frtCndCd, shpgSvcLvlCd, ssmBatchClient);
                } else {
                    FRT_COND_SVC_LVL_RELNTMsg carrSvcTMsg = NPZC104001DBAccess.getFrtCondSvcLvlReln(glblCmpyCd, lineBizTpCd, frtCndCd, shpgSvcLvlCd, carrSvcLvlCd);
                    if (carrSvcTMsg == null) {
                        reFrtCondCd = "";
                    } else {
                        reFrtCondCd = carrSvcTMsg.frtCondCd.getValue();
                    }
                }
            } else {
                reFrtCondCd = pMsg.poLineInfo.no(i).frtCondCd.getValue();
            }
            // QC#29155
            if (!ZYPCommonFunc.hasValue(reFrtCondCd)) {
                reFrtCondCd = "";
            }

            // Carrier Account Check
            String carrAcctNum = pMsg.carrAcctNum.getValue();
            if (reFrtCondCd.equals(NPZC104001Constant.FRT_COND_COLLECT) && carrAcctNum == null) {
                msgMap.addXxMsgId(NPZC104001Constant.NPAM1367E);
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).xxMsgId, NPZC104001Constant.NPAM1367E);
                return false;
            }
        }

        return true;
    }

    // BuyBack PO Location check
    protected static boolean checkBuyBackPoLocation(NPZC104001PMsg pMsg, S21ApiMessageMap msgMap, S21SsmBatchClient ssmBatchClient) {
        if (DS_PO_TP.BUYBACK_PO.equals(pMsg.dsPoTpCd.getValue())) {
            String glblCmpyCd = pMsg.glblCmpyCd.getValue();
            String srcRtlWhCd = pMsg.srcRtlWhCd.getValue();
            String destRtlWhCd = pMsg.destRtlWhCd.getValue();
            Map<String, Object> rtlWhSrcDestData = NPZC104001DBAccess.getRtlWhSrcDest(glblCmpyCd, srcRtlWhCd, destRtlWhCd, ssmBatchClient);
            if (rtlWhSrcDestData == null || rtlWhSrcDestData.isEmpty()) {
                msgMap.addXxMsgId(NPZC104001Constant.NPAM1428E);
                return false;
            }
        }
        return true;
    }

    // Line Type Check
    protected static boolean checkLineType(NPZC104001PMsg pMsg,
                                           NPZC104001_poLineInfoPMsg poLineInfo,
                                           int i,
                                           S21ApiMessageMap msgMap,
                                           NPZC104001InternalInfoBean wkInternalInfo) {
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String enblAseetFlg = wkInternalInfo.getEnblAseetFlg();
        String poLineTpCd = poLineInfo.poLineTpCd.getValue();

        if (enblAseetFlg != null && enblAseetFlg.equals(ZYPConstant.FLG_OFF_N)) {
            if (poLineTpCd.equals(PO_LINE_TP.ASSET)) {
                msgMap.addXxMsgId(NPZC104001Constant.NPAM1429E);
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).xxMsgId, NPZC104001Constant.NPAM1429E);
                return false;
            }
        }

        PO_LINE_TPTMsg poLineTpTMsg = NPZC104001DBAccess.getPoLineTp(glblCmpyCd, poLineTpCd);
        if (poLineTpTMsg != null) {
            // set param
            if (!hasValue(poLineInfo.poMatchTpCd)) {
                ZYPEZDItemValueSetter.setValue(poLineInfo.poMatchTpCd, poLineTpTMsg.poMatchTpCd.getValue());
            }
            // set inner info
            wkInternalInfo.setRwsAutoCratFlg(poLineTpTMsg.rwsAutoCratFlg.getValue());
            wkInternalInfo.setAcrlCmDefAcctCd(poLineTpTMsg.acrlCmDefAcctCd.getValue());
        }

        return true;
    }

    // Destination Location Check
    protected static boolean checkDetailLocation(NPZC104001PMsg pMsg,
                                                 NPZC104001_poLineInfoPMsg poLineInfo,
                                                 int i,
                                                 S21ApiMessageMap msgMap,
                                                 NPZC104001InternalInfoBean wkInternalInfo,
                                                 S21SsmBatchClient ssmBatchClient) {
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String destRtlWhCd = pMsg.destRtlWhCd.getValue();
        String destRtlSwhCd = poLineInfo.destRtlSwhCd.getValue();
        // Destination Location Check
        Map<String, Object> dsInvtyLocV = NPZC104001DBAccess.getDsInvtyLocV(glblCmpyCd, destRtlWhCd, destRtlSwhCd, ssmBatchClient);
        if (dsInvtyLocV == null) {
            msgMap.addXxMsgId(NPZC104001Constant.NPAM1431E);
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).xxMsgId, NPZC104001Constant.NPAM1431E);
            return false;
        } else {
            // set param
            if (!hasValue(pMsg.rqstTechTocCd)) {
                ZYPEZDItemValueSetter.setValue(pMsg.rqstTechTocCd, (String) dsInvtyLocV.get(NPZC104001Constant.TECH_TOC_CD));
            }
            if (!hasValue(poLineInfo.invtyLocCd)) {
                ZYPEZDItemValueSetter.setValue(poLineInfo.invtyLocCd, (String) dsInvtyLocV.get(NPZC104001Constant.INVTY_LOC_CD));
            }
            // set inner info
            wkInternalInfo.setInvtyLocNm((String) dsInvtyLocV.get(NPZC104001Constant.INVTY_LOC_NM));
        }

        // Source Location Check
        String srcRtlWhCd = pMsg.srcRtlWhCd.getValue();
        String srcRtlSwhCd = poLineInfo.srcRtlSwhCd.getValue();
        if (srcRtlWhCd != null && !srcRtlWhCd.isEmpty()) {
            dsInvtyLocV = NPZC104001DBAccess.getDsInvtyLocV(glblCmpyCd, srcRtlWhCd, srcRtlSwhCd, ssmBatchClient);
            if (dsInvtyLocV == null) {
                msgMap.addXxMsgId(NPZC104001Constant.NPAM1432E);
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).xxMsgId, NPZC104001Constant.NPAM1432E);
                return false;
            }
        }

        return true;
    }

    // Item Check
    protected static boolean checkItem(NPZC104001PMsg pMsg, NPZC104001_poLineInfoPMsg poLineInfo, int i, S21ApiMessageMap msgMap, S21SsmBatchClient ssmBatchClient, NPZC104001InternalInfoBean wkInternalInfo) {
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String mdseCd = poLineInfo.mdseCd.getValue();
        Map<String, Object> mdseData = NPZC104001DBAccess.getMdse(glblCmpyCd, mdseCd, ssmBatchClient);
        if (mdseData == null || mdseData.isEmpty()) {
            msgMap.addXxMsgId(NPZC104001Constant.NPAM0069E);
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).xxMsgId, NPZC104001Constant.NPAM0069E);
            return false;
        } else {
            // QC#27474 Mod Start
            if (!DS_PO_TP.BUYBACK_PO.equals(pMsg.dsPoTpCd.getValue())) {
                String dsctnFlg = (String) mdseData.get(NPZC104001Constant.DSCTN_FLG);
                String dsctnInd = pMsg.dsctnInd.getValue();
                if (ZYPConstant.FLG_ON_Y.equals(dsctnFlg) && dsctnInd != null && !ZYPConstant.FLG_OFF_N.equals(dsctnInd)) {
                    msgMap.addXxMsgId(NPZC104001Constant.NPZM0140E);
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).xxMsgId, NPZC104001Constant.NPZM0140E);
                    return false;
                }
                // QC#27474 Mod End
            }
            // set param
            if (!hasValue(poLineInfo.mdseNm)) {
                ZYPEZDItemValueSetter.setValue(poLineInfo.mdseNm, (String) mdseData.get(NPZC104001Constant.MDSE_NM));
            }
            if (!hasValue(poLineInfo.mdseDescShortTxt)) {
                ZYPEZDItemValueSetter.setValue(poLineInfo.mdseDescShortTxt, (String) mdseData.get(NPZC104001Constant.MDSE_DESC_SHORT_TEXT));
            }
            if (!hasValue(poLineInfo.poMdseCmpsnTpCd)) {
                String prntCmpySetMdseFlg = (String) mdseData.get(NPZC104001Constant.PRNT_CMPY_SET_MDSE_FLG);
                if (prntCmpySetMdseFlg != null && ZYPConstant.FLG_ON_Y.equals(prntCmpySetMdseFlg)) {
                    ZYPEZDItemValueSetter.setValue(poLineInfo.poMdseCmpsnTpCd, PO_MDSE_CMPSN_TP.PARENT);
                } else if (prntCmpySetMdseFlg != null && ZYPConstant.FLG_OFF_N.equals(prntCmpySetMdseFlg)) {
                    ZYPEZDItemValueSetter.setValue(poLineInfo.poMdseCmpsnTpCd, PO_MDSE_CMPSN_TP.REGULAR);
                }
            }
            if (!hasValue(poLineInfo.poMatchTpCd)) {
                String invtyCtrlFlg = (String) mdseData.get(NPZC104001Constant.INVTY_CTRL_FLG);
                if (ZYPConstant.FLG_ON_Y.equals(invtyCtrlFlg)) {
                    ZYPEZDItemValueSetter.setValue(poLineInfo.poMatchTpCd, PO_MATCH_TP.RECEIPT);
                } else {
                    ZYPEZDItemValueSetter.setValue(poLineInfo.poMatchTpCd, PO_MATCH_TP.NO_GOODS_RECEIPT);
                }
            }
            wkInternalInfo.setInvtyCtrlFlg((String) mdseData.get(NPZC104001Constant.INVTY_CTRL_FLG));
            wkInternalInfo.setRcvSerTakeFlg((String) mdseData.get(NPZC104001Constant.RCV_SER_TAKE_FLG));
        }

        return true;
    }

    // Vendor Item VendorUOM Check
    protected static boolean checkVendorItemUom(NPZC104001PMsg pMsg,
                                                NPZC104001_poLineInfoPMsg poLineInfo,
                                                int i,
                                                S21ApiMessageMap msgMap,
                                                S21SsmBatchClient ssmBatchClient,
                                                ONBATCH_TYPE onBatchType) {

        if (!DS_PO_TP.BUYBACK_PO.equals(pMsg.dsPoTpCd.getValue())) {

            String vndCd = pMsg.vndCd.getValue();

            // for CheckStyle code
            int returnFlg = 0;
            // QC#16001 Mod.
            if (ZYPCommonFunc.hasValue(vndCd)) {
                returnFlg++;
            }
            if (ZYPCommonFunc.hasValue(pMsg.prntVndCd)) {
                returnFlg++;
            }
            if (ZYPCommonFunc.hasValue(poLineInfo.aslDtlPk)) {
                returnFlg++;
            }
            if (ZYPCommonFunc.hasValue(poLineInfo.entDealNetUnitPrcAmt)) {
                returnFlg++;
            }
            if (ZYPCommonFunc.hasValue(poLineInfo.entPoDtlDealNetAmt)) {
                returnFlg++;
            }

            if (returnFlg == NPZC104001Constant.LENGTH_FIVE) {
                return true;
            }

            // QC#16432 START
            String noAslCheckPoTp = "";
            boolean needAslCheck = true;

            noAslCheckPoTp = ZYPCodeDataUtil.getVarCharConstValue("NO_ASL_CHECK_PO_TP", pMsg.glblCmpyCd.getValue());

            if (ZYPCommonFunc.hasValue(noAslCheckPoTp)) {

                String[] array = noAslCheckPoTp.split(",");

                for (int j = 0; j < array.length; j++) {

                    String poTp = array[j];

                    if (poTp.equals(pMsg.dsPoTpCd.getValue())) {
                        needAslCheck = false;
                        break;
                    }

                }
            }
            
            // START 2017/12/04 [QC#14858, ADD]. QC#23616 Mod
            // TEXT ITEM: do not asl check
            if (PO_LINE_TP.EXPENSE.equals(poLineInfo.poLineTpCd.getValue()) // 
                    || PO_LINE_TP.EXPENSE_WITH_RECEIPT.equals(poLineInfo.poLineTpCd.getValue()) //
                    || PO_LINE_TP.ASSET.equals(poLineInfo.poLineTpCd.getValue())) { //
                // get MDSE_ITEM_TP_CD
                MDSETMsg mdseTmsg = NPZC104001DBAccess.findMdse(pMsg.glblCmpyCd.getValue(), poLineInfo.mdseCd.getValue());

                if (mdseTmsg != null) {
                    if (MDSE_ITEM_TP.TEXT_ITEM.equals(mdseTmsg.mdseItemTpCd.getValue())) {
                        needAslCheck = false;
                    }
                }
            }
            // END 2017/12/04 [QC#14858, ADD]

            // START 04/06/2020 T.Ogura [QC#56390,MOD]
//            if (needAslCheck) {
            String openPoFlg = NPZC104001DBAccess.getOpenPoFlg(pMsg, poLineInfo.poOrdDtlLineNum.getValue(), ssmBatchClient);
            if (needAslCheck && (!hasValue(openPoFlg) || ZYPConstant.FLG_ON_Y.equals(openPoFlg))) {
            // END   04/06/2020 T.Ogura [QC#56390,MOD]

                // get Primary Vendor from ASL
                NPZC113001PMsg vendorPmsg = new NPZC113001PMsg();
                ZYPEZDItemValueSetter.setValue(vendorPmsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());

                if (pMsg.poSubmtTs.getValue().length() > NPZC104001Constant.LENGTH_8) {
                    ZYPEZDItemValueSetter.setValue(vendorPmsg.slsDt, pMsg.poSubmtTs.getValue().substring(0, NPZC104001Constant.LENGTH_8));
                } else {
                    ZYPEZDItemValueSetter.setValue(vendorPmsg.slsDt, pMsg.poSubmtTs.getValue());
                }

                // ZYPEZDItemValueSetter.setValue(vendorPmsg.slsDt,
                // pMsg.poSubmtTs.getValue());

                ZYPEZDItemValueSetter.setValue(vendorPmsg.shipToCustCd, pMsg.shipToCustCd.getValue());
                if (ZYPCommonFunc.hasValue(vndCd)) {
                    ZYPEZDItemValueSetter.setValue(vendorPmsg.vndCd, vndCd);
                }
                ZYPEZDItemValueSetter.setValue(vendorPmsg.prntVndCd, pMsg.prntVndCd.getValue());
                ZYPEZDItemValueSetter.setValue(vendorPmsg.mdseCd, poLineInfo.mdseCd.getValue());

                NPZC113001 vendorApi = new NPZC113001();
                vendorApi.execute(vendorPmsg, onBatchType);

                if (ZYPConstant.FLG_ON_Y.equals(vendorPmsg.xxErrFlg.getValue()) || vendorPmsg.xxMsgIdList.getValidCount() > 0) {
                    msgMap.addXxMsgId(NPZC104001Constant.NPAM1364E);
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).xxMsgId, NPZC104001Constant.NPAM1364E);
                    return false;

                } else {
                    // set param
                    if (!hasValue(pMsg.vndCd)) {
                        ZYPEZDItemValueSetter.setValue(pMsg.vndCd, vendorPmsg.vndCd);
                    }
                    if (!hasValue(pMsg.prntVndCd)) {
                        ZYPEZDItemValueSetter.setValue(pMsg.prntVndCd, vendorPmsg.prntVndCd);
                    }
                    if (!hasValue(poLineInfo.aslDtlPk)) {
                        ZYPEZDItemValueSetter.setValue(poLineInfo.aslDtlPk, vendorPmsg.aslDtlPk);
                    }
                    if (!hasValue(poLineInfo.aslMdseCd)) {
                        if (vendorPmsg.mdseCd.getValue() == null) {
                            ZYPEZDItemValueSetter.setValue(poLineInfo.aslMdseCd, vendorPmsg.mdseCd);
                        } else {
                            ZYPEZDItemValueSetter.setValue(poLineInfo.aslMdseCd, poLineInfo.mdseCd);
                        }
                    }
                    if (!hasValue(poLineInfo.poDispUomCd)) {
                        ZYPEZDItemValueSetter.setValue(poLineInfo.poDispUomCd, vendorPmsg.vndUomCd);
                    }
                    if (!hasValue(poLineInfo.aslUnitPrcAmt)) {
                        ZYPEZDItemValueSetter.setValue(poLineInfo.aslUnitPrcAmt, vendorPmsg.unitPrcAmt);
                    }
                    if (!hasValue(poLineInfo.entDealNetUnitPrcAmt)) {
                        ZYPEZDItemValueSetter.setValue(poLineInfo.entDealNetUnitPrcAmt, vendorPmsg.unitPrcAmt);
                    }
                    if (!hasValue(poLineInfo.entPoDtlDealNetAmt)) {
                        BigDecimal poDispQty = poLineInfo.poDispQty.getValue();
                        BigDecimal entDealNetUnitPrcAmt = poLineInfo.entDealNetUnitPrcAmt.getValue();
                        if (poDispQty != null) {
                            BigDecimal entPoDtlDealNetAmt = poDispQty.multiply(entDealNetUnitPrcAmt);
                            ZYPEZDItemValueSetter.setValue(poLineInfo.entPoDtlDealNetAmt, entPoDtlDealNetAmt);
                        }
                    }
                }

            }
            // QC#16432 END
            
            // get Vendor Info
            if (!checkVendorData(pMsg, msgMap, ssmBatchClient)) {
                return false;
            }
            
        } else { // Not BUY BACK
            // get Inventory Item Cost
            // QC#30039. Mod QC#30438
            if (!ZYPCommonFunc.hasValue(poLineInfo.entDealNetUnitPrcAmt)) {
                NLXC001001GetInventoryItemCostBean invItemCost = new NLXC001001GetInventoryItemCostBean();
                invItemCost.setGlblCmpyCd(pMsg.glblCmpyCd.getValue());
                invItemCost.setInvtyLocCd(pMsg.destRtlWhCd.getValue() + poLineInfo.destRtlSwhCd.getValue());
                invItemCost.setMdseCd(poLineInfo.mdseCd.getValue());
                if (hasValue(poLineInfo.poDispQty)) {
                    invItemCost.setQty(poLineInfo.poDispQty.getValue());
                } else {
                    invItemCost.setQty(poLineInfo.poQty.getValue());
                }

                NLXC001001GetInventoryItemCost.getInventoryItemCost(invItemCost);

                if (!invItemCost.getErrList().isEmpty()) {
                    msgMap.addXxMsgId(invItemCost.getErrList().get(0));
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).xxMsgId, invItemCost.getErrList().get(0));
                    return false;
                } else {
                    // set param
                    ZYPEZDItemValueSetter.setValue(poLineInfo.poDispUomCd, VND_UOM.EACH);
                    if (invItemCost.getUnitPrcAmt().compareTo(poLineInfo.entDealNetUnitPrcAmt.getValue()) != 0) {
                        ZYPEZDItemValueSetter.setValue(poLineInfo.entDealNetUnitPrcAmt, invItemCost.getUnitPrcAmt());
                    }
                    ZYPEZDItemValueSetter.setValue(poLineInfo.entPoDtlDealNetAmt, invItemCost.getTotPrcAmt());
                }
            } else {
                ZYPEZDItemValueSetter.setValue(poLineInfo.thisMthFobCostAmt, poLineInfo.entDealNetUnitPrcAmt);
                BigDecimal poDispQty = poLineInfo.poDispQty.getValue();
                BigDecimal entDealNetUnitPrcAmt = poLineInfo.entDealNetUnitPrcAmt.getValue();
                if (poDispQty != null) {
                    BigDecimal entPoDtlDealNetAmt = poDispQty.multiply(entDealNetUnitPrcAmt);
                    ZYPEZDItemValueSetter.setValue(poLineInfo.entPoDtlDealNetAmt, entPoDtlDealNetAmt);
                }
            }
        }
        return true;
    }

    // change QTY
    // START 04/06/2020 T.Ogura [QC#56390,MOD]
//    protected static boolean changeQty(NPZC104001PMsg pMsg, NPZC104001_poLineInfoPMsg poLineInfo, int i, S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchType) {
    protected static boolean changeQty(NPZC104001PMsg pMsg, NPZC104001_poLineInfoPMsg poLineInfo, int i, S21ApiMessageMap msgMap, S21SsmBatchClient ssmBatchClient, ONBATCH_TYPE onBatchType) {
    // END   04/06/2020 T.Ogura [QC#56390,MOD]

        // QC#16432 if BuyBack. do not asl check
        String noAslCheckPoTp = "";
        noAslCheckPoTp = ZYPCodeDataUtil.getVarCharConstValue("NO_ASL_CHECK_PO_TP", pMsg.glblCmpyCd.getValue());

        if (ZYPCommonFunc.hasValue(noAslCheckPoTp)) {
            String[] array = noAslCheckPoTp.split(",");

            for (int j = 0; j < array.length; j++) {

                String poTp = array[j];

                if (poTp.equals(pMsg.dsPoTpCd.getValue())) {
                    // QC#18100 Add.
                    if (!ZYPCommonFunc.hasValue(poLineInfo.poDispQty)) {
                        ZYPEZDItemValueSetter.setValue(poLineInfo.poDispQty, poLineInfo.poQty);
                    }

                    return true;

                }

            }
        }

        // QC#16432 END
        
        // START 2017/12/04 [QC#14858, ADD]. QC#23616 Mod
        // TEXT ITEM: do not asl check
        if (PO_LINE_TP.EXPENSE.equals(poLineInfo.poLineTpCd.getValue()) // 
                || PO_LINE_TP.EXPENSE_WITH_RECEIPT.equals(poLineInfo.poLineTpCd.getValue()) //
                || PO_LINE_TP.ASSET.equals(poLineInfo.poLineTpCd.getValue())) { //
            // get MDSE_ITEM_TP_CD
            MDSETMsg mdseTmsg = NPZC104001DBAccess.findMdse(pMsg.glblCmpyCd.getValue(), poLineInfo.mdseCd.getValue());

            if (mdseTmsg != null) {
                if (MDSE_ITEM_TP.TEXT_ITEM.equals(mdseTmsg.mdseItemTpCd.getValue())) {
                    return true;
                }
            }
        }
        // END 2017/12/04 [QC#14858, ADD]

        // QC#20506 Add
        if (PO_QLFY.DROPSHIP.equals(pMsg.poQlfyCd.getValue())) {
            return true;
        }

        // START 04/06/2020 T.Ogura [QC#56390,ADD]
        String openPoFlg = NPZC104001DBAccess.getOpenPoFlg(pMsg, poLineInfo.poOrdDtlLineNum.getValue(), ssmBatchClient);
        if (hasValue(openPoFlg) && ZYPConstant.FLG_OFF_N.equals(openPoFlg)) {
            return true;
        }
        // END   04/06/2020 T.Ogura [QC#56390,ADD]

        // change Vendor UOM
        NPZC129001PMsg vendorPmsg = new NPZC129001PMsg();
        ZYPEZDItemValueSetter.setValue(vendorPmsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        if (ZYPCommonFunc.hasValue(pMsg.poSubmtTs) && pMsg.poSubmtTs.getValue().length() > 8) {
            ZYPEZDItemValueSetter.setValue(vendorPmsg.slsDt, pMsg.poSubmtTs.getValue().substring(0, 8));
        } else {
            ZYPEZDItemValueSetter.setValue(vendorPmsg.slsDt, pMsg.poSubmtTs.getValue());
        }

        ZYPEZDItemValueSetter.setValue(vendorPmsg.prntVndCd, pMsg.prntVndCd.getValue());
        ZYPEZDItemValueSetter.setValue(vendorPmsg.vndCd, pMsg.vndCd.getValue());
        ZYPEZDItemValueSetter.setValue(vendorPmsg.mdseCd, poLineInfo.mdseCd.getValue());
        ZYPEZDItemValueSetter.setValue(vendorPmsg.poQty, poLineInfo.poQty.getValue());
        ZYPEZDItemValueSetter.setValue(vendorPmsg.poDispQty, poLineInfo.poDispQty.getValue());

        NPZC129001 vendorApi = new NPZC129001();
        vendorApi.execute(vendorPmsg, onBatchType);

        if (vendorPmsg.xxMsgIdList.getValidCount() > 0) {
            // QC#24780
            if (NPZC104001Constant.MODE_ASN.equals(pMsg.xxModeCd.getValue())) {
                NPZC129001PMsg asnVendorPmsg = new NPZC129001PMsg();
                ZYPEZDItemValueSetter.setValue(asnVendorPmsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(asnVendorPmsg.slsDt, pMsg.procDt);
                ZYPEZDItemValueSetter.setValue(asnVendorPmsg.prntVndCd, pMsg.prntVndCd.getValue());
                ZYPEZDItemValueSetter.setValue(asnVendorPmsg.vndCd, pMsg.vndCd.getValue());
                ZYPEZDItemValueSetter.setValue(asnVendorPmsg.mdseCd, poLineInfo.mdseCd.getValue());
                ZYPEZDItemValueSetter.setValue(asnVendorPmsg.poQty, poLineInfo.poQty.getValue());
                ZYPEZDItemValueSetter.setValue(asnVendorPmsg.poDispQty, poLineInfo.poDispQty.getValue());
                vendorApi.execute(asnVendorPmsg, onBatchType);
                if (asnVendorPmsg.xxMsgIdList.getValidCount() > 0) {
                    msgMap.addXxMsgId(vendorPmsg.xxMsgIdList.no(0).xxMsgId.getValue());
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).xxMsgId, vendorPmsg.xxMsgIdList.no(0).xxMsgId.getValue());
                    return false;
                }
                ZYPEZDItemValueSetter.setValue(poLineInfo.poQty, asnVendorPmsg.poQty_O1);
                ZYPEZDItemValueSetter.setValue(poLineInfo.poDispQty, asnVendorPmsg.poDispQty_O1);
                return true;
            }
            msgMap.addXxMsgId(vendorPmsg.xxMsgIdList.no(0).xxMsgId.getValue());
            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).xxMsgId, vendorPmsg.xxMsgIdList.no(0).xxMsgId.getValue());
            return false;
        } else {
            ZYPEZDItemValueSetter.setValue(poLineInfo.poQty, vendorPmsg.poQty_O1);
            ZYPEZDItemValueSetter.setValue(poLineInfo.poDispQty, vendorPmsg.poDispQty_O1);
        }
        return true;
    }

    // change Price
    protected static boolean changePrice(NPZC104001PMsg pMsg, NPZC104001_poLineInfoPMsg poLineInfo, int i, S21ApiMessageMap msgMap) {

        if (hasValue(poLineInfo.entFuncNetUnitPrcAmt) && hasValue(poLineInfo.entPoDtlFuncNetAmt)) {
            return true;
        }

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String dealCcyCd = pMsg.dealCcyCd.getValue();

        // get STD_CCY_CD
        GLBL_CMPYTMsg glblCmpyTMsg = NPZC104001DBAccess.getGlblCmpy(glblCmpyCd);
        String stdCcyCd = glblCmpyTMsg.stdCcyCd.getValue();

        BigDecimal entDealNetUnitPrcAmt = poLineInfo.entDealNetUnitPrcAmt.getValue();
        BigDecimal entPoDtlDealNetAmt = poLineInfo.entPoDtlDealNetAmt.getValue();
        BigDecimal poQty = poLineInfo.poQty.getValue();

        if (dealCcyCd != null && dealCcyCd.equals(stdCcyCd)) {
            // set param
            ZYPEZDItemValueSetter.setValue(poLineInfo.entFuncNetUnitPrcAmt, entDealNetUnitPrcAmt);
            ZYPEZDItemValueSetter.setValue(poLineInfo.entPoDtlFuncNetAmt, entPoDtlDealNetAmt);
            ZYPEZDItemValueSetter.setValue(poLineInfo.exchRate, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(poLineInfo.thisMthFobCostAmt, entDealNetUnitPrcAmt);
            return true;
        } else {
            // get func currency
            NPXC001001CurrencyConversion currCon = new NPXC001001CurrencyConversion();
            NPXC001001CurrencyConversionBean currConBean = currCon.convertCurrency(glblCmpyCd, dealCcyCd, entDealNetUnitPrcAmt, pMsg.procDt.getValue(), null);
            if (NFCConst.CST_RTN_CD_ERR.equals(currConBean.getRtrnCd())) {
                msgMap.addXxMsgId(NPZC104001Constant.NPAM1433E);
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).xxMsgId, NPZC104001Constant.NPAM1433E);
                return false;
            }

            // get amount currency
            NPXC001001CurrencyConversionBean currConBeanAmt = currCon.convertCurrency(glblCmpyCd, dealCcyCd, entDealNetUnitPrcAmt.multiply(poQty), pMsg.procDt.getValue(), null);
            if (NFCConst.CST_RTN_CD_ERR.equals(currConBeanAmt.getRtrnCd())) {
                msgMap.addXxMsgId(NPZC104001Constant.NPAM1433E);
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).xxMsgId, NPZC104001Constant.NPAM1433E);
                return false;
            }

            // set param
            ZYPEZDItemValueSetter.setValue(poLineInfo.entFuncNetUnitPrcAmt, currConBean.getFuncAmt());
            ZYPEZDItemValueSetter.setValue(poLineInfo.entPoDtlFuncNetAmt, currConBean.getFuncAmt());
            ZYPEZDItemValueSetter.setValue(poLineInfo.exchRate, currConBean.getExchRate());
            BigDecimal thisMthFobCostAmt = (currConBean.getFuncAmt().divide(poQty)).setScale(NPZC104001Constant.ROUND_UP_NO_3, BigDecimal.ROUND_DOWN);
            ZYPEZDItemValueSetter.setValue(poLineInfo.thisMthFobCostAmt, thisMthFobCostAmt);
        }
        return true;
    }

    // check Serial
    protected static boolean checkSerial(NPZC104001PMsg pMsg,
                                         NPZC104001_poLineInfoPMsg poLineInfo,
                                         List<NPZC104001_serNumListPMsg> serNumList,
                                         int i,
                                         S21ApiMessageMap msgMap,
                                         S21SsmBatchClient ssmBatchClient,    // 03/02/2020 T.Ogura [QC#55920,ADD]
                                         NPZC104001InternalInfoBean wkInternalInfo) {

        if (serNumList.size() == 0) {
            return true;
        }

        // START 03/02/2020 T.Ogura [QC#55920,MOD]
//        if (i == 0) {
//            // sernum duplicate check
//            String oldSerNum = serNumList.get(i).serNum.getValue();
//            for (int j = 1; j < serNumList.size(); j++) {
//                if (oldSerNum.equals(serNumList.get(j).serNum.getValue())) {
//                    msgMap.addXxMsgId(NPZC104001Constant.NPAM0039E);
//                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).xxMsgId, NPZC104001Constant.NPAM0039E);
//                    return false;
//                } else {
//                    oldSerNum = serNumList.get(j).serNum.getValue();
//                }
//            }
//        }
        if (i == 0) {
            // sernum duplicate check
            HashMap<String, HashSet<String>> serChkList = new HashMap<String, HashSet<String>>();

            for (NPZC104001_serNumListPMsg serNumInfo : serNumList) {
                String curMdseCd = serNumInfo.mdseCd.getValue();
                String curSerNum = serNumInfo.serNum.getValue();
                String curPoOrdDtlLineNum = serNumInfo.poOrdDtlLineNum.getValue();

                if (serChkList.containsKey(curMdseCd)) {

                    // Line duplicate
                    HashSet<String> set = serChkList.get(curMdseCd);
                    if (set.contains(curSerNum)) {
                        // Duplicate
                        msgMap.addXxMsgId(NPZC104001Constant.NPAM0039E);
                        for (int j = 0; j < pMsg.poLineInfo.getValidCount(); j++) {
                            if (curPoOrdDtlLineNum.equals(pMsg.poLineInfo.no(j).poOrdDtlLineNum.getValue())) {
                                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(j).xxMsgId, NPZC104001Constant.NPAM0039E);
                                break;
                            }
                        }
                        return false;

                    } else {
                        set.add(curSerNum);

                        // check duplicate DB
                        if (isDuplicateSerialNum(pMsg, curSerNum, curMdseCd, ssmBatchClient)) {
                            // Duplicate
                            msgMap.addXxMsgId(NPZC104001Constant.NPAM0039E);
                            for (int j = 0; j < pMsg.poLineInfo.getValidCount(); j++) {
                                if (curPoOrdDtlLineNum.equals(pMsg.poLineInfo.no(j).poOrdDtlLineNum.getValue())) {
                                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(j).xxMsgId, NPZC104001Constant.NPAM0039E);
                                    break;
                                }
                            }
                            return false;
                        }
                    }
                } else {

                    // check duplicate DB
                    if (isDuplicateSerialNum(pMsg, curSerNum, curMdseCd, ssmBatchClient)) {
                        // Duplicate
                        msgMap.addXxMsgId(NPZC104001Constant.NPAM0039E);
                        for (int j = 0; j < pMsg.poLineInfo.getValidCount(); j++) {
                            if (curPoOrdDtlLineNum.equals(pMsg.poLineInfo.no(j).poOrdDtlLineNum.getValue())) {
                                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(j).xxMsgId, NPZC104001Constant.NPAM0039E);
                                break;
                            }
                        }
                        return false;
                    }

                    // add chkList
                    HashSet<String> set = new HashSet<String>();
                    set.add(curSerNum);
                    serChkList.put(curMdseCd, set);
                }
            }
        }
        // END   03/02/2020 T.Ogura [QC#55920,MOD]

        // serNumList Count check
        int poSerNumListCnt = 0;
        for (NPZC104001_serNumListPMsg serNumPMsg : serNumList) {
            if (serNumPMsg.poOrdDtlLineNum.getValue().equals(poLineInfo.poOrdDtlLineNum.getValue())) {
                poSerNumListCnt++;

                // Serialized Item check
                // for CheckStyle code
                int returnFlg = 0;
                if (DS_PO_TP.BUYBACK_PO.equals(pMsg.dsPoTpCd.getValue())) {
                    returnFlg++;
                }
                if (wkInternalInfo != null) {
                    returnFlg++;
                }
                if (wkInternalInfo.getRcvSerTakeFlg() != null) {
                    returnFlg++;
                }
                if (ZYPConstant.FLG_ON_Y.equals(wkInternalInfo.getRcvSerTakeFlg())) {
                    returnFlg++;
                }
                if (!ZYPCommonFunc.hasValue(serNumPMsg.serNum)) {
                    returnFlg++;
                }

                if (returnFlg == NPZC104001Constant.LENGTH_FIVE) {
                    msgMap.addXxMsgId(NPZC104001Constant.NPAM1435E);
                    return false;
                }
            }
        }
        if (poLineInfo.poQty.getValueInt() < poSerNumListCnt) {
            msgMap.addXxMsgId(NPZC104001Constant.NPAM1434E);
            return false;
        }

        return true;
    }

    // START 03/02/2020 T.Ogura [QC#55920,ADD]
    /**
     * isDuplicateSerialNum
     * @param NPZC104001PMsg NPZC104001PMsg
     * @param serNum String
     * @param mdseCd String
     * @param ssmBatchClient S21SsmBatchClient
     * @return boolean
     */
    private static boolean isDuplicateSerialNum(NPZC104001PMsg pMsg, String serNum, String mdseCd, S21SsmBatchClient ssmBatchClient) {

        int count = NPZC104001DBAccess.getPoSerNumPk(pMsg, serNum, mdseCd, ssmBatchClient);

        if (count > 0) {
            // Duplicate
            return true;
        } else {
            // Not duplicate
            return false;
        }
    }
    // END   03/02/2020 T.Ogura [QC#55920,ADD]

    /**
     * checkBuyBackPoConf
     * @param pMsg NPZC104001PMsg
     * @param poLineInfo NPZC104001_poLineInfoPMsg
     * @param i int
     * @param msgMap S21ApiMessageMap
     * @param ssmBatchClient S21SsmBatchClient
     * @param wkInternalInfo NPZC104001InternalInfoBean
     * @param svcConfigMstrPkSet HashSet<BigDecimal>
     * @return boolean
     */
    protected static boolean checkBuyBackPoConf(NPZC104001PMsg pMsg,
                                                NPZC104001_poLineInfoPMsg poLineInfo,
                                                int i,
                                                S21ApiMessageMap msgMap,
                                                S21SsmBatchClient ssmBatchClient,
                                                NPZC104001InternalInfoBean wkInternalInfo,
                                                HashSet<BigDecimal> svcConfigMstrPkSet) {

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();

        if (DS_PO_TP.BUYBACK_PO.equals(pMsg.dsPoTpCd.getValue())) {

            // Check Service Machine Master
            for (int index = 0; index < pMsg.svcMachMstrList.getValidCount(); index++) {

                NPZC104001_svcMachMstrListPMsg svcMachMstrInfo = pMsg.svcMachMstrList.no(index);

                if (poLineInfo.poOrdDtlLineNum.getValue().equals(svcMachMstrInfo.poOrdDtlLineNum.getValue())) {

                    SVC_MACH_MSTRTMsg svcMachMstrTMsg = NPZC104001DBAccess.getSvcMachMstr(glblCmpyCd, svcMachMstrInfo.svcMachMstrPk.getValue());

                    if (svcMachMstrTMsg == null) {

                        msgMap.addXxMsgId(NPZC104001Constant.NPZM0265E);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).xxMsgId, NPZC104001Constant.NPZM0265E);
                        return false;
                    }

                    BigDecimal svcConfigMstrPk = svcMachMstrTMsg.svcConfigMstrPk.getValue();
//                    String trxHdrNum = svcMachMstrTMsg.trxHdrNum.getValue();
                    String svcMachMstrStsCd = svcMachMstrTMsg.svcMachMstrStsCd.getValue();
                    String curLocNum = svcMachMstrTMsg.curLocNum.getValue();
                    RTL_SWHTMsg rtlSwhTmsg = NPZC104001DBAccess.getRtlSwh(glblCmpyCd, curLocNum, pMsg.procDt.getValue());

                    if (!checkSameVal(svcConfigMstrPk, svcMachMstrInfo.svcConfigMstrPk.getValue())) {

                        msgMap.addXxMsgId(NPZC104001Constant.NPZM0266E);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).xxMsgId, NPZC104001Constant.NPZM0266E);
                        return false;
// 10/14/2016 QC#6159 DEL Start.
//                    } else if (PO_APVL_STS.APPROVED.equals(pMsg.poApvlStsCd.getValue())) {
//
//                        if (!checkSameVal(trxHdrNum, pMsg.poOrdNum.getValue())) {
//                            msgMap.addXxMsgId(NPZC104001Constant.NPAM1370E);
//                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).xxMsgId, NPZC104001Constant.NPAM1370E);
//                            return false;
//                        }
// 10/14/2016 QC#6159 DEL End.

                    } else if (!SVC_MACH_MSTR_STS.CREATED.equals(svcMachMstrStsCd) && !SVC_MACH_MSTR_STS.REMOVED.equals(svcMachMstrStsCd)) {

                        msgMap.addXxMsgId(NPZC104001Constant.NPAM1511E);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).xxMsgId, NPZC104001Constant.NPAM1511E);
                        return false;

                    } else if (rtlSwhTmsg != null) {
                        // QC#30039
                        PO_DTLTMsg poDtlTmsg = NPZC104001DBAccess.getPoDtl(pMsg.glblCmpyCd.getValue(), pMsg.poOrdNum.getValue(), poLineInfo.poOrdDtlLineNum.getValue());
                        if (poDtlTmsg != null && !PO_STS.SAVED.equals(poDtlTmsg.poStsCd.getValue())) {
                            return true;
                        }   

                        String rtlWhCd = rtlSwhTmsg.rtlWhCd.getValue();
                        String rtlSwhCd = rtlSwhTmsg.rtlSwhCd.getValue();

                        if (!checkSameVal(rtlWhCd, pMsg.srcRtlWhCd.getValue())) {

                            msgMap.addXxMsgId(NPZC104001Constant.NPAM1510E);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).xxMsgId, NPZC104001Constant.NPAM1510E);
                            return false;

                        } else if (!checkSameVal(rtlSwhCd, poLineInfo.srcRtlSwhCd.getValue())) {

                            msgMap.addXxMsgId(NPZC104001Constant.NPAM1510E);
                            ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).xxMsgId, NPZC104001Constant.NPAM1510E);
                            return false;
                        }

                    } else if (rtlSwhTmsg == null) {

                        msgMap.addXxMsgId(NPZC104001Constant.NPAM1510E);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).xxMsgId, NPZC104001Constant.NPAM1510E);
                        return false;
                    }
                }
            }

            // Not specify Config PK
            if (!hasValue(poLineInfo.svcConfigMstrPk.getValue())) {

                return true;
            }

            // Check Config Component
            if (svcConfigMstrPkSet.isEmpty() || !svcConfigMstrPkSet.contains(poLineInfo.svcConfigMstrPk.getValue())) {

                PO_DTLTMsg poDtlTmsg = NPZC104001DBAccess.getPoDtl(pMsg.glblCmpyCd.getValue(), pMsg.poOrdNum.getValue(), poLineInfo.poOrdDtlLineNum.getValue());
                if (poDtlTmsg != null && !PO_STS.SAVED.equals(poDtlTmsg.poStsCd.getValue())) {
                    return true;
                }
                
                BigDecimal svcConfigMstrPk = poLineInfo.svcConfigMstrPk.getValue();

                // Get Config Component
                String srcRtlWhCd = pMsg.srcRtlWhCd.getValue();
                List<Map<String, Object>> configCompList = NPZC104001DBAccess.getConfigComponet(glblCmpyCd, svcConfigMstrPk, srcRtlWhCd, ssmBatchClient);

                if (configCompList == null || configCompList.size() == 0) {

                    msgMap.addXxMsgId(NPZC104001Constant.NPAM1436E);
                    return false;
                }

                BigDecimal configCompCnt = new BigDecimal(configCompList.size());

                // Check Quantity
                BigDecimal poDispQty = BigDecimal.ZERO;

                for (int index = 0; index < pMsg.poLineInfo.getValidCount(); index++) {

                    if (svcConfigMstrPk.equals(pMsg.poLineInfo.no(index).svcConfigMstrPk.getValue())) {

                        poDispQty = poDispQty.add(pMsg.poLineInfo.no(index).poDispQty.getValue());
                    }
                }

                if (poDispQty.compareTo(configCompCnt) != 0) {

                    msgMap.addXxMsgId(NPZC104001Constant.NPAM1437E);
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).xxMsgId, NPZC104001Constant.NPAM1437E);
                    return false;
                }

                // Check Machine Master
                BigDecimal svcMachMstrCnt = BigDecimal.ZERO;

                for (int index = 0; index < pMsg.svcMachMstrList.getValidCount(); index++) {

                    if (svcConfigMstrPk.equals(pMsg.svcMachMstrList.no(index).svcConfigMstrPk.getValue())) {

                        svcMachMstrCnt = svcMachMstrCnt.add(BigDecimal.ONE);
                    }
                }

                if (svcMachMstrCnt.compareTo(configCompCnt) != 0) {

                    msgMap.addXxMsgId(NPZC104001Constant.NPAM1437E);
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).xxMsgId, NPZC104001Constant.NPAM1437E);
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * checkSameVal
     * @param val1 String
     * @param val2 String
     * @return boolean
     */
    private static boolean checkSameVal(String val1, String val2) {

        if (!ZYPCommonFunc.hasValue(val1) && !ZYPCommonFunc.hasValue(val2)) {

            return true;

        } else if (!ZYPCommonFunc.hasValue(val1) && ZYPCommonFunc.hasValue(val2)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(val1) && !ZYPCommonFunc.hasValue(val2)) {

            return false;

        } else if (val1.equals(val2)) {

            return true;
        }

        return false;
    }

    /**
     * checkSameVal
     * @param val1 BigDecimal
     * @param val2 BigDecimal
     * @return boolean
     */
    private static boolean checkSameVal(BigDecimal val1, BigDecimal val2) {

        if (!ZYPCommonFunc.hasValue(val1) && !ZYPCommonFunc.hasValue(val2)) {

            return true;

        } else if (!ZYPCommonFunc.hasValue(val1) && ZYPCommonFunc.hasValue(val2)) {

            return false;

        } else if (ZYPCommonFunc.hasValue(val1) && !ZYPCommonFunc.hasValue(val2)) {

            return false;

        } else if (val1.compareTo(val2) == 0) {

            return true;
        }

        return false;
    }

    // CUSA Parts check
    protected static boolean checkCusaParts(NPZC104001PMsg pMsg) {
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String vndCd = pMsg.vndCd.getValue();
        int count = NPZC104001DBAccess.countPrntCmpyVnd(glblCmpyCd, vndCd);
        if (count > 0) { // CUSA Parts
            String cusaPartsChkFlg = ZYPCodeDataUtil.getVarCharConstValue(NPZC104001Constant.CUSA_PARTS_PO_CHK_RQST_FLG, glblCmpyCd);
            if (cusaPartsChkFlg != null && ZYPConstant.FLG_ON_Y.equals(cusaPartsChkFlg)) {
                return true;
            }
        }
        return false;
    }

    // create PO Header data
    protected static boolean createPoHead(NPZC104001PMsg pMsg, NPZC104001InternalInfoBean wkInternalInfo, ONBATCH_TYPE onBatchType, String poStsCd) {
        String poOrdNum = pMsg.poOrdNum.getValue();

        if (!hasValue(poOrdNum)) {
            poOrdNum = ZYPMaxTenDigitsNumbering.getUniqueID(NPZC104001Constant.PO_ORD_NUM);
        }
        ZYPEZDItemValueSetter.setValue(pMsg.poOrdNum, poOrdNum);

        NPZC104001DBAccess.insertPo(pMsg, poOrdNum, poStsCd, wkInternalInfo.getRwsAutoCratFlg());


        createPoMsg(pMsg);

        return true;
    }

    // create PO Message
    protected static boolean createPoMsg(NPZC104001PMsg pMsg) {
        // update PO_MSG
        if (pMsg.poInfo.getValidCount() > 0) {

            // Delete Internal PO Message
            for (int i = 0; i < pMsg.poInfo.getValidCount(); i++) {
                NPZC104001_poInfoPMsg poInfo = pMsg.poInfo.no(i);
                if (PO_MSG_TP.INTERNAL_PO_MESSAGE.equals(poInfo.poMsgTpCd.getValue())) {
                    List<PO_MSGTMsg> delList = NPXC001001PoMsg.getPoMsgForUpdateNoWait(pMsg.glblCmpyCd.getValue(), poInfo.poMsgTpCd.getValue(), pMsg.poOrdNum.getValue(), null);
                    if (delList != null && delList.size() > 0) {
                        S21ApiTBLAccessor.remove(delList.toArray(new PO_MSGTMsg[delList.size()]));
                    }
                    break;
                }
            }

            // Insert Internal PO Message
            int internalPoMsgSegId = 0;
            for (int i = 0; i < pMsg.poInfo.getValidCount(); i++) {
                NPZC104001_poInfoPMsg poInfo = pMsg.poInfo.no(i);
                if (PO_MSG_TP.INTERNAL_PO_MESSAGE.equals(poInfo.poMsgTpCd.getValue())) {
                    NPZC104001DBAccess.insertPoMsg(pMsg.glblCmpyCd.getValue() //
                                                 , pMsg.poOrdNum.getValue() //
                                                 , null //
                                                 , poInfo
                                                 , internalPoMsgSegId
                                                 , poInfo.xxDsMultMsgDplyTxt.getValue());
                    internalPoMsgSegId++;
                }
            }

            // Delete And Insert Other PO Message
            for (int i = 0; i < pMsg.poInfo.getValidCount(); i++) {
                NPZC104001_poInfoPMsg poInfo = pMsg.poInfo.no(i);
                if (!PO_MSG_TP.INTERNAL_PO_MESSAGE.equals(poInfo.poMsgTpCd.getValue())) {
                    List<PO_MSGTMsg> delList = NPXC001001PoMsg.getPoMsgForUpdateNoWait(pMsg.glblCmpyCd.getValue(), poInfo.poMsgTpCd.getValue(), pMsg.poOrdNum.getValue(), null);
                    if (delList != null && delList.size() > 0) {
                        S21ApiTBLAccessor.remove(delList.toArray(new PO_MSGTMsg[delList.size()]));
                    }
                    List<String> msgList = NPXC001001PoMsg.splitPoMsg(pMsg.poInfo.no(i).xxDsMultMsgDplyTxt.getValue());
                    for (int segId = 0; segId < msgList.size(); segId++) {
                        NPZC104001DBAccess.insertPoMsg(pMsg.glblCmpyCd.getValue() //
                                                     , pMsg.poOrdNum.getValue() //
                                                     , null //
                                                     , poInfo
                                                     , segId
                                                     , msgList.get(segId));
                    }
                }
            }
        }
        return true;
    }

    // create PO Detail data
    protected static boolean createPoDetail(NPZC104001PMsg pMsg, S21ApiMessageMap msgMap, List<NPZC104001_poLineInfoPMsg> poLineInfoList, String poStsCd) {
        for (int i = 0; i < poLineInfoList.size(); i++) {
            NPZC104001_poLineInfoPMsg poLineInfo = poLineInfoList.get(i);

            // START 2017/10/20 S.Katsuma QC#21206 ADD
//            NPZC104001DBAccess.insertPoDtl(pMsg, poLineInfo, poStsCd, poLineInfo.poQty.getValue());
            NPZC104001DBAccess.insertPoDtl(pMsg, poLineInfo, poStsCd, poLineInfo.poQty.getValue(), null);
            // END 2017/10/20 S.Katsuma QC#21206 ADD
        }
        return true;
    }

    // START 2017/10/20 S.Katsuma QC#21206 ADD - QC#22074 Update. 
    // create PO Detail data
    protected static boolean createPoDetail(NPZC104001PMsg pMsg, S21ApiMessageMap msgMap, List<NPZC104001_poLineInfoPMsg> poLineInfoList, String poStsCd, List<Map<String, Object>> poDtlList) {
        for (int i = 0; i < poLineInfoList.size(); i++) {
            NPZC104001_poLineInfoPMsg poLineInfo = poLineInfoList.get(i);
            String paramPoOrdDtlLineNum = poLineInfo.poOrdDtlLineNum.getValue();
            String _poStsCd = poStsCd;
            String poLineStsCd = null;

            for (int j = 0; j < poDtlList.size(); j++) {
                String poOrdDtlLineNum = (String) poDtlList.get(j).get(NPZC104001Constant.PO_ORD_DTL_LINE_NUM);

                if (paramPoOrdDtlLineNum.equals(poOrdDtlLineNum)) {
                    poLineStsCd = (String) poDtlList.get(j).get(NPZC104001Constant.PO_LINE_STS_CD);
                    String oldPoStsCd = (String) poDtlList.get(j).get(NPZC104001Constant.PO_STS_CD);
                    if ((!oldPoStsCd.equals(poStsCd) && NPZC104001Constant.EVENT_SAVE.equals(pMsg.eventId.getValue())) //
                            || PO_STS.CANCELLED.equals(oldPoStsCd)) {
                        _poStsCd = oldPoStsCd;
                    }
                    break;
                }
            }
            NPZC104001DBAccess.insertPoDtl(pMsg, poLineInfo, _poStsCd, poLineInfo.poQty.getValue(), poLineStsCd);
        }
        return true;
    }
    // END 2017/10/20 S.Katsuma QC#21206 ADD

    // create PO Detail data
    protected static boolean createPoHistory(NPZC104001PMsg pMsg, S21ApiMessageMap msgMap, List<NPZC104001_poLineInfoPMsg> poLineInfoList) {
        for (int i = 0; i < poLineInfoList.size(); i++) {
            NPZC104001_poLineInfoPMsg poLineInfo = poLineInfoList.get(i);

            // PO History
            if (NPZC104001Constant.EVENT_SUBMIT.equals(pMsg.eventId.getValue())) {

                String poApvlStsCd = PO_APVL_STS.AWAITING_APPROVAL;

                if (hasValue(pMsg.poApvlStsCd) && PO_APVL_STS.PRE_APPROVED.equals(pMsg.poApvlStsCd.getValue())) {

                    poApvlStsCd = PO_APVL_STS.PRE_APPROVED;

                    // QC# 8424
                    if (0 != NPXC001001CreatePOHistory.createPOHistory(pMsg.glblCmpyCd.getValue(), getPoApvlStsNm(pMsg.glblCmpyCd.getValue(), poApvlStsCd), pMsg.poOrdNum.getValue(), poLineInfo.poOrdDtlLineNum.getValue())) {
                        msgMap.addXxMsgId(NPZC104001Constant.NPAM1442E);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).xxMsgId, NPZC104001Constant.NPAM1442E);
                    }

                } else {

                    // QC# 8424
                    if (0 != NPXC001001CreatePOHistory.createPOHistory(pMsg.glblCmpyCd.getValue(), NPZC104001Constant.PO_SUBMITTED, pMsg.poOrdNum.getValue(), poLineInfo.poOrdDtlLineNum.getValue())) {
                        msgMap.addXxMsgId(NPZC104001Constant.NPAM1442E);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).xxMsgId, NPZC104001Constant.NPAM1442E);
                    }

                }

            }

            // Approval History
            if (hasValue(pMsg.poApvlStsCd) && PO_APVL_STS.PRE_APPROVED.equals(pMsg.poApvlStsCd.getValue())) {
                NPXC001001CreateApprovalHistoryBean createApprovalHistoryBean = new NPXC001001CreateApprovalHistoryBean();
                createApprovalHistoryBean.setGlblCmpyCd(pMsg.glblCmpyCd.getValue());
                createApprovalHistoryBean.setApvlHistSrcTpCd(APVL_HIST_SRC_TP.PO_ENTRY);
                createApprovalHistoryBean.setTrxRefNum(pMsg.poOrdNum.getValue());
                // QC#10455 Start 
                if (ZYPCommonFunc.hasValue(pMsg.poApvlTs)) {
                    createApprovalHistoryBean.setApvlHistInfoTs(pMsg.poApvlTs.getValue());
                }
                createApprovalHistoryBean.setApvlHistActTpCd(APVL_HIST_ACT_TP.PRE_APPROVED);
                // QC#10455 End.
                createApprovalHistoryBean.setApvlByPsnCd(pMsg.poApvlPsnCd.getValue());
                if (NPXC001001CreateApprovalHistory.ERROR.equals(NPXC001001CreateApprovalHistory.createApprovalHistory(createApprovalHistoryBean))) {
                    msgMap.addXxMsgId(NPZC104001Constant.NPAM1443E);
                }
            }
        }
        return true;
    }

    // create PO Serial data
    protected static boolean createPoSerial(NPZC104001PMsg pMsg,
                                            List<NPZC104001_poLineInfoPMsg> poLineInfoList,
                                            List<NPZC104001_serNumListPMsg> serNumList,
                                            List<NPZC104001InternalInfoBean> internalInfoList) {
        for (int i = 0; i < serNumList.size(); i++) {
            NPZC104001_serNumListPMsg serNumInfo = serNumList.get(i);
            String wkPoOrdDtlLineNum = serNumInfo.poOrdDtlLineNum.getValue();

            for (int j = 0; j < poLineInfoList.size(); j++) {
                if (wkPoOrdDtlLineNum.equals(poLineInfoList.get(j).poOrdDtlLineNum.getValue())) {
                    if (!hasValue(serNumInfo.serNum)) {
                        BigDecimal serNumPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PO_SER_NUM_SQ);
                        ZYPEZDItemValueSetter.setValue(serNumInfo.poSerNumPk, serNumPk);
                    }
                    String invtyLocNm = internalInfoList.get(j).getInvtyLocNm();
                    NPZC104001DBAccess.insertPoSerNum(pMsg, poLineInfoList.get(j), serNumList.get(i), invtyLocNm);
                }
            }
        }
        return true;
    }

    // create PO Acct data
    protected static boolean createPoAcct(NPZC104001PMsg pMsg, List<NPZC104001_poAcctInfoPMsg> poAcctInfoList) {
        for (NPZC104001_poAcctInfoPMsg poAcctInfo : poAcctInfoList) {
            NPZC104001DBAccess.insertPoAcct(pMsg, poAcctInfo);
        }
        return true;
    }

    // update CPO Order
    protected static boolean updateCpoOrder(NPZC104001PMsg pMsg, S21ApiMessageMap msgMap, List<NPZC104001_poLineInfoPMsg> poLineInfoList, ONBATCH_TYPE onBatchType, NPZC104001InternalInfoBean wkInternalInfo) {

        // Customer Dropship check
        String poQlfyCd = pMsg.poQlfyCd.getValue();
        String poCustDropQlfyCd = wkInternalInfo.getPoCustDropQlfyCd();
        if (poQlfyCd == null || !poQlfyCd.equals(poCustDropQlfyCd)) {
            return true;
        }
        // Dropship Flag check
        if (!hasValue(pMsg.vndDropShipFlg) || !ZYPConstant.FLG_ON_Y.equals(pMsg.vndDropShipFlg.getValue())) {
            return true;
        }
        for (int i = 0; i < poLineInfoList.size(); i++) {
            NPZC104001_poLineInfoPMsg poLineInfo = poLineInfoList.get(i);

            // Parent Item check
            if (PO_MDSE_CMPSN_TP.PARENT.equals(poLineInfo.poMdseCmpsnTpCd.getValue())) {
                continue;
            }

            // Do not Update Shipping Plan of Expense Item
            if (!PO_LINE_TP.GOODS.equals(poLineInfo.poLineTpCd.getValue())) {
                continue;
            }

            // Get Shipping Plan
            // START 2018/12/14 M.Naito [QC#29027,MOD]
//            SHPG_PLNTMsg shpgPlnTMsg = NPZC104001DBAccess.getShpgPlnTMsg(pMsg.glblCmpyCd.getValue(), poLineInfo.cpoOrdNum.getValue(), poLineInfo.cpoDtlLineNum.getValue(), poLineInfo.cpoDtlLineSubNum.getValue(), TRX_SRC_TP.WHOLE_SALES);
            SHPG_PLNTMsg shpgPlnTMsg = NPZC104001DBAccess.getShpgPlnTMsgForDropShip(pMsg.glblCmpyCd.getValue(), poLineInfo.cpoOrdNum.getValue(), poLineInfo.cpoDtlLineNum.getValue(), poLineInfo.cpoDtlLineSubNum.getValue(), TRX_SRC_TP.WHOLE_SALES);
            // END 2018/12/14 M.Naito [QC#29027,MOD]
            if (shpgPlnTMsg == null) {
                msgMap.addXxMsgId(NPZC104001Constant.NPAM1600E);
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).xxMsgId, NPZC104001Constant.NPAM1600E);
                return false;
            }
            // QC#26730
            if (SHPG_STS.P_OR_O_PRINTED.equals(shpgPlnTMsg.shpgStsCd.getValue()) || SHPG_STS.SHIPPED.equals(shpgPlnTMsg.shpgStsCd.getValue())) {
                continue;
            }

            // Set Shipping Plan Update Parameter
            List<NWZC003001PMsg> params = new ArrayList<NWZC003001PMsg>();
            NWZC003001PMsg shpgPlnPMsg = new NWZC003001PMsg();
            ZYPEZDItemValueSetter.setValue(shpgPlnPMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
            ZYPEZDItemValueSetter.setValue(shpgPlnPMsg.shpgModeCd, SHPG_PLN_UPD_MODE.PO_PRINTED);
            ZYPEZDItemValueSetter.setValue(shpgPlnPMsg.shpgPlnNum, shpgPlnTMsg.shpgPlnNum);
            ZYPEZDItemValueSetter.setValue(shpgPlnPMsg.poOrdNum, pMsg.poOrdNum);
            //QC#29173 add start
            ZYPEZDItemValueSetter.setValue(shpgPlnPMsg.invtyLocCd, pMsg.vndCd);
            //QC#29173 add end
            params.add(shpgPlnPMsg);

            // Call Shipping Plan Update API
            NWZC003001 api = new NWZC003001();
            api.execute(params, onBatchType);
            if (params.get(0).xxMsgIdList.getValidCount() > 0) {
                msgMap.addXxMsgId(NPZC104001Constant.NPZM0118E);
                ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).xxMsgId, NPZC104001Constant.NPZM0118E);
                return false;
            }

            // update CPO_DTL
            NPZC104001DBAccess.updateCpoDtl(pMsg, poLineInfo);

        }

        return true;
    }

    // create Transaction data
    protected static boolean createTranData(NPZC104001PMsg pMsg,
                                            S21ApiMessageMap msgMap,
                                            List<NPZC104001_poLineInfoPMsg> poLineInfoList,
                                            ONBATCH_TYPE onBatchType,
                                            String poStsCd,
                                            List<NPZC104001InternalInfoBean> wkInfoList,
                                            S21SsmBatchClient ssmBatchClient) {

        // START 2021/06/02 [QC#58872,ADD]
//        if (PO_STS.WAITING_FOR_APPROVAL.equals(poStsCd)) {
        if (PO_STS.WAITING_FOR_APPROVAL.equals(poStsCd) || PO_APVL_STS.PRE_APPROVED.equals(pMsg.poApvlStsCd.getValue())) {
        // END 2021/06/02 [QC#58872,ADD]
            // Customer Dropship check
            String poQlfyCd = pMsg.poQlfyCd.getValue();
            String poCustDropQlfyCd = wkInfoList.get(0).getPoCustDropQlfyCd();
            if (poQlfyCd != null && poQlfyCd.equals(poCustDropQlfyCd)) {
                return true;
            }
            if (hasValue(pMsg.vndDropShipFlg) && ZYPConstant.FLG_ON_Y.equals(pMsg.vndDropShipFlg.getValue())) {
                return true;
            }

            // PO Type Check
            if (!DS_PO_TP.BUYBACK_PO.equals(pMsg.dsPoTpCd.getValue())) {
                return true;
            }

            for (int i = 0; i < poLineInfoList.size(); i++) {
                NPZC104001_poLineInfoPMsg poLineInfo = poLineInfoList.get(i);
                // Intangible Item check
                if (wkInfoList.get(i).getInvtyCtrlFlg() == null) {
                    MDSETMsg mdseTmsg = NPZC104001DBAccess.findMdse(pMsg.glblCmpyCd.getValue(), poLineInfo.mdseCd.getValue());
                    if (mdseTmsg == null || !hasValue(mdseTmsg.invtyCtrlFlg) || ZYPConstant.FLG_ON_Y.equals(mdseTmsg.invtyCtrlFlg.getValue())) {
                        continue;
                    }
                } else if (ZYPConstant.FLG_ON_Y.equals(wkInfoList.get(i).getInvtyCtrlFlg())) {
                    continue;
                }
                // Parent Item check
                if (PO_MDSE_CMPSN_TP.PARENT.equals(poLineInfo.poMdseCmpsnTpCd.getValue())) {
                    continue;
                }
                // Goods Line check
                if (!PO_LINE_TP.GOODS.equals(poLineInfo.poLineTpCd.getValue())) {
                    continue;
                }
                // RWS Request QTY check
                BigDecimal rwsQty = NPZC104001DBAccess.getRwsQty(pMsg, poLineInfo.poOrdDtlLineNum.getValue(), ssmBatchClient);
                if (!hasValue(rwsQty)) {
                    continue;
                }
                // setInstlBaseCtrlFlg cd check
                if (wkInfoList.get(i).getInstlBaseCtrlFlg() == null || !ZYPConstant.FLG_ON_Y.equals(wkInfoList.get(i).getInstlBaseCtrlFlg())) {
                    continue;
                }
            }

            // Machine Master Allocation
            for (int i = 0; i < pMsg.svcMachMstrList.getValidCount(); i++) {

                NSZC001001PMsg svcMachMstrApiPMsg = new NSZC001001PMsg();
                ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
                ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.slsDt, pMsg.procDt);
                ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.svcMachMstrPk, pMsg.svcMachMstrList.no(i).svcMachMstrPk.getValue());
                ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.xxModeCd, ProcessMode.ALLOCATION_ON.code);
                ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.trxHdrNum, pMsg.poOrdNum);
                ZYPEZDItemValueSetter.setValue(svcMachMstrApiPMsg.trxLineNum, pMsg.svcMachMstrList.no(i).poOrdDtlLineNum.getValue());

                NSZC001001 svcMachMstrApi = new NSZC001001();
                svcMachMstrApi.execute(svcMachMstrApiPMsg, onBatchType);

                List<String> msgIdList = S21ApiUtil.getXxMsgIdList(svcMachMstrApiPMsg);

                if (!msgIdList.isEmpty()) {

                    for (String msgId : msgIdList) {

                        if (ZYPCommonFunc.hasValue(msgId)) {

                            msgMap.addXxMsgId(msgId);

                            if (msgId.endsWith("E")) {

                                for (int j = 0; j < poLineInfoList.size(); j++) {

                                    if (poLineInfoList.get(j).poOrdDtlLineNum.getValue().equals(pMsg.svcMachMstrList.no(i).poOrdDtlLineNum.getValue())) {

                                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(j).xxMsgId, msgId);
                                    }
                                }

                                return false;
                            }
                        }
                    }
                }
            }
        }

        // create Inbound Visibility
        if (PO_STS.VALIDATED.equals(poStsCd)) {

            // POYO Update API param Head set
            NPZC109001PMsg poyoPmsg = new NPZC109001PMsg();
            ZYPEZDItemValueSetter.setValue(poyoPmsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(poyoPmsg.xxModeCd, NPZC109001Constant.POYO_INSERT_MODE);

            for (int i = 0; i < poLineInfoList.size(); i++) {
                NPZC104001_poLineInfoPMsg poLineInfo = poLineInfoList.get(i);
                // POYO Update API param Detail set
                ZYPEZDItemValueSetter.setValue(poyoPmsg.detailList.no(i).poOrdNum, pMsg.poOrdNum);
                ZYPEZDItemValueSetter.setValue(poyoPmsg.detailList.no(i).poOrdDtlLineNum, poLineInfo.poOrdDtlLineNum);
                ZYPEZDItemValueSetter.setValue(poyoPmsg.detailList.no(i).xxQty10Num, poLineInfo.poQty);
                poyoPmsg.detailList.setValidCount(i + 1);
            }

            NPZC109001 dPZC109001 = new NPZC109001();
            dPZC109001.execute(poyoPmsg, onBatchType);
            if (poyoPmsg.xxMsgIdList.getValidCount() > 0) {
                msgMap.addXxMsgId(poyoPmsg.xxMsgIdList.no(0).xxMsgId.getValue());
                return false;
            }
        }

        return true;
    }

    // create RWS
    protected static boolean createRws(NPZC104001PMsg pMsg,
                                       S21ApiMessageMap msgMap,
                                       List<NPZC104001_poLineInfoPMsg> poLineInfoList,
                                       String poStsCd,
                                       String vndCd,
                                       String whCd,
                                       List<NPZC104001InternalInfoBean> wkInfoList,
                                       S21SsmBatchClient ssmBatchClient,
                                       ONBATCH_TYPE onBatchType,
                                       boolean addLineFlg // QC#55023 2019/12/10 ADD
                                       ) {

        // QC#55023 2019/12/10 MOD START
        if (!addLineFlg && !PO_STS.VALIDATED.equals(poStsCd)) {
        //if (!PO_STS.VALIDATED.equals(poStsCd)) {
        // QC#55023 2019/12/10 MOD END
            return true;
        }

        // Customer Dropship check
        String poQlfyCd = pMsg.poQlfyCd.getValue();
        String poCustDropQlfyCd = wkInfoList.get(0).getPoCustDropQlfyCd();
        if (poQlfyCd != null && poQlfyCd.equals(poCustDropQlfyCd)) {
            return true;
        }

        if (DS_PO_TP.SUBCONTRACT_PO.equals(pMsg.dsPoTpCd.getValue())) {

            if (!createRwsForSubContract(pMsg, msgMap, poLineInfoList, vndCd, whCd, wkInfoList, ssmBatchClient, onBatchType)) {

                return false;
            }
        // 2016/10/14 QC#6159 BUYBACK_PO Add Start.
        } else if (DS_PO_TP.BUYBACK_PO.equals(pMsg.dsPoTpCd.getValue())) {

            if (!createRwsForBuyback(pMsg, msgMap, poLineInfoList, vndCd, whCd, wkInfoList, ssmBatchClient, onBatchType)) {

                return false;
            }
        // 2016/10/14 QC#6159 BUYBACK_PO Add End.
        } else {

            if (!createRwsForOther(pMsg, msgMap, poLineInfoList, vndCd, whCd, wkInfoList, ssmBatchClient, onBatchType)) {

                return false;
            }
        }

        return true;
    }

    /**
     * createRwsForOther
     * @param pMsg NPZC104001PMsg
     * @param msgMap S21ApiMessageMap
     * @param poLineInfoList List<NPZC104001_poLineInfoPMsg>
     * @param vndCd String
     * @param whCd String
     * @param wkInfoList List<NPZC104001InternalInfoBean>
     * @param ssmBatchClient S21SsmBatchClient
     * @param onBatchType ONBATCH_TYPE
     * @return boolean
     */
    private static boolean createRwsForOther(NPZC104001PMsg pMsg, S21ApiMessageMap msgMap, List<NPZC104001_poLineInfoPMsg> poLineInfoList, String vndCd, String whCd, List<NPZC104001InternalInfoBean> wkInfoList,
            S21SsmBatchClient ssmBatchClient, ONBATCH_TYPE onBatchType) {

        // set PO Receiving Api Param
        NLZC201001PMsg poRcvPmsg = new NLZC201001PMsg();
        ZYPEZDItemValueSetter.setValue(poRcvPmsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(poRcvPmsg.sysSrcCd, SYS_SRC.S21_PROCUREMENT);
        if (hasValue(wkInfoList.get(0).getSceOrdTpCd())) {
            ZYPEZDItemValueSetter.setValue(poRcvPmsg.sceOrdTpCd, wkInfoList.get(0).getSceOrdTpCd());
        } else {
            // 2016/11/09 R.Shimamoto QC#15121 Mod Start.
            ZYPEZDItemValueSetter.setValue(poRcvPmsg.sceOrdTpCd, NLXSceConst.SCE_ORD_TP_CD_DP);
            // 2016/11/09 R.Shimamoto QC#15121 Mod End.
        }
        ZYPEZDItemValueSetter.setValue(poRcvPmsg.poRcvFromLocTpCd, LOC_TP.VENDOR);
        ZYPEZDItemValueSetter.setValue(poRcvPmsg.poRcvFromLocCd, vndCd);
        ZYPEZDItemValueSetter.setValue(poRcvPmsg.whCd, whCd);
        ZYPEZDItemValueSetter.setValue(poRcvPmsg.poRcvDrctShipTpCd, DRCT_SHIP_TP.N_OR_A);
        ZYPEZDItemValueSetter.setValue(poRcvPmsg.poRcvTrxHdrNum, pMsg.poOrdNum);
        if (NLXSceConst.SCE_ORD_TP_CD_DP.equals(poRcvPmsg.sceOrdTpCd.getValue())
         || NLXSceConst.SCE_ORD_TP_CD_DG.equals(poRcvPmsg.sceOrdTpCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(poRcvPmsg.domInvNum, pMsg.poOrdNum);
        }

        String rwsRefNum = getRwsRefNum(pMsg, whCd, msgMap, ssmBatchClient);

        if (rwsRefNum == null) {

            return false;
        }

        ZYPEZDItemValueSetter.setValue(poRcvPmsg.rwsRefNum, rwsRefNum);
        ZYPEZDItemValueSetter.setValue(poRcvPmsg.poRcvEtaDt, pMsg.rqstRcvDt);

        List<String> poRcvMsgList = getMsgList(pMsg, PO_MSG_TP.RECEIVER_NOTE);
        int poRcvMsgIdx = 0;
        for (; poRcvMsgIdx < poRcvMsgList.size(); poRcvMsgIdx++) {
            ZYPEZDItemValueSetter.setValue(poRcvPmsg.MsgInfoLIst.no(poRcvMsgIdx).poRcvMsgTxt, poRcvMsgList.get(poRcvMsgIdx));
        }
        poRcvPmsg.MsgInfoLIst.setValidCount(poRcvMsgIdx);

        int ordInfoListIndex = 0;

        for (int i = 0; i < poLineInfoList.size(); i++) {

            NPZC104001_poLineInfoPMsg poLineInfo = poLineInfoList.get(i);
            // Intangible Item check
            MDSETMsg mdseTmsg = NPZC104001DBAccess.findMdse(pMsg.glblCmpyCd.getValue(), poLineInfo.mdseCd.getValue());
            if (mdseTmsg == null || !hasValue(mdseTmsg.invtyCtrlFlg) || ZYPConstant.FLG_OFF_N.equals(mdseTmsg.invtyCtrlFlg.getValue())) {
                continue;
            }
            // Parent Item check
            if (PO_MDSE_CMPSN_TP.PARENT.equals(poLineInfo.poMdseCmpsnTpCd.getValue())) {
                continue;
            }
            // Goods Line check
//            if (!PO_LINE_TP.GOODS.equals(poLineInfo.poLineTpCd.getValue())) {
//                continue;
//            }
            PO_LINE_TPTMsg poLineTpTMsg = NPZC104001DBAccess.getPoLineTp(pMsg.glblCmpyCd.getValue(), poLineInfo.poLineTpCd.getValue());
            if (poLineTpTMsg == null || !ZYPConstant.FLG_ON_Y.equals(poLineTpTMsg.rwsAutoCratFlg.getValue())) {
                continue;
            }

            // RWS Request QTY check
            BigDecimal rwsQty = NPZC104001DBAccess.getRwsQty(pMsg, poLineInfo.poOrdDtlLineNum.getValue(), ssmBatchClient);
            if (!hasValue(rwsQty)) {
                continue;
            }
            // set PO Receiving Api ORD_INFO Param
            ZYPEZDItemValueSetter.setValue(poRcvPmsg.OrdInfoLIst.no(ordInfoListIndex).mdseCd, poLineInfo.mdseCd);
            if (hasValue(poLineInfo.toStkStsCd)) {
                ZYPEZDItemValueSetter.setValue(poRcvPmsg.OrdInfoLIst.no(ordInfoListIndex).stkStsCd, poLineInfo.toStkStsCd);
            } else {
                ZYPEZDItemValueSetter.setValue(poRcvPmsg.OrdInfoLIst.no(ordInfoListIndex).stkStsCd, STK_STS.GOOD);
            }
            ZYPEZDItemValueSetter.setValue(poRcvPmsg.OrdInfoLIst.no(ordInfoListIndex).poRcvQty, rwsQty);
            if (NLXSceConst.SCE_ORD_TP_CD_DP.equals(poRcvPmsg.sceOrdTpCd.getValue())
             || NLXSceConst.SCE_ORD_TP_CD_DN.equals(poRcvPmsg.sceOrdTpCd.getValue())
             || NLXSceConst.SCE_ORD_TP_CD_DG.equals(poRcvPmsg.sceOrdTpCd.getValue())
             || NLXSceConst.SCE_ORD_TP_CD_RP.equals(poRcvPmsg.sceOrdTpCd.getValue())
             || NLXSceConst.SCE_ORD_TP_CD_BB.equals(poRcvPmsg.sceOrdTpCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(poRcvPmsg.OrdInfoLIst.no(ordInfoListIndex).poRcvTrxLineNum, poLineInfo.poOrdDtlLineNum);
            }
            ZYPEZDItemValueSetter.setValue(poRcvPmsg.OrdInfoLIst.no(ordInfoListIndex).carrCd, pMsg.carrCd);
            ZYPEZDItemValueSetter.setValue(poRcvPmsg.OrdInfoLIst.no(ordInfoListIndex).proNum, poLineInfo.proNum);
            ZYPEZDItemValueSetter.setValue(poRcvPmsg.OrdInfoLIst.no(ordInfoListIndex).invtyLocCd, poLineInfo.invtyLocCd);
            ZYPEZDItemValueSetter.setValue(poRcvPmsg.OrdInfoLIst.no(ordInfoListIndex).shipFromInvtyLocCd, vndCd);
            ZYPEZDItemValueSetter.setValue(poRcvPmsg.OrdInfoLIst.no(ordInfoListIndex).prchReqNum, poLineInfo.prchReqNum);
            ZYPEZDItemValueSetter.setValue(poRcvPmsg.OrdInfoLIst.no(ordInfoListIndex).prchReqLineNum, poLineInfo.prchReqLineNum);
            ZYPEZDItemValueSetter.setValue(poRcvPmsg.OrdInfoLIst.no(ordInfoListIndex).prchReqLineSubNum, poLineInfo.prchReqLineSubNum);

            ordInfoListIndex++;
        } // end loop

        // if detail data not exist then Skip
        if (ordInfoListIndex == 0) {
            return true;
        }

        poRcvPmsg.OrdInfoLIst.setValidCount(ordInfoListIndex);

        // call PO Receiving API
        NLZC201001 poRcvApi = new NLZC201001();
        poRcvApi.execute(poRcvPmsg, onBatchType);

        if (poRcvPmsg.xxMsgIdList.getValidCount() > 0) {
            msgMap.addXxMsgId(NPZC104001Constant.NPZM0120E);
            return false;
        }

        // RWS API
        NLZC200001PMsg rwsPmsg = new NLZC200001PMsg();
        ZYPEZDItemValueSetter.setValue(rwsPmsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rwsPmsg.sysSrcCd, SYS_SRC.S21_PROCUREMENT);
        ZYPEZDItemValueSetter.setValue(rwsPmsg.inbdSrcTpCd, INBD_SRC_TP.PO_RECEIVING);
        ZYPEZDItemValueSetter.setValue(rwsPmsg.poRcvNum, poRcvPmsg.poRcvNum);
        NLZC200001 rwsApi = new NLZC200001();
        rwsApi.execute(rwsPmsg, onBatchType);
        if (rwsPmsg.xxMsgIdList.getValidCount() > 0) {
            msgMap.addXxMsgId(NPZC104001Constant.NPZM0121E);
            return false;
        }

        for (int j = 0; j < rwsPmsg.RWSNumList.length(); j++) {
            String rwsNum = rwsPmsg.RWSNumList.no(j).rwsNum.getValue();

            // get Serial List
            List<Map<String, Object>> serialList = NPZC104001DBAccess.getSerialList(pMsg.glblCmpyCd.getValue(), rwsNum, ssmBatchClient);
            if (serialList == null || serialList.size() == 0) {
                return true; // skip under process
            }

            // RWS Serial API
            NLZC304001PMsg rwsSerPmsg = new NLZC304001PMsg();
            ZYPEZDItemValueSetter.setValue(rwsSerPmsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(rwsSerPmsg.rwsNum, rwsNum);
            int k = 0;
            for (; k < serialList.size(); k++) {
                String serRwsDtlLineNum = (String) serialList.get(k).get(NPZC104001Constant.RWS_DTL_LINE_NUM);
                String serSerNum = (String) serialList.get(k).get(NPZC104001Constant.SER_NUM);
                String serMdseCd = (String) serialList.get(k).get(NPZC104001Constant.MDSE_CD);
                ZYPEZDItemValueSetter.setValue(rwsSerPmsg.SerialList.no(k).rwsDtlLineNum, serRwsDtlLineNum);
                ZYPEZDItemValueSetter.setValue(rwsSerPmsg.SerialList.no(k).serNum, serSerNum);
                ZYPEZDItemValueSetter.setValue(rwsSerPmsg.SerialList.no(k).mdseCd, serMdseCd);
                ZYPEZDItemValueSetter.setValue(rwsSerPmsg.SerialList.no(k).putAwayChkStsCd, PUT_AWAY_CHK_STS.NOT_PROCESSED);
                ZYPEZDItemValueSetter.setValue(rwsSerPmsg.SerialList.no(k).serNumSendFlg, ZYPConstant.FLG_OFF_N);
            }
            rwsSerPmsg.SerialList.setValidCount(k);
            NLZC304001 rwsSerApi = new NLZC304001();
            rwsSerApi.execute(rwsSerPmsg, onBatchType);
            if (rwsSerPmsg.xxMsgIdList.getValidCount() > 0) {
                msgMap.addXxMsgId(NPZC104001Constant.NPAM1445E);
                return false;
            }
        }

        return true;
    }

    /**
     * createRwsForSubContract
     * @param pMsg NPZC104001PMsg
     * @param msgMap S21ApiMessageMap
     * @param poLineInfoList List<NPZC104001_poLineInfoPMsg>
     * @param vndCd String
     * @param whCd String
     * @param wkInfoList List<NPZC104001InternalInfoBean>
     * @param ssmBatchClient S21SsmBatchClient
     * @param onBatchType ONBATCH_TYPE
     * @return boolean
     */
    private static boolean createRwsForSubContract(NPZC104001PMsg pMsg, S21ApiMessageMap msgMap, List<NPZC104001_poLineInfoPMsg> poLineInfoList, String vndCd, String whCd, List<NPZC104001InternalInfoBean> wkInfoList,
            S21SsmBatchClient ssmBatchClient, ONBATCH_TYPE onBatchType) {

        // QC#22481
        // set PO Receiving Api Param
        NLZC201001PMsg poRcvPmsg = new NLZC201001PMsg();
        ZYPEZDItemValueSetter.setValue(poRcvPmsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(poRcvPmsg.sysSrcCd, SYS_SRC.S21_PROCUREMENT);
        ZYPEZDItemValueSetter.setValue(poRcvPmsg.sceOrdTpCd, wkInfoList.get(0).getSceOrdTpCd());
        ZYPEZDItemValueSetter.setValue(poRcvPmsg.poRcvFromLocTpCd, LOC_TP.VENDOR);
        ZYPEZDItemValueSetter.setValue(poRcvPmsg.poRcvFromLocCd, vndCd);
        ZYPEZDItemValueSetter.setValue(poRcvPmsg.whCd, whCd);
        ZYPEZDItemValueSetter.setValue(poRcvPmsg.poRcvDrctShipTpCd, DRCT_SHIP_TP.N_OR_A);
        ZYPEZDItemValueSetter.setValue(poRcvPmsg.poRcvTrxHdrNum, pMsg.poOrdNum);

        // get RWS_REF_NUM
        String rwsRefNum = getRwsRefNum(pMsg, whCd, msgMap, ssmBatchClient);

        if (rwsRefNum == null) {

            return false;
        }

        ZYPEZDItemValueSetter.setValue(poRcvPmsg.rwsRefNum, rwsRefNum);
        ZYPEZDItemValueSetter.setValue(poRcvPmsg.poRcvEtaDt, pMsg.rqstRcvDt);

        // set PO Receiving Api MSG_INFO Param
        List<String> poRcvMsgList = getMsgList(pMsg, PO_MSG_TP.RECEIVER_NOTE);
        int poRcvMsgIdx = 0;
        for (; poRcvMsgIdx < poRcvMsgList.size(); poRcvMsgIdx++) {
            ZYPEZDItemValueSetter.setValue(poRcvPmsg.MsgInfoLIst.no(poRcvMsgIdx).poRcvMsgTxt, poRcvMsgList.get(poRcvMsgIdx));
        }
        poRcvPmsg.MsgInfoLIst.setValidCount(poRcvMsgIdx);

        int ordInfoListIndex = 0;

        for (int i = 0; i < poLineInfoList.size(); i++) {

            NPZC104001_poLineInfoPMsg poLineInfo = poLineInfoList.get(i);

            // Intangible Item check
            MDSETMsg mdseTmsg = NPZC104001DBAccess.findMdse(pMsg.glblCmpyCd.getValue(), poLineInfo.mdseCd.getValue());
            if (mdseTmsg == null || !hasValue(mdseTmsg.invtyCtrlFlg) || ZYPConstant.FLG_OFF_N.equals(mdseTmsg.invtyCtrlFlg.getValue())) {
                continue;
            }
            // Parent Item check
            if (PO_MDSE_CMPSN_TP.PARENT.equals(poLineInfo.poMdseCmpsnTpCd.getValue())) {
                continue;
            }

            PO_LINE_TPTMsg poLineTpTMsg = NPZC104001DBAccess.getPoLineTp(pMsg.glblCmpyCd.getValue(), poLineInfo.poLineTpCd.getValue());
            if (poLineTpTMsg == null || !ZYPConstant.FLG_ON_Y.equals(poLineTpTMsg.rwsAutoCratFlg.getValue())) {
                continue;
            }

            // RWS Request QTY check
            BigDecimal rwsQty = NPZC104001DBAccess.getRwsQty(pMsg, poLineInfo.poOrdDtlLineNum.getValue(), ssmBatchClient);
            if (!hasValue(rwsQty)) {
                continue;
            }

            // QC#22481
            // set PO Receiving Api ORD_INFO Param
            ZYPEZDItemValueSetter.setValue(poRcvPmsg.OrdInfoLIst.no(i).mdseCd, poLineInfo.mdseCd);
            if (hasValue(poLineInfo.toStkStsCd)) {
                ZYPEZDItemValueSetter.setValue(poRcvPmsg.OrdInfoLIst.no(i).stkStsCd, poLineInfo.toStkStsCd);
            } else {
                ZYPEZDItemValueSetter.setValue(poRcvPmsg.OrdInfoLIst.no(i).stkStsCd, STK_STS.GOOD);
            }
            ZYPEZDItemValueSetter.setValue(poRcvPmsg.OrdInfoLIst.no(i).poRcvQty, rwsQty);
            if (NLXSceConst.SCE_ORD_TP_CD_DP.equals(poRcvPmsg.sceOrdTpCd.getValue()) || NLXSceConst.SCE_ORD_TP_CD_DN.equals(poRcvPmsg.sceOrdTpCd.getValue()) || NLXSceConst.SCE_ORD_TP_CD_DG.equals(poRcvPmsg.sceOrdTpCd.getValue())
                    || NLXSceConst.SCE_ORD_TP_CD_RP.equals(poRcvPmsg.sceOrdTpCd.getValue()) || NLXSceConst.SCE_ORD_TP_CD_BB.equals(poRcvPmsg.sceOrdTpCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(poRcvPmsg.OrdInfoLIst.no(i).poRcvTrxLineNum, poLineInfo.poOrdDtlLineNum);
            }
            ZYPEZDItemValueSetter.setValue(poRcvPmsg.OrdInfoLIst.no(i).carrCd, pMsg.carrCd);
            ZYPEZDItemValueSetter.setValue(poRcvPmsg.OrdInfoLIst.no(i).proNum, poLineInfo.proNum);
            ZYPEZDItemValueSetter.setValue(poRcvPmsg.OrdInfoLIst.no(i).invtyLocCd, poLineInfo.invtyLocCd);
            ZYPEZDItemValueSetter.setValue(poRcvPmsg.OrdInfoLIst.no(i).shipFromInvtyLocCd, vndCd);
            ZYPEZDItemValueSetter.setValue(poRcvPmsg.OrdInfoLIst.no(i).prchReqNum, poLineInfo.prchReqNum);
            ZYPEZDItemValueSetter.setValue(poRcvPmsg.OrdInfoLIst.no(i).prchReqLineNum, poLineInfo.prchReqLineNum);
            ZYPEZDItemValueSetter.setValue(poRcvPmsg.OrdInfoLIst.no(i).prchReqLineSubNum, poLineInfo.prchReqLineSubNum);

            ordInfoListIndex++;
        }

        if (ordInfoListIndex == 0) {
            return true;
        }
        poRcvPmsg.OrdInfoLIst.setValidCount(ordInfoListIndex);

        // call PO Receiving API
        NLZC201001 poRcvApi = new NLZC201001();
        poRcvApi.execute(poRcvPmsg, onBatchType);

        if (poRcvPmsg.xxMsgIdList.getValidCount() > 0) {
            msgMap.addXxMsgId(NPZC104001Constant.NPZM0120E);
            return false;
        }

        // RWS API
        NLZC200001PMsg rwsPmsg = new NLZC200001PMsg();
        ZYPEZDItemValueSetter.setValue(rwsPmsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rwsPmsg.sysSrcCd, SYS_SRC.S21_PROCUREMENT);
        ZYPEZDItemValueSetter.setValue(rwsPmsg.inbdSrcTpCd, INBD_SRC_TP.PO_RECEIVING);
        ZYPEZDItemValueSetter.setValue(rwsPmsg.poRcvNum, poRcvPmsg.poRcvNum);
        NLZC200001 rwsApi = new NLZC200001();
        rwsApi.execute(rwsPmsg, onBatchType);
        if (rwsPmsg.xxMsgIdList.getValidCount() > 0) {
            msgMap.addXxMsgId(NPZC104001Constant.NPZM0121E);
            return false;
        }

        for (int j = 0; j < rwsPmsg.RWSNumList.length(); j++) {
            String rwsNum = rwsPmsg.RWSNumList.no(j).rwsNum.getValue();

            // get Serial List
            List<Map<String, Object>> serialList = NPZC104001DBAccess.getSerialList(pMsg.glblCmpyCd.getValue(), rwsNum, ssmBatchClient);
            if (serialList == null || serialList.size() == 0) {
                return true; // skip under process
            }

            // RWS Serial API
            NLZC304001PMsg rwsSerPmsg = new NLZC304001PMsg();
            ZYPEZDItemValueSetter.setValue(rwsSerPmsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(rwsSerPmsg.rwsNum, rwsNum);
            int k = 0;
            for (; k < serialList.size(); k++) {
                String serRwsDtlLineNum = (String) serialList.get(k).get(NPZC104001Constant.RWS_DTL_LINE_NUM);
                String serSerNum = (String) serialList.get(k).get(NPZC104001Constant.SER_NUM);
                String serMdseCd = (String) serialList.get(k).get(NPZC104001Constant.MDSE_CD);
                ZYPEZDItemValueSetter.setValue(rwsSerPmsg.SerialList.no(k).rwsDtlLineNum, serRwsDtlLineNum);
                ZYPEZDItemValueSetter.setValue(rwsSerPmsg.SerialList.no(k).serNum, serSerNum);
                ZYPEZDItemValueSetter.setValue(rwsSerPmsg.SerialList.no(k).mdseCd, serMdseCd);
                ZYPEZDItemValueSetter.setValue(rwsSerPmsg.SerialList.no(k).putAwayChkStsCd, PUT_AWAY_CHK_STS.NOT_PROCESSED);
                ZYPEZDItemValueSetter.setValue(rwsSerPmsg.SerialList.no(k).serNumSendFlg, ZYPConstant.FLG_OFF_N);
            }
            rwsSerPmsg.SerialList.setValidCount(k);
            NLZC304001 rwsSerApi = new NLZC304001();
            rwsSerApi.execute(rwsSerPmsg, onBatchType);
            if (rwsSerPmsg.xxMsgIdList.getValidCount() > 0) {
                msgMap.addXxMsgId(NPZC104001Constant.NPAM1445E);
                return false;
            }
        }

        return true;
    }

    /**
     * getRwsRefNum
     * @param pMsg NPZC104001PMsg
     * @param destRtlWhCd String
     * @param msgMap S21ApiMessageMap
     * @param ssmBatchClient S21SsmBatchClient
     * @return String
     */
    protected static String getRwsRefNum(NPZC104001PMsg pMsg, String destRtlWhCd, S21ApiMessageMap msgMap, S21SsmBatchClient ssmBatchClient) {

        String rwsRefNum = null;

        if (ZYPCommonFunc.hasValue(pMsg.shipFromSoNumListTxt)) {

            rwsRefNum = pMsg.shipFromSoNumListTxt.getValue();

        } else {

            rwsRefNum = pMsg.poOrdNum.getValue();
        }

        if (NPZC104001DBAccess.getRwsRefNum(pMsg.glblCmpyCd.getValue(), destRtlWhCd, rwsRefNum) != null) {

            String maxRwsRefNum = NPZC104001DBAccess.getMaxRwsRefNum(pMsg.glblCmpyCd.getValue(), destRtlWhCd, pMsg.poOrdNum.getValue(), ssmBatchClient);

            if (maxRwsRefNum != null) {

                int index = maxRwsRefNum.lastIndexOf("-");
                String strRevision = String.valueOf(Integer.parseInt(maxRwsRefNum.substring(index + 1)) + 1);

                if (rwsRefNum.length() < NPZC104001Constant.LENGTH_13) {

                    rwsRefNum = ZYPCommonFunc.concatString(rwsRefNum, "-", ZYPCommonFunc.leftPad(strRevision, 2, "0"));

                } else {

                    rwsRefNum = ZYPCommonFunc.concatString(rwsRefNum, "-", strRevision);
                }

            } else {

                if (rwsRefNum.length() < NPZC104001Constant.LENGTH_13) {

                    rwsRefNum = ZYPCommonFunc.concatString(rwsRefNum, "-", "01");

                } else {

                    rwsRefNum = ZYPCommonFunc.concatString(rwsRefNum, "-", "1");
                }
            }

            if (rwsRefNum.length() > NPZC104001Constant.LENGTH_15) {

                msgMap.addXxMsgId(NPZC104001Constant.NPAM1601E);
                return null;
            }
        }

        return rwsRefNum;
    }

    // send WF
    protected static boolean sendWf(NPZC104001PMsg pMsg,
                                    S21ApiMessageMap msgMap,
                                    List<NPZC104001_poLineInfoPMsg> poLineInfoList,
                                    String poStsCd,
                                    ONBATCH_TYPE onBatchType,
                                    String poWfRqstFlg) {

        // CREATE MODE
        if (NPZC104001Constant.MODE_CREATE.equals(pMsg.xxModeCd.getValue())) {

            if (!PO_STS.WAITING_FOR_APPROVAL.equals(poStsCd) || !ZYPConstant.FLG_ON_Y.equals(pMsg.wfFlg.getValue())) {
                return true;
            }

            for (int i = 0; i < poLineInfoList.size(); i++) {

                NPZC104001_poLineInfoPMsg poLineInfo = poLineInfoList.get(i);

                String poApvlStsCd = PO_APVL_STS.AWAITING_APPROVAL;

                if (0 != NPXC001001CreatePOHistory.createPOHistory(pMsg.glblCmpyCd.getValue(), getPoApvlStsNm(pMsg.glblCmpyCd.getValue(), poApvlStsCd), pMsg.poOrdNum.getValue(), poLineInfo.poOrdDtlLineNum.getValue())) {
                    msgMap.addXxMsgId(NPZC104001Constant.NPAM1442E);
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).xxMsgId, NPZC104001Constant.NPAM1442E);
                }
            }

            NPZC130001PMsg wfPmsg = new NPZC130001PMsg();
            ZYPEZDItemValueSetter.setValue(wfPmsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(wfPmsg.slsDt, pMsg.procDt);
            ZYPEZDItemValueSetter.setValue(wfPmsg.xxModeCd, NPZC130001Constant.WFMD_PO);
            ZYPEZDItemValueSetter.setValue(wfPmsg.xxProcTpCd, NPZC104001Constant.NEW_ORDER);
            ZYPEZDItemValueSetter.setValue(wfPmsg.trxRefNum, pMsg.poOrdNum);
            ZYPEZDItemValueSetter.setValue(wfPmsg.apvlHistSrcTpCd, APVL_HIST_SRC_TP.PO_ENTRY);
            NPZC130001 wfApi = new NPZC130001();
            wfApi.execute(wfPmsg, onBatchType);
            // if (wfPmsg.xxMsgIdList.getValidCount() > 0) {
            // msgMap.addXxMsgId(wfPmsg.xxMsgIdList.no(0).xxMsgId.getValue());
            // return false;
            // }
            // QC# 15933
            if (wfPmsg.xxMsgIdList.getValidCount() > 0) {
                msgMap.addXxMsgId(wfPmsg.xxMsgIdList.no(0).xxMsgId.getValue());
                if (wfPmsg.xxMsgIdList.no(0).xxMsgId.getValue().endsWith(NPZC104001Constant.MESSAGE_KIND_ERROR)) {
                    return false;
                }
            }

            // UPDATE MODE Modify QC#24780
        } else if (NPZC104001Constant.MODE_UPDATE.equals(pMsg.xxModeCd.getValue()) || NPZC104001Constant.MODE_ASN.equals(pMsg.xxModeCd.getValue())) {
            if (PO_STS.WAITING_FOR_APPROVAL.equals(poStsCd)) {

                for (int i = 0; i < poLineInfoList.size(); i++) {

                    NPZC104001_poLineInfoPMsg poLineInfo = poLineInfoList.get(i);

                    String poApvlStsCd = PO_APVL_STS.AWAITING_APPROVAL;

                    if (0 != NPXC001001CreatePOHistory.createPOHistory(pMsg.glblCmpyCd.getValue(), getPoApvlStsNm(pMsg.glblCmpyCd.getValue(), poApvlStsCd), pMsg.poOrdNum.getValue(), poLineInfo.poOrdDtlLineNum.getValue())) {
                        msgMap.addXxMsgId(NPZC104001Constant.NPAM1442E);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).xxMsgId, NPZC104001Constant.NPAM1442E);
                    }
                }

                NPZC130001PMsg wfPmsg = new NPZC130001PMsg();
                ZYPEZDItemValueSetter.setValue(wfPmsg.glblCmpyCd, pMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(wfPmsg.slsDt, pMsg.procDt);
                ZYPEZDItemValueSetter.setValue(wfPmsg.xxModeCd, NPZC130001Constant.WFMD_PO);
                ZYPEZDItemValueSetter.setValue(wfPmsg.xxProcTpCd, NPZC104001Constant.NEW_ORDER);
                ZYPEZDItemValueSetter.setValue(wfPmsg.trxRefNum, pMsg.poOrdNum);
                ZYPEZDItemValueSetter.setValue(wfPmsg.apvlHistSrcTpCd, APVL_HIST_SRC_TP.PO_ENTRY);
                NPZC130001 wfApi = new NPZC130001();
                wfApi.execute(wfPmsg, onBatchType);
                // if (wfPmsg.xxMsgIdList.getValidCount() > 0) {
                // msgMap.addXxMsgId(wfPmsg.xxMsgIdList.no(0).xxMsgId.getValue());
                // return false;
                // }
                // QC# 15933
                if (wfPmsg.xxMsgIdList.getValidCount() > 0) {
                    msgMap.addXxMsgId(wfPmsg.xxMsgIdList.no(0).xxMsgId.getValue());
                    if (wfPmsg.xxMsgIdList.no(0).xxMsgId.getValue().endsWith(NPZC104001Constant.MESSAGE_KIND_ERROR)) {
                        return false;
                    }
                }
            } else if (!PO_STS.WAITING_FOR_APPROVAL.equals(poStsCd)
                     && ZYPConstant.FLG_ON_Y.equals(pMsg.wfFlg.getValue())
                     && ZYPConstant.FLG_ON_Y.equals(poWfRqstFlg)) {

                for (int i = 0; i < poLineInfoList.size(); i++) {

                    NPZC104001_poLineInfoPMsg poLineInfo = poLineInfoList.get(i);

                    if (0 != NPXC001001CreatePOHistory.createPOHistory(pMsg.glblCmpyCd.getValue(), NPZC104001Constant.AWAITING_PO_CHANGE, pMsg.poOrdNum.getValue(), poLineInfo.poOrdDtlLineNum.getValue())) {
                        msgMap.addXxMsgId(NPZC104001Constant.NPAM1442E);
                        ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).xxMsgId, NPZC104001Constant.NPAM1442E);
                    }
                }

                NPZC130001PMsg wfPmsg = new NPZC130001PMsg();
                ZYPEZDItemValueSetter.setValue(wfPmsg.glblCmpyCd, pMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(wfPmsg.slsDt, pMsg.procDt);
                ZYPEZDItemValueSetter.setValue(wfPmsg.xxModeCd, NPZC130001Constant.WFMD_PO);
                ZYPEZDItemValueSetter.setValue(wfPmsg.xxProcTpCd, NPZC104001Constant.UPDATE_ORDER);
                ZYPEZDItemValueSetter.setValue(wfPmsg.trxRefNum, pMsg.poOrdNum);
                ZYPEZDItemValueSetter.setValue(wfPmsg.apvlHistSrcTpCd, APVL_HIST_SRC_TP.PO_ENTRY);
                NPZC130001 wfApi = new NPZC130001();
                wfApi.execute(wfPmsg, onBatchType);
                // if (wfPmsg.xxMsgIdList.getValidCount() > 0) {
                // msgMap.addXxMsgId(wfPmsg.xxMsgIdList.no(0).xxMsgId.getValue());
                // return false;
                // }
                // QC# 15933
                if (wfPmsg.xxMsgIdList.getValidCount() > 0) {
                    msgMap.addXxMsgId(wfPmsg.xxMsgIdList.no(0).xxMsgId.getValue());
                    if (wfPmsg.xxMsgIdList.no(0).xxMsgId.getValue().endsWith(NPZC104001Constant.MESSAGE_KIND_ERROR)) {
                        return false;
                    }
                }
            }
        // CANCEL MODE
        } else if (!NPZC104001Constant.MODE_CANCEL.equals(pMsg.xxModeCd.getValue()) && ZYPConstant.FLG_ON_Y.equals(pMsg.wfFlg.getValue())) {

            for (int i = 0; i < poLineInfoList.size(); i++) {

                NPZC104001_poLineInfoPMsg poLineInfo = poLineInfoList.get(i);

                if (0 != NPXC001001CreatePOHistory.createPOHistory(pMsg.glblCmpyCd.getValue(), NPZC104001Constant.AWAITING_PO_CANCEL, pMsg.poOrdNum.getValue(), poLineInfo.poOrdDtlLineNum.getValue())) {
                    msgMap.addXxMsgId(NPZC104001Constant.NPAM1442E);
                    ZYPEZDItemValueSetter.setValue(pMsg.poLineInfo.no(i).xxMsgId, NPZC104001Constant.NPAM1442E);
                }
            }

            NPZC130001PMsg wfPmsg = new NPZC130001PMsg();
            ZYPEZDItemValueSetter.setValue(wfPmsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(wfPmsg.slsDt, pMsg.procDt);
            ZYPEZDItemValueSetter.setValue(wfPmsg.xxModeCd, NPZC130001Constant.WFMD_PO);
            ZYPEZDItemValueSetter.setValue(wfPmsg.xxProcTpCd, NPZC104001Constant.UPDATE_ORDER);
            ZYPEZDItemValueSetter.setValue(wfPmsg.trxRefNum, pMsg.poOrdNum);
            ZYPEZDItemValueSetter.setValue(wfPmsg.apvlHistSrcTpCd, APVL_HIST_SRC_TP.PO_ENTRY);
            NPZC130001 wfApi = new NPZC130001();
            wfApi.execute(wfPmsg, onBatchType);
            // if (wfPmsg.xxMsgIdList.getValidCount() > 0) {
            // msgMap.addXxMsgId(wfPmsg.xxMsgIdList.no(0).xxMsgId.getValue());
            // return false;
            // }
            // QC# 15933
            if (wfPmsg.xxMsgIdList.getValidCount() > 0) {
                msgMap.addXxMsgId(wfPmsg.xxMsgIdList.no(0).xxMsgId.getValue());
                if (wfPmsg.xxMsgIdList.no(0).xxMsgId.getValue().endsWith(NPZC104001Constant.MESSAGE_KIND_ERROR)) {
                    return false;
                }
            }
        }
        return true;
    }

    // send PO
    protected static boolean sendPo(NPZC104001PMsg pMsg,
                                    S21ApiMessageMap msgMap,
                                    String poStsCd,
                                    ONBATCH_TYPE onBatchType,
                                    boolean sendPoFlg,
                                    List<NPZC104001InternalInfoBean> internalInfoList) {

        int sendCnt = 0;

        // QC#14996
        RCV_ASN_VNDTMsg rcvAsnVnd = NPZC104001DBAccess.getRcvAsnVnd(pMsg.glblCmpyCd.getValue(), pMsg.vndCd.getValue());
        DS_PO_TPTMsg dsPoTpTMsg = NPZC104001DBAccess.getDsPoTp(pMsg.glblCmpyCd.getValue(), pMsg.dsPoTpCd.getValue());
        if (rcvAsnVnd != null && dsPoTpTMsg != null && ZYPConstant.FLG_ON_Y.equals(dsPoTpTMsg.ediSendFlg.getValue())) {
            sendCnt++;
        }

        if (sendCnt == 0) {
            return true;
        }

        if (!PO_STS.VALIDATED.equals(poStsCd)) {
            return true;
        }

        // QC#24780
        if ((NPZC104001Constant.MODE_UPDATE.equals(pMsg.xxModeCd.getValue()) || NPZC104001Constant.MODE_ASN.equals(pMsg.xxModeCd.getValue())) && !sendPoFlg) {
            return true;
        }

        // DRCT_CPO_CRAT_FLG
        String drctCpoCratFlg = internalInfoList.get(0).getDrctCpoCratFlg();
        if (drctCpoCratFlg == null || drctCpoCratFlg.isEmpty()) {
            drctCpoCratFlg = ZYPConstant.FLG_ON_Y;
        }

        // SEND_PO_IF_CRAT_FLG
        String sendPoIfCratFlg = internalInfoList.get(0).getSendPoIfCratFlg();
        if (sendPoIfCratFlg == null || sendPoIfCratFlg.isEmpty()) {
            sendPoIfCratFlg = ZYPConstant.FLG_ON_Y;
        }

        NPZC134001PMsg sendPoPmsg = new NPZC134001PMsg();
        ZYPEZDItemValueSetter.setValue(sendPoPmsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(sendPoPmsg.slsDt, pMsg.procDt);
        ZYPEZDItemValueSetter.setValue(sendPoPmsg.poOrdNum, pMsg.poOrdNum);

        if (NPZC104001Constant.MODE_CREATE.equals(pMsg.xxModeCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(sendPoPmsg.xxProcTpCd, NPZC104001Constant.NEW_ORDER);
        // QC#24780
        } else if (NPZC104001Constant.MODE_UPDATE.equals(pMsg.xxModeCd.getValue()) || NPZC104001Constant.MODE_ASN.equals(pMsg.xxModeCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(sendPoPmsg.xxProcTpCd, NPZC104001Constant.UPDATE_ORDER);
        }

        ZYPEZDItemValueSetter.setValue(sendPoPmsg.xxdrctCpoCratFlg, drctCpoCratFlg);
        ZYPEZDItemValueSetter.setValue(sendPoPmsg.xxsendPoIfCratFlg, sendPoIfCratFlg);
        NPZC134001 sendPoApi = new NPZC134001();
        sendPoApi.execute(sendPoPmsg, onBatchType);
        if (sendPoPmsg.xxMsgIdList.getValidCount() > 0) {
            msgMap.addXxMsgId(sendPoPmsg.xxMsgIdList.no(0).xxMsgId.getValue());
            return false;
        }

        return true;
    }

    protected static void checkGlblCmpyCd(NPZC104001PMsg pMsg, S21ApiMessageMap msgMap) {
        if (!hasValue(pMsg.glblCmpyCd)) {
            msgMap.addXxMsgId(NPZC104001Constant.NPZM0001E);
        }
    }

    protected static void checkMode(NPZC104001PMsg pMsg, S21ApiMessageMap msgMap) {
        if (!hasValue(pMsg.xxModeCd)) {
            msgMap.addXxMsgId(NPZC104001Constant.NPZM0093E);
        }
    }

    protected static void checkEvent(NPZC104001PMsg pMsg, S21ApiMessageMap msgMap) {
        if (!hasValue(pMsg.eventId)) {
            msgMap.addXxMsgId(NPZC104001Constant.NPZM0094E);
        }
    }

    protected static void checkProcDt(NPZC104001PMsg pMsg, S21ApiMessageMap msgMap) {
        if (!hasValue(pMsg.procDt)) {
            msgMap.addXxMsgId(NPZC104001Constant.NPZM0059E);
        }
    }

    protected static void checkXxRqstTs(NPZC104001PMsg pMsg, S21ApiMessageMap msgMap) {
        if (!hasValue(pMsg.xxRqstTs)) {
            msgMap.addXxMsgId(NPZC104001Constant.NPZM0122E);
        }
    }

    protected static void checkDsPoTpCd(NPZC104001PMsg pMsg, S21ApiMessageMap msgMap) {
        if (!hasValue(pMsg.dsPoTpCd)) {
            msgMap.addXxMsgId(NPZC104001Constant.NPZM0063E);
        }
    }

    protected static void checkDestRtlWhCd(NPZC104001PMsg pMsg, S21ApiMessageMap msgMap) {
        if (!hasValue(pMsg.destRtlWhCd)) {
            msgMap.addXxMsgId(NPZC104001Constant.NPAM1454E);
        }
    }

    protected static void checkPoOrdSrcCd(NPZC104001PMsg pMsg, S21ApiMessageMap msgMap) {
        if (!hasValue(pMsg.poOrdSrcCd)) {
            msgMap.addXxMsgId(NPZC104001Constant.NPZM0109E);
        }
    }

    protected static void checkPrchGrpCd(NPZC104001PMsg pMsg, S21ApiMessageMap msgMap) {
        if (!hasValue(pMsg.prchGrpCd)) {
            msgMap.addXxMsgId(NPZC104001Constant.NPAM1455E);
        }
    }

    protected static void checkPoOrdNum(NPZC104001PMsg pMsg, S21ApiMessageMap msgMap) {
        if (!hasValue(pMsg.poOrdNum)) {
            msgMap.addXxMsgId(NPZC104001Constant.NPAM1384E);
        }
    }

    protected static void checkPoApvlStsCd(NPZC104001PMsg pMsg, S21ApiMessageMap msgMap) {
        if (hasValue(pMsg.poApvlStsCd) &&  PO_APVL_STS.PRE_APPROVED.equals(pMsg.poApvlStsCd.getValue())) {
            if (!hasValue(pMsg.poApvlPsnCd)
             || !hasValue(pMsg.poApvlDt)
             || !hasValue(pMsg.poApvlTs)) {
                msgMap.addXxMsgId(NPZC104001Constant.NPZM0112E);
            }
        }
    }

    protected static void checkSrcRtlWhCd(NPZC104001PMsg pMsg, S21ApiMessageMap msgMap) {
        if (!hasValue(pMsg.srcRtlWhCd)) {
            msgMap.addXxMsgId(NPZC104001Constant.NPZM0078E);
        }
    }

    protected static void checkVndCd(NPZC104001PMsg pMsg, S21ApiMessageMap msgMap) {
        if (!hasValue(pMsg.vndCd)) {
            msgMap.addXxMsgId(NPZC104001Constant.NPZM0065E);
        }
    }

    protected static void checkCreateEvent(NPZC104001PMsg pMsg, S21ApiMessageMap msgMap) {
        if (!NPZC104001Constant.EVENT_SAVE.equals(pMsg.eventId.getValue())
         && !NPZC104001Constant.EVENT_SUBMIT.equals(pMsg.eventId.getValue())) {
            msgMap.addXxMsgId(NPZC104001Constant.NPZM0102E);
        }
    }

    protected static boolean checkPoHdrMsgInfo(NPZC104001PMsg pMsg, S21ApiMessageMap msgMap) {
        if (pMsg.poInfo == null) {
            return true;
        }
        for (int i = 0; i < pMsg.poInfo.getValidCount(); i++) {
            if (!hasValue(pMsg.poInfo.no(i).poMsgTpCd)) {
                msgMap.addXxMsgId(NPZC104001Constant.NPAM1456E);
                return false;
            }
        }
        return true;
    }

    protected static boolean checkPoLineInfo(NPZC104001PMsg pMsg, S21ApiMessageMap msgMap, S21SsmBatchClient glSsmBatchClient) {
        if (pMsg.poLineInfo == null || pMsg.poLineInfo.getValidCount() == 0) {
            msgMap.addXxMsgId(NPZC104001Constant.NPZM0069E);
            return false;
        }

        List<String> poOrdDtlLineNumList = new ArrayList<String>();
        for (int i = 0; i < pMsg.poLineInfo.getValidCount(); i++) {
            if (!checkPoOrdDtlLineNum(pMsg.poLineInfo.no(i).poOrdDtlLineNum.getValue(), msgMap)) {
                return false;
            }
            if (!checkPoLineTpCd(pMsg.poLineInfo.no(i).poLineTpCd.getValue(), msgMap)) {
                return false;
            }
            if (!checkMdseCd(pMsg.poLineInfo.no(i).mdseCd.getValue(), msgMap)) {
                return false;
            }
            if (!checkPoQtyAndPoDispQty(pMsg.poLineInfo.no(i), msgMap)) {
                return false;
            }
            if (!checkEntDealNetUnitPrcAmtAndDealCcyCd(pMsg, i, msgMap)) {
                return false;
            }

            if (DS_PO_TP.BUYBACK_PO.equals(pMsg.dsPoTpCd.getValue())) {
                if (!checkFromStkStsCd(pMsg.poLineInfo.no(i).fromStkStsCd.getValue(), msgMap)) {
                    return false;
                }
            }

            // QC#24780
            if (NPZC104001Constant.MODE_UPDATE.equals(pMsg.xxModeCd.getValue()) || NPZC104001Constant.MODE_ASN.equals(pMsg.xxModeCd.getValue())) {
                if (DS_PO_TP.SUBCONTRACT_PO.equals(pMsg.dsPoTpCd.getValue())) {
                    if (!checkPrchReqNum(pMsg.poLineInfo.no(i).prchReqNum.getValue(), msgMap)) {
                        return false;
                    }
                }
            }

            if (!checkPoMdseCmpsnTpCd(pMsg.poLineInfo.no(i).poMdseCmpsnTpCd.getValue(), msgMap)) {
                return false;
            }

            // QC#18671 Add.
            if (!chkMdseDuplicateFromAsl(pMsg, msgMap, i, glSsmBatchClient)) {
            	return false;
            }

            poOrdDtlLineNumList.add(pMsg.poLineInfo.no(i).poOrdDtlLineNum.getValue());
        }

        if (!checkDuplicatePoOrdDtlLineNum(poOrdDtlLineNumList, msgMap)) {
            return false;
        }

        return true;
    }

    protected static boolean checkPoSerInfo(NPZC104001PMsg pMsg, S21ApiMessageMap msgMap) {
        if (pMsg.serNumList == null) {
            return true;
        }
        for (int i = 0; i < pMsg.serNumList.getValidCount(); i++) {
            if (!hasValue(pMsg.serNumList.no(i).poOrdDtlLineNum)) {
                msgMap.addXxMsgId(NPZC104001Constant.NPZM0108E);
                return false;
            }
            if (!hasValue(pMsg.serNumList.no(i).serNum)) {
                msgMap.addXxMsgId(NPZC104001Constant.NPAM1458E);
                return false;
            }
        }
        return true;
    }

    protected static boolean checkPoAcctInfo(NPZC104001PMsg pMsg, S21ApiMessageMap msgMap) {
        if (pMsg.poAcctInfo == null) {
            return true;
        }
        for (int i = 0; i < pMsg.poAcctInfo.getValidCount(); i++) {
            if (!hasValue(pMsg.poAcctInfo.no(i).poOrdDtlLineNum)) {
                msgMap.addXxMsgId(NPZC104001Constant.NPZM0031E);
                return false;
            }
            if (!hasValue(pMsg.poAcctInfo.no(i).poAcctTpCd)) {
                msgMap.addXxMsgId(NPZC104001Constant.NPAM1459E);
                return false;
            }
            if (!hasValue(pMsg.poAcctInfo.no(i).coaCmpyCd)) {
                msgMap.addXxMsgId(NPZC104001Constant.NPAM1460E);
                return false;
            }
            if (!hasValue(pMsg.poAcctInfo.no(i).coaAfflCd)) {
                msgMap.addXxMsgId(NPZC104001Constant.NPAM1461E);
                return false;
            }
            if (!hasValue(pMsg.poAcctInfo.no(i).coaBrCd)) {
                msgMap.addXxMsgId(NPZC104001Constant.NPAM1462E);
                return false;
            }
            if (!hasValue(pMsg.poAcctInfo.no(i).coaChCd)) {
                msgMap.addXxMsgId(NPZC104001Constant.NPAM1463E);
                return false;
            }
            if (!hasValue(pMsg.poAcctInfo.no(i).coaCcCd)) {
                msgMap.addXxMsgId(NPZC104001Constant.NPAM1464E);
                return false;
            }
            if (!hasValue(pMsg.poAcctInfo.no(i).coaAcctCd)) {
                msgMap.addXxMsgId(NPZC104001Constant.NPAM1465E);
                return false;
            }
            if (!hasValue(pMsg.poAcctInfo.no(i).coaProdCd)) {
                msgMap.addXxMsgId(NPZC104001Constant.NPAM1466E);
                return false;
            }
            if (!hasValue(pMsg.poAcctInfo.no(i).coaProjCd)) {
                msgMap.addXxMsgId(NPZC104001Constant.NPAM1467E);
                return false;
            }
            if (!hasValue(pMsg.poAcctInfo.no(i).coaExtnCd)) {
                msgMap.addXxMsgId(NPZC104001Constant.NPAM1468E);
                return false;
            }
        }
        return true;
    }

    protected static boolean checkPoOrdDtlLineNum(String poOrdDtlLineNum, S21ApiMessageMap msgMap) {
        if (!hasValue(poOrdDtlLineNum)) {
            msgMap.addXxMsgId(NPZC104001Constant.NPZM0108E);
            return false;
        }
        return true;
    }

    protected static boolean checkPoLineTpCd(String poLineTpCd, S21ApiMessageMap msgMap) {
        if (!hasValue(poLineTpCd)) {
            msgMap.addXxMsgId(NPZC104001Constant.NPAM1469E);
            return false;
        }
        return true;
    }

    protected static boolean checkMdseCd(String mdseCd, S21ApiMessageMap msgMap) {
        if (!hasValue(mdseCd)) {
            msgMap.addXxMsgId(NPZC104001Constant.NPZM0020E);
            return false;
        }
        return true;
    }

    protected static boolean checkPoQtyAndPoDispQty(NPZC104001_poLineInfoPMsg pMsg, S21ApiMessageMap msgMap) {
        if (!hasValue(pMsg.poQty) && !hasValue(pMsg.poDispQty)) {
            msgMap.addXxMsgId(NPZC104001Constant.NPZM0110E);
            return false;
        }
        if (hasValue(pMsg.poQty) && BigDecimal.ZERO.compareTo(pMsg.poQty.getValue()) >= 0) {
            msgMap.addXxMsgId(NPZC104001Constant.NPZM0114E);
            return false;
        }
        if (hasValue(pMsg.poDispQty) && BigDecimal.ZERO.compareTo(pMsg.poDispQty.getValue()) >= 0) {
            msgMap.addXxMsgId(NPZC104001Constant.NPZM0114E);
            return false;
        }
        return true;
    }

    protected static boolean checkEntDealNetUnitPrcAmtAndDealCcyCd(NPZC104001PMsg pMsg, int i, S21ApiMessageMap msgMap) {
        BigDecimal amt = pMsg.poLineInfo.no(i).entDealNetUnitPrcAmt.getValue();
        if (BigDecimal.ZERO.compareTo(amt) < 0 && hasValue(pMsg.dealCcyCd)) {
            return true;
        }
        if (!ZYPCommonFunc.hasValue(amt)) {
            return true;
        }
        if (BigDecimal.ZERO.compareTo(amt) == 0) {
            return true;
        }
        msgMap.addXxMsgId(NPZC104001Constant.NPAM1471E);
        return false;
    }

    protected static boolean checkFromStkStsCd(String fromStkStsCd, S21ApiMessageMap msgMap) {
        if (!hasValue(fromStkStsCd)) {
            msgMap.addXxMsgId(NPZC104001Constant.NPAM1473E);
            return false;
        }
        return true;
    }

    protected static boolean checkPrchReqNum(String prchReqNum, S21ApiMessageMap msgMap) {
        if (!hasValue(prchReqNum)) {
            msgMap.addXxMsgId(NPZC104001Constant.NPAM1474E);
            return false;
        }
        return true;
    }

    protected static boolean checkPoMdseCmpsnTpCd(String poMdseCmpsnTpCd, S21ApiMessageMap msgMap) {
        if (hasValue(poMdseCmpsnTpCd)) {
            if (!PO_MDSE_CMPSN_TP.PARENT.equals(poMdseCmpsnTpCd)
             && !PO_MDSE_CMPSN_TP.CHILD.equals(poMdseCmpsnTpCd)
             && !PO_MDSE_CMPSN_TP.REGULAR.equals(poMdseCmpsnTpCd)) {
                msgMap.addXxMsgId(NPZC104001Constant.NPAM1475E);
                return false;
            }
        }
        return true;
    }

    protected static boolean checkDuplicatePoOrdDtlLineNum(List<String> poOrdDtlLineNumList, S21ApiMessageMap msgMap) {
        Set<String> checkHash = new HashSet<String>();
        for (String str : poOrdDtlLineNumList) {
            if (checkHash.contains(str)) {
                msgMap.addXxMsgId(NPZC104001Constant.NPAM1476E);
                return false;
            } else {
                checkHash.add(str);
            }
        }
        return true;
    }

    /**
     * Get PO Approval Status Name for PO History
     * @param poApvlStsCd String
     */
    protected static String getPoApvlStsNm(String glblCmpyCd, String poApvlStsCd) {

        String poApvlStsNm = "";

        PO_APVL_STSTMsg inMsg = new PO_APVL_STSTMsg();
        inMsg.glblCmpyCd.setValue(glblCmpyCd);
        inMsg.poApvlStsCd.setValue(poApvlStsCd);

        PO_APVL_STSTMsg tMsg = (PO_APVL_STSTMsg) S21CacheTBLAccessor.findByKey(inMsg);

        if (tMsg != null && EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            poApvlStsNm = tMsg.poApvlStsDescTxt.getValue();
        }
        return poApvlStsNm;
    }

    protected static List<String> getMsgList(NPZC104001PMsg pMsg, String poMsgTpCd) {

        List<String> list = new ArrayList<String>();

        String msgTxt = "";

        if (PO_MSG_TP.RECEIVER_NOTE.equals(poMsgTpCd)) {
            msgTxt = pMsg.shipFromSoNumListTxt.getValue();
        }

        for (int i = 0; i < pMsg.poInfo.getValidCount(); i++) {
            NPZC104001_poInfoPMsg poInfo = pMsg.poInfo.no(i);
            if (hasValue(poInfo.poMsgTpCd) && poMsgTpCd.equals(poInfo.poMsgTpCd.getValue()) && ZYPCommonFunc.hasValue(poInfo.xxDsMultMsgDplyTxt)) {
                msgTxt = msgTxt.concat(poInfo.xxDsMultMsgDplyTxt.getValue());
            }
        }

        int msgLength = 65;

        if (msgTxt.length() <= msgLength) {
            list.add(msgTxt);

        } else if (msgTxt.length() > msgLength && msgTxt.length() <= msgLength * 2) {
            list.add(msgTxt.substring(0, msgLength));
            list.add(msgTxt.substring(msgLength));

        } else if (msgTxt.length() > msgLength * 2 && msgTxt.length() <= msgLength * 3) {
            list.add(msgTxt.substring(0, msgLength));
            list.add(msgTxt.substring(msgLength, msgLength * 2));
            list.add(msgTxt.substring(msgLength * 2));

        } else if (msgTxt.length() > msgLength * 3 && msgTxt.length() <= msgLength * 4) {
            list.add(msgTxt.substring(0, msgLength));
            list.add(msgTxt.substring(msgLength, msgLength * 2));
            list.add(msgTxt.substring(msgLength * 2, msgLength * 3));
            list.add(msgTxt.substring(msgLength * 3));
        } else {
            list.add(msgTxt.substring(0, msgLength));
            list.add(msgTxt.substring(msgLength, msgLength * 2));
            list.add(msgTxt.substring(msgLength * 2, msgLength * 3));
            list.add(msgTxt.substring(msgLength * 3, msgLength * 4));
        }

        return list;
    }

    /**
     * Override Comparator Class
     */
    public static class Comp implements Comparator<Object> {

        /** sortKey */
        private String sortKey;

        /**
         * Constructor
         * @param sortKey String
         */
        public Comp(String sortKey) {
            this.sortKey = sortKey;
        }

        /**
         * comparator
         * @param oPMsg1 Object
         * @param oPMsg2 Object
         * @return result int
         */
        @Override
        public int compare(Object oPMsg1, Object oPMsg2) {
            String ret1 = getValue(sortKey, oPMsg1);
            String ret2 = getValue(sortKey, oPMsg2);
            return ret1.compareTo(ret2);
        }

        private String getValue(String key, Object oPMsg) {
            try {
                Field field = oPMsg.getClass().getField(key);
                EZDPStringItem item = (EZDPStringItem) field.get(oPMsg);
                return item.getValue();
            } catch (Exception e) {
                return "";
            }
        }
    }

    /**
     * Override Comparator Class By BigDecimal
     */
    public static class CompByBigDecimal implements Comparator<Object> {

        /** sortKey */
        private String sortKey;

        /**
         * Constructor
         * @param sortKey String
         */
        public CompByBigDecimal(String sortKey) {
            this.sortKey = sortKey;
        }

        /**
         * comparator
         * @param oPMsg1 Object
         * @param oPMsg2 Object
         * @return result int
         */
        @Override
        public int compare(Object oPMsg1, Object oPMsg2) {
            int ret;
            BigDecimal ret1 = getValue(sortKey, oPMsg1);
            BigDecimal ret2 = getValue(sortKey, oPMsg2);
            if (ret1 != null && ret2 == null) {
                ret = -1;
            } else if (ret1 == null && ret2 != null) {
                ret = 1;
            } else if (ret1 == null && ret2 == null) {
                ret = 0;
            } else {
                ret = ret1.compareTo(ret2);
            }
            return ret;
        }

        private BigDecimal getValue(String key, Object oPMsg) {
            try {
                Field field = oPMsg.getClass().getField(key);
                EZDPBigDecimalItem item = (EZDPBigDecimalItem) field.get(oPMsg);
                return item.getValue();
            } catch (Exception e) {
                return BigDecimal.ZERO;
            }
        }
    }

    /**
     * createRwsForBuyback
     * @param pMsg NPZC104001PMsg
     * @param msgMap S21ApiMessageMap
     * @param poLineInfoList List<NPZC104001_poLineInfoPMsg>
     * @param vndCd String
     * @param whCd String
     * @param wkInfoList List<NPZC104001InternalInfoBean>
     * @param ssmBatchClient S21SsmBatchClient
     * @param onBatchType ONBATCH_TYPE
     * @return boolean
     */
    private static boolean createRwsForBuyback(NPZC104001PMsg pMsg, S21ApiMessageMap msgMap, List<NPZC104001_poLineInfoPMsg> poLineInfoList, String vndCd, String whCd, List<NPZC104001InternalInfoBean> wkInfoList,
            S21SsmBatchClient ssmBatchClient, ONBATCH_TYPE onBatchType) {

        // set PO Receiving Api Param
        NLZC201001PMsg poRcvPmsg = new NLZC201001PMsg();
        ZYPEZDItemValueSetter.setValue(poRcvPmsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(poRcvPmsg.sysSrcCd, SYS_SRC.S21_PROCUREMENT);
        if (hasValue(wkInfoList.get(0).getSceOrdTpCd())) {
            ZYPEZDItemValueSetter.setValue(poRcvPmsg.sceOrdTpCd, wkInfoList.get(0).getSceOrdTpCd());
        } else {
            int countPrntCmpyVnd = NPZC104001DBAccess.countPrntCmpyVndByKey(pMsg, vndCd, ssmBatchClient);

            if (countPrntCmpyVnd > 0) {
                ZYPEZDItemValueSetter.setValue(poRcvPmsg.sceOrdTpCd, NLXSceConst.SCE_ORD_TP_CD_DG);
            } else {
                ZYPEZDItemValueSetter.setValue(poRcvPmsg.sceOrdTpCd, NLXSceConst.SCE_ORD_TP_CD_DP);
            }
        }
        ZYPEZDItemValueSetter.setValue(poRcvPmsg.poRcvFromLocTpCd, LOC_TP.VENDOR);
        ZYPEZDItemValueSetter.setValue(poRcvPmsg.poRcvFromLocCd, vndCd);
        ZYPEZDItemValueSetter.setValue(poRcvPmsg.whCd, whCd);
        ZYPEZDItemValueSetter.setValue(poRcvPmsg.poRcvDrctShipTpCd, DRCT_SHIP_TP.N_OR_A);
        ZYPEZDItemValueSetter.setValue(poRcvPmsg.poRcvTrxHdrNum, pMsg.poOrdNum);
        if (NLXSceConst.SCE_ORD_TP_CD_DP.equals(poRcvPmsg.sceOrdTpCd.getValue())
         || NLXSceConst.SCE_ORD_TP_CD_DG.equals(poRcvPmsg.sceOrdTpCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(poRcvPmsg.domInvNum, pMsg.poOrdNum);
        }

        String rwsRefNum = getRwsRefNum(pMsg, whCd, msgMap, ssmBatchClient);

        if (rwsRefNum == null) {

            return false;
        }

        ZYPEZDItemValueSetter.setValue(poRcvPmsg.rwsRefNum, rwsRefNum);
        ZYPEZDItemValueSetter.setValue(poRcvPmsg.poRcvEtaDt, pMsg.rqstRcvDt);

        List<String> poRcvMsgList = getMsgList(pMsg, PO_MSG_TP.RECEIVER_NOTE);
        int poRcvMsgIdx = 0;
        for (; poRcvMsgIdx < poRcvMsgList.size(); poRcvMsgIdx++) {
            ZYPEZDItemValueSetter.setValue(poRcvPmsg.MsgInfoLIst.no(poRcvMsgIdx).poRcvMsgTxt, poRcvMsgList.get(poRcvMsgIdx));
        }
        poRcvPmsg.MsgInfoLIst.setValidCount(poRcvMsgIdx);

        int ordInfoListIndex = 0;

        for (int i = 0; i < poLineInfoList.size(); i++) {

            NPZC104001_poLineInfoPMsg poLineInfo = poLineInfoList.get(i);
            // Intangible Item check
            MDSETMsg mdseTmsg = NPZC104001DBAccess.findMdse(pMsg.glblCmpyCd.getValue(), poLineInfo.mdseCd.getValue());
            if (mdseTmsg == null || !hasValue(mdseTmsg.invtyCtrlFlg) || ZYPConstant.FLG_OFF_N.equals(mdseTmsg.invtyCtrlFlg.getValue())) {
                continue;
            }
            // Parent Item check
            if (PO_MDSE_CMPSN_TP.PARENT.equals(poLineInfo.poMdseCmpsnTpCd.getValue())) {
                continue;
            }

            PO_LINE_TPTMsg poLineTpTMsg = NPZC104001DBAccess.getPoLineTp(pMsg.glblCmpyCd.getValue(), poLineInfo.poLineTpCd.getValue());
            if (poLineTpTMsg == null || !ZYPConstant.FLG_ON_Y.equals(poLineTpTMsg.rwsAutoCratFlg.getValue())) {
                continue;
            }

            // RWS Request QTY check
            BigDecimal rwsQty = NPZC104001DBAccess.getRwsQty(pMsg, poLineInfo.poOrdDtlLineNum.getValue(), ssmBatchClient);
            if (!hasValue(rwsQty)) {
                continue;
            }
            // set PO Receiving Api ORD_INFO Param
            ZYPEZDItemValueSetter.setValue(poRcvPmsg.OrdInfoLIst.no(ordInfoListIndex).mdseCd, poLineInfo.mdseCd);
            if (hasValue(poLineInfo.toStkStsCd)) {
                ZYPEZDItemValueSetter.setValue(poRcvPmsg.OrdInfoLIst.no(ordInfoListIndex).stkStsCd, poLineInfo.toStkStsCd);
            } else {
                ZYPEZDItemValueSetter.setValue(poRcvPmsg.OrdInfoLIst.no(ordInfoListIndex).stkStsCd, STK_STS.GOOD);
            }
            ZYPEZDItemValueSetter.setValue(poRcvPmsg.OrdInfoLIst.no(ordInfoListIndex).poRcvQty, rwsQty);
            ZYPEZDItemValueSetter.setValue(poRcvPmsg.OrdInfoLIst.no(ordInfoListIndex).poRcvTrxLineNum, poLineInfo.poOrdDtlLineNum);
            ZYPEZDItemValueSetter.setValue(poRcvPmsg.OrdInfoLIst.no(ordInfoListIndex).carrCd, pMsg.carrCd);
            ZYPEZDItemValueSetter.setValue(poRcvPmsg.OrdInfoLIst.no(ordInfoListIndex).proNum, poLineInfo.proNum);
            ZYPEZDItemValueSetter.setValue(poRcvPmsg.OrdInfoLIst.no(ordInfoListIndex).invtyLocCd, poLineInfo.invtyLocCd);
            ZYPEZDItemValueSetter.setValue(poRcvPmsg.OrdInfoLIst.no(ordInfoListIndex).shipFromInvtyLocCd, vndCd);
            ZYPEZDItemValueSetter.setValue(poRcvPmsg.OrdInfoLIst.no(ordInfoListIndex).prchReqNum, poLineInfo.prchReqNum);
            ZYPEZDItemValueSetter.setValue(poRcvPmsg.OrdInfoLIst.no(ordInfoListIndex).prchReqLineNum, poLineInfo.prchReqLineNum);
            ZYPEZDItemValueSetter.setValue(poRcvPmsg.OrdInfoLIst.no(ordInfoListIndex).prchReqLineSubNum, poLineInfo.prchReqLineSubNum);
            if (ZYPCommonFunc.hasValue(poLineInfo.svcConfigMstrPk)) {
                ZYPEZDItemValueSetter.setValue(poRcvPmsg.svcConfigMstrPk, poLineInfo.svcConfigMstrPk.getValue());
            }
            ordInfoListIndex++;
        } // end loop

        // if detail data not exist then Skip
        if (ordInfoListIndex == 0) {
            return true;
        }

        poRcvPmsg.OrdInfoLIst.setValidCount(ordInfoListIndex);

        // call PO Receiving API
        NLZC201001 poRcvApi = new NLZC201001();
        poRcvApi.execute(poRcvPmsg, onBatchType);

        if (poRcvPmsg.xxMsgIdList.getValidCount() > 0) {
            msgMap.addXxMsgId(NPZC104001Constant.NPZM0120E);
            return false;
        }

        // RWS API
        NLZC200001PMsg rwsPmsg = new NLZC200001PMsg();
        ZYPEZDItemValueSetter.setValue(rwsPmsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(rwsPmsg.sysSrcCd, SYS_SRC.S21_PROCUREMENT);
        ZYPEZDItemValueSetter.setValue(rwsPmsg.inbdSrcTpCd, INBD_SRC_TP.PO_RECEIVING);
        ZYPEZDItemValueSetter.setValue(rwsPmsg.poRcvNum, poRcvPmsg.poRcvNum);
        NLZC200001 rwsApi = new NLZC200001();
        rwsApi.execute(rwsPmsg, onBatchType);
        if (rwsPmsg.xxMsgIdList.getValidCount() > 0) {
            msgMap.addXxMsgId(NPZC104001Constant.NPZM0121E);
            return false;
        }

        for (int j = 0; j < rwsPmsg.RWSNumList.getValidCount(); j++) {
            String rwsNum = rwsPmsg.RWSNumList.no(j).rwsNum.getValue();

            // get Serial List
            List<Map<String, Object>> serialList = NPZC104001DBAccess.getSerialList(pMsg.glblCmpyCd.getValue(), rwsNum, ssmBatchClient);
            if (serialList == null || serialList.size() == 0) {
                return true; // skip under process
            }

            // RWS Serial API
            NLZC304001PMsg rwsSerPmsg = new NLZC304001PMsg();
            ZYPEZDItemValueSetter.setValue(rwsSerPmsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(rwsSerPmsg.rwsNum, rwsNum);
            int k = 0;
            for (; k < serialList.size(); k++) {
                String serRwsDtlLineNum = (String) serialList.get(k).get(NPZC104001Constant.RWS_DTL_LINE_NUM);
                String serSerNum = (String) serialList.get(k).get(NPZC104001Constant.SER_NUM);
                String serMdseCd = (String) serialList.get(k).get(NPZC104001Constant.MDSE_CD);
                ZYPEZDItemValueSetter.setValue(rwsSerPmsg.SerialList.no(k).rwsDtlLineNum, serRwsDtlLineNum);
                ZYPEZDItemValueSetter.setValue(rwsSerPmsg.SerialList.no(k).serNum, serSerNum);
                ZYPEZDItemValueSetter.setValue(rwsSerPmsg.SerialList.no(k).mdseCd, serMdseCd);
                ZYPEZDItemValueSetter.setValue(rwsSerPmsg.SerialList.no(k).putAwayChkStsCd, PUT_AWAY_CHK_STS.NOT_PROCESSED);
                ZYPEZDItemValueSetter.setValue(rwsSerPmsg.SerialList.no(k).serNumSendFlg, ZYPConstant.FLG_OFF_N);
            }
            rwsSerPmsg.SerialList.setValidCount(k);
            NLZC304001 rwsSerApi = new NLZC304001();
            rwsSerApi.execute(rwsSerPmsg, onBatchType);
            if (rwsSerPmsg.xxMsgIdList.getValidCount() > 0) {
                msgMap.addXxMsgId(NPZC104001Constant.NPAM1445E);
                return false;
            }
        }

        return true;
    }

    /** QC#18671 Add.
     * chkMdseDuplicateFromAsl
     * @param pMsg
     * @param msgMap
     * @param index
     * @param glSsmBatchClient
     * @return
     */
    public static boolean chkMdseDuplicateFromAsl(NPZC104001PMsg pMsg, S21ApiMessageMap msgMap, int index, S21SsmBatchClient glSsmBatchClient) {

        // VND check
        RCV_ASN_VNDTMsg tMsg = new RCV_ASN_VNDTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.rcvAsnVndCd, pMsg.vndCd);
        tMsg = (RCV_ASN_VNDTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        if (!(tMsg != null && EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode()))) {
            return true;
        }
        if (!VND_SYS_TP.PARTS.equals(tMsg.vndSysTpCd.getValue())) {
            // Skip checking unless CUSA Parts
            return true;
        }

        // QC#52460 2019/09/25 Mod Start
        String poLineStsCd = null;
        if (ZYPCommonFunc.hasValue(pMsg.poLineInfo.no(index).poLineStsCd)) {
        	poLineStsCd = pMsg.poLineInfo.no(index).poLineStsCd.getValue();
        }

        List<Map<String, Object>> mdseMapList = null;
        if (poLineStsCd == null || (!PO_LINE_STS.CANCELLED.equals(poLineStsCd) && !PO_LINE_STS.CLOSED.equals(poLineStsCd)) ) {
        	mdseMapList = NPZC104001DBAccess.getMdseFromSupplierItem(
        			pMsg.glblCmpyCd.getValue(), pMsg.vndCd.getValue(), pMsg.poLineInfo.no(index).mdseCd.getValue(), glSsmBatchClient);
        }


    	if (mdseMapList == null || mdseMapList.isEmpty()) {
			// No Check.
			return true;
		}

		for (Map<String, Object> mdseMap : mdseMapList) {
			for (int i = 0; i < pMsg.poLineInfo.getValidCount(); i++) {
				// QC#52460 2019/09/25 Mod Start
//				if (mdseMap.get("MDSE_CD").toString().equals(pMsg.poLineInfo.no(i).mdseCd.getValue())) {
//
//					// Duplicated Error.
//					msgMap.addXxMsgId(NPZC104001Constant.NPZM0300E);
//					return false;
//				}
				String targetPoLineStsCd = null;
    			if (ZYPCommonFunc.hasValue(pMsg.poLineInfo.no(i).poLineStsCd)) {
    				targetPoLineStsCd = pMsg.poLineInfo.no(i).poLineStsCd.getValue();
            	}
    			if (targetPoLineStsCd == null || (!PO_LINE_STS.CANCELLED.equals(targetPoLineStsCd) && !PO_LINE_STS.CLOSED.equals(targetPoLineStsCd))) {

					if (mdseMap.get("MDSE_CD").toString().equals(pMsg.poLineInfo.no(i).mdseCd.getValue())) {

						// Duplicated Error.
						msgMap.addXxMsgId(NPZC104001Constant.NPZM0300E);
						return false;
					}
				}
				// QC#52460 2019/09/25 Mod End
			}
		}
		return true;
    }

}
