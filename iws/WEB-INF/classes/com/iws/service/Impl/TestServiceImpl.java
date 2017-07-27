package com.iws.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iws.dao.TestDao;
import com.iws.pojo.TestPojo;
import com.iws.service.TestService;
@Service("testServiceImpl")
public class TestServiceImpl implements TestService {
    
    @Autowired
    private TestDao testDao;    
   
    public List<TestPojo> selectAll() {
        
        return testDao.queryAll();
    }

}
