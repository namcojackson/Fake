/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NMAL4500;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL4500.common.NMAL4500CommonLogic;
import business.blap.NMAL4500.constant.NMAL4500Constant;
import business.db.CHRG_RATE_VND_GRPTMsg;
import business.db.CHRG_RATE_VND_GRPTMsgArray;
import business.db.CMPYTMsg;
import business.db.CNTYTMsg;
import business.db.COA_AFFLTMsgArray;
import business.db.CTRYTMsgArray;
import business.db.FRT_CHRG_METHTMsg;
import business.db.FRT_CHRG_METHTMsgArray;
import business.db.FRT_CHRG_TOTMsg;
import business.db.FRT_CHRG_TOTMsgArray;
import business.db.INV_RCV_METH_TPTMsgArray;
import business.db.PTY_LOC_WRKTMsg;
import business.db.SHPG_SVC_LVLTMsg;
import business.db.SHPG_SVC_LVLTMsgArray;
import business.db.TRSMT_METH_TPTMsg;
import business.db.TRSMT_METH_TPTMsgArray;
import business.db.VNDTMsg;
import business.db.VNDTMsgArray;
import business.db.VND_TP_RELNTMsgArray;
import business.db.WHTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/09/01   SRA             T.Chijimatsu    Create          N/A
 * 2010/04/05   Fujitsu         T.ishii         Update          5206
 * 2013/05/08   Fujitsu         F.Saito         Update          WDS#R-MS001
 * 2013/08/05   Fujitsu         N.Sugiura       Update          QC1469
 * 2013/09/19   Fujitsu         N.Sugiura       Update          MEX-LC004
 * 2013/10/21   Fujitsu         D.Yanagisawa    Update          MEX-LC001
 * 2016/07/29   CITS            S.Endo          Update          QC#
 *</pre>
 */
