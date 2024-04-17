/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1320;

import static business.servlet.NSAL1320.constant.NSAL1320Constant.NSAM0019E;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.PRM_CNT_MASS_APPLY;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NSAL1320.NSAL1320BMsg;
import business.servlet.NSAL1320.NSAL1320_BBMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NSAL1320Scrn00_OpenWin_MassApply
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/19   Hitachi         Y.Takeno        Create          N/A
 * 2018/04/16   Fujitsu         A.Kosai         Update          QC#20162
 *</pre>
 */
public class NSAL1320Scrn00_OpenWin_MassApply extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
        NSAL1320BMsg scrnMsg = (NSAL1320BMsg) bMsg;
        int ix = getButtonSelectNumber();
        scrnMsg.xxCellIdx.setValue(getButtonSelectNumber());

        BigDecimal cpoSvcLineNum = scrnMsg.A.no(ix).shellLineNum.getValue();
        List<Integer> selList = ZYPTableUtil.getSelectedRows(scrnMsg.B, "xxChkBox_B", ZYPConstant.CHKBOX_ON_Y);
        int targetCnt = 0;
        for (int ixB : selList) {
            NSAL1320_BBMsg bScrnMsg = scrnMsg.B.no(ixB);
            if (cpoSvcLineNum.compareTo(bScrnMsg.shellLineNum_B.getValue()) != 0) {
                continue;
            }
            targetCnt++;
        }
        if (targetCnt == 0) {
            if (scrnMsg.B.getValidCount() == 0) {
                scrnMsg.setMessageInfo(NSAM0019E);
                throw new EZDFieldErrorException();
            }
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                NSAL1320_BBMsg bScrnMsg = scrnMsg.B.no(i);
                if (cpoSvcLineNum.compareTo(bScrnMsg.shellLineNum_B.getValue()) != 0) {
                    continue;
                }

                bScrnMsg.xxChkBox_B.setErrorInfo(1, NSAM0019E);
                scrnMsg.addCheckItem(bScrnMsg.xxChkBox_B);
            }
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1320BMsg scrnMsg = (NSAL1320BMsg) bMsg;

        // Clear Parameters
        scrnMsg.mtrReadMethCd_X.clear();
        scrnMsg.custIssPoNum_X.clear();
        scrnMsg.custIssPoDt_X.clear();
        // 2018/04/16 QC#20162 Add Start
        scrnMsg.dsPoExprDt_X.clear();
        // 2018/04/16 QC#20162 Add End
        //
        // Set Parameters
        Object[] params = new Object[PRM_CNT_MASS_APPLY];
        params[0] = scrnMsg.mtrReadMethCd_X;
        params[1] = scrnMsg.custIssPoNum_X;
        params[2] = scrnMsg.custIssPoDt_X;
        // 2018/04/16 QC#20162 Add Start
        params[3] = scrnMsg.dsPoExprDt_X;
        // 2018/04/16 QC#20162 Add End

        setArgForSubScreen(params);
    }
}
