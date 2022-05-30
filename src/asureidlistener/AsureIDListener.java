
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
        m.updateApronLounges();
        LoopTrackApronLounge();
//        m.updateAprons();
//        LoopTrackApron();
//        m.updateAirsides();
//        LoopTrackAirside();
//        m.updateSecurity();
//        LoopTrackSecurity();
//        m.updateFreights();
//        LoopTrackFreight();
//        m.updateLounges();
//        LoopTrackLounges();
//        m.updatePermit();
//        m.updateStaffId();
//
//        LoopTrackP();
//        LoopTrackSC();

    }


   //Landside Permit Access manipulation 
   static  public int getPermanentPermitId(){
       System.out.println("Updating permanent permit");
       Config config =new Config();
       int max=1;
        try{ 
              Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
              Connection conn= DriverManager.getConnection("jdbc:ucanaccess://"+config.getAccessHost()+config.getAccessDb(),config.getAccessPassword(),config.getAccessPassword());
             
              PreparedStatement preparedStatement=conn.prepareStatement("SELECT * FROM pp_2122_1_0_2_005_1");
             
              ResultSet resultSet=preparedStatement.executeQuery();
              while(resultSet.next()){
                   int count=resultSet.getInt("PrintCount");
                   String by=resultSet.getString("PrintBy");
                   String date1=resultSet.getString("PrintDateTime");
                   int id=resultSet.getInt("id");
                 
                   
                   Statement st2=conn.createStatement();
                   PreparedStatement ps3= conn.prepareStatement("SELECT * FROM pl_print_tracker WHERE printid=?");
                   ps3.setInt(1,id);
                   ResultSet rs3=ps3.executeQuery();
                   
                   if(!rs3.next()){
                       if(count>0){
                      
                       
                     String date=java.time.LocalDate.now().toString();
                     PreparedStatement ps1= conn.prepareStatement("insert into pl_print_tracker values(?,?,?,?,?,?)");
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
   static public void createPermitUI(){
          JFrame frame = new JFrame();
          JOptionPane.showMessageDialog(frame, "Permanent permit updated.","SUCCESS",JOptionPane.INFORMATION_MESSAGE);
          
      }
   public static void updateTrackerP(int id){
      Config config =new Config();
       
        try{ 
              Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
              Connection conn= DriverManager.getConnection("jdbc:ucanaccess://"+config.getAccessHost()+config.getAccessDb(),config.getAccessPassword(),config.getAccessPassword());
             
                   PreparedStatement ps3= conn.prepareStatement("SELECT * FROM pl_print_tracker WHERE printid='"+id+"'");
                   
                   ResultSet rs3=ps3.executeQuery();
                   
                   while(rs3.next()){
            
                     PreparedStatement ps1= conn.prepareStatement("UPDATE  pl_print_tracker SET status=? WHERE printid='"+id+"'");
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
             
                   PreparedStatement ps3= conn.prepareStatement("SELECT * FROM pl_print_tracker WHERE status<2");
                   
               
                   
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
   
   //Staff card Access manipulation 
   static  public int getStaffId(){
       System.out.println("Updating Staff ID");
       Config config = new Config();
       int max2=0;
        try{ 
              Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
              Connection conn= DriverManager.getConnection("jdbc:ucanaccess://"+config.getAccessHost()+config.getAccessDb(),config.getAccessPassword(),config.getAccessPassword());
              
              PreparedStatement preparedStatement=conn.prepareStatement("SELECT * FROM sd_2122_0_1_2_005_1 ");
             
              ResultSet resultSet=preparedStatement.executeQuery();
              while(resultSet.next()){
                   int count=resultSet.getInt("PrintCount");
                   String by=resultSet.getString("PrintBy");
                   String date1=resultSet.getString("PrintDateTime");
                   int id=resultSet.getInt("id");
                   
                   
                   Statement st2=conn.createStatement();
                   PreparedStatement ps3= conn.prepareStatement("SELECT * FROM sd_print_tracker WHERE printid=?");
                   ps3.setInt(1,id);
                   ResultSet rs3=ps3.executeQuery();
                   
                   if(!rs3.next()){
                       if(count>0){
                      
                       
                     String date=java.time.LocalDate.now().toString();
                     PreparedStatement ps1= conn.prepareStatement("insert into sd_print_tracker values(?,?,?,?,?,?)");
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
   static public void createtafftUI(){
          JFrame frame = new JFrame();
          JOptionPane.showMessageDialog(frame, "Staff record updated","SUCCESS",JOptionPane.INFORMATION_MESSAGE);
      }
   public static void updateTrackerSC(int id){
      Config config =new Config();
       
        try{ 
              Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
              Connection conn= DriverManager.getConnection("jdbc:ucanaccess://"+config.getAccessHost()+config.getAccessDb(),config.getAccessPassword(),config.getAccessPassword());
             
                   PreparedStatement ps3= conn.prepareStatement("SELECT * FROM sd_print_tracker WHERE printid='"+id+"'");
                   
                   ResultSet rs3=ps3.executeQuery();
                   
                   while(rs3.next()){
            
                     PreparedStatement ps1= conn.prepareStatement("UPDATE  sd_print_tracker SET status=? WHERE printid='"+id+"'");
                     ps1.setInt(1, 2);

                     ps1.executeUpdate();
                     
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
             
                   PreparedStatement ps3= conn.prepareStatement("SELECT * FROM sd_print_tracker WHERE status<2");
                   
               
                   
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
   
   //PA Lounges Access manipulation 
   static  public int getLoungesId(){
       System.out.println("Updating Staff ID");
       Config config = new Config();
       int max2=0;
        try{ 
              Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
              Connection conn= DriverManager.getConnection("jdbc:ucanaccess://"+config.getAccessHost()+config.getAccessDb(),config.getAccessPassword(),config.getAccessPassword());
              
              PreparedStatement preparedStatement=conn.prepareStatement("SELECT * FROM pp_2122_2_21_2_005_1 ");
             
              ResultSet resultSet=preparedStatement.executeQuery();
              while(resultSet.next()){
                   int count=resultSet.getInt("PrintCount");
                   String by=resultSet.getString("PrintBy");
                   String date1=resultSet.getString("PrintDateTime");
                   int id=resultSet.getInt("id");
                   
                   
                   Statement st2=conn.createStatement();
                   PreparedStatement ps3= conn.prepareStatement("SELECT * FROM pa_lounges_print_tracker WHERE printid=?");
                   ps3.setInt(1,id);
                   ResultSet rs3=ps3.executeQuery();
                   
                   if(!rs3.next()){
                       if(count>0){
                      
                       
                     String date=java.time.LocalDate.now().toString();
                     PreparedStatement ps1= conn.prepareStatement("insert into pa_lounges_print_tracker values(?,?,?,?,?,?)");
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
   static public void createLoungesUI(){
          JFrame frame = new JFrame();
          JOptionPane.showMessageDialog(frame, "Lounges record updated","SUCCESS",JOptionPane.INFORMATION_MESSAGE);
      }
   public static void updateTrackerLounges(int id){
      Config config =new Config();
       
        try{ 
              Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
              Connection conn= DriverManager.getConnection("jdbc:ucanaccess://"+config.getAccessHost()+config.getAccessDb(),config.getAccessPassword(),config.getAccessPassword());
             
                   PreparedStatement ps3= conn.prepareStatement("SELECT * FROM pa_lounges_print_tracker WHERE printid='"+id+"'");
                   
                   ResultSet rs3=ps3.executeQuery();
                   
                   while(rs3.next()){
            
                     PreparedStatement ps1= conn.prepareStatement("UPDATE  pa_lounges_print_tracker SET status=? WHERE printid='"+id+"'");
                     ps1.setInt(1, 2);

                     ps1.executeUpdate();
                     
                   }

          }catch(Exception e){
              System.out.println("Error in connection"+e);
 
          }
        
      
  }
   public static void LoopTrackLounges(){
      Config config =new Config();
       
        try{ 
              Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
              Connection conn= DriverManager.getConnection("jdbc:ucanaccess://"+config.getAccessHost()+config.getAccessDb(),config.getAccessPassword(),config.getAccessPassword());
             
                   PreparedStatement ps3= conn.prepareStatement("SELECT * FROM pa_lounges_print_tracker WHERE status<2");
                   
               
                   
                   ResultSet rs3=ps3.executeQuery();
                   
                   while(rs3.next()){
                       int id=rs3.getInt("printid");
                       RemoteAction action =new RemoteAction();
                       action.loopLounges(id);

                     
                   }

          }catch(Exception e){
              System.out.println("Error in connection"+e);
 
          }
        
      
  }
   
   //Freightapron  MS Access manipulation 
   static  public int getFreightId(){
       System.out.println("Updating Staff ID");
       Config config = new Config();
       int max2=0;
        try{ 
              Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
              Connection conn= DriverManager.getConnection("jdbc:ucanaccess://"+config.getAccessHost()+config.getAccessDb(),config.getAccessPassword(),config.getAccessPassword());
              
              PreparedStatement preparedStatement=conn.prepareStatement("SELECT * FROM pp_2122_2_17_2_005_1 ");
             
              ResultSet resultSet=preparedStatement.executeQuery();
              while(resultSet.next()){
                   int count=resultSet.getInt("PrintCount");
                   String by=resultSet.getString("PrintBy");
                   String date1=resultSet.getString("PrintDateTime");
                   int id=resultSet.getInt("id");
                   
                   
                   Statement st2=conn.createStatement();
                   PreparedStatement ps3= conn.prepareStatement("SELECT * FROM pa_freightapron_print_tracker WHERE printid=?");
                   ps3.setInt(1,id);
                   ResultSet rs3=ps3.executeQuery();
                   
                   if(!rs3.next()){
                       if(count>0){
                      
                       
                     String date=java.time.LocalDate.now().toString();
                     PreparedStatement ps1= conn.prepareStatement("insert into pa_freightapron_print_tracker values(?,?,?,?,?,?)");
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
   static public void createFreightUI(){
          JFrame frame = new JFrame();
          JOptionPane.showMessageDialog(frame, "Freight Apron record updated","SUCCESS",JOptionPane.INFORMATION_MESSAGE);
      }
   public static void updateTrackerFreight(int id){
      Config config =new Config();
       
        try{ 
              Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
              Connection conn= DriverManager.getConnection("jdbc:ucanaccess://"+config.getAccessHost()+config.getAccessDb(),config.getAccessPassword(),config.getAccessPassword());
             
                   PreparedStatement ps3= conn.prepareStatement("SELECT * FROM pa_freightapron_print_tracker WHERE printid='"+id+"'");
                   
                   ResultSet rs3=ps3.executeQuery();
                   
                   while(rs3.next()){
            
                     PreparedStatement ps1= conn.prepareStatement("UPDATE  pa_freightapron_print_tracker SET status=? WHERE printid='"+id+"'");
                     ps1.setInt(1, 2);

                     ps1.executeUpdate();
                     
                   }

          }catch(Exception e){
              System.out.println("Error in connection"+e);
 
          }
        
      
  }
   public static void LoopTrackFreight(){
      Config config =new Config();
       
        try{ 
              Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
              Connection conn= DriverManager.getConnection("jdbc:ucanaccess://"+config.getAccessHost()+config.getAccessDb(),config.getAccessPassword(),config.getAccessPassword());
             
                   PreparedStatement ps3= conn.prepareStatement("SELECT * FROM pa_freightapron_print_tracker WHERE status<2");
                   
               
                   
                   ResultSet rs3=ps3.executeQuery();
                   
                   while(rs3.next()){
                       int id=rs3.getInt("printid");
                       RemoteAction action =new RemoteAction();
                       action.loopFreights(id);

                     
                   }

          }catch(Exception e){
              System.out.println("Error in connection"+e);
 
          }
        
      
  }
   
   //Security table   MS Access manipulation 
   static  public int getSecurityId(){
       System.out.println("Updating Security");
       Config config = new Config();
       int max2=0;
        try{ 
              Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
              Connection conn= DriverManager.getConnection("jdbc:ucanaccess://"+config.getAccessHost()+config.getAccessDb(),config.getAccessPassword(),config.getAccessPassword());
              
              PreparedStatement preparedStatement=conn.prepareStatement("SELECT * FROM sd_2122_0_2_2_005_1 ");
             
              ResultSet resultSet=preparedStatement.executeQuery();
              while(resultSet.next()){
                   int count=resultSet.getInt("PrintCount");
                   String by=resultSet.getString("PrintBy");
                   String date1=resultSet.getString("PrintDateTime");
                   int id=resultSet.getInt("id");
                   
                   
                   Statement st2=conn.createStatement();
                   PreparedStatement ps3= conn.prepareStatement("SELECT * FROM sd_security_print_tracker WHERE printid=?");
                   ps3.setInt(1,id);
                   ResultSet rs3=ps3.executeQuery();
                   
                   if(!rs3.next()){
                       if(count>0){
                      
                       
                     String date=java.time.LocalDate.now().toString();
                     PreparedStatement ps1= conn.prepareStatement("insert into sd_security_print_tracker values(?,?,?,?,?,?)");
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
   static public void createSecurityUI(){
          JFrame frame = new JFrame();
          JOptionPane.showMessageDialog(frame, "Security record updated","SUCCESS",JOptionPane.INFORMATION_MESSAGE);
      }
   public static void updateTrackerSecurity(int id){
      Config config =new Config();
       
        try{ 
              Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
              Connection conn= DriverManager.getConnection("jdbc:ucanaccess://"+config.getAccessHost()+config.getAccessDb(),config.getAccessPassword(),config.getAccessPassword());
             
                   PreparedStatement ps3= conn.prepareStatement("SELECT * FROM sd_security_print_tracker WHERE printid='"+id+"'");
                   
                   ResultSet rs3=ps3.executeQuery();
                   
                   while(rs3.next()){
            
                     PreparedStatement ps1= conn.prepareStatement("UPDATE  sd_security_print_tracker SET status=? WHERE printid='"+id+"'");
                     ps1.setInt(1, 2);

                     ps1.executeUpdate();
                     
                   }

          }catch(Exception e){
              System.out.println("Error in connection"+e);
 
          }
        
      
  }
   public static void LoopTrackSecurity(){
      Config config =new Config();
       
        try{ 
              Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
              Connection conn= DriverManager.getConnection("jdbc:ucanaccess://"+config.getAccessHost()+config.getAccessDb(),config.getAccessPassword(),config.getAccessPassword());
             
                   PreparedStatement ps3= conn.prepareStatement("SELECT * FROM sd_security_print_tracker WHERE status<2");
                   
               
                   
                   ResultSet rs3=ps3.executeQuery();
                   
                   while(rs3.next()){
                       int id=rs3.getInt("printid");
                       RemoteAction action =new RemoteAction();
                       action.loopSecurity(id);

                     
                   }

          }catch(Exception e){
              System.out.println("Error in connection"+e);
 
          }
        
      
  }
   
   //Airside Permit   MS Access manipulation 
   static  public int getAirsideId(){
       System.out.println("Updating Airside permit");
       Config config = new Config();
       int max2=0;
        try{ 
              Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
              Connection conn= DriverManager.getConnection("jdbc:ucanaccess://"+config.getAccessHost()+config.getAccessDb(),config.getAccessPassword(),config.getAccessPassword());
              
              PreparedStatement preparedStatement=conn.prepareStatement("SELECT * FROM pp_2122_2_5_2_005_1");
             
              ResultSet resultSet=preparedStatement.executeQuery();
              while(resultSet.next()){
                   int count=resultSet.getInt("PrintCount");
                   String by=resultSet.getString("PrintBy");
                   String date1=resultSet.getString("PrintDateTime");
                   int id=resultSet.getInt("id");
                   
                   
                   Statement st2=conn.createStatement();
                   PreparedStatement ps3= conn.prepareStatement("SELECT * FROM pa_allareas_print_tracker WHERE printid=?");
                   ps3.setInt(1,id);
                   ResultSet rs3=ps3.executeQuery();
                   
                   if(!rs3.next()){
                       if(count>0){
                      
                       
                     String date=java.time.LocalDate.now().toString();
                     PreparedStatement ps1= conn.prepareStatement("insert into pa_allareas_print_tracker values(?,?,?,?,?,?)");
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
   static public void createAirsideUI(){
          JFrame frame = new JFrame();
          JOptionPane.showMessageDialog(frame, "Airside record updated","SUCCESS",JOptionPane.INFORMATION_MESSAGE);
      }
   public static void updateTrackerAirside(int id){
      Config config =new Config();
       
        try{ 
              Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
              Connection conn= DriverManager.getConnection("jdbc:ucanaccess://"+config.getAccessHost()+config.getAccessDb(),config.getAccessPassword(),config.getAccessPassword());
             
                   PreparedStatement ps3= conn.prepareStatement("SELECT * FROM pa_allareas_print_tracker WHERE printid='"+id+"'");
                   
                   ResultSet rs3=ps3.executeQuery();
                   
                   while(rs3.next()){
            
                     PreparedStatement ps1= conn.prepareStatement("UPDATE  pa_allareas_print_tracker SET status=? WHERE printid='"+id+"'");
                     ps1.setInt(1, 2);

                     ps1.executeUpdate();
                     
                   }

          }catch(Exception e){
              System.out.println("Error in connection"+e);
 
          }
        
      
  }
   public static void LoopTrackAirside(){
      Config config =new Config();
       
        try{ 
              Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
              Connection conn= DriverManager.getConnection("jdbc:ucanaccess://"+config.getAccessHost()+config.getAccessDb(),config.getAccessPassword(),config.getAccessPassword());
             
                   PreparedStatement ps3= conn.prepareStatement("SELECT * FROM pa_allareas_print_tracker WHERE status<2");
                   
               
                   
                   ResultSet rs3=ps3.executeQuery();
                   
                   while(rs3.next()){
                       int id=rs3.getInt("printid");
                       RemoteAction action =new RemoteAction();
                       action.loopAirsides(id);

                     
                   }

          }catch(Exception e){
              System.out.println("Error in connection"+e);
 
          }
        
      
  }
   
   //Apron Permit   MS Access manipulation 
   static  public int getApronId(){
       System.out.println("Updating Apron passes");
       Config config = new Config();
       int max2=0;
        try{ 
              Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
              Connection conn= DriverManager.getConnection("jdbc:ucanaccess://"+config.getAccessHost()+config.getAccessDb(),config.getAccessPassword(),config.getAccessPassword());
              
              PreparedStatement preparedStatement=conn.prepareStatement("SELECT * FROM pp_2122_2_15_2_005_1");
             
              ResultSet resultSet=preparedStatement.executeQuery();
              while(resultSet.next()){
                   int count=resultSet.getInt("PrintCount");
                   String by=resultSet.getString("PrintBy");
                   String date1=resultSet.getString("PrintDateTime");
                   int id=resultSet.getInt("id");
                   
                   
                   Statement st2=conn.createStatement();
                   PreparedStatement ps3= conn.prepareStatement("SELECT * FROM pa_apron_print_tracker WHERE printid=?");
                   ps3.setInt(1,id);
                   ResultSet rs3=ps3.executeQuery();
                   
                   if(!rs3.next()){
                       if(count>0){
                      
                       
                     String date=java.time.LocalDate.now().toString();
                     PreparedStatement ps1= conn.prepareStatement("insert into pa_apron_print_tracker values(?,?,?,?,?,?)");
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
   static public void createApronUI(){
          JFrame frame = new JFrame();
          JOptionPane.showMessageDialog(frame, "Apron record updated","SUCCESS",JOptionPane.INFORMATION_MESSAGE);
      }
   public static void updateTrackerApron(int id){
      Config config =new Config();
       
        try{ 
              Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
              Connection conn= DriverManager.getConnection("jdbc:ucanaccess://"+config.getAccessHost()+config.getAccessDb(),config.getAccessPassword(),config.getAccessPassword());
             
                   PreparedStatement ps3= conn.prepareStatement("SELECT * FROM pa_apron_print_tracker WHERE printid='"+id+"'");
                   
                   ResultSet rs3=ps3.executeQuery();
                   
                   while(rs3.next()){
            
                     PreparedStatement ps1= conn.prepareStatement("UPDATE  pa_apron_print_tracker SET status=? WHERE printid='"+id+"'");
                     ps1.setInt(1, 2);

                     ps1.executeUpdate();
                     
                   }

          }catch(Exception e){
              System.out.println("Error in connection"+e);
 
          }
        
      
  }
   public static void LoopTrackApron(){
      Config config =new Config();
       
        try{ 
              Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
              Connection conn= DriverManager.getConnection("jdbc:ucanaccess://"+config.getAccessHost()+config.getAccessDb(),config.getAccessPassword(),config.getAccessPassword());
             
                   PreparedStatement ps3= conn.prepareStatement("SELECT * FROM pa_apron_print_tracker WHERE status<2");
                   
               
                   
                   ResultSet rs3=ps3.executeQuery();
                   
                   while(rs3.next()){
                       int id=rs3.getInt("printid");
                       RemoteAction action =new RemoteAction();
                       action.loopAprons(id);

                     
                   }

          }catch(Exception e){
              System.out.println("Error in connection"+e);
 
          }
        
      
  }
   
   //Apron lounge Permit   MS Access manipulation 
   static  public int getApronLoungesId(){
       System.out.println("Updating Apron Lounge passes");
       Config config = new Config();
       int max2=0;
        try{ 
              Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
              Connection conn= DriverManager.getConnection("jdbc:ucanaccess://"+config.getAccessHost()+config.getAccessDb(),config.getAccessPassword(),config.getAccessPassword());
              
              PreparedStatement preparedStatement=conn.prepareStatement("SELECT * FROM pp_2122_2_14_2_005_1");
             
              ResultSet resultSet=preparedStatement.executeQuery();
              while(resultSet.next()){
                   int count=resultSet.getInt("PrintCount");
                   String by=resultSet.getString("PrintBy");
                   String date1=resultSet.getString("PrintDateTime");
                   int id=resultSet.getInt("id");
                   
                   
                   Statement st2=conn.createStatement();
                   PreparedStatement ps3= conn.prepareStatement("SELECT * FROM pa_appronlounges_print_tracker WHERE printid=?");
                   ps3.setInt(1,id);
                   ResultSet rs3=ps3.executeQuery();
                   
                   if(!rs3.next()){
                       if(count>0){
                      
                       
                     String date=java.time.LocalDate.now().toString();
                     PreparedStatement ps1= conn.prepareStatement("insert into pa_appronlounges_print_tracker values(?,?,?,?,?,?)");
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
   static public void createApronLoungeUI(){
          JFrame frame = new JFrame();
          JOptionPane.showMessageDialog(frame, "Apron lounge record updated","SUCCESS",JOptionPane.INFORMATION_MESSAGE);
      }
   public static void updateTrackerApronLounge(int id){
      Config config =new Config();
       
        try{ 
              Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
              Connection conn= DriverManager.getConnection("jdbc:ucanaccess://"+config.getAccessHost()+config.getAccessDb(),config.getAccessPassword(),config.getAccessPassword());
             
                   PreparedStatement ps3= conn.prepareStatement("SELECT * FROM pa_appronlounges_print_tracker WHERE printid='"+id+"'");
                   
                   ResultSet rs3=ps3.executeQuery();
                   
                   while(rs3.next()){
            
                     PreparedStatement ps1= conn.prepareStatement("UPDATE  pa_appronlounges_print_tracker SET status=? WHERE printid='"+id+"'");
                     ps1.setInt(1, 2);

                     ps1.executeUpdate();
                     
                   }

          }catch(Exception e){
              System.out.println("Error in connection"+e);
 
          }
        
      
  }
   public static void LoopTrackApronLounge(){
      Config config =new Config();
       
        try{ 
              Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
              Connection conn= DriverManager.getConnection("jdbc:ucanaccess://"+config.getAccessHost()+config.getAccessDb(),config.getAccessPassword(),config.getAccessPassword());
             
                   PreparedStatement ps3= conn.prepareStatement("SELECT * FROM pa_appronlounges_print_tracker WHERE status<2");
                   
               
                   
                   ResultSet rs3=ps3.executeQuery();
                   
                   while(rs3.next()){
                       int id=rs3.getInt("printid");
                       RemoteAction action =new RemoteAction();
                       action.loopApronLounges(id);

                     
                   }

          }catch(Exception e){
              System.out.println("Error in connection"+e);
 
          }
        
      
  }
   
}  


