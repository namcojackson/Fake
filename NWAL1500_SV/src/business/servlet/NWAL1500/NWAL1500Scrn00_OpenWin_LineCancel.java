/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.BIZ_ID;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_RMA;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NWAL1500.NWAL1500CMsg;
import business.servlet.NWAL1500.common.NWAL1500CommonLogicForScrnFields;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1500Scrn00_OpenWin_LineCancel
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/06   Fujitsu         T.Ishii         Create          N/A
 *</pre>
 */
public class NWAL1500Scrn00_OpenWin_LineCancel extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;

        NWAL1500CMsg bizMsg = new NWAL1500CMsg();
        int idx = getButtonSelectNumber();
        scrnMsg.xxCellIdx_BB.setValue(idx);
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        NWAL1500CMsg bizMsg = (NWAL1500CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_LC);
        }
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.B.no(i).xxChkBox_LL);
        }
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.C.no(i).xxChkBox_RC);
        }
        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.D.no(i).xxChkBox_RL);
        }
        scrnMsg.putErrorScreen();
        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }
        setResult(scrnMsg.xxRqstFlg.getValue());
        if (S21StringUtil.isEquals(scrnMsg.xxRqstFlg.getValue(), ZYPConstant.FLG_ON_Y)) {
            scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
            Object[] params = null;
            if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
                List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_LC", ZYPConstant.FLG_ON_Y);
                if (selectedRows.size() > 0) {
                    params = getParamNWAL2000ForLineConfigConfigLevel(scrnMsg, selectedRows.get(0));
                } else {
                    selectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);
                    if (selectedRows.size() > 0) {
                        params = getParamNWAL2000ForLineConfigLineLevel(scrnMsg, selectedRows.get(0));
                    }
                }
            } else if (TAB_RMA.equals(scrnMsg.xxDplyTab.getValue())) {
                List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.C, "xxChkBox_RC", ZYPConstant.FLG_ON_Y);
                if (selectedRows.size() > 0) {
                    params = getParamNWAL2000ForRMAConfigLevel(scrnMsg, selectedRows.get(0));
                } else {
                    selectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.D, "xxChkBox_RL", ZYPConstant.FLG_ON_Y);
                    if (selectedRows.size() > 0) {
                        params = getParamNWAL2000ForRMALineLevel(scrnMsg, selectedRows.get(0));
                    }
                }
            }

            setArgForSubScreen(params);
        } else {
            NWAL1500CommonLogicForScrnFields.setProtect(this, scrnMsg);
        }
    }

    private Object[] getParamNWAL2000ForLineConfigConfigLevel(NWAL1500BMsg scrnMsg, int selectedRow) {

        Object[] params = new Object[9];

        scrnMsg.xxModeCd_P1.setValue("01");
        scrnMsg.glblCmpyCd_P1.setValue(getGlobalCompanyCode());
        scrnMsg.cpoOrdNum_P1.setValue(scrnMsg.cpoOrdNum.getValue());
        scrnMsg.configCatgCd_P1.setValue(CONFIG_CATG.OUTBOUND);
        scrnMsg.dsOrdPosnNum_P1.setValue(scrnMsg.A.no(selectedRow).dsOrdPosnNum_LC.getValue());
        scrnMsg.dsCpoLineNum_P1.clear();
        scrnMsg.dsCpoLineSubNum_P1.clear();
        scrnMsg.cancQty_P1.clear();
        scrnMsg.cancQty_P2.clear();

        int index = 0;
        params[index++] = scrnMsg.xxModeCd_P1;
        params[index++] = scrnMsg.glblCmpyCd_P1;
        params[index++] = scrnMsg.cpoOrdNum_P1;
        params[index++] = scrnMsg.configCatgCd_P1;
        params[index++] = scrnMsg.dsOrdPosnNum_P1;
        params[index++] = scrnMsg.dsCpoLineNum_P1;
        params[index++] = scrnMsg.dsCpoLineSubNum_P1;
        params[index++] = scrnMsg.cancQty_P1;
        params[index++] = scrnMsg.cancQty_P2;

        return params;
    }

    private Object[] getParamNWAL2000ForLineConfigLineLevel(NWAL1500BMsg scrnMsg, int selectedRow) {

        Object[] params = new Object[9];

        scrnMsg.xxModeCd_P1.setValue("02");
        scrnMsg.glblCmpyCd_P1.setValue(getGlobalCompanyCode());
        scrnMsg.cpoOrdNum_P1.setValue(scrnMsg.cpoOrdNum.getValue());
        scrnMsg.configCatgCd_P1.setValue(CONFIG_CATG.OUTBOUND);
        scrnMsg.dsOrdPosnNum_P1.setValue(scrnMsg.B.no(selectedRow).dsOrdPosnNum_LL.getValue());
        scrnMsg.dsCpoLineNum_P1.setValue(scrnMsg.B.no(selectedRow).dsCpoLineNum_LL.getValue());
        scrnMsg.dsCpoLineSubNum_P1.clear();
        // scrnMsg.cancQty_P1.clear();
        scrnMsg.cancQty_P2.clear();

        int index = 0;
        params[index++] = scrnMsg.xxModeCd_P1;
        params[index++] = scrnMsg.glblCmpyCd_P1;
        params[index++] = scrnMsg.cpoOrdNum_P1;
        params[index++] = scrnMsg.configCatgCd_P1;
        params[index++] = scrnMsg.dsOrdPosnNum_P1;
        params[index++] = scrnMsg.dsCpoLineNum_P1;
        params[index++] = scrnMsg.dsCpoLineSubNum_P1;
        params[index++] = scrnMsg.cancQty_P1;
        params[index++] = scrnMsg.cancQty_P2;

        return params;
    }

    private Object[] getParamNWAL2000ForRMAConfigLevel(NWAL1500BMsg scrnMsg, int selectedRow) {

        Object[] params = new Object[9];

        scrnMsg.xxModeCd_P1.setValue("01");
        scrnMsg.glblCmpyCd_P1.setValue(getGlobalCompanyCode());
        scrnMsg.cpoOrdNum_P1.setValue(scrnMsg.cpoOrdNum.getValue());
        scrnMsg.configCatgCd_P1.setValue(CONFIG_CATG.INBOUND);
        scrnMsg.dsOrdPosnNum_P1.setValue(scrnMsg.C.no(selectedRow).dsOrdPosnNum_RC.getValue());
        scrnMsg.dsCpoLineNum_P1.clear();
        scrnMsg.dsCpoLineSubNum_P1.clear();
        scrnMsg.cancQty_P1.clear();
        scrnMsg.cancQty_P2.clear();

        int index = 0;
        params[index++] = scrnMsg.xxModeCd_P1;
        params[index++] = scrnMsg.glblCmpyCd_P1;
        params[index++] = scrnMsg.cpoOrdNum_P1;
        params[index++] = scrnMsg.configCatgCd_P1;
        params[index++] = scrnMsg.dsOrdPosnNum_P1;
        params[index++] = scrnMsg.dsCpoLineNum_P1;
        params[index++] = scrnMsg.dsCpoLineSubNum_P1;
        params[index++] = scrnMsg.cancQty_P1;
        params[index++] = scrnMsg.cancQty_P2;

        return params;
    }

    private Object[] getParamNWAL2000ForRMALineLevel(NWAL1500BMsg scrnMsg, int selectedRow) {

        Object[] params = new Object[9];

        scrnMsg.xxModeCd_P1.setValue("02");
        scrnMsg.glblCmpyCd_P1.setValue(getGlobalCompanyCode());
        scrnMsg.cpoOrdNum_P1.setValue(scrnMsg.cpoOrdNum.getValue());
        scrnMsg.configCatgCd_P1.setValue(CONFIG_CATG.INBOUND);
        scrnMsg.dsOrdPosnNum_P1.setValue(scrnMsg.D.no(selectedRow).dsOrdPosnNum_RL.getValue());
        scrnMsg.dsCpoLineNum_P1.setValue(scrnMsg.D.no(selectedRow).dsCpoLineNum_RL.getValue());
        scrnMsg.dsCpoLineSubNum_P1.setValue(scrnMsg.D.no(selectedRow).dsCpoLineSubNum_RL.getValue());
        // scrnMsg.cancQty_P1.clear();
        scrnMsg.cancQty_P2.clear();

        int index = 0;
        params[index++] = scrnMsg.xxModeCd_P1;
        params[index++] = scrnMsg.glblCmpyCd_P1;
        params[index++] = scrnMsg.cpoOrdNum_P1;
        params[index++] = scrnMsg.configCatgCd_P1;
        params[index++] = scrnMsg.dsOrdPosnNum_P1;
        params[index++] = scrnMsg.dsCpoLineNum_P1;
        params[index++] = scrnMsg.dsCpoLineSubNum_P1;
        params[index++] = scrnMsg.cancQty_P1;
        params[index++] = scrnMsg.cancQty_P2;

        return params;
    }
}
