/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1020.common;

import static business.blap.NSAL1020.constant.NSAL1020Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCStringItem;
import parts.dbcommon.EZDTBLAccessor;

import business.blap.NSAL1020.NSAL1020CMsg;
import business.blap.NSAL1020.NSAL1020Query;
import business.blap.NSAL1020.NSAL1020_ACMsg;
import business.blap.NSAL1020.NSAL1020_PCMsg;
import business.blap.NSAL1020.NSAL1020_PCMsgArray;
import business.blap.NSAL1020.constant.NSAL1020Constant;
import business.db.SVC_PHYS_MTR_READTMsg;
import business.parts.NMZC610001PMsg;
import business.parts.NMZC610001_RelatedBillShipListPMsg;

import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant;
import com.canon.cusa.s21.common.NSX.NSXC001001.CovTmplInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetCovTmpl;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 *<pre>
 * Supply Order Serial# Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/19   Hitachi         T.Tsuchida      Create          N/A
 * 2016/03/03   Hitachi         T.Tsuchida      Update          QC4738
 * 2016/03/11   Hitachi         A.Kohinata      Update          QC#5211
 * 2017/09/26   Hitachi         K.Kim           Update          QC#18744
 * 2018/07/19   Hitachi         K.Kim           Update          QC#27009
 * 2018/07/19   Hitachi         K.Kitachi       Update          QC#26978
 * 2018/07/19   Hitachi         K.Kitachi       Update          QC#26981
 * 2018/08/03   Hitachi         K.Kim           Update          QC#27009
 * 2018/08/10   Hitachi         K.Kim           Update          QC#27671
 * 2018/10/23   Hitachi         K.Kim           Update          QC#28721
 * 2018/10/30   Hitachi         K.Kim           Update          QC#28721
 * 2019/01/15   Hitachi         A.Kohinata      Update          QC#29917
 * 2019/07/31   Hitachi         T.Kanasaka      Update          QC#51976
 * 2024/04/11   Hitachi         T.Kawasue       Update          QC#63717
 *</pre>
 */
public class NSAL1020CommonLogic {

