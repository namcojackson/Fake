/**
 * <pre>
 * Copyright (c) 2017 Canon USA Inc. All rights reserved.
 * </pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB077001;

import static com.canon.cusa.s21.batch.NSA.NSAB077001.constant.NSAB077001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_OFF_N;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.text.DateFormat;
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

import business.db.BLLG_CYCLETMsg;
import business.db.CONTR_PHYS_BLLG_MTR_RELNTMsg;
import business.db.CONTR_PHYS_BLLG_MTR_RELNTMsgArray;
import business.db.CPOTMsg;
import business.db.CPO_DTLTMsg;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_ADDL_CHRGTMsg;
import business.db.DS_CONTR_ADDL_CHRGTMsgArray;
import business.db.DS_CONTR_BLLG_MTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsgArray;
import business.db.DS_CONTR_CR_CARD_POTMsg;
import business.db.DS_CONTR_CR_CARD_POTMsgArray;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_INTFC_DEF_RULETMsg;
import business.db.DS_CONTR_INTFC_DEF_RULETMsgArray;
import business.db.DS_CONTR_PRC_EFFTMsg;
import business.db.DS_CONTR_RNW_ESCLTMsg;
import business.db.DS_SUB_CONTRTMsg;
import business.db.DS_SUB_CONTRTMsgArray;
import business.db.GLBL_CMPYTMsg;
import business.db.MDSETMsg;
import business.db.PRC_SVC_CONTR_TPTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsgArray;
import business.db.SVC_TERM_CONDTMsg;
import business.db.SVC_TERM_CONDTMsgArray;
import business.db.SVC_TERM_COND_ATTRBTMsg;
import business.db.SVC_TERM_COND_ATTRBTMsgArray;
import business.db.WAIT_ISTL_CONTRTMsg;
import business.parts.NSZC010001PMsg;
import business.parts.NSZC046001PMsg;
import business.parts.NSZC046001_xxContrDtlListPMsg;
import business.parts.NSZC046001_xxDsContrBllgMtrListPMsg;
import business.parts.NSZC046001_xxDsContrSegAllocListPMsg;
import business.parts.NSZC057001PMsg;
import business.parts.NWZC172001PMsg;
import business.parts.NWZC172002PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC010001.NSZC010001;
import com.canon.cusa.s21.api.NSZ.NSZC046001.NSZC046001;
import com.canon.cusa.s21.api.NSZ.NSZC046001.constant.NSZC046001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC057001.NSZC057001;
import com.canon.cusa.s21.api.NSZ.NSZC077001.ContrTrkProcMode;
import com.canon.cusa.s21.api.NWZ.NWZC172001.NWZC172001;
import com.canon.cusa.s21.api.NWZ.NWZC172001.constant.NWZC172001Constant;
import com.canon.cusa.s21.common.NSX.NSXC001001.ContrDurationInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001CalcUplftInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContrDurationCalculator;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContractTracking;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001DefaultSvcPgmGetter;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetRspTime;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetUplftFromDt;
import com.canon.cusa.s21.common.NSX.NSXC001001.SlaInfoBean;
import com.canon.cusa.s21.common.NSX.NSXC001001.UpliftInfoBean;
import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001Exchange;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_INTFC_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_UPLFT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CRAT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_INTFC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_VLD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FSR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_EST_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_READ_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.START_READ_VLD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STUB_PREP_FROM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STUB_PREP_THRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_MERGE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPExtnNumbering;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;

/**
 * <pre>
 * Update Contract For Shell Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/24/2017   Hitachi         A.Kohinata      Create          N/A
 * 06/07/2017   Hitachi         A.Kohinata      Update          QC#18850
 * 06/16/2017   Hitachi         K.Kishimoto     Update          QC#19240
 * 06/17/2017   Hitachi         K.Kitachi       Update          QC#18177
 * 06/27/2017   Hitachi         T.Kanasaka      Update          QC#19560
 * 06/27/2017   Hitachi         A.Kohinata      Update          QC#18525
 * 07/10/2017   Hitachi         T.Tomita        Update          QC#19818
 * 07/11/2017   Hitachi         K.Kojima        Update          QC#19822
 * 07/13/2017   Hitachi         K.Kojima        Update          QC#19908
 * 07/26/2017   Hitachi         K.Kojima        Update          QC#20090
 * 07/11/2017   Hitachi         K.Kasai         Update          QC#18780
 * 07/27/2017   Hitachi         K.Kasai         Update          QC#18882
 * 07/31/2017   Hitachi         M.Kidokoro      Update          QC#20116
 * 08/22/2017   Hitachi         Y.Takeno        Update          QC#20663
 * 08/08/2017   Hitachi         A.Kohinata      Update          QC#18799
 * 08/28/2017   Hitachi         Y.Takeno        Update          QC#20665
 * 08/29/2017   Hitachi         K.Yamada        Update          QC#20576
 * 09/08/2017   Hitachi         Y.Takeno        Update          QC#20096
 * 09/11/2017   Hitachi         T.Tomita        Update          QC#21039
 * 09/15/2017   Hitachi         K.Kim           Update          QC#21162
 * 09/21/2017   Hitachi         K.Kim           Update          QC#21226
 * 09/22/2017   Hitachi         A.Kohinata      Update          QC#18534
 * 10/02/2017   Hitachi         A.Kohinata      Update          QC#21567
 * 10/06/2017   Hitachi         A.Kohinata      Update          QC#21639
 * 10/10/2017   Hitachi         A.Kohinata      Update          QC#21617
 * 2017/10/18   Hitachi         K.Kitachi       Update          QC#21622
 * 2017/10/23   Hitachi         K.Kitachi       Update          QC#21622
 * 2017/10/24   Hitachi         K.Kitachi       Update          QC#22048
 * 2017/10/24   Hitachi         U.Kim           Update          QC#21864, 21865
 * 2017/11/14   Hitachi         K.Kishimoto     Update          QC#22526
 * 2017/11/16   Hitachi         K.Yamada        Update          QC#22422,22442
 * 2017/12/04   Hitachi         K.Ochiai        Update          QC#21698
 * 2017/12/14   Hitachi         T.Tomita        Update          QC#18362
 * 2018/01/09   Hitachi         M.Kidokoro      Update          QC#20635
 * 2018/01/15   Hitachi         K.Kojima        Update          QC#23255
 * 2018/02/23   Hitachi         K.Kojima        Update          QC#21685
 * 2018/02/28   CITS            M.Naito         Update          QC#22475
 * 2018/03/13   Hitachi         K.Kojima        Update          QC#24263
 * 2018/04/02   Hitachi         U.Kim           Update          QC#23559(Sol358)
 * 2018/04/06   Hitachi         U.Kim           Update          QC#23378(Sol320)
 * 2018/04/26   Hitachi         U.Kim           Update          QC#23378(Sol320)
 * 2018/04/26   Hitachi         T.Tomita        Update          QC#25638
 * 2018/05/07   Hitachi         U.Kim           Update          QC#25895
 * 2018/05/09   Hitachi         K.Kitachi       Update          QC#25728
 * 2018/05/07   Fujitsu         A.Kosai         Update          QC#22482
 * 2018/05/16   Fujitsu         M.Yamada        Update          QC#25030
 * 2018/06/13   Fujitsu         M.Ohno          Update          QC#26495
 * 2018/06/25   CITS            T.Wada          Update          QC#24266
 * 2018/07/02   Hitachi         K.Kim           Update          QC#24146
 * 2018/08/24   Hitachi         K.Kojima        Update          QC#27919
 * 2018/08/30   CITS            M.Naito         Update          QC#27102
 * 2018/11/13   Hitachi         K.Kitachi       Update          QC#28638
 * 2019/01/21   Hitachi         K.Kim           Update          QC#29782
 * 2019/01/23   Fujitsu         W.Honda         Update          QC#29371
 * 2019/01/29   Hitachi         K.Kim           Update          QC#29782-1
 * 2019/02/05   Hitachi         K.Kim           Update          QC#30244
 * 2019/02/28   Hitachi         K.Kim           Update          QC#30503
 * 2019/03/12   Hitachi         K.Kitachi       Update          QC#30732
 * 2019/05/24   Hitachi         K.Kim           Update          QC#50394
 * 2019/06/17   Fujitsu         W.HOnda         Update          QC#50842
 * 2019/07/30   Hitachi         A.Kohinata      Update          QC#52070
 * 2020/03/24   Hitachi         A.Kohinata      Update          QC#54318
 * 2020/04/14   Hitachi         A.Kohinata      Update          QC#54318-1
 * 2020/06/17   Hitachi         K.Yamada       Update          QC#54318-2
 * 2022/06/22   CITS            E.Sanchez       Update          QC#59804
 *</pre>
 */

