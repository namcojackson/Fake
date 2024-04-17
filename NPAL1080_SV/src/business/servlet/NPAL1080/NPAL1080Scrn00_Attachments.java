/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1080;

import static business.servlet.NPAL1080.constant.NPAL1080Constant.BUSINESS_APPLICATION_ID;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.FUNC_ID_SUBMIT;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NPAL1080.common.NPAL1080CommonLogic;
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
public class NPAL1080Scrn00_Attachments extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.Z);
        boolean authHeaderEdit = getUserProfileService().isFunctionGranted(getUserProfileService().getContextUserInfo().getUserId(), FUNC_ID_SUBMIT);
        if (authHeaderEdit) {
            scrnMsg.Z.no(0).xxPopPrm_AT.setValue(ZYPL0310Constant.DISPLAY_MODE_MODIFICATION);  //[0]:display mode
        } else {
            scrnMsg.Z.no(0).xxPopPrm_AT.setValue(ZYPL0310Constant.DISPLAY_MODE_READ_ONLY);     //[0]:read only mode
        }
        scrnMsg.Z.no(1).xxPopPrm_AT.setValue(BUSINESS_APPLICATION_ID);                  //[1]:business application id
        scrnMsg.Z.no(2).xxPopPrm_AT.setValue(scrnMsg.prchReqNum_H1.getValue());         //[2]:attachments grouping text
        scrnMsg.Z.no(3).xxPopPrm_AT.setValue("Tech Request Entry Attachments");         //[3]:function name
        scrnMsg.Z.no(4).xxPopPrm_AT.setValue("Tech Request Number");                    //[4]:primary key
        scrnMsg.Z.no(5).xxPopPrm_AT.setValue("TECH_RQST_ATT_TP");                       //[5]:document type list //Code Table Name
        scrnMsg.Z.no(6).xxPopPrm_AT.setValue("NPAL1080_PARAM_ATT_LIMIT_NUM");           //[6]:attachments limit (This is key of  //NUM_CONST's key
        scrnMsg.Z.no(7).xxPopPrm_AT.setValue("NPAL1080_PARAM_AUTHE_FILE_EXT");          //[7]:authorize file extensions (This is key of //VAR_CHAR_CONST's key
        scrnMsg.Z.no(8).xxPopPrm_AT.setValue("NPAL1080_PARAM_ATT_DATA_VOL");            //[8]:authorize file volume(size) (This is key of //NUM_CONST's key
        Object[] params = NPAL1080CommonLogic.toArray_popupForZYPL0310(scrnMsg.Z, 10);
        setArgForSubScreen(params);
    }
}
