/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/15/2010   Fujitsu         I.Kondo         Create          N/A
 * </pre>
 */
package business.servlet.NFCL5140;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFCL5140.constant.NFCL5140Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * NFCL5140_NMAL6050 class.
 */
public class NFCL5140_NMAL6050 extends S21CommonHandler implements NFCL5140Constant {

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

        if (GUARD_SELECT.equals(getLastGuard())) {
        	if (PRM_OPEN_WIN_PROD_4.equals(scrnMsg.xxScrNm.getValue())) {
                scrnMsg.setFocusItem(scrnMsg.arAdjTpCd_P1);
            }
        } else {
        	if (PRM_OPEN_WIN_PROD_4.equals(scrnMsg.xxScrNm.getValue())) {
        		scrnMsg.setFocusItem(scrnMsg.arAdjTpCd_P1);
        	}
        }
        
        /*if (GUARD_SELECT.equals(getLastGuard())) {
            if (PRM_OPEN_WIN_TOC_4.equals(scrnMsg.xxScrNm.getValue())) {
                setValue(scrnMsg.tocCd, scrnMsg.xxCondCd);
                scrnMsg.setFocusItem(scrnMsg.coaProdCd);
            } else if (PRM_OPEN_WIN_PROD_4.equals(scrnMsg.xxScrNm.getValue())) {
                setValue(scrnMsg.coaProdCd, scrnMsg.xxCondCd);
                scrnMsg.setFocusItem(scrnMsg.arAdjTpCd_P1);
            }

        } else {
            if (PRM_OPEN_WIN_TOC_4.equals(scrnMsg.xxScrNm.getValue())) {
                scrnMsg.setFocusItem(scrnMsg.coaProdCd);
            } else if (PRM_OPEN_WIN_PROD_4.equals(scrnMsg.xxScrNm.getValue())) {
                scrnMsg.setFocusItem(scrnMsg.arAdjTpCd_P1);
            }

        }*/

    }

}
