import Action.HibernateUtil;
import Tables.Admin;
import Tables.Department;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

public class FetchReport extends HttpServlet {
    static Session session = null;
    HttpSession sess;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            sess = request.getSession();
            
            String name = (String)sess.getAttribute("user_hod");
            
            //get hod id for report
            session = HibernateUtil.getSessionFactory().openSession();
            Query fetch_hod_id = session.createQuery("FROM Admin where admin_uname ='"+name+"'");
            List<Admin> result_hod_id = fetch_hod_id.list();
            int hod_id = result_hod_id.get(0).getAdminId();
            
            String responseMsg = "";
            
            if(request.getParameterMap().containsKey("select")){
                //select * from admin where admin_type='faculty' and dept_id in (select dept_id from admin where admin_name);
                //fetch dept_id of hod
                String select = request.getParameter("select");
                
                if(select.equals("ans")){
                    session = HibernateUtil.getSessionFactory().openSession();
                    String fetch_dept_id = "FROM Admin where admin_uname = '"+name+"'";
                    Query query_dept_id = session.createQuery(fetch_dept_id);
                    List<Admin> result_dept_id = query_dept_id.list();
                    Integer deptId = result_dept_id.get(0).getDepartment().getDeptId();
                    String deptName = result_dept_id.get(0).getDepartment().getDeptName();
                    session.close();
                    
                    
                    
                    //fetch faculty with rank according to feedback score
                    session = HibernateUtil.getSessionFactory().openSession();
                    String fetch_rank = "SELECT avg(F.f_score) as score,count(F.admin_id) as noofquestion,F.admin_id,A.admin_name,A.dept_id from feedback F INNER JOIN admin A on F.admin_id = A.admin_id group by A.admin_id having A.dept_id ="+deptId+" order by score desc";
                    SQLQuery query = session.createSQLQuery(fetch_rank);

                    List<Object[]> result = query.list();

                    int rank = 1;
                    
                    responseMsg += "<table border='0' style='text-align:center;' cellpadding='30'><h4 style= margin-top:20px;><b>Department Of "+deptName+"</b></h4><button class='form-submit' onclick='downloadReport()'><i class='fa fa-download'></i>";
                    
                    responseMsg += "<a href='downloaded_report/"+hod_id+"_report-answer.txt' download>Download</a></button><hr>";
                    
                    responseMsg += "<th>Rank</th><th>Faculty Name</th><th>No Of Question</th><th>Score Out Of 10</th><th>See Comments</th>";
                    for(Object[] row : result){
                        responseMsg += "<tr><td>"+String.valueOf(rank)+"</td><td>"+row[3].toString()+"</td><td>"+row[1].toString()+"</td><td>"+row[0].toString()+"</td><td><button id='commentget' value='"+row[2].toString()+"' class='see'  onclick='getComments(this.value)'>Comment</button></td></tr>";
                        rank++;
                    }
                    session.close();

                    //fetch faculty who has not given answer to any question.
                    session = HibernateUtil.getSessionFactory().openSession();
                    String fetch_remaining = "SELECT * FROM admin WHERE admin_type = 'FACULTY' and dept_id="+deptId+" and NOT EXISTS ( SELECT admin_id FROM feedback WHERE admin_id = admin.admin_id)";
                    SQLQuery query_remaining = session.createSQLQuery(fetch_remaining);
                    
                    List<Object[]> result_remaining_1 = query_remaining.list();
                    for(Object[] ob: result_remaining_1){
                    responseMsg += "<tr><td> "+rank+" </td><td>"+ob[1].toString()+"</td><td> 0 </td><td> 0 </td><td>No comments</td></tr>";
                    rank++;
                }
                    session.close();
                    responseMsg += "</table>";
                    out.print(responseMsg);
                }
            
            else {
                //fetch dept_id and dept_name of hod
                session = HibernateUtil.getSessionFactory().openSession();
                String fetch_dept_id = "FROM Admin where admin_uname = '"+name+"'";
                Query query_dept_id = session.createQuery(fetch_dept_id);
                List<Admin> result_dept_id = query_dept_id.list();
                Integer deptId = result_dept_id.get(0).getDepartment().getDeptId();
                String deptName = result_dept_id.get(0).getDepartment().getDeptName();
                session.close();
               
                //fetch faculty with rank according to feedback score
                session = HibernateUtil.getSessionFactory().openSession();
                SQLQuery report = session.createSQLQuery("select avg(rating) as score,count(admin_id) as no_of_question,admin_id,admin.admin_name,admin.dept_id from "
                        + "faculty_feedback natural join admin group by admin_id having admin.dept_id ="+deptId+" order by score desc");
                List<Object[]> f_report = report.list();
                int rank = 1;
                session.close();
             
                String responseMsg1 = "<table border='0' style='text-align:center;' cellpadding='30'><h4 style= margin-top:20px;><b>Department Of "+deptName+"</b></h4><button class='form-submit' onclick='downloadReport()'>"
                        + "<i class='fa fa-download''></i> <a href='downloaded_report/"+hod_id+"_report-feedback.txt' download>Download</a></button><hr>";
                responseMsg1 += "<th>Rank</th><th>Faculty Name</th><th>No Of feedback</th><th>Score Out Of 10</th><th>See Comments</th>";
                for(Object[] obj : f_report){
                    responseMsg1 += "<tr><td>"+rank+"</td><td>"+obj[3].toString()+"</td><td>"+obj[1].toString()+"</td><td>"+obj[0].toString()+"</td><td><button id='commentget' value='"+obj[2].toString()+"' class='see' onclick='getComments(this.value)'>Comment</button></td></tr>";
                    rank++;
                }
                responseMsg1 += "</table>";
                out.print(responseMsg1);
            }
          }
            
