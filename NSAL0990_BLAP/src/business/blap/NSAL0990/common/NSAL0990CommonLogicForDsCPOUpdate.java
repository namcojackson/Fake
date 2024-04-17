/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0990.common;

import static business.blap.NSAL0990.constant.NSAL0990Constant.ATTACH_BUSINESS_ID;
import static business.blap.NSAL0990.constant.NSAL0990Constant.ATTACH_BUSINESS_NM;
import static business.blap.NSAL0990.constant.NSAL0990Constant.BUSINESS_ID;
import static business.blap.NSAL0990.constant.NSAL0990Constant.DELY_CMNT_END_IDX;
import static business.blap.NSAL0990.constant.NSAL0990Constant.DELY_CMNT_START_IDX;
import static business.blap.NSAL0990.constant.NSAL0990Constant.DS_ACPO_NUM_SEQ;
import static business.blap.NSAL0990.constant.NSAL0990Constant.NSAM0032E;
import static business.blap.NSAL0990.constant.NSAL0990Constant.NSAM0440E;
import static business.blap.NSAL0990.constant.NSAL0990Constant.NWZM2023E;
import static business.blap.NSAL0990.constant.NSAL0990Constant.NWZM2024E;
import static business.blap.NSAL0990.constant.NSAL0990Constant.NWZM2025E;
import static business.blap.NSAL0990.constant.NSAL0990Constant.PKG_UOM_FOR_PRC;
import static business.blap.NSAL0990.constant.NSAL0990Constant.ZZM9037E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0990.NSAL0990CMsg;
import business.blap.NSAL0990.NSAL0990Query;
import business.blap.NSAL0990.NSAL0990QueryForDsCPOUpdate;
import business.blap.NSAL0990.NSAL0990_ECMsg;
import business.blap.NSAL0990.constant.NSAL0990Constant;
import business.db.CTRYTMsg;
import business.db.DS_ACPOTMsg;
import business.db.DS_ACPO_TRGT_DTLTMsg;
import business.db.DS_MDLTMsg;
import business.db.DS_ORD_TPTMsg;
import business.db.FRT_CONDTMsg;
import business.db.MDSETMsg;
import business.db.MDSE_STORE_PKGTMsg;
import business.db.MSG_TXT_DTLTMsg;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SVC_CONFIG_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.parts.NWZC150001PMsg;
import business.parts.NWZC150001_APMsg;
import business.parts.NWZC150001_cpoConfigPMsg;
import business.parts.NWZC150001_cpoCtacPsnInfoListPMsg;
import business.parts.NWZC150001_cpoDlvyInfoListPMsg;
import business.parts.NWZC150001_priceListPMsg;
import business.parts.NWZC150002PMsg;
import business.parts.NWZC150003PMsg;
import business.parts.NWZC157001PMsg;
import business.parts.NWZC157001_xxPrcListPMsg;
import business.parts.NWZC157002PMsg;
import business.parts.NWZC157003PMsg;
import business.parts.NWZC157004PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC150001.NWZC150001;
import com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC157001.NWZC157001;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001SalesRep;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_CUST_REF_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_FUFL_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TXT_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileUpDown;
import com.canon.cusa.s21.framework.ZYP.file.ZYPFileUpDownParameter;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPNumbering;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;

/**
 *<pre>
 * Supply Order
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/06   Hitachi         K.Kasai         Create          N/A
 * 2016/03/14   Hitachi         A.Kohinata      Update          QC#5375
 * 2016/03/16   Hitachi         A.Kohinata      Update          QC#5530
 * 2016/03/16   Hitachi         A.Kohinata      Update          QC#5540
 * 2016/03/17   Hitachi         A.Kohinata      Update          QC#5632
 * 2016/03/23   Hitachi         A.Kohinata      Update          QC#5730
 * 2016/03/31   Hitachi         K.Kasai         Update          QC#6344
 * 2016/04/04   Hitachi         K.Kasai         Update          QC#6490
 * 2016/06/27   Hitachi         K.Kasai         Update          QC#9888
 * 2016/07/12   Hitachi         K.Kasai         Update          QC#9884
 * 2016/08/22   Hitachi         T.Tomita        Update          QC#13606
 * 2016/10/04   Hitachi         A.Kohinata      Update          QC#12898
 * 2016/10/20   Hitachi         A.Kohinata      Update          QC#15323
 * 2017/08/02   Hitachi         K.Kim           Update          QC#18311
 * 2017/10/18   Hitachi         M.Kidokoro      Update          QC#20246
 * 2018/01/09   Hitachi         K.Kojima        Update          QC#21855
 * 2018/04/11   Hitachi         K.Kitachi       Update          QC#11642
 * 2018/06/01   Fujitsu         M.Ohno          Update          QC#26273
 * 2018/06/12   Fujitsu         M.Ohno          Update          QC#26488
 * 2018/08/24   CITS            T.Kikuhara      Update          QC#27746
 * 2018/09/19   Fujitsu         Mz.Takahashi    Update          QC#9700
 * 2018/09/25   Hitachi         K.Kitachi       Update          QC#26260
 * 2018/10/29   Fujitsu         M.Yamada        Update          QC#28953
 * 2018/11/07   Hitachi         K.Kitachi       Update          QC#27387
 * 2019/03/04   Fujitsu         K.Ishizuka      Update          QC#30557
 * 2019/05/29   Fujitsu         R.Nakamura      Update          QC#50405
 * 2019/11/22   Hitachi         Y.Takeno        Update          QC#53125
 *</pre>
 */
public final class NSAL0990CommonLogicForDsCPOUpdate {

    /**
     * Singleton instance.
     */
    private static final NSAL0990CommonLogicForDsCPOUpdate MY_INSTANCE = new NSAL0990CommonLogicForDsCPOUpdate();

    /** Query Interface: NSAL0990QueryForDsCPOUpdate */
    private NSAL0990QueryForDsCPOUpdate queryForDsCPOUpdate = null;

    /** Query Interface: NSAL0990Query */
    private NSAL0990Query query = null;

    /**
     * Constructor.
     */
    private NSAL0990CommonLogicForDsCPOUpdate() {
        this.queryForDsCPOUpdate = NSAL0990QueryForDsCPOUpdate.getInstance();
        this.query = NSAL0990Query.getInstance();
    }

