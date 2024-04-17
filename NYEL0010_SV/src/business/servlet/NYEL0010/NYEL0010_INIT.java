/**
 * <Pre>
 * 
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 * 
 * <Pre>
 * 
 * Date Company Name Create/Update Defect No
 * ----------------------------------------------------------------------
 * 09/01/2009 Fujitsu T.Nakamatsu Create N/A
 * 
 * </Pre>
 */
package business.servlet.NYEL0010;

import java.util.HashMap;


import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NYEL0010.NYEL0010CMsg;
import business.servlet.NYEL0010.common.NYEL0010CommonLogic;
import business.servlet.NYEL0010.constant.NYEL0010Constant;

import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.online.process.S21BusinessProcessInfo;
import com.canon.cusa.s21.framework.online.process.S21BusinessProcessInfoConstant;
import com.canon.cusa.s21.framework.online.process.S21BusinessTaskInfo;
import com.canon.cusa.s21.framework.online.process.S21SelectedProcessInfo;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.transition.S21DirectScreenAccess;
import com.canon.cusa.s21.framework.online.transition.S21DirectScreenConstant;

public class NYEL0010_INIT extends S21CommonHandler implements NYEL0010Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    	
//    	String EXTNE307T010_url = getCustomAppURL("EXTNE307T010");
//    	String S21EXTN_E307_url =getCustomAppURL("S21EXTN_E307");

    }
    
