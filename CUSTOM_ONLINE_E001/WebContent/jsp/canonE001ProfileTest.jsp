
<%@page import="canon.apps.common.CanonCustomProfile"%>
<%@page import="canon.apps.common.CanonCommonUtilDao"%>

<%@page import="canon.apps.common.CanonS21SessionValidate"%>
<%@page import="com.canon.apps.e001.CanonE001CommonUtil"%>
<%@page import="static com.canon.apps.e001.CanonE001CommonUtil.checkErrors"%>

<%!

	public static String getSystemProfileValue(String profileName) throws Exception{
		System.out.println("in getSystemProfileValue profileName is "+profileName);
		if(profileName==null){
			return null;
		}
		Object [] result=CanonCommonUtilDao.retrieveProfileValues(null, null, profileName);
	    checkErrors(result, 1,2);
	    String val=(String)result[0];
	    return val;
	}
	

%>
<%
	String profileName = request.getParameter("profile_name");
%>

<fieldset>
	<legend>User Profile Value for Online System</legend>
	Profile Name:
	<%=CanonE001CommonUtil.checkNull(profileName)%><br> 
	Login Name :
	<%=CanonE001CommonUtil.checkNull(CanonS21SessionValidate.getUserName())%><br> 
	Profile Value: <span class="test-profile-value"><%=CanonE001CommonUtil.checkNull(CanonCustomProfile.getUserProfileValue(profileName))%></span>	<br> 
	<span style="white-space: nowrap"><pre>Java:CanonCustomProfile.getUserProfileValue("<%=profileName%>") </pre></span> <br> 
	
	
</fieldset>
<br>

<fieldset>
	<legend>System Profile Value for Batch System</legend>
	Profile Name:
	<%=CanonE001CommonUtil.checkNull(profileName)%><br> 
	Profile Value: <span class="test-profile-value"><%=CanonE001CommonUtil.checkNull(getSystemProfileValue(profileName))%></span><br>
	<span style="white-space: nowrap"><pre>Java:CanonCustomProfile.getSystemProfileValue("<%=profileName%>")</pre></span><br> 
</fieldset>
