/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NPZ.NPZC121001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.api.NPZ.NPZC121001.constant.NPZC121001Constant.*;

import java.math.BigDecimal;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import parts.common.EZDPItem;
import parts.common.EZDPMsg;
import parts.dbcommon.EZDConnectionMgr;
import business.db.CLICK_TECH_TRNSF_TOTMsg;
import business.db.DS_COND_CONSTTMsg;
import business.db.INVTYTMsg;
import business.db.RTL_SWHTMsg;
import business.db.RTL_SWHTMsgArray;
import business.db.TECH_LOCTMsg;
import business.db.TECH_LOCTMsgArray;
import business.parts.NLZC002001PMsg;
import business.parts.NLZC003001PMsg;
import business.parts.NPZC121001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC002001.NLZC002001;
import com.canon.cusa.s21.api.NLZ.NLZC003001.NLZC003001;
import com.canon.cusa.s21.common.NLC.NLCC001001.TRX_RSN;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.common.NSX.NSXC001001.SvcTimeZoneInfo;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ORD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_SVC_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * Tech to Tech Transfer API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/07/2015   Hitachi         K.Kasai         Create
 * 01/04/2016   Fujitsu         S.Nakai         Update          QC#2425
 * 02/12/2016   Hitachi         T.Iwamoto       Update          QC#3727
 * 02/22/2016   Hitachi         Y.Takeno        Update          QC#3727
 * 2017/01/05   Hitachi         K.Kojima        Update          QC#16301
 * 2017/02/21   Hitachi         A.Kohinata      Update          QC#16123
 * 2020/06/12   Hitachi         K.Kitachi       Update          QC#56501
 * 2022/04/20   Hitachi         K.Kitachi       Update          QC#59897
 * </pre>
 */
public class NPZC121001 extends S21ApiCommonBase {

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap = null;

    /** onBatTp */
    private ONBATCH_TYPE onBatType = null;

    /** slsDt */
    private String slsDt = null;

    /** dateFormat */
    private String dateFormat = null;

    /** trnsfSts */
    private String trnsfSts = null;

    /** reqStsSuccess */
    private String reqStsSuccess = null;

    /** reqStsFailure */
    private String reqStsFailure = null;

    // START 02/12/2016 T.Iwamoto [QC#3727, ADD]
    /** requestDateTime */
    private String rqstDtTm = null;
    // END 02/12/2016 T.Iwamoto [QC#3727, ADD]

    /**
     * Constructor
     */
    public NPZC121001() {
        super();
    }

    /**
     * Tech to Tech Transfer API
     * @param pMsg {@link NPZC121001PMsg}
     * @param onBatTp Online Batch Type
     */
    public void execute(NPZC121001PMsg pMsg, final ONBATCH_TYPE onBatTp) {

        init(pMsg, onBatTp);
        if (mandatoryCheck()) {
            if (valueCheck()) {
                doProcess(pMsg);
            }
        }
        setOutPrm(pMsg);
        // add start 2017/02/21 CSA Defect#16123
        // START 2022/04/20 K.Kitachi [QC#59897, DEL]
//        callMyInvtyAPI(pMsg);
        // END 2022/04/20 K.Kitachi [QC#59897, DEL]
        // add end 2017/02/21 CSA Defect#16123
        this.msgMap.flush();
    }

