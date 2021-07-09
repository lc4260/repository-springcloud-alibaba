package com.ruoyi.file.utils;

/**
 * 该api主要用于公司软著收集软件系统的源代码
 * 可直接从某一文件目录开始扫描，将其下所有的文件读取写入到同一个文件中，并且已进行处理，无需进行二次修改
 * 处理的方式：
 * 1.去掉了空白行
 * 2.去掉打印语句System.out.println和System.err.println
 * 3.去掉了以//开头的注释行，此行内容均为注释内容，不写入
 * 4.去掉了以//结尾的注释行的注释内容，保留有效代码并写入
 * 5.去掉以代码段注释的方式进行注释的代码段内容，整段注释均不写入，例如此注释段均不会写入到文件中
 * @author liuchun
 * @Package com.ruoyi.file.utils
 * @date 2021/7/2 11:24
 * description: 按源代码文档的规范，大约44行TXT内容可占word文档1页
 */

import java.io.*;
import java.util.*;

public class FileUtil {

    private static int javaAddIng = 0;
    private static int xmlAddIng = 0;
    private static int pages = 0;
    private static int lineNum = 0;
    private static int directoryNum = 0;
    private static StringJoiner fileDirectory = new StringJoiner("、");;
    //用来保存文件的绝对路径的list
    private static LinkedList<String> Filelist = new LinkedList<String>();

    /**
     * 读取文件夹下的所有文件并获取文件的绝对路径
     * @param path
     */
    public static void readFilesDir(String path,List<String> fileTypes){
        LinkedList<File> Dirlist = new LinkedList<File>();
        File dir = new File(path);
        File[] files = dir.listFiles();
        for(File file : files){
            if(file.isDirectory()){
                directoryNum ++;
                Dirlist.add(file);
                fileDirectory.add(file.getName());
            }else{
                System.out.println(path+"目录下，文件有： "+file.getAbsolutePath());
                for (String fileType : fileTypes) {
                    if(file.getAbsolutePath().endsWith(fileType)){
                        Filelist.add(file.getAbsolutePath());
                        System.out.println("筛选后读取的文件： " + file.getAbsolutePath());
                    }
                }
            }
        }
        File temp;
        while(!Dirlist.isEmpty()){
            temp = Dirlist.removeFirst();
            if(temp.isDirectory()){
                files = temp.listFiles();
                if(files == null) {
                    continue;
                }
                for(File file : files){
                    if(file.isDirectory()){
                        Dirlist.add(file);
                    }else{
                        //处理文件内容，可自定义主要哪些文件，在main函数中修改
                        for (String fileType : fileTypes) {
                            if(file.getAbsolutePath().endsWith(fileType)){
                                Filelist.add(file.getAbsolutePath());
                                System.out.println("筛选后读取的文件： " + file.getAbsolutePath());
                            }
                        }
                    }
                }
            }else{
                //处理文件内容,这种情况好像不会发生
                System.out.println("-------------");
            }
        }
    }


