package com.ssg.homework.t2021hw.store.jpo;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/*
* PAYMENT 테이블 PMT_ID 키 생성
* */
public class CustomIdGenerator implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {

        Connection connection = sharedSessionContractImplementor.connection();
        try {
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("select count(PMT_ID) as Id from PAYMENT");

            if(rs.next())
            {
                int id = rs.getInt(1)+1;
                StringBuffer sb = new StringBuffer();
                for(int i = 0 ; i < 10 - String.valueOf(id).length(); i++){
                    sb.append(0);
                }
                String generatedId = sb.append(id).toString();
                return generatedId;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
