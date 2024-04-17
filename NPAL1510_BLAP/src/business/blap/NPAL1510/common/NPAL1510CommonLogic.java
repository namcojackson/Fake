/**
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 */
package business.blap.NPAL1510.common;

import static business.blap.NPAL1510.constant.NPAL1510Constant.BUSINESS_APPL_ID;
import static business.blap.NPAL1510.constant.NPAL1510Constant.NPAM0001W;
import static business.blap.NPAL1510.constant.NPAL1510Constant.NPAM0089E;
import static business.blap.NPAL1510.constant.NPAL1510Constant.REPLACE_CHAR;
import static business.blap.NPAL1510.constant.NPAL1510Constant.RS_SRCH_OPT_NM;
import static business.blap.NPAL1510.constant.NPAL1510Constant.RS_SRCH_OPT_PK;
import static business.blap.NPAL1510.constant.NPAL1510Constant.ZZZM9003I;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parts.common.EZDCMsg;
import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NPAL1510.NPAL1510CMsg;
import business.blap.NPAL1510.NPAL1510Query;
import business.blap.NPAL1510.NPAL1510SMsg;
import business.blap.NPAL1510.NPAL1510_ASMsg;
import business.db.COA_PRODTMsg;
import business.db.COA_PROJTMsg;
import business.parts.NSZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC033001.NSZC033001;
import com.canon.cusa.s21.api.NSZ.NSZC033001.constant.NSZC033001Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_PO_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_APVL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MDSE_CMPSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_ORD_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_SVC_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_PO_ACK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Business ID : NPAL1510 PO Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/08   CUSA            K.Ogino         Create          N/A
 * 2017/08/24   CITS            S.Katsuma       Update          QC#20678
 * 2017/11/16   CITS            S.Katsuma       Update          QC#22296
 * 2018/01/30   CITS            K.Mishiro       Update          QC#22521
 * 2018/02/27   CITS            T.Gotoda        Update          QC#21944
 * 2018/02/27   CITS            T.Gotoda        Update          QC#22521-2
 * 2022/05/19   Hitachi         A.Kohinata      Update          QC#57934
 * 2023/02/09   Hitachi         TZ.Win          Update          QC#60966
 *</pre>
 */
public class NPAL1510CommonLogic {

    /**
     * @param bizMsg NPAL1510CMsg
     */
    public static void setSavedSearchOptionPullDown(NPAL1510CMsg bizMsg) {
        // Clear pulldown list
        bizMsg.srchOptPk_PD.clear();
        bizMsg.srchOptNm_PD.clear();

        S21SsmEZDResult ssmResult = NPAL1510Query.getInstance().getSavedSearchOptionList();
        if (ssmResult.isCodeNormal()) {
            List<Map<String, Object>> list = (List) ssmResult.getResultObject();
            for (int i = 0; i < list.size(); i++) {
                Map<String, Object> map = list.get(i);
                bizMsg.srchOptPk_PD.no(i).setValue(new BigDecimal(map.get(RS_SRCH_OPT_PK).toString()));
                bizMsg.srchOptNm_PD.no(i).setValue(map.get(RS_SRCH_OPT_NM).toString());
            }
        }
    }

    /**
     * @param bizMsg NPAL1510CMsg
     */
    public static void setPoSourceList(NPAL1510CMsg bizMsg) {
        ZYPCodeDataUtil.createPulldownList(PO_ORD_SRC.class, bizMsg.poOrdSrcCd_PD, bizMsg.poOrdSrcDescTxt_PD);
    }

    /**
     * @param bizMsg NPAL1510CMsg
     */
    public static void setPoTypeList(NPAL1510CMsg bizMsg) {
        ZYPCodeDataUtil.createPulldownList(DS_PO_TP.class, bizMsg.dsPoTpCd_PD, bizMsg.dsPoTpDescTxt_PD);
    }

    /**
     * @param bizMsg NPAL1510CMsg
     */
    public static void setPoHederStatusList(NPAL1510CMsg bizMsg) {
        ZYPCodeDataUtil.createPulldownList(PO_HDR_STS.class, bizMsg.poHdrStsCd_PD, bizMsg.poHdrStsDescTxt_PD);
    }

    /**
     * @param bizMsg NPAL1510CMsg
     */
    public static void setApprovalStatusList(NPAL1510CMsg bizMsg) {
        ZYPCodeDataUtil.createPulldownList(PO_APVL_STS.class, bizMsg.poApvlStsCd_PD, bizMsg.poApvlStsDescTxt_PD);
    }

