<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType= "text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="/jsp/common/systemerr.jsp" %>
<%@ page import="parts.common.*" %>
<%@ page import="parts.servletcommon.*" %>
<%@ taglib uri="/WEB-INF/ujiall.tld" prefix="uji" %>
<%@ taglib uri="/WEB-INF/ezfall.tld" prefix="ezf" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="Content-Script-Type" content="text/javascript">
    <meta http-equiv="Content-Style-Type" content="text/css">
    <script type="text/javascript">screen.updateInterval=5000;</script>
    <link rel="stylesheet" type="text/css" href="./css/common.css">
    <title>redirect</title>
  </head>

  <body style="background:#eeeeff" onunload="screen.updateInterval=0;">

    <div style="width:1010px; height:25px; overflow:hidden;">
      <table border="0" cellspacing="0" cellpadding="2" class="cHeadCloth" width="1010" style="height:24px">
        <tr>
          <td class="cSystemName" style="width:55px;">
            &nbsp;&nbsp;S21
          </td>
          <td class="cDispid" style="width:130px;">
            <div id="pageIDArea">ZZZL0010Scrn00</div>
          </td>
          <td class="cDispname" style="width:300px;">
            <div id="pageNameArea">redirect</div>
          </td>
          <td class="cUser" style="width:100px;">
            <div id="userID">&nbsp;</div>
          </td>
          <td class="cUser" style="width:180px;">
            <div id="userName">&nbsp;</div>
          </td>
          <td id="cDate" class="cDate" style="width:90px;" align="right">
            <div>&nbsp;</div>
          </td>
          <td id="cTime" class="cTime" style="width:70px;" align="right">
            <div>&nbsp;</div>
          </td>
          <td style="width:80px;" align="right">
            <div>&nbsp;</div>
          </td>
        </tr>
      </table>
    </div>

    <div style="width:1010px; height:566px; overflow:hidden; background:#eeeeff">
      <form name="redirect" method='post' action='<c:out value="${redirectUrl}" />'>
        <input type='hidden' name='randomKey' value='<c:out value="${transfInfo.randomKey}" />'>
        <c:if test="${!empty takeoverInfo.startBusinessId}">
        <input type='hidden' name='ezd.startBusinessId' value='<c:out value="${transfInfo.startBusinessId}" />'>
        </c:if>
        <input type='hidden' name='ezBusinessID' value='<c:out value="${transfInfo.ezBusinessID}" />'>
        <input type='hidden' name='ezWindowName' value='<c:out value="${transfInfo.ezWindowName}" />'>
        <input type='hidden' name='ezLoginTime' value='<c:out value="${transfInfo.ezLoginTime}" />'>
        <input type='hidden' name='ezLoginTimeZone' value='<c:out value="${transfInfo.ezLoginTimeZone}" />'>
        <input type='hidden' name='pageID' value='<c:out value="${transfInfo.pageID}" />'>
        <input type='hidden' name='jswindowname' value='<c:out value="${transfInfo.windowNames}" />'>
        <c:forEach var="i" items="${transfInfo.parameter}">
        <input type='hidden' name='<c:out value="${i.key}" />' value='<c:out value="${i.value}" />'>
        </c:forEach>
        <input type='hidden' name='trnsfObj' value='<c:out value="${trnsfObj}" />'>
<%--
        <input type='hidden' name='uji.pageid' value=''>
        <input type='hidden' name='uji.id' value='main'>
        <input type='hidden' name='pageID' value='ZZZL0010Scrn00'>
--%>
      </form>
    </div>

    <div style="width:1010px; height:25px; overflow:hidden; background:#ddddff">
      <div style="text-align:center">
        <table border=1 cellspacing=0 width="92%" class="cMsg">
          <tr>
            <td class="cMsgCloth" style="width:16%;height:20px;">
              <span id="msgCode" class="cMsgS">&nbsp;</span>
            </td>
            <td class="cMsgCloth">
              <span id="msgfield" class="cMsgS">&nbsp;It is processing...</span>
            </td>
          </tr>
        </table>
      </div>
    </div>

    <div style="width:1010px; height:28px; overflow:hidden; background:#ddddff; text-align:center">
      <table width="930">
        <tr style="text-align:center;">
          <td width="10%" class="stab">F1<input type="button" name="" value="" disabled class="cBtn"  id="btn1"></td>
          <td width="10%" class="stab">F2<input type="button" name="" value="" disabled class="cBtn"  id="btn2"></td>
          <td width="10%" class="stab">F3<input type="button" name="" value="" disabled class="cBtn"  id="btn3"></td>
          <td width="10%" class="stab">F4<input type="button" name="" value="" disabled class="cBtn"  id="btn4"></td>
          <td width="10%" class="stab">F5<input type="button" name="" value="" disabled class="cBtn"  id="btn5"></td>
          <td width="10%" class="stab">F6<input type="button" name="" value="" disabled class="cBtn"  id="btn6"></td>
          <td width="10%" class="stab">F7<input type="button" name="" value="" disabled class="cBtn"  id="btn7"></td>
          <td width="10%" class="stab">F8<input type="button" name="" value="" disabled class="cBtn"  id="btn8"></td>
          <td width="10%" class="stab">F9<input type="button" name="" value="" disabled class="cBtn"  id="btn9"></td>
          <td width="10%" class="stab">F10<input type="button" name="" value="" disabled class="cBtn"  id="btn10"></td>
        </tr>
      </table>
    </div>

    <%-- Multi screen start Shinobu Tsunak --%>
    <script type="text/javascript">
        var winnames = document.getElementById("jswindowname").value.split(";");
        var msId;
        for (msId = 0; msId < winnames.length; msId++) {
            if (winnames[msId] != null && winnames[msId].length > 0 && winnames[msId].indexOf("main_") == -1) {
                var childwin = window.open("", winnames[msId]);
                childwin.close();
            }
        }
    </script>
    <%-- Multi screen end Shinobu Tsunak --%>

    <script type="text/javascript">document.forms['redirect'].submit();</script>

  </body>
</html>
