/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1500;

import static business.servlet.NPAL1500.constant.NPAL1500Constant.BIZ_ID;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.TAB_DETAIL;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.ZZM9000E;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1500.NPAL1500CMsg;
import business.servlet.NPAL1500.common.NPAL1500CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_PO_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/04/17   Hitachi         TZ.Win          Create          QC#61299
 *</pre>
 */
public class NPAL1500Scrn00_Apply_ASL extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;
        
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).mdseCd_A1)) {
                scrnMsg.A.no(i).mdseCd_A1.setErrorInfo(1, ZZM9000E, new String[] {"Item#" });
            }
            scrnMsg.addCheckItem(scrnMsg.A.no(i).mdseCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).poLineTpCd_A1);
            scrnMsg.addCheckItem(scrnMsg.vndCd);

            if (DS_PO_TP.BUYBACK_PO.equals(scrnMsg.dsPoTpCd.getValue())) {
                scrnMsg.addCheckItem(scrnMsg.destRtlWhCd_DS);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).destRtlSwhCd_A1);
            }
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;

        NPAL1500CMsg bizMsg = new NPAL1500CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;
        NPAL1500CMsg bizMsg = (NPAL1500CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.xxDplyTab.setValue(TAB_DETAIL);

        if ("E".equals(bizMsg.getMessageKind()) || "W".equals(bizMsg.getMessageKind())) {
            NPAL1500CommonLogic.moveErrorTab(scrnMsg);
            NPAL1500CommonLogic.addCheckItem(scrnMsg);
        }

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_ID);
        NPAL1500CommonLogic.ctrlScreenItem(this, scrnMsg, funcList);
        scrnMsg.putErrorScreen();
    }
}