    /**
     * @param bizMsg NPAL1510CMsg
     */
    public static void setRequestedShipMethodList(NPAL1510CMsg bizMsg) {
        ZYPCodeDataUtil.createPulldownList(SHPG_SVC_LVL.class, bizMsg.shpgSvcLvlCd_PD, bizMsg.shpgSvcLvlDescTxt_PD);
    }

    /**
     * @param bizMsg NPAL1510CMsg
     */
    public static void setPoLineStatusList(NPAL1510CMsg bizMsg) {
        ZYPCodeDataUtil.createPulldownList(PO_LINE_STS.class, bizMsg.poLineStsCd_PD, bizMsg.poLineStsDescTxt_PD);
    }

    /**
     * @param bizMsg NPAL1510CMsg
     */
    public static void setVendorPoAckStatusList(NPAL1510CMsg bizMsg) {
        ZYPCodeDataUtil.createPulldownList(VND_PO_ACK_STS.class, bizMsg.vndPoAckStsCd_PD, bizMsg.vndPoAckStsDescTxt_PD);
    }

    /**
     * @param bizMsg NPAL1510CMsg
     * @param searchCond1 Input Search Flag1
     * @param searchCond2 Input Search Flag2
     * @param glblMsg NPAL1510SMsg
     */
    public static void setDispLevelPoSearchList(NPAL1510CMsg bizMsg, String searchCond1, String searchCond2, NPAL1510SMsg glblMsg) {
        S21SsmEZDResult ssmResult = null;
        String displayLevel = bizMsg.xxDplyByCtrlItemCdNm.getValue();
        if ("PO".equals(displayLevel)) {
            ssmResult = NPAL1510Query.getInstance().getDispLevelPoHeaderSearchList(bizMsg, searchCond1, searchCond2, glblMsg.A.length() + 1);
        } else if ("Detail".equals(displayLevel)) {
            ssmResult = NPAL1510Query.getInstance().getDispLevelPoDetailSearchList(bizMsg, searchCond1, searchCond2, glblMsg.A.length() + 1);
        } else if ("ACK".equals(displayLevel)) {
            ssmResult = NPAL1510Query.getInstance().getDispLevelPoAckSearchList(bizMsg, searchCond1, searchCond2, glblMsg.A.length() + 1);
        } else {
            ssmResult = NPAL1510Query.getInstance().getDispLevelPoDetailSearchList(bizMsg, searchCond1, searchCond2, glblMsg.A.length() + 1);
        }
        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > glblMsg.A.length()) {
                bizMsg.setMessageInfo(NPAM0001W);
                queryResCnt = glblMsg.A.length();
            }
            List<NPAL1510_ASMsg> list = (List) ssmResult.getResultObject();
            setSearchResultMap(glblMsg, list, queryResCnt);
            glblMsg.A.setValidCount(queryResCnt);