    private void init(NPZC121001PMsg pMsg, final ONBATCH_TYPE onBatTp) {

        this.msgMap = new S21ApiMessageMap(pMsg);

        this.onBatType = onBatTp;
        this.rqstDtTm = pMsg.xxRqstTs.getValue();

        this.slsDt = pMsg.slsDt.getValue();
        if (!ZYPCommonFunc.hasValue(this.slsDt)) {
            this.slsDt = ZYPDateUtil.getSalesDate(pMsg.glblCmpyCd.getValue());
        }

        dataSetUp(pMsg.glblCmpyCd.getValue());
        // START 02/12/2016 T.Iwamoto [QC#3727, ADD]
        if (!hasValue(convDateFormat(pMsg.xxRqstTs.getValue()))) {
            msgMap.addXxMsgIdWithPrm(ZZZM9026E, new String[] {MSG_PRM_REQUEST_DATE });
        }
        String techTocCd = getTechTocCd(pMsg);
// START 02/22/2016 Y.Takeno [QC#3727, MOD]
//        if (hasValue(techTocCd)) {
        if (hasValue(techTocCd) && hasValue(convDateFormat(pMsg.xxRqstTs.getValue()))) {
// END   02/32/2016 Y.Takeno [QC#3727, MOD]
            SvcTimeZoneInfo svcTz = NSXC001001SvcTimeZone.convertTimeRtlWh(NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, pMsg.xxRqstTs.getValue(), techTocCd);
            // START 2020/06/12 K.Kitachi [QC#56501, MOD]
//            this.rqstDtTm = svcTz.getDateTime().substring(0, pMsg.xxRqstTs.getValue().length());
            if (svcTz != null) {
                this.rqstDtTm = svcTz.getDateTime().substring(0, pMsg.xxRqstTs.getValue().length());
            }
            // END 2020/06/12 K.Kitachi [QC#56501, MOD]
        }
        // END 02/12/2016 T.Iwamoto [QC#3727, ADD]
    }

    private void dataSetUp(String glblCmpyCd) {
        setDataFormat(glblCmpyCd);
        setTransferStatus(glblCmpyCd);
        setRequestStatus(glblCmpyCd);
    }

    private void setDataFormat(String glblCmpyCd) {
        DS_COND_CONSTTMsg dsCondConstTMsg = getDsCondConstTMsg(glblCmpyCd, CONST_GRP_ID_CLICK_COMMON, CONST_GRP_ID_DATE_FORMAT);
        if (dsCondConstTMsg != null) {
            this.dateFormat = dsCondConstTMsg.dsCondConstValTxt_01.getValue();
        }
    }

    private void setTransferStatus(String glblCmpyCd) {
        DS_COND_CONSTTMsg dsCondConstTMsg = getDsCondConstTMsg(glblCmpyCd, CONST_GRP_ID_NPZC1210, CONST_CD_TRANSFER_STATUS);
        if (dsCondConstTMsg != null) {
            this.trnsfSts = dsCondConstTMsg.dsCondConstValTxt_05.getValue();
        }
    }

    private void setRequestStatus(String glblCmpyCd) {
        DS_COND_CONSTTMsg dsCondConstTMsg = getDsCondConstTMsg(glblCmpyCd, CONST_GRP_ID_NPZC1210, CONST_CD_REQUEST_STATUS);
        if (dsCondConstTMsg != null) {
            this.reqStsSuccess = dsCondConstTMsg.dsCondConstValTxt_01.getValue();
            this.reqStsFailure = dsCondConstTMsg.dsCondConstValTxt_02.getValue();
        }
    }

    private DS_COND_CONSTTMsg getDsCondConstTMsg(String glblCmpyCd, String dsCondConstGrpId, String dsCondConstCd) {
        DS_COND_CONSTTMsg dsCondConstTMsg = new DS_COND_CONSTTMsg();
        ZYPEZDItemValueSetter.setValue(dsCondConstTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsCondConstTMsg.dsCondConstGrpId, dsCondConstGrpId);
        ZYPEZDItemValueSetter.setValue(dsCondConstTMsg.dsCondConstCd, dsCondConstCd);
        return (DS_COND_CONSTTMsg) S21ApiTBLAccessor.findByKey(dsCondConstTMsg);
    }

    private boolean mandatoryCheck() {
        NPZC121001PMsg pMsg = (NPZC121001PMsg) this.msgMap.getPmsg();

        mandatoryCheck(pMsg.glblCmpyCd, ZZM9000E, MSG_PRM_GLBL_CMPY_CD);
        mandatoryCheck(pMsg.toInvtyLocCd, ZZM9000E, MSG_PRM_TO_INVTY_LOC_CD);
        mandatoryCheck(pMsg.mdseCd, ZZM9000E, MSG_PRM_MDSE_CD);
        mandatoryCheck(pMsg.shipQty, ZZM9000E, MSG_PRM_SHIP_QTY);
        mandatoryCheck(pMsg.fromInvtyLocCd, ZZM9000E, MSG_PRM_FROM_INVTY_LOC_CD);

        if (this.msgMap.getMsgMgr().isXxMsgId()) {
            return false;
        }
        return true;
    }