    /**
     * Singleton instance getter.
     * @return NSAL0990Query
     */
    public static NSAL0990CommonLogicForDsCPOUpdate getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Call NWZC1500 DS CPO Update API
     * @param cMsg NSAL0990CMsg
     * @param prcApiPMsg NWZC157001PMsg
     * @return NWZC150001PMsg
     */
    public NWZC150001PMsg callDsCPOUpdate(NSAL0990CMsg cMsg, NWZC157001PMsg prcApiPMsg) {

        NWZC150001PMsg cpoUpdApiReqPMsg = new NWZC150001PMsg();

        // Header
        setCpoUpdApiReqPMsg(cpoUpdApiReqPMsg, cMsg, prcApiPMsg);

        BigDecimal svcMachMstrPk = BigDecimal.ZERO;
        int dsOrdPosnNum = 0;
        int dsCpoLineNum = 0;
        for (int i = 0; i < cMsg.E.getValidCount(); i++) {
            final NSAL0990_ECMsg lineMsg = cMsg.E.no(i);

            // Line Config: Config
            // START 2019/11/22 [QC#53125, ADD]
            // if (hasValue(lineMsg.svcMachMstrPk_E) && svcMachMstrPk.compareTo(lineMsg.svcMachMstrPk_E.getValue()) != 0) {
            if (hasValue(lineMsg.svcMachMstrPk_E) && svcMachMstrPk.compareTo(lineMsg.svcMachMstrPk_E.getValue()) != 0
                    && isConfigHasValidOrderLine(cMsg, i, lineMsg.svcMachMstrPk_E.getValue())) {
            // END   2019/11/22 [QC#53125, ADD]
                svcMachMstrPk = lineMsg.svcMachMstrPk_E.getValue();
                dsOrdPosnNum++;
                setConfigUpdApiReqDtlPMsg(cpoUpdApiReqPMsg, cMsg, lineMsg, dsOrdPosnNum);
            }

            // Line Config: Line
            if (hasValue(lineMsg.ordCustUomQty_E) && lineMsg.ordCustUomQty_E.getValue().compareTo(BigDecimal.ZERO) > 0) {
                dsCpoLineNum++;
                setCpoUpdApiReqDtlPMsg(cpoUpdApiReqPMsg, cMsg, lineMsg, prcApiPMsg, dsOrdPosnNum, dsCpoLineNum);
                setCpoUpdApiReqPriceCalcBase(cpoUpdApiReqPMsg, cMsg, prcApiPMsg, i, dsCpoLineNum);
            }
        }

        // add start 2016/06/27 CSA Defect#9884
        // Delivery Info
        setCpoUpdApiReqDeliveryInfo(cpoUpdApiReqPMsg, cMsg);
        // add end 2016/06/27 CSA Defect#9884

        // setCpoUpdApiReqSalesCredit(cpoUpdApiReqPMsg, cMsg);

        // START 2018/04/11 K.Kitachi [QC#11642, ADD]
        setCpoUpdApiReqContactInfo(cpoUpdApiReqPMsg, cMsg);
        // END 2018/04/11 K.Kitachi [QC#11642, ADD]

        // Add Start 2019/05/29 QC#50405
        NWXC150001SalesRep.updateToLatestSalesRep(cpoUpdApiReqPMsg);
        // Add End 2019/05/29 QC#50405

        if ("E".equals(cMsg.getMessageKind())) {
            return null;
        }

        boolean callApiFlg = true;
        if (cpoUpdApiReqPMsg.A.getValidCount() == 0) {
            callApiFlg = false;
        }

        // Call DS CPO Update API
        boolean isNormal = true;
        if (callApiFlg) {
            isNormal = callApi(cMsg, cpoUpdApiReqPMsg);
//            if (isNormal) {
//                EZDConnectionMgr.getInstance().commit();
//            }
        }

        if ("E".equals(cMsg.getMessageKind()) || !isNormal) {
            return null;
        }
        return cpoUpdApiReqPMsg;
    }

    private void setCpoUpdApiReqPMsg(NWZC150001PMsg pMsg, NSAL0990CMsg cMsg, NWZC157001PMsg prcApiPMsg) {

        setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(pMsg.xxModeCd, NWZC150001Constant.MODE_SUBMIT);
        setValue(pMsg.slsDt, cMsg.slsDt);
        setValue(pMsg.usrId, cMsg.getUserID());
        setValue(pMsg.bizAppId, BUSINESS_ID);

        pMsg.cpoOrdNum.clear();
        setValue(pMsg.dsOrdCatgCd, cMsg.dsOrdCatgCd);
        setValue(pMsg.dsOrdTpCd, cMsg.dsOrdTpCd);
        setValue(pMsg.dsOrdRsnCd, cMsg.dsOrdRsnCd);
        DS_ORD_TPTMsg dsOrdTpTMsg = queryForDsCPOUpdate.findDsOrdTp(cMsg);
        if (dsOrdTpTMsg != null) {
            setValue(pMsg.cpoOrdTpCd, dsOrdTpTMsg.cpoOrdTpCd);
        }
        setValue(pMsg.custIssPoNum, cMsg.custIssPoNum);
        pMsg.custIssPoDt.clear();
        setValue(pMsg.sysSrcCd, SYS_SRC.S21_ORDER);
        setValue(pMsg.cpoSrcTpCd, CPO_SRC_TP.SUPPLY_RELEASE_SCREEN);
        setValue(pMsg.ordFuflLvlCd, ORD_FUFL_LVL.UNRESTRICTED);
        setValue(pMsg.billToCustAcctCd, cMsg.billToAcctNum);
        setValue(pMsg.billToCustCd, cMsg.billToLocNum);
        setValue(pMsg.shipToCustAcctCd, cMsg.curLocAcctNum);
        // mod start 2016/08/22 CSA Defect#13606
        setValue(pMsg.sellToCustCd, cMsg.billToAcctNum);
        setValue(pMsg.soldToCustLocCd, cMsg.billToLocNum);
        // mod end 2016/08/22 CSA Defect#13606
        // START 2017/10/18 M.Kidokoro [QC#20246, ADD]
        setValue(pMsg.sellToFirstRefCmntTxt, cMsg.scrLbNm);
        // END 2017/10/18 M.Kidokoro [QC#20246, ADD]
        setValue(pMsg.adminPsnCd, cMsg.getUserID());
        setValue(pMsg.addRddDt, cMsg.slsDt);
        pMsg.addRsdDt.clear();
        setValue(pMsg.addShpgSvcLvlCd, cMsg.shpgSvcLvlCd);

        FRT_CONDTMsg frtCondTMsg = query.findFrtCond(cMsg);
        if (frtCondTMsg != null) {
            setValue(pMsg.addFrtChrgMethCd, frtCondTMsg.frtChrgMethCd);
            setValue(pMsg.addFrtChrgToCd, frtCondTMsg.frtChrgToCd);
        }

        setValue(pMsg.addDropShipFlg, ZYPConstant.FLG_OFF_N);

        SHIP_TO_CUSTTMsg shipToCustTMsg = queryForDsCPOUpdate.findShipToCust(cMsg);
        if (shipToCustTMsg != null) {
            setValue(pMsg.addShipToCustCd, shipToCustTMsg.shipToCustCd);
            setValue(pMsg.addShipToLocNm, shipToCustTMsg.locNm);
            setValue(pMsg.addShipToAddlLocNm, shipToCustTMsg.addlLocNm);
            setValue(pMsg.addShipToFirstLineAddr, shipToCustTMsg.firstLineAddr);
            setValue(pMsg.addShipToScdLineAddr, shipToCustTMsg.scdLineAddr);
            setValue(pMsg.addShipToThirdLineAddr, shipToCustTMsg.thirdLineAddr);
            setValue(pMsg.addShipToFrthLineAddr, shipToCustTMsg.frthLineAddr);
            setValue(pMsg.addShipToCtyAddr, shipToCustTMsg.ctyAddr);
            setValue(pMsg.addShipToStCd, shipToCustTMsg.stCd);
            setValue(pMsg.addShipToProvNm, shipToCustTMsg.provNm);
            setValue(pMsg.addShipToPostCd, shipToCustTMsg.postCd);
            setValue(pMsg.addShipToCtryCd, shipToCustTMsg.ctryCd);
            if (hasValue(shipToCustTMsg.ctryCd)) {
                CTRYTMsg ctryTMsg = queryForDsCPOUpdate.findCtry(cMsg, shipToCustTMsg.ctryCd.getValue());
                if (ctryTMsg != null) {
                    setValue(pMsg.addShipToCntyNm, ctryTMsg.ctryNm);
                }
            }
            setValue(pMsg.addShipTo01RefCmntTxt, shipToCustTMsg.firstRefCmntTxt);
            setValue(pMsg.addShipTo02RefCmntTxt, shipToCustTMsg.scdRefCmntTxt);
        }

        pMsg.addPmtTermCashDiscCd.clear();
        setValue(pMsg.carrAcctNum, cMsg.carrAcctNum);
        // add start 2016/06/27 CSA Defect#9888
        setValue(pMsg.ordSgnDt, cMsg.slsDt);
        // add end 2016/06/27 CSA Defect#9888
        // mod start 2016/10/20 CSA Defect#15323
        //setValue(pMsg.slsRepCd, cMsg.tocCd);
        // START 2018/09/25 K.Kitachi [QC#26260, DEL]
//        setValue(pMsg.slsRepCd, cMsg.slsRepCd);
        // END 2018/09/25 K.Kitachi [QC#26260, DEL]
        // mod start 2016/10/20 CSA Defect#15323
        setValue(pMsg.prcBaseDt, cMsg.slsDt);
        setValue(pMsg.prcCalcDt, cMsg.slsDt);
        pMsg.negoDealAmt.clear();

        if (prcApiPMsg.NWZC157002PMsg.getValidCount() != 0) {
            NWZC157002PMsg linePrcApiPMsg = prcApiPMsg.NWZC157002PMsg.no(0);
            setValue(pMsg.prcCatgCd, linePrcApiPMsg.prcCatgCd);
            NWZC157001PMsg flPrcApiListPMsg = callPricingApiForGetFloorPrcList(cMsg);
            if (!NSAL0990CommonLogic.checkPrcApiParam(cMsg, flPrcApiListPMsg)) {
                return;
            }
            if (flPrcApiListPMsg.xxPrcList.getValidCount() != 0) {
                NWZC157001_xxPrcListPMsg prcListPMsg = flPrcApiListPMsg.xxPrcList.no(0);
                setValue(pMsg.flPrcListCd, prcListPMsg.prcCatgCd);
            }
        }
        setValue(pMsg.frtCondCd, cMsg.frtCondCd);
        setValue(pMsg.carrSvcLvlCd, cMsg.carrSvcLvlCd);
        setValue(pMsg.dsCrCardPk, cMsg.dsCrCardPk);
        // QC#27746 ADD START
        setValue(pMsg.dsPmtMethCd, cMsg.dsPmtMethCd);
        setValue(pMsg.xxValUpdFlg, ZYPConstant.FLG_ON_Y);
        setValue(pMsg.sendInvFlg, ZYPConstant.FLG_OFF_N);
    }