    /**
     * @param cMsg NSAL1020CMsg
     */
    public static void getSearchData(NSAL1020CMsg cMsg) {

        ZYPTableUtil.clear(cMsg.A);

        // START 2018/07/19 K.Kim [QC#27009, ADD]
        // Get Related Account for Related BillTo / ShipTo
        NMZC610001PMsg custInfoGetApiPMsg = callCustomerInfomationGetApi(cMsg.glblCmpyCd.getValue(), cMsg.billToAcctNum_PH.getValue());
        // Get Bill To Code / Ship To Code
        List<String> relnShipToCustCdList = new ArrayList<String>(); 
        for (int i = 0; i < custInfoGetApiPMsg.RelatedBillShipList.getValidCount(); i++) {
            NMZC610001_RelatedBillShipListPMsg inPmsg = custInfoGetApiPMsg.RelatedBillShipList.no(i);
            // START 2018/08/03 [QC#27009, MOD]
            // if (hasValue(inPmsg.shipToCustCd) && ZYPConstant.FLG_ON_Y.equals(inPmsg.dsAcctRelnShipToFlg.getValue())) {
            if (hasValue(inPmsg.shipToCustCd)) {
            // END 2018/08/03 [QC#27009, MOD]
                relnShipToCustCdList.add((String)inPmsg.shipToCustCd.getValue());
            }
        }
        // relnShipToCustCdList.add(cMsg.shipToCustCd_PH.getValue());
        // END 2018/07/19 K.Kim [QC#27009, ADD]

        Map<String, Object> ssmParamGetSearchData = createParamGetSearchData(cMsg, cMsg.A.length() + 1, relnShipToCustCdList);
        S21SsmEZDResult ssmResult = NSAL1020Query.getInstance().getSearchData(ssmParamGetSearchData, cMsg.A);
        if (!ssmResult.isCodeNormal()) {
            return;
        }

        // Result > 200
        int queryResCnt = ssmResult.getQueryResultCount();
        if (queryResCnt > cMsg.A.length()) {
            cMsg.setMessageInfo(NZZM0001W);
        } else {
            cMsg.setMessageInfo(NZZM0002I);
        }

        // START 2018/07/19 K.Kitachi [QC#26981, ADD]
        Map<String, Object> ssmParamGetFltMachPk = createParamGetFltMachPk(cMsg);
        List<BigDecimal> chkFltMachPkList = NSAL1020Query.getInstance().getFltMachPk(ssmParamGetFltMachPk);
        // END 2018/07/19 K.Kitachi [QC#26981, ADD]

        // START 2024/04/11 T.Kawasue [QC#63717, ADD]
        String glblCmpyCd = cMsg.glblCmpyCd.getValue();
        // END 2024/04/11 T.Kawasue [QC#63717, ADD]

        // START 2016/03/03 T.Tsuchida [QC4738, MOD]
        validChk: for (int aCMsgIdx = 0; aCMsgIdx < cMsg.A.getValidCount(); aCMsgIdx++) {

            NSAL1020_ACMsg aCMsg = cMsg.A.no(aCMsgIdx);

            // Set Address
            setValue(aCMsg.xxAllLineAddr_D, createAddress(aCMsg));

            // Selected Serial# Check
            for (int pCMsgIdx = 0; pCMsgIdx < cMsg.P.getValidCount(); pCMsgIdx++) {
                NSAL1020_PCMsg pCMsg = cMsg.P.no(pCMsgIdx);
                // START 2018/07/19 K.Kitachi [QC#26978, MOD]
//                if (aCMsg.serNum_D.getValue().equals(pCMsg.serNum_PD.getValue())) {
                if (aCMsg.svcMachMstrPk_D.getValue().compareTo(pCMsg.svcMachMstrPk_PD.getValue()) == 0) {
                // END 2018/07/19 K.Kitachi [QC#26978, MOD]
                    // Set Comments
                    setValue(aCMsg.xxMsgTxt_D, getMessageTxt(NSAM0427E));
                    continue validChk;
                }
            }

            // START 2018/07/19 K.Kitachi [QC#26981, ADD]
            if (chkFltMachPkList.contains(aCMsg.svcMachMstrPk_D.getValue())) {
                // Set Comments
                setValue(aCMsg.xxMsgTxt_D, getMessageTxt(NSAM0733E));
                continue validChk;
            }
            // END 2018/07/19 K.Kitachi [QC#26981, ADD]

            // Supply Inclusive, Laser Plus Check
            if (!isSuplInclOrLaserPlusContr(cMsg, aCMsgIdx)) {
                // Set Comments
                setValue(aCMsg.xxMsgTxt_D, getMessageTxt(NSAM0428E));
                setValue(aCMsg.xxRsltFlg_D, FLG_OFF_N);
                continue validChk;
            }

            // Line of Business Check
            // START 2016/03/11 [QC5211, MOD]
            // START 2019/07/31 T.Kanasaka [QC#51976, MOD]
//            if (!aCMsg.svcByLineBizTpCd_D.getValue().equals(cMsg.lineBizTpCd_PH.getValue())) {
            if ((hasValue(aCMsg.sldByLineBizTpCd_D) && !aCMsg.sldByLineBizTpCd_D.getValue().equals(cMsg.lineBizTpCd_PH.getValue())) || (!hasValue(aCMsg.sldByLineBizTpCd_D) && !aCMsg.svcByLineBizTpCd_D.getValue().equals(cMsg.lineBizTpCd_PH.getValue()))) {
            // END 2019/07/31 T.Kanasaka [QC#51976, MOD]
            // END 2016/03/11 [QC5211, MOD]
                // Set Comments
                setValue(aCMsg.xxMsgTxt_D, getMessageTxt(NSAM0429E));
                continue validChk;
            }

            // START 2018/08/10 K.Kim [QC#27671, DEL]
            // // Ship to Check
            // // START 2016/03/11 [QC5211, MOD]
            // if (!aCMsg.shipToCustCd_D.getValue().equals(cMsg.shipToCustCd_PH.getValue())) {
            // // END 2016/03/11 [QC5211, MOD]
            //     // Set Comments
            //     setValue(aCMsg.xxMsgTxt_D, getMessageTxt(NSAM0430E));
            //     continue validChk;
            // }
            // END 2018/08/10 K.Kim [QC#27671, DEL]

            // Supply Order Check
            // del start 2019/01/15 QC#29917
            //Map<String, Object> ssmParamCntCpo = createParamCntCpo(cMsg, aCMsgIdx);
            //if (NSAL1020Query.getInstance().existsCpo(ssmParamCntCpo)) {
            //    // Set Comments
            //    setValue(aCMsg.xxMsgTxt_D, getMessageTxt(NSAM0431E));
            //}
            // del end 2019/01/15 QC#29917

            // START 2017/09/26 K.Kim [QC#18744, ADD]
            // Supply Inclusive Check
            if (ZYPConstant.FLG_ON_Y.equals(aCMsg.splyInclFlg_D.getValue())) {
                // Set Comments
                setValue(aCMsg.xxMsgTxt_D, getMessageTxt(NSAM0704E));

                // START 2024/04/11 T.Kawasue [QC#63717, ADD]
                continue validChk;
                // END 2024/04/11 T.Kawasue [QC#63717, ADD]
            }
            // END 2017/09/26 K.Kim [QC#18744, ADD]

            // START 2024/04/11 T.Kawasue [QC#63717, ADD]
            // Meter Read Check
            if (!existSplyReadExclCust(glblCmpyCd, aCMsg.svcMachMstrPk_D.getValue())) {
                String salesDt = ZYPDateUtil.getSalesDate();
                String lineBizTp = getLineBizTp(glblCmpyCd, aCMsg.svcByLineBizTpCd_D, aCMsg.sldByLineBizTpCd_D);
                BigDecimal mtrEntryIntervalDays = null;
                if (LINE_BIZ_TP.LFS.equals(lineBizTp)) {
                    mtrEntryIntervalDays = ZYPCodeDataUtil.getNumConstValue("NSAL0150_INTVL_DAYS_LFS", glblCmpyCd);
                } else if (LINE_BIZ_TP.PPS.equals(lineBizTp)) {
                    mtrEntryIntervalDays = ZYPCodeDataUtil.getNumConstValue("NSAL0150_INTVL_DAYS_PPS", glblCmpyCd);
                } else if (LINE_BIZ_TP.ESS.equals(lineBizTp)) {
                    mtrEntryIntervalDays = ZYPCodeDataUtil.getNumConstValue("NSAL0150_INTVL_DAYS_WTS", glblCmpyCd);
                }
                if (!ZYPCommonFunc.hasValue(mtrEntryIntervalDays)) {
                    mtrEntryIntervalDays = NSAL1020Constant.DEF_SPLY_ODR_MTR_ENTRY_INTVL_DAYS;
                }
                String mtrEntryLimitDt = ZYPDateUtil.addDays(salesDt, mtrEntryIntervalDays.intValue() * -1);

                SVC_PHYS_MTR_READTMsg inMsg = new SVC_PHYS_MTR_READTMsg();
                inMsg.setSQLID("011");
                inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                inMsg.setConditionValue("svcMachMstrPk01", aCMsg.svcMachMstrPk_D.getValue());
                inMsg.setConditionValue("vldMtrFlg01", ZYPConstant.FLG_ON_Y);
                inMsg.setConditionValue("mtrReadDt01", mtrEntryLimitDt);
                int count = EZDTBLAccessor.count(inMsg);
                if (count == 0) {
                    String message = S21MessageFunc.clspGetMessage(NSAM0795E, new String[] {String.valueOf(mtrEntryIntervalDays.intValue())});
                    message = message.substring(NSAM0795E.length() + 1);
                    setValue(aCMsg.xxMsgTxt_D, message);
                    setValue(aCMsg.xxRsltFlg_D, FLG_OFF_N);
                    continue validChk;
                }
            }
            // END 2024/04/11 T.Kawasue [QC#63717, ADD]
        }
        // END 2016/03/03 T.Tsuchida [QC4738, MOD]
    }

