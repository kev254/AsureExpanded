
package asureidlistener;

import static asureidlistener.AsureIDListener.createApronLoungeUI;
import static asureidlistener.AsureIDListener.createApronTaxwayUI;
import static asureidlistener.AsureIDListener.createApronUI;
import static asureidlistener.AsureIDListener.createBaggageHallUI;
import static asureidlistener.AsureIDListener.createCheckinCounterUI;
import static asureidlistener.AsureIDListener.createConCourseUI;
import static asureidlistener.AsureIDListener.createFreightUI;
import static asureidlistener.AsureIDListener.createLoungesUI;
import static asureidlistener.AsureIDListener.createPermitUI;
import static asureidlistener.AsureIDListener.createSecurityUI;
import static asureidlistener.AsureIDListener.createtafftUI;
import static asureidlistener.AsureIDListener.getAirsideId;
import static asureidlistener.AsureIDListener.getApronId;
import static asureidlistener.AsureIDListener.getApronLoungesId;
import static asureidlistener.AsureIDListener.getApronTaxwayId;
import static asureidlistener.AsureIDListener.getBaggageHallId;
import static asureidlistener.AsureIDListener.getCheckinCounterId;
import static asureidlistener.AsureIDListener.getConCourseId;
import static asureidlistener.AsureIDListener.getFreightId;
import static asureidlistener.AsureIDListener.getLoungesId;
import static asureidlistener.AsureIDListener.getPermanentPermitId;
import static asureidlistener.AsureIDListener.getSecurityId;
import static asureidlistener.AsureIDListener.getStaffId;
import static asureidlistener.AsureIDListener.updateTrackerAirside;
import static asureidlistener.AsureIDListener.updateTrackerApron;
import static asureidlistener.AsureIDListener.updateTrackerBaggageHall;
import static asureidlistener.AsureIDListener.updateTrackerCheckinCounter;
import static asureidlistener.AsureIDListener.updateTrackerConCourse;
import static asureidlistener.AsureIDListener.updateTrackerFreight;
import static asureidlistener.AsureIDListener.updateTrackerLounges;
import static asureidlistener.AsureIDListener.updateTrackerP;
import static asureidlistener.AsureIDListener.updateTrackerSC;
import static asureidlistener.AsureIDListener.updateTrackerSecurity;
import static asureidlistener.AsureIDListener.updateTrackerTaxway;
import com.mysql.jdbc.*;

