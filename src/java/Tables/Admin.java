package Tables;
// Generated Apr 9, 2020 1:59:34 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Admin generated by hbm2java
 */
public class Admin  implements java.io.Serializable {


     private Integer adminId;
     private Department department;
     private Subject subject;
     private String adminName;
     private String adminUname;
     private String adminPwd;
     private String adminType;
     private String adminEmail;
     private Set facultyFeedbacks = new HashSet(0);
     private Set answers = new HashSet(0);
     private Set feedbacks = new HashSet(0);

    public Admin() {
        
    }
    
    public Admin(int adminId) {
        this.adminId = adminId;
    }

    public Admin(Department department, Subject subject, String adminName, String adminUname, String adminPwd, String adminType, String adminEmail) {
        this.department = department;
        this.subject = subject;
        this.adminName = adminName;
        this.adminUname = adminUname;
        this.adminPwd = adminPwd;
        this.adminType = adminType;
        this.adminEmail = adminEmail;
    }
    public Admin(Department department, Subject subject, String adminName, String adminUname, String adminPwd, String adminType, String adminEmail, Set facultyFeedbacks, Set answers, Set feedbacks) {
       this.department = department;
       this.subject = subject;
       this.adminName = adminName;
       this.adminUname = adminUname;
       this.adminPwd = adminPwd;
       this.adminType = adminType;
       this.adminEmail = adminEmail;
       this.facultyFeedbacks = facultyFeedbacks;
       this.answers = answers;
       this.feedbacks = feedbacks;
    }
   
    public Integer getAdminId() {
        return this.adminId;
    }
    
    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }
    public Department getDepartment() {
        return this.department;
    }
    
    public void setDepartment(Department department) {
        this.department = department;
    }
    
    public Subject getSubject() {
        return this.subject;
    }
    
    public void setSubject(Subject subject) {
        this.subject = subject;
    }
    public String getAdminName() {
        return this.adminName;
    }
    
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }
    public String getAdminUname() {
        return this.adminUname;
    }
    
    public void setAdminUname(String adminUname) {
        this.adminUname = adminUname;
    }
    public String getAdminPwd() {
        return this.adminPwd;
    }
    
    public void setAdminPwd(String adminPwd) {
        this.adminPwd = adminPwd;
    }
    public String getAdminType() {
        return this.adminType;
    }
    
    public void setAdminType(String adminType) {
        this.adminType = adminType;
    }
    public String getAdminEmail() {
        return this.adminEmail;
    }
    
    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }
    public Set getFacultyFeedbacks() {
        return this.facultyFeedbacks;
    }
    
    public void setFacultyFeedbacks(Set facultyFeedbacks) {
        this.facultyFeedbacks = facultyFeedbacks;
    }
    public Set getAnswers() {
        return this.answers;
    }
    
    public void setAnswers(Set answers) {
        this.answers = answers;
    }
    public Set getFeedbacks() {
        return this.feedbacks;
    }
    
    public void setFeedbacks(Set feedbacks) {
        this.feedbacks = feedbacks;
    }
}


