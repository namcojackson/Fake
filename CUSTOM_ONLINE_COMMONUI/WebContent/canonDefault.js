var crtHomeTab=null;
var crtLevel3LeftMenu=null;
var currentParentMenuContainer;
var currentJsDropdown=null;

currentParentMenuContainer=new Array();
/*
currentParentMenuContainer should contain expanded menu IDs when it loads:
currentParentMenuContainer[0] - level 1 expanded menu ID
currentParentMenuContainer[1] - level 2 expanded menu ID
currentParentMenuContainer[2] - level 3 expanded menu ID

This will not automatically expand the menus. This is done in the leftnavbar using stylesheets.
The IDs are used to collapse the menus when the user clicks on other menus
*/
var _fctReturnFalse=new Function ("e", "return false;");
var _fctReturnTrue=new Function ("e", "return true;");

function expandLeftMenu(parentMenuId, level)
{
	var style, parentMenuContainer, submenuContainer, prevMenuContainer, expand, classFirstListItem;
	
	parentMenuContainer=document.getElementById(parentMenuId);
	submenuContainer=document.getElementById(parentMenuId+"_submenu");
	
	style=getElementCurrentStyle(submenuContainer);
	expand = !(style.display=="block");
	
	if (expand) //collapse current menu (if any)
	{
		prevMenuContainer=document.getElementById(currentParentMenuContainer[level-1]);
		if (prevMenuContainer && prevMenuContainer!=parentMenuContainer)
		{
			classFirstListItem=(prevMenuContainer.className.indexOf(new String("first_list_item").toLowerCase())>-1)?"first_list_item":"";
			prevMenuContainer.className=classFirstListItem?classFirstListItem:"";
		}
	}
	
	classFirstListItem=(parentMenuContainer.className.indexOf(new String("first_list_item").toLowerCase())>-1)?"first_list_item":""
	parentMenuContainer.className=(expand?"expanded":"")+(classFirstListItem?" "+classFirstListItem:"");
	
	
	currentParentMenuContainer[level-1]=parentMenuId;
}

