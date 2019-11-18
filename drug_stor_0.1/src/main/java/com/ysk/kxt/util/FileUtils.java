package com.ysk.kxt.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 文件操作工具类
 * 
 * @author 常宵阳
 *
 */
public class FileUtils {
	
	/**
	 * 删除单个文件
	 * 
	 * @param sPath
	 *            被删除文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public static boolean deleteFile(String sPath) {
		boolean flag = false;
		File file = null;
		file = new File(sPath);
		// 路径为文件且不为空则进行删除  
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 删除目录（文件夹）以及目录下的文件
	 * 
	 * @param sPath
	 *            被删除目录的文件路径
	 * @return 目录删除成功返回true，否则返回false
	 */
	public static boolean deleteDirectory(String sPath) {
		//如果sPath不以文件分隔符结尾，自动添加文件分隔符  
		if (!sPath.endsWith(File.separator)) {
			sPath = sPath + File.separator;
		}
		File dirFile = new File(sPath);
		//如果dir对应的文件不存在，或者不是一个目录，则退出  
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		boolean flag = true;
		//删除文件夹下的所有文件(包括子目录)  
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			//删除子文件  
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			} //删除子目录  
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag)
			return false;
		//删除当前目录  
		if (dirFile.delete()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 重命名文件夹, 文件名
	 * 
	 * @param String
	 *            path 文件夹, 文件路径
	 * @param String
	 *            newName 新文件夹, 文件名
	 * @return 是否操作成功
	 */
	public static boolean reName(String path, String newName) {
		try {
			File file = null;
			file = new File(path);
			
			if (file.exists()) {
				if (file.isFile()) { //文件
					//截取文件绝对路径, 不包含文件名 
					StringBuffer filePath = new StringBuffer(
							file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf(file.getName())));
					filePath.append(newName) //添加新文件名   
							.append(file.getName().substring(file.getName().lastIndexOf("."), file.getName().length())); //添加文件后缀 
					
					//重命名文件   
					file.renameTo(new File(filePath.toString()));
				} else { //文件夹
					//截取文件夹据对路径, 不包含当前文件夹名称    
					StringBuffer filePath = new StringBuffer(
							file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf(file.getName())));
					filePath.append(newName); //添加新文件夹名称 
					
					//重命名文件夹 
					file.renameTo(new File(filePath.toString()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 递归查找文件
	 * 
	 * @param baseDirName
	 *            查找的文件夹路径
	 * @param targetFileName
	 *            需要查找的文件名
	 * @param fileList
	 *            查找到的文件集合
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static void findFiles(String baseDirName, String targetFileName, List fileList) throws IOException {
		
		File baseDir = new File(baseDirName); // 创建一个File对象  
		if (!baseDir.exists() || !baseDir.isDirectory()) { // 判断目录是否存在  
			System.out.println("文件查找失败：" + baseDirName + "不是一个目录！");
		}
		String tempName = null;
		//判断目录是否存在     
		File tempFile;
		File[] files = baseDir.listFiles();
		for (int i = 0; i < files.length; i++) {
			tempFile = files[i];
			if (tempFile.isDirectory()) {
				findFiles(tempFile.getAbsolutePath(), targetFileName, fileList);
			} else if (tempFile.isFile()) {
				tempName = tempFile.getName();
				if (wildcardMatch(targetFileName, tempName)) {
					// 匹配成功，将文件名添加到结果集  
					fileList.add(tempFile.getAbsolutePath());
				}
			}
		}
	}
	
	/**
	 * 通配符匹配
	 * 
	 * @param pattern
	 *            通配符模式
	 * @param str
	 *            待匹配的字符串
	 * @return 匹配成功则返回true，否则返回false
	 */
	private static boolean wildcardMatch(String pattern, String str) {
		int patternLength = pattern.length();
		int strLength = str.length();
		int strIndex = 0;
		char ch;
		for (int patternIndex = 0; patternIndex < patternLength; patternIndex++) {
			ch = pattern.charAt(patternIndex);
			if (ch == '*') {
				//通配符星号*表示可以匹配任意多个字符     
				while (strIndex < strLength) {
					if (wildcardMatch(pattern.substring(patternIndex + 1), str.substring(strIndex))) {
						return true;
					}
					strIndex++;
				}
			} else if (ch == '?') {
				//通配符问号?表示匹配任意一个字符     
				strIndex++;
				if (strIndex > strLength) {
					//表示str中已经没有字符匹配?了。     
					return false;
				}
			} else {
				if ((strIndex >= strLength) || (ch != str.charAt(strIndex))) {
					return false;
				}
				strIndex++;
			}
		}
		return (strIndex == strLength);
	}
	
}
