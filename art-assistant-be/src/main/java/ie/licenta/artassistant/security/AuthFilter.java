package ie.licenta.artassistant.security;

import ie.licenta.artassistant.common.ErrorCode;
import ie.licenta.artassistant.config.AuthUrls;
import ie.licenta.artassistant.controllers.AuthorizationController;
import ie.licenta.artassistant.dto.AuthorizeRequestDTO;
import ie.licenta.artassistant.dto.AuthorizeResponseDTO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static ie.licenta.artassistant.security.ApiSecurity.API_OPEN_URLS;
import static ie.licenta.artassistant.security.ApiSecurity.API_SECURITY_URLS;

@Data
@Component
@Slf4j
public class AuthFilter implements Filter {

    private String sessionId;
    private String userRole;
    private final AuthUrls authUrls;
    private final RestTemplate restTemplate = new RestTemplate();
    private final AuthorizationController authorizationController;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        String requestUri = httpRequest.getRequestURI();
        if (CorsUtils.isPreFlightRequest(httpRequest)) {
            httpResponse.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        log.info("Request URI: {}", requestUri);
        log.info("Request method: {}", httpRequest.getMethod());
        MethodType requestMethod = MethodType.valueOf(httpRequest.getMethod());

        Endpoint endpoint = new Endpoint(requestUri, requestMethod);
        if (API_OPEN_URLS.contains(endpoint)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            List<Role> roleList = new ArrayList<>();
            Map.Entry<Endpoint, List<Role>> matchedEndpoint = API_SECURITY_URLS.entrySet().stream().filter(apiUrl ->
                    httpRequest.getRequestURL().toString().contains(apiUrl.getKey().getPath())).findFirst().orElse(null);
            if (matchedEndpoint == null) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }


            HttpHeaders authoriseHeaders = new HttpHeaders();
            authoriseHeaders.setContentType(MediaType.APPLICATION_JSON);
            for (Map.Entry<Endpoint, List<Role>> entry : API_SECURITY_URLS.entrySet()) {
                if (Objects.equals(requestMethod, entry.getKey().getMethod())) {
                    List<String> reqPaths = Arrays.stream(requestUri.split("/")).map(String::trim)
                            .filter(elem -> elem.length() != 0).toList();
                    List<String> mapPaths = Arrays.stream(entry.getKey().getPath().split("/")).map(String::trim)
                            .filter(elem -> elem.length() != 0).toList();
                    if (reqPaths.size() == mapPaths.size()) {
                        for (int i = 0; i < reqPaths.size(); i++) {
                            if (mapPaths.get(i).contains("{")) {
                                if (i == reqPaths.size() - 1) {
                                    roleList = entry.getValue();
                                }
                                continue;
                            }
                            if (!Objects.equals(reqPaths.get(i), mapPaths.get(i))) {
                                break;
                            }
                            if (i == reqPaths.size() - 1) {
                                roleList = entry.getValue();
                            }
                        }
                    }
                }
            }
            String sessionId = httpRequest.getHeader("session_id");
            log.info("sessionId: {}", sessionId);
            AuthorizeRequestDTO authoriseDTO = new AuthorizeRequestDTO(sessionId, roleList);
            try {
                HttpEntity<AuthorizeRequestDTO> requestMap = new HttpEntity<>(authoriseDTO, authoriseHeaders);
                ResponseEntity<AuthorizeResponseDTO> responseEntity = authorizationController.authorizeUser(authoriseDTO);
                if (responseEntity.getStatusCode() != HttpStatus.OK || responseEntity.getBody() == null) {
                    httpResponse.sendError(404, ErrorCode.ERR_11_SESSION_FORBIDDEN.EN_MESSAGE);
                    return;
                }
                log.info(">>>>>>>>>>> {}", responseEntity.getBody());
                httpRequest.setAttribute("userId", responseEntity.getBody().getUserId());
                filterChain.doFilter(servletRequest, servletResponse);
            } catch (Exception exception) {
                log.error("Endpoint error", exception);
                httpResponse.sendError(403, exception.getMessage());
            }
        }
    }
}
