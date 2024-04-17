/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2610;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL2610.common.NMAL2610CommonLogic;
import business.servlet.NMAL2610.constant.NMAL2610Constant;
import business.servlet.ZYPL0310.constant.ZYPL0310Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/06   Hitachi         Y.Takeno        Create          QC#13431
 *</pre>
 */
public class NMAL2610Scrn00_OpenWin_Attachments extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2610BMsg scrnMsg = (NMAL2610BMsg) bMsg;
        S21UserInfo userInfo = getContextUserInfo();
        String userId = userInfo.getUserId();
        S21UserProfileService profile = getUserProfileService();
        boolean authEdit = profile.isFunctionGranted(userId, NMAL2610Constant.FUNCID_UPD);

        //[0]:display mode
        if (authEdit) {
            scrnMsg.xxPopPrm_0.setValue(ZYPL0310Constant.DISPLAY_MODE_MODIFICATION);
        } else {
            scrnMsg.xxPopPrm_0.setValue(ZYPL0310Constant.DISPLAY_MODE_READ_ONLY);
        }
        //[1]:business application id
        scrnMsg.xxPopPrm_1.setValue(NMAL2610Constant.BIZ_ID);
        //[2]:attachments grouping text
        scrnMsg.xxPopPrm_2.setValue(scrnMsg.orgCd_H1.getValue());
        //[3]:function name
        scrnMsg.xxPopPrm_3.setValue(NMAL2610Constant.ATT_FUNC_NAME);
        //[4]:primary key
        scrnMsg.xxPopPrm_4.setValue(NMAL2610Constant.ATT_KEY_NAME);
        //[5]:document type list
        scrnMsg.xxPopPrm_5.setValue(NMAL2610Constant.TABLE_NAME_ATT_DOC_TP);
        //[6]:attachments limit
        scrnMsg.xxPopPrm_6.setValue(NMAL2610Constant.NUM_CONST_KEY_ATT_LIMIT_NUM);
        //[7]:authorize file extensions
        scrnMsg.xxPopPrm_7.setValue("");  //Default
        //[8]:authorize file volume(size)
        scrnMsg.xxPopPrm_8.setValue(NMAL2610Constant.NUM_CONST_KEY_ATT_DATA_VOL);

        Object[] params = NMAL2610CommonLogic.setParamForAttachmentPopup(scrnMsg, this.getGlobalCompanyCode());
        setArgForSubScreen(params);
    }
}
