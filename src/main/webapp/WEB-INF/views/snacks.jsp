<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.snacks.model.Snack" %>
<%
    // 直接从request属性获取数据
    List<Snack> snacks = (List<Snack>) request.getAttribute("snacks");
    Integer currentPage = (Integer) request.getAttribute("currentPage");
    Integer totalPages = (Integer) request.getAttribute("totalPages");
    String imageBaseUrl = (String) request.getAttribute("imageBaseUrl");

    if (snacks == null) {
        snacks = java.util.Collections.emptyList();
    }
    if (currentPage == null) currentPage = 1;
    if (totalPages == null) totalPages = 1;
    if (imageBaseUrl == null) imageBaseUrl = "https://via.placeholder.com";
%>
<html>
<head>
    <title>日本零食学习</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: Arial, sans-serif; background: #f5f5f5; padding: 20px; }
        .container { max-width: 1200px; margin: 0 auto; }
        h1 { text-align: center; color: #e74c3c; margin-bottom: 30px; }
        .snacks-grid { display: grid; grid-template-columns: repeat(auto-fill, minmax(280px, 1fr)); gap: 20px; }
        .snack-card { background: white; border-radius: 10px; padding: 15px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }
        .snack-title { color: #2c3e50; font-size: 1.2em; margin-bottom: 10px; }
        .snack-japanese { color: #e74c3c; margin-bottom: 5px; }
        .snack-english { color: #27ae60; margin-bottom: 10px; }
        .snack-description { color: #7f8c8d; font-size: 0.9em; }
        .pagination { text-align: center; margin-top: 30px; }
        .page-link { padding: 8px 16px; margin: 0 5px; text-decoration: none; color: #3498db; border: 1px solid #ddd; border-radius: 5px; }
        .current-page { padding: 8px 16px; margin: 0 5px; background: #3498db; color: white; border-radius: 5px; }
    </style>
</head>
<body>
<div class="container">
    <h1>日本零食学习 - 学习日语和英语</h1>

    <% if (snacks.isEmpty()) { %>
    <div style="text-align: center; padding: 50px; background: white; border-radius: 10px;">
        <h3>暂无零食数据</h3>
        <p>请检查数据库连接和表数据</p>
    </div>
    <% } %>

    <div class="snacks-grid">
        <% for (Snack snack : snacks) { %>
        <div class="snack-card">
            <div style="height: 150px; background: #eee; margin-bottom: 15px; display: flex; align-items: center; justify-content: center; border-radius: 5px;">
                <img src="<%= imageBaseUrl %>/<%= snack.getImageName() %>"
                     alt="<%= snack.getTitle() %>"
                     style="max-width: 100%; max-height: 100%;"
                     onerror="this.style.display='none'">
            </div>
            <div class="snack-info">
                <h3 class="snack-title"><%= snack.getTitle() %></h3>
                <p class="snack-japanese">日语: <%= snack.getJapanese() %></p>
                <p class="snack-english">英语: <%= snack.getEnglish() %></p>
                <p class="snack-description"><%= snack.getDescription() %></p>
            </div>
        </div>
        <% } %>
    </div>

    <% if (!snacks.isEmpty()) { %>
    <div class="pagination">
        <% if (currentPage > 1) { %>
        <a href="?page=<%= currentPage - 1 %>" class="page-link">上一页</a>
        <% } %>

        <% for (int i = 1; i <= totalPages; i++) { %>
        <% if (i == currentPage) { %>
        <span class="current-page"><%= i %></span>
        <% } else { %>
        <a href="?page=<%= i %>" class="page-link"><%= i %></a>
        <% } %>
        <% } %>

        <% if (currentPage < totalPages) { %>
        <a href="?page=<%= currentPage + 1 %>" class="page-link">下一页</a>
        <% } %>
    </div>
    <% } %>
</div>
</body>
</html>