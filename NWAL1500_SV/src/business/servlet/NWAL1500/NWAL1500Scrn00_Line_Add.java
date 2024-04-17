/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.MSG_PARAM_CONF_LINE;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.NWAM0697E;
import static business.servlet.NWAL1500.constant.NWAL1500MsgConstant.NWAM0774E;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.servlet.NWAL1500.common.NWAL1500CommonLogic;
import business.servlet.NWAL1500.common.NWAL1500CommonLogicForOrderEntryAction;
import business.servlet.NWAL1500.common.NWAL1500CommonLogicForScrnFields;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_ENTRY_ACT;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/12   Fujitsu         T.Yoshida       Create          N/A
 * 2016/02/18   Fujitsu         T.Ishii         Update          QC#1634
 * 2017/10/13   Fujitsu         S.Takami        Update          S21_NA#21267
 *</pre>
 */
public class NWAL1500Scrn00_Line_Add extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;

        List<Integer> selectRows = null;
        int confValidCnt = 0;
        if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
            // 2017/10/13 S21_NA#21267 Add Start
            if (NWAL1500CommonLogic.isOrderCredit(scrnMsg)) {
                String ordEntryActNm = NWAL1500CommonLogicForOrderEntryAction.getOrdEntryActNm(scrnMsg, ORD_ENTRY_ACT.ADD_LINE);
                scrnMsg.ordEntryActCd_AC.setErrorInfo(1, NWAM0774E, new String[] {ordEntryActNm});
            }
            scrnMsg.addCheckItem(scrnMsg.ordEntryActCd_AC);
            scrnMsg.putErrorScreen();
            //2017/10/13 S21_NA#21267 Add End
            selectRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_LC", "Y");
            confValidCnt = scrnMsg.A.getValidCount();
        } else {
            selectRows = ZYPTableUtil.getSelectedRows(scrnMsg.C, "xxChkBox_RC", "Y");
            confValidCnt = scrnMsg.C.getValidCount();
        }

        if (selectRows.size() == 0 && confValidCnt > 1) {
            scrnMsg.setMessageInfo(NWAM0697E, new String[] {MSG_PARAM_CONF_LINE });
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;

        NWAL1500CMsg bizMsg = new NWAL1500CMsg();
        bizMsg.setBusinessID("NWAL1500");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        NWAL1500CMsg bizMsg = (NWAL1500CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL1500CommonLogicForScrnFields.setProtect(this, scrnMsg);

        // S21_NA#1634 start
        // if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
        // NWAL1500CommonLogic.controlMdseField(scrnMsg);
        // } else {
        // NWAL1500CommonLogic.controlMdseFieldForRma(scrnMsg);
        // }
        // S21_NA#1634 end

        List<Integer> selectRows = null;
        if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
            selectRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_LC", "Y");
            if(selectRows.size() == 0){
                selectRows.add(0);
            }
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                if (scrnMsg.A.no(selectRows.get(0)).dsOrdPosnNum_LC.getValue().equals(scrnMsg.B.no(i).dsOrdPosnNum_LL.getValue())) {
                    scrnMsg.setFocusItem(scrnMsg.B.no(i).mdseCd_LL);
                }
            }
        } else {
            selectRows = ZYPTableUtil.getSelectedRows(scrnMsg.C, "xxChkBox_RC", "Y");
            if(selectRows.size() == 0){
                selectRows.add(0);
            }
            for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
                if (scrnMsg.C.no(selectRows.get(0)).dsOrdPosnNum_RC.getValue().equals(scrnMsg.D.no(i).dsOrdPosnNum_RL.getValue())) {
                    scrnMsg.setFocusItem(scrnMsg.D.no(i).mdseCd_RL);
                }
            }
        }
        
    }
}
