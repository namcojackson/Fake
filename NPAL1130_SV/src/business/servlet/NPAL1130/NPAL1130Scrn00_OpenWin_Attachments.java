/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1130;

import static business.servlet.NPAL1130.constant.NPAL1130Constant.BUSINESS_ID;
import static business.servlet.NPAL1130.constant.NPAL1130Constant.FUNCTION_CD_SEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1130.NPAL1130CMsg;
import business.servlet.ZYPL0310.constant.ZYPL0310Constant;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Business ID : NPAL1130 Replenishment Plan Inquiry(Detail)
 * Function Name : Open Attachments Screen
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 22/12/2016   CITS            T.Kikuhara      Create          QC#14774
 * 2018/04/11   CITS            K.Ogino         Update          QC#21229
 *</pre>
 */
public class NPAL1130Scrn00_OpenWin_Attachments extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // QC#21229
        NPAL1130BMsg scrnMsg = (NPAL1130BMsg) bMsg;

        NPAL1130CMsg bizMsg = new NPAL1130CMsg();
        scrnMsg.xxNum.setValue(getButtonSelectNumber());
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1130BMsg scrnMsg = (NPAL1130BMsg) bMsg;
        NPAL1130CMsg bizMsg = (NPAL1130CMsg) cMsg;

        ZYPTableUtil.clear(scrnMsg.P);

        scrnMsg.P.no(0).xxPopPrm.setValue(ZYPL0310Constant.DISPLAY_MODE_READ_ONLY);  //[0]:display mode
        scrnMsg.P.no(1).xxPopPrm.setValue("NMAL0110");  //[1]:business application id
        scrnMsg.P.no(2).xxPopPrm.setValue(bizMsg.P.no(0).xxPopPrm.getValue());  //[2]:attachments grouping text
        scrnMsg.P.no(3).xxPopPrm.setValue("Item Master Attachments");  //[3]:function name
        scrnMsg.P.no(4).xxPopPrm.setValue("Item Number");  //[4]:primary key
        scrnMsg.P.no(5).xxPopPrm.setValue("MDSE_ATT_DOC_TP");  //[5]:document type list //Code Table Name
        scrnMsg.P.no(6).xxPopPrm.setValue("NMAL0110_PARAM_ATT_LIMIT_NUM");  //[6]:attachments limit (This is key of  //NUM_CONST's key
        scrnMsg.P.no(7).xxPopPrm.setValue("");  //Default
        scrnMsg.P.no(8).xxPopPrm.setValue("NMAL0110_PARAM_ATT_DATA_VOL");  //[8]:authorize file volume(size) (This is key of //NUM_CONST's key

        Object[] params = new Object[9];
        for (int i = 0; i < 9; i++) {
            params[i] = scrnMsg.P.no(i).xxPopPrm.getValue();
        }

        setArgForSubScreen(params);

    }
}
