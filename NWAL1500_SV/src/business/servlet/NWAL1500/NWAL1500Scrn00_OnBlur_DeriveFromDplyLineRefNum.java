/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.TAB_LINE_CONFIG;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/27   Fujitsu         Y.Kanefusa      Create          S21_NA#10979
 * 2016/12/19   Fujitsu         S.Takami        Update          S21_NA#10979-2 (Delete unused import)
 *</pre>
 */
public class NWAL1500Scrn00_OnBlur_DeriveFromDplyLineRefNum extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        BigDecimal dsCpoLineNum = BigDecimal.ZERO;
        String dplyLineRefNum = null;
        String dsOrdPosnNum = null;
        int selectIndex = getButtonSelectNumber();
        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        if (TAB_LINE_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {

            dplyLineRefNum = scrnMsg.B.no(selectIndex).dplyLineRefNum_LL.getValue();
            dsOrdPosnNum = scrnMsg.B.no(selectIndex).dsOrdPosnNum_LL.getValue();
            dsCpoLineNum = scrnMsg.B.no(selectIndex).dsCpoLineNum_LL.getValue();
            for (int i = selectIndex + 1; i < scrnMsg.B.getValidCount(); i++) {
                NWAL1500_BBMsg line = scrnMsg.B.no(i);
                if (S21StringUtil.isEquals(dsOrdPosnNum, line.dsOrdPosnNum_LL.getValue()) 
                        && dsCpoLineNum.compareTo(line.dsCpoLineNum_LL.getValue()) == 0) {
                    ZYPEZDItemValueSetter.setValue(line.dplyLineRefNum_LL, dplyLineRefNum);
                } else {
                    break;
                }
            }
        }else{
            dplyLineRefNum = scrnMsg.D.no(selectIndex).dplyLineRefNum_RL.getValue();
            dsOrdPosnNum = scrnMsg.D.no(selectIndex).dsOrdPosnNum_RL.getValue();
            dsCpoLineNum = scrnMsg.D.no(selectIndex).dsCpoLineNum_RL.getValue();
            for (int i = selectIndex + 1; i < scrnMsg.D.getValidCount(); i++) {
                NWAL1500_DBMsg line = scrnMsg.D.no(i);
                if (S21StringUtil.isEquals(dsOrdPosnNum, line.dsOrdPosnNum_RL.getValue()) 
                        && dsCpoLineNum.compareTo(line.dsCpoLineNum_RL.getValue()) == 0) {
                    ZYPEZDItemValueSetter.setValue(line.dplyLineRefNum_RL, dplyLineRefNum);
                } else {
                    break;
                }
            }
        }
    }
}
