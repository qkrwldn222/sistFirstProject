package com.sist.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sist.controller.Controller;
import com.sist.controller.Model;
import com.sist.controller.RequestMapping;
import com.sist.dao.*;

import com.sist.vo.RecipeVO;
@Controller("recipeModel")
public class RecipeModel {

	@RequestMapping("recipe/recipe_list.do")
	public static String recipe_page(Model model){
		String page=model.getRequest().getParameter("page");
		if(page==null){
			page="1";
		}
		int curpage=Integer.parseInt(page);
		Map map=new HashMap();
		int rowSize=6;
		int start=(curpage*rowSize)-(rowSize-1);
		int end=curpage*rowSize;
		  
		map.put("start", start);
		map.put("end", end);
		  
		int total = RecipeDAO.RecipeTotalPage();
		int count = RecipeDAO.RecipeCount();
		count=count-((curpage*rowSize)-rowSize); 
		
		int BLOCK=5;
		   
		int startpage=((curpage-1)/BLOCK*BLOCK)+1;
	   
		int endpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
	  
		int allpage=total;
	   
		if(endpage>allpage)
		{
			   endpage=allpage;
		}
		List<RecipeVO> hlist = RecipeDAO.recipeHotRecipe(3);
		try {
			List<RecipeVO> list = RecipeDAO.recipeListData(map);
			for(RecipeVO vo:list){
				int len=vo.getSummary_in().length();
				if(len>100)
				{
					vo.setSummary_in(vo.getSummary_in().substring(0, 100)+"...");
				}
			}
			model.addAttribute("list", list);
		} catch (Exception e) {
		}
		
		model.addAttribute("startpage", startpage);
		model.addAttribute("endpage", endpage);
		model.addAttribute("curpage", curpage);
		model.addAttribute("allpage", allpage);
		model.addAttribute("BLOCK", BLOCK);
		
		model.addAttribute("total", total);
		model.addAttribute("count", count);
		System.out.println("현재 페이지:"+curpage);
		model.addAttribute("hlist", hlist);
		model.addAttribute("main_jsp", "../recipe/recipe_list.jsp");
		return "../main/main.jsp";
	}
	
	// 디테일 페이지
	@RequestMapping("recipe/recipe_detail.do")
	public static String recipe_detail(Model model){
		String no = model.getRequest().getParameter("no");
		RecipeVO vo = RecipeDAO.recipeDetailData(Integer.parseInt(no));
		String in = new String();
		String[] info = new String[3];
		String tag = vo.getTag();
		
		Map map = new HashMap();
		List<String> tagList = new ArrayList<String>();
		List<String> sList = new ArrayList<String>();
		try {
			in = vo.getInfo().substring(4, vo.getInfo().length()-2);
			info = in.split("##");
			String ingre[] = vo.getIngre().split(",");
			String[] tagStr = tag.split(",");
			String[] step = vo.getStep().split("##");
			String[] step_poster = vo.getSTEP_POSTER().split("##");
			
			for(int i=0; i<tagStr.length;i++){
				tagList.add(tagStr[i]);
			}
			map.put("tagStr", tagStr);
			List<RecipeVO> list = RecipeDAO.relateRecipe(map);
			model.addAttribute("tagStr", tagStr);
			model.addAttribute("list", list);
			model.addAttribute("info", info);
			model.addAttribute("ingre", ingre);
			model.addAttribute("step", step);
			model.addAttribute("step_poster", step_poster);
		} catch (Exception e) {}
		if(vo.getImage()==null){
			vo.setImage("https://previews.123rf.com/images/julynx/julynx1408/julynx140800023/30746516-%EC%82%AC%EC%9A%A9%ED%95%A0-%EC%88%98-%EC%97%86%EA%B1%B0%EB%82%98-%EC%9D%B4%EB%AF%B8%EC%A7%80-%EC%82%AC%EC%A7%84-%EC%97%86%EC%9D%8C.jpg");
		}
		System.out.println(in);
		model.addAttribute("vo", vo);
		model.addAttribute("main_jsp", "../recipe/recipe_detail.jsp");
		return "../main/main.jsp";
	}
}