    private boolean valueCheck() {
        NPZC121001PMsg pMsg = (NPZC121001PMsg) this.msgMap.getPmsg();

        if (ZYPCommonFunc.hasValue(pMsg.fromInvtyLocCd.getValue()) && ZYPCommonFunc.hasValue(pMsg.toInvtyLocCd.getValue())) {
            invtyLocRelnCheck(pMsg.fromInvtyLocCd.getValue(), pMsg.toInvtyLocCd.getValue());
        }

        if (ZYPCommonFunc.hasValue(pMsg.shipQty.getValue())) {
            maxRangeCheck(pMsg);
            avalQtyCheck(pMsg);
        }
        if (this.msgMap.getMsgMgr().isXxMsgId()) {
            return false;
        }

        return true;
    }

    private void mandatoryCheck(EZDPItem targetItem, String messageId, String messageParam) {
        if (!hasValue(targetItem)) {
            setErrMsgInfo(messageId, new String[] {messageParam });
        }
    }

    private void invtyLocRelnCheck(String fromInvtyLocCd, String toInvtyLocCd) {
        if (fromInvtyLocCd.equals(toInvtyLocCd)) {
            setErrMsgInfo(NPZM0177E);
        }
    }

    private void maxRangeCheck(NPZC121001PMsg pmsg) {
        if (pmsg.shipQty.getValue().precision() > MAX_SHIP_QTY_DIGIT) {
            setErrMsgInfo(ZZM9002E, new String[] {MSG_PRM_SHIP_QTY });
        }
    }

