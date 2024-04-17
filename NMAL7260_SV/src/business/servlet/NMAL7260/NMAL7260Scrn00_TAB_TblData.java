/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7260;

import static business.servlet.NMAL7260.constant.NMAL7260Constant.NMAM8324E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7260.common.NMAL7260CommonLogic;
import business.servlet.NMAL7260.constant.NMAL7260Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/04/09   Fujitsu         H.Nagashima     Create          QC#22593
 *</pre>
 */
public class NMAL7260Scrn00_TAB_TblData extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7260BMsg scrnMsg = (NMAL7260BMsg) bMsg;
        if (!scrnMsg.xxComnColOrdTxt.getValue().equals(NMAL7260CommonLogic.getColumnTableData(scrnMsg))) {
            scrnMsg.setMessageInfo(NMAM8324E);
        }


    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {


        NMAL7260BMsg scrnMsg = (NMAL7260BMsg) bMsg;

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab_H1, NMAL7260Constant.TAB_ADJ_TBL_DATA);
        NMAL7260CommonLogic.setTableColor(scrnMsg);

    }
}
