/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC043001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.common.EZDPMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.AHSTMsg;
import business.db.AHSTMsgArray;
import business.db.BILL_TO_CUSTTMsg;
import business.db.BILL_TO_CUSTTMsgArray;
import business.db.CALTMsg;
import business.db.CALTMsgArray;
import business.db.CR_CARD_TRXTMsg;
import business.db.CR_CARD_TRXTMsgArray;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CR_CARDTMsg;
import business.db.DS_CR_CARDTMsgArray;
import business.db.DS_SVC_CALL_TPTMsg;
import business.db.FSRTMsg;
import business.db.FSR_CHRG_DTLTMsg;
import business.db.FSR_VISITTMsg;
import business.db.FSR_VISITTMsgArray;
import business.db.FSR_VISIT_TASKTMsg;
import business.db.FSR_VISIT_TM_EVENTTMsg;
import business.db.MDSETMsg;
import business.db.MDSETMsgArray;
import business.db.PMT_TERM_CASH_DISCTMsg;
import business.db.SVC_BILL_TPTMsg;
import business.db.SVC_CALL_SRC_TPTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MEMOTMsg;
import business.db.SVC_TASKTMsg;
import business.parts.NSZC002001PMsg;
import business.parts.NSZC003001PMsg;
import business.parts.NSZC005001PMsg;
import business.parts.NSZC005001_xxChargesListPMsg;
import business.parts.NSZC005001_xxVisitTaskListPMsg;
import business.parts.NSZC041001PMsg;
import business.parts.NSZC043001PMsg;
import business.parts.NSZC043001_taskListPMsg;
import business.parts.NSZC045001PMsg;
import business.parts.NSZC045001_xxSvcMemoListPMsg;
import business.parts.NSZC045001_xxSvcTaskListPMsg;
import business.parts.NSZC061001PMsg;
import business.parts.NSZC107001PMsg;
import business.parts.NWZC203001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC002001.NSZC002001;
import com.canon.cusa.s21.api.NSZ.NSZC002001.constant.NSZC002001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC003001.NSZC003001;
import com.canon.cusa.s21.api.NSZ.NSZC003001.constant.NSZC003001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC005001.NSZC005001;
import com.canon.cusa.s21.api.NSZ.NSZC005001.constant.NSZC005001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC041001.NSZC041001;
import com.canon.cusa.s21.api.NSZ.NSZC043001.constant.NSZC043001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC045001.NSZC045001;
import com.canon.cusa.s21.api.NSZ.NSZC061001.NSZC061001;
import com.canon.cusa.s21.api.NSZ.NSZC061001.constant.NSZC061001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC107001.NSZC107001;
import com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001;
import com.canon.cusa.s21.api.NWZ.NWZC203001.NWZC203001Constant;
import com.canon.cusa.s21.common.NSX.NSXC001001.EndOfLifeBean;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetContr;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetEndOfLifeInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetRspTime;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetFollUpInfoBean;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetFollUpInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001SvcTimeZone;
import com.canon.cusa.s21.common.NSX.NSXC001001.SvcTimeZoneInfo;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BIZ_AREA_ORG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CAL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_AUTH_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_SVC_CALL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PMT_TERM_CASH_DISC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTL_WH_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_BILL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CALL_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_HLD_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.security.helpers.S21SessionHelper;

/**
 * <pre>
 * FSR Update API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/04/2015   Fujitsu         Y.Kamide        Create
 * 08/10/2015   Fujitsu         T.Nakamura      Update          NA#ASCC CLICK
 * 08/28/2015   Fujitsu         T.Nakamura      Update          NA#ASCC CLICK
 * 10/14/2015   Fujitsu         T.Nakamura      Update          NA#ASCC CLICK BugFix
 * 10/28/2015   Fujitsu         T.Nakamura      Update          NA#ASCC CLICK BugFix
 * 11/18/2015   Hitachi         T.Iwamoto       Update          NA#395  CLICK BugFix
 * 01/04/2015   Fujitsu         T.Nakamura      Update          NA#1107
 * 02/05/2016   Hitachi         A.Kohinata      Update          CSA UnitTest#92
 * 02/08/2016   Hitachi         T.Iwamoto       Update          QC#2863
 * 02/10/2016   Hitachi         T.Iwamoto       Update          QC#3727
 * 02/18/2016   Hitachi         T.Iwamoto       Update          QC#4471
 * 02/25/2016   Hitachi         T.Iwamoto       Update          QC#3991
 * 03/03/2016   Hitachi         T.Iwamoto       Update          QC#4766
 * 03/28/2016   Hitachi         T.Iwamoto       Update          QC#6020
 * 04/05/2016   Hitachi         T.Iwamoto       Update          QC#4495
 * 04/13/2016   Hitachi         T.Iwamoto       Update          QC#5486
 * 04/28/2016   Hitachi         T.Iwamoto       Update          QC#2696
 * 05/23/2016   Hitachi         Y.Takeno        Update          QC#447
 * 06/07/2016   Hitachi         T.Iwamoto       Update          QC#3727,9218
 * 06/09/2016   Hitachi         T.Iwamoto       Update          QC#9177
 * 06/09/2016   Hitachi         T.Iwamoto       Update          QC#8899
 * 06/16/2016   Hitachi         T.Iwamoto       Update          QC#9677
 * 06/22/2016   Hitachi         T.Iwamoto       Update          QC#8899,9677
 * 07/01/2016   Hitachi         T.Iwamoto       Update          QC#9677
 * 07/14/2016   Hitachi         T.Iwamoto       Update          SADP
 * 07/21/2016   Hitachi         T.Iwamoto       Update          QC#12154
 * 08/02/2016   Hitachi         A.Kohinata      Update          QC#12793
 * 08/10/2016   Fujitsu         S.Nakai         Update          QC#8421
 * 2016/09/15   Hitachi         T.Tomita        Update          QC#14401
 * 07/21/2016   Hitachi         T.Iwamoto       Update          QC#14251
 * 10/04/2016   Hitachi         T.Iwamoto       Update          QC#14981
 * 10/06/2016   Hitachi         K.Yamada        Update          QC#14950
 * 10/05/2016   Hitachi         T.Tomita        Update          QC#14145
 * 11/25/2016   Hitachi         T.Mizuki        Update          QC#16025
 * 12/15/2016   Hitachi         T.Tomita        Update          QC#16490
 * 01/18/2017   Hitachi         K.Kitachi       Update          QC#15818
 * 01/19/2017   Hitachi         Y.Takeno        Update          QC#15092
 * 04/24/2017   Hitachi         K.Kitachi       Update          QC#18370
 * 05/19/2017   Hitachi         K.Kitachi       Update          QC#18213
 * 06/22/2017   Hitachi         T.Tomita        Update          QC#19351
 * 07/21/2017   Hitachi         K.Kojima        Update          QC#19987
 * 09/04/2017   Hitachi         T.Tomita        Update          QC#18573
 * 10/02/2017   Hitachi         T.Tomita        Update          QC#21273
 * 03/22/2018   Hitachi         T.Tomita        Update          QC#18713
 * 04/10/2018   Hitachi         T.Tomita        Update          QC#21868
 * 05/08/2018   CITS            M.Naito         Update          QC#17679
 * 06/05/2018   CITS            M.Naito         Update          QC#17390
 * 06/20/2018   Hitachi         T.Tomita        Update          QC#26830
 * 06/22/2018   CITS            M.Naito         Update          QC#26251
 * 07/12/2018   CITS            M.Naito         Update          QC#13309
 * 08/14/2018   CITS            M.Naito         Update          QC#27472
 * 2018/08/30   Hitachi         K.Kitachi       Update          QC#22665
 * 2018/09/06   Hitachi         T.Tomita        Update          QC#22665
 * 2018/09/27   CITS            M.Naito         Update          QC#26149
 * 2018/11/29   Hitachi         K.Kitachi       Update          QC#29459
 * 2019/01/21   Fujitsu         W.Honda         Update          QC#28650
 * 2019/02/22   Hitachi         K.Kim           Update          QC#30486
 * 2019/03/27   Hitachi         S.Kitamura      Update          QC#30906
 * 2019/07/31   Hitachi         K.Kitachi       Update          QC#52257
 * 2019/09/02   Hitachi         K.Fujimoto      Update          QC#52933
 * 2019/11/06   Hitachi         K.Kim           Update          QC#53849
 * 2020/04/06   Hitachi         K.Kitachi       Update          QC#56229
 * 2020/06/15   Hitachi         K.Yamada        Update          QC#56229
 * 2021/01/26   CITS            L.Mandanas      Update          QC#56947
 * 2021/08/03   CITS            L.Mandanas      Update          QC#59066
 * 2022/04/11   Hitachi         K.Kitachi       Update          QC#59899
 * 2022/07/20   CITS            L.Mandanas      Update          QC#60316
 * 2023/05/24   Hitachi         T.Nagae         Update          CSA-QC#61365
 * 2023/05/26   Hitachi         T.Usui          Update          QC#61069
 * 2023/05/31   Hitachi         K.Watanabe      Update          QC#61309
 * 2023/06/14   Hitachi         K.Watanabe      Update          QC#61309
 * 2023/07/21   Hitachi         K.Watanabe      Update          QC#61309
 * 2023/07/14   Hitachi         K.Watanabe      Update          QC#61310
 * 2023/09/15   Hitachi         K.Watanabe      Update          QC#61310
 * </pre>
 */
public class NSZC043001 extends S21ApiCommonBase implements NSZC043001Constant {

    /** S21ApiMessageMap */
    private S21ApiMessageMap msgMap = null;

    /** onBatTp */
    private ONBATCH_TYPE onBatType = null;

    /** Batch client */
    private S21SsmBatchClient ssmBatClnt;

    /** Current Time (HHmmss) */
    private String curTime = null;

    // START 2018/08/30 K.Kitachi [QC#22665, ADD]
    /** Time stamp */
    private String systemTimeStamp = null;

    /** information message list */
    private List<String> infoMsgList = null;
    // END 2018/08/30 K.Kitachi [QC#22665, ADD]

    /**
     * Constructor
     */
    public NSZC043001() {
        super();
        ssmBatClnt = S21SsmBatchClient.getClient(getClass());
    }

    /**
     * FSR Update API
     * @param pMsgList List<NSZC043001PMsg>
     * @param onBatTp ONBATCH_TYPE
     */
    public void execute(List<NSZC043001PMsg> pMsgList, final ONBATCH_TYPE onBatTp) {
        for (NSZC043001PMsg pMsg : pMsgList) {
            execute(pMsg, onBatTp);
        }
    }

    /**
     * FSR Update API
     * @param pMsg {@link NSZC043001PMsg}
     * @param onBatTp Online Batch Type
     */
    public void execute(NSZC043001PMsg pMsg, final ONBATCH_TYPE onBatTp) {

        init(pMsg, onBatTp);

        if (!inputCheck(pMsg)) {
            // START 2016/09/15 T.Tomita [QC#14401, ADD]
            this.msgMap.flush();
            // END 2016/09/15 T.Tomita [QC#14401, ADD]
            // START 2017/05/19 K.Kitachi [QC#18213, ADD]
            setMsgTxt(pMsg);
            // END 2017/05/19 K.Kitachi [QC#18213, ADD]
            return;
        }

        String mode = pMsg.xxModeCd.getValue();
        if (MODE_CREATE_FSR.equals(mode)) {
            createFSR(pMsg);

        } else if (MODE_UPDATE_FSR.equals(mode)) {
            updateFSR(pMsg);

        } else if (MODE_CANCEL_FSR.equals(mode)) {
            cancelFSR(pMsg);
        // START 2021/01/26 L.Mandanas [QC#56947, ADD]
        } else if (MODE_UPDATE_FSR_PR.equals(mode)) {
            if (!checkSvcTaskSts(pMsg.glblCmpyCd.getValue(),
                    pMsg.taskList.no(0).svcTaskNum.getValue())) {
                this.msgMap.flush();
                setMsgTxt(pMsg);
                return;
            }
            updateFSR(pMsg);
        // END 2021/01/26 L.Mandanas [QC#56947, ADD]
        }

        // add start 2016/06/16 CSA Defect#9677
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            // START 2016/09/15 T.Tomita [QC#14401, ADD]
            this.msgMap.flush();
            // END 2016/09/15 T.Tomita [QC#14401, ADD]
            // START 2017/05/19 K.Kitachi [QC#18213, ADD]
            setMsgTxt(pMsg);
            // END 2017/05/19 K.Kitachi [QC#18213, ADD]
            return;
        }
        // Call Send Task Info API To Click
        callSendTaskInfoApi(pMsg);
        // add end 2016/06/16 CSA Defect#9677
        // START 2018/08/30 K.Kitachi [QC#22665, ADD] TODO temporarily delete.
//        for (String infoMsg : this.infoMsgList) {
//            setErrMsg(pMsg, infoMsg);
//        }
        // END 2018/08/30 K.Kitachi [QC#22665, ADD]
        // START 2016/09/15 T.Tomita [QC#14401, ADD]
        this.msgMap.flush();
        // END 2016/09/15 T.Tomita [QC#14401, ADD]
        // START 2017/05/19 K.Kitachi [QC#18213, ADD]
        setMsgTxt(pMsg);
        // END 2017/05/19 K.Kitachi [QC#18213, ADD]
    }

    private boolean inputCheck(NSZC043001PMsg pMsg) {
        boolean retFlg = true;

        if (!mandatoryCheck(pMsg)) {
            return false;
        }

        // START 2017/01/18 K.Kitachi [QC#15818, ADD]
        if (isAccessory(pMsg)) {
            setErrMsg(pMsg, NSZM1097E);
            return false;
        }
        // END 2017/01/18 K.Kitachi [QC#15818, ADD]

        return retFlg;
    }

    private boolean mandatoryCheck(NSZC043001PMsg pMsg) {
        boolean retFlg = true;

        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            setErrMsg(pMsg, NSZM0001E);
            retFlg = false;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.xxModeCd)) {
            setErrMsg(pMsg, NSZM0003E);
            retFlg = false;
        }
        // START 2019/03/27 S.Kitamura [QC#30906, DEL]