import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class RemoteAction {
    //updating remote Mysql for the landslide permit 
     public void updatePermit(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Config config = new Config();
            Connection con2= (Connection) DriverManager.getConnection("jdbc:mysql://"+config.getMysqlhost()+config.getMysqldb(), config.getMysqlUser(), config.getMysqlPassword());
            //System.out.println("Connected");
            int ID=getPermanentPermitId();
            Statement st=con2.createStatement();
            ResultSet rs= st.executeQuery("SELECT * FROM pl_print_mgr WHERE id='"+ID+"'");
            while(rs.next()){

              int id=rs.getInt(1);
              String permit_no=rs.getString(2);
              String permit_type=rs.getString(3);
              String access_area=rs.getString(4);
              String applicant_name=rs.getString(5);
              String doc_no=rs.getString(6);
              String operator=rs.getString(7);
              String designation=rs.getString(8);
              String application_date=rs.getString(9);
              String expiry_date=rs.getString(10);
              String applicant_photo_file=rs.getString(11);
              String applicant_photo=rs.getString(12);
              String applicant_signature_file=rs.getString(13);
              String applicant_signature=rs.getString(14);
              String ceo_signature_file=rs.getString(15).toString().trim();
              String ceo_signature=rs.getString(16);
              String batch_id=rs.getString(17);
              String airport_airstrip_name=rs.getString(18);
              String print_template_name=rs.getString(19);
              String date=rs.getString(20);
              
              
              PreparedStatement ps2= con2.prepareStatement("INSERT INTO pl_printed_cards_mgr VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
              ps2.setInt(1,id);
              ps2.setString(2, permit_no);
              ps2.setString(3, permit_type);
              ps2.setString(4, access_area);
              ps2.setString(5,applicant_name);
              ps2.setString(6, doc_no);
              ps2.setString(7,operator);
              ps2.setString(8, designation);
              ps2.setString(9,application_date);
              ps2.setString(10,expiry_date);
              ps2.setString(11,applicant_photo_file);
              ps2.setString(12,applicant_photo);
              ps2.setString(13,applicant_signature_file);
              ps2.setString(14,applicant_signature);
              ps2.setString(15,ceo_signature_file);
              ps2.setString(16,ceo_signature);
              ps2.setString(17,batch_id);
              ps2.setString(18,airport_airstrip_name);
              ps2.setString(19,print_template_name);
              ps2.setString(20,date);
              
              int check=ps2.executeUpdate();
              if(check>0){
                   PreparedStatement ps3= con2.prepareStatement("DELETE FROM pl_print_mgr WHERE id=?");
                   ps3.setInt(1,ID);
                   ps3.executeUpdate();
                   updateTrackerP(ID);
                   createPermitUI();
                   
                  System.out.println("Permit Record migrated  succesfully");
                  
                
            }
              else{
                  System.out.println("Not data inserted");
              }
              
            }
            
        } catch (Exception ex) {
            System.out.println("Not Connected"+ex);
        }
   }
     public void loopP(int ID){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Config config = new Config();
            Connection con2= (Connection) DriverManager.getConnection("jdbc:mysql://"+config.getMysqlhost()+config.getMysqldb(), config.getMysqlUser(), config.getMysqlPassword());
            //System.out.println("Connected");
            
            Statement st=con2.createStatement();
            ResultSet rs= st.executeQuery("SELECT * FROM pl_print_mgr WHERE id='"+ID+"'");
            while(rs.next()){

              int id=rs.getInt(1);
              String permit_no=rs.getString(2);
              String permit_type=rs.getString(3);
              String access_area=rs.getString(4);
              String applicant_name=rs.getString(5);
              String doc_no=rs.getString(6);
              String operator=rs.getString(7);
              String designation=rs.getString(8);
              String application_date=rs.getString(9);
              String expiry_date=rs.getString(10);
              String applicant_photo_file=rs.getString(11);
              String applicant_photo=rs.getString(12);
              String applicant_signature_file=rs.getString(13);
              String applicant_signature=rs.getString(14);
              String ceo_signature_file=rs.getString(15).toString().trim();
              String ceo_signature=rs.getString(16);
              String batch_id=rs.getString(17);
              String airport_airstrip_name=rs.getString(18);
              String print_template_name=rs.getString(19);
              String date=rs.getString(20);
              
              
              PreparedStatement ps2= con2.prepareStatement("INSERT INTO pl_printed_cards_mgr VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
              ps2.setInt(1,id);
              ps2.setString(2, permit_no);
              ps2.setString(3, permit_type);
              ps2.setString(4, access_area);
              ps2.setString(5,applicant_name);
              ps2.setString(6, doc_no);
              ps2.setString(7,operator);
              ps2.setString(8, designation);
              ps2.setString(9,application_date);
              ps2.setString(10,expiry_date);
              ps2.setString(11,applicant_photo_file);
              ps2.setString(12,applicant_photo);
              ps2.setString(13,applicant_signature_file);
              ps2.setString(14,applicant_signature);
              ps2.setString(15,ceo_signature_file);
              ps2.setString(16,ceo_signature);
              ps2.setString(17,batch_id);
              ps2.setString(18,airport_airstrip_name);
              ps2.setString(19,print_template_name);
              ps2.setString(20,date);
              
              int check=ps2.executeUpdate();
              if(check>0){
                   PreparedStatement ps3= con2.prepareStatement("DELETE FROM pl_print_mgr WHERE id=?");
                   ps3.setInt(1,ID);
                   ps3.executeUpdate();
                   updateTrackerP(ID);
                   
                  System.out.println("Permit Record migrated  succesfully");
                
            }
              else{
                  System.out.println("Not data inserted");
              }
              
            }
            
        } catch (Exception ex) {
            System.out.println("Not Connected"+ex);
        }
   }
     
    //updating remote Mysql for the staff card
     public void updateStaffId(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Config config = new Config();
            Connection con2= (Connection) DriverManager.getConnection("jdbc:mysql://"+config.getMysqlhost()+config.getMysqldb(), config.getMysqlUser(), config.getMysqlPassword());
            int ID2=getStaffId();
//            System.out.println(ID2);
            Statement st=con2.createStatement();
            ResultSet rs= st.executeQuery("SELECT * FROM sd_print_mgr WHERE id='"+ID2+"'");
            while(rs.next()){

              int id=rs.getInt(1);
              String permit_no=rs.getString(2);
              String permit_type=rs.getString(3);
              String access_area=rs.getString(4);
              String applicant_name=rs.getString(5);
              String doc_no=rs.getString(6);
              String operator=rs.getString(7);
              String designation=rs.getString(8);
              String department=rs.getString(9);
              String application_date=rs.getString(10);
              String expiry_date=rs.getString(11);
              String applicant_photo_file=rs.getString(12);
              String applicant_photo=rs.getString(13);
              String applicant_signature_file=rs.getString(14);
              String applicant_signature=rs.getString(15);
              String ceo_signature_file=rs.getString(16).toString().trim();
              String ceo_signature=rs.getString(17);
              String batch_id=rs.getString(18);
              String airport_airstrip_name=rs.getString(19);
              String print_template_name=rs.getString(20);
              String date=rs.getString(21);
              
              
              
              
              PreparedStatement ps2= con2.prepareStatement("INSERT INTO sd_printed_cards_mgr VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
              ps2.setInt(1,id);
              ps2.setString(2, permit_no);
              ps2.setString(3, permit_type);
              ps2.setString(4, access_area);
              ps2.setString(5,applicant_name);
              ps2.setString(6, doc_no);
              ps2.setString(7,operator);
              ps2.setString(8, designation);
              ps2.setString(9,department);
              ps2.setString(10,application_date);
              ps2.setString(11,expiry_date);
              ps2.setString(12,applicant_photo_file);
              ps2.setString(13,applicant_photo);
              ps2.setString(14,applicant_signature_file);
              ps2.setString(15,applicant_signature);
              ps2.setString(16,ceo_signature_file);
              ps2.setString(17,ceo_signature);
              ps2.setString(18,batch_id);
              ps2.setString(19,airport_airstrip_name);
              ps2.setString(20,print_template_name);
              ps2.setString(21,date);
              
              int check=ps2.executeUpdate();
              if(check>0){
                   PreparedStatement ps3= con2.prepareStatement("DELETE FROM sd_print_mgr WHERE id=?");
                   ps3.setInt(1,ID2);
                   ps3.executeUpdate();
                   updateTrackerSC(ID2);
                   
                  System.out.println("Staff Record migrated  succesfully");
                  createtafftUI();
                
            }
              else{
                  System.out.println("Not data inserted");
              }
              
            }
            
        } catch (Exception ex) {
            System.out.println("Not Connected"+ex);
        }
   }
     public void loopSC(int ID){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Config config = new Config();
            Connection con2= (Connection) DriverManager.getConnection("jdbc:mysql://"+config.getMysqlhost()+config.getMysqldb(), config.getMysqlUser(), config.getMysqlPassword());
            //System.out.println("Connected");
            
            Statement st=con2.createStatement();
            ResultSet rs= st.executeQuery("SELECT * FROM sd_print_mgr WHERE id='"+ID+"'");
            while(rs.next()){

              int id=rs.getInt(1);
              String permit_no=rs.getString(2);
              String permit_type=rs.getString(3);
              String access_area=rs.getString(4);
              String applicant_name=rs.getString(5);
              String doc_no=rs.getString(6);
              String operator=rs.getString(7);
              String designation=rs.getString(8);
              String department=rs.getString(9);
              String application_date=rs.getString(10);
              String expiry_date=rs.getString(11);
              String applicant_photo_file=rs.getString(12);
              String applicant_photo=rs.getString(13);
              String applicant_signature_file=rs.getString(14);
              String applicant_signature=rs.getString(15);
              String ceo_signature_file=rs.getString(16).toString().trim();
              String ceo_signature=rs.getString(17);
              String batch_id=rs.getString(18);
              String airport_airstrip_name=rs.getString(19);
              String print_template_name=rs.getString(20);
              String date=rs.getString(21);
              
              
              PreparedStatement ps2= con2.prepareStatement("INSERT INTO sd_printed_cards_mgr VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
              ps2.setInt(1,id);
              ps2.setString(2, permit_no);
              ps2.setString(3, permit_type);
              ps2.setString(4, access_area);
              ps2.setString(5,applicant_name);
              ps2.setString(6, doc_no);
              ps2.setString(7,operator);
              ps2.setString(8, designation);
              ps2.setString(9,department);
              ps2.setString(10,application_date);
              ps2.setString(11,expiry_date);
              ps2.setString(12,applicant_photo_file);
              ps2.setString(13,applicant_photo);
              ps2.setString(14,applicant_signature_file);
              ps2.setString(15,applicant_signature);
              ps2.setString(16,ceo_signature_file);
              ps2.setString(17,ceo_signature);
              ps2.setString(18,batch_id);
              ps2.setString(19,airport_airstrip_name);
              ps2.setString(20,print_template_name);
              ps2.setString(21,date);
              
              
              int check=ps2.executeUpdate();
              if(check>0){
                   PreparedStatement ps3= con2.prepareStatement("DELETE FROM sd_print_mgr WHERE id=?");
                   ps3.setInt(1,ID);
                   ps3.executeUpdate();
                   updateTrackerSC(ID);
                   
                  System.out.println("Staff Record migrated  succesfully");
                 
                
            }
              else{
                  System.out.println("Not data inserted");
              }
              
            }
            
        } catch (Exception ex) {
            System.out.println("Not Connected"+ex);
        }
   }
     
    //updating remote Mysql for the lounges 
     public void updateLounges(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Config config = new Config();
            Connection con2= (Connection) DriverManager.getConnection("jdbc:mysql://"+config.getMysqlhost()+config.getMysqldb(), config.getMysqlUser(), config.getMysqlPassword());
            //System.out.println("Connected");
            int ID=getLoungesId();
            Statement st=con2.createStatement();
            ResultSet rs= st.executeQuery("SELECT * FROM pa_lounges_print_mgr WHERE id='"+ID+"'");
            while(rs.next()){

              int id=rs.getInt(1);
              String permit_no=rs.getString(2);
              String permit_type=rs.getString(3);
              String access_area=rs.getString(4);
              String applicant_name=rs.getString(5);
              String doc_no=rs.getString(6);
              String operator=rs.getString(7);
              String designation=rs.getString(8);
              String application_date=rs.getString(9);
              String expiry_date=rs.getString(10);
              String applicant_photo_file=rs.getString(11);
              String applicant_photo=rs.getString(12);
              String applicant_signature_file=rs.getString(13);
              String applicant_signature=rs.getString(14);
              String ceo_signature_file=rs.getString(15).toString().trim();
              String ceo_signature=rs.getString(16);
              String batch_id=rs.getString(17);
              String airport_airstrip_name=rs.getString(18);
              String print_template_name=rs.getString(19);
              String date=rs.getString(20);
              
              
              PreparedStatement ps2= con2.prepareStatement("INSERT INTO pa_lounges_printed_cards_mgr VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
              ps2.setInt(1,id);
              ps2.setString(2, permit_no);
              ps2.setString(3, permit_type);
              ps2.setString(4, access_area);
              ps2.setString(5,applicant_name);
              ps2.setString(6, doc_no);
              ps2.setString(7,operator);
              ps2.setString(8, designation);
              ps2.setString(9,application_date);
              ps2.setString(10,expiry_date);
              ps2.setString(11,applicant_photo_file);
              ps2.setString(12,applicant_photo);
              ps2.setString(13,applicant_signature_file);
              ps2.setString(14,applicant_signature);
              ps2.setString(15,ceo_signature_file);
              ps2.setString(16,ceo_signature);
              ps2.setString(17,batch_id);
              ps2.setString(18,airport_airstrip_name);
              ps2.setString(19,print_template_name);
              ps2.setString(20,date);
              
              int check=ps2.executeUpdate();
              if(check>0){
                   PreparedStatement ps3= con2.prepareStatement("DELETE FROM pa_lounges_print_mgr WHERE id=?");
                   ps3.setInt(1,ID);
                   ps3.executeUpdate();
                   updateTrackerLounges(ID);
                   createLoungesUI();
                   
                  System.out.println("Lounges  Record migrated  succesfully");
                  
                
            }
              else{
                  System.out.println("Not data inserted");
              }
              
            }
            
        } catch (Exception ex) {
            System.out.println("Not Connected"+ex);
        }
   }
     public void loopLounges(int ID){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Config config = new Config();
            Connection con2= (Connection) DriverManager.getConnection("jdbc:mysql://"+config.getMysqlhost()+config.getMysqldb(), config.getMysqlUser(), config.getMysqlPassword());
            //System.out.println("Connected");
            
            Statement st=con2.createStatement();
            ResultSet rs= st.executeQuery("SELECT * FROM pa_lounges_print_mgr WHERE id='"+ID+"'");
            while(rs.next()){

              int id=rs.getInt(1);
              String permit_no=rs.getString(2);
              String permit_type=rs.getString(3);
              String access_area=rs.getString(4);
              String applicant_name=rs.getString(5);
              String doc_no=rs.getString(6);
              String operator=rs.getString(7);
              String designation=rs.getString(8);
              String application_date=rs.getString(9);
              String expiry_date=rs.getString(10);
              String applicant_photo_file=rs.getString(11);
              String applicant_photo=rs.getString(12);
              String applicant_signature_file=rs.getString(13);
              String applicant_signature=rs.getString(14);
              String ceo_signature_file=rs.getString(15).toString().trim();
              String ceo_signature=rs.getString(16);
              String batch_id=rs.getString(17);
              String airport_airstrip_name=rs.getString(18);
              String print_template_name=rs.getString(19);
              String date=rs.getString(20);
              
              
              PreparedStatement ps2= con2.prepareStatement("INSERT INTO pa_lounges_printed_cards_mgr VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
              ps2.setInt(1,id);
              ps2.setString(2, permit_no);
              ps2.setString(3, permit_type);
              ps2.setString(4, access_area);
              ps2.setString(5,applicant_name);
              ps2.setString(6, doc_no);
              ps2.setString(7,operator);
              ps2.setString(8, designation);
              ps2.setString(9,application_date);
              ps2.setString(10,expiry_date);
              ps2.setString(11,applicant_photo_file);
              ps2.setString(12,applicant_photo);
              ps2.setString(13,applicant_signature_file);
              ps2.setString(14,applicant_signature);
              ps2.setString(15,ceo_signature_file);
              ps2.setString(16,ceo_signature);
              ps2.setString(17,batch_id);
              ps2.setString(18,airport_airstrip_name);
              ps2.setString(19,print_template_name);
              ps2.setString(20,date);
              
              int check=ps2.executeUpdate();
              if(check>0){
                   PreparedStatement ps3= con2.prepareStatement("DELETE FROM pa_lounges_print_mgr WHERE id=?");
                   ps3.setInt(1,ID);
                   ps3.executeUpdate();
                   updateTrackerLounges(ID);
                   
                  System.out.println("Lounges  Record migrated  succesfully");
                
            }
              else{
                  System.out.println("Not data inserted");
              }
              
            }
            
        } catch (Exception ex) {
            System.out.println("Not Connected"+ex);
        }
   }
       
     //updating remote Mysql for the Freight Apron 
     public void updateFreights(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Config config = new Config();
            Connection con2= (Connection) DriverManager.getConnection("jdbc:mysql://"+config.getMysqlhost()+config.getMysqldb(), config.getMysqlUser(), config.getMysqlPassword());
            //System.out.println("Connected");
            int ID=getFreightId();
            Statement st=con2.createStatement();
            ResultSet rs= st.executeQuery("SELECT * FROM pa_freightapron_print_mgr WHERE id='"+ID+"'");
            while(rs.next()){

              int id=rs.getInt(1);
              String permit_no=rs.getString(2);
              String permit_type=rs.getString(3);
              String access_area=rs.getString(4);
              String applicant_name=rs.getString(5);
              String doc_no=rs.getString(6);
              String operator=rs.getString(7);
              String designation=rs.getString(8);
              String application_date=rs.getString(9);
              String expiry_date=rs.getString(10);
              String applicant_photo_file=rs.getString(11);
              String applicant_photo=rs.getString(12);
              String applicant_signature_file=rs.getString(13);
              String applicant_signature=rs.getString(14);
              String ceo_signature_file=rs.getString(15).toString().trim();
              String ceo_signature=rs.getString(16);
              String batch_id=rs.getString(17);
              String airport_airstrip_name=rs.getString(18);
              String print_template_name=rs.getString(19);
              String date=rs.getString(20);
              
              
              PreparedStatement ps2= con2.prepareStatement("INSERT INTO pa_freightapron_printed_cards_mgr VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
              ps2.setInt(1,id);
              ps2.setString(2, permit_no);
              ps2.setString(3, permit_type);
              ps2.setString(4, access_area);
              ps2.setString(5,applicant_name);
              ps2.setString(6, doc_no);
              ps2.setString(7,operator);
              ps2.setString(8, designation);
              ps2.setString(9,application_date);
              ps2.setString(10,expiry_date);
              ps2.setString(11,applicant_photo_file);
              ps2.setString(12,applicant_photo);
              ps2.setString(13,applicant_signature_file);
              ps2.setString(14,applicant_signature);
              ps2.setString(15,ceo_signature_file);
              ps2.setString(16,ceo_signature);
              ps2.setString(17,batch_id);
              ps2.setString(18,airport_airstrip_name);
              ps2.setString(19,print_template_name);
              ps2.setString(20,date);
              
              int check=ps2.executeUpdate();
              if(check>0){
                   PreparedStatement ps3= con2.prepareStatement("DELETE FROM pa_freightapron_print_mgr WHERE id=?");
                   ps3.setInt(1,ID);
                   ps3.executeUpdate();
                   updateTrackerFreight(ID);
                   createFreightUI();
                   
                  System.out.println("Freight Apron  Record migrated  succesfully");
                  
                
            }
              else{
                  System.out.println("Not data inserted");
              }
              
            }
            
        } catch (Exception ex) {
            System.out.println("Not Connected"+ex);
        }
   }
     public void loopFreights(int ID){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Config config = new Config();
            Connection con2= (Connection) DriverManager.getConnection("jdbc:mysql://"+config.getMysqlhost()+config.getMysqldb(), config.getMysqlUser(), config.getMysqlPassword());
            //System.out.println("Connected");
            
            Statement st=con2.createStatement();
            ResultSet rs= st.executeQuery("SELECT * FROM pa_freightapron_print_mgr WHERE id='"+ID+"'");
            while(rs.next()){

              int id=rs.getInt(1);
              String permit_no=rs.getString(2);
              String permit_type=rs.getString(3);
              String access_area=rs.getString(4);
              String applicant_name=rs.getString(5);
              String doc_no=rs.getString(6);
              String operator=rs.getString(7);
              String designation=rs.getString(8);
              String application_date=rs.getString(9);
              String expiry_date=rs.getString(10);
              String applicant_photo_file=rs.getString(11);
              String applicant_photo=rs.getString(12);
              String applicant_signature_file=rs.getString(13);
              String applicant_signature=rs.getString(14);
              String ceo_signature_file=rs.getString(15).toString().trim();
              String ceo_signature=rs.getString(16);
              String batch_id=rs.getString(17);
              String airport_airstrip_name=rs.getString(18);
              String print_template_name=rs.getString(19);
              String date=rs.getString(20);
              
              
              PreparedStatement ps2= con2.prepareStatement("INSERT INTO pa_freightapron_printed_cards_mgr VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
              ps2.setInt(1,id);
              ps2.setString(2, permit_no);
              ps2.setString(3, permit_type);
              ps2.setString(4, access_area);
              ps2.setString(5,applicant_name);
              ps2.setString(6, doc_no);
              ps2.setString(7,operator);
              ps2.setString(8, designation);
              ps2.setString(9,application_date);
              ps2.setString(10,expiry_date);
              ps2.setString(11,applicant_photo_file);
              ps2.setString(12,applicant_photo);
              ps2.setString(13,applicant_signature_file);
              ps2.setString(14,applicant_signature);
              ps2.setString(15,ceo_signature_file);
              ps2.setString(16,ceo_signature);
              ps2.setString(17,batch_id);
              ps2.setString(18,airport_airstrip_name);
              ps2.setString(19,print_template_name);
              ps2.setString(20,date);
              
              int check=ps2.executeUpdate();
              if(check>0){
                   PreparedStatement ps3= con2.prepareStatement("DELETE FROM pa_freightapron_print_mgr WHERE id=?");
                   ps3.setInt(1,ID);
                   ps3.executeUpdate();
                   updateTrackerFreight(ID);
                   
                  System.out.println("Freight Apron  Record migrated  succesfully");
                
            }
              else{
                  System.out.println("Not data inserted");
              }
              
            }
            
        } catch (Exception ex) {
            System.out.println("Not Connected"+ex);
        }
   }
     
     //updating remote Mysql for the Freight Apron 
     public void updateSecurity(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Config config = new Config();
            Connection con2= (Connection) DriverManager.getConnection("jdbc:mysql://"+config.getMysqlhost()+config.getMysqldb(), config.getMysqlUser(), config.getMysqlPassword());
            int ID2=getSecurityId();
//            System.out.println(ID2);
            Statement st=con2.createStatement();
            ResultSet rs= st.executeQuery("SELECT * FROM sd_security_print_mgr WHERE id='"+ID2+"'");
            while(rs.next()){

              int id=rs.getInt(1);
              String permit_no=rs.getString(2);
              String permit_type=rs.getString(3);
              String access_area=rs.getString(4);
              String applicant_name=rs.getString(5);
              String doc_no=rs.getString(6);
              String operator=rs.getString(7);
              String designation=rs.getString(8);
              String department=rs.getString(9);
              String application_date=rs.getString(10);
              String expiry_date=rs.getString(11);
              String applicant_photo_file=rs.getString(12);
              String applicant_photo=rs.getString(13);
              String applicant_signature_file=rs.getString(14);
              String applicant_signature=rs.getString(15);
              String ceo_signature_file=rs.getString(16).toString().trim();
              String ceo_signature=rs.getString(17);
              String batch_id=rs.getString(18);
              String airport_airstrip_name=rs.getString(19);
              String print_template_name=rs.getString(20);
              String date=rs.getString(21);
              
              
              
              
              PreparedStatement ps2= con2.prepareStatement("INSERT INTO sd_security_printed_cards_mgr VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
              ps2.setInt(1,id);
              ps2.setString(2, permit_no);
              ps2.setString(3, permit_type);
              ps2.setString(4, access_area);
              ps2.setString(5,applicant_name);
              ps2.setString(6, doc_no);
              ps2.setString(7,operator);
              ps2.setString(8, designation);
              ps2.setString(9,department);
              ps2.setString(10,application_date);
              ps2.setString(11,expiry_date);
              ps2.setString(12,applicant_photo_file);
              ps2.setString(13,applicant_photo);
              ps2.setString(14,applicant_signature_file);
              ps2.setString(15,applicant_signature);
              ps2.setString(16,ceo_signature_file);
              ps2.setString(17,ceo_signature);
              ps2.setString(18,batch_id);
              ps2.setString(19,airport_airstrip_name);
              ps2.setString(20,print_template_name);
              ps2.setString(21,date);
              
              int check=ps2.executeUpdate();
              if(check>0){
                   PreparedStatement ps3= con2.prepareStatement("DELETE FROM sd_security_print_mgr WHERE id=?");
                   ps3.setInt(1,ID2);
                   ps3.executeUpdate();
                   updateTrackerSecurity(ID2);
                   
                  System.out.println("Security Record migrated  succesfully");
                  createSecurityUI();
                
            }
              else{
                  System.out.println("Not data inserted");
              }
              
            }
            
        } catch (Exception ex) {
            System.out.println("Not Connected"+ex);
        }
   }
     public void loopSecurity(int ID){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Config config = new Config();
            Connection con2= (Connection) DriverManager.getConnection("jdbc:mysql://"+config.getMysqlhost()+config.getMysqldb(), config.getMysqlUser(), config.getMysqlPassword());
            //System.out.println("Connected");
            
            Statement st=con2.createStatement();
            ResultSet rs= st.executeQuery("SELECT * FROM sd_security_print_mgr WHERE id='"+ID+"'");
            while(rs.next()){

              int id=rs.getInt(1);
              String permit_no=rs.getString(2);
              String permit_type=rs.getString(3);
              String access_area=rs.getString(4);
              String applicant_name=rs.getString(5);
              String doc_no=rs.getString(6);
              String operator=rs.getString(7);
              String designation=rs.getString(8);
              String department=rs.getString(9);
              String application_date=rs.getString(10);
              String expiry_date=rs.getString(11);
              String applicant_photo_file=rs.getString(12);
              String applicant_photo=rs.getString(13);
              String applicant_signature_file=rs.getString(14);
              String applicant_signature=rs.getString(15);
              String ceo_signature_file=rs.getString(16).toString().trim();
              String ceo_signature=rs.getString(17);
              String batch_id=rs.getString(18);
              String airport_airstrip_name=rs.getString(19);
              String print_template_name=rs.getString(20);
              String date=rs.getString(21);
              
              
              PreparedStatement ps2= con2.prepareStatement("INSERT INTO sd_security_printed_cards_mgr VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
              ps2.setInt(1,id);
              ps2.setString(2, permit_no);
              ps2.setString(3, permit_type);
              ps2.setString(4, access_area);
              ps2.setString(5,applicant_name);
              ps2.setString(6, doc_no);
              ps2.setString(7,operator);
              ps2.setString(8, designation);
              ps2.setString(9,department);
              ps2.setString(10,application_date);
              ps2.setString(11,expiry_date);
              ps2.setString(12,applicant_photo_file);
              ps2.setString(13,applicant_photo);
              ps2.setString(14,applicant_signature_file);
              ps2.setString(15,applicant_signature);
              ps2.setString(16,ceo_signature_file);
              ps2.setString(17,ceo_signature);
              ps2.setString(18,batch_id);
              ps2.setString(19,airport_airstrip_name);
              ps2.setString(20,print_template_name);
              ps2.setString(21,date);
              
              
              int check=ps2.executeUpdate();
              if(check>0){
                   PreparedStatement ps3= con2.prepareStatement("DELETE FROM sd_security_print_mgr WHERE id=?");
                   ps3.setInt(1,ID);
                   ps3.executeUpdate();
                   updateTrackerSecurity(ID);
                   
                  System.out.println("Secuirty Record migrated  succesfully");
                 
                
            }
              else{
                  System.out.println("Not data inserted");
              }
              
            }
            
        } catch (Exception ex) {
            System.out.println("Not Connected"+ex);
        }
   }
     
      //updating remote Mysql for the Airside 
     public void updateAirsides(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Config config = new Config();
            Connection con2= (Connection) DriverManager.getConnection("jdbc:mysql://"+config.getMysqlhost()+config.getMysqldb(), config.getMysqlUser(), config.getMysqlPassword());
            //System.out.println("Connected");
            int ID=getAirsideId();
            Statement st=con2.createStatement();
            ResultSet rs= st.executeQuery("SELECT * FROM pa_allareas_print_mgr WHERE id='"+ID+"'");
            while(rs.next()){

              int id=rs.getInt(1);
              String permit_no=rs.getString(2);
              String permit_type=rs.getString(3);
              String access_area=rs.getString(4);
              String applicant_name=rs.getString(5);
              String doc_no=rs.getString(6);
              String operator=rs.getString(7);
              String designation=rs.getString(8);
              String application_date=rs.getString(9);
              String expiry_date=rs.getString(10);
              String applicant_photo_file=rs.getString(11);
              String applicant_photo=rs.getString(12);
              String applicant_signature_file=rs.getString(13);
              String applicant_signature=rs.getString(14);
              String ceo_signature_file=rs.getString(15).toString().trim();
              String ceo_signature=rs.getString(16);
              String batch_id=rs.getString(17);
              String airport_airstrip_name=rs.getString(18);
              String print_template_name=rs.getString(19);
              String date=rs.getString(20);
              
              
              PreparedStatement ps2= con2.prepareStatement("INSERT INTO pa_allareas_printed_cards_mgr VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
              ps2.setInt(1,id);
              ps2.setString(2, permit_no);
              ps2.setString(3, permit_type);
              ps2.setString(4, access_area);
              ps2.setString(5,applicant_name);
              ps2.setString(6, doc_no);
              ps2.setString(7,operator);
              ps2.setString(8, designation);
              ps2.setString(9,application_date);
              ps2.setString(10,expiry_date);
              ps2.setString(11,applicant_photo_file);
              ps2.setString(12,applicant_photo);
              ps2.setString(13,applicant_signature_file);
              ps2.setString(14,applicant_signature);
              ps2.setString(15,ceo_signature_file);
              ps2.setString(16,ceo_signature);
              ps2.setString(17,batch_id);
              ps2.setString(18,airport_airstrip_name);
              ps2.setString(19,print_template_name);
              ps2.setString(20,date);
              
              int check=ps2.executeUpdate();
              if(check>0){
                   PreparedStatement ps3= con2.prepareStatement("DELETE FROM pa_allareas_print_mgr WHERE id=?");
                   ps3.setInt(1,ID);
                   ps3.executeUpdate();
                   updateTrackerAirside(ID);
                   createFreightUI();
                   
                  System.out.println("Airside  Record migrated  succesfully");
                  
                
            }
              else{
                  System.out.println("Not data inserted");
              }
              
            }
            
        } catch (Exception ex) {
            System.out.println("Not Connected"+ex);
        }
   }
     public void loopAirsides(int ID){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Config config = new Config();
            Connection con2= (Connection) DriverManager.getConnection("jdbc:mysql://"+config.getMysqlhost()+config.getMysqldb(), config.getMysqlUser(), config.getMysqlPassword());
            //System.out.println("Connected");
            
            Statement st=con2.createStatement();
            ResultSet rs= st.executeQuery("SELECT * FROM pa_allareas_print_mgr WHERE id='"+ID+"'");
            while(rs.next()){

              int id=rs.getInt(1);
              String permit_no=rs.getString(2);
              String permit_type=rs.getString(3);
              String access_area=rs.getString(4);
              String applicant_name=rs.getString(5);
              String doc_no=rs.getString(6);
              String operator=rs.getString(7);
              String designation=rs.getString(8);
              String application_date=rs.getString(9);
              String expiry_date=rs.getString(10);
              String applicant_photo_file=rs.getString(11);
              String applicant_photo=rs.getString(12);
              String applicant_signature_file=rs.getString(13);
              String applicant_signature=rs.getString(14);
              String ceo_signature_file=rs.getString(15).toString().trim();
              String ceo_signature=rs.getString(16);
              String batch_id=rs.getString(17);
              String airport_airstrip_name=rs.getString(18);
              String print_template_name=rs.getString(19);
              String date=rs.getString(20);
              
              
              PreparedStatement ps2= con2.prepareStatement("INSERT INTO pa_allareas_printed_cards_mgr VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
              ps2.setInt(1,id);
              ps2.setString(2, permit_no);
              ps2.setString(3, permit_type);
              ps2.setString(4, access_area);
              ps2.setString(5,applicant_name);
              ps2.setString(6, doc_no);
              ps2.setString(7,operator);
              ps2.setString(8, designation);
              ps2.setString(9,application_date);
              ps2.setString(10,expiry_date);
              ps2.setString(11,applicant_photo_file);
              ps2.setString(12,applicant_photo);
              ps2.setString(13,applicant_signature_file);
              ps2.setString(14,applicant_signature);
              ps2.setString(15,ceo_signature_file);
              ps2.setString(16,ceo_signature);
              ps2.setString(17,batch_id);
              ps2.setString(18,airport_airstrip_name);
              ps2.setString(19,print_template_name);
              ps2.setString(20,date);
              
              int check=ps2.executeUpdate();
              if(check>0){
                   PreparedStatement ps3= con2.prepareStatement("DELETE FROM pa_allareas_print_mgr WHERE id=?");
                   ps3.setInt(1,ID);
                   ps3.executeUpdate();
                   updateTrackerFreight(ID);
                   
                  System.out.println("Airside Record migrated  succesfully");
                
            }
              else{
                  System.out.println("Not data inserted");
              }
              
            }
            
        } catch (Exception ex) {
            System.out.println("Not Connected"+ex);
        }
   }
     
      //updating remote Mysql for the Aprons 
     public void updateAprons(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Config config = new Config();
            Connection con2= (Connection) DriverManager.getConnection("jdbc:mysql://"+config.getMysqlhost()+config.getMysqldb(), config.getMysqlUser(), config.getMysqlPassword());
            //System.out.println("Connected");
            int ID=getApronId();
            Statement st=con2.createStatement();
            ResultSet rs= st.executeQuery("SELECT * FROM pa_apron_print_mgr WHERE id='"+ID+"'");
            while(rs.next()){

              int id=rs.getInt(1);
              String permit_no=rs.getString(2);
              String permit_type=rs.getString(3);
              String access_area=rs.getString(4);
              String applicant_name=rs.getString(5);
              String doc_no=rs.getString(6);
              String operator=rs.getString(7);
              String designation=rs.getString(8);
              String application_date=rs.getString(9);
              String expiry_date=rs.getString(10);
              String applicant_photo_file=rs.getString(11);
              String applicant_photo=rs.getString(12);
              String applicant_signature_file=rs.getString(13);
              String applicant_signature=rs.getString(14);
              String ceo_signature_file=rs.getString(15).toString().trim();
              String ceo_signature=rs.getString(16);
              String batch_id=rs.getString(17);
              String airport_airstrip_name=rs.getString(18);
              String print_template_name=rs.getString(19);
              String date=rs.getString(20);
              
              
              PreparedStatement ps2= con2.prepareStatement("INSERT INTO pa_apron_printed_cards_mgr VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
              ps2.setInt(1,id);
              ps2.setString(2, permit_no);
              ps2.setString(3, permit_type);
              ps2.setString(4, access_area);
              ps2.setString(5,applicant_name);
              ps2.setString(6, doc_no);
              ps2.setString(7,operator);
              ps2.setString(8, designation);
              ps2.setString(9,application_date);
              ps2.setString(10,expiry_date);
              ps2.setString(11,applicant_photo_file);
              ps2.setString(12,applicant_photo);
              ps2.setString(13,applicant_signature_file);
              ps2.setString(14,applicant_signature);
              ps2.setString(15,ceo_signature_file);
              ps2.setString(16,ceo_signature);
              ps2.setString(17,batch_id);
              ps2.setString(18,airport_airstrip_name);
              ps2.setString(19,print_template_name);
              ps2.setString(20,date);
              
              int check=ps2.executeUpdate();
              if(check>0){
                   PreparedStatement ps3= con2.prepareStatement("DELETE FROM pa_apron_print_mgr WHERE id=?");
                   ps3.setInt(1,ID);
                   ps3.executeUpdate();
                   updateTrackerApron(ID);
                   createApronUI();
                   
                  System.out.println("Apron  Record migrated  succesfully");
                  
                
            }
              else{
                  System.out.println("Not data inserted");
              }
              
            }
            
        } catch (Exception ex) {
            System.out.println("Not Connected"+ex);
        }
   }
     public void loopAprons(int ID){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Config config = new Config();
            Connection con2= (Connection) DriverManager.getConnection("jdbc:mysql://"+config.getMysqlhost()+config.getMysqldb(), config.getMysqlUser(), config.getMysqlPassword());
            //System.out.println("Connected");
            
            Statement st=con2.createStatement();
            ResultSet rs= st.executeQuery("SELECT * FROM pa_apron_print_mgr WHERE id='"+ID+"'");
            while(rs.next()){

              int id=rs.getInt(1);
              String permit_no=rs.getString(2);
              String permit_type=rs.getString(3);
              String access_area=rs.getString(4);
              String applicant_name=rs.getString(5);
              String doc_no=rs.getString(6);
              String operator=rs.getString(7);
              String designation=rs.getString(8);
              String application_date=rs.getString(9);
              String expiry_date=rs.getString(10);
              String applicant_photo_file=rs.getString(11);
              String applicant_photo=rs.getString(12);
              String applicant_signature_file=rs.getString(13);
              String applicant_signature=rs.getString(14);
              String ceo_signature_file=rs.getString(15).toString().trim();
              String ceo_signature=rs.getString(16);
              String batch_id=rs.getString(17);
              String airport_airstrip_name=rs.getString(18);
              String print_template_name=rs.getString(19);
              String date=rs.getString(20);
              
              
              PreparedStatement ps2= con2.prepareStatement("INSERT INTO pa_apron_printed_cards_mgr VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
              ps2.setInt(1,id);
              ps2.setString(2, permit_no);
              ps2.setString(3, permit_type);
              ps2.setString(4, access_area);
              ps2.setString(5,applicant_name);
              ps2.setString(6, doc_no);
              ps2.setString(7,operator);
              ps2.setString(8, designation);
              ps2.setString(9,application_date);
              ps2.setString(10,expiry_date);
              ps2.setString(11,applicant_photo_file);
              ps2.setString(12,applicant_photo);
              ps2.setString(13,applicant_signature_file);
              ps2.setString(14,applicant_signature);
              ps2.setString(15,ceo_signature_file);
              ps2.setString(16,ceo_signature);
              ps2.setString(17,batch_id);
              ps2.setString(18,airport_airstrip_name);
              ps2.setString(19,print_template_name);
              ps2.setString(20,date);
              
              int check=ps2.executeUpdate();
              if(check>0){
                   PreparedStatement ps3= con2.prepareStatement("DELETE FROM pa_apron_print_mgr WHERE id=?");
                   ps3.setInt(1,ID);
                   ps3.executeUpdate();
                   updateTrackerApron(ID);
                   
                  System.out.println("Apron Record migrated  succesfully");
                
            }
              else{
                  System.out.println("Not data inserted");
              }
              
            }
            
        } catch (Exception ex) {
            System.out.println("Not Connected"+ex);
        }
   }
     
     //updating remote Mysql for the Aprons 
     public void updateApronLounges(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Config config = new Config();
            Connection con2= (Connection) DriverManager.getConnection("jdbc:mysql://"+config.getMysqlhost()+config.getMysqldb(), config.getMysqlUser(), config.getMysqlPassword());
            //System.out.println("Connected");
            int ID=getApronLoungesId();
            Statement st=con2.createStatement();
            ResultSet rs= st.executeQuery("SELECT * FROM pa_apronlounges_print_mgr WHERE id='"+ID+"'");
            while(rs.next()){

              int id=rs.getInt(1);
              String permit_no=rs.getString(2);
              String permit_type=rs.getString(3);
              String access_area=rs.getString(4);
              String applicant_name=rs.getString(5);
              String doc_no=rs.getString(6);
              String operator=rs.getString(7);
              String designation=rs.getString(8);
              String application_date=rs.getString(9);
              String expiry_date=rs.getString(10);
              String applicant_photo_file=rs.getString(11);
              String applicant_photo=rs.getString(12);
              String applicant_signature_file=rs.getString(13);
              String applicant_signature=rs.getString(14);
              String ceo_signature_file=rs.getString(15).toString().trim();
              String ceo_signature=rs.getString(16);
              String batch_id=rs.getString(17);
              String airport_airstrip_name=rs.getString(18);
              String print_template_name=rs.getString(19);
              String date=rs.getString(20);
              
              
              PreparedStatement ps2= con2.prepareStatement("INSERT INTO pa_apronlounges_printed_cards_mgr VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
              ps2.setInt(1,id);
              ps2.setString(2, permit_no);
              ps2.setString(3, permit_type);
              ps2.setString(4, access_area);
              ps2.setString(5,applicant_name);
              ps2.setString(6, doc_no);
              ps2.setString(7,operator);
              ps2.setString(8, designation);
              ps2.setString(9,application_date);
              ps2.setString(10,expiry_date);
              ps2.setString(11,applicant_photo_file);
              ps2.setString(12,applicant_photo);
              ps2.setString(13,applicant_signature_file);
              ps2.setString(14,applicant_signature);
              ps2.setString(15,ceo_signature_file);
              ps2.setString(16,ceo_signature);
              ps2.setString(17,batch_id);
              ps2.setString(18,airport_airstrip_name);
              ps2.setString(19,print_template_name);
              ps2.setString(20,date);
              
              int check=ps2.executeUpdate();
              if(check>0){
                   PreparedStatement ps3= con2.prepareStatement("DELETE FROM pa_apronlounges_print_mgr WHERE id=?");
                   ps3.setInt(1,ID);
                   ps3.executeUpdate();
                   updateTrackerApron(ID);
                   createApronLoungeUI();
                   
                  System.out.println("Apron lounge  Record migrated  succesfully");
                  
                
            }
              else{
                  System.out.println("Not data inserted");
              }
              
            }
            
        } catch (Exception ex) {
            System.out.println("Not Connected"+ex);
        }
   }
     public void loopApronLounges(int ID){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Config config = new Config();
            Connection con2= (Connection) DriverManager.getConnection("jdbc:mysql://"+config.getMysqlhost()+config.getMysqldb(), config.getMysqlUser(), config.getMysqlPassword());
            //System.out.println("Connected");
            
            Statement st=con2.createStatement();
            ResultSet rs= st.executeQuery("SELECT * FROM pa_apronlounges_print_mgr WHERE id='"+ID+"'");
            while(rs.next()){

              int id=rs.getInt(1);
              String permit_no=rs.getString(2);
              String permit_type=rs.getString(3);
              String access_area=rs.getString(4);
              String applicant_name=rs.getString(5);
              String doc_no=rs.getString(6);
              String operator=rs.getString(7);
              String designation=rs.getString(8);
              String application_date=rs.getString(9);
              String expiry_date=rs.getString(10);
              String applicant_photo_file=rs.getString(11);
              String applicant_photo=rs.getString(12);
              String applicant_signature_file=rs.getString(13);
              String applicant_signature=rs.getString(14);
              String ceo_signature_file=rs.getString(15).toString().trim();
              String ceo_signature=rs.getString(16);
              String batch_id=rs.getString(17);
              String airport_airstrip_name=rs.getString(18);
              String print_template_name=rs.getString(19);
              String date=rs.getString(20);
              
              
              PreparedStatement ps2= con2.prepareStatement("INSERT INTO pa_apronlounges_printed_cards_mgr VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
              ps2.setInt(1,id);
              ps2.setString(2, permit_no);
              ps2.setString(3, permit_type);
              ps2.setString(4, access_area);
              ps2.setString(5,applicant_name);
              ps2.setString(6, doc_no);
              ps2.setString(7,operator);
              ps2.setString(8, designation);
              ps2.setString(9,application_date);
              ps2.setString(10,expiry_date);
              ps2.setString(11,applicant_photo_file);
              ps2.setString(12,applicant_photo);
              ps2.setString(13,applicant_signature_file);
              ps2.setString(14,applicant_signature);
              ps2.setString(15,ceo_signature_file);
              ps2.setString(16,ceo_signature);
              ps2.setString(17,batch_id);
              ps2.setString(18,airport_airstrip_name);
              ps2.setString(19,print_template_name);
              ps2.setString(20,date);
              
              int check=ps2.executeUpdate();
              if(check>0){
                   PreparedStatement ps3= con2.prepareStatement("DELETE FROM pa_apronlounges_print_mgr WHERE id=?");
                   ps3.setInt(1,ID);
                   ps3.executeUpdate();
                   updateTrackerApron(ID);
                   
                  System.out.println("Apron Lounge Record migrated  succesfully");
                
            }
              else{
                  System.out.println("Not data inserted");
              }
              
            }
            
        } catch (Exception ex) {
            System.out.println("Not Connected"+ex);
        }
   }
        //updating remote Mysql for the Aprons 
     public void updateApronTaxways(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Config config = new Config();
            Connection con2= (Connection) DriverManager.getConnection("jdbc:mysql://"+config.getMysqlhost()+config.getMysqldb(), config.getMysqlUser(), config.getMysqlPassword());
            //System.out.println("Connected");
            int ID=getApronTaxwayId();
            Statement st=con2.createStatement();
            ResultSet rs= st.executeQuery("SELECT * FROM pa_aprontaxwayrunway_print_mgr WHERE id='"+ID+"'");
            while(rs.next()){

              int id=rs.getInt(1);
              String permit_no=rs.getString(2);
              String permit_type=rs.getString(3);
              String access_area=rs.getString(4);
              String applicant_name=rs.getString(5);
              String doc_no=rs.getString(6);
              String operator=rs.getString(7);
              String designation=rs.getString(8);
              String application_date=rs.getString(9);
              String expiry_date=rs.getString(10);
              String applicant_photo_file=rs.getString(11);
              String applicant_photo=rs.getString(12);
              String applicant_signature_file=rs.getString(13);
              String applicant_signature=rs.getString(14);
              String ceo_signature_file=rs.getString(15).toString().trim();
              String ceo_signature=rs.getString(16);
              String batch_id=rs.getString(17);
              String airport_airstrip_name=rs.getString(18);
              String print_template_name=rs.getString(19);
              String date=rs.getString(20);
              
              
              PreparedStatement ps2= con2.prepareStatement("INSERT INTO pa_aprontaxwayrunway_printed_cards_mgr VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
              ps2.setInt(1,id);
              ps2.setString(2, permit_no);
              ps2.setString(3, permit_type);
              ps2.setString(4, access_area);
              ps2.setString(5,applicant_name);
              ps2.setString(6, doc_no);
              ps2.setString(7,operator);
              ps2.setString(8, designation);
              ps2.setString(9,application_date);
              ps2.setString(10,expiry_date);
              ps2.setString(11,applicant_photo_file);
              ps2.setString(12,applicant_photo);
              ps2.setString(13,applicant_signature_file);
              ps2.setString(14,applicant_signature);
              ps2.setString(15,ceo_signature_file);
              ps2.setString(16,ceo_signature);
              ps2.setString(17,batch_id);
              ps2.setString(18,airport_airstrip_name);
              ps2.setString(19,print_template_name);
              ps2.setString(20,date);
              
              int check=ps2.executeUpdate();
              if(check>0){
                   PreparedStatement ps3= con2.prepareStatement("DELETE FROM pa_aprontaxwayrunway_print_mgr WHERE id=?");
                   ps3.setInt(1,ID);
                   ps3.executeUpdate();
                   updateTrackerTaxway(ID);
                   createApronTaxwayUI();
                   
                  System.out.println("Apron Taxway runway  Record migrated  succesfully");
                  
                
            }
              else{
                  System.out.println("Not data inserted");
              }
              
            }
            
        } catch (Exception ex) {
            System.out.println("Not Connected"+ex);
        }
   }
     public void loopApronTaxways(int ID){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Config config = new Config();
            Connection con2= (Connection) DriverManager.getConnection("jdbc:mysql://"+config.getMysqlhost()+config.getMysqldb(), config.getMysqlUser(), config.getMysqlPassword());
            //System.out.println("Connected");
            
            Statement st=con2.createStatement();
            ResultSet rs= st.executeQuery("SELECT * FROM pa_aprontaxwayrunway_print_mgr WHERE id='"+ID+"'");
            while(rs.next()){

              int id=rs.getInt(1);
              String permit_no=rs.getString(2);
              String permit_type=rs.getString(3);
              String access_area=rs.getString(4);
              String applicant_name=rs.getString(5);
              String doc_no=rs.getString(6);
              String operator=rs.getString(7);
              String designation=rs.getString(8);
              String application_date=rs.getString(9);
              String expiry_date=rs.getString(10);
              String applicant_photo_file=rs.getString(11);
              String applicant_photo=rs.getString(12);
              String applicant_signature_file=rs.getString(13);
              String applicant_signature=rs.getString(14);
              String ceo_signature_file=rs.getString(15).toString().trim();
              String ceo_signature=rs.getString(16);
              String batch_id=rs.getString(17);
              String airport_airstrip_name=rs.getString(18);
              String print_template_name=rs.getString(19);
              String date=rs.getString(20);
              
              
              PreparedStatement ps2= con2.prepareStatement("INSERT INTO pa_aprontaxwayrunway_printed_cards_mgr VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
              ps2.setInt(1,id);
              ps2.setString(2, permit_no);
              ps2.setString(3, permit_type);
              ps2.setString(4, access_area);
              ps2.setString(5,applicant_name);
              ps2.setString(6, doc_no);
              ps2.setString(7,operator);
              ps2.setString(8, designation);
              ps2.setString(9,application_date);
              ps2.setString(10,expiry_date);
              ps2.setString(11,applicant_photo_file);
              ps2.setString(12,applicant_photo);
              ps2.setString(13,applicant_signature_file);
              ps2.setString(14,applicant_signature);
              ps2.setString(15,ceo_signature_file);
              ps2.setString(16,ceo_signature);
              ps2.setString(17,batch_id);
              ps2.setString(18,airport_airstrip_name);
              ps2.setString(19,print_template_name);
              ps2.setString(20,date);
              
              int check=ps2.executeUpdate();
              if(check>0){
                   PreparedStatement ps3= con2.prepareStatement("DELETE FROM pa_aprontaxwayrunway_print_mgr WHERE id=?");
                   ps3.setInt(1,ID);
                   ps3.executeUpdate();
                   updateTrackerTaxway(ID);
                   
                  System.out.println("Apron Taxway Record migrated  succesfully");
                
            }
              else{
                  System.out.println("Not data inserted");
              }
              
            }
            
        } catch (Exception ex) {
            System.out.println("Not Connected"+ex);
        }
   }
     
         //updating remote Mysql for the Baggage Hall 
     public void updateBaggageHalls(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Config config = new Config();
            Connection con2= (Connection) DriverManager.getConnection("jdbc:mysql://"+config.getMysqlhost()+config.getMysqldb(), config.getMysqlUser(), config.getMysqlPassword());
            //System.out.println("Connected");
            int ID=getBaggageHallId();
            Statement st=con2.createStatement();
            ResultSet rs= st.executeQuery("SELECT * FROM pa_baggagehall_print_mgr WHERE id='"+ID+"'");
            while(rs.next()){

              int id=rs.getInt(1);
              String permit_no=rs.getString(2);
              String permit_type=rs.getString(3);
              String access_area=rs.getString(4);
              String applicant_name=rs.getString(5);
              String doc_no=rs.getString(6);
              String operator=rs.getString(7);
              String designation=rs.getString(8);
              String application_date=rs.getString(9);
              String expiry_date=rs.getString(10);
              String applicant_photo_file=rs.getString(11);
              String applicant_photo=rs.getString(12);
              String applicant_signature_file=rs.getString(13);
              String applicant_signature=rs.getString(14);
              String ceo_signature_file=rs.getString(15).toString().trim();
              String ceo_signature=rs.getString(16);
              String batch_id=rs.getString(17);
              String airport_airstrip_name=rs.getString(18);
              String print_template_name=rs.getString(19);
              String date=rs.getString(20);
              
              
              PreparedStatement ps2= con2.prepareStatement("INSERT INTO pa_baggagehall_printed_cards_mgr VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
              ps2.setInt(1,id);
              ps2.setString(2, permit_no);
              ps2.setString(3, permit_type);
              ps2.setString(4, access_area);
              ps2.setString(5,applicant_name);
              ps2.setString(6, doc_no);
              ps2.setString(7,operator);
              ps2.setString(8, designation);
              ps2.setString(9,application_date);
              ps2.setString(10,expiry_date);
              ps2.setString(11,applicant_photo_file);
              ps2.setString(12,applicant_photo);
              ps2.setString(13,applicant_signature_file);
              ps2.setString(14,applicant_signature);
              ps2.setString(15,ceo_signature_file);
              ps2.setString(16,ceo_signature);
              ps2.setString(17,batch_id);
              ps2.setString(18,airport_airstrip_name);
              ps2.setString(19,print_template_name);
              ps2.setString(20,date);
              
              int check=ps2.executeUpdate();
              if(check>0){
                   PreparedStatement ps3= con2.prepareStatement("DELETE FROM pa_baggagehall_print_mgr WHERE id=?");
                   ps3.setInt(1,ID);
                   ps3.executeUpdate();
                   updateTrackerBaggageHall(ID);
                   createBaggageHallUI();
                   
                  System.out.println("Baggage Hall  Record migrated  succesfully");
                  
                
            }
              else{
                  System.out.println("Not data inserted");
              }
              
            }
            
        } catch (Exception ex) {
            System.out.println("Not Connected"+ex);
        }
   }
     public void loopBaggageHalls(int ID){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Config config = new Config();
            Connection con2= (Connection) DriverManager.getConnection("jdbc:mysql://"+config.getMysqlhost()+config.getMysqldb(), config.getMysqlUser(), config.getMysqlPassword());
            //System.out.println("Connected");
            
            Statement st=con2.createStatement();
            ResultSet rs= st.executeQuery("SELECT * FROM pa_baggagehall_print_mgr WHERE id='"+ID+"'");
            while(rs.next()){

              int id=rs.getInt(1);
              String permit_no=rs.getString(2);
              String permit_type=rs.getString(3);
              String access_area=rs.getString(4);
              String applicant_name=rs.getString(5);
              String doc_no=rs.getString(6);
              String operator=rs.getString(7);
              String designation=rs.getString(8);
              String application_date=rs.getString(9);
              String expiry_date=rs.getString(10);
              String applicant_photo_file=rs.getString(11);
              String applicant_photo=rs.getString(12);
              String applicant_signature_file=rs.getString(13);
              String applicant_signature=rs.getString(14);
              String ceo_signature_file=rs.getString(15).toString().trim();
              String ceo_signature=rs.getString(16);
              String batch_id=rs.getString(17);
              String airport_airstrip_name=rs.getString(18);
              String print_template_name=rs.getString(19);
              String date=rs.getString(20);
              
              
              PreparedStatement ps2= con2.prepareStatement("INSERT INTO pa_baggagehall_printed_cards_mgr VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
              ps2.setInt(1,id);
              ps2.setString(2, permit_no);
              ps2.setString(3, permit_type);
              ps2.setString(4, access_area);
              ps2.setString(5,applicant_name);
              ps2.setString(6, doc_no);
              ps2.setString(7,operator);
              ps2.setString(8, designation);
              ps2.setString(9,application_date);
              ps2.setString(10,expiry_date);
              ps2.setString(11,applicant_photo_file);
              ps2.setString(12,applicant_photo);
              ps2.setString(13,applicant_signature_file);
              ps2.setString(14,applicant_signature);
              ps2.setString(15,ceo_signature_file);
              ps2.setString(16,ceo_signature);
              ps2.setString(17,batch_id);
              ps2.setString(18,airport_airstrip_name);
              ps2.setString(19,print_template_name);
              ps2.setString(20,date);
              
              int check=ps2.executeUpdate();
              if(check>0){
                   PreparedStatement ps3= con2.prepareStatement("DELETE FROM pa_baggagehall_print_mgr WHERE id=?");
                   ps3.setInt(1,ID);
                   ps3.executeUpdate();
                   updateTrackerTaxway(ID);
                   
                  System.out.println("Baggage Hall Record migrated  succesfully");
                
            }
              else{
                  System.out.println("Not data inserted");
              }
              
            }
            
        } catch (Exception ex) {
            System.out.println("Not Connected"+ex);
        }
   }
     
     
         //updating remote Mysql for the checkin counter 
     public void updateCheckinCounters(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Config config = new Config();
            Connection con2= (Connection) DriverManager.getConnection("jdbc:mysql://"+config.getMysqlhost()+config.getMysqldb(), config.getMysqlUser(), config.getMysqlPassword());
            //System.out.println("Connected");
            int ID=getCheckinCounterId();
            System.out.println(ID);
            Statement st=con2.createStatement();
            ResultSet rs= st.executeQuery("SELECT * FROM pa_checkincounter_print_mgr WHERE id='"+ID+"'");
            while(rs.next()){

              int id=rs.getInt(1);
              String permit_no=rs.getString(2);
              String permit_type=rs.getString(3);
              String access_area=rs.getString(4);
              String applicant_name=rs.getString(5);
              String doc_no=rs.getString(6);
              String operator=rs.getString(7);
              String designation=rs.getString(8);
              String application_date=rs.getString(9);
              String expiry_date=rs.getString(10);
              String applicant_photo_file=rs.getString(11);
              String applicant_photo=rs.getString(12);
              String applicant_signature_file=rs.getString(13);
              String applicant_signature=rs.getString(14);
              String ceo_signature_file=rs.getString(15).toString().trim();
              String ceo_signature=rs.getString(16);
              String batch_id=rs.getString(17);
              String airport_airstrip_name=rs.getString(18);
              String print_template_name=rs.getString(19);
              String date=rs.getString(20);
              
              
              PreparedStatement ps2= con2.prepareStatement("INSERT INTO pa_checkincounter_printed_cards_mgr VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
              ps2.setInt(1,id);
              ps2.setString(2, permit_no);
              ps2.setString(3, permit_type);
              ps2.setString(4, access_area);
              ps2.setString(5,applicant_name);
              ps2.setString(6, doc_no);
              ps2.setString(7,operator);
              ps2.setString(8, designation);
              ps2.setString(9,application_date);
              ps2.setString(10,expiry_date);
              ps2.setString(11,applicant_photo_file);
              ps2.setString(12,applicant_photo);
              ps2.setString(13,applicant_signature_file);
              ps2.setString(14,applicant_signature);
              ps2.setString(15,ceo_signature_file);
              ps2.setString(16,ceo_signature);
              ps2.setString(17,batch_id);
              ps2.setString(18,airport_airstrip_name);
              ps2.setString(19,print_template_name);
              ps2.setString(20,date);
              
              int check=ps2.executeUpdate();
              if(check>0){
                   PreparedStatement ps3= con2.prepareStatement("DELETE FROM pa_checkincounter_print_mgr WHERE id=?");
                   ps3.setInt(1,ID);
                   ps3.executeUpdate();
                   updateTrackerCheckinCounter(ID);
                   createCheckinCounterUI();
                   
                  System.out.println("Checkin counter  Record migrated  succesfully");
                  
                
            }
              else{
                  System.out.println("Not data inserted");
              }
              
            }
            
        } catch (Exception ex) {
            System.out.println("Not Connected"+ex);
        }
   }
     public void loopCheckinCounters(int ID){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Config config = new Config();
            Connection con2= (Connection) DriverManager.getConnection("jdbc:mysql://"+config.getMysqlhost()+config.getMysqldb(), config.getMysqlUser(), config.getMysqlPassword());
            //System.out.println("Connected");
            
            Statement st=con2.createStatement();
            ResultSet rs= st.executeQuery("SELECT * FROM pa_checkincounter_print_mgr WHERE id='"+ID+"'");
            while(rs.next()){

              int id=rs.getInt(1);
              String permit_no=rs.getString(2);
              String permit_type=rs.getString(3);
              String access_area=rs.getString(4);
              String applicant_name=rs.getString(5);
              String doc_no=rs.getString(6);
              String operator=rs.getString(7);
              String designation=rs.getString(8);
              String application_date=rs.getString(9);
              String expiry_date=rs.getString(10);
              String applicant_photo_file=rs.getString(11);
              String applicant_photo=rs.getString(12);
              String applicant_signature_file=rs.getString(13);
              String applicant_signature=rs.getString(14);
              String ceo_signature_file=rs.getString(15).toString().trim();
              String ceo_signature=rs.getString(16);
              String batch_id=rs.getString(17);
              String airport_airstrip_name=rs.getString(18);
              String print_template_name=rs.getString(19);
              String date=rs.getString(20);
              
              
              PreparedStatement ps2= con2.prepareStatement("INSERT INTO pa_checkincounter_printed_cards_mgr VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
              ps2.setInt(1,id);
              ps2.setString(2, permit_no);
              ps2.setString(3, permit_type);
              ps2.setString(4, access_area);
              ps2.setString(5,applicant_name);
              ps2.setString(6, doc_no);
              ps2.setString(7,operator);
              ps2.setString(8, designation);
              ps2.setString(9,application_date);
              ps2.setString(10,expiry_date);
              ps2.setString(11,applicant_photo_file);
              ps2.setString(12,applicant_photo);
              ps2.setString(13,applicant_signature_file);
              ps2.setString(14,applicant_signature);
              ps2.setString(15,ceo_signature_file);
              ps2.setString(16,ceo_signature);
              ps2.setString(17,batch_id);
              ps2.setString(18,airport_airstrip_name);
              ps2.setString(19,print_template_name);
              ps2.setString(20,date);
              
              int check=ps2.executeUpdate();
              if(check>0){
                   PreparedStatement ps3= con2.prepareStatement("DELETE FROM pa_checkincounter_print_mgr WHERE id=?");
                   ps3.setInt(1,ID);
                   ps3.executeUpdate();
                   updateTrackerTaxway(ID);
                   
                  System.out.println("Checkin counter Record migrated  succesfully");
                
            }
              else{
                  System.out.println("Not data inserted");
              }
              
            }
            
        } catch (Exception ex) {
            System.out.println("Not Connected"+ex);
        }
   }
     
     //updating remote Mysql for the Aprons 
     public void updateConCourses(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Config config = new Config();
            Connection con2= (Connection) DriverManager.getConnection("jdbc:mysql://"+config.getMysqlhost()+config.getMysqldb(), config.getMysqlUser(), config.getMysqlPassword());
            //System.out.println("Connected");
            int ID=getConCourseId();
            Statement st=con2.createStatement();
            ResultSet rs= st.executeQuery("SELECT * FROM pa_concoursearrivals_print_mgr WHERE id='"+ID+"'");
            while(rs.next()){

              int id=rs.getInt(1);
              String permit_no=rs.getString(2);
              String permit_type=rs.getString(3);
              String access_area=rs.getString(4);
              String applicant_name=rs.getString(5);
              String doc_no=rs.getString(6);
              String operator=rs.getString(7);
              String designation=rs.getString(8);
              String application_date=rs.getString(9);
              String expiry_date=rs.getString(10);
              String applicant_photo_file=rs.getString(11);
              String applicant_photo=rs.getString(12);
              String applicant_signature_file=rs.getString(13);
              String applicant_signature=rs.getString(14);
              String ceo_signature_file=rs.getString(15).toString().trim();
              String ceo_signature=rs.getString(16);
              String batch_id=rs.getString(17);
              String airport_airstrip_name=rs.getString(18);
              String print_template_name=rs.getString(19);
              String date=rs.getString(20);
              
              
              PreparedStatement ps2= con2.prepareStatement("INSERT INTO pa_concoursearrivals_printed_cards_mgr VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
              ps2.setInt(1,id);
              ps2.setString(2, permit_no);
              ps2.setString(3, permit_type);
              ps2.setString(4, access_area);
              ps2.setString(5,applicant_name);
              ps2.setString(6, doc_no);
              ps2.setString(7,operator);
              ps2.setString(8, designation);
              ps2.setString(9,application_date);
              ps2.setString(10,expiry_date);
              ps2.setString(11,applicant_photo_file);
              ps2.setString(12,applicant_photo);
              ps2.setString(13,applicant_signature_file);
              ps2.setString(14,applicant_signature);
              ps2.setString(15,ceo_signature_file);
              ps2.setString(16,ceo_signature);
              ps2.setString(17,batch_id);
              ps2.setString(18,airport_airstrip_name);
              ps2.setString(19,print_template_name);
              ps2.setString(20,date);
              
              int check=ps2.executeUpdate();
              if(check>0){
                   PreparedStatement ps3= con2.prepareStatement("DELETE FROM pa_concoursearrivals_print_mgr WHERE id=?");
                   ps3.setInt(1,ID);
                   ps3.executeUpdate();
                   updateTrackerConCourse(ID);
                   createConCourseUI();
                   
                  System.out.println("Con Course arrival  Record migrated  succesfully");
                  
                
            }
              else{
                  System.out.println("Not data inserted");
              }
              
            }
            
        } catch (Exception ex) {
            System.out.println("Not Connected"+ex);
        }
   }
     public void loopConCourses(int ID){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Config config = new Config();
            Connection con2= (Connection) DriverManager.getConnection("jdbc:mysql://"+config.getMysqlhost()+config.getMysqldb(), config.getMysqlUser(), config.getMysqlPassword());
            //System.out.println("Connected");
            
            Statement st=con2.createStatement();
            ResultSet rs= st.executeQuery("SELECT * FROM pa_concoursearrivals_print_mgr WHERE id='"+ID+"'");
            while(rs.next()){

              int id=rs.getInt(1);
              String permit_no=rs.getString(2);
              String permit_type=rs.getString(3);
              String access_area=rs.getString(4);
              String applicant_name=rs.getString(5);
              String doc_no=rs.getString(6);
              String operator=rs.getString(7);
              String designation=rs.getString(8);
              String application_date=rs.getString(9);
              String expiry_date=rs.getString(10);
              String applicant_photo_file=rs.getString(11);
              String applicant_photo=rs.getString(12);
              String applicant_signature_file=rs.getString(13);
              String applicant_signature=rs.getString(14);
              String ceo_signature_file=rs.getString(15).toString().trim();
              String ceo_signature=rs.getString(16);
              String batch_id=rs.getString(17);
              String airport_airstrip_name=rs.getString(18);
              String print_template_name=rs.getString(19);
              String date=rs.getString(20);
              
              
              PreparedStatement ps2= con2.prepareStatement("INSERT INTO pa_concoursearrivals_printed_cards_mgr VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
              ps2.setInt(1,id);
              ps2.setString(2, permit_no);
              ps2.setString(3, permit_type);
              ps2.setString(4, access_area);
              ps2.setString(5,applicant_name);
              ps2.setString(6, doc_no);
              ps2.setString(7,operator);
              ps2.setString(8, designation);
              ps2.setString(9,application_date);
              ps2.setString(10,expiry_date);
              ps2.setString(11,applicant_photo_file);
              ps2.setString(12,applicant_photo);
              ps2.setString(13,applicant_signature_file);
              ps2.setString(14,applicant_signature);
              ps2.setString(15,ceo_signature_file);
              ps2.setString(16,ceo_signature);
              ps2.setString(17,batch_id);
              ps2.setString(18,airport_airstrip_name);
              ps2.setString(19,print_template_name);
              ps2.setString(20,date);
              
              int check=ps2.executeUpdate();
              if(check>0){
                   PreparedStatement ps3= con2.prepareStatement("DELETE FROM pa_concoursearrivals_print_mgr WHERE id=?");
                   ps3.setInt(1,ID);
                   ps3.executeUpdate();
                   updateTrackerTaxway(ID);
                   
                  System.out.println("Con Courses Record migrated  succesfully");
                
            }
              else{
                  System.out.println("Not data inserted");
              }
              
            }
            
        } catch (Exception ex) {
            System.out.println("Not Connected"+ex);
        }
   }
     
     

    
}
