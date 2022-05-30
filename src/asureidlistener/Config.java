/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asureidlistener;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class Config {
    InputStream input = Config.class.getResourceAsStream("/passwords.properties");
    Properties prop = new Properties();
    public String getAccessHost(){
       String host= null;

            try {
                prop.load(input);
                
                host=prop.getProperty("accesshost");
               
            } catch (IOException ex) {
                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            }
            return accesshost;
}
    public String getAccessDb(){
       String accessdb= null;
  
            try {
                prop.load(input);
                
                accessdb=prop.getProperty("accessdb");
               
            } catch (IOException ex) {
                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            }
            return accessdb2;
}
    public String getAccessPassword(){
       String password= null;
  
            try {
                prop.load(input);
                
                password=prop.getProperty("accesspassword");
               
            } catch (IOException ex) {
                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            }
            return accesspassword;
}
    
    //Mysql
        public String getMysqlhost(){
       String host= null;
  
            try {
                prop.load(input);
                
                host=prop.getProperty("mysqlhost");
               
            } catch (IOException ex) {
                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            }
            return mysqlhost;
}
    public String getMysqldb(){
       String db= null;
  
            try {
                prop.load(input);
                
                db=prop.getProperty("mysqldb");
               
            } catch (IOException ex) {
                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            }
            return mysqldb;
}
    public String getMysqlUser(){
       String user= null;
  
            try {
                prop.load(input);
                
                user=prop.getProperty("mysqluser");
               
            } catch (IOException ex) {
                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            }
            return mysqluser;
}
    public String getMysqlPassword(){
       String password= null;
  
            try {
                prop.load(input);
                
                password=prop.getProperty("mysqlpassword");
               
            } catch (IOException ex) {
                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            }
            return mysqlpassword;
}

String mysqlhost="localhost:3306/";
String mysqldb= "asureiddb";
String mysqluser="root";
String mysqlpassword="";


String accesshost="C:\\ProgramData\\HID Global\\Asure ID\\";

String accessdb2="AsureID.mdb";
String accesspassword="loo<oage";
  
}