function getElementCurrentStyle(element)
{
	if (element.currentStyle)
	{ 
		return element.currentStyle;
	}
	else
	{
		if (document.defaultView.getComputedStyle)
		{
			return document.defaultView.getComputedStyle(element, null);
		}
	}
}
function domAddEventListener(element, eventName, eventHandler)
{
	if (element.attachEvent)
	{
		element.attachEvent("on"+eventName, eventHandler);
	}
	else
	if (element.addEventListener)
	{
		element.addEventListener(eventName, eventHandler, false);
	}
}
function domRemoveEventListener(element, eventName)
{
	if (element.detachEvent)
	{
		element.detachEvent(eventName);
	}
	else
	if (element.removeEventListener)
	{
		element.removeEventListener(eventName)
	}
}
function showWhereToBuyContainer(a, hideEStoreButton)
{
	var arrowImg, containerVisible;
	
	containerVisible=switchElementVisibility("where_to_buy");
	if (hideEStoreButton)
	{
		switchElementVisibility("buy_now_estore");
	}
	
	arrowImg=document.getElementById("arrowWhereToBuy");
	
	arrowImg.src="images/utility_navbar/arrow_"+(containerVisible?"close":"open")+".gif";
	a.className=containerVisible?"open":"";
}
function switchElementVisibility(elementId)
{
	var element, style;
	
	element=document.getElementById(elementId);
	style=getElementCurrentStyle(element);
	
	element.style.display=style.display=="none"?"block":"none";
	
	if (style.display!="none")
	{
		element.style.zIndex=1000;
	}
	
	return !(element.style.display=="none");
}
function showOtherProductsList()
{
	var otherProductsListContainer, style;
	
	otherProductsListContainer=document.getElementById("other_products_listing");
	
	style=getElementCurrentStyle(otherProductsListContainer);
	
	otherProductsListContainer.style.display=style.display=="block"?"none":"block";
	if (otherProductsListContainer.style.display=="block")
	{
		
		otherProductsListContainer.style.zIndex=1100;
		otherProductsListContainer.parentNode.parentNode.style.zIndex=1100;
	}
}
function showJsDropdown(jsDropdownId)
{	
	var style;
	var dropdown, dropdownContainer;
	
	dropdown=document.getElementById(jsDropdownId+"_Dropdown");
	
	style=getElementCurrentStyle(dropdown);
	
	if (style.display=="block")
	{
		dropdown.style.display="none";
		currentJsDropdown=null;
		
	}
	else
	{
		if (currentJsDropdown && currentJsDropdown!=dropdown)
		{
			currentJsDropdown.style.display="none";
			currentJsDropdown.style.zIndex=-1;
		}
		
		currentJsDropdown=dropdown;
		currentJsDropdown.style.zIndex=1000;
		currentJsDropdown.style.display="block";
		currentJsDropdown.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.style.zIndex=1000;
		currentJsDropdown.scrollTop=0;
	}
}
function setJsDropdownValue(jsDropdownId, value, text)
{
	var input, element;
	
	input=document.getElementById(jsDropdownId);//may be a div or text input
	input.value=value;
	
	element=document.getElementById(jsDropdownId+"_text")
	switch(element.nodeName.toLowerCase())
	{
		case "div":
			element.innerHTML = text;
			element.title=text;
			break;
		case "input":
			element.value=text;
			break;
	}
	
	showJsDropdown(jsDropdownId)
}
function onFocusZipCode(input)
{
    if (input.value=="Enter Zip Code")
    {
	input.value="";
    }
}
function onBlurZipCode(input)
{
    if (input.value=="")
    {
	input.value="Enter Zip Code";
    }
}
function checkProduct(element)
{
	var inputId;
	var input, a;
	
	
	if (element.nodeName.toLowerCase()=="input")//checkbox clicked
	{
		input=element;
		inputId=element.id;
		
		a=document.getElementById(inputId+"_Compare");
	}
	else //"Compare" link clicked
	{
		a=element;
		
		inputId=element.id.replace("_Compare", "");
		input=document.getElementById(inputId);
		input.checked=!input.checked;
		
	}
	
	var checkboxesList, hotCompareLinks, numChecked;
	
	hotCompareLinks=false;
	numChecked=0
	checkboxesList=document.getElementsByTagName("input");
	
	if(checkboxesList)
	{
		for (var i=0; i<checkboxesList.length; i ++)
		{
			var input=checkboxesList[i];
			if(input.type!="checkbox")
			{
				continue;
			}
			if (input.checked)
			{
				numChecked++;
			}
			if (numChecked>1)
			{
				hotCompareLinks=true;
			}
		}
		
		for (var i=0; i<checkboxesList.length; i ++)
		{
			
			var input=checkboxesList[i];
			
			if(input.type!="checkbox")
			{
				continue;
			}
			
			inputId=input.id;
			a=document.getElementById(inputId+"_Compare");
			
			if (!hotCompareLinks)
			{
				a.className="";
				a.onclick=_fctReturnFalse;
			}
			else
			{
				a.className=input.checked?"selected":"activated"
				a.onclick=input.checked?_fctReturnTrue:_fctReturnFalse;
			}
		}
		
	}
}
/*advanced search*/
var currentProductCategoryLink=null;
var currentProductCategoryId="testProductCategoryId1";
var currentProductTypeLink=null;
var currentProductTypeId="testProductTypeId1";
var currentProductModelLink=null;
var currentProductModelId="testProductModelId1";
var currentProductFamilyLink=null;
var currentProductFamilyId="testProductFamilyId2";
var currentProductSerieLink=null;
var currentProductSerieId="testProductSerieId2";
function onClickAdvancedSearchCriteria(link, currentLink)
{
	if (currentLink)
	{
		currentLink.className="";
	}
	
	link.className="selected";

	
}
function onClickProductCategory(link)
{
	if (!currentProductCategoryLink && currentProductCategoryId)
	{
		currentProductCategoryLink=document.getElementById(currentProductCategoryId);
	}
	onClickAdvancedSearchCriteria(link, currentProductCategoryLink);
	currentProductCategoryLink=link;
}
function onClickProductType(link)
{
	if (!currentProductTypeLink && currentProductTypeId)
	{
		currentProductTypeLink=document.getElementById(currentProductTypeId);
	}
	onClickAdvancedSearchCriteria(link, currentProductTypeLink);
	currentProductTypeLink=link;
}
function onClickProductModel(link)
{
	if (!currentProductModelLink && currentProductModelId)
	{
		currentProductModelLink=document.getElementById(currentProductModelId);
	}
	onClickAdvancedSearchCriteria(link, currentProductModelLink);
	currentProductModelLink=link;
}
function onClickProductFamily(link)
{
	if (!currentProductFamilyLink && currentProductFamilyId)
	{
		currentProductFamilyLink=document.getElementById(currentProductFamilyId);
	}
	onClickAdvancedSearchCriteria(link, currentProductFamilyLink);
	currentProductFamilyLink=link;
}
function onClickProductSerie(link)
{
	if (!currentProductSerieLink && currentProductSerieId)
	{
		currentProductSerieLink=document.getElementById(currentProductSerieId);
	}
	onClickAdvancedSearchCriteria(link, currentProductSerieLink);
	currentProductSerieLink=link;
}
function onClickProdAdvCategory(elementId)
{
	var td;
	
	td=document.getElementById(elementId);
	
	td.className=(td.className.indexOf(new String("expanded").toLowerCase())>-1)?"":"expanded"
	
}
var currentRSSCategoryLink;
var currentRSSCategoryLinkId="press_releases";