public class NSAB077001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String salesDate = null;

    /** User Variable1 */
    private String usrVar1 = null;

    /** Process User ID */
    private String userId = null;

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Termination Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Normal Count */
    private int normalCount = 0;

    /** Error Count */
    private int errorCount = 0;

    @Override
    protected void initRoutine() {

        // Get GLBL_CMPY_CD
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NSAM0177E);
        }

        // Get Sales Date
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd, BATCH_ID);
        if (!ZYPCommonFunc.hasValue(this.salesDate)) {
            throw new S21AbendException(NSAM0178E);
        }

        // Get User Variable1
        this.usrVar1 = getUserVariable1();
        if (!hasValue(usrVar1)) {
            throw new S21AbendException(ZZZM9006E, new String[] {"User Variable1" });
        }

        // Initialize
        this.userId = this.getClass().getSimpleName().substring(0, INT_8);
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {

        if (BATCH_MODE_DAILY.equals(this.usrVar1)) {
            doProcessDaily();
        } else if (BATCH_MODE_NIGHTLY.equals(this.usrVar1)) {
            doProcessNightly();
        }
    }

    @Override
    protected void termRoutine() {
        if (this.errorCount > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }
        setTermState(this.termCd, this.normalCount, this.errorCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSAB077001().executeBatch(NSAB077001.class.getSimpleName());
    }

    private void doProcessDaily() {
        // 1.3.1.Get Shell Contract(Daily)
        List<BigDecimal> shellContrList = getShellContrDaily();

        for (BigDecimal dsContrPk : shellContrList) {
            // 1.3.2.Get Shell Contract Detail(Daily)
            List<Map<String, Object>> shellContrDtlList = getShellContrDtlDaily(dsContrPk);

            // add start 2020/03/24 QC#54318
            if (shellContrDtlList.size() == 0) {
                continue;
            }
            // add end 2020/03/24 QC#54318

            // 1.4.-7.Update Shell Contract
            // START 2017/09/22 A.Kohinata [QC#18534,MOD]
            if (!updateShellContr(dsContrPk, shellContrDtlList, PROC_NEW)) {
            // END 2017/09/22 A.Kohinata [QC#18534,MOD]
                this.errorCount++;
                rollback();
                continue;
            }

            // 9.Commit
            this.normalCount++;
            commit();
        }

        // add start 2017/10/10 QC#21617
        List<BigDecimal> instlCompWtyContrList = getContrHdrForInstlCompWty();
        for (BigDecimal dsContrPk : instlCompWtyContrList) {
            if (!deleteWaitIstlContrForContrStsChng(dsContrPk)) {
                this.errorCount++;
                rollback();
                continue;
            }
            List<Map<String, Object>> instlCompContrDtlList = getContrDtlForInstlComp(dsContrPk);
            if (instlCompContrDtlList.size() > 0) {
                if (!updateShellContrWty(dsContrPk, instlCompContrDtlList)) {
                    this.errorCount++;
                    rollback();
                    continue;
                }
            }
            this.normalCount++;
            commit();
        }
        // add end 2017/10/10 QC#21617

        // START 2017/09/22 A.Kohinata [QC#18534,ADD]
        List<BigDecimal> instlCompContrList = getContrHdrForInstlComp();
        for (BigDecimal dsContrPk : instlCompContrList) {
            // mod start 2017/10/06 QC#21639
            if (!deleteWaitIstlContrForContrStsChng(dsContrPk)) {
                this.errorCount++;
                rollback();
                continue;
            }

            List<Map<String, Object>> instlCompContrDtlList = getContrDtlForInstlComp(dsContrPk);
            if (instlCompContrDtlList.size() > 0) {
                if (!updateShellContr(dsContrPk, instlCompContrDtlList, PROC_INSTL_COMP)) {
                    this.errorCount++;
                    rollback();
                    continue;
                }
            }
            // mod end 2017/10/06 QC#21639

            this.normalCount++;
            commit();

            // START 2018/02/28 M.Naito [QC#22475,ADD]
            // Update SubContract
            if (instlCompContrDtlList.size() > 0) {
                if (!updateSubContr(instlCompContrDtlList)) {
                    rollback();
                    continue;
                }
            }
            commit();
            // END 2018/02/28 M.Naito [QC#22475,ADD]
        }
        // END 2017/09/22 A.Kohinata [QC#18534,ADD]
    }

    private void doProcessNightly() {
        // 1.1.Get Conversion Order
        List<String> convOrdList = getConvOrd();

        for (String cpoOrdNum : convOrdList) {
            // 1.2.Terminate Contract
            if (!terminateContr(cpoOrdNum)) {
                this.errorCount++;
                rollback();
                continue;
            }

            // 1.3.1.Get Shell Contract(Nightly)
            List<BigDecimal> shellContrList = getShellContrNightly(cpoOrdNum);

            boolean errorFlg = false;
            for (BigDecimal dsContrPk : shellContrList) {
                // 1.3.2.Get shell Contract Detail(Nightly)
                List<Map<String, Object>> shellContrDtlList = getShellContrDtlNightly(cpoOrdNum, dsContrPk);

                // add start 2020/03/24 QC#54318
                if (shellContrDtlList.size() == 0) {
                    continue;
                }
                // add end 2020/03/24 QC#54318

                // 1.4.-7.Update Shell Contract
                // START 2017/09/22 A.Kohinata [QC#18534,MOD]
                if (!updateShellContr(dsContrPk, shellContrDtlList, PROC_NEW)) {
                // END 2017/09/22 A.Kohinata [QC#18534,MOD]
                    errorFlg = true;
                    break;
                }
            }
            if (errorFlg) {
                this.errorCount++;
                rollback();
                continue;
            }

            // 8.Update Conversion Order Status
            if (!updateConvOrdStatus(cpoOrdNum)) {
                this.errorCount++;
                rollback();
                continue;
            }

            // 9.Commit
            this.normalCount++;
            commit();
        }
    }

    // 1.1.Get Conversion Order
    private List<String> getConvOrd() {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("inProcess", CONV_PROC_STS_IN_PROCESS);
        return (List<String>) this.ssmBatchClient.queryObjectList("getConvOrd", param);
    }

    // 1.2.Terminate Contract
    private boolean terminateContr(String cpoOrdNum) {
        List<Map<String, Object>> dsContrDtlList = getDsContrDtlForTrmn(cpoOrdNum);
        // mod start 2017/11/16 QC#22442
        String nextBusinessDate = ZYPDateUtil.addDays(this.salesDate, 1);
        // mod end 2017/11/16 QC#22442
        String closeDate = ZYPDateUtil.addDays(nextBusinessDate, -1);
        BigDecimal preDsContrPk = null;

        for (int i = 0; i < dsContrDtlList.size(); i++) {
            Map<String, Object> dsContrDtl = dsContrDtlList.get(i);
            String contrEffFromDt = (String) dsContrDtl.get("CONTR_EFF_FROM_DT");
            String supprCrFlg = (String) dsContrDtl.get("SUPPR_CR_FLG");
            String dsContrCatgCd = (String) dsContrDtl.get("DS_CONTR_CATG_CD");
            BigDecimal dsContrPk = (BigDecimal) dsContrDtl.get("DS_CONTR_PK");
            BigDecimal dsContrDtlPk = (BigDecimal) dsContrDtl.get("DS_CONTR_DTL_PK");

            if (hasValue(preDsContrPk) && preDsContrPk.compareTo(dsContrPk) != 0) {
                if (!createContrTrk(preDsContrPk)) {
                    return false;
                }
            }
            preDsContrPk = dsContrPk;

            String mode = null;
            if (ZYPDateUtil.compare(contrEffFromDt, closeDate) <= 0) {
                mode = NSZC046001Constant.MODE_TERMINATE;
            } else {
                mode = NSZC046001Constant.MODE_DELETE;
            }

            NSZC046001PMsg pMsg = new NSZC046001PMsg();
            setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(pMsg.xxModeCd, mode);
            setValue(pMsg.slsDt, this.salesDate);
            setValue(pMsg.dsContrSrcTpCd, DS_CONTR_SRC_TP.CPO);
            setValue(pMsg.psnCd, this.userId);
            setValue(pMsg.dsContrPk, dsContrPk);
            setValue(pMsg.manContrOvrdFlg, ZYPConstant.FLG_OFF_N);
            setValue(pMsg.dsContrCatgCd, dsContrCatgCd);

            int idx = 0;
            setValue(pMsg.xxContrDtlList.no(idx).dsContrDtlPk, dsContrDtlPk);
            if (NSZC046001Constant.MODE_TERMINATE.equals(mode)) {
                setValue(pMsg.xxContrDtlList.no(idx).contrCloDt, closeDate);
                setValue(pMsg.xxContrDtlList.no(idx).supprCrFlg, supprCrFlg);
            }
            idx++;

            List<Map<String, Object>> dsContrDtlAccList = getDsContrDtlAccForTrmn(dsContrPk, dsContrDtlPk);
            for (int j = 0; j < dsContrDtlAccList.size(); j++) {
                Map<String, Object> dsContrDtlAcc = dsContrDtlAccList.get(j);
                supprCrFlg = (String) dsContrDtlAcc.get("SUPPR_CR_FLG");
                dsContrDtlPk = (BigDecimal) dsContrDtlAcc.get("DS_CONTR_DTL_PK");

                setValue(pMsg.xxContrDtlList.no(idx).dsContrDtlPk, dsContrDtlPk);
                if (NSZC046001Constant.MODE_TERMINATE.equals(mode)) {
                    setValue(pMsg.xxContrDtlList.no(idx).contrCloDt, closeDate);
                    setValue(pMsg.xxContrDtlList.no(idx).supprCrFlg, supprCrFlg);
                }
                idx++;
            }
            pMsg.xxContrDtlList.setValidCount(idx);

            NSZC046001 api = new NSZC046001();
            api.execute(pMsg, ONBATCH_TYPE.BATCH);

            if (isErrorNSZC046001API(pMsg)) {
                return false;
            }
        }

        if (hasValue(preDsContrPk)) {
            if (!createContrTrk(preDsContrPk)) {
                return false;
            }
        }
        return true;
    }

    private List<Map<String, Object>> getDsContrDtlForTrmn(String cpoOrdNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", cpoOrdNum);
        param.put("inProcess", CONV_PROC_STS_IN_PROCESS);
        // START 2018/01/15 K.Kojima [QC#23255,MOD]
        // String[] dsContrCtrlStsCdList = new String[] {DS_CONTR_CTRL_STS.TERMINATED, DS_CONTR_CTRL_STS.EXPIRED, DS_CONTR_CTRL_STS.CANCELLED, DS_CONTR_CTRL_STS.ORDER };
        // param.put("dsContrCtrlStsCdList", dsContrCtrlStsCdList);
        param.put("flgOnY", ZYPConstant.FLG_ON_Y);
        // END 2018/01/15 K.Kojima [QC#23255,MOD]
        param.put("warranty", DS_CONTR_CATG.WARRANTY);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getDsContrDtlForTrmn", param);
    }

    private List<Map<String, Object>> getDsContrDtlAccForTrmn(BigDecimal dsContrPk, BigDecimal dsContrDtlPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("dsContrDtlPk", dsContrDtlPk);
        // START 2018/01/15 K.Kojima [QC#23255,MOD]
        // String[] dsContrCtrlStsCdList = new String[] {DS_CONTR_CTRL_STS.TERMINATED, DS_CONTR_CTRL_STS.EXPIRED, DS_CONTR_CTRL_STS.CANCELLED, DS_CONTR_CTRL_STS.ORDER };
        // param.put("dsContrCtrlStsCdList", dsContrCtrlStsCdList);
        param.put("flgOnY", ZYPConstant.FLG_ON_Y);
        // END 2018/01/15 K.Kojima [QC#23255,MOD]
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getDsContrDtlAccForTrmn", param);
    }

    // 1.3.1.Get Shell Contract(Daily)
    private List<BigDecimal> getShellContrDaily() {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("order", DS_CONTR_DTL_STS.ORDER);
        param.put("arrived", SHPG_STS.ARRIVED);
        param.put("installed", SHPG_STS.INSTALLED);
        // START 2019/02/28 [QC#30503,ADD]
        param.put("invoiced", SHPG_STS.INVOICED);
        param.put("accessories", DS_CONTR_DTL_TP.ACCESSORIES);
        // END 2019/02/28 [QC#30503,ADD]
        return (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getShellContrDaily", param);
    }

    // 1.3.2.Get Shell Contract Detail(Daily)
    private List<Map<String, Object>> getShellContrDtlDaily(BigDecimal dsContrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("order", DS_CONTR_DTL_STS.ORDER);
        param.put("arrived", SHPG_STS.ARRIVED);
        param.put("installed", SHPG_STS.INSTALLED);
        // START 2019/02/28 [QC#30503,ADD]
        param.put("invoiced", SHPG_STS.INVOICED);
        // END 2019/02/28 [QC#30503,ADD]
        param.put("dsContrPk", dsContrPk);
        param.put("accessories", DS_CONTR_DTL_TP.ACCESSORIES);
        // START 2017/09/22 A.Kohinata [QC#18534,ADD]
        param.put("dsContrDtlTpFlt", DS_CONTR_DTL_TP.FLEET);
        // END 2017/09/22 A.Kohinata [QC#18534,ADD]
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getShellContrDtlDaily", param);
    }

    // 1.3.1.Get Shell Contract(Nightly)
    private List<BigDecimal> getShellContrNightly(String cpoOrdNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("order", DS_CONTR_DTL_STS.ORDER);
        param.put("cpoOrdNum", cpoOrdNum);
        param.put("inProcess", CONV_PROC_STS_IN_PROCESS);
        return (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getShellContrNightly", param);
    }

    // 1.3.2.Get Shell Contract Detail(Nightly)
    private List<Map<String, Object>> getShellContrDtlNightly(String cpoOrdNum, BigDecimal dsContrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("order", DS_CONTR_DTL_STS.ORDER);
        param.put("cpoOrdNum", cpoOrdNum);
        param.put("inProcess", CONV_PROC_STS_IN_PROCESS);
        param.put("accessories", DS_CONTR_DTL_TP.ACCESSORIES);
        // START 2017/09/22 A.Kohinata [QC#18534,ADD]
        param.put("dsContrDtlTpFlt", DS_CONTR_DTL_TP.FLEET);
        // END 2017/09/22 A.Kohinata [QC#18534,ADD]
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getShellContrDtlNightly", param);
    }

    // START 2018/04/20 U.Kim [QC#23378(Sol320), ADD]
    private BigDecimal getRntlPrcAmtForFleetline(String glblCmpyCd, BigDecimal dsContrPk){
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("dsContrDtlTpFlt", DS_CONTR_DTL_TP.FLEET);
        param.put("dsContrDtlTpCdShell", DS_CONTR_DTL_TP.PRICE_OF_MODEL_LEVEL);
        return (BigDecimal) this.ssmBatchClient.queryObject("getRntlPrcAmtForFleetline", param);
    }
    // END 2018/04/20 U.Kim [QC#23378(Sol320), ADD]

    // START 2018/04/20 U.Kim [QC#23378(Sol320), ADD]
    private BigDecimal getBasePrcDealAmtForFleetline(String glblCmpyCd, BigDecimal dsContrPk){
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("dsContrDtlTpFlt", DS_CONTR_DTL_TP.FLEET);
        return (BigDecimal) this.ssmBatchClient.queryObject("getBasePrcDealAmtForFleetline", param);
    }
    // END 2018/04/20 U.Kim [QC#23378(Sol320), ADD]

    // 1.4.-7.Update Shell Contract
    // START 2017/09/22 A.Kohinata [QC#18534,MOD]
    private boolean updateShellContr(BigDecimal dsContrPk, List<Map<String, Object>> shellContrDtlList, String process) {
    // END 2017/09/22 A.Kohinata [QC#18534,MOD]
        DS_CONTRTMsg updDsContrTMsg = new DS_CONTRTMsg();
        List<DS_CONTR_DTLTMsg> updDsContrDtlTMsgList = new ArrayList<DS_CONTR_DTLTMsg>();
        List<DS_CONTR_BLLG_MTRTMsg> updDsContrBllgMtrTMsgList = new ArrayList<DS_CONTR_BLLG_MTRTMsg>();
        DS_CONTR_DTLTMsg updDsContrDtlTMsgFltAgg = new DS_CONTR_DTLTMsg();
        List<DS_CONTR_BLLG_MTRTMsg> updDsContrBllgMtrTMsgFltAggList = new ArrayList<DS_CONTR_BLLG_MTRTMsg>();

        // START 2017/09/22 A.Kohinata [QC#18534,MOD]
        // 1.4.-1.9.Update Contract Detail Info
        if (!updateContrDtlInfo(shellContrDtlList, updDsContrDtlTMsgList, updDsContrBllgMtrTMsgList, process)) {
            return false;
        }
        // END 2017/09/22 A.Kohinata [QC#18534,MOD]

        //START 2017/07/26 K.Kasai [QC#18882,ADD]
        if (!updateContrDtlInfoForNonActiveFleetContr(dsContrPk)) {
            return false;
        }
        //END 2017/07/26 K.Kasai [QC#18882,ADD]

        // 1.10.Update Contract Header/Fleet Line/Aggregate Line
        // mod start 2017/06/07 QC#18850
        if (!updateContrHeadAndFltAggLine(dsContrPk, shellContrDtlList, updDsContrTMsg, updDsContrDtlTMsgFltAgg, updDsContrBllgMtrTMsgFltAggList)) {
            return false;
        }
        // mod end 2017/06/07 QC#18850

        // add start 2017/10/02 QC#21567
        if (!updateDsContrDtlCloDayForAgg(dsContrPk, updDsContrDtlTMsgList, updDsContrBllgMtrTMsgList)) {
            return false;
        }
        // add end 2017/10/02 QC#21567

        // 1.11.Update Term Condition
        // START 2018/02/23 K.Kojima [QC#21685,MOD]
        // updateSvcTermCond(dsContrPk, shellContrDtlList);
        // START 2018/06/13 [QC#26495,MOD]
//        updateSvcTermCond(dsContrPk, shellContrDtlList, updDsContrTMsg.prcSvcContrTpCd.getValue());
        updateSvcTermCond(dsContrPk, shellContrDtlList, updDsContrTMsg.prcSvcContrTpCd.getValue(), updDsContrTMsg);
        // END 2018/06/13 [QC#26495,MOD]
        // END 2018/02/23 K.Kojima [QC#21685,MOD]

        // START 2018/11/13 K.Kitachi [QC#28638, ADD]
        // mod start 2020/03/24 QC#54318
        //if (!updateDsContrRnwEscl(dsContrPk)) {
        if (!updateDsContrRnwEscl(dsContrPk, shellContrDtlList)) {
            return false;
        }
        // mod end 2020/03/24 QC#54318
        // END 2018/11/13 K.Kitachi [QC#28638, ADD]

        // 2.Create Billing Schedule
        // START 2018/04/20 U.Kim [QC#23378(Sol320), MOD]
        // if (!createBllgSchd(updDsContrTMsg, updDsContrDtlTMsgList, updDsContrBllgMtrTMsgList, updDsContrDtlTMsgFltAgg, updDsContrBllgMtrTMsgFltAggList)) {
        if (!createBllgSchd(updDsContrTMsg, updDsContrDtlTMsgList, updDsContrBllgMtrTMsgList, updDsContrDtlTMsgFltAgg, updDsContrBllgMtrTMsgFltAggList, process)) {
        // END 2018/04/20 U.Kim [QC#23378(Sol320), MOD]
            return false;
        }

        // 3.Create Start Meter Read
        if (!createStartMeterRead(shellContrDtlList)) {
            return false;
        }

        // 4.QA Check
        String vldRslt = validContract(dsContrPk);
        if (!hasValue(vldRslt)) {
            return false;
        }

        // 6.Contract Tracking
        if (!createContrTrk(dsContrPk)) {
            return false;
        }

        // 5.Update Status
        if (!updateStatus(dsContrPk, vldRslt)) {
            return false;
        }

        // START 2017/08/28 [QC#20665, ADD]
        // 5.1 Update Source Ref.#
        if (!updateSvcContrRefCmntTxt(dsContrPk, shellContrDtlList, vldRslt)) {
            return false;
        }
        // END   2017/08/28 [QC#20665, ADD]

        // START 2017/09/22 A.Kohinata [QC#18534,ADD]
        if (PROC_NEW.equals(process)) {
            if (!createWaitIstlContr(shellContrDtlList)) {
                return false;
            }
        } else if (PROC_INSTL_COMP.equals(process)) {
            if (!deleteWaitIstlContr(shellContrDtlList)) {
                return false;
            }
        }
        // END 2017/09/22 A.Kohinata [QC#18534,ADD]

        // 6.Contract Tracking
        if (!createContrTrk(dsContrPk)) {
            return false;
        }

        // START 2017/10/24 U.Kim [QC#21865, MOD]
        // START 2017/09/22 A.Kohinata [QC#18534,MOD]
        //if (PROC_NEW.equals(process)) {
        // 7.Create Order Schedule(OM)
        createOrdSch(dsContrPk);
        //}
        // END 2017/09/22 A.Kohinata [QC#18534,MOD]
        // END 2017/10/24 U.Kim [QC#21865, MOD]

        return true;
    }

    // 1.4.-1.8.Update Contract Detail Info
    // START 2017/09/22 A.Kohinata [QC#18534,MOD]
    private boolean updateContrDtlInfo(List<Map<String, Object>> shellContrDtlList, List<DS_CONTR_DTLTMsg> updDsContrDtlTMsgList, List<DS_CONTR_BLLG_MTRTMsg> updDsContrBllgMtrTMsgList, String process) {
    // END 2017/09/22 A.Kohinata [QC#18534,MOD]
        for (int i = 0; i < shellContrDtlList.size(); i++) {
            Map<String, Object> shellContrDtl = shellContrDtlList.get(i);

            // Validate Contract Status
            String dsContrStsCd = (String) shellContrDtl.get("DS_CONTR_STS_CD");
            if (DS_CONTR_STS.EXPIRED.equals(dsContrStsCd) || DS_CONTR_STS.CANCELLED.equals(dsContrStsCd) || DS_CONTR_STS.TERMINATED.equals(dsContrStsCd)) {
                String dsContrStsNm = (String) shellContrDtl.get("DS_CONTR_STS_NM");
                S21InfoLogOutput.println(NSAM0683E, new String[] {"Contract Status", dsContrStsNm });
                continue;
            }

            // 1.4.Update Contract Detail
            // START 2017/09/22 A.Kohinata [QC#18534,MOD]
            DS_CONTR_DTLTMsg dsContrDtlTMsg = null;
            if (PROC_NEW.equals(process)) {
                dsContrDtlTMsg = updateDsContrDtl(shellContrDtl, updDsContrDtlTMsgList);
                if (dsContrDtlTMsg == null) {
                    return false;
                }

                // 1.5.Update Contract Billing Meter
                if (!updateDsContrBllgMtr(dsContrDtlTMsg, updDsContrBllgMtrTMsgList)) {
                    return false;
                }

                // 1.6.Update Contract Physical Billing Meter Relation
                if (!updateContrPhysBllgMtrReln(dsContrDtlTMsg)) {
                    return false;
                }

                // START 2019/01/21 [QC#29782, ADD]
                // 1.7.Copy Contract Detail for Rental Line
                if (!copyContrDtlForRntlLine(shellContrDtl, dsContrDtlTMsg, updDsContrDtlTMsgList)) {
                    return false;
                }
                // END 2019/01/21 [QC#29782, ADD]

                // START 2019/01/29 [QC#29782-1, ADD]
                // 1.8.Copy Contract Detail for Supply Line
                if (!copyContrDtlForSplyLine(shellContrDtl, dsContrDtlTMsg, updDsContrDtlTMsgList)) {
                    return false;
                }
                // END 2019/01/29 [QC#29782-1, ADD]
            } else if (PROC_INSTL_COMP.equals(process)) {
                dsContrDtlTMsg = updateDsContrDtlForInstlComp(shellContrDtl, updDsContrDtlTMsgList);
                if (dsContrDtlTMsg == null) {
                    return false;
                }
                updateDsContrBllgMtrForInstlComp(dsContrDtlTMsg, updDsContrBllgMtrTMsgList);
            }
            // END 2017/09/22 A.Kohinata [QC#18534,MOD]

            // 1.9.Update Contract Additional Charge
            if (!updateDsContrAddlChrg(dsContrDtlTMsg)) {
                return false;
            }
        }
        return true;
    }

    //START 2017/07/26 K.Kasai [QC#18882,ADD]
    private boolean updateContrDtlInfoForNonActiveFleetContr(BigDecimal dsContrPk) {

        // check fleet contract
        if (!checkFltContr(dsContrPk)) {
            return true;
        }
        if (getActStsRcdInDsContrDtlCnt(dsContrPk) > 0) {
            return true;
        }
        // get effective From/Thru Date
        Map<String, Object> getEffDt = getEffDtFromOthContrDtlForFlt(dsContrPk);
        if (getEffDt == null) {
            return true;
        }
        String fromDt = (String) getEffDt.get("MAX_CONTR_EFF_FROM_DT");
        String thruDt = (String) getEffDt.get("MAX_CONTR_EFF_THRU_DT");

        // update from/thru date in Contract Header
        if (!updateFromThruDtInDsContr(dsContrPk, fromDt, thruDt)) {
            return false;
        }

        // update From/Thru date in Contract Detail and Contract Additional Charge
        if (!updateFromThruDtInDsContrDtl(dsContrPk, fromDt, thruDt)) {
            return false;
        }
        return true;
    }

    // check fleet contract
    private boolean checkFltContr(BigDecimal dsContrPk) {
        DS_CONTRTMsg dsContrTMsg = new DS_CONTRTMsg();
        setValue(dsContrTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(dsContrTMsg.dsContrPk, dsContrPk);
        dsContrTMsg = (DS_CONTRTMsg) EZDTBLAccessor.findByKey(dsContrTMsg);
        if (dsContrTMsg == null) {
            S21InfoLogOutput.println(NSAM0045E, new String[] {"DS_CONTR" });
            return false;
        }
        // check fleet contract
        if (!DS_CONTR_CATG.FLEET.equals(dsContrTMsg.dsContrCatgCd.getValue())) {
            return false;
        }
        return true;
    }

    // Update From/Thru date in Contract Header
    private boolean updateFromThruDtInDsContr(BigDecimal dsContrPk, String fromDt, String thruDt) {
        DS_CONTRTMsg dsContrTMsg = new DS_CONTRTMsg();
        setValue(dsContrTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(dsContrTMsg.dsContrPk, dsContrPk);
        dsContrTMsg = (DS_CONTRTMsg) EZDTBLAccessor.findByKeyForUpdateWait(dsContrTMsg);
        if (dsContrTMsg == null) {
            S21InfoLogOutput.println(NSAM0045E, new String[] {"DS_CONTR" });
            return false;
        }
        setValue(dsContrTMsg.contrVrsnEffFromDt, fromDt);
        setValue(dsContrTMsg.contrVrsnEffThruDt, thruDt);
        S21FastTBLAccessor.update(dsContrTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dsContrTMsg.getReturnCode())) {
            S21InfoLogOutput.println(NSAM0031E, new String[] {"DS_CONTR" });
            return false;
        }
        return true;
    }

    // Update From/Thru date in Contract Detail
    private boolean updateFromThruDtInDsContrDtl(BigDecimal dsContrPk, String fromDt, String thruDt) {
        List<Map<String, BigDecimal>> dsContrDtlTMsgList = getDsContrDtlFromDtIsNotNull(dsContrPk);
        DS_CONTR_DTLTMsg dsContrDtlTMsg = new DS_CONTR_DTLTMsg();
        BigDecimal dsContrDtlPk = null;
        for (Map<String, BigDecimal> dsContrDtlPkMap : dsContrDtlTMsgList) {
            dsContrDtlPk = dsContrDtlPkMap.get("DS_CONTR_DTL_PK");
            dsContrDtlTMsg = getDsContrDtlByKeyForUpdate(dsContrDtlPk);
            // update From/Thru date in Contract Detail
            if (!hasValue(dsContrDtlTMsg.contrEffFromDt)) {
                continue;
            }
            setValue(dsContrDtlTMsg.contrEffFromDt, fromDt);
            setValue(dsContrDtlTMsg.contrEffThruDt, thruDt);
            S21FastTBLAccessor.update(dsContrDtlTMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dsContrDtlTMsg.getReturnCode())) {
                S21InfoLogOutput.println(NSAM0031E, new String[] {"DS_CONTR_DTL" });
                return false;
            }

            // Update From/Thru date in Contract Additional Charge
            if (!updateDsContrAddlChrg(dsContrDtlTMsg)) {
                return false;
            }
        }
        return true;
    }
    //END 2017/07/26 K.Kasai [QC#18882,ADD]

    // 1.4.Update Contract Detail
    private DS_CONTR_DTLTMsg updateDsContrDtl(Map<String, Object> shellContrDtl, List<DS_CONTR_DTLTMsg> updDsContrDtlTMsgList) {
        String dsContrStsCd = (String) shellContrDtl.get("DS_CONTR_STS_CD");
        String dsContrCatgCd = (String) shellContrDtl.get("DS_CONTR_CATG_CD");
        String baseDplyPerEndDay = (String) shellContrDtl.get("BASE_DPLY_PER_END_DAY");
        String mtrDplyPerEndDay = (String) shellContrDtl.get("MTR_DPLY_PER_END_DAY");
        BigDecimal dsContrDtlPk = (BigDecimal) shellContrDtl.get("DS_CONTR_DTL_PK");
        BigDecimal svcMachMstrPk = (BigDecimal) shellContrDtl.get("SVC_MACH_MSTR_PK");
        //START 2017/07/26 K.Kasai [QC#18882,ADD]
        BigDecimal dsContrPk = (BigDecimal) shellContrDtl.get("DS_CONTR_PK");
        //END 2017/07/26 K.Kasai [QC#18882,ADD]
        // add start 2017/08/08 QC#18799
        String dsContrSrcTpCd = (String) shellContrDtl.get("DS_CONTR_SRC_TP_CD");
        String dsContrClsCd = (String) shellContrDtl.get("DS_CONTR_CLS_CD");
        String svcLineBizCd = (String) shellContrDtl.get("SVC_LINE_BIZ_CD");
        // add end 2017/08/08 QC#18799

        String contrActMode = null;
        if (DS_CONTR_STS.ORDER.equals(dsContrStsCd)) {
            contrActMode = CONTR_ACT_MODE_NEW;
        } else {
            contrActMode = CONTR_ACT_MODE_ADD;
        }

        // mod start 2017/06/27 QC#18525
        // mod start 2017/08/08 QC#18799
        //DS_CONTR_INTFC_DEF_RULETMsg dsContrIntfcDefRuleTMsg = getDsContrIntfcDefRule();
        DS_CONTR_INTFC_DEF_RULETMsg dsContrIntfcDefRuleTMsg = getDsContrIntfcDefRule(dsContrSrcTpCd, dsContrClsCd, svcLineBizCd);
        // mod end 2017/08/08 QC#18799
        String stubPrepFromTpCd = null;
        String stubPrepThruTpCd = null;
        if (dsContrIntfcDefRuleTMsg != null) {
            stubPrepFromTpCd = dsContrIntfcDefRuleTMsg.stubPrepFromTpCd.getValue();
            stubPrepThruTpCd = dsContrIntfcDefRuleTMsg.stubPrepThruTpCd.getValue();
        }

        // 1.4.1.Get Effective From Date
        //START 2017/07/26 K.Kasai [QC#18882,MOD]
        String effFromDt = getEffFromDt(shellContrDtl, contrActMode, stubPrepFromTpCd, dsContrCatgCd);
        //END 2017/07/26 K.Kasai [QC#18882,MOD]
        if (effFromDt == null) {
            S21InfoLogOutput.println(NSAM0179E, new String[] {"Effective From Date" });
            return null;
        }

        // 1.4.2.Get Effective Thru Date
        //START 2017/07/26 K.Kasai [QC#18882,MOD]
        String effThruDt = getEffThruDt(shellContrDtl, contrActMode, effFromDt, stubPrepThruTpCd, dsContrCatgCd, dsContrPk);
        //END 2017/07/26 K.Kasai [QC#18882,MOD]
        if (effThruDt == null) {
            S21InfoLogOutput.println(NSAM0179E, new String[] {"Effective Thru Date" });
            return null;
        }
        // mod end 2017/06/27 QC#18525

        // 1.4.3.Get Close Day
        String contrCloDay = getCloDay(baseDplyPerEndDay, effFromDt);
        String mtrCloDay = getCloDay(mtrDplyPerEndDay, effFromDt);

        // add start 2017/07/10 QC#19818
        // START 2017/10/18 K.Kitachi [QC#21622, DEL]
//        BigDecimal basePrcTermDealAmtRate = getBasePrcTermDealAmtRate(dsContrDtlPk);
        // END 2017/10/18 K.Kitachi [QC#21622, DEL]
        // add end   2017/07/10 QC#19818

        // 1.4.4.Update DS_CONTR_DTL
        DS_CONTR_DTLTMsg tMsg = getDsContrDtlByKeyForUpdate(dsContrDtlPk);
        if (tMsg == null) {
            S21InfoLogOutput.println(NSAM0045E, new String[] {"DS_CONTR_DTL" });
            return null;
        }

        // START 2018/03/13 K.Kojima [QC#24263,ADD]
        if (DS_CONTR_DTL_STS.ORDER.equals(tMsg.dsContrDtlStsCd.getValue())) {
            setValue(tMsg.ctacPsnPk, getCtacPsnPk(tMsg.cpoOrdNum.getValue(), tMsg.baseBillToCustCd.getValue(), tMsg.ctacPsnPk.getValue()));
        }
        // END 2018/03/13 K.Kojima [QC#24263,ADD]

        setValue(tMsg.dsContrDtlStsCd, DS_CONTR_DTL_STS.SAVED);
        setValue(tMsg.svcMachMstrPk, svcMachMstrPk);
        setValue(tMsg.contrEffFromDt, effFromDt);
        setValue(tMsg.contrEffThruDt, effThruDt);
        if (!DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
            setValue(tMsg.contrCloDay, contrCloDay);
            setValue(tMsg.mtrCloDay, mtrCloDay);
        }
        if (ZYPConstant.FLG_ON_Y.equals(tMsg.billWithEquipFlg.getValue())) {
            setValue(tMsg.baseInvUpToDt, effThruDt);
        }
        // add start 2017/07/10 QC#19818
        // START 2017/10/18 K.Kitachi [QC#21622, MOD]
//        if (!hasValue(tMsg.basePrcTermDealAmtRate)) {
//            setValue(tMsg.basePrcTermDealAmtRate, basePrcTermDealAmtRate);
//        }
        BigDecimal basePrcTermDealAmtRate;
        if (ZYPConstant.FLG_ON_Y.equals(tMsg.billWithEquipFlg.getValue())) {
            if (!hasValue(tMsg.basePrcTermDealAmtRate)) {
                basePrcTermDealAmtRate = getBasePrcTermDealAmtRate(dsContrDtlPk);
                setValue(tMsg.basePrcTermDealAmtRate, basePrcTermDealAmtRate);
            }
        // START 2017/10/24 K.Kitachi [QC#22048, MOD]
//        } else {
        } else if (hasValue(tMsg.baseBllgCycleCd)) {
        // END 2017/10/24 K.Kitachi [QC#22048, MOD]
            // START 2017/10/24 K.Kitachi [QC#21622, MOD]
//            if (isDiffTermPerMth(tMsg)) {
//                int bllgCycleCnt = calcBllgCycleCntFromDuration(tMsg);
//                if (bllgCycleCnt > 0 && hasValue(tMsg.basePrcDealAmt)) {
//                    basePrcTermDealAmtRate = tMsg.basePrcDealAmt.getValue().multiply(BigDecimal.valueOf(bllgCycleCnt));
//                    setValue(tMsg.basePrcTermDealAmtRate, basePrcTermDealAmtRate);
//                }
//            } else {
//                if (!hasValue(tMsg.basePrcTermDealAmtRate)) {
//                    basePrcTermDealAmtRate = getBasePrcTermDealAmtRate(dsContrDtlPk);
//                    setValue(tMsg.basePrcTermDealAmtRate, basePrcTermDealAmtRate);
//                }
//            }
            int bllgCycleCnt = calcBllgCycleCntFromDuration(tMsg);
            if (bllgCycleCnt > 0 && hasValue(tMsg.basePrcDealAmt)) {
                basePrcTermDealAmtRate = tMsg.basePrcDealAmt.getValue().multiply(BigDecimal.valueOf(bllgCycleCnt));
                setValue(tMsg.basePrcTermDealAmtRate, basePrcTermDealAmtRate);
            }
            // END 2017/10/24 K.Kitachi [QC#21622, MOD]
        }
        // END 2017/10/18 K.Kitachi [QC#21622, MOD]
        // add end   2017/07/10 QC#19818

        // START 2018/08/30 M.Naito [QC#27102, ADD]
        DS_CONTRTMsg dsContrTMsg = getDsContrByKey(dsContrPk);
        if (dsContrTMsg != null) {
            String uplftFromDt = NSXC001001GetUplftFromDt.getUplftFromDt(glblCmpyCd, tMsg.contrEffFromDt.getValue(), CONTR_INTFC_SRC_TP.ORDER, dsContrTMsg.dsContrClsCd.getValue(), dsContrTMsg.svcLineBizCd.getValue());
            if (ZYPCommonFunc.hasValue(uplftFromDt)) {
                setValue(tMsg.uplftFromDt, uplftFromDt);
            }
        }
        // END 2018/08/30 M.Naito [QC#27102, ADD]

        // START 2019/01/23 W.Honda [QC#29371, ADD]
        if (DS_CONTR_DTL_TP.ACCESSORIES.equals(tMsg.dsContrDtlTpCd.getValue())
                && ZYPConstant.FLG_OFF_N.equals(dsContrIntfcDefRuleTMsg.printDtlFlg.getValue())) {
            setValue(tMsg.svcInvMergeTpCd, SVC_INV_MERGE_TP.MERGE_INTO_BASE_CHARGE); // Merge
        } else {
            setValue(tMsg.svcInvMergeTpCd, SVC_INV_MERGE_TP.SPLIT_BASE_CHARGE); // Non Merge
        }
        // END 2019/01/23 W.Honda [QC#29371, ADD]
        S21FastTBLAccessor.update(tMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            S21InfoLogOutput.println(NSAM0031E, new String[] {"DS_CONTR_DTL" });
            return null;
        }
        updDsContrDtlTMsgList.add(tMsg);
        return tMsg;
    }

    // 1.4.1.Get Effective From Date
    //START 2017/07/26 K.Kasai [QC#18882,MOD]
    // mod start 2017/06/27 QC#18525
    private String getEffFromDt(Map<String, Object> shellContrDtl, String contrActMode, String stubPrepFromTpCd, String dsContrCatgCd) {
    // mod end 2017/06/27 QC#18525
    //END 2017/07/26 K.Kasai [QC#18882,MOD]
        String effFromDt = null;
        String prntEffFromDt = null;
        String dsContrDtlTpCd = (String) shellContrDtl.get("DS_CONTR_DTL_TP_CD");
        String cpoOrdNum = (String) shellContrDtl.get("CPO_ORD_NUM");
        // START 2017/09/22 A.Kohinata [QC#18534,MOD]
//        String svcPgmMdseCd = (String) shellContrDtl.get("SVC_PGM_MDSE_CD");
        String ovrdMnfWtyFlg = (String) shellContrDtl.get("OVRD_MNF_WTY_FLG");
        // mod start 2017/10/06 QC#21639
//        String fsrCpltDt = (String) shellContrDtl.get("FSR_CPLT_DT");
        String fsrCpltDt = (String) shellContrDtl.get("ISTL_DT");
        // mod end 2017/10/06 QC#21639
        // END 2017/09/22 A.Kohinata [QC#18534,MOD]
        BigDecimal svcMachMstrPk = (BigDecimal) shellContrDtl.get("SVC_MACH_MSTR_PK");
        BigDecimal fromPerMthNum = (BigDecimal) shellContrDtl.get("FROM_PER_MTH_NUM");
        BigDecimal dsContrPk = (BigDecimal) shellContrDtl.get("DS_CONTR_PK");
        BigDecimal prntDsContrDtlPk = (BigDecimal) shellContrDtl.get("PRNT_DS_CONTR_DTL_PK");
        //add start 2017/08/20 QC#20576
        String contrVrsnEffFromDt = (String) shellContrDtl.get("CONTR_VRSN_EFF_FROM_DT");
        //add end 2017/08/20 QC#20576
        // add start 2017/06/27 QC#18525
        boolean existsWty = false;
        // add end 2017/06/27 QC#18525

        if (DS_CONTR_DTL_TP.ACCESSORIES.equals(dsContrDtlTpCd) && hasValue(prntDsContrDtlPk)) {
            DS_CONTR_DTLTMsg prntDsContrDtlTMsg = getDsContrDtlByKey(prntDsContrDtlPk);
            if (prntDsContrDtlTMsg != null) {
                prntEffFromDt = prntDsContrDtlTMsg.contrEffFromDt.getValue();
            }
        }

        // mod start 2017/06/27 QC#18525
        // New Contract / Daily Mode
        if (CONTR_ACT_MODE_NEW.equals(contrActMode) && BATCH_MODE_DAILY.equals(this.usrVar1)) {
            Map<String, Object> startDt = getStartDtForNewContr(cpoOrdNum);
            if (startDt == null) {
                return null;
            }
            String startDtByOrd = addFromPerMthNum((String) startDt.get("START_DT_BY_ORD"), fromPerMthNum);
            String startDtByBook = addFromPerMthNum((String) startDt.get("START_DT_BY_BOOK"), fromPerMthNum);
            String dsOrdCatgCd = (String) startDt.get("DS_ORD_CATG_CD");
            // START 2019/06/17 W.Honda [QC#50842,ADD]
            String dsOrdTpCd = (String) startDt.get("DS_ORD_TP_CD");
            // END 2019/06/17 W.Honda [QC#50842,ADD]
            effFromDt = startDtByOrd;

            // START 2017/09/22 A.Kohinata [QC#18534,ADD]
            if (hasValue(fsrCpltDt)) {
                effFromDt = fsrCpltDt;
                if (hasValue(fromPerMthNum) && BigDecimal.ONE.compareTo(fromPerMthNum) < 0) {
                    effFromDt = addFromPerMthNum(fsrCpltDt, fromPerMthNum);
                }
            }
            // END 2017/09/22 A.Kohinata [QC#18534,ADD]

            // START 2019/06/17 W.Honda [QC#50842,MOD]
//            if (isLoanOrd(dsOrdCatgCd)) {
            if (isLoanOrd(dsOrdCatgCd, dsOrdTpCd)) {
            // END 2019/06/17 W.Honda [QC#50842,MOD]
                if (ZYPDateUtil.compare(startDtByOrd, startDtByBook) < 0) {
                    effFromDt = startDtByBook;
                }
            // START 2017/09/22 A.Kohinata [QC#18534,MOD]
//            } else if (isSplyExcl(svcPgmMdseCd)) {
            } else if (!isWtyOverlap(ovrdMnfWtyFlg)) {
            // END 2017/09/22 A.Kohinata [QC#18534,MOD]
                String effThruDtByWty = getEffThruDtByWtyContr(svcMachMstrPk);
                if (hasValue(effThruDtByWty)) {
                    String effThruDtByWtyAddOneDay = ZYPDateUtil.addDays(effThruDtByWty, 1);
                    effFromDt = addFromPerMthNum(effThruDtByWtyAddOneDay, fromPerMthNum);
                    existsWty = true;
                } else {
                    effThruDtByWty = getEffThruDtByWtyIntfc(svcMachMstrPk);
                    if (hasValue(effThruDtByWty)) {
                        String effThruDtByWtyAddOneDay = ZYPDateUtil.addDays(effThruDtByWty, 1);
                        effFromDt = addFromPerMthNum(effThruDtByWtyAddOneDay, fromPerMthNum);
                        existsWty = true;
                    } else {
                        // START 2017/07/13 K.Kojima [QC#19908,MOD]
                        // effFromDt = startDtByBook;
                        // START 2017/09/22 A.Kohinata [QC#18534,MOD]
                        if (!hasValue(fsrCpltDt) && ZYPDateUtil.compare(startDtByOrd, startDtByBook) < 0) {
                            effFromDt = startDtByBook;
                        }
                        // END 2017/09/22 A.Kohinata [QC#18534,MOD]
                        // END 2017/07/13 K.Kojima [QC#19908,MOD]
                    }
                }
            }

            if (!existsWty && STUB_PREP_FROM_TP.EXTEND_START_MONTH.equals(stubPrepFromTpCd)) {
                effFromDt = getFirstMonth(effFromDt);
            }

            // START 2017/09/22 A.Kohinata [QC#18534,MOD]
            if (!hasValue(fsrCpltDt)) {
                // START 2017/06/17 K.Kitachi [QC#18177, ADD]
                String maxContrEffThruDt = getMaxContrEffThruDt(cpoOrdNum, svcMachMstrPk);
                effFromDt = getContrEffFromDt(effFromDt, maxContrEffThruDt);
                // END 2017/06/17 K.Kitachi [QC#18177, ADD]
            }
            // END 2017/09/22 A.Kohinata [QC#18534,MOD]
        }
        // mod end 2017/06/27 QC#18525

        // New Contract / Nightly Mode
        if (CONTR_ACT_MODE_NEW.equals(contrActMode) && BATCH_MODE_NIGHTLY.equals(this.usrVar1)) {
            // mod start 2017/11/16 QC#22442
            String nextBusinessDate = ZYPDateUtil.addDays(this.salesDate, 1);
            // mod end 2017/11/16 QC#22442
            effFromDt = addFromPerMthNum(nextBusinessDate, fromPerMthNum);

            // START 2017/09/22 A.Kohinata [QC#18534,MOD]
//          if (isSplyExcl(svcPgmMdseCd)) {
            if (!isWtyOverlap(ovrdMnfWtyFlg)) {
            // END 2017/09/22 A.Kohinata [QC#18534,MOD]
                String effThruDtByWty = getEffThruDtByWtyContr(svcMachMstrPk);
                if (hasValue(effThruDtByWty)) {
                    String effThruDtByWtyAddOneDay = ZYPDateUtil.addDays(effThruDtByWty, 1);
                    effFromDt = addFromPerMthNum(effThruDtByWtyAddOneDay, fromPerMthNum);
                }
            }
            // START 2017/06/17 K.Kitachi [QC#18177, ADD]
            String maxContrEffThruDt = getMaxContrEffThruDt(cpoOrdNum, svcMachMstrPk);
            effFromDt = getContrEffFromDt(effFromDt, maxContrEffThruDt);
            // END 2017/06/17 K.Kitachi [QC#18177, ADD]
        }

        // Add Contract
        if (CONTR_ACT_MODE_ADD.equals(contrActMode)) {
            //START 2017/07/26 K.Kasai [QC#18882,MOD]
//            String startDtByOthDtl = getStartDtForAddContr(dsContrPk);
            String startDtByOthDtl = null;
            if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd) && getActStsRcdInDsContrDtlCnt(dsContrPk) == 0) {
                startDtByOthDtl = getStartDtForAddContrForFlt(cpoOrdNum);
            } else {
                //START 2017/08/30 QC#20576
                //startDtByOthDtl = getStartDtForAddContr(dsContrPk);
                Map<String, Object> startDt = getStartDtForNewContr(cpoOrdNum);
                if (startDt == null) {
                    return null;
                }
                String startDtByOrd = (String) startDt.get("START_DT_BY_ORD");
                if (ZYPDateUtil.compare(startDtByOrd, contrVrsnEffFromDt) < 0) {
                    startDtByOthDtl = contrVrsnEffFromDt;
                } else {
                    startDtByOthDtl = startDtByOrd;
                }
                //END 2017/08/30 QC#20576
            }
            //END 2017/07/26 K.Kasai [QC#18882,MOD]
            effFromDt = startDtByOthDtl;
            // START 2017/09/22 A.Kohinata [QC#18534,ADD]
            if (hasValue(fsrCpltDt)) {
                effFromDt = fsrCpltDt;
                if (hasValue(fromPerMthNum) && BigDecimal.ONE.compareTo(fromPerMthNum) < 0) {
                    effFromDt = addFromPerMthNum(fsrCpltDt, fromPerMthNum);
                }
            }
            // END 2017/09/22 A.Kohinata [QC#18534,ADD]

            // START 2017/09/22 A.Kohinata [QC#18534,MOD]
//          if (isSplyExcl(svcPgmMdseCd)) {
            if (!isWtyOverlap(ovrdMnfWtyFlg)) {
            // END 2017/09/22 A.Kohinata [QC#18534,MOD]
                String effThruDtByWty = getEffThruDtByWtyContr(svcMachMstrPk);
                if (hasValue(effThruDtByWty)) {
                    String effThruDtByWtyAddOneDay = ZYPDateUtil.addDays(effThruDtByWty, 1);
                    if (hasValue(effThruDtByWtyAddOneDay) && ZYPDateUtil.compare(startDtByOthDtl, effThruDtByWtyAddOneDay) < 0) {
                        effFromDt = addFromPerMthNum(effThruDtByWtyAddOneDay, fromPerMthNum);
                    }
                }
            }
            // mod start 2017/10/02 QC#21567
            if (!hasValue(fsrCpltDt)) {
                //START 2017/07/26 K.Kasai [QC#18882,ADD]
                String maxContrEffThruDt = getMaxContrEffThruDt(cpoOrdNum, svcMachMstrPk);
                effFromDt = getContrEffFromDt(effFromDt, maxContrEffThruDt);
                //END 2017/07/26 K.Kasai [QC#18882,ADD]
            }
            // mod end 2017/10/02 QC#21567
        }
        return comparePrntEffFromDt(effFromDt, prntEffFromDt);
    }

    private Map<String, Object> getStartDtForNewContr(String cpoOrdNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", cpoOrdNum);
        param.put("salesDate", this.salesDate);
        param.put("dtLen", INT_8);
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getStartDtForNewContr", param);
    }

    private String getStartDtForAddContr(BigDecimal dsContrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        return (String) this.ssmBatchClient.queryObject("getStartDtForAddContr", param);
    }

    //START 2017/07/26 K.Kasai [QC#18882,ADD]
    private String getStartDtForAddContrForFlt(String cpoOrdNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", cpoOrdNum);
        return (String) this.ssmBatchClient.queryObject("getStartDtForAddContrForFlt", param);
    }

    private Map<String, Object> getEffDtFromOthContrDtlForFlt(BigDecimal dsContrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getEffDtFromOthContrDtlForFlt", param);
    }

    private boolean isErrContrVldErrorWithoutAllArvMachChk(BigDecimal dsContrPk, BigDecimal dsContrVldPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("dsContrVldPk", dsContrVldPk);
        param.put("dsContrVldStsCd", DS_CONTR_VLD_STS.SUCCESS);
        BigDecimal count = (BigDecimal) this.ssmBatchClient.queryObject("getContrVldErrorWithoutAllArvMachChk", param);
        if (count.compareTo(BigDecimal.ZERO) > 0) {
            return true;
        }
        return false;
    }
    //END 2017/07/26 K.Kasai [QC#18882,ADD]
    // START 2017/09/22 A.Kohinata [QC#18534,ADD]
//    private boolean isErrContrVldErrorWithoutMachInstlChk(BigDecimal dsContrPk, BigDecimal dsContrDtlPk, BigDecimal dsContrVldPk) {
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("glblCmpyCd", this.glblCmpyCd);
//        param.put("dsContrPk", dsContrPk);
//        param.put("dsContrDtlPk", dsContrDtlPk);
//        param.put("dsContrVldPk", dsContrVldPk);
//        param.put("dsContrVldStsCd", DS_CONTR_VLD_STS.SUCCESS);
//        BigDecimal count = (BigDecimal) this.ssmBatchClient.queryObject("countContrVldErrorWithoutMachInstlChk", param);
//        if (count.compareTo(BigDecimal.ZERO) > 0) {
//            return true;
//        }
//        return false;
//    }
    // END 2017/09/22 A.Kohinata [QC#18534,ADD]

    // START 2019/06/17 W.Honda [QC#50842,MOD]
//    private boolean isLoanOrd(String dsOrdCatgCd) {
    private boolean isLoanOrd(String dsOrdCatgCd, String dsOrdTpCd) {
    // END 2019/06/17 W.Honda [QC#50842,MOD]
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsOrdCatgCd", dsOrdCatgCd);
        // START 2019/06/17 W.Honda [QC#50842,ADD]
        param.put("dsOrdTpCd", dsOrdTpCd);
        // END 2019/06/17 W.Honda [QC#50842,ADD]
        param.put("ordCatgCtxTpCd", ORD_CATG_CTX_TP.LOAN_ORDER_VALUE_SET);
        BigDecimal count = (BigDecimal) this.ssmBatchClient.queryObject("countOrdCatgBizCtx", param);
        if (count.compareTo(BigDecimal.ZERO) > 0) {
            return true;
        }
        return false;
    }

    // START 2017/09/22 A.Kohinata [QC#18534,DEL]
//    private boolean isSplyExcl(String svcPgmMdseCd) {
//        if (!hasValue(svcPgmMdseCd)) {
//            return false;
//        }
//        CovTmplInfo covTmplInfo = new CovTmplInfo();
//        covTmplInfo.setGlblCmpyCd(this.glblCmpyCd);
//        covTmplInfo.setSvcPgmMdseCd(svcPgmMdseCd);
//        covTmplInfo.setSlsDt(this.salesDate);
//        NSXC001001GetCovTmpl nsxc001001GetCovTmpl = new NSXC001001GetCovTmpl();
//        nsxc001001GetCovTmpl.getCovTmpl(covTmplInfo);
//        List<OutputCovTmplInfo> outList = covTmplInfo.getOutputCovTmplInfoList();
//        if (outList == null) {
//            return false;
//        }
//        for (OutputCovTmplInfo out : outList) {
//            if (SVC_COV_FEAT.SPLY_INCL.equals(out.getSvcCovFeatCd()) && ZYPConstant.FLG_OFF_N.equals(out.getSvcCovDtlValTxt())) {
//                return true;
//            }
//        }
//        return false;
//    }
    // END 2017/09/22 A.Kohinata [QC#18534,DEL]
    // START 2017/09/22 A.Kohinata [QC#18534,ADD]
    private boolean isWtyOverlap(String ovrdMnfWtyFlg) {
        if (hasValue(ovrdMnfWtyFlg) && ZYPConstant.FLG_ON_Y.equals(ovrdMnfWtyFlg)) {
            return true;
        }
        return false;
    }
    // END 2017/09/22 A.Kohinata [QC#18534,ADD]

    private String getEffThruDtByWtyContr(BigDecimal svcMachMstrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("svcMachMstrPk", svcMachMstrPk);
        param.put("warranty", DS_CONTR_CATG.WARRANTY);
        String[] dsContrStsCdList = new String[] {DS_CONTR_STS.EXPIRED, DS_CONTR_STS.CANCELLED, DS_CONTR_STS.TERMINATED };
        param.put("dsContrStsCdList", dsContrStsCdList);
        String[] dsContrDtlStsCdList = new String[] {DS_CONTR_DTL_STS.EXPIRED, DS_CONTR_DTL_STS.CANCELLED, DS_CONTR_DTL_STS.TERMINATED };
        param.put("dsContrDtlStsCdList", dsContrDtlStsCdList);
        return (String) this.ssmBatchClient.queryObject("getEffThruDtByWtyContr", param);
    }

    private String getEffThruDtByWtyIntfc(BigDecimal svcMachMstrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("svcMachMstrPk", svcMachMstrPk);
        param.put("warranty", DS_CONTR_CATG.WARRANTY);
        param.put("success", DS_CONTR_INTFC_STS.SUCCESS);
        return (String) this.ssmBatchClient.queryObject("getEffThruDtByWtyIntfc", param);
    }

    private String addFromPerMthNum(String targetDate, BigDecimal fromPerMthNum) {
        if (!hasValue(targetDate) || !hasValue(fromPerMthNum) || fromPerMthNum.intValue() <= 1) {
            return targetDate;
        }
        int calFromPerMthNum = fromPerMthNum.intValue() - 1;
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("targetDate", targetDate);
        param.put("addMth", calFromPerMthNum);
        param.put("dateFormat", DATE_FORMAT);
        return (String) this.ssmBatchClient.queryObject("addMonths", param);
    }

    private String comparePrntEffFromDt(String effFromDt, String prntEffFromDt) {
        if (!hasValue(prntEffFromDt)) {
            return effFromDt;
        }
        if (ZYPDateUtil.compare(effFromDt, prntEffFromDt) < 0) {
            return prntEffFromDt;
        }
        return effFromDt;
    }

    // 1.4.2.Get Effective Thru Date
    //START 2017/07/26 K.Kasai [QC#18882,MOD]
    // mod start 2017/06/27 QC#18525
    private String getEffThruDt(Map<String, Object> shellContrDtl, String contrActMode, String effFromDt, String stubPrepThruTpCd, String dsContrCatgCd, BigDecimal dsContrPk) {
    // mod end 2017/06/27 QC#18525
    //END 2017/07/26 K.Kasai [QC#18882,MOD]
        String effThruDt = null;
        String dsContrDtlTpCd = (String) shellContrDtl.get("DS_CONTR_DTL_TP_CD");
        String contrVrsnEffThruDt = (String) shellContrDtl.get("CONTR_VRSN_EFF_THRU_DT");
        BigDecimal fromPerMthNum = (BigDecimal) shellContrDtl.get("FROM_PER_MTH_NUM");
        BigDecimal toPerMthNum = (BigDecimal) shellContrDtl.get("TO_PER_MTH_NUM");
        BigDecimal prntDsContrDtlPk = (BigDecimal) shellContrDtl.get("PRNT_DS_CONTR_DTL_PK");

        if (DS_CONTR_DTL_TP.ACCESSORIES.equals(dsContrDtlTpCd) && hasValue(prntDsContrDtlPk)) {
            DS_CONTR_DTLTMsg prntDsContrDtlTMsg = getDsContrDtlByKey(prntDsContrDtlPk);
            if (prntDsContrDtlTMsg != null && hasValue(prntDsContrDtlTMsg.contrEffThruDt)) {
                return prntDsContrDtlTMsg.contrEffThruDt.getValue();
            }
        }

        if (hasValue(fromPerMthNum) && hasValue(toPerMthNum)) {
            int termMth = toPerMthNum.intValue() - fromPerMthNum.intValue() + 1;
            String effFromDtAddTermMth = addTermMth(effFromDt, termMth);
            if (hasValue(effFromDtAddTermMth)) {
                effThruDt = ZYPDateUtil.addDays(effFromDtAddTermMth, -1);
            }
            // mod start 2017/06/27 QC#18525
            // START 2018/05/09 K.Kitachi [QC#25728, MOD]
//            if (STUB_PREP_THRU_TP.EXTEND_END_MONTH.equals(stubPrepThruTpCd)) {
            if (isChangeThruDateToEndMonth(shellContrDtl, stubPrepThruTpCd, fromPerMthNum)) {
            // END 2018/05/09 K.Kitachi [QC#25728, MOD]
                effThruDt = getEndMonth(effThruDt);
            }
            // mod end 2017/06/27 QC#18525
        }

        //START 2017/08/29 QC#20576 Mod
        //START 2017/07/26 K.Kasai [QC#18882,MOD]
        // if add contract mode, contract detail thru date must not be over contract header thru date.
        // if in the case of non active fleet contract, skip this process.
        //if (CONTR_ACT_MODE_ADD.equals(contrActMode) && !(DS_CONTR_CATG.FLEET.equals(dsContrCatgCd) && getActStsRcdInDsContrDtlCnt(dsContrPk) == 0)) {
        if (CONTR_ACT_MODE_ADD.equals(contrActMode) && DS_CONTR_CATG.FLEET.equals(dsContrCatgCd) && getActStsRcdInDsContrDtlCnt(dsContrPk) > 0) {
        //END 2017/07/26 K.Kasai [QC#18882,MOD]
        //END 2017/08/29 QC#20576 Mod
            if (ZYPDateUtil.compare(contrVrsnEffThruDt, effThruDt) < 0) {
                effThruDt = contrVrsnEffThruDt;
            }
        }
        return effThruDt;
    }

    private String addTermMth(String targetDate, int termMth) {
        if (termMth <= 0) {
            return targetDate;
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("targetDate", targetDate);
        param.put("addMth", termMth);
        param.put("dateFormat", DATE_FORMAT);
        return (String) this.ssmBatchClient.queryObject("addMonths", param);
    }

    // 1.4.3.Get Close Day
    private String getCloDay(String dplyPerEndDay, String effFromDt) {
        String cloDay = null;
        if (INIT_DAY.equals(dplyPerEndDay)) {
            int fromDt = 0;
            if (hasValue(effFromDt)) {
                fromDt = Integer.parseInt(effFromDt.substring(INT_6));
            }
            if (fromDt >= INT_2 && fromDt <= INT_28) {
                fromDt--;
                cloDay = new Integer(fromDt).toString();
            } else {
                cloDay = LAST_DAY_OF_A_MONTH;
            }
        } else {
            cloDay = dplyPerEndDay;
        }
        return cloDay;
    }

    // 1.5.Update Contract Billing Meter
    private boolean updateDsContrBllgMtr(DS_CONTR_DTLTMsg dsContrDtlTMsg, List<DS_CONTR_BLLG_MTRTMsg> upDsContrBllgMtrTMsgList) {
        BigDecimal dsContrDtlPk = dsContrDtlTMsg.dsContrDtlPk.getValue();

        DS_CONTR_BLLG_MTRTMsgArray tMsgArray = getDsContrBllgMtrByDsContrDtlPkForUpdate(dsContrDtlPk);
        for (int i = 0; i < tMsgArray.getValidCount(); i++) {
            DS_CONTR_BLLG_MTRTMsg tMsg = (DS_CONTR_BLLG_MTRTMsg) tMsgArray.get(i);
            setValue(tMsg.dsContrBllgMtrStsCd, DS_CONTR_DTL_STS.SAVED);
            // START 2018/03/13 K.Kojima [QC#24263,ADD]
            if (ZYPCommonFunc.hasValue(dsContrDtlTMsg.cpoOrdNum)) {
                setValue(tMsg.ctacPsnPk, getCtacPsnPk(dsContrDtlTMsg.cpoOrdNum.getValue(), tMsg.bllgMtrBillToCustCd.getValue(), tMsg.ctacPsnPk.getValue()));
            }
            // END 2018/03/13 K.Kojima [QC#24263,ADD]

            S21FastTBLAccessor.update(tMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                S21InfoLogOutput.println(NSAM0031E, new String[] {"DS_CONTR_BLLG_MTR" });
                return false;
            }
            upDsContrBllgMtrTMsgList.add(tMsg);
        }
        return true;
    }

    // 1.6.Update Contract Physical Billing Meter Relation
    private boolean updateContrPhysBllgMtrReln(DS_CONTR_DTLTMsg dsContrDtlTMsg) {
        BigDecimal dsContrDtlPk = dsContrDtlTMsg.dsContrDtlPk.getValue();
        BigDecimal svcMachMstrPk = dsContrDtlTMsg.svcMachMstrPk.getValue();

        CONTR_PHYS_BLLG_MTR_RELNTMsgArray tMsgArray = getContrPhysBllgMtrRelnByDsContrDtlPkForUpdate(dsContrDtlPk);
        for (int i = 0; i < tMsgArray.getValidCount(); i++) {
            CONTR_PHYS_BLLG_MTR_RELNTMsg tMsg = (CONTR_PHYS_BLLG_MTR_RELNTMsg) tMsgArray.get(i);
            BigDecimal svcPhysMtrPk = getSvcPhysMtrPk(svcMachMstrPk, tMsg.regMtrLbCd.getValue());
            setValue(tMsg.machMstrPk, svcMachMstrPk);
            setValue(tMsg.svcPhysMtrPk, svcPhysMtrPk);

            S21FastTBLAccessor.update(tMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                S21InfoLogOutput.println(NSAM0031E, new String[] {"CONTR_PHYS_BLLG_MTR_RELN" });
                return false;
            }
        }
        return true;
    }

    private BigDecimal getSvcPhysMtrPk(BigDecimal svcMachMstrPk, String regMtrLbCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("svcMachMstrPk", svcMachMstrPk);
        param.put("mtrLbCd", regMtrLbCd);
        return (BigDecimal) this.ssmBatchClient.queryObject("getSvcPhysMtrPk", param);
    }

    // START 2019/01/21 [QC#29782, ADD]
    // 1.7.Copy Contract Detail for Rental Line
    private boolean copyContrDtlForRntlLine(Map<String, Object> shellContrDtl, DS_CONTR_DTLTMsg dsContrDtlTMsg, List<DS_CONTR_DTLTMsg> updDsContrDtlTMsgList) {
        // 1.7.1 get Service Program Mdse for Rental Line
        BigDecimal dsContrPk = (BigDecimal) shellContrDtl.get("DS_CONTR_PK");
        DS_CONTRTMsg dsContrTMsg = getDsContrByKey(dsContrPk);
        if (dsContrTMsg == null) {
            return true;
        }

        String cpoOrdNum = dsContrDtlTMsg.cpoOrdNum.getValue();
        String cpoDtlLineNum = dsContrDtlTMsg.cpoDtlLineNum.getValue();
        String cpoDtlLineSubNum = dsContrDtlTMsg.cpoDtlLineSubNum.getValue();
        SVC_MACH_MSTRTMsgArray svcMachMstrTMsgArray = getSvcMachMstr(glblCmpyCd, cpoOrdNum, cpoDtlLineNum, cpoDtlLineSubNum);
        if (svcMachMstrTMsgArray == null || svcMachMstrTMsgArray.getValidCount() == 0) {
            return true;
        }
        SVC_MACH_MSTRTMsg svcMachMstrTMsg = svcMachMstrTMsgArray.no(0);

        // START 2019/02/05 [QC#30244, ADD]
        BigDecimal svcConfigMstrPk = svcMachMstrTMsg.svcConfigMstrPk.getValue();
        BigDecimal sumRntlPrcAmt = getSumRntlPrcAmt(glblCmpyCd, dsContrPk, svcConfigMstrPk);
        if (sumRntlPrcAmt.compareTo(BigDecimal.ZERO) == 0) {
            return true;
        }
        // END 2019/02/05 [QC#30244, ADD]

        String svcLineBizCd = dsContrTMsg.svcLineBizCd.getValue();
        String svcPgmMdseCd = dsContrDtlTMsg.svcPgmMdseCd.getValue();
        BigDecimal dsContrDtlPk = dsContrDtlTMsg.dsContrDtlPk.getValue();
        String mdseCd = svcMachMstrTMsg.mdseCd.getValue();
        String svcMachTpCd = svcMachMstrTMsg.svcMachTpCd.getValue();
        BigDecimal prntDsContrDtlPk = dsContrDtlTMsg.prntDsContrDtlPk.getValue();
        String rntlSpltSvcPgmMdseCd = null;
        String dsContrDtlTpCd = null;
        if (SVC_MACH_TP.ACCESSORY.equals(svcMachTpCd)) {
            String prntSvcPgmMdseCd = getPrntSvcPgmMdseCd(glblCmpyCd, dsContrDtlTMsg.prntDsContrDtlPk.getValue());
            rntlSpltSvcPgmMdseCd = getRntlSpltSvcPgmMdseCd(glblCmpyCd, svcLineBizCd, mdseCd, prntSvcPgmMdseCd, svcMachTpCd);
            dsContrDtlTpCd = DS_CONTR_DTL_TP.ACCESSORIES;
            BigDecimal rntlSpltPrntDsContrDtlPk = getPrntDsContrDtlPk(glblCmpyCd, dsContrPk, svcConfigMstrPk);
            if (hasValue(rntlSpltPrntDsContrDtlPk)) {
                prntDsContrDtlPk = rntlSpltPrntDsContrDtlPk;
            }
        } else {
            rntlSpltSvcPgmMdseCd = getRntlSpltSvcPgmMdseCd(glblCmpyCd, svcLineBizCd, mdseCd, svcPgmMdseCd, svcMachTpCd);
            dsContrDtlTpCd = DS_CONTR_DTL_TP.BASE_ONLY;
        }
        if (!hasValue(rntlSpltSvcPgmMdseCd)) {
            return true;
        }

        // 1.7.2 Insert DS Contract Detail for Rental Line
        BigDecimal dsContrDtlPkRntl = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_DTL_SQ);
        if (!insertContrDtlForRntlLine(dsContrDtlTMsg, dsContrDtlPkRntl, dsContrDtlTpCd, prntDsContrDtlPk, rntlSpltSvcPgmMdseCd, updDsContrDtlTMsgList)) {
            return false;
        }

        // 1.7.3 Insert DS Contract Renewal Escalation for Rental Line
        if (!insertContrRnwEsclForRntlLine(dsContrPk, dsContrDtlPk, dsContrDtlPkRntl)) {
            return false;
        }

        // 1.7.4 Insert DS Contract CR_CARD_PO for Rental Line
        DS_CONTR_CR_CARD_POTMsgArray dsContrCrCardPoTMsgArray = getContrCrCardPo(glblCmpyCd, dsContrDtlPk);
        if (!insertContrCrCardPoForRntlLine(dsContrCrCardPoTMsgArray, dsContrDtlPkRntl)) {
            return false;
        }

        // 1.7.5 Update DS Contract Detail by Base Price
        if (!updateContrDtlByBasePrc(dsContrDtlTMsg)) {
            return false;
        }

        return true;
    }

    private SVC_MACH_MSTRTMsgArray getSvcMachMstr(String glblCmpyCd, String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {
        SVC_MACH_MSTRTMsg tMsg = new SVC_MACH_MSTRTMsg();
        tMsg.setSQLID("021");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("cpoOrdNum01", cpoOrdNum);
        tMsg.setConditionValue("cpoDtlLineNum01", cpoDtlLineNum);
        tMsg.setConditionValue("cpoDtlLineSubNum01", cpoDtlLineSubNum);
        return (SVC_MACH_MSTRTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
    }

    // START 2019/02/05 [QC#30244, ADD]
    private BigDecimal getSumRntlPrcAmt(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal svcConfigMstrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);
        ssmPrm.put("svcConfigMstrPk", svcConfigMstrPk);
        return (BigDecimal) this.ssmBatchClient.queryObject("getSumRntlPrcAmt", ssmPrm);
    }
    // END 2019/02/05 [QC#30244, ADD]

    private String getPrntSvcPgmMdseCd(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        return (String) this.ssmBatchClient.queryObject("getPrntSvcPgmMdseCd", ssmPrm);
    }

    private BigDecimal getPrntDsContrDtlPk(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal svcConfigMstrPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);
        ssmPrm.put("dsContrCratTpCd", DS_CONTR_CRAT_TP.OTHER);
        ssmPrm.put("svcConfigMstrPk", svcConfigMstrPk);
        ssmPrm.put("svcMachTpCd", SVC_MACH_TP.MACHINE);
        return (BigDecimal) this.ssmBatchClient.queryObject("getPrntDsContrDtlPk", ssmPrm);
    }

    private String getRntlSpltSvcPgmMdseCd(String glblCmpyCd, String svcLineBizCd, String mdseCd, String prntSvcPgmMdseCd, String svcMachTpCd) {
        NSXC001001DefaultSvcPgmGetter nsxc001001DefaultSvcPgmGetter = new NSXC001001DefaultSvcPgmGetter();
        String svcPgmMdseCd = nsxc001001DefaultSvcPgmGetter.getRentalSplitSvcPgmMdse(glblCmpyCd, svcLineBizCd, mdseCd, FLG_ON_Y, prntSvcPgmMdseCd, svcMachTpCd);
        return svcPgmMdseCd;
    }

    private Map<String, Object> getDsContrRnwEscl(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrPk", dsContrPk);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        ssmPrm.put("dsContrMachLvl1", DS_CONTR_MACH_LVL_NUM_1);
        ssmPrm.put("dsContrMachLvl2", DS_CONTR_MACH_LVL_NUM_2);
        ssmPrm.put("dsContrMachLvl3", DS_CONTR_MACH_LVL_NUM_3);
        ssmPrm.put("dsContrBaseUsgNmBase", DS_CONTR_BASE_USG_NM_B);
        ssmPrm.put("ROW_NUM", ROW_NUM1);
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getDsContrRnwEscl", ssmPrm);
    }

    private boolean insertContrDtlForRntlLine(DS_CONTR_DTLTMsg dsContrDtlTMsg, BigDecimal dsContrDtlPkRntl, String dsContrDtlTpCd, BigDecimal prntDsContrDtlPk, String rntlSpltSvcPgmMdseCd, List<DS_CONTR_DTLTMsg> updDsContrDtlTMsgList) {
        DS_CONTR_DTLTMsg tMsg = new DS_CONTR_DTLTMsg();
        EZDMsg.copy(dsContrDtlTMsg, null, tMsg, null);

        setValue(tMsg.dsContrDtlPk, dsContrDtlPkRntl);
        setValue(tMsg.dsContrDtlTpCd, dsContrDtlTpCd);
        setValue(tMsg.prntDsContrDtlPk, prntDsContrDtlPk);
        setValue(tMsg.basePrcFuncAmt, NSXC003001Exchange.calcFuncFromDeal(glblCmpyCd, getCcyCd(), salesDate, tMsg.basePrcDealAmt.getValue()));
        setValue(tMsg.svcPgmMdseCd, rntlSpltSvcPgmMdseCd);
        setValue(tMsg.dsContrCratTpCd, DS_CONTR_CRAT_TP.OTHER);
        setValue(tMsg.mtrHldFlg, FLG_OFF_N);

        BigDecimal rntlPrcAmt = tMsg.rntlPrcAmt.getValue();
        if (!hasValue(rntlPrcAmt)) {
            rntlPrcAmt = BigDecimal.ZERO;
        }
        setValue(tMsg.basePrcDealAmt, rntlPrcAmt);
        tMsg.mtrBllgCycleCd.clear();
        tMsg.mtrBllgDay.clear();
        tMsg.mtrBllgLastBllgDt.clear();
        tMsg.mtrBllgLastSchdDt.clear();
        tMsg.mtrBllgNextBllgDt.clear();
        tMsg.mtrBllgTmgCd.clear();
        tMsg.mtrCloDay.clear();
        tMsg.mtrDplyPerEndDay.clear();
        tMsg.mtrInvUpToDt.clear();
        tMsg.mtrReadMethCd.clear();
        // START 2019/03/12 K.Kitachi [QC#30732, ADD]
        tMsg.basePrcTermDealAmtRate.clear();
        // END 2019/03/12 K.Kitachi [QC#30732, ADD]

        S21FastTBLAccessor.insert(tMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            S21InfoLogOutput.println(NSAM0032E, new String[] {"DS_CONTR_DTL" });
            return false;
        }

        // START 2019/03/12 K.Kitachi [QC#30732, DEL]
//        setValue(tMsg.basePrcTermDealAmtRate, getBasePrcTermDealAmtRate(dsContrDtlPkRntl));
//        S21FastTBLAccessor.update(tMsg);
//        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
//            S21InfoLogOutput.println(NSAM0031E, new String[] {"DS_CONTR_DTL" });
//            return false;
//        }
        // END 2019/03/12 K.Kitachi [QC#30732, DEL]

        updDsContrDtlTMsgList.add(tMsg);
        return true;
    }

    private boolean insertContrRnwEsclForRntlLine(BigDecimal dsContrPk, BigDecimal dsContrDtlPk, BigDecimal dsContrDtlPkRntl) {
        DS_CONTR_RNW_ESCLTMsg dsContrRnwEsclTMsg = new DS_CONTR_RNW_ESCLTMsg();
        Map<String, Object> dsContrRnwEscl = getDsContrRnwEscl(glblCmpyCd, dsContrPk, dsContrDtlPk);
        if (dsContrRnwEscl != null) {
            setValue(dsContrRnwEsclTMsg.glblCmpyCd, glblCmpyCd);
            setValue(dsContrRnwEsclTMsg.dsContrRnwEsclPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_RNW_ESCL_SQ));
            setValue(dsContrRnwEsclTMsg.dsContrPk, dsContrPk);
            setValue(dsContrRnwEsclTMsg.dsContrDtlPk, dsContrDtlPkRntl);
            setValue(dsContrRnwEsclTMsg.dsContrMachLvlNum, DS_CONTR_MACH_LVL_NUM_2);
            setValue(dsContrRnwEsclTMsg.contrAutoRnwTpCd, (String) dsContrRnwEscl.get("CONTR_AUTO_RNW_TP_CD"));
            setValue(dsContrRnwEsclTMsg.rnwPrcMethCd, (String) dsContrRnwEscl.get("RNW_PRC_METH_CD"));
            setValue(dsContrRnwEsclTMsg.befEndRnwDaysAot, (BigDecimal) dsContrRnwEscl.get("BEF_END_RNW_DAYS_AOT"));
            setValue(dsContrRnwEsclTMsg.basePrcUpRatio, BigDecimal.ZERO);
            setValue(dsContrRnwEsclTMsg.contrUplftTpCd, CONTR_UPLFT_TP.DO_NOT_UPLIFT);
            setValue(dsContrRnwEsclTMsg.firstYrContrUplftFlg, FLG_OFF_N);
            setValue(dsContrRnwEsclTMsg.scdYrContrUplftFlg, FLG_OFF_N);
            setValue(dsContrRnwEsclTMsg.thirdYrContrUplftFlg, FLG_OFF_N);
            setValue(dsContrRnwEsclTMsg.frthYrContrUplftFlg, FLG_OFF_N);
            setValue(dsContrRnwEsclTMsg.fifthYrContrUplftFlg, FLG_OFF_N);
            setValue(dsContrRnwEsclTMsg.sixthYrContrUplftFlg, FLG_OFF_N);
            setValue(dsContrRnwEsclTMsg.svnthYrContrUplftFlg, FLG_OFF_N);
            setValue(dsContrRnwEsclTMsg.eighthYrContrUplftFlg, FLG_OFF_N);
            setValue(dsContrRnwEsclTMsg.ninthYrContrUplftFlg, FLG_OFF_N);
            setValue(dsContrRnwEsclTMsg.tenthYrContrUplftFlg, FLG_OFF_N);

            S21FastTBLAccessor.insert(dsContrRnwEsclTMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dsContrRnwEsclTMsg.getReturnCode())) {
                S21InfoLogOutput.println(NSAM0032E, new String[] {"DS_CONTR_RNW_ESCL" });
                return false;
            }
        }
        return true;
    }

    private boolean updateContrDtlByBasePrc(DS_CONTR_DTLTMsg dsContrDtlTMsg) {
        BigDecimal basePrcDealAmt = dsContrDtlTMsg.basePrcDealAmt.getValue();
        BigDecimal rntlPrcAmt = dsContrDtlTMsg.rntlPrcAmt.getValue();
        if (hasValue(basePrcDealAmt) && hasValue(rntlPrcAmt)) {
            if (basePrcDealAmt.compareTo(rntlPrcAmt) >= 0) {
                basePrcDealAmt = basePrcDealAmt.subtract(rntlPrcAmt);
            }
        }

        setValue(dsContrDtlTMsg.basePrcDealAmt, basePrcDealAmt);
        setValue(dsContrDtlTMsg.basePrcFuncAmt, NSXC003001Exchange.calcFuncFromDeal(glblCmpyCd, getCcyCd(), salesDate, basePrcDealAmt));
        // START 2019/03/12 K.Kitachi [QC#30732, ADD]
        dsContrDtlTMsg.basePrcTermDealAmtRate.clear();
        // END 2019/03/12 K.Kitachi [QC#30732, ADD]
        S21FastTBLAccessor.update(dsContrDtlTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dsContrDtlTMsg.getReturnCode())) {
            S21InfoLogOutput.println(NSAM0031E, new String[] {"DS_CONTR_DTL" });
            return false;
        }

        // START 2019/03/12 K.Kitachi [QC#30732, DEL]
