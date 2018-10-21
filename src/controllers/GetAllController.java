package controllers;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReadWriteDAO;
import item.Paper;

/**
 * Servlet implementation class Search
 */
@WebServlet("/all")
public class GetAllController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReadWriteDAO paperDao = new ReadWriteDAO();
	public static final String ATTRIBUTE_MODEL_TO_VIEW = "papers";
	public static final String PAGE_OK = "/resources/All.jsp";

	/**
	 * Default constructor.
	 */
	public GetAllController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CopyOnWriteArraySet<Paper> itemModel = null;
		itemModel = paperDao.getAll();
		request.setAttribute(ATTRIBUTE_MODEL_TO_VIEW, itemModel);

		getServletContext().getRequestDispatcher(PAGE_OK).include(request, response);

//		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
