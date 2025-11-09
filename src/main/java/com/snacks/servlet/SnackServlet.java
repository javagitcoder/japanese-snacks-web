package com.snacks.servlet;

import com.snacks.dao.SnackDAO;
import com.snacks.model.Snack;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/snacks")
public class SnackServlet extends HttpServlet {
    private SnackDAO snackDAO;
    private static final int PAGE_SIZE = 10;

    @Override
    public void init() throws ServletException {
        snackDAO = new SnackDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int page = 1;
        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            page = Integer.parseInt(pageParam);
        }

        List<Snack> snacks = snackDAO.getAllSnacks(page, PAGE_SIZE);
        int totalCount = snackDAO.getTotalCount();
        int totalPages = (int) Math.ceil((double) totalCount / PAGE_SIZE);

        request.setAttribute("snacks", snacks);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("imageBaseUrl", com.snacks.util.DatabaseUtil.getImageBaseUrl());  // 传递图片基础URL

        request.getRequestDispatcher("/WEB-INF/views/snacks.jsp").forward(request, response);
    }
}