//        setValue(dsContrDtlTMsg.basePrcTermDealAmtRate, getBasePrcTermDealAmtRate(dsContrDtlTMsg.dsContrDtlPk.getValue()));
//        S21FastTBLAccessor.update(dsContrDtlTMsg);
//        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dsContrDtlTMsg.getReturnCode())) {
//            S21InfoLogOutput.println(NSAM0031E, new String[] {"DS_CONTR_DTL" });
//            return false;
//        }
        // END 2019/03/12 K.Kitachi [QC#30732, DEL]

        return true;
    }

    private DS_CONTR_CR_CARD_POTMsgArray getContrCrCardPo(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_CR_CARD_POTMsg tMsg = new DS_CONTR_CR_CARD_POTMsg();
        tMsg.setSQLID("206");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        tMsg.setConditionValue("dsContrMachLvlNum01", DS_CONTR_MACH_LVL_NUM_2);
        return (DS_CONTR_CR_CARD_POTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
    }

    private boolean insertContrCrCardPoForRntlLine(DS_CONTR_CR_CARD_POTMsgArray dsContrCrCardPoTMsgArray, BigDecimal dsContrDtlPkRntl) {
        List<DS_CONTR_CR_CARD_POTMsg> dsContrCrCardPoTMsgList = new ArrayList<DS_CONTR_CR_CARD_POTMsg>();
        if (dsContrCrCardPoTMsgArray != null) {
            for (int i = 0; i < dsContrCrCardPoTMsgArray.getValidCount(); i++) {
                setValue(dsContrCrCardPoTMsgArray.no(i).dsContrCrCardPoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_CR_CARD_PO_SQ));
                setValue(dsContrCrCardPoTMsgArray.no(i).dsContrDtlPk, dsContrDtlPkRntl);
                dsContrCrCardPoTMsgList.add(dsContrCrCardPoTMsgArray.no(i));
            }
            if (dsContrCrCardPoTMsgList.size() > 0) {
                int insCnt = S21FastTBLAccessor.insert(dsContrCrCardPoTMsgList.toArray(new DS_CONTR_CR_CARD_POTMsg[dsContrCrCardPoTMsgList.size()]));
                if (insCnt != dsContrCrCardPoTMsgList.size()) {
                    S21InfoLogOutput.println(NSAM0032E, new String[] {"DS_CONTR_CR_CARD_PO" });
                    return false;
                }
            }
        }
        return true;
    }
    // END 2019/01/21 [QC#29782, ADD]

    // START 2019/01/29 [QC#29782-1, ADD]
    // 1.8.Copy Contract Detail for Supply Line
    private boolean copyContrDtlForSplyLine(Map<String, Object> shellContrDtl, DS_CONTR_DTLTMsg dsContrDtlTMsg, List<DS_CONTR_DTLTMsg> updDsContrDtlTMsgList) {
        BigDecimal dsContrPk = (BigDecimal) shellContrDtl.get("DS_CONTR_PK");
        DS_CONTRTMsg dsContrTMsg = getDsContrByKey(dsContrPk);
        if (dsContrTMsg == null) {
            return true;
        }

        // 1.8.1 get Supply Agreement Base Amount
        BigDecimal dsContrDtlPk = dsContrDtlTMsg.dsContrDtlPk.getValue();
        BigDecimal splyAgmtBaseAmt = getSplyAgmtBaseAmt(glblCmpyCd, dsContrDtlPk);
        if (splyAgmtBaseAmt == null) {
            return true;
        }

        // 1.8.2 get Supply Agreement Type by CPO
        String splyAgmMdse = getSplyAgmtMdseCd(glblCmpyCd, dsContrDtlPk);
        if (!hasValue(splyAgmMdse)) {
            return true;
        }
        Map<String,Object> cpoMap = getSplyMachMstr(glblCmpyCd, dsContrDtlTMsg.cpoOrdNum.getValue(), dsContrDtlTMsg.svcConfigMstrPk.getValue(), splyAgmMdse);
        if (cpoMap == null || cpoMap.isEmpty()) {
            return true;
        }

        String svcLineBizCd = dsContrTMsg.svcLineBizCd.getValue();
        String svcPgmMdseCd = dsContrDtlTMsg.svcPgmMdseCd.getValue();
        String splySvcPgmMdseCd = getAccSvcPgmMdseCd(svcPgmMdseCd, glblCmpyCd, svcLineBizCd, splyAgmMdse, svcPgmMdseCd);
        if (!hasValue(splySvcPgmMdseCd)) {
            return true;
        }

        String dsContrSrcTpCd = dsContrTMsg.dsContrSrcTpCd.getValue();
        String dsContrClsCd =dsContrTMsg.dsContrClsCd.getValue();

        DS_CONTR_INTFC_DEF_RULETMsg dsContrIntfcDefRuleTMsg = getDsContrIntfcDefRule(dsContrSrcTpCd, dsContrClsCd, svcLineBizCd);

        // 1.8.3 Insert DS Contract Detail for Supply Line
        BigDecimal dsContrDtlPkSply = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_DTL_SQ);
        if (!insertContrDtlForSplyLine(dsContrDtlTMsg, dsContrDtlPkSply, dsContrDtlPk, splyAgmtBaseAmt, splySvcPgmMdseCd, cpoMap, updDsContrDtlTMsgList, dsContrIntfcDefRuleTMsg)) {
            return false;
        }

        // 1.8.4 Insert DS Contract Renewal Escalation for Rental Line
        if (!insertContrRnwEsclForRntlLine(dsContrPk, dsContrDtlPk, dsContrDtlPkSply)) {
            return false;
        }

        // 1.8.5 Insert DS Contract CR_CARD_PO for Rental Line
        DS_CONTR_CR_CARD_POTMsgArray dsContrCrCardPoTMsgArray = getContrCrCardPo(glblCmpyCd, dsContrDtlPk);
        if (!insertContrCrCardPoForRntlLine(dsContrCrCardPoTMsgArray, dsContrDtlPkSply)) {
            return false;
        }

        // 1.8.6 Update DS Contract Detail by Base Price
        if (!updateContrDtlBySplyPrc(dsContrDtlTMsg, splyAgmtBaseAmt)) {
            return false;
        }

        // START 2019/05/24 [QC#50394, ADD]
        // 1.8.7 Create Wait Install Contract for Supply Line
        Map<String, Object> splyContrDtl = new HashMap<String, Object>();
        splyContrDtl.put("DS_CONTR_DTL_PK", dsContrDtlPkSply);
        splyContrDtl.put("SVC_MACH_MSTR_STS_CD", (String) shellContrDtl.get("SVC_MACH_MSTR_STS_CD"));

        List<Map<String, Object>> splyContrDtlList = new ArrayList<Map<String, Object>>();
        splyContrDtlList.add(splyContrDtl);

        if (!createWaitIstlContr(splyContrDtlList)) {
            return false;
        }
        // END 2019/05/24 [QC#50394, ADD]

        return true;
    }

    private BigDecimal getSplyAgmtBaseAmt(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        return (BigDecimal) this.ssmBatchClient.queryObject("getSplyAgmtBaseAmt", ssmPrm);
    }

    private String getSplyAgmtMdseCd(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("dsContrDtlPk", dsContrDtlPk);
        ssmPrm.put("slsDt", this.salesDate);
        return (String) this.ssmBatchClient.queryObject("getSplyAgmtMdseCd", ssmPrm);
    }

    private Map<String, Object> getSplyMachMstr(String glblCmpyCd, String cpoOrdNum, BigDecimal svcConfigMstrPk, String mdseCd) {
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("cpoOrdNum", cpoOrdNum);
        ssmPrm.put("svcConfigMstrPk", svcConfigMstrPk);
        ssmPrm.put("mdseCd", mdseCd);
        ssmPrm.put("acc", SVC_MACH_TP.ACCESSORY);
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getSplyMachMstr", ssmPrm);
    }

    private boolean insertContrDtlForSplyLine(DS_CONTR_DTLTMsg dsContrDtlTMsg, BigDecimal dsContrDtlPkSply, BigDecimal dsContrDtlPk, BigDecimal basePrcDealAmt, 
            String splySvcPgmMdseCd, Map<String,Object> cpoMap, List<DS_CONTR_DTLTMsg> updDsContrDtlTMsgList, DS_CONTR_INTFC_DEF_RULETMsg dsContrIntfcDefRuleTMsg) {
        DS_CONTR_DTLTMsg tMsg = new DS_CONTR_DTLTMsg();
        EZDMsg.copy(dsContrDtlTMsg, null, tMsg, null);

        setValue(tMsg.dsContrDtlPk, dsContrDtlPkSply);
        setValue(tMsg.dsContrDtlTpCd, DS_CONTR_DTL_TP.ACCESSORIES);
        setValue(tMsg.prntDsContrDtlPk, dsContrDtlPk);
        setValue(tMsg.basePrcDealAmt, basePrcDealAmt);
        setValue(tMsg.basePrcFuncAmt, NSXC003001Exchange.calcFuncFromDeal(glblCmpyCd, getCcyCd(), salesDate, tMsg.basePrcDealAmt.getValue()));
        setValue(tMsg.svcPgmMdseCd, splySvcPgmMdseCd);
        setValue(tMsg.dsContrCratTpCd, DS_CONTR_CRAT_TP.SUPPLY);
        setValue(tMsg.cpoOrdNum, (String) cpoMap.get("CPO_ORD_NUM"));
        setValue(tMsg.cpoDtlLineNum, (String) cpoMap.get("CPO_DTL_LINE_NUM"));
        setValue(tMsg.cpoDtlLineSubNum, (String) cpoMap.get("CPO_DTL_LINE_SUB_NUM"));
        setValue(tMsg.svcMachMstrPk, (BigDecimal) cpoMap.get("SVC_MACH_MSTR_PK"));

        setValue(tMsg.mtrHldFlg, FLG_OFF_N);
        tMsg.mtrBllgCycleCd.clear();
        tMsg.mtrBllgDay.clear();
        tMsg.mtrBllgLastBllgDt.clear();
        tMsg.mtrBllgLastSchdDt.clear();
        tMsg.mtrBllgNextBllgDt.clear();
        tMsg.mtrBllgTmgCd.clear();
        tMsg.mtrCloDay.clear();
        tMsg.mtrDplyPerEndDay.clear();
        tMsg.mtrInvUpToDt.clear();
        tMsg.mtrReadMethCd.clear();
        tMsg.prcMtrPkgPk.clear();
        // START 2019/03/12 K.Kitachi [QC#30732, ADD]
        tMsg.basePrcTermDealAmtRate.clear();
        // END 2019/03/12 K.Kitachi [QC#30732, ADD]

        if (FLG_OFF_N.equals(dsContrIntfcDefRuleTMsg.printDtlFlg.getValue())) {
            setValue(tMsg.svcInvMergeTpCd, SVC_INV_MERGE_TP.MERGE_INTO_BASE_CHARGE); // Merge
        } else {
            setValue(tMsg.svcInvMergeTpCd, SVC_INV_MERGE_TP.SPLIT_BASE_CHARGE); // Non Merge
        }

        S21FastTBLAccessor.insert(tMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            S21InfoLogOutput.println(NSAM0032E, new String[] {"DS_CONTR_DTL" });
            return false;
        }

        // START 2019/03/12 K.Kitachi [QC#30732, DEL]
//        setValue(tMsg.basePrcTermDealAmtRate, getBasePrcTermDealAmtRate(dsContrDtlPkSply));
//        S21FastTBLAccessor.update(tMsg);
//        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
//            S21InfoLogOutput.println(NSAM0031E, new String[] {"DS_CONTR_DTL" });
//            return false;
//        }
        // END 2019/03/12 K.Kitachi [QC#30732, DEL]

        updDsContrDtlTMsgList.add(tMsg);
        return true;
    }

    private String getAccSvcPgmMdseCd(String defSvcPgmMdseCd, String glblCmpyCd, String svcLineBizCd, String mdseCd, String prntSvcPgmMdseCd) {
        NSXC001001DefaultSvcPgmGetter nsxc001001DefaultSvcPgmGetter = new NSXC001001DefaultSvcPgmGetter();
        String svcPgmMdseCd = nsxc001001DefaultSvcPgmGetter.getAccSvcPgmMdse(glblCmpyCd, svcLineBizCd, mdseCd, ZYPConstant.FLG_OFF_N, prntSvcPgmMdseCd);
        if (!ZYPCommonFunc.hasValue(svcPgmMdseCd)) {
            svcPgmMdseCd = defSvcPgmMdseCd;
        }
        return svcPgmMdseCd;
    }

    private boolean updateContrDtlBySplyPrc(DS_CONTR_DTLTMsg dsContrDtlTMsg, BigDecimal splyAgmtBaseAmt) {
        BigDecimal basePrcDealAmt = dsContrDtlTMsg.basePrcDealAmt.getValue();
        if (hasValue(basePrcDealAmt) && hasValue(splyAgmtBaseAmt)) {
            basePrcDealAmt = basePrcDealAmt.subtract(splyAgmtBaseAmt);
            if (basePrcDealAmt.compareTo(BigDecimal.ZERO) < 0) {
                basePrcDealAmt = BigDecimal.ZERO;
            }
        }

        setValue(dsContrDtlTMsg.basePrcDealAmt, basePrcDealAmt);
        setValue(dsContrDtlTMsg.basePrcFuncAmt, NSXC003001Exchange.calcFuncFromDeal(glblCmpyCd, getCcyCd(), salesDate, basePrcDealAmt));
        // START 2019/03/12 K.Kitachi [QC#30732, ADD]
        dsContrDtlTMsg.basePrcTermDealAmtRate.clear();
        // END 2019/03/12 K.Kitachi [QC#30732, ADD]
        S21FastTBLAccessor.update(dsContrDtlTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dsContrDtlTMsg.getReturnCode())) {
            S21InfoLogOutput.println(NSAM0031E, new String[] {"DS_CONTR_DTL" });
            return false;
        }

        // START 2019/03/12 K.Kitachi [QC#30732, DEL]
