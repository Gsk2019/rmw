package org.cs.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import jd.util.DateUtil;
import jd.util.PropUtil;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @ClassName: FileUtil
 * @Description: TODO
 * @author: cosco gt.fan@msn.cn
 * @date: 2016年9月13日 下午9:57:22
 */
public class FileUtil {
	static Logger log = Logger.getLogger(FileUtil.class);

	private final static int BUFFER = 8192;
	
	private final static String basePath = PropUtil.getString("file.path");
	
	
	/**
	 * 获取 webroot 路径
	 * @Title: getWebRootPath 
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	public static String getWebRootPath() {
		String resource = Thread.currentThread().getContextClassLoader().getResource("").getFile();
		File directory = new File(resource);
		String parent = directory.getParentFile().getParent();
		return parent;
	}
	
	/**
	 * 获取文件的扩展名 
	 * 例如： 1.png  返回 png 
	 * @Description: TODO
	 * @param file
	 * @return
	 * @return: String
	 */
	public static String getSuffix(MultipartFile file){
		if(file  == null) return null;
		return file.getOriginalFilename().substring(
					file.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();
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
	public static void inputStreamToFile(InputStream ins,File file) throws IOException{
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
	
	
	/**
	 * 根绝类型获取文件路径
	 * @Title: getFilePath 
	 * @Description: TODO
	 * @param type
	 * @return
	 * @return: String
	 */
	public static String getFilePath(int type){
		String filePath = "";
		switch(type){
		case 100:
			filePath = "pic";
			break;
		case 101: // 营业执照图片
			filePath = "license";
			break;
		case 102: // 机构租赁合同
			filePath = "leaseContract";
			break;
		case 104: // 业务人员与讲师合影
			filePath = "staffLecturerPic";
			break;
		case 105: // 业务人员与法人合影
			filePath = "staffCorpPic";
			break;
		case 106: // 业务人员与教室合影
			filePath = "staffClassroomPic";
			break;
		case 107: // 业务人员与教室合影
			filePath = "staffCorpLogoPic";
			break;
			
		case 200:  // 通用
			filePath = "commonFile";
			break;
		case 201:  // 银行流水
			filePath = "bankStatement";
			break;
		case 202: // 资产负债表
			filePath = "balanceSheet";
			break;
		case 203: // 随机往期50名学员就业城市分布比例
			filePath = "employmentCityRate";
			break;
		case 204: // 随机往期50名学员就业薪资比例
			filePath = "employmentSalaryRate";
			break;
		case 205: // 尽调报告
			filePath = "report";
			break;
		default:
			filePath = "other";
		}
		
		return "/" + filePath + "/";
	}
	
	private static String scaleSuffix = "_360.jpg";
	/**
	 * 上传 保存图片并保存缩略图
	 * @throws IOException 
	 */
	/**
	 * 返回第一个路径为原图， 第二个路径为缩略图
	 * @Title: uploadImageAndScale 
	 * @Description: TODO
	 * @param filePath
	 * @param fileImg
	 * @return
	 * @throws IOException
	 * @return: String[]
	 */
	public static String[] uploadImageAndScale(String filePath, MultipartFile fileImg) throws IOException{
		String path = filePath +DateUtil.formatYeayMonth(new Date());
		String strDir = basePath+path;
		FileUtil.makeDir(strDir);
		String fileName = System.currentTimeMillis()+"";
		String imgSuffix = "."+FileUtil.getSuffix(fileImg);
		File file =  new File(strDir, fileName+imgSuffix);
		FileUtil.inputStreamToFile(fileImg.getInputStream(), file);
		String newFilePath = fileName+imgSuffix;
		String thumbPath = fileName+scaleSuffix;
		ImageUtils.scaleNormal(strDir+"/" + newFilePath,strDir+"/" + thumbPath, 360, 360);
		return new String[]{path + "/" + newFilePath, path + "/" +  thumbPath};
	}
	
	public static String uploadFile(String filePath, MultipartFile fileImg) throws IOException{
		String path = filePath +DateUtil.formatYeayMonth(new Date());
		String strDir = basePath+path;
		FileUtil.makeDir(strDir);
		String fileName = System.currentTimeMillis()+""; //如果同时上传多个图片，此图片名字生成方式会有重复
		String imgSuffix = "."+FileUtil.getSuffix(fileImg);
		File file =  new File(strDir, fileName+imgSuffix);
		FileUtil.inputStreamToFile(fileImg.getInputStream(), file);
		return path + "/" + fileName+imgSuffix;
	}
	
	
	/**
	 * 
	 * @Title: downloadFile
	 * @Description: TODO
	 * @param theURL
	 * @param filePath
	 * @param fileName
	 * @return
	 * @return: boolean
	 */
	public static boolean download(URL theURL, String filePath, String fileName) {
		File dirFile = new File(filePath);
		if (!dirFile.exists()) {// 文件路径不存在时，自动创建目录
			dirFile.mkdir();
		}
		// 从服务器上获取图片并保存
		URLConnection connection;
		try {
			connection = theURL.openConnection();

			InputStream in = connection.getInputStream();
			FileOutputStream os = new FileOutputStream(filePath + fileName);
			byte[] buffer = new byte[4 * 1024];
			int read;
			while ((read = in.read(buffer)) > 0) {
				os.write(buffer, 0, read);
			}
			os.close();
			in.close();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
			return false;
		}

	}

	/**
	 * 
	 * @Title: copyTo
	 * @Description: TODO
	 * @param strSourceFileName
	 * @param strDestDir
	 * @return
	 * @return: boolean
	 */
	public static boolean copyTo(String strSourceFileName, String strDestDir) {
		File fileSource = new File(strSourceFileName);
		File fileDest = new File(strDestDir);

		// 如果源文件不存或源文件是文件夹
		if (!fileSource.exists() || !fileSource.isFile()) {
			log.debug("源文件[" + strSourceFileName + "],不存在或是文件夹!");
			return false;
		}

		// 如果目标文件夹不存在
		if (!fileDest.isDirectory() || !fileDest.exists()) {
			if (!fileDest.mkdirs()) {
				log.debug("目录文件夹不存，在创建目标文件夹时失败!");
				return false;
			}
		}

		try {
			String strAbsFilename = strDestDir + File.separator + fileSource.getName();

			FileInputStream fileInput = new FileInputStream(strSourceFileName);
			FileOutputStream fileOutput = new FileOutputStream(strAbsFilename);

			log.debug("开始拷贝文件");

			int count = -1;

			long nWriteSize = 0;
			long nFileSize = fileSource.length();

			byte[] data = new byte[BUFFER];

			while (-1 != (count = fileInput.read(data, 0, BUFFER))) {

				fileOutput.write(data, 0, count);

				nWriteSize += count;

				long size = (nWriteSize * 100) / nFileSize;
				long t = nWriteSize;

				String msg = null;

				if (size <= 100 && size >= 0) {
					msg = "\r拷贝文件进度:   " + size + "%   \t" + "\t   已拷贝:   " + t;
					log.debug(msg);
				} else if (size > 100) {
					msg = "\r拷贝文件进度:   " + 100 + "%   \t" + "\t   已拷贝:   " + t;
					log.debug(msg);
				}

			}

			fileInput.close();
			fileOutput.close();

			log.debug("拷贝文件成功!");
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 功 能: 移动文件(只能移动文件) 参 数: strSourceFileName: 是指定的文件全路径名 strDestDir:
	 * 移动到指定的文件夹中 返回值: 如果成功true; 否则false
	 * 
	 * @Title: moveFile
	 * @Description: TODO
	 * @param strSourceFileName
	 * @param strDestDir
	 * @return
	 * @return: boolean
	 */
	public static boolean moveFile(String strSourceFileName, String strDestDir) {
		if (copyTo(strSourceFileName, strDestDir))
			return delete(strSourceFileName);
		else
			return false;
	}

	/**
	 * 功 能: 删除指定的文件 参 数: 指定绝对路径的文件名 strFileName 返回值: 如果删除成功true否则false;
	 * 
	 * @param strFileName
	 * @return
	 */
	public static boolean delete(String strFileName) {
		File fileDelete = new File(strFileName);

		if (!fileDelete.exists() || !fileDelete.isFile()) {
			log.debug(strFileName + "不存在!");
			return false;
		}

		return fileDelete.delete();
	}

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
	 * 功 能: 删除文件夹 参 数: strDir 要删除的文件夹名称 返回值: 如果成功true;否则false
	 * 
	 * @param strDir
	 * @return
	 */
	public static boolean removeDir(String strDir) {
		File rmDir = new File(strDir);
		if (rmDir.isDirectory() && rmDir.exists()) {
			String[] fileList = rmDir.list();

			for (int i = 0; i < fileList.length; i++) {
				String subFile = strDir + File.separator + fileList[i];
				File tmp = new File(subFile);
				if (tmp.isFile())
					tmp.delete();
				else if (tmp.isDirectory())
					removeDir(subFile);
			}
			rmDir.delete();
		} else {
			return false;
		}
		return true;
	}
	
	/**
	 * excel 导出
	 * 
	 * @param response
	 * @param list 从list.get(i)[1]开始取
	 *            数据
	 * @param titles
	 *            标题
	 * @param name
	 *            文件名
	 */
	@SuppressWarnings("deprecation")
	public static void exportExcel(HttpServletResponse response, List<?> list,
			Object[] titles, String name) {
		try {
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet(name);

			int i = 0;
			HSSFRow row0 = sheet.createRow((short) 0);
			for (Object title : titles) {
				HSSFCell cell0 = row0.createCell((short) i);
				cell0.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell0.setCellValue(title + "");
				i++;
			}

			i = 1;
			for (Object value : list) {
				if (value != null) {
					Object[] values = (Object[]) value;
					HSSFRow row = sheet.createRow((short) i);
					for (int j = 0; j < values.length-1; j++) {
						HSSFCell cell = row.createCell((short) j);
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						cell.setCellValue(values[j+1] + "");
					}
				}
				i++;
			}

			String date = DateUtil.getDate(new Date());

			String downFileName = name + "_" + date + ".xls";
			try {
				downFileName = new String(downFileName.getBytes(), "ISO8859-1");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			
			
			response.setHeader("Content-Disposition", "attachment; filename="
					+ downFileName);
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			ServletOutputStream outputStream = response.getOutputStream();
			workbook.write(outputStream);
			outputStream.close();
			outputStream.flush();
		} catch (Exception e) {
			log.info("excel导出失败，" + e.getMessage());
		}
	}
	
	
	

}

