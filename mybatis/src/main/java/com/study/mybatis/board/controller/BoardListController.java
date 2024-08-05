  package com.study.mybatis.board.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import com.study.mybatis.board.service.BoardServiceImpl;
import com.study.mybatis.board.vo.Board;
import com.study.mybatis.common.template.Pagenation;
import com.study.mybatis.common.vo.PageInfo;

/**
 * Servlet implementation class BoardListController
 */
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int totalRecord = new BoardServiceImpl().selectTotalRecord();
		System.out.println("totalRecord : " + totalRecord);
		int nowPage = Integer.parseInt(request.getParameter("nowPage"));
		PageInfo pi = Pagenation.getPageInfo(totalRecord, nowPage, 5, 3);
		
		ArrayList<Board> list = new BoardServiceImpl().selectList(pi);
		request.setAttribute("pi", pi);
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("WEB-INF/views/board/boardListView.jsp").forward(request, response);   	 
	}

}
