/**
 * <Pre>Copyright(c)2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLCL0090;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLCL0090.common.NLCL0090CommonLogic;
import business.blap.NLCL0090.constant.NLCL0090Constant;
import business.db.INVTYTMsg;
import business.db.MDSETMsg;
import business.db.RTL_SWHTMsg;

import com.canon.cusa.s21.common.NLX.NLXC011001.NLXLocationStatus;
import com.canon.cusa.s21.common.NLX.NLXC012001.NLXStockStatus;
import com.canon.cusa.s21.common.NMX.NMXC100001.NMXC100001EnableWH;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INVTY_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Item Change Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/06/25   Fujitsu         FXS)KF.Qian     Create          N/A
 * 2010/01/26   Fujitsu         M.Yamada        Update          Message ID Change
 * 2010/04/15   Fujitsu         S.Yoshida       Update          Def.5017
 * 2013/05/23   Fujitsu         F.Saito         Update          R-OM025 Inventory Transaction
 * 03/03/2016   CSAI            Y.Imazu         Update          CSA
 *</pre>
 */
public class NLCL0090BL02 extends S21BusinessHandler implements NLCL0090Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            // NLCL0090_INIT
            if ("NLCL0090_INIT".equals(screenAplID)) {
                doProcess_NLCL0090_INIT((NLCL0090CMsg) cMsg);

            // NLCL0090Scrn00_Add_Detail_From
            } else if ("NLCL0090Scrn00_Add_Detail_From".equals(screenAplID)) {
                doProcess_NLCL0090Scrn00_Add_Detail_From((NLCL0090CMsg) cMsg);

            // NLCL0090Scrn00_Add_Detail_To
            } else if ("NLCL0090Scrn00_Add_Detail_To".equals(screenAplID)) {
                doProcess_NLCL0090Scrn00_Add_Detail_To((NLCL0090CMsg) cMsg);

            // NLCL0090Scrn00_Search_MDSEInfo_From
            } else if ("NLCL0090Scrn00_Search_MDSEInfo_From".equals(screenAplID)) {
                doProcess_NLCL0090Scrn00_Search_MDSEInfo_From((NLCL0090CMsg) cMsg);

            // NLCL0090Scrn00_Search_MDSEInfo_To
            } else if ("NLCL0090Scrn00_Search_MDSEInfo_To".equals(screenAplID)) {
                doProcess_NLCL0090Scrn00_Search_MDSEInfo_To((NLCL0090CMsg) cMsg);

            // NLCL0090Scrn00_Search
            } else if ("NLCL0090Scrn00_Search".equals(screenAplID)) {
                doProcess_NLCL0090Scrn00_Search((NLCL0090CMsg) cMsg);

            // NLCL0090Scrn00_CMN_Reset
            } else if ("NLCL0090Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NLCL0090Scrn00_CMN_Reset((NLCL0090CMsg) cMsg);

            // NLCL0090Scrn00_CMN_Submit
            } else if ("NLCL0090Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NLCL0090Scrn00_CMN_Submit((NLCL0090CMsg) cMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NLCL0090CMsg
     */
    private void doProcess_NLCL0090_INIT(NLCL0090CMsg bizMsg) {

        NLCL0090CommonLogic.clearScrnItemValue(bizMsg);

        ZYPEZDItemValueSetter.setValue(bizMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(bizMsg.slsDt, ZYPDateUtil.getSalesDate());

        // Set Location Role Type Code List
        ZYPEZDItemValueSetter.setValue(bizMsg.xxLocRoleTpCdListTxt_P2, NMXC100001EnableWH.getLocRoleTpForPopup(bizMsg.glblCmpyCd.getValue(), BUSINESS_ID));

        String errStock = NLXStockStatus.exec(bizMsg.glblCmpyCd.getValue(), BUSINESS_ID, bizMsg.stkStsCd_H1, bizMsg.xxStkStsTxt_P1);

        if (ZYPCommonFunc.hasValue(errStock)) {

            bizMsg.setMessageInfo(NLCM0001E);
            return;
        }

        String errLoction = NLXLocationStatus.exec(bizMsg.glblCmpyCd.getValue(), BUSINESS_ID, bizMsg.locStsCd_H1, bizMsg.xxLocStsTxt_P1);

        if (ZYPCommonFunc.hasValue(errLoction)) {

            bizMsg.setMessageInfo(NLCM0001E);
            return;
        }

        bizMsg.locStsCd.setValue(LOC_STS.DC_STOCK);
        bizMsg.stkStsCd.setValue(STK_STS.GOOD);

        if (ZYPCommonFunc.hasValue(bizMsg.invtyOrdNum_BK)) {

            searchInvtyOrd(bizMsg);
        }
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NLCL0090CMsg
     */
    private void doProcess_NLCL0090Scrn00_Add_Detail_From(NLCL0090CMsg bizMsg) {

        if (!NLCL0090CommonLogic.isLocCdCheck(bizMsg, getUserProfileService(), getContextUserInfo())) {

            return;
        }

        if (!NLCL0090CommonLogic.findAvalInvtyAppId(bizMsg)) {

            return;
        }

        if (!NLCL0090CommonLogic.checkMdseAddFrom(bizMsg)) {

            return;
        }

        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.shpgSerTakeFlg_HF.getValue()) && MAXLINE < (bizMsg.A.getValidCount() + bizMsg.invtyQty_HF.getValueInt())) {

            bizMsg.invtyQty_HF.setErrorInfo(1, NLCM0025E, new String[]{MAXLENGTH});
            bizMsg.setMessageInfo(ZZM9037E);
            return;
        }

        if (!NLCL0090CommonLogic.findInvtyInfoForRecChk(bizMsg, bizMsg.mdseCd_HF, bizMsg.invtyAvalQty_HF, bizMsg.invtyQty_HF)) {

            return;
        }

        if (ZYPCommonFunc.hasValue(bizMsg.serNum_HF) && !ZYPConstant.FLG_ON_Y.equals(bizMsg.shpgSerTakeFlg_HF.getValue())) {

            bizMsg.serNum_HF.clear();
        }

        if (ZYPCommonFunc.hasValue(bizMsg.serNum_HF) && ZYPConstant.FLG_ON_Y.equals(bizMsg.instlBaseCtrlFlg_HF.getValue())
                && !NLCL0090CommonLogic.checkSerialAddFrom(bizMsg, -1)) {

            return;
        }

        int length = bizMsg.A.getValidCount();
        int lineNum = length;

        if (length > 0) {

            lineNum = Integer.parseInt(bizMsg.A.no(length - 1).invtyOrdLineNum_DF.getValue());
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(length).invtyOrdLineNum_DF, String.valueOf(lineNum + 1));
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(length).mdseCd_DF, bizMsg.mdseCd_HF.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(length).mdseDescShortTxt_DF, bizMsg.mdseDescShortTxt_HF.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(length).invtyQty_DF, bizMsg.invtyQty_HF.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(length).invtyAvalQty_DF, bizMsg.invtyAvalQty_HF.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(length).serNum_DF, bizMsg.serNum_HF.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(length).svcMachMstrPk_DF, bizMsg.svcMachMstrPk_HF.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(length).svcConfigMstrPk_DF, bizMsg.svcConfigMstrPk_HF.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(length).shpgSerTakeFlg_DF, bizMsg.shpgSerTakeFlg_HF.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(length).instlBaseCtrlFlg_DF, bizMsg.instlBaseCtrlFlg_HF.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(length).dispFlg_DF, ZYPConstant.FLG_ON_Y);
        length++;
        lineNum++;

        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.shpgSerTakeFlg_HF.getValue())) {

            for (int i = 1; i < bizMsg.invtyQty_HF.getValueInt(); i++) {

                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(length).invtyOrdLineNum_DF, String.valueOf(lineNum));
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(length).mdseCd_DF, bizMsg.mdseCd_HF.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(length).mdseDescShortTxt_DF, bizMsg.mdseDescShortTxt_HF.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(length).invtyQty_DF, bizMsg.invtyQty_HF.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(length).invtyAvalQty_DF, bizMsg.invtyAvalQty_HF.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(length).shpgSerTakeFlg_DF, bizMsg.shpgSerTakeFlg_HF.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(length).instlBaseCtrlFlg_DF, bizMsg.instlBaseCtrlFlg_HF.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.A.no(length).dispFlg_DF, ZYPConstant.FLG_OFF_N);
                length++;
            }
        }

        bizMsg.A.setValidCount(length);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLCL0090Scrn00_Add_Detail_To(NLCL0090CMsg bizMsg) {

        if (!NLCL0090CommonLogic.isLocCdCheck(bizMsg, getUserProfileService(), getContextUserInfo())) {

            return;
        }

        if (!NLCL0090CommonLogic.findAvalInvtyAppId(bizMsg)) {

            return;
        }

        if (!NLCL0090CommonLogic.checkMdseAddTo(bizMsg)) {

            return;
        }

        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.shpgSerTakeFlg_HT.getValue()) && MAXLINE < (bizMsg.B.getValidCount() + bizMsg.invtyQty_HT.getValueInt())) {

            bizMsg.invtyQty_HT.setErrorInfo(1, NLCM0025E, new String[]{MAXLENGTH});
            bizMsg.setMessageInfo(ZZM9037E);
            return;
        }

        if (ZYPCommonFunc.hasValue(bizMsg.serNum_HT) && !ZYPConstant.FLG_ON_Y.equals(bizMsg.shpgSerTakeFlg_HT.getValue())) {

            bizMsg.serNum_HF.clear();
        }

        if (ZYPCommonFunc.hasValue(bizMsg.serNum_HT) && ZYPConstant.FLG_ON_Y.equals(bizMsg.instlBaseCtrlFlg_HT.getValue())
                && !NLCL0090CommonLogic.checkSerialAddTo(bizMsg, bizMsg.mdseCd_HT, bizMsg.serNum_HT)) {

            return;
        }

        MDSETMsg mdseTMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, bizMsg.glblCmpyCd.getValue());
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, bizMsg.mdseCd_HT.getValue());
        mdseTMsg = (MDSETMsg) EZDTBLAccessor.findByKey(mdseTMsg);

        if (mdseTMsg == null) {

            bizMsg.mdseCd_HT.setErrorInfo(1, NLZM2278E, new String[] {"Item Number" });
            bizMsg.setMessageInfo(ZZM9037E);
            return;
        }

