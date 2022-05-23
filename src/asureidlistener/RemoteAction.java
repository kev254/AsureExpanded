/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asureidlistener;

import static asureidlistener.AsureIDListener.createUI;
import static asureidlistener.AsureIDListener.getPermanentPermitId;
import static asureidlistener.AsureIDListener.getStaffId;
import static asureidlistener.AsureIDListener.updateTrackerP;
import static asureidlistener.AsureIDListener.updateTrackerSC;
import com.mysql.jdbc.*;

import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class RemoteAction {
     public void updatePermit(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Config config = new Config();
            Connection con2= (Connection) DriverManager.getConnection("jdbc:mysql://"+config.getMysqlhost()+config.getMysqldb(), config.getMysqlUser(), config.getMysqlPassword());
            //System.out.println("Connected");
            int ID=getPermanentPermitId();
            Statement st=con2.createStatement();
            ResultSet rs= st.executeQuery("SELECT * FROM permanent_pass_print_mgr WHERE id='"+ID+"'");
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
              
              
              PreparedStatement ps2= con2.prepareStatement("INSERT INTO permanent_pass_printed_cards_mgr VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
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
                   PreparedStatement ps3= con2.prepareStatement("DELETE FROM permanent_pass_print_mgr WHERE id=?");
                   ps3.setInt(1,ID);
                   ps3.executeUpdate();
                   updateTrackerP(ID);
                   
                  System.out.println("Record migrated from permanent_pass_print_mgr to permanent_pass_printed_cards_mgr succesfully");
                  createUI();
                
            }
              else{
                  System.out.println("Not data inserted");
              }
              
            }
            
        } catch (Exception ex) {
            System.out.println("Not Connected"+ex);
        }
   }
     public void updateStaffId(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Config config = new Config();
            Connection con2= (Connection) DriverManager.getConnection("jdbc:mysql://"+config.getMysqlhost()+config.getMysqldb(), config.getMysqlUser(), config.getMysqlPassword());
            int ID2=getStaffId();
//            System.out.println(ID2);
            Statement st=con2.createStatement();
            ResultSet rs= st.executeQuery("SELECT * FROM staff_id_print_mgr WHERE id='"+ID2+"'");
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
              
              
              
              
              PreparedStatement ps2= con2.prepareStatement("INSERT INTO staff_id_printed_cards_mgr VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
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
                   PreparedStatement ps3= con2.prepareStatement("DELETE FROM staff_id_print_mgr WHERE id=?");
                   ps3.setInt(1,ID2);
                   ps3.executeUpdate();
                   updateTrackerSC(ID2);
                   
                  System.out.println("Record migrated from staff_id_print_mgr to staff_id_printed_cards_mgr succesfully");
                  createUI();
                
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
            ResultSet rs= st.executeQuery("SELECT * FROM permanent_pass_print_mgr WHERE id='"+ID+"'");
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
              
              
              PreparedStatement ps2= con2.prepareStatement("INSERT INTO permanent_pass_printed_cards_mgr VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
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
                   PreparedStatement ps3= con2.prepareStatement("DELETE FROM permanent_pass_print_mgr WHERE id=?");
                   ps3.setInt(1,ID);
                   ps3.executeUpdate();
                   updateTrackerP(ID);
                   
                  System.out.println("Record migrated from permanent_pass_print_mgr to permanent_pass_printed_cards_mgr succesfully");
                
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
            ResultSet rs= st.executeQuery("SELECT * FROM staff_id_print_mgr WHERE id='"+ID+"'");
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
              
              
              PreparedStatement ps2= con2.prepareStatement("INSERT INTO staff_id_printed_cards_mgr VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
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
                   PreparedStatement ps3= con2.prepareStatement("DELETE FROM staff_id_print_mgr WHERE id=?");
                   ps3.setInt(1,ID);
                   ps3.executeUpdate();
                   updateTrackerSC(ID);
                   
                  System.out.println("Record migrated from staff_id_printing_mgr to staff_id_print_mgr succesfully");
                 
                
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
