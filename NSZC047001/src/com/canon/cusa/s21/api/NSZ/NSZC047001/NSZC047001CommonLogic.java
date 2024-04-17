/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC047001;

import static com.canon.cusa.s21.api.NSZ.NSZC047001.constant.NSZC047001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.common.EZDPBigDecimalItem;
import parts.common.EZDPDateItem;
import parts.common.EZDPItem;
import parts.dbcommon.EZDTBLAccessor;
import business.db.BLLG_CYCLETMsg;
import business.db.CONTR_XS_COPYTMsg;
import business.db.CONTR_XS_COPYTMsgArray;
import business.db.DS_CONTR_BLLG_MTRTMsg;
import business.db.DS_CONTR_BLLG_SCHDTMsg;
import business.db.DS_CONTR_BLLG_SCHD_MTRTMsg;
import business.db.DS_CONTR_BLLG_SCHD_SMRYTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_PRC_EFFTMsg;
import business.db.DS_CONTR_PRC_EFF_MTRTMsg;
import business.db.DS_CONTR_PRC_EFF_MTRTMsgArray;
import business.db.SVC_CONTR_ADDL_CHRG_BLLGTMsgArray;
import business.db.SVC_CONTR_BASE_BLLGTMsg;
import business.db.SVC_CONTR_BASE_BLLGTMsgArray;
import business.db.SVC_CONTR_BLLGTMsg;
import business.db.SVC_CONTR_BLLGTMsgArray;
import business.db.SVC_CONTR_BLLG_ALLOCTMsgArray;
import business.db.SVC_CONTR_MTR_BLLGTMsg;
import business.db.SVC_CONTR_MTR_BLLGTMsgArray;
import business.db.SVC_CONTR_XS_MTR_BLLGTMsgArray;
import business.db.SVC_PHYS_MTR_READTMsg;
import business.parts.NSZC047001PMsg;
import business.parts.NSZC047002_xxBaseLineListPMsg;
import business.parts.NSZC047008_xxBaseLineListPMsg;

import com.canon.cusa.s21.common.NSX.NSXC003001.CalcNextBllgDtBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.CalcSchdSmryTermAndAmtForBaseBean;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001CalcNextBllgDt;
import com.canon.cusa.s21.common.NSX.NSXC003001.NSXC003001SvcPhysMtrRead;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CPLT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BLLG_SCHD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.internal.calendar.S21CalendarUtilConstants;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContext;
import com.canon.cusa.s21.framework.nwf.core.common.S21NwfContextFactory;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfBizException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfException;
import com.canon.cusa.s21.framework.nwf.core.exception.S21NwfSystemException;
import com.canon.cusa.s21.framework.nwf.core.process.S21NwfProcess;
import com.canon.cusa.s21.framework.nwf.util.common.S21NwfUtilContextFactory;

/**
 * <pre>
 * Contract Billing Schedule API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/14/2015   Hitachi         T.Aoyagi        Create          N/A
 * 01/21/2015   Hitachi         K.Kishimoto     Update          QC#3331
 * 03/11/2016   Hitachi         T.Kanasaka      Update          QC#5377
 * 03/28/2016   Hitachi         K.Kishimoto     Update          QC#1003
 * 06/29/2016   Hitachi         K.Kishimoto     Update          QC#7428,QC#7429
 * 08/02/2016   Hitachi         K.Kishimoto     Update          QC#7402
 * 08/23/2016   Hitachi         T.Aoyagi        Update          QC#13150
 * 10/11/2016   Hitachi         K.Kishimoto     Update          QC#14400
 * 2017/06/30   Hitachi         K.Kitachi       Update          QC#18288
 * 2017/08/15   Hitachi         A.Kohinata      Update          QC#18799
 * 2017/08/23   Hitachi         K.Kasai         Update          QC#18639
 * 2017/08/29   Hitachi         K.Kasai         Update          QC#20829
 * 08/21/2017   Hitachi         M.Kidokoro      Update          QC#20057
 * 2017/09/13   Hitachi         K.Kasai         Update          QC#21060
 * 2017/09/21   Hitachi         K.Kasai         Update          QC#21060
 * 2017/09/26   Hitachi         T.Kanasaka      Update          QC#21414
 * 2017/10/02   Hitachi         A.Kohinata      Update          QC#21567
 * 2017/10/03   Hitachi         E.Kameishi      Update          QC#18636
 * 2017/10/30   Hitachi         K.Kitachi       Update          QC#21449
 * 2017/10/25   Hitachi         K.Yamada        Update          QC#22301
 * 2017/12/07   Hitachi         K.Kishimoto     Update          QC#22935
 * 2018/05/07   Hitachi         T.Tomita        Update          QC#25409
 * 2018/05/07   Fujitsu         A.Kosai         Update          QC#22482
 * 2018/06/20   Hitachi         T.Tomita        Update          QC#26766
 * 2018/07/03   Hitachi         K.Kim           Update          QC#26726
 * 2018/07/23   Hitachi         K.Kishimoto     Update          QC#23863
 * 2018/08/30   Hitachi         K.Kim           Update          QC#27967
 * 2018/12/20   Hitachi         K.Kitachi       Update          QC#29647
 * 2019/03/05   Hitachi         K.Kitachi       Update          QC#30619
 * 2019/04/11   Hitachi         A.Kohinata      Update          QC#31149
 * 2019/06/13   Hitachi         K.Kitachi       Update          QC#50811
 * 2019/07/18   Hitachi         K.Kishimoto     Update          QC#51706
 * 2019/09/02   Hitachi         K.Kitachi       Update          QC#52695
 * 2019/12/25   Hitachi         K.Kim           Update          QC#55170
 * 2020/03/18   Hitachi         K.Kitachi       Update          QC#55693
 * 2021/04/06   CITS            S.Go            Update          QC#58642
 * 2022/05/16   Hitachi         K.Kitachi       Update          QC#60030
 * 2023/06/01   CITS            T.Aizawa        Update          QC#60752
 * 2023/08/18   CITS            T.Kojima        Update          QC#60846
 * </pre>
 */
public class NSZC047001CommonLogic implements ZYPConstant {

    /**
     * @param msgMap S21ApiMessageMap
     * @param targetItem EZDPItem
     * @param msgId String
     * @param msgParam String[]
     */
    public static void mandatoryCheck(S21ApiMessageMap msgMap, EZDPItem targetItem, String msgId, String[] msgParam) {
        if (!hasValue(targetItem)) {
            msgMap.addXxMsgIdWithPrm(msgId, msgParam);
        }
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param glblCmpyCd String
     * @param dsContrBllgSchdPk BigDecimal
     * @return boolean
     */
    public static boolean deleteSvcContrBllgInfo(S21ApiMessageMap msgMap, String glblCmpyCd, BigDecimal dsContrBllgSchdPk) {

        List<BigDecimal> svcContrBllgPkList = NSZC0470Query.getInstance().getSvcContrBllgPk(glblCmpyCd, dsContrBllgSchdPk);

        for (BigDecimal svcContrBllgPk : svcContrBllgPkList) {

            List<BigDecimal> svcContrBllgAllocPkList = NSZC0470Query.getInstance().getSvcContrBllgAllocPk(glblCmpyCd, svcContrBllgPk);
            NSZC0470Query.getInstance().removeSvcContrBllgAlloc(glblCmpyCd, svcContrBllgAllocPkList);

            List<BigDecimal> svcContrBaseBllgPkList = NSZC0470Query.getInstance().getSvcContrBaseBllgPk(glblCmpyCd, svcContrBllgPk);
            NSZC0470Query.getInstance().removeSvcContrBaseBllg(glblCmpyCd, svcContrBaseBllgPkList);

            List<BigDecimal> svcContrMtrBllgPkList = NSZC0470Query.getInstance().getSvcContrMtrBllgPk(glblCmpyCd, svcContrBllgPk);
            NSZC0470Query.getInstance().removeSvcContrMtrBllg(glblCmpyCd, svcContrMtrBllgPkList);

            List<BigDecimal> svcContrXsMtrBllgPkList = NSZC0470Query.getInstance().getSvcContrXsMtrBllgPk(glblCmpyCd, svcContrBllgPk);
            NSZC0470Query.getInstance().removeSvcContrXsMtrBllg(glblCmpyCd, svcContrXsMtrBllgPkList);

            // Mod Start 01/21/2016 <QC#3331>
            deleteSvcContrAddlChrgInfo(msgMap, glblCmpyCd, svcContrBllgPk);
            // Mod End 01/21/2016 <QC#3331>
        }

        List<BigDecimal> fleetReadRollUpPkList = NSZC0470Query.getInstance().getFleetReadRollUpPk(glblCmpyCd, dsContrBllgSchdPk);
        NSZC0470Query.getInstance().removeFleetReadRollUp(glblCmpyCd, fleetReadRollUpPkList);

        // Mod Start 03/11/2016 <QC#5377>
        List<BigDecimal> fleetReadRollUpDtlPkList = NSZC0470Query.getInstance().getFleetReadRollUpDtlPk(glblCmpyCd, fleetReadRollUpPkList);
        NSZC0470Query.getInstance().removeFleetReadRollUpDtl(glblCmpyCd, fleetReadRollUpDtlPkList);
        // Mod End 03/11/2016 <QC#5377>

        // START 2017/09/26 T.Kanasaka [QC#21414,ADD]
        List<Map<String, Object>> calcMtrSchdRelnPkList = NSZC0470Query.getInstance().getCalcMtrSchdRelnPk(glblCmpyCd, dsContrBllgSchdPk);
        NSZC0470Query.getInstance().removeCalcMtrSchdReln(glblCmpyCd, calcMtrSchdRelnPkList);
        // END 2017/09/26 T.Kanasaka [QC#21414,ADD]

        List<BigDecimal> svcContrBllgGrpSqList = NSZC0470Query.getInstance().getSvcContrBllgGrpSq(glblCmpyCd, dsContrBllgSchdPk);

        for (BigDecimal svcContrBllgGrpSq : svcContrBllgGrpSqList) {
            List<BigDecimal> aggrUsgRecalPkList = NSZC0470Query.getInstance().getAggrUsgRecalPk(glblCmpyCd, svcContrBllgGrpSq);

            for (BigDecimal aggrUsgRecalPk : aggrUsgRecalPkList) {
                List<BigDecimal> aggrUsgRecalDtlPkList = NSZC0470Query.getInstance().getAggrUsgRecalDtlPk(glblCmpyCd, aggrUsgRecalPk);

                for (BigDecimal aggUsgRecalDtlPk : aggrUsgRecalDtlPkList) {
                    List<BigDecimal> aggrUsgRecalXsMtrList = NSZC0470Query.getInstance().getAggrUsgRecalXsMtrPk(glblCmpyCd, aggUsgRecalDtlPk);
                    NSZC0470Query.getInstance().removeAggrUsgRecalXsMtr(glblCmpyCd, aggUsgRecalDtlPk, aggrUsgRecalXsMtrList);
                }
                NSZC0470Query.getInstance().removeAggrUsgRecalDtl(glblCmpyCd, aggrUsgRecalPk, aggrUsgRecalDtlPkList);
            }
            NSZC0470Query.getInstance().removeAggrUsgRecal(glblCmpyCd, aggrUsgRecalPkList);
        }

        // Add Start 2018/05/07 QC#25409
        List<SVC_CONTR_BLLGTMsg> svcContrBllgTMsgList = getDelSvcContrBllgTMsgList(glblCmpyCd, svcContrBllgPkList);
        cancelWFBySvcContrBllg(msgMap, glblCmpyCd, svcContrBllgTMsgList);
        // START 2018/08/30 [QC#27967,ADD]
        boolean existsDtl = checkUnApprovedUsgChrgForDtl(glblCmpyCd, svcContrBllgTMsgList);
        boolean existsHdr = checkUnApprovedUsgChrg(glblCmpyCd, svcContrBllgTMsgList);
        // END 2018/08/30 [QC#27967,ADD]
        // Add End 2018/05/07 QC#25409
        NSZC0470Query.getInstance().removeSvcContrBllg(glblCmpyCd, svcContrBllgPkList);
        // Add Start 2018/05/07 QC#25409
        // START 2018/08/30 [QC#27967,MOD]
        // updateBllgHold(glblCmpyCd, svcContrBllgTMsgList);
        updateBllgHold(glblCmpyCd, svcContrBllgTMsgList, existsDtl, existsHdr);
        // END 2018/08/30 [QC#27967,MOD]
        // Add End 2018/05/07 QC#25409
        return true;
    }

    // ADD Start 01/21/2016 <QC#3331>
    /**
     * @param msgMap S21ApiMessageMap
     * @param glblCmpyCd String
     * @param dsContrBllgSchdPk prntSvcContrBllgPk
     */
    public static void deleteSvcContrAddlChrgInfo(S21ApiMessageMap msgMap, String glblCmpyCd, BigDecimal prntSvcContrBllgPk) {

        List<BigDecimal> svcContrBllgPkList = NSZC0470Query.getInstance().getSvcContrBllgPkForAddl(glblCmpyCd, prntSvcContrBllgPk);

        for (BigDecimal svcContrBllgPk : svcContrBllgPkList) {

            List<BigDecimal> svcContrBllgAllocPkList = NSZC0470Query.getInstance().getSvcContrBllgAllocPk(glblCmpyCd, svcContrBllgPk);
            NSZC0470Query.getInstance().removeSvcContrBllgAlloc(glblCmpyCd, svcContrBllgAllocPkList);

            List<BigDecimal> svcContrAddlChrgBllgPkList = NSZC0470Query.getInstance().getSvcContrAddlChrgBllgPk(glblCmpyCd, svcContrBllgPk);
            NSZC0470Query.getInstance().removeSvcContrAddlChrgBllg(glblCmpyCd, svcContrAddlChrgBllgPkList);
        }

        // Add Start 2018/05/07 QC#25409
        List<SVC_CONTR_BLLGTMsg> svcContrBllgTMsgList = getDelSvcContrBllgTMsgList(glblCmpyCd, svcContrBllgPkList);
        // START 2022/05/16 K.Kitachi [QC#60030, DEL]
//        cancelWFBySvcContrBllg(msgMap, glblCmpyCd, svcContrBllgTMsgList);
        // END 2022/05/16 K.Kitachi [QC#60030, DEL]
        // START 2018/08/30 [QC#27967,ADD]
        boolean existsDtl = checkUnApprovedUsgChrgForDtl(glblCmpyCd, svcContrBllgTMsgList);
        boolean existsHdr = checkUnApprovedUsgChrg(glblCmpyCd, svcContrBllgTMsgList);
        // END 2018/08/30 [QC#27967,ADD]
        // Add End 2018/05/07 QC#25409
        NSZC0470Query.getInstance().removeSvcContrBllg(glblCmpyCd, svcContrBllgPkList);
        // Add Start 2018/05/07 QC#25409
        // START 2018/08/30 [QC#27967,MOD]
        // updateBllgHold(glblCmpyCd, svcContrBllgTMsgList);
        updateBllgHold(glblCmpyCd, svcContrBllgTMsgList, existsDtl, existsHdr);
        // END 2018/08/30 [QC#27967,MOD]
        // Add End 2018/05/07 QC#25409
    }
    // ADD End 01/21/2016 <QC#3331>

    /**
     * @param msgMap S21ApiMessageMap
     * @param inTMsg DS_CONTR_PRC_EFFTMsg
     * @param schdSmryBeanList List<CalcSchdSmryTermAndAmtForBaseBean>
     */
    public static void updateTermAmtForPrcEff(S21ApiMessageMap msgMap, DS_CONTR_PRC_EFFTMsg inTMsg, List<CalcSchdSmryTermAndAmtForBaseBean> schdSmryBeanList) {

        BigDecimal termAmt = BigDecimal.ZERO;
        // 2018/05/07 QC#22482 Mod Start
//        for (CalcSchdSmryTermAndAmtForBaseBean sshcSmryBean : schdSmryBeanList) {
//            BigDecimal baseSubTotPrcDealAmt = sshcSmryBean.getBaseSubTotPrcDealAmt();
//            if (hasValue(baseSubTotPrcDealAmt)) {
//                termAmt = termAmt.add(baseSubTotPrcDealAmt);
//            }
//        }
        if (schdSmryBeanList != null) {
            for (CalcSchdSmryTermAndAmtForBaseBean sshcSmryBean : schdSmryBeanList) {
                BigDecimal baseSubTotPrcDealAmt = sshcSmryBean.getBaseSubTotPrcDealAmt();
                if (hasValue(baseSubTotPrcDealAmt)) {
                    termAmt = termAmt.add(baseSubTotPrcDealAmt);
                }
            }
        }
        // 2018/05/07 QC#22482 Mod End

        setValue(inTMsg.basePrcTermDealAmtRate, termAmt);
        S21ApiTBLAccessor.update(inTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSAM0031E, new String[] {"DS_CONTR_PRC_EFF" });
        }
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     */
    public static void updateTermAmtForDsContrDtl(S21ApiMessageMap msgMap, String glblCmpyCd, BigDecimal dsContrDtlPk) {

        BigDecimal termAmt = NSZC0470Query.getInstance().getTermAmtRate(glblCmpyCd, dsContrDtlPk);

        DS_CONTR_DTLTMsg inTMsg = new DS_CONTR_DTLTMsg();
        setValue(inTMsg.glblCmpyCd, glblCmpyCd);
        setValue(inTMsg.dsContrDtlPk, dsContrDtlPk);
        inTMsg = (DS_CONTR_DTLTMsg) S21ApiTBLAccessor.findByKey(inTMsg);
        if (inTMsg == null) {
            return;
        }
        setValue(inTMsg.basePrcTermDealAmtRate, termAmt);

        S21ApiTBLAccessor.update(inTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSAM0031E, new String[] {"DS_CONTR_DTL" });
        }
    }

