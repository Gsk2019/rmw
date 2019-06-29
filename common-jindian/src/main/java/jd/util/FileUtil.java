package jd.util;

import java.io.*;

public class FileUtil {
    /**
     * 功 能: 创建文件夹 参 数: strDir 要创建的文件夹名称 返回值: 如果成功true;否则false
     *
     * @param strDir
     * @return
     */
    public static boolean makeDir(String strDir) {
        File fileNew = new File(strDir);

        if (!fileNew.exists()) {
            return fileNew.mkdirs();
        } else {
            return true;
        }
    }

    /**
     * 获取文件的扩展名
     * 例如： 1.png  返回 png
     * @Description: TODO
     * @param fileName
     * @return
     * @return: String
     */
    public static String getSuffix(String fileName){
        if(fileName == null) return null;
        return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
    }

    /**
     *
     * @Title: inputStreamToFile
     * @Description: TODO
     * @param ins
     * @param file				生成的文件路径
     * @throws IOException
     * @return: void
     */
    public static void inputStreamToFile(InputStream ins, File file) throws IOException{
        OutputStream os = new FileOutputStream(file);
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        try {
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        os.close();
        ins.close();
    }
}
