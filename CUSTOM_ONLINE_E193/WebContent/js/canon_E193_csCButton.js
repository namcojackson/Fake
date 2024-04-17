

 /*==========================================================================
 | FILE 
 |	canon_E193_csCButton.js
 |   
 | DESCRIPTION
 |   This is used to print the button which looks similar to BLAF UI. There is
 |   also a generic function which can be used to submit the form.
 |
 | AUTHOR
 |	Dipti Shedji 
 |
 | CREATION DATE
 |	08/07/2005
 |
 | HISTORY
 | DATE			WHO					WHY
 |
 |
 |
 ========================================================================= */
// -->

function buttonGen() {
     document.writeln('         <table summary="" BORDER="0" CELLSPACING="0" CELLPADDING="0" nowrap>');
     document.writeln('            <tr>');
     document.writeln('               <td rowspan="4" width="11"><img alt="" src="/OA_MEDIA/ibeBtnLeft.gif" width="11" height="21"></td>');
     document.writeln('               <td class="OraBGAccentVeryDark"  valign="TOP"><img alt="" src="/OA_MEDIA/ibeBtnStretch.gif" width="1" height="1"></td>');
     document.writeln('               <td rowspan="4" width="11"><img alt="" src="/OA_MEDIA/ibeBtnRight.gif" width="11" height="21"></td>');
     document.writeln('            </tr>');
     document.writeln('            <tr>');
     document.writeln('               <td nowrap>');
     document.writeln('                   <table summary="" WIDTH="100%" BORDER="0" CELLSPACING="0" CELLPADDING="0" class="BUTTON">');
     document.writeln('                      <tr><td ALIGN="center" VALIGN="top"  height="18" nowrap class=buttonText>');

     var js=buttonGen.arguments[1];
     if (buttonGen.arguments[1]=="disabled")
      {
              document.writeln('                  <FONT class="DISABLEDBUTTONTEXT">' + buttonGen.arguments[0] +'</FONT></td></tr>');
	}
     else
      {
 	  if (js.indexOf("history")<0) 
        {
         document.writeln('                              <A  HREF="' + buttonGen.arguments[1] + '" NAME="' + buttonGen.arguments[0] +'" class="buttonText">' + buttonGen.arguments[0] +'</A></TD></tr>');
        }
        else
        {
         document.writeln('                              <A  HREF="#" onClick=' + buttonGen.arguments[1] +' class="buttonText">' + buttonGen.arguments[0]+'</A></TD></tr>');
        }
       }
     document.writeln('                   </table>');
     document.writeln('               </td>');
     document.writeln('            </tr>');
     document.writeln('            <tr>');
     document.writeln('               <td class="OraBGAccentVeryVeryDark"  valign="TOP"><img alt="" src="/OA_MEDIA/ibeBtnStretch.gif" width="1" height="1"></td> </tr>');
     document.writeln('            <tr><td class="OraBGGrayVeryDark" valign="TOP"><img alt="" src="/OA_MEDIA/ibeBtnStretch.gif" width="1" height="1"></td></tr>');
     document.writeln('         </table>');
}  

function submitForm(event,formName)

{
  
	var windowForm = document.forms[formName];
  
	windowForm.event.name = event;
 
	windowForm.submit();
  
	return true;
}


function putNbsp ()
{
  document.writeln('&nbsp');
}