//        setValue(dsContrDtlTMsg.basePrcTermDealAmtRate, getBasePrcTermDealAmtRate(dsContrDtlTMsg.dsContrDtlPk.getValue()));
//        S21FastTBLAccessor.update(dsContrDtlTMsg);
//        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dsContrDtlTMsg.getReturnCode())) {
//            S21InfoLogOutput.println(NSAM0031E, new String[] {"DS_CONTR_DTL" });
//            return false;
//        }
        // END 2019/03/12 K.Kitachi [QC#30732, DEL]

        return true;
    }
    // END 2019/01/29 [QC#29782-1, ADD]

    // 1.9.Update Contract Additional Charge
    private boolean updateDsContrAddlChrg(DS_CONTR_DTLTMsg dsContrDtlTMsg) {
        BigDecimal dsContrDtlPk = dsContrDtlTMsg.dsContrDtlPk.getValue();
        String contrEffFromDt = dsContrDtlTMsg.contrEffFromDt.getValue();
        String contrEffThruDt = dsContrDtlTMsg.contrEffThruDt.getValue();

        DS_CONTR_ADDL_CHRGTMsgArray tMsgArray = getDsContrAddlChrgByDsContrDtlPkForUpdate(dsContrDtlPk);
        for (int i = 0; i < tMsgArray.getValidCount(); i++) {
            DS_CONTR_ADDL_CHRGTMsg tMsg = (DS_CONTR_ADDL_CHRGTMsg) tMsgArray.get(i);
            setValue(tMsg.effFromDt, contrEffFromDt);
            setValue(tMsg.effThruDt, contrEffThruDt);

            S21FastTBLAccessor.update(tMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                S21InfoLogOutput.println(NSAM0031E, new String[] {"DS_CONTR_ADDL_CHRG" });
                return false;
            }
        }
        return true;
    }

    // 1.10.Update Contract Header/Fleet Line/Aggregate Line
    // mod start 2017/06/07 QC#18850
    private boolean updateContrHeadAndFltAggLine(BigDecimal dsContrPk, List<Map<String, Object>> shellContrDtlList, DS_CONTRTMsg updDsContrTMsg, DS_CONTR_DTLTMsg updDsContrDtlTMsgFltAgg,
            List<DS_CONTR_BLLG_MTRTMsg> updDsContrBllgMtrTMsgFltAggList) {
    // mod end 2017/06/07 QC#18850
        // 1.9.1.Get Min Effective From Date/Max Effective Thru Date
        Map<String, Object> effDt = getEffDtByDsContrDtl(dsContrPk);
        if (effDt == null) {
            S21InfoLogOutput.println(NSAM0045E, new String[] {"DS_CONTR_DTL" });
            return false;
        }
        String contrEffFromDt = (String) effDt.get("CONTR_EFF_FROM_DT");
        String contrEffThruDt = (String) effDt.get("CONTR_EFF_THRU_DT");

        // 1.9.2.Get Contract Duration Aot/Billing Cycle UOM Code
        ContrDurationInfo info = new ContrDurationInfo();
        info.setGlblCmpyCd(getGlobalCompanyCode());
        info.setContrEffFromDt(contrEffFromDt);
        info.setContrEffThruDt(contrEffThruDt);
        NSXC001001ContrDurationCalculator calculator = new NSXC001001ContrDurationCalculator(info);
        calculator.calcDuration();
        BigDecimal contrDurnNum = info.getContrDurnNum();
        String cycleUomCd = info.getCycleUomCd();

        // 1.9.3.Update Contract Header
        DS_CONTRTMsg dsContrTMsg = getDsContrByKeyForUpdate(dsContrPk);
        if (dsContrTMsg == null) {
            S21InfoLogOutput.println(NSAM0045E, new String[] {"DS_CONTR" });
            return false;
        }
        if (!hasValue(dsContrTMsg.dsContrNum)) {
            setValue(dsContrTMsg.dsContrNum, ZYPExtnNumbering.getUniqueID(this.glblCmpyCd, DS_CONTR_NUM));
        }
        if (DS_CONTR_STS.ORDER.equals(dsContrTMsg.dsContrStsCd.getValue())) {
            setValue(dsContrTMsg.dsContrStsCd, DS_CONTR_STS.DRAFT);
            // START 2018/03/13 K.Kojima [QC#24263,ADD]
            if (hasValue(dsContrTMsg.cpoOrdNum)) {
                setValue(dsContrTMsg.ctacPsnPk, getCtacPsnPk(dsContrTMsg.cpoOrdNum.getValue(), dsContrTMsg.altPayerCustCd.getValue(), dsContrTMsg.ctacPsnPk.getValue()));
            }
            // END 2018/03/13 K.Kojima [QC#24263,ADD]
        }
        setValue(dsContrTMsg.contrVrsnEffFromDt, contrEffFromDt);
        setValue(dsContrTMsg.contrVrsnEffThruDt, contrEffThruDt);
        setValue(dsContrTMsg.contrDurnAot, contrDurnNum);
        setValue(dsContrTMsg.bllgCycleUomCd, cycleUomCd);
        setValue(dsContrTMsg.dsContrLastUpdDt, this.salesDate);
        setValue(dsContrTMsg.dsContrLastUpdPsnCd, this.userId);
        // mod start 2017/12/04 QC#21698
        if (hasValue(dsContrTMsg.cpoOrdNum)) {
            // START 2018/01/09 M.Kidokoro [QC#20635,MOD]
//            setValue(dsContrTMsg.tocCd, getTocCd(dsContrTMsg.cpoOrdNum.getValue()));
//            setValue(dsContrTMsg.svcContrBrCd, getSvcContrBrCd(dsContrTMsg.cpoOrdNum.getValue()));
            setValue(dsContrTMsg.tocCd, getTocCd(dsContrTMsg.cpoOrdNum.getValue(), dsContrTMsg.svcLineBizCd.getValue()));
            setValue(dsContrTMsg.svcContrBrCd, getSvcContrBrCd(dsContrTMsg.cpoOrdNum.getValue(), dsContrTMsg.svcLineBizCd.getValue()));
            // END 2018/01/09 M.Kidokoro [QC#20635,MOD]
            // Mod Start 2017/12/14 QC#18362
            setValue(dsContrTMsg.contrAdminPsnCd, getContrAdminPsnCd(dsContrTMsg));
            // Mod End 2017/12/14 QC#18362
        }
        // mod end 2017/12/04 QC#21698
        // START 2018/07/02 K.Kim [QC#24146,ADD]
        String svcContrBrAutoEstFlg = getSvcContrBrAutoEstFlg(dsContrTMsg.svcContrBrCd.getValue());
        if (hasValue(svcContrBrAutoEstFlg) && ZYPConstant.FLG_ON_Y.equals(svcContrBrAutoEstFlg)) {
            String dsContrSrcTpCd = dsContrTMsg.dsContrSrcTpCd.getValue();
            String dsContrClsCd = dsContrTMsg.dsContrClsCd.getValue();
            String svcLineBizCd = dsContrTMsg.svcLineBizCd.getValue();
            DS_CONTR_INTFC_DEF_RULETMsg dsContrIntfcDefRuleTMsg = getDsContrIntfcDefRule(dsContrSrcTpCd, dsContrClsCd, svcLineBizCd);
            if (dsContrIntfcDefRuleTMsg != null && hasValue(dsContrIntfcDefRuleTMsg.mtrEstTpCd)) {
                setValue(dsContrTMsg.mtrEstTpCd, dsContrIntfcDefRuleTMsg.mtrEstTpCd.getValue());
            }
        } else {
            setValue(dsContrTMsg.mtrEstTpCd, MTR_EST_TP.DO_NOT_ESTIMATE);
        }
        // END 2018/07/02 K.Kim [QC#24146,ADD]

        S21FastTBLAccessor.update(dsContrTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dsContrTMsg.getReturnCode())) {
            S21InfoLogOutput.println(NSAM0031E, new String[] {"DS_CONTR" });
            return false;
        }
        EZDMsg.copy(dsContrTMsg, null, updDsContrTMsg, null);

        // 1.9.4.Get Fleet Line/Aggregate Line
        BigDecimal dsContrDtlPk = getDsContrDtlPkFltAggLine(dsContrPk);
        if (dsContrDtlPk == null) {
            return true;
        }

        // 1.9.5.Update Fleet Line/Aggregate Line
        DS_CONTR_DTLTMsg dsContrDtlTMsg = getDsContrDtlByKeyForUpdate(dsContrDtlPk);
        if (dsContrDtlTMsg == null) {
            S21InfoLogOutput.println(NSAM0045E, new String[] {"DS_CONTR_DTL" });
            return false;
        }

        String contrCloDay = getCloDay(dsContrDtlTMsg.baseDplyPerEndDay.getValue(), contrEffFromDt);
        String mtrCloDay = getCloDay(dsContrDtlTMsg.mtrDplyPerEndDay.getValue(), contrEffFromDt);
        // START 2017/08/24 [QC#20663, ADD]
        BigDecimal basePrcDealAmt = getBasePrcDealAmt(dsContrPk, shellContrDtlList, dsContrDtlTMsg);
        // END   2017/08/24 [QC#20663, ADD]

        // START 2017/08/23 [QC#20663, MOD]
        // setValue(dsContrDtlTMsg.dsContrDtlStsCd, DS_CONTR_DTL_STS.SAVED);
        // START 2018/04/20 U.Kim [QC#23378(Sol320), ADD]
        boolean statusChangeFlgFromOrder = false;
        // END 2018/04/20 U.Kim [QC#23378(Sol320), ADD]
        if (DS_CONTR_DTL_STS.ORDER.equals(dsContrDtlTMsg.dsContrDtlStsCd.getValue())) {
            setValue(dsContrDtlTMsg.dsContrDtlStsCd, DS_CONTR_DTL_STS.SAVED);
            // START 2018/03/13 K.Kojima [QC#24263,ADD]
            setValue(dsContrDtlTMsg.ctacPsnPk, getCtacPsnPk(dsContrDtlTMsg.cpoOrdNum.getValue(), dsContrDtlTMsg.baseBillToCustCd.getValue(), dsContrDtlTMsg.ctacPsnPk.getValue()));
            // END 2018/03/13 K.Kojima [QC#24263,ADD]
            // START 2018/04/20 U.Kim [QC#23378(Sol320), ADD]
            statusChangeFlgFromOrder = true;
            // END 2018/04/20 U.Kim [QC#23378(Sol320), ADD]
        }
        // END   2017/08/23 [QC#20663, MOD]
        setValue(dsContrDtlTMsg.contrEffFromDt, contrEffFromDt);
        setValue(dsContrDtlTMsg.contrEffThruDt, contrEffThruDt);
        setValue(dsContrDtlTMsg.contrCloDay, contrCloDay);
        setValue(dsContrDtlTMsg.mtrCloDay, mtrCloDay);

        // START 2017/08/24 [QC#20663, DEL]
        // add start 2017/06/07 QC#18850
        // BigDecimal basePrcDealAmt = getBasePrcDealAmt(dsContrPk, shellContrDtlList, dsContrDtlTMsg);
        // END   2017/08/24 [QC#20663, DEL]
        setValue(dsContrDtlTMsg.basePrcDealAmt, basePrcDealAmt);
        setValue(dsContrDtlTMsg.basePrcFuncAmt, NSXC003001Exchange.calcFuncFromDeal(this.glblCmpyCd, getCcyCd(), this.salesDate, dsContrDtlTMsg.basePrcDealAmt.getValue()));
        // add end 2017/06/07 QC#18850
        // START 2018/04/20 U.Kim [QC#23378(Sol320), ADD]
        if (statusChangeFlgFromOrder) {
            if (DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
                BigDecimal totalRntalPrcAmt = getRntlPrcAmtForFleetline(glblCmpyCd, dsContrPk);
                if (ZYPCommonFunc.hasValue(totalRntalPrcAmt)) {
                    setValue(dsContrDtlTMsg.rntlPrcAmt, totalRntalPrcAmt);
                    basePrcDealAmt = totalRntalPrcAmt.add(basePrcDealAmt);
                    setValue(dsContrDtlTMsg.basePrcDealAmt, basePrcDealAmt);
                    setValue(dsContrDtlTMsg.basePrcFuncAmt, NSXC003001Exchange.calcFuncFromDeal(this.glblCmpyCd, getCcyCd(), this.salesDate, dsContrDtlTMsg.basePrcDealAmt.getValue()));
                }
            }
        }
        // END 2018/04/20 U.Kim [QC#23378(Sol320), ADD]
        // add start 2017/07/10 QC#19818
        // START 2017/07/11 K.Kojima [QC#19822,MOD]
        // if (DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
        //     setValue(dsContrDtlTMsg.basePrcTermDealAmtRate, getBasePrcTermDealAmtRateForFltLine(dsContrDtlTMsg.dsContrDtlPk.getValue(), basePrcDealAmt));
        // }
        if (DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue()) || DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
            setValue(dsContrDtlTMsg.basePrcTermDealAmtRate, getBasePrcTermDealAmtRateForFltAggLine(dsContrDtlTMsg.dsContrDtlPk.getValue(), basePrcDealAmt));
        }
        // END 2017/07/11 K.Kojima [QC#19822,MOD]
        // add end   2017/07/10 QC#19818

        // add start 2017/06/27 CSA Defect#19560
        if (ZYPConstant.FLG_ON_Y.equals(dsContrDtlTMsg.billWithEquipFlg.getValue())) {
            setValue(dsContrDtlTMsg.baseInvUpToDt, contrEffThruDt);
        }
        // add end 2017/06/27 CSA Defect#19560

        // START 2018/08/30 M.Naito [QC#27102, ADD]
        if (dsContrTMsg != null) {
            String uplftFromDt = NSXC001001GetUplftFromDt.getUplftFromDt(glblCmpyCd, dsContrDtlTMsg.contrEffFromDt.getValue(), CONTR_INTFC_SRC_TP.ORDER, dsContrTMsg.dsContrClsCd.getValue(), dsContrTMsg.svcLineBizCd.getValue());
            if (ZYPCommonFunc.hasValue(uplftFromDt)) {
                setValue(dsContrDtlTMsg.uplftFromDt, uplftFromDt);
            }
        }
        // END 2018/08/30 M.Naito [QC#27102, ADD]

        S21FastTBLAccessor.update(dsContrDtlTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dsContrDtlTMsg.getReturnCode())) {
            S21InfoLogOutput.println(NSAM0031E, new String[] {"DS_CONTR_DTL" });
            return false;
        }
        EZDMsg.copy(dsContrDtlTMsg, null, updDsContrDtlTMsgFltAgg, null);

        DS_CONTR_BLLG_MTRTMsgArray dsContrBllgMtrTMsgArray = getDsContrBllgMtrByDsContrDtlPkForUpdate(dsContrDtlPk);
        for (int i = 0; i < dsContrBllgMtrTMsgArray.getValidCount(); i++) {
            DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg = (DS_CONTR_BLLG_MTRTMsg) dsContrBllgMtrTMsgArray.get(i);
            // START 2017/08/23 [QC#20663, MOD]
            // setValue(dsContrBllgMtrTMsg.dsContrBllgMtrStsCd, DS_CONTR_DTL_STS.SAVED);
            if (DS_CONTR_DTL_STS.ORDER.equals(dsContrBllgMtrTMsg.dsContrBllgMtrStsCd.getValue())) {
                setValue(dsContrBllgMtrTMsg.dsContrBllgMtrStsCd, DS_CONTR_DTL_STS.SAVED);
                // START 2018/03/13 K.Kojima [QC#24263,ADD]
                setValue(dsContrBllgMtrTMsg.ctacPsnPk, getCtacPsnPk(dsContrDtlTMsg.cpoOrdNum.getValue(), dsContrBllgMtrTMsg.bllgMtrBillToCustCd.getValue(), dsContrBllgMtrTMsg.ctacPsnPk.getValue()));
                // END 2018/03/13 K.Kojima [QC#24263,ADD]
            }
            // END   2017/08/23 [QC#20663, MOD]

            S21FastTBLAccessor.update(dsContrBllgMtrTMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dsContrBllgMtrTMsg.getReturnCode())) {
                S21InfoLogOutput.println(NSAM0031E, new String[] {"DS_CONTR_BLLG_MTR" });
                return false;
            }
            updDsContrBllgMtrTMsgFltAggList.add(dsContrBllgMtrTMsg);
        }

        DS_CONTR_ADDL_CHRGTMsgArray dsContrAddlChrgTMsgArray = getDsContrAddlChrgByDsContrDtlPkForUpdate(dsContrDtlPk);
        for (int i = 0; i < dsContrAddlChrgTMsgArray.getValidCount(); i++) {
            DS_CONTR_ADDL_CHRGTMsg dsContrAddlChrgTMsg = (DS_CONTR_ADDL_CHRGTMsg) dsContrAddlChrgTMsgArray.get(i);
            setValue(dsContrAddlChrgTMsg.effFromDt, contrEffFromDt);
            setValue(dsContrAddlChrgTMsg.effThruDt, contrEffThruDt);

            S21FastTBLAccessor.update(dsContrAddlChrgTMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dsContrAddlChrgTMsg.getReturnCode())) {
                S21InfoLogOutput.println(NSAM0031E, new String[] {"DS_CONTR_ADDL_CHRG" });
                return false;
            }
        }
        return true;
    }

    // add start 2017/12/04 QC#21698
    // START 2018/01/09 M.Kidokoro [QC#20635,MOD]
//    private String getTocCd(String cpoOrdNum) {
    private String getTocCd(String cpoOrdNum, String svcLineBizCd) {
    // END 2018/01/09 M.Kidokoro [QC#20635,MOD]
        String tocCd = null;
        String emsdTocCd = getEmsdTocd(cpoOrdNum);
        if (hasValue(emsdTocCd)) {
            return emsdTocCd;
        }

        CPOTMsg cpoTMsg = getCPOTMsg(cpoOrdNum);
        if (cpoTMsg == null || !ZYPCommonFunc.hasValue(cpoTMsg.slsRepTocCd)) {
            return null;
        }

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("tocCd", cpoTMsg.slsRepTocCd.getValue());
        // START 2018/01/09 M.Kidokoro [QC#20635,ADD]
        param.put("svcLineBizCd", svcLineBizCd);
        // END 2018/01/09 M.Kidokoro [QC#20635,ADD]
        String subContrBrMaptocCd = (String) this.ssmBatchClient.queryObject("getTocCd", param);

        if (hasValue(subContrBrMaptocCd)) {
            tocCd = subContrBrMaptocCd;
        } else {
            tocCd = cpoTMsg.slsRepTocCd.getValue();
        }
        return tocCd;

    }

    private CPOTMsg getCPOTMsg(String cpoOrdNum) {
        CPOTMsg tMsg = new CPOTMsg();
        setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tMsg.cpoOrdNum, cpoOrdNum);
        return (CPOTMsg) S21FastTBLAccessor.findByKey(tMsg);
    }

    private String getEmsdTocd(String cpoOrdNum) {
        Map<String, Object> param = new HashMap<String, Object>();

        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", cpoOrdNum);
        param.put("emsdContrBrRep", ORD_CATG_CTX_TP_EMSD_CONTR_BR_REP);
        return (String) this.ssmBatchClient.queryObject("getEmsdTocd", param);
    }

    // START 2018/01/09 M.Kidokoro [QC#20635,MOD]
//    private String getSvcContrBrCd(String cpoOrdNum) {
    private String getSvcContrBrCd(String cpoOrdNum, String svcLineBizCd) {
    // END 2018/01/09 M.Kidokoro [QC#20635,MOD]
        String svcContrBrCd = null;
        Map<String, Object> eMsdtargetMap = getEmsdContractBranch(cpoOrdNum);
        if (eMsdtargetMap != null) {
            return (String) eMsdtargetMap.get("SVC_CONTR_BR_CD_OCBC");
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", cpoOrdNum);
        // START 2018/01/09 M.Kidokoro [QC#20635,ADD]
        param.put("svcLineBizCd", svcLineBizCd);
        // END 2018/01/09 M.Kidokoro [QC#20635,ADD]
        Map<String, Object> targetMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getContractBranch", param);

        if (targetMap != null) {
            svcContrBrCd = (String) targetMap.get("SVC_CONTR_BR_CD_SCCBM");
        }
        return svcContrBrCd;
    }

    private Map<String, Object> getEmsdContractBranch(String cpoOrdNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", cpoOrdNum);
        param.put("emsdContrBrRep", ORD_CATG_CTX_TP_EMSD_CONTR_BR_REP);

        return (Map<String, Object>) this.ssmBatchClient.queryObject("getEmsdContractBranch", param);
    }

    // Mod Start 2017/12/14 QC#18362
    private String getContrAdminPsnCd(DS_CONTRTMsg dsContrTMsg) {
        String contrAdminPsnCd = null;
        Map<String, Object> eMsdtargetMap = getEmsdContractBranch(dsContrTMsg.cpoOrdNum.getValue());
        if (eMsdtargetMap != null) {
            return (String) eMsdtargetMap.get("CONTR_ADMIN_PSN_CD_OCBC");

        }
        String acctAdminPsnCd = getAcctAdminPsnCd(dsContrTMsg);
        if (hasValue(acctAdminPsnCd)) {
            return acctAdminPsnCd;
        }

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", dsContrTMsg.cpoOrdNum.getValue());
        // START 2018/01/09 M.Kidokoro [QC#20635,ADD]
        param.put("svcLineBizCd", dsContrTMsg.svcLineBizCd.getValue());
        // END 2018/01/09 M.Kidokoro [QC#20635,ADD]
        Map<String, Object> targetMap = (Map<String, Object>) this.ssmBatchClient.queryObject("getContractBranch", param);

        if (targetMap != null) {
            contrAdminPsnCd = (String) targetMap.get("CONTR_ADMIN_PSN_CD");
        }
        return contrAdminPsnCd;
    }
    // Mod End 2017/12/14 QC#18362
    // add end 2017/12/04 QC#21698

    // START 2018/07/02 K.Kim [QC#24146,ADD]
    private String getSvcContrBrAutoEstFlg(String svcContrBrCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("svcContrBrCd", svcContrBrCd);
        param.put("svcContrBrActvFlg", ZYPConstant.FLG_ON_Y);
        param.put("slsDt", this.salesDate);
        return (String) this.ssmBatchClient.queryObject("getSvcContrBrAutoEstFlg", param);
    }
    // END 2018/07/02 K.Kim [QC#24146,ADD]

    private Map<String, Object> getEffDtByDsContrDtl(BigDecimal dsContrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        String[] dsContrDtlStsCdList = new String[] {DS_CONTR_DTL_STS.CANCELLED, DS_CONTR_DTL_STS.ORDER };
        param.put("dsContrDtlStsCdList", dsContrDtlStsCdList);
        // START 2017/09/22 A.Kohinata [QC#18534,ADD]
        param.put("fleet", DS_CONTR_DTL_TP.FLEET);
        param.put("aggregate", DS_CONTR_DTL_TP.AGGREGATE);
        // END 2017/09/22 A.Kohinata [QC#18534,ADD]
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getEffDtByDsContrDtl", param);
    }

    private BigDecimal getDsContrDtlPkFltAggLine(BigDecimal dsContrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("fleet", DS_CONTR_DTL_TP.FLEET);
        param.put("aggregate", DS_CONTR_DTL_TP.AGGREGATE);
        return (BigDecimal) this.ssmBatchClient.queryObject("getDsContrDtlPkFltAggLine", param);
    }

    // add start 2017/06/07 QC#18850
    private BigDecimal getBasePrcDealAmt(BigDecimal dsContrPk, List<Map<String, Object>> shellContrDtlList, DS_CONTR_DTLTMsg dsContrDtlTMsg) {
        String dsContrCatgCd = (String) shellContrDtlList.get(0).get("DS_CONTR_CATG_CD");
        BigDecimal curBasePrcDealAmt = dsContrDtlTMsg.basePrcDealAmt.getValue();
        BigDecimal newBasePrcDealAmt = null;

        if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
            // START 2017/08/22 [QC#20663, MOD]
            // List<String> cpoOrdNumList = new ArrayList<String>();
            // for (int i = 0; i < shellContrDtlList.size(); i++) {
            //     String cpoOrdNum = (String) shellContrDtlList.get(i).get("CPO_ORD_NUM");
            //     if (!cpoOrdNumList.contains(cpoOrdNum)) {
            //         cpoOrdNumList.add(cpoOrdNum);
            //     }
            // }
            // BigDecimal addBasePrcDealAmt = BigDecimal.ZERO;
            // for (String cpoOrdNum : cpoOrdNumList) {
            //     BigDecimal basePrcDealAmt = getBasePrcDealAmtForFlt(dsContrPk, cpoOrdNum);
            //     addBasePrcDealAmt = addBasePrcDealAmt.add(basePrcDealAmt);
            // }
            // if (hasValue(curBasePrcDealAmt)) {
            //     newBasePrcDealAmt = curBasePrcDealAmt.add(addBasePrcDealAmt);
            // } else {
            //     newBasePrcDealAmt = addBasePrcDealAmt;
            // }
            if (DS_CONTR_DTL_STS.ORDER.equals(dsContrDtlTMsg.dsContrDtlStsCd.getValue())) {
                String cpoOrdNum = dsContrDtlTMsg.cpoOrdNum.getValue();
                newBasePrcDealAmt = getBasePrcDealAmtForFlt(dsContrPk, cpoOrdNum);
            } else {
                newBasePrcDealAmt = curBasePrcDealAmt;
            }
            // END   2017/08/22 [QC#20663, MOD]
        }

        if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
            newBasePrcDealAmt = getBasePrcDealAmtForAgg(dsContrPk);
        }

        return newBasePrcDealAmt;
    }

    private BigDecimal getBasePrcDealAmtForFlt(BigDecimal dsContrPk, String cpoOrdNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("cpoOrdNum", cpoOrdNum);
        param.put("shell", DS_CONTR_DTL_TP.PRICE_OF_MODEL_LEVEL);
        // Del Start 2017/09/11 QC#21039
//        param.put("fleet", DS_CONTR_DTL_TP.FLEET);
//        param.put("order", DS_CONTR_DTL_STS.ORDER);
        // Del End 2017/09/11 QC#21039
        return (BigDecimal) this.ssmBatchClient.queryObject("getBasePrcDealAmtForFlt", param);
    }

    private BigDecimal getBasePrcDealAmtForAgg(BigDecimal dsContrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("aggregate", DS_CONTR_DTL_TP.AGGREGATE);
        param.put("shell", DS_CONTR_DTL_TP.PRICE_OF_MODEL_LEVEL);
        param.put("terminated", DS_CONTR_DTL_STS.TERMINATED);
        param.put("expired", DS_CONTR_DTL_STS.EXPIRED);
        param.put("cancelled", DS_CONTR_DTL_STS.CANCELLED);
        param.put("order", DS_CONTR_DTL_STS.ORDER);
        return (BigDecimal) this.ssmBatchClient.queryObject("getBasePrcDealAmtForAgg", param);
    }

    private String getCcyCd() {
        GLBL_CMPYTMsg tMsg = new GLBL_CMPYTMsg();
        setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        tMsg = (GLBL_CMPYTMsg) S21FastTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return null;
        }
        return tMsg.stdCcyCd.getValue();
    }
    // add end 2017/06/07 QC#18850

    // 1.11.Update Term Condition
    // START 2018/02/23 K.Kojima [QC#21685,MOD]
    // private void updateSvcTermCond(BigDecimal dsContrPk, List<Map<String, Object>> shellContrDtlList) {
    // START 2018/06/13 [QC#26495,MOD]
