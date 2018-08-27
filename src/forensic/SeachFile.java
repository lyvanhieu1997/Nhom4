/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forensic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hieu
 */
public class SeachFile {

  void listFolder(File dir, String x) throws IOException {
        File[] subDirs = dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
  
        BufferedWriter bw = null;
	FileWriter fw = null;
        File fn = new File("D:\\KetQua.txt");
        File[] files = dir.listFiles();    
        for(File file: files) {            
            if (file.toString().endsWith(".txt")){  
                try {                   
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String str;

                    while ((str=br.readLine()) != null) {
                        String[] words = str.split("\\s");
                        for (String word : words) {                          
                            if(word.equalsIgnoreCase(x)){
                                System.out.println(dir.getAbsolutePath()+"\\"+file.getName());
                                
                                
                                try {
                                    if (!fn.exists()) {
                                    fn.createNewFile();
                                    }
                                    // true = append file
                                    fw = new FileWriter(fn.getAbsoluteFile(), true);
                                    bw = new BufferedWriter(fw);
                                    bw.write(dir.getAbsolutePath()+"\\"+file.getName()+"\r\n");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                finally {
 
                                    try {

                                            if (bw != null)
                                                    bw.close();

                                            if (fw != null)
                                                    fw.close();

                                    } catch (IOException ex) {

                                            ex.printStackTrace();

                                    }
                            }
 
                            }                             
                        }
                    }
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(SeachFile.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;
        }
        
        for(File folder: subDirs) {
            listFolder(folder,x);
        }
    }   
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner (System.in);
        System.out.println("Nhap thu muc ban muon tim (vidu:C:\\test):");
        String a = sc.nextLine(); 
        System.out.print("Nhap chuoi can tim: ");
        String x = sc.nextLine();         
        new SeachFile().listFolder(new File(a),x);      
    }
}