//        if (!ZYPCommonFunc.hasValue(pMsg.userId)) {
//            setErrMsg(pMsg, NSZM0163E);
//            retFlg = false;
//        }
        // END 2019/03/27 S.Kitamura [QC#30906, DEL]
        int size = pMsg.taskList.getValidCount();
        for (int i = 0; i < size; i++) {

            NSZC043001_taskListPMsg dtlPMsg = pMsg.taskList.no(i);

            if (!ZYPCommonFunc.hasValue(dtlPMsg.mblIntfcFlg)) {

                setErrMsg(pMsg, NSZM0591E);
                retFlg = false;
            }
        }

        if (MODE_CREATE_FSR.equals(pMsg.xxModeCd.getValue())) {

            if (!ZYPCommonFunc.hasValue(pMsg.dsSvcCallTpCd)) {
                setErrMsg(pMsg, NSZM0049E);
                retFlg = false;
            }

            if (!ZYPCommonFunc.hasValue(pMsg.svcTaskRcvDt)) {
                setErrMsg(pMsg, NSZM0053E);
                retFlg = false;
            }

            if (!ZYPCommonFunc.hasValue(pMsg.svcTaskRcvTm)) {
                setErrMsg(pMsg, NSZM0054E);
                retFlg = false;
            }

            if (!ZYPCommonFunc.hasValue(pMsg.svcPblmTpCd)) {
                setErrMsg(pMsg, NSZM0243E);
                retFlg = false;
            }

            if (!ZYPCommonFunc.hasValue(pMsg.svcCallSrcTpCd)) {
                setErrMsg(pMsg, NSZM0544E);
                retFlg = false;
            }
        } else if (MODE_UPDATE_FSR.equals(pMsg.xxModeCd.getValue())) {

            if (!ZYPCommonFunc.hasValue(pMsg.fsrNum)) {
                setErrMsg(pMsg, NSZM0180E);
                retFlg = false;
            }

            for (int i = 0; i < size; i++) {
                NSZC043001_taskListPMsg dtlPMsg = pMsg.taskList.no(i);

                if (!ZYPCommonFunc.hasValue(dtlPMsg.svcTaskNum)) {
                    setErrMsg(pMsg, NSZM0156E, i);
                    retFlg = false;
                }
            }

        } else if (MODE_CANCEL_FSR.equals(pMsg.xxModeCd.getValue())) {

            if (!ZYPCommonFunc.hasValue(pMsg.fsrNum)) {
                setErrMsg(pMsg, NSZM0180E);
                retFlg = false;
            }

            for (int i = 0; i < size; i++) {
                NSZC043001_taskListPMsg dtlPMsg = pMsg.taskList.no(i);

                if (!ZYPCommonFunc.hasValue(dtlPMsg.schdDisptEmlFlg)) {
                    setErrMsg(pMsg, NSZM0157E, i);
                    retFlg = false;
                }
            }

        }
        return retFlg;
    }

    // Mod Start 2018/06/22 QC#26251
    // START 05/23/2016 [QC#447, ADD]
    private boolean eolCheck(NSZC043001PMsg pMsg, DS_SVC_CALL_TPTMsg dsSvcCallTpTMsg) {

        EndOfLifeBean infoBean = new EndOfLifeBean();
        infoBean.setGlblCmpyCd(pMsg.glblCmpyCd.getValue());
        infoBean.setSvcMachMstrPk(pMsg.svcMachMstrPk.getValue());
        infoBean.setEolDt(pMsg.slsDt.getValue());
        if (!NSXC001001GetEndOfLifeInfo.getEndOfLifeInfo(infoBean)) {
            setErrMsg(pMsg, infoBean.getXxMsgId());
            return false;
        }

//        if (ZYPConstant.FLG_OFF_N.equals(infoBean.getSvcAvalFlg())) {
        if (ZYPConstant.FLG_OFF_N.equals(infoBean.getSvcAvalFlg()) && ZYPConstant.FLG_OFF_N.equals(dsSvcCallTpTMsg.eolCallAvalFlg.getValue())) {
            setErrMsg(pMsg, NSZM0976E);
            return false;
        }

        return true;
    }
    // END   05/23/2016 [QC#447, ADD]
    // Mod End 2018/06/22 QC#26251

    // START 2018/06/05 M.Naito [QC#17390, ADD]
    private boolean mdseSvcCallEnblCheck(NSZC043001PMsg pMsg) {

        SVC_MACH_MSTRTMsg svcMachMstrTMsg = null;
        if (ZYPCommonFunc.hasValue(pMsg.svcMachMstrPk)) {
            svcMachMstrTMsg = getSvcMachMstr(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        }
        if (svcMachMstrTMsg != null) {
            MDSETMsg mdseTMsg = getMdse(pMsg.glblCmpyCd.getValue(), svcMachMstrTMsg.mdseCd.getValue());
            if (mdseTMsg != null) {
                if (ZYPConstant.FLG_OFF_N.equals(mdseTMsg.svcCallEnblFlg.getValue())) {
                    setErrMsg(pMsg, NSZM1336E);
                } else {
                    return true;
                }
            }
        }
        return false;
    }
    // END 2018/06/05 M.Naito [QC#17390, ADD]

    /**
     * createFSR
     * @param pMsg NSZC043001PMsg
     */
    private void createFSR(NSZC043001PMsg pMsg) {
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();

        if (!ZYPCommonFunc.hasValue(pMsg.dsSvcCallTpCd)) {
            setErrMsg(pMsg, NSZM0049E);
            return;
        }

        DS_SVC_CALL_TPTMsg dsSvcCallTpTMsg = (DS_SVC_CALL_TPTMsg) ZYPCodeDataUtil.findByCode(DS_SVC_CALL_TP.class, glblCmpyCd, pMsg.dsSvcCallTpCd.getValue());
        if (dsSvcCallTpTMsg == null) {
            setErrMsg(pMsg, NSZM0549E);
            return;
        }

        // START 2019/09/02 K.Fujimoto [QC#52933, MOD]
        // START 2018/08/30 K.Kitachi [QC#22665, ADD]
        if (ZYPConstant.FLG_ON_Y.equals(dsSvcCallTpTMsg.aftHrsFlg.getValue()) && !SVC_CALL_SRC_TP.CROSS_SERVICE.equals(pMsg.svcCallSrcTpCd.getValue())) {
        // if (ZYPConstant.FLG_ON_Y.equals(dsSvcCallTpTMsg.aftHrsFlg.getValue())) {
        // END   2019/09/02 K.Fujimoto [QC#52933, MOD]
            NSZC041001PMsg nszc041001PMsg = createNSZC041001PMsg(pMsg);
            NSZC041001 nszc041001API = new NSZC041001();
            nszc041001API.execute(nszc041001PMsg, this.onBatType);
            // START 2022/07/20 L.Mandanas [QC#60316, MOD]
            //if (nszc041001PMsg.A.getValidCount() > 0 && !SVC_TASK_HLD_RSN.OVER_DUE.equals(nszc041001PMsg.A.no(0).svcTaskHldRsnCd.getValue())) {
            if (nszc041001PMsg.A.getValidCount() > 0 && !SVC_TASK_HLD_RSN.OVER_DUE.equals(nszc041001PMsg.A.no(0).svcTaskHldRsnCd.getValue())
                    && !SVC_TASK_HLD_RSN.CREDIT_LIMIT.equals(nszc041001PMsg.A.no(0).svcTaskHldRsnCd.getValue())) {
            // END 2022/07/20 L.Mandanas [QC#60316, MOD]
                sendMail(pMsg);
// TODO temporarily delete.
//                setErrMsg(pMsg, NSZM1346I);
                return;
            }
        }
        // END 2018/08/30 K.Kitachi [QC#22665, ADD]

        // Add Start 2018/04/10 QC#21868
        if (!convSvcCallTpForSrcTp(pMsg, dsSvcCallTpTMsg)) {
            return;
        }
        // Add End 2018/04/10 QC#21868

        // START 05/23/2016 [QC#447, ADD]
        // Mod Start 2018/06/22 QC#26251
//        if (!eolCheck(pMsg)) {
        if (!eolCheck(pMsg, dsSvcCallTpTMsg)) {
        // Mod End 2018/06/22 QC#26251
            return;
        }
        // END   05/23/2016 [QC#447, ADD]

        // START 2018/06/05 M.Naito [QC#17390, ADD]
        if (!mdseSvcCallEnblCheck(pMsg)) {
            return;
        }
        // END 2018/06/05 M.Naito [QC#17390, ADD]

        NSZC002001PMsg nszc002001PMsg = createNSZC002001PMsgForCreateFSR(pMsg, dsSvcCallTpTMsg);
        NSZC002001 nszc002001API = new NSZC002001();
        nszc002001API.execute(nszc002001PMsg, this.onBatType);
        if (hasError(pMsg, nszc002001PMsg)) {
            return;
        }

        NSZC003001PMsg nszc003001PMsg = createNSZC003001PMsgForCreateFSR(pMsg, nszc002001PMsg, dsSvcCallTpTMsg);
        NSZC003001 nszc003001API = new NSZC003001();
        nszc003001API.execute(nszc003001PMsg, this.onBatType);
        if (hasError(pMsg, nszc003001PMsg)) {
            return;
        }

        String svcTaskNum = nszc002001PMsg.svcTaskNum.getValue();
        ZYPEZDItemValueSetter.setValue(pMsg.taskList.no(0).svcTaskNum, svcTaskNum);
        List<Map<String, Object>> list = getFsrVisit(glblCmpyCd, svcTaskNum);
        if (list.size() > 0) {
            Map<String, Object> result = list.get(0);
            ZYPEZDItemValueSetter.setValue(pMsg.fsrNum, (String) result.get("FSR_NUM"));
            ZYPEZDItemValueSetter.setValue(pMsg.fsrVisitNum, (String) result.get("FSR_VISIT_NUM"));
            ZYPEZDItemValueSetter.setValue(pMsg.invCcyCd, (String) result.get("INV_CCY_CD"));
            ZYPEZDItemValueSetter.setValue(pMsg.ccyExchRate, (BigDecimal) result.get("CCY_EXCH_RATE"));
            ZYPEZDItemValueSetter.setValue(pMsg.pmtTermCashDiscCd, (String) result.get("PMT_TERM_CASH_DISC_CD"));
        }
        pMsg.taskList.setValidCount(1);

        // add start 2016/02/25 CSA Defect#3991
        callCreditCardValidationApi(pMsg);
        // add end 2016/02/25 CSA Defect#3991
        
       // START 2023/05/31 K.Watanabe [QC#61309, ADD]
        createSendLaborAndTravelRatesMessage(glblCmpyCd, pMsg, getSvcTask(glblCmpyCd, svcTaskNum));
       // END 2023/05/31 K.Watanabe [QC#61309, ADD]
    }

    /**
     * updateFSR
     * @param pMsg NSZC043001PMsg
     */
    private void updateFSR(NSZC043001PMsg pMsg) {

        // START 2018/08/14 M.Naito [QC#27472, ADD]
        if (!SVC_TASK_STS.COMPLETED.equals(pMsg.svcTaskStsCd.getValue())) {
            if (!checkSvcTaskSts(pMsg.glblCmpyCd.getValue(), pMsg.taskList.no(0).svcTaskNum.getValue())) {
                setErrMsg(pMsg, NSZM1344E);
                return;
            }
        }
        // END 2018/08/14 M.Naito [QC#27472, ADD]

        // add start 2016/02/08 CSA Defect#2863
        if (SVC_TASK_STS.CANCELLED.equals(pMsg.svcTaskStsCd.getValue())) {
            cancellByModeUpdate(pMsg);
            return;
        }
        if (SVC_TASK_STS.CLOSED.equals(pMsg.svcTaskStsCd.getValue())) {
            callCompletinonApiByModeUpdate(pMsg);
            return;
        }
        // add end 2016/02/08 CSA Defect#2863

        // add start 2016/02/25 CSA Defect#3991
        if (!callCreditCardValidationApi(pMsg)) {
            return;
        }
        // add end 2016/02/25 CSA Defect#3991

        NSXC001001GetFollUpInfoBean follUpBean = new NSXC001001GetFollUpInfoBean();

        NSZC002001PMsg nszc002001PMsg = createNSZC002001PMsgForUpdateFSR(pMsg, follUpBean);
        if (nszc002001PMsg == null) {
            return;
        }
        NSZC002001 nszc002001API = new NSZC002001();
        nszc002001API.execute(nszc002001PMsg, this.onBatType);
        if (hasError(pMsg, nszc002001PMsg)) {
            return;
        }

        NSZC003001PMsg nszc003001PMsg = createNSZC003001PMsgForUpdateFSR(pMsg, nszc002001PMsg, follUpBean);
        NSZC003001 nszc003001API = new NSZC003001();
        nszc003001API.execute(nszc003001PMsg, this.onBatType);
        if (hasError(pMsg, nszc003001PMsg)) {
            return;
        }

        // add end 2016/02/08 CSA Defect#2863
        if (SVC_TASK_STS.COMPLETED.equals(pMsg.svcTaskStsCd.getValue())) {
            callCompletinonApiByModeUpdate(pMsg);
            return;
        }
        // add end 2016/02/08 CSA Defect#2863

    }

    /**
     * cancelFSR
     * @param pMsg NSZC043001PMsg
     */
    private void cancelFSR(NSZC043001PMsg pMsg) {
        NSZC003001PMsg nszc003001PMsg = createNSZC003001PMsgForCancelFSR(pMsg);
        NSZC003001 nszc003001API = new NSZC003001();
        nszc003001API.execute(nszc003001PMsg, this.onBatType);
        if (hasError(pMsg, nszc003001PMsg)) {
            return;
        }

        for (int i = 0; i < pMsg.taskList.getValidCount(); i++) {

            NSZC043001_taskListPMsg taskInfo = (NSZC043001_taskListPMsg) pMsg.taskList.get(i);

            NSZC002001PMsg nszc002001PMsg = createNSZC002001PMsgForCancelFSR(pMsg, taskInfo);
            if (nszc002001PMsg == null) {
                return;
            }

            NSZC002001 nszc002001API = new NSZC002001();
            nszc002001API.execute(nszc002001PMsg, this.onBatType);
            if (hasError(pMsg, nszc002001PMsg)) {
                return;
            }
        }
    }

    private NSZC002001PMsg createNSZC002001PMsgForCreateFSR(NSZC043001PMsg pMsg, DS_SVC_CALL_TPTMsg dsSvcCallTpTMsg) {
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();

        SVC_MACH_MSTRTMsg svcMachMstrTMsg = null;
        if (ZYPCommonFunc.hasValue(pMsg.svcMachMstrPk)) {
            svcMachMstrTMsg = getSvcMachMstr(glblCmpyCd, pMsg.svcMachMstrPk.getValue());
        }

        NSZC002001PMsg nszc002001PMsg = new NSZC002001PMsg();

        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.xxModeCd, NSZC002001Constant.PROCESS_MODE_SUBMIT);
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.soNum, pMsg.soNum);
        if (ZYPCommonFunc.hasValue(pMsg.firstProdCtrlCd)) {
            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.firstProdCtrlCd, pMsg.firstProdCtrlCd);
        } else {
            if (svcMachMstrTMsg != null) {
                MDSETMsg mdseTMsg = getMdse(glblCmpyCd, svcMachMstrTMsg.mdseCd.getValue());
                if (mdseTMsg != null) {
                    ZYPEZDItemValueSetter.setValue(nszc002001PMsg.firstProdCtrlCd, mdseTMsg.firstProdCtrlCd);
                }
            }
        }

        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.svcCallPrtyCd, dsSvcCallTpTMsg.svcCallPrtyCd);
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.svcMachMstrPk, pMsg.svcMachMstrPk);

        if (svcMachMstrTMsg != null) {
            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.custMachCtrlNum, svcMachMstrTMsg.custMachCtrlNum);
            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.serNum, svcMachMstrTMsg.serNum);
            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.mdseCd, svcMachMstrTMsg.mdseCd);

            // mod start 2016/06/09 CSA Defect#8899
            setMdl(glblCmpyCd, svcMachMstrTMsg, nszc002001PMsg);
            // mod end 2016/06/09 CSA Defect#8899

            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.shipToCustCd, svcMachMstrTMsg.curLocNum.getValue());

        } else {

            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.custMachCtrlNum, pMsg.custMachCtrlNum);
            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.serNum, pMsg.serNum);
        }

        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.custTelNum, pMsg.custTelNum);
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.custTelExtnNum, pMsg.custTelExtnNum);
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.svcCustAttnTxt, pMsg.svcCustAttnTxt);
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.custEmlAddr, pMsg.custEmlAddr);
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.custPoNum, pMsg.custPoNum);
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.custPoDt, pMsg.custPoDt);

        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.dsSvcCallTpCd, pMsg.dsSvcCallTpCd);

        String svcBillTpCd = null;
        if (ZYPCommonFunc.hasValue(pMsg.svcBillTpCd)) {
            svcBillTpCd = pMsg.svcBillTpCd.getValue();
        } else {
            svcBillTpCd = dsSvcCallTpTMsg.svcBillTpCd.getValue();
        }
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.svcBillTpCd, svcBillTpCd);

        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.techCd, pMsg.taskList.no(0).techCd);

        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.svcTaskRcvDt, pMsg.svcTaskRcvDt);
        // add start 02/05/2016 CSA UnitTest#92
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.svcModNum, pMsg.svcModNum);
        // add end 02/05/2016 CSA UnitTest#92
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.svcTaskRcvTm, pMsg.svcTaskRcvTm);
        if (ZYPCommonFunc.hasValue(pMsg.machDownFlg)) {
            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.machDownFlg, pMsg.machDownFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.machDownFlg, ZYPConstant.FLG_OFF_N);
        }
        // mod start 2016/02/18 CSA Defect#4471
        if (ZYPCommonFunc.hasValue(pMsg.prtChrgFlg)) {
            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.prtChrgFlg, pMsg.prtChrgFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.prtChrgFlg, ZYPConstant.FLG_OFF_N);
        }
        if (ZYPCommonFunc.hasValue(pMsg.svcLborChrgFlg)) {
            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.svcLborChrgFlg, pMsg.svcLborChrgFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.svcLborChrgFlg, ZYPConstant.FLG_OFF_N);
        }
        // mod start 2016/02/18 CSA Defect#4471
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.firstSvcTaskFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.fsrEventExeUsrId, pMsg.userId);

        // add Start 2016/07/14 CSA SADP
        // START 2016/12/15 T.Tomita [QC#16490, MOD]
        if (ZYPCommonFunc.hasValue(pMsg.taskList.no(0).ovrdFlg) && ZYPConstant.FLG_ON_Y.equals(pMsg.taskList.no(0).ovrdFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.erlStartTs, pMsg.taskList.no(0).erlStartTs);
//            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.lateStartTs, pMsg.taskList.no(0).lateStartTs);
            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.ovrdFlg, ZYPConstant.FLG_ON_Y);
        } else {
            // START 2019/07/31 K.Kitachi [QC#52257, MOD]
//            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.erlStartTs, getErlStartTs(pMsg));
            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.erlStartTs, getErlStartTs(pMsg, pMsg.dsSvcCallTpCd.getValue()));
            // END 2019/07/31 K.Kitachi [QC#52257, MOD]
        }
        // Mod Start 2017/10/02 QC#21273
        // START 2017/07/21 K.Kojima [QC#19987,MOD]
        // ZYPEZDItemValueSetter.setValue(nszc002001PMsg.lateStartTs, getLateStartTs(glblCmpyCd, pMsg.svcMachMstrPk.getValue(), nszc002001PMsg.erlStartTs.getValue(), pMsg.taskList.no(0).lateStartTs.getValue()));
        // START 2022/04/11 K.Kitachi [QC#59899, MOD]
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.lateStartTs,
                getLateStartTs(
                        glblCmpyCd,
                        pMsg.svcMachMstrPk.getValue(),
                        nszc002001PMsg.erlStartTs.getValue(),
                        pMsg.taskList.no(0).lateStartTs.getValue(),
                        nszc002001PMsg.svcTaskRcvDt.getValue(),
                        nszc002001PMsg.machDownFlg.getValue(),
                        nszc002001PMsg.mdlNm.getValue(),
                        pMsg.dsSvcCallTpCd.getValue(),
                        pMsg.shipToUpdCustCd.getValue()));
        // END 2022/04/11 K.Kitachi [QC#59899, MOD]
        // END 2017/07/21 K.Kojima [QC#19987,MOD]
        // Mod End 2017/10/02 QC#21273
        // END 2016/12/15 T.Tomita [QC#16490, MOD]
        // add End 2016/07/14 CSA SADP

        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.svcRgCd, pMsg.taskList.no(0).svcRgCd);
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.svcBrCd, pMsg.taskList.no(0).svcBrCd);
        // mod start 2016/03/03 CSA Defect#4766
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.svcTeamTxt, pMsg.taskList.no(0).svcTeamTxt);
        // mod end 2016/03/03 CSA Defect#4766
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.svcBrMgrPsnCd, pMsg.taskList.no(0).svcBrMgrPsnCd);
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.svcTrtyMgrPsnCd, pMsg.taskList.no(0).svcTrtyMgrPsnCd);
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.svcTeamMgrPsnCd, pMsg.taskList.no(0).svcTeamMgrPsnCd);
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.svcZnCd, pMsg.taskList.no(0).svcZnCd);
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.svcBillChngRsnCd, pMsg.taskList.no(0).svcBillChngRsnCd);

        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.svcTaskSchdDt, pMsg.svcTaskSchdDt);
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.svcTaskSchdTm, pMsg.svcTaskSchdTm);
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.svcTaskSchdByUsrId, pMsg.svcTaskSchdByUsrId);
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.svcCustCllrTxt, pMsg.svcCustCllrTxt);
        // mod start 2016/03/28 CSA Defect#6020
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.mblIntfcFlg, pMsg.taskList.no(0).mblIntfcFlg);
        // mod end 2016/03/28 CSA Defect#6020
        // mod start 2016/04/05 CSA Defect#4495
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.svcCustCllrTelNum, pMsg.taskList.no(0).svcCustCllrTelNum);
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.custAwareChrgFlg, pMsg.taskList.no(0).custAwareChrgFlg);
        // mod end 2016/04/05 CSA Defect#4495
        // mod start 2016/06/09 CSA Defect#9177
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.svcCustCllrTelExtnNum, pMsg.taskList.no(0).svcCustCllrTelExtnNum);
        // mod end 2016/06/09 CSA Defect#9177
        // Add Start 2018/03/22 QC#18713
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.cellPhoNum, pMsg.taskList.no(0).cellPhoNum);
        // Add End 2018/03/22 QC#18713
        // START 2022/04/11 K.Kitachi [QC#59899, ADD]
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.shipToUpdCustCd, pMsg.shipToUpdCustCd);
        // END 2022/04/11 K.Kitachi [QC#59899, ADD]
        return nszc002001PMsg;
    }

    private void setMdl(String glblCmpyCd, SVC_MACH_MSTRTMsg svcMachMstrTMsg, NSZC002001PMsg nszc002001PMsg) {
        // mod start 2016/06/09 CSA Defect#8899
        if (hasValue(svcMachMstrTMsg.svcConfigMstrPk)) {
            List<Map<String, Object>> list = getMdlNm(glblCmpyCd, svcMachMstrTMsg.svcConfigMstrPk.getValue());
            if (list.size() > 0) {
                Map<String, Object> result = list.get(0);
                ZYPEZDItemValueSetter.setValue(nszc002001PMsg.mdlNm, (String) result.get("T_MDL_NM"));
                ZYPEZDItemValueSetter.setValue(nszc002001PMsg.mdlGrpNm, (String) result.get("MDL_GRP_NM"));
                return;
            }
        // mod start 2016/10/05 CSA Defect#14145
        } else {
            List<Map<String, Object>> list = getMdlNmForConfigMtrDtl(glblCmpyCd, svcMachMstrTMsg.svcMachMstrPk.getValue());
            if (list.size() > 0) {
                Map<String, Object> result = list.get(0);
                ZYPEZDItemValueSetter.setValue(nszc002001PMsg.mdlNm, (String) result.get("T_MDL_NM"));
                ZYPEZDItemValueSetter.setValue(nszc002001PMsg.mdlGrpNm, (String) result.get("MDL_GRP_NM"));
                return;
            }
        }
        // mod end 2016/10/05 CSA Defect#14145

        List<Map<String, Object>> listByCpo = getMdlNmByCpo(glblCmpyCd, svcMachMstrTMsg.svcMachMstrPk.getValue());
        if (listByCpo.size() > 0) {
            Map<String, Object> result = listByCpo.get(0);
            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.mdlNm, (String) result.get("T_MDL_NM"));
            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.mdlGrpNm, (String) result.get("MDL_GRP_NM"));
        }
        // mod end 2016/06/09 CSA Defect#8899
    }

    private NSZC002001PMsg createNSZC002001PMsgForUpdateFSR(NSZC043001PMsg pMsg, NSXC001001GetFollUpInfoBean follUpBean) {

        NSZC002001PMsg nszc002001PMsg = new NSZC002001PMsg();

        // mod start 2016/04/28 CSA Defect#2696
        if (ZYPConstant.FLG_ON_Y.equals(pMsg.taskList.no(0).xxOrigFollUpTaskFlg.getValue())) {
            createNSZC002001PMsgForFollUpTaskUpdateFSR(nszc002001PMsg, pMsg, follUpBean);
            return nszc002001PMsg;
        }
        // mod end 2016/04/28 CSA Defect#2696

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String svcTaskNum = pMsg.taskList.no(0).svcTaskNum.getValue();
        SVC_TASKTMsg svcTaskTMsg = getSvcTask(glblCmpyCd, svcTaskNum);
        if (svcTaskTMsg == null) {
            setErrMsg(pMsg, NSZM0486E);
            return null;
        }

        EZDMsg.copy(svcTaskTMsg, null, nszc002001PMsg, null);

        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.xxModeCd, NSZC002001Constant.PROCESS_MODE_UPDATE);
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.svcTaskNum, svcTaskNum);
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.soNum, pMsg.soNum);
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.svcTaskStsCd, pMsg.svcTaskStsCd);
        if (ZYPCommonFunc.hasValue(pMsg.firstProdCtrlCd)) {

            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.firstProdCtrlCd, pMsg.firstProdCtrlCd);
        }

        if (ZYPCommonFunc.hasValue(pMsg.dsSvcCallTpCd)) {

            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.dsSvcCallTpCd, pMsg.dsSvcCallTpCd);
        }

        if (ZYPCommonFunc.hasValue(pMsg.taskList.no(0).svcCallPrtyCd)) {

            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.svcCallPrtyCd, pMsg.taskList.no(0).svcCallPrtyCd);
        }

        SVC_MACH_MSTRTMsg svcMachMstrTMsg = null;
        if (ZYPCommonFunc.hasValue(pMsg.svcMachMstrPk)) {
            svcMachMstrTMsg = getSvcMachMstr(glblCmpyCd, pMsg.svcMachMstrPk.getValue());
        }

        if (svcMachMstrTMsg != null) {
            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.custMachCtrlNum, svcMachMstrTMsg.custMachCtrlNum);
            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.serNum, svcMachMstrTMsg.serNum);
            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.mdseCd, svcMachMstrTMsg.mdseCd);

            // mod start 2016/06/09 CSA Defect#8899
            setMdl(glblCmpyCd, svcMachMstrTMsg, nszc002001PMsg);
            // mod end 2016/06/09 CSA Defect#8899

            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.shipToCustCd, svcMachMstrTMsg.curLocNum);

        } else {
            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.custMachCtrlNum, pMsg.custMachCtrlNum);
            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.serNum, pMsg.serNum);
        }

        if (ZYPCommonFunc.hasValue(pMsg.svcBillTpCd)) {

            nszc002001PMsg.svcBillTpCd.setValue(pMsg.svcBillTpCd.getValue());
        }
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.custTelNum, pMsg.custTelNum);
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.custTelExtnNum, pMsg.custTelExtnNum);
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.svcCustAttnTxt, pMsg.svcCustAttnTxt);
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.custEmlAddr, pMsg.custEmlAddr);
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.custPoNum, pMsg.custPoNum);
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.custPoDt, pMsg.custPoDt);

        if (ZYPCommonFunc.hasValue(pMsg.svcTaskRcvDt)) {

            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.svcTaskRcvDt, pMsg.svcTaskRcvDt.getValue());
        }

        if (ZYPCommonFunc.hasValue(pMsg.svcTaskRcvTm)) {

            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.svcTaskRcvTm, pMsg.svcTaskRcvTm.getValue());
        }

        if (ZYPCommonFunc.hasValue(pMsg.machDownFlg)) {
            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.machDownFlg, pMsg.machDownFlg);
        }

        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.fsrEventExeUsrId, pMsg.userId);

        // START 2019/07/31 K.Kitachi [QC#52257, ADD]
        String dsSvcCallTpCd = svcTaskTMsg.dsSvcCallTpCd.getValue();
        if (ZYPCommonFunc.hasValue(pMsg.dsSvcCallTpCd)) {
            dsSvcCallTpCd = pMsg.dsSvcCallTpCd.getValue();
        }
        // END 2019/07/31 K.Kitachi [QC#52257, ADD]

        // add Start 2016/07/14 CSA SADP
        // START 2016/12/15 T.Tomita [QC#16490, MOD]
        if (ZYPCommonFunc.hasValue(pMsg.taskList.no(0).ovrdFlg) && ZYPConstant.FLG_ON_Y.equals(pMsg.taskList.no(0).ovrdFlg.getValue())) {
            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.erlStartTs, pMsg.taskList.no(0).erlStartTs);