    private BigDecimal sumBigDecimal(BigDecimal inItem, BigDecimal outItem) {
        if (hasValue(inItem) && hasValue(outItem)) {
            return outItem.add(inItem);
        }
        return outItem;
    }

    private void setCpoUpdApiReqDtlPMsg(NWZC150001PMsg pMsg, NSAL0990CMsg cMsg, NSAL0990_ECMsg lineMsg, NWZC157001PMsg prcApiPMsg, int dsOrdPosnNum, int dsCpoLineNum) {

        final int dtlPMsgCount = pMsg.A.getValidCount();

        final NWZC150001_APMsg dtlPMsg = pMsg.A.no(dtlPMsgCount);
        setValue(dtlPMsg.xxRqstTpCd_A1, NWZC150001Constant.RQST_TP_DTL_NEW);
        setValue(dtlPMsg.cpoDtlLineNum_A1, ZYPCommonFunc.leftPad(String.valueOf(dtlPMsgCount + 1), NSAL0990Constant.NUM_THREE, "0"));
        setValue(dtlPMsg.cpoDtlLineSubNum_A1, NSAL0990Constant.DEF_LINE_SUB_NUM);
        DS_ORD_TPTMsg dsOrdTpTMsg = queryForDsCPOUpdate.findDsOrdTp(cMsg);
        if (dsOrdTpTMsg != null) {
            setValue(dtlPMsg.cpoOrdTpCd_A1, dsOrdTpTMsg.cpoOrdTpCd);
        }
        setValue(dtlPMsg.mdseCd_A1, lineMsg.mdseCd_E);
        setValue(dtlPMsg.origMdseCd_A1, lineMsg.mdseCd_E);
        setValue(dtlPMsg.dropShipFlg_A1, ZYPConstant.FLG_OFF_N);

        SHIP_TO_CUSTTMsg shipToCustTMsg = queryForDsCPOUpdate.findShipToCust(cMsg);
        if (shipToCustTMsg != null) {
            setValue(dtlPMsg.shipToCustCd_A1, shipToCustTMsg.shipToCustCd);
            setValue(dtlPMsg.shipToLocNm_A1, shipToCustTMsg.locNm);
            setValue(dtlPMsg.shipToAddlLocNm_A1, shipToCustTMsg.addlLocNm);
            setValue(dtlPMsg.shipToFirstLineAddr_A1, shipToCustTMsg.firstLineAddr);
            setValue(dtlPMsg.shipToScdLineAddr_A1, shipToCustTMsg.scdLineAddr);
            setValue(dtlPMsg.shipToThirdLineAddr_A1, shipToCustTMsg.thirdLineAddr);
            setValue(dtlPMsg.shipToFrthLineAddr_A1, shipToCustTMsg.frthLineAddr);
            setValue(dtlPMsg.shipToCtyAddr_A1, shipToCustTMsg.ctyAddr);
            setValue(dtlPMsg.shipToStCd_A1, shipToCustTMsg.stCd);
            setValue(dtlPMsg.shipToProvNm_A1, shipToCustTMsg.provNm);
            setValue(dtlPMsg.shipToPostCd_A1, shipToCustTMsg.postCd);
            setValue(dtlPMsg.shipToCtryCd_A1, shipToCustTMsg.ctryCd);
            if (hasValue(shipToCustTMsg.ctryCd)) {
                CTRYTMsg ctryTMsg = queryForDsCPOUpdate.findCtry(cMsg, shipToCustTMsg.ctryCd.getValue());
                if (ctryTMsg != null) {
                    setValue(dtlPMsg.shipToCntyNm_A1, ctryTMsg.ctryNm);
                }
            }
            setValue(dtlPMsg.shipToFirstRefCmntTxt_A1, shipToCustTMsg.firstRefCmntTxt);
            setValue(dtlPMsg.shipToScdRefCmntTxt_A1, shipToCustTMsg.scdRefCmntTxt);
        }
        setValue(dtlPMsg.ordQty_A1, lineMsg.ordCustUomQty_E);
        setValue(dtlPMsg.ordCustUomQty_A1, lineMsg.ordCustUomQty_E);
        // setValue(dtlPMsg.entDealNetUnitPrcAmt_A1, lineMsg.entDealNetUnitPrcAmt_E);
        setValue(dtlPMsg.ccyCd_A1, cMsg.ccyCd);
        setValue(dtlPMsg.rddDt_A1, cMsg.slsDt);
        setValue(dtlPMsg.uomCpltFlg_A1, ZYPConstant.FLG_OFF_N);
        setValue(dtlPMsg.stkStsCd_A1, STK_STS.GOOD);
        setValue(dtlPMsg.frtChrgMethCd_A1, pMsg.addFrtChrgMethCd);
        setValue(dtlPMsg.frtChrgToCd_A1, pMsg.addFrtChrgToCd);
        setValue(dtlPMsg.shpgSvcLvlCd_A1, cMsg.shpgSvcLvlCd);
        // setValue(dtlPMsg.slsRepOrSlsTeamTocCd_A1, cMsg.tocCd); // 2019/03/04 S21_NA#30557 Del
        setValue(dtlPMsg.custUomCd_A1, PKG_UOM.EACH);
        setValue(dtlPMsg.manPrcFlg_A1, ZYPConstant.FLG_OFF_N);
        setValue(dtlPMsg.carrSvcLvlCd_A1, cMsg.carrSvcLvlCd);
        setValue(dtlPMsg.carrAcctNum_A1, cMsg.carrAcctNum);
        setValue(dtlPMsg.setItemShipCpltFlg_A1, ZYPConstant.FLG_OFF_N);
        // mod start 2016/10/04 CSA Defect#12898
        // START 2018/01/16 K.Kojima [QC#21855,MOD]
        // if (getSvcMachMstrPk(cMsg.dsContrCatgCd.getValue(), lineMsg.svcMachMstrPk_E.getValue()) != null) {
        if (ZYPCommonFunc.hasValue(lineMsg.svcMachMstrPk_E)) {
        // END 2018/01/16 K.Kojima [QC#21855,MOD]
            SVC_MACH_MSTRTMsg svcMachMstrTMsg = queryForDsCPOUpdate.findSvcMachMstr(cMsg, lineMsg.svcMachMstrPk_E.getValue());
            if (svcMachMstrTMsg != null) {
                setValue(dtlPMsg.svcConfigMstrPk_A1, svcMachMstrTMsg.svcConfigMstrPk);
                setValue(dtlPMsg.firstBllgAttrbValTxt_A1, svcMachMstrTMsg.ctrlFldTxt_01);
                setValue(dtlPMsg.scdBllgAttrbValTxt_A1, svcMachMstrTMsg.ctrlFldTxt_02);
                setValue(dtlPMsg.thirdBllgAttrbValTxt_A1, svcMachMstrTMsg.ctrlFldTxt_03);
                setValue(dtlPMsg.frthBllgAttrbValTxt_A1, svcMachMstrTMsg.ctrlFldTxt_04);
                setValue(dtlPMsg.fifthBllgAttrbValTxt_A1, svcMachMstrTMsg.ctrlFldTxt_05);
                setValue(dtlPMsg.sixthBllgAttrbValTxt_A1, svcMachMstrTMsg.ctrlFldTxt_06);
            }
        }
        // mod end 2016/10/04 CSA Defect#12898
        setValue(dtlPMsg.dsOrdPosnNum_A1, Integer.toString(dsOrdPosnNum));

        BigDecimal unitNetWt = getUnitNetWt(cMsg.glblCmpyCd.getValue(), lineMsg);
        if (null == unitNetWt) {
            cMsg.setMessageInfo(ZZM9037E);
            return;
        }
        setValue(dtlPMsg.unitNetWt_A1, unitNetWt);

        setValue(dtlPMsg.frtCondCd_A1, cMsg.frtCondCd);
        setValue(dtlPMsg.dsContrNum_A1, lineMsg.dsContrNum_E);
        setValue(dtlPMsg.dsCpoLineNum_A1, new BigDecimal(dsCpoLineNum));
        setValue(dtlPMsg.dsOrdLineCatgCd_A1, cMsg.dsOrdLineCatgCd);
        setValue(dtlPMsg.flPrcListCd_A1, pMsg.flPrcListCd);
        setValue(dtlPMsg.dealPrcListPrcAmt_A1, lineMsg.entDealNetUnitPrcAmt_E);
        setValue(dtlPMsg.funcPrcListPrcAmt_A1, lineMsg.entDealNetUnitPrcAmt_E);
        setValue(dtlPMsg.prcBaseDt_A1, cMsg.slsDt);

        if (prcApiPMsg.NWZC157002PMsg.getValidCount() != 0) {
            NWZC157002PMsg linePrcApiPMsg = prcApiPMsg.NWZC157002PMsg.no(0);
            setValue(dtlPMsg.prcCatgCd_A1, linePrcApiPMsg.prcCatgCd);
        }
        // set Field Amount
        for (int k = 0; k < prcApiPMsg.NWZC157004PMsg.getValidCount(); k++) {
            NWZC157004PMsg nwzc157004PMsg = prcApiPMsg.NWZC157004PMsg.no(k);
            setValue(dtlPMsg.xxTotBaseAmt_A1, sumBigDecimal(nwzc157004PMsg.xxTotBaseAmt.getValue(), dtlPMsg.xxTotBaseAmt_A1.getValue()));
            setValue(dtlPMsg.xxTotDiscAmt_A1, sumBigDecimal(nwzc157004PMsg.xxTotDiscAmt.getValue(), dtlPMsg.xxTotDiscAmt_A1.getValue()));
            setValue(dtlPMsg.xxTotSpclPrcAmt_A1, sumBigDecimal(nwzc157004PMsg.xxTotSpclPrcAmt.getValue(), dtlPMsg.xxTotSpclPrcAmt_A1.getValue()));
            setValue(dtlPMsg.xxTotNetDiscAmt_A1, sumBigDecimal(nwzc157004PMsg.xxTotNetDiscAmt.getValue(), dtlPMsg.xxTotNetDiscAmt_A1.getValue()));
            setValue(dtlPMsg.xxTotNetPrcAmt_A1, sumBigDecimal(nwzc157004PMsg.xxTotNetPrcAmt.getValue(), dtlPMsg.xxTotNetPrcAmt_A1.getValue()));
            setValue(dtlPMsg.xxTotFrtAmt_A1, sumBigDecimal(nwzc157004PMsg.xxTotFrtAmt.getValue(), dtlPMsg.xxTotFrtAmt_A1.getValue()));
            setValue(dtlPMsg.xxTotSpclChrgAmt_A1, sumBigDecimal(nwzc157004PMsg.xxTotSpclChrgAmt.getValue(), dtlPMsg.xxTotSpclChrgAmt_A1.getValue()));
            setValue(dtlPMsg.xxTotTaxAmt_A1, sumBigDecimal(nwzc157004PMsg.xxTotTaxAmt.getValue(), dtlPMsg.xxTotTaxAmt_A1.getValue()));
            setValue(dtlPMsg.xxSlsAmt_A1, sumBigDecimal(nwzc157004PMsg.xxSlsAmt.getValue(), dtlPMsg.xxSlsAmt_A1.getValue()));
            setValue(dtlPMsg.xxTotAmt_A1, sumBigDecimal(nwzc157004PMsg.xxTotAmt.getValue(), dtlPMsg.xxTotAmt_A1.getValue()));
        }
        setValue(dtlPMsg.supdLockFlg_A1, ZYPConstant.FLG_ON_Y);
        setValue(dtlPMsg.loanBalQty_A1, BigDecimal.ZERO);
        setValue(dtlPMsg.baseCmptFlg_A1, ZYPConstant.FLG_OFF_N);
        // mod start 2016/10/04 CSA Defect#12898
        // START 2018/01/16 K.Kojima [QC#21855,MOD]
        // setValue(dtlPMsg.svcMachMstrPk_A1, getSvcMachMstrPk(cMsg.dsContrCatgCd.getValue(), lineMsg.svcMachMstrPk_E.getValue()));
        setValue(dtlPMsg.svcMachMstrPk_A1, lineMsg.svcMachMstrPk_E.getValue());
        // END 2018/01/16 K.Kojima [QC#21855,MOD]
        // mod end 2016/10/04 CSA Defect#12898
        pMsg.A.setValidCount(dtlPMsgCount + 1);
    }

