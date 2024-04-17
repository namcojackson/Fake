/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */

package com.canon.cusa.s21.api.NPZ.NPZC132001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDTBLAccessor;
import business.db.NPAA0010_01TMsg;
import business.db.NPAA0010_02TMsg;
import business.db.NPAA0020_01TMsg;
import business.db.NPAA0020_02TMsg;
import business.db.POTMsg;
import business.db.PO_DTLTMsg;
import business.db.PO_MSGTMsg;
import business.parts.NPZC132001PMsg;

import com.canon.cusa.s21.common.NPX.NPXC001001.NPXC001001PoMsg;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCharacterConversionUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTRY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_PO_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_COND;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_CMPSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MSG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_ORD_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_SVC_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_OWNR;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * <pre>
 * Send PO API for Interface .
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/03/2015   CITS            T.Hakodate      Create          N/A
 * 29/23/2016   CITS            T.Hakodate      UPDATE          UniteTest#111
 * 05/04/2016   CITS            T.Hakodate      UPDATE          QC#6501
 * 09/21/2016   CITS            T.Tokutomi      UPDATE          QC#12300
 * 12/22/2016   CITS            K.Ogino         UPDATE          QC#14749
 * 08/08/2017   CITS            S.Endo          UPDATE          Sol#035(QC#18108)
 * 10/16/2017   CITS            T.Kikuhara      Update          QC#20246(Sol#454)
 * 11/07/2017   CITS            K.Ogino         Update          QC#22295
 * 12/11/2017   CITS            T.Hakodate      UPDATE          QC#21025
 * 12/26/2017   CITS            T.Hakodate      Update          QC#23031
 * 02/02/2018   CITS            K.Ogino         Update          QC#23814
 * 03/09/2018   CITS            K.Ogino         Update          QC#24732
 * 05/22/2018   CITS            T.Hakodate      UPDATE          QC#26006
 * 05/28/2018   CITS            K.Kameoka       UPDATE          QC#26074/QC#26105
 * 06/06/2018   CITS            K.Kameoka       UPDATE          QC#23356
 * 06/26/2018   CITS            T.Hakodate      UPDATE          QC#25392
 * 08/22/2018   CITS            T.hakodate      Update          QC#27628
 * 10/18/2018   CITS            T.Hakodate      Update          QC#28731
 * 02/21/2019   CITS            K.Ogino         Update          QC#30473
 * 02/26/2019   Fujitsu         T.Ogura         Update          QC#30518
 * 04/11/2019   Fujitsu         T.Ogura         Update          QC#31041
 * 04/24/2019   CITS            K.Ogino         Update          QC#31322
 * 06/03/2019   CITS            K.Ogino         Update          QC#50026
 * 07/17/2019   CITS            R.Shimamoto     Update          QC#51557
 * 09/25/2019   CITS            K.Ogino         Update          QC#53395
 * 10/14/2019   CITS            K.Ogino         Update          QC#53395-1
 * 12/03/2019   CITS            M.Naito         Update          QC#54563-1
 * 2021/04/30   CITS            K.Ogino         Update          QC#58769
 * 2023/02/17   Hitachi         TZ.Win          Update          QC#60966
 *</pre>
 */

public class NPZC132001 extends S21ApiCommonBase implements NPZC132001Constant {

    /** SSM Client */
    // private S21SsmBatchClient ssmClient;
    /**
     * SQLAccess.
     */
    private S21SsmBatchClient ssmBatchClient = null;

    /**
     * trdPtnrCarrCd.
     */
    private String trdPtnrCarrCd = null;

    /**
     * trdPtnrShpgSvcLvlCd.
     */
    private String trdPtnrShpgSvcLvlCd = null;

    /**
     * trdPtnrShipViaCd.
     */
    private String trdPtnrShipViaCd = null;

    /**
     * trdPtnrFrtChegMethCd.
     */
    private String trdPtnrFrtChegMethCd = null;

    /**
     * cusaGlblCmpyCd.
     */
    private String cusaGlblCmpyCd = null;

    /**
     * slsdt.
     */
    private String slsdt = null;

    /**
     * carrTpCd.
     */
    private String carrTpCd = null;

    // QC#27628 add start
    /** carrCdfromXref. */
    private static String carrCdfromXref = "";

    private static boolean carrAcctNumIsNull = false;

    private static boolean carrAcctNumFromXref = false;

    // QC#27628 add end

