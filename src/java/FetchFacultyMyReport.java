import Action.HibernateUtil;
import Tables.Department;
import Tables.FacultyFeedback;
import Tables.Feedback;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

public class FetchFacultyMyReport extends HttpServlet {
    static Session session = null;
    HttpSession sess;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            sess = request.getSession();
            session = HibernateUtil.getSessionFactory().openSession();
            String dept_id = request.getParameter("dept_id");
            String responseMsg = "";
            
            int rank = 1;
            double avg1 = 0;
            double avg2 = 0;
            double count = 0;
            
            //echo "select dept_name from department where dept_id = ".$_REQUEST['dept_id'];
            String fetch_dept_name = "FROM Department WHERE dept_id = "+dept_id;
            Query query_dept_name = session.createQuery(fetch_dept_name);
            List<Department> result_dept_name = query_dept_name.list();
            
            //feedback data fetching.
            Query query_feedback = session.createQuery("FROM Feedback WHERE admin_id in (SELECT adminId from Admin WHERE admin_uname='"+sess.getAttribute("user_faculty")+"')");
            List<Feedback> result_feedback = query_feedback.list();
            
            if(result_feedback.isEmpty()){
                Query query_faculty_feedback =session.createQuery("From FacultyFeedback WHERE admin_id in (SELECT adminId FROM Admin where admin_uname='"+sess.getAttribute("user_faculty")+"')");
                List<FacultyFeedback> result_faculty_feedback = query_faculty_feedback.list();
                
                if(result_faculty_feedback.isEmpty())
                    responseMsg += "<h4>No data Available for you right now!</h4>";
                else{
                    responseMsg += "<table class='tbl' border='0' style='text-align:center;' cellpadding='30'><h4 style= margin-top:20px;><b>Department Of "+result_dept_name.get(0).getDeptName()+"</b></h4><hr>";
                    responseMsg += "<th>Sr.Number</th><th>Score</th><th>Comment</th>";
                    
                    //data from faculty_feedback table.
                    Query query_feedback_table = session.createQuery("FROM FacultyFeedback where admin_id in (SELECT adminId FROM Admin where admin_uname='"+sess.getAttribute("user_faculty")+"')");
                    List<FacultyFeedback> result_feedback_table = query_feedback_table.list();
                    
                    for(int i=0; i<result_feedback_table.size() ; i++){
                        
                        responseMsg += "<tr><td>"+String.valueOf(rank)+"</td><td>"+result_feedback_table.get(i).getRating()+"</td><td>"+result_feedback_table.get(i).getComment()+"</td></tr>";
                        rank++;
                    
                    }
                responseMsg += "</table>";
                Query query_calaculate_avg = session.createQuery("SELECT avg(rating) as avg FROM FacultyFeedback WHERE admin_id in (SELECT adminId from Admin WHERE admin_uname='"+sess.getAttribute("user_faculty")+"')");
                List<FacultyFeedback> result_calculate_avg = query_calaculate_avg.list();
                responseMsg += "Your overall score is "+result_calculate_avg.get(0);
                
                
                }
            }
            else{
                responseMsg += "<table class='tbl' border='0' style='text-align:center;' cellpadding='30'><h4 style= margin-top:20px;><b>Department Of "+result_dept_name.get(0).getDeptName()+"</b></h4><hr>";
                responseMsg += "<th>Sr.Number</th><th>Score</th><th>Comment</th>";
                
                for(int i=0 ; i<result_feedback.size() ; i++){
                    if(rank == 1)
                        responseMsg += "<tr style='border-bottom: 1px solid grey;border-top: 1px solid grey;'><td>"+String.valueOf(rank)+"</td><td>"+result_feedback.get(0).getFScore()+"</td><td>"+result_feedback.get(0).getFComment()+"</td></tr>";
                    else
                        responseMsg += "<tr style='border-bottom: 1px solid grey;'><td>"+String.valueOf(rank)+"</td><td>"+result_feedback.get(0).getFScore()+"</td><td>"+result_feedback.get(0).getFComment()+"</td></tr>";
                    rank++;
                }
                
                SQLQuery query_feedback_sum = session.createSQLQuery("select sum(f_score),count(f_score) from feedback where admin_id in (select admin_id from admin where admin_uname='"+sess.getAttribute("user_faculty")+"')");
                List<Object[]> result_sum = query_feedback_sum.list();
                
                if(!result_sum.isEmpty()){
                
                    for(Object[] row : result_sum){
                        avg1 = Double.parseDouble(String.valueOf(row[0]));
                        count += Double.parseDouble(String.valueOf(row[1]));
                    }
                }
                
                //data from faculty_feedback table.
                Query query_feedback_table = session.createQuery("FROM FacultyFeedback where admin_id in (SELECT adminId FROM Admin where admin_uname='"+sess.getAttribute("user_faculty")+"')");
                List<FacultyFeedback> result_feedback_table = query_feedback_table.list();
                    
                for(int i=0; i<result_feedback_table.size() ; i++){
                    responseMsg += "<tr><td>"+String.valueOf(rank)+"</td><td>"+result_feedback_table.get(i).getRating()+"</td><td>"+result_feedback_table.get(i).getComment()+"</td></tr>";
                    rank++;
                }
                responseMsg += "</table>";
                
                SQLQuery query_facullty_feedback_sum = session.createSQLQuery("SELECT sum(rating) as avg,count(rating) as count FROM faculty_feedback WHERE admin_id in (SELECT admin_id FROM admin WHERE admin_uname='"+sess.getAttribute("user_faculty")+"')");
                List<Object[]> result_faculty_sum = query_facullty_feedback_sum.list();
                
                if(!result_faculty_sum.isEmpty()){
                   
                    for(Object[] row : result_faculty_sum){
                        avg2 = Double.parseDouble(String.valueOf(row[0]));
                        count += Double.parseDouble(String.valueOf(row[1]));
                    }
                }
                double avg = (avg1+avg2)/count;
                responseMsg += "Your overall score is "+avg;
                
            }
            out.print(responseMsg);
        }
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

}