//        if (checkProduct(bizMsg, mdseTMsg)) {
//
//            return;
//        }

        int length = bizMsg.B.getValidCount();
        int lineNum = length;

        if (length > 0) {

            lineNum = Integer.parseInt(bizMsg.B.no(length - 1).invtyOrdLineNum_DT.getValue());
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(length).invtyOrdLineNum_DT, String.valueOf(lineNum + 1));
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(length).mdseCd_DT, bizMsg.mdseCd_HT.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(length).mdseDescShortTxt_DT, bizMsg.mdseDescShortTxt_HT.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(length).invtyQty_DT, bizMsg.invtyQty_HT.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(length).serNum_DT, bizMsg.serNum_HT.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(length).svcMachMstrPk_DT, bizMsg.svcMachMstrPk_HT.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(length).shpgSerTakeFlg_DT, bizMsg.shpgSerTakeFlg_HT.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(length).instlBaseCtrlFlg_DT, bizMsg.instlBaseCtrlFlg_HT.getValue());
        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(length).dispFlg_DT, ZYPConstant.FLG_ON_Y);
        length++;
        lineNum++;

        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.shpgSerTakeFlg_HT.getValue())) {

            for (int i = 1; i < bizMsg.invtyQty_HT.getValueInt(); i++) {

                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(length).invtyOrdLineNum_DT, String.valueOf(lineNum));
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(length).mdseCd_DT, bizMsg.mdseCd_HT.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(length).mdseDescShortTxt_DT, bizMsg.mdseDescShortTxt_HT.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(length).invtyQty_DT, bizMsg.invtyQty_HT.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(length).shpgSerTakeFlg_DT, bizMsg.shpgSerTakeFlg_HT.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(length).instlBaseCtrlFlg_DT, bizMsg.instlBaseCtrlFlg_HT.getValue());
                ZYPEZDItemValueSetter.setValue(bizMsg.B.no(length).dispFlg_DT, ZYPConstant.FLG_OFF_N);
                length++;
            }
        }

        bizMsg.B.setValidCount(length);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NLCL0090CMsg
     */
    private void doProcess_NLCL0090Scrn00_Search_MDSEInfo_From(NLCL0090CMsg bizMsg) {

        if (NLCL0090CommonLogic.checkMdseAddFrom(bizMsg)) {

            RTL_SWHTMsg rtlSwhTMsg = NLCL0090CommonLogic.getInvtyLoc(bizMsg);

            if (rtlSwhTMsg != null) {

                ZYPEZDItemValueSetter.setValue(bizMsg.invtyLocCd, rtlSwhTMsg.invtyLocCd.getValue());

                if (ZYPCommonFunc.hasValue(bizMsg.locStsCd) && ZYPCommonFunc.hasValue(bizMsg.stkStsCd)) {

                    INVTYTMsg invtyTMsg = NLCL0090CommonLogic.getInvty(bizMsg, bizMsg.mdseCd_HF.getValue());

                    if (invtyTMsg == null || !ZYPCommonFunc.hasValue(invtyTMsg.invtyAvalQty)) {

                        ZYPEZDItemValueSetter.setValue(bizMsg.invtyAvalQty_HF, BigDecimal.ZERO);

                    } else {

                        ZYPEZDItemValueSetter.setValue(bizMsg.invtyAvalQty_HF, invtyTMsg.invtyAvalQty.getValue());
                    }
                }
            }
        }
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NLCL0090CMsg
     */
    private void doProcess_NLCL0090Scrn00_Search_MDSEInfo_To(NLCL0090CMsg bizMsg) {

        NLCL0090CommonLogic.checkMdseAddTo(bizMsg);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NLCL0090CMsg
     */
    private void doProcess_NLCL0090Scrn00_Search(NLCL0090CMsg bizMsg) {

        ZYPEZDItemValueSetter.setValue(bizMsg.invtyOrdNum_BK, bizMsg.invtyOrdNum.getValue());
        doProcess_NLCL0090_INIT(bizMsg);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param bizMsg NLCL0090CMsg
     */
    private void doProcess_NLCL0090Scrn00_CMN_Reset(NLCL0090CMsg bizMsg) {

        ZYPEZDItemValueSetter.setValue(bizMsg.invtyOrdNum_BK, bizMsg.invtyOrdNum_IN.getValue());
        doProcess_NLCL0090_INIT(bizMsg);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg NLCL0090CMsg
     */
    private void doProcess_NLCL0090Scrn00_CMN_Submit(NLCL0090CMsg bizMsg) {

        searchInvtyOrd(bizMsg);
        ZYPEZDItemValueSetter.setValue(bizMsg.submtFlg, ZYPConstant.FLG_ON_Y);
    }

    /**
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg NLCL0090CMsg
     */
    private void searchInvtyOrd(NLCL0090CMsg bizMsg) {

        ZYPEZDItemValueSetter.setValue(bizMsg.invtyOrdNum, bizMsg.invtyOrdNum_BK.getValue());

        S21SsmEZDResult ssmResult = NLCL0090Query.getInstance().searchInvtyOrd(bizMsg);

        if (ssmResult.isCodeNormal()) {

            ArrayList<Map<String, Object>> invtyOrdList = (ArrayList<Map<String, Object>>) ssmResult.getResultObject();

            if (invtyOrdList != null && !invtyOrdList.isEmpty()) {

                ArrayList<String> invtyOrdLineNumList = new ArrayList<String>();
                boolean firstLine = true;
                int indexA = 0;
                int indexB = 0;
                int dispLineB = 0;

                for (Map<String, Object> invtyOrdMap : invtyOrdList) {

                    if (firstLine) {

                        if (!INVTY_ORD_TP.ITEM_CHANGE.equals((String) invtyOrdMap.get("INVTY_ORD_TP_CD"))) {

                            bizMsg.invtyOrdNum.setErrorInfo(1, NLCM0020E);
                            bizMsg.setMessageInfo(NLCM0020E);
                            return;
                        }

                        ZYPEZDItemValueSetter.setValue(bizMsg.rtlWhCd, (String) invtyOrdMap.get("RTL_WH_CD"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.rtlSwhCd, (String) invtyOrdMap.get("RTL_SWH_CD"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.locNm_P1, (String) invtyOrdMap.get("RTL_WH_NM"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.locNm_P2, (String) invtyOrdMap.get("RTL_SWH_NM"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.locStsCd, (String) invtyOrdMap.get("LOC_STS_CD"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.stkStsCd, (String) invtyOrdMap.get("STK_STS_CD"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.firstInvtyOrdCmntTxt, (String) invtyOrdMap.get("FIRST_INVTY_ORD_CMNT_TXT"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.scdInvtyOrdCmntTxt, (String) invtyOrdMap.get("SCD_INVTY_ORD_CMNT_TXT"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.thirdInvtyOrdCmntTxt, (String) invtyOrdMap.get("THIRD_INVTY_ORD_CMNT_TXT"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.invtyOrdStsDescTxt, (String) invtyOrdMap.get("INVTY_ORD_STS_DESC_TXT"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.soNum, (String) invtyOrdMap.get("SO_NUM"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.xxTsDsp19Txt_SB, (String) invtyOrdMap.get("INVTY_ORD_SUBMT_TS"));
                        ZYPEZDItemValueSetter.setValue(bizMsg.xxTsDsp19Txt_CL, (String) invtyOrdMap.get("INVTY_ORD_CLOSE_OR_CANC_TS"));

                        firstLine = false;
                    }

                    // From
                    if (ZYPConstant.FLG_ON_Y.equals((String) invtyOrdMap.get("FROM_LINE_FLG"))) {

                        if (invtyOrdLineNumList.isEmpty() || !invtyOrdLineNumList.contains((String) invtyOrdMap.get("INVTY_ORD_LINE_NUM"))) {

                            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(indexA).invtyOrdLineNum_DF, (String) invtyOrdMap.get("INVTY_ORD_LINE_NUM"));
                            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(indexA).mdseCd_DF, (String) invtyOrdMap.get("MDSE_CD"));
                            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(indexA).mdseDescShortTxt_DF, (String) invtyOrdMap.get("MDSE_DESC_SHORT_TXT"));
                            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(indexA).invtyQty_DF, (BigDecimal) invtyOrdMap.get("INVTY_QTY"));
                            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(indexA).invtyAvalQty_DF, (BigDecimal) invtyOrdMap.get("INVTY_AVAL_QTY"));
                            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(indexA).dispFlg_DF, ZYPConstant.FLG_ON_Y);

                            invtyOrdLineNumList.add((String) invtyOrdMap.get("INVTY_ORD_LINE_NUM"));

                        } else {

                            ZYPEZDItemValueSetter.setValue(bizMsg.A.no(indexA).dispFlg_DF, ZYPConstant.FLG_OFF_N);
                        }

                        ZYPEZDItemValueSetter.setValue(bizMsg.A.no(indexA).serNum_DF, (String) invtyOrdMap.get("SER_NUM"));
                        indexA++;

                    // To
                    } else {

                        if (invtyOrdLineNumList.isEmpty() || !invtyOrdLineNumList.contains((String) invtyOrdMap.get("INVTY_ORD_LINE_NUM"))) {

                            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(indexB).invtyOrdLineNum_DT, Integer.toString(dispLineB + 1));
                            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(indexB).mdseCd_DT, (String) invtyOrdMap.get("MDSE_CD"));
                            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(indexB).mdseDescShortTxt_DT, (String) invtyOrdMap.get("MDSE_DESC_SHORT_TXT"));
                            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(indexB).invtyQty_DT, (BigDecimal) invtyOrdMap.get("INVTY_QTY"));
                            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(indexB).dispFlg_DT, ZYPConstant.FLG_ON_Y);

                            invtyOrdLineNumList.add((String) invtyOrdMap.get("INVTY_ORD_LINE_NUM"));
                            dispLineB++;

                        } else {

                            ZYPEZDItemValueSetter.setValue(bizMsg.B.no(indexB).dispFlg_DT, ZYPConstant.FLG_OFF_N);
                        }

                        ZYPEZDItemValueSetter.setValue(bizMsg.B.no(indexB).serNum_DT, (String) invtyOrdMap.get("SER_NUM"));
                        indexB++;
                    }
                }

                bizMsg.A.setValidCount(indexA);
                bizMsg.B.setValidCount(indexB);
                ZYPEZDItemValueSetter.setValue(bizMsg.xxOrdDplyFlg, ZYPConstant.FLG_ON_Y);

                bizMsg.setMessageInfo(ZZZM9003I, new String[]{"Search"});
                return;
            }
        }

        bizMsg.setMessageInfo(NZZM0000E);
    }

//    /**
//     * <dd>The method explanation: The business peculiarity
//     * processing is executed.
//     * @param bizMsg Business Component Interface Message
//     * @return boolean
//     */
//    private boolean checkProduct(NLCL0090CMsg bizMsg, MDSETMsg mdseMsg) {
//
//        S21DataSecurityProfile dsProfile = getUserProfileService().getDataSecurityProfileFor(BUSINESS_ID);
//        List<S21DataSecurityAttributeData> dsAttrList = dsProfile.getDataSecurityAttributeDataListFor("PCS");
//
//        String msg = NLXProdLineCheck.exec(mdseMsg, dsAttrList);
//
//        if (ZYPCommonFunc.hasValue(msg)) {
//
//            bizMsg.mdseCd_HT.setErrorInfo(1, msg);
//            bizMsg.setMessageInfo(ZZM9037E);
//
//            return false;
//        }
//
//        return true;
//    }
}