    /**
     * 读取单个文件的内容
     * @param file
     * @return 文件的内容，String
     */
    public static String readContents(File file){
        System.out.println("读写了文件："+file.getAbsolutePath());
        String suffixName = file.getName().substring(file.getName().lastIndexOf(".")).toLowerCase();
        StringBuilder res = new StringBuilder();
        try{
            //构造一个BufferedReader类来读取文件
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String s = null;
            //使用readLine方法，一次读一行
            while((s = br.readLine())!=null){
                if(".java".equalsIgnoreCase(suffixName)){
                    res = javaFileInputContext(res,s);
                }else if(".xml".equalsIgnoreCase(suffixName)){
                    res = xmlFileInputContext(res,s);
                }
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return res.toString();
    }

    /**
     * 该方法适用于java类型的文件行内容写入
     * @param res
     * @param oldContext
     * @return
     */
    private static StringBuilder javaFileInputContext(StringBuilder res, String oldContext) {
        //去掉空格开头后的内容
        String clearSpaceStr = clearStartsSpace(oldContext);
        //除去/***/这种格式的注释和swagger注解
        if(clearSpaceStr.startsWith("/**")
                || clearSpaceStr.startsWith("@ApiOperation")
                || clearSpaceStr.startsWith("@ApiImplicitParams")){
            javaAddIng = 1;
            return res;
        }else {
            if(clearSpaceStr.endsWith("*/") || clearSpaceStr.endsWith("})")){
                javaAddIng = 0;
                return res;
            }
            if(javaAddIng == 0){
                //这里定义加入的规则
                HashMap<String,Object> hashMap = javaFileIsAllowedAdd(oldContext,clearSpaceStr);
                if((boolean)hashMap.get("flag")){
                    lineNum ++;
                    res.append( System.lineSeparator() +(String)hashMap.get("msg"));
                }
            }
            return res;
        }
    }
    /**
     * 该方法适用于xml类型的文件行内容写入
     * 只需要去掉以<!--开头，以-->结尾的注释
     * @param res
     * @param oldContext
     * @return
     */
    private static StringBuilder xmlFileInputContext(StringBuilder res, String oldContext) {
        //去掉空格开头后的内容
        String clearSpaceStr = clearStartsSpace(oldContext);
        //除去以<!--开头，以-->结尾的注释
        if(clearSpaceStr.startsWith("<!--")){
            //在不是在同一行的
            if(!clearSpaceStr.endsWith("-->")){
                xmlAddIng = 1;
            }
            return res;
        }else {
            if(clearSpaceStr.endsWith("-->")){
                xmlAddIng = 0;
                return res;
            }
            if(xmlAddIng == 0){
                if(oldContext.replace(" ","").length() != 0){
                    lineNum ++;
                    res.append( System.lineSeparator() +oldContext);
                }
            }
            return res;
        }
    }

    public static HashMap<String,Object> javaFileIsAllowedAdd(String oldContext,String clearSpaceStr){
        HashMap hashMap = new HashMap();
        if(oldContext.length() == 0){
            hashMap.put("flag",false);
            return hashMap;
        }
        //1.以System.out.println开头的
        if(clearSpaceStr.startsWith("System.out.println") || clearSpaceStr.startsWith("System.err.println")){
            hashMap.put("flag",false);
            return hashMap;
        }
        //2.去掉注释，以//开头的
        if(clearSpaceStr.startsWith("//")){
            hashMap.put("flag",false);
            return hashMap;
        }
        //3.不是以//开头的注释，而是写在语句末尾的注释
        if(!clearSpaceStr.startsWith("//") && oldContext.contains("//")){
            //去掉语句后加了//的内容
            int i = oldContext.indexOf("//");
            oldContext = oldContext.substring(0,i);
            //并且去掉空格结尾的内容
            oldContext = clearEndSpace(oldContext);
        }
        //4.去除swagger注解，主要有文字内容，太多了
        if(clearSpaceStr.startsWith("@ApiOperation")){
            hashMap.put("flag",false);
            return hashMap;
        }
        hashMap.put("flag",true);
        hashMap.put("msg",oldContext);
        return hashMap;
    }
    public static HashMap<String,Object> xmlFileIsAllowedAdd(String oldContext,String clearSpaceStr){
        HashMap hashMap = new HashMap();
        if(oldContext.length() == 0){
            hashMap.put("flag",false);
            return hashMap;
        }
        //1.以System.out.println开头的
        if(clearSpaceStr.startsWith("System.out.println") || clearSpaceStr.startsWith("System.err.println")){
            hashMap.put("flag",false);
            return hashMap;
        }
        //2.去掉注释，以//开头的
        if(clearSpaceStr.startsWith("//")){
            hashMap.put("flag",false);
            return hashMap;
        }
        //3.不是以//开头的注释，而是写在语句末尾的注释
        if(!clearSpaceStr.startsWith("//") && oldContext.contains("//")){
            //去掉语句后加了//的内容
            int i = oldContext.indexOf("//");
            oldContext = oldContext.substring(0,i);
            //并且去掉空格结尾的内容
            oldContext = clearEndSpace(oldContext);
        }
        hashMap.put("flag",true);
        hashMap.put("msg",oldContext);
        return hashMap;
    }
    /**
     * 去掉某字串空格开头的字符
     * @param s
     * @return
     */
    public static String clearStartsSpace(String s){
        boolean flag = true;
        while (flag){
            if(s.startsWith(" ")){
                s = s.substring(1,s.length());
            } else {
                break;
            }
        }
        return s;
    }
    public static String clearEndSpace(String s){
        while (true){
            if(s.endsWith(" ")){
                s = s.substring(0,s.lastIndexOf(" "));
            } else {
                break;
            }
        }
        return s;
    }
    /**
     * 保存文件
     */
    public static void saveFiles(String contents, String output){
        File outputFile = new File(output);
        try {
            if(!outputFile.exists()){
                outputFile.createNewFile();
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile, true));
            bw.write(contents);
            bw.flush();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //合并所有文件到一个文件中
    public static void mergeFiles(List<String> files, String output){
        if(files == null){
            System.out.println("Error:文件list为空");
        }
        //先清空保存到的目标文件内容
        File targetFile = new File(output+"/"+"test.txt");
        if (targetFile.exists()) {
            targetFile.delete();
        }
        for(String filepath : files){
            String contents = readContents(new File(filepath));
            saveFiles(contents,output+"/"+"test.txt");
        }
        System.out.println("合并完成");
    }

    public static void main(String[] args) {
        long startTimeMillis = System.currentTimeMillis();
        /**
         * 使用时按需修改path和out
         */
        //1.读取那个目录下的文件内容
//        String path = "E:\\workspace-idea\\github\\RuoYi-Cloud\\ruoyi-modules";
        String path = "E:\\workspace-idea\\github\\RuoYi-Vue\\ruoyi-common";
        //2.创建输出的目标文件目录
        String out = "F:/test";
        File file = new File(out);
        if(!file.exists()){
            file.mkdir();
        }
        //3.一页word有多少行TXT文件数据
        int size = 44;
        //4.只读取哪些类型的文件，可自定义主要哪些文件,这里只要了java文件
        // 其他类型的文件，如xml文件，该类型的文件注释方式也不同还未进行编写
        List<String> fileTypes = new ArrayList<>();
        fileTypes.add(".java");
//        fileTypes.add(".xml");

        //扫描满足上述条件的所有文件
        readFilesDir(path,fileTypes);
        //将扫描后的所有文件的内容合并在同一个文件中
        mergeFiles(Filelist, out);
        long endTimeMillis = System.currentTimeMillis();
        System.out.println("共花费时长：" + (endTimeMillis-startTimeMillis) + "毫秒");
        if ((lineNum % size) == 0) {
            pages = lineNum / size;
        } else {
            pages = lineNum / size+ 1;
        }
        System.out.println(path+"目录下，共有文件夹： "+directoryNum + " 个,分别是："+fileDirectory);
        System.out.println(path+"目录下，共有有效文件： "+ Filelist.size() + " 个");
        System.out.println("所有文件共有 " +lineNum+"行");
        System.out.println("word文档大约共有 " +pages+"页");
    }
}