    private BigDecimal getUnitNetWt(String glblCmpyCd, NSAL0990_ECMsg lineMsg) {
        String pkgUomCd = ZYPCodeDataUtil.getVarCharConstValue(PKG_UOM_FOR_PRC, glblCmpyCd);
        if (null == pkgUomCd) {
            pkgUomCd = PKG_UOM.EACH;
        }
        MDSE_STORE_PKGTMsg strePkgTMsg = new MDSE_STORE_PKGTMsg();
        strePkgTMsg.glblCmpyCd.setValue(glblCmpyCd);
        //get MDSE info
        MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, lineMsg.mdseCd_E.getValue());
        strePkgTMsg.mdseCd.setValue(mdseTMsg.mdseCd.getValue());
        strePkgTMsg.pkgUomCd.setValue(pkgUomCd);

        strePkgTMsg = (MDSE_STORE_PKGTMsg) S21FastTBLAccessor.findByKey(strePkgTMsg);
        if (null == strePkgTMsg) {
            return null;
        }
        BigDecimal inPoundWt = strePkgTMsg.inPoundWt.getValue();
        BigDecimal inPountNetWt = inPoundWt.multiply(lineMsg.ordCustUomQty_E.getValue());

        if (null == inPountNetWt) {
            lineMsg.mdseCd_E.setErrorInfo(1, NSAM0440E, new String[] {"MDSE_STORE_PKG", lineMsg.mdseCd_E.getValue() });
            return null;
        }
        return inPountNetWt;
    }

    private void setConfigUpdApiReqDtlPMsg(NWZC150001PMsg pMsg, NSAL0990CMsg cMsg, NSAL0990_ECMsg lineMsg, int dsOrdPosnNum) {

        final int dtlPMsgCount = pMsg.cpoConfig.getValidCount();
        final NWZC150001_cpoConfigPMsg cpoConfigPMsg = pMsg.cpoConfig.no(dtlPMsgCount);

        setValue(cpoConfigPMsg.xxRqstTpCd, NWZC150001Constant.RQST_TP_CONFIG_NEW);
        cpoConfigPMsg.dsCpoConfigPk.clear();
        setValue(cpoConfigPMsg.dsOrdPosnNum, Integer.toString(dsOrdPosnNum));
        setValue(cpoConfigPMsg.configTpCd, CONFIG_TP.NEW);
        setValue(cpoConfigPMsg.configCatgCd, CONFIG_CATG.OUTBOUND);
        // mod start 2016/10/04 CSA Defect#12898
        // START 2018/01/16 K.Kojima [QC#21855,MOD]
        // if (getSvcMachMstrPk(cMsg.dsContrCatgCd.getValue(), lineMsg.svcMachMstrPk_E.getValue()) != null) {
        if (ZYPCommonFunc.hasValue(lineMsg.svcMachMstrPk_E)) {
        // END 2018/01/16 K.Kojima [QC#21855,MOD]
            SVC_MACH_MSTRTMsg svcMachMstrTMsg = queryForDsCPOUpdate.findSvcMachMstr(cMsg, lineMsg.svcMachMstrPk_E.getValue());
            if (svcMachMstrTMsg != null && hasValue(svcMachMstrTMsg.svcConfigMstrPk)) {
                SVC_CONFIG_MSTRTMsg svcConfigTMsg = queryForDsCPOUpdate.findSvcConfigMstr(cMsg, svcMachMstrTMsg.svcConfigMstrPk.getValue());
                if (svcConfigTMsg != null && hasValue(svcConfigTMsg.mdlId)) {
                    setValue(cpoConfigPMsg.svcConfigMstrPk, svcConfigTMsg.svcConfigMstrPk);
                    setValue(cpoConfigPMsg.mdlId, svcConfigTMsg.mdlId);
                    DS_MDLTMsg dsMdlTMsg = queryForDsCPOUpdate.findDsMdl(cMsg, svcConfigTMsg.mdlId.getValue());
                    if (dsMdlTMsg != null) {
                        setValue(cpoConfigPMsg.mdlDescTxt, dsMdlTMsg.mdlDescTxt);
                    }
                }
            }
        }
        // mod end 2016/10/04 CSA Defect#12898
        setValue(cpoConfigPMsg.billToCustAcctCd, cMsg.billToAcctNum);
        setValue(cpoConfigPMsg.billToCustCd, cMsg.billToLocNum);
        setValue(cpoConfigPMsg.shipToCustAcctCd, cMsg.curLocAcctNum);
        setValue(cpoConfigPMsg.shipToCustCd, cMsg.curLocNum);
        setValue(cpoConfigPMsg.dropShipFlg, ZYPConstant.FLG_OFF_N);
        SHIP_TO_CUSTTMsg shipToCustTMsg = queryForDsCPOUpdate.findShipToCust(cMsg);
        if (shipToCustTMsg != null) {
            setValue(cpoConfigPMsg.shipToLocNm, shipToCustTMsg.locNm);
            setValue(cpoConfigPMsg.shipToAddlLocNm, shipToCustTMsg.addlLocNm);
            setValue(cpoConfigPMsg.shipToFirstLineAddr, shipToCustTMsg.firstLineAddr);
            setValue(cpoConfigPMsg.shipToScdLineAddr, shipToCustTMsg.scdLineAddr);
            setValue(cpoConfigPMsg.shipToThirdLineAddr, shipToCustTMsg.thirdLineAddr);
            setValue(cpoConfigPMsg.shipToFrthLineAddr, shipToCustTMsg.frthLineAddr);
            setValue(cpoConfigPMsg.shipTo01RefCmntTxt, shipToCustTMsg.firstRefCmntTxt);
            setValue(cpoConfigPMsg.shipTo02RefCmntTxt, shipToCustTMsg.scdRefCmntTxt);
            setValue(cpoConfigPMsg.shipToCtyAddr, shipToCustTMsg.ctyAddr);
            setValue(cpoConfigPMsg.shipToStCd, shipToCustTMsg.stCd);
            setValue(cpoConfigPMsg.shipToProvNm, shipToCustTMsg.provNm);
            if (hasValue(shipToCustTMsg.ctryCd)) {
                CTRYTMsg ctryTMsg = queryForDsCPOUpdate.findCtry(cMsg, shipToCustTMsg.ctryCd.getValue());
                if (ctryTMsg != null) {
                    setValue(cpoConfigPMsg.shipToCntyNm, ctryTMsg.ctryNm);
                }
            }
            setValue(cpoConfigPMsg.shipToPostCd, shipToCustTMsg.postCd);
            setValue(cpoConfigPMsg.shipToCtryCd, shipToCustTMsg.ctryCd);
        }

        pMsg.cpoConfig.setValidCount(dtlPMsgCount + 1);
    }