    // Add Start 08/02/2016 <QC#7402>
    /**
     * @param msgMap S21ApiMessageMap
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param slsDt String
     */
    public static void updateBacePrcAmtAndTermAmtForDsContrDtl(S21ApiMessageMap msgMap, String glblCmpyCd, BigDecimal dsContrDtlPk, String slsDt) {

        // START 2017/08/29 K.Kojima [QC#20057,ADD]
        String bllgCycleCd = NSZC0470Query.getInstance().getCurPeBllgCycleCdForBase(glblCmpyCd, dsContrDtlPk, slsDt);
        // END 2017/08/29 K.Kojima [QC#20057,ADD]
        BigDecimal basePrcAmt = NSZC0470Query.getInstance().getCurPeBasePrcDealAmt(glblCmpyCd, dsContrDtlPk, slsDt);
        BigDecimal termAmt = NSZC0470Query.getInstance().getTermAmtRate(glblCmpyCd, dsContrDtlPk);

        DS_CONTR_DTLTMsg inTMsg = new DS_CONTR_DTLTMsg();
        setValue(inTMsg.glblCmpyCd, glblCmpyCd);
        setValue(inTMsg.dsContrDtlPk, dsContrDtlPk);
        inTMsg = (DS_CONTR_DTLTMsg) S21ApiTBLAccessor.findByKey(inTMsg);
        if (inTMsg == null) {
            return;
        }
        setValue(inTMsg.basePrcDealAmt, basePrcAmt);
        setValue(inTMsg.basePrcTermDealAmtRate, termAmt);
        // START 2017/08/21 M.Kidokoro [QC#20057, ADD]
        setValue(inTMsg.baseBllgCycleCd, bllgCycleCd);
        // END 2017/08/21 M.Kidokoro [QC#20057, ADD]

        S21ApiTBLAccessor.update(inTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSAM0031E, new String[] {"DS_CONTR_DTL" });
        }
    }
    // Add End   08/02/2016 <QC#7402>

    // START 2017/08/21 M.Kidokoro [QC#20057, ADD]
    /**
     * updateDsContrBllgMtrInfo
     * 
     * @param msgMap S21ApiMessageMap
     * @param glblCmpyCd String
     * @param dsContrBllgMtrPk BigDecimal
     * @param slsDt String
     */
    public static void updateBllgMtrBllgCycleCdForDsContrBllgMtr(S21ApiMessageMap msgMap, String glblCmpyCd, BigDecimal dsContrBllgMtrPk, String slsDt) {

        String bllgCycleCd = NSZC0470Query.getInstance().getCurPeBllgCycleCdForUsage(glblCmpyCd, dsContrBllgMtrPk, slsDt);

        DS_CONTR_BLLG_MTRTMsg inTMsg = new DS_CONTR_BLLG_MTRTMsg();
        setValue(inTMsg.glblCmpyCd, glblCmpyCd);
        setValue(inTMsg.dsContrBllgMtrPk, dsContrBllgMtrPk);
        inTMsg = (DS_CONTR_BLLG_MTRTMsg) S21ApiTBLAccessor.findByKey(inTMsg);
        if (inTMsg == null) {
            return;
        }
        setValue(inTMsg.bllgMtrBllgCycleCd, bllgCycleCd);

        S21ApiTBLAccessor.update(inTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSAM0031E, new String[] {"DS_CONTR_BLLG_MTR" });
        }
    }
    // END 2017/08/21 M.Kidokoro [QC#20057, ADD]

    // START 2021/04/06 S.Go [QC#58642, ADD]
    /**
     * updateCumCopyEndDtForDsContrBllgMtr
     * 
     * @param msgMap S21ApiMessageMap
     * @param glblCmpyCd String
     * @param dsContrBllgMtrPk BigDecimal
     * @param effFromDt String
     * @param effThruDt String
     */
    public static void updateCumCopyEndDtForDsContrBllgMtr(S21ApiMessageMap msgMap, String glblCmpyCd, BigDecimal dsContrBllgMtrPk, String effFromDt, String effThruDt) {
        // get current value of Cum Copy End Date
        String cumCopyEndDt = NSZC0470Query.getInstance().getCurPeCumCopyEndDtForUsage(glblCmpyCd, dsContrBllgMtrPk);
        // get the Contract Line End Date BEFORE Renewal
        String contrLineEndDt = ZYPDateUtil.addDays(effFromDt, -1);
        // Cum Copy End Date will be extended if
        // Case B: Cum Copy End Date = Contract Line End Date BEFORE Renewal
        // Case D: Cum Copy End Date > Contract Line End Date BEFORE Renewal AND Cum Copy End Date < Contract Line End Date AFTER Renewal
        if (cumCopyEndDt != null
            && (cumCopyEndDt.compareTo(contrLineEndDt) >= 0 && cumCopyEndDt.compareTo(effThruDt) < 0)) {

            DS_CONTR_BLLG_MTRTMsg inTMsg = new DS_CONTR_BLLG_MTRTMsg();
            setValue(inTMsg.glblCmpyCd, glblCmpyCd);
            setValue(inTMsg.dsContrBllgMtrPk, dsContrBllgMtrPk);
            inTMsg = (DS_CONTR_BLLG_MTRTMsg) S21ApiTBLAccessor.findByKey(inTMsg);

            if (inTMsg == null) {
                return;
            }
            setValue(inTMsg.cumCopyEndDt, effThruDt);
            S21ApiTBLAccessor.update(inTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                msgMap.addXxMsgIdWithPrm(NSAM0031E, new String[] {"DS_CONTR_BLLG_MTR" });
            }
        }
    }
    // END 2021/04/06 S.Go [QC#58642, ADD]

    /**
     * @param msgMap S21ApiMessageMap
     * @param glblCmpyCd String
     * @param prcEffPkList List<BigDecimal>
     * @param usgChrgFlg String
     * @param dsContrDtlTp String
     */
    public static void deletePrcEffAndSchdInfo(S21ApiMessageMap msgMap, String glblCmpyCd, List<BigDecimal> prcEffPkList, String usgChrgFlg, String dsContrDtlTp) {

        // DS_CONTR_PRC_EFF
        NSZC0470Query.getInstance().removePrcEff(glblCmpyCd, prcEffPkList);

        for (BigDecimal dsContrPrcEffPk : prcEffPkList) {

            // DS_CONTR_BLLG_SCHD_SMRY
            List<BigDecimal> schdSmryPkList = NSZC0470Query.getInstance().getDeleteSchdSmryListByPrcEffPk(glblCmpyCd, dsContrPrcEffPk);
            NSZC0470Query.getInstance().removeSchdSmry(glblCmpyCd, schdSmryPkList);

            // DS_CONTR_BLLG_SCHD
            List<BigDecimal> schdPkList = NSZC0470Query.getInstance().getDeleteSchdListByPrcEffPk(glblCmpyCd, dsContrPrcEffPk);
            // Add Start 01/21/2016 <QC#3331>
            for (BigDecimal schdPk : schdPkList) {
                NSZC047001CommonLogic.deleteSvcContrBllgInfo(msgMap, glblCmpyCd, schdPk);
            }
            // Add Start 01/21/2016 <QC#3331>

            NSZC0470Query.getInstance().removeSchd(glblCmpyCd, schdPkList);
            // START 2017/10/03 E.Kameishi [QC18636, ADD]
            NSZC0470Query.getInstance().removeSchdTestMtrSmry(glblCmpyCd, schdPkList);
            // END 2017/10/03 E.Kameishi [QC18636, ADD]

            if (FLG_ON_Y.equals(usgChrgFlg)) {

                // DS_CONTR_PRC_EFF_MTR
                List<BigDecimal> prcEffMtrPkList = NSZC0470Query.getInstance().getDeletePrcEffMtrList(glblCmpyCd, dsContrPrcEffPk);
                NSZC0470Query.getInstance().removePrcEffMtr(glblCmpyCd, prcEffMtrPkList);

                // DS_CONTR_BLLG_SCHD_MTR
                List<BigDecimal> schdMtrPkList = NSZC0470Query.getInstance().getDeleteSchdMtrList(glblCmpyCd, schdSmryPkList);
                NSZC0470Query.getInstance().removeSchdMtr(glblCmpyCd, schdMtrPkList);

                if (DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTp)) {
                    for (BigDecimal prntSchdPk : schdPkList) {

                        // mod start 08/23/2016 QC#13150
                        // DS_CONTR_BLLG_SCHD
                        List<BigDecimal> childSchdPkList = NSZC0470Query.getInstance().getDeleteChildSchdList(glblCmpyCd, prntSchdPk);
                        for (BigDecimal childSchdPk : childSchdPkList) {
                            deleteSvcContrBllgInfo(msgMap, glblCmpyCd, childSchdPk);
                        }
                        NSZC0470Query.getInstance().removeSchd(glblCmpyCd, childSchdPkList);
                        // mod end 08/23/2016 QC#13150
                    }
                }
            }
        }
    }

    /**
     * sort PMsg
     * @param list List<NSZC047008_xxBaseLineListPMsg>
     * @param sortKey String
     * @param order int
     */
    public static void sort(List<NSZC047008_xxBaseLineListPMsg> list, String sortKey, int order) {

        String[] sortKeys = new String[1];
        sortKeys[0] = sortKey;

        int[] orders = new int[1];
        orders[0] = order;

        Collections.sort(list, new Comp(sortKeys, orders));
    }

    /**
     * sort PMsg
     * @param list List<NSZC047002_xxBaseLineListPMsg>
     * @param sortKey String
     * @param order int
     */
    public static void sortMode02(List<NSZC047002_xxBaseLineListPMsg> list, String sortKey, int order) {

        String[] sortKeys = new String[1];
        sortKeys[0] = sortKey;

        int[] orders = new int[1];
        orders[0] = order;

        Collections.sort(list, new Comp(sortKeys, orders));
    }

    /**
     * Comparator Class
     */
    private static class Comp implements Comparator<Object>, Serializable {

        /** serialVersionUID */
        private static final long serialVersionUID = 1L;

        /**
         * sortKeys
         */
        private String[] sortKeys;

        /**
         * orders
         */
        private int[] orders;

        public Comp(String[] sortKeys, int[] orders) {
            this.sortKeys = sortKeys;
            this.orders = orders;
        }

        @Override
        public int compare(Object oPMsg1, Object oPMsg2) {

            int c = 0;
            for (int idx = 0; idx < this.sortKeys.length; idx++) {
                Object val1 = getValue(sortKeys[idx], oPMsg1);
                Object val2 = getValue(sortKeys[idx], oPMsg2);

                if (orders[idx] == ASC) {
                    // ASC
                    if (val1 instanceof BigDecimal) {
                        c = compareTo((BigDecimal) val1, (BigDecimal) val2);
                    } else {
                        c = compareTo((String) val1, (String) val2);
                    }
                } else {
                    // DESC
                    if (val1 instanceof BigDecimal) {
                        c = compareTo((BigDecimal) val2, (BigDecimal) val1);
                    } else {
                        c = compareTo((String) val2, (String) val1);
                    }
                }

                if (c != 0) {
                    break;
                }
            }
            return c;
        }

        private Object getValue(String key, Object oPMsg) {

            try {
                Field field = oPMsg.getClass().getField(key);

                if (key.endsWith("Dt")) {
                    EZDPDateItem item = (EZDPDateItem) field.get(oPMsg);
                    return item.getValue();
                } else {
                    EZDPBigDecimalItem item = (EZDPBigDecimalItem) field.get(oPMsg);
                    return item.getValue();
                }
            } catch (Exception e) {
                EZDDebugOutput.println(1, "ParseException occured.", e);
            }
            return "";
        }

        private int compareTo(BigDecimal val1, BigDecimal val2) {
            if (hasValue(val1) && hasValue(val2)) {
                return val1.compareTo(val2);
            } else {
                return val2.compareTo(val1);
            }
        }

        private int compareTo(String val1, String val2) {
            if (hasValue(val1) && hasValue(val2)) {
                return val1.compareTo(val2);
            } else {
                return val2.compareTo(val1);
            }
        }
    }

    /**
     * @param pMsg NSZC047001PMsg
     * @return List<Map<String, Object>>
     */
    public static List<Map<String, Object>> getPrcEffList(NSZC047001PMsg pMsg) {
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        BigDecimal dsContgrDtlPk = pMsg.dsContrDtlPk.getValue();
        String baseChrgFlg = pMsg.baseChrgFlg.getValue();
        List<Map<String, Object>> currPrcEffList = null;
        if (FLG_ON_Y.equals(baseChrgFlg)) {
            currPrcEffList = NSZC0470Query.getInstance().getPrcEffList(glblCmpyCd, dsContgrDtlPk, null, baseChrgFlg);
        } else {
            BigDecimal dsContrBllgMtrPk = pMsg.xxMtrLineList.no(0).dsContrBllgMtrPk_ML.getValue();
            currPrcEffList = NSZC0470Query.getInstance().getPrcEffList(glblCmpyCd, dsContgrDtlPk, dsContrBllgMtrPk, baseChrgFlg);
        }
        return currPrcEffList;
    }

    /**
     * @param pMsg NSZC047001PMsg
     * @return String
     */
    public static String getInvSchdMaxThruDt(NSZC047001PMsg pMsg) {
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        BigDecimal dsContgrDtlPk = pMsg.dsContrDtlPk.getValue();
        String baseChrgFlg = pMsg.baseChrgFlg.getValue();
        String invSchdMaxThruDt = null;
        if (FLG_ON_Y.equals(baseChrgFlg)) {
            invSchdMaxThruDt = NSZC0470Query.getInstance().getInvSchdMaxThruDt(glblCmpyCd, dsContgrDtlPk, null, baseChrgFlg);
        } else {
            BigDecimal dsContrBllgMtrPk = pMsg.xxMtrLineList.no(0).dsContrBllgMtrPk_ML.getValue();
            invSchdMaxThruDt = NSZC0470Query.getInstance().getInvSchdMaxThruDt(glblCmpyCd, dsContgrDtlPk, dsContrBllgMtrPk, baseChrgFlg);
        }
        return invSchdMaxThruDt;
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param dsContrDtlTp String
     * @param effFromDt String
     */
    public static void deletePrcEffAndSchdInfo(S21ApiMessageMap msgMap, String dsContrDtlTp, String effFromDt) {

        NSZC047001PMsg pMsg = (NSZC047001PMsg) msgMap.getPmsg();

        String baseChrgFlg = pMsg.baseChrgFlg.getValue();
        String usgChrgFlg = pMsg.usgChrgFlg.getValue();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        BigDecimal dsContrDtlPk = pMsg.dsContrDtlPk.getValue();
        BigDecimal dsContrBllgMtrPk = null;
        if (pMsg.xxMtrLineList.getValidCount() > 0) {
            dsContrBllgMtrPk = pMsg.xxMtrLineList.no(0).dsContrBllgMtrPk_ML.getValue();
        }
        effFromDt = ZYPDateUtil.addDays(effFromDt, 1);

        List<Map<String, BigDecimal>> deleteBllgSchdList = NSZC0470Query.getInstance().getBllgSchdForDelete(glblCmpyCd, dsContrDtlPk, dsContrBllgMtrPk, effFromDt, baseChrgFlg);
        List<BigDecimal> prcEffPkList = getPrcEffPkList(deleteBllgSchdList);
        List<BigDecimal> schdSmryPkList = getSchdSmryPkList(deleteBllgSchdList);
        List<BigDecimal> schdPkList = getSchdPkList(deleteBllgSchdList);

        // Add Start 01/21/2016 <QC#3331>
        for (BigDecimal schdPk : schdPkList) {
            deleteSvcContrBllgInfo(msgMap, glblCmpyCd, schdPk);
        }
        // Add Start 01/21/2016 <QC#3331>
        NSZC0470Query.getInstance().removeSchd(glblCmpyCd, schdPkList);
        // START 2017/10/03 E.Kameishi [QC18636, ADD]
        NSZC0470Query.getInstance().removeSchdTestMtrSmry(glblCmpyCd, schdPkList);
        // END 2017/10/03 E.Kameishi [QC18636, ADD]
        if (FLG_ON_Y.equals(usgChrgFlg) && DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTp)) {
            for (BigDecimal prntSchdPk : schdPkList) {

                // mod start 08/23/2016 QC#13150
                List<BigDecimal> childSchdPkList = NSZC0470Query.getInstance().getDeleteChildSchdList(glblCmpyCd, prntSchdPk);
                for (BigDecimal childSchdPk : childSchdPkList) {
                    deleteSvcContrBllgInfo(msgMap, glblCmpyCd, childSchdPk);
                }
                NSZC0470Query.getInstance().removeSchd(glblCmpyCd, childSchdPkList);
                // mod end 08/23/2016 QC#13150
            }
        }

        for (BigDecimal schdSmryPk : schdSmryPkList) {

            DS_CONTR_BLLG_SCHD_SMRYTMsg shcdSmryTMsg = NSZC0470Query.getInstance().getDsContrBllgSchdSmryTMsg(glblCmpyCd, schdSmryPk);
            //start 2017/11/07 QC#22301,mod
            if (shcdSmryTMsg != null && effFromDt.compareTo(shcdSmryTMsg.bllgSchdFromDt.getValue()) <= 0) {
            //end 2017/11/07 QC#22301,mod
                NSZC0470Query.getInstance().removeSchdSmry(glblCmpyCd, schdSmryPk);

                if (FLG_ON_Y.equals(usgChrgFlg)) {
                    List<BigDecimal> schdMtrPkList = NSZC0470Query.getInstance().getDsContrBllgSchdMtrForCancel(glblCmpyCd, schdSmryPk);
                    NSZC0470Query.getInstance().removeSchdMtr(glblCmpyCd, schdMtrPkList);
                }
            }
        }

        for (BigDecimal prcEffPk : prcEffPkList) {
            DS_CONTR_PRC_EFFTMsg prcEffTMsg = NSZC0470Query.getInstance().getDsContrPrcEffTMsg(glblCmpyCd, prcEffPk);
            //start 2017/11/07 QC#22301,mod
            if (prcEffTMsg != null && effFromDt.compareTo(prcEffTMsg.contrPrcEffFromDt.getValue()) <= 0) {
            //end 2017/11/07 QC#22301,mod
                NSZC0470Query.getInstance().removePrcEff(glblCmpyCd, prcEffPk);

                if (FLG_ON_Y.equals(usgChrgFlg)) {
                    List<BigDecimal> prcEffMtrPkList = NSZC0470Query.getInstance().getDeletePrcEffMtrList(glblCmpyCd, prcEffPk);
                    NSZC0470Query.getInstance().removePrcEffMtr(glblCmpyCd, prcEffMtrPkList);
                }
            }
        }
    }

    // START 2023/08/18 T.Kojima [QC#60846, ADD]
    /**
     * @param msgMap S21ApiMessageMap
     * @param dsContrDtlTp String
     * @param effFromDt String
     */
    public static void deletePastPrcEffAndSchdInfo(S21ApiMessageMap msgMap, String dsContrDtlTp, String effFromDt) {

        NSZC047001PMsg pMsg = (NSZC047001PMsg) msgMap.getPmsg();

        String baseChrgFlg = pMsg.baseChrgFlg.getValue();
        String usgChrgFlg = pMsg.usgChrgFlg.getValue();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        BigDecimal dsContrDtlPk = pMsg.dsContrDtlPk.getValue();
        BigDecimal dsContrBllgMtrPk = null;
        if (pMsg.xxMtrLineList.getValidCount() > 0) {
            dsContrBllgMtrPk = pMsg.xxMtrLineList.no(0).dsContrBllgMtrPk_ML.getValue();
        }

        List<Map<String, BigDecimal>> deleteBllgSchdList = NSZC0470Query.getInstance().getPastBllgSchdForDelete(glblCmpyCd, dsContrDtlPk, dsContrBllgMtrPk, effFromDt, baseChrgFlg);
        List<BigDecimal> prcEffPkList = getPrcEffPkList(deleteBllgSchdList);
        List<BigDecimal> schdSmryPkList = getSchdSmryPkList(deleteBllgSchdList);
        List<BigDecimal> schdPkList = getSchdPkList(deleteBllgSchdList);

        for (BigDecimal schdPk : schdPkList) {
            deleteSvcContrBllgInfo(msgMap, glblCmpyCd, schdPk);
        }

        NSZC0470Query.getInstance().removeSchd(glblCmpyCd, schdPkList);
        NSZC0470Query.getInstance().removeSchdTestMtrSmry(glblCmpyCd, schdPkList);
        if (FLG_ON_Y.equals(usgChrgFlg) && DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTp)) {
            for (BigDecimal prntSchdPk : schdPkList) {

                List<BigDecimal> childSchdPkList = NSZC0470Query.getInstance().getDeleteChildSchdList(glblCmpyCd, prntSchdPk);
                for (BigDecimal childSchdPk : childSchdPkList) {
                    deleteSvcContrBllgInfo(msgMap, glblCmpyCd, childSchdPk);
                }
                NSZC0470Query.getInstance().removeSchd(glblCmpyCd, childSchdPkList);
            }
        }

        for (BigDecimal schdSmryPk : schdSmryPkList) {
            DS_CONTR_BLLG_SCHD_SMRYTMsg shcdSmryTMsg = NSZC0470Query.getInstance().getDsContrBllgSchdSmryTMsg(glblCmpyCd, schdSmryPk);
            if (shcdSmryTMsg != null && effFromDt.compareTo(shcdSmryTMsg.bllgSchdThruDt.getValue()) >= 0) {
                NSZC0470Query.getInstance().removeSchdSmry(glblCmpyCd, schdSmryPk);

                if (FLG_ON_Y.equals(usgChrgFlg)) {
                    List<BigDecimal> schdMtrPkList = NSZC0470Query.getInstance().getDsContrBllgSchdMtrForCancel(glblCmpyCd, schdSmryPk);
                    NSZC0470Query.getInstance().removeSchdMtr(glblCmpyCd, schdMtrPkList);
                }
            }
        }

        for (BigDecimal prcEffPk : prcEffPkList) {
            DS_CONTR_PRC_EFFTMsg prcEffTMsg = NSZC0470Query.getInstance().getDsContrPrcEffTMsg(glblCmpyCd, prcEffPk);
            if (prcEffTMsg != null && effFromDt.compareTo(prcEffTMsg.contrPrcEffThruDt.getValue()) >= 0) {
                NSZC0470Query.getInstance().removePrcEff(glblCmpyCd, prcEffPk);

                if (FLG_ON_Y.equals(usgChrgFlg)) {
                    List<BigDecimal> prcEffMtrPkList = NSZC0470Query.getInstance().getDeletePrcEffMtrList(glblCmpyCd, prcEffPk);
                    NSZC0470Query.getInstance().removePrcEffMtr(glblCmpyCd, prcEffMtrPkList);
                }
            }
        }
    }
    // END 2023/08/18 T.Kojima [QC#60846, ADD]

    /**
     * @param msgMap S21ApiMessageMap
     * @param dsContrPrcEffPk BigDecimal
     * @return boolean
     */
    public static boolean deletePrcEffAndSchdInfo(S21ApiMessageMap msgMap, BigDecimal dsContrPrcEffPk) {

        NSZC047001PMsg pMsg = (NSZC047001PMsg) msgMap.getPmsg();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        String dsContrDtlTp = NSZC0470Query.getInstance().getDsContrDtlTp(glblCmpyCd, pMsg.dsContrDtlPk.getValue());
        // DS_CONTR_BLLG_SCHD
        List<BigDecimal> delSchdPkList = NSZC0470Query.getInstance().getDeleteSchdList(glblCmpyCd, dsContrPrcEffPk);
        // Add Start 01/21/2016 <QC#3331>
        for (BigDecimal delSchdPk : delSchdPkList) {
            deleteSvcContrBllgInfo(msgMap, glblCmpyCd, delSchdPk);
        }
        // Add Start 01/21/2016 <QC#3331>
        if (DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTp)) {
            for (BigDecimal delSchdPk : delSchdPkList) {
                List<BigDecimal> childDsContrBllgSchdPkList = NSZC0470Query.getInstance().getChildDsContrBllgSchdForCancel(glblCmpyCd, delSchdPk);
                for (BigDecimal childDsContrBllgSchdPk : childDsContrBllgSchdPkList) {
                    // add start 08/23/2016 QC#13150
                    deleteSvcContrBllgInfo(msgMap, glblCmpyCd, childDsContrBllgSchdPk);
                    // add end 08/23/2016 QC#13150
                    NSZC0470Query.getInstance().removeSchd(glblCmpyCd, childDsContrBllgSchdPk);
                    // START 2017/10/03 E.Kameishi [QC18636, ADD]
                    NSZC0470Query.getInstance().removeSchdTestMtrSmry(glblCmpyCd, childDsContrBllgSchdPk);
                    // END 2017/10/03 E.Kameishi [QC18636, ADD]
                }
            }
        }
        NSZC0470Query.getInstance().removeSchd(glblCmpyCd, delSchdPkList);
        // START 2017/10/03 E.Kameishi [QC18636, ADD]
        NSZC0470Query.getInstance().removeSchdTestMtrSmry(glblCmpyCd, delSchdPkList);
        // END 2017/10/03 E.Kameishi [QC18636, ADD]

        // DS_CONTR_BLLG_SCHD_SMRY
        List<BigDecimal> delSchdSmryPkList = NSZC0470Query.getInstance().getDeleteSchdSmryList(glblCmpyCd, dsContrPrcEffPk);
        NSZC0470Query.getInstance().removeSchdSmry(glblCmpyCd, delSchdSmryPkList);

        // DS_CONTR_PRC_EFF
        List<BigDecimal> delPrcEffPkList = NSZC0470Query.getInstance().getDeletePrcEffList(glblCmpyCd, dsContrPrcEffPk);
        NSZC0470Query.getInstance().removePrcEff(glblCmpyCd, delPrcEffPkList);

        // DS_CONTR_PRC_EFF_MTR
        List<BigDecimal> delPrcEffMtrPkList = NSZC0470Query.getInstance().getDeletePrcEffMtrList(glblCmpyCd, dsContrPrcEffPk);
        NSZC0470Query.getInstance().removePrcEffMtr(glblCmpyCd, delPrcEffMtrPkList);

        // DS_CONTR_BLLG_SCHD_MTR
        List<BigDecimal> delSchdMtrPkList = NSZC0470Query.getInstance().getDeleteSchdMtrList(glblCmpyCd, delSchdSmryPkList);
        NSZC0470Query.getInstance().removeSchdMtr(glblCmpyCd, delSchdMtrPkList);

        return true;
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param effFromDt String
     */
    public static void updatePrcEffAndSchdInfo(S21ApiMessageMap msgMap, String effFromDt) {

        NSZC047001PMsg pMsg = (NSZC047001PMsg) msgMap.getPmsg();

        String baseChrgFlg = pMsg.baseChrgFlg.getValue();
        String glblCmpyCd = pMsg.glblCmpyCd.getValue();
        BigDecimal dsContrDtlPk = pMsg.dsContrDtlPk.getValue();
        BigDecimal dsContrBllgMtrPk = null;
        if (pMsg.xxMtrLineList.getValidCount() > 0) {
            dsContrBllgMtrPk = pMsg.xxMtrLineList.no(0).dsContrBllgMtrPk_ML.getValue();
        }

        effFromDt = ZYPDateUtil.addDays(effFromDt, 1);
        List<Map<String, BigDecimal>> updateBllgSchdList = NSZC0470Query.getInstance().getBllgSchdForUpdate(glblCmpyCd, dsContrDtlPk, dsContrBllgMtrPk, effFromDt, baseChrgFlg);
        List<BigDecimal> prcEffPkList = getPrcEffPkList(updateBllgSchdList);
        List<BigDecimal> schdSmryPkList = getSchdSmryPkList(updateBllgSchdList);

        for (BigDecimal schdSmryPk : schdSmryPkList) {

            DS_CONTR_BLLG_SCHD_SMRYTMsg shcdSmryTMsg = NSZC0470Query.getInstance().getDsContrBllgSchdSmryTMsg(glblCmpyCd, schdSmryPk);
            if (shcdSmryTMsg.bllgSchdFromDt.getValue().compareTo(effFromDt) <= 0 && effFromDt.compareTo(shcdSmryTMsg.bllgSchdThruDt.getValue()) <= 0) {
                updateSchdSmry(msgMap, shcdSmryTMsg, effFromDt, baseChrgFlg);
                break;
            }
        }

        for (BigDecimal prcEffPk : prcEffPkList) {

            DS_CONTR_PRC_EFFTMsg prcEffTMsg = NSZC0470Query.getInstance().getDsContrPrcEffTMsg(glblCmpyCd, prcEffPk);
            if (prcEffTMsg.contrPrcEffFromDt.getValue().compareTo(effFromDt) <= 0 && effFromDt.compareTo(prcEffTMsg.contrPrcEffThruDt.getValue()) <= 0) {
                updatePrcEff(msgMap, prcEffTMsg, effFromDt, baseChrgFlg);
                break;
            }
        }
    }

    private static void updateSchdSmry(S21ApiMessageMap msgMap, DS_CONTR_BLLG_SCHD_SMRYTMsg inTMsg, String effFromDt, String baseChrgFlg) {

        String glblCmpyCd = inTMsg.glblCmpyCd.getValue();
        BigDecimal dsContrDtlPk = inTMsg.dsContrDtlPk.getValue();
        BigDecimal dsContrBllgMtrPk = inTMsg.dsContrBllgMtrPk.getValue();
        String bllgSchdFromDt = inTMsg.bllgSchdFromDt.getValue();
        String bllgSchdThruDt = inTMsg.bllgSchdThruDt.getValue();

        Map<String, BigDecimal> smryBasePrcAmt = NSZC0470Query.getInstance().getSummaryBasePrcAmt(glblCmpyCd, dsContrDtlPk, dsContrBllgMtrPk, bllgSchdFromDt, bllgSchdThruDt, baseChrgFlg);

        if (smryBasePrcAmt != null) {
            if (FLG_ON_Y.equals(baseChrgFlg)) {

                setValue(inTMsg.basePrcDealAdjAmt, smryBasePrcAmt.get("BASE_PRC_ADJ_DEAL_AMT"));
                setValue(inTMsg.baseSubTotPrcDealAmt, smryBasePrcAmt.get("BASE_ACTL_PRC_DEAL_AMT"));
            }
            setValue(inTMsg.perSchdNum, smryBasePrcAmt.get("PER_SCHD_NUM"));
        }

        setValue(inTMsg.bllgSchdThruDt, ZYPDateUtil.addDays(effFromDt, -1));

        S21ApiTBLAccessor.update(inTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSAM0031E, new String[]{"DS_CONTR_BLLG_SCHD_SMRY"});
        }
    }

    private static void updatePrcEff(S21ApiMessageMap msgMap, DS_CONTR_PRC_EFFTMsg inTMsg, String effFromDt, String baseChrgFlg) {

        if (FLG_ON_Y.equals(baseChrgFlg)) {
            String glblCmpyCd = inTMsg.glblCmpyCd.getValue();
            BigDecimal dsContrDtlPk = inTMsg.dsContrDtlPk.getValue();
            BigDecimal dsContrBllgMtrPk = inTMsg.dsContrBllgMtrPk.getValue();
            String bllgSchdFromDt = inTMsg.contrPrcEffFromDt.getValue();
            String bllgSchdThruDt = inTMsg.contrPrcEffThruDt.getValue();

            Map<String, BigDecimal> smryBasePrcAmt = NSZC0470Query.getInstance().getSummaryBasePrcAmt(glblCmpyCd, dsContrDtlPk, dsContrBllgMtrPk, bllgSchdFromDt, bllgSchdThruDt, baseChrgFlg);

            if (smryBasePrcAmt != null) {
                setValue(inTMsg.basePrcTermDealAmtRate, smryBasePrcAmt.get("BASE_ACTL_PRC_DEAL_AMT"));
            }
        }

        setValue(inTMsg.contrPrcEffThruDt, ZYPDateUtil.addDays(effFromDt, -1));

        S21ApiTBLAccessor.update(inTMsg);

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
            msgMap.addXxMsgIdWithPrm(NSAM0031E, new String[]{"DS_CONTR_BLLG_SCHD_SMRY"});
        }
    }

    /**
     * @param bllgSchdList List<Map<String, BigDecimal>>
     * @return List<BigDecimal>
     */
    public static List<BigDecimal> getPrcEffPkList(List<Map<String, BigDecimal>> bllgSchdList) {

        List<BigDecimal> pkList = new ArrayList<BigDecimal>();
        for (Map<String, BigDecimal> bllgSchdInfo : bllgSchdList) {
            BigDecimal pk = bllgSchdInfo.get("DS_CONTR_PRC_EFF_PK");
            if (!hasValue(pk)) {
                continue;
            }
            if (!pkList.contains(pk)) {
                pkList.add(pk);
            }
        }
        return pkList;
    }

    /**
     * @param bllgSchdList List<Map<String, BigDecimal>>
     * @return List<BigDecimal>
     */
    public static List<BigDecimal> getSchdSmryPkList(List<Map<String, BigDecimal>> bllgSchdList) {

        List<BigDecimal> pkList = new ArrayList<BigDecimal>();
        for (Map<String, BigDecimal> bllgSchdInfo : bllgSchdList) {
            BigDecimal pk = bllgSchdInfo.get("DS_CONTR_BLLG_SCHD_SMRY_PK");
            if (!hasValue(pk)) {
                continue;
            }
            if (!pkList.contains(pk)) {
                pkList.add(pk);
            }
        }
        return pkList;
    }

    /**
     * @param bllgSchdList List<Map<String, BigDecimal>>
     * @return List<BigDecimal>
     */
    public static List<BigDecimal> getSchdPkList(List<Map<String, BigDecimal>> bllgSchdList) {

        List<BigDecimal> pkList = new ArrayList<BigDecimal>();
        for (Map<String, BigDecimal> bllgSchdInfo : bllgSchdList) {
            BigDecimal pk = bllgSchdInfo.get("DS_CONTR_BLLG_SCHD_PK");
            if (!hasValue(pk)) {
                continue;
            }
            if (!pkList.contains(pk)) {
                pkList.add(pk);
            }
        }
        return pkList;
    }

    /**
     * @param after Calendar
     * @param before Calendar
     * @param contrCloDay String
     * @return int
     */
    public static int getDiffMonths(Calendar after, Calendar before, String contrCloDay) {

        int count = 0;

        Calendar tmp = (Calendar) before.clone();
        tmp.add(Calendar.MONTH, 1);
        if (MONTH_END_99.equals(contrCloDay)) {
            tmp = setEndMonth(tmp);
        }

        while (tmp.compareTo(after) <= 0) {
            tmp.add(Calendar.MONTH, 1);
            if (MONTH_END_99.equals(contrCloDay)) {
                tmp = setEndMonth(tmp);
            }
            count++;
        }

        return count;
    }

    /**
     * @param cal Calendar
     * @return Calendar
     */
    public static Calendar setEndMonth(Calendar cal) {

        int cloDay = cal.getActualMaximum(Calendar.DATE);
        cal.set(Calendar.DATE, cloDay);
        return cal;
    }


    /**
     * @param strDate String
     * @return Calendar
     */
    public static Calendar toCalendar(String strDate) {

        if (!hasValue(strDate)) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(S21CalendarUtilConstants.TYPE_YYYYMMDD);
        Date dt = null;
        try {
            dt = format.parse(strDate);
        } catch (ParseException e) {
            EZDDebugOutput.println(1, "ParseException occured.", e);
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        return cal;
    }

    /**
     * @param cloDay String
     * @return String
     */
    public static String convCloDay(String cloDay) {

        if (MONTH_END_0.equals(cloDay)) {
            cloDay = MONTH_END_99;
        }
        return cloDay;
    }

    /**
     * @param thruDt String
     * @param fromDt String
     * @return int
     */
    public static int getDiffDays(String thruDt, String fromDt) {

        int diffDays = ZYPDateUtil.getDiffDays(thruDt, fromDt) + 1;
        return diffDays;
    }

    // Add Start 03/28/2016 <QC#1003>
    /**
     * @param glblCmpyCd String
     * @param slsDt String
     * @param dsContrPk BigDecimal
     */
    public static void mtrEntryStsUpd(S21ApiMessageMap msgMap, String glblCmpyCd, String slsDt, BigDecimal dsContrPk) {

        // del start 2017/08/15 QC#18799
//        BigDecimal winBefAot = ZYPCodeDataUtil.getNumConstValue(BLLG_MTR_READ_WINDOW_BEF_DAYS, glblCmpyCd);
//        int winBefDays = 0;
//        if (hasValue(winBefAot)) {
//            winBefDays = winBefAot.intValue();
//        }
        // del start 2017/08/15 QC#18799
        BigDecimal winAftAot = ZYPCodeDataUtil.getNumConstValue(BLLG_MTR_READ_WINDOW_AFT_DAYS, glblCmpyCd);
        int winAftDays = 0;
        if (hasValue(winAftAot)) {
            winAftDays = winAftAot.intValue();
        }
        // mod start 2017/08/15 QC#18799
//        String thruDt = ZYPDateUtil.addDays(slsDt, winBefDays);
//        List<Map<String, Object>> getList = NSZC0470Query.getInstance().getMtrEntryStsUpd(glblCmpyCd, thruDt, dsContrPk);
        List<Map<String, Object>> getList = NSZC0470Query.getInstance().getMtrEntryStsUpd(glblCmpyCd, slsDt, dsContrPk);
        // mod end 2017/08/15 QC#18799
        for (Map<String, Object> target : getList) {
            DS_CONTR_BLLG_SCHDTMsg inTMsg = new DS_CONTR_BLLG_SCHDTMsg();
            setValue(inTMsg.glblCmpyCd, glblCmpyCd);
            setValue(inTMsg.dsContrBllgSchdPk, (BigDecimal) target.get("DS_CONTR_BLLG_SCHD_PK"));
            inTMsg = (DS_CONTR_BLLG_SCHDTMsg) S21ApiTBLAccessor.findByKey(inTMsg);
            if (inTMsg == null) {
                continue;
            }
            BigDecimal svcMachMstrPk = (BigDecimal) target.get("SVC_MACH_MSTR_PK");
            BigDecimal svcPhysMtrPk = NSZC0470Query.getInstance().getPhysMtrPk(glblCmpyCd, (BigDecimal) target.get("DS_CONTR_BLLG_MTR_PK"));
            if (!hasValue(svcPhysMtrPk)) {
                continue;
            }
            String bllgThruDt = (String) target.get("BLLG_SCHD_THRU_DT");
            // mod start 2017/08/15 QC#18799
//            String mtrFromdt = ZYPDateUtil.addDays(bllgThruDt, - winBefDays);
            BigDecimal winBefDays = (BigDecimal) target.get("BEF_DAYS");
            String mtrFromdt = ZYPDateUtil.addDays(bllgThruDt, - winBefDays.intValue());
            // mod end 2017/08/15 QC#18799
            String mtrThruDt = ZYPDateUtil.addDays(bllgThruDt, winAftDays);

            // START 2023/06/01 t.aizawa [QC#60752,ADD]
            String bllgFromDt = (String) target.get("BLLG_SCHD_FROM_DT");
            if (ZYPDateUtil.compare(mtrFromdt, bllgFromDt) < 0) {
                mtrFromdt = bllgFromDt;
            }
            // END   2023/06/01 t.aizawa [QC#60752,ADD]

            // START 2018/07/23 [QC#23863]
            SVC_PHYS_MTR_READTMsg bllgMtrRead = NSXC003001SvcPhysMtrRead.getBillingMeterSvcPhysMtrRead(glblCmpyCd, inTMsg.dsContrDtlPk.getValue(), svcMachMstrPk, svcPhysMtrPk, mtrFromdt, mtrThruDt);
            // END   2018/07/23 [QC#23863]
            if (bllgMtrRead == null) {
                continue;
            }
            setValue(inTMsg.mtrEntryCpltFlg, FLG_ON_Y);
            setValue(inTMsg.svcPhysMtrReadGrpSq, bllgMtrRead.svcPhysMtrReadGrpSq);
            S21ApiTBLAccessor.update(inTMsg);

            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                msgMap.addXxMsgIdWithPrm(NSAM0031E, new String[] {"DS_CONTR_BLLG_SCHD" });
            }
        }
    }
    // Add End   03/28/2016 <QC#1003>

    // Add Start   06/29/2016 <QC#7428,QC#7429>
    /**
     * @param msgMap S21ApiMessageMap
     * @param dsContrDtlPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
     * @return boolean
     */
    public static boolean deleteAllSchdInfo(S21ApiMessageMap msgMap) {

        NSZC047001PMsg pMsg = (NSZC047001PMsg) msgMap.getPmsg();
        BigDecimal dsContrBllgMtrPk = null;
        if (pMsg.xxMtrLineList.getValidCount() != 0) {
            dsContrBllgMtrPk = pMsg.xxMtrLineList.no(0).dsContrBllgMtrPk_ML.getValue();
        }
        List <DS_CONTR_PRC_EFFTMsg> asisPeList = NSZC0470Query.getInstance().getAsisPeList(pMsg.glblCmpyCd.getValue(), pMsg.dsContrDtlPk.getValue(), dsContrBllgMtrPk);
        for (DS_CONTR_PRC_EFFTMsg delPe : asisPeList) {
            deletePrcEffAndSchdInfo(msgMap, delPe.dsContrPrcEffPk.getValue());
        }

        return true;
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
     * @return String
     */
    public static String getFixSchdDt(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk) {
        String fixDtSchd = NSZC0470Query.getInstance().getFixDtFromSchd(glblCmpyCd, dsContrDtlPk, dsContrBllgMtrPk);
        String fixDtPe = NSZC0470Query.getInstance().getFixDtFromPe(glblCmpyCd, dsContrDtlPk, dsContrBllgMtrPk);
        if (!hasValue(fixDtSchd) && !hasValue(fixDtPe)) {
            return null;
        }
        if (hasValue(fixDtSchd) && hasValue(fixDtPe)) {
            if (fixDtSchd.compareTo(fixDtPe) < 0) {
                return fixDtPe;
            } else {
                return fixDtSchd;
            }
        }
        if (hasValue(fixDtSchd)) {
            return fixDtSchd;
        }
        return fixDtPe;
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param dsContrDtlTp String
     * @param DS_CONTR_PRC_EFFTMsg
     */
    public static void deletePrcEffAndSchdInfoByPe(S21ApiMessageMap msgMap, String dsContrDtlTp, DS_CONTR_PRC_EFFTMsg delPe) {

        String glblCmpyCd = delPe.glblCmpyCd.getValue();
        String usgChrgFlg = delPe.usgChrgFlg.getValue();
        BigDecimal dsContrPrcEffPk = delPe.dsContrPrcEffPk.getValue();
        List<Map<String, BigDecimal>> deleteBllgSchdList = NSZC0470Query.getInstance().getBllgSchdForDeleteByPePk(glblCmpyCd, dsContrPrcEffPk);
        List<BigDecimal> schdSmryPkList = getSchdSmryPkList(deleteBllgSchdList);
        List<BigDecimal> schdPkList = getSchdPkList(deleteBllgSchdList);

        for (BigDecimal schdPk : schdPkList) {
            deleteSvcContrBllgInfo(msgMap, glblCmpyCd, schdPk);
        }
        NSZC0470Query.getInstance().removeSchd(glblCmpyCd, schdPkList);
        // START 2017/10/03 E.Kameishi [QC18636, ADD]
        NSZC0470Query.getInstance().removeSchdTestMtrSmry(glblCmpyCd, schdPkList);
        // END 2017/10/03 E.Kameishi [QC18636, ADD]
        if (FLG_ON_Y.equals(usgChrgFlg) && DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTp)) {
            for (BigDecimal prntSchdPk : schdPkList) {

                List<BigDecimal> prntSchdPkList = NSZC0470Query.getInstance().getDeleteChildSchdList(glblCmpyCd, prntSchdPk);
                // START 2017/10/30 K.Kitachi [QC#21449, ADD]
                NSZC047001CommonLogic.deleteSvcContrBllgInfo(msgMap, glblCmpyCd, prntSchdPkList);
                // END 2017/10/30 K.Kitachi [QC#21449, ADD]
                NSZC0470Query.getInstance().removeSchd(glblCmpyCd, prntSchdPkList);
            }
        }

        for (BigDecimal schdSmryPk : schdSmryPkList) {
            NSZC0470Query.getInstance().removeSchdSmry(glblCmpyCd, schdSmryPk);

            if (FLG_ON_Y.equals(usgChrgFlg)) {
                List<BigDecimal> schdMtrPkList = NSZC0470Query.getInstance().getDsContrBllgSchdMtrForCancel(glblCmpyCd, schdSmryPk);
                NSZC0470Query.getInstance().removeSchdMtr(glblCmpyCd, schdMtrPkList);
            }
        }

        NSZC0470Query.getInstance().removePrcEff(glblCmpyCd, dsContrPrcEffPk);

        if (FLG_ON_Y.equals(usgChrgFlg)) {
            List<BigDecimal> prcEffMtrPkList = NSZC0470Query.getInstance().getDeletePrcEffMtrList(glblCmpyCd, dsContrPrcEffPk);
            NSZC0470Query.getInstance().removePrcEffMtr(glblCmpyCd, prcEffMtrPkList);
        }
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param dsContrDtlTp String
     * @param DS_CONTR_PRC_EFFTMsg
     * @param String
     */
    public static void deletePrcEffAndSchdInfoByPeAndDt(S21ApiMessageMap msgMap, String dsContrDtlTp, DS_CONTR_PRC_EFFTMsg delPe, String fixDt) {

        String glblCmpyCd = delPe.glblCmpyCd.getValue();
        String usgChrgFlg = delPe.usgChrgFlg.getValue();
        BigDecimal dsContrPrcEffPk = delPe.dsContrPrcEffPk.getValue();
        List<Map<String, BigDecimal>> deleteBllgSchdList = NSZC0470Query.getInstance().getBllgSchdForDeleteByPePkAndDt(glblCmpyCd, dsContrPrcEffPk, fixDt);
        List<BigDecimal> schdPkList = getSchdPkList(deleteBllgSchdList);

        for (BigDecimal schdPk : schdPkList) {
            deleteSvcContrBllgInfo(msgMap, glblCmpyCd, schdPk);
        }
        NSZC0470Query.getInstance().removeSchd(glblCmpyCd, schdPkList);
        // START 2017/10/03 E.Kameishi [QC18636, ADD]
        NSZC0470Query.getInstance().removeSchdTestMtrSmry(glblCmpyCd, schdPkList);
        // END 2017/10/03 E.Kameishi [QC18636, ADD]
        if (FLG_ON_Y.equals(usgChrgFlg) && DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTp)) {
            for (BigDecimal prntSchdPk : schdPkList) {

                List<BigDecimal> prntSchdPkList = NSZC0470Query.getInstance().getDeleteChildSchdList(glblCmpyCd, prntSchdPk);
                // START 2017/10/30 K.Kitachi [QC#21449, ADD]
                NSZC047001CommonLogic.deleteSvcContrBllgInfo(msgMap, glblCmpyCd, prntSchdPkList);
                // END 2017/10/30 K.Kitachi [QC#21449, ADD]
                NSZC0470Query.getInstance().removeSchd(glblCmpyCd, prntSchdPkList);
            }
        }
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param DS_CONTR_PRC_EFFTMsg
     * @param String
     */
    public static void deleteSchdBySchd(S21ApiMessageMap msgMap, DS_CONTR_BLLG_SCHDTMsg schd, String fleetFlg) {

        String glblCmpyCd = schd.glblCmpyCd.getValue();
        String usgChrgFlg = schd.usgChrgFlg.getValue();
        BigDecimal schdPk = schd.dsContrBllgSchdPk.getValue();
        
        NSZC047001CommonLogic.deleteSvcContrBllgInfo(msgMap, glblCmpyCd, schd.dsContrBllgSchdPk.getValue());
        NSZC0470Query.getInstance().removeSchd(glblCmpyCd, schdPk);
        // START 2017/10/03 E.Kameishi [QC18636, ADD]
        NSZC0470Query.getInstance().removeSchdTestMtrSmry(glblCmpyCd, schdPk);
        // END 2017/10/03 E.Kameishi [QC18636, ADD]
        if (FLG_ON_Y.equals(usgChrgFlg) && FLG_ON_Y.equals(fleetFlg)) {
            List<BigDecimal> prntSchdPkList = NSZC0470Query.getInstance().getDeleteChildSchdList(glblCmpyCd, schdPk);
            // START 2017/10/30 K.Kitachi [QC#21449, ADD]
            NSZC047001CommonLogic.deleteSvcContrBllgInfo(msgMap, glblCmpyCd, prntSchdPkList);
            // END 2017/10/30 K.Kitachi [QC#21449, ADD]
            NSZC0470Query.getInstance().removeSchd(glblCmpyCd, prntSchdPkList);
            // START 2017/10/03 E.Kameishi [QC18636, ADD]
            NSZC0470Query.getInstance().removeSchdTestMtrSmry(glblCmpyCd, prntSchdPkList);
            // END 2017/10/03 E.Kameishi [QC18636, ADD]
        }
    }
    // Add End   06/29/2016 <QC#7428,QC#7429>

    // Add Start 10/11/2016 <QC#14400>
    /**
     * @param msgMap S21ApiMessageMap
     * @param glblCmpyCd String
     * @param dsContrBllgSchdPk BigDecimal
     * @return boolean
     */
    public static boolean resetSvcContrBllgInfoAgg(S21ApiMessageMap msgMap, String glblCmpyCd, BigDecimal dsContrBllgSchdPk) {

        List<BigDecimal> svcContrBllgGrpSqList = NSZC0470Query.getInstance().getSvcContrBllgGrpSq(glblCmpyCd, dsContrBllgSchdPk);

        for (BigDecimal svcContrBllgGrpSq : svcContrBllgGrpSqList) {
            List<BigDecimal> aggrUsgRecalPkList = NSZC0470Query.getInstance().getAggrUsgRecalPk(glblCmpyCd, svcContrBllgGrpSq);

            for (BigDecimal aggrUsgRecalPk : aggrUsgRecalPkList) {
                List<BigDecimal> aggrUsgRecalDtlPkList = NSZC0470Query.getInstance().getAggrUsgRecalDtlPk(glblCmpyCd, aggrUsgRecalPk);

                for (BigDecimal aggUsgRecalDtlPk : aggrUsgRecalDtlPkList) {
                    List<BigDecimal> aggrUsgRecalXsMtrList = NSZC0470Query.getInstance().getAggrUsgRecalXsMtrPk(glblCmpyCd, aggUsgRecalDtlPk);
                    NSZC0470Query.getInstance().removeAggrUsgRecalXsMtr(glblCmpyCd, aggUsgRecalDtlPk, aggrUsgRecalXsMtrList);
                }
                NSZC0470Query.getInstance().removeAggrUsgRecalDtl(glblCmpyCd, aggrUsgRecalPk, aggrUsgRecalDtlPkList);
            }
            NSZC0470Query.getInstance().removeAggrUsgRecal(glblCmpyCd, aggrUsgRecalPkList);
        }

        List<BigDecimal> childSchdPkList = NSZC0470Query.getInstance().getDeleteChildSchdList(glblCmpyCd, dsContrBllgSchdPk);
        for (BigDecimal childSchdPk : childSchdPkList) {
            resetSvcContrBllgInfo(msgMap, glblCmpyCd, childSchdPk, PREFIX_DOC_ID_AGG);
        }
        resetSvcContrBllgInfo(msgMap, glblCmpyCd, dsContrBllgSchdPk, null);
        return true;
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param glblCmpyCd String
     * @param dsContrBllgSchdPk BigDecimal
     * @return boolean
     */
    public static boolean resetSvcContrBllgInfoFlt(S21ApiMessageMap msgMap, String glblCmpyCd, BigDecimal dsContrBllgSchdPk) {

        List<BigDecimal> fleetReadRollUpPkList = NSZC0470Query.getInstance().getFleetReadRollUpPk(glblCmpyCd, dsContrBllgSchdPk);
        NSZC0470Query.getInstance().removeFleetReadRollUp(glblCmpyCd, fleetReadRollUpPkList);

        List<BigDecimal> fleetReadRollUpDtlPkList = NSZC0470Query.getInstance().getFleetReadRollUpDtlPk(glblCmpyCd, fleetReadRollUpPkList);
        NSZC0470Query.getInstance().removeFleetReadRollUpDtl(glblCmpyCd, fleetReadRollUpDtlPkList);

        List<BigDecimal> childSchdPkList = NSZC0470Query.getInstance().getDeleteChildSchdList(glblCmpyCd, dsContrBllgSchdPk);
        for (BigDecimal childSchdPk : childSchdPkList) {
            resetSvcContrBllgInfo(msgMap, glblCmpyCd, childSchdPk, null);
        }
        resetSvcContrBllgInfo(msgMap, glblCmpyCd, dsContrBllgSchdPk, PREFIX_DOC_ID_FLT);

        return true;
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param glblCmpyCd String
     * @param dsContrBllgSchdPk BigDecimal
     * @param dsContrCatgCdString
     * @return boolean
     */
    public static boolean resetSvcContrBllgInfo(S21ApiMessageMap msgMap, String glblCmpyCd, BigDecimal dsContrBllgSchdPk, String prefixDocId) {

        List<BigDecimal> svcContrBllgPkList = NSZC0470Query.getInstance().getSvcContrBllgPk(glblCmpyCd, dsContrBllgSchdPk);

        for (BigDecimal svcContrBllgPk : svcContrBllgPkList) {
            SVC_CONTR_BLLGTMsg svcContrBllgTMsg = new SVC_CONTR_BLLGTMsg();
            setValue(svcContrBllgTMsg.glblCmpyCd, glblCmpyCd);
            setValue(svcContrBllgTMsg.svcContrBllgPk, svcContrBllgPk);
            svcContrBllgTMsg = (SVC_CONTR_BLLGTMsg) S21ApiTBLAccessor.findByKey(svcContrBllgTMsg);
            if (svcContrBllgTMsg == null) {
                return true;
            }
            if (hasValue(prefixDocId)) {
                // START 2018/07/03 K.Kim [QC#26726,MOD]
                // executeWorkFlowCancel(msgMap, svcContrBllgTMsg, prefixDocId);
                // Cancel Preview Billing WF
                if (!executeWorkFlowCancel(msgMap, svcContrBllgTMsg, prefixDocId, WF_PROCESS_NM)) {
                    return false;
                }
                // Cancel Negative Billing WF
                if (!executeWorkFlowCancel(msgMap, svcContrBllgTMsg, prefixDocId, WF_PROCESS_NM_NEGA_BLLG)) {
                    return false;
                }
                // END 2018/07/03 K.Kim [QC#26726,MOD]
            }
            setValue(svcContrBllgTMsg.bllgApvlReqFlg, FLG_OFF_N);
            setValue(svcContrBllgTMsg.bllgApvlCpltFlg, FLG_ON_Y);
            setValue(svcContrBllgTMsg.fleetCalcFlg, FLG_OFF_N);
            setValue(svcContrBllgTMsg.bllgCpltStsCd, BLLG_CPLT_STS.CREATED);
            S21ApiTBLAccessor.update(svcContrBllgTMsg);
            // START 2018/07/03 K.Kim [QC#26726,ADD]
            if (existCreditRebill(glblCmpyCd, svcContrBllgTMsg.dsContrPk.getValue())) {
                continue;
            }
            List<SVC_CONTR_BLLGTMsg> svcContrBllgTMsgList = new ArrayList<SVC_CONTR_BLLGTMsg>();
            svcContrBllgTMsgList.add(svcContrBllgTMsg);
            // START 2018/08/30 [QC#27967,MOD]
            // updateBllgHold(glblCmpyCd, svcContrBllgTMsgList);
            updateBllgHold(glblCmpyCd, svcContrBllgTMsgList, true, true);
            // END 2018/08/30 [QC#27967,MOD]
            // END 2018/07/03 K.Kim [QC#26726,ADD]
            deleteMtrBllg(msgMap, glblCmpyCd, svcContrBllgTMsg.dsContrBllgSchdPk.getValue());
            DS_CONTR_BLLG_SCHDTMsg dsContrBllgSchdTMsg = new DS_CONTR_BLLG_SCHDTMsg();
            setValue(dsContrBllgSchdTMsg.glblCmpyCd, glblCmpyCd);
            setValue(dsContrBllgSchdTMsg.dsContrBllgSchdPk, svcContrBllgTMsg.dsContrBllgSchdPk);
            dsContrBllgSchdTMsg = (DS_CONTR_BLLG_SCHDTMsg) S21ApiTBLAccessor.findByKey(dsContrBllgSchdTMsg);
            if (dsContrBllgSchdTMsg == null) {
                return true;
            }
            setValue(dsContrBllgSchdTMsg.dsBllgSchdStsCd, DS_BLLG_SCHD_STS.OPEN);
            setValue(dsContrBllgSchdTMsg.bllgCalcFlg, FLG_OFF_N);
            dsContrBllgSchdTMsg.readMtrCnt.clear();
            dsContrBllgSchdTMsg.bllgMtrCnt.clear();
            dsContrBllgSchdTMsg.mtrChrgDealAmt.clear();
            dsContrBllgSchdTMsg.mtrChrgFuncAmt.clear();
            dsContrBllgSchdTMsg.slsTaxRate.clear();
            dsContrBllgSchdTMsg.dealTaxAmt.clear();
            dsContrBllgSchdTMsg.funcTaxAmt.clear();
            S21ApiTBLAccessor.update(dsContrBllgSchdTMsg);
        }

        // START 2017/09/26 T.Kanasaka [QC#21414,ADD]
        List<Map<String, Object>> calcMtrSchdRelnPkList = NSZC0470Query.getInstance().getCalcMtrSchdRelnPk(glblCmpyCd, dsContrBllgSchdPk);
        NSZC0470Query.getInstance().removeCalcMtrSchdReln(glblCmpyCd, calcMtrSchdRelnPkList);
        // END 2017/09/26 T.Kanasaka [QC#21414,ADD]

        return true;
    }

    private static void deleteMtrBllg(S21ApiMessageMap msgMap, String glblCmpyCd, BigDecimal dsContrBllgSchdPk) {
        List<BigDecimal> svcContrBllgPkList = NSZC0470Query.getInstance().getSvcContrBllgPk(glblCmpyCd, dsContrBllgSchdPk);

        for (BigDecimal svcContrBllgPk : svcContrBllgPkList) {

            List<BigDecimal> svcContrBllgAllocPkList = NSZC0470Query.getInstance().getSvcContrBllgAllocPk(glblCmpyCd, svcContrBllgPk);
            NSZC0470Query.getInstance().removeSvcContrBllgAlloc(glblCmpyCd, svcContrBllgAllocPkList);

            List<BigDecimal> svcContrMtrBllgPkList = NSZC0470Query.getInstance().getSvcContrMtrBllgPk(glblCmpyCd, svcContrBllgPk);
            NSZC0470Query.getInstance().removeSvcContrMtrBllg(glblCmpyCd, svcContrMtrBllgPkList);

            List<BigDecimal> svcContrXsMtrBllgPkList = NSZC0470Query.getInstance().getSvcContrXsMtrBllgPk(glblCmpyCd, svcContrBllgPk);
            NSZC0470Query.getInstance().removeSvcContrXsMtrBllg(glblCmpyCd, svcContrXsMtrBllgPkList);

            deleteSvcContrAddlChrgInfo(msgMap, glblCmpyCd, svcContrBllgPk);
        }
    }
    // START 2018/07/03 K.Kim [QC#26726,MOD]
    // private static boolean executeWorkFlowCancel(S21ApiMessageMap msgMap, SVC_CONTR_BLLGTMsg svcContrBllgTMsg, String prefixDocId) {
    private static boolean executeWorkFlowCancel(S21ApiMessageMap msgMap, SVC_CONTR_BLLGTMsg svcContrBllgTMsg, String prefixDocId, String processNm) {
    // END 2018/07/03 K.Kim [QC#26726,MOD]

        boolean resExecWF = true;
        if (!hasValue(prefixDocId)) {
            return resExecWF;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(prefixDocId);
        if (PREFIX_DOC_ID_REG.equals(prefixDocId) || PREFIX_DOC_ID_AGG.equals(prefixDocId)) {
            sb.append(svcContrBllgTMsg.svcMachMstrPk.getValue().toPlainString());
        } else {
            sb.append(svcContrBllgTMsg.dsContrPk.getValue().toPlainString());
        }
        sb.append(svcContrBllgTMsg.billToCustCd.getValue());
        sb.append(svcContrBllgTMsg.svcContrBllgThruDt.getValue());
        String documentId = sb.toString();

        // START 2018/07/03 K.Kim [QC#26726,MOD]
        // resExecWF = cancelWF(msgMap, documentId);
        resExecWF = cancelWF(msgMap, documentId, processNm);
        // END 2018/07/03 K.Kim [QC#26726,MOD]

        // START 2019/09/02 K.Kitachi [QC#52695, ADD]
        if (resExecWF) {
            sb.append(svcContrBllgTMsg.dsContrDtlPk.getValue().toPlainString());
            documentId = sb.toString();
            resExecWF = cancelWF(msgMap, documentId, processNm);
        }
        // END 2019/09/02 K.Kitachi [QC#52695, ADD]

        return resExecWF;
    }

    // START 2018/07/03 K.Kim [QC#26726,MOD]
    // private static boolean cancelWF(S21ApiMessageMap msgMap, String documentId) {
    private static boolean cancelWF(S21ApiMessageMap msgMap, String documentId, String processNm) {
    // END 2018/07/03 K.Kim [QC#26726,MOD]

        boolean resCancelWF = false;
        S21NwfContextFactory factory = new S21NwfUtilContextFactory();
        S21NwfContext context = null;
        List<S21NwfProcess> processes = null;

        try {
            
            context = factory.getContex();
            // START 2018/07/03 K.Kim [QC#26726,MOD]
            // processes = context.getProcess(WF_PROCESS_NM, documentId);
            processes = context.getProcess(processNm, documentId);
            // END 2018/07/03 K.Kim [QC#26726,MOD]

            for (S21NwfProcess nwfProcess : processes) {
                if (nwfProcess.isActive()) {
                    nwfProcess.getToken().cancel();
                }
            }
            resCancelWF = true;
        } catch (S21NwfSystemException e) {
            msgMap.addXxMsgId(NSZM0866E);
        } catch (S21NwfBizException e) {
            msgMap.addXxMsgId(NSZM0866E);
        } catch (S21NwfException e) {
            msgMap.addXxMsgId(NSZM0866E);
        }

        return resCancelWF;
    }
    // Add End   10/11/2016 <QC#14400>

    // START 2017/06/30 K.Kitachi [QC#18288, ADD]
    /**
     * @param glblCmpyCd String
     * @param slsDt String
     * @param dsContrDtlPk BigDecimal
     */
    public static void updateUpliftInfo(String glblCmpyCd, String slsDt, BigDecimal dsContrDtlPk) {
        updateUpliftInfoForBase(glblCmpyCd, slsDt, dsContrDtlPk);
        updateUpliftInfoForUsg(glblCmpyCd, slsDt, dsContrDtlPk);
    }

    private static void updateUpliftInfoForBase(String glblCmpyCd, String slsDt, BigDecimal dsContrDtlPk) {
        List<BigDecimal> prcEffPkListForCurBase = NSZC0470Query.getInstance().getPrcEffPkForCur(glblCmpyCd, slsDt, dsContrDtlPk, FLG_ON_Y, FLG_OFF_N);
        if (prcEffPkListForCurBase.size() == 0) {
            return;
        }
        DS_CONTR_PRC_EFFTMsg prcEffTMsgForBase = NSZC0470Query.getInstance().getDsContrPrcEffTMsg(glblCmpyCd, prcEffPkListForCurBase.get(0));
        if (prcEffTMsgForBase == null || !DS_CONTR_DTL_STS.ACTIVE.equals(prcEffTMsgForBase.dsContrPrcEffStsCd.getValue())) {
            return;
        }
        DS_CONTR_DTLTMsg dsContrDtlTMsg = new DS_CONTR_DTLTMsg();
        setValue(dsContrDtlTMsg.glblCmpyCd, glblCmpyCd);
        setValue(dsContrDtlTMsg.dsContrDtlPk, dsContrDtlPk);
        dsContrDtlTMsg = (DS_CONTR_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(dsContrDtlTMsg);
        if (dsContrDtlTMsg.basePrcDealAmt.getValue().compareTo(prcEffTMsgForBase.basePrcDealAmt.getValue()) == 0) {
            return;
        }
        setValue(dsContrDtlTMsg.basePrcDealAmt, prcEffTMsgForBase.basePrcDealAmt);
        S21ApiTBLAccessor.update(dsContrDtlTMsg);
    }

    private static void updateUpliftInfoForUsg(String glblCmpyCd, String slsDt, BigDecimal dsContrDtlPk) {
        List<BigDecimal> prcEffPkListForCurUsg = NSZC0470Query.getInstance().getPrcEffPkForCur(glblCmpyCd, slsDt, dsContrDtlPk, FLG_OFF_N, FLG_ON_Y);
        for (BigDecimal prcEffPkForCurUsg : prcEffPkListForCurUsg) {
            DS_CONTR_PRC_EFFTMsg prcEffTMsgForUsg = NSZC0470Query.getInstance().getDsContrPrcEffTMsg(glblCmpyCd, prcEffPkForCurUsg);
            if (prcEffTMsgForUsg == null || !DS_CONTR_DTL_STS.ACTIVE.equals(prcEffTMsgForUsg.dsContrPrcEffStsCd.getValue())) {
                continue;
            }
            DS_CONTR_PRC_EFF_MTRTMsgArray prcEffMtrAry = NSZC0470Query.getInstance().getPeMtrArray(glblCmpyCd, prcEffTMsgForUsg.dsContrPrcEffPk.getValue(), prcEffTMsgForUsg.dsContrBllgMtrPk.getValue());
            CONTR_XS_COPYTMsgArray xsCopyAry = NSZC0470Query.getInstance().getXsCopyTMsgArray(glblCmpyCd, prcEffTMsgForUsg.dsContrBllgMtrPk.getValue());
            for (int i = 0; i < prcEffMtrAry.getValidCount(); i++) {
                DS_CONTR_PRC_EFF_MTRTMsg prcEffMtr = prcEffMtrAry.no(i);
                for (int j = 0; j < xsCopyAry.getValidCount(); j++) {
                    CONTR_XS_COPYTMsg xsCopy = xsCopyAry.no(j);
                    // mod start 2019/04/11 QC#31149
                    //if (xsCopy.contrXsCopyPk.getValue().compareTo(prcEffMtr.contrXsCopyPk.getValue()) != 0) {
                    if (!hasValue(xsCopy.contrXsCopyPk) || !hasValue(prcEffMtr.contrXsCopyPk) || xsCopy.contrXsCopyPk.getValue().compareTo(prcEffMtr.contrXsCopyPk.getValue()) != 0) {
                    // mod end 2019/04/11 QC#31149
                        continue;
                    }
                    if (xsCopy.xsMtrCopyQty.getValue().compareTo(prcEffMtr.xsMtrCopyQty.getValue()) == 0 && xsCopy.xsMtrAmtRate.getValue().compareTo(prcEffMtr.xsMtrAmtRate.getValue()) == 0) {
                        continue;
                    }
                    CONTR_XS_COPYTMsg xsCopyTMsg = new CONTR_XS_COPYTMsg();
                    setValue(xsCopyTMsg.glblCmpyCd, glblCmpyCd);
                    setValue(xsCopyTMsg.contrXsCopyPk, prcEffMtr.contrXsCopyPk);
                    xsCopyTMsg = (CONTR_XS_COPYTMsg) S21ApiTBLAccessor.findByKeyForUpdate(xsCopyTMsg);
                    setValue(xsCopyTMsg.xsMtrCopyQty, prcEffMtr.xsMtrCopyQty);
                    setValue(xsCopyTMsg.xsMtrAmtRate, prcEffMtr.xsMtrAmtRate);
                    S21ApiTBLAccessor.update(xsCopyTMsg);
                    break;
                }
            }
        }
    }
    // END 2017/06/30 K.Kitachi [QC#18288, ADD]

    //START 2017/08/22 K.Kasai [QC#18639,ADD]
    public static boolean adjustXsCopyQty(String glblCmpyCd, BigDecimal dsContrBllgMtrPk, String usgChrgFlg) {

        List<Map<String, Object>> dsContrBllgSchdMtrList = null;
        DS_CONTR_BLLG_SCHD_MTRTMsg dsContrBllgSchdMtrTMsg = new DS_CONTR_BLLG_SCHD_MTRTMsg();
        BigDecimal dsContrPrcEffPk = null;
        BigDecimal dsContrBllgSchdMtrPk = null;
        List<Map<String, BigDecimal>> sumAllowAdjInfo = null;
        BigDecimal sumXsMtrAdjCopyQty = null;
        BigDecimal xsMtrCopyQty = null;
        //START 2017/09/21 K.Kasai [QC#21060,MOD]
        List<Map<String, BigDecimal>> tierList = null;
        //END 2017/09/21 K.Kasai [QC#21060,MOD]
        int idx = 0;
        boolean isAdjustTargetHdr = true;
        boolean isAdjustTargetLine = true;
        BigDecimal xsMtrAdjCopyQty = null;

        // execute only usage charge flag = 'Y'
        if (!hasValue(usgChrgFlg) || !ZYPConstant.FLG_ON_Y.equals(usgChrgFlg)) {
            return true;
        }
        // get DS_CONTR_PRC_EFF_PK List in DS_CONTR_BLLG_SCHD_SMRY using DS_CONTR_BLLG_MTR_PK
        List<Map<String, BigDecimal>> prcEffPkList = NSZC0470Query.getInstance().getPrcEffPkListForAllowAdj(glblCmpyCd, dsContrBllgMtrPk);

        // loop per DS_CONTR_PRC_EFF_PK in DS_CONTR_BLLG_SCHD_SMRY
        for (Map<String, BigDecimal> prcEffPkMap: prcEffPkList) {
            dsContrPrcEffPk = prcEffPkMap.get("DS_CONTR_PRC_EFF_PK");

            // check Adjust target
            isAdjustTargetHdr = isAdjustTarget(glblCmpyCd, dsContrPrcEffPk);

            //START 2017/09/21 K.Kasai [QC#21060,MOD]
            // get Tier List
            tierList = NSZC0470Query.getInstance().getTierCnt(glblCmpyCd, dsContrPrcEffPk);

            // loop per Tier
            for (Map<String, BigDecimal> tierMap : tierList) {

                BigDecimal tierNum = tierMap.get("TIER_NUM");
                // calculate Adjust Copy Qty
                // get DS_CONTR_PRC_EFF_MTR.XS_MTR_COPY_QTY, sum of XS_MTR_COPY_QTY, PER_SCHD_NUM per Tier
                if (isAdjustTargetHdr) {
                    sumAllowAdjInfo = NSZC0470Query.getInstance().getSumAllowAdjInfo(glblCmpyCd, dsContrPrcEffPk, tierNum);
                }

                // get DS_CONTR_BLLG_SCHD_SMRY_MTR List
                dsContrBllgSchdMtrList = NSZC0470Query.getInstance().getDsContrBllgSchdMtrForAllowAdj(glblCmpyCd, dsContrPrcEffPk, BigDecimal.ONE);

                // loop per DS_CONTR_BLLG_SCHD_MTR
                for (Map<String, Object> dsContrBllgSchdMtrPkMap: dsContrBllgSchdMtrList) {
                    isAdjustTargetLine = true;
                    dsContrBllgSchdMtrPk = (BigDecimal) dsContrBllgSchdMtrPkMap.get("DS_CONTR_BLLG_SCHD_MTR_PK");

                    // get DS_CONTR_BLLG_SCHD_MTR Info for Update
                    dsContrBllgSchdMtrTMsg = getDsContrBllgSchdMtrForAllowAdjUpdate(glblCmpyCd, dsContrBllgSchdMtrPk);
                    //START 2017/08/29 K.Kasai [QC#20829,ADD]
                    if (hasValue(dsContrBllgSchdMtrTMsg.xsMtrCopyQty) && dsContrBllgSchdMtrTMsg.xsMtrCopyQty.getValue().compareTo(BigDecimal.ZERO) == 0) {
                        isAdjustTargetLine = false;
                    }
                    //END 2017/08/29 K.Kasai [QC#20829,ADD]

                    // get XS_MTR_COPY_QTY
                    //START 2017/08/29 K.Kasai [QC#20829,MOD]
                    if (isAdjustTargetHdr && isAdjustTargetLine) {
                        if (sumAllowAdjInfo != null && sumAllowAdjInfo.size() > 0) {
                    //END 2017/08/29 K.Kasai [QC#20829,MOD]
                            xsMtrCopyQty = sumAllowAdjInfo.get(idx).get("XS_MTR_COPY_QTY");
                        }
                        // if DS_CONTR_BLLG_SCHD_MTR.XS_MTR_COPY_QTY is equal to DS_CONTR_PRC_EFF_MTR.XS_MTR_COPY_QTY, DS_CONTR_BLLG_SCHD_MTR record is out of adjust.
                        //START 2017/08/29 K.Kasai [QC#20829,MOD]
                        if (sumAllowAdjInfo == null || sumAllowAdjInfo.size() == 0 || dsContrBllgSchdMtrTMsg.xsMtrCopyQty.getValue().compareTo(xsMtrCopyQty) == 0) {
                        //END 2017/08/29 K.Kasai [QC#20829,MOD]
                            isAdjustTargetLine = false;
                        }
                    }

                    // do Adjust process
                    if (isAdjustTargetHdr && isAdjustTargetLine) {
                        //START 2017/08/29 K.Kasai [QC#20829,MOD]
                        //START 2017/09/13 K.Kasai [QC#21060,MOD]
                        //if (sumAllowAdjInfo != null && sumAllowAdjInfo.size() > 0) {
                        if (sumXsMtrAdjCopyQty == null) {
                        //END 2017/09/13 K.Kasai [QC#21060,MOD]
                        //START 2017/08/29 K.Kasai [QC#20829,MOD]
                            // get sum of XS_MTR_COPY_QTY, PER_SCHD_NUM
                            //START 2017/09/13 K.Kasai [QC#21060,MOD]
//                            sumXsMtrAdjCopyQty = sumAllowAdjInfo.get(idx).get("XS_MTR_COPY_QTY");
                            sumXsMtrAdjCopyQty = sumAllowAdjInfo.get(idx).get("SUM_XS_MTR_COPY_QTY");
                            //END 2017/09/13 K.Kasai [QC#21060,MOD]

                            // calculate Allowance Adjust Qty
                            xsMtrAdjCopyQty = calcAllowAdjQty(sumAllowAdjInfo.get(idx), glblCmpyCd, dsContrBllgSchdMtrTMsg.dsContrBllgSchdSmryPk.getValue());

                            setValue(dsContrBllgSchdMtrTMsg.xsMtrAdjCopyQty, xsMtrAdjCopyQty);
                        } else {
                            setValue(dsContrBllgSchdMtrTMsg.xsMtrAdjCopyQty, xsMtrCopyQty.subtract(sumXsMtrAdjCopyQty).subtract(xsMtrAdjCopyQty));
                        }
                        setValue(dsContrBllgSchdMtrTMsg.xsMtrCopyQty, dsContrBllgSchdMtrTMsg.xsMtrCopyQty.getValue().add(dsContrBllgSchdMtrTMsg.xsMtrAdjCopyQty.getValue()));
                    } else {
                        setValue(dsContrBllgSchdMtrTMsg.xsMtrAdjCopyQty, BigDecimal.ZERO);
                    }
                    S21ApiTBLAccessor.update(dsContrBllgSchdMtrTMsg);
    
                    // update DS_CONTR_BLLG_SCHD_MTR
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsContrBllgSchdMtrTMsg.getReturnCode())) {
                        return false;
                    }
                }
                //START 2017/09/13 K.Kasai [QC#21060,ADD]
                sumXsMtrAdjCopyQty = null;
                xsMtrAdjCopyQty = null;
                //END 2017/09/13 K.Kasai [QC#21060,ADD]
            }
            
            //END 2017/09/21 K.Kasai [QC#21060,MOD]
        }
        return true;
    }

    private static boolean isAdjustTarget(String glblCmpyCd, BigDecimal dsContrPrcEffPk) {

        boolean isAdjustTarget = true;
        // check exist Billing Schedule with invoice
        if (existBllgSchdWithInv(glblCmpyCd, dsContrPrcEffPk)) {
            isAdjustTarget = false;
        // check divide 3 in Top Schedule
        } else if (!divideThreeInTopSchd(glblCmpyCd, dsContrPrcEffPk)) {
            isAdjustTarget = false;
        // check relation between duration and Frequency
        } else if (!beDivisibleDurnWithFreq(glblCmpyCd, dsContrPrcEffPk)) {
            isAdjustTarget = false;
        }
        return isAdjustTarget;
    }

    private static boolean existBllgSchdWithInv(String glblCmpyCd, BigDecimal dsContrPrcEffPk) {
        DS_CONTR_BLLG_SCHDTMsg inMsg = new DS_CONTR_BLLG_SCHDTMsg();
        inMsg.setSQLID("007");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrPrcEffPk01", dsContrPrcEffPk);
        inMsg.setConditionValue("invFlg01", FLG_ON_Y);
        int cnt = S21ApiTBLAccessor.count(inMsg);
        if (cnt > 0) {
            return true;
        }
        return false;
    }

    private static boolean divideThreeInTopSchd(String glblCmpyCd, BigDecimal dsContrPrcEffPk) {

        Integer cntDsContrBllgSchdSmry = cntDsContrBllgSchdSmryForAllowAdj (glblCmpyCd, dsContrPrcEffPk);
        if (cntDsContrBllgSchdSmry == 3) {
            return true;
        }
        return false;
    }

    private static DS_CONTR_PRC_EFFTMsg getDsContrPrcEffByPk(String glblCmpyCd, BigDecimal dsContrPrcEffPk) {

        // get DS_CONTR_PRC_EFF Info
        DS_CONTR_PRC_EFFTMsg peTMsg = new DS_CONTR_PRC_EFFTMsg();
        setValue(peTMsg.glblCmpyCd, glblCmpyCd);
        setValue(peTMsg.dsContrPrcEffPk, dsContrPrcEffPk);
        return (DS_CONTR_PRC_EFFTMsg) S21ApiTBLAccessor.findByKey(peTMsg);
    }

    private static Integer cntDsContrBllgSchdSmryForAllowAdj(String glblCmpyCd, BigDecimal dsContrPrcEffPk) {

        // get DS_CONTR_BLLG_SCHD_SMRY count
        DS_CONTR_BLLG_SCHD_SMRYTMsg inMsg = new DS_CONTR_BLLG_SCHD_SMRYTMsg();

        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrPrcEffPk01", dsContrPrcEffPk);
        return (Integer) S21ApiTBLAccessor.count(inMsg);
    }

    private static boolean beDivisibleDurnWithFreq(String glblCmpyCd, BigDecimal dsContrPrcEffPk) {

        // get DS_CONTR_PRC_EFF Info
        DS_CONTR_PRC_EFFTMsg dsContrPrcEffTMsg = getDsContrPrcEffByPk(glblCmpyCd, dsContrPrcEffPk);

        //calculate duration
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        Date startDt;
        try {
            startDt = df.parse(dsContrPrcEffTMsg.contrPrcEffFromDt.getValue());
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }

        String paramEndDate = dsContrPrcEffTMsg.contrPrcEffThruDt.getValue();
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

        // get BLLG_CYCLE Info
        BLLG_CYCLETMsg bcTMsg = new BLLG_CYCLETMsg();
        setValue(bcTMsg.glblCmpyCd, glblCmpyCd);
        setValue(bcTMsg.bllgCycleCd, dsContrPrcEffTMsg.bllgCycleCd);
        bcTMsg = (BLLG_CYCLETMsg) S21ApiTBLAccessor.findByKey(bcTMsg);

        if (durnCnt.intValue() % bcTMsg.bllgCycleMthAot.getValueInt() == 0) {
            return true;
        }
        return false;
    }

    private static DS_CONTR_BLLG_SCHD_MTRTMsg getDsContrBllgSchdMtrForAllowAdjUpdate(String glblCmpyCd, BigDecimal dsContrBllgSchdMtrPk) {

        // get DS_CONTR_BLLG_SCHD_MTR Info
        DS_CONTR_BLLG_SCHD_MTRTMsg inMsg = new DS_CONTR_BLLG_SCHD_MTRTMsg();

        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsContrBllgSchdMtrPk, dsContrBllgSchdMtrPk);
        return (DS_CONTR_BLLG_SCHD_MTRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
    }

    private static BigDecimal getPerSchdNumForAllowAdj(String glblCmpyCd, BigDecimal dsContrBllgSchdSmryPk) {

        // get DS_CONTR_BLLG_SCHD_SMRY Info
        DS_CONTR_BLLG_SCHD_SMRYTMsg inMsg = new DS_CONTR_BLLG_SCHD_SMRYTMsg();

        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsContrBllgSchdSmryPk, dsContrBllgSchdSmryPk);
        DS_CONTR_BLLG_SCHD_SMRYTMsg outTMsg = (DS_CONTR_BLLG_SCHD_SMRYTMsg) S21ApiTBLAccessor.findByKey(inMsg);
        return outTMsg.perSchdNum.getValue();
    }

    private static BigDecimal calcAllowAdjQty(Map<String, BigDecimal> sumAllowAdjInfoMap, String glblCmpyCd, BigDecimal dsContrBllgSchdSmryPk) {

        // get sum of XS_MTR_COPY_QTY, PER_SCHD_NUM
        BigDecimal xsMtrCopyQty = sumAllowAdjInfoMap.get("XS_MTR_COPY_QTY");
        BigDecimal sumXsMtrCopyQty = sumAllowAdjInfoMap.get("SUM_XS_MTR_COPY_QTY");
        BigDecimal sumPerSchdNum = sumAllowAdjInfoMap.get("SUM_PER_SCHD_NUM");

        // get perSchdNum For calculate allowance adjustment
        BigDecimal perSchdNum = getPerSchdNumForAllowAdj(glblCmpyCd, dsContrBllgSchdSmryPk);
        //START 2017/08/29 K.Kasai [QC#20829,ADD]
        if (sumPerSchdNum.compareTo(perSchdNum) == 0) {
            return BigDecimal.ZERO;
        }
        //END 2017/08/29 K.Kasai [QC#20829,ADD]
        // set Allowance Adjust
        BigDecimal tmpDiffQty = xsMtrCopyQty.subtract(sumXsMtrCopyQty);
        BigDecimal tmpAdjRatio = perSchdNum.divide(sumPerSchdNum, 2, BigDecimal.ROUND_HALF_UP);
        BigDecimal xsMtrAdjCopyQty = tmpDiffQty.multiply(tmpAdjRatio).setScale(0, BigDecimal.ROUND_HALF_UP);
        return xsMtrAdjCopyQty;
    }

    //END 2017/08/22 K.Kasai [QC#18639,ADD]

    // add start 2017/10/02 QC#21567
    /**
     * @param msgMap S21ApiMessageMap
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param baseChrgFlg String
     * @param usgChrgFlg String
     */
    public static void updateNextBllgDt(S21ApiMessageMap msgMap, String glblCmpyCd, BigDecimal dsContrDtlPk, String baseChrgFlg, String usgChrgFlg) {

        //Add Start 2017/12/07 <QC#22935>
        DS_CONTR_DTLTMsg dtlTmsg = NSZC0470Query.getInstance().getDsContrDtlTMsg(glblCmpyCd, dsContrDtlPk);
        if (dtlTmsg == null) {
            return;
        }
        if (DS_CONTR_DTL_TP.FLEET.equals(dtlTmsg.dsContrDtlTpCd.getValue())) {
            return;
        }
        //Add End   2017/12/07 <QC#22935>
        List<Map<String, Object>> bllgSchdList = NSZC0470Query.getInstance().getBllgSchdForNextBllgDtUpd(glblCmpyCd, dsContrDtlPk, baseChrgFlg, usgChrgFlg);
        for (Map<String, Object> bllgSchd : bllgSchdList) {
            DS_CONTR_BLLG_SCHDTMsg inTMsg = new DS_CONTR_BLLG_SCHDTMsg();
            setValue(inTMsg.glblCmpyCd, glblCmpyCd);
            setValue(inTMsg.dsContrBllgSchdPk, (BigDecimal) bllgSchd.get("DS_CONTR_BLLG_SCHD_PK"));
            inTMsg = (DS_CONTR_BLLG_SCHDTMsg) S21ApiTBLAccessor.findByKey(inTMsg);
            if (inTMsg == null) {
                continue;
            }

            setValue(inTMsg.nextBllgDt, (String) bllgSchd.get("PRNT_NEXT_BLLG_DT"));
            S21ApiTBLAccessor.update(inTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                msgMap.addXxMsgIdWithPrm(NSAM0031E, new String[] {"DS_CONTR_BLLG_SCHD" });
            }
            //Add Start 2017/12/07 <QC#22935>
            updateNextBllgDtChild(msgMap, glblCmpyCd, inTMsg.dsContrDtlPk.getValue(), baseChrgFlg, usgChrgFlg);
            //Add End   2017/12/07 <QC#22935>
        }
    }
    // add end 2017/10/02 QC#21567

    //Add Start 2017/12/07 <QC#22935>
    private static void updateNextBllgDtChild(S21ApiMessageMap msgMap, String glblCmpyCd, BigDecimal dsContrDtlPk, String baseChrgFlg, String usgChrgFlg) {

        List<Map<String, Object>> bllgSchdList = NSZC0470Query.getInstance().getBllgSchdForNextBllgDtUpd(glblCmpyCd, dsContrDtlPk, baseChrgFlg, usgChrgFlg);
        for (Map<String, Object> bllgSchd : bllgSchdList) {
            DS_CONTR_BLLG_SCHDTMsg inTMsg = new DS_CONTR_BLLG_SCHDTMsg();
            setValue(inTMsg.glblCmpyCd, glblCmpyCd);
            setValue(inTMsg.dsContrBllgSchdPk, (BigDecimal) bllgSchd.get("DS_CONTR_BLLG_SCHD_PK"));
            inTMsg = (DS_CONTR_BLLG_SCHDTMsg) S21ApiTBLAccessor.findByKey(inTMsg);
            if (inTMsg == null) {
                continue;
            }

            setValue(inTMsg.nextBllgDt, (String) bllgSchd.get("PRNT_NEXT_BLLG_DT"));
            S21ApiTBLAccessor.update(inTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(inTMsg.getReturnCode())) {
                msgMap.addXxMsgIdWithPrm(NSAM0031E, new String[] {"DS_CONTR_BLLG_SCHD" });
            }
        }
    }
    //Add End   2017/12/07 <QC#22935>

    // START 2017/10/30 K.Kitachi [QC#21449, ADD]
    /**
     * @param msgMap S21ApiMessageMap
     * @param glblCmpyCd String
     * @param dsContrBllgSchdPkList List<BigDecimal>
     * @return boolean
     */
    public static boolean deleteSvcContrBllgInfo(S21ApiMessageMap msgMap, String glblCmpyCd, List<BigDecimal> dsContrBllgSchdPkList) {
        for (BigDecimal dsContrBllgSchdPk : dsContrBllgSchdPkList) {
            deleteSvcContrBllgInfo(msgMap, glblCmpyCd, dsContrBllgSchdPk);
        }
        return true;
    }
    // END 2017/10/30 K.Kitachi [QC#21449, ADD]

    // Add Start 2018/05/07 QC#25409
    private static List<SVC_CONTR_BLLGTMsg> getDelSvcContrBllgTMsgList(String glblCmpyCd, List<BigDecimal> svcContrBllgPkList) {
        SVC_CONTR_BLLGTMsg inTMsg;
        List<SVC_CONTR_BLLGTMsg> rtnTMsgList = new ArrayList<SVC_CONTR_BLLGTMsg>();
        for (BigDecimal svcContrBllgPk : svcContrBllgPkList) {
            inTMsg = new SVC_CONTR_BLLGTMsg();
            setValue(inTMsg.glblCmpyCd, glblCmpyCd);
            setValue(inTMsg.svcContrBllgPk, svcContrBllgPk);
            inTMsg = (SVC_CONTR_BLLGTMsg) S21ApiTBLAccessor.findByKey(inTMsg);
            if (inTMsg == null) {
                continue;
            }
            rtnTMsgList.add(inTMsg);
        }
        return rtnTMsgList;
    }
    
    private static void cancelWFBySvcContrBllg(S21ApiMessageMap msgMap, String glblCmpyCd, List<SVC_CONTR_BLLGTMsg> svcContrBllgTMsgList) {
        String prefixDocId;
        String dsContrCatgCd;
        for (SVC_CONTR_BLLGTMsg inTMsg : svcContrBllgTMsgList) {
            prefixDocId = null;
            dsContrCatgCd = inTMsg.dsContrCatgCd.getValue();
            if (DS_CONTR_CATG.REGULAR.equals(dsContrCatgCd)) {
                prefixDocId = PREFIX_DOC_ID_REG;
            } else if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
                prefixDocId = PREFIX_DOC_ID_FLT;
            } else if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd) && hasValue(inTMsg.svcMachMstrPk)) {
                prefixDocId = PREFIX_DOC_ID_AGG;
            }
            // START 2018/07/03 K.Kim [QC#26726,MOD]
            // // Cancel WF
            // if (!executeWorkFlowCancel(msgMap, inTMsg, prefixDocId)) {
            //     return;
            // }
            // Cancel Preview Billing WF
            if (!executeWorkFlowCancel(msgMap, inTMsg, prefixDocId, WF_PROCESS_NM)) {
                return;
            }
            // Cancel Negative Billing WF
            if (!executeWorkFlowCancel(msgMap, inTMsg, prefixDocId, WF_PROCESS_NM_NEGA_BLLG)) {
                return;
            }
            // END 2018/07/03 K.Kim [QC#26726,MOD]
        }
    }

    // START 2018/08/30 [QC#27967,ADD]
    private static boolean checkUnApprovedUsgChrgForDtl(String glblCmpyCd, List<SVC_CONTR_BLLGTMsg> svcContrBllgTMsgList) {

        for (SVC_CONTR_BLLGTMsg inTMsg : svcContrBllgTMsgList) {
            if (DS_CONTR_CATG.REGULAR.equals(inTMsg.dsContrCatgCd.getValue())) {
                if (existUnapprovedUsgChrgForDtl(glblCmpyCd, inTMsg.dsContrDtlPk.getValue())) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkUnApprovedUsgChrg(String glblCmpyCd, List<SVC_CONTR_BLLGTMsg> svcContrBllgTMsgList) {

        if (!svcContrBllgTMsgList.isEmpty()) {
            SVC_CONTR_BLLGTMsg inTMsg = svcContrBllgTMsgList.get(0);
            return existUnapprovedUsgChrg(glblCmpyCd, inTMsg.dsContrPk.getValue());
        }
        return false;
    }
    // END 2018/08/30 [QC#27967,ADD]

    // START 2018/08/30 [QC#27967,MOD]
    private static void updateBllgHold(String glblCmpyCd, List<SVC_CONTR_BLLGTMsg> svcContrBllgTMsgList, boolean existsDtl, boolean existsHdr) {
        BigDecimal dsContrPk;
        BigDecimal dsContrDtlPk;
        String dsContrCatgCd;
        for (SVC_CONTR_BLLGTMsg inTMsg : svcContrBllgTMsgList) {
            // Update Billing Hold
            dsContrPk = inTMsg.dsContrPk.getValue();
            dsContrDtlPk = inTMsg.dsContrDtlPk.getValue();
            dsContrCatgCd = inTMsg.dsContrCatgCd.getValue();
            // START 2018/12/20 K.Kitachi [QC#29647, MOD]
//            if (DS_CONTR_CATG.REGULAR.equals(dsContrCatgCd)) {
//                if (!existUnapprovedUsgChrgForDtl(glblCmpyCd, dsContrDtlPk) && existsDtl) {
//                    NSZC0470Query.getInstance().updateBllgHoldFlgForDsContrDtl(glblCmpyCd, dsContrDtlPk);
//                }
//                if (!existUnapprovedUsgChrg(glblCmpyCd, dsContrPk) && existsHdr) {
//                    NSZC0470Query.getInstance().updateBllgHoldFlgForDsContr(glblCmpyCd, dsContrPk);
//                }
//            } else {
//                if (!existsHdr || existUnapprovedUsgChrg(glblCmpyCd, dsContrPk)) {
//                    continue;
//                }
//                NSZC0470Query.getInstance().updateBllgHoldFlgForDsContr(glblCmpyCd, dsContrPk);
//            }
            BigDecimal aggLinePk = null;
            if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
                aggLinePk = NSZC0470Query.getInstance().getBillingHoldAggLine(glblCmpyCd, dsContrPk);
            }
            if (!existUnapprovedUsgChrgForDtl(glblCmpyCd, dsContrDtlPk) && existsDtl) {
                if (!hasValue(aggLinePk) || aggLinePk.compareTo(dsContrDtlPk) != 0) {
                    // START 2019/07/18 [QC#51706, MOD]
                    updateBllgHoldFlgForDsContrDtlUnderInfo(glblCmpyCd, dsContrPk, dsContrDtlPk);
                    // END   2019/07/18 [QC#51706, MOD]
                }
            }
            if (!existUnapprovedUsgChrg(glblCmpyCd, dsContrPk) && existsHdr) {
                if (hasValue(aggLinePk)) {
                    updateBllgHoldFlgForDsContrDtlUnderInfo(glblCmpyCd, dsContrPk, aggLinePk);
                }
                NSZC0470Query.getInstance().updateBllgHoldFlgForDsContr(glblCmpyCd, dsContrPk);
            }
            // END 2018/12/20 K.Kitachi [QC#29647, MOD]
        }
    }
    // END 2018/08/30 [QC#27967,MOD]

    private static boolean existUnapprovedUsgChrgForDtl(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        List<BigDecimal> svcContrBllgPkList = NSZC0470Query.getInstance().getUnapprovedUsgChrgForDtl(glblCmpyCd, dsContrDtlPk);
        if (svcContrBllgPkList.isEmpty()) {
            return false;
        }
        return true;
    }

    private static boolean existUnapprovedUsgChrg(String glblCmpyCd, BigDecimal dsContrPk) {
        List<BigDecimal> svcContrBllgPkList = NSZC0470Query.getInstance().getUnapprovedUsgChrg(glblCmpyCd, dsContrPk);
        if (svcContrBllgPkList.isEmpty()) {
            return false;
        }
        return true;
    }

    // START 2019/07/18 [QC#51706, MOD]
    // START 2018/12/20 K.Kitachi [QC#29647, ADD]
    private static void updateBllgHoldFlgForDsContrDtlUnderInfo(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk) {
        List<BigDecimal> dsContrPrcEffPkList = NSZC0470Query.getInstance().getBillingHoldPrcEff(glblCmpyCd, dsContrDtlPk);
        for (BigDecimal dsContrPrcEffPk : dsContrPrcEffPkList) {
            NSZC0470Query.getInstance().updateBllgHoldFlgForDsContrPrcEff(glblCmpyCd, dsContrPrcEffPk);
        }
        List<BigDecimal> dsContrBllgMtrPkList = NSZC0470Query.getInstance().getBillingHoldBllgMtr(glblCmpyCd, dsContrDtlPk);
        for (BigDecimal dsContrBllgMtrPk : dsContrBllgMtrPkList) {
            NSZC0470Query.getInstance().updateBllgHoldFlgForDsContrBllgMtr(glblCmpyCd, dsContrPk, dsContrDtlPk, dsContrBllgMtrPk);
        }
        NSZC0470Query.getInstance().updateBllgHoldFlgForDsContrDtl(glblCmpyCd, dsContrDtlPk);
    }
    // END 2018/12/20 K.Kitachi [QC#29647, ADD]
    // END 2019/07/18 [QC#51706, MOD]

    // Add End 2018/05/07 QC#25409
    // Add Start 2018/06/20 QC#26766
    public static void clearSvcContrBllg(S21ApiMessageMap msgMap, DS_CONTR_BLLG_SCHDTMsg schdTMsg) {
        String glblCmpyCd = schdTMsg.glblCmpyCd.getValue();
        BigDecimal dsContrBllgSchdPk = schdTMsg.dsContrBllgSchdPk.getValue();
        List<SVC_CONTR_BLLGTMsg> svcContrBllgTMsgList = new ArrayList<SVC_CONTR_BLLGTMsg>();
        // SVC_CONTR_BLLG
        SVC_CONTR_BLLGTMsgArray bllgArray = NSZC0470Query.getInstance().getSvcContrBllgTMsgArray(glblCmpyCd, dsContrBllgSchdPk);
        for (int i = 0; i < bllgArray.getValidCount(); i++) {
            // SVC_CONTR_BASE_BLLG
            clearSvcContrBaseBllg(bllgArray.no(i));
            // SVC_CONTR_MTR_BLLG
            clearSvcContrMtrBllg(bllgArray.no(i));
            // SVC_CONTR_ADDL_CHRG_BLLG
            clearSvcContrAddlChrgBllg(bllgArray.no(i));

            svcContrBllgTMsgList.add(bllgArray.no(i));
            S21ApiTBLAccessor.logicalRemove(bllgArray.no(i));
        }
        cancelWFBySvcContrBllg(msgMap, glblCmpyCd, svcContrBllgTMsgList);
    }

    private static void clearSvcContrBaseBllg(SVC_CONTR_BLLGTMsg bllgTMsg) {
        SVC_CONTR_BASE_BLLGTMsgArray baseBllgArray = NSZC0470Query.getInstance().getSvcContrBaseBllgTMsgArray(bllgTMsg.glblCmpyCd.getValue(), bllgTMsg.svcContrBllgPk.getValue());
        for (int i = 0; i < baseBllgArray.getValidCount(); i++) {
            clearSvcContrBaseBllgAlloc(baseBllgArray.no(i));
            S21ApiTBLAccessor.logicalRemove(baseBllgArray.no(i));
        }
    }

    private static void clearSvcContrMtrBllg(SVC_CONTR_BLLGTMsg bllgTMsg) {
        SVC_CONTR_MTR_BLLGTMsgArray mtrBllgArray = NSZC0470Query.getInstance().getSvcContrMtrBllgTMsgArray(bllgTMsg.glblCmpyCd.getValue(), bllgTMsg.svcContrBllgPk.getValue());
        for (int i = 0; i < mtrBllgArray.getValidCount(); i++) {
            clearSvcContrXsMtrBllg(mtrBllgArray.no(i));
            clearSvcContrMtrBllgAlloc(mtrBllgArray.no(i));
            S21ApiTBLAccessor.logicalRemove(mtrBllgArray.no(i));
        }
    }

    private static void clearSvcContrXsMtrBllg(SVC_CONTR_MTR_BLLGTMsg mtrbllgTMsg) {
        SVC_CONTR_XS_MTR_BLLGTMsgArray xsMtrBllgArray = NSZC0470Query.getInstance().getSvcContrXsMtrBllgTMsgArray(mtrbllgTMsg.glblCmpyCd.getValue(), mtrbllgTMsg.svcContrMtrBllgPk.getValue());
        for (int i = 0; i < xsMtrBllgArray.getValidCount(); i++) {
            S21ApiTBLAccessor.logicalRemove(xsMtrBllgArray.no(i));
        }
    }

    private static void clearSvcContrAddlChrgBllg(SVC_CONTR_BLLGTMsg bllgTMsg) {
        SVC_CONTR_ADDL_CHRG_BLLGTMsgArray addlChrgBllgArray = NSZC0470Query.getInstance().getSvcContrAddlChrgBllgTMsgArray(bllgTMsg.glblCmpyCd.getValue(), bllgTMsg.svcContrBllgPk.getValue());
        for (int i = 0; i < addlChrgBllgArray.getValidCount(); i++) {
            S21ApiTBLAccessor.logicalRemove(addlChrgBllgArray.no(i));
        }
    }

    private static void clearSvcContrBaseBllgAlloc(SVC_CONTR_BASE_BLLGTMsg basebllgTMsg) {
        SVC_CONTR_BLLG_ALLOCTMsgArray baseBllgAllocArray = NSZC0470Query.getInstance().getSvcContrBaseBllgAllocTMsgArray(basebllgTMsg.glblCmpyCd.getValue(), basebllgTMsg.svcContrBaseBllgPk.getValue());
        for (int i = 0; i < baseBllgAllocArray.getValidCount(); i++) {
            S21ApiTBLAccessor.logicalRemove(baseBllgAllocArray.no(i));
        }
    }

    private static void clearSvcContrMtrBllgAlloc(SVC_CONTR_MTR_BLLGTMsg mtrbllgTMsg) {
        SVC_CONTR_BLLG_ALLOCTMsgArray mtrBllgAllocArray = NSZC0470Query.getInstance().getSvcContrMtrBllgAllocTMsgArray(mtrbllgTMsg.glblCmpyCd.getValue(), mtrbllgTMsg.svcContrMtrBllgPk.getValue());
        for (int i = 0; i < mtrBllgAllocArray.getValidCount(); i++) {
            S21ApiTBLAccessor.logicalRemove(mtrBllgAllocArray.no(i));
        }
    }
    // Add End 2018/06/20 QC#26766
    // START 2018/07/03 K.Kim [QC#26726,ADD]
    private static boolean existCreditRebill(String glblCmpyCd, BigDecimal dsContrPk) {
        BigDecimal countCreditRebill = NSZC0470Query.getInstance().getCreditAndRebillCnt(glblCmpyCd, dsContrPk);
        if (countCreditRebill == null || countCreditRebill.compareTo(BigDecimal.ZERO) == 0) {
            return false;
        }
        return true;
    }
    // END 2018/07/03 K.Kim [QC#26726,ADD]

    // START 2019/03/05 K.Kitachi [QC#30619, ADD]
    /**
     * @param msgMap S21ApiMessageMap
     * @param glblCmpyCd String
     * @param prntDsContrDtlPk BigDecimal
     */
    public static boolean resetAccSchdPrntInfo(S21ApiMessageMap msgMap, String glblCmpyCd, BigDecimal prntDsContrDtlPk) {
        NSZC0470Query query = NSZC0470Query.getInstance();
        List<Map<String, Object>> resetAccSchdList = query.getResetAccSchdList(glblCmpyCd, prntDsContrDtlPk);
        for (Map<String, Object> resetAccSchd : resetAccSchdList) {
            BigDecimal dsContrBllgSchdPk = (BigDecimal) resetAccSchd.get("DS_CONTR_BLLG_SCHD_PK");
            DS_CONTR_BLLG_SCHDTMsg updTMsg = query.getDsContrBllgSchdTMsgForUpdate(glblCmpyCd, dsContrBllgSchdPk);
            if (updTMsg == null) {
                msgMap.addXxMsgId(NSZM0828E);
                return false;
            }
            Map<String, Object> prntBllgSchdInfo = query.getPrntBllgSchdPk(glblCmpyCd, prntDsContrDtlPk, null, updTMsg.bllgSchdFromDt.getValue(), updTMsg.bllgSchdThruDt.getValue(), FLG_ON_Y, INV_TP.INVOICE, updTMsg.dsContrDtlPk.getValue());
            if (prntBllgSchdInfo != null) {
                setValue(updTMsg.prntDsContrBllgSchdPk, (BigDecimal) prntBllgSchdInfo.get("DS_CONTR_BLLG_SCHD_PK"));
                setValue(updTMsg.nextBllgDt, (String) prntBllgSchdInfo.get("NEXT_BLLG_DT"));
            } else {
                updTMsg.prntDsContrBllgSchdPk.clear();
                String nextBllgDt = getBaseNextBllgDt(resetAccSchd);
                setValue(updTMsg.nextBllgDt, nextBllgDt);
            }
            S21ApiTBLAccessor.update(updTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode())) {
                msgMap.addXxMsgId(NSZM0828E);
                return false;
            }
        }
        return true;
    }

    private static String getBaseNextBllgDt(Map<String, Object> contrInfo) {
        CalcNextBllgDtBean inBean = new CalcNextBllgDtBean();
        inBean.setBllgSchdFromDt((String) contrInfo.get("BLLG_SCHD_FROM_DT"));
        inBean.setBllgSchdThruDt((String) contrInfo.get("BLLG_SCHD_THRU_DT"));
        inBean.setBllgTmgTp((String) contrInfo.get("BASE_BLLG_TMG_CD"));
        inBean.setContrBllgDay((String) contrInfo.get("CONTR_BLLG_DAY"));
        inBean.setInvUpToDt((String) contrInfo.get("BASE_INV_UP_TO_DT"));
        inBean.setContrEffFromDt((String) contrInfo.get("CONTR_EFF_FROM_DT"));
        return NSXC003001CalcNextBllgDt.calcTermAmt(inBean);
    }
    // END 2019/03/05 K.Kitachi [QC#30619, ADD]

    // START 2019/06/13 K.Kitachi [QC#50811, ADD]
    /**
     * @param msgMap S21ApiMessageMap
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     */
    public static boolean recovAccSchdReln(S21ApiMessageMap msgMap, String glblCmpyCd, BigDecimal dsContrPk) {
        NSZC0470Query query = NSZC0470Query.getInstance();
        Map<String, Object> paramRecovAccSchdReln = createParamRecovAccSchdReln(glblCmpyCd, dsContrPk);
        List<Map<String, Object>> recovAccSchdRelnList = query.getRecovAccSchdReln(paramRecovAccSchdReln);
        for (Map<String, Object> recovAccSchdReln : recovAccSchdRelnList) {
            BigDecimal accBllgSchdPk = (BigDecimal) recovAccSchdReln.get("ACC_BLLG_SCHD_PK");
            BigDecimal oldPrntBllgSchdPk = (BigDecimal) recovAccSchdReln.get("OLD_PRNT_BLLG_SCHD_PK");
            BigDecimal newPrntBllgSchdPk = (BigDecimal) recovAccSchdReln.get("NEW_PRNT_BLLG_SCHD_PK");
            if (isEqualNum(oldPrntBllgSchdPk, newPrntBllgSchdPk)) {
                continue;
            }
            DS_CONTR_BLLG_SCHDTMsg accBllgSchdTMsg = query.getDsContrBllgSchdTMsgForUpdate(glblCmpyCd, accBllgSchdPk);
            if (accBllgSchdTMsg == null) {
                msgMap.addXxMsgId(NSZM0828E);
                return false;
            }
            accBllgSchdTMsg.prntDsContrBllgSchdPk.clear();
            if (hasValue(newPrntBllgSchdPk)) {
                setValue(accBllgSchdTMsg.prntDsContrBllgSchdPk, newPrntBllgSchdPk);
            }
            S21ApiTBLAccessor.update(accBllgSchdTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(accBllgSchdTMsg.getReturnCode())) {
                msgMap.addXxMsgId(NSZM0828E);
                return false;
            }
            if (hasValue(oldPrntBllgSchdPk)) {
                deleteSvcContrBllgInfo(msgMap, glblCmpyCd, oldPrntBllgSchdPk);
            }
        }
        return true;
    }

    private static Map<String, Object> createParamRecovAccSchdReln(String glblCmpyCd, BigDecimal dsContrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        // START 2019/12/25 [QC#55170, DEL]
        // param.put("invTpCd", INV_TP.INVOICE);
        // END 2019/12/25 [QC#55170, DEL]
        param.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.ACCESSORIES);
        return param;
    }

    private static boolean isEqualNum(BigDecimal val1, BigDecimal val2) {
        if (!hasValue(val1) && !hasValue(val2)) {
            return true;
        }
        if (hasValue(val1) && hasValue(val2) && val1.compareTo(val2) == 0) {
            return true;
        }
        return false;
    }
    // END 2019/06/13 K.Kitachi [QC#50811, ADD]

    // START 2020/03/18 K.Kitachi [QC#55693, ADD]
    /**
     * @param msgMap S21ApiMessageMap
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     */
    public static boolean resetXsCopyPk(S21ApiMessageMap msgMap, String glblCmpyCd, BigDecimal dsContrPk) {
        NSZC0470Query query = NSZC0470Query.getInstance();
        List<Map<String, Object>> bllgSchdMtrList = query.getResetXsCopyPkOfBllgSchdMtrList(glblCmpyCd, dsContrPk);
        for (Map<String, Object> bllgSchdMtr : bllgSchdMtrList) {
            BigDecimal dsContrBllgSchdMtrPk = (BigDecimal) bllgSchdMtr.get("DS_CONTR_BLLG_SCHD_MTR_PK");
            DS_CONTR_BLLG_SCHD_MTRTMsg updTMsg = query.getDsContrBllgSchdMtrTMsgForUpdate(glblCmpyCd, dsContrBllgSchdMtrPk);
            if (updTMsg == null) {
                msgMap.addXxMsgId(NSZM1375E);
                return false;
            }
            BigDecimal dsContrPrcEffMtrPk = (BigDecimal) bllgSchdMtr.get("DS_CONTR_PRC_EFF_MTR_PK");
            setValue(updTMsg.contrXsCopyPk, dsContrPrcEffMtrPk);
            S21ApiTBLAccessor.update(updTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updTMsg.getReturnCode())) {
                msgMap.addXxMsgId(NSZM1375E);
                return false;
            }
        }
        return true;
    }
    // END 2020/03/18 K.Kitachi [QC#55693, ADD]
}
