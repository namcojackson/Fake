/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0310;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBDateItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0310.NSAL0310CMsg;
import business.servlet.NSAL0310.common.NSAL0310CommonLogic;
import business.servlet.NSAL0310.constant.NSAL0310Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/26   CUSA            SRAA            Create          N/A
 * 2015/10/08   Hitachi         T.Tomita        Update          N/A
 * 2016/02/16   Hitachi         A.Kohinata      Update          QC#3017
 * 2016/09/15   Hitachi         N.Arai          Update          QC#11616
 * 2016/09/27   Hitachi         Y.Zhang         Update          QC#12582
 * 2016/12/19   Hitachi         K.Kojima        Update          QC#16600
 * 2017/11/16   Hitachi         M.Kidokoro      Update          QC#22294
 * 2018/06/18   Fujitsu         T.Murai         Update          QC#26567
 *</pre>
 */
public class NSAL0310_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0310BMsg scrnMsg = (NSAL0310BMsg) bMsg;

        // START 2015/10/08 T.Tomita [N/A, MOD]
//        String dsAcctNum = null;
//        String contrEffFromDt = null;
//        String contrEffThruDt = null;
        BigDecimal dsContrPk = null;
        // END 2015/10/08 T.Tomita [N/A, MOD]
        // START 2016/12/19 K.Kojima [QC#16600,ADD]
        String serNum = null;
        // END 2016/12/19 K.Kojima [QC#16600,ADD]
        // START 2017/11/16 M.Kidokoro [QC#22294, ADD]
        String dsAcctNum = null;
        String contrEffFromDt = null;
        String contrEffThruDt = null;
        // END 2017/11/16 M.Kidokoro [QC#22294, ADD]

        ZYPTableUtil.clear(scrnMsg.P);

        Serializable ser = getArgForSubScreen();
        if (ser instanceof Object[]) {
            Object[] prm = (Object[]) ser;
            if (prm != null) {
                // START 2015/10/08 T.Tomita [N/A, MOD]
//                if (prm.length > 0 && prm[0] != null && prm[0] instanceof EZDBStringItem) {
//                    dsAcctNum = ((EZDBStringItem) prm[0]).getValue();
//                }
//                if (prm.length > 1 && prm[1] != null && prm[1] instanceof EZDBDateItem) {
//                    contrEffFromDt = ((EZDBDateItem) prm[1]).getValue();
//                }
//                if (prm.length > 2 && prm[2] != null && prm[2] instanceof EZDBDateItem) {
//                    contrEffThruDt = ((EZDBDateItem) prm[2]).getValue();
//                }
//                if (prm.length > 3 && prm[3] != null && prm[3] instanceof EZDBMsgArray) {
//                    EZDMsg.copy(((EZDBMsgArray) prm[3]), "P", scrnMsg.P, "P");
//                }
                if (prm.length > 0 && prm[0] != null && prm[0] instanceof EZDBBigDecimalItem) {
                    dsContrPk = ((EZDBBigDecimalItem) prm[0]).getValue();
                }
                // START 2016/12/19 K.Kojima [QC#16600,ADD]
                if (prm.length > 2 && prm[2] != null && prm[2] instanceof EZDBStringItem) {
                    serNum = ((EZDBStringItem) prm[2]).getValue();
                }
                // END 2016/12/19 K.Kojima [QC#16600,ADD]
                // END 2015/10/08 T.Tomita [N/A, MOD]
                // START 2018/06/18 T.Murai [QC#26567, MOD]
                // START 2017/11/16 M.Kidokoro [QC#22294, ADD]
//                if (prm.length > 0 && prm[2] != null && prm[2] instanceof EZDBDateItem) {
//                    contrEffFromDt = ((EZDBDateItem) prm[2]).getValue();
//                }
//                if (prm.length > 0 && prm[3] != null && prm[3] instanceof EZDBDateItem) {
//                    contrEffThruDt = ((EZDBDateItem) prm[3]).getValue();
//                }
//                if (prm.length > 0 && prm[4] != null && prm[4] instanceof EZDBStringItem) {
//                    dsAcctNum = ((EZDBStringItem) prm[4]).getValue();
//                }
                // END 2017/11/16 M.Kidokoro [QC#22294, ADD]
                if (prm.length > 3 && prm[3] != null && prm[3] instanceof EZDBDateItem) {
                    contrEffFromDt = ((EZDBDateItem) prm[3]).getValue();
                }
                if (prm.length > 4 && prm[4] != null && prm[4] instanceof EZDBDateItem) {
                    contrEffThruDt = ((EZDBDateItem) prm[4]).getValue();
                }
                if (prm.length > 5 && prm[5] != null && prm[5] instanceof EZDBStringItem) {
                    dsAcctNum = ((EZDBStringItem) prm[5]).getValue();
                }
                // END 2018/06/18 T.Murai [QC#26567, MOD]
            }
        }
        // START 2015/10/08 T.Tomita [N/A, MOD]
