package chat;

import sfs2x.client.SmartFox;
import sfs2x.client.core.BaseEvent;
import sfs2x.client.core.SFSEvent;
import sfs2x.client.entities.Room;
import sfs2x.client.entities.User;
import sfs2x.client.requests.JoinRoomRequest;
import sfs2x.client.requests.LoginRequest;
import sfs2x.client.requests.LogoutRequest;
import sfs2x.client.requests.PublicMessageRequest;
import sfs2x.client.util.ConfigData;


public class Client  {
	
	
   SmartFox sfs;
   ConfigData cfg;
   String Clientname;
   String Clientpassword;
   String connectionFlag = "true";
   String message;
   
    public Client ()
    {
    	
        // Configure client connection settings
        cfg = new ConfigData();
        cfg.setHost("localhost");
        cfg.setPort(9933);
        cfg.setZone("BasicExamples");
        cfg.setDebug(false);

        // Set up event handlers
        sfs = new SmartFox();
        sfs.addEventListener(SFSEvent.CONNECTION, this::onConnection);
        sfs.addEventListener(SFSEvent.CONNECTION_LOST, this::onConnectionLost);
        sfs.addEventListener(SFSEvent.LOGIN, this::onLogin);
        sfs.addEventListener(SFSEvent.LOGIN_ERROR, this::onLoginError);
        sfs.addEventListener(SFSEvent.LOGOUT,this::onLogout);
        sfs.addEventListener(SFSEvent.ROOM_JOIN, this::onRoomJoin);
    	sfs.addEventListener(SFSEvent.ROOM_JOIN_ERROR,this::onRoomJoinError);
    	sfs.addEventListener(SFSEvent.USER_COUNT_CHANGE, this::onUserCountChange);
    	sfs.addEventListener(SFSEvent.USER_ENTER_ROOM, this::onUserEnterRoom );
    	sfs.addEventListener(SFSEvent.USER_EXIT_ROOM, this::onUserExitRoom);
    	sfs.addEventListener(SFSEvent.PUBLIC_MESSAGE, this::onPublicMessage);
    	sfs.addEventListener(SFSEvent.ROOM_VARIABLES_UPDATE, this::onRoomVariablesUpdate);
    	sfs.addEventListener(SFSEvent.USER_VARIABLES_UPDATE, this::onUserVariablesUpdate);
    	
    	
        
        // Connect to server
        sfs.connect(cfg);
    }

    public Client (SmartFox sf)
    {
    	sfs=sf;
    }
    
    // ----------------------------------------------------------------------
    // Event Handlers
    
    // ----------------------------------------------------------------------

	
    public SmartFox getsfs() {
    	System.out.println("sfs in const1: " + sfs);
    	return sfs;
    }

    public String checkconnection() {
    	
    	System.out.println("sfs in const1: " + sfs);
    	System.out.println("check flag");
    	System.out.println("connn: "+connectionFlag);
    	
    	return connectionFlag;
    }
    
    public void requestlogin(String name) {
    	
    	sfs.send(new LoginRequest(name, "", "BasicExamples"));
    }
    
    public void roomjoining() {
    	sfs.send(new JoinRoomRequest("The Lobby"));
    }
    
    
    public String  sendMSg(String message) {
    	sfs.send(new PublicMessageRequest(message));
		return message;
    }
 
    public void onConnection(BaseEvent evt)
    {
    	

    	boolean success = (boolean) evt.getArguments().get("success");
    	
        if (success)
        {
            System.out.println("Connection success");
            
        }
        else {
        	connectionFlag="flase";
            System.out.println("Connection Failed. Is the server running?");
        }
    }
    
    

    public void onConnectionLost(BaseEvent evt)
    {
        System.out.println("-- Connection lost --");
    }

    
    
    public void onLogin(BaseEvent evt)
    {
    	
      
    	System.out.println("Login successful!");
        System.out.println("Logged in as: " + sfs.getMySelf().getName());
       
    }

    
    
    public void onLoginError(BaseEvent evt)
    {
        String message = (String) evt.getArguments().get("errorMessage");
        System.out.println("Login failed. Cause: " + message);
    }
    
    
    
    public void onLogout(BaseEvent evt)
    {
    	 sfs.send(new LogoutRequest());
    	 System.out.println("Logout successful!");
    }
    
    
 

    public void onRoomJoin(BaseEvent evt)
    {
 //   	sfs.send(new JoinRoomRequest("The Lobby"));
//        Room room = (Room) evt.getArguments().get("room");
//        System.out.println("Joined Room: " + room.getName());
//    	String message;
  //      sfs.send(new PublicMessageRequest("y raab"));
    }
    
    public void onRoomJoinError(BaseEvent evt)
    {
    	 String message = (String) evt.getArguments().get("errorMessage");
         System.out.println("Room Joining failed. Cause: " + message);
    }

    public int onUserCountChange(BaseEvent evt)
    {
    	Room room = (Room) evt.getArguments().get("room");
    	int count=room.getUserCount();
    	System.out.println(count);
    	
		return count;
    }
    
    private void onUserEnterRoom(BaseEvent evt)
    
    
    {
    	User sender = (User)evt.getArguments().get("sender");
        String name=sender.getName();
    	System.out.println("user entered: "+ name);
    }
    
    
    
    
    
    private void onUserExitRoom(BaseEvent evt)
    {
    	//type here
    }
    
    public void onPublicMessage(BaseEvent evt)
    {
    	message=null;
    	 User sender = (User)evt.getArguments().get("sender");
    	
    	 if (sender == sfs.getMySelf()) {
    	 System.out.println("You said:" + evt.getArguments().get("message"));}
    	message="You said:" + evt.getArguments().get("message");
    	
         System.out.println(sender.getName() + " said:" + evt.getArguments().get("message"));	 
    	 message=" said:" + evt.getArguments().get("message");

    }
    

    
    private void onRoomVariablesUpdate(BaseEvent evt)
    {
    	//type here
    }
    
    private void onUserVariablesUpdate(BaseEvent evt)
    {
    	//type here
    }
    
    
    //setMaxUsers
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			

	    
		new Client();
 
		
	
         
            
	}

}
