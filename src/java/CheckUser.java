/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Action.HibernateUtil;
import Tables.Admin;
import Tables.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Harshal
 */
@WebServlet(urlPatterns = {"/checkUser"})
public class CheckUser extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     */
    static Session session_db = null;
    static String login_username = "";
    static HttpSession session;
    static PrintWriter out;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        out = response.getWriter();
        session = request.getSession(); 
        
        
        String uname = request.getParameter("user_name");
        String pass = request.getParameter("user_pwd");
            
        //call static method of login class
        this.validateLoginData(uname, pass);
        
       
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public static void validateLoginData(String uname,String pass){
         
        session_db = HibernateUtil.getSessionFactory().openSession();
        
        //create query for validate student login cedentials
        String check_user = "FROM User WHERE user_name = '"+uname+"' and user_pwd = '"+pass+"'";
        Query query = session_db.createQuery(check_user);
        
        
        
        List<User> result = query.list();

     
        if(!result.isEmpty()){
            //set session attributes for student
            session.setAttribute("user_student",uname);
            session.setAttribute("user_id",result.get(0).getUserId());
            session.setAttribute("user_type","STUDENT");
                
            //page redirection to the home_page.jsp
            out.print("success");
        }else{
            validateAdmin(uname, pass);
           // login_username = validateAdmin(uname,pass);
        }
        
        
    }
    
    public  static void validateAdmin(String uname,String pass){
        
        //create query for validate admint login cedentials
        String check_admin = "FROM Admin where admin_uname = '"+uname+"' and admin_pwd = '"+pass+"'";
        Query query_admin = session_db.createQuery(check_admin);
        
        
        List<Admin> result = query_admin.list();
       
        //set flag to check whether login successfull or failed
       
        if(!result.isEmpty()){
            
            String type = result.get(0).getAdminType();
            
            if(type.equals("FACULTY")){
                //set session attributes for faculty
                session.setAttribute("user_faculty",uname);
                session.setAttribute("user_type","FACULTY");
                
                //page redirection to the home_page.jsp
                out.print("success");
            }else{
                //set session attributes for hod
                session.setAttribute("user_hod",uname);
                session.setAttribute("user_type","HOD");
                
                //page redirection to the home_page.jsp
                out.print("success");
            }
            
           
        }else{
         
            //Check for Blocked User
            String check_blockuser = "FROM User WHERE user_pwd = '"+pass+"'";
            Query query_block = session_db.createQuery(check_blockuser);

            
            
            List<User> result_block = query_block.list();
            
            if(!result_block.isEmpty()){
                
                try{
                    String name = result_block.get(0).getUserName();
                    //out.print("H");
                    if(name.equals("")){
                        //out.print("H");
                        out.print("You are blocked according to your misbehaviour");
                    }
                }catch(NullPointerException e){
                    out.print("You are blocked according to your misbehaviour");
                    
                }
            }else
                out.print("Invalid Username and Password");
        }   
     
    }
}