//        ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNum, dsAcctNum);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.contrEffFromDt, contrEffFromDt);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.contrEffThruDt, contrEffThruDt);
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrPk, dsContrPk);
        // END 2015/10/08 T.Tomita [N/A, MOD]
        // START 2016/12/19 K.Kojima [QC#16600,ADD]
        ZYPEZDItemValueSetter.setValue(scrnMsg.serNum, serNum);
        // END 2016/12/19 K.Kojima [QC#16600,ADD]
        // START 2017/11/16 M.Kidokoro [QC#22294, ADD]
        ZYPEZDItemValueSetter.setValue(scrnMsg.contrVrsnEffFromDt_H, contrEffFromDt);
        ZYPEZDItemValueSetter.setValue(scrnMsg.contrVrsnEffThruDt_H, contrEffThruDt);
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctNum_H, dsAcctNum);
        // END 2017/11/16 M.Kidokoro [QC#22294, ADD]

        NSAL0310CMsg bizMsg = new NSAL0310CMsg();
        bizMsg.setBusinessID(NSAL0310Constant.BIZ_ID);
        bizMsg.setFunctionCode(NSAL0310Constant.EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0310BMsg scrnMsg = (NSAL0310BMsg) bMsg;
        NSAL0310CMsg bizMsg = (NSAL0310CMsg) cMsg;

        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(NSAL0310Constant.BIZ_ID);
        NSAL0310CommonLogic.activateButtons(this, scrnMsg, functionList);

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSAL0310CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        NSAL0310CommonLogic.setupScreenItems(scrnMsg, functionList);
        NSAL0310CommonLogic.alternateTableRowColor(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.serNum);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL0310BMsg scrnMsg = (NSAL0310BMsg) bMsg;
        scrnMsg.serNum.setNameForMessage("Serial#");
        scrnMsg.mdlNm.setNameForMessage("Model");
        // [QC#3017,MOD]START
        scrnMsg.xxComnScrColValTxt_H.setNameForMessage("Installed At Location");
        // [QC#3017,MOD]END
        scrnMsg.xxScrItem29Txt.setNameForMessage("Machine Master Pk");
        // START 2016/09/15 N.Arai [QC#11616, MOD]
        // scrnMsg.mdseNm.setNameForMessage("Merchandise Name");
        // START 2016/09/27 Y.Zhang [QC#12582, MOD]
        scrnMsg.mdseDescShortTxt.setNameForMessage("Item Name");
        // END 2016/09/27 Y.Zhang [QC#12582, MOD]
        // END 2016/09/15 N.Arai [QC#11616, MOD]
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).contrEffFromDt_A.setNameForMessage("Effective From Date");
            scrnMsg.A.no(i).contrEffThruDt_A.setNameForMessage("Effective Through Date");
        }
    }
}
