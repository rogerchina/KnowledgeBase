package com.debuglife.codelabs.corejava;

import java.io.File;
import java.io.FileWriter;


public class TestWritingFile {
    public static void main(String[] args) throws Exception{
        String filePath = "c://test/test.txt";
        String fileContent = new String("wo ai bei jing tian an men");
        
        writeFile(filePath,  fileContent);
    }
    
    public static File writeFile(String filePath, String fileContent) throws Exception{

        File _file = new File(filePath);
        if (!_file.exists()) {
            _file.createNewFile();
        }

        FileWriter _fileWriter = new FileWriter(_file);
        try {
            _fileWriter.write(fileContent);
        } catch (Exception ex) {
            throw ex;
        } finally {
            _fileWriter.close();
        }
        return _file;
    }
}