    /**
     * INIT.
     */
    public NPZC132001() {
        super();

        // SSM
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * @param param NPZC132001PMsg
     * @param onBatchType ONBATCH_TYPE
     */
    public void execute(final NPZC132001PMsg param, final ONBATCH_TYPE onBatchType) {

        EZDDebugOutput.println(1, NPZC132001Constant.PROGRAM_ID + "[ execute ] start", this);

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        if (!checkParam(msgMap, onBatchType)) {
            msgMap.flush();
            return;
        }

        if (!checkIfId(msgMap, onBatchType)) {
            msgMap.flush();
            return;
        }

        /**
         * *Get Send Po List
         */

        List<Map<String, Object>> sendPoInfoList = sendPoInfo(msgMap, onBatchType);

        if (sendPoInfoList == null || sendPoInfoList.isEmpty()) {

            S21InfoLogOutput.printlnv(MSG_ID_NPAM1391E, param.poOrdNum.getValue());

            msgMap.addXxMsgId(MSG_ID_NPAM1391E);

            msgMap.flush();

            return;
        }

        Map<String, Object> poInfoMap = sendPoInfoList.get(0);

        String dropShipFlg = (String) poInfoMap.get(VND_DROP_SHIP_FLG);

        String csaLocCd = searchCsaLocationCd(msgMap, dropShipFlg, poInfoMap);

        // QC#26074 Start
        // dropShipFlag
        if (MD_VND_LOC_CD.equals(csaLocCd)) {
            dropShipFlg = ZYPConstant.FLG_ON_Y;
        }
        // QC#26074 End

        // QC#12300
        String dsPoTpCd = (String) poInfoMap.get(DS_PO_TP_CD);

        // QC#27628 add start
        carrAcctNumFromXref = false;
        // QC#27628 add end

        /********************************************************************************************
         * CUSA Mdse IF NPAA0010_01 VND_CD = CANON1 WS
         ********************************************************************************************/
        if (param.itrlIntfcId.getValue().equals(ITRL_INTFC_ID_NPAA0010)) {

            NPAA0010_01TMsg npaa001001 = new NPAA0010_01TMsg();

            boolean isError = true;

            List<NPAA0010_02TMsg> npaa001002TMsgList = new ArrayList<NPAA0010_02TMsg>();

            // set EDI PO Type
            if (ZYPConstant.FLG_ON_Y.equals(dropShipFlg)) {

                ZYPEZDItemValueSetter.setValue(npaa001001.ediPoTpCd, EDI_PO_TP_CD_DS);

            } else {

                ZYPEZDItemValueSetter.setValue(npaa001001.ediPoTpCd, EDI_PO_TP_CD_SA);

            }

            // QC#12300
            if (DS_PO_TP.PASS_THROUGH_PO.equals(dsPoTpCd)) {
                ZYPEZDItemValueSetter.setValue(npaa001001.ediPoTpCd, EDI_PO_TP_CD_PT);
            }

            // QC#12300
            if (DS_PO_TP.DO_NOT_TRANSMIT_PO.equals(dsPoTpCd)) {
                return;
            }

            /**
             * *Freight
             */
            // TRD_PTNR_SHPG_COND_XREF
            Map<String, Object> trdPtnrShpgCondCd = getTrdPtnrShpgCondCdInfo(poInfoMap, msgMap);

            if (trdPtnrShpgCondCd != null) {

                ZYPEZDItemValueSetter.setValue(npaa001001.shpgCondCd, (String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD));
            }

            // TRD_PTNR_SHPG_X_REF

            // QC#26006 MOD START
            Map<String, Object> trdPtnrShpgXRefMap = new HashMap<String, Object>();
            trdPtnrShpgXRefMap = getTrdPtnrShpgXRefInfo(poInfoMap, msgMap);
            if (trdPtnrShpgXRefMap != null) {
                trdPtnrCarrCd = (String) trdPtnrShpgXRefMap.get(TRD_PTNR_CARR_CD);
                trdPtnrShpgSvcLvlCd = (String) trdPtnrShpgXRefMap.get(TRD_PTNR_SHPG_SVC_LVL_CD);
                trdPtnrShipViaCd = (String) trdPtnrShpgXRefMap.get(TRD_PTNR_SHIP_VIA_CD);
                trdPtnrFrtChegMethCd = (String) trdPtnrShpgXRefMap.get(TRD_PTNR_FRT_CHRG_METH_CD);
            }
            // QC#26006 MOD END

            ZYPEZDItemValueSetter.setValue(npaa001001.carrCd, trdPtnrCarrCd);
            ZYPEZDItemValueSetter.setValue(npaa001001.shpgSvcLvlCd, trdPtnrShpgSvcLvlCd);
            ZYPEZDItemValueSetter.setValue(npaa001001.shipViaCd, trdPtnrShipViaCd);
            ZYPEZDItemValueSetter.setValue(npaa001001.ediFrtChrgMethCd, trdPtnrFrtChegMethCd);

            /**
             *　Other Header Info
             */
            ZYPEZDItemValueSetter.setValue(npaa001001.itrlIntfcId, param.itrlIntfcId.getValue());
            ZYPEZDItemValueSetter.setValue(npaa001001.itrlTrxSq, param.itrlTrxSq.getValue());
            ZYPEZDItemValueSetter.setValue(npaa001001.sendId, param.sendId.getValue());
            ZYPEZDItemValueSetter.setValue(npaa001001.rcvId, param.rcvId.getValue());
            ZYPEZDItemValueSetter.setValue(npaa001001.custEdiPoNum, param.poOrdNum.getValue());
            ZYPEZDItemValueSetter.setValue(npaa001001.custEdiPoDt, (String) poInfoMap.get(PO_APVL_DT));
            ZYPEZDItemValueSetter.setValue(npaa001001.ediTrxPrpsCd, EDI_TRX_PRPS_CD_O);
            ZYPEZDItemValueSetter.setValue(npaa001001.poRcvTs, ZYPDateUtil.getCurrentSystemTime(TIMESTANP));
            // QC#20246 UPD START
            // QC#24732
            if (poInfoMap.get(CTAC_PSN_NM) != null) {
                // START 2019/12/04 M.Naito [QC#54563-1,ADD]
                convertCharacterForPoInfo(poInfoMap);
                // END 2019/12/04 M.Naito [QC#54563-1,ADD]
                if (((String) poInfoMap.get(CTAC_PSN_NM)).length() > TXT_LENGTH_30) {
                    ZYPEZDItemValueSetter.setValue(npaa001001.firstRefTxt, ((String) poInfoMap.get(CTAC_PSN_NM)).substring(0, TXT_LENGTH_30));
                } else {
                    ZYPEZDItemValueSetter.setValue(npaa001001.firstRefTxt, (String) poInfoMap.get(CTAC_PSN_NM));
                }
            }
            // QC#20246 UPD END
            npaa001001.scdRefTxt.clear();

            // QC#14749
            setMsgWs(msgMap, poInfoMap, npaa001001);

            ZYPEZDItemValueSetter.setValue(npaa001001.custCpoOrdNum, (String) poInfoMap.get(CPO_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(npaa001001.endCustIssPoNum, (String) poInfoMap.get(CUST_ISS_PO_NUM));

            // QC#23356 Mod Start
            // get Carrier Acount #
            String carrAcctNum = null;

            if (poInfoMap.get(CARR_ACCT_NUM) == null) {
                carrAcctNum = getCarrAcctNum(poInfoMap, msgMap);

                // QC#27628 mod start
                if (ZYPCommonFunc.hasValue(carrAcctNum)) {
                    carrAcctNumFromXref = true;
                }
                // QC#27628 mod end
            } else {
                carrAcctNum = (String) poInfoMap.get(CARR_ACCT_NUM);
            }
            // CARR_ACCT_NUM
            ZYPEZDItemValueSetter.setValue(npaa001001.carrAcctNum, carrAcctNum);
            // QC#23356 Mod End

            // RTRN_SHIP_TO_RTL_WH_CD
            ZYPEZDItemValueSetter.setValue(npaa001001.rtrnShipToRtlWhCd, (String) poInfoMap.get(RTRN_SHIP_TO_RTL_WH_CD));
            // VND_PMT_TERM_DESC_TXT
            ZYPEZDItemValueSetter.setValue(npaa001001.vndPmtTermDescTxt, (String) poInfoMap.get(VND_PMT_TERM_DESC_TXT));

            if (!ZYPCommonFunc.hasValue(npaa001001.custEdiPoDt)) {
                msgMap.addXxMsgId(MSG_ID_NPAM1384E);
            }

            /**
             * sendPoInfoList Loop NPAA0010_02Array
             */
            for (int i = 0; i < sendPoInfoList.size(); i++) {

                Map<String, Object> sendPoInfoLineMap = sendPoInfoList.get(i);

                NPAA0010_02TMsg npaa001002 = new NPAA0010_02TMsg();

                // Trading Partner SKU Code
                if (sendPoInfoLineMap.get(CUST_MDSE_CD) != null) {
                    ZYPEZDItemValueSetter.setValue(npaa001002.trdPtnrSkuCd, (String) sendPoInfoLineMap.get(CUST_MDSE_CD));
                } else {
                    ZYPEZDItemValueSetter.setValue(npaa001002.trdPtnrSkuCd, (String) sendPoInfoLineMap.get(MDSE_CD));
                }

                // Trading Partner Ship To Category Code
                // *****************
                if (sendPoInfoLineMap.get(PO_QLFY_CD) == PO_QLFY_CD_PC) {
                    ZYPEZDItemValueSetter.setValue(npaa001002.trdPtnrShipToCatgCd, TRD_PTNR_SHIP_TO_CATG_CD_01);
                } else if (sendPoInfoLineMap.get(PO_QLFY_CD) == PO_QLFY_CD_DS) {
                    ZYPEZDItemValueSetter.setValue(npaa001002.trdPtnrShipToCatgCd, TRD_PTNR_SHIP_TO_CATG_CD_05);
                } else {
                    ZYPEZDItemValueSetter.setValue(npaa001002.trdPtnrShipToCatgCd, TRD_PTNR_SHIP_TO_CATG_CD_06);
                }
                // *****************

                if (sendPoInfoLineMap.get(SHIP_TO_ACCT_NM) != null) {
                    if ((((String) sendPoInfoLineMap.get(SHIP_TO_ACCT_NM))).length() > TXT_LENGTH_35) {

                        // Ship To Location 1- 35
                        ZYPEZDItemValueSetter.setValue(npaa001002.shipToCustLocNm, ((String) sendPoInfoLineMap.get(SHIP_TO_ACCT_NM)).substring(0, TXT_LENGTH_35));

                        if ((((String) sendPoInfoLineMap.get(SHIP_TO_ACCT_NM))).length() > TXT_LENGTH_70) {

                            // START 2019/02/26 T.Ogura [QC#30518,MOD]
//                            ZYPEZDItemValueSetter.setValue(npaa001002.shipToCustLocNm, ((String) sendPoInfoLineMap.get(SHIP_TO_ACCT_NM)).substring(TXT_LENGTH_36, TXT_LENGTH_70));
                            ZYPEZDItemValueSetter.setValue(npaa001002.shipToCustLocNm, ((String) sendPoInfoLineMap.get(SHIP_TO_ACCT_NM)).substring(TXT_LENGTH_35, TXT_LENGTH_70));
                            // END   2019/02/26 T.Ogura [QC#30518,MOD]
                            msgMap.addXxMsgId(MSG_ID_NPAM1379W);

                        } else {

                            // START 2019/02/26 T.Ogura [QC#30518,MOD]
//                            ZYPEZDItemValueSetter.setValue(npaa001002.shipToCustLocNm, ((String) sendPoInfoLineMap.get(SHIP_TO_ACCT_NM)).substring(TXT_LENGTH_36, (((String) sendPoInfoLineMap.get(SHIP_TO_ACCT_NM))).length()));
                            ZYPEZDItemValueSetter.setValue(npaa001002.shipToCustLocNm, ((String) sendPoInfoLineMap.get(SHIP_TO_ACCT_NM)).substring(TXT_LENGTH_35, (((String) sendPoInfoLineMap.get(SHIP_TO_ACCT_NM))).length()));
                            // END   2019/02/26 T.Ogura [QC#30518,MOD]

                        }
                    }
                }

                ZYPEZDItemValueSetter.setValue(npaa001002.trdPtnrShipToCustCd, csaLocCd);

                String shipToLocNmTemp = (String) sendPoInfoLineMap.get(SHIP_TO_LOC_NM);
                String shipToAddlLocNm = (String) sendPoInfoLineMap.get(SHIP_TO_ADDL_LOC_NM);

                // Ship To Location 1- 35
                if (shipToLocNmTemp != null) {
                    if (shipToLocNmTemp.length() > TXT_LENGTH_35) {
                        shipToLocNmTemp = shipToLocNmTemp.substring(0, TXT_LENGTH_35);
                    }
                }

                if (shipToAddlLocNm != null) {
                    if (shipToAddlLocNm.length() > TXT_LENGTH_35) {
                        shipToAddlLocNm = shipToAddlLocNm.substring(0, TXT_LENGTH_35);
                    }
                }

                ZYPEZDItemValueSetter.setValue(npaa001002.shipToCustLocNm, shipToLocNmTemp);
                ZYPEZDItemValueSetter.setValue(npaa001002.shipToCustAddlLocNm, shipToAddlLocNm);

                // Ship To Address
                StringBuffer shipToFirstLineAddr = new StringBuffer();

                if (sendPoInfoLineMap.get(SHIP_TO_FIRST_LINE_ADDR) != null) {

                    shipToFirstLineAddr.append((String) sendPoInfoLineMap.get(SHIP_TO_FIRST_LINE_ADDR));
                    shipToFirstLineAddr.append(" ");
                }

                if (sendPoInfoLineMap.get(SHIP_TO_SCD_LINE_ADDR) != null) {

                    shipToFirstLineAddr.append((String) sendPoInfoLineMap.get(SHIP_TO_SCD_LINE_ADDR));
                    shipToFirstLineAddr.append(" ");
                }
                if (sendPoInfoLineMap.get(SHIP_TO_THIRD_LINE_ADDR) != null) {

                    shipToFirstLineAddr.append((String) sendPoInfoLineMap.get(SHIP_TO_THIRD_LINE_ADDR));
                    shipToFirstLineAddr.append(" ");
                }
                if (sendPoInfoLineMap.get(SHIP_TO_FRTH_LINE_ADDR) != null) {

                    shipToFirstLineAddr.append((String) sendPoInfoLineMap.get(SHIP_TO_FRTH_LINE_ADDR));
                    shipToFirstLineAddr.append(" ");
                }

                // QC:51557 Start
                String shipToFirstLineAddrStr = new String(shipToFirstLineAddr);
                shipToFirstLineAddrStr = shipToFirstLineAddrStr.replaceAll("\\r\\n|\\r|\\n|\\t", " ");

                if (shipToFirstLineAddrStr.length() > TXT_LENGTH_60) {

                    ZYPEZDItemValueSetter.setValue(npaa001002.shipToFirstLineAddr, shipToFirstLineAddrStr.substring(0, TXT_LENGTH_60));

                } else {

                    ZYPEZDItemValueSetter.setValue(npaa001002.shipToFirstLineAddr, new String(shipToFirstLineAddrStr));
                }
                // QC:51557 End

                // if (shipToFirstLineAddr.length() > TXT_LENGTH_35) {
                // ZYPEZDItemValueSetter.setValue(npaa001002.shipToFirstLineAddr,
                // (new String(shipToFirstLineAddr)).substring(0,
                // TXT_LENGTH_35));
                // if (shipToFirstLineAddr.length() > TXT_LENGTH_70) {
                // ZYPEZDItemValueSetter.setValue(npaa001002.shipToScdLineAddr,
                // (new
                // String(shipToFirstLineAddr)).substring(TXT_LENGTH_36,
                // TXT_LENGTH_70));
                // msgMap.addXxMsgId(MSG_ID_NPAM1379W);
                // } else {
                // ZYPEZDItemValueSetter.setValue(npaa001002.shipToScdLineAddr,
                // (new
                // String(shipToFirstLineAddr)).substring(TXT_LENGTH_36,
                // shipToFirstLineAddr.length()));
                // }
                // } else {
                // ZYPEZDItemValueSetter.setValue(npaa001002.shipToFirstLineAddr,
                // new String(shipToFirstLineAddr));
                // }

                if (sendPoInfoLineMap.get(CTAC_PSN_NM) != null) {

                    // START 2019/12/04 M.Naito [QC#54563-1,ADD]
                    convertCharacterForPoInfo(sendPoInfoLineMap);
                    // END 2019/12/04 M.Naito [QC#54563-1,ADD]
                    if (((String) sendPoInfoLineMap.get(CTAC_PSN_NM)).length() > TXT_LENGTH_60) {
                        ZYPEZDItemValueSetter.setValue(npaa001002.shipToScdLineAddr, ((String) sendPoInfoLineMap.get(CTAC_PSN_NM)).substring(0, TXT_LENGTH_60));
                    } else {
                        ZYPEZDItemValueSetter.setValue(npaa001002.shipToScdLineAddr, (String) sendPoInfoLineMap.get(CTAC_PSN_NM));
                    }
                }

                /**
                 *　Other Line Info
                 */
                ZYPEZDItemValueSetter.setValue(npaa001002.itrlIntfcId, param.itrlIntfcId.getValue());
                ZYPEZDItemValueSetter.setValue(npaa001002.itrlTrxSq, param.itrlTrxSq.getValue());
                ZYPEZDItemValueSetter.setValue(npaa001002.sendId, param.sendId.getValue());
                ZYPEZDItemValueSetter.setValue(npaa001002.rcvId, param.rcvId.getValue());
                ZYPEZDItemValueSetter.setValue(npaa001002.custEdiPoNum, param.poOrdNum.getValue());
                ZYPEZDItemValueSetter.setValue(npaa001002.custEdiPoDt, (String) sendPoInfoLineMap.get(PO_APVL_DT));
                ZYPEZDItemValueSetter.setValue(npaa001002.ediTrxPrpsCd, EDI_TRX_PRPS_CD_O);
                ZYPEZDItemValueSetter.setValue(npaa001002.ediPoLineNum, (String) sendPoInfoLineMap.get(PO_ORD_DTL_LINE_NUM));
                ZYPEZDItemValueSetter.setValue(npaa001002.ediPoSubLineNum, EDI_PO_SUB_LINE_NUM_DEFOULT);
                ZYPEZDItemValueSetter.setValue(npaa001002.rqstDelyDt, (String) sendPoInfoLineMap.get(ETA_DT));
                ZYPEZDItemValueSetter.setValue(npaa001002.cusaMdseCd, (String) sendPoInfoLineMap.get(ASL_MDSE_CD));
                ZYPEZDItemValueSetter.setValue(npaa001002.ordQty, (BigDecimal) sendPoInfoLineMap.get(PO_QTY));
                ZYPEZDItemValueSetter.setValue(npaa001002.uomCd, UOM_CD_EA);
                ZYPEZDItemValueSetter.setValue(npaa001002.unitPrcAmt, (BigDecimal) sendPoInfoLineMap.get(ENT_DEAL_NET_UNIT_PRC_AMT));
                ZYPEZDItemValueSetter.setValue(npaa001002.whCd, (String) sendPoInfoLineMap.get(VND_INVTY_LOC_CD));
                ZYPEZDItemValueSetter.setValue(npaa001002.trdPtnrShipToCustCd, csaLocCd);
                // START 2023/02/16 TZ.Win [QC#60966, ADD]
                ZYPEZDItemValueSetter.setValue(npaa001002.rqstShipDt, (String) sendPoInfoLineMap.get(RQST_SHIP_DT));
                // END 2023/02/16 TZ.Win [QC#60966, ADD]

                if (sendPoInfoLineMap.get(SHIP_TO_CTY_ADDR) != null) {
                    if (((String) sendPoInfoLineMap.get(SHIP_TO_CTY_ADDR)).length() > TXT_LENGTH_20) {
                        ZYPEZDItemValueSetter.setValue(npaa001002.shipToCustCtyAddr, ((String) sendPoInfoLineMap.get(SHIP_TO_CTY_ADDR)).substring(0, TXT_LENGTH_20));
                    } else {
                        ZYPEZDItemValueSetter.setValue(npaa001002.shipToCustCtyAddr, (String) sendPoInfoLineMap.get(SHIP_TO_CTY_ADDR));
                    }
                }

                ZYPEZDItemValueSetter.setValue(npaa001002.shipToCustStCd, (String) sendPoInfoLineMap.get(SHIP_TO_ST_CD));
                ZYPEZDItemValueSetter.setValue(npaa001002.shipToCustPostCd, (String) sendPoInfoLineMap.get(SHIP_TO_POST_CD));
                // QC#20246 UPD START
                ZYPEZDItemValueSetter.setValue(npaa001002.shipToCtacPsnNm, (String) sendPoInfoLineMap.get(CTAC_PSN_NM));
                // QC#20246 UPD END

                /**
                 *　IF Validation Check
                 */
                // QC# 25392 MOD START
                // isError = validationCheckWs(msgMap, npaa001001,
                // npaa001002);
                isError = validationCheckWs(msgMap, npaa001001, npaa001002, sendPoInfoLineMap);
                // QC# 25392 MOD END

                npaa001002TMsgList.add(npaa001002);

            }

            if (isError) {
                msgMap.flush();
                return;
            }

            /**
             *　IF INSERT
             */
            if (!createIfNPAA0010(msgMap, npaa001001, npaa001002TMsgList)) {
                msgMap.flush();
                return;

            }
        }

        /**************************************************************************
         * CANONY Parts
         **************************************************************************/
        else if (param.itrlIntfcId.getValue().equals(ITRL_INTFC_ID_NPAA0020)) {

            NPAA0020_01TMsg npaa002001 = new NPAA0020_01TMsg();

            boolean isError = false;

            List<NPAA0020_02TMsg> npaa002001TMsgList = new ArrayList<NPAA0020_02TMsg>();

            // EDI PO Type
            if (ZYPConstant.FLG_ON_Y.equals(dropShipFlg)) {
                ZYPEZDItemValueSetter.setValue(npaa002001.ediPoTpCd, EDI_PO_TP_CD_DS);
            } else {
                ZYPEZDItemValueSetter.setValue(npaa002001.ediPoTpCd, EDI_PO_TP_CD_SA);
            }

            // QC#23031 T.Hakodate MOD START
            // Choice EDI_PO_TP='DS'
            if (WH_OWNR.CHOICE.equals((String) poInfoMap.get("WH_OWNR_CD"))) {
                ZYPEZDItemValueSetter.setValue(npaa002001.ediPoTpCd, EDI_PO_TP_CD_DS);
                // QC#23814
                Map<String, Object> ssmParam = new HashMap<String, Object>();
                ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                ssmParam.put(BIND_VND_XREF_TP_CD, VND_XREF_TP_CD_1);
                ssmParam.put(BIND_EFF_FROM_DT, EFF_FROM_DATE);
                ssmParam.put(BIND_EFF_THRU_DT, EFF_THRU_DATE);
                ssmParam.put(BIND_SLS_DT, slsdt);

                // search1
                String dropShipWhCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_DROP_SHIP_WH_CD, param.glblCmpyCd.getValue());

                if (dropShipWhCd == null) {

                    dropShipWhCd = DROP_SHIP_WH_CD_DS;

                }

                ssmParam.put(BIND_RTL_WH_CD, dropShipWhCd);
                ssmParam.put(BIND_PRCH_GRP_CD, "ESS");

                csaLocCd = (String) ssmBatchClient.queryObject("searchVndShipToXrefRtl", ssmParam);

                if (!ZYPCommonFunc.hasValue(csaLocCd)) {

                    csaLocCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHOICE_DS_SHIP_TO_CUST_CD, param.glblCmpyCd.getValue());
                }

            }
            // QC#23031 T.Hakodate MOD END

            // QC#12300
            if (DS_PO_TP.PASS_THROUGH_PO.equals(dsPoTpCd)) {
                ZYPEZDItemValueSetter.setValue(npaa002001.ediPoTpCd, EDI_PO_TP_CD_PT);
            }

            // QC#12300
            if (DS_PO_TP.DO_NOT_TRANSMIT_PO.equals(dsPoTpCd)) {
                return;
            }

            /**
             * *Freight
             */
            // TRD_PTNR_SHPG_COND_XREF
            Map<String, Object> trdPtnrShpgCondCd = getTrdPtnrShpgCondCdInfo(poInfoMap, msgMap);

            if (trdPtnrShpgCondCd != null) {

                ZYPEZDItemValueSetter.setValue(npaa002001.shpgCondCd, (String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD));
            }

            // TRD_PTNR_SHPG_X_REF

            // QC#26006 MOD START
            Map<String, Object> trdPtnrShpgXRefMap = new HashMap<String, Object>();
            trdPtnrShpgXRefMap = getTrdPtnrShpgXRefInfo(poInfoMap, msgMap);
            if (trdPtnrShpgXRefMap != null) {
                trdPtnrCarrCd = (String) trdPtnrShpgXRefMap.get(TRD_PTNR_CARR_CD);
                trdPtnrShpgSvcLvlCd = (String) trdPtnrShpgXRefMap.get(TRD_PTNR_SHPG_SVC_LVL_CD);
                trdPtnrShipViaCd = (String) trdPtnrShpgXRefMap.get(TRD_PTNR_SHIP_VIA_CD);
                trdPtnrFrtChegMethCd = (String) trdPtnrShpgXRefMap.get(TRD_PTNR_FRT_CHRG_METH_CD);
            }
            // QC#26006 MOD END

            ZYPEZDItemValueSetter.setValue(npaa002001.carrCd, trdPtnrCarrCd);
            ZYPEZDItemValueSetter.setValue(npaa002001.shpgSvcLvlCd, trdPtnrShpgSvcLvlCd);
            ZYPEZDItemValueSetter.setValue(npaa002001.shipViaCd, trdPtnrShipViaCd);
            ZYPEZDItemValueSetter.setValue(npaa002001.ediFrtChrgMethCd, trdPtnrFrtChegMethCd);

            /**
             *　Other Header Info
             */
            ZYPEZDItemValueSetter.setValue(npaa002001.itrlIntfcId, param.itrlIntfcId.getValue());
            ZYPEZDItemValueSetter.setValue(npaa002001.itrlTrxSq, param.itrlTrxSq.getValue());
            ZYPEZDItemValueSetter.setValue(npaa002001.sendId, param.sendId.getValue());
            ZYPEZDItemValueSetter.setValue(npaa002001.rcvId, param.rcvId.getValue());
            ZYPEZDItemValueSetter.setValue(npaa002001.custEdiPoNum, param.poOrdNum.getValue());
            ZYPEZDItemValueSetter.setValue(npaa002001.custEdiPoDt, (String) poInfoMap.get(PO_APVL_DT));
            ZYPEZDItemValueSetter.setValue(npaa002001.ediTrxPrpsCd, EDI_TRX_PRPS_CD_O);
            ZYPEZDItemValueSetter.setValue(npaa002001.poRcvTs, ZYPDateUtil.getCurrentSystemTime(TIMESTANP));
            // QC#20246 UPD START
            // QC#24732
            if (poInfoMap.get(CTAC_PSN_NM) != null) {
                // START 2019/12/04 M.Naito [QC#54563-1,ADD]
                convertCharacterForPoInfo(poInfoMap);
                // END 2019/12/04 M.Naito [QC#54563-1,ADD]
                if (((String) poInfoMap.get(CTAC_PSN_NM)).length() > TXT_LENGTH_30) {
                    ZYPEZDItemValueSetter.setValue(npaa002001.firstRefTxt, ((String) poInfoMap.get(CTAC_PSN_NM)).substring(0, TXT_LENGTH_30));
                } else {
                    ZYPEZDItemValueSetter.setValue(npaa002001.firstRefTxt, (String) poInfoMap.get(CTAC_PSN_NM));
                }
            }
            // QC#20246 UPD END
            npaa002001.scdRefTxt.clear();

            // QC#14749
            setMsgParts(msgMap, poInfoMap, npaa002001);

            ZYPEZDItemValueSetter.setValue(npaa002001.custCpoOrdNum, (String) poInfoMap.get(CPO_ORD_NUM));
            ZYPEZDItemValueSetter.setValue(npaa002001.endCustIssPoNum, (String) poInfoMap.get(CUST_ISS_PO_NUM));
            // ZYPEZDItemValueSetter.setValue(npaa002001.carrAcctNum,
            // (String) poInfoMap.get(CARR_ACCT_NUM));
            ZYPEZDItemValueSetter.setValue(npaa002001.rtrnShipToRtlWhCd, (String) poInfoMap.get(RTRN_SHIP_TO_RTL_WH_CD));
            ZYPEZDItemValueSetter.setValue(npaa002001.vndPmtTermDescTxt, (String) poInfoMap.get(VND_PMT_TERM_DESC_TXT));

            // QC#23356 Mod Start
            // Carrier Account Num
            String carrAcctNum = null;

            if (poInfoMap.get(CARR_ACCT_NUM) == null) {
                carrAcctNum = getCarrAcctNum(poInfoMap, msgMap);

                // QC#27628 mod start
                if (ZYPCommonFunc.hasValue(carrAcctNum)) {
                    carrAcctNumFromXref = true;
                }
                // QC#27628 mod end
            } else {
                carrAcctNum = (String) poInfoMap.get(CARR_ACCT_NUM);
            }
            // CARR_ACCT_NUM
            ZYPEZDItemValueSetter.setValue(npaa002001.carrAcctNum, carrAcctNum);
            // QC#23356 Mod End

            /**
             * sendPoInfoList Loop NPAA0020_02Array
             */
            for (int i = 0; i < sendPoInfoList.size(); i++) {

                Map<String, Object> sendPoInfoLineMap = sendPoInfoList.get(i);

                NPAA0020_02TMsg npaa002002 = new NPAA0020_02TMsg();

                // Trading Partner SKU Code
                if (sendPoInfoLineMap.get(CUST_MDSE_CD) != null) {
                    ZYPEZDItemValueSetter.setValue(npaa002002.trdPtnrSkuCd, (String) sendPoInfoLineMap.get(CUST_MDSE_CD));

                } else {

                    if (sendPoInfoLineMap.get(MDSE_CD) != null) {
                        ZYPEZDItemValueSetter.setValue(npaa002002.trdPtnrSkuCd, (String) sendPoInfoLineMap.get(MDSE_CD));
                    }
                }

                // Trading Partner Ship To Category Code

                // QC#23031 T.Hakodate MOD START
                // Choice TRD_PTNR_SHIP_TO_CATG_CD_02
                if (WH_OWNR.CHOICE.equals((String) poInfoMap.get("WH_OWNR_CD"))) {
                    ZYPEZDItemValueSetter.setValue(npaa002002.trdPtnrShipToCatgCd, TRD_PTNR_SHIP_TO_CATG_CD_02);
                } else if (sendPoInfoLineMap.get(PO_QLFY_CD) == PO_QLFY_CD_PC) {
                    ZYPEZDItemValueSetter.setValue(npaa002002.trdPtnrShipToCatgCd, TRD_PTNR_SHIP_TO_CATG_CD_01);
                } else if (sendPoInfoLineMap.get(PO_QLFY_CD) == PO_QLFY_CD_DS) {
                    ZYPEZDItemValueSetter.setValue(npaa002002.trdPtnrShipToCatgCd, TRD_PTNR_SHIP_TO_CATG_CD_05);
                } else {
                    ZYPEZDItemValueSetter.setValue(npaa002002.trdPtnrShipToCatgCd, TRD_PTNR_SHIP_TO_CATG_CD_06);
                }
                // QC#23031 T.Hakodate MOD END

                if (sendPoInfoLineMap.get(SHIP_TO_ACCT_NM) != null) {

                    if (((String) sendPoInfoLineMap.get(SHIP_TO_ACCT_NM)).length() > TXT_LENGTH_35) {

                        // Ship To Location 1- 35
                        ZYPEZDItemValueSetter.setValue(npaa002002.shipToCustLocNm, ((String) sendPoInfoLineMap.get(SHIP_TO_ACCT_NM)).substring(0, TXT_LENGTH_35));

                        if (((String) sendPoInfoLineMap.get(SHIP_TO_ACCT_NM)).length() > TXT_LENGTH_70) {

                            // Ship To Location 36 - 70
                            // START 2019/02/26 T.Ogura [QC#30518,MOD]
//                            ZYPEZDItemValueSetter.setValue(npaa002002.shipToCustAddlLocNm, ((String) sendPoInfoLineMap.get(SHIP_TO_ACCT_NM)).substring(TXT_LENGTH_36, TXT_LENGTH_70));
                            ZYPEZDItemValueSetter.setValue(npaa002002.shipToCustAddlLocNm, ((String) sendPoInfoLineMap.get(SHIP_TO_ACCT_NM)).substring(TXT_LENGTH_35, TXT_LENGTH_70));
                            // END   2019/02/26 T.Ogura [QC#30518,MOD]
                        } else {
                            // START 2019/02/26 T.Ogura [QC#30518,MOD]
//                            ZYPEZDItemValueSetter.setValue(npaa002002.shipToCustAddlLocNm, ((String) sendPoInfoLineMap.get(SHIP_TO_ACCT_NM)).substring(TXT_LENGTH_36, ((String) sendPoInfoLineMap.get(SHIP_TO_ACCT_NM)).length()));
                            ZYPEZDItemValueSetter.setValue(npaa002002.shipToCustAddlLocNm, ((String) sendPoInfoLineMap.get(SHIP_TO_ACCT_NM)).substring(TXT_LENGTH_35, ((String) sendPoInfoLineMap.get(SHIP_TO_ACCT_NM)).length()));
                            // END   2019/02/26 T.Ogura [QC#30518,MOD]
                        }
                    }

                    if (((String) sendPoInfoLineMap.get(SHIP_TO_ACCT_NM)).getBytes().length > TXT_LENGTH_70) {
                        msgMap.addXxMsgId(MSG_ID_NPAM1379W);
                    }
                }

                ZYPEZDItemValueSetter.setValue(npaa002002.trdPtnrShipToCustCd, csaLocCd);

                if (sendPoInfoLineMap.get(SHIP_TO_LOC_NM) != null) {
                    if (((String) sendPoInfoLineMap.get(SHIP_TO_LOC_NM)).length() > TXT_LENGTH_35) {
                        // Ship To Location 1- 35
                        ZYPEZDItemValueSetter.setValue(npaa002002.shipToCustLocNm, ((String) sendPoInfoLineMap.get(SHIP_TO_LOC_NM)).substring(0, TXT_LENGTH_35));
                    } else {
                        ZYPEZDItemValueSetter.setValue(npaa002002.shipToCustLocNm, ((String) sendPoInfoLineMap.get(SHIP_TO_LOC_NM)));
                    }
                }

                if (sendPoInfoLineMap.get(SHIP_TO_ADDL_LOC_NM) != null) {

                    if (((String) sendPoInfoLineMap.get(SHIP_TO_ADDL_LOC_NM)).length() > TXT_LENGTH_35) {
                        // Ship To Location 1- 35
                        ZYPEZDItemValueSetter.setValue(npaa002002.shipToCustAddlLocNm, ((String) sendPoInfoLineMap.get(SHIP_TO_ADDL_LOC_NM)).substring(0, TXT_LENGTH_35));
                    } else {
                        ZYPEZDItemValueSetter.setValue(npaa002002.shipToCustAddlLocNm, ((String) sendPoInfoLineMap.get(SHIP_TO_ADDL_LOC_NM)));
                    }
                }

                // QC#26105 Start
                if (sendPoInfoLineMap.get(CTAC_PSN_NM) != null) {

                    // START 2019/12/04 M.Naito [QC#54563-1,ADD]
                    convertCharacterForPoInfo(sendPoInfoLineMap);
                    // END 2019/12/04 M.Naito [QC#54563-1,ADD]
                    if (((String) sendPoInfoLineMap.get(CTAC_PSN_NM)).length() > TXT_LENGTH_35) {
                        // Ship To Location 1- 35
                        ZYPEZDItemValueSetter.setValue(npaa002002.shipToCustAddlLocNm, ((String) sendPoInfoLineMap.get(CTAC_PSN_NM)).substring(0, TXT_LENGTH_35));
                    } else {
                        ZYPEZDItemValueSetter.setValue(npaa002002.shipToCustAddlLocNm, ((String) sendPoInfoLineMap.get(CTAC_PSN_NM)));
                    }
                }
                // QC#26105 End

                // START 2019/04/11 T.Ogura [QC#31041,DEL]
//                // Ship To Address
//                StringBuffer shipToFirstLineAddr = new StringBuffer();
//
//                if (sendPoInfoLineMap.get(SHIP_TO_FIRST_LINE_ADDR) != null) {
//
//                    shipToFirstLineAddr.append((String) sendPoInfoLineMap.get(SHIP_TO_FIRST_LINE_ADDR));
//                    shipToFirstLineAddr.append(" ");
//                }
//
//                if (sendPoInfoLineMap.get(SHIP_TO_SCD_LINE_ADDR) != null) {
//
//                    shipToFirstLineAddr.append((String) sendPoInfoLineMap.get(SHIP_TO_SCD_LINE_ADDR));
//                    shipToFirstLineAddr.append(" ");
//                }
//                if (sendPoInfoLineMap.get(SHIP_TO_THIRD_LINE_ADDR) != null) {
//
//                    shipToFirstLineAddr.append((String) sendPoInfoLineMap.get(SHIP_TO_THIRD_LINE_ADDR));
//                    shipToFirstLineAddr.append(" ");
//                }
//                if (sendPoInfoLineMap.get(SHIP_TO_FRTH_LINE_ADDR) != null) {
//
//                    shipToFirstLineAddr.append((String) sendPoInfoLineMap.get(SHIP_TO_FRTH_LINE_ADDR));
//                    shipToFirstLineAddr.append(" ");
//                }
                // END   2019/04/11 T.Ogura [QC#31041,DEL]

                // if (shipToFirstLineAddr.length() > TXT_LENGTH_60) {
                //
                // ZYPEZDItemValueSetter.setValue(npaa002002.shipToFirstLineAddr,
                // (String) shipToFirstLineAddr.substring(0,
                // TXT_LENGTH_60));
                //
                // msgMap.addXxMsgId(MSG_ID_NPAM1379W);
                //
                // } else {
                //
                // ZYPEZDItemValueSetter.setValue(npaa002002.shipToFirstLineAddr,
                // new String(shipToFirstLineAddr));
                // }

                // if (sendPoInfoLineMap.get(CTAC_PSN_NM) != null) {
                //
                // if (((String)
                // sendPoInfoLineMap.get(CTAC_PSN_NM)).length() >
                // TXT_LENGTH_60) {
                // ZYPEZDItemValueSetter.setValue(npaa002002.shipToScdLineAddr,
                // ((String)
                // sendPoInfoLineMap.get(CTAC_PSN_NM)).substring(0,
                // TXT_LENGTH_60));
                // } else {
                // ZYPEZDItemValueSetter.setValue(npaa002002.shipToScdLineAddr,
                // (String) sendPoInfoLineMap.get(CTAC_PSN_NM));
                // }
                // }

                // START 2019/04/11 T.Ogura [QC#31041,DEL]
//                if (shipToFirstLineAddr.length() > TXT_LENGTH_35) {
//
//                    ZYPEZDItemValueSetter.setValue(npaa002002.shipToFirstLineAddr, (new String(shipToFirstLineAddr)).substring(0, TXT_LENGTH_35));
//
//                    if (shipToFirstLineAddr.length() > TXT_LENGTH_70) {
//
//                        // START 2019/02/26 T.Ogura [QC#30518,MOD]
////                        ZYPEZDItemValueSetter.setValue(npaa002002.shipToScdLineAddr, (new String(shipToFirstLineAddr)).substring(TXT_LENGTH_36, TXT_LENGTH_70));
//                        ZYPEZDItemValueSetter.setValue(npaa002002.shipToScdLineAddr, (new String(shipToFirstLineAddr)).substring(TXT_LENGTH_35, TXT_LENGTH_70));
//                        // END   2019/02/26 T.Ogura [QC#30518,MOD]
//                        msgMap.addXxMsgId(MSG_ID_NPAM1379W);
//
//                    } else {
//
//                        // START 2019/02/26 T.Ogura [QC#30518,MOD]
////                        ZYPEZDItemValueSetter.setValue(npaa002002.shipToScdLineAddr, (new String(shipToFirstLineAddr)).substring(TXT_LENGTH_36, shipToFirstLineAddr.length()));
//                        ZYPEZDItemValueSetter.setValue(npaa002002.shipToScdLineAddr, (new String(shipToFirstLineAddr)).substring(TXT_LENGTH_35, shipToFirstLineAddr.length()));
//                        // END   2019/02/26 T.Ogura [QC#30518,MOD]
//
//                    }
//
//                } else {
//
//                    ZYPEZDItemValueSetter.setValue(npaa002002.shipToFirstLineAddr, new String(shipToFirstLineAddr));
//
//                }
                // END   2019/04/11 T.Ogura [QC#31041,DEL]

                // START 2019/04/11 T.Ogura [QC#31041,ADD]
                // Ship To Address
                String shipToFirstLineAddr = (String) sendPoInfoLineMap.get(SHIP_TO_FIRST_LINE_ADDR);
                String shipToScdLineAddr = (String) sendPoInfoLineMap.get(SHIP_TO_SCD_LINE_ADDR);

                if (ZYPCommonFunc.hasValue(shipToFirstLineAddr) && ZYPCommonFunc.hasValue(shipToScdLineAddr)
                        && shipToFirstLineAddr.length() <= TXT_LENGTH_35 && shipToScdLineAddr.length() <= TXT_LENGTH_35) {

                    ZYPEZDItemValueSetter.setValue(npaa002002.shipToFirstLineAddr, shipToFirstLineAddr);
                    ZYPEZDItemValueSetter.setValue(npaa002002.shipToScdLineAddr, shipToScdLineAddr);

                } else {

                    StringBuilder shipToAllLineAddr = new StringBuilder();

                    if (ZYPCommonFunc.hasValue(shipToFirstLineAddr)) {

                        shipToAllLineAddr.append(shipToFirstLineAddr);
                    }

                    if (ZYPCommonFunc.hasValue(shipToScdLineAddr)) {

                        shipToAllLineAddr.append(" ");
                        shipToAllLineAddr.append(shipToScdLineAddr);
                    }

                    int numBeginIdx = -1;
                    String shipToLineAddr = new String(shipToAllLineAddr);
                    String workAddr = "";

                    // Get position where number first appears
                    Matcher m = Pattern.compile("[0-9]").matcher(shipToLineAddr);
                    while (m.find()) {
                        numBeginIdx = m.start();
                        break;
                    }

                    // QC:51557 Start
                    shipToLineAddr = shipToLineAddr.replaceAll("\\r\\n|\\r|\\n|\\t", " ");
                    // QC:51557 End

                    if (shipToLineAddr.length() > TXT_LENGTH_35) {

                        if (numBeginIdx > 0 && numBeginIdx < TXT_LENGTH_35) {

                            ZYPEZDItemValueSetter.setValue(npaa002002.shipToFirstLineAddr, shipToLineAddr.substring(0, numBeginIdx));
                            workAddr = shipToLineAddr.substring(numBeginIdx);

                            if (workAddr.length() > TXT_LENGTH_35) {
                                ZYPEZDItemValueSetter.setValue(npaa002002.shipToScdLineAddr, workAddr.substring(0, TXT_LENGTH_35));
                            } else {
                                ZYPEZDItemValueSetter.setValue(npaa002002.shipToScdLineAddr, workAddr);
                            }

                        } else {

                            ZYPEZDItemValueSetter.setValue(npaa002002.shipToFirstLineAddr, shipToLineAddr.substring(0, TXT_LENGTH_35));
                            workAddr = shipToLineAddr.substring(TXT_LENGTH_35);

                            if (workAddr.length() > TXT_LENGTH_35) {
                                ZYPEZDItemValueSetter.setValue(npaa002002.shipToScdLineAddr, workAddr.substring(0, TXT_LENGTH_35));
                            } else {
                                ZYPEZDItemValueSetter.setValue(npaa002002.shipToScdLineAddr, workAddr);
                            }
                        }
                    } else {
                        ZYPEZDItemValueSetter.setValue(npaa002002.shipToFirstLineAddr, shipToLineAddr);
                    }
                }
                // END   2019/04/11 T.Ogura [QC#31041,ADD]

                /**
                 *　Other Line Info
                 */

                ZYPEZDItemValueSetter.setValue(npaa002002.itrlIntfcId, param.itrlIntfcId.getValue());
                ZYPEZDItemValueSetter.setValue(npaa002002.itrlTrxSq, param.itrlTrxSq.getValue());
                ZYPEZDItemValueSetter.setValue(npaa002002.sendId, param.sendId.getValue());
                ZYPEZDItemValueSetter.setValue(npaa002002.rcvId, param.rcvId.getValue());
                ZYPEZDItemValueSetter.setValue(npaa002002.custEdiPoNum, param.poOrdNum.getValue());
                ZYPEZDItemValueSetter.setValue(npaa002002.custEdiPoDt, (String) sendPoInfoLineMap.get(PO_APVL_DT));
                ZYPEZDItemValueSetter.setValue(npaa002002.ediTrxPrpsCd, EDI_TRX_PRPS_CD_O);
                ZYPEZDItemValueSetter.setValue(npaa002002.ediPoLineNum, (String) sendPoInfoLineMap.get(PO_ORD_DTL_LINE_NUM));
                ZYPEZDItemValueSetter.setValue(npaa002002.ediPoSubLineNum, EDI_PO_SUB_LINE_NUM_DEFOULT);
                ZYPEZDItemValueSetter.setValue(npaa002002.rqstDelyDt, (String) sendPoInfoLineMap.get(ETA_DT));
                ZYPEZDItemValueSetter.setValue(npaa002002.cusaMdseCd, (String) sendPoInfoLineMap.get(ASL_MDSE_CD));
                ZYPEZDItemValueSetter.setValue(npaa002002.ordQty, (BigDecimal) sendPoInfoLineMap.get(PO_QTY));
                ZYPEZDItemValueSetter.setValue(npaa002002.uomCd, UOM_CD_EA);
                ZYPEZDItemValueSetter.setValue(npaa002002.unitPrcAmt, (BigDecimal) sendPoInfoLineMap.get(ENT_DEAL_NET_UNIT_PRC_AMT));
                ZYPEZDItemValueSetter.setValue(npaa002002.whCd, (String) sendPoInfoLineMap.get(VND_INVTY_LOC_CD));
                ZYPEZDItemValueSetter.setValue(npaa002002.trdPtnrShipToCustCd, csaLocCd);
                if (sendPoInfoLineMap.get(SHIP_TO_CTY_ADDR) != null) {
                    if (((String) sendPoInfoLineMap.get(SHIP_TO_CTY_ADDR)).length() > TXT_LENGTH_20) {
                        ZYPEZDItemValueSetter.setValue(npaa002002.shipToCustCtyAddr, ((String) sendPoInfoLineMap.get(SHIP_TO_CTY_ADDR)).substring(0, TXT_LENGTH_20));
                    } else {
                        ZYPEZDItemValueSetter.setValue(npaa002002.shipToCustCtyAddr, (String) sendPoInfoLineMap.get(SHIP_TO_CTY_ADDR));
                    }
                }
                ZYPEZDItemValueSetter.setValue(npaa002002.shipToCustStCd, (String) sendPoInfoLineMap.get(SHIP_TO_ST_CD));
                ZYPEZDItemValueSetter.setValue(npaa002002.shipToCustPostCd, (String) sendPoInfoLineMap.get(SHIP_TO_POST_CD));
                // QC#20246 UPD START
                ZYPEZDItemValueSetter.setValue(npaa002002.shipToCtacPsnNm, (String) sendPoInfoLineMap.get(CTAC_PSN_NM));
                // QC#20246 UPD END
                /**
                 *　IF Validation Check
                 */

                // QC# 25392 MOD START
                // isError = validationCheckParts(msgMap, npaa002001,
                // npaa002002);
                isError = validationCheckParts(msgMap, npaa002001, npaa002002, sendPoInfoLineMap);
                // QC# 25392 MOD END

                npaa002001TMsgList.add(npaa002002);

            }

            if (isError) {
                msgMap.flush();
                return;
            }

            /**
             *　IF INSERT
             */
            if (!createIfNPAA0020(msgMap, npaa002001, npaa002001TMsgList)) {
                msgMap.flush();
                return;
            }

        } else {
            S21InfoLogOutput.printlnv(MSG_ID_NPAM1391E, param.poOrdNum.getValue());
            msgMap.addXxMsgId(MSG_ID_NPAM1391E);
            msgMap.flush();
            return;
        }

        msgMap.flush();
        return;

    }

    /**
     * checkParam
     * @param msgMap
     * @param onBatchType
     * @return
     */
    private boolean checkParam(S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {

        EZDDebugOutput.println(1, PROGRAM_ID + "[ checkParam ] start", this);

        NPZC132001PMsg param = (NPZC132001PMsg) msgMap.getPmsg();

        boolean returnValue = true;

        if (!ZYPCommonFunc.hasValue(param.glblCmpyCd)) {
            S21InfoLogOutput.println(MSG_ID_NSXM0001E, new String[] {ERR_GLBL_CMPY_CD });
            msgMap.addXxMsgId(MSG_ID_NSXM0001E);
            returnValue = false;
        }

        cusaGlblCmpyCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CUSA_GLBL_CMPY_CD, param.glblCmpyCd.getValue());

        if (!ZYPCommonFunc.hasValue(cusaGlblCmpyCd)) {

            S21InfoLogOutput.println(MSG_ID_NPAM1380E, new String[] {ERR_ITRL_INTFC_ID });
            msgMap.addXxMsgId(MSG_ID_NPAM1482E);
            returnValue = false;
        }

        slsdt = ZYPDateUtil.getSalesDate(param.glblCmpyCd.getValue());

        if (!ZYPCommonFunc.hasValue(param.itrlIntfcId)) {
            S21InfoLogOutput.println(MSG_ID_NPAM1380E, new String[] {ERR_ITRL_INTFC_ID });
            msgMap.addXxMsgId(MSG_ID_NPAM1380E);

            returnValue = false;
        }

        if (!ZYPCommonFunc.hasValue(param.itrlTrxSq)) {
            S21InfoLogOutput.println(MSG_ID_NPAM1382E, new String[] {ERR_ITRL_TRX_SQ });
            msgMap.addXxMsgId(MSG_ID_NPAM1382E);
            returnValue = false;
        }

        if (!ZYPCommonFunc.hasValue(param.sendId)) {
            S21InfoLogOutput.println(MSG_ID_NPAM1381E, new String[] {ERR_SEND_ID });
            msgMap.addXxMsgId(MSG_ID_NPAM1381E);
            returnValue = false;
        }

        if (!ZYPCommonFunc.hasValue(param.rcvId)) {
            S21InfoLogOutput.println(MSG_ID_NPAM1383E, new String[] {ERR_RCV_ID });
            msgMap.addXxMsgId(MSG_ID_NPAM1383E);
            returnValue = false;
        }

        if (!ZYPCommonFunc.hasValue(param.poOrdNum)) {
            S21InfoLogOutput.println(MSG_ID_NPAM1384E, new String[] {ERR_PO_ORD_NUM });
            msgMap.addXxMsgId(MSG_ID_NPAM1384E);
            returnValue = false;
        }

        return returnValue;
    }

    /**
     * checkIfId
     * @param msgMap
     * @param onBatchType
     * @return
     */
    private boolean checkIfId(S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {

        EZDDebugOutput.println(1, PROGRAM_ID + "[ checkIfId ] start", this);

        NPZC132001PMsg param = (NPZC132001PMsg) msgMap.getPmsg();

        boolean returnValue = true;

        final String intfcId = param.itrlIntfcId.getValue();

        if (!intfcId.equals(ITRL_INTFC_ID_NPAA0010) && !intfcId.equals(ITRL_INTFC_ID_NPAA0020)) {
            S21InfoLogOutput.println(MSG_ID_NPAM1168E);
            msgMap.addXxMsgId(MSG_ID_NPAM1168E);
            returnValue = false;
        }

        return returnValue;

    }

    /**
     * sendPoInfo
     * @param msgMap
     * @param onBatchType
     * @return
     */
    private List<Map<String, Object>> sendPoInfo(S21ApiMessageMap msgMap, final ONBATCH_TYPE onBatchType) {

        NPZC132001PMsg param = (NPZC132001PMsg) msgMap.getPmsg();

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
        ssmParam.put(BIND_PO_ORD_NUM, param.poOrdNum.getValue());
        ssmParam.put(BIND_PO_STS_CD, PO_STS.VALIDATED);
        ssmParam.put(BIND_PO_MSG_TP_CD, PO_MSG_TP.SPECIAL_INSTRUCTIONS);
        ssmParam.put(BIND_PO_MDSE_CMPSN_TP_CD, MDSE_CMPSN_TP.SET_ORDERTAKE_MDSE);
        ssmParam.put(BIND_PO_MSG_SEG_ID, PO_MSG_SEG_ID);

        return ssmBatchClient.queryObjectList("sendPoInfo", ssmParam);
    }

    /**
     * getTrdPtnrShpgCondCdInfo
     * @param poInfoMap
     * @param msgMap
     * @return
     */
    private Map<String, Object> getTrdPtnrShpgCondCdInfo(Map poInfoMap, S21ApiMessageMap msgMap) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        NPZC132001PMsg param = (NPZC132001PMsg) msgMap.getPmsg();

        // QC#50026
        if (poInfoMap.get(DS_PO_TP_CD) != null && poInfoMap.get(SHPG_SVC_LVL_CD) != null && poInfoMap.get(CARR_CD) != null) {

            ssmParam.clear();

            // search1
            ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
            ssmParam.put(BIND_DS_PO_TP_CD, poInfoMap.get(DS_PO_TP_CD));
            ssmParam.put(BIND_SHPG_SVC_LVL_CD, poInfoMap.get(SHPG_SVC_LVL_CD));
            ssmParam.put(BIND_CARR_CD, poInfoMap.get(CARR_CD));

            Map<String, Object> trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

            if (trdPtnrShpgCondCd != null && trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD) == null) {
                ssmParam.clear();

                // QC#53395. search2
                ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                ssmParam.put(BIND_DS_PO_TP_CD, poInfoMap.get(DS_PO_TP_CD));
                ssmParam.put(BIND_CARR_CD, poInfoMap.get(CARR_CD));

                trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                if (trdPtnrShpgCondCd != null && trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD) == null) {
                    ssmParam.clear();

                    // search3
                    ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                    ssmParam.put(BIND_DS_PO_TP_CD, poInfoMap.get(DS_PO_TP_CD));
                    ssmParam.put(BIND_SHPG_SVC_LVL_CD, poInfoMap.get(SHPG_SVC_LVL_CD));

                    trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                    if (trdPtnrShpgCondCd != null && trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD) == null) {

                        ssmParam.clear();

                        // search4
                        ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                        ssmParam.put(BIND_DS_PO_TP_CD, poInfoMap.get(DS_PO_TP_CD));

                        trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                        if (trdPtnrShpgCondCd != null && trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD) == null) {

                            ssmParam.clear();

                            // search5
                            ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                            ssmParam.put(BIND_SHPG_SVC_LVL_CD, poInfoMap.get(SHPG_SVC_LVL_CD));

                            trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                            if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                                return trdPtnrShpgCondCd;

                            } else {

                                ssmParam.clear();

                                // search6
                                ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());

                                trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                                return trdPtnrShpgCondCd;

                            }
                        } else {

                            if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                                return trdPtnrShpgCondCd;
                            }

