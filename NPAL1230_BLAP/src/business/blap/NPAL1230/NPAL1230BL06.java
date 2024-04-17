/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NPAL1230;

import static business.blap.NPAL1230.constant.NPAL1230Constant.DB_PARAM_ASL_NM;
import static business.blap.NPAL1230.constant.NPAL1230Constant.DB_PARAM_ASL_QLFY_REF_CD_LIST;
import static business.blap.NPAL1230.constant.NPAL1230Constant.DB_PARAM_ASL_QLFY_TP_CD;
import static business.blap.NPAL1230.constant.NPAL1230Constant.DB_PARAM_EFF_FROM_DT;
import static business.blap.NPAL1230.constant.NPAL1230Constant.DB_PARAM_EFF_THRU_DT;
import static business.blap.NPAL1230.constant.NPAL1230Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NPAL1230.constant.NPAL1230Constant.DB_PARAM_IS_ASL_QLFY_REF_FLG;
import static business.blap.NPAL1230.constant.NPAL1230Constant.DB_PARAM_MDSE_CD;
import static business.blap.NPAL1230.constant.NPAL1230Constant.DB_PARAM_PRNT_VND_CD;
import static business.blap.NPAL1230.constant.NPAL1230Constant.DB_PARAM_RGTN_STS_CD_LIST;
import static business.blap.NPAL1230.constant.NPAL1230Constant.DB_PARAM_SALES_DATE;
import static business.blap.NPAL1230.constant.NPAL1230Constant.DB_PARAM_VND_CD;
import static business.blap.NPAL1230.constant.NPAL1230Constant.DISPLAY_NM_SPLY_ITEM_NUM;
import static business.blap.NPAL1230.constant.NPAL1230Constant.EVENT_NM_NPAL1230_CMN_SUBMIT;
import static business.blap.NPAL1230.constant.NPAL1230Constant.MAX_DATE;
import static business.blap.NPAL1230.constant.NPAL1230Constant.MIN_DATE;
import static business.blap.NPAL1230.constant.NPAL1230Constant.NMAM0072E;
import static business.blap.NPAL1230.constant.NPAL1230Constant.NPAM1297E;
import static business.blap.NPAL1230.constant.NPAL1230Constant.NPAM1565E;
import static business.blap.NPAL1230.constant.NPAL1230Constant.NPAM1580E;
import static business.blap.NPAL1230.constant.NPAL1230Constant.NPAM1652E;
import static business.blap.NPAL1230.constant.NPAL1230Constant.NZZM0002I;
import static business.blap.NPAL1230.constant.NPAL1230Constant.NZZM0010E;
import static business.blap.NPAL1230.constant.NPAL1230Constant.ZZM9037E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NPAL1230.common.NPAL1230CommonLogic;
import business.db.ASL_DTLTMsg;
import business.db.ASL_HDRTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASL_QLFY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Business ID : NPAL1230 ASL Entry
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/12   CITS            T.Gotoda        Create          N/A
 * 10/07/2016   CSAI            Y.Imazu         Update          QC#14881
 * 2018/04/11   CITS            K.Ogino         Update          QC#18162
 * 2018/04/15   CITS            K.Fukumura      Update          QC#21170
 * 2018/07/12   CITS            S.Katsuma       Update          QC#26849
 * 2018/08/27   CITS            K.Ogino         Update          QC#27834
 * 2019/05/16   CITS            T.Tokutomi      Update          QC#50098
 * 2021/01/15   CITS            J.Evangelista   Update          QC#58165
 * 2021/08/09   CITS            A.Marte         Update          QC#58933
 * 2021/08/31   CITS            A.Marte         Update          QC#59145
 *</pre>
 */
