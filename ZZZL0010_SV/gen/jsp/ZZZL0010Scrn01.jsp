<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType= "text/html; charset=UTF-8" pageEncoding="UTF-8" errorPage="/jsp/common/systemerr.jsp" %>
<%@ taglib uri="/WEB-INF/ujiall.tld" prefix="uji" %>
<%@ taglib uri="/WEB-INF/ezfall.tld" prefix="ezf" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="Content-Script-Type" content="text/javascript">
    <meta http-equiv="Content-Style-Type" content="text/css">
    <title>redirect</title>
    <script type="text/javascript" src="./js/common/common.js" charset="UTF-8"></script>
    <script type="text/javascript">
  <!--
  function openWin(url, targetName, businessId) {
    openApp(url, targetName, businessId);
    (open('', '_top').opener = top).close();
  }
  //-->

  </script>
  </head>

  <body>
      <script type="text/javascript">openWin('<c:out value="${redirectUrl}" />',null,null);</script>
      <script type="text/javascript">window.close();</script>
  </body>
</html>
