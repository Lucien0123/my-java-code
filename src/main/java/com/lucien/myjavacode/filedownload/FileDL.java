package com.lucien.myjavacode.filedownload;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2017/7/26.
 */
public class FileDL {

    public static void downloadFromUrl(String trlStr) throws IOException {

        URL url = new URL(trlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(3 * 1000);
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        //得到输入流
        InputStream inputStream = conn.getInputStream();
        //获取自己数组
        byte[] getData = readInputStream(inputStream);

        //文件保存位置
        File saveDir = new File("d:/urlfilesave");
        if (!saveDir.exists()) {
            saveDir.mkdir();
        }
        File file = new File("d:/urlfilesave/123.png");
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        if (fos != null) {
            fos.close();
        }
        if (inputStream != null) {
            inputStream.close();
        }

        System.out.println("info:" + url + " download success");

    }

    /**
     * 从输入流中获取字节数组
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }

    public static void main(String[] args) {

        try{
            String url = "http://219.147.134.12:10090/hljfile//libv3/filesend.nsf/0/0E946462CCA697124825806E0034BD21/$FILE/%E6%96%87%E4%BB%B6%E6%AD%A3%E6%96%87.doc";
            url = "http://101.95.48.97:8005/res/upload/interface/apptutorials/manualstypeico/6f83ce8f-0da5-49b3-bac8-fd5fc67d2725.png";
            downloadFromUrl(url);
        }catch (Exception e) {
            // TODO: handle exception
            System.out.println("error!!!");
        }
    }
}
