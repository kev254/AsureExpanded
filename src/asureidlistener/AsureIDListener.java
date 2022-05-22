
package asureidlistener;
import java.sql.*;
import javax.swing.*;
import javax.swing.JOptionPane;




/**
 *
 * @author Kevin Okomba
 */
public class AsureIDListener {
    

    public static void main(String[] args) {
        
       
        RemoteAction m= new RemoteAction();
        m.updatePermit();
        m.updateStaffId();
        createUI();
       
        

        
  
    }
    
   static  public int getPermanentPermitId(){
       System.out.println("Updating permanent permit");
       int max=1;
        try{ 
              Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
              Connection conn= DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\USER\\Desktop\\AsureID.mdb","","loo<oage");
             
              PreparedStatement preparedStatement=conn.prepareStatement("SELECT * FROM PP_2122_1_14_006_1");
             
              ResultSet resultSet=preparedStatement.executeQuery();
              while(resultSet.next()){
                   int count=resultSet.getInt("PrintCount");
                   String by=resultSet.getString("PrintBy");
                   String date1=resultSet.getString("PrintDateTime");
                   int id=resultSet.getInt("id");
                   
                   Statement st2=conn.createStatement();
                   PreparedStatement ps3= conn.prepareStatement("SELECT * FROM permanent_permit_print_tracker WHERE printid=?");
                   ps3.setInt(1,id);
                   ResultSet rs3=ps3.executeQuery();
                   
                   if(!rs3.next()){
                       if(count>0){
                      
                       
                     String date=java.time.LocalDate.now().toString();
                     PreparedStatement ps1= conn.prepareStatement("insert into permanent_permit_print_tracker values(?,?,?,?,?,?)");
                     ps1.setInt(1, 1);
                     ps1.setString(2, date);
                     ps1.setString(3, date1);
                     ps1.setInt(4, id);
                     ps1.setString(5, by);
                     ps1.setInt(6, 2);
                     
                     ps1.executeUpdate();
                     max=id;



                   }
                       
                   }
                
                   
                   
                   
              }
 
          }catch(Exception e){
              System.out.println("Error in connection"+e);
 
          }
        
        return max;
    }
   static  public int getStaffId(){
       System.out.println("Updating Staff ID");
       int max2=0;
        try{ 
              Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
              Connection conn= DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\USER\\Desktop\\AsureID.mdb","","loo<oage");
             
              PreparedStatement preparedStatement=conn.prepareStatement("SELECT * FROM SD_2122_2_14_006_1 ");
             
              ResultSet resultSet=preparedStatement.executeQuery();
              while(resultSet.next()){
                   int count=resultSet.getInt("PrintCount");
                   String by=resultSet.getString("PrintBy");
                   String date1=resultSet.getString("PrintDateTime");
                   int id=resultSet.getInt("id");
                   
                   Statement st2=conn.createStatement();
                   PreparedStatement ps3= conn.prepareStatement("SELECT * FROM staff_id_print_tracker WHERE printid=?");
                   ps3.setInt(1,id);
                   ResultSet rs3=ps3.executeQuery();
                   
                   if(!rs3.next()){
                       if(count>0){
                      
                       
                     String date=java.time.LocalDate.now().toString();
                     PreparedStatement ps1= conn.prepareStatement("insert into staff_id_print_tracker values(?,?,?,?,?,?)");
                     ps1.setInt(1, 1);
                     ps1.setString(2, date);
                     ps1.setString(3, date1);
                     ps1.setInt(4, id);
                     ps1.setString(5, by);
                     ps1.setInt(6, 2);
                     
                     ps1.executeUpdate();
                     max2=id;



                   }
                       
                   }
  
              }
 
          }catch(Exception e){
              System.out.println("Error in connection"+e);
 
          }
        
        return max2;
    }
      
      static public void createUI(){
          JFrame frame = new JFrame();
          JOptionPane.showMessageDialog(frame, "Remote record updated succesfully","SUCCESS",JOptionPane.QUESTION_MESSAGE);
      }

   
 

    
}  