//    private void updateSvcTermCond(BigDecimal dsContrPk, List<Map<String, Object>> shellContrDtlList, String prcSvcContrTpCd) {
    private void updateSvcTermCond(BigDecimal dsContrPk, List<Map<String, Object>> shellContrDtlList, String prcSvcContrTpCd, DS_CONTRTMsg dsContrTMsg) {
    // END 2018/06/13 [QC#26495,MOD]
    // END 2018/02/23 K.Kojima [QC#21685,MOD]
        List<BigDecimal> dsContrDtlPkList = new ArrayList<BigDecimal>();

        BigDecimal dsContrDtlPkFltAggLine = getDsContrDtlPkFltAggLine(dsContrPk);
        if (dsContrDtlPkFltAggLine != null) {
            dsContrDtlPkList.add(dsContrDtlPkFltAggLine);
        }

        for (int i = 0; i < shellContrDtlList.size(); i++) {
            Map<String, Object> shellContrDtl = shellContrDtlList.get(i);
            dsContrDtlPkList.add((BigDecimal) shellContrDtl.get("DS_CONTR_DTL_PK"));
        }

        for (BigDecimal dsContrDtlPk : dsContrDtlPkList) {
            DS_CONTR_DTLTMsg tMsg = getDsContrDtlByKey(dsContrDtlPk);
            if (tMsg == null) {
                continue;
            }
            String contrFromDt = tMsg.contrEffFromDt.getValue();

            SlaInfoBean slaBean = new SlaInfoBean();
            slaBean.setGlblCmpyCd(this.glblCmpyCd);
            slaBean.setDsContrDtlPk(dsContrDtlPk);
            if (this.salesDate.compareTo(contrFromDt) >= 0) {
                slaBean.setSlaDt(this.salesDate);
            } else {
                slaBean.setSlaDt(contrFromDt);
            }
            if (!NSXC001001GetRspTime.getSLA(slaBean)) {
                continue;
            }
            String termCondOptValTxt = slaBean.getTermCondOptValTxt();
            if (!hasValue(termCondOptValTxt)) {
                continue;
            }

            for (int j = 0; j < SLA_TERM_COND_VARCHAR_CONST_KEY.length; j++) {
                String varCharConstKey = SLA_TERM_COND_VARCHAR_CONST_KEY[j];
                String svcTermCondAttrbNm = ZYPCodeDataUtil.getVarCharConstValue(varCharConstKey, this.glblCmpyCd);
                BigDecimal svcTermCondAttrbPk = getSvcTermCondAttrbPk(svcTermCondAttrbNm);
                if (svcTermCondAttrbPk == null) {
                    continue;
                }
                updateSvcTermCondForSla(dsContrPk, dsContrDtlPk, svcTermCondAttrbPk, termCondOptValTxt);
            }
        }

        // START 2018/02/23 K.Kojima [QC#21685,ADD]
        String svcTermCondAttrbNm = ZYPCodeDataUtil.getVarCharConstValue(TERM_COND_STAPLES_INCL_NAME, this.glblCmpyCd);
        BigDecimal svcTermCondAttrbPk = getSvcTermCondAttrbPk(svcTermCondAttrbNm);
        if (svcTermCondAttrbPk != null) {
            String termCondOptValTxt = "";
            PRC_SVC_CONTR_TPTMsg prcSvcContrTpTMsg = getPrcSvcContrTp(prcSvcContrTpCd);
            // 2018/05/07 QC#22482 Mod Start
//            if (ZYPConstant.FLG_ON_Y.equals(prcSvcContrTpTMsg.stplInclSvcFlg.getValue())) {
//                termCondOptValTxt = STAPLES_INCL_VAL_YES;
//            } else {
//                termCondOptValTxt = STAPLES_INCL_VAL_NO;
//            }
//            updateSvcTermCondForContrLevel(dsContrPk, svcTermCondAttrbPk, termCondOptValTxt);
            if (prcSvcContrTpTMsg != null) {
                if (ZYPConstant.FLG_ON_Y.equals(prcSvcContrTpTMsg.stplInclSvcFlg.getValue())) {
                    termCondOptValTxt = STAPLES_INCL_VAL_YES;
                } else {
                    termCondOptValTxt = STAPLES_INCL_VAL_NO;
                }
                updateSvcTermCondForContrLevel(dsContrPk, svcTermCondAttrbPk, termCondOptValTxt);
            }
            // 2018/05/07 QC#22482 Mod End
        }
        // END 2018/02/23 K.Kojima [QC#21685,ADD]

        // START 2018/06/13 [QC#26495,ADD]

        String termCondOptValTxt = null;

        for (int i = 0; i < CONTR_LVL_TERM_COND_VARCHAR_CONST_KEY.length; i++) {
            svcTermCondAttrbNm = null;
            svcTermCondAttrbPk = null;
            termCondOptValTxt = null;

            String varCharConstKey = CONTR_LVL_TERM_COND_VARCHAR_CONST_KEY[i];
            svcTermCondAttrbNm = ZYPCodeDataUtil.getVarCharConstValue(varCharConstKey, this.glblCmpyCd);
            svcTermCondAttrbPk = getSvcTermCondAttrbPk(svcTermCondAttrbNm);
            if (svcTermCondAttrbPk == null) {
                continue;
            }
            if (TERM_COND_CBS_STD_VRSN.equals(varCharConstKey)) {
                termCondOptValTxt = dsContrTMsg.cpoSvcAgmtVerNum.getValue();
            } else if (TERM_COND_CONTR_PRC_TP.equals(varCharConstKey)) {
                termCondOptValTxt = dsContrTMsg.prcSvcPlnTpCd.getValue();
            }
            updateSvcTermCondForContrLevel(dsContrPk, svcTermCondAttrbPk, termCondOptValTxt);
        }
        // END 2018/06/13 [QC#26495,ADD]
    }

    private BigDecimal getSvcTermCondAttrbPk(String svcTermCondAttrbNm) {
        if (!hasValue(svcTermCondAttrbNm)) {
            return null;
        }
        SVC_TERM_COND_ATTRBTMsgArray tMsgArray = getSvcTermCondAttrb(svcTermCondAttrbNm);
        if (tMsgArray.getValidCount() > 0) {
            return ((SVC_TERM_COND_ATTRBTMsg) tMsgArray.get(0)).svcTermCondAttrbPk.getValue();
        }
        return null;
    }

    private void updateSvcTermCondForSla(BigDecimal dsContrPk, BigDecimal dsContrDtlPk, BigDecimal svcTermCondAttrbPk, String termCondOptValTxt) {
        SVC_TERM_CONDTMsg tMsg = new SVC_TERM_CONDTMsg();
        SVC_TERM_CONDTMsgArray tMsgArray = getSvcTermCondForUpdate(dsContrPk, dsContrDtlPk, svcTermCondAttrbPk);
        if (tMsgArray != null && tMsgArray.getValidCount() > 0) {
            tMsg = tMsgArray.no(0);
            setValue(tMsg.svcTermAttrbMapValCd, termCondOptValTxt);
            S21FastTBLAccessor.update(tMsg);
        } else {
            setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(tMsg.svcTermCondPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_TERM_COND_SQ));
            setValue(tMsg.dsContrPk, dsContrPk);
            setValue(tMsg.dsContrDtlPk, dsContrDtlPk);
            setValue(tMsg.svcTermCondAttrbPk, svcTermCondAttrbPk);
            setValue(tMsg.svcTermAttrbMapValCd, termCondOptValTxt);
            S21FastTBLAccessor.insert(tMsg);
        }
    }

    // START 2018/02/23 K.Kojima [QC#21685,ADD]
    private void updateSvcTermCondForContrLevel(BigDecimal dsContrPk, BigDecimal svcTermCondAttrbPk, String termCondOptValTxt) {
        SVC_TERM_CONDTMsg tMsg = new SVC_TERM_CONDTMsg();
        SVC_TERM_CONDTMsgArray tMsgArray = getSvcTermCondForUpdate(dsContrPk, svcTermCondAttrbPk);
        if (tMsgArray != null && tMsgArray.getValidCount() > 0) {
            tMsg = tMsgArray.no(0);
            setValue(tMsg.svcTermAttrbMapValCd, termCondOptValTxt);
            S21FastTBLAccessor.update(tMsg);
        } else {
            setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(tMsg.svcTermCondPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_TERM_COND_SQ));
            setValue(tMsg.dsContrPk, dsContrPk);
            setValue(tMsg.svcTermCondAttrbPk, svcTermCondAttrbPk);
            setValue(tMsg.svcTermAttrbMapValCd, termCondOptValTxt);
            S21FastTBLAccessor.insert(tMsg);
        }
    }
    // END 2018/02/23 K.Kojima [QC#21685,ADD]

    // 2.Create Billing Schedule
    private boolean createBllgSchd(DS_CONTRTMsg updDsContrTMsg, List<DS_CONTR_DTLTMsg> updDsContrDtlTMsgList, List<DS_CONTR_BLLG_MTRTMsg> updDsContrBllgMtrTMsgList, DS_CONTR_DTLTMsg updDsContrDtlTMsgFltAgg,
            // START 2018/04/20 U.Kim [QC#23378(Sol320), MOD]
            // List<DS_CONTR_BLLG_MTRTMsg> updDsContrBllgMtrTMsgFltAggList) {
            List<DS_CONTR_BLLG_MTRTMsg> updDsContrBllgMtrTMsgFltAggList, String process) {
            // END 2018/04/20 U.Kim [QC#23378(Sol320), MOD]
        NSZC046001PMsg pMsg = new NSZC046001PMsg();
        setValue(pMsg.xxModeCd, NSZC046001Constant.MODE_UPDATE);
        setValue(pMsg.slsDt, this.salesDate);
        setValue(pMsg.psnCd, this.userId);

        EZDMsg.copy(updDsContrTMsg, null, pMsg, null);
        setValue(pMsg.contrAdminPsnCd_HD, updDsContrTMsg.contrAdminPsnCd);
        setValue(pMsg.billToCustCd, updDsContrTMsg.altPayerCustCd);

        int idx = 0;
        if (DS_CONTR_CATG.REGULAR.equals(updDsContrTMsg.dsContrCatgCd.getValue())) {
            for (int i = 0; i < updDsContrDtlTMsgList.size(); i++) {
                EZDMsg.copy(updDsContrDtlTMsgList.get(i), null, pMsg.xxContrDtlList.no(idx), null);
                // add start 2017/06/16 QC#19240
                if (ZYPConstant.FLG_ON_Y.equals(updDsContrDtlTMsgList.get(i).billWithEquipFlg.getValue())) {
                    setValue(pMsg.xxContrDtlList.no(idx).invUpToDt, updDsContrDtlTMsgList.get(i).contrEffThruDt);
                }
                // add end   2017/06/16 QC#19240
                idx++;
            }
            pMsg.xxContrDtlList.setValidCount(idx);

            idx = 0;
            for (int i = 0; i < updDsContrBllgMtrTMsgList.size(); i++) {
                EZDMsg.copy(updDsContrBllgMtrTMsgList.get(i), null, pMsg.xxDsContrBllgMtrList.no(idx), null);
                setValue(pMsg.xxDsContrBllgMtrList.no(idx).xxModeCd, NSZC046001Constant.XX_MODE_UPDATE);
                idx++;
            }
            pMsg.xxDsContrBllgMtrList.setValidCount(idx);
        }

        if (DS_CONTR_CATG.FLEET.equals(updDsContrTMsg.dsContrCatgCd.getValue())) {
            EZDMsg.copy(updDsContrDtlTMsgFltAgg, null, pMsg.xxContrDtlList.no(idx), null);
            // add start 2017/06/16 QC#19240
            if (ZYPConstant.FLG_ON_Y.equals(updDsContrDtlTMsgFltAgg.billWithEquipFlg.getValue())) {
                setValue(pMsg.xxContrDtlList.no(idx).invUpToDt, updDsContrDtlTMsgFltAgg.contrEffThruDt);
            }
            // add end   2017/06/16 QC#19240
            idx++;
            pMsg.xxContrDtlList.setValidCount(idx);

            idx = 0;
            for (int i = 0; i < updDsContrBllgMtrTMsgFltAggList.size(); i++) {
                EZDMsg.copy(updDsContrBllgMtrTMsgFltAggList.get(i), null, pMsg.xxDsContrBllgMtrList.no(idx), null);
                setValue(pMsg.xxDsContrBllgMtrList.no(idx).xxModeCd, NSZC046001Constant.XX_MODE_UPDATE);
                idx++;
            }
            pMsg.xxDsContrBllgMtrList.setValidCount(idx);
        }

        if (DS_CONTR_CATG.AGGREGATE.equals(updDsContrTMsg.dsContrCatgCd.getValue())) {
            EZDMsg.copy(updDsContrDtlTMsgFltAgg, null, pMsg.xxContrDtlList.no(idx), null);
            // add start 2017/06/16 QC#19240
            if (ZYPConstant.FLG_ON_Y.equals(updDsContrDtlTMsgFltAgg.billWithEquipFlg.getValue())) {
                setValue(pMsg.xxContrDtlList.no(idx).invUpToDt, updDsContrDtlTMsgFltAgg.contrEffThruDt);
            }
            // add end   2017/06/16 QC#19240
            idx++;
            // START 2017/07/11 K.Kojima [QC#19822,ADD]
            BigDecimal[] dsContrDtlPkList = new BigDecimal[updDsContrDtlTMsgList.size()];
            // END 2017/07/11 K.Kojima [QC#19822,ADD]
            for (int i = 0; i < updDsContrDtlTMsgList.size(); i++) {
                EZDMsg.copy(updDsContrDtlTMsgList.get(i), null, pMsg.xxContrDtlList.no(idx), null);
                // add start 2017/06/16 QC#19240
                if (ZYPConstant.FLG_ON_Y.equals(updDsContrDtlTMsgList.get(i).billWithEquipFlg.getValue())) {
                    setValue(pMsg.xxContrDtlList.no(idx).invUpToDt, updDsContrDtlTMsgList.get(i).contrEffThruDt);
                }
                // add end   2017/06/16 QC#19240
                // START 2017/07/11 K.Kojima [QC#19822,ADD]]
                dsContrDtlPkList[i] = updDsContrDtlTMsgList.get(i).dsContrDtlPk.getValue();
                // END 2017/07/11 K.Kojima [QC#19822,ADD]
                idx++;
            }
            // START 2017/07/11 K.Kojima [QC#19822,ADD]
            List<DS_CONTR_DTLTMsg> addDtlList = getActiveDetailList(updDsContrTMsg.dsContrPk.getValue(), dsContrDtlPkList);
            if (addDtlList != null) {
                for (int i = 0; i < addDtlList.size(); i++) {
                    EZDMsg.copy(addDtlList.get(i), null, pMsg.xxContrDtlList.no(idx), null);
                    idx++;
                }
            }
            // END 2017/07/11 K.Kojima [QC#19822,ADD]
            pMsg.xxContrDtlList.setValidCount(idx);

            idx = 0;
            for (int i = 0; i < updDsContrBllgMtrTMsgFltAggList.size(); i++) {
                EZDMsg.copy(updDsContrBllgMtrTMsgFltAggList.get(i), null, pMsg.xxDsContrBllgMtrList.no(idx), null);
                setValue(pMsg.xxDsContrBllgMtrList.no(idx).xxModeCd, NSZC046001Constant.XX_MODE_UPDATE);
                idx++;
            }
            for (int i = 0; i < updDsContrBllgMtrTMsgList.size(); i++) {
                EZDMsg.copy(updDsContrBllgMtrTMsgList.get(i), null, pMsg.xxDsContrBllgMtrList.no(idx), null);
                setValue(pMsg.xxDsContrBllgMtrList.no(idx).xxModeCd, NSZC046001Constant.XX_MODE_UPDATE);
                DS_CONTR_DTLTMsg tMsg = getDsContrDtlByKey(updDsContrBllgMtrTMsgList.get(i).dsContrDtlPk.getValue());
                if (tMsg != null) {
                    setValue(pMsg.xxDsContrBllgMtrList.no(idx).svcMachMstrPk, tMsg.svcMachMstrPk);
                }
                idx++;
            }
            // START 2017/07/11 K.Kojima [QC#19822,ADD]
            List<DS_CONTR_BLLG_MTRTMsg> addBllgMtrList = getActiveBllgMtrList(updDsContrTMsg.dsContrPk.getValue(), dsContrDtlPkList);
            if (addBllgMtrList != null) {
                for (int i = 0; i < addBllgMtrList.size(); i++) {
                    EZDMsg.copy(addBllgMtrList.get(i), null, pMsg.xxDsContrBllgMtrList.no(idx), null);
                    setValue(pMsg.xxDsContrBllgMtrList.no(idx).xxModeCd, NSZC046001Constant.XX_MODE_UPDATE);
                    DS_CONTR_DTLTMsg tMsg = getDsContrDtlByKey(addBllgMtrList.get(i).dsContrDtlPk.getValue());
                    if (tMsg != null) {
                        setValue(pMsg.xxDsContrBllgMtrList.no(idx).svcMachMstrPk, tMsg.svcMachMstrPk);
                    }
                    idx++;
                }
            }
            // END 2017/07/11 K.Kojima [QC#19822,ADD]
            pMsg.xxDsContrBllgMtrList.setValidCount(idx);
        }

// START 2019/01/21 [QC#29782, DEL]
//        // START 2018/04/20 U.Kim [QC#23378(Sol320), ADD]
//        if (PROC_NEW.equals(process)) {
//            List<Map<String, Object>> segAllocInfoList = getSegAllocInfo(updDsContrTMsg, updDsContrDtlTMsgList, updDsContrDtlTMsgFltAgg);
//            idx = 0;
//            for (Map<String, Object> segAllocInfo : segAllocInfoList) {
//                setValue(pMsg.xxDsContrSegAllocList.no(idx).xxModeCd, NSZC046001Constant.CRUD_MODE_CREATE);
//                setValue(pMsg.xxDsContrSegAllocList.no(idx).svcInvChrgTpCd, SVC_INV_CHRG_TP.BASE_CHARGE);
//                setValue(pMsg.xxDsContrSegAllocList.no(idx).dsContrPk, (BigDecimal) segAllocInfo.get("DS_CONTR_PK"));
//                setValue(pMsg.xxDsContrSegAllocList.no(idx).dsContrDtlPk, (BigDecimal) segAllocInfo.get("DS_CONTR_DTL_PK"));
//                setValue(pMsg.xxDsContrSegAllocList.no(idx).coaCmpyCd, (String) segAllocInfo.get("COA_CMPY_CD"));
//                setValue(pMsg.xxDsContrSegAllocList.no(idx).coaAfflCd, (String) segAllocInfo.get("COA_AFFL_CD"));
//                setValue(pMsg.xxDsContrSegAllocList.no(idx).coaBrCd, (String) segAllocInfo.get("COA_BR_CD"));
//                setValue(pMsg.xxDsContrSegAllocList.no(idx).coaChCd, (String) segAllocInfo.get("COA_CH_CD"));
//                setValue(pMsg.xxDsContrSegAllocList.no(idx).coaCcCd, (String) segAllocInfo.get("COA_CC_CD"));
//                setValue(pMsg.xxDsContrSegAllocList.no(idx).coaAcctCd, (String) segAllocInfo.get("COA_ACCT_CD"));
//                setValue(pMsg.xxDsContrSegAllocList.no(idx).coaProdCd, (String) segAllocInfo.get("COA_PROD_CD"));
//                setValue(pMsg.xxDsContrSegAllocList.no(idx).coaProjCd, (String) segAllocInfo.get("COA_PROJ_CD"));
//                setValue(pMsg.xxDsContrSegAllocList.no(idx).coaExtnCd, (String) segAllocInfo.get("COA_EXTN_CD"));
//                setValue(pMsg.xxDsContrSegAllocList.no(idx).prcAllocAmt, (BigDecimal) segAllocInfo.get("PRC_ALLOC_AMT"));
//                idx++;
//            }
//            pMsg.xxDsContrSegAllocList.setValidCount(segAllocInfoList.size());
//        }
//        // END 2018/04/20 U.Kim [QC#23378(Sol320), ADD]
// END 2019/01/21 [QC#29782, DEL]
        // Add Start 2018/04/26 QC#25638
        if (PROC_INSTL_COMP.equals(process)) {
            for (int i = 0; i < pMsg.xxContrDtlList.getValidCount(); i++) {
                pMsg.xxContrDtlList.no(i).basePrcTermDealAmtRate.clear();
                pMsg.xxContrDtlList.no(i).basePrcTermFuncAmtRate.clear();
                // START 2018/05/09 K.Kitachi [QC#25728, ADD]
                DS_CONTR_DTLTMsg tMsg = getDsContrDtlByKey(pMsg.xxContrDtlList.no(i).dsContrDtlPk.getValue());
                if (tMsg == null) {
                    continue;
                }
                // 2018/05/16 QC#22482 Add Start
                if (!ZYPCommonFunc.hasValue(tMsg.baseBllgCycleCd)) {
                    continue;
                }
                // 2018/05/16 QC#22482 Add End
                int bllgCycleCnt = calcBllgCycleCntFromDuration(tMsg);
                if (bllgCycleCnt > 0 && hasValue(tMsg.basePrcDealAmt)) {
                    BigDecimal basePrcTermDealAmtRate = tMsg.basePrcDealAmt.getValue().multiply(BigDecimal.valueOf(bllgCycleCnt));
                    setValue(pMsg.xxContrDtlList.no(i).basePrcTermDealAmtRate, basePrcTermDealAmtRate);
                }
                // END 2018/05/09 K.Kitachi [QC#25728, ADD]
            }
        }
        // Add End 2018/04/26 QC#25638

        NSZC046001 api = new NSZC046001();
        api.execute(pMsg, ONBATCH_TYPE.BATCH);

        if (isErrorNSZC046001API(pMsg)) {
            return false;
        }
        return true;
    }

    // 3.Create Start Meter Read
    private boolean createStartMeterRead(List<Map<String, Object>> shellContrDtlList) {
        // add start 2017/11/16 QC#22422
        if (BATCH_MODE_NIGHTLY.equals(this.usrVar1)) {
            return true;
        }
        // add end 2017/11/16 QC#22422

        NSZC010001 api = new NSZC010001();
        for (int i = 0; i < shellContrDtlList.size(); i++) {
            Map<String, Object> shellContrDtl = shellContrDtlList.get(i);
            BigDecimal svcMachMstrPk = (BigDecimal) shellContrDtl.get("SVC_MACH_MSTR_PK");
            BigDecimal dsContrDtlPk = (BigDecimal) shellContrDtl.get("DS_CONTR_DTL_PK");
            // add start 2017/08/08 QC#18799
            String dsContrSrcTpCd = (String) shellContrDtl.get("DS_CONTR_SRC_TP_CD");
            String dsContrClsCd = (String) shellContrDtl.get("DS_CONTR_CLS_CD");
            String svcLineBizCd = (String) shellContrDtl.get("SVC_LINE_BIZ_CD");
            // add end 2017/08/08 QC#18799
            // START 2017/09/08 [QC#20096, ADD]
            String usgChrgFlg = (String) shellContrDtl.get("USG_CHRG_FLG");
            // START 2017/09/08 [QC#20096, ADD]
            // START 2017/10/24 U.Kim [QC#21864, ADD]
            String svcMachMstrStsCd = (String) shellContrDtl.get("SVC_MACH_MSTR_STS_CD");
            String cpoOrdNum = (String) shellContrDtl.get("CPO_ORD_NUM");
            // END 2017/10/24 U.Kim [QC#21864, ADD]

            // START 2022/06/22 E.Sanchez [QC#59804, ADD]
            CONTR_PHYS_BLLG_MTR_RELNTMsgArray relnTmsg = getContrPhysBllgMtrReln(this.glblCmpyCd, dsContrDtlPk);
            if (relnTmsg == null || relnTmsg.getValidCount() == 0) {
                continue;
            }
            // END 2022/06/22 E.Sanchez [QC#59804, ADD]

            // START 2017/09/08 [QC#20096, ADD]
            if (!ZYPConstant.FLG_ON_Y.equals(usgChrgFlg)) {
                continue;
            }
            // END   2017/09/08 [QC#20096, ADD]
            // START 2017/10/24 U.Kim [QC#21864, ADD]
            if (!SVC_MACH_MSTR_STS.INSTALLED.equals(svcMachMstrStsCd)) {
                continue;
            }
            // END 2017/10/24 U.Kim [QC#21864, ADD]

            DS_CONTR_DTLTMsg tMsg = getDsContrDtlByKey(dsContrDtlPk);
            if (tMsg == null) {
                S21InfoLogOutput.println(NSAM0045E, new String[] {"DS_CONTR_DTL" });
                return false;
            }

            // mod start 2017/08/08 QC#18799
            String nextBusinessDate = ZYPDateUtil.addBusinessDay(this.glblCmpyCd, this.salesDate, 1);
            if (ZYPDateUtil.compare(nextBusinessDate, tMsg.contrEffFromDt.getValue()) < 0) {
                continue;
            }

            String istlCallMtrReadUseFlg = ZYPConstant.FLG_OFF_N;
            DS_CONTR_INTFC_DEF_RULETMsg dsContrIntfcDefRuleTMsg = getDsContrIntfcDefRule(dsContrSrcTpCd, dsContrClsCd, svcLineBizCd);
            if (dsContrIntfcDefRuleTMsg != null) {
                // START 2017/09/15 K.Kim [QC#21162, ADD]
                if (!START_READ_VLD_TP.INSERT_READING.equals(dsContrIntfcDefRuleTMsg.startReadVldTpCd.getValue())) {
                    continue;
                }
                // END 2017/09/15 K.Kim [QC#21162, ADD]
                istlCallMtrReadUseFlg = dsContrIntfcDefRuleTMsg.istlCallMtrReadUseFlg.getValue();
            } else {
                // START 2017/10/24 U.Kim [QC#21864, ADD]
                continue;
                // END 2017/10/24 U.Kim [QC#21864, ADD]
            }

            List<Map<String, Object>> svcPhysMtrList = new ArrayList<Map<String, Object>>();
            String mtrReadDt = null;
            if (ZYPConstant.FLG_ON_Y.equals(istlCallMtrReadUseFlg)) {
                // START 2017/10/24 U.Kim [QC#21864, MOD]
                CPOTMsg cpo = getCpo(cpoOrdNum);
                svcPhysMtrList = getSvcPhysMtrIstlCall(svcMachMstrPk, dsContrDtlPk, cpo.cpoOrdTs.getValue());
                // END 2017/10/24 U.Kim [QC#21864, MOD]
                if (svcPhysMtrList.size() > 0) {
                    mtrReadDt = (String) svcPhysMtrList.get(0).get("MTR_READ_DT");
                }
            }

            if (svcPhysMtrList.size() == 0) {
                svcPhysMtrList = getSvcPhysMtr(svcMachMstrPk, dsContrDtlPk);
                if (svcPhysMtrList.size() == 0) {
                    continue;
                }
                // START 2017/09/21 K.Kim [QC#21226, MOD]
                // mtrReadDt = ZYPDateUtil.addDays(tMsg.contrEffFromDt.getValue(), -1);
                mtrReadDt = tMsg.contrEffFromDt.getValue();
                // END 2017/09/21 K.Kim [QC#21226, MOD]
            }
            // mod end 2017/08/08 QC#18799

            NSZC010001PMsg pMsg = new NSZC010001PMsg();
            setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(pMsg.mtrReadSrcTpCd, MTR_READ_SRC_TP.S21);
            setValue(pMsg.dsMtrReadTpCd, DS_MTR_READ_TP.ACTUAL);
            setValue(pMsg.slsDt, this.salesDate);
            setValue(pMsg.svcMachMstrPk, svcMachMstrPk);
            setValue(pMsg.dsContrDtlPk, dsContrDtlPk);
            setValue(pMsg.contrEffFromDt, tMsg.contrEffFromDt);
            setValue(pMsg.xxWrnSkipFlg, ZYPConstant.FLG_OFF_N);
            setValue(pMsg.xxStartReadFlg, ZYPConstant.FLG_ON_Y);
            setValue(pMsg.dsMtrReadTpGrpCd, DS_MTR_READ_TP_GRP.BILLABLE_READS);

            for (int j = 0; j < svcPhysMtrList.size(); j++) {
                Map<String, Object> svcPhysMtr = svcPhysMtrList.get(j);
                setValue(pMsg.A.no(j).mtrReadDt, mtrReadDt);
                setValue(pMsg.A.no(j).readMtrCnt, (BigDecimal) svcPhysMtr.get("READ_MTR_CNT"));
                setValue(pMsg.A.no(j).testMtrCnt, BigDecimal.ZERO);
                setValue(pMsg.A.no(j).svcPhysMtrPk, (BigDecimal) svcPhysMtr.get("SVC_PHYS_MTR_PK"));
                setValue(pMsg.A.no(j).vldMtrFlg, ZYPConstant.FLG_ON_Y);
            }
            pMsg.A.setValidCount(svcPhysMtrList.size());

            api.execute(pMsg, ONBATCH_TYPE.BATCH);
            if (S21ApiUtil.isXxMsgId(pMsg)) {
                outputLogForApi(pMsg);
                return false;
            }
        }
        return true;
    }

    private List<Map<String, Object>> getSvcPhysMtr(BigDecimal svcMachMstrPk, BigDecimal dsContrDtlPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("svcMachMstrPk", svcMachMstrPk);
        param.put("dsContrDtlPk", dsContrDtlPk);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getSvcPhysMtr", param);
    }

    // add start 2017/08/08 QC#18799
    // START 2017/10/24 U.Kim [QC#21864, MOD]
    // private List<Map<String, Object>> getSvcPhysMtrIstlCall(BigDecimal svcMachMstrPk, BigDecimal dsContrDtlPk) {
    private List<Map<String, Object>> getSvcPhysMtrIstlCall(BigDecimal svcMachMstrPk, BigDecimal dsContrDtlPk, String cpoOrdTs) {
    // END 2017/10/24 U.Kim [QC#21864, MOD]
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("svcMachMstrPk", svcMachMstrPk);
        param.put("dsContrDtlPk", dsContrDtlPk);
        param.put("fsrTpCd", FSR_TP.INSTALL_CALL);
        // START 2017/10/24 U.Kim [QC#21864, ADD]
        param.put("cpoOrdTs", cpoOrdTs);
        // END 2017/10/24 U.Kim [QC#21864, ADD]
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getSvcPhysMtrIstlCall", param);
    }
    // add end 2017/08/08 QC#18799

    // 4.QA Check
    private String validContract(BigDecimal dsContrPk) {
        DS_CONTRTMsg dsContrTMsg = getDsContrByKey(dsContrPk);
        if (dsContrTMsg == null) {
            S21InfoLogOutput.println(NSAM0045E, new String[] {"DS_CONTR" });
            return null;
        }

        NSZC057001PMsg pMsg = new NSZC057001PMsg();
        setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(pMsg.slsDt, this.salesDate);
        setValue(pMsg.dsContrNum, dsContrTMsg.dsContrNum);

        NSZC057001 api = new NSZC057001();
        api.execute(pMsg, ONBATCH_TYPE.BATCH);
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            outputLogForApi(pMsg);
            return null;
        }
        return pMsg.dsContrVldRsltMsgTxt.getValue();
    }

    // 5.Update Status
    private boolean updateStatus(BigDecimal dsContrPk, String vldRslt) {
        // Update DS_CONTR
        DS_CONTRTMsg dsContrTMsg = getDsContrByKeyForUpdate(dsContrPk);
        if (dsContrTMsg == null) {
            S21InfoLogOutput.println(NSAM0045E, new String[] {"DS_CONTR" });
            return false;
        }
        //START 2017/07/14 K.Kasai [QC#18780,MOD]
        // update DS_CONTR only when QA Hold flag is 'N'
        if (ZYPConstant.FLG_OFF_N.equals(dsContrTMsg.qltyAsrnHldFlg.getValue())) {

            //  START 2018/06/25 T.Wada [QC#24266, MOD]

//            if (NSZC057001Constant.SUCCESS.equals(vldRslt)) {
//                if (ZYPDateUtil.compare(dsContrTMsg.contrVrsnEffFromDt.getValue(), this.salesDate) <= 0) {
//                    setValue(dsContrTMsg.dsContrStsCd, DS_CONTR_STS.EFFECTIVE);
//                    setValue(dsContrTMsg.qltyAsrnHldFlg, ZYPConstant.FLG_OFF_N);
//                } else {
//                    setValue(dsContrTMsg.dsContrStsCd, DS_CONTR_STS.APPROVED);
//                    setValue(dsContrTMsg.qltyAsrnHldFlg, ZYPConstant.FLG_OFF_N);
//                }
//            //START 2017/07/12 K.Kasai [QC#18780,MOD]
////            } else if (NSZC057001Constant.ERROR.equals(vldRslt)) {
//            } else {
            //END 2017/07/12 K.Kasai [QC#18780,MOD]
                // START 2017/07/31 M.Kidokoro [QC#20116, MOD]
//                if (!DS_CONTR_STS.DRAFT.equals(dsContrTMsg.dsContrStsCd.getValue())) {
                if (!(DS_CONTR_STS.DRAFT.equals(dsContrTMsg.dsContrStsCd.getValue())
                        || DS_CONTR_STS.ENTERED.equals(dsContrTMsg.dsContrStsCd.getValue()))) {
                // END 2017/07/31 M.Kidokoro [QC#20116, MOD]
                    setValue(dsContrTMsg.qltyAsrnHldFlg, ZYPConstant.FLG_ON_Y);
                // START 2017/07/31 M.Kidokoro [QC#20116, MOD]
//                }
                } else if (DS_CONTR_STS.DRAFT.equals(dsContrTMsg.dsContrStsCd.getValue())) {
                    setValue(dsContrTMsg.dsContrStsCd, DS_CONTR_STS.ENTERED);
                }
                // END 2017/07/31 M.Kidokoro [QC#20116, MOD]
//            }
            //  END 2018/06/25 T.Wada [QC#24266, MOD]

            S21FastTBLAccessor.update(dsContrTMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dsContrTMsg.getReturnCode())) {
                S21InfoLogOutput.println(NSAM0031E, new String[] {"DS_CONTR" });
                return false;
            }

        }
        //END 2017/07/14 K.Kasai [QC#18780,MOD]

        // QC#25030
        boolean isAggregate = DS_CONTR_CATG.AGGREGATE.equals(dsContrTMsg.dsContrCatgCd.getValue());
        BigDecimal aggBllgRollOverRatio = null;
        if (isAggregate) {
            aggBllgRollOverRatio = getAggBllgRollOverRatio(dsContrTMsg.dsContrPk.getValue());
        }

        //START 2017/07/26 K.Kasai [QC#18882,ADD]
        //check Contract Validation result count without All Arvived Machine check
        BigDecimal dsContrVldPk = ZYPCodeDataUtil.getNumConstValue("FLT_ALL_MACH_ARV_CONTR_VLD_PK", this.glblCmpyCd);
        boolean isErrWithoutAllArvMachChk = isErrContrVldErrorWithoutAllArvMachChk(dsContrPk, dsContrVldPk);
        //END 2017/07/26 K.Kasai [QC#18882,ADD]
        // START 2017/09/22 A.Kohinata [QC#18534,ADD]
