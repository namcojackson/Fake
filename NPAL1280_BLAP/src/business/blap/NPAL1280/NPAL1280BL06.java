/**
 * Copyright (c) 2016 Canon USA Inc. All rights reserved.
 */
package business.blap.NPAL1280;

import static business.blap.NPAL1280.constant.NPAL1280Constant.BUSINESS_APPL_ID;
import static business.blap.NPAL1280.constant.NPAL1280Constant.CARRIER_D;
import static business.blap.NPAL1280.constant.NPAL1280Constant.CHK_TIME_PATTERN;
import static business.blap.NPAL1280.constant.NPAL1280Constant.CMN_SAVE;
import static business.blap.NPAL1280.constant.NPAL1280Constant.CMN_SUBMIT;
import static business.blap.NPAL1280.constant.NPAL1280Constant.COLON;
import static business.blap.NPAL1280.constant.NPAL1280Constant.IDX_5;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NMAM0038I;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAL1280_CMN_COL_CLEAR;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAL1280_CMN_COL_SAVE;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAL1280_HEADER_CANCEL;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAL1280_LINE_CANCEL;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAM0006E;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAM0008E;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAM0049E;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAM1359I;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAM1360E;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAM1361E;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAM1365E;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAM1515E;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAM1588E;
import static business.blap.NPAL1280.constant.NPAL1280Constant.NPAM1655W;
import static business.blap.NPAL1280.constant.NPAL1280Constant.TIME_FORMAT;
import static business.blap.NPAL1280.constant.NPAL1280Constant.ZZM8100I;
import static business.blap.NPAL1280.constant.NPAL1280Constant.ZZM9000E;
import static business.blap.NPAL1280.constant.NPAL1280Constant.ZZZM9003I;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDSMsg;
import business.blap.NPAL1280.common.NPAL1280CommonLogic;
import business.blap.NPAL1500.common.NPAL1500CommonLogic;
import business.db.PO_MSGTMsg;
import business.db.PRCH_REQTMsg;
import business.db.PRCH_REQ_DTLTMsg;
import business.parts.NPZC103001PMsg;

import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWH;
import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWHData;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_MDSE_CMPSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_APVL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_SVC_LVL;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;


/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/01/2016   CITS         K.Ogino            Create          N/A
 * 03/08/2016   CITS         K.Ogino            Update          QC#5156
 * 03/17/2016   CITS         K.Ogino            Update          QC#5659
 * 03/22/2016   CITS         K.Ogino            Update          QC#5717
 * 04/04/2016   CITS         K.Ogino            Update          QC#5964
 * 05/26/2016   CITS         K.Ogino            Update          QC#8936
 * 11/02/2016   CITS         K.Ogino            Update          QC#14890
 * 01/11/2017   CITS         T.Hakodate         Update          QC#17075
 * 03/01/2017   CITS         Y.IWASAKI          Update          QC#17487
 * 08/03/2017   CITS         R.Shimamoto        Update          QC#18761
 * 04/04/2018   CITS         T.Wada             Update          QC#21170
 * 07/03/2018   CITS         T.Tokutomi         Update          QC#23726
 * 08/16/2018   CITS         K.Ogino            Update          QC#27819
 * 09/14/2018   CITS         K.Ogino            Update          QC#28216/QC#28143
 * 10/18/2018   CITS         T.Wada             Update          QC#28653
 * 10/31/2018   CITS         K.Kameoka          Update          QC#28941
 * 10/13/2018   CITS         T.Tokutomi         Update          QC#28939
 * 10/15/2018   CITS         T.Tokutomi         Update          QC#29155
 * 12/17/2018   Fujitsu      S.Takami           Update          QC#29456
 * 12/19/2018   CITS         K.Ogino            Update          QC#29645
 * 12/21/2018   CITS         K.Ogino            Update          QC#29729
 * 01/24/2019   CITS         K.Ogino            Update          QC#29997
 * 02/22/2019   CITS         K.Ogino            Update          QC#30460
 * 2019/04/17   CITS         K.Ogino            Update          QC#31087
 * 2019/07/17   CITS         T.Wada             Update          QC#51578
 * 2019/09/24   CITS         R.Shimamoto        Update          QC#53422
 * 2019/12/03   Fujitsu      T.Ogura            Update          QC#54814
 * 2021/04/23   CITS         M.Furugoori        Update          QC#58645
 * 2022/10/31   Hitachi      N.Takatsu          Update          QC#60604
 * 2023/02/06   Hitachi      T.Kuroda           Update          QC#60966
 * 2024/01/15   CITS         K.Iwamoto          Update          QC#62443
 *</pre>
 */
