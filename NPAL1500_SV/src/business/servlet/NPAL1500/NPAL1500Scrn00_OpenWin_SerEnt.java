/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1500;

import static business.servlet.NPAL1500.constant.NPAL1500Constant.BIZ_ID;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.NPAM1329E;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.NPAM0046E;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1500.NPAL1500CMsg;
import business.servlet.NPAL1500.common.NPAL1500CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_PO_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PO_QLFY;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/18/2015   CITS            N Akaishi       Create          n/a
 * 05/05/2016   CSAI            K.Lee           Update          QC#7896
 * 09/14/2016   CITS            S.Endo          Update          QC#8759
 * 09/30/2016   CITS            S.Endo          Update          QC#8759
 * 08/07/2018   CITS            K.Ogino         Update          QC#27024
 * 10/09/2018   CITS            K.Kameoka       Update          QC#28463
 *</pre>
 */
public class NPAL1500Scrn00_OpenWin_SerEnt extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;

        int idx = this.getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum, new BigDecimal(idx));
        scrnMsg.addCheckItem(scrnMsg.A.no(idx).poLineTpCd_A1);
        scrnMsg.addCheckItem(scrnMsg.A.no(idx).mdseCd_A1);
        scrnMsg.addCheckItem(scrnMsg.A.no(idx).entDealNetUnitPrcAmt_A1);
        scrnMsg.addCheckItem(scrnMsg.A.no(idx).poDispQty_A1);
        if (scrnMsg.dsPoTpCd.getValue().equals(DS_PO_TP.BUYBACK_PO)) {
            scrnMsg.addCheckItem(scrnMsg.srcRtlWhCd_SC);
            scrnMsg.addCheckItem(scrnMsg.A.no(idx).srcRtlSwhCd_A1);
        } else {
            scrnMsg.addCheckItem(scrnMsg.destRtlWhCd_DS);
            scrnMsg.addCheckItem(scrnMsg.A.no(idx).destRtlSwhCd_A1);
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).mdseCd_A1)) {
            scrnMsg.A.no(idx).mdseCd_A1.setErrorInfo(1, NPAM1329E, new String[]{"Item#", " Please select."});
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).poLineTpCd_A1)) {
            scrnMsg.A.no(idx).poLineTpCd_A1.setErrorInfo(1, NPAM1329E, new String[]{"Line Type", " Please select."});
        }
        if (scrnMsg.A.no(idx).poDispQty_A1.getValueInt() < 1) {
            scrnMsg.A.no(idx).poDispQty_A1.setErrorInfo(1, NPAM0046E, new String[]{"Order Qty"});
        }
        if (scrnMsg.dsPoTpCd.getValue().equals(DS_PO_TP.BUYBACK_PO)) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.srcRtlWhCd_SC)) {
                scrnMsg.srcRtlWhCd_SC.setErrorInfo(1, NPAM1329E, new String[]{"Source WH", " Please select."});
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).srcRtlSwhCd_A1)) {
                scrnMsg.A.no(idx).srcRtlSwhCd_A1.setErrorInfo(1, NPAM1329E, new String[] {"Source SWH", " Please select."});
            }
        } else {
            if (!ZYPCommonFunc.hasValue(scrnMsg.destRtlWhCd_DS)) {
                scrnMsg.destRtlWhCd_DS.setErrorInfo(1, NPAM1329E, new String[]{"Destination WH", " Please select."});
            }
            //QC#28463 Add Start
            if (!PO_QLFY.DROPSHIP.equals(scrnMsg.poQlfyCd.getValue())) {
            //QC#28463 Add End
                if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).destRtlSwhCd_A1)) {
                    scrnMsg.A.no(idx).destRtlSwhCd_A1.setErrorInfo(1, NPAM1329E, new String[] {"Destination SWH", " Please select." });
                }
            }
        }
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;

        NPAL1500CMsg bizMsg = new NPAL1500CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;
        NPAL1500CMsg bizMsg  = (NPAL1500CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        int idx = this.getButtonSelectNumber();
        scrnMsg.addCheckItem(scrnMsg.A.no(idx).poLineTpCd_A1);
        scrnMsg.addCheckItem(scrnMsg.A.no(idx).mdseCd_A1);
        scrnMsg.putErrorScreen();

        // Making of subscreen delivery information. Mod QC#27024
        Object[] params = NPAL1500CommonLogic.setParamForSeialNumEntryPopup(scrnMsg, bizMsg, idx);

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }

        // Subscreen transition
        setArgForSubScreen(params);
    }
}
