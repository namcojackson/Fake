/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0020;

import static business.servlet.NPBL0020.constant.NPBL0020Constant.BIZ_APP_ID;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.FUNC_EDIT;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NPBL0020.common.NPBL0020CommonLogic;
import business.servlet.ZYPL0310.constant.ZYPL0310Constant;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NPBL0020Scrn00_Attachments extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.X);
        boolean authHeaderEdit = getUserProfileService().isFunctionGranted(getUserProfileService().getContextUserInfo().getUserId(), FUNC_EDIT);
        if (authHeaderEdit) {
            scrnMsg.X.no(0).xxPopPrm_AT.setValue(ZYPL0310Constant.DISPLAY_MODE_MODIFICATION);  //[0]:display mode
        } else {
            scrnMsg.X.no(0).xxPopPrm_AT.setValue(ZYPL0310Constant.DISPLAY_MODE_READ_ONLY);     //[0]:read only mode
        }
        scrnMsg.X.no(1).xxPopPrm_AT.setValue(BIZ_APP_ID);                               //[1]:business application id
        scrnMsg.X.no(2).xxPopPrm_AT.setValue(scrnMsg.prchReqNum.getValue());            //[2]:attachments grouping text
        scrnMsg.X.no(3).xxPopPrm_AT.setValue("Inventory Request Entry Attachments");    //[3]:function name
        scrnMsg.X.no(4).xxPopPrm_AT.setValue("Inventory Request Number");               //[4]:primary key
        scrnMsg.X.no(5).xxPopPrm_AT.setValue("INVTY_REQ_ATT_TP");                       //[5]:document type list //Code Table Name
        scrnMsg.X.no(6).xxPopPrm_AT.setValue("NPBL0020_PARAM_ATT_LIMIT_NUM");           //[6]:attachments limit (This is key of  //NUM_CONST's key
        scrnMsg.X.no(7).xxPopPrm_AT.setValue("NPBL0020_PARAM_AUTHE_FILE_EXT");          //[7]:authorize file extensions (This is key of //VAR_CHAR_CONST's key
        scrnMsg.X.no(8).xxPopPrm_AT.setValue("NPBL0020_PARAM_ATT_DATA_VOL");            //[8]:authorize file volume(size) (This is key of //NUM_CONST's key
        Object[] params = NPBL0020CommonLogic.toArray_popupForZYPL0310(scrnMsg.X, 10);
        setArgForSubScreen(params);
    }
}
