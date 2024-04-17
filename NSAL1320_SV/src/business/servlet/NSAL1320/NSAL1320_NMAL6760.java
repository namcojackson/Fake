/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1320;

import static business.servlet.NSAL1320.constant.NSAL1320Constant.IX_POP_PRM_CTY_ADDR;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.IX_POP_PRM_FIRST_LINE_ADDR;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.IX_POP_PRM_FRTH_LINE_ADDR;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.IX_POP_PRM_POST_CD;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.IX_POP_PRM_SCD_LINE_ADDR;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.IX_POP_PRM_ST_CD;
import static business.servlet.NSAL1320.constant.NSAL1320Constant.IX_POP_PRM_THIRD_LINE_ADDR;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NSAL1320.NSAL1320BMsg;
import business.servlet.NSAL1320.NSAL1320_ABMsg;
import business.servlet.NSAL1320.NSAL1320_WBMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NSAL1320_NMAL6760
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/19   Hitachi         Y.Takeno        Create          N/A
 *</pre>
 */
public class NSAL1320_NMAL6760 extends S21CommonHandler {

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

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }

        NSAL1320BMsg scrnMsg = (NSAL1320BMsg) bMsg;

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        String scrEventNm = scrnMsg.xxScrEventNm.getValue();
        int ix = scrnMsg.xxCellIdx.getValueInt();
        NSAL1320_ABMsg aScrnMsg = scrnMsg.A.no(ix);
        if (ZYPCommonFunc.hasValue(aScrnMsg.soldToCustLocCd)) {
            ZYPEZDItemValueSetter.setValue(aScrnMsg.xxGenlFldAreaTxt_BI, getBillToSrchTxt(scrnMsg.W));
        } else {
            aScrnMsg.xxGenlFldAreaTxt_BI.clear();
        }

        if ("OpenWin_Customer".equals(scrEventNm)) {
            scrnMsg.setFocusItem(aScrnMsg.dsAcctNum);

        } else if ("OpenWin_Location".equals(scrEventNm)) {
            scrnMsg.setFocusItem(aScrnMsg.soldToCustLocCd);
        }
    }

    private String getBillToSrchTxt(NSAL1320_WBMsgArray popupOutAry) {
        return S21StringUtil.concatStrings(//
                popupOutAry.no(IX_POP_PRM_FIRST_LINE_ADDR).xxPopPrm_W.getValue() // first line address
                , " " //
                , popupOutAry.no(IX_POP_PRM_SCD_LINE_ADDR).xxPopPrm_W.getValue() // second line address
                , " " //
                , popupOutAry.no(IX_POP_PRM_THIRD_LINE_ADDR).xxPopPrm_W.getValue() // ThirdLineAddr
                , " " //
                , popupOutAry.no(IX_POP_PRM_FRTH_LINE_ADDR).xxPopPrm_W.getValue() // FrthLineAddr
                , " " //
                , popupOutAry.no(IX_POP_PRM_CTY_ADDR).xxPopPrm_W.getValue() // CtyAddr
                , "," //
                , popupOutAry.no(IX_POP_PRM_ST_CD).xxPopPrm_W.getValue() // StCd
                , " " //
                , popupOutAry.no(IX_POP_PRM_POST_CD).xxPopPrm_W.getValue() // PostCd
                );
    }

}
