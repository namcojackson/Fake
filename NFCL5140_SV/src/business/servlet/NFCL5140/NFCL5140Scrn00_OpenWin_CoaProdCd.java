/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/15/2010   Fujitsu         I.Kondo         Create          N/A
 * </pre>
 */
package business.servlet.NFCL5140;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFCL5140.common.NFCL5140CommonLogic;
import business.servlet.NFCL5140.constant.NFCL5140Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * NFCL5140Scrn00_OpenWin_CoaProdCd class.
 */
public class NFCL5140Scrn00_OpenWin_CoaProdCd extends S21CommonHandler implements NFCL5140Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL5140BMsg scrnMsg = (NFCL5140BMsg) bMsg;

        scrnMsg.xxTblNm.setValue(PRM_OPEN_WIN_PROD_0);
        scrnMsg.xxTblCdColNm.setValue(PRM_OPEN_WIN_PROD_1);
        scrnMsg.xxTblNmColNm.setValue(PRM_OPEN_WIN_PROD_2);
        scrnMsg.xxTblSortColNm.setValue(PRM_OPEN_WIN_PROD_3);
        scrnMsg.xxScrNm.setValue(PRM_OPEN_WIN_PROD_4);
        scrnMsg.xxHdrCdLbNm.setValue(PRM_OPEN_WIN_PROD_5);
        scrnMsg.xxHdrNmLbNm.setValue(PRM_OPEN_WIN_PROD_6);
        scrnMsg.xxDtlCdLbNm.setValue(PRM_OPEN_WIN_PROD_7);
        scrnMsg.xxDtlNmLbNm.setValue(PRM_OPEN_WIN_PROD_8);
        //scrnMsg.xxCondCd.setValue(scrnMsg.coaProdCd.getValue());
        scrnMsg.xxCondNm.clear();

        setArgForSubScreen(NFCL5140CommonLogic.getParams_for_NMAL6050(scrnMsg));

        NFCL5140CommonLogic.protectDetailCmnt(scrnMsg);

    }

}