//    private void setCpoUpdApiReqSalesCredit(NWZC150001PMsg cpoUpdApiReqPMsg, NSAL0990CMsg cMsg) {
//        NMZC260001PMsg drPMsg = callDefaultRepApi(cMsg);
//        setDefSlsRepApiErrMsg(cMsg, drPMsg);
//        if ("E".equals(cMsg.getMessageKind())) {
//            return;
//        }
//        for (int i = 0; i < drPMsg.defSlsRepList.getValidCount(); i++) {
//            NMZC260001_defSlsRepListPMsg dsrlPMsg = drPMsg.defSlsRepList.no(i);
//            LINE_BIZ_ROLE_TPTMsg lineBizRoleTpTMsg = queryForDsCPOUpdate.findLineBizRoleTp(cMsg, dsrlPMsg.lineBizRoleTpCd.getValue());
//            if (lineBizRoleTpTMsg != null && hasValue(cMsg.svcByLineBizTpCd) && cMsg.svcByLineBizTpCd.getValue().equals(dsrlPMsg.lineBizTpCd.getValue())) {
//                int ix = cpoUpdApiReqPMsg.cpoSlsCr.getValidCount();
//                NWZC150001_cpoSlsCrPMsg cscPMsg = cpoUpdApiReqPMsg.cpoSlsCr.no(ix++);
//
//                cscPMsg.clear();
//                setValue(cscPMsg.xxRqstTpCd, NWZC150001Constant.RQST_TP_SLS_CR_NEW);
//                setValue(cscPMsg.slsRepCd, cpoUpdApiReqPMsg.slsRepCd);
//                setValue(cscPMsg.slsRepRoleTpCd, dsrlPMsg.lineBizRoleTpCd);
//                setValue(cscPMsg.slsRepCrPct, NSAL0990Constant.PCT_100);
//                setValue(cscPMsg.slsCrQuotFlg, ZYPConstant.FLG_ON_Y);
//
//                cpoUpdApiReqPMsg.cpoSlsCr.setValidCount(ix);
//            }
//        }
//    }
//
//    private NMZC260001PMsg callDefaultRepApi(NSAL0990CMsg cMsg) {
//        NMZC260001PMsg drPMsg = new NMZC260001PMsg();
//
//        setValue(drPMsg.glblCmpyCd, cMsg.glblCmpyCd);
//        setValue(drPMsg.shipToCustCd, cMsg.curLocNum);
//
//        new NMZC260001().execute(drPMsg, ONBATCH_TYPE.ONLINE);
//        return drPMsg;
//    }

    // add start 2016/07/12 CSA Defect#9884
    private void setCpoUpdApiReqDeliveryInfo(NWZC150001PMsg cpoUpdApiReqPMsg, NSAL0990CMsg cMsg) {
        NWZC150001_cpoDlvyInfoListPMsg dlvyInfo = cpoUpdApiReqPMsg.cpoDlvyInfoList.no(0);
        setValue(dlvyInfo.xxRqstTpCd, NWZC150001Constant.RQST_TP_DELY_INFO_NEW);
        // START 2018/11/07 K.Kitachi [QC#27387, MOD]
//        String delyAddlCmntTxt = cMsg.scrLbNm.getValue();
//        if (hasValue(delyAddlCmntTxt)) {
//            delyAddlCmntTxt = ZYPCommonFunc.concatString(cMsg.scrLbNm.getValue(), SPACE, cMsg.shpgInstnCmntTxt.getValue());
//        } else {
//            delyAddlCmntTxt = cMsg.shpgInstnCmntTxt.getValue();
//        }
        String delyAddlCmntTxt = cMsg.shpgInstnCmntTxt.getValue();
        // END 2018/11/07 K.Kitachi [QC#27387, MOD]
        if (delyAddlCmntTxt.length() > DELY_CMNT_END_IDX) {
            delyAddlCmntTxt = delyAddlCmntTxt.substring(DELY_CMNT_START_IDX, DELY_CMNT_END_IDX);
        }
        setValue(dlvyInfo.delyAddlCmntTxt, delyAddlCmntTxt);
        cpoUpdApiReqPMsg.cpoDlvyInfoList.setValidCount(1);
    }
    // add end 2016/07/12 CSA Defect#9884

    private void setCpoUpdApiReqPriceCalcBase(NWZC150001PMsg cpoUpdApiReqPMsg, NSAL0990CMsg cMsg, NWZC157001PMsg prcApiPMsg, int dtlIdx, int dsCpoLineNum) {

        int priceListPMsgCount = cpoUpdApiReqPMsg.priceList.getValidCount();
        NWZC157002PMsg linePrcApiPMsg = prcApiPMsg.NWZC157002PMsg.no(dtlIdx);

        for (int j = 0; j < linePrcApiPMsg.NWZC157003PMsg.getValidCount() && priceListPMsgCount < cpoUpdApiReqPMsg.priceList.length(); j++) {
            NWZC150001_priceListPMsg priceListPMsg = cpoUpdApiReqPMsg.priceList.no(priceListPMsgCount);
            NWZC157003PMsg nwzc157003PMsg = linePrcApiPMsg.NWZC157003PMsg.no(j);

            if (!CONFIG_CATG.OUTBOUND.equals(nwzc157003PMsg.configCatgCd.getValue())) {
                continue;
            }

            priceListPMsg.ordPrcCalcBasePk.clear(); 
            setValue(priceListPMsg.cpoDtlLineNum, ZYPCommonFunc.leftPad(String.valueOf(dsCpoLineNum), NSAL0990Constant.NUM_THREE, "0"));
            setValue(priceListPMsg.cpoDtlLineSubNum, NSAL0990Constant.DEF_LINE_SUB_NUM);
            setValue(priceListPMsg.prcCondTpCd, nwzc157003PMsg.prcCondTpCd);
            setValue(priceListPMsg.prcDtlGrpCd, nwzc157003PMsg.prcDtlGrpCd);
            setValue(priceListPMsg.prcJrnlGrpCd, nwzc157003PMsg.prcJrnlGrpCd);
            setValue(priceListPMsg.prcCondManEntryFlg, nwzc157003PMsg.prcCondManEntryFlg);
            setValue(priceListPMsg.prcCondManAddFlg, nwzc157003PMsg.prcCondManAddFlg);
            setValue(priceListPMsg.prcCondManDelFlg, nwzc157003PMsg.prcCondManDelFlg);
            setValue(priceListPMsg.pkgUomCd, nwzc157003PMsg.pkgUomCd);
            setValue(priceListPMsg.prcCondUnitCd, nwzc157003PMsg.prcCondUnitCd);
            setValue(priceListPMsg.prcCalcMethCd, nwzc157003PMsg.prcCalcMethCd);
            setValue(priceListPMsg.autoPrcAmtRate, nwzc157003PMsg.autoPrcAmtRate);
            setValue(priceListPMsg.maxPrcAmtRate, nwzc157003PMsg.maxPrcAmtRate);
            setValue(priceListPMsg.minPrcAmtRate, nwzc157003PMsg.minPrcAmtRate);
            setValue(priceListPMsg.manPrcAmtRate, nwzc157003PMsg.manPrcAmtRate);
            setValue(priceListPMsg.calcPrcAmtRate, nwzc157003PMsg.calcPrcAmtRate);
            setValue(priceListPMsg.unitPrcAmt, nwzc157003PMsg.unitPrcAmt);
            setValue(priceListPMsg.dsMdsePrcPk, nwzc157003PMsg.dsMdsePrcPk);
            setValue(priceListPMsg.specCondPrcPk, nwzc157003PMsg.specCondPrcPk);
            setValue(priceListPMsg.frtPerWtPk, nwzc157003PMsg.frtPerWtPk);
            setValue(priceListPMsg.autoPrcCcyCd, nwzc157003PMsg.autoPrcCcyCd);

            // START 2018/09/19  [QC#9700,ADD]
            ZYPEZDItemValueSetter.setValue(priceListPMsg.prcRuleApplyFlg, nwzc157003PMsg.prcRuleApplyFlg);
            ZYPEZDItemValueSetter.setValue(priceListPMsg.prcRulePrcdPk, nwzc157003PMsg.prcRulePrcdPk);
            // END 2018/09/19  [QC#9700,ADD]

            priceListPMsgCount++;
        }
        cpoUpdApiReqPMsg.priceList.setValidCount(priceListPMsgCount);
    }

    private boolean callApi(NSAL0990CMsg cMsg, NWZC150001PMsg cpoUpdApiMsg) {

        /**************************************************
         * NWZC150001 : DS CPO Update API
         **************************************************/
        final List<NWZC150002PMsg> cpoUpdApiOutMsgList = new ArrayList<NWZC150002PMsg>();
        final List<NWZC150003PMsg> cpoUpdApiOutMsgList03 = new ArrayList<NWZC150003PMsg>();

        new NWZC150001().execute(cpoUpdApiMsg, cpoUpdApiOutMsgList, cpoUpdApiOutMsgList03, ONBATCH_TYPE.ONLINE);

        setCpoUpdateApiErrMsg(cMsg, cpoUpdApiMsg);

        setCpoUpdateApiDtlErrMsg(cMsg, cpoUpdApiMsg, cpoUpdApiOutMsgList, cpoUpdApiOutMsgList03);

        if ("E".equals(cMsg.getMessageKind()) || !ZYPCommonFunc.hasValue(cpoUpdApiMsg.cpoOrdNum)) { // QC#28953
            return false;
        }

        setValue(cMsg.cpoOrdNum, cpoUpdApiMsg.cpoOrdNum.getValue());

        return true;

    }

