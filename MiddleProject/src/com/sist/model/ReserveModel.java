package com.sist.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.sist.controller.*;
import com.sist.dao.RecipeDAO;
import com.sist.dao.ReserveDAO;
import com.sist.vo.MemberVO;
import com.sist.vo.RecipeVO;
import com.sist.vo.ReserveVO;

@Controller("reserveModel")
public class ReserveModel {
	
	@RequestMapping("reserve/reserve.do")
	public String reserve_main(Model model){
		
		model.addAttribute("main_jsp", "../reserve/reserve.jsp");
		return "../main/main.jsp";
	}

	
	@RequestMapping("reserve/search.do")
	public String reserve_search(Model model){
		try {
			model.getRequest().setCharacterEncoding("UTF-8");
		} catch (Exception e) {}
		
		
		Map keymap = new HashMap();
		String key = model.getRequest().getParameter("key");
		if(key!=null && key!="") {
			keymap.put("key", key);
		}else{
			key="�˻��ϼ���";
			keymap.put("key", key);
		}
		
		
		
		model.addAttribute("key", key);
		
		return "../reserve/search.jsp";
	}
	
	@RequestMapping("reserve/search2.do")
	public String reserve_search2(Model model){
		try {
			model.getRequest().setCharacterEncoding("UTF-8");
		} catch (Exception e) {}
		
		
		Map keymap = new HashMap();
		String key = model.getRequest().getParameter("key");
		keymap.put("key", key);

		
		
		String page=model.getRequest().getParameter("page");
		if(page==null){
			page="1";
		}
		int curpage=Integer.parseInt(page);
		
		int rowSize=1;
		int start=(curpage*rowSize)-(rowSize-1);
		int end=curpage*rowSize;
		
		keymap.put("end", end);
		keymap.put("start", start);
		int total=RecipeDAO.RecipeSearchPage(keymap);
		/*int count = RecipeDAO.RecipeCount();
		count=count-((curpage*rowSize)-rowSize); */
		
		int BLOCK=5;
		
		int startpage=((curpage-1)/BLOCK*BLOCK)+1;
		
		int endpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		int allpage=total;
		
		if(endpage>allpage)
		{
			endpage=allpage;
		}
		
		
		List<RecipeVO> list = RecipeDAO.recipeSearch(keymap);
		model.addAttribute("list", list);
		
		for(RecipeVO vo:list){
			if(vo.getSummary_in()==null){
				continue;
			}else{
				int len=vo.getSummary_in().length();
				
				if(len>50)
				{
					vo.setSummary_in(vo.getSummary_in().substring(0, 50)+"...");
				}
			}
		}
		
		model.addAttribute("key", key);
		model.addAttribute("startpage", startpage);
		model.addAttribute("endpage", endpage);
		model.addAttribute("curpage", curpage);
		model.addAttribute("allpage", allpage);
		model.addAttribute("BLOCK", BLOCK);
		model.addAttribute("total", total);
		//model.addAttribute("count", count);
		
		return "../reserve/search2.jsp";
	}
	
	@RequestMapping("reserve/recipeSelect.do")
	public String reserve_recipeSelect(Model model){
		String no=model.getRequest().getParameter("no");
		
		RecipeVO vo=ReserveDAO.recipeSelectReserve(Integer.parseInt(no));
		
		model.addAttribute("vo", vo);
		return "../reserve/recipeSelect.jsp";
	}
	
	@RequestMapping("reserve/blank.do")
	public String reserve_blank(Model model){
		
		return "../reserve/blank.jsp";
	}
	
	
	
