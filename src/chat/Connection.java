package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import sfs2x.client.SmartFox;
import sfs2x.client.core.SFSEvent;



/**
 * Servlet implementation class Connection
 */
@WebServlet("/Connection")
public class Connection extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Connection() {
	
	    
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//System.out.println(request.getParameter("event"));
		System.out.println("event here");
		
		SmartFox sfs=new SmartFox();
		Client t=new Client();
		   PrintWriter writer = response.getWriter();
		sfs= t.getsfs();
		 HttpSession session=request.getSession();  
	        session.setAttribute("smartfoxserver",sfs);  
		JSONObject obj = new JSONObject();
		  request.setCharacterEncoding("utf8");
		    response.setContentType("application/json");
		try {
			obj.put("sfsOBJ",sfs);
		    response.setStatus(200);
		    //System.out.println(obj.get("message"));
		    writer.append(obj.toString());
		    writer.close();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
