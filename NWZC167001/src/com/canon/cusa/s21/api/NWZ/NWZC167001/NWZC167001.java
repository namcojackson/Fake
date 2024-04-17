/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC167001;

import static com.canon.cusa.s21.api.NWZ.NWZC167001.constant.NWZC167001Constant.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import parts.common.EZDItemAttribute;
import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.ACCT_DLY_ACTL_EXCH_RATESTMsg;
import business.db.ALL_MDSE_VTMsg;
import business.db.ALL_MDSE_VTMsgArray;
import business.db.AVAL_INVTY_APP_IDTMsg;
import business.db.BIZ_PROC_LOGTMsg;
import business.db.CTAC_PSNTMsg;
import business.db.FRT_CONDTMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.MDSETMsg;
import business.db.MSG_TXT_DTLTMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.OTBD_CARR_VTMsg;
import business.db.OTBD_CARR_VTMsgArray;
import business.db.PMT_TERM_CASH_DISCTMsg;
import business.db.SHIP_FROM_LOC_LIST_VTMsg;
import business.db.SHIP_FROM_LOC_LIST_VTMsgArray;
import business.db.SHPG_SVC_FRT_CHRG_RELNTMsg;
import business.db.SPLY_QUOTETMsg;
import business.db.SPLY_QUOTE_CALC_BASE_RECTMsg;
import business.db.SPLY_QUOTE_CTAC_PSNTMsg;
import business.db.SPLY_QUOTE_DTLTMsg;
import business.db.SPLY_QUOTE_DTL_RECTMsg;
import business.db.SPLY_QUOTE_PRC_CALC_BASETMsg;
import business.db.SPLY_QUOTE_PRC_CALC_BASETMsgArray;
import business.db.SPLY_QUOTE_RECTMsg;
import business.db.SPLY_QUOTE_SLS_CRTMsg;
import business.db.SPLY_QUOTE_SLS_CR_RECTMsg;
import business.db.TOCTMsg;
import business.db.VNDTMsg;
import business.db.VNDTMsgArray;
import business.db.WHTMsg;
import business.parts.NMZC002001PMsg;
import business.parts.NMZC002001_ContactPointInfoListPMsg;
import business.parts.NMZC610001PMsg;
import business.parts.NMZC610001_EligibleCheckListPMsg;
import business.parts.NMZC610001_TransactionRuleListPMsg;
import business.parts.NPZC113001PMsg;
import business.parts.NWZC150001PMsg;
import business.parts.NWZC150001_APMsg;
import business.parts.NWZC150001_cpoConfigPMsg;
import business.parts.NWZC150001_cpoCtacPsnInfoListPMsg;
import business.parts.NWZC150001_cpoDlvyInfoListPMsg;
import business.parts.NWZC150001_cpoSlsCrPMsg;
import business.parts.NWZC150001_priceListPMsg;
import business.parts.NWZC150002PMsg;
import business.parts.NWZC150003PMsg;
import business.parts.NWZC157001PMsg;
import business.parts.NWZC157001_xxPrcListPMsgArray;
import business.parts.NWZC167001PMsg;
import business.parts.NWZC167001_BPMsg;
import business.parts.NWZC167001_CPMsg;
import business.parts.NWZC167002PMsg;
import business.parts.NWZC167002PMsgArray;
import business.parts.NWZC167002_APMsg;

import com.canon.cusa.s21.api.NMZ.NMZC002001.NMZC002001;
import com.canon.cusa.s21.api.NMZ.NMZC002001.constant.NMZC002001Constant;
import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant;
import com.canon.cusa.s21.api.NPZ.NPZC113001.NPZC113001;
import com.canon.cusa.s21.api.NWZ.NWZC150001.NWZC150001;
import com.canon.cusa.s21.api.NWZ.NWZC150001.constant.NWZC150001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC157001.NWZC157001;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001Exchange;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ExchangeData;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ExchangePriceData;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001NumberingUtil;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001SalesRep;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_DRCTN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_CHRG_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_CHRG_TO;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SPLY_QUOTE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TXT_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPNumbering;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiMessageIdMgr;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;

/**
 * Supply Quote Update API
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/14   Fujitsu         T.Murai         Create          N/A
 * 2016/06/21   Fujitsu         Y.Kanefusa      Update          S21_NA#9437
 * 2016/07/28   Fujitsu         H.Ikeda         Update          S21_NA#11591
 * 2016/08/03   Fujitsu         M.Hara          Update          S21_NA#7704
 * 2016/08/25   Hitachi         Y.Takeno        Update          S21_NA#13681
 * 2016/08/30   Fujitsu         T.Murai         Update          S21_NA#11547
 * 2016/09/07   Fujitsu         H.Ikeda         Update          S21_NA#13918,13919
 * 2016/09/12   Fujitsu         Y.Kanefusa      Update          S21_NA#13684
 * 2016/09/13   Fujitsu         H.Ikeda         Update          S21_NA#13918
 * 2016/09/28   Fujitsu         H.Ikeda         Update          S21_NA#14328
 * 2016/09/30   Fujitsu         H.Ikeda         Update          S21_NA#14329
 * 2016/10/03   Fujitsu         H.Ikeda         Update          S21_NA#11595
 * 2016/10/04   Fujitsu         T.Murai         Update          S21_NA#13921
 * 2016/10/04   Fujitsu         N.Sugiura       Update          S21_NA#13170
 * 2016/10/26   Fujitsu         M.Ohnoi         Update          S21_NA#15569
 * 2016/12/12   Fujitsu         S.Ohki          Update          S21_NA#16344
 * 2016/12/13   Fujitsu         S.Ohki          Update          S21_NA#16344
 * 2017/02/21   Fujitsu         Y.Kanefusa      Update          S21_NA#17474
 * 2017/03/13   Fujitsu         T.Aoi           Update          S21_NA#16987
 * 2017/03/16   Fujitsu         M.Ohno          Update          S21_NA#18048
 * 2017/04/12   Fujitsu         Y.Kanefusa      Update          S21_NA#18235
 * 2017/05/19   Fujitsu         M.Yamada        Update          S21_NA#18634
 * 2017/06/09   Fujitsu         S.Takami        Update          S21_NA#18296
 * 2017/08/07   Fujitsu         Y.Kanefusa      Update          S21_NA#10347
 * 2017/08/09   Fujitsu         N.Sugiura       Update          S21_NA#8598
 * 2017/08/17   Fujitsu         S.Takami        Update          S21_NA#20659
 * 2017/08/29   Fujitsu         T.Murai         Update          S21_NA#20803
 * 2017/09/04   Fujitsu         K.Ishizuka      Update          S21_NA#17149(Sol#259)
 * 2017/10/18   Hitachi         J.Kim           Update          QC#21760
 * 2017/10/18   Fujitsu         W.Honda         Update          S21_NA#20246-1(L3 Sol#454)
 * 2017/10/26   Fujitsu         R.Nakamura      Update          S21_NA#21323
 * 2018/02/13   Fujitsu         T.Aoi           Update          S21_NA#21165
 * 2018/02/26   Fujitsu         Hd.Sugawara     Update          QC#22967
 * 2018/03/08   Fujitsu         T.Aoi           Update          S21_NA#24460
 * 2018/05/11   Fujitsu         T.Aoi           Update          S21_NA#22139
 * 2018/05/25   Fujitsu         H.Tomimatsu     Update          S21_NA#26145
 * 2018/06/26   Fujitsu         H.Nagashima     Update          S21_NA#23726
 * 2018/07/24   Fujitsu         T.Aoi           Update          S21_NA#26274
 * 2018/08/27   Fujitsu         M.Ishii         Update          S21_NA#26888
 * 2018/09/14   Fujitsu         Y.Kanefusa      Update          QC#9700
 * 2018/11/28   Fujitsu         Hd.Sugawara     Update          QC#29252
 * 2018/12/13   Fujitsu         K.Kato          Update          S21_NA#29315
 * 2019/01/08   Fujitsu         K.Kato          Update          QC#29241
 * 2019/01/16   Fujitsu         S.Kosaka        Update          QC#29642
 * 2019/01/29   Fujitsu         S.Kosaka        Update          QC#30036
 * 2019/05/29   Fujitsu         R.Nakamura      Update          S21_NA#50405
 * 2020/06/09   CITS            K.Ogino         Update          QC#56978
 * </pre>
 */
public class NWZC167001 extends S21ApiCommonBase {

    /** Online Batch Type */
    private ONBATCH_TYPE onBatchType = null;

    /** Global Company Code : Set From pMsg */
    private String glblCmpyCd = null;

    /** Sales Date : Set From pMsg */
    private String slsDt = null;

    /** Message ID Manager */
    private S21ApiMessageIdMgr msgIdMgr = null;

    /** Constructor */
    public NWZC167001() {
        super();
    }

    /**
     * Execute API
     * @param inPMsg NWZC167001PMsg
     * @param prmOnBatchType ONBATCH_TYPE
     */
    public void execute(final NWZC167001PMsg inPMsg, final ONBATCH_TYPE prmOnBatchType) {

        NWZC167001PMsg pMsg = new NWZC167001PMsg();
        try {
            EZDMsg.copy(inPMsg, null, pMsg, null);
            onBatchType = prmOnBatchType;
            glblCmpyCd = pMsg.glblCmpyCd.getValue();
            slsDt = pMsg.slsDt.getValue();
            msgIdMgr = new S21ApiMessageIdMgr();

            doProcess(pMsg);

        } finally {
            inPMsg.xxMsgIdList.setValidCount(pMsg.xxMsgIdList.getValidCount());
            EZDMsg.copy(pMsg, null, inPMsg, null);
            super.updateMessage(inPMsg, msgIdMgr);
        }
    }