//            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.lateStartTs, pMsg.taskList.no(0).lateStartTs);
            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.ovrdFlg, ZYPConstant.FLG_ON_Y);
        } else if (ZYPCommonFunc.hasValue(pMsg.svcTaskRcvDt) || ZYPCommonFunc.hasValue(pMsg.taskList.no(0).futSvcDt)) {
            // START 2019/07/31 K.Kitachi [QC#52257, MOD]
//            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.erlStartTs, getErlStartTs(pMsg));
            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.erlStartTs, getErlStartTs(pMsg, dsSvcCallTpCd));
            // END 2019/07/31 K.Kitachi [QC#52257, MOD]
        }
        // Mod Start 2017/10/02 QC#21273
        // START 2017/07/21 K.Kojima [QC#19987,MOD]
        // ZYPEZDItemValueSetter.setValue(nszc002001PMsg.lateStartTs, getLateStartTs(glblCmpyCd, pMsg.svcMachMstrPk.getValue(), nszc002001PMsg.erlStartTs.getValue(), pMsg.taskList.no(0).lateStartTs.getValue()));
        // START 2022/04/11 K.Kitachi [QC#59899, MOD]
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.lateStartTs,
                getLateStartTs(
                        glblCmpyCd,
                        pMsg.svcMachMstrPk.getValue(),
                        nszc002001PMsg.erlStartTs.getValue(),
                        pMsg.taskList.no(0).lateStartTs.getValue(),
                        nszc002001PMsg.svcTaskRcvDt.getValue(),
                        nszc002001PMsg.machDownFlg.getValue(),
                        nszc002001PMsg.mdlNm.getValue(),
                        // START 2019/07/31 K.Kitachi [QC#52257, MOD]
//                        null));
//                        dsSvcCallTpCd));
                        // END 2019/07/31 K.Kitachi [QC#52257, MOD]
                        dsSvcCallTpCd,
                        pMsg.shipToUpdCustCd.getValue()));
        // END 2022/04/11 K.Kitachi [QC#59899, MOD]
        // END 2017/07/21 K.Kojima [QC#19987,MOD]
        // Mod End 2017/10/02 QC#21273
        // END 2016/12/15 T.Tomita [QC#16490, MOD]
        // add End 2016/07/14 CSA SADP

        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.svcRgCd, pMsg.taskList.no(0).svcRgCd);
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.svcBrCd, pMsg.taskList.no(0).svcBrCd);
        // mod start 2016/03/03 CSA Defect#4766
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.svcTeamTxt, pMsg.taskList.no(0).svcTeamTxt);
        // mod end 2016/03/03 CSA Defect#4766
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.svcBrMgrPsnCd, pMsg.taskList.no(0).svcBrMgrPsnCd);
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.svcTrtyMgrPsnCd, pMsg.taskList.no(0).svcTrtyMgrPsnCd);
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.svcTeamMgrPsnCd, pMsg.taskList.no(0).svcTeamMgrPsnCd);
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.svcZnCd, pMsg.taskList.no(0).svcZnCd);
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.svcBillChngRsnCd, pMsg.taskList.no(0).svcBillChngRsnCd);

        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.svcTaskSchdDt, pMsg.svcTaskSchdDt);
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.svcTaskSchdTm, pMsg.svcTaskSchdTm);
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.svcTaskSchdByUsrId, pMsg.svcTaskSchdByUsrId);
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.svcCustCllrTxt, pMsg.svcCustCllrTxt);

        // mod start 2016/03/28 CSA Defect#6020
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.mblIntfcFlg, pMsg.taskList.no(0).mblIntfcFlg);
        // mod end 2016/03/28 CSA Defect#6020
        // mod start 2016/04/05 CSA Defect#4495
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.svcCustCllrTelNum, pMsg.taskList.no(0).svcCustCllrTelNum);
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.custAwareChrgFlg, pMsg.taskList.no(0).custAwareChrgFlg);
        // mod end 2016/04/05 CSA Defect#4495
        // mod start 2016/06/09 CSA Defect#9177
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.svcCustCllrTelExtnNum, pMsg.taskList.no(0).svcCustCllrTelExtnNum);
        // mod end 2016/06/09 CSA Defect#9177
        // Add Start 2018/03/22 QC#18713
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.cellPhoNum, pMsg.taskList.no(0).cellPhoNum);
        // Add End 2018/03/22 QC#18713
        // START 2022/04/11 K.Kitachi [QC#59899, ADD]
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.shipToUpdCustCd, pMsg.shipToUpdCustCd);
        // END 2022/04/11 K.Kitachi [QC#59899, ADD]
        return nszc002001PMsg;
    }

    // add 2016/04/28 CSA Defect#2696
    private void createNSZC002001PMsgForFollUpTaskUpdateFSR(NSZC002001PMsg nszc002001PMsg, NSZC043001PMsg pMsg, NSXC001001GetFollUpInfoBean follUpBean) {
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String fsrNum = pMsg.fsrNum.getValue();
        String preSvcTaskNum = pMsg.taskList.no(0).svcTaskNum.getValue();
        FSR_VISITTMsg preFsrVisitTMsg = getFsrVisitBySvcTaskNum(glblCmpyCd, fsrNum, preSvcTaskNum);
        if (preFsrVisitTMsg == null) {
            setErrMsg(pMsg, NSZM0486E);
            nszc002001PMsg = null;
            return;
        }
        if (!ZYPCommonFunc.hasValue(preFsrVisitTMsg.nextFsrVisitNum)) {
            setErrMsg(pMsg, NSZM0970E);
            nszc002001PMsg = null;
            return;
        }
        FSR_VISITTMsg nextFsrVisitTMsg = getFsrVisit(glblCmpyCd, fsrNum, preFsrVisitTMsg.nextFsrVisitNum.getValue());
        if (nextFsrVisitTMsg == null) {
            setErrMsg(pMsg, NSZM0970E);
            nszc002001PMsg = null;
            return;
        }
        SVC_TASKTMsg svcTaskTMsg = getSvcTask(glblCmpyCd, nextFsrVisitTMsg.svcTaskNum.getValue());
        if (svcTaskTMsg == null) {
            setErrMsg(pMsg, NSZM0970E);
            nszc002001PMsg = null;
            return;
        }

        EZDMsg.copy(svcTaskTMsg, null, nszc002001PMsg, null);
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.xxModeCd, NSZC002001Constant.PROCESS_MODE_UPDATE);
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.fsrEventExeUsrId, pMsg.userId);
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.mblIntfcFlg, pMsg.taskList.no(0).mblIntfcFlg);
        // Add Start 2017/09/04 QC#18573
        if (hasValue(pMsg.firstProdCtrlCd)) {
            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.firstProdCtrlCd, pMsg.firstProdCtrlCd);
        } else {
            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.firstProdCtrlCd, svcTaskTMsg.firstProdCtrlCd);
        }
        if (hasValue(pMsg.svcTaskRcvDt)) {
            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.svcTaskRcvDt, pMsg.svcTaskRcvDt);
            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.svcTaskRcvTm, pMsg.svcTaskRcvTm);
        } else {
            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.svcTaskRcvDt, svcTaskTMsg.svcTaskRcvDt);
            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.svcTaskRcvTm, svcTaskTMsg.svcTaskRcvTm);
        }
        // Add End 2017/09/04 QC#18573
        // START 2022/04/11 K.Kitachi [QC#59899, ADD]
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.shipToUpdCustCd, pMsg.shipToUpdCustCd);
        // END 2022/04/11 K.Kitachi [QC#59899, ADD]

        // Shipment
        if(ZYPCommonFunc.hasValue(pMsg.taskList.no(0).ovrdFlg) && ZYPConstant.FLG_ON_Y.equals(pMsg.taskList.no(0).ovrdFlg.getValue())){

            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.ovrdFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.erlStartTs, pMsg.taskList.no(0).erlStartTs);
            // START 2016/12/15 T.Tomita [QC#16490, MOD]
//            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.lateStartTs, pMsg.taskList.no(0).lateStartTs);
            // Mod Start 2017/10/02 QC#21273
            // START 2017/07/21 K.Kojima [QC#19987,MOD]
            // ZYPEZDItemValueSetter.setValue(nszc002001PMsg.lateStartTs, getLateStartTs(glblCmpyCd, svcTaskTMsg.svcMachMstrPk.getValue(), nszc002001PMsg.erlStartTs.getValue(), pMsg.taskList.no(0).lateStartTs.getValue()));
            // START 2022/04/11 K.Kitachi [QC#59899, MOD]
            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.lateStartTs,
                    getLateStartTs(
                            glblCmpyCd,
                            svcTaskTMsg.svcMachMstrPk.getValue(),
                            nszc002001PMsg.erlStartTs.getValue(),
                            pMsg.taskList.no(0).lateStartTs.getValue(),
                            nszc002001PMsg.svcTaskRcvDt.getValue(),
                            nszc002001PMsg.machDownFlg.getValue(),
                            nszc002001PMsg.mdlNm.getValue(),
                            pMsg.dsSvcCallTpCd.getValue(),
                            pMsg.shipToUpdCustCd.getValue()));
            // END 2022/04/11 K.Kitachi [QC#59899, MOD]
            // END 2017/07/21 K.Kojima [QC#19987,MOD]
            // Mod End 2017/10/02 QC#21273
            // END 2016/12/15 T.Tomita [QC#16490, MOD]

        } else {
        // Receiving
            follUpBean.setGlblCmpyCd(pMsg.glblCmpyCd.getValue());
            follUpBean.setSvcPblmCrctTpCd(getSvcPblmCrctTpCd(glblCmpyCd, fsrNum, preFsrVisitTMsg.fsrVisitNum.getValue(), preSvcTaskNum));
            follUpBean.setSvcMachMstrPk(svcTaskTMsg.svcMachMstrPk.getValue());
            follUpBean.setMdlNm(svcTaskTMsg.mdlNm.getValue());
            follUpBean.setSvcTaskRcvDt(svcTaskTMsg.svcTaskRcvDt.getValue());
            follUpBean.setMachDownFlg(svcTaskTMsg.machDownFlg.getValue());
            // START 2022/04/11 K.Kitachi [QC#59899, ADD]
            follUpBean.setShipToUpdCustCd(pMsg.shipToUpdCustCd.getValue());
            // END 2022/04/11 K.Kitachi [QC#59899, ADD]

            if (!NSXC001001GetFollUpInfo.getFollUpInfo(follUpBean)) {
                setErrMsg(pMsg, follUpBean.getXxMsgId());
                return;
            }

            if (ZYPConstant.FLG_ON_Y.equals(follUpBean.getReqTechFlg())) {
                ZYPEZDItemValueSetter.setValue(nszc002001PMsg.techCd, pMsg.taskList.no(0).techCd);
            }

            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.ovrdFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.erlStartTs, follUpBean.getErlStartTs());
            // START 2016/12/15 T.Tomita [QC#16490, MOD]