//        BigDecimal machInstlContrVldPk = ZYPCodeDataUtil.getNumConstValue("MACH_INSTL_CONTR_VLD_PK", this.glblCmpyCd);
//        boolean isErrWithoutMachInstlChk;
        // END 2017/09/22 A.Kohinata [QC#18534,ADD]
        List<BigDecimal> dsContrDtlPkList = getDsContrDtlPk(dsContrPk);
        for (int i = 0; i < dsContrDtlPkList.size(); i++) {
            BigDecimal dsContrDtlPk = dsContrDtlPkList.get(i);

            // Update DS_CONTR_DTL
            DS_CONTR_DTLTMsg dsContrDtlTMsg = getDsContrDtlByKeyForUpdate(dsContrDtlPk);
            if (dsContrDtlTMsg == null) {
                S21InfoLogOutput.println(NSAM0045E, new String[] {"DS_CONTR_DTL" });
                return false;
            }
            String dsContrDtlStsCd = dsContrDtlTMsg.dsContrDtlStsCd.getValue();
            String qltyAsrnHldFlg = dsContrDtlTMsg.qltyAsrnHldFlg.getValue();
            // START 2017/09/22 A.Kohinata [QC#18534,ADD]
//            isErrWithoutMachInstlChk = isErrContrVldErrorWithoutMachInstlChk(dsContrPk, dsContrDtlPk, machInstlContrVldPk);
            // END 2017/09/22 A.Kohinata [QC#18534,ADD]
            //START 2017/07/12 K.Kasai [QC#18780,ADD]
            // update DS_CONTR_XXX only when QA Hold flag is 'N'
            if (ZYPConstant.FLG_ON_Y.equals(qltyAsrnHldFlg)) {
                continue;
            }
            //END 2017/07/12 K.Kasai [QC#18780,ADD]

            //  START 2018/06/25 T.Wada [QC#24266, MOD]
//            if (NSZC057001Constant.SUCCESS.equals(vldRslt)) {
//                if (ZYPDateUtil.compare(dsContrDtlTMsg.contrEffFromDt.getValue(), this.salesDate) <= 0) {
//                    dsContrDtlStsCd = DS_CONTR_DTL_STS.ACTIVE;
//                    qltyAsrnHldFlg = ZYPConstant.FLG_OFF_N;
//                } else {
//                    dsContrDtlStsCd = DS_CONTR_DTL_STS.SIGNED;
//                    qltyAsrnHldFlg = ZYPConstant.FLG_OFF_N;
//                }
//            //START 2017/07/12 K.Kasai [QC#18780,MOD]
////            } else if (NSZC057001Constant.ERROR.equals(vldRslt)) {
//            // START 2017/09/22 A.Kohinata [QC#18534,MOD]
//            } else if (!isErrWithoutAllArvMachChk) {
////            } else if (!isErrWithoutAllArvMachChk && !isErrWithoutMachInstlChk) {
//            // END 2017/09/22 A.Kohinata [QC#18534,MOD]
//                dsContrDtlStsCd = DS_CONTR_DTL_STS.SIGNED;
//                qltyAsrnHldFlg = ZYPConstant.FLG_OFF_N;
//            } else {
                //END 2017/07/12 K.Kasai [QC#18780,MOD]
                // START 2017/07/31 M.Kidokoro [QC#20116, MOD]
//                if (!DS_CONTR_DTL_STS.SAVED.equals(dsContrDtlStsCd)) {
                if (!DS_CONTR_DTL_STS.SAVED.equals(dsContrDtlStsCd) && !DS_CONTR_DTL_STS.SUBMITED.equals(dsContrDtlStsCd)) {
                    // END 2017/07/31 M.Kidokoro [QC#20116, MOD]
                    qltyAsrnHldFlg = ZYPConstant.FLG_ON_Y;
                    // START 2017/07/31 M.Kidokoro [QC#20116, MOD]
//                    }
                } else if (DS_CONTR_DTL_STS.SAVED.equals(dsContrDtlStsCd)) {
                    dsContrDtlStsCd = DS_CONTR_DTL_STS.SUBMITED;
                }
                // END 2017/07/31 M.Kidokoro [QC#20116, MOD]
//            }
            //  END 2018/06/25 T.Wada [QC#24266, MOD]
            setValue(dsContrDtlTMsg.dsContrDtlStsCd, dsContrDtlStsCd);
            setValue(dsContrDtlTMsg.qltyAsrnHldFlg, qltyAsrnHldFlg);
            S21FastTBLAccessor.update(dsContrDtlTMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dsContrDtlTMsg.getReturnCode())) {
                S21InfoLogOutput.println(NSAM0031E, new String[] {"DS_CONTR_DTL" });
                return false;
            }

            // Update DS_CONTR_BLLG_MTR
            List<BigDecimal> dsContrBllgMtPkList = getDsContrBllgMtrPk(dsContrDtlPk);
            for (int j = 0; j < dsContrBllgMtPkList.size(); j++) {
                BigDecimal dsContrBllgMtPk = dsContrBllgMtPkList.get(j);
                DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg = getDsContrBllgMtrByKeyForUpdate(dsContrBllgMtPk);
                if (dsContrBllgMtrTMsg == null) {
                    S21InfoLogOutput.println(NSAM0045E, new String[] {"DS_CONTR_BLLG_MTR" });
                    return false;
                }
                setValue(dsContrBllgMtrTMsg.dsContrBllgMtrStsCd, dsContrDtlStsCd);
                setValue(dsContrBllgMtrTMsg.qltyAsrnHldFlg, qltyAsrnHldFlg);
                // QC#25030
                if (isAggregate) {
                    if (DS_CONTR_DTL_TP.BASE_AND_USAGE.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
                        dsContrBllgMtrTMsg.bllgFreeCopyCnt.clear();
                        if (ZYPCommonFunc.hasValue(aggBllgRollOverRatio)) {
                            ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.bllgRollOverRatio, aggBllgRollOverRatio);
                        }
                    }
                }
                S21FastTBLAccessor.update(dsContrBllgMtrTMsg);
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dsContrBllgMtrTMsg.getReturnCode())) {
                    S21InfoLogOutput.println(NSAM0031E, new String[] {"DS_CONTR_BLLG_MTR" });
                    return false;
                }
            }

            // Update DS_CONTR_PRC_EFF
            List<BigDecimal> dsContrPrcEffPkList = getDsContrPrcEffPk(dsContrDtlPk);
            for (int j = 0; j < dsContrPrcEffPkList.size(); j++) {
                BigDecimal dsContrPrcEffPk = dsContrPrcEffPkList.get(j);
                DS_CONTR_PRC_EFFTMsg dsContrPrcEffTMsg = getDsContrPrcEffByKeyForUpdate(dsContrPrcEffPk);
                if (dsContrPrcEffTMsg == null) {
                    S21InfoLogOutput.println(NSAM0045E, new String[] {"DS_CONTR_PRC_EFF" });
                    return false;
                }
                setValue(dsContrPrcEffTMsg.dsContrPrcEffStsCd, dsContrDtlStsCd);
                setValue(dsContrPrcEffTMsg.qltyAsrnHldFlg, qltyAsrnHldFlg);
                S21FastTBLAccessor.update(dsContrPrcEffTMsg);
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dsContrPrcEffTMsg.getReturnCode())) {
                    S21InfoLogOutput.println(NSAM0031E, new String[] {"DS_CONTR_PRC_EFF" });
                    return false;
                }
            }
        }
        return true;
    }

    private BigDecimal getAggBllgRollOverRatio(BigDecimal dsContrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("aggregate", DS_CONTR_DTL_TP.AGGREGATE);
        return (BigDecimal) this.ssmBatchClient.queryObject("getAggBllgRollOverRatio", param);
    }

    private List<BigDecimal> getDsContrDtlPk(BigDecimal dsContrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        String[] dsContrCtrlStsCdList = new String[] {DS_CONTR_CTRL_STS.TERMINATED, DS_CONTR_CTRL_STS.EXPIRED, DS_CONTR_CTRL_STS.CANCELLED, DS_CONTR_CTRL_STS.ORDER };
        param.put("dsContrCtrlStsCdList", dsContrCtrlStsCdList);
        return (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getDsContrDtlPk", param);
    }

    private List<BigDecimal> getDsContrBllgMtrPk(BigDecimal dsContrDtlPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrDtlPk", dsContrDtlPk);
        String[] dsContrCtrlStsCdList = new String[] {DS_CONTR_CTRL_STS.TERMINATED, DS_CONTR_CTRL_STS.EXPIRED, DS_CONTR_CTRL_STS.CANCELLED, DS_CONTR_CTRL_STS.ORDER };
        param.put("dsContrCtrlStsCdList", dsContrCtrlStsCdList);
        return (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getDsContrBllgMtrPk", param);
    }

    private List<BigDecimal> getDsContrPrcEffPk(BigDecimal dsContrDtlPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrDtlPk", dsContrDtlPk);
        String[] dsContrCtrlStsCd = new String[] {DS_CONTR_CTRL_STS.TERMINATED, DS_CONTR_CTRL_STS.EXPIRED, DS_CONTR_CTRL_STS.CANCELLED, DS_CONTR_CTRL_STS.ORDER };
        param.put("dsContrCtrlStsList", dsContrCtrlStsCd);
        return (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getDsContrPrcEffPk", param);
    }

    // 6.Contract Tracking
    private boolean createContrTrk(BigDecimal dsContrPk) {
        if (!NSXC001001ContractTracking.callContrTrk(this.glblCmpyCd, ContrTrkProcMode.CONTRACT_MODE_CHANGE.code, dsContrPk, this.userId, this.salesDate, null, null, ONBATCH_TYPE.BATCH)) {
            S21InfoLogOutput.println(NSXC001001ContractTracking.ERR_MSG_ID);
            return false;
        }
        return true;
    }

    // 7.Create Order Schedule(OM)
    private void createOrdSch(BigDecimal dsContrPk) {
        NWZC172001 api = new NWZC172001();
        List<Map<String, Object>> ordSchInfoList = getOrdSchInfo(dsContrPk);
        for (Map<String, Object> ordSchInfo : ordSchInfoList) {
            NWZC172001PMsg nwzc172001PMsg = new NWZC172001PMsg();
            setValue(nwzc172001PMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(nwzc172001PMsg.slsDt, this.salesDate);
            setValue(nwzc172001PMsg.xxRqstTpCd, NWZC172001Constant.RQST_TP_SCHD);
            setValue(nwzc172001PMsg.dsContrPk, dsContrPk);
            setValue(nwzc172001PMsg.refCpoOrdNum, (String) ordSchInfo.get("CPO_ORD_NUM"));
            setValue(nwzc172001PMsg.mdlId, (BigDecimal) ordSchInfo.get("MDL_ID"));
            setValue(nwzc172001PMsg.serNum, (String) ordSchInfo.get("SER_NUM"));
            setValue(nwzc172001PMsg.svcMachMstrPk, (BigDecimal) ordSchInfo.get("SVC_MACH_MSTR_PK"));
            setValue(nwzc172001PMsg.svcConfigMstrPk, (BigDecimal) ordSchInfo.get("SVC_CONFIG_MSTR_PK"));
            setValue(nwzc172001PMsg.dsContrNum, (String) ordSchInfo.get("DS_CONTR_NUM"));
            setValue(nwzc172001PMsg.dsContrSqNum, (String) ordSchInfo.get("DS_CONTR_SQ_NUM"));
            setValue(nwzc172001PMsg.schdAgmtVldFromDt, (String) ordSchInfo.get("CONTR_EFF_FROM_DT"));
            setValue(nwzc172001PMsg.schdAgmtVldThruDt, (String) ordSchInfo.get("CONTR_EFF_THRU_DT"));
            setValue(nwzc172001PMsg.dsContrDtlPk, (BigDecimal) ordSchInfo.get("DS_CONTR_DTL_PK"));

            List<NWZC172002PMsg> nwzc172002PMsgList = new ArrayList<NWZC172002PMsg>();
            api.execute(nwzc172001PMsg, nwzc172002PMsgList, ONBATCH_TYPE.BATCH);
            if (S21ApiUtil.isXxMsgId(nwzc172001PMsg)) {
                outputLogForApi(nwzc172001PMsg);
            }
        }
    }

    // 8.Update Conversion Order Status
    private boolean updateConvOrdStatus(String cpoOrdNum) {
        List<Map<String, Object>> convOrdDtlList = getConvOrdByCpoOrdNum(cpoOrdNum);

        for (int i = 0; i < convOrdDtlList.size(); i++) {
            Map<String, Object> dsCpoDtl = convOrdDtlList.get(i);

            CPO_DTLTMsg tMsg = getCpoDtlByKeyForUpdate(cpoOrdNum, (String) dsCpoDtl.get("CPO_DTL_LINE_NUM"), (String) dsCpoDtl.get("CPO_DTL_LINE_SUB_NUM"));
            if (tMsg == null) {
                S21InfoLogOutput.println(NSAM0045E, new String[] {"CPO_DTL" });
                return false;
            }
            setValue(tMsg.convProcStsCd, CONV_PROC_STS_PROCESSED);

            S21FastTBLAccessor.update(tMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                S21InfoLogOutput.println(NSAM0031E, new String[] {"CPO_DTL" });
                return false;
            }
        }
        return true;
    }

    private List<Map<String, Object>> getConvOrdByCpoOrdNum(String cpoOrdNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("inProcess", CONV_PROC_STS_IN_PROCESS);
        param.put("cpoOrdNum", cpoOrdNum);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getConvOrdByCpoOrdNum", param);
    }

    private List<Map<String, Object>> getOrdSchInfo(BigDecimal dsContrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("machine", SVC_MACH_TP.MACHINE);
        // START 2017/10/24 U.Kim [QC#21865, ADD]
        param.put("installed", SVC_MACH_MSTR_STS.INSTALLED);
        param.put("dsContrCatgCdWty", DS_CONTR_CATG.WARRANTY);
        // END 2017/10/24 U.Kim [QC#21865, ADD]
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getOrdSchInfo", param);
    }

    private DS_CONTRTMsg getDsContrByKey(BigDecimal dsContrPk) {
        DS_CONTRTMsg prmTMsg = new DS_CONTRTMsg();
        setValue(prmTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(prmTMsg.dsContrPk, dsContrPk);
        return (DS_CONTRTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }

    private DS_CONTRTMsg getDsContrByKeyForUpdate(BigDecimal dsContrPk) {
        DS_CONTRTMsg prmTMsg = new DS_CONTRTMsg();
        setValue(prmTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(prmTMsg.dsContrPk, dsContrPk);
        return (DS_CONTRTMsg) S21FastTBLAccessor.findByKeyForUpdate(prmTMsg);
    }

    private DS_CONTR_DTLTMsg getDsContrDtlByKey(BigDecimal dsContrDtlPk) {
        DS_CONTR_DTLTMsg prmTMsg = new DS_CONTR_DTLTMsg();
        setValue(prmTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(prmTMsg.dsContrDtlPk, dsContrDtlPk);
        return (DS_CONTR_DTLTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }

    private DS_CONTR_DTLTMsg getDsContrDtlByKeyForUpdate(BigDecimal dsContrDtlPk) {
        DS_CONTR_DTLTMsg prmTMsg = new DS_CONTR_DTLTMsg();
        setValue(prmTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(prmTMsg.dsContrDtlPk, dsContrDtlPk);
        return (DS_CONTR_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdate(prmTMsg);
    }

    private DS_CONTR_BLLG_MTRTMsg getDsContrBllgMtrByKeyForUpdate(BigDecimal dsContrBllgMtrPk) {
        DS_CONTR_BLLG_MTRTMsg prmTMsg = new DS_CONTR_BLLG_MTRTMsg();
        setValue(prmTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(prmTMsg.dsContrBllgMtrPk, dsContrBllgMtrPk);
        return (DS_CONTR_BLLG_MTRTMsg) S21FastTBLAccessor.findByKeyForUpdate(prmTMsg);
    }

    private DS_CONTR_PRC_EFFTMsg getDsContrPrcEffByKeyForUpdate(BigDecimal dsContrPrcEffPk) {
        DS_CONTR_PRC_EFFTMsg prmTMsg = new DS_CONTR_PRC_EFFTMsg();
        setValue(prmTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(prmTMsg.dsContrPrcEffPk, dsContrPrcEffPk);
        return (DS_CONTR_PRC_EFFTMsg) S21FastTBLAccessor.findByKeyForUpdate(prmTMsg);
    }

    private CPO_DTLTMsg getCpoDtlByKeyForUpdate(String cpoOrdNum, String cpoDtlLineNum, String cpoDtlLineSubNum) {
        CPO_DTLTMsg prmTMsg = new CPO_DTLTMsg();
        setValue(prmTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(prmTMsg.cpoOrdNum, cpoOrdNum);
        setValue(prmTMsg.cpoDtlLineNum, cpoDtlLineNum);
        setValue(prmTMsg.cpoDtlLineSubNum, cpoDtlLineSubNum);
        return (CPO_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdate(prmTMsg);
    }

    private DS_CONTR_BLLG_MTRTMsgArray getDsContrBllgMtrByDsContrDtlPkForUpdate(BigDecimal dsContrDtlPk) {
        DS_CONTR_BLLG_MTRTMsg prmTMsg = new DS_CONTR_BLLG_MTRTMsg();
        prmTMsg.setSQLID("001");
        prmTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        prmTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        return (DS_CONTR_BLLG_MTRTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(prmTMsg);
    }

    // START 2017/09/22 A.Kohinata [QC#18534,ADD]
    private DS_CONTR_BLLG_MTRTMsgArray getDsContrBllgMtrByDsContrDtlPk(BigDecimal dsContrDtlPk) {
        DS_CONTR_BLLG_MTRTMsg prmTMsg = new DS_CONTR_BLLG_MTRTMsg();
        prmTMsg.setSQLID("001");
        prmTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        prmTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        return (DS_CONTR_BLLG_MTRTMsgArray) EZDTBLAccessor.findByCondition(prmTMsg);
    }
    // END 2017/09/22 A.Kohinata [QC#18534,ADD]

    private CONTR_PHYS_BLLG_MTR_RELNTMsgArray getContrPhysBllgMtrRelnByDsContrDtlPkForUpdate(BigDecimal dsContrDtlPk) {
        CONTR_PHYS_BLLG_MTR_RELNTMsg prmTMsg = new CONTR_PHYS_BLLG_MTR_RELNTMsg();
        prmTMsg.setSQLID("001");
        prmTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        prmTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        return (CONTR_PHYS_BLLG_MTR_RELNTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(prmTMsg);
    }

    private DS_CONTR_ADDL_CHRGTMsgArray getDsContrAddlChrgByDsContrDtlPkForUpdate(BigDecimal dsContrDtlPk) {
        DS_CONTR_ADDL_CHRGTMsg prmTMsg = new DS_CONTR_ADDL_CHRGTMsg();
        prmTMsg.setSQLID("001");
        prmTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        prmTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        return (DS_CONTR_ADDL_CHRGTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(prmTMsg);
    }

    private SVC_TERM_COND_ATTRBTMsgArray getSvcTermCondAttrb(String svcTermCondAttrbNm) {
        SVC_TERM_COND_ATTRBTMsg prmTMsg = new SVC_TERM_COND_ATTRBTMsg();
        prmTMsg.setSQLID("001");
        prmTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        prmTMsg.setConditionValue("svcTermCondAttrbNm01", svcTermCondAttrbNm);
        return (SVC_TERM_COND_ATTRBTMsgArray) EZDTBLAccessor.findByCondition(prmTMsg);
    }

    private SVC_TERM_CONDTMsgArray getSvcTermCondForUpdate(BigDecimal dsContrPk, BigDecimal dsContrDtlPk, BigDecimal svcTermCondAttrbPk) {
        SVC_TERM_CONDTMsg prmTMsg = new SVC_TERM_CONDTMsg();
        prmTMsg.setSQLID("001");
        prmTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        prmTMsg.setConditionValue("dsContrPk01", dsContrPk);
        prmTMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        prmTMsg.setConditionValue("svcTermCondAttrbPk01", svcTermCondAttrbPk);
        return (SVC_TERM_CONDTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(prmTMsg);
    }

    // START 2018/02/23 K.Kojima [QC#21685,ADD]
    private SVC_TERM_CONDTMsgArray getSvcTermCondForUpdate(BigDecimal dsContrPk, BigDecimal svcTermCondAttrbPk) {
        SVC_TERM_CONDTMsg prmTMsg = new SVC_TERM_CONDTMsg();
        prmTMsg.setSQLID("002");
        prmTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        prmTMsg.setConditionValue("dsContrPk01", dsContrPk);
        prmTMsg.setConditionValue("svcTermCondAttrbPk01", svcTermCondAttrbPk);
        return (SVC_TERM_CONDTMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(prmTMsg);
    }
    // END 2018/02/23 K.Kojima [QC#21685,ADD]

    //START 2017/07/26 K.Kasai [QC#18882,ADD]
    private int getActStsRcdInDsContrDtlCnt(BigDecimal dsContrPk) {
        DS_CONTR_DTLTMsg prmTMsg = new DS_CONTR_DTLTMsg();
        prmTMsg.setSQLID("004");
        prmTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        prmTMsg.setConditionValue("dsContrPk01", dsContrPk);
        prmTMsg.setConditionValue("dsContrDtlStsCd01", DS_CONTR_DTL_STS.ACTIVE);
        return EZDTBLAccessor.count(prmTMsg);
    }

    private List<Map<String, BigDecimal>> getDsContrDtlFromDtIsNotNull(BigDecimal dsContrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        return (List<Map<String, BigDecimal>>) this.ssmBatchClient.queryObjectList("getDsContrDtlFromDtIsNotNull", param);
    }
    //END 2017/07/26 K.Kasai [QC#18882,ADD]

    private boolean isErrorNSZC046001API(NSZC046001PMsg pMsg) {
        boolean errorFlg = false;
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            outputLogForApi(pMsg);
            errorFlg = true;
        }
        if (hasValue(pMsg.xxMsgId_HD)) {
            S21InfoLogOutput.println(pMsg.xxMsgId_HD.getValue() + SPACE + pMsg.xxDsMultMsgDplyTxt_HD.getValue());
            errorFlg = true;
        }
        for (int j = 0; j < pMsg.xxContrDtlList.getValidCount(); j++) {
            NSZC046001_xxContrDtlListPMsg dtlMsg = pMsg.xxContrDtlList.no(j);
            if (hasValue(dtlMsg.xxMsgId_DT)) {
                S21InfoLogOutput.println(dtlMsg.xxMsgId_DT.getValue() + SPACE + dtlMsg.xxDsMultMsgDplyTxt_DT.getValue());
                errorFlg = true;
            }
        }
        for (int j = 0; j < pMsg.xxDsContrBllgMtrList.getValidCount(); j++) {
            NSZC046001_xxDsContrBllgMtrListPMsg bllgMtrMsg = pMsg.xxDsContrBllgMtrList.no(j);
            if (hasValue(bllgMtrMsg.xxMsgId)) {
                S21InfoLogOutput.println(bllgMtrMsg.xxMsgId.getValue() + SPACE + bllgMtrMsg.xxDsMultMsgDplyTxt.getValue());
                errorFlg = true;
            }
        }
        // START 2018/04/26 U.Kim [QC#23378(Sol320), ADD]
        for (int j = 0; j < pMsg.xxDsContrSegAllocList.getValidCount(); j++) {
            NSZC046001_xxDsContrSegAllocListPMsg segAllocMsg = pMsg.xxDsContrSegAllocList.no(j);
            if (hasValue(segAllocMsg.xxMsgId)) {
                S21InfoLogOutput.println(segAllocMsg.xxMsgId.getValue() + SPACE + segAllocMsg.xxDsMultMsgDplyTxt.getValue());
                errorFlg = true;
            }
        }
        // END 2018/04/26 U.Kim [QC#23378(Sol320), ADD]
        return errorFlg;
    }

    private void outputLogForApi(EZDPMsg pMsg) {
        List<S21ApiMessage> apiMsgList = S21ApiUtil.getXxMsgList(pMsg);
        for (S21ApiMessage apiMsg : apiMsgList) {
            S21InfoLogOutput.println(apiMsg.getXxMsgid(), apiMsg.getXxMsgPrmArray());
        }
    }

    // START 2017/06/17 K.Kitachi [QC#18177, ADD]
    private String getContrEffFromDt(String contrEffFromDt, String maxContrEffThruDt) {
        if (!hasValue(maxContrEffThruDt)) {
            return contrEffFromDt;
        }
        if (ZYPDateUtil.compare(contrEffFromDt, maxContrEffThruDt) > 0) {
            return contrEffFromDt;
        }
        return ZYPDateUtil.addDays(maxContrEffThruDt, 1);
    }

    private String getMaxContrEffThruDt(String cpoOrdNum, BigDecimal svcMachMstrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", cpoOrdNum);
        param.put("svcMachMstrPk", svcMachMstrPk);
        param.put("dsContrCatg", DS_CONTR_CATG.WARRANTY);
        return (String) this.ssmBatchClient.queryObject("getMaxContrEffThruDt", param);
    }
    // END 2017/06/17 K.Kitachi [QC#18177, ADD]

    // add start 2017/06/27 QC#18525
    // mod start 2017/08/08 QC#18799
    private DS_CONTR_INTFC_DEF_RULETMsg getDsContrIntfcDefRule(String dsContrSrcTpCd, String dsContrClsCd, String svcLineBizCd) {
        DS_CONTR_INTFC_DEF_RULETMsg param = new DS_CONTR_INTFC_DEF_RULETMsg();
        // START 2017/07/26 K.Kojima [QC#20090,MOD]
        // param.setSQLID("001");
        //param.setSQLID("201");
        // END 2017/07/26 K.Kojima [QC#20090,MOD]
        param.setSQLID("202");
        param.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        param.setConditionValue("contrIntfcSrcTpCd01", dsContrSrcTpCd);
        param.setConditionValue("dsContrClsCd01", dsContrClsCd);
        param.setConditionValue("svcLineBizCd01", svcLineBizCd);
        param.setConditionValue("effFromDt01", this.salesDate);
        param.setConditionValue("enblFlg01", ZYPConstant.FLG_ON_Y);
        DS_CONTR_INTFC_DEF_RULETMsgArray list = (DS_CONTR_INTFC_DEF_RULETMsgArray) EZDTBLAccessor.findByCondition(param);
        if (list.getValidCount() == 0) {
            return null;
        }
        return (DS_CONTR_INTFC_DEF_RULETMsg) list.get(0);
    }
    // mod end 2017/08/08 QC#18799

    private String getFirstMonth(String date) {
        String year = date.substring(0, INT_4);
        String month = date.substring(INT_4, INT_6);
        String day = date.substring(INT_6, INT_8);

        Calendar firstCal = Calendar.getInstance();
        firstCal.set(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(day));

        int firstDay = firstCal.getActualMinimum(Calendar.DATE);
        firstCal.set(Calendar.DATE, firstDay);
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        return sf.format(firstCal.getTimeInMillis());
    }

    private String getEndMonth(String date) {
        String year = date.substring(0, INT_4);
        String month = date.substring(INT_4, INT_6);
        String day = date.substring(INT_6, INT_8);

        Calendar endCal = Calendar.getInstance();
        endCal.set(Integer.parseInt(year), Integer.parseInt(month) - 1, Integer.parseInt(day));

        int endDay = endCal.getActualMaximum(Calendar.DATE);
        endCal.set(Calendar.DATE, endDay);
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        return sf.format(endCal.getTimeInMillis());
    }
    // add end 2017/06/27 QC#18525

    // add start 2017/07/10 QC#19818
    private BigDecimal getBasePrcTermDealAmtRate(BigDecimal dsContrDtlPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrDtlPk", dsContrDtlPk);
        param.put("baseBllgCycleCd", BLLG_CYCLE.CONTRACT_PERIOD);
        return (BigDecimal) this.ssmBatchClient.queryObject("getBasePrcTermDealAmtRate", param);
    }

    // START 2017/07/11 K.Kojima [QC#19822,MOD]
    // private BigDecimal getBasePrcTermDealAmtRateForFltLine(BigDecimal dsContrDtlPk, BigDecimal basePrcDealAmt) {
    private BigDecimal getBasePrcTermDealAmtRateForFltAggLine(BigDecimal dsContrDtlPk, BigDecimal basePrcDealAmt) {
    // END 2017/07/11 K.Kojima [QC#19822,MOD]
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrDtlPk", dsContrDtlPk);
        param.put("basePrcDealAmt", basePrcDealAmt);
        param.put("baseBllgCycleCd", BLLG_CYCLE.CONTRACT_PERIOD);
        // START 2017/07/11 K.Kojima [QC#19822,MOD]
        // return (BigDecimal) this.ssmBatchClient.queryObject("getBasePrcTermDealAmtRateForFltLine", param);
        return (BigDecimal) this.ssmBatchClient.queryObject("getBasePrcTermDealAmtRateForFltAggLine", param);
        // END 2017/07/11 K.Kojima [QC#19822,MOD]
    }
    // add start 2017/07/10 QC#19818

    // START 2017/07/11 K.Kojima [QC#19822,ADD]
    private List<DS_CONTR_DTLTMsg> getActiveDetailList(BigDecimal dsContrPk, BigDecimal[] dsContrDtlPkList) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        //Add Start 2017/11/14 QC#22526
        param.put("accessories", DS_CONTR_DTL_TP.ACCESSORIES);
        //Add End   2017/11/14 QC#22526
        String[] dsContrDtlTpCdList = new String[] {DS_CONTR_DTL_TP.PRICE_OF_MODEL_LEVEL, DS_CONTR_DTL_TP.AGGREGATE };
        param.put("dsContrDtlTpCdList", dsContrDtlTpCdList);
        String[] dsContrDtlStsCdList = new String[] {DS_CONTR_DTL_STS.ORDER, DS_CONTR_DTL_STS.EXPIRED, DS_CONTR_DTL_STS.CANCELLED, DS_CONTR_DTL_STS.TERMINATED };
        param.put("dsContrDtlStsCdList", dsContrDtlStsCdList);
        param.put("dsContrDtlPkList", dsContrDtlPkList);
        List<BigDecimal> pkList = (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getActiveDetailList", param);
        if (pkList == null || pkList.size() == 0) {
            return null;
        }
        List<DS_CONTR_DTLTMsg> activeDetailList = new ArrayList<DS_CONTR_DTLTMsg>(pkList.size());
        for (int i = 0; i < pkList.size(); i++) {
            DS_CONTR_DTLTMsg tMsg = new DS_CONTR_DTLTMsg();
            setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(tMsg.dsContrDtlPk, (BigDecimal) pkList.get(i));
            tMsg = (DS_CONTR_DTLTMsg) S21FastTBLAccessor.findByKey(tMsg);
            if (tMsg != null) {
                activeDetailList.add(tMsg);
            }
        }
        return activeDetailList;
    }
    // END 2017/07/11 K.Kojima [QC#19822,ADD]

    // START 2017/07/11 K.Kojima [QC#19822,ADD]
    List<DS_CONTR_BLLG_MTRTMsg> getActiveBllgMtrList(BigDecimal dsContrPk, BigDecimal[] dsContrDtlPkList) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        String[] dsContrDtlTpCdList = new String[] {DS_CONTR_DTL_TP.PRICE_OF_MODEL_LEVEL, DS_CONTR_DTL_TP.AGGREGATE };
        param.put("dsContrDtlTpCdList", dsContrDtlTpCdList);
        String[] dsContrDtlStsCdList = new String[] {DS_CONTR_DTL_STS.ORDER, DS_CONTR_DTL_STS.EXPIRED, DS_CONTR_DTL_STS.CANCELLED, DS_CONTR_DTL_STS.TERMINATED };
        param.put("dsContrDtlStsCdList", dsContrDtlStsCdList);
        param.put("dsContrDtlPkList", dsContrDtlPkList);
        List<BigDecimal> pkList = (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getActiveBllgMtrList", param);
        if (pkList == null || pkList.size() == 0) {
            return null;
        }
        List<DS_CONTR_BLLG_MTRTMsg> activeBllgMtrList = new ArrayList<DS_CONTR_BLLG_MTRTMsg>(pkList.size());
        for (int i = 0; i < pkList.size(); i++) {
            DS_CONTR_BLLG_MTRTMsg tMsg = new DS_CONTR_BLLG_MTRTMsg();
            setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            setValue(tMsg.dsContrBllgMtrPk, (BigDecimal) pkList.get(i));
            tMsg = (DS_CONTR_BLLG_MTRTMsg) S21FastTBLAccessor.findByKey(tMsg);
            if (tMsg != null) {
                activeBllgMtrList.add(tMsg);
            }
        }
        return activeBllgMtrList;
    }
    // END 2017/07/11 K.Kojima [QC#19822,ADD]
    
    // START 2017/08/28 [QC#20665, ADD]
    private boolean updateSvcContrRefCmntTxt(BigDecimal dsContrPk, List<Map<String, Object>> shellContrDtlList, String vldRslt) 
    {
        DS_CONTRTMsg dsContrTMsg = getDsContrByKeyForUpdate(dsContrPk);
        if (dsContrTMsg == null) {
            S21InfoLogOutput.println(NSAM0045E, new String[] {"DS_CONTR" });
            return false;
        }

        StringBuilder builder = new StringBuilder();
        if (hasValue(dsContrTMsg.svcContrRefCmntTxt)) {
            builder.append(dsContrTMsg.svcContrRefCmntTxt.getValue());
        }

        for (Map<String, Object> shellContrDtl : shellContrDtlList) {
            String addContrFlg = (String) shellContrDtl.get("ADD_CONTR_FLG");
            if (!ZYPConstant.FLG_ON_Y.equals(addContrFlg)) {
                continue;
            }

            String cpoOrdNum = (String) shellContrDtl.get("CPO_ORD_NUM");
            if (builder.indexOf(cpoOrdNum) != -1) {
                continue;
            }

            if (builder.length() > 0) {
                builder.append(SPACE);
                builder.append(DELIMITER_SRC_REF_CMNT_TXT);
                builder.append(SPACE);
            }
            builder.append(cpoOrdNum);
        }

        String svcContrRefCmntTxt = builder.toString();

        setValue(dsContrTMsg.svcContrRefCmntTxt, svcContrRefCmntTxt);
        S21FastTBLAccessor.update(dsContrTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dsContrTMsg.getReturnCode())) {
            S21InfoLogOutput.println(NSAM0031E, new String[] {"DS_CONTR" });
            return false;
        }

        return true;
    }
    // END   2017/08/28 [QC#20665, ADD]
    // START 2017/09/22 A.Kohinata [QC#18534,ADD]
    private boolean createWaitIstlContr(List<Map<String, Object>> shellContrDtlList) {
        if (!BATCH_MODE_DAILY.equals(this.usrVar1)) {
            return true;
        }

        BigDecimal dsContrDtlPk;
        DS_CONTR_DTLTMsg dsContrDtlTMsg;
        WAIT_ISTL_CONTRTMsg waitIstlContrTMsg;
        // del start 2017/10/06 QC#21639
//        String fsrNum;
        // del end 2017/10/06 QC#21639
        for (Map<String, Object> shellContr : shellContrDtlList) {
            dsContrDtlPk = (BigDecimal) shellContr.get("DS_CONTR_DTL_PK");
            if (!hasValue(dsContrDtlPk)) {
                continue;
            }
            dsContrDtlTMsg = getDsContrDtlByKey(dsContrDtlPk);
            if (dsContrDtlTMsg == null || !hasValue(dsContrDtlTMsg.dsContrDtlStsCd)) {
                continue;
            }

            // Check Status
            if (DS_CONTR_DTL_STS.ACTIVE.equals(dsContrDtlTMsg.dsContrDtlStsCd.getValue())) {
                continue;
            }

            waitIstlContrTMsg = getWaitIstlContr(dsContrDtlPk, false);
            if (waitIstlContrTMsg != null) {
                continue;
            }

            // mod start 2017/10/06 QC#21639
//            fsrNum = getFsrNumForNotInstl(dsContrDtlTMsg.svcMachMstrPk.getValue());
//            if (!hasValue(fsrNum)) {
//                continue;
//            }
//
//            if (!insertWaitIstlContr(dsContrDtlPk, fsrNum)) {
//                return false;
//            }
            if (SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION.equals((String) shellContr.get("SVC_MACH_MSTR_STS_CD"))) {
                if (!insertWaitIstlContr(dsContrDtlPk, DEF_FSR_NUM)) {
                    return false;
                }
            }
            // mod end 2017/10/06 QC#21639
        }
        return true;
    }

    private boolean deleteWaitIstlContr(List<Map<String, Object>> shellContrDtlList) {
        if (!BATCH_MODE_DAILY.equals(this.usrVar1)) {
            return true;
        }

        BigDecimal dsContrDtlPk;
        DS_CONTR_DTLTMsg dsContrDtlTMsg;
        WAIT_ISTL_CONTRTMsg waitIstlContrTMsg;
        for (Map<String, Object> shellContr : shellContrDtlList) {
            dsContrDtlPk = (BigDecimal) shellContr.get("DS_CONTR_DTL_PK");
            if (!hasValue(dsContrDtlPk)) {
                continue;
            }
            dsContrDtlTMsg = getDsContrDtlByKey(dsContrDtlPk);
            if (dsContrDtlTMsg == null || !hasValue(dsContrDtlTMsg.dsContrDtlStsCd)) {
                continue;
            }

            waitIstlContrTMsg = getWaitIstlContr(dsContrDtlPk, true);
            if (waitIstlContrTMsg == null) {
                continue;
            }

            EZDTBLAccessor.logicalRemove(waitIstlContrTMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(waitIstlContrTMsg.getReturnCode())) {
                S21InfoLogOutput.println(NSAM0031E, new String[] {"WAIT_ISTL_CONTR" });
                return false;
            }
        }
        return true;
    }

    // del start 2017/10/06 QC#21639
//    private String getFsrNumForNotInstl(BigDecimal svcMachMstrPk) {
//        if (!hasValue(svcMachMstrPk)) {
//            return null;
//        }
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("glblCmpyCd", this.glblCmpyCd);
//        param.put("svcMachMstrPk", svcMachMstrPk);
//        param.put("fsrStsCd", SVC_TASK_STS.CANCELLED);
//        param.put("dsCondConstGrpId", "ISTL_CALL_TP_CD");
//        return (String) this.ssmBatchClient.queryObject("getFsrNumForNotInstl", param);
//    }
    // del end 2017/10/06 QC#21639

    private WAIT_ISTL_CONTRTMsg getWaitIstlContr(BigDecimal dsContrDtlPk, boolean forUpdateFlg) {
        WAIT_ISTL_CONTRTMsg inMsg = new WAIT_ISTL_CONTRTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.dsContrDtlPk, dsContrDtlPk);
        WAIT_ISTL_CONTRTMsg rtnTMsg;
        if (forUpdateFlg) {
            rtnTMsg = (WAIT_ISTL_CONTRTMsg) EZDTBLAccessor.findByKeyForUpdate(inMsg);
        } else {
            rtnTMsg = (WAIT_ISTL_CONTRTMsg) EZDTBLAccessor.findByKey(inMsg);
        }
        return rtnTMsg;
    }

    private boolean insertWaitIstlContr(BigDecimal dsContrDtlPk, String fsrNum) {
        WAIT_ISTL_CONTRTMsg inMsg = new WAIT_ISTL_CONTRTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.dsContrDtlPk, dsContrDtlPk);
        setValue(inMsg.fsrNum, fsrNum);
        EZDTBLAccessor.create(inMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            S21InfoLogOutput.println(NSAM0031E, new String[] {"WAIT_ISTL_CONTR" });
            return false;
        }
        return true;
    }

    private List<BigDecimal> getContrHdrForInstlComp() {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        // add start 2017/10/06 QC#21639
        param.put("installed", SVC_MACH_MSTR_STS.INSTALLED);
        // add end 2017/10/06 QC#21639
        // add start 2017/10/10 QC#21617
        param.put("warranty", DS_CONTR_CATG.WARRANTY);
        param.put("warrantyFlg", ZYPConstant.FLG_OFF_N);
        // add end 2017/10/10 QC#21617
        return (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getContrHdrForInstlComp", param);
    }

    private List<Map<String, Object>> getContrDtlForInstlComp(BigDecimal dsContrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        // add start 2017/10/06 QC#21639
        param.put("installed", SVC_MACH_MSTR_STS.INSTALLED);
        // add end 2017/10/06 QC#21639
        // START 2018/08/24 K.Kojima [QC#27919,ADD]
        param.put("accessories", DS_CONTR_DTL_TP.ACCESSORIES);
        // END 2018/08/24 K.Kojima [QC#27919,ADD]
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getContrDtlForInstlComp", param);
    }

    private DS_CONTR_DTLTMsg updateDsContrDtlForInstlComp(Map<String, Object> shellContrDtl, List<DS_CONTR_DTLTMsg> updDsContrDtlTMsgList) {
        String dsContrCatgCd = (String) shellContrDtl.get("DS_CONTR_CATG_CD");
        BigDecimal dsContrDtlPk = (BigDecimal) shellContrDtl.get("DS_CONTR_DTL_PK");
        BigDecimal dsContrPk = (BigDecimal) shellContrDtl.get("DS_CONTR_PK");
        String dsContrSrcTpCd = (String) shellContrDtl.get("DS_CONTR_SRC_TP_CD");
        String dsContrClsCd = (String) shellContrDtl.get("DS_CONTR_CLS_CD");
        String svcLineBizCd = (String) shellContrDtl.get("SVC_LINE_BIZ_CD");
        // add start 2017/10/02 QC#21567
        String baseDplyPerEndDay = (String) shellContrDtl.get("BASE_DPLY_PER_END_DAY");
        String mtrDplyPerEndDay = (String) shellContrDtl.get("MTR_DPLY_PER_END_DAY");
        // add end 2017/10/02 QC#21567

        DS_CONTR_DTLTMsg tMsg = getDsContrDtlByKeyForUpdate(dsContrDtlPk);
        if (tMsg == null) {
            S21InfoLogOutput.println(NSAM0045E, new String[] {"DS_CONTR_DTL" });
            return null;
        }

        String contrActMode = null;
        if (ZYPConstant.FLG_OFF_N.equals(tMsg.addContrFlg.getValue())) {
            contrActMode = CONTR_ACT_MODE_NEW;
        } else {
            contrActMode = CONTR_ACT_MODE_ADD;
        }

        DS_CONTR_INTFC_DEF_RULETMsg dsContrIntfcDefRuleTMsg = getDsContrIntfcDefRule(dsContrSrcTpCd, dsContrClsCd, svcLineBizCd);
        String stubPrepFromTpCd = null;
        String stubPrepThruTpCd = null;
        if (dsContrIntfcDefRuleTMsg != null) {
            stubPrepFromTpCd = dsContrIntfcDefRuleTMsg.stubPrepFromTpCd.getValue();
            stubPrepThruTpCd = dsContrIntfcDefRuleTMsg.stubPrepThruTpCd.getValue();
        }

        // 1.4.1.Get Effective From Date
        String effFromDt = getEffFromDt(shellContrDtl, contrActMode, stubPrepFromTpCd, dsContrCatgCd);
        if (effFromDt == null) {
            S21InfoLogOutput.println(NSAM0179E, new String[] {"Effective From Date" });
            return null;
        }

        // 1.4.2.Get Effective Thru Date
        String effThruDt = getEffThruDt(shellContrDtl, contrActMode, effFromDt, stubPrepThruTpCd, dsContrCatgCd, dsContrPk);
        if (effThruDt == null) {
            S21InfoLogOutput.println(NSAM0179E, new String[] {"Effective Thru Date" });
            return null;
        }

        // 1.4.3.Get Close Day
        // add start 2017/10/02 QC#21567
        String contrCloDay = getCloDay(baseDplyPerEndDay, effFromDt);
        String mtrCloDay = getCloDay(mtrDplyPerEndDay, effFromDt);
        // add end 2017/10/02 QC#21567

        // 1.4.4.Update DS_CONTR_DTL
        setValue(tMsg.contrEffFromDt, effFromDt);
        setValue(tMsg.contrEffThruDt, effThruDt);
        // add start 2017/10/02 QC#21567
        if (!DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
            setValue(tMsg.contrCloDay, contrCloDay);
            setValue(tMsg.mtrCloDay, mtrCloDay);
        }
        // add end 2017/10/02 QC#21567

        // START 2018/08/30 M.Naito [QC#27102, ADD]
        DS_CONTRTMsg dsContrTMsg = getDsContrByKey(dsContrPk);
        if (dsContrTMsg != null) {
            String uplftFromDt = NSXC001001GetUplftFromDt.getUplftFromDt(glblCmpyCd, effFromDt, CONTR_INTFC_SRC_TP.ORDER, dsContrTMsg.dsContrClsCd.getValue(), dsContrTMsg.svcLineBizCd.getValue());
            if (ZYPCommonFunc.hasValue(uplftFromDt)) {
                setValue(tMsg.uplftFromDt, uplftFromDt);
            }
        }
        // END 2018/08/30 M.Naito [QC#27102, ADD]

        S21FastTBLAccessor.update(tMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            S21InfoLogOutput.println(NSAM0031E, new String[] {"DS_CONTR_DTL" });
            return null;
        }
        updDsContrDtlTMsgList.add(tMsg);

        // mod start 2017/10/02 QC#21567
        if (!updateDsContrDtlForAcc(tMsg, updDsContrDtlTMsgList, dsContrCatgCd)) {
            return null;
        }
        // mod end 2017/10/02 QC#21567
        return tMsg;
    }

    // mod start 2017/10/02 QC#21567
    private boolean updateDsContrDtlForAcc(DS_CONTR_DTLTMsg machContrDtlTMsg, List<DS_CONTR_DTLTMsg> updDsContrDtlTMsgList, String dsContrCatgCd) {
    // mod end 2017/10/02 QC#21567
        if (machContrDtlTMsg == null) {
            return true;
        }
        List<Map<String, Object>> accContrDtlList = getDsContrDtlAccForTrmn(machContrDtlTMsg.dsContrPk.getValue(), machContrDtlTMsg.dsContrDtlPk.getValue());
        BigDecimal dsContrDtlPk;
        DS_CONTR_DTLTMsg accContrDtlTMsg;
        for (Map<String, Object> accContrDtl : accContrDtlList) {
            dsContrDtlPk = (BigDecimal) accContrDtl.get("DS_CONTR_DTL_PK");
            if (!hasValue(dsContrDtlPk)) {
                continue;
            }

            accContrDtlTMsg = getDsContrDtlByKeyForUpdate(dsContrDtlPk);
            if (accContrDtlTMsg == null) {
                continue;
            }

            setValue(accContrDtlTMsg.contrEffFromDt, machContrDtlTMsg.contrEffFromDt);
            setValue(accContrDtlTMsg.contrEffThruDt, machContrDtlTMsg.contrEffThruDt);
            // add start 2017/10/02 QC#21567
            if (!DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
                setValue(accContrDtlTMsg.contrCloDay, machContrDtlTMsg.contrCloDay);
            }
            // add end 2017/10/02 QC#21567
            S21FastTBLAccessor.update(accContrDtlTMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(accContrDtlTMsg.getReturnCode())) {
                S21InfoLogOutput.println(NSAM0031E, new String[] {"DS_CONTR_DTL" });
                return false;
            }
            updDsContrDtlTMsgList.add(accContrDtlTMsg);
        }
        return true;
    }

    private void updateDsContrBllgMtrForInstlComp(DS_CONTR_DTLTMsg dsContrDtlTMsg, List<DS_CONTR_BLLG_MTRTMsg> updDsContrBllgMtrTMsgList) {
        BigDecimal dsContrDtlPk = dsContrDtlTMsg.dsContrDtlPk.getValue();

        DS_CONTR_BLLG_MTRTMsgArray tMsgArray = getDsContrBllgMtrByDsContrDtlPk(dsContrDtlPk);
        for (int i = 0; i < tMsgArray.getValidCount(); i++) {
            DS_CONTR_BLLG_MTRTMsg tMsg = (DS_CONTR_BLLG_MTRTMsg) tMsgArray.get(i);
            updDsContrBllgMtrTMsgList.add(tMsg);
        }
    }
    // END 2017/09/22 A.Kohinata [QC#18534,ADD]

    // add start 2017/10/02 QC#21567
    private boolean updateDsContrDtlCloDayForAgg(BigDecimal dsContrPk, List<DS_CONTR_DTLTMsg> updDsContrDtlTMsgList, List<DS_CONTR_BLLG_MTRTMsg> updDsContrBllgMtrTMsgList) {
        Map<String, Object> cloDayMap = getCloDayForAgg(dsContrPk);
        if (cloDayMap == null) {
            return true;
        }
        String contrCloDay = (String) cloDayMap.get("CONTR_CLO_DAY");
        String mtrCloDay = (String) cloDayMap.get("MTR_CLO_DAY");

        List<BigDecimal> contrDtlPkList = getDsContrDtlForAgg(dsContrPk);
        if (contrDtlPkList == null || contrDtlPkList.size() == 0) {
            return true;
        }

        updDsContrDtlTMsgList.clear();
        updDsContrBllgMtrTMsgList.clear();
        DS_CONTR_DTLTMsg dsContrDtlTMsg = new DS_CONTR_DTLTMsg();

        for (BigDecimal contrDtlPk : contrDtlPkList) {
            dsContrDtlTMsg = getDsContrDtlByKeyForUpdate(contrDtlPk);
            if (dsContrDtlTMsg == null) {
                continue;
            }
            setValue(dsContrDtlTMsg.contrCloDay, contrCloDay);
            if (hasValue(dsContrDtlTMsg.mtrDplyPerEndDay)) {
                setValue(dsContrDtlTMsg.mtrCloDay, mtrCloDay);
            }
            S21FastTBLAccessor.update(dsContrDtlTMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dsContrDtlTMsg.getReturnCode())) {
                S21InfoLogOutput.println(NSAM0031E, new String[] {"DS_CONTR_DTL" });
                return false;
            }
            updDsContrDtlTMsgList.add(dsContrDtlTMsg);

            updateDsContrBllgMtrForInstlComp(dsContrDtlTMsg, updDsContrBllgMtrTMsgList);
        }
        return true;
    }

    private Map<String, Object> getCloDayForAgg(BigDecimal dsContrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("aggregate", DS_CONTR_DTL_TP.AGGREGATE);
        param.put("initDay", INIT_DAY);
        return (Map<String, Object>) this.ssmBatchClient.queryObject("getCloDayForAgg", param);
    }

    private List<BigDecimal> getDsContrDtlForAgg(BigDecimal dsContrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("aggregate", DS_CONTR_DTL_TP.AGGREGATE);
        String[] dsContrCtrlStsCdList = new String[] {DS_CONTR_CTRL_STS.TERMINATED, DS_CONTR_CTRL_STS.EXPIRED, DS_CONTR_CTRL_STS.CANCELLED, DS_CONTR_CTRL_STS.ORDER };
        param.put("dsContrCtrlStsCdList", dsContrCtrlStsCdList);
        return (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getDsContrDtlForAgg", param);
    }
    // add end 2017/10/02 QC#21567

    // add start 2017/10/06 QC#21639
    private boolean deleteWaitIstlContrForContrStsChng(BigDecimal dsContrPk) {
        List<BigDecimal> dsContrDtlPkList = getDsContrDtlPkForContrStsChng(dsContrPk);

        WAIT_ISTL_CONTRTMsg waitIstlContrTMsg;
        for (BigDecimal dsContrDtlPk : dsContrDtlPkList) {
            waitIstlContrTMsg = getWaitIstlContr(dsContrDtlPk, true);
            if (waitIstlContrTMsg == null) {
                continue;
            }

            EZDTBLAccessor.logicalRemove(waitIstlContrTMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(waitIstlContrTMsg.getReturnCode())) {
                S21InfoLogOutput.println(NSAM0031E, new String[] {"WAIT_ISTL_CONTR" });
                return false;
            }
        }
        return true;
    }

    private List<BigDecimal> getDsContrDtlPkForContrStsChng(BigDecimal dsContrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("submited", DS_CONTR_DTL_STS.SUBMITED);
        param.put("installed", SVC_MACH_MSTR_STS.INSTALLED);
        return (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getDsContrDtlPkForContrStsChng", param);
    }
    // add end 2017/10/06 QC#21639

    // add start 2017/10/10 QC#21617
    private List<BigDecimal> getContrHdrForInstlCompWty() {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("installed", SVC_MACH_MSTR_STS.INSTALLED);
        param.put("warranty", DS_CONTR_CATG.WARRANTY);
        param.put("warrantyFlg", ZYPConstant.FLG_ON_Y);
        return (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getContrHdrForInstlComp", param);
    }

    private boolean updateShellContrWty(BigDecimal dsContrPk, List<Map<String, Object>> shellContrDtlList) {
        // Update Warranty Contract Detail
        for (int i = 0; i < shellContrDtlList.size(); i++) {
            Map<String, Object> shellContrDtl = shellContrDtlList.get(i);

            BigDecimal dsContrDtlPk = (BigDecimal) shellContrDtl.get("DS_CONTR_DTL_PK");
            String istlDt = (String) shellContrDtl.get("ISTL_DT");
            String mdseCd = (String) shellContrDtl.get("MDSE_CD");
            String effThruDt = getEffThruDt(istlDt, mdseCd);

            DS_CONTR_DTLTMsg tMsg = getDsContrDtlByKeyForUpdate(dsContrDtlPk);
            if (tMsg == null) {
                S21InfoLogOutput.println(NSAM0045E, new String[] {"DS_CONTR_DTL" });
                return false;
            }
            setValue(tMsg.contrEffFromDt, istlDt);
            setValue(tMsg.contrEffThruDt, effThruDt);
            S21FastTBLAccessor.update(tMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                S21InfoLogOutput.println(NSAM0031E, new String[] {"DS_CONTR_DTL" });
                return false;
            }
        }

        // Update Warranty Contract Header
        Map<String, Object> effDt = getEffDtByDsContrDtl(dsContrPk);
        if (effDt == null) {
            S21InfoLogOutput.println(NSAM0045E, new String[] {"DS_CONTR_DTL" });
            return false;
        }
        String contrEffFromDt = (String) effDt.get("CONTR_EFF_FROM_DT");
        String contrEffThruDt = (String) effDt.get("CONTR_EFF_THRU_DT");

        DS_CONTRTMsg dsContrTMsg = getDsContrByKeyForUpdate(dsContrPk);
        if (dsContrTMsg == null) {
            S21InfoLogOutput.println(NSAM0045E, new String[] {"DS_CONTR" });
            return false;
        }
        setValue(dsContrTMsg.contrVrsnEffFromDt, contrEffFromDt);
        setValue(dsContrTMsg.contrVrsnEffThruDt, contrEffThruDt);
        S21FastTBLAccessor.update(dsContrTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dsContrTMsg.getReturnCode())) {
            S21InfoLogOutput.println(NSAM0031E, new String[] {"DS_CONTR" });
            return false;
        }

        // 4.QA Check
        String vldRslt = validContract(dsContrPk);
        if (!hasValue(vldRslt)) {
            return false;
        }

        // 5.Update Status
        if (!updateStatus(dsContrPk, vldRslt)) {
            return false;
        }

        if (!deleteWaitIstlContr(shellContrDtlList)) {
            return false;
        }

        // 6.Contract Tracking
        if (!createContrTrk(dsContrPk)) {
            return false;
        }

        return true;
    }

    private String getEffThruDt(String effFromDt, String mdseCd) {
        MDSETMsg mdseTMsg = getMdseByKey(mdseCd);
        if (mdseTMsg == null) {
            return effFromDt;
        }
        int wtyDaysAot = mdseTMsg.wtyDaysAot.getValueInt();
        if (wtyDaysAot > 0) {
            wtyDaysAot = wtyDaysAot - 1;
        }
        return ZYPDateUtil.addDays(effFromDt, wtyDaysAot);
    }

    private MDSETMsg getMdseByKey(String mdseCd) {
        MDSETMsg prmTMsg = new MDSETMsg();
        setValue(prmTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(prmTMsg.mdseCd, mdseCd);
        return (MDSETMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }
    // add start 2017/10/10 QC#21617

    // START 2017/10/18 K.Kitachi [QC#21622, ADD]
    // START 2017/10/24 K.Kitachi [QC#21622, DEL]
//    private boolean isDiffTermPerMth(DS_CONTR_DTLTMsg dsContrDtlTMsg) {
//        DateFormat df = new SimpleDateFormat(ZYPDateUtil.TYPE_YYYYMMDD);
//        Date startDt;
//        try {
//            startDt = df.parse(dsContrDtlTMsg.contrEffFromDt.getValue());
//        } catch (ParseException e) {
//            e.printStackTrace();
//            return false;
//        }
//
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(startDt);
//        cal.add(Calendar.MONTH, diffPerMth(dsContrDtlTMsg));
//        String calcEndDate = df.format(cal.getTime());
//
//        if (dsContrDtlTMsg.contrEffThruDt.getValue().compareTo(calcEndDate) < 0) {
//            return true;
//        }
//        return false;
//    }
//
//    private int diffPerMth(DS_CONTR_DTLTMsg dsContrDtlTMsg) {
//        int fromPerMthNum = dsContrDtlTMsg.fromPerMthNum.getValueInt();
//        int toPerMthNum = dsContrDtlTMsg.toPerMthNum.getValueInt();
//        return toPerMthNum - fromPerMthNum;
//    }
    // END 2017/10/24 K.Kitachi [QC#21622, DEL]

    private int calcBllgCycleCntFromDuration(DS_CONTR_DTLTMsg dsContrDtlTMsg) {
        // calculate duration
        DateFormat df = new SimpleDateFormat(ZYPDateUtil.TYPE_YYYYMMDD);
        Date startDt;
        try {
            // START 2018/05/09 K.Kitachi [QC#25728, MOD]
//            String calcFromDt = getMinUnbilledFromDt(dsContrDtlTMsg.dsContrDtlPk.getValue());
//            if (!hasValue(calcFromDt)) {
//                calcFromDt = dsContrDtlTMsg.contrEffFromDt.getValue();
//            }
            String calcFromDt = dsContrDtlTMsg.contrEffFromDt.getValue();
            // END 2018/05/09 K.Kitachi [QC#25728, MOD]
            startDt = df.parse(calcFromDt);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }

        String paramEndDate = dsContrDtlTMsg.contrEffThruDt.getValue();
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
            return 0;
        }

        // get BLLG_CYCLE Info
        BLLG_CYCLETMsg bcTMsg = new BLLG_CYCLETMsg();
        setValue(bcTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(bcTMsg.bllgCycleCd, dsContrDtlTMsg.baseBllgCycleCd);
        bcTMsg = (BLLG_CYCLETMsg) EZDTBLAccessor.findByKey(bcTMsg);

        if (durnCnt.intValue() % bcTMsg.bllgCycleMthAot.getValueInt() != 0) {
            return 0;
        }
        return durnCnt.intValue() / bcTMsg.bllgCycleMthAot.getValueInt();
    }

    // START 2018/05/09 K.Kitachi [QC#25728, DEL]
//    private String getMinUnbilledFromDt(BigDecimal dsContrDtlPk) {
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("glblCmpyCd", this.glblCmpyCd);
//        param.put("dsContrDtlPk", dsContrDtlPk);
//        param.put("invTpCd", INV_TP.INVOICE);
//        return (String) this.ssmBatchClient.queryObject("getMinUnbilledFromDt", param);
//    }
    // END 2018/05/09 K.Kitachi [QC#25728, DEL]
    // END 2017/10/18 K.Kitachi [QC#21622, ADD]

    // START 2017/10/24 U.Kim [QC#21864, ADD]
    private CPOTMsg getCpo(String cpoOrdNum) {
        CPOTMsg prmTMsg = new CPOTMsg();
        setValue(prmTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(prmTMsg.cpoOrdNum, cpoOrdNum);
        return (CPOTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }
    // END 2017/10/24 U.Kim [QC#21864, ADD]
    // Add Start 2017/12/14 QC#18362
    private String getAcctAdminPsnCd(DS_CONTRTMsg dsContrTMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrTMsg.dsContrPk.getValue());
        param.put("svcLineBizCd", dsContrTMsg.svcLineBizCd.getValue());
        param.put("slsDt", dsContrTMsg.contrVrsnEffFromDt.getValue());
        return (String) this.ssmBatchClient.queryObject("getAcctAdminPsnCd", param);
    }
    // Add End 2017/12/14 QC#18362

    // START 2018/02/23 K.Kojima [QC#21685,ADD]
    private PRC_SVC_CONTR_TPTMsg getPrcSvcContrTp(String prcSvcContrTpCd) {
        PRC_SVC_CONTR_TPTMsg prmTMsg = new PRC_SVC_CONTR_TPTMsg();
        setValue(prmTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(prmTMsg.prcSvcContrTpCd, prcSvcContrTpCd);
        return (PRC_SVC_CONTR_TPTMsg) S21FastTBLAccessor.findByKey(prmTMsg);
    }
    // END 2018/02/23 K.Kojima [QC#21685,ADD]

    // START 2018/02/28 M.Naito [QC#22475,ADD]
    private boolean updateSubContr(List<Map<String, Object>> contrDtlList) {

        for (int i = 0; i < contrDtlList.size(); i++) {
            Map<String, Object> contrDtl = contrDtlList.get(i);
            BigDecimal dsContrDtlPk = (BigDecimal) contrDtl.get("DS_CONTR_DTL_PK");

            // get Contract StartDate
            DS_CONTR_DTLTMsg dsContrDtlTMsg = getDsContrDtlByKey(dsContrDtlPk);
            if (dsContrDtlTMsg == null) {
                continue;
            }
            String effFromDt = dsContrDtlTMsg.contrEffFromDt.getValue();

            DS_SUB_CONTRTMsgArray tMsgArray = getDsSubContr(dsContrDtlPk);
            for (int j = 0; j < tMsgArray.getValidCount(); j++) {
                DS_SUB_CONTRTMsg tMsg = (DS_SUB_CONTRTMsg) tMsgArray.get(j);
                int diffDays = ZYPDateUtil.getDiffDays(effFromDt, tMsg.effFromDt.getValue());
                String effThruDt = ZYPDateUtil.addDays(tMsg.effThruDt.getValue(), diffDays);

                setValue(tMsg.effFromDt, effFromDt);
                setValue(tMsg.effThruDt, effThruDt);

                S21FastTBLAccessor.update(tMsg);
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                    S21InfoLogOutput.println(NSAM0031E, new String[] {"DS_SUB_CONTR" });
                    return false;
                }
            }
        }
        return true;
    }

    private DS_SUB_CONTRTMsgArray getDsSubContr(BigDecimal dsContrDtlPk) {
        DS_SUB_CONTRTMsg inMsg = new DS_SUB_CONTRTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        return (DS_SUB_CONTRTMsgArray) EZDTBLAccessor.findByConditionForUpdate(inMsg);
    }
    // END 2018/02/28 M.Naito [QC#22475,ADD]

    // START 2018/03/13 K.Kojima [QC#24263,ADD]
    private BigDecimal getCtacPsnPk(String cpoOrdNum, String billToCustCd, BigDecimal ctacPsnPk) {
        if (ZYPCommonFunc.hasValue(ctacPsnPk)) {
            return ctacPsnPk;
        }
        if (!ZYPCommonFunc.hasValue(billToCustCd)) {
            return null;
        }
        // START 2018/04/02 U.Kim [QC#23559(Sol358), ADD]
        String [] ctacTpList = {CTAC_TP.BILL_TO_CONTACT, CTAC_TP.BILL_TO_CONTACT_CONTRACTS};
        // END 2018/04/02 U.Kim [QC#23559(Sol358), ADD]
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", cpoOrdNum);
        // START 2018/04/02 U.Kim [QC#23559(Sol358), ADD]
        param.put("billToContactContr", CTAC_TP.BILL_TO_CONTACT_CONTRACTS);
        param.put("ctacTpList", ctacTpList);
        // END 2018/04/02 U.Kim [QC#23559(Sol358), ADD]
        param.put("billToContact", CTAC_TP.BILL_TO_CONTACT);
        param.put("billToCustCd", billToCustCd);
        return (BigDecimal) this.ssmBatchClient.queryObject("getCtacPsnPk", param);
    }
    // END 2018/03/13 K.Kojima [QC#24263,ADD]

// START 2019/01/21 [QC#29782, DEL]
//    // START 2018/04/06 U.Kim [QC#23378(Sol320), ADD]
//    private List<Map<String, Object>> getSegAllocInfo(DS_CONTRTMsg updDsContrTMsg, List<DS_CONTR_DTLTMsg> updDsContrDtlTMsgList, DS_CONTR_DTLTMsg updDsContrDtlTMsgFltAgg) {
//        List<DS_CONTR_DTLTMsg> targetTMsgList = new ArrayList<DS_CONTR_DTLTMsg>();
//        if (DS_CONTR_CATG.FLEET.equals(updDsContrTMsg.dsContrCatgCd.getValue())) {
//            if (!ZYPCommonFunc.hasValue(updDsContrDtlTMsgFltAgg.rntlPrcAmt)) {
//                return new ArrayList<Map<String, Object>>();
//            }
//            // START 2018/05/07 U.Kim [QC#25895, ADD]
//            if (!ZYPCommonFunc.hasValue(updDsContrDtlTMsgFltAgg.basePrcDealAmt)) {
//                return new ArrayList<Map<String, Object>>();
//            }
//            if (updDsContrDtlTMsgFltAgg.basePrcDealAmt.getValue().compareTo(BigDecimal.ZERO) == 0) {
//                return new ArrayList<Map<String, Object>>();
//            }
//            // END 2018/05/07 U.Kim [QC#25895, ADD]
//            if (updDsContrDtlTMsgFltAgg.dsContrDtlTpCd.getValue().equals(DS_CONTR_DTL_TP.FLEET)) {
//                targetTMsgList.add(updDsContrDtlTMsgFltAgg);
//            }
//        } else {
//            for (DS_CONTR_DTLTMsg detailTMsg : updDsContrDtlTMsgList) {
//                if (!ZYPCommonFunc.hasValue(detailTMsg.rntlPrcAmt)) {
//                    continue;
//                }
//                // START 2018/05/07 U.Kim [QC#25895, ADD]
//                if (!ZYPCommonFunc.hasValue(detailTMsg.basePrcDealAmt)) {
//                    continue;
//                }
//                if (detailTMsg.basePrcDealAmt.getValue().compareTo(BigDecimal.ZERO) == 0) {
//                    continue;
//                }
//                // END 2018/05/07 U.Kim [QC#25895, ADD]
//                if (detailTMsg.dsContrDtlTpCd.getValue().equals(DS_CONTR_DTL_TP.BASE_AND_USAGE) || detailTMsg.dsContrDtlTpCd.getValue().equals(DS_CONTR_DTL_TP.BASE_ONLY)
//                        || detailTMsg.dsContrDtlTpCd.getValue().equals(DS_CONTR_DTL_TP.ACCESSORIES)) {
//                    targetTMsgList.add(detailTMsg);
//                }
//            }
//        }
//        if (targetTMsgList.size() == 0) {
//            return new ArrayList<Map<String, Object>>();
//        }
//
//        List<Map<String, Object>> nineSegmentList = new NFACommonJrnlEntry().getSegInfoForSvc(this.glblCmpyCd, updDsContrTMsg.dsContrPk.getValue());
//        if (nineSegmentList == null || nineSegmentList.size() == 0) {
//            return new ArrayList<Map<String, Object>>();
//        }
//
//        BigDecimal digitNum = getDigitNum(glblCmpyCd, updDsContrTMsg.dsContrPk.getValue());
//        if (digitNum == null) {
//            return new ArrayList<Map<String, Object>>();
//        }
//
//        List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
//        for (DS_CONTR_DTLTMsg targetTMsg : targetTMsgList) {
//            Map<String, BigDecimal> percentForAlloc = getPercentForAlloc(updDsContrTMsg, targetTMsg);
//            if (percentForAlloc.size() == 0) {
//                continue;
//            }
//            List<Map<String, Object>> nineSegList = getNineSegment(nineSegmentList, targetTMsg.dsContrDtlPk.getValue());
//            if (nineSegList.size() == 0) {
//                continue;
//            }
//            Map<String, BigDecimal> prcAllocAmt = getPrcAllocAmt(percentForAlloc, targetTMsg, digitNum);
//            for (Map<String, Object> nineSegment : nineSegList) {
//                String trxRsnCd = (String) nineSegment.get("TRX_RSN_CD");
//                if (trxRsnCd.equals(TRX_RSN.SVC_EQUIP_REV_BASE)) {
//                    nineSegment.put("PRC_ALLOC_AMT", targetTMsg.rntlPrcAmt.getValue());
//                    returnList.add(nineSegment);
//                } else if (trxRsnCd.equals(TRX_RSN.SVC_SUP_REV_BASE) && prcAllocAmt != null && hasValue(prcAllocAmt.get(TRX_RSN.SVC_SUP_REV_BASE))) {
//                    nineSegment.put("PRC_ALLOC_AMT", prcAllocAmt.get(TRX_RSN.SVC_SUP_REV_BASE));
//                    returnList.add(nineSegment);
//                } else if (trxRsnCd.equals(TRX_RSN.SVC_SVC_REV_BASE) && prcAllocAmt != null && hasValue(prcAllocAmt.get(TRX_RSN.SVC_SVC_REV_BASE))) {
//                    nineSegment.put("PRC_ALLOC_AMT", prcAllocAmt.get(TRX_RSN.SVC_SVC_REV_BASE));
//                    returnList.add(nineSegment);
//                }
//            }
//        }
//
//        return returnList;
//    }
//
//    private Map<String, BigDecimal> getPrcAllocAmt(Map<String, BigDecimal> percentForAlloc, DS_CONTR_DTLTMsg targetTMsg, BigDecimal digitNum) {
//        if (targetTMsg.basePrcDealAmt.getValue().compareTo(targetTMsg.rntlPrcAmt.getValue()) <= 0) {
//            return null;
//        }
//        BigDecimal remainBasePrc = targetTMsg.basePrcDealAmt.getValue().subtract(targetTMsg.rntlPrcAmt.getValue());
//        Map<String, BigDecimal> prcAllocAmt = new HashMap<String, BigDecimal>();
//        BigDecimal svcAllocAmt = null;
//        BigDecimal splyAllocAmt = null;
//        BigDecimal svcAllocPct =  percentForAlloc.get(INV_LINE_SPL_TP.SERVICE);
//        BigDecimal splyAllocPct =  percentForAlloc.get(INV_LINE_SPL_TP.SUPPLY);
//        BigDecimal totAllocPct = BigDecimal.ZERO;
//        if (svcAllocPct != null) {
//            totAllocPct = totAllocPct.add(svcAllocPct);
//        }
//        if (splyAllocPct != null) {
//            totAllocPct = totAllocPct.add(splyAllocPct);
//        }
//        // Calculation Service Price
//        if (svcAllocPct != null && svcAllocPct.compareTo(BigDecimal.ZERO) != 0 && totAllocPct.compareTo(BigDecimal.ZERO) != 0) {
//            svcAllocAmt = remainBasePrc.multiply(svcAllocPct).divide(totAllocPct, digitNum.intValue(), BigDecimal.ROUND_HALF_UP);
//        }
//        // Calculation Supply Price
//        if (splyAllocPct != null && splyAllocPct.compareTo(BigDecimal.ZERO) != 0 && totAllocPct.compareTo(BigDecimal.ZERO) != 0) {
//            splyAllocAmt = remainBasePrc.multiply(splyAllocPct).divide(totAllocPct, digitNum.intValue(), BigDecimal.ROUND_HALF_UP);
//        }
//        // Adjust
//        if (svcAllocAmt != null && splyAllocAmt != null) {
//            if (remainBasePrc.compareTo(svcAllocAmt.add(splyAllocAmt)) != 0) {
//                if (svcAllocAmt.compareTo(splyAllocAmt) >= 0) {
//                    svcAllocAmt = svcAllocAmt.add(remainBasePrc.subtract(svcAllocAmt.add(splyAllocAmt)));
//                } else {
//                    splyAllocAmt = splyAllocAmt.add(remainBasePrc.subtract(svcAllocAmt.add(splyAllocAmt)));
//                }
//            }
//        }
//        prcAllocAmt.put(TRX_RSN.SVC_SVC_REV_BASE, svcAllocAmt);
//        prcAllocAmt.put(TRX_RSN.SVC_SUP_REV_BASE, splyAllocAmt);
//        return prcAllocAmt;
//    }
//
//    private List<Map<String, Object>> getNineSegment(List<Map<String, Object>> nineSegmentList, BigDecimal targetDsContrDtlPk) {
//        List<Map<String, Object>> targetNineSegment = new ArrayList<Map<String, Object>>();
//        for (Map<String, Object> nineSegment : nineSegmentList) {
//            BigDecimal dsContrDtlPk = (BigDecimal) nineSegment.get("DS_CONTR_DTL_PK");
//            if (targetDsContrDtlPk.compareTo(dsContrDtlPk) != 0) {
//                continue;
//            }
//            targetNineSegment.add(nineSegment);
//        }
//        return targetNineSegment;
//    }
//
//    private Map<String, BigDecimal> getPercentForAlloc(DS_CONTRTMsg dsContrTMsg, DS_CONTR_DTLTMsg dsContrDtlTMsg) {
//        BigDecimal dsContrPk = dsContrTMsg.dsContrPk.getValue();
//        BigDecimal dsContrDtlPk = dsContrDtlTMsg.dsContrDtlPk.getValue();
//        String svcPgmMdseCd = dsContrDtlTMsg.svcPgmMdseCd.getValue();
//        Map<String, BigDecimal> prcAllocPct = new HashMap<String, BigDecimal>();
//        List<GetDefCoaTrxCdForOutListInfoBean> allocList = getAllocList(dsContrPk, dsContrDtlPk, svcPgmMdseCd, SVC_INV_CHRG_TP.BASE_CHARGE);
//        if (allocList.size() != 0) {
//            return null;
//        }
//        if (allocList.size() == 0) {
//            GetDefCoaTrxCdInfoBean bean = new GetDefCoaTrxCdInfoBean();
//            bean.setGlblCmpyCd(this.glblCmpyCd);
//            bean.setSvcMachMstrPk(dsContrDtlTMsg.svcMachMstrPk.getValue());
//            bean.setDsContrDtlPk(dsContrDtlPk);
//            bean.setMdseCd(svcPgmMdseCd);
//            bean.setDsAcctNum(dsContrTMsg.dsAcctNum.getValue());
//            bean.setBaseChrgFlg(FLG_ON_Y);
//            bean.setUsgChrgFlg(FLG_OFF_N);
//            bean.setAddlChrgFlg(FLG_OFF_N);
//            if (NSXC004001GetDefCoaTrxCd.getDefCoaTrxCd(bean) != null) {
//                allocList = bean.getOutLisstInfoBean();
//            }
//        }
//        for (GetDefCoaTrxCdForOutListInfoBean outBean : allocList) {
//            prcAllocPct.put(outBean.getInvLineSplTpCd(), outBean.getPrcAllocPct());
//        }
//        return prcAllocPct;
//    }
//
//    private List<GetDefCoaTrxCdForOutListInfoBean> getAllocList(BigDecimal dsContrPk, BigDecimal dsContrDtlPk, String mdseCd, String svcInvCrhgTpCd) {
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("glblCmpyCd", this.glblCmpyCd);
//        param.put("dsContrPk", dsContrPk);
//        param.put("dsContrDtlPk", dsContrDtlPk);
//        param.put("svcInvCrhgTpCd", svcInvCrhgTpCd);
//        List<GetDefCoaTrxCdForOutListInfoBean> outList = new ArrayList<GetDefCoaTrxCdForOutListInfoBean>();
//        List<Map<String, Object>> resultMapList = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getContrAlloc", param);
//        if (resultMapList.size() == 0) {
//            return outList;
//        }
//        SVC_INV_CHRG_TPTMsg svcInvChrgTpTmsg = new SVC_INV_CHRG_TPTMsg();
//        setValue(svcInvChrgTpTmsg.glblCmpyCd, this.glblCmpyCd);
//        setValue(svcInvChrgTpTmsg.svcInvChrgTpCd, svcInvCrhgTpCd);
//        svcInvChrgTpTmsg = (SVC_INV_CHRG_TPTMsg) S21FastTBLAccessor.findByKey(svcInvChrgTpTmsg);
//
//        Map<String, Object> resultRule = new HashMap<String, Object>();
//        if (hasValue(mdseCd)) {
//            Map<String, Object> paramRule = new HashMap<String, Object>();
//            paramRule.put("glblCmpyCd", this.glblCmpyCd);
//            paramRule.put("mdseCd", mdseCd);
//            resultRule = (Map<String, Object>) this.ssmBatchClient.queryObject("getdfrdAcctgRule", paramRule);
//        }
//
//        for (Map<String, Object> reslultMap : resultMapList) {
//            GetDefCoaTrxCdForOutListInfoBean outBean = new GetDefCoaTrxCdForOutListInfoBean();
//            outBean.setCoaCmpyCd((String) reslultMap.get("COA_CMPY_CD"));
//            outBean.setCoaAfflCd((String) reslultMap.get("COA_AFFL_CD"));
//            outBean.setCoaBrCd((String) reslultMap.get("COA_BR_CD"));
//            outBean.setCoaChCd((String) reslultMap.get("COA_CH_CD"));
//            outBean.setCoaCcCd((String) reslultMap.get("COA_CC_CD"));
//            outBean.setCoaAcctCd((String) reslultMap.get("COA_ACCT_CD"));
//            outBean.setCoaProdCd((String) reslultMap.get("COA_PROD_CD"));
//            outBean.setCoaProjCd((String) reslultMap.get("COA_PROJ_CD"));
//            outBean.setCoaExtnCd((String) reslultMap.get("COA_EXTN_CD"));
//            outBean.setInvLineSplTpCd("");
//            outBean.setPrcAllocPct((BigDecimal) reslultMap.get("PRC_ALLOC_RATE"));
//            outBean.setTrxCd(svcInvChrgTpTmsg.trxCd.getValue());
//            outBean.setTrxRsnCd(svcInvChrgTpTmsg.trxRsnCd.getValue());
//            outBean.setDfrdAcctgRuleCd((String) resultRule.get("DFRD_ACCTG_RULE_CD"));
//            outList.add(outBean);
//        }
//        return outList;
//    }
//
//    private static BigDecimal getDigitNum(String glblCmpyCd, BigDecimal dsContrPk) {
//        DS_CONTRTMsg tMsg = getDsContr(glblCmpyCd, dsContrPk);
//        if (tMsg == null) {
//            return null;
//        }
//        CCYTMsg ccyTMsg = getCcy(glblCmpyCd, tMsg.ccyCd.getValue());
//        if (ccyTMsg == null) {
//            return null;
//        }
//        return ccyTMsg.aftDeclPntDigitNum.getValue();
//    }
//
//    private static DS_CONTRTMsg getDsContr(String glblCmpyCd, BigDecimal dsContrPk) {
//        DS_CONTRTMsg tMsg = new DS_CONTRTMsg();
//        setValue(tMsg.glblCmpyCd, glblCmpyCd);
//        setValue(tMsg.dsContrPk, dsContrPk);
//        return (DS_CONTRTMsg) EZDTBLAccessor.findByKey(tMsg);
//    }
//
//    private static CCYTMsg getCcy(String glblCmpyCd, String ccyCd) {
//        CCYTMsg tMsg = new CCYTMsg();
//        setValue(tMsg.glblCmpyCd, glblCmpyCd);
//        setValue(tMsg.ccyCd, ccyCd);
//        return (CCYTMsg) EZDTBLAccessor.findByKey(tMsg);
//    }
//
//    // END 2018/04/06 U.Kim [QC#23378(Sol320), ADD]
// END 2019/01/21 [QC#29782, DEL]

    // START 2018/05/09 K.Kitachi [QC#25728, ADD]
    private boolean isChangeThruDateToEndMonth(Map<String, Object> shellContrDtl, String stubPrepThruTpCd, BigDecimal fromPerMthNum) {
        if (!STUB_PREP_THRU_TP.EXTEND_END_MONTH.equals(stubPrepThruTpCd)) {
            return false;
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", (String) shellContrDtl.get("CPO_ORD_NUM"));
        param.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.PRICE_OF_MODEL_LEVEL);
        param.put("fromPerMthNum", fromPerMthNum);
        BigDecimal count = (BigDecimal) this.ssmBatchClient.queryObject("countAfterShellLine", param);
        if (count.compareTo(BigDecimal.ZERO) > 0) {
            return false;
        }
        String svcPgmTpCd = (String) shellContrDtl.get("SVC_PGM_TP_CD");
        if (!hasValue(svcPgmTpCd)) {
            return false;
        }
        String stubPrepSvcPgmTp = ZYPCodeDataUtil.getVarCharConstValue(STUB_PREP_SVC_PGM_TP, this.glblCmpyCd);
        if (!hasValue(stubPrepSvcPgmTp)) {
            return false;
        }
        String[] list = stubPrepSvcPgmTp.split(COMMA);
        for (String val : list) {
            if (val.equals(svcPgmTpCd)) {
                return true;
            }
        }
        return false;
    }
    // END 2018/05/09 K.Kitachi [QC#25728, ADD]

    // START 2018/11/13 K.Kitachi [QC#28638, ADD]
    // mod start 2020/03/24 QC#54318
    //private boolean updateDsContrRnwEscl(BigDecimal dsContrPk) {
    private boolean updateDsContrRnwEscl(BigDecimal dsContrPk, List<Map<String, Object>> shellContrDtlList) {
    // mod end 2020/03/24 QC#54318

        // mod start 2020/03/24 QC#54318
        //Map<String, Object> uplftInfo = getUplftInfo(dsContrPk);
        //if (uplftInfo == null) {
        //    return true;
        //}
        List<Map<String, Object>> uplftInfoList = getUplftInfo(dsContrPk, shellContrDtlList);
        // add start 2020/04/14 QC#54318-1
        if (uplftInfoList == null) {
            return true;
        }
        // add end 2020/04/14 QC#54318-1
        for (int i = 0; i < uplftInfoList.size(); i++) {
            Map<String, Object> uplftInfo = uplftInfoList.get(i);
        // mod end 2020/03/24 QC#54318

        BigDecimal dsContrRnwEsclPk = (BigDecimal) uplftInfo.get("DS_CONTR_RNW_ESCL_PK");
        DS_CONTR_RNW_ESCLTMsg dsContrRnwEsclTMsg = getDsContrRnwEsclByKeyForUpdate(dsContrRnwEsclPk);
        if (dsContrRnwEsclTMsg == null) {
            S21InfoLogOutput.println(NSAM0045E, new String[] {"DS_CONTR_RNW_ESCL" });
            // mod start 2020/03/24 QC#54318
            //return false;
            continue;
            // mod end 2020/03/24 QC#54318
        }

        String contrEffFromDt = (String) uplftInfo.get("CONTR_VRSN_EFF_FROM_DT");
        String contrEffThruDt = (String) uplftInfo.get("CONTR_VRSN_EFF_THRU_DT");
        UpliftInfoBean bean = new UpliftInfoBean();
        bean.setContrEffFromDt(contrEffFromDt);
        bean.setContrEffThruDt(contrEffThruDt);
        // mod start 2020/06/16 QC#54318-2
        if (hasValue(dsContrRnwEsclTMsg.fixTermInMthAot))  {
            bean.setFixTermInMthAot(dsContrRnwEsclTMsg.fixTermInMthAot.getValue());
        } else {
            bean.setFixTermInMthAot(DEF_FIXED_MONTH);
        }
        // mod end 2020/06/16 QC#54318-2
        NSXC001001CalcUplftInfo.calcUplftInfoByChngFixTermInMthAot(bean);

        // mod start 2019/07/30 QC#52070
        //setValue(dsContrRnwEsclTMsg.uplftFixedDt, bean.getUplftFixedDt());
        //setValue(dsContrRnwEsclTMsg.uplftPcyDt, bean.getUplftPcyDt());
        if (ZYPDateUtil.compare(bean.getUplftPcyDt(), contrEffThruDt) > 0) {
            setValue(dsContrRnwEsclTMsg.contrUplftTpCd, CONTR_UPLFT_TP.DO_NOT_UPLIFT);
            dsContrRnwEsclTMsg.uplftPrcMethCd.clear();
            dsContrRnwEsclTMsg.uplftBefEndRnwDaysAot.clear();
            dsContrRnwEsclTMsg.uplftBasePrcUpRatio.clear();
            dsContrRnwEsclTMsg.uplftMtrPrcUpRatio.clear();
        } else {
            // add start 2020/03/24 QC#54318
            String uplftPcyDt = (String) uplftInfo.get("UPLFT_PCY_DT");
            if (hasValue(uplftPcyDt) && ZYPDateUtil.compare(bean.getUplftPcyDt(), uplftPcyDt) < 0) {
                continue;
            }
            // add end 2020/03/24 QC#54318
            setValue(dsContrRnwEsclTMsg.uplftFixedDt, bean.getUplftFixedDt());
            setValue(dsContrRnwEsclTMsg.uplftPcyDt, bean.getUplftPcyDt());
            // add start 2020/06/16 QC#54318-2
            if (!hasValue(dsContrRnwEsclTMsg.fixTermInMthAot)) {
                setValue(dsContrRnwEsclTMsg.fixTermInMthAot, DEF_FIXED_MONTH);
            }
            // add end 2020/06/16 QC#54318-2
        }
        // mod end 2019/07/30 QC#52070

        S21FastTBLAccessor.update(dsContrRnwEsclTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dsContrRnwEsclTMsg.getReturnCode())) {
            S21InfoLogOutput.println(NSAM0031E, new String[] {"DS_CONTR_RNW_ESCL" });
            // mod start 2020/03/24 QC#54318
            //return false;
            continue;
            // mod end 2020/03/24 QC#54318
        }
        // add start 2020/03/24 QC#54318
        }
        // add end 2020/03/24 QC#54318

        return true;
    }

    // mod start 2020/03/24 QC#54318
    //private Map<String, Object> getUplftInfo(BigDecimal dsContrPk) {
    private List<Map<String, Object>> getUplftInfo(BigDecimal dsContrPk, List<Map<String, Object>> shellContrDtlList) {
        List<BigDecimal> dsContrDtlPkList = new ArrayList<BigDecimal>();
        String addContr = ZYPConstant.FLG_OFF_N;
        BigDecimal dsContrDtlPk;
        for (int i = 0; i < shellContrDtlList.size(); i++) {
            Map<String, Object> shellContrDtl = shellContrDtlList.get(i);
            dsContrDtlPk = (BigDecimal) shellContrDtl.get("DS_CONTR_DTL_PK");
            if (hasValue(dsContrDtlPk)) {
                DS_CONTR_DTLTMsg tMsg = getDsContrDtlByKey(dsContrDtlPk);
                if (tMsg != null && ZYPConstant.FLG_OFF_N.equals(tMsg.addAsryFlg.getValue())) {
                    dsContrDtlPkList.add(dsContrDtlPk);
                }
                if (tMsg != null && ZYPConstant.FLG_ON_Y.equals(tMsg.addContrFlg.getValue())) {
                    addContr = ZYPConstant.FLG_ON_Y;
                }
            }
        }
        if (dsContrDtlPkList.size() == 0) {
            return null;
        }

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("dsContrMachLvlNum", "1");
        param.put("dsContrDtlPkList", dsContrDtlPkList);
        param.put("dsContrMachLvl2", "2");
        param.put("addContr", addContr);
        param.put("dsContrDtlTpFlt", DS_CONTR_DTL_TP.FLEET);
        param.put("dsContrDtlTpAgg", DS_CONTR_DTL_TP.AGGREGATE);
        param.put("contrUplftTpCd", CONTR_UPLFT_TP.DO_NOT_UPLIFT);
        //return (Map<String, Object>) this.ssmBatchClient.queryObject("getUplftInfo", param);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getUplftInfo", param);
    }
    // mod end 2020/03/24 QC#54318

    private DS_CONTR_RNW_ESCLTMsg getDsContrRnwEsclByKeyForUpdate(BigDecimal dsContrRnwEsclPk) {
        DS_CONTR_RNW_ESCLTMsg prmTMsg = new DS_CONTR_RNW_ESCLTMsg();
        setValue(prmTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(prmTMsg.dsContrRnwEsclPk, dsContrRnwEsclPk);
        return (DS_CONTR_RNW_ESCLTMsg) S21FastTBLAccessor.findByKeyForUpdate(prmTMsg);
    }
    // END 2018/11/13 K.Kitachi [QC#28638, ADD]

    // START 2022/06/22 E.Sanchez [QC#59804, ADD]
    private CONTR_PHYS_BLLG_MTR_RELNTMsgArray getContrPhysBllgMtrReln(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        CONTR_PHYS_BLLG_MTR_RELNTMsg inMsg = new CONTR_PHYS_BLLG_MTR_RELNTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        return (CONTR_PHYS_BLLG_MTR_RELNTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
    }
    // END 2022/06/22 E.Sanchez [QC#59804, ADD]
}
