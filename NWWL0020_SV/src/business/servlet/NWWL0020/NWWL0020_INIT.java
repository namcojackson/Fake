/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWWL0020;

import static business.servlet.NWWL0020.constant.NWWL0020Constant.BIZ_ID;
import static business.servlet.NWWL0020.constant.NWWL0020Constant.TAB_NAME_HEADER;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWWL0020.NWWL0020CMsg;
import business.servlet.NWWL0020.common.NWWL0020CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NWWL0020_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/28   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public class NWWL0020_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWWL0020BMsg scrnMsg = (NWWL0020BMsg) bMsg;
        NWWL0020CMsg bizMsg = new NWWL0020CMsg();

        // IN Parameter Get
        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null && params.length == 1) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.ntfyHdrId_H0, (String) params[0]);
        }

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWWL0020BMsg scrnMsg = (NWWL0020BMsg) bMsg;
        NWWL0020CMsg bizMsg = (NWWL0020CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (ZYPCommonFunc.hasValue(scrnMsg.effThruDt_H0) && scrnMsg.effThruDt_H0.getValue().compareTo(ZYPDateUtil.getSalesDate()) < 0) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyCtrlFlg, ZYPConstant.FLG_OFF_N);
        } else {
            NWWL0020CommonLogic.setControlFieldsByAuth(scrnMsg);
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlProtFlg, ZYPConstant.FLG_ON_Y);
        NWWL0020CommonLogic.setIntvalRadioValue(scrnMsg);
        scrnMsg.xxDplyTab.setValue(TAB_NAME_HEADER);

        NWWL0020CommonLogic.setScrnTm(scrnMsg);
        NWWL0020CommonLogic.initCmnBtnProp(this);
        NWWL0020CommonLogic.setControlFields(scrnMsg, this);
        NWWL0020CommonLogic.setControlFieldsRepeatChkBox(scrnMsg);
        NWWL0020CommonLogic.setControlFieldsActListProt(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.ntfyHdrNm_H0);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NWWL0020BMsg scrnMsg = (NWWL0020BMsg) bMsg;

        // Header
        scrnMsg.ntfyHdrNm_H0.setNameForMessage("Name");
        scrnMsg.ntfyHdrDescTxt_H0.setNameForMessage("Description");
        scrnMsg.ntfyBizAreaTpCd_SL.setNameForMessage("Business Area");
        scrnMsg.ntfySubAreaTpCd_SL.setNameForMessage("Sub Area");
        scrnMsg.effFromDt_H0.setNameForMessage("Start Date");
        scrnMsg.effThruDt_H0.setNameForMessage("End Date");
        scrnMsg.ntfyHdrActvFlg_H0.setNameForMessage("Enable");

        // Notification Periodic Detail
        scrnMsg.ntfyFreqTpCd_SL.setNameForMessage("Frequency");
        scrnMsg.ntfyRunDayListTxt_PD.setNameForMessage("Run Calender");
        scrnMsg.ntfyEomFlg_PD.setNameForMessage("End of Month");
        scrnMsg.ntfyStartHourMn_PD.setNameForMessage("Start Time");
        scrnMsg.ntfyEndHourMn_PD.setNameForMessage("End Time");
        scrnMsg.xxStartDplyTmTxt.setNameForMessage("Start Time");
        scrnMsg.xxEndDplyTmTxt.setNameForMessage("End Time");
        scrnMsg.ntfyIntvlAot_PD.setNameForMessage("Interval");
        scrnMsg.ntfyIntvlUomTpCd_SL.setNameForMessage("Interval Unit");
        scrnMsg.histDaysAot_PD.setNameForMessage("Maintain History For(Days)");

        // SQL
        scrnMsg.xxNtfySqlTxt.setNameForMessage("Notification Selection(SQL)");

        // Action Detail
        scrnMsg.ntfyActNm.setNameForMessage("Action Name");
        scrnMsg.ntfyActDescTxt.setNameForMessage("Description");
        scrnMsg.ntfyActTpCd_SL.setNameForMessage("Action Type");
        scrnMsg.ntfyOtptTpCd_SL.setNameForMessage("Output Type");
        scrnMsg.ntfyEmlRpyToAddr.setNameForMessage("Reply To");
        scrnMsg.ntfyEmlToAddr.setNameForMessage("To");
        scrnMsg.ntfyEmlCcAddr.setNameForMessage("Cc");
        scrnMsg.ntfyEmlBccAddr.setNameForMessage("Bcc");
        scrnMsg.rtrvToAddrFromSqlFlg.setNameForMessage("Retrieve To Address From SQL");
        scrnMsg.ntfyAttTpCd_SL.setNameForMessage("Attachment Format");
        scrnMsg.ntfyDistListNmListTxt.setNameForMessage("Distribution List");
        scrnMsg.ntfyEmlSubjTxt.setNameForMessage("Subject");
        scrnMsg.xxNtfyEmlBodyTxt.setNameForMessage("Body");
        scrnMsg.xxNum.setNameForMessage("# of Column");
        scrnMsg.xxRadioBtn_A0.setNameForMessage("Notification Action Select");

        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).ntfyActDtlColSortNum_B0.setNameForMessage("Sort");
            scrnMsg.B.no(i).hdrLbNm_B0.setNameForMessage("Label");
            scrnMsg.B.no(i).placeHldNm_B0.setNameForMessage("Place Holder");
        }
    }
}
