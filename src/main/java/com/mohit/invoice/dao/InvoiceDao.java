package com.mohit.invoice.dao;

import java.util.Comparator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import com.mohit.invoice.model.Invoice;

public class InvoiceDao {
	public static Session getSession() {
		Configuration con = new Configuration().configure().addAnnotatedClass(Invoice.class);
		ServiceRegistry registery = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).configure().build();
		SessionFactory sf = con.buildSessionFactory(registery);
		Session s = sf.openSession();
		return s;
	}
	public static List<Invoice> getInvoices(){
		Session s = getSession();
		Transaction tx = s.beginTransaction();
		Query q = s.createQuery("from Invoice"); 
		List<Invoice> list = q.list();
		tx.commit();
		return list;
	}
	public static List<Invoice> getInvoices(int id){ 
		List<Invoice> list = getInvoices();
		if(id == 1) {
			list.sort(new Comparator<Invoice>() {
				@Override
				public int compare(Invoice o1, Invoice o2) {
					return Long.compare(o2.getAmount(), o1.getAmount());
				}
			});
		}else if(id == 2) {
			list.sort(new Comparator<Invoice>() {
				@Override
				public int compare(Invoice o1, Invoice o2) {
					return o1.getDate().compareTo(o2.getDate());
				}
			});
		}
		System.out.println(list);
		return list;
	}
	public static void addInvoice(Invoice invoice) {
		Session s = getSession();
		Transaction tx = s.beginTransaction();
		s.persist(invoice);
		tx.commit();		
	}
	public static void deleteInvoice(int id) {
		Session s = getSession();
		Transaction tx = s.beginTransaction();
		s.delete(s.get(Invoice.class, id));
		tx.commit();
	}
}
