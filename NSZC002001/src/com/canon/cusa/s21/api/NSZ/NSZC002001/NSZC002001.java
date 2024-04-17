/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */

package com.canon.cusa.s21.api.NSZ.NSZC002001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parts.common.EZDPBigDecimalItem;
import parts.common.EZDPDateItem;
import parts.common.EZDPStringItem;
import parts.common.EZDTBigDecimalItem;
import parts.common.EZDTDateItem;
import parts.common.EZDTStringItem;
import parts.dbcommon.EZDDBRecordLockedException;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CCYTMsg;
import business.db.DS_COND_CONSTTMsg;
import business.db.DS_COND_CONSTTMsgArray;
import business.db.DS_SVC_CALL_TPTMsg;
import business.db.FSR_EVENTTMsg;
import business.db.FSR_VISITTMsg;
import business.db.FSR_VISITTMsgArray;
import business.db.S21_PSNTMsg;
import business.db.SVC_DISPT_EVENTTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_PBLM_SYMP_TPTMsg;
import business.db.SVC_PHYS_MTR_READTMsg;
import business.db.SVC_PHYS_MTR_READTMsgArray;
import business.db.SVC_TASKTMsg;
import business.db.SVC_TASK_HLDTMsg;
import business.db.SVC_TASK_HLDTMsgArray;
import business.parts.NSZC002001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC002001.constant.NSZC002001Constant;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetMblIntfcInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetMblIntfcInfoBean;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetRspTime;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SVC_CALL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MBL_INTFC_PROC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_DISPT_EVENT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPExtnNumbering;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Service Task Creation/Update API.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/25/2013   Fujitsu         J.Sakamoto      Create
 * 09/04/2013   SRA             E.Inada         Update          QC#1961
 * 03/17/2014   Fujitsu         M.Nakamura      Update          S21 NA Def#61.
 * 05/11/2015   SRA             N.Otsuji        Update          NA#ASCC
 * 07/28/2015   Fujitsu         T.Nakamura      Update          NA#ASCC CLICK
 * 08/28/2015   Fujitsu         T.Nakamura      Update          NA#ASCC CLICK
 * 10/14/2015   Fujitsu         T.Nakamura      Update          NA#ASCC CLICK Bug Fix
 * 12/18/2015   Hitachi         J.Kim           Update          CSA QC#1112
 * 01/15/2015   Hitachi         J.Kim           Update          CSA QC#3146
 * 02/12/2016   Hitachi         T.Iwamoto       Update          CSA QC#3727,2863,2560,4283
 * 03/03/2016   Hitachi         T.Iwamoto       Update          CSA QC#4766,4982
 * 03/28/2016   Hitachi         T.Iwamoto       Update          QC#6020
 * 04/05/2016   Hitachi         T.Iwamoto       Update          QC#4495
 * 04/13/2016   Hitachi         T.Iwamoto       Update          QC#5486
 * 05/20/2016   Hitachi         T.Iwamoto       Update          QC#8562
 * 06/09/2016   Hitachi         T.Iwamoto       Update          QC#9177
 * 07/01/2016   Hitachi         T.Iwamoto       Update          QC#11185
 * 2016/12/19   Hitachi         K.Kojima        Update          QC#15653
 * 2016/12/21   Hitachi         K.Kojima        Update          QC#16600
 * 2017/08/07   Hitachi         T.Tomita        Update          QC#19971
 * 2017/09/05   Hitachi         K.Kitachi       Update          QC#19971
 * 2017/10/02   Hitachi         T.Tomita        Update          QC#21273
 * 2018/01/09   Hitachi         U.Kim           Update          QC#19702
 * 2018/03/22   Hitachi         T.Tomita        Update          QC#18713
 * 2018/06/05   CITS            M.Naito         Update          QC#22449
 * 2018/08/03   Hitachi         A.Kohinata      Update          QC#26659
 * 2019/03/08   Hitachi         A.Kohinata      Update          QC#30686
 * 2019/04/19   Hitachi         A.Kohinata      Update          QC#31237
 * 2019/04/26   Hitachi         A.Kohinata      Update          QC#31213
 * 2019/07/15   Hitachi         K.Fujimoto      Update          QC#51361
 * 2019/07/31   Hitachi         K.Kitachi       Update          QC#52257
 * 2019/10/01   Hitachi         K.Fujimoto      Update          QC#53670
 * 2019/10/24   Hitachi         K.Fujimoto      Update          QC#53441
 * 2019/11/18   Hitachi         K.Fujimoto      Update          CSA QC#54391
 * 2022/02/10   Hitachi         D.Yoshida       Update          CSA QC#57338
 * 2022/04/11   Hitachi         K.Kitachi       Update          CSA QC#59899
 * </pre>
 */
public class NSZC002001 extends S21ApiCommonBase implements NSZC002001Constant {

    /*****************************************************************
     * Variable
     ****************************************************************/
    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /** ONBATCH_TYPE */
    private ONBATCH_TYPE onBatchType = null;

    /** create FSR Event : Credit Approval */
    private boolean isCreditApproval = true;

    // add start 2016/02/12 CSA Defect#2863
    /** input svcTaskStsCd */
    private String inputSvcTaskStsCd = null;

    // add end 2016/02/12 CSA Defect#2863

    // START 2018/01/09 U.Kim [QC#19702, ADD]
    /** Waiting Second for find by key */
    private int waitSecUpdTaskOther;

    // END 2018/01/09 U.Kim [QC#19702, ADD]

    /**
     * Constructor
     */
    public NSZC002001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * Execute API.
     * @param pMsg API Parametor
     * @param aOnBatchType ONLINE/BATCH
     */
    public void execute(NSZC002001PMsg pMsg, ONBATCH_TYPE aOnBatchType) {
        onBatchType = aOnBatchType;
        this.isCreditApproval = true;

        S21ApiMessageMap msgMap = new S21ApiMessageMap(pMsg);
        String processMode = pMsg.xxModeCd.getValue();
        // add start 2016/02/12 CSA Defect#2863
        inputSvcTaskStsCd = pMsg.svcTaskStsCd.getValue();
        // add end 2016/02/12 CSA Defect#2863

        // START 2018/01/09 U.Kim [QC#19702, ADD]
        setWaitSecUpdTaskOther(pMsg.glblCmpyCd.getValue());
        // END 2018/01/09 U.Kim [QC#19702, ADD]

        if (!checkInputParam(pMsg, msgMap, processMode)) {
            return;
        }
        if (!validateInputParam(pMsg, msgMap, processMode)) {
            return;
        }
        if (!checkCredit(pMsg, msgMap, processMode)) {
            return;
        }
        if (!executeProcess(pMsg, msgMap, processMode)) {
            return;
        }
    }

    private boolean checkInputParam(NSZC002001PMsg pMsg, S21ApiMessageMap msgMap, String processMode) {

        if (PROCESS_MODE_CANCEL.equals(processMode)) {
            if (!ZYPCommonFunc.hasValue(pMsg.svcTaskNum)) {
                msgMap.addXxMsgId(NSZM0082E);
            } else {
                return true;
            }
        }

        // **************************************************************
        // Mandatory Parameter Check
        // **************************************************************
        if (!ZYPCommonFunc.hasValue(pMsg.xxModeCd.getValue())) {
            msgMap.addXxMsgId(NSZM0003E);
        }
        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            msgMap.addXxMsgId(NSZM0001E);
        }
        // if (!ZYPCommonFunc.hasValue(pMsg.svcZnCd)) {
        // msgMap.addXxMsgId(NSZM0040E);
        // }
        // if (!ZYPCommonFunc.hasValue(pMsg.orgLayerNum)) {
        // msgMap.addXxMsgId(NSZM0384E);
        // }
        // if (!ZYPCommonFunc.hasValue(pMsg.orgCd)) {
        // msgMap.addXxMsgId(NSZM0385E);
        // }
        if (!ZYPCommonFunc.hasValue(pMsg.firstProdCtrlCd)) {
            msgMap.addXxMsgId(NSZM0042E);
        }
        // if (!ZYPCommonFunc.hasValue(pMsg.svcCallPrtyCd)) {
        // msgMap.addXxMsgId(NSZM0043E);
        // }
        if (!ZYPCommonFunc.hasValue(pMsg.mdlNm)) {
            msgMap.addXxMsgId(NSZM0044E);
        }
        // NA#ASCC CLICK DEL START
        // if (!ZYPCommonFunc.hasValue(pMsg.mdlGrpNm)) {
        // msgMap.addXxMsgId(NSZM0045E);
        // }
        // NA#ASCC CLICK DEL END
        if (!ZYPCommonFunc.hasValue(pMsg.shipToCustCd)) {
            msgMap.addXxMsgId(NSZM0047E);
        }
        // if (!ZYPCommonFunc.hasValue(pMsg.custTelNum)) {
        // msgMap.addXxMsgId(NSZM0048E);
        // }
        // FW checks date format
        // if (ZYPCommonFunc.hasValue(pMsg.custPoDt)) {
        // if (!ZYPDateUtil.checkDate(pMsg.custPoDt.getValue())) {
        // msgMap.addXxMsgId(NSZM0566E);
        // }
        // }
        if (!ZYPCommonFunc.hasValue(pMsg.dsSvcCallTpCd)) {
            msgMap.addXxMsgId(NSZM0049E);
        }
        if (!ZYPCommonFunc.hasValue(pMsg.svcBillTpCd)) {
            msgMap.addXxMsgId(NSZM0050E);
        }
        // if (!ZYPCommonFunc.hasValue(pMsg.svcPblmSympTpCd)) {
        // msgMap.addXxMsgId(NSZM0051E);
        // }
        if (ZYPCommonFunc.hasValue(pMsg.custAvalFromHourMn)) {
            if (!checkTimeFormat(pMsg.custAvalFromHourMn.getValue())) {
                msgMap.addXxMsgId(NSZM0303E);
            }
        }
        if (ZYPCommonFunc.hasValue(pMsg.custAvalToHourMn)) {
            if (!checkTimeFormat(pMsg.custAvalToHourMn.getValue())) {
                msgMap.addXxMsgId(NSZM0304E);
            }
        }
        if (!ZYPCommonFunc.hasValue(pMsg.svcTaskRcvDt)) {
            msgMap.addXxMsgId(NSZM0053E);
        }
        if (!ZYPCommonFunc.hasValue(pMsg.svcTaskRcvTm) || !checkTimeFormat(pMsg.svcTaskRcvTm.getValue())) {
            msgMap.addXxMsgId(NSZM0054E);
        }
        // if (!ZYPCommonFunc.hasValue(pMsg.svcTaskRcvTz)) {
        // msgMap.addXxMsgId(NSZM0055E);
        // }
        if (!ZYPCommonFunc.hasValue(pMsg.machDownFlg)) {
            msgMap.addXxMsgId(NSZM0281E);
        }
        if (!ZYPCommonFunc.hasValue(pMsg.prtChrgFlg)) {
            msgMap.addXxMsgId(NSZM0057E);
        }
        if (!ZYPCommonFunc.hasValue(pMsg.svcLborChrgFlg)) {
            msgMap.addXxMsgId(NSZM0058E);
        }
        // if (!ZYPCommonFunc.hasValue(pMsg.svcLborUnitAmt)) {
        // msgMap.addXxMsgId(NSZM0060E);
        // }
        // if (!ZYPCommonFunc.hasValue(pMsg.svcLborTaxRate)) {
        // msgMap.addXxMsgId(NSZM0063E);
        // }
        // if (!ZYPCommonFunc.hasValue(pMsg.svcLborDealTaxAmt)) {
        // msgMap.addXxMsgId(NSZM0064E);
        // }
        // if (!ZYPCommonFunc.hasValue(pMsg.svcLborFuncTaxAmt)) {
        // msgMap.addXxMsgId(NSZM0065E);
        // }
        // if (!ZYPCommonFunc.hasValue(pMsg.svcLborDiscRate)) {
        // msgMap.addXxMsgId(NSZM0066E);
        // }
        // if (!ZYPCommonFunc.hasValue(pMsg.svcLborDealDiscAmt)) {
        // msgMap.addXxMsgId(NSZM0067E);
        // }
        // if (!ZYPCommonFunc.hasValue(pMsg.svcLborFuncDiscAmt)) {
        // msgMap.addXxMsgId(NSZM0068E);
        // }
        // if (!ZYPCommonFunc.hasValue(pMsg.origInvCcyCd)) {
        // msgMap.addXxMsgId(NSZM0069E);
        // }
        // if (!ZYPCommonFunc.hasValue(pMsg.invCcyCd)) {
        // msgMap.addXxMsgId(NSZM0070E);
        // }
        // if (!ZYPCommonFunc.hasValue(pMsg.ccyExchRate)) {
        // msgMap.addXxMsgId(NSZM0071E);
        // }
        // if (ZYPCommonFunc.hasValue(pMsg.svcCmntTxt)) {
        // if (!ZYPCommonFunc.hasValue(pMsg.svcMemoTpCd)) {
        // msgMap.addXxMsgId(NSZM0072E);
        // }
        // }
        // NA#ASCC CLICK Del Start
        // if (!ZYPCommonFunc.hasValue(pMsg.svcTaskTpCd)) {
        // msgMap.addXxMsgId(NSZM0561E);
        // }
        // if (!ZYPCommonFunc.hasValue(pMsg.firstSvcTaskFlg)) {
        // msgMap.addXxMsgId(NSZM0562E);
        // }
        // if (!ZYPCommonFunc.hasValue(pMsg.svcCallRqstOwnrTocCd)) {
        // msgMap.addXxMsgId(NSZM0546E);
        // }
        // if (!ZYPCommonFunc.hasValue(pMsg.fsrEventExeUsrId)) {
        // msgMap.addXxMsgId(NSZM0563E);
        // }
        // NA#ASCC CLICK Del End
        // NA#ASCC CLICK ADD Start
        if (ZYPCommonFunc.hasValue(pMsg.erlStartTs)) {
            if (!chkTimeFormatTs(pMsg.erlStartTs.getValue())) {
                msgMap.addXxMsgId(NSZM0604E);
            }
        }
        if (ZYPCommonFunc.hasValue(pMsg.lateStartTs)) {
            if (!chkTimeFormatTs(pMsg.lateStartTs.getValue())) {
                msgMap.addXxMsgId(NSZM0605E);
            }
        }
        // NA#ASCC CLICK ADD End