    private void doProcess(NWZC167001PMsg pMsg) {

        // Parameter Check
        execDsCheck(pMsg);
        if (isErrorExists(pMsg)) {
            return;
        }

        setDefaultValues(pMsg);
        relationCheck(pMsg);
        if (isErrorExists(pMsg)) {
            return;
        }

        otherCheck(pMsg);
        if (isErrorExists(pMsg)) {
            return;
        }

        boolean allCancelFlg = true;
        for (int i = 0; i < pMsg.NWZC167002PMsg.getValidCount(); i++) {
            if (!RQST_TP_CAN.equals(pMsg.NWZC167002PMsg.no(i).xxRqstTpCd.getValue())) {
                allCancelFlg = false;
                break;
            }
        }

        // 2017/08/17 S21_NA#20659 Add Start
        if (pMsg.NWZC167002PMsg.getValidCount() == 0) {
            allCancelFlg = false;
        }
        // 2017/08/17 S21_NA#20659 Add End

        if (allCancelFlg) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxRqstTpCd, RQST_TP_CAN);
        }

        String hdrRqstTpCd = pMsg.xxRqstTpCd.getValue();

        if (RQST_TP_NEW.equals(hdrRqstTpCd)) {
            String splyQuoteNum = ZYPNumbering.getUniqueID(glblCmpyCd, QUOTE_SEQ_CD);
            ZYPEZDItemValueSetter.setValue(pMsg.splyQuoteNum, splyQuoteNum);

            if (!insertQuote(pMsg)) {
                return;
            }
            if (!insertQuoteDtlAndCalcBase(pMsg)) {
                return;
            }
            if (!insertQuoteSlsCr(pMsg)) {
                return;
            }
            if (!insertQuoteCtacPsn(pMsg)) {
                return;
            }

        } else if (RQST_TP_MOD.equals(hdrRqstTpCd)) {
            if (!updateQuote(pMsg)) {
                return;
            }
            if (!updateQuoteDtlAndCalcBase(pMsg)) {
                return;
            }
            if (!updateQuoteSlsCr(pMsg)) {
                return;
            }
            if (!updateQuoteCtacPsn(pMsg)) {
                return;
            }

        } else if (RQST_TP_CAN.equals(hdrRqstTpCd)) {
            cancelQuoteForAllLine(pMsg);
        }

        if (MODE_SUBMIT.equals(pMsg.procModeCd.getValue()) && !RQST_TP_CAN.equals(hdrRqstTpCd)) {
            if (!doSubmitProcess(pMsg)) {
                return;
            }
        }
    }

    private void execDsCheck(NWZC167001PMsg pMsg) {

        if (checkMode(pMsg)) {
            return;
        }

        if (checkEssentialForHeader(pMsg)) {
            return;
        }

        if (checkEssentialForDetail(pMsg.NWZC167002PMsg)) {
            return;
        }

        if (checkEssentialForCredit(pMsg)) {
            return;
        }

        if (checkEssentialForContact(pMsg)) {
            return;
        }

        if (checkRelationEssential(pMsg)) {
            return;
        }

        if (checkMasterForHeader(pMsg)) {
            return;
        }

        if (checkMasterForDetail(pMsg)) {
            return;
        }

        if (checkMasterForSalesCredit(pMsg)) {
            return;
        }

        if (checkMasterForContactPerson(pMsg)) {
            return;
        }

        return;
    }

    private boolean checkMode(NWZC167001PMsg pMsg) {

        boolean hasError = false;

        if (!MODE_SAVE.equals(pMsg.procModeCd.getValue()) && !MODE_SUBMIT.equals(pMsg.procModeCd.getValue())) {
            setMsgId(pMsg, NWZM0977E);
            hasError = true;
        }

        if (!RQST_TP_NEW.equals(pMsg.xxRqstTpCd.getValue()) && !RQST_TP_MOD.equals(pMsg.xxRqstTpCd.getValue())) {
            setMsgId(pMsg, NWZM0209E);
            hasError = true;
        }

        for (int i = 0; i < pMsg.NWZC167002PMsg.getValidCount(); i++) {
            NWZC167002PMsg pMsg2 = pMsg.NWZC167002PMsg.no(i);
            if (!RQST_TP_NEW.equals(pMsg2.xxRqstTpCd.getValue()) && !RQST_TP_MOD.equals(pMsg2.xxRqstTpCd.getValue()) && !RQST_TP_CAN.equals(pMsg2.xxRqstTpCd.getValue())) {
                setMsgId2(pMsg2, NWZM0209E);
                hasError = true;
            }
        }

        for (int i = 0; i < pMsg.B.getValidCount(); i++) {
            if (!RQST_TP_NEW.equals(pMsg.B.no(i).xxRqstTpCd_B.getValue()) && !RQST_TP_MOD.equals(pMsg.B.no(i).xxRqstTpCd_B.getValue()) && !RQST_TP_CAN.equals(pMsg.B.no(i).xxRqstTpCd_B.getValue())) {
                setMsgId(pMsg, NWZM0209E);
                hasError = true;
            }
        }

        for (int i = 0; i < pMsg.C.getValidCount(); i++) {
            if (!RQST_TP_NEW.equals(pMsg.C.no(i).xxRqstTpCd_C.getValue()) && !RQST_TP_MOD.equals(pMsg.C.no(i).xxRqstTpCd_C.getValue()) && !RQST_TP_CAN.equals(pMsg.C.no(i).xxRqstTpCd_C.getValue())) {
                setMsgId(pMsg, NWZM0209E);
                hasError = true;
            }
        }

        return hasError;
    }

    private boolean checkEssentialForHeader(NWZC167001PMsg pMsg) {

        boolean hasError = false;

        if (!ZYPCommonFunc.hasValue(pMsg.glblCmpyCd)) {
            setMsgId(pMsg, NWZM0011E);
            hasError = true;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.procModeCd)) {
            setMsgId(pMsg, NWZM0977E);
            hasError = true;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.slsDt)) {
            setMsgId(pMsg, NWZM0346E);
            hasError = true;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.usrId)) {
            setMsgId(pMsg, NWZM0336E);
            hasError = true;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.bizAppId)) {
            setMsgId(pMsg, NWZM0087E);
            hasError = true;
        }

        if (RQST_TP_MOD.equals(pMsg.xxRqstTpCd.getValue())) {
            if (!ZYPCommonFunc.hasValue(pMsg.splyQuoteNum)) {
                setMsgId(pMsg, NWZM1796E);
                hasError = true;
            }
        } else if (RQST_TP_NEW.equals(pMsg.xxRqstTpCd.getValue())) {
            pMsg.splyQuoteNum.clear();
        }

        if (!ZYPCommonFunc.hasValue(pMsg.cpoOrdTpCd)) {
            setMsgId(pMsg, NWZM1253E);
            hasError = true;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.splyQuoteCatgCd)) {
            setMsgId(pMsg, NWZM1797E);
            hasError = true;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.splyQuoteSrcTpCd)) {
            setMsgId(pMsg, NWZM1798E);
            hasError = true;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.dsOrdCatgCd)) {
            setMsgId(pMsg, NWZM1401E);
            hasError = true;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.billToCustAcctCd)) {
            setMsgId(pMsg, NWZM1377E);
            hasError = true;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.billToCustCd)) {
            setMsgId(pMsg, NWZM0617E);
            hasError = true;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.shipToCustAcctCd)) {
            setMsgId(pMsg, NWZM1379E);
            hasError = true;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.sellToCustCd)) {
            setMsgId(pMsg, NWZM1402E);
            hasError = true;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.soldToCustLocCd)) {
            setMsgId(pMsg, NWZM1403E);
            hasError = true;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.shpgSvcLvlCd)) {
            setMsgId(pMsg, NWZM0049E);
            hasError = true;
        }

        if (!ZYPCommonFunc.hasValue(pMsg.prcBaseDt)) {
            setMsgId(pMsg, NWZM1799E);
            hasError = true;
        }

        return hasError;
    }

    private boolean checkEssentialForDetail(NWZC167002PMsgArray pMsgAry) {

        boolean hasError = false;

        for (int i = 0; i < pMsgAry.getValidCount(); i++) {
            NWZC167002PMsg pMsg2 = pMsgAry.no(i);

            if (!ZYPCommonFunc.hasValue(pMsg2.xxRqstTpCd)) {
                setMsgId2(pMsg2, NWZM0209E);
                hasError = true;
            }

            if (!ZYPCommonFunc.hasValue(pMsg2.splyQuoteDtlLineNum)) {
                setMsgId2(pMsg2, NWZM1800E);
                hasError = true;
            }

            if (!ZYPCommonFunc.hasValue(pMsg2.splyQuoteDtlLineSubNum)) {
                setMsgId2(pMsg2, NWZM1801E);
                hasError = true;
            }

            if (!ZYPCommonFunc.hasValue(pMsg2.mdseCd)) {
                setMsgId2(pMsg2, NWZM0492E);
                hasError = true;
            }

            if (!ZYPCommonFunc.hasValue(pMsg2.ordQty)) {
                setMsgId2(pMsg2, NWZM1179E);
                hasError = true;
            }

            if (!ZYPCommonFunc.hasValue(pMsg2.ccyCd)) {
                setMsgId2(pMsg2, NWZM1258E);
                hasError = true;
            }
        }

        return hasError;
    }

    private boolean checkEssentialForCredit(NWZC167001PMsg pMsg) {

        boolean hasError = false;

        for (int i = 0; i < pMsg.B.getValidCount(); i++) {
            NWZC167001_BPMsg bPMsg = pMsg.B.no(i);

            if (!ZYPCommonFunc.hasValue(bPMsg.xxRqstTpCd_B)) {
                setMsgId(pMsg, NWZM0209E);
                hasError = true;
            }

            if (!ZYPCommonFunc.hasValue(bPMsg.slsRepTocCd_B)) {
                setMsgId(pMsg, NWZM0529E);
                hasError = true;
            }

            if (!RQST_TP_NEW.equals(bPMsg.xxRqstTpCd_B.getValue())) {
                if (!ZYPCommonFunc.hasValue(bPMsg.splyQuoteSlsCrPk_B)) {
                    setMsgId(pMsg, NWZM1803E);
                    hasError = true;
                }
            }
        }

        return hasError;
    }

    private boolean checkEssentialForContact(NWZC167001PMsg pMsg) {

        boolean hasError = false;

        for (int i = 0; i < pMsg.C.getValidCount(); i++) {
            NWZC167001_CPMsg cPMsg = pMsg.C.no(i);

            if (!ZYPCommonFunc.hasValue(cPMsg.xxRqstTpCd_C)) {
                setMsgId(pMsg, NWZM0209E);
                hasError = true;
            }

            if (!RQST_TP_NEW.equals(cPMsg.xxRqstTpCd_C.getValue())) {
                if (!ZYPCommonFunc.hasValue(cPMsg.splyQuoteCtacPsnPk_C)) {
                    setMsgId(pMsg, NWZM1804E);
                    hasError = true;
                }

                if (!ZYPCommonFunc.hasValue(cPMsg.ctacPsnPk_C)) {
                    setMsgId(pMsg, NWZM1805E);
                    hasError = true;
                }
            }

            if (!ZYPCommonFunc.hasValue(cPMsg.ctacPsnTpCd_C)) {
                setMsgId(pMsg, NWZM1806E);
                hasError = true;
            }

            if (!ZYPCommonFunc.hasValue(cPMsg.ctacPsnFirstNm_C)) {
                setMsgId(pMsg, NWZM1807E);
                hasError = true;
            }
        }

        return hasError;
    }

    private boolean checkRelationEssential(NWZC167001PMsg pMsg) {

        boolean hasError = false;

        if (FRT_CHRG_METH.CUST_ACCOUNT.equals(pMsg.frtChrgMethCd.getValue())) {
            if (!ZYPCommonFunc.hasValue(pMsg.carrCd)) {
                setMsgId(pMsg, NWZM0225E);
                hasError = true;
            }

            if (!ZYPCommonFunc.hasValue(pMsg.carrAcctNum)) {
                setMsgId(pMsg, NWZM0458E);
                hasError = true;
            }
        }

        boolean checkFlg = false;
        String lineNum = "";

        for (int i = 0; i < pMsg.NWZC167002PMsg.getValidCount(); i++) {
            NWZC167002PMsg aPMsg = pMsg.NWZC167002PMsg.no(i);

            if (checkFlg) {
                if (aPMsg.splyQuoteDtlLineNum.getValue().equals(lineNum)) {
                    if (!RQST_TP_CAN.equals(aPMsg.xxRqstTpCd.getValue())) {
                        setMsgId(pMsg, NWZM1812E);
                        hasError = true;
                    }
                } else {
                    checkFlg = false;
                }
            }

            if (LINE_SUB_NUM_000.equals(aPMsg.splyQuoteDtlLineSubNum.getValue()) && RQST_TP_CAN.equals(aPMsg.xxRqstTpCd.getValue())) {
                lineNum = aPMsg.splyQuoteDtlLineNum.getValue();
                checkFlg = true;
            }

            if (ZYPConstant.FLG_ON_Y.equals(aPMsg.manPrcFlg.getValue())) {
                if (ZYPCommonFunc.hasValue(aPMsg.ccyCd)) {
                    hasError = true;
                }
            }
        }

        return hasError;
    }

    private boolean checkMasterForHeader(NWZC167001PMsg pMsg) {

        boolean hasError = false;

        if (!NWXC150001DsCheck.checkCpoOrdTp(glblCmpyCd, pMsg.cpoOrdTpCd.getValue())) {
            setMsgId(pMsg, NWZM0109E);
            hasError = true;
        }

        if (!NWXC150001DsCheck.existsSplyQuoteCatg(glblCmpyCd, pMsg.splyQuoteCatgCd.getValue())) {
            setMsgId(pMsg, NWZM1813E);
            hasError = true;
        }

        if (!NWXC150001DsCheck.existsSplyQuoteSrcTp(glblCmpyCd, pMsg.splyQuoteSrcTpCd.getValue())) {
            setMsgId(pMsg, NWZM1814E);
            hasError = true;
        }

        if (!NWXC150001DsCheck.existsDsOrdCatg(glblCmpyCd, pMsg.dsOrdCatgCd.getValue())) {
            setMsgId(pMsg, NWZM1415E);
            hasError = true;
        }

        if (!NWXC150001DsCheck.existsDsOrdTp(glblCmpyCd, pMsg.dsOrdTpCd.getValue())) {
            setMsgId(pMsg, NWZM1815E);
            hasError = true;
        }

        if (!NWXC150001DsCheck.existsDsOrdRsn(glblCmpyCd, pMsg.dsOrdRsnCd.getValue())) {
            setMsgId(pMsg, NWZM1816E);
            hasError = true;
        }

        if (!NWXC150001DsCheck.existsAccount(glblCmpyCd, slsDt, pMsg.billToCustAcctCd.getValue())) {
            setMsgId(pMsg, NWZM1416E);
            hasError = true;
        }

        if (!NWXC150001DsCheck.existsSoldToLocation(glblCmpyCd, slsDt, pMsg.billToCustCd.getValue())) {
            setMsgId(pMsg, NWZM1444E);
            hasError = true;
        }

        if (!NWXC150001DsCheck.existsAccount(glblCmpyCd, slsDt, pMsg.shipToCustAcctCd.getValue())) {
            setMsgId(pMsg, NWZM1417E);
            hasError = true;
        }

        if (!NWXC150001DsCheck.existsBilgAttrCustAcct(glblCmpyCd, slsDt, pMsg.sellToCustCd.getValue())) {
            setMsgId(pMsg, NWZM1817E);
            hasError = true;
        }

        if (!NWXC150001DsCheck.existsSoldToLocation(glblCmpyCd, slsDt, pMsg.soldToCustLocCd.getValue())) {
            setMsgId(pMsg, NWZM1418E);
            hasError = true;
        }

        if (!NWXC150001DsCheck.existsShipToLocation(glblCmpyCd, slsDt, pMsg.shipToCustCd.getValue())) {
            setMsgId(pMsg, NWZM1445E);
            hasError = true;
        }

        if (!NWXC150001DsCheck.existsState(glblCmpyCd, pMsg.shipToStCd.getValue())) {
            setMsgId(pMsg, NWZM1446E);
            hasError = true;
        }

        if (!NWXC150001DsCheck.existsCtry(glblCmpyCd, pMsg.shipToCtryCd.getValue())) {
            setMsgId(pMsg, NWZM1448E);
            hasError = true;
        }

        if (!existsAuthPsn(pMsg.adminPsnCd.getValue())) {
            setMsgId(pMsg, NWZM1818E);
            hasError = true;
        }

        if (!existsAuthPsn(pMsg.splyQuoteSubmtPsnCd.getValue())) {
            setMsgId(pMsg, NWZM1819E);
            hasError = true;
        }

        if (!existsCarrCd(pMsg.carrCd.getValue())) {
            setMsgId(pMsg, NWZM1820E);
            hasError = true;
        }

        if (!NWXC150001DsCheck.existsPmtTermCashDisc(glblCmpyCd, pMsg.pmtTermCashDiscCd.getValue())) {
            setMsgId(pMsg, NWZM1821E);
            hasError = true;
        }

        if (!NWXC150001DsCheck.existsSalesRep(glblCmpyCd, pMsg.slsRepTocCd.getValue())) {
            setMsgId(pMsg, NWZM0054E);
            hasError = true;
        }

        if (!NWXC150001DsCheck.existsPrcCatg(glblCmpyCd, pMsg.prcCatgCd.getValue())) {
            setMsgId(pMsg, NWZM1419E);
            hasError = true;
        }

        if (!NWXC150001DsCheck.existsFlPrcList(glblCmpyCd, pMsg.flPrcListCd.getValue())) {
            setMsgId(pMsg, NWZM1420E);
            hasError = true;
        }

        if (!NWXC150001DsCheck.existsAssnPgm(glblCmpyCd, slsDt, pMsg.prcContrNum.getValue())) {
            setMsgId(pMsg, NWZM1421E);
            hasError = true;
        }

        if (!NWXC150001DsCheck.existsFrtCond(glblCmpyCd, pMsg.frtCondCd.getValue())) {
            setMsgId(pMsg, NWZM1426E);
            hasError = true;
        }

        if (!NWXC150001DsCheck.existsCarrSvcLvl(glblCmpyCd, pMsg.carrSvcLvlCd.getValue())) {
            setMsgId(pMsg, NWZM1427E);
            hasError = true;
        }

        if (!NWXC150001DsCheck.existsShpgSvcLvl(glblCmpyCd, pMsg.shpgSvcLvlCd.getValue())) {
            setMsgId(pMsg, NWZM1822E);
            hasError = true;
        }

        if (!NWXC150001DsCheck.existsFrtChrgTo(glblCmpyCd, pMsg.frtChrgToCd.getValue())) {
            setMsgId(pMsg, NWZM1823E);
            hasError = true;
        }

        if (!NWXC150001DsCheck.existsFrtChrgMeth(glblCmpyCd, pMsg.frtChrgMethCd.getValue())) {
            setMsgId(pMsg, NWZM1824E);
            hasError = true;
        }

        if (!NWXC150001DsCheck.existsDsPmtMeth(glblCmpyCd, pMsg.dsPmtMethCd.getValue())) {
            setMsgId(pMsg, NWZM1825E);
            hasError = true;
        }

        if (!NWXC150001DsCheck.existsSpclHdlgTp(glblCmpyCd, pMsg.spclHdlgTpCd.getValue())) {
            setMsgId(pMsg, NWZM1428E);
            hasError = true;
        }

        return hasError;
    }

    private boolean checkMasterForDetail(NWZC167001PMsg pMsg) {

        boolean hasError = false;

        for (int i = 0; i < pMsg.NWZC167002PMsg.getValidCount(); i++) {
            NWZC167002PMsg pMsg2 = pMsg.NWZC167002PMsg.no(i);

            if (!NWXC150001DsCheck.checkCpoOrdTp(glblCmpyCd, pMsg2.cpoOrdTpCd.getValue())) {
                setMsgId2(pMsg2, NWZM0109E);
                hasError = true;
            }

            if (!NWXC150001DsCheck.existsDsOrdLineCatg(glblCmpyCd, pMsg2.dsOrdLineCatgCd.getValue(), DS_ORD_LINE_DRCTN.OUTBOUND)) {
                setMsgId2(pMsg2, NWZM1431E);
                hasError = true;
            }

            if (!NWXC150001DsCheck.existsPkgUom(glblCmpyCd, pMsg2.custUomCd.getValue())) {
                setMsgId2(pMsg2, NWZM1826E);
                hasError = true;
            }

            if (!existsInvtyLocCd(pMsg2.invtyLocCd.getValue())) {
                setMsgId2(pMsg2, NWZM1827E);
                hasError = true;
            }

            if (!NWXC150001DsCheck.existsCcy(glblCmpyCd, pMsg2.ccyCd.getValue())) {
                setMsgId2(pMsg2, NWZM1828E);
                hasError = true;
            }

            if (!NWXC150001DsCheck.existsStkSts(glblCmpyCd, pMsg2.stkStsCd.getValue())) {
                setMsgId2(pMsg2, NWZM1829E);
                hasError = true;
            }

            if (!existsAllMdse(pMsg2.mdseCd.getValue())) {
                setMsgId2(pMsg2, NWZM0598E);
                hasError = true;
            }

            if (!NWXC150001DsCheck.existsMdseFrtCls(glblCmpyCd, pMsg2.mdseFrtClsCd.getValue())) {
                setMsgId2(pMsg2, NWZM1830E);
                hasError = true;
            }

            if (!NWXC150001DsCheck.existsMdsePrcGrp(glblCmpyCd, pMsg2.mdsePrcGrpCd.getValue())) {
                setMsgId2(pMsg2, NWZM1831E);
                hasError = true;
            }

            if (!NWXC150001DsCheck.existsRtlWh(glblCmpyCd, slsDt, pMsg2.rtlWhCd.getValue())) {
                setMsgId2(pMsg2, NWZM1433E);
                hasError = true;
            }
            if (!NWXC150001DsCheck.existsRtlSubWh(glblCmpyCd, slsDt, //
                    pMsg2.rtlWhCd.getValue(), pMsg2.rtlSwhCd.getValue())) {
                setMsgId2(pMsg2, NWZM1434E);
                hasError = true;
            }

            if (!NWXC150001DsCheck.existsOrdLineSrc(glblCmpyCd, pMsg2.ordLineSrcCd.getValue())) {
                setMsgId2(pMsg2, NWZM1432E);
                hasError = true;
            }

            if (!NWXC150001DsCheck.existsPrcCatg(glblCmpyCd, pMsg2.prcCatgCd.getValue())) {
                setMsgId2(pMsg2, NWZM1419E);
                hasError = true;
            }
        }

        return hasError;
    }

    private boolean checkMasterForSalesCredit(NWZC167001PMsg pMsg) {

        boolean hasError = false;

        for (int i = 0; i < pMsg.B.getValidCount(); i++) {
            NWZC167001_BPMsg pBmsg = pMsg.B.no(i);

            if (!NWXC150001DsCheck.existsSalesRep(glblCmpyCd, pBmsg.slsRepTocCd_B.getValue())) {
                setMsgId(pMsg, NWZM0054E);
                hasError = true;
            }

            if (!NWXC150001DsCheck.existsLineBizRoleTp(glblCmpyCd, pBmsg.slsRepRoleTpCd_B.getValue())) {
                setMsgId(pMsg, NWZM1832E);
                hasError = true;
            }
        }

        return hasError;
    }

    private boolean checkMasterForContactPerson(NWZC167001PMsg pMsg) {

        boolean hasError = false;

        for (int i = 0; i < pMsg.C.getValidCount(); i++) {
            NWZC167001_CPMsg pCmsg = pMsg.C.no(i);

            if (!NWXC150001DsCheck.existsCtacTp(glblCmpyCd, pCmsg.ctacPsnTpCd_C.getValue())) {
                setMsgId(pMsg, NWZM1833E);
                hasError = true;
            }

            if (!NWXC150001DsCheck.existsState(glblCmpyCd, pCmsg.ctacPsnStCd_C.getValue())) {
                setMsgId(pMsg, NWZM1834E);
                hasError = true;
            }

            if (!NWXC150001DsCheck.existsCtry(glblCmpyCd, pCmsg.ctacPsnCtryCd_C.getValue())) {
                setMsgId(pMsg, NWZM1835E);
                hasError = true;
            }
        }

        return hasError;
    }

    private boolean existsAuthPsn(String psnCd) {

        if (!ZYPCommonFunc.hasValue(psnCd)) {
            return true;
        }

        return (NWZC167001Query.getInstance().countPsnCd(glblCmpyCd, psnCd) != 0);
    }

    private boolean existsCarrCd(String carrCd) {

        if (!ZYPCommonFunc.hasValue(carrCd)) {
            return true;
        }

        OTBD_CARR_VTMsg otbdCarrTmsg = new OTBD_CARR_VTMsg();
        otbdCarrTmsg.setSQLID("002");
        otbdCarrTmsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        otbdCarrTmsg.setConditionValue("carrCd01", carrCd);

        OTBD_CARR_VTMsgArray otbdCarrTmsgArray = (OTBD_CARR_VTMsgArray) EZDTBLAccessor.findByCondition(otbdCarrTmsg);

        return (otbdCarrTmsgArray.length() > 0);
    }

    private boolean existsInvtyLocCd(String invtyLocCd) {

        if (!ZYPCommonFunc.hasValue(invtyLocCd)) {
            return true;
        }

        SHIP_FROM_LOC_LIST_VTMsg tmsg = new SHIP_FROM_LOC_LIST_VTMsg();
        tmsg.setSQLID("003");
        tmsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tmsg.setConditionValue("invtyLocCd01", invtyLocCd);

        SHIP_FROM_LOC_LIST_VTMsgArray tmsgArray = (SHIP_FROM_LOC_LIST_VTMsgArray) EZDTBLAccessor.findByCondition(tmsg);

        return (tmsgArray.length() > 0);
    }

    private boolean existsAllMdse(String mdseCd) {

        if (!ZYPCommonFunc.hasValue(mdseCd)) {
            return true;
        }

        ALL_MDSE_VTMsg tmsg = new ALL_MDSE_VTMsg();
        tmsg.setSQLID("001");
        tmsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tmsg.setConditionValue("mdseCd01", mdseCd);
        tmsg.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);

        ALL_MDSE_VTMsgArray tmsgArray = (ALL_MDSE_VTMsgArray) EZDTBLAccessor.findByCondition(tmsg);

        return (tmsgArray.length() > 0);
    }

    private void setDefaultValues(NWZC167001PMsg pMsg) {

        setDefaultFreightCharge(pMsg);
        setDefaultCarrier(pMsg);
        setDefaultVendor(pMsg);
    }

    private void setDefaultFreightCharge(NWZC167001PMsg pMsg) {

        FRT_CONDTMsg tMsg = new FRT_CONDTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.frtCondCd, pMsg.frtCondCd);
        tMsg = (FRT_CONDTMsg) S21CacheTBLAccessor.findByKey(tMsg);

        if (tMsg != null) {
            ZYPEZDItemValueSetter.setValue(pMsg.frtChrgMethCd, tMsg.frtChrgMethCd);
            ZYPEZDItemValueSetter.setValue(pMsg.frtChrgToCd, tMsg.frtChrgToCd);
        }
    }

    private void setDefaultCarrier(NWZC167001PMsg pMsg) {

        if (!ZYPCommonFunc.hasValue(pMsg.carrSvcLvlCd)) {
            return;
        }

        if (ZYPCommonFunc.hasValue(pMsg.carrCd)) {
            return;
        }

        String defaultCarrCd = NWZC167001Query.getInstance().getDefaultCarrCd(pMsg);
        if (ZYPCommonFunc.hasValue(defaultCarrCd)) {
            ZYPEZDItemValueSetter.setValue(pMsg.carrCd, defaultCarrCd);
        }
    }

    private void setDefaultVendor(NWZC167001PMsg pMsg) {

        String dsWhCd = ZYPCodeDataUtil.getVarCharConstValue("DROP_SHIP_RTL_WH_CD", glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(dsWhCd)) {
            setMsgId(pMsg, NWZM1503E);
            return;
        }

        for (int i = 0; i < pMsg.NWZC167002PMsg.getValidCount(); i++) {
            NWZC167002PMsg pMsg2 = pMsg.NWZC167002PMsg.no(i);
            if (dsWhCd.equals(pMsg2.rtlWhCd.getValue())) {
                NPZC113001PMsg aslApiMsg = callAslApi(pMsg2);
                if (S21ApiUtil.isXxMsgId(aslApiMsg)) {
                    List<String> msgIdList = S21ApiUtil.getXxMsgIdList(aslApiMsg);
                    for (String msgId : msgIdList) {
                        setMsgId2(pMsg2, msgId);
                        ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineNum, pMsg2.cpoDtlLineNum);
                        ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineSubNum, pMsg2.cpoDtlLineSubNum);
                    }
                    continue;
                }
                // QC#56978 Add Start
                if (ZYPCommonFunc.hasValue(aslApiMsg.vndCd)) {
                    VNDTMsg keyVndTMsg = new VNDTMsg();
                    keyVndTMsg.setSQLID("001");
                    keyVndTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
                    keyVndTMsg.setConditionValue("vndCd01", aslApiMsg.vndCd.getValue());

                    VNDTMsgArray rsltArray = (VNDTMsgArray) EZDTBLAccessor.findByCondition(keyVndTMsg);
                    if (rsltArray == null || rsltArray.getValidCount() == 0) {
                        setMsgId4(pMsg2, NWAM0983E, new String[] {aslApiMsg.vndCd.getValue() }); // TODO NEW Error Code
                        ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineNum, pMsg2.cpoDtlLineNum);
                        ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineSubNum, pMsg2.cpoDtlLineSubNum);
                    }
                }
                // QC#56978 Add End
                ZYPEZDItemValueSetter.setValue(pMsg2.invtyLocCd, aslApiMsg.vndCd);
            }
        }
    }

    private NPZC113001PMsg callAslApi(NWZC167002PMsg pMsg2) {

        NPZC113001PMsg aslPMsg = new NPZC113001PMsg();
        ZYPEZDItemValueSetter.setValue(aslPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(aslPMsg.slsDt, slsDt);
        ZYPEZDItemValueSetter.setValue(aslPMsg.mdseCd, pMsg2.mdseCd);

        NPZC113001 aslApi = new NPZC113001();
        aslApi.execute(aslPMsg, onBatchType);

        return aslPMsg;
    }

    private void relationCheck(NWZC167001PMsg pMsg) {

        relationHeaderCheck(pMsg);

        if (msgIdMgr.isXxMsgId()) {
            return;
        }

        relationDetailCheck(pMsg);
    }

    private void relationHeaderCheck(NWZC167001PMsg pMsg) {

        if (NWXC150001DsCheck.checkDsOrdTpAndDsOrdCatgRelation(glblCmpyCd, pMsg.dsOrdTpCd.getValue(), pMsg.dsOrdCatgCd.getValue())) {
            setMsgId(pMsg, NWZM1450E);
        }

        if (NWXC150001DsCheck.checkCpoOrdTpAndDsOrdRsnRelation(glblCmpyCd, slsDt, pMsg.dsOrdTpCd.getValue(), pMsg.dsOrdRsnCd.getValue())) {
            setMsgId(pMsg, NWZM1451E);
        }

        // 2018/12/12 QC#29315 Mod Start
        //if (NWXC150001DsCheck.checkFrtCondSvcLvlRelation(glblCmpyCd, slsDt, pMsg.dsOrdTpCd.getValue(), pMsg.frtCondCd.getValue(), pMsg.shpgSvcLvlCd.getValue(), pMsg.carrSvcLvlCd.getValue())) {
        if (NWXC150001DsCheck.checkFrtCondSvcLvlRelation(glblCmpyCd, slsDt, pMsg.dsOrdTpCd.getValue(), pMsg.frtCondCd.getValue(), pMsg.shpgSvcLvlCd.getValue(), pMsg.carrSvcLvlCd.getValue(), pMsg.shipToCustAcctCd.getValue(), pMsg.shipToLocNum.getValue())) {
        // 2018/12/12 QC#29315 Mod End
            setMsgId(pMsg, NWZM1458E);
        }

        if (NWXC150001DsCheck.checkAddlCarrAcctNumRelation(glblCmpyCd, pMsg.carrAcctNum.getValue(), pMsg.frtCondCd.getValue())) {
            setMsgId(pMsg, NWZM1459E);
        }

        // Add Start 2016/10/03 S21_NA#11595
        if (NWXC150001DsCheck.checkCarrSvcLevelRelation(glblCmpyCd, pMsg.carrSvcLvlCd.getValue(), pMsg.frtCondCd.getValue())) {
            setMsgId(pMsg, NWZM2010E);
        }
        // Add Start 2016/10/03 S21_NA#11595;
        // QC#23726 2018/06/25 add Start
        if (NWXC150001DsCheck.checkCustCarrSvcLvlRelation(glblCmpyCd, pMsg.dsOrdCatgCd.getValue(), pMsg.dsOrdTpCd.getValue()
                ,pMsg.dsOrdRsnCd.getValue(), pMsg.shipToCustAcctCd.getValue(), pMsg.carrSvcLvlCd.getValue(), pMsg.frtCondCd.getValue())) {
            setMsgId(pMsg, NWZM2267E);
        }
        // QC#23726 2018/06/25 add End

        if (!MODE_SAVE.equals(pMsg.procModeCd.getValue())) {
            if (NWXC150001DsCheck.checkPmntMethRelation(pMsg.dsPmtMethCd.getValue(), pMsg.dsCrCardPk.getValue())) {
                // Mod Start 2016/09/30 S21_NA#14329
                //setMsgId(pMsg, NWZM1460E);
                setMsgId(pMsg, NWZM2026E);
                // Mod Start 2016/09/30 S21_NA#14329
            }
        }
        // Add Start 2016/08/30 S21_NA#11547
        if (NWXC150001DsCheck.checkPmntMethAndTermRelation(glblCmpyCd, pMsg.dsPmtMethCd.getValue(), pMsg.pmtTermCashDiscCd.getValue())) {
            setMsgId(pMsg, NWZM2005E);
        }
        // Add Start 2016/08/30 S21_NA#11547

        if (NWXC150001DsCheck.checkSalesRepRelation(glblCmpyCd, pMsg.slsRepTocCd.getValue())) {
            setMsgId(pMsg, NWZM1463E);
        }

        if (NWXC150001DsCheck.checkPrcCatgRelation(glblCmpyCd, pMsg.dsOrdTpCd.getValue(), slsDt, pMsg.prcCatgCd.getValue())) {
            NWZC157001PMsg prcApiPMsg = callPricingApiForGetPrcList(pMsg, NWZC157001.GET_PRICE_LIST, PRC_CTX_TP.SALES_PRICING, ZYPConstant.FLG_ON_Y);

            if (S21ApiUtil.isXxMsgId(prcApiPMsg)) {
                for (String msgId : S21ApiUtil.getXxMsgIdList(prcApiPMsg)) {
                    setMsgId(pMsg, msgId);
                }
                return;
            }

            if (checkIncludePrcCatg(prcApiPMsg.xxPrcList, pMsg.prcCatgCd.getValue())) {
                setMsgId(pMsg, NWZM1464E);
            }
        }

        if (NWXC150001DsCheck.checkFlPrcListRelation(glblCmpyCd, pMsg.dsOrdTpCd.getValue(), slsDt, pMsg.flPrcListCd.getValue())) {
            NWZC157001PMsg prcApiPMsg = callPricingApiForGetPrcList(pMsg, NWZC157001.GET_PRICE_LIST, PRC_CTX_TP.FLOOR_PRICE, ZYPConstant.FLG_ON_Y);

            if (S21ApiUtil.isXxMsgId(prcApiPMsg)) {
                for (String msgId : S21ApiUtil.getXxMsgIdList(prcApiPMsg)) {
                    setMsgId(pMsg, msgId);
                }
                return;
            }

            if (checkIncludePrcCatg(prcApiPMsg.xxPrcList, pMsg.flPrcListCd.getValue())) {
                setMsgId(pMsg, NWZM1465E);
            }
        }

        checkAllCustCd(pMsg);
    }

    private NWZC157001PMsg callPricingApiForGetPrcList(NWZC167001PMsg pMsg, String modeCd, String prcCtxTpCd, String prcCatgOpeFlg) {

        NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxModeCd, modeCd);
        // QC#9437 2016/06/21 Mod Start
        // if (ZYPCommonFunc.hasValue(pMsg.prcBaseDt)) {
        //     ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, pMsg.prcBaseDt);
        // } else {
        //     ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, slsDt);
        // }
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, slsDt);
        // QC#9437 2016/06/21 Mod End
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcCtxTpCd, prcCtxTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdCatgCd, pMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdTpCd, pMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.lineBizTpCd, NWXC150001DsCheck.getLineBizTpCd(glblCmpyCd, slsDt, pMsg.dsOrdTpCd.getValue()));
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsAcctNum, pMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcContrNum, pMsg.prcContrNum);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.coaBrCd, getCoaBrCd(pMsg.slsRepTocCd.getValue()));
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxPrcCatgOpFlg, prcCatgOpeFlg);

        new NWZC157001().execute(prcApiPMsg, onBatchType);

        return prcApiPMsg;
    }

    private String getCoaBrCd(String slsRepCd) {

        TOCTMsg tMsg = new TOCTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.tocCd, slsRepCd);
        tMsg = (TOCTMsg) S21CacheTBLAccessor.findByKey(tMsg);

        if (tMsg == null) {
            return "";
        }

        return tMsg.coaBrCd.getValue();
    }

    private static boolean checkIncludePrcCatg(NWZC157001_xxPrcListPMsgArray xxPrcList, String value) {

        for (int i = 0; i < xxPrcList.getValidCount(); i++) {
            if (value.equals(xxPrcList.no(i).prcCatgCd.getValue())) {
                return false;
            }
        }

        return true;
    }

    private void checkAllCustCd(NWZC167001PMsg pMsg) {

        if (NWXC150001DsCheck.checkBillToRalation(glblCmpyCd, pMsg.billToCustCd.getValue(), pMsg.billToCustAcctCd.getValue())) {
            setMsgId(pMsg, NWZM1452E);
        }

        if (NWXC150001DsCheck.checkShipToRalation(glblCmpyCd, pMsg.shipToCustCd.getValue(), pMsg.shipToCustAcctCd.getValue())) {
            setMsgId(pMsg, NWZM1453E);
        }

        if (NWXC150001DsCheck.checkSoldToRalation(glblCmpyCd, pMsg.soldToCustLocCd.getValue(), pMsg.sellToCustCd.getValue())) {
            setMsgId(pMsg, NWZM1454E);
        }

        // Mod Start 2018/02/26 QC#22967
        //if (checkBillShipSoldRelation(pMsg)) {
        //    setMsgId(pMsg, NWZM1455E);
        //}
        checkBillShipSoldRelation(pMsg);
        // Mod End 2018/02/26 QC#22967
    }

    // Mod Start 2018/02/26 QC#22967
    //private boolean checkBillShipSoldRelation(NWZC167001PMsg pMsg) {
    private void checkBillShipSoldRelation(NWZC167001PMsg pMsg) {
        // Mod End 2018/02/26 QC#22967

        // Add Start 2018/02/26 QC#22967
        // Check Sold To(Customer Code) <- Ship To(Account Number)
        // relation.
        callCustInfoGetApiForCheckRelation(pMsg, //
                pMsg.soldToCustLocCd.getValue(), pMsg.shipToCustAcctCd.getValue(), //
                NWZM2254E);

        // Check Bill To(Customer Code) <- Sold To(Account Number)
        // relation.
        callCustInfoGetApiForCheckRelation(pMsg, //
                pMsg.billToCustCd.getValue(), pMsg.sellToCustCd.getValue(), //
                NWZM2255E);
        // Add End 2018/02/26 QC#22967

        // Del Start 2018/02/26 QC#22967
//        // 2017/06/09 S21_NA#18296 Mod Start
//        // QC#18235 2017/04/12 Add Start
//        //NMZC610001PMsg custInfoGetApiMsg = NWXC150001DsCheck.callCustInfoGetApi(glblCmpyCd, pMsg.billToCustCd.getValue(), pMsg.shipToCustCd.getValue(), pMsg.sellToCustCd.getValue(), onBatchType);
////        NMZC610001PMsg custInfoGetApiMsg = NWXC150001DsCheck.callCustInfoGetApi(glblCmpyCd, pMsg.billToCustCd.getValue(), pMsg.shipToCustCd.getValue(), pMsg.shipToCustAcctCd.getValue(), onBatchType);
//        // QC#18235 2017/04/12 Add End
//        NMZC610001PMsg custInfoGetApiMsg = NWXC150001DsCheck.callCustInfoGetApi(//
//                glblCmpyCd, //
//                pMsg.billToCustCd.getValue(), //
//                pMsg.sellToCustCd.getValue(), //
//                pMsg.shipToCustAcctCd.getValue(), //
//                onBatchType);
//        // 2017/06/09 S21_NA#18296 Mod End
//
//        if (S21ApiUtil.isXxMsgId(custInfoGetApiMsg)) {
//            for (String msgId : S21ApiUtil.getXxMsgIdList(custInfoGetApiMsg)) {
//                setMsgId(pMsg, msgId);
//            }
//            return false; // Mod 2016/10/04 S21_NA#13921 true -> false not NWZM1455E
//        }
//
//        for (int i = 0; i < custInfoGetApiMsg.EligibleCheckList.getValidCount(); i++) {
//            NMZC610001_EligibleCheckListPMsg eligiblePMsg = custInfoGetApiMsg.EligibleCheckList.no(i);
//            // 2017/06/09 S21_NA#18296 Mod Start
////            // QC#11591 2016/07/28 Mod Start
////            //if (ZYPConstant.FLG_OFF_N.equals(eligiblePMsg.dsAcctRelnBillToFlg_B.getValue()) //
////            //        || ZYPConstant.FLG_OFF_N.equals(eligiblePMsg.dsAcctRelnShipToFlg_S.getValue())) {
////            if (!ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnBillToFlg_B.getValue()) //
////                      || !ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnShipToFlg_S.getValue())) {
////                return true;
////            }
////            // QC#11591 2016/07/28 Mod End
//            if (!ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnBillToFlg_B.getValue()) //
//                    || !ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnRecipFlg.getValue())) {
//              return true;
//            }
//            // 2017/06/09 S21_NA#18296 Mod End
//        }
//
//        return false;
        // Del End 2018/02/26 QC#22967
    }

    // Add Start 2018/02/26 QC#22967
    /**
     * @param pMsg NWZC167001PMsg
     * @param billToCustCd String
     * @param acctNum String
     * @param errMsgId String
     */
    private void callCustInfoGetApiForCheckRelation(NWZC167001PMsg pMsg, String billToCustCd, String acctNum, String errMsgId) {
        NMZC610001PMsg custInfoGetApiMsg = NWXC150001DsCheck.callCustInfoGetApiBillTo( //
                glblCmpyCd, billToCustCd, acctNum, onBatchType);

        if (S21ApiUtil.isXxMsgId(custInfoGetApiMsg)) {
            for (String msgId : S21ApiUtil.getXxMsgIdList(custInfoGetApiMsg)) {
                setMsgId(pMsg, msgId);
            }
        }

        for (int i = 0; i < custInfoGetApiMsg.EligibleCheckList.getValidCount(); i++) {
            NMZC610001_EligibleCheckListPMsg eligiblePMsg = custInfoGetApiMsg.EligibleCheckList.no(i);
            if (!ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnBillToFlg_B.getValue())) {
                setMsgId(pMsg, errMsgId);
            }
        }
    }
    // Add End 2018/02/26 QC#22967

    private void relationDetailCheck(NWZC167001PMsg pMsg) {

        for (int i = 0; i < pMsg.NWZC167002PMsg.getValidCount(); i++) {
            NWZC167002PMsg pMsg2 = pMsg.NWZC167002PMsg.no(i);

            // Del Start 2017/10/26 QC#21323
//            if (NWXC150001DsCheck.checkDetailMdseRelation(glblCmpyCd, pMsg2.mdseCd.getValue(), pMsg2.custMdseCd.getValue(), pMsg.sellToCustCd.getValue())) {
//                setMsgId2(pMsg2, NWZM1468E);
//            }
            // Del End 2017/10/26 QC#21323

            if (NWXC150001DsCheck.checkDetailLineCatgRelation(glblCmpyCd, pMsg2.dsOrdLineCatgCd.getValue(), pMsg.dsOrdTpCd.getValue())) {
                setMsgId2(pMsg2, NWZM1469E);
            }

            if (NWXC150001DsCheck.checkRetailWhRelation(glblCmpyCd, slsDt, pMsg.dsOrdTpCd.getValue(), pMsg2.rtlWhCd.getValue(), pMsg2.rtlSwhCd.getValue())) {
                setMsgId2(pMsg2, NWZM1470E);
            }

            if (S21ApiUtil.isXxMsgId(pMsg2)) {
                ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineNum, pMsg2.cpoDtlLineNum);
                ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineSubNum, pMsg2.cpoDtlLineSubNum);
            }
        }
    }

    private static MDSETMsg getMdse(String glblCmpyCd, String mdseCd) {

        return NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);
    }

    private void checkQty(NWZC167001PMsg pMsg, NWZC167002PMsg pMsg2, MDSETMsg mdseTMsg, Map<String, Integer> qtyByMdse) {

        // S21_NA#18048 start
        // String mdseCd = pMsg2.mdseCd.getValue();
        // BigDecimal culcOrdQty =
        // BigDecimal.valueOf(qtyByMdse.get(mdseCd));
        // S21_NA#18048 end

        String rqstTpCd = pMsg2.xxRqstTpCd.getValue();
        if (RQST_TP_CAN.equals(rqstTpCd)) {
            return;
        }

        // S21_NA#18048 start
        String mdseCd = pMsg2.mdseCd.getValue();
        BigDecimal culcOrdQty = BigDecimal.valueOf(qtyByMdse.get(mdseCd));
        // S21_NA#18048 end

        if (checkMinusQty(pMsg2.ordQty.getValue())) {
            setMsgId2(pMsg2, NWZM1486E);
        }

        // 2019/01/08 QC#29241 Mod Start
        if (NWXC150001DsCheck.checkOrdQtyVldFlg(this.glblCmpyCd, pMsg.dsOrdTpCd.getValue(), pMsg2.dsOrdLineCatgCd.getValue(), this.slsDt)) {
            if (checkMinOrdQty(mdseTMsg.cpoMinOrdQty.getValue(), culcOrdQty)) {
                setMsgId2(pMsg2, NWZM1488E);
            }

            if (checkMaxOrdQty(mdseTMsg.cpoMaxOrdQty.getValue(), culcOrdQty)) {
                setMsgId2(pMsg2, NWZM1489E);
            }

            if (checkIncrOrdQty(mdseTMsg.cpoIncrOrdQty.getValue(), pMsg2.ordQty.getValue())) {
                setMsgId2(pMsg2, NWZM1492E);
            }
        }
        // 2019/01/08 QC#29241 Mod End
    }

    private boolean checkMinusQty(BigDecimal ordQty) {

        return (BigDecimal.ONE.compareTo(ordQty) > 0);
    }

    private boolean checkMinOrdQty(BigDecimal minOrdQty, BigDecimal ordQty) {

        if (!ZYPCommonFunc.hasValue(minOrdQty)) {
            return false;
        }

        if (!ZYPCommonFunc.hasValue(ordQty)) {
            return true;
        }

        return (minOrdQty.compareTo(ordQty) > 0);
    }

    private boolean checkMaxOrdQty(BigDecimal maxOrdQty, BigDecimal ordQty) {

        if (!ZYPCommonFunc.hasValue(maxOrdQty)) {
            return false;
        }

        if (!ZYPCommonFunc.hasValue(ordQty)) {
            return true;
        }

        return (maxOrdQty.compareTo(ordQty) < 0);
    }

    private boolean checkIncrOrdQty(BigDecimal incrOrdQty, BigDecimal ordQty) {

        if (!ZYPCommonFunc.hasValue(incrOrdQty)) {
            return false;
        }

        if (!ZYPCommonFunc.hasValue(ordQty)) {
            return true;
        }

        return ((ordQty.remainder(incrOrdQty)).compareTo(BigDecimal.ZERO) != 0);
    }

    private BigDecimal calcAmt(NWZC167001PMsg pMsg, NWZC167002PMsg pMsg2, BigDecimal price) {

        NWXC001001ExchangeData exchData = new NWXC001001ExchangeData();
        exchData.setGlblCmpyCd(glblCmpyCd);
        exchData.setCcyCd(getFuncCcy());
        exchData.setSlsDt(slsDt);

        List<NWXC001001ExchangePriceData> priceDataList = getUnitPrcExchDataList(price);
        exchData.setPriceData(priceDataList);

        NWXC001001Exchange.exchFuncUnitPrice(exchData);
        if (!exchData.getXxMsgIdList().isEmpty()) {
            for (String msgId : exchData.getXxMsgIdList()) {
                setMsgId2(pMsg2, msgId);
            }
            return null;
        }

        priceDataList = exchData.getPriceData();
        Iterator<NWXC001001ExchangePriceData> unitPrcExchDataItr = priceDataList.iterator();
        UnitPrcExchangeAmountData unitPrcExchAmtData = (UnitPrcExchangeAmountData) unitPrcExchDataItr.next();

        return unitPrcExchAmtData.getFuncUnitPrcAmt();
    }

    private List<NWXC001001ExchangePriceData> getUnitPrcExchDataList(BigDecimal dealAmt) {

        List<NWXC001001ExchangePriceData> unitPrcExchDataList = new ArrayList<NWXC001001ExchangePriceData>();
        UnitPrcExchangeAmountData unitPrcExchangeAmountData = new UnitPrcExchangeAmountData();
        unitPrcExchangeAmountData.setDealUnitPrcAmt(dealAmt);
        unitPrcExchDataList.add(unitPrcExchangeAmountData);

        return unitPrcExchDataList;
    }

    private String getFuncCcy() {

        GLBL_CMPYTMsg glblCmpyTMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(glblCmpyTMsg.glblCmpyCd, glblCmpyCd);
        glblCmpyTMsg = (GLBL_CMPYTMsg) S21CacheTBLAccessor.findByKey(glblCmpyTMsg);

        if (glblCmpyTMsg != null) {
            return glblCmpyTMsg.stdCcyCd.getValue();
        }

        return "";
    }

    private BigDecimal getFuncExchRate(String ccyCd) {

        ACCT_DLY_ACTL_EXCH_RATESTMsg tMsg = new ACCT_DLY_ACTL_EXCH_RATESTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.ccyCd, ccyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.actlExchRateEntDt, slsDt);
        tMsg = (ACCT_DLY_ACTL_EXCH_RATESTMsg) S21CacheTBLAccessor.findByKey(tMsg);

        if (tMsg != null) {
            return tMsg.actlExchRate.getValue();
        }

        return BigDecimal.ONE;
    }

    private void otherCheck(NWZC167001PMsg pMsg) {

        String firstAttrbTxt = NWZC167001Query.getInstance().getAttrbTxt(pMsg);

        checkCustPoNum(pMsg, firstAttrbTxt);

        if (checkCreditCardReq(pMsg, firstAttrbTxt)) {
            setMsgId(pMsg, NWZM1838E);
        }

        // QC#17474 2017/02/21 Add Start
        //if (checkCcReq(pMsg)) {
        //    setMsgId(pMsg, NWZM1839E);
        //}
        if (NWXC150001DsCheck.checkCcReq(glblCmpyCd, pMsg.billToCustAcctCd.getValue(), pMsg.billToCustCd.getValue(), pMsg.pmtTermCashDiscCd.getValue())) {
            setMsgId(pMsg, NWZM1839E);
        }
        // QC#17474 2017/02/21 Add End

        if (checkInternal(pMsg)) {
            setMsgId(pMsg, NWZM0028E);
        }
        checkSalable(pMsg);

        // get Map<MdseCd, Quantity>
        Map<String, Integer> qtyByMdse = countQty(pMsg);

        for (int i = 0; i < pMsg.NWZC167002PMsg.getValidCount(); i++) {
            NWZC167002PMsg pMsg2 = pMsg.NWZC167002PMsg.no(i);
            MDSETMsg mdseTmsg = getMdse(glblCmpyCd, pMsg2.mdseCd.getValue());
            otherCheckComponent(pMsg, pMsg2, mdseTmsg);

            String ordTakeMdseCd = getOrdTakeMdseCd(pMsg2);
            if (!ZYPCommonFunc.hasValue(ordTakeMdseCd)) {
                otherCheckOriginCountry(pMsg, pMsg2);
            }
            checkQty(pMsg, pMsg2, mdseTmsg, qtyByMdse);
        }

        // START 2017/10/8 J.Kim [QC#21760,DEL]
        // checkRddDt(pMsg);
        // END 2017/10/8 J.Kim [QC#21760,DEL]
        checkFrtChrg(pMsg);
        checkBillToInternal(pMsg);
        checkStockStatus(pMsg);
        checkSaleAbleSellCust(pMsg);
        checkEmbrago(pMsg);
        checkAvalWarehouse(pMsg);
        checkCtacPsn(pMsg);
        // QC#13918,13919 2016/09/07 Add Start
        otherEasyPackCheck(pMsg);
        // QC#13918,13919 2016/08/07 Add End
    }

    // QC#13918,13919 2016/09/07 Add Start
    private void otherEasyPackCheck(NWZC167001PMsg pMsg) {
        if (NWXC150001DsCheck.isEasyPack(glblCmpyCd, pMsg.dsOrdCatgCd.getValue(), pMsg.dsOrdTpCd.getValue())) {
            Set<String> setCompSet = new HashSet<String>(); // Add S21_NA#17149(Sol#259)
            for (int i = 0; i < pMsg.NWZC167002PMsg.getValidCount(); i++) {
                NWZC167002PMsg aPMsg = pMsg.NWZC167002PMsg.no(i);
                // Add Start S21_NA#17149(Sol#259) 
                if (setCompSet.contains(aPMsg.cpoDtlLineNum.getValue())) {
                    continue;
                }
                setCompSet.add(aPMsg.cpoDtlLineNum.getValue());
                // Add End S21_NA#17149(Sol#259)
                MDSETMsg tMsg = new MDSETMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
                MDSETMsg mdseTmsg = getMdse(glblCmpyCd, aPMsg.mdseCd.getValue());
                ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, mdseTmsg.mdseCd.getValue());
                tMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(tMsg);
                if (tMsg == null || !ZYPCommonFunc.hasValue(tMsg.easyPackTpCd)) {
                    setMsgId2(aPMsg, NWZM1530E);
                    continue;
                }
            }
            if (!NWXC150001DsCheck.isSplyPgmContr(glblCmpyCd, slsDt, pMsg.billToCustAcctCd.getValue(), pMsg.billToCustCd.getValue())) {
                // QC#13918 2016/09/13 Mod End
                //setMsgId(pMsg, NWZM1532E);
                setMsgId(pMsg, NWZM2008E);
                // QC#13918 2016/09/13 Mod End
            }
        }
    }

    // QC#13918,13919 2016/09/07 Add End

    private void checkCustPoNum(NWZC167001PMsg pMsg, String firstAttrbTxt) {

        if (!MODE_SUBMIT.equals(pMsg.procModeCd.getValue())) {
            return;
        }

        NMZC610001PMsg rsltPMsg = callTranGetApiByShip(pMsg, firstAttrbTxt);
        if (S21ApiUtil.isXxMsgId(rsltPMsg)) {
            copyMsgId(pMsg, S21ApiUtil.getXxMsgIdList(rsltPMsg));
            // setMsgId(pMsg, NWZM1455E); // 2016/10/04 S21_NA#13170 Del
        }

        for (int i = 0; i < rsltPMsg.TransactionRuleList.getValidCount(); i++) {
            NMZC610001_TransactionRuleListPMsg tranpMsg = rsltPMsg.TransactionRuleList.no(i);

            if (ZYPConstant.FLG_ON_Y.equals(tranpMsg.dsPoReqFlg.getValue())) {
                if (!ZYPCommonFunc.hasValue(pMsg.custIssPoNum)) {
                    setMsgId(pMsg, NWZM1836E);
                }

                if (!ZYPCommonFunc.hasValue(pMsg.custIssPoDt)) {
                    setMsgId(pMsg, NWZM1837E);
                }
            }
        }
    }

    private NMZC610001PMsg callTranGetApiByShip(NWZC167001PMsg pMsg, String firstAttrbTxt) {

        NMZC610001PMsg custInfoGetApiMsg = new NMZC610001PMsg();
        ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_TRANSACTION);
        ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.dsTrxRuleTpCd, firstAttrbTxt);
        // 2019/01/29 QC#30036 Add Start
        //ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.dsAcctNum_I1, pMsg.shipToCustCd);
        ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.dsAcctNum_I1, pMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.billToCustCd, pMsg.soldToCustLocCd);
        // 2019/01/29 QC#30036 Add End

        new NMZC610001().execute(custInfoGetApiMsg, onBatchType);

        return custInfoGetApiMsg;
    }

    private void copyMsgId(NWZC167001PMsg pMsg, List<String> list) {

        for (String msgId : list) {
            setMsgId(pMsg, msgId);
        }
    }

    private boolean checkCreditCardReq(NWZC167001PMsg pMsg, String firstAttrbTxt) {

        if (!MODE_SUBMIT.equals(pMsg.procModeCd.getValue())) {
            return false;
        }

        PMT_TERM_CASH_DISCTMsg pmtTermTMsg = getPmtTerm(pMsg);

        if (pmtTermTMsg == null) {
            return false;
        }

        NMZC610001PMsg rsltPMsg = callTranGetApiByBill(pMsg, firstAttrbTxt);
        if (S21ApiUtil.isXxMsgId(rsltPMsg)) {
            copyMsgId(pMsg, S21ApiUtil.getXxMsgIdList(rsltPMsg));
            // setMsgId(pMsg, NWZM1455E); // 2016/10/04 S21_NA#13170 Del
        }

        for (int i = 0; i < rsltPMsg.TransactionRuleList.getValidCount(); i++) {
            NMZC610001_TransactionRuleListPMsg tranpMsg = rsltPMsg.TransactionRuleList.no(i);

            if (ZYPConstant.FLG_ON_Y.equals(tranpMsg.dsCrCardReqFlg.getValue())) {
                // Mod Start 2018/11/28 QC#29252
                //if (!ZYPConstant.FLG_OFF_N.equals(pmtTermTMsg.pmtCcFlg.getValue())) {
                if (!ZYPConstant.FLG_ON_Y.equals(pmtTermTMsg.pmtCcFlg.getValue())) {
                    // Mod End 2018/11/28 QC#29252
                    return true;
                }
            }
        }

        return false;
    }

    private NMZC610001PMsg callTranGetApiByBill(NWZC167001PMsg pMsg, String firstAttrbTxt) {

        NMZC610001PMsg custInfoGetApiMsg = new NMZC610001PMsg();
        ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_TRANSACTION);
        ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.dsTrxRuleTpCd, firstAttrbTxt);
        ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.billToCustCd, pMsg.billToCustCd);

        new NMZC610001().execute(custInfoGetApiMsg, onBatchType);

        return custInfoGetApiMsg;
    }

