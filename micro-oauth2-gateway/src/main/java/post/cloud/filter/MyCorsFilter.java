package post.cloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.NettyWriteResponseFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

//@Component
@Slf4j
public class MyCorsFilter implements GlobalFilter{

//    @Override
//    public int getOrder() {
//        // 指定位于 NettyWriteResponseFilter 处理完响应体后移除重复 CORS 响应头
//        return NettyWriteResponseFilter.WRITE_RESPONSE_FILTER_ORDER + 1;
//    }
//
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("指定位于 NettyWriteResponseFilter 处理完响应体后移除重复 CORS 响应头");
        return chain.filter(exchange).then(Mono.defer(() -> {
            exchange.getResponse().getHeaders().entrySet().stream()
                    .filter(kv -> (kv.getValue() != null && kv.getValue().size() > 1))
                    .filter(kv -> (kv.getKey().equals(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN)
                            || kv.getKey().equals(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS)))
                    .forEach(kv -> kv.setValue(new ArrayList<String>() {{
                        add(kv.getValue().get(0));
                    }}));
            return chain.filter(exchange);
        }));
    }


//    @Override
//    public int getOrder() {
//        return Ordered.LOWEST_PRECEDENCE - 2;
//    }

}
