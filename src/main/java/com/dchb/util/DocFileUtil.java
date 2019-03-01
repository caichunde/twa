package com.dchb.util;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class DocFileUtil {
    /**
     * @param
     * @return ReturnMsg
     * *@Description: 保存上传文件
     * @author caichunde
     * @date 2018-11-21
     */
    public static Map<String, String> batchUploadFiles(MultipartFile[] files, String filePath, String sker_idcardno) {
        Map<String, String> resMap = new HashMap();
        String savePath = "", msg = "";
        if (!StringUtils.isEmpty(files)) {
            try {
                savePath = saveUploadedFiles(Arrays.asList(files), filePath, sker_idcardno);
            } catch (Exception e) {
                msg = e.getMessage();
            }
        }
        resMap.put("savePath", savePath);
        resMap.put("msg", msg);
        return resMap;
    }

    /**
     * @param
     * @return ReturnMsg
     * @Description: 保存上传文件
     * @author caichunde
     * @date 2018-11-21
     */

    private static String saveUploadedFiles(List<MultipartFile> files, String filePath, String sker_idcardno) throws Exception {
        Date date = new Date();
        String strDate = DateUtil.dateToString(date);
        String zipPath = filePath + sker_idcardno + "_" + strDate;
        filePath = zipPath + File.separator;
        DocFileUtil.createPath(filePath);
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue;
            }
            byte[] bytes = file.getBytes();
            String fileName = file.getOriginalFilename();
            String wjmc = fileName.substring(0, fileName.lastIndexOf("."));
            String wjgs = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            Path path = Paths.get(filePath + wjmc + "." + wjgs);  //保存上传文件的访问路径
            Files.write(path, bytes);
        }
        String retPath = compressExe(zipPath, zipPath);// 打包保存
        deleteDirectory(zipPath);
        return retPath;
    }

    /**
     * 将服务器中的文件下载到本地
     *
     * @author caichunde
     * @date 创建时间 2018-11-26
     * @since V1.0
     */
    public static void writeFileToResponse(String filepath, String filename,
                                           HttpServletResponse response) throws Exception {
        FileInputStream inputstream = null;
        File file = new File(filepath);
        filename = URLEncoder.encode(filename, "UTF-8");
        response.reset();
        response.setContentType("application/x-download");
        response.addHeader("Content-Length", "" + file.length());
        response.addHeader("Content-Disposition", "attachment;filename="
                + filename);
        try {
            inputstream = new FileInputStream(file);
            DocFileUtil.writeStreamToResponse(inputstream, response);
        } catch (IOException e) {
            throw new Exception("文件读取异常：" + e.getMessage());
        } finally {
            try {
                if (inputstream != null) {
                    inputstream.close();
                }
            } catch (Exception e) {
                throw new Exception("文件读取异常，可能是文件损坏或不存在!错误信息为："
                        + e.getMessage());
            }
        }
    }

    /**
     * 将inputString输出到response中
     *
     * @return
     * @author caichunde
     * @date 创建时间 2018-11-26
     * @since V1.0
     */
    public static void writeStreamToResponse(InputStream inputstream, HttpServletResponse response) throws Exception {
        OutputStream outputstream = null; // 下载流
        try {
            outputstream = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
            byte[] byteValue = new byte[1024];
            int tempValue = 0;
            while ((tempValue = inputstream.read(byteValue)) > 0) {
                outputstream.write(byteValue, 0, tempValue);
            }
            outputstream.flush();
        } catch (IOException e) {
            throw new Exception("文件读取异常：" + e.getMessage());
        } finally {
            try {
                if (outputstream != null) {
                    outputstream.close();
                }
            } catch (Exception e) {
                throw new Exception("输出流存在异常，可能是文件损坏或不存在!错误信息为："
                        + e.getMessage());
            }
        }
    }

    /**
     * 删除目录（文件夹）以及目录下的文件
     *
     * @param sPath 被删除目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     * @author caichunde
     * @date 创建时间 2019年1月7日
     * @since V1.0
     */
    public static boolean deleteDirectory(String sPath) throws Exception {
        // 如果sPath不以文件分隔符结尾，自动添加文件分隔符
        sPath = addSeparator(sPath);
        File dirFile = new File(sPath);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        // 删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            } // 删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag)
            return false;
        // 删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 删除目录空文件夹
     *
     * @param path 被删除目录的文件路径
     * @return
     * @author caichunde
     * @date 创建时间 2019年1月7日
     * @since V1.0
     */
    public static void deleteEmptyFolder(String path) {
        // 如果path不以文件分隔符结尾，自动添加文件分隔符
        path = addSeparator(path);
        File dirFile = new File(path);
        for (File file : dirFile.listFiles()) {
            if (file.isDirectory()) {
                deleteEmptyFolder(file.getAbsolutePath());
                //一直递归到最后的目录
                if (file.listFiles().length == 0) {
                    //如果是文件夹里面没有文件证明是空文件，进行删除
                    file.delete();
                }
            }
        }
    }

    /**
     * 如果path不以文件分隔符结尾，自动添加文件分隔符
     *
     * @param path 如果path不以文件分隔符结尾，自动添加文件分隔符
     * @return
     * @author caichunde
     * @date 创建时间 2019年1月7日
     * @since V1.0
     */
    public static String addSeparator(String path) {
        if (!path.endsWith(File.separator)) {
            path = path + File.separator;
        }
        return path;
    }

    /**
     * 获取项目路径
     *
     * @param path 获取项目路径
     * @return
     * @author caichunde
     * @date 创建时间 2019年1月7日
     * @since V1.0
     */
    public static String getProjectPath() {
        return System.getProperty("user.dir") + File.separator + "twaFiles" + File.separator;
    }

    /**
     * 删除单个文件
     *
     * @param sPath 被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     * @author caichunde
     * @date 创建时间 2018年11月26日
     * @since V1.0
     */
    public static boolean deleteFile(String sPath) throws Exception {
        if (StringUtils.isEmpty(sPath)) {
            throw new Exception("文件路径为空");
        }
        File file = new File(sPath);
        boolean flag = file.delete();
        return flag;
    }

    /**
     * 根据wjid下载文件
     *
     * @author caichunde
     * @date 创建时间 2018-11-26
     * @since V1.0
     */
    public static void downloadFile(HttpServletResponse response, String path, String wjmc) throws Exception {
        DocFileUtil.writeFileToResponse(path, wjmc, response);
    }

    /**
     * 创建目录
     *
     * @author caichunde
     * @date 创建时间 2018-12-24
     * @since V1.0
     */
    public static void createPath(String path) {
        File pathFile = new File(path);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }
    }

    /**
     * 压缩文件夹/文件
     *
     * @param finalFile   压缩后的文件名
     * @param srcPathName 文件相对路径
     * @author caichunde
     * @date 创建时间 2019年1月7日
     * @since V1.0
     */
    public static String compressExe(String finalFile, String srcPathName) {
        String zipPath = finalFile + ".zip";
        File zipFile = new File(zipPath);
        File srcdir = new File(srcPathName);
        if (!srcdir.exists()) {
            throw new RuntimeException(srcPathName + "不存在！");
        }

        Project prj = new Project();
        Zip zip = new Zip();
        zip.setProject(prj);
        zip.setDestFile(zipFile);
        FileSet fileSet = new FileSet();
        fileSet.setProject(prj);
        fileSet.setDir(srcdir);
        zip.addFileset(fileSet);
        zip.execute();
        return zipPath;
    }

    /**
     * 说明：将字节流写入到服务器端的文件中。
     *
     * @param filebyte
     * @param filepath
     * @param filename
     * @throws Exception
     * @author:yjc
     */
    public static void writeByteToServer(byte[] filebyte, String filepath,
                                         String filename) throws Exception {
        ByteArrayInputStream inputstream = null;
        FileOutputStream outputstream = null;
        try {
            if (filename != null && !filename.equals("")) {
                File file = new File(filepath + File.separator + filename);
                file.createNewFile();
                if (filebyte != null && filebyte.length != 0) {
                    inputstream = new ByteArrayInputStream(filebyte);
                    outputstream = new FileOutputStream(file);
                    byte[] b = new byte[1024];
                    int len = 0;
                    while ((len = inputstream.read(b)) != -1) {
                        outputstream.write(b, 0, len);
                    }
                    outputstream.flush();
                    outputstream.close();
                    inputstream.close();
                }
            }
        } catch (IOException e) {
            throw new Exception("文件读取异常，可能是文件损坏或不存在!错误信息为：" + e.getMessage());
        } finally {
            try {
                if (outputstream != null) {
                    outputstream.close();
                }
                if (inputstream != null) {
                    inputstream.close();
                }
            } catch (Exception e) {
                throw new Exception("文件读取异常，可能是文件损坏或不存在!错误信息为："
                        + e.getMessage());
            }
        }
    }
}
