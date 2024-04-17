/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6720;

import static business.servlet.NMAL6720.constant.NMAL6720Constant.BUSINESS_ID;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL6720.common.NMAL6720CommonLogic;
import business.servlet.ZYPL0310.constant.ZYPL0310Constant;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/06   CUSA            Fujitsu         Create          N/A
 * 2018/01/22   Fujitsu         Hd.Sugawara     Update          QC#20291(Sol#348)
 *</pre>
 */
public class NMAL6720Scrn00_OpenWin_Attachments extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;

        int selectIdx = getButtonSelectNumber();
        BigDecimal pk = scrnMsg.E.no(selectIdx).dsCustSpclMsgPk_E1.getValue();

        ZYPTableUtil.clear(scrnMsg.P);
        // Mod Start 2018/01/22 QC#20291(Sol#348)
        //if (hasUpdateFuncId()) {
        if (hasUpdateFuncId(scrnMsg)) {
            // Mod End 2018/01/22 QC#20291(Sol#348)
            scrnMsg.P.no(0).xxPopPrm_P.setValue(ZYPL0310Constant.DISPLAY_MODE_MODIFICATION);    //[0]:display mode
        } else {
            scrnMsg.P.no(0).xxPopPrm_P.setValue(ZYPL0310Constant.DISPLAY_MODE_READ_ONLY);       //[0]:display mode
        }
        scrnMsg.P.no(1).xxPopPrm_P.setValue(BUSINESS_ID);                           //[1]:business application id
        scrnMsg.P.no(2).xxPopPrm_P.setValue(pk.toPlainString());                    //[2]:attachments grouping text
        scrnMsg.P.no(3).xxPopPrm_P.setValue("Customer Instructions Attachments");   //[3]:function name
        scrnMsg.P.no(4).xxPopPrm_P.setValue("Customer Instructions");               //[4]:primary key
        scrnMsg.P.no(5).xxPopPrm_P.setValue("DS_ACCT_ATT_DOC_TP");                  //[5]:document type list //Code Table Name
        scrnMsg.P.no(6).xxPopPrm_P.setValue("NMAL6720_PARAM_ATT_LIMIT_NUM");        //[6]:attachments limit (This is key of  //NUM_CONST's key  
        scrnMsg.P.no(7).xxPopPrm_P.setValue("");                                    //[7]:authorize file extentions (This is key of //VAR_CHAR_CONST's key
        scrnMsg.P.no(8).xxPopPrm_P.setValue("NMAL6720_PARAM_ATT_DATA_VOL");         //[8]:authorize file volume(size) (This is key of //NUM_CONST's key
        Object[] params = NMAL6720CommonLogic.toArray_popupForZYPL0310(scrnMsg.P, 11);
        setArgForSubScreen(params);
    }

    // Mod Start 2018/01/22 QC#20291(Sol#348)
    //private boolean hasUpdateFuncId() {
    private boolean hasUpdateFuncId(NMAL6720BMsg scrnMsg) {
        // Mod End 2018/01/22 QC#20291(Sol#348)
        String userId = getUserProfileService().getContextUserInfo().getUserId();

        // Mod Start 2018/01/22 QC#20291(Sol#348)
        //if (getUserProfileService().isFunctionGranted(userId, FUNC_ID_INSTRUCTIONS_UPDATE)) {
        List<String> functionIds = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_ID);

        if (NMAL6720CommonLogic.isInstructionsTabUpdate(scrnMsg, functionIds)){
            // Mod End 2018/01/22 QC#20291(Sol#348)
            return true;
        }
        return false;

    }
}
