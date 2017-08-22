package com.test.server;  
  
import net.spy.memcached.MemcachedClient;  
  
import org.springframework.beans.factory.annotation.Autowired;  
  
import com.test.bean.User;  
import com.test.dao.IUserDao;  
  
public class UserServerImpl implements IUserServer  
{  
  
    private IUserDao userDao;  
    private MemcachedClient memcachedClient;  
      
    public User testMethod(String userName)  
    {  
        User user;  
        // 判断缓存中数据是否存在，如果不存在则添加，存在则读取  
        if (this.memcachedClient.get("user") != null)  
        {  
            user = (User) this.memcachedClient.get("user");  
            System.out.println("本次操作是在缓存中查询数据...");  
        }  
        else  
        {  
            user = userDao.getUser(userName);  
            this.memcachedClient.add("user", 100, user);  
            System.out.println("本次操作是在数据库中查询数据...");  
        }  
        return user;  
    }  
      
    public IUserDao getUserDao()  
    {  
        return userDao;  
    }  
    // 依赖注入，根据属性名自动注入  
    @Autowired  
    public void setUserDao(IUserDao userDao)  
    {  
        this.userDao = userDao;  
    }  
      
    public MemcachedClient getMemcachedClient()  
    {  
        return memcachedClient;  
    }  
    // 依赖注入（分布式缓存，在spring中自动生成）  
    @Autowired  
    public void setMemcachedClient(MemcachedClient memcachedClient)  
    {  
        this.memcachedClient = memcachedClient;  
    }  
      
}  