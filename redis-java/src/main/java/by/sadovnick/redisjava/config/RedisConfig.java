package by.sadovnick.redisjava.config;

import by.sadovnick.redisjava.model.dto.CarDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    //РЕАЛИЗАЦИЯ ОЧЕРЕДИ ЧЕРЕЗ КЕШ РЕДИСА
//    @Bean
//    public RedisTemplate<String, CarDto> redisTemplate(RedisConnectionFactory factory) {
//        RedisTemplate<String, CarDto> template = new RedisTemplate<>();
//        template.setConnectionFactory(factory);
//
//        template.setKeySerializer(new StringRedisSerializer());
//        Jackson2JsonRedisSerializer<CarDto> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(CarDto.class);
//        template.setValueSerializer(jackson2JsonRedisSerializer);
//        return template;
//    }

    //РЕАЛИЗАЦИЯ КЕША РЕДИСА
//    @Bean
//    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
//
//        Jackson2JsonRedisSerializer<CarDto> jsonRedisSerializer = new Jackson2JsonRedisSerializer<>(CarDto.class);
//        RedisCacheConfiguration redisCacheConfiguration =
//                RedisCacheConfiguration
//                        .defaultCacheConfig()
//                        .entryTtl(Duration.ofSeconds(5))
//                        .serializeValuesWith(
//                                RedisSerializationContext.SerializationPair
//                                        .fromSerializer(jsonRedisSerializer)
//                        );
//        return RedisCacheManager
//                .builder(redisConnectionFactory)
//                .cacheDefaults(redisCacheConfiguration)
//                .build();
//    }

    //реализация кеша как листенер
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        return template;
    }

}
