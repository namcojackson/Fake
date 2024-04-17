/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1500;

import static business.servlet.NPAL1500.constant.NPAL1500Constant.BIZ_ID;
import static business.servlet.NPAL1500.constant.NPAL1500Constant.FUNCTION_CD_SEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1500.NPAL1500CMsg;
import business.servlet.NPAL1500.common.NPAL1500CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/18/2015   CITS            N Akaishi       Create          n/a
 * 10/05/2016   CITS            S.Endo          Update          QC#12768
 * 11/09/2018   CITS            K.Kameoka       Update          QC#29064
 * 02/18/2020   Fujitsu         T.Ogura         Update          QC#55916
 *</pre>
 */
public class NPAL1500Scrn00_OpenWin_AccountChrg extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;
        //QC#29064 Del Start
        //NPAL1500CommonLogic.addCheckHeaderItem(scrnMsg);
        //QC#29064 Del End
        int idx = this.getButtonSelectNumber();
        scrnMsg.addCheckItem(scrnMsg.A.no(idx).poLineTpCd_A1);
        scrnMsg.addCheckItem(scrnMsg.A.no(idx).mdseCd_A1);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;
        NPAL1500CMsg bizMsg = new NPAL1500CMsg();

        scrnMsg.xxNum.setValue(getButtonSelectNumber());

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1500BMsg scrnMsg = (NPAL1500BMsg) bMsg;
        NPAL1500CMsg bizMsg  = (NPAL1500CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        int idx = this.getButtonSelectNumber();

        scrnMsg.addCheckItem(scrnMsg.A.no(idx).mdseCd_A1);
        scrnMsg.addCheckItem(scrnMsg.A.no(idx).poLineTpCd_A1);
        // START 02/18/2020 T.Ogura [QC#55916,MOD]
//        scrnMsg.addCheckItem(scrnMsg.A.no(idx).xxScrItem130Txt_CH);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxScrItem130Txt_CH);
        }
        // END   02/18/2020 T.Ogura [QC#55916,MOD]
        scrnMsg.putErrorScreen();

        // Initialization of subscreen delivery information
        NPAL1500CommonLogic.setInitParamForAcctChrgPopup(scrnMsg, idx);

        // Making of subscreen delivery information
        Object[] params = NPAL1500CommonLogic.setParamForNMAL2550Popup(scrnMsg);

        // Subscreen transition
        setArgForSubScreen(params);
    }
}