public class NMAL4500BL02 extends S21BusinessHandler implements NMAL4500Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NMAL4500_INIT".equals(screenAplID)) {
                doProcess_NMAL4500_INIT((NMAL4500CMsg) cMsg, (NMAL4500SMsg) sMsg);
            } else if ("NMAL4500Scrn00_Search_Vendor_CD".equals(screenAplID)) {
                doProcess_NMAL4500Scrn00_Search_Vendor_CD((NMAL4500CMsg) cMsg, (NMAL4500SMsg) sMsg);
            // DEL START 2013/08/05 QC1469
            // } else if ("NMAL4500Scrn00_Get_Address".equals(screenAplID)) {
            //     doProcess_NMAL4500Scrn00_Get_Address((NMAL4500CMsg) cMsg, (NMAL4500SMsg) sMsg);
            // DEL END 2013/08/05 QC1469
            } else if ("NMAL4500Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NMAL4500_INIT((NMAL4500CMsg) cMsg, (NMAL4500SMsg) sMsg);
            } else if ("NMAL4500Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL4500_INIT((NMAL4500CMsg) cMsg, (NMAL4500SMsg) sMsg);
            } else if ("NMAL4500Scrn00_GetAddress".equals(screenAplID)) {
                doProcess_NMAL4500_GetAddress((NMAL4500CMsg) cMsg, (NMAL4500SMsg) sMsg);
            } else if ("NMAL4500_NWAL1130".equals(screenAplID)) {
                doProcess_NMAL4500_NWAL1130((NMAL4500CMsg) cMsg, (NMAL4500SMsg) sMsg);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }


    @SuppressWarnings("unchecked")
    private void doProcess_NMAL4500_INIT(NMAL4500CMsg cMsg, NMAL4500SMsg sMsg) {

        //get CTRY
        CTRYTMsgArray ctryR = NMAL4500CommonLogic.db_ctry_init(this.getGlobalCompanyCode());
        if (ctryR.length() == 0) {
            cMsg.setMessageInfo("NMAM0011E", new String[]{"base"});
            return;
        }
        for (int i = 0; i < ctryR.length(); i++) {
            if (i < cMsg.ctryCd_01.length()) {
                cMsg.ctryCd_01.no(i).setValue(ctryR.no(i).ctryCd.getValue());
                cMsg.ctryNm_02.no(i).setValue(ctryR.no(i).ctryNm.getValue());
            }
        }

        //get SHPG_SVC_LVL
        SHPG_SVC_LVLTMsgArray shpg_svc_lvlR = NMAL4500CommonLogic.db_shpg_svc_lvl_init(this.getGlobalCompanyCode());
        if (shpg_svc_lvlR.length() == 0) {
            cMsg.setMessageInfo("NMAM0011E", new String[]{"base"});
            return;
        }
        for (int i = 0; i < shpg_svc_lvlR.length(); i++) {
            if (i < cMsg.shpgSvcLvlCd_01.length()) {
                cMsg.shpgSvcLvlCd_01.no(i).setValue(shpg_svc_lvlR.no(i).shpgSvcLvlCd.getValue());
                cMsg.shpgSvcLvlNm_02.no(i).setValue(shpg_svc_lvlR.no(i).shpgSvcLvlNm.getValue());
            }
        }

        //get FRT_CHRG_TO
        FRT_CHRG_TOTMsgArray frt_chrg_toR = NMAL4500CommonLogic.db_frt_chrg_to_init(this.getGlobalCompanyCode());
        if (frt_chrg_toR.length() == 0) {
            cMsg.setMessageInfo("NMAM0011E", new String[]{"base"});
            return;
        }
        for (int i = 0; i < frt_chrg_toR.length(); i++) {
            if (i < cMsg.frtChrgToCd_01.length()) {

                cMsg.frtChrgToCd_01.no(i).setValue(frt_chrg_toR.no(i).frtChrgToCd.getValue());
                cMsg.frtChrgToNm_02.no(i).setValue(frt_chrg_toR.no(i).frtChrgToNm.getValue());
            }
        }

        //get FRT_CHRG_METH
        FRT_CHRG_METHTMsgArray frt_chrg_methR = NMAL4500CommonLogic.db_frt_chrg_meth_init(this.getGlobalCompanyCode());
        if (frt_chrg_methR.length() == 0) {
            cMsg.setMessageInfo("NMAM0011E", new String[]{"base"});
            return;
        }
        for (int i = 0; i < frt_chrg_methR.length(); i++) {
            if (i < cMsg.frtChrgMethCd_01.length()) {

                cMsg.frtChrgMethCd_01.no(i).setValue(frt_chrg_methR.no(i).frtChrgMethCd.getValue());
                cMsg.frtChrgMethNm_02.no(i).setValue(frt_chrg_methR.no(i).frtChrgMethNm.getValue());
            }
        }

        //get TRSMT_METH_TP
        TRSMT_METH_TPTMsgArray trsmt_meth_tpR = NMAL4500CommonLogic.db_trsmt_meth_tp_init(this.getGlobalCompanyCode());
        if (trsmt_meth_tpR.length() == 0) {
            cMsg.setMessageInfo("NMAM0011E", new String[]{"base"});
            return;
        }
        for (int i = 0; i < trsmt_meth_tpR.length(); i++) {
            if (i < cMsg.trsmtMethTpCd_01.length()) {

                cMsg.trsmtMethTpCd_01.no(i).setValue(trsmt_meth_tpR.no(i).trsmtMethTpCd.getValue());
                cMsg.trsmtMethTpNm_02.no(i).setValue(trsmt_meth_tpR.no(i).trsmtMethTpNm.getValue());
            }
        }

        //get INV_RCV_METH_TP
        INV_RCV_METH_TPTMsgArray invRcvMethTp = NMAL4500CommonLogic.db_inv_rcv_meth_tp_init(this.getGlobalCompanyCode());
        if (trsmt_meth_tpR.length() == 0) {
            cMsg.setMessageInfo("NMAM0011E", new String[]{"base"});
            return;
        }

        for (int i = 0; i < invRcvMethTp.length(); i++) {
            if (i < cMsg.invRcvMethTpCd_01.length()) {
                cMsg.invRcvMethTpCd_01.no(i).setValue(invRcvMethTp.no(i).invRcvMethTpCd.getValue());
                cMsg.invRcvMethTpNm_02.no(i).setValue(invRcvMethTp.no(i).invRcvMethTpNm.getValue());
            }
        }

        //get CHRG_RATE_VND_GRP_CD
        CHRG_RATE_VND_GRPTMsgArray chrgRateVndGrpR = NMAL4500CommonLogic.db_chrg_rate_vnd_grp_init(this.getGlobalCompanyCode());
        for (int i = 0; i < chrgRateVndGrpR.length(); i++) {
            if (i < cMsg.chrgRateVndGrpCd_01.length()) {
                cMsg.chrgRateVndGrpCd_01.no(i).setValue(chrgRateVndGrpR.no(i).chrgRateVndGrpCd.getValue());
                cMsg.chrgRateVndGrpCd_02.no(i).setValue(chrgRateVndGrpR.no(i).chrgRateVndGrpCd.getValue());
            }
        }

        //get WH_CD <defect#5206 T.Ishii 20100405>
        WHTMsgArray wHTMsgArray = NMAL4500CommonLogic.db_wh_init(this.getGlobalCompanyCode());
        for (int i = 0; i < wHTMsgArray.length(); i++) {
            if (i < cMsg.whPk_01.length()) {
                ZYPEZDItemValueSetter.setValue(cMsg.whPk_01.no(i), wHTMsgArray.no(i).whPk);
                ZYPEZDItemValueSetter.setValue(cMsg.whCd_02.no(i), wHTMsgArray.no(i).whCd);
            }
        }

        //get VND_TP
        // 10/22/2015 mod start
//        VND_TPTMsgArray vnd_tpR = new VND_TPTMsgArray();
//        vnd_tpR = NMAL4500CommonLogic.db_vnd_tp_init(this.getGlobalCompanyCode());
//        if (vnd_tpR.length() == 0) {
//            cMsg.setMessageInfo("NMAM0011E", new String[]{"base"});
//            return;
//        }
//        for (int i = 0; i < vnd_tpR.length(); i++) {
//            if (i < cMsg.A.length()) {
//                cMsg.A.no(i).vndTpCd_A1.setValue(vnd_tpR.no(i).vndTpCd.getValue());
//                cMsg.A.no(i).vndTpNm_A1.setValue(vnd_tpR.no(i).vndTpNm.getValue());
//            }
//        }
        List<Map> vndTpList = NMAL4500CommonLogic.db_vnd_tp_init(this.getGlobalCompanyCode());
        for (int i = 0; i < vndTpList.size(); i++) {
            Map map = (Map) vndTpList.get(i);
            if (i < cMsg.A.length()) {
                cMsg.A.no(i).vndTpCd_A1.setValue((String) map.get("VND_TP_CD"));
                cMsg.A.no(i).vndTpNm_A1.setValue((String) map.get("VND_TP_NM"));
            }
        }
        // 10/22/2015 mod end

        // 10/21/2015 add start
        //get CARR_TP
        List<Map> carrTpList = NMAL4500CommonLogic.db_carr_tp_init(this.getGlobalCompanyCode());
        for (int i = 0; i < carrTpList.size(); i++) {
            Map map = (Map) carrTpList.get(i);
            if (i < cMsg.carrTpCd_01.length()) {
                ZYPEZDItemValueSetter.setValue(cMsg.carrTpCd_01.no(i),(String) map.get("CARR_TP_CD"));
                ZYPEZDItemValueSetter.setValue(cMsg.carrTpDescTxt_02.no(i),(String) map.get("CARR_TP_DESC_TXT"));
            }
        }
        // 10/21/2015 add end

        //set screen minimum put loc table row(10)
        if (MIN_LOC_ROW > vndTpList.size()) {
            for (int i = vndTpList.size(); MIN_LOC_ROW < i; i++) {
                cMsg.A.no(i).xxChkBox_A1.setValue(CHECK_BOX_OFF);
                cMsg.A.no(i).vndTpCd_A1.clear();
                cMsg.A.no(i).vndTpNm_A1.clear();
            }
            cMsg.A.setValidCount(MIN_LOC_ROW);
        }
        else {
            cMsg.A.setValidCount(vndTpList.size());
        }


    }

    // DEL START 2013/08/05 QC1469
    // private void doProcess_NMAL4500Scrn00_Get_Address(NMAL4500CMsg cMsg, NMAL4500SMsg sMsg) {
    // 
    //     // check Country
    //     if (!LIST_COUNTRY_USA.equals(cMsg.ctryCd_03.getValue())) {
    //         cMsg.setMessageInfo("NMAM0147I");
    //         return;
    //     }
    // 
    //     // check post code
    //     if (!ZYPCommonFunc.hasValue(cMsg.postCd_01)) {
    //         cMsg.postCd_01.setErrorInfo(1, "NMAM0041E", new String[]{"PostCode"});
    //         cMsg.setMessageInfo("NMAM0041E", new String[]{"PostCode"});
    //         return;
    //     }
    // 
    //     // QC :5515
    //     String postCd = cMsg.postCd_01.getValue();
    // 
    //     POSTTMsg postC = new POSTTMsg();
    //     postC.setSQLID("006");
    //     postC.setConditionValue("glblCmpyCd01", this.getGlobalCompanyCode());
    //     postC.setConditionValue("postCd01", postCd);
    //      POSTTMsgArray xxpost = (POSTTMsgArray) EZDTBLAccessor.findByCondition(postC);
    //     if (xxpost.length() == 0 && ZYPCommonFunc.hasValue(postCd) && postCd.length() > 5) {
    //         postC.setConditionValue("postCd01", postCd.substring(0, 5));
    //         xxpost = (POSTTMsgArray) EZDTBLAccessor.findByCondition(postC);
    //     }
    // 
    //     // Post check
    //     if (xxpost.length() == 0) {
    //         cMsg.postCd_01.setErrorInfo(1, "NMAM0039E", new String[]{"PostalCode"});
    //         return;
    //     }
    // 
    //     // set city
    //     if (xxpost.length() == 1) {
    //         cMsg.ctyAddr_01.setValue(xxpost.no(ONE_ROW).ctyAddr.getValue());
    //         }
    //     postCd = xxpost.no(0).postCd.getValue();
    //     // set state
    //     S21SsmEZDResult ssmResult = NMAL4500Query.getInstance().get_ST_CD(cMsg, sMsg, this.getGlobalCompanyCode(), postCd);
    //     if (ssmResult.isCodeNormal()) {
    //         if (ssmResult.getQueryResultCount() == 1) {
    //             cMsg.stCd_01.setValue(sMsg.B.no(ONE_ROW).stCd_B1.getValue());
    //         }
    //     }
    // 
    //     // set county
    //     S21SsmEZDResult ssmResult2 = NMAL4500Query.getInstance().get_CNTY(cMsg, sMsg, this.getGlobalCompanyCode(), postCd);
    //     if (ssmResult2.isCodeNormal()) {
    //         cMsg.cntyPk_01.clear();
    //         cMsg.cntyNm_02.clear();
    //         for (int i = 0; i < ssmResult2.getQueryResultCount(); i++) {
    //             cMsg.cntyPk_01.no(i).setValue(sMsg.C.no(i).cntyPk_C1.getValue());
    //             cMsg.cntyNm_02.no(i).setValue(sMsg.C.no(i).cntyNm_C1.getValue());
    //             // QC361875 20111005
    //                 if ( i == 0 ){
    //                    cMsg.cntyPk_03.setValue(sMsg.C.no(i).cntyPk_C1.getValue());
    //                }
    //         }
    //      }
    // 
    // }
    // DEL END 2013/08/05 QC1469

    private void doProcess_NMAL4500Scrn00_Search_Vendor_CD(NMAL4500CMsg cMsg, NMAL4500SMsg sMsg) {

        //check  VND_CD
        if (!ZYPCommonFunc.hasValue(cMsg.vndCd_01)) {
            cMsg.vndCd_01.setErrorInfo(1, "NMAM0041E", new String[]{"VendorCode"});
            return;
        }
        VNDTMsgArray vndR = NMAL4500CommonLogic.db_vnd_search(cMsg, this.getGlobalCompanyCode());
        if (vndR.length() == 0) {
            if (NMAL4500CommonLogic.isDuplicateInventoryLocation(getGlobalCompanyCode(), cMsg.vndCd_01.getValue())) {
                cMsg.vndCd_01.setErrorInfo(1, "NMAM0071E", new String[]{"VendorCode"});
            } else {

                COA_AFFLTMsgArray coaAffl = NMAL4500CommonLogic.db_affiliation_search(this.getGlobalCompanyCode(), AFFILIATION_NUM);
                if (coaAffl.length() > 0) {
                    if (ZYPCommonFunc.hasValue(coaAffl.no(0).coaAfflCd)) {
                        cMsg.coaAfflCd_01.setValue(coaAffl.no(0).coaAfflCd.getValue());
                    } else {
                        cMsg.coaAfflCd_01.clear();
                    }
                } else {
                    cMsg.coaAfflCd_01.clear();
                }

                cMsg.setMessageInfo("NMAM0150I", new String[]{"VendorCD"});
            }
            return;
        }
        cMsg.ptyLocPk_01.setValue(vndR.no(ONE_ROW).ptyLocPk.getValue());
        cMsg.taxPayerId_01.setValue(vndR.no(ONE_ROW).taxPayerId.getValue());
        cMsg.coaAfflCd_01.setValue(vndR.no(ONE_ROW).coaAfflCd.getValue());
        cMsg.intlVndFlg_01.setValue(vndR.no(ONE_ROW).intlVndFlg.getValue());
        cMsg.payeeFlg_01.setValue(vndR.no(ONE_ROW).payeeFlg.getValue());
        cMsg.asnReqFlg_01.setValue(vndR.no(ONE_ROW).asnReqFlg.getValue());
        cMsg.thirdPtyVndFlg_01.setValue(vndR.no(ONE_ROW).thirdPtyVndFlg.getValue());
        cMsg.ezUpTime_01.setValue(vndR.no(ONE_ROW).ezUpTime.getValue());
        cMsg.ezUpTimeZone_01.setValue(vndR.no(ONE_ROW).ezUpTimeZone.getValue());
        // add warehouse code <defect#5206 T.ishii 20100405>
        BigDecimal whPk = null;
        String whCd = vndR.no(ONE_ROW).whCd.getValue();
        if (ZYPCommonFunc.hasValue(whCd)) {
            for (int i = 0; i < cMsg.whPk_01.length(); i++) {
                if (i < cMsg.whCd_02.length()) {
                    if (whCd.equals(cMsg.whCd_02.no(i).getValue())) {
                        whPk = cMsg.whPk_01.no(i).getValue();
                        break;
                    }
                }
            }
        }
        ZYPEZDItemValueSetter.setValue(cMsg.whPk_03, whPk);
        // add attention name code <defect#5206 T.ishii 20100405>
        ZYPEZDItemValueSetter.setValue(cMsg.attnNm_01, vndR.no(ONE_ROW).attnNm);

        // ADD START 2013/05/08 WDS#R-MS001
        VNDTMsg dsVnd = NMAL4500CommonLogic.getDsVnd(getGlobalCompanyCode(), vndR.no(ONE_ROW).vndPk.getValue());
        if (dsVnd != null) {
            ZYPEZDItemValueSetter.setValue(cMsg.sendPoEmlAddr_01, dsVnd.sendPoEmlAddr);
            // ADD START 2013/09/19 MEX-LC004
            ZYPEZDItemValueSetter.setValue(cMsg.dealCcyCd_01, dsVnd.dealCcyCd);
            // ADD END 2013/09/19 MEX-LC004

            // ADD START 2013/10/21 MEX-LC001
            if (ZYPCommonFunc.hasValue(dsVnd.invVndCd)) {
                ZYPEZDItemValueSetter.setValue(cMsg.invVndCd_01, dsVnd.invVndCd);
                VNDTMsgArray vndI = NMAL4500CommonLogic.db_inv_vnd_search(this.getGlobalCompanyCode(), cMsg.invVndCd_01.getValue());
                if (vndI != null && vndI.length() > 0) {
                    VNDTMsg vndITMsg = vndI.no(0);
                    ZYPEZDItemValueSetter.setValue(cMsg.locNm_02, vndITMsg.locNm);
                } else {
                    cMsg.vndCd_01.setErrorInfo(1, "NMAM0039E", new String[]{"InvoiceVendorCode"});
                    return;
                }
            }
            // ADD E N D 2013/10/21 MEX-LC001

            ZYPEZDItemValueSetter.setValue(cMsg.carrTpCd_03, dsVnd.carrTpCd); // 10/21/2015 add
        }
        // ADD END   2013/05/08 WDS#R-MS001

        //serch VND data set
        VNDTMsg vndtmsg = new VNDTMsg();
        EZDMsg.copy(vndR.no(ONE_ROW), null, vndtmsg, null);

        //CMPY
        CMPYTMsg cmpyR = NMAL4500CommonLogic.db_cmpy_search(cMsg, this.getGlobalCompanyCode(), vndR.no(ONE_ROW).cmpyPk.getValue());
        if (cmpyR != null) {
            cMsg.cmpyPk_01.setValue(cmpyR.cmpyPk.getValue());
            cMsg.ezUpTime_02.setValue(cmpyR.ezUpTime.getValue());
            cMsg.ezUpTimeZone_02.setValue(cmpyR.ezUpTimeZone.getValue());
        }

        //get PTY_LOC_WRK
        PTY_LOC_WRKTMsg ptylocwrkR = NMAL4500CommonLogic.db_ptylocwrk_search(this.getGlobalCompanyCode(), vndtmsg);
        if (ptylocwrkR != null) {
            cMsg.locNm_01.setValue(ptylocwrkR.locNm.getValue());
            cMsg.dbaNm_01.setValue(ptylocwrkR.dbaNm.getValue());
            cMsg.firstLineAddr_01.setValue(ptylocwrkR.firstLineAddr.getValue());
            cMsg.scdLineAddr_01.setValue(ptylocwrkR.scdLineAddr.getValue());
            cMsg.thirdLineAddr_01.setValue(ptylocwrkR.thirdLineAddr.getValue());
            cMsg.frthLineAddr_01.setValue(ptylocwrkR.frthLineAddr.getValue());
            cMsg.postCd_01.setValue(ptylocwrkR.postCd.getValue());
            cMsg.ctyAddr_01.setValue(ptylocwrkR.ctyAddr.getValue());
            cMsg.stCd_01.setValue(ptylocwrkR.stCd.getValue());
            cMsg.provNm_01.setValue(ptylocwrkR.provNm.getValue());
            cMsg.firstRefCmntTxt_01.setValue(ptylocwrkR.firstRefCmntTxt.getValue());
            cMsg.scdRefCmntTxt_01.setValue(ptylocwrkR.scdRefCmntTxt.getValue());
            cMsg.telNum_01.setValue(ptylocwrkR.telNum.getValue());
            cMsg.faxNum_01.setValue(ptylocwrkR.faxNum.getValue());
            cMsg.ctryCd_03.setValue(ptylocwrkR.ctryCd.getValue());
            cMsg.ezUpTime_03.setValue(ptylocwrkR.ezUpTime.getValue());
            cMsg.ezUpTimeZone_03.setValue(ptylocwrkR.ezUpTimeZone.getValue());
            /* ADD : No.382 of Quality Center <10/02/09 Noda> */
            // (2009/10/19 Y.Chen) Trac No.457 Add-Beg
            // Init county list
            cMsg.cntyPk_01.clear();
            cMsg.cntyNm_02.clear();
            cMsg.cntyPk_03.clear();
            // (2009/10/19 Y.Chen) Trac No.457 Add-End
            // get cnty Pk & Nm
            CNTYTMsg cntytmsg = new CNTYTMsg();
            cntytmsg.glblCmpyCd.setValue(this.getGlobalCompanyCode());
            cntytmsg.cntyPk.setValue(ptylocwrkR.cntyPk.getValue());
            cntytmsg = (CNTYTMsg) EZDTBLAccessor.findByKey(cntytmsg);
            if (cntytmsg != null) {
                cMsg.cntyPk_01.no(ONE_ROW).setValue(cntytmsg.cntyPk.getValue());
                cMsg.cntyNm_02.no(ONE_ROW).setValue(cntytmsg.cntyNm.getValue());
                cMsg.cntyPk_03.setValue(cntytmsg.cntyPk.getValue());
            }
        }

        //get SHPG_SVC_LVL
        SHPG_SVC_LVLTMsg shpgsvclvlR = NMAL4500CommonLogic.db_shpgsvclvl_search(this.getGlobalCompanyCode(), vndtmsg);
        if (shpgsvclvlR != null) {
            cMsg.shpgSvcLvlCd_03.setValue(shpgsvclvlR.shpgSvcLvlCd.getValue());
        }

        ///get FRT_CHRG_TOTM
        FRT_CHRG_TOTMsg frtchrgtoR = NMAL4500CommonLogic.db_frtchrgto_search(this.getGlobalCompanyCode(), vndtmsg);
        if (frtchrgtoR != null) {
            cMsg.frtChrgToCd_03.setValue(frtchrgtoR.frtChrgToCd.getValue());
            }

        ///get FRT_CHRG_METH
        FRT_CHRG_METHTMsg frtchrgmethR = NMAL4500CommonLogic.db_frtchrgmeth_search(this.getGlobalCompanyCode(), vndtmsg);
        if (frtchrgmethR != null) {
            cMsg.frtChrgMethCd_03.setValue(frtchrgmethR.frtChrgMethCd.getValue());
        }

        ///get TRSMT_METH_TP
        TRSMT_METH_TPTMsg trsmtmethtpR = NMAL4500CommonLogic.db_rtsmtmethtp_search(this.getGlobalCompanyCode(), vndtmsg);
        if (trsmtmethtpR != null) {
            cMsg.trsmtMethTpCd_03.setValue(trsmtmethtpR.trsmtMethTpCd.getValue());
        }

        ///get CHRG_RATE_VND_GRP
        CHRG_RATE_VND_GRPTMsg chrgratevndgrpR = NMAL4500CommonLogic.db_chrgratevndgrp_search(this.getGlobalCompanyCode(), vndtmsg);
        if (chrgratevndgrpR != null) {
            cMsg.chrgRateVndGrpCd_03.setValue(chrgratevndgrpR.chrgRateVndGrpCd.getValue());
        }

        //get  vendor type
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            cMsg.A.no(i).xxChkBox_A1.setValue(CHECK_BOX_OFF);
        }
        VND_TP_RELNTMsgArray vndtprelnR = NMAL4500CommonLogic.db_vndtpreln_search(cMsg, this.getGlobalCompanyCode());
        for (int i = 0; i < vndtprelnR.length(); i++) {
            //S Check
            for (int i2 = 0; i2 < cMsg.A.length(); i2++) {
                if (ZYPCommonFunc.hasValue(cMsg.A.no(i2).vndTpCd_A1)) {
                    if (vndtprelnR.no(i).vndTpCd.getValue().equals(cMsg.A.no(i2).vndTpCd_A1.getValue())) {
                        cMsg.A.no(i2).xxChkBox_A1.setValue(CHECK_BOX_ON);
                        cMsg.A.no(i2).ezUpTime_A1.setValue(vndtprelnR.no(i).ezUpTime.getValue());
                        cMsg.A.no(i2).ezUpTimeZone_A1.setValue(vndtprelnR.no(i).ezUpTimeZone.getValue());
                        break;
                    }
                }
            }
        }

        cMsg.invRcvMethTpCd_03.setValue(vndtmsg.invRcvMethTpCd.getValue());
    }
    
    private void doProcess_NMAL4500_GetAddress(NMAL4500CMsg cMsg, NMAL4500SMsg sMsg) {
        List<Map<String, Object>> list = null;
        String postCd = cMsg.postCd_01.getValue();

        S21SsmEZDResult res = NMAL4500Query.getInstance().getAddrByPost(getGlobalCompanyCode(), postCd);

        if (res.isCodeNormal()) {
            list = (List<Map<String, Object>>) res.getResultObject();
        } else {
            // Post Code xxxxx-yyyy => use xxxxx.
            if (postCd.length() > 5) {
                res = NMAL4500Query.getInstance().getAddrByPost(getGlobalCompanyCode(), postCd.substring(0, 5));
                if (res.isCodeNormal()) {
                    list = (List<Map<String, Object>>) res.getResultObject();
                }
            }
        }

        if (list == null) {
            cMsg.postCd_01.setErrorInfo(1, NMAM0039E, new String[] {"Ship to Location Postal Code" });
        } else {
            List<String> listCtyAddr = getDistinctValueList(list, "CTY_ADDR");
            List<String> listStCd = getDistinctValueList(list, "ST_CD");
            List<String> listCntyNm = getDistinctValueList(list, "CNTY_NM");
            List<BigDecimal> listCntyPk = getDistinctDecimalValueList(list, "CNTY_PK");

            if (listCtyAddr.size() == 1) {
                ZYPEZDItemValueSetter.setValue(cMsg.ctyAddr_01, listCtyAddr.get(0));
            }
            if (listStCd.size() == 1) {
                ZYPEZDItemValueSetter.setValue(cMsg.stCd_01, listStCd.get(0));
            }
            if (listCntyNm.size() == 1) {
                ZYPEZDItemValueSetter.setValue((EZDCStringItem)cMsg.cntyNm_02.get(0), listCntyNm.get(0));
            }
            if (listCntyPk.size() == 1) {
                ZYPEZDItemValueSetter.setValue(cMsg.cntyPk_03, listCntyPk.get(0));
                ZYPEZDItemValueSetter.setValue(cMsg.cntyPk_01.no(0), listCntyPk.get(0));
            }
        }
        
    }
    /**
     * getDistinctValueList
     * @param list List<Map<String, Object>>
     * @param colNm String
     * @return listDistinct List<String>
     */
    private List<String> getDistinctValueList(List<Map<String, Object>> list, String colNm) {
        List<String> listDistinct = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            String value = (String) map.get(colNm);
            if (ZYPCommonFunc.hasValue(value)) {
                if (!listDistinct.contains(value)) {
                    listDistinct.add(value);
                }
            }
        }
        return listDistinct;
    }
    /**
     * getDistinctValueList
     * @param list List<Map<String, Object>>
     * @param colNm String
     * @return listDistinct List<String>
     */
    private List<BigDecimal> getDistinctDecimalValueList(List<Map<String, Object>> list, String colNm) {
        List<BigDecimal> listDistinct = new ArrayList<BigDecimal>();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            BigDecimal value = (BigDecimal) map.get(colNm);
            if (ZYPCommonFunc.hasValue(value)) {
                if (!listDistinct.contains(value)) {
                    listDistinct.add(value);
                }
            }
        }
        return listDistinct;
    }
    /**
     * Return from NWAL1130(common popup. supplier, state, country)
     * @param cMsg NPAL1500CMsg
     * @param sMsg NPAL1500SMsg
     */
    private void doProcess_NMAL4500_NWAL1130(NMAL4500CMsg cMsg, NMAL4500SMsg sMsg) {
        S21SsmEZDResult ssmResult = NMAL4500Query.getInstance().getAddrInfo(cMsg, getGlobalCompanyCode());
        if (ssmResult.isCodeNormal()) {
            BigDecimal result = (BigDecimal) ssmResult.getResultObject();
            if(result != null) {
                ZYPEZDItemValueSetter.setValue(cMsg.cntyPk_03, result);
            }
        }
    }


}