            //Download report...
            if(request.getParameterMap().containsKey("download_select")){
                if(request.getParameter("download_select").equals("ans")){
                    //deptId and Name
                     session = HibernateUtil.getSessionFactory().openSession();
                    String fetch_dept_id = "FROM Admin where admin_uname = '"+name+"'";
                    Query query_dept_id = session.createQuery(fetch_dept_id);
                    List<Admin> result_dept_id = query_dept_id.list();
                    Integer deptId = result_dept_id.get(0).getDepartment().getDeptId();
                    String deptName = result_dept_id.get(0).getDepartment().getDeptName();
                    session.close();
                    
                    //fetch faculty with rank according to feedback score
                    session = HibernateUtil.getSessionFactory().openSession();
                    String fetch_rank = "SELECT avg(F.f_score) as score,count(F.admin_id) as noofquestion,F.admin_id,A.admin_name,A.dept_id from feedback F INNER JOIN admin A on F.admin_id = A.admin_id group by A.admin_id having A.dept_id ="+deptId+" order by score desc";
                    SQLQuery query = session.createSQLQuery(fetch_rank);

                    List<Object[]> result = query.list();
                    int rank = 1;
                    
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    String date = simpleDateFormat.format(new Date());
            
                    simpleDateFormat = new SimpleDateFormat("hh:mm:ss aa");
                    String time = simpleDateFormat.format(new Date());

                    responseMsg += "\t\t\t\tReport Of Faculty Performance\n\n\n\n";
                    responseMsg += "Date:"+date+"\t\t\t\t\t\tTime:"+time+"\n\n\n\n";

                    responseMsg += "Rank\t\tFaculty Name\t\tNo Of Question\t\tScore Out Of 10\n\n";
        
                    for(Object[] row : result){
                        responseMsg += String.valueOf(rank)+"\t\t"+row[3].toString()+"\t\t\t"+row[1].toString()+"\t\t\t\t"+row.toString();
                        responseMsg += "\n\n------------------------------------------------------------------------\n\n";
                    }
                    session.close();

                    //fetch faculty who has not given answer to any question.
                    session = HibernateUtil.getSessionFactory().openSession();
                    String fetch_remaining = "SELECT * FROM admin WHERE admin_type = 'FACULTY' and dept_id="+deptId+" and NOT EXISTS ( SELECT admin_id FROM feedback WHERE admin_id = admin.admin_id)";
                    SQLQuery query_remaining = session.createSQLQuery(fetch_remaining);
                    
                    List<Object[]> result_remaining_1 = query_remaining.list();
                    for(Object[] ob: result_remaining_1){
                        responseMsg += " - \t\t"+ob[1].toString()+"\t\t\t\t\t0\t\t\t\t0";
                        responseMsg += "\n\n------------------------------------------------------------------------\n\n";  
                        rank++;
                    }
                    responseMsg += "\n\n\n\n";
                    session.close();
                    
                    
              
                   try{
                        File file = new File(getServletContext().getRealPath("/")+"downloaded_report/"+hod_id+"_report-answer.txt");
                        if(file.createNewFile()){
                            System.out.print("File created");
                        }
                        else{
                            System.out.print("Alerady exists");
                        }
                        FileWriter writer = new FileWriter(file, true); 
                        writer.write(responseMsg); 
                        writer.close();
                    }
                    catch(Exception e){
                       System.out.print(e);
                    }
                }
                else{
                        //fetch dept_id and dept_name of hod
                        session = HibernateUtil.getSessionFactory().openSession();
                        String fetch_dept_id = "FROM Admin where admin_uname = '"+name+"'";
                        Query query_dept_id = session.createQuery(fetch_dept_id);
                        List<Admin> result_dept_id = query_dept_id.list();
                        Integer deptId = result_dept_id.get(0).getDepartment().getDeptId();
                        String deptName = result_dept_id.get(0).getDepartment().getDeptName();
                        session.close();

                        //fetch faculty with rank according to feedback score
                        session = HibernateUtil.getSessionFactory().openSession();
                        SQLQuery report = session.createSQLQuery("select avg(rating) as score,count(admin_id) as no_of_question,admin_id,admin.admin_name,admin.dept_id from "
                                + "faculty_feedback natural join admin group by admin_id having admin.dept_id ="+deptId+" order by score desc");
                        List<Object[]> f_report = report.list();
                        int rank = 1;
                        session.close();

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                        String date = simpleDateFormat.format(new Date());

                        simpleDateFormat = new SimpleDateFormat("hh:mm:ss aa");
                        String time = simpleDateFormat.format(new Date());

                        responseMsg += "\t\t\t\tReport Of Faculty Performance\n\n\n\n";
                        responseMsg += "Date:"+date+"\t\t\t\t\t\tTime:"+time+"\n\n\n\n";

                        responseMsg += "Rank\t\tFaculty Name\t\tNo Of Question\t\tScore Out Of 10\n\n";

                        for(Object[] row : f_report){
                            responseMsg += String.valueOf(rank)+"\t\t"+row[3].toString()+"\t\t\t"+row[1].toString()+"\t\t\t\t"+row[0].toString();
                            responseMsg += "\n\n------------------------------------------------------------------------\n\n";
                        }
                          
                        responseMsg += "\n\n\n\n";
                        //File Write...
                        try{
                        File file = new File(getServletContext().getRealPath("/")+"downloaded_report/"+hod_id+"_report-feedback.txt");
                        
                        if(file.createNewFile()){
                            System.out.print("File created");
                        }
                        else{
                            System.out.print("Alerady exists");
                        }
                        FileWriter writer = new FileWriter(file, true); 
                        writer.write(responseMsg); 
                        writer.close();
                        }
                        catch(Exception e){
                           System.out.print(e);
                        }
                       // out.print(getServletContext().getRealPath("/downloaded_report"));
                    }
                }
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
