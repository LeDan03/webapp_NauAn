package vn.edu.stu.WebBlogNauAn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    //Xu ly cua redis, giam truy xuat db
    public <T> void saveToCache(String searchKey , T object) {
        redisTemplate.opsForValue().set(searchKey , object);
    }
    public  <T> T getFromCache(String searchKey, Class<T> clazz ) {
        Object cachedObject = redisTemplate.opsForValue().get(searchKey);
        if (cachedObject != null) {
            return clazz.cast(cachedObject);  // Ép kiểu an toàn
        }
        return null;
    }
    public boolean isEmailRegistered(String email) {
        return Boolean.TRUE.equals(redisTemplate.opsForSet().isMember("emails", email));
        //Dùng Boolean.TRUE.equals để tránh lỗi nếu ỡ redis trả null khi xảy ra lỗi
    }

    // Lưu email đã đăng ký vào Redis (Set)
    public void registerEmail(String email) {
        redisTemplate.opsForSet().add("emails", email);
    }
}