//            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.lateStartTs, follUpBean.getLateStartTs());
            // Mod Start 2017/10/02 QC#21273
            // START 2017/07/21 K.Kojima [QC#19987,MOD]
            // ZYPEZDItemValueSetter.setValue(nszc002001PMsg.lateStartTs, getLateStartTs(glblCmpyCd, svcTaskTMsg.svcMachMstrPk.getValue(), nszc002001PMsg.erlStartTs.getValue(), follUpBean.getLateStartTs()));
            // START 2022/04/11 K.Kitachi [QC#59899, MOD]
            ZYPEZDItemValueSetter.setValue(nszc002001PMsg.lateStartTs,
                    getLateStartTs(
                            glblCmpyCd,
                            svcTaskTMsg.svcMachMstrPk.getValue(),
                            nszc002001PMsg.erlStartTs.getValue(),
                            follUpBean.getLateStartTs(),
                            nszc002001PMsg.svcTaskRcvDt.getValue(),
                            nszc002001PMsg.machDownFlg.getValue(),
                            nszc002001PMsg.mdlNm.getValue(),
                            pMsg.dsSvcCallTpCd.getValue(),
                            pMsg.shipToUpdCustCd.getValue()));
            // END 2022/04/11 K.Kitachi [QC#59899, MOD]
            // END 2017/07/21 K.Kojima [QC#19987,MOD]
            // Mod End 2017/10/02 QC#21273
            // END 2016/12/15 T.Tomita [QC#16490, MOD]
        }
    }

    private NSZC002001PMsg createNSZC002001PMsgForCancelFSR(NSZC043001PMsg pMsg, NSZC043001_taskListPMsg taskInfo) {

        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String svcTaskNum = taskInfo.svcTaskNum.getValue();

        SVC_TASKTMsg svcTaskTMsg = getSvcTask(glblCmpyCd, svcTaskNum);
        if (svcTaskTMsg == null) {
            setErrMsg(pMsg, NSZM0486E);
            return null;
        }

        NSZC002001PMsg nszc002001PMsg = new NSZC002001PMsg();
        EZDMsg.copy(svcTaskTMsg, null, nszc002001PMsg, null);

        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.xxModeCd, NSZC002001Constant.PROCESS_MODE_CANCEL);
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.svcTaskNum, svcTaskNum);
        // mod start 2016/03/28 CSA Defect#6020
        ZYPEZDItemValueSetter.setValue(nszc002001PMsg.mblIntfcFlg, pMsg.taskList.no(0).mblIntfcFlg);
        // mod end 2016/03/28 CSA Defect#6020 
        return nszc002001PMsg;
    }

    private NSZC003001PMsg createNSZC003001PMsgForCreateFSR(NSZC043001PMsg pMsg, NSZC002001PMsg nszc002001PMsg, DS_SVC_CALL_TPTMsg dsSvcCallTpTMsg) {

        NSZC003001PMsg nszc003001PMsg = new NSZC003001PMsg();

        createNSZC003001PMsgForCrtUpdFSRCommon(pMsg, nszc003001PMsg, NSZC003001.MODE_CREATE_CALL);
        ZYPEZDItemValueSetter.setValue(nszc003001PMsg.xxSvcTaskList.no(0).svcTaskNum, nszc002001PMsg.svcTaskNum);
        ZYPEZDItemValueSetter.setValue(nszc003001PMsg.xxSvcTaskList.no(0).schdDisptEmlFlg, pMsg.taskList.no(0).schdDisptEmlFlg);
        ZYPEZDItemValueSetter.setValue(nszc003001PMsg.svcCallAvoidCd, pMsg.svcCallAvoidCd);
        if (ZYPCommonFunc.hasValue(pMsg.serNum)) {
            ZYPEZDItemValueSetter.setValue(nszc003001PMsg.serNum, pMsg.serNum);
        } else {
            SVC_MACH_MSTRTMsg svcMachMstrTMsg = getSvcMachMstr(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
            if (svcMachMstrTMsg != null) {
                ZYPEZDItemValueSetter.setValue(nszc003001PMsg.serNum, svcMachMstrTMsg.serNum);
            }
        }

        nszc003001PMsg.fsrTpCd.setValue(dsSvcCallTpTMsg.fsrTpCd.getValue());

        // add Start 2016/07/14 CSA SADP
        if (hasValue(pMsg.svcCallIncdtDt) && hasValue(pMsg.svcCallIncdtTm)) {
            ZYPEZDItemValueSetter.setValue(nszc003001PMsg.svcCallIncdtDt, pMsg.svcCallIncdtDt);
            ZYPEZDItemValueSetter.setValue(nszc003001PMsg.svcCallIncdtTm, pMsg.svcCallIncdtTm);
        } else {
            if (ZYPCommonFunc.hasValue(nszc002001PMsg.erlStartTs)) {
                ZYPEZDItemValueSetter.setValue(nszc003001PMsg.svcCallIncdtDt, nszc002001PMsg.erlStartTs.getValue().substring(0, 8));
                ZYPEZDItemValueSetter.setValue(nszc003001PMsg.svcCallIncdtTm, nszc002001PMsg.erlStartTs.getValue().substring(8, 14));
            }
        }
        // add End 2016/07/14 CSA SADP

        ZYPEZDItemValueSetter.setValue(nszc003001PMsg.xxModeCd_AD, pMsg.xxModeCd_AD);

        // START 2018/07/12 M.Naito [QC#13309, ADD]
        // check Temporary Entitlement
        if (DS_SVC_CALL_TP.AHS_SERV_CALL.equals(dsSvcCallTpTMsg.dsSvcCallTpCd.getValue())) {
            String tempEttlNum = getTempEttlNum(pMsg);
            if (hasValue(tempEttlNum)) {
                ZYPEZDItemValueSetter.setValue(nszc003001PMsg.tempEttlNum, tempEttlNum);
            }
        }
        // END 2018/07/12 M.Naito [QC#13309, ADD]

        return nszc003001PMsg;
    }

    private NSZC003001PMsg createNSZC003001PMsgForUpdateFSR(NSZC043001PMsg pMsg, NSZC002001PMsg nszc002001PMsg, NSXC001001GetFollUpInfoBean follUpBean) {

        NSZC003001PMsg nszc003001PMsg = new NSZC003001PMsg();

        // mod start 2016/04/28 CSA Defect#2696
        if (ZYPConstant.FLG_ON_Y.equals(pMsg.taskList.no(0).xxOrigFollUpTaskFlg.getValue())) {
            createNSZC003001PMsgForFollUpTaskUpdateFSR(nszc003001PMsg, pMsg, follUpBean);
            return nszc003001PMsg;
        }
        // mod end 2016/04/28 CSA Defect#2696

        String svcTaskNum = pMsg.taskList.no(0).svcTaskNum.getValue();
        // START 2017/04/24 K.Kitachi [QC#18370, ADD]
        SVC_TASKTMsg svcTaskTMsg = getSvcTask(pMsg.glblCmpyCd.getValue(), svcTaskNum);
        if (svcTaskTMsg == null) {
            setErrMsg(pMsg, NSZM0486E);
            return nszc003001PMsg;
        }
        // END 2017/04/24 K.Kitachi [QC#18370, ADD]

        List<Map<String, Object>> list = getFsrVisit(pMsg.glblCmpyCd.getValue(), svcTaskNum);
        if (list == null || list.size() != 1) {
            setErrMsg(pMsg, NSZM0486E);
            return null;
        }

        FSRTMsg srchFsrTMsg = new FSRTMsg();
        srchFsrTMsg.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
        srchFsrTMsg.fsrNum.setValue((String) list.get(0).get("FSR_NUM"));
        FSRTMsg fsrTMsg = (FSRTMsg) S21ApiTBLAccessor.findByKey(srchFsrTMsg);

        FSR_VISITTMsg srchFsrVisitTMsg = new FSR_VISITTMsg();
        srchFsrVisitTMsg.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
        srchFsrVisitTMsg.fsrNum.setValue(fsrTMsg.fsrNum.getValue());
        srchFsrVisitTMsg.fsrVisitNum.setValue((String) list.get(0).get("FSR_VISIT_NUM"));
        FSR_VISITTMsg fsrVisitTMsg = (FSR_VISITTMsg) S21ApiTBLAccessor.findByKey(srchFsrVisitTMsg);

        EZDMsg.copy(fsrTMsg, null, nszc003001PMsg, null);
        EZDMsg.copy(fsrVisitTMsg, null, nszc003001PMsg.xxSvcTaskList.no(0), null);
        nszc003001PMsg.svcTaskStsCd.setValue(fsrVisitTMsg.fsrVisitStsCd.getValue());

        createNSZC003001PMsgForCrtUpdFSRCommon(pMsg, nszc003001PMsg, NSZC003001.MODE_UPDATE_CALL);
        ZYPEZDItemValueSetter.setValue(nszc003001PMsg.xxSvcTaskList.no(0).svcTaskNum, pMsg.taskList.no(0).svcTaskNum);
        ZYPEZDItemValueSetter.setValue(nszc003001PMsg.xxSvcTaskList.no(0).techSchdToDt, pMsg.taskList.no(0).techSchdToDt);
        ZYPEZDItemValueSetter.setValue(nszc003001PMsg.xxSvcTaskList.no(0).techSchdToTm, pMsg.taskList.no(0).techSchdToTm);
//NA#395 ADD Start
        if (ZYPCommonFunc.hasValue(pMsg.taskList.no(0).techAcptDt)) {
            ZYPEZDItemValueSetter.setValue(nszc003001PMsg.xxSvcTaskList.no(0).techAcptDt, pMsg.taskList.no(0).techAcptDt);
            ZYPEZDItemValueSetter.setValue(nszc003001PMsg.xxSvcTaskList.no(0).techAcptTm, pMsg.taskList.no(0).techAcptTm);
        }
        if (ZYPCommonFunc.hasValue(pMsg.taskList.no(0).fsrVisitDisptDt)) {
            ZYPEZDItemValueSetter.setValue(nszc003001PMsg.xxSvcTaskList.no(0).fsrVisitDisptDt, pMsg.taskList.no(0).fsrVisitDisptDt);
            ZYPEZDItemValueSetter.setValue(nszc003001PMsg.xxSvcTaskList.no(0).fsrVisitDisptTm, pMsg.taskList.no(0).fsrVisitDisptTm);
        }
//NA#395 ADD End

        if (ZYPCommonFunc.hasValue(pMsg.svcTaskStsCd)) {

            ZYPEZDItemValueSetter.setValue(nszc003001PMsg.svcTaskStsCd, pMsg.svcTaskStsCd);
        }
        ZYPEZDItemValueSetter.setValue(nszc003001PMsg.serNum, pMsg.serNum);

        DS_SVC_CALL_TPTMsg dsSvcCallTpTMsg = null;
        if (ZYPCommonFunc.hasValue(pMsg.dsSvcCallTpCd)) {

            dsSvcCallTpTMsg = (DS_SVC_CALL_TPTMsg) ZYPCodeDataUtil.findByCode(DS_SVC_CALL_TP.class, pMsg.glblCmpyCd.getValue(), pMsg.dsSvcCallTpCd.getValue());
            if (dsSvcCallTpTMsg == null) {
                setErrMsg(pMsg, NSZM0549E);
                return nszc003001PMsg;
            } else {
                nszc003001PMsg.fsrTpCd.setValue(dsSvcCallTpTMsg.fsrTpCd.getValue());
            }
        }

        // add Start 2016/07/14 CSA SADP
        if (hasValue(pMsg.svcCallIncdtDt) && hasValue(pMsg.svcCallIncdtTm)) {
            ZYPEZDItemValueSetter.setValue(nszc003001PMsg.svcCallIncdtDt, pMsg.svcCallIncdtDt);
            ZYPEZDItemValueSetter.setValue(nszc003001PMsg.svcCallIncdtTm, pMsg.svcCallIncdtTm);
        // START 2017/04/24 K.Kitachi [QC#18370, MOD]
//        } else {
        } else if (ZYPConstant.FLG_ON_Y.equals(svcTaskTMsg.firstSvcTaskFlg.getValue()) && ZYPCommonFunc.hasValue(nszc002001PMsg.erlStartTs) && nszc002001PMsg.erlStartTs.getValue().length() >= TS_LEN) {
        // END 2017/04/24 K.Kitachi [QC#18370, MOD]
            ZYPEZDItemValueSetter.setValue(nszc003001PMsg.svcCallIncdtDt, nszc002001PMsg.erlStartTs.getValue().substring(0, 8));
            ZYPEZDItemValueSetter.setValue(nszc003001PMsg.svcCallIncdtTm, nszc002001PMsg.erlStartTs.getValue().substring(8, 14));
        }
        // add End 2016/07/14 CSA SADP

        return nszc003001PMsg;
    }

    // add 2016/04/28 CSA Defect#2696
    private void createNSZC003001PMsgForFollUpTaskUpdateFSR(NSZC003001PMsg nszc003001PMsg, NSZC043001PMsg pMsg, NSXC001001GetFollUpInfoBean follUpBean) {
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String fsrNum = pMsg.fsrNum.getValue();
        String preSvcTaskNum = pMsg.taskList.no(0).svcTaskNum.getValue();
        FSR_VISITTMsg preFsrVisitTMsg = getFsrVisitBySvcTaskNum(glblCmpyCd, fsrNum, preSvcTaskNum);
        if (preFsrVisitTMsg == null) {
            setErrMsg(pMsg, NSZM0486E);
            nszc003001PMsg = null;
            return;
        }
        if (!ZYPCommonFunc.hasValue(preFsrVisitTMsg.nextFsrVisitNum)) {
            setErrMsg(pMsg, NSZM0970E);
            nszc003001PMsg = null;
            return;
        }
        FSR_VISITTMsg nextFsrVisitTMsg = getFsrVisit(glblCmpyCd, fsrNum, preFsrVisitTMsg.nextFsrVisitNum.getValue());
        if (nextFsrVisitTMsg == null) {
            setErrMsg(pMsg, NSZM0970E);
            nszc003001PMsg = null;
            return;
        }
        SVC_TASKTMsg svcTaskTMsg = getSvcTask(glblCmpyCd, nextFsrVisitTMsg.svcTaskNum.getValue());
        if (svcTaskTMsg == null) {
            setErrMsg(pMsg, NSZM0970E);
            nszc003001PMsg = null;
            return;
        }
        FSRTMsg srchFsrTMsg = new FSRTMsg();
        srchFsrTMsg.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
        srchFsrTMsg.fsrNum.setValue(pMsg.fsrNum.getValue());
        FSRTMsg fsrTMsg = (FSRTMsg) S21ApiTBLAccessor.findByKey(srchFsrTMsg);

        EZDMsg.copy(fsrTMsg, null, nszc003001PMsg, null);
        EZDMsg.copy(nextFsrVisitTMsg, null, nszc003001PMsg.xxSvcTaskList.no(0), null);

        ZYPEZDItemValueSetter.setValue(nszc003001PMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(nszc003001PMsg.xxBizProcTp, NSZC003001.MODE_UPDATE_CALL);
        ZYPEZDItemValueSetter.setValue(nszc003001PMsg.slsDt, pMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(nszc003001PMsg.fsrNum, pMsg.fsrNum);
        ZYPEZDItemValueSetter.setValue(nszc003001PMsg.xxSvcTaskList.no(0).userId, pMsg.userId);

        // Receiving
//        if (!ZYPCommonFunc.hasValue(pMsg.taskList.no(0).erlStartTs)) {
        if(!ZYPCommonFunc.hasValue(pMsg.taskList.no(0).ovrdFlg) || ZYPConstant.FLG_OFF_N.equals(pMsg.taskList.no(0).ovrdFlg.getValue())){
            ZYPEZDItemValueSetter.setValue(nszc003001PMsg.svcTaskStsCd, follUpBean.getFsrVisitStsCd());
            if (ZYPConstant.FLG_ON_Y.equals(follUpBean.getReqTechFlg())) {
                ZYPEZDItemValueSetter.setValue(nszc003001PMsg.xxSvcTaskList.no(0).techCd, pMsg.taskList.no(0).techCd);
            }
            ZYPEZDItemValueSetter.setValue(nszc003001PMsg.xxSvcTaskList.no(0).svcAsgTpCd, follUpBean.getSvcAsgTpCd());
        }

        nszc003001PMsg.xxSvcTaskList.setValidCount(1);
    }

    private void createNSZC003001PMsgForCrtUpdFSRCommon(NSZC043001PMsg pMsg, NSZC003001PMsg nszc003001PMsg, String xxModeCode) {

        ZYPEZDItemValueSetter.setValue(nszc003001PMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(nszc003001PMsg.xxBizProcTp, xxModeCode);
//NA#1107 ADD Start
        ZYPEZDItemValueSetter.setValue(nszc003001PMsg.slsDt, pMsg.slsDt);
//NA#1107 ADD End
        ZYPEZDItemValueSetter.setValue(nszc003001PMsg.fsrNum, pMsg.fsrNum);
        ZYPEZDItemValueSetter.setValue(nszc003001PMsg.xxSvcTaskList.no(0).techCd, pMsg.taskList.no(0).techCd);
        ZYPEZDItemValueSetter.setValue(nszc003001PMsg.xxSvcTaskList.no(0).techSchdFromDt, pMsg.taskList.no(0).techSchdFromDt);
        ZYPEZDItemValueSetter.setValue(nszc003001PMsg.xxSvcTaskList.no(0).techSchdFromTm, pMsg.taskList.no(0).techSchdFromTm);
        ZYPEZDItemValueSetter.setValue(nszc003001PMsg.xxSvcTaskList.no(0).userId, pMsg.userId);
        nszc003001PMsg.xxSvcTaskList.setValidCount(1);

        ZYPEZDItemValueSetter.setValue(nszc003001PMsg.svcPblmTpCd, pMsg.svcPblmTpCd);
        ZYPEZDItemValueSetter.setValue(nszc003001PMsg.svcCallSrcTpCd, pMsg.svcCallSrcTpCd);
        ZYPEZDItemValueSetter.setValue(nszc003001PMsg.svcCallRqstOwnrTocCd, pMsg.svcCallRqstOwnrTocCd);
        ZYPEZDItemValueSetter.setValue(nszc003001PMsg.custCseNum, pMsg.custCseNum);
        ZYPEZDItemValueSetter.setValue(nszc003001PMsg.ittNum, pMsg.ittNum);
        // START 2019/11/06 [QC#53849, MOD]
        FSRTMsg srchFsrTMsg = new FSRTMsg();
        srchFsrTMsg.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
        srchFsrTMsg.fsrNum.setValue(pMsg.fsrNum.getValue());
        FSRTMsg fsrTMsg = (FSRTMsg) S21FastTBLAccessor.findByKey(srchFsrTMsg);

        SVC_MACH_MSTRTMsg srchSvcMachMstr = new SVC_MACH_MSTRTMsg();
        srchSvcMachMstr.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
        srchSvcMachMstr.svcMachMstrPk.setValue(pMsg.svcMachMstrPk.getValue());
        SVC_MACH_MSTRTMsg svcMachMstr = (SVC_MACH_MSTRTMsg) S21FastTBLAccessor.findByKey(srchSvcMachMstr);
        // START 2023/09/15 K.Watanabe [QC#61310, ADD]
        boolean isShowRoomMachine = isShowRoomMachine(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue(), pMsg.slsDt.getValue());
        // END 2023/09/15 K.Watanabe [QC#61310, ADD]

        if (fsrTMsg == null) {
            // START 2023/07/14 K.Watanabe [QC#61310, MOD]
            // ZYPEZDItemValueSetter.setValue(nszc003001PMsg.billToCustCd, svcMachMstr.billToLocNum.getValue());
            // ZYPEZDItemValueSetter.setValue(nszc003001PMsg.billToCustAcctCd, svcMachMstr.billToAcctNum.getValue());
            // START 2023/09/15 K.Watanabe [QC#61310, MOD]
            //if (isShowRoomMachine(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue(), pMsg.slsDt.getValue())) {
            if (isShowRoomMachine) {
            // END 2023/09/15 K.Watanabe [QC#61310, MOD]
                ZYPEZDItemValueSetter.setValue(nszc003001PMsg.billToCustCd, ZYPCodeDataUtil.getVarCharConstValue(NSZC0430_DEF_BILL_TO_CUST_CD, pMsg.glblCmpyCd.getValue()));
                ZYPEZDItemValueSetter.setValue(nszc003001PMsg.billToCustAcctCd, ZYPCodeDataUtil.getVarCharConstValue(NSZC0430_DEF_BILL_TO_ACCT_CD, pMsg.glblCmpyCd.getValue()));
            } else {
                ZYPEZDItemValueSetter.setValue(nszc003001PMsg.billToCustCd, svcMachMstr.billToLocNum.getValue());
                ZYPEZDItemValueSetter.setValue(nszc003001PMsg.billToCustAcctCd, svcMachMstr.billToAcctNum.getValue());
            }
            // END 2023/07/14 K.Watanabe [QC#61310, MOD]
        } else {
            ZYPEZDItemValueSetter.setValue(nszc003001PMsg.billToCustCd, fsrTMsg.billToCustCd.getValue());
            ZYPEZDItemValueSetter.setValue(nszc003001PMsg.billToCustAcctCd, fsrTMsg.billToCustAcctCd.getValue());
        }
        // END 2019/11/06 [QC#53849, MOD]
        ZYPEZDItemValueSetter.setValue(nszc003001PMsg.pmtTermCashDiscCd, pMsg.pmtTermCashDiscCd);
        ZYPEZDItemValueSetter.setValue(nszc003001PMsg.billToCustUpdFlg, pMsg.billToCustUpdFlg);
        ZYPEZDItemValueSetter.setValue(nszc003001PMsg.billToUpdCustCd, pMsg.billToUpdCustCd);
        // add start 2016/10/06 CSA Defect#14950
        if (!hasValue(nszc003001PMsg.billToCustCd) && hasValue(nszc003001PMsg.billToUpdCustCd)) {
            //overwrite billToCustCd
            ZYPEZDItemValueSetter.setValue(nszc003001PMsg.billToCustCd, nszc003001PMsg.billToUpdCustCd);
            String billToAcct = getBillToAcct(pMsg, nszc003001PMsg.billToUpdCustCd.getValue());
            ZYPEZDItemValueSetter.setValue(nszc003001PMsg.billToCustAcctCd, billToAcct);
        }
        // add end 2016/10/06 CSA Defect#14950
        ZYPEZDItemValueSetter.setValue(nszc003001PMsg.svcMachMstrPk, pMsg.svcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(nszc003001PMsg.shipToCustCd, svcMachMstr.curLocNum.getValue());
        // START 2023/09/15 K.Watanabe [QC#61310, MOD]
        //ZYPEZDItemValueSetter.setValue(nszc003001PMsg.shipToCustAcctCd, svcMachMstr.curLocAcctNum.getValue());
        if (isShowRoomMachine) {
            ZYPEZDItemValueSetter.setValue(nszc003001PMsg.shipToCustAcctCd, getSellToCustCd(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue()));
        } else {
            ZYPEZDItemValueSetter.setValue(nszc003001PMsg.shipToCustAcctCd, svcMachMstr.curLocAcctNum.getValue());
        }
        // END 2023/09/15 K.Watanabe [QC#61310, MOD]

        ZYPEZDItemValueSetter.setValue(nszc003001PMsg.shipToCustUpdFlg, pMsg.shipToCustUpdFlg);
        ZYPEZDItemValueSetter.setValue(nszc003001PMsg.shipToUpdCustCd, pMsg.shipToUpdCustCd);

        // START 2019/01/21 W.Honda [QC#28650, ADD]
        ZYPEZDItemValueSetter.setValue(nszc003001PMsg.xxSvcTaskList.no(0).techAcptFlg, pMsg.taskList.no(0).techAcptFlg);
        // END 2019/01/21 W.Honda [QC#28650, ADD]
        ZYPEZDItemValueSetter.setValue(nszc003001PMsg.xxSvcTaskList.no(0).techAcptDt, pMsg.taskList.no(0).techAcptDt);
        ZYPEZDItemValueSetter.setValue(nszc003001PMsg.xxSvcTaskList.no(0).techAcptTm, pMsg.taskList.no(0).techAcptTm);
        ZYPEZDItemValueSetter.setValue(nszc003001PMsg.xxSvcTaskList.no(0).fsrVisitDisptDt, pMsg.taskList.no(0).fsrVisitDisptDt);
        ZYPEZDItemValueSetter.setValue(nszc003001PMsg.xxSvcTaskList.no(0).fsrVisitDisptTm, pMsg.taskList.no(0).fsrVisitDisptTm);
        ZYPEZDItemValueSetter.setValue(nszc003001PMsg.xxSvcTaskList.no(0).mblIntfcFlg, pMsg.taskList.no(0).mblIntfcFlg);

        ZYPEZDItemValueSetter.setValue(nszc003001PMsg.xxSvcTaskList.no(0).svcLttdNum, pMsg.taskList.no(0).svcLttdNum);
        ZYPEZDItemValueSetter.setValue(nszc003001PMsg.xxSvcTaskList.no(0).svcLgtdNum, pMsg.taskList.no(0).svcLgtdNum);
        ZYPEZDItemValueSetter.setValue(nszc003001PMsg.xxSvcTaskList.no(0).svcUnAsgRsnTxt, pMsg.taskList.no(0).svcUnAsgRsnTxt);
        ZYPEZDItemValueSetter.setValue(nszc003001PMsg.xxSvcTaskList.no(0).svcAsgTpCd, pMsg.taskList.no(0).svcAsgTpCd);

        // mod Start 2016/06/07 CSA Defect#3727
        String futSvcDt = pMsg.taskList.no(0).futSvcDt.getValue();
        String futSvcTm = pMsg.taskList.no(0).futSvcTm.getValue();

        if (ZYPConstant.FLG_ON_Y.equals(pMsg.xxTmZnConvtFlg.getValue()) && hasValue(futSvcDt)) {

            StringBuilder sb = new StringBuilder();
            sb.append(futSvcDt);
            if (ZYPCommonFunc.hasValue(futSvcTm)) {
                sb.append(futSvcTm);
            } else {
                sb.append(TM_START_OFTHEDAY);
            }
            Map<String, String> shipInfoMap = getShipToInfo(pMsg);
            if (shipInfoMap != null) {
                SvcTimeZoneInfo tzFutSvcTs = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, sb.toString(), shipInfoMap.get("CTRY_CD"), shipInfoMap.get("POST_CD"));
                if (tzFutSvcTs != null) {
                    futSvcDt = tzFutSvcTs.getDateTime().substring(0, 8);
                    futSvcTm = tzFutSvcTs.getDateTime().substring(8, 14);
                }
            }
        }
        ZYPEZDItemValueSetter.setValue(nszc003001PMsg.xxSvcTaskList.no(0).futSvcDt, futSvcDt);
        ZYPEZDItemValueSetter.setValue(nszc003001PMsg.xxSvcTaskList.no(0).futSvcTm, futSvcTm);
        // mod End 2016/06/07 CSA Defect#3727

        setSvcMemoList(pMsg, nszc003001PMsg);

        setAttachedFileList(pMsg, nszc003001PMsg);

    }

    private NSZC003001PMsg createNSZC003001PMsgForCancelFSR(NSZC043001PMsg pMsg) {

        NSZC003001PMsg nszc003001PMsg = new NSZC003001PMsg();
        ZYPEZDItemValueSetter.setValue(nszc003001PMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(nszc003001PMsg.xxBizProcTp, NSZC003001Constant.MODE_CANCEL_CALL);
        ZYPEZDItemValueSetter.setValue(nszc003001PMsg.fsrNum, pMsg.fsrNum);

        for (int i = 0; i < pMsg.taskList.getValidCount(); i++) {

            ZYPEZDItemValueSetter.setValue(nszc003001PMsg.xxSvcTaskList.no(i).svcTaskNum, pMsg.taskList.no(i).svcTaskNum);
            ZYPEZDItemValueSetter.setValue(nszc003001PMsg.xxSvcTaskList.no(i).schdDisptEmlFlg, pMsg.taskList.no(i).schdDisptEmlFlg);
            ZYPEZDItemValueSetter.setValue(nszc003001PMsg.xxSvcTaskList.no(i).userId, pMsg.userId);
            ZYPEZDItemValueSetter.setValue(nszc003001PMsg.xxSvcTaskList.no(i).mblIntfcFlg, pMsg.taskList.no(i).mblIntfcFlg);

            nszc003001PMsg.xxSvcTaskList.setValidCount(i + 1);
        }

        ZYPEZDItemValueSetter.setValue(nszc003001PMsg.svcTaskStsCd, SVC_TASK_STS.CANCELLED);

        setSvcMemoList(pMsg, nszc003001PMsg);

        setAttachedFileList(pMsg, nszc003001PMsg);

        return nszc003001PMsg;
    }

    private void setAttachedFileList(NSZC043001PMsg pMsg, NSZC003001PMsg nszc003001PMsg) {
        int cnt = pMsg.attachedFileList.getValidCount();
        for (int i = 0; i < cnt; i++) {
            EZDMsg.copy(pMsg.attachedFileList.no(i), null, nszc003001PMsg.attachedFileList.no(i), null);
        }
        nszc003001PMsg.attachedFileList.setValidCount(cnt);
    }

    private void setSvcMemoList(NSZC043001PMsg pMsg, NSZC003001PMsg nszc003001PMsg) {
        int cnt = pMsg.svcMemoList.getValidCount();
        for (int i = 0; i < cnt; i++) {
            EZDMsg.copy(pMsg.svcMemoList.no(i), null, nszc003001PMsg.svcMemoList.no(i), null);
        }
        nszc003001PMsg.svcMemoList.setValidCount(cnt);
    }

    private void init(NSZC043001PMsg pMsg, final ONBATCH_TYPE onBatTp) {

        msgMap = new S21ApiMessageMap(pMsg);

        this.onBatType = onBatTp;
        // START 2018/08/30 K.Kitachi [QC#22665, MOD]
        // Add Start 2018/04/10 QC#21868
//        this.curTime = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmss").substring(8);
        // Add End 2018/04/10 QC#21868
        this.systemTimeStamp = ZYPDateUtil.getCurrentSystemTime("yyyyMMddHHmmss");
        this.curTime = this.systemTimeStamp.substring(8);
        this.infoMsgList = new ArrayList<String>();
        // END 2018/08/30 K.Kitachi [QC#22665, MOD]

        // add start 2016/08/02 CSA Defect#12793
        for (int i = 0; i < pMsg.taskList.getValidCount(); i++) {
            String erlStartTs = pMsg.taskList.no(i).erlStartTs.getValue();
            String lateStartTs = pMsg.taskList.no(i).lateStartTs.getValue();
            if (hasValue(erlStartTs) && erlStartTs.length() <= 14) {
                ZYPEZDItemValueSetter.setValue(pMsg.taskList.no(i).erlStartTs, erlStartTs + TS_POSTFIX);
            }
            if (hasValue(lateStartTs) && lateStartTs.length() <= 14) {
                ZYPEZDItemValueSetter.setValue(pMsg.taskList.no(i).lateStartTs, lateStartTs + TS_POSTFIX);
            }
        }
        // add end 2016/08/02 CSA Defect#12793
        // add start 2016/09/28 CSA Defect#14251
        if (MODE_CREATE_FSR.equals(pMsg.xxModeCd.getValue()) || MODE_UPDATE_FSR.equals(pMsg.xxModeCd.getValue())) {
            if (hasValue(pMsg.crCardCustRefNum)) {
                ZYPEZDItemValueSetter.setValue(pMsg.pmtTermCashDiscCd, PMT_TERM_CASH_DISC.CREDIT_CARD);
            }
        }
        // add end 2016/09/28 CSA Defect#14251
        // START 2022/04/11 K.Kitachi [QC#59899, ADD]
        if (!hasValue(pMsg.shipToUpdCustCd) && hasValue(pMsg.fsrNum)) {
            Map<String, String> shipInfoMap = getShipToInfo(pMsg);
            if (shipInfoMap != null) {
                ZYPEZDItemValueSetter.setValue(pMsg.shipToUpdCustCd, shipInfoMap.get("SHIP_TO_UPD_CUST_CD"));
            }
        }
        // END 2022/04/11 K.Kitachi [QC#59899, ADD]
    }

    private SVC_MACH_MSTRTMsg getSvcMachMstr(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        SVC_MACH_MSTRTMsg tMsg = new SVC_MACH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.svcMachMstrPk, svcMachMstrPk);
        return (SVC_MACH_MSTRTMsg) S21ApiTBLAccessor.findByKey(tMsg);
    }

    private SVC_TASKTMsg getSvcTask(String glblCmpyCd, String svcTaskNum) {
        SVC_TASKTMsg tMsg = new SVC_TASKTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.svcTaskNum, svcTaskNum);
        return (SVC_TASKTMsg) S21ApiTBLAccessor.findByKey(tMsg);
    }

    // START 2023/05/31 K.Watanabe [QC#61309, ADD]
    private SVC_BILL_TPTMsg getSvcBillTp(String glblCmpyCd, String svcBillTpCd) {
        SVC_BILL_TPTMsg tMsg = new SVC_BILL_TPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.svcBillTpCd, svcBillTpCd);
        return (SVC_BILL_TPTMsg) S21ApiTBLAccessor.findByKey(tMsg);
    }
    // START 2023/05/31 K.Watanabe [QC#61309, ADD]

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getMdlNm(String glblCmpyCd, BigDecimal svcConfigMstrPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("svcConfigMstrPk", svcConfigMstrPk);
        return (List<Map<String, Object>>) ssmBatClnt.queryObjectList("getMdlNm", paramMap);
    }

    @SuppressWarnings("unchecked")
    // add 2016/06/09 CSA Defect#8899
    private List<Map<String, Object>> getMdlNmByCpo(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("svcMachMstrPk", svcMachMstrPk);
        return (List<Map<String, Object>>) ssmBatClnt.queryObjectList("getMdlNmByCpo", paramMap);
    }
    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> getFsrVisit(String glblCmpyCd, String svcTaskNum) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("svcTaskNum", svcTaskNum);
        return (List<Map<String, Object>>) ssmBatClnt.queryObjectList("getFsrVisit", paramMap);
    }

    // add 2016/04/28 CSA Defect#2696
   private FSR_VISITTMsg getFsrVisitBySvcTaskNum(String glblCmpyCd, String fsrNum, String svcTaskNum) {
        FSR_VISITTMsg tMsg = new FSR_VISITTMsg();
        tMsg.setSQLID("003");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("fsrNum01", fsrNum);
        tMsg.setConditionValue("svcTaskNum01", svcTaskNum);
        FSR_VISITTMsgArray outArray = (FSR_VISITTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
        if (outArray.getValidCount() > 0) {
            return outArray.no(0);
        }
        return null;
    }
   // add 2016/04/28 CSA Defect#2696
    private FSR_VISITTMsg getFsrVisit(String glblCmpyCd, String fsrNum, String fsrVisitNum) {
        FSR_VISITTMsg paramFsrTMsg = new FSR_VISITTMsg();
        ZYPEZDItemValueSetter.setValue(paramFsrTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(paramFsrTMsg.fsrNum, fsrNum);
        ZYPEZDItemValueSetter.setValue(paramFsrTMsg.fsrVisitNum, fsrVisitNum);

        return (FSR_VISITTMsg) S21ApiTBLAccessor.findByKey(paramFsrTMsg);
    }
    // add 2016/04/28 CSA Defect#2696, mod 2016/06/07 CSA Defect#9218
    private String getSvcPblmCrctTpCd(String glblCmpyCd, String fsrNum, String fsrVisitNum, String svcTaskNum) {
        FSR_VISIT_TASKTMsg paramFsrTMsg = new FSR_VISIT_TASKTMsg();
        ZYPEZDItemValueSetter.setValue(paramFsrTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(paramFsrTMsg.fsrNum, fsrNum);
        ZYPEZDItemValueSetter.setValue(paramFsrTMsg.fsrVisitNum, fsrVisitNum);
        ZYPEZDItemValueSetter.setValue(paramFsrTMsg.svcTaskNum, svcTaskNum);

        FSR_VISIT_TASKTMsg outTMsg = (FSR_VISIT_TASKTMsg) S21ApiTBLAccessor.findByKey(paramFsrTMsg);
        if (outTMsg == null) {
            return null;
        }
        return outTMsg.svcPblmCrctTpCd.getValue();
    }

    private MDSETMsg getMdse(String glblCmpyCd, String mdseCd) {

        MDSETMsg inTMsg = new MDSETMsg();
        inTMsg.setSQLID("001");
        inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inTMsg.setConditionValue("mdseCd01", mdseCd);
        inTMsg.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
        MDSETMsgArray tMsgArray = (MDSETMsgArray) S21ApiTBLAccessor.findByCondition(inTMsg);

        if (tMsgArray.getValidCount() <= 0) {
            return null;
        }
        return tMsgArray.no(0);
    }

    // START 2019/07/31 K.Kitachi [QC#52257, MOD]
//    private String getErlStartTs(NSZC043001PMsg pMsg) {
    private String getErlStartTs(NSZC043001PMsg pMsg, String dsSvcCallTpCd) {
    // END 2019/07/31 K.Kitachi [QC#52257, MOD]

        StringBuilder sb = new StringBuilder();

        sb.append(pMsg.svcTaskRcvDt.getValue());

        if (ZYPCommonFunc.hasValue(pMsg.svcTaskRcvTm)) {

            sb.append(pMsg.svcTaskRcvTm.getValue());

        } else {

            sb.append(TM_START_OFTHEDAY);
        }
        sb.append(TS_POSTFIX);

        if (ZYPCommonFunc.hasValue(pMsg.taskList.no(0).futSvcDt)) {

            sb = new StringBuilder();
            sb.append(pMsg.taskList.no(0).futSvcDt.getValue());

            if (ZYPCommonFunc.hasValue(pMsg.taskList.no(0).futSvcTm)) {

                sb.append(pMsg.taskList.no(0).futSvcTm.getValue());

            } else {

                sb.append(TM_START_OFTHEDAY);
            }
            sb.append(TS_POSTFIX);

            // mod Start 2016/06/07 CSA Defect#3727
            if (ZYPConstant.FLG_ON_Y.equals(pMsg.xxTmZnConvtFlg.getValue())) {
                Map<String, String> shipInfoMap = getShipToInfo(pMsg);
                if (shipInfoMap != null) {
                    SvcTimeZoneInfo tzFutSvcTs = NSXC001001SvcTimeZone.convertTime(NSXC001001SvcTimeZone.MODE2_LOC_TO_SYS, sb.toString(), shipInfoMap.get("CTRY_CD"), shipInfoMap.get("POST_CD"));
                    if (tzFutSvcTs != null) {
                        sb = new StringBuilder();
                        sb.append(tzFutSvcTs.getDateTime());
                    }
                }
            }
            // mod End 2016/06/07 CSA Defect#3727
        }

        // add start 2016/02/10 CSA Defect#3727
        String cvtErlStartTs = sb.toString();
        // Mod Start 2017/10/02 QC#21273
        // START 2019/07/31 K.Kitachi [QC#52257, DEL]
//        String dsSvcCallTpCd = null;
//        if (MODE_CREATE_FSR.equals(pMsg.xxModeCd.getValue())) {
//            dsSvcCallTpCd = pMsg.dsSvcCallTpCd.getValue();
//        }
        // END 2019/07/31 K.Kitachi [QC#52257, DEL]
        // START 2022/04/11 K.Kitachi [QC#59899, MOD]
//        cvtErlStartTs = NSXC001001GetRspTime.getEndDateByRspTimeWithTimeDiff(pMsg.glblCmpyCd.getValue(), BigDecimal.ZERO, pMsg.svcMachMstrPk.getValue(), sb.substring(0, 8), sb.substring(8, 14), false, dsSvcCallTpCd);
        cvtErlStartTs = NSXC001001GetRspTime.getEndDateByRspTimeWithTimeDiff(pMsg.glblCmpyCd.getValue(), BigDecimal.ZERO, pMsg.svcMachMstrPk.getValue(), sb.substring(0, 8), sb.substring(8, 14), false, dsSvcCallTpCd, pMsg.shipToUpdCustCd.getValue());
        // END 2022/04/11 K.Kitachi [QC#59899, MOD]
        // Mod End 2017/10/02 QC#21273
        return cvtErlStartTs;
        // add end 2016/02/10 CSA Defect#3727
    }

    // add 2016/06/07 CSA Defect#3727
    private Map<String, String> getShipToInfo(NSZC043001PMsg pMsg) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("fsrNum", pMsg.fsrNum.getValue());
        paramMap.put("svcMachMstrPk", pMsg.svcMachMstrPk.getValue());
        return (Map<String, String>) ssmBatClnt.queryObject("getShipToInfo", paramMap);
    }

    private boolean hasError(NSZC043001PMsg pMsg, EZDPMsg callApiPMsg) {
        boolean result = false;
        List<String> errList = S21ApiUtil.getXxMsgIdList(callApiPMsg);
        if (errList.size() > 0) {
            // START 2018/08/30 K.Kitachi [QC#22665, MOD]
//            result = true;
//            for (String msgId : errList) {
//                setErrMsg(pMsg, msgId);
//            }
            for (String msgId : errList) {
                if (msgId.endsWith("I")) {
                    this.infoMsgList.add(msgId);
                    continue;
                }
                setErrMsg(pMsg, msgId);
                result = true;
            }
            // END 2018/08/30 K.Kitachi [QC#22665, MOD]
        }
        return result;
    }

    private void setErrMsg(NSZC043001PMsg pMsg, String msgId) {
        msgMap.addXxMsgId(msgId);
        msgMap.flush();
    }

    private void setErrMsg(NSZC043001PMsg pMsg, String msgId, int idx) {
        if (!ZYPCommonFunc.hasValue(pMsg.taskList.no(idx).xxMsgId)) {
            ZYPEZDItemValueSetter.setValue(pMsg.taskList.no(idx).xxMsgId, msgId);
        }
        setErrMsg(pMsg, msgId);
    }

    private void cancellByModeUpdate(NSZC043001PMsg pMsg) {
        if (!existOtherTask(pMsg)) {
            // Call FSR Update API / 03(cancel)
            cancelFSR(pMsg);
        } else {
            // Call Add Task API / 02(cancel)
            final NSZC045001 callAddTaskApi = new NSZC045001();
            NSZC045001PMsg callAddTaskApiParam = createAddTaskApiCancelParam(pMsg);
            callAddTaskApi.execute(callAddTaskApiParam, this.onBatType);

            if (callAddTaskApiParam.xxMsgIdList.getValidCount() != 0) {
                for (int i = 0; i < callAddTaskApiParam.xxMsgIdList.getValidCount(); i++) {
                    msgMap.addXxMsgId(callAddTaskApiParam.xxMsgIdList.no(i).xxMsgId.getValue());
                }
            }
        }
    }

    private boolean existOtherTask(NSZC043001PMsg pMsg) {
        SVC_TASKTMsg inMsg = new SVC_TASKTMsg();
        inMsg.setSQLID("005");
        inMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("fsrNum01", pMsg.fsrNum.getValue());
        Integer resCnt = (Integer) S21ApiTBLAccessor.count(inMsg);
        if (resCnt > 1) {
            return true;
        }
        return false;
    }

    private NSZC045001PMsg createAddTaskApiCancelParam(NSZC043001PMsg pMsg) {
        NSZC045001PMsg addTaskApiPMsg = new NSZC045001PMsg();

        setValue(addTaskApiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(addTaskApiPMsg.xxModeCd, NSZC045001.PROCESS_MODE_CANCEL_TASK);
        setValue(addTaskApiPMsg.fsrNum, pMsg.fsrNum.getValue());
        setValue(addTaskApiPMsg.userId, pMsg.userId.getValue());
        // add start 2016/07/01 CSA Defect#9677
        setValue(addTaskApiPMsg.xxRqstSendFlg, ZYPConstant.FLG_OFF_N);
        // add end 2016/07/01 CSA Defect#9677

        int i = 0;
        for (; i < pMsg.taskList.getValidCount(); i++) {
            NSZC045001_xxSvcTaskListPMsg taskListPMsg = addTaskApiPMsg.xxSvcTaskList.no(i);
            setValue(taskListPMsg.svcTaskNum, pMsg.taskList.no(i).svcTaskNum);
            setValue(taskListPMsg.schdDisptEmlFlg, pMsg.taskList.no(i).schdDisptEmlFlg);
        }
        addTaskApiPMsg.xxSvcTaskList.setValidCount(i);

        int j = 0;
        for (; j < pMsg.svcMemoList.getValidCount(); j++) {
            NSZC045001_xxSvcMemoListPMsg memoListPMsg = addTaskApiPMsg.xxSvcMemoList.no(j);
            setValue(memoListPMsg.svcMemoTpCd, pMsg.svcMemoList.no(j).svcMemoTpCd);
            setValue(memoListPMsg.svcCmntTxt, pMsg.svcMemoList.no(j).svcCmntTxt);
            // mod start 2016/11/25 CSA QC#16025
            setValue(memoListPMsg.svcMemoRsnCd, pMsg.svcMemoList.no(j).svcMemoRsnCd);
            // mod end 2016/11/25 CSA QC#16025
        }
        addTaskApiPMsg.xxSvcMemoList.setValidCount(j);
        return addTaskApiPMsg;
    }

    private void callCompletinonApiByModeUpdate(NSZC043001PMsg pMsg) {
        if (!checkCompletion(pMsg)) {
            return;
        }

        NSZC005001 callCompletionApi = new NSZC005001();
        NSZC005001PMsg callCompletionParam = createCompletionParam(pMsg);
        callCompletionApi.execute(callCompletionParam, this.onBatType);

        if (callCompletionParam.xxMsgIdList.getValidCount() != 0) {
            for (int i = 0; i < callCompletionParam.xxMsgIdList.getValidCount(); i++) {
                msgMap.addXxMsgId(callCompletionParam.xxMsgIdList.no(i).xxMsgId.getValue());
            }
        }
    }

    private boolean checkCompletion(NSZC043001PMsg pMsg) {
        if (SVC_TASK_STS.COMPLETED.equals(pMsg.svcTaskStsCd.getValue())) {
            for (int i = 0; i < pMsg.taskList.getValidCount(); i++) {
                if (!isOkDebrief(pMsg.glblCmpyCd.getValue(), pMsg.taskList.no(i).svcTaskNum.getValue())) {
                    msgMap.addXxMsgId(NSZM0863E);
                    return false;
                }
            }
        } else if (SVC_TASK_STS.CLOSED.equals(pMsg.svcTaskStsCd.getValue())) {
            for (int i = 0; i < pMsg.taskList.getValidCount(); i++) {
                if (!isOkChargable(pMsg.glblCmpyCd.getValue(), pMsg.taskList.no(i).svcTaskNum.getValue())) {
                    msgMap.addXxMsgId(NSZM0864E);
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isOkDebrief(String glblCmpyCd, String svcTaskNum) {
        FSR_VISIT_TM_EVENTTMsg inMsg = new FSR_VISIT_TM_EVENTTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcTaskNum01", svcTaskNum);
        Integer resCnt = (Integer) S21ApiTBLAccessor.count(inMsg);
        if (resCnt > 0) {
            return true;
        }
        return false;
    }

    private boolean isOkChargable(String glblCmpyCd, String svcTaskNum) {
        FSR_CHRG_DTLTMsg inMsg = new FSR_CHRG_DTLTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcTaskNum01", svcTaskNum);
        Integer resCnt = (Integer) S21ApiTBLAccessor.count(inMsg);
        if (resCnt > 0) {
            return true;
        }
        return false;
    }

    private NSZC005001PMsg createCompletionParam(NSZC043001PMsg pMsg) {
        NSZC005001PMsg completionApiPMsg = new NSZC005001PMsg();

        // Visit Header
        setValue(completionApiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        if (SVC_TASK_STS.COMPLETED.equals(pMsg.svcTaskStsCd.getValue())) {
            setValue(completionApiPMsg.xxModeCd, NSZC005001Constant.MODE_DEBRIEF);
        } else if (SVC_TASK_STS.CLOSED.equals(pMsg.svcTaskStsCd.getValue())) {
            setValue(completionApiPMsg.xxModeCd, NSZC005001Constant.MODE_CHARGABLE);
        }
        setValue(completionApiPMsg.slsDt, pMsg.slsDt);
        setValue(completionApiPMsg.userId, pMsg.userId);
        setValue(completionApiPMsg.frceUpdFlg, ZYPConstant.FLG_ON_Y);
        setValue(completionApiPMsg.fsrNum, pMsg.fsrNum);
        setValue(completionApiPMsg.svcMachMstrPk, pMsg.svcMachMstrPk);
        if (hasValue(pMsg.svcTaskCpltDt) && hasValue(pMsg.svcTaskCpltTm)) {
            setValue(completionApiPMsg.fsrVisitCpltDt, pMsg.svcTaskCpltDt);
            setValue(completionApiPMsg.fsrVisitCpltTm, pMsg.svcTaskCpltTm);
        } else {
            String sysDate = ZYPDateUtil.getCurrentSystemTime(ZYPDateUtil.TYPE_YYYYMMDD);
            String sysTime = S21StringUtil.subStringByLength(ZYPDateUtil.getCurrentSystemTime(FORMAT_SYS_TS), TIME_START_POS, TIME_END_POS);
            setValue(completionApiPMsg.fsrVisitCpltDt, sysDate);
            setValue(completionApiPMsg.fsrVisitCpltTm, sysTime);
        }
        setValue(completionApiPMsg.svcTrvlTmNum, BigDecimal.ZERO);

        // Visit for task
        if (SVC_TASK_STS.COMPLETED.equals(pMsg.svcTaskStsCd.getValue())) {
            int i = 0;
            for (; i < pMsg.taskList.getValidCount(); i++) {
                NSZC005001_xxVisitTaskListPMsg visitTaskList = completionApiPMsg.xxVisitTaskList.no(i);
                setValue(visitTaskList.svcTaskNum, pMsg.taskList.no(i).svcTaskNum);
            }
            completionApiPMsg.xxVisitTaskList.setValidCount(i);
        }

        // Visit for task
        if (SVC_TASK_STS.CLOSED.equals(pMsg.svcTaskStsCd.getValue())) {
            int i = 0;
            for (; i < pMsg.taskList.getValidCount(); i++) {
                NSZC005001_xxChargesListPMsg chargesTaskList = completionApiPMsg.xxChargesList.no(i);
                setValue(chargesTaskList.fsrNum, pMsg.fsrNum);
                setValue(chargesTaskList.svcTaskNum, pMsg.taskList.no(i).svcTaskNum);
            }
            completionApiPMsg.xxChargesList.setValidCount(i);
        }

        return completionApiPMsg;
    }

    // add start 2016/02/25 CSA Defect#3991
    private boolean callCreditCardValidationApi(NSZC043001PMsg pMsg) {

        // START 01/19/2017 [QC#15092, MOD]
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String svcTaskNum = pMsg.taskList.no(0).svcTaskNum.getValue();
        SVC_TASKTMsg svcTaskTMsg = getSvcTask(glblCmpyCd, svcTaskNum);
        if (svcTaskTMsg == null) {
            setErrMsg(pMsg, NSZM0486E);
            return false;
        }

        // add start 2017/06/22 CSA Defect#19351
        if (isNoCharge(svcTaskTMsg)) {
            // START 2018/11/29 K.Kitachi [QC#29459, MOD]
//            return true;
            if (!isPhoneFix(svcTaskTMsg)) {
                return true;
            }
            // END 2018/11/29 K.Kitachi [QC#29459, MOD]
        }
        // add end   2017/06/22 CSA Defect#19351

        // check target
        if (!checkTargetForCreditCard(pMsg, svcTaskTMsg)) {
            return true;
        }

        // check Credit Card Validation API is callable
        if (!checkCreditCardValidationApiParam(pMsg)) {
            return false;
        }
        // END   01/19/2017 [QC#15092, MOD]

        // create or update DS_CR_CARD
        if (!execDsCrCard(pMsg)) {
            return false;
        }

        // create or update CR_CARD_TRX
        if (!execCrCardtrx(pMsg)) {
            return false;
        }
        return true;
    }
    // add end 2016/02/25 CSA Defect#3991

    // add start 2016/02/25 CSA Defect#3991
    private boolean checkTargetForCreditCard(NSZC043001PMsg pMsg, SVC_TASKTMsg svcTaskTMsg) {
        FSRTMsg fsrTMsg = new FSRTMsg();
        String pmtTermCashDiscCd = pMsg.pmtTermCashDiscCd.getValue();
        if (!hasValue(pMsg.pmtTermCashDiscCd)) {
            FSRTMsg srchFsrTMsg = new FSRTMsg();
            srchFsrTMsg.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
            srchFsrTMsg.fsrNum.setValue(pMsg.fsrNum.getValue());
            fsrTMsg = (FSRTMsg) S21ApiTBLAccessor.findByKey(srchFsrTMsg);
            pmtTermCashDiscCd = fsrTMsg.pmtTermCashDiscCd.getValue();
        }

        PMT_TERM_CASH_DISCTMsg ptcdTMsg = new PMT_TERM_CASH_DISCTMsg();
        ptcdTMsg.glblCmpyCd.setValue(pMsg.glblCmpyCd.getValue());
        ptcdTMsg.pmtTermCashDiscCd.setValue(pmtTermCashDiscCd);
        ptcdTMsg.ezCancelFlag.setValue(ZYPConstant.FLG_OFF_0);
        ptcdTMsg = (PMT_TERM_CASH_DISCTMsg) ZYPCodeDataUtil.findByKey(ptcdTMsg);
        if (ptcdTMsg == null) {
            return false;
        }
        // START 01/19/2017 [QC#15092, MOD]
        // return ZYPConstant.FLG_ON_Y.equals(ptcdTMsg.pmtCcFlg.getValue());
        return ZYPConstant.FLG_ON_Y.equals(ptcdTMsg.pmtCcFlg.getValue()) && isCreditCheckTarget(pMsg, svcTaskTMsg);
        // END   01/19/2017 [QC#15092, MOD]
    }
    // add end 2016/02/25 CSA Defect#3991

    // START 01/19/2017 [QC#15092, ADD]
    private boolean isCreditCheckTarget(NSZC043001PMsg pMsg, SVC_TASKTMsg svcTaskTMsg) {
        if (!SVC_TASK_STS.OPEN.equals(svcTaskTMsg.svcTaskStsCd.getValue()) && !hasValue(pMsg.crCardCustRefNum)) {
            return false;
        }

        return true;
    }

    private boolean checkCreditCardValidationApiParam(NSZC043001PMsg pMsg) {
        if (!hasValue(pMsg.crCardCustRefNum)) {
            setErrMsg(pMsg, NSZM1067E);
            return false;
        }
        return true;
    }
    // END   01/19/2017 [QC#15092, ADD]

    // add start 2016/02/25 CSA Defect#3991
    private boolean execDsCrCard(NSZC043001PMsg pMsg) {
        if (!existsDsCrCard(pMsg)) {
            // get Account info
            SVC_MACH_MSTRTMsg svcMachMstrTMsg = null;
            if (ZYPCommonFunc.hasValue(pMsg.svcMachMstrPk)) {
                svcMachMstrTMsg = getSvcMachMstr(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
            }
            String curLocAcctNum = null;
            if (svcMachMstrTMsg != null) {
                curLocAcctNum = svcMachMstrTMsg.curLocAcctNum.getValue();
            }

            // Call credit Card Validation API,
            NWZC203001PMsg inPMsg = new NWZC203001PMsg();
            ZYPEZDItemValueSetter.setValue(inPMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inPMsg.xxModeCd, NWZC203001Constant.PROC_MODE_WRITE_CC_MSTR);
            ZYPEZDItemValueSetter.setValue(inPMsg.slsDt, pMsg.slsDt);
            ZYPEZDItemValueSetter.setValue(inPMsg.crCardCustRefNum, pMsg.crCardCustRefNum);
            ZYPEZDItemValueSetter.setValue(inPMsg.sellToCustCd, curLocAcctNum);
            // START 05/08/2018 [QC#17679, ADD]
//            ZYPEZDItemValueSetter.setValue(inPMsg.xxPmtcAcctNum, curLocAcctNum);
            // Mod Start 2018/06/20 QC#26830
            String crCardLastDigitNum = pMsg.crCardLastDigitNum.getValue();
            if (!hasValue(crCardLastDigitNum)) {
                crCardLastDigitNum = ZYPCodeDataUtil.getVarCharConstValue("DUMMY_PMTC_ACCT_NUM", pMsg.glblCmpyCd.getValue());
            }
            ZYPEZDItemValueSetter.setValue(inPMsg.xxPmtcAcctNum, crCardLastDigitNum);
            // Mod End 2018/06/20 QC#26830
            // END 05/08/2018 [QC#17679, ADD]
            ZYPEZDItemValueSetter.setValue(inPMsg.xxPmtcExprDtTxt, pMsg.crCardExprYrMth.getValue());
            ZYPEZDItemValueSetter.setValue(inPMsg.xxPmtcAvsNm, pMsg.crCardHldNm);
            ZYPEZDItemValueSetter.setValue(inPMsg.crCardTpCd, pMsg.crCardTpCd);

            NWZC203001 nwzc020301API = new NWZC203001();
            nwzc020301API.execute(inPMsg, this.onBatType);
            if (hasError(pMsg, inPMsg)) {
                return false;
            }
        }
        return true;
    }
    // add start 2016/02/25 CSA Defect#3991

    // add start 2016/02/25 CSA Defect#3991
    private boolean existsDsCrCard(NSZC043001PMsg pMsg) {
        DS_CR_CARDTMsg inMsg = new DS_CR_CARDTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("crCardCustRefNum01", pMsg.crCardCustRefNum.getValue());
        DS_CR_CARDTMsgArray outArray = (DS_CR_CARDTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
        if (outArray.getValidCount() > 0) {
            return true;
        }
        return false;
    }
    // add end 2016/02/25 CSA Defect#3991

    // add start 2016/02/25 CSA Defect#3991
    private boolean execCrCardtrx(NSZC043001PMsg pMsg) {

        // get CrCardtrx
        CR_CARD_TRXTMsgArray cctTMsgArryay = getCrCardtrx(pMsg);

        // Cancel CrCardtrx
        for (int i = 0; i < cctTMsgArryay.getValidCount(); i++) {
            // Call credit Card Validation API,
            NWZC203001PMsg inPMsg = new NWZC203001PMsg();
            CR_CARD_TRXTMsg cctTMsg = cctTMsgArryay.no(i);

            // copy
            EZDMsg.copy(cctTMsg, null, inPMsg, null);
            // set parameter
            ZYPEZDItemValueSetter.setValue(inPMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inPMsg.xxModeCd, NWZC203001Constant.PROC_MODE_WRITE_CC_TRX);
            ZYPEZDItemValueSetter.setValue(inPMsg.slsDt, pMsg.slsDt);
            ZYPEZDItemValueSetter.setValue(inPMsg.sellToCustCd, cctTMsg.billToCustAcctCd);
            ZYPEZDItemValueSetter.setValue(inPMsg.crCardCancDt, ZYPDateUtil.getCurrentSystemTime(ZYPDateUtil.TYPE_YYYYMMDD));
            ZYPEZDItemValueSetter.setValue(inPMsg.crCardTrxTpCd, CR_CARD_TRX_TP.SERVICE_REQUEST);
            ZYPEZDItemValueSetter.setValue(inPMsg.crCardAuthStsCd, CR_CARD_AUTH_STS.VOID_COMPLETED);
            ZYPEZDItemValueSetter.setValue(inPMsg.firstTrxInfoTxt, pMsg.fsrNum);
            ZYPEZDItemValueSetter.setValue(inPMsg.xxPmtcProcStsCd, cctTMsg.crCardTrxProcStsCd);
            ZYPEZDItemValueSetter.setValue(inPMsg.xxPmtcApvlStsNum, cctTMsg.crCardTrxApvlStsCd);
            ZYPEZDItemValueSetter.setValue(inPMsg.xxPmtcTrxRefIdxCd, cctTMsg.crCardRefIdxNum);

            NWZC203001 nwzc020301API = new NWZC203001();
            nwzc020301API.execute(inPMsg, this.onBatType);
            if (hasError(pMsg, inPMsg)) {
                return false;
            }
        }

        // get Account info
        SVC_MACH_MSTRTMsg svcMachMstrTMsg = new SVC_MACH_MSTRTMsg();
        if (ZYPCommonFunc.hasValue(pMsg.svcMachMstrPk)) {
            svcMachMstrTMsg = getSvcMachMstr(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        }
        String curLocAcctNum = null;
        if (svcMachMstrTMsg != null) {
            curLocAcctNum = svcMachMstrTMsg.curLocAcctNum.getValue();
        }
        // Insert CrCardtrx
        NWZC203001PMsg inPMsg = new NWZC203001PMsg();
        ZYPEZDItemValueSetter.setValue(inPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inPMsg.xxModeCd, NWZC203001Constant.PROC_MODE_WRITE_CC_TRX);
        ZYPEZDItemValueSetter.setValue(inPMsg.slsDt, pMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(inPMsg.crCardCustRefNum, pMsg.crCardCustRefNum);
        ZYPEZDItemValueSetter.setValue(inPMsg.crCardAuthRefNum, pMsg.crCardAuthRefNum);
        ZYPEZDItemValueSetter.setValue(inPMsg.sellToCustCd, curLocAcctNum);
        ZYPEZDItemValueSetter.setValue(inPMsg.crCardAuthAmt, pMsg.crCardAuthAmt);
        ZYPEZDItemValueSetter.setValue(inPMsg.crCardTrxTpCd, CR_CARD_TRX_TP.SERVICE_REQUEST);
        ZYPEZDItemValueSetter.setValue(inPMsg.crCardAuthStsCd, CR_CARD_AUTH_STS.SAVED);
        ZYPEZDItemValueSetter.setValue(inPMsg.firstTrxInfoTxt, pMsg.fsrNum);

        NWZC203001 nwzc020301API = new NWZC203001();
        nwzc020301API.execute(inPMsg, this.onBatType);
        if (hasError(pMsg, inPMsg)) {
            return false;
        }
        return true;
    }
    // add end 2016/02/25 CSA Defect#3991

    // add start 2016/02/25 CSA Defect#3991, 2016/07/21 CSA Defect#12154
    private CR_CARD_TRXTMsgArray getCrCardtrx(NSZC043001PMsg pMsg) {
        CR_CARD_TRXTMsg inMsg = new CR_CARD_TRXTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("firstTrxInfoTxt01", pMsg.fsrNum.getValue());
        inMsg.setConditionValue("crCardAuthStsCd01", CR_CARD_AUTH_STS.SAVED);
        inMsg.setConditionValue("crCardTrxTpCd01", CR_CARD_TRX_TP.SERVICE_REQUEST);
        return (CR_CARD_TRXTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
    }
    // add end 2016/02/25 CSA Defect#3991, 2016/07/21 CSA Defect#12154

    // add start 2016/06/16 CSA Defect#9677
    private void callSendTaskInfoApi(NSZC043001PMsg pMsg) {
         if (ZYPConstant.FLG_OFF_N.equals(pMsg.xxRqstSendFlg.getValue())) {
            return;
         }

        List<NSZC107001PMsg> inPMsgList = new ArrayList<NSZC107001PMsg>();
        List<String> taskList = getSendTaskList(pMsg);
        // Add Start 2018/09/06 QC#22665
        if (taskList.size() == 0) {
            return;
        }
        // Add End 2018/09/06 QC#22665
        for (String svcTaskNum : taskList) {
            NSZC107001PMsg inPMsg = new NSZC107001PMsg();
            ZYPEZDItemValueSetter.setValue(inPMsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inPMsg.slsDt, pMsg.slsDt);
            ZYPEZDItemValueSetter.setValue(inPMsg.svcTaskNum, svcTaskNum);
            inPMsgList.add(inPMsg);
        }

        NSZC107001 nszc107001API = new NSZC107001();
        nszc107001API.execute(inPMsgList, this.onBatType);
        for (NSZC107001PMsg inPMsg : inPMsgList) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(inPMsg);
            for (S21ApiMessage msg : msgList) {
                String xxMsgId = msg.getXxMsgid();
                String[] xxMsgPrm = msg.getXxMsgPrmArray();
                if (ZYPCommonFunc.hasValue(xxMsgId) && xxMsgId.endsWith("E")) {
                    msgMap.addXxMsgIdWithPrm(xxMsgId, xxMsgPrm);
                }
            }
        }
    }
    // add end 2016/06/16 CSA Defect#9677

    // add start 2016/06/16 CSA Defect#9677
    private List<String> getSendTaskList(NSZC043001PMsg pMsg) {
        List<String> taskList = new ArrayList<String>();
        for (int i = 0; i < pMsg.taskList.getValidCount(); i++) {
            // Mod Start 2018/09/06 QC#22665
            if (hasValue(pMsg.taskList.no(i).svcTaskNum.getValue())) {
                taskList.add(pMsg.taskList.no(i).svcTaskNum.getValue());
            }
            // Mod End 2018/09/06 QC#22665
        }

        if (MODE_UPDATE_FSR.equals(pMsg.xxModeCd.getValue()) && SVC_TASK_STS.COMPLETED.equals(pMsg.svcTaskStsCd.getValue())) {
            String follUpTaskNum = getFollUpTask(pMsg);
            if (hasValue(follUpTaskNum)) {
                taskList.add(follUpTaskNum);
            }
        }
        return taskList;
    }
    // add end 2016/06/16 CSA Defect#9677

    // add start 2016/06/16 CSA Defect#9677
    private String getFollUpTask(NSZC043001PMsg pMsg) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("svcTaskNum", pMsg.taskList.no(0).svcTaskNum.getValue());
        return (String) ssmBatClnt.queryObject("getFollUpTask", paramMap);
    }
    // add end 2016/06/16 CSA Defect#9677

    // add start 2016/10/06 CSA Defect#14950
    private String getBillToAcct(NSZC043001PMsg pMsg, String billToCustCd) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        paramMap.put("billToCustCd", billToCustCd);
        return (String) ssmBatClnt.queryObject("getBillToAcct", paramMap);
    }
    // add end 2016/10/06 CSA Defect#14950

    // add start 2016/10/05 CSA Defect#14145
    private List<Map<String, Object>> getMdlNmForConfigMtrDtl(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("svcMachMstrPk", svcMachMstrPk);
        return (List<Map<String, Object>>) ssmBatClnt.queryObjectList("getMdlNmForConfigMtrDtl", paramMap);
    }
    // add end 2016/10/05 CSA Defect#14145

    // Mod Start 2017/10/02 QC#21273
    // START 2016/12/15 T.Tomita [QC#16490, ADD]
    // START 2017/07/21 K.Kojima [QC#19987,MOD]
    // private String getLateStartTs(String glblCmpyCd, BigDecimal svcMachMstrPk, String erlStartTs, String lateStartTs) {
    // START 2022/04/11 K.Kitachi [QC#59899, MOD]
//    private String getLateStartTs(String glblCmpyCd, BigDecimal svcMachMstrPk, String erlStartTs, String lateStartTs, String svcTaskRcvDt, String machDownFlg, String mdlNm, String dsSvcCallTpCd) {
    private String getLateStartTs(String glblCmpyCd, BigDecimal svcMachMstrPk, String erlStartTs, String lateStartTs, String svcTaskRcvDt, String machDownFlg, String mdlNm, String dsSvcCallTpCd, String shipToUpdCustCd) {
    // END 2022/04/11 K.Kitachi [QC#59899, MOD]
    // END 2017/07/21 K.Kojima [QC#19987,MOD]
        if (hasValue(lateStartTs)) {
            return lateStartTs;
        }
        if (!hasValue(erlStartTs)) {
            return null;
        }
        // START 2017/07/21 K.Kojima [QC#19987,ADD]
        BigDecimal rspTmMnAot = BigDecimal.ZERO;
        if (ZYPCommonFunc.hasValue(svcTaskRcvDt)) {
            rspTmMnAot = NSXC001001GetRspTime.getRspTmMnAot(glblCmpyCd, svcMachMstrPk, svcTaskRcvDt, machDownFlg, mdlNm);
        }
        // END 2017/07/21 K.Kojima [QC#19987,ADD]
        // START 2017/07/21 K.Kojima [QC#19987,MOD]
        // return NSXC001001GetRspTime.getEndDateByRspTimeWithTimeDiff(glblCmpyCd, BigDecimal.ZERO, svcMachMstrPk, erlStartTs.substring(0, 8), erlStartTs.substring(8, 14), false);
        // START 2022/04/11 K.Kitachi [QC#59899, MOD]
//        return NSXC001001GetRspTime.getEndDateByRspTimeWithTimeDiff(glblCmpyCd, rspTmMnAot, svcMachMstrPk, erlStartTs.substring(0, 8), erlStartTs.substring(8, 14), false, dsSvcCallTpCd);
        return NSXC001001GetRspTime.getEndDateByRspTimeWithTimeDiff(glblCmpyCd, rspTmMnAot, svcMachMstrPk, erlStartTs.substring(0, 8), erlStartTs.substring(8, 14), false, dsSvcCallTpCd, shipToUpdCustCd);
        // END 2022/04/11 K.Kitachi [QC#59899, MOD]
        // END 2017/07/21 K.Kojima [QC#19987,MOD]
    }
    // END 2016/12/15 T.Tomita [QC#16490, ADD]
    // Mod End 2017/10/02 QC#21273

    // START 2017/01/18 K.Kitachi [QC#15818, ADD]
    private boolean isAccessory(NSZC043001PMsg pMsg) {
        if (!hasValue(pMsg.svcMachMstrPk)) {
            return false;
        }
        SVC_MACH_MSTRTMsg svcMachMstrTMsg = getSvcMachMstr(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
        if (svcMachMstrTMsg == null) {
            return false;
        }
        if (SVC_MACH_TP.ACCESSORY.equals(svcMachMstrTMsg.svcMachTpCd.getValue())) {
            return true;
        }
        return false;
    }
    // END 2017/01/18 K.Kitachi [QC#15818, ADD]

    // START 2017/05/19 K.Kitachi [QC#18213, ADD]
    private void setMsgTxt(NSZC043001PMsg pMsg) {
        String msgId;
        String msgTxt;
        for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
            msgId = pMsg.xxMsgIdList.no(i).xxMsgId.getValue();
            msgTxt = getMsgTxt(msgId);
            if (!hasValue(msgTxt)) {
                continue;
            }
            setValue(pMsg.xxMsgIdList.no(i).xxMsgTxt, msgTxt);
        }
        for (int i = 0; i < pMsg.taskList.getValidCount(); i++) {
            msgId = pMsg.taskList.no(i).xxMsgId.getValue();
            msgTxt = getMsgTxt(msgId);
            if (!hasValue(msgTxt)) {
                continue;
            }
            setValue(pMsg.taskList.no(i).xxMsgTxt, msgTxt);
        }
    }

    private String getMsgTxt(String msgId) {
        if (!hasValue(msgId)) {
            return null;
        }
        String msgTxt = S21MessageFunc.clspGetMessage(msgId);
        if (!hasValue(msgTxt)) {
            return null;
        }
        if (msgTxt.length() > SUB_STR_POS_10) {
            msgTxt = msgTxt.substring(SUB_STR_POS_10);
        }
        return msgTxt;
    }
    // END 2017/05/19 K.Kitachi [QC#18213, ADD]
    // add start 2017/06/22 CSA Defect#19351 TODO
    private boolean isNoCharge(SVC_TASKTMsg svcTaskTMsg) {
        if (!hasValue(svcTaskTMsg.svcBillTpCd)) {
            return true;
        }

        SVC_BILL_TPTMsg svcBillTpTMsg = (SVC_BILL_TPTMsg)ZYPCodeDataUtil.findByCode(SVC_BILL_TP.class, svcTaskTMsg.glblCmpyCd.getValue(), svcTaskTMsg.svcBillTpCd.getValue());
        if (svcBillTpTMsg == null || ZYPConstant.FLG_OFF_N.equals(svcBillTpTMsg.bllblFlg.getValue())) {
            return true;
        }
        return false;
    }
    // add end   2017/06/22 CSA Defect#19351
    // Add Start 2018/04/10 QC#21868
    private boolean convSvcCallTpForSrcTp(NSZC043001PMsg pMsg, DS_SVC_CALL_TPTMsg dsSvcCallTpTMsg) {
        if (!hasValue(pMsg.svcCallSrcTpCd)) {
            return true;
        }

        // Check SVC_CALL_SRC_TP
        SVC_CALL_SRC_TPTMsg svcCallSrcTpTMsg = getSvcCallSrcTp(pMsg.glblCmpyCd.getValue(), pMsg.svcCallSrcTpCd.getValue());
        if (svcCallSrcTpTMsg == null || !ZYPConstant.FLG_ON_Y.equals(svcCallSrcTpTMsg.aftHrsAllwFlg.getValue())) {
            return true;
        }

        // Call NSZC0610
        NSZC061001PMsg svcPrcApiPMsg = callSvcPrcApi(pMsg);
        if (hasError(pMsg, svcPrcApiPMsg)) {
            return false;
        }

        // START 2020/04/06 K.Kitachi [QC#56229, ADD]
        if (SVC_CALL_SRC_TP.CROSS_SERVICE.equals((svcCallSrcTpTMsg.svcCallSrcTpCd.getValue()))) {
            List<DS_CONTR_DTLTMsg> contrDtlList = NSXC001001GetContr.getEttlAvalContrDtlByMachMstrPk(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue(), pMsg.slsDt.getValue());
            if (contrDtlList.size() == 0) {
                return true;
            }
            if (ZYPConstant.FLG_ON_Y.equals(svcPrcApiPMsg.ahsCdEnblFlg.getValue())) {
                String ahsDsSvcCallTpCd = dsSvcCallTpTMsg.ahsDsSvcCallTpCd.getValue();
                if (hasValue(ahsDsSvcCallTpCd)) {
                    setValue(pMsg.dsSvcCallTpCd, ahsDsSvcCallTpCd);
                }
            } else {
                String rhsDsSvcCallTpCd = getRhsDsSvcCallTpCd(pMsg, dsSvcCallTpTMsg);
                if (hasValue(rhsDsSvcCallTpCd)) {
                    setValue(pMsg.dsSvcCallTpCd, rhsDsSvcCallTpCd);
                }
                if (ZYPConstant.FLG_OFF_N.equals(svcPrcApiPMsg.ahsCdEnblFlg.getValue())) {
                    if (pMsg.taskList.getValidCount() > 0) {
                        String futSvcTs = getErlStartTs(pMsg, pMsg.dsSvcCallTpCd.getValue());
                        if (hasValue(futSvcTs) && futSvcTs.length() >= TS_LEN) {
                            setValue(pMsg.taskList.no(0).futSvcDt, futSvcTs.substring(0, SUB_STR_POS_8));
                            setValue(pMsg.taskList.no(0).futSvcTm, futSvcTs.substring(SUB_STR_POS_8, TS_LEN));
                        }
                    }
                }
            }
            return true;
        }
        // END 2020/04/06 K.Kitachi [QC#56229, ADD]

        // START 2019/02/22 [QC#30486, MOD]
        // Set Service Bill Type
        if (!SVC_CALL_SRC_TP.CROSS_SERVICE.equals((svcCallSrcTpTMsg.svcCallSrcTpCd.getValue()))) {
            setValue(pMsg.svcBillTpCd, svcPrcApiPMsg.svcBillTpCd);
        }
        // END 2019/02/22 [QC#30486, MOD]

        // Check AHS
        if (!ZYPConstant.FLG_ON_Y.equals(svcPrcApiPMsg.ahsCdEnblFlg.getValue())) {
            return true;
        }

        if (!hasValue(dsSvcCallTpTMsg.ahsDsSvcCallTpCd)) {
            return true;
        }

        // Set AHS Service Call Type Code
        setValue(pMsg.dsSvcCallTpCd, dsSvcCallTpTMsg.ahsDsSvcCallTpCd);
        return true;
    }

    private SVC_CALL_SRC_TPTMsg getSvcCallSrcTp(String glblCmpyCd, String svcCallSrcTpCd) {
        SVC_CALL_SRC_TPTMsg inMsg = new SVC_CALL_SRC_TPTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.svcCallSrcTpCd, svcCallSrcTpCd);
        return (SVC_CALL_SRC_TPTMsg) S21ApiTBLAccessor.findByKey(inMsg);
    }

    private NSZC061001PMsg callSvcPrcApi(NSZC043001PMsg pMsg) {
        NSZC061001PMsg svcPrcApiPMsg = new NSZC061001PMsg();
        setValue(svcPrcApiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(svcPrcApiPMsg.xxModeCd, NSZC061001Constant.PROCESS_MODE_CALL_CREATION);
        String startDt = pMsg.svcTaskRcvDt.getValue();
        if (!hasValue(startDt)) {
            startDt = pMsg.slsDt.getValue();
        }
        String startTm = pMsg.svcTaskRcvTm.getValue();
        if (!hasValue(startTm)) {
            startTm = this.curTime;
        }

        setValue(svcPrcApiPMsg.startDt, startDt);
        setValue(svcPrcApiPMsg.startTm, startTm);
        setValue(svcPrcApiPMsg.svcMachMstrPk, pMsg.svcMachMstrPk);
        setValue(svcPrcApiPMsg.dsSvcCallTpCd, pMsg.dsSvcCallTpCd);
        // add start 2020/06/15 QC#56229
        setValue(svcPrcApiPMsg.svcCallSrcTpCd, pMsg.svcCallSrcTpCd);
        // add end 2020/06/15 QC#56229
        NSZC061001 api = new NSZC061001();
        api.execute(svcPrcApiPMsg, this.onBatType);
        return svcPrcApiPMsg;
    }
    // Add End 2018/04/10 QC#21868

    // START 2023/05/31 K.Watanabe [QC#61309, ADD]
    private NSZC061001PMsg callSvcPrcApiForGetSendLaborAndTravelRatesMessage(NSZC043001PMsg pMsg, SVC_TASKTMsg svcTaskTMsg, SVC_MACH_MSTRTMsg svcMachMstrTMsg) {
        NSZC061001PMsg svcPrcApiPMsg = new NSZC061001PMsg();
        setValue(svcPrcApiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(svcPrcApiPMsg.xxModeCd, NSZC061001Constant.PROCESS_MODE_CALL_CREATION);
        String startDt = svcTaskTMsg.svcTaskRcvDt.getValue();
        String startTm = svcTaskTMsg.svcTaskRcvTm.getValue();
        if (hasValue(pMsg.svcBillTpCd)) {
            setValue(svcPrcApiPMsg.svcBillTpCd, pMsg.svcBillTpCd.getValue());
        }
        setValue(svcPrcApiPMsg.startDt, startDt);
        setValue(svcPrcApiPMsg.startTm, startTm);
        setValue(svcPrcApiPMsg.svcMachMstrPk, svcTaskTMsg.svcMachMstrPk);
        setValue(svcPrcApiPMsg.dsSvcCallTpCd, svcTaskTMsg.dsSvcCallTpCd);
        setValue(svcPrcApiPMsg.svcCallSrcTpCd, pMsg.svcCallSrcTpCd);
        NSZC061001 api = new NSZC061001();
        api.execute(svcPrcApiPMsg, this.onBatType);
        return svcPrcApiPMsg;
    }
    // END 2023/05/31 K.Watanabe [QC#61309, ADD]

    // START 2018/07/12 M.Naito [QC#13309, ADD]
    private String getTempEttlNum(NSZC043001PMsg pMsg) {

        List<DS_CONTR_DTLTMsg> contrDtlList = NSXC001001GetContr.getEttlAvalContrDtlByMachMstrPk(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue(), pMsg.slsDt.getValue());
        DS_CONTR_DTLTMsg contrDtl = null;
        if (contrDtlList.size() > 0) {
            contrDtl = contrDtlList.get(0);
        }
        int dayOfTheWeek = getDayOfTheWeek(pMsg.glblCmpyCd.getValue(), pMsg.slsDt.getValue());

        // check AHS
        if (contrDtl == null || !isAhsForTermCondAhsWrkPgm(pMsg, contrDtl, dayOfTheWeek)) {
            // get Temporary Entitlement Number
            String tempEttlNum = getTempEttlInfo(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue(), pMsg.slsDt.getValue(), getStartTm(pMsg), dayOfTheWeek);
            if (tempEttlNum != null) {
                return tempEttlNum;
            }
        }
        return null;
    }

    private boolean isAhsForTermCondAhsWrkPgm(NSZC043001PMsg pMsg, DS_CONTR_DTLTMsg contrDtl, int dayOfTheWeek) {

        String svcTermCondAttrbNm = ZYPCodeDataUtil.getVarCharConstValue(TERM_COND_AFTER_HOURS_WRK_PGM, pMsg.glblCmpyCd.getValue());
        Map<String, Object> rtnMap = getSvcTermAttrbMapValCd(pMsg.glblCmpyCd.getValue(), svcTermCondAttrbNm, contrDtl.dsContrPk.getValue(), contrDtl.dsContrDtlPk.getValue());
        if (rtnMap == null) {
            return false;
        }

        String ahsCd = (String) rtnMap.get("SVC_TERM_ATTRB_MAP_VAL_CD");
        if (!hasValue(ahsCd)) {
            return false;
        }
        AHSTMsgArray ahsTMsgArray = getAhs(pMsg.glblCmpyCd.getValue(), ahsCd, getStartTm(pMsg), dayOfTheWeek);
        if (ahsTMsgArray.getValidCount() == 0) {
            return false;
        }
        return true;
    }

    private int getDayOfTheWeek(String glblCmpyCd, String startDt) {
        int dayOfTheWeek = DAT_OF_THE_WEEK_IS_HOLIDAY;
        // START 2023/05/24 T.Nagae [CSA-QC#61365, MOD]
//        CALTMsgArray calTMsgArray = getCal(glblCmpyCd, CAL_TP.CSA_HOLIDAY, DT_ATRB_VAL_CD, startDt);
        CALTMsgArray calTMsgArray = getCal(glblCmpyCd, CAL_TP.SVC_HOLIDAY, DT_ATRB_VAL_CD, startDt);
        // END   2023/05/24 T.Nagae [CSA-QC#61365, MOD]
        if (calTMsgArray.getValidCount() == 0) {
            Calendar cal = Calendar.getInstance();
            int yyyy = Integer.parseInt(startDt.substring(0, SUB_STR_POS_4));
            int mm = Integer.parseInt(startDt.substring(SUB_STR_POS_4, SUB_STR_POS_6));
            int dd = Integer.parseInt(startDt.substring(SUB_STR_POS_6, SUB_STR_POS_8));
            cal.set(yyyy, mm - 1, dd);
            dayOfTheWeek = cal.get(Calendar.DAY_OF_WEEK);
        }
        return dayOfTheWeek;
    }

    private CALTMsgArray getCal(String glblCmpyCd, String calTpCd, String dtAttrbValCd, String calDt) {
        CALTMsg tMsg = new CALTMsg();
        tMsg.setSQLID("903");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("calTpCd01", calTpCd);
        tMsg.setConditionValue("dtAttrbValCd01", dtAttrbValCd);
        tMsg.setConditionValue("calDt01", calDt);
        return (CALTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
    }

    private Map<String, Object> getSvcTermAttrbMapValCd(String glblCmpyCd, String svcTermCondAttrbNm, BigDecimal dsContrPk, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcTermCondAttrbNm", svcTermCondAttrbNm);
        ssmPrm.put("dsContrPk", dsContrPk);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        return (Map<String, Object>) ssmBatClnt.queryObject("getSvcTermAttrbMapValCd", ssmPrm);
    }

    private String getTempEttlInfo(String glblCmpyCd, BigDecimal svcMachMstrPk, String startDt, String startTm, int dayOfTheWeek) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("svcMachMstrPk", svcMachMstrPk);
        ssmPrm.put("startDt", startDt);
        ssmPrm.put("startTm", startTm);
        ssmPrm.put("dayOfTheWeek", dayOfTheWeek);
        return (String) ssmBatClnt.queryObject("getTempEttlInfo", ssmPrm);
    }

    private AHSTMsgArray getAhs(String glblCmpyCd, String ahsCd, String startTm, int dayOfTheWeek) {
        AHSTMsg tMsg = new AHSTMsg();
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("ahsCd01", ahsCd);
        if (dayOfTheWeek == Calendar.SUNDAY) {
            tMsg.setSQLID("001");
            tMsg.setConditionValue("ahsSunFromTm01", startTm);
            tMsg.setConditionValue("ahsSunToTm01", startTm);
        } else if (dayOfTheWeek == Calendar.MONDAY) {
            tMsg.setSQLID("002");
            tMsg.setConditionValue("ahsMonFromTm01", startTm);
            tMsg.setConditionValue("ahsMonToTm01", startTm);
        } else if (dayOfTheWeek == Calendar.TUESDAY) {
            tMsg.setSQLID("003");
            tMsg.setConditionValue("ahsTueFromTm01", startTm);
            tMsg.setConditionValue("ahsTueToTm01", startTm);
        } else if (dayOfTheWeek == Calendar.WEDNESDAY) {
            tMsg.setSQLID("004");
            tMsg.setConditionValue("ahsWedFromTm01", startTm);
            tMsg.setConditionValue("ahsWedToTm01", startTm);
        } else if (dayOfTheWeek == Calendar.THURSDAY) {
            tMsg.setSQLID("005");
            tMsg.setConditionValue("ahsThuFromTm01", startTm);
            tMsg.setConditionValue("ahsThuToTm01", startTm);
        } else if (dayOfTheWeek == Calendar.FRIDAY) {
            tMsg.setSQLID("006");
            tMsg.setConditionValue("ahsFriFromTm01", startTm);
            tMsg.setConditionValue("ahsFriToTm01", startTm);
        } else if (dayOfTheWeek == Calendar.SATURDAY) {
            tMsg.setSQLID("007");
            tMsg.setConditionValue("ahsSatFromTm01", startTm);
            tMsg.setConditionValue("ahsSatToTm01", startTm);
        } else if (dayOfTheWeek == DAT_OF_THE_WEEK_IS_HOLIDAY) {
            tMsg.setSQLID("008");
            tMsg.setConditionValue("ahsHolFromTm01", startTm);
            tMsg.setConditionValue("ahsHolToTm01", startTm);
        } else {
            return new AHSTMsgArray();
        }
        return (AHSTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
    }

    private String getStartTm(NSZC043001PMsg pMsg) {
        // START 2018/09/27 M.Naito [QC#26149, MOD]
//        String setTime = pMsg.slsDt.getValue();
        String setTime = pMsg.svcTaskRcvTm.getValue();
        // END 2018/09/27 M.Naito [QC#26149, MOD]
        SimpleDateFormat sdfForTime = new SimpleDateFormat(TIME_FORMAT);
        Date newTime = new Date();
        try {
            newTime = sdfForTime.parse(setTime);
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdfForTime.parse(setTime));
            cal.add(Calendar.MINUTE, ZYPCodeDataUtil.getNumConstValue(AHS_CALC_MIN, pMsg.glblCmpyCd.getValue()).intValue());
            newTime = new Date(cal.getTimeInMillis());
        } catch (ParseException e) {
            return setTime;
        }
        return sdfForTime.format(newTime);
    }
    // END 2018/07/12 M.Naito [QC#13309, ADD]

    // START 2018/08/14 M.Naito [QC#27472, ADD]
    private boolean checkSvcTaskSts(String glblCmpyCd, String svcTaskNum) {
        if (!hasValue(svcTaskNum)) {
            return false;
        }
        SVC_TASKTMsg svcTaskTMsg = getSvcTask(glblCmpyCd, svcTaskNum);
        if (svcTaskTMsg == null) {
            return false;
        }
        List<String> svcTaskStsCdList = new ArrayList<String>();
        svcTaskStsCdList.add(SVC_TASK_STS.COMPLETED);
        svcTaskStsCdList.add(SVC_TASK_STS.DEBRIEF_ERROR);
        svcTaskStsCdList.add(SVC_TASK_STS.CLOSED);
        svcTaskStsCdList.add(SVC_TASK_STS.CANCELLED);
        for (String svcTaskStsCd : svcTaskStsCdList) {
            if (svcTaskStsCd.equals(svcTaskTMsg.svcTaskStsCd.getValue())) {
                return false;
            }
        }
        return true;
    }
    // END 2018/08/14 M.Naito [QC#27472, ADD]

    // START 2018/08/30 K.Kitachi [QC#22665, ADD]
    private NSZC041001PMsg createNSZC041001PMsg(NSZC043001PMsg pMsg) {
        NSZC041001PMsg param = new NSZC041001PMsg();
        setValue(param.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(param.slsDt, pMsg.slsDt);
        setValue(param.usrId, pMsg.userId);
        setValue(param.svcBillTpCd, pMsg.svcBillTpCd);
        setValue(param.dsSvcCallTpCd, pMsg.dsSvcCallTpCd);
        if (ZYPConstant.FLG_ON_Y.equals(pMsg.billToCustUpdFlg.getValue()) && hasValue(pMsg.billToUpdCustCd)) {
            BILL_TO_CUSTTMsg billToCustTMsg = getBillToCust(pMsg.glblCmpyCd.getValue(), pMsg.billToUpdCustCd.getValue());
            if (billToCustTMsg != null) {
                setValue(param.billToAcctNum, billToCustTMsg.sellToCustCd);
                setValue(param.billToCustCd, billToCustTMsg.billToCustCd);
                return param;
            }
        }
        if (hasValue(pMsg.svcMachMstrPk)) {
            SVC_MACH_MSTRTMsg svcMachMstrTMsg = getSvcMachMstr(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
            if (svcMachMstrTMsg != null) {
                setValue(param.billToAcctNum, svcMachMstrTMsg.billToAcctNum);
                setValue(param.billToCustCd, svcMachMstrTMsg.billToLocNum);
            }
        }
        return param;
    }

    private BILL_TO_CUSTTMsg getBillToCust(String glblCmpyCd, String billToCustCd) {
        BILL_TO_CUSTTMsg tMsg = new BILL_TO_CUSTTMsg();
        tMsg.setSQLID("990");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("billToCustCd01", billToCustCd);
        BILL_TO_CUSTTMsgArray outArray = (BILL_TO_CUSTTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
        if (outArray.getValidCount() > 0) {
            return outArray.no(0);
        }
        return null;
    }

    private void sendMail(NSZC043001PMsg pMsg) {
        // Get From Address
        S21MailGroup groupFrom = new S21MailGroup(pMsg.glblCmpyCd.getValue(), MAIL_GROUP_ID_FROM);
        // START 2021/08/03 L.Mandanas [QC#59066, ADD]
        groupFrom.setMailKey1(MAIL_GROUP_MAIL_KEY_FROM);
        // END 2021/08/03 L.Mandanas [QC#59066, ADD]
        List<S21MailAddress> fromAddrList = groupFrom.getMailAddress();
        if (fromAddrList == null || fromAddrList.isEmpty()) {
            return;
        }

        // Get To Address
        List<S21MailAddress> toAddrList = new ArrayList<S21MailAddress>();
        List<String> emlAddrList = getEmlAddrList(pMsg);
        for (String emlAddr : emlAddrList) {
            toAddrList.add(new S21MailAddress(pMsg.glblCmpyCd.getValue(), emlAddr));
        }

        if (toAddrList == null || toAddrList.isEmpty()) {
            return;
        }

        // Get Mail Template
        S21MailTemplate template = new S21MailTemplate(pMsg.glblCmpyCd.getValue(), MAIL_TEMPLATE_ID);
        if (template == null || !hasValue(template.getBody())) {
            throw new S21AbendException(NSZM0185E);
        }

        // Mail Body Edit Start
        Map<String, Object> mailInfo = getMailInfo(pMsg);
        if (mailInfo == null) {
            return;
        }
        template.setTemplateParameter("custNm", (String) mailInfo.get("DS_ACCT_NM"));
        template.setTemplateParameter("address", (String) mailInfo.get("LINE_ADDR"));
        template.setTemplateParameter("ctacNm", pMsg.svcCustAttnTxt.getValue());
        template.setTemplateParameter("ctacNum", pMsg.custTelNum.getValue());
        template.setTemplateParameter("mdlNm", (String) mailInfo.get("T_MDL_NM"));
        template.setTemplateParameter("serNum", (String) mailInfo.get("SER_NUM"));
        Map<String, Object> custInfo = getCustInfo(pMsg);
        if (custInfo != null) {
            template.setTemplateParameter("custNm", (String) custInfo.get("DS_ACCT_NM"));
            template.setTemplateParameter("address", (String) custInfo.get("LINE_ADDR"));
        }
        // Mail Body Edit End

        // Call Mail API
        S21Mail mail = new S21Mail(pMsg.glblCmpyCd.getValue());
        mail.setFromAddress(fromAddrList.get(0));
        mail.setToAddressList(toAddrList);
        mail.setMailTemplate(template);
        mail.postMail();
    }

    private List<String> getEmlAddrList(NSZC043001PMsg pMsg) {
        List<String> emlAddrList = new ArrayList<String>();
        List<String> dtyMgrEmlAddrList = getDtyMgrEmlAddrList(pMsg);
        for (String dtyMgrEmlAddr : dtyMgrEmlAddrList) {
            if (!emlAddrList.contains(dtyMgrEmlAddr)) {
                emlAddrList.add(dtyMgrEmlAddr);
            }
        }
        List<String> cltEmlAddrList = getCltEmlAddrList(pMsg);
        if (cltEmlAddrList.size() == 0) {
            String defCltEmlAddr = ZYPCodeDataUtil.getVarCharConstValue(AR_CLT_DEF_EML_ADDR, pMsg.glblCmpyCd.getValue());
            emlAddrList.add(defCltEmlAddr);
            return emlAddrList;
        }
        for (String cltEmlAddr : cltEmlAddrList) {
            if (!emlAddrList.contains(cltEmlAddr)) {
                emlAddrList.add(cltEmlAddr);
            }
        }
        return emlAddrList;
    }

    private List<String> getDtyMgrEmlAddrList(NSZC043001PMsg pMsg) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmPrm.put("svcMachMstrPk", pMsg.svcMachMstrPk.getValue());
        ssmPrm.put("salesDate", pMsg.slsDt.getValue());
        ssmPrm.put("sysTmStamp", this.systemTimeStamp);
        ssmPrm.put("flgY", ZYPConstant.FLG_ON_Y);
        ssmPrm.put("service", BIZ_AREA_ORG.SERVICE);
        // QC#61069 2023/05/26 Add Start
        ssmPrm.put("svcByLineBizTpCdPps", SVC_BY_LINE_BIZ_TP_CD_PPS);
        ssmPrm.put("svcByLineBizTpCdLfs", SVC_BY_LINE_BIZ_TP_CD_LFS);
        // QC#61069 2023/05/26 Add End
        return (List<String>) this.ssmBatClnt.queryObjectList("getDtyMgrEmlAddrList", ssmPrm);
    }

    private List<String> getCltEmlAddrList(NSZC043001PMsg pMsg) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        if (ZYPConstant.FLG_ON_Y.equals(pMsg.billToCustUpdFlg.getValue()) && hasValue(pMsg.billToUpdCustCd)) {
            BILL_TO_CUSTTMsg billToCustTMsg = getBillToCust(pMsg.glblCmpyCd.getValue(), pMsg.billToUpdCustCd.getValue());
            if (billToCustTMsg != null) {
                ssmPrm.put("sellToCustCd", billToCustTMsg.sellToCustCd.getValue());
                ssmPrm.put("billToCustCd", billToCustTMsg.billToCustCd.getValue());
                return (List<String>) this.ssmBatClnt.queryObjectList("getCltEmlAddrList", ssmPrm);
            }
        }
        if (hasValue(pMsg.svcMachMstrPk)) {
            SVC_MACH_MSTRTMsg svcMachMstrTMsg = getSvcMachMstr(pMsg.glblCmpyCd.getValue(), pMsg.svcMachMstrPk.getValue());
            if (svcMachMstrTMsg != null) {
                ssmPrm.put("sellToCustCd", svcMachMstrTMsg.billToAcctNum.getValue());
                ssmPrm.put("billToCustCd", svcMachMstrTMsg.billToLocNum.getValue());
                return (List<String>) this.ssmBatClnt.queryObjectList("getCltEmlAddrList", ssmPrm);
            }
        }
        return new ArrayList<String>();
    }

    private Map<String, Object> getMailInfo(NSZC043001PMsg pMsg) {
        if (!hasValue(pMsg.svcMachMstrPk)) {
            return null;
        }
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmPrm.put("svcMachMstrPk", pMsg.svcMachMstrPk.getValue());
        return (Map<String, Object>) this.ssmBatClnt.queryObject("getMailInfo", ssmPrm);
    }

    private Map<String, Object> getCustInfo(NSZC043001PMsg pMsg) {
        if (!ZYPConstant.FLG_ON_Y.equals(pMsg.shipToCustUpdFlg.getValue())) {
            return null;
        }
        if (!hasValue(pMsg.shipToUpdCustCd)) {
            return null;
        }
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmPrm.put("shipToCustCd", pMsg.shipToUpdCustCd.getValue());
        return (Map<String, Object>) this.ssmBatClnt.queryObject("getCustInfo", ssmPrm);
    }
    // END 2018/08/30 K.Kitachi [QC#22665, ADD]

    // START 2018/11/29 K.Kitachi [QC#29459, ADD]
    private boolean isPhoneFix(SVC_TASKTMsg svcTaskTMsg) {
        if (DS_SVC_CALL_TP.PHONE_FIX_DISPATCHER.equals(svcTaskTMsg.dsSvcCallTpCd.getValue())) {
            return true;
        }
        if (DS_SVC_CALL_TP.PHONE_FIX_TECHNICIAN.equals(svcTaskTMsg.dsSvcCallTpCd.getValue())) {
            return true;
        }
        if (DS_SVC_CALL_TP.AHS_PHONE_FIX_DISPATCHER.equals(svcTaskTMsg.dsSvcCallTpCd.getValue())) {
            return true;
        }
        if (DS_SVC_CALL_TP.AHS_PHONE_FIX_TECHNICIAN.equals(svcTaskTMsg.dsSvcCallTpCd.getValue())) {
            return true;
        }
        return false;
    }
    // END 2018/11/29 K.Kitachi [QC#29459, ADD]

    // START 2020/04/06 K.Kitachi [QC#56229, ADD]
    private String getRhsDsSvcCallTpCd(NSZC043001PMsg pMsg, DS_SVC_CALL_TPTMsg dsSvcCallTpTMsg) {
        if (!ZYPConstant.FLG_ON_Y.equals(dsSvcCallTpTMsg.aftHrsFlg.getValue())) {
            return null;
        }
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmPrm.put("ahsDsSvcCallTpCd", dsSvcCallTpTMsg.dsSvcCallTpCd.getValue());
        return (String) this.ssmBatClnt.queryObject("getRhsDsSvcCallTpCd", ssmPrm);
    }
    // END 2020/04/06 K.Kitachi [QC#56229, ADD]

    // START 2023/05/31 K.Watanabe [QC#61309, ADD]
    private void createSendLaborAndTravelRatesMessage(String glblCmpyCd, NSZC043001PMsg pMsg, SVC_TASKTMsg svcTaskTMsg) {
        String svcBillTpCd = svcTaskTMsg.svcBillTpCd.getValue();
        String dsSvcCallTpCd = svcTaskTMsg.dsSvcCallTpCd.getValue();
        if (SVC_BILL_TP._3_CHARGE_FOR_DRUMS_ONLY.equals(svcBillTpCd) && (DS_SVC_CALL_TP.RECALL.equals(dsSvcCallTpCd) || DS_SVC_CALL_TP.AHS_RECALL.equals(dsSvcCallTpCd))) {
            return;
        }

        SVC_BILL_TPTMsg svcBillTpTMsg = getSvcBillTp(glblCmpyCd, svcBillTpCd);
        if (ZYPConstant.FLG_OFF_N.equals(svcBillTpTMsg.bllblFlg.getValue())) {
            return;
        }

        SVC_MACH_MSTRTMsg svcMachMstr = getSvcMachMstr(glblCmpyCd, svcTaskTMsg.svcMachMstrPk.getValue());
        NSZC061001PMsg svcPrcApiPMsg = callSvcPrcApiForGetSendLaborAndTravelRatesMessage(pMsg, svcTaskTMsg, svcMachMstr);

        // START 2023/06/14 K.Watanabe [QC#61309, MOD]
//        insertSvcMemo(glblCmpyCd, pMsg, svcTaskTMsg, getSvcCmntTxt(svcPrcApiPMsg, dsSvcCallTpCd, svcMachMstr.svcByLineBizTpCd.getValue()));
        insertSvcMemo(glblCmpyCd, pMsg, svcTaskTMsg, getSvcCmntTxt(svcPrcApiPMsg, dsSvcCallTpCd, svcBillTpTMsg.freeHrsNum.getValue(), svcMachMstr.svcByLineBizTpCd.getValue()));
        // END 2023/06/14 K.Watanabe [QC#61309, MOD]
    }

    // START 2023/06/14 K.Watanabe [QC#61309, MOD]
//    private String getSvcCmntTxt(NSZC061001PMsg svcPrcApiPMsg, String dsSvcCallTpCd, String svcByLineBizTpCd) {
    private String getSvcCmntTxt(NSZC061001PMsg svcPrcApiPMsg, String dsSvcCallTpCd, BigDecimal freeHrsNum, String svcByLineBizTpCd) {
        BigDecimal svcLborUnitAmt = svcPrcApiPMsg.svcLborUnitAmt.getValue();
        BigDecimal svcMinChrgHrsAot = svcPrcApiPMsg.svcMinChrgHrsAot.getValue();
        if (svcMinChrgHrsAot != null && BigDecimal.ZERO.compareTo(svcMinChrgHrsAot) < 0) {
            svcLborUnitAmt = svcPrcApiPMsg.svcLborUnitAmt.getValue().divide(svcPrcApiPMsg.svcMinChrgHrsAot.getValue(), DOLLAR_FRACTION, BigDecimal.ROUND_UP);
        }
        if (ZYPConstant.FLG_ON_Y.equals(svcPrcApiPMsg.ahsCdEnblFlg.getValue()) && ZYPConstant.FLG_ON_Y.equals(svcPrcApiPMsg.xxAftHrsPopDplyFlg.getValue())) {
//          if (DS_SVC_CALL_TP.AHS_RECALL.equals(dsSvcCallTpCd)) {
            if (DS_SVC_CALL_TP.AHS_RECALL.equals(dsSvcCallTpCd) && freeHrsNum != null && BigDecimal.ZERO.compareTo(freeHrsNum) < 0) {
                // START 2023/07/21 K.Watanabe [QC#61309, MOD]
                // if (COA_LINE_BIZ_TP.ESS.equals(svcByLineBizTpCd)) {
                if (LINE_BIZ_TP.ESS.equals(svcByLineBizTpCd)) {
                // END 2023/07/21 K.Watanabe [QC#61309, MOD]
//                    return S21MessageFunc.clspGetMessage(NSZM1397I, new String[] {svcPrcApiPMsg.svcLborUnitAmt.getValue().toString()}).substring(BEGIN_INDEX_FOR_MESSAGE);
                    return S21MessageFunc.clspGetMessage(NSZM1397I, new String[] {svcLborUnitAmt.toString()}).substring(BEGIN_INDEX_FOR_MESSAGE);
                } else if (hasValue(svcPrcApiPMsg.svcTrvlUnitAmt) && !BigDecimal.ZERO.equals(svcPrcApiPMsg.svcTrvlUnitAmt.getValue())) {
//                    return S21MessageFunc.clspGetMessage(NSZM1398I, new String[] {svcPrcApiPMsg.svcLborUnitAmt.getValue().toString(), svcPrcApiPMsg.svcTrvlUnitAmt.getValue().toString()}).substring(BEGIN_INDEX_FOR_MESSAGE);
                    return S21MessageFunc.clspGetMessage(NSZM1398I, new String[] {svcLborUnitAmt.toString(), svcPrcApiPMsg.svcTrvlUnitAmt.getValue().toString()}).substring(BEGIN_INDEX_FOR_MESSAGE);
                } else {
//                    return S21MessageFunc.clspGetMessage(NSZM1399I, new String[] {svcPrcApiPMsg.svcLborUnitAmt.getValue().toString()}).substring(BEGIN_INDEX_FOR_MESSAGE);
                    return S21MessageFunc.clspGetMessage(NSZM1399I, new String[] {svcLborUnitAmt.toString()}).substring(BEGIN_INDEX_FOR_MESSAGE);
                }
            } else {
                // START 2023/07/21 K.Watanabe [QC#61309, MOD]
                // if (COA_LINE_BIZ_TP.ESS.equals(svcByLineBizTpCd)) {
                if (LINE_BIZ_TP.ESS.equals(svcByLineBizTpCd)) {
                // END 2023/07/21 K.Watanabe [QC#61309, MOD]
//                    return S21MessageFunc.clspGetMessage(NSZM1400I, new String[] {svcPrcApiPMsg.svcLborUnitAmt.getValue().toString()}).substring(BEGIN_INDEX_FOR_MESSAGE);
                    return S21MessageFunc.clspGetMessage(NSZM1400I, new String[] {svcLborUnitAmt.toString()}).substring(BEGIN_INDEX_FOR_MESSAGE);
                } else if (hasValue(svcPrcApiPMsg.svcTrvlUnitAmt) && !BigDecimal.ZERO.equals(svcPrcApiPMsg.svcTrvlUnitAmt.getValue())) {
//                    return S21MessageFunc.clspGetMessage(NSZM1401I, new String[] {svcPrcApiPMsg.svcLborUnitAmt.getValue().toString(), svcPrcApiPMsg.svcMinChrgHrsAot.getValue().toString(), svcPrcApiPMsg.svcTrvlUnitAmt.getValue().toString()}).substring(BEGIN_INDEX_FOR_MESSAGE);
                    return S21MessageFunc.clspGetMessage(NSZM1401I, new String[] {svcLborUnitAmt.toString(), svcMinChrgHrsAot.toString(), svcPrcApiPMsg.svcTrvlUnitAmt.getValue().toString()}).substring(BEGIN_INDEX_FOR_MESSAGE);
                } else {
//                    return S21MessageFunc.clspGetMessage(NSZM1402I, new String[] {svcPrcApiPMsg.svcLborUnitAmt.getValue().toString(), svcPrcApiPMsg.svcMinChrgHrsAot.getValue().toString()}).substring(BEGIN_INDEX_FOR_MESSAGE);
                    return S21MessageFunc.clspGetMessage(NSZM1402I, new String[] {svcLborUnitAmt.toString(), svcMinChrgHrsAot.toString()}).substring(BEGIN_INDEX_FOR_MESSAGE);
                }
            }
        } else {
//            if (DS_SVC_CALL_TP.RECALL.equals(dsSvcCallTpCd)) {
            if (DS_SVC_CALL_TP.RECALL.equals(dsSvcCallTpCd) && freeHrsNum != null && BigDecimal.ZERO.compareTo(freeHrsNum) < 0) {
                // START 2023/07/21 K.Watanabe [QC#61309, MOD]
                // if (COA_LINE_BIZ_TP.ESS.equals(svcByLineBizTpCd)) {
                if (LINE_BIZ_TP.ESS.equals(svcByLineBizTpCd)) {
                // END 2023/07/21 K.Watanabe [QC#61309, MOD]
//                    return S21MessageFunc.clspGetMessage(NSZM1397I, new String[] {svcPrcApiPMsg.svcLborUnitAmt.getValue().toString()}).substring(BEGIN_INDEX_FOR_MESSAGE);
                    return S21MessageFunc.clspGetMessage(NSZM1397I, new String[] {svcLborUnitAmt.toString()}).substring(BEGIN_INDEX_FOR_MESSAGE);
                } else if (hasValue(svcPrcApiPMsg.svcTrvlUnitAmt) && !BigDecimal.ZERO.equals(svcPrcApiPMsg.svcTrvlUnitAmt.getValue())) {
//                    return S21MessageFunc.clspGetMessage(NSZM1398I, new String[] {svcPrcApiPMsg.svcLborUnitAmt.getValue().toString(), svcPrcApiPMsg.svcTrvlUnitAmt.getValue().toString()}).substring(BEGIN_INDEX_FOR_MESSAGE);
                    return S21MessageFunc.clspGetMessage(NSZM1398I, new String[] {svcLborUnitAmt.toString(), svcPrcApiPMsg.svcTrvlUnitAmt.getValue().toString()}).substring(BEGIN_INDEX_FOR_MESSAGE);
                } else {
//                    return S21MessageFunc.clspGetMessage(NSZM1399I, new String[] {svcPrcApiPMsg.svcLborUnitAmt.getValue().toString()}).substring(BEGIN_INDEX_FOR_MESSAGE);
                    return S21MessageFunc.clspGetMessage(NSZM1399I, new String[] {svcLborUnitAmt.toString()}).substring(BEGIN_INDEX_FOR_MESSAGE);
                }
            } else {
                // START 2023/07/21 K.Watanabe [QC#61309, MOD]
                // if (COA_LINE_BIZ_TP.ESS.equals(svcByLineBizTpCd)) {
                if (LINE_BIZ_TP.ESS.equals(svcByLineBizTpCd)) {
                // END 2023/07/21 K.Watanabe [QC#61309, MOD]
//                    return S21MessageFunc.clspGetMessage(NSZM1403I, new String[] {svcPrcApiPMsg.svcLborUnitAmt.getValue().toString()}).substring(BEGIN_INDEX_FOR_MESSAGE);
                    return S21MessageFunc.clspGetMessage(NSZM1403I, new String[] {svcLborUnitAmt.toString()}).substring(BEGIN_INDEX_FOR_MESSAGE);
                } else if (hasValue(svcPrcApiPMsg.svcTrvlUnitAmt) && !BigDecimal.ZERO.equals(svcPrcApiPMsg.svcTrvlUnitAmt.getValue())) {
//                    return S21MessageFunc.clspGetMessage(NSZM1401I, new String[] {svcPrcApiPMsg.svcLborUnitAmt.getValue().toString(), svcPrcApiPMsg.svcMinChrgHrsAot.getValue().toString(), svcPrcApiPMsg.svcTrvlUnitAmt.getValue().toString()}).substring(BEGIN_INDEX_FOR_MESSAGE);
                    return S21MessageFunc.clspGetMessage(NSZM1401I, new String[] {svcLborUnitAmt.toString(), svcMinChrgHrsAot.toString(), svcPrcApiPMsg.svcTrvlUnitAmt.getValue().toString()}).substring(BEGIN_INDEX_FOR_MESSAGE);
                } else {
//                    return S21MessageFunc.clspGetMessage(NSZM1402I, new String[] {svcPrcApiPMsg.svcLborUnitAmt.getValue().toString(), svcPrcApiPMsg.svcMinChrgHrsAot.getValue().toString()}).substring(BEGIN_INDEX_FOR_MESSAGE);
                    return S21MessageFunc.clspGetMessage(NSZM1402I, new String[] {svcLborUnitAmt.toString(), svcMinChrgHrsAot.toString()}).substring(BEGIN_INDEX_FOR_MESSAGE);
                }
            }
        }
    }
    // END 2023/06/14 K.Watanabe [QC#61309, MOD]

    private void insertSvcMemo(String glblCmpyCd, NSZC043001PMsg pMsg, SVC_TASKTMsg svcTaskTMsg, String svcCmntTxt) {
        SVC_MEMOTMsg tmsg = new SVC_MEMOTMsg();
        BigDecimal newSvcMemoPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MEMO_SQ);
        setValue(tmsg.glblCmpyCd, glblCmpyCd);
        setValue(tmsg.svcMemoPk, newSvcMemoPk);
        setValue(tmsg.svcMemoCatgCd, SVC_MEMO_CATG.DISPATCH_MEMO);
        setValue(tmsg.svcMemoTpCd, SVC_MEMO_TP.GENERAL);
        setValue(tmsg.svcCmntTxt, svcCmntTxt);
        setValue(tmsg.svcTaskNum, svcTaskTMsg.svcTaskNum.getValue());
        setValue(tmsg.fsrNum, svcTaskTMsg.fsrNum.getValue());
        setValue(tmsg.lastUpdUsrId, pMsg.userId.getValue());
        setValue(tmsg.lastUpdTs, ZYPDateUtil.getCurrentSystemTime(S21SessionHelper.EZD_TIME_FORMAT));
        EZDTBLAccessor.insert(tmsg);
    }
    // END 2023/05/31 K.Watanabe [QC#61309, ADD]

    // START 2023/07/14 K.Watanabe [QC#61310, ADD]
    private boolean isShowRoomMachine(String glblCmpyCd, BigDecimal svcMachMstrPk, String startDt) {
        boolean isShowRoomMachine = false;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("svcMachMstrPk", svcMachMstrPk);
        List<String> svcMachMstrStsCdList = new ArrayList<String>();
        svcMachMstrStsCdList.add(SVC_MACH_MSTR_STS.CREATED);
        svcMachMstrStsCdList.add(SVC_MACH_MSTR_STS.REMOVED);
        paramMap.put("svcMachMstrStsCdList", svcMachMstrStsCdList);
        paramMap.put("svcMachTpCd", SVC_MACH_TP.MACHINE);
        paramMap.put("rtlWhCatgCd", RTL_WH_CATG.SHOWROOM);
        paramMap.put("startDt", startDt);
        BigDecimal showRoomMachineCnt = (BigDecimal) this.ssmBatClnt.queryObject("getShowRoomMachineCnt", paramMap);
        if (showRoomMachineCnt != null && showRoomMachineCnt.compareTo(BigDecimal.ZERO) > 0) {
            isShowRoomMachine = true;
        }
        return isShowRoomMachine;
    }
    // END 2023/07/14 K.Watanabe [QC#61310, ADD]

    // START 2023/09/15 K.Watanabe [QC#61310, ADD]
    private String getSellToCustCd(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", glblCmpyCd);
        paramMap.put("svcMachMstrPk", svcMachMstrPk);
        paramMap.put("svcMachTpCd", SVC_MACH_TP.MACHINE);
        return (String) this.ssmBatClnt.queryObject("getSellToCustCd", paramMap);
    }
    // END 2023/09/15 K.Watanabe [QC#61310, ADD]
}
