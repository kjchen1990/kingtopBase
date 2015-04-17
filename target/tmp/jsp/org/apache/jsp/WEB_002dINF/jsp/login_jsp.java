package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\r\n");

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print(basePath);
      out.write("statics/styles/common.css\">\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.print(basePath);
      out.write("statics/styles/login.css\">\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.print(basePath);
      out.write("statics/javascript/jquery/jquery-1.11.2.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.print(basePath);
      out.write("statics/javascript/common.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.print(basePath);
      out.write("statics/javascript/login.js\"></script>\r\n");
      out.write("    <title>登录</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("\t<div id=\"headerNav\"></div>\r\n");
      out.write("\t\r\n");
      out.write("\t<!-- 登录部分 -->\r\n");
      out.write("\t<div id=\"login\">\r\n");
      out.write("\t\t<div class=\"fast-login\">\r\n");
      out.write("\t\t\t<h3>\r\n");
      out.write("\t\t\t\t合作账户一键登录\r\n");
      out.write("\t\t\t</h3>\r\n");
      out.write("\t\t\t<div>\r\n");
      out.write("\t\t\t\t<ul>\r\n");
      out.write("\t\t\t\t\t<li><span class=\"qq_bg\">&nbsp;</span><a href=\"javascript:;\">QQ登录</a></li>\r\n");
      out.write("\t\t\t\t\t<li><span class=\"wx_bg\">&nbsp;</span><a href=\"javascript:;\">微信登录</a></li>\r\n");
      out.write("\t\t\t\t\t<li><span class=\"xl_bg\">&nbsp;</span><a href=\"javascript:;\">新浪登录</a></li>\r\n");
      out.write("\t\t\t\t</ul>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t<div class=\"author-login\">\r\n");
      out.write("\t\t\t<h3>\r\n");
      out.write("\t\t\t\t帐号注册\r\n");
      out.write("\t\t\t</h3>\r\n");
      out.write("\t\t\t<form id=\"loginForm\" action=\"\" method=\"post\">\r\n");
      out.write("\t\t\t\t<div class=\"login-error\">\r\n");
      out.write("\t\t\t\t\t<span id=\"loginError\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${error}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</span>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"login-input\">\r\n");
      out.write("\t\t\t\t\t<span class=\"username_icon\"></span>\r\n");
      out.write("\t\t\t\t\t<label for=\"username\">用户名</label>\r\n");
      out.write("\t\t\t\t\t<input id=\"username\" name=\"username\" type=\"text\">\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"login-input\">\r\n");
      out.write("\t\t\t\t\t<span class=\"password_icon\"></span>\r\n");
      out.write("\t\t\t\t\t<label for=\"password\" style=\"position: absolute;\">密码，6-20位字符，建议有字母、数字和符号(.,_)组成</label>\r\n");
      out.write("\t\t\t\t\t<input id=\"password\" name=\"password\" type=\"password\">\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div class=\"login-rember\">\r\n");
      out.write("\t\t\t\t\t<input type=\"checkbox\" id=\"rememberMe\" name=\"rememberMe\" checked=\"checked\">\r\n");
      out.write("\t\t\t\t\t<span>下次自动登录</span>\r\n");
      out.write("\t\t\t\t\t<a>忘记密码？</a>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t<div>\r\n");
      out.write("\t\t\t\t\t<input id=\"login_btn\" type=\"submit\" value=\"登录\">\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</form>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t<!-- 底部 -->\r\n");
      out.write("\t<div id=\"footer\">\r\n");
      out.write("\t\t<div id=\"footer_content\"><span>Copyright © 2014-2015</span></div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