        // **************************************************************
        // Parameter Check
        // **************************************************************
        // NA#ASCC CLICK Add Start
        if (PROCESS_MODE_SAVE.equals(processMode) || PROCESS_MODE_SUBMIT.equals(processMode)) {

            if (!ZYPCommonFunc.hasValue(pMsg.firstSvcTaskFlg)) {
                msgMap.addXxMsgId(NSZM0562E);
            }
        }
        if (PROCESS_MODE_SAVE.equals(processMode)
                || PROCESS_MODE_SUBMIT.equals(processMode)
                || PROCESS_MODE_UPDATE.equals(processMode)
                || PROCESS_MODE_CANCEL.equals(processMode)
        ) {

            if (!ZYPCommonFunc.hasValue(pMsg.fsrEventExeUsrId)) {
                msgMap.addXxMsgId(NSZM0563E);
            }
        }
        // NA#ASCC CLICK Add End

        if (PROCESS_MODE_SAVE.equals(processMode)) {
            DS_SVC_CALL_TPTMsg tMsg = new DS_SVC_CALL_TPTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dsSvcCallTpCd, pMsg.dsSvcCallTpCd);

            tMsg = (DS_SVC_CALL_TPTMsg) S21ApiTBLAccessor.findByKey(tMsg);

            if (tMsg != null && ZYPConstant.FLG_ON_Y.equals(tMsg.svcIstlReqFlg.getValue())) {
                if (!ZYPCommonFunc.hasValue(pMsg.soNum) && !ZYPCommonFunc.hasValue(pMsg.serNum)) {
                    msgMap.addXxMsgId(NSZM0307E);
                }
            }
            if (ZYPCommonFunc.hasValue(pMsg.svcMachMstrPk)) {

                if (!ZYPCommonFunc.hasValue(pMsg.custMachCtrlNum)) {
                    msgMap.addXxMsgId(NSZM0075E);
                }
                if (!ZYPCommonFunc.hasValue(pMsg.serNum)) {
                    msgMap.addXxMsgId(NSZM0012E);
                }
            }
            if (ZYPCommonFunc.hasValue(pMsg.svcTaskStsCd)) {
                if (!SVC_TASK_STS.SAVED.equals(pMsg.svcTaskStsCd.getValue())) {
                    msgMap.addXxMsgId(NSZM0077E);
                }
            } else {
                ZYPEZDItemValueSetter.setValue(pMsg.svcTaskStsCd, SVC_TASK_STS.SAVED);
            }
            if (!ZYPCommonFunc.hasValue(pMsg.svcTaskRcvDt)) {
                msgMap.addXxMsgId(NSZM0053E);
            }
            if (!ZYPCommonFunc.hasValue(pMsg.svcTaskRcvTm)) {
                msgMap.addXxMsgId(NSZM0054E);
            }
            // if (!ZYPCommonFunc.hasValue(pMsg.svcTaskRcvTz)) {
            // msgMap.addXxMsgId(NSZM0055E);
            // }

        } else if (PROCESS_MODE_SUBMIT.equals(processMode) || PROCESS_MODE_DISPATCH.equals(processMode)) {
            DS_SVC_CALL_TPTMsg tMsg = new DS_SVC_CALL_TPTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dsSvcCallTpCd, pMsg.dsSvcCallTpCd);

            tMsg = (DS_SVC_CALL_TPTMsg) S21ApiTBLAccessor.findByKey(tMsg);

            if (tMsg != null && ZYPConstant.FLG_ON_Y.equals(tMsg.svcIstlReqFlg.getValue())) {
                if (!ZYPCommonFunc.hasValue(pMsg.soNum) && !ZYPCommonFunc.hasValue(pMsg.serNum)) {
                    msgMap.addXxMsgId(NSZM0307E);
                }
            }
            // NA#ASCC CLICK Del Start
//            if (tMsg != null && ZYPConstant.FLG_ON_Y.equals(tMsg.svcRcllFlg.getValue())) {
            // if (ZYPCommonFunc.hasValue(pMsg.svcMachMstrPk)) {
            //
//                    ZYPEZDItemValueSetter.setValue(pMsg.svcLborUnitAmt, BigDecimal.ZERO);
//                    ZYPEZDItemValueSetter.setValue(pMsg.svcLborUnitHrsAot, BigDecimal.ZERO);
//                    ZYPEZDItemValueSetter.setValue(pMsg.svcLborDealAmt, BigDecimal.ZERO);
//                    ZYPEZDItemValueSetter.setValue(pMsg.svcLborFuncAmt, BigDecimal.ZERO);
//                    ZYPEZDItemValueSetter.setValue(pMsg.svcLborFuncDiscAmt, BigDecimal.ZERO);
//                    ZYPEZDItemValueSetter.setValue(pMsg.svcLborDealDiscAmt, BigDecimal.ZERO);
            // } else {
            // msgMap.addXxMsgId(NSZM0074E);
            // }
            // }
            // NA#ASCC CLICK Del End

            if (ZYPCommonFunc.hasValue(pMsg.svcMachMstrPk)) {

                if (!ZYPCommonFunc.hasValue(pMsg.custMachCtrlNum)) {
                    msgMap.addXxMsgId(NSZM0075E);
                }
                if (!ZYPCommonFunc.hasValue(pMsg.serNum)) {
                    msgMap.addXxMsgId(NSZM0012E);
                }
            }
            if (!ZYPCommonFunc.hasValue(pMsg.svcTaskRcvDt)) {
                msgMap.addXxMsgId(NSZM0053E);
            }
            if (!ZYPCommonFunc.hasValue(pMsg.svcTaskRcvTm)) {
                msgMap.addXxMsgId(NSZM0054E);
            }
            // if (!ZYPCommonFunc.hasValue(pMsg.svcTaskRcvTz)) {
            // msgMap.addXxMsgId(NSZM0055E);
            // }
        } else if (PROCESS_MODE_UPDATE.equals(processMode)) {
            if (!ZYPCommonFunc.hasValue(pMsg.svcTaskNum)) {
                msgMap.addXxMsgId(NSZM0082E);
            }

            DS_SVC_CALL_TPTMsg tMsg = new DS_SVC_CALL_TPTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.dsSvcCallTpCd, pMsg.dsSvcCallTpCd);

            tMsg = (DS_SVC_CALL_TPTMsg) S21ApiTBLAccessor.findByKey(tMsg);

            if (tMsg != null && ZYPConstant.FLG_ON_Y.equals(tMsg.svcIstlReqFlg.getValue())) {
                if (!ZYPCommonFunc.hasValue(pMsg.soNum) && !ZYPCommonFunc.hasValue(pMsg.serNum)) {
                    msgMap.addXxMsgId(NSZM0307E);
                }
            }
            // NA#ASCC CLICK Del Start
//            if (tMsg != null && ZYPConstant.FLG_ON_Y.equals(tMsg.svcRcllFlg.getValue())) {
            // if (ZYPCommonFunc.hasValue(pMsg.svcMachMstrPk)) {
            //
//                    ZYPEZDItemValueSetter.setValue(pMsg.svcLborUnitAmt, BigDecimal.ZERO);
//                    ZYPEZDItemValueSetter.setValue(pMsg.svcLborUnitHrsAot, BigDecimal.ZERO);
//                    ZYPEZDItemValueSetter.setValue(pMsg.svcLborDealAmt, BigDecimal.ZERO);
//                    ZYPEZDItemValueSetter.setValue(pMsg.svcLborFuncAmt, BigDecimal.ZERO);
//                    ZYPEZDItemValueSetter.setValue(pMsg.svcLborFuncDiscAmt, BigDecimal.ZERO);
//                    ZYPEZDItemValueSetter.setValue(pMsg.svcLborDealDiscAmt, BigDecimal.ZERO);
            //
            // } else {
            // msgMap.addXxMsgId(NSZM0074E);
            // }
            // }
            // NA#ASCC CLICK Del End
            if (ZYPCommonFunc.hasValue(pMsg.svcMachMstrPk)) {

                if (!ZYPCommonFunc.hasValue(pMsg.custMachCtrlNum)) {
                    msgMap.addXxMsgId(NSZM0075E);
                }
                if (!ZYPCommonFunc.hasValue(pMsg.serNum)) {
                    msgMap.addXxMsgId(NSZM0012E);
                }
            }
            if (DS_SVC_CALL_TP.P_M.equals(pMsg.dsSvcCallTpCd.getValue())) {
                if (!ZYPCommonFunc.hasValue(pMsg.svcMachMstrPk)) {
                    msgMap.addXxMsgId(NSZM0074E);
                }
                // if (!ZYPCommonFunc.hasValue(pMsg.techCd)) {
                // msgMap.addXxMsgId(NSZM0052E);
                // }
                if (!checkTaskReceivedDate(pMsg, msgMap)) {
                    msgMap.addXxMsgId(NSZM0053E);
                }

            } else {
                if (!ZYPCommonFunc.hasValue(pMsg.svcTaskRcvDt)) {
                    msgMap.addXxMsgId(NSZM0053E);
                }
                if (!ZYPCommonFunc.hasValue(pMsg.svcTaskRcvTm)) {
                    msgMap.addXxMsgId(NSZM0054E);
                }
                // if (!ZYPCommonFunc.hasValue(pMsg.svcTaskRcvTz)) {
                // msgMap.addXxMsgId(NSZM0055E);
                // }
            }
        } else if (PROCESS_MODE_PM.equals(processMode)) {
            if (!ZYPCommonFunc.hasValue(pMsg.svcMachMstrPk)) {
                msgMap.addXxMsgId(NSZM0074E);
            }
            if (DS_SVC_CALL_TP.P_M.equals(pMsg.dsSvcCallTpCd.getValue())) {
                // if (!ZYPCommonFunc.hasValue(pMsg.techCd)) {
                // msgMap.addXxMsgId(NSZM0052E);
                // }
            } else {
                msgMap.addXxMsgId(NSZM0039E);
            }
            // if (!ZYPCommonFunc.hasValue(pMsg.techCd)) {
            // msgMap.addXxMsgId(NSZM0052E);
            // }
            if (!checkTaskReceivedDate(pMsg, msgMap)) {
                msgMap.addXxMsgId(NSZM0053E);
            }
        } else {
            msgMap.addXxMsgId(NSZM0004E);
        }

        msgMap.flush();
        if (msgMap.getMsgMgr().getXxMsgIdListSize() == 0) {
            return true;
        }
        return false;
    }

    private boolean validateInputParam(NSZC002001PMsg pMsg, S21ApiMessageMap msgMap, String processMode) {

        // mod start 2015/12/18 CSA Defect#1112
        if (!PROCESS_MODE_CANCEL.equals(processMode)) {

            // **************************************************************
            // Parameter Check
            // **************************************************************
            if (ZYPCommonFunc.hasValue(pMsg.soNum) && !ZYPCommonFunc.hasValue(pMsg.svcMachMstrPk)) {
                existsSoNum(pMsg, msgMap);
            }

            // **************************************************************
            // Check Product Control Code
            // **************************************************************
            existsFirstProdCtrlCd(pMsg, msgMap);

            // **************************************************************
            // Symptom Name
            // **************************************************************
            if (ZYPCommonFunc.hasValue(pMsg.svcPblmSympTpCd)) {
                SVC_PBLM_SYMP_TPTMsg tMsg = new SVC_PBLM_SYMP_TPTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.svcPblmSympTpCd, pMsg.svcPblmSympTpCd);

                tMsg = (SVC_PBLM_SYMP_TPTMsg) S21ApiTBLAccessor.findByKey(tMsg);

                if (tMsg == null) {
                    msgMap.addXxMsgId(NSZM0285E);
                }
            }

            // **************************************************************
            // Currency
            // **************************************************************
            if (ZYPCommonFunc.hasValue(pMsg.invCcyCd)) {
                CCYTMsg tMsg = new CCYTMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.ccyCd, pMsg.invCcyCd);

                tMsg = (CCYTMsg) S21ApiTBLAccessor.findByKey(tMsg);

                if (tMsg == null) {
                    msgMap.addXxMsgId(NSZM0286E);
                }
            }
        }
        // mod end 2015/12/18 CSA Defect#1112

        // **************************************************************
        // Get current SVC_TASK
        // **************************************************************
        SVC_TASKTMsg currentSvcTask = null;
        if (ZYPCommonFunc.hasValue(pMsg.svcTaskNum)) {
            currentSvcTask = getSvcTask(pMsg, msgMap);
            if (currentSvcTask != null) {
                // **************************************************************
                // Function // TODO
                // **************************************************************
                String currSvcTaskStsCd = currentSvcTask.svcTaskStsCd.getValue();
                String pMsgSvcTaskStsCd = pMsg.svcTaskStsCd.getValue();
                if (currSvcTaskStsCd != null && pMsgSvcTaskStsCd != null && !currSvcTaskStsCd.equals(pMsgSvcTaskStsCd)) {
                    if (!hasFunction(pMsg.glblCmpyCd.getValue(), pMsg.fsrEventExeUsrId.getValue(), pMsg.svcTaskStsCd.getValue())) {
                        msgMap.addXxMsgId(NSZM0568E);
                    }
                }
            }
        }

        // NA#ASCC CLICK Del Start
