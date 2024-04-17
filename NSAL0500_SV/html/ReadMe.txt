※JSP生成時の注意点※

○御願い事項
  JSPの生成後、以下のコメントアウトを必ず削除してください。
  <--
  -->

  FWの制約上、HTMLのコメントアウトを外すと以下エラーが発生してしまうため。
  ※ローカルでは削除しなくても動作しますが、
    AP1環境ではsbSvcCmntTxtの定義エラーとなりAbendします。

  [Generate JSP File]
  [Project Name] NSAL0500_SV
  [Input File] C:\S21_EZDeveloper\ezdDesignDoc\wsrootMDL\NSAL0500_SV_MDL\NSAL0500Scrn00.html
  [Export Folder] C:\S21_EZDeveloper\workspace\NSAL0500_SV\gen
  [EZDJSPC.exe] C:\S21_EZDeveloper\ezdDesignDoc\wsrootMDL\NSAL0500_SV_MDL\NSAL0500Scrn00.html:217: The mistake is found in the format of the attribute description.
   > NG

Total 0 files that were processed.
○対象ソース(NSAL0500Scrn00.jsp)
<--
											<%
												StringBuilder sbSvcCmntTxtWk = new StringBuilder();
												sbSvcCmntTxtWk.append(bMsg.svcCmntTxt_71.getValue());
												sbSvcCmntTxtWk.append(bMsg.svcCmntTxt_72.getValue());
												sbSvcCmntTxtWk.append(bMsg.svcCmntTxt_73.getValue());
												sbSvcCmntTxtWk.append(bMsg.svcCmntTxt_74.getValue());
												sbSvcCmntTxtWk.append(bMsg.svcCmntTxt_75.getValue());
												sbSvcCmntTxtWk.append(bMsg.svcCmntTxt_76.getValue());
												sbSvcCmntTxtWk.append(bMsg.svcCmntTxt_77.getValue());
												sbSvcCmntTxtWk.append(bMsg.svcCmntTxt_78.getValue());
												
												String toHtml =  EZDCommonFunc.toHTMLString(sbSvcCmntTxtWk.toString());
												
												StringBuilder sbSvcCmntTxt = new StringBuilder();
												sbSvcCmntTxt.append("<textarea name=\"svcCmntTxt\" class=\"pPro\" readOnly cols=\"65\" rows=\"4\">");
												sbSvcCmntTxt.append(toHtml);
												sbSvcCmntTxt.append("</textarea>");
											%>
											<%= sbSvcCmntTxt.toString() %>
-->

