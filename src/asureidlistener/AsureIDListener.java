
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

        LoopTrackP();
        LoopTrackSC();




    }
    
   static  public int getPermanentPermitId(){
       System.out.println("Updating permanent permit");
       Config config =new Config();
       int max=1;
        try{ 
              Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
              Connection conn= DriverManager.getConnection("jdbc:ucanaccess://"+config.getAccessHost()+config.getAccessDb(),config.getAccessPassword(),config.getAccessPassword());
             
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
                     ps1.setInt(6, 1);
                     
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
       Config config = new Config();
       int max2=0;
        try{ 
              Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
              Connection conn= DriverManager.getConnection("jdbc:ucanaccess://"+config.getAccessHost()+config.getAccessDb(),config.getAccessPassword(),config.getAccessPassword());
             
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
                     ps1.setInt(6, 1);
                     
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
      
  static public void createPermitUI(){
          JFrame frame = new JFrame();
          JOptionPane.showMessageDialog(frame, "Permanent permit updated.","SUCCESS",JOptionPane.INFORMATION_MESSAGE);
          
      }
  static public void createtafftUI(){
          JFrame frame = new JFrame();
          JOptionPane.showMessageDialog(frame, "Staff card updated","SUCCESS",JOptionPane.INFORMATION_MESSAGE);
      }
  public static void updateTrackerP(int id){
      Config config =new Config();
       
        try{ 
              Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
              Connection conn= DriverManager.getConnection("jdbc:ucanaccess://"+config.getAccessHost()+config.getAccessDb(),config.getAccessPassword(),config.getAccessPassword());
             
                   PreparedStatement ps3= conn.prepareStatement("SELECT * FROM permanent_permit_print_tracker WHERE printid='"+id+"'");
                   
                   ResultSet rs3=ps3.executeQuery();
                   
                   while(rs3.next()){
            
                     PreparedStatement ps1= conn.prepareStatement("UPDATE  permanent_permit_print_tracker SET status=? WHERE printid='"+id+"'");
                     ps1.setInt(1, 2);

                     ps1.executeUpdate();
                     
                   }

          }catch(Exception e){
              System.out.println("Error in connection"+e);
 
          }
        
      
  }
  public static void updateTrackerSC(int id){
      Config config =new Config();
       
        try{ 
              Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
              Connection conn= DriverManager.getConnection("jdbc:ucanaccess://"+config.getAccessHost()+config.getAccessDb(),config.getAccessPassword(),config.getAccessPassword());
             
                   PreparedStatement ps3= conn.prepareStatement("SELECT * FROM staff_id_print_tracker WHERE printid='"+id+"'");
                   
                   ResultSet rs3=ps3.executeQuery();
                   
                   while(rs3.next()){
            
                     PreparedStatement ps1= conn.prepareStatement("UPDATE  staff_id_print_tracker SET status=? WHERE printid='"+id+"'");
                     ps1.setInt(1, 2);

                     ps1.executeUpdate();
                     
                   }

          }catch(Exception e){
              System.out.println("Error in connection"+e);
 
          }
        
      
  }
  
    public static void LoopTrackP(){
      Config config =new Config();
       
        try{ 
              Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
              Connection conn= DriverManager.getConnection("jdbc:ucanaccess://"+config.getAccessHost()+config.getAccessDb(),config.getAccessPassword(),config.getAccessPassword());
             
                   PreparedStatement ps3= conn.prepareStatement("SELECT * FROM permanent_permit_print_tracker WHERE status<2");
                   
               
                   
                   ResultSet rs3=ps3.executeQuery();
                   
                   while(rs3.next()){
                       int id=rs3.getInt("printid");
                       RemoteAction action =new RemoteAction();
                       action.loopP(id);

                     
                   }
                   
                   

          }catch(Exception e){
              System.out.println("Error in connection"+e);
 
          }
        
      
  }
    
    public static void LoopTrackSC(){
      Config config =new Config();
       
        try{ 
              Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
              Connection conn= DriverManager.getConnection("jdbc:ucanaccess://"+config.getAccessHost()+config.getAccessDb(),config.getAccessPassword(),config.getAccessPassword());
             
                   PreparedStatement ps3= conn.prepareStatement("SELECT * FROM staff_id_print_tracker WHERE status<2");
                   
               
                   
                   ResultSet rs3=ps3.executeQuery();
                   
                   while(rs3.next()){
                       int id=rs3.getInt("printid");
                       RemoteAction action =new RemoteAction();
                       action.loopSC(id);

                     
                   }

          }catch(Exception e){
              System.out.println("Error in connection"+e);
 
          }
        
      
  }




}  


