package sysu.main;

import org.json.JSONArray;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.List;

public class BookOpServlet extends HttpServlet {
    private String addNewBook(HashMap<String, String> map, FileItem item, String destDir,
                                    Statement stmt) {
        try {
            String sql = String.format("select isbn from Books where isbn = '%s'", map.get("isbn"));
            ResultSet res = stmt.executeQuery(sql);
            if(res.next()) {
                res.close();
                return "1";
            }
            DiskFileItem img = (DiskFileItem) item;
            File dest = new File(destDir);
            if(!dest.exists()) {
                dest.mkdirs();
            }
            String destFile = destDir + System.getProperty("file.separator") + map.get("isbn") + "."
                    + FilenameUtils.getExtension(img.getName());
            img.write(new File(destFile));

            sql = String.format("insert into Books values(" +
                            "'%s', '%s', '%s', '%s', '%s', %s, '%s');",
                    map.get("isbn"), map.get("name"), map.get("author"), map.get("publisher"), map.get("publish_date"),
                    map.get("number"), map.get("description"));
            stmt.executeUpdate(sql);
            res.close();
            return "3";
        }
        catch (Exception e) {
            return e.getMessage();
        }

    }
    private JSONArray searchAll(Statement stmt) {
        try {
            ResultSet res = stmt.executeQuery("select * from books");
            return getJsonRes(res);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private JSONArray search(String str, Statement stmt) {
        try {
            String s = "'%" + str + "%'";
            String sql = String.format("select * from books where isbn like %s or name like %s or author like %s",
                    s, s, s);
            ResultSet res = stmt.executeQuery(sql);
            return getJsonRes(res);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private JSONArray getJsonRes(ResultSet res) throws SQLException {
        JSONArray jsonArray = new JSONArray();
        while(res.next()) {
            JSONObject object = new JSONObject();
            object.put("isbn", res.getString("isbn"));
            object.put("name", res.getString("name"));
            object.put("author", res.getString("author"));
            object.put("press", res.getString("press"));
            object.put("date", res.getString("date"));
            object.put("number", res.getString("number"));
            object.put("description", res.getString("description"));
            jsonArray.put(object);
        }
        res.close();
        return jsonArray;
    }

    private String delBook(Statement stmt, String isbn, String coverPath) {
        String sql = String.format("delete from books where isbn = '%s';", isbn);
        try {
            stmt.executeUpdate(sql);
            String[] imgExtensions = {".jpeg", ".jpg", ".png", ".gif"};
            for(String ex : imgExtensions) {
                String destFile = coverPath + isbn + ex;
                File tmp = new File(destFile);
                if(tmp.exists()) {
                    tmp.delete();
                }
            }
            return "1";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    private String updateBook(Statement stmt, HttpServletRequest request) {
        try {
            String sql = String.format("update books " +
                    "set name = '%s', author = '%s', press = '%s', date = '%s', number = %s, description = '%s' " +
                    "where isbn = '%s';",
                    request.getParameter("name"), request.getParameter("author"),
                    request.getParameter("publisher"), request.getParameter("publish_date"),
                    request.getParameter("number"), request.getParameter("description"),
                    request.getParameter("isbn"));
            System.out.println(sql);
            stmt.executeUpdate(sql);
            return "1";
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException {
        System.out.println("yes");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String url = "jdbc:mysql://localhost:3306/library20337230"
                + "?autoReconnect=true&useUnicode=true"
                + "&characterEncoding=UTF-8";
        String user = "user";
        String password = "123";
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = DriverManager.getConnection(url, user, password);
            stmt = conn.createStatement();
        }
        catch (Exception e) {
            response.getWriter().print(e.getMessage());
            return;
        }


        if(ServletFileUpload.isMultipartContent(request)) {
            response.setContentType("text/plain; charset=utf-8");
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            HashMap<String, String> map = new HashMap<>();
            FileItem img = null;

            try {
                List<FileItem> items = upload.parseRequest(request);
                for (FileItem item : items) {
                    if (item.isFormField()) {
                        map.put(item.getFieldName(), item.getString("UTF-8"));
                    } else {
                        img = item;
                    }
                }
                String destDir = getServletContext().getRealPath("/") + "covers";
                response.getWriter().print(addNewBook(map, img, destDir, stmt));

            }
            catch (Exception e) {
                response.getWriter().print(e.getMessage());
            }
        }
        else {
            if(request.getParameter("op").equals("search")) {
                response.setContentType("application/json; charset=utf-8");
                HttpSession session = request.getSession();
                JSONArray array = null;
                if(request.getParameter("str") == null || request.getParameter("str").length() == 0) {
                    array = searchAll(stmt);
                }
                else {
                    array = search(request.getParameter("str"), stmt);
                }
                JSONObject object = new JSONObject();
                object.put("books", array);
                object.put("id", session.getAttribute("id"));
                response.getWriter().print(object);
            }
            else if(request.getParameter("op").equals("del")) {
                response.setContentType("text/plain; charset=utf-8");
                String destDir = getServletContext().getRealPath("/") + "covers" + System.getProperty("file.separator");
                response.getWriter().print(delBook(stmt, request.getParameter("isbn"), destDir));
            }
            else if(request.getParameter("op").equals("update")) {
                response.setContentType("text/plain; charset=utf-8");
                response.getWriter().print(updateBook(stmt, request));
            }
        }
        try {
            stmt.close();
            conn.close();
        } catch (Exception e) {
            response.getWriter().print(e.getMessage());
        }
    }
}
