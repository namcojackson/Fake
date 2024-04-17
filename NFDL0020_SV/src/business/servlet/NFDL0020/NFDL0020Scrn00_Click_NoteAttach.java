/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFDL0020.common.NFDL0020CommonLogic;
import business.servlet.NFDL0020.constant.NFDL0020Constant;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Collection Detail Screen
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 2018/07/09   Hitachi         J.Kim           Create          QC#26801
 *</pre>
 */
public class NFDL0020Scrn00_Click_NoteAttach extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;
        scrnMsg.xxScrEventNm.setValue("Click_NoteAttachment");
        ZYPTableUtil.clear(scrnMsg.P);
        boolean authHeaderEdit = getUserProfileService().isFunctionGranted(getUserProfileService().getContextUserInfo().getUserId(), "NFDL0020T020");
        if (authHeaderEdit) {
            scrnMsg.P.no(0).xxPopPrm.setValue(NFDL0020Constant.DISPLAY_MODE_MODIFICATION);  //[0]:display mode
        } else {
            scrnMsg.P.no(0).xxPopPrm.setValue(NFDL0020Constant.DISPLAY_MODE_READ_ONLY);     //[0]:read only mode
        }
        scrnMsg.P.no(1).xxPopPrm.setValue(NFDL0020Constant.BIZ_ID);                         //[1]:business application id
        scrnMsg.P.no(2).xxPopPrm.setValue(scrnMsg.dsAcctNum_H.getValue());                  //[2]:attachments grouping text
        scrnMsg.P.no(3).xxPopPrm.setValue("Collection Header");                             //[3]:function name
        scrnMsg.P.no(4).xxPopPrm.setValue("Account");                                       //[4]:primary key
        scrnMsg.P.no(5).xxPopPrm.setValue("CLT_UPD_ATT_TP");                                //[5]:document type list //Code Table Name
        scrnMsg.P.no(6).xxPopPrm.setValue("NFDL0020_PARAM_ATT_LIMIT_NUM");                  //[6]:attachments limit (This is key of  //NUM_CONST's key
        scrnMsg.P.no(7).xxPopPrm.setValue("");                                              //[7]:authorize file extensions (This is key of //VAR_CHAR_CONST's key - Defaulet
        scrnMsg.P.no(8).xxPopPrm.setValue("NFDL0020_PARAM_ATT_DATA_VOL");                   //[8]:authorize file volume(size) (This is key of //NUM_CONST's key
        Object[] params = NFDL0020CommonLogic.toArray_popupForZYPL0310(scrnMsg.P, 10);
        setArgForSubScreen(params);
    }
}
