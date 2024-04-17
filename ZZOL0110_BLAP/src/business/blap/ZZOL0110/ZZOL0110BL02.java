/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/03/2009   Fujitsu         T.Nakamatsu     Create          N/A
 *</Pre>
 */
package business.blap.ZZOL0110;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.ZZOL0110.common.ZZOL0110CommonLogic;
import business.blap.ZZOL0110.constant.ZZOL0110Constant;
import business.db.SYS_MENUTMsg;
import business.db.SYS_MENUTMsgArray;

import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

public class ZZOL0110BL02 extends S21BusinessHandler implements ZZOL0110Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("ZZOL0110_INIT".equals(screenAplID)) {
                doProcess_ZZOL0110_INIT((ZZOL0110CMsg) cMsg);
            } else if ("ZZOL0110_ZZSL1110".equals(screenAplID)) {
                doProcess_ZZOL0110_ZZSL1110((ZZOL0110CMsg) cMsg);
            } else if ("ZZOL0110Scrn00_Search".equals(screenAplID)) {
                doProcess_ZZOL0110Scrn00_Search((ZZOL0110CMsg) cMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_ZZOL0110_INIT(ZZOL0110CMsg cMsg) {

        doProcess_ZZOL0110Scrn00_CMN_Clear(cMsg);
        
        String sGlobalCpyCd = cMsg.glblCmpyCd_X1.getValue();
        if (ZZOL0110CommonLogic.getGlbCmpNm(sGlobalCpyCd, cMsg) == false) {
            cMsg.glblCmpyCd_X1.setErrorInfo(1, "ZZZM9006E", new String[] {GLOBAL_COMPANY_CODE});
            return;
        }
        
        doProcess_setScrn00(cMsg);

    }
    
    private void doProcess_ZZOL0110Scrn00_Search(ZZOL0110CMsg cMsg) {

        doProcess_ZZOL0110Scrn00_CMN_Clear(cMsg);
        
        String sGlobalCpyCd = cMsg.glblCmpyCd_X1.getValue();
        if (ZZOL0110CommonLogic.getGlbCmpNm(sGlobalCpyCd, cMsg) == false) {
            cMsg.glblCmpyCd_X1.setErrorInfo(1, "ZZZM9006E", new String[] {GLOBAL_COMPANY_CODE});
            return;
        }
        cMsg.glblCmpyCd_BK.setValue(sGlobalCpyCd);

        doProcess_setScrn00(cMsg);

    }
    
    private void doProcess_ZZOL0110_ZZSL1110(ZZOL0110CMsg cMsg) {

        String sGlobalCpyCd = cMsg.glblCmpyCd_X1.getValue();
        if (ZZOL0110CommonLogic.getGlbCmpNm(sGlobalCpyCd, cMsg) == false) {
            cMsg.glblCmpyCd_X1.setErrorInfo(1, "ZZZM9006E", new String[] {GLOBAL_COMPANY_CODE});
            return;
        }

    }


    /**
     * Method name: doProcess_ZZOL0110Scrn00_CMN_Clear
     * <dd>The method explanation: Scrn00 Clear
     * <dd>Remarks:
     * @param cMsg cMsg
     */
    private void doProcess_ZZOL0110Scrn00_CMN_Clear(ZZOL0110CMsg cMsg) {

        cMsg.A.clear();
        cMsg.A.setValidCount(MAX_ROWS);

        cMsg.B.clear();
        cMsg.B.setValidCount(MAX_ROWS);

        cMsg.C.clear();
        cMsg.C.setValidCount(MAX_ROWS);

    }

    /**
     * Method name: doProcess_setScrn00
     * <dd>The method explanation: SYS_MENU -> SubMenu(Message)
     * <dd>Remarks:
     * @param cMsg cMsg
     */
    private void doProcess_setScrn00(ZZOL0110CMsg cMsg) {
        
        String sysMenuNm   = "";
        String bizAppId    = "";
        String wfAppNm     = "";
        String sysUsbleFlg = "";
        String rqstUrl     = "";
        
        SYS_MENUTMsg rSysMenu = new SYS_MENUTMsg();
        rSysMenu.setSQLID("001");
        rSysMenu.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd_BK.getValue());
        SYS_MENUTMsgArray tMsgArray = (SYS_MENUTMsgArray) EZDTBLAccessor.findByCondition(rSysMenu);
        // No Data
        if (tMsgArray == null || tMsgArray.length() == 0) {
            cMsg.setMessageInfo("ZZZM9005W");
            return;
        }
        
        S21UserProfileService profileService = getUserProfileService();
        String userId = profileService.getContextUserInfo().getUserId();
        int j = 0;
        for (int i = 0; i < tMsgArray.length(); i++) {
            sysMenuNm    = tMsgArray.no(i).sysMenuNm.getValue();
            bizAppId     = tMsgArray.no(i).bizAppId.getValue();
            wfAppNm      = tMsgArray.no(i).wfAppNm.getValue();
            sysUsbleFlg  = tMsgArray.no(i).sysUsbleFlg.getValue();
            rqstUrl      = tMsgArray.no(i).rqstUrl.getValue();
            
            String startAppId  = getAppId(userId, wfAppNm);   
            if ((i >= 0) && (i < MAX_ROWS)) {
                cMsg.A.no(j).sysMenuNm_A1.setValue(sysMenuNm);
                cMsg.A.no(j).sysUsbleFlg_A1.setValue(sysUsbleFlg); 
                if (bizAppId.equals("")) {
                    if (startAppId.equals("")) {
                        cMsg.A.no(j).bizAppId_A1.setValue("");
                        cMsg.A.no(j).sysUsbleFlg_A1.setValue("N");                           
                    } else {
                        cMsg.A.no(j).bizAppId_A1.setValue(startAppId);
                    }
                } else {
                    cMsg.A.no(j).bizAppId_A1.setValue(bizAppId);
                    if (!chkAppId(userId, bizAppId)){
                        cMsg.A.no(j).sysUsbleFlg_A1.setValue("N"); 
                    }
                }
                cMsg.A.no(j).wfAppNm_A1.setValue(wfAppNm);
                cMsg.A.no(j).rqstUrl_A1.setValue(rqstUrl);
                j++;
            }
            if ((i >= MAX_ROWS) && (i < (MAX_ROWS * 2))) {
                if (i == MAX_ROWS) {
                    j = 0;
                }
                cMsg.B.no(j).sysMenuNm_B1.setValue(sysMenuNm);
                cMsg.B.no(j).sysUsbleFlg_B1.setValue(sysUsbleFlg);
                if (bizAppId.equals("")) {
                    if (startAppId.equals("")) {
                        cMsg.B.no(j).bizAppId_B1.setValue("");
                        cMsg.B.no(j).sysUsbleFlg_B1.setValue("N");                           
                    } else {
                        cMsg.B.no(j).bizAppId_B1.setValue(startAppId);
                    }
                } else {
                    cMsg.B.no(j).bizAppId_B1.setValue(bizAppId);
                    if (!chkAppId(userId, bizAppId)){
                        cMsg.B.no(j).sysUsbleFlg_B1.setValue("N"); 
                    }

                }
                cMsg.B.no(j).wfAppNm_B1.setValue(wfAppNm);
                cMsg.B.no(j).rqstUrl_B1.setValue(rqstUrl);
                j++;
            }
            if ((i >= (MAX_ROWS * 2)) && (i < (MAX_ROWS * 3))) {
                if (i == (MAX_ROWS * 2)) {
                    j = 0;
                }
                cMsg.C.no(j).sysMenuNm_C1.setValue(sysMenuNm);
                cMsg.C.no(j).sysUsbleFlg_C1.setValue(sysUsbleFlg);
                if (bizAppId.equals("")) {
                    if (startAppId.equals("")) {
                        cMsg.C.no(j).bizAppId_C1.setValue("");
                        cMsg.C.no(j).sysUsbleFlg_C1.setValue("N");                           
                    } else {
                        cMsg.C.no(j).bizAppId_C1.setValue(startAppId);
                    }
                } else {
                    cMsg.C.no(j).bizAppId_C1.setValue(bizAppId);
                    if (!chkAppId(userId, bizAppId)){
                        cMsg.C.no(j).sysUsbleFlg_C1.setValue("N"); 
                    }

                }
                cMsg.C.no(j).wfAppNm_C1.setValue(wfAppNm);
                cMsg.C.no(j).rqstUrl_C1.setValue(rqstUrl);
                j++;
            }
        }
    }

    /**
     * Method name: getAppId
     * <dd>The method explanation: 
     * <dd>Remarks:
     * @param userId String
     * @param processId String
     * @return String
     */
    private String getAppId(String userId, String processId) {

        if (processId.equals("")) {
            return "";
        }

        // Get BusinessID from UP_TAB,MENU_PROC (ProcessID)
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("GLBL_CMPY_CD", getGlobalCompanyCode());
        param.put("MENU_PROC_ID", processId);
        S21SsmEZDResult ssmResult = ZZOL0110Query.getInstance().getTabInfoListForProc(param);
        if (!ssmResult.isCodeNormal()) { // No Data
            return "";
        }
        // Normal
        List resultList = (List) ssmResult.getResultObject();

        // Pick a biz app id to transit
        S21UserProfileService profileService = getUserProfileService();
        for (Iterator iterator = resultList.iterator(); iterator.hasNext();) {
            Map resultMap = (Map) iterator.next();
            String bizAppId = nvl((String) resultMap.get("BIZ_APP_ID"));
            if (profileService.isBusinessAppGranted(userId, bizAppId)) {
                return bizAppId;
            }
        }
        return "";
    }
    /**
     * Method name: getAppId
     * <dd>The method explanation: 
     * <dd>Remarks:
     * @param userId String
     * @param processId String
     * @return String
     */
    private boolean chkAppId(String userId, String bizAppId) {

        // Pick a biz app id to transit
        S21UserProfileService profileService = getUserProfileService();

        if (profileService.isBusinessAppGranted(userId, bizAppId)) {
            return true;
        }
        return false;
    }
    /**
     * Method name: nvl
     * <dd>The method explanation: 
     * <dd>Remarks:
     * @param str String
     * @return String
     */
    private static String nvl(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }
}
