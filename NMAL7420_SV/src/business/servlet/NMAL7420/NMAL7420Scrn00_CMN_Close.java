/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7420;

import static business.servlet.NMAL7420.constant.NMAL7420Constant.COMMA;
import static business.servlet.NMAL7420.constant.NMAL7420Constant.MAX_INPUT_DATA_CNT;
import static business.servlet.NMAL7420.constant.NMAL7420Constant.NMAM0042E;
import static business.servlet.NMAL7420.constant.NMAL7420Constant.NMAM8200E;
import static business.servlet.NMAL7420.constant.NMAL7420Constant.NMAM8535E;
import static business.servlet.NMAL7420.constant.NMAL7420Constant.NMAM8579E;
import static business.servlet.NMAL7420.constant.NMAL7420Constant.NMAM8696E;

import java.util.regex.Pattern;

import parts.common.EZDBDateItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.db.MDSETMsg;
import business.db.PRC_GRPTMsg;
import business.db.SELL_TO_CUSTTMsg;
import business.servlet.NMAL7420.common.NMAL7420CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/08/07   Fujitsu         K.Ishizuka         Create          N/A
 * 2018/04/24   Fujitsu         M.Ohno             Update          QC#22569
 * 2018/12/04   Fujitsu         Hd.Sugawara     Update          QC#29321
 * 2019/01/08   Fujitsu         Hd.Sugawara     Update          QC#29751
 *</pre>
 */