public class NPAL1280BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            NPAL1280CMsg bizMsg = (NPAL1280CMsg) cMsg;
            NPAL1280SMsg glblMsg = (NPAL1280SMsg) sMsg;
            String glblCmpyCd = getGlobalCompanyCode();
            String loginUserId = getUserProfileService().getContextUserInfo().getUserId();
            String salesDate = ZYPDateUtil.getSalesDate(glblCmpyCd);

            if (CMN_SAVE.equals(screenAplID)) {
                doProcess_NPAL1280_CMN_SAVE(bizMsg, glblMsg, glblCmpyCd, salesDate, loginUserId);
            } else if (CMN_SUBMIT.equals(screenAplID)) {
                doProcess_NPAL1280_CMN_SUBMIT(bizMsg, glblMsg, glblCmpyCd, salesDate, loginUserId);
            } else if (NPAL1280_CMN_COL_CLEAR.equals(screenAplID)) {
                ZYPGUITableColumn.clearColData(bizMsg, glblMsg);
            } else if (NPAL1280_CMN_COL_SAVE.equals(screenAplID)) {
                ZYPGUITableColumn.setColData(bizMsg, glblMsg);
            } else if (NPAL1280_HEADER_CANCEL.equals(screenAplID)) {
                doProcess_NPAL1280_HEADER_CANCEL(bizMsg, glblMsg, glblCmpyCd, salesDate, loginUserId);
            } else if (NPAL1280_LINE_CANCEL.equals(screenAplID)) {
                doProcess_NPAL1280_LINE_CANCEL(bizMsg, glblMsg, glblCmpyCd, salesDate, loginUserId);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Save button event
     * @param cMsg NPAL1280CMsg
     * @param sMsg NPAL1280SMsg
     * @param glblCmpyCd String
     * @param salesDate String
     * @param loginUserId String
     */
    private void doProcess_NPAL1280_CMN_SAVE(NPAL1280CMsg cMsg, NPAL1280SMsg sMsg, String glblCmpyCd, String salesDate, String loginUserId) {
        boolean errorFlg = false;

        // QC#29155 Add.
        boolean isModifyShpgSvcLvlReln = NPAL1280CommonLogic.isModifyShpgSvcLvlReln(cMsg, sMsg);

        // QC#51578 Add Start
        if (cMsg.destRtlWhCd != null) {
            if(NPAL1280CommonLogic.isGmdWh(glblCmpyCd, cMsg.destRtlWhCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(cMsg.prchReqTpCd_SL, PRCH_REQ_TP.ROSS);
            }
        }
        // QC#51578 Add End

        cMsg.xxTotAmt.clear();
        sMsg.A.setValidCount(cMsg.xxPageShowOfNum.getValueInt());
        NPAL1280CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);

        int maxDisplayRows = cMsg.A.length();

        // QC#21170 Start
        Boolean flgErr = NPAL1280CommonLogic.chkRqstDt(cMsg, sMsg, glblCmpyCd);
        if (flgErr) {
            NPAL1280CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
            return;
        }
        // QC#21170 End
        
        // Call to NMXC100001
        String invtyLocCd = ZYPCommonFunc.concatString(cMsg.destRtlWhCd.getValue(), cMsg.destRtlSwhCd.getValue(), null);
        NMXC100001EnableWHData enableWhData = NMXC100001EnableWH.checkEnableWH(glblCmpyCd, invtyLocCd, BUSINESS_APPL_ID, null, ZYPConstant.FLG_ON_Y, cMsg.prchReqTpCd_SL.getValue());
        if (!NPAL1280CommonLogic.isCustDropShipPoQulf(glblCmpyCd, cMsg.poQlfyCd.getValue())) {
            if (ZYPCommonFunc.hasValue(enableWhData.getXxMsgId())) {

                // QC#28653 Mod Start
                if (!ZYPCommonFunc.hasValue(cMsg.destRtlSwhCd.getValue())) {
                    // cMsg.destRtlSwhCd.setErrorInfo(1, enableWhData.getXxMsgId());
                    cMsg.destRtlSwhCd.setErrorInfo(1, ZZM9000E, new String[] {"Destination SWH"});
                    return;
                }

                cMsg.destRtlWhCd.setErrorInfo(1, enableWhData.getXxMsgId());
                cMsg.destRtlSwhCd.setErrorInfo(1, enableWhData.getXxMsgId());
                //cMsg.prchReqTpCd_SL.setErrorInfo(1, enableWhData.getXxMsgId());
                // QC#28653 Mod End
                return;
            }
        }

        if (sMsg.A.getValidCount() == 0) {
            cMsg.setMessageInfo(NPAM1360E);
            NPAL1280CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
            return;
        }


        // Validation to NPZC1030 PR Update API
        String parentSupplier = "";
        String parentSupplierSite = "";
        BigDecimal parentVndPk = BigDecimal.ZERO;
        String parentRqstRcvDt = "";
        // START 2023/02/06 T.Kuroda [QC#60966, ADD]
        String parentRqstShipDt = "";
        // END   2023/02/06 T.Kuroda [QC#60966, ADD]
        String poSchdRelDt = "";
        // QC#29645
        String relRqstToPoOrdNum = "";
        // QC#28143
        boolean isErr = false;
        int errScrnInex = -1;

        // QC#28939 Add.
        Deque<String> textItemQue = NPAL1280CommonLogic.setupTextItemQueue(getGlobalCompanyCode());
        // START 2024/1/1 K.Iwamoto [QC#62443, ADD]]
        if (NPAL1280CommonLogic.checkAllLineType(cMsg, sMsg, glblCmpyCd)) {
        	NPAL1280CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
            isErr = true;
        }
        // END 2024/1/1 K.Iwamoto [QC#62443, ADD]]

        for (int index = 0; index < sMsg.A.getValidCount(); index++) {
            if (!PRCH_REQ_LINE_STS.CANCELLED.equals(sMsg.A.no(index).prchReqLineStsCd_HD.getValue())) {

                // QC#28939 Add.
                errorFlg = NPAL1280CommonLogic.chkAndSetTextItem(sMsg, getGlobalCompanyCode(), index, textItemQue);
                if (!errorFlg) {
                    if (errScrnInex == -1) {
                        errScrnInex = (index / maxDisplayRows) * maxDisplayRows + 1;
                    }
                    isErr = true;
                }

                errorFlg = NPAL1280CommonLogic.chkDetailValues(cMsg, sMsg, glblCmpyCd, index);
                if (!errorFlg) {
                    // QC#28143
                    if (errScrnInex == -1) { 
                        errScrnInex = (index / maxDisplayRows) * maxDisplayRows + 1;
                    }
                    isErr = true;
                }

                errorFlg = NPAL1280CommonLogic.chkItemNumber(sMsg, glblCmpyCd, index);
                if (!errorFlg) {
                    // QC#28143
                    if (errScrnInex == -1) { 
                        errScrnInex = (index / maxDisplayRows) * maxDisplayRows + 1;
                    }
                    isErr = true;
                }

                if (PO_MDSE_CMPSN_TP.CHILD.equals(sMsg.A.no(index).poMdseCmpsnTpCd_A1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prntVndCd_A1, parentSupplier);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).vndCd_A1, parentSupplierSite);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).vndPk_A1, parentVndPk);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rqstRcvDt_A1, parentRqstRcvDt);
                    // START 2023/02/06 T.Kuroda [QC#60966, ADD]
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rqstShipDt_A1, parentRqstShipDt);
                    // END   2023/02/06 T.Kuroda [QC#60966, ADD]
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).poSchdRelDt_A1, poSchdRelDt);
                    // QC#29645
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).relRqstToPoOrdNum_A1, relRqstToPoOrdNum);
                    
                } else {
                    // Parent or Regular
                    errorFlg = NPAL1280CommonLogic.fillDetailItems(cMsg, sMsg, glblCmpyCd, index);
                    if (!errorFlg) {
                        // QC#28143
                        if (errScrnInex == -1) {
                            errScrnInex = (index / maxDisplayRows) * maxDisplayRows + 1;
                        }
                        isErr = true;
                    }
                    errorFlg = NPAL1280CommonLogic.chkSupplier(sMsg, glblCmpyCd, index);
                    if (!errorFlg) {
                        // QC#28143
                        if (errScrnInex == -1) {
                            errScrnInex = (index / maxDisplayRows) * maxDisplayRows + 1;
                        }
                        isErr = true;
                    }
                    // QC#31087
                    errorFlg = NPAL1280CommonLogic.chkSupplierSite(sMsg, glblCmpyCd, index, true);
                    if (!errorFlg) {
                        // QC#28143
                        if (errScrnInex == -1) {
                            errScrnInex = (index / maxDisplayRows) * maxDisplayRows + 1;
                        }
                        isErr = true;
                    }
                }

                // QC#29155 Update.
                if (ZYPCommonFunc.hasValue(cMsg.carrCd_HF)) {
                    String carrNm = NPAL1280CommonLogic.getCarrierName(glblCmpyCd, cMsg.carrCd_HF.getValue());
                    
                    if(carrNm == null){
                        errorFlg = false;
                        sMsg.locNm_HF.clear();
                        cMsg.locNm_HF.clear();
                        cMsg.carrCd_HF.setErrorInfo(1, NPAM1361E, new String[] {CARRIER_D });
                    } else {
                        ZYPEZDItemValueSetter.setValue(sMsg.locNm_HF, carrNm);
                    }

                    if (!errorFlg) {
                        // QC#28143
                        if (errScrnInex == -1) {
                            errScrnInex = (index / maxDisplayRows) * maxDisplayRows + 1;
                        }
                        isErr = true;
                    }
                }
                if (!(PO_MDSE_CMPSN_TP.CHILD.equals(sMsg.A.no(index).poMdseCmpsnTpCd_A1.getValue()))) {
                    errorFlg = NPAL1280CommonLogic.chkPrchAvalFlg(cMsg, sMsg, glblCmpyCd, salesDate, index);
                    if (!errorFlg) {
                        // QC#28143
                        if (errScrnInex == -1) {
                            errScrnInex = (index / maxDisplayRows) * maxDisplayRows + 1;
                        }
                        isErr = true;
                    }

                    if (PO_MDSE_CMPSN_TP.PARENT.equals(sMsg.A.no(index).poMdseCmpsnTpCd_A1.getValue())) {
                        parentSupplier = sMsg.A.no(index).prntVndCd_A1.getValue();
                        parentSupplierSite = sMsg.A.no(index).vndCd_A1.getValue();
                        parentVndPk = sMsg.A.no(index).vndPk_A1.getValue();
                        parentRqstRcvDt = sMsg.A.no(index).rqstRcvDt_A1.getValue();
                        // START 2023/02/06 T.Kuroda [QC#60966, ADD]
                        parentRqstShipDt = sMsg.A.no(index).rqstShipDt_A1.getValue();
                        // END   2023/02/06 T.Kuroda [QC#60966, ADD]
                        poSchdRelDt = sMsg.A.no(index).poSchdRelDt_A1.getValue();
                        // QC#29645
                        relRqstToPoOrdNum = sMsg.A.no(index).relRqstToPoOrdNum_A1.getValue();
                    }
                }
                errorFlg = NPAL1280CommonLogic.chkPoVnd(sMsg, glblCmpyCd, index);
                if (!errorFlg) {
                    // QC#28143
                    if (errScrnInex == -1) {
                        errScrnInex = (index / maxDisplayRows) * maxDisplayRows + 1;
                    }
                    isErr = true;
                }
                errorFlg = NPAL1280CommonLogic.chkAslDtl(sMsg, glblCmpyCd, salesDate, index);
                if (!errorFlg) {
                    // QC#28143
                    if (errScrnInex == -1) {
                        errScrnInex = (index / maxDisplayRows) * maxDisplayRows + 1;
                    }
                    isErr = true;
                }
                errorFlg = NPAL1280CommonLogic.convNPZC1290Call(sMsg, glblCmpyCd, salesDate, index, cMsg);
                if (!errorFlg) {
                    // QC#28143
                    if (errScrnInex == -1) {
                        errScrnInex = (index / maxDisplayRows) * maxDisplayRows + 1;
                    }
                    isErr = true;
                }
                if (ZYPCommonFunc.hasValue(sMsg.A.no(index).prchReqLineStsCd_HD) && !(PRCH_REQ_LINE_STS.CANCELLED.equals(sMsg.A.no(index).prchReqLineStsCd_HD.getValue()))) {
                    NPAL1280CommonLogic.convUnitPrice(cMsg, sMsg, glblCmpyCd, salesDate, index);
                } else if (!(ZYPCommonFunc.hasValue(sMsg.A.no(index).prchReqLineStsCd_HD))
                        && (PO_MDSE_CMPSN_TP.CHILD.equals(sMsg.A.no(index).poMdseCmpsnTpCd_A1.getValue()) || PO_MDSE_CMPSN_TP.REGULAR.equals(sMsg.A.no(index).poMdseCmpsnTpCd_A1.getValue()) || PO_MDSE_CMPSN_TP.PARENT
                                .equals(sMsg.A.no(index).poMdseCmpsnTpCd_A1.getValue()))) {
                    NPAL1280CommonLogic.convUnitPrice(cMsg, sMsg, glblCmpyCd, salesDate, index);
                }

                // START 08/03/2017 QC#18761 Add.
                if (!NPAL1280CommonLogic.chkMdseDuplicateFromAsl(cMsg, sMsg, glblCmpyCd, salesDate, index)) {
                    if (errScrnInex == -1) { 
                        errScrnInex = (index / maxDisplayRows) * maxDisplayRows + 1;
                    }
                    isErr = true;
                }
                // END 08/03/2017 QC#18761 Add.
                // START 12/17/2018 QC#29456 Add.
                // QC#29729 Mod
                // 2019/09/24 QC#53422 Add Start
                if (!isErr) {
                	if (!sMsg.A.no(index).xxScrItem130Txt_A1.isError() && !NPAL1280CommonLogic.checkChargeAccount(sMsg.A.no(index), glblCmpyCd)) {
                        if (errScrnInex == -1) { 
                            errScrnInex = (index / maxDisplayRows) * maxDisplayRows + 1;
                        }
                        isErr = true;
                    }
                }
                // 2019/09/24 QC#53422 Add End
                // END  12/17/2018 QC#29456 Add.
            }
        }
        // QC#28143
        if (isErr) {
            cMsg.xxPageShowFromNum.setValue(errScrnInex);
            NPAL1280CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
            return;
        }

        // QC#17487
        // Set Service Level to Ground if target WH is included in specified WH owners.
        if (NPAL1280CommonLogic.isSvcLvlWhOwnr(glblCmpyCd, cMsg)) {
            ZYPEZDItemValueSetter.setValue(cMsg.shpgSvcLvlCd_SL, SHPG_SVC_LVL.GROUND_SERVICE);
            ZYPEZDItemValueSetter.setValue(sMsg.shpgSvcLvlCd_SL, SHPG_SVC_LVL.GROUND_SERVICE);
        }

        // Addl Header Validation
        // QC#28941 Update Start
        // QC#29155 Update.
        if ((ZYPCommonFunc.hasValue(cMsg.carrCd_HF))) {
            String carrNm = NPAL1280CommonLogic.getCarrierName(glblCmpyCd, cMsg.carrCd_HF.getValue());

            ZYPEZDItemValueSetter.setValue(cMsg.locNm_HF, carrNm);

        } else {
            cMsg.locNm_HF.clear();
        }

        // QC#28941 Update End
        // QC$29155 Update Error => Warning.
        if (isModifyShpgSvcLvlReln && !NPAL1280CommonLogic.chkShpSvcLvlCarrRelation(cMsg, glblCmpyCd)) {
            return;
        }

        errorFlg = NPAL1280CommonLogic.chkCarrierAccountRelation(cMsg, glblCmpyCd);
        if (!errorFlg) {
            return;
        }
        
        // QC#23726 07/03/2018 Check Customer Carrier Service
        // Relation.
        if (NPAL1280CommonLogic.checkCustCarrSvcLvlRelation(glblCmpyCd, cMsg)) {
            // Error.
            cMsg.shpgSvcLvlCd_SL.setErrorInfo(1, NPAM1365E);
            // QC#29155 Update.
            cMsg.carrCd_HF.setErrorInfo(1, NPAM1365E);
            return;
        }
        // QC#27819
        if (ZYPCommonFunc.hasValue(cMsg.shipToCustCd_CP) && !cMsg.shipToCustCd_CP.getValue().equals(cMsg.shipToCustCd.getValue())) {
            NPAL1280CommonLogic.setShipToAddressFromShipToCustomer(cMsg, sMsg);
            // QC#29997
            String sellToCustCd = NPAL1280CommonLogic.getBillToSellToByShipTo(cMsg.destRtlWhCd.getValue(), glblCmpyCd);
            if (ZYPCommonFunc.hasValue(sellToCustCd)) {
                NPAL1280CommonLogic.getBillToLocation(cMsg, glblCmpyCd, null, sellToCustCd);
            }
        } else if (!ZYPCommonFunc.hasValue(cMsg.shipToCustCd_CP)) {
            if(!NPAL1280CommonLogic.setManualDropWh(cMsg, sMsg, glblCmpyCd)){
                // Error.
                return;
            }
            // QC#29997
            String sellToCustCd = NPAL1280CommonLogic.getBillToSellToByShipTo(cMsg.destRtlWhCd.getValue(), glblCmpyCd);
            if (ZYPCommonFunc.hasValue(sellToCustCd)) {
                NPAL1280CommonLogic.getBillToLocation(cMsg, glblCmpyCd, null, sellToCustCd);
            }
        // START 2019/12/03 T.Ogura [QC#54814,MOD]
//        }
        } else {
            if (!NPAL1280CommonLogic.checkShipToCustomer(cMsg, sMsg)) {
                // Error.
                return;
            }
        }
        // END   2019/12/03 T.Ogura [QC#54814,MOD]

        // lock and exclusive check for PRCH_REQ
        PRCH_REQTMsg prTMsg = NPAL1280CommonLogic.findPrchReqByKeyForUpdate(sMsg, glblCmpyCd, cMsg);
        if (NPAM0006E.equals(cMsg.getMessageCode())) {
            NPAL1280CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
            return;
        }

        List<NPAL1280_ASMsg> prdTMsgNewList = new ArrayList<NPAL1280_ASMsg>();
        List<PRCH_REQ_DTLTMsg> prdTMsgUpdateList = new ArrayList<PRCH_REQ_DTLTMsg>();

        for (int index = 0; index < sMsg.A.getValidCount(); index++) {

            if (!PRCH_REQ_LINE_STS.CANCELLED.equals(sMsg.A.no(index).prchReqLineStsCd_HD.getValue())) {

                // lock and exclusive check for PRCH_REQ_DTL
                PRCH_REQ_DTLTMsg prdTMsg = NPAL1280CommonLogic.findPrchReqDtlByKeyForUpdate(sMsg, glblCmpyCd, index, cMsg);

                if (prdTMsg == null) {

                    // add new line
                    prdTMsgNewList.add(sMsg.A.no(index));

                } else {

                    // update line List
                    prdTMsgUpdateList.add(prdTMsg);
                }
            }

        }

        // add new line
        if (prdTMsgNewList.size() > 0) {
            NPZC103001PMsg params = NPAL1280CommonLogic.savePrUpdateNew(cMsg, prdTMsgNewList, glblCmpyCd, salesDate, loginUserId, PRCH_REQ_APVL_STS.ENTERED, prTMsg);
            NPAL1280CommonLogic.callNPZC1030(params, cMsg);
            ZYPEZDItemValueSetter.setValue(cMsg.prchReqNum, params.prchReqNum);
        }

        // update line
        if (prdTMsgUpdateList.size() > 0) {
            NPZC103001PMsg params = NPAL1280CommonLogic.savePrUpdateUpdate(cMsg, sMsg, glblCmpyCd, salesDate, prTMsg, prdTMsgUpdateList, PRCH_REQ_APVL_STS.ENTERED, loginUserId);
            NPAL1280CommonLogic.callNPZC1030(params, cMsg);
            ZYPEZDItemValueSetter.setValue(cMsg.prchReqNum, params.prchReqNum);
        }

        // create MSG
        int internalPoMsgSegId = 0;
        if (sMsg.R.getValidCount() > 0) {

            for (int i = 0; i < sMsg.R.getValidCount(); i++) {

                NPAL1280_RSMsg msgInfo = sMsg.R.no(i);

                if (hasValue(msgInfo.poMsgPk)) {

                    // update
                    PO_MSGTMsg poMsg = NPAL1280CommonLogic.getPoMsg(glblCmpyCd, msgInfo.poMsgPk.getValue());

                    if (poMsg != null && hasValue(poMsg.poMsgPk)) {

                        NPAL1280CommonLogic.updatePoMsg(cMsg, poMsg, msgInfo.poMsgTxt.getValue());

                    }

                } else {

                    BigDecimal poMsgPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PO_MSG_SQ);
                    ZYPEZDItemValueSetter.setValue(msgInfo.poMsgPk, poMsgPk);
                    NPAL1280CommonLogic.insertPoMsg(cMsg, glblCmpyCd, msgInfo, internalPoMsgSegId, msgInfo.poMsgTxt.getValue(), sMsg);
                    internalPoMsgSegId++;
                }
            }
        }

        NPAL1280CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);

        cMsg.setMessageInfo(ZZZM9003I, new String[] {"Save" });
    }
    private static boolean isTime(EZDCStringItem... timeItems) {
        for (EZDCStringItem timeItem : timeItems) {
            if (ZYPCommonFunc.hasValue(timeItem)) {
                // IDX_5:5 hh24mi input length check
                if (timeItem.getValue().length() < IDX_5) {
                    // timeItem.setErrorInfo(1, NWAM0664E, new
                    // String[] {TIME_FORMAT });
                    timeItem.setErrorInfo(1, NPAM1515E, new String[] {TIME_FORMAT });
                    return false;
                }

                if (!timeItem.getValue().matches(CHK_TIME_PATTERN)) {
                    timeItem.setErrorInfo(1, NPAM1515E);
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * Get Time
     * @param tm time
     * @return time
     */
    public static String getTm(String tm) {
        if (ZYPCommonFunc.hasValue(tm)) {
            return tm.replace(COLON, "");
        }
        return tm;
    }
    /**
     * Submit button event
     * @param cMsg NPAL1280CMsg
     * @param sMsg NPAL1280SMsg
     * @param glblCmpyCd String
     * @param salesDate String
     * @param loginUserId String
     */
    private void doProcess_NPAL1280_CMN_SUBMIT(NPAL1280CMsg cMsg, NPAL1280SMsg sMsg, String glblCmpyCd, String salesDate, String loginUserId) {
        boolean errorFlg = false;
        
        // QC#29155 Add.
        boolean isModifyShpgSvcLvlReln = NPAL1280CommonLogic.isModifyShpgSvcLvlReln(cMsg, sMsg);

        // QC#51578 Add Start
        if (cMsg.destRtlWhCd != null) {
            if(NPAL1280CommonLogic.isGmdWh(glblCmpyCd, cMsg.destRtlWhCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(cMsg.prchReqTpCd_SL, PRCH_REQ_TP.ROSS);
            }
        }
        // QC#51578 Add End

        sMsg.A.setValidCount(cMsg.xxPageShowOfNum.getValueInt());
        NPAL1280CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);

        // QC#21170 Start
        Boolean flgErr = NPAL1280CommonLogic.chkRqstDt(cMsg, sMsg, glblCmpyCd);
        if (flgErr) {
            NPAL1280CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
            return;
        }
        // QC#21170 End
        int maxDisplayRows = cMsg.A.length();

        // Call to NMXC100001
        String invtyLocCd = ZYPCommonFunc.concatString(cMsg.destRtlWhCd.getValue(), cMsg.destRtlSwhCd.getValue(), null);
        NMXC100001EnableWHData enableWhData = NMXC100001EnableWH.checkEnableWH(glblCmpyCd, invtyLocCd, BUSINESS_APPL_ID, null, ZYPConstant.FLG_ON_Y, cMsg.prchReqTpCd_SL.getValue());
        if (!NPAL1280CommonLogic.isCustDropShipPoQulf(glblCmpyCd, cMsg.poQlfyCd.getValue())) {
            if (ZYPCommonFunc.hasValue(enableWhData.getXxMsgId())) {
                cMsg.destRtlWhCd.setErrorInfo(1, enableWhData.getXxMsgId());
                cMsg.destRtlSwhCd.setErrorInfo(1, enableWhData.getXxMsgId());
                cMsg.prchReqTpCd_SL.setErrorInfo(1, enableWhData.getXxMsgId());
                return;
            }
        }

        if (sMsg.A.getValidCount() == 0) {
            cMsg.setMessageInfo(NPAM1360E);
            NPAL1280CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
            return;
        }

        // Validation to NPZC1030 PR Update API
        // QC#29155 Update.
        if (ZYPCommonFunc.hasValue(cMsg.carrCd_HF)) {
            errorFlg = NPAL1280CommonLogic.chkCarrier(cMsg, glblCmpyCd);
            if (!errorFlg) {
                sMsg.locNm_HF.clear();
                cMsg.locNm_HF.clear();
                NPAL1280CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
                return;
            }
        } else {
            cMsg.locNm_HF.clear();
        }

        // QC#29155 Update. Delete getCarrierCd.
        // QC$29155 Update Error => Warning.
        if (isModifyShpgSvcLvlReln && !NPAL1280CommonLogic.chkShpSvcLvlCarrRelation(cMsg, glblCmpyCd)) {
            return;
        }

        errorFlg = NPAL1280CommonLogic.chkCarrierAccountRelation(cMsg, glblCmpyCd);
        if (!errorFlg) {
            NPAL1280CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
            return;
        }

        String parentSupplier = "";
        String parentSupplierSite = "";
        BigDecimal parentVndPk = BigDecimal.ZERO;
        String parentRqstRcvDt = "";
        // START 2023/02/06 T.Kuroda [QC#60966, ADD]
        String parentRqstShipDt = "";
        // END   2023/02/06 T.Kuroda [QC#60966, ADD]
        String poSchdRelDt = "";
        // QC#29645
        String relRqstToPoOrdNum = "";
        // QC#28143
        boolean isErr = false;
        int errScrnInex = -1;
        // START 2024/1/1 K.Iwamoto [QC#62443, ADD]]
        if (NPAL1280CommonLogic.checkAllLineType(cMsg, sMsg, glblCmpyCd)) {
        	NPAL1280CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
            isErr = true;
        }
        // END 2024/1/1 K.Iwamoto [QC#62443, ADD]]

        // QC#28939 Add.
        Deque<String> textItemQue = NPAL1280CommonLogic.setupTextItemQueue(getGlobalCompanyCode());

        for (int index = 0; index < sMsg.A.getValidCount(); index++) {
            if (!PRCH_REQ_LINE_STS.CANCELLED.equals(sMsg.A.no(index).prchReqLineStsCd_HD.getValue())) {

                // QC#28939 Add.
                errorFlg = NPAL1280CommonLogic.chkAndSetTextItem(sMsg, getGlobalCompanyCode(), index, textItemQue);
                if (!errorFlg) {
                    if (errScrnInex == -1) {
                        errScrnInex = (index / maxDisplayRows) * maxDisplayRows + 1;
                    }
                    isErr = true;
                }

                errorFlg = NPAL1280CommonLogic.chkDetailValues(cMsg, sMsg, glblCmpyCd, index);
                if (!errorFlg) {
                    // QC#28143
                    if (errScrnInex == -1) { 
                        errScrnInex = (index / maxDisplayRows) * maxDisplayRows + 1;
                    }
                    isErr = true;
                }

                if (PO_MDSE_CMPSN_TP.CHILD.equals(sMsg.A.no(index).poMdseCmpsnTpCd_A1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).prntVndCd_A1, parentSupplier);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).vndCd_A1, parentSupplierSite);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).vndPk_A1, parentVndPk);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rqstRcvDt_A1, parentRqstRcvDt);
                    // START 2023/02/06 T.Kuroda [QC#60966, ADD]
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).rqstShipDt_A1, parentRqstShipDt);
                    // END   2023/02/06 T.Kuroda [QC#60966, ADD]
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).poSchdRelDt_A1, poSchdRelDt);
                    // QC#29645
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).relRqstToPoOrdNum_A1, relRqstToPoOrdNum);
                }

                errorFlg = NPAL1280CommonLogic.fillDetailItems(cMsg, sMsg, glblCmpyCd, index);
                if (!errorFlg) {
                    // QC#28143
                    if (errScrnInex == -1) { 
                        errScrnInex = (index / maxDisplayRows) * maxDisplayRows + 1;
                    }
                    isErr = true;
                }
                errorFlg = NPAL1280CommonLogic.chkItemNumber(sMsg, glblCmpyCd, index);
                if (!errorFlg) {
                    // QC#28143
                    if (errScrnInex == -1) { 
                        errScrnInex = (index / maxDisplayRows) * maxDisplayRows + 1;
                    }
                    isErr = true;
                }
                errorFlg = NPAL1280CommonLogic.chkSupplier(sMsg, glblCmpyCd, index);
                if (!errorFlg) {
                    // QC#28143
                    if (errScrnInex == -1) { 
                        errScrnInex = (index / maxDisplayRows) * maxDisplayRows + 1;
                    }
                    isErr = true;
                }
                // QC#31087
                errorFlg = NPAL1280CommonLogic.chkSupplierSite(sMsg, glblCmpyCd, index, true);
                if (!errorFlg) {
                    // QC#28143
                    if (errScrnInex == -1) { 
                        errScrnInex = (index / maxDisplayRows) * maxDisplayRows + 1;
                    }
                    isErr = true;
                }
                errorFlg = NPAL1280CommonLogic.chkPoVnd(sMsg, glblCmpyCd, index);
                if (!errorFlg) {
                    // QC#28143
                    if (errScrnInex == -1) { 
                        errScrnInex = (index / maxDisplayRows) * maxDisplayRows + 1;
                    }
                    isErr = true;
                }
                if (!(PO_MDSE_CMPSN_TP.CHILD.equals(sMsg.A.no(index).poMdseCmpsnTpCd_A1.getValue()))) {
                    errorFlg = NPAL1280CommonLogic.chkPrchAvalFlg(cMsg, sMsg, glblCmpyCd, salesDate, index);
                    if (!errorFlg) {
                        // QC#28143
                        if (errScrnInex == -1) { 
                            errScrnInex = (index / maxDisplayRows) * maxDisplayRows + 1;
                        }
                        isErr = true;
                    }
                    if (PO_MDSE_CMPSN_TP.PARENT.equals(sMsg.A.no(index).poMdseCmpsnTpCd_A1.getValue())) {
                        parentSupplier = sMsg.A.no(index).prntVndCd_A1.getValue();
                        parentSupplierSite = sMsg.A.no(index).vndCd_A1.getValue();
                        parentVndPk = sMsg.A.no(index).vndPk_A1.getValue();
                        parentRqstRcvDt = sMsg.A.no(index).rqstRcvDt_A1.getValue();
                        // START 2023/02/06 T.Kuroda [QC#60966, ADD]
                        parentRqstShipDt = sMsg.A.no(index).rqstShipDt_A1.getValue();
                        // END   2023/02/06 T.Kuroda [QC#60966, ADD]
                        poSchdRelDt = sMsg.A.no(index).poSchdRelDt_A1.getValue();
                        // QC#29645
                        relRqstToPoOrdNum = sMsg.A.no(index).relRqstToPoOrdNum_A1.getValue();
                    }
                }
                errorFlg = NPAL1280CommonLogic.chkAslDtl(sMsg, glblCmpyCd, salesDate, index);
                if (!errorFlg) {
                    // QC#28143
                    if (errScrnInex == -1) { 
                        errScrnInex = (index / maxDisplayRows) * maxDisplayRows + 1;
                    }
                    isErr = true;
                }
                errorFlg = NPAL1280CommonLogic.convNPZC1290Call(sMsg, glblCmpyCd, salesDate, index, cMsg);
                if (!errorFlg) {
                    // QC#28143
                    if (errScrnInex == -1) { 
                        errScrnInex = (index / maxDisplayRows) * maxDisplayRows + 1;
                    }
                    isErr = true;
                }
                if (ZYPCommonFunc.hasValue(sMsg.A.no(index).prchReqLineStsCd_HD) && !(PRCH_REQ_LINE_STS.CANCELLED.equals(sMsg.A.no(index).prchReqLineStsCd_HD.getValue()))) {
                    NPAL1280CommonLogic.convUnitPrice(cMsg, sMsg, glblCmpyCd, salesDate, index);
                } else if (!(ZYPCommonFunc.hasValue(sMsg.A.no(index).prchReqLineStsCd_HD))
                        && (PO_MDSE_CMPSN_TP.CHILD.equals(sMsg.A.no(index).poMdseCmpsnTpCd_A1.getValue()) || PO_MDSE_CMPSN_TP.REGULAR.equals(sMsg.A.no(index).poMdseCmpsnTpCd_A1.getValue()) || PO_MDSE_CMPSN_TP.PARENT
                                .equals(sMsg.A.no(index).poMdseCmpsnTpCd_A1.getValue()))) {
                    NPAL1280CommonLogic.convUnitPrice(cMsg, sMsg, glblCmpyCd, salesDate, index);
                }

                // START 08/03/2017 QC#18761 Add.
                if (!NPAL1280CommonLogic.chkMdseDuplicateFromAsl(cMsg, sMsg, glblCmpyCd, salesDate, index)) {
                    // QC#28143
                    if (errScrnInex == -1) { 
                        errScrnInex = (index / maxDisplayRows) * maxDisplayRows + 1;
                    }
                    isErr = true;
                }
                // END 08/03/2017 QC#18761 Add.
                // START 12/17/2018 QC#29456 Add.
                // QC#29729 Mod
                // 2019/09/24 QC#53422 Add Start
                if (!isErr) {
                	if (!sMsg.A.no(index).xxScrItem130Txt_A1.isError() && !NPAL1280CommonLogic.checkChargeAccount(sMsg.A.no(index), glblCmpyCd)) {
                        if (errScrnInex == -1) { 
                            errScrnInex = (index / maxDisplayRows) * maxDisplayRows + 1;
                        }
                        isErr = true;
                    }
                }
                // 2019/09/24 QC#53422 Add End
                // END  12/17/2018 QC#29456 Add.
            }
        }
        // QC#28143
        if (isErr) {
            cMsg.xxPageShowFromNum.setValue(errScrnInex);
            NPAL1280CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
            return;
        }

        // QC#23726 07/03/2018 Check Customer Carrier Service
        // Relation.
        if (NPAL1280CommonLogic.checkCustCarrSvcLvlRelation(glblCmpyCd, cMsg)) {
            // Error.
            cMsg.shpgSvcLvlCd_SL.setErrorInfo(1, NPAM1365E);
            // QC#29155 Update.
            cMsg.carrCd_HF.setErrorInfo(1, NPAM1365E);
            return;
        }

        // START 2019/12/03 T.Ogura [QC#54814,ADD]
        if (!NPAL1280CommonLogic.checkShipToCustomer(cMsg, sMsg)) {
            // Error.
            return;
        }
        // END   2019/12/03 T.Ogura [QC#54814,ADD]

        // lock and exclusive check for PRCH_REQ
        PRCH_REQTMsg prTMsg = NPAL1280CommonLogic.findPrchReqByKeyForUpdate(sMsg, glblCmpyCd, cMsg);
        if (prTMsg == null) {
            cMsg.setMessageInfo(NMAM0038I);
            return;
        }
        if (NPAM0006E.equals(cMsg.getMessageCode())) {
            NPAL1280CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
            return;
        }

        List<PRCH_REQ_DTLTMsg> prdTMsgList = new ArrayList<PRCH_REQ_DTLTMsg>();
        List<NPAL1280_ASMsg> prdTMsgNewList = new ArrayList<NPAL1280_ASMsg>();

        // add new line
        // ***************************************************
        for (int index = 0; index < sMsg.A.getValidCount(); index++) {

            if (!PRCH_REQ_LINE_STS.CANCELLED.equals(sMsg.A.no(index).prchReqLineStsCd_HD.getValue())) {

                // lock and exclusive check for PRCH_REQ_DTL
                PRCH_REQ_DTLTMsg prdTMsg = NPAL1280CommonLogic.findPrchReqDtlByKeyForUpdate(sMsg, glblCmpyCd, index, cMsg);

                if (prdTMsg == null) {
                    prdTMsgNewList.add(sMsg.A.no(index));
                }

                if (NPAM0006E.equals(cMsg.getMessageCode())) {
                    NPAL1280CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
                    return;
                }
            }
        }

        // add new line
        // ***************************************************
        if (prdTMsgNewList.size() > 0) {
            NPZC103001PMsg params = NPAL1280CommonLogic.savePrUpdateNew(cMsg, prdTMsgNewList, glblCmpyCd, salesDate, loginUserId, PRCH_REQ_APVL_STS.ENTERED, prTMsg);
            NPAL1280CommonLogic.callNPZC1030(params, cMsg);
        }

        for (int index = 0; index < sMsg.A.getValidCount(); index++) {

            if (!PRCH_REQ_LINE_STS.CANCELLED.equals(sMsg.A.no(index).prchReqLineStsCd_HD.getValue())) {

                // lock and exclusive check for PRCH_REQ_DTL
                PRCH_REQ_DTLTMsg prdTMsg = NPAL1280CommonLogic.findPrchReqDtlByKeyForUpdate(sMsg, glblCmpyCd, index, cMsg);
                prdTMsgList.add(prdTMsg);

                if (NPAM0006E.equals(cMsg.getMessageCode())) {
                    NPAL1280CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
                    return;
                }
            }
        }

        // START 2022/10/31 N.Takatsu[QC#60604, ADD]
        if (!ZYPConstant.FLG_ON_Y.equals(cMsg.xxWrnSkipFlg_A.getValue())) {
            if (NPAL1280CommonLogic.isManualDropShipWHCd(glblCmpyCd, cMsg.destRtlWhCd.getValue())) {
                if (NPAL1280CommonLogic.isCsaWh(glblCmpyCd, cMsg.shipToCustCd.getValue())) {
                    cMsg.setMessageInfo(NPAM1655W);
                    ZYPEZDItemValueSetter.setValue(cMsg.xxWrnSkipFlg_A, ZYPConstant.FLG_ON_Y);
                    return;
                }
            }
        }
        // END 2022/10/31 N.Takatsu[QC#60604, ADD]

        NPZC103001PMsg params = null;

        if (PRCH_REQ_APVL_STS.ENTERED.equals(prTMsg.prchReqApvlStsCd.getValue()) || PRCH_REQ_APVL_STS.REJECTED.equals(prTMsg.prchReqApvlStsCd.getValue())) {

            params = NPAL1280CommonLogic.savePrUpdateUpdate(cMsg, sMsg, glblCmpyCd, salesDate, prTMsg, prdTMsgList, PRCH_REQ_APVL_STS.SUBMIT_FOR_APPROVAL, loginUserId);

        } else {

            params = NPAL1280CommonLogic.savePrUpdateUpdate(cMsg, sMsg, glblCmpyCd, salesDate, prTMsg, prdTMsgList, cMsg.prchReqApvlStsCd.getValue(), loginUserId);

        }

        // START 2021/04/23 [QC#58645,ADD]
        if (NPAL1280CommonLogic.checkAutoApprove(glblCmpyCd, prdTMsgList)) {
            ZYPEZDItemValueSetter.setValue(params.prchReqApvlStsCd, PRCH_REQ_APVL_STS.PRE_APPROVED);
        }
        // END 2021/04/23 [QC#58645,ADD]
        NPAL1280CommonLogic.callNPZC1030(params, cMsg);

        // create MSG
        int internalPoMsgSegId = 0;
        if (sMsg.R.getValidCount() > 0) {
            for (int i = 0; i < sMsg.R.getValidCount(); i++) {
                NPAL1280_RSMsg msgInfo = sMsg.R.no(i);

                if (hasValue(msgInfo.poMsgPk)) {
                    // update
                    PO_MSGTMsg poMsg = NPAL1280CommonLogic.getPoMsg(glblCmpyCd, msgInfo.poMsgPk.getValue());

                    if (poMsg != null && hasValue(poMsg.poMsgPk)) {
                        NPAL1280CommonLogic.updatePoMsg(cMsg, poMsg, msgInfo.poMsgTxt.getValue());
                    }

                } else {
                    BigDecimal poMsgPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PO_MSG_SQ);
                    ZYPEZDItemValueSetter.setValue(msgInfo.poMsgPk, poMsgPk);

                    NPAL1280CommonLogic.insertPoMsg(cMsg, glblCmpyCd, msgInfo, internalPoMsgSegId, msgInfo.poMsgTxt.getValue(), sMsg);

                    internalPoMsgSegId++;
                }
            }
        }
        NPAL1280CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);

        cMsg.setMessageInfo(ZZZM9003I, new String[] {"Submit" });
    }

    /**
     * Header cancel button event
     * @param cMsg NPAL1280CMsg
     * @param sMsg NPAL1280SMsg
     * @param glblCmpyCd String
     * @param salesDate String
     * @param loginUserId String
     */
    private void doProcess_NPAL1280_HEADER_CANCEL(NPAL1280CMsg cMsg, NPAL1280SMsg sMsg, String glblCmpyCd, String salesDate, String loginUserId) {
        if (!(ZYPCommonFunc.hasValue(cMsg.xxBtnFlg)) || ZYPConstant.FLG_OFF_N.equals(cMsg.xxBtnFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxBtnFlg, ZYPConstant.FLG_ON_Y);
            cMsg.setMessageInfo(NPAM1359I);
            return;
        } else {
            ZYPEZDItemValueSetter.setValue(cMsg.xxBtnFlg, ZYPConstant.FLG_OFF_N);
        }
        sMsg.A.setValidCount(cMsg.xxPageShowOfNum.getValueInt());
        NPAL1280CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);
        // lock and exclusive check for PRCH_REQ
        NPAL1280CommonLogic.findPrchReqByKeyForUpdate(sMsg, glblCmpyCd, cMsg);
        if (NPAM0006E.equals(cMsg.getMessageCode())) {
            NPAL1280CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
            return;
        }
        NPZC103001PMsg params = NPAL1280CommonLogic.getHeaderCancelParam(cMsg, glblCmpyCd);
        NPAL1280CommonLogic.callNPZC1030(params, cMsg);
        NPAL1280CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);

        // Re Search.
        ZYPEZDItemValueSetter.setValue(sMsg.xxSrchTrfFlg_HD, ZYPConstant.FLG_ON_Y);
    }

    /**
     * Line cancel evnet
     * @param cMsg NPAL1280CMsg
     * @param sMsg NPAL1280SMsg
     * @param glblCmpyCd String
     * @param salesDate String
     * @param loginUserId String
     */
    private void doProcess_NPAL1280_LINE_CANCEL(NPAL1280CMsg cMsg, NPAL1280SMsg sMsg, String glblCmpyCd, String salesDate, String loginUserId) {
        if (!(ZYPCommonFunc.hasValue(cMsg.xxBtnFlg)) || ZYPConstant.FLG_OFF_N.equals(cMsg.xxBtnFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(cMsg.xxBtnFlg, ZYPConstant.FLG_ON_Y);
            cMsg.setMessageInfo(NPAM1359I);
            return;
        } else {
            ZYPEZDItemValueSetter.setValue(cMsg.xxBtnFlg, ZYPConstant.FLG_OFF_N);
        }
        sMsg.A.setValidCount(cMsg.xxPageShowOfNum.getValueInt());
        NPAL1280CommonLogic.copyFromCmsgOntoSmsg(cMsg, sMsg);

        boolean checkFlg = false;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A1.getValue())) {
                checkFlg = true;
                break;
            }
        }
        if (!checkFlg) {
            cMsg.setMessageInfo(NPAM0049E);
            NPAL1280CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
            return;
        }

        // Line Status Check
        int maxDisplayRows = cMsg.A.length();
        int errIndex = 0;
        boolean lineSsErrFlg = false;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).prchReqLineStsCd_HD)
                    && ZYPConstant.CHKBOX_ON_Y.equals(sMsg.A.no(i).xxChkBox_A1.getValue())
                    && !(PRCH_REQ_LINE_STS.OPEN.equals(sMsg.A.no(i).prchReqLineStsCd_HD.getValue()) || PRCH_REQ_LINE_STS.PR_IMPORT_ERROR.equals(sMsg.A.no(i).prchReqLineStsCd_HD.getValue()))) {

                sMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NPAM0008E);
                errIndex = i;
                lineSsErrFlg = true;
            }
        }

        if (lineSsErrFlg) {
            int errScrnInex = (errIndex / maxDisplayRows) * maxDisplayRows + 1;
            cMsg.xxPageShowFromNum.setValue(errScrnInex);
            NPAL1280CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
            return;
        }

        // Set Item Line Check QC#14890
        boolean setItemFlg = false;
        boolean errFlg = false;
        maxDisplayRows = cMsg.A.length();
        errIndex = 0;
        String prchReqLineNum = "";

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {

            NPAL1280_ASMsg asMsg = sMsg.A.no(i);

            if (PO_MDSE_CMPSN_TP.PARENT.equals(asMsg.poMdseCmpsnTpCd_A1.getValue())) {
                if (ZYPConstant.CHKBOX_ON_Y.equals(asMsg.xxChkBox_A1.getValue())) {
                    setItemFlg = true;
                } else {
                    setItemFlg = false;
                }
                prchReqLineNum = asMsg.prchReqLineNum_A1.getValue();
            } else if (prchReqLineNum.equals(asMsg.prchReqLineNum_A1.getValue())) {
                if (ZYPConstant.CHKBOX_ON_Y.equals(asMsg.xxChkBox_A1.getValue())) {
                    if (setItemFlg) {
                        continue;
                    } else {
                        errIndex = i;
                        asMsg.xxChkBox_A1.setErrorInfo(1, NPAM1588E);
                        errFlg = true;
                    }
                }
            } else {
                setItemFlg = false;
            }
        }

        if (errFlg) {
            int errScrnInex = (errIndex / maxDisplayRows) * maxDisplayRows + 1;
            cMsg.xxPageShowFromNum.setValue(errScrnInex);
            NPAL1280CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
            return;
        }

        // lock and exclusive check for PRCH_REQ
        PRCH_REQTMsg prTMsg = NPAL1280CommonLogic.findPrchReqByKeyForUpdate(sMsg, glblCmpyCd, cMsg);
        if (NPAM0006E.equals(cMsg.getMessageCode())) {
            NPAL1280CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
            return;
        }
        if (prTMsg == null) {
            // remove row
            String parentNum = "999";
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                NPAL1280_ASMsg bizMsg = sMsg.A.no(i);
                if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_A1.getValue())) {

                    if (PO_MDSE_CMPSN_TP.PARENT.equals(sMsg.A.no(i).poMdseCmpsnTpCd_A1.getValue())) {
                        parentNum = sMsg.A.no(i).xxLineNum_A1.getValue();
                        NPAL1280CommonLogic.removeRow(sMsg.A, i);
                        i--;
                    } else {
                        NPAL1280CommonLogic.removeRow(sMsg.A, i);
                        i--;
                    }
                }
                if (i >= 0) {
                    if (PO_MDSE_CMPSN_TP.CHILD.equals(sMsg.A.no(i).poMdseCmpsnTpCd_A1.getValue())) {
                        String lineNum = sMsg.A.no(i).xxLineNum_A1.getValue();
                        if (lineNum.startsWith(parentNum)) {
                            NPAL1280CommonLogic.removeRow(sMsg.A, i);
                            i--;
                        }
                    }
                }
            }
            // Line Delete Only.
            NPAL1280CommonLogic.relocationLineNumber(glblCmpyCd, cMsg, sMsg);
            ZYPEZDItemValueSetter.setValue(sMsg.xxSrchTrfFlg_HD, ZYPConstant.FLG_OFF_N);
            cMsg.setMessageInfo(ZZM8100I);
        } else {
            List<PRCH_REQ_DTLTMsg> prdTMsgList = new ArrayList<PRCH_REQ_DTLTMsg>();

            String parentNum = "999";
            for (int index = 0; index < sMsg.A.getValidCount(); index++) {
                NPAL1280_ASMsg bizMsg = sMsg.A.no(index);
                if (PO_MDSE_CMPSN_TP.CHILD.equals(bizMsg.poMdseCmpsnTpCd_A1.getValue())) {
                    String lineNum = sMsg.A.no(index).xxLineNum_A1.getValue();
                    if (lineNum.startsWith(parentNum)) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_A1, ZYPConstant.FLG_ON_Y);
                    }
                }

                // lock and exclusive check for PRCH_REQ_DTL
                if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_A1.getValue())) {

                    if (PO_MDSE_CMPSN_TP.PARENT.equals(bizMsg.poMdseCmpsnTpCd_A1.getValue())) {
                        parentNum = bizMsg.xxLineNum_A1.getValue();
                    }

                    PRCH_REQ_DTLTMsg prdTMsg = NPAL1280CommonLogic.findPrchReqDtlByKeyForUpdate(sMsg, glblCmpyCd, index, cMsg);
                    if (NPAM0006E.equals(cMsg.getMessageCode())) {
                        NPAL1280CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
                        return;
                    } else if (prdTMsg == null) {
                        // PR Line Not found
                        NPAL1280CommonLogic.removeRow(sMsg.A, index);
                        index--;
                        continue;
                    }
                    prdTMsgList.add(prdTMsg);
                }
            }
            if (prdTMsgList.size() > 0) {
                NPZC103001PMsg params = NPAL1280CommonLogic.getLineCancelParam(cMsg, glblCmpyCd, prdTMsgList);
                params.prchReqInfo.setValidCount(prdTMsgList.size());
                NPAL1280CommonLogic.callNPZC1030(params, cMsg);
                // Re Search.
                ZYPEZDItemValueSetter.setValue(sMsg.xxSrchTrfFlg_HD, ZYPConstant.FLG_ON_Y);
            } else {
                // Line Delete Only.
                NPAL1280CommonLogic.relocationLineNumber(glblCmpyCd, cMsg, sMsg);
                ZYPEZDItemValueSetter.setValue(sMsg.xxSrchTrfFlg_HD, ZYPConstant.FLG_OFF_N);
            }
        }
        // QC#28216
        NPAL1280CommonLogic.setMultipleHeader(cMsg, sMsg);
        NPAL1280CommonLogic.copyFromSMsgOntoCmsg(cMsg, sMsg);
        // QC#21170
        NPAL1280CommonLogic.getHeaderRqstRcvDt(cMsg, sMsg, salesDate);
        // START 2023/02/06 T.Kuroda [QC#60966, ADD]
        NPAL1280CommonLogic.getHeaderRqstShipDt(cMsg, sMsg, salesDate);
        // END   2023/02/06 T.Kuroda [QC#60966, ADD]

    }
}
