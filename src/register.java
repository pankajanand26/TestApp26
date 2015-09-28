

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class register
 */
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String uname=request.getParameter("uname");
		String pass=request.getParameter("pass");
		String cpass=request.getParameter("cpass");
		String email=request.getParameter("email");

		int error_flag=0;
		
		if(uname==null || pass==null || cpass==null || email==null || uname.isEmpty() || pass.isEmpty() || cpass.isEmpty() || email.isEmpty()){
			error_flag=1;
		}else if(!pass.equals(cpass)){
			error_flag=2;
		}
		
		if(error_flag==1){
			request.setAttribute("error", "Don't leave the fields blank.");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
		}else if(error_flag==2){
			request.setAttribute("error", "Don't leave the fields blank.");
			request.getRequestDispatcher("/register.jsp").forward(request, response);	
		}else{			
			request.setAttribute("uname", "Hello, "+uname+"!!");
			request.setAttribute("login_info", "Your account is ready.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		};
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
