package chat;

import java.io.IOException;
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
 * Servlet implementation class Checkcon
 */
@WebServlet("/Checkcon")
public class Checkcon extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Checkcon() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	     
		SmartFox sfs;
		HttpSession session=request.getSession(false);
		sfs=(SmartFox)session.getAttribute("smartfoxserver");
	    
	       String flag=new Client(sfs).checkconnection();
	       PrintWriter writer = response.getWriter();
	       response.setContentType("application/json");
	   	   JSONObject obj = new JSONObject();
	   	   try {
			obj.put("flag",flag);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    response.setStatus(200);
		    //System.out.println(obj.get("message"));
		    writer.append(obj.toString());
		    writer.close();
		System.out.println("connection check  --------");
		//System.out.println("check event" + sfs);

	
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
