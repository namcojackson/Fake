/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1910;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1910.NWAL1910CMsg;
import business.servlet.NWAL1910.common.NWAL1910CommonLogic;
import static business.servlet.NWAL1910.constant.NWAL1910Constant.PROCESS_LVL_HEADER;
import static business.servlet.NWAL1910.constant.NWAL1910Constant.BIZ_ID;
import static business.servlet.NWAL1910.constant.NWAL1910Constant.PRM_NUM;
import static business.servlet.NWAL1910.constant.NWAL1910Constant.SCRN_ID_00;


import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/09/14   Fujitsu         M.Ishii         Create          N/A
 *</pre>
 */
public class NWAL1910_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //do nothing

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1910BMsg scrnMsg = (NWAL1910BMsg) bMsg;

        NWAL1910CMsg bizMsg = new NWAL1910CMsg();

        // clear pagenation parameter
        NWAL1910CommonLogic.pageItemClear(scrnMsg);

        // get popup parameter
        Object[] params = (Object[]) getArgForSubScreen();
        if (params != null && params.length == PRM_NUM) {
            int ixP = 0;
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxViewChngLogSrcCd, (EZDBStringItem) params[ixP++]); // 0
            ZYPEZDItemValueSetter.setValue(scrnMsg.ordSrcRefNum, (EZDBStringItem) params[ixP++]); // 1
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdPosnNum, (EZDBStringItem) params[ixP++]); // 2
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsCpoLineNum, (EZDBBigDecimalItem) params[ixP++]); // 3
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd, (EZDBStringItem) params[ixP++]); // 4
            ZYPEZDItemValueSetter.setValue(scrnMsg.configCatgCd, (EZDBStringItem) params[ixP++]); // 5
            if(!ZYPCommonFunc.hasValue(scrnMsg.configCatgCd)){
                scrnMsg.configCatgCd.setValue(CONFIG_CATG.OUTBOUND);
            }

            if (!ZYPCommonFunc.hasValue(scrnMsg.xxViewChngLogSrcCd)) {
                return null;
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.ordSrcRefNum)) {
                return null;
            }
        } else {
            //insufficient parameter
            return null;
        }
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1910BMsg scrnMsg = (NWAL1910BMsg) bMsg;
        NWAL1910CMsg bizMsg  = (NWAL1910CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        //common button setting
        NWAL1910CommonLogic.initCmnBtnSetting(this);

        //protection setting
        NWAL1910CommonLogic.initSetProtection(scrnMsg);

        //setAppFracDigit
        NWAL1910CommonLogic.setAppFracDigit(scrnMsg);

        //set focus
        scrnMsg.setFocusItem(scrnMsg.ordSrcRefNum);

        //table color setting
        if (scrnMsg.A.getValidCount() > 0) {
            S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);
            tblColor.clearRowsBG("A", scrnMsg.A);
            tblColor.setAlternateRowsBG("A", scrnMsg.A);
        }

        if (!PROCESS_LVL_HEADER.equals(scrnMsg.xxModeCd.getValue())) {
            NWAL1910CommonLogic.setDispLineNum(scrnMsg);
        }
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NWAL1910BMsg scrnMsg = (NWAL1910BMsg) bMsg;

        scrnMsg.ordSrcRefNum.setNameForMessage("Trx Number");
        scrnMsg.dsOrdPosnNum.setNameForMessage("Config Line#");
        scrnMsg.xxLineNum.setNameForMessage("Line Number");
    }
}
