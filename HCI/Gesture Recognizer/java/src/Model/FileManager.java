package Model;

import java.io.*;

/**
 * Created by kemo on 31/05/2017.
 */
public class FileManager {
    private static FileManager instance;
    private void FileManager(){}
    public static FileManager getInstance()
    {
       if (instance == null) instance = new FileManager();
        return instance;
    }
    public void createFolder(String folderName) {
        if (new File(folderName).exists()) return;
        String[] nestedFolders = folderName.split("/");
        String nextFolderLocation = "";
        for (int i = 0; i < nestedFolders.length; i++) {
            File Folder = new File(nextFolderLocation + nestedFolders[i]);
            //noinspection ResultOfMethodCallIgnored
            Folder.mkdir();
            nextFolderLocation += nestedFolders[i] + "/";
        }
    }
    public String read(String fileLocation)
    {
        try {
            FileReader fileReader  = new FileReader(fileLocation);
            BufferedReader br = new BufferedReader(fileReader);
           StringBuilder s = new StringBuilder();
           String line;
            while ((line = br.readLine()) != null) {
                s.append(line);
            }
            fileReader.close();
            br.close();
            return s.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Boolean exists(String fileLocation)
    {
        return new File(fileLocation).exists();
    }

    @SuppressWarnings("WeakerAccess")
    public BufferedWriter openToWrite(String FileName) {
        try {
            FileWriter fileWriter = new FileWriter(FileName);
            return new BufferedWriter(fileWriter);
        } catch (IOException ex) {
            return null;
        }
    }

    public void write(String fileLocation, String content)
    {
        BufferedWriter bufferedWriter = openToWrite(fileLocation);
        if (bufferedWriter != null) {
            try {
                bufferedWriter.write(content);
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
