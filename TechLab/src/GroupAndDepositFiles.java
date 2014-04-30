
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class GroupAndDepositFiles {
	private static int folderNum = 200;
	private static int fileNum = 2000;
	
	public static void main(String[] args)  throws Exception{
		String path = /*"C:\\QDP Related Document\\TestFiles\\hv-largefile";// */"C:\\Users\\zyonglia\\Desktop\\GenerateTest\\GenerateTest\\out";
		
		File[] fileArray = returnFileList(path);
		groupFile(fileArray);
		
	}
	
	private static File[] returnFileList(String path){
		return new File(path).listFiles();
	}
	
	private static void groupFile(File[] fileArray) throws Exception{
		int index = 0;  // file count
		int number = 0; // folder index
		for(int i=0;i<fileArray.length;i++){
			File ff = null;
			
			if(index % fileNum == 0){
				ff = new File("C:\\40WAN\\" + ((index/fileNum)+1));
				number = (index/fileNum)+1;
				System.out.println("--------current folder is " + number + "------------");
				if(!ff.exists())
					ff.mkdir();
			}
			
			index++;
			//System.out.println("C:\\40WAN\\" + number + "\\" + fileArray[i].getName());
			copyFile(fileArray[i], new File("C:\\40WAN\\" + number + "\\" + fileArray[i].getName()));
		}
	}
	
	private static void copyFile(File src,File des) throws IOException{
		InputStream in = new FileInputStream(src);
		OutputStream out = new FileOutputStream(des);
		
		if(!des.exists())
			des.createNewFile();
		
		byte[] buff = new byte[1024];
		int len;
		while((len=in.read(buff)) != -1){
			out.write(buff, 0, len);
		}
		
		in.close();
		out.close();
	}
}