//        // **************************************************************
        // // Service Call Type
//        // **************************************************************
//        if (ZYPCommonFunc.hasValue(pMsg.dsSvcCallTpCd) && ZYPCommonFunc.hasValue(pMsg.svcTaskTpCd)) {
//            DS_SVC_CALL_TPTMsg tMsg = (DS_SVC_CALL_TPTMsg) ZYPCodeDataUtil.findByCode("DS_SVC_CALL_TP", pMsg.glblCmpyCd.getValue(), pMsg.dsSvcCallTpCd.getValue());
        // if (tMsg == null) {
        // msgMap.addXxMsgId(NSZM0049E);
        // } else {
        // String pMsgSvcTaskTpCd = pMsg.svcTaskTpCd.getValue();
        // String tMsgSvcTaskTpCd = tMsg.svcTaskTpCd.getValue();
        // if (tMsgSvcTaskTpCd == null && pMsgSvcTaskTpCd == null) {
//                } else if (tMsgSvcTaskTpCd != null && pMsgSvcTaskTpCd != null && tMsgSvcTaskTpCd.equals(pMsgSvcTaskTpCd)) {
        // } else {
        // msgMap.addXxMsgId(NSZM0567E);
        // }
        // }
        // }
        // NA#ASCC CLICK Del End
        // NA#ASCC CLICK DEL Start
//        // **************************************************************
        // // Service Task Type
//        // **************************************************************
        // if (ZYPCommonFunc.hasValue(pMsg.svcTaskTpCd)) {
//            if (!ZYPCodeDataUtil.hasCodeValue("SVC_TASK_TP", pMsg.glblCmpyCd.getValue(), pMsg.svcTaskTpCd.getValue())) {
        // msgMap.addXxMsgId(NSZM0564E);
        // }
        // }
//        // **************************************************************
        // // Service Call Request Owner TOC Code
//        // **************************************************************
        // if (ZYPCommonFunc.hasValue(pMsg.svcCallRqstOwnrTocCd)) {
        // TOCTMsg tMsg = new TOCTMsg();
