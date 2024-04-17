/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1320;

import static business.servlet.NSAL1320.constant.NSAL1320Constant.BIZ_ID;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1320.NSAL1320CMsg;
import business.servlet.NSAL1320.NSAL1320BMsg;
import business.servlet.NSAL1320.NSAL1320_BBMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NSAL1320_NSAL1360
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/19   Hitachi         Y.Takeno        Create          N/A
 * 2018/04/16   Fujitsu         A.Kosai         Update          QC#20162
 *</pre>
 */
public class NSAL1320_NSAL1360 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL1320BMsg scrnMsg = (NSAL1320BMsg) bMsg;

        NSAL1320CMsg bizMsg = new NSAL1320CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1320BMsg scrnMsg = (NSAL1320BMsg) bMsg;
        NSAL1320CMsg bizMsg = (NSAL1320CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        List<Integer> selList = ZYPTableUtil.getSelectedRows(scrnMsg.B, "xxChkBox_B", ZYPConstant.CHKBOX_ON_Y);
        int ixA = scrnMsg.xxCellIdx.getValueInt();
        BigDecimal cpoSvcLineNum = scrnMsg.A.no(ixA).shellLineNum.getValue();
        if (!ZYPCommonFunc.hasValue(cpoSvcLineNum)) {
            return;
        }
        int ix = 0;
        for (int ixB : selList) {
            NSAL1320_BBMsg bScrnMsg = scrnMsg.B.no(ixB);
            if (!ZYPCommonFunc.hasValue(bScrnMsg.shellLineNum_B)) {
                continue;
            }
            if (cpoSvcLineNum.compareTo(bScrnMsg.shellLineNum_B.getValue()) == 0) {
                ix = ixB;
            }
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.custIssPoDt_X)) {
            scrnMsg.setFocusItem(scrnMsg.B.no(ix).custIssPoDt);
        } else if (ZYPCommonFunc.hasValue(scrnMsg.custIssPoNum_X)) {
            scrnMsg.setFocusItem(scrnMsg.B.no(ix).custIssPoNum);
        // 2018/04/16 QC#20162 Add Start
        } else if (ZYPCommonFunc.hasValue(scrnMsg.dsPoExprDt_X)) {
            scrnMsg.setFocusItem(scrnMsg.B.no(ix).dsPoExprDt);
        // 2018/04/16 QC#20162 Add End
        } else {
            scrnMsg.setFocusItem(scrnMsg.B.no(ix).mtrReadMethCd);
        }
    }
}