                            ssmParam.clear();

                            // search5
                            ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                            ssmParam.put(BIND_SHPG_SVC_LVL_CD, poInfoMap.get(SHPG_SVC_LVL_CD));

                            trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                            if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                                return trdPtnrShpgCondCd;

                            } else {

                                ssmParam.clear();

                                // search6
                                ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());

                                trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                                return trdPtnrShpgCondCd;

                            }
                        }
                    } else {

                        if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                            return trdPtnrShpgCondCd;
                        }

                        ssmParam.clear();

                        // search4
                        ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                        ssmParam.put(BIND_DS_PO_TP_CD, poInfoMap.get(DS_PO_TP_CD));

                        trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                        if (trdPtnrShpgCondCd != null && trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD) == null) {

                            ssmParam.clear();

                            // search5
                            ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                            ssmParam.put(BIND_SHPG_SVC_LVL_CD, poInfoMap.get(SHPG_SVC_LVL_CD));

                            trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                            if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                                return trdPtnrShpgCondCd;

                            } else {

                                ssmParam.clear();

                                // search6
                                ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());

                                trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                                return trdPtnrShpgCondCd;

                            }
                        } else {

                            if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                                return trdPtnrShpgCondCd;
                            }

                            ssmParam.clear();

                            // search5
                            ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                            ssmParam.put(BIND_SHPG_SVC_LVL_CD, poInfoMap.get(SHPG_SVC_LVL_CD));

                            trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                            if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                                return trdPtnrShpgCondCd;

                            } else {

                                ssmParam.clear();

                                // search6
                                ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());

                                trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                                return trdPtnrShpgCondCd;
                            }
                        }
                    }

                } else {

                    if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                        return trdPtnrShpgCondCd;
                    }
                    ssmParam.clear();

                    // search3
                    ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                    ssmParam.put(BIND_DS_PO_TP_CD, poInfoMap.get(DS_PO_TP_CD));
                    ssmParam.put(BIND_SHPG_SVC_LVL_CD, poInfoMap.get(SHPG_SVC_LVL_CD));

                    trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                    if (trdPtnrShpgCondCd != null && trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD) == null) {

                        ssmParam.clear();

                        // search4
                        ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                        ssmParam.put(BIND_DS_PO_TP_CD, poInfoMap.get(DS_PO_TP_CD));

                        trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                        if (trdPtnrShpgCondCd != null && trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD) == null) {

                            ssmParam.clear();

                            // search5
                            ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                            ssmParam.put(BIND_SHPG_SVC_LVL_CD, poInfoMap.get(SHPG_SVC_LVL_CD));

                            trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                            if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                                return trdPtnrShpgCondCd;

                            } else {

                                ssmParam.clear();

                                // search6
                                ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());

                                trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                                return trdPtnrShpgCondCd;

                            }
                        } else {

                            if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                                return trdPtnrShpgCondCd;
                            }

                            ssmParam.clear();

                            // search5
                            ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                            ssmParam.put(BIND_SHPG_SVC_LVL_CD, poInfoMap.get(SHPG_SVC_LVL_CD));

                            trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                            if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                                return trdPtnrShpgCondCd;

                            } else {

                                ssmParam.clear();

                                // search6
                                ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());

                                trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                                return trdPtnrShpgCondCd;

                            }
                        }
                    } else {

                        if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                            return trdPtnrShpgCondCd;
                        }

                        ssmParam.clear();

                        // search4
                        ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                        ssmParam.put(BIND_DS_PO_TP_CD, poInfoMap.get(DS_PO_TP_CD));

                        trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                        if (trdPtnrShpgCondCd != null && trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD) == null) {

                            ssmParam.clear();

                            // search5
                            ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                            ssmParam.put(BIND_SHPG_SVC_LVL_CD, poInfoMap.get(SHPG_SVC_LVL_CD));

                            trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                            if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                                return trdPtnrShpgCondCd;

                            } else {

                                ssmParam.clear();

                                // search6
                                ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());

                                trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                                return trdPtnrShpgCondCd;

                            }
                        } else {

                            if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                                return trdPtnrShpgCondCd;
                            }

                            ssmParam.clear();

                            // search5
                            ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                            ssmParam.put(BIND_SHPG_SVC_LVL_CD, poInfoMap.get(SHPG_SVC_LVL_CD));

                            trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                            if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                                return trdPtnrShpgCondCd;

                            } else {

                                ssmParam.clear();

                                // search6
                                ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());

                                trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                                return trdPtnrShpgCondCd;

                            }

                        }

                    }

                }

            } else {

                if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                    return trdPtnrShpgCondCd;
                }

                ssmParam.clear();

                // QC#53395. search2
                ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                ssmParam.put(BIND_DS_PO_TP_CD, poInfoMap.get(DS_PO_TP_CD));
                ssmParam.put(BIND_CARR_CD, poInfoMap.get(CARR_CD));

                trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                if (trdPtnrShpgCondCd != null && trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD) == null) {
                    ssmParam.clear();

                    // search3
                    ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                    ssmParam.put(BIND_DS_PO_TP_CD, poInfoMap.get(DS_PO_TP_CD));
                    ssmParam.put(BIND_SHPG_SVC_LVL_CD, poInfoMap.get(SHPG_SVC_LVL_CD));

                    trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                    if (trdPtnrShpgCondCd != null && trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD) == null) {

                        ssmParam.clear();

                        // search4
                        ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                        ssmParam.put(BIND_DS_PO_TP_CD, poInfoMap.get(DS_PO_TP_CD));

                        trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                        if (trdPtnrShpgCondCd != null && trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD) == null) {

                            ssmParam.clear();

                            // search5
                            ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                            ssmParam.put(BIND_SHPG_SVC_LVL_CD, poInfoMap.get(SHPG_SVC_LVL_CD));

                            trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                            if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                                return trdPtnrShpgCondCd;

                            } else {

                                ssmParam.clear();

                                // search6
                                ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());

                                trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                                return trdPtnrShpgCondCd;

                            }
                        } else {

                            if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                                return trdPtnrShpgCondCd;
                            }

                            ssmParam.clear();

                            // search5
                            ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                            ssmParam.put(BIND_SHPG_SVC_LVL_CD, poInfoMap.get(SHPG_SVC_LVL_CD));

                            trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                            if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                                return trdPtnrShpgCondCd;

                            } else {

                                ssmParam.clear();

                                // search6
                                ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());

                                trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                                return trdPtnrShpgCondCd;

                            }
                        }
                    } else {

                        if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                            return trdPtnrShpgCondCd;
                        }

                        ssmParam.clear();

                        // search4
                        ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                        ssmParam.put(BIND_DS_PO_TP_CD, poInfoMap.get(DS_PO_TP_CD));

                        trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                        if (trdPtnrShpgCondCd != null && trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD) == null) {

                            ssmParam.clear();

                            // search5
                            ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                            ssmParam.put(BIND_SHPG_SVC_LVL_CD, poInfoMap.get(SHPG_SVC_LVL_CD));

                            trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                            if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                                return trdPtnrShpgCondCd;

                            } else {

                                ssmParam.clear();

                                // search6
                                ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());

                                trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                                return trdPtnrShpgCondCd;

                            }
                        } else {

                            if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                                return trdPtnrShpgCondCd;
                            }

                            ssmParam.clear();

                            // search5
                            ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                            ssmParam.put(BIND_SHPG_SVC_LVL_CD, poInfoMap.get(SHPG_SVC_LVL_CD));

                            trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                            if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                                return trdPtnrShpgCondCd;

                            } else {

                                ssmParam.clear();

                                // search6
                                ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());

                                trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                                return trdPtnrShpgCondCd;
                            }
                        }
                    }

                } else {

                    if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                        return trdPtnrShpgCondCd;
                    }
                    ssmParam.clear();

                    // search3
                    ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                    ssmParam.put(BIND_DS_PO_TP_CD, poInfoMap.get(DS_PO_TP_CD));
                    ssmParam.put(BIND_SHPG_SVC_LVL_CD, poInfoMap.get(SHPG_SVC_LVL_CD));

                    trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                    if (trdPtnrShpgCondCd != null && trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD) == null) {

                        ssmParam.clear();

                        // search4
                        ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                        ssmParam.put(BIND_DS_PO_TP_CD, poInfoMap.get(DS_PO_TP_CD));

                        trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                        if (trdPtnrShpgCondCd != null && trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD) == null) {

                            ssmParam.clear();

                            // search5
                            ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                            ssmParam.put(BIND_SHPG_SVC_LVL_CD, poInfoMap.get(SHPG_SVC_LVL_CD));

                            trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                            if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                                return trdPtnrShpgCondCd;

                            } else {

                                ssmParam.clear();

                                // search6
                                ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());

                                trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                                return trdPtnrShpgCondCd;

                            }
                        } else {

                            if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                                return trdPtnrShpgCondCd;
                            }

                            ssmParam.clear();

                            // search5
                            ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                            ssmParam.put(BIND_SHPG_SVC_LVL_CD, poInfoMap.get(SHPG_SVC_LVL_CD));

                            trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                            if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                                return trdPtnrShpgCondCd;

                            } else {

                                ssmParam.clear();

                                // search6
                                ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());

                                trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                                return trdPtnrShpgCondCd;

                            }
                        }
                    } else {

                        if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                            return trdPtnrShpgCondCd;
                        }

                        ssmParam.clear();

                        // search4
                        ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                        ssmParam.put(BIND_DS_PO_TP_CD, poInfoMap.get(DS_PO_TP_CD));

                        trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                        if (trdPtnrShpgCondCd != null && trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD) == null) {

                            ssmParam.clear();

                            // search5
                            ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                            ssmParam.put(BIND_SHPG_SVC_LVL_CD, poInfoMap.get(SHPG_SVC_LVL_CD));

                            trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                            if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                                return trdPtnrShpgCondCd;

                            } else {

                                ssmParam.clear();

                                // search6
                                ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());

                                trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                                return trdPtnrShpgCondCd;

                            }
                        } else {

                            if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                                return trdPtnrShpgCondCd;
                            }

                            ssmParam.clear();

                            // search5
                            ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                            ssmParam.put(BIND_SHPG_SVC_LVL_CD, poInfoMap.get(SHPG_SVC_LVL_CD));

                            trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                            if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                                return trdPtnrShpgCondCd;

                            } else {

                                ssmParam.clear();

                                // search6
                                ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());

                                trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                                return trdPtnrShpgCondCd;

                            }
                        }
                    }
                }
            }
        // Mod QC#53395-1
        } else if (poInfoMap.get(DS_PO_TP_CD) != null && poInfoMap.get(CARR_CD) != null) {

            ssmParam.clear();

            // QC#53395. search2
            ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
            ssmParam.put(BIND_DS_PO_TP_CD, poInfoMap.get(DS_PO_TP_CD));
            ssmParam.put(BIND_CARR_CD, poInfoMap.get(CARR_CD));

            Map<String, Object> trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

            if (trdPtnrShpgCondCd != null && trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD) == null) {
                ssmParam.clear();

                // search3
                ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                ssmParam.put(BIND_DS_PO_TP_CD, poInfoMap.get(DS_PO_TP_CD));
                ssmParam.put(BIND_SHPG_SVC_LVL_CD, poInfoMap.get(SHPG_SVC_LVL_CD));

                trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                if (trdPtnrShpgCondCd != null && trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD) == null) {

                    ssmParam.clear();

                    // search4
                    ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                    ssmParam.put(BIND_DS_PO_TP_CD, poInfoMap.get(DS_PO_TP_CD));

                    trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                    if (trdPtnrShpgCondCd != null && trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD) == null) {

                        ssmParam.clear();

                        // search5
                        ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                        ssmParam.put(BIND_SHPG_SVC_LVL_CD, poInfoMap.get(SHPG_SVC_LVL_CD));

                        trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                        if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                            return trdPtnrShpgCondCd;

                        } else {

                            ssmParam.clear();

                            // search6
                            ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());

                            trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                            return trdPtnrShpgCondCd;

                        }
                    } else {

                        if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                            return trdPtnrShpgCondCd;
                        }

                        ssmParam.clear();

                        // search5
                        ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                        ssmParam.put(BIND_SHPG_SVC_LVL_CD, poInfoMap.get(SHPG_SVC_LVL_CD));

                        trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                        if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                            return trdPtnrShpgCondCd;

                        } else {

                            ssmParam.clear();

                            // search6
                            ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());

                            trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                            return trdPtnrShpgCondCd;

                        }
                    }
                } else {

                    if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                        return trdPtnrShpgCondCd;
                    }

                    ssmParam.clear();

                    // search4
                    ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                    ssmParam.put(BIND_DS_PO_TP_CD, poInfoMap.get(DS_PO_TP_CD));

                    trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                    if (trdPtnrShpgCondCd != null && trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD) == null) {

                        ssmParam.clear();

                        // search5
                        ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                        ssmParam.put(BIND_SHPG_SVC_LVL_CD, poInfoMap.get(SHPG_SVC_LVL_CD));

                        trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                        if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                            return trdPtnrShpgCondCd;

                        } else {

                            ssmParam.clear();

                            // search6
                            ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());

                            trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                            return trdPtnrShpgCondCd;

                        }
                    } else {

                        if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                            return trdPtnrShpgCondCd;
                        }

                        ssmParam.clear();

                        // search5
                        ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                        ssmParam.put(BIND_SHPG_SVC_LVL_CD, poInfoMap.get(SHPG_SVC_LVL_CD));

                        trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                        if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                            return trdPtnrShpgCondCd;

                        } else {

                            ssmParam.clear();

                            // search6
                            ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());

                            trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                            return trdPtnrShpgCondCd;
                        }
                    }
                }

            } else {

                if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                    return trdPtnrShpgCondCd;
                }
                ssmParam.clear();

                // search3
                ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                ssmParam.put(BIND_DS_PO_TP_CD, poInfoMap.get(DS_PO_TP_CD));
                ssmParam.put(BIND_SHPG_SVC_LVL_CD, poInfoMap.get(SHPG_SVC_LVL_CD));

                trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                if (trdPtnrShpgCondCd != null && trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD) == null) {

                    ssmParam.clear();

                    // search4
                    ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                    ssmParam.put(BIND_DS_PO_TP_CD, poInfoMap.get(DS_PO_TP_CD));

                    trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                    if (trdPtnrShpgCondCd != null && trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD) == null) {

                        ssmParam.clear();

                        // search5
                        ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                        ssmParam.put(BIND_SHPG_SVC_LVL_CD, poInfoMap.get(SHPG_SVC_LVL_CD));

                        trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                        if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                            return trdPtnrShpgCondCd;

                        } else {

                            ssmParam.clear();

                            // search6
                            ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());

                            trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                            return trdPtnrShpgCondCd;

                        }
                    } else {

                        if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                            return trdPtnrShpgCondCd;
                        }

                        ssmParam.clear();

                        // search5
                        ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                        ssmParam.put(BIND_SHPG_SVC_LVL_CD, poInfoMap.get(SHPG_SVC_LVL_CD));

                        trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                        if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                            return trdPtnrShpgCondCd;

                        } else {

                            ssmParam.clear();

                            // search6
                            ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());

                            trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                            return trdPtnrShpgCondCd;

                        }
                    }
                } else {

                    if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                        return trdPtnrShpgCondCd;
                    }

                    ssmParam.clear();

                    // search4
                    ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                    ssmParam.put(BIND_DS_PO_TP_CD, poInfoMap.get(DS_PO_TP_CD));

                    trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                    if (trdPtnrShpgCondCd != null && trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD) == null) {

                        ssmParam.clear();

                        // search5
                        ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                        ssmParam.put(BIND_SHPG_SVC_LVL_CD, poInfoMap.get(SHPG_SVC_LVL_CD));

                        trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                        if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                            return trdPtnrShpgCondCd;

                        } else {

                            ssmParam.clear();

                            // search6
                            ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());

                            trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                            return trdPtnrShpgCondCd;

                        }
                    } else {

                        if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                            return trdPtnrShpgCondCd;
                        }

                        ssmParam.clear();

                        // search5
                        ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                        ssmParam.put(BIND_SHPG_SVC_LVL_CD, poInfoMap.get(SHPG_SVC_LVL_CD));

                        trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                        if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                            return trdPtnrShpgCondCd;

                        } else {

                            ssmParam.clear();

                            // search6
                            ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());

                            trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                            return trdPtnrShpgCondCd;

                        }

                    }

                }

            }
        // Add QC#53395-1
        } else if (poInfoMap.get(DS_PO_TP_CD) != null && poInfoMap.get(SHPG_SVC_LVL_CD) != null) {

            ssmParam.clear();

            // search3
            ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
            ssmParam.put(BIND_DS_PO_TP_CD, poInfoMap.get(DS_PO_TP_CD));
            ssmParam.put(BIND_SHPG_SVC_LVL_CD, poInfoMap.get(SHPG_SVC_LVL_CD));

            Map<String, Object> trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

            if (trdPtnrShpgCondCd != null && trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD) == null) {

                ssmParam.clear();

                // search4
                ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                ssmParam.put(BIND_DS_PO_TP_CD, poInfoMap.get(DS_PO_TP_CD));

                trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                if (trdPtnrShpgCondCd != null && trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD) == null) {

                    ssmParam.clear();

                    // search5
                    ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                    ssmParam.put(BIND_SHPG_SVC_LVL_CD, poInfoMap.get(SHPG_SVC_LVL_CD));

                    trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                    if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                        return trdPtnrShpgCondCd;

                    } else {

                        ssmParam.clear();

                        // search6
                        ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());

                        trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                        return trdPtnrShpgCondCd;

                    }
                } else {

                    if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                        return trdPtnrShpgCondCd;
                    }

                    ssmParam.clear();

                    // search5
                    ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                    ssmParam.put(BIND_SHPG_SVC_LVL_CD, poInfoMap.get(SHPG_SVC_LVL_CD));

                    trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                    if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                        return trdPtnrShpgCondCd;

                    } else {

                        ssmParam.clear();

                        // search6
                        ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());

                        trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                        return trdPtnrShpgCondCd;

                    }
                }
            } else {

                if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                    return trdPtnrShpgCondCd;
                }

                ssmParam.clear();

                // search4
                ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                ssmParam.put(BIND_DS_PO_TP_CD, poInfoMap.get(DS_PO_TP_CD));

                trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                if (trdPtnrShpgCondCd != null && trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD) == null) {

                    ssmParam.clear();

                    // search5
                    ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                    ssmParam.put(BIND_SHPG_SVC_LVL_CD, poInfoMap.get(SHPG_SVC_LVL_CD));

                    trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                    if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                        return trdPtnrShpgCondCd;

                    } else {

                        ssmParam.clear();

                        // search6
                        ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());

                        trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                        return trdPtnrShpgCondCd;

                    }
                } else {

                    if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                        return trdPtnrShpgCondCd;
                    }

                    ssmParam.clear();

                    // search5
                    ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                    ssmParam.put(BIND_SHPG_SVC_LVL_CD, poInfoMap.get(SHPG_SVC_LVL_CD));

                    trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                    if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                        return trdPtnrShpgCondCd;

                    } else {

                        ssmParam.clear();

                        // search6
                        ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());

                        trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                        return trdPtnrShpgCondCd;
                    }
                }
            }

        } else {

            // seacrh4
            ssmParam.clear();

            ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
            ssmParam.put(BIND_DS_PO_TP_CD, poInfoMap.get(DS_PO_TP_CD));

            Map<String, Object> trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

            if (trdPtnrShpgCondCd != null && ZYPCommonFunc.hasValue((String) trdPtnrShpgCondCd.get(TRD_PTNR_SHPG_COND_CD))) {

                return trdPtnrShpgCondCd;

            } else {

                ssmParam.clear();

                // search6
                ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());

                trdPtnrShpgCondCd = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgCondCdInfo", ssmParam);

                return trdPtnrShpgCondCd;
            }

        }

    }

    /**
     * getTrdPtnrShpgXRefInfo
     * @param poInfoMap
     * @param msgMap
     * @return
     */
    private Map<String, Object> getTrdPtnrShpgXRefInfo(Map<String, Object> poInfoMap, S21ApiMessageMap msgMap) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        NPZC132001PMsg param = (NPZC132001PMsg) msgMap.getPmsg();

        Map<String, Object> trdPtnrShpgXRefMap = new HashMap<String, Object>();

        // TRD_PTNR_CARR_CD
        trdPtnrCarrCd = null;

        // TRD_PTNR_SHPG_SVC_LVL_CD
        trdPtnrShpgSvcLvlCd = null;

        // TRD_PTNR_SHIP_VIA_CD
        trdPtnrShipViaCd = null;

        // TRD_PTNR_FRT_CHRG_METH_CD
        trdPtnrFrtChegMethCd = null;

        // QC#26006 MOD START

        String glblCmpyCd = param.glblCmpyCd.getValue();
        String vndCd = (String) poInfoMap.get(VND_CD);
        String carrCd = (String) poInfoMap.get(CARR_CD);
        String shpgSvcLvlCd = (String) poInfoMap.get(SHPG_SVC_LVL_CD);
        String frtCondCd = (String) poInfoMap.get(FRT_COND_CD);

        if (ZYPCommonFunc.hasValue(carrCd) && ZYPCommonFunc.hasValue(shpgSvcLvlCd) && ZYPCommonFunc.hasValue(frtCondCd)) {

            // Search 1
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, carrCd, shpgSvcLvlCd, frtCondCd);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

            // Search 2
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, STAR, shpgSvcLvlCd, frtCondCd);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

            // Search 3
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, carrCd, shpgSvcLvlCd, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

            // Search 4
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, STAR, shpgSvcLvlCd, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

            // Search 5
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, carrCd, STAR, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

            // Search 6
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, STAR, STAR, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

        } else if (!ZYPCommonFunc.hasValue(carrCd) && ZYPCommonFunc.hasValue(shpgSvcLvlCd) && ZYPCommonFunc.hasValue(frtCondCd)) {

            // Search 2
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, STAR, shpgSvcLvlCd, frtCondCd);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

            // Search 4
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, STAR, shpgSvcLvlCd, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

            // Search 6
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, STAR, STAR, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

        } else if (ZYPCommonFunc.hasValue(carrCd) && !ZYPCommonFunc.hasValue(shpgSvcLvlCd) && ZYPCommonFunc.hasValue(frtCondCd)) {

            // Search 5
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, carrCd, STAR, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

            // Search 6
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, STAR, STAR, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

        } else if (ZYPCommonFunc.hasValue(carrCd) && ZYPCommonFunc.hasValue(shpgSvcLvlCd) && !ZYPCommonFunc.hasValue(frtCondCd)) {

            // Search 3
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, carrCd, shpgSvcLvlCd, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

            // Search 4
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, STAR, shpgSvcLvlCd, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

            // Search 5
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, carrCd, STAR, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

            // Search 6
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, STAR, STAR, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

        } else if (ZYPCommonFunc.hasValue(carrCd) && !ZYPCommonFunc.hasValue(shpgSvcLvlCd) && !ZYPCommonFunc.hasValue(frtCondCd)) {

            // Search 5
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, carrCd, STAR, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

            // Search 6
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, STAR, STAR, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

        } else if (!ZYPCommonFunc.hasValue(carrCd) && ZYPCommonFunc.hasValue(shpgSvcLvlCd) && !ZYPCommonFunc.hasValue(frtCondCd)) {

            // Search 4
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, STAR, shpgSvcLvlCd, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

            // Search 6
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, STAR, STAR, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

        } else if (!ZYPCommonFunc.hasValue(carrCd) && !ZYPCommonFunc.hasValue(shpgSvcLvlCd) && ZYPCommonFunc.hasValue(frtCondCd)) {

            // Search 6
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, STAR, STAR, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }

        } else if (!ZYPCommonFunc.hasValue(carrCd) && !ZYPCommonFunc.hasValue(shpgSvcLvlCd) && !ZYPCommonFunc.hasValue(frtCondCd)) {

            // Search 6
            trdPtnrShpgXRefMap = searchTrdPtnrShpgXRefInfo(glblCmpyCd, vndCd, STAR, STAR, STAR);
            if (trdPtnrShpgXRefMap != null) {
                return trdPtnrShpgXRefMap;
            }
        }

        //        
        // // START QC#23087 T.Hakodate MOD
        //        
        // //if (poInfoMap.get(VND_CD) == null &&
        // poInfoMap.get(CARR_CD) == null &&
        // poInfoMap.get(SHPG_SVC_LVL_CD) == null &&
        // poInfoMap.get(FRT_COND_CD) == null) {
        //
        // if (poInfoMap.get(VND_CD) == null) {
        // // no search
        // ssmParam.clear();
        //
        // return trdPtnrShpgXRefMap;
        //
        // // } else if (poInfoMap.get(CARR_CD) != null &&
        // // poInfoMap.get(SHPG_SVC_LVL_CD) != null &&
        // // poInfoMap.get(FRT_COND_CD) != null) {
        //
        // } else {
        //
        // String carrCd = null;
        // String shpgSvcLvlCd = null;
        // String frtCondCd = null;
        //
        // if (poInfoMap.get(CARR_CD) == null) {
        // carrCd = STAR;
        // } else {
        // carrCd = (String) poInfoMap.get(CARR_CD);
        // }
        //
        // if (poInfoMap.get(SHPG_SVC_LVL_CD) == null) {
        // shpgSvcLvlCd = STAR;
        // } else {
        // shpgSvcLvlCd = (String) poInfoMap.get(SHPG_SVC_LVL_CD);
        // }
        //
        // if (poInfoMap.get(FRT_COND_CD) == null) {
        // frtCondCd = STAR;
        // } else {
        // frtCondCd = (String) poInfoMap.get(FRT_COND_CD);
        // }
        //
        // ssmParam.clear();
        // ssmParam.put(BIND_GLBL_CMPY_CD,
        // param.glblCmpyCd.getValue());
        // ssmParam.put(BIND_VND_CD, poInfoMap.get(VND_CD));
        // ssmParam.put(BIND_CARR_CD, carrCd);
        // ssmParam.put(BIND_SHPG_SVC_LCL_CD, shpgSvcLvlCd);
        // ssmParam.put(BIND_FRT_COND_CD, frtCondCd);
        //
        // trdPtnrShpgXRefMap = (Map<String, Object>)
        // ssmBatchClient.queryObject("getTrdPtnrShpgXRefInfo",
        // ssmParam);

        // QC#26006 MOD END

        // if (trdPtnrShpgXRefMap != null) {
        // trdPtnrCarrCd = (String)
        // trdPtnrShpgXRefMap.get(TRD_PTNR_CARR_CD);
        // trdPtnrShpgSvcLvlCd = (String)
        // trdPtnrShpgXRefMap.get(TRD_PTNR_SHPG_SVC_LVL_CD);
        // trdPtnrShipViaCd = (String)
        // trdPtnrShpgXRefMap.get(TRD_PTNR_SHIP_VIA_CD);
        // trdPtnrFrtChegMethCd = (String)
        // trdPtnrShpgXRefMap.get(TRD_PTNR_FRT_CHRG_METH_CD);
        // }

        return trdPtnrShpgXRefMap;

    }

    // if (trdPtnrShpgXRefMap != null) {
    //
    // trdPtnrCarrCd = (String)
    // trdPtnrShpgXRefMap.get(TRD_PTNR_CARR_CD);
    // trdPtnrShpgSvcLvlCd = (String)
    // trdPtnrShpgXRefMap.get(TRD_PTNR_SHPG_SVC_LVL_CD);
    // trdPtnrShipViaCd = (String)
    // trdPtnrShpgXRefMap.get(TRD_PTNR_SHIP_VIA_CD);
    // trdPtnrFrtChegMethCd = (String)
    // trdPtnrShpgXRefMap.get(TRD_PTNR_FRT_CHRG_METH_CD);
    //
    // } else {
    //
    // // search2
    // ssmParam.clear();
    // ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
    // ssmParam.put(BIND_VND_CD, poInfoMap.get(VND_CD));
    // ssmParam.put(BIND_CARR_CD, STAR);
    // ssmParam.put(BIND_SHPG_SVC_LCL_CD,
    // poInfoMap.get(SHPG_SVC_LVL_CD));
    // ssmParam.put(BIND_FRT_COND_CD, poInfoMap.get(FRT_COND_CD));
    //
    // trdPtnrShpgXRefMap = (Map<String, Object>)
    // ssmBatchClient.queryObject("getTrdPtnrShpgXRefInfo", ssmParam);
    //
    // if (trdPtnrShpgXRefMap != null) {
    //
    // trdPtnrCarrCd = (String)
    // trdPtnrShpgXRefMap.get(TRD_PTNR_CARR_CD);
    // trdPtnrShpgSvcLvlCd = (String)
    // trdPtnrShpgXRefMap.get(TRD_PTNR_SHPG_SVC_LVL_CD);
    // trdPtnrShipViaCd = (String)
    // trdPtnrShpgXRefMap.get(TRD_PTNR_SHIP_VIA_CD);
    // trdPtnrFrtChegMethCd = (String)
    // trdPtnrShpgXRefMap.get(TRD_PTNR_FRT_CHRG_METH_CD);
    //
    // } else {
    //
    // // search3
    // ssmParam.clear();
    // ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
    // ssmParam.put(BIND_VND_CD, poInfoMap.get(VND_CD));
    // ssmParam.put(BIND_CARR_CD, poInfoMap.get(CARR_CD));
    // ssmParam.put(BIND_SHPG_SVC_LCL_CD, STAR);
    // ssmParam.put(BIND_FRT_COND_CD, STAR);
    //
    // trdPtnrShpgXRefMap = (Map<String, Object>)
    // ssmBatchClient.queryObject("getTrdPtnrShpgXRefInfo", ssmParam);
    //
    // if (trdPtnrShpgXRefMap != null) {
    //
    // trdPtnrCarrCd = (String)
    // trdPtnrShpgXRefMap.get(TRD_PTNR_CARR_CD);
    // trdPtnrShpgSvcLvlCd = (String)
    // trdPtnrShpgXRefMap.get(TRD_PTNR_SHPG_SVC_LVL_CD);
    // trdPtnrShipViaCd = (String)
    // trdPtnrShpgXRefMap.get(TRD_PTNR_SHIP_VIA_CD);
    // trdPtnrFrtChegMethCd = (String)
    // trdPtnrShpgXRefMap.get(TRD_PTNR_FRT_CHRG_METH_CD);
    //
    // } else {
    //
    // // search4
    // ssmParam.clear();
    // ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
    // ssmParam.put(BIND_VND_CD, poInfoMap.get(VND_CD));
    // ssmParam.put(BIND_CARR_CD, STAR);
    // ssmParam.put(BIND_SHPG_SVC_LCL_CD, STAR);
    // ssmParam.put(BIND_FRT_COND_CD, STAR);
    //
    // trdPtnrShpgXRefMap = (Map<String, Object>)
    // ssmBatchClient.queryObject("getTrdPtnrShpgXRefInfo", ssmParam);
    //
    // if (trdPtnrShpgXRefMap != null) {
    //
    // trdPtnrCarrCd = (String)
    // trdPtnrShpgXRefMap.get(TRD_PTNR_CARR_CD);
    // trdPtnrShpgSvcLvlCd = (String)
    // trdPtnrShpgXRefMap.get(TRD_PTNR_SHPG_SVC_LVL_CD);
    // trdPtnrShipViaCd = (String)
    // trdPtnrShpgXRefMap.get(TRD_PTNR_SHIP_VIA_CD);
    // trdPtnrFrtChegMethCd = (String)
    // trdPtnrShpgXRefMap.get(TRD_PTNR_FRT_CHRG_METH_CD);
    // }
    // }
    //
    // }
    //
    // }
    //
    // return trdPtnrShpgXRefMap;
    //
    // } else if (poInfoMap.get(CARR_CD) == null) {
    //
    // // search2
    // ssmParam.clear();
    // ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
    // ssmParam.put(BIND_VND_CD, poInfoMap.get(VND_CD));
    // ssmParam.put(BIND_CARR_CD, STAR);
    // ssmParam.put(BIND_SHPG_SVC_LCL_CD,
    // poInfoMap.get(SHPG_SVC_LVL_CD));
    // ssmParam.put(BIND_FRT_COND_CD, poInfoMap.get(FRT_COND_CD));
    //
    // trdPtnrShpgXRefMap = (Map<String, Object>)
    // ssmBatchClient.queryObject("getTrdPtnrShpgXRefInfo", ssmParam);
    //
    // if (trdPtnrShpgXRefMap != null) {
    //
    // trdPtnrCarrCd = (String)
    // trdPtnrShpgXRefMap.get(TRD_PTNR_CARR_CD);
    // trdPtnrShpgSvcLvlCd = (String)
    // trdPtnrShpgXRefMap.get(TRD_PTNR_SHPG_SVC_LVL_CD);
    // trdPtnrShipViaCd = (String)
    // trdPtnrShpgXRefMap.get(TRD_PTNR_SHIP_VIA_CD);
    // trdPtnrFrtChegMethCd = (String)
    // trdPtnrShpgXRefMap.get(TRD_PTNR_FRT_CHRG_METH_CD);
    //
    // } else {
    //
    // // search4
    // ssmParam.clear();
    // ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
    // ssmParam.put(BIND_VND_CD, poInfoMap.get(VND_CD));
    // ssmParam.put(BIND_CARR_CD, STAR);
    // ssmParam.put(BIND_SHPG_SVC_LCL_CD, STAR);
    // ssmParam.put(BIND_FRT_COND_CD, STAR);
    //
    // trdPtnrShpgXRefMap = (Map<String, Object>)
    // ssmBatchClient.queryObject("getTrdPtnrShpgXRefInfo", ssmParam);
    //
    // if (trdPtnrShpgXRefMap != null) {
    //
    // trdPtnrCarrCd = (String)
    // trdPtnrShpgXRefMap.get(TRD_PTNR_CARR_CD);
    // trdPtnrShpgSvcLvlCd = (String)
    // trdPtnrShpgXRefMap.get(TRD_PTNR_SHPG_SVC_LVL_CD);
    // trdPtnrShipViaCd = (String)
    // trdPtnrShpgXRefMap.get(TRD_PTNR_SHIP_VIA_CD);
    // trdPtnrFrtChegMethCd = (String)
    // trdPtnrShpgXRefMap.get(TRD_PTNR_FRT_CHRG_METH_CD);
    // }
    // }
    //
    // } else {
    //
    // // search3
    // ssmParam.clear();
    // ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
    // ssmParam.put(BIND_VND_CD, poInfoMap.get(VND_CD));
    // ssmParam.put(BIND_CARR_CD, poInfoMap.get(CARR_CD));
    // ssmParam.put(BIND_SHPG_SVC_LCL_CD, STAR);
    // ssmParam.put(BIND_FRT_COND_CD, STAR);
    //
    // trdPtnrShpgXRefMap = (Map<String, Object>)
    // ssmBatchClient.queryObject("getTrdPtnrShpgXRefInfo", ssmParam);
    //
    // if (trdPtnrShpgXRefMap != null) {
    //
    // trdPtnrCarrCd = (String)
    // trdPtnrShpgXRefMap.get(TRD_PTNR_CARR_CD);
    // trdPtnrShpgSvcLvlCd = (String)
    // trdPtnrShpgXRefMap.get(TRD_PTNR_SHPG_SVC_LVL_CD);
    // trdPtnrShipViaCd = (String)
    // trdPtnrShpgXRefMap.get(TRD_PTNR_SHIP_VIA_CD);
    // trdPtnrFrtChegMethCd = (String)
    // trdPtnrShpgXRefMap.get(TRD_PTNR_FRT_CHRG_METH_CD);
    //
    // } else {
    //
    // // search4
    // ssmParam.clear();
    // ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
    // ssmParam.put(BIND_VND_CD, poInfoMap.get(VND_CD));
    // ssmParam.put(BIND_CARR_CD, STAR);
    // ssmParam.put(BIND_SHPG_SVC_LCL_CD, STAR);
    // ssmParam.put(BIND_FRT_COND_CD, STAR);
    //
    // trdPtnrShpgXRefMap = (Map<String, Object>)
    // ssmBatchClient.queryObject("getTrdPtnrShpgXRefInfo", ssmParam);
    //
    // if (trdPtnrShpgXRefMap != null) {
    //
    // trdPtnrCarrCd = (String)
    // trdPtnrShpgXRefMap.get(TRD_PTNR_CARR_CD);
    // trdPtnrShpgSvcLvlCd = (String)
    // trdPtnrShpgXRefMap.get(TRD_PTNR_SHPG_SVC_LVL_CD);
    // trdPtnrShipViaCd = (String)
    // trdPtnrShpgXRefMap.get(TRD_PTNR_SHIP_VIA_CD);
    // trdPtnrFrtChegMethCd = (String)
    // trdPtnrShpgXRefMap.get(TRD_PTNR_FRT_CHRG_METH_CD);
    // }
    // }
    //
    // }

    // return trdPtnrShpgXRefMap;
    // END QC#23087 T.Hakodate MOD

    private Map<String, Object> searchTrdPtnrShpgXRefInfo(String glblCmpyCd, String vndCd, String carrCd, String shpgSvcLvlCd, String frtCondCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        Map<String, Object> trdPtnrShpgXRefMap = new HashMap<String, Object>();

        ssmParam.put(BIND_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(BIND_VND_CD, vndCd);
        ssmParam.put(BIND_CARR_CD, carrCd);
        ssmParam.put(BIND_SHPG_SVC_LCL_CD, shpgSvcLvlCd);
        ssmParam.put(BIND_FRT_COND_CD, frtCondCd);

        trdPtnrShpgXRefMap = (Map<String, Object>) ssmBatchClient.queryObject("getTrdPtnrShpgXRefInfo", ssmParam);

        return trdPtnrShpgXRefMap;

    }

    /**
     * validationCheckWs
     * @param msgMap
     * @param tMsg
     * @param NPAA0010_02
     * @param lineCheckFlg
     * @return
     */
    private boolean validationCheckWs(S21ApiMessageMap msgMap, NPAA0010_01TMsg tMsg, NPAA0010_02TMsg NPAA0010_02, Map<String, Object> map) {

        boolean isError = false;

        // QC#25392 MOD START
        // check country. Us or not
        boolean ctryUs = false;
        if (ZYPCommonFunc.hasValue((String) map.get(SHIP_TO_CTRY_CD)) && CTRY.UNITED_STATES_OF_AMERICA.equals((String) map.get(SHIP_TO_CTRY_CD))) {
            ctryUs = true;
        }
        // QC#25392 MOD END

        EZDDebugOutput.println(1, PROGRAM_ID + "[ validationCheckWs ] start", this);

        if (!ZYPCommonFunc.hasValue(NPAA0010_02.unitPrcAmt)) {

            msgMap.addXxMsgId(MSG_ID_NPZM0288E);
            isError = true;
        }

        if (!ZYPCommonFunc.hasValue(NPAA0010_02.trdPtnrShipToCustCd)) {

            msgMap.addXxMsgId(MSG_ID_NPZM0287E);
            isError = true;
        }

        if (EDI_PO_TP_CD_DS.equals(tMsg.ediPoTpCd.getValue())) {

            if (!ZYPCommonFunc.hasValue(NPAA0010_02.shipToCustLocNm)) {
                msgMap.addXxMsgId(MSG_ID_NPAM1386E);
                isError = true;
            }

            if (!ZYPCommonFunc.hasValue(NPAA0010_02.shipToFirstLineAddr)) {
                msgMap.addXxMsgId(MSG_ID_NPAM1387E);
                isError = true;

            }

            if (!ZYPCommonFunc.hasValue(NPAA0010_02.shipToCustCtyAddr)) {
                msgMap.addXxMsgId(MSG_ID_NPAM1388E);
                isError = true;

            }

            // QC#25392 MOD START
            if (ctryUs) {
                if (!ZYPCommonFunc.hasValue(NPAA0010_02.shipToCustStCd)) {
                    msgMap.addXxMsgId(MSG_ID_NPAM1389E);
                    isError = true;
                }
            }
            // QC#25392 MOD END

            if (!ZYPCommonFunc.hasValue(NPAA0010_02.shipToCustPostCd)) {
                msgMap.addXxMsgId(MSG_ID_NPAM1390E);
                isError = true;
            }

        }

        return isError;

    }

    /**
     * validationCheckParts
     * @param msgMap
     * @param tMsg
     * @param NPAA0020_02
     * @param lineCheckFlg
     * @return
     */
    private boolean validationCheckParts(S21ApiMessageMap msgMap, NPAA0020_01TMsg tMsg, NPAA0020_02TMsg NPAA0020_02, Map<String, Object> map) {

        boolean isError = false;

        // QC#25392 MOD START
        // check country. Us or not
        boolean ctryUs = false;
        if (ZYPCommonFunc.hasValue((String) map.get(SHIP_TO_CTRY_CD)) && CTRY.UNITED_STATES_OF_AMERICA.equals((String) map.get(SHIP_TO_CTRY_CD))) {
            ctryUs = true;
        }
        // QC#25392 MOD END

        EZDDebugOutput.println(1, PROGRAM_ID + "[ validationCheckParts ] start", this);

        if (!ZYPCommonFunc.hasValue(NPAA0020_02.unitPrcAmt)) {

            msgMap.addXxMsgId(MSG_ID_NPZM0288E);
            isError = true;

        }

        if (!ZYPCommonFunc.hasValue(NPAA0020_02.trdPtnrShipToCustCd)) {

            msgMap.addXxMsgId(MSG_ID_NPZM0287E);
            isError = true;

        }

        if (EDI_PO_TP_CD_DS.equals(tMsg.ediPoTpCd.getValue())) {

            if (!ZYPCommonFunc.hasValue(NPAA0020_02.shipToCustLocNm)) {

                msgMap.addXxMsgId(MSG_ID_NPAM1386E);
                isError = true;

            }

            if (!ZYPCommonFunc.hasValue(NPAA0020_02.shipToFirstLineAddr)) {

                msgMap.addXxMsgId(MSG_ID_NPAM1387E);
                isError = true;

            }

            if (!ZYPCommonFunc.hasValue(NPAA0020_02.shipToCustCtyAddr)) {

                msgMap.addXxMsgId(MSG_ID_NPAM1388E);
                isError = true;

            }

            // QC#25392 MOD START
            if (ctryUs) {
                if (!ZYPCommonFunc.hasValue(NPAA0020_02.shipToCustStCd)) {
                    msgMap.addXxMsgId(MSG_ID_NPAM1389E);
                    isError = true;

                }
            }

            // QC#25392 MOD END
            if (!ZYPCommonFunc.hasValue(NPAA0020_02.shipToCustPostCd)) {

                msgMap.addXxMsgId(MSG_ID_NPAM1390E);
                isError = true;

            }

        }

        return isError;
    }

    /**
     * createIfNPAA0010
     * @param tmsg
     * @param NPAA0010_02TMsgList
     * @return
     */
    public boolean createIfNPAA0010(S21ApiMessageMap msgMap, final NPAA0010_01TMsg tmsg, List<NPAA0010_02TMsg> NPAA0010_02TMsgList) {

        boolean returnValue = true;

        // Header

        S21ApiTBLAccessor.insert(tmsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tmsg.getReturnCode())) {
            msgMap.addXxMsgId(MSG_ID_NPZM0283E);
            returnValue = false;
        }

        NPAA0010_02TMsg[] insertTMsg = new NPAA0010_02TMsg[NPAA0010_02TMsgList.size()];

        for (int i = 0; i < NPAA0010_02TMsgList.size(); i++) {

            insertTMsg[i] = NPAA0010_02TMsgList.get(i);
        }

        S21FastTBLAccessor.insert(insertTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tmsg.getReturnCode())) {
            msgMap.addXxMsgId(MSG_ID_NPZM0284E);
            returnValue = false;
        }

        return returnValue;
    }

    /**
     * createIfNPAA0020
     * @param tmsg
     * @param NPAA0020_02TMsgList
     * @return
     */
    public boolean createIfNPAA0020(S21ApiMessageMap msgMap, final NPAA0020_01TMsg tmsg, List<NPAA0020_02TMsg> NPAA0020_02TMsgList) {

        boolean returnValue = true;

        NPZC132001PMsg param = (NPZC132001PMsg) msgMap.getPmsg();

        // Header
        S21ApiTBLAccessor.insert(tmsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tmsg.getReturnCode())) {
            msgMap.addXxMsgId(MSG_ID_NPZM0285E);
            returnValue = false;
        }

        NPAA0020_02TMsg[] insertTMsg = new NPAA0020_02TMsg[NPAA0020_02TMsgList.size()];

        for (int i = 0; i < NPAA0020_02TMsgList.size(); i++) {

            insertTMsg[i] = NPAA0020_02TMsgList.get(i);

            // QC#27628 add start
            PO_DTLTMsg poDtlTmsg = new PO_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(poDtlTmsg.glblCmpyCd, param.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(poDtlTmsg.poOrdNum, insertTMsg[i].custEdiPoNum);
            ZYPEZDItemValueSetter.setValue(poDtlTmsg.poOrdDtlLineNum, insertTMsg[i].ediPoLineNum);
            poDtlTmsg = (PO_DTLTMsg) S21ApiTBLAccessor.findByKey(poDtlTmsg);

            if (carrAcctNumFromXref) {

                // Freight Terms
                ZYPEZDItemValueSetter.setValue(poDtlTmsg.frtCondCd, FRT_COND.COLLECT);

                // Carrier
                ZYPEZDItemValueSetter.setValue(poDtlTmsg.carrCd, carrCdfromXref);
            }

            // QC#28731 mod start
            String defShpgSvcLvlCd = ZYPCodeDataUtil.getVarCharConstValue("SEND_PO_DEF_SHPG_SVC_LVL", param.glblCmpyCd.getValue());
            if (!ZYPCommonFunc.hasValue(defShpgSvcLvlCd)) {
                defShpgSvcLvlCd = SHPG_SVC_LVL.GROUND_SERVICE;
            }

            // SSL
            if (!ZYPCommonFunc.hasValue(poDtlTmsg.shpgSvcLvlCd)) {
                ZYPEZDItemValueSetter.setValue(poDtlTmsg.shpgSvcLvlCd, defShpgSvcLvlCd);
            } else {
                // check SSL combination.
                Map<String, Object> ssmParam = new HashMap<String, Object>();
                ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                String shpgSvcLvlCd = poDtlTmsg.shpgSvcLvlCd.getValue();

                if (!ZYPCommonFunc.hasValue(shpgSvcLvlCd)) {
                    shpgSvcLvlCd = STAR;
                }

                String carrCd = poDtlTmsg.carrCd.getValue();
                if (!ZYPCommonFunc.hasValue(carrCd)) {
                    carrCd = STAR;
                }

                ssmParam.put(BIND_SHPG_SVC_LVL_CD, shpgSvcLvlCd);
                ssmParam.put(BIND_CARR_CD, carrCd);

                Map<String, Object> carrSvcLvlCd = (Map<String, Object>) ssmBatchClient.queryObject("getCarrSvcLvlCd", ssmParam);

                if (carrSvcLvlCd == null || carrSvcLvlCd.get(CARR_SVC_LVL_CD) == null) {
                    ZYPEZDItemValueSetter.setValue(poDtlTmsg.shpgSvcLvlCd, defShpgSvcLvlCd);
                }
            }

            S21ApiTBLAccessor.update(poDtlTmsg);

            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(poDtlTmsg.getReturnCode())) {
                msgMap.addXxMsgId(MSG_ID_NPZM0286E);
                returnValue = false;
            }

            // QC#27628 add end

        }

        S21FastTBLAccessor.insert(insertTMsg);

        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tmsg.getReturnCode())) {
            msgMap.addXxMsgId(MSG_ID_NPZM0286E);
            returnValue = false;
        }

        // QC#27628 add start
        POTMsg poTmsg = new POTMsg();
        ZYPEZDItemValueSetter.setValue(poTmsg.glblCmpyCd, param.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(poTmsg.poOrdNum, tmsg.custEdiPoNum);
        poTmsg = (POTMsg) S21ApiTBLAccessor.findByKey(poTmsg);

        // update carrAcctNum
        ZYPEZDItemValueSetter.setValue(poTmsg.carrAcctNum, tmsg.carrAcctNum);

        S21ApiTBLAccessor.update(poTmsg);

        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(poTmsg.getReturnCode())) {
            msgMap.addXxMsgId(MSG_ID_NPZM0286E);
            returnValue = false;
        }

        return returnValue;
    }

    /**
     * searchVndShipToXref
     * @param cusaCpoApiMap
     * @param apiParam
     * @return apiParam
     */
    private String searchCsaLocationCd(S21ApiMessageMap msgMap, String dropShipFlg, Map<String, Object> poInfoMap) {

        String csaLocCd = null;

        NPZC132001PMsg param = (NPZC132001PMsg) msgMap.getPmsg();

        if (ZYPConstant.FLG_OFF_N.equals(dropShipFlg)) {

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
            ssmParam.put(BIND_VND_XREF_TP_CD, VND_XREF_TP_CD_1);
            ssmParam.put(BIND_EFF_FROM_DT, EFF_FROM_DATE);
            ssmParam.put(BIND_EFF_THRU_DT, EFF_THRU_DATE);
            ssmParam.put(BIND_SLS_DT, slsdt);

            // search 1
            ssmParam.put(BIND_INVTY_LOC_CD, poInfoMap.get(INVTY_LOC_CD));
            ssmParam.put(BIND_PRCH_GRP_CD, poInfoMap.get(PRCH_GRP_CD));

            csaLocCd = (String) ssmBatchClient.queryObject("searchVndShipToXrefInv", ssmParam);

            if (csaLocCd == null) {
                // search2
                ssmParam.put(BIND_INVTY_LOC_CD, poInfoMap.get(INVTY_LOC_CD));
                ssmParam.put(BIND_PRCH_GRP_CD, STAR);

                csaLocCd = (String) ssmBatchClient.queryObject("searchVndShipToXrefInv", ssmParam);

                if (csaLocCd == null) {
                    // search 3
                    ssmParam.put(BIND_RTL_WH_CD, poInfoMap.get(DEST_RTL_WH_CD));
                    ssmParam.put(BIND_PRCH_GRP_CD, poInfoMap.get(PRCH_GRP_CD));

                    csaLocCd = (String) ssmBatchClient.queryObject("searchVndShipToXrefRtl", ssmParam);

                    if (csaLocCd == null) {
                        // search 4
                        ssmParam.put(BIND_RTL_WH_CD, poInfoMap.get(DEST_RTL_WH_CD));
                        ssmParam.put(BIND_PRCH_GRP_CD, STAR);

                        csaLocCd = (String) ssmBatchClient.queryObject("searchVndShipToXrefRtl", ssmParam);
                    }

                }

            }

        } else if (ZYPConstant.FLG_ON_Y.equals(dropShipFlg)) {

            String dropShipWhCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_DROP_SHIP_WH_CD, param.glblCmpyCd.getValue());

            if (dropShipWhCd == null) {

                dropShipWhCd = DROP_SHIP_WH_CD_DS;

            }

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
            ssmParam.put(BIND_VND_XREF_TP_CD, VND_XREF_TP_CD_1);
            ssmParam.put(BIND_EFF_FROM_DT, EFF_FROM_DATE);
            ssmParam.put(BIND_EFF_THRU_DT, EFF_THRU_DATE);
            ssmParam.put(BIND_SLS_DT, slsdt);

            // search1
            ssmParam.put(BIND_RTL_WH_CD, poInfoMap.get(DEST_RTL_WH_CD));
            ssmParam.put(BIND_PRCH_GRP_CD, poInfoMap.get(PRCH_GRP_CD));

            csaLocCd = (String) ssmBatchClient.queryObject("searchVndShipToXrefRtl", ssmParam);

            if (csaLocCd == null) {

                // search 2
                ssmParam.put(BIND_RTL_WH_CD, poInfoMap.get(DEST_RTL_WH_CD));
                ssmParam.put(BIND_PRCH_GRP_CD, STAR);

                csaLocCd = (String) ssmBatchClient.queryObject("searchVndShipToXrefRtl", ssmParam);

                if (csaLocCd == null) {

                    // search 3
                    ssmParam.put(BIND_RTL_WH_CD, dropShipWhCd);
                    ssmParam.put(BIND_PRCH_GRP_CD, poInfoMap.get(PRCH_GRP_CD));

                    csaLocCd = (String) ssmBatchClient.queryObject("searchVndShipToXrefRtl", ssmParam);

                    if (csaLocCd == null) {
                        // search 4
                        ssmParam.put(BIND_RTL_WH_CD, dropShipWhCd);
                        ssmParam.put(BIND_PRCH_GRP_CD, STAR);

                        csaLocCd = (String) ssmBatchClient.queryObject("searchVndShipToXrefRtl", ssmParam);

                    }

                }
            }

        }

        return csaLocCd;
    }

    /**
     * Set Message Whole Sales
     * @param msgMap S21ApiMessageMap
     * @param poInfoMap Map<String, Object>
     * @param npaa001001 NPAA0010_01TMsg
     */
    private void setMsgWs(S21ApiMessageMap msgMap, Map<String, Object> poInfoMap, NPAA0010_01TMsg npaa001001) {

        // 2017/12/11 T.Hakodate QC#21025 MOD START
        // List<Map<String, Object>> msgMapList = getMsgs(msgMap);
        StringBuilder sb = new StringBuilder();

        String[] poMsgTpArray = new String[] {PO_MSG_TP.SHIPPER_NOTE, PO_MSG_TP.SPECIAL_INSTRUCTIONS };

        NPZC132001PMsg param = (NPZC132001PMsg) msgMap.getPmsg();

        for (int i = 0; i < poMsgTpArray.length; i++) {

            List<PO_MSGTMsg> poMsgList = NPXC001001PoMsg.getPoMsg(param.glblCmpyCd.getValue(), poMsgTpArray[i], param.poOrdNum.getValue(), null);

            if (poMsgList != null && poMsgList.size() > 0) {

                String msg = NPXC001001PoMsg.concatenatePoMsg(poMsgList);

                sb = sb.append(msg);
                sb = sb.append(" ");
            }

        }

        if (sb == null) {
            return;
        }

        // 08/08/2017 CITS S.Endo Mod Sol#035(QC#18108) START
        // max digit
        // int firstMaxDigit =
        // npaa001001.getAttr("firstOrdMsgTxt").getDigit();
        // int scdMaxDigit =
        // npaa001001.getAttr("scdOrdMsgTxt").getDigit();
        // int thirdMaxDigit =
        // npaa001001.getAttr("thirdOrdMsgTxt").getDigit();
        // int frthMaxDigit =
        // npaa001001.getAttr("frthOrdMsgTxt").getDigit();
        // int max = 0;

        int max = npaa001001.getAttr("firstOrdMsgTxt").getDigit();
        int msgCnt = 0;

        // // boolean msgAppendFlg = false;
        // if (msgMapList == null || msgMapList.size() == 0) {
        // return;
        // }
        // for (Map<String, Object> map : msgMapList) {
        // sb = sb.append((String) map.get(PO_MSG_TXT));
        // // if (!msgAppendFlg) {
        // // sb.append((String) map.get(PO_MSG_TXT));
        // // msgAppendFlg = true;
        // // continue;
        // // } else {
        // // sb.append(" ");
        // // sb.append((String) map.get(PO_MSG_TXT));
        // // msgAppendFlg = false;
        // // }
        // //
        // // if (msgCnt == 0) {
        // // max = firstMaxDigit;
        // // } else if (msgCnt == 1) {
        // // max = scdMaxDigit;
        // // } else if (msgCnt == 2) {
        // // max = thirdMaxDigit;
        // // } else if (msgCnt == 3) {
        // // max = frthMaxDigit;
        // // }
        // //
        // // if (sb.length() > max) {
        // // sb.delete(max, sb.length());
        // // }
        // //
        // // if (msgCnt == 0) {
        // //
        // ZYPEZDItemValueSetter.setValue(npaa001001.firstOrdMsgTxt,
        // sb.toString());
        // // sb.setLength(0);
        // // msgCnt++;
        // // } else if (msgCnt == 1) {
        // // ZYPEZDItemValueSetter.setValue(npaa001001.scdOrdMsgTxt,
        // sb.toString());
        // // sb.setLength(0);
        // // msgCnt++;
        // // } else if (msgCnt == 2) {
        // //
        // ZYPEZDItemValueSetter.setValue(npaa001001.thirdOrdMsgTxt,
        // sb.toString());
        // // sb.setLength(0);
        // // msgCnt++;
        // // } else if (msgCnt == 3) {
        // // ZYPEZDItemValueSetter.setValue(npaa001001.frthOrdMsgTxt,
        // sb.toString());
        // // sb.setLength(0);
        // // msgCnt++;
        // // }
        // }
        // 2017/12/11 T.Hakodate QC#21025 MOD END

        String concatStr = sb.toString();
        // QC#30473
        concatStr = concatStr.replaceAll("\\r\\n|\\r|\\n|\\t", " ");
        msgCnt = concatStr.length() / max;
        String sliceStr;
        int loopCount = msgCnt;
        if (msgCnt > 3) {
            loopCount = 3;
        }
        for (int i = 0; i < loopCount + 1; i++) {
            if (concatStr.length() < max) {
                if (i == 0) {
                    ZYPEZDItemValueSetter.setValue(npaa001001.firstOrdMsgTxt, concatStr);
                } else if (i == 1) {
                    ZYPEZDItemValueSetter.setValue(npaa001001.scdOrdMsgTxt, concatStr);
                } else if (i == 2) {
                    ZYPEZDItemValueSetter.setValue(npaa001001.thirdOrdMsgTxt, concatStr);
                } else if (i == 3) {
                    ZYPEZDItemValueSetter.setValue(npaa001001.frthOrdMsgTxt, concatStr);
                }
                break;
            }
            sliceStr = concatStr.substring(0, max);
            if (i == 0) {
                ZYPEZDItemValueSetter.setValue(npaa001001.firstOrdMsgTxt, sliceStr);
            } else if (i == 1) {
                ZYPEZDItemValueSetter.setValue(npaa001001.scdOrdMsgTxt, sliceStr);
            } else if (i == 2) {
                ZYPEZDItemValueSetter.setValue(npaa001001.thirdOrdMsgTxt, sliceStr);
            } else if (i == 3) {
                ZYPEZDItemValueSetter.setValue(npaa001001.frthOrdMsgTxt, sliceStr);
            }

            concatStr = concatStr.substring(max);

        }
        // if (msgAppendFlg) {
        //
        // if (msgCnt == 0) {
        // max = firstMaxDigit;
        // } else if (msgCnt == 1) {
        // max = scdMaxDigit;
        // } else if (msgCnt == 2) {
        // max = thirdMaxDigit;
        // } else if (msgCnt == 3) {
        // max = frthMaxDigit;
        // }
        //
        // if (sb.length() > max) {
        // sb.delete(max, sb.length());
        // }
        //
        // if (msgCnt == 0) {
        // ZYPEZDItemValueSetter.setValue(npaa001001.firstOrdMsgTxt,
        // sb.toString());
        // sb.setLength(0);
        // msgCnt++;
        // } else if (msgCnt == 1) {
        // ZYPEZDItemValueSetter.setValue(npaa001001.scdOrdMsgTxt,
        // sb.toString());
        // sb.setLength(0);
        // msgCnt++;
        // } else if (msgCnt == 2) {
        // ZYPEZDItemValueSetter.setValue(npaa001001.thirdOrdMsgTxt,
        // sb.toString());
        // sb.setLength(0);
        // msgCnt++;
        // } else if (msgCnt == 3) {
        // ZYPEZDItemValueSetter.setValue(npaa001001.frthOrdMsgTxt,
        // sb.toString());
        // sb.setLength(0);
        // msgCnt++;
        // }
        // }
        // 08/08/2017 CITS S.Endo Mod Sol#035(QC#18108) END
    }

    /**
     * Set Message Parts
     * @param msgMap S21ApiMessageMap
     * @param poInfoMap Map<String, Object>
     * @param npaa002001 NPAA0020_01TMsg
     */
    private void setMsgParts(S21ApiMessageMap msgMap, Map<String, Object> poInfoMap, NPAA0020_01TMsg npaa002001) {

        // 2017/12/11 T.Hakodate QC#21025 MOD START
        // List<Map<String, Object>> msgMapList = getMsgs(msgMap);

        StringBuilder sb = new StringBuilder();

        NPZC132001PMsg param = (NPZC132001PMsg) msgMap.getPmsg();

        String[] poMsgTpArray = new String[] {PO_MSG_TP.SHIPPER_NOTE, PO_MSG_TP.SPECIAL_INSTRUCTIONS };

        for (int i = 0; i < poMsgTpArray.length; i++) {

            List<PO_MSGTMsg> poMsgList = NPXC001001PoMsg.getPoMsg(param.glblCmpyCd.getValue(), poMsgTpArray[i], param.poOrdNum.getValue(), null);

            if (poMsgList != null && poMsgList.size() > 0) {

                String msg = NPXC001001PoMsg.concatenatePoMsg(poMsgList);

                sb = sb.append(msg);
                sb = sb.append(" ");
            }

        }

        if (sb == null) {
            return;
        }

        // 08/08/2017 CITS S.Endo Mod Sol#035(QC#18108) START
        // max digit
        // int firstMaxDigit =
        // npaa002001.getAttr("firstOrdMsgTxt").getDigit();
        // int scdMaxDigit =
        // npaa002001.getAttr("scdOrdMsgTxt").getDigit();
        // int thirdMaxDigit =
        // npaa002001.getAttr("thirdOrdMsgTxt").getDigit();
        // int frthMaxDigit =
        // npaa002001.getAttr("frthOrdMsgTxt").getDigit();
        // int max = 0 ;
        int max = npaa002001.getAttr("firstOrdMsgTxt").getDigit();

        int msgCnt = 0;

        // //boolean msgAppendFlg = false;
        // if (msgMapList == null || msgMapList.size() == 0) {
        // return;
        // }
        // for (Map<String, Object> map : msgMapList) {
        // sb = sb.append((String) map.get(PO_MSG_TXT));
        // // if (!msgAppendFlg) {
        // // sb.append((String) map.get(PO_MSG_TXT));
        // // msgAppendFlg = true;
        // // continue;
        // // } else {
        // // sb.append(" ");
        // // sb.append((String) map.get(PO_MSG_TXT));
        // // msgAppendFlg = false;
        // // }
        // //
        // // if (msgCnt == 0) {
        // // max = firstMaxDigit;
        // // } else if (msgCnt == 1) {
        // // max = scdMaxDigit;
        // // } else if (msgCnt == 2) {
        // // max = thirdMaxDigit;
        // // } else if (msgCnt == 3) {
        // // max = frthMaxDigit;
        // // }
        // //
        // // if (sb.length() > max) {
        // // sb.delete(max, sb.length());
        // // }
        // //
        // // if (msgCnt == 0) {
        // //
        // ZYPEZDItemValueSetter.setValue(npaa002001.firstOrdMsgTxt,
        // sb.toString());
        // // sb.setLength(0);
        // // msgCnt++;
        // // } else if (msgCnt == 1) {
        // // ZYPEZDItemValueSetter.setValue(npaa002001.scdOrdMsgTxt,
        // sb.toString());
        // // sb.setLength(0);
        // // msgCnt++;
        // // } else if (msgCnt == 2) {
        // //
        // ZYPEZDItemValueSetter.setValue(npaa002001.thirdOrdMsgTxt,
        // sb.toString());
        // // sb.setLength(0);
        // // msgCnt++;
        // // } else if (msgCnt == 3) {
        // // ZYPEZDItemValueSetter.setValue(npaa002001.frthOrdMsgTxt,
        // sb.toString());
        // // sb.setLength(0);
        // // msgCnt++;
        // // }
        // }
        // 2017/12/11 T.Hakodate QC#21025 MOD END

        String concatStr = sb.toString();
        // QC#31322
        concatStr = concatStr.replaceAll("\\r\\n|\\r|\\n|\\t", " ");
        msgCnt = concatStr.length() / max;
        String sliceStr;
        int loopCount = msgCnt;
        if (msgCnt > 3) {
            loopCount = 3;
        }
        for (int i = 0; i < loopCount + 1; i++) {
            if (concatStr.length() < max) {
                if (i == 0) {
                    ZYPEZDItemValueSetter.setValue(npaa002001.firstOrdMsgTxt, concatStr);
                } else if (i == 1) {
                    ZYPEZDItemValueSetter.setValue(npaa002001.scdOrdMsgTxt, concatStr);
                } else if (i == 2) {
                    ZYPEZDItemValueSetter.setValue(npaa002001.thirdOrdMsgTxt, concatStr);
                } else if (i == 3) {
                    ZYPEZDItemValueSetter.setValue(npaa002001.frthOrdMsgTxt, concatStr);
                }
                break;
            }
            sliceStr = concatStr.substring(0, max);
            if (i == 0) {
                ZYPEZDItemValueSetter.setValue(npaa002001.firstOrdMsgTxt, sliceStr);
            } else if (i == 1) {
                ZYPEZDItemValueSetter.setValue(npaa002001.scdOrdMsgTxt, sliceStr);
            } else if (i == 2) {
                ZYPEZDItemValueSetter.setValue(npaa002001.thirdOrdMsgTxt, sliceStr);
            } else if (i == 3) {
                ZYPEZDItemValueSetter.setValue(npaa002001.frthOrdMsgTxt, sliceStr);
            }

            concatStr = concatStr.substring(max);

        }

        // if (msgAppendFlg) {
        //
        // if (msgCnt == 0) {
        // max = firstMaxDigit;
        // } else if (msgCnt == 1) {
        // max = scdMaxDigit;
        // } else if (msgCnt == 2) {
        // max = thirdMaxDigit;
        // } else if (msgCnt == 3) {
        // max = frthMaxDigit;
        // }
        //
        // if (sb.length() > max) {
        // sb.delete(max, sb.length());
        // }
        //
        // if (msgCnt == 0) {
        // ZYPEZDItemValueSetter.setValue(npaa002001.firstOrdMsgTxt,
        // sb.toString());
        // sb.setLength(0);
        // msgCnt++;
        // } else if (msgCnt == 1) {
        // ZYPEZDItemValueSetter.setValue(npaa002001.scdOrdMsgTxt,
        // sb.toString());
        // sb.setLength(0);
        // msgCnt++;
        // } else if (msgCnt == 2) {
        // ZYPEZDItemValueSetter.setValue(npaa002001.thirdOrdMsgTxt,
        // sb.toString());
        // sb.setLength(0);
        // msgCnt++;
        // } else if (msgCnt == 3) {
        // ZYPEZDItemValueSetter.setValue(npaa002001.frthOrdMsgTxt,
        // sb.toString());
        // sb.setLength(0);
        // msgCnt++;
        // }
        // }
        // 08/08/2017 CITS S.Endo Mod Sol#035(QC#18108) END
    }

    /**
     * Get Msgs
     * @param msgMap
     * @param onBatchType
     * @return
     */
    private List<Map<String, Object>> getMsgs(S21ApiMessageMap msgMap) {

        NPZC132001PMsg param = (NPZC132001PMsg) msgMap.getPmsg();

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
        ssmParam.put(BIND_PO_ORD_NUM, param.poOrdNum.getValue());
        ssmParam.put(BIND_PO_MSG_TP_CD_SI, PO_MSG_TP.SPECIAL_INSTRUCTIONS);
        ssmParam.put(BIND_PO_MSG_TP_CD_SN, PO_MSG_TP.SHIPPER_NOTE);

        return ssmBatchClient.queryObjectList("getMsgs", ssmParam);

    }

    // QC#23356 Add Start
    // QC#23356 Add Start
    /**
     * searchVndShipToXref
     * @param poInfoMap
     * @param msgMap
     * @return carrAcctNum
     */
    private String getCarrAcctNum(Map<String, Object> poInfoMap, S21ApiMessageMap msgMap) {

        String carrAcctNum = null;
        String carrTpCd = null;
        String prchGrpCd = null;
        boolean cpoFlg = false;

        NPZC132001PMsg param = (NPZC132001PMsg) msgMap.getPmsg();

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        Map<String, Object> cpoInfoMap = new HashMap<String, Object>();
        Map<String, Object> carrXrefMap = new HashMap<String, Object>();

        if (poInfoMap.get(CARR_ACCT_NUM) == null) {

            if (poInfoMap.get(CARR_CD) != null) {

                // Search VND
                ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                ssmParam.put(BIND_CARR_CD, (String) poInfoMap.get(CARR_CD));

                // Get Carrier Type Code From VND
                carrTpCd = (String) ssmBatchClient.queryObject("getcarrTpCd", ssmParam);

                // Other -DS_ORD_CATG_CD / DS_ORD_TP_CD
                if (PO_ORD_SRC.SALES_ORDER.equals(poInfoMap.get(PO_ORD_SRC_CD))) {
                    ssmParam.clear();
                    // Search Order Category/Reason from CPO
                    ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                    ssmParam.put(BIND_CPO_ORD_NUM, (String) poInfoMap.get(CPO_ORD_NUM));

                    cpoInfoMap = (Map<String, Object>) ssmBatchClient.queryObject("getCpoInfo", ssmParam);

                    cpoFlg = true;

                }
                // Other -PRCH_GRP_CD
                if (poInfoMap.get(PRCH_GRP_CD) != null) {
                    prchGrpCd = (String) poInfoMap.get(PRCH_GRP_CD);
                }

                if (carrTpCd == null) {
                    return null;
                }

                // search 1
                ssmParam.clear();

                ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                ssmParam.put(BIND_CARR_TP_CD, carrTpCd);

                if (carrAcctNum == null && cpoFlg) {
                    ssmParam.put(BIND_DS_ORD_CATG_CD, (String) cpoInfoMap.get(DS_ORD_CATG_CD));
                    // QC#58769
//                    ssmParam.put(BIND_DS_ORD_RSN_CD, (String) cpoInfoMap.get(DS_ORD_RSN_CD));
                    ssmParam.put(BIND_DS_ORD_TP_CD, (String) cpoInfoMap.get(DS_ORD_TP_CD));
                    ssmParam.put(BIND_PRCH_GRP_CD, prchGrpCd);
                    ssmParam.put(BIND_PO_ORD_SRC_CD, (String) cpoInfoMap.get(PO_ORD_SRC_CD));

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get("CARR_ACCT_NUM");
                        carrCdfromXref = (String) carrXrefMap.get("CARR_CD");
                    }
                }
                if (carrAcctNum == null && cpoFlg) {
                    // search 2
                    // QC#58769
//                    ssmParam.put(BIND_DS_ORD_RSN_CD, STAR);
                    ssmParam.put(BIND_DS_ORD_TP_CD, STAR);
                    ssmParam.put(BIND_DS_ORD_CATG_CD, (String) cpoInfoMap.get(DS_ORD_CATG_CD));

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get("CARR_ACCT_NUM");
                        carrCdfromXref = (String) carrXrefMap.get("CARR_CD");
                    }
                }
                if (carrAcctNum == null && cpoFlg) {
                    // search 3
                    ssmParam.put(BIND_DS_ORD_CATG_CD, STAR);
                    // QC#58769
//                    ssmParam.put(BIND_DS_ORD_RSN_CD, (String) cpoInfoMap.get(DS_ORD_RSN_CD));
                    ssmParam.put(BIND_DS_ORD_TP_CD, (String) cpoInfoMap.get(DS_ORD_TP_CD));

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get("CARR_ACCT_NUM");
                        carrCdfromXref = (String) carrXrefMap.get("CARR_CD");
                    }
                }
                if (carrAcctNum == null && cpoFlg) {
                    // search 4
                    // QC#58769
//                    ssmParam.put(BIND_DS_ORD_RSN_CD, STAR);
                    ssmParam.put(BIND_DS_ORD_TP_CD, STAR);

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get("CARR_ACCT_NUM");
                        carrCdfromXref = (String) carrXrefMap.get("CARR_CD");
                    }
                }
                if (carrAcctNum == null && cpoFlg) {
                    // search 5
                    ssmParam.put(BIND_PRCH_GRP_CD, STAR);
                    ssmParam.put(BIND_DS_ORD_CATG_CD, (String) cpoInfoMap.get(DS_ORD_CATG_CD));
                    // QC#58769
//                    ssmParam.put(BIND_DS_ORD_RSN_CD, (String) cpoInfoMap.get(DS_ORD_RSN_CD));
                    ssmParam.put(BIND_DS_ORD_TP_CD, (String) cpoInfoMap.get(DS_ORD_TP_CD));

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get("CARR_ACCT_NUM");
                        carrCdfromXref = (String) carrXrefMap.get("CARR_CD");
                    }
                }
                if (carrAcctNum == null && cpoFlg) {
                    // search 6
                    // QC#58769
//                    ssmParam.put(BIND_DS_ORD_RSN_CD, STAR);
                    ssmParam.put(BIND_DS_ORD_TP_CD, STAR);

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get("CARR_ACCT_NUM");
                        carrCdfromXref = (String) carrXrefMap.get("CARR_CD");
                    }
                }
                if (carrAcctNum == null && cpoFlg) {
                    // search 7
                    ssmParam.put(BIND_DS_ORD_CATG_CD, STAR);
                    // QC#58769
//                    ssmParam.put(BIND_DS_ORD_RSN_CD, cpoInfoMap.get(DS_ORD_RSN_CD));
                    ssmParam.put(BIND_DS_ORD_TP_CD, cpoInfoMap.get(DS_ORD_TP_CD));

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get("CARR_ACCT_NUM");
                        carrCdfromXref = (String) carrXrefMap.get("CARR_CD");
                    }
                }
                if (carrAcctNum == null && cpoFlg) {
                    // search 8
                    // QC#58769
//                    ssmParam.put(BIND_DS_ORD_RSN_CD, STAR);
                    ssmParam.put(BIND_DS_ORD_TP_CD, STAR);

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get("CARR_ACCT_NUM");
                        carrCdfromXref = (String) carrXrefMap.get("CARR_CD");
                    }
                }
                if (carrAcctNum == null) {
                    // search 9
                    ssmParam.put(BIND_PO_ORD_SRC_CD, STAR);
                    ssmParam.put(BIND_PRCH_GRP_CD, prchGrpCd);

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get("CARR_ACCT_NUM");
                        carrCdfromXref = (String) carrXrefMap.get("CARR_CD");
                    }
                }
                if (carrAcctNum == null) {
                    // search 10
                    ssmParam.put(BIND_PRCH_GRP_CD, STAR);

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get("CARR_ACCT_NUM");
                        carrCdfromXref = (String) carrXrefMap.get("CARR_CD");
                    }
                }

            } else {

                // Other -DS_ORD_CATG_CD / DS_ORD_TP_CD
                if (PO_ORD_SRC.SALES_ORDER.equals(poInfoMap.get(PO_ORD_SRC_CD))) {
                    // Search Order Category/Reason from CPO
                    ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                    ssmParam.put(BIND_CPO_ORD_NUM, (String) poInfoMap.get(CPO_ORD_NUM));

                    cpoInfoMap = (Map<String, Object>) ssmBatchClient.queryObject("getCpoInfo", ssmParam);

                    cpoFlg = true;
                }
                // Other -PRCH_GRP_CD
                if (poInfoMap.get(PRCH_GRP_CD) != null) {
                    prchGrpCd = (String) poInfoMap.get(PRCH_GRP_CD);

                }

                // Search CARR_TP_CD from TRD_PTNR_SHPG_X_REF
                Map<String, Object> trdPtnrShpgXRefMap = new HashMap<String, Object>();

                trdPtnrShpgXRefMap = getTrdPtnrShpgXRefInfo(poInfoMap, msgMap);

                if (trdPtnrShpgXRefMap != null) {
                    carrTpCd = (String) trdPtnrShpgXRefMap.get(CARR_TP_CD);
                }

                if (carrTpCd == null) {
                    return null;
                }

                // search 1
                ssmParam.clear();

                ssmParam.put(BIND_GLBL_CMPY_CD, param.glblCmpyCd.getValue());
                ssmParam.put(BIND_CARR_TP_CD, carrTpCd);

                if (carrAcctNum == null && cpoFlg) {
                    ssmParam.put(BIND_DS_ORD_CATG_CD, (String) cpoInfoMap.get(DS_ORD_CATG_CD));
                    // QC#58769
//                    ssmParam.put(BIND_DS_ORD_RSN_CD, (String) cpoInfoMap.get(DS_ORD_RSN_CD));
                    ssmParam.put(BIND_DS_ORD_TP_CD, (String) cpoInfoMap.get(DS_ORD_TP_CD));
                    ssmParam.put(BIND_PRCH_GRP_CD, prchGrpCd);
                    ssmParam.put(BIND_PO_ORD_SRC_CD, (String) cpoInfoMap.get(PO_ORD_SRC_CD));

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get("CARR_ACCT_NUM");
                        carrCdfromXref = (String) carrXrefMap.get("CARR_CD");
                    }
                }
                if (carrAcctNum == null && cpoFlg) {
                    // search 2
                    // QC#58769
//                    ssmParam.put(BIND_DS_ORD_RSN_CD, STAR);
                    ssmParam.put(BIND_DS_ORD_TP_CD, STAR);
                    ssmParam.put(BIND_DS_ORD_CATG_CD, (String) cpoInfoMap.get(DS_ORD_CATG_CD));

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get("CARR_ACCT_NUM");
                        carrCdfromXref = (String) carrXrefMap.get("CARR_CD");
                    }
                }
                if (carrAcctNum == null && cpoFlg) {
                    // search 3
                    ssmParam.put(BIND_DS_ORD_CATG_CD, STAR);
                    // QC#58769
//                    ssmParam.put(BIND_DS_ORD_RSN_CD, (String) cpoInfoMap.get(DS_ORD_RSN_CD));
                    ssmParam.put(BIND_DS_ORD_TP_CD, (String) cpoInfoMap.get(DS_ORD_TP_CD));

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get("CARR_ACCT_NUM");
                        carrCdfromXref = (String) carrXrefMap.get("CARR_CD");
                    }
                }
                if (carrAcctNum == null && cpoFlg) {
                    // search 4
                    // QC#58769
//                    ssmParam.put(BIND_DS_ORD_RSN_CD, STAR);
                    ssmParam.put(BIND_DS_ORD_TP_CD, STAR);

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get("CARR_ACCT_NUM");
                        carrCdfromXref = (String) carrXrefMap.get("CARR_CD");
                    }
                }
                if (carrAcctNum == null && cpoFlg) {
                    // search 5
                    ssmParam.put(BIND_PRCH_GRP_CD, STAR);
                    ssmParam.put(BIND_DS_ORD_CATG_CD, (String) cpoInfoMap.get(DS_ORD_CATG_CD));
                    // QC#58769
//                    ssmParam.put(BIND_DS_ORD_RSN_CD, (String) cpoInfoMap.get(DS_ORD_RSN_CD));
                    ssmParam.put(BIND_DS_ORD_TP_CD, (String) cpoInfoMap.get(DS_ORD_TP_CD));

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get("CARR_ACCT_NUM");
                        carrCdfromXref = (String) carrXrefMap.get("CARR_CD");
                    }
                }
                if (carrAcctNum == null && cpoFlg) {
                    // search 6
                    // QC#58769
//                    ssmParam.put(BIND_DS_ORD_RSN_CD, STAR);
                    ssmParam.put(BIND_DS_ORD_TP_CD, STAR);

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get("CARR_ACCT_NUM");
                        carrCdfromXref = (String) carrXrefMap.get("CARR_CD");
                    }
                }
                if (carrAcctNum == null && cpoFlg) {
                    // search 7
                    ssmParam.put(BIND_DS_ORD_CATG_CD, STAR);
                    // QC#58769
//                    ssmParam.put(BIND_DS_ORD_RSN_CD, cpoInfoMap.get(DS_ORD_RSN_CD));
                    ssmParam.put(BIND_DS_ORD_TP_CD, cpoInfoMap.get(DS_ORD_TP_CD));

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get("CARR_ACCT_NUM");
                        carrCdfromXref = (String) carrXrefMap.get("CARR_CD");
                    }
                }
                if (carrAcctNum == null && cpoFlg) {
                    // search 8
                    // QC#58769
//                    ssmParam.put(BIND_DS_ORD_RSN_CD, STAR);
                    ssmParam.put(BIND_DS_ORD_TP_CD, STAR);

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get("CARR_ACCT_NUM");
                        carrCdfromXref = (String) carrXrefMap.get("CARR_CD");
                    }
                }
                if (carrAcctNum == null) {
                    // search 9
                    ssmParam.put(BIND_PO_ORD_SRC_CD, STAR);
                    ssmParam.put(BIND_PRCH_GRP_CD, prchGrpCd);

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get("CARR_ACCT_NUM");
                        carrCdfromXref = (String) carrXrefMap.get("CARR_CD");
                    }
                }
                if (carrAcctNum == null) {
                    // search 10
                    ssmParam.put(BIND_PRCH_GRP_CD, STAR);

                    carrXrefMap = (Map<String, Object>) ssmBatchClient.queryObject("getCarrAcctNum", ssmParam);

                    if (carrXrefMap != null) {
                        carrAcctNum = (String) carrXrefMap.get("CARR_ACCT_NUM");
                        carrCdfromXref = (String) carrXrefMap.get("CARR_CD");
                    }
                }
            }
        } else {
            carrAcctNum = (String) poInfoMap.get(CARR_ACCT_NUM);
        }

        return carrAcctNum;
    }

    // QC#23356 Add End

    // START 2019/12/04 M.Naito [QC#54563-1,ADD]
    private static void convertCharacterForPoInfo(Map<String, Object> poInfoMap) {
        if (ZYPCommonFunc.hasValue((String) poInfoMap.get(CTAC_PSN_NM))) {
            poInfoMap.put(CTAC_PSN_NM, ZYPCharacterConversionUtil.convertCharacter((String) poInfoMap.get(CTAC_PSN_NM)));
        }
    }
    // END 2019/12/04 M.Naito [QC#54563-1,ADD]

}
