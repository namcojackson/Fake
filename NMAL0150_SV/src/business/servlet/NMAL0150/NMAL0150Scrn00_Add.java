package business.servlet.NMAL0150;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL0150.NMAL0150CMsg;
import business.servlet.NMAL0150.common.NMAL0150CommonLogic;
import business.servlet.NMAL0150.constant.NMAL0150Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   SRAA            K.Aratani       Create          
 * 2022/05/30   Hitachi         A.Kohinata      Update          QC#55970
 *</pre>
 */
public class NMAL0150Scrn00_Add extends S21CommonHandler implements NMAL0150Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0150BMsg scrnMsg = (NMAL0150BMsg) bMsg;
        // mod start 2022/05/30 QC#55970
        //if (scrnMsg.A.getValidCount() == scrnMsg.A.length()) {
        //    // The maximum number of data has been exceeded.
        //    scrnMsg.setMessageInfo("NMAM0050E", new String[] { String.valueOf(scrnMsg.A.length()) });
        //    throw new EZDFieldErrorException();
        //}
        if (scrnMsg.A.getValidCount() == scrnMsg.A.length()) {
            NMAL0150CommonLogic.addCheckItemDetail(ctx, scrnMsg);
            scrnMsg.putErrorScreen();
        }
        // mod end 2022/05/30 QC#55970
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // mod start 2022/05/30 QC#55970
        //return null;
        NMAL0150BMsg scrnMsg = (NMAL0150BMsg) bMsg;
        NMAL0150CMsg bizMsg = new NMAL0150CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
        // mod end 2022/05/30 QC#55970
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

    	NMAL0150BMsg scrnMsg = (NMAL0150BMsg) bMsg;
        // mod start 2022/05/30 QC#55970
        //int row = scrnMsg.A.getValidCount();
        //int count = scrnMsg.A.getValidCount() + 1;
        //scrnMsg.A.setValidCount(count);
        //scrnMsg.A.no(row).xxChkBox_A1.clear();
        //scrnMsg.A.no(row).mdseCd_A1.clear();
        //scrnMsg.A.no(row).mdseDescShortTxt_A1.clear();
        //scrnMsg.A.no(row).mdseItemTpNm_A1.clear();
        //scrnMsg.A.no(row).xxScrItem54Txt_A1.clear();
        //scrnMsg.A.no(row).coaProdCd_A1.clear();
        //scrnMsg.A.no(row).rqstTotStdCostAmt_A1.clear();
        //scrnMsg.A.no(row).pkgUomCd_A1.clear();
        //scrnMsg.A.no(row).mdseCstUpdEffFromDt_A1.clear();
        //scrnMsg.A.no(row).mdseCstUpdStsNm_A1.clear();
        //NMAL0150CommonLogic.changeActivation(this, getUserProfileService(), scrnMsg);
        //scrnMsg.setFocusItem(scrnMsg.A.no(row).mdseCd_A1);
        NMAL0150CMsg bizMsg = (NMAL0150CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NMAL0150CommonLogic.changeActivation(this, getUserProfileService(), scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.A.getValidCount() - 1).mdseCd_A1);
        // mod end 2022/05/30 QC#55970
    }

}
