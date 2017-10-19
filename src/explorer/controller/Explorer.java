package controller;

import bean.ExplorerAttribute;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.HTML;
import java.io.*;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by root on 5/18/17.
 */


@Controller
@RequestMapping("/explorer")
public class Explorer {
    Logger log;
    static final String explorerRoot = "log/";
    static final String downUrl = "explorer/doDownload?target=";
    static final String listUrl = "explorer?target=";

    public Explorer() {
        log = LoggerFactory.getLogger("error");
    }

    @RequestMapping(path = {"/", ""}, method = {RequestMethod.GET, RequestMethod.POST})
    public String explore(HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap model) throws Exception {
        String root = request.getServletContext().getRealPath(explorerRoot);
        String target = (String) request.getParameter("target");
        LinkedList<ExplorerAttribute> fd = this.list(root, target == null ? "" : target);
        request.setAttribute("exploreAttribute", fd);
        return "explorer/explore";
    }

    @RequestMapping(path = {"/doList"}, method = {RequestMethod.GET, RequestMethod.POST})
    public void doList(HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap model) throws IOException {
        String typeParameter = (String) request.getParameter("type");
        if (null == typeParameter) {
            typeParameter = "text/json";
        }
        String relativePath = (String) request.getParameter("dir");
        if (null == relativePath) {
            relativePath = "log";
        }
        String realPath = request.getServletContext().getRealPath("");

        LinkedList<ExplorerAttribute> attrs = this.list(realPath, relativePath);

        String content = null;
        String contentType = null;
        switch (typeParameter) {
            case "text/json":
                ObjectMapper mapper = new ObjectMapper();
                content = mapper.writeValueAsString(encodeWrap((ExplorerAttribute[]) attrs.toArray()));
                contentType = "text/json; charset=utf-8";
                break;
            default:
        }

        response.setContentType(contentType);
        Writer w = response.getWriter();
        w.write(content);
        w.flush();
        w.close();
    }

    @RequestMapping(path = "/doDownload", method = {RequestMethod.GET, RequestMethod.POST})
    public void doDownload(HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap model) throws IOException {
        String target = request.getParameter("target");
        String filename = URLEncoder.encode(target, "utf-8");

        /*
        response.addHeader("Content-disposition", "attachment; filename*=utf8''" + filename);
        log.info("doDownload filename = {}", filename);

        FileNameMap mapper = URLConnection.getFileNameMap();
        String suffix = mapper.getContentTypeFor(target);
        if (suffix == null) {
            suffix = "application/pdf";
        }
        response.setContentType(suffix);
        log.info("doDownload filename suffix = {}", suffix);
        */

        String root = (String) request.getServletContext().getRealPath(explorerRoot);

        InputStream istream = new FileInputStream(root + target);
        OutputStream ostream = response.getOutputStream();

        byte b[] = new byte[512];
        int i  = 0;
        while (-1 !=  (i = istream.read(b, 0, b.length))) {
            ostream.write(b, 0, i);
        }
        ostream.flush();
        ostream.close();
    }

    @RequestMapping(path = "/doUpload", method = {RequestMethod.GET, RequestMethod.POST})
    public String doUpload(HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap model) throws Exception {
        ServletContext ctx = request.getServletContext();
        log.info("file upload:{}" , request.getParameter("file_upload"));
        DiskFileItemFactory factory = new DiskFileItemFactory();
        String path = ctx.getRealPath("/log");
        log.info(path);
        factory.setRepository(new File(path));
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> files = upload.parseRequest(request);
        for (FileItem x : files) {
            log.info("content type:{}", x.getContentType());
            log.info("size:{}", x.getSize());
            log.info("fieldname:{}", x.getFieldName());
            log.info("name:{}", x.getName());
            File file = new File(path + "/" + x.getName());
            x.write(file);
        }
        return null;
    }

    private LinkedList<ExplorerAttribute> list(String root, String target) throws UnsupportedEncodingException {

        File fs = new File(root + target);
        LinkedList<ExplorerAttribute> fd = new LinkedList<ExplorerAttribute>(); //file descriptors
        ExplorerAttribute ea = null;

        if (fs.isDirectory()) {
            File f = null;
            for (int i = 0; i < fs.listFiles().length; i++) {
                f = fs.listFiles()[i];
                ea = new ExplorerAttribute();
                ea.setLength(f.length());
                if (!f.isDirectory()) {
                    ea.setName(HtmlUtils.htmlEscape(f.getName()));
                    ea.setUrl(downUrl + URLEncoder.encode(target + f.getName(), "utf-8"));
                } else {
                    ea.setName(HtmlUtils.htmlEscape(f.getName() + "/"));
                    ea.setUrl(listUrl + URLEncoder.encode(target + f.getName() + "/", "utf-8"));
                }
                fd.add(ea);
            }
        }
        return fd;
    }

    private ExplorerAttribute[] encodeWrap(ExplorerAttribute[] attrs) throws UnsupportedEncodingException {
        Base64.Encoder encoder = Base64.getEncoder();
        for (ExplorerAttribute x : attrs) {
            x.setName(encoder.encodeToString(x.getName().getBytes()));
            x.setUrl(encoder.encodeToString(x.getUrl().getBytes()));
        }
        return attrs;
    }
}
