
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class GroupAndDepositFiles {
	//file count in every folder
	private static int fileCount = 4;

	public static void main(String [] args){
		String srcPath = "D:\\qdpFiles\\pi";
		String desPath = "D:\\qdpFiles\\pi";
		File[] fileArray = returnFileArray(srcPath);
		groupFiles(fileArray, desPath);

		//output the files info
		/*
		for(int i=0;i<fileArray.length;i++){
			System.out.println(fileArray[i].isFile() + "\t" + fileArray[i].getName());
		} */
	}

	public static File[] returnFileArray(String path){
		return new File(path).listFiles();
	}

	public static void groupFiles(File[] files,String path){
		int folderIndex = 0;
		for(int i=0;i<files.length;i++){
			File f = null;
			if(i%fileCount == 0){
				folderIndex = (i/fileCount)+1;
				f = new File(path + "\\" + folderIndex);
				if(!f.exists())
					f.mkdir();
			}

			copyFile(files[i],new File(path + "\\" + folderIndex + "\\" + files[i].getName()));
		}
	}

	public static void copyFile(File src, File des){
		InputStream in = null;
		OutputStream out = null;
		try{
			in = new FileInputStream(src);
			out = new FileOutputStream(des);
		
			byte[] buff = new byte[1024];
			int len;
			while((len=in.read(buff)) != -1){
				out.write(buff,0,len);
			}
		} catch(FileNotFoundException e){
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		} finally{
			try{
				in.close();
				out.close();
			} catch(IOException e){
				e.printStackTrace();
			}
		}
	}

}
