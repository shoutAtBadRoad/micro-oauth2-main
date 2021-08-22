package iyp.filter;

import cn.hutool.core.date.DateUtil;
import iyp.token.Token;
import iyp.token.TokenBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author JYP
 * @date 2021/8/21
 **/
//@Component
@Slf4j
public class TokenFilter implements Filter {

    private static String withePath = "/user/login";

    private final static int valid = 3600;

    {
        try {
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            FileInputStream is = new FileInputStream(path.getAbsolutePath()+"/static/config.properties");
            Properties properties = new Properties();
            properties.load(is);
            withePath = properties.getProperty("withePath");
            log.info("withePath load down");
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();
        log.info("请求路径："+ uri);
        if(uri.equals(withePath))
            filterChain.doFilter(servletRequest,servletResponse);
        else {
            String token = request.getHeader("token");
            if(token!=null) {
                Token decode = TokenBuilder.decode(token);
                if ((decode.getTime() + valid) >= DateUtil.currentSeconds()) {
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            }
        }
        response.getWriter().print("denied!");
    }
}
