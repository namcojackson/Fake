/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2620;

import static business.servlet.NMAL2620.constant.NMAL2620Constant.SCREEN_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2620.NMAL2620CMsg;
import business.servlet.NMAL2620.common.NMAL2620CommonLogic;
import business.servlet.NMAL2620.constant.NMAL2620Constant;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   Hitachi         T.Mizuki        Create          N/A
 * 2016/04/27   Fujitsu         C.Yokoi         Update          CSA-QC#7580
 *</pre>
 */
public class NMAL2620Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2620BMsg scrnMsg = (NMAL2620BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.xxPsnNm_H);
        scrnMsg.addCheckItem(scrnMsg.psnNum_H);
        scrnMsg.addCheckItem(scrnMsg.orgNm);
        scrnMsg.addCheckItem(scrnMsg.psnCd);

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2620BMsg scrnMsg = (NMAL2620BMsg) bMsg;
        ZYPTableUtil.clear(scrnMsg.A);

        NMAL2620CMsg bizMsg = new NMAL2620CMsg();
        bizMsg.setBusinessID("NMAL2620");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2620BMsg scrnMsg = (NMAL2620BMsg) bMsg;
        NMAL2620CMsg bizMsg = (NMAL2620CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        if (scrnMsg.A.getValidCount() > 0) {
            NMAL2620CommonLogic.setRowColors(scrnMsg);
        }
        // 2016/04/27 CSA-QC#7580 Add Start
        NMAL2620CommonLogic.protectSearchResult(scrnMsg);
        // 2016/04/27 CSA-QC#7580 Add End

        if (NMAL2620Constant.MESSAGE_KIND_ERROR.equals(cMsg.getMessageKind())) {
            scrnMsg.putErrorScreen();
            throw new EZDFieldErrorException();
        }
    }
}