            // Copy from SMsg to cMsg
            int i = 0;
            for (; i < queryResCnt; i++) {
                if (i == bizMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(glblMsg.A.no(i), null, bizMsg.A.no(i), null);
            }
            bizMsg.A.setValidCount(i);
            // Set page number
            bizMsg.xxPageShowFromNum.setValue(1);
            bizMsg.xxPageShowToNum.setValue(bizMsg.A.getValidCount());
            bizMsg.xxPageShowOfNum.setValue(queryResCnt);
        } else {
            bizMsg.setMessageInfo(NPAM0089E);
            bizMsg.xxPageShowFromNum.clear();
            bizMsg.xxPageShowToNum.clear();
            bizMsg.xxPageShowOfNum.clear();
            return;
        }
    }

    private static void setSearchResultMap(NPAL1510SMsg glblMsg, List<NPAL1510_ASMsg> list, int queryResCnt) {
        String poNumPre = "";
        String poLineNumPre = "";
        for (int i = 0; i < queryResCnt; i++) {
            NPAL1510_ASMsg bean = list.get(i);
            String poNum = bean.poOrdNum_A1.getValue();
            if (!poNumPre.equals(poNum)) {
                // Header
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).poOrdNum_A1, bean.poOrdNum_A1.getValue());
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).dsPoTpDescTxt_A1, bean.dsPoTpDescTxt_A1.getValue());
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).poHdrStsDescTxt_A1, bean.poHdrStsDescTxt_A1.getValue());
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).poApvlStsDescTxt_A1, bean.poApvlStsDescTxt_A1.getValue());
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).shipToCustCd_A1, bean.shipToCustCd_A1.getValue());
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).shipToCustLocNm_A1, bean.shipToCustLocNm_A1.getValue());
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).destRtlWhCd_A1, bean.destRtlWhCd_A1.getValue());
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).shpgSvcLvlDescTxt_A1, bean.shpgSvcLvlDescTxt_A1.getValue());
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).prchReqNum_A1, bean.prchReqNum_A1.getValue());
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).trxRefNum_A1, bean.trxRefNum_A1.getValue());
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).origPoOrdNum_A1, bean.origPoOrdNum_A1.getValue());
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).poSendTs_A0, bean.poSendTs_A0.getValue());
            }
            // Detail
            String dispLineNum = bean.dispPoDtlLineNum_A1.getValue();

            if (!poLineNumPre.equals(dispLineNum) || !poNumPre.equals(poNum)) {
                if (ZYPCommonFunc.hasValue(dispLineNum)) {
                    if (dispLineNum.indexOf(".") == -1) {
                        // Not Period = parent item or regular item.
                        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).dispPoDtlLineNum_A1, dispLineNum + ".0");
                    } else {
                        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).dispPoDtlLineNum_A1, bean.dispPoDtlLineNum_A1.getValue());
                    }
                }
                poLineNumPre = dispLineNum;
            }

            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).poLineTpDescTxt_A1, bean.poLineTpDescTxt_A1.getValue());

            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).mdseCd_A0, bean.mdseCd_A0.getValue());
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxMdseCd_A0, bean.xxMdseCd_A0.getValue());
            
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).aslMdseCd_A1, bean.aslMdseCd_A1.getValue());
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).mdseDescShortTxt_A1, bean.mdseDescShortTxt_A1.getValue());
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).entDealNetUnitPrcAmt_A1, bean.entDealNetUnitPrcAmt_A1.getValue());
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).poDispQty_A1, bean.poDispQty_A1.getValue());
            // QC#21944 MOD START
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).poSubmtDt_A1, bean.poSubmtDt_A1.getValue());
            // QC#21944 MOD END
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).destRtlSwhCd_A1, bean.destRtlSwhCd_A1.getValue());
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).poMatchTpDescTxt_A1, bean.poMatchTpDescTxt_A1.getValue());
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).poLineStsDescTxt_A1, bean.poLineStsDescTxt_A1.getValue());
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).entPoDtlDealNetAmt_A1, bean.entPoDtlDealNetAmt_A1.getValue());
            if (!(PO_MDSE_CMPSN_TP.PARENT.equals(bean.poMdseCmpsnTpCd_A1.getValue()))) {
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).poRcvQty_A1, bean.poRcvQty_A1.getValue());
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).poInvQty_A1, bean.poInvQty_A1.getValue());
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).poCancQty_A1, bean.poCancQty_A1.getValue());
                // add start 2022/05/19 QC#57934
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).poRcvQty_WO, bean.poRcvQty_WO.getValue());
                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).poInvQty_WO, bean.poInvQty_WO.getValue());
                // add end 2022/05/19 QC#57934
            }
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).prchReqLineNum_A1, bean.prchReqLineNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).trxRefLineNum_A1, bean.trxRefLineNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).origDispPoDtlLineNum_A1, bean.origDispPoDtlLineNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).poAckNum_A1, bean.poAckNum_A1.getValue());
            // QC#21944 MOD START
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).poSendDt_A1, bean.poSendDt_A1.getValue());
            // QC#21944 MOD END
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).vndPoAckStsCd_A1, bean.vndPoAckStsCd_A1.getValue());
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).vndPoAckLineStsTxt_A1, bean.vndPoAckLineStsTxt_A1.getValue());
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).vndPoAckStsDescTxt_A1, bean.vndPoAckStsDescTxt_A1.getValue());
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxDt10Dt_A1, bean.xxDt10Dt_A1.getValue());
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).ordQty_A1, bean.ordQty_A1.getValue());
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).xxTotAmt_A1, bean.xxTotAmt_A1.getValue());
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).etdDt_A1, bean.etdDt_A1.getValue());
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).etaDt_A1, bean.etaDt_A1.getValue());
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).vndOtbdCarrNm_A1, bean.vndOtbdCarrNm_A1.getValue());
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).proNum_A1, bean.proNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).vndSoNum_A1, bean.vndSoNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).vndSoSlpNum_A1, bean.vndSoSlpNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).vndInvtyLocCd_A1, bean.vndInvtyLocCd_A1.getValue());
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).vndShipToCustLocNm_A1, bean.vndShipToCustLocNm_A1.getValue());
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).vndSellToCustLocNm_A1, bean.vndSellToCustLocNm_A1.getValue());
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).thisMthFobCostAmt_A1, bean.thisMthFobCostAmt_A1.getValue());
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).vndChildBomPrcAmt_A1, bean.vndChildBomPrcAmt_A1.getValue());
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).vndCpoOrdNum_A1, bean.vndCpoOrdNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).vndIssPoOrdNum_A1, bean.vndIssPoOrdNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).vndOtbdCarrCd_A1, bean.vndOtbdCarrCd_A1.getValue());
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).shipToCustCd_A1, bean.shipToCustCd_A1.getValue());
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).shipToCustLocNm_A1, bean.shipToCustLocNm_A1.getValue());
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).destRtlWhCd_A1, bean.destRtlWhCd_A1.getValue());
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).shpgSvcLvlDescTxt_A1, bean.shpgSvcLvlDescTxt_A1.getValue());
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).prchReqNum_A1, bean.prchReqNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).trxRefNum_A1, bean.trxRefNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).origPoOrdNum_A1, bean.origPoOrdNum_A1.getValue());
            // ACK
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).vndCpoDtlLineNum_A1, bean.vndCpoDtlLineNum_A1.getValue());
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).vndCpoDtlLineSubNum_A1, bean.vndCpoDtlLineSubNum_A1.getValue());
            // QC:11819
            if (ZYPCommonFunc.hasValue((bean.proNum_A1.getValue())) && ZYPCommonFunc.hasValue((bean.carrTrkUrl_A1.getValue()))) {
                editCarrTrkUrl(bean);
            }
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).carrTrkUrl_A1, bean.carrTrkUrl_A1.getValue());
            poNumPre = poNum;
            // START 2018/01/30 K.Mishiro [QC#22521,ADD]
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).prntVndCd_A1, bean.prntVndCd_A1.getValue());
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).prntVndNm_A1, bean.prntVndNm_A1.getValue());
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).vndCd_A1, bean.vndCd_A1.getValue());
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).vndNm_A1, bean.vndNm_A1.getValue());
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).coaMdseTpCd_A1, bean.coaMdseTpCd_A1.getValue());
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).coaProdCd_A1, bean.coaProdCd_A1.getValue());
            // END 2018/01/30 K.Mishiro [QC#22521,ADD]
            // START 2023/02/13 TZ.Win [QC#60966, ADD]
            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(i).rqstShipDt_A1, bean.rqstShipDt_A1.getValue());
            // END 2023/02/13 TZ.Win [QC#60966, ADD]
        }
    }
    

    /**
     * <pre>
     * editCarrTrkUrl      
     * ADD QC:11819
     * </pre>
     * @param bean NPAL1510_ASMsg
     */
    private static void editCarrTrkUrl(NPAL1510_ASMsg bean) {
        String url = bean.carrTrkUrl_A1.getValue();
        Pattern pattern = Pattern.compile(REPLACE_CHAR);
        Matcher matcher = pattern.matcher(url);
        String str = matcher.replaceAll(bean.proNum_A1.getValue());
        bean.carrTrkUrl_A1.setValue(str);
    }
    

    /**
     * check duplicate search name
     * @param cMsg NPAL1090CMsg
     * @return boolean
     */
    public static boolean isExistSaveSearchName(NPAL1510CMsg cMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_TX)) {
            return false;
        }

        for (int i = 0; i < cMsg.srchOptNm_PD.length(); i++) {
            if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_PD.no(i))) {
                return false;
            }
            if (cMsg.srchOptNm_TX.getValue().equals(cMsg.srchOptNm_PD.no(i).getValue())) {
                if (ZYPCommonFunc.hasValue(cMsg.srchOptPk_SL) //
                        && cMsg.srchOptPk_SL.getValue().compareTo(cMsg.srchOptPk_PD.no(i).getValue()) == 0) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Call API NSZC033001 (Save SearchOption)
     * @param cMsg NPAL1090CMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSaveSearchOption(NPAL1510CMsg cMsg, String usrId, String glblCmpyCd) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_INSERT_UPDATE);

        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_TX) || isSameSaveSearchName(cMsg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_SL);
        }
        if (ZYPCommonFunc.hasValue(cMsg.srchOptNm_TX)) {
            ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, cMsg.srchOptNm_TX);
        } else {
            setSelectSaveSearchName(pMsg, cMsg);
        }

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BUSINESS_APPL_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);

        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_01, cMsg.poOrdNum);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_02, cMsg.poOrdSrcCd_SL);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_03, cMsg.dsPoTpCd_SL);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_04, cMsg.poHdrStsCd_SL);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_05, cMsg.poApvlStsCd_SL);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_06, cMsg.trxRefNum);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_07, cMsg.shpgSvcLvlCd_SL);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_08, cMsg.fullPsnNm);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_09, cMsg.prntVndCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_10, cMsg.prntVndNm);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_11, cMsg.vndCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_12, cMsg.vndNm);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_13, cMsg.destRtlWhCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_14, cMsg.rtlWhNm);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_15, cMsg.destRtlSwhCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_16, cMsg.rtlSwhNm);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_17, cMsg.shipToCustCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_18, cMsg.shipToCustLocNm);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_19, cMsg.poLineStsCd_SL);
        pMsg.srchOptTxt_20.setValue(cMsg.poSubmtDt_FR.getValue());
        pMsg.srchOptTxt_21.setValue(cMsg.poSubmtDt_TO.getValue());
        pMsg.srchOptTxt_22.setValue(cMsg.xxDt10Dt_FR.getValue());
        pMsg.srchOptTxt_23.setValue(cMsg.xxDt10Dt_TO.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_24, cMsg.vndPoAckStsCd_SL);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_25, cMsg.poAckNum);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_26, cMsg.vndIssOrdNum);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_27, cMsg.proNum);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_28, cMsg.vndSoNum);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_29, cMsg.vndInvtyLocCd);
        pMsg.srchOptTxt_30.setValue(cMsg.xxOrdDt_FR.getValue());
        pMsg.srchOptTxt_31.setValue(cMsg.xxOrdDt_TO.getValue());
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_32, (cMsg.xxDplyByCtrlItemCdNm));
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_33, (cMsg.mdseCd_SB));
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_34, (cMsg.mdseCd));
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_35, (cMsg.aslMdseCd));
        // START 2017/08/24 S.Katsuma [QC#20678,ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_36, cMsg.prchReqNum);
        // END 2017/08/24 S.Katsuma [QC#20678,ADD]
        // START 2018/01/31 K.Mishiro [QC#22521,ADD]
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_37, cMsg.coaMdseTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_38, cMsg.coaProjDescTxt);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_39, cMsg.coaProdCd);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_40, cMsg.coaProdNm);
        // END 2018/01/31 K.Mishiro [QC#22521,ADD]
        // add start 2022/05/19 QC#57934
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptTxt_41, cMsg.xxChkBox_H1);
        // add end 2022/05/19 QC#57934
        // START 2023/02/09 TZ.Win [QC#60966, ADD]
        pMsg.srchOptTxt_42.setValue(cMsg.rqstShipDt_FR.getValue());
        pMsg.srchOptTxt_43.setValue(cMsg.rqstShipDt_TO.getValue());
        // END 2023/02/09 TZ.Win [QC#60966, ADD]

        if (executeNszc0330(cMsg, pMsg)) {
            cMsg.srchOptUsrId_U1.setValue(usrId);
            setSavedSearchOptionPullDown(cMsg);
            ZYPEZDItemValueSetter.setValue(cMsg.srchOptPk_SL, pMsg.srchOptPk);
            cMsg.setMessageInfo(ZZZM9003I, new String[] {"Save Search" });
        }
    }

    /**
     * isSameSaveSearchName
     * @param cMsg NPAL1090CMsg
     * @return boolean
     */
    private static boolean isSameSaveSearchName(NPAL1510CMsg cMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_SL)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_TX)) {
            return false;
        }

        for (int i = 0; i < cMsg.srchOptNm_PD.length(); i++) {
            if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_PD.no(i))) {
                return false;
            }
            if (cMsg.srchOptPk_SL.getValue().compareTo(cMsg.srchOptPk_PD.no(i).getValue()) == 0) {
                if (cMsg.srchOptNm_TX.getValue().equals(cMsg.srchOptNm_PD.no(i).getValue())) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    /**
     * setSelectSaveSearchName
     * @param pMsg NSZC033001PMsg
     * @param cMsg NPAL1090CMsg
     */
    public static void setSelectSaveSearchName(NSZC033001PMsg pMsg, NPAL1510CMsg cMsg) {
        if (!ZYPCommonFunc.hasValue(cMsg.srchOptPk_SL)) {
            return;
        }

        for (int i = 0; i < cMsg.srchOptNm_PD.length(); i++) {
            if (!ZYPCommonFunc.hasValue(cMsg.srchOptNm_PD.no(i))) {
                return;
            }
            if (cMsg.srchOptPk_SL.getValue().compareTo(cMsg.srchOptPk_PD.no(i).getValue()) == 0) {
                ZYPEZDItemValueSetter.setValue(pMsg.srchOptNm, cMsg.srchOptNm_PD.no(i));
            }
        }
        return;
    }

    /**
     * Execute API NSZC033001
     * @param bizMsg NPAL1090CMsg
     * @param pMsg NSZC033001PMsg
     * @return
     */
    private static boolean executeNszc0330(NPAL1510CMsg cMsg, NSZC033001PMsg pMsg) {
        // Search Option API(NSZC0330) is executed
        NSZC033001 api = new NSZC033001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);

        String msgId;
        if (!S21ApiUtil.getXxMsgIdList(pMsg).isEmpty()) {
            for (int j = 0; j < pMsg.xxMsgIdList.length(); j++) {
                if (ZYPCommonFunc.hasValue(pMsg.xxMsgIdList.no(j).xxMsgId)) {
                    msgId = pMsg.xxMsgIdList.no(j).xxMsgId.getValue();
                    cMsg.setMessageInfo(msgId);
                    if (msgId.endsWith("E")) {
                        cMsg.srchOptPk_SL.setErrorInfo(1, msgId);
                        cMsg.srchOptNm_TX.setErrorInfo(1, msgId);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Call API NSZC033001 (Delete SearchOption)
     * @param cMsg NPAL1090CMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forDeleteSearchOption(NPAL1510CMsg cMsg, String usrId, String glblCmpyCd) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_DELETE);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_SL);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BUSINESS_APPL_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);

        if (executeNszc0330(cMsg, pMsg)) {
            cMsg.srchOptUsrId_U1.setValue(usrId);
            setSavedSearchOptionPullDown(cMsg);
            cMsg.srchOptNm_TX.clear();
            cMsg.srchOptPk_SL.clear();
            cMsg.setMessageInfo(ZZZM9003I, new String[] {"Delete Search" });
        }
    }

    /**
     * Call API NSZC033001 (Search SearchOption)
     * @param cMsg NPAL1090CMsg
     * @param usrId String
     * @param glblCmpyCd String
     */
    public static void callNszc0330forSearchSearchOption(NPAL1510CMsg cMsg, String usrId, String glblCmpyCd) {
        NSZC033001PMsg pMsg = new NSZC033001PMsg();
        pMsg.clear();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxProcMd, NSZC033001Constant.PROCESS_MODE_SEARCH);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptPk, cMsg.srchOptPk_SL);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptAplId, BUSINESS_APPL_ID);
        ZYPEZDItemValueSetter.setValue(pMsg.srchOptUsrId, usrId);

        if (!executeNszc0330(cMsg, pMsg)) {
            return; // Error
        }

        // Set Result
        ZYPEZDItemValueSetter.setValue(cMsg.srchOptNm_TX, pMsg.srchOptNm);

        ZYPEZDItemValueSetter.setValue(cMsg.poOrdNum, pMsg.srchOptTxt_01);
        ZYPEZDItemValueSetter.setValue(cMsg.poOrdSrcCd_SL, pMsg.srchOptTxt_02);
        ZYPEZDItemValueSetter.setValue(cMsg.dsPoTpCd_SL, pMsg.srchOptTxt_03);
        ZYPEZDItemValueSetter.setValue(cMsg.poHdrStsCd_SL, pMsg.srchOptTxt_04);
        ZYPEZDItemValueSetter.setValue(cMsg.poApvlStsCd_SL, pMsg.srchOptTxt_05);
        ZYPEZDItemValueSetter.setValue(cMsg.trxRefNum, pMsg.srchOptTxt_06);
        ZYPEZDItemValueSetter.setValue(cMsg.shpgSvcLvlCd_SL, pMsg.srchOptTxt_07);
        ZYPEZDItemValueSetter.setValue(cMsg.fullPsnNm, pMsg.srchOptTxt_08);
        ZYPEZDItemValueSetter.setValue(cMsg.prntVndCd, pMsg.srchOptTxt_09);
        ZYPEZDItemValueSetter.setValue(cMsg.prntVndNm, pMsg.srchOptTxt_10);
        ZYPEZDItemValueSetter.setValue(cMsg.vndCd, pMsg.srchOptTxt_11);
        ZYPEZDItemValueSetter.setValue(cMsg.vndNm, pMsg.srchOptTxt_12);
        ZYPEZDItemValueSetter.setValue(cMsg.destRtlWhCd, pMsg.srchOptTxt_13);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm, pMsg.srchOptTxt_14);
        ZYPEZDItemValueSetter.setValue(cMsg.destRtlSwhCd, pMsg.srchOptTxt_15);
        ZYPEZDItemValueSetter.setValue(cMsg.rtlSwhNm, pMsg.srchOptTxt_16);
        ZYPEZDItemValueSetter.setValue(cMsg.shipToCustCd, pMsg.srchOptTxt_17);
        ZYPEZDItemValueSetter.setValue(cMsg.shipToCustLocNm, pMsg.srchOptTxt_18);
        ZYPEZDItemValueSetter.setValue(cMsg.poLineStsCd_SL, pMsg.srchOptTxt_19);
        cMsg.poSubmtDt_FR.setValue(pMsg.srchOptTxt_20.getValue());
        cMsg.poSubmtDt_TO.setValue(pMsg.srchOptTxt_21.getValue());
        cMsg.xxDt10Dt_FR.setValue(pMsg.srchOptTxt_22.getValue());
        cMsg.xxDt10Dt_TO.setValue(pMsg.srchOptTxt_23.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.vndPoAckStsCd_SL, pMsg.srchOptTxt_24);
        ZYPEZDItemValueSetter.setValue(cMsg.poAckNum, pMsg.srchOptTxt_25);
        ZYPEZDItemValueSetter.setValue(cMsg.vndIssOrdNum, pMsg.srchOptTxt_26);
        ZYPEZDItemValueSetter.setValue(cMsg.proNum, pMsg.srchOptTxt_27);
        ZYPEZDItemValueSetter.setValue(cMsg.vndSoNum, pMsg.srchOptTxt_28);
        ZYPEZDItemValueSetter.setValue(cMsg.vndInvtyLocCd, pMsg.srchOptTxt_29);
        cMsg.xxOrdDt_FR.setValue(pMsg.srchOptTxt_30.getValue());
        cMsg.xxOrdDt_TO.setValue(pMsg.srchOptTxt_31.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.xxDplyByCtrlItemCdNm, pMsg.srchOptTxt_32);
        ZYPEZDItemValueSetter.setValue(cMsg.mdseCd_SB, pMsg.srchOptTxt_33);
        ZYPEZDItemValueSetter.setValue(cMsg.mdseCd, pMsg.srchOptTxt_34);
        ZYPEZDItemValueSetter.setValue(cMsg.aslMdseCd, pMsg.srchOptTxt_35);
        // START 2017/08/24 S.Katsuma [QC#20678,ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.prchReqNum, pMsg.srchOptTxt_36);
        // END 2017/08/24 S.Katsuma [QC#20678,ADD]
        // START 2018/01/31 K.Mishiro [QC#22521,ADD]
        ZYPEZDItemValueSetter.setValue(cMsg.coaMdseTpCd, pMsg.srchOptTxt_37);
        ZYPEZDItemValueSetter.setValue(cMsg.coaProjDescTxt, pMsg.srchOptTxt_38);
        ZYPEZDItemValueSetter.setValue(cMsg.coaProdCd, pMsg.srchOptTxt_39);
        ZYPEZDItemValueSetter.setValue(cMsg.coaProdNm, pMsg.srchOptTxt_40);
        // END 2018/01/31 K.Mishiro [QC#22521,ADD]
        // add start 2022/05/19 QC#57934
        ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_H1, pMsg.srchOptTxt_41);
        // add end 2022/05/19 QC#57934
        // START 2023/02/09 TZ.Win [QC#60966, ADD]
        cMsg.rqstShipDt_FR.setValue(pMsg.srchOptTxt_42.getValue());
        cMsg.rqstShipDt_TO.setValue(pMsg.srchOptTxt_43.getValue());
        // END 2023/02/09 TZ.Win [QC#60966, ADD]
    }

    /**
     * loadOnePageToCMsg
     * @param cMsg EZDCMsg
     * @param cMsgArray EZDCMsgArray
     * @param sMsgArray EZDSMsgArray
     */
    public static void loadOnePageToCMsg(EZDCMsg cMsg, EZDCMsgArray cMsgArray, EZDSMsgArray sMsgArray) {

        NPAL1510CMsg bizMsg = (NPAL1510CMsg) cMsg;

        ZYPTableUtil.clear(cMsgArray);

        bizMsg.xxPageShowToNum.clear();
        bizMsg.xxPageShowOfNum.clear();
        int maxDisplayRows = cMsgArray.length();
        int startIndex = (bizMsg.xxPageShowFromNum.getValueInt() / maxDisplayRows) * maxDisplayRows;

        int i = startIndex;
        for (; i < startIndex + cMsgArray.length(); i++) {
            if (i < sMsgArray.getValidCount()) {
                EZDMsg sLineMsg = sMsgArray.get(i);
                int indexOfCMsg = i - startIndex;
                EZDMsg.copy(sLineMsg, null, cMsgArray.get(indexOfCMsg), null);

            } else {
                break;
            }
        }

        cMsgArray.setValidCount(i - startIndex);
        bizMsg.xxPageShowFromNum.setValue(startIndex + 1);
        bizMsg.xxPageShowToNum.setValue(startIndex + cMsgArray.getValidCount());
        bizMsg.xxPageShowOfNum.setValue(sMsgArray.getValidCount());
    }

    // START 2017/11/16 S.Katsuma [QC#22296,ADD]
    public static void initilizeMsgForInitAndReset(NPAL1510CMsg bizMsg, NPAL1510SMsg glblMsg) {
        String xxComnColOrdTxt = bizMsg.xxComnColOrdTxt.getValue();
        bizMsg.clear();
        bizMsg.xxComnColOrdTxt.setValue(xxComnColOrdTxt);
        glblMsg.clear();
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(glblMsg.A);

        // Set Pulldown Lists
        NPAL1510CommonLogic.setSavedSearchOptionPullDown(bizMsg);
        NPAL1510CommonLogic.setApprovalStatusList(bizMsg);
        NPAL1510CommonLogic.setPoHederStatusList(bizMsg);
        NPAL1510CommonLogic.setPoLineStatusList(bizMsg);
        NPAL1510CommonLogic.setPoSourceList(bizMsg);
        NPAL1510CommonLogic.setPoTypeList(bizMsg);
        NPAL1510CommonLogic.setRequestedShipMethodList(bizMsg);
        NPAL1510CommonLogic.setVendorPoAckStatusList(bizMsg);

        // add start 2022/05/19 QC#57934
        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_H1, ZYPConstant.CHKBOX_ON_Y);
        // add end 2022/05/19 QC#57934
    }
    // END 2017/11/16 S.Katsuma [QC#22296,ADD]

    /**
     * Pre Search For Set Name
     * @param cMsg NPAL1510CMsg
     * @param glblCmpyCd String
     */
    public static void preSearchForSetName(NPAL1510CMsg cMsg, String glblCmpyCd) {
        // MERCHANDISE TYPE
        if (ZYPCommonFunc.hasValue(cMsg.coaMdseTpCd)) {
            COA_PROJTMsg coaProjMsg = new COA_PROJTMsg();
            ZYPEZDItemValueSetter.setValue(coaProjMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(coaProjMsg.coaProjCd, cMsg.coaMdseTpCd);
            coaProjMsg = (COA_PROJTMsg) EZDTBLAccessor.findByKey(coaProjMsg);
            if (coaProjMsg != null) {
                ZYPEZDItemValueSetter.setValue(cMsg.coaProjDescTxt, coaProjMsg.coaProjDescTxt);
            } else {
                cMsg.coaProjDescTxt.clear();
            }
        } else {
            cMsg.coaProjDescTxt.clear();
        }
        // PRODUCT NAME
        if (ZYPCommonFunc.hasValue(cMsg.coaProdCd)) {
            COA_PRODTMsg coaProdMsg = new COA_PRODTMsg();
            ZYPEZDItemValueSetter.setValue(coaProdMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(coaProdMsg.coaProdCd, cMsg.coaProdCd);
            coaProdMsg = (COA_PRODTMsg) EZDTBLAccessor.findByKey(coaProdMsg);
            if (coaProdMsg != null) {
                ZYPEZDItemValueSetter.setValue(cMsg.coaProdNm, coaProdMsg.coaProdNm);
            } else {
                cMsg.coaProdNm.clear();
            }
        } else {
            cMsg.coaProdNm.clear();
        }
    }
}