public class NMAL7420Scrn00_CMN_Close extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkParams(bMsg);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7420BMsg scrnMsg = (NMAL7420BMsg) bMsg;
        Object[] arg = (Object[]) getArgForSubScreen();

        NMAL7420CommonLogic.setOutputParam(scrnMsg, arg);

    }

    private boolean isNumber(String s) {
        return Pattern.compile("^[0-9]+$").matcher(s).find();
    }

    private boolean checkNum(EZDBStringItem ezStr) {
        if (ZYPCommonFunc.hasValue(ezStr.getValue())) {
            if (!isNumber(ezStr.getValue())) {
                ezStr.setErrorInfo(1, NMAM8535E, new String[] {"Input Value" });
                return false;
            }
            return true;
        }
        return false;
    }

    private void checkDate(EZDBDateItem dateFr, EZDBDateItem dateTo, String message) {
        if (ZYPCommonFunc.hasValue(dateFr) && ZYPCommonFunc.hasValue(dateTo) // 
                && ZYPDateUtil.compare(dateFr.getValue(), dateTo.getValue()) > 0) {
            dateFr.setErrorInfo(1, NMAM8200E, new String[] {message });
            dateTo.setErrorInfo(1, NMAM8200E, new String[] {message });
        }

    }

    private void checkValue(EZDBStringItem valueFr, EZDBStringItem valueTo) {
        if (ZYPCommonFunc.hasValue(valueFr) && ZYPCommonFunc.hasValue(valueTo) // 
                && checkNum(valueFr) && checkNum(valueTo)) {
            if (Integer.valueOf(valueFr.getValue()) > Integer.valueOf(valueTo.getValue())) {
                valueFr.setErrorInfo(1, NMAM0042E, new String[] {"Right Input", "Left" });
                valueTo.setErrorInfo(1, NMAM0042E, new String[] {"Right Input", "Left" });
            }
        }
    }

    private void checkParams(EZDBMsg bMsg) {
        NMAL7420BMsg scrnMsg = (NMAL7420BMsg) bMsg;

        checkNum(scrnMsg.prcRuleCondFromTxt_AF);
        checkNum(scrnMsg.prcRuleCondFromTxt_AT);
        checkNum(scrnMsg.prcRuleCondThruTxt_AF);
        checkNum(scrnMsg.prcRuleCondThruTxt_AT);
        checkNum(scrnMsg.prcRuleCondFromTxt_BF);
        checkNum(scrnMsg.prcRuleCondFromTxt_BT);
        checkNum(scrnMsg.prcRuleCondThruTxt_BF);
        checkNum(scrnMsg.prcRuleCondThruTxt_BT);
        checkNum(scrnMsg.prcRuleCondFromTxt_CF);
        checkNum(scrnMsg.prcRuleCondFromTxt_CT);
        checkNum(scrnMsg.prcRuleCondThruTxt_CF);
        checkNum(scrnMsg.prcRuleCondThruTxt_CT);
        checkNum(scrnMsg.prcRuleCondFromTxt_DF);
        checkNum(scrnMsg.prcRuleCondFromTxt_DT);
        checkNum(scrnMsg.prcRuleCondThruTxt_DF);
        checkNum(scrnMsg.prcRuleCondThruTxt_DT);
        checkNum(scrnMsg.prcRuleCondFromTxt_EF);
        checkNum(scrnMsg.prcRuleCondFromTxt_ET);
        checkNum(scrnMsg.prcRuleCondThruTxt_EF);
        checkNum(scrnMsg.prcRuleCondThruTxt_ET);
        checkNum(scrnMsg.prcRuleCondFromTxt_FF);
        checkNum(scrnMsg.prcRuleCondFromTxt_FT);
        checkNum(scrnMsg.prcRuleCondThruTxt_FF);
        checkNum(scrnMsg.prcRuleCondThruTxt_FT);

        checkValue(scrnMsg.prcRuleCondFromTxt_AF, scrnMsg.prcRuleCondFromTxt_AT);
        checkValue(scrnMsg.prcRuleCondThruTxt_AF, scrnMsg.prcRuleCondThruTxt_AT);
        checkValue(scrnMsg.prcRuleCondFromTxt_BF, scrnMsg.prcRuleCondFromTxt_BT);
        checkValue(scrnMsg.prcRuleCondThruTxt_BF, scrnMsg.prcRuleCondThruTxt_BT);
        checkValue(scrnMsg.prcRuleCondFromTxt_CF, scrnMsg.prcRuleCondFromTxt_CT);
        checkValue(scrnMsg.prcRuleCondThruTxt_CF, scrnMsg.prcRuleCondThruTxt_CT);
        checkValue(scrnMsg.prcRuleCondFromTxt_DF, scrnMsg.prcRuleCondFromTxt_DT);
        checkValue(scrnMsg.prcRuleCondThruTxt_DF, scrnMsg.prcRuleCondThruTxt_DT);
        checkValue(scrnMsg.prcRuleCondFromTxt_EF, scrnMsg.prcRuleCondFromTxt_ET);
        checkValue(scrnMsg.prcRuleCondThruTxt_EF, scrnMsg.prcRuleCondThruTxt_ET);
        checkValue(scrnMsg.prcRuleCondFromTxt_FF, scrnMsg.prcRuleCondFromTxt_FT);
        checkValue(scrnMsg.prcRuleCondThruTxt_FF, scrnMsg.prcRuleCondThruTxt_FT);

        checkDate(scrnMsg.xxSvcCallDt_FF, scrnMsg.xxSvcCallDt_FT, "Call Date From");
        checkDate(scrnMsg.xxSvcCallDt_TF, scrnMsg.xxSvcCallDt_TT, "Call Date To");
        checkDate(scrnMsg.prcDt_FF, scrnMsg.prcDt_FT, "Pricing Date From");
        checkDate(scrnMsg.prcDt_TF, scrnMsg.prcDt_TT, "Pricing Date To");
        checkDate(scrnMsg.xxRqstDt_FF, scrnMsg.xxRqstDt_FT, "Request Date From");
        checkDate(scrnMsg.xxRqstDt_TF, scrnMsg.xxRqstDt_TT, "CRequestall Date To");

        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_04);
        scrnMsg.addCheckItem(scrnMsg.csmpNumCmntTxt_04);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_05);
        scrnMsg.addCheckItem(scrnMsg.prcGrpNm_05);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_06);
        scrnMsg.addCheckItem(scrnMsg.prodCtrlNm_06);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_07);
        scrnMsg.addCheckItem(scrnMsg.prodCtrlNm_07);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_08);
        scrnMsg.addCheckItem(scrnMsg.coaMdseTpDescTxt_08);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_09);
        scrnMsg.addCheckItem(scrnMsg.coaProdDescTxt_09);
        // Mod Start 2018/12/04 QC#29321
        //scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_10);
        //scrnMsg.addCheckItem(scrnMsg.mdseDescShortTxt_10);
        scrnMsg.addCheckItem(scrnMsg.xxPrcQlfyValSrchTxt_J1);
        scrnMsg.addCheckItem(scrnMsg.xxPrcQlfyValSrchTxt_J2);
        scrnMsg.addCheckItem(scrnMsg.xxPrcQlfyValSrchTxt_J3);
        // Mod End 2018/12/04 QC#29321
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_11);
        scrnMsg.addCheckItem(scrnMsg.dsOrdCatgDescTxt_11);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_12);
        scrnMsg.addCheckItem(scrnMsg.dsOrdTpDescTxt_12);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_13);
        scrnMsg.addCheckItem(scrnMsg.lineBizTpDescTxt_13);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_14);
        scrnMsg.addCheckItem(scrnMsg.prcGrpNm_14);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_AF);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_AT);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondThruTxt_AF);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondThruTxt_AT);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_16);
        scrnMsg.addCheckItem(scrnMsg.billToAcctNm_16);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_17);
        scrnMsg.addCheckItem(scrnMsg.coaChDescTxt_17);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_18);
        scrnMsg.addCheckItem(scrnMsg.dsAcctClsDescTxt_18);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_19);
        scrnMsg.addCheckItem(scrnMsg.coaBrDescTxt_19);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_20);
        scrnMsg.addCheckItem(scrnMsg.svcCallTpDescTxt_20);
        scrnMsg.addCheckItem(scrnMsg.xxSvcCallDt_FF);
        scrnMsg.addCheckItem(scrnMsg.xxSvcCallDt_FT);
        scrnMsg.addCheckItem(scrnMsg.xxSvcCallDt_TF);
        scrnMsg.addCheckItem(scrnMsg.xxSvcCallDt_TT);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_BF);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_BT);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondThruTxt_BF);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondThruTxt_BT);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_CF);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_CT);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondThruTxt_CF);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondThruTxt_CT);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_25);
        scrnMsg.addCheckItem(scrnMsg.dsOrdLineCatgDescTxt_25);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_DF);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_DT);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondThruTxt_DF);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondThruTxt_DT);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_27);
        scrnMsg.addCheckItem(scrnMsg.mktMdlDescTxt_27);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_28);
        scrnMsg.addCheckItem(scrnMsg.mktMdlSegDescTxt_28);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_29);
        scrnMsg.addCheckItem(scrnMsg.cpoSrcTpDescTxt_29);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_EF);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_ET);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondThruTxt_EF);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondThruTxt_ET);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_31);
        scrnMsg.addCheckItem(scrnMsg.dsPmtMethDescTxt_31);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_32);
        scrnMsg.addCheckItem(scrnMsg.prcCatgNm_32);
        scrnMsg.addCheckItem(scrnMsg.prcDt_FF);
        scrnMsg.addCheckItem(scrnMsg.prcDt_FT);
        scrnMsg.addCheckItem(scrnMsg.prcDt_TF);
        scrnMsg.addCheckItem(scrnMsg.prcDt_TT);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_34);
        scrnMsg.addCheckItem(scrnMsg.prodCtrlNm_34);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_35);
        scrnMsg.addCheckItem(scrnMsg.prodCtrlNm_35);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_36);
        scrnMsg.addCheckItem(scrnMsg.prodCtrlNm_36);
        scrnMsg.addCheckItem(scrnMsg.xxRqstDt_FF);
        scrnMsg.addCheckItem(scrnMsg.xxRqstDt_FT);
        scrnMsg.addCheckItem(scrnMsg.xxRqstDt_TF);
        scrnMsg.addCheckItem(scrnMsg.xxRqstDt_TT);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_38);
        scrnMsg.addCheckItem(scrnMsg.rtrnRsnDescTxt_38);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_39);
        scrnMsg.addCheckItem(scrnMsg.shpgSvcLvlDescTxt_39);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_40);
        scrnMsg.addCheckItem(scrnMsg.mdlNm_40);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_41);
        scrnMsg.addCheckItem(scrnMsg.prcSvcZoneDescTxt_41);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_42);
        scrnMsg.addCheckItem(scrnMsg.dsAcctClsDescTxt_42);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_44);
        scrnMsg.addCheckItem(scrnMsg.spclHdlgTpDescTxt_44);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_FF);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_FT);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondThruTxt_FF);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondThruTxt_FT);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_46);
        scrnMsg.addCheckItem(scrnMsg.coaExtnDescTxt_46);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_48);
        scrnMsg.addCheckItem(scrnMsg.frtCondDescTxt_48);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_49);
        scrnMsg.addCheckItem(scrnMsg.fill40Txt_49);
        // Mod Start 2019/01/08 QC#29751
        //scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_53);
        scrnMsg.addCheckItem(scrnMsg.xxPrcQlfyValSrchTxt_53);
        // Mod End 2019/01/08 QC#29751
        scrnMsg.addCheckItem(scrnMsg.prcGrpNm_53);
        // Mod Start 2018/12/04 QC#29321
        //scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_54);
        //scrnMsg.addCheckItem(scrnMsg.dsAcctNm_54);
        scrnMsg.addCheckItem(scrnMsg.xxPrcQlfyValSrchTxt_K1);
        scrnMsg.addCheckItem(scrnMsg.xxPrcQlfyValSrchTxt_K2);
        // Mod End 2018/12/04 QC#29321
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_55);
        scrnMsg.addCheckItem(scrnMsg.coaChDescTxt_55);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_56);
        scrnMsg.addCheckItem(scrnMsg.dsAcctClsDescTxt_56);
        // 2018/04/19 QC#22569 add start
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_59);
        scrnMsg.addCheckItem(scrnMsg.slsMatGrpDescTxt_59);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_60);
        scrnMsg.addCheckItem(scrnMsg.slsMatGrpDescTxt_60);
        scrnMsg.addCheckItem(scrnMsg.prcRuleCondFromTxt_61);
        scrnMsg.addCheckItem(scrnMsg.slsMatGrpDescTxt_61);
        // 2018/04/19 QC#22569 add end
        scrnMsg.addCheckItem(scrnMsg.prcFmlaPk);
        scrnMsg.addCheckItem(scrnMsg.prcFmlaNm);
        scrnMsg.addCheckItem(scrnMsg.prcRuleDtlRate);
        scrnMsg.addCheckItem(scrnMsg.prcRuleDtlTxt);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_H1);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_H2);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_H1);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_H2);
        scrnMsg.addCheckItem(scrnMsg.cratDt_H1);
        scrnMsg.addCheckItem(scrnMsg.cratDt_H2);
        scrnMsg.addCheckItem(scrnMsg.xxFullNm_H1);
        scrnMsg.addCheckItem(scrnMsg.lastUpdDt_H1);
        scrnMsg.addCheckItem(scrnMsg.lastUpdDt_H2);
        scrnMsg.addCheckItem(scrnMsg.xxFullNm_H2);

        // Add Start 2018/12/04 QC#29321
        String[] strArray;
        SELL_TO_CUSTTMsg sellToCustTmsg = new SELL_TO_CUSTTMsg();
        MDSETMsg mdseTmsg = new MDSETMsg();
        // Add Start 2019/01/08 QC#29751
        PRC_GRPTMsg prcGrpTmsg = new PRC_GRPTMsg();
        // Add End 2019/01/08 QC#29751

        // Item Code(Item Number)
        if (ZYPCommonFunc.hasValue(scrnMsg.xxPrcQlfyValSrchTxt_J1)) {
            strArray = S21StringUtil.toStringArray(scrnMsg.xxPrcQlfyValSrchTxt_J1.getValue(), COMMA);
            if (MAX_INPUT_DATA_CNT < strArray.length) {
                scrnMsg.xxPrcQlfyValSrchTxt_J1.setErrorInfo(1, NMAM8696E, new String[] {scrnMsg.xxPrcQlfyValSrchTxt_J1.getNameForMessage(), String.valueOf(MAX_INPUT_DATA_CNT) });
            }

            int mdseCdLength = mdseTmsg.getAttr("mdseCd").getDigit();
            for (String element : strArray) {
                if (ZYPCommonFunc.hasValue(element) && //
                        element.length() > mdseCdLength) {
                    scrnMsg.xxPrcQlfyValSrchTxt_J1.setErrorInfo(1, NMAM8579E, new String[] {scrnMsg.xxPrcQlfyValSrchTxt_J1.getNameForMessage() });
                    break;
                }
            }
        }

        // Item Code(Item Description)
        if (ZYPCommonFunc.hasValue(scrnMsg.xxPrcQlfyValSrchTxt_J2)) {
            strArray = S21StringUtil.toStringArray(scrnMsg.xxPrcQlfyValSrchTxt_J2.getValue(), COMMA);
            if (MAX_INPUT_DATA_CNT < strArray.length) {
                scrnMsg.xxPrcQlfyValSrchTxt_J2.setErrorInfo(1, NMAM8696E, new String[] {scrnMsg.xxPrcQlfyValSrchTxt_J2.getNameForMessage(), String.valueOf(MAX_INPUT_DATA_CNT) });
            }

            int mdseDescShortTxtLength = mdseTmsg.getAttr("mdseDescShortTxt").getDigit();
            for (String element : strArray) {
                if (ZYPCommonFunc.hasValue(element) && //
                        element.length() > mdseDescShortTxtLength) {
                    scrnMsg.xxPrcQlfyValSrchTxt_J2.setErrorInfo(1, NMAM8579E, new String[] {scrnMsg.xxPrcQlfyValSrchTxt_J2.getNameForMessage() });
                    break;
                }
            }
        }

        // Manufacture#
        if (ZYPCommonFunc.hasValue(scrnMsg.xxPrcQlfyValSrchTxt_J3)) {
            strArray = S21StringUtil.toStringArray(scrnMsg.xxPrcQlfyValSrchTxt_J3.getValue(), COMMA);
            if (MAX_INPUT_DATA_CNT < strArray.length) {
                scrnMsg.xxPrcQlfyValSrchTxt_J3.setErrorInfo(1, NMAM8696E, new String[] {scrnMsg.xxPrcQlfyValSrchTxt_J3.getNameForMessage(), String.valueOf(MAX_INPUT_DATA_CNT) });
            }

            int mnfItemCdLength = mdseTmsg.getAttr("mnfItemCd").getDigit();
            for (String element : strArray) {
                if (ZYPCommonFunc.hasValue(element) && //
                        element.length() > mnfItemCdLength) {
                    scrnMsg.xxPrcQlfyValSrchTxt_J3.setErrorInfo(1, NMAM8579E, new String[] {scrnMsg.xxPrcQlfyValSrchTxt_J3.getNameForMessage() });
                    break;
                }
            }
        }

        // Sold To (Acct#)(Account Number)
        if (ZYPCommonFunc.hasValue(scrnMsg.xxPrcQlfyValSrchTxt_K1)) {
            strArray = S21StringUtil.toStringArray(scrnMsg.xxPrcQlfyValSrchTxt_K1.getValue(), COMMA);
            if (MAX_INPUT_DATA_CNT < strArray.length) {
                scrnMsg.xxPrcQlfyValSrchTxt_K1.setErrorInfo(1, NMAM8696E, new String[] {scrnMsg.xxPrcQlfyValSrchTxt_K1.getNameForMessage(), String.valueOf(MAX_INPUT_DATA_CNT) });
            }

            int sellToCustCdLength = sellToCustTmsg.getAttr("sellToCustCd").getDigit();
            for (String element : strArray) {
                if (ZYPCommonFunc.hasValue(element) && //
                        element.length() > sellToCustCdLength) {
                    scrnMsg.xxPrcQlfyValSrchTxt_K1.setErrorInfo(1, NMAM8579E, new String[] {scrnMsg.xxPrcQlfyValSrchTxt_K1.getNameForMessage() });
                    break;
                }
            }
        }

        // Sold To (Acct#)(Account Name)
        if (ZYPCommonFunc.hasValue(scrnMsg.xxPrcQlfyValSrchTxt_K2)) {
            strArray = S21StringUtil.toStringArray(scrnMsg.xxPrcQlfyValSrchTxt_K2.getValue(), COMMA);
            if (MAX_INPUT_DATA_CNT < strArray.length) {
                scrnMsg.xxPrcQlfyValSrchTxt_K2.setErrorInfo(1, NMAM8696E, new String[] {scrnMsg.xxPrcQlfyValSrchTxt_K2.getNameForMessage(), String.valueOf(MAX_INPUT_DATA_CNT) });
            }

            int dsAcctNmLength = sellToCustTmsg.getAttr("dsAcctNm").getDigit();
            for (String element : strArray) {
                if (ZYPCommonFunc.hasValue(element) && //
                        element.length() > dsAcctNmLength) {
                    scrnMsg.xxPrcQlfyValSrchTxt_K2.setErrorInfo(1, NMAM8579E, new String[] {scrnMsg.xxPrcQlfyValSrchTxt_K2.getNameForMessage() });
                    break;
                }
            }
        }
        // Add End 2018/12/04 QC#29321

        // Add Start 2019/01/08 QC#29751
        // Customer Price Group(Sold To)
        if (ZYPCommonFunc.hasValue(scrnMsg.xxPrcQlfyValSrchTxt_53)) {
            strArray = S21StringUtil.toStringArray(scrnMsg.xxPrcQlfyValSrchTxt_53.getValue(), COMMA);
            if (MAX_INPUT_DATA_CNT < strArray.length) {
                scrnMsg.xxPrcQlfyValSrchTxt_53.setErrorInfo(1, NMAM8696E, new String[] {scrnMsg.xxPrcQlfyValSrchTxt_53.getNameForMessage(), String.valueOf(MAX_INPUT_DATA_CNT) });
            }

            int prcGrpPkLength = prcGrpTmsg.getAttr("prcGrpPk").getDigit();
            for (String element : strArray) {
                if (ZYPCommonFunc.hasValue(element) && //
                        element.length() > prcGrpPkLength) {
                    scrnMsg.xxPrcQlfyValSrchTxt_53.setErrorInfo(1, NMAM8579E, new String[] {scrnMsg.xxPrcQlfyValSrchTxt_53.getNameForMessage() });
                    break;
                }
            }
        }
        // Add End 2019/01/08 QC#29751

        scrnMsg.putErrorScreen();

    }
}
