/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asureidlistener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class Config {
    InputStream input = Config.class.getResourceAsStream("/passwords.properties");
    Properties prop = new Properties();
    public void readCredenctials(){


Properties prop = new Properties();
          
            try {
                prop.load(input);
                String serverUsername = prop.getProperty("mysqlhost");
                System.out.println("server Name Is :"+serverUsername+prop.getProperty("mysqldb")+prop.getProperty("mysqlhost")+prop.getProperty("mysqluser"));
                
                String serverInterProc = prop.getProperty("mysqldb");
                System.out.println( prop.getProperty("accesshost")+prop.getProperty("accessdb")+prop.getProperty("accesspassword"));
               
            } catch (IOException ex) {
                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            }
  
    }
    public String getAccessHost(){
       String host= null;

            try {
                prop.load(input);
                
                host=prop.getProperty("accesshost");
               
            } catch (IOException ex) {
                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            }
            return host;
}
    public String getAccessDb(){
       String accessdb= null;
  
            try {
                prop.load(input);
                
                accessdb=prop.getProperty("accessdb");
               
            } catch (IOException ex) {
                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            }
            return accessdb;
}
    public String getAccessPassword(){
       String password= null;
  
            try {
                prop.load(input);
                
                password=prop.getProperty("accesspassword");
               
            } catch (IOException ex) {
                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            }
            return password;
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
            return host;
}
    public String getMysqldb(){
       String db= null;
  
            try {
                prop.load(input);
                
                db=prop.getProperty("mysqldb");
               
            } catch (IOException ex) {
                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            }
            return db;
}
    public String getMysqlUser(){
       String user= null;
  
            try {
                prop.load(input);
                
                user=prop.getProperty("mysqluser");
               
            } catch (IOException ex) {
                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            }
            return user;
}
    public String getMysqlPassword(){
       String password= null;
  
            try {
                prop.load(input);
                
                password=prop.getProperty("mysqlpassword");
               
            } catch (IOException ex) {
                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            }
            return password;
}

   
    
}
