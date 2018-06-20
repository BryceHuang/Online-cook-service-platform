package ofs.id;

import java.io.*;

import org.apache.commons.io.FileUtils;

public class Demo
{
  public static void main( String[] args)
  {
    File   dirFile;
    File   tempFile;
    boolean bFile;
    String   sFileName;    
    bFile = false;

    try
    {
        dirFile = new File("E://test");
        bFile   = dirFile.exists();

        if( bFile == true )
        	
        {
          System.out.println("The folder exists.");
        }
        else
        {
          System.out.println("The folder do not exist,now trying to create a one...");
          bFile = dirFile.mkdir();
          if( bFile == true )
          {
            System.out.println("Create successfully!");
          }
          else
          {
            System.out.println("Disable to make the folder,please check the disk is full or not.");
            System.exit(1);
          }
        }
      
        System.out.println("Now we put files in to the folder...");

        for(int i=0; i<5; i++)
        {
          sFileName = new String("E://test//");
          sFileName += String.valueOf(i+".txt");
          tempFile = new File(sFileName);

          bFile = tempFile.createNewFile();
        }
        for(int i=0;i<5;i++){
        	 sFileName = new String("E://test//");
             sFileName += String.valueOf(i+".txt");
        	tempFile = new File(sFileName);
            File newFile=new File("E://test//a.jpg");
            //tempFile.renameTo(newFile);
            FileUtils.copyFile(tempFile, newFile);
        }
      }catch(IOException e)
      {
        // Exception hadler
      }
      
      if( bFile == true )
        System.out.println("Successfully put files in to the folder!");
      else
        System.out.println("Sorry sir,i don't finish the job!");
  }
} 