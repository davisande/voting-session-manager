package br.com.challenge.votingsessionmanager.api.exceptionhandle;

import br.com.challenge.votingsessionmanager.core.exception.UnexpectedErrorException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

@Configuration
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final ObjectMapper objectMapper;

    @Bean
    public WebExceptionHandler exceptionHandler() {
        return (ServerWebExchange serverWebExchange, Throwable ex) -> {
            if (ex instanceof UnexpectedErrorException) {
                return makeResponse(serverWebExchange, ex, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (ex instanceof IllegalArgumentException) {
                return makeResponse(serverWebExchange, ex, HttpStatus.BAD_REQUEST);
            }

            return Mono.error(ex);
        };
    }

    private Mono<Void> makeResponse(final ServerWebExchange serverWebExchange, final Throwable ex, final HttpStatus httpStatus) {
        serverWebExchange.getResponse().setStatusCode(httpStatus);
        serverWebExchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);

        final Mono<DataBuffer> dataBufferMono = makeDateBufferResponse(serverWebExchange, ex.getMessage());

        return serverWebExchange.getResponse().writeWith(dataBufferMono);
    }

    private Mono<DataBuffer> makeDateBufferResponse(final ServerWebExchange serverWebExchange, final String message) {
        final DataBufferFactory bufferFactory = serverWebExchange.getResponse().bufferFactory();
        DataBuffer dataBuffer = null;
        try {
            dataBuffer = bufferFactory.wrap(objectMapper.writeValueAsBytes(new HttpError(message)));
        } catch (JsonProcessingException e) {
            dataBuffer = bufferFactory.wrap("".getBytes());
        }

        return Mono.just(dataBuffer);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class HttpError {
        private String message;

    }
}
