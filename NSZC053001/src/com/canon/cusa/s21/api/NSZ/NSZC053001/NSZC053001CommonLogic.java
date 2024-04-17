/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC053001;

import static com.canon.cusa.s21.api.NSZ.NSZC053001.constant.NSZC053001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleTypes;

import parts.common.EZDCommonConst;
import parts.common.EZDMsg;
import parts.common.EZDPItem;
import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDTBLAccessor;
import business.db.AUTH_PSNTMsg;
import business.db.AUTH_PSNTMsgArray;
import business.db.CONTR_XS_COPYTMsg;
import business.db.CONTR_XS_COPYTMsgArray;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsgArray;
import business.db.DS_CONTR_BLLG_SCHDTMsg;
import business.db.DS_CONTR_BLLG_SCHD_MTRTMsg;
import business.db.DS_CONTR_BLLG_SCHD_SMRYTMsg;
import business.db.DS_CONTR_BLLG_SCHD_SMRYTMsgArray;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_DTL_TPTMsg;
import business.db.DS_CONTR_PRC_EFFTMsg;
import business.db.DS_CONTR_PRC_EFFTMsgArray;
import business.db.DS_CONTR_PRC_EFF_MTRTMsg;
import business.db.DS_CONTR_PRC_EFF_MTRTMsgArray;
import business.db.SVC_CONTR_BLLGTMsg;
import business.db.SVC_CR_REBILTMsg;
import business.db.SVC_CR_REBIL_ADDL_BLLGTMsg;
import business.db.SVC_CR_REBIL_BASE_BLLGTMsg;
import business.db.SVC_CR_REBIL_BASE_BLLGTMsgArray;
import business.db.SVC_CR_REBIL_DTLTMsg;
import business.db.SVC_CR_REBIL_DTLTMsgArray;
import business.db.SVC_CR_REBIL_MTR_BLLGTMsg;
import business.db.SVC_CR_REBIL_MTR_BLLGTMsgArray;
import business.db.SVC_CR_REBIL_MTR_READTMsg;
import business.db.SVC_CR_REBIL_XS_MTR_BLLGTMsg;
import business.db.SVC_CR_REBIL_XS_MTR_BLLGTMsgArray;
import business.db.SVC_INVTMsg;
import business.db.SVC_MEMOTMsg;
import business.db.SVC_PHYS_MTR_READTMsg;
import business.parts.NSZC034001PMsg;
import business.parts.NSZC035001PMsg;
import business.parts.NSZC047007PMsg;
import business.parts.NSZC047008PMsg;
import business.parts.NSZC047008_xxBaseLineListPMsg;
import business.parts.NSZC047008_xxMtrLineListPMsg;
import business.parts.NSZC047011PMsg;
import business.parts.NSZC053001PMsg;
import business.parts.NSZC053001_xxCrRebilDtlListPMsg;
import business.parts.NSZC053002_xxBaseChangesListPMsg;
import business.parts.NSZC053002_xxMeterReadChangesListPMsg;
import business.parts.NSZC053002_xxPriceChangesListPMsg;
import business.parts.NSZC056001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC034001.NSZC034001;
import com.canon.cusa.s21.api.NSZ.NSZC035001.NSZC035001;
import com.canon.cusa.s21.api.NSZ.NSZC047001.NSZC047001;
import com.canon.cusa.s21.api.NSZ.NSZC056001.NSZC056001;
import com.canon.cusa.s21.api.NSZ.NSZC077001.ContrTrkProcMode;
import com.canon.cusa.s21.common.NSX.NSXC001001.AddlChrgFromThruDtInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.CalcAddlChrgInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001CalcAddlChrg;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContractTracking;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetAddlChrgFromThruDt;
import com.canon.cusa.s21.common.NSX.NSXC003001.CalcSchdSmryTermAndAmtBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001CalcSchdSmryTermAndAmt;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ADDL_CHRG_INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_FMLA_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CR_REBIL_PROC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CR_REBIL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContext;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContextFactory;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfSystemException;
import com.canon.cusa.s21.framework.nwf.core.process.S21NwfProcess;
import com.canon.cusa.s21.framework.nwf.util.bizapi.S21NwfUtilBizHistWorkItem;
import com.canon.cusa.s21.framework.nwf.util.bizapi.S21NwfUtilBizProcess;
import com.canon.cusa.s21.framework.nwf.util.common.S21NwfUtilContextFactory;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;


/**
 * <pre>
 * Credit Rebill Update API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/01/2016   Hitachi         T.Aoyagi        Create          N/A
 * 03/25/2016   Hitachi         T.Aoyagi        Update          QC#5435
 * 04/06/2016   Hitachi         T.Aoyagi        Update          QC#5963
 * 04/07/2016   Hitachi         T.Aoyagi        Update          QC#6647
 * 04/08/2016   Hitachi         T.Aoyagi        Update          QC#6762
 * 04/11/2016   Hitachi         T.Aoyagi        Update          QC#6715
 * 04/11/2016   Hitachi         T.Aoyagi        Update          QC#6510
 * 04/13/2016   Hitachi         K.Yamada        Update          QC#6512
 * 04/18/2016   Hitachi         T.Aoyagi        Update          QC#7056
 * 04/26/2016   Hitachi         T.Aoyagi        Update          QC#7578
 * 05/09/2016   Hitachi         T.Aoyagi        Update          QC#7618
 * 05/10/2016   Hitachi         T.Aoyagi        Update          QC#7734
 * 05/11/2016   Hitachi         K.Kishimoto     Update          QC#7764
 * 05/12/2016   Hitachi         K.Kishimoto     Update          QC#8183
 * 06/06/2016   Hitachi         T.Tomita        Update          QC#1523, 4624
 * 09/12/2016   Hitachi         K.Kishimoto     Update          QC#5566
 * 12/14/2016   Hitachi         Y.Takeno        Update          QC#16285
 * 01/24/2017   Hitachi         A.Kohinata      Update          QC#17261
 * 02/22/2017   Hitachi         Y.Takeno        Update          QC#16285-1
 * 09/25/2017   Hitachi         K.Yamada        Update          QC#21219
 * 2017/09/26   Hitachi         K.Kitachi       Update          QC#21212
 * 2017/10/05   Hitachi         K.Kim           Update          QC#21219-1
 * 2017/10/13   Hitachi         K.Kim           Update          QC#21775
 * 2017/10/13   Hitachi         E.Kameishi      Update          QC#18636
 * 2017/10/13   Hitachi         M.Kidokoro      Update          QC#21792
 * 2017/10/17   Hitachi         K.Kojima        Update          QC#21795
 * 2017/10/20   Hitachi         K.Kitachi       Update          QC#21927
 * 2017/10/23   Hitachi         K.Kitachi       Update          QC#21966
 * 2017/10/23   Hitachi         K.Ochiai        Update          QC#22009
 * 2017/10/17   Hitachi         T.Tomita        Update          QC#21792
 * 2017/10/19   Hitachi         T.Tomita        Update          QC#21815
 * 2017/10/25   Hitachi         T.Tomita        Update          QC#21815
 * 2017/10/25   Hitachi         K.Yamada        Update          QC#22301
 * 2017/11/08   Hitachi         K.Kojima        Update          QC#22301
 * 2018/01/11   Hitachi         K.Ochiai        Update          QC#23420
 * 2018/03/16   Hitachi         K.Kojima        Update          QC#24497
 * 2018/03/27   CITS            M.Naito         Update          QC#18672
 * 2018/04/20   Hitachi         K.Kojima        Update          QC#25595
 * 2018/05/14   Hitachi         T.Tomita        Update          QC#24878
 * 2018/06/05   Hitachi         K.Kojima        Update          QC#21974
 * 2018/06/14   Fujitsu         T.Ogura         Update          QC#22382
 * 2018/06/25   Hitachi         K.Kitachi       Update          QC#22245
 * 2018/06/29   Hitachi         K.Kitachi       Update          QC#22057
 * 2018/07/18   Hitachi         K.Kojima        Update          QC#26791
 * 2018/08/27   Hitachi         K.Kishimoto     Update          QC#24555
 * 2018/08/31   Hitachi         K.Kojima        Update          QC#27960
 * 2018/09/05   Hitachi         K.Kishimoto     Update          QC#24555
 * 2018/10/25   Hitachi         K.Kojima        Update          QC#28997
 * 2019/07/18   Hitachi         K.Kishimoto     Update          QC#51706
 * 2019/11/01   Hitachi         K.Kim           Update          QC#54419
 * 2020/01/08   Hitachi         K.Kim           Update          QC#55170
 * 2020/03/18   Hitachi         K.Kitachi       Update          QC#55693
 * 2020/06/03   Hitachi         K.Yamada        Update          QC#56945
 * 2021/04/05   CITS            T.Wada          Update          QC#58177-2
 * 2021/04/27   CITS            T.Wada          Update          QC#58177-3
 * 2021/05/19   CITS            T.Wada          Update          QC#58177-4
 * 2021/06/28   CITS            T.Wada          Update          QC#58177-5
 * 2022/04/29   CITS            E.Sanchez       Update          QC#59914-1
 * 2022/05/18   Hitachi         K.Kitachi       Update          QC#60054
 * 2022/05/27   Hitachi         K.Kitachi       Update          QC#60121
 * 2022/06/14   Hitachi         K.Kitachi       Update          QC#60160
 * 2022/09/27   CITS            L.Mandanas      Update          QC#58427
 * 2022/10/23   CITS            T.Suzuki        Update          QC#58427
 * 2023/01/16   CITS            R.Jin           Update          QC#58890
 * 2023/04/14   CITS            R.Jin           Update          QC#58890-2
 * </pre>
 */
public class NSZC053001CommonLogic implements ZYPConstant {

    /** NSZC0530Query */
    private static NSZC0530Query query = NSZC0530Query.getInstance();

    /**
     * @param msgMap S21ApiMessageMap
     */
    public static void checkParameter(S21ApiMessageMap msgMap) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        mandatoryCheck(msgMap, pMsg.glblCmpyCd, NSZM0001E);
        mandatoryCheck(msgMap, pMsg.xxModeCd, NSZM0003E);
        mandatoryCheck(msgMap, pMsg.slsDt, NSZM0002E);
        if (msgMap.getMsgMgr().isXxMsgId()) {
            return;
        }
        String mode = pMsg.xxModeCd.getValue();
        if (MODE_CI_ENTRY.equals(mode)) {
            checkCIEntry(msgMap);

        } else if (MODE_CI_UPDATE.equals(mode)) {
            checkCIUpdate(msgMap);

        } else if (MODE_SUBMIT_FOR_APPROVAL.equals(mode)) {
            checkSubmitForApproval(msgMap);

        } else if (MODE_CI_CANCEL.equals(mode)) {
            checkCICancel(msgMap);

        } else if (MODE_OVERRIDE_APPROVER.equals(mode)) {
            // START 03/25/2016 T.Aoyagi [QC#5435, MOD]
            checkOverrideApprover(msgMap);
            // END 03/25/2016 T.Aoyagi [QC#5435, MOD]

        } else if (MODE_WORK_FLOW_APPROVE.equals(mode)) {
            checkWorkFlowApprove(msgMap);

        } else if (MODE_CI_REJECT.equals(mode)) {
            checkWorkFlowApprove(msgMap);

        } else if (MODE_INVOICING_FROM_BILLING.equals(mode)) {
            checkInvoicingFromBilling(msgMap);

        } else {
            msgMap.addXxMsgId(NSZM0039E);
        }
    }

    private static void checkCIEntry(S21ApiMessageMap msgMap) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        mandatoryCheck(msgMap, pMsg.custIncdtId, NSZM0902E);
        mandatoryCheck(msgMap, pMsg.custIncdtLineId, NSZM0903E);
        mandatoryCheck(msgMap, pMsg.svcCrRebilSrcNm, NSZM0919E);
        mandatoryCheck(msgMap, pMsg.custCareRgtnPsnCd, NSZM0901E);
        // START 04/07/2016 T.Aoyagi [QC#6647, ADD]
        mandatoryCheck(msgMap, pMsg.svcContrBrCd, NSZM0959E);
        mandatoryCheck(msgMap, pMsg.custIncdtAsgPsnCd, NSZM0960E);
        // END 04/07/2016 T.Aoyagi [QC#6647, ADD]

        for (int i = 0; i < pMsg.xxCrRebilDtlList.getValidCount(); i++) {

            NSZC053001_xxCrRebilDtlListPMsg inPMsg = pMsg.xxCrRebilDtlList.no(i);
            mandatoryCheck(msgMap, inPMsg.origSvcInvNum, NSZM0911E);

            // Meter Read
            //START 2017/09/19 E.Kameishi [QC#18636,MOD]
            if (hasValues(inPMsg.startMtrReadDt, inPMsg.endMtrReadDt, inPMsg.physMtrLbCd
                        , inPMsg.newStartReadMtrCnt, inPMsg.newEndReadMtrCnt, inPMsg.newTestMtrCnt)) {

            //END 2017/09/19 E.Kameishi [QC#18636,MOD]
                // START 04/18/2016 T.Aoyagi [QC#7056, DEL]
//                mandatoryCheck(msgMap, inPMsg.bllgMtrLbCd, NSZM0899E);
                // END 04/18/2016 T.Aoyagi [QC#7056, DEL]
                mandatoryCheck(msgMap, inPMsg.startMtrReadDt, NSZM0920E);
                mandatoryCheck(msgMap, inPMsg.endMtrReadDt, NSZM0904E);
                mandatoryCheck(msgMap, inPMsg.physMtrLbCd, NSZM0912E);
            }

            // Xs Meter
            if (hasValues(inPMsg.mtrBllgFromDt, inPMsg.mtrBllgThruDt
                        , inPMsg.oldXsCopyQty, inPMsg.oldXsMtrAmtRate, inPMsg.newXsCopyQty, inPMsg.newXsMtrAmtRate)) {

                mandatoryCheck(msgMap, inPMsg.bllgMtrLbCd, NSZM0899E);
                mandatoryCheck(msgMap, inPMsg.mtrBllgFromDt, NSZM0906E);
                mandatoryCheck(msgMap, inPMsg.mtrBllgThruDt, NSZM0907E);

                // add start 09/25/2017 QC#21219
                if (!hasValue(inPMsg.oldXsCopyQty) || !hasValue(inPMsg.oldXsMtrAmtRate)) {
                    List<Map<String, BigDecimal>> origInvXsInfo = query.fillupParameter(msgMap, inPMsg);
                    if (origInvXsInfo.size() > 0) {
                        if (!hasValue(inPMsg.oldXsCopyQty)) {
                            // START 2017/10/05 K.Kim [QC#21219-1, MOD]
                            // setValue(inPMsg.oldXsCopyQty, origInvXsInfo.get(0).get("XS_MTR_FROM_COPY_QTY"));
                            setValue(inPMsg.oldXsCopyQty, origInvXsInfo.get(0).get("XS_MTR_COPY_QTY"));
                            // END 2017/10/05 K.Kim [QC#21219-1, MOD]
                        }
                        if (!hasValue(inPMsg.oldXsMtrAmtRate)) {
                            setValue(inPMsg.oldXsMtrAmtRate, origInvXsInfo.get(0).get("XS_MTR_AMT_RATE"));
                        }
                    }
                }
                // add end 09/25/2017 QC#21219

                mandatoryCheck(msgMap, inPMsg.oldXsCopyQty, NSZM0909E);
                mandatoryCheck(msgMap, inPMsg.oldXsMtrAmtRate, NSZM0910E);
            }

            // Base Charge
            if (hasValues(inPMsg.baseBllgFromDt, inPMsg.baseBllgThruDt, inPMsg.newBaseDealAmt)) {

                mandatoryCheck(msgMap, inPMsg.baseBllgFromDt, NSZM0897E);
                mandatoryCheck(msgMap, inPMsg.baseBllgThruDt, NSZM0898E);
                mandatoryCheck(msgMap, inPMsg.newBaseDealAmt, NSZM0908E);
            }
        }
    }

    private static void checkCIUpdate(S21ApiMessageMap msgMap) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        mandatoryCheck(msgMap, pMsg.svcCrRebilPk, NSZM0915E);

        for (int i = 0; i < pMsg.xxCrRebilDtlList.getValidCount(); i++) {

            NSZC053001_xxCrRebilDtlListPMsg inPMsg = pMsg.xxCrRebilDtlList.no(i);
            mandatoryCheck(msgMap, inPMsg.origSvcInvNum, NSZM0911E);

            // Meter Read
            //START 2017/09/19 E.Kameishi [QC#18636,MOD]
            if (hasValues(inPMsg.startMtrReadDt, inPMsg.endMtrReadDt, inPMsg.physMtrLbCd
                        , inPMsg.newStartReadMtrCnt, inPMsg.newEndReadMtrCnt, inPMsg.newTestMtrCnt)) {
            //END 2017/09/19 E.Kameishi [QC#18636,MOD]

                // START 04/18/2016 T.Aoyagi [QC#7056, DEL]
//                mandatoryCheck(msgMap, inPMsg.bllgMtrLbCd, NSZM0899E);
                // END 04/18/2016 T.Aoyagi [QC#7056, DEL]
                mandatoryCheck(msgMap, inPMsg.startMtrReadDt, NSZM0920E);
                mandatoryCheck(msgMap, inPMsg.endMtrReadDt, NSZM0904E);
                mandatoryCheck(msgMap, inPMsg.physMtrLbCd, NSZM0912E);
            }

            // Xs Meter
            if (hasValues(inPMsg.mtrBllgFromDt, inPMsg.mtrBllgThruDt
                        , inPMsg.oldXsCopyQty, inPMsg.oldXsMtrAmtRate, inPMsg.newXsCopyQty, inPMsg.newXsMtrAmtRate)) {

                mandatoryCheck(msgMap, inPMsg.bllgMtrLbCd, NSZM0899E);
                mandatoryCheck(msgMap, inPMsg.mtrBllgFromDt, NSZM0906E);
                mandatoryCheck(msgMap, inPMsg.mtrBllgThruDt, NSZM0907E);

                // add start 09/25/2017 QC#21219
                if (!hasValue(inPMsg.oldXsCopyQty) || !hasValue(inPMsg.oldXsMtrAmtRate)) {
                    List<Map<String, BigDecimal>> origInvXsInfo = query.fillupParameter(msgMap, inPMsg);
                    if (origInvXsInfo.size() > 0) {
                        if (!hasValue(inPMsg.oldXsCopyQty)) {
                            setValue(inPMsg.oldXsCopyQty, origInvXsInfo.get(0).get("XS_MTR_COPY_QTY"));
                        }
                        if (!hasValue(inPMsg.oldXsMtrAmtRate)) {
                            setValue(inPMsg.oldXsMtrAmtRate, origInvXsInfo.get(0).get("XS_MTR_AMT_RATE"));
                        }
                    }
                }
                // add end 09/25/2017 QC#21219

                mandatoryCheck(msgMap, inPMsg.oldXsCopyQty, NSZM0909E);
                mandatoryCheck(msgMap, inPMsg.oldXsMtrAmtRate, NSZM0910E);
            }

            // Base Charge
            if (hasValues(inPMsg.baseBllgFromDt, inPMsg.baseBllgThruDt, inPMsg.newBaseDealAmt)) {

                mandatoryCheck(msgMap, inPMsg.baseBllgFromDt, NSZM0897E);
                mandatoryCheck(msgMap, inPMsg.baseBllgThruDt, NSZM0898E);
                mandatoryCheck(msgMap, inPMsg.newBaseDealAmt, NSZM0908E);
            }
        }
    }

    private static void checkCICancel(S21ApiMessageMap msgMap) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        if (!hasValue(pMsg.svcCrRebilPk)) {
            mandatoryCheck(msgMap, pMsg.custIncdtId, NSZM0902E);
            mandatoryCheck(msgMap, pMsg.custIncdtLineId, NSZM0903E);
        }
    }

    private static void checkSubmitForApproval(S21ApiMessageMap msgMap) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        mandatoryCheck(msgMap, pMsg.svcCrRebilPk, NSZM0915E);
        // START 2018/06/14 T.Ogura [QC#22382,DEL]
//        mandatoryCheck(msgMap, pMsg.svcCrRebilRsnCd, NSZM0917E);
        // END   2018/06/14 T.Ogura [QC#22382,DEL]
        mandatoryCheck(msgMap, pMsg.svcCrRebilRsnTxt, NSZM0918E);
        mandatoryCheck(msgMap, pMsg.svcCrRebilProcCd, NSZM0916E);

        for (int i = 0; i < pMsg.xxCrRebilDtlList.getValidCount(); i++) {

            NSZC053001_xxCrRebilDtlListPMsg inPMsg = pMsg.xxCrRebilDtlList.no(i);
            mandatoryCheck(msgMap, inPMsg.svcCrRebilDtlPk, NSZM0914E);
            mandatoryCheck(msgMap, inPMsg.invPrintFlg, NSZM0905E);
        }
    }

    // START 03/25/2016 T.Aoyagi [QC#5435, ADD]
    private static void checkOverrideApprover(S21ApiMessageMap msgMap) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        mandatoryCheck(msgMap, pMsg.svcCrRebilPk, NSZM0915E);
        mandatoryCheck(msgMap, pMsg.svcCrRebilProcCd, NSZM0916E);

        for (int i = 0; i < pMsg.xxCrRebilDtlList.getValidCount(); i++) {

            NSZC053001_xxCrRebilDtlListPMsg inPMsg = pMsg.xxCrRebilDtlList.no(i);
            mandatoryCheck(msgMap, inPMsg.svcCrRebilDtlPk, NSZM0914E);
            mandatoryCheck(msgMap, inPMsg.invPrintFlg, NSZM0905E);
        }
    }
    // END 03/25/2016 T.Aoyagi [QC#5435, ADD]

    private static void checkWorkFlowApprove(S21ApiMessageMap msgMap) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        mandatoryCheck(msgMap, pMsg.svcCrRebilPk, NSZM0915E);
    }

    private static void checkInvoicingFromBilling(S21ApiMessageMap msgMap) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        mandatoryCheck(msgMap, pMsg.svcCrRebilPk, NSZM0915E);

        for (int i = 0; i < pMsg.xxCrRebilDtlList.getValidCount(); i++) {

            NSZC053001_xxCrRebilDtlListPMsg inPMsg = pMsg.xxCrRebilDtlList.no(i);
            mandatoryCheck(msgMap, inPMsg.svcCrRebilDtlPk, NSZM0914E);
            // START 04/11/2016 T.Aoyagi [QC#6510, MOD]
            if (!hasValue(inPMsg.crSvcInvNum) && !hasValue(inPMsg.rebilSvcInvNum)) {
                mandatoryCheck(msgMap, inPMsg.crSvcInvNum, NSZM0900E);
                mandatoryCheck(msgMap, inPMsg.rebilSvcInvNum, NSZM0913E);
            }
            // END 04/11/2016 T.Aoyagi [QC#6510, MOD]
        }
    }

    private static void mandatoryCheck(S21ApiMessageMap msgMap, EZDPItem targetItem, String msgId) {
        if (!hasValue(targetItem)) {
            msgMap.addXxMsgId(msgId);
        }
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @return boolean
     */
    public static boolean existSvcCrRebilByIncdtId(S21ApiMessageMap msgMap) {

        SVC_CR_REBILTMsg crRebilTMsg = query.getSvcCrRebilTMsgByIncdtId(msgMap);
        if (crRebilTMsg != null) {
            return true;
        }
        return false;
    }

    // START 05/10/2016 T.Aoyagi [QC#7734, ADD]
    /**
     * @param msgMap S21ApiMessageMap
     * @return boolean
     */
    public static boolean existSvcCrRebilBySvcInvNum(S21ApiMessageMap msgMap) {

        List<String> svcInvNumList = getSvcInvNumListFromPMsg(msgMap);
        for (String svcInvNum : svcInvNumList) {

            BigDecimal svcCrRebilPk = query.getRegisteredSvcCrRebilPk(msgMap, svcInvNum, null);
            if (hasValue(svcCrRebilPk)) {
                // already exist
                String msgPrm = "Invoice#:" + svcInvNum;
                msgMap.addXxMsgIdWithPrm(NSAM0079E, new String[]{msgPrm});
                return true;
            }
        }
        return false;
    }
    // END 05/10/2016 T.Aoyagi [QC#7734, ADD]

    /**
     * @param msgMap S21ApiMessageMap
     * @return List<String>
     */
    public static List<String> getSvcInvNumListFromPMsg(S21ApiMessageMap msgMap) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        List<String> svcInvNumList = new ArrayList<String>();

        for (int i = 0; i < pMsg.xxCrRebilDtlList.getValidCount(); i++) {

            NSZC053001_xxCrRebilDtlListPMsg inPMsg = pMsg.xxCrRebilDtlList.no(i);
            String svcInvNum = inPMsg.origSvcInvNum.getValue();

            if (!svcInvNumList.contains(svcInvNum)) {
                svcInvNumList.add(svcInvNum);
            }
        }
        return svcInvNumList;
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @return List<BigDecimal>
     */
    public static List<BigDecimal> getCrRebilDtlPkFromPMsg(S21ApiMessageMap msgMap) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        List<BigDecimal> crRebilDtlPkList = new ArrayList<BigDecimal>();

        for (int i = 0; i < pMsg.xxCrRebilDtlList.getValidCount(); i++) {
            NSZC053001_xxCrRebilDtlListPMsg dtlPMsg = pMsg.xxCrRebilDtlList.no(i);
            if (!hasValue(dtlPMsg.svcCrRebilDtlPk)) {
                continue;
            }
            if (!crRebilDtlPkList.contains(dtlPMsg.svcCrRebilDtlPk.getValue())) {
                crRebilDtlPkList.add(dtlPMsg.svcCrRebilDtlPk.getValue());
            }
        }
        return crRebilDtlPkList;
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param svcInvNum String
     * @return BigDecimal
     */
    public static BigDecimal getCrRebilDtlPkFromPMsg(S21ApiMessageMap msgMap, String svcInvNum) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        BigDecimal svcCrRebilDtlPk = null;

        for (int i = 0; i < pMsg.xxCrRebilDtlList.getValidCount(); i++) {

            NSZC053001_xxCrRebilDtlListPMsg dtlPMsg = pMsg.xxCrRebilDtlList.no(i);
            if (svcInvNum.equals(dtlPMsg.origSvcInvNum.getValue())) {
                if (hasValue(dtlPMsg.svcCrRebilDtlPk)) {
                    svcCrRebilDtlPk = dtlPMsg.svcCrRebilDtlPk.getValue();
                    break;
                }
            }
        }
        return svcCrRebilDtlPk;
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param svcInvBaseMap Map<String, Object>
     * @return BigDecimal
     */
    // START 04/11/2016 T.Aoyagi [QC#6715, MOD]
    private static BigDecimal getNewBaseDealAmtFromPMsg(S21ApiMessageMap msgMap, Map<String, Object> svcInvBaseMap) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        BigDecimal newBaseDealAmt = null;
        // START 2018/06/29 K.Kitachi [QC#22057, ADD]
        BigDecimal dsContrDtlPk = nullToZero((BigDecimal) svcInvBaseMap.get("DS_CONTR_DTL_PK"));
        // END 2018/06/29 K.Kitachi [QC#22057, ADD]
        String serNum = nullToSpace((String) svcInvBaseMap.get("SER_NUM"));
        String bllgPerFromDt = nullToSpace((String) svcInvBaseMap.get("BLLG_PER_FROM_DT"));
        String bllgPerThruDt = nullToSpace((String) svcInvBaseMap.get("BLLG_PER_THRU_DT"));

        for (int i = 0; i < pMsg.xxCrRebilDtlList.getValidCount(); i++) {
            NSZC053001_xxCrRebilDtlListPMsg dtlPMsg = pMsg.xxCrRebilDtlList.no(i);

            // START 2018/06/29 K.Kitachi [QC#22057, MOD]
            if (hasValue(dtlPMsg.dsContrDtlPk)) {
                if (dsContrDtlPk.compareTo(dtlPMsg.dsContrDtlPk.getValue()) == 0
                        && bllgPerFromDt.equals(dtlPMsg.baseBllgFromDt.getValue())
                        && bllgPerThruDt.equals(dtlPMsg.baseBllgThruDt.getValue())) {
                    if (hasValue(dtlPMsg.newBaseDealAmt)) {
                        newBaseDealAmt = dtlPMsg.newBaseDealAmt.getValue();
                        break;
                    }
                }
            } else {
                if (serNum.equals(dtlPMsg.serNum.getValue())
                        && bllgPerFromDt.equals(dtlPMsg.baseBllgFromDt.getValue())
                        && bllgPerThruDt.equals(dtlPMsg.baseBllgThruDt.getValue())) {
                    if (hasValue(dtlPMsg.newBaseDealAmt)) {
                        newBaseDealAmt = dtlPMsg.newBaseDealAmt.getValue();
                        break;
                    }
                }
            }
            // END 2018/06/29 K.Kitachi [QC#22057, MOD]
        }
        return newBaseDealAmt;
    }
    // END 04/11/2016 T.Aoyagi [QC#6715, MOD]

    /**
     * @param msgMap S21ApiMessageMap
     * @param key String
     * @return List<NSZC053002_xxBaseChangesListPMsg>
     */
    public static List<NSZC053002_xxBaseChangesListPMsg> getBaseInfoFromPMsg(S21ApiMessageMap msgMap, String key) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        List<NSZC053002_xxBaseChangesListPMsg> list = new ArrayList<NSZC053002_xxBaseChangesListPMsg>();
        NSZC053002_xxBaseChangesListPMsg wrkPMsg;

        for (int i = 0; i < pMsg.xxCrRebilDtlList.getValidCount(); i++) {

            NSZC053001_xxCrRebilDtlListPMsg inPMsg = pMsg.xxCrRebilDtlList.no(i);

            if (!matchKey(inPMsg, key)) {
                continue;
            }
            if (!hasValues(inPMsg.baseBllgFromDt)) {
                continue;
            }
            if (!existBasePMsg(list, inPMsg)) {
                wrkPMsg = new NSZC053002_xxBaseChangesListPMsg();
                EZDMsg.copy(inPMsg, null, wrkPMsg, null);
                list.add(wrkPMsg);
            }
        }
        return list;
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param key String
     * @return List<NSZC053002_xxBaseChangesListPMsg>
     */
    public static List<NSZC053002_xxPriceChangesListPMsg> getXsInfoFromPMsg(S21ApiMessageMap msgMap, String key) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        List<NSZC053002_xxPriceChangesListPMsg> list = new ArrayList<NSZC053002_xxPriceChangesListPMsg>();
        NSZC053002_xxPriceChangesListPMsg wrkPMsg;

        for (int i = 0; i < pMsg.xxCrRebilDtlList.getValidCount(); i++) {

            NSZC053001_xxCrRebilDtlListPMsg inPMsg = pMsg.xxCrRebilDtlList.no(i);

            if (!matchKey(inPMsg, key)) {
                continue;
            }
            if (!hasValues(inPMsg.mtrBllgFromDt)) {
                continue;
            }
            if (!existXsMtrPMsg(list, inPMsg)) {
                wrkPMsg = new NSZC053002_xxPriceChangesListPMsg();
                EZDMsg.copy(inPMsg, null, wrkPMsg, null);
                list.add(wrkPMsg);
            }
        }
        return list;
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param key String
     * @return List<NSZC053002_xxBaseChangesListPMsg>
     */
    public static List<NSZC053002_xxMeterReadChangesListPMsg> getMtrReadInfoFromPMsg(S21ApiMessageMap msgMap, String key) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        List<NSZC053002_xxMeterReadChangesListPMsg> list = new ArrayList<NSZC053002_xxMeterReadChangesListPMsg>();
        NSZC053002_xxMeterReadChangesListPMsg wrkPMsg;

        for (int i = 0; i < pMsg.xxCrRebilDtlList.getValidCount(); i++) {

            NSZC053001_xxCrRebilDtlListPMsg inPMsg = pMsg.xxCrRebilDtlList.no(i);

            if (!matchKey(inPMsg, key)) {
                continue;
            }
            if (!hasValues(inPMsg.physMtrLbCd)) {
                continue;
            }
            if (!existPhysMtrPMsg(list, inPMsg)) {
                wrkPMsg = new NSZC053002_xxMeterReadChangesListPMsg();
                EZDMsg.copy(inPMsg, null, wrkPMsg, null);
                list.add(wrkPMsg);
            }
        }
        return list;
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param svcInvNum String
     * @param dsContrDtlPk BigDecimal
     * @param serNum String
     * @param bllgMtrLbCd String
     * @param oldXsCopyQty BigDecimal
     * @param aggtirRownum int
     * @return NSZC053002_xxPriceChangesListPMsg
     */
    // START 2023/01/16 R.Jin [QC#58890, MOD]
    // START 2018/06/29 K.Kitachi [QC#22057, MOD]
//    private static NSZC053002_xxPriceChangesListPMsg getXsInfoFromPMsg(S21ApiMessageMap msgMap, String svcInvNum, String serNum, String bllgMtrLbCd, BigDecimal oldXsCopyQty) {
//    private static NSZC053002_xxPriceChangesListPMsg getXsInfoFromPMsg(S21ApiMessageMap msgMap, String svcInvNum, BigDecimal dsContrDtlPk, String serNum, String bllgMtrLbCd, BigDecimal oldXsCopyQty) {
    private static NSZC053002_xxPriceChangesListPMsg getXsInfoFromPMsg(S21ApiMessageMap msgMap, String svcInvNum, BigDecimal dsContrDtlPk, String serNum, String bllgMtrLbCd, BigDecimal oldXsCopyQty, int aggtirRownum) {
    // END 2018/06/29 K.Kitachi [QC#22057, MOD]
    // END 2023/01/16 R.Jin [QC#58890, MOD]

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        NSZC053002_xxPriceChangesListPMsg xsMtrPMsg = new NSZC053002_xxPriceChangesListPMsg();
        // START 2023/01/16 R.Jin [QC#58890, ADD]
        boolean isAggLineOnlyXsCopyQtyChange = false;
        BigDecimal aggLineXsCopyQtyDiff = new BigDecimal("0");
        // END 2023/01/16 R.Jin [QC#58890, ADD]
        // START 2023/04/14 R.Jin [QC#58890-2, ADD]
        boolean usageBilingExstencFlg = false;
        for (int i = 0; i < pMsg.xxCrRebilDtlList.getValidCount(); i++) {
            NSZC053001_xxCrRebilDtlListPMsg msg = pMsg.xxCrRebilDtlList.no(i);
            if (hasValue(msg.serNum) && hasValue(msg.bllgMtrLbCd) && msg.serNum.getValue().equals(serNum) && msg.bllgMtrLbCd.getValue().equals(bllgMtrLbCd)) {
                usageBilingExstencFlg = true;
            }
        }
        // END 2023/04/14 R.Jin [QC#58890-2, ADD]

        for (int i = 0; i < pMsg.xxCrRebilDtlList.getValidCount(); i++) {

            NSZC053001_xxCrRebilDtlListPMsg inPMsg = pMsg.xxCrRebilDtlList.no(i);

            // START 2018/06/29 K.Kitachi [QC#22057, MOD]
            if (hasValue(inPMsg.dsContrDtlPk)) {
                if (!matchKey(inPMsg, svcInvNum, dsContrDtlPk, bllgMtrLbCd)) {
                    continue;
                }
            } else {
                // START 2023/01/16 R.Jin [QC#58890, MOD]
                DS_CONTR_DTLTMsg  dsContrDtlMsg = query.getDsContrDtlTMsg(pMsg.glblCmpyCd.getValue(), dsContrDtlPk);
                if (DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlMsg.dsContrDtlTpCd.getValue())) {
                    if(hasValue(inPMsg.serNum) && hasValue(inPMsg.oldXsCopyQty)) {
                        int tirRownum = 0;
                        List<Map<String, Object>> svcInvXsMtrList = query.getSvcInvLineForXsMtr(pMsg.glblCmpyCd.getValue(), svcInvNum, inPMsg.serNum.getValue(), bllgMtrLbCd, inPMsg.mtrBllgFromDt.getValue());
                        for (Map<String, Object> svcInvXsMtrMap :svcInvXsMtrList) {
                            BigDecimal xsCopyQty = (BigDecimal) svcInvXsMtrMap.get("XS_MTR_COPY_QTY");
                            if (xsCopyQty.compareTo(inPMsg.oldXsCopyQty.getValue()) == 0) {
                                tirRownum=((BigDecimal)svcInvXsMtrMap.get("TIER")).intValue();
                                break;
                            }
                        }
//                        int tirRownum = query.getAggLineXscopyChangeTirInfo(pMsg.glblCmpyCd.getValue(), inPMsg.serNum.getValue(),inPMsg.bllgMtrLbCd.getValue(),inPMsg.oldXsCopyQty.getValue()).get(0).intValue();
                        if(tirRownum == aggtirRownum && bllgMtrLbCd.equals(inPMsg.bllgMtrLbCd.getValue()) &&  hasValue(inPMsg.newXsCopyQty)) {
                            isAggLineOnlyXsCopyQtyChange = true;
                            aggLineXsCopyQtyDiff = aggLineXsCopyQtyDiff.add(inPMsg.newXsCopyQty.getValue().subtract(inPMsg.oldXsCopyQty.getValue()));
                            continue;
                        }
                    }
                } else {
                    String dsContrCateCd = query.getDsContrCatgCd(pMsg.glblCmpyCd.getValue(), inPMsg.origSvcInvNum.getValue());
                    // START 2023/04/14 R.Jin [QC#58890-2, MOD]
//                    if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCateCd) && !serNum.equals(inPMsg.serNum.getValue()) && inPMsg.bllgMtrLbCd.getValue().equals(bllgMtrLbCd) && inPMsg.origSvcInvNum.getValue().equals(svcInvNum) && (hasValue(inPMsg.newXsCopyQty.getValue()) || hasValue(inPMsg.newXsMtrAmtRate.getValue()))) {
                    if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCateCd) && !usageBilingExstencFlg && !serNum.equals(inPMsg.serNum.getValue()) && inPMsg.bllgMtrLbCd.getValue().equals(bllgMtrLbCd) && inPMsg.origSvcInvNum.getValue().equals(svcInvNum) && (hasValue(inPMsg.newXsCopyQty.getValue()) || hasValue(inPMsg.newXsMtrAmtRate.getValue()))) {
                        xsMtrPMsg.newXsCopyQty.setValue(oldXsCopyQty);
                        continue;
                    }
//                    else if (!matchKey(inPMsg, svcInvNum, serNum, bllgMtrLbCd)) {
//                        continue;
//                    }
                }
                
                if (!matchKey(inPMsg, svcInvNum, serNum, bllgMtrLbCd)) {
                    continue;
                }
                // END 2023/04/14 R.Jin [QC#58890-2, MOD]
            }
            // END 2018/06/29 K.Kitachi [QC#22057, MOD]
            if (equals(inPMsg.oldXsCopyQty.getValue(), oldXsCopyQty)) {
                EZDMsg.copy(inPMsg, null, xsMtrPMsg, null);
            }
        }
        if (isAggLineOnlyXsCopyQtyChange) {
            xsMtrPMsg.newXsCopyQty.setValue(oldXsCopyQty.add(aggLineXsCopyQtyDiff));
        }
        // END 2023/01/16 R.Jin [QC#58890, MOD]
        return xsMtrPMsg;
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param svcInvNum String
     * @param dsContrDtlPk BigDecimal
     * @param serNum String
     * @param bllgMtrLbCd String
     * @param physMtrLbCd String
     * @return NSZC053002_xxMeterReadChangesListPMsg
     */
    // START 2018/06/29 K.Kitachi [QC#22057, MOD]
//    private static NSZC053002_xxMeterReadChangesListPMsg getMtrReadInfoFromPMsg(S21ApiMessageMap msgMap, String svcInvNum, String serNum, String physMtrLbCd) {
    private static NSZC053002_xxMeterReadChangesListPMsg getMtrReadInfoFromPMsg(S21ApiMessageMap msgMap, String svcInvNum, BigDecimal dsContrDtlPk, String serNum, String physMtrLbCd) {
    // END 2018/06/29 K.Kitachi [QC#22057, MOD]

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        NSZC053002_xxMeterReadChangesListPMsg mtrReadPMsg = new NSZC053002_xxMeterReadChangesListPMsg();

        for (int i = 0; i < pMsg.xxCrRebilDtlList.getValidCount(); i++) {

            NSZC053001_xxCrRebilDtlListPMsg inPMsg = pMsg.xxCrRebilDtlList.no(i);

            // START 2018/06/29 K.Kitachi [QC#22057, MOD]
            if (hasValue(inPMsg.dsContrDtlPk)) {
                if (!matchKey(inPMsg, svcInvNum, dsContrDtlPk)) {
                    continue;
                }
            } else {
                if (!matchKey(inPMsg, svcInvNum, serNum)) {
                    continue;
                }
            }
            // END 2018/06/29 K.Kitachi [QC#22057, MOD]
            if (equals(inPMsg.physMtrLbCd.getValue(), physMtrLbCd)) {
                EZDMsg.copy(inPMsg, null, mtrReadPMsg, null);
            }
        }
        return mtrReadPMsg;
    }

    private static boolean matchKey(NSZC053001_xxCrRebilDtlListPMsg inPMsg, String key) {

        return (equals(inPMsg.origSvcInvNum.getValue(), key));
    }

    private static boolean matchKey(NSZC053001_xxCrRebilDtlListPMsg inPMsg, String svcInvNum, String serNum) {

        return (equals(inPMsg.origSvcInvNum.getValue(), svcInvNum)
                    && equals(inPMsg.serNum.getValue(), serNum));
    }

    private static boolean matchKey(NSZC053001_xxCrRebilDtlListPMsg inPMsg, String svcInvNum, String serNum, String bllgMtrLbCd) {

        return (equals(inPMsg.origSvcInvNum.getValue(), svcInvNum)
                    && equals(inPMsg.serNum.getValue(), serNum)
                    && equals(inPMsg.bllgMtrLbCd.getValue(), bllgMtrLbCd));
    }

    private static boolean existBasePMsg(List<NSZC053002_xxBaseChangesListPMsg> list, NSZC053001_xxCrRebilDtlListPMsg inPMsg) {

        for (NSZC053002_xxBaseChangesListPMsg compPMsg : list) {

            if (equals(inPMsg.svcCrRebilDtlPk.getValue(), compPMsg.svcCrRebilDtlPk.getValue())
                    && equals(inPMsg.origSvcInvNum.getValue(), compPMsg.origSvcInvNum.getValue())
                    && equals(inPMsg.serNum.getValue(), compPMsg.serNum.getValue())
                    && equals(inPMsg.baseBllgFromDt.getValue(), compPMsg.baseBllgFromDt.getValue())
                    && equals(inPMsg.baseBllgThruDt.getValue(), compPMsg.baseBllgThruDt.getValue())
                    && equals(inPMsg.newBaseDealAmt.getValue(), compPMsg.newBaseDealAmt.getValue())
                    // START 2022/04/29 E.Sanchez [QC#59914-1,ADD]
                    && equals(inPMsg.newBaseUnitAmt.getValue(), compPMsg.newBaseUnitAmt.getValue())
                    && equals(inPMsg.svcMachMstrPk.getValue(), compPMsg.svcMachMstrPk.getValue())) {   
                    // END 2022/04/29 E.Sanchez [QC#59914-1,ADD]
                return true;
            }
        }
        return false;
    }

    private static boolean existXsMtrPMsg(List<NSZC053002_xxPriceChangesListPMsg> list, NSZC053001_xxCrRebilDtlListPMsg inPMsg) {

        for (NSZC053002_xxPriceChangesListPMsg compPMsg : list) {

            if (equals(inPMsg.svcCrRebilDtlPk.getValue(), compPMsg.svcCrRebilDtlPk.getValue())
                    && equals(inPMsg.origSvcInvNum.getValue(), compPMsg.origSvcInvNum.getValue())
                    && equals(inPMsg.serNum.getValue(), compPMsg.serNum.getValue())
                    && equals(inPMsg.mtrBllgFromDt.getValue(), compPMsg.mtrBllgFromDt.getValue())
                    && equals(inPMsg.mtrBllgThruDt.getValue(), compPMsg.mtrBllgThruDt.getValue())
                    && equals(inPMsg.oldXsCopyQty.getValue(), compPMsg.oldXsCopyQty.getValue())
                    && equals(inPMsg.oldXsMtrAmtRate.getValue(), compPMsg.oldXsMtrAmtRate.getValue())
                    && equals(inPMsg.newXsCopyQty.getValue(), compPMsg.newXsCopyQty.getValue())
                    && equals(inPMsg.newXsMtrAmtRate.getValue(), compPMsg.newXsMtrAmtRate.getValue())) {
                return true;
            }
        }
        return false;
    }

    private static boolean existPhysMtrPMsg(List<NSZC053002_xxMeterReadChangesListPMsg> list, NSZC053001_xxCrRebilDtlListPMsg inPMsg) {

        for (NSZC053002_xxMeterReadChangesListPMsg compPMsg : list) {
            //START 2017/09/19 E.Kameishi [QC#18636,MOD]
            if (equals(inPMsg.svcCrRebilDtlPk.getValue(), compPMsg.svcCrRebilDtlPk.getValue())
                    && equals(inPMsg.origSvcInvNum.getValue(), compPMsg.origSvcInvNum.getValue())
                    && equals(inPMsg.serNum.getValue(), compPMsg.serNum.getValue())
                    && equals(inPMsg.physMtrLbCd.getValue(), compPMsg.physMtrLbCd.getValue())
                    && equals(inPMsg.startMtrReadDt.getValue(), compPMsg.startMtrReadDt.getValue())
                    && equals(inPMsg.endMtrReadDt.getValue(), compPMsg.endMtrReadDt.getValue())
                    && equals(inPMsg.newStartReadMtrCnt.getValue(), compPMsg.newStartReadMtrCnt.getValue())
                    && equals(inPMsg.newEndReadMtrCnt.getValue(), compPMsg.newEndReadMtrCnt.getValue())
                    && equals(inPMsg.newTestMtrCnt.getValue(), compPMsg.newTestMtrCnt.getValue())) {
                    //&& equals(inPMsg.newEndTestMtrCnt.getValue(), compPMsg.newEndTestMtrCnt.getValue())) {
                return true;
            }
            //END 2017/09/19 E.Kameishi [QC#18636,MOD]
        }
        return false;
    }

    /**
     * @param items EZDPItem
     * @return boolean
     */
    public static boolean hasValues(EZDPItem... items) {
        for (EZDPItem item : items) {
            if (hasValue(item)) {
                return true;
            }
        }
        return false;
    }

    private static boolean equals(String str1, String str2) {

        if (!hasValue(str1) && !hasValue(str2)) {
            return true;
        }
        if (hasValue(str1) && hasValue(str2)) {
            if (str1.equals(str2)) {
                return true;
            }
        }
        return false;
    }

    private static boolean equals(BigDecimal bd1, BigDecimal bd2) {

        if (!hasValue(bd1) && !hasValue(bd2)) {
            return true;
        }
        if (hasValue(bd1) && hasValue(bd2)) {
            if (bd1.compareTo(bd2) == 0) {
                return true;
            }
        }
        return false;
    }

    // START 04/18/2016 T.Aoyagi [QC#7056, MOD]
    /**
     * @param msgMap S21ApiMessageMap
     * @param svcInvNum String
     * @param crRebilTMsg SVC_CR_REBILTMsg
     * @param fixMtrReadList List<SVC_CR_REBIL_MTR_READTMsg>
     */
    public static void createCrRebilInfo(S21ApiMessageMap msgMap, String svcInvNum, SVC_CR_REBILTMsg crRebilTMsg, List<SVC_CR_REBIL_MTR_READTMsg> fixMtrReadList) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();

        SVC_INVTMsg svcInvTMsg = query.getSvcInvTMsg(glblCmpyCd, svcInvNum);
        if (svcInvTMsg == null) {
            msgMap.addXxMsgId(NSZM0896E);
            return;
        }

        // ----------------------------------------
        // Create SVC_CR_REBIL_DTL
        // ----------------------------------------
        SVC_CR_REBIL_DTLTMsg crRebilDtlTMsg = createCrRebilDtl(msgMap, crRebilTMsg, svcInvTMsg);
        if (crRebilDtlTMsg == null) {
            return;
        }

        // ----------------------------------------
        // Create SVC_CR_REBIL_BASE_BLLG
        // ----------------------------------------
        List<Map<String, Object>> svcInvBaseList = query.getSvcInvLineForBase(glblCmpyCd, svcInvNum);

        createCrRebilBaseForAggLine(msgMap, crRebilDtlTMsg, svcInvBaseList);

        for (Map<String, Object> svcInvBaseMap : svcInvBaseList) {
            // START 04/11/2016 T.Aoyagi [QC#6715, MOD]
            BigDecimal newBaseDealAmt = getNewBaseDealAmtFromPMsg(msgMap, svcInvBaseMap);
            // END 04/11/2016 T.Aoyagi [QC#6715, MOD]
            createCrRebilBaseBllg(msgMap, crRebilDtlTMsg, svcInvBaseMap, newBaseDealAmt);

            // START 2018/03/27 M.Naito [QC#18672, ADD]
            // ----------------------------------------
            // Create SVC_CR_REBIL_ADDL_BLLG
            // ----------------------------------------
            // START 2022/05/27 K.Kitachi [QC#60121, ADD]
            if (!hasValue(newBaseDealAmt)) {
                newBaseDealAmt = (BigDecimal) svcInvBaseMap.get("INV_LINE_DEAL_NET_AMT");
            }
            // END 2022/05/27 K.Kitachi [QC#60121, ADD]
            BigDecimal prntSvcInvLinePk = (BigDecimal) svcInvBaseMap.get("SVC_INV_LINE_PK");
            List<Map<String, Object>> svcInvAddlChrgList = query.getSvcInvLineForAddlChrg(glblCmpyCd, prntSvcInvLinePk, ADDL_CHRG_INV_TP.BASE);
            for (Map<String, Object> svcInvAddlChrgMap : svcInvAddlChrgList) {
                Map<String, Object> bllgSchdMap = query.getBllgSchdInfoForCalcAddlChrg(glblCmpyCd, prntSvcInvLinePk);
                AddlChrgFromThruDtInfo addlChrgFromThruDtInfo = getAddlChrgFromThruDtInfo(glblCmpyCd, bllgSchdMap, svcInvAddlChrgMap);
                if (addlChrgFromThruDtInfo == null) {
                    continue;
                }
                // get New addtional cherge amount
                BigDecimal newAddlChrgDealAmt = getNewAddlChrgDealAmt(glblCmpyCd, svcInvAddlChrgMap, bllgSchdMap, addlChrgFromThruDtInfo, newBaseDealAmt);
                createCrRebilAddlBllg(msgMap, crRebilDtlTMsg.svcCrRebilDtlPk.getValue(), svcInvAddlChrgMap, newAddlChrgDealAmt);
            }

            // Create SVC_CR_REBIL_DTL for Additional Chrge(Invoice not exist)
            List<Map<String, Object>> addlChrgNotExistSvcInvLineList = query.getAddlChrgNotExistSvcInvLine(pMsg.glblCmpyCd.getValue(), prntSvcInvLinePk, ADDL_CHRG_INV_TP.BASE);
            boolean createAddlBllgFlg = true;
            BigDecimal newSvcCrRebilDtlPk = null;
            // Create SVC_CR_REBIL_ADDL_BLLG (Invoice not exist)
            for (Map<String, Object> addlChrgNotExistSvcInvLineMap : addlChrgNotExistSvcInvLineList) {
                Map<String, Object> bllgSchdMap = query.getBllgSchdInfoForCalcAddlChrg(glblCmpyCd, prntSvcInvLinePk);
                AddlChrgFromThruDtInfo addlChrgFromThruDtInfo = getAddlChrgFromThruDtInfo(glblCmpyCd, bllgSchdMap, addlChrgNotExistSvcInvLineMap);
                if (addlChrgFromThruDtInfo == null) {
                    continue;
                }
                if (createAddlBllgFlg) {
                    // Create SVC_CR_REBIL_DTL for Additional Charge(Invoice not exist)
                    newSvcCrRebilDtlPk = createCrRebilDtlForAddlChrg(msgMap, crRebilTMsg.svcCrRebilPk.getValue(), svcInvNum, addlChrgNotExistSvcInvLineMap, crRebilDtlTMsg.svcCrRebilDtlPk.getValue());
                }
                BigDecimal newAddlChrgDealAmt = getNewAddlChrgDealAmt(glblCmpyCd, addlChrgNotExistSvcInvLineMap, bllgSchdMap, addlChrgFromThruDtInfo, newBaseDealAmt);
                createCrRebilAddlBllg(msgMap, newSvcCrRebilDtlPk, addlChrgNotExistSvcInvLineMap, newAddlChrgDealAmt);
                createAddlBllgFlg = false;
            }
            // END 2018/03/27 M.Naito [QC#18672, ADD]
        }

        // ----------------------------------------
        // Create SVC_CR_REBIL_MTR_BLLG
        // ----------------------------------------
        List<Map<String, Object>> svcInvMtrList = query.getSvcInvLineForBllgMtr(glblCmpyCd, svcInvNum);

        // START 2017/10/20 K.Kitachi [QC#21927, MOD]
//        createCrRebilMtrForAggLine(msgMap, crRebilDtlTMsg, svcInvMtrList);
        // START 2017/11/08 K.Kitachi [QC#22301, MOD]
        // createCrRebilMtrForAggLine(msgMap, crRebilDtlTMsg, svcInvMtrList, svcInvNum);
        createCrRebilMtrForAggLine(msgMap, crRebilDtlTMsg, svcInvMtrList, svcInvNum, svcInvNum);
        // MOD 2017/11/08 K.Kitachi [QC#22301, MOD]
        // END 2017/10/20 K.Kitachi [QC#21927, MOD]

        for (Map<String, Object> svcInvMtrMap : svcInvMtrList) {

            if (svcInvMtrMap == null) {
                msgMap.addXxMsgId(NSZM0896E);
                continue;
            }
            SVC_CR_REBIL_MTR_BLLGTMsg crRebilMtrTMsg = createCrRebilMtrBllg(msgMap, crRebilDtlTMsg, svcInvMtrMap);
            // START 2018/06/29 K.Kitachi [QC#22057, ADD]
            BigDecimal dsContrDtlPk = nullToZero((BigDecimal) svcInvMtrMap.get("DS_CONTR_DTL_PK"));
            // END 2018/06/29 K.Kitachi [QC#22057, ADD]
            String serNum = (String) svcInvMtrMap.get("SER_NUM");
            String bllgMtrLbCd = (String) svcInvMtrMap.get("MTR_LB_CD");
            String bllgPerFromDt = (String) svcInvMtrMap.get("BLLG_PER_FROM_DT");

            // ----------------------------------------
            // Create SVC_CR_REBIL_XS_MTR_BLLG
            // ----------------------------------------
            List<Map<String, Object>> svcInvXsMtrList = query.getSvcInvLineForXsMtr(glblCmpyCd, svcInvNum, serNum, bllgMtrLbCd, bllgPerFromDt);
            for (Map<String, Object> svcInvXsMtrMap : svcInvXsMtrList) {

                BigDecimal oldXsCopyQty = (BigDecimal) svcInvXsMtrMap.get("XS_MTR_COPY_QTY");
                // START 2018/06/29 K.Kitachi [QC#22057, MOD]
//                NSZC053002_xxPriceChangesListPMsg xsMtrPMsg = getXsInfoFromPMsg(msgMap, svcInvNum, serNum, bllgMtrLbCd, oldXsCopyQty);
                // START 2023/01/16 R.Jin [QC#58890, MOD]
//                NSZC053002_xxPriceChangesListPMsg xsMtrPMsg = getXsInfoFromPMsg(msgMap, svcInvNum, dsContrDtlPk, serNum, bllgMtrLbCd, oldXsCopyQty);
                NSZC053002_xxPriceChangesListPMsg xsMtrPMsg = getXsInfoFromPMsg(msgMap, svcInvNum, dsContrDtlPk, serNum, bllgMtrLbCd, oldXsCopyQty, 0);
                // END 2023/01/16 R.Jin [QC#58890, MOD]
                // END 2018/06/29 K.Kitachi [QC#22057, MOD]
                createCrRebilXsMtrBllg(msgMap, crRebilMtrTMsg, svcInvXsMtrMap, xsMtrPMsg);
            }

            // START 2018/03/27 M.Naito [QC#18672, ADD]
            // ----------------------------------------
            // Create SVC_CR_REBIL_ADDL_BLLG
            // ----------------------------------------
            BigDecimal prntSvcInvLinePk = (BigDecimal) svcInvMtrMap.get("SVC_INV_LINE_PK");
            List<Map<String, Object>> svcInvAddlChrgList = query.getSvcInvLineForAddlChrg(glblCmpyCd, prntSvcInvLinePk, ADDL_CHRG_INV_TP.USAGE);
            for (Map<String, Object> svcInvAddlChrgMap : svcInvAddlChrgList) {
                Map<String, Object> bllgSchdMap = query.getBllgSchdInfoForCalcAddlChrg(glblCmpyCd, prntSvcInvLinePk);
                AddlChrgFromThruDtInfo addlChrgFromThruDtInfo = getAddlChrgFromThruDtInfo(glblCmpyCd, bllgSchdMap, svcInvAddlChrgMap);
                if (addlChrgFromThruDtInfo == null) {
                    continue;
                }
                createCrRebilAddlBllg(msgMap, crRebilMtrTMsg.svcCrRebilDtlPk.getValue(), svcInvAddlChrgMap, null);
            }

            // Create SVC_CR_REBIL_ADDL_BLLG (Invoice not exist)
            List<Map<String, Object>> addlChrgNotExistSvcInvLineList = query.getAddlChrgNotExistSvcInvLine(pMsg.glblCmpyCd.getValue(), prntSvcInvLinePk, ADDL_CHRG_INV_TP.USAGE);
            boolean createAddlBllgFlg = true;
            BigDecimal newSvcCrRebilDtlPk = null;
            for (Map<String, Object> addlChrgNotExistSvcInvLineMap : addlChrgNotExistSvcInvLineList) {
                Map<String, Object> bllgSchdMap = query.getBllgSchdInfoForCalcAddlChrg(glblCmpyCd, prntSvcInvLinePk);
                AddlChrgFromThruDtInfo addlChrgFromThruDtInfo = getAddlChrgFromThruDtInfo(glblCmpyCd, bllgSchdMap, addlChrgNotExistSvcInvLineMap);
                if (addlChrgFromThruDtInfo == null) {
                    continue;
                }
                if (createAddlBllgFlg) {
                    // Create SVC_CR_REBIL_DTL for Additional Charge(Invoice not exist)
                    newSvcCrRebilDtlPk =createCrRebilDtlForAddlChrg(msgMap, crRebilTMsg.svcCrRebilPk.getValue(), svcInvNum, addlChrgNotExistSvcInvLineMap, crRebilMtrTMsg.svcCrRebilDtlPk.getValue());
                }
                createCrRebilAddlBllg(msgMap, newSvcCrRebilDtlPk, addlChrgNotExistSvcInvLineMap, null);
                createAddlBllgFlg = false;
            }
            // END 2018/03/27 M.Naito [QC#18672, ADD]
        }

        // add start 2016/04/27 CSA Defect#7056
        syncAggLineXsRateToAggMach(msgMap, crRebilDtlTMsg.svcCrRebilDtlPk.getValue());
        // add end 2016/04/27 CSA Defect#7056

        // ----------------------------------------
        // Create SVC_CR_REBIL_MTR_READ
        // ----------------------------------------
        List<Map<String, Object>> svcInvMtrReadList = query.getSvcInvLineForMtrRead(glblCmpyCd, svcInvNum);
        for (Map<String, Object> svcInvMtrReadMap : svcInvMtrReadList) {
            // START 2018/06/29 K.Kitachi [QC#22057, ADD]
            BigDecimal dsContrDtlPk = nullToZero((BigDecimal) svcInvMtrReadMap.get("DS_CONTR_DTL_PK"));
            // END 2018/06/29 K.Kitachi [QC#22057, ADD]
            String serNum = (String) svcInvMtrReadMap.get("SER_NUM");
            String physMtrLbCd = (String) svcInvMtrReadMap.get("MDL_MTR_LB_CD");
            // START 2018/06/29 K.Kitachi [QC#22057, MOD]
//            NSZC053002_xxMeterReadChangesListPMsg mtrReadPMsg = getMtrReadInfoFromPMsg(msgMap, svcInvNum, serNum, physMtrLbCd);
            NSZC053002_xxMeterReadChangesListPMsg mtrReadPMsg = getMtrReadInfoFromPMsg(msgMap, svcInvNum, dsContrDtlPk, serNum, physMtrLbCd);
            // END 2018/06/29 K.Kitachi [QC#22057, MOD]
            SVC_CR_REBIL_MTR_READTMsg crRebilMtrReadTMsg = createCrRebilMtrRead(msgMap, crRebilDtlTMsg, svcInvMtrReadMap, mtrReadPMsg);

            if (hasValue(mtrReadPMsg.physMtrLbCd)) {
                fixMtrReadList.add(crRebilMtrReadTMsg);
            }
        }
    }
    // END 04/18/2016 T.Aoyagi [QC#7056, MOD]

    /**
     * @param msgMap S21ApiMessageMap
     * @return SVC_CR_REBILTMsg
     */
    public static SVC_CR_REBILTMsg createCrRebil(S21ApiMessageMap msgMap) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();

        BigDecimal svcCrRebilPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CR_REBIL_SQ);

        SVC_CR_REBILTMsg inTMsg = new SVC_CR_REBILTMsg();
        setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inTMsg.svcCrRebilPk, svcCrRebilPk);
        setValue(inTMsg.custIncdtId, pMsg.custIncdtId);
        setValue(inTMsg.custIncdtLineId, pMsg.custIncdtLineId);
        setValue(inTMsg.svcCrRebilStsCd, SVC_CR_REBIL_STS.ENTERED);
        inTMsg.svcCrRebilRsnCd.clear();
        inTMsg.svcCrRebilRsnTxt.clear();
        setValue(inTMsg.svcCrRebilDescTxt, pMsg.svcCrRebilDescTxt);
        setValue(inTMsg.svcCrRebilSrcNm, pMsg.svcCrRebilSrcNm);
        setValue(inTMsg.svcCrRebilProcCd, SVC_CR_REBIL_PROC.CREDIT_AND_REBILL);
        setValue(inTMsg.custCareRgtnPsnCd, pMsg.custCareRgtnPsnCd);
        setValue(inTMsg.svcContrBrCd, pMsg.svcContrBrCd);
        setValue(inTMsg.custIncdtAsgPsnCd, pMsg.custIncdtAsgPsnCd);
        S21ApiTBLAccessor.insert(inTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0883E);
            return null;
        }
        return inTMsg;
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param crRebilTMsg SVC_CR_REBILTMsg
     * @param svcInvTMsg SVC_INVTMsg
     * @return SVC_CR_REBIL_DTLTMsg
     */
    private static SVC_CR_REBIL_DTLTMsg createCrRebilDtl(S21ApiMessageMap msgMap, SVC_CR_REBILTMsg crRebilTMsg, SVC_INVTMsg svcInvTMsg) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();

        BigDecimal svcCrRebilDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CR_REBIL_DTL_SQ);

        SVC_CR_REBIL_DTLTMsg inTMsg = new SVC_CR_REBIL_DTLTMsg();
        setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inTMsg.svcCrRebilDtlPk, svcCrRebilDtlPk);
        setValue(inTMsg.svcCrRebilPk, crRebilTMsg.svcCrRebilPk);
        setValue(inTMsg.custIncdtId, pMsg.custIncdtId);
        setValue(inTMsg.origSvcInvNum, svcInvTMsg.svcInvNum);
        setValue(inTMsg.ccyCd, svcInvTMsg.dealCcyCd);
        setValue(inTMsg.oldInvTotDealAmt, svcInvTMsg.invTotDealNetAmt);
        setValue(inTMsg.oldInvTotFuncAmt, svcInvTMsg.invTotFuncNetAmt);
        inTMsg.newInvTotDealAmt.clear();
        inTMsg.newInvTotFuncAmt.clear();
        inTMsg.invTotDealNetAmt.clear();
        inTMsg.invTotFuncNetAmt.clear();
        inTMsg.crSvcInvNum.clear();
        inTMsg.rebilSvcInvNum.clear();
        setValue(inTMsg.invPrintFlg, FLG_ON_Y);
        setValue(inTMsg.dsContrPk, svcInvTMsg.dsContrPk);

        S21ApiTBLAccessor.insert(inTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0885E);
            return null;
        }
        return inTMsg;
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param crRebilDtlTMsg SVC_CR_REBIL_DTLTMsg
     * @param svcInvBaseMap Map<String, Object>
     * @param newBaseDealAmt BigDecimal
     * @return SVC_CR_REBIL_BASE_BLLGTMsg
     */
    private static SVC_CR_REBIL_BASE_BLLGTMsg createCrRebilBaseBllg(S21ApiMessageMap msgMap, SVC_CR_REBIL_DTLTMsg crRebilDtlTMsg, Map<String, Object> svcInvBaseMap, BigDecimal newBaseDealAmt) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();

        BigDecimal svcCrRebilBaseBllgPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CR_REBIL_BASE_BLLG_SQ);

        SVC_CR_REBIL_BASE_BLLGTMsg inTMsg = new SVC_CR_REBIL_BASE_BLLGTMsg();
        BigDecimal oldBaseDealAmt = (BigDecimal) svcInvBaseMap.get("INV_LINE_DEAL_NET_AMT");
        setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inTMsg.svcCrRebilBaseBllgPk, svcCrRebilBaseBllgPk);
        setValue(inTMsg.svcCrRebilDtlPk, crRebilDtlTMsg.svcCrRebilDtlPk);
        setValue(inTMsg.dsContrDtlPk, (BigDecimal) svcInvBaseMap.get("DS_CONTR_DTL_PK"));
        setValue(inTMsg.baseBllgFromDt, (String) svcInvBaseMap.get("BLLG_PER_FROM_DT"));
        setValue(inTMsg.baseBllgThruDt, (String) svcInvBaseMap.get("BLLG_PER_THRU_DT"));
        setValue(inTMsg.oldBaseDealAmt, oldBaseDealAmt);
        // START 2018/08/27 [QC#24555, ADD]
        BigDecimal oldBaseUnitAmt = (BigDecimal) svcInvBaseMap.get("OLD_BASE_UNIT_AMT");
        BigDecimal newBaseUnitAmt = null;
        BigDecimal calcNewBaseAmt = null;
        setValue(inTMsg.svcInvLinePk, (BigDecimal) svcInvBaseMap.get("SVC_INV_LINE_PK"));
        setValue(inTMsg.oldBaseUnitAmt, oldBaseUnitAmt);
        if (hasValue(newBaseDealAmt)) {
            newBaseUnitAmt = newBaseDealAmt;
            setValue(inTMsg.newBaseUnitAmt, newBaseUnitAmt);
            calcNewBaseAmt = calcNewBaseDealAmt(inTMsg, newBaseUnitAmt);
            setValue(inTMsg.newBaseDealAmt, calcNewBaseAmt);
        }
        // END   2018/08/27 [QC#24555, ADD]
        // START 2018/08/27 [QC#24555, MOD]
        // START 2017/10/13 K.Kim [QC#21775, ADD]
        if (!hasValue(newBaseDealAmt)) {
            calcNewBaseAmt = oldBaseDealAmt;
        }
        // END 2017/10/13 K.Kim [QC#21775, ADD]
        // START 05/09/2016 T.Aoyagi [QC#7618, ADD]
        if (hasValue(oldBaseDealAmt) && hasValue(newBaseDealAmt)) {
            setValue(inTMsg.invBaseDealNetAmt, (calcNewBaseAmt.subtract(oldBaseDealAmt)).abs());
        }
        // END 05/09/2016 T.Aoyagi [QC#7618, ADD]
        // END   2018/08/27 [QC#24555, MOD]
        setValue(inTMsg.svcInvLinePk, (BigDecimal) svcInvBaseMap.get("SVC_INV_LINE_PK"));

        S21ApiTBLAccessor.insert(inTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0884E);
            return null;
        }
        return inTMsg;
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param crRebilDtlTMsg SVC_CR_REBIL_DTLTMsg
     * @param svcInvMap Map<String, Object>
     * @return SVC_CR_REBIL_MTR_BLLGTMsg
     */
    private static SVC_CR_REBIL_MTR_BLLGTMsg createCrRebilMtrBllg(S21ApiMessageMap msgMap, SVC_CR_REBIL_DTLTMsg crRebilDtlTMsg, Map<String, Object> svcInvMap) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();

        BigDecimal svcCrRebilMtrBllgPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CR_REBIL_MTR_BLLG_SQ);

        SVC_CR_REBIL_MTR_BLLGTMsg inTMsg = new SVC_CR_REBIL_MTR_BLLGTMsg();
        setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inTMsg.svcCrRebilMtrBllgPk, svcCrRebilMtrBllgPk);
        setValue(inTMsg.svcCrRebilDtlPk, crRebilDtlTMsg.svcCrRebilDtlPk);
        setValue(inTMsg.dsContrDtlPk, (BigDecimal) svcInvMap.get("DS_CONTR_DTL_PK"));
        setValue(inTMsg.dsContrBllgMtrPk, (BigDecimal) svcInvMap.get("DS_CONTR_BLLG_MTR_PK"));
        setValue(inTMsg.bllgMtrLbCd, (String) svcInvMap.get("MTR_LB_CD"));
        setValue(inTMsg.mtrBllgFromDt, (String) svcInvMap.get("BLLG_PER_FROM_DT"));
        setValue(inTMsg.mtrBllgThruDt, (String) svcInvMap.get("BLLG_PER_THRU_DT"));
        setValue(inTMsg.oldMtrCnt, (BigDecimal) svcInvMap.get("BLLG_COPY_QTY"));
        inTMsg.newMtrCnt.clear();
        setValue(inTMsg.oldMtrTestCnt, (BigDecimal) svcInvMap.get("TEST_COPY_QTY"));
        inTMsg.newMtrTestCnt.clear();
        setValue(inTMsg.oldMtrChrgDealAmt, (BigDecimal) svcInvMap.get("MTR_CHRG_DEAL_AMT"));
        inTMsg.newMtrChrgDealAmt.clear();
        inTMsg.invMtrChrgDealNetAmt.clear();
        setValue(inTMsg.svcInvLineMtrPk, (BigDecimal) svcInvMap.get("SVC_INV_LINE_MTR_PK"));
        setValue(inTMsg.svcInvLinePk, (BigDecimal) svcInvMap.get("SVC_INV_LINE_PK"));

        S21ApiTBLAccessor.insert(inTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0886E);
            return null;
        }
        return inTMsg;
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param crRebilMtrTMsg SVC_CR_REBIL_MTR_BLLGTMsg
     * @param svcInvMap Map<String, Object>
     * @param xsMtrPMsg NSZC053002_xxPriceChangesListPMsg
     * @return SVC_CR_REBIL_XS_MTR_BLLGTMsg
     */
    public static SVC_CR_REBIL_XS_MTR_BLLGTMsg createCrRebilXsMtrBllg(S21ApiMessageMap msgMap, SVC_CR_REBIL_MTR_BLLGTMsg crRebilMtrTMsg, Map<String, Object> svcInvMap, NSZC053002_xxPriceChangesListPMsg xsMtrPMsg) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();

        BigDecimal svcCrRebilXsMtrBllgPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CR_REBIL_XS_MTR_BLLG_SQ);

        SVC_CR_REBIL_XS_MTR_BLLGTMsg inTMsg = new SVC_CR_REBIL_XS_MTR_BLLGTMsg();
        setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inTMsg.svcCrRebilXsMtrBllgPk, svcCrRebilXsMtrBllgPk);
        setValue(inTMsg.svcCrRebilMtrBllgPk, crRebilMtrTMsg.svcCrRebilMtrBllgPk);
        setValue(inTMsg.svcCrRebilDtlPk, crRebilMtrTMsg.svcCrRebilDtlPk);
        setValue(inTMsg.dsContrDtlPk, (BigDecimal) svcInvMap.get("DS_CONTR_DTL_PK"));
        setValue(inTMsg.dsContrBllgMtrPk, (BigDecimal) svcInvMap.get("DS_CONTR_BLLG_MTR_PK"));
        setValue(inTMsg.contrXsCopyPk, (BigDecimal) svcInvMap.get("CONTR_XS_COPY_PK"));
        setValue(inTMsg.oldXsCopyQty, (BigDecimal) svcInvMap.get("XS_MTR_COPY_QTY"));
        setValue(inTMsg.newXsCopyQty, xsMtrPMsg.newXsCopyQty);
        setValue(inTMsg.oldXsMtrAmtRate, (BigDecimal) svcInvMap.get("XS_MTR_AMT_RATE"));
        setValue(inTMsg.newXsMtrAmtRate, xsMtrPMsg.newXsMtrAmtRate);
        setValue(inTMsg.oldXsMtrChrgDealAmt, (BigDecimal) svcInvMap.get("XS_MTR_CHRG_DEAL_AMT"));
        inTMsg.newXsMtrChrgDealAmt.clear();
        setValue(inTMsg.svcInvLineXsMtrPk, (BigDecimal) svcInvMap.get("SVC_INV_LINE_XS_MTR_PK"));
        setValue(inTMsg.svcInvLineMtrPk, (BigDecimal) svcInvMap.get("SVC_INV_LINE_MTR_PK"));
        setValue(inTMsg.svcInvLinePk, (BigDecimal) svcInvMap.get("SVC_INV_LINE_PK"));
        // START 2018/08/27 [QC#24555, ADD]
        setValue(inTMsg.oldUnitXsCopyQty, (BigDecimal) svcInvMap.get("OLD_UNIT_XS_MTR_COPY_QTY"));
        setValue(inTMsg.newUnitXsCopyQty, xsMtrPMsg.newXsCopyQty);
        // END   2018/08/27 [QC#24555, ADD]
        // START 2018/08/27 [QC#24555, ADD]
        setValue(inTMsg.newXsCopyQty, calcNewAllowance(inTMsg));
        // END 2018/08/27 [QC#24555, MOD]

        S21ApiTBLAccessor.insert(inTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0888E);
            return null;
        }
        return inTMsg;
    }

    // START 2018/03/27 M.Naito [QC#18672, ADD]
    /**
     * @param msgMap S21ApiMessageMap
     * @param crRebilDtlTMsg SVC_CR_REBIL_DTLTMsg
     * @param svcInvLineForAddlChrgMap Map<String, Object>
     * @param newAddlChrgDealAmt BigDecimal
     */
    public static void createCrRebilAddlBllg(S21ApiMessageMap msgMap, BigDecimal svcCrRebilDtlPk, Map<String, Object> svcInvLineForAddlChrgMap, BigDecimal newAddlChrgDealAmt) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();

        BigDecimal svcCrRebilAddlBllgPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CR_REBIL_ADDL_BLLG_SQ);

        SVC_CR_REBIL_ADDL_BLLGTMsg inTMsg = new SVC_CR_REBIL_ADDL_BLLGTMsg();
        setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inTMsg.svcCrRebilAddlBllgPk, svcCrRebilAddlBllgPk);
        setValue(inTMsg.svcCrRebilDtlPk, svcCrRebilDtlPk);
        setValue(inTMsg.dsContrDtlPk, (BigDecimal) svcInvLineForAddlChrgMap.get("DS_CONTR_DTL_PK"));
        setValue(inTMsg.addlBllgFromDt, (String) svcInvLineForAddlChrgMap.get("BLLG_PER_FROM_DT"));
        setValue(inTMsg.addlBllgThruDt, (String) svcInvLineForAddlChrgMap.get("BLLG_PER_THRU_DT"));
        BigDecimal oldAddlChrgDealAmt = (BigDecimal) svcInvLineForAddlChrgMap.get("INV_LINE_DEAL_NET_AMT");
        if (!hasValue(oldAddlChrgDealAmt)) {
            oldAddlChrgDealAmt = BigDecimal.ZERO;
        }
        setValue(inTMsg.oldAddlChrgDealAmt, oldAddlChrgDealAmt);
        if (!hasValue(newAddlChrgDealAmt)) {
            newAddlChrgDealAmt = BigDecimal.ZERO;
        }
        setValue(inTMsg.newAddlChrgDealAmt, newAddlChrgDealAmt);
        if (hasValue((BigDecimal) svcInvLineForAddlChrgMap.get("SVC_INV_LINE_ADDL_CHRG_PK"))) {
            setValue(inTMsg.svcInvLineAddlChrgPk, (BigDecimal) svcInvLineForAddlChrgMap.get("SVC_INV_LINE_ADDL_CHRG_PK"));
        }
        setValue(inTMsg.dsContrAddlChrgPk, (BigDecimal) svcInvLineForAddlChrgMap.get("DS_CONTR_ADDL_CHRG_PK"));

        S21ApiTBLAccessor.insert(inTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM1324E);
        }
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param crRebilDtlTMsg SVC_CR_REBIL_DTLTMsg
     * @param svcInvTMsg SVC_INVTMsg
     * @param addlChrgNotExistSvcInvLineMap Map<String, Object>
     * @param prntSvcCrRebilDtlPk BigDecimal
     */
    public static BigDecimal createCrRebilDtlForAddlChrg(S21ApiMessageMap msgMap, BigDecimal svcCrRebilPk, String origSvcInvNum, Map<String, Object> addlChrgNotExistSvcInvLineMap, BigDecimal prntSvcCrRebilDtlPk) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        BigDecimal svcCrRebilDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CR_REBIL_DTL_SQ);

        SVC_CR_REBIL_DTLTMsg inTMsg = new SVC_CR_REBIL_DTLTMsg();
        setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inTMsg.svcCrRebilDtlPk, svcCrRebilDtlPk);
        setValue(inTMsg.svcCrRebilPk, svcCrRebilPk);
        setValue(inTMsg.custIncdtId, pMsg.custIncdtId);
        setValue(inTMsg.origSvcInvNum, origSvcInvNum);
        setValue(inTMsg.ccyCd, (String) addlChrgNotExistSvcInvLineMap.get("DEAL_CCY_CD"));
        inTMsg.oldInvTotDealAmt.clear();
        inTMsg.oldInvTotFuncAmt.clear();
        inTMsg.newInvTotDealAmt.clear();
        inTMsg.newInvTotFuncAmt.clear();
        inTMsg.invTotDealNetAmt.clear();
        inTMsg.invTotFuncNetAmt.clear();
        inTMsg.crSvcInvNum.clear();
        inTMsg.rebilSvcInvNum.clear();
        setValue(inTMsg.invPrintFlg, FLG_ON_Y);
        setValue(inTMsg.dsContrPk, (BigDecimal) addlChrgNotExistSvcInvLineMap.get("DS_CONTR_PK"));
        setValue(inTMsg.prntSvcCrRebilDtlPk, prntSvcCrRebilDtlPk);

        S21ApiTBLAccessor.insert(inTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0885E);
        }
        return svcCrRebilDtlPk;
    }
    // END 2018/03/27 M.Naito [QC#18672, ADD]

    // START 04/18/2016 T.Aoyagi [QC#7056, MOD]
    /**
     * @param msgMap S21ApiMessageMap
     * @param crRebilMtrTMsg SVC_CR_REBIL_MTR_BLLGTMsg
     * @param svcInvMap Map<String, Object>
     * @param mtrReadPMsg NSZC053002_xxMeterReadChangesListPMsg
     * @return SVC_CR_REBIL_MTR_READTMsg
     */
    private static SVC_CR_REBIL_MTR_READTMsg createCrRebilMtrRead(S21ApiMessageMap msgMap, SVC_CR_REBIL_DTLTMsg crRebilDtlTMsg, Map<String, Object> svcInvMap
                                                                    , NSZC053002_xxMeterReadChangesListPMsg mtrReadPMsg) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();

        BigDecimal svcCrRebilMtrReadPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_CR_REBIL_MTR_READ_SQ);
        BigDecimal dsContrBllgMtrPk = (BigDecimal) svcInvMap.get("DS_CONTR_BLLG_MTR_PK");
        BigDecimal svcCrRebilMtrBllgPk = null;
        if (hasValue(dsContrBllgMtrPk)) {
            svcCrRebilMtrBllgPk = query.getCrRebilMtrBllgPk(msgMap, crRebilDtlTMsg.svcCrRebilDtlPk.getValue()
                                                                        , (BigDecimal) svcInvMap.get("DS_CONTR_BLLG_MTR_PK")
                                                                        , (String) svcInvMap.get("BLLG_PER_FROM_DT"));
        }

        SVC_CR_REBIL_MTR_READTMsg inTMsg = new SVC_CR_REBIL_MTR_READTMsg();
        setValue(inTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inTMsg.svcCrRebilMtrReadPk, svcCrRebilMtrReadPk);
        setValue(inTMsg.svcCrRebilMtrBllgPk, svcCrRebilMtrBllgPk);
        setValue(inTMsg.svcCrRebilDtlPk, crRebilDtlTMsg.svcCrRebilDtlPk);
        setValue(inTMsg.dsContrDtlPk, (BigDecimal) svcInvMap.get("DS_CONTR_DTL_PK"));
        setValue(inTMsg.dsContrBllgMtrPk, (BigDecimal) svcInvMap.get("DS_CONTR_BLLG_MTR_PK"));
        setValue(inTMsg.physMtrLbCd, (String) svcInvMap.get("MDL_MTR_LB_CD"));
        setValue(inTMsg.svcPhysMtrPk, (BigDecimal) svcInvMap.get("SVC_PHYS_MTR_PK"));
        setValue(inTMsg.startSvcPhysMtrReadPk, (BigDecimal) svcInvMap.get("START_SVC_PHYS_MTR_READ_PK"));
        setValue(inTMsg.endSvcPhysMtrReadPk, (BigDecimal) svcInvMap.get("END_SVC_PHYS_MTR_READ_PK"));
        setValue(inTMsg.startMtrReadDt, (String) svcInvMap.get("START_MTR_READ_DT"));
        setValue(inTMsg.endMtrReadDt, (String) svcInvMap.get("END_MTR_READ_DT"));
        setValue(inTMsg.oldStartReadMtrCnt, (BigDecimal) svcInvMap.get("START_READ_MTR_CNT"));
        setValue(inTMsg.oldEndReadMtrCnt, (BigDecimal) svcInvMap.get("END_READ_MTR_CNT"));;
        //START 2017/10/13 E.Kameishi [QC#18636,MOD]
        //setValue(inTMsg.oldStartTestMtrCnt, (BigDecimal) svcInvMap.get("START_TEST_MTR_CNT"));
        //setValue(inTMsg.oldEndTestMtrCnt, (BigDecimal) svcInvMap.get("END_TEST_MTR_CNT"));
        setValue(inTMsg.oldTestMtrCnt, (BigDecimal) svcInvMap.get("TEST_MTR_CNT"));
        // START 2017/09/26 K.Kitachi [QC#21212, MOD]
        if (mtrReadPMsg != null) {
            setValue(inTMsg.newStartReadMtrCnt, mtrReadPMsg.newStartReadMtrCnt);
            setValue(inTMsg.newEndReadMtrCnt, mtrReadPMsg.newEndReadMtrCnt);
            //setValue(inTMsg.newStartTestMtrCnt, mtrReadPMsg.newStartTestMtrCnt);
            //setValue(inTMsg.newEndTestMtrCnt, mtrReadPMsg.newEndTestMtrCnt);
            setValue(inTMsg.newTestMtrCnt, mtrReadPMsg.newTestMtrCnt);
        }
        // END 2017/09/26 K.Kitachi [QC#21212, MOD]
        //END 2017/10/13 E.Kameishi [QC#18636,MOD]
        // Add Start 05/11/2016 <QC#7764>
        setValue(inTMsg.oldStartMtrReadGrpSq, (BigDecimal) svcInvMap.get("PREV_PHYS_MTR_READ_GRP_SQ"));
        setValue(inTMsg.oldEndMtrReadGrpSq, (BigDecimal) svcInvMap.get("SVC_PHYS_MTR_READ_GRP_SQ"));
        // Add End   05/11/2016 <QC#7764>
        setValue(inTMsg.firstMtrReadFlg, (String) svcInvMap.get("FIRST_MTR_READ_FLG"));

        S21ApiTBLAccessor.insert(inTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0887E);
            return null;
        }
        return inTMsg;
    }
    // END 04/18/2016 T.Aoyagi [QC#7056, MOD]

    // START 04/18/2016 T.Aoyagi [QC#7056, ADD]
    private static void createCrRebilBaseForAggLine(S21ApiMessageMap msgMap, SVC_CR_REBIL_DTLTMsg crRebilDtlTMsg, List<Map<String, Object>> svcInvBaseList) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();

        if (svcInvBaseList.isEmpty()) {
            return;
        }
        String dsContrCatgCd = query.getDsContrCatgCd(glblCmpyCd, crRebilDtlTMsg.origSvcInvNum.getValue());
        if (!DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
            return;
        }
        // ----------------------------------------
        // Create SVC_CR_REBIL_BASE_BLLG
        // ----------------------------------------
        Map<String, Object> aggLineInfo = query.getBaseInfoForAggLine(msgMap, crRebilDtlTMsg.origSvcInvNum.getValue());
        if (aggLineInfo != null) {
            createCrRebilBaseBllg(msgMap, crRebilDtlTMsg, aggLineInfo, null);
        }
    }

    // START 2017/10/20 K.Kitachi [QC#21927, MOD]
//    private static void createCrRebilMtrForAggLine(S21ApiMessageMap msgMap, SVC_CR_REBIL_DTLTMsg crRebilDtlTMsg, List<Map<String, Object>> svcInvMtrList) {
    // START 2017/11/08 K.Kojima [QC#22301, MOD]
    // private static void createCrRebilMtrForAggLine(S21ApiMessageMap msgMap, SVC_CR_REBIL_DTLTMsg crRebilDtlTMsg, List<Map<String, Object>> svcInvMtrList, String svcInvNum) {
    private static void createCrRebilMtrForAggLine(S21ApiMessageMap msgMap, SVC_CR_REBIL_DTLTMsg crRebilDtlTMsg, List<Map<String, Object>> svcInvMtrList, String svcInvNum, String trgtSvcInvNum) {
        // END 2017/11/08 K.Kojima [QC#22301, MOD]
    // END 2017/10/20 K.Kitachi [QC#21927, MOD]

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        // START 2017/10/20 K.Kitachi [QC#21927, DEL]
//        String svcInvNum = crRebilDtlTMsg.origSvcInvNum.getValue();
        // END 2017/10/20 K.Kitachi [QC#21927, DEL]

        if (svcInvMtrList.isEmpty()) {
            return;
        }
        String dsContrCatgCd = query.getDsContrCatgCd(glblCmpyCd, svcInvNum);
        if (!DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
            return;
        }

        // ----------------------------------------
        // Create SVC_CR_REBIL_MTR_BLLG
        // ----------------------------------------
        // START 2017/11/08 K.Kojima [QC#22301,MOD]
        // List<Map<String, Object>> bllgMtrInfoList = query.getBllgMtrInfoForAggLine(msgMap, svcInvNum);
        List<Map<String, Object>> bllgMtrInfoList = query.getBllgMtrInfoForAggLine(msgMap, trgtSvcInvNum);
        // END 2017/11/08 K.Kojima [QC#22301,MOD]
        for (Map<String, Object> bllgMtrInfo : bllgMtrInfoList) {

            SVC_CR_REBIL_MTR_BLLGTMsg crRebilMtrTMsg = createCrRebilMtrBllg(msgMap, crRebilDtlTMsg, bllgMtrInfo);
            // START 2018/06/29 K.Kitachi [QC#22057, ADD]
            BigDecimal dsContrDtlPk = nullToZero((BigDecimal) bllgMtrInfo.get("DS_CONTR_DTL_PK"));
            // END 2018/06/29 K.Kitachi [QC#22057, ADD]
            String bllgMtrLbCd = (String) bllgMtrInfo.get("MTR_LB_CD");
            String bllgPerFromDt = (String) bllgMtrInfo.get("BLLG_PER_FROM_DT");

            // ----------------------------------------
            // Create SVC_CR_REBIL_XS_MTR_BLLG
            // ----------------------------------------
            // START 2017/11/08 K.Kojima [QC#22301,MOD]
            // List<Map<String, Object>> xsMtrInfoList = query.getXsMtrInfoForAggLine(msgMap, svcInvNum, bllgMtrLbCd, bllgPerFromDt);
            List<Map<String, Object>> xsMtrInfoList = query.getXsMtrInfoForAggLine(msgMap, trgtSvcInvNum, bllgMtrLbCd, bllgPerFromDt);
            // END 2017/11/08 K.Kojima [QC#22301,MOD]
            // START 2023/01/16 R.Jin [QC#58890, MOD]
            int aggtirRownum =0;
            for (Map<String, Object> xsMtrInfo : xsMtrInfoList) {

                BigDecimal oldXsCopyQty = (BigDecimal) xsMtrInfo.get("XS_MTR_COPY_QTY");
                aggtirRownum++;
                // START 2018/06/29 K.Kitachi [QC#22057, MOD]
//                NSZC053002_xxPriceChangesListPMsg xsMtrPMsg = getXsInfoFromPMsg(msgMap, svcInvNum, null, bllgMtrLbCd, oldXsCopyQty);
//                NSZC053002_xxPriceChangesListPMsg xsMtrPMsg = getXsInfoFromPMsg(msgMap, svcInvNum, dsContrDtlPk, null, bllgMtrLbCd, oldXsCopyQty);
                NSZC053002_xxPriceChangesListPMsg xsMtrPMsg = getXsInfoFromPMsg(msgMap, svcInvNum, dsContrDtlPk, null, bllgMtrLbCd, oldXsCopyQty, aggtirRownum);
                // END 2018/06/29 K.Kitachi [QC#22057, MOD]
                createCrRebilXsMtrBllg(msgMap, crRebilMtrTMsg, xsMtrInfo, xsMtrPMsg);
            }
            // END 2023/01/16 R.Jin [QC#58890, MOD]
        }
    }
    // END 04/18/2016 T.Aoyagi [QC#7056, ADD]

    // START 2018/06/05 K.Kojima [QC#21974,ADD]
    public static void adjustMtrRead(S21ApiMessageMap msgMap, SVC_CR_REBILTMsg crRebilTMsg, List<SVC_CR_REBIL_MTR_READTMsg> fixMtrReadList) {
        adjustMtrRead(msgMap, crRebilTMsg, fixMtrReadList, null, null);
    }
    // END 2018/06/05 K.Kojima [QC#21974,ADD]

    /**
     * @param msgMap S21ApiMessageMap
     * @param crRebilTMsg SVC_CR_REBILTMsg
     * @param fixMtrReadList List<SVC_CR_REBIL_MTR_READTMsg>
     */
    // START 2018/06/05 K.Kojima [QC#21974,MOD]
    // public static void adjustMtrRead(S21ApiMessageMap msgMap, SVC_CR_REBILTMsg crRebilTMsg, List<SVC_CR_REBIL_MTR_READTMsg> fixMtrReadList) {
    public static void adjustMtrRead(S21ApiMessageMap msgMap, SVC_CR_REBILTMsg crRebilTMsg, List<SVC_CR_REBIL_MTR_READTMsg> fixMtrReadList, String svcInvNum, List<String> tmpSvcInvNumList) {
    // END 2018/06/05 K.Kojima [QC#21974,MOD]

        if (fixMtrReadList.isEmpty()) {
            return;
        }

        // Meter Read of previous month
        // START 2018/06/05 K.Kojima [QC#21974,MOD]
        // createPrevNextMtrRead(msgMap, crRebilTMsg, fixMtrReadList, FLG_ON_Y);
        String prevSvcInvNum = createPrevNextMtrRead(msgMap, crRebilTMsg, fixMtrReadList, FLG_ON_Y);
        // END 2018/06/05 K.Kojima [QC#21974,MOD]
        // Meter Read of next month
        // START 2018/06/05 K.Kojima [QC#21974,MOD]
        // createPrevNextMtrRead(msgMap, crRebilTMsg, fixMtrReadList, FLG_OFF_N);
        String nextSvcInvNum = createPrevNextMtrRead(msgMap, crRebilTMsg, fixMtrReadList, FLG_OFF_N);
        // END 2018/06/05 K.Kojima [QC#21974,MOD]

        // START 2018/06/05 K.Kojima [QC#21974,ADD]
        if (hasValue(svcInvNum)) {
            NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
            BigDecimal countFreeCopyRollOverUsed = query.countFreeCopyRollOverUsed(pMsg.glblCmpyCd.getValue(), svcInvNum, prevSvcInvNum, nextSvcInvNum);
            if (countFreeCopyRollOverUsed == null || countFreeCopyRollOverUsed.compareTo(BigDecimal.ZERO) == 0) {
                return;
            }
            String tmpSvcInvNum = svcInvNum;
            if (nextSvcInvNum != null) {
                tmpSvcInvNum = nextSvcInvNum;
            }
            List<String> trgtSvcInvNumList = query.getTrgtSvcInvNumList(msgMap, tmpSvcInvNum);
            for (String trgtSvcInvNum : trgtSvcInvNumList) {
                if (tmpSvcInvNum.equals(trgtSvcInvNum)) {
                    continue;
                }
                if (tmpSvcInvNumList.contains(trgtSvcInvNum)) {
                    continue;
                }
                NSZC053001CommonLogic.createCrRebilInfo(msgMap, svcInvNum, trgtSvcInvNum, crRebilTMsg);
                tmpSvcInvNumList.add(trgtSvcInvNum);
            }
        }
        // END 2018/06/05 K.Kojima [QC#21974,ADD]
    }

    /**
     * @param msgMap
     * @param crRebilTMsg
     * @param fixMtrReadList
     * @param prevFlg
     * @param freeCopyUsedFlg
     * @return boolean freeCopyUsedFlg
     */
    // START 2018/06/05 K.Kojima [QC#21974,MOD]
    // private static void createPrevNextMtrRead(S21ApiMessageMap msgMap, SVC_CR_REBILTMsg crRebilTMsg, List<SVC_CR_REBIL_MTR_READTMsg> fixMtrReadList, String prevFlg) {
    private static String createPrevNextMtrRead(S21ApiMessageMap msgMap, SVC_CR_REBILTMsg crRebilTMsg, List<SVC_CR_REBIL_MTR_READTMsg> fixMtrReadList, String prevFlg) {
    // END 2018/06/05 K.Kojima [QC#21974,MOD]

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        BigDecimal svcCrRebilPk = crRebilTMsg.svcCrRebilPk.getValue();
        // Mod Start 05/11/2016 <QC#7764> 
        BigDecimal curMtrReadPk = null;
        for (SVC_CR_REBIL_MTR_READTMsg curMtrReadTMsg : fixMtrReadList) {
            // START 2018/07/18 K.Kojima [QC#26791,ADD]
            if (!hasValue(curMtrReadTMsg.dsContrBllgMtrPk)) {
                continue;
            }
            // END 2018/07/18 K.Kojima [QC#26791,ADD]
            if (FLG_ON_Y.equals(prevFlg) && hasValue(curMtrReadTMsg.newStartReadMtrCnt)) {
                curMtrReadPk = curMtrReadTMsg.svcCrRebilMtrReadPk.getValue();
                break;
            } else if (FLG_OFF_N.equals(prevFlg) && hasValue(curMtrReadTMsg.newEndReadMtrCnt)) {
                curMtrReadPk = curMtrReadTMsg.svcCrRebilMtrReadPk.getValue();
                break;
            }
        }
        if (curMtrReadPk == null) {
            // START 2018/06/05 K.Kojima [QC#21974,MOD]
            // return;
            return null;
            // END 2018/06/05 K.Kojima [QC#21974,MOD]
        }
        // Mod End 05/11/2016 <QC#7764> 

        // Get Service Invoice Number
        Map<String, Object> svcInvMap = query.getSvcInvForPrevNextMtrRead(glblCmpyCd, curMtrReadPk, prevFlg);
        String svcInvNum = "";
        if (svcInvMap != null) {
            svcInvNum = (String) svcInvMap.get("SVC_INV_NUM");
        }
        if (!hasValue(svcInvNum)) {
            // START 2018/06/05 K.Kojima [QC#21974,MOD]
            // return;
            return null;
            // END 2018/06/05 K.Kojima [QC#21974,MOD]
        }

        // START 05/10/2016 T.Aoyagi [QC#7734, ADD]
        BigDecimal registeredSvcCrRebilPk = query.getRegisteredSvcCrRebilPk(msgMap, svcInvNum, svcCrRebilPk);
        if (hasValue(registeredSvcCrRebilPk)) {
            // START 2018/06/05 K.Kojima [QC#21974,MOD]
            // return;
            return null;
            // END 2018/06/05 K.Kojima [QC#21974,MOD]
        }
        // END 05/10/2016 T.Aoyagi [QC#7734, ADD]

        // ----------------------------------------
        // Create SVC_CR_REBIL Info
        // ----------------------------------------
        SVC_CR_REBIL_DTLTMsg crRebilDtlTMsg = query.getSvcCrRebilDtlTMsg(glblCmpyCd, svcCrRebilPk, svcInvNum);
        if (crRebilDtlTMsg == null) {
            NSZC053001CommonLogic.createCrRebilInfo(msgMap, svcInvNum, crRebilTMsg, fixMtrReadList);
        }

        // ----------------------------------------
        // Update Previous or Next Meter Read
        // ----------------------------------------
        for (SVC_CR_REBIL_MTR_READTMsg curMtrReadTMsg : fixMtrReadList) {

            Map<String, BigDecimal> prevNextMtrReadMap = query.getPrevNextMtrRead(glblCmpyCd, curMtrReadTMsg.svcCrRebilMtrReadPk.getValue(), prevFlg);

            if (prevNextMtrReadMap != null) {
                updateCrRebilMtrRead(msgMap, curMtrReadTMsg, prevNextMtrReadMap, prevFlg);
            }
        }

        // START 2018/06/05 K.Kojima [QC#21974,ADD]
        return svcInvNum;
        // END 2018/06/05 K.Kojima [QC#21974,ADD]
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param onBatchTp ONBATCH_TYPE
     * @param crRebilTMsg SVC_CR_REBILTMsg
     * @param estFlg String
     * @param delFlg String
     */
    public static void callApi(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchTp, SVC_CR_REBILTMsg crRebilTMsg, String estFlg, String delFlg) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        BigDecimal crRebilPk = crRebilTMsg.svcCrRebilPk.getValue();

        SVC_CR_REBIL_DTLTMsgArray crRebilDtlTMsgArray = query.getSvcCrRebilDtlTMsg(glblCmpyCd, crRebilPk);

        for (int i = 0; i < crRebilDtlTMsgArray.getValidCount(); i++) {
            SVC_CR_REBIL_DTLTMsg crRebilDtlTMsg =  crRebilDtlTMsgArray.no(i);
            String dsContrCatgCd = query.getDsContrCatgCd(glblCmpyCd, crRebilDtlTMsg.origSvcInvNum.getValue());
            BigDecimal crRebilDtlPk = crRebilDtlTMsg.svcCrRebilDtlPk.getValue();

            // ----------------------------------------
            // call Billing Schedule API
            // ----------------------------------------
            callBllgShcdApi(msgMap, onBatchTp, crRebilDtlTMsg, estFlg, delFlg);

            // ----------------------------------------
            // call Fleet Calculation API
            // ----------------------------------------
            if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
                // START 04/11/2016 T.Aoyagi [QC#6715, MOD]
                // START 04/26/2016 T.Aoyagi [QC#7578, MOD]
                List<Map<String, Object>> schdInfoList = query.getSchdAndDsContr(glblCmpyCd, crRebilPk, crRebilDtlPk, DS_CONTR_DTL_TP.FLEET);
                for (Map<String, Object> schdInfoMap : schdInfoList) {
                    callFleetCalcApi(msgMap, schdInfoMap, onBatchTp);
                }
                // END 04/26/2016 T.Aoyagi [QC#7578, MOD]
                // END 04/11/2016 T.Aoyagi [QC#6715, MOD]
            }

            // ----------------------------------------
            // call Billing Calculation API
            // ----------------------------------------
            callBllgCalcApi(msgMap, onBatchTp, crRebilDtlTMsg, estFlg, dsContrCatgCd);

            // ----------------------------------------
            // call Aggregate Calculation API
            // ----------------------------------------
            if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
                // START 04/11/2016 T.Aoyagi [QC#6715, MOD]
                Map<String, Object> svcInvInfoMap = query.getSvcInvAndDsContr(glblCmpyCd, crRebilPk, crRebilDtlPk, DS_CONTR_DTL_TP.AGGREGATE);
                if (svcInvInfoMap != null) {
                    callAggCalcApi(msgMap, svcInvInfoMap, onBatchTp);
                }
                // END 04/11/2016 T.Aoyagi [QC#6715, MOD]
            }
        }
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param crRebilDtlTMsg SVC_CR_REBIL_DTLTMsg
     * @param onBatchTp ONBATCH_TYPE
     * @param estFlg String
     * @param delFlg String
     */
    public static void callBllgShcdApi(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchTp, SVC_CR_REBIL_DTLTMsg crRebilDtlTMsg, String estFlg, String delFlg) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        BigDecimal svcCrRebilDtlPk = crRebilDtlTMsg.svcCrRebilDtlPk.getValue();

        NSZC047007PMsg apiPMsg = new NSZC047007PMsg();
        setValue(apiPMsg.glblCmpyCd, glblCmpyCd);
        setValue(apiPMsg.xxModeCd, "07");
        setValue(apiPMsg.slsDt, pMsg.slsDt);
        setValue(apiPMsg.svcInvNum, crRebilDtlTMsg.origSvcInvNum);
        setValue(apiPMsg.svcCrRebilPk, crRebilDtlTMsg.svcCrRebilPk);
        setValue(apiPMsg.svcCrRebilDtlPk, crRebilDtlTMsg.svcCrRebilDtlPk);
        setValue(apiPMsg.svcCrRebilProcCd, SVC_CR_REBIL_PROC.CREDIT_AND_REBILL);
        setValue(apiPMsg.estFlg, estFlg);
        setValue(apiPMsg.delFlg, delFlg);

        int baseCnt = 0;
        List<Map<String, Object>> crRebilBaseList = query.getSvcCrBaseBllg(msgMap, svcCrRebilDtlPk);
        for (Map<String, Object> crRebilBaseInfo : crRebilBaseList) {
            setValue(apiPMsg.xxBaseLineList.no(baseCnt).svcInvLinePk_BL, (BigDecimal) crRebilBaseInfo.get("SVC_INV_LINE_PK"));
            setValue(apiPMsg.xxBaseLineList.no(baseCnt).dsContrDtlPk_BL, (BigDecimal) crRebilBaseInfo.get("DS_CONTR_DTL_PK"));
            setValue(apiPMsg.xxBaseLineList.no(baseCnt).basePrcDealAmt_BL, (BigDecimal) crRebilBaseInfo.get("NEW_BASE_DEAL_AMT"));
            baseCnt++;
            apiPMsg.xxBaseLineList.setValidCount(baseCnt);
        }

        // mod start 2016/04/28 CSA Defect#7056
        int usgCnt = 0;
        List<Map<String, Object>> crRebilXsMtrList = query.getSvcCrXsMtrBllg(msgMap, svcCrRebilDtlPk);
        //start 2017/11/07 QC#22301,mod
        BigDecimal xsMtrCopyQty = null;
        for (Map<String, Object> crRebilXsMtrInfo : crRebilXsMtrList) {
            setValue(apiPMsg.xxMtrLineList.no(usgCnt).svcInvLinePk_ML, (BigDecimal) crRebilXsMtrInfo.get("SVC_INV_LINE_PK"));
            setValue(apiPMsg.xxMtrLineList.no(usgCnt).dsContrDtlPk_ML, (BigDecimal) crRebilXsMtrInfo.get("DS_CONTR_DTL_PK"));
            setValue(apiPMsg.xxMtrLineList.no(usgCnt).dsContrBllgMtrPk_ML, (BigDecimal) crRebilXsMtrInfo.get("DS_CONTR_BLLG_MTR_PK"));
            setValue(apiPMsg.xxMtrLineList.no(usgCnt).contrXsCopyPk_ML, (BigDecimal) crRebilXsMtrInfo.get("CONTR_XS_COPY_PK"));
            xsMtrCopyQty = (BigDecimal) crRebilXsMtrInfo.get("NEW_XS_COPY_QTY");
            if (!hasValue(xsMtrCopyQty)) {
                xsMtrCopyQty = (BigDecimal) crRebilXsMtrInfo.get("OLD_XS_COPY_QTY");
            }
            setValue(apiPMsg.xxMtrLineList.no(usgCnt).xsMtrCopyQty_ML, xsMtrCopyQty);
            setValue(apiPMsg.xxMtrLineList.no(usgCnt).xsMtrAmtRate_ML, (BigDecimal) crRebilXsMtrInfo.get("NEW_XS_MTR_AMT_RATE"));
            usgCnt++;
            apiPMsg.xxMtrLineList.setValidCount(usgCnt);
        }
        //end 2017/11/07 QC#22301,mod
        // mod end 2016/04/28 CSA Defect#7056

        NSZC047001 api = new NSZC047001();
        api.execute(apiPMsg, onBatchTp);
        if (apiPMsg.xxMsgIdList.getValidCount() > 0) {
            S21ApiMessage msg = S21ApiUtil.getXxMsgList(apiPMsg).get(0);
            String msgId = msg.getXxMsgid();
            String[] msgPrms = msg.getXxMsgPrmArray();
            msgMap.addXxMsgIdWithPrm(msgId, msgPrms);
        }
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param onBatchTp ONBATCH_TYPE
     * @param crRebilDtlTMsg SVC_CR_REBIL_DTLTMsg
     * @param estFlg String
     * @param dsContrCatgCd String
     */
    public static void callBllgCalcApi(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchTp, SVC_CR_REBIL_DTLTMsg crRebilDtlTMsg, String estFlg, String dsContrCatgCd) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        BigDecimal svcCrRebilPk = crRebilDtlTMsg.svcCrRebilPk.getValue();
        BigDecimal svcCrRebilDtlPk = crRebilDtlTMsg.svcCrRebilDtlPk.getValue();

        SVC_CR_REBIL_BASE_BLLGTMsgArray baseArray = query.getSvcCrRebilBaseBllgTMsg(glblCmpyCd, svcCrRebilDtlPk);
        for (int i = 0; i < baseArray.getValidCount(); i++) {
            SVC_CR_REBIL_BASE_BLLGTMsg baseTMsg = baseArray.no(i);

            if (isSkipContr(glblCmpyCd, dsContrCatgCd, baseTMsg.dsContrDtlPk.getValue())) {
                continue;
            }
            NSZC056001PMsg apiPMsg = new NSZC056001PMsg();
            setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
            setValue(apiPMsg.xxModeCd, "03");
            setValue(apiPMsg.slsDt, pMsg.slsDt);
            setValue(apiPMsg.dsContrDtlPk, baseTMsg.dsContrDtlPk);
            apiPMsg.dsContrBllgMtrPk.clear();
            setValue(apiPMsg.baseChrgFlg, FLG_ON_Y);
            setValue(apiPMsg.usgChrgFlg, FLG_OFF_N);
            // Mod Start 05/12/2016 <QC#8183>
            setValue(apiPMsg.svcCrRebilDtlPk, svcCrRebilDtlPk);
            // Mod End   05/12/2016 <QC#8183>
            setValue(apiPMsg.estFlg, estFlg);
            setValue(apiPMsg.nextBllgDt, pMsg.slsDt);

            NSZC056001 api = new NSZC056001();
            api.execute(apiPMsg, onBatchTp);
            if (apiPMsg.xxMsgIdList.getValidCount() > 0) {
                S21ApiMessage msg = S21ApiUtil.getXxMsgList(apiPMsg).get(0);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                msgMap.addXxMsgIdWithPrm(msgId, msgPrms);
            }
        }

        SVC_CR_REBIL_MTR_BLLGTMsgArray mtrArray = query.getSvcCrRebilMtrBllgTMsg(glblCmpyCd, svcCrRebilDtlPk);
        for (int i = 0; i < mtrArray.getValidCount(); i++) {
            SVC_CR_REBIL_MTR_BLLGTMsg mtrTMsg = mtrArray.no(i);

            if (isSkipContr(glblCmpyCd, dsContrCatgCd, mtrTMsg.dsContrDtlPk.getValue())) {
                continue;
            }
            NSZC056001PMsg apiPMsg = new NSZC056001PMsg();
            setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
            setValue(apiPMsg.xxModeCd, "03");
            setValue(apiPMsg.slsDt, pMsg.slsDt);
            setValue(apiPMsg.dsContrDtlPk, mtrTMsg.dsContrDtlPk);
            setValue(apiPMsg.dsContrBllgMtrPk, mtrTMsg.dsContrBllgMtrPk);
            setValue(apiPMsg.baseChrgFlg, FLG_OFF_N);
            setValue(apiPMsg.usgChrgFlg, FLG_ON_Y);
            // Mod Start 05/12/2016 <QC#8183>
            setValue(apiPMsg.svcCrRebilDtlPk, svcCrRebilDtlPk);
            // Mod End   05/12/2016 <QC#8183>
            setValue(apiPMsg.estFlg, estFlg);
            setValue(apiPMsg.nextBllgDt, pMsg.slsDt);

            NSZC056001 api = new NSZC056001();
            api.execute(apiPMsg, onBatchTp);
            if (apiPMsg.xxMsgIdList.getValidCount() > 0) {
                S21ApiMessage msg = S21ApiUtil.getXxMsgList(apiPMsg).get(0);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                msgMap.addXxMsgIdWithPrm(msgId, msgPrms);
            }
        }
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param schdInfoMap Map<String, Object>
     * @param onBatchTp ONBATCH_TYPE
     */
    public static void callFleetCalcApi(S21ApiMessageMap msgMap, Map<String, Object> schdInfoMap, ONBATCH_TYPE onBatchTp) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();

        NSZC035001PMsg apiPMsg = new NSZC035001PMsg();
        setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(apiPMsg.xxModeCd, "01");
        setValue(apiPMsg.dsContrNum, (String) schdInfoMap.get("DS_CONTR_NUM"));
        setValue(apiPMsg.prntDsContrBllgSchdPk, (BigDecimal) schdInfoMap.get("DS_CONTR_BLLG_SCHD_PK"));
        setValue(apiPMsg.bllgDt, (String) schdInfoMap.get("BLLG_SCHD_FROM_DT"));
        setValue(apiPMsg.svcCrRebilPk, (BigDecimal) schdInfoMap.get("SVC_CR_REBIL_PK"));
        setValue(apiPMsg.svcCrRebilDtlPk, (BigDecimal) schdInfoMap.get("SVC_CR_REBIL_DTL_PK"));

        NSZC035001 api = new NSZC035001();
        api.execute(apiPMsg, onBatchTp);
        if (apiPMsg.xxMsgIdList.getValidCount() > 0) {
            S21ApiMessage msg = S21ApiUtil.getXxMsgList(apiPMsg).get(0);
            String msgId = msg.getXxMsgid();
            String[] msgPrms = msg.getXxMsgPrmArray();
            msgMap.addXxMsgIdWithPrm(msgId, msgPrms);
        }
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param svcInvInfoMap Map<String, Object> svcInvInfoMap
     * @param onBatchTp ONBATCH_TYPE
     */
    public static void callAggCalcApi(S21ApiMessageMap msgMap, Map<String, Object> svcInvInfoMap, ONBATCH_TYPE onBatchTp) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();

        NSZC034001PMsg apiPMsg = new NSZC034001PMsg();
        setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(apiPMsg.dsContrNum, (String) svcInvInfoMap.get("DS_CONTR_NUM"));
        setValue(apiPMsg.slsDt, pMsg.slsDt);
        // START 04/11/2016 T.Aoyagi [QC#6715, MOD]
        setValue(apiPMsg.bllgDt, (String) svcInvInfoMap.get("SVC_CONTR_BLLG_FROM_DT"));
        // END 04/11/2016 T.Aoyagi [QC#6715, MOD]

        NSZC034001 api = new NSZC034001();
        api.execute(apiPMsg, onBatchTp);
        if (apiPMsg.xxMsgIdList.getValidCount() > 0) {
            S21ApiMessage msg = S21ApiUtil.getXxMsgList(apiPMsg).get(0);
            String msgId = msg.getXxMsgid();
            String[] msgPrms = msg.getXxMsgPrmArray();
            msgMap.addXxMsgIdWithPrm(msgId, msgPrms);
        }
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param svcCrRebilMtrReadPk BigDecimal
     * @param crRebilMtrReadTMsg SVC_CR_REBIL_MTR_READTMsg
     * @param prevFlg String
     */
    private static void updateCrRebilMtrRead(S21ApiMessageMap msgMap, SVC_CR_REBIL_MTR_READTMsg crRebilMtrReadTMsg, Map<String, BigDecimal> prevNextMtrReadMap, String prevFlg) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();

        SVC_CR_REBIL_MTR_READTMsg inTMsg = new SVC_CR_REBIL_MTR_READTMsg();
        setValue(inTMsg.glblCmpyCd, glblCmpyCd);
        setValue(inTMsg.svcCrRebilMtrReadPk, prevNextMtrReadMap.get("SVC_CR_REBIL_MTR_READ_PK"));
        inTMsg = (SVC_CR_REBIL_MTR_READTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inTMsg);
        if (inTMsg == null) {
            msgMap.addXxMsgId(NSZM0894E);
            return;
        }

        SVC_PHYS_MTR_READTMsg startMtrReadTMsg = query.getSvcPhysMtrReadTMsg(glblCmpyCd, inTMsg.startSvcPhysMtrReadPk.getValue());
        SVC_PHYS_MTR_READTMsg endMtrReadTMsg = query.getSvcPhysMtrReadTMsg(glblCmpyCd, inTMsg.endSvcPhysMtrReadPk.getValue());

        if (startMtrReadTMsg != null) {
            setValue(inTMsg.physMtrLbCd, startMtrReadTMsg.mtrLbCd);
            setValue(inTMsg.startMtrReadDt, startMtrReadTMsg.mtrReadDt);
        }
        if (endMtrReadTMsg != null) {
            //START 2017/10/23 K.Ochiai [QC#22009,Mod]
            setValue(inTMsg.endMtrReadDt, endMtrReadTMsg.mtrReadDt);
            //END 2017/10/23 K.Ochiai [QC#22009,Mod]
        }

        if (FLG_ON_Y.equals(prevFlg)) {
            setValue(inTMsg.newEndReadMtrCnt, crRebilMtrReadTMsg.newStartReadMtrCnt);
            //START 2017/09/19 E.Kameishi [QC#18636,DEL]
            //setValue(inTMsg.newEndTestMtrCnt, crRebilMtrReadTMsg.newStartTestMtrCnt);
            //END 2017/09/19 E.Kameishi [QC#18636,DEL]
        } else {
            setValue(inTMsg.newStartReadMtrCnt, crRebilMtrReadTMsg.newEndReadMtrCnt);
            //START 2017/09/19 E.Kameishi [QC#18636,DEL]
            //setValue(inTMsg.newStartTestMtrCnt, crRebilMtrReadTMsg.newEndTestMtrCnt);
            //END 2017/09/19 E.Kameishi [QC#18636,DEL]
        }
        S21ApiTBLAccessor.update(inTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0881E);
        }
    }

    private static boolean isSkipContr(String glblCmpyCd, String dsContrCatgCd, BigDecimal dsContrDtlPk) {

        DS_CONTR_DTLTMsg dsContrDtlTMsg = query.getDsContrDtlTMsg(glblCmpyCd, dsContrDtlPk);

        if (dsContrDtlTMsg == null) {
            return true;
        }
        String dsContrDtlTpCd = dsContrDtlTMsg.dsContrDtlTpCd.getValue();

        if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd) && !DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTpCd)) {
            return true;
        } else if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd) && !DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTpCd)) {
            return true;
        }
        return false;
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param crRebilTMsg SVC_CR_REBILTMsg
     */
    public static void updateCrRebilInfo(S21ApiMessageMap msgMap, SVC_CR_REBILTMsg crRebilTMsg) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        BigDecimal crRebilPk = crRebilTMsg.svcCrRebilPk.getValue();

        SVC_CR_REBIL_DTLTMsgArray crRebilDtlTMsgArray = query.getSvcCrRebilDtlTMsg(glblCmpyCd, crRebilPk);
        for (int i = 0; i < crRebilDtlTMsgArray.getValidCount(); i++) {
            BigDecimal crRebilDtlPk = crRebilDtlTMsgArray.no(i).svcCrRebilDtlPk.getValue();
            udapteCrRebilDtl(msgMap, crRebilPk, crRebilDtlPk);

            List<Map<String, Object>> svcContrMtrList = query.getSvcContrMtrInfo(glblCmpyCd, crRebilPk, crRebilDtlPk);
            for (Map<String, Object> svcContrMtrInfo : svcContrMtrList) {
                updateCrRebilMtrBllg(msgMap, svcContrMtrInfo);

                // START 2018/03/27 M.Naito [QC#18672, ADD]
                //update SVC_CR_REBIL_ADDL_BLLG
                // START 2022/05/27 K.Kitachi [QC#60121, DEL]
//                List<Map<String, Object>> svcContrAddlChrgList = query.getCrRebilAddlInfoForUpdate(glblCmpyCd, crRebilPk, crRebilDtlPk, ADDL_CHRG_INV_TP.USAGE);
                // END 2022/05/27 K.Kitachi [QC#60121, DEL]
                SVC_CR_REBIL_MTR_BLLGTMsg svcCrRebilMtrBllgTMsg = new SVC_CR_REBIL_MTR_BLLGTMsg();
                setValue(svcCrRebilMtrBllgTMsg.glblCmpyCd, glblCmpyCd);
                setValue(svcCrRebilMtrBllgTMsg.svcCrRebilMtrBllgPk, (BigDecimal) svcContrMtrInfo.get("SVC_CR_REBIL_MTR_BLLG_PK"));
                svcCrRebilMtrBllgTMsg = (SVC_CR_REBIL_MTR_BLLGTMsg) S21ApiTBLAccessor.findByKey(svcCrRebilMtrBllgTMsg);
                // START 2022/05/27 K.Kitachi [QC#60121, ADD]
                BigDecimal prntSvcInvLinePk = svcCrRebilMtrBllgTMsg.svcInvLinePk.getValue();
                if (!hasValue(prntSvcInvLinePk)) {
                    continue;
                }
                List<Map<String, Object>> svcContrAddlChrgList = query.getCrRebilAddlInfoForUpdate(glblCmpyCd, crRebilPk, crRebilDtlPk, ADDL_CHRG_INV_TP.USAGE, prntSvcInvLinePk);
                // END 2022/05/27 K.Kitachi [QC#60121, ADD]
                for (Map<String, Object> svcContrAddlChrgInfo : svcContrAddlChrgList) {
                    // START 2022/05/18 K.Kitachi [QC#60054, ADD]
                    if (!hasValue((String) svcContrAddlChrgInfo.get("ADDL_CHRG_INV_TP_CD"))) {
                        continue;
                    }
                    // END 2022/05/18 K.Kitachi [QC#60054, ADD]
                    updateCrRebilAddlBllg(msgMap, svcCrRebilMtrBllgTMsg.svcInvLinePk.getValue(), svcCrRebilMtrBllgTMsg.newMtrChrgDealAmt.getValue(), svcContrAddlChrgInfo);
                }
                // END 2018/03/27 M.Naito [QC#18672, ADD]
            }

            List<Map<String, Object>> svcContrXsMtrList = query.getSvcContrXsMtrInfo(glblCmpyCd, crRebilPk, crRebilDtlPk);
            for (Map<String, Object> svcContrXsMtrInfo : svcContrXsMtrList) {
                updateCrRebilXsMtrBllg(msgMap, svcContrXsMtrInfo);

                // START 2022/05/27 K.Kitachi [QC#60121, DEL]
//                // START 2018/03/27 M.Naito [QC#18672, ADD]
//                //update SVC_CR_REBIL_ADDL_BLLG
//                List<Map<String, Object>> svcContrAddlChrgList = query.getCrRebilAddlInfoForUpdate(glblCmpyCd, crRebilPk, crRebilDtlPk, ADDL_CHRG_INV_TP.USAGE);
//                SVC_CR_REBIL_XS_MTR_BLLGTMsg svcCrRebilXsMtrBllgTMsg = new SVC_CR_REBIL_XS_MTR_BLLGTMsg();
//                setValue(svcCrRebilXsMtrBllgTMsg.glblCmpyCd, glblCmpyCd);
//                setValue(svcCrRebilXsMtrBllgTMsg.svcCrRebilXsMtrBllgPk, (BigDecimal) svcContrXsMtrInfo.get("SVC_CR_REBIL_XS_MTR_BLLG_PK"));
//                svcCrRebilXsMtrBllgTMsg = (SVC_CR_REBIL_XS_MTR_BLLGTMsg) S21ApiTBLAccessor.findByKey(svcCrRebilXsMtrBllgTMsg);
//
//                for (Map<String, Object> svcContrAddlChrgInfo : svcContrAddlChrgList) {
//                    // START 2022/05/18 K.Kitachi [QC#60054, ADD]
//                    if (!hasValue((String) svcContrAddlChrgInfo.get("ADDL_CHRG_INV_TP_CD"))) {
//                        continue;
//                    }
//                    // END 2022/05/18 K.Kitachi [QC#60054, ADD]
//                    updateCrRebilAddlBllg(msgMap, svcCrRebilXsMtrBllgTMsg.svcInvLinePk.getValue(), svcCrRebilXsMtrBllgTMsg.newXsMtrChrgDealAmt.getValue(), svcContrAddlChrgInfo);
//                }
//                // END 2018/03/27 M.Naito [QC#18672, ADD]
                // END 2022/05/27 K.Kitachi [QC#60121, DEL]
            }
        }
    }

    private static void udapteCrRebilDtl(S21ApiMessageMap msgMap, BigDecimal crRebilPk, BigDecimal crRebilDtlPk) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();

        SVC_CR_REBIL_DTLTMsg inTMsg = new SVC_CR_REBIL_DTLTMsg();
        setValue(inTMsg.glblCmpyCd, glblCmpyCd);
        setValue(inTMsg.svcCrRebilDtlPk, crRebilDtlPk);
        inTMsg = (SVC_CR_REBIL_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inTMsg);
        if (inTMsg == null) {
            msgMap.addXxMsgId(NSZM0892E);
            return;
        }

        BigDecimal baseChrgDealAmt = query.getBaseDealAmt(glblCmpyCd, crRebilPk, crRebilDtlPk);
        BigDecimal mtrChrgDealAmt = query.getMtrChrgDealAmt(glblCmpyCd, crRebilPk, crRebilDtlPk);
        // START 2018/03/27 M.Naito [QC#18672, MOD]
        BigDecimal addlChrgDealAmt = query.getAddlChrgDealAmt(glblCmpyCd, crRebilPk, crRebilDtlPk);
//        BigDecimal newInvTotDealAmt = baseChrgDealAmt.add(mtrChrgDealAmt);
        BigDecimal newInvTotDealAmt = baseChrgDealAmt.add(mtrChrgDealAmt.add(addlChrgDealAmt));
        // END 2018/03/27 M.Naito [QC#18672, MOD]
        BigDecimal oldInvTotDealAmt = inTMsg.oldInvTotDealAmt.getValue();
        if (!hasValue(oldInvTotDealAmt)) {
            oldInvTotDealAmt = BigDecimal.ZERO;
        }
        BigDecimal invTotDealNetAmt = newInvTotDealAmt.subtract(oldInvTotDealAmt);

        setValue(inTMsg.newInvTotDealAmt, newInvTotDealAmt);
        setValue(inTMsg.invTotDealNetAmt, invTotDealNetAmt);
        S21ApiTBLAccessor.update(inTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0879E);
        }
    }

    private static void updateCrRebilMtrBllg(S21ApiMessageMap msgMap, Map<String, Object> svcContrMtrInfo) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();

        SVC_CR_REBIL_MTR_BLLGTMsg inTMsg = new SVC_CR_REBIL_MTR_BLLGTMsg();
        setValue(inTMsg.glblCmpyCd, glblCmpyCd);
        setValue(inTMsg.svcCrRebilMtrBllgPk, (BigDecimal) svcContrMtrInfo.get("SVC_CR_REBIL_MTR_BLLG_PK"));
        inTMsg = (SVC_CR_REBIL_MTR_BLLGTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inTMsg);
        if (inTMsg == null) {
            msgMap.addXxMsgId(NSZM0893E);
            return;
        }

        setValue(inTMsg.newMtrCnt, (BigDecimal) svcContrMtrInfo.get("BLLG_COPY_QTY"));
        setValue(inTMsg.newMtrTestCnt, (BigDecimal) svcContrMtrInfo.get("TEST_COPY_QTY"));
        setValue(inTMsg.newMtrChrgDealAmt, (BigDecimal) svcContrMtrInfo.get("MTR_CHRG_DEAL_AMT"));
        // START 05/09/2016 T.Aoyagi [QC#7618, ADD]
        BigDecimal newMtrChrgDealAmt = (BigDecimal) svcContrMtrInfo.get("MTR_CHRG_DEAL_AMT");
        BigDecimal oldMtrChrgDealAmt = inTMsg.oldMtrChrgDealAmt.getValue();
        if (hasValue(oldMtrChrgDealAmt) && hasValue(newMtrChrgDealAmt)) {
            setValue(inTMsg.invMtrChrgDealNetAmt, (newMtrChrgDealAmt.subtract(oldMtrChrgDealAmt)).abs());
        }
        // END 05/09/2016 T.Aoyagi [QC#7618, ADD]
        S21ApiTBLAccessor.update(inTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0880E);
        }
    }

    private static void updateCrRebilXsMtrBllg(S21ApiMessageMap msgMap, Map<String, Object> svcContrMtrInfo) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();

        SVC_CR_REBIL_XS_MTR_BLLGTMsg inTMsg = new SVC_CR_REBIL_XS_MTR_BLLGTMsg();
        setValue(inTMsg.glblCmpyCd, glblCmpyCd);
        // START 04/08/2016 T.Aoyagi [QC#6762, MOD]
//        setValue(inTMsg.svcCrRebilMtrBllgPk, (BigDecimal) svcContrMtrInfo.get("SVC_CR_REBIL_XS_MTR_BLLG_PK"));
        setValue(inTMsg.svcCrRebilXsMtrBllgPk, (BigDecimal) svcContrMtrInfo.get("SVC_CR_REBIL_XS_MTR_BLLG_PK"));
        // END 04/08/2016 T.Aoyagi [QC#6762, MOD]
        inTMsg = (SVC_CR_REBIL_XS_MTR_BLLGTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inTMsg);
        if (inTMsg == null) {
            msgMap.addXxMsgId(NSZM0895E);
            return;
        }

        setValue(inTMsg.newXsMtrChrgDealAmt, (BigDecimal) svcContrMtrInfo.get("XS_MTR_CHRG_DEAL_AMT"));

        S21ApiTBLAccessor.update(inTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0882E);
        }
    }

    // START 2018/03/27 M.Naito [QC#18672, ADD]
    public static void updateCrRebilAddlBllg(S21ApiMessageMap msgMap, BigDecimal prntSvcInvLinePk, BigDecimal newBaseDealAmt, Map<String, Object> svcContrAddlChrgInfo) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        BigDecimal newAddlChrgDealAmt = null;

        SVC_CR_REBIL_ADDL_BLLGTMsg inTMsg = new SVC_CR_REBIL_ADDL_BLLGTMsg();
        setValue(inTMsg.glblCmpyCd, glblCmpyCd);
        setValue(inTMsg.svcCrRebilAddlBllgPk, (BigDecimal) svcContrAddlChrgInfo.get("SVC_CR_REBIL_ADDL_BLLG_PK"));
        inTMsg = (SVC_CR_REBIL_ADDL_BLLGTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inTMsg);
        if (inTMsg == null) {
            msgMap.addXxMsgId(NSZM1326E);
            return;
        }

        if (hasValue((BigDecimal) svcContrAddlChrgInfo.get("DS_CONTR_ADDL_CHRG_PK"))) {
            Map<String, Object> bllgSchdMap = query.getBllgSchdInfoForCalcAddlChrg(glblCmpyCd, prntSvcInvLinePk);
            AddlChrgFromThruDtInfo addlChrgFromThruDtInfo = getAddlChrgFromThruDtInfo(glblCmpyCd, bllgSchdMap, svcContrAddlChrgInfo);
            if (addlChrgFromThruDtInfo == null) {
                // DS_CONTR_ADDL_CHRG is out of term
                newAddlChrgDealAmt = BigDecimal.ZERO;
            } else {
                newAddlChrgDealAmt = getNewAddlChrgDealAmt(glblCmpyCd, svcContrAddlChrgInfo, bllgSchdMap, addlChrgFromThruDtInfo, newBaseDealAmt);
            }
        } else {
            // DS_CONTR_ADDL_CHRG is not exist
            newAddlChrgDealAmt = BigDecimal.ZERO;
        }
        setValue(inTMsg.newAddlChrgDealAmt, newAddlChrgDealAmt);

        S21ApiTBLAccessor.update(inTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM1325E);
        }
    }
    // END 2018/03/27 M.Naito [QC#18672, ADD]

    /**
     * @param msgMap S21ApiMessageMap
     * @param crRebilTMsg SVC_CR_REBILTMsg
     */
    public static void cancelCrRebillInfo(S21ApiMessageMap msgMap, SVC_CR_REBILTMsg crRebilTMsg) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();

        if (crRebilTMsg == null) {
            msgMap.addXxMsgId(NSZM0890E);
            return;
        }

        String crRebilSts = crRebilTMsg.svcCrRebilStsCd.getValue();
        if (!SVC_CR_REBIL_STS.ENTERED.equals(crRebilSts) && !SVC_CR_REBIL_STS.PENDING_APPROVAL.equals(crRebilSts)) {
            msgMap.addXxMsgId(NSZM0866E);
            return;
        }

        setValue(crRebilTMsg.svcCrRebilStsCd, SVC_CR_REBIL_STS.CANCELLED);
        setValue(crRebilTMsg.svcCrRebilRsnTxt, pMsg.svcCrRebilRsnTxt);
        S21ApiTBLAccessor.update(crRebilTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(crRebilTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0877E);
        }
    }

    // mod start 2016/06/06 CSA Defect#1523, 4624
    /**
     * @param msgMap S21ApiMessageMap
     * @param crRebilTMsg SVC_CR_REBILTMsg
     * @param onbatTp ONBATCH_TYPE
     */
    public static void billingHld(S21ApiMessageMap msgMap, SVC_CR_REBILTMsg crRebilTMsg, ONBATCH_TYPE onbatTp) {
        updateBllgHld(msgMap, crRebilTMsg, true, onbatTp);
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param crRebilTMsg SVC_CR_REBILTMsg
     * @param onbatTp ONBATCH_TYPE
     */
    public static void releaseBllgHld(S21ApiMessageMap msgMap, SVC_CR_REBILTMsg crRebilTMsg, ONBATCH_TYPE onbatTp) {
        updateBllgHld(msgMap, crRebilTMsg, false, onbatTp);
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param crRebilTMsg SVC_CR_REBILTMsg
     * @param onbatTp ONBATCH_TYPE
     */
    private static void updateBllgHld(S21ApiMessageMap msgMap, SVC_CR_REBILTMsg crRebilTMsg, boolean bllgHld, ONBATCH_TYPE onbatTp) {

        String oldHldFlg = FLG_ON_Y;
        String newHldFlg = FLG_OFF_N;
        if (bllgHld) {
            oldHldFlg = FLG_OFF_N;
            newHldFlg = FLG_ON_Y;
        }

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        List<BigDecimal> dsContrDtlPkList = query.getDsContrDtlBySvcCrRebilPk(
                glblCmpyCd, crRebilTMsg.svcCrRebilPk.getValue());

        List<DS_CONTR_DTLTMsg> updDsContrDtlList = new ArrayList<DS_CONTR_DTLTMsg>(dsContrDtlPkList.size());
        List<DS_CONTR_PRC_EFFTMsg> updPEList = new ArrayList<DS_CONTR_PRC_EFFTMsg>();
        List<DS_CONTR_BLLG_MTRTMsg> updBllgMtrList = new ArrayList<DS_CONTR_BLLG_MTRTMsg>();
        // add start 2016/06/06 CSA Defect#1523, 4624
        List<BigDecimal> dsContrPkList = new ArrayList<BigDecimal>();
        // add end 2016/06/06 CSA Defect#1523, 4624

        for (BigDecimal dsContrDtlPk : dsContrDtlPkList) {
            if (!hasValue(dsContrDtlPk)) {
                continue;
            }
            // START 2018/06/25 K.Kitachi [QC#22245, ADD]
            if (!bllgHld) {
                if (existUnapprovedUsgChrgForDtl(glblCmpyCd, dsContrDtlPk)) {
                    continue;
                }
                if (existUnapprovedCrRebilForBase(glblCmpyCd, crRebilTMsg.svcCrRebilPk.getValue(), dsContrDtlPk)) {
                    continue;
                }
                if (existUnapprovedCrRebilForMtr(glblCmpyCd, crRebilTMsg.svcCrRebilPk.getValue(), dsContrDtlPk)) {
                    continue;
                }
            }
            // END 2018/06/25 K.Kitachi [QC#22245, ADD]
            DS_CONTR_DTLTMsg dsContrDtlTMsg = query.getDsContrDtlTMsgForUpdate(glblCmpyCd, dsContrDtlPk);
            // START 04/06/2016 T.Aoyagi [QC#5963, MOD]
            // START 2019/07/18 [QC#51706, MOD]
            if (dsContrDtlTMsg != null) {
                if (bllgHld) {
                    if (oldHldFlg.equals(dsContrDtlTMsg.bllgHldFlg.getValue())) {
                        setValue(dsContrDtlTMsg.bllgHldFlg, newHldFlg);
                        updDsContrDtlList.add(dsContrDtlTMsg);
                    }
                } else {
                    if (!query.hasManualHld(glblCmpyCd, dsContrDtlTMsg.dsContrPk.getValue(), dsContrDtlPk, null)) {
                        if (oldHldFlg.equals(dsContrDtlTMsg.bllgHldFlg.getValue())) {
                            setValue(dsContrDtlTMsg.bllgHldFlg, newHldFlg);
                            updDsContrDtlList.add(dsContrDtlTMsg);
                        }
                    }
                }
            }
            // END 2019/07/18 [QC#51706, MOD]
            // END 04/06/2016 T.Aoyagi [QC#5963, MOD]
            // add start 2016/06/06 CSA Defect#1523, 4624
            if (!dsContrPkList.contains(dsContrDtlTMsg.dsContrPk.getValue())) {
                dsContrPkList.add(dsContrDtlTMsg.dsContrPk.getValue());
            }
            // add end 2016/06/06 CSA Defect#1523, 4624

            DS_CONTR_PRC_EFFTMsgArray peTMsgAry = query.getDsContrPrcEffTMsg(glblCmpyCd, dsContrDtlPk);
            for (int i = 0; i < peTMsgAry.getValidCount(); i++) {
                DS_CONTR_PRC_EFFTMsg peTMsg = query.getDsContrPrcEffTMsgForUpdate(glblCmpyCd, peTMsgAry.no(i).dsContrPrcEffPk.getValue());
                // START 04/06/2016 T.Aoyagi [QC#5963, MOD]
                if (peTMsg != null) {
                    if (oldHldFlg.equals(peTMsg.bllgHldFlg.getValue())) {
                        setValue(peTMsg.bllgHldFlg, newHldFlg);
                        updPEList.add(peTMsg);
                    }
                }
                // END 04/06/2016 T.Aoyagi [QC#5963, MOD]
            }

            DS_CONTR_BLLG_MTRTMsgArray bllgMtrTMsgAry = query.getDsContrBllgMtrTMsg(glblCmpyCd, dsContrDtlPk);
            for (int i = 0; i < bllgMtrTMsgAry.getValidCount(); i++) {
                DS_CONTR_BLLG_MTRTMsg bllgMtrTMsg = query.getDsContrBllgMtrTMsgForUpdate(glblCmpyCd, bllgMtrTMsgAry.no(i).dsContrBllgMtrPk.getValue());
                // START 04/06/2016 T.Aoyagi [QC#5963, MOD]
                if (bllgMtrTMsg != null) {
                    // START 2019/07/18 [QC#51706, MOD]
                    if (bllgHld) {
                        if (oldHldFlg.equals(bllgMtrTMsg.bllgHldFlg.getValue())) {
                            setValue(bllgMtrTMsg.bllgHldFlg, newHldFlg);
                            updBllgMtrList.add(bllgMtrTMsg);
                        }
                    } else {
                        if (!query.hasManualHld(glblCmpyCd, dsContrDtlTMsg.dsContrPk.getValue(), dsContrDtlPk, bllgMtrTMsg.dsContrBllgMtrPk.getValue())) {
                            if (oldHldFlg.equals(bllgMtrTMsg.bllgHldFlg.getValue())) {
                                setValue(bllgMtrTMsg.bllgHldFlg, newHldFlg);
                                updBllgMtrList.add(bllgMtrTMsg);
                            }
                        }
                    }
                    // END 2019/07/18 [QC#51706, MOD]
                }
                // END 04/06/2016 T.Aoyagi [QC#5963, MOD]
            }
        }

        if (!updPEList.isEmpty()) {
            // ----------------------------------------
            // Update DS_CONTR_PRC_EFF
            // ----------------------------------------
            DS_CONTR_PRC_EFFTMsg[] inMsgPeArray = new DS_CONTR_PRC_EFFTMsg[updPEList.size()];
            int updPeCnt = S21FastTBLAccessor.update(updPEList.toArray(inMsgPeArray));

            if (updPEList.size() != updPeCnt) {
                msgMap.addXxMsgId(NSZM0876E);
                return;
            }
        }

        if (!updBllgMtrList.isEmpty()) {
            // ----------------------------------------
            // Update DS_CONTR_BLLG_MTR
            // ----------------------------------------
            DS_CONTR_BLLG_MTRTMsg[] inMsgBllgMtrArray = new DS_CONTR_BLLG_MTRTMsg[updBllgMtrList.size()];
            int updBllgMtrCnt = S21FastTBLAccessor.update(updBllgMtrList.toArray(inMsgBllgMtrArray));

            if (updBllgMtrList.size() != updBllgMtrCnt) {
                msgMap.addXxMsgId(NSZM0874E);
                return;
            }
        }

        if (!updDsContrDtlList.isEmpty()) {
            // ----------------------------------------
            // Update DS_CONTR_DTL
            // ----------------------------------------
            DS_CONTR_DTLTMsg[] inMsgArray;
            inMsgArray = new DS_CONTR_DTLTMsg[updDsContrDtlList.size()];
            int updCnt = S21FastTBLAccessor.update(updDsContrDtlList.toArray(inMsgArray));

            if (updDsContrDtlList.size() != updCnt) {
                msgMap.addXxMsgId(NSZM0875E);
                return;
            }

            // ----------------------------------------
            // Update DS_CONTR
            // ----------------------------------------
            BigDecimal dsContrPk = updDsContrDtlList.get(0).dsContrPk.getValue();
            // START 2018/06/25 K.Kitachi [QC#22245, ADD]
            if (!bllgHld) {
                if (existUnapprovedUsgChrg(glblCmpyCd, dsContrPk)) {
                    return;
                }
                if (existUnapprovedCrRebil(glblCmpyCd, crRebilTMsg.svcCrRebilPk.getValue(), dsContrPk)) {
                    return;
                }
            }
            // END 2018/06/25 K.Kitachi [QC#22245, ADD]
            DS_CONTRTMsg dsContrTMsg = query.getDsContrTMsgForUpdate(glblCmpyCd, dsContrPk);
            // START 04/06/2016 T.Aoyagi [QC#5963, MOD]
            if (dsContrTMsg != null) {
                // START 2019/07/18 [QC#51706, MOD]
                if (bllgHld) {
                    if (oldHldFlg.equals(dsContrTMsg.bllgHldFlg.getValue())) {
                        setValue(dsContrTMsg.bllgHldFlg, newHldFlg);
                        S21ApiTBLAccessor.update(dsContrTMsg);

                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsContrTMsg.getReturnCode())) {
                            msgMap.addXxMsgId(NSZM0873E);
                        }
                    }
                } else {
                    if (!query.hasManualHld(glblCmpyCd, dsContrPk, null, null)) {
                        if (oldHldFlg.equals(dsContrTMsg.bllgHldFlg.getValue())) {
                            setValue(dsContrTMsg.bllgHldFlg, newHldFlg);
                            S21ApiTBLAccessor.update(dsContrTMsg);

                            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsContrTMsg.getReturnCode())) {
                                msgMap.addXxMsgId(NSZM0873E);
                            }
                        }
                    }
                }
                // END 2019/07/18 [QC#51706, MOD]
            }
            // END 04/06/2016 T.Aoyagi [QC#5963, MOD]

            // START 12/14/2016 [QC#16285, ADD]
            // ----------------------------------------
            // Insert SVC_MEMO
            // ----------------------------------------
            if (bllgHld) {
                // START 02/22/2017 [QC#16285-1, MOD]
                // if (!createSvcMemo(msgMap, dsContrTMsg)) {
                if (!createSvcMemo(msgMap, dsContrTMsg, crRebilTMsg)) {
                // END   02/22/2017 [QC#16285-1, MOD]
                    return;
                }
            }
            // END   12/14/2016 [QC#16285, ADD]
        }
        // add start 2016/06/06 CSA Defect#
        for (BigDecimal dsContrPk : dsContrPkList) {
            if (!callContrTrkAPI(msgMap, glblCmpyCd, dsContrPk, pMsg.slsDt.getValue(), onbatTp)) {
                return;
            }
        }
        // add end 2016/06/06 CSA Defect#
    }
    // mod end 2016/06/06 CSA Defect#1523, 4624

    // START 12/14/2016 [QC#16285, ADD]
    // START 02/22/2017 [QC#16285-1, MOD]
    // private static boolean createSvcMemo(S21ApiMessageMap msgMap, DS_CONTRTMsg dsContrTMsg) {
    private static boolean createSvcMemo(S21ApiMessageMap msgMap, DS_CONTRTMsg dsContrTMsg, SVC_CR_REBILTMsg crRebilTMsg) {
    // END   02/22/2017 [QC#16285-1, MOD]
        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();

        String dsContrNum = dsContrTMsg.dsContrNum.getValue();
        BigDecimal dsContrPk = dsContrTMsg.dsContrPk.getValue();
    	// START 02/22/2017 [QC#16285-1, MOD]
        String custIncdtId = crRebilTMsg.custIncdtId.getValue();
        // String svcCmntTxt = String.format(SVC_MEMO_BLLG_RSN_CMNT_TXT_HDR, dsContrNum);
        String svcCmntTxt = String.format(SVC_MEMO_BLLG_RSN_CMNT_TXT_HDR, custIncdtId, dsContrNum);
    	// END   02/22/2017 [QC#16285-1, MOD]

        SVC_MEMOTMsg insertTMsg = new SVC_MEMOTMsg();
        setValue(insertTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(insertTMsg.svcMemoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MEMO_SQ));
        setValue(insertTMsg.svcMemoCatgCd, SVC_MEMO_CATG.CONTRACT_MEMO);
        setValue(insertTMsg.svcMemoTpCd, SVC_MEMO_TP.BILLING_HOLD_REASON);
        setValue(insertTMsg.svcCmntTxt, svcCmntTxt);
        setValue(insertTMsg.dsContrPk, dsContrPk);
        setValue(insertTMsg.lastUpdUsrId, S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        setValue(insertTMsg.lastUpdTs, ZYPDateUtil.getCurrentSystemTime(DT_FORMAT_TS));
        setValue(insertTMsg.svcMemoRsnCd, SVC_MEMO_RSN.BILLING_HOLD_REASON);

        S21ApiTBLAccessor.insert(insertTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insertTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0475E);
            return false;
        }

        return true;
    }
    // END   12/14/2016 [QC#16285, ADD]

    // START 04/11/2016 T.Aoyagi [QC#6715, ADD]
    private static String nullToSpace(String val) {
        if (!hasValue(val)) {
            return "";
        }
        return val;
    }
    // END 04/11/2016 T.Aoyagi [QC#6715, ADD]

    // add start 2016/04/13 CSA Defect#6512
    /**
     * @param glblCmpyCd String
     * @return S21MailAddress
     */
    public static S21MailAddress getFromMailAddress(String glblCmpyCd) {
        S21MailGroup fromGrp = new S21MailGroup(glblCmpyCd, FROM_ADDR_GRP_CD);
        fromGrp.setMailKey1("NSZ");
        List<S21MailAddress> fromAddrList = fromGrp.getMailAddress();
        if (fromAddrList == null || fromAddrList.isEmpty()) {
            return null;
        }
        return fromAddrList.get(0);
    }

    /**
     * @param glblCmpyCd String
     * @param psnCd String
     * @return String
     */
    public static String getEmailAddress(String glblCmpyCd, String psnCd) {
        AUTH_PSNTMsg inMsg = new AUTH_PSNTMsg();
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("usrNm01", psnCd);
        inMsg.setSQLID("053");
        AUTH_PSNTMsgArray authPsnArray = (AUTH_PSNTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        if (authPsnArray == null || authPsnArray.length() == 0) {
            return null;
        }
        return authPsnArray.no(0).emlAddr.getValue();
    }

    /**
     * @param glblCmpyCd String
     * @param mailTmplId String
     * @return S21MailTemplate
     */
    public static S21MailTemplate getMailTemplate(String glblCmpyCd, String mailTmplId) {
        return new S21MailTemplate(glblCmpyCd, mailTmplId);
    }

    /**
     * @param inMsg SVC_CR_REBILTMsg
     * @return String
     */
    public static String formatTimeStamp(SVC_CR_REBILTMsg inMsg) {
        String ezInTime = inMsg.ezInTime.getValue();
        if (!hasValue(ezInTime)) {
            return null;
        }

        Date srcDate = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        try {
            srcDate = dateFormat.parse(ezInTime);
            dateFormat.applyPattern("MM/dd/yyyy");
            return dateFormat.format(srcDate);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * @param inMsg SVC_CR_REBILTMsg
     * @return String
     */
    public static String getComment(SVC_CR_REBILTMsg inMsg) {
        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        S21NwfContext context = null;

        try {
            context = factory.getContex();
            List<S21NwfProcess> procs = context.getProcessForBiz(WF_PROCESS_NM, inMsg.svcCrRebilPk.getValue().toPlainString());
            for (S21NwfProcess proc : procs) {
                S21NwfUtilBizProcess p = (S21NwfUtilBizProcess) proc;
                List<S21NwfUtilBizHistWorkItem> history = p.getHistory();
                for (S21NwfUtilBizHistWorkItem wi : history) {
                    String comment = wi.getComment();
                    if (hasValue(comment)) {
                        return comment;
                    }
                }
            }
        } catch (S21NwfSystemException e) {
            return null;
        }

        return null;
    }
    // add end 2016/04/13 CSA Defect#6512
    // add start 2016/04/27 CSA Defect#7056
    /**
     * @param msgMap S21ApiMessageMap
     * @param svcCrRebilDtlPk BigDecimal
     */
    public static void syncAggLineXsRateToAggMach(S21ApiMessageMap msgMap, BigDecimal svcCrRebilDtlPk) {

        List<Map<String, Object>> aggLineXsRateList = query.getAggLineXsRate(msgMap, svcCrRebilDtlPk);
        if (aggLineXsRateList == null || aggLineXsRateList.isEmpty()) {
            return;
        }
        for (Map<String, Object> aggLineXsRate : aggLineXsRateList) {
            String bllgMtrLbCd = (String) aggLineXsRate.get("BLLG_MTR_LB_CD");
            // OLD_XS_COPY_QTY of aggregate line means excess tier.
            BigDecimal tierCnt = (BigDecimal) aggLineXsRate.get("OLD_XS_COPY_QTY");
            BigDecimal newXsMtrAmtRate = (BigDecimal) aggLineXsRate.get("NEW_XS_MTR_AMT_RATE");

            List<BigDecimal> diffAggMachXsRateList = query.getAggMachDiffRate(msgMap, svcCrRebilDtlPk, bllgMtrLbCd, tierCnt, newXsMtrAmtRate);
            if (diffAggMachXsRateList == null || diffAggMachXsRateList.isEmpty()) {
                continue;
            }
            for (BigDecimal svcCrRebilXsMtrBllgPk : diffAggMachXsRateList) {
                updateSvcCrRebilXsMtr(msgMap, svcCrRebilXsMtrBllgPk, newXsMtrAmtRate);
            }
        }
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param svcCrRebilXsMtrBllgPk BigDecimal
     * @param newXsMtrAmtRate BigDecimal
     * @return boolean
     */
    public static boolean updateSvcCrRebilXsMtr(S21ApiMessageMap msgMap, BigDecimal svcCrRebilXsMtrBllgPk, BigDecimal newXsMtrAmtRate) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        SVC_CR_REBIL_XS_MTR_BLLGTMsg inMsg = query.getSvcCrRebilXsMtrBllgTMsgForUpdate(pMsg.glblCmpyCd.getValue(), svcCrRebilXsMtrBllgPk);
        if (inMsg == null) {
            msgMap.addXxMsgId(NSZM0895E);
            return false;
        }
        setValue(inMsg.newXsMtrAmtRate, newXsMtrAmtRate);
        S21ApiTBLAccessor.update(inMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0882E);
            return false;
        }
        return true;
    }
    // add end 2016/04/27 CSA Defect#7056

    // add start 2016/06/06 CSA Defect#1523, 4624
    private static boolean callContrTrkAPI(S21ApiMessageMap msgMap,String glblCmpyCd, BigDecimal dsContrPk, String slsDt, ONBATCH_TYPE onbatTp) {
        S21UserProfileServiceFactory profileService = S21UserProfileServiceFactory.getInstance();
        S21UserProfileService profile;
        S21UserInfo userInfo = null;
        if (profileService != null) {
            profile = profileService.getService();
            if (profile != null) {
                userInfo = profile.getContextUserInfo();
            }
        }
        if (userInfo == null) {
            msgMap.addXxMsgId(NSXC001001ContractTracking.ERR_MSG_ID);
            return false;
        }

        if (!NSXC001001ContractTracking.callContrTrk(glblCmpyCd, ContrTrkProcMode.BILLING_HOLD.code, dsContrPk, userInfo.getUserId(), slsDt, null, null, onbatTp)) {
            msgMap.addXxMsgId(NSXC001001ContractTracking.ERR_MSG_ID);
            return false;
        }
        return true;
    }
    // add end 2016/06/06 CSA Defect#1523, 4624

    // Add Start 09/12/2016 <QC#5566>
    // mod start 01/24/2017 CSA Defect#17261
    public static void callCustomerCareApi(S21ApiMessageMap msgMap, String custIncdtId, String custIncdtLineId, String status, String notesDetail, String updatedBy) {
        CallableStatement callStmt = null;
        OracleConnection oraConnection = null;

        try {
            oraConnection = (OracleConnection) EZDConnectionMgr.getInstance().getConnection().getMetaData().getConnection();
            callStmt = oraConnection.prepareCall(CALL_PRC_CANON_E193_UPDATE_TICKET);

            // Bind Values to the IN parameter
            callStmt.setString(1, custIncdtId);
            callStmt.setString(2, custIncdtLineId);
            callStmt.setString(3, status);
            callStmt.setString(4, notesDetail);
            callStmt.setString(5, updatedBy);

            // Register OUT parameter
            callStmt.registerOutParameter(6, OracleTypes.NUMBER);
            callStmt.registerOutParameter(7, OracleTypes.NUMBER);
            callStmt.registerOutParameter(8, OracleTypes.VARCHAR);

            // Execute the CallableStatement
            callStmt.execute();

            // Retrieve the value from the OUT parameter
            if (hasError(callStmt)) {
                String message = callStmt.getString(8);
                //set error message
                msgMap.addXxMsgIdWithPrm(NSZM1098E, new String[] {message});
            }

        } catch (SQLException e) {
            msgMap.addXxMsgId(NSZM1099E);
            e.printStackTrace();
        } catch (Exception ex){
            msgMap.addXxMsgId(NSZM1099E);
            ex.printStackTrace();
        } finally {
            // Mandatory, Close CallableStatement object
            if (callStmt != null) {
                try {
                    callStmt.close();
                } catch (SQLException e) {
                    msgMap.addXxMsgId(NSZM1099E);
                    e.printStackTrace();
                }
            }
        }
        return;
    }
    // mod end 01/24/2017 CSA Defect#17261

    private static boolean hasError(CallableStatement callStmt) throws SQLException {
        int updCntHC = callStmt.getInt(6);
        int updCntLC = callStmt.getInt(7);
        if (updCntHC < 0 || updCntLC < 0) {
            return true;
        }
        return false;
    }

    public static String getCurrTimestamp() {
        // START 2018/03/16 K.Kojima [QC#24497,MOD]
        // String currTimeStmap = ZYPDateUtil.getCurrentSystemTime(DATE_FORMAT);
        String currTimeStmap = ZYPDateUtil.getCurrentSystemTime(DATE_FORMAT_DDMMMYYYY_KKMMSS_A).toUpperCase();
        // END 2018/03/16 K.Kojima [QC#24497,MOD]
        return currTimeStmap;
    }
    // Add End   09/12/2016 <QC#5566>

    // START 2017/09/26 K.Kitachi [QC#21212, ADD]
    /**
     * createCrRebilInfo
     * @param msgMap S21ApiMessageMap
     * @param origSvcInvNum String
     * @param trgtSvcInvNum String
     * @param crRebilTMsg SVC_CR_REBILTMsg
     */
    public static void createCrRebilInfo(S21ApiMessageMap msgMap, String origSvcInvNum, String trgtSvcInvNum, SVC_CR_REBILTMsg crRebilTMsg) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();

        SVC_INVTMsg trgtSvcInvTMsg = query.getSvcInvTMsg(glblCmpyCd, trgtSvcInvNum);
        if (trgtSvcInvTMsg == null) {
            msgMap.addXxMsgId(NSZM0896E);
            return;
        }

        // ----------------------------------------
        // Create SVC_CR_REBIL_DTL
        // ----------------------------------------
        SVC_CR_REBIL_DTLTMsg crRebilDtlTMsg = createCrRebilDtl(msgMap, crRebilTMsg, trgtSvcInvTMsg);
        if (crRebilDtlTMsg == null) {
            return;
        }

        // ----------------------------------------
        // Create SVC_CR_REBIL_BASE_BLLG
        // ----------------------------------------
        List<Map<String, Object>> origSvcInvBaseList = query.getSvcInvLineForBase(glblCmpyCd, origSvcInvNum);
        List<Map<String, Object>> trgtSvcInvBaseList = query.getSvcInvLineForBase(glblCmpyCd, trgtSvcInvNum);

        createCrRebilBaseForAggLine(msgMap, crRebilDtlTMsg, trgtSvcInvBaseList);

        for (Map<String, Object> trgtSvcInvBaseMap : trgtSvcInvBaseList) {
            BigDecimal trgtDsContrDtlPk = (BigDecimal) trgtSvcInvBaseMap.get("DS_CONTR_DTL_PK");
            BigDecimal origDsContrDtlPk = null;
            BigDecimal newBaseDealAmt = null;
            for (Map<String, Object> origSvcInvBaseMap : origSvcInvBaseList) {
                origDsContrDtlPk = (BigDecimal) origSvcInvBaseMap.get("DS_CONTR_DTL_PK");
                if (trgtDsContrDtlPk.compareTo(origDsContrDtlPk) == 0) {
                    newBaseDealAmt = getNewBaseDealAmtFromPMsg(msgMap, origSvcInvBaseMap);
                    break;
                }
            }
            createCrRebilBaseBllg(msgMap, crRebilDtlTMsg, trgtSvcInvBaseMap, newBaseDealAmt);
            // START 2022/05/27 K.Kitachi [QC#60121, ADD]
            // ----------------------------------------
            // Create SVC_CR_REBIL_ADDL_BLLG
            // ----------------------------------------
            if (!hasValue(newBaseDealAmt)) {
                newBaseDealAmt = (BigDecimal) trgtSvcInvBaseMap.get("INV_LINE_DEAL_NET_AMT");
            }
            BigDecimal prntSvcInvLinePk = (BigDecimal) trgtSvcInvBaseMap.get("SVC_INV_LINE_PK");
            String svcInvNum =  trgtSvcInvNum;
            List<Map<String, Object>> svcInvAddlChrgList = query.getSvcInvLineForAddlChrg(glblCmpyCd, prntSvcInvLinePk, ADDL_CHRG_INV_TP.BASE);
            for (Map<String, Object> svcInvAddlChrgMap : svcInvAddlChrgList) {
                Map<String, Object> bllgSchdMap = query.getBllgSchdInfoForCalcAddlChrg(glblCmpyCd, prntSvcInvLinePk);
                AddlChrgFromThruDtInfo addlChrgFromThruDtInfo = getAddlChrgFromThruDtInfo(glblCmpyCd, bllgSchdMap, svcInvAddlChrgMap);
                if (addlChrgFromThruDtInfo == null) {
                    continue;
                }
                // get New additional charge amount
                BigDecimal newAddlChrgDealAmt = getNewAddlChrgDealAmt(glblCmpyCd, svcInvAddlChrgMap, bllgSchdMap, addlChrgFromThruDtInfo, newBaseDealAmt);
                createCrRebilAddlBllg(msgMap, crRebilDtlTMsg.svcCrRebilDtlPk.getValue(), svcInvAddlChrgMap, newAddlChrgDealAmt);
            }

            // Create SVC_CR_REBIL_DTL for Additional Charge (Invoice not exist)
            List<Map<String, Object>> addlChrgNotExistSvcInvLineList = query.getAddlChrgNotExistSvcInvLine(pMsg.glblCmpyCd.getValue(), prntSvcInvLinePk, ADDL_CHRG_INV_TP.BASE);
            boolean createAddlBllgFlg = true;
            BigDecimal newSvcCrRebilDtlPk = null;
            // Create SVC_CR_REBIL_ADDL_BLLG (Invoice not exist)
            for (Map<String, Object> addlChrgNotExistSvcInvLineMap : addlChrgNotExistSvcInvLineList) {
                Map<String, Object> bllgSchdMap = query.getBllgSchdInfoForCalcAddlChrg(glblCmpyCd, prntSvcInvLinePk);
                AddlChrgFromThruDtInfo addlChrgFromThruDtInfo = getAddlChrgFromThruDtInfo(glblCmpyCd, bllgSchdMap, addlChrgNotExistSvcInvLineMap);
                if (addlChrgFromThruDtInfo == null) {
                    continue;
                }
                if (createAddlBllgFlg) {
                    // Create SVC_CR_REBIL_DTL for Additional Charge (Invoice not exist)
                    newSvcCrRebilDtlPk = createCrRebilDtlForAddlChrg(msgMap, crRebilTMsg.svcCrRebilPk.getValue(), svcInvNum, addlChrgNotExistSvcInvLineMap, crRebilDtlTMsg.svcCrRebilDtlPk.getValue());
                }
                BigDecimal newAddlChrgDealAmt = getNewAddlChrgDealAmt(glblCmpyCd, addlChrgNotExistSvcInvLineMap, bllgSchdMap, addlChrgFromThruDtInfo, newBaseDealAmt);
                createCrRebilAddlBllg(msgMap, newSvcCrRebilDtlPk, addlChrgNotExistSvcInvLineMap, newAddlChrgDealAmt);
                createAddlBllgFlg = false;
            }
            // END 2022/05/27 K.Kitachi [QC#60121, ADD]
        }

        // ----------------------------------------
        // Create SVC_CR_REBIL_MTR_BLLG
        // ----------------------------------------
        List<Map<String, Object>> origSvcInvMtrList = query.getSvcInvLineForBllgMtr(glblCmpyCd, origSvcInvNum);
        List<Map<String, Object>> trgtSvcInvMtrList = query.getSvcInvLineForBllgMtr(glblCmpyCd, trgtSvcInvNum);

        // START 2017/10/20 K.Kitachi [QC#21927, MOD]
//        createCrRebilMtrForAggLine(msgMap, crRebilDtlTMsg, trgtSvcInvMtrList);
        // START 2017/11/08 K.Kitachi [QC#22301, MOD]
        // createCrRebilMtrForAggLine(msgMap, crRebilDtlTMsg, trgtSvcInvMtrList, origSvcInvNum);
        createCrRebilMtrForAggLine(msgMap, crRebilDtlTMsg, trgtSvcInvMtrList, origSvcInvNum, trgtSvcInvNum);
        // END 2017/11/08 K.Kitachi [QC#22301, MOD]
        // END 2017/10/20 K.Kitachi [QC#21927, MOD]

        for (Map<String, Object> trgtSvcInvMtrMap : trgtSvcInvMtrList) {
            BigDecimal trgtDsContrBllgMtrPk = (BigDecimal) trgtSvcInvMtrMap.get("DS_CONTR_BLLG_MTR_PK");
            String trgtSerNum = (String) trgtSvcInvMtrMap.get("SER_NUM");
            String trgtBllgMtrLbCd = (String) trgtSvcInvMtrMap.get("MTR_LB_CD");
            String trgtBllgPerFromDt = (String) trgtSvcInvMtrMap.get("BLLG_PER_FROM_DT");
            BigDecimal origDsContrBllgMtrPk = null;
            // START 2018/06/29 K.Kitachi [QC#22057, ADD]
            BigDecimal origDsContrDtlPk = null;
            // END 2018/06/29 K.Kitachi [QC#22057, ADD]
            String origSerNum = null;
            String origBllgMtrLbCd = null;
            String origBllgPerFromDt = null;
            for (Map<String, Object> origSvcInvMtrMap : origSvcInvMtrList) {
                origDsContrBllgMtrPk = (BigDecimal) origSvcInvMtrMap.get("DS_CONTR_BLLG_MTR_PK");
                if (trgtDsContrBllgMtrPk.compareTo(origDsContrBllgMtrPk) == 0) {
                    // START 2018/06/29 K.Kitachi [QC#22057, ADD]
                    origDsContrDtlPk = nullToZero((BigDecimal) origSvcInvMtrMap.get("DS_CONTR_DTL_PK"));
                    // END 2018/06/29 K.Kitachi [QC#22057, ADD]
                    origSerNum = (String) origSvcInvMtrMap.get("SER_NUM");
                    origBllgMtrLbCd = (String) origSvcInvMtrMap.get("MTR_LB_CD");
                    origBllgPerFromDt = (String) origSvcInvMtrMap.get("BLLG_PER_FROM_DT");
                    break;
                }
            }

            SVC_CR_REBIL_MTR_BLLGTMsg crRebilMtrTMsg = createCrRebilMtrBllg(msgMap, crRebilDtlTMsg, trgtSvcInvMtrMap);

            // ----------------------------------------
            // Create SVC_CR_REBIL_XS_MTR_BLLG
            // ----------------------------------------
            List<Map<String, Object>> origSvcInvXsMtrList = query.getSvcInvLineForXsMtr(glblCmpyCd, origSvcInvNum, origSerNum, origBllgMtrLbCd, origBllgPerFromDt);
            List<Map<String, Object>> trgtSvcInvXsMtrList = query.getSvcInvLineForXsMtr(glblCmpyCd, trgtSvcInvNum, trgtSerNum, trgtBllgMtrLbCd, trgtBllgPerFromDt);
            // START 2017/10/23 K.Kitachi [QC#21966, MOD]
            for (Map<String, Object> trgtSvcInvXsMtrMap : trgtSvcInvXsMtrList) {
                // mod start 2020/06/23 QC#56945
                //BigDecimal trgtContrXsCopyPk = (BigDecimal) trgtSvcInvXsMtrMap.get("CONTR_XS_COPY_PK");
                //BigDecimal origContrXsCopyPk = null;
                BigDecimal trgtTier = (BigDecimal) trgtSvcInvXsMtrMap.get("TIER");
                BigDecimal origTier = null;
                BigDecimal oldXsCopyQty = null;
                NSZC053002_xxPriceChangesListPMsg xsMtrPMsg = new NSZC053002_xxPriceChangesListPMsg();
                for (Map<String, Object> origSvcInvXsMtrMap : origSvcInvXsMtrList) {
                    origTier = (BigDecimal) origSvcInvXsMtrMap.get("TIER");
                    oldXsCopyQty = (BigDecimal) origSvcInvXsMtrMap.get("XS_MTR_COPY_QTY");
                    if (trgtTier.compareTo(origTier) == 0) {
                // mod end 2020/06/23 QC#56945
                        // START 2018/06/29 K.Kitachi [QC#22057, MOD]
//                        xsMtrPMsg = getXsInfoFromPMsg(msgMap, origSvcInvNum, origSerNum, origBllgMtrLbCd, oldXsCopyQty);
                        // START 2023/01/16 R.Jin [QC#58890, MOD]
//                        xsMtrPMsg = getXsInfoFromPMsg(msgMap, origSvcInvNum, origDsContrDtlPk, origSerNum, origBllgMtrLbCd, oldXsCopyQty);
                        xsMtrPMsg = getXsInfoFromPMsg(msgMap, origSvcInvNum, origDsContrDtlPk, origSerNum, origBllgMtrLbCd, oldXsCopyQty, 0);
                        // END 2023/01/16 R.Jin [QC#58890, MOD]
                        // END 2018/06/29 K.Kitachi [QC#22057, MOD]
                        break;
                    }
                }
                createCrRebilXsMtrBllg(msgMap, crRebilMtrTMsg, trgtSvcInvXsMtrMap, xsMtrPMsg);
                // START 2022/05/27 K.Kitachi [QC#60121, ADD]
                // START 2022/06/14 K.Kitachi [QC#60160, DEL]
//                // ----------------------------------------
//                // Create SVC_CR_REBIL_ADDL_BLLG
//                // ----------------------------------------
//                BigDecimal prntSvcInvLinePk = (BigDecimal) trgtSvcInvMtrMap.get("SVC_INV_LINE_PK");
//                String svcInvNum =  trgtSvcInvNum;
//                List<Map<String, Object>> svcInvAddlChrgList = query.getSvcInvLineForAddlChrg(glblCmpyCd, prntSvcInvLinePk, ADDL_CHRG_INV_TP.USAGE);
//                for (Map<String, Object> svcInvAddlChrgMap : svcInvAddlChrgList) {
//                    Map<String, Object> bllgSchdMap = query.getBllgSchdInfoForCalcAddlChrg(glblCmpyCd, prntSvcInvLinePk);
//                    AddlChrgFromThruDtInfo addlChrgFromThruDtInfo = getAddlChrgFromThruDtInfo(glblCmpyCd, bllgSchdMap, svcInvAddlChrgMap);
//                    if (addlChrgFromThruDtInfo == null) {
//                        continue;
//                    }
//                    createCrRebilAddlBllg(msgMap, crRebilMtrTMsg.svcCrRebilDtlPk.getValue(), svcInvAddlChrgMap, null);
//                }
//
//                // Create SVC_CR_REBIL_ADDL_BLLG (Invoice not exist)
//                List<Map<String, Object>> addlChrgNotExistSvcInvLineList = query.getAddlChrgNotExistSvcInvLine(pMsg.glblCmpyCd.getValue(), prntSvcInvLinePk, ADDL_CHRG_INV_TP.USAGE);
//                boolean createAddlBllgFlg = true;
//                BigDecimal newSvcCrRebilDtlPk = null;
//                for (Map<String, Object> addlChrgNotExistSvcInvLineMap : addlChrgNotExistSvcInvLineList) {
//                    Map<String, Object> bllgSchdMap = query.getBllgSchdInfoForCalcAddlChrg(glblCmpyCd, prntSvcInvLinePk);
//                    AddlChrgFromThruDtInfo addlChrgFromThruDtInfo = getAddlChrgFromThruDtInfo(glblCmpyCd, bllgSchdMap, addlChrgNotExistSvcInvLineMap);
//                    if (addlChrgFromThruDtInfo == null) {
//                        continue;
//                    }
//                    if (createAddlBllgFlg) {
//                        // Create SVC_CR_REBIL_DTL for Additional Charge (Invoice not exist)
//                        newSvcCrRebilDtlPk =createCrRebilDtlForAddlChrg(msgMap, crRebilTMsg.svcCrRebilPk.getValue(), svcInvNum, addlChrgNotExistSvcInvLineMap, crRebilMtrTMsg.svcCrRebilDtlPk.getValue());
//                    }
//                    createCrRebilAddlBllg(msgMap, newSvcCrRebilDtlPk, addlChrgNotExistSvcInvLineMap, null);
//                    createAddlBllgFlg = false;
//                }
                // END 2022/06/14 K.Kitachi [QC#60160, DEL]
                // END 2022/05/27 K.Kitachi [QC#60121, ADD]
            }
            // END 2017/10/23 K.Kitachi [QC#21966, MOD]

            // START 2022/06/14 K.Kitachi [QC#60160, ADD]
            // ----------------------------------------
            // Create SVC_CR_REBIL_ADDL_BLLG
            // ----------------------------------------
            BigDecimal prntSvcInvLinePk = (BigDecimal) trgtSvcInvMtrMap.get("SVC_INV_LINE_PK");
            String svcInvNum =  trgtSvcInvNum;
            List<Map<String, Object>> svcInvAddlChrgList = query.getSvcInvLineForAddlChrg(glblCmpyCd, prntSvcInvLinePk, ADDL_CHRG_INV_TP.USAGE);
            for (Map<String, Object> svcInvAddlChrgMap : svcInvAddlChrgList) {
                Map<String, Object> bllgSchdMap = query.getBllgSchdInfoForCalcAddlChrg(glblCmpyCd, prntSvcInvLinePk);
                AddlChrgFromThruDtInfo addlChrgFromThruDtInfo = getAddlChrgFromThruDtInfo(glblCmpyCd, bllgSchdMap, svcInvAddlChrgMap);
                if (addlChrgFromThruDtInfo == null) {
                    continue;
                }
                createCrRebilAddlBllg(msgMap, crRebilMtrTMsg.svcCrRebilDtlPk.getValue(), svcInvAddlChrgMap, null);
            }

            // Create SVC_CR_REBIL_ADDL_BLLG (Invoice not exist)
            List<Map<String, Object>> addlChrgNotExistSvcInvLineList = query.getAddlChrgNotExistSvcInvLine(pMsg.glblCmpyCd.getValue(), prntSvcInvLinePk, ADDL_CHRG_INV_TP.USAGE);
            boolean createAddlBllgFlg = true;
            BigDecimal newSvcCrRebilDtlPk = null;
            for (Map<String, Object> addlChrgNotExistSvcInvLineMap : addlChrgNotExistSvcInvLineList) {
                Map<String, Object> bllgSchdMap = query.getBllgSchdInfoForCalcAddlChrg(glblCmpyCd, prntSvcInvLinePk);
                AddlChrgFromThruDtInfo addlChrgFromThruDtInfo = getAddlChrgFromThruDtInfo(glblCmpyCd, bllgSchdMap, addlChrgNotExistSvcInvLineMap);
                if (addlChrgFromThruDtInfo == null) {
                    continue;
                }
                if (createAddlBllgFlg) {
                    // Create SVC_CR_REBIL_DTL for Additional Charge (Invoice not exist)
                    newSvcCrRebilDtlPk =createCrRebilDtlForAddlChrg(msgMap, crRebilTMsg.svcCrRebilPk.getValue(), svcInvNum, addlChrgNotExistSvcInvLineMap, crRebilMtrTMsg.svcCrRebilDtlPk.getValue());
                }
                createCrRebilAddlBllg(msgMap, newSvcCrRebilDtlPk, addlChrgNotExistSvcInvLineMap, null);
                createAddlBllgFlg = false;
            }
            // END 2022/06/14 K.Kitachi [QC#60160, ADD]
        }

        syncAggLineXsRateToAggMach(msgMap, crRebilDtlTMsg.svcCrRebilDtlPk.getValue());

        // ----------------------------------------
        // Create SVC_CR_REBIL_MTR_READ
        // ----------------------------------------
        List<Map<String, Object>> trgtSvcInvMtrReadList = query.getSvcInvLineForMtrRead(glblCmpyCd, trgtSvcInvNum);
        for (Map<String, Object> trgtSvcInvMtrReadMap : trgtSvcInvMtrReadList) {
            createCrRebilMtrRead(msgMap, crRebilDtlTMsg, trgtSvcInvMtrReadMap, null);
        }
    }

    /**
     * callBllgSchdApi
     * @param msgMap S21ApiMessageMap
     * @param crRebilDtlTMsg SVC_CR_REBIL_DTLTMsg
     * @param onBatchTp ONBATCH_TYPE
     */
    public static void callBllgSchdApi(S21ApiMessageMap msgMap, SVC_CR_REBILTMsg crRebilTMsg, ONBATCH_TYPE onBatchTp) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String slsDt = pMsg.slsDt.getValue();
        BigDecimal svcCrRebilPk = crRebilTMsg.svcCrRebilPk.getValue();
        BigDecimal dsContrPk = query.getDsContrPk(glblCmpyCd, svcCrRebilPk);
        DS_CONTRTMsg dsContrTMsg = query.getDsContrTMsg(glblCmpyCd, dsContrPk);
        String dsContrCatgCd = dsContrTMsg.dsContrCatgCd.getValue();

        // START 2017/10/17 K.Kojima [QC#21795,ADD]
        boolean isBaseChrg = isBaseChrgCrReillTarget(pMsg);
        boolean isUsgChrg = isUsgChrgCrReillTarget(pMsg);
        // END 2017/10/17 K.Kojima [QC#21795,ADD]

        boolean isBaseCalc = false;

        // Mod Start 2017/10/19 QC#21815
        List<BigDecimal> dsContrDtlPkList = query.getDsContrDtlBySvcCrRebilPkForSchdApi(glblCmpyCd, svcCrRebilPk);
        // Mod End 2017/10/19 QC#21815
        for (BigDecimal dsContrDtlPk : dsContrDtlPkList) {
            if (!hasValue(dsContrDtlPk)) {
                continue;
            }
            DS_CONTR_DTLTMsg dsContrDtlTMsg = query.getDsContrDtlTMsg(glblCmpyCd, dsContrDtlPk);
            if (dsContrDtlTMsg == null) {
                continue;
            }
            String dsContrDtlTpCd = dsContrDtlTMsg.dsContrDtlTpCd.getValue();
            if (isUnderFleet(dsContrCatgCd, dsContrDtlTpCd)) {
                continue;
            }
            // START 2017/10/17 K.Kojima [QC#21795,MOD]
            // if (isBaseChrg(dsContrDtlTMsg)) {
            if (isBaseChrg(dsContrDtlTMsg) && isBaseChrg) {
            // END 2017/10/17 K.Kojima [QC#21795,MOD]
                // START 2022/10/23 T.Suzuki [QC#58427, ADD]
                Map<String, Object>currentPrcEffInfoBase = query.getCurrentPrcEffForBase(glblCmpyCd, slsDt, dsContrDtlPk);
                // END 2022/10/23 T.Suzuki [QC#58427, ADD]
                // Add Start 2017/10/19 QC#21815
                // START 2018/09/03 K.Kojima [QC#27960,MOD]
                // dividePrcEffForBase(dsContrDtlTMsg, crRebilTMsg, slsDt);
                String invEndPrcEffDt = dividePrcEffForBase(dsContrDtlTMsg, crRebilTMsg, slsDt);
                // END 2018/09/03 K.Kojima [QC#27960,MOD]
                // Add End 2017/10/19 QC#21815

                //  START 2021/04/27 T.Wada [QC#58177-3,MOD]
                boolean isBasePriceChg = true;
                if (!isChangedBaseUnitAmt(crRebilTMsg, dsContrDtlPk, dsContrTMsg)) {
                    isBasePriceChg = false;
                }
                //  END 2021/04/27 T.Wada [QC#58177-3,MOD]

                if (isBasePriceChg) { 
                    isBaseCalc = true;
                    // Mod Start 2017/10/19 QC#21815
                    Map<String, Object> prcEffInfo = query.getPrcEffForBase(glblCmpyCd, dsContrDtlPk, svcCrRebilPk);
                    if (prcEffInfo == null || prcEffInfo.isEmpty()) {
                        continue;
                    }
                    String nextStartDt = (String) prcEffInfo.get("NEXT_START_DT");
                    // START 2018/09/03 K.Kojima [QC#27960,ADD]
                    if (invEndPrcEffDt != null) {
                        nextStartDt = ZYPDateUtil.addDays(invEndPrcEffDt, 1);
                    }
                    // END 2018/09/03 K.Kojima [QC#27960,ADD]
                    String preSchdDt = ZYPDateUtil.addDays(nextStartDt, -1);
                    String nextEndDt = dsContrDtlTMsg.contrEffThruDt.getValue();

                    // START 2022/10/23 T.Suzuki [QC#58427, ADD]
                    String baseRnwlHoldSts = query.getBaseRnwlHold(glblCmpyCd, dsContrDtlTMsg.dsContrDtlPk.getValue(), dsContrDtlTMsg.contrEffThruDt.getValue());
                    String currentThruDt = "";
                    String pendingHold = ZYPConstant.FLG_OFF_N;
                    if (currentPrcEffInfoBase != null) {
                        currentThruDt = (String) currentPrcEffInfoBase.get("CONTR_PRC_EFF_THRU_DT");
                    }
                    if (hasValue(baseRnwlHoldSts) &&  ZYPDateUtil.compare(currentThruDt, dsContrDtlTMsg.contrEffThruDt.getValue()) < 0) {
                        //setValue(nszc047008PMsg.contrEffThruDt, currentThruDt);
                        pendingHold = ZYPConstant.FLG_ON_Y;
                        nextEndDt = currentThruDt;
                    }
                    // END 2022/10/23 T.Suzuki [QC#58427, ADD]
                    if (hasValue(dsContrDtlTMsg.contrCloDt)) {
                        nextEndDt = dsContrDtlTMsg.contrCloDt.getValue();
                    }
                    if (ZYPDateUtil.compare(nextStartDt, nextEndDt) < 0) {
                        NSZC047008PMsg nszc047008PMsg = new NSZC047008PMsg();
                        setValue(nszc047008PMsg.glblCmpyCd, glblCmpyCd);
                        setValue(nszc047008PMsg.slsDt, slsDt);
                        setValue(nszc047008PMsg.xxModeCd, "08");
                        setValue(nszc047008PMsg.dsContrDtlPk, dsContrDtlPk);
                        setValue(nszc047008PMsg.baseChrgFlg, FLG_ON_Y);
                        setValue(nszc047008PMsg.usgChrgFlg, FLG_OFF_N);
                        setValue(nszc047008PMsg.contrCloDay, dsContrDtlTMsg.contrCloDay);
                        setValue(nszc047008PMsg.baseBllgTmgCd, dsContrDtlTMsg.baseBllgTmgCd);
                        setValue(nszc047008PMsg.contrBllgDay, dsContrDtlTMsg.contrBllgDay);
                        setValue(nszc047008PMsg.contrEffFromDt, dsContrDtlTMsg.contrEffFromDt);
                        setValue(nszc047008PMsg.contrEffThruDt, dsContrDtlTMsg.contrEffThruDt);
                        // START 2022/10/23 T.Suzuki [QC#58427, ADD]
                        setValue(nszc047008PMsg.svcCrRebilPk, svcCrRebilPk);
                        // END 2022/10/23 T.Suzuki [QC#58427, ADD]

                        // START 2022/10/23 T.Suzuki [QC#58427, DEL]
                        // START 2022/09/27 L.Mandanas [QC#58427, ADD]
                        // String baseRnwlHoldSts = query.getBaseRnwlHold(glblCmpyCd, dsContrDtlTMsg.dsContrDtlPk.getValue(),
                        //            dsContrDtlTMsg.contrEffThruDt.getValue());
                        // END 2022/09/27 L.Mandanas [QC#58427, ADD]
                        // END 2022/10/23 T.Suzuki [QC#58427, DEL]

                        NSZC047008_xxBaseLineListPMsg baseLine = nszc047008PMsg.xxBaseLineList.no(0);
                        if (prcEffInfo != null && !prcEffInfo.isEmpty()) {
                            setValue(baseLine.dsContrPrcEffPk_BL, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_PRC_EFF_SQ));
                            setValue(baseLine.dsContrPrcEffSqNum_BL, (BigDecimal) prcEffInfo.get("NEXT_SQ_NUM"));
                            if (ZYPDateUtil.compare(nextStartDt, slsDt) <= 0 && ZYPDateUtil.compare(slsDt, nextEndDt) <= 0) {
                                // START 2022/09/27 L.Mandanas [QC#58427, MOD]
                                // START 2022/10/23 T.Suzuki [QC#58427, MOD]
                                // if (hasValue(baseRnwlHoldSts)) {
                                if (hasValue(baseRnwlHoldSts) && ZYPConstant.FLG_OFF_N.equals(pendingHold)) {
                                     setValue(baseLine.dsContrPrcEffStsCd_BL, baseRnwlHoldSts);
                                } else {
                                     setValue(baseLine.dsContrPrcEffStsCd_BL, DS_CONTR_DTL_STS.ACTIVE);
                                }
                                //setValue(baseLine.dsContrPrcEffStsCd_BL, DS_CONTR_DTL_STS.ACTIVE);
                                // END 2022/10/23 T.Suzuki [QC#58427, MOD]
                                // END 2022/09/27 L.Mandanas [QC#58427, MOD]
                                setValue(baseLine.mtrHldFlg_BL, dsContrDtlTMsg.mtrHldFlg);
                            } else {
                                // START 2022/09/27 L.Mandanas [QC#58427, MOD]
                                // START 2022/10/23 T.Suzuki [QC#58427, MOD]
                                // if (hasValue(baseRnwlHoldSts)) {
                                if (hasValue(baseRnwlHoldSts) && ZYPConstant.FLG_OFF_N.equals(pendingHold)) {
                                     setValue(baseLine.dsContrPrcEffStsCd_BL, baseRnwlHoldSts);
                                } else {
                                     setValue(baseLine.dsContrPrcEffStsCd_BL, DS_CONTR_DTL_STS.SIGNED);
                                }
                                //setValue(baseLine.dsContrPrcEffStsCd_BL, DS_CONTR_DTL_STS.SIGNED);
                                // END 2022/10/23 T.Suzuki [QC#58427, MOD]
                                // END 2022/09/27 L.Mandanas [QC#58427, MOD]
                                setValue(baseLine.mtrHldFlg_BL, ZYPConstant.FLG_OFF_N);
                            }
                            setValue(baseLine.effFromDt_BL, (String) nextStartDt);
                            setValue(baseLine.effThruDt_BL, nextEndDt);
                            setValue(baseLine.qltyAsrnHldFlg_BL, dsContrDtlTMsg.qltyAsrnHldFlg);
                            setValue(baseLine.contrHldFlg_BL, dsContrDtlTMsg.contrHldFlg);
                            setValue(baseLine.bllgHldFlg_BL, dsContrDtlTMsg.bllgHldFlg);
                        }
                        setValue(baseLine.baseBllgCycleCd_BL, dsContrDtlTMsg.baseBllgCycleCd);
                        setValue(baseLine.basePrcDealAmt_BL, dsContrDtlTMsg.basePrcDealAmt);
                        BigDecimal newBasePrcDealAmt = query.getCrRebilBase(glblCmpyCd, dsContrDtlPk, preSchdDt);
                        if (hasValue(newBasePrcDealAmt)) {
                            setValue(baseLine.basePrcDealAmt_BL, newBasePrcDealAmt);
                        }
                        nszc047008PMsg.xxBaseLineList.setValidCount(1);
    
                        new NSZC047001().execute(nszc047008PMsg, onBatchTp);
                        if (hasValue(nszc047008PMsg.xxMsgIdList.no(0).xxMsgId)) {
                            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(nszc047008PMsg);
                            for (S21ApiMessage msg : msgList) {
                                msgMap.addXxMsgIdWithPrm(msg.getXxMsgid(), msg.getXxMsgPrmArray());
                            }
                            return;
                        }

                        // START 2022/10/23 T.Suzuki [QC#58427, ADD]
                        if (ZYPConstant.FLG_ON_Y.equals(pendingHold)) {
                            BigDecimal seq = baseLine.dsContrPrcEffSqNum_BL.getValue();
                            String stDay = ZYPDateUtil.addDays(nextEndDt, 1);
                            setValue(baseLine.dsContrPrcEffSqNum_BL, seq.add(BigDecimal.valueOf(1)));
                            setValue(baseLine.dsContrPrcEffStsCd_BL, baseRnwlHoldSts);
                            setValue(baseLine.effFromDt_BL, stDay);
                            setValue(baseLine.effThruDt_BL, dsContrDtlTMsg.contrEffThruDt);;
                            new NSZC047001().execute(nszc047008PMsg, onBatchTp);
                            if (hasValue(nszc047008PMsg.xxMsgIdList.no(0).xxMsgId)) {
                                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(nszc047008PMsg);
                                for (S21ApiMessage msg : msgList) {
                                    msgMap.addXxMsgIdWithPrm(msg.getXxMsgid(), msg.getXxMsgPrmArray());
                                }
                                return;
                            }
                        }
                        // END 2022/10/23 T.Suzuki [QC#58427, ADD]
                    }
                // Mod End 2017/10/19 QC#21815
                }
                // START 2018/09/05 [QC#24555, ADD]
                logicalRemoveTopSchd(glblCmpyCd, dsContrDtlPk, null, FLG_ON_Y);
                // END   2018/09/05 [QC#24555, ADD]

                // START 2021/06/28 T.Wada [QC#58177-5,MOD]
                if (!isBasePriceChg 
                        && DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd) 
                        && DS_CONTR_DTL_TP.ACCESSORIES.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())){

                    List<Map<String, Object>> prntlessBllgSchdList = query.getPrntlessBllgSchdList(glblCmpyCd, dsContrDtlTMsg.dsContrDtlPk.getValue(), dsContrDtlTMsg.dsContrPk.getValue());

                    for (Map<String, Object> prntlessBllgSchd : prntlessBllgSchdList) {
                        String bllgSchdFromDt = (String) prntlessBllgSchd.get("BLLG_SCHD_FROM_DT");
                        String bllgSchdThruDt = (String) prntlessBllgSchd.get("BLLG_SCHD_THRU_DT");
                        BigDecimal dsContrBllgSchdPk = (BigDecimal) prntlessBllgSchd.get("DS_CONTR_BLLG_SCHD_PK");

                        Map<String, Object> prntBllgSchdInfo = query.getPrntBllgSchdPk(pMsg.glblCmpyCd.getValue(), 
                                dsContrDtlTMsg.prntDsContrDtlPk.getValue(), bllgSchdFromDt, bllgSchdThruDt,
                                INV_TP.INVOICE, dsContrDtlTMsg.dsContrDtlPk.getValue());

                        BigDecimal prntDsContrBllgSchdPk = (BigDecimal) prntBllgSchdInfo.get("DS_CONTR_BLLG_SCHD_PK");

                        if (ZYPCommonFunc.hasValue(prntDsContrBllgSchdPk)) {
                            DS_CONTR_BLLG_SCHDTMsg tMsg = new DS_CONTR_BLLG_SCHDTMsg();
                            tMsg = query.getDsContrBllgSchdForUpdate(glblCmpyCd, dsContrBllgSchdPk);
                            setValue(tMsg.prntDsContrBllgSchdPk, prntDsContrBllgSchdPk);
                            S21ApiTBLAccessor.update(tMsg);
                        }
                    }
                }
                // END 2021/06/28 T.Wada [QC#58177-5,MOD]

            }
            // START 2017/10/17 K.Kojima [QC#21795,MOD]
            // if (isUsgChrg(dsContrDtlTMsg)) {
            if (isUsgChrg(dsContrDtlTMsg) && isUsgChrg) {
            // END 2017/10/17 K.Kojima [QC#21795,MOD]
                DS_CONTR_BLLG_MTRTMsgArray dsContrBllgMtrTMsgArray = query.getDsContrBllgMtrTMsg(glblCmpyCd, dsContrDtlPk);

                for (int i = 0; i < dsContrBllgMtrTMsgArray.getValidCount(); i++) {
                    DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg = dsContrBllgMtrTMsgArray.no(i);

                    // START 2022/10/23 T.Suzuki [QC#58427, ADD]
                    Map<String, Object>currentPrcEffInfoUsg = query.getCurrentPrcEffForUsg(glblCmpyCd, slsDt, dsContrDtlPk, dsContrBllgMtrTMsg.dsContrBllgMtrPk.getValue());
                    // END 2022/10/23 T.Suzuki [QC#58427, ADD]

                    // Mod Start 2017/10/19 QC#21815
                    // START 2018/09/03 K.Kojima [QC#27960,MOD]
                    // dividePrcEffForUsage(dsContrDtlTMsg, dsContrBllgMtrTMsg, crRebilTMsg, slsDt);
                    // START 2023/01/16 R.Jin [QC#58890, MOD]
//                    String invEndPrcEffDt = dividePrcEffForUsage(dsContrDtlTMsg, dsContrBllgMtrTMsg, crRebilTMsg, slsDt);
                    String invEndPrcEffDt = dividePrcEffForUsage(dsContrDtlTMsg, dsContrBllgMtrTMsg, crRebilTMsg, slsDt, dsContrCatgCd);
                    // END 2023/01/16 R.Jin [QC#58890, MOD]
                    // END 2018/09/03 K.Kojima [QC#27960,MOD]

                    //  Start 2021/04/27 T.Wada [QC#58177-3,MOD]
                    if(isNotChangedMtrAmtRate(crRebilTMsg, dsContrBllgMtrTMsg, dsContrTMsg)){
                        continue;
                    }
                    //  END 2021/04/27 T.Wada [QC#58177-3,MOD]

                    Map<String, Object> prcEffInfo = query.getPrcEffForUsg(glblCmpyCd, slsDt, dsContrDtlPk, dsContrBllgMtrTMsg.dsContrBllgMtrPk.getValue(), svcCrRebilPk);
                    if (prcEffInfo == null || prcEffInfo.isEmpty()) {
                        continue;
                    }
                    String nextStartDt = (String) prcEffInfo.get("NEXT_START_DT");
                    // START 2018/09/03 K.Kojima [QC#27960,ADD]
                    if (invEndPrcEffDt != null) {
                        nextStartDt = ZYPDateUtil.addDays(invEndPrcEffDt, 1);
                    }
                    // END 2018/09/03 K.Kojima [QC#27960,ADD]
                    String nextEndDt = dsContrDtlTMsg.contrEffThruDt.getValue();

                    // START 2022/10/23 T.Suzuki [QC#58427, ADD]
                    String usgRnwlHoldSts = query.getUsgRnwlHold(glblCmpyCd, dsContrDtlTMsg.dsContrDtlPk.getValue(),
                            dsContrBllgMtrTMsg.dsContrBllgMtrPk.getValue(), dsContrDtlTMsg.contrEffThruDt.getValue());
                    String currentThruDt = "";
                    String pendingHold = ZYPConstant.FLG_OFF_N;
                    if (currentPrcEffInfoUsg != null) {
                        currentThruDt = (String) currentPrcEffInfoUsg.get("CONTR_PRC_EFF_THRU_DT");
                    }
                    if (hasValue(usgRnwlHoldSts) &&  ZYPDateUtil.compare(currentThruDt, dsContrDtlTMsg.contrEffThruDt.getValue()) < 0) {
                        pendingHold = ZYPConstant.FLG_ON_Y;
                        nextEndDt = currentThruDt;
                    }
                    // END 2022/10/23 T.Suzuki [QC#58427, ADD]
                    // START 2019/11/01 [QC#54419,ADD]
                    if (hasValue(dsContrDtlTMsg.contrCloDt)) {
                        nextEndDt = dsContrDtlTMsg.contrCloDt.getValue();
                    }
                    // END 2019/11/01 [QC#54419,ADD]
                    BigDecimal dsContrPrcMtrPk = (BigDecimal) prcEffInfo.get("DS_CONTR_PRC_EFF_PK");
                    DS_CONTR_PRC_EFF_MTRTMsgArray xsList = query.getPrcEffTMsgArray(glblCmpyCd, dsContrPrcMtrPk, dsContrBllgMtrTMsg.dsContrBllgMtrPk.getValue());

                    // START 2022/10/23 T.Suzuki [QC#58427, DEL]
                    // START 2022/09/27 L.Mandanas [QC#58427, MOD]
                    // String usgRnwlHoldSts = query.getUsgRnwlHold(glblCmpyCd, dsContrDtlTMsg.dsContrDtlPk.getValue(),
                    //            dsContrBllgMtrTMsg.dsContrBllgMtrPk.getValue(), dsContrDtlTMsg.contrEffThruDt.getValue());
                    // END 2022/09/27 L.Mandanas [QC#58427, MOD]
                    // END 2022/10/23 T.Suzuki [QC#58427, DEL]

                    if (ZYPDateUtil.compare(nextStartDt, nextEndDt) < 0) {
                        NSZC047008PMsg nszc047008PMsg = new NSZC047008PMsg();
                        setValue(nszc047008PMsg.glblCmpyCd, glblCmpyCd);
                        setValue(nszc047008PMsg.slsDt, slsDt);
                        setValue(nszc047008PMsg.xxModeCd, "08");
                        setValue(nszc047008PMsg.dsContrDtlPk, dsContrDtlPk);
                        setValue(nszc047008PMsg.baseChrgFlg, FLG_OFF_N);
                        setValue(nszc047008PMsg.usgChrgFlg, FLG_ON_Y);
                        setValue(nszc047008PMsg.mtrCloDay, dsContrDtlTMsg.mtrCloDay);
                        setValue(nszc047008PMsg.mtrBllgTmgCd, dsContrDtlTMsg.mtrBllgTmgCd);
                        setValue(nszc047008PMsg.mtrBllgDay, dsContrDtlTMsg.mtrBllgDay);
                        setValue(nszc047008PMsg.contrEffFromDt, dsContrDtlTMsg.contrEffFromDt);
                        setValue(nszc047008PMsg.contrEffThruDt, dsContrDtlTMsg.contrEffThruDt);
                        // START 2022/10/23 T.Suzuki [QC#58427, ADD]
                        setValue(nszc047008PMsg.svcCrRebilPk, svcCrRebilPk);
                        // END 2022/10/23 T.Suzuki [QC#58427, ADD]

                        int mtrIdx = 0;
                        for (; mtrIdx < xsList.getValidCount(); mtrIdx++) {
                            NSZC047008_xxMtrLineListPMsg mtrLine = nszc047008PMsg.xxMtrLineList.no(mtrIdx);
                            setValue(mtrLine.dsContrPrcEffPk_ML, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_PRC_EFF_SQ));
                            setValue(mtrLine.dsContrPrcEffSqNum_ML, (BigDecimal) prcEffInfo.get("NEXT_SQ_NUM"));
                            setValue(mtrLine.effFromDt_ML, nextStartDt);
                            setValue(mtrLine.effThruDt_ML, nextEndDt);
                            if (ZYPDateUtil.compare(nextStartDt, slsDt) <= 0 && ZYPDateUtil.compare(slsDt, nextEndDt) <= 0) {
                                // START 2022/09/27 L.Mandanas [QC#58427, MOD]
                                // START 2022/10/23 T.Suzuki [QC#58427, MOD]
                                // if (hasValue(usgRnwlHoldSts)) {
                                if (hasValue(usgRnwlHoldSts) && ZYPConstant.FLG_OFF_N.equals(pendingHold)) {
                                     setValue(mtrLine.dsContrPrcEffStsCd_ML, usgRnwlHoldSts);
                                } else {
                                     setValue(mtrLine.dsContrPrcEffStsCd_ML, DS_CONTR_DTL_STS.ACTIVE);
                                }
                                //setValue(baseLine.dsContrPrcEffStsCd_ML, DS_CONTR_DTL_STS.ACTIVE);
                                // END 2022/10/23 T.Suzuki [QC#58427, MOD]
                                // END 2022/09/27 L.Mandanas [QC#58427, MOD]
                                setValue(mtrLine.mtrHldFlg_ML, dsContrDtlTMsg.mtrHldFlg);
                            } else {
                                // START 2022/09/27 L.Mandanas [QC#58427, MOD]
                                // START 2022/10/23 T.Suzuki [QC#58427, MOD]
                                // if (hasValue(usgRnwlHoldSts)) {
                                if (hasValue(usgRnwlHoldSts) && ZYPConstant.FLG_OFF_N.equals(pendingHold)) {
                                     setValue(mtrLine.dsContrPrcEffStsCd_ML, usgRnwlHoldSts);
                                } else {
                                     setValue(mtrLine.dsContrPrcEffStsCd_ML, DS_CONTR_DTL_STS.SIGNED);
                                }
                                //setValue(baseLine.dsContrPrcEffStsCd_ML, DS_CONTR_DTL_STS.SIGNED);
                                // END 2022/10/23 T.Suzuki [QC#58427, MOD]
                                // END 2022/09/27 L.Mandanas [QC#58427, MOD]
                                setValue(mtrLine.mtrHldFlg_ML, ZYPConstant.FLG_OFF_N);
                            }
                            setValue(mtrLine.mtrBllgCycleCd_ML, dsContrBllgMtrTMsg.bllgMtrBllgCycleCd);
                            setValue(mtrLine.dsContrBllgMtrPk_ML, dsContrBllgMtrTMsg.dsContrBllgMtrPk);
                            setValue(mtrLine.contrXsCopyPk_ML, xsList.no(mtrIdx).contrXsCopyPk);
                            setValue(mtrLine.xsMtrCopyQty_ML, xsList.no(mtrIdx).xsMtrCopyQty);
                            setValue(mtrLine.xsMtrAmtRate_ML, xsList.no(mtrIdx).xsMtrAmtRate);
                            setValue(mtrLine.xsMtrFirstFlg_ML, xsList.no(mtrIdx).xsMtrFirstFlg);
                            setValue(mtrLine.qltyAsrnHldFlg_ML, dsContrDtlTMsg.qltyAsrnHldFlg);
                            setValue(mtrLine.contrHldFlg_ML, dsContrDtlTMsg.contrHldFlg);
                            setValue(mtrLine.bllgHldFlg_ML, dsContrDtlTMsg.bllgHldFlg);
                        }
                        nszc047008PMsg.xxMtrLineList.setValidCount(mtrIdx);

                        new NSZC047001().execute(nszc047008PMsg, onBatchTp);
                        if (hasValue(nszc047008PMsg.xxMsgIdList.no(0).xxMsgId)) {
                            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(nszc047008PMsg);
                            for (S21ApiMessage msg : msgList) {
                                msgMap.addXxMsgIdWithPrm(msg.getXxMsgid(), msg.getXxMsgPrmArray());
                            }
                            return;
                        }
                        // START 2022/10/23 T.Suzuki [QC#58427, ADD]
                        if (ZYPConstant.FLG_ON_Y.equals(pendingHold)) {
                            String stDay = ZYPDateUtil.addDays(nextEndDt, 1);
                            for (mtrIdx = 0; mtrIdx < xsList.getValidCount(); mtrIdx++) {
                                NSZC047008_xxMtrLineListPMsg mtrLine = nszc047008PMsg.xxMtrLineList.no(mtrIdx);
                                BigDecimal seq = mtrLine.dsContrPrcEffSqNum_ML.getValue();
                                setValue(mtrLine.dsContrPrcEffSqNum_ML, seq.add(BigDecimal.valueOf(1)));
                                setValue(mtrLine.dsContrPrcEffStsCd_ML, usgRnwlHoldSts);
                                setValue(mtrLine.effFromDt_ML, stDay);
                                setValue(mtrLine.effThruDt_ML, dsContrDtlTMsg.contrEffThruDt);
                            }
                            new NSZC047001().execute(nszc047008PMsg, onBatchTp);
                            if (hasValue(nszc047008PMsg.xxMsgIdList.no(0).xxMsgId)) {
                                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(nszc047008PMsg);
                                for (S21ApiMessage msg : msgList) {
                                    msgMap.addXxMsgIdWithPrm(msg.getXxMsgid(), msg.getXxMsgPrmArray());
                                }
                                return;
                            }
                        }
                        // END 2022/10/23 T.Suzuki [QC#58427, ADD]
                    }
                    // Mod End 2017/10/19 QC#21815

                    // START 2018/09/05 [QC#24555, ADD]
                    logicalRemoveTopSchd(glblCmpyCd, dsContrDtlPk, dsContrBllgMtrTMsg.dsContrBllgMtrPk.getValue(), FLG_OFF_N);
                    // END   2018/09/05 [QC#24555, ADD]
                }
            }

            // START 2018/09/05 [QC#24555, ADD]
            NSZC053001CommonLogic.roundAdjustForApproval(msgMap, crRebilTMsg, dsContrDtlPk);
            // END   2018/09/05 [QC#24555, ADD]

            // Add Start 2017/10/25 QC#21815
            if (isBaseCalc) {
                BigDecimal basePrcTermDealAmt = query.getDtlTermDealAmt(glblCmpyCd, dsContrDtlPk);
                dsContrDtlTMsg = query.getDsContrDtlTMsgForUpdate(glblCmpyCd, dsContrDtlPk);
                if (dsContrDtlTMsg == null) {
                    continue;
                }
                BigDecimal basePrcDealAmt = query.getCurPeBasePrcDealAmt(glblCmpyCd, dsContrDtlPk, slsDt);
                if (hasValue(basePrcDealAmt)) {
                    setValue(dsContrDtlTMsg.basePrcDealAmt, basePrcDealAmt);
                }
                setValue(dsContrDtlTMsg.basePrcTermDealAmtRate, basePrcTermDealAmt);
                S21ApiTBLAccessor.update(dsContrDtlTMsg);
            }
            // Add End 2017/10/25 QC#21815
        }
        if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd) && isBaseCalc) {
            sumAggregate(msgMap, onBatchTp, dsContrPk);
        }
    }

    private static boolean isUnderFleet(String dsContrCatgCd, String dsContrDtlTpCd) {
        if (!DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
            return false;
        }
        if (DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTpCd)) {
            return false;
        }
        return true;
    }

    private static boolean isBaseChrg(DS_CONTR_DTLTMsg dsContrDtlTMsg) {
        DS_CONTR_DTL_TPTMsg dtlTp = (DS_CONTR_DTL_TPTMsg) query.getCodeTbl(DS_CONTR_DTL_TP.class.getSimpleName(), dsContrDtlTMsg.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlTpCd.getValue());
        return FLG_ON_Y.equals(dtlTp.baseChrgFlg.getValue());
    }

    private static boolean isUsgChrg(DS_CONTR_DTLTMsg dsContrDtlTMsg) {
        DS_CONTR_DTL_TPTMsg dtlTp = (DS_CONTR_DTL_TPTMsg) query.getCodeTbl(DS_CONTR_DTL_TP.class.getSimpleName(), dsContrDtlTMsg.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlTpCd.getValue());
        return FLG_ON_Y.equals(dtlTp.usgChrgFlg.getValue());
    }

    private static void sumAggregate(S21ApiMessageMap msgMap, ONBATCH_TYPE onBatchTp, BigDecimal dsContrPk) {
        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String slsDt = pMsg.slsDt.getValue();
        BigDecimal aggLinePk = query.getAggLinePk(glblCmpyCd, dsContrPk);
        if (!hasValue(aggLinePk)) {
            return;
        }
        NSZC047011PMsg nszc047011PMsg = new NSZC047011PMsg();
        setValue(nszc047011PMsg.glblCmpyCd, glblCmpyCd);
        setValue(nszc047011PMsg.slsDt, slsDt);
        setValue(nszc047011PMsg.xxModeCd, "11");
        setValue(nszc047011PMsg.dsContrDtlPk, aggLinePk);
        new NSZC047001().execute(nszc047011PMsg, onBatchTp);
        if (hasValue(nszc047011PMsg.xxMsgIdList.no(0).xxMsgId)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(nszc047011PMsg);
            for (S21ApiMessage msg : msgList) {
                msgMap.addXxMsgIdWithPrm(msg.getXxMsgid(), msg.getXxMsgPrmArray());
            }
            return;
        }
    }

    /**
     * isPriceChange
     * @param crRebilDtlTMsg SVC_CR_REBIL_DTLTMsg
     */
    public static boolean isPriceChange(SVC_CR_REBILTMsg crRebilTMsg) {
        String glblCmpyCd = crRebilTMsg.glblCmpyCd.getValue();
        BigDecimal svcCrRebilPk = crRebilTMsg.svcCrRebilPk.getValue();
        BigDecimal countBaseBllg = query.countCrRebilBaseBllg(glblCmpyCd, svcCrRebilPk);
        if (countBaseBllg.compareTo(BigDecimal.ZERO) > 0) {
            return true;
        }
        BigDecimal countMtrBllg = query.countCrRebilMtrBllg(glblCmpyCd, svcCrRebilPk);
        if (countMtrBllg.compareTo(BigDecimal.ZERO) > 0) {
            return true;
        }
        BigDecimal countXsMtrBllg = query.countCrRebilXsMtrBllg(glblCmpyCd, svcCrRebilPk);
        if (countXsMtrBllg.compareTo(BigDecimal.ZERO) > 0) {
            return true;
        }
        return false;
    }

    /**
     * isPriceChange
     * @param msgMap S21ApiMessageMap
     */
    public static boolean isPriceChange(S21ApiMessageMap msgMap) {
        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        for (int i = 0; i < pMsg.xxCrRebilDtlList.getValidCount(); i++) {
            NSZC053001_xxCrRebilDtlListPMsg inPMsg = pMsg.xxCrRebilDtlList.no(i);
            // Xs Meter
            if (hasValues(inPMsg.mtrBllgFromDt, inPMsg.mtrBllgThruDt, inPMsg.oldXsCopyQty, inPMsg.oldXsMtrAmtRate, inPMsg.newXsCopyQty, inPMsg.newXsMtrAmtRate)) {
                return true;
            }
            // Base Charge
            if (hasValues(inPMsg.baseBllgFromDt, inPMsg.baseBllgThruDt, inPMsg.newBaseDealAmt)) {
                return true;
            }
        }
        return false;
    }
    // END 2017/09/26 K.Kitachi [QC#21212, ADD]

    // START 2017/10/17 K.Kojima [QC#21795,ADD]
    private static boolean isBaseChrgCrReillTarget(NSZC053001PMsg pMsg) {
        List<BigDecimal> svcCrRebilDtlPkList = null;
        if (pMsg.xxCrRebilDtlList.getValidCount() != 0) {
            svcCrRebilDtlPkList = new ArrayList<BigDecimal>(pMsg.xxCrRebilDtlList.getValidCount());
            for (int i = 0; i < pMsg.xxCrRebilDtlList.getValidCount(); i++) {
                svcCrRebilDtlPkList.add(pMsg.xxCrRebilDtlList.no(i).svcCrRebilDtlPk.getValue());
            }
        }
        BigDecimal count = NSZC0530Query.getInstance().countSvcCrRebilBaseBillg(pMsg.glblCmpyCd.getValue(), pMsg.svcCrRebilPk.getValue(), svcCrRebilDtlPkList);
        if (count != null && count.compareTo(BigDecimal.ZERO) > 0) {
            return true;
        }
        return false;
    }

    private static boolean isUsgChrgCrReillTarget(NSZC053001PMsg pMsg) {
        List<BigDecimal> svcCrRebilDtlPkList = null;
        if (pMsg.xxCrRebilDtlList.getValidCount() != 0) {
            svcCrRebilDtlPkList = new ArrayList<BigDecimal>(pMsg.xxCrRebilDtlList.getValidCount());
            for (int i = 0; i < pMsg.xxCrRebilDtlList.getValidCount(); i++) {
                svcCrRebilDtlPkList.add(pMsg.xxCrRebilDtlList.no(i).svcCrRebilDtlPk.getValue());
            }
        }
        BigDecimal count = NSZC0530Query.getInstance().countSvcCrRebilMtrBillg(pMsg.glblCmpyCd.getValue(), pMsg.svcCrRebilPk.getValue(), svcCrRebilDtlPkList);
        if (count != null && count.compareTo(BigDecimal.ZERO) > 0) {
            return true;
        }
        return false;
    }
    // END 2017/10/17 K.Kojima [QC#21795,ADD]
    // Add Start 2017/10/19 QC#21815
    // START 2018/09/03 K.Kojima [QC#27960,MOD]
    // private static void dividePrcEffForBase(DS_CONTR_DTLTMsg dsContrDtlTMsg, SVC_CR_REBILTMsg crRebilTMsg, String slsDt) {
    private static String dividePrcEffForBase(DS_CONTR_DTLTMsg dsContrDtlTMsg, SVC_CR_REBILTMsg crRebilTMsg, String slsDt) {
    // END 2018/09/03 K.Kojima [QC#27960,MOD]
        if (dsContrDtlTMsg == null || crRebilTMsg == null) {
            // START 2018/09/03 K.Kojima [QC#27960,MOD]
            // return;
            return null;
            // END 2018/09/03 K.Kojima [QC#27960,MOD]
        }
        String glblCmpyCd = dsContrDtlTMsg.glblCmpyCd.getValue();
        BigDecimal dsContrDtlPk = dsContrDtlTMsg.dsContrDtlPk.getValue();
        BigDecimal svcCrRebilPk = crRebilTMsg.svcCrRebilPk.getValue();
        List<Map<String, Object>> targetRbilSchdList = query.getTargetRbilSchdForBase(glblCmpyCd, dsContrDtlPk, svcCrRebilPk);
        if (targetRbilSchdList.size() == 0) {
            // START 2018/09/03 K.Kojima [QC#27960,MOD]
            // return;
            return null;
            // END 2018/09/03 K.Kojima [QC#27960,MOD]
        }
        // START 2018/08/27 [QC#24555, MOD]
//        String invEndPrcEffDt = (String) targetRbilSchdList.get(targetRbilSchdList.size() - 1).get("RBIL_BLLG_SCHD_THRU_DT");
        String invEndPrcEffDt = query.getInvSchdMaxThruDt(glblCmpyCd, dsContrDtlPk, null, FLG_ON_Y);
        // END   2018/08/27 [QC#24555, MOD]
        String startPrcEffDt;
        // START 2018/08/27 [QC#24555, DEL]
//        BigDecimal rbilSchdBaseAmt;
        // START 2018/08/27 [QC#24555, DEL]
        BigDecimal newBaseUnitAmt;
        BigDecimal targetPrcEffPk;
        DS_CONTR_PRC_EFFTMsg dsContrPrcEffTMsg;
        BigDecimal targetTopSchdPk;
        DS_CONTR_BLLG_SCHD_SMRYTMsg dsContrBllgSchdSmryTMsg;
        DS_CONTR_BLLG_SCHD_SMRYTMsg rbilDsContrBllgSchdSmryTMsg;
        DS_CONTR_PRC_EFFTMsg newPrcEffTMsg;
        String endDt;
        BigDecimal origDsContrBllgSchdPk;
        BigDecimal rbilDsContrBllgSchdPk;
        DS_CONTR_BLLG_SCHDTMsg origDsContrBllgSchdTMsg;
        DS_CONTR_BLLG_SCHDTMsg rbilDsContrBllgSchdTMsg;
        Map<String, Object> totalTopSchdAmtMap;
        BigDecimal totalRbilPrcEffAmt;
        // START 2018/09/05 [QC#24555, ADD]
        String  preBllgSchdPrrtFlg = FLG_OFF_N;
        String  curBllgSchdPrrtFlg = FLG_OFF_N;
        int topSchdSq   = 1;
        DS_CONTR_BLLG_SCHD_SMRYTMsg preDsContrBllgSchdSmryTMsg = null;
        String firstEffFromDt = null;
        // END   2018/09/05 [QC#24555, ADD]
        for (Map<String, Object> targetRbilSchd : targetRbilSchdList) {
            startPrcEffDt = (String) targetRbilSchd.get("RBIL_BLLG_SCHD_FROM_DT");
            // START 2018/08/27 [QC#24555, MOD]
//            rbilSchdBaseAmt = (BigDecimal) targetRbilSchd.get("RBIL_BASE_PRC_DEAL_AMT");
            newBaseUnitAmt = (BigDecimal) targetRbilSchd.get("NEW_BASE_UNIT_AMT");
            // END   2018/08/27 [QC#24555, MOD]
            // START 2018/09/05 [QC#24555, ADD]
            curBllgSchdPrrtFlg = (String) targetRbilSchd.get("BLLG_SCHD_PRRT_FLG");
            // END   2018/09/05 [QC#24555, ADD]
            targetPrcEffPk = query.getTargetPrcEffPkForBase(glblCmpyCd, dsContrDtlPk, startPrcEffDt);
            dsContrPrcEffTMsg = query.getDsContrPrcEffTMsgForUpdate(glblCmpyCd, targetPrcEffPk);
            if (dsContrPrcEffTMsg == null) {
                continue;
            }
            targetTopSchdPk = query.getTargetTopSchdPkForBase(glblCmpyCd, dsContrDtlPk, dsContrPrcEffTMsg.dsContrPrcEffPk.getValue(), dsContrPrcEffTMsg.dsContrPrcEffSqNum.getValue(), startPrcEffDt);
            dsContrBllgSchdSmryTMsg = query.getDsContrBllgSchdSmryForUpdate(glblCmpyCd, targetTopSchdPk);
            if (dsContrBllgSchdSmryTMsg == null) {
                continue;
            }
            rbilDsContrBllgSchdSmryTMsg = query.getDsContrBllgSchdSmryForUpdate(glblCmpyCd, (BigDecimal) targetRbilSchd.get("RBIL_CONTR_BLLG_SCHD_SMRY_PK"));
            if (rbilDsContrBllgSchdSmryTMsg == null) {
                continue;
            }
            rbilDsContrBllgSchdPk = (BigDecimal) targetRbilSchd.get("RBIL_DS_CONTR_BLLG_SCHD_PK");
            // START 2018/08/27 [QC#24555, DEL]
//            if (DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
//                rbilSchdBaseAmt = query.getSchdAmtForAggLine(glblCmpyCd, rbilDsContrBllgSchdPk);
//            }
            
            // END   2018/08/27 [QC#24555, DEL]

            // START 2018/09/05 [QC#24555, ADD]
            if (firstEffFromDt == null) {
                firstEffFromDt = startPrcEffDt;
            }
            // END   2018/09/05 [QC#24555, ADD]

            // START 2018/08/27 [QC#24555, MOD]
//            if (rbilSchdBaseAmt.compareTo(dsContrPrcEffTMsg.basePrcDealAmt.getValue()) != 0) {
            if (!DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue()) && newBaseUnitAmt.compareTo(dsContrPrcEffTMsg.basePrcDealAmt.getValue()) != 0) {
            // END   2018/08/27 [QC#24555, MOD]
                newPrcEffTMsg = new DS_CONTR_PRC_EFFTMsg();
                //start 2017/11/07 QC#22301,mod
                EZDMsg.copy(dsContrPrcEffTMsg, null, newPrcEffTMsg, null);
                if (startPrcEffDt.compareTo(dsContrPrcEffTMsg.contrPrcEffFromDt.getValue()) != 0) {
                    endDt = ZYPDateUtil.addDays(startPrcEffDt, -1);
                    setValue(dsContrPrcEffTMsg.contrPrcEffThruDt, endDt);

                    totalRbilPrcEffAmt = query.getRbilPrcEffAmtForBase(glblCmpyCd, targetPrcEffPk, dsContrPrcEffTMsg.contrPrcEffFromDt.getValue(), endDt);
                    setValue(dsContrPrcEffTMsg.basePrcTermDealAmtRate, totalRbilPrcEffAmt);
                    if (ZYPDateUtil.compare(dsContrPrcEffTMsg.contrPrcEffFromDt.getValue(), slsDt) <= 0 && ZYPDateUtil.compare(slsDt, dsContrPrcEffTMsg.contrPrcEffThruDt.getValue()) <= 0) {
                        setValue(dsContrPrcEffTMsg.dsContrPrcEffStsCd, DS_CONTR_DTL_STS.ACTIVE);
                    //start 2018/01/11 QC#23420,add
                    } else if (ZYPDateUtil.compare(slsDt, dsContrPrcEffTMsg.contrPrcEffFromDt.getValue()) <= 0) {
                        setValue(dsContrPrcEffTMsg.dsContrPrcEffStsCd, DS_CONTR_DTL_STS.SIGNED);
                    //end 2018/01/11 QC#23420,add
                    } else {
                        setValue(dsContrPrcEffTMsg.dsContrPrcEffStsCd, DS_CONTR_DTL_STS.EXPIRED);
                    }
                    S21ApiTBLAccessor.update(dsContrPrcEffTMsg);

                    // Mod Start 2018/05/14 QC#24878
                    if (ZYPDateUtil.compare(dsContrBllgSchdSmryTMsg.bllgSchdFromDt.getValue(), endDt) <= 0) {
                        setValue(dsContrBllgSchdSmryTMsg.bllgSchdThruDt, endDt);
                        setValue(dsContrBllgSchdSmryTMsg.perSchdNum, calcPerSchdNum(dsContrBllgSchdSmryTMsg, dsContrDtlTMsg.contrCloDay.getValue()));
                        totalTopSchdAmtMap = query.getRbilTopSchdAmtForBase(glblCmpyCd, targetTopSchdPk, dsContrBllgSchdSmryTMsg.bllgSchdFromDt.getValue(), endDt);
                        setValue(dsContrBllgSchdSmryTMsg.baseSubTotPrcDealAmt, (BigDecimal) totalTopSchdAmtMap.get("BASE_SUB_TOT_PRC_DEAL_AMT"));
                        setValue(dsContrBllgSchdSmryTMsg.basePrcDealAdjAmt, (BigDecimal) totalTopSchdAmtMap.get("BASE_PRC_DEAL_ADJ_AMT"));
                        S21ApiTBLAccessor.update(dsContrBllgSchdSmryTMsg);
                    } else {
                        S21ApiTBLAccessor.logicalRemove(dsContrBllgSchdSmryTMsg);
                    }
                    // Mod End 2018/05/14 QC#24878
                } else {
                    S21ApiTBLAccessor.logicalRemove(dsContrPrcEffTMsg);
                    S21ApiTBLAccessor.logicalRemove(dsContrBllgSchdSmryTMsg);
                    // START 2018/09/05 [QC#24555, ADD]
                    logicalRemovePrcEff(glblCmpyCd, dsContrDtlPk, null, FLG_ON_Y, startPrcEffDt, invEndPrcEffDt);
                    // END 2018/09/05 [QC#24555, ADD]
                    // mod start 2020/06/02 QC#56945
                    //setValue(newPrcEffTMsg.dsContrPrcEffSqNum, BigDecimal.ZERO);
                    if (hasValue(dsContrPrcEffTMsg.dsContrPrcEffSqNum)) {
                        setValue(newPrcEffTMsg.dsContrPrcEffSqNum, dsContrPrcEffTMsg.dsContrPrcEffSqNum.getValue().subtract(BigDecimal.ONE));
                    } else {
                        setValue(newPrcEffTMsg.dsContrPrcEffSqNum, BigDecimal.ZERO);
                    }
                    // mod end 2020/06/02 QC#56945
                }
                //end 2017/11/07 QC#22301,mod

                setValue(newPrcEffTMsg.dsContrPrcEffPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_PRC_EFF_SQ));
                setValue(newPrcEffTMsg.dsContrPrcEffSqNum, newPrcEffTMsg.dsContrPrcEffSqNum.getValue().add(BigDecimal.ONE));
                setValue(newPrcEffTMsg.contrPrcEffFromDt, startPrcEffDt);
                setValue(newPrcEffTMsg.contrPrcEffThruDt, invEndPrcEffDt);
                // START 2018/08/27 [QC#24555, MOD]
                setValue(newPrcEffTMsg.basePrcDealAmt, newBaseUnitAmt);
                // END   2018/08/27 [QC#24555, MOD]
                newPrcEffTMsg.basePrcFuncAmt.clear();
                newPrcEffTMsg.basePrcTermDealAmtRate.clear();
                newPrcEffTMsg.basePrcTermFuncAmtRate.clear();
                if (ZYPDateUtil.compare(newPrcEffTMsg.contrPrcEffFromDt.getValue(), slsDt) <= 0 && ZYPDateUtil.compare(slsDt, newPrcEffTMsg.contrPrcEffThruDt.getValue()) <= 0) {
                    setValue(newPrcEffTMsg.dsContrPrcEffStsCd, DS_CONTR_DTL_STS.ACTIVE);
                //start 2018/01/11 QC#23420,add
                } else if (ZYPDateUtil.compare(slsDt, dsContrPrcEffTMsg.contrPrcEffFromDt.getValue()) <= 0) {
                    setValue(dsContrPrcEffTMsg.dsContrPrcEffStsCd, DS_CONTR_DTL_STS.SIGNED);
                //end 2018/01/11 QC#23420,add
                } else {
                    setValue(newPrcEffTMsg.dsContrPrcEffStsCd, DS_CONTR_DTL_STS.EXPIRED);
                }
                S21ApiTBLAccessor.create(newPrcEffTMsg);

                // add start 2020/06/09 QC#56945
                logicalRemoveDuplicatePrcEff(glblCmpyCd, dsContrDtlPk, null, "Y", startPrcEffDt, invEndPrcEffDt, newPrcEffTMsg.dsContrPrcEffPk.getValue());
                // add end 2020/06/09 QC#56945

                // START 2018/09/05 [QC#24555, ADD]
                topSchdSq = 1;
                // END   2018/09/05 [QC#24555, ADD]
                setValue(rbilDsContrBllgSchdSmryTMsg.dsContrBllgSchdSqNum, "1");
                setValue(rbilDsContrBllgSchdSmryTMsg.dsContrPrcEffPk, newPrcEffTMsg.dsContrPrcEffPk);
                setValue(rbilDsContrBllgSchdSmryTMsg.dsContrPrcEffSqNum, newPrcEffTMsg.dsContrPrcEffSqNum);
                setValue(rbilDsContrBllgSchdSmryTMsg.bllgSchdFromDt, startPrcEffDt);
                setValue(rbilDsContrBllgSchdSmryTMsg.bllgSchdThruDt, invEndPrcEffDt);
                //start 2017/11/07 QC#22301,add
                setValue(rbilDsContrBllgSchdSmryTMsg.perSchdNum, calcPerSchdNum(rbilDsContrBllgSchdSmryTMsg, dsContrDtlTMsg.contrCloDay.getValue()));
                //end 2017/11/07 QC#22301,add
                // START 2018/08/27 [QC#24555, MOD]
                setValue(rbilDsContrBllgSchdSmryTMsg.basePrcDealAmt, newBaseUnitAmt);
                // END   2018/08/27 [QC#24555, MOD]
                rbilDsContrBllgSchdSmryTMsg.basePrcFuncAmt.clear();
                rbilDsContrBllgSchdSmryTMsg.basePrcDealAdjAmt.clear();
                rbilDsContrBllgSchdSmryTMsg.basePrcFuncAdjAmt.clear();
                rbilDsContrBllgSchdSmryTMsg.baseSubTotPrcDealAmt.clear();
                rbilDsContrBllgSchdSmryTMsg.baseSubTotPrcFuncAmt.clear();
                S21ApiTBLAccessor.update(rbilDsContrBllgSchdSmryTMsg);

                dsContrPrcEffTMsg = new DS_CONTR_PRC_EFFTMsg();
                dsContrBllgSchdSmryTMsg = new DS_CONTR_BLLG_SCHD_SMRYTMsg();
                targetPrcEffPk = newPrcEffTMsg.dsContrPrcEffPk.getValue();
                targetTopSchdPk = rbilDsContrBllgSchdSmryTMsg.dsContrBllgSchdSmryPk.getValue();
                EZDMsg.copy(newPrcEffTMsg, null, dsContrPrcEffTMsg, null);
                EZDMsg.copy(rbilDsContrBllgSchdSmryTMsg, null, dsContrBllgSchdSmryTMsg, null);
            } else {
                // START 2018/09/05 [QC#24555, DEL]
                // S21ApiTBLAccessor.logicalRemove(rbilDsContrBllgSchdSmryTMsg);
                // END 2018/09/05 [QC#24555, DEL]
                // START 2018/09/05 [QC#24555, ADD]
                if (preDsContrBllgSchdSmryTMsg!= null && (FLG_ON_Y.equals(preBllgSchdPrrtFlg) || FLG_ON_Y.equals(curBllgSchdPrrtFlg))){
                    setValue(preDsContrBllgSchdSmryTMsg.bllgSchdThruDt, ZYPDateUtil.addDays(startPrcEffDt, -1));
                    S21ApiTBLAccessor.update(preDsContrBllgSchdSmryTMsg);
                    topSchdSq++;
                    setValue(rbilDsContrBllgSchdSmryTMsg.dsContrBllgSchdSqNum, Integer.toString(topSchdSq));
                    setValue(rbilDsContrBllgSchdSmryTMsg.dsContrPrcEffPk, dsContrPrcEffTMsg.dsContrPrcEffPk);
                    setValue(rbilDsContrBllgSchdSmryTMsg.dsContrPrcEffSqNum, dsContrPrcEffTMsg.dsContrPrcEffSqNum);
                    setValue(rbilDsContrBllgSchdSmryTMsg.bllgSchdFromDt, startPrcEffDt);
                    setValue(rbilDsContrBllgSchdSmryTMsg.bllgSchdThruDt, invEndPrcEffDt);
                    setValue(rbilDsContrBllgSchdSmryTMsg.basePrcDealAmt, newBaseUnitAmt);
                    rbilDsContrBllgSchdSmryTMsg.basePrcFuncAmt.clear();
                    rbilDsContrBllgSchdSmryTMsg.basePrcDealAdjAmt.clear();
                    rbilDsContrBllgSchdSmryTMsg.basePrcFuncAdjAmt.clear();
                    rbilDsContrBllgSchdSmryTMsg.baseSubTotPrcDealAmt.clear();
                    rbilDsContrBllgSchdSmryTMsg.baseSubTotPrcFuncAmt.clear();
                    S21ApiTBLAccessor.update(rbilDsContrBllgSchdSmryTMsg);
                    targetTopSchdPk = rbilDsContrBllgSchdSmryTMsg.dsContrBllgSchdSmryPk.getValue();
                    EZDMsg.copy(rbilDsContrBllgSchdSmryTMsg, null, dsContrBllgSchdSmryTMsg, null);
                } else {
                    S21ApiTBLAccessor.logicalRemove(rbilDsContrBllgSchdSmryTMsg);
                }
                // END   2018/09/05 [QC#24555, ADD]
            }
            // START 2018/09/05 [QC#24555, ADD]
            preBllgSchdPrrtFlg = curBllgSchdPrrtFlg;
            // END   2018/09/05 [QC#24555, ADD]
            targetPrcEffPk = query.getTargetPrcEffPkForBase(glblCmpyCd, dsContrDtlPk, startPrcEffDt);

            origDsContrBllgSchdPk = (BigDecimal) targetRbilSchd.get("ORIG_DS_CONTR_BLLG_SCHD_PK");
            origDsContrBllgSchdTMsg = query.getDsContrBllgSchdForUpdate(glblCmpyCd, origDsContrBllgSchdPk);

            BigDecimal dsContrPrcEffSqNum = origDsContrBllgSchdTMsg.dsContrPrcEffSqNum.getValue();
            String dsContrBllgSchdLvlNum = origDsContrBllgSchdTMsg.dsContrBllgSchdLvlNum.getValue();
            String dsContrBllgSchdSqNum = origDsContrBllgSchdTMsg.dsContrBllgSchdSqNum.getValue();
            if (isChangeTopSchdSq(dsContrBllgSchdSmryTMsg, origDsContrBllgSchdTMsg)) {
                dsContrPrcEffSqNum = dsContrBllgSchdSmryTMsg.dsContrPrcEffSqNum.getValue();
                dsContrBllgSchdLvlNum = dsContrBllgSchdSmryTMsg.dsContrBllgSchdSqNum.getValue();
                dsContrBllgSchdSqNum = query.getNextDsContrBllgSchdSq(glblCmpyCd, dsContrDtlPk, dsContrBllgSchdSmryTMsg.dsContrPrcEffPk.getValue(), dsContrBllgSchdSmryTMsg.dsContrBllgSchdSmryPk.getValue());
            }

            origDsContrBllgSchdTMsg.dsContrPrcEffPk.clear();
            origDsContrBllgSchdTMsg.dsContrPrcEffSqNum.clear();
            origDsContrBllgSchdTMsg.dsContrBllgSchdSqNum.clear();
            origDsContrBllgSchdTMsg.dsContrBllgSchdSmryPk.clear();
            origDsContrBllgSchdTMsg.dsContrBllgSchdLvlNum.clear();
            S21ApiTBLAccessor.update(origDsContrBllgSchdTMsg);

            rbilDsContrBllgSchdTMsg = query.getDsContrBllgSchdForUpdate(glblCmpyCd, rbilDsContrBllgSchdPk);
            setValue(rbilDsContrBllgSchdTMsg.dsContrPrcEffPk, dsContrBllgSchdSmryTMsg.dsContrPrcEffPk);
            setValue(rbilDsContrBllgSchdTMsg.dsContrPrcEffSqNum, dsContrPrcEffSqNum);
            setValue(rbilDsContrBllgSchdTMsg.dsContrBllgSchdSmryPk, dsContrBllgSchdSmryTMsg.dsContrBllgSchdSmryPk);
            setValue(rbilDsContrBllgSchdTMsg.dsContrBllgSchdLvlNum, dsContrBllgSchdLvlNum);
            setValue(rbilDsContrBllgSchdTMsg.dsContrBllgSchdSqNum, dsContrBllgSchdSqNum);
            setValue(rbilDsContrBllgSchdTMsg.basePrcDealAmt, dsContrBllgSchdSmryTMsg.basePrcDealAmt);
            S21ApiTBLAccessor.update(rbilDsContrBllgSchdTMsg);

            totalTopSchdAmtMap = query.getRbilTopSchdAmtForBase(glblCmpyCd, targetTopSchdPk, null, null);
            setValue(dsContrBllgSchdSmryTMsg.baseSubTotPrcDealAmt, (BigDecimal) totalTopSchdAmtMap.get("BASE_SUB_TOT_PRC_DEAL_AMT"));
            setValue(dsContrBllgSchdSmryTMsg.basePrcDealAdjAmt, (BigDecimal) totalTopSchdAmtMap.get("BASE_PRC_DEAL_ADJ_AMT"));
            // START 2018/09/05 [QC#24555, ADD]
            setValue(dsContrBllgSchdSmryTMsg.perSchdNum, (BigDecimal) totalTopSchdAmtMap.get("PER_SCHD_NUM"));
            // END   2018/09/05 [QC#24555, ADD]
            S21ApiTBLAccessor.update(dsContrBllgSchdSmryTMsg);
            // START 2018/09/05 [QC#24555, ADD]
            preDsContrBllgSchdSmryTMsg = dsContrBllgSchdSmryTMsg;
            // END   2018/09/05 [QC#24555, ADD]

            totalRbilPrcEffAmt = query.getRbilPrcEffAmtForBase(glblCmpyCd, targetPrcEffPk, null, null);
            setValue(dsContrPrcEffTMsg.basePrcTermDealAmtRate, totalRbilPrcEffAmt);
            S21ApiTBLAccessor.update(dsContrPrcEffTMsg);
        }
        // START 2018/09/05 [QC#24555, ADD]
        adjustTopSchd(glblCmpyCd, dsContrDtlPk, FLG_ON_Y, firstEffFromDt);
        // END   2018/09/05 [QC#24555, ADD]

        // START 2018/08/31 K.Kojima [QC#27960,ADD]
        replacePrcEffPkSkipMonth(glblCmpyCd, dsContrDtlPk, FLG_ON_Y);

        return invEndPrcEffDt;
        // END 2018/08/31 K.Kojima [QC#27960,ADD]
    }

    // START 2023/01/16 R.Jin [QC#58890, MOD]
    // START 2018/09/03 K.Kojima [QC#27960,MOD]
    // private static void dividePrcEffForUsage(DS_CONTR_DTLTMsg dsContrDtlTMsg, DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg, SVC_CR_REBILTMsg crRebilTMsg, String slsDt) {
//    private static String dividePrcEffForUsage(DS_CONTR_DTLTMsg dsContrDtlTMsg, DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg, SVC_CR_REBILTMsg crRebilTMsg, String slsDt) {
    private static String dividePrcEffForUsage(DS_CONTR_DTLTMsg dsContrDtlTMsg, DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg, SVC_CR_REBILTMsg crRebilTMsg, String slsDt, String dsContrCatgCd) {
    // END 2018/09/03 K.Kojima [QC#27960,MOD]
    // END 2023/01/16 R.Jin [QC#58890, MOD]
        if (dsContrDtlTMsg == null || dsContrBllgMtrTMsg == null || crRebilTMsg == null) {
            // START 2018/09/03 K.Kojima [QC#27960,MOD]
            // return;
            return null;
            // END 2018/09/03 K.Kojima [QC#27960,MOD]
        }
        String glblCmpyCd = dsContrDtlTMsg.glblCmpyCd.getValue();
        BigDecimal dsContrDtlPk = dsContrDtlTMsg.dsContrDtlPk.getValue();
        BigDecimal dsContrBllgMtrPk = dsContrBllgMtrTMsg.dsContrBllgMtrPk.getValue();
        BigDecimal svcCrRebilPk = crRebilTMsg.svcCrRebilPk.getValue();
        List<Map<String, Object>> targetRbilSchdList = query.getTargetRbilSchdForUsage(glblCmpyCd, dsContrDtlPk, dsContrBllgMtrPk, svcCrRebilPk);
        if (targetRbilSchdList.size() == 0) {
            // START 2018/09/03 K.Kojima [QC#27960,MOD]
            // return;
            return null;
            // END 2018/09/03 K.Kojima [QC#27960,MOD]
        }
        // START 2018/08/27 [QC#24555,MOD]
//        String invEndPrcEffDt = (String) targetRbilSchdList.get(targetRbilSchdList.size() - 1).get("RBIL_BLLG_SCHD_THRU_DT");
        String invEndPrcEffDt = query.getInvSchdMaxThruDt(glblCmpyCd, dsContrDtlPk, dsContrBllgMtrPk, FLG_OFF_N);;
        // END   2018/08/27 [QC#24555,MOD]
        String startPrcEffDt;
        BigDecimal targetPrcEffPk;
        DS_CONTR_PRC_EFFTMsg dsContrPrcEffTMsg;
        
        // START 2018/08/27 [QC#24555,MOD]
        // START 2018/04/20 K.Kojima [QC#25595,MOD]
         DS_CONTR_PRC_EFF_MTRTMsgArray peMtrArray;
//        DS_CONTR_BLLG_SCHD_MTRTMsgArray schdMtrArray;
        // END 2018/04/20 K.Kojima [QC#25595,MOD]
        // END   2018/08/27 [QC#24555,MOD]
        BigDecimal targetTopSchdPk;
        DS_CONTR_BLLG_SCHD_SMRYTMsg dsContrBllgSchdSmryTMsg;
        DS_CONTR_BLLG_SCHD_SMRYTMsg rbilDsContrBllgSchdSmryTMsg;
        // START 2018/08/27 [QC#24555, MOD]
        SVC_CR_REBIL_XS_MTR_BLLGTMsgArray rbilMtrArray;
        // END   2018/08/27 [QC#24555, MOD]
        DS_CONTR_PRC_EFFTMsg newPrcEffTMsg;
        DS_CONTR_PRC_EFF_MTRTMsg newPrcEffMtrTMsg;
        String endDt;
        BigDecimal origDsContrBllgSchdPk;
        BigDecimal rbilDsContrBllgSchdPk;
        DS_CONTR_BLLG_SCHDTMsg origDsContrBllgSchdTMsg;
        DS_CONTR_BLLG_SCHDTMsg rbilDsContrBllgSchdTMsg;
        // START 2018/09/05 [QC#24555, ADD]
        String  preBllgSchdPrrtFlg = FLG_OFF_N;
        String  curBllgSchdPrrtFlg = FLG_OFF_N;
        int topSchdSq   = 1;
        DS_CONTR_BLLG_SCHD_SMRYTMsg preDsContrBllgSchdSmryTMsg = null;
        String firstEffFromDt = null;
        // START 2023/01/16 R.Jin [QC#58890, MOD]
        int aggMachinePEIndex = 0;
        // END   2018/09/05 [QC#24555, ADD]
        for (Map<String, Object> targetRbilSchd : targetRbilSchdList) {
            aggMachinePEIndex++;
         // END 2023/01/16 R.Jin [QC#58890, MOD]
            startPrcEffDt = (String) targetRbilSchd.get("RBIL_BLLG_SCHD_FROM_DT");
            // START 2018/09/05 [QC#24555, ADD]
            curBllgSchdPrrtFlg = (String) targetRbilSchd.get("BLLG_SCHD_PRRT_FLG");
            // END   2018/09/05 [QC#24555, ADD]
            targetPrcEffPk = query.getTargetPrcEffPkForUsage(glblCmpyCd, dsContrDtlPk, dsContrBllgMtrPk, startPrcEffDt);
            dsContrPrcEffTMsg = query.getDsContrPrcEffTMsgForUpdate(glblCmpyCd, targetPrcEffPk);
            if (dsContrPrcEffTMsg == null) {
                continue;
            }

            // START 2018/08/27 [QC#24555,MOD]
            // START 2018/04/20 K.Kojima [QC#25595,DEL]
             peMtrArray = query.getPrcEffTMsgArray(glblCmpyCd, dsContrPrcEffTMsg.dsContrPrcEffPk.getValue(), dsContrBllgMtrPk);
             if (peMtrArray.getValidCount() == 0) {
                 continue;
             }
            // END 2018/04/20 K.Kojima [QC#25595,DEL]

            targetTopSchdPk = query.getTargetTopSchdPkForUsage(glblCmpyCd, dsContrDtlPk, dsContrBllgMtrPk, dsContrPrcEffTMsg.dsContrPrcEffPk.getValue(), dsContrPrcEffTMsg.dsContrPrcEffSqNum.getValue(), startPrcEffDt);
            dsContrBllgSchdSmryTMsg = query.getDsContrBllgSchdSmryForUpdate(glblCmpyCd, targetTopSchdPk);
            if (dsContrBllgSchdSmryTMsg == null) {
                continue;
            }
//            // START 2018/04/20 K.Kojima [QC#25595,ADD]
//            schdMtrArray = query.getDsContrBllgSchdMtrTMsgArray(glblCmpyCd, targetTopSchdPk, dsContrBllgMtrPk);
//            if (schdMtrArray.getValidCount() == 0) {
//                continue;
//            }
//            // END 2018/04/20 K.Kojima [QC#25595,ADD]

            rbilDsContrBllgSchdSmryTMsg = query.getDsContrBllgSchdSmryForUpdate(glblCmpyCd, (BigDecimal) targetRbilSchd.get("RBIL_CONTR_BLLG_SCHD_SMRY_PK"));
            if (rbilDsContrBllgSchdSmryTMsg == null) {
                continue;
            }
            BigDecimal svcCrRebilMtrBllgPk = (BigDecimal) targetRbilSchd.get("SVC_CR_REBIL_MTR_BLLG_PK");
            rbilMtrArray = query.getBllgSchdMtrTMsgArray(glblCmpyCd, svcCrRebilMtrBllgPk);
            // END   2018/08/27 [QC#24555,MOD]

            // START 2018/09/05 [QC#24555, ADD]
            if (firstEffFromDt == null) {
                firstEffFromDt = startPrcEffDt;
            }
            // END   2018/09/05 [QC#24555, ADD]

            // START 2023/01/16 R.Jin [QC#58890, MOD]
            // START 2018/04/20 K.Kojima [QC#25595,MOD]
            // if (isChangeXsMtr(peMtrArray, rbilMtrArray)) {
            // START  2018/08/27 [QC#24555,MOD]
//            if (isChangeXsMtr(peMtrArray, rbilMtrArray,dsContrCatgCd)) {
            if (isChangeXsMtr(peMtrArray, rbilMtrArray,dsContrCatgCd,aggMachinePEIndex)) {
                // END   2018/08/27 [QC#24555,MOD]
            // END 2018/04/20 K.Kojima [QC#25595,MOD]
            // END 2023/01/16 R.Jin [QC#58890, MOD]
                newPrcEffTMsg = new DS_CONTR_PRC_EFFTMsg();
                //start 2017/11/07 QC#22301,mod
                EZDMsg.copy(dsContrPrcEffTMsg, null, newPrcEffTMsg, null);
                if (startPrcEffDt.compareTo(dsContrPrcEffTMsg.contrPrcEffFromDt.getValue()) != 0) {
                    endDt = ZYPDateUtil.addDays(startPrcEffDt, -1);
                    setValue(dsContrPrcEffTMsg.contrPrcEffThruDt, endDt);
                    if (ZYPDateUtil.compare(dsContrPrcEffTMsg.contrPrcEffFromDt.getValue(), slsDt) <= 0 && ZYPDateUtil.compare(slsDt, dsContrPrcEffTMsg.contrPrcEffThruDt.getValue()) <= 0) {
                        setValue(dsContrPrcEffTMsg.dsContrPrcEffStsCd, DS_CONTR_DTL_STS.ACTIVE);
                    } else {
                        setValue(dsContrPrcEffTMsg.dsContrPrcEffStsCd, DS_CONTR_DTL_STS.EXPIRED);
                    }
                    S21ApiTBLAccessor.update(dsContrPrcEffTMsg);

                    // Mod Start 2018/05/14 QC#24878
                    if (ZYPDateUtil.compare(dsContrBllgSchdSmryTMsg.bllgSchdFromDt.getValue(), endDt) <= 0) {
                        setValue(dsContrBllgSchdSmryTMsg.bllgSchdThruDt, endDt);
                        setValue(dsContrBllgSchdSmryTMsg.perSchdNum, calcPerSchdNum(dsContrBllgSchdSmryTMsg, dsContrDtlTMsg.contrCloDay.getValue()));
                        S21ApiTBLAccessor.update(dsContrBllgSchdSmryTMsg);
                    } else {
                        S21ApiTBLAccessor.logicalRemove(dsContrBllgSchdSmryTMsg);
                    }
                    // Mod End 2018/05/14 QC#24878
                } else {
                    S21ApiTBLAccessor.logicalRemove(dsContrPrcEffTMsg);
                    DS_CONTR_PRC_EFF_MTRTMsgArray prcEffMtrTMsgArray = query.getPrcEffTMsgArray(glblCmpyCd, dsContrPrcEffTMsg.dsContrPrcEffPk.getValue(), dsContrPrcEffTMsg.dsContrBllgMtrPk.getValue());
                    for (int peMtrCnt = 0; peMtrCnt < prcEffMtrTMsgArray.getValidCount(); peMtrCnt++) {
                        S21ApiTBLAccessor.logicalRemove(prcEffMtrTMsgArray.no(peMtrCnt));
                    }
                    S21ApiTBLAccessor.logicalRemove(dsContrBllgSchdSmryTMsg);
                    // START 2018/09/05 [QC#24555, ADD]
                    logicalRemovePrcEff(glblCmpyCd, dsContrDtlPk, dsContrPrcEffTMsg.dsContrBllgMtrPk.getValue(), FLG_OFF_N, startPrcEffDt, invEndPrcEffDt);
                    // END 2018/09/05 [QC#24555, ADD]
                    // mod start 2020/06/02 QC#56945
                    if (hasValue(dsContrPrcEffTMsg.dsContrPrcEffSqNum)) {
                        setValue(newPrcEffTMsg.dsContrPrcEffSqNum, dsContrPrcEffTMsg.dsContrPrcEffSqNum.getValue().subtract(BigDecimal.ONE));
                    } else {
                        setValue(newPrcEffTMsg.dsContrPrcEffSqNum, BigDecimal.ZERO);
                    }
                    // mod end 2020/06/02 QC#56945
                    
                }
                //end 2017/11/07 QC#22301,mod

                setValue(newPrcEffTMsg.dsContrPrcEffPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_PRC_EFF_SQ));
                setValue(newPrcEffTMsg.dsContrPrcEffSqNum, newPrcEffTMsg.dsContrPrcEffSqNum.getValue().add(BigDecimal.ONE));
                setValue(newPrcEffTMsg.contrPrcEffFromDt, startPrcEffDt);
                setValue(newPrcEffTMsg.contrPrcEffThruDt, invEndPrcEffDt);
                if (ZYPDateUtil.compare(newPrcEffTMsg.contrPrcEffFromDt.getValue(), slsDt) <= 0 && ZYPDateUtil.compare(slsDt, newPrcEffTMsg.contrPrcEffThruDt.getValue()) <= 0) {
                    setValue(newPrcEffTMsg.dsContrPrcEffStsCd, DS_CONTR_DTL_STS.ACTIVE);
                } else {
                    setValue(newPrcEffTMsg.dsContrPrcEffStsCd, DS_CONTR_DTL_STS.EXPIRED);
                }
                S21ApiTBLAccessor.create(newPrcEffTMsg);

                // add start 2020/06/09 QC#56945
                logicalRemoveDuplicatePrcEff(glblCmpyCd, dsContrDtlPk, dsContrBllgMtrPk, "N", startPrcEffDt, invEndPrcEffDt, newPrcEffTMsg.dsContrPrcEffPk.getValue());
                // add end 2020/06/09 QC#56945

                for (int i = 0; i < rbilMtrArray.getValidCount(); i++) {
                    newPrcEffMtrTMsg = new DS_CONTR_PRC_EFF_MTRTMsg();
                    setValue(newPrcEffMtrTMsg.glblCmpyCd, newPrcEffTMsg.glblCmpyCd);
                    setValue(newPrcEffMtrTMsg.dsContrPrcEffMtrPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_PRC_EFF_MTR_SQ));
                    setValue(newPrcEffMtrTMsg.dsContrPrcEffPk, newPrcEffTMsg.dsContrPrcEffPk);
                    // START 2020/03/18 K.Kitachi [QC#55693, DEL]
//                    setValue(newPrcEffMtrTMsg.contrXsCopyPk, rbilMtrArray.no(i).contrXsCopyPk);
                    // END 2020/03/18 K.Kitachi [QC#55693, DEL]
                    setValue(newPrcEffMtrTMsg.dsContrBllgMtrPk, newPrcEffTMsg.dsContrBllgMtrPk);
                    // START 2018/08/27 [QC#24555, MOD]
                    BigDecimal newUnitXsCopyQty = getNewVal(rbilMtrArray.no(i).newUnitXsCopyQty.getValue(), rbilMtrArray.no(i).oldUnitXsCopyQty.getValue());
                    setValue(newPrcEffMtrTMsg.xsMtrCopyQty, newUnitXsCopyQty);
                    BigDecimal newXsMtrAmtRate = getNewVal(rbilMtrArray.no(i).newXsMtrAmtRate.getValue(), rbilMtrArray.no(i).oldXsMtrAmtRate.getValue());
                    setValue(newPrcEffMtrTMsg.xsMtrAmtRate, newXsMtrAmtRate);
                    String xsMtrFirstFlg = FLG_OFF_N;
                    if (i == 0) {
                        xsMtrFirstFlg = FLG_ON_Y;
                    } 
                    setValue(newPrcEffMtrTMsg.xsMtrFirstFlg, xsMtrFirstFlg);
                    // END   2018/08/27 [QC#24555, MOD]
                    S21ApiTBLAccessor.create(newPrcEffMtrTMsg);
                }
                //start 2017/11/07 QC#22301,mod
                setValue(rbilDsContrBllgSchdSmryTMsg.dsContrBllgSchdSqNum, "1");
                //end 2017/11/07 QC#22301,mod
                // START 2018/09/05 [QC#24555, ADD]
                topSchdSq = 1;
                // END   2018/09/05 [QC#24555, ADD]
                setValue(rbilDsContrBllgSchdSmryTMsg.dsContrPrcEffPk, newPrcEffTMsg.dsContrPrcEffPk);
                setValue(rbilDsContrBllgSchdSmryTMsg.dsContrPrcEffSqNum, newPrcEffTMsg.dsContrPrcEffSqNum);
                setValue(rbilDsContrBllgSchdSmryTMsg.bllgSchdFromDt, startPrcEffDt);
                setValue(rbilDsContrBllgSchdSmryTMsg.bllgSchdThruDt, invEndPrcEffDt);
                //start 2017/11/07 QC#22301,add
                setValue(rbilDsContrBllgSchdSmryTMsg.perSchdNum, calcPerSchdNum(rbilDsContrBllgSchdSmryTMsg, dsContrDtlTMsg.contrCloDay.getValue()));
                //end 2017/11/07 QC#22301,add
                S21ApiTBLAccessor.update(rbilDsContrBllgSchdSmryTMsg);

                dsContrPrcEffTMsg = new DS_CONTR_PRC_EFFTMsg();
                dsContrBllgSchdSmryTMsg = new DS_CONTR_BLLG_SCHD_SMRYTMsg();
                targetPrcEffPk = newPrcEffTMsg.dsContrPrcEffPk.getValue();
                targetTopSchdPk = rbilDsContrBllgSchdSmryTMsg.dsContrBllgSchdSmryPk.getValue();
                EZDMsg.copy(newPrcEffTMsg, null, dsContrPrcEffTMsg, null);
                EZDMsg.copy(rbilDsContrBllgSchdSmryTMsg, null, dsContrBllgSchdSmryTMsg, null);
            } else {
                // START 2018/09/05 [QC#24555, DEL]
                // S21ApiTBLAccessor.logicalRemove(rbilDsContrBllgSchdSmryTMsg);
                // END 2018/09/05 [QC#24555, DEL]
                // START 2018/09/05 [QC#24555, ADD]
                if (preDsContrBllgSchdSmryTMsg!= null && (FLG_ON_Y.equals(preBllgSchdPrrtFlg) || FLG_ON_Y.equals(curBllgSchdPrrtFlg))){
                    setValue(preDsContrBllgSchdSmryTMsg.bllgSchdThruDt, ZYPDateUtil.addDays(startPrcEffDt, -1));
                    S21ApiTBLAccessor.update(preDsContrBllgSchdSmryTMsg);
                    topSchdSq++;
                    setValue(rbilDsContrBllgSchdSmryTMsg.dsContrBllgSchdSqNum, Integer.toString(topSchdSq));
                    setValue(rbilDsContrBllgSchdSmryTMsg.dsContrPrcEffPk, dsContrPrcEffTMsg.dsContrPrcEffPk);
                    setValue(rbilDsContrBllgSchdSmryTMsg.dsContrPrcEffSqNum, dsContrPrcEffTMsg.dsContrPrcEffSqNum);
                    setValue(rbilDsContrBllgSchdSmryTMsg.bllgSchdFromDt, startPrcEffDt);
                    setValue(rbilDsContrBllgSchdSmryTMsg.bllgSchdThruDt, invEndPrcEffDt);
                    S21ApiTBLAccessor.update(rbilDsContrBllgSchdSmryTMsg);
                    targetTopSchdPk = rbilDsContrBllgSchdSmryTMsg.dsContrBllgSchdSmryPk.getValue();
                    EZDMsg.copy(rbilDsContrBllgSchdSmryTMsg, null, dsContrBllgSchdSmryTMsg, null);
                } else {
                    S21ApiTBLAccessor.logicalRemove(rbilDsContrBllgSchdSmryTMsg);
                }
                // END   2018/09/05 [QC#24555, ADD]
                // START 2018/09/03 K.Kojima [QC#27960,DEL]
                // for (int i = 0; i < rbilMtrArray.getValidCount(); i++) {
                //     S21ApiTBLAccessor.logicalRemove(rbilMtrArray.no(i));
                // }
                // END 2018/09/03 K.Kojima [QC#27960,DEL]
            }
            // START 2018/09/05 [QC#24555, ADD]
            preBllgSchdPrrtFlg = curBllgSchdPrrtFlg;
            // END   2018/09/05 [QC#24555, ADD]
            //start 2017/11/07 QC#22301,mod
            origDsContrBllgSchdPk = (BigDecimal) targetRbilSchd.get("ORIG_DS_CONTR_BLLG_SCHD_PK");
            origDsContrBllgSchdTMsg = query.getDsContrBllgSchdForUpdate(glblCmpyCd, origDsContrBllgSchdPk);
            BigDecimal dsContrPrcEffSqNum = origDsContrBllgSchdTMsg.dsContrPrcEffSqNum.getValue();
            String dsContrBllgSchdLvlNum = origDsContrBllgSchdTMsg.dsContrBllgSchdLvlNum.getValue();
            String dsContrBllgSchdSqNum = origDsContrBllgSchdTMsg.dsContrBllgSchdSqNum.getValue();
            if (isChangeTopSchdSq(dsContrBllgSchdSmryTMsg, origDsContrBllgSchdTMsg)) {
                dsContrPrcEffSqNum = dsContrBllgSchdSmryTMsg.dsContrPrcEffSqNum.getValue();
                dsContrBllgSchdLvlNum = dsContrBllgSchdSmryTMsg.dsContrBllgSchdSqNum.getValue();
                dsContrBllgSchdSqNum = query.getNextDsContrBllgSchdSq(glblCmpyCd, dsContrDtlPk, dsContrBllgSchdSmryTMsg.dsContrPrcEffPk.getValue(), dsContrBllgSchdSmryTMsg.dsContrBllgSchdSmryPk.getValue());
            }

            origDsContrBllgSchdTMsg.dsContrPrcEffPk.clear();
            origDsContrBllgSchdTMsg.dsContrPrcEffSqNum.clear();
            origDsContrBllgSchdTMsg.dsContrBllgSchdSqNum.clear();
            origDsContrBllgSchdTMsg.dsContrBllgSchdSmryPk.clear();
            origDsContrBllgSchdTMsg.dsContrBllgSchdLvlNum.clear();
            S21ApiTBLAccessor.update(origDsContrBllgSchdTMsg);

            rbilDsContrBllgSchdPk = (BigDecimal) targetRbilSchd.get("RBIL_DS_CONTR_BLLG_SCHD_PK");
            rbilDsContrBllgSchdTMsg = query.getDsContrBllgSchdForUpdate(glblCmpyCd, rbilDsContrBllgSchdPk);
            setValue(rbilDsContrBllgSchdTMsg.dsContrPrcEffPk, dsContrBllgSchdSmryTMsg.dsContrPrcEffPk);
            setValue(rbilDsContrBllgSchdTMsg.dsContrPrcEffSqNum, dsContrPrcEffSqNum);
            setValue(rbilDsContrBllgSchdTMsg.dsContrBllgSchdSmryPk, dsContrBllgSchdSmryTMsg.dsContrBllgSchdSmryPk);
            setValue(rbilDsContrBllgSchdTMsg.dsContrBllgSchdLvlNum, dsContrBllgSchdLvlNum);
            setValue(rbilDsContrBllgSchdTMsg.dsContrBllgSchdSqNum, dsContrBllgSchdSqNum);
            //end 2017/11/07 QC#22301,mod
            S21ApiTBLAccessor.update(rbilDsContrBllgSchdTMsg);

            // START 2018/09/05 [QC#24555, ADD]
            preDsContrBllgSchdSmryTMsg = dsContrBllgSchdSmryTMsg;
            // END   2018/09/05 [QC#24555, ADD]
        }

        // START 2018/09/05 [QC#24555, ADD]
        adjustTopSchd(glblCmpyCd, dsContrDtlPk, FLG_OFF_N, firstEffFromDt);
        // END   2018/09/05 [QC#24555, ADD]

        // START 2018/08/31 K.Kojima [QC#27960,ADD]
        replacePrcEffPkSkipMonth(glblCmpyCd, dsContrDtlPk, FLG_OFF_N);

        return invEndPrcEffDt;
        // END 2018/08/31 K.Kojima [QC#27960,ADD]
    }

    // START 2018/04/20 K.Kojima [QC#25595,DEL]
    // private static boolean isChangeXsMtr(DS_CONTR_PRC_EFF_MTRTMsgArray peMtrArray, DS_CONTR_BLLG_SCHD_MTRTMsgArray rbilMtrArray) {
    //     if (peMtrArray.getValidCount() != rbilMtrArray.getValidCount()) {
    //         return true;
    //     }
    // 
    //     for (int i = 0; i < peMtrArray.getValidCount(); i++) {
    //         if (peMtrArray.no(i).xsMtrCopyQty.getValue().compareTo(rbilMtrArray.no(i).xsMtrCopyQty.getValue()) != 0) {
    //             return true;
    //         }
    // 
    //         if (peMtrArray.no(i).xsMtrAmtRate.getValue().compareTo(rbilMtrArray.no(i).xsMtrAmtRate.getValue()) != 0) {
    //             return true;
    //         }
    //     }
    //     return false;
    // }
    // END 2018/04/20 K.Kojima [QC#25595,DEL]

    // START 2018/08/27 [QC#24555, DEL]
    // START 2018/04/20 K.Kojima [QC#25595,ADD]
//    private static boolean isChangeXsMtr(DS_CONTR_BLLG_SCHD_MTRTMsgArray schdMtrArray, DS_CONTR_BLLG_SCHD_MTRTMsgArray rbilMtrArray) {
//        if (schdMtrArray.getValidCount() != rbilMtrArray.getValidCount()) {
//            return true;
//        }
//
//        for (int i = 0; i < schdMtrArray.getValidCount(); i++) {
//            if (schdMtrArray.no(i).xsMtrCopyQty.getValue().compareTo(rbilMtrArray.no(i).xsMtrCopyQty.getValue()) != 0) {
//                return true;
//            }
//
//            if (schdMtrArray.no(i).xsMtrAmtRate.getValue().compareTo(rbilMtrArray.no(i).xsMtrAmtRate.getValue()) != 0) {
//                return true;
//            }
//        }
//        return false;
//    }
    // END 2018/04/20 K.Kojima [QC#25595,ADD]
    // END   2018/08/27 [QC#24555, DEL]
    // SATRT 2018/08/27 [QC#24555, ADD]
    // START 2023/01/16 R.Jin [QC#58890, MOD]
//     private static boolean isChangeXsMtr(DS_CONTR_PRC_EFF_MTRTMsgArray peMtrArray, SVC_CR_REBIL_XS_MTR_BLLGTMsgArray rbilMtrArray, String dsContrCatgCd) {
     private static boolean isChangeXsMtr(DS_CONTR_PRC_EFF_MTRTMsgArray peMtrArray, SVC_CR_REBIL_XS_MTR_BLLGTMsgArray rbilMtrArray, String dsContrCatgCd, int aggMachinePEIndex) {
    // END 2023/01/16 R.Jin [QC#58890, MOD]
         if (peMtrArray.getValidCount() != rbilMtrArray.getValidCount()) {
             return true;
         }
     
         for (int i = 0; i < peMtrArray.getValidCount(); i++) {
             BigDecimal newUnitXsCopyQty = getNewVal(rbilMtrArray.no(i).newUnitXsCopyQty.getValue(), rbilMtrArray.no(i).oldUnitXsCopyQty.getValue());
             if (peMtrArray.no(i).xsMtrCopyQty.getValue().compareTo(newUnitXsCopyQty) != 0) {
                 return true;
             }
             
             // START 2023/01/16 R.Jin [QC#58890, ADD]
             if(DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd) && hasValue(rbilMtrArray.no(i).newXsCopyQty) && rbilMtrArray.no(i).oldXsCopyQty.getValue().compareTo(rbilMtrArray.no(i).newXsCopyQty.getValue()) == 0 && aggMachinePEIndex == 1 ) {
                 return true;
              }
             // END 2023/01/16 R.Jin [QC#58890, ADD]
     
             BigDecimal newXsMtrAmtRate = getNewVal(rbilMtrArray.no(i).newXsMtrAmtRate.getValue(), rbilMtrArray.no(i).oldXsMtrAmtRate.getValue());
             if (peMtrArray.no(i).xsMtrAmtRate.getValue().compareTo(newXsMtrAmtRate) != 0) {
                 return true;
             }
         }

         return false;
     }

     private static BigDecimal getNewVal(BigDecimal newVal, BigDecimal oldVal) {
         BigDecimal rtnVal = newVal;
         if (!hasValue(rtnVal)) {
             rtnVal = oldVal;
         }
         return rtnVal;
     }
    // END   2018/08/27 [QC#24555, ADD]

    private static boolean isChangeTopSchdSq(DS_CONTR_BLLG_SCHD_SMRYTMsg dsContrBllgSchdSmryTMsg, DS_CONTR_BLLG_SCHDTMsg dsContrBllgSchdTMsg) {
        if (!equals(dsContrBllgSchdSmryTMsg.dsContrPrcEffSqNum.getValue(), dsContrBllgSchdTMsg.dsContrPrcEffSqNum.getValue())) {
            return true;
        } else if (!equals(dsContrBllgSchdSmryTMsg.dsContrBllgSchdSqNum.getValue(), dsContrBllgSchdTMsg.dsContrBllgSchdLvlNum.getValue())) {
            return true;
        }
        return false;
    }
    // Add End 2017/10/19 QC#21815
    // START 2017/10/17 K.Kitachi [QC#21792, ADD]
    public static void recreateXsCopy(S21ApiMessageMap msgMap, BigDecimal svcCrRebilPk) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String slsDt = pMsg.slsDt.getValue();
        List<Map<String, Object>> trgtPrcEffForUsgList = query.getTrgtPrcEffForUsgList(glblCmpyCd, slsDt, svcCrRebilPk);

        List<CONTR_XS_COPYTMsg> delXsCopyList = new ArrayList<CONTR_XS_COPYTMsg>();
        List<CONTR_XS_COPYTMsg> insXsCopyList = new ArrayList<CONTR_XS_COPYTMsg>();
        List<CONTR_XS_COPYTMsg> updXsCopyList = new ArrayList<CONTR_XS_COPYTMsg>();
        List<DS_CONTR_PRC_EFF_MTRTMsg> updDsContrPrcEffMtrList = new ArrayList<DS_CONTR_PRC_EFF_MTRTMsg>();
        setDelXsCopyList(glblCmpyCd, trgtPrcEffForUsgList, delXsCopyList);
        setInsAndUpdXsCopyList(glblCmpyCd, trgtPrcEffForUsgList, insXsCopyList, updXsCopyList, updDsContrPrcEffMtrList);
        if (delXsCopyList.size() > 0) {
            CONTR_XS_COPYTMsg[] inMsgArray = new CONTR_XS_COPYTMsg[delXsCopyList.size()];
            int retCount = S21FastTBLAccessor.removePhysical(delXsCopyList.toArray(inMsgArray));
            if (retCount != inMsgArray.length) {
                msgMap.addXxMsgId(NSZM1310E);
                return;
            }
        }
        if (insXsCopyList.size() > 0) {
            CONTR_XS_COPYTMsg[] inMsgArray = new CONTR_XS_COPYTMsg[insXsCopyList.size()];
            int retCount = S21FastTBLAccessor.insert(insXsCopyList.toArray(inMsgArray));
            if (retCount != inMsgArray.length) {
                msgMap.addXxMsgId(NSZM1308E);
                return;
            }
        }
        if (updXsCopyList.size() > 0) {
            CONTR_XS_COPYTMsg[] inMsgArray = new CONTR_XS_COPYTMsg[updXsCopyList.size()];
            int retCount = S21FastTBLAccessor.update(updXsCopyList.toArray(inMsgArray));
            if (retCount != inMsgArray.length) {
                msgMap.addXxMsgId(NSZM1307E);
                return;
            }
        }
        if (updDsContrPrcEffMtrList.size() > 0) {
            DS_CONTR_PRC_EFF_MTRTMsg[] inMsgArray = new DS_CONTR_PRC_EFF_MTRTMsg[updDsContrPrcEffMtrList.size()];
            int retCount = S21FastTBLAccessor.update(updDsContrPrcEffMtrList.toArray(inMsgArray));
            if (retCount != inMsgArray.length) {
                msgMap.addXxMsgId(NSZM1311E);
                return;
            }
        }
    }

    private static void setDelXsCopyList(String glblCmpyCd, List<Map<String, Object>> trgtPrcEffForUsgList, List<CONTR_XS_COPYTMsg> delXsCopyList) {

        CONTR_XS_COPYTMsg delXsCopyTMsg;

        for (Map<String, Object> trgtPrcEffForUsg : trgtPrcEffForUsgList) {

            BigDecimal dsContrBllgMtrPk = (BigDecimal) trgtPrcEffForUsg.get("DS_CONTR_BLLG_MTR_PK");
            BigDecimal dsContrPrcEffPk = (BigDecimal) trgtPrcEffForUsg.get("DS_CONTR_PRC_EFF_PK");

            DS_CONTR_PRC_EFF_MTRTMsgArray prcEffMtrAry = query.getPrcEffTMsgArray(glblCmpyCd, dsContrPrcEffPk, dsContrBllgMtrPk);
            CONTR_XS_COPYTMsgArray xsCopyAry = query.getXsCopy(glblCmpyCd, dsContrBllgMtrPk);

            for (int i = 0; i < xsCopyAry.getValidCount(); i++) {
                boolean isExist = false;
                for (int j = 0; j < prcEffMtrAry.getValidCount(); j++) {

                    if (!hasValue(prcEffMtrAry.no(j).contrXsCopyPk)) {
                        continue;
                    }
                    if (xsCopyAry.no(i).contrXsCopyPk.getValue().compareTo(prcEffMtrAry.no(j).contrXsCopyPk.getValue()) == 0) {
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) {
                    delXsCopyTMsg = new CONTR_XS_COPYTMsg();
                    EZDMsg.copy(xsCopyAry.no(i), null, delXsCopyTMsg, null);
                    delXsCopyList.add(delXsCopyTMsg);
                }
            }
        }
    }

    private static void setInsAndUpdXsCopyList(String glblCmpyCd, List<Map<String, Object>> trgtPrcEffForUsgList, List<CONTR_XS_COPYTMsg> insXsCopyList, List<CONTR_XS_COPYTMsg> updXsCopyList, List<DS_CONTR_PRC_EFF_MTRTMsg> updDsContrPrcEffMtrList) {

        CONTR_XS_COPYTMsg xsCopyTMsg;

        for (Map<String, Object> trgtPrcEffForUsg : trgtPrcEffForUsgList) {

            BigDecimal dsContrBllgMtrPk = (BigDecimal) trgtPrcEffForUsg.get("DS_CONTR_BLLG_MTR_PK");
            BigDecimal dsContrPrcEffPk = (BigDecimal) trgtPrcEffForUsg.get("DS_CONTR_PRC_EFF_PK");

            DS_CONTR_PRC_EFF_MTRTMsgArray prcEffMtrAry = query.getPrcEffTMsgArray(glblCmpyCd, dsContrPrcEffPk, dsContrBllgMtrPk);
            CONTR_XS_COPYTMsgArray xsCopyAry = query.getXsCopy(glblCmpyCd, dsContrBllgMtrPk);

            for (int i = 0; i < prcEffMtrAry.getValidCount(); i++) {
                boolean isExist = false;
                DS_CONTR_PRC_EFF_MTRTMsg prcEffMtr = prcEffMtrAry.no(i);

                for (int j = 0; j < xsCopyAry.getValidCount(); j++) {
                    CONTR_XS_COPYTMsg xsCopy = xsCopyAry.no(j);

                    if (!hasValue(prcEffMtr.contrXsCopyPk)) {
                        continue;
                    }

                    if (xsCopy.contrXsCopyPk.getValue().compareTo(prcEffMtr.contrXsCopyPk.getValue()) == 0) {

                        if (xsCopy.xsMtrCopyQty.getValue().compareTo(prcEffMtr.xsMtrCopyQty.getValue()) != 0 || xsCopy.xsMtrAmtRate.getValue().compareTo(prcEffMtr.xsMtrAmtRate.getValue()) != 0) {

                            xsCopyTMsg = new CONTR_XS_COPYTMsg();
                            setValue(xsCopyTMsg.glblCmpyCd, prcEffMtr.glblCmpyCd);
                            setValue(xsCopyTMsg.contrXsCopyPk, prcEffMtr.contrXsCopyPk);
                            xsCopyTMsg = (CONTR_XS_COPYTMsg) S21FastTBLAccessor.findByKeyForUpdate(xsCopyTMsg);

                            setValue(xsCopyTMsg.xsMtrCopyQty, prcEffMtr.xsMtrCopyQty);
                            setValue(xsCopyTMsg.xsMtrAmtRate, prcEffMtr.xsMtrAmtRate);
                            updXsCopyList.add(xsCopyTMsg);
                        }
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) {
                    xsCopyTMsg = new CONTR_XS_COPYTMsg();
                    EZDMsg.copy(prcEffMtr, null, xsCopyTMsg, null);
                    if (!hasValue(xsCopyTMsg.contrXsCopyPk)) {
                        setValue(xsCopyTMsg.contrXsCopyPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CONTR_XS_COPY_SQ));

                        setValue(prcEffMtr.contrXsCopyPk, xsCopyTMsg.contrXsCopyPk);
                        updDsContrPrcEffMtrList.add(prcEffMtr);
                    }
                    insXsCopyList.add(xsCopyTMsg);
                }
            }
        }
    }
    // END 2017/10/17 K.Kitachi [QC#21792, ADD]
    //start 2017/11/07 QC#22301,add
    private static BigDecimal calcPerSchdNum(DS_CONTR_BLLG_SCHD_SMRYTMsg tMsg, String contrCloDay) {
        CalcSchdSmryTermAndAmtBean inBean = new CalcSchdSmryTermAndAmtBean();
        inBean.setGlblCmpyCd(tMsg.glblCmpyCd.getValue());
        inBean.setBllgSchdFromDt(tMsg.bllgSchdFromDt.getValue());
        inBean.setBllgSchdThruDt(tMsg.bllgSchdThruDt.getValue());
        inBean.setBllgCycleCd(tMsg.perBllgCycleCd.getValue());
        inBean.setContrCloDay(contrCloDay);
        inBean.setBasePrcDealAmt(BigDecimal.ZERO);
        inBean.setBasePrcTermDealAmtRate(BigDecimal.ZERO);
        inBean.setBaseChrgFlg(ZYPConstant.FLG_ON_Y);
        inBean.setUsgChrgFlg(ZYPConstant.FLG_OFF_N);
        inBean.setCcyCd(tMsg.ccyCd.getValue());
        CalcSchdSmryTermAndAmtBean outBean = NSXC003001CalcSchdSmryTermAndAmt.calcSchdSmryTermAndAmt(inBean);
        if (outBean != null && outBean.getBaseList().size() > 0) {
            return outBean.getBaseList().get(0).getPerSchdNum();
        }
        return tMsg.perSchdNum.getValue();
    }
    //end 2017/11/07 QC#22301,add

    // START 2018/03/27 M.Naito [QC#18672, ADD]
    public static BigDecimal getNewAddlChrgDealAmt(String glblCmpyCd, Map<String, Object> svcInvAddlChrgMap, Map<String, Object> bllgSchdMap, AddlChrgFromThruDtInfo addlChrgFromThruDtinfo, BigDecimal srcPrcAmt) {

        if (!hasValue((BigDecimal) svcInvAddlChrgMap.get("DS_CONTR_ADDL_CHRG_PK"))) {
            return BigDecimal.ZERO;
        }
        CalcAddlChrgInfo calcAddlChrgInfo = new CalcAddlChrgInfo();
        if (!hasValue(srcPrcAmt)) {
            srcPrcAmt = BigDecimal.ZERO;
        }

        calcAddlChrgInfo.setBasePrcAmt(srcPrcAmt);
        calcAddlChrgInfo.setFlatRateAmt((BigDecimal) svcInvAddlChrgMap.get("ADDL_CHRG_FLAT_DEAL_PRC_AMT"));
        calcAddlChrgInfo.setAplcPct((BigDecimal) svcInvAddlChrgMap.get("ADDL_CHRG_APLC_PCT"));
        calcAddlChrgInfo.setInvUpToDt((String) svcInvAddlChrgMap.get("INV_UP_TO_DT"));
        calcAddlChrgInfo.setAddlChrgFromDt(addlChrgFromThruDtinfo.getAddlChrgFromDt());
        calcAddlChrgInfo.setAddlChrgThueDt(addlChrgFromThruDtinfo.getAddlChrgThruDt());
        calcAddlChrgInfo.setBllgFromDt(addlChrgFromThruDtinfo.getBllgFromDt());
        calcAddlChrgInfo.setBllgThruDt(addlChrgFromThruDtinfo.getBllgThruDt());
        calcAddlChrgInfo.setAftDeclPntDigitNum((BigDecimal) svcInvAddlChrgMap.get("AFT_DECL_PNT_DIGIT_NUM"));
        calcAddlChrgInfo.setSvcMachCnt(BigDecimal.ONE);
        calcAddlChrgInfo.setPerBllgCycleCd((String) bllgSchdMap.get("PER_BLLG_CYCLE_CD"));
        calcAddlChrgInfo.setPrrtDivRate((BigDecimal) svcInvAddlChrgMap.get("PRRT_DIV_RATE"));
        NSXC001001CalcAddlChrg calcAddlChrg = new NSXC001001CalcAddlChrg();
        calcAddlChrg.calcAddrChrg(calcAddlChrgInfo);
        return calcAddlChrgInfo.getAddlChrgAmt();
    }

    public static AddlChrgFromThruDtInfo getAddlChrgFromThruDtInfo(String glblCmpyCd, Map<String, Object> bllgSchdMap, Map<String, Object> rsltAddlChrg) {

        NSXC001001GetAddlChrgFromThruDt getAddlChrgFromThruDt = new NSXC001001GetAddlChrgFromThruDt();
        AddlChrgFromThruDtInfo addlChrgFromThruDtInfo = new AddlChrgFromThruDtInfo();

        if (bllgSchdMap == null) {
            return null;
        }

        addlChrgFromThruDtInfo.setEffFromDt((String) rsltAddlChrg.get("EFF_FROM_DT"));
        addlChrgFromThruDtInfo.setEffThruDt((String) rsltAddlChrg.get("EFF_THRU_DT"));
        addlChrgFromThruDtInfo.setTrmnDt((String) rsltAddlChrg.get("TRMN_DT"));
        String invUpToDt = (String) rsltAddlChrg.get("INV_UP_TO_DT");
        String preCalcAddlNextBllgDt = query.getPreCalcAddlNextBllgDt(glblCmpyCd, (String) bllgSchdMap.get("BLLG_SCHD_FROM_DT"), (BigDecimal) rsltAddlChrg.get("DS_CONTR_ADDL_CHRG_PK"));
        if (!hasValue(invUpToDt) || invUpToDt.compareTo(preCalcAddlNextBllgDt) < 0) {
            invUpToDt = preCalcAddlNextBllgDt;
        }
        addlChrgFromThruDtInfo.setInvUpToDt(invUpToDt);
        addlChrgFromThruDtInfo.setBllgFromDt((String) bllgSchdMap.get("BLLG_SCHD_FROM_DT"));
        addlChrgFromThruDtInfo.setBllgThruDt((String) bllgSchdMap.get("BLLG_SCHD_THRU_DT"));
        addlChrgFromThruDtInfo.setBllgCycleMthAot((BigDecimal) rsltAddlChrg.get("BLLG_CYCLE_MTH_AOT"));
        getAddlChrgFromThruDt.getAddlChrgFromThruDt(addlChrgFromThruDtInfo);
        if (ZYPDateUtil.compare((String) bllgSchdMap.get("BLLG_SCHD_FROM_DT"), addlChrgFromThruDtInfo.getAddlChrgFromDt()) < 0) {
            return null;
        }
        return addlChrgFromThruDtInfo;
    }
    // END 2018/03/27 M.Naito [QC#18672, ADD]

    // START 2018/06/25 K.Kitachi [QC#22245, ADD]
    private static boolean existUnapprovedUsgChrgForDtl(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        List<BigDecimal> svcContrBllgPkList = query.getUnapprovedUsgChrgForDtl(glblCmpyCd, dsContrDtlPk);
        if (svcContrBllgPkList.isEmpty()) {
            return false;
        }
        return true;
    }

    private static boolean existUnapprovedUsgChrg(String glblCmpyCd, BigDecimal dsContrPk) {
        List<BigDecimal> svcContrBllgPkList = query.getUnapprovedUsgChrg(glblCmpyCd, dsContrPk);
        if (svcContrBllgPkList.isEmpty()) {
            return false;
        }
        return true;
    }

    private static boolean existUnapprovedCrRebilForBase(String glblCmpyCd, BigDecimal svcCrRebilPk, BigDecimal dsContrDtlPk) {
        List<BigDecimal> svcCrRebilPkList = query.getUnapprovedCrRebilForBase(glblCmpyCd, svcCrRebilPk, dsContrDtlPk);
        if (svcCrRebilPkList.isEmpty()) {
            return false;
        }
        return true;
    }

    private static boolean existUnapprovedCrRebilForMtr(String glblCmpyCd, BigDecimal svcCrRebilPk, BigDecimal dsContrDtlPk) {
        List<BigDecimal> svcCrRebilPkList = query.getUnapprovedCrRebilForMtr(glblCmpyCd, svcCrRebilPk, dsContrDtlPk);
        if (svcCrRebilPkList.isEmpty()) {
            return false;
        }
        return true;
    }

    private static boolean existUnapprovedCrRebil(String glblCmpyCd, BigDecimal svcCrRebilPk, BigDecimal dsContrPk) {
        List<BigDecimal> svcCrRebilPkList = query.getUnapprovedCrRebil(glblCmpyCd, svcCrRebilPk, dsContrPk);
        if (svcCrRebilPkList.isEmpty()) {
            return false;
        }
        return true;
    }
    // END 2018/06/25 K.Kitachi [QC#22245, ADD]

    // START 2018/06/29 K.Kitachi [QC#22057, ADD]
    private static BigDecimal nullToZero(BigDecimal val) {
        if (!hasValue(val)) {
            return BigDecimal.ZERO;
        }
        return val;
    }

    private static boolean matchKey(NSZC053001_xxCrRebilDtlListPMsg inPMsg, String svcInvNum, BigDecimal dsContrDtlPk) {

        return (equals(inPMsg.origSvcInvNum.getValue(), svcInvNum)
                    && equals(inPMsg.dsContrDtlPk.getValue(), dsContrDtlPk));
    }

    private static boolean matchKey(NSZC053001_xxCrRebilDtlListPMsg inPMsg, String svcInvNum, BigDecimal dsContrDtlPk, String bllgMtrLbCd) {

        return (equals(inPMsg.origSvcInvNum.getValue(), svcInvNum)
                    && equals(inPMsg.dsContrDtlPk.getValue(), dsContrDtlPk)
                    && equals(inPMsg.bllgMtrLbCd.getValue(), bllgMtrLbCd));
    }
    // END 2018/06/29 K.Kitachi [QC#22057, ADD]

    // START 2018/07/18 K.Kojima [QC#26791,ADD]
    public static void adjustMtrFmla(S21ApiMessageMap msgMap, BigDecimal svcCrRebilPk, String origSvcInvNum, List<SVC_CR_REBIL_MTR_READTMsg> fixMtrReadList, boolean negativeCheckFlag) {
        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();

        // Get Adjust Target Invoice/Machine
        List<Map<String, Object>> adjustTargetMachineList = query.getAdjustTargetMachine(glblCmpyCd, svcCrRebilPk, origSvcInvNum);

        for (Map<String, Object> adjustTargetMachine : adjustTargetMachineList) {
            BigDecimal svcMachMstrPk = (BigDecimal) adjustTargetMachine.get("SVC_MACH_MSTR_PK");

            // Get Adjust Target Meter Read
            List<Map<String, Object>> adjustTargetMeterReadList = query.getAdjustTargetMtrRead(glblCmpyCd, svcCrRebilPk, origSvcInvNum, svcMachMstrPk);

            List<Map<String, Object>> mtrFmlaInfoList = query.getMtrFmlaInfo(glblCmpyCd, svcMachMstrPk);
            for (Map<String, Object> mtrFmlaInfo : mtrFmlaInfoList) {
                String mtrLbCd = (String) mtrFmlaInfo.get("MTR_LB_CD");
                BigDecimal svcCrRebilMtrReadPk = null;
                for (Map<String, Object> adjustTargetMeterRead : adjustTargetMeterReadList) {
                    String physMtrLbCd = (String) adjustTargetMeterRead.get("PHYS_MTR_LB_CD");
                    if (mtrLbCd.equals(physMtrLbCd)) {
                        svcCrRebilMtrReadPk = (BigDecimal) adjustTargetMeterRead.get("SVC_CR_REBIL_MTR_READ_PK");
                        break;
                    }
                }
                if (svcCrRebilMtrReadPk == null) {
                    continue;
                }
                SVC_CR_REBIL_MTR_READTMsg svcCrRebilMtrReadTMsg = new SVC_CR_REBIL_MTR_READTMsg();
                setValue(svcCrRebilMtrReadTMsg.glblCmpyCd, glblCmpyCd);
                setValue(svcCrRebilMtrReadTMsg.svcCrRebilMtrReadPk, svcCrRebilMtrReadPk);
                svcCrRebilMtrReadTMsg = (SVC_CR_REBIL_MTR_READTMsg) S21ApiTBLAccessor.findByKeyForUpdate(svcCrRebilMtrReadTMsg);
                if (svcCrRebilMtrReadTMsg == null) {
                    continue;
                }

                BigDecimal calcNewStartReadMtrCnt = BigDecimal.ZERO;
                BigDecimal calcNewEndReadMtrCnt = BigDecimal.ZERO;
                BigDecimal calcNewTestMtrCnt = BigDecimal.ZERO;
                boolean existsNewStartReadMtrCnt = false;
                boolean existsNewEndReadMtrCnt = false;
                boolean existsNewTestMtrCnt = false;
                for (int i = 1; i <= 10; i++) {
                    String number = String.valueOf(i);
                    if (i < 10) {
                        number = "0" + number;
                    }
                    String mtrFmlaTpCd = (String) mtrFmlaInfo.get("MTR_FMLA_TP_CD_" + number);
                    String fmlaMtrLbCd = (String) mtrFmlaInfo.get("FMLA_MTR_LB_CD_" + number);
                    if (!hasValue(mtrFmlaTpCd) || !hasValue(fmlaMtrLbCd)) {
                        break;
                    }
                    if (i != 1 && MTR_FMLA_TP.EQUALS.equals(mtrFmlaTpCd)) {
                        break;
                    }
                    for (Map<String, Object> adjustTargetMeterRead : adjustTargetMeterReadList) {
                        String physMtrLbCd = (String) adjustTargetMeterRead.get("PHYS_MTR_LB_CD");
                        BigDecimal oldStartReadMtrCnt = (BigDecimal) adjustTargetMeterRead.get("OLD_START_READ_MTR_CNT");
                        BigDecimal oldEndReadMtrCnt = (BigDecimal) adjustTargetMeterRead.get("OLD_END_READ_MTR_CNT");
                        BigDecimal oldTestMtrCnt = (BigDecimal) adjustTargetMeterRead.get("OLD_TEST_MTR_CNT");
                        BigDecimal newStartReadMtrCnt = (BigDecimal) adjustTargetMeterRead.get("NEW_START_READ_MTR_CNT");
                        BigDecimal newEndReadMtrCnt = (BigDecimal) adjustTargetMeterRead.get("NEW_END_READ_MTR_CNT");
                        BigDecimal newTestMtrCnt = (BigDecimal) adjustTargetMeterRead.get("NEW_TEST_MTR_CNT");
                        if (fmlaMtrLbCd.equals(physMtrLbCd)) {
                            if (hasValue(newStartReadMtrCnt)) {
                                existsNewStartReadMtrCnt = true;
                            }
                            if (hasValue(newEndReadMtrCnt)) {
                                existsNewEndReadMtrCnt = true;
                            }
                            if (hasValue(newTestMtrCnt)) {
                                existsNewTestMtrCnt = true;
                            }
                            if (MTR_FMLA_TP.EQUALS.equals(mtrFmlaTpCd)) {
                                if (hasValue(newStartReadMtrCnt) || hasValue(oldStartReadMtrCnt)) {
                                    calcNewStartReadMtrCnt = getMtrCnt(newStartReadMtrCnt, oldStartReadMtrCnt);
                                }
                                if (hasValue(newEndReadMtrCnt) || hasValue(oldEndReadMtrCnt)) {
                                    calcNewEndReadMtrCnt = getMtrCnt(newEndReadMtrCnt, oldEndReadMtrCnt);
                                }
                                if (hasValue(newTestMtrCnt) || hasValue(oldTestMtrCnt)) {
                                    calcNewTestMtrCnt = getMtrCnt(newTestMtrCnt, oldTestMtrCnt);
                                }
                            } else if (MTR_FMLA_TP.ADD.equals(mtrFmlaTpCd)) {
                                if (hasValue(newStartReadMtrCnt) || hasValue(oldStartReadMtrCnt)) {
                                    calcNewStartReadMtrCnt = calcNewStartReadMtrCnt.add(getMtrCnt(newStartReadMtrCnt, oldStartReadMtrCnt));
                                }
                                if (hasValue(newEndReadMtrCnt) || hasValue(oldEndReadMtrCnt)) {
                                    calcNewEndReadMtrCnt = calcNewEndReadMtrCnt.add(getMtrCnt(newEndReadMtrCnt, oldEndReadMtrCnt));
                                }
                                if (hasValue(newTestMtrCnt) || hasValue(oldTestMtrCnt)) {
                                    calcNewTestMtrCnt = calcNewTestMtrCnt.add(getMtrCnt(newTestMtrCnt, oldTestMtrCnt));
                                }
                            } else if (MTR_FMLA_TP.SUBTRACT.equals(mtrFmlaTpCd)) {
                                if (hasValue(newStartReadMtrCnt) || hasValue(oldStartReadMtrCnt)) {
                                    calcNewStartReadMtrCnt = calcNewStartReadMtrCnt.subtract(getMtrCnt(newStartReadMtrCnt, oldStartReadMtrCnt));
                                }
                                if (hasValue(newEndReadMtrCnt) || hasValue(oldEndReadMtrCnt)) {
                                    calcNewEndReadMtrCnt = calcNewEndReadMtrCnt.subtract(getMtrCnt(newEndReadMtrCnt, oldEndReadMtrCnt));
                                }
                                if (hasValue(newTestMtrCnt) || hasValue(oldTestMtrCnt)) {
                                    calcNewTestMtrCnt = calcNewTestMtrCnt.subtract(getMtrCnt(newTestMtrCnt, oldTestMtrCnt));
                                }
                            }
                            break;
                        }
                    }
                }
                if (negativeCheckFlag) {
                    if (hasValue(calcNewStartReadMtrCnt) && calcNewStartReadMtrCnt.compareTo(BigDecimal.ZERO) < 0) {
                        msgMap.addXxMsgId(NSZM1333E);
                        return;
                    }
                    if (hasValue(calcNewEndReadMtrCnt) && calcNewEndReadMtrCnt.compareTo(BigDecimal.ZERO) < 0) {
                        msgMap.addXxMsgId(NSZM1333E);
                        return;
                    }
                    if (hasValue(calcNewTestMtrCnt) && calcNewTestMtrCnt.compareTo(BigDecimal.ZERO) < 0) {
                        msgMap.addXxMsgId(NSZM1333E);
                        return;
                    }
                }
                if (existsNewStartReadMtrCnt) {
                    setValue(svcCrRebilMtrReadTMsg.newStartReadMtrCnt, calcNewStartReadMtrCnt);
                } else {
                    svcCrRebilMtrReadTMsg.newStartReadMtrCnt.clear();
                }
                if (existsNewEndReadMtrCnt) {
                    setValue(svcCrRebilMtrReadTMsg.newEndReadMtrCnt, calcNewEndReadMtrCnt);
                } else {
                    svcCrRebilMtrReadTMsg.newEndReadMtrCnt.clear();
                }
                if (existsNewTestMtrCnt) {
                    setValue(svcCrRebilMtrReadTMsg.newTestMtrCnt, calcNewTestMtrCnt);
                } else {
                    svcCrRebilMtrReadTMsg.newTestMtrCnt.clear();
                }
                S21ApiTBLAccessor.update(svcCrRebilMtrReadTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(svcCrRebilMtrReadTMsg.getReturnCode())) {
                    msgMap.addXxMsgId(NSZM0881E);
                    return;
                }
                fixMtrReadList.add(svcCrRebilMtrReadTMsg);
            }
        }
    }

    private static BigDecimal getMtrCnt(BigDecimal newMtrCnt, BigDecimal oldMtrCnt) {
        if (hasValue(newMtrCnt)) {
            return newMtrCnt;
        } else if (hasValue(oldMtrCnt)) {
            return oldMtrCnt;
        }
        return null;
    }

    public static boolean checkNegativeRead(String glblCmpyCd, BigDecimal svcCrRebilPk) {
        BigDecimal count = query.countNegativeRead(glblCmpyCd, svcCrRebilPk);
        if (count != null && count.compareTo(BigDecimal.ONE) >= 0) {
            return false;
        }
        return true;
    }
    // END 2018/07/18 K.Kojima [QC#26791,ADD]
    // START 2018/08/27 [QC#24555]
    public static BigDecimal calcNewBaseDealAmt(SVC_CR_REBIL_BASE_BLLGTMsg inTMsg, BigDecimal newBaseDealAmt) {
        if (!hasValue(newBaseDealAmt)) {
            return null;
        }
        BigDecimal calcNewBaseDealAmt = newBaseDealAmt;
        BigDecimal newBaseUnitAmt = inTMsg.newBaseUnitAmt.getValue();
        String glblCmpyCd = inTMsg.glblCmpyCd.getValue();
        Map<String, Object> dsContrBllgSchdInfo = query.getDsContrBllgSchdInfo(glblCmpyCd, inTMsg.svcInvLinePk.getValue());
        if (dsContrBllgSchdInfo == null) {
            return calcNewBaseDealAmt;
        }
        if (FLG_OFF_N.equals((String) dsContrBllgSchdInfo.get("BLLG_SCHD_PRRT_FLG"))) {
            return calcNewBaseDealAmt;
        }
        String bllgCycleCd = (String) dsContrBllgSchdInfo.get("BLLG_CYCLE_CD");
        BigDecimal bPerSchdNum = (BigDecimal) dsContrBllgSchdInfo.get("PER_SCHD_NUM");
        int digitNum = ((BigDecimal) dsContrBllgSchdInfo.get("DIGIT_NUM")).intValue();
        calcNewBaseDealAmt = NSXC003001CalcSchdSmryTermAndAmt.calcBasePrcAmtForStub(glblCmpyCd, bllgCycleCd, newBaseUnitAmt, bPerSchdNum, digitNum);
        return calcNewBaseDealAmt;
    }

    public static BigDecimal calcNewAllowance(SVC_CR_REBIL_XS_MTR_BLLGTMsg inTMsg) {
        BigDecimal calcNewAllowance = inTMsg.newUnitXsCopyQty.getValue();
        if (!hasValue(calcNewAllowance)) {
            return null;
        }
        // START 2023/01/16 R.Jin [QC#58890, MOD]
        if (!hasValue(inTMsg.svcInvLinePk) && hasValue(inTMsg.newXsCopyQty)) {
            return inTMsg.newXsCopyQty.getValue();
        }
        // END 2023/01/16 R.Jin [QC#58890, MOD]
        String glblCmpyCd = inTMsg.glblCmpyCd.getValue();
        Map<String, Object> dsContrBllgSchdInfo = query.getDsContrBllgSchdInfo(glblCmpyCd, inTMsg.svcInvLinePk.getValue());
        if (dsContrBllgSchdInfo == null) {
            return calcNewAllowance;
        }
        if (FLG_OFF_N.equals((String) dsContrBllgSchdInfo.get("BLLG_SCHD_PRRT_FLG"))) {
            return calcNewAllowance;
        }
        String bllgCycleCd = (String) dsContrBllgSchdInfo.get("BLLG_CYCLE_CD");
        BigDecimal bPerSchdNum = (BigDecimal) dsContrBllgSchdInfo.get("PER_SCHD_NUM");
        calcNewAllowance = NSXC003001CalcSchdSmryTermAndAmt.calcAllowanceForStub(glblCmpyCd, bllgCycleCd, calcNewAllowance, bPerSchdNum);
        return calcNewAllowance;
    }
    // END   2018/08/27 [QC#24555]

    // START 2018/09/05 [QC#24555]
    private static void adjustTopSchd(String glblCmpyCd, BigDecimal dsContrDtlPk, String baseChrgFlg, String effFromDt) {
        List<Map<String, Object>> targetList = query.getAdjustTopSchd(glblCmpyCd, dsContrDtlPk, baseChrgFlg, effFromDt);
        for (Map<String, Object> target : targetList) {

            DS_CONTR_BLLG_SCHD_SMRYTMsg topSchd = query.getDsContrBllgSchdSmryForUpdate(glblCmpyCd, (BigDecimal) target.get("DS_CONTR_BLLG_SCHD_SMRY_PK"));
            if (topSchd == null) {
                continue;
            }
            if (BLLG_CYCLE.DAILY.equals(topSchd.perBllgCycleCd.getValue())) {
                setValue(topSchd.perSchdNum, (BigDecimal) target.get("PER_SCHD_NUM_D"));
            } else {
                setValue(topSchd.perSchdNum, (BigDecimal) target.get("PER_SCHD_NUM"));
            }
            if (FLG_ON_Y.equals(baseChrgFlg)) {
                setValue(topSchd.baseSubTotPrcDealAmt, (BigDecimal) target.get("BASE_SUB_TOT_PRC_DEAL_AMT"));
                setValue(topSchd.basePrcDealAdjAmt, (BigDecimal) target.get("BASE_PRC_DEAL_ADJ_AMT"));
            }
            S21ApiTBLAccessor.update(topSchd);
        }
    }

    private static void logicalRemoveTopSchd(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String baseChrgFlg) {
        List<BigDecimal> logicalRemoveTopSchdPkList = query.getLogicalRemoveTopSchdPk(glblCmpyCd, dsContrDtlPk, dsContrBllgMtrPk, baseChrgFlg);
        for (BigDecimal logicalRemoveTopSchdPk : logicalRemoveTopSchdPkList) {
            DS_CONTR_BLLG_SCHD_SMRYTMsg topSchd = query.getDsContrBllgSchdSmryForUpdate(glblCmpyCd, logicalRemoveTopSchdPk);
            if (topSchd == null) {
                continue;
            }
            S21ApiTBLAccessor.logicalRemove(topSchd);
        }
    }

    private static void logicalRemovePrcEff(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String baseChrgFlg, String effFromDt, String effThruDt) {
        List<BigDecimal> logicalRemovePrcEffPkList = query.getLogicalRemovePrcEffPk(glblCmpyCd, dsContrDtlPk, dsContrBllgMtrPk, baseChrgFlg, effFromDt, effThruDt);
        for (BigDecimal logicalRemovePrcEffPk : logicalRemovePrcEffPkList) {
            DS_CONTR_PRC_EFFTMsg prcEff = query.getDsContrPrcEffForUpdate(glblCmpyCd, logicalRemovePrcEffPk);
            if (prcEff == null) {
                continue;
            }
            S21ApiTBLAccessor.logicalRemove(prcEff);

            if (baseChrgFlg.equals(ZYPConstant.FLG_OFF_N)) {
                DS_CONTR_PRC_EFF_MTRTMsgArray prcEffMtrTMsgArray = query.getPrcEffTMsgArray(glblCmpyCd, prcEff.dsContrPrcEffPk.getValue(), prcEff.dsContrBllgMtrPk.getValue());
                for (int peMtrCnt = 0; peMtrCnt < prcEffMtrTMsgArray.getValidCount(); peMtrCnt++) {
                    S21ApiTBLAccessor.logicalRemove(prcEffMtrTMsgArray.no(peMtrCnt));
                }
            }

            DS_CONTR_BLLG_SCHD_SMRYTMsgArray topSchdTMsgArray = query.getDsContrBllgSchdSmryTMsgArray(glblCmpyCd, prcEff.dsContrPrcEffPk.getValue());
            for (int topSchdCnt = 0; topSchdCnt < topSchdTMsgArray.getValidCount(); topSchdCnt++) {
                S21ApiTBLAccessor.logicalRemove(topSchdTMsgArray.no(topSchdCnt));
            }
        }
    }

    // END 2018/09/05 [QC#24555]

    // START 2018/08/31 K.Kojima [QC#27960,ADD]
    private static void replacePrcEffPkSkipMonth(String glblCmpyCd, BigDecimal dsContrDtlPk, String baseChrgFlg) {
        List<BigDecimal> dsContrBllgSchdSmryPkList = new ArrayList<BigDecimal>();

        List<Map<String, Object>> targetList = query.getReplacePrcEffPkSkipMonth(glblCmpyCd, dsContrDtlPk, baseChrgFlg);
        for (Map<String, Object> target : targetList) {
            DS_CONTR_BLLG_SCHDTMsg dsContrBllgSchdTMsg = query.getDsContrBllgSchdForUpdate(glblCmpyCd, (BigDecimal) target.get("DS_CONTR_BLLG_SCHD_PK"));
            if (dsContrBllgSchdTMsg == null) {
                continue;
            }
            setValue(dsContrBllgSchdTMsg.dsContrPrcEffPk, (BigDecimal) target.get("DS_CONTR_PRC_EFF_PK"));
            setValue(dsContrBllgSchdTMsg.dsContrPrcEffSqNum, (BigDecimal) target.get("DS_CONTR_PRC_EFF_SQ_NUM"));
            setValue(dsContrBllgSchdTMsg.dsContrBllgSchdSmryPk, (BigDecimal) target.get("DS_CONTR_BLLG_SCHD_SMRY_PK"));
            setValue(dsContrBllgSchdTMsg.dsContrBllgSchdSqNum, (String) target.get("DS_CONTR_BLLG_SCHD_SQ_NUM"));
            S21ApiTBLAccessor.update(dsContrBllgSchdTMsg);
            dsContrBllgSchdSmryPkList.add(dsContrBllgSchdTMsg.dsContrBllgSchdSmryPk.getValue());
        }

        for (BigDecimal dsContrBllgSchdSmryPk : dsContrBllgSchdSmryPkList) {
            List<BigDecimal> replaceDsContrBllgSchdSqNumTargetList = query.getReplaceDsContrBllgSchdSqNumTarget(glblCmpyCd, dsContrBllgSchdSmryPk);
            if (replaceDsContrBllgSchdSqNumTargetList == null || replaceDsContrBllgSchdSqNumTargetList.size() == 0) {
                continue;
            }
            int dsContrBllgSchdSqNum = 1;
            for (BigDecimal replaceDsContrBllgSchdSqNumTarget : replaceDsContrBllgSchdSqNumTargetList) {
                DS_CONTR_BLLG_SCHDTMsg dsContrBllgSchdTMsg = query.getDsContrBllgSchdForUpdate(glblCmpyCd, replaceDsContrBllgSchdSqNumTarget);
                if (dsContrBllgSchdTMsg == null) {
                    continue;
                }
                setValue(dsContrBllgSchdTMsg.dsContrBllgSchdSqNum, String.valueOf(dsContrBllgSchdSqNum));
                S21ApiTBLAccessor.update(dsContrBllgSchdTMsg);
                dsContrBllgSchdSqNum++;
            }
        }
    }
    // END 2018/08/31 K.Kojima [QC#27960,ADD]
    // START 2018/09/05 [QC#24555,ADD]
    public static void roundAdjustForCiEntry(S21ApiMessageMap msgMap, SVC_CR_REBILTMsg crRebilTMsg) {
        String glblCmpyCd = crRebilTMsg.glblCmpyCd.getValue();
        BigDecimal svcCrRebilPk = crRebilTMsg.svcCrRebilPk.getValue();

        roundAdjustBaseCiEntry(glblCmpyCd, svcCrRebilPk);
        roundAdjustAllowanceCiEntry(glblCmpyCd, svcCrRebilPk);
    }

    public static void roundAdjustBaseCiEntry(String glblCmpyCd, BigDecimal svcCrRebilPk) {
        List<BigDecimal> dsContrDtlPkList = query.getDsContrDtlPkFromBaseRebil(glblCmpyCd, svcCrRebilPk);
        for (BigDecimal dsContrDtlPk : dsContrDtlPkList) {
            List<Map<String, Object>> stubSchdInfoList = query.getRoundAdjustBaseForCiEntry(glblCmpyCd, svcCrRebilPk, dsContrDtlPk);
            if (stubSchdInfoList.size() != 2) {
                continue;
            }
            BigDecimal newBaseUnitAmt = (BigDecimal) stubSchdInfoList.get(0).get("NEW_BASE_UNIT_AMT");
            if (!hasValue(newBaseUnitAmt) || BigDecimal.ZERO.compareTo(newBaseUnitAmt) == 0) {
                continue;
            }
            String stub1BllgCycle = (String) stubSchdInfoList.get(0).get("BLLG_CYCLE_CD");
            String stub2BllgCycle = (String) stubSchdInfoList.get(1).get("BLLG_CYCLE_CD");
            if (!stub1BllgCycle.equals(stub2BllgCycle)) {
                continue;
            }
            String fromDt = (String) stubSchdInfoList.get(0).get("BLLG_SCHD_FROM_DT");
            String thruDt = (String) stubSchdInfoList.get(1).get("BLLG_SCHD_THRU_DT");
            BigDecimal bllgCycleMthAot = (BigDecimal) stubSchdInfoList.get(0).get("BLLG_CYCLE_MTH_AOT");
            if (!beDivisibleDurnWithFreq(fromDt, thruDt, bllgCycleMthAot)) {
                continue;
            }
            BigDecimal stub1BaseDealAmt = (BigDecimal) stubSchdInfoList.get(0).get("BASE_DEAL_AMT");
            BigDecimal stub2BaseDealAmt = (BigDecimal) stubSchdInfoList.get(1).get("BASE_DEAL_AMT");
            BigDecimal adjustBaseAmt = newBaseUnitAmt.subtract(stub1BaseDealAmt);
            if (adjustBaseAmt.compareTo(BigDecimal.ZERO) < 0 || adjustBaseAmt.compareTo(stub2BaseDealAmt) == 0) {
                continue;
            }
            SVC_CR_REBIL_BASE_BLLGTMsg stub2RebilBase = query.getSvcCrRebilBaseBllgTMsgForUpdate(glblCmpyCd, (BigDecimal) stubSchdInfoList.get(1).get("SVC_CR_REBIL_BASE_BLLG_PK"));
            if (stub2RebilBase == null) {
                continue;
            }
            setValue(stub2RebilBase.newBaseDealAmt, adjustBaseAmt);
            S21ApiTBLAccessor.update(stub2RebilBase);
        }
        
    }

    public static void roundAdjustAllowanceCiEntry(String glblCmpyCd, BigDecimal svcCrRebilPk) {
        List<BigDecimal> dsContrBllgMtrPkList = query.getDsContrBllgMtrPkFromRebilMtr(glblCmpyCd, svcCrRebilPk, null);
        for (BigDecimal dsContrBllgMtrPk : dsContrBllgMtrPkList) {
            List<Map<String, Object>> stubSchdInfoList = query.getRoundAdjustMtrBllgForCiEntry(glblCmpyCd, svcCrRebilPk, dsContrBllgMtrPk);
            if (stubSchdInfoList.size() != 2) {
                continue;
            }
            String stub1BllgCycle = (String) stubSchdInfoList.get(0).get("BLLG_CYCLE_CD");
            String stub2BllgCycle = (String) stubSchdInfoList.get(1).get("BLLG_CYCLE_CD");
            if (!stub1BllgCycle.equals(stub2BllgCycle)) {
                continue;
            }
            String fromDt = (String) stubSchdInfoList.get(0).get("BLLG_SCHD_FROM_DT");
            String thruDt = (String) stubSchdInfoList.get(1).get("BLLG_SCHD_THRU_DT");
            BigDecimal bllgCycleMthAot = (BigDecimal) stubSchdInfoList.get(0).get("BLLG_CYCLE_MTH_AOT");
            if (!beDivisibleDurnWithFreq(fromDt, thruDt, bllgCycleMthAot)) {
                continue;
            }
            List<Map<String, Object>> stub1TierInfoList = query.getRoundAdjustXsMtrForCiEntry(glblCmpyCd, (BigDecimal) stubSchdInfoList.get(0).get("SVC_CR_REBIL_MTR_BLLG_PK"));
            List<Map<String, Object>> stub2TierInfoList = query.getRoundAdjustXsMtrForCiEntry(glblCmpyCd, (BigDecimal) stubSchdInfoList.get(1).get("SVC_CR_REBIL_MTR_BLLG_PK"));;
            if (stub1TierInfoList.size() != stub2TierInfoList.size()) {
                continue;
            }
            boolean hasNewAllowance = false;
            for (Map<String, Object> stub1TierInfo : stub1TierInfoList) {
                if (hasValue((BigDecimal) stub1TierInfo.get("UNIT_XS_COPY"))) {
                    hasNewAllowance = true;
                    break;
                }
            }
            if (hasNewAllowance == false) {
                continue;
            }
            for(int i = 0; i < stub1TierInfoList.size(); i++) {
                BigDecimal newUnitXsCopy = (BigDecimal) stub1TierInfoList.get(i).get("UNIT_XS_COPY");
                if (!hasValue(newUnitXsCopy)) {
                    continue;
                }
                BigDecimal stub1XsCopy = (BigDecimal) stub1TierInfoList.get(i).get("XS_COPY_QTY");
                BigDecimal stub2XsCopy = (BigDecimal) stub2TierInfoList.get(i).get("XS_COPY_QTY");
                BigDecimal adjustXsCopy = newUnitXsCopy.subtract(stub1XsCopy);
                if (adjustXsCopy.compareTo(BigDecimal.ZERO) < 0 || adjustXsCopy.compareTo(stub2XsCopy) == 0) {
                    continue;
                }
                BigDecimal stub2XsMtrPk = (BigDecimal) stub2TierInfoList.get(i).get("SVC_CR_REBIL_XS_MTR_BLLG_PK");
                SVC_CR_REBIL_XS_MTR_BLLGTMsg stub2XsMtr = query.getSvcCrRebilXsMtrBllgTMsgForUpdate(glblCmpyCd, stub2XsMtrPk);
                if (stub2XsMtr == null) {
                    continue;
                }
                setValue(stub2XsMtr.newXsCopyQty, adjustXsCopy);
                S21ApiTBLAccessor.update(stub2XsMtr);
            }
        }
        
    }

    public static void roundAdjustForApproval(S21ApiMessageMap msgMap, SVC_CR_REBILTMsg crRebilTMsg, BigDecimal dsContrDtlPk) {
        String glblCmpyCd = crRebilTMsg.glblCmpyCd.getValue();
        BigDecimal svcCrRebilPk = crRebilTMsg.svcCrRebilPk.getValue();

        roundAdjustBaseApproval(glblCmpyCd, svcCrRebilPk, dsContrDtlPk);
        roundAdjustAllowanceApproval(glblCmpyCd, svcCrRebilPk, dsContrDtlPk);
    }

    public static void roundAdjustBaseApproval(String glblCmpyCd, BigDecimal svcCrRebilPk, BigDecimal dsContrDtlPk) {
        List<Map<String, Object>> ciEntrySchdInfoList = query.getRoundAdjustBaseForApproval(glblCmpyCd, svcCrRebilPk, dsContrDtlPk);
        if (ciEntrySchdInfoList.size() == 0) {
            return;
        }
        int ciEntryStubCnt = 0;
        BigDecimal ciEntryUnitAmt = null;
        BigDecimal ciEntryBaseAmt = null;
        BigDecimal ciEntryPerSchdNum = BigDecimal.ONE;
        BigDecimal ciEntryMthAot = BigDecimal.ONE;
        int ciEntrydigitNum = 2;
        String ciEntryBllgCycleCd = "";
        String ciEntryFromDt = "";
        String ciEntryThruDateMax = "";
        for (Map<String, Object> ciEntrySchdInfo : ciEntrySchdInfoList) {
            String curPeriodPrrtFlg = (String) ciEntrySchdInfo.get("BLLG_SCHD_PRRT_FLG");
            BigDecimal curPeriodUnitAmt = (BigDecimal) ciEntrySchdInfo.get("BASE_UNIT_AMT");
            BigDecimal curPeriodBaseAmt = (BigDecimal) ciEntrySchdInfo.get("BASE_DEAL_AMT");
            String curPeriodBllgCycleCd = (String) ciEntrySchdInfo.get("BLLG_CYCLE_CD");
            BigDecimal curPeriodPerSchdNum = (BigDecimal) ciEntrySchdInfo.get("PER_SCHD_NUM");
            if (!ciEntryBllgCycleCd.equals(curPeriodBllgCycleCd) || !equals(ciEntryUnitAmt, curPeriodUnitAmt)) {
                ciEntryStubCnt = 0;
            }
            ciEntryThruDateMax = (String) ciEntrySchdInfo.get("BLLG_SCHD_THRU_DT");
            if (FLG_OFF_N.equals(curPeriodPrrtFlg)) {
                continue;
            }
            ciEntryStubCnt++;
            ciEntryUnitAmt = curPeriodUnitAmt;
            ciEntryBaseAmt = curPeriodBaseAmt;
            ciEntryBllgCycleCd = curPeriodBllgCycleCd;
            ciEntryPerSchdNum = curPeriodPerSchdNum;
            ciEntryFromDt = (String) ciEntrySchdInfo.get("BLLG_SCHD_FROM_DT");
            ciEntryMthAot = (BigDecimal) ciEntrySchdInfo.get("BLLG_CYCLE_MTH_AOT");
            ciEntrydigitNum = ((BigDecimal) ciEntrySchdInfo.get("AFT_DECL_PNT_DIGIT_NUM")).intValue();
        }
        if (ciEntryStubCnt != 1 || BigDecimal.ZERO.compareTo(ciEntryUnitAmt) == 0) {
            return;
        }
        BigDecimal calcNewBaseDealAmt = NSXC003001CalcSchdSmryTermAndAmt.calcBasePrcAmtForStub(glblCmpyCd, ciEntryBllgCycleCd, ciEntryUnitAmt, ciEntryPerSchdNum, ciEntrydigitNum);
        if (ciEntryBaseAmt.compareTo(calcNewBaseDealAmt) != 0) {
            return;
        }
        List<Map<String, Object>> targetStubInfoList = query.getTargetStubBase(glblCmpyCd, dsContrDtlPk, ciEntryThruDateMax);
        if (targetStubInfoList.size() != 1) {
            return;
        }
        String targetBllgCycle = (String) targetStubInfoList.get(0).get("BLLG_CYCLE_CD");
        String targetThruDt = (String) targetStubInfoList.get(0).get("BLLG_SCHD_THRU_DT");
        BigDecimal targetUnitAmt = (BigDecimal) targetStubInfoList.get(0).get("UNIT_AMT");
        BigDecimal targetBaseAmt = (BigDecimal) targetStubInfoList.get(0).get("BASE_AMT");
        if (!equals(targetBllgCycle, ciEntryBllgCycleCd) || !equals(targetUnitAmt, ciEntryUnitAmt)) {
            return;
        }
        if (!beDivisibleDurnWithFreq(ciEntryFromDt, targetThruDt, ciEntryMthAot)) {
            return;
        }
        BigDecimal adjustBaseAmt = ciEntryUnitAmt.subtract(ciEntryBaseAmt);
        if (adjustBaseAmt.compareTo(BigDecimal.ZERO) < 0 || adjustBaseAmt.compareTo(targetBaseAmt) == 0) {
            return;
        }
        BigDecimal adjustAmt = targetBaseAmt.subtract(adjustBaseAmt);
        BigDecimal dsContrBllgSchdPk = (BigDecimal) targetStubInfoList.get(0).get("DS_CONTR_BLLG_SCHD_PK");
        DS_CONTR_BLLG_SCHDTMsg targetBllgSchd = query.getDsContrBllgSchdForUpdate(glblCmpyCd, dsContrBllgSchdPk);
        if (targetBllgSchd == null) {
            return;
        }
        setValue(targetBllgSchd.baseActlPrcDealAmt, adjustBaseAmt);
        S21ApiTBLAccessor.update(targetBllgSchd);

        BigDecimal dsContrBllgSchdSmryPk = (BigDecimal) targetStubInfoList.get(0).get("DS_CONTR_BLLG_SCHD_SMRY_PK");
        DS_CONTR_BLLG_SCHD_SMRYTMsg targetBllgSchdSmry = query.getDsContrBllgSchdSmryForUpdate(glblCmpyCd, dsContrBllgSchdSmryPk);
        if (targetBllgSchdSmry == null) {
            return;
        }
        BigDecimal subttlAmt = targetBllgSchdSmry.baseSubTotPrcDealAmt.getValue();
        subttlAmt = subttlAmt.subtract(adjustAmt);
        setValue(targetBllgSchdSmry.baseSubTotPrcDealAmt, subttlAmt);
        S21ApiTBLAccessor.update(targetBllgSchdSmry);

        BigDecimal dsContrPrcEffPk = (BigDecimal) targetStubInfoList.get(0).get("DS_CONTR_PRC_EFF_PK");
        DS_CONTR_PRC_EFFTMsg targetPrcEff = query.getDsContrPrcEffForUpdate(glblCmpyCd, dsContrPrcEffPk);
        if (targetPrcEff == null) {
            return;
        }
        BigDecimal basePrcTermDealAmtRate = targetPrcEff.basePrcTermDealAmtRate.getValue();
        basePrcTermDealAmtRate = basePrcTermDealAmtRate.subtract(adjustAmt);
        setValue(targetPrcEff.basePrcTermDealAmtRate, basePrcTermDealAmtRate);
        S21ApiTBLAccessor.update(targetPrcEff);
    }

    public static void roundAdjustAllowanceApproval(String glblCmpyCd, BigDecimal svcCrRebilPk, BigDecimal dsContrDtlPk) {
        List<BigDecimal> dsContrBllgMtrPkList = query.getDsContrBllgMtrPkFromRebilMtr(glblCmpyCd, svcCrRebilPk, dsContrDtlPk);
        for (BigDecimal dsContrBllgMtrPk : dsContrBllgMtrPkList) {
            List<Map<String, Object>> ciEntrySchdInfoList = query.getRoundAdjustMtrBllgForCiEntry(glblCmpyCd, svcCrRebilPk, dsContrBllgMtrPk);
            if (ciEntrySchdInfoList.size() == 0) {
                continue;
            }
            int ciEntryStubCnt = 0;
            BigDecimal ciEntryPerSchdNum = BigDecimal.ONE;
            BigDecimal ciEntryMthAot = BigDecimal.ONE;
            String ciEntryBllgCycleCd = "";
            String ciEntryFromDt = "";
            String ciEntryThruDateMax = "";
            List<Map<String, Object>> ciEntryUnitXsCopy = new ArrayList<Map<String,Object>>();
            for (Map<String, Object> ciEntrySchdInfo : ciEntrySchdInfoList) {
                String curPeriodPrrtFlg = (String) ciEntrySchdInfo.get("BLLG_SCHD_PRRT_FLG");
                String curPeriodBllgCycleCd = (String) ciEntrySchdInfo.get("BLLG_CYCLE_CD");
                BigDecimal curPeriodPerSchdNum = (BigDecimal) ciEntrySchdInfo.get("PER_SCHD_NUM");
                List<Map<String, Object>> curPeriodUnitXsCopy = query.getRoundAdjustXsMtrForCiEntry(glblCmpyCd, (BigDecimal) ciEntrySchdInfo.get("SVC_CR_REBIL_MTR_BLLG_PK"));
                if (!ciEntryBllgCycleCd.equals(curPeriodBllgCycleCd) || !equalsUnitXsCopy(curPeriodUnitXsCopy, ciEntryUnitXsCopy)) {
                    ciEntryStubCnt = 0;
                }
                ciEntryThruDateMax = (String) ciEntrySchdInfo.get("BLLG_SCHD_THRU_DT");
                if (FLG_OFF_N.equals(curPeriodPrrtFlg)) {
                    continue;
                }
                ciEntryStubCnt++;
                ciEntryBllgCycleCd = curPeriodBllgCycleCd;
                ciEntryPerSchdNum = curPeriodPerSchdNum;
                ciEntryFromDt = (String) ciEntrySchdInfo.get("BLLG_SCHD_FROM_DT");
                ciEntryMthAot = (BigDecimal) ciEntrySchdInfo.get("BLLG_CYCLE_MTH_AOT");
                ciEntryUnitXsCopy = curPeriodUnitXsCopy;
            }
            // START 2018/10/25 K.Kojima [QC#28997,MOD]
            // if (ciEntryStubCnt != 1 || (ciEntryUnitXsCopy.size() == 1 && BigDecimal.ZERO.compareTo((BigDecimal)ciEntryUnitXsCopy.get(0).get("UNIT_XS_COPY")) == 0)) {
            if (ciEntryStubCnt != 1) {
                continue;
            }
            if (ciEntryUnitXsCopy.size() == 1 && !hasValue((BigDecimal)ciEntryUnitXsCopy.get(0).get("UNIT_XS_COPY"))) {
                continue;
            }
            if (ciEntryUnitXsCopy.size() == 1 && BigDecimal.ZERO.compareTo((BigDecimal)ciEntryUnitXsCopy.get(0).get("UNIT_XS_COPY")) == 0) {
            // END 2018/10/25 K.Kojima [QC#28997,MOD]
                continue;
            }
            List<Map<String, Object>> targetStubInfoList = query.getTargetStubUsage(glblCmpyCd, dsContrBllgMtrPk, ciEntryThruDateMax);
            if (targetStubInfoList.size() != 1) {
                continue;
            }
            String targetBllgCycle = (String) targetStubInfoList.get(0).get("BLLG_CYCLE_CD");
            String targetThruDt = (String) targetStubInfoList.get(0).get("BLLG_SCHD_THRU_DT");
            if (!beDivisibleDurnWithFreq(ciEntryFromDt, targetThruDt, ciEntryMthAot)) {
                continue;
            }
            BigDecimal dsContrPrcEffPk = (BigDecimal) targetStubInfoList.get(0).get("DS_CONTR_PRC_EFF_PK");
            BigDecimal dsContrBllgSchdSmryPk = (BigDecimal) targetStubInfoList.get(0).get("DS_CONTR_BLLG_SCHD_SMRY_PK");
            List<Map<String, Object>> tartgetUnitXsCopy = query.getTargetXsCopy(glblCmpyCd, dsContrPrcEffPk, dsContrBllgSchdSmryPk);
            if (!ciEntryBllgCycleCd.equals(targetBllgCycle) || !equalsUnitXsCopy(ciEntryUnitXsCopy, tartgetUnitXsCopy)) {
                continue;
            }

            for (int i = 0; i < ciEntryUnitXsCopy.size(); i++) {
                BigDecimal entryXsCopy = (BigDecimal) ciEntryUnitXsCopy.get(i).get("XS_COPY_QTY");
                BigDecimal entryUnitXsCopy = (BigDecimal) ciEntryUnitXsCopy.get(i).get("UNIT_XS_COPY");
                BigDecimal targetXsCopy = (BigDecimal) tartgetUnitXsCopy.get(i).get("XS_COPY_QTY");
                BigDecimal adjustXsCopy = entryUnitXsCopy.subtract(entryXsCopy);
                BigDecimal adjustAot    = targetXsCopy.subtract(adjustXsCopy);
                BigDecimal dsContrBllgSchdMtrPk = (BigDecimal) tartgetUnitXsCopy.get(i).get("DS_CONTR_BLLG_SCHD_MTR_PK");
                if (targetXsCopy.compareTo(adjustXsCopy) == 0) {
                    continue;
                }
                DS_CONTR_BLLG_SCHD_MTRTMsg targetBllgSchdMtr = query.getDsContrBllgSchdMtrForUpdate(glblCmpyCd, dsContrBllgSchdMtrPk);
                if (targetBllgSchdMtr == null) {
                    continue;
                }
                setValue(targetBllgSchdMtr.xsMtrCopyQty, adjustXsCopy);
                setValue(targetBllgSchdMtr.xsMtrAdjCopyQty, adjustAot);
                S21ApiTBLAccessor.update(targetBllgSchdMtr);
            }
        }
    }

    public static boolean equalsUnitXsCopy(List<Map<String, Object>> curPeriodUnitXsCopyList, List<Map<String, Object>> ciEntryUnitXsCopyList) {
        if (curPeriodUnitXsCopyList.size() != ciEntryUnitXsCopyList.size()) {
            return false;
        }
        boolean ret = true;
        for (int i = 0; i < curPeriodUnitXsCopyList.size(); i++) {
            BigDecimal curUnitXsCopy = (BigDecimal) curPeriodUnitXsCopyList.get(i).get("UNIT_XS_COPY");
            BigDecimal ciEntryUnitXsCopy = (BigDecimal) ciEntryUnitXsCopyList.get(i).get("UNIT_XS_COPY");
            BigDecimal curChrgAmt = (BigDecimal) curPeriodUnitXsCopyList.get(i).get("CHRG_AMT");
            BigDecimal ciEntryChrgAmt = (BigDecimal) ciEntryUnitXsCopyList.get(i).get("CHRG_AMT");
            if (!equals(curUnitXsCopy, ciEntryUnitXsCopy) || !equals(curChrgAmt, ciEntryChrgAmt)) {
                ret = false;
                break;
            }
        }
        return ret;
    }


    public static boolean beDivisibleDurnWithFreq(String fromDt, String thruDt, BigDecimal bllgCycleMthAot) {

        //calculate duration
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        Date startDt;
        try {
            startDt = df.parse(fromDt);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }

        String paramEndDate = thruDt;
        Calendar cal = Calendar.getInstance();
        String calcEndDate = "";
        BigDecimal durnCnt = BigDecimal.ZERO;

        while (paramEndDate.compareTo(calcEndDate) > 0) {
            cal.setTime(startDt);
            durnCnt = durnCnt.add(BigDecimal.ONE);

            cal.add(Calendar.MONTH, durnCnt.intValue());
            cal.add(Calendar.DATE, -1);

            calcEndDate = df.format(cal.getTime());
        }

        if (paramEndDate.compareTo(calcEndDate) != 0) {
            return false;
        }

        if (durnCnt.intValue() % bllgCycleMthAot.intValue() == 0) {
            return true;
        }
        return false;
    }

    // END   2018/09/05 [QC#24555,ADD]

    // START 2020/01/08 [QC#55170, ADD]
    /**
     * @param msgMap S21ApiMessageMap
     * @param svcCrRebilPk BigDecimal
     */
    public static void recovPrntSvcContrBllgForRegAcc(S21ApiMessageMap msgMap, BigDecimal svcCrRebilPk) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();

        List<Map<BigDecimal, Object>> prntSvcContrBllgList = query.getPrntSvcContrBllgForRegAcc(glblCmpyCd, svcCrRebilPk);
        if (prntSvcContrBllgList == null || prntSvcContrBllgList.isEmpty()) {
            return;
        }
        for (Map<BigDecimal, Object> prntSvcContrBllgMap : prntSvcContrBllgList) {
            BigDecimal svcContrBllgPk = (BigDecimal) prntSvcContrBllgMap.get("SVC_CONTR_BLLG_PK");
            BigDecimal prntSvcContrBllgPk = (BigDecimal) prntSvcContrBllgMap.get("NEW_PRNT_SVC_CONTR_BLLG_PK");

            if (!hasValue(prntSvcContrBllgPk)) {
                continue;
            }

            SVC_CONTR_BLLGTMsg svcContrBllgTMsg = query.getSvcContrBllgTMsgForUpdate(glblCmpyCd, svcContrBllgPk);

            setValue(svcContrBllgTMsg.prntSvcContrBllgPk, prntSvcContrBllgPk);
            S21ApiTBLAccessor.update(svcContrBllgTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(svcContrBllgTMsg.getReturnCode())) {
                msgMap.addXxMsgId(NSZM0787E);
                return;
            }
        }
    }
    // END 2020/01/08 [QC#55170, ADD]
    
    // add start 2020/06/09 QC#56945
    private static void logicalRemoveDuplicatePrcEff(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String baseChrgFlg, String effFromDt, String effThruDt, BigDecimal newDsContrPrcEffPk) {
        List<BigDecimal> logicalRemovePrcEffPkList = query.getLogicalRemoveDuplicatePrcEffPk(glblCmpyCd, dsContrDtlPk, dsContrBllgMtrPk, baseChrgFlg, effFromDt, effThruDt, newDsContrPrcEffPk);
        for (BigDecimal logicalRemovePrcEffPk : logicalRemovePrcEffPkList) {
            DS_CONTR_PRC_EFFTMsg prcEff = query.getDsContrPrcEffForUpdate(glblCmpyCd, logicalRemovePrcEffPk);
            if (prcEff == null) {
                continue;
            }
            S21ApiTBLAccessor.logicalRemove(prcEff);

            if (baseChrgFlg.equals(ZYPConstant.FLG_OFF_N)) {
                DS_CONTR_PRC_EFF_MTRTMsgArray prcEffMtrTMsgArray = query.getPrcEffTMsgArray(glblCmpyCd, prcEff.dsContrPrcEffPk.getValue(), prcEff.dsContrBllgMtrPk.getValue());
                for (int peMtrCnt = 0; peMtrCnt < prcEffMtrTMsgArray.getValidCount(); peMtrCnt++) {
                    S21ApiTBLAccessor.logicalRemove(prcEffMtrTMsgArray.no(peMtrCnt));
                }
            }

            DS_CONTR_BLLG_SCHD_SMRYTMsgArray topSchdTMsgArray = query.getDsContrBllgSchdSmryTMsgArray(glblCmpyCd, prcEff.dsContrPrcEffPk.getValue());
            for (int topSchdCnt = 0; topSchdCnt < topSchdTMsgArray.getValidCount(); topSchdCnt++) {
                S21ApiTBLAccessor.logicalRemove(topSchdTMsgArray.no(topSchdCnt));
            }
        }
    }
    // add start 2021/04/05 QC#58177-2
    private static boolean isNotChangedMtrAmtRate(SVC_CR_REBILTMsg crRebilTMsg, DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg, DS_CONTRTMsg dsContrTMsg) {
        if (crRebilTMsg != null && ZYPCommonFunc.hasValue(crRebilTMsg.custIncdtId) 
                && dsContrBllgMtrTMsg != null && ZYPCommonFunc.hasValue(dsContrBllgMtrTMsg.dsContrBllgMtrPk)
                && dsContrTMsg != null && ZYPCommonFunc.hasValue(dsContrTMsg.dsContrCatgCd)) {

            BigDecimal count = null;

            if (DS_CONTR_CATG.REGULAR.equals(dsContrTMsg.dsContrCatgCd.getValue())) {
                count = NSZC0530Query.getInstance().countChangeMtrAmtRate(crRebilTMsg.glblCmpyCd.getValue(), crRebilTMsg.custIncdtId.getValue(), dsContrBllgMtrTMsg.dsContrBllgMtrPk.getValue());
            } else if (DS_CONTR_CATG.AGGREGATE.equals(dsContrTMsg.dsContrCatgCd.getValue()))  {
                count = NSZC0530Query.getInstance().countChangeMtrAmtRate4Agg(crRebilTMsg.glblCmpyCd.getValue(), crRebilTMsg.custIncdtId.getValue(), dsContrBllgMtrTMsg.dsContrBllgMtrPk.getValue());
            }

            if (count != null && count.compareTo(BigDecimal.ZERO) > 0) {
                return false;
            }

        } else {
            return false;
        }

        return true;
    }
    private static boolean isChangedBaseUnitAmt(SVC_CR_REBILTMsg crRebilTMsg, BigDecimal dsContrDtlPk, DS_CONTRTMsg dsContrTMsg) {
        if (crRebilTMsg != null && ZYPCommonFunc.hasValue(crRebilTMsg.custIncdtId) 
                && ZYPCommonFunc.hasValue(dsContrDtlPk)
                && dsContrTMsg != null && ZYPCommonFunc.hasValue(dsContrTMsg.dsContrCatgCd)) {

            BigDecimal count = null;

            count = NSZC0530Query.getInstance().countChangeBaseUnitAmt(crRebilTMsg.glblCmpyCd.getValue(), crRebilTMsg.custIncdtId.getValue(), dsContrDtlPk);

            if (count != null && count.compareTo(BigDecimal.ZERO) > 0) {
                return true;
            } else {
                return false;
            }

        } else {
            return true;
        }
    }
    // add end 2021/04/05 QC#58177-2
}
