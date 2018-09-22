/*package com.rong.friend.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

*//**
 * 图片操作
 * @author 荣
 *
 *//*
@Component
public class ImageOperation {
	
	@Autowired
	private Random random;
	
	*//**
	 * 上传图片
	 * @param imgPath
	 * @param path
	 * @return
	 * @throws Exception
	 *//*
	public String uploadImg(String imgPath,String path)throws Exception{
		String filePath="";
		File file=new File(imgPath);
		if(file.exists()){
			InputStream is=new FileInputStream(file);
			//文件上传路径
			File folderFile=new File(path);
			if(!folderFile.exists()){
				folderFile.mkdirs();
			}
			//原文件名
			String oldFileName=file.getName();
			//源文件后缀名
			String prefix=oldFileName.substring(oldFileName.lastIndexOf("."), oldFileName.length());
			if(prefix.equalsIgnoreCase(".jpg")
					||prefix.equalsIgnoreCase(".png")
					||prefix.equalsIgnoreCase(".gif")
					||prefix.equalsIgnoreCase(".jpeg")
					||prefix.equalsIgnoreCase(".pneg")
					||prefix.equalsIgnoreCase(".gif")){//判断上传文件是否为图片
				String fileName=System.currentTimeMillis()+random.nextInt(100000)+"_img.jpg";
				File targeFile=new File(path,fileName);
				targeFile.createNewFile();
				//保存
				OutputStream os=new FileOutputStream(targeFile);
				byte temp[]=new byte[1024];
				int size=-1;
				while((size=is.read(temp))!=-1){
					os.write(temp,0, size);
				}
				is.close();
				os.close();
				filePath=path+fileName;
			}else{
				throw new Exception("文件上传格式不正确");
			}
		}else{
			throw new Exception("上传文件不能为空");
		}
		return filePath;
	}

}
*/