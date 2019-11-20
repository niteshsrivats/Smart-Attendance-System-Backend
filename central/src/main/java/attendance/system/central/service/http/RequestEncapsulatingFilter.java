package attendance.system.central.service.http;

import attendance.system.central.security.JwtTokenProvider;
import attendance.system.central.service.AuthorizationEntityService;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Enumeration;
import java.util.HashMap;

import static attendance.system.central.named.Headers.AuthorizationHeader;

/**
 * @author Nitesh (niteshsrivats.k@gmail.com)
 */

@Service
public class RequestEncapsulatingFilter extends OncePerRequestFilter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final JwtTokenProvider tokenProvider;
    private final AuthorizationEntityService authorizationEntityService;


    public RequestEncapsulatingFilter(JwtTokenProvider tokenProvider, AuthorizationEntityService authorizationEntityService) {
        this.tokenProvider = tokenProvider;
        this.authorizationEntityService = authorizationEntityService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String requestId = generateFastId();
        long startTime = System.currentTimeMillis();
        String path = null;
        try {
            path = new URI(request.getRequestURI()).getPath();
            logger.info("[REQ.START:" + requestId + " '" + request.getMethod() + " " + request.getRequestURI() +
                    "'. REQ.INFO: " + summarize(request));
            request.setAttribute("request-id", requestId);
            response.setHeader("request-id", requestId);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        try {
            String jwt = getJwtFromRequest(request);

            if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
                UserDetails userDetails = authorizationEntityService.loadUserByUsername(tokenProvider.getUserIdFromJWT(jwt));
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception ex) {
            logger.error("Could not set user authentication in security context", ex);
        }

        filterChain.doFilter(request, response);

        logger.info("[REQ.END:" + requestId + " '" + request.getMethod() + " " + request.getRequestURI() + "' STATUS: " +
                response.getStatus() + ". TIME: " + (System.currentTimeMillis() - startTime) + "ms");
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        return request.getHeader(AuthorizationHeader);
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
