package login.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import login.dao.MemberDAO;
import login.vo.MemberBean;

@WebServlet("/phoneCheck")
public class PhoneCheck extends HttpServlet {
        protected void doProcess(HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException {
                
                String ph = request.getParameter("ph");

        		MemberBean mVO = new MemberBean();
        		mVO.setPh(ph);
        		MemberDAO mDAO = new MemberDAO();
        		boolean result = mDAO.phCheck(mVO);
        		String str;
        		String str1;

        		if (result) {
        			str = "사용하실 수 없는 전화번호입니다.";
        			str1 = "0";
        		}else {        			
        			str = "사용 가능한 전화번호입니다.";	
        			str1 = "1";
        		}

        		HashMap<String, Object> map = new HashMap<String, Object>();
        		map.put("str", str);
        		map.put("str1", str1);
        		map.put("ph", ph);

        		JSONObject jObject = new JSONObject();
        		jObject.put("map", map);

        		response.setContentType("application/x-json; charset=utf-8");
        		response.getWriter().print(jObject);
        	}

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException {
                doProcess(request, response);
        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                        throws ServletException, IOException {
                doProcess(request, response);
        }
}
