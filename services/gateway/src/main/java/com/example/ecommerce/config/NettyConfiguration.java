package com.example.ecommerce.config;

import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.boot.web.embedded.netty.NettyServerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.netty.http.server.HttpServer;

@Configuration
public class NettyConfiguration {
    @Bean
    public NettyReactiveWebServerFactory nettyReactiveWebServerFactory() {
        NettyReactiveWebServerFactory webServerFactory = new NettyReactiveWebServerFactory();
        webServerFactory.addServerCustomizers(new CustomNettyWebServerFactory());
        return webServerFactory;
    }

    private static class CustomNettyWebServerFactory implements NettyServerCustomizer {
        @Override
        public HttpServer apply(HttpServer httpServer) {
            return httpServer
                    .httpRequestDecoder(spec -> 
                        spec.maxHeaderSize(65536) // 64KB pour les en-têtes
                            .maxInitialLineLength(65536) // 64KB pour la ligne de requête
                            .maxChunkSize(65536) // 64KB pour les chunks
                            .validateHeaders(false)
                    );
        }
    }
}
