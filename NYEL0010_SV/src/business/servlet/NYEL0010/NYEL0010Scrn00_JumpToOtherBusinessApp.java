/**
 *<Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 *<Pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/01/2009   Fujitsu         T.Nakamatsu     Create          N/A
 *</Pre>
 */
package business.servlet.NYEL0010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NYEL0010.constant.NYEL0010Constant;

import com.canon.cusa.s21.framework.online.process.S21SelectedProcessInfo;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandlerEx;
import com.canon.cusa.s21.framework.workflow.ezd.servlet.S21EZDWfParameter;
//import com.canon.cusa.s21.framework.workflow.ezd.servlet.S21EZDWfParameter;
import com.fujitsu.uji.http.HttpDispatchContext;

public class NYEL0010Scrn00_JumpToOtherBusinessApp extends S21CommonHandlerEx
		implements NYEL0010Constant {

	@Override
	protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

	}

	@Override
	protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
		// Added on 4/13/10
		// flush the current workflow parameter
		// Once a page transition "menu > global worklist > biz app page"
		// happens,
		// S21EZDWfParameter object is created and the latest work item
		// information
		// will be kept. This causes unexpected application behaviour, so
		// This attribute must flush everytime a transition from menu to biz app
		// happens.
		S21EZDWfParameter ezdWfParameter = new S21EZDWfParameter();
		ctx.setAttribute("S21EZDWfParameter", null);

		return null;
	}

	@Override
	protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg,
			EZDCMsg cMsg) {

		NYEL0010BMsg scrnMsg = (NYEL0010BMsg) bMsg;

		String bizId = "";
		String processId = "";
		String processGroupId = "";
		S21SelectedProcessInfo s21Info = new S21SelectedProcessInfo();

		final String selectedProcess = nvl(((HttpDispatchContext) ctx
				.getDispatchContext()).getParameters().getSingleValue(
				"selectedProcess"));
		final char prefix = selectedProcess.charAt(0);

		if (selectedProcess.length() == 2) {

			// Order Process
			if (prefix == 'A') {

				processGroupId = scrnMsg.menuProcGrpCd_A0.getValue();

				if (selectedProcess.equals("A1")) {
					bizId = scrnMsg.bizAppId_A1.getValue();
					processId = scrnMsg.menuProcId_A1.getValue();

				} else if (selectedProcess.equals("A2")) {
					bizId = scrnMsg.bizAppId_A2.getValue();
					processId = scrnMsg.menuProcId_A2.getValue();

				} else if (selectedProcess.equals("A3")) {
					bizId = scrnMsg.bizAppId_A3.getValue();
					processId = scrnMsg.menuProcId_A3.getValue();

				}
			}

			// SCE
			if (prefix == 'B') {

				processGroupId = scrnMsg.menuProcGrpCd_B0.getValue();

				if (selectedProcess.equals("B1")) {
					bizId = scrnMsg.bizAppId_B1.getValue();
					processId = scrnMsg.menuProcId_B1.getValue();

				} else if (selectedProcess.equals("B2")) {
					bizId = scrnMsg.bizAppId_B2.getValue();
					processId = scrnMsg.menuProcId_B2.getValue();

				} else if (selectedProcess.equals("B3")) {
					bizId = scrnMsg.bizAppId_B3.getValue();
					processId = scrnMsg.menuProcId_B3.getValue();

				} else if (selectedProcess.equals("B4")) {
					bizId = scrnMsg.bizAppId_B4.getValue();
					processId = scrnMsg.menuProcId_B4.getValue();

				} else if (selectedProcess.equals("B5")) {
					bizId = scrnMsg.bizAppId_B5.getValue();
					processId = scrnMsg.menuProcId_B5.getValue();
				}
			}

			// Invoicing
			if (prefix == 'C') {

				processGroupId = scrnMsg.menuProcGrpCd_C0.getValue();

				if (selectedProcess.equals("C1")) {
					bizId = scrnMsg.bizAppId_C1.getValue();
					processId = scrnMsg.menuProcId_C1.getValue();

				} else if (selectedProcess.equals("C2")) {
					bizId = scrnMsg.bizAppId_C2.getValue();
					processId = scrnMsg.menuProcId_C2.getValue();
				}
			}

			// AR
			if (prefix == 'D') {

				processGroupId = scrnMsg.menuProcGrpCd_D0.getValue();

				if (selectedProcess.equals("D1")) {
					bizId = scrnMsg.bizAppId_D1.getValue();
					processId = scrnMsg.menuProcId_D1.getValue();

				} else if (selectedProcess.equals("D2")) {
					bizId = scrnMsg.bizAppId_D2.getValue();
					processId = scrnMsg.menuProcId_D2.getValue();
				}
			}

			// Post-Sales
			if (prefix == 'H') {

                processGroupId = scrnMsg.menuProcGrpCd_H0.getValue();

			    if (selectedProcess.equals("H1")) {
					processGroupId = scrnMsg.menuProcGrpCd_H1.getValue();
					bizId = scrnMsg.bizAppId_H1.getValue();
					processId = scrnMsg.menuProcId_H1.getValue();

                } else if (selectedProcess.equals("H3")) {
                    bizId = scrnMsg.bizAppId_H3.getValue();
                    processId = scrnMsg.menuProcId_H3.getValue();

                } else if (selectedProcess.equals("H4")) {
                    bizId = scrnMsg.bizAppId_H4.getValue();
                    processId = scrnMsg.menuProcId_H4.getValue();

                } else if (selectedProcess.equals("H5")) {
                    bizId = scrnMsg.bizAppId_H5.getValue();
                    processId = scrnMsg.menuProcId_H5.getValue();

                } else if (selectedProcess.equals("H2")) {
					processGroupId = scrnMsg.menuProcGrpCd_H2.getValue();
					bizId = scrnMsg.bizAppId_H2.getValue();
					processId = scrnMsg.menuProcId_H2.getValue();
				}
			}

			// Financial Link
			if (prefix == 'J') {

				processGroupId = scrnMsg.menuProcGrpCd_J0.getValue();

				if (selectedProcess.equals("J1")) {
					bizId = scrnMsg.bizAppId_J1.getValue();
					processId = scrnMsg.menuProcId_J1.getValue();

				} else if (selectedProcess.equals("J2")) {
					bizId = scrnMsg.bizAppId_J2.getValue();
					processId = scrnMsg.menuProcId_J2.getValue();
				}
			}

			// My Process
			if (prefix == 'K') {

				processGroupId = scrnMsg.menuProcGrpCd_K0.getValue();

				if (selectedProcess.equals("K1")) {
					bizId = scrnMsg.bizAppId_K1.getValue();
					processId = scrnMsg.menuProcId_K1.getValue();
				}
			}

			// Online Inquiry
			if (prefix == 'L') {

				processGroupId = scrnMsg.menuProcGrpCd_L0.getValue();

				if (selectedProcess.equals("L1")) {
					bizId = scrnMsg.bizAppId_L1.getValue();
					processId = scrnMsg.menuProcId_L1.getValue();
				}
			}

			// Master
			if (prefix == 'N') {

				processGroupId = scrnMsg.menuProcGrpCd_J0.getValue();

				if (selectedProcess.equals("N1")) {
					bizId = scrnMsg.bizAppId_N1.getValue();
					processId = scrnMsg.menuProcId_N1.getValue();

				} else if (selectedProcess.equals("N2")) {
					bizId = scrnMsg.bizAppId_N2.getValue();
					processId = scrnMsg.menuProcId_N2.getValue();

				} else if (selectedProcess.equals("N3")) {
					bizId = scrnMsg.bizAppId_N3.getValue();
					processId = scrnMsg.menuProcId_N3.getValue();

				} else if (selectedProcess.equals("N4")) {
					bizId = scrnMsg.bizAppId_N4.getValue();
					processId = scrnMsg.menuProcId_N4.getValue();

				} else if (selectedProcess.equals("N5")) {
					bizId = scrnMsg.bizAppId_N5.getValue();
					processId = scrnMsg.menuProcId_N5.getValue();
				}
			}

            // Admin Menu
			if (prefix == 'O') {

				processGroupId = scrnMsg.menuProcGrpCd_O0.getValue();

				if (selectedProcess.equals("O1")) {
					bizId = scrnMsg.bizAppId_O1.getValue();
					processId = scrnMsg.menuProcId_O1.getValue();
				}
			}

            // Admin Menu
            if (prefix == 'Z') {

                processGroupId = scrnMsg.menuProcGrpCd_Z0.getValue();

                if (selectedProcess.equals("Z1")) {
                    bizId = scrnMsg.bizAppId_Z1.getValue();
                    processId = scrnMsg.menuProcId_Z1.getValue();
                }
            }

			// DWH(Reporting)
			if (selectedProcess.equals("M2")) {
				bizId = scrnMsg.bizAppId_M2.getValue();
				processId = scrnMsg.menuProcId_M2.getValue();
			}
            // IDS(Reporting)
            if (selectedProcess.equals("P1")) {
                bizId = scrnMsg.bizAppId_P1.getValue();
                processId = scrnMsg.menuProcId_P1.getValue();
            }
            
            
		}

		if (bizId.equals("")) {
			scrnMsg.xxErrFlg.setErrorInfo(1, "ZZZM9001E");
			scrnMsg.addCheckItem(scrnMsg.xxErrFlg);
			scrnMsg.putErrorScreen();
		}

//		// Service Parts
//		if (prefix == 'E') {
//			ctx.setAttribute("SelectedS21ProcessInfo", null);
//			ctx.setAttribute("businessProcessInfo", null);
//			ctx.setAttribute("SelectedProcessGroupId", null);
//			setJumpTransition(bizId);
//
//		} else {
//			// Not Service Parts
//
//			s21Info.setBusinessId(bizId);
//			s21Info.setProcessId(processId);
//			ctx.setAttribute("SelectedS21ProcessInfo", s21Info);
//			ctx.setAttribute("businessProcessInfo", null);
//			ctx.setAttribute("SelectedProcessGroupId", processGroupId);
//			setJumpTransition(bizId);
//
//		}
        s21Info.setBusinessId(bizId);
        s21Info.setProcessId(processId);
        ctx.setAttribute("SelectedS21ProcessInfo", s21Info);
        ctx.setAttribute("businessProcessInfo", null);
        ctx.setAttribute("SelectedProcessGroupId", processGroupId);
        setJumpTransition(bizId);

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

}
