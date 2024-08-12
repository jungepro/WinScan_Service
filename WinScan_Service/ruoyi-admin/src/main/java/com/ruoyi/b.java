package com.ruoyi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class b {

    public static Charset charset = Charset.forName("GBK");
    static List<String[]> list = new ArrayList<>();

    private static void check() throws Exception {
        Runtime runtime = Runtime.getRuntime();
        Process process = null;
        process = runtime
                .exec("cmd /c reg query HKEY_LOCAL_MACHINE\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\");
        BufferedReader in = new BufferedReader(new InputStreamReader(process
                .getInputStream(), "GBK"));
        String string = null;
        while ((string = in.readLine()) != null) {
            process = runtime.exec("cmd /c reg query " + string
                    + " /v DisplayName");
            BufferedReader name = new BufferedReader(new InputStreamReader(
                    process.getInputStream(), "GBK"));
            String[] message = queryValue(string);
            if (message != null) list.add(message);
        }
        in.close();
        process.destroy();

    }

    //具体查询每一个软件的详细信息
    private static String[] queryValue(String string) throws Exception {
        String nameString = "";
        String versionString = "";

        String publisherString = "";
        String uninstallPathString = "";

        Runtime runtime = Runtime.getRuntime();
        Process process = null;
        BufferedReader br = null;

        process = runtime.exec("cmd /c reg query " + string + " /v DisplayName");
        br = new BufferedReader(new InputStreamReader(process
                .getInputStream(), "GBK"));
        br.readLine();
        br.readLine();//去掉前两行无用信息
        if ((nameString = br.readLine()) != null) {
            nameString = nameString.replaceAll("DisplayName    REG_SZ    ", "");    //去掉无用信息
        }

        process = runtime.exec("cmd /c reg query " + string + " /v DisplayVersion");
        br = new BufferedReader(new InputStreamReader(process
                .getInputStream(), "GBK"));
        br.readLine();
        br.readLine();//去掉前两行无用信息
        if ((versionString = br.readLine()) != null) {
            versionString = versionString.replaceAll("DisplayVersion    REG_SZ    ", "");    //去掉无用信息
        }

        process = runtime.exec("cmd /c reg query " + string + " /v Publisher");
        br = new BufferedReader(new InputStreamReader(process
                .getInputStream(), "GBK"));
        br.readLine();
        br.readLine();//去掉前两行无用信息
        if ((publisherString = br.readLine()) != null) {
            publisherString = publisherString.replaceAll("Publisher    REG_SZ    ", "");    //去掉无用信息
        }

        process = runtime.exec("cmd /c reg query " + string + " /v UninstallString");
        br = new BufferedReader(new InputStreamReader(process
                .getInputStream(), "GBK"));
        br.readLine();
        br.readLine();//去掉前两行无用信息
        if ((uninstallPathString = br.readLine()) != null) {
            uninstallPathString = uninstallPathString.replaceAll("UninstallString    REG_SZ    ", "");    //去掉无用信息
        }

        String[] resultString = new String[4];
        resultString[0] = nameString;//== null ? null : new String(nameString.getBytes(),"GB-2312");
        resultString[1] = versionString;//== null ? null : new String(versionString.getBytes(),"GB-2312");
        resultString[2] = publisherString;//== null ? null : new String(publisherString.getBytes(),"GB-2312");
        resultString[3] = uninstallPathString;//== null ? null : new String(uninstallPathString.getBytes(),"GB-2312");
        if (resultString[0] == null) resultString = null;    //没有名字的不显示
        return resultString;
    }

    public static void main(String[] args) throws Exception {
        b.check();
        list.forEach(System.out::println);
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).length; j++) {
                System.out.print(list.get(i)[j]);
            }
            System.out.println();
        }
        System.out.println("sout");
    }


}
