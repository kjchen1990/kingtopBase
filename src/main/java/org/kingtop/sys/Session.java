
package org.kingtop.sys;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.kingtop.lang.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class Session
{

	public static final Logger log = LoggerFactory.getLogger(Session.class);
	
    public Session()
    {
    }

    public static User getCurrUser()
    {
        try
        {
            HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
            HttpSession session = request.getSession(true);
            User sessionUser = (User)session.getAttribute("user");
            return sessionUser;
        }
        catch(Exception e)
        {
            throw new BaseException(e, "9006", log);
        }
    }

    public static User getCurrUser(HttpServletRequest request)
    {
        HttpSession session = request.getSession(true);
        try
        {
            User user = (User)session.getAttribute("user");
            return user;
        }
        catch(Exception e)
        {
            throw new BaseException(e, "9006", log);
        }
    }


}
