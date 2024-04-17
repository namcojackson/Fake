/**
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NMAL4500;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL4500.common.NMAL4500CommonLogic;
import business.blap.NMAL4500.constant.NMAL4500Constant;
import business.db.CCYTMsg;
import business.db.CMPYTMsg;
import business.db.COA_AFFLTMsg;
import business.db.LOC_USGTMsg;
import business.db.PTYTMsg;
import business.db.PTY_LOC_WRKTMsg;
import business.db.VNDTMsg;
import business.db.VNDTMsgArray;
import business.db.VND_TP_RELNTMsg;
import business.db.WHTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/09/01   SRA             T.Chijimatsu    Create          N/A
 * 2010/04/05   Fujitsu         T.ishii         Update          5203,5206
 * 2013/05/08   Fujitsu         F.Saito         Update          WDS#R-MS001
 * 2013/08/05   Fujitsu         N.Sugiura       Update          QC1469
 * 2013/09/19   Fujitsu         N.Sugiura       Update          MEX-LC004
 * 2013/10/21   Fujitsu         D.Yanagisawa    Update          MEX-LC001
 * 
 *</pre>
 */
public class NMAL4500BL06 extends S21BusinessHandler implements NMAL4500Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NMAL4500Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL4500Scrn00_CMN_Submit((NMAL4500CMsg) cMsg, (NMAL4500SMsg) sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NMAL4500Scrn00_CMN_Submit(NMAL4500CMsg cMsg, NMAL4500SMsg sMsg) {

        String globalCompanyCode = getGlobalCompanyCode();
        // must check
        boolean isMandatoryError = false;

        boolean isMasterError = false;

        // VND_CD check
        // when warehouse code is selected, vndCd and locNm check are
        // not necessary.<defect#5206 T.Ishii 20100405>
        if (!ZYPCommonFunc.hasValue(cMsg.whPk_03)) {
            if (!ZYPCommonFunc.hasValue(cMsg.vndCd_01)) {
                cMsg.vndCd_01.setErrorInfo(1, "NMAM0041E", new String[] {"Vendor Code" });
                cMsg.setMessageInfo("NMAM0041E", new String[] {"Vendor Code" });
                isMandatoryError = true;
            }
            if (!ZYPCommonFunc.hasValue(cMsg.locNm_01)) {
                cMsg.locNm_01.setErrorInfo(1, "NMAM0041E", new String[] {"Vendor Name" });
                cMsg.setMessageInfo("NMAM0041E", new String[] {"Vendor Name" });
                isMandatoryError = true;
            }
        }
        // QC 1114 Affiliation code -- must check
        // 10/22/2015 mod start
        // if (!ZYPCommonFunc.hasValue(cMsg.coaAfflCd_01)) {
        // cMsg.coaAfflCd_01.setErrorInfo(1, "NMAM0041E", new String[]
        // {"Affiliation Code" });
        // cMsg.setMessageInfo("NMAM0041E", new String[]
        // {"Affiliation Code" });
        // isMandatoryError = true;
        // }
        ZYPEZDItemValueSetter.setValue(cMsg.coaAfflCd_01, "000");
        // 10/22/2015 mod end

        if (ZYPConstant.FLG_ON_Y.equals(cMsg.thirdPtyVndFlg_01.getValue())) {
            if (!ZYPCommonFunc.hasValue(cMsg.trsmtMethTpCd_03)) {
                cMsg.trsmtMethTpCd_03.setErrorInfo(1, "NMAM0041E", new String[] {"Outb PO Trans Method" });
                cMsg.setMessageInfo("NMAM0041E", new String[] {"Outb PO Trans Method or Receive Method Type" });
                isMandatoryError = true;
            }
            if (!ZYPCommonFunc.hasValue(cMsg.invRcvMethTpCd_03)) {
                cMsg.invRcvMethTpCd_03.setErrorInfo(1, "NMAM0041E", new String[] {"Receive Method Type" });
                cMsg.setMessageInfo("NMAM0041E", new String[] {"Outb PO Trans Method or Receive Method Type" });
                isMandatoryError = true;
            }
        }
        // Exit when musk check error
        if (isMandatoryError) {
            return;
        }

        // E2E.730 <Y.Chen 20100317>
        if (isOutboundCarrierVendor(cMsg)) {
            if (cMsg.vndCd_01.getValue().length() > MAX_VND_CD_LEN_OF_OUTBOUND_CARRIER) {
                cMsg.setMessageInfo("NMAM8064E");
                return;
            }
        }

        // confirmation for WH Code - Vender Code(and Name)
        // Relation.<defect#5206 T.ishii 20100405>
        if (ZYPCommonFunc.hasValue(cMsg.whPk_03)) {
            WHTMsg wHTMsg = this.getWHInfo(cMsg);
            if (wHTMsg instanceof WHTMsg) {
                String whVndCd = wHTMsg.whCd.getValue() + NMAL4500Constant.SUFFIX_VND_CD_FOR_IN_HOUSE_KITTING_OR_IN_HOUSE_REFURBISH;
                String whLocNm = wHTMsg.locNm.getValue();
                ZYPEZDItemValueSetter.setValue(cMsg.whCd_04, wHTMsg.whCd);

                if (!ZYPCommonFunc.hasValue(whLocNm)) {
                    whLocNm = "";
                }
                // vender name is always upper case.
                whLocNm = whLocNm.toUpperCase();

                if (!isValidatedVenderInfo(cMsg, whVndCd, whLocNm)) {
                    // confirm vender code and vender name.
                    ZYPEZDItemValueSetter.setValue(cMsg.vndCd_01, whVndCd);
                    ZYPEZDItemValueSetter.setValue(cMsg.locNm_01, whLocNm);
                    cMsg.setMessageInfo("NMAM8070W", new String[] {whVndCd, whLocNm });
                    cMsg.vndCd_01.setErrorInfo(2, "NMAM8070W", new String[] {whVndCd, whLocNm });
                    cMsg.locNm_01.setErrorInfo(2, "NMAM8070W", new String[] {whVndCd, whLocNm });

                    return;
                }
            }
        }

        // Remove duplicate check when all address info are null
        if (hasAddressInput(cMsg)) {
            S21SsmEZDResult ssmResult = NMAL4500Query.getInstance().getSameLocationVenderList(cMsg, getGlobalCompanyCode());
            if (ssmResult.isCodeNormal()) {
                if (!cMsg.xxScrEventNm_01.getValue().equals(VND_CHECK)) {
                    cMsg.xxScrEventNm_01.setValue(VND_CHECK);
                    cMsg.setMessageInfo("NMAM0164I", new String[] {"Dupulicate" });
                    return;
                }
            }
        }
        cMsg.xxScrEventNm_01.clear();
        // MOD START 2013/08/05 QC1469
        // if (LIST_COUNTRY_USA.equals(cMsg.ctryCd_03.getValue())) {
        // if (!ZYPCommonFunc.hasValue(cMsg.ctyAddr_01)) {
        // cMsg.ctyAddr_01.setErrorInfo(1, "NMAM0041E", new String[]
        // {"City" });
        // isMandatoryError = true;
        // }
        // if (!ZYPCommonFunc.hasValue(cMsg.stCd_01)) {
        // cMsg.stCd_01.setErrorInfo(1, "NMAM0041E", new String[]
        // {"State" });
        // isMandatoryError = true;
        // }
        // if (!ZYPCommonFunc.hasValue(cMsg.postCd_01)) {
        // cMsg.postCd_01.setErrorInfo(1, "NMAM0041E", new String[]
        // {"Postal Code" });
        // isMandatoryError = true;
        // }
        // if (!NMAL4500CommonLogic.Country_check(cMsg,
        // globalCompanyCode)) {
        // isMasterError = true;
        // }
        // } else {
        // if (ZYPCommonFunc.hasValue(cMsg.ctryCd_03)) {
        // // not USA city and Postalcode must input check
        // if (!ZYPCommonFunc.hasValue(cMsg.ctyAddr_01)) {
        // cMsg.ctyAddr_01.setErrorInfo(1, "NMAM0041E", new String[]
        // {"City" });
        // isMandatoryError = true;
        // }
        // if (!ZYPCommonFunc.hasValue(cMsg.postCd_01)) {
        // cMsg.postCd_01.setErrorInfo(1, "NMAM0041E", new String[]
        // {"PostalCode" });
        // isMandatoryError = true;
        // }
        // }
        // }
        if (ZYPCommonFunc.hasValue(cMsg.ctryCd_03)) {
            // Postalcode must input check
            if (!ZYPCommonFunc.hasValue(cMsg.ctyAddr_01)) {
                cMsg.ctyAddr_01.setErrorInfo(1, "NMAM0041E", new String[] {"City" });
                isMandatoryError = true;
            }
            if (!ZYPCommonFunc.hasValue(cMsg.postCd_01)) {
                cMsg.postCd_01.setErrorInfo(1, "NMAM0041E", new String[] {"PostalCode" });
                isMandatoryError = true;
            }
            if (!ZYPCommonFunc.hasValue(cMsg.stCd_01)) {
                cMsg.stCd_01.setErrorInfo(1, "NMAM0041E", new String[] {"State" });
                isMandatoryError = true;
            }
        }

        if (ZYPCommonFunc.hasValue(cMsg.stCd_01)) {
            S21SsmEZDResult ssmResult = NMAL4500Query.getInstance().cntStCd(cMsg, this.getGlobalCompanyCode());
            if (ssmResult.isCodeNormal()) {
                if ((Integer) ssmResult.getResultObject() < 1) {
                    cMsg.stCd_01.setErrorInfo(1, "NMAM0039E", new String[] {"State" });
                    isMasterError = true;
                }
            } else {
                cMsg.setMessageInfo("NWDM0007E");
                isMasterError = true;
            }
        }
        // ADD START 2013/09/19 MEX-LC004
        if (ZYPCommonFunc.hasValue(cMsg.dealCcyCd_01)) {
            CCYTMsg tMsg = new CCYTMsg();
            tMsg.glblCmpyCd.setValue(globalCompanyCode);
            tMsg.ccyCd.setValue(cMsg.dealCcyCd_01.getValue());
            if (EZDTBLAccessor.findByKey(tMsg) == null) {
                cMsg.dealCcyCd_01.setErrorInfo(1, "NMAM0039E", new String[] {"Deal Currency Code" });
                isMasterError = true;
            }
        }
        // ADD END 2013/09/19 MEX-LC004
        // MOD END 2013/08/05 QC1469

        // ADD START 2013/10/21 MEX-LC001
        String vndCd = cMsg.vndCd_01.getValue();
        String invVndCd = cMsg.invVndCd_01.getValue();
        if (ZYPCommonFunc.hasValue(invVndCd)) {
            if (!invVndCd.equals(vndCd)) {
                VNDTMsgArray invVndTMsgArray = NMAL4500CommonLogic.db_inv_vnd_search(globalCompanyCode, invVndCd);
                if (invVndTMsgArray == null || invVndTMsgArray.length() == 0) {
                    cMsg.invVndCd_01.setErrorInfo(1, "NMAM0039E", new String[] {"Vendor" });
                    isMasterError = true;
                } else {
                    VNDTMsg invVndTMsg = invVndTMsgArray.no(0);
                    String invVndLocNm = invVndTMsg.locNm.getValue();
                    ZYPEZDItemValueSetter.setValue(cMsg.locNm_02, invVndLocNm);
                }
            } else {
                ZYPEZDItemValueSetter.setValue(cMsg.locNm_02, cMsg.locNm_01);
            }
        } else {
            cMsg.locNm_02.clear();
        }
        // ADD E N D 2013/10/21 MEX-LC001

        // Affiliation code -- existence in Master
        COA_AFFLTMsg recordTMsg = new COA_AFFLTMsg();

        recordTMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        recordTMsg.coaAfflCd.setValue(cMsg.coaAfflCd_01.getValue());

        recordTMsg = (COA_AFFLTMsg) EZDTBLAccessor.findByKey(recordTMsg);

        if (null == recordTMsg) {
            cMsg.coaAfflCd_01.setErrorInfo(1, "NMAM0039E", new String[] {"Affiliation" });
            isMasterError = true;
        }

        if (checkAffiliation(cMsg) == false) {
            isMasterError = true;
        }

        if (isMandatoryError || isMasterError) {
            return;
        }
        // check address
        if (!NMAL4500CommonLogic.checkAddress(globalCompanyCode, cMsg, sMsg)) {
            return;
        }
        // Update DB
        VNDTMsgArray vndR = NMAL4500CommonLogic.db_vnd_search(cMsg, globalCompanyCode);
        if (vndR.length() == 0) {
            // confirmation for WH Code - Vender Type.<defect#5206
            // T.ishii 20100405>
            if (ZYPCommonFunc.hasValue(cMsg.whPk_03)) {
                // check duplicate WH code and Vender Type.
                if (!validateDuplicateWHCdAndVndTp(cMsg, null)) {
                    return;
                }
            }

            if (NMAL4500CommonLogic.isDuplicateInventoryLocation(getGlobalCompanyCode(), cMsg.vndCd_01.getValue())) {
                cMsg.vndCd_01.setErrorInfo(1, "NMAM0071E", new String[] {"VendorCode" });
                return;
            }
            // insert
            if (!doProcess_NMAL4500Scrn00_CMN_Submit_Create(cMsg)) {
                // ENVIRONMENT DB ERROR
                return;
            }
        } else {
            // update
            BigDecimal vndSeq = vndR.no(ONE_ROW).vndPk.getValue();

            // confirmation for WH Code - Vender Type.<defect#5206
            // T.ishii 20100405>
            if (ZYPCommonFunc.hasValue(cMsg.whPk_03)) {
                // check duplicate WH code and Vender Type.
                if (!validateDuplicateWHCdAndVndTp(cMsg, vndSeq)) {
                    return;
                }
            }
            // DefectID: 1243
            // CMPY : PTY_LOC_WRK : VND = 1 : 1 : 1
            // CMPY.CMPY_PK == PTY_LOC_WRK.CMPY_PK == VND.CMPY_PK
            cMsg.cmpyPk_01.setValue(vndR.no(ONE_ROW).cmpyPk.getValue());
            // VND.PTY_LOC_PK == PTY_LOC_WRK.PTY_LOC_PK
            cMsg.ptyLocPk_01.setValue(vndR.no(ONE_ROW).ptyLocPk.getValue());
            cMsg.ezUpTime_01.setValue(vndR.no(ONE_ROW).ezUpTime.getValue());
            cMsg.ezUpTimeZone_01.setValue(vndR.no(ONE_ROW).ezUpTimeZone.getValue());
            doProcess_NMAL4500Scrn00_CMN_Submit_Update(cMsg, vndSeq);
        }
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            VND_TP_RELNTMsg vndtprelnR = NMAL4500CommonLogic.db_vndtpreln_searchUpdate(cMsg, this.getGlobalCompanyCode(), cMsg.A.no(i).vndTpCd_A1.getValue());
            String updateType = divideUpdateType(vndtprelnR, cMsg.A.no(i).xxChkBox_A1.getValue());
            // CREATE
            if (updateType.equals(CREATE)) {
                vndtprelnR = new VND_TP_RELNTMsg();
                // SET VALUE
                vndtprelnR.glblCmpyCd.setValue(this.getGlobalCompanyCode());
                vndtprelnR.vndCd.setValue(cMsg.vndCd_01.getValue());
                vndtprelnR.vndTpCd.setValue(cMsg.A.no(i).vndTpCd_A1.getValue());
                EZDTBLAccessor.create(vndtprelnR);
                // ENVIRONMENT DB ERROR
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(vndtprelnR.getReturnCode())) {
                    cMsg.setMessageInfo(ENVIRONMENT_DB_ACCESS_ERROR, new String[] {vndtprelnR.getTableName() });
                    return;
                }
            } else if (updateType.equals(LOGICAL_REMOVE)) {
                if (cMsg.vndCd_01.getValue().equals(vndtprelnR.vndCd.getValue()) && cMsg.A.no(i).vndTpCd_A1.getValue().equals(vndtprelnR.vndTpCd.getValue())) {
                    if (ZYPCommonFunc.hasValue(cMsg.A.no(i).ezUpTime_A1) && ZYPCommonFunc.hasValue(cMsg.A.no(i).ezUpTimeZone_A1)) {
                        if (!cMsg.A.no(i).ezUpTime_A1.getValue().equals(vndtprelnR.ezUpTime.getValue()) || !cMsg.A.no(i).ezUpTimeZone_A1.getValue().equals(vndtprelnR.ezUpTimeZone.getValue())) {
                            cMsg.setMessageInfo("NZZM0003E");
                            return;
                        }
                    }
                    EZDTBLAccessor.logicalRemove(vndtprelnR);
                }
            }
        }
        // Set completed message
        cMsg.setMessageInfo("NZZM0002I");
    }

    private static String divideUpdateType(VND_TP_RELNTMsg vndtprelnR, String checkBox) {
        if (checkBox.equals(ZYPConstant.CHKBOX_ON_Y)) {
            if (vndtprelnR == null || (vndtprelnR != null && vndtprelnR.ezCancelFlag.getValue().equals(ZYPConstant.FLG_ON_1))) {
                return CREATE;
            }
        } else {
            if (vndtprelnR != null && vndtprelnR.ezCancelFlag.getValue().equals(ZYPConstant.FLG_OFF_0)) {
                return LOGICAL_REMOVE;
            }
        }
        return NONE;
    }

    private boolean doProcess_NMAL4500Scrn00_CMN_Submit_Create(NMAL4500CMsg cMsg) {
        String globalCompanyCode = getGlobalCompanyCode();
        // set Seq
        BigDecimal vndSeq = ZYPOracleSeqAccessor.getNumberBigDecimal(SEQ_VND_PK);
        BigDecimal cmpySeq = ZYPOracleSeqAccessor.getNumberBigDecimal(SEQ_CMPY_PK);
        BigDecimal ptyLocWrkSeq = ZYPOracleSeqAccessor.getNumberBigDecimal(SEQ_PTY_LOC_WRK_PK);
        BigDecimal locUsgSeq = ZYPOracleSeqAccessor.getNumberBigDecimal(SEQ_LOC_USG_PK);
        // BigDecimal shipSeq =
        // ZYPOracleSeqAccessor.getNumberBigDecimal(SEQ_SHIP_TO_CUST);
        // CMPY
        CMPYTMsg tcmpy = NMAL4500CommonLogic.setCreateCMPY(cMsg, this.getGlobalCompanyCode(), cmpySeq);
        EZDTBLAccessor.create(tcmpy);
        // ENVIRONMENT DB ERROR
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tcmpy.getReturnCode())) {
            cMsg.setMessageInfo(ENVIRONMENT_DB_ACCESS_ERROR, new String[] {tcmpy.getTableName() });
            return false;
        }
        // PTY_LOC_WRK
        String xxeffFromDt = ZYPDateUtil.getSalesDate(this.getGlobalCompanyCode());
        PTY_LOC_WRKTMsg tptylocwrk = NMAL4500CommonLogic.setCreatePTYLOCWRK(cMsg, this.getGlobalCompanyCode(), ptyLocWrkSeq);
        tptylocwrk.cmpyPk.setValue(cmpySeq);
        tptylocwrk.apvlStsCd.setValue(AP);
        tptylocwrk.effFromDt.setValue(xxeffFromDt);
        tptylocwrk.effThruDt.setValue(EFF_THRU_DATE);
        tptylocwrk.rgtnStsCd.setValue(P20);
        tptylocwrk.embgoFlg.setValue(embgoFlg_N);
        tptylocwrk.dsInsdCtyLimitFlg.setValue(ZYPConstant.FLG_OFF_N);
        EZDTBLAccessor.create(tptylocwrk);
        // ENVIRONMENT DB ERROR
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tptylocwrk.getReturnCode())) {
            cMsg.setMessageInfo(ENVIRONMENT_DB_ACCESS_ERROR, new String[] {tptylocwrk.getTableName() });
            return false;
        }
        // LOC_USG
        LOC_USGTMsg tlocusg = new LOC_USGTMsg();
        tlocusg.glblCmpyCd.setValue(globalCompanyCode);
        tlocusg.locUsgPk.setValue(locUsgSeq);
        tlocusg.ptyLocPk.setValue(ptyLocWrkSeq);
        tlocusg.locRoleTpCd.setValue(ROLE_VENDOR);
        tlocusg.locGrpCd.setValue(GRP_VENDOR);
        EZDTBLAccessor.create(tlocusg);
        // ENVIRONMENT DB ERROR
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tlocusg.getReturnCode())) {
            cMsg.setMessageInfo(ENVIRONMENT_DB_ACCESS_ERROR, new String[] {tlocusg.getTableName() });
            return false;
        }
        // PTY
        PTYTMsg tpty = new PTYTMsg();
        tpty.glblCmpyCd.setValue(globalCompanyCode);
        tpty.ptyCd.setValue(cMsg.vndCd_01.getValue());
        tpty.locGrpCd.setValue(GRP_VENDOR);
        tpty.ptyLocPk.setValue(ptyLocWrkSeq);
        EZDTBLAccessor.create(tpty);
        // ENVIRONMENT DB ERROR
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tpty.getReturnCode())) {
            cMsg.setMessageInfo(ENVIRONMENT_DB_ACCESS_ERROR, new String[] {tpty.getTableName() });
            return false;
        }
        // VND
        VNDTMsg tvnd = NMAL4500CommonLogic.setCreateVND(cMsg, this.getGlobalCompanyCode(), vndSeq);
        tvnd.rgtnStsCd.setValue(P20);
        tvnd.ptyLocPk.setValue(ptyLocWrkSeq);
        tvnd.cmpyPk.setValue(cmpySeq);
        tvnd.effFromDt.setValue(xxeffFromDt);
        tvnd.effThruDt.setValue(EFF_THRU_DATE);
        tvnd.locRoleTpCd.setValue(ROLE_VENDOR);
        tvnd.locGrpCd.setValue(GRP_VENDOR);
        tvnd.invRcvMethTpCd.setValue(cMsg.invRcvMethTpCd_03.getValue());
        // add item.<defect#5203, 5206 T.ishii 20100405>
        ZYPEZDItemValueSetter.setValue(tvnd.attnNm, cMsg.attnNm_01);
        ZYPEZDItemValueSetter.setValue(tvnd.whCd, cMsg.whCd_04);

        EZDTBLAccessor.create(tvnd);
        // ENVIRONMENT DB ERROR
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tvnd.getReturnCode())) {
            cMsg.setMessageInfo(ENVIRONMENT_DB_ACCESS_ERROR, new String[] {tvnd.getTableName() });
            return false;
        }

        // SHIP_TO_CUST
        // SHIP_TO_CUSTTMsg ship =
        // NMAL4500CommonLogic.setCreateSHIPTOCUST(tptylocwrk,
        // globalCompanyCode, shipSeq, tpty.ptyCd.getValue());
        // EZDTBLAccessor.create(ship);
        // // ENVIRONMENT DB ERROR
        // if
        // (!EZDTBLAccessor.RTNCD_NORMAL.equals(ship.getReturnCode()))
        // {
        // cMsg.setMessageInfo(ENVIRONMENT_DB_ACCESS_ERROR, new
        // String[] {ship.getTableName() });
        // return false;
        // }

        return true;
    }

    private void doProcess_NMAL4500Scrn00_CMN_Submit_Update(NMAL4500CMsg cMsg, BigDecimal vndSeq) {
        // set SEQ
        BigDecimal ptyLocWrkSeq = new BigDecimal(0);
        // CMPY
        CMPYTMsg tcmpy = new CMPYTMsg();
        tcmpy.glblCmpyCd.setValue(this.getGlobalCompanyCode());
        tcmpy.cmpyPk.setValue(cMsg.cmpyPk_01.getValue());
        tcmpy = (CMPYTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tcmpy);
        if (tcmpy == null) {
            cMsg.setMessageInfo("NZZM0003E");
            return;
        } else {
            if (ZYPCommonFunc.hasValue(cMsg.ezUpTime_02) && ZYPCommonFunc.hasValue(cMsg.ezUpTimeZone_02)) {
                if (!cMsg.ezUpTime_02.getValue().equals(tcmpy.ezUpTime.getValue()) || !cMsg.ezUpTimeZone_02.getValue().equals(tcmpy.ezUpTimeZone.getValue())) {
                    cMsg.setMessageInfo("NZZM0003E");
                    return;
                }
            }
            NMAL4500CommonLogic.setUpdateCMPY(cMsg, tcmpy);
            EZDTBLAccessor.update(tcmpy);
        }
        // PTY_LOC_WRK
        PTY_LOC_WRKTMsg tptylocwrk = new PTY_LOC_WRKTMsg();
        tptylocwrk.glblCmpyCd.setValue(this.getGlobalCompanyCode());
        tptylocwrk.ptyLocPk.setValue(cMsg.ptyLocPk_01.getValue());
        tptylocwrk = (PTY_LOC_WRKTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tptylocwrk);
        if (tptylocwrk == null) {
            cMsg.setMessageInfo("NZZM0003E");
            return;
        } else {
            if (ZYPCommonFunc.hasValue(cMsg.ezUpTime_03) && ZYPCommonFunc.hasValue(cMsg.ezUpTimeZone_03)) {
                if (!cMsg.ezUpTime_03.getValue().equals(tptylocwrk.ezUpTime.getValue()) || !cMsg.ezUpTimeZone_03.getValue().equals(tptylocwrk.ezUpTimeZone.getValue())) {
                    cMsg.setMessageInfo("NZZM0003E");
                    return;
                }
            }
            ptyLocWrkSeq = tptylocwrk.ptyLocPk.getValue();
            NMAL4500CommonLogic.setUpdatePTYLOCWRK(cMsg, tptylocwrk);
            tptylocwrk.apvlStsCd.setValue(AP);
            tptylocwrk.effThruDt.setValue(EFF_THRU_DATE);
            tptylocwrk.rgtnStsCd.setValue(P20);
            tptylocwrk.embgoFlg.setValue(embgoFlg_N);
            EZDTBLAccessor.update(tptylocwrk);
        }
        // VND
        VNDTMsg tvnd = new VNDTMsg();
        tvnd.glblCmpyCd.setValue(this.getGlobalCompanyCode());
        tvnd.vndPk.setValue(vndSeq);
        tvnd = (VNDTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(tvnd);
        if (tvnd == null) {
            cMsg.setMessageInfo("NZZM0003E");
            return;
        } else {
            if (!cMsg.ezUpTime_01.getValue().equals(tvnd.ezUpTime.getValue()) || !cMsg.ezUpTimeZone_01.getValue().equals(tvnd.ezUpTimeZone.getValue())) {
                cMsg.setMessageInfo("NZZM0003E");
                return;
            }
            NMAL4500CommonLogic.setUpdateVND(cMsg, tvnd);
            tvnd.rgtnStsCd.setValue(P20);
            tvnd.ptyLocPk.setValue(ptyLocWrkSeq);
            tvnd.cmpyPk.setValue(tcmpy.cmpyPk.getValue());
            tvnd.effThruDt.setValue(EFF_THRU_DATE);
            tvnd.locRoleTpCd.setValue(ROLE_VENDOR);
            tvnd.locGrpCd.setValue(GRP_VENDOR);
            tvnd.invRcvMethTpCd.setValue(cMsg.invRcvMethTpCd_03.getValue());
            // add item.<defect#5203,5206 T.ishii 20100405>
            ZYPEZDItemValueSetter.setValue(tvnd.attnNm, cMsg.attnNm_01);
            ZYPEZDItemValueSetter.setValue(tvnd.whCd, cMsg.whCd_04);
            
            ZYPEZDItemValueSetter.setValue(tvnd.sendPoEmlAddr, cMsg.sendPoEmlAddr_01);
            ZYPEZDItemValueSetter.setValue(tvnd.dealCcyCd, cMsg.dealCcyCd_01);
            ZYPEZDItemValueSetter.setValue(tvnd.invVndCd, cMsg.invVndCd_01);
            ZYPEZDItemValueSetter.setValue(tvnd.splyPmtFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tvnd.splyPoFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tvnd.sendArcsFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(tvnd.carrTpCd, cMsg.carrTpCd_03);

            EZDTBLAccessor.update(tvnd);
        }

        // SHIP_TO_CUST
        // cmpyPk, shipToCustCd
        // SHIP_TO_CUSTTMsgArray ships =
        // NMAL4500CommonLogic.db_shiptocust_search_nowait(this.getGlobalCompanyCode(),
        // cMsg.cmpyPk_01.getValue(), tvnd.vndCd.getValue());
        // if (ships.length() == 0) {
        // BigDecimal SHIPSeq =
        // ZYPOracleSeqAccessor.getNumberBigDecimal(SEQ_SHIP_TO_CUST);
        // SHIP_TO_CUSTTMsg ship =
        // NMAL4500CommonLogic.setCreateSHIPTOCUST(tptylocwrk,
        // this.getGlobalCompanyCode(), SHIPSeq,
        // cMsg.vndCd_01.getValue());
        // EZDTBLAccessor.create(ship);
        // } else {
        // SHIP_TO_CUSTTMsg ship = ships.no(ONE_ROW);
        // NMAL4500CommonLogic.setUpdateSHIPTOCUST(tptylocwrk, ship);
        // EZDTBLAccessor.update(ship);
        // }
    }

    private boolean checkAffiliation(NMAL4500CMsg cMsg) {
        // Check input
        if (!ZYPCommonFunc.hasValue(cMsg.coaAfflCd_01)) {
            return true;
        }
        // Check DB
        S21SsmEZDResult ssmResult = NMAL4500Query.getInstance().getAffiliation(cMsg, getGlobalCompanyCode());
        if (!ssmResult.isCodeNormal()) {
            cMsg.coaAfflCd_01.setErrorInfo(1, "NMAM0039E", new String[] {"Affiliation" });
            return false;
        }
        return true;
    }

    private boolean hasAddressInput(NMAL4500CMsg cMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.firstLineAddr_01) || ZYPCommonFunc.hasValue(cMsg.scdLineAddr_01) || ZYPCommonFunc.hasValue(cMsg.thirdLineAddr_01) || ZYPCommonFunc.hasValue(cMsg.frthLineAddr_01)
                || ZYPCommonFunc.hasValue(cMsg.ctryCd_03) || ZYPCommonFunc.hasValue(cMsg.postCd_01) || ZYPCommonFunc.hasValue(cMsg.ctyAddr_01) || ZYPCommonFunc.hasValue(cMsg.cntyPk_03) || ZYPCommonFunc.hasValue(cMsg.stCd_01)
                || ZYPCommonFunc.hasValue(cMsg.provNm_01)) {
            return true;
        }
        return false;
    }

    private boolean isOutboundCarrierVendor(NMAL4500CMsg cMsg) {
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (VND_TP.OUTBOUND_CARRIER.equals(cMsg.A.no(i).vndTpCd_A1.getValue())) {
                if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.A.no(i).xxChkBox_A1.getValue())) {
                    return true;
                }
            }
        }
        return false;
    }

    private WHTMsg getWHInfo(NMAL4500CMsg cMsg) {
        // get wh code and wh name
        WHTMsg whTMsg = new WHTMsg();
        ZYPEZDItemValueSetter.setValue(whTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(whTMsg.whPk, cMsg.whPk_03);
        whTMsg = (WHTMsg) EZDTBLAccessor.findByKey(whTMsg);
        if (whTMsg instanceof WHTMsg) {
            return whTMsg;
        }
        return null;
    }

    private boolean isValidatedVenderInfo(NMAL4500CMsg cMsg, String whVndCd, String whLocNm) {
        String inputVndCd = cMsg.vndCd_01.getValue();
        String inputLocNm = cMsg.locNm_01.getValue();
        if (!ZYPCommonFunc.hasValue(inputVndCd) || !ZYPCommonFunc.hasValue(inputLocNm)) {
            return false;
        }
        if (!inputVndCd.equals(whVndCd) || !inputLocNm.equals(whLocNm)) {
            return false;
        }
        return true;
    }

    private boolean validateDuplicateWHCdAndVndTp(NMAL4500CMsg cMsg, BigDecimal vndPk) {
        // check duplicate WH code and Vender Type.
        boolean result = true;
        List<Integer> checkedRows = ZYPTableUtil.getSelectedRows(cMsg.A, "xxChkBox_A1", ZYPConstant.CHKBOX_ON_Y);
        for (int checkedRow : checkedRows) {
            String vndTpCd = ((NMAL4500_ACMsg) cMsg.A.get(checkedRow)).vndTpCd_A1.getValue();
            if (ZYPCommonFunc.hasValue(vndTpCd)) {
                if (VND_TP.IN_HOUSE_KITTING.equals(vndTpCd) || VND_TP.IN_HOUSE_REFURBISH.equals(vndTpCd)) {
                    if (existsVenderByWHCdAndVndtpCd(cMsg.whCd_04.getValue(), vndTpCd, vndPk)) {
                        ((NMAL4500_ACMsg) cMsg.A.get(checkedRow)).xxChkBox_A1.setErrorInfo(1, "NMAM8071E", new String[] {vndTpCd, cMsg.whCd_04.getValue() });
                        result = false;
                    }
                }
            }
        }
        return result;
    }

    private boolean existsVenderByWHCdAndVndtpCd(String whCd, String vndTpCd, BigDecimal vndPk) {
        S21SsmEZDResult ssmResult = NMAL4500Query.getInstance().getVenderCountByWHCdAndVndtpCd(this.getGlobalCompanyCode(), whCd, vndTpCd, vndPk);
        if (ssmResult.isCodeNormal()) {
            BigDecimal vndCount = (BigDecimal) ssmResult.getResultObject();
            if (BigDecimal.ZERO.compareTo(vndCount) < 0) {
                return true;
            }
        }
        return false;
    }
}
