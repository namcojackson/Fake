/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1360;

import static business.servlet.NSAL1360.constant.NSAL1360Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NSAL1360.constant.NSAL1360Constant.BIZ_ID;
import static business.servlet.NSAL1360.constant.NSAL1360Constant.NSAM0626E;
import static business.servlet.NSAL1360.constant.NSAL1360Constant.PRM_CNT;
import parts.common.EZDBDateItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1360.NSAL1360CMsg;
import business.servlet.NSAL1360.common.NSAL1360CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NSAL1360_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/06   Hitachi         Y.Osawa         Create          N/A
 * 2018/04/16   Fujitsu         A.Kosai         Update          QC#20162
 *</pre>
 */
public class NSAL1360_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1360BMsg scrnMsg = (NSAL1360BMsg) bMsg;
        NSAL1360CMsg bizMsg = new NSAL1360CMsg();

        // IN Parameter Get
        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null && params.length >= PRM_CNT) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.mtrReadMethCd_P, (EZDBStringItem) params[0]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.custIssPoNum_P, (EZDBStringItem) params[1]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.custIssPoDt_P, (EZDBDateItem) params[2]);
            // 2018/04/16 QC#20162 Add Start
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsPoExprDt_P, (EZDBDateItem) params[3]);
            // 2018/04/16 QC#20162 Add End
        } else {
            scrnMsg.setMessageInfo(NSAM0626E, new String[] {"" });
            return null;
        }

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1360BMsg scrnMsg = (NSAL1360BMsg) bMsg;
        if (scrnMsg.getMessageCode().endsWith(MESSAGE_KIND_ERROR)) {
            scrnMsg.setInputProtected(true);
            return;
        }
        NSAL1360CMsg bizMsg = (NSAL1360CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL1360CommonLogic.initCmnBtnProp(this);

        scrnMsg.setFocusItem(scrnMsg.mtrReadMethCd);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NSAL1360BMsg scrnMsg = (NSAL1360BMsg) bMsg;

        scrnMsg.mtrReadMethCd.setNameForMessage("Meter Method");
        scrnMsg.custIssPoNum.setNameForMessage("Customer PO Number");
        scrnMsg.custIssPoDt.setNameForMessage("Customer PO Date");
        // 2018/04/16 QC#20162 Add Start
        scrnMsg.custIssPoDt.setNameForMessage("Customer PO Expiration Date");
        // 2018/04/16 QC#20162 Add End
    }

}
