package com.remote.exec.central.service.http;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Service
public class RequestEncapsulatingFilter extends OncePerRequestFilter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String requestId = generateFastId();
        long startTime = System.currentTimeMillis();
        logger.info("[REQ.START:" + requestId + " '" + request.getMethod() + " " + request.getRequestURI() +
                "'. REQ.INFO: " + summarize(request));
        request.setAttribute("request-id", requestId);
        response.setHeader("request-id", requestId);
        filterChain.doFilter(request, response);

        logger.info("[REQ.END:" + requestId + " '" + request.getMethod() + " " + request.getRequestURI() + "' STATUS: " +
                response.getStatus() + ". TIME: " + (System.currentTimeMillis() - startTime) + "ms");
    }

    private String generateFastId() {
        return RandomStringUtils.random(25, true, true);
    }

    private String summarize(HttpServletRequest request) {
        HashMap<String, String> headers = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            headers.put(name, request.getHeader(name));
        }
        return headers.toString();
    }
}
