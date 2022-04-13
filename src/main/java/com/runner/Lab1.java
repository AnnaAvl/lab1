/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.runner;

import com.Utis.Gruppyi;
import com.Utis.NewHibernateUtil;
import com.Utis.Studentyi;



import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;

/**
 * @author 19570
 */
public class Lab1 {

    /**
     * @param args the command line argumentss
     */
    public static void main(String[] args) {
        Session s = NewHibernateUtil.getSessionFactory().openSession();
        Transaction t1 = s.beginTransaction();
        List<Studentyi> q = s.createQuery("from Studentyi s").list();
        for (Studentyi u : q) {
            System.out.print(u.getImya() + " " + u.getFamiliya() + " " + u.getOtchestvo() + " " + u.getGruppyi().getNazvanie() + ";\n");
            u.setStatusDate(new Date());
            s.saveOrUpdate(u);
        }
        s.flush();
        t1.commit();

        Transaction t2 = s.beginTransaction();
        List<Gruppyi> d = s.createQuery("from Gruppyi g").list();
        int count;
        for (Gruppyi gruppyi : d){
            count = 0;
            for (Studentyi u : q){
                if (u.getGruppyi().getNazvanie().equals(gruppyi.getNazvanie())){
                    count++;
                }
            }
            System.out.println(gruppyi.getNazvanie() + " - " + count + ";");
        }
        t2.commit();
        s.close();
    }

}
