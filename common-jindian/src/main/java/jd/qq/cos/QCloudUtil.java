package jd.qq.cos;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import jd.qrcode.QRCode;
import jd.util.DateUtil;
import jd.util.FileUtil;
import jd.util.PropUtil;
import org.apache.log4j.Logger;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;
import com.qcloud.cos.transfer.Transfer;
import com.qcloud.cos.transfer.Transfer.TransferState;
import com.qcloud.cos.transfer.TransferManager;
import com.qcloud.cos.transfer.TransferProgress;
import com.qcloud.cos.transfer.Upload;

/**
 * 
 * @ClassName: FileUtil
 * @Description: TODO
 * @author: cosco gt.fan@msn.cn
 * @date: 2016年9月13日 下午9:57:22
 */
@Component
public class QCloudUtil {
	static Logger log = Logger.getLogger(QCloudUtil.class);

	private final static int BUFFER = 8192;
	
	private final static String qrcode = PropUtil.getString("file.path");
	
	private static String imgBucket = "answertea-1256269780";// bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
	private static  String appId = "1256269780";
	private static String secretId = "AKIDthjZIqdOQmfgihIWQH83yusj0BBEkSbr";
	private static String secretKey = "V2ZJKJHFWGlRTWdvCObflt6DvxAImA79";


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
			case 101: // 答案
				filePath = "pic/answer";
				break;
			case 102: // 活动
				filePath = "pic/promotion";
				break;
			case 103: // 买单
				filePath = "pic/maidan-01";
				break;
			case 104: // 买单
				filePath = "pic/maidan-02";
				break;
			case 111: // 图库
				filePath = "pic/tuku";
				break;
			case 112: // 手机扫码上传
				filePath = "pic/mobile";
				break;
			case 113: // 文字
				filePath = "pic/hilitecolor";
				break;
			case 200: // 客户端版本
				filePath = "version";
				break;
			case 300: // 二维码
				filePath = "pic/qrcode";
				break;
			default:
				filePath = "pic/other";
		}

		return "/" + filePath + "/";
	}


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

	private static COSClient getCOSClient(String bucketName){
		// 1 初始化用户身份信息(secretId, secretKey)
		COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
		// 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
		ClientConfig clientConfig = new ClientConfig(new Region("ap-beijing"));
		// 3 生成cos客户端
		COSClient cosclient = new COSClient(cred, clientConfig);

		return cosclient;
	}

	/**
	 * 删除文件cenos命令
	 */
	public static void deleteCosFile(String file){
		String warterflv = "rm  -rf "  + file ;
		try{
			Runtime rt = Runtime.getRuntime();
			Process proc = rt.exec(warterflv);
			InputStream stderr = proc.getErrorStream();
			InputStreamReader isr = new InputStreamReader(stderr);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ( (line = br.readLine()) != null){
				System.out.println(line);
			}
			int exitVal = proc.waitFor();
		} catch (Throwable t){
			t.printStackTrace();
		}
	}

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
	public static String[] uploadImageAndScale(String filePath, InputStream fileImg, String fileAllName) throws IOException{
		String fileName = System.currentTimeMillis()+"";
		String path = filePath +DateUtil.formatYeayMonth(new Date());
		
		String strDir = getWebRootPath()+path;
		FileUtil.makeDir(strDir);
		String suffix = FileUtil.getSuffix(fileAllName);
		
		String imgSuffix = "."+suffix;
		
		fileName =  fileName+imgSuffix;
		
		File file =  new File(strDir, fileName);
		FileUtil.inputStreamToFile(fileImg, file);
		
		COSClient cos = getCOSClient(imgBucket);
		
		// 简单文件上传, 最大支持 5 GB, 适用于小文件上传, 建议 20 M 以下的文件使用该接口
		// 指定要上传到 COS 上的路径
		String key = path +"/"+ fileName;
		PutObjectRequest putObjectRequest = new PutObjectRequest(imgBucket, key, file);
		cos.putObject(putObjectRequest);
		cos.shutdown();
		
		deleteCosFile(strDir +"/"+ fileName);
		
		
//		String thumbPath = fileName+scaleSuffix;
//		if(!"gif".equals(suffix)){
//			ImageUtils.scaleNormal(strDir+"/" + newFilePath,strDir+"/" + thumbPath, 360, 360);
//		}
//		return new String[]{path + "/" + newFilePath, path + "/" +  thumbPath};
		return new String[]{path + "/" + fileName};
	}

	private static void showTransferProgress(Transfer transfer,String fileName) {
		log.info(transfer.getDescription());
		do {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				return;
			}
			TransferProgress progress = transfer.getProgress();
			long so_far = progress.getBytesTransferred();
			long total = progress.getTotalBytesToTransfer();
			double pct = progress.getPercentTransferred();
			log.info("文件"+fileName+"上传中....["+so_far+"/"+total+"]");
		} while (transfer.isDone() == false);
		TransferState xfer_state = transfer.getState();
		log.info("文件"+fileName+"上传"+xfer_state);
	}

	@Async
	public void uploadFile(String filePath, File file) throws IOException, InterruptedException{
		String path = filePath ;
		String strDir = getWebRootPath()+path;
//		FileUtil.makeDir(strDir);
		String fileName = file.getName();
//		File file =  new File(strDir, fileName);
//		FileUtil.inputStreamToFile(fileImg.getInputStream(), file);
		
		COSClient cos = getCOSClient(imgBucket);
		
		
		// 指定要上传到 COS 上的路径
		String key = path + fileName;
		
		ExecutorService threadPool = Executors.newFixedThreadPool(32);
		// 传入一个 threadpool, 若不传入线程池, 默认 TransferManager 中会生成一个单线程的线程池。
		TransferManager transferManager = new TransferManager(cos, threadPool);
		// .....(提交上传下载请求, 如下文所属)
		PutObjectRequest putObjectRequest = new PutObjectRequest(imgBucket, key, file);
		// 本地文件上传
		Upload upload = transferManager.upload(putObjectRequest);
		
		showTransferProgress(upload,fileName);
		 // 等待传输结束（如果想同步的等待上传结束，则调用 waitForCompletion）
		upload.waitForUploadResult();
		// 关闭 TransferManger
		transferManager.shutdownNow();
		
		deleteCosFile(strDir + fileName);
	}
    
    
    /**
	 * 二维码部分内容
	 * @param encodern userId
	 * @return
	 * @throws Exception
	 */
	public synchronized static String getQRCode(String encodern){
		//二维码生成
		String path = getFilePath(300);
		String filePath = PropUtil.getString("url.file")+"/"+path;
		String dirPath = getWebRootPath()+"/"+path;
		//创建文件夹
		File file = new File(dirPath);
		if(!file.exists()) {
			file.mkdirs();
		}
		String fileName = encodern+".png";

		encodern = qrcode+"?code="+encodern;
		//生成图片
		QRCode qrcode = QRCode.NEW(encodern).toFile(dirPath+"/"+fileName);
		File qrcodeFile = qrcode.getQrcodeFile();
			
		COSClient cos = getCOSClient(imgBucket);
		
		// 简单文件上传, 最大支持 5 GB, 适用于小文件上传, 建议 20 M 以下的文件使用该接口
		// 指定要上传到 COS 上的路径
		PutObjectRequest putObjectRequest = new PutObjectRequest(imgBucket, path+"/"+fileName, qrcodeFile);
		cos.putObject(putObjectRequest);
		cos.shutdown();
		
		deleteCosFile(dirPath +"/"+ fileName);
		
		return filePath + "/" + fileName;
	}
	
	
	public static void main(String[] args) throws Exception {
		System.out.println(getQRCode("10"));
	}
}