public class NPAL1230BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ((EVENT_NM_NPAL1230_CMN_SUBMIT.equals(screenAplID))) {
                doProcess_Submit((NPAL1230CMsg) cMsg, (NPAL1230SMsg) sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Submit
     * @param cMsg NPAL1230CMsg
     * @param sMsg NPAL1230SMsg
     */
    private void doProcess_Submit(NPAL1230CMsg cMsg, NPAL1230SMsg sMsg) {

        // QC#21170 Start
        // copy data from CMsg onto SMsg
        int pageShowFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            EZDMsg.copy(cMsg.A.no(i), null, sMsg.A.no(pageShowFromNum + i), null);
        }
        // QC#21170 End

        String glblCmpyCd = cMsg.glblCmpyCd.getValue();
        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd);

        // Check ASL Header
        boolean hdrValidateFlg = true;

        // Check Supplier Code
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_PRNT_VND_CD, cMsg.prntVndCd.getValue());
        ssmParam.put(DB_PARAM_SALES_DATE, slsDt);

        S21SsmEZDResult result = NPAL1230Query.getInstance().chkSupplier(ssmParam);
        if (!result.isCodeNormal() || (Integer) result.getResultObject() == 0) {
            cMsg.prntVndCd.setErrorInfo(1, NZZM0010E, new String[] {cMsg.prntVndCd.getValue() });
            cMsg.setMessageInfo(NZZM0010E, new String[] {cMsg.prntVndCd.getValue() });
            hdrValidateFlg = false;
        }

        // Check ASL Name
        ssmParam.clear();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_ASL_NM, cMsg.aslNm);
        // START 2018/07/12 S.Katsuma [QC#26849,ADD]
        if (ZYPCommonFunc.hasValue(cMsg.aslHdrPk)) {
            ssmParam.put(DB_PARAM_PRNT_VND_CD, cMsg.prntVndCd);
        }
        // END 2018/07/12 S.Katsuma [QC#26849,ADD]
        result = NPAL1230Query.getInstance().chkAslNm(ssmParam);
        if (result.isCodeNormal()) {
            int count = (Integer) result.getResultObject();

            if ((ZYPCommonFunc.hasValue(cMsg.aslHdrPk) && count > 1) || (!ZYPCommonFunc.hasValue(cMsg.aslHdrPk) && count > 0)) {
                // Duplicate
                cMsg.aslNm.setErrorInfo(1, NMAM0072E, new String[] {"ASL Name" });
                cMsg.setMessageInfo(NMAM0072E, new String[] {"ASL Name" });
                hdrValidateFlg = false;
            }
        }

        // Check ASL Details
        boolean itemValidateFlg = true;
        // QC#27834
        StringBuilder sb = new StringBuilder();
        ArrayList<BigDecimal> aslDtlPkList = new ArrayList<BigDecimal>();
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).aslDtlPk_A)) {
                aslDtlPkList.add(sMsg.A.no(i).aslDtlPk_A.getValue());
                sb.append(sMsg.A.no(i).aslDtlPk_A.getValue());
                sb.append(",");
            }
        }
        // QC#27834
        if (sb.lastIndexOf(",") > -1) {
            sb.delete(sb.length() - 1, sb.length());
        }

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            // ////////////////////////////
            // Check Item Number
            // ////////////////////////////
            ssmParam.clear();
            ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            ssmParam.put(DB_PARAM_MDSE_CD, sMsg.A.no(i).mdseCd_A.getValue());
            ArrayList<String> rgtnStsCdList = new ArrayList<String>();
            rgtnStsCdList.add(RGTN_STS.READY_FOR_CUSTOMS_CLEARANCE_AND_RECEIVING);
            rgtnStsCdList.add(RGTN_STS.READY_FOR_ORDER_TAKING);
            ssmParam.put(DB_PARAM_RGTN_STS_CD_LIST, rgtnStsCdList);

            result = NPAL1230Query.getInstance().chkMdseCd(ssmParam);
            if (!result.isCodeNormal() || (Integer) result.getResultObject() == 0) {
                // Does not exist in master.
                sMsg.A.no(i).mdseCd_A.setErrorInfo(1, NZZM0010E, new String[] {cMsg.mdseCd.getValue() });
                cMsg.setMessageInfo(NZZM0010E, new String[] {cMsg.mdseCd.getValue() });
                itemValidateFlg = false;
            }

            // ////////////////////////////
            // Duplicate Item Check
            // ////////////////////////////
            int itemCount = 0;
            // Display Line Check
            for (int j = 0; j < sMsg.A.getValidCount(); j++) {

                if (j != i && sMsg.A.no(i).mdseCd_A.getValue().equals(sMsg.A.no(j).mdseCd_A.getValue()) && sMsg.A.no(i).vndCd_A.getValue().equals(sMsg.A.no(j).vndCd_A.getValue())) {

                    if (chkEffTerm(sMsg.A.no(i).effFromDt_A.getValue(), sMsg.A.no(i).effThruDt_A.getValue(), sMsg.A.no(j).effFromDt_A.getValue(), sMsg.A.no(j).effThruDt_A.getValue())) {

                        itemCount++;
                    }
                }
            }

            // DB Check
            ssmParam.clear();
            ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            ssmParam.put(DB_PARAM_PRNT_VND_CD, cMsg.prntVndCd.getValue());
            // QC#27834
            // ssmParam.put(DB_PARAM_ASL_DTL_PK_LIST, aslDtlPkList);
            ssmParam.put("PKs", sb.toString());
            ssmParam.put(DB_PARAM_VND_CD, sMsg.A.no(i).vndCd_A.getValue());
            ssmParam.put(DB_PARAM_MDSE_CD, sMsg.A.no(i).mdseCd_A.getValue());
            ssmParam.put(DB_PARAM_EFF_FROM_DT, sMsg.A.no(i).effFromDt_A.getValue());
            ssmParam.put(DB_PARAM_EFF_THRU_DT, sMsg.A.no(i).effThruDt_A.getValue());
            ssmParam.put(DB_PARAM_ASL_QLFY_TP_CD, ASL_QLFY_TP.BIG_DEAL_SPECIFIC);

            ArrayList<String> aslQlfyRefCdList = new ArrayList<String>();
            for (int j = 0; j < sMsg.Q.getValidCount(); j++) {
                aslQlfyRefCdList.add(sMsg.Q.no(j).aslQlfyRefCd_Q.getValue());
            }
            ssmParam.put(DB_PARAM_ASL_QLFY_REF_CD_LIST, aslQlfyRefCdList);

            if (aslQlfyRefCdList.size() > 0) {
                ssmParam.put(DB_PARAM_IS_ASL_QLFY_REF_FLG, ZYPConstant.FLG_ON_Y);
            } else {
                ssmParam.put(DB_PARAM_IS_ASL_QLFY_REF_FLG, ZYPConstant.FLG_OFF_N);
            }

            result = NPAL1230Query.getInstance().chkAslDtl(ssmParam);
            if (result.isCodeNormal()) {
                itemCount = itemCount + (Integer) result.getResultObject();
            }

            if (itemCount > 0) {
                // Duplicate
                sMsg.A.no(i).mdseCd_A.setErrorInfo(1, NMAM0072E, new String[] {sMsg.A.no(i).mdseCd_A.getValue() });
                cMsg.setMessageInfo(NMAM0072E, new String[] {sMsg.A.no(i).mdseCd_A.getValue() });
                itemValidateFlg = false;
            }

            // START 2021/01/15 J.Evangelista [QC#58165,ADD]
            // ////////////////////////////
            // Check Supplier Item Code
            // ////////////////////////////
            // Get Supplier Item Code Max Length
            String splyItemNumMaxLen = NPAL1230CommonLogic.getSupplierItemCodeMaxLength(glblCmpyCd, sMsg.A.no(i).vndCd_A.getValue());
            if (ZYPCommonFunc.hasValue(splyItemNumMaxLen)) {
                if (sMsg.A.no(i).splyItemNum_A.getValue().length() > Integer.parseInt(splyItemNumMaxLen)) {
                    sMsg.A.no(i).splyItemNum_A.setErrorInfo(1, NPAM1652E, new String[] {DISPLAY_NM_SPLY_ITEM_NUM, splyItemNumMaxLen });
                    cMsg.setMessageInfo(NPAM1652E, new String[] {DISPLAY_NM_SPLY_ITEM_NUM, splyItemNumMaxLen });
                    itemValidateFlg = false;
                }
            }
            // END 2021/01/15 J.Evangelista [QC#58165,ADD]

            // ////////////////////////////
            // Check Supplier Site Code
            // ////////////////////////////
            ssmParam.clear();
            ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            ssmParam.put(DB_PARAM_PRNT_VND_CD, cMsg.prntVndCd.getValue());
            ssmParam.put(DB_PARAM_VND_CD, sMsg.A.no(i).vndCd_A.getValue());
            ssmParam.put(DB_PARAM_RGTN_STS_CD_LIST, rgtnStsCdList);
            result = NPAL1230Query.getInstance().chkSupplierSite(ssmParam);
            if (!result.isCodeNormal() || (Integer) result.getResultObject() == 0) {
                // Does not exist in master.
                sMsg.A.no(i).vndCd_A.setErrorInfo(1, NZZM0010E, new String[] {sMsg.A.no(i).vndCd_A.getValue() });
                cMsg.setMessageInfo(NZZM0010E, new String[] {sMsg.A.no(i).vndCd_A.getValue() });
                itemValidateFlg = false;
            }

            // ////////////////////////////
            // Check Prime Flag
            // ////////////////////////////
            int primSplyFlgCount = 0;

            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(i).primSplyFlg_A.getValue())) {
                primSplyFlgCount++;
            }

            // Display Line Check
            for (int j = 0; j < sMsg.A.getValidCount(); j++) {

                if (j != i && sMsg.A.no(i).mdseCd_A.getValue().equals(sMsg.A.no(j).mdseCd_A.getValue())) {

                    if (chkEffTerm(sMsg.A.no(i).effFromDt_A.getValue(), sMsg.A.no(i).effThruDt_A.getValue(), sMsg.A.no(j).effFromDt_A.getValue(), sMsg.A.no(j).effThruDt_A.getValue())) {

                        if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(j).primSplyFlg_A.getValue())) {
                            primSplyFlgCount++;
                        }
                    }
                }
            }

            // DB Check
            ssmParam.clear();
            ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
            ssmParam.put(DB_PARAM_MDSE_CD, sMsg.A.no(i).mdseCd_A.getValue());
            // QC#27834
            // ssmParam.put(DB_PARAM_ASL_DTL_PK_LIST, aslDtlPkList);
            ssmParam.put("PKs", sb.toString());
            result = NPAL1230Query.getInstance().getPrimSplyFlg(ssmParam);

            if (result.isCodeNormal() && result.getQueryResultCount() > 0) {

                List<ASL_DTLTMsg> primFlgList = (List<ASL_DTLTMsg>) result.getResultObject();
                for (int j = 0; j < primFlgList.size(); j++) {

                    if (chkEffTerm(sMsg.A.no(i).effFromDt_A.getValue(), sMsg.A.no(i).effThruDt_A.getValue(), primFlgList.get(j).effFromDt.getValue(), primFlgList.get(j).effThruDt.getValue())) {

                        if (ZYPConstant.FLG_ON_Y.equals(primFlgList.get(j).primSplyFlg.getValue())) {
                            primSplyFlgCount++;
                            // QC#18162
                            if (primSplyFlgCount > 1) {
                                ASL_DTLTMsg aslDtlTMsg = new ASL_DTLTMsg();
                                aslDtlTMsg.glblCmpyCd.setValue(glblCmpyCd);
                                aslDtlTMsg.aslDtlPk.setValue(primFlgList.get(j).aslDtlPk.getValue());

                                aslDtlTMsg = (ASL_DTLTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(aslDtlTMsg);

                                if (aslDtlTMsg == null) {
                                    sMsg.A.no(i).mdseCd_A.setErrorInfo(1, NPAM1297E);
                                    cMsg.setMessageInfo(NPAM1297E);
                                    // QC#21170 Start
                                    CopySMsgToCMsgAarray(cMsg, sMsg);
                                    // QC#21170 End
                                    return;
                                }

                                aslDtlTMsg.primSplyFlg.setValue(ZYPConstant.FLG_OFF_N);
                                EZDTBLAccessor.update(aslDtlTMsg);

                                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(aslDtlTMsg.getReturnCode())) {
                                    sMsg.A.no(i).mdseCd_A.setErrorInfo(1, NPAM1297E);
                                    cMsg.setMessageInfo(NPAM1297E);
                                    // QC#21170 Start
                                    CopySMsgToCMsgAarray(cMsg, sMsg);
                                    // QC#21170 End
                                    return;
                                }

                                primSplyFlgCount--;
                            }
                        }
                    }
                }
            }

            /* 10/07/2016 CSAI Y.Imazu Mod QC#14881 START */
            // if (primSplyFlgCount != 1) {
            if (primSplyFlgCount > 1) {
                /* 10/07/2016 CSAI Y.Imazu Mod QC#14881 END */

                // Prime supply Flag Error
                sMsg.A.no(i).primSplyFlg_A.setErrorInfo(1, NPAM1565E);
                /* 10/07/2016 CSAI Y.Imazu Mod QC#14881 START */
                // cMsg.setMessageInfo(NPAM1565E);
                cMsg.setMessageInfo(ZZM9037E);
                /* 10/07/2016 CSAI Y.Imazu Mod QC#14881 END */
                itemValidateFlg = false;

                /* 10/07/2016 CSAI Y.Imazu Add QC#14881 START */
            } else if (primSplyFlgCount == 0) {

                sMsg.A.no(i).primSplyFlg_A.setErrorInfo(1, NPAM1580E);
                cMsg.setMessageInfo(ZZM9037E);
                itemValidateFlg = false;
                /* 10/07/2016 CSAI Y.Imazu Add QC#14881 END */
            }
        }

        if (!hdrValidateFlg || !itemValidateFlg) {
            // QC#21170 Start
            // QC#50098 set Error Page.
            int index = 0;
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                if (sMsg.A.no(i).mdseCd_A.isError() //
                        || sMsg.A.no(i).vndCd_A.isError() //
                        || sMsg.A.no(i).primSplyFlg_A.isError()) {
                    index = i;
                    break;
                }
            }

            int pageCnt = 0;

            while (index > cMsg.A.length() * (pageCnt + 1)) {
                pageCnt++;
            }

            // Error Page
            int errPageShowFromNum = (cMsg.A.length() * pageCnt) + 1;
            cMsg.xxPageShowFromNum.setValue(errPageShowFromNum);
            int showToNum = (errPageShowFromNum - 1) + cMsg.A.length();
            if (showToNum > cMsg.xxPageShowOfNum.getValueInt()) {
                cMsg.xxPageShowToNum.setValue(cMsg.xxPageShowOfNum.getValueInt());
                cMsg.A.setValidCount(cMsg.xxPageShowOfNum.getValueInt() - (errPageShowFromNum -1));
            } else {
                cMsg.xxPageShowToNum.setValue((errPageShowFromNum - 1) + cMsg.A.length());
                cMsg.A.setValidCount(cMsg.A.length());
            }

            CopySMsgToCMsgAarray(cMsg, sMsg);
            // QC#21170 End
            return;
        }

        // Get Lock for ASL_HDR Table.
        if (ZYPCommonFunc.hasValue(cMsg.aslHdrPk)) {
            ASL_HDRTMsg aslHdrTMsg = new ASL_HDRTMsg();

            aslHdrTMsg.glblCmpyCd.setValue(glblCmpyCd);
            aslHdrTMsg.aslHdrPk.setValue(cMsg.aslHdrPk.getValue());

            aslHdrTMsg = (ASL_HDRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait(aslHdrTMsg);

            if (aslHdrTMsg == null) {
                cMsg.aslNm.setErrorInfo(1, NPAM1297E);
                cMsg.setMessageInfo(NPAM1297E);
                // QC#21170 Start
                CopySMsgToCMsgAarray(cMsg, sMsg);
                // QC#21170 End
                return;
            }

            String aslHdrEzUpTime = cMsg.ezUpTime.getValue();
            String aslHdrEzTimeZone = cMsg.ezUpTimeZone.getValue();
            String dbEzUpTime = aslHdrTMsg.ezUpTime.getValue();
            String dbEzTimeZone = aslHdrTMsg.ezUpTimeZone.getValue();

            if (!ZYPDateUtil.isSameTimeStamp(aslHdrEzUpTime, aslHdrEzTimeZone, dbEzUpTime, dbEzTimeZone)) {
                cMsg.aslNm.setErrorInfo(1, NPAM1297E);
                cMsg.setMessageInfo(NPAM1297E);
                // QC#21170 Start
                CopySMsgToCMsgAarray(cMsg, sMsg);
                // QC#21170 End
                return;
            }
        }
        // QC#21170 Start
        CopySMsgToCMsgAarray(cMsg, sMsg);
        // QC#21170 End

        // Call ASL Update API
        // QC#21170 Add Parameter sMsg
        if (NPAL1230CommonLogic.executeAslUpdateApi(cMsg, sMsg)) {
            cMsg.setMessageInfo(NZZM0002I);
        }
    }

    // QC#21170 Start
    private void CopySMsgToCMsgAarray(NPAL1230CMsg cMsg, NPAL1230SMsg sMsg) {
        // copy data from CMsg onto SMsg
        int pageShowFromNum = cMsg.xxPageShowFromNum.getValueInt() - 1;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            EZDMsg.copy(sMsg.A.no(pageShowFromNum + i), null, cMsg.A.no(i), null);
        }
    }

    // QC#21170 End
    private boolean chkEffTerm(String srcEffFromDt, String srcEffThruDt, String trgEffFromDt, String trgEffThruDt) {

        // START 2021/08/31 A.Marte [QC#59145, DEL]
        //if (!ZYPCommonFunc.hasValue(srcEffThruDt)) {
        //    srcEffFromDt = MIN_DATE;
        //}
        // END 2021/08/31 A.Marte [QC#59145, DEL]

        if (!ZYPCommonFunc.hasValue(srcEffThruDt)) {
            srcEffThruDt = MAX_DATE;
        }

        // START 2021/08/31 A.Marte [QC#59145, DEL]
        //if (!ZYPCommonFunc.hasValue(srcEffThruDt)) {
        //    trgEffFromDt = MIN_DATE;
        //}
        // END 2021/08/31 A.Marte [QC#59145, DEL]

        if (!ZYPCommonFunc.hasValue(trgEffThruDt)) {
            trgEffThruDt = MAX_DATE;
        }

        // START 2021/08/09 A.Marte [QC#58933, MOD]
        if ((ZYPDateUtil.compare(srcEffFromDt, trgEffFromDt) >= 0 && ZYPDateUtil.compare(srcEffFromDt, trgEffThruDt) <= 0)
                || (ZYPDateUtil.compare(srcEffThruDt, trgEffFromDt) >= 0 && ZYPDateUtil.compare(srcEffThruDt, trgEffThruDt) <= 0)
                || (ZYPDateUtil.compare(srcEffFromDt, trgEffFromDt) <= 0  && ZYPDateUtil.compare(srcEffThruDt, trgEffFromDt) >= 0)) {
        // END 2021/08/09 A.Marte [QC#58933, MOD]
            return true;
        }

        return false;
    }
}
