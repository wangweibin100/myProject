package main.java.wang.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WriteCSV {
	/** 读取文件名称 */
	public static final String READ_FILE_NAME = "F:/电表.csv";
	public static final String FILE_PATH = "F:/电表";
	public static void main(String[] args) {  
	    try {
	    	File dir = new File(FILE_PATH);
	        File[] fileList = dir.listFiles();
	        List<Map<String, Object>> result = new ArrayList<>();
	        // 循环文件夹中的文件
	        for(File f:fileList){
	        	if((f.isFile()) && (".csv".equals(f.getName().substring(
	        			f.getName().lastIndexOf("."), f.getName().length())))){
	                BufferedReader reader = new BufferedReader(new FileReader(f.getAbsolutePath())); //换成你的文件名 
	                String line = null;
	                // 读取文件里的字段
	                while((line = reader.readLine())!=null){  
	                    String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分 
	                    Map<String, Object> map = new HashMap<>();
	                    map.put("watchId", item[0]);
	                    map.put("date", item[1]);
	                    map.put("totalNo", item[2]);
	                    result.add(map);
	      
	                }
	            }
	        }
	    	
            // reversed()
            List<Map<String, Object>> list = result.stream().sorted(Comparator.comparing(
            		WriteCSV::comparingWatchId).thenComparing(Comparator.comparing(
    				WriteCSV::comparingDate))).collect(Collectors.toList());
            File csv = new File(READ_FILE_NAME); // CSV数据文件 
            if(csv.exists()) {
            	csv.delete();
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true)); // 附加 
            for (Map<String, Object> item : list) {
            	// 添加新的数据行 
                bw.write(item.get("watchId") + "," + item.get("date") +
                		 "," + item.get("totalNo"));  
                bw.newLine(); 
            }             
            bw.close();  
	    } catch (FileNotFoundException e) {  
	      // File对象的创建过程中的异常捕获 
	      e.printStackTrace();  
	    } catch (IOException e) {  
	      // BufferedWriter在关闭对象捕捉异常 
	      e.printStackTrace();  
	    }  catch (Exception e) {  
	      // BufferedWriter在关闭对象捕捉异常 
	      e.printStackTrace();  
	    } 
	}
	
	private static String comparingWatchId(Map<String, Object> map) {
		return (String) map.get("watchId");
	}
	
	private static String comparingDate(Map<String, Object> map) {
		return (String) map.get("date");
	}
	
	private static int comparingTotalNo(Map<String, Object> map) {
		return (int) map.get("totalNo");
	}
}
