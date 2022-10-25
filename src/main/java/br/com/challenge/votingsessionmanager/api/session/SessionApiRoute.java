package br.com.challenge.votingsessionmanager.api.session;

import br.com.challenge.votingsessionmanager.api.session.handle.CreateSessionHandle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class SessionApiRoute {
    private static final String SESSION_URL_PATH = "/topics/{id}/sessions";

    @Bean("sessionRoutes")
    public RouterFunction<ServerResponse> makeSessionRoutes(final CreateSessionHandle createSessionHandle) {
        return RouterFunctions.route(
                RequestPredicates.POST(SESSION_URL_PATH)
                        .and(RequestPredicates.contentType(MediaType.APPLICATION_JSON))
                        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                createSessionHandle::createSession);
    }
}
