/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2710;

import static business.servlet.NMAL2710.constant.NMAL2710Constant.BIZ_ID;
import static business.servlet.NMAL2710.constant.NMAL2710Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NMAL2710.constant.NMAL2710Constant.MESSAGE_KIND_WARN;
import static business.servlet.NMAL2710.constant.NMAL2710Constant.NMAM0042E;
import static business.servlet.NMAL2710.constant.NMAL2710Constant.NMAM0043E;
import static business.servlet.NMAL2710.constant.NMAL2710Constant.NMAM0835E;
import static business.servlet.NMAL2710.constant.NMAL2710Constant.NMAM8578E;
import static business.servlet.NMAL2710.constant.NMAL2710Constant.SCRN_ID;
import static business.servlet.NMAL2710.constant.NMAL2710Constant.ZZM9000E;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2710.NMAL2710CMsg;
import business.servlet.NMAL2710.common.NMAL2710CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NMAL2710Scrn00_CMN_Submit
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/16   Fujitsu         W.Honda         Create          N/A
 * 2017/11/16   Fujitsu         N.Sugiura       Update          CSA-QC#20597
 *</pre>
 */
public class NMAL2710Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2710BMsg scrnMsg = (NMAL2710BMsg) bMsg;

        NMAL2710CommonLogic.addCheckItemForHeader(scrnMsg);
        NMAL2710CommonLogic.addCheckItemForDetail(scrnMsg);

        scrnMsg.putErrorScreen();

        if (!ZYPCommonFunc.hasValue(scrnMsg.orgNm_DC)) {
            scrnMsg.orgNm_DC.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.orgNm_DC.getNameForMessage()});
            scrnMsg.addCheckItem(scrnMsg.orgNm_DC);
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.effFromDt_DC)) {
            scrnMsg.effFromDt_DC.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.effFromDt_DC.getNameForMessage()});
            scrnMsg.addCheckItem(scrnMsg.effFromDt_DC);
        }

        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, NMAL2710Bean.xxChkBox_A1, ZYPConstant.CHKBOX_ON_Y);
        if (selectedRows.isEmpty()) {
            scrnMsg.xxChkBox_DH.setErrorInfo(1, NMAM0835E);
            scrnMsg.addCheckItem(scrnMsg.xxChkBox_DH);
        }

        for (int index : selectedRows) {
            if (scrnMsg.orgNm_DC.getValue().equals(scrnMsg.A.no(index).orgNm_A1.getValue())) {
                scrnMsg.A.no(index).xxChkBox_A1.setErrorInfo(1, NMAM8578E);
                scrnMsg.addCheckItem(scrnMsg.A.no(index).xxChkBox_A1);
            }
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.effFromDt_DC)
                && ZYPCommonFunc.hasValue(scrnMsg.effThruDt_DC)
                && ZYPDateUtil.compare(scrnMsg.effFromDt_DC.getValue(), scrnMsg.effThruDt_DC.getValue()) > 0) {
            scrnMsg.effFromDt_DC.setErrorInfo(1, NMAM0043E, new String[] {scrnMsg.effFromDt_DC.getNameForMessage(), scrnMsg.effThruDt_DC.getNameForMessage()});
            scrnMsg.effThruDt_DC.setErrorInfo(1, NMAM0042E, new String[] {scrnMsg.effThruDt_DC.getNameForMessage(), scrnMsg.effFromDt_DC.getNameForMessage()});
            scrnMsg.addCheckItem(scrnMsg.effFromDt_DC);
            scrnMsg.addCheckItem(scrnMsg.effThruDt_DC);
        }

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2710BMsg scrnMsg = (NMAL2710BMsg) bMsg;

        NMAL2710CMsg bizMsg = new NMAL2710CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2710BMsg scrnMsg = (NMAL2710BMsg) bMsg;
        NMAL2710CMsg bizMsg = (NMAL2710CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A, 1);

        // 2017/11/16 CSA-QC#20597 Mod Start
        // if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind()) || MESSAGE_KIND_WARN.equals(bizMsg.getMessageKind())) {
        // 2017/11/16 CSA-QC#20597 Mod End
            NMAL2710CommonLogic.addCheckItemForDetail(scrnMsg);
            scrnMsg.putErrorScreen();
        }

        scrnMsg.setFocusItem(scrnMsg.orgNm_H);
    }
}
