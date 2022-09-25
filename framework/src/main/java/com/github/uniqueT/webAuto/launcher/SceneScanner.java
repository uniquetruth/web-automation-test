package com.github.uniqueT.webAuto.launcher;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.github.uniqueT.webAuto.testScript.anno.SceneComment;

/**
 * this class is used to find test scenes in the main directory (in script project) by the @-started value of run.xml file's scene parameter
 * @author uniqueT
 *
 */
public class SceneScanner {
	
	private static boolean isScanned = false;
	private static Map<String, Class<?>> sceneMap = new HashMap<String, Class<?>>();
	private static String mainRoot;
	
	/**
	 * find scene class by its {@linkplain com.ulic.test.webui.testScript.anno.SceneComment SceneComment} anno.
	 * @param comment value of anno
	 * @return scene class
	 * @throws ReflectiveOperationException if can't find the scene
	 */
	static Class<?> getSceneFromComment(String comment) throws ReflectiveOperationException {
		if(!isScanned) {
			try {
				findScenes();
			}catch(Exception e) {
				throw new ReflectiveOperationException(e);
			}
		}
		if(sceneMap.containsKey(comment)) {
			return sceneMap.get(comment);
		}else {
			throw new ReflectiveOperationException("can not find scene with comment "+comment);
		}
	}

	private static void findScenes() throws IOException, ClassNotFoundException {
		String root = SceneScanner.class.getClassLoader().getResource(".").getPath();
		//find from main directory
		if(root.endsWith("/")) {
			root = root.substring(0, root.length()-1);
		}
		mainRoot = root.substring(0, root.lastIndexOf("/")+1)+"main";
		findScenesInDir(new File(mainRoot));
		isScanned = true;
	}
	
	private static void findScenesInDir(File dir) throws IOException, ClassNotFoundException {
		for(File f : dir.listFiles()) {
			if(f.isDirectory()) {
				findScenesInDir(f);
			}else if(f.getName().endsWith(".class")){
				//检查常量池里是否有SceneComment
				if(checkUsingAnno(f)) {
					//将类实际加载进来做二次检查，确保有SceneComment注解
					String classname = f.getAbsolutePath().substring(mainRoot.length(), 
							f.getAbsolutePath().length()-6);
					//System.out.println("luanfei test +++ classname="+classname);
					Class<?> c = Class.forName(classname.replace(File.separator, "."));
					if(c.isAnnotationPresent(SceneComment.class)) {
						sceneMap.put(c.getAnnotation(SceneComment.class).value(), c);
					}
				}
			}
		}
	}
	
	private static boolean checkUsingAnno(File classFile) throws IOException{
		BufferedInputStream bis = null;
		byte[] u2 = new byte[2], u3 = new byte[3], u4 = new byte[4], u8 = new byte[8];
		try {
			bis = new BufferedInputStream(new FileInputStream(classFile));
			//magic
			bis.read(u4);
			//minor_version
			bis.read(u2);
			//major_version
			bis.read(u2);
			//cp length
			bis.read(u2);
			int cpLength = byteArray2Int(u2);
			
			byte[] tag = new byte[1];
			for(int i=1;i<cpLength;i++) { //CONSTANT_POOL's index starts from 1
				bis.read(tag);
				switch(tag[0]) {
				case 1: //Utf8_info
					bis.read(u2);
					int u8l = byteArray2Int(u2);
					byte[] strByte = new byte[u8l];
					bis.read(strByte);
					String str = new String(strByte, "utf-8");
					//System.out.println(str);
					if("Lcom/github/uniqueT/webAuto/testScript/anno/SceneComment;".equals(str)){
						return true;
					}
					break;
				case 3: //Integer_info
					bis.read(u4);
					break;
				case 4: //Float_info
					bis.read(u4);
					break;
				case 5: //Long_info
					bis.read(u8);
					i++; //phantom index
					break;
				case 6: //Double_info
					bis.read(u8);
					i++; //phantom index
					break;
				case 7: //Class_info
					bis.read(u2);
					break;
				case 8: //String_info
					bis.read(u2);
					break;
				case 9: //Fieldref_info
					bis.read(u4);
					break;
				case 10: //Methodref_info
					bis.read(u4);
					break;
				case 11: //InterfaceMethodref_info
					bis.read(u4);
					break;
				case 12: //NameAndType_info
					bis.read(u4);
					break;
				case 15: //MethodHandle_info
					bis.read(u3);
					break;
				case 16: //MethodType_info
					bis.read(u2);
					break;
				case 17: //	Dynamic_info
					bis.read(u4);
					break;
				case 18: //InvokeDynamic_info
					bis.read(u4);
					break;
				case 19: //Module_info
					bis.read(u2);
					break;
				case 20: //Package_info
					bis.read(u2);
					break;
				default:
					//throw new ClassNotFoundException("unsupported class file structure");
					return false;
				}
			}
		}catch(IOException e) {
			throw e;
		}finally {
			if(bis!=null) {
				bis.close();
			}
		}
		return false;
	}
	
	private static int byteArray2Int(byte[] byteArray) {
		int r = 0;
		for(int i=byteArray.length;i>0;i--) {
			r += (byteArray[i-1]&0xff)*Math.pow(256, byteArray.length-i);
		}
		return r;
	}

}
