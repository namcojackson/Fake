/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8810;

import static business.servlet.NYEL8810.constant.NYEL8810Constant.SCRN_ID_00;
import static business.servlet.NYEL8810.constant.NYEL8810Constant.BIZ_ID;

import java.io.Serializable;
import java.math.BigDecimal;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NYEL8810.NYEL8810CMsg;
import business.servlet.NYEL8810.common.NYEL8810CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NYEL8810_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/06/29   Fujitsu         Q09079          Create          N/A
 * 2018/05/07   Fujitsu         Q10814          Update          QC#23516
 * 2018/12/18   Fujitsu         Q10627          Update          QC#29682
 *</pre>
 */
public class NYEL8810_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NYEL8810BMsg scrnMsg = (NYEL8810BMsg) bMsg;
        NYEL8810CMsg bizMsg = new NYEL8810CMsg();

        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();

        // From URL link
        Serializable arg = getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;

            if ((params != null) && (params.length > 0)){
                BigDecimal wfProcPk = BigDecimal.ZERO;
                if (params[0] instanceof EZDBBigDecimalItem) {
                    wfProcPk = ((EZDBBigDecimalItem) params[0]).getValue();
                } else if (params[0] instanceof BigDecimal) {
                    wfProcPk = (BigDecimal) params[0];
                }

                if (!BigDecimal.ZERO.equals(wfProcPk)){
                    ZYPEZDItemValueSetter.setValue(scrnMsg.wfProcPk, wfProcPk);
                }
            }
        }

        bizMsg.setBusinessID("NYEL8810");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NYEL8810BMsg scrnMsg = (NYEL8810BMsg) bMsg;
        NYEL8810CMsg bizMsg = (NYEL8810CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

// QC#29682 DEL START 2018/12/18
//        if (PROC_ID_ALL.equals(scrnMsg.wfProcNm.getValue())){
//            scrnMsg.wfWrkItemNm.setInputProtected(true);
//        } else {
//            scrnMsg.wfWrkItemNm.setInputProtected(false);
//        }
// QC#29682 DEL END   2018/12/18

        scrnMsg.usrId.setIndispensable(true);
        NYEL8810CommonLogic.initCmnBtnProp(this, scrnMsg);
        NYEL8810CommonLogic.initRowCtrlProp(this, scrnMsg);
        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
        tblColor.setAlternateRowsBG("A", scrnMsg.A, 1);

        scrnMsg.setFocusItem(scrnMsg.usrId);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NYEL8810BMsg scrnMsg = (NYEL8810BMsg) bMsg;

        scrnMsg.procStsCd.setNameForMessage("Process Status");

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxWfAprChkFlg_A);
        }

        scrnMsg.xxWfAsgCd.setNameForMessage("Assignee");
        // QC#23516 MOD START 2018/05/07
        //scrnMsg.wfCmntTxt.setNameForMessage("Details");
        scrnMsg.wfCmntTxt.setNameForMessage("Comment");
        // QC#23516 MOD END 2018/05/07
        // QC#23516 ADD START 2018/05/07
        scrnMsg.usrId.setNameForMessage("User ID");
        scrnMsg.wfProcPk.setNameForMessage("Workflow ID");
        scrnMsg.wfProcDocId.setNameForMessage("Source#");
        scrnMsg.wfBizAttrbTxt.setNameForMessage("Subject");
        scrnMsg.wfUsrGrpNm_F.setNameForMessage("From");
        scrnMsg.wfUsrGrpNm_T.setNameForMessage("To");
        scrnMsg.xxFromDt_SS.setNameForMessage("Sent Date From");
        scrnMsg.xxThruDt_SE.setNameForMessage("Sent Date To");
        scrnMsg.xxFromDt_DS.setNameForMessage("Due Date From");
        scrnMsg.xxThruDt_DE.setNameForMessage("Due Date To");
        // QC#23516 ADD END 2018/05/07

        scrnMsg.putErrorScreen();
    }

}
