/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0150;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0150.NSAL0150CMsg;
import business.servlet.NSAL0150.common.NSAL0150CommonLogic;
import business.servlet.NSAL0150.constant.NSAL0150Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/03   CUSA            SRAA            Create          N/A
 * 2015/11/26   Hitachi         K.Kasai         Update          Unit Test #71
 * 2015/12/10   Hitachi         K.Kasai         Update          QC#1746
 * 2016/03/29   Hitachi         T.Tomita        Update          QC#4396
 * 2016/10/03   Hitachi         K.Kishimoto     Update          QC#12274
 * 2019/04/09   Hitachi         K.Kitachi       Update          QC#31089
 * 2024/04/08   Hitachi         S.Moriai        Update          QC#63540
 *</pre>
 */
public class NSAL0150_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), NSAL0150Constant.BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0150BMsg scrnMsg = (NSAL0150BMsg) bMsg;

        BigDecimal svcMachMstrPk = null;
        // MOD START 2015/11/26 K.Kasai [Unit Test #71]
        // String serNum = null;
        // String mdseCd = null;
        String cpoOrdNum = null;
        // MOD END 2015/11/26 K.Kasai [Unit Test #71]

        Serializable ser = getArgForSubScreen();
        if (ser instanceof Object[]) {
            Object[] params = (Object[]) ser;
            if (params != null) {
                if (params.length > 0 && params[0] != null && params[0] instanceof EZDBBigDecimalItem) {
                    svcMachMstrPk = ((EZDBBigDecimalItem) params[0]).getValue();
                }
                // MOD START 2015/11/26 K.Kasai [Unit Test #71]
//                if (params.length > 1 && params[1] != null && params[1] instanceof EZDBStringItem) {
//                    serNum = ((EZDBStringItem) params[1]).getValue();
//                }
//                if (params.length > 2 && params[2] != null && params[2] instanceof EZDBStringItem) {
//                    mdseCd = ((EZDBStringItem) params[2]).getValue();
//                }
                if (params.length > 1 && params[1] != null && params[1] instanceof EZDBStringItem) {
                    cpoOrdNum = ((EZDBStringItem) params[1]).getValue();
                }
                // MOD END 2015/11/26 K.Kasai [Unit Test #71]
            }
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.svcMachMstrPk, svcMachMstrPk);
        // MOD START 2015/11/26 K.Kasai [Unit Test #71]
//        ZYPEZDItemValueSetter.setValue(scrnMsg.serNum, serNum);
//        ZYPEZDItemValueSetter.setValue(scrnMsg.mdseCd, mdseCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.cpoOrdNum, cpoOrdNum);
        // MOD END 2015/11/26 K.Kasai [Unit Test #71]

        // ADD START 2015/11/26 K.Kasai [Unit Test #71]
        ZYPTableUtil.clear(scrnMsg.E);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();
        // ADD END 2015/11/26 K.Kasai [Unit Test #71]
        NSAL0150CMsg bizMsg = new NSAL0150CMsg();
        bizMsg.setBusinessID(NSAL0150Constant.BIZ_ID);
        bizMsg.setFunctionCode(NSAL0150Constant.EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0150BMsg scrnMsg = (NSAL0150BMsg) bMsg;
        NSAL0150CMsg bizMsg = (NSAL0150CMsg) cMsg;

        // DEL START 2015/12/10 K.Kasai [QC1746]
//        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(NSAL0150Constant.BIZ_ID);
//        NSAL0150CommonLogic.activateButtons(this, scrnMsg, functionList);
        // DEL END 2015/12/10 K.Kasai [QC1746]

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // ADD START 2015/12/10 K.Kasai [QC1746]
        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(NSAL0150Constant.BIZ_ID);
        NSAL0150CommonLogic.activateButtons(this, scrnMsg, functionList);
        // ADD END 2015/12/08 K.Kasai [QC1746]
        NSAL0150CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        // ADD START 2015/12/10 K.Kasai [QC1746]
        if (scrnMsg.B.getValidCount() == 0) {
            scrnMsg.setMessageInfo(NSAL0150Constant.NSAM0401E);
            scrnMsg.putErrorScreen();
        }
        // ADD END 2015/12/10 K.Kasai [QC1746]
        NSAL0150CommonLogic.activateScreenItems(this, functionList, scrnMsg);
        NSAL0150CommonLogic.alternateTableRowColor(scrnMsg);
        NSAL0150CommonLogic.focusItem(scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL0150BMsg scrnMsg = (NSAL0150BMsg) bMsg;
        for (int i = 0; i < scrnMsg.B.length(); i++) {
            scrnMsg.B.no(i).dsMtrReadTpCd_B.setNameForMessage("Meter Type");
            scrnMsg.B.no(i).mtrReadDt_B.setNameForMessage("Date");
            scrnMsg.B.no(i).readMtrCnt_B.setNameForMessage("New Read");
            //Del Start 2016/10/03 <QC#12274>
//            // add start 2016/03/29 CSA Defect#4396
//            scrnMsg.B.no(i).testMtrCnt_B.setNameForMessage("Test Copies");
//            // add end 2016/03/29 CSA Defect#4396
            //Del End   2016/10/03 <QC#12274>
        }
        //Add Start 2016/10/03 <QC#12274>
        scrnMsg.svcTaskNum_B.setNameForMessage("task Number");
        scrnMsg.dsTestCopyClsCd_BS.setNameForMessage("In/Out Flag");
        //Add End   2016/10/03 <QC#12274>
        // START 2019/04/09 K.Kitachi [QC#31089, ADD]
        scrnMsg.svcCmntTxt.setNameForMessage("Comment");
        // END 2019/04/09 K.Kitachi [QC#31089, ADD]
        // START 2024/04/08 S.Moriai [QC#63540, ADD]
        scrnMsg.effFromDt.setNameForMessage("From Date");
        scrnMsg.effThruDt.setNameForMessage("Thru Date");
        // END 2024/04/08 S.Moriai [QC#63540, ADD]
    }
}