//    private String getCustomAppURL(String cutomBizId) {
//		String xtnlName = "";
//			
//		try {
//			S21SecurityContext context = S21SecurityContextHolder.getContext();
//	    	if (context != null) {
//	    		S21AuthorizationAction action = context.getAuthentication().getAuthorizationAction(cutomBizId);
//		   		String resourceObjName = action.getName();
//		    	xtnlName = action.getResourceAlternativeName();
//		    	System.out.println("Resource ObjName: " + resourceObjName + " (AlternativeName: " + xtnlName + ")");
//		    //for (S21AuthorizationAction action : actions) {
//		    //	String resourceObjName = action.getName();
//		    //	String xtnlName = action.getResourceAlternativeName();
//		    //	out.print("Resource ObjName: " + resourceObjName + " (AlternativeName: " + xtnlName + ")<br>");
//	        //}
//	    	}
//		} catch (S21SecurityException aex) {
//	        //out.print("<br>"+"S21SecurityException Error:" + aex +"<br>");
//	        aex.printStackTrace();
//	    } catch (Exception e) {
//			//out.print("<br>"+"Exception Error:" + e +"<br>");
//			e.printStackTrace();
//	    }
//	    return "../" + xtnlName;
//	}

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NYEL0010BMsg scrnMsg = (NYEL0010BMsg) bMsg;

        scrnMsg.clear();

        HashMap<String, String> map = new HashMap<String, String>();
        map = NYEL0010CommonLogic.s21getMenu();

        // Order Process
        scrnMsg.menuProcGrpCd_A0.setValue(getMap(map, A_PROCESS_GROUP_ID));
        scrnMsg.menuProcId_A1.setValue(getMap(map, A_PROCESS_PROCESS_ID1));
        scrnMsg.menuProcId_A2.setValue(getMap(map, A_PROCESS_PROCESS_ID2));
        scrnMsg.menuProcId_A3.setValue(getMap(map, A_PROCESS_PROCESS_ID3));

        // SCE
        scrnMsg.menuProcGrpCd_B0.setValue(getMap(map, B_PROCESS_GROUP_ID));
        scrnMsg.menuProcId_B1.setValue(getMap(map, B_PROCESS_PROCESS_ID1));
        scrnMsg.menuProcId_B2.setValue(getMap(map, B_PROCESS_PROCESS_ID2));
        scrnMsg.menuProcId_B3.setValue(getMap(map, B_PROCESS_PROCESS_ID3));
        scrnMsg.menuProcId_B4.setValue(getMap(map, B_PROCESS_PROCESS_ID4));
        scrnMsg.menuProcId_B5.setValue(getMap(map, B_PROCESS_PROCESS_ID5));

        // Invoicing
        scrnMsg.menuProcGrpCd_C0.setValue(getMap(map, C_PROCESS_GROUP_ID));
        scrnMsg.menuProcId_C1.setValue(getMap(map, C_PROCESS_PROCESS_ID1));
        scrnMsg.menuProcId_C2.setValue(getMap(map, C_PROCESS_PROCESS_ID2));

        // AR
        scrnMsg.menuProcGrpCd_D0.setValue(getMap(map, D_PROCESS_GROUP_ID));
        scrnMsg.menuProcId_D1.setValue(getMap(map, D_PROCESS_PROCESS_ID1));
        scrnMsg.menuProcId_D2.setValue(getMap(map, D_PROCESS_PROCESS_ID2));

        // Post-Sales
        scrnMsg.menuProcGrpCd_H1.setValue(getMap(map, H_PROCESS_GROUP_ID1));
        scrnMsg.menuProcId_H1.setValue(getMap(map, H_PROCESS_PROCESS_ID1));
        scrnMsg.menuProcGrpCd_H0.setValue(getMap(map, H_PROCESS_GROUP_ID0));
        scrnMsg.menuProcId_H3.setValue(getMap(map, H_PROCESS_PROCESS_ID3));
        scrnMsg.menuProcId_H4.setValue(getMap(map, H_PROCESS_PROCESS_ID4));
        scrnMsg.menuProcId_H5.setValue(getMap(map, H_PROCESS_PROCESS_ID5));
        scrnMsg.menuProcGrpCd_H2.setValue(getMap(map, H_PROCESS_GROUP_ID2));
        scrnMsg.menuProcId_H2.setValue(getMap(map, H_PROCESS_PROCESS_ID2));

        // Financial Link
        scrnMsg.menuProcGrpCd_J0.setValue(getMap(map, J_PROCESS_GROUP_ID));
        scrnMsg.menuProcId_J1.setValue(getMap(map, J_PROCESS_PROCESS_ID1));
        scrnMsg.menuProcId_J2.setValue(getMap(map, J_PROCESS_PROCESS_ID2));

        // My Process
        scrnMsg.menuProcGrpCd_K0.setValue(getMap(map, K_PROCESS_GROUP_ID));
        scrnMsg.menuProcId_K1.setValue(S21BusinessProcessInfoConstant.PROCNAME_MYPROCESS);
        scrnMsg.menuProcId_K2.setValue(getMap(map, K_PROCESS_PROCESS_ID2));

        // Online Inquiry
        scrnMsg.menuProcGrpCd_L0.setValue(getMap(map, L_PROCESS_GROUP_ID));
        scrnMsg.menuProcId_L1.setValue(getMap(map, L_PROCESS_PROCESS_ID1));

        // DWH
        scrnMsg.menuProcGrpCd_M0.setValue(getMap(map, M_PROCESS_GROUP_ID));
        scrnMsg.menuProcId_M1.setValue(getMap(map, M_PROCESS_PROCESS_ID1));
        scrnMsg.menuProcId_M2.setValue(getMap(map, M_PROCESS_PROCESS_ID2));

        // Master
        scrnMsg.menuProcGrpCd_N0.setValue(getMap(map, N_PROCESS_GROUP_ID));
        scrnMsg.menuProcId_N1.setValue(getMap(map, N_PROCESS_PROCESS_ID1));
        scrnMsg.menuProcId_N2.setValue(getMap(map, N_PROCESS_PROCESS_ID2));
        scrnMsg.menuProcId_N3.setValue(getMap(map, N_PROCESS_PROCESS_ID3));
        scrnMsg.menuProcId_N4.setValue(getMap(map, N_PROCESS_PROCESS_ID4));
        scrnMsg.menuProcId_N5.setValue(getMap(map, N_PROCESS_PROCESS_ID5));

        // IDS
        scrnMsg.menuProcGrpCd_P0.setValue(getMap(map, P_PROCESS_GROUP_ID));
        scrnMsg.menuProcId_P1.setValue(getMap(map, P_PROCESS_PROCESS_ID1));

        // Admin Menu
        scrnMsg.menuProcGrpCd_O0.setValue(getMap(map, O_PROCESS_GROUP_ID));
        scrnMsg.menuProcId_O1.setValue(getMap(map, O_PROCESS_PROCESS_ID1));

        // Workflow
        scrnMsg.menuProcGrpCd_Z0.setValue(getMap(map, Z_PROCESS_GROUP_ID));
        scrnMsg.menuProcId_Z1.setValue(getMap(map, Z_PROCESS_PROCESS_ID1));
        
        /** 
         * START
         * [ADD] ASCC Security
         * C.Kim
         */
        // ASCC (CustomApp)
        scrnMsg.menuProcGrpCd_Q0.setValue(getMap(map, R_PROCESS_GROUP_ID));
        scrnMsg.menuProcId_Q1.setValue(getMap(map, R_PROCESS_PROCESS_ID1));
        /** 
         * END
         * [ADD] ASCC Security
         * C.Kim
         */
        
        NYEL0010CMsg bizMsg = new NYEL0010CMsg();
        bizMsg.setBusinessID("NYEL0010");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NYEL0010BMsg scrnMsg = (NYEL0010BMsg) bMsg;
        NYEL0010CMsg bizMsg = (NYEL0010CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        String beforeBizId = getSubmitedBusinessAplId(ctx);
        boolean fromOpeApp = (beforeBizId != null && beforeBizId.length() != 0 && beforeBizId.startsWith("ZZ") && !beforeBizId.startsWith("ZZPL0010") && !beforeBizId.startsWith("ZZSL0000") && !beforeBizId.startsWith("ZZOL0110") && !beforeBizId.startsWith("ZZPL0030"));
        if (fromOpeApp) {
            // Goto Admin Menu
            String bizId = "ZZOL0110";
            String processId = "";
            S21SelectedProcessInfo s21Info = new S21SelectedProcessInfo();
            s21Info.setBusinessId(bizId);
            s21Info.setProcessId(processId);
            ctx.setAttribute("SelectedS21ProcessInfo", s21Info);
            ctx.setAttribute("businessProcessInfo", null);
            ctx.setAttribute("SelectedProcessGroupId", null);
            setResult("Sysmenu");
            setJumpTransition(bizId);
        } else {
            setResult("Init");
        }

        NYEL0010CommonLogic.setCommonButton(this);

        // Chaching URL for DWH and IDS on session.
        // "S21Menu" shortcut button will read URL from this cache.
        if (scrnMsg.othSysUrl_M1 != null) {
            ctx.setAttribute("Data Warehouse URL", scrnMsg.othSysUrl_M1.getValue());
        } else {
            ctx.setAttribute("Data Warehouse URL", null);
        }

        if (scrnMsg.othSysUrl_M1 != null) {
            ctx.setAttribute("IDS URL", scrnMsg.othSysUrl_P1.getValue());
        } else {
            ctx.setAttribute("IDS URL", null);
        }
        
        /** 
         * START
         * [ADD] ASCC Security
         * C.Kim
         */
        if (scrnMsg.othSysUrl_Q1 != null) {
            ctx.setAttribute("ascc_url", scrnMsg.othSysUrl_Q1.getValue());
        } else {
            ctx.setAttribute("ascc_url", null);
        }
        /** 
         * END
         * [ADD] ASCC Security
         * C.Kim
         */
        scrnMsg.menuInfoTxt.setInputProtected(true);

        // --- Direct business screen access (bypass top menu screen) ---

        String scrTrnstId = (String)ctx.getAttribute(S21DirectScreenConstant.ATTRB_KEY_SCR_TRNST_ID);
        S21InfoLogOutput.println("Menu Screen: scrTrnstId = " + scrTrnstId);

        if (S21StringUtil.isNotEmpty(scrTrnstId)) {
            bizScreenJump(ctx, false);
            ctx.setAttribute(S21DirectScreenConstant.ATTRB_KEY_RTN_TRNST_ID, scrTrnstId);
        }

        // --- Return the specified screen from the business screen

        String rtnBizAppId = (String)ctx.getAttribute(S21DirectScreenConstant.ATTRB_KEY_RTN_BIZ_APP_ID);
        String rtnTrnstId = (String)ctx.getAttribute(S21DirectScreenConstant.ATTRB_KEY_RTN_TRNST_ID);
        S21InfoLogOutput.println("Menu Screen: rtnBizAppId = " + rtnBizAppId);
        S21InfoLogOutput.println("Menu Screen: rtnTrnstId = " + rtnTrnstId);

        if (S21StringUtil.isEmpty(scrTrnstId) &&
            S21StringUtil.isNotEmpty(rtnBizAppId) && S21StringUtil.isNotEmpty(rtnTrnstId)) {

            bizScreenJump(ctx, true);

            cleanAttributesForRtnScr(ctx);
        }

        // --- Clean Attributes of direct business screen access ---

        cleanAttributes(ctx);

    }

    /**
     * getMap
     * @param str String
     * @param map HashMap<String, String>
     * @return String
     */
    private static String getMap(HashMap<String, String> map, String str) {
        String srtn = "";
        try {
            srtn = map.get(str);
            if (srtn == null) {
                return "";
            }
        } catch (Exception e) {
            return "";
        }
        return srtn;
    }
    

    /**
     * nvl
     * 
     * @param str
     *            String
     * @return String
     */
    private static String nvl(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }

    /**
     * Biz Screen Jump
     * @param ctx EZDApplicationContext
     * @param rtnMode boolean
     */
    private void bizScreenJump(EZDApplicationContext ctx, boolean rtnMode) {
        String trnstBizAppId = null;
        String trnstMenuProcId = null;
        String trnstProcessGroupId = null;
        String trnstPrmTxt = null;
        String trnstUpTabNm = null;

        S21InfoLogOutput.println("Menu Screen: rtnMode = " + rtnMode);
        if (!rtnMode) {
            trnstBizAppId = (String)ctx.getAttribute(S21DirectScreenConstant.ATTRB_KEY_SCR_BIZ_APP_ID);
            trnstMenuProcId = (String)ctx.getAttribute(S21DirectScreenConstant.ATTRB_KEY_SCR_MENU_PROC_ID);
            trnstProcessGroupId = (String)ctx.getAttribute(S21DirectScreenConstant.ATTRB_KEY_SCR_PROC_GRP_ID);
            trnstPrmTxt = (String)ctx.getAttribute(S21DirectScreenConstant.ATTRB_KEY_SCR_PRM_TXT);
            trnstUpTabNm = (String)ctx.getAttribute(S21DirectScreenConstant.ATTRB_KEY_SCR_UPTAB_NM);
        }
        else {
            trnstBizAppId = (String)ctx.getAttribute(S21DirectScreenConstant.ATTRB_KEY_RTN_BIZ_APP_ID);
            trnstMenuProcId = (String)ctx.getAttribute(S21DirectScreenConstant.ATTRB_KEY_RTN_MENU_PROC_ID);
            trnstProcessGroupId = (String)ctx.getAttribute(S21DirectScreenConstant.ATTRB_KEY_RTN_PROC_GRP_ID);
            trnstPrmTxt = (String)ctx.getAttribute(S21DirectScreenConstant.ATTRB_KEY_RTN_PRM_TXT);
            trnstUpTabNm = (String)ctx.getAttribute(S21DirectScreenConstant.ATTRB_KEY_RTN_UPTAB_NM);
        }

        if (S21StringUtil.isEmpty(trnstBizAppId)) {
            S21InfoLogOutput.println("Menu Screen: trnstBizAppId is not set");
            return;
        }

        if (S21StringUtil.isNotEmpty(trnstMenuProcId) && S21StringUtil.isNotEmpty(trnstProcessGroupId)) {
            S21InfoLogOutput.println("Menu Screen: trnstBizAppId = " + trnstBizAppId);
            S21InfoLogOutput.println("Menu Screen: trnstMenuProcId = " + trnstMenuProcId);
            S21InfoLogOutput.println("Menu Screen: trnstProcessGroupId = " + trnstProcessGroupId);
            S21InfoLogOutput.println("Menu Screen: trnstPrmTxt = " + trnstPrmTxt);

            S21SelectedProcessInfo s21Info = new S21SelectedProcessInfo();
            s21Info.setBusinessId(trnstBizAppId);
            s21Info.setProcessId(trnstMenuProcId);
            ctx.setAttribute("SelectedS21ProcessInfo", s21Info);
            ctx.setAttribute("SelectedProcessGroupId", trnstProcessGroupId);

            // Set TAB information
            S21BusinessTaskInfo taskInfo = new S21BusinessTaskInfo();
            taskInfo.setBusinessAplID(trnstBizAppId);
            taskInfo.setCanUse(true);
            if (S21StringUtil.isNotEmpty(trnstUpTabNm)) {
                S21InfoLogOutput.println("trnstUpTabNm = " + trnstUpTabNm);
                taskInfo.setName(trnstUpTabNm);
                taskInfo.setAbbreviation(trnstUpTabNm);
            }

            S21BusinessProcessInfo businessProcessInfo = new S21BusinessProcessInfo();
            businessProcessInfo.add(taskInfo);
            ctx.setAttribute("businessProcessInfo", businessProcessInfo);
        }

        if (S21StringUtil.isNotEmpty(trnstPrmTxt)){
            Object[] scrParams = S21DirectScreenAccess.getScreenParams(trnstPrmTxt);
            if (scrParams != null && scrParams.length > 0 && scrParams[0] != null) {
                setArgForJump(scrParams);
            }
        }

        // Set transition over EZD Application ID
        setJumpTransition(trnstBizAppId);
    }

    /**
     * Clean Attributes for Biz Screen Jump
     * @param ctx EZDApplicationContext
     */
    private void cleanAttributes(EZDApplicationContext ctx) {
        ctx.setAttribute(S21DirectScreenConstant.ATTRB_KEY_SCR_TRNST_ID, null);
        ctx.setAttribute(S21DirectScreenConstant.ATTRB_KEY_SCR_BIZ_APP_ID, null);
        ctx.setAttribute(S21DirectScreenConstant.ATTRB_KEY_SCR_MENU_PROC_ID, null);
        ctx.setAttribute(S21DirectScreenConstant.ATTRB_KEY_SCR_PROC_GRP_ID, null);
        ctx.setAttribute(S21DirectScreenConstant.ATTRB_KEY_SCR_PRM_TXT, null);
        ctx.setAttribute(S21DirectScreenConstant.ATTRB_KEY_SCR_UPTAB_NM, null);
    }

    /**
     * Clean Attributes for Return screen from Biz Screen
     * @param ctx EZDApplicationContext
     */
    private void cleanAttributesForRtnScr(EZDApplicationContext ctx) {
        ctx.setAttribute(S21DirectScreenConstant.ATTRB_KEY_RTN_TRNST_ID, null);
        ctx.setAttribute(S21DirectScreenConstant.ATTRB_KEY_RTN_BIZ_APP_ID, null);
        ctx.setAttribute(S21DirectScreenConstant.ATTRB_KEY_RTN_MENU_PROC_ID, null);
        ctx.setAttribute(S21DirectScreenConstant.ATTRB_KEY_RTN_PROC_GRP_ID, null);
        ctx.setAttribute(S21DirectScreenConstant.ATTRB_KEY_RTN_PRM_TXT, null);
        ctx.setAttribute(S21DirectScreenConstant.ATTRB_KEY_RTN_UPTAB_NM, null);
    }
}
