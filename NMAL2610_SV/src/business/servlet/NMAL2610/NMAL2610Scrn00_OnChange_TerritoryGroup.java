/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2610;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2610.NMAL2610CMsg;
import business.servlet.NMAL2610.common.NMAL2610CommonLogic;
import business.servlet.NMAL2610.constant.NMAL2610Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/03/04/  Fujitsu         M.suzuki        Update          S21_NA#4539 
 * 2016/05/24   SRAA            Y.Chen          Update          QC#7962
 * 2016/06/24   Hitachi         A.Kohinata      Update          QC#10276
 *</pre>
 */
public class NMAL2610Scrn00_OnChange_TerritoryGroup extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // 2016/03/04 S21_NA#4539 Add Start --------------
        NMAL2610BMsg scrnMsg = (NMAL2610BMsg) bMsg;
        NMAL2610CommonLogic.clearMandantoryForHeader(scrnMsg);
        NMAL2610CommonLogic.addCheckItem(scrnMsg, false);
        // 2016/03/04 S21_NA#4539 Add Start --------------
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2610BMsg scrnMsg = (NMAL2610BMsg) bMsg;

        NMAL2610CMsg bizMsg = new NMAL2610CMsg();
        bizMsg.setBusinessID(NMAL2610Constant.BIZ_ID);
        bizMsg.setFunctionCode(NMAL2610Constant.FUNCTION_CD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2610BMsg scrnMsg = (NMAL2610BMsg) bMsg;
        NMAL2610CMsg bizMsg  = (NMAL2610CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // 2016/06/24 QC#10276 Add Start
        NMAL2610CommonLogic.controlOrgLink(scrnMsg);
        // 2016/06/24 QC#10276 Add End
        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.otherResrcTrtyFlg_H1.getValue())) {
                scrnMsg.D.no(i).acctTeamRoleTpCd_P1.setInputProtected(false);
            } else {
                scrnMsg.D.no(i).acctTeamRoleTpCd_P1.setInputProtected(true);
            }
        }
        
        // QC#7962
        scrnMsg.setFocusItem(scrnMsg.trtyGrpTpCd_P1);
    }
}