function onClickRSSCategory(elementId)
{
	var li;
	li=document.getElementById(elementId);
	
	li.className=(li.className.indexOf(new String("expanded").toLowerCase())>-1)?"":"expanded";
	
	if (!currentRSSCategoryLink && currentRSSCategoryLinkId)
	{
		currentRSSCategoryLink=document.getElementById(currentRSSCategoryLinkId);
	}
	if (currentRSSCategoryLink && currentRSSCategoryLink!=li)
	{
		currentRSSCategoryLink.className="";
	}
	currentRSSCategoryLink=li;
}
var currentEventsColumnLink;
var currentEventsColumnLinkId='date_column_link';
var eventColumnLinks, eventColumnLinkIds;
eventColumnLinkIds = new Array("date_column_link", "events_column_link", "location_column_link", "type_column_link");
function sortEvents(link)
{
	var linkClassName;
	
	if (link.className.indexOf(new String("selected").toLowerCase())==-1)
	{
		linkClassName = link.className.indexOf(new String("asc").toLowerCase())>-1 ? "asc":"desc";
		
		link.className=linkClassName+"_selected";
	}
	else
	{
		linkClassName = link.className.indexOf(new String("asc").toLowerCase())>-1 ? "desc":"asc";
		
		link.className=linkClassName+"_selected";
	}
	
	if (!currentEventsColumnLink && currentEventsColumnLinkId)
	{
		currentEventsColumnLink=document.getElementById(currentEventsColumnLinkId);
	}
	
	if (currentEventsColumnLink && currentEventsColumnLink!=link)
	{
		currentEventsColumnLink.className=currentEventsColumnLink.className.replace("selected ", "");
	}
	
	var defaultClassName, link2;
	
	defaultClassName=link.className.indexOf(new String("asc").toLowerCase())>-1?"asc":"desc";
	
	if (!eventColumnLinks)
	{
		eventColumnLinks = new Array();
		
		for (var i=0; i<eventColumnLinkIds.length; i++)
		{
			eventColumnLinks[i]=document.getElementById(eventColumnLinkIds[i]);
		}
	}
	
	for (var i=0; i<eventColumnLinks.length; i++)
	{
		
		if (eventColumnLinks[i]!=link)
		{
			eventColumnLinks[i].className=defaultClassName;
		}
	}
	
	currentEventsColumnLink=link;
}

var currentGlossaryLetterLink=null;
var currentGlossaryLetter='A';
function onClickGlossaryLetter(link)
{
	link.className+=" selected";
	
	if (!currentGlossaryLetterLink && currentGlossaryLetter)
	{
		currentGlossaryLetterLink=document.getElementById("letter"+currentGlossaryLetter);
	}
	
	if (currentGlossaryLetterLink)
	{
		currentGlossaryLetterLink.className=currentGlossaryLetterLink.className.indexOf(new String("last_letter").toLowerCase())>-1 ? "last_letter":"";
	}
	
	currentGlossaryLetterLink=link;
}
var currentProductCategoryImg, changeImgTimerId, changeImgTimerDelay;
changeImgTimerDelay=200;
function changeProductCategoryImg(src, description)
{
	if(changeImgTimerId)
	{
		clearTimeout(changeImgTimerId);
	}
	if (!currentProductCategoryImg)
	{
		currentProductCategoryImg=document.getElementById("producCategoryImg");
		currentProductCategoryImgDesc=document.getElementById("image_description");
	}
	currentProductCategoryImg.src="images/product/"+src;
	currentProductCategoryImgDesc.innerHTML=description;
}
 function setDefaultProductCategoryImg(src)
 {
 	changeImgTimerId=setTimeout("changeProductCategoryImg('"+src+"', '')", changeImgTimerDelay);
 }
 
 function onClickBuyerGuideAnswer(answerId)
 {
 	var radio;
 	
 	radio=document.getElementById(answerId);
 	
 	radio.checked=true;
 }
  function _bodyOnLoad()
 {
 	if (document.body.addEventListener)
 	{
 		document.body.addEventListener("click", onClickBody, false);
 	}
 	else
 	{
 		document.body.attachEvent("onclick", onClickBody);
 		
 	}
 }
 function onClickBody(e)
 {
 	var parentElement=null;
 	var hide=true;
 	
 	if (currentJsDropdown)
 	{
 		if (window.event)
 		{
 			parentElement=window.event.srcElement;
 			
 		}
 		else
 		{
 			parentElement=e.target;
 		}
 		
 		do
 		{	
 			if (parentElement.className=="js_dropdown")
 			{
 				//alert(parentElement.id+":"+currentJsDropdown.id+":"+parentElement.id.indexOf(currentJsDropdown.id));
 				if(parentElement.id.indexOf(currentJsDropdown.id)==0)
 				{
 					hide=false;
 				}
 				break;
 			}
 		}while (parentElement=parentElement.parentNode);
 		
 		if (hide)
 		{
 			currentJsDropdown.style.display="none";
 			
 		}
 		
 	}
 }
function onClickHomeTab(link)
{
	if (crtHomeTab && crtHomeTab!=link)
	{
		crtHomeTab.className="";
	}
	crtHomeTab=link;
	crtHomeTab.className="selected"
}

function onClickLevel3LeftMenu(link)
{
	if (crtLevel3LeftMenu && crtLevel3LeftMenu!=link)
	{
		crtLevel3LeftMenu.parentNode.className=crtLevel3LeftMenu.parentNode.className.indexOf("first_list_item")!=-1?"first_list_item":"";
	}
	
	crtLevel3LeftMenu=link;
	crtLevel3LeftMenu.parentNode.className+=" selected";
	
}