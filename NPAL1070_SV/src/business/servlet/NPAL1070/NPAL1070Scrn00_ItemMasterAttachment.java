/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1070;

import static business.servlet.NPAL1070.constant.NPAL1070Constant.BIZ_APP_ID;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.DISPLAY_NM_MDSE_CD;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.FUNC_EDIT;
import static business.servlet.NPAL1070.constant.NPAL1070Constant.ZZM9000E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1070.NPAL1070CMsg;
import business.servlet.NPAL1070.common.NPAL1070CommonLogic;
import business.servlet.ZYPL0310.constant.ZYPL0310Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/10/05   CITS            K.Ogino         Create          N/A
 * 2018/04/09   CITS            K.Ogino         Update          QC#21229
 *</pre>
 */
public class NPAL1070Scrn00_ItemMasterAttachment extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1070BMsg scrnMsg = (NPAL1070BMsg) bMsg;

        int idx = getButtonSelectNumber();

        // mandatory check
        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).mdseCd_A1)) {
            scrnMsg.mrpPlnNm.setErrorInfo(1, ZZM9000E, new String[] {DISPLAY_NM_MDSE_CD});
        }
        scrnMsg.addCheckItem(scrnMsg.A.no(idx).mdseCd_A1);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // QC#21229
        NPAL1070BMsg scrnMsg = (NPAL1070BMsg) bMsg;

        NPAL1070CMsg bizMsg = new NPAL1070CMsg();
        scrnMsg.xxNum.setValue(getButtonSelectNumber());
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1070BMsg scrnMsg = (NPAL1070BMsg) bMsg;
        NPAL1070CMsg bizMsg = (NPAL1070CMsg) cMsg;

        ZYPTableUtil.clear(scrnMsg.X);
        int idx = getButtonSelectNumber();
        boolean authHeaderEdit = getUserProfileService().isFunctionGranted(getUserProfileService().getContextUserInfo().getUserId(), FUNC_EDIT);
        if (authHeaderEdit) {
            scrnMsg.X.no(0).xxPopPrm_AT.setValue(ZYPL0310Constant.DISPLAY_MODE_MODIFICATION);  //[0]:display mode
        } else {
            scrnMsg.X.no(0).xxPopPrm_AT.setValue(ZYPL0310Constant.DISPLAY_MODE_READ_ONLY);     //[0]:read only mode
        }
        scrnMsg.X.no(1).xxPopPrm_AT.setValue("NMAL0110");                               //[1]:business application id
        scrnMsg.X.no(2).xxPopPrm_AT.setValue(bizMsg.X.no(2).xxPopPrm_AT.getValue());   //[2]:attachments grouping text
        scrnMsg.X.no(3).xxPopPrm_AT.setValue("Item Master Attachments");                 //[3]:function name
        scrnMsg.X.no(4).xxPopPrm_AT.setValue("Item Number");                            //[4]:primary key
        scrnMsg.X.no(5).xxPopPrm_AT.setValue("MDSE_ATT_DOC_TP");                        //[5]:document type list //Code Table Name
        scrnMsg.X.no(6).xxPopPrm_AT.setValue("NMAL0110_PARAM_ATT_LIMIT_NUM");           //[6]:attachments limit (This is key of  //NUM_CONST's key
        scrnMsg.X.no(7).xxPopPrm_AT.setValue("");                                       //[7]:authorize file extensions (This is key of //VAR_CHAR_CONST's key
        scrnMsg.X.no(8).xxPopPrm_AT.setValue("NMAL0110_PARAM_ATT_DATA_VOL");            //[8]:authorize file volume(size) (This is key of //NUM_CONST's key
        Object[] params = NPAL1070CommonLogic.toArray_popupForZYPL0310(scrnMsg.X, 11);
        setArgForSubScreen(params);
    }
}