	@RequestMapping("reserve/area.do")
	public String reserve_area(Model model){
		
		
		return "../reserve/area.jsp";
	}
	@RequestMapping("reserve/areaSelect.do")
	public String reserve_areaSelect(Model model){
		String title=model.getRequest().getParameter("title");
		String areaName=model.getRequest().getParameter("areaName");
		
		
		model.addAttribute("areaT", title);
		model.addAttribute("areaName", areaName);
		return "../reserve/areaSelect.jsp";
	}
	
	
	@RequestMapping("reserve/cheif.do")
	public String reserve_cheif(Model model){
		try {
			model.getRequest().setCharacterEncoding("UTF-8");
		} catch (Exception e) {}
		
		Map map = new HashMap();
		String area=model.getRequest().getParameter("area");	
		map.put("area", area);
		
		System.out.println("@@@@@area"+area);
		//������
		String page=model.getRequest().getParameter("page");
		if(page==null){
			page="1";
		}
		System.out.println("@@@@@page"+page);
		int curpage=Integer.parseInt(page);
		
		int rowSize=2;
		int start=(curpage*rowSize)-(rowSize-1);
		int end=curpage*rowSize;
		
		map.put("end", end);
		map.put("start", start);
		System.out.println("@@@@@start"+start);
		System.out.println("@@@@@end"+end);
		int total=ReserveDAO.recipeSearchReserve(map);
		System.out.println("@@@@@total"+total);
	
		
		int BLOCK=5;
		
		int startpage=((curpage-1)/BLOCK*BLOCK)+1;
		
		int endpage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		
		int allpage=total;
		
		if(endpage>allpage)
		{
			endpage=allpage;
		}
		
		
	
		List<MemberVO> list=ReserveDAO.chiefList(map);
		
		model.addAttribute("list", list);
		model.addAttribute("startpage", startpage);
		model.addAttribute("endpage", endpage);
		model.addAttribute("curpage", curpage);
		model.addAttribute("allpage", allpage);
		model.addAttribute("BLOCK", BLOCK);
		model.addAttribute("total", total);
		
		return "../reserve/cheif.jsp";
	}
	
	@RequestMapping("reserve/cheifSelect.do")
	public String reserve_cheifSelect(Model model){
		String id=model.getRequest().getParameter("id");
		
		MemberVO vo=ReserveDAO.chiefSelect(id);
		
		model.addAttribute("vo", vo);
		return "../reserve/cheifSelect.jsp";
	}
	@RequestMapping("reserve/date.do")
	public String reserve_date(Model model){
		// ����� ��û�� �ޱ�
				String strYear = model.getRequest().getParameter("strYear");
				String strMonth = model.getRequest().getParameter("strMonth");

				// ���� ��¥ �б�
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-d");// 07 08 MM=>1~9
																		// => 01,09
				// �ڹٴ� ����ǥ���� => 10,8(0),16(0x)
				StringTokenizer st = new StringTokenizer(sdf.format(date), "-");

				String sy = st.nextToken();
				String sm = st.nextToken();
				String day1 = st.nextToken();

				if (strYear == null) {
					strYear = sy;
				}
				if (strMonth == null) {
					strMonth = sm;
				}

				int year = Integer.parseInt(strYear);
				int month = Integer.parseInt(strMonth);
				int day = Integer.parseInt(day1);
				
				model.addAttribute("year", year);
				model.addAttribute("month", month);
				model.addAttribute("day", day);
				String[] strWeek = { "��", "��", "ȭ", "��", "��", "��", "��" };
				model.addAttribute("strWeek", strWeek);

				Calendar cal = Calendar.getInstance();
				// cal.set(year,month,1);
				/*
				 * cal.set(Calendar.YEAR, year); cal.set(Calendar.MONTH, month-1);
				 * cal.set(Calendar.DATE, 1);
				 */
				cal.set(year, month - 1, 1);
				//System.out.println(cal.get(Calendar.DAY_OF_WEEK));
				/*
				 * // ���� ���ϱ� int[] lastday={31,28,31,30,31,30, 31,31,30,31,30,31}; // 1��
				 * 1�� 1�� ~ 2018�� 12�� 31�� ���� int total=(year-1)*365 +(year-1)/4
				 * -(year-1)/100 +(year-1)/400; //2019�� 1�� ~ 7���� ���� if((year%4==0 &&
				 * year%100!=0)||(year%400==0)) lastday[1]=29; else lastday[1]=28;
				 * 
				 * for(int i=0;i<month-1;i++) { total+=lastday[i]; }
				 * 
				 * total++;
				 * 
				 * // ���ϱ��ϱ� int week=total%7;
				 */

				int week = cal.get(Calendar.DAY_OF_WEEK);
				week = week - 1;
				int lastday = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
				//System.out.println("Calendar.DATE=" + cal.getActualMaximum(Calendar.DAY_OF_MONTH));
				//System.out.println(lastday);
				model.addAttribute("week", week);
				model.addAttribute("lastday", lastday);
				
				String id=model.getRequest().getParameter("id");
				System.out.println("@@@id"+id);
				List<ReserveVO> list=ReserveDAO.selectdate(id);
				int count=ReserveDAO.selectdatecount(id);
				int[] reday=new int[31];
				int today=day;
				for(int i=0;i<list.size();i++){
					System.out.println("@@@list"+list.get(i).getRdate());
					String alldate=list.get(i).getRdate().substring(0, list.get(i).getRdate().indexOf(" "));
					StringTokenizer st1=new StringTokenizer(alldate,"-");
					if(strMonth.length()==1){
						strMonth="0"+strMonth;
					}
					
					if(st1.nextToken().equals(strYear) && st1.nextToken().equals(strMonth)){
						if(count==3){
							int k=Integer.parseInt(st1.nextToken());
							reday[k-1]=k;
						}
						
						
					}else{
						today=0;
					}
				}
				System.out.println("Integer.parseInt(strMonth):"+(Integer.parseInt(strMonth)<Integer.parseInt(sm)));
				System.out.println("today:"+today);
				//System.out.println("Integer.parseInt(sm):"+);
				List<Integer> rList=new ArrayList<Integer>();
				int u=1;
				for(int k:reday) {
					
					if(k<today || Integer.parseInt(strMonth)<Integer.parseInt(sm)){
						k=u;
						rList.add(k);
						k=0;
					}else{
						rList.add(k);
					}
					
					System.out.println("u:"+u);
					if(u<today || Integer.parseInt(strMonth)<Integer.parseInt(sm)) u++;
					
				}
				model.addAttribute("today", today);
				model.addAttribute("rList", rList);
				/*strYear
				strMonth*/
				/*String dno = model.getRequest().getParameter("dno"); // date no => ������ �����, 1~31�� �
				int[] reday=new int[31];
				StringTokenizer st1=new StringTokenizer(dno,", "); // ������ ", "�� �߶�
				// 1 => reday[0]=1 => reday[25]=26
				while(st1.hasMoreTokens()) {
					int k=Integer.parseInt(st1.nextToken());
					reday[k-1]=k; // ÷�� �ϳ��� �۾ƾߵ�
				}
				List<Integer> rList=new ArrayList<Integer>();
				for(int k:reday) {
					if(k<day) // ���� ���İ͸� ���
						k=0;
					rList.add(k);
				}
				model.addAttribute("rList", rList);*/
		return "../reserve/date.jsp";
	}
	@RequestMapping("reserve/time.do")
	public String reserve_time(Model model){
		String year=model.getRequest().getParameter("year");
		String month=model.getRequest().getParameter("month");
		String day=model.getRequest().getParameter("day");
		String id=model.getRequest().getParameter("id");
		
		if(month.length()==1){
			month="0"+month;
		}
		if(day.length()==1){
			day="0"+day;
		}
		String data=year+"-"+month+"-"+day;
		ReserveVO vo=new ReserveVO();
		vo.setCheifid(id);
		vo.setRdate(data);
		List<ReserveVO> list=ReserveDAO.selectTime(vo);
		/*List<String> tlist=new ArrayList<String>();
		tlist.add("��ħ");
		tlist.add("����");
		tlist.add("����");
		
		model.addAttribute("tlist", tlist);*/
		
		for(int i=0;i<list.size();i++){
			model.addAttribute("ch"+(i+1), i);
		}
		model.addAttribute("list", list);
		model.addAttribute("data", data);
		return "../reserve/time.jsp";
	}
	
