/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2500;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2500.NMAL2500CMsg;
import business.servlet.NMAL2500.constant.NMAL2500Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.treeview.S21TreeView;
import com.fujitsu.uji.compo.TreeNodeRow;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/19   Fujitsu         K.Koitabashi    Create          N/A
 *</pre>
 */
public class NMAL2500Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2500BMsg scrnMsg = (NMAL2500BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.effThruDt_H4);

        if (!ZYPCommonFunc.hasValue(scrnMsg.effThruDt_H4.getValue())) {
            scrnMsg.effThruDt_H4.setErrorInfo(1, NMAL2500Constant.ZZZM9007E, new String[] {NMAL2500Constant.NAME_FOR_MESSAGE_EFF_THRU_DT });
        }

        if (scrnMsg.effThruDt_H4.getValue().compareTo(ZYPDateUtil.getSalesDate()) < 0) {
            scrnMsg.effThruDt_H4.setErrorInfo(1, NMAL2500Constant.NMAM0044E, new String[] {NMAL2500Constant.NAME_FOR_MESSAGE_EFF_THRU_DT, NMAL2500Constant.NAME_FOR_MESSAGE_CURRENT_DT });
        }

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2500BMsg scrnMsg = (NMAL2500BMsg) bMsg;

        S21TreeView treeView = scrnMsg.getTreeView();

        if (treeView != null) {
            List<TreeNodeRow> checkedNodeList = treeView.getCheckedTreeNodeRows(NMAL2500Constant.COLUMN_INDEX_RADIO_BUTTON);
            if (!checkedNodeList.isEmpty()) {
                TreeNodeRow checkedNode = checkedNodeList.get(0);
                if (!checkedNode.isLeaf()) {
                    String nodeValue = S21TreeView.getNodeColumnValue(checkedNode);
                    if (nodeValue != null) {
                        scrnMsg.orgNm_G1.setValue(nodeValue);

                        NMAL2500CMsg bizMsg = new NMAL2500CMsg();
                        bizMsg.setBusinessID(NMAL2500Constant.BIZ_ID);
                        bizMsg.setFunctionCode(NMAL2500Constant.FUNCTION_CD_SUBMIT);
                        EZDMsg.copy(scrnMsg, null, bizMsg, null);

                        return bizMsg;
                    }
                }
            }
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2500BMsg scrnMsg = (NMAL2500BMsg) bMsg;
        NMAL2500CMsg bizMsg = (NMAL2500CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (scrnMsg.getMessageCode().endsWith(NMAL2500Constant.MESSAGE_KIND_ERR) || scrnMsg.getMessageCode().endsWith(NMAL2500Constant.MESSAGE_KIND_WARN)) {
            throw new EZDFieldErrorException();
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            scrnMsg.setMessageInfo(NMAL2500Constant.NZZM0002I);
        }

    }
}
