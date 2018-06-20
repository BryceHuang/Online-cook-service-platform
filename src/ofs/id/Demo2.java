package ofs.id;

import java.io.File;

public class Demo2 {
 
		  
	    public void renameFile(String file, String toFile) {  
	  
	        File toBeRenamed = new File(file);  
	        //检查要重命名的文件是否存在，是否是文件  
	        if (!toBeRenamed.exists() || toBeRenamed.isDirectory()) {  
	  
	            System.out.println("File does not exist: " + file);  
	            return;  
	        }  
	  
	        File newFile = new File(toFile);  
	  
	        //修改文件名  
	        if (toBeRenamed.renameTo(newFile)) {  
	            System.out.println("File has been renamed.");  
	        } else {  
	            System.out.println("Error renmaing file");  
	        }  
	  
	    }  
	  
	    public static void main(String[] args) {  
	        new Demo2().renameFile("E:\\temp\\file1.txt", "E:\\temp\\file2.txt");  
	    }  
	  
}
