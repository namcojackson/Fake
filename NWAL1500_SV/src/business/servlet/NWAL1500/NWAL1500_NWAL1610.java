/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.BIZ_ID;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1500.NWAL1500CMsg;
import business.servlet.NWAL1500.common.NWAL1500CommonLogicForScrnFields;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/19   Fujitsu         Y.Kanefusa      Create          N/A
 * 2016/02/20   Fujitsu         Y.Kanefusa      Update          QC#3943
 * 2016/05/10   Fujitsu         M.Yamada        Update          S21_NA#6148
 * 2017/03/02   Fujitsu         S.Takami        Update          S21_NA#17714-2
 *</pre>
 */
public class NWAL1500_NWAL1610 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        if ("CMN_Close".equals(getLastGuard()) || "CMN_Cancel".equals(getLastGuard())) {
            return null;
        }

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;

        NWAL1500CMsg bizMsg = new NWAL1500CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if ("CMN_Close".equals(getLastGuard()) || "CMN_Cancel".equals(getLastGuard())) {
            return;
        }

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        NWAL1500CMsg bizMsg = (NWAL1500CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL1500CommonLogicForScrnFields.setProtect(this, scrnMsg);//add QC#3943 
        List<Integer> selectRows = null;
        if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
            // S21_NA#6148
            selectRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, "xxChkBox_LC", "Y");
            for(int i : selectRows){
                scrnMsg.addCheckItem(scrnMsg.A.no(i).shipToCustCd_LC);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).billToCustCd_LC);
            }

            selectRows = ZYPTableUtil.getSelectedRows(scrnMsg.B, "xxChkBox_LL", "Y");
            for(int i : selectRows){
                scrnMsg.addCheckItem(scrnMsg.B.no(i).mdseCd_LL);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).ordCustUomQty_LL); // 2017/03/02 S21_NA#17714-2 Add
            }
        } else {
            // S21_NA#6148
            selectRows = ZYPTableUtil.getSelectedRows(scrnMsg.C, "xxChkBox_RC", "Y");
            for(int i : selectRows){
                scrnMsg.addCheckItem(scrnMsg.C.no(i).shipToCustCd_RC);
                scrnMsg.addCheckItem(scrnMsg.C.no(i).billToCustCd_RC);
            }

            selectRows = ZYPTableUtil.getSelectedRows(scrnMsg.D, "xxChkBox_RL", "Y");
            for(int i : selectRows){
                scrnMsg.addCheckItem(scrnMsg.D.no(i).mdseCd_RL);
                scrnMsg.addCheckItem(scrnMsg.D.no(i).ordCustUomQty_RL); // 2017/03/02 S21_NA#17714-2 Add
            }
        }
        scrnMsg.putErrorScreen();
    }
}
