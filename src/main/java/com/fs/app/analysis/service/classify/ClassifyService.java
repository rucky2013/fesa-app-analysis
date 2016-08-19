package com.fs.app.analysis.service.classify;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;

import com.fs.app.analysis.utils.HtmlParse;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;

public class ClassifyService {

	public static String myapitoken="UuES7ofn.8947.LFKgPfbL_qUS";
	public static String classifyurl="http://api.bosonnlp.com/classify/analysis";
	
	public static String getClassify(List<String> contents){
		try {
			String str=com.alibaba.fastjson.JSONArray.toJSONString(contents);
			HttpResponse<JsonNode> Response = Unirest
				    .post(classifyurl)
				    .header("Accept", "application/json")
				    .header("Content-Type", "application/json")
				    .header("X-Token", myapitoken)
				    .body(str)
				    .asJson();
			JsonNode node=Response.getBody();
			JSONArray jsonarray= node.getArray();
			for(int i=0;i<contents.size();i++){
				String cls=jsonarray.get(i).toString();
				System.out.println("分类结果:"+cls);
			}
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return "";
	}
	
	public static void main(String[] args){
		List<String> result=new ArrayList<String>();
		result.add("我们都是中国人");
		result.add("我们都是日本人");
		result.add(HtmlParse.htmltotext("<p>俄否决安理会谴责叙军战机空袭阿勒颇平民</p>"));
		getClassify(result);
	}
}
