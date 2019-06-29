package org.cs.mgr.file.ctl;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import jd.util.PropUtil;
import org.cs.core.ctl.BaseCtl;
import org.cs.util.FileUtil;
import org.cs.util.OSSClientUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/file")
public class FileCtl extends BaseCtl {
	
	
    @RequestMapping(value="uploadPic", method = RequestMethod.POST)
    @ResponseBody
    public String uploadPic( ModelMap mm, HttpServletRequest request,  @RequestParam(required = false) MultipartFile Filedata) throws Exception {
        String fileName = "";
        JSONObject json = new JSONObject();
        String fileType = request.getParameter("fileType").toString();
        String fileUrl = PropUtil.getString("url.file");
        if(Filedata != null){
            try {
//                fileName = FileUtil.uploadImageAndScale(FileUtil.getFilePath(Integer.valueOf(fileType)), Filedata)[0] ;
                String url=OSSClientUtil.uploadImg2Oss(Filedata,OSSClientUtil.NEWSDIR);
                json.put("fileName", fileName);
                json.put("name", fileName);
                json.put("path", url);
                json.put("thumb", fileName);
//                json.put("rootPath", fileUrl);
                json.put("rootPath", url);
                json.put("status", 1);
            } catch (IOException e) {
                json.put("fileName",  fileName);
                json.put("name",  fileName);
                json.put("path",  fileName);
                json.put("thumb", fileName);
                json.put("rootPath", fileUrl);
                json.put("status", 0);
                e.printStackTrace();
            } 
        } 
        return JSONObject.toJSONString(json, features);
    }
    
    /**
     * 上传文件
     * @Title: uploadFile 
     * @Description: TODO
     * @param mm
     * @param request
     * @param Filedata
     * @return
     * @return: String
     */
    @RequestMapping(value="uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile( ModelMap mm, HttpServletRequest request, @RequestParam(required = false) MultipartFile Filedata){
//    	public String uploadFile( ModelMap mm, HttpServletRequest request, int type,  @RequestParam(required = false) MultipartFile Filedata){
    	String fileName = "";
    	String fileType = request.getParameter("fileType").toString();
    	JSONObject json = new JSONObject();
    	String fileUrl = PropUtil.getString("url.file");
    	if(Filedata != null){
    		try {
    			fileName = FileUtil.uploadFile(FileUtil.getFilePath(Integer.valueOf(fileType)), Filedata) ; 
//    			fileName = FileUtil.uploadImageAndScale(PropUtil.getString("pic.path"), Filedata)[0] ; 
    			json.put("fileName", fileName);
    			json.put("name", fileName);
    			json.put("path", fileUrl + fileName);
    			json.put("thumb", fileName);
    			json.put("rootPath", fileUrl);
    			json.put("status", 1);
    		} catch (IOException e) {
    			json.put("fileName",  fileName);
    			json.put("name",  fileName);
    			json.put("path",  fileName);
    			json.put("thumb", fileName);
    			json.put("rootPath", fileUrl);
    			json.put("status", 0);
    			e.printStackTrace();
    		} 
    	} 
    	return JSONObject.toJSONString(json, features);
    }
	
	
	
}
