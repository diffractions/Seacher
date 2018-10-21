package controllers;

import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.ReadWriteDAO;
import item.Paper;
import utils.PDFReader;

/**
 * Servlet implementation class AddPapersController
 */
@WebServlet("/add")
@MultipartConfig
public class AddPapersController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ReadWriteDAO paperDao = new ReadWriteDAO();
	public PDFReader reader = new PDFReader();
	public static final String ADD_COUNT = "new_papers_count";
	public static final String ATTRIBUTE_MODEL_TO_VIEW = "new_papers";
	public static final String PAGE_OK = "/resources/Add.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddPapersController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());

		getServletContext().getRequestDispatcher(PAGE_OK).include(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CopyOnWriteArraySet<Paper> itemModel = new CopyOnWriteArraySet<>();

		int count = 0;
		for (Part filePart : request.getParts()) {
			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
			String text = reader.getText(filePart.getInputStream());
			paperDao.addPaperToBD(fileName, text);
			itemModel.add(new Paper(fileName, null));
			count++;

		}
		request.setAttribute(ADD_COUNT, count);
		request.setAttribute(ATTRIBUTE_MODEL_TO_VIEW, itemModel);

		doGet(request, response);
	}

}