//    private boolean checkCcReq(NWZC167001PMsg pMsg) {
//
//        PMT_TERM_CASH_DISCTMsg pmtTermTMsg = getPmtTerm(pMsg);
//        if (pmtTermTMsg == null) {
//            return false;
//        }
//
//        String cashOrCcReqFlg = NWZC167001Query.getInstance().getCashOrCcReqFlg(pMsg);
//        String pmtCcFlg = pmtTermTMsg.pmtCcFlg.getValue();
//        String pmtCashFlg = pmtTermTMsg.pmtCashFlg.getValue();
//
//        if (ZYPConstant.FLG_ON_Y.equals(cashOrCcReqFlg)) {
//            if (ZYPConstant.FLG_OFF_N.equals(pmtCcFlg) && ZYPConstant.FLG_OFF_N.equals(pmtCashFlg)) {
//                return true;
//            }
//        }
//
//        return false;
//    }

    private PMT_TERM_CASH_DISCTMsg getPmtTerm(NWZC167001PMsg pMsg) {

        PMT_TERM_CASH_DISCTMsg pmtTermTMsg = new PMT_TERM_CASH_DISCTMsg();
        ZYPEZDItemValueSetter.setValue(pmtTermTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pmtTermTMsg.pmtTermCashDiscCd, pMsg.pmtTermCashDiscCd);

        return (PMT_TERM_CASH_DISCTMsg) S21CacheTBLAccessor.findByKey(pmtTermTMsg);
    }

    private boolean checkInternal(NWZC167001PMsg pMsg) {

        String internalFlg = NWZC167001Query.getInstance().getIntrlFlg(pMsg);
        if (ZYPConstant.FLG_ON_Y.equals(internalFlg)) {
            return true;
        }

        return false;
    }

    private void checkSalable(NWZC167001PMsg pMsg) {

        for (int i = 0; i < pMsg.NWZC167002PMsg.getValidCount(); i++) {
            NWZC167002PMsg pMsg2 = pMsg.NWZC167002PMsg.no(i);

            String ordTakeMdseCd = getOrdTakeMdseCd(pMsg2);
            if (!ZYPCommonFunc.hasValue(ordTakeMdseCd)) {
                ordTakeMdseCd = pMsg2.mdseCd.getValue();
            }

            MDSETMsg mdseTmsg = getMdse(glblCmpyCd, ordTakeMdseCd);
            if (otherCheckOfSellToHold(mdseTmsg, pMsg2)) {
                return;
            }

            otherCheckMdseStsAndNewOrUsed(pMsg, pMsg2, mdseTmsg.mdseCd.getValue());
        }
    }

    private String getOrdTakeMdseCd(NWZC167002PMsg pMsg2) {

        ORD_TAKE_MDSETMsg ordTakeMdseTmsg = new ORD_TAKE_MDSETMsg();
        ZYPEZDItemValueSetter.setValue(ordTakeMdseTmsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(ordTakeMdseTmsg.mdseCd, pMsg2.mdseCd);
        ordTakeMdseTmsg = (ORD_TAKE_MDSETMsg) S21CacheTBLAccessor.findByKey(ordTakeMdseTmsg);

        if (ordTakeMdseTmsg == null) {
            return "";
        }

        return ordTakeMdseTmsg.ordTakeMdseCd.getValue();
    }

    private boolean otherCheckOfSellToHold(MDSETMsg mdseTmsg, NWZC167002PMsg pMsg2) {

        if (mdseTmsg == null || !RGTN_STS.READY_FOR_ORDER_TAKING.equals(mdseTmsg.rgtnStsCd.getValue())) {
            setMsgId2(pMsg2, NWZM0492E);
            return true;
        }

        // 2017/03/13 S21_NA#16987 Mod Start
        MDSETMsg dsMdseInfoMsg = new MDSETMsg();
        if (mdseTmsg != null) {
            ZYPEZDItemValueSetter.setValue(dsMdseInfoMsg.glblCmpyCd, mdseTmsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dsMdseInfoMsg.mdseCd, mdseTmsg.mdseCd);
            dsMdseInfoMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(dsMdseInfoMsg);
            if (dsMdseInfoMsg != null) {
                //if (ZYPConstant.FLG_ON_Y.equals(mdseTmsg.sellHldFlg.getValue())) {
                if (ZYPConstant.FLG_ON_Y.equals(mdseTmsg.sellHldFlg.getValue()) || ZYPConstant.FLG_OFF_N.equals(dsMdseInfoMsg.custOrdEnblFlg.getValue())) {
                    setMsgId2(pMsg2, NWZM0037E);
                    return true;
                }
            }
        }
        // 2017/03/13 S21_NA#16987 Mod End

        return false;
    }

    private void otherCheckMdseStsAndNewOrUsed(NWZC167001PMsg pMsg, NWZC167002PMsg pMsg2, String ordTakeMdseCd) {

        Map<String, String> resultMap = NWZC167001Query.getInstance().getMdseSts(glblCmpyCd, ordTakeMdseCd);

        if (!ZYPCommonFunc.hasValue(pMsg2.rtlSwhCd)) {
            checkSaleAbleNew(pMsg, pMsg2, resultMap);

        } else {
            BigDecimal invtyCostPct = NWZC167001Query.getInstance().getCostPct(pMsg, pMsg2.rtlSwhCd.getValue());
            if (BigDecimal.valueOf(IDX_100).compareTo(invtyCostPct) == 0) {
                checkSaleAbleNew(pMsg, pMsg2, resultMap);
            } else {
                if (ZYPConstant.FLG_OFF_N.equals(resultMap.get("USED_ONLY_AVAL_FLG"))) {
                    checkSaleAbleUsed(pMsg, pMsg2, resultMap);
                }
            }
        }
    }

    private void checkSaleAbleNew(NWZC167001PMsg pMsg, NWZC167002PMsg pMsg2, Map<String, String> resultMap) {

        if (ZYPConstant.FLG_OFF_N.equals(resultMap.get("CUST_ORD_ENTRY_AVAL_FLG"))) {
            Map<String, String> rltMap = NWZC167001Query.getInstance().getOrdCatgAndTp(pMsg, MDSE_ORD_ENTRY_AVALS);
            String ordCatg = (String) rltMap.get("DS_ORD_CATG_CD");
            String ordTp = (String) rltMap.get("DS_ORD_TP_CD");

            if (ZYPCommonFunc.hasValue(ordCatg) && ordCatg.equals(pMsg.dsOrdCatgCd.getValue())) {
                setMsgId2(pMsg2, NWZM0037E);
            }

            if (ZYPCommonFunc.hasValue(ordTp) && ordTp.equals(pMsg.dsOrdTpCd.getValue())) {
                setMsgId2(pMsg2, NWZM0037E);
            }
        }
    }

    private void checkSaleAbleUsed(NWZC167001PMsg pMsg, NWZC167002PMsg pMsg2, Map<String, String> resultMap) {

        if (ZYPConstant.FLG_OFF_N.equals(resultMap.get("CUST_ORD_ENTRY_AVAL_FLG"))) {
            Map<String, String> rltMap = NWZC167001Query.getInstance().getOrdCatgAndTp(pMsg, MDSE_ORD_ENTRY_USED_AVALS);
            String ordCatg = (String) rltMap.get("DS_ORD_CATG_CD");
            String ordTp = (String) rltMap.get("DS_ORD_TP_CD");

            if (ZYPCommonFunc.hasValue(ordCatg) && ordCatg.equals(pMsg.dsOrdCatgCd.getValue())) {
                setMsgId2(pMsg2, NWZM0037E);
            }

            if (ZYPCommonFunc.hasValue(ordTp) && ordTp.equals(pMsg.dsOrdTpCd.getValue())) {
                setMsgId2(pMsg2, NWZM0037E);
            }
        }
    }

    private Map<String, Integer> countQty(NWZC167001PMsg pMsg) {

        // Map<MdseCd , qty>
        Map<String, Integer> mdseMap = new HashMap<String, Integer>();

        for (int i = 0; i < pMsg.NWZC167002PMsg.getValidCount(); i++) {
            NWZC167002PMsg pMsg2 = pMsg.NWZC167002PMsg.no(i);
            String mdseCd = pMsg2.mdseCd.getValue();
            String rqstTpCd = pMsg2.xxRqstTpCd.getValue();
            if (RQST_TP_CAN.equals(rqstTpCd)) {
                continue;
            }
            if (mdseMap.containsKey(mdseCd)) {
                int qty = mdseMap.get(mdseCd);
                mdseMap.put(mdseCd, qty + pMsg2.ordQty.getValueInt());
            } else {
                mdseMap.put(mdseCd, pMsg2.ordQty.getValueInt());
            }
        }

        return mdseMap;
    }

    private void otherCheckComponent(NWZC167001PMsg pMsg, NWZC167002PMsg pMsg2, MDSETMsg mdseTmsg) {

        int count = 0;
        for (int i = 0; i < pMsg.NWZC167002PMsg.getValidCount(); i++) {
            if (pMsg2.splyQuoteDtlLineNum.getValue().equals(pMsg.NWZC167002PMsg.no(i).splyQuoteDtlLineNum.getValue())) {
                count++;
            }
        }

        if (count == 1) {
            // Single Order
            if (mdseTmsg == null || !RGTN_STS.READY_FOR_ORDER_TAKING.equals(mdseTmsg.rgtnStsCd.getValue())) {
                setMsgId2(pMsg2, NWZM0492E);
            } else if (ZYPConstant.FLG_OFF_N.equals(mdseTmsg.sellBySelfFlg.getValue())) {
                setMsgId2(pMsg2, NWZM0038E);
            }
        }
    }

    private void otherCheckOriginCountry(NWZC167001PMsg pMsg, NWZC167002PMsg pMsg2) {

        MDSETMsg mdseTmsg = getMdse(glblCmpyCd, pMsg2.mdseCd.getValue());

        if (mdseTmsg == null || !RGTN_STS.READY_FOR_ORDER_TAKING.equals(mdseTmsg.rgtnStsCd.getValue())) {
            setMsgId2(pMsg2, NWZM0492E);
        }
    }

    // Del Start 2018/02/26 QC#22967
    //private boolean checkLimitedCountry(NWZC167001PMsg pMsg, String ctryCd) {
    //
    //    return (NWZC167001Query.getInstance().countCtryCd(pMsg, ctryCd) != 0);
    //}
    // Del End 2018/02/26 QC#22967

    // START 2017/10/18 J.Kim [QC#21760,DEL]
    //private void checkRddDt(NWZC167001PMsg pMsg) {
    //
    //    if (FRT_CHRG_METH.PICK_UP_NO_CHARGE.equals(pMsg.frtChrgMethCd.getValue())) {
    //        if (ZYPCommonFunc.hasValue(pMsg.rddDt)) {
    //            setMsgId(pMsg, NWZM0147E);
    //        }
    //    }
    //}
    // END 2017/10/18 J.Kim [QC#21760,DEL]

    private void checkFrtChrg(NWZC167001PMsg pMsg) {

        for (int i = 0; i < pMsg.NWZC167002PMsg.getValidCount(); i++) {
            NWZC167002PMsg pMsg2 = pMsg.NWZC167002PMsg.no(i);
            MDSETMsg mdseTmsg = getMdse(glblCmpyCd, pMsg2.mdseCd.getValue());

            if (LINE_SUB_NUM_000.equals(pMsg2.cpoDtlLineSubNum.getValue())) {
                continue;
            }

            String intgFlg = ZYPConstant.FLG_OFF_N;
            if (ZYPConstant.FLG_OFF_N.equals(mdseTmsg.invtyCtrlFlg.getValue()) && ZYPConstant.FLG_OFF_N.equals(mdseTmsg.expItemFlg.getValue()) && MDSE_TP.REGULAR.equals(mdseTmsg.mdseTpCd.getValue())
                    || MDSE_TP.SALES_BOM.equals(mdseTmsg.mdseTpCd.getValue())) {
                intgFlg = ZYPConstant.FLG_ON_Y;
            }

            String thirdPtyVndDropYFlg = getThirdPtyVndDropYFlg(pMsg, pMsg2);
            if (ZYPConstant.FLG_ON_Y.equals(intgFlg) && !ZYPConstant.FLG_ON_Y.equals(thirdPtyVndDropYFlg)) {
                continue;
            }

            if (!CPO_ORD_TP.SALES.equals(pMsg.cpoOrdTpCd.getValue())) {
                if (FRT_CHRG_TO.CUSTOMER.equals(pMsg.frtChrgToCd.getValue()) && !FRT_CHRG_METH.PICK_UP_NO_CHARGE.equals(pMsg.frtChrgMethCd.getValue())) {
                    setMsgId(pMsg, NWZM0149E);
                }
            }

            SHPG_SVC_FRT_CHRG_RELNTMsg shpgSvcFrtChrgRelnTMsg = new SHPG_SVC_FRT_CHRG_RELNTMsg();
            shpgSvcFrtChrgRelnTMsg.setSQLID("001");
            shpgSvcFrtChrgRelnTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            shpgSvcFrtChrgRelnTMsg.setConditionValue("shpgSvcLvlCd01", pMsg.shpgSvcLvlCd.getValue());
            shpgSvcFrtChrgRelnTMsg.setConditionValue("frtChrgToCd01", pMsg.frtChrgToCd.getValue());
            shpgSvcFrtChrgRelnTMsg.setConditionValue("frtChrgMethCd01", pMsg.frtChrgMethCd.getValue());

            if (EZDTBLAccessor.count(shpgSvcFrtChrgRelnTMsg) == 0) {
                setMsgId2(pMsg2, NWZM0151E);

            } else {
                if (Arrays.asList(CPO_ORD_TP.EXPENSE, CPO_ORD_TP.TRIAL, CPO_ORD_TP.LOAN).contains(pMsg.cpoOrdTpCd)) {
                    if (ZYPConstant.FLG_OFF_N.equals(shpgSvcFrtChrgRelnTMsg.trialLoanUseFlg.getValue())) {
                        setMsgId(pMsg, NWZM0149E);
                    }
                }
            }
        }
    }

    private String getThirdPtyVndDropYFlg(NWZC167001PMsg pMsg, NWZC167002PMsg pMsg2) {

        SHIP_FROM_LOC_LIST_VTMsg vTmsg = new SHIP_FROM_LOC_LIST_VTMsg();
        vTmsg.setSQLID("001");
        vTmsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        vTmsg.setConditionValue("invtyLocCd01", pMsg2.invtyLocCd.getValue());
        SHIP_FROM_LOC_LIST_VTMsgArray vTmsgArray = (SHIP_FROM_LOC_LIST_VTMsgArray) EZDTBLAccessor.findByCondition(vTmsg);

        if (vTmsgArray.length() != 0) {
            if (LOC_TP.VENDOR.equals(vTmsgArray.no(0).invtyLocTpCd.getValue())) {
                return ZYPConstant.FLG_ON_Y;
            }
        }

        return ZYPConstant.FLG_OFF_N;
    }

    private void checkBillToInternal(NWZC167001PMsg pMsg) {

        String billToCust = NWZC167001Query.getInstance().getInternalBill(pMsg);
        if (ZYPCommonFunc.hasValue(billToCust)) {
            setMsgId(pMsg, NWZM0028E);
        }
    }

    private void checkStockStatus(NWZC167001PMsg pMsg) {

        for (int i = 0; i < pMsg.NWZC167002PMsg.getValidCount(); i++) {
            NWZC167002PMsg pMsg2 = pMsg.NWZC167002PMsg.no(i);

            AVAL_INVTY_APP_IDTMsg resAvalInvtyAppIdTMsg = new AVAL_INVTY_APP_IDTMsg();
            ZYPEZDItemValueSetter.setValue(resAvalInvtyAppIdTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(resAvalInvtyAppIdTMsg.bizAppId, "NWAL0010");
            ZYPEZDItemValueSetter.setValue(resAvalInvtyAppIdTMsg.locStsCd, LOC_STS.DC_STOCK);
            ZYPEZDItemValueSetter.setValue(resAvalInvtyAppIdTMsg.stkStsCd, pMsg2.stkStsCd);
            resAvalInvtyAppIdTMsg = (AVAL_INVTY_APP_IDTMsg) S21CacheTBLAccessor.findByKey(resAvalInvtyAppIdTMsg);

            if (resAvalInvtyAppIdTMsg == null) {
                setMsgId2(pMsg2, NWZM0248E);
            }
        }
    }

    private void checkSaleAbleSellCust(NWZC167001PMsg pMsg) {

        String sellToCust = NWZC167001Query.getInstance().getSaleableSell(pMsg);
        if (ZYPCommonFunc.hasValue(sellToCust)) {
            setMsgId(pMsg, NWZM0417E);
        }
    }

    private void checkEmbrago(NWZC167001PMsg pMsg) {

        String embragoFlg = NWZC167001Query.getInstance().getEmbargoFlag(pMsg);
        if (ZYPCommonFunc.hasValue(embragoFlg)) {
            setMsgId(pMsg, NWZM0420E);
        }
    }

    private void checkAvalWarehouse(NWZC167001PMsg pMsg) {

        HashMap<String, Integer> cntAvalWarehouseMap = new HashMap<String, Integer>();

        for (int i = 0; i < pMsg.NWZC167002PMsg.getValidCount(); i++) {
            NWZC167002PMsg pMsg2 = pMsg.NWZC167002PMsg.no(i);

            if (!ZYPCommonFunc.hasValue(pMsg2.invtyLocCd)) {
                continue;
            }

            String invtyLocCd = pMsg2.invtyLocCd.getValue();

            final WHTMsg whTMsg = new WHTMsg();
            whTMsg.setSQLID("009");
            whTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            whTMsg.setConditionValue("whCd01", invtyLocCd);

            if (EZDTBLAccessor.count(whTMsg) > 0) {
                String dsOrdTpCd = pMsg.dsOrdTpCd.getValue();
                String key = glblCmpyCd.concat(dsOrdTpCd).concat(invtyLocCd);
                Integer cntWH = cntAvalWarehouseMap.get(key);

                if (cntWH == null) {
                    cntWH = NWZC167001Query.getInstance().cntAvalWarehouse(pMsg, invtyLocCd);
                    cntAvalWarehouseMap.put(key, cntWH);
                }

                if (cntWH <= 0) {
                    setMsgId2(pMsg2, NWZM1262E);
                }
            }
        }
    }

    private void checkCtacPsn(NWZC167001PMsg pMsg) {

        for (int i = 0; i < pMsg.C.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(pMsg.C.no(i).ctacPsnPk_C)) {
                continue;
            }

            CTAC_PSNTMsg ctacTmsg = new CTAC_PSNTMsg();
            ZYPEZDItemValueSetter.setValue(ctacTmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(ctacTmsg.ctacPsnPk, pMsg.C.no(i).ctacPsnPk_C);
            ctacTmsg = (CTAC_PSNTMsg) S21CacheTBLAccessor.findByKey(ctacTmsg);

            if (ctacTmsg == null) {
                setMsgId(pMsg, NWZM1138E);
            }
        }
    }

    private boolean insertQuote(NWZC167001PMsg pMsg) {

        SPLY_QUOTETMsg quoteTMsg = new SPLY_QUOTETMsg();
        setQuoteTMsg(pMsg, quoteTMsg);

        SPLY_QUOTE_RECTMsg quoteRecTMsg = new SPLY_QUOTE_RECTMsg();
        EZDMsg.copy(quoteTMsg, null, quoteRecTMsg, null);

        S21FastTBLAccessor.insert(quoteTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(quoteTMsg.getReturnCode())) {
            setMsgId(pMsg, NWZM1888E);
            return false;
        }

        BIZ_PROC_LOGTMsg bizProcLogTMsg = getBizProcLogTMsg(EVENT_QUOTE_CRAT, pMsg.splyQuoteNum.getValue());
        ZYPEZDItemValueSetter.setValue(quoteRecTMsg.bizProcLogPk, bizProcLogTMsg.bizProcLogPk);

        S21FastTBLAccessor.insert(bizProcLogTMsg);
        S21FastTBLAccessor.insert(quoteRecTMsg);

        return true;
    }

    private void setQuoteTMsg(NWZC167001PMsg pMsg, SPLY_QUOTETMsg quoteTMsg) {

        String bkupAdminPsnCd = quoteTMsg.adminPsnCd.getValue();
        EZDMsg.copy(pMsg, null, quoteTMsg, null);
        // QC#14328 2016/09/28 Mod Start
        //if (MODE_SUBMIT.equals(pMsg.procModeCd.getValue()) && ZYPCommonFunc.hasValue(bkupAdminPsnCd)) {
        if (ZYPCommonFunc.hasValue(bkupAdminPsnCd)) {
        // QC#14328 2016/09/28 Mod End
            ZYPEZDItemValueSetter.setValue(quoteTMsg.adminPsnCd, bkupAdminPsnCd);
        }

        String currentTs = ZYPDateUtil.getCurrentSystemTime(DATE_TIME_PATTERN);
        if (RQST_TP_NEW.equals(pMsg.xxRqstTpCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(quoteTMsg.splyQuoteCratTs, currentTs);
        }

        if (MODE_SUBMIT.equals(pMsg.procModeCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(quoteTMsg.splyQuoteSubmtTs, currentTs);

            if (ZYPCommonFunc.hasValue(pMsg.rddDt)) {
                ZYPEZDItemValueSetter.setValue(quoteTMsg.rddDt, pMsg.rddDt);
            } else {
                // 2018/03/08 QC#24460 Mod Start
                //String addDays = ZYPDateUtil.addDays(slsDt, 1);
                //ZYPEZDItemValueSetter.setValue(pMsg.rddDt, addDays);
                ZYPEZDItemValueSetter.setValue(pMsg.rddDt, slsDt);
                // 2018/03/08 QC#24460 Mod End
                //ZYPEZDItemValueSetter.setValue(quoteTMsg.rddDt, addDays); //2018/03/08 QC#24460 Del
            }
        } else {
            quoteTMsg.splyQuoteSubmtPsnCd.clear();
        }

        ZYPEZDItemValueSetter.setValue(quoteTMsg.splyQuoteVldThruDt, ZYPDateUtil.addDays(pMsg.splyQuoteDt.getValue(), pMsg.splyQuoteVldDaysAot.getValueInt()));
        ZYPEZDItemValueSetter.setValue(quoteTMsg.splyQuoteStsCd, getQuoteSts(pMsg.procModeCd.getValue(), pMsg.xxRqstTpCd.getValue()));
        ZYPEZDItemValueSetter.setValue(quoteTMsg.cpoSrcTpCd, CPO_SRC_TP.SUPPLY_QUOTE_ENTRY);

        // Add Start 2017/10/13 QC#20246-1(L3 Sol#454)
        ZYPEZDItemValueSetter.setValue(quoteTMsg.sellToFirstRefCmntTxt, pMsg.sellToFirstRefCmntTxt);
        // Add End 2017/10/13 QC#20246-1(L3 Sol#454)

        if (!ZYPCommonFunc.hasValue(pMsg.prcBaseDt.getValue())) {
            ZYPEZDItemValueSetter.setValue(quoteTMsg.prcBaseDt, slsDt);
        }
    }

    private BIZ_PROC_LOGTMsg getBizProcLogTMsg(String eventId, String splyQuoteNum) {

        BIZ_PROC_LOGTMsg bizProcLogTMsg = new BIZ_PROC_LOGTMsg();
        ZYPEZDItemValueSetter.setValue(bizProcLogTMsg.bizProcLogPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.BIZ_PROC_LOG_SQ));
        ZYPEZDItemValueSetter.setValue(bizProcLogTMsg.subSysId, SUB_SYS_ID_NWZ);
        ZYPEZDItemValueSetter.setValue(bizProcLogTMsg.procId, PROC_ID_OM);
        ZYPEZDItemValueSetter.setValue(bizProcLogTMsg.eventId, eventId);
        ZYPEZDItemValueSetter.setValue(bizProcLogTMsg.docTpCd, DOC_TP_CD);
        ZYPEZDItemValueSetter.setValue(bizProcLogTMsg.prntDocId, splyQuoteNum);

        return bizProcLogTMsg;
    }

    private BIZ_PROC_LOGTMsg getBizProcLogTMsgForLine(String eventId, String splyQuoteNum, String docId) {

        BIZ_PROC_LOGTMsg bizProcLogTMsg = new BIZ_PROC_LOGTMsg();
        ZYPEZDItemValueSetter.setValue(bizProcLogTMsg.bizProcLogPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.BIZ_PROC_LOG_SQ));
        ZYPEZDItemValueSetter.setValue(bizProcLogTMsg.subSysId, SUB_SYS_ID_NWZ);
        ZYPEZDItemValueSetter.setValue(bizProcLogTMsg.procId, PROC_ID_OM);
        ZYPEZDItemValueSetter.setValue(bizProcLogTMsg.eventId, eventId);
        ZYPEZDItemValueSetter.setValue(bizProcLogTMsg.docTpCd, DOC_TP_CD);
        ZYPEZDItemValueSetter.setValue(bizProcLogTMsg.docId, docId);
        ZYPEZDItemValueSetter.setValue(bizProcLogTMsg.prntDocId, splyQuoteNum);

        return bizProcLogTMsg;
    }

    private String getQuoteSts(String procMode, String rqstTpCd) {

        if (RQST_TP_CAN.equals(rqstTpCd)) {
            return SPLY_QUOTE_STS.CANCELLED;
        } else if (MODE_SUBMIT.equals(procMode)) {
            return SPLY_QUOTE_STS.SUBMITTED;
        }

        return SPLY_QUOTE_STS.SAVED;
    }

    private boolean insertQuoteDtlAndCalcBase(NWZC167001PMsg pMsg) {

        // 2017/08/17 S21_NA#20659 Add Start
        if (pMsg.NWZC167002PMsg.getValidCount() == 0) {
            return true;
        }
        // 2017/08/17 S21_NA#20659 Add End

        List<BIZ_PROC_LOGTMsg> bizProcLogTMsgList = new ArrayList<BIZ_PROC_LOGTMsg>();
        List<SPLY_QUOTE_DTLTMsg> quoteDtlTMsgList = new ArrayList<SPLY_QUOTE_DTLTMsg>();
        List<SPLY_QUOTE_DTL_RECTMsg> quoteDtlReqTMsgList = new ArrayList<SPLY_QUOTE_DTL_RECTMsg>();
        List<SPLY_QUOTE_PRC_CALC_BASETMsg> calcBaseTMsgList = new ArrayList<SPLY_QUOTE_PRC_CALC_BASETMsg>();
        List<SPLY_QUOTE_CALC_BASE_RECTMsg> calcBaseReqTMsgList = new ArrayList<SPLY_QUOTE_CALC_BASE_RECTMsg>();

        for (int i = 0; i < pMsg.NWZC167002PMsg.getValidCount(); i++) {
            NWZC167002PMsg linePMsg = pMsg.NWZC167002PMsg.no(i);

            // Biz Proc Log
            BIZ_PROC_LOGTMsg bizProcLogTMsg = getBizProcLogTMsgForLine(EVENT_QUOTE_CRAT, pMsg.splyQuoteNum.getValue(), getDocId(linePMsg));
            BigDecimal bizProcLogPk = bizProcLogTMsg.bizProcLogPk.getValue();
            bizProcLogTMsgList.add(bizProcLogTMsg);

            // Quote Detail And Calc Base
            SPLY_QUOTE_DTLTMsg quoteDtlTMsg = new SPLY_QUOTE_DTLTMsg();
            setQuoteDtlAndCalcBaseTMsg(quoteDtlTMsg, pMsg, linePMsg, bizProcLogPk, calcBaseTMsgList, calcBaseReqTMsgList);
            quoteDtlTMsgList.add(quoteDtlTMsg);

            // Quote Detail Record
            SPLY_QUOTE_DTL_RECTMsg quoteDtlRecTMsg = new SPLY_QUOTE_DTL_RECTMsg();
            EZDMsg.copy(quoteDtlTMsg, null, quoteDtlRecTMsg, null);
            ZYPEZDItemValueSetter.setValue(quoteDtlRecTMsg.bizProcLogPk, bizProcLogPk);
            quoteDtlReqTMsgList.add(quoteDtlRecTMsg);
        }

        int result = S21FastTBLAccessor.insert(quoteDtlTMsgList.toArray(new SPLY_QUOTE_DTLTMsg[0]));
        if (result != quoteDtlTMsgList.size()) {
            setMsgId(pMsg, NWZM1872E);
            return false;
        }

        if (calcBaseTMsgList.size() > 0) {
            result = S21FastTBLAccessor.insert(calcBaseTMsgList.toArray(new SPLY_QUOTE_PRC_CALC_BASETMsg[0]));
            if (result != calcBaseTMsgList.size()) {
                setMsgId(pMsg, NWZM1874E);
                return false;
            }
        }

        S21FastTBLAccessor.insert(bizProcLogTMsgList.toArray(new BIZ_PROC_LOGTMsg[0]));
        S21FastTBLAccessor.insert(quoteDtlReqTMsgList.toArray(new SPLY_QUOTE_DTL_RECTMsg[0]));
        if (calcBaseReqTMsgList.size() > 0) {
            S21FastTBLAccessor.insert(calcBaseReqTMsgList.toArray(new SPLY_QUOTE_CALC_BASE_RECTMsg[0]));
        }

        return true;
    }

    private void setQuoteDtlAndCalcBaseTMsg(SPLY_QUOTE_DTLTMsg quoteDtlTMsg, NWZC167001PMsg pMsg, NWZC167002PMsg linePMsg, BigDecimal bizProcPk, List<SPLY_QUOTE_PRC_CALC_BASETMsg> insCalcBaseTMsgList,
            List<SPLY_QUOTE_CALC_BASE_RECTMsg> calcBaseReqTMsgList) {

        EZDMsg.copy(linePMsg, null, quoteDtlTMsg, null);

        String splyQuoteNum = pMsg.splyQuoteNum.getValue();
        MDSETMsg mdseMsg = getMdse(glblCmpyCd, linePMsg.mdseCd.getValue());

        ZYPEZDItemValueSetter.setValue(quoteDtlTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(quoteDtlTMsg.splyQuoteNum, splyQuoteNum);
        ZYPEZDItemValueSetter.setValue(quoteDtlTMsg.mdseNm, mdseMsg.mdseNm);
        ZYPEZDItemValueSetter.setValue(quoteDtlTMsg.splyQuoteLineStsCd, getQuoteSts(pMsg.procModeCd.getValue(), linePMsg.xxRqstTpCd.getValue()));
        ZYPEZDItemValueSetter.setValue(quoteDtlTMsg.prcCatgCd, pMsg.prcCatgCd);
        ZYPEZDItemValueSetter.setValue(quoteDtlTMsg.flPrcListCd, pMsg.flPrcListCd);

        BigDecimal dealNetUnitPrcAmt = linePMsg.dealNetUnitPrcAmt.getValue();
        BigDecimal funcNetUnitPrc = calcAmt(pMsg, linePMsg, dealNetUnitPrcAmt);
        BigDecimal dealQuoteDtlSlsPrc = linePMsg.dealSplyQuoteDtlSlsAmt.getValue();
        BigDecimal dealQuoteDtlNetPrc = linePMsg.dealSplyQuoteDtlNetAmt.getValue();

        ZYPEZDItemValueSetter.setValue(quoteDtlTMsg.dealNetUnitPrcAmt, dealNetUnitPrcAmt);
        ZYPEZDItemValueSetter.setValue(quoteDtlTMsg.funcNetUnitPrcAmt, funcNetUnitPrc);
        ZYPEZDItemValueSetter.setValue(quoteDtlTMsg.dealSplyQuoteDtlSlsAmt, dealQuoteDtlSlsPrc);
        ZYPEZDItemValueSetter.setValue(quoteDtlTMsg.funcSplyQuoteDtlSlsAmt, calcAmt(pMsg, linePMsg, dealQuoteDtlSlsPrc));
        ZYPEZDItemValueSetter.setValue(quoteDtlTMsg.dealSplyQuoteDtlNetAmt, dealQuoteDtlNetPrc);
        ZYPEZDItemValueSetter.setValue(quoteDtlTMsg.funcSplyQuoteDtlNetAmt, calcAmt(pMsg, linePMsg, dealQuoteDtlNetPrc));
        ZYPEZDItemValueSetter.setValue(quoteDtlTMsg.dealPrcListPrcAmt, linePMsg.dealPrcListPrcAmt);
        ZYPEZDItemValueSetter.setValue(quoteDtlTMsg.funcPrcListPrcAmt, linePMsg.funcPrcListPrcAmt);
        ZYPEZDItemValueSetter.setValue(quoteDtlTMsg.exchRate, getFuncExchRate(getFuncCcy()));
        // QC#10347 2017/07/24 Add Start
        ZYPEZDItemValueSetter.setValue(quoteDtlTMsg.prcBaseDt, linePMsg.prcBaseDt);
        // QC#10347 2017/07/24 Add End
        // 2018/03/08 QC#24460 Mod Start
        // 2018/02/13 QC#21165 Add Start
        //ZYPEZDItemValueSetter.setValue(quoteDtlTMsg.rddDt, linePMsg.rddDt);
        if (ZYPCommonFunc.hasValue(linePMsg.rddDt)) {
            ZYPEZDItemValueSetter.setValue(quoteDtlTMsg.rddDt, linePMsg.rddDt);
        } else {
            ZYPEZDItemValueSetter.setValue(linePMsg.rddDt, slsDt);
        }
        // 2018/02/13 QC#21165 Add End
        // 2018/03/08 QC#24460 Mod End

        // Delete Calc Base
        removeQuoteCalcBase(pMsg, linePMsg);

        for (int i = 0; i < linePMsg.A.getValidCount(); i++) {
            SPLY_QUOTE_PRC_CALC_BASETMsg calcBaseTMsg = getCalcBaseTMsg(linePMsg.A.no(i), splyQuoteNum);
            SPLY_QUOTE_CALC_BASE_RECTMsg calcBaseRecTMsg = new SPLY_QUOTE_CALC_BASE_RECTMsg();
            EZDMsg.copy(calcBaseTMsg, null, calcBaseRecTMsg, null);
            ZYPEZDItemValueSetter.setValue(calcBaseRecTMsg.bizProcLogPk, bizProcPk);
            insCalcBaseTMsgList.add(calcBaseTMsg);
            // 2018/09/14 QC#9700 add start
            //calcBaseReqTMsgList.add(calcBaseRecTMsg);
            if (!ZYPConstant.FLG_OFF_N.equals(calcBaseTMsg.prcRuleApplyFlg.getValue())) {
                calcBaseReqTMsgList.add(calcBaseRecTMsg);
            }
           // 2018/09/14 QC#9700 add end
        }
    }

    private void removeQuoteCalcBase(NWZC167001PMsg pMsg, NWZC167002PMsg linePMsg) {

        SPLY_QUOTE_PRC_CALC_BASETMsg condCalcBaseTMsg = new SPLY_QUOTE_PRC_CALC_BASETMsg();
        condCalcBaseTMsg.setSQLID("002");
        condCalcBaseTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        condCalcBaseTMsg.setConditionValue("splyQuoteNum01", pMsg.splyQuoteNum.getValue());
        condCalcBaseTMsg.setConditionValue("splyQuoteDtlLineNum01", linePMsg.splyQuoteDtlLineNum.getValue());
        condCalcBaseTMsg.setConditionValue("splyQuoteDtlLineSubNum01", linePMsg.splyQuoteDtlLineSubNum.getValue());
        SPLY_QUOTE_PRC_CALC_BASETMsgArray calcBaseTMsgArray = (SPLY_QUOTE_PRC_CALC_BASETMsgArray) S21ApiTBLAccessor.findByCondition(condCalcBaseTMsg);

        List<SPLY_QUOTE_PRC_CALC_BASETMsg> delCalcBaseTMsgList = new ArrayList<SPLY_QUOTE_PRC_CALC_BASETMsg>();

        for (int i = 0; calcBaseTMsgArray.getValidCount() > i; i++) {
            SPLY_QUOTE_PRC_CALC_BASETMsg targetTMsg = (SPLY_QUOTE_PRC_CALC_BASETMsg) calcBaseTMsgArray.get(i);

            SPLY_QUOTE_PRC_CALC_BASETMsg calcBaseTMsg = new SPLY_QUOTE_PRC_CALC_BASETMsg();
            ZYPEZDItemValueSetter.setValue(calcBaseTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(calcBaseTMsg.ordPrcCalcBasePk, targetTMsg.ordPrcCalcBasePk);
            calcBaseTMsg = (SPLY_QUOTE_PRC_CALC_BASETMsg) S21FastTBLAccessor.findByKeyForUpdate(calcBaseTMsg);

            if (calcBaseTMsg == null) {
                continue;
            }

            delCalcBaseTMsgList.add(calcBaseTMsg);
        }

        if (delCalcBaseTMsgList.size() > 0) {
            S21FastTBLAccessor.removePhysical(delCalcBaseTMsgList.toArray(new SPLY_QUOTE_PRC_CALC_BASETMsg[0]));
        }
    }

    private SPLY_QUOTE_PRC_CALC_BASETMsg getCalcBaseTMsg(NWZC167002_APMsg apMsg2, String splyQuoteNum) {

        if (!ZYPCommonFunc.hasValue(apMsg2.ordPrcCalcBasePk_A)) {
            ZYPEZDItemValueSetter.setValue(apMsg2.ordPrcCalcBasePk_A, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.ORD_PRC_CALC_BASE_SQ));
        }

        SPLY_QUOTE_PRC_CALC_BASETMsg calcBaseTMsg = new SPLY_QUOTE_PRC_CALC_BASETMsg();
        EZDMsg.copy(apMsg2, "A", calcBaseTMsg, "");
        ZYPEZDItemValueSetter.setValue(calcBaseTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(calcBaseTMsg.splyQuoteNum, splyQuoteNum);

        return calcBaseTMsg;
    }

    private boolean insertQuoteSlsCr(NWZC167001PMsg pMsg) {

        List<SPLY_QUOTE_SLS_CRTMsg> quoteSlsCrTMsgList = new ArrayList<SPLY_QUOTE_SLS_CRTMsg>();
        List<BIZ_PROC_LOGTMsg> bizProcLogTMsgList = new ArrayList<BIZ_PROC_LOGTMsg>();
        List<SPLY_QUOTE_SLS_CR_RECTMsg> quoteSlsCrRecTMsgList = new ArrayList<SPLY_QUOTE_SLS_CR_RECTMsg>();

        String splyQuoteNum = pMsg.splyQuoteNum.getValue();

        for (int i = 0; i < pMsg.B.getValidCount(); i++) {
            NWZC167001_BPMsg slsCrPMsg = pMsg.B.no(i);
            SPLY_QUOTE_SLS_CRTMsg quoteSlsCrTMsg = new SPLY_QUOTE_SLS_CRTMsg();
            setQuoteSlsCrTMsg(slsCrPMsg, quoteSlsCrTMsg, splyQuoteNum);
            quoteSlsCrTMsgList.add(quoteSlsCrTMsg);
            setQuoteSlsCrRecInfo(quoteSlsCrTMsg, EVENT_QUOTE_CRAT, splyQuoteNum, bizProcLogTMsgList, quoteSlsCrRecTMsgList);
        }

        int result = S21FastTBLAccessor.insert(quoteSlsCrTMsgList.toArray(new SPLY_QUOTE_SLS_CRTMsg[0]));
        if (result != quoteSlsCrTMsgList.size()) {
            setMsgId(pMsg, NWZM1876E);
            return false;
        }

        S21FastTBLAccessor.insert(bizProcLogTMsgList.toArray(new BIZ_PROC_LOGTMsg[0]));
        S21FastTBLAccessor.insert(quoteSlsCrRecTMsgList.toArray(new SPLY_QUOTE_SLS_CR_RECTMsg[0]));

        return true;
    }

    private void setQuoteSlsCrTMsg(NWZC167001_BPMsg slsCrPMsg, SPLY_QUOTE_SLS_CRTMsg quoteSlsCrTMsg, String splyQuoteNum) {

        if (!ZYPCommonFunc.hasValue(slsCrPMsg.splyQuoteSlsCrPk_B)) {
            ZYPEZDItemValueSetter.setValue(slsCrPMsg.splyQuoteSlsCrPk_B, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SPLY_QUOTE_SLS_CR_SQ));
        }

        EZDMsg.copy(slsCrPMsg, "B", quoteSlsCrTMsg, "");
        ZYPEZDItemValueSetter.setValue(quoteSlsCrTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(quoteSlsCrTMsg.splyQuoteNum, splyQuoteNum);

        TOCTMsg tocTMsg = getTocData(slsCrPMsg.slsRepTocCd_B.getValue());
        ZYPEZDItemValueSetter.setValue(quoteSlsCrTMsg.coaExtnCd, tocTMsg.coaExtnCd);
        ZYPEZDItemValueSetter.setValue(quoteSlsCrTMsg.coaBrCd, tocTMsg.coaBrCd);
        ZYPEZDItemValueSetter.setValue(quoteSlsCrTMsg.coaCcCd, tocTMsg.coaCcCd);
    }

    private boolean insertQuoteCtacPsn(NWZC167001PMsg pMsg) {

        if (pMsg.C.getValidCount() == 0) {
            return true;
        }

        List<SPLY_QUOTE_CTAC_PSNTMsg> quoteCtacPsnTMsgList = new ArrayList<SPLY_QUOTE_CTAC_PSNTMsg>();

        for (int i = 0; i < pMsg.C.getValidCount(); i++) {
            NWZC167001_CPMsg ctacPsnPMsg = pMsg.C.no(i);
            BigDecimal ctacPsnPk = ctacPsnPMsg.ctacPsnPk_C.getValue();

// QC#13681 2016/08/25 Mod Start
//            if (!ZYPCommonFunc.hasValue(ctacPsnPk)) {
                // Call Customer Update API
            NMZC002001PMsg nmzc002001msg = insertCtacPsnForMaster(pMsg, ctacPsnPMsg);
            if (nmzc002001msg == null || !ZYPCommonFunc.hasValue(nmzc002001msg.ctacPsnPk)) {
                return false;
            } else {
                ctacPsnPk = nmzc002001msg.ctacPsnPk.getValue();
            }
//            }
// QC#13681 2016/08/25 Mod End

            SPLY_QUOTE_CTAC_PSNTMsg quoteCtacPsnTMsg = new SPLY_QUOTE_CTAC_PSNTMsg();
            setQuoteCtacPsnTMsg(ctacPsnPMsg, quoteCtacPsnTMsg, ctacPsnPk, pMsg.splyQuoteNum.getValue());
            quoteCtacPsnTMsgList.add(quoteCtacPsnTMsg);
        }

        int result = S21FastTBLAccessor.insert(quoteCtacPsnTMsgList.toArray(new SPLY_QUOTE_CTAC_PSNTMsg[0]));
        if (result != quoteCtacPsnTMsgList.size()) {
            setMsgId(pMsg, NWZM1878E);
            return false;
        }

        return true;
    }

    private NMZC002001PMsg insertCtacPsnForMaster(NWZC167001PMsg pMsg, NWZC167001_CPMsg ctacPsnPMsg) {

        // Map<String, String> shipToInfo = NWZC167001Query.getInstance().getShipToInfo(pMsg); // 2017/08/08 S21_NA#8598 Del

        NMZC002001PMsg nmzc002001pMsg = new NMZC002001PMsg();
        // Mod Start 2016/10/26 M.Ohno S21_NA#15569
        // Mod Start 2017/08/29 T.Murai S21NA#20803
        // 2018/08/27 S21_NA#26888 Del Start
//      if (ZYPCommonFunc.hasValue(ctacPsnPMsg.splyQuoteCtacPsnPk_C)) {
        // 2018/08/27 S21_NA#26888 Del End
     // if (ZYPCommonFunc.hasValue(ctacPsnPMsg.ctacPsnPk_C)) {
        // Mod End 2017/08/29 T.Murai S21NA#20803

        // 2018/08/27 S21_NA#26888 Mod Start
//            ZYPEZDItemValueSetter.setValue(nmzc002001pMsg.xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_UPD);
//            ZYPEZDItemValueSetter.setValue(nmzc002001pMsg.ctacPsnPk, ctacPsnPMsg.ctacPsnPk_C);
//        } else {
//            ZYPEZDItemValueSetter.setValue(nmzc002001pMsg.xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_CRAT);
//        }

        ZYPEZDItemValueSetter.setValue(nmzc002001pMsg.xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_CRAT);
        // 2018/08/27 S21_NA#26888 Mod End

        // Mod End   2016/10/26 M.Ohno S21_NA#15569
        ZYPEZDItemValueSetter.setValue(nmzc002001pMsg.glblCmpyCd, glblCmpyCd);
        // 2017/08/08 S21_NA#8598 Mod Start
        // ZYPEZDItemValueSetter.setValue(nmzc002001pMsg.dsAcctNum, shipToInfo.get("SELL_TO_CUST_CD"));
        // ZYPEZDItemValueSetter.setValue(nmzc002001pMsg.locNum, shipToInfo.get("LOC_NUM"));
        ZYPEZDItemValueSetter.setValue(nmzc002001pMsg.locNum, ctacPsnPMsg.locNum_C);
        // 2017/08/08 S21_NA#8598 Mod End
        ZYPEZDItemValueSetter.setValue(nmzc002001pMsg.slsDt, slsDt);
        ZYPEZDItemValueSetter.setValue(nmzc002001pMsg.effFromDt, pMsg.splyQuoteDt);
        ZYPEZDItemValueSetter.setValue(nmzc002001pMsg.ctacPsnFirstNm, ctacPsnPMsg.ctacPsnFirstNm_C);
        ZYPEZDItemValueSetter.setValue(nmzc002001pMsg.ctacPsnLastNm, ctacPsnPMsg.ctacPsnLastNm_C);
        ZYPEZDItemValueSetter.setValue(nmzc002001pMsg.ctacTpCd, ctacPsnPMsg.ctacPsnTpCd_C);
        ZYPEZDItemValueSetter.setValue(nmzc002001pMsg.ctacPsnCmntTxt, ctacPsnPMsg.ctacPsnCmntTxt_C);
        ZYPEZDItemValueSetter.setValue(nmzc002001pMsg.ctacPsnActvFlg, ZYPConstant.FLG_ON_Y);

        NMZC002001_ContactPointInfoListPMsg contactPntPMsg = null;
        int contactVldCnt = 0;

        if (ZYPCommonFunc.hasValue(ctacPsnPMsg.ctacPsnTelNum_C)) {
            contactPntPMsg = nmzc002001pMsg.ContactPointInfoList.no(contactVldCnt);
            ZYPEZDItemValueSetter.setValue(contactPntPMsg.xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_CRAT);
            ZYPEZDItemValueSetter.setValue(contactPntPMsg.dsCtacPntTpCd, DS_CTAC_PNT_TP.PHONE_WORK);
            ZYPEZDItemValueSetter.setValue(contactPntPMsg.dsCtacPntValTxt, ctacPsnPMsg.ctacPsnTelNum_C);
            ZYPEZDItemValueSetter.setValue(contactPntPMsg.dsCtacPsnExtnNum, ctacPsnPMsg.ctacPsnExtnNum_C);
            ZYPEZDItemValueSetter.setValue(contactPntPMsg.dsOpsOutFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(contactPntPMsg.dsCtacPntActvFlg, ZYPConstant.FLG_ON_Y);
            // 2019/01/16 QC#29642 Add Start
            ZYPEZDItemValueSetter.setValue(contactPntPMsg.updCtrlFlg, ZYPConstant.FLG_ON_Y);
            // 2019/01/16 QC#29642 Add End
            contactVldCnt++;
        }

        if (ZYPCommonFunc.hasValue(ctacPsnPMsg.ctacPsnEmlAddr_C)) {
            contactPntPMsg = nmzc002001pMsg.ContactPointInfoList.no(contactVldCnt);
            ZYPEZDItemValueSetter.setValue(contactPntPMsg.xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_CRAT);
            ZYPEZDItemValueSetter.setValue(contactPntPMsg.dsCtacPntTpCd, DS_CTAC_PNT_TP.EMAIL_WORK);
            ZYPEZDItemValueSetter.setValue(contactPntPMsg.dsCtacPntValTxt, ctacPsnPMsg.ctacPsnEmlAddr_C);
            // 2019/01/16 QC#29642 Del Start
            //ZYPEZDItemValueSetter.setValue(contactPntPMsg.dsCtacPsnExtnNum, ctacPsnPMsg.ctacPsnExtnNum_C);
            // 2019/01/16 QC#29642 Del End
            ZYPEZDItemValueSetter.setValue(contactPntPMsg.dsOpsOutFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(contactPntPMsg.dsCtacPntActvFlg, ZYPConstant.FLG_ON_Y);
            contactVldCnt++;
        }

        if (ZYPCommonFunc.hasValue(ctacPsnPMsg.ctacPsnFaxNum_C)) {
            contactPntPMsg = nmzc002001pMsg.ContactPointInfoList.no(contactVldCnt);
            ZYPEZDItemValueSetter.setValue(contactPntPMsg.xxModeCd, NMZC002001Constant.PROCESS_MODE_CTAC_CRAT);
            ZYPEZDItemValueSetter.setValue(contactPntPMsg.dsCtacPntTpCd, DS_CTAC_PNT_TP.FAX_WORK);
            ZYPEZDItemValueSetter.setValue(contactPntPMsg.dsCtacPntValTxt, ctacPsnPMsg.ctacPsnFaxNum_C);
            // 2019/01/16 QC#29642 Del Start
            //ZYPEZDItemValueSetter.setValue(contactPntPMsg.dsCtacPsnExtnNum, ctacPsnPMsg.ctacPsnExtnNum_C);
            // 2019/01/16 QC#29642 Del End
            ZYPEZDItemValueSetter.setValue(contactPntPMsg.dsOpsOutFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(contactPntPMsg.dsCtacPntActvFlg, ZYPConstant.FLG_ON_Y);
            contactVldCnt++;
        }

        nmzc002001pMsg.ContactPointInfoList.setValidCount(contactVldCnt);
        new NMZC002001().execute(nmzc002001pMsg, onBatchType);

        List<String> errList = S21ApiUtil.getXxMsgIdList(nmzc002001pMsg);
        if (!errList.isEmpty()) {
            for (String xxMsgId : errList) {
                if (xxMsgId.endsWith("E")) {
                    setMsgId(pMsg, xxMsgId);
                    return null;
                }
            }
        }

        return nmzc002001pMsg;
    }

    private void setQuoteCtacPsnTMsg(NWZC167001_CPMsg ctacPsnPMsg, SPLY_QUOTE_CTAC_PSNTMsg quoteCtacPsnTMsg, BigDecimal ctacPsnPk, String splyQuoteNum) {

        if (!ZYPCommonFunc.hasValue(ctacPsnPMsg.splyQuoteCtacPsnPk_C)) {
            ZYPEZDItemValueSetter.setValue(ctacPsnPMsg.splyQuoteCtacPsnPk_C, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SPLY_QUOTE_CTAC_PSN_SQ));
        }
        ZYPEZDItemValueSetter.setValue(ctacPsnPMsg.ctacPsnPk_C, ctacPsnPk);

        EZDMsg.copy(ctacPsnPMsg, "C", quoteCtacPsnTMsg, "");
        ZYPEZDItemValueSetter.setValue(quoteCtacPsnTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(quoteCtacPsnTMsg.splyQuoteNum, splyQuoteNum);
        ZYPEZDItemValueSetter.setValue(quoteCtacPsnTMsg.ctacPsnOvrdFlg, ZYPConstant.FLG_ON_Y);
    }

    private boolean updateQuote(NWZC167001PMsg pMsg) {

        String splyQuoteNum = pMsg.splyQuoteNum.getValue();

        SPLY_QUOTETMsg quoteTMsg = new SPLY_QUOTETMsg();
        ZYPEZDItemValueSetter.setValue(quoteTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(quoteTMsg.splyQuoteNum, splyQuoteNum);
        quoteTMsg = (SPLY_QUOTETMsg) S21FastTBLAccessor.findByKeyForUpdate(quoteTMsg);

        if (quoteTMsg == null) {
            setMsgId(pMsg, NWZM1880E);
            return false;
        }
        setQuoteTMsg(pMsg, quoteTMsg);

        SPLY_QUOTE_RECTMsg quoteRecTMsg = new SPLY_QUOTE_RECTMsg();
        EZDMsg.copy(quoteTMsg, null, quoteRecTMsg, null);

        S21FastTBLAccessor.update(quoteTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(quoteTMsg.getReturnCode())) {
            setMsgId(pMsg, NWZM1871E);
            return false;
        }

        BIZ_PROC_LOGTMsg bizProcLogTMsg = getBizProcLogTMsg(EVENT_QUOTE_CHNG, splyQuoteNum);
        ZYPEZDItemValueSetter.setValue(quoteRecTMsg.bizProcLogPk, bizProcLogTMsg.bizProcLogPk);

        S21FastTBLAccessor.insert(bizProcLogTMsg);
        S21FastTBLAccessor.insert(quoteRecTMsg);

        return true;
    }

    private boolean updateQuoteDtlAndCalcBase(NWZC167001PMsg pMsg) {

        // 2017/08/17 S21_NA#20659 Add Start
        if (pMsg.NWZC167002PMsg.getValidCount() == 0) {
            return true;
        }
        // 2017/08/17 S21_NA#20659 Add End

        List<SPLY_QUOTE_DTLTMsg> insQuoteDtlTMsgList = new ArrayList<SPLY_QUOTE_DTLTMsg>();
        List<SPLY_QUOTE_DTLTMsg> updQuoteDtlTMsgList = new ArrayList<SPLY_QUOTE_DTLTMsg>();
        List<SPLY_QUOTE_DTLTMsg> delQuoteDtlTMsgList = new ArrayList<SPLY_QUOTE_DTLTMsg>(); // 2018/07/24 S21_NA#26274 Add
        List<SPLY_QUOTE_DTL_RECTMsg> quoteDtlReqTMsgList = new ArrayList<SPLY_QUOTE_DTL_RECTMsg>();
        List<SPLY_QUOTE_PRC_CALC_BASETMsg> insCalcBaseTMsgList = new ArrayList<SPLY_QUOTE_PRC_CALC_BASETMsg>();
        List<SPLY_QUOTE_CALC_BASE_RECTMsg> calcBaseReqTMsgList = new ArrayList<SPLY_QUOTE_CALC_BASE_RECTMsg>();
        List<BIZ_PROC_LOGTMsg> bizProcLogTMsgList = new ArrayList<BIZ_PROC_LOGTMsg>();
        int dplyLineNum = 0; // 2018/07/24 S21_NA#26274 Add

        for (int i = 0; i < pMsg.NWZC167002PMsg.getValidCount(); i++) {
            NWZC167002PMsg linePMsg = pMsg.NWZC167002PMsg.no(i);
            String rqstTpCd = linePMsg.xxRqstTpCd.getValue();

            if (RQST_TP_NEW.equals(rqstTpCd)) {
                SPLY_QUOTE_DTLTMsg quoteDtlTMsg = new SPLY_QUOTE_DTLTMsg();
                setQuoteDtlAndCalcBaseData(quoteDtlTMsg, pMsg, linePMsg, insQuoteDtlTMsgList, bizProcLogTMsgList, insCalcBaseTMsgList, calcBaseReqTMsgList, quoteDtlReqTMsgList, EVENT_QUOTE_CRAT, false);
                // 2018/07/24 S21_NA#26274 Add Start
                if (ZYPCommonFunc.hasValue(quoteDtlTMsg.dplyQuoteLineSubNum)) {
                    quoteDtlTMsg.dplyQuoteLineNum.setValue(dplyLineNum);
                } else {
                    quoteDtlTMsg.dplyQuoteLineNum.setValue(++dplyLineNum);
                }
                // 2018/07/24 S21_NA#26274 Add End

            } else {
                SPLY_QUOTE_DTLTMsg quoteDtlTMsg = new SPLY_QUOTE_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(quoteDtlTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(quoteDtlTMsg.splyQuoteNum, pMsg.splyQuoteNum);
                ZYPEZDItemValueSetter.setValue(quoteDtlTMsg.splyQuoteDtlLineNum, linePMsg.splyQuoteDtlLineNum);
                ZYPEZDItemValueSetter.setValue(quoteDtlTMsg.splyQuoteDtlLineSubNum, linePMsg.splyQuoteDtlLineSubNum);
                quoteDtlTMsg = (SPLY_QUOTE_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdate(quoteDtlTMsg);

                if (quoteDtlTMsg == null) {
                    setMsgId(pMsg, NWZM1881E);
                    return false;
                }

                if (RQST_TP_MOD.equals(rqstTpCd)) {
                    setQuoteDtlAndCalcBaseData(quoteDtlTMsg, pMsg, linePMsg, updQuoteDtlTMsgList, bizProcLogTMsgList, insCalcBaseTMsgList, calcBaseReqTMsgList, quoteDtlReqTMsgList, EVENT_QUOTE_CHNG, false);

                    // 2018/07/24 S21_NA#26274 Add Start
                    if (ZYPCommonFunc.hasValue(quoteDtlTMsg.dplyQuoteLineSubNum)) {
                        quoteDtlTMsg.dplyQuoteLineNum.setValue(dplyLineNum);
                    } else {
                        quoteDtlTMsg.dplyQuoteLineNum.setValue(++dplyLineNum);
                    }
                    // 2018/07/24 S21_NA#26274 Add End
                } else if (RQST_TP_CAN.equals(rqstTpCd)) {
                    // 2018/07/24 S21_NA#26274 Mod Start
                    //setQuoteDtlAndCalcBaseData(quoteDtlTMsg, pMsg, linePMsg, updQuoteDtlTMsgList, bizProcLogTMsgList, insCalcBaseTMsgList, calcBaseReqTMsgList, quoteDtlReqTMsgList, EVENT_QUOTE_CANC, true);
                    setQuoteDtlAndCalcBaseData(quoteDtlTMsg, pMsg, linePMsg, delQuoteDtlTMsgList, bizProcLogTMsgList, insCalcBaseTMsgList, calcBaseReqTMsgList, quoteDtlReqTMsgList, EVENT_QUOTE_CANC, true);
                    // 2018/07/24 S21_NA#26274 Mod End
                }
            }
        }

        if (insQuoteDtlTMsgList.size() > 0) {
            int result = S21FastTBLAccessor.insert(insQuoteDtlTMsgList.toArray(new SPLY_QUOTE_DTLTMsg[0]));
            if (result != insQuoteDtlTMsgList.size()) {
                setMsgId(pMsg, NWZM1872E);
                return false;
            }
        }

        if (updQuoteDtlTMsgList.size() > 0) {
            int result = S21FastTBLAccessor.update(updQuoteDtlTMsgList.toArray(new SPLY_QUOTE_DTLTMsg[0]));
            if (result != updQuoteDtlTMsgList.size()) {
                setMsgId(pMsg, NWZM1873E);
                return false;
            }
        }

        // 2018/07/24 S21_NA#26274 Add Start
        if (delQuoteDtlTMsgList.size() > 0) {
            int result = S21FastTBLAccessor.removeLogical(delQuoteDtlTMsgList.toArray(new SPLY_QUOTE_DTLTMsg[0]));
            if (result != delQuoteDtlTMsgList.size()) {
                setMsgId(pMsg, NWZM1873E);
                return false;
            }
        }
        // 2018/07/24 S21_NA#26274 Add End

        if (insCalcBaseTMsgList.size() > 0) {
            int result = S21FastTBLAccessor.insert(insCalcBaseTMsgList.toArray(new SPLY_QUOTE_PRC_CALC_BASETMsg[0]));
            if (result != insCalcBaseTMsgList.size()) {
                setMsgId(pMsg, NWZM1874E);
                return false;
            }
        }

        S21FastTBLAccessor.insert(bizProcLogTMsgList.toArray(new BIZ_PROC_LOGTMsg[0]));
        S21FastTBLAccessor.insert(quoteDtlReqTMsgList.toArray(new SPLY_QUOTE_DTL_RECTMsg[0]));
        if (calcBaseReqTMsgList.size() > 0) {
            S21FastTBLAccessor.insert(calcBaseReqTMsgList.toArray(new SPLY_QUOTE_CALC_BASE_RECTMsg[0]));
        }

        return true;
    }

    // Check Style's error is ignored.
    private void setQuoteDtlAndCalcBaseData(SPLY_QUOTE_DTLTMsg quoteDtlTMsg, NWZC167001PMsg pMsg, NWZC167002PMsg linePMsg, List<SPLY_QUOTE_DTLTMsg> quoteDtlTMsgList, List<BIZ_PROC_LOGTMsg> bizProcLogTMsgList,
            List<SPLY_QUOTE_PRC_CALC_BASETMsg> insCalcBaseTMsgList, List<SPLY_QUOTE_CALC_BASE_RECTMsg> calcBaseReqTMsgList, List<SPLY_QUOTE_DTL_RECTMsg> quoteDtlReqTMsgList, String eventId, boolean isCallCancel) {

        // Biz Proc Log
        BIZ_PROC_LOGTMsg bizProcLogTMsg = getBizProcLogTMsgForLine(eventId, pMsg.splyQuoteNum.getValue(), getDocId(linePMsg));
        BigDecimal bizProcLogPk = bizProcLogTMsg.bizProcLogPk.getValue();
        bizProcLogTMsgList.add(bizProcLogTMsg);

        // Quote Detail And Calc Base
        if (isCallCancel) {
            ZYPEZDItemValueSetter.setValue(quoteDtlTMsg.splyQuoteLineStsCd, SPLY_QUOTE_STS.CANCELLED);
        } else {
            setQuoteDtlAndCalcBaseTMsg(quoteDtlTMsg, pMsg, linePMsg, bizProcLogPk, insCalcBaseTMsgList, calcBaseReqTMsgList);
        }

        quoteDtlTMsgList.add(quoteDtlTMsg);

        // Quote Detail Record
        SPLY_QUOTE_DTL_RECTMsg quoteDtlRecTMsg = new SPLY_QUOTE_DTL_RECTMsg();
        EZDMsg.copy(quoteDtlTMsg, null, quoteDtlRecTMsg, null);
        ZYPEZDItemValueSetter.setValue(quoteDtlRecTMsg.bizProcLogPk, bizProcLogPk);
        quoteDtlReqTMsgList.add(quoteDtlRecTMsg);
    }

    private boolean updateQuoteSlsCr(NWZC167001PMsg pMsg) {

        List<SPLY_QUOTE_SLS_CRTMsg> insQuoteSlsCrTMsgList = new ArrayList<SPLY_QUOTE_SLS_CRTMsg>();
        List<SPLY_QUOTE_SLS_CRTMsg> updQuoteSlsCrTMsgList = new ArrayList<SPLY_QUOTE_SLS_CRTMsg>();
        List<SPLY_QUOTE_SLS_CRTMsg> delQuoteSlsCrTMsgList = new ArrayList<SPLY_QUOTE_SLS_CRTMsg>();
        List<BIZ_PROC_LOGTMsg> bizProcLogTMsgList = new ArrayList<BIZ_PROC_LOGTMsg>();
        List<SPLY_QUOTE_SLS_CR_RECTMsg> quoteSlsCrRecTMsgList = new ArrayList<SPLY_QUOTE_SLS_CR_RECTMsg>();

        String splyQuoteNum = pMsg.splyQuoteNum.getValue();

        for (int i = 0; i < pMsg.B.getValidCount(); i++) {
            NWZC167001_BPMsg slsCrPMsg = pMsg.B.no(i);
            String rqstTpCd = slsCrPMsg.xxRqstTpCd_B.getValue();

            if (RQST_TP_NEW.equals(rqstTpCd)) {
                SPLY_QUOTE_SLS_CRTMsg quoteSlsCrTMsg = new SPLY_QUOTE_SLS_CRTMsg();
                setQuoteSlsCrTMsg(slsCrPMsg, quoteSlsCrTMsg, splyQuoteNum);
                insQuoteSlsCrTMsgList.add(quoteSlsCrTMsg);
                setQuoteSlsCrRecInfo(quoteSlsCrTMsg, EVENT_QUOTE_CRAT, splyQuoteNum, bizProcLogTMsgList, quoteSlsCrRecTMsgList);

            } else {
                SPLY_QUOTE_SLS_CRTMsg quoteSlsCrTMsg = new SPLY_QUOTE_SLS_CRTMsg();
                ZYPEZDItemValueSetter.setValue(quoteSlsCrTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(quoteSlsCrTMsg.splyQuoteSlsCrPk, slsCrPMsg.splyQuoteSlsCrPk_B);
                quoteSlsCrTMsg = (SPLY_QUOTE_SLS_CRTMsg) S21FastTBLAccessor.findByKeyForUpdate(quoteSlsCrTMsg);

                if (quoteSlsCrTMsg == null) {
                    setMsgId(pMsg, NWZM1883E);
                    return false;
                }

                if (RQST_TP_MOD.equals(rqstTpCd)) {
                    setQuoteSlsCrTMsg(slsCrPMsg, quoteSlsCrTMsg, splyQuoteNum);
                    updQuoteSlsCrTMsgList.add(quoteSlsCrTMsg);
                    setQuoteSlsCrRecInfo(quoteSlsCrTMsg, EVENT_QUOTE_CHNG, splyQuoteNum, bizProcLogTMsgList, quoteSlsCrRecTMsgList);

                } else if (RQST_TP_CAN.equals(rqstTpCd)) {
                    delQuoteSlsCrTMsgList.add(quoteSlsCrTMsg);
                    setQuoteSlsCrRecInfo(quoteSlsCrTMsg, EVENT_QUOTE_CANC, splyQuoteNum, bizProcLogTMsgList, quoteSlsCrRecTMsgList);
                }
            }
        }

        if (insQuoteSlsCrTMsgList.size() > 0) {
            int result = S21FastTBLAccessor.insert(insQuoteSlsCrTMsgList.toArray(new SPLY_QUOTE_SLS_CRTMsg[0]));
            if (result != insQuoteSlsCrTMsgList.size()) {
                setMsgId(pMsg, NWZM1876E);
                return false;
            }
        }

        if (updQuoteSlsCrTMsgList.size() > 0) {
            int result = S21FastTBLAccessor.update(updQuoteSlsCrTMsgList.toArray(new SPLY_QUOTE_SLS_CRTMsg[0]));
            if (result != updQuoteSlsCrTMsgList.size()) {
                setMsgId(pMsg, NWZM1877E);
                return false;
            }
        }

        if (delQuoteSlsCrTMsgList.size() > 0) {
            int result = S21FastTBLAccessor.removeLogical(delQuoteSlsCrTMsgList.toArray(new SPLY_QUOTE_SLS_CRTMsg[0]));
            if (result != delQuoteSlsCrTMsgList.size()) {
                setMsgId(pMsg, NWZM1933E);
                return false;
            }
        }

        S21FastTBLAccessor.insert(bizProcLogTMsgList.toArray(new BIZ_PROC_LOGTMsg[0]));
        S21FastTBLAccessor.insert(quoteSlsCrRecTMsgList.toArray(new SPLY_QUOTE_SLS_CR_RECTMsg[0]));

        return true;
    }

    private void setQuoteSlsCrRecInfo(SPLY_QUOTE_SLS_CRTMsg quoteSlsCrTMsg, String eventId, String splyQuoteNum, List<BIZ_PROC_LOGTMsg> bizProcLogTMsgList, List<SPLY_QUOTE_SLS_CR_RECTMsg> quoteSlsCrRecTMsgList) {

        SPLY_QUOTE_SLS_CR_RECTMsg quoteSlsCrRecTMsg = new SPLY_QUOTE_SLS_CR_RECTMsg();
        EZDMsg.copy(quoteSlsCrTMsg, null, quoteSlsCrRecTMsg, null);

        BIZ_PROC_LOGTMsg bizProcLogTMsg = getBizProcLogTMsg(eventId, splyQuoteNum);
        ZYPEZDItemValueSetter.setValue(quoteSlsCrRecTMsg.bizProcLogPk, bizProcLogTMsg.bizProcLogPk);

        bizProcLogTMsgList.add(bizProcLogTMsg);
        quoteSlsCrRecTMsgList.add(quoteSlsCrRecTMsg);
    }

    private TOCTMsg getTocData(String slsRepCd) {

        TOCTMsg tMsg = new TOCTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.tocCd, slsRepCd);
        tMsg = (TOCTMsg) S21CacheTBLAccessor.findByKey(tMsg);

        return tMsg;
    }

    private boolean updateQuoteCtacPsn(NWZC167001PMsg pMsg) {

        if (pMsg.C.getValidCount() == 0) {
            return true;
        }

        List<SPLY_QUOTE_CTAC_PSNTMsg> insQuoteCtacPsnTMsgList = new ArrayList<SPLY_QUOTE_CTAC_PSNTMsg>();
        List<SPLY_QUOTE_CTAC_PSNTMsg> updQuoteCtacPsnTMsgList = new ArrayList<SPLY_QUOTE_CTAC_PSNTMsg>();
        List<SPLY_QUOTE_CTAC_PSNTMsg> delQuoteCtacPsnTMsgList = new ArrayList<SPLY_QUOTE_CTAC_PSNTMsg>();

        String splyQuoteNum = pMsg.splyQuoteNum.getValue();

        for (int i = 0; i < pMsg.C.getValidCount(); i++) {
            NWZC167001_CPMsg ctacPsnPMsg = pMsg.C.no(i);
            String rqstTpCd = ctacPsnPMsg.xxRqstTpCd_C.getValue();
            BigDecimal ctacPsnPk = ctacPsnPMsg.ctacPsnPk_C.getValue();

            // QC#13681 2016/08/25 Mod Start
            if (RQST_TP_NEW.equals(rqstTpCd) || RQST_TP_MOD.equals(rqstTpCd)) {
                // S21_NA#7704
                // Call Customer Update API
                NMZC002001PMsg nmzc002001msg = insertCtacPsnForMaster(pMsg, ctacPsnPMsg);
                if (nmzc002001msg == null || !ZYPCommonFunc.hasValue(nmzc002001msg.ctacPsnPk)) {
                    return false;
                } else {
                    ctacPsnPk = nmzc002001msg.ctacPsnPk.getValue();
                }
            }
            // QC#13681 2016/08/25 Mod End

            if (RQST_TP_NEW.equals(rqstTpCd)) {
//                if (!ZYPCommonFunc.hasValue(ctacPsnPk)) {
//                    // Call Customer Update API
//                    NMZC002001PMsg nmzc002001msg = insertCtacPsnForMaster(pMsg, ctacPsnPMsg);
//                    if (nmzc002001msg == null || !ZYPCommonFunc.hasValue(nmzc002001msg.ctacPsnPk)) {
//                        return false;
//                    } else {
//                        ctacPsnPk = nmzc002001msg.ctacPsnPk.getValue();
//                    }
//                }

                SPLY_QUOTE_CTAC_PSNTMsg quoteCtacPsnTMsg = new SPLY_QUOTE_CTAC_PSNTMsg();
                setQuoteCtacPsnTMsg(ctacPsnPMsg, quoteCtacPsnTMsg, ctacPsnPk, splyQuoteNum);
                insQuoteCtacPsnTMsgList.add(quoteCtacPsnTMsg);

            } else {
                SPLY_QUOTE_CTAC_PSNTMsg quoteCtacPsnTMsg = new SPLY_QUOTE_CTAC_PSNTMsg();
                ZYPEZDItemValueSetter.setValue(quoteCtacPsnTMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(quoteCtacPsnTMsg.splyQuoteCtacPsnPk, ctacPsnPMsg.splyQuoteCtacPsnPk_C);
                quoteCtacPsnTMsg = (SPLY_QUOTE_CTAC_PSNTMsg) S21FastTBLAccessor.findByKeyForUpdate(quoteCtacPsnTMsg);

                if (quoteCtacPsnTMsg == null) {
                    setMsgId(pMsg, NWZM1884E);
                    return false;
                }

                if (RQST_TP_MOD.equals(rqstTpCd)) {
                    setQuoteCtacPsnTMsg(ctacPsnPMsg, quoteCtacPsnTMsg, ctacPsnPk, splyQuoteNum);
                    updQuoteCtacPsnTMsgList.add(quoteCtacPsnTMsg);

                } else if (RQST_TP_CAN.equals(rqstTpCd)) {
                    delQuoteCtacPsnTMsgList.add(quoteCtacPsnTMsg);
                }
            }
        }

        if (insQuoteCtacPsnTMsgList.size() > 0) {
            int result = S21FastTBLAccessor.insert(insQuoteCtacPsnTMsgList.toArray(new SPLY_QUOTE_CTAC_PSNTMsg[0]));
            if (result != insQuoteCtacPsnTMsgList.size()) {
                setMsgId(pMsg, NWZM1878E);
                return false;
            }
        }

        if (updQuoteCtacPsnTMsgList.size() > 0) {
            int result = S21FastTBLAccessor.update(updQuoteCtacPsnTMsgList.toArray(new SPLY_QUOTE_CTAC_PSNTMsg[0]));
            if (result != updQuoteCtacPsnTMsgList.size()) {
                setMsgId(pMsg, NWZM1879E);
                return false;
            }
        }

        if (delQuoteCtacPsnTMsgList.size() > 0) {
            int result = S21FastTBLAccessor.removeLogical(delQuoteCtacPsnTMsgList.toArray(new SPLY_QUOTE_CTAC_PSNTMsg[0]));
            if (result != delQuoteCtacPsnTMsgList.size()) {
                setMsgId(pMsg, NWZM1934E);
                return false;
            }
        }

        return true;
    }

    private void cancelQuoteForAllLine(NWZC167001PMsg pMsg) {

        // Cancel Quote Header
        SPLY_QUOTETMsg quoteTMsg = new SPLY_QUOTETMsg();
        ZYPEZDItemValueSetter.setValue(quoteTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(quoteTMsg.splyQuoteNum, pMsg.splyQuoteNum);
        quoteTMsg = (SPLY_QUOTETMsg) S21FastTBLAccessor.findByKeyForUpdate(quoteTMsg);

        if (quoteTMsg == null) {
            setMsgId(pMsg, NWZM1880E);
            return;
        }
        ZYPEZDItemValueSetter.setValue(quoteTMsg.splyQuoteStsCd, SPLY_QUOTE_STS.CANCELLED);

        SPLY_QUOTE_RECTMsg quoteRecTMsg = new SPLY_QUOTE_RECTMsg();
        EZDMsg.copy(quoteTMsg, null, quoteRecTMsg, null);

        S21FastTBLAccessor.update(quoteTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(quoteTMsg.getReturnCode())) {
            setMsgId(pMsg, NWZM1871E);
            return;
        }

        BIZ_PROC_LOGTMsg bizProcLogTMsg = getBizProcLogTMsg(EVENT_QUOTE_CANC, pMsg.splyQuoteNum.getValue());
        ZYPEZDItemValueSetter.setValue(quoteRecTMsg.bizProcLogPk, bizProcLogTMsg.bizProcLogPk);

        S21FastTBLAccessor.insert(bizProcLogTMsg);
        S21FastTBLAccessor.insert(quoteRecTMsg);

        // Cancel Quote Detail
        // 2018/07/24 S21_NA#26274 Mod Start
        //List<SPLY_QUOTE_DTLTMsg> updQuoteDtlTMsgList = new ArrayList<SPLY_QUOTE_DTLTMsg>();
        List<SPLY_QUOTE_DTLTMsg> delQuoteDtlTMsgList = new ArrayList<SPLY_QUOTE_DTLTMsg>();
        // 2018/07/24 S21_NA#26274 Mod End
        List<SPLY_QUOTE_DTL_RECTMsg> quoteDtlReqTMsgList = new ArrayList<SPLY_QUOTE_DTL_RECTMsg>();
        List<BIZ_PROC_LOGTMsg> bizProcLogTMsgList = new ArrayList<BIZ_PROC_LOGTMsg>();

        for (int i = 0; i < pMsg.NWZC167002PMsg.getValidCount(); i++) {
            NWZC167002PMsg linePMsg = pMsg.NWZC167002PMsg.no(i);
            SPLY_QUOTE_DTLTMsg quoteDtlTMsg = new SPLY_QUOTE_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(quoteDtlTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(quoteDtlTMsg.splyQuoteNum, pMsg.splyQuoteNum);
            ZYPEZDItemValueSetter.setValue(quoteDtlTMsg.splyQuoteDtlLineNum, linePMsg.splyQuoteDtlLineNum);
            ZYPEZDItemValueSetter.setValue(quoteDtlTMsg.splyQuoteDtlLineSubNum, linePMsg.splyQuoteDtlLineSubNum);
            quoteDtlTMsg = (SPLY_QUOTE_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdate(quoteDtlTMsg);

            if (quoteDtlTMsg == null) {
                setMsgId(pMsg, NWZM1881E);
                return;
            }

            // 2018/07/24 S21_NA#26274 Mod Start
            //setQuoteDtlAndCalcBaseData(quoteDtlTMsg, pMsg, linePMsg, updQuoteDtlTMsgList, bizProcLogTMsgList, null, null, quoteDtlReqTMsgList, EVENT_QUOTE_CANC, true);
            setQuoteDtlAndCalcBaseData(quoteDtlTMsg, pMsg, linePMsg, delQuoteDtlTMsgList, bizProcLogTMsgList, null, null, quoteDtlReqTMsgList, EVENT_QUOTE_CANC, true);
            // 2018/07/24 S21_NA#26274 Mod End
        }

        // 2018/07/24 S21_NA#26274 Mod Start
        //if (updQuoteDtlTMsgList.size() > 0) {
        //    int result = S21FastTBLAccessor.update(updQuoteDtlTMsgList.toArray(new SPLY_QUOTE_DTLTMsg[0]));
        //    if (result != updQuoteDtlTMsgList.size()) {
        //        setMsgId(pMsg, NWZM1873E);
        //        return;
        //    }
        //}
        if (delQuoteDtlTMsgList.size() > 0) {
            int result = S21FastTBLAccessor.removeLogical(delQuoteDtlTMsgList.toArray(new SPLY_QUOTE_DTLTMsg[0]));
            if (result != delQuoteDtlTMsgList.size()) {
                setMsgId(pMsg, NWZM1873E);
                return;
            }
        }
        // 2018/07/24 S21_NA#26274 Mod End

        S21FastTBLAccessor.insert(bizProcLogTMsgList.toArray(new BIZ_PROC_LOGTMsg[0]));
        S21FastTBLAccessor.insert(quoteDtlReqTMsgList.toArray(new SPLY_QUOTE_DTL_RECTMsg[0]));
    }

    private boolean doSubmitProcess(NWZC167001PMsg pMsg) {

        List<NWZC150002PMsg> dsCpoDtlList1 = new ArrayList<NWZC150002PMsg>();
        List<NWZC150003PMsg> dsCpoDtlList2 = new ArrayList<NWZC150003PMsg>();

        // Call DS CPO API
        NWZC150001PMsg resultApiMsg = callDsCpoUpdtApi(pMsg, dsCpoDtlList1, dsCpoDtlList2);
        boolean hasError = checkApiMsg(resultApiMsg, dsCpoDtlList1, dsCpoDtlList2, pMsg);

        if (hasError) {
            return false;
        }

        // Set Return Data
        setReturnData(resultApiMsg, pMsg);

        // Set CPO_ORD_NUM at QUOTE
        hasError = updateQuoteForOrdNum(pMsg);
        if (hasError) {
            return false;
        }

        // Set Shipping Comment
        setMsgTxtDtlForShpgComment(pMsg);

        return true;
    }

    private NWZC150001PMsg callDsCpoUpdtApi(NWZC167001PMsg pMsg, List<NWZC150002PMsg> dsCpoDtlList1, List<NWZC150003PMsg> dsCpoDtlList2) {

        NWZC150001PMsg dsCpoUpdateApiMsg = createDsCpoUpdtApiPMsg(pMsg);

        // Call DS CPO Update API [NWZC150001]
        NWZC150001 dsCpoUpdateApi = new NWZC150001();
        dsCpoUpdateApi.execute(dsCpoUpdateApiMsg, dsCpoDtlList1, dsCpoDtlList2, ONBATCH_TYPE.BATCH);

        return dsCpoUpdateApiMsg;
    }

    private boolean checkApiMsg(NWZC150001PMsg dsCpo, List<NWZC150002PMsg> dsCpoDtlList1, List<NWZC150003PMsg> dsCpoDtlList2, NWZC167001PMsg pMsg) {

        boolean hasError = false;
        List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(dsCpo);
        for (S21ApiMessage msg : msgList) {
            String msgId = msg.getXxMsgid();
            if (ZYPCommonFunc.hasValue(msgId)) {
                setMsgId(pMsg, msgId);
                hasError = true;
            }
        }

        int i = 0;
        for (NWZC150002PMsg dsCpoDtl : dsCpoDtlList1) {
            // QC#18634
            for (int ix2 = 0; ix2 < pMsg.NWZC167002PMsg.getValidCount(); ix2++) {
                if (S21StringUtil.isEquals(dsCpoDtl.cpoDtlLineNum.getValue(), pMsg.NWZC167002PMsg.no(ix2).splyQuoteDtlLineNum.getValue()) //
                        && S21StringUtil.isEquals(dsCpoDtl.cpoDtlLineSubNum.getValue(), pMsg.NWZC167002PMsg.no(ix2).splyQuoteDtlLineSubNum.getValue())) {
                    i = ix2;
                    break;
                }
            }
            msgList = S21ApiUtil.getXxMsgList(dsCpoDtl);
            for (S21ApiMessage msg : msgList) {
                String msgId = msg.getXxMsgid();
                if (ZYPCommonFunc.hasValue(msgId)) {
                    setMsgId2(pMsg.NWZC167002PMsg.no(i), msgId);
                    hasError = true;
                }
            }
        }

        i = 0;
        for (NWZC150003PMsg dsCpoDtl : dsCpoDtlList2) {
            msgList = S21ApiUtil.getXxMsgList(dsCpoDtl);
            for (S21ApiMessage msg : msgList) {
                String msgId = msg.getXxMsgid();
                if (ZYPCommonFunc.hasValue(msgId)) {
                    setMsgId2(pMsg.NWZC167002PMsg.no(i), msgId);
                    hasError = true;
                }
            }
        }

        return hasError;
    }

    private NWZC150001PMsg createDsCpoUpdtApiPMsg(NWZC167001PMsg quotePMsg) {

        NWZC150001PMsg dsCpoPMsg = new NWZC150001PMsg();
        EZDMsg.copy(quotePMsg, null, dsCpoPMsg, null);

        // Header
        ZYPEZDItemValueSetter.setValue(dsCpoPMsg.xxModeCd, MODE_SUBMIT);
        ZYPEZDItemValueSetter.setValue(dsCpoPMsg.sysSrcCd, SYS_SRC.S21_ORDER);
        ZYPEZDItemValueSetter.setValue(dsCpoPMsg.cpoSrcTpCd, CPO_SRC_TP.SUPPLY_QUOTE_ENTRY);
        ZYPEZDItemValueSetter.setValue(dsCpoPMsg.addRddDt, quotePMsg.rddDt);
        ZYPEZDItemValueSetter.setValue(dsCpoPMsg.addShpgSvcLvlCd, quotePMsg.shpgSvcLvlCd);
        ZYPEZDItemValueSetter.setValue(dsCpoPMsg.addFrtChrgToCd, quotePMsg.frtChrgToCd);
        ZYPEZDItemValueSetter.setValue(dsCpoPMsg.addFrtChrgMethCd, quotePMsg.frtChrgMethCd);
        ZYPEZDItemValueSetter.setValue(dsCpoPMsg.addDropShipFlg, quotePMsg.dropShipFlg);
        ZYPEZDItemValueSetter.setValue(dsCpoPMsg.addShipToCustCd, quotePMsg.shipToCustCd);
        ZYPEZDItemValueSetter.setValue(dsCpoPMsg.addShipToLocNm, quotePMsg.shipToLocNm);
        ZYPEZDItemValueSetter.setValue(dsCpoPMsg.addShipToAddlLocNm, quotePMsg.shipToAddlLocNm);
        ZYPEZDItemValueSetter.setValue(dsCpoPMsg.addShipToFirstLineAddr, quotePMsg.shipToFirstLineAddr);
        ZYPEZDItemValueSetter.setValue(dsCpoPMsg.addShipToScdLineAddr, quotePMsg.shipToScdLineAddr);
        ZYPEZDItemValueSetter.setValue(dsCpoPMsg.addShipToThirdLineAddr, quotePMsg.shipToThirdLineAddr);
        ZYPEZDItemValueSetter.setValue(dsCpoPMsg.addShipToFrthLineAddr, quotePMsg.shipToFrthLineAddr);
        ZYPEZDItemValueSetter.setValue(dsCpoPMsg.addShipToCtyAddr, quotePMsg.shipToCtyAddr);
        ZYPEZDItemValueSetter.setValue(dsCpoPMsg.addShipToStCd, quotePMsg.shipToStCd);
        ZYPEZDItemValueSetter.setValue(dsCpoPMsg.addShipToProvNm, quotePMsg.shipToProvNm);
        ZYPEZDItemValueSetter.setValue(dsCpoPMsg.addShipToPostCd, quotePMsg.shipToPostCd);
        ZYPEZDItemValueSetter.setValue(dsCpoPMsg.addShipToCtryCd, quotePMsg.shipToCtryCd);
        ZYPEZDItemValueSetter.setValue(dsCpoPMsg.addShipToCntyNm, quotePMsg.shipToCntyNm);
        ZYPEZDItemValueSetter.setValue(dsCpoPMsg.addShipTo01RefCmntTxt, quotePMsg.shipTo01RefCmntTxt);
        ZYPEZDItemValueSetter.setValue(dsCpoPMsg.addShipTo02RefCmntTxt, quotePMsg.shipTo02RefCmntTxt);
        ZYPEZDItemValueSetter.setValue(dsCpoPMsg.addPmtTermCashDiscCd, quotePMsg.pmtTermCashDiscCd);
        ZYPEZDItemValueSetter.setValue(dsCpoPMsg.slsRepCd, quotePMsg.slsRepTocCd);
        ZYPEZDItemValueSetter.setValue(dsCpoPMsg.ordSrcImptDt, quotePMsg.slsDt);
        ZYPEZDItemValueSetter.setValue(dsCpoPMsg.ordSrcRefNum, quotePMsg.splyQuoteNum);
        ZYPEZDItemValueSetter.setValue(dsCpoPMsg.xxValUpdFlg, ZYPConstant.FLG_ON_Y);
        // Add Start 2017/10/13 QC#20246-1(L3 Sol#454)
        ZYPEZDItemValueSetter.setValue(dsCpoPMsg.sellToFirstRefCmntTxt, quotePMsg.sellToFirstRefCmntTxt);
        // Add End 2017/10/13 QC#20246-1(L3 Sol#454)
        // 2018/05/11 QC#22139 Add Start
        ZYPEZDItemValueSetter.setValue(dsCpoPMsg.quotePrintCmntTxt, quotePMsg.quotePrintCmntTxt);
        ZYPEZDItemValueSetter.setValue(dsCpoPMsg.ordPrintCmntTxt, quotePMsg.ordPrintCmntTxt);
        ZYPEZDItemValueSetter.setValue(dsCpoPMsg.shpgCmntPrintCd, quotePMsg.shpgCmntPrintCd);
        // 2018/05/11 QC#22139 Add End

        // Config
        NWZC150001_cpoConfigPMsg cpoConfigPMsg = dsCpoPMsg.cpoConfig.no(0);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.xxRqstTpCd, NWZC150001Constant.RQST_TP_CONFIG_NEW);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.dsOrdPosnNum, ORD_POSN_NUM1);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.configCatgCd, CONFIG_CATG.OUTBOUND);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.configTpCd, CONFIG_TP.NEW);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.billToCustAcctCd, quotePMsg.billToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.billToCustCd, quotePMsg.billToCustCd);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCustAcctCd, quotePMsg.shipToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCustCd, quotePMsg.shipToCustCd);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.dropShipFlg, quotePMsg.dropShipFlg);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToLocNm, quotePMsg.shipToLocNm);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToAddlLocNm, quotePMsg.shipToAddlLocNm);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToFirstLineAddr, quotePMsg.shipToFirstLineAddr);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToScdLineAddr, quotePMsg.shipToScdLineAddr);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToThirdLineAddr, quotePMsg.shipToThirdLineAddr);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToFrthLineAddr, quotePMsg.shipToFrthLineAddr);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipTo01RefCmntTxt, quotePMsg.shipTo01RefCmntTxt);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipTo02RefCmntTxt, quotePMsg.shipTo02RefCmntTxt);
        // QC#13684 2016/09/12 Mod Start
        //ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCtyAddr, quotePMsg.shipTo02RefCmntTxt);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCtyAddr, quotePMsg.shipToCtyAddr);
        // QC#13684 2016/09/12 Mod End
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToStCd, quotePMsg.shipToStCd);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToProvNm, quotePMsg.shipToProvNm);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCntyNm, quotePMsg.shipToCntyNm);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToPostCd, quotePMsg.shipToPostCd);
        ZYPEZDItemValueSetter.setValue(cpoConfigPMsg.shipToCtryCd, quotePMsg.shipToCtryCd);
        dsCpoPMsg.cpoConfig.setValidCount(1);

        int dtlVldCnt = 0;
        int calcBaseVldCnt = 0;

        BigDecimal previousLineNum = quotePMsg.NWZC167002PMsg.no(0).dplyQuoteLineNum.getValue();
        String lineNum = LINE_NUM_FIRST;
        boolean firstFlg = true;

        // Detail
        for (int i = 0; i < quotePMsg.NWZC167002PMsg.getValidCount(); i++) {
            NWZC167002PMsg quoteDtlPMsg = quotePMsg.NWZC167002PMsg.no(i);
            if (RQST_TP_CAN.equals(quoteDtlPMsg.xxRqstTpCd.getValue())) {
                continue;
            }

            NWZC150001_APMsg cpoDtlPMsg = dsCpoPMsg.A.no(dtlVldCnt);
            EZDMsg.copy(quoteDtlPMsg, "", cpoDtlPMsg, "A1");

            BigDecimal dplyQuoteLineNum = quoteDtlPMsg.dplyQuoteLineNum.getValue();
            if (!firstFlg && previousLineNum.compareTo(dplyQuoteLineNum) != 0) {
                lineNum = NWXC150001NumberingUtil.getNextCpoDtlLineNum(lineNum);
            }
            firstFlg = false;
            previousLineNum = dplyQuoteLineNum;

            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.xxRqstTpCd_A1, NWZC150001Constant.RQST_TP_DTL_NEW);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.cpoDtlLineNum_A1, lineNum);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.cpoDtlLineSubNum_A1, quoteDtlPMsg.splyQuoteDtlLineSubNum);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.dsCpoLineNum_A1, quoteDtlPMsg.dplyQuoteLineNum);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.dsCpoLineSubNum_A1, quoteDtlPMsg.dplyQuoteLineSubNum);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.dropShipFlg_A1, quotePMsg.dropShipFlg);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.shipToCustCd_A1, quotePMsg.shipToCustCd);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.shipToLocNm_A1, quotePMsg.shipToLocNm);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.shipToAddlLocNm_A1, quotePMsg.shipToAddlLocNm);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.shipToFirstLineAddr_A1, quotePMsg.shipToFirstLineAddr);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.shipToScdLineAddr_A1, quotePMsg.shipToScdLineAddr);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.shipToThirdLineAddr_A1, quotePMsg.shipToThirdLineAddr);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.shipToFrthLineAddr_A1, quotePMsg.shipToFrthLineAddr);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.shipToCtyAddr_A1, quotePMsg.shipToCtyAddr);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.shipToStCd_A1, quotePMsg.shipToStCd);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.shipToProvNm_A1, quotePMsg.shipToProvNm);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.shipToPostCd_A1, quotePMsg.shipToPostCd);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.shipToCtryCd_A1, quotePMsg.shipToCtryCd);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.shipToCntyNm_A1, quotePMsg.shipToCntyNm);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.shipToFirstRefCmntTxt_A1, quotePMsg.shipTo01RefCmntTxt);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.shipToScdRefCmntTxt_A1, quotePMsg.shipTo02RefCmntTxt);
            // 2018/02/13 QC#21165 Mod Start
            //ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.rddDt_A1, quotePMsg.rddDt);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.rddDt_A1, quoteDtlPMsg.rddDt);
            // 2018/02/13 QC#21165 Mod End
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.pmtTermCashDiscCd_A1, quotePMsg.pmtTermCashDiscCd);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.frtChrgToCd_A1, quotePMsg.frtChrgToCd);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.frtChrgMethCd_A1, quotePMsg.frtChrgMethCd);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.shpgSvcLvlCd_A1, quotePMsg.shpgSvcLvlCd);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.cashDiscTermCd_A1, quotePMsg.pmtTermCashDiscCd);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.slsRepOrSlsTeamTocCd_A1, quotePMsg.slsRepTocCd);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.carrCd_A1, quotePMsg.carrCd);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.carrAcctNum_A1, quotePMsg.carrAcctNum);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.dsOrdPosnNum_A1, ORD_POSN_NUM1);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.unitNetWt_A1, quoteDtlPMsg.unitNetWt);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.xxTotBaseAmt_A1, quoteDtlPMsg.dealSplyQuoteDtlSlsAmt);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.xxTotDiscAmt_A1, quoteDtlPMsg.xxTotDiscAmt);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.xxTotSpclPrcAmt_A1, quoteDtlPMsg.xxTotSpclPrcAmt);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.xxTotNetDiscAmt_A1, quoteDtlPMsg.xxTotNetDiscAmt);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.xxTotNetPrcAmt_A1, quoteDtlPMsg.dealSplyQuoteDtlNetAmt);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.xxTotFrtAmt_A1, quoteDtlPMsg.xxTotFrtAmt);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.xxTotSpclChrgAmt_A1, quoteDtlPMsg.xxTotSpclChrgAmt);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.xxTotTaxAmt_A1, quoteDtlPMsg.xxTotTaxAmt);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.xxSlsAmt_A1, quoteDtlPMsg.xxTotNetPrcAmt);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.xxTotAmt_A1, quoteDtlPMsg.xxTotAmt);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.frtCondCd_A1, quotePMsg.frtCondCd);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.flPrcListCd_A1, quotePMsg.flPrcListCd);
            // QC#10347 2017/07/24 Mod Start
            // QC#9437 2016/06/21 Mod Start
            // ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.prcBaseDt_A1, quotePMsg.prcBaseDt);
            // ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.prcBaseDt_A1, quotePMsg.splyQuoteDt);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.prcBaseDt_A1, quoteDtlPMsg.prcBaseDt);
            // QC#9437 2016/06/21 Mod End
            // QC#10347 2017/07/24 Mod End
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.firstBllgAttrbNm_A1, quotePMsg.firstBllgAttrbNm);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.firstBllgAttrbValTxt_A1, quotePMsg.firstBllgAttrbValTxt);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.scdBllgAttrbNm_A1, quotePMsg.scdBllgAttrbNm);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.scdBllgAttrbValTxt_A1, quotePMsg.scdBllgAttrbValTxt);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.thirdBllgAttrbNm_A1, quotePMsg.thirdBllgAttrbNm);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.thirdBllgAttrbValTxt_A1, quotePMsg.thirdBllgAttrbValTxt);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.frthBllgAttrbNm_A1, quotePMsg.frthBllgAttrbNm);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.frthBllgAttrbValTxt_A1, quotePMsg.frthBllgAttrbValTxt);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.fifthBllgAttrbNm_A1, quotePMsg.fifthBllgAttrbNm);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.fifthBllgAttrbValTxt_A1, quotePMsg.fifthBllgAttrbValTxt);
            // 2018/05/25 QC#26145 Add Start
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.sixthBllgAttrbNm_A1, quotePMsg.sixthBllgAttrbNm);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.sixthBllgAttrbValTxt_A1, quotePMsg.sixthBllgAttrbValTxt);
            // 2018/05/25 QC#26145 Add End
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.baseCmptFlg_A1, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(cpoDtlPMsg.loanBalQty_A1, BigDecimal.ZERO);
            cpoDtlPMsg.setItemShipCpltFlg_A1.clear();
            dtlVldCnt++;

            for (int j = 0; j < quoteDtlPMsg.A.getValidCount(); j++) {
                NWZC167002_APMsg pMsg2A = quoteDtlPMsg.A.no(j);
                NWZC150001_priceListPMsg prcListPMsg = dsCpoPMsg.priceList.no(calcBaseVldCnt);
                EZDMsg.copy(pMsg2A, "A", prcListPMsg, "");

                ZYPEZDItemValueSetter.setValue(prcListPMsg.cpoDtlLineNum, lineNum);
                ZYPEZDItemValueSetter.setValue(prcListPMsg.cpoDtlLineSubNum, quoteDtlPMsg.splyQuoteDtlLineSubNum);
                calcBaseVldCnt++;
            }
        }

        dsCpoPMsg.A.setValidCount(dtlVldCnt);
        dsCpoPMsg.priceList.setValidCount(calcBaseVldCnt);

        // Sales Credit Information
        for (int i = 0; i < quotePMsg.B.getValidCount(); i++) {
            NWZC167001_BPMsg quoteSlsCrPMsg = quotePMsg.B.no(i);

            NWZC150001_cpoSlsCrPMsg cpoSlsCrPMsg = dsCpoPMsg.cpoSlsCr.no(i);
            ZYPEZDItemValueSetter.setValue(cpoSlsCrPMsg.xxRqstTpCd, NWZC150001Constant.RQST_TP_SLS_CR_NEW);
            ZYPEZDItemValueSetter.setValue(cpoSlsCrPMsg.slsRepCd, quoteSlsCrPMsg.slsRepTocCd_B);
            ZYPEZDItemValueSetter.setValue(cpoSlsCrPMsg.slsRepRoleTpCd, quoteSlsCrPMsg.slsRepRoleTpCd_B);
            ZYPEZDItemValueSetter.setValue(cpoSlsCrPMsg.slsRepCrPct, quoteSlsCrPMsg.slsRepCrPct_B);
            ZYPEZDItemValueSetter.setValue(cpoSlsCrPMsg.slsCrQuotFlg, quoteSlsCrPMsg.slsCrQuotFlg_B);
            ZYPEZDItemValueSetter.setValue(cpoSlsCrPMsg.configCatgCd, CONFIG_CATG.OUTBOUND);
        }
        dsCpoPMsg.cpoSlsCr.setValidCount(quotePMsg.B.getValidCount());

        // Contact Person Information
        for (int i = 0; i < quotePMsg.C.getValidCount(); i++) {
            NWZC167001_CPMsg quoteCtacPsnPMsg = quotePMsg.C.no(i);

            NWZC150001_cpoCtacPsnInfoListPMsg cpoCtacPsnPMsg = dsCpoPMsg.cpoCtacPsnInfoList.no(i);
            ZYPEZDItemValueSetter.setValue(cpoCtacPsnPMsg.xxRqstTpCd, NWZC150001Constant.RQST_TP_CTAC_PSN_NEW);
            ZYPEZDItemValueSetter.setValue(cpoCtacPsnPMsg.ctacPsnPk, quoteCtacPsnPMsg.ctacPsnPk_C);
            ZYPEZDItemValueSetter.setValue(cpoCtacPsnPMsg.ctacTpCd, quoteCtacPsnPMsg.ctacPsnTpCd_C);
            ZYPEZDItemValueSetter.setValue(cpoCtacPsnPMsg.firstNm, quoteCtacPsnPMsg.ctacPsnFirstNm_C);
            ZYPEZDItemValueSetter.setValue(cpoCtacPsnPMsg.lastNm, quoteCtacPsnPMsg.ctacPsnLastNm_C);
            ZYPEZDItemValueSetter.setValue(cpoCtacPsnPMsg.emlAddr, quoteCtacPsnPMsg.ctacPsnEmlAddr_C);
            ZYPEZDItemValueSetter.setValue(cpoCtacPsnPMsg.telNum, quoteCtacPsnPMsg.ctacPsnTelNum_C);
            ZYPEZDItemValueSetter.setValue(cpoCtacPsnPMsg.ctacPsnExtnNum, quoteCtacPsnPMsg.ctacPsnExtnNum_C);
            ZYPEZDItemValueSetter.setValue(cpoCtacPsnPMsg.faxNum, quoteCtacPsnPMsg.ctacPsnFaxNum_C);
            ZYPEZDItemValueSetter.setValue(cpoCtacPsnPMsg.configCatgCd, CONFIG_CATG.OUTBOUND);
            ZYPEZDItemValueSetter.setValue(cpoCtacPsnPMsg.ctacCustRefTpCd, quoteCtacPsnPMsg.ctacCustRefTpCd_C);// S21_NA#8598 Add
        }
        dsCpoPMsg.cpoCtacPsnInfoList.setValidCount(quotePMsg.C.getValidCount());

        // 2016/12/13 S21_NA#16344 Add Start
        // Delivery Information
        NWZC150001_cpoDlvyInfoListPMsg dlvyInfo = dsCpoPMsg.cpoDlvyInfoList.no(0);
        ZYPEZDItemValueSetter.setValue(dlvyInfo.xxRqstTpCd, NWZC150001Constant.RQST_TP_DELY_INFO_NEW);
        ZYPEZDItemValueSetter.setValue(dlvyInfo.configCatgCd, CONFIG_CATG.OUTBOUND);
        String shpgCmntTxt = quotePMsg.shpgCmntTxt.getValue();
        if (shpgCmntTxt.length() > DELY_ADDL_CMNT_TXT_MAX_SIZE) {
            shpgCmntTxt = shpgCmntTxt.substring(0, DELY_ADDL_CMNT_TXT_MAX_SIZE);
        }
        ZYPEZDItemValueSetter.setValue(dlvyInfo.delyAddlCmntTxt, shpgCmntTxt);
        dsCpoPMsg.cpoDlvyInfoList.setValidCount(1);
        // 2016/12/13 S21_NA#16344 Add End

        // Add Start 2019/05/29 QC#50405
        NWXC150001SalesRep.updateToLatestSalesRep(dsCpoPMsg);
        // Add End 2019/05/29 QC#50405

        return dsCpoPMsg;
    }

    private boolean updateQuoteForOrdNum(NWZC167001PMsg pMsg) {

        SPLY_QUOTETMsg quoteTMsg = new SPLY_QUOTETMsg();
        ZYPEZDItemValueSetter.setValue(quoteTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(quoteTMsg.splyQuoteNum, pMsg.splyQuoteNum);
        quoteTMsg = (SPLY_QUOTETMsg) S21FastTBLAccessor.findByKeyForUpdate(quoteTMsg);

        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(quoteTMsg.getReturnCode())) {
            setMsgId(pMsg, NWZM1880E);
            return true;
        }

        ZYPEZDItemValueSetter.setValue(quoteTMsg.cpoOrdNum, pMsg.cpoOrdNum);

        S21FastTBLAccessor.update(quoteTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(quoteTMsg.getReturnCode())) {
            setMsgId(pMsg, NWZM1871E);
            return true;
        }

        BIZ_PROC_LOGTMsg bizProcLogTMsg = getBizProcLogTMsg(EVENT_ORDER_CRAT, pMsg.splyQuoteNum.getValue());
        S21FastTBLAccessor.insert(bizProcLogTMsg);

        SPLY_QUOTE_RECTMsg recMsg = new SPLY_QUOTE_RECTMsg();
        EZDMsg.copy(quoteTMsg, null, recMsg, null);
        ZYPEZDItemValueSetter.setValue(recMsg.bizProcLogPk, bizProcLogTMsg.bizProcLogPk);
        S21FastTBLAccessor.insert(recMsg);

        return false;
    }

    private void registMsgTxt(NWZC167001PMsg pMsg, List<String> strList, String txtTp, List<MSG_TXT_DTLTMsg> txtDtlMsgList) {

        for (int i = 0; i < strList.size(); i++) {
            MSG_TXT_DTLTMsg txtDtlMsg = new MSG_TXT_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(txtDtlMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(txtDtlMsg.msgTxtDtlSq, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.MSG_TXT_DTL_SQ));
            ZYPEZDItemValueSetter.setValue(txtDtlMsg.cpoOrdNum, pMsg.cpoOrdNum);
            ZYPEZDItemValueSetter.setValue(txtDtlMsg.txtTpCd, txtTp);
            ZYPEZDItemValueSetter.setValue(txtDtlMsg.txtSqNum, String.valueOf(i + 1));
            ZYPEZDItemValueSetter.setValue(txtDtlMsg.msgTxtInfoTxt, strList.get(i));
            txtDtlMsgList.add(txtDtlMsg);
        }
    }

    private void setReturnData(NWZC150001PMsg dsCpo, NWZC167001PMsg pMsg) {

        ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum, dsCpo.cpoOrdNum);

        for (int i = 0, j = 0; i < pMsg.NWZC167002PMsg.getValidCount(); i++) {
            NWZC167002PMsg pMsg2 = pMsg.NWZC167002PMsg.no(i);

            if (RQST_TP_CAN.equals(pMsg2.xxRqstTpCd.getValue())) {
                continue;
            }

            ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineNum, dsCpo.A.no(j).cpoDtlLineNum_A1);
            ZYPEZDItemValueSetter.setValue(pMsg2.cpoDtlLineSubNum, dsCpo.A.no(j).cpoDtlLineSubNum_A1);
            j++;
        }
    }

    private void setMsgId(NWZC167001PMsg pMsg, String val) {

        msgIdMgr.addXxMsgId(val, pMsg);
    }

    private void setMsgId2(NWZC167002PMsg pMsg2, String val) {

        int ix = pMsg2.xxMsgIdList.getValidCount();
        if (ix >= pMsg2.xxMsgIdList.length()) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(pMsg2.xxMsgIdList.no(ix).xxMsgId, val);
        pMsg2.xxMsgIdList.setValidCount(ix + 1);
    }

    private boolean isErrorExists(NWZC167001PMsg pMsg) {

        if (msgIdMgr.isXxMsgId()) {
            return true;
        }

        for (int i = 0; i < pMsg.NWZC167002PMsg.getValidCount(); i++) {
            NWZC167002PMsg pMsg2 = pMsg.NWZC167002PMsg.no(i);
            if (S21ApiUtil.isXxMsgId(pMsg2)) {
                return true;
            }
        }

        return false;
    }

    private String getDocId(NWZC167002PMsg linePMsg) {

        StringBuilder docId = new StringBuilder(linePMsg.splyQuoteDtlLineNum.getValue());
        docId.append(COMMA);
        docId.append(linePMsg.splyQuoteDtlLineSubNum.getValue());

        return docId.toString();
    }

    private void setMsgTxtDtlForShpgComment(NWZC167001PMsg pMsg) {

        String shpgMemoTxt = pMsg.shpgCmntTxt.getValue();
        List<MSG_TXT_DTLTMsg> msgDtlMsgList = new ArrayList<MSG_TXT_DTLTMsg>();

        if (ZYPCommonFunc.hasValue(shpgMemoTxt)) {
            List<String> memoStrList = splitMemoTxt(shpgMemoTxt);
            registMsgTxt(pMsg, memoStrList, TXT_TP.S_OR_O_COMMENT, msgDtlMsgList);

            if (msgDtlMsgList.size() > 0) {
                S21FastTBLAccessor.insert(msgDtlMsgList.toArray(new MSG_TXT_DTLTMsg[0]));
            }
        }
    }

    private List<String> splitMemoTxt(String memoTxt) {

        List<String> memoStrList = new ArrayList<String>();

        if (ZYPCommonFunc.hasValue(memoTxt)) {

            final int txtItemDigit = getItemDigit(new MSG_TXT_DTLTMsg(), "msgTxtInfoTxt");

            do {
                if (memoTxt.length() > txtItemDigit) {
                    memoStrList.add(memoTxt.substring(0, txtItemDigit));
                    memoTxt = memoTxt.substring(txtItemDigit);
                    continue;
                }
                memoStrList.add(memoTxt);
                break;
            } while (true);
        }

        return memoStrList;
    }

    private int getItemDigit(EZDMsg ezdMsg, String itemKey) {

        EZDItemAttribute itemAttr = ezdMsg.getAttr(itemKey);
        return itemAttr.getDigit();
    }

    /**
     * <pre>
     * Set the message id. Add QC#56978
     * @param pMsg2 Input parameters.
     * @param val setting value for Message ID
     * </pre>
     */
    public static void setMsgId4(NWZC167002PMsg pMsg2, String msgId, String[] msgParam) {
        int ix = pMsg2.xxMsgIdList.getValidCount();
        if (ix >= pMsg2.xxMsgIdList.length()) {
            return;
        }
        ZYPEZDItemValueSetter.setValue(pMsg2.xxMsgIdList.no(ix).xxMsgId, msgId);
        int msgParamLen = msgParam.length;
        switch (msgParamLen) {
            case 5:
                ZYPEZDItemValueSetter.setValue(pMsg2.xxMsgIdList.no(ix).xxMsgPrmTxt_4, msgParam[4]);
            case 4:
                ZYPEZDItemValueSetter.setValue(pMsg2.xxMsgIdList.no(ix).xxMsgPrmTxt_3, msgParam[3]);
            case 3:
                ZYPEZDItemValueSetter.setValue(pMsg2.xxMsgIdList.no(ix).xxMsgPrmTxt_2, msgParam[2]);
            case 2:
                ZYPEZDItemValueSetter.setValue(pMsg2.xxMsgIdList.no(ix).xxMsgPrmTxt_1, msgParam[1]);
            case 1:
                ZYPEZDItemValueSetter.setValue(pMsg2.xxMsgIdList.no(ix).xxMsgPrmTxt_0, msgParam[0]);
                break;
            default:
                break;
        }
        pMsg2.xxMsgIdList.setValidCount(ix + 1);
    }
}
