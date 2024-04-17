/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6700;

import static business.servlet.NMAL6700.constant.NMAL6700Constant.BUSINESS_ID;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL6700.common.NMAL6700CommonLogic;
import business.servlet.NMAL6700.constant.NMAL6700Constant;
import business.servlet.ZYPL0310.constant.ZYPL0310Constant;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/24   SRAA            Y.Chen          Create          QC#4482
 * 2018/01/22   Fujitsu         Hd.Sugawara     Update          QC#20291(Sol#348)
 *</pre>
 */
public class NMAL6700Scrn00_OpenWin_Attachments extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        //do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL6700BMsg scrnMsg = (NMAL6700BMsg) bMsg;

        int selectIdx = getButtonSelectNumber();
        BigDecimal pk = scrnMsg.J.no(selectIdx).dsCustSpclMsgPk_J1.getValue();

        ZYPTableUtil.clear(scrnMsg.P);
        // Mod Start 2018/01/22 QC#20291(Sol#348)
        //if (hasUpdateFuncId()) {
        if (hasUpdateFuncId(scrnMsg)) {
            // Mod End 2018/01/22 QC#20291(Sol#348)
            scrnMsg.P.no(0).xxPopPrm.setValue(ZYPL0310Constant.DISPLAY_MODE_MODIFICATION);    //[0]:display mode
        } else {
            scrnMsg.P.no(0).xxPopPrm.setValue(ZYPL0310Constant.DISPLAY_MODE_READ_ONLY);       //[0]:display mode
        }
        scrnMsg.P.no(1).xxPopPrm.setValue(BUSINESS_ID);                             //[1]:business application id
        scrnMsg.P.no(2).xxPopPrm.setValue(pk.toPlainString());                      //[2]:attachments grouping text
        scrnMsg.P.no(3).xxPopPrm.setValue("Customer Instructions Attachments");     //[3]:function name
        scrnMsg.P.no(4).xxPopPrm.setValue("Customer Instructions");                 //[4]:primary key
        scrnMsg.P.no(5).xxPopPrm.setValue("DS_ACCT_ATT_DOC_TP");                    //[5]:document type list //Code Table Name
        scrnMsg.P.no(6).xxPopPrm.setValue("NMAL6720ARAM_ATT_LIMIT_NUM");            //[6]:attachments limit (This is key of  //NUM_CONST's key  
        scrnMsg.P.no(7).xxPopPrm.setValue("");                                      //[7]:authorize file extensions (This is key of //VAR_CHAR_CONST's key
        scrnMsg.P.no(8).xxPopPrm.setValue("NMAL6720ARAM_ATT_DATA_VOL");             //[8]:authorize file volume(size) (This is key of //NUM_CONST's key
        Object[] params = new Object[11];
        for (int i = 0; i < params.length; i++) {
            params[i] = scrnMsg.P.no(i).xxPopPrm.getValue();
        }
        setArgForSubScreen(params);
    }

    // Mod Start 2018/01/22 QC#20291(Sol#348)
    //private boolean hasUpdateFuncId() {
    private boolean hasUpdateFuncId(NMAL6700BMsg scrnMsg) {
        // Mod End 2018/01/22 QC#20291(Sol#348)
        String userId = getUserProfileService().getContextUserInfo().getUserId();

        // Mod Start 2018/01/22 QC#20291(Sol#348)
        //if (getUserProfileService().isFunctionGranted(userId, NMAL6700Constant.FUNC_ID_INSTRUCTION_UPDATE)) {
        List<String> functionIds = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_ID);

        if (NMAL6700CommonLogic.isInstractionsTabUpdate(scrnMsg, functionIds)) {
            // Mod End 2018/01/22 QC#20291(Sol#348)
            return true;
        }
        return false;

    }
}