    private static Map<String, Object> createParamGetSearchData(NSAL1020CMsg cMsg, int rowNum, List<String> relnShipToCustCdList) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        // START 2018/10/23 [QC#28721, DEL]
        // ssmParam.put("ownrAcctNum", cMsg.ownrAcctNum_PH.getValue());
        // END 2018/10/23 [QC#28721, DEL]
        // START 2018/07/19 K.Kitachi [QC#26981, ADD]
        // START 2018/10/30 [QC#28721, ADD]
        ssmParam.put("svcMachTpMachine", SVC_MACH_TP.MACHINE);
        // END 2018/10/30 [QC#28721, ADD]
        ssmParam.put("dsContrCatgCd", DS_CONTR_CATG.FLEET);
        // END 2018/07/19 K.Kitachi [QC#26981, ADD]
        ssmParam.put("serNum", cMsg.serNum_H.getValue());
        // START 2018/07/12 K.Kim [QC#27009, ADD]
        ssmParam.put("relnShipToCustCdList", relnShipToCustCdList);
        // END 2018/07/12 K.Kim [QC#27009, ADD]
        ssmParam.put("rowNum", rowNum);
        return ssmParam;
    }

    // del start 2019/01/15 QC#29917
    //private static Map<String, Object> createParamCntCpo(NSAL1020CMsg cMsg, int idx) {
    //    Map<String, Object> ssmParam = new HashMap<String, Object>();
    //    ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
    //    ssmParam.put("billToCustAcctCd", cMsg.ownrAcctNum_PH.getValue());
    //    ssmParam.put("pickSvcConfigMstrPk", cMsg.A.no(idx).svcConfigMstrPk_D.getValue());
    //    ssmParam.put("ordLineStsCd", ORD_LINE_STS.VALIDATED);
    //    return ssmParam;
    //}
    // del end 2019/01/15 QC#29917

    private static String createAddress(NSAL1020_ACMsg aCMsg) {
        StringBuilder sb = new StringBuilder();
        sb = appendData(sb, aCMsg.firstLineAddr_D);
        sb = appendData(sb, aCMsg.scdLineAddr_D);
        sb = appendData(sb, aCMsg.thirdLineAddr_D);
        sb = appendData(sb, aCMsg.frthLineAddr_D);
        sb = appendData(sb, aCMsg.ctyAddr_D);
        sb = appendData(sb, aCMsg.stCd_D);
        sb = appendData(sb, aCMsg.postCd_D);
        return sb.toString();
    }

    private static StringBuilder appendData(StringBuilder sb, EZDCStringItem item) {
        if (hasValue(item)) {
            if (sb.length() > 0) {
                sb.append(COMMA);
            }
            sb.append(item.getValue());
        }
        return sb;
    }

    private static String getMessageTxt(String msgId) {
        String msgTxt = S21MessageFunc.clspGetMessage(msgId);
        return msgTxt.substring(msgId.length() + 1);
    }

    private static boolean isSuplInclOrLaserPlusContr(NSAL1020CMsg cMsg, int idx) {
        NSXC001001GetCovTmpl covTmpl = new NSXC001001GetCovTmpl();
        CovTmplInfo tmplInfo = new CovTmplInfo();
        tmplInfo.setGlblCmpyCd(cMsg.glblCmpyCd.getValue());
        tmplInfo.setSlsDt(cMsg.slsDt.getValue());
        tmplInfo.setSvcPgmMdseCd(cMsg.A.no(idx).svcPgmMdseCd_D.getValue());
        boolean isSuplIncl = covTmpl.isSuplIncl(tmplInfo);
        boolean isLaserPlusContr = covTmpl.isLaserPlusContr(tmplInfo);
        if (isSuplIncl || isLaserPlusContr) {
            return true;
        }
        return false;
    }

    // START 2018/10/23 [QC#28721, MOD]
    // START 2018/07/19 K.Kim [QC#27009, ADD]
    /**
     * @param glblCmpyCd String
     * @param dsAcctNum String
     * @return NMZC610001PMsg
     */
    private static NMZC610001PMsg callCustomerInfomationGetApi(String glblCmpyCd, String dsAcctNum) {

        NMZC610001PMsg custInfoGetApiPMsg = new NMZC610001PMsg();
        setValue(custInfoGetApiPMsg.glblCmpyCd, glblCmpyCd);
        setValue(custInfoGetApiPMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_RELATED_BILL_SHIP);
        setValue(custInfoGetApiPMsg.xxChildRelnFlg, ZYPConstant.FLG_ON_Y);
        setValue(custInfoGetApiPMsg.dsAcctNum_I1, dsAcctNum);
        setValue(custInfoGetApiPMsg.xxModeCd_SB, NMZC610001Constant.RELATED_BILL_SHIP_ALL_RGTN_STS_CD);
        setValue(custInfoGetApiPMsg.xxResFltrFlg, ZYPConstant.FLG_ON_Y);
        new NMZC610001().execute(custInfoGetApiPMsg, ONBATCH_TYPE.ONLINE);
        return custInfoGetApiPMsg;
    }
    // END 2018/07/19 K.Kim [QC#27009, ADD]
    // END 2018/10/23 [QC#28721, MOD]

    // START 2018/07/19 K.Kitachi [QC#26981, ADD]
    private static Map<String, Object> createParamGetFltMachPk(NSAL1020CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        ssmParam.put("dsContrCatgCd", DS_CONTR_CATG.FLEET);
        List<BigDecimal> svcMachMstrPkList = createInParamPkList(cMsg.P);
        ssmParam.put("svcMachMstrPkList", svcMachMstrPkList);
        return ssmParam;
    }

    private static List<BigDecimal> createInParamPkList(NSAL1020_PCMsgArray pCMsgArray) {
        List<BigDecimal> list = new ArrayList<BigDecimal>();
        for (int pCMsgIdx = 0; pCMsgIdx < pCMsgArray.getValidCount(); pCMsgIdx++) {
            if (!list.contains(pCMsgArray.no(pCMsgIdx).svcMachMstrPk_PD.getValue())) {
                list.add(pCMsgArray.no(pCMsgIdx).svcMachMstrPk_PD.getValue());
            }
        }
        return list;
    }
    // END 2018/07/19 K.Kitachi [QC#26981, ADD]

    // START 2024/04/11 T.Kawasue [QC#63717, ADD]
    private static boolean existSplyReadExclCust(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        if (!ZYPCommonFunc.hasValue(svcMachMstrPk)) {
            return false;
        }
        String salesDt = ZYPDateUtil.getSalesDate();
        BigDecimal count = NSAL1020Query.getInstance().getSplyReadExclCustCnt(glblCmpyCd, salesDt, svcMachMstrPk);
        if (BigDecimal.ZERO.compareTo(count) == 0) {
            return false;
        }
        return true;
    }

    private static String getLineBizTp(String glblCmpyCd, EZDCStringItem svcByLineBizTpCd, EZDCStringItem sldByLineBizTpCd) {
        String lineBizTpCd = "";
        if (!ZYPCommonFunc.hasValue(svcByLineBizTpCd) && !ZYPCommonFunc.hasValue(sldByLineBizTpCd)) {
            return lineBizTpCd;
        }
        lineBizTpCd = sldByLineBizTpCd.getValue();
        if (!ZYPCommonFunc.hasValue(lineBizTpCd)) {
            lineBizTpCd = svcByLineBizTpCd.getValue();
        }
        lineBizTpCd = NSAL1020Query.getInstance().convLineBizTpToSvcLineBiz(glblCmpyCd, lineBizTpCd);
        return lineBizTpCd;
    }
    // END 2024/04/11 T.Kawasue [QC#63717, ADD]
}
