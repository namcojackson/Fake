<!--
 /*==========================================================================
       Copyright (c) 2000 Oracle Corporation, Redwood Shores, CA, USA
                          All rights reserved.
 ============================================================================
  FILENAME
     jtfdtfrm.js

  DESCRIPTION
     Created based on calendar_constructor.js in Cabo/JSUI version 0.5.

  HISTORY
     04/10/2000 pkanukol Modified.
     03/18/2000   sulee   Created.
 ========================================================================= */
// -->
<!-- $Header: jtfdtfrm.js 115.44 2005/10/24 15:03:04 yuxu ship $ -->
//Values here are all defaults, and can be overridden
var l_mon   = new Array('Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec');
if(mon_nls) var mon_nls;
var l_days  = new Array(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
var l_len  = new Array(2, 3, 4);
var l_yearbreak = 75;
var m_pos = 1;
var d_pos = 0;
var y_pos = 2;
var delim = "-";
var m_type = "N";
var JS_date ="N";
var jttNLSformat = "DD-MON-RRRR"; // CHANGED
var numericDate = 0;
var today = new Date();
var day   = today.getDate();
var month = today.getMonth();
var year;// = today.getFullYear();
var closeOnPick=true;
Calwindow = new Object;
Calwindow.closed=true;
var calwidth=260;  // CHANGED
var calheight=260;
if( !calfile ) var calfile="jtfdcall.jsp";  // CHANGED
if( !dateErrorMsg) var dateErrorMsg="You have entered a date in wrong format.";

//var dateClearMsg="Please clear the text before invoking the calendar.";
var error = false;
var midYear = 0;
var gap = 10;

function datecheck (P_value, P_NLS_format)
{//alert("datecheck");
//alert('Value : ' + P_value + ' format: ' + P_NLS_format);
  if(mon_nls) l_mon = mon_nls;
//exit if no date passed in
  if (P_value == null|P_value == ''| P_value == ' ')
  {
    var today = new Date();
    day = today.getDate();
    month = today.getMonth();
    //year  = today.getFullYear();
    return '';
  }
  if(P_NLS_format) evalNLS(P_NLS_format);//pavani

  var l_length=P_value.length;
  var l_str = new Array(' ',' ',' ');
  var temp = new Array('','','');
  var l_swap='';
  var l_splitstr='';
  var l_index=0;
  var l_splitcount=1;
  var l_char='';
  var l_prevspace=true;
  var y_extra=0;
  var d_extra=0;
  var m_extra=0;
//Commenting reference bug 3598294
  //var l_delims = /\W/
  var l_delims = /\x2D|\x2E|\x2F/

//Splits and arranges an all numeric input string into numeric values ordered D M Y
//Assumes 2 characters for month and day, and assigns what is left over to year
 /* if (parseFloat(P_value) == P_value) {
    if (P_value.length <5) {
      P_value = P_value + "????????".substr(0,8-P_value.length);
    }
    else {
      if (P_value.length <= 6) {
      y_extra=0;
      d_extra=0;
      m_extra=0;
      }
      else {
        y_extra = P_value.length - 6 ;
        if (d_pos - y_pos > 0) d_extra = y_extra;
        if (m_pos - y_pos > 0) m_extra = y_extra;
      }
    }
    l_str[0]=P_value.substr(d_pos*2+d_extra,2);
    l_str[1]=P_value.substr(m_pos*2+m_extra,2);
    l_str[2]=P_value.substr(y_pos*2,2+y_extra);
    if (l_str[1] > 12)
    {  l_swap = l_str[0];
      l_str[0]=l_str[1];
      l_str[1]=l_swap;
    }
  }
  else { */

//Otherwise, splits the string using delimiters after compressing spaces

//splits the format into 3 parts to obtain the delimiter
if (P_NLS_format) {
for( var count=0; count < P_NLS_format.length; ++count ) {
      l_char=P_NLS_format.charAt(count);
       if (l_char.search(l_delims)==0) {
        delim = l_char;
      }
  }
}

//and common delimiter characters
    for (var count=0; count < l_length; ++count ) {
      l_char=P_value.charAt(count);
      if (l_char == delim) { // yuanjing fix bug 4238327, use delimiter parsed from dateformat
         if (l_prevspace==false) {
            l_splitstr += l_char;
         }
         l_prevspace = true;
      }
      else  { l_prevspace=false;
          l_splitstr += l_char;
      }
    }

    l_length = l_splitstr.length;

//splits string by delimiter into 3 parts
    for( var count=0; count < l_length; ++count ) {
      l_char=l_splitstr.charAt(count);
      if (l_char == delim) { // yuanjing fix bug 4238327, use delimiter parsed from dateformat
        l_index = l_index + 1;
      }
      else { temp[l_index] += l_char;
      }
    }
    l_str[0]=temp[d_pos];
    l_str[1]=temp[m_pos];
    l_str[2]=temp[y_pos];
//alert('yuanjing after split to 3 parts: l_str[0]=' + l_str[0] +' l_str[1]=' + l_str[1] +' l_str[2]=' + l_str[2]);
//The array is now in the order of the NLS mask, but some sanity
//checking of the contents should still be done
//If the string already in the year slot is > 31, leave it - otherwise
//if any string is > 31 and numeric (probably the year), swap it into the year slot.

    if (!(l_str[2] > 31))
    {
      if (l_str[0] > 31 && !isNaN(l_str[0])) {
        l_swap = l_str[2];
        l_str[2] = l_str[0];
        l_str[0] = l_swap;
      }

      if (l_str[1] > 31 && !isNaN(l_str[1])) {
        l_swap = l_str[2];
        l_str[2] = l_str[1];
        l_str[1] = l_swap;
      }
    }

//strips off non-numeric characters from elements
    var strip = ""
    for (i=0; i<l_str[0].length; i++)
    { if (!isNaN(l_str[0].charAt(i))) strip += l_str[0].charAt(i); }
    //l_str[0] = (temp.length > 0)?temp + '':l_str[0] + '';
    if (strip.length > 0) l_str[0] = parseFloat(l_str[0],10) + ''

    strip = ""
    // yuanjing fix bug 4238327, this should not be used for month short name string
    if (!isNaN(l_str[1].charAt(0)))
    {
    for (i=0; i<l_str[1].length; i++)
    { if (!isNaN(l_str[1].charAt(i))) strip += l_str[1].charAt(i); }
    if (strip.length > 0) l_str[1] = parseFloat(l_str[1],10) + ''
    }
//swaps the first two strings if the first one is not numeric
//so the month string is placed into position 1 in the array, or
//swaps any value > 12 in the months slot into the day slot
  /*  if (isNaN(l_str[0])) {
      l_swap=l_str[0];
      l_str[0]=l_str[1];
      l_str[1]=l_swap;
    }
    else {
      if (l_str[1] > 12) {
        l_swap = l_str[0];
        l_str[0]=l_str[1];
        l_str[1]=l_swap;
    }  }*/


//If the month isn't numeric, checks the table of valid
//months to determine the numeric equivalent for the string.  Uses this to
//check whether the day is valid for the particular month later.

    var monthctr = 1;
    if (isNaN(l_str[1])) {

      for (var count=0; count <= 11; ++count) {
        //l_str[1] = l_str[1].substr(0,3); // yuanjing fix bug 4238327, month short name could be long

        if (l_str[1].toUpperCase() == l_mon[count].toUpperCase() ) {
          l_str[1]=(count+1)+'';
          monthctr = count;
          break; }
      }

      if (count == 12) {
        l_str[1] = "???";
      }
      else {
        if (l_str[1].length > l_len[1]) {
          l_str[1] = l_str[1].substr(0,l_len[1]);
        }
        else {
       while (l_str[1].length < l_len[1]) {
            l_str[1] = '0'+l_str[1];
      }  }  }
    }
    else {
    if (l_str[1] > 12 || l_str[1] < 1) {
      l_str[1] = "??" }
    }

//zero fills the day if less than defined length and truncates if more
    if (l_str[0].length > l_len[0]) {
      //l_str[0] = l_str[0].substr(0,l_len[0]);
    l_str[0] = '??';
    }
    if(l_str[0] < 1) l_str[0]='??';
    if(isNaN(l_str[0])) l_str[0] = '??';
    else {
      while (l_str[0].length < l_len[0]) {
        l_str[0] = '0'+l_str[0];
    }  }

//end of the "else" statement-->
//}
//If the year isn't numeric, fill with ? and treat as unrecognizable, otherwise, convert to integer before
//filling to correct length.

if (isNaN(l_str[2])) l_str[2] = "????";
else l_str[2] = parseFloat(l_str[2]) + '';

//If the year comes in as 3 digits, just pads it with a "1", otherwise fills
//it to at least 2 digits.
    if(l_str[2] < 1)
    {  if(l_len[2] == 2) l_str[2] ='??';
    else if(l_len[2] == 4) l_str[2] = '????';
    }else
    {
    if (l_str[2].length > l_len[2])
    {
      if(l_str[2].length > 4) l_str[2] = '????';
      else {
    var yr_len = l_str[2].length;
        l_str[2] = l_str[2].substr(yr_len-2,l_len[2]);
    }
    }
    else
    {
      if (l_str[2].length == 3)
    {
        //l_str[2] = '1' + l_str[2];
    l_str[2] = '????';
     }
      else
      {
        while (l_str[2].length < 2)
        {
          l_str[2] = '0'+l_str[2];
        }
      }
    }
    }

//adds century to a 2 digit year using an arbitrary split point
  if ((l_str[2].length <=2 || parseFloat(l_str[2])< 100 ) && l_str[2].indexOf("?") == -1 ) {
    l_str[2] = parseFloat(l_str[2])+'';
    while (l_str[2].length < 2) {
      l_str[2] = '0'+l_str[2];
    }
    if(l_str[2].length < l_len[2]){
    if (l_str[2] < l_yearbreak) {
      l_str[2]='20'+l_str[2];
    }
    else {
      l_str[2]='19'+l_str[2];
  }  }}

//Check if day of month is valid for month in year (check leapyear too)
  if (!isNaN(l_str[2]) && !isNaN(l_str[1])) {
       if (((l_str[2] % 4 == 0) && (l_str[2] % 100 != 0)) || (l_str[2] % 400 == 0)) {
      l_days[1] = 29;
    }
        else {
               l_days[1] = 28;
    }
    //if (l_str[0] > l_days[l_str[1]-1]) l_str[0] = l_days[l_str[1]-1];
    if (l_str[0] > l_days[l_str[1]-1]) l_str[0] = '??';
  }
//If length of month > 2, just use the last two digits in the month.
    if (l_str[1].length > 2) l_str[1] = l_str[1].substring(l_str[1].length-2);
//If length of month < 2, zero fill it
  monthctr = l_str[1];
  while (l_str[1].length < 2) { l_str[1] = '0'+l_str[1]; }

//Store numeric date for future range checking.
  numericDate = parseFloat(l_str[2]+l_str[1]+l_str[0]);
  numericDate = isNaN(numericDate)? 0 : numericDate;

//Replaces the numeric month with string from the table if type is "C"
  if (m_type == "C") {
    if (!isNaN(l_str[1]) && l_str[1] <= 12 && l_str[1] > 0) {
      l_str[1]=l_mon[l_str[1]-1]; //alert('if;'+ l_str[1]);
    }
    else   {
    if(isNaN(monthctr)|| monthctr < 0 || monthctr >= 12 || count == 12)
        l_str[1] = '???';
    else
            l_str[1]=l_mon[monthctr]; //alert('month12:'+l_str[1]+':'+ monthctr);
    } }

//Finally, swap items around into output format and add delimiters
  temp[d_pos] = l_str[0];
  temp[m_pos] = l_str[1];
  temp[y_pos] = l_str[2];

  day = temp[d_pos];
  //if(!isNaN(temp[m_pos]))
    //month = temp[m_pos];
  //else
    month = monthctr -1;
  year = temp[y_pos];

  if(JS_date == "Y") {
    month = month + 1;
    l_splitstr=temp[d_pos]+":"+month+":"+temp[y_pos];
    JS_date = "N";
  } else
    l_splitstr=temp[0]+delim+temp[1]+delim+temp[2];
  return l_splitstr;

//End of Datecheck Function
}

function formatdate (P_Day, P_Month, P_Year, P_Dateformat)
{//alert('formatdate');
//This function returns the formatted output when fed the parts of the date.
//It relies on the presence of the same global javascript variables used by the
//evalNLS function.  It also assumes valid date components (as coming from the calendar)
  //if(mon_array != null)
    //l_mon = mon_array;
  if(mon_nls) l_mon = mon_nls;
  var temp = new Array('','','');
  var l_splitstr='';
  if (P_Dateformat) evalNLS (P_Dateformat);
  if (m_type == "C") P_Month=l_mon[parseFloat(P_Month)-1];
  temp[d_pos] = P_Day;
  temp[m_pos] = P_Month;
  temp[y_pos] = P_Year;
  l_splitstr=temp[0]+delim+temp[1]+delim+temp[2];
  if(m_type != "C") l_splitstr = datecheck(l_splitstr,P_Dateformat);
  return l_splitstr;

}

function getJSDate(p_value,p_format)
{//alert('getJSDate');
    var l_splitstr ='';
    JS_date="Y";
    l_splitstr = datecheck(p_value,p_format);
    var arr=l_splitstr.split(':');
    //alert("getJSDate():"+new Date(arr[2],arr[1]-1,arr[0]));
    return (new Date(arr[2],arr[1]-1,arr[0]));
}

//Accepts the NLS format string and populates the variables necessary to apply it.
function evalNLS (P_Dateformat)
{//alert('evalNLS');
  if (P_Dateformat) { jttNLSformat = P_Dateformat; }

  m_pos=jttNLSformat.indexOf('MON');
  m_type="C";
  l_len[1]=3;
  if (m_pos < 0) { m_pos=jttNLSformat.indexOf('MM');  m_type="N"; l_len[1]=2;}
  d_pos=jttNLSformat.indexOf('DD');
  if(jttNLSformat.indexOf('RRRR') != -1)
    y_pos=jttNLSformat.indexOf('RRRR'); // CHANGED
  else if(jttNLSformat.indexOf('RR') != -1)
  {
    y_pos=jttNLSformat.indexOf('RR'); // CHANGED
    l_len[2]=2;
  }
  if (m_pos!=0) delim=jttNLSformat.charAt(m_pos - 1);
    else delim = jttNLSformat.charAt(d_pos - 1);
  if (m_pos > 5) m_pos=2; else if (m_pos < 3) m_pos=0; else m_pos=1;
  if (d_pos > 5) d_pos=2; else if (d_pos < 3) d_pos=0; else d_pos=1;
  if (y_pos > 5) y_pos=2; else if (y_pos < 3) y_pos=0; else y_pos=1;
  if((m_pos ==0 && d_pos == 0) || (m_pos ==0 && y_pos == 0)|| (d_pos ==0 && y_pos == 0)) // pavani
    alert(dateErrorMsg);
    //alert('mPos:'+m_pos + ' yPos:' + y_pos + ' dPos:' +d_pos);
}
//Returns an //alert if the date entered is in error.  This should be an official //alert.
//If the date is OK, then the date string will be passed to an optionally included
//function which is custom written by the developer.
function checkForError (obj, func)
{//alert('checkForError');
  if (obj.value.indexOf('?') != -1)
  {
    alert(dateErrorMsg);
    error = true;
// when onBlur is used, this does not give user a chance to correct the date
//    obj.focus();
  } else
  {
    if (func) func(obj.value,numericDate);
  }
}
//Required to open and control the calendar
function opencal(fieldobj,func,NLSfmt)
{//alert('opencal,error='+error);
    var openleft=100;
    var opentop=100;
    if(NLSfmt) jttNLSformat = NLSfmt;
    if((fieldobj.value).length >0 && NLSfmt && NLSfmt.length > 0)
        datecheck(fieldobj.value,NLSfmt);
    // commented out, because it requires click calendar icon twice, if date has wrong format
    //if(!error) {
        if (Calwindow.close && !Calwindow.closed) {
            // don't position calendar if already open
            var attr = "resizable=no,width=" + calwidth + ",height=" + calheight;
        } else {
            if (Nav4) {
                // center on the main window
                openleft = parseInt(window.screenX + ((window.outerWidth - calwidth) / 2),10);
                opentop = parseInt(window.screenY + ((window.outerHeight - calheight) / 2),10);
                openleft=270;
                opentop=200;
                var attr = "screenX=" + openleft + ",screenY=" + opentop;
                attr += ",resizable=no,width=" + calwidth + ",height=" + calheight;
            } else {
                // best we can do is center in screen
                openleft = parseInt(((screen.width - calwidth) / 2),10);
                opentop = parseInt(((screen.height - calheight) / 2),10);
                openleft=270;
                opentop=200;
                var attr = "left=" + openleft + ",top=" + opentop;
                attr += ",resizable=no,width=" + calwidth + ",height=" + calheight;
            }
        }
        Calwindow=open(calfile, 'Calendar', attr);
        window.fillfield = fieldobj;
        if (func) {window.afterfunc = func}
        if (Calwindow.opener == null) {
            Calwindow.opener = self;
        }
    //}
    error = false;
}

function closecal()
{//alert('closecal');
  if (Calwindow.close && !Calwindow.closed) {
    delete window.afterfunc;
    delete window.fillfield;
    Calwindow.close();
}  }

function setYrRange(midyear,diff)
{//alert('setYrRange');
     midYear = midyear;
     gap = diff;
}