//            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
//            ZYPEZDItemValueSetter.setValue(tMsg.tocCd, pMsg.svcCallRqstOwnrTocCd);
        // tMsg = (TOCTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        // if (tMsg == null) {
        // msgMap.addXxMsgId(NSZM0554E);
        // }
        // }
        // NA#ASCC CLICK DEL End
        // NA#ASCC CLICK ADD Start
        if (ZYPCommonFunc.hasValue(pMsg.svcBrMgrPsnCd)) {

            S21_PSNTMsg s21PsnTMsg = getS21Psn(pMsg.glblCmpyCd.getValue(), pMsg.svcBrMgrPsnCd.getValue());

            if (s21PsnTMsg == null) {

                msgMap.addXxMsgId(NSZM0596E);

            } else if (ZYPConstant.FLG_ON_Y.equals(s21PsnTMsg.delFlg.getValue())) {

                msgMap.addXxMsgId(NSZM0590E);
            }

        }

        if (ZYPCommonFunc.hasValue(pMsg.svcTrtyMgrPsnCd)) {

            S21_PSNTMsg s21PsnTMsg = getS21Psn(pMsg.glblCmpyCd.getValue(), pMsg.svcTrtyMgrPsnCd.getValue());

            if (s21PsnTMsg == null) {

                msgMap.addXxMsgId(NSZM0598E);

            } else if (ZYPConstant.FLG_ON_Y.equals(s21PsnTMsg.delFlg.getValue())) {

                msgMap.addXxMsgId(NSZM0600E);
            }
        }

        if (ZYPCommonFunc.hasValue(pMsg.svcTeamMgrPsnCd)) {

            S21_PSNTMsg s21PsnTMsg = getS21Psn(pMsg.glblCmpyCd.getValue(), pMsg.svcTeamMgrPsnCd.getValue());

            if (s21PsnTMsg == null) {

                msgMap.addXxMsgId(NSZM0599E);

            } else if (ZYPConstant.FLG_ON_Y.equals(s21PsnTMsg.delFlg.getValue())) {

                msgMap.addXxMsgId(NSZM0601E);
            }
        }
        // NA#ASCC CLICK ADD End

        // del start 2016/01/15 CSA Defect#3146
        //// **************************************************************
        // // FSR Event Execution User ID
        //// **************************************************************
        // if (ZYPCommonFunc.hasValue(pMsg.fsrEventExeUsrId)) {
        // AUTH_PSNTMsg tMsg = new AUTH_PSNTMsg();
        // tMsg.setSQLID("053");
        //    tMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        //    tMsg.setConditionValue("usrNm01", pMsg.fsrEventExeUsrId.getValue());
        //    AUTH_PSNTMsgArray tMsgArray = (AUTH_PSNTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
        // if (tMsgArray.length() == 0) {
        // msgMap.addXxMsgId(NSZM0565E);
        // }
        // }
        // del end 2016/01/15 CSA Defect#3146

        // **************************************************************
        // Parameter Check
        // **************************************************************
        if (PROCESS_MODE_SAVE.equals(processMode)) {
            if (ZYPCommonFunc.hasValue(pMsg.svcTaskNum) && currentSvcTask != null) {
                removeRecord(pMsg.glblCmpyCd, pMsg.svcTaskNum, msgMap, currentSvcTask);
                currentSvcTask = null;
            } else {
                String svcTaskNum = ZYPExtnNumbering.getUniqueID(pMsg.glblCmpyCd.getValue(), "SVC_TS_NUM");
                ZYPEZDItemValueSetter.setValue(pMsg.svcTaskNum, svcTaskNum);
            }
            if (ZYPCommonFunc.hasValue(pMsg.svcMachMstrPk)) {
                existsMachMstr(pMsg, msgMap);
            }
            if (!ZYPCommonFunc.hasValue(pMsg.svcMachMstrPk) && !ZYPCommonFunc.hasValue(pMsg.soNum)) {
                existsShipToCustCd(pMsg, msgMap);
            }
            if (ZYPCommonFunc.hasValue(pMsg.techCd)) {
                existsTechMstr(pMsg, msgMap);
            }
            if (isFutureTaskReceivedDate(pMsg, msgMap)) {
                msgMap.addXxMsgId(NSZM0085E);
            }
        } else if (PROCESS_MODE_SUBMIT.equals(processMode)) {
            if (ZYPCommonFunc.hasValue(pMsg.svcTaskNum) && currentSvcTask != null) {
                removeRecord(pMsg.glblCmpyCd, pMsg.svcTaskNum, msgMap, currentSvcTask);
                currentSvcTask = null;
            } else {
                String svcTaskNum = ZYPExtnNumbering.getUniqueID(pMsg.glblCmpyCd.getValue(), "SVC_TS_NUM");
                ZYPEZDItemValueSetter.setValue(pMsg.svcTaskNum, svcTaskNum);
            }

            if (ZYPCommonFunc.hasValue(pMsg.svcMachMstrPk)) {
                existsMachMstr(pMsg, msgMap);
            }
            if (!ZYPCommonFunc.hasValue(pMsg.svcMachMstrPk) && !ZYPCommonFunc.hasValue(pMsg.soNum)) {
                existsShipToCustCd(pMsg, msgMap);
            }
            if (ZYPCommonFunc.hasValue(pMsg.techCd)) {
                existsTechMstr(pMsg, msgMap);
            }
            if (isFutureTaskReceivedDate(pMsg, msgMap)) {
                msgMap.addXxMsgId(NSZM0085E);
            }
        } else if (PROCESS_MODE_UPDATE.equals(processMode)) {
            setOrigSvcCallPrtyCd(pMsg, msgMap);

            if (ZYPCommonFunc.hasValue(pMsg.svcMachMstrPk)) {
                existsMachMstr(pMsg, msgMap);
            }
            if (!ZYPCommonFunc.hasValue(pMsg.svcMachMstrPk) && !ZYPCommonFunc.hasValue(pMsg.soNum)) {
                existsShipToCustCd(pMsg, msgMap);
            }
            // START 2019/10/01 K.Fujimoto [QC#53670,DEL]
            // if (ZYPCommonFunc.hasValue(pMsg.techCd)) {
            // existsTechMstr(pMsg, msgMap);
            // }
            // END 2019/10/01 K.Fujimoto [QC#53670,DEL]
            if (isFutureTaskReceivedDate(pMsg, msgMap)) {
                msgMap.addXxMsgId(NSZM0085E);
            }
            if (currentSvcTask != null) {
                setSvcCallPrtyChngDt(pMsg, msgMap, currentSvcTask);
            }
        } else if (PROCESS_MODE_PM.equals(processMode)) {
            String svcTaskNum = ZYPExtnNumbering.getUniqueID(pMsg.glblCmpyCd.getValue(), "SVC_TS_NUM");
            ZYPEZDItemValueSetter.setValue(pMsg.svcTaskNum, svcTaskNum);
            existsMachMstr(pMsg, msgMap);
            if (ZYPCommonFunc.hasValue(pMsg.techCd)) {
                existsTechMstr(pMsg, msgMap);
            }
            if (isFutureTaskReceivedDate(pMsg, msgMap)) {
                msgMap.addXxMsgId(NSZM0085E);
            }
        } else if (PROCESS_MODE_DISPATCH.equals(processMode)) {
            if (ZYPCommonFunc.hasValue(pMsg.svcTaskNum)) {
                setOrigSvcCallPrtyCd(pMsg, msgMap);

                if (currentSvcTask != null) {
                    setSvcCallPrtyChngDt(pMsg, msgMap, currentSvcTask);
                }
            } else {
                String svcTaskNum = ZYPExtnNumbering.getUniqueID(pMsg.glblCmpyCd.getValue(), "SVC_TS_NUM");
                ZYPEZDItemValueSetter.setValue(pMsg.svcTaskNum, svcTaskNum);
            }

            if (ZYPCommonFunc.hasValue(pMsg.svcMachMstrPk)) {
                existsMachMstr(pMsg, msgMap);
            }
            if (!ZYPCommonFunc.hasValue(pMsg.svcMachMstrPk) && !ZYPCommonFunc.hasValue(pMsg.soNum)) {
                existsShipToCustCd(pMsg, msgMap);
            }
            if (ZYPCommonFunc.hasValue(pMsg.techCd)) {
                existsTechMstr(pMsg, msgMap);
            }
            if (isFutureTaskReceivedDate(pMsg, msgMap)) {
                msgMap.addXxMsgId(NSZM0085E);
            }
        }

        msgMap.flush();
        if (msgMap.getMsgMgr().getXxMsgIdListSize() == 0) {
            return true;
        }
        return false;
    }

    private boolean existsSoNum(NSZC002001PMsg pMsg, S21ApiMessageMap msgMap) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("soNum", pMsg.soNum.getValue());
        param.put("mdseCd", pMsg.mdseCd.getValue());
        param.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        String result = (String) ssmBatchClient.queryObject("getSoNum", param);

        if (!pMsg.soNum.getValue().equals(result)) {
            msgMap.addXxMsgId(NSZM0087E);
            return false;
        }
        return true;
    }

    private boolean existsFirstProdCtrlCd(NSZC002001PMsg pMsg, S21ApiMessageMap msgMap) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("firstProdCtrlCd", pMsg.firstProdCtrlCd.getValue());

        List<Object> result = ssmBatchClient.queryObjectList("getFirstProdCtrlCd", param);

        if (result.size() == 0) {
            msgMap.addXxMsgId(NSZM0088E);
            return false;
        }
        return true;
    }

    private void setOrigSvcCallPrtyCd(NSZC002001PMsg pMsg, S21ApiMessageMap msgMap) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("svcTaskNum", pMsg.svcTaskNum.getValue());

        Map<String, String> result = (Map<String, String>) ssmBatchClient.queryObject("getSvcCallPrtyCd", param);

        String origSvcCallPrtyCd = result.get("ORIG_SVC_CALL_PRTY_CD");
        String svcCallPrtyCd = result.get("SVC_CALL_PRTY_CD");

        if (!pMsg.svcCallPrtyCd.getValue().equals(svcCallPrtyCd)) {
            if (origSvcCallPrtyCd == null) {
                ZYPEZDItemValueSetter.setValue(pMsg.origSvcCallPrtyCd, svcCallPrtyCd);
            }
        }
    }

    private boolean existsMachMstr(NSZC002001PMsg pMsg, S21ApiMessageMap msgMap) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("svcMachMstrPk", pMsg.svcMachMstrPk.getValue());
        param.put("custMachCtrlNum", pMsg.custMachCtrlNum.getValue());
        param.put("serNum", pMsg.serNum.getValue());
        param.put("mdseCd", pMsg.mdseCd.getValue());
        param.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);

        String result = (String) ssmBatchClient.queryObject("getSvcMachMstrPk", param);

        if (!pMsg.svcMachMstrPk.getValue().toPlainString().equals(result)) {
            msgMap.addXxMsgId(NSZM0288E);
            return false;
        }
        return true;
    }

    private boolean existsShipToCustCd(NSZC002001PMsg pMsg, S21ApiMessageMap msgMap) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("shipToCustCd", pMsg.shipToCustCd.getValue());

        String result = (String) ssmBatchClient.queryObject("getShipToCustCd", param);

        if (!pMsg.shipToCustCd.getValue().equals(result)) {
            msgMap.addXxMsgId(NSZM0084E);
            return false;
        }
        return true;
    }

    private boolean existsTechMstr(NSZC002001PMsg pMsg, S21ApiMessageMap msgMap) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("techCd", pMsg.techCd.getValue());
        // START 2016/12/16 K.Kojima [QC#15653,ADD]
        param.put("effDt", pMsg.svcTaskRcvDt.getValue());
        // END 2016/12/16 K.Kojima [QC#15653,ADD]
        // START 2016/12/21 K.Kojima [QC#15653,ADD]
        param.put("flgY", ZYPConstant.FLG_ON_Y);
        // END 2016/12/21 K.Kojima [QC#15653,ADD]

        String result = (String) ssmBatchClient.queryObject("getTechCd", param);

        if (!pMsg.techCd.getValue().equals(result)) {
            msgMap.addXxMsgId(NSZM0076E);
            return false;
        }
        return true;
    }

    private boolean checkTaskReceivedDate(NSZC002001PMsg pMsg, S21ApiMessageMap msgMap) {
        int count = 0;
        if (!ZYPCommonFunc.hasValue(pMsg.svcTaskRcvDt)) {
            count++;
        }
        if (!ZYPCommonFunc.hasValue(pMsg.svcTaskRcvTm)) {
            count++;
        }
        // if (!ZYPCommonFunc.hasValue(pMsg.svcTaskRcvTz)) {
        // count++;
        // }
        if (count == 0 || count == SVC_TASK_RCV_COLUMN_SIZE) {
            return true;
        }
        return false;
    }

    private boolean isFutureTaskReceivedDate(NSZC002001PMsg pMsg, S21ApiMessageMap msgMap) {
        // Calendar calendar =
        // Calendar.getInstance(TimeZone.getTimeZone(pMsg.svcTaskRcvTz.getValue()));
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();

        SimpleDateFormat formater = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = null;
        try {
            date = formater.parse(pMsg.svcTaskRcvDt.getValue() + pMsg.svcTaskRcvTm.getValue());
            if (date.before(currentDate)) {
                return false;
            }
        } catch (ParseException e) {
            msgMap.addXxMsgId(NSZM0046E);
        }
        return true;
    }

    private void setSvcCallPrtyChngDt(NSZC002001PMsg pMsg, S21ApiMessageMap msgMap, SVC_TASKTMsg currentSvcTask) {
        String inputPrtyCd = currentSvcTask.svcCallPrtyCd.getValue();
        String currentPrtyCd = pMsg.svcCallPrtyCd.getValue();

        if (!currentPrtyCd.equals(inputPrtyCd)) {
            // String currentDate =
            // ZYPDateUtil.getCurrentSystemTime("yyyyMMdd",
            // TimeZone.getTimeZone(pMsg.svcTaskRcvTz.getValue()));
            String currentDate = ZYPDateUtil.getCurrentSystemTime("yyyyMMdd");
            ZYPEZDItemValueSetter.setValue(pMsg.svcCallPrtyChngDt, currentDate);
        }
    }

    private boolean checkCredit(NSZC002001PMsg pMsg, S21ApiMessageMap msgMap, String processMode) {
        if (PROCESS_MODE_SAVE.equals(processMode)) {
            ZYPEZDItemValueSetter.setValue(pMsg.svcCrHldFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(pMsg.svcTaskStsCd, SVC_TASK_STS.SAVED);
        } else if (PROCESS_MODE_CANCEL.equals(processMode)) {
            ZYPEZDItemValueSetter.setValue(pMsg.svcTaskStsCd, SVC_TASK_STS.CANCELLED);
            ZYPEZDItemValueSetter.setValue(pMsg.svcCrHldFlg, ZYPConstant.FLG_OFF_N);
            removeSvcTaskHld(pMsg, msgMap, processMode);
        } else if (PROCESS_MODE_PM.equals(processMode)) {
            ZYPEZDItemValueSetter.setValue(pMsg.svcTaskStsCd, SVC_TASK_STS.SAVED);
            ZYPEZDItemValueSetter.setValue(pMsg.svcCrHldFlg, ZYPConstant.FLG_OFF_N);
        } else if (PROCESS_MODE_DISPATCH.equals(processMode)) {
            ZYPEZDItemValueSetter.setValue(pMsg.svcTaskStsCd, SVC_TASK_STS.OPEN);
            ZYPEZDItemValueSetter.setValue(pMsg.svcCrHldFlg, ZYPConstant.FLG_OFF_N);
            // NA#ASCC CLICK MOD Start
            // } else if (!needCreditCheck(pMsg, msgMap)) {
            // msgMap.flush();
            // } else if (creditCheck(pMsg, msgMap)) {
            // msgMap.flush();
            // if (msgMap.getMsgMgr().getXxMsgIdListSize() == 0) {
            // // no Hold
//                ZYPEZDItemValueSetter.setValue(pMsg.svcCrHldFlg, ZYPConstant.FLG_OFF_N);
//                ZYPEZDItemValueSetter.setValue(pMsg.svcTaskStsCd, SVC_TASK_STS.APPROVED);
            // }
            // } else {
            // msgMap.flush();
            // if (msgMap.getMsgMgr().getXxMsgIdListSize() == 0) {
            // // Hold
//                ZYPEZDItemValueSetter.setValue(pMsg.svcCrHldFlg, ZYPConstant.FLG_ON_Y);
//                ZYPEZDItemValueSetter.setValue(pMsg.svcTaskStsCd, SVC_TASK_STS.WAITING_FOR_CREDIT_APPROVAL);
            // }
        // add start 2019/07/15 CSA Defect#51361
        } else if (PROCESS_MODE_UPDATE.equals(processMode)) {
            if (!ZYPCommonFunc.hasValue(pMsg.svcTaskStsCd)) {
                ZYPEZDItemValueSetter.setValue(pMsg.svcTaskStsCd, SVC_TASK_STS.OPEN);
            }
            String svcCrHldFlg = ZYPConstant.FLG_OFF_N;
            FSR_VISITTMsgArray tMsgArray = getFsrVisit(pMsg.glblCmpyCd.getValue(), pMsg.svcTaskNum.getValue());
            if (tMsgArray.getValidCount() > 0) {
                FSR_VISITTMsg tMsg = tMsgArray.no(0);
                if (tMsg.fsrVisitStsCd.getValue().equals(SVC_TASK_STS.CREDIT_HOLD)) {
                    svcCrHldFlg = ZYPConstant.FLG_ON_Y;
                }
            }
            ZYPEZDItemValueSetter.setValue(pMsg.svcCrHldFlg, svcCrHldFlg);
        // add end 2019/07/15 CSA Defect#51361
        } else {
            msgMap.flush();
            ZYPEZDItemValueSetter.setValue(pMsg.svcCrHldFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(pMsg.svcTaskStsCd, SVC_TASK_STS.OPEN);
            // NA#ASCC CLICK MOD End
        }

        msgMap.flush();
        if (msgMap.getMsgMgr().getXxMsgIdListSize() == 0) {
            return true;
        }
        return false;
    }

    // NA#ASCC CLICK DEL Start
//    private boolean needCreditCheck(NSZC002001PMsg pMsg, S21ApiMessageMap msgMap) {
    //
    // SVC_TASKTMsg tMsg = new SVC_TASKTMsg();
//        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(tMsg.svcTaskNum, pMsg.svcTaskNum);
    //
    // tMsg = (SVC_TASKTMsg) S21ApiTBLAccessor.findByKey(tMsg);
    // if (tMsg == null) {
    // return true;
    // }
    //
//        BigDecimal previousAmt = subtract(tMsg.svcLborFuncAmt, tMsg.svcLborFuncDiscAmt);
//        BigDecimal currentAmt = subtract(pMsg.svcLborFuncAmt, pMsg.svcLborFuncDiscAmt);
    //
    // if (previousAmt.compareTo(currentAmt) < 0) {
    // return true;
    // }
    //
    // return false;
    // }
    //
//    private boolean creditCheck(NSZC002001PMsg pMsg, S21ApiMessageMap msgMap) {
    //
    // // QC#1961 Add Start
//        BigDecimal currentAmt = subtract(pMsg.svcLborFuncAmt, pMsg.svcLborFuncDiscAmt);
//        if (!ZYPCommonFunc.hasValue(currentAmt) || BigDecimal.ZERO.compareTo(currentAmt) == 0) {
    // isCreditApproval = false;
    // return true;
    // }
    // // QC#1961 Add End
    //
    // Map<String, String> param = new HashMap<String, String>();
    // param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
    // param.put("shipTo", pMsg.shipToCustCd.getValue());
    //
//        String billToCustCd = (String) ssmBatchClient.queryObject("getBillToCustCd", param);
    //
    // NMZC600001PMsg checkCreditPMsg = new NMZC600001PMsg();
//        ZYPEZDItemValueSetter.setValue(checkCreditPMsg.glblCmpyCd, pMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(checkCreditPMsg.billToCustCd, billToCustCd);
    // // ZYPEZDItemValueSetter.setValue(checkCreditPMsg.inProcAmt,
//        // pMsg.svcLborFuncAmt.getValue().add(pMsg.svcLborFuncDiscAmt.getValue()));
//        ZYPEZDItemValueSetter.setValue(checkCreditPMsg.inProcAmt, currentAmt);
//        ZYPEZDItemValueSetter.setValue(checkCreditPMsg.updKeyNum, pMsg.svcTaskNum);
//        ZYPEZDItemValueSetter.setValue(checkCreditPMsg.slsDt, ZYPDateUtil.getSalesDate());
//        ZYPEZDItemValueSetter.setValue(checkCreditPMsg.xxReadOnlyFlg, ZYPConstant.FLG_ON_Y);
    //
    // NMZC600001 creditBalanceCheck = new NMZC600001();
    // creditBalanceCheck.execute(checkCreditPMsg, onBatchType);
    //
    // if (checkCreditPMsg.xxMsgIdList.getValidCount() != 0) {
    // msgMap.addXxMsgId(NSZM0203E);
    // return false;
    // }
    //
    // String hldRsnCd = null;
//        if (NMZC600001.CR_CHK_REQ_TP_NO_CHECK.equals(checkCreditPMsg.crChkReqTpCd.getValue())) {
    // isCreditApproval = false;
    // return true;
//        } else if (NMZC600001.CR_CHK_REQ_TP_HOLD.equals(checkCreditPMsg.crChkReqTpCd.getValue())) {
    // hldRsnCd = SVC_TASK_HLD_RSN.CREDIT_PROFILE;
//        } else if (NMZC600001.CR_CHK_REQ_TP_CREDIT_LIMIT.equals(checkCreditPMsg.crChkReqTpCd.getValue())) {
    // BigDecimal amt = checkCreditPMsg.crBalAmt.getValue();
    // if (0 <= amt.signum()) {
    // return true;
    // } else {
    // hldRsnCd = SVC_TASK_HLD_RSN.CREDIT_CHECK_DATE_PAST;
    // }
    // }
    //
    // createSvcTaskHld(pMsg, msgMap, hldRsnCd);
    // return false;
    // }
    //
//    private void createSvcTaskHld(NSZC002001PMsg pMsg, S21ApiMessageMap msgMap, String hldRsnCd) {
//        BigDecimal pk = ZYPOracleSeqAccessor.getNumberBigDecimal("SVC_TASK_HLD_SQ");
    //
    // SVC_TASK_HLDTMsg svcTaskHldTMsg = new SVC_TASK_HLDTMsg();
//        ZYPEZDItemValueSetter.setValue(svcTaskHldTMsg.glblCmpyCd, pMsg.glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(svcTaskHldTMsg.svcTaskHldPk, pk);
//        ZYPEZDItemValueSetter.setValue(svcTaskHldTMsg.svcTaskNum, pMsg.svcTaskNum);
//        ZYPEZDItemValueSetter.setValue(svcTaskHldTMsg.svcTaskHldRsnCd, hldRsnCd);
//        ZYPEZDItemValueSetter.setValue(svcTaskHldTMsg.svcTaskHldDt, ZYPDateUtil.getSalesDate());
//        ZYPEZDItemValueSetter.setValue(svcTaskHldTMsg.svcTaskHldUsrId, pMsg.fsrEventExeUsrId);
//        ZYPEZDItemValueSetter.setValue(svcTaskHldTMsg.svcTaskRelFlg, ZYPConstant.FLG_OFF_N);
    //
    // S21ApiTBLAccessor.create(svcTaskHldTMsg);
//        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(svcTaskHldTMsg.getReturnCode())) {
    // msgMap.addXxMsgId(NSZM0203E);
    // }
    // }
    // NA#ASCC CLICK DEL End

    private void removeSvcTaskHld(NSZC002001PMsg pMsg, S21ApiMessageMap msgMap, String processMode) {
        Map<String, String> param = new HashMap<String, String>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("svcTaskNum", pMsg.svcTaskNum.getValue());
        List<BigDecimal> svcTaskHldPkList = ssmBatchClient.queryObjectList("getSvcTaskHld", param);

        if (0 < svcTaskHldPkList.size()) {
            for (BigDecimal svcTaskHldPk : svcTaskHldPkList) {
                SVC_TASK_HLDTMsg svcTaskHldTMsg = new SVC_TASK_HLDTMsg();
                ZYPEZDItemValueSetter.setValue(svcTaskHldTMsg.glblCmpyCd, pMsg.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(svcTaskHldTMsg.svcTaskHldPk, svcTaskHldPk);
                S21ApiTBLAccessor.logicalRemove(svcTaskHldTMsg);

                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(svcTaskHldTMsg.getReturnCode())) {
                    msgMap.addXxMsgId(NSZM0203E);
                    return;
                }
            }
        }
    }

    private boolean executeProcess(NSZC002001PMsg pMsg, S21ApiMessageMap msgMap, String processMode) {
        // add start 2016/02/12 CSA Defect#2863
        if (!releaseHold(pMsg, msgMap)) {
            msgMap.flush();
            return false;
        }
        // add end 2016/02/12 CSA Defect#2863

        createRecord(pMsg, msgMap, processMode);

        msgMap.flush();
        if (msgMap.getMsgMgr().getXxMsgIdListSize() == 0) {
            return true;
        }
        return false;
    }

    private void createRecord(NSZC002001PMsg pMsg, S21ApiMessageMap msgMap, String processMode) {
        if (!createSvcTaskRecord(pMsg, msgMap)) {
            return;
        }
        if (PROCESS_MODE_SUBMIT.equals(processMode)) {
            // if (!createFsrEventRecord(pMsg, msgMap,
            // SVC_DISPT_EVENT.OPEN)) {
            // return;
            // }
            if (isCreditApproval && SVC_TASK_STS.APPROVED.equals(pMsg.svcTaskStsCd.getValue())) {
                if (!createFsrEventRecord(pMsg, msgMap, SVC_DISPT_EVENT.CREDIT_APPROVAL)) {
                    return;
                }
            }
        } else if (PROCESS_MODE_UPDATE.equals(processMode)) {
            if (!createFsrEventRecord(pMsg, msgMap)) {
                return;
            }
            if (!createFsrEventRecord(pMsg, msgMap, SVC_DISPT_EVENT.UPDATE_TASK)) {
                return;
            }
        } else if (PROCESS_MODE_SAVE.equals(processMode)) {
        } else if (PROCESS_MODE_PM.equals(processMode)) {
        } else if (PROCESS_MODE_CANCEL.equals(processMode)) {
            // NA#ASCC CLICK MOD Start
//            if (!Canon0002(pMsg, msgMap, SVC_DISPT_EVENT.CANCELLED)) {
            // START 2019/10/24 K.Fujimoto [QC#53441, ADD]
            if (!updateInvldSvcMtrRead(pMsg, msgMap)) {
                return;
            }
            // END   2019/10/24 K.Fujimoto [QC#53441, ADD]
            if (!createFsrEventRecord(pMsg, msgMap, SVC_DISPT_EVENT.CANCEL)) {
                // NA#ASCC CLICK MOD End
                return;
            }
        } else if (PROCESS_MODE_DISPATCH.equals(processMode)) {
            // The coding is in createSvcTaskRecord method.
        }
        return;
    }

    private boolean createSvcTaskRecord(NSZC002001PMsg pMsg, S21ApiMessageMap msgMap) {
        boolean insertFlag = false;
        // SVC_TASK
        SVC_TASKTMsg tMsg = new SVC_TASKTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.svcTaskNum, pMsg.svcTaskNum);

        tMsg = (SVC_TASKTMsg) S21ApiTBLAccessor.findByKeyForUpdate(tMsg);

        if (tMsg == null) {
            tMsg = new SVC_TASKTMsg();
            insertFlag = true;
        }

        if (insertFlag || SVC_TASK_STS.SAVED.equals(tMsg.svcTaskStsCd.getValue())) {
            if (PROCESS_MODE_DISPATCH.equals(pMsg.xxModeCd.getValue())) {
                if (!createFsrEventRecord(pMsg, msgMap, SVC_DISPT_EVENT.OPEN)) {
                    return false;
                }
            }
        }

        setValueCompletion(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValueCompletion(tMsg.svcTaskNum, pMsg.svcTaskNum);
        setValueCompletion(tMsg.soNum, pMsg.soNum);
        // Add Start 2014/03/17 S21 NA Def#61.
        setValueCompletion(tMsg.rcvRptNum, pMsg.rcvRptNum);
        // Add End 2014/03/17 S21 NA Def#61.
        setValueCompletion(tMsg.orgLayerNum, pMsg.orgLayerNum);
        setValueCompletion(tMsg.orgCd, pMsg.orgCd);
        // mod start 2016/05/20 CSA Defect#8562
        if (!PROCESS_MODE_UPDATE.equals(pMsg.xxModeCd.getValue())) {
            setValueCompletion(tMsg.svcTaskStsCd, pMsg.svcTaskStsCd);
        }
        // mod end 2016/05/20 CSA Defect#8562
        setValueCompletion(tMsg.firstProdCtrlCd, pMsg.firstProdCtrlCd);
        setValueCompletion(tMsg.origSvcCallPrtyCd, pMsg.origSvcCallPrtyCd);
        setValueCompletion(tMsg.svcCallPrtyCd, pMsg.svcCallPrtyCd);
        setValueCompletion(tMsg.svcCallPrtyChngDt, pMsg.svcCallPrtyChngDt);
        setValueCompletion(tMsg.svcMachMstrPk, pMsg.svcMachMstrPk);
        setValueCompletion(tMsg.custMachCtrlNum, pMsg.custMachCtrlNum);
        setValueCompletion(tMsg.serNum, pMsg.serNum);
        setValueCompletion(tMsg.mdlNm, pMsg.mdlNm);
        setValueCompletion(tMsg.mdlGrpNm, pMsg.mdlGrpNm);
        setValueCompletion(tMsg.mdseCd, pMsg.mdseCd);
        setValueCompletion(tMsg.shipToCustCd, pMsg.shipToCustCd);
        setValueCompletion(tMsg.custTelNum, pMsg.custTelNum);
        setValueCompletion(tMsg.custTelExtnNum, pMsg.custTelExtnNum);
        setValueCompletion(tMsg.svcCustAttnTxt, pMsg.svcCustAttnTxt);
        setValueCompletion(tMsg.custEmlAddr, pMsg.custEmlAddr);
        setValueCompletion(tMsg.custPoNum, pMsg.custPoNum);
        setValueCompletion(tMsg.custPoDt, pMsg.custPoDt);
        // NA#ASCC CLICK ADD Start
        if (insertFlag && checkRecallTarget(pMsg)) {

            HashMap<String, Object> param = new HashMap<String, Object>();
            param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
            param.put("fromSvcCallTpCd", pMsg.dsSvcCallTpCd.getValue());
            param.put("svcRcllTrgtFlg", ZYPConstant.FLG_ON_Y);
            param.put("svcRcllFlg", ZYPConstant.FLG_ON_Y);

            List<Map<String, Object>> rcllCallTpList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getRcllCallTp", param);
            if (rcllCallTpList == null || rcllCallTpList.size() != 1) {

                msgMap.addXxMsgId(NSZM0592E);
                return false;
            }
            Map<String, Object> rcllSvcTpMap = rcllCallTpList.get(0);
            ZYPEZDItemValueSetter.setValue(pMsg.dsSvcCallTpCd, (String) rcllSvcTpMap.get("DS_SVC_CALL_TP_CD"));

        }
        // NA#ASCC CLICK ADD End
        setValueCompletion(tMsg.dsSvcCallTpCd, pMsg.dsSvcCallTpCd);
        setValueCompletion(tMsg.svcBillTpCd, pMsg.svcBillTpCd);
        setValueCompletion(tMsg.svcPblmSympTpCd, pMsg.svcPblmSympTpCd);
        setValueCompletion(tMsg.techCd, pMsg.techCd);
        // NA#ASCC CLICK ADD Start
        String techCd = null;
        if (ZYPCommonFunc.hasValue(pMsg.svcMachMstrPk)) {

            SVC_MACH_MSTRTMsg srchSvcMachMstrTMsg = new SVC_MACH_MSTRTMsg();
            srchSvcMachMstrTMsg.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
            srchSvcMachMstrTMsg.svcMachMstrPk.setValue(pMsg.svcMachMstrPk.getValue());
            SVC_MACH_MSTRTMsg svcMachMstrTMsg = (SVC_MACH_MSTRTMsg) S21ApiTBLAccessor.findByKey(srchSvcMachMstrTMsg);

            if (svcMachMstrTMsg != null) {

                if (ZYPCommonFunc.hasValue(svcMachMstrTMsg.reqTechCd)) {

                    techCd = svcMachMstrTMsg.reqTechCd.getValue();

                } else {

                    techCd = svcMachMstrTMsg.prfTechCd.getValue();
                }

                if (!ZYPCommonFunc.hasValue(svcMachMstrTMsg.fldSvcBrCd)) {

                    msgMap.addXxMsgId(NSZM0607E);
                    return false;
                }
            }
        }

        if (ZYPCommonFunc.hasValue(techCd)) {

            ZYPEZDItemValueSetter.setValue(tMsg.techCd, techCd);
        }
        // NA#ASCC CLICK ADD End
        setValueCompletion(tMsg.svcCrHldFlg, pMsg.svcCrHldFlg);
        setValueCompletion(tMsg.custAvalFromHourMn, pMsg.custAvalFromHourMn);
        setValueCompletion(tMsg.custAvalToHourMn, pMsg.custAvalToHourMn);
        setValueCompletion(tMsg.svcTaskRcvDt, pMsg.svcTaskRcvDt);
        setValueCompletion(tMsg.svcTaskRcvTm, pMsg.svcTaskRcvTm);
        setValueCompletion(tMsg.svcTaskRcvTz, pMsg.svcTaskRcvTz);
        // mod start 2016/02/12 CSA Defect#2863,2560
        if (SVC_TASK_STS.SCHEDULED.equals(inputSvcTaskStsCd) && !ZYPCommonFunc.hasValue(tMsg.svcTaskSchdDt) && !ZYPCommonFunc.hasValue(pMsg.svcTaskSchdDt)) {
            ZYPEZDItemValueSetter.setValue(tMsg.svcTaskSchdDt, ZYPDateUtil.getCurrentSystemTime(ZYPDateUtil.TYPE_YYYYMMDD));
            ZYPEZDItemValueSetter.setValue(tMsg.svcTaskSchdTm, S21StringUtil.subStringByLength(ZYPDateUtil.getCurrentSystemTime(FORMAT_SYS_TS), TIME_START_POS, TIME_END_POS));
        } else if (ZYPCommonFunc.hasValue(pMsg.svcTaskSchdDt)) {
            setValueCompletion(tMsg.svcTaskSchdDt, pMsg.svcTaskSchdDt);
            setValueCompletion(tMsg.svcTaskSchdTm, pMsg.svcTaskSchdTm);
        }
        // mod end 2016/02/12 CSA DesetSupplementValueItemValueSetter.setValue(tMsg.svcTaskCpltDt, pMsg.svcTaskCpltDt);
        setValueCompletion(tMsg.svcTaskCpltTm, pMsg.svcTaskCpltTm);
        setValueCompletion(tMsg.svcTaskCloDt, pMsg.svcTaskCloDt);
        setValueCompletion(tMsg.svcTaskCloTm, pMsg.svcTaskCloTm);
        // mod start 2016/02/12 CSA Defect#2863
        if (SVC_TASK_STS.SCHEDULED.equals(inputSvcTaskStsCd) && !ZYPCommonFunc.hasValue(tMsg.svcTaskSchdByUsrId) && !ZYPCommonFunc.hasValue(pMsg.svcTaskSchdByUsrId)) {
            setValueCompletion(tMsg.svcTaskSchdByUsrId, pMsg.fsrEventExeUsrId);
        } else if (ZYPCommonFunc.hasValue(pMsg.svcTaskSchdByUsrId)) {
            setValueCompletion(tMsg.svcTaskSchdByUsrId, pMsg.svcTaskSchdByUsrId);
        }
        // mod end 2016/02/12 CSA Defect#2863
        setValueCompletion(tMsg.svcTaskCloByUsrId, pMsg.svcTaskCloByUsrId);
        setValueCompletion(tMsg.svcTaskApvlDt, pMsg.svcTaskApvlDt);
        setValueCompletion(tMsg.svcTaskApvlTm, pMsg.svcTaskApvlTm);
        setValueCompletion(tMsg.svcTaskRejDt, pMsg.svcTaskRejDt);
        setValueCompletion(tMsg.svcTaskRejTm, pMsg.svcTaskRejTm);
        setValueCompletion(tMsg.svcTaskApvlByUsrId, pMsg.svcTaskApvlByUsrId);
        setValueCompletion(tMsg.svcTaskRejByUsrId, pMsg.svcTaskRejByUsrId);
        // START 2022/02/10 [QC#57338, MOD]
//        setValueCompletion(tMsg.svcRspTmMnAot, pMsg.svcRspTmMnAot);
        if (isResponseTimeTarget(pMsg.glblCmpyCd.getValue(), pMsg.dsSvcCallTpCd.getValue())) {
            setValueCompletion(tMsg.svcRspTmMnAot, pMsg.svcRspTmMnAot);
        }
        // END   2022/02/10 [QC#57338, MOD]
        setValueCompletion(tMsg.svcRspTmNum, pMsg.svcRspTmNum);
        setValueCompletion(tMsg.svcModNum, pMsg.svcModNum);
        setValueCompletion(tMsg.machDownFlg, pMsg.machDownFlg);
        setValueCompletion(tMsg.prtChrgFlg, pMsg.prtChrgFlg);
        setValueCompletion(tMsg.svcLborChrgFlg, pMsg.svcLborChrgFlg);
        setValueCompletion(tMsg.svcLborUnitAmt, pMsg.svcLborUnitAmt);
        setValueCompletion(tMsg.svcLborDealAmt, pMsg.svcLborDealAmt);
        setValueCompletion(tMsg.svcLborFuncAmt, pMsg.svcLborFuncAmt);
        setValueCompletion(tMsg.svcLborTaxRate, pMsg.svcLborTaxRate);
        setValueCompletion(tMsg.svcLborUnitHrsAot, pMsg.svcLborUnitHrsAot);
        setValueCompletion(tMsg.svcLborDealTaxAmt, pMsg.svcLborDealTaxAmt);
        setValueCompletion(tMsg.svcLborFuncTaxAmt, pMsg.svcLborFuncTaxAmt);
        setValueCompletion(tMsg.svcLborDiscRate, pMsg.svcLborDiscRate);
        setValueCompletion(tMsg.svcLborDealDiscAmt, pMsg.svcLborDealDiscAmt);
        setValueCompletion(tMsg.svcLborFuncDiscAmt, pMsg.svcLborFuncDiscAmt);
        setValueCompletion(tMsg.origInvCcyCd, pMsg.origInvCcyCd);
        setValueCompletion(tMsg.invCcyCd, pMsg.invCcyCd);
        setValueCompletion(tMsg.ccyExchRate, pMsg.ccyExchRate);
        setValueCompletion(tMsg.pmtTermCashDiscCd, pMsg.pmtTermCashDiscCd);
        setValueCompletion(tMsg.svcRprTmNum, pMsg.svcRprTmNum);
        // NA#ASCC CLICK Del Start
//        ZYPEZDItemValueSetter.setValue(tMsg.svcTaskTpCd, pMsg.svcTaskTpCd);
        // NA#ASCC CLICK Del End
        setValueCompletion(tMsg.firstSvcTaskFlg, pMsg.firstSvcTaskFlg);
        // NA#ASCC CLICK Del Start
//        ZYPEZDItemValueSetter.setValue(tMsg.svcCallRqstOwnrTocCd, pMsg.svcCallRqstOwnrTocCd);
        // NA#ASCC CLICK Del End
        // NA#ASCC CLICK Mod Start
//        ZYPEZDItemValueSetter.setValue(tMsg.erlStartTs, pMsg.erlStartTs);
//        ZYPEZDItemValueSetter.setValue(tMsg.lateStartTs, pMsg.lateStartTs);
        if (ZYPCommonFunc.hasValue(pMsg.ovrdFlg) && ZYPConstant.FLG_ON_Y.equals(pMsg.ovrdFlg.getValue())) {

            setValueCompletion(tMsg.erlStartTs, pMsg.erlStartTs);
            setValueCompletion(tMsg.lateStartTs, pMsg.lateStartTs);
            // Mod Start 2017/08/07 QC#19971
        } else if (isUpdateStartTs(tMsg)) {
            String erlStartTs = null;
            // mod start 2016/02/12 CSA Defect#3727
            String futSvcTs = getFutSvcTs(pMsg);

            if (ZYPCommonFunc.hasValue(pMsg.erlStartTs)) {

                erlStartTs = pMsg.erlStartTs.getValue();

            } else if (ZYPCommonFunc.hasValue(futSvcTs)) {

                erlStartTs = futSvcTs;

                // mod end 2016/02/12 CSA Defect#3727
            } else {

                StringBuilder erlStartSb = new StringBuilder();
                erlStartSb.append(pMsg.svcTaskRcvDt.getValue());
                erlStartSb.append(pMsg.svcTaskRcvTm.getValue());
                erlStartSb.append(TS_POSTFIX);

                erlStartTs = erlStartSb.toString();
            }

            // Mod Start 2017/10/02 QC#21273
            // START 2019/07/31 K.Kitachi [QC#52257, MOD]
//            String dsSvcCallTpCd = null;
//            if (insertFlag) {
//                dsSvcCallTpCd = pMsg.dsSvcCallTpCd.getValue();
//            }
            String dsSvcCallTpCd = pMsg.dsSvcCallTpCd.getValue();
            // END 2019/07/31 K.Kitachi [QC#52257, MOD]
            erlStartTs = NSXC001001GetRspTime.getEndDateByRspTimeWithTimeDiff(
                    pMsg.glblCmpyCd.getValue()
                  , BigDecimal.ZERO
                  , pMsg.svcMachMstrPk.getValue()
                  , erlStartTs.substring(0, 8)
                  , erlStartTs.substring(8, 14)
                  , false
                  , dsSvcCallTpCd
                  // START 2022/04/11 K.Kitachi [QC#59899, ADD]
                  , pMsg.shipToUpdCustCd.getValue()
                  // END 2022/04/11 K.Kitachi [QC#59899, ADD]
            );
            // Mod End 2017/10/02 QC#21273

            ZYPEZDItemValueSetter.setValue(tMsg.erlStartTs, erlStartTs);

            BigDecimal rspTmMnAot = NSXC001001GetRspTime.getRspTmMnAot(
                    pMsg.glblCmpyCd.getValue()
                  , pMsg.svcMachMstrPk.getValue()
                  , pMsg.svcTaskRcvDt.getValue()
                  , pMsg.machDownFlg.getValue()
                  , pMsg.mdlNm.getValue()
            );

            // add start 2018/08/03 QC#26659
            if (ZYPCommonFunc.hasValue(rspTmMnAot)) {
                rspTmMnAot = rspTmMnAot.setScale(0, RoundingMode.DOWN);
            }
            // add end 2018/08/03 QC#26659

            // START 2022/02/10 [QC#57338, MOD]
//            ZYPEZDItemValueSetter.setValue(tMsg.svcRspTmMnAot, rspTmMnAot);
            if (isResponseTimeTarget(pMsg.glblCmpyCd.getValue(), dsSvcCallTpCd)) {
                ZYPEZDItemValueSetter.setValue(tMsg.svcRspTmMnAot, rspTmMnAot);
            }
            // START 2022/02/10 [QC#57338, MOD]
            // Mod Start 2017/10/02 QC#21273
            String lateStartTs = NSXC001001GetRspTime.getEndDateByRspTimeWithTimeDiff(
                    pMsg.glblCmpyCd.getValue()
                  , rspTmMnAot
                  , pMsg.svcMachMstrPk.getValue()
                  , erlStartTs.substring(0, 8)
                  , erlStartTs.substring(8, 14)
                  , false
                  , dsSvcCallTpCd
                  // START 2022/04/11 K.Kitachi [QC#59899, ADD]
                  , pMsg.shipToUpdCustCd.getValue()
                  // END 2022/04/11 K.Kitachi [QC#59899, ADD]
            );
            // Mod End 2017/10/02 QC#21273

            ZYPEZDItemValueSetter.setValue(tMsg.lateStartTs, lateStartTs);
        }
        // Mod End 2017/08/07 QC#19971
        // NA#ASCC CLICK Mod End
        setValueCompletion(tMsg.svcRgCd, pMsg.svcRgCd);
        setValueCompletion(tMsg.svcBrCd, pMsg.svcBrCd);
        // mod start 2016/03/03 CSA Defect#4766
        setValueCompletion(tMsg.svcTeamTxt, pMsg.svcTeamTxt);
        // mod end 2016/03/03 CSA Defect#4766
        setValueCompletion(tMsg.svcBrMgrPsnCd, pMsg.svcBrMgrPsnCd);
        setValueCompletion(tMsg.svcTrtyMgrPsnCd, pMsg.svcTrtyMgrPsnCd);
        setValueCompletion(tMsg.svcTeamMgrPsnCd, pMsg.svcTeamMgrPsnCd);
        setValueCompletion(tMsg.svcCustCllrTxt, pMsg.svcCustCllrTxt);
        // mod start 2016/04/05 CSA Defect#4495
        setValueCompletion(tMsg.svcCustCllrTelNum, pMsg.svcCustCllrTelNum);
        setValueCompletion(tMsg.custAwareChrgFlg, pMsg.custAwareChrgFlg);
        if (!ZYPCommonFunc.hasValue(tMsg.custAwareChrgFlg)) {
            ZYPEZDItemValueSetter.setValue(tMsg.custAwareChrgFlg, ZYPConstant.FLG_OFF_N);
        }
        // mod end 2016/04/05 CSA Defect#4495
        // mod start 2016/06/09 CSA Defect#9177
        setValueCompletion(tMsg.svcCustCllrTelExtnNum, pMsg.svcCustCllrTelExtnNum);
        // mod end 2016/06/09 CSA Defect#9177

        // Add Start 2018/03/22 QC#18713
        if (ZYPCommonFunc.hasValue(pMsg.cellPhoNum)) {
            setValueCompletion(tMsg.cellPhoNum, pMsg.cellPhoNum);
        }
        // Add End 2018/03/22 QC#18713

        // Add Start 2018/06/05 QC#22449
        if (ZYPCommonFunc.hasValue(pMsg.svcBillChngRsnCd)) {
            setValueCompletion(tMsg.svcBillChngRsnCd, pMsg.svcBillChngRsnCd);
        }
        // Add End 2018/06/05 QC#22449
        // add start 2019/04/19 QC#31237
        setValueCompletion(tMsg.svcZnCd, pMsg.svcZnCd);
        // add end 2019/04/19 QC#31237
        // add start 2019/04/26 QC#31213
        setValueCompletion(tMsg.crsSvcSrNum, pMsg.crsSvcSrNum);
        // add end 2019/04/26 QC#31213

        if (insertFlag) {

            ZYPEZDItemValueSetter.setValue(tMsg.postDisptFlg, ZYPConstant.FLG_OFF_N);
            S21ApiTBLAccessor.insert(tMsg);
        } else {
            S21ApiTBLAccessor.update(tMsg);
        }
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0080E);
            return false;
        }
        return true;
    }

    private boolean createFsrEventRecord(NSZC002001PMsg pMsg, S21ApiMessageMap msgMap) {
        for (int i = 0; i < pMsg.xxFsrEventList.getValidCount(); i++) {
            BigDecimal fsrEventPk = ZYPOracleSeqAccessor.getNumberBigDecimal("FSR_EVENT_SQ");

            FSR_EVENTTMsg tMsg = new FSR_EVENTTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.fsrEventPk, fsrEventPk);
            ZYPEZDItemValueSetter.setValue(tMsg.svcDisptEventCd, pMsg.xxFsrEventList.no(i).svcDisptEventCd);
            ZYPEZDItemValueSetter.setValue(tMsg.svcTaskNum, pMsg.svcTaskNum);
            ZYPEZDItemValueSetter.setValue(tMsg.techCd, pMsg.techCd);
            ZYPEZDItemValueSetter.setValue(tMsg.fsrEventExeTs, pMsg.xxFsrEventList.no(i).fsrEventExeTs);
            ZYPEZDItemValueSetter.setValue(tMsg.fsrEventExeUsrId, pMsg.fsrEventExeUsrId);
            // mod start 2016/03/28 CSA Defect#6020, 2016/07/01 CSA Defect#11185
            NSXC001001GetMblIntfcInfoBean mblIntfcInfoBean = new NSXC001001GetMblIntfcInfoBean();
            mblIntfcInfoBean.setGlblCmpyCd(pMsg.glblCmpyCd.getValue());
            mblIntfcInfoBean.setMblIntfcFlg(pMsg.mblIntfcFlg.getValue());
            mblIntfcInfoBean.setSvcDisptEventCd(pMsg.xxFsrEventList.no(i).svcDisptEventCd.getValue());
            String svcTaskStsCd = getSvcTaskStsCd(pMsg.glblCmpyCd.getValue(), pMsg.svcTaskNum.getValue());
            mblIntfcInfoBean.setSvcTaskStsCd(svcTaskStsCd);
            mblIntfcInfoBean.setTechCd(pMsg.techCd.getValue());
            NSXC001001GetMblIntfcInfo.getMblIntfcInfo(mblIntfcInfoBean);

            ZYPEZDItemValueSetter.setValue(tMsg.mblIntfcProcCd, mblIntfcInfoBean.getMblIntfcProcCd());
            ZYPEZDItemValueSetter.setValue(tMsg.mblIntfcFlg, mblIntfcInfoBean.getMblIntfcFlg());
            // mod end 2016/03/28 CSA Defect#6020, 2016/07/01 CSA Defect#11185

            S21ApiTBLAccessor.insert(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                msgMap.addXxMsgId(NSZM0083E);
                return false;
            }
        }
        return true;
    }

    private boolean createFsrEventRecord(NSZC002001PMsg pMsg, S21ApiMessageMap msgMap, String svcEventCd) {
        BigDecimal fsrEventPk = ZYPOracleSeqAccessor.getNumberBigDecimal("FSR_EVENT_SQ");

        FSR_EVENTTMsg tMsg = new FSR_EVENTTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.fsrEventPk, fsrEventPk);
        ZYPEZDItemValueSetter.setValue(tMsg.svcDisptEventCd, svcEventCd);
        ZYPEZDItemValueSetter.setValue(tMsg.svcTaskNum, pMsg.svcTaskNum);
        ZYPEZDItemValueSetter.setValue(tMsg.techCd, pMsg.techCd);
        ZYPEZDItemValueSetter.setValue(tMsg.fsrEventExeTs, ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmssSSS"));
        ZYPEZDItemValueSetter.setValue(tMsg.fsrEventExeUsrId, pMsg.fsrEventExeUsrId);
        ZYPEZDItemValueSetter.setValue(tMsg.mblIntfcProcCd, MBL_INTFC_PROC.NOT_PROCESSED);
        // mod start 2016/03/28 CSA Defect#6020, 2016/07/01 CSA Defect#11185
        NSXC001001GetMblIntfcInfoBean mblIntfcInfoBean = new NSXC001001GetMblIntfcInfoBean();
        mblIntfcInfoBean.setGlblCmpyCd(pMsg.glblCmpyCd.getValue());
        mblIntfcInfoBean.setMblIntfcFlg(pMsg.mblIntfcFlg.getValue());
        mblIntfcInfoBean.setSvcDisptEventCd(svcEventCd);
        String svcTaskStsCd = getSvcTaskStsCd(pMsg.glblCmpyCd.getValue(), pMsg.svcTaskNum.getValue());
        mblIntfcInfoBean.setSvcTaskStsCd(svcTaskStsCd);
        mblIntfcInfoBean.setTechCd(pMsg.techCd.getValue());
        NSXC001001GetMblIntfcInfo.getMblIntfcInfo(mblIntfcInfoBean);

        ZYPEZDItemValueSetter.setValue(tMsg.mblIntfcProcCd, mblIntfcInfoBean.getMblIntfcProcCd());
        ZYPEZDItemValueSetter.setValue(tMsg.mblIntfcFlg, mblIntfcInfoBean.getMblIntfcFlg());
        // mod end 2016/03/28 CSA Defect#6020, 2016/07/01 CSA Defect#11185

        S21ApiTBLAccessor.insert(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0083E);
            return false;
        }
        return true;
    }

    // mod start 2016/07/01 CSA Defect#11185
    private String getSvcTaskStsCd(String glblCmpyCd, String svcTaskNum) {
        SVC_TASKTMsg svcTaskTMsg = new SVC_TASKTMsg();
        ZYPEZDItemValueSetter.setValue(svcTaskTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcTaskTMsg.svcTaskNum, svcTaskNum);
        SVC_TASKTMsg outTMsg = (SVC_TASKTMsg) S21ApiTBLAccessor.findByKey(svcTaskTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg.svcTaskStsCd.getValue();
    }

    // mod end 2016/07/01 CSA Defect#11185

    private boolean removeRecord(EZDPStringItem glblCmpyCd, EZDPStringItem svcTaskNum, S21ApiMessageMap msgMap, SVC_TASKTMsg svcTaskTMsg) {
        // Remove SVC_TASK
        S21ApiTBLAccessor.remove(svcTaskTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(svcTaskTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0086E);
            return false;
        }
        return true;
    }

    private SVC_TASKTMsg getSvcTask(NSZC002001PMsg pMsg, S21ApiMessageMap msgMap) {
        SVC_TASKTMsg svcTaskTMsg = new SVC_TASKTMsg();
        ZYPEZDItemValueSetter.setValue(svcTaskTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcTaskTMsg.svcTaskNum, pMsg.svcTaskNum);
        // START 2018/01/09 U.Kim [QC#19702, MOD]
        // svcTaskTMsg = (SVC_TASKTMsg) S21ApiTBLAccessor.findByKeyForUpdate(svcTaskTMsg);
        try {
            svcTaskTMsg = (SVC_TASKTMsg) S21ApiTBLAccessor.findByKeyForUpdate(svcTaskTMsg);
        } catch (EZDDBRecordLockedException e) {
            svcTaskTMsg = getSvcTaskForUpdateWait(svcTaskTMsg);
        }
        // END 2018/01/09 U.Kim [QC#19702, MOD]
        if (svcTaskTMsg == null) {
            msgMap.addXxMsgId(NSZM0079E);
            return null;
        }
        return svcTaskTMsg;
    }

    // START 2018/01/09 U.Kim [QC#19702, ADD]
    private SVC_TASKTMsg getSvcTaskForUpdateWait(SVC_TASKTMsg svcTaskTMsg) {
        try {
            return (SVC_TASKTMsg) EZDTBLAccessor.findByKeyForUpdateWait(svcTaskTMsg, this.waitSecUpdTaskOther);
        } catch (EZDDBRecordLockedException e) {
            return null;
        }
    }

    // END 2018/01/09 U.Kim [QC#19702, ADD]

    private boolean checkTimeFormat(String value) {
        String regex = "([0-1][0-9]|[2][0-3])([0-5][0-9])";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(value);

        return m.find();
    }

    private boolean hasFunction(String glblCmpyCd, String usrId, String svcTaskStsCd) {
        // TODO pending
        return true;
    }

    // NA#ASCC CLICK DEL Start
//    private static BigDecimal subtract(BigDecimal minuend, BigDecimal subtrahend) {
    // if (minuend == null) {
    // minuend = BigDecimal.ZERO;
    // }
    // if (subtrahend == null) {
    // subtrahend = BigDecimal.ZERO;
    // }
    // return minuend.subtract(subtrahend);
    // }
    //
//    private static BigDecimal subtract(EZDPBigDecimalItem minuend, EZDPBigDecimalItem subtrahend) {
    // return subtract(minuend.getValue(), subtrahend.getValue());
    // }
    //
//    private static BigDecimal subtract(EZDTBigDecimalItem minuend, EZDTBigDecimalItem subtrahend) {
    // return subtract(minuend.getValue(), subtrahend.getValue());
    // }
    // NA#ASCC CLICK DEL Start

    // NA#ASCC CLICK ADD Start
    private S21_PSNTMsg getS21Psn(String glblCmpyCd, String psnCd) {
        S21_PSNTMsg tMsg = new S21_PSNTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.psnCd, psnCd);
        return (S21_PSNTMsg) S21ApiTBLAccessor.findByKey(tMsg);
    }

    private boolean checkRecallTarget(NSZC002001PMsg pMsg) {

        // add start 2019/03/07 QC#30686
        DS_SVC_CALL_TPTMsg tMsg = new DS_SVC_CALL_TPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsSvcCallTpCd, pMsg.dsSvcCallTpCd);
        tMsg = (DS_SVC_CALL_TPTMsg) S21ApiTBLAccessor.findByKey(tMsg);
        if (tMsg == null || ZYPConstant.FLG_OFF_N.equals(tMsg.svcRcllTrgtFlg.getValue())) {
            return false;
        }
        // add end 2019/03/07 QC#30686

        /********************
         * Set Parameter.
         ********************/
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("svcMachMstrPk", pMsg.svcMachMstrPk.getValue());
        param.put("rcllTrgtFlg", ZYPConstant.FLG_ON_Y);
        param.put("svcTaskNum", pMsg.svcTaskNum.getValue());

        List<String> svcCallStsList = new ArrayList<String>();
        svcCallStsList.add(SVC_TASK_STS.SAVED);
        svcCallStsList.add(SVC_TASK_STS.CANCELLED);
        svcCallStsList.add(SVC_TASK_STS.COMPLETED);
        // START   2019/11/18 K.Fujimoto [QC#54391, ADD]
        svcCallStsList.add(SVC_TASK_STS.CLOSED);
        // END     2019/11/18 K.Fujimoto [QC#54391, ADD]
        param.put("svcCallStsList", svcCallStsList);
        /********************
         * Exists Recall Target SVC_TASK.
         ********************/
        Map<String, Object> recallInfoMap = (Map<String, Object>) ssmBatchClient.queryObject("getRecallInfo", param);
        if (recallInfoMap == null) {
            return false;
        }

        /********************
         * Not Exists Processing SVC_TASK Record.
         ********************/
        BigDecimal processingSvcTaskCnt = (BigDecimal) recallInfoMap.get("PROCESSING_TASK_CNT");
        if (!BigDecimal.ZERO.equals(processingSvcTaskCnt)) {
            return false;
        }

        /********************
         * Check Recall interval date.
         ********************/
        if (!checkRecallIntvlDays(recallInfoMap)) {
            return false;
        }

        return true;
    }

    private boolean checkRecallIntvlDays(Map<String, Object> recallInfo) {

        String previousDate;
        String currentDate = ZYPDateUtil.getCurrentSystemTime("yyyyMMdd");
        int rcllIntvlDays = 0;

        if (ZYPCommonFunc.hasValue(recallInfo.get("PREVIOUS_TASK_DT").toString()) && 8 <= recallInfo.get("PREVIOUS_TASK_DT").toString().length()) {
            previousDate = recallInfo.get("PREVIOUS_TASK_DT").toString().substring(0, 8);
        } else {
            return false;
        }

        // START   2019/11/18 K.Fujimoto [QC#54391, MOD]
//        if (ZYPCommonFunc.hasValue((BigDecimal) recallInfo.get("RCLL_INTVL_DAYS_AOT"))) {
//            rcllIntvlDays = ((BigDecimal) recallInfo.get("RCLL_INTVL_DAYS_AOT")).intValue();
//        } else {
//            return false;
//        }
        if (ZYPCommonFunc.hasValue((BigDecimal) recallInfo.get("RCLL_GLBL_INTVL_DAYS_AOT"))) {
            rcllIntvlDays = ((BigDecimal) recallInfo.get("RCLL_GLBL_INTVL_DAYS_AOT")).intValue();
        } else {
            return false;
        }
//        HashMap<String, Object> param = new HashMap<String, Object>();
//        param.put("glblCmpyCd", (String) recallInfo.get("GLBL_CMPY_CD"));
//        param.put("calTpGeneral", CAL_TP.CSA_GENERAL);
//        param.put("calTpHoliday", CAL_TP.CSA_HOLIDAY);
//        param.put("dtAttrbBiz", DT_ATTRB.BUSINESS);
//        param.put("constOn", ZYPConstant.FLG_ON_1);
//        param.put("constOff", ZYPConstant.FLG_OFF_0);
//        param.put("fromDt", previousDate);
//        param.put("thruDt", currentDate);
//
//        BigDecimal nonWrkDaysRslt = (BigDecimal) ssmBatchClient.queryObject("getNonWrkDays", param);
//        int nonWrkDays = 0;
//        if (ZYPCommonFunc.hasValue(nonWrkDaysRslt)) {
//            nonWrkDays = nonWrkDaysRslt.intValue();
//        }
//        int diffDays = ZYPDateUtil.getDiffDays(currentDate, previousDate) - nonWrkDays;
        int diffDays = ZYPDateUtil.getDiffDays(currentDate, previousDate);
        if (diffDays <= rcllIntvlDays) {
            return true;
        }
        return false;
        // END   2019/11/18 K.Fujimoto [QC#54391, MOD]
    }

    private boolean chkTimeFormatTs(String value) {

        boolean dateChkRslt = ZYPDateUtil.isValidDate(value.substring(0, 8), ZYPDateUtil.TYPE_YYYYMMDD);
        String regex = "([0-1][0-9]|[2][0-3])([0-5][0-9])([0-5][0-9])([0-9][0-9][0-9])";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(value.substring(8));

        return dateChkRslt && m.find();
    }

    // NA#ASCC CLICK ADD End
    // add start 2016/02/12 CSA Defect#3727
    private String getFutSvcTs(NSZC002001PMsg pMsg) {
        String futSvcTs = null;
        FSR_VISITTMsgArray tMsgArray = getFsrVisit(pMsg.glblCmpyCd.getValue(), pMsg.svcTaskNum.getValue());
        if (tMsgArray.getValidCount() > 0) {
            FSR_VISITTMsg tMsg = tMsgArray.no(0);
            if (ZYPCommonFunc.hasValue(tMsg.futSvcDt) && ZYPCommonFunc.hasValue(tMsg.futSvcTm)) {
                StringBuilder sb = new StringBuilder();
                sb.append(tMsg.futSvcDt.getValue());
                sb.append(tMsg.futSvcTm.getValue());
                sb.append("000");
                futSvcTs = sb.toString();
            }
        }
        return futSvcTs;
    }

    // add start 2016/02/12 CSA Defect#3727
    private FSR_VISITTMsgArray getFsrVisit(String glblCmpyCd, String svcTaskNum) {
        FSR_VISITTMsg tMsg = new FSR_VISITTMsg();
        tMsg.setSQLID("002");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("svcTaskNum01", svcTaskNum);
        return (FSR_VISITTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
    }

    // add end 2016/02/12 CSA Defect#2863
    private boolean releaseHold(NSZC002001PMsg msg, S21ApiMessageMap msgMap) {
        // add start 2019/07/15 CSA Defect#51361
        if (!ZYPCommonFunc.hasValue(msg.svcTaskNum.getValue())) {
            return true;
        }
        FSR_VISITTMsgArray tMsgArray = getFsrVisit(msg.glblCmpyCd.getValue(), msg.svcTaskNum.getValue());
        if (tMsgArray.getValidCount() > 0) {
            FSR_VISITTMsg tMsg = tMsgArray.no(0);
            if (tMsg.fsrVisitStsCd.getValue().equals(SVC_TASK_STS.CREDIT_HOLD)) {
                return true;
            }
        }
        // add end 2019/07/15 CSA Defect#51361
        SVC_TASK_HLDTMsgArray svcTaskHldList = getSvcTaskHldForUpdate(msg.glblCmpyCd.getValue(), msg.svcTaskNum.getValue());
        for (int i = 0; i < svcTaskHldList.getValidCount(); i++) {
            setValue(svcTaskHldList.no(i).svcTaskRelDt, msg.svcTaskRcvDt);
            setValue(svcTaskHldList.no(i).svcTaskRelUsrId, msg.fsrEventExeUsrId);
            setValue(svcTaskHldList.no(i).svcTaskRelFlg, ZYPConstant.FLG_ON_Y);

            S21ApiTBLAccessor.update(svcTaskHldList.no(i));
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(svcTaskHldList.no(i).getReturnCode())) {
                msgMap.addXxMsgId(NSZM0865E);
                return false;
            }
        }
        return true;
    }

    // add end 2016/02/12 CSA Defect#2863
    private SVC_TASK_HLDTMsgArray getSvcTaskHldForUpdate(String glblCmpyCd, String svcTaskNum) {
        SVC_TASK_HLDTMsg tMsg = new SVC_TASK_HLDTMsg();
        tMsg.setSQLID("002");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("svcTaskNum01", svcTaskNum);
        tMsg.setConditionValue("svcTaskRelFlg01", ZYPConstant.FLG_OFF_N);
        return (SVC_TASK_HLDTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(tMsg);
    }

    // add end 2016/02/12 CSA Defect#4283
    private String getMblIntfcFlg(String glblCmpyCd, String svcDisptEventCd) {
        SVC_DISPT_EVENTTMsg tMsg = new SVC_DISPT_EVENTTMsg();
        setValue(tMsg.glblCmpyCd, glblCmpyCd);
        setValue(tMsg.svcDisptEventCd, svcDisptEventCd);
        SVC_DISPT_EVENTTMsg outTMsg = (SVC_DISPT_EVENTTMsg) S21ApiTBLAccessor.findByKey(tMsg);
        if (outTMsg == null) {
            return ZYPConstant.FLG_ON_Y;
        }
        return outTMsg.mblIntfcFlg.getValue();
    }

    // add start 2016/03/03 CSA Defect#4982
    private void setValueCompletion(EZDTStringItem dbValue, EZDPStringItem paramValue) {
        if (ZYPCommonFunc.hasValue(paramValue)) {
            ZYPEZDItemValueSetter.setValue(dbValue, paramValue);
        }
        return;
    }

    // add start 2016/03/03 CSA Defect#4982
    private void setValueCompletion(EZDTBigDecimalItem dbValue, EZDPBigDecimalItem paramValue) {
        if (ZYPCommonFunc.hasValue(paramValue)) {
            ZYPEZDItemValueSetter.setValue(dbValue, paramValue);
        }
        return;
    }

    // add start 2016/03/03 CSA Defect#4982
    private void setValueCompletion(EZDTDateItem dbValue, EZDPDateItem paramValue) {
        if (ZYPCommonFunc.hasValue(paramValue)) {
            ZYPEZDItemValueSetter.setValue(dbValue, paramValue);
        }
        return;
    }

    // Add Start 2017/08/07 QC#19971
    // START 2017/09/05 K.Kitachi [QC#19971, MOD]
    private boolean isUpdateStartTs(SVC_TASKTMsg tMsg) {
        if (tMsg == null || !ZYPCommonFunc.hasValue(tMsg.glblCmpyCd) || !ZYPCommonFunc.hasValue(tMsg.fsrNum) || !ZYPCommonFunc.hasValue(tMsg.svcTaskNum)) {
            return true;
        }

        if (isExistsOpenPrchReq(tMsg.glblCmpyCd.getValue(), tMsg.fsrNum.getValue(), tMsg.svcTaskNum.getValue())) {
            return false;
        }

        return true;
    }

    private boolean isExistsOpenPrchReq(String glblCmpyCd, String fsrNum, String svcTaskNum) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("fsrNum", fsrNum);
        param.put("svcTaskNum", svcTaskNum);

        BigDecimal count = (BigDecimal) this.ssmBatchClient.queryObject("countOpenPrchReq", param);
        return count.compareTo(BigDecimal.ZERO) > 0;
    }

    // END 2017/09/05 K.Kitachi [QC#19971, MOD]
    // Add End 2017/08/07 QC#19971

    // START 2018/01/09 U.Kim [QC#19702, ADD]
    private void setWaitSecUpdTaskOther(String glblCmpyCd) {
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            return;
        }
        BigDecimal numConst = ZYPCodeDataUtil.getNumConstValue(WAIT_SEC_UPD_TASK_OTHER, glblCmpyCd);
        if (numConst != null) {
            this.waitSecUpdTaskOther = numConst.intValue();
        }
    }
    // END 2018/01/09 U.Kim [QC#19702, ADD]
    // START 2019/10/24 K.Fujimoto [QC#53441, ADD]
    private boolean updateInvldSvcMtrRead(NSZC002001PMsg pMsg, S21ApiMessageMap msgMap) {
        List<BigDecimal> svcPhysMtrReadGrpSqList = getSvcPhysMtrReadGrpSq(pMsg);

        for (BigDecimal svcPhysMtrReadGrpSq : svcPhysMtrReadGrpSqList) {
            SVC_PHYS_MTR_READTMsg inMsg = new SVC_PHYS_MTR_READTMsg();
            inMsg.setSQLID("007");
            inMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
            inMsg.setConditionValue("svcPhysMtrReadGrpSq01", svcPhysMtrReadGrpSq);
            SVC_PHYS_MTR_READTMsgArray tMsgArray = (SVC_PHYS_MTR_READTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(inMsg);

            for (int i = 0; i < tMsgArray.getValidCount(); i++) {
                SVC_PHYS_MTR_READTMsg tMsg = tMsgArray.no(i);
                setValue(tMsg.vldMtrFlg, ZYPConstant.FLG_OFF_N);
                S21ApiTBLAccessor.update(tMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    msgMap.addXxMsgId(NSZM0368E);
                    return false;
                }
            }
        }
        return true;
    }
    
    private List<BigDecimal> getSvcPhysMtrReadGrpSq(NSZC002001PMsg pMsg) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("svcMachMstrPk", pMsg.svcMachMstrPk.getValue());
        paramMap.put("svcTaskNum", pMsg.svcTaskNum.getValue());
        paramMap.put("vldMtrFlgY", ZYPConstant.FLG_ON_Y);
        return (List<BigDecimal>) ssmBatchClient.queryObjectList("getSvcPhysMtrReadGrpSq", paramMap);
    }
    // END   2019/10/24 K.Fujimoto [QC#53441, ADD]

    // START 2022/02/10 [QC#57338, ADD]
    private boolean isResponseTimeTarget(String glblCmpyCd, String dsSvcCallTpCd) {
        DS_COND_CONSTTMsg inMsg = new DS_COND_CONSTTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsCondConstGrpId01", RSP_TM_TGT_CALL_TP);
        inMsg.setConditionValue("dsCondConstCd01", dsSvcCallTpCd);
        DS_COND_CONSTTMsgArray outArray = (DS_COND_CONSTTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        if (outArray.getValidCount() == 0) {
            return false;
        }
        return true;
    }
    // END   2022/02/10 [QC#57338, ADD]
}