    private void avalQtyCheck(NPZC121001PMsg pmsg) {
        INVTYTMsg tmsg = new INVTYTMsg();
        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, pmsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(tmsg.mdseCd, pmsg.mdseCd.getValue());
        ZYPEZDItemValueSetter.setValue(tmsg.invtyLocCd, pmsg.fromInvtyLocCd.getValue());
        ZYPEZDItemValueSetter.setValue(tmsg.locStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(tmsg.stkStsCd, STK_STS.GOOD);
        tmsg = (INVTYTMsg) S21ApiTBLAccessor.findByKey(tmsg);
        if (tmsg == null) {
            setErrMsgInfo(NPZM0178E);
        } else if (tmsg.invtyAvalQty.getValue().compareTo(BigDecimal.ZERO) < 0) {
            setErrMsgInfo(NPAM0070E, new String[] {MSG_PRM_INVTY_AVAL_QTY });
        } else if (tmsg.invtyAvalQty.getValue().compareTo(pmsg.shipQty.getValue()) < 0) {
            setErrMsgInfo(NPZM0176E);
        }
    }

    private String convDateFormat(String inDate) {

        if (!ZYPCommonFunc.hasValue(inDate)) {
            return null;
        }

        SimpleDateFormat inFormat = new SimpleDateFormat(this.dateFormat);
        SimpleDateFormat convFormat = new SimpleDateFormat(DATE_FORMAT);

        Date d = inFormat.parse(inDate, new ParsePosition(0));
// START 02/22/2016 Y.Takeno [QC#3727, MOD]
        if (d != null) {
            return convFormat.format(d);
        }

        return null;
// END   02/22/2016 Y.Takeno [QC#3727, MOD]
    }

    private void doProcess(NPZC121001PMsg pMsg) {
        // Inventory Order API
        NLZC003001PMsg nlzc003001PMsgHdr = createNLZC003001PMsgHdr(pMsg);
        NLZC003001PMsg nlzc003001PMsgDtl = createNLZC003001PMsgDtl(pMsg);
        NLZC003001 nlzc003001API = new NLZC003001();
        List<NLZC003001PMsg> nlzc003001PMsgList = new ArrayList<NLZC003001PMsg>(ARRAY_LIST_SIZE);
        nlzc003001PMsgList.add(nlzc003001PMsgHdr);
        nlzc003001PMsgList.add(nlzc003001PMsgDtl);
        nlzc003001API.execute(nlzc003001PMsgList, this.onBatType);
        if (hasError(pMsg, nlzc003001PMsgList)) {
            return;
        }

        // Inventory Update API (Stock Out)
        NLZC002001PMsg nlzc002001PMsgStkOut = createNLZC002001PMsgStkOut(pMsg, nlzc003001PMsgDtl.invtyOrdNum.getValue());
        NLZC002001 nlzc002001API = new NLZC002001();
        nlzc002001API.execute(nlzc002001PMsgStkOut, this.onBatType);
        if (hasError(pMsg, nlzc002001PMsgStkOut)) {
            return;
        }

        // Inventory Update API (Stock In)
        NLZC002001PMsg nlzc002001PMsgStkIn = createNLZC002001PMsgStkIn(pMsg, nlzc003001PMsgDtl.invtyOrdNum.getValue());
        nlzc002001API = new NLZC002001();
        nlzc002001API.execute(nlzc002001PMsgStkIn, this.onBatType);
        if (hasError(pMsg, nlzc002001PMsgStkIn)) {
            return;
        }
    }

    private NLZC003001PMsg createNLZC003001PMsgHdr(NPZC121001PMsg pMsg) {
        NLZC003001PMsg nlzc003001PMsg = new NLZC003001PMsg();
        ZYPEZDItemValueSetter.setValue(nlzc003001PMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(nlzc003001PMsg.xxProcTpCd, PRM_PROC_TP_CRAT);
        ZYPEZDItemValueSetter.setValue(nlzc003001PMsg.xxDtTpCd, PRM_DATE_TP_HDR);
        ZYPEZDItemValueSetter.setValue(nlzc003001PMsg.invtyOrdTpCd, INVTY_ORD_TP.INTERNAL_DC_TRANSFER);
        ZYPEZDItemValueSetter.setValue(nlzc003001PMsg.invtyLocCd, getRtlWhCd(pMsg.glblCmpyCd.getValue(), pMsg.fromInvtyLocCd.getValue()));
        ZYPEZDItemValueSetter.setValue(nlzc003001PMsg.locStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(nlzc003001PMsg.trxCd, TRX.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(nlzc003001PMsg.trxRsnCd, TRX_RSN.INTERNAL_WH_TRANSFER_STOCK_OUT);
        ZYPEZDItemValueSetter.setValue(nlzc003001PMsg.shpgSvcLvlCd, SHPG_SVC_LVL.GROUND_SERVICE);
        // START 02/12/2016 T.Iwamoto [QC#3727, ADD]
        ZYPEZDItemValueSetter.setValue(nlzc003001PMsg.dcTrnsfRsdDt, rqstDtTm.substring(0, POS_LEN_8));
        // END 02/12/2016 T.Iwamoto [QC#3727, ADD]
        ZYPEZDItemValueSetter.setValue(nlzc003001PMsg.dmgClmCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(nlzc003001PMsg.invtyOrdStsCd, INVTY_ORD_STS.CLOSED);
        ZYPEZDItemValueSetter.setValue(nlzc003001PMsg.trxSrcTpCd, TRX_SRC_TP.WAREHOUSE_TRANSFER);
        ZYPEZDItemValueSetter.setValue(nlzc003001PMsg.sysSrcCd, SYS_SRC.S21_LOGISTICS);
        ZYPEZDItemValueSetter.setValue(nlzc003001PMsg.xxRqstTs, ZYPDateUtil.getCurrentSystemTime(DATE_FORMAT));
        return nlzc003001PMsg;
    }

    private NLZC003001PMsg createNLZC003001PMsgDtl(NPZC121001PMsg pMsg) {
        NLZC003001PMsg nlzc003001PMsg = new NLZC003001PMsg();
        ZYPEZDItemValueSetter.setValue(nlzc003001PMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(nlzc003001PMsg.xxProcTpCd, PRM_PROC_TP_CRAT);
        ZYPEZDItemValueSetter.setValue(nlzc003001PMsg.xxDtTpCd, PRM_DATE_TP_DTL);
        ZYPEZDItemValueSetter.setValue(nlzc003001PMsg.invtyOrdTpCd, INVTY_ORD_TP.INTERNAL_DC_TRANSFER);
        ZYPEZDItemValueSetter.setValue(nlzc003001PMsg.invtyLocCd, pMsg.fromInvtyLocCd.getValue());
        ZYPEZDItemValueSetter.setValue(nlzc003001PMsg.invtyOrdLineNum, PRM_INVTY_ORD_LINE_NUM);
        ZYPEZDItemValueSetter.setValue(nlzc003001PMsg.mdseCd, pMsg.mdseCd.getValue());
        ZYPEZDItemValueSetter.setValue(nlzc003001PMsg.stkStsCd, STK_STS.GOOD);
        ZYPEZDItemValueSetter.setValue(nlzc003001PMsg.invtyLocCd_D1, pMsg.toInvtyLocCd.getValue());
        ZYPEZDItemValueSetter.setValue(nlzc003001PMsg.locStsCd_D1, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(nlzc003001PMsg.toStkStsCd, STK_STS.GOOD);
        ZYPEZDItemValueSetter.setValue(nlzc003001PMsg.ordQty, pMsg.shipQty.getValue());
        ZYPEZDItemValueSetter.setValue(nlzc003001PMsg.invtyOrdDtlStsCd, INVTY_ORD_STS.CLOSED);
        // START 02/12/2016 T.Iwamoto [QC#3727, ADD]
        ZYPEZDItemValueSetter.setValue(nlzc003001PMsg.xxRqstTs, rqstDtTm);
        // END 02/12/2016 T.Iwamoto [QC#3727, ADD]
        return nlzc003001PMsg;
    }

    private NLZC002001PMsg createNLZC002001PMsgStkOut(NPZC121001PMsg pMsg, String invtyOrdNum) {
        NLZC002001PMsg nlzc002001PMsg = new NLZC002001PMsg();
        ZYPEZDItemValueSetter.setValue(nlzc002001PMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(nlzc002001PMsg.mdseCd, pMsg.mdseCd.getValue());
        ZYPEZDItemValueSetter.setValue(nlzc002001PMsg.invtyLocCd, pMsg.fromInvtyLocCd.getValue());
        ZYPEZDItemValueSetter.setValue(nlzc002001PMsg.locStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(nlzc002001PMsg.stkStsCd, STK_STS.GOOD);
        ZYPEZDItemValueSetter.setValue(nlzc002001PMsg.xxRqstQty, pMsg.shipQty.getValue());
        ZYPEZDItemValueSetter.setValue(nlzc002001PMsg.trxCd, TRX.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(nlzc002001PMsg.trxRsnCd, TRX_RSN.INTERNAL_WH_TRANSFER_STOCK_OUT);
        ZYPEZDItemValueSetter.setValue(nlzc002001PMsg.invtyTrxDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(nlzc002001PMsg.sysSrcCd, SYS_SRC.S21_LOGISTICS);
        ZYPEZDItemValueSetter.setValue(nlzc002001PMsg.invtyOrdNum, invtyOrdNum);
        ZYPEZDItemValueSetter.setValue(nlzc002001PMsg.invtyOrdLineNum, PRM_INVTY_ORD_LINE_NUM);
        ZYPEZDItemValueSetter.setValue(nlzc002001PMsg.shipToCustCd, pMsg.toInvtyLocCd.getValue());
        ZYPEZDItemValueSetter.setValue(nlzc002001PMsg.shipToCustNm, getLocNm(pMsg.glblCmpyCd.getValue(), pMsg.toInvtyLocCd.getValue()));
        ZYPEZDItemValueSetter.setValue(nlzc002001PMsg.shipFromLocCustCd, pMsg.fromInvtyLocCd.getValue());
        ZYPEZDItemValueSetter.setValue(nlzc002001PMsg.uomCd, PKG_UOM.EACH);
        ZYPEZDItemValueSetter.setValue(nlzc002001PMsg.xxRqstTpCd, PRM_RQST_STOCK_OUT);
        ZYPEZDItemValueSetter.setValue(nlzc002001PMsg.xxSysTp, SYS_TP_OTBD);
        ZYPEZDItemValueSetter.setValue(nlzc002001PMsg.xxTrxDtlCd, TRX_DTL_SHIP_CONF);
        return nlzc002001PMsg;
    }

    private NLZC002001PMsg createNLZC002001PMsgStkIn(NPZC121001PMsg pMsg, String invtyOrdNum) {
        NLZC002001PMsg nlzc002001PMsg = new NLZC002001PMsg();
        ZYPEZDItemValueSetter.setValue(nlzc002001PMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(nlzc002001PMsg.mdseCd, pMsg.mdseCd.getValue());
        ZYPEZDItemValueSetter.setValue(nlzc002001PMsg.invtyLocCd, pMsg.toInvtyLocCd.getValue());
        ZYPEZDItemValueSetter.setValue(nlzc002001PMsg.locStsCd, LOC_STS.DC_STOCK);
        ZYPEZDItemValueSetter.setValue(nlzc002001PMsg.stkStsCd, STK_STS.GOOD);
        ZYPEZDItemValueSetter.setValue(nlzc002001PMsg.xxRqstQty, pMsg.shipQty.getValue());
        ZYPEZDItemValueSetter.setValue(nlzc002001PMsg.trxCd, TRX.MOVEMENT);
        ZYPEZDItemValueSetter.setValue(nlzc002001PMsg.trxRsnCd, TRX_RSN.INTERNAL_WH_TRANSFER_STOCK_IN);
        ZYPEZDItemValueSetter.setValue(nlzc002001PMsg.invtyTrxDt, this.slsDt);
        ZYPEZDItemValueSetter.setValue(nlzc002001PMsg.sysSrcCd, SYS_SRC.S21_LOGISTICS);
        ZYPEZDItemValueSetter.setValue(nlzc002001PMsg.invtyOrdNum, invtyOrdNum);
        ZYPEZDItemValueSetter.setValue(nlzc002001PMsg.invtyOrdLineNum, PRM_INVTY_ORD_LINE_NUM);
        ZYPEZDItemValueSetter.setValue(nlzc002001PMsg.shipFromLocCustCd, pMsg.fromInvtyLocCd.getValue());
        ZYPEZDItemValueSetter.setValue(nlzc002001PMsg.uomCd, PKG_UOM.EACH);
        ZYPEZDItemValueSetter.setValue(nlzc002001PMsg.xxRqstTpCd, PRM_RQST_STOCK_IN);
        ZYPEZDItemValueSetter.setValue(nlzc002001PMsg.xxSysTp, SYS_TP_INBD);
        ZYPEZDItemValueSetter.setValue(nlzc002001PMsg.xxTrxDtlCd, TRX_DTL_RCV_CONF);
        return nlzc002001PMsg;
    }

    private void setOutPrm(NPZC121001PMsg pMsg) {
        ZYPEZDItemValueSetter.setValue(pMsg.techProcStsDescTxt, trnsfSts);
        if (pMsg.xxMsgIdList.getValidCount() > 0) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxRtrnCd, reqStsFailure);
            if (ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
                // START 2017/01/05 K.Kojima [QC#16301,ADD]
                // This API is called directly from Clicksoft that will not control any transactions in S21.
                // Therefore, Commit / Rollback is necessary in this API although it violates the implementation rule.
                EZDConnectionMgr.getInstance().rollback();
                // END 2017/01/05 K.Kojima [QC#16301,ADD]
                insertClickTechTrnsfTo(pMsg, reqStsFailure);
            }
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.xxRtrnCd, reqStsSuccess);
            if (ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
                insertClickTechTrnsfTo(pMsg, reqStsSuccess);
            }
        }
    }

    private String getRtlWhCd(String glblCmpyCd, String invtyLocCd) {
        RTL_SWHTMsg tmsg = new RTL_SWHTMsg();
        RTL_SWHTMsgArray tmsgAryRes;
        tmsg.setSQLID("001");
        tmsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tmsg.setConditionValue("invtyLocCd01", invtyLocCd);
        tmsg.setConditionValue("effFromDt01", this.slsDt);
        tmsg.setConditionValue("effThruDt01", this.slsDt);
        tmsgAryRes = (RTL_SWHTMsgArray) S21ApiTBLAccessor.findByCondition(tmsg);
        if (tmsgAryRes.getValidCount() == 0) {
            return null;
        }
        return tmsgAryRes.no(0).rtlWhCd.getValue();
    }

    private String getLocNm(String glblCmpyCd, String invtyLocCd) {
        TECH_LOCTMsg tmsg = new TECH_LOCTMsg();
        TECH_LOCTMsgArray tmsgAryRes;
        tmsg.setSQLID("003");
        tmsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tmsg.setConditionValue("techLocCd01", invtyLocCd);
        tmsgAryRes = (TECH_LOCTMsgArray) S21ApiTBLAccessor.findByCondition(tmsg);
        if (tmsgAryRes.getValidCount() == 0) {
            return null;
        }
        return tmsgAryRes.no(0).locNm.getValue();
    }

    // START 02/12/2016 T.Iwamoto [QC#3727, ADD]
    private String getTechTocCd(NPZC121001PMsg pMsg) {
        TECH_LOCTMsg inMsg = new TECH_LOCTMsg();
        inMsg.setSQLID("003");
        inMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        // START 2020/06/12 K.Kitachi [QC#56501, MOD]
//        inMsg.setConditionValue("techLocCd01", pMsg.fromInvtyLocCd.getValue());
        inMsg.setConditionValue("techLocCd01", pMsg.toInvtyLocCd.getValue());
        // END 2020/06/12 K.Kitachi [QC#56501, MOD]
        TECH_LOCTMsgArray outArray = (TECH_LOCTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        String techTocCd = null;
        if (outArray.getValidCount() > 0) {
            techTocCd = outArray.no(0).techTocCd.getValue();
        }
        return techTocCd;
    }
    // END 02/12/2016 T.Iwamoto [QC#3727, ADD]

    private boolean hasError(NPZC121001PMsg pMsg, List<NLZC003001PMsg> callApiPMsgList) {
        boolean result = false;
        for (EZDPMsg callApiPMsg : callApiPMsgList) {
            if (hasError(pMsg, callApiPMsg)) {
                result = true;
            }
        }
        return result;
    }

    private boolean hasError(NPZC121001PMsg pMsg, EZDPMsg callApiPMsg) {
        boolean result = false;
        List<String> errList = S21ApiUtil.getXxMsgIdList(callApiPMsg);
        if (errList.size() > 0) {
            result = true;
            for (String msgId : errList) {
                setErrMsgInfo(msgId);
            }
        }
        return result;
    }

    private void setErrMsgInfo(String msgId, String[] msgPrm) {
        String msgInfo = S21MessageFunc.clspGetMessage(msgId, msgPrm);
        this.msgMap.addXxMsgIdWithPrm(msgId, new String[] {msgInfo });
    }

    private void setErrMsgInfo(String msgId) {
        String msgInfo = S21MessageFunc.clspGetMessage(msgId);
        this.msgMap.addXxMsgIdWithPrm(msgId, new String[] {msgInfo });
    }
    private void mandatoryCheck(S21ApiMessageMap msgMap, EZDPItem targetItem, String messageId) {
        if (!hasValue(targetItem)) {
            msgMap.addXxMsgId(messageId);
        }
    }
    private boolean insertClickTechTrnsfTo(NPZC121001PMsg pmsg, String techTrnsfRtrnStsTxt) {
        CLICK_TECH_TRNSF_TOTMsg clickTechTrnsfToTMsg = new CLICK_TECH_TRNSF_TOTMsg();
        BigDecimal clickTechTrnsfToPk = ZYPOracleSeqAccessor.getNumberBigDecimal("CLICK_TECH_TRNSF_TO_SQ");
        setValue(clickTechTrnsfToTMsg.glblCmpyCd, pmsg.glblCmpyCd.getValue());
        setValue(clickTechTrnsfToTMsg.clickTechTrnsfToPk, clickTechTrnsfToPk);
        setValue(clickTechTrnsfToTMsg.toInvtyLocCd, pmsg.toInvtyLocCd.getValue());
        // START 02/12/2016 T.Iwamoto [QC#3727, ADD]
        setValue(clickTechTrnsfToTMsg.techTrnsfRqstTs, rqstDtTm);
        // END 02/12/2016 T.Iwamoto [QC#3727, ADD]
        setValue(clickTechTrnsfToTMsg.mdseCd , pmsg.mdseCd.getValue());
        setValue(clickTechTrnsfToTMsg.mdseDescShortTxt, pmsg.mdseNm.getValue());
        setValue(clickTechTrnsfToTMsg.shipQty, pmsg.shipQty.getValue());
        setValue(clickTechTrnsfToTMsg.fromInvtyLocCd, pmsg.fromInvtyLocCd.getValue());
        setValue(clickTechTrnsfToTMsg.techProcStsDescTxt, pmsg.techProcStsDescTxt.getValue());
        setValue(clickTechTrnsfToTMsg.serNum, pmsg.serNum.getValue());
        setValue(clickTechTrnsfToTMsg.techTrnsfRtrnStsTxt, techTrnsfRtrnStsTxt);
        setValue(clickTechTrnsfToTMsg.procStsCd, PROC_STS.IN_COMPLETED);
        setValue(clickTechTrnsfToTMsg.clickKeyTxt, pmsg.clickKeyTxt.getValue());

        S21FastTBLAccessor.insert(clickTechTrnsfToTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(clickTechTrnsfToTMsg.getReturnCode())) {
            S21ApiMessageMap msgMap = new S21ApiMessageMap(pmsg);
            msgMap.addXxMsgId(NPZM0209E);
            msgMap.flush();
            return false;
        }
        // START 2017/01/05 K.Kojima [QC#16301,ADD]
        // This API is called directly from Clicksoft that will not control any transactions in S21.
        // Therefore, Commit / Rollback is necessary in this API although it violates the implementation rule.
        EZDConnectionMgr.getInstance().commit();
        // END 2017/01/05 K.Kojima [QC#16301,ADD]
        return true;
    }

    // add start 2017/02/21 CSA Defect#16123
    // START 2022/04/20 K.Kitachi [QC#59897, DEL]
//    private void callMyInvtyAPI(NPZC121001PMsg pMsg) {
//        if (pMsg.xxMsgIdList.getValidCount() > 0) {
//            return;
//        }
//
//        // Call Send My Inventory to Click API
//        List<String> invtyLocCdList = new ArrayList<String>();
//        invtyLocCdList.add(pMsg.fromInvtyLocCd.getValue());
//        invtyLocCdList.add(pMsg.toInvtyLocCd.getValue());
//
//        for (String invtyLocCd : invtyLocCdList) {
//            NLZC410001PMsg apiPMsg = new NLZC410001PMsg();
//            setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
//            setValue(apiPMsg.procDt, this.slsDt);
//            setValue(apiPMsg.xxModeCd, NLZC410001Constant.MODE_DAILIY);
//            setValue(apiPMsg.mdseCd, pMsg.mdseCd);
//            setValue(apiPMsg.invtyLocCd, invtyLocCd);
//            NLZC410001 api = new NLZC410001();
//            api.execute(apiPMsg, this.onBatType);
//            if (hasError(pMsg, apiPMsg)) {
//                EZDConnectionMgr.getInstance().rollback();
//                return;
//            }
//        }
//        EZDConnectionMgr.getInstance().commit();
//    }
    // END 2022/04/20 K.Kitachi [QC#59897, DEL]
    // add end 2017/02/21 CSA Defect#16123
}