	@RequestMapping("reserve/dateSelect.do")
	public String reserve_dateSelect(Model model){
		String datedata=model.getRequest().getParameter("datedata");
		String time=model.getRequest().getParameter("time");
		
		String timekey;
		if(time.equals("��ħ")){
			timekey="1";
		}else if(time.equals("����")){
			timekey="2";
		}else if(time.equals("����")){
			timekey="3";
		}else{
			timekey="����";
		}
		
		model.addAttribute("datedata", datedata);
		model.addAttribute("time", time);
		model.addAttribute("timekey", timekey);
		return "../reserve/dateSelect.jsp";
	}
	
	@RequestMapping("reserve/price.do")
	public String reserve_price(Model model){
		
		return "../reserve/price.jsp";
	}
	@RequestMapping("reserve/priceSelect.do")
	public String reserve_priceSelect(Model model){
		String pricesug=model.getRequest().getParameter("pricesug");
		
		model.addAttribute("pricesug", pricesug);
		return "../reserve/priceSelect.jsp";
	}
	@RequestMapping("reserve/command.do")
	public String reserve_command(Model model){
		
		return "../reserve/command.jsp";
	}
	@RequestMapping("reserve/commandSelect.do")
	public String reserve_commandSelect(Model model){
		String commandText=model.getRequest().getParameter("commandText");
		
		System.out.println(commandText);
		model.addAttribute("commandText", commandText);
		return "../reserve/commandSelect.jsp";
	}
	
}