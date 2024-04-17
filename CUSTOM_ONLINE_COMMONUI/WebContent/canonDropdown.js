var currentDropdownId, currentDropdownMenuId, showDropdownDelay, hideDropdownDelay, currentDropdownHasCapture;
var showTimerId, hideTimerId;

showDelay=50;
hideDelay=500;

function showDropdown(dropdownMenuId, show)
{
	var dropdownId;
	
	dropdownId=dropdownMenuId+"_sub";
	if (show)
	{
		dropdown=document.getElementById(dropdownId);
		
		if (currentDropdownId && currentDropdownId!=dropdownId)
		{
			currentDropdown=document.getElementById(currentDropdownId);
			if (currentDropdown)
			{
				currentDropdown.style.display="none";
			}
			currentDropdownMenu=document.getElementById(currentDropdownMenuId);
			if (currentDropdownMenu)
			{
				currentDropdownMenu.className="";
			}
		}
		
		currentDropdownHasCapture=true;
		
		currentDropdownId=dropdownId;
		currentDropdownMenuId=dropdownMenuId;
		
		if (showTimerId)
		{
			clearTimeout(showTimerId);
		}
		if (dropdownMenuId)
		{
			showTimerId=setTimeout("showDropdownContainer('"+dropdownId+"', true)", showDelay);
		}
	}
	else
	{
		currentDropdownHasCapture=false;
			
		hideTimerId=setTimeout("showDropdownContainer('"+dropdownId+"', false)", hideDelay);
	}
}
function showDropdownContainer(dropdownId, show, forceHide)
{
	var dropdown, li;
	if (!show && currentDropdownHasCapture)
	{
		return;
	}
	
	dropdown=document.getElementById(dropdownId);
	if (dropdown)
	{
		dropdown.style.display=show?"block":"none";
		if (show)
		{
			dropdown.style.zIndex=1000;
			if (dropdown.parentNode)
			{
				dropdown.parentNode.style.zIndex=1000;
			}
		
		}
	}
	li=document.getElementById(currentDropdownMenuId);
	if (li)
	{
		li.className=show?"selected":"";
	}
}
function getDropdownCapture()
{
	currentDropdownHasCapture=true;
}