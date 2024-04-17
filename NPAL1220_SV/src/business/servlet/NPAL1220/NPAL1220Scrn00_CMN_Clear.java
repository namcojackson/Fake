/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1220;

import static business.servlet.NPAL1220.constant.NPAL1220Constant.BIZ_APP_ID;
import static business.servlet.NPAL1220.constant.NPAL1220Constant.FUNCTION_CD_SEARCH;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NPAL1220.NPAL1220CMsg;
//import business.servlet.NPAL1220.constant.NPAL1220Constant;

import business.blap.NPAL1220.NPAL1220CMsg;
import business.servlet.NPAL1220.common.NPAL1220CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/24   CITS            Y.IWASAKI       Create          N/A
 *</pre>
 */
public class NPAL1220Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1220BMsg scrnMsg = (NPAL1220BMsg) bMsg;
        EZDBStringItem str = scrnMsg.xxComnColOrdTxt;
        scrnMsg.clear();

        NPAL1220CMsg bizMsg = new NPAL1220CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        // Set UserID
        ZYPEZDItemValueSetter.setValue(bizMsg.srchOptUsrId_U1, getContextUserInfo().getUserId());
        ZYPEZDItemValueSetter.setValue(bizMsg.xxCondCd_Z, str);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1220BMsg scrnMsg = (NPAL1220BMsg) bMsg;
        NPAL1220CMsg bizMsg = (NPAL1220CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_APP_ID);

        NPAL1220CommonLogic.ctrlScrnItemDispInit(this, scrnMsg, funcList);

        // Set Forcus
        scrnMsg.setFocusItem(scrnMsg.aslNm);
    }
}
