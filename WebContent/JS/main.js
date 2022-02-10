var sfs = null;
var name;
function init()
{
	trace("Application started");
	
	 $.ajax({
		 url: "Connection", 
		 success: function(result){
		
			 sfs=result.sfsOBJ;

		  }});

}

function onConnectBtClick()
{
	
	
	// Disable button
	enableButton("#connectBt", false);
	
	var jsondata=new Object();
	jsondata.sfs=sfs;
	 $.ajax({
		 url: "Checkcon",
		 data: {"smartfox": JSON.stringify(jsondata)},
		 success: function(result){
			if (result.flag=="true")
			{
				
				trace("Connected to SmartFoxServer 2X!");

				// Enable interface
				enableTextField("#usernameIn", true);
				enableButton("#loginBt", true);
				enableButton("#disconnectBt", true);
			}
			else
			{
				trace("Connection failed");

				// Enable button
				enableButton("#connectBt", true);
			}

		  }})
	

	
}


function onLoginBtClick()
{
	// Perform login
	 name = document.getElementById("usernameIn").value;

	
	 $.ajax({
		 url: "Loginreq",
		 data: {"name": name},
		 success: function(result){
				// Enable interface
				enableButton("#logoutBt", true);

				// Set user name
				$("#usernameIn").val(name);
				onroomjoin();
		 },
		 error:function(result){
			 enableTextField("#usernameIn", false);
				enableButton("#loginBt", false);
		 }
		 });
	
}


function onroomjoin(){
	
	 $.ajax({
		 url: "Roomjoin",
		 success: function(result){
				// Enable interface
			 
				enableChatArea(true, true);

				writeToChatArea("<em>You entered room '" + name + "'</em>");

		 },
		 });
	
	
}

function SendPublicMessage(){
	
	 var msg = document.getElementById("publicMsgIn").value;
//	 $.ajax({
//		 url: "publicmsg",
//		 data: {"msg": msg},
//		 success: function(result){
//				// Enable interface
//				enableButton("#logoutBt", true);
//
//				// Set user name
//				$("#usernameIn").val(name);
//				onroomjoin();
//		 },
//		 error:function(result){
//			 enableTextField("#usernameIn", false);
//				enableButton("#loginBt", false);
//		 }
//		 });
	 
	 writeToChatArea("<b>" + name + " said:</b><br/>" + msg);
	 document.getElementById("publicMsgIn").value="";
	 
	 
}


//
//function onPublicMessage(event)
//{
//	var sender = (event.sender.isItMe ? "You" : event.sender.name);
//	var nick = event.sender.getVariable("nick");
//	var aka = (!event.sender.isItMe && nick != null ? " (aka '" + nick.value + "')" : "");
//	writeToChatArea("<b>" + sender + aka + " said:</b><br/>" + event.message);
//}


function writeToChatArea(text)
{
	$("#chatAreaPn").jqxPanel("append", "<p class='chatAreaElement'>" + text + "</p>");

	// Set chat area scroll position
	$("#chatAreaPn").jqxPanel("scrollTo", 0, $("#chatAreaPn").jqxPanel("getScrollHeight"));
}


function trace(txt, showAlert)
{
	console.log(txt);

	if (showAlert)
		alert(txt);
}

function enableButton(id, doEnable)
{
	$(id).jqxButton({disabled:!doEnable});
}
function enableTextField(id, doEnable)
{
	if (doEnable)
		$(id).removeAttr("disabled");
	else
		$(id).attr("disabled", true);
}

function enableChatArea(doEnable, clear)
{
	if (clear)
	{
		$("#chatAreaPn").jqxPanel("clearcontent");
		$("#publicMsgIn").val("");
	}

	$("#chatAreaPn").jqxPanel({disabled:!doEnable});

	enableTextField("#publicMsgIn", doEnable);
	enableButton("#sendMsgBt", doEnable);

}