//    private static void setDefSlsRepApiErrMsg(NSAL0990CMsg cMsg, NMZC260001PMsg cpoUpdApiMsg) {
//
//        if (S21ApiUtil.isXxMsgId(cpoUpdApiMsg)) {
//            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(cpoUpdApiMsg);
//            for (int i = 0; i < msgList.size(); i++) {
//                S21ApiMessage msg = msgList.get(i);
//                String msgId = msg.getXxMsgid();
//                String[] msgPrms = msg.getXxMsgPrmArray();
//                cMsg.setMessageInfo(msgId, msgPrms);
//            }
//        }
//    }

    private static void setCpoUpdateApiErrMsg(NSAL0990CMsg cMsg, NWZC150001PMsg cpoUpdApiMsg) {

        if (S21ApiUtil.isXxMsgId(cpoUpdApiMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(cpoUpdApiMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                cMsg.setMessageInfo(msgId, msgPrms);
            }
        }
    }

    private void setCpoUpdateApiDtlErrMsg(NSAL0990CMsg cMsg, NWZC150001PMsg cpoUpdApiMsg, List<NWZC150002PMsg> cpoUpdApiOutMsgList, List<NWZC150003PMsg> cpoUpdApiInMsgList) {

        for (NWZC150002PMsg pMsg : cpoUpdApiOutMsgList) {
            if (S21ApiUtil.isXxMsgId(pMsg)) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
                for (int i = 0; i < msgList.size(); i++) {
                    S21ApiMessage msg = msgList.get(i);
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    // 2018/06/05 QC#26273 add start
                    String mdseCd = null;

                    for (int j = 0; j < cpoUpdApiMsg.A.getValidCount(); j++) {
                        NWZC150001_APMsg dtlPMsg = cpoUpdApiMsg.A.no(j);
                        // 2018/06/12 QC#26488 mod start
                        if ( //dtlPMsg.dsOrdPosnNum_A1.getValue().equals(pMsg.dsOrdPosnNum.getValue()) //
                                dtlPMsg.cpoDtlLineNum_A1.getValue().equals(pMsg.cpoDtlLineNum.getValue()) //
                                && dtlPMsg.cpoDtlLineSubNum_A1.getValue().equals(pMsg.cpoDtlLineSubNum.getValue())) {
                            mdseCd = dtlPMsg.mdseCd_A1.getValue();
                            break;
                        }
                        // 2018/06/12 QC#26488 mod end
                    }

                    NSAL0990_ECMsg lineMsg = null;
                    for (int j = 0; j < cMsg.E.getValidCount(); j++) {
                        if (cMsg.E.no(j).mdseCd_E.getValue().equals(mdseCd)) {
                            lineMsg = cMsg.E.no(j);
                            break;
                        }
                    }

                    if (lineMsg == null) {
                        cMsg.setMessageInfo(msgId, msgPrms);
                        continue;
                    }

                    if (NWZC150001Constant.NWZM1488E.equals(msgId) && mdseCd != null) {

                        MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(cMsg.glblCmpyCd.getValue(), mdseCd);
                        msgId = NWZM2023E;
                        msgPrms = new String[] {String.valueOf(mdseTMsg.cpoMinOrdQty.getValueInt()) };
                    } else if (NWZC150001Constant.NWZM1489E.equals(msgId) && mdseCd != null) {
                        MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(cMsg.glblCmpyCd.getValue(), mdseCd);
                        msgId = NWZM2024E;
                        msgPrms = new String[] {String.valueOf(mdseTMsg.cpoMaxOrdQty.getValueInt()) };
                    } else if (NWZC150001Constant.NWZM1492E.equals(msgId) && mdseCd != null) {
                        MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(cMsg.glblCmpyCd.getValue(), mdseCd);
                        msgId = NWZM2025E;
                        msgPrms = new String[] {String.valueOf(mdseTMsg.cpoIncrOrdQty.getValueInt()) };
                    }
                    lineMsg.ordCustUomQty_E.setErrorInfo(1, msgId, msgPrms);
                    cMsg.setMessageInfo(msgId, msgPrms); // QC#28953
                    // 2018/06/05 QC#26273 add start
                }
            }
        }
    }

    /**
     * call Pricing API For Get Floor Pricing List
     * @param cMsg NSAL0990CMsg
     * @return NWZC157001PMsg
     */
    private NWZC157001PMsg callPricingApiForGetFloorPrcList(NSAL0990CMsg cMsg) {
        NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();
        setValue(prcApiPMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(prcApiPMsg.xxModeCd, NWZC157001.GET_PRICE_LIST);
        setValue(prcApiPMsg.prcBaseDt, cMsg.slsDt);
        setValue(prcApiPMsg.prcCtxTpCd, PRC_CTX_TP.FLOOR_PRICE);
        setValue(prcApiPMsg.dsOrdCatgCd, cMsg.dsOrdCatgCd);
        setValue(prcApiPMsg.dsOrdTpCd, cMsg.dsOrdTpCd);
        setValue(prcApiPMsg.lineBizTpCd, cMsg.svcByLineBizTpCd);
        setValue(prcApiPMsg.dsAcctNum, cMsg.ownrAcctNum);
        setValue(prcApiPMsg.xxPrcCatgOpFlg, ZYPConstant.FLG_OFF_N);

        new NWZC157001().execute(prcApiPMsg, ONBATCH_TYPE.ONLINE);
        return prcApiPMsg;
    }

    /**
     * add text
     * @param cMsg NSAL0990CMsg
     */
    public void addText(NSAL0990CMsg cMsg) {
        // Order Notes
        if (ZYPCommonFunc.hasValue(cMsg.xxDsMultMsgDplyTxt)) {
            // START 2017/08/02 K.Kim [QC#18311, MOD]
            // insertMsgTxtDtl(cMsg, cMsg.xxDsMultMsgDplyTxt.getValue(), TXT_TP.ORDER_REMARKS);
            registAttachComment(cMsg, cMsg.xxDsMultMsgDplyTxt.getValue(), TXT_TP.ORDER_REMARKS);
            // END 2017/08/02 K.Kim [QC#18311, MOD]
        }
        // Invoice Comments
        if (ZYPCommonFunc.hasValue(cMsg.invCmntTxt)) {
            insertMsgTxtDtl(cMsg, cMsg.invCmntTxt.getValue(), TXT_TP.INVOICE_COMMENT);
        }
    }

    private static void insertMsgTxtDtl(NSAL0990CMsg cMsg, String targetTxt, String txtTpCd) {
        int count = 0;
        for (String separeteTxt : separeteText(targetTxt)) {
            count++;
            MSG_TXT_DTLTMsg tMsg = new MSG_TXT_DTLTMsg();
            setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
            setValue(tMsg.msgTxtDtlSq, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.MSG_TXT_DTL_SQ));
            setValue(tMsg.cpoOrdNum, cMsg.cpoOrdNum);
            setValue(tMsg.txtTpCd, txtTpCd);
            setValue(tMsg.txtSqNum, String.valueOf(count));
            setValue(tMsg.msgTxtInfoTxt, separeteTxt);
            EZDTBLAccessor.insert(tMsg);
        }
    }

    private static List<String> separeteText(String targetTxt) {
        List<String> list = new ArrayList<String>();
        MSG_TXT_DTLTMsg msgTxtDtlTMsg = new MSG_TXT_DTLTMsg();
        int txtLen = msgTxtDtlTMsg.getAttr("msgTxtInfoTxt").getDigit();
        for (int i = 0; i < targetTxt.length(); i += txtLen) {
            String s = S21StringUtil.subStringByLength(targetTxt, i, txtLen);
            list.add(s);
        }
        return list;
    }

    // START 2017/08/02 K.Kim [QC#18311, ADD]
    /**
     * registAttachComment
     * @param cMsg NSAL0990CMsg
     * @param targetTxt String
     * @param txtTpCd String
     */
    public static void registAttachComment(NSAL0990CMsg cMsg, String targetTxt, String txtTpCd) {
        ZYPFileUpDownParameter params = new ZYPFileUpDownParameter();
        params.setBusinessId(ATTACH_BUSINESS_ID);
        params.setAttDataGrp(cMsg.cpoOrdNum.getValue());
        params.setAttDataCmnt(targetTxt);
        params.setBusinessNm(ATTACH_BUSINESS_NM);
        params.setAttDocTpCd(txtTpCd);
        ZYPFileUpDown.uploadNote(params);
    }
    // END 2017/08/02 K.Kim [QC#18311, ADD]

    /**
     * create DS_ACPO Info
     * @param cMsg NSAL0990CMsg
     * @return boolean
     */
    public boolean createDsAcpoInfo(NSAL0990CMsg cMsg) {

        String dsAcpoNum = null;
        String preDsContrNum = "";
        for (int i = 0; i < cMsg.E.getValidCount(); i++) {
            NSAL0990_ECMsg lineMsg = cMsg.E.no(i);

            if (!hasValue(lineMsg.ordCustUomQty_E) || lineMsg.ordCustUomQty_E.getValue().compareTo(BigDecimal.ZERO) == 0) {
                continue;
            }

            if (!preDsContrNum.equals(lineMsg.dsContrNum_E.getValue())) {
                // get DS_ACPO
                // mod start 2016/10/04 CSA Defect#12898
                dsAcpoNum = queryForDsCPOUpdate.getDsAcpo(cMsg.glblCmpyCd.getValue(), lineMsg.dsContrNum_E.getValue(), getSvcMachMstrPk(cMsg.dsContrCatgCd.getValue(), lineMsg.svcMachMstrPk_E.getValue()));
                // mod end 2016/10/04 CSA Defect#12898
                if (dsAcpoNum == null) {
                    // create DS_ACPO
                    dsAcpoNum = ZYPNumbering.getUniqueID(cMsg.glblCmpyCd.getValue(), DS_ACPO_NUM_SEQ);
                    // mod start 2016/10/04 CSA Defect#12898
                    if (!createDsAcpo(cMsg.glblCmpyCd.getValue(), dsAcpoNum, getDsContrDtlPk(cMsg.dsContrCatgCd.getValue(), lineMsg.dsContrDtlPk_E.getValue(), cMsg.dsContrDtlPk_FL.getValue()))) {
                        // mod end 2016/10/04 CSA Defect#12898
                        cMsg.setMessageInfo(NSAM0032E, new String[] {"DS_ACPO" });
                        return false;
                    }
                }
            }

            //get MDSE info
            MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(cMsg.glblCmpyCd.getValue(), lineMsg.mdseCd_E.getValue());
            // get DS_ACPO_TRGT_DTL
            // mod start 2016/10/04 CSA Defect#12898
            BigDecimal dsACpoDtlCount = queryForDsCPOUpdate.getDsAcpoDtl(cMsg.glblCmpyCd.getValue(), lineMsg.dsContrNum_E.getValue(), getSvcMachMstrPk(cMsg.dsContrCatgCd.getValue(), lineMsg.svcMachMstrPk_E.getValue()), mdseTMsg.mdseCd
                    .getValue());
            // mod end 2016/10/04 CSA Defect#12898
            if (dsACpoDtlCount.compareTo(BigDecimal.ZERO) == 0) {
                // create DS_ACPO_TRGT_DTL
                if (!createDsAcpoDtl(cMsg.glblCmpyCd.getValue(), dsAcpoNum, mdseTMsg.mdseCd.getValue())) {
                    cMsg.setMessageInfo(NSAM0032E, new String[] {"DS_ACPO_TRGT_DTL" });
                    return false;
                }
            }
            preDsContrNum = lineMsg.dsContrNum_E.getValue();
        }
        return true;
    }

    private boolean createDsAcpo(String glblCmpyCd, String dsAcpoNum, BigDecimal dsContrDtlPk) {
        DS_ACPOTMsg dsAcpoTmsg = new DS_ACPOTMsg();

        setValue(dsAcpoTmsg.glblCmpyCd, glblCmpyCd);
        setValue(dsAcpoTmsg.dsAcpoNum, dsAcpoNum);
        setValue(dsAcpoTmsg.dsContrDtlPk, dsContrDtlPk);
        setValue(dsAcpoTmsg.addDropShipFlg, ZYPConstant.FLG_OFF_N);

        S21ApiTBLAccessor.insert(dsAcpoTmsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsAcpoTmsg.getReturnCode())) {
            return false;
        }
        return true;
    }

    private boolean createDsAcpoDtl(String glblCmpyCd, String dsAcpoNum, String mdseCd) {
        DS_ACPO_TRGT_DTLTMsg dsAcpoDtlTmsg = new DS_ACPO_TRGT_DTLTMsg();

        String dsAcpoTrgeLineNum = queryForDsCPOUpdate.getNextDsAcpoTrgtLineNum(glblCmpyCd, dsAcpoNum);
        if (!hasValue(dsAcpoTrgeLineNum)) {
            dsAcpoTrgeLineNum = "001";
        }

        setValue(dsAcpoDtlTmsg.glblCmpyCd, glblCmpyCd);
        setValue(dsAcpoDtlTmsg.dsAcpoNum, dsAcpoNum);
        setValue(dsAcpoDtlTmsg.dsAcpoTrgtLineNum, dsAcpoTrgeLineNum);
        setValue(dsAcpoDtlTmsg.shipQty, BigDecimal.ZERO);
        setValue(dsAcpoDtlTmsg.mdseCd, mdseCd);
        setValue(dsAcpoDtlTmsg.trgtOrdQty, BigDecimal.ZERO);
        setValue(dsAcpoDtlTmsg.qtyCtrlNoLimitFlg, ZYPConstant.FLG_OFF_N);
        setValue(dsAcpoDtlTmsg.dropShipFlg, ZYPConstant.FLG_OFF_N);
        setValue(dsAcpoDtlTmsg.manPrcFlg, ZYPConstant.FLG_OFF_N);

        S21ApiTBLAccessor.insert(dsAcpoDtlTmsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsAcpoDtlTmsg.getReturnCode())) {
            return false;
        }
        return true;
    }

    // add start 2016/10/04 CSA Defect#12898
    private BigDecimal getDsContrDtlPk(String dsContrCatgCd, BigDecimal dsContrDtlPk, BigDecimal dsContrDtlPkFltLine) {
        if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
            return dsContrDtlPkFltLine;
        }
        return dsContrDtlPk;
    }

    private BigDecimal getSvcMachMstrPk(String dsContrCatgCd, BigDecimal svcMachMstrPk) {
        if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
            return null;
        }
        return svcMachMstrPk;
    }
    // add end 2016/10/04 CSA Defect#12898

    // START 2018/04/11 K.Kitachi [QC#11642, ADD]
    private void setCpoUpdApiReqContactInfo(NWZC150001PMsg cpoUpdApiReqPMsg, NSAL0990CMsg cMsg) {
        if (!hasValue(cMsg.ctacPsnPk_HD)) {
            return;
        }
        NWZC150001_cpoCtacPsnInfoListPMsg ctacInfo = cpoUpdApiReqPMsg.cpoCtacPsnInfoList.no(0);
        setValue(ctacInfo.xxRqstTpCd, NWZC150001Constant.RQST_TP_CTAC_PSN_NEW);
        setValue(ctacInfo.ctacPsnPk, cMsg.ctacPsnPk_HD);
        setValue(ctacInfo.ctacTpCd, CTAC_TP.ORDER_CONTACT);
        setValue(ctacInfo.firstNm, cMsg.ctacPsnFirstNm);
        setValue(ctacInfo.lastNm, cMsg.ctacPsnLastNm);
        setValue(ctacInfo.emlAddr, cMsg.ctacPsnEmlAddr);
        setValue(ctacInfo.ctacCustRefTpCd, CTAC_CUST_REF_TP.SHIP_TO);
        cpoUpdApiReqPMsg.cpoCtacPsnInfoList.setValidCount(1);
    }
    // END 2018/04/11 K.Kitachi [QC#11642, ADD]

    // START 2019/11/22 [QC#53125, ADD]
    private boolean isConfigHasValidOrderLine(NSAL0990CMsg cMsg, int startIdx, BigDecimal svcMachMstrPk) {
        for (int i = startIdx ; i < cMsg.E.getValidCount() ; i++) {
            final NSAL0990_ECMsg lineMsg = cMsg.E.no(i);
            if (hasValue(lineMsg.svcMachMstrPk_E) && svcMachMstrPk.compareTo(lineMsg.svcMachMstrPk_E.getValue()) != 0) {
                break;
            }
            if (hasValue(lineMsg.ordCustUomQty_E) && lineMsg.ordCustUomQty_E.getValue().compareTo(BigDecimal.ZERO) > 0) {
                return true;
            }
        }
        return false;
    }
    // END   2019/11/22 [QC#53125, ADD]